package com.jarvis.brain;

import android.os.SystemClock;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.google.ai.edge.litertlm.Backend;
import com.google.ai.edge.litertlm.Content;
import com.google.ai.edge.litertlm.Contents;
import com.google.ai.edge.litertlm.Conversation;
import com.google.ai.edge.litertlm.ConversationConfig;
import com.google.ai.edge.litertlm.Engine;
import com.google.ai.edge.litertlm.EngineConfig;
import com.google.ai.edge.litertlm.Message;
import com.google.ai.edge.litertlm.NoRepeatNgramConfig;
import com.google.ai.edge.litertlm.RepetitionPenaltyConfig;
import com.google.ai.edge.litertlm.ResponseFormat;
import com.google.ai.edge.litertlm.SamplerConfig;
import com.google.ai.edge.litertlm.SuppressTokensConfig;
import com.google.ai.edge.litertlm.ThinkingConfig;
import com.jarvis.app.VoiceService;
import com.jarvis.app.ui.JarvisActivity;
import com.jarvis.core.common.model.ConfidenceLevel;
import com.jarvis.core.common.model.Intent;
import com.jarvis.core.common.model.IntentType;
import com.jarvis.core.common.model.Slot;
import com.jarvis.core.common.model.UserRequest;
import com.jarvis.core.nlp.Transliterator;
import com.jarvis.core.privacy.SensitivityClassifier;
import com.jarvis.core.routines.EventMatcher;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.sequences.Sequence;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.AbstractCircuitBreaker;

/* JADX INFO: compiled from: LlmBrain.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0010%\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 H2\u00020\u0001:\u0002GHB5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0001\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\u0006\u0010\u0015\u001a\u00020\u0016J\b\u0010\u0017\u001a\u00020\u0016H\u0002J\u0010\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0010H\u0002J,\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u001fH\u0016J>\u0010\"\u001a\u0004\u0018\u00010\u001b2\u0006\u0010#\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u001f2\u0006\u0010$\u001a\u00020\u00032\u0006\u0010%\u001a\u00020\u0003H\u0016J\u0010\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'H\u0002J\u0006\u0010)\u001a\u00020\u0016J\u0006\u0010*\u001a\u00020\bJ6\u0010+\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010,\u001a\u00020\u00032\f\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u001f2\u000e\b\u0002\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\u001fH\u0002J\u0016\u0010-\u001a\u00020\u00032\f\u0010.\u001a\b\u0012\u0004\u0012\u00020!0\u001fH\u0002J\u0010\u0010/\u001a\u00020\u00032\u0006\u00100\u001a\u00020\u0003H\u0002J\u0010\u00101\u001a\u00020\u00032\u0006\u00102\u001a\u00020\u0003H\u0002J\u0010\u00103\u001a\u00020\u00032\u0006\u00104\u001a\u00020\u0003H\u0002J(\u00105\u001a\u0004\u0018\u00010\u001b2\u0006\u00106\u001a\u00020\u00032\f\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u001f2\u0006\u0010,\u001a\u00020\u0003H\u0002J,\u00107\u001a\u00020\u00162\u0006\u00108\u001a\u00020!2\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000e0:2\u0006\u0010,\u001a\u00020\u0003H\u0002J\u0018\u0010;\u001a\u00020\b2\u0006\u0010,\u001a\u00020\u00032\u0006\u0010<\u001a\u00020\u0003H\u0002J\u0010\u0010=\u001a\u00020\u00032\u0006\u0010,\u001a\u00020\u0003H\u0002J\u0016\u0010>\u001a\b\u0012\u0004\u0012\u00020?0\u001f2\u0006\u0010@\u001a\u00020\u0003H\u0002J \u0010A\u001a\u0004\u0018\u00010!2\u0006\u0010B\u001a\u00020\u00032\f\u0010.\u001a\b\u0012\u0004\u0012\u00020!0\u001fH\u0002J\u0010\u0010C\u001a\u00020\u000e2\u0006\u00102\u001a\u00020\u0003H\u0002J\u0010\u0010D\u001a\u00020E2\u0006\u00100\u001a\u00020\u0003H\u0002J\u000e\u0010@\u001a\u00020\u0003*\u0004\u0018\u00010FH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006I"}, d2 = {"Lcom/jarvis/brain/LlmBrain;", "Lcom/jarvis/brain/BrainRouter;", "modelPath", "", "fallback", "cacheDir", "cloudAvailable", "Lkotlin/Function0;", "", "<init>", "(Ljava/lang/String;Lcom/jarvis/brain/BrainRouter;Ljava/lang/String;Lkotlin/jvm/functions/Function0;)V", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "promptCacheLock", "", "engine", "Lcom/google/ai/edge/litertlm/Engine;", "initialized", "initFailed", "cachedPromptKey", "cachedPrompt", "initialize", "", "ensureInitializedLazy", "warmUp", "eng", "route", "Lcom/jarvis/brain/BrainResult;", "request", "Lcom/jarvis/core/common/model/UserRequest;", "history", "", "topSkills", "Lcom/jarvis/brain/BrainRouter$SkillInfo;", "continueAfterTool", "originalRequest", "lastToolId", "lastToolMessage", "elapsed", "", "t0", "shutdown", "isReady", "callModel", "userText", "buildSystemPrompt", JarvisActivity.SKILLS, "functionName", "skillId", "escapeValue", "v", "typeToGemma", "type", "parseFunctionCalls", "raw", "sanitizeArgs", "skill", "args", "", "userTextMentions", "value", "extractAppName", "extractToolCalls", "Lcom/jarvis/brain/LlmBrain$FunctionCall;", VoiceService.EXTRA_TEXT, "matchSkill", "fnName", "castValue", "resolveIntentType", "Lcom/jarvis/core/common/model/IntentType;", "Lcom/google/ai/edge/litertlm/Contents;", "FunctionCall", "Companion", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LlmBrain implements BrainRouter {
    private static final String APP_LAUNCH_SKILL = "app.launch";
    private static final float CONFIDENCE_SKILL_CALL = 0.95f;
    private static final String FUNCTION_CALLING_TRIGGER = "You are a model that can do function calling with the following functions";
    private static final int MAX_OUTPUT_TOKENS = 48;
    private static final float RULES_CHAT_FAST = 0.75f;
    private static final float RULES_CLARIFY_FAST = 0.45f;
    private static final float RULES_SKILL_FAST = 0.82f;
    private static final String TAG = "Jarvis.LlmBrain";
    private final String cacheDir;
    private volatile String cachedPrompt;
    private volatile String cachedPromptKey;
    private final Function0<Boolean> cloudAvailable;
    private volatile Engine engine;
    private final BrainRouter fallback;
    private volatile boolean initFailed;
    private volatile boolean initialized;
    private final ReentrantLock lock;
    private final String modelPath;
    private final Object promptCacheLock;
    private static final Regex PLACEHOLDER_REGEX = new Regex("^\\[.+\\]$");
    private static final Set<String> LAUNCH_VERBS = SetsKt.setOf((Object[]) new String[]{"открой", "открыть", "открывай", "запусти", "запустить", "запускай", "включи", "включить", "покажи", "показать", "загрузи", "загрузить", "launch", AbstractCircuitBreaker.PROPERTY_NAME, "start", "run"});
    private static final List<String> ON_HINTS = CollectionsKt.listOf((Object[]) new String[]{"включи", "зажги", "включить", "turn on", "on"});
    private static final List<String> OFF_HINTS = CollectionsKt.listOf((Object[]) new String[]{"выключи", "погаси", "потуши", "отключи", "выключить", "turn off", "off"});
    private static final Regex CALL_REGEX = new Regex("<start_function_call>call:(\\w+)\\{(.*?)\\}<end_function_call>", RegexOption.DOT_MATCHES_ALL);
    private static final Regex ARG_REGEX = new Regex("(\\w+):(?:<escape>(.*?)<escape>|([^,}]*))", RegexOption.DOT_MATCHES_ALL);

    public LlmBrain(String modelPath, BrainRouter fallback, String cacheDir, Function0<Boolean> cloudAvailable) {
        Intrinsics.checkNotNullParameter(modelPath, "modelPath");
        Intrinsics.checkNotNullParameter(fallback, "fallback");
        Intrinsics.checkNotNullParameter(cloudAvailable, "cloudAvailable");
        this.modelPath = modelPath;
        this.fallback = fallback;
        this.cacheDir = cacheDir;
        this.cloudAvailable = cloudAvailable;
        this.lock = new ReentrantLock();
        this.promptCacheLock = new Object();
        this.cachedPromptKey = "";
    }

    public /* synthetic */ LlmBrain(String str, RuleBasedBrain ruleBasedBrain, String str2, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? new RuleBasedBrain(null, 1, null) : ruleBasedBrain, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? new Function0() { // from class: com.jarvis.brain.LlmBrain$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(LlmBrain._init_$lambda$0());
            }
        } : function0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean _init_$lambda$0() {
        return false;
    }

    public final void initialize() {
        Object objM392constructorimpl;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (this.initialized || this.initFailed) {
                reentrantLock.unlock();
                return;
            }
            try {
                EngineConfig config = new EngineConfig(this.modelPath, new Backend.CPU(null, null, 3, null), null, null, null, null, this.cacheDir, 60, null);
                Engine eng = new Engine(config);
                eng.initialize();
                this.engine = eng;
                this.initialized = true;
                Log.i(TAG, "LiteRT-LM engine initialized: " + this.modelPath);
                try {
                    Result.Companion companion = Result.INSTANCE;
                    LlmBrain $this$initialize_u24lambda_u240_u241 = this;
                    $this$initialize_u24lambda_u240_u241.warmUp(eng);
                    objM392constructorimpl = Result.m392constructorimpl(Unit.INSTANCE);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
                }
                Result.m391boximpl(objM392constructorimpl);
            } catch (Exception e) {
                this.initFailed = true;
                Integer.valueOf(Log.e(TAG, "Failed to initialize LiteRT-LM: " + e.getMessage(), e));
            }
            reentrantLock.unlock();
        } catch (Throwable th2) {
            reentrantLock.unlock();
            throw th2;
        }
    }

    private final void ensureInitializedLazy() {
        if (this.initialized || this.initFailed) {
            return;
        }
        Log.i(TAG, "lazy initialize FunctionGemma on first ambiguous route");
        initialize();
    }

    private final void warmUp(Engine eng) throws Exception {
        long t0 = SystemClock.elapsedRealtime();
        ConversationConfig config = new ConversationConfig(Contents.INSTANCE.of(FUNCTION_CALLING_TRIGGER), null, null, new SamplerConfig(1, 1.0d, 0.0d, 0, 8, null), false, null, null, null, false, 8, null, false, 3574, null);
        Conversation conversationCreateConversation = eng.createConversation(config);
        try {
            Conversation conv = conversationCreateConversation;
            Conversation.sendMessage$default(conv, "ping", (Map) null, (RepetitionPenaltyConfig) null, (NoRepeatNgramConfig) null, (SuppressTokensConfig) null, (Integer) null, (ThinkingConfig) null, (ResponseFormat) null, 254, (Object) null);
            AutoCloseableKt.closeFinally(conversationCreateConversation, null);
            Log.i(TAG, "LLM warm-up done in " + (SystemClock.elapsedRealtime() - t0) + "ms");
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                AutoCloseableKt.closeFinally(conversationCreateConversation, th);
                throw th2;
            }
        }
    }

    @Override // com.jarvis.brain.BrainRouter
    public BrainResult route(UserRequest request, List<String> history, List<BrainRouter.SkillInfo> topSkills) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(history, "history");
        Intrinsics.checkNotNullParameter(topSkills, "topSkills");
        long t0 = SystemClock.elapsedRealtime();
        BrainResult rules = this.fallback.route(request, history, topSkills);
        if (this.cloudAvailable.invoke().booleanValue()) {
            return rules;
        }
        if ((rules instanceof BrainResult.SkillCall) && ((BrainResult.SkillCall) rules).getConfidence() >= RULES_SKILL_FAST) {
            Log.d(TAG, "fast rules-skill '" + SensitivityClassifier.INSTANCE.mask(request.getText()) + "' -> " + ((BrainResult.SkillCall) rules).getIntent().getType() + StringUtils.SPACE + elapsed(t0) + "ms");
            return rules;
        }
        if ((rules instanceof BrainResult.Chat) && ((BrainResult.Chat) rules).getConfidence() >= 0.75f) {
            Log.d(TAG, "fast rules-chat '" + SensitivityClassifier.INSTANCE.mask(request.getText()) + "' " + elapsed(t0) + "ms");
            return rules;
        }
        if ((rules instanceof BrainResult.Clarify) && ((BrainResult.Clarify) rules).getConfidence() >= RULES_CLARIFY_FAST) {
            Log.d(TAG, "fast rules-clarify '" + SensitivityClassifier.INSTANCE.mask(request.getText()) + "' " + elapsed(t0) + "ms");
            return rules;
        }
        if (!this.initialized && !this.initFailed) {
            ensureInitializedLazy();
        }
        if (!this.initialized || this.initFailed) {
            Log.d(TAG, "LLM not ready, using fallback " + elapsed(t0) + "ms");
            return rules;
        }
        Engine eng = this.engine;
        if (eng == null) {
            return rules;
        }
        try {
            String raw = callModel(eng, request.getText(), topSkills, history);
            BrainResult parsed = parseFunctionCalls(raw, topSkills, request.getText());
            long jElapsed = elapsed(t0);
            String strMask = SensitivityClassifier.INSTANCE.mask(request.getText());
            String strTake = StringsKt.take(SensitivityClassifier.INSTANCE.mask(raw), 160);
            List<BrainRouter.SkillInfo> $this$map$iv = topSkills;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            Iterable $this$mapTo$iv$iv = $this$map$iv;
            for (Object item$iv$iv : $this$mapTo$iv$iv) {
                BrainRouter.SkillInfo it = (BrainRouter.SkillInfo) item$iv$iv;
                destination$iv$iv.add(it.getId());
                $this$mapTo$iv$iv = $this$mapTo$iv$iv;
            }
            Log.d(TAG, "llm " + jElapsed + "ms user='" + strMask + "' out='" + strTake + "' skills=" + ((List) destination$iv$iv) + " hit=" + (parsed != null));
            if (parsed != null) {
                return parsed;
            }
            LlmBrain llmBrain = this;
            Log.d(TAG, "No function call in model output, delegate to fallback");
            return rules;
        } catch (Exception e) {
            Log.e(TAG, "LLM route failed: " + e.getMessage());
            return rules;
        }
    }

    @Override // com.jarvis.brain.BrainRouter
    public BrainResult continueAfterTool(UserRequest originalRequest, List<String> history, List<BrainRouter.SkillInfo> topSkills, String lastToolId, String lastToolMessage) {
        Engine eng;
        Intrinsics.checkNotNullParameter(originalRequest, "originalRequest");
        Intrinsics.checkNotNullParameter(history, "history");
        Intrinsics.checkNotNullParameter(topSkills, "topSkills");
        Intrinsics.checkNotNullParameter(lastToolId, "lastToolId");
        Intrinsics.checkNotNullParameter(lastToolMessage, "lastToolMessage");
        if (!this.initialized || this.initFailed || (eng = this.engine) == null) {
            return null;
        }
        if (!Intrinsics.areEqual(lastToolId, "launcher") && !StringsKt.startsWith$default(lastToolId, "ui.", false, 2, (Object) null) && !Intrinsics.areEqual(lastToolId, "system.setting")) {
            return null;
        }
        long t0 = SystemClock.elapsedRealtime();
        try {
            String followUp = "Original user request: " + originalRequest.getText() + "\nLast tool " + lastToolId + " result: " + lastToolMessage + "\nIf another function is needed to finish the request, call it. If done, reply with DONE.";
            String raw = callModel(eng, followUp, topSkills, history);
            try {
                Log.d(TAG, "react " + elapsed(t0) + "ms out='" + StringsKt.take(raw, 160) + "'");
                if (!StringsKt.contains((CharSequence) raw, (CharSequence) "DONE", true) || StringsKt.contains$default((CharSequence) raw, (CharSequence) "<start_function_call>", false, 2, (Object) null)) {
                    return parseFunctionCalls(raw, topSkills, originalRequest.getText());
                }
                return null;
            } catch (Exception e) {
                e = e;
                Log.e(TAG, "react failed: " + e.getMessage());
                return null;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    private final long elapsed(long t0) {
        return SystemClock.elapsedRealtime() - t0;
    }

    public final void shutdown() {
        Unit unit;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            try {
                Result.Companion companion = Result.INSTANCE;
                LlmBrain $this$shutdown_u24lambda_u240_u240 = this;
                Engine engine = $this$shutdown_u24lambda_u240_u240.engine;
                if (engine != null) {
                    engine.close();
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                Result.m392constructorimpl(unit);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m392constructorimpl(ResultKt.createFailure(th));
            }
            this.engine = null;
            this.initialized = false;
            Log.i(TAG, "LiteRT-LM engine shut down");
        } finally {
            reentrantLock.unlock();
        }
    }

    public final boolean isReady() {
        return this.initialized && !this.initFailed;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ String callModel$default(LlmBrain llmBrain, Engine engine, String str, List list, List list2, int i, Object obj) {
        if ((i & 8) != 0) {
            list2 = CollectionsKt.emptyList();
        }
        return llmBrain.callModel(engine, str, list, list2);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x003d A[Catch: all -> 0x0116, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x0116, blocks: (B:5:0x0020, B:13:0x003d), top: B:44:0x0020 }] */
    private final String callModel(Engine eng, String userText, List<BrainRouter.SkillInfo> topSkills, List<String> history) throws Exception {
        String it;
        String skillKey = CollectionsKt.joinToString$default(topSkills, "|", null, null, 0, null, new Function1() { // from class: com.jarvis.brain.LlmBrain$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LlmBrain.callModel$lambda$0((BrainRouter.SkillInfo) obj);
            }
        }, 30, null);
        synchronized (this.promptCacheLock) {
            try {
                if (Intrinsics.areEqual(skillKey, this.cachedPromptKey)) {
                    try {
                        if (this.cachedPrompt != null) {
                            it = this.cachedPrompt;
                            Intrinsics.checkNotNull(it);
                        } else {
                            it = buildSystemPrompt(topSkills);
                            this.cachedPromptKey = skillKey;
                            this.cachedPrompt = it;
                        }
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                } else {
                    it = buildSystemPrompt(topSkills);
                    this.cachedPromptKey = skillKey;
                    this.cachedPrompt = it;
                }
                String systemPrompt = it;
                ConversationConfig config = new ConversationConfig(Contents.INSTANCE.of(systemPrompt), null, null, new SamplerConfig(1, 1.0d, 0.0d, 0, 8, null), false, null, null, null, false, 48, null, false, 3574, null);
                StringBuilder $this$callModel_u24lambda_u242 = new StringBuilder();
                Iterable recent = CollectionsKt.takeLast(history, 4);
                if (!((Collection) recent).isEmpty()) {
                    $this$callModel_u24lambda_u242.append("Context:\n");
                    Iterable $this$forEach$iv = recent;
                    for (Object element$iv : $this$forEach$iv) {
                        $this$callModel_u24lambda_u242.append((String) element$iv).append('\n');
                        skillKey = skillKey;
                    }
                }
                $this$callModel_u24lambda_u242.append(userText);
                String payload = $this$callModel_u24lambda_u242.toString();
                Conversation conversationCreateConversation = eng.createConversation(config);
                try {
                    Conversation conv = conversationCreateConversation;
                    Message response = Conversation.sendMessage$default(conv, payload, (Map) null, (RepetitionPenaltyConfig) null, (NoRepeatNgramConfig) null, (SuppressTokensConfig) null, (Integer) null, (ThinkingConfig) null, (ResponseFormat) null, 254, (Object) null);
                    String strText = text(response.getContents());
                    if (strText == null) {
                        strText = "";
                    }
                    AutoCloseableKt.closeFinally(conversationCreateConversation, null);
                    return strText;
                } catch (Throwable th2) {
                    try {
                        throw th2;
                    } catch (Throwable th3) {
                        AutoCloseableKt.closeFinally(conversationCreateConversation, th2);
                        throw th3;
                    }
                }
            } catch (Throwable th4) {
                th = th4;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence callModel$lambda$0(BrainRouter.SkillInfo it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getId();
    }

    private final String buildSystemPrompt(List<BrainRouter.SkillInfo> skills) {
        StringBuilder $this$buildSystemPrompt_u24lambda_u240 = new StringBuilder();
        $this$buildSystemPrompt_u24lambda_u240.append(FUNCTION_CALLING_TRIGGER);
        for (BrainRouter.SkillInfo skill : skills) {
            $this$buildSystemPrompt_u24lambda_u240.append("<start_function_declaration>");
            $this$buildSystemPrompt_u24lambda_u240.append("declaration:").append(functionName(skill.getId())).append("{");
            $this$buildSystemPrompt_u24lambda_u240.append("description:<escape>").append(escapeValue(skill.getDescription())).append("<escape>,");
            $this$buildSystemPrompt_u24lambda_u240.append("parameters:{properties:{");
            for (BrainRouter.SkillParam p : skill.getParameters()) {
                $this$buildSystemPrompt_u24lambda_u240.append(p.getName()).append(":{");
                $this$buildSystemPrompt_u24lambda_u240.append("description:<escape>").append(escapeValue(p.getDescription())).append("<escape>,");
                if (!p.getAllowedValues().isEmpty()) {
                    $this$buildSystemPrompt_u24lambda_u240.append("enum:[");
                    $this$buildSystemPrompt_u24lambda_u240.append(CollectionsKt.joinToString$default(p.getAllowedValues(), ",", null, null, 0, null, new Function1() { // from class: com.jarvis.brain.LlmBrain$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return LlmBrain.buildSystemPrompt$lambda$0$0((String) obj);
                        }
                    }, 30, null));
                    $this$buildSystemPrompt_u24lambda_u240.append("],");
                }
                $this$buildSystemPrompt_u24lambda_u240.append("type:<escape>").append(typeToGemma(p.getType())).append("<escape>");
                $this$buildSystemPrompt_u24lambda_u240.append("},");
            }
            if (!skill.getParameters().isEmpty()) {
                $this$buildSystemPrompt_u24lambda_u240.deleteCharAt($this$buildSystemPrompt_u24lambda_u240.length() - 1);
            }
            $this$buildSystemPrompt_u24lambda_u240.append("},");
            $this$buildSystemPrompt_u24lambda_u240.append("required:[");
            Iterable $this$filter$iv = skill.getParameters();
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                BrainRouter.SkillParam it = (BrainRouter.SkillParam) element$iv$iv;
                if (it.getRequired()) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            $this$buildSystemPrompt_u24lambda_u240.append(CollectionsKt.joinToString$default((List) destination$iv$iv, ",", null, null, 0, null, new Function1() { // from class: com.jarvis.brain.LlmBrain$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return LlmBrain.buildSystemPrompt$lambda$0$2((BrainRouter.SkillParam) obj);
                }
            }, 30, null));
            $this$buildSystemPrompt_u24lambda_u240.append("],");
            $this$buildSystemPrompt_u24lambda_u240.append("type:<escape>OBJECT<escape>");
            $this$buildSystemPrompt_u24lambda_u240.append("}}");
            $this$buildSystemPrompt_u24lambda_u240.append("<end_function_declaration>");
        }
        return $this$buildSystemPrompt_u24lambda_u240.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence buildSystemPrompt$lambda$0$0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return "<escape>" + it + "<escape>";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence buildSystemPrompt$lambda$0$2(BrainRouter.SkillParam it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return "<escape>" + it.getName() + "<escape>";
    }

    private final String functionName(String skillId) {
        return StringsKt.replace$default(skillId, '.', '_', false, 4, (Object) null);
    }

    private final String escapeValue(String v) {
        return StringsKt.replace$default(StringsKt.replace$default(v, "\\", "\\\\", false, 4, (Object) null), "<escape>", StringUtils.SPACE, false, 4, (Object) null);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:22:0x0043 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002e, code lost:
    
        if (r0.equals("INT") == false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0035, code lost:
    
        if (r0.equals("INTEGER") != false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:?, code lost:
    
        return "INTEGER";
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.String typeToGemma(java.lang.String r5) {
        /*
            r4 = this;
            java.util.Locale r0 = java.util.Locale.ROOT
            java.lang.String r0 = r5.toUpperCase(r0)
            java.lang.String r1 = "toUpperCase(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            int r1 = r0.hashCode()
            java.lang.String r2 = "BOOLEAN"
            java.lang.String r3 = "INTEGER"
            switch(r1) {
                case -1981034679: goto L39;
                case -1618932450: goto L31;
                case 72655: goto L28;
                case 2044650: goto L1e;
                case 782694408: goto L17;
                default: goto L16;
            }
        L16:
            goto L43
        L17:
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L27
            goto L16
        L1e:
            java.lang.String r1 = "BOOL"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L27
            goto L16
        L27:
            goto L45
        L28:
            java.lang.String r1 = "INT"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L37
            goto L16
        L31:
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L16
        L37:
            r2 = r3
            goto L45
        L39:
            java.lang.String r2 = "NUMBER"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L42
            goto L16
        L42:
            goto L45
        L43:
            java.lang.String r2 = "STRING"
        L45:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jarvis.brain.LlmBrain.typeToGemma(java.lang.String):java.lang.String");
    }

    private final BrainResult parseFunctionCalls(String raw, List<BrainRouter.SkillInfo> topSkills, String userText) {
        List<FunctionCall> listExtractToolCalls = extractToolCalls(raw);
        if (listExtractToolCalls.isEmpty()) {
            return null;
        }
        FunctionCall first = listExtractToolCalls.get(0);
        BrainRouter.SkillInfo skill = matchSkill(first.getName(), topSkills);
        if (skill == null) {
            Log.w(TAG, "Unknown function name from LLM: " + first.getName());
            return null;
        }
        Map args = new LinkedHashMap();
        for (Map.Entry<String, String> entry : first.getArguments().entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            args.put(k, castValue(v));
        }
        sanitizeArgs(skill, args, userText);
        IntentType intentType = resolveIntentType(skill.getId());
        if (intentType == IntentType.UNKNOWN) {
            Log.w(TAG, "Unknown skill from LLM: " + skill.getId());
            return null;
        }
        String strTake = StringsKt.take(raw, 200);
        Collection destination$iv$iv = new ArrayList(args.size());
        for (Map.Entry<String, Object> entry2 : args.entrySet()) {
            String k2 = entry2.getKey();
            Object v2 = entry2.getValue();
            destination$iv$iv.add(TuplesKt.to(k2, new Slot(k2, v2, 0.0f, 4, null)));
            listExtractToolCalls = listExtractToolCalls;
        }
        return new BrainResult.SkillCall(new Intent(intentType, strTake, MapsKt.toMap((List) destination$iv$iv), CONFIDENCE_SKILL_CALL, ConfidenceLevel.HIGH), CONFIDENCE_SKILL_CALL);
    }

    private final void sanitizeArgs(BrainRouter.SkillInfo skill, Map<String, Object> args, String userText) {
        String lowerCase;
        boolean z;
        int $i$f$any;
        String string;
        CollectionsKt.removeAll(args.entrySet(), new Function1() { // from class: com.jarvis.brain.LlmBrain$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(LlmBrain.sanitizeArgs$lambda$0((Map.Entry) obj));
            }
        });
        String str = null;
        if (Intrinsics.areEqual(skill.getId(), APP_LAUNCH_SKILL)) {
            Object obj = args.get("app");
            String current = obj != null ? obj.toString() : null;
            if (current == null) {
                current = "";
            }
            if (StringsKt.isBlank(current) || !userTextMentions(userText, current)) {
                String fromText = extractAppName(userText);
                if (!StringsKt.isBlank(fromText)) {
                    Log.d(TAG, "app arg '" + current + "' not grounded in text, using '" + fromText + "'");
                    args.put("app", fromText);
                }
            }
        }
        if (StringsKt.contains((CharSequence) skill.getId(), (CharSequence) "flashlight", true)) {
            Object obj2 = args.get("action");
            if (obj2 == null || (string = obj2.toString()) == null) {
                lowerCase = null;
            } else {
                lowerCase = string.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            }
            String action = lowerCase != null ? lowerCase : "";
            if (!SetsKt.setOf((Object[]) new String[]{"on", "off"}).contains(action)) {
                String lower = userText.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lower, "toLowerCase(...)");
                Iterable $this$any$iv = OFF_HINTS;
                if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                    Iterator it = $this$any$iv.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            Object element$iv = it.next();
                            String it2 = (String) element$iv;
                            if (StringsKt.contains$default((CharSequence) lower, (CharSequence) it2, false, 2, (Object) null)) {
                                z = true;
                                break;
                            }
                        } else {
                            z = false;
                            break;
                        }
                    }
                } else {
                    z = false;
                }
                if (z) {
                    str = "off";
                } else {
                    Iterable $this$any$iv2 = ON_HINTS;
                    if (!($this$any$iv2 instanceof Collection) || !((Collection) $this$any$iv2).isEmpty()) {
                        Iterator it3 = $this$any$iv2.iterator();
                        while (true) {
                            if (!it3.hasNext()) {
                                $i$f$any = 0;
                                break;
                            }
                            Object element$iv2 = it3.next();
                            String it4 = (String) element$iv2;
                            Iterable $this$any$iv3 = $this$any$iv2;
                            if (StringsKt.contains$default((CharSequence) lower, (CharSequence) it4, false, 2, (Object) null)) {
                                $i$f$any = 1;
                                break;
                            }
                            $this$any$iv2 = $this$any$iv3;
                        }
                    } else {
                        $i$f$any = 0;
                    }
                    if ($i$f$any != 0) {
                        str = "on";
                    }
                }
                String mapped = str;
                if (mapped != null) {
                    Log.d(TAG, "flashlight action '" + action + "' remapped to '" + mapped + "' from text");
                    args.put("action", mapped);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean sanitizeArgs$lambda$0(Map.Entry it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (it.getValue() instanceof String) {
            Regex regex = PLACEHOLDER_REGEX;
            Object value = it.getValue();
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.String");
            if (regex.matches((String) value)) {
                return true;
            }
        }
        return false;
    }

    private final boolean userTextMentions(String userText, String value) {
        String haystack = Transliterator.INSTANCE.normalize(userText);
        String needle = Transliterator.INSTANCE.normalize(value);
        return (needle.length() > 0) && StringsKt.contains$default((CharSequence) haystack, (CharSequence) needle, false, 2, (Object) null);
    }

    private final String extractAppName(String userText) {
        String lowerCase = userText.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        Iterable $this$map$iv = new Regex("\\s+").split(lowerCase, 0);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            destination$iv$iv.add(StringsKt.trim(it, '.', ',', '!', '?', 171, 187, Typography.quote, '\''));
        }
        Iterable $this$filter$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it2 = (String) element$iv$iv;
            if (!StringsKt.isBlank(it2)) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        List words = (List) destination$iv$iv2;
        int index$iv = 0;
        Iterator it3 = words.iterator();
        while (true) {
            if (it3.hasNext()) {
                Object item$iv = it3.next();
                String it4 = (String) item$iv;
                if (LAUNCH_VERBS.contains(it4)) {
                    break;
                }
                index$iv++;
            } else {
                index$iv = -1;
                break;
            }
        }
        int idx = index$iv;
        return idx < 0 ? "" : StringsKt.trim((CharSequence) CollectionsKt.joinToString$default(CollectionsKt.drop(words, idx + 1), StringUtils.SPACE, null, null, 0, null, null, 62, null)).toString();
    }

    private final List<FunctionCall> extractToolCalls(String text) {
        List calls = new ArrayList();
        int i = 0;
        int i2 = 2;
        Object obj = null;
        Sequence $this$forEach$iv = Regex.findAll$default(CALL_REGEX, text, 0, 2, null);
        for (Object element$iv : $this$forEach$iv) {
            MatchResult m = (MatchResult) element$iv;
            String name = m.getGroupValues().get(1);
            String argsStr = m.getGroupValues().get(i2);
            Map args = new LinkedHashMap();
            Sequence $this$forEach$iv2 = Regex.findAll$default(ARG_REGEX, argsStr, i, i2, obj);
            for (Object element$iv2 : $this$forEach$iv2) {
                MatchResult a = (MatchResult) element$iv2;
                String key = a.getGroupValues().get(1);
                String value = a.getGroupValues().get(i2).length() > 0 ? a.getGroupValues().get(i2) : a.getGroupValues().get(3);
                if (key.length() > 0) {
                    args.put(key, StringsKt.trim((CharSequence) value).toString());
                }
                i2 = 2;
            }
            calls.add(new FunctionCall(name, args));
            i = 0;
            i2 = 2;
            obj = null;
        }
        return calls;
    }

    /* JADX INFO: compiled from: LlmBrain.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J)\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/jarvis/brain/LlmBrain$FunctionCall;", "", "name", "", "arguments", "", "<init>", "(Ljava/lang/String;Ljava/util/Map;)V", "getName", "()Ljava/lang/String;", "getArguments", "()Ljava/util/Map;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final /* data */ class FunctionCall {
        private final Map<String, String> arguments;
        private final String name;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ FunctionCall copy$default(FunctionCall functionCall, String str, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                str = functionCall.name;
            }
            if ((i & 2) != 0) {
                map = functionCall.arguments;
            }
            return functionCall.copy(str, map);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        public final Map<String, String> component2() {
            return this.arguments;
        }

        public final FunctionCall copy(String name, Map<String, String> arguments) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(arguments, "arguments");
            return new FunctionCall(name, arguments);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FunctionCall)) {
                return false;
            }
            FunctionCall functionCall = (FunctionCall) other;
            return Intrinsics.areEqual(this.name, functionCall.name) && Intrinsics.areEqual(this.arguments, functionCall.arguments);
        }

        public int hashCode() {
            return (this.name.hashCode() * 31) + this.arguments.hashCode();
        }

        public String toString() {
            return "FunctionCall(name=" + this.name + ", arguments=" + this.arguments + ")";
        }

        public FunctionCall(String name, Map<String, String> arguments) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(arguments, "arguments");
            this.name = name;
            this.arguments = arguments;
        }

        public final Map<String, String> getArguments() {
            return this.arguments;
        }

        public final String getName() {
            return this.name;
        }
    }

    private final BrainRouter.SkillInfo matchSkill(String fnName, List<BrainRouter.SkillInfo> skills) {
        Object element$iv;
        List<BrainRouter.SkillInfo> $this$firstOrNull$iv = skills;
        Iterator it = $this$firstOrNull$iv.iterator();
        while (it.hasNext()) {
            element$iv = it.next();
            BrainRouter.SkillInfo it2 = (BrainRouter.SkillInfo) element$iv;
            if (Intrinsics.areEqual(functionName(it2.getId()), fnName)) {
                return (BrainRouter.SkillInfo) element$iv;
            }
        }
        element$iv = null;
        return (BrainRouter.SkillInfo) element$iv;
    }

    private final Object castValue(String v) {
        if (StringsKt.startsWith$default(v, "<escape>", false, 2, (Object) null) && StringsKt.endsWith$default(v, "</escape>", false, 2, (Object) null)) {
            return StringsKt.removeSuffix(StringsKt.removePrefix(v, (CharSequence) "<escape>"), (CharSequence) "</escape>");
        }
        if (StringsKt.toIntOrNull(v) != null) {
            return Integer.valueOf(Integer.parseInt(v));
        }
        if (StringsKt.toDoubleOrNull(v) != null) {
            return Integer.valueOf(MathKt.roundToInt(Double.parseDouble(v)));
        }
        if (StringsKt.equals(v, "true", true)) {
            return true;
        }
        if (StringsKt.equals(v, "false", true)) {
            return false;
        }
        return StringsKt.trim(v, Typography.quote, '\'', ' ');
    }

    private final IntentType resolveIntentType(String skillId) {
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "flashlight", true)) {
            return IntentType.FLASHLIGHT;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "volume", true)) {
            return IntentType.VOLUME;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "brightness", true)) {
            return IntentType.BRIGHTNESS;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "timer", true)) {
            return IntentType.TIMER;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "system.status", true)) {
            return IntentType.SYSTEM_STATUS;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) NotificationCompat.CATEGORY_REMINDER, true)) {
            return IntentType.REMINDER;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "briefing", true)) {
            return IntentType.BRIEFING;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "news", true)) {
            return IntentType.NEWS;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "rates", true)) {
            return IntentType.RATES;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "location", true)) {
            return IntentType.LOCATION;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "navigate", true)) {
            return IntentType.NAVIGATE;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "web.search", true)) {
            return IntentType.WEB_SEARCH;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "camera", true)) {
            return IntentType.CAMERA;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) NotificationCompat.CATEGORY_EVENT, true) || StringsKt.contains((CharSequence) skillId, (CharSequence) "calendar", true)) {
            return IntentType.CREATE_EVENT;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "sms", true)) {
            return IntentType.SEND_SMS;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "telegram", true)) {
            return IntentType.SEND_TELEGRAM;
        }
        if (Intrinsics.areEqual(skillId, APP_LAUNCH_SKILL) || (StringsKt.contains((CharSequence) skillId, (CharSequence) "launch", true) && StringsKt.contains((CharSequence) skillId, (CharSequence) "app", true))) {
            return IntentType.LAUNCH_APP;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "system", true) || StringsKt.contains((CharSequence) skillId, (CharSequence) EventMatcher.WIFI, true) || StringsKt.contains((CharSequence) skillId, (CharSequence) "bluetooth", true)) {
            return IntentType.SYSTEM_SETTING;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "phone.call", true) || (StringsKt.contains((CharSequence) skillId, (CharSequence) "call", true) && StringsKt.contains((CharSequence) skillId, (CharSequence) "phone", true))) {
            return IntentType.CALL;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "contacts", true)) {
            return IntentType.CONTACTS;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "files", true)) {
            return IntentType.FILES;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "ui.click", true)) {
            return IntentType.UI_CLICK;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "ui.type", true)) {
            return IntentType.UI_TYPE;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "ui.swipe", true)) {
            return IntentType.UI_SWIPE;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "ui.global", true)) {
            return IntentType.UI_GLOBAL;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "ui.read", true) || StringsKt.contains((CharSequence) skillId, (CharSequence) "screen.read", true)) {
            return IntentType.UI_READ;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "ui.wait", true)) {
            return IntentType.UI_WAIT;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "notification", true)) {
            return IntentType.NOTIFICATIONS;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "clipboard", true)) {
            return IntentType.CLIPBOARD;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "screenshot", true)) {
            return IntentType.SCREENSHOT;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "weather", true) || StringsKt.contains((CharSequence) skillId, (CharSequence) "погод", true)) {
            return IntentType.WEATHER;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "music", true) || StringsKt.contains((CharSequence) skillId, (CharSequence) "media", true)) {
            return IntentType.MUSIC;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "knowledge", true) || StringsKt.contains((CharSequence) skillId, (CharSequence) "wiki", true)) {
            return IntentType.KNOWLEDGE;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "setting", true)) {
            return IntentType.SYSTEM_SETTING;
        }
        if (StringsKt.contains((CharSequence) skillId, (CharSequence) "launch", true) || StringsKt.contains((CharSequence) skillId, (CharSequence) "app", true)) {
            return IntentType.LAUNCH_APP;
        }
        return IntentType.UNKNOWN;
    }

    private final String text(Contents $this$text) {
        if ($this$text == null) {
            return "";
        }
        Iterable $this$filterIsInstance$iv = $this$text.getContents();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterIsInstance$iv) {
            if (element$iv$iv instanceof Content.Text) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return CollectionsKt.joinToString$default((List) destination$iv$iv, "", null, null, 0, null, new Function1() { // from class: com.jarvis.brain.LlmBrain$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LlmBrain.text$lambda$0((Content.Text) obj);
            }
        }, 30, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence text$lambda$0(Content.Text it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getText();
    }
}
