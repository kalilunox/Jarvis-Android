package com.jarvis.brain;

import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.location.LocationRequestCompat;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.actions.SearchIntents;
import com.jarvis.app.VoiceService;
import com.jarvis.app.ui.JarvisActivity;
import com.jarvis.brain.aiprovider.AICompletionRequest;
import com.jarvis.brain.aiprovider.AIRequestComplexity;
import com.jarvis.brain.aiprovider.AiTaskProfile;
import com.jarvis.brain.aiprovider.ChatSentenceSink;
import com.jarvis.brain.aiprovider.ChatStreamPipeline;
import com.jarvis.brain.aiprovider.ChatStreamResult;
import com.jarvis.brain.aiprovider.LocalLLMProvider;
import com.jarvis.brain.aiprovider.PrivacyLevel;
import com.jarvis.brain.llm.LlmContextAssembly;
import com.jarvis.brain.semantic.LlmStructuredSemanticInterpreter;
import com.jarvis.core.agent.AgentLoopResult;
import com.jarvis.core.aioutput.CloudToolAction;
import com.jarvis.core.aioutput.StructuredAiDiagnostics;
import com.jarvis.core.aioutput.StructuredAiLimits;
import com.jarvis.core.autonomy.AutonomyLevel;
import com.jarvis.core.capability.CapabilityDescriptor;
import com.jarvis.core.capability.CapabilityDiscovery;
import com.jarvis.core.common.Tool;
import com.jarvis.core.common.ToolExecutor;
import com.jarvis.core.common.ToolPolicy;
import com.jarvis.core.common.ToolRegistry;
import com.jarvis.core.common.model.AssistantResponse;
import com.jarvis.core.common.model.AssistantResponseStatus;
import com.jarvis.core.common.model.ConversationContext;
import com.jarvis.core.common.model.Intent;
import com.jarvis.core.common.model.IntentType;
import com.jarvis.core.common.model.PendingAction;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import com.jarvis.core.common.model.UserRequest;
import com.jarvis.core.discovery.CatalogToolDiscovery;
import com.jarvis.core.discovery.DiscoveryResult;
import com.jarvis.core.event.AssistantEvent;
import com.jarvis.core.event.JarvisEvents;
import com.jarvis.core.llm.AssembledLlmContext;
import com.jarvis.core.llm.ContextBuildDiagnostics;
import com.jarvis.core.llm.ContextBuildInput;
import com.jarvis.core.llm.LlmContextBudget;
import com.jarvis.core.llm.LlmContextKind;
import com.jarvis.core.llm.LlmOutputPolicy;
import com.jarvis.core.llm.UnifiedContextBuilder;
import com.jarvis.core.model.AssistantState;
import com.jarvis.core.nlp.AsrPhraseNormalizer;
import com.jarvis.core.nlp.Routine;
import com.jarvis.core.nlp.Routines;
import com.jarvis.core.nlp.TranslationTarget;
import com.jarvis.core.nlp.Translator;
import com.jarvis.core.nlp.Transliterator;
import com.jarvis.core.planner.ActionPlanStatus;
import com.jarvis.core.planner.PlanStep;
import com.jarvis.core.privacy.SensitivityClassifier;
import com.jarvis.core.runtime.ExecutionOrchestrator;
import com.jarvis.core.runtime.ExecutionOrchestratorKt;
import com.jarvis.core.runtime.InteractionLatency;
import com.jarvis.core.runtime.InteractionLatencyKt;
import com.jarvis.core.runtime.LatencyClock;
import com.jarvis.core.runtime.PlanPathCoordinator;
import com.jarvis.core.runtime.RequestTrace;
import com.jarvis.core.runtime.RequestTraceKt;
import com.jarvis.core.runtime.RequestTraceLog;
import com.jarvis.core.runtime.RouteDecision;
import com.jarvis.core.runtime.TtftKind;
import com.jarvis.core.runtime.WarmState;
import com.jarvis.core.runtime.semantic.FastSemanticClassifier;
import com.jarvis.core.runtime.semantic.SemanticClarity;
import com.jarvis.core.runtime.semantic.SemanticInput;
import com.jarvis.core.runtime.semantic.SemanticInterpreter;
import com.jarvis.core.runtime.semantic.SemanticRouteSelector;
import com.jarvis.core.runtime.semantic.SemanticUnderstanding;
import com.jarvis.core.skills.SkillManifest;
import com.jarvis.core.skills.SkillRetriever;
import com.jarvis.core.storage.ConversationJournal;
import com.jarvis.tools.NotificationReplyTool;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.time.DurationKt;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.AbstractCircuitBreaker;

/* JADX INFO: compiled from: AssistantOrchestrator.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000¦\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000  \u00012\u00020\u0001:\u0002 \u0001B\u0083\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016\u0012\u0014\b\u0002\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00190\u0018\u0012\u001a\b\u0002\u0010\u001a\u001a\u0014\u0012\u0004\u0012\u00020\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u001b0\u0018\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001e\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 \u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$\u0012\b\b\u0002\u0010%\u001a\u00020&\u0012\n\b\u0002\u0010'\u001a\u0004\u0018\u00010&\u0012\b\b\u0002\u0010(\u001a\u00020)\u0012\n\b\u0002\u0010*\u001a\u0004\u0018\u00010+\u0012\u000e\b\u0002\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00190-\u0012\u000e\b\u0002\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00190-\u0012\u000e\b\u0002\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00160-\u0012\n\b\u0002\u00100\u001a\u0004\u0018\u000101\u0012\u000e\b\u0002\u00102\u001a\b\u0012\u0004\u0012\u00020\u00160-\u0012\u000e\b\u0002\u00103\u001a\b\u0012\u0004\u0012\u00020\u00160-\u0012\u0014\b\u0002\u00104\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00190\u0018\u0012\u001c\b\u0002\u00105\u001a\u0016\u0012\u0004\u0012\u00020\u0019\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u001b0\u0018¢\u0006\u0004\b6\u00107J^\u0010B\u001a\u0004\u0018\u00010C2\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020G2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00160-26\u0010I\u001a2\u0012\u0013\u0012\u00110\u0019¢\u0006\f\bK\u0012\b\bL\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\u0016¢\u0006\f\bK\u0012\b\bL\u0012\u0004\b\b(N\u0012\u0004\u0012\u00020\u00160JJ\u0006\u0010O\u001a\u00020PJ\u000e\u0010Q\u001a\u00020C2\u0006\u0010D\u001a\u00020EJ\u0010\u0010R\u001a\u00020C2\u0006\u0010S\u001a\u00020CH\u0002J\u0010\u0010T\u001a\u00020C2\u0006\u0010D\u001a\u00020EH\u0002J\"\u0010U\u001a\u0004\u0018\u00010V2\u0006\u0010W\u001a\u00020\u00192\u0006\u0010X\u001a\u00020\u00192\u0006\u0010Y\u001a\u00020:H\u0002J\u0012\u0010Z\u001a\u0004\u0018\u00010V2\u0006\u0010[\u001a\u00020\\H\u0002J\u0010\u0010]\u001a\u00020\u00162\u0006\u0010^\u001a\u00020VH\u0002J \u0010_\u001a\u00020P2\u0006\u0010Y\u001a\u00020:2\u0006\u0010`\u001a\u00020V2\u0006\u0010a\u001a\u00020bH\u0002J\u0010\u0010c\u001a\u00020\u00192\u0006\u0010`\u001a\u00020VH\u0002J6\u0010d\u001a\u0004\u0018\u00010C2\u0006\u0010D\u001a\u00020E2\u0006\u0010e\u001a\u00020\u00192\u0006\u0010f\u001a\u00020G2\b\u0010Y\u001a\u0004\u0018\u00010:2\b\b\u0002\u0010g\u001a\u00020\u0016H\u0002J(\u0010h\u001a\u00020C2\u0006\u0010D\u001a\u00020E2\u0006\u0010`\u001a\u00020V2\u0006\u0010f\u001a\u00020G2\u0006\u0010Y\u001a\u00020:H\u0002J \u0010i\u001a\u00020C2\u0006\u0010S\u001a\u00020C2\u0006\u0010Y\u001a\u00020:2\u0006\u0010f\u001a\u00020GH\u0002J.\u0010j\u001a\u00020C2\u0006\u0010D\u001a\u00020E2\f\u0010k\u001a\b\u0012\u0004\u0012\u00020\u00190\u001b2\u0006\u0010f\u001a\u00020G2\u0006\u0010Y\u001a\u00020:H\u0002J\"\u0010l\u001a\u0004\u0018\u00010C2\u0006\u0010D\u001a\u00020E2\u0006\u0010f\u001a\u00020G2\u0006\u0010Y\u001a\u00020:H\u0002J\u0012\u0010m\u001a\u0004\u0018\u00010n2\u0006\u0010o\u001a\u00020\u0019H\u0002J\u0016\u0010p\u001a\u00020\u00162\f\u0010k\u001a\b\u0012\u0004\u0012\u00020\u00190\u001bH\u0002J<\u0010q\u001a\u00020C2\u0006\u0010r\u001a\u00020E2\u0006\u0010f\u001a\u00020G2\u0006\u0010s\u001a\u00020\u00162\b\b\u0002\u0010t\u001a\u00020\u00162\b\b\u0002\u0010u\u001a\u00020\u00162\u0006\u0010Y\u001a\u00020:H\u0002J\u0018\u0010v\u001a\u00020\u00162\u0006\u0010D\u001a\u00020E2\u0006\u0010N\u001a\u00020wH\u0002JD\u0010x\u001a\u00020C2\u0006\u0010D\u001a\u00020E2\u0006\u0010N\u001a\u00020w2\f\u0010y\u001a\b\u0012\u0004\u0012\u00020z0\u001b2\f\u0010{\u001a\b\u0012\u0004\u0012\u00020\u00190\u001b2\u0006\u0010f\u001a\u00020G2\u0006\u0010Y\u001a\u00020:H\u0002J\u0010\u0010|\u001a\u00020\u00162\u0006\u0010o\u001a\u00020\u0019H\u0002J\f\u0010}\u001a\u00020\u0019*\u00020\u0019H\u0002J)\u0010~\u001a\u00020C2\u0006\u0010D\u001a\u00020E2\u0007\u0010\u007f\u001a\u00030\u0080\u00012\u0006\u0010f\u001a\u00020G2\u0006\u0010Y\u001a\u00020:H\u0002J\u001a\u0010\u0081\u0001\u001a\u00020\u00162\u0006\u0010o\u001a\u00020\u00192\u0007\u0010\u007f\u001a\u00030\u0080\u0001H\u0002J\u0011\u0010\u0082\u0001\u001a\u00020\u00192\u0006\u0010W\u001a\u00020\u0019H\u0002J*\u0010\u0083\u0001\u001a\u00020C2\u0006\u0010D\u001a\u00020E2\u0007\u0010\u0084\u0001\u001a\u00020w2\u0006\u0010f\u001a\u00020G2\u0006\u0010Y\u001a\u00020:H\u0002Jk\u0010\u0085\u0001\u001a\u00020C2\u0006\u0010D\u001a\u00020E2\b\u0010\u0086\u0001\u001a\u00030\u0087\u00012\u0006\u0010f\u001a\u00020G2\f\b\u0002\u0010\u0088\u0001\u001a\u0005\u0018\u00010\u0089\u00012\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010:2\t\b\u0002\u0010\u008a\u0001\u001a\u00020b2\t\b\u0002\u0010\u008b\u0001\u001a\u00020\u00192\t\b\u0002\u0010\u008c\u0001\u001a\u00020\u00162\u000b\b\u0002\u0010\u008d\u0001\u001a\u0004\u0018\u00010\u0019H\u0002J\u0013\u0010\u008e\u0001\u001a\u00020\u00192\b\u0010\u0086\u0001\u001a\u00030\u0087\u0001H\u0002J\"\u0010\u008f\u0001\u001a\u0004\u0018\u00010\u001c2\u0006\u0010o\u001a\u00020\u00192\r\u0010\u0090\u0001\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0002J\u0011\u0010\u0091\u0001\u001a\u00020\u00162\u0006\u0010o\u001a\u00020\u0019H\u0002J\u0011\u0010\u0092\u0001\u001a\u00020\u00162\u0006\u0010o\u001a\u00020\u0019H\u0002J.\u0010\u0093\u0001\u001a\u00020C2\u0006\u0010D\u001a\u00020E2\u0007\u0010\u0094\u0001\u001a\u00020\u00192\u0006\u0010f\u001a\u00020G2\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010:H\u0002J+\u0010\u0095\u0001\u001a\u00020C2\u0006\u0010D\u001a\u00020E2\u0007\u0010\u0094\u0001\u001a\u00020\u00192\u0006\u0010f\u001a\u00020G2\u0007\u0010\u007f\u001a\u00030\u0080\u0001H\u0002J/\u0010\u0096\u0001\u001a\u00020C2\u0006\u0010D\u001a\u00020E2\u0006\u0010f\u001a\u00020G2\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010:2\b\b\u0002\u0010g\u001a\u00020\u0016H\u0002J\u0011\u0010\u0097\u0001\u001a\u00020P2\u0006\u0010o\u001a\u00020\u0019H\u0002J\u0011\u0010\u0098\u0001\u001a\u00020P2\u0006\u0010o\u001a\u00020\u0019H\u0002J\u0011\u0010\u0099\u0001\u001a\u00020\u00162\u0006\u0010D\u001a\u00020EH\u0002JR\u0010\u009a\u0001\u001a\u00020C2\u0006\u0010D\u001a\u00020E2\u0006\u0010o\u001a\u00020\u00192\b\u0010\u009b\u0001\u001a\u00030\u009c\u00012\u0006\u0010f\u001a\u00020G2\f\b\u0002\u0010\u0088\u0001\u001a\u0005\u0018\u00010\u0089\u00012\n\b\u0002\u0010a\u001a\u0004\u0018\u00010\u00192\u000b\b\u0002\u0010\u008b\u0001\u001a\u0004\u0018\u00010\u0019H\u0002J\u0013\u0010\u009d\u0001\u001a\u00020P2\b\u0010\u009e\u0001\u001a\u00030\u009f\u0001H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u001a\u001a\u0014\u0012\u0004\u0012\u00020\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u001b0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010&X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00190-X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00190-X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00160-X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u0004\u0018\u000101X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00102\u001a\b\u0012\u0004\u0012\u00020\u00160-X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00103\u001a\b\u0012\u0004\u0012\u00020\u00160-X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u00104\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u00105\u001a\u0016\u0012\u0004\u0012\u00020\u0019\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u001b0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010;\u001a\u0004\u0018\u00010:2\b\u00109\u001a\u0004\u0018\u00010:@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b<\u0010=R\"\u0010?\u001a\u0004\u0018\u00010>2\b\u00109\u001a\u0004\u0018\u00010>@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b@\u0010A¨\u0006¡\u0001"}, d2 = {"Lcom/jarvis/brain/AssistantOrchestrator;", "", "brainRouter", "Lcom/jarvis/brain/BrainRouter;", "skillRetriever", "Lcom/jarvis/core/skills/SkillRetriever;", "toolRegistry", "Lcom/jarvis/core/common/ToolRegistry;", "toolExecutor", "Lcom/jarvis/core/common/ToolExecutor;", "toolPolicy", "Lcom/jarvis/core/common/ToolPolicy;", "context", "Lcom/jarvis/core/common/model/ConversationContext;", "longTermMemory", "Lcom/jarvis/brain/LongTermMemoryInterface;", "dialogue", "Lcom/jarvis/brain/DialogueResponder;", "executeThreshold", "", "clarifyThreshold", "memoryEnabled", "", "memoryProvider", "Lkotlin/Function1;", "", "resolveContacts", "", "Lcom/jarvis/core/common/model/PendingAction$Choice;", "capabilityDiscovery", "Lcom/jarvis/core/capability/CapabilityDiscovery;", "executionOrchestrator", "Lcom/jarvis/core/runtime/ExecutionOrchestrator;", "planPathCoordinator", "Lcom/jarvis/core/runtime/PlanPathCoordinator;", "catalogToolDiscovery", "Lcom/jarvis/core/discovery/CatalogToolDiscovery;", "fastSemantic", "Lcom/jarvis/core/runtime/semantic/SemanticInterpreter;", "llmSemantic", "unifiedContextBuilder", "Lcom/jarvis/core/llm/UnifiedContextBuilder;", "llmContextAssembly", "Lcom/jarvis/brain/llm/LlmContextAssembly;", "chatPersona", "Lkotlin/Function0;", "chatProfile", "cloudMemoryAllowed", "chatStreamPipeline", "Lcom/jarvis/brain/aiprovider/ChatStreamPipeline;", "isOnlineForAi", "preferCloudForAi", "confirmationStyle", "customRoutine", "<init>", "(Lcom/jarvis/brain/BrainRouter;Lcom/jarvis/core/skills/SkillRetriever;Lcom/jarvis/core/common/ToolRegistry;Lcom/jarvis/core/common/ToolExecutor;Lcom/jarvis/core/common/ToolPolicy;Lcom/jarvis/core/common/model/ConversationContext;Lcom/jarvis/brain/LongTermMemoryInterface;Lcom/jarvis/brain/DialogueResponder;FFZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lcom/jarvis/core/capability/CapabilityDiscovery;Lcom/jarvis/core/runtime/ExecutionOrchestrator;Lcom/jarvis/core/runtime/PlanPathCoordinator;Lcom/jarvis/core/discovery/CatalogToolDiscovery;Lcom/jarvis/core/runtime/semantic/SemanticInterpreter;Lcom/jarvis/core/runtime/semantic/SemanticInterpreter;Lcom/jarvis/core/llm/UnifiedContextBuilder;Lcom/jarvis/brain/llm/LlmContextAssembly;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lcom/jarvis/brain/aiprovider/ChatStreamPipeline;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "execution", "value", "Lcom/jarvis/core/runtime/RequestTrace;", "lastTrace", "getLastTrace", "()Lcom/jarvis/core/runtime/RequestTrace;", "Lcom/jarvis/brain/aiprovider/ChatStreamResult;", "lastChatStreamResult", "getLastChatStreamResult", "()Lcom/jarvis/brain/aiprovider/ChatStreamResult;", "processStreamingChat", "Lcom/jarvis/core/common/model/AssistantResponse;", "request", "Lcom/jarvis/core/common/model/UserRequest;", "generationId", "", "isGenerationStale", "onSentence", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "sentence", "first", "cancelActiveChatStream", "", "process", "styled", "response", "processRequest", "understandSemantic", "Lcom/jarvis/core/runtime/semantic/SemanticUnderstanding;", "raw", "normalized", "trace", "interpretWithLlm", "input", "Lcom/jarvis/core/runtime/semantic/SemanticInput;", "needsLlmSemanticFallback", "fast", "applySemanticToTrace", "semantic", "route", "Lcom/jarvis/core/runtime/RouteDecision;", "buildClarifyFromCandidates", "cloudToolResponse", "reply", "startedAt", "allowFollowUp", "processSemanticPlan", "finalizeTrace", "processCompound", "steps", "translationResponse", "findRoutine", "Lcom/jarvis/core/nlp/Routine;", VoiceService.EXTRA_TEXT, "endsWithAnswer", "processSingle", "normalizedRequest", "preferUiType", "addUser", "allowReact", "shouldReact", "Lcom/jarvis/brain/BrainResult$SkillCall;", "executeWithReact", "topSkills", "Lcom/jarvis/brain/BrainRouter$SkillInfo;", "history", "looksLikeUiTypeOverride", "removePrefixVerb", "handlePending", "pending", "Lcom/jarvis/core/common/model/PendingAction;", "looksLikeFreshCommand", "stripTelegramWriteVerb", "executeSkillCall", "call", "runTool", "toolCall", "Lcom/jarvis/core/common/model/ToolCall;", "intent", "Lcom/jarvis/core/common/model/Intent;", "routeHint", "routeReason", "userConfirmed", "priorFingerprint", "confirmationPrompt", "matchChoice", "choices", "isYes", "isNo", "respondClarify", "question", "respondPending", "respondChat", "rememberReply", "rememberUser", "rememberIfFact", "respond", "status", "Lcom/jarvis/core/common/model/AssistantResponseStatus;", "state", "s", "Lcom/jarvis/core/model/AssistantState;", "Companion", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AssistantOrchestrator {
    private static final Set<String> DATA_TOOLS = SetsKt.setOf((Object[]) new String[]{"weather", "calendar", "system.status", "briefing", "contacts.search", "news", "rates", "location"});
    private static final String DENIED_REPLY = "Этого я выполнить не могу, сэр: действие запрещено правилами безопасности.";
    private static final int REACT_MAX_STEPS = 3;
    private final BrainRouter brainRouter;
    private final CapabilityDiscovery capabilityDiscovery;
    private final CatalogToolDiscovery catalogToolDiscovery;
    private final Function0<String> chatPersona;
    private final Function0<String> chatProfile;
    private final ChatStreamPipeline chatStreamPipeline;
    private final float clarifyThreshold;
    private final Function0<Boolean> cloudMemoryAllowed;
    private final Function1<String, String> confirmationStyle;
    private final ConversationContext context;
    private final Function1<String, List<String>> customRoutine;
    private final DialogueResponder dialogue;
    private final float executeThreshold;
    private final ExecutionOrchestrator execution;
    private final SemanticInterpreter fastSemantic;
    private final Function0<Boolean> isOnlineForAi;
    private volatile ChatStreamResult lastChatStreamResult;
    private volatile RequestTrace lastTrace;
    private final LlmContextAssembly llmContextAssembly;
    private final SemanticInterpreter llmSemantic;
    private final LongTermMemoryInterface longTermMemory;
    private final boolean memoryEnabled;
    private final Function1<String, String> memoryProvider;
    private final PlanPathCoordinator planPathCoordinator;
    private final Function0<Boolean> preferCloudForAi;
    private final Function1<String, List<PendingAction.Choice>> resolveContacts;
    private final SkillRetriever skillRetriever;
    private final ToolExecutor toolExecutor;
    private final ToolPolicy toolPolicy;
    private final ToolRegistry toolRegistry;
    private final UnifiedContextBuilder unifiedContextBuilder;

    /* JADX INFO: compiled from: AssistantOrchestrator.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[RouteDecision.values().length];
            try {
                iArr[RouteDecision.TEMPLATE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[RouteDecision.CHAT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[RouteDecision.CLARIFY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[RouteDecision.PLAN.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[RouteDecision.FAST_SKILL.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[RouteDecision.UNKNOWN.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[PendingAction.Phase.values().length];
            try {
                iArr2[PendingAction.Phase.COLLECT_CHAT.ordinal()] = 1;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr2[PendingAction.Phase.COLLECT_TEXT.ordinal()] = 2;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr2[PendingAction.Phase.DISAMBIGUATE.ordinal()] = 3;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr2[PendingAction.Phase.CONFIRM.ordinal()] = 4;
            } catch (NoSuchFieldError e10) {
            }
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[DialogueResult.Kind.values().length];
            try {
                iArr3[DialogueResult.Kind.TEMPLATE.ordinal()] = 1;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr3[DialogueResult.Kind.FALLBACK_TEMPLATE.ordinal()] = 2;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr3[DialogueResult.Kind.LLM.ordinal()] = 3;
            } catch (NoSuchFieldError e13) {
            }
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AssistantOrchestrator(BrainRouter brainRouter, SkillRetriever skillRetriever, ToolRegistry toolRegistry, ToolExecutor toolExecutor, ToolPolicy toolPolicy, ConversationContext context, LongTermMemoryInterface longTermMemory, DialogueResponder dialogue, float executeThreshold, float clarifyThreshold, boolean memoryEnabled, Function1<? super String, String> memoryProvider, Function1<? super String, ? extends List<PendingAction.Choice>> resolveContacts, CapabilityDiscovery capabilityDiscovery, ExecutionOrchestrator executionOrchestrator, PlanPathCoordinator planPathCoordinator, CatalogToolDiscovery catalogToolDiscovery, SemanticInterpreter fastSemantic, SemanticInterpreter llmSemantic, UnifiedContextBuilder unifiedContextBuilder, LlmContextAssembly llmContextAssembly, Function0<String> chatPersona, Function0<String> chatProfile, Function0<Boolean> cloudMemoryAllowed, ChatStreamPipeline chatStreamPipeline, Function0<Boolean> isOnlineForAi, Function0<Boolean> preferCloudForAi, Function1<? super String, String> confirmationStyle, Function1<? super String, ? extends List<String>> customRoutine) {
        ExecutionOrchestrator executionOrchestrator2;
        Intrinsics.checkNotNullParameter(brainRouter, "brainRouter");
        Intrinsics.checkNotNullParameter(skillRetriever, "skillRetriever");
        Intrinsics.checkNotNullParameter(toolRegistry, "toolRegistry");
        Intrinsics.checkNotNullParameter(toolExecutor, "toolExecutor");
        Intrinsics.checkNotNullParameter(toolPolicy, "toolPolicy");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(longTermMemory, "longTermMemory");
        Intrinsics.checkNotNullParameter(dialogue, "dialogue");
        Intrinsics.checkNotNullParameter(memoryProvider, "memoryProvider");
        Intrinsics.checkNotNullParameter(resolveContacts, "resolveContacts");
        Intrinsics.checkNotNullParameter(fastSemantic, "fastSemantic");
        Intrinsics.checkNotNullParameter(unifiedContextBuilder, "unifiedContextBuilder");
        Intrinsics.checkNotNullParameter(chatPersona, "chatPersona");
        Intrinsics.checkNotNullParameter(chatProfile, "chatProfile");
        Intrinsics.checkNotNullParameter(cloudMemoryAllowed, "cloudMemoryAllowed");
        Intrinsics.checkNotNullParameter(isOnlineForAi, "isOnlineForAi");
        Intrinsics.checkNotNullParameter(preferCloudForAi, "preferCloudForAi");
        Intrinsics.checkNotNullParameter(confirmationStyle, "confirmationStyle");
        Intrinsics.checkNotNullParameter(customRoutine, "customRoutine");
        this.brainRouter = brainRouter;
        this.skillRetriever = skillRetriever;
        this.toolRegistry = toolRegistry;
        this.toolExecutor = toolExecutor;
        this.toolPolicy = toolPolicy;
        this.context = context;
        this.longTermMemory = longTermMemory;
        this.dialogue = dialogue;
        this.executeThreshold = executeThreshold;
        this.clarifyThreshold = clarifyThreshold;
        this.memoryEnabled = memoryEnabled;
        this.memoryProvider = memoryProvider;
        this.resolveContacts = resolveContacts;
        this.capabilityDiscovery = capabilityDiscovery;
        this.planPathCoordinator = planPathCoordinator;
        this.catalogToolDiscovery = catalogToolDiscovery;
        this.fastSemantic = fastSemantic;
        this.llmSemantic = llmSemantic;
        this.unifiedContextBuilder = unifiedContextBuilder;
        this.llmContextAssembly = llmContextAssembly;
        this.chatPersona = chatPersona;
        this.chatProfile = chatProfile;
        this.cloudMemoryAllowed = cloudMemoryAllowed;
        this.chatStreamPipeline = chatStreamPipeline;
        this.isOnlineForAi = isOnlineForAi;
        this.preferCloudForAi = preferCloudForAi;
        this.confirmationStyle = confirmationStyle;
        this.customRoutine = customRoutine;
        if (executionOrchestrator != null) {
            executionOrchestrator2 = executionOrchestrator;
        } else {
            executionOrchestrator2 = new ExecutionOrchestrator(this.toolExecutor, null, null, null, null, null, 62, null);
        }
        this.execution = executionOrchestrator2;
    }

    public /* synthetic */ AssistantOrchestrator(BrainRouter brainRouter, SkillRetriever skillRetriever, ToolRegistry toolRegistry, ToolExecutor toolExecutor, ToolPolicy toolPolicy, ConversationContext conversationContext, LongTermMemoryInterface longTermMemoryInterface, DialogueResponder dialogueResponder, float f, float f2, boolean z, Function1 function1, Function1 function2, CapabilityDiscovery capabilityDiscovery, ExecutionOrchestrator executionOrchestrator, PlanPathCoordinator planPathCoordinator, CatalogToolDiscovery catalogToolDiscovery, SemanticInterpreter semanticInterpreter, SemanticInterpreter semanticInterpreter2, UnifiedContextBuilder unifiedContextBuilder, LlmContextAssembly llmContextAssembly, Function0 function0, Function0 function3, Function0 function4, ChatStreamPipeline chatStreamPipeline, Function0 function5, Function0 function6, Function1 function7, Function1 function8, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(brainRouter, skillRetriever, toolRegistry, toolExecutor, toolPolicy, conversationContext, longTermMemoryInterface, dialogueResponder, (i & 256) != 0 ? 0.75f : f, (i & 512) != 0 ? 0.4f : f2, (i & 1024) != 0 ? true : z, (i & 2048) != 0 ? new Function1() { // from class: com.jarvis.brain.AssistantOrchestrator$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AssistantOrchestrator._init_$lambda$0((String) obj);
            }
        } : function1, (i & 4096) != 0 ? new Function1() { // from class: com.jarvis.brain.AssistantOrchestrator$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AssistantOrchestrator._init_$lambda$1((String) obj);
            }
        } : function2, (i & 8192) != 0 ? null : capabilityDiscovery, (i & 16384) != 0 ? null : executionOrchestrator, (32768 & i) != 0 ? null : planPathCoordinator, (65536 & i) != 0 ? null : catalogToolDiscovery, (131072 & i) != 0 ? new FastSemanticClassifier() : semanticInterpreter, (262144 & i) != 0 ? null : semanticInterpreter2, (524288 & i) != 0 ? new UnifiedContextBuilder() : unifiedContextBuilder, (1048576 & i) != 0 ? null : llmContextAssembly, (2097152 & i) != 0 ? new Function0() { // from class: com.jarvis.brain.AssistantOrchestrator$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AssistantOrchestrator._init_$lambda$2();
            }
        } : function0, (4194304 & i) != 0 ? new Function0() { // from class: com.jarvis.brain.AssistantOrchestrator$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AssistantOrchestrator._init_$lambda$3();
            }
        } : function3, (8388608 & i) != 0 ? new Function0() { // from class: com.jarvis.brain.AssistantOrchestrator$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(AssistantOrchestrator._init_$lambda$4());
            }
        } : function4, (16777216 & i) != 0 ? null : chatStreamPipeline, (33554432 & i) != 0 ? new Function0() { // from class: com.jarvis.brain.AssistantOrchestrator$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(AssistantOrchestrator._init_$lambda$5());
            }
        } : function5, (67108864 & i) != 0 ? new Function0() { // from class: com.jarvis.brain.AssistantOrchestrator$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(AssistantOrchestrator._init_$lambda$6());
            }
        } : function6, (134217728 & i) != 0 ? new Function1() { // from class: com.jarvis.brain.AssistantOrchestrator$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AssistantOrchestrator._init_$lambda$7((String) obj);
            }
        } : function7, (i & 268435456) != 0 ? new Function1() { // from class: com.jarvis.brain.AssistantOrchestrator.9
            @Override // kotlin.jvm.functions.Function1
            public final Void invoke(String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return null;
            }
        } : function8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String _init_$lambda$0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List _init_$lambda$1(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return CollectionsKt.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String _init_$lambda$2() {
        return "CLASSIC";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String _init_$lambda$3() {
        return "BALANCED";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean _init_$lambda$4() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean _init_$lambda$5() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean _init_$lambda$6() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String _init_$lambda$7(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it;
    }

    public final RequestTrace getLastTrace() {
        return this.lastTrace;
    }

    public final ChatStreamResult getLastChatStreamResult() {
        return this.lastChatStreamResult;
    }

    public final AssistantResponse processStreamingChat(UserRequest request, long generationId, Function0<Boolean> isGenerationStale, final Function2<? super String, ? super Boolean, Boolean> onSentence) {
        SemanticUnderstanding semantic;
        AssembledLlmContext assembled;
        List<String> messages;
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(isGenerationStale, "isGenerationStale");
        Intrinsics.checkNotNullParameter(onSentence, "onSentence");
        ChatStreamPipeline pipeline = this.chatStreamPipeline;
        if (pipeline != null && this.isOnlineForAi.invoke().booleanValue() && this.preferCloudForAi.invoke().booleanValue()) {
            long startedAt = System.nanoTime();
            long wallStart = LatencyClock.INSTANCE.elapsedMs();
            InteractionLatency latency = new InteractionLatency(request.getRequestId(), wallStart);
            RequestTrace trace = new RequestTrace(request.getRequestId(), request.getTimestampMillis(), request.getSource().name(), StringsKt.take(request.getText(), 80), null, null, null, null, false, null, null, false, false, null, null, 0L, null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, latency, null, -16, -1, -1, 2, null);
            this.lastTrace = trace;
            latency.setNormalizeStartAt(Long.valueOf(wallStart));
            String normalizedText = AsrPhraseNormalizer.INSTANCE.normalize(request.getText());
            latency.setNormalizeEndAt(Long.valueOf(LatencyClock.INSTANCE.elapsedMs()));
            UserRequest normalizedRequest = Intrinsics.areEqual(normalizedText, request.getText()) ? request : UserRequest.copy$default(request, normalizedText, null, null, 0L, null, 30, null);
            if (this.context.getPendingAction() == null && findRoutine(normalizedRequest.getText()) == null && Translator.INSTANCE.getActive() == null && Translator.INSTANCE.parse(normalizedRequest.getText()) == null) {
                ToolCall earlyFollowUp = FollowUpResolver.INSTANCE.resolve(normalizedRequest.getText(), this.context.getLastToolCall());
                if (earlyFollowUp != null || (semantic = understandSemantic(request.getText(), normalizedRequest.getText(), trace)) == null) {
                    return null;
                }
                RouteDecision route = SemanticRouteSelector.INSTANCE.select(semantic);
                applySemanticToTrace(trace, semantic, route);
                if (route != RouteDecision.CHAT) {
                    return null;
                }
                rememberUser(normalizedRequest.getText());
                state(AssistantState.THINKING);
                if (this.memoryEnabled && this.llmContextAssembly != null) {
                    latency.setContextStartAt(Long.valueOf(LatencyClock.INSTANCE.elapsedMs()));
                    ContextBuildInput input = this.llmContextAssembly.gatherInput(normalizedRequest.getText(), this.context.getMessages(), this.chatPersona.invoke(), (24 & 8) != 0 ? LlmContextKind.CHAT : null, (24 & 16) != 0 ? LlmContextBudget.BALANCED : null, (24 & 32) != 0 ? true : this.cloudMemoryAllowed.invoke().booleanValue(), (24 & 64) != 0 ? "BALANCED" : this.chatProfile.invoke(), (24 & 128) != 0 ? null : this.context);
                    assembled = this.unifiedContextBuilder.build(input);
                    latency.setContextEndAt(Long.valueOf(LatencyClock.INSTANCE.elapsedMs()));
                } else {
                    assembled = null;
                }
                latency.setLlmRequestStartAt(Long.valueOf(LatencyClock.INSTANCE.elapsedMs()));
                AiTaskProfile profile = AiTaskProfile.INSTANCE.forChat(true, true, AIRequestComplexity.SIMPLE, (8 & 8) != 0 ? PrivacyLevel.NORMAL : null, (8 & 16) != 0 ? "" : request.getRequestId(), (8 & 32) != 0 ? 0 : normalizedRequest.getText().length());
                String requestId = request.getRequestId();
                String text = normalizedRequest.getText();
                if (assembled == null || (messages = assembled.getHistoryLines()) == null) {
                    messages = this.context.getMessages();
                }
                List<String> list = messages;
                String legacyMemoriesSnippet = assembled != null ? assembled.getLegacyMemoriesSnippet() : null;
                if (legacyMemoriesSnippet == null) {
                    legacyMemoriesSnippet = "";
                }
                AICompletionRequest aiReq = new AICompletionRequest(requestId, text, list, legacyMemoriesSnippet, this.chatPersona.invoke(), false, false, null, null, assembled, 480, null);
                ChatStreamResult streamResult = pipeline.streamChat(profile, aiReq, generationId, isGenerationStale, new ChatSentenceSink() { // from class: com.jarvis.brain.AssistantOrchestrator$$ExternalSyntheticLambda0
                    @Override // com.jarvis.brain.aiprovider.ChatSentenceSink
                    public final boolean onSentence(String str, boolean z) {
                        return AssistantOrchestrator.processStreamingChat$lambda$0(onSentence, str, z);
                    }
                });
                this.lastChatStreamResult = streamResult;
                latency.setLlmCompleteAt(Long.valueOf(LatencyClock.INSTANCE.elapsedMs()));
                latency.setStreamingUsed(streamResult.getStreamed());
                latency.setTtftKind(streamResult.getTtftKind());
                if (streamResult.getTtftKind() == TtftKind.REAL && streamResult.getTtftMs() != null) {
                    Long llmRequestStartAt = latency.getLlmRequestStartAt();
                    Intrinsics.checkNotNull(llmRequestStartAt);
                    latency.setLlmFirstTokenAt(Long.valueOf(llmRequestStartAt.longValue() + streamResult.getTtftMs().longValue()));
                    latency.setLlmFirstByteAt(latency.getLlmFirstTokenAt());
                }
                latency.setFallback(streamResult.getFallbackUsed());
                if (streamResult.getCancelled() || isGenerationStale.invoke().booleanValue()) {
                    RequestTrace trace2 = trace;
                    latency.setCancelled(true);
                    String it = streamResult.getFullText();
                    String it2 = !StringsKt.isBlank(it) ? it : null;
                    String str = "…";
                    if (it2 != null) {
                        String it3 = it2 + "…";
                        if (it3 != null) {
                            str = it3;
                        }
                    }
                    rememberReply(str);
                    AssistantResponse assistantResponseFinalizeTrace = finalizeTrace(new AssistantResponse(request.getRequestId(), streamResult.getFullText(), AssistantResponseStatus.PROCESSED, null, null, (System.nanoTime() - startedAt) / ((long) 1000000), "CHAT", "stream_cancelled", streamResult.getProviderId(), streamResult.getModelId(), streamResult.getFallbackUsed(), null, 2072, null), trace2, startedAt);
                    trace2.setStreamingUsed(Boolean.valueOf(streamResult.getStreamed()));
                    trace2.setStreamRequested(true);
                    trace2.setStreamSupported(true);
                    trace2.setStreamFallback(Boolean.valueOf(streamResult.getFallbackUsed()));
                    trace2.setStreamFallbackReason(streamResult.getFallbackReason());
                    trace2.setCancelled(true);
                    trace2.setAiTtftKind(streamResult.getTtftKind().name());
                    trace2.setAiTtftMs(streamResult.getTtftMs());
                    trace2.setAiCompletionMs(Long.valueOf(streamResult.getLatencyMs()));
                    return assistantResponseFinalizeTrace;
                }
                AssistantResponse it4 = cloudToolResponse$default(this, normalizedRequest, streamResult.getFullText(), startedAt, trace, false, 16, null);
                if (it4 != null) {
                    return finalizeTrace(styled(it4), trace, startedAt);
                }
                if (streamResult.getFailed() && StringsKt.isBlank(streamResult.getFullText())) {
                    return finalizeTrace(respondChat$default(this, normalizedRequest, startedAt, trace, false, 8, null), trace, startedAt);
                }
                String fullText = streamResult.getFullText();
                if (StringsKt.isBlank(fullText)) {
                    fullText = "Готово.";
                }
                String text2 = fullText;
                String policyText = LlmOutputPolicy.INSTANCE.apply(text2, LlmContextBudget.BALANCED);
                boolean factRefused = rememberIfFact(normalizedRequest);
                String finalText = factRefused ? FactExtractor.REFUSAL : policyText;
                rememberReply(finalText);
                state(AssistantState.READY);
                trace.setRoute(RouteDecision.CHAT);
                trace.setRouteReason(streamResult.getStreamed() ? "chat_stream" : "chat_stream_fallback");
                trace.setProvider(streamResult.getProviderId());
                trace.setModel(streamResult.getModelId());
                trace.setFallback(streamResult.getFallbackUsed());
                trace.setStreamingUsed(Boolean.valueOf(streamResult.getStreamed()));
                trace.setStreamRequested(true);
                trace.setStreamSupported(true);
                trace.setStreamFallback(Boolean.valueOf(streamResult.getFallbackUsed()));
                trace.setStreamFallbackReason(streamResult.getFallbackReason());
                trace.setAiTtftKind(streamResult.getTtftKind().name());
                trace.setAiTtftMs(streamResult.getTtftMs());
                trace.setAiCompletionMs(Long.valueOf(streamResult.getLatencyMs()));
                trace.setAiLatencyMs(Long.valueOf(streamResult.getLatencyMs()));
                latency.setFirstUsefulAt(Long.valueOf(LatencyClock.INSTANCE.elapsedMs()));
                return finalizeTrace(new AssistantResponse(request.getRequestId(), finalText, AssistantResponseStatus.PROCESSED, null, null, (System.nanoTime() - startedAt) / ((long) DurationKt.NANOS_IN_MILLIS), "CHAT", trace.getRouteReason(), streamResult.getProviderId(), streamResult.getModelId(), streamResult.getFallbackUsed(), null, 2072, null), trace, startedAt);
            }
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean processStreamingChat$lambda$0(Function2 $onSentence, String sentence, boolean first) {
        Intrinsics.checkNotNullParameter(sentence, "sentence");
        return ((Boolean) $onSentence.invoke(sentence, Boolean.valueOf(first))).booleanValue();
    }

    public final void cancelActiveChatStream() {
        ChatStreamPipeline chatStreamPipeline = this.chatStreamPipeline;
        if (chatStreamPipeline != null) {
            chatStreamPipeline.cancelActive();
        }
    }

    public final AssistantResponse process(UserRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        return styled(processRequest(request));
    }

    private final AssistantResponse styled(AssistantResponse response) {
        ToolResult toolResult = response.getToolResult();
        boolean executed = (toolResult != null ? toolResult.getStatus() : null) == ToolResultStatus.SUCCESS;
        return (executed && response.getStatus() == AssistantResponseStatus.PROCESSED) ? response.copy((31 & 1) != 0 ? response.requestId : null, (31 & 2) != 0 ? response.replyText : this.confirmationStyle.invoke(response.getReplyText()), (31 & 4) != 0 ? response.status : null, (31 & 8) != 0 ? response.intent : null, (31 & 16) != 0 ? response.toolResult : null, (31 & 32) != 0 ? response.durationMs : 0L, (31 & 64) != 0 ? response.route : null, (31 & 128) != 0 ? response.routeReason : null, (31 & 256) != 0 ? response.provider : null, (31 & 512) != 0 ? response.model : null, (31 & 1024) != 0 ? response.fallbackUsed : false, (31 & 2048) != 0 ? response.verification : null) : response;
    }

    /* JADX WARN: Code duplicated, block: B:35:0x01ef  */
    private final AssistantResponse processRequest(UserRequest request) throws InterruptedException {
        boolean z;
        AssistantResponse done;
        long startedAt = System.nanoTime();
        long wallStart = LatencyClock.INSTANCE.elapsedMs();
        InteractionLatency latency = new InteractionLatency(request.getRequestId(), wallStart);
        RequestTrace trace = new RequestTrace(request.getRequestId(), request.getTimestampMillis(), request.getSource().name(), StringsKt.take(request.getText(), 80), null, null, null, null, false, null, null, false, false, null, null, 0L, null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, latency, null, -16, -1, -1, 2, null);
        this.lastTrace = trace;
        latency.setNormalizeStartAt(Long.valueOf(wallStart));
        String normalizedText = AsrPhraseNormalizer.INSTANCE.normalize(request.getText());
        latency.setNormalizeEndAt(Long.valueOf(LatencyClock.INSTANCE.elapsedMs()));
        UserRequest normalizedRequest = Intrinsics.areEqual(normalizedText, request.getText()) ? request : UserRequest.copy$default(request, normalizedText, null, null, 0L, null, 30, null);
        PendingAction pending = this.context.getPendingAction();
        if (pending != null) {
            String lowerCase = normalizedRequest.getText().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            String text = StringsKt.trim((CharSequence) lowerCase).toString();
            boolean fresh = (isYes(text) || isNo(text) || !looksLikeFreshCommand(text, pending)) ? false : true;
            if (!fresh) {
                rememberUser(normalizedRequest.getText());
                AssistantResponse resp = handlePending(normalizedRequest, pending, startedAt, trace);
                return finalizeTrace(resp, trace, startedAt);
            }
            this.context.clearPending();
        }
        rememberUser(normalizedRequest.getText());
        state(AssistantState.THINKING);
        AssistantResponse it = translationResponse(normalizedRequest, startedAt, trace);
        if (it != null) {
            return finalizeTrace(it, trace, startedAt);
        }
        Routine routine = findRoutine(normalizedRequest.getText());
        if (routine != null) {
            AssistantResponse done2 = processCompound(normalizedRequest, routine.getSteps(), startedAt, trace);
            if (done2.getStatus() != AssistantResponseStatus.PROCESSED) {
                z = false;
            } else if (routine.getClosing().length() > 0) {
                z = true;
            } else {
                z = false;
            }
            boolean fine = z;
            if (!fine) {
                return finalizeTrace(done2, trace, startedAt);
            }
            String step = routine.getReport();
            String replyText = null;
            if (step != null) {
                done = done2;
                AssistantResponse it2 = processSingle(UserRequest.copy$default(normalizedRequest, step, null, null, 0L, null, 30, null), startedAt, false, false, false, trace);
                if (!(it2.getStatus() == AssistantResponseStatus.PROCESSED)) {
                    it2 = null;
                }
                if (it2 != null) {
                    replyText = it2.getReplyText();
                }
            } else {
                done = done2;
            }
            String report = replyText;
            String reply = CollectionsKt.joinToString$default(CollectionsKt.listOfNotNull((Object[]) new String[]{report, routine.getClosing()}), StringUtils.SPACE, null, null, 0, null, null, 62, null);
            AssistantResponse assistantResponse = done;
            return finalizeTrace(assistantResponse.copy((31 & 1) != 0 ? assistantResponse.requestId : null, (31 & 2) != 0 ? assistantResponse.replyText : reply, (31 & 4) != 0 ? assistantResponse.status : null, (31 & 8) != 0 ? assistantResponse.intent : null, (31 & 16) != 0 ? assistantResponse.toolResult : null, (31 & 32) != 0 ? assistantResponse.durationMs : 0L, (31 & 64) != 0 ? assistantResponse.route : null, (31 & 128) != 0 ? assistantResponse.routeReason : null, (31 & 256) != 0 ? assistantResponse.provider : null, (31 & 512) != 0 ? assistantResponse.model : null, (31 & 1024) != 0 ? assistantResponse.fallbackUsed : false, (31 & 2048) != 0 ? assistantResponse.verification : null), trace, startedAt);
        }
        ToolCall earlyFollowUp = FollowUpResolver.INSTANCE.resolve(normalizedRequest.getText(), this.context.getLastToolCall());
        if (earlyFollowUp != null) {
            return finalizeTrace(runTool$default(this, normalizedRequest, earlyFollowUp, startedAt, null, trace, RouteDecision.FAST_SKILL, "follow_up", false, null, 392, null), trace, startedAt);
        }
        SemanticUnderstanding semantic = understandSemantic(request.getText(), normalizedRequest.getText(), trace);
        if (semantic != null) {
            RouteDecision route = SemanticRouteSelector.INSTANCE.select(semantic);
            applySemanticToTrace(trace, semantic, route);
            switch (WhenMappings.$EnumSwitchMapping$0[route.ordinal()]) {
                case 1:
                    return finalizeTrace(respondChat$default(this, normalizedRequest, startedAt, trace, false, 8, null), trace, startedAt);
                case 2:
                    return finalizeTrace(respondChat$default(this, normalizedRequest, startedAt, trace, false, 8, null), trace, startedAt);
                case 3:
                    String clarifyQuestion = semantic.getClarifyQuestion();
                    return finalizeTrace(respondClarify(normalizedRequest, clarifyQuestion == null ? buildClarifyFromCandidates(semantic) : clarifyQuestion, startedAt, trace), trace, startedAt);
                case 4:
                    return finalizeTrace(processSemanticPlan(normalizedRequest, semantic, startedAt, trace), trace, startedAt);
                case 5:
                    String rewritten = semantic.getRewrittenCommand();
                    boolean isChain = CompoundCommandSplitter.INSTANCE.isCompound(StringsKt.trim((CharSequence) request.getText()).toString());
                    if (!isChain) {
                        String str = rewritten;
                        if (!(str == null || StringsKt.isBlank(str)) && !Intrinsics.areEqual(rewritten, normalizedRequest.getText())) {
                            UserRequest rewrittenReq = UserRequest.copy$default(normalizedRequest, rewritten, null, null, 0L, null, 30, null);
                            return finalizeTrace(processSingle(rewrittenReq, startedAt, false, false, true, trace), trace, startedAt);
                        }
                    }
                    Unit unit = Unit.INSTANCE;
                    break;
                case 6:
                    Unit unit2 = Unit.INSTANCE;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
        if (!StringsKt.isBlank(normalizedRequest.getText()) && AsrPhraseNormalizer.INSTANCE.isIncompleteCommand(normalizedRequest.getText())) {
            return finalizeTrace(respondClarify(normalizedRequest, "Что именно сделать, сэр? Скажите полностью, например: включи свет.", startedAt, trace), trace, startedAt);
        }
        Iterable $this$map$iv = CompoundCommandSplitter.INSTANCE.split(StringsKt.trim((CharSequence) request.getText()).toString());
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            destination$iv$iv.add(StringsKt.trim((CharSequence) AsrPhraseNormalizer.INSTANCE.normalize((String) item$iv$iv)).toString());
        }
        Iterable $this$filter$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            if (((String) element$iv$iv).length() > 0) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        List steps = (List) destination$iv$iv2;
        return steps.size() > 1 ? finalizeTrace(processCompound(normalizedRequest, steps, startedAt, trace), trace, startedAt) : finalizeTrace(processSingle$default(this, normalizedRequest, startedAt, false, false, false, trace, 24, null), trace, startedAt);
    }

    /* JADX WARN: Code duplicated, block: B:34:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:35:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:45:0x013a  */
    /* JADX WARN: Code duplicated, block: B:47:0x013d  */
    /* JADX WARN: Code duplicated, block: B:48:0x0143  */
    /* JADX WARN: Code duplicated, block: B:51:0x014c  */
    /* JADX WARN: Code duplicated, block: B:52:0x014e  */
    /* JADX WARN: Code duplicated, block: B:58:0x0164  */
    /* JADX WARN: Code duplicated, block: B:61:0x017f  */
    /* JADX WARN: Code duplicated, block: B:64:0x0186  */
    /* JADX WARN: Code duplicated, block: B:66:0x0190  */
    /* JADX WARN: Code duplicated, block: B:67:0x0192  */
    /* JADX WARN: Code duplicated, block: B:70:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:72:0x01ac  */
    private final SemanticUnderstanding understandSemantic(String raw, String normalized, RequestTrace trace) {
        Object element$iv;
        String str;
        String prevAsst;
        ToolCall lastToolCall;
        String toolId;
        ToolCall lastToolCall2;
        Map map;
        Map mapEmptyMap;
        boolean z;
        PendingAction pendingAction;
        String strName;
        SemanticInput input;
        SemanticUnderstanding fast;
        SemanticUnderstanding result;
        long tSem1;
        PendingAction.Phase phase;
        Map<String, Object> arguments;
        String it;
        InteractionLatency lat = trace.getInteractionLatency();
        long tSem0 = LatencyClock.INSTANCE.elapsedMs();
        if (lat != null) {
            lat.setSemanticStartAt(Long.valueOf(tSem0));
        }
        if (lat != null) {
            lat.setRouteStartAt(Long.valueOf(tSem0));
        }
        Iterable<String> $this$mapNotNull$iv = this.context.getMessages();
        Collection destination$iv$iv = new ArrayList();
        for (String line : $this$mapNotNull$iv) {
            long tSem2 = tSem0;
            String string = StringsKt.startsWith(line, "user:", true) ? StringsKt.trim((CharSequence) StringsKt.substringAfter$default(line, ':', (String) null, 2, (Object) null)).toString() : null;
            if (string != null) {
                destination$iv$iv.add(string);
            }
            tSem0 = tSem2;
        }
        String prevUser = (String) CollectionsKt.lastOrNull(CollectionsKt.dropLast((List) destination$iv$iv, 1));
        List<String> messages = this.context.getMessages();
        ListIterator<String> listIterator = messages.listIterator(messages.size());
        do {
            if (!listIterator.hasPrevious()) {
                element$iv = null;
                break;
            }
            element$iv = listIterator.previous();
            it = (String) element$iv;
        } while (!StringsKt.startsWith(it, "assistant:", true));
        String str2 = (String) element$iv;
        if (str2 != null) {
            str = null;
            String strSubstringAfter$default = StringsKt.substringAfter$default(str2, ':', (String) null, 2, (Object) null);
            if (strSubstringAfter$default != null) {
                prevAsst = StringsKt.trim((CharSequence) strSubstringAfter$default).toString();
            }
            lastToolCall = this.context.getLastToolCall();
            if (lastToolCall != null) {
                toolId = lastToolCall.getToolId();
            } else {
                toolId = str;
            }
            lastToolCall2 = this.context.getLastToolCall();
            if (lastToolCall2 != null || (arguments = lastToolCall2.getArguments()) == null) {
                map = null;
            } else {
                Map destination$iv$iv2 = new LinkedHashMap(MapsKt.mapCapacity(arguments.size()));
                Iterable $this$associateByTo$iv$iv$iv = arguments.entrySet();
                for (Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
                    Map.Entry it$iv$iv = (Map.Entry) element$iv$iv$iv;
                    Object key = it$iv$iv.getKey();
                    Map.Entry it2 = (Map.Entry) element$iv$iv$iv;
                    destination$iv$iv2.put(key, it2.getValue().toString());
                }
                map = destination$iv$iv2;
            }
            if (map == null) {
                mapEmptyMap = MapsKt.emptyMap();
            } else {
                mapEmptyMap = map;
            }
            if (this.context.getPendingAction() != null) {
                z = true;
            } else {
                z = false;
            }
            pendingAction = this.context.getPendingAction();
            if (pendingAction != null || (phase = pendingAction.getPhase()) == null) {
                strName = null;
            } else {
                strName = phase.name();
            }
            input = new SemanticInput(raw, normalized, prevUser, prevAsst, toolId, mapEmptyMap, strName, z);
            fast = this.fastSemantic.interpret(input);
            if (fast != null) {
                trace.setLlmRequired(true);
                result = interpretWithLlm(input);
            } else if (needsLlmSemanticFallback(fast)) {
                trace.setLlmRequired(true);
                result = interpretWithLlm(input);
                if (result == null) {
                    result = fast;
                }
            } else {
                result = fast;
            }
            tSem1 = LatencyClock.INSTANCE.elapsedMs();
            if (lat != null) {
                lat.setSemanticEndAt(Long.valueOf(tSem1));
            }
            if (lat != null) {
                lat.setRouteEndAt(Long.valueOf(tSem1));
            }
            return result;
        }
        str = null;
        prevAsst = str;
        lastToolCall = this.context.getLastToolCall();
        if (lastToolCall != null) {
            toolId = lastToolCall.getToolId();
        } else {
            toolId = str;
        }
        lastToolCall2 = this.context.getLastToolCall();
        if (lastToolCall2 != null) {
            map = null;
        } else {
            map = null;
        }
        if (map == null) {
            mapEmptyMap = MapsKt.emptyMap();
        } else {
            mapEmptyMap = map;
        }
        if (this.context.getPendingAction() != null) {
            z = true;
        } else {
            z = false;
        }
        pendingAction = this.context.getPendingAction();
        if (pendingAction != null) {
            strName = null;
        } else {
            strName = null;
        }
        input = new SemanticInput(raw, normalized, prevUser, prevAsst, toolId, mapEmptyMap, strName, z);
        fast = this.fastSemantic.interpret(input);
        if (fast != null) {
            trace.setLlmRequired(true);
            result = interpretWithLlm(input);
        } else if (needsLlmSemanticFallback(fast)) {
            result = fast;
        } else {
            trace.setLlmRequired(true);
            result = interpretWithLlm(input);
            if (result == null) {
                result = fast;
            }
        }
        tSem1 = LatencyClock.INSTANCE.elapsedMs();
        if (lat != null) {
            lat.setSemanticEndAt(Long.valueOf(tSem1));
        }
        if (lat != null) {
            lat.setRouteEndAt(Long.valueOf(tSem1));
        }
        return result;
    }

    private final SemanticUnderstanding interpretWithLlm(SemanticInput input) {
        SemanticInterpreter semanticInterpreter;
        if ((this.isOnlineForAi.invoke().booleanValue() && this.preferCloudForAi.invoke().booleanValue()) || (semanticInterpreter = this.llmSemantic) == null) {
            return null;
        }
        return semanticInterpreter.interpret(input);
    }

    private final boolean needsLlmSemanticFallback(SemanticUnderstanding fast) {
        switch (WhenMappings.$EnumSwitchMapping$0[fast.getSuggestedRoute().ordinal()]) {
            case 1:
            case 3:
            case 4:
            case 5:
                return false;
            case 2:
                return false;
            case 6:
                return fast.getClarity() == SemanticClarity.UNCLEAR || fast.getConfidence() < 0.45f;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final void applySemanticToTrace(RequestTrace trace, SemanticUnderstanding semantic, RouteDecision route) {
        boolean z;
        Object element$iv;
        Object element$iv2;
        String it;
        String it2;
        StructuredAiDiagnostics diag;
        trace.setRawInput(StringsKt.take(semantic.getRawInput(), 120));
        trace.setNormalizedInput(StringsKt.take(semantic.getNormalizedInput(), 120));
        trace.setSemanticGoal(semantic.getGoal());
        trace.setSemanticIntent(semantic.getIntent());
        trace.setSemanticConfidence(Float.valueOf(semantic.getConfidence()));
        trace.setAmbiguity(semantic.getAmbiguity().name());
        trace.setLlmInterpretationUsed(semantic.getLlmInterpretationUsed());
        trace.setRoute(route);
        trace.setRouteReason(semantic.getRouteReason());
        SemanticInterpreter semanticInterpreter = this.llmSemantic;
        LlmStructuredSemanticInterpreter llmStructuredSemanticInterpreter = semanticInterpreter instanceof LlmStructuredSemanticInterpreter ? (LlmStructuredSemanticInterpreter) semanticInterpreter : null;
        if (llmStructuredSemanticInterpreter != null && (diag = llmStructuredSemanticInterpreter.getLastStructuredDiagnostics()) != null) {
            RequestTraceKt.applyStructured(trace, diag);
            return;
        }
        Iterable $this$any$iv = semantic.getNotes();
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            Iterator it3 = $this$any$iv.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    z = false;
                    break;
                }
                Object element$iv3 = it3.next();
                String it4 = (String) element$iv3;
                if (StringsKt.startsWith$default(it4, "structured_type=", false, 2, (Object) null)) {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
        }
        if (z) {
            trace.setStructured(true);
            Iterable $this$firstOrNull$iv = semantic.getNotes();
            Iterator it5 = $this$firstOrNull$iv.iterator();
            do {
                if (!it5.hasNext()) {
                    element$iv = null;
                    break;
                } else {
                    element$iv = it5.next();
                    it2 = (String) element$iv;
                }
            } while (!StringsKt.startsWith$default(it2, "schema=", false, 2, (Object) null));
            String str = (String) element$iv;
            trace.setStructuredSchemaVersion(str != null ? StringsKt.substringAfter$default(str, '=', (String) null, 2, (Object) null) : null);
            Iterable $this$firstOrNull$iv2 = semantic.getNotes();
            Iterator it6 = $this$firstOrNull$iv2.iterator();
            do {
                if (!it6.hasNext()) {
                    element$iv2 = null;
                    break;
                } else {
                    element$iv2 = it6.next();
                    it = (String) element$iv2;
                }
            } while (!StringsKt.startsWith$default(it, "structured_type=", false, 2, (Object) null));
            String str2 = (String) element$iv2;
            trace.setStructuredResponseType(str2 != null ? StringsKt.substringAfter$default(str2, '=', (String) null, 2, (Object) null) : null);
            trace.setStructuredParse("SUCCESS");
            trace.setStructuredValidation("SUCCESS");
        }
    }

    private final String buildClarifyFromCandidates(SemanticUnderstanding semantic) {
        List<String> candidateInterpretations = semantic.getCandidateInterpretations();
        if (candidateInterpretations.size() >= 2) {
            return "Ты имеешь в виду «" + ((Object) candidateInterpretations.get(0)) + "» или «" + ((Object) candidateInterpretations.get(1)) + "»?";
        }
        return "Что именно сделать, сэр?";
    }

    static /* synthetic */ AssistantResponse cloudToolResponse$default(AssistantOrchestrator assistantOrchestrator, UserRequest userRequest, String str, long j, RequestTrace requestTrace, boolean z, int i, Object obj) {
        boolean z2;
        if ((i & 16) == 0) {
            z2 = z;
        } else {
            z2 = true;
        }
        return assistantOrchestrator.cloudToolResponse(userRequest, str, j, requestTrace, z2);
    }

    private final AssistantResponse cloudToolResponse(UserRequest request, String reply, long startedAt, RequestTrace trace, boolean allowFollowUp) {
        String data = null;
        if (!CloudToolAction.INSTANCE.isAction(reply)) {
            return null;
        }
        ToolCall call = CloudToolAction.INSTANCE.parse(reply, request.getRequestId());
        if (call == null) {
            return respond$default(this, request, "Не разобрал команду, сэр. Скажите иначе.", AssistantResponseStatus.FAILED, startedAt, null, "CHAT", "cloud_tool_invalid", 16, null);
        }
        AssistantResponse done = runTool$default(this, request, call, startedAt, null, trace, null, "cloud_tool", false, null, TypedValues.CycleType.TYPE_WAVE_OFFSET, null);
        ToolResult it = done.getToolResult();
        boolean z = true;
        if (it != null) {
            if (!(it.getStatus() == ToolResultStatus.SUCCESS)) {
                it = null;
            }
            if (it != null) {
                data = it.getMessage();
            }
        }
        if (allowFollowUp && DATA_TOOLS.contains(call.getToolId())) {
            String str = data;
            if (str != null && !StringsKt.isBlank(str)) {
                z = false;
            }
            if (!z) {
                UserRequest followUp = UserRequest.copy$default(request, request.getText() + "\n\nДанные: " + data + "\nОтветь сэру по этим данным коротко, без вызова инструментов.", null, null, 0L, null, 30, null);
                AssistantResponse reply2 = respondChat(followUp, startedAt, trace, false);
                return CloudToolAction.INSTANCE.isAction(reply2.getReplyText()) ? done : reply2;
            }
        }
        return done;
    }

    private final AssistantResponse processSemanticPlan(UserRequest request, SemanticUnderstanding semantic, long startedAt, RequestTrace trace) {
        trace.setRoute(RouteDecision.PLAN);
        trace.setRouteReason(semantic.getRouteReason());
        trace.setPlanner(this.planPathCoordinator != null);
        Iterable $this$map$iv = CompoundCommandSplitter.INSTANCE.split(StringsKt.trim((CharSequence) request.getText()).toString());
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            destination$iv$iv.add(StringsKt.trim((CharSequence) AsrPhraseNormalizer.INSTANCE.normalize(it)).toString());
        }
        Iterable $this$filter$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it2 = (String) element$iv$iv;
            if (it2.length() > 0) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        List commandSteps = (List) destination$iv$iv2;
        if (commandSteps.size() > 1) {
            return processCompound(request, commandSteps, startedAt, trace);
        }
        if (this.isOnlineForAi.invoke().booleanValue() && this.preferCloudForAi.invoke().booleanValue()) {
            return respondChat$default(this, request, startedAt, trace, false, 8, null);
        }
        if (this.planPathCoordinator != null && this.catalogToolDiscovery != null) {
            CatalogToolDiscovery catalogToolDiscovery = this.catalogToolDiscovery;
            Intrinsics.checkNotNull(catalogToolDiscovery);
            DiscoveryResult discovery = catalogToolDiscovery.discover(request.getText(), 8);
            PlanPathCoordinator planPathCoordinator = this.planPathCoordinator;
            Intrinsics.checkNotNull(planPathCoordinator);
            PlanPathCoordinator.PlanRun planRun = planPathCoordinator.planAndRun(request.getText(), discovery, request.getRequestId(), (40 & 8) != 0 ? System.currentTimeMillis() : 0L, (40 & 16) != 0 ? false : false, (40 & 32) != 0 ? 6 : 0);
            trace.setAgent(true);
            String strMask = SensitivityClassifier.INSTANCE.mask(semantic.getGoal());
            ActionPlanStatus status = planRun.getPlan().getStatus();
            int size = planRun.getPlan().getSteps().size();
            AgentLoopResult loopResult = planRun.getLoopResult();
            Log.i("Jarvis.PlanPath", "semantic PLAN goal=" + strMask + " status=" + status + " steps=" + size + " completed=" + (loopResult != null ? Boolean.valueOf(loopResult.getCompleted()) : null) + " dry=" + planRun.getDryRun());
            StringBuilder $this$processSemanticPlan_u24lambda_u242 = new StringBuilder();
            $this$processSemanticPlan_u24lambda_u242.append("Понял цель: " + semantic.getGoal() + ". ");
            if (!semantic.getConstraints().isEmpty()) {
                $this$processSemanticPlan_u24lambda_u242.append("Ограничения: " + CollectionsKt.joinToString$default(semantic.getConstraints(), null, null, null, 0, null, null, 63, null) + ". ");
            }
            $this$processSemanticPlan_u24lambda_u242.append("План из " + planRun.getPlan().getSteps().size() + " шагов");
            if (!planRun.getPlan().getSteps().isEmpty()) {
                $this$processSemanticPlan_u24lambda_u242.append(" (");
                $this$processSemanticPlan_u24lambda_u242.append(CollectionsKt.joinToString$default(CollectionsKt.take(planRun.getPlan().getSteps(), 4), null, null, null, 0, null, new Function1() { // from class: com.jarvis.brain.AssistantOrchestrator$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AssistantOrchestrator.processSemanticPlan$lambda$2$0((PlanStep) obj);
                    }
                }, 31, null));
                $this$processSemanticPlan_u24lambda_u242.append(")");
            }
            $this$processSemanticPlan_u24lambda_u242.append(". Пока в режиме планирования — скажи, с какого шага начать.");
            String summary = $this$processSemanticPlan_u24lambda_u242.toString();
            return respond$default(this, request, summary, AssistantResponseStatus.PROCESSED, startedAt, null, "PLAN", semantic.getRouteReason(), 16, null);
        }
        return respondClarify(request, "Это похоже на многошаговую задачу. С чего начать?", startedAt, trace);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence processSemanticPlan$lambda$2$0(PlanStep s) {
        Intrinsics.checkNotNullParameter(s, "s");
        String displaySummary = s.getDisplaySummary();
        if (StringsKt.isBlank(displaySummary)) {
            String toolId = s.getToolId();
            if (toolId == null) {
                toolId = s.getKind().name();
            }
            displaySummary = toolId;
        }
        return displaySummary;
    }

    private final AssistantResponse finalizeTrace(AssistantResponse response, RequestTrace trace, long startedAt) {
        WarmState warmState;
        long now = LatencyClock.INSTANCE.elapsedMs();
        InteractionLatency lat = trace.getInteractionLatency();
        if (lat != null) {
            if (lat.getFirstUsefulAt() == null) {
                lat.setFirstUsefulAt(Long.valueOf(now));
            }
            lat.setInteractionCompleteAt(Long.valueOf(now));
            lat.setFallback(lat.getFallback() || response.getFallbackUsed() || trace.getFallback());
            if (trace.getProvider() != null || response.getProvider() != null) {
                if (Intrinsics.areEqual(response.getProvider(), LocalLLMProvider.ID) || Intrinsics.areEqual(trace.getProvider(), LocalLLMProvider.ID)) {
                    warmState = WarmState.WARM;
                } else {
                    warmState = lat.getWarmState();
                }
                lat.setWarmState(warmState);
            }
            InteractionLatencyKt.applyLatency(trace, lat);
        }
        trace.setTotalLatencyMs((System.nanoTime() - startedAt) / ((long) DurationKt.NANOS_IN_MILLIS));
        if (trace.getE2eMs() == null) {
            trace.setE2eMs(Long.valueOf(trace.getTotalLatencyMs()));
        }
        if (trace.getFirstUsefulMs() == null) {
            trace.setFirstUsefulMs(Long.valueOf(trace.getTotalLatencyMs()));
        }
        String result = trace.getResult();
        if (result == null || StringsKt.isBlank(result)) {
            trace.setResult(response.getStatus().name());
        }
        if (trace.getRoute() == RouteDecision.UNKNOWN) {
            String route = response.getRoute();
            if (!(route == null || StringsKt.isBlank(route))) {
                try {
                    Result.Companion companion = Result.INSTANCE;
                    AssistantOrchestrator assistantOrchestrator = this;
                    String route2 = response.getRoute();
                    Intrinsics.checkNotNull(route2);
                    trace.setRoute(RouteDecision.valueOf(route2));
                    Result.m392constructorimpl(Unit.INSTANCE);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    Result.m392constructorimpl(ResultKt.createFailure(th));
                }
            }
        }
        if (StringsKt.isBlank(trace.getRouteReason())) {
            String routeReason = response.getRouteReason();
            if (routeReason == null) {
                routeReason = "";
            }
            trace.setRouteReason(routeReason);
        }
        RequestTraceLog.INSTANCE.emit(trace);
        if (response.getRoute() == null) {
            return response.copy((31 & 1) != 0 ? response.requestId : null, (31 & 2) != 0 ? response.replyText : null, (31 & 4) != 0 ? response.status : null, (31 & 8) != 0 ? response.intent : null, (31 & 16) != 0 ? response.toolResult : null, (31 & 32) != 0 ? response.durationMs : trace.getTotalLatencyMs(), (31 & 64) != 0 ? response.route : trace.getRoute().name(), (31 & 128) != 0 ? response.routeReason : trace.getRouteReason(), (31 & 256) != 0 ? response.provider : trace.getProvider(), (31 & 512) != 0 ? response.model : trace.getModel(), (31 & 1024) != 0 ? response.fallbackUsed : trace.getFallback(), (31 & 2048) != 0 ? response.verification : trace.getVerification());
        }
        return response.copy((31 & 1) != 0 ? response.requestId : null, (31 & 2) != 0 ? response.replyText : null, (31 & 4) != 0 ? response.status : null, (31 & 8) != 0 ? response.intent : null, (31 & 16) != 0 ? response.toolResult : null, (31 & 32) != 0 ? response.durationMs : trace.getTotalLatencyMs(), (31 & 64) != 0 ? response.route : null, (31 & 128) != 0 ? response.routeReason : null, (31 & 256) != 0 ? response.provider : null, (31 & 512) != 0 ? response.model : null, (31 & 1024) != 0 ? response.fallbackUsed : false, (31 & 2048) != 0 ? response.verification : null);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:93:0x023e  */
    /* JADX WARN: Code duplicated, block: B:94:0x0241  */
    private final AssistantResponse processCompound(UserRequest request, List<String> steps, long startedAt, RequestTrace trace) throws InterruptedException {
        String toolId;
        long j;
        String lowerCase;
        trace.setRoute(RouteDecision.PLAN);
        trace.setRouteReason("compound_steps=" + steps.size());
        trace.setPlanner(this.planPathCoordinator != null);
        if (this.planPathCoordinator != null && this.catalogToolDiscovery != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                AssistantOrchestrator assistantOrchestrator = this;
                CatalogToolDiscovery catalogToolDiscovery = assistantOrchestrator.catalogToolDiscovery;
                Intrinsics.checkNotNull(catalogToolDiscovery);
                DiscoveryResult discoveryResultDiscover = catalogToolDiscovery.discover(request.getText(), 8);
                PlanPathCoordinator planPathCoordinator = assistantOrchestrator.planPathCoordinator;
                Intrinsics.checkNotNull(planPathCoordinator);
                PlanPathCoordinator.PlanRun planRunPlanAndRun = planPathCoordinator.planAndRun(request.getText(), discoveryResultDiscover, request.getRequestId(), (40 & 8) != 0 ? System.currentTimeMillis() : 0L, (40 & 16) != 0 ? false : false, (40 & 32) != 0 ? 6 : 0);
                trace.setAgent(true);
                ActionPlanStatus status = planRunPlanAndRun.getPlan().getStatus();
                int size = planRunPlanAndRun.getPlan().getSteps().size();
                AgentLoopResult loopResult = planRunPlanAndRun.getLoopResult();
                Boolean boolValueOf = loopResult != null ? Boolean.valueOf(loopResult.getCompleted()) : null;
                AgentLoopResult loopResult2 = planRunPlanAndRun.getLoopResult();
                Result.m392constructorimpl(Integer.valueOf(Log.i("Jarvis.PlanPath", "compound plan status=" + status + " steps=" + size + " loopCompleted=" + boolValueOf + " reason=" + (loopResult2 != null ? loopResult2.getStoppedReason() : null) + " dry=" + planRunPlanAndRun.getDryRun())));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m392constructorimpl(ResultKt.createFailure(th));
            }
        }
        ArrayList arrayList = new ArrayList();
        AssistantResponseStatus assistantResponseStatus = AssistantResponseStatus.PROCESSED;
        Iterator<T> it = steps.iterator();
        AssistantResponseStatus status2 = assistantResponseStatus;
        Intent intent = null;
        boolean z = false;
        String str = null;
        int i = 0;
        while (it.hasNext()) {
            int i2 = i;
            int i3 = i + 1;
            UserRequest userRequestCopy$default = UserRequest.copy$default(request, (String) it.next(), null, request.getRequestId(), 0L, null, 26, null);
            trace.childStepId(i2 + 1);
            AssistantResponse assistantResponseProcessSingle = processSingle(userRequestCopy$default, startedAt, z, false, false, trace);
            arrayList.add(assistantResponseProcessSingle.getReplyText());
            status2 = assistantResponseProcessSingle.getStatus();
            Intent intent2 = assistantResponseProcessSingle.getIntent();
            if (intent2 == null) {
                intent2 = intent;
            }
            intent = intent2;
            ToolResult toolResult = assistantResponseProcessSingle.getToolResult();
            if (toolResult == null || (toolId = toolResult.getToolId()) == null) {
                toolId = str;
            }
            if (assistantResponseProcessSingle.getStatus() != AssistantResponseStatus.NEEDS_CONFIRMATION && assistantResponseProcessSingle.getStatus() != AssistantResponseStatus.NEEDS_CLARIFICATION && assistantResponseProcessSingle.getStatus() != AssistantResponseStatus.DENIED) {
                if (assistantResponseProcessSingle.getStatus() == AssistantResponseStatus.FAILED) {
                    String str2 = (String) CollectionsKt.getOrNull(steps, i2 + 1);
                    if (str2 != null) {
                        lowerCase = str2.toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                    } else {
                        lowerCase = null;
                    }
                    if (lowerCase == null) {
                        lowerCase = "";
                    }
                    if (!(Intrinsics.areEqual(toolId, "ui.click") && (StringsKt.startsWith$default(lowerCase, "напиши", false, 2, (Object) null) || StringsKt.startsWith$default(lowerCase, "введи", false, 2, (Object) null) || StringsKt.startsWith$default(lowerCase, "напечатай", false, 2, (Object) null)))) {
                    }
                }
                ToolResult toolResult2 = assistantResponseProcessSingle.getToolResult();
                String toolId2 = toolResult2 != null ? toolResult2.getToolId() : null;
                String str3 = toolId2 != null ? toolId2 : "";
                if (Intrinsics.areEqual(str3, "launcher") || StringsKt.startsWith$default(str3, "ui.", false, 2, (Object) null)) {
                    z = true;
                }
                if (i2 < CollectionsKt.getLastIndex(steps)) {
                    if (toolId != null) {
                        switch (toolId) {
                            case "ui.click":
                                j = 1000;
                                break;
                            case "launcher":
                                j = 2400;
                                break;
                            case "system.setting":
                                j = 700;
                                break;
                            case "ui.wait":
                                j = 1000;
                                break;
                            default:
                                j = 500;
                                break;
                        }
                    } else {
                        j = 500;
                    }
                    Thread.sleep(j);
                }
                str = toolId;
                i = i3;
            }
            if (status2 == AssistantResponseStatus.PROCESSED && !arrayList.isEmpty()) {
                arrayList.set(CollectionsKt.getLastIndex(arrayList), this.confirmationStyle.invoke((String) CollectionsKt.last((List) arrayList)));
            }
            return new AssistantResponse(request.getRequestId(), CollectionsKt.joinToString$default(arrayList, StringUtils.SPACE, null, null, 0, null, null, 62, null), status2, intent, null, (System.nanoTime() - startedAt) / ((long) DurationKt.NANOS_IN_MILLIS), null, null, null, null, false, null, 4048, null);
        }
        if (status2 == AssistantResponseStatus.PROCESSED) {
            arrayList.set(CollectionsKt.getLastIndex(arrayList), this.confirmationStyle.invoke((String) CollectionsKt.last((List) arrayList)));
        }
        return new AssistantResponse(request.getRequestId(), CollectionsKt.joinToString$default(arrayList, StringUtils.SPACE, null, null, 0, null, null, 62, null), status2, intent, null, (System.nanoTime() - startedAt) / ((long) DurationKt.NANOS_IN_MILLIS), null, null, null, null, false, null, 4048, null);
    }

    private static final AssistantResponse translationResponse$said(AssistantOrchestrator this$0, UserRequest $request, long $startedAt, String text) {
        this$0.rememberReply(text);
        this$0.state(AssistantState.READY);
        return new AssistantResponse($request.getRequestId(), text, AssistantResponseStatus.PROCESSED, null, null, (System.nanoTime() - $startedAt) / ((long) DurationKt.NANOS_IN_MILLIS), null, null, null, null, false, null, 4048, null);
    }

    private static final AssistantResponse translationResponse$translate(AssistantOrchestrator this$0, UserRequest $request, long $startedAt, RequestTrace $trace, TranslationTarget target, String text) {
        return this$0.respondChat(UserRequest.copy$default($request, Translator.INSTANCE.prompt(target, text), null, null, 0L, null, 30, null), $startedAt, $trace, false);
    }

    private final AssistantResponse translationResponse(UserRequest request, long startedAt, RequestTrace trace) {
        Translator.Request parsed = Translator.INSTANCE.parse(request.getText());
        if (Intrinsics.areEqual(parsed, Translator.Request.Stop.INSTANCE)) {
            Translator.INSTANCE.setActive(null);
            return translationResponse$said(this, request, startedAt, "Переводчик выключен, сэр.");
        }
        if (parsed instanceof Translator.Request.Start) {
            Translator.INSTANCE.setActive(((Translator.Request.Start) parsed).getTarget());
            return translationResponse$said(this, request, startedAt, "Перевожу на " + ((Translator.Request.Start) parsed).getTarget().getName() + ", сэр. Говорите.");
        }
        if (parsed instanceof Translator.Request.Once) {
            return translationResponse$translate(this, request, startedAt, trace, ((Translator.Request.Once) parsed).getTarget(), ((Translator.Request.Once) parsed).getText());
        }
        if (parsed != null) {
            throw new NoWhenBranchMatchedException();
        }
        TranslationTarget it = Translator.INSTANCE.getActive();
        if (it != null) {
            return translationResponse$translate(this, request, startedAt, trace, it, request.getText());
        }
        return null;
    }

    private final Routine findRoutine(String text) {
        Routine routineFind = Routines.INSTANCE.find(text);
        if (routineFind != null) {
            return routineFind;
        }
        Function1<String, List<String>> function1 = this.customRoutine;
        String lowerCase = text.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        List<String> listInvoke = function1.invoke(StringsKt.trimEnd(StringsKt.trim((CharSequence) lowerCase).toString(), '.', '!', '?'));
        if (listInvoke != null) {
            return new Routine(listInvoke, endsWithAnswer(listInvoke) ? "" : "Готово, сэр.", null, 4, null);
        }
        return null;
    }

    private final boolean endsWithAnswer(List<String> steps) {
        return SetsKt.setOf((Object[]) new String[]{"скажи", "расскажи", "прочитай"}).contains(StringsKt.substringBefore$default((String) CollectionsKt.last((List) steps), ' ', (String) null, 2, (Object) null));
    }

    static /* synthetic */ AssistantResponse processSingle$default(AssistantOrchestrator assistantOrchestrator, UserRequest userRequest, long j, boolean z, boolean z2, boolean z3, RequestTrace requestTrace, int i, Object obj) {
        boolean z4;
        boolean z5;
        if ((i & 8) == 0) {
            z4 = z2;
        } else {
            z4 = true;
        }
        if ((i & 16) == 0) {
            z5 = z3;
        } else {
            z5 = true;
        }
        return assistantOrchestrator.processSingle(userRequest, j, z, z4, z5, requestTrace);
    }

    private final AssistantResponse processSingle(UserRequest normalizedRequest, long startedAt, boolean preferUiType, boolean addUser, boolean allowReact, RequestTrace trace) {
        Object objM392constructorimpl;
        String text;
        UserRequest userRequestCopy$default;
        if (addUser) {
            Intrinsics.areEqual(CollectionsKt.lastOrNull((List) this.context.getMessages()), "user: " + normalizedRequest.getText());
        }
        ToolCall followUp = FollowUpResolver.INSTANCE.resolve(normalizedRequest.getText(), this.context.getLastToolCall());
        if (followUp != null) {
            return runTool$default(this, normalizedRequest, followUp, startedAt, null, trace, RouteDecision.FAST_SKILL, "follow_up", false, null, 392, null);
        }
        List $this$processSingle_u24lambda_u240 = CollectionsKt.createListBuilder();
        if (this.memoryEnabled) {
            try {
                Result.Companion companion = Result.INSTANCE;
                objM392constructorimpl = Result.m392constructorimpl(this.memoryProvider.invoke(normalizedRequest.getText()));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m398isFailureimpl(objM392constructorimpl)) {
                objM392constructorimpl = "";
            }
            String mem = StringsKt.take((String) objM392constructorimpl, 180);
            if (!StringsKt.isBlank(mem)) {
                $this$processSingle_u24lambda_u240.add("memory: " + mem);
            }
        }
        $this$processSingle_u24lambda_u240.addAll(CollectionsKt.takeLast(this.context.getMessages(), 4));
        List<String> listBuild = CollectionsKt.build($this$processSingle_u24lambda_u240);
        if (preferUiType) {
            text = normalizedRequest.getText();
        } else {
            text = normalizedRequest.getText();
        }
        String routeText = text;
        if (preferUiType && looksLikeUiTypeOverride(routeText)) {
            String body = StringsKt.trim((CharSequence) StringsKt.removePrefix(StringsKt.removePrefix(removePrefixVerb(routeText), (CharSequence) "на экране "), (CharSequence) "в поле ")).toString();
            userRequestCopy$default = UserRequest.copy$default(normalizedRequest, "введи " + body, null, null, 0L, null, 30, null);
        } else {
            userRequestCopy$default = normalizedRequest;
        }
        UserRequest routeRequest = userRequestCopy$default;
        Iterable $this$map$iv = this.skillRetriever.retrieve(normalizedRequest.getText());
        int $i$f$map = 0;
        int i = 10;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            SkillManifest it = (SkillManifest) item$iv$iv;
            String id = it.getId();
            String name = it.getName();
            String description = it.getDescription();
            List<String> examples = it.getExamples();
            Iterable $this$map$iv2 = it.getParameters();
            Iterable $this$map$iv3 = $this$map$iv;
            int $i$f$map2 = $i$f$map;
            int $i$f$map3 = CollectionsKt.collectionSizeOrDefault($this$map$iv2, i);
            Collection destination$iv$iv2 = new ArrayList($i$f$map3);
            for (Object item$iv$iv2 : $this$map$iv2) {
                SkillManifest.ManifestParameter p = (SkillManifest.ManifestParameter) item$iv$iv2;
                destination$iv$iv2.add(new BrainRouter.SkillParam(p.getName(), p.getDescription(), p.getType().name(), p.getRequired(), p.getAllowedValues()));
            }
            destination$iv$iv.add(new BrainRouter.SkillInfo(id, name, description, examples, (List) destination$iv$iv2));
            $this$map$iv = $this$map$iv3;
            $i$f$map = $i$f$map2;
            i = 10;
        }
        List topSkills = (List) destination$iv$iv;
        CapabilityDiscovery capabilityDiscovery = this.capabilityDiscovery;
        List listDiscover$default = capabilityDiscovery != null ? CapabilityDiscovery.discover$default(capabilityDiscovery, normalizedRequest.getText(), 5, null, 4, null) : null;
        if (listDiscover$default == null) {
            listDiscover$default = CollectionsKt.emptyList();
        }
        List topCaps = listDiscover$default;
        if (!topCaps.isEmpty()) {
            try {
                Result.Companion companion3 = Result.INSTANCE;
                AssistantOrchestrator assistantOrchestrator = this;
                Result.m392constructorimpl(Integer.valueOf(Log.d("Jarvis.Capability", "discover q='" + StringsKt.take(SensitivityClassifier.INSTANCE.mask(normalizedRequest.getText()), 60) + "' → " + CollectionsKt.joinToString$default(topCaps, null, null, null, 0, null, new Function1() { // from class: com.jarvis.brain.AssistantOrchestrator$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AssistantOrchestrator.processSingle$lambda$2$0((CapabilityDescriptor) obj);
                    }
                }, 31, null))));
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.INSTANCE;
                Result.m392constructorimpl(ResultKt.createFailure(th2));
            }
        }
        BrainResult result = this.brainRouter.route(routeRequest, listBuild, topSkills);
        if (!(result instanceof BrainResult.SkillCall)) {
            if (result instanceof BrainResult.Clarify) {
                return respondClarify(normalizedRequest, ((BrainResult.Clarify) result).getQuestion(), startedAt, trace);
            }
            if (result instanceof BrainResult.Chat) {
                return respondChat$default(this, normalizedRequest, startedAt, trace, false, 8, null);
            }
            throw new NoWhenBranchMatchedException();
        }
        if (((BrainResult.SkillCall) result).getConfidence() < this.executeThreshold) {
            if (((BrainResult.SkillCall) result).getConfidence() >= this.clarifyThreshold) {
                return respondClarify(normalizedRequest, "Что именно сделать, сэр?", startedAt, trace);
            }
            return respondChat$default(this, normalizedRequest, startedAt, trace, false, 8, null);
        }
        if (allowReact && shouldReact(normalizedRequest, (BrainResult.SkillCall) result)) {
            return executeWithReact(normalizedRequest, (BrainResult.SkillCall) result, topSkills, listBuild, startedAt, trace);
        }
        return executeSkillCall(normalizedRequest, (BrainResult.SkillCall) result, startedAt, trace);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence processSingle$lambda$2$0(CapabilityDescriptor it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getId();
    }

    private final boolean shouldReact(UserRequest request, BrainResult.SkillCall first) {
        if (CompoundCommandSplitter.INSTANCE.isCompound(request.getText())) {
            return false;
        }
        IntentType type = first.getIntent().getType();
        if (type != IntentType.LAUNCH_APP && type != IntentType.SYSTEM_SETTING) {
            return false;
        }
        String t = request.getText().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(t, "toLowerCase(...)");
        Iterable followVerbs = CollectionsKt.listOf((Object[]) new String[]{"нажми", "тапни", "найди", "напиши", "введи", "включи", "выключи", "прочитай", "опиши", "подожди", "что на экране"});
        Iterable $this$any$iv = followVerbs;
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            String it = (String) element$iv;
            if (StringsKt.contains$default((CharSequence) t, (CharSequence) it, false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:47:0x00b0  */
    private final AssistantResponse executeWithReact(UserRequest request, BrainResult.SkillCall first, List<BrainRouter.SkillInfo> topSkills, List<String> history, long startedAt, RequestTrace trace) throws InterruptedException {
        BrainResult next;
        long j;
        AssistantResponse assistantResponseExecuteSkillCall = executeSkillCall(request, first, startedAt, trace);
        ToolResult toolResult = assistantResponseExecuteSkillCall.getToolResult();
        String lastTool = toolResult != null ? toolResult.getToolId() : null;
        if (lastTool == null) {
            lastTool = "";
        }
        ToolResult toolResult2 = assistantResponseExecuteSkillCall.getToolResult();
        String message = toolResult2 != null ? toolResult2.getMessage() : null;
        if (message == null) {
            message = "";
        }
        String replyText = message;
        if (StringsKt.isBlank(replyText)) {
            replyText = assistantResponseExecuteSkillCall.getReplyText();
        }
        AssistantResponse assistantResponse = assistantResponseExecuteSkillCall;
        int steps = 1;
        String lastTool2 = lastTool;
        String lastMsg = replyText;
        while (steps < 3 && assistantResponse.getStatus() == AssistantResponseStatus.PROCESSED && !StringsKt.isBlank(lastTool2) && (next = this.brainRouter.continueAfterTool(request, history, topSkills, lastTool2, lastMsg)) != null && (next instanceof BrainResult.SkillCall) && ((BrainResult.SkillCall) next).getConfidence() >= this.executeThreshold) {
            switch (lastTool2) {
                case "launcher":
                    j = 400;
                    break;
                case "system.setting":
                    j = 800;
                    break;
                case "ui.wait":
                    j = 0;
                    break;
                default:
                    j = 450;
                    break;
            }
            long delay = j;
            if (delay > 0) {
                Thread.sleep(delay);
            }
            AssistantResponse stepResp = executeSkillCall(request, (BrainResult.SkillCall) next, startedAt, trace);
            String requestId = request.getRequestId();
            Iterable $this$filter$iv = CollectionsKt.listOf((Object[]) new String[]{assistantResponse.getReplyText(), stepResp.getReplyText()});
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                String it = (String) element$iv$iv;
                if (!StringsKt.isBlank(it)) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            String strJoinToString$default = CollectionsKt.joinToString$default((List) destination$iv$iv, StringUtils.SPACE, null, null, 0, null, null, 62, null);
            AssistantResponseStatus status = stepResp.getStatus();
            Intent intent = stepResp.getIntent();
            if (intent == null) {
                intent = assistantResponse.getIntent();
            }
            Intent intent2 = intent;
            ToolResult toolResult3 = stepResp.getToolResult();
            if (toolResult3 == null) {
                toolResult3 = assistantResponse.getToolResult();
            }
            assistantResponse = new AssistantResponse(requestId, strJoinToString$default, status, intent2, toolResult3, (System.nanoTime() - startedAt) / ((long) DurationKt.NANOS_IN_MILLIS), "FAST_SKILL", "react", null, null, false, stepResp.getVerification(), 1792, null);
            ToolResult toolResult4 = stepResp.getToolResult();
            String toolId = toolResult4 != null ? toolResult4.getToolId() : null;
            if (toolId == null) {
                toolId = "";
            }
            lastTool2 = toolId;
            ToolResult toolResult5 = stepResp.getToolResult();
            String message2 = toolResult5 != null ? toolResult5.getMessage() : null;
            if (message2 == null) {
                message2 = "";
            }
            String replyText2 = message2;
            if (StringsKt.isBlank(replyText2)) {
                replyText2 = stepResp.getReplyText();
            }
            String lastMsg2 = replyText2;
            lastMsg = lastMsg2;
            steps++;
            if (stepResp.getStatus() != AssistantResponseStatus.PROCESSED) {
                return assistantResponse;
            }
        }
        return assistantResponse;
    }

    private final boolean looksLikeUiTypeOverride(String text) {
        String lowerCase = text.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String t = StringsKt.trim((CharSequence) lowerCase).toString();
        return StringsKt.startsWith$default(t, "напиши ", false, 2, (Object) null) || StringsKt.startsWith$default(t, "напечатай ", false, 2, (Object) null) || StringsKt.startsWith$default(t, "введи ", false, 2, (Object) null);
    }

    private final String removePrefixVerb(String $this$removePrefixVerb) {
        String t = StringsKt.trim((CharSequence) $this$removePrefixVerb).toString();
        List<String> verbs = CollectionsKt.listOf((Object[]) new String[]{"напиши ", "напечатай ", "введи "});
        for (String v : verbs) {
            String lowerCase = t.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (StringsKt.startsWith$default(lowerCase, v, false, 2, (Object) null)) {
                return StringsKt.trim((CharSequence) StringsKt.drop(t, v.length())).toString();
            }
        }
        return t;
    }

    private final AssistantResponse handlePending(UserRequest request, PendingAction pending, long startedAt, RequestTrace trace) {
        String lowerCase = request.getText().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String text = StringsKt.trim((CharSequence) lowerCase).toString();
        switch (WhenMappings.$EnumSwitchMapping$1[pending.getPhase().ordinal()]) {
            case 1:
                if (isNo(text)) {
                    this.context.clearPending();
                    return respond$default(this, request, "Отменил.", AssistantResponseStatus.PROCESSED, startedAt, null, null, null, 112, null);
                }
                String chat = StringsKt.trim((CharSequence) stripTelegramWriteVerb(request.getText())).toString();
                if (StringsKt.isBlank(chat)) {
                    return respondPending(request, "Кому написать?", startedAt, pending);
                }
                ToolCall withChat = ToolCall.copy$default(pending.getToolCall(), null, null, null, MapsKt.plus(pending.getToolCall().getArguments(), MapsKt.mapOf(TuplesKt.to(JarvisActivity.CHAT, chat))), 7, null);
                Object obj = withChat.getArguments().get(VoiceService.EXTRA_TEXT);
                String string = obj != null ? obj.toString() : null;
                if (string == null) {
                    string = "";
                }
                String msg = StringsKt.trim((CharSequence) string).toString();
                if (!StringsKt.isBlank(msg)) {
                    this.context.clearPending();
                    return runTool$default(this, request, withChat, startedAt, null, trace, null, null, true, null, 360, null);
                }
                PendingAction ask = new PendingAction(withChat, "Что написать?", PendingAction.Phase.COLLECT_TEXT, null, null, 24, null);
                this.context.setPendingAction(ask);
                return respond$default(this, request, ask.getPrompt(), AssistantResponseStatus.NEEDS_CLARIFICATION, startedAt, null, null, null, 112, null);
            case 2:
                if (isNo(text)) {
                    this.context.clearPending();
                    return respond$default(this, request, "Отменил.", AssistantResponseStatus.PROCESSED, startedAt, null, null, null, 112, null);
                }
                String message = StringsKt.trim((CharSequence) stripTelegramWriteVerb(request.getText())).toString();
                if (StringsKt.isBlank(message)) {
                    return respondPending(request, "Что написать?", startedAt, pending);
                }
                ToolCall updatedCall = ToolCall.copy$default(pending.getToolCall(), null, null, null, MapsKt.plus(pending.getToolCall().getArguments(), MapsKt.mapOf(TuplesKt.to(VoiceService.EXTRA_TEXT, message))), 7, null);
                this.context.clearPending();
                return runTool$default(this, request, updatedCall, startedAt, null, trace, null, null, true, null, 360, null);
            case 3:
                if (isNo(text)) {
                    this.context.clearPending();
                    return respond$default(this, request, "Отменил.", AssistantResponseStatus.PROCESSED, startedAt, null, null, null, 112, null);
                }
                PendingAction.Choice choice = matchChoice(text, pending.getChoices());
                if (choice == null) {
                    String names = CollectionsKt.joinToString$default(pending.getChoices(), ", ", null, null, 0, null, new Function1() { // from class: com.jarvis.brain.AssistantOrchestrator$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return AssistantOrchestrator.handlePending$lambda$0((PendingAction.Choice) obj2);
                        }
                    }, 30, null);
                    return respondPending(request, "Не расслышал. Кому именно: " + names + "?", startedAt, pending);
                }
                ToolCall updatedCall2 = ToolCall.copy$default(pending.getToolCall(), null, null, null, MapsKt.plus(pending.getToolCall().getArguments(), MapsKt.mapOf(TuplesKt.to("contact", choice.getLabel()), TuplesKt.to("number", choice.getValue()))), 7, null);
                this.context.clearPending();
                return runTool$default(this, request, updatedCall2, startedAt, null, trace, null, null, true, null, 360, null);
            case 4:
                if (!isYes(text)) {
                    if (!isNo(text)) {
                        return respondPending(request, "Да или нет, сэр? " + pending.getPrompt(), startedAt, pending);
                    }
                    this.context.clearPending();
                    return respond$default(this, request, "Отменил.", AssistantResponseStatus.PROCESSED, startedAt, null, null, null, 112, null);
                }
                if (pending.getChoices().size() <= 1) {
                    this.context.clearPending();
                    return runTool$default(this, request, pending.getToolCall(), startedAt, null, trace, null, null, true, pending.getIntegrityFingerprint(), LocationRequestCompat.QUALITY_LOW_POWER, null);
                }
                String names2 = CollectionsKt.joinToString$default(pending.getChoices(), ", ", null, null, 0, null, new Function1() { // from class: com.jarvis.brain.AssistantOrchestrator$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return AssistantOrchestrator.handlePending$lambda$1((PendingAction.Choice) obj2);
                    }
                }, 30, null);
                PendingAction ask2 = new PendingAction(pending.getToolCall(), "Нашёл несколько: " + names2 + ". Кому звонить?", PendingAction.Phase.DISAMBIGUATE, pending.getChoices(), pending.getIntegrityFingerprint());
                this.context.setPendingAction(ask2);
                return respond$default(this, request, ask2.getPrompt(), AssistantResponseStatus.NEEDS_CLARIFICATION, startedAt, null, null, null, 112, null);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence handlePending$lambda$0(PendingAction.Choice it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getLabel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence handlePending$lambda$1(PendingAction.Choice it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getLabel();
    }

    private final boolean looksLikeFreshCommand(String text, PendingAction pending) {
        boolean z;
        if (pending.getPhase() == PendingAction.Phase.COLLECT_TEXT || pending.getPhase() == PendingAction.Phase.COLLECT_CHAT) {
            Iterable cancel = CollectionsKt.listOf((Object[]) new String[]{"открой", "включи", "выключи", "позвони", "запусти", "поставь", "найди", "нажми", "закрой", "сверни"});
            Iterable $this$any$iv = cancel;
            if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
                return false;
            }
            for (Object element$iv : $this$any$iv) {
                String it = (String) element$iv;
                if (StringsKt.startsWith$default(text, it, false, 2, (Object) null)) {
                    return true;
                }
            }
            return false;
        }
        Iterable verbs = CollectionsKt.listOf((Object[]) new String[]{"позвони", "набери", "открой", "включи", "выключи", "напиши", "отправь", "сделай", "поставь", "найди", "запусти", "нажми", "покажи"});
        Iterable $this$any$iv2 = verbs;
        if (!($this$any$iv2 instanceof Collection) || !((Collection) $this$any$iv2).isEmpty()) {
            Iterator it2 = $this$any$iv2.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z = false;
                    break;
                }
                Object element$iv2 = it2.next();
                String it3 = (String) element$iv2;
                if (StringsKt.startsWith$default(text, it3, false, 2, (Object) null)) {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
        }
        if (z) {
            return true;
        }
        return (pending.getPhase() != PendingAction.Phase.DISAMBIGUATE || StringsKt.split$default((CharSequence) text, new String[]{StringUtils.SPACE}, false, 0, 6, (Object) null).size() <= 4) ? false : false;
    }

    private final String stripTelegramWriteVerb(String raw) {
        String t = StringsKt.trim((CharSequence) raw).toString();
        List<String> verbs = CollectionsKt.listOf((Object[]) new String[]{"напиши ", "напечатай ", "отправь ", "передай ", "напиши", "напечатай"});
        String lower = t.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lower, "toLowerCase(...)");
        for (String v : verbs) {
            if (StringsKt.startsWith$default(lower, v, false, 2, (Object) null)) {
                return StringsKt.trim((CharSequence) StringsKt.drop(t, v.length())).toString();
            }
        }
        return t;
    }

    /* JADX WARN: Code duplicated, block: B:51:0x020d  */
    /* JADX WARN: Code duplicated, block: B:56:0x0240  */
    /* JADX WARN: Code duplicated, block: B:60:0x0248  */
    /* JADX WARN: Code duplicated, block: B:63:0x024e  */
    /* JADX WARN: Code duplicated, block: B:65:0x025a  */
    /* JADX WARN: Code duplicated, block: B:68:0x0261  */
    /* JADX WARN: Code duplicated, block: B:71:0x026a  */
    /* JADX WARN: Code duplicated, block: B:74:0x02af  */
    private final AssistantResponse executeSkillCall(UserRequest request, BrainResult.SkillCall call, long startedAt, RequestTrace trace) {
        ToolCall toolCall;
        char c;
        String str;
        Object obj;
        String lowerCase;
        String string;
        Intent intent = call.getIntent();
        JarvisEvents.INSTANCE.publish(new AssistantEvent.IntentResolved(intent));
        trace.setRoute(RouteDecision.FAST_SKILL);
        if (StringsKt.isBlank(trace.getRouteReason())) {
            trace.setRouteReason("skill_call");
        }
        trace.setSkill(intent.getType().name());
        ToolCall toolCall2 = ToolCallFactory.INSTANCE.build(intent, request.getRequestId());
        if (toolCall2 == null) {
            return respond$default(this, request, "Не хватает деталей, сэр. Уточните, пожалуйста.", AssistantResponseStatus.NEEDS_CLARIFICATION, startedAt, intent, null, null, 96, null);
        }
        if (Intrinsics.areEqual(toolCall2.getToolId(), "telegram.send")) {
            Object obj2 = toolCall2.getArguments().get(JarvisActivity.CHAT);
            String string2 = obj2 != null ? obj2.toString() : null;
            if (string2 == null) {
                string2 = "";
            }
            String chat = StringsKt.trim((CharSequence) string2).toString();
            Object obj3 = toolCall2.getArguments().get(VoiceService.EXTRA_TEXT);
            String string3 = obj3 != null ? obj3.toString() : null;
            if (string3 == null) {
                string3 = "";
            }
            String msg = StringsKt.trim((CharSequence) string3).toString();
            if (StringsKt.isBlank(chat)) {
                this.context.setPendingAction(new PendingAction(toolCall2, "Кому написать?", PendingAction.Phase.COLLECT_CHAT, null, null, 24, null));
                return respond$default(this, request, "Кому написать?", AssistantResponseStatus.NEEDS_CLARIFICATION, startedAt, intent, null, null, 96, null);
            }
            if (StringsKt.isBlank(msg)) {
                this.context.setPendingAction(new PendingAction(ToolCall.copy$default(toolCall2, null, null, null, MapsKt.plus(toolCall2.getArguments(), MapsKt.mapOf(TuplesKt.to(JarvisActivity.CHAT, chat))), 7, null), "Что написать?", PendingAction.Phase.COLLECT_TEXT, null, null, 24, null));
                return respond$default(this, request, "Что написать?", AssistantResponseStatus.NEEDS_CLARIFICATION, startedAt, intent, null, null, 96, null);
            }
        }
        Tool tool = this.toolRegistry.find(toolCall2.getToolId());
        if (tool == null) {
            return respond$default(this, request, "Инструмент «" + toolCall2.getToolId() + "» пока недоступен.", AssistantResponseStatus.FAILED, startedAt, intent, null, null, 96, null);
        }
        char c2 = 0;
        if (Intrinsics.areEqual(toolCall2.getToolId(), "phone.call")) {
            Object obj4 = toolCall2.getArguments().get("contact");
            String raw = obj4 != null ? obj4.toString() : null;
            if (raw == null) {
                raw = "";
            }
            List<PendingAction.Choice> listInvoke = this.resolveContacts.invoke(raw);
            if (listInvoke.size() == 1) {
                c2 = 0;
                toolCall = ToolCall.copy$default(toolCall2, null, null, null, MapsKt.plus(toolCall2.getArguments(), MapsKt.mapOf(TuplesKt.to("contact", listInvoke.get(0).getLabel()), TuplesKt.to("number", listInvoke.get(0).getValue()))), 7, null);
            }
            if (Intrinsics.areEqual(toolCall.getToolId(), "launcher")) {
                String[] strArr = new String[3];
                strArr[c2] = "force_stop";
                strArr[1] = "forcestop";
                strArr[2] = "kill";
                Set of = SetsKt.setOf((Object[]) strArr);
                obj = toolCall.getArguments().get("action");
                if (obj != null || (string = obj.toString()) == null) {
                    lowerCase = null;
                } else {
                    lowerCase = string.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                }
                c = CollectionsKt.contains(of, lowerCase) ? (char) 1 : c2;
            }
            if (c != 0) {
                Object obj5 = toolCall.getArguments().get("app");
                String string4 = obj5 != null ? obj5.toString() : null;
                str = string4 != null ? string4 : "";
                if (StringsKt.isBlank(str)) {
                    str = "приложение";
                }
                String app = str;
                String prompt = "Принудительно остановить «" + app + "»? Да или нет, сэр?";
                return respondPending(request, prompt, startedAt, new PendingAction(toolCall, prompt, null, null, null, 28, null));
            }
            return runTool$default(this, request, toolCall, startedAt, intent, trace, RouteDecision.FAST_SKILL, "skill:" + toolCall.getToolId(), false, null, 384, null);
        }
        toolCall = toolCall2;
        if (Intrinsics.areEqual(toolCall.getToolId(), "launcher")) {
            String[] strArr2 = new String[3];
            strArr2[c2] = "force_stop";
            strArr2[1] = "forcestop";
            strArr2[2] = "kill";
            Set of2 = SetsKt.setOf((Object[]) strArr2);
            obj = toolCall.getArguments().get("action");
            if (obj != null) {
                lowerCase = null;
            } else {
                lowerCase = null;
            }
            if (CollectionsKt.contains(of2, lowerCase)) {
            }
        }
        if (c != 0) {
            Object obj6 = toolCall.getArguments().get("app");
            if (obj6 != null) {
            }
            str = string4 != null ? string4 : "";
            if (StringsKt.isBlank(str)) {
                str = "приложение";
            }
            String app2 = str;
            String prompt2 = "Принудительно остановить «" + app2 + "»? Да или нет, сэр?";
            return respondPending(request, prompt2, startedAt, new PendingAction(toolCall, prompt2, null, null, null, 28, null));
        }
        return runTool$default(this, request, toolCall, startedAt, intent, trace, RouteDecision.FAST_SKILL, "skill:" + toolCall.getToolId(), false, null, 384, null);
    }

    static /* synthetic */ AssistantResponse runTool$default(AssistantOrchestrator assistantOrchestrator, UserRequest userRequest, ToolCall toolCall, long j, Intent intent, RequestTrace requestTrace, RouteDecision routeDecision, String str, boolean z, String str2, int i, Object obj) {
        return assistantOrchestrator.runTool(userRequest, toolCall, j, (i & 8) != 0 ? null : intent, (i & 16) != 0 ? null : requestTrace, (i & 32) != 0 ? RouteDecision.FAST_SKILL : routeDecision, (i & 64) != 0 ? "tool" : str, (i & 128) != 0 ? false : z, (i & 256) != 0 ? null : str2);
    }

    private final AssistantResponse runTool(UserRequest request, ToolCall toolCall, long startedAt, Intent intent, RequestTrace trace, RouteDecision routeHint, String routeReason, boolean userConfirmed, String priorFingerprint) {
        ToolCall toolCallCopy$default;
        String string;
        String string2;
        String string3;
        String string4;
        String string5;
        String string6;
        String string7;
        List<PendingAction.Choice> listEmptyList;
        state(AssistantState.EXECUTING);
        if (trace != null) {
            trace.setRoute(routeHint);
        }
        if (trace != null && StringsKt.isBlank(trace.getRouteReason())) {
            trace.setRouteReason(routeReason);
        }
        if (trace != null) {
            trace.setTool(toolCall.getToolId());
        }
        Tool tool = this.toolRegistry.find(toolCall.getToolId());
        if (tool == null) {
            return respond(request, "Инструмент «" + toolCall.getToolId() + "» пока недоступен.", AssistantResponseStatus.FAILED, startedAt, intent, routeHint.name(), routeReason);
        }
        ExecutionOrchestrator.Outcome outcome = this.execution.executeAction(toolCall, tool.getDefinition(), (96 & 4) != 0 ? toolCall.getRequestId() : request.getRequestId(), (96 & 8) != 0 ? false : userConfirmed, (96 & 16) != 0 ? null : priorFingerprint, (96 & 32) != 0 ? System.currentTimeMillis() : 0L, (96 & 64) != 0 ? AutonomyLevel.SAFE_ONLY : null);
        String string8 = null;
        if (outcome instanceof ExecutionOrchestrator.Outcome.NeedsConfirmation) {
            if (Intrinsics.areEqual(toolCall.getToolId(), "phone.call")) {
                Object obj = toolCall.getArguments().get("action");
                if (Intrinsics.areEqual(obj != null ? obj.toString() : null, "history")) {
                    return runTool$default(this, request, toolCall, startedAt, intent, trace, null, null, true, null, 352, null);
                }
            }
            String prompt = confirmationPrompt(toolCall);
            if (Intrinsics.areEqual(toolCall.getToolId(), "phone.call")) {
                Object obj2 = toolCall.getArguments().get("contact");
                string8 = obj2 != null ? obj2.toString() : null;
                String raw = string8 != null ? string8 : "";
                List<PendingAction.Choice> listInvoke = this.resolveContacts.invoke(raw);
                listEmptyList = listInvoke.size() > 1 ? listInvoke : CollectionsKt.emptyList();
            } else {
                listEmptyList = CollectionsKt.emptyList();
            }
            this.context.setPendingAction(new PendingAction(toolCall, prompt, PendingAction.Phase.CONFIRM, listEmptyList, ((ExecutionOrchestrator.Outcome.NeedsConfirmation) outcome).getIntegrityFingerprint()));
            return respond(request, prompt, AssistantResponseStatus.NEEDS_CONFIRMATION, startedAt, intent, routeHint.name(), "needs_confirmation");
        }
        if (outcome instanceof ExecutionOrchestrator.Outcome.Denied) {
            return respond(request, DENIED_REPLY, AssistantResponseStatus.DENIED, startedAt, intent, routeHint.name(), "denied: " + ((ExecutionOrchestrator.Outcome.Denied) outcome).getReason());
        }
        if (outcome instanceof ExecutionOrchestrator.Outcome.Failed) {
            return respond(request, ((ExecutionOrchestrator.Outcome.Failed) outcome).getReason(), AssistantResponseStatus.FAILED, startedAt, intent, routeHint.name(), "exec_failed");
        }
        if (!(outcome instanceof ExecutionOrchestrator.Outcome.Executed)) {
            throw new NoWhenBranchMatchedException();
        }
        ToolResult result = ((ExecutionOrchestrator.Outcome.Executed) outcome).getToolResult();
        String verifyLabel = ExecutionOrchestratorKt.traceLabel(((ExecutionOrchestrator.Outcome.Executed) outcome).getVerification());
        if (trace != null) {
            trace.setVerification(verifyLabel);
        }
        JarvisEvents.INSTANCE.publish(new AssistantEvent.ToolExecuted(result));
        if (Intrinsics.areEqual(toolCall.getToolId(), "knowledge") && (result.getStatus() == ToolResultStatus.OFFLINE || result.getStatus() == ToolResultStatus.FAILED)) {
            ToolResultStatus status = result.getStatus();
            SensitivityClassifier sensitivityClassifier = SensitivityClassifier.INSTANCE;
            String message = result.getMessage();
            if (message == null) {
                message = "";
            }
            Log.i("Jarvis.Orchestrator", "knowledge " + status + " → chat LLM fallback msg='" + StringsKt.take(sensitivityClassifier.mask(message), 80) + "'");
            this.context.setLastToolCall(toolCall);
            Object obj3 = toolCall.getArguments().get(SearchIntents.EXTRA_QUERY);
            if (obj3 != null && (string7 = obj3.toString()) != null) {
                string8 = StringsKt.trim((CharSequence) string7).toString();
            }
            String q = string8 != null ? string8 : "";
            UserRequest chatReq = !StringsKt.isBlank(q) ? UserRequest.copy$default(request, "Расскажи кратко про " + q, null, null, 0L, null, 30, null) : request;
            if (trace != null) {
                trace.setFallback(true);
            }
            return respondChat$default(this, chatReq, startedAt, trace, false, 8, null);
        }
        if (result.getStatus() == ToolResultStatus.SUCCESS) {
            switch (toolCall.getToolId()) {
                case "launcher":
                    Object obj4 = result.getData().get("app");
                    String string9 = (obj4 == null || (string2 = obj4.toString()) == null) ? null : StringsKt.trim((CharSequence) string2).toString();
                    if (string9 == null) {
                        string9 = "";
                    }
                    String str = string9;
                    if (StringsKt.isBlank(str)) {
                        Object obj5 = toolCall.getArguments().get("app");
                        if (obj5 != null && (string = obj5.toString()) != null) {
                            string8 = StringsKt.trim((CharSequence) string).toString();
                        }
                        str = string8 != null ? string8 : "";
                    }
                    String app = str;
                    if (!StringsKt.isBlank(app)) {
                        Map<String, Object> arguments = toolCall.getArguments();
                        Pair[] pairArr = new Pair[2];
                        pairArr[0] = TuplesKt.to("app", app);
                        Object obj6 = toolCall.getArguments().get("action");
                        if (obj6 == null) {
                            obj6 = AbstractCircuitBreaker.PROPERTY_NAME;
                        }
                        pairArr[1] = TuplesKt.to("action", obj6);
                        toolCallCopy$default = ToolCall.copy$default(toolCall, null, null, null, MapsKt.plus(arguments, MapsKt.mapOf(pairArr)), 7, null);
                    } else {
                        toolCallCopy$default = toolCall;
                    }
                    break;
                case "weather":
                    Object obj7 = result.getData().get("city");
                    if (obj7 != null && (string3 = obj7.toString()) != null) {
                        string8 = StringsKt.trim((CharSequence) string3).toString();
                    }
                    String city = string8 != null ? string8 : "";
                    toolCallCopy$default = !StringsKt.isBlank(city) ? ToolCall.copy$default(toolCall, null, null, null, MapsKt.plus(toolCall.getArguments(), MapsKt.mapOf(TuplesKt.to("city", city))), 7, null) : toolCall;
                    break;
                case "knowledge":
                    Object obj8 = toolCall.getArguments().get(SearchIntents.EXTRA_QUERY);
                    String string10 = (obj8 == null || (string6 = obj8.toString()) == null) ? null : StringsKt.trim((CharSequence) string6).toString();
                    if (string10 == null) {
                        string10 = "";
                    }
                    String q2 = string10;
                    Object obj9 = toolCall.getArguments().get("topic");
                    String string11 = (obj9 == null || (string5 = obj9.toString()) == null) ? null : StringsKt.trim((CharSequence) string5).toString();
                    if (string11 == null) {
                        string11 = "";
                    }
                    String strTake = string11;
                    if (StringsKt.isBlank(strTake)) {
                        String lowerCase = q2.toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                        strTake = StringsKt.take(StringsKt.trim((CharSequence) StringsKt.removePrefix(StringsKt.removePrefix(StringsKt.removePrefix(StringsKt.removePrefix(StringsKt.removePrefix(StringsKt.removePrefix(lowerCase, (CharSequence) "кто такие "), (CharSequence) "кто такой "), (CharSequence) "кто такая "), (CharSequence) "что такое "), (CharSequence) "расскажи про "), (CharSequence) "расскажи о ")).toString(), 40);
                    }
                    String topic = strTake;
                    Object obj10 = result.getData().get("title");
                    if (obj10 != null && (string4 = obj10.toString()) != null) {
                        string8 = StringsKt.trim((CharSequence) string4).toString();
                    }
                    String answeredTitle = string8 != null ? string8 : "";
                    if (StringsKt.isBlank(q2) && StringsKt.isBlank(topic)) {
                        toolCallCopy$default = toolCall;
                    } else {
                        Map $this$runTool_u24lambda_u241 = MapsKt.createMapBuilder();
                        if (!StringsKt.isBlank(q2)) {
                            $this$runTool_u24lambda_u241.put(SearchIntents.EXTRA_QUERY, q2);
                        }
                        if (!StringsKt.isBlank(topic)) {
                            $this$runTool_u24lambda_u241.put("topic", topic);
                        }
                        if (!StringsKt.isBlank(answeredTitle)) {
                            $this$runTool_u24lambda_u241.put("exclude_title", answeredTitle);
                            $this$runTool_u24lambda_u241.put("title", answeredTitle);
                        }
                        Unit unit = Unit.INSTANCE;
                        toolCallCopy$default = ToolCall.copy$default(toolCall, null, null, null, MapsKt.build($this$runTool_u24lambda_u241), 7, null);
                    }
                    break;
                default:
                    toolCallCopy$default = toolCall;
                    break;
            }
            ToolCall enriched = toolCallCopy$default;
            this.context.setLastToolCall(enriched);
        }
        String reply = ResponsePlanner.INSTANCE.plan(result);
        boolean factRefused = rememberIfFact(request);
        String finalReply = factRefused ? FactExtractor.REFUSAL : reply;
        rememberReply(finalReply);
        state(AssistantState.READY);
        return new AssistantResponse(request.getRequestId(), finalReply, ResponsePlanner.INSTANCE.assistantStatusOf(result), intent, result, (System.nanoTime() - startedAt) / ((long) DurationKt.NANOS_IN_MILLIS), routeHint.name(), routeReason, null, null, false, verifyLabel, 1792, null);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final String confirmationPrompt(ToolCall toolCall) {
        String string;
        String toolId = toolCall.getToolId();
        String string2 = null;
        switch (toolId.hashCode()) {
            case -1869698338:
                if (toolId.equals("phone.call")) {
                    Object obj = toolCall.getArguments().get("contact");
                    if (obj == null || (string = obj.toString()) == null) {
                        Object obj2 = toolCall.getArguments().get("number");
                        if (obj2 != null) {
                            string2 = obj2.toString();
                        }
                    } else {
                        string2 = string;
                    }
                    String name = string2;
                    return name == null ? "Перезвонить на последний номер? Да или нет, сэр?" : "Позвонить «" + name + "»? Да или нет, сэр?";
                }
                break;
            case -1407250528:
                if (toolId.equals("launcher")) {
                    Object obj3 = toolCall.getArguments().get("app");
                    string2 = obj3 != null ? obj3.toString() : null;
                    if (string2 == null) {
                        string2 = "";
                    }
                    String str = string2;
                    if (StringsKt.isBlank(str)) {
                        str = "приложение";
                    }
                    String app = str;
                    return "Принудительно остановить «" + app + "»? Да или нет, сэр?";
                }
                break;
            case -214808988:
                if (toolId.equals(NotificationReplyTool.ID)) {
                    return "Ответить: «" + toolCall.getArguments().get(VoiceService.EXTRA_TEXT) + "»? Да или нет, сэр?";
                }
                break;
        }
        return "Выполнить «" + toolCall.getToolId() + "»? Да или нет, сэр?";
    }

    private final PendingAction.Choice matchChoice(String text, List<PendingAction.Choice> choices) {
        int ordinal;
        Object maxElem$iv;
        String str;
        boolean z;
        PendingAction.Choice choice;
        int i;
        PendingAction.Choice choice2 = null;
        if (choices.isEmpty()) {
            return null;
        }
        String lowerCase = text.toLowerCase(Locale.ROOT);
        String str2 = "toLowerCase(...)";
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String t = StringsKt.trim((CharSequence) lowerCase).toString();
        boolean z2 = false;
        if (StringsKt.contains$default((CharSequence) t, (CharSequence) "перв", false, 2, (Object) null) || Intrinsics.areEqual(t, StructuredAiLimits.SCHEMA_VERSION) || StringsKt.contains$default((CharSequence) t, (CharSequence) "один", false, 2, (Object) null)) {
            ordinal = 0;
        } else if (StringsKt.contains$default((CharSequence) t, (CharSequence) "втор", false, 2, (Object) null) || Intrinsics.areEqual(t, ExifInterface.GPS_MEASUREMENT_2D)) {
            ordinal = 1;
        } else {
            ordinal = (StringsKt.contains$default((CharSequence) t, (CharSequence) "трет", false, 2, (Object) null) || Intrinsics.areEqual(t, ExifInterface.GPS_MEASUREMENT_3D)) ? 2 : -1;
        }
        if (ordinal >= 0 && ordinal < choices.size()) {
            return choices.get(ordinal);
        }
        List<PendingAction.Choice> $this$map$iv = choices;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            PendingAction.Choice c = (PendingAction.Choice) item$iv$iv;
            String label = c.getLabel().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(label, str2);
            if (Intrinsics.areEqual(label, t)) {
                i = 100;
                str = str2;
                z = false;
                choice = null;
            } else {
                str = str2;
                z = false;
                choice = null;
                i = (StringsKt.contains$default((CharSequence) label, (CharSequence) t, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) label, false, 2, (Object) null)) ? 80 : StringsKt.contains$default((CharSequence) Transliterator.INSTANCE.compareKey(label), (CharSequence) Transliterator.INSTANCE.compareKey(t), false, 2, (Object) null) ? 70 : 0;
            }
            int score = i;
            destination$iv$iv.add(TuplesKt.to(c, Integer.valueOf(score)));
            z2 = z;
            choice2 = choice;
            str2 = str;
        }
        PendingAction.Choice choice3 = choice2;
        boolean z3 = z2;
        Iterable $this$filter$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            Pair it = (Pair) element$iv$iv;
            if (((Number) it.getSecond()).intValue() > 0 ? true : z3) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        Iterable $this$maxByOrNull$iv = (List) destination$iv$iv2;
        Iterator iterator$iv = $this$maxByOrNull$iv.iterator();
        if (iterator$iv.hasNext()) {
            maxElem$iv = iterator$iv.next();
            if (iterator$iv.hasNext()) {
                Pair it2 = (Pair) maxElem$iv;
                int maxValue$iv = ((Number) it2.getSecond()).intValue();
                do {
                    Object e$iv = iterator$iv.next();
                    Pair it3 = (Pair) e$iv;
                    int v$iv = ((Number) it3.getSecond()).intValue();
                    if (maxValue$iv < v$iv) {
                        maxElem$iv = e$iv;
                        maxValue$iv = v$iv;
                    }
                } while (iterator$iv.hasNext());
            }
        } else {
            maxElem$iv = choice3;
        }
        Pair scored = (Pair) maxElem$iv;
        return scored != null ? (PendingAction.Choice) scored.getFirst() : choice3;
    }

    private final boolean isYes(String text) {
        String lowerCase = text.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String t = StringsKt.trim((CharSequence) lowerCase).toString();
        if (isNo(t)) {
            return false;
        }
        return Intrinsics.areEqual(t, "да") || Intrinsics.areEqual(t, "ага") || Intrinsics.areEqual(t, "угу") || Intrinsics.areEqual(t, "yes") || Intrinsics.areEqual(t, "ок") || Intrinsics.areEqual(t, "окей") || StringsKt.startsWith$default(t, "да ", false, 2, (Object) null) || Intrinsics.areEqual(t, "подтверждаю") || Intrinsics.areEqual(t, "конечно") || Intrinsics.areEqual(t, "звони") || Intrinsics.areEqual(t, "звонй") || Intrinsics.areEqual(t, "call");
    }

    private final boolean isNo(String text) {
        String lowerCase = text.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String t = StringsKt.trim((CharSequence) lowerCase).toString();
        return Intrinsics.areEqual(t, "нет") || Intrinsics.areEqual(t, "не") || Intrinsics.areEqual(t, "no") || StringsKt.startsWith$default(t, "не ", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "отмен", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "не надо", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "не звон", false, 2, (Object) null);
    }

    static /* synthetic */ AssistantResponse respondClarify$default(AssistantOrchestrator assistantOrchestrator, UserRequest userRequest, String str, long j, RequestTrace requestTrace, int i, Object obj) {
        RequestTrace requestTrace2;
        if ((i & 8) == 0) {
            requestTrace2 = requestTrace;
        } else {
            requestTrace2 = null;
        }
        return assistantOrchestrator.respondClarify(userRequest, str, j, requestTrace2);
    }

    private final AssistantResponse respondClarify(UserRequest request, String question, long startedAt, RequestTrace trace) {
        String routeReason;
        if (trace != null) {
            trace.setRoute(RouteDecision.CLARIFY);
        }
        String routeReason2 = trace != null ? trace.getRouteReason() : null;
        if ((routeReason2 == null || StringsKt.isBlank(routeReason2)) && trace != null) {
            trace.setRouteReason("clarify");
        }
        return respond$default(this, request, question, AssistantResponseStatus.NEEDS_CLARIFICATION, startedAt, null, "CLARIFY", (trace == null || (routeReason = trace.getRouteReason()) == null) ? "clarify" : routeReason, 16, null);
    }

    private final AssistantResponse respondPending(UserRequest request, String question, long startedAt, PendingAction pending) {
        this.context.setPendingAction(pending);
        return respond$default(this, request, question, AssistantResponseStatus.NEEDS_CONFIRMATION, startedAt, null, "FAST_SKILL", "pending_confirm", 16, null);
    }

    static /* synthetic */ AssistantResponse respondChat$default(AssistantOrchestrator assistantOrchestrator, UserRequest userRequest, long j, RequestTrace requestTrace, boolean z, int i, Object obj) {
        RequestTrace requestTrace2;
        boolean z2;
        if ((i & 4) == 0) {
            requestTrace2 = requestTrace;
        } else {
            requestTrace2 = null;
        }
        if ((i & 8) == 0) {
            z2 = z;
        } else {
            z2 = true;
        }
        return assistantOrchestrator.respondChat(userRequest, j, requestTrace2, z2);
    }

    /* JADX WARN: Code duplicated, block: B:36:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:38:0x00be  */
    /* JADX WARN: Code duplicated, block: B:39:0x00cb  */
    private final AssistantResponse respondChat(UserRequest request, long startedAt, RequestTrace trace, boolean allowFollowUp) {
        AssembledLlmContext assembledLlmContextBuild;
        String it;
        RouteDecision routeDecision;
        RouteDecision route;
        long j;
        ContextBuildDiagnostics d;
        Object objM392constructorimpl;
        ContextBuildDiagnostics diagnostics;
        InteractionLatency lat = trace != null ? trace.getInteractionLatency() : null;
        long tCtx0 = LatencyClock.INSTANCE.elapsedMs();
        if (lat != null) {
            lat.setContextStartAt(Long.valueOf(tCtx0));
        }
        if (!this.memoryEnabled || this.llmContextAssembly == null) {
            assembledLlmContextBuild = null;
        } else {
            ContextBuildInput input = this.llmContextAssembly.gatherInput(request.getText(), this.context.getMessages(), this.chatPersona.invoke(), (24 & 8) != 0 ? LlmContextKind.CHAT : null, (24 & 16) != 0 ? LlmContextBudget.BALANCED : null, (24 & 32) != 0 ? true : this.cloudMemoryAllowed.invoke().booleanValue(), (24 & 64) != 0 ? "BALANCED" : this.chatProfile.invoke(), (24 & 128) != 0 ? null : this.context);
            assembledLlmContextBuild = this.unifiedContextBuilder.build(input);
        }
        AssembledLlmContext assembled = assembledLlmContextBuild;
        if (lat != null) {
            lat.setContextEndAt(Long.valueOf(LatencyClock.INSTANCE.elapsedMs()));
        }
        if (assembled != null && (diagnostics = assembled.getDiagnostics()) != null) {
            long ms = diagnostics.getBuildMs();
            if (lat != null && lat.getContextStartAt() != null) {
                Long contextStartAt = lat.getContextStartAt();
                Intrinsics.checkNotNull(contextStartAt);
                lat.setContextEndAt(Long.valueOf(contextStartAt.longValue() + ms));
            }
        }
        if (assembled != null && (it = assembled.getLegacyMemoriesSnippet()) != null) {
            if (StringsKt.isBlank(it)) {
                it = null;
            }
            if (it == null) {
                if (this.memoryEnabled) {
                    it = this.memoryProvider.invoke(request.getText());
                } else {
                    it = "";
                }
            }
        } else if (this.memoryEnabled) {
            it = this.memoryProvider.invoke(request.getText());
        } else {
            it = "";
        }
        String memories = it;
        long tDlg = System.nanoTime();
        if (assembled != null && (d = assembled.getDiagnostics()) != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                AssistantOrchestrator assistantOrchestrator = this;
                objM392constructorimpl = Result.m392constructorimpl(Integer.valueOf(Log.i("Jarvis.LlmContext", d.logLine())));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
            }
            Result.m391boximpl(objM392constructorimpl);
        }
        if (lat != null) {
            lat.setLlmRequestStartAt(Long.valueOf(LatencyClock.INSTANCE.elapsedMs()));
        }
        DialogueResult detailed = this.dialogue.respondDetailed(request.getText(), this.context.getMessages(), memories, assembled);
        if (lat != null) {
            lat.setLlmCompleteAt(Long.valueOf(LatencyClock.INSTANCE.elapsedMs()));
        }
        long jNanoTime = System.nanoTime() - tDlg;
        long j2 = DurationKt.NANOS_IN_MILLIS;
        long dialogueMs = jNanoTime / j2;
        switch (WhenMappings.$EnumSwitchMapping$2[detailed.getKind().ordinal()]) {
            case 1:
                routeDecision = RouteDecision.TEMPLATE;
                break;
            case 2:
                routeDecision = RouteDecision.TEMPLATE;
                break;
            case 3:
                routeDecision = RouteDecision.CHAT;
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        RouteDecision route2 = routeDecision;
        if (trace != null) {
            trace.setRoute(route2);
        }
        if (trace != null) {
            String reason = detailed.getReason();
            if (StringsKt.isBlank(reason)) {
                reason = detailed.getKind().name();
            }
            trace.setRouteReason(reason);
        }
        if (detailed.getKind() == DialogueResult.Kind.LLM) {
            if (trace != null) {
                String providerHint = detailed.getProviderHint();
                if (providerHint == null) {
                    providerHint = "chat_llm";
                }
                trace.setProvider(providerHint);
            }
            if (trace != null) {
                trace.setModel(detailed.getModelHint());
            }
            if (trace != null) {
                trace.setFallback(detailed.getFallbackUsed());
            }
            String selectionReason = detailed.getSelectionReason();
            if (!(selectionReason == null || StringsKt.isBlank(selectionReason)) && trace != null) {
                trace.setAiSelectionReason(detailed.getSelectionReason());
            }
            Long latencyMs = detailed.getLatencyMs();
            if (latencyMs != null) {
                long it2 = latencyMs.longValue();
                if (trace != null) {
                    trace.setAiLatencyMs(Long.valueOf(it2));
                }
                if (trace != null) {
                    trace.setAiCompletionMs(Long.valueOf(it2));
                }
                if (lat != null && lat.getLlmRequestStartAt() != null && lat.getLlmCompleteAt() == null) {
                    Long llmRequestStartAt = lat.getLlmRequestStartAt();
                    Intrinsics.checkNotNull(llmRequestStartAt);
                    lat.setLlmCompleteAt(Long.valueOf(llmRequestStartAt.longValue() + it2));
                }
            }
            TtftKind kind = detailed.getTtftKind();
            if (kind == null) {
                kind = TtftKind.NOT_AVAILABLE;
            }
            if (lat != null) {
                lat.setTtftKind(kind);
            }
            if (trace != null) {
                trace.setAiTtftKind(kind.name());
            }
            if (kind == TtftKind.REAL) {
                Long ttftMs = detailed.getTtftMs();
                if (ttftMs != null) {
                    long it3 = ttftMs.longValue();
                    if (trace != null) {
                        trace.setAiTtftMs(Long.valueOf(it3));
                    }
                }
            } else if (trace != null) {
                trace.setAiTtftMs(null);
            }
            ContextBuildDiagnostics d2 = detailed.getContextDiagnostics();
            if (d2 != null) {
                if (trace != null) {
                    trace.setContextSections(CollectionsKt.joinToString$default(d2.getContextSections(), ",", null, null, 0, null, null, 62, null));
                }
                if (trace != null) {
                    trace.setContextChars(Integer.valueOf(d2.getContextChars()));
                }
                if (trace != null) {
                    trace.setMemoryCount(Integer.valueOf(d2.getMemoryCount()));
                }
                if (trace != null) {
                    trace.setHistoryTurns(Integer.valueOf(d2.getHistoryTurns()));
                }
                if (trace != null) {
                    trace.setSystemPromptVersion(d2.getSystemPromptVersion());
                }
                if (trace != null) {
                    trace.setContextMs(Long.valueOf(d2.getBuildMs()));
                }
            }
        }
        try {
            Result.Companion companion3 = Result.INSTANCE;
            AssistantOrchestrator assistantOrchestrator2 = this;
            route = route2;
            try {
                j = j2;
                try {
                    Result.m392constructorimpl(Integer.valueOf(Log.i("Jarvis.Orchestrator", "dialogue " + dialogueMs + "ms kind=" + detailed.getKind() + " reason=" + detailed.getReason() + " replyLen=" + detailed.getText().length() + " user='" + StringsKt.take(SensitivityClassifier.INSTANCE.mask(request.getText()), 80) + "'")));
                } catch (Throwable th2) {
                    th = th2;
                    Result.Companion companion4 = Result.INSTANCE;
                    Result.m392constructorimpl(ResultKt.createFailure(th));
                }
            } catch (Throwable th3) {
                th = th3;
                j = j2;
            }
        } catch (Throwable th4) {
            th = th4;
            route = route2;
            j = j2;
        }
        RouteDecision route3 = route;
        long j3 = j;
        AssistantResponse it4 = cloudToolResponse(request, detailed.getText(), startedAt, trace, allowFollowUp);
        if (it4 != null) {
            return it4;
        }
        boolean factRefused = allowFollowUp && rememberIfFact(request);
        String finalText = factRefused ? FactExtractor.REFUSAL : detailed.getText();
        rememberReply(finalText);
        state(AssistantState.READY);
        return new AssistantResponse(request.getRequestId(), finalText, AssistantResponseStatus.PROCESSED, null, null, (System.nanoTime() - startedAt) / j3, route3.name(), detailed.getReason(), trace != null ? trace.getProvider() : null, null, false, null, 3600, null);
    }

    private final void rememberReply(String text) {
        String masked = SensitivityClassifier.INSTANCE.mask(text);
        this.context.addAssistant(masked);
        ConversationJournal.INSTANCE.appendAssistant(masked);
    }

    private final void rememberUser(String text) {
        this.context.addUser(text);
        ConversationJournal.INSTANCE.appendUser(text);
    }

    private final boolean rememberIfFact(UserRequest request) {
        if (!this.memoryEnabled) {
            return false;
        }
        FactExtractor.Decision decision = FactExtractor.INSTANCE.decide(request.getText());
        if (decision instanceof FactExtractor.Decision.Save) {
            try {
                Result.Companion companion = Result.INSTANCE;
                AssistantOrchestrator $this$rememberIfFact_u24lambda_u240 = this;
                Result.m392constructorimpl(Boolean.valueOf(LongTermMemoryInterface.add$default($this$rememberIfFact_u24lambda_u240.longTermMemory, ((FactExtractor.Decision.Save) decision).getText(), "preference", null, 4, null)));
                return false;
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m392constructorimpl(ResultKt.createFailure(th));
                return false;
            }
        }
        if (Intrinsics.areEqual(decision, FactExtractor.Decision.Refused.INSTANCE)) {
            return true;
        }
        if (decision == null) {
            return false;
        }
        throw new NoWhenBranchMatchedException();
    }

    static /* synthetic */ AssistantResponse respond$default(AssistantOrchestrator assistantOrchestrator, UserRequest userRequest, String str, AssistantResponseStatus assistantResponseStatus, long j, Intent intent, String str2, String str3, int i, Object obj) {
        return assistantOrchestrator.respond(userRequest, str, assistantResponseStatus, j, (i & 16) != 0 ? null : intent, (i & 32) != 0 ? null : str2, (i & 64) != 0 ? null : str3);
    }

    private final AssistantResponse respond(UserRequest request, String text, AssistantResponseStatus status, long startedAt, Intent intent, String route, String routeReason) {
        rememberReply(text);
        state(AssistantState.READY);
        return new AssistantResponse(request.getRequestId(), text, status, intent, null, (System.nanoTime() - startedAt) / ((long) DurationKt.NANOS_IN_MILLIS), route, routeReason, null, null, false, null, 3856, null);
    }

    private final void state(AssistantState s) {
        JarvisEvents.INSTANCE.publish(new AssistantEvent.StateChanged(s));
    }
}
