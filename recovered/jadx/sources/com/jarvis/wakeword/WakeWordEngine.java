package com.jarvis.wakeword;

import com.jarvis.app.VoiceService;
import com.jarvis.app.proactive.PhoneStateReader;
import com.jarvis.asr.AsrChunkResult;
import com.jarvis.asr.AsrSession;
import com.jarvis.asr.CommandRefinePasses;
import com.jarvis.core.common.model.RequestSource;
import com.jarvis.core.common.model.UserRequest;
import com.jarvis.core.nlp.AsrPhraseNormalizer;
import com.jarvis.core.nlp.CommandTextSelector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequencesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.scheduling.WorkQueueKt;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: WakeWordEngine.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 a2\u00020\u0001:\u0003_`aB\u0087\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\u001e\b\u0002\u0010\u000f\u001a\u0018\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u0010\u0012\u001a\b\u0002\u0010\u0013\u001a\u0014\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00140\u0010¢\u0006\u0004\b\u0015\u0010\u0016J\u0006\u00106\u001a\u00020\fJ\u0006\u00107\u001a\u00020\fJ\u000e\u00108\u001a\u00020\f2\u0006\u00109\u001a\u00020\u0011J\u0010\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\u000bH\u0002J\u0010\u0010<\u001a\u00020\u00142\u0006\u0010;\u001a\u00020\u000bH\u0002J\u0010\u0010=\u001a\u00020\f2\u0006\u00109\u001a\u00020\u0011H\u0002J\b\u0010>\u001a\u00020\fH\u0002J\u0010\u0010?\u001a\u00020\u000b2\u0006\u0010@\u001a\u00020\u000bH\u0002J\u0010\u0010A\u001a\u00020\u000b2\u0006\u0010;\u001a\u00020\u000bH\u0002J\u0018\u0010B\u001a\u00020\u000b2\u0006\u0010C\u001a\u00020\u000b2\u0006\u0010D\u001a\u00020\u000bH\u0002J\u0010\u0010E\u001a\u00020\u000b2\u0006\u0010F\u001a\u00020\u000bH\u0002J\b\u0010G\u001a\u00020\fH\u0002J\b\u0010H\u001a\u00020\fH\u0002J\u0010\u0010I\u001a\u00020\f2\b\b\u0002\u0010J\u001a\u00020\u0007J\u0018\u0010K\u001a\u00020\u000b2\u0006\u0010L\u001a\u00020\u000b2\u0006\u0010M\u001a\u00020\u000bH\u0002J\u0012\u0010N\u001a\u0004\u0018\u00010O2\u0006\u0010F\u001a\u00020\u000bH\u0002J\u0010\u0010P\u001a\u00020\f2\u0006\u0010F\u001a\u00020\u000bH\u0002J\f\u0010Q\u001a\u00020\u0014*\u00020\u000bH\u0002J\f\u0010R\u001a\u00020\u000b*\u00020\u000bH\u0002J\u0012\u0010S\u001a\b\u0012\u0004\u0012\u00020\u000b0T*\u00020\u000bH\u0002J\f\u0010U\u001a\u00020\u0014*\u00020\u000bH\u0002J\f\u0010V\u001a\u00020\u0014*\u00020\u000bH\u0002J\f\u0010W\u001a\u00020\u0014*\u00020\u000bH\u0002J\u0010\u0010X\u001a\u00020Y2\u0006\u0010Z\u001a\u00020\u000bH\u0002J\u0010\u0010[\u001a\u00020\u00142\u0006\u0010Z\u001a\u00020\u000bH\u0002J\u0018\u0010\\\u001a\u00020\u000e2\u0006\u0010]\u001a\u00020\u000b2\u0006\u0010^\u001a\u00020\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u000f\u001a\u0018\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0013\u001a\u0014\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00140\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\f0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\f0\u001c¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001eR\u001e\u0010$\u001a\u00020#2\u0006\u0010\"\u001a\u00020#@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u000e\u0010'\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u00102\u001a\u0012\u0012\u0004\u0012\u00020403j\b\u0012\u0004\u0012\u000204`5X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006b"}, d2 = {"Lcom/jarvis/wakeword/WakeWordEngine;", "", PhoneStateReader.FAKE_SESSION, "Lcom/jarvis/asr/AsrSession;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "maxCommandDurationMs", "", "awaitCommandTimeoutMs", "onPartial", "Lkotlin/Function1;", "", "", "sensitivity", "", "refineWithPcm", "Lkotlin/Function2;", "", "Lcom/jarvis/asr/CommandRefinePasses;", "voiceAllowed", "", "<init>", "(Lcom/jarvis/asr/AsrSession;Lkotlinx/coroutines/CoroutineScope;JJLkotlin/jvm/functions/Function1;ILkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;)V", "maxFuzzyDist", "_userRequests", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/jarvis/core/common/model/UserRequest;", "userRequests", "Lkotlinx/coroutines/flow/SharedFlow;", "getUserRequests", "()Lkotlinx/coroutines/flow/SharedFlow;", "_wakeDetected", "wakeDetected", "getWakeDetected", "value", "Lcom/jarvis/wakeword/WakeWordState;", "state", "getState", "()Lcom/jarvis/wakeword/WakeWordState;", "lastPartial", "commandTimeoutJob", "Lkotlinx/coroutines/Job;", "awaitTimeoutJob", "lastWakeAtMs", "lastEmitAtMs", "lastEmitText", "lastWeakMatchAtMs", "hangoverText", "hangoverUntilMs", "pendingCommandPrefix", "commandPcm", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "cancelListening", "notifyExternalWake", "onAudioChunk", "samples", "finishCommand", "rawText", "rejectForeignVoice", "appendPcm", "clearPcm", "withPendingPrefix", "command", "mergeWithHangover", "joinFragments", "left", "right", "coalesceWakeTokens", VoiceService.EXTRA_TEXT, "clearHangover", "restartAwaitWatchdog", "listenForFollowUp", "timeoutMs", "mergeListeningPartial", "seeded", "incoming", "findWakeHit", "Lcom/jarvis/wakeword/WakeWordEngine$WakeHit;", "maybeResetIdleNoise", "containsWakeWord", "stripWakeWord", "tokens", "", "looksLikeWakePrefix", "hasWakeFragment", "looksLikeWakePrefixToken", "wakeStrength", "Lcom/jarvis/wakeword/WakeWordEngine$WakeStrength;", "token", "isWakeToken", "levenshtein", "a", "b", "WakeHit", "WakeStrength", "Companion", "wakeword-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WakeWordEngine {
    private static final int COMMAND_THEN_WAKE_MAX = 5;
    public static final long DEFAULT_AWAIT_COMMAND_TIMEOUT_MS = 9000;
    public static final long DEFAULT_MAX_COMMAND_DURATION_MS = 12000;
    public static final int DEFAULT_SENSITIVITY = 2;
    private static final long EMIT_DEDUP_MS = 1000;
    private static final long HANGOVER_MS = 3200;
    private static final int IDLE_NOISE_RESET_TOKENS = 10;
    private static final int IDLE_TRAILING_KEEP_TOKENS = 6;
    private static final int SAMPLE_RATE = 16000;
    private static final long WAKE_DEDUP_MS = 1500;
    private static final long WEAK_CONFIRM_WINDOW_MS = 1400;
    private static final int WEAK_MAX_PREFIX_TOKENS = 3;
    private final MutableSharedFlow<UserRequest> _userRequests;
    private final MutableSharedFlow<Unit> _wakeDetected;
    private final long awaitCommandTimeoutMs;
    private Job awaitTimeoutJob;
    private final ArrayList<Float> commandPcm;
    private Job commandTimeoutJob;
    private String hangoverText;
    private long hangoverUntilMs;
    private long lastEmitAtMs;
    private String lastEmitText;
    private String lastPartial;
    private long lastWakeAtMs;
    private long lastWeakMatchAtMs;
    private final long maxCommandDurationMs;
    private final int maxFuzzyDist;
    private final Function1<String, Unit> onPartial;
    private String pendingCommandPrefix;
    private final Function2<float[], String, CommandRefinePasses> refineWithPcm;
    private final CoroutineScope scope;
    private final AsrSession session;
    private WakeWordState state;
    private final SharedFlow<UserRequest> userRequests;
    private final Function2<float[], Boolean, Boolean> voiceAllowed;
    private final SharedFlow<Unit> wakeDetected;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final List<String> WAKE_PATTERNS = CollectionsKt.listOf((Object[]) new String[]{"джарвис", "джарвиз", "джервис", "джеарвис", "джарви", "джарус", "жарвис", "жарвиз", "джарвес", "джорвис", "джавирс", "чарвис", "джарвьс", "джервиз", "джеорвис", "jarvis", "jarvice", "jarwis", "gervis", "djarvis"});
    private static final List<String> WEAK_WAKE_PATTERNS = CollectionsKt.listOf("джорус");
    private static final List<String> FUZZY_CORES = CollectionsKt.listOf((Object[]) new String[]{"джарвис", "jarvis"});
    private static final Set<String> CONFIRM_PREFIXES = SetsKt.setOf((Object[]) new String[]{"дж", "жа", "дже", "ча", "ja", "dj", "ge"});
    private static final List<String> WAKE_PREFIXES = CollectionsKt.listOf((Object[]) new String[]{"джа", "джар", "джарв", "джарви", "джер", "джерв", "жар", "жарв", ArchiveStreamFactory.JAR, "jarv", "jarvi"});
    private static final Regex TOKEN_REGEX = new Regex("[\\p{L}\\p{N}]+");

    /* JADX INFO: compiled from: WakeWordEngine.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/jarvis/wakeword/WakeWordEngine$WakeStrength;", "", "<init>", "(Ljava/lang/String;I)V", "NONE", "WEAK", "STRONG", "wakeword-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private enum WakeStrength {
        NONE,
        WEAK,
        STRONG;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<WakeStrength> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: compiled from: WakeWordEngine.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[WakeStrength.values().length];
            try {
                iArr[WakeStrength.STRONG.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[WakeStrength.WEAK.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[WakeStrength.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[WakeWordState.values().length];
            try {
                iArr2[WakeWordState.IDLE.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr2[WakeWordState.LISTENING.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr2[WakeWordState.AWAITING_COMMAND.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public WakeWordEngine(AsrSession session, CoroutineScope scope, long maxCommandDurationMs, long awaitCommandTimeoutMs, Function1<? super String, Unit> onPartial, int sensitivity, Function2<? super float[], ? super String, CommandRefinePasses> function2, Function2<? super float[], ? super Boolean, Boolean> voiceAllowed) {
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(onPartial, "onPartial");
        Intrinsics.checkNotNullParameter(voiceAllowed, "voiceAllowed");
        this.session = session;
        this.scope = scope;
        this.maxCommandDurationMs = maxCommandDurationMs;
        this.awaitCommandTimeoutMs = awaitCommandTimeoutMs;
        this.onPartial = onPartial;
        this.refineWithPcm = function2;
        this.voiceAllowed = voiceAllowed;
        this.maxFuzzyDist = INSTANCE.fuzzyDistFor(sensitivity);
        this._userRequests = SharedFlowKt.MutableSharedFlow$default(0, 8, null, 5, null);
        this.userRequests = this._userRequests;
        this._wakeDetected = SharedFlowKt.MutableSharedFlow$default(0, 4, null, 5, null);
        this.wakeDetected = this._wakeDetected;
        this.state = WakeWordState.IDLE;
        this.lastPartial = "";
        this.hangoverText = "";
        this.pendingCommandPrefix = "";
        this.commandPcm = new ArrayList<>(96000);
    }

    public /* synthetic */ WakeWordEngine(AsrSession asrSession, CoroutineScope coroutineScope, long j, long j2, Function1 function1, int i, Function2 function2, Function2 function3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(asrSession, coroutineScope, (i2 & 4) != 0 ? 12000L : j, (i2 & 8) != 0 ? 9000L : j2, (i2 & 16) != 0 ? new Function1() { // from class: com.jarvis.wakeword.WakeWordEngine$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return WakeWordEngine._init_$lambda$0((String) obj);
            }
        } : function1, (i2 & 32) != 0 ? 2 : i, (i2 & 64) != 0 ? null : function2, (i2 & 128) != 0 ? new Function2() { // from class: com.jarvis.wakeword.WakeWordEngine$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(WakeWordEngine._init_$lambda$1((float[]) obj, ((Boolean) obj2).booleanValue()));
            }
        } : function3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit _init_$lambda$0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean _init_$lambda$1(float[] fArr, boolean z) {
        Intrinsics.checkNotNullParameter(fArr, "<unused var>");
        return true;
    }

    public final SharedFlow<UserRequest> getUserRequests() {
        return this.userRequests;
    }

    public final SharedFlow<Unit> getWakeDetected() {
        return this.wakeDetected;
    }

    public final WakeWordState getState() {
        return this.state;
    }

    public final void cancelListening() {
        if (this.state == WakeWordState.IDLE) {
            return;
        }
        clearHangover();
        clearPcm();
        this.pendingCommandPrefix = "";
        this.lastPartial = "";
        this.session.reset();
        this.state = WakeWordState.IDLE;
    }

    public final void notifyExternalWake() {
        if (this.state != WakeWordState.IDLE) {
            return;
        }
        long now = System.currentTimeMillis();
        clearHangover();
        clearPcm();
        this.pendingCommandPrefix = "";
        this.lastPartial = "";
        this.lastWeakMatchAtMs = 0L;
        this.session.reset();
        this.state = WakeWordState.LISTENING;
        if (now - this.lastWakeAtMs >= WAKE_DEDUP_MS) {
            this.lastWakeAtMs = now;
            if (!this._wakeDetected.tryEmit(Unit.INSTANCE)) {
                BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C00721(null), 3, null);
            }
        }
        Job job = this.commandTimeoutJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.commandTimeoutJob = BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AnonymousClass2(null), 3, null);
    }

    /* JADX INFO: renamed from: com.jarvis.wakeword.WakeWordEngine$notifyExternalWake$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: WakeWordEngine.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.wakeword.WakeWordEngine$notifyExternalWake$1", f = "WakeWordEngine.kt", i = {}, l = {78}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C00721 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C00721(Continuation<? super C00721> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return WakeWordEngine.this.new C00721(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00721) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (WakeWordEngine.this._wakeDetected.emit(Unit.INSTANCE, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.jarvis.wakeword.WakeWordEngine$notifyExternalWake$2, reason: invalid class name */
    /* JADX INFO: compiled from: WakeWordEngine.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.wakeword.WakeWordEngine$notifyExternalWake$2", f = "WakeWordEngine.kt", i = {}, l = {83}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return WakeWordEngine.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (DelayKt.delay(WakeWordEngine.this.maxCommandDurationMs, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            WakeWordEngine.this.finishCommand(WakeWordEngine.this.lastPartial);
            return Unit.INSTANCE;
        }
    }

    public final void onAudioChunk(float[] samples) {
        WakeStrength strength;
        Intrinsics.checkNotNullParameter(samples, "samples");
        if (this.state == WakeWordState.LISTENING || this.state == WakeWordState.AWAITING_COMMAND) {
            appendPcm(samples);
        }
        AsrChunkResult result = this.session.processChunk(samples);
        String rawText = StringsKt.trim((CharSequence) result.getText()).toString();
        String text = mergeWithHangover(rawText);
        if (!StringsKt.isBlank(text) && !Intrinsics.areEqual(text, this.lastPartial)) {
            this.onPartial.invoke(text);
        }
        switch (WhenMappings.$EnumSwitchMapping$1[this.state.ordinal()]) {
            case 1:
                long now = System.currentTimeMillis();
                WakeHit hit = findWakeHit(text);
                if (hit == null || (strength = hit.getStrength()) == null) {
                    strength = WakeStrength.NONE;
                }
                boolean shouldWake = true;
                boolean weakConfirmed = strength == WakeStrength.WEAK && this.lastWeakMatchAtMs > 0 && now - this.lastWeakMatchAtMs < WEAK_CONFIRM_WINDOW_MS;
                switch (WhenMappings.$EnumSwitchMapping$0[strength.ordinal()]) {
                    case 1:
                        break;
                    case 2:
                        if (!result.isEndpoint() && !weakConfirmed) {
                            shouldWake = false;
                        }
                        break;
                    case 3:
                        shouldWake = false;
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                if (shouldWake && hit != null) {
                    String seeded = hit.getCommandText();
                    clearHangover();
                    clearPcm();
                    appendPcm(samples);
                    this.session.reset();
                    this.pendingCommandPrefix = "";
                    this.state = WakeWordState.LISTENING;
                    this.lastWeakMatchAtMs = 0L;
                    this.lastPartial = seeded;
                    if (now - this.lastWakeAtMs >= WAKE_DEDUP_MS) {
                        this.lastWakeAtMs = now;
                        this._wakeDetected.tryEmit(Unit.INSTANCE);
                    }
                    this.commandTimeoutJob = BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C00731(null), 3, null);
                    if (result.isEndpoint()) {
                        finishCommand(seeded);
                    } else if (!StringsKt.isBlank(seeded) && !AsrPhraseNormalizer.INSTANCE.isIncompleteCommand(seeded)) {
                        finishCommand(seeded);
                    }
                } else {
                    this.lastWeakMatchAtMs = strength == WakeStrength.WEAK ? now : 0L;
                    maybeResetIdleNoise(text);
                }
                if (this.state == WakeWordState.IDLE && result.isEndpoint()) {
                    if (!StringsKt.isBlank(text) && (looksLikeWakePrefix(text) || hasWakeFragment(text))) {
                        this.hangoverText = text;
                        this.hangoverUntilMs = HANGOVER_MS + now;
                    } else if (hit == null) {
                        clearHangover();
                    }
                    this.session.reset();
                    return;
                }
                return;
            case 2:
                String incoming = stripWakeWord(text);
                String merged = mergeListeningPartial(this.lastPartial, incoming);
                this.lastPartial = merged;
                if (result.isEndpoint()) {
                    finishCommand(merged);
                    return;
                }
                return;
            case 3:
                String candidate = withPendingPrefix(stripWakeWord(text));
                if (!StringsKt.isBlank(candidate)) {
                    this.lastPartial = candidate;
                    restartAwaitWatchdog();
                    if (result.isEndpoint()) {
                        finishCommand(candidate);
                        return;
                    }
                    return;
                }
                if (containsWakeWord(text)) {
                    restartAwaitWatchdog();
                    if (result.isEndpoint()) {
                        this.session.reset();
                        return;
                    }
                    return;
                }
                if (result.isEndpoint()) {
                    this.session.reset();
                    return;
                }
                return;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: renamed from: com.jarvis.wakeword.WakeWordEngine$onAudioChunk$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: WakeWordEngine.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.wakeword.WakeWordEngine$onAudioChunk$1", f = "WakeWordEngine.kt", i = {}, l = {WorkQueueKt.MASK}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C00731 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C00731(Continuation<? super C00731> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return WakeWordEngine.this.new C00731(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00731) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (DelayKt.delay(WakeWordEngine.this.maxCommandDurationMs, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            WakeWordEngine.this.finishCommand(WakeWordEngine.this.lastPartial);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finishCommand(String rawText) {
        float[] pcmSnapshot;
        Function2<float[], String, CommandRefinePasses> function2;
        if ((this.state == WakeWordState.LISTENING || this.state == WakeWordState.AWAITING_COMMAND) && !rejectForeignVoice(rawText)) {
            Job job = this.commandTimeoutJob;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.commandTimeoutJob = null;
            Job job2 = this.awaitTimeoutJob;
            if (job2 != null) {
                Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
            }
            this.awaitTimeoutJob = null;
            this.session.reset();
            clearHangover();
            if (this.commandPcm.isEmpty()) {
                pcmSnapshot = new float[0];
            } else {
                pcmSnapshot = new float[this.commandPcm.size()];
                int size = this.commandPcm.size();
                for (int i = 0; i < size; i++) {
                    Float f = this.commandPcm.get(i);
                    Intrinsics.checkNotNullExpressionValue(f, "get(...)");
                    pcmSnapshot[i] = f.floatValue();
                }
            }
            String streaming = withPendingPrefix(stripWakeWord(rawText));
            CommandRefinePasses refined = ((pcmSnapshot.length == 0) || (function2 = this.refineWithPcm) == null) ? null : function2.invoke(pcmSnapshot, streaming);
            String command = CommandTextSelector.INSTANCE.select(streaming, refined != null ? refined.getMultilingual() : null, refined != null ? refined.getEnglish() : null);
            long now = System.currentTimeMillis();
            boolean dup = now - this.lastEmitAtMs < 1000 && Intrinsics.areEqual(this.lastEmitText, command);
            if (StringsKt.isBlank(command)) {
                this.pendingCommandPrefix = "";
                this.state = WakeWordState.AWAITING_COMMAND;
                clearPcm();
                restartAwaitWatchdog();
                if (!dup) {
                    this.lastEmitAtMs = now;
                    this.lastEmitText = command;
                    this._userRequests.tryEmit(new UserRequest("", RequestSource.VOICE, null, 0L, null, 28, null));
                    return;
                }
                return;
            }
            if (AsrPhraseNormalizer.INSTANCE.isIncompleteCommand(command)) {
                this.pendingCommandPrefix = command;
                this.state = WakeWordState.AWAITING_COMMAND;
                this.lastPartial = command;
                restartAwaitWatchdog();
                return;
            }
            this.pendingCommandPrefix = "";
            this.state = WakeWordState.IDLE;
            clearPcm();
            if (!dup) {
                this.lastEmitAtMs = now;
                this.lastEmitText = command;
                this._userRequests.tryEmit(new UserRequest(command, RequestSource.VOICE, null, 0L, null, 28, null));
            }
        }
    }

    private final boolean rejectForeignVoice(String rawText) {
        if (StringsKt.isBlank(rawText) || this.commandPcm.isEmpty()) {
            return false;
        }
        int size = this.commandPcm.size();
        float[] pcm = new float[size];
        for (int i = 0; i < size; i++) {
            Float f = this.commandPcm.get(i);
            Intrinsics.checkNotNullExpressionValue(f, "get(...)");
            pcm[i] = f.floatValue();
        }
        boolean awaited = this.state == WakeWordState.AWAITING_COMMAND;
        if (this.voiceAllowed.invoke(pcm, Boolean.valueOf(awaited)).booleanValue()) {
            return false;
        }
        this.session.reset();
        clearHangover();
        clearPcm();
        this.pendingCommandPrefix = "";
        this.lastPartial = "";
        if (!awaited) {
            Job job = this.commandTimeoutJob;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.commandTimeoutJob = null;
            this.state = WakeWordState.IDLE;
        }
        return true;
    }

    private final void appendPcm(float[] samples) {
        if (!(samples.length == 0) && this.commandPcm.size() < 192000) {
            int room = 192000 - this.commandPcm.size();
            int n = Math.min(samples.length, room);
            for (int i = 0; i < n; i++) {
                this.commandPcm.add(Float.valueOf(samples[i]));
            }
        }
    }

    private final void clearPcm() {
        this.commandPcm.clear();
    }

    private final String withPendingPrefix(String command) {
        String prefix = StringsKt.trim((CharSequence) this.pendingCommandPrefix).toString();
        String body = StringsKt.trim((CharSequence) command).toString();
        if (prefix.length() == 0) {
            return body;
        }
        if (body.length() == 0) {
            return prefix;
        }
        return StringsKt.startsWith(body, prefix, true) ? body : prefix + StringUtils.SPACE + body;
    }

    private final String mergeWithHangover(String rawText) {
        long now = System.currentTimeMillis();
        if (StringsKt.isBlank(this.hangoverText) || now > this.hangoverUntilMs) {
            if (now > this.hangoverUntilMs) {
                clearHangover();
            }
            return coalesceWakeTokens(rawText);
        }
        if (StringsKt.isBlank(rawText)) {
            return coalesceWakeTokens(this.hangoverText);
        }
        String merged = joinFragments(this.hangoverText, rawText);
        return coalesceWakeTokens(merged);
    }

    private final String joinFragments(String left, String right) {
        String l = StringsKt.trim((CharSequence) left).toString();
        String r = StringsKt.trim((CharSequence) right).toString();
        if (l.length() == 0) {
            return r;
        }
        if (r.length() == 0) {
            return l;
        }
        if (StringsKt.startsWith(r, l, true)) {
            return r;
        }
        List<String> list = tokens(l);
        List<String> list2 = tokens(r);
        if (list.size() == 1 && !list2.isEmpty()) {
            String combinedFirst = new StringBuilder().append(CollectionsKt.first((List) list)).append(CollectionsKt.first((List) list2)).toString().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(combinedFirst, "toLowerCase(...)");
            if (isWakeToken(combinedFirst) || looksLikeWakePrefixToken(combinedFirst)) {
                return CollectionsKt.joinToString$default(CollectionsKt.plus((Collection) CollectionsKt.listOf(combinedFirst), (Iterable) CollectionsKt.drop(list2, 1)), StringUtils.SPACE, null, null, 0, null, null, 62, null);
            }
        }
        return l + StringUtils.SPACE + r;
    }

    private final String coalesceWakeTokens(String text) {
        if (StringsKt.isBlank(text)) {
            return text;
        }
        List<String> list = tokens(text);
        if (list.size() < 2) {
            return StringsKt.trim((CharSequence) text).toString();
        }
        List out = new ArrayList();
        int i = 0;
        while (i < list.size()) {
            if (i + 1 < list.size()) {
                String a = list.get(i);
                String b = list.get(i + 1);
                String glued = a + b;
                if (!isWakeToken(a) && !isWakeToken(b) && isWakeToken(glued)) {
                    out.add(glued);
                    i += 2;
                }
            }
            out.add(list.get(i));
            i++;
        }
        return CollectionsKt.joinToString$default(out, StringUtils.SPACE, null, null, 0, null, null, 62, null);
    }

    private final void clearHangover() {
        this.hangoverText = "";
        this.hangoverUntilMs = 0L;
    }

    /* JADX INFO: renamed from: com.jarvis.wakeword.WakeWordEngine$restartAwaitWatchdog$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: WakeWordEngine.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.wakeword.WakeWordEngine$restartAwaitWatchdog$1", f = "WakeWordEngine.kt", i = {}, l = {334}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C00741 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C00741(Continuation<? super C00741> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return WakeWordEngine.this.new C00741(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00741) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (DelayKt.delay(WakeWordEngine.this.awaitCommandTimeoutMs, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            if (WakeWordEngine.this.getState() == WakeWordState.AWAITING_COMMAND) {
                WakeWordEngine.this.pendingCommandPrefix = "";
                WakeWordEngine.this.state = WakeWordState.IDLE;
                WakeWordEngine.this.awaitTimeoutJob = null;
                WakeWordEngine.this.session.reset();
            }
            return Unit.INSTANCE;
        }
    }

    private final void restartAwaitWatchdog() {
        Job job = this.awaitTimeoutJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.awaitTimeoutJob = BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C00741(null), 3, null);
    }

    public static /* synthetic */ void listenForFollowUp$default(WakeWordEngine wakeWordEngine, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = DEFAULT_AWAIT_COMMAND_TIMEOUT_MS;
        }
        wakeWordEngine.listenForFollowUp(j);
    }

    public final void listenForFollowUp(long timeoutMs) {
        Job job = this.commandTimeoutJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.commandTimeoutJob = null;
        this.pendingCommandPrefix = "";
        this.lastPartial = "";
        this.state = WakeWordState.AWAITING_COMMAND;
        Job job2 = this.awaitTimeoutJob;
        if (job2 != null) {
            Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
        }
        this.awaitTimeoutJob = BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AnonymousClass1(timeoutMs, this, null), 3, null);
        this.session.reset();
    }

    /* JADX INFO: renamed from: com.jarvis.wakeword.WakeWordEngine$listenForFollowUp$1, reason: invalid class name */
    /* JADX INFO: compiled from: WakeWordEngine.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.wakeword.WakeWordEngine$listenForFollowUp$1", f = "WakeWordEngine.kt", i = {}, l = {352}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $timeoutMs;
        int label;
        final /* synthetic */ WakeWordEngine this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(long j, WakeWordEngine wakeWordEngine, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$timeoutMs = j;
            this.this$0 = wakeWordEngine;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$timeoutMs, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (DelayKt.delay(this.$timeoutMs, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            if (this.this$0.getState() == WakeWordState.AWAITING_COMMAND) {
                this.this$0.state = WakeWordState.IDLE;
                this.this$0.awaitTimeoutJob = null;
                this.this$0.session.reset();
            }
            return Unit.INSTANCE;
        }
    }

    private final String mergeListeningPartial(String seeded, String incoming) {
        String seed = StringsKt.trim((CharSequence) seeded).toString();
        String body = StringsKt.trim((CharSequence) incoming).toString();
        if (seed.length() == 0) {
            return body;
        }
        if (body.length() == 0) {
            return seed;
        }
        if (StringsKt.startsWith(body, seed, true)) {
            return body;
        }
        if (StringsKt.startsWith(seed, body, true)) {
            return seed;
        }
        List<String> list = tokens(seed);
        List<String> list2 = tokens(body);
        if (!list2.isEmpty() && Intrinsics.areEqual(CollectionsKt.takeLast(list, list2.size()), list2)) {
            return seed;
        }
        if (!list.isEmpty() && Intrinsics.areEqual(CollectionsKt.take(list2, list.size()), list)) {
            return body;
        }
        return seed + StringUtils.SPACE + body;
    }

    /* JADX INFO: compiled from: WakeWordEngine.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/jarvis/wakeword/WakeWordEngine$WakeHit;", "", "strength", "Lcom/jarvis/wakeword/WakeWordEngine$WakeStrength;", "commandText", "", "<init>", "(Lcom/jarvis/wakeword/WakeWordEngine$WakeStrength;Ljava/lang/String;)V", "getStrength", "()Lcom/jarvis/wakeword/WakeWordEngine$WakeStrength;", "getCommandText", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "wakeword-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final /* data */ class WakeHit {
        private final String commandText;
        private final WakeStrength strength;

        public static /* synthetic */ WakeHit copy$default(WakeHit wakeHit, WakeStrength wakeStrength, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                wakeStrength = wakeHit.strength;
            }
            if ((i & 2) != 0) {
                str = wakeHit.commandText;
            }
            return wakeHit.copy(wakeStrength, str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final WakeStrength getStrength() {
            return this.strength;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getCommandText() {
            return this.commandText;
        }

        public final WakeHit copy(WakeStrength strength, String commandText) {
            Intrinsics.checkNotNullParameter(strength, "strength");
            Intrinsics.checkNotNullParameter(commandText, "commandText");
            return new WakeHit(strength, commandText);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof WakeHit)) {
                return false;
            }
            WakeHit wakeHit = (WakeHit) other;
            return this.strength == wakeHit.strength && Intrinsics.areEqual(this.commandText, wakeHit.commandText);
        }

        public int hashCode() {
            return (this.strength.hashCode() * 31) + this.commandText.hashCode();
        }

        public String toString() {
            return "WakeHit(strength=" + this.strength + ", commandText=" + this.commandText + ")";
        }

        public WakeHit(WakeStrength strength, String commandText) {
            Intrinsics.checkNotNullParameter(strength, "strength");
            Intrinsics.checkNotNullParameter(commandText, "commandText");
            this.strength = strength;
            this.commandText = commandText;
        }

        public final String getCommandText() {
            return this.commandText;
        }

        public final WakeStrength getStrength() {
            return this.strength;
        }
    }

    private final WakeHit findWakeHit(String text) {
        int idx;
        WakeStrength strength;
        String command;
        List<String> list = tokens(text);
        if (list.isEmpty()) {
            return null;
        }
        Integer strongIdx = null;
        Integer weakIdx = null;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            switch (WhenMappings.$EnumSwitchMapping$0[wakeStrength(list.get(i)).ordinal()]) {
                case 1:
                    strongIdx = Integer.valueOf(i);
                    break;
                case 2:
                    weakIdx = Integer.valueOf(i);
                    break;
                case 3:
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
        if (strongIdx != null) {
            idx = strongIdx.intValue();
            strength = WakeStrength.STRONG;
        } else {
            if (weakIdx == null || weakIdx.intValue() > 3) {
                return null;
            }
            idx = weakIdx.intValue();
            strength = WakeStrength.WEAK;
        }
        List before = CollectionsKt.take(list, idx);
        List after = CollectionsKt.drop(list, idx + 1);
        if (after.isEmpty()) {
            int size2 = before.size();
            boolean z = false;
            if (1 <= size2 && size2 < 6) {
                z = true;
            }
            command = z ? CollectionsKt.joinToString$default(before, StringUtils.SPACE, null, null, 0, null, null, 62, null) : "";
        } else {
            command = CollectionsKt.joinToString$default(after, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        }
        return new WakeHit(strength, command);
    }

    private final void maybeResetIdleNoise(String text) {
        List<String> list = tokens(text);
        if (list.size() < 10) {
            return;
        }
        Iterable trailing = CollectionsKt.takeLast(list, 6);
        Iterable $this$any$iv = trailing;
        boolean z = false;
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            for (Object element$iv : $this$any$iv) {
                String it = (String) element$iv;
                if (wakeStrength(it) == WakeStrength.STRONG) {
                    z = true;
                    break;
                }
            }
        }
        boolean recentStrong = z;
        if (recentStrong) {
            return;
        }
        this.session.reset();
        clearHangover();
        this.lastWeakMatchAtMs = 0L;
        this.lastPartial = "";
    }

    private final boolean containsWakeWord(String $this$containsWakeWord) {
        Iterable $this$any$iv = tokens($this$containsWakeWord);
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            String p0 = (String) element$iv;
            if (isWakeToken(p0)) {
                return true;
            }
        }
        return false;
    }

    private final String stripWakeWord(String $this$stripWakeWord) {
        Iterable $this$filterNot$iv = tokens($this$stripWakeWord);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterNot$iv) {
            String p0 = (String) element$iv$iv;
            if (!isWakeToken(p0)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return CollectionsKt.joinToString$default((List) destination$iv$iv, StringUtils.SPACE, null, null, 0, null, null, 62, null);
    }

    private final List<String> tokens(String $this$tokens) {
        return SequencesKt.toList(SequencesKt.map(Regex.findAll$default(TOKEN_REGEX, $this$tokens, 0, 2, null), new Function1() { // from class: com.jarvis.wakeword.WakeWordEngine$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return WakeWordEngine.tokens$lambda$0((MatchResult) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String tokens$lambda$0(MatchResult it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String lowerCase = it.getValue().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return lowerCase;
    }

    private final boolean looksLikeWakePrefix(String $this$looksLikeWakePrefix) {
        List<String> list = tokens($this$looksLikeWakePrefix);
        if (list.isEmpty() || list.size() > 3) {
            return false;
        }
        Iterable $this$any$iv = CollectionsKt.take(list, 2);
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            String it = (String) element$iv;
            if (looksLikeWakePrefixToken(it)) {
                return true;
            }
        }
        return false;
    }

    private final boolean hasWakeFragment(String $this$hasWakeFragment) {
        Iterable $this$any$iv = tokens($this$hasWakeFragment);
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            String it = (String) element$iv;
            if (looksLikeWakePrefixToken(it) || isWakeToken(it)) {
                return true;
            }
        }
        return false;
    }

    private final boolean looksLikeWakePrefixToken(String $this$looksLikeWakePrefixToken) {
        if ($this$looksLikeWakePrefixToken.length() < 2 || $this$looksLikeWakePrefixToken.length() > 6) {
            return false;
        }
        Iterable $this$any$iv = WAKE_PREFIXES;
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            String prefix = (String) element$iv;
            if (StringsKt.startsWith$default(prefix, $this$looksLikeWakePrefixToken, false, 2, (Object) null) || StringsKt.startsWith$default($this$looksLikeWakePrefixToken, StringsKt.take(prefix, RangesKt.coerceAtMost($this$looksLikeWakePrefixToken.length(), prefix.length())), false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    private final WakeStrength wakeStrength(String token) {
        if (WAKE_PATTERNS.contains(token)) {
            return WakeStrength.STRONG;
        }
        Iterable $this$any$iv = FUZZY_CORES;
        boolean z = false;
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            for (Object element$iv : $this$any$iv) {
                String core = (String) element$iv;
                if (token.length() >= core.length() - 1 && levenshtein(token, core) <= 1) {
                    z = true;
                    break;
                }
            }
        }
        if (z) {
            return WakeStrength.STRONG;
        }
        if (!WEAK_WAKE_PATTERNS.contains(token) && !isWakeToken(token)) {
            return WakeStrength.NONE;
        }
        return WakeStrength.WEAK;
    }

    private final boolean isWakeToken(String token) {
        boolean z;
        if (WAKE_PATTERNS.contains(token) || WEAK_WAKE_PATTERNS.contains(token)) {
            return true;
        }
        Iterable $this$any$iv = FUZZY_CORES;
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            z = false;
        } else {
            for (Object element$iv : $this$any$iv) {
                String core = (String) element$iv;
                if (token.length() >= core.length() - 1 && levenshtein(token, core) <= this.maxFuzzyDist) {
                    z = true;
                }
            }
            z = false;
        }
        return z;
    }

    private final int levenshtein(String a, String b) {
        int length = b.length() + 1;
        int[] dp = new int[length];
        for (int i = 0; i < length; i++) {
            dp[i] = i;
        }
        int i2 = 1;
        int length2 = a.length();
        if (1 <= length2) {
            while (true) {
                int prev = dp[0];
                dp[0] = i2;
                int j = 1;
                int length3 = b.length();
                if (1 <= length3) {
                    while (true) {
                        int tmp = dp[j];
                        dp[j] = Math.min(dp[j] + 1, Math.min(dp[j - 1] + 1, (a.charAt(i2 + (-1)) == b.charAt(j + (-1)) ? 0 : 1) + prev));
                        prev = tmp;
                        if (j == length3) {
                            break;
                        }
                        j++;
                    }
                }
                if (i2 == length2) {
                    break;
                }
                i2++;
            }
        }
        return dp[b.length()];
    }

    /* JADX INFO: compiled from: WakeWordEngine.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\r\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bJ\u0018\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\bH\u0002J\u0010\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0019X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0019X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0019X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0019X\u0082T¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/jarvis/wakeword/WakeWordEngine$Companion;", "", "<init>", "()V", "DEFAULT_SENSITIVITY", "", "WAKE_PATTERNS", "", "", "getWAKE_PATTERNS", "()Ljava/util/List;", "WEAK_WAKE_PATTERNS", "FUZZY_CORES", "textContainsWake", "", VoiceService.EXTRA_TEXT, "CONFIRM_PREFIXES", "", "companionLevenshtein", "a", "b", "WAKE_PREFIXES", "TOKEN_REGEX", "Lkotlin/text/Regex;", "DEFAULT_MAX_COMMAND_DURATION_MS", "", "DEFAULT_AWAIT_COMMAND_TIMEOUT_MS", "SAMPLE_RATE", "HANGOVER_MS", "fuzzyDistFor", "sensitivity", "WEAK_CONFIRM_WINDOW_MS", "WEAK_MAX_PREFIX_TOKENS", "COMMAND_THEN_WAKE_MAX", "IDLE_NOISE_RESET_TOKENS", "IDLE_TRAILING_KEEP_TOKENS", "WAKE_DEDUP_MS", "EMIT_DEDUP_MS", "wakeword-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<String> getWAKE_PATTERNS() {
            return WakeWordEngine.WAKE_PATTERNS;
        }

        /* JADX WARN: Code duplicated, block: B:36:0x00aa  */
        /* JADX WARN: Code duplicated, block: B:58:0x0100  */
        public final boolean textContainsWake(String text) {
            char c;
            boolean z;
            boolean z2;
            Intrinsics.checkNotNullParameter(text, "text");
            boolean z3 = false;
            if (StringsKt.isBlank(text)) {
                return false;
            }
            char c2 = 2;
            Iterable tokens = SequencesKt.toList(SequencesKt.map(Regex.findAll$default(WakeWordEngine.TOKEN_REGEX, text, 0, 2, null), new Function1() { // from class: com.jarvis.wakeword.WakeWordEngine$Companion$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return WakeWordEngine.Companion.textContainsWake$lambda$0((MatchResult) obj);
                }
            }));
            Iterable $this$any$iv = tokens;
            if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
                return false;
            }
            for (Object element$iv : $this$any$iv) {
                String token = (String) element$iv;
                if (WakeWordEngine.INSTANCE.getWAKE_PATTERNS().contains(token)) {
                    z = true;
                    c = c2;
                } else if (token.length() >= 6) {
                    Iterable $this$any$iv2 = WakeWordEngine.FUZZY_CORES;
                    if (!($this$any$iv2 instanceof Collection) || !((Collection) $this$any$iv2).isEmpty()) {
                        Iterator it = $this$any$iv2.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                Object element$iv2 = it.next();
                                String core = (String) element$iv2;
                                if (WakeWordEngine.INSTANCE.companionLevenshtein(token, core) <= 1) {
                                    z3 = true;
                                    break;
                                }
                            } else {
                                z3 = false;
                                break;
                            }
                        }
                    }
                    if (z3) {
                        z = true;
                        c = 2;
                    } else {
                        if (token.length() < 6 && WakeWordEngine.CONFIRM_PREFIXES.contains(StringsKt.take(token, 2))) {
                            Iterable $this$any$iv3 = WakeWordEngine.FUZZY_CORES;
                            if (!($this$any$iv3 instanceof Collection) || !((Collection) $this$any$iv3).isEmpty()) {
                                Iterator it2 = $this$any$iv3.iterator();
                                while (true) {
                                    if (it2.hasNext()) {
                                        Object element$iv3 = it2.next();
                                        String core2 = (String) element$iv3;
                                        c = 2;
                                        if (WakeWordEngine.INSTANCE.companionLevenshtein(token, core2) <= 2) {
                                            z2 = true;
                                            break;
                                        }
                                    } else {
                                        c = 2;
                                        z2 = false;
                                        break;
                                    }
                                }
                            } else {
                                z2 = false;
                                c = 2;
                            }
                            if (z2) {
                                z = true;
                            }
                        } else {
                            c = 2;
                        }
                        z = false;
                    }
                } else if (token.length() < 6) {
                    c = 2;
                    z = false;
                } else {
                    c = 2;
                    z = false;
                }
                if (z) {
                    return true;
                }
                c2 = c;
                z3 = false;
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String textContainsWake$lambda$0(MatchResult it) {
            Intrinsics.checkNotNullParameter(it, "it");
            String lowerCase = it.getValue().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            return lowerCase;
        }

        private final int companionLevenshtein(String a, String b) {
            int length = b.length() + 1;
            int[] dp = new int[length];
            for (int i = 0; i < length; i++) {
                dp[i] = i;
            }
            int i2 = 1;
            int length2 = a.length();
            if (1 <= length2) {
                while (true) {
                    int prev = dp[0];
                    dp[0] = i2;
                    int j = 1;
                    int length3 = b.length();
                    if (1 <= length3) {
                        while (true) {
                            int tmp = dp[j];
                            dp[j] = Math.min(dp[j] + 1, Math.min(dp[j - 1] + 1, (a.charAt(i2 + (-1)) == b.charAt(j + (-1)) ? 0 : 1) + prev));
                            prev = tmp;
                            if (j == length3) {
                                break;
                            }
                            j++;
                        }
                    }
                    if (i2 == length2) {
                        break;
                    }
                    i2++;
                }
            }
            return dp[b.length()];
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int fuzzyDistFor(int sensitivity) {
            switch (sensitivity) {
                case 0:
                case 1:
                default:
                    return 1;
                case 2:
                    return 2;
            }
        }
    }
}
