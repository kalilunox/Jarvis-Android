package com.jarvis.app;

import android.content.Context;
import android.os.Process;
import android.util.Log;
import androidx.core.view.PointerIconCompat;
import com.jarvis.app.asr.CloudAsrFactory;
import com.jarvis.app.companion.CompanionMemory;
import com.jarvis.app.companion.CompanionProactive;
import com.jarvis.app.proactive.PhoneStateReader;
import com.jarvis.app.tts.CloudTtsFactory;
import com.jarvis.app.ui.JarvisActivity;
import com.jarvis.brain.AssistantOrchestrator;
import com.jarvis.brain.BrainRouter;
import com.jarvis.brain.ChatLlmEngine;
import com.jarvis.brain.CustomRoutineTool;
import com.jarvis.brain.DialogueResponder;
import com.jarvis.brain.ForgetFactTool;
import com.jarvis.brain.LlmBrain;
import com.jarvis.brain.LongTermMemory;
import com.jarvis.brain.LongTermMemoryInterface;
import com.jarvis.brain.RuleBasedBrain;
import com.jarvis.brain.SkillManifestLoader;
import com.jarvis.brain.aiprovider.AICompletionRequest;
import com.jarvis.brain.aiprovider.AICompletionResult;
import com.jarvis.brain.aiprovider.AIProvider;
import com.jarvis.brain.aiprovider.AIProviderRegistry;
import com.jarvis.brain.aiprovider.AIRequestComplexity;
import com.jarvis.brain.aiprovider.AIRouter;
import com.jarvis.brain.aiprovider.AiTaskType;
import com.jarvis.brain.aiprovider.ChatStreamPipeline;
import com.jarvis.brain.aiprovider.CloudLLMProvider;
import com.jarvis.brain.aiprovider.LocalLLMProvider;
import com.jarvis.brain.aiprovider.ModelNamed;
import com.jarvis.brain.aiprovider.NaraRouterDefaults;
import com.jarvis.brain.aiprovider.OpenAiCompatibleCloudClient;
import com.jarvis.brain.aiprovider.RoutedChatLlmEngine;
import com.jarvis.brain.cloud.ChainCloudProvider;
import com.jarvis.brain.llm.LlmContextAssembly;
import com.jarvis.brain.semantic.LlmStructuredSemanticInterpreter;
import com.jarvis.core.agent.AgentExecutionLoop;
import com.jarvis.core.agent.ProactiveAgent;
import com.jarvis.core.autonomy.AutonomyActionPipeline;
import com.jarvis.core.autonomy.SafeAutonomousExecutor;
import com.jarvis.core.browseragent.BrowserAgent;
import com.jarvis.core.capability.CapabilityAwareToolExecutor;
import com.jarvis.core.capability.CapabilityDiscovery;
import com.jarvis.core.capability.CapabilityExecutor;
import com.jarvis.core.capability.CapabilityRegistry;
import com.jarvis.core.capability.ToolCapabilityCatalog;
import com.jarvis.core.cloud.ChainHealth;
import com.jarvis.core.cloud.ChainRouter;
import com.jarvis.core.common.DefaultToolExecutor;
import com.jarvis.core.common.RiskBasedPolicy;
import com.jarvis.core.common.ToolExecutor;
import com.jarvis.core.common.ToolRegistry;
import com.jarvis.core.common.model.ConversationContext;
import com.jarvis.core.common.model.PendingAction;
import com.jarvis.core.companion.PhoneContext;
import com.jarvis.core.companion.PhoneContextBus;
import com.jarvis.core.diagnostics.SystemDiagnosticsSnapshot;
import com.jarvis.core.discovery.CatalogToolDiscovery;
import com.jarvis.core.execution.CatalogToolRegistry;
import com.jarvis.core.execution.CatalogToolRegistryAdapter;
import com.jarvis.core.filesagent.FilesAgent;
import com.jarvis.core.mcp.McpRegistry;
import com.jarvis.core.memory.Memory2Record;
import com.jarvis.core.memory.Memory2Store;
import com.jarvis.core.memory.MemoryRecall;
import com.jarvis.core.nativeandroid.NativeAndroidToolCatalog;
import com.jarvis.core.nlp.JarvisStyle;
import com.jarvis.core.planner.AIPlanner;
import com.jarvis.core.planner.DeterministicAIPlanner;
import com.jarvis.core.plugin.PluginRegistry;
import com.jarvis.core.remote.RemoteDeviceRegistry;
import com.jarvis.core.routines.RoutineGenerator;
import com.jarvis.core.routines.RoutineStore;
import com.jarvis.core.routines.SavedRoutine;
import com.jarvis.core.runtime.ExecutionOrchestrator;
import com.jarvis.core.runtime.FeatureLifecycle;
import com.jarvis.core.runtime.FeatureStatus;
import com.jarvis.core.runtime.FeatureStatusSnapshot;
import com.jarvis.core.runtime.LatencyStatsStore;
import com.jarvis.core.runtime.PlanPathCoordinator;
import com.jarvis.core.runtime.semantic.FastSemanticClassifier;
import com.jarvis.core.skills.SkillManifest;
import com.jarvis.core.skills.SkillManifestRegistry;
import com.jarvis.core.skills.SkillRetriever;
import com.jarvis.core.storage.ConversationJournal;
import com.jarvis.core.storage.JarvisSchema;
import com.jarvis.core.storage.RetentionPolicy;
import com.jarvis.core.verification.DefaultResultVerifier;
import com.jarvis.core.verification.ResultVerifier;
import com.jarvis.core.vision.CloudVisionStub;
import com.jarvis.core.vision.LocalVisionStub;
import com.jarvis.core.vision.VisionAvailability;
import com.jarvis.core.vision.VisionProvider;
import com.jarvis.core.vision.VisionRouter;
import com.jarvis.core.voice.AsrProvider;
import com.jarvis.core.voice.AsrRouter;
import com.jarvis.core.voice.LocalAsrStub;
import com.jarvis.core.voice.LocalTtsStub;
import com.jarvis.core.voice.TtsProvider;
import com.jarvis.core.voice.TtsRouter;
import com.jarvis.core.voice.VoiceProviderAvailability;
import com.jarvis.core.voice.VoiceProviderKind;
import com.jarvis.core.voice.VoiceRouteContext;
import com.jarvis.tools.AnnounceSettingsTool;
import com.jarvis.tools.AppAgentTool;
import com.jarvis.tools.BriefingTool;
import com.jarvis.tools.BrightnessTool;
import com.jarvis.tools.CalendarTool;
import com.jarvis.tools.CallTool;
import com.jarvis.tools.ClipboardTool;
import com.jarvis.tools.ContactResolver;
import com.jarvis.tools.ContactsTool;
import com.jarvis.tools.DeviceIntentToolsKt;
import com.jarvis.tools.FilesTool;
import com.jarvis.tools.FlashlightTool;
import com.jarvis.tools.InfoToolsKt;
import com.jarvis.tools.KnowledgeTool;
import com.jarvis.tools.LauncherTool;
import com.jarvis.tools.MusicTool;
import com.jarvis.tools.NotesTool;
import com.jarvis.tools.NotificationReplyTool;
import com.jarvis.tools.NotificationTool;
import com.jarvis.tools.PhoneControlTool;
import com.jarvis.tools.PlacesTool;
import com.jarvis.tools.ReminderTool;
import com.jarvis.tools.ScreenshotTool;
import com.jarvis.tools.SmsTool;
import com.jarvis.tools.SystemSettingTool;
import com.jarvis.tools.SystemStatusTool;
import com.jarvis.tools.TelegramSendTool;
import com.jarvis.tools.TimerTool;
import com.jarvis.tools.UiClickTool;
import com.jarvis.tools.UiGlobalTool;
import com.jarvis.tools.UiReadTool;
import com.jarvis.tools.UiSwipeTool;
import com.jarvis.tools.UiTypeTool;
import com.jarvis.tools.UiWaitTool;
import com.jarvis.tools.VolumeTool;
import com.jarvis.tools.WeatherTool;
import com.jarvis.tools.WebAnswerTool;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: BrainGraph.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000ø\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013J\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015J\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017J\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019J\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bJ\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dJ\b\u0010 \u001a\u0004\u0018\u00010!J\b\u0010\"\u001a\u0004\u0018\u00010#J\b\u0010$\u001a\u0004\u0018\u00010%J\b\u0010&\u001a\u0004\u0018\u00010'J\b\u0010(\u001a\u0004\u0018\u00010)J\b\u0010*\u001a\u0004\u0018\u00010+J\b\u0010,\u001a\u0004\u0018\u00010-J\b\u0010.\u001a\u0004\u0018\u00010/J\b\u00100\u001a\u0004\u0018\u000101J\b\u00102\u001a\u0004\u0018\u000103J\b\u00104\u001a\u0004\u0018\u000105J\b\u00106\u001a\u0004\u0018\u000107J\b\u00108\u001a\u0004\u0018\u000109J\b\u0010:\u001a\u0004\u0018\u00010;J\b\u0010<\u001a\u0004\u0018\u00010=J\b\u0010>\u001a\u0004\u0018\u00010?J\b\u0010@\u001a\u0004\u0018\u00010AJ\u000e\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020\u0011J\u000e\u0010E\u001a\u00020F2\u0006\u0010D\u001a\u00020\u0011J\u000e\u0010G\u001a\u00020\u00072\u0006\u0010D\u001a\u00020\u0011J\u000e\u0010H\u001a\u00020I2\u0006\u0010D\u001a\u00020\u0011J\u000e\u0010J\u001a\u00020I2\u0006\u0010D\u001a\u00020\u0011J\u000e\u0010K\u001a\u00020I2\u0006\u0010D\u001a\u00020\u0011J\u0010\u0010L\u001a\u00020I2\u0006\u0010D\u001a\u00020\u000bH\u0002J\u0006\u0010M\u001a\u00020IJ\u000e\u0010N\u001a\u00020\t2\u0006\u0010D\u001a\u00020\u0011J\u0006\u0010O\u001a\u00020IJ\u0006\u0010P\u001a\u00020IJ\u0006\u0010Q\u001a\u00020IJ\u0006\u0010R\u001a\u00020IJ\u000e\u0010S\u001a\u00020I2\u0006\u0010T\u001a\u00020UJ\u0010\u0010V\u001a\u0004\u0018\u00010W2\u0006\u0010D\u001a\u00020\u0011J\u0010\u0010X\u001a\u0004\u0018\u00010Y2\u0006\u0010D\u001a\u00020\u0011J\u0010\u0010Z\u001a\u00020[2\u0006\u0010D\u001a\u00020\u0011H\u0002J\u0012\u0010\\\u001a\u0004\u0018\u00010Y2\u0006\u0010D\u001a\u00020\u0011H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u0004\u0018\u00010/X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u0004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00102\u001a\u0004\u0018\u000103X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00106\u001a\u0004\u0018\u000107X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00108\u001a\u0004\u0018\u000109X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010:\u001a\u0004\u0018\u00010;X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010<\u001a\u0004\u0018\u00010=X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010>\u001a\u0004\u0018\u00010?X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010@\u001a\u0004\u0018\u00010AX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010]\u001a\u00020UX\u0082T¢\u0006\u0002\n\u0000¨\u0006^"}, d2 = {"Lcom/jarvis/app/BrainGraph;", "", "<init>", "()V", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "cached", "Lcom/jarvis/brain/AssistantOrchestrator;", "memoryInstance", "Lcom/jarvis/brain/LongTermMemory;", "dialogueContext", "Lcom/jarvis/core/common/model/ConversationContext;", "llmBrain", "Lcom/jarvis/brain/LlmBrain;", "chatLlm", "Lcom/jarvis/app/LlamaChatLlmEngine;", "appContext", "Landroid/content/Context;", "capabilityRegistry", "Lcom/jarvis/core/capability/CapabilityRegistry;", "capabilityDiscovery", "Lcom/jarvis/core/capability/CapabilityDiscovery;", "capabilityExecutor", "Lcom/jarvis/core/capability/CapabilityExecutor;", "catalogToolRegistry", "Lcom/jarvis/core/execution/CatalogToolRegistry;", "catalogToolDiscovery", "Lcom/jarvis/core/discovery/CatalogToolDiscovery;", "aiProviderRegistry", "Lcom/jarvis/brain/aiprovider/AIProviderRegistry;", "chainCloudProvider", "Lcom/jarvis/brain/cloud/ChainCloudProvider;", "aiRouter", "Lcom/jarvis/brain/aiprovider/AIRouter;", "aiPlanner", "Lcom/jarvis/core/planner/AIPlanner;", "agentExecutionLoop", "Lcom/jarvis/core/agent/AgentExecutionLoop;", "resultVerifier", "Lcom/jarvis/core/verification/ResultVerifier;", "pluginRegistry", "Lcom/jarvis/core/plugin/PluginRegistry;", "mcpRegistry", "Lcom/jarvis/core/mcp/McpRegistry;", "filesAgent", "Lcom/jarvis/core/filesagent/FilesAgent;", "browserAgent", "Lcom/jarvis/core/browseragent/BrowserAgent;", "routineStore", "Lcom/jarvis/core/routines/RoutineStore;", "routineGenerator", "Lcom/jarvis/core/routines/RoutineGenerator;", "memory2Store", "Lcom/jarvis/core/memory/Memory2Store;", "asrRouter", "Lcom/jarvis/core/voice/AsrRouter;", "ttsRouter", "Lcom/jarvis/core/voice/TtsRouter;", "visionRouter", "Lcom/jarvis/core/vision/VisionRouter;", "proactiveAgent", "Lcom/jarvis/core/agent/ProactiveAgent;", "remoteDeviceRegistry", "Lcom/jarvis/core/remote/RemoteDeviceRegistry;", "toolExecutor", "Lcom/jarvis/core/common/ToolExecutor;", "diagnosticsSnapshot", "Lcom/jarvis/core/diagnostics/SystemDiagnosticsSnapshot;", "context", "featureStatusSnapshot", "Lcom/jarvis/core/runtime/FeatureStatusSnapshot;", "build", "warmLlmBrain", "", "warmChatLlm", "preloadChatModel", "seedDialogueHistory", "clearDialogueHistory", JarvisActivity.MEMORY, "shutdownLlmBrain", "shutdownChatLlm", "invalidate", "invalidateChatLlm", "onCloudKeyChanged", "providerId", "", "chatLlmOrNull", "Lcom/jarvis/brain/ChatLlmEngine;", "chatModelFile", "Ljava/io/File;", "buildBrainRouter", "Lcom/jarvis/brain/BrainRouter;", "getLlmModelPath", "LLM_MODEL_NAME", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BrainGraph {
    private static final String LLM_MODEL_NAME = "brain.litertlm";
    private static volatile AgentExecutionLoop agentExecutionLoop;
    private static volatile AIPlanner aiPlanner;
    private static volatile AIProviderRegistry aiProviderRegistry;
    private static volatile AIRouter aiRouter;
    private static volatile Context appContext;
    private static volatile AsrRouter asrRouter;
    private static volatile BrowserAgent browserAgent;
    private static volatile AssistantOrchestrator cached;
    private static volatile CapabilityDiscovery capabilityDiscovery;
    private static volatile CapabilityExecutor capabilityExecutor;
    private static volatile CapabilityRegistry capabilityRegistry;
    private static volatile CatalogToolDiscovery catalogToolDiscovery;
    private static volatile CatalogToolRegistry catalogToolRegistry;
    private static volatile ChainCloudProvider chainCloudProvider;
    private static volatile LlamaChatLlmEngine chatLlm;
    private static volatile ConversationContext dialogueContext;
    private static volatile FilesAgent filesAgent;
    private static volatile LlmBrain llmBrain;
    private static volatile McpRegistry mcpRegistry;
    private static volatile Memory2Store memory2Store;
    private static volatile LongTermMemory memoryInstance;
    private static volatile PluginRegistry pluginRegistry;
    private static volatile ProactiveAgent proactiveAgent;
    private static volatile RemoteDeviceRegistry remoteDeviceRegistry;
    private static volatile ResultVerifier resultVerifier;
    private static volatile RoutineGenerator routineGenerator;
    private static volatile RoutineStore routineStore;
    private static volatile ToolExecutor toolExecutor;
    private static volatile TtsRouter ttsRouter;
    private static volatile VisionRouter visionRouter;
    public static final BrainGraph INSTANCE = new BrainGraph();
    private static final ReentrantLock lock = new ReentrantLock();

    private BrainGraph() {
    }

    public final CapabilityRegistry capabilityRegistry() {
        return capabilityRegistry;
    }

    public final CapabilityDiscovery capabilityDiscovery() {
        return capabilityDiscovery;
    }

    public final CapabilityExecutor capabilityExecutor() {
        return capabilityExecutor;
    }

    public final CatalogToolRegistry catalogToolRegistry() {
        return catalogToolRegistry;
    }

    public final CatalogToolDiscovery catalogToolDiscovery() {
        return catalogToolDiscovery;
    }

    public final AIProviderRegistry aiProviderRegistry() {
        return aiProviderRegistry;
    }

    public final AIRouter aiRouter() {
        return aiRouter;
    }

    public final AIPlanner aiPlanner() {
        return aiPlanner;
    }

    public final AgentExecutionLoop agentExecutionLoop() {
        return agentExecutionLoop;
    }

    public final ResultVerifier resultVerifier() {
        return resultVerifier;
    }

    public final PluginRegistry pluginRegistry() {
        return pluginRegistry;
    }

    public final McpRegistry mcpRegistry() {
        return mcpRegistry;
    }

    public final FilesAgent filesAgent() {
        return filesAgent;
    }

    public final BrowserAgent browserAgent() {
        return browserAgent;
    }

    public final RoutineStore routineStore() {
        return routineStore;
    }

    public final RoutineGenerator routineGenerator() {
        return routineGenerator;
    }

    public final Memory2Store memory2Store() {
        return memory2Store;
    }

    public final AsrRouter asrRouter() {
        return asrRouter;
    }

    public final TtsRouter ttsRouter() {
        return ttsRouter;
    }

    public final VisionRouter visionRouter() {
        return visionRouter;
    }

    public final ProactiveAgent proactiveAgent() {
        return proactiveAgent;
    }

    public final RemoteDeviceRegistry remoteDeviceRegistry() {
        return remoteDeviceRegistry;
    }

    public final ToolExecutor toolExecutor() {
        return toolExecutor;
    }

    public final SystemDiagnosticsSnapshot diagnosticsSnapshot(Context context) {
        AsrProvider asr;
        TtsProvider tts;
        String id;
        String id2;
        List<Memory2Record> listDurable;
        List<SavedRoutine> listEnabled;
        List<AIProvider> listAvailable;
        List<AIProvider> listCloudProviders;
        List<AIProvider> listLocalProviders;
        Intrinsics.checkNotNullParameter(context, "context");
        Context app = context.getApplicationContext();
        AsrRouter asrRouter2 = asrRouter;
        if (asrRouter2 != null) {
            CloudAsrFactory cloudAsrFactory = CloudAsrFactory.INSTANCE;
            Intrinsics.checkNotNull(app);
            asr = asrRouter2.select(new VoiceRouteContext(cloudAsrFactory.isOnline(app), Intrinsics.areEqual(JarvisSettings.INSTANCE.asrMode(app), "CLOUD_PREFERRED"), Intrinsics.areEqual(JarvisSettings.INSTANCE.asrMode(app), "LOCAL_ONLY")));
        } else {
            asr = null;
        }
        TtsRouter ttsRouter2 = ttsRouter;
        if (ttsRouter2 != null) {
            CloudTtsFactory cloudTtsFactory = CloudTtsFactory.INSTANCE;
            Intrinsics.checkNotNull(app);
            tts = ttsRouter2.select(new VoiceRouteContext(cloudTtsFactory.isOnline(app), Intrinsics.areEqual(JarvisSettings.INSTANCE.ttsMode(app), "CLOUD_PREFERRED"), Intrinsics.areEqual(JarvisSettings.INSTANCE.ttsMode(app), "LOCAL_ONLY")));
        } else {
            tts = null;
        }
        VisionRouter visionRouter2 = visionRouter;
        VisionProvider vision = visionRouter2 != null ? visionRouter2.select(true, false) : null;
        Intrinsics.checkNotNull(app);
        FeatureStatusSnapshot features = featureStatusSnapshot(app);
        long jCurrentTimeMillis = System.currentTimeMillis();
        boolean z = agentExecutionLoop != null;
        boolean z2 = aiPlanner != null;
        boolean z3 = aiRouter != null;
        boolean zCloudConfigured = JarvisSettings.INSTANCE.cloudConfigured(app);
        boolean zPreferCloudLlm = JarvisSettings.INSTANCE.preferCloudLlm(app);
        AIProviderRegistry aIProviderRegistry = aiProviderRegistry;
        int size = (aIProviderRegistry == null || (listLocalProviders = aIProviderRegistry.localProviders()) == null) ? 0 : listLocalProviders.size();
        AIProviderRegistry aIProviderRegistry2 = aiProviderRegistry;
        int size2 = (aIProviderRegistry2 == null || (listCloudProviders = aIProviderRegistry2.cloudProviders()) == null) ? 0 : listCloudProviders.size();
        AIProviderRegistry aIProviderRegistry3 = aiProviderRegistry;
        int size3 = (aIProviderRegistry3 == null || (listAvailable = aIProviderRegistry3.available()) == null) ? 0 : listAvailable.size();
        CatalogToolRegistry catalogToolRegistry2 = catalogToolRegistry;
        int size4 = catalogToolRegistry2 != null ? catalogToolRegistry2.size() : 0;
        boolean z4 = catalogToolDiscovery != null;
        RoutineStore routineStore2 = routineStore;
        int size5 = (routineStore2 == null || (listEnabled = routineStore2.enabled()) == null) ? 0 : listEnabled.size();
        Memory2Store memory2Store2 = memory2Store;
        int size6 = (memory2Store2 == null || (listDurable = memory2Store2.durable()) == null) ? 0 : listDurable.size();
        RemoteDeviceRegistry remoteDeviceRegistry2 = remoteDeviceRegistry;
        int size7 = remoteDeviceRegistry2 != null ? remoteDeviceRegistry2.size() : 0;
        boolean z5 = (vision != null ? vision.availability() : null) == VisionAvailability.AVAILABLE;
        String str = (asr == null || (id2 = asr.getId()) == null) ? PhoneStateReader.FAKE_NONE : id2;
        String str2 = (tts == null || (id = tts.getId()) == null) ? PhoneStateReader.FAKE_NONE : id;
        List listPlus = CollectionsKt.plus((Collection) CollectionsKt.plus((Collection) CollectionsKt.listOf((Object[]) new String[]{"roadmap=v2-09", "asrMode=" + JarvisSettings.INSTANCE.asrMode(app), "asrCloud=" + CloudAsrFactory.INSTANCE.cloudConfiguredLabel(app), "ttsMode=" + JarvisSettings.INSTANCE.ttsMode(app), "ttsCloud=" + CloudTtsFactory.INSTANCE.cloudConfiguredLabel(app)}), (Iterable) features.lines()), (Iterable) LatencyStatsStore.INSTANCE.summaryLines());
        Iterable $this$map$iv = features.getFeatures();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            FeatureStatus it = (FeatureStatus) item$iv$iv;
            destination$iv$iv.add(it.m369short());
            app = app;
        }
        return new SystemDiagnosticsSnapshot(jCurrentTimeMillis, z, z2, z3, zCloudConfigured, zPreferCloudLlm, size, size2, size3, size4, z4, size5, size6, size7, z5, str, str2, listPlus, (List) destination$iv$iv);
    }

    public final FeatureStatusSnapshot featureStatusSnapshot(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Context app = context.getApplicationContext();
        JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
        Intrinsics.checkNotNull(app);
        boolean cloudOk = jarvisSettings.cloudConfigured(app);
        long jCurrentTimeMillis = System.currentTimeMillis();
        FeatureStatus[] featureStatusArr = new FeatureStatus[21];
        featureStatusArr[0] = featureStatusSnapshot$st$default("executionOrchestrator", true, false, false, true, "live_skills", 12, null);
        featureStatusArr[1] = featureStatusSnapshot$st("aiRouter", aiRouter != null, aiRouter != null, true, true, "chat_path");
        featureStatusArr[2] = featureStatusSnapshot$st$default("planner", aiPlanner != null, false, false, false, "compound_boundary", 28, null);
        featureStatusArr[3] = featureStatusSnapshot$st$default("agentLoop", agentExecutionLoop != null, false, false, false, "compound_dry_run", 28, null);
        featureStatusArr[4] = featureStatusSnapshot$st$default("verifier", resultVerifier != null, false, false, true, "soft_verify", 12, null);
        featureStatusArr[5] = featureStatusSnapshot$st$default("catalogDiscovery", catalogToolDiscovery != null, false, false, true, "log+plan", 12, null);
        featureStatusArr[6] = featureStatusSnapshot$st$default("memory2", memory2Store != null, false, false, false, "not_on_live_path", 28, null);
        featureStatusArr[7] = featureStatusSnapshot$st$default("vision", visionRouter != null, false, false, false, "stub_not_live", 28, null);
        featureStatusArr[8] = featureStatusSnapshot$st$default("proactiveAgent", proactiveAgent != null, false, false, false, "not_companion_ui", 28, null);
        featureStatusArr[9] = featureStatusSnapshot$st$default("companionProactive", true, false, false, true, "dashboard_tick", 12, null);
        featureStatusArr[10] = featureStatusSnapshot$st$default("hybridAsr", asrRouter != null, false, false, false, "v2-08_" + CloudAsrFactory.INSTANCE.cloudConfiguredLabel(app), 28, null);
        featureStatusArr[11] = featureStatusSnapshot$st$default("hybridTts", ttsRouter != null, false, false, false, "v2-09_" + CloudTtsFactory.INSTANCE.cloudConfiguredLabel(app), 28, null);
        featureStatusArr[12] = featureStatusSnapshot$st$default("streaming", true, false, false, false, "v2_07_real_sse_chat_when_cloud", 28, null);
        featureStatusArr[13] = featureStatusSnapshot$st$default("remote", remoteDeviceRegistry != null, false, false, false, "empty_registry", 28, null);
        featureStatusArr[14] = featureStatusSnapshot$st$default("plugins", pluginRegistry != null, false, false, false, "empty", 28, null);
        featureStatusArr[15] = featureStatusSnapshot$st$default("mcp", mcpRegistry != null, false, false, false, "empty", 28, null);
        featureStatusArr[16] = featureStatusSnapshot$st$default("filesAgent", filesAgent != null, false, false, false, "planning_only", 28, null);
        featureStatusArr[17] = featureStatusSnapshot$st$default("browserAgent", browserAgent != null, false, false, false, "planning_only", 28, null);
        featureStatusArr[18] = featureStatusSnapshot$st$default(JarvisActivity.ROUTINES, routineStore != null, false, false, false, "not_voice_driven", 28, null);
        featureStatusArr[19] = featureStatusSnapshot$st("cloudLlm", cloudOk, cloudOk, JarvisSettings.INSTANCE.preferCloudLlm(app), cloudOk, "dialogue");
        featureStatusArr[20] = featureStatusSnapshot$st$default("longTermMemory", memoryInstance != null, false, false, true, null, 44, null);
        return new FeatureStatusSnapshot(jCurrentTimeMillis, CollectionsKt.listOf((Object[]) featureStatusArr));
    }

    static /* synthetic */ FeatureStatus featureStatusSnapshot$st$default(String str, boolean z, boolean z2, boolean z3, boolean z4, String str2, int i, Object obj) {
        boolean z5 = (i & 4) != 0 ? z : z2;
        return featureStatusSnapshot$st(str, z, z5, (i & 8) != 0 ? z5 : z3, (i & 16) != 0 ? false : z4, (i & 32) != 0 ? "" : str2);
    }

    private static final FeatureStatus featureStatusSnapshot$st(String id, boolean constructed, boolean available, boolean enabled, boolean active, String note) {
        FeatureLifecycle life;
        if (!constructed) {
            life = FeatureLifecycle.DISABLED;
        } else if (active) {
            life = FeatureLifecycle.ACTIVE_ON_LIVE_PATH;
        } else if (enabled && available) {
            life = FeatureLifecycle.ENABLED;
        } else {
            life = available ? FeatureLifecycle.AVAILABLE : FeatureLifecycle.CONSTRUCTED;
        }
        return new FeatureStatus(id, life, note);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final AssistantOrchestrator build(Context context) throws Throwable {
        ReentrantLock reentrantLock;
        Object objM392constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        ReentrantLock reentrantLock2 = lock;
        reentrantLock2.lock();
        try {
            AssistantOrchestrator assistantOrchestrator = cached;
            if (assistantOrchestrator != null) {
                reentrantLock2.unlock();
                return assistantOrchestrator;
            }
            final Context applicationContext = context.getApplicationContext();
            appContext = applicationContext;
            Intrinsics.checkNotNull(applicationContext);
            PrefsCustomRoutines prefsCustomRoutines = new PrefsCustomRoutines(applicationContext);
            ToolRegistry toolRegistry = new ToolRegistry();
            toolRegistry.register(new FlashlightTool(applicationContext));
            toolRegistry.register(new VolumeTool(applicationContext));
            toolRegistry.register(new BrightnessTool(applicationContext));
            toolRegistry.register(new TimerTool(applicationContext));
            toolRegistry.register(new CalendarTool(applicationContext));
            toolRegistry.register(new SmsTool(applicationContext));
            toolRegistry.register(new TelegramSendTool(applicationContext));
            toolRegistry.register(new LauncherTool(applicationContext));
            toolRegistry.register(new CallTool(applicationContext));
            toolRegistry.register(new ContactsTool(applicationContext));
            toolRegistry.register(new FilesTool(applicationContext));
            toolRegistry.register(new SystemSettingTool(applicationContext));
            toolRegistry.register(new NotificationTool());
            toolRegistry.register(new NotificationReplyTool());
            toolRegistry.register(new ClipboardTool(applicationContext));
            toolRegistry.register(new ScreenshotTool(applicationContext));
            toolRegistry.register(new UiClickTool(applicationContext));
            toolRegistry.register(new UiTypeTool(applicationContext));
            toolRegistry.register(new UiSwipeTool(applicationContext));
            toolRegistry.register(new UiGlobalTool(applicationContext));
            toolRegistry.register(new UiReadTool(applicationContext));
            toolRegistry.register(new UiWaitTool(applicationContext));
            toolRegistry.register(new WeatherTool(applicationContext));
            toolRegistry.register(new SystemStatusTool(applicationContext));
            AndroidReminderBook androidReminderBook = new AndroidReminderBook(applicationContext);
            androidReminderBook.rearm();
            int i = 2;
            toolRegistry.register(new ReminderTool(androidReminderBook, null, i, 0 == true ? 1 : 0));
            toolRegistry.register(new BriefingTool(new BrainGraph$build$1$toolRegistry$1$1(toolRegistry), 0 == true ? 1 : 0, i, 0 == true ? 1 : 0));
            PrefsPlaceBook prefsPlaceBook = new PrefsPlaceBook(applicationContext);
            toolRegistry.register(DeviceIntentToolsKt.navigationTool(applicationContext, prefsPlaceBook));
            toolRegistry.register(new PlacesTool(prefsPlaceBook));
            toolRegistry.register(new NotesTool(new PrefsNoteBook(applicationContext)));
            toolRegistry.register(DeviceIntentToolsKt.appActionTool(applicationContext));
            toolRegistry.register(new CustomRoutineTool(prefsCustomRoutines, new PrefsEventRoutines(applicationContext)));
            toolRegistry.register(DeviceIntentToolsKt.webSearchTool(applicationContext));
            toolRegistry.register(DeviceIntentToolsKt.cameraTool(applicationContext));
            toolRegistry.register(InfoToolsKt.newsTool());
            toolRegistry.register(InfoToolsKt.ratesTool());
            toolRegistry.register(InfoToolsKt.locationTool(applicationContext));
            toolRegistry.register(new PhoneControlTool(applicationContext));
            toolRegistry.register(new AnnounceSettingsTool(new Function2() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda38
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BrainGraph.build$lambda$0$1$0(applicationContext, (String) obj, ((Boolean) obj2).booleanValue());
                }
            }));
            toolRegistry.register(new MusicTool(applicationContext));
            toolRegistry.register(new KnowledgeTool());
            CapabilityRegistry capabilityRegistryBuildFromTools = ToolCapabilityCatalog.INSTANCE.buildFromTools(toolRegistry);
            int i2 = 0;
            CapabilityDiscovery capabilityDiscovery2 = new CapabilityDiscovery(capabilityRegistryBuildFromTools, i2, i, 0 == true ? 1 : 0);
            DefaultToolExecutor defaultToolExecutor = new DefaultToolExecutor(toolRegistry);
            CapabilityExecutor capabilityExecutor2 = new CapabilityExecutor(capabilityRegistryBuildFromTools, defaultToolExecutor, null, 4, null);
            CapabilityAwareToolExecutor capabilityAwareToolExecutor = new CapabilityAwareToolExecutor(defaultToolExecutor, capabilityRegistryBuildFromTools, null, 4, null);
            capabilityRegistry = capabilityRegistryBuildFromTools;
            capabilityDiscovery = capabilityDiscovery2;
            capabilityExecutor = capabilityExecutor2;
            toolExecutor = capabilityAwareToolExecutor;
            CatalogToolRegistry catalogToolRegistry2 = new CatalogToolRegistry();
            CatalogToolRegistryAdapter.importDefinitionsFromCapabilityRegistry$default(CatalogToolRegistryAdapter.INSTANCE, catalogToolRegistry2, capabilityRegistryBuildFromTools, null, 4, null);
            CatalogToolRegistryAdapter.importExecutablesFromToolRegistry$default(CatalogToolRegistryAdapter.INSTANCE, catalogToolRegistry2, toolRegistry, null, null, 12, null);
            CatalogToolDiscovery catalogToolDiscovery2 = new CatalogToolDiscovery(catalogToolRegistry2, i2, i, 0 == true ? 1 : 0);
            catalogToolRegistry = catalogToolRegistry2;
            catalogToolDiscovery = catalogToolDiscovery2;
            aiPlanner = new DeterministicAIPlanner();
            agentExecutionLoop = new AgentExecutionLoop(new AutonomyActionPipeline(null, null, null, 7, null), capabilityAwareToolExecutor, new SafeAutonomousExecutor(capabilityAwareToolExecutor, new AutonomyActionPipeline(null, null, null, 7, null)));
            resultVerifier = new DefaultResultVerifier();
            pluginRegistry = new PluginRegistry();
            mcpRegistry = new McpRegistry();
            filesAgent = new FilesAgent();
            browserAgent = new BrowserAgent();
            routineStore = new RoutineStore();
            char c = 1;
            routineGenerator = new RoutineGenerator(null, 1, 0 == true ? 1 : 0);
            memory2Store = new Memory2Store();
            asrRouter = new AsrRouter(new LocalAsrStub(null, true, 1, null), new AsrProvider(applicationContext) { // from class: com.jarvis.app.BrainGraph$build$1$2
                final /* synthetic */ Context $ctx;
                private final String id;
                private final VoiceProviderKind kind;

                {
                    this.$ctx = applicationContext;
                    CloudAsrFactory cloudAsrFactory = CloudAsrFactory.INSTANCE;
                    Intrinsics.checkNotNull(applicationContext);
                    this.id = cloudAsrFactory.cloudBackend(applicationContext).getId();
                    this.kind = VoiceProviderKind.CLOUD;
                }

                @Override // com.jarvis.core.voice.AsrProvider
                public String getId() {
                    return this.id;
                }

                @Override // com.jarvis.core.voice.AsrProvider
                public VoiceProviderKind getKind() {
                    return this.kind;
                }

                @Override // com.jarvis.core.voice.AsrProvider
                public VoiceProviderAvailability availability() {
                    CloudAsrFactory cloudAsrFactory = CloudAsrFactory.INSTANCE;
                    Context context2 = this.$ctx;
                    Intrinsics.checkNotNull(context2);
                    return cloudAsrFactory.cloudBackend(context2).availability();
                }

                @Override // com.jarvis.core.voice.AsrProvider
                public String transcribe(byte[] pcm16le, int sampleRateHz) {
                    Intrinsics.checkNotNullParameter(pcm16le, "pcm16le");
                    return null;
                }
            });
            ttsRouter = new TtsRouter(new LocalTtsStub(null, 1 == true ? 1 : 0, 1 == true ? 1 : 0, 0 == true ? 1 : 0), new TtsProvider(applicationContext) { // from class: com.jarvis.app.BrainGraph$build$1$3
                final /* synthetic */ Context $ctx;
                private final String id;
                private final VoiceProviderKind kind;

                {
                    this.$ctx = applicationContext;
                    CloudTtsFactory cloudTtsFactory = CloudTtsFactory.INSTANCE;
                    Intrinsics.checkNotNull(applicationContext);
                    this.id = cloudTtsFactory.cloudBackend(applicationContext).getId();
                    this.kind = VoiceProviderKind.CLOUD;
                }

                @Override // com.jarvis.core.voice.TtsProvider
                public String getId() {
                    return this.id;
                }

                @Override // com.jarvis.core.voice.TtsProvider
                public VoiceProviderKind getKind() {
                    return this.kind;
                }

                @Override // com.jarvis.core.voice.TtsProvider
                public VoiceProviderAvailability availability() {
                    CloudTtsFactory cloudTtsFactory = CloudTtsFactory.INSTANCE;
                    Context context2 = this.$ctx;
                    Intrinsics.checkNotNull(context2);
                    return cloudTtsFactory.cloudBackend(context2).availability();
                }

                @Override // com.jarvis.core.voice.TtsProvider
                public byte[] synthesize(String text) {
                    Intrinsics.checkNotNullParameter(text, "text");
                    return null;
                }
            });
            boolean z = false;
            visionRouter = new VisionRouter(new LocalVisionStub(null, z, 3, 0 == true ? 1 : 0), new CloudVisionStub(0 == true ? 1 : 0, z, 1 == true ? 1 : 0, 0 == true ? 1 : 0));
            AgentExecutionLoop agentExecutionLoop2 = agentExecutionLoop;
            if (agentExecutionLoop2 == null) {
                agentExecutionLoop2 = new AgentExecutionLoop(null, null, null, 7, null);
            }
            proactiveAgent = new ProactiveAgent(null, agentExecutionLoop2, c == true ? 1 : 0, 0 == true ? 1 : 0);
            remoteDeviceRegistry = new RemoteDeviceRegistry();
            int size = NativeAndroidToolCatalog.INSTANCE.size();
            int size2 = NativeAndroidToolCatalog.INSTANCE.liveDeviceSafeIds().size();
            int size3 = NativeAndroidToolCatalog.INSTANCE.liveReadOnlyIds().size();
            PluginRegistry pluginRegistry2 = pluginRegistry;
            int size4 = pluginRegistry2 != null ? pluginRegistry2.size() : 0;
            McpRegistry mcpRegistry2 = mcpRegistry;
            Log.i("Jarvis.Planner", "aiPlanner=deterministic discovery=on agentLoop=on verifier=on nativeTools=" + size + " liveSafe=" + size2 + " liveRead=" + size3 + " plugins=" + size4 + " mcp=" + (mcpRegistry2 != null ? mcpRegistry2.size() : 0) + " filesAgent=on browserAgent=on routines=on memory2=on voiceHybrid=on vision=on proactiveAgent=on remote=on");
            AIProviderRegistry aIProviderRegistry = new AIProviderRegistry();
            OpenAiCompatibleCloudClient openAiCompatibleCloudClient = new OpenAiCompatibleCloudClient(new Function0() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return BrainGraph.build$lambda$0$2(applicationContext);
                }
            }, new Function0() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda21
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return BrainGraph.build$lambda$0$3(applicationContext);
                }
            }, null, null, 0, 0, 60, null);
            final SecureKeyStore secureKeyStore = new SecureKeyStore(applicationContext);
            toolRegistry.register(new AppAgentTool(new BrainGraph$build$1$4(new AppAgentRunner(applicationContext, new Function1() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda23
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return BrainGraph.build$lambda$0$4((String) obj);
                }
            }))));
            toolRegistry.register(new WebAnswerTool(new Function1() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda24
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return BrainGraph.build$lambda$0$5((String) obj);
                }
            }, null, null, 6, null));
            ChainCloudProvider chainCloudProvider2 = new ChainCloudProvider(new CloudLLMProvider(null, null, openAiCompatibleCloudClient, new Function0() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda25
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return BrainGraph.build$lambda$0$6(applicationContext);
                }
            }, null, 0, 0, false, false, false, PointerIconCompat.TYPE_COPY, null), new ChainRouter(new Function0() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda26
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Long.valueOf(System.currentTimeMillis());
                }
            }, null, 2, 0 == true ? 1 : 0), new ChainHealth(new Function0() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda27
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Long.valueOf(System.currentTimeMillis());
                }
            }, null, 2, 0 == true ? 1 : 0), new Function1() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda28
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return BrainGraph.build$lambda$0$9(secureKeyStore, (String) obj);
                }
            }, new Function0() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda29
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return secureKeyStore.providersWithKeys();
                }
            }, null, null, null, 0L, null, 992, null);
            chainCloudProvider = chainCloudProvider2;
            aIProviderRegistry.register(chainCloudProvider2);
            ChatLlmEngine chatLlmEngineChatLlmOrNull = INSTANCE.chatLlmOrNull(applicationContext);
            if (chatLlmEngineChatLlmOrNull != null) {
                aIProviderRegistry.register(new LocalLLMProvider(chatLlmEngineChatLlmOrNull, null, null, 0, 0, 30, null));
                Unit unit = Unit.INSTANCE;
                Unit unit2 = Unit.INSTANCE;
            }
            aiProviderRegistry = aIProviderRegistry;
            aiRouter = new AIRouter(aIProviderRegistry, null, null, new Function0() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return NaraRouterDefaults.MODEL_FAST;
                }
            }, new Function0() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return NaraRouterDefaults.MODEL_QUALITY;
                }
            }, new Function0() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return BrainGraph.build$lambda$0$14(applicationContext);
                }
            }, 6, null);
            reentrantLock = reentrantLock2;
            try {
                Log.i("Jarvis.AIProvider", "providers=" + aIProviderRegistry.size() + " local=" + aIProviderRegistry.localProviders().size() + " cloud=" + aIProviderRegistry.cloudProviders().size() + " available=" + aIProviderRegistry.available().size() + " router=on cloudKey=" + (JarvisSettings.INSTANCE.cloudConfigured(applicationContext) ? "set" : "missing") + " preferCloud=" + JarvisSettings.INSTANCE.preferCloudLlm(applicationContext));
                Log.i("Jarvis.Capability", "registry ready caps=" + capabilityRegistryBuildFromTools.size() + " tools=" + toolRegistry.available().size() + " catalog=" + catalogToolRegistry2.size() + " discovery=on");
                CompanionProactive.INSTANCE.attachSafeExecutor(capabilityAwareToolExecutor, false);
                SkillManifestRegistry skillManifestRegistry = new SkillManifestRegistry();
                Iterator<T> it = new SkillManifestLoader(applicationContext).loadAll().iterator();
                while (it.hasNext()) {
                    skillManifestRegistry.register((SkillManifest) it.next());
                }
                SkillRetriever skillRetriever = new SkillRetriever(skillManifestRegistry, 5, null, 4, null);
                final LongTermMemory longTermMemory = new LongTermMemory(applicationContext);
                toolRegistry.register(new ForgetFactTool(new Function0() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return BrainGraph.build$lambda$0$16(longTermMemory);
                    }
                }));
                memoryInstance = longTermMemory;
                BrainRouter brainRouterBuildBrainRouter = INSTANCE.buildBrainRouter(applicationContext);
                ConversationContext conversationContext = new ConversationContext(0, 1, null);
                INSTANCE.seedDialogueHistory(conversationContext);
                dialogueContext = conversationContext;
                RiskBasedPolicy riskBasedPolicy = new RiskBasedPolicy();
                DialogueResponder dialogueResponder = new DialogueResponder(applicationContext, JarvisSettings.INSTANCE.persona(applicationContext), new Function0() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return BrainGraph.build$lambda$0$18(applicationContext);
                    }
                });
                float fBrainConfidenceThreshold = JarvisSettings.INSTANCE.brainConfidenceThreshold(applicationContext);
                boolean zMemoryEnabled = JarvisSettings.INSTANCE.memoryEnabled(applicationContext);
                CapabilityAwareToolExecutor capabilityAwareToolExecutor2 = capabilityAwareToolExecutor;
                AutonomyActionPipeline autonomyActionPipeline = new AutonomyActionPipeline(null, null, null, 7, null);
                DefaultResultVerifier defaultResultVerifier = resultVerifier;
                if (defaultResultVerifier == null) {
                    defaultResultVerifier = new DefaultResultVerifier();
                }
                ExecutionOrchestrator executionOrchestrator = new ExecutionOrchestrator(capabilityAwareToolExecutor2, autonomyActionPipeline, null, defaultResultVerifier, null, new Function0() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(BrainGraph.build$lambda$0$19(applicationContext));
                    }
                }, 20, null);
                DeterministicAIPlanner deterministicAIPlanner = aiPlanner;
                if (deterministicAIPlanner == null) {
                    deterministicAIPlanner = new DeterministicAIPlanner();
                }
                AIPlanner aIPlanner = deterministicAIPlanner;
                AgentExecutionLoop agentExecutionLoop3 = agentExecutionLoop;
                if (agentExecutionLoop3 == null) {
                    agentExecutionLoop3 = new AgentExecutionLoop(null, null, null, 7, null);
                }
                PlanPathCoordinator planPathCoordinator = new PlanPathCoordinator(aIPlanner, agentExecutionLoop3);
                LongTermMemory longTermMemory2 = longTermMemory;
                BrainGraph brainGraph = INSTANCE;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM392constructorimpl = Result.m392constructorimpl(CompanionMemory.INSTANCE.get(applicationContext).getRecall());
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m398isFailureimpl(objM392constructorimpl)) {
                    objM392constructorimpl = null;
                }
                LlmContextAssembly llmContextAssembly = new LlmContextAssembly(longTermMemory2, (MemoryRecall) objM392constructorimpl, INSTANCE.memory2Store(), catalogToolDiscovery2, new Function0() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return BrainGraph.build$lambda$0$21();
                    }
                });
                FastSemanticClassifier fastSemanticClassifier = new FastSemanticClassifier();
                LlmStructuredSemanticInterpreter llmStructuredSemanticInterpreter = new LlmStructuredSemanticInterpreter(new Function0() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return BrainGraph.build$lambda$0$22(applicationContext);
                    }
                }, new Function0() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return BrainGraph.build$lambda$0$23();
                    }
                }, new Function0() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(BrainGraph.build$lambda$0$24(applicationContext));
                    }
                }, new Function0() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(BrainGraph.build$lambda$0$25());
                    }
                }, null, null, 48, null);
                AIRouter aiRouter2 = INSTANCE.aiRouter();
                AssistantOrchestrator assistantOrchestrator2 = new AssistantOrchestrator(brainRouterBuildBrainRouter, skillRetriever, toolRegistry, capabilityAwareToolExecutor, riskBasedPolicy, conversationContext, longTermMemory, dialogueResponder, fBrainConfidenceThreshold, 0.4f, zMemoryEnabled, new Function1() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return BrainGraph.build$lambda$0$27(longTermMemory, (String) obj);
                    }
                }, new Function1() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return BrainGraph.build$lambda$0$28(applicationContext, (String) obj);
                    }
                }, capabilityDiscovery2, executionOrchestrator, planPathCoordinator, catalogToolDiscovery2, fastSemanticClassifier, llmStructuredSemanticInterpreter, null, llmContextAssembly, new Function0() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda16
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return BrainGraph.build$lambda$0$29(applicationContext);
                    }
                }, new Function0() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda17
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return BrainGraph.build$lambda$0$30(applicationContext);
                    }
                }, new Function0() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda18
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(BrainGraph.build$lambda$0$31(applicationContext));
                    }
                }, aiRouter2 != null ? new ChatStreamPipeline(aiRouter2) : null, new Function0() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda19
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(BrainGraph.build$lambda$0$32(applicationContext));
                    }
                }, new Function0() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda20
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(BrainGraph.build$lambda$0$33(applicationContext));
                    }
                }, new BrainGraph$build$1$orchestrator$10(JarvisStyle.INSTANCE), new BrainGraph$build$1$orchestrator$11(prefsCustomRoutines), 524288, null);
                cached = assistantOrchestrator2;
                reentrantLock.unlock();
                return assistantOrchestrator2;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            reentrantLock = reentrantLock2;
        }
        Throwable th4 = th;
        reentrantLock.unlock();
        throw th4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit build$lambda$0$1$0(Context $ctx, String kind, boolean on) {
        Intrinsics.checkNotNullParameter(kind, "kind");
        JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
        Intrinsics.checkNotNull($ctx);
        jarvisSettings.setAnnounce($ctx, kind, on);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String build$lambda$0$2(Context $ctx) {
        JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
        Intrinsics.checkNotNull($ctx);
        return jarvisSettings.cloudBaseUrl($ctx);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String build$lambda$0$3(Context $ctx) {
        JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
        Intrinsics.checkNotNull($ctx);
        return jarvisSettings.cloudApiKey($ctx);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String build$lambda$0$4(String prompt) {
        Intrinsics.checkNotNullParameter(prompt, "prompt");
        ChainCloudProvider chainCloudProvider2 = chainCloudProvider;
        if (chainCloudProvider2 != null) {
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            AICompletionResult aICompletionResultComplete = chainCloudProvider2.complete(new AICompletionRequest(string, prompt, null, null, null, false, false, null, AiTaskType.GENERAL, null, 764, null));
            if (aICompletionResultComplete != null) {
                return aICompletionResultComplete.getText();
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String build$lambda$0$5(String prompt) {
        Intrinsics.checkNotNullParameter(prompt, "prompt");
        ChainCloudProvider chainCloudProvider2 = chainCloudProvider;
        if (chainCloudProvider2 != null) {
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            AICompletionResult aICompletionResultComplete = chainCloudProvider2.complete(new AICompletionRequest(string, prompt, null, null, null, false, false, null, AiTaskType.GENERAL, null, 764, null));
            if (aICompletionResultComplete != null) {
                return aICompletionResultComplete.getText();
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String build$lambda$0$6(Context $ctx) {
        JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
        Intrinsics.checkNotNull($ctx);
        return jarvisSettings.cloudModelForProfile($ctx);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String build$lambda$0$9(SecureKeyStore $keyStore, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return $keyStore.getKey(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String build$lambda$0$14(Context $ctx) {
        String modelId;
        BrainGraph brainGraph = INSTANCE;
        Intrinsics.checkNotNull($ctx);
        ChatLlmEngine chatLlmEngineChatLlmOrNull = brainGraph.chatLlmOrNull($ctx);
        ModelNamed modelNamed = chatLlmEngineChatLlmOrNull instanceof ModelNamed ? (ModelNamed) chatLlmEngineChatLlmOrNull : null;
        return (modelNamed == null || (modelId = modelNamed.getModelId()) == null) ? "local.gguf" : modelId;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LongTermMemoryInterface build$lambda$0$16(LongTermMemory $memory) {
        return $memory;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ChatLlmEngine build$lambda$0$18(final Context $ctx) {
        return new RoutedChatLlmEngine(new Function0() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda33
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return BrainGraph.build$lambda$0$18$0($ctx);
            }
        }, new Function0() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda34
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return BrainGraph.build$lambda$0$18$1();
            }
        }, new Function0() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda35
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(BrainGraph.build$lambda$0$18$2($ctx));
            }
        }, new Function0() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda36
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(BrainGraph.build$lambda$0$18$3($ctx));
            }
        }, new Function0() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda37
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return BrainGraph.build$lambda$0$18$4($ctx);
            }
        }, null, 32, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ChatLlmEngine build$lambda$0$18$0(Context $ctx) {
        BrainGraph brainGraph = INSTANCE;
        Intrinsics.checkNotNull($ctx);
        return brainGraph.chatLlmOrNull($ctx);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AIRouter build$lambda$0$18$1() {
        return INSTANCE.aiRouter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean build$lambda$0$18$2(Context $ctx) {
        CloudAsrFactory cloudAsrFactory = CloudAsrFactory.INSTANCE;
        Intrinsics.checkNotNull($ctx);
        return cloudAsrFactory.isOnline($ctx);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean build$lambda$0$18$3(Context $ctx) {
        JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
        Intrinsics.checkNotNull($ctx);
        return jarvisSettings.preferCloudLlm($ctx) && JarvisSettings.INSTANCE.cloudConfigured($ctx);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AIRequestComplexity build$lambda$0$18$4(Context $ctx) {
        JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
        Intrinsics.checkNotNull($ctx);
        return Intrinsics.areEqual(jarvisSettings.chatProfile($ctx), "QUALITY") ? AIRequestComplexity.COMPLEX : AIRequestComplexity.SIMPLE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String build$lambda$0$27(LongTermMemory $memory, String query) {
        Object objM392constructorimpl;
        Intrinsics.checkNotNullParameter(query, "query");
        BrainGraph brainGraph = INSTANCE;
        try {
            Result.Companion companion = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(CollectionsKt.joinToString$default(LongTermMemoryInterface.retrieve$default($memory, query, 0, 2, null), "; ", null, null, 0, null, new Function1() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda31
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return BrainGraph.build$lambda$0$27$0$0((LongTermMemory.MemoryFact) obj);
                }
            }, 30, null));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = "";
        }
        return (String) objM392constructorimpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence build$lambda$0$27$0$0(LongTermMemory.MemoryFact it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getText();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List build$lambda$0$28(Context $ctx, String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        ContactResolver contactResolver = ContactResolver.INSTANCE;
        Intrinsics.checkNotNull($ctx);
        Iterable $this$map$iv = ContactResolver.resolveAll$default(contactResolver, $ctx, query, 0, 4, null);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            ContactResolver.Match it = (ContactResolver.Match) item$iv$iv;
            destination$iv$iv.add(new PendingAction.Choice(it.getName(), it.getNumber()));
        }
        return (List) destination$iv$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean build$lambda$0$19(Context $ctx) {
        JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
        Intrinsics.checkNotNull($ctx);
        return jarvisSettings.confirmSensitive($ctx);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PhoneContext build$lambda$0$21() {
        return PhoneContextBus.INSTANCE.current();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String build$lambda$0$29(Context $ctx) {
        JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
        Intrinsics.checkNotNull($ctx);
        return jarvisSettings.persona($ctx);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String build$lambda$0$30(Context $ctx) {
        JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
        Intrinsics.checkNotNull($ctx);
        return jarvisSettings.chatProfile($ctx);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean build$lambda$0$31(Context $ctx) {
        Object objM392constructorimpl;
        BrainGraph brainGraph = INSTANCE;
        try {
            Result.Companion companion = Result.INSTANCE;
            CloudAsrFactory cloudAsrFactory = CloudAsrFactory.INSTANCE;
            Intrinsics.checkNotNull($ctx);
            objM392constructorimpl = Result.m392constructorimpl(Boolean.valueOf(cloudAsrFactory.isOnline($ctx)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = false;
        }
        boolean online = ((Boolean) objM392constructorimpl).booleanValue();
        if (online) {
            JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
            Intrinsics.checkNotNull($ctx);
            if (jarvisSettings.preferCloudLlm($ctx) && JarvisSettings.INSTANCE.cloudConfigured($ctx)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ChatLlmEngine build$lambda$0$22(Context $ctx) {
        BrainGraph brainGraph = INSTANCE;
        Intrinsics.checkNotNull($ctx);
        return brainGraph.chatLlmOrNull($ctx);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AIRouter build$lambda$0$23() {
        return INSTANCE.aiRouter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean build$lambda$0$24(Context $ctx) {
        CloudAsrFactory cloudAsrFactory = CloudAsrFactory.INSTANCE;
        Intrinsics.checkNotNull($ctx);
        return cloudAsrFactory.isOnline($ctx);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean build$lambda$0$25() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean build$lambda$0$32(Context $ctx) {
        CloudAsrFactory cloudAsrFactory = CloudAsrFactory.INSTANCE;
        Intrinsics.checkNotNull($ctx);
        return cloudAsrFactory.isOnline($ctx);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean build$lambda$0$33(Context $ctx) {
        JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
        Intrinsics.checkNotNull($ctx);
        return jarvisSettings.preferCloudLlm($ctx) && JarvisSettings.INSTANCE.cloudConfigured($ctx);
    }

    public final void warmLlmBrain(Context context) throws Throwable {
        Intrinsics.checkNotNullParameter(context, "context");
        final Context app = context.getApplicationContext();
        Intrinsics.checkNotNull(app);
        build(app);
        new Thread(new Runnable() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda30
            @Override // java.lang.Runnable
            public final void run() {
                BrainGraph.warmLlmBrain$lambda$0(app);
            }
        }, "llm-warm").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void warmLlmBrain$lambda$0(Context $app) {
        Process.setThreadPriority(-1);
        Log.i("Jarvis.ChatLlm", "warm start (chat only, tool lazy)");
        BrainGraph brainGraph = INSTANCE;
        Intrinsics.checkNotNull($app);
        ChatLlmEngine chatLlmEngineChatLlmOrNull = brainGraph.chatLlmOrNull($app);
        LlamaChatLlmEngine llamaChatLlmEngine = chatLlmEngineChatLlmOrNull instanceof LlamaChatLlmEngine ? (LlamaChatLlmEngine) chatLlmEngineChatLlmOrNull : null;
        if (llamaChatLlmEngine != null) {
            llamaChatLlmEngine.warm();
        }
    }

    public final void warmChatLlm(Context context) throws Throwable {
        Intrinsics.checkNotNullParameter(context, "context");
        final Context app = context.getApplicationContext();
        Intrinsics.checkNotNull(app);
        build(app);
        if (chatModelFile(app) == null) {
            return;
        }
        new Thread(new Runnable() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                BrainGraph.warmChatLlm$lambda$0(app);
            }
        }, "chat-llm-warm").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void warmChatLlm$lambda$0(Context $app) {
        Process.setThreadPriority(-1);
        Log.i("Jarvis.ChatLlm", "warm start (priority)");
        BrainGraph brainGraph = INSTANCE;
        Intrinsics.checkNotNull($app);
        ChatLlmEngine chatLlmEngineChatLlmOrNull = brainGraph.chatLlmOrNull($app);
        LlamaChatLlmEngine llamaChatLlmEngine = chatLlmEngineChatLlmOrNull instanceof LlamaChatLlmEngine ? (LlamaChatLlmEngine) chatLlmEngineChatLlmOrNull : null;
        if (llamaChatLlmEngine != null) {
            llamaChatLlmEngine.warm();
        }
    }

    public final void preloadChatModel(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        final Context app = context.getApplicationContext();
        JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
        Intrinsics.checkNotNull(app);
        if (jarvisSettings.chatPreloadEnabled(app) && chatModelFile(app) != null) {
            new Thread(new Runnable() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    BrainGraph.preloadChatModel$lambda$0(app);
                }
            }, "chat-preload").start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void preloadChatModel$lambda$0(Context $app) {
        Object objM392constructorimpl;
        Process.setThreadPriority(10);
        Log.i("Jarvis.ChatLlm", "eager preload…");
        BrainGraph $this$preloadChatModel_u24lambda_u240_u240 = INSTANCE;
        try {
            Result.Companion companion = Result.INSTANCE;
            Intrinsics.checkNotNull($app);
            ChatLlmEngine chatLlmEngineChatLlmOrNull = $this$preloadChatModel_u24lambda_u240_u240.chatLlmOrNull($app);
            Unit unit = null;
            LlamaChatLlmEngine llamaChatLlmEngine = chatLlmEngineChatLlmOrNull instanceof LlamaChatLlmEngine ? (LlamaChatLlmEngine) chatLlmEngineChatLlmOrNull : null;
            if (llamaChatLlmEngine != null) {
                llamaChatLlmEngine.preload();
                unit = Unit.INSTANCE;
            }
            objM392constructorimpl = Result.m392constructorimpl(unit);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        Throwable e = Result.m395exceptionOrNullimpl(objM392constructorimpl);
        if (e != null) {
            Log.w("Jarvis.ChatLlm", "eager preload failed: " + e.getMessage());
        }
    }

    private final void seedDialogueHistory(ConversationContext context) {
        if (ConversationJournal.INSTANCE.isInstalled()) {
            long startedAt = System.currentTimeMillis();
            try {
                Result.Companion companion = Result.INSTANCE;
                BrainGraph brainGraph = this;
                List<ConversationJournal.Entry> listRecent = ConversationJournal.INSTANCE.recent(20, RetentionPolicy.RECENT_WINDOW_MS);
                List<ConversationJournal.Entry> $this$forEach$iv = listRecent;
                for (Object element$iv : $this$forEach$iv) {
                    ConversationJournal.Entry e = (ConversationJournal.Entry) element$iv;
                    if (Intrinsics.areEqual(e.getRole(), JarvisSchema.ROLE_USER)) {
                        context.addUser(e.getText());
                    } else {
                        context.addAssistant(e.getText());
                    }
                }
                Result.m392constructorimpl(Integer.valueOf(Log.i("Jarvis.ChatHistory", "context restored n=" + listRecent.size() + " in " + (System.currentTimeMillis() - startedAt) + "ms")));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m392constructorimpl(ResultKt.createFailure(th));
            }
        }
    }

    public final void clearDialogueHistory() {
        ConversationContext conversationContext = dialogueContext;
        if (conversationContext != null) {
            conversationContext.clear();
        }
    }

    public final LongTermMemory memory(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ReentrantLock reentrantLock = lock;
        reentrantLock.lock();
        try {
            LongTermMemory it = memoryInstance;
            if (it == null) {
                Context applicationContext = context.getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                it = new LongTermMemory(applicationContext);
                memoryInstance = it;
            }
            return it;
        } finally {
            reentrantLock.unlock();
        }
    }

    public final void shutdownLlmBrain() {
        LlmBrain llmBrain2 = llmBrain;
        if (llmBrain2 != null) {
            llmBrain2.shutdown();
        }
        llmBrain = null;
    }

    public final void shutdownChatLlm() {
        LlamaChatLlmEngine llamaChatLlmEngine = chatLlm;
        if (llamaChatLlmEngine != null) {
            llamaChatLlmEngine.unload();
        }
        chatLlm = null;
    }

    public final void invalidate() {
        ReentrantLock reentrantLock = lock;
        reentrantLock.lock();
        try {
            cached = null;
            capabilityRegistry = null;
            capabilityDiscovery = null;
            capabilityExecutor = null;
            catalogToolRegistry = null;
            catalogToolDiscovery = null;
            aiProviderRegistry = null;
            chainCloudProvider = null;
            aiRouter = null;
            aiPlanner = null;
            agentExecutionLoop = null;
            resultVerifier = null;
            pluginRegistry = null;
            mcpRegistry = null;
            filesAgent = null;
            browserAgent = null;
            routineStore = null;
            routineGenerator = null;
            memory2Store = null;
            asrRouter = null;
            ttsRouter = null;
            visionRouter = null;
            proactiveAgent = null;
            remoteDeviceRegistry = null;
            toolExecutor = null;
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    public final void invalidateChatLlm() {
        shutdownChatLlm();
    }

    public final void onCloudKeyChanged(String providerId) {
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        ChainCloudProvider chainCloudProvider2 = chainCloudProvider;
        if (chainCloudProvider2 != null) {
            chainCloudProvider2.onKeyChanged(providerId);
        }
    }

    public final ChatLlmEngine chatLlmOrNull(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        final Context app = context.getApplicationContext();
        Intrinsics.checkNotNull(app);
        File file = chatModelFile(app);
        if (file == null) {
            return null;
        }
        LlamaChatLlmEngine existing = chatLlm;
        if (existing != null && existing.isReady() && Intrinsics.areEqual(existing.getModelPath(), file.getAbsolutePath())) {
            return existing;
        }
        ReentrantLock reentrantLock = lock;
        reentrantLock.lock();
        try {
            LlamaChatLlmEngine again = chatLlm;
            if (again != null && again.isReady() && Intrinsics.areEqual(again.getModelPath(), file.getAbsolutePath())) {
                return again;
            }
            if (again != null) {
                again.unload();
            }
            LlamaChatLlmEngine engine = new LlamaChatLlmEngine(file, new Function0() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda32
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return BrainGraph.chatLlmOrNull$lambda$0$0(app);
                }
            });
            chatLlm = engine;
            AIProviderRegistry reg = aiProviderRegistry;
            if (reg != null) {
                reg.unregister(LocalLLMProvider.ID);
                reg.register(new LocalLLMProvider(engine, null, null, 0, 0, 30, null));
            }
            Log.i("Jarvis.ChatLlm", "bound chat model=" + file.getName() + " profile=" + JarvisSettings.INSTANCE.chatProfile(app));
            return engine;
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String chatLlmOrNull$lambda$0$0(Context $app) {
        JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
        Intrinsics.checkNotNull($app);
        return jarvisSettings.chatProfile($app);
    }

    public final File chatModelFile(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        File root = context.getExternalFilesDir(null);
        if (root == null) {
            return null;
        }
        String preferred = JarvisSettings.INSTANCE.prefs(context).getString("active_chat_model", null);
        List $this$chatModelFile_u24lambda_u240 = CollectionsKt.createListBuilder();
        String str = preferred;
        if (!(str == null || StringsKt.isBlank(str))) {
            $this$chatModelFile_u24lambda_u240.add(preferred);
        }
        $this$chatModelFile_u24lambda_u240.addAll(ModelCatalog.INSTANCE.getCHAT_MODEL_CANDIDATES());
        List<String> order = CollectionsKt.distinct(CollectionsKt.build($this$chatModelFile_u24lambda_u240));
        for (String rel : order) {
            File f = new File(root, "models/" + rel);
            if (f.isFile() && f.length() > 50000000) {
                return f;
            }
        }
        return null;
    }

    private final BrainRouter buildBrainRouter(final Context context) {
        Function0 cloudAvailable = new Function0() { // from class: com.jarvis.app.BrainGraph$$ExternalSyntheticLambda22
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(BrainGraph.buildBrainRouter$lambda$0(context));
            }
        };
        RuleBasedBrain ruleBased = new RuleBasedBrain(cloudAvailable);
        boolean llmEnabled = JarvisSettings.INSTANCE.llmEnabled(context);
        File modelFile = getLlmModelPath(context);
        Log.d("Jarvis.LlmBrain", "buildBrainRouter llmEnabled=" + llmEnabled + " model=" + (modelFile != null ? modelFile.getAbsolutePath() : null) + " isFile=" + (modelFile != null ? Boolean.valueOf(modelFile.isFile()) : null));
        if (!llmEnabled || modelFile == null || !modelFile.isFile()) {
            return ruleBased;
        }
        String absolutePath = modelFile.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
        LlmBrain brain = new LlmBrain(absolutePath, ruleBased, context.getCacheDir().getAbsolutePath(), cloudAvailable);
        llmBrain = brain;
        return brain;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean buildBrainRouter$lambda$0(Context $context) {
        return CloudAsrFactory.INSTANCE.isOnline($context) && JarvisSettings.INSTANCE.preferCloudLlm($context) && JarvisSettings.INSTANCE.cloudConfigured($context);
    }

    private final File getLlmModelPath(Context context) {
        File externalDir = new File(context.getExternalFilesDir(null), "models/llm");
        File externalModel = new File(externalDir, LLM_MODEL_NAME);
        if (externalModel.isFile()) {
            return externalModel;
        }
        File internalDir = new File(context.getFilesDir(), "models/llm");
        File internalModel = new File(internalDir, LLM_MODEL_NAME);
        if (internalModel.isFile()) {
            return internalModel;
        }
        return null;
    }
}
