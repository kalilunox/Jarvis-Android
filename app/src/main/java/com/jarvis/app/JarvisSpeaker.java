package com.jarvis.app;

import android.content.Context;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import com.jarvis.app.tts.AndroidSystemTtsBackend;
import com.jarvis.app.tts.HybridTtsBridge;
import com.jarvis.core.nlp.AddressRhythm;
import com.jarvis.core.nlp.JarvisStyle;
import com.jarvis.core.nlp.SpeechTextNormalizer;
import com.jarvis.core.nlp.Translator;
import com.jarvis.core.voice.TtsAudioResult;
import com.jarvis.core.voice.TtsTelemetry;
import com.jarvis.core.voice.TtsVoiceConfig;
import com.jarvis.tts.JarvisTts;
import com.jarvis.tts.JarvisTtsConfig;
import com.jarvis.tts.JarvisVoiceFx;
import com.jarvis.tts.TtsPlayer;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.random.Random;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: JarvisSpeaker.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0014\n\u0002\b\u000f\n\u0002\u0010 \n\u0002\b\u0005\u0018\u0000 i2\u00020\u0001:\u0002ijB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u00109\u001a\u00020:H\u0002J\u0010\u0010;\u001a\u00020:2\b\b\u0002\u0010<\u001a\u00020)J\u0006\u0010=\u001a\u00020:J6\u0010>\u001a\u00020:2\u0006\u0010?\u001a\u00020@2\u000e\b\u0002\u0010A\u001a\b\u0012\u0004\u0012\u00020:0B2\u0016\b\u0002\u0010C\u001a\u0010\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020:\u0018\u00010DJ\u0006\u0010E\u001a\u00020:J\u0006\u0010F\u001a\u00020)J>\u0010G\u001a\u00020:2\u0006\u0010?\u001a\u00020@2\u0006\u0010H\u001a\u00020\"2\u0016\b\u0002\u0010C\u001a\u0010\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020:\u0018\u00010D2\u000e\b\u0002\u0010I\u001a\b\u0012\u0004\u0012\u00020:0BJ\u0006\u0010J\u001a\u00020\"J\u0016\u0010K\u001a\u00020:2\u000e\b\u0002\u0010A\u001a\b\u0012\u0004\u0012\u00020:0BJ.\u0010T\u001a\u0010\u0012\u0004\u0012\u00020V\u0012\u0004\u0012\u00020\"\u0018\u00010U2\u0006\u0010W\u001a\u00020@2\u0006\u0010X\u001a\u00020@2\u0006\u0010Y\u001a\u00020\"H\u0002J,\u0010Z\u001a\u000e\u0012\u0004\u0012\u00020V\u0012\u0004\u0012\u00020\"0U2\u0006\u0010W\u001a\u00020@2\u0006\u0010Y\u001a\u00020\"2\u0006\u0010[\u001a\u00020\"H\u0002J0\u0010\\\u001a\u00020:2\u0006\u0010]\u001a\u00020N2\u0006\u0010^\u001a\u00020V2\u0006\u0010_\u001a\u00020\"2\u0006\u0010`\u001a\u0002012\u0006\u0010a\u001a\u000201H\u0002J\b\u0010b\u001a\u00020:H\u0002J\b\u0010c\u001a\u00020:H\u0002J8\u0010d\u001a\u00020:2\u0006\u0010?\u001a\u00020@2\u000e\b\u0002\u0010A\u001a\b\u0012\u0004\u0012\u00020:0B2\u0016\b\u0002\u0010C\u001a\u0010\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020:\u0018\u00010DH\u0002J\u001b\u0010e\u001a\b\u0012\u0004\u0012\u00020@0f2\u0006\u0010?\u001a\u00020@H\u0000¢\u0006\u0002\bgJ\u0006\u0010h\u001a\u00020:R\u0016\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n \u0007*\u0004\u0018\u00010\u00120\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n \u0007*\u0004\u0018\u00010\u00120\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\n \u0007*\u0004\u0018\u00010\u00120\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010%\u001a\u0004\u0018\u00010$2\b\u0010#\u001a\u0004\u0018\u00010$@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010(\u001a\u00020)8F¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0011\u0010,\u001a\u00020)8F¢\u0006\u0006\u001a\u0004\b-\u0010+R\u0011\u0010.\u001a\u00020)8F¢\u0006\u0006\u001a\u0004\b/\u0010+R\u0011\u00100\u001a\u0002018F¢\u0006\u0006\u001a\u0004\b2\u00103R\u000e\u00104\u001a\u000201X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u00106\u001a\u00020)2\u0006\u0010#\u001a\u00020)@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b7\u0010+R\u000e\u00108\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010L\u001a\b\u0012\u0004\u0012\u00020N0MX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010O\u001a\u00020PX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010Q\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006k"}, d2 = {"Lcom/jarvis/app/JarvisSpeaker;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "appCtx", "kotlin.jvm.PlatformType", "foreignVoice", "Lcom/jarvis/app/tts/AndroidSystemTtsBackend;", "getForeignVoice", "()Lcom/jarvis/app/tts/AndroidSystemTtsBackend;", "foreignVoice$delegate", "Lkotlin/Lazy;", "player", "Lcom/jarvis/tts/TtsPlayer;", "chimePlayer", "ttsExecutor", "Ljava/util/concurrent/ExecutorService;", "ttsDispatcher", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "chimeExecutor", "playExecutor", "readAhead", "Ljava/util/concurrent/Semaphore;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "tts", "Lcom/jarvis/tts/JarvisTts;", "hybrid", "Lcom/jarvis/app/tts/HybridTtsBridge;", "speakJob", "Lkotlinx/coroutines/Job;", "speakGeneration", "", "value", "Lcom/jarvis/core/voice/TtsTelemetry;", "lastTtsTelemetry", "getLastTtsTelemetry", "()Lcom/jarvis/core/voice/TtsTelemetry;", "ttsAvailable", "", "getTtsAvailable", "()Z", "playing", "getPlaying", "speaking", "getSpeaking", "lastPlayEndAtMs", "", "getLastPlayEndAtMs", "()J", "_lastPlayEndAtMs", "speechPlaying", "saysOwnName", "getSaysOwnName", "listeningTurn", "setPlayEnd", "", "onWakeDetected", "chime", "sayListening", "speakResponse", VoiceService.EXTRA_TEXT, "", "onComplete", "Lkotlin/Function0;", "onFirstAudio", "Lkotlin/Function1;", "stop", "isSpeaking", "enqueueSpeak", "streamGeneration", "onChunkDone", "beginStreamSpeak", "endStreamSpeak", "utteranceQueue", "Lkotlin/collections/ArrayDeque;", "Lcom/jarvis/app/JarvisSpeaker$QueuedUtterance;", "address", "Lcom/jarvis/core/nlp/AddressRhythm;", "queuePumpRunning", "streamSpeakGeneration", "streamFirstAudioSent", "foreignPcm", "Lkotlin/Pair;", "", "raw", "locale", "gen", "synthPcm", "queueDepth", "playQueued", "next", "samples", "rate", "t0", "synthMs", "pumpUtteranceQueue", "stopSpeaking", "speak", "splitSentences", "", "splitSentences$app_debug", "release", "Companion", "QueuedUtterance", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JarvisSpeaker {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String DMITRI_ONNX = "ru_RU-dmitri-medium.onnx";
    private static final float DMITRI_SPEED = 1.08f;
    private static final int FULL_SYNTH_MAX_CHARS = 160;
    private static final String JARVIS_VOICE_DIR = "jarvis-voice-ru";
    private static final String JARVIS_VOICE_ONNX = "jarvis.onnx";
    private static final float JARVIS_VOICE_SPEED = 1.12f;
    private static final int MAX_QUEUE_DEPTH = 12;
    private static final int READ_AHEAD_CHUNKS = 2;
    private static final String TAG = "Jarvis.Speaker";
    public static final String TTS_MODEL_DIR = "vits-piper-ru_RU-dmitri-medium";
    private static final String TTS_MODEL_DIR_INT8 = "vits-piper-ru_RU-dmitri-medium-int8";
    private volatile long _lastPlayEndAtMs;
    private final AddressRhythm address;
    private final Context appCtx;
    private final ExecutorService chimeExecutor;
    private final TtsPlayer chimePlayer;

    /* JADX INFO: renamed from: foreignVoice$delegate, reason: from kotlin metadata */
    private final Lazy foreignVoice;
    private HybridTtsBridge hybrid;
    private volatile TtsTelemetry lastTtsTelemetry;
    private int listeningTurn;
    private final ExecutorService playExecutor;
    private final TtsPlayer player;
    private volatile boolean queuePumpRunning;
    private final Semaphore readAhead;
    private volatile boolean saysOwnName;
    private final CoroutineScope scope;
    private volatile int speakGeneration;
    private Job speakJob;
    private volatile boolean speechPlaying;
    private volatile boolean streamFirstAudioSent;
    private volatile int streamSpeakGeneration;
    private JarvisTts tts;
    private final ExecutorCoroutineDispatcher ttsDispatcher;
    private final ExecutorService ttsExecutor;
    private final ArrayDeque<QueuedUtterance> utteranceQueue;

    /* JADX WARN: Multi-variable type inference failed */
    public JarvisSpeaker(Context context) {
        JarvisTtsConfig jarvisTtsConfig;
        File modelDir;
        String name;
        Object objM392constructorimpl;
        Object objM392constructorimpl2;
        Intrinsics.checkNotNullParameter(context, "context");
        this.appCtx = context.getApplicationContext();
        this.foreignVoice = LazyKt.lazy(new Function0() { // from class: com.jarvis.app.JarvisSpeaker$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JarvisSpeaker.foreignVoice_delegate$lambda$0(this.f$0);
            }
        });
        this.player = new TtsPlayer();
        this.chimePlayer = new TtsPlayer();
        this.ttsExecutor = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.jarvis.app.JarvisSpeaker$$ExternalSyntheticLambda5
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                return JarvisSpeaker.ttsExecutor$lambda$0(runnable);
            }
        });
        ExecutorService ttsExecutor = this.ttsExecutor;
        Intrinsics.checkNotNullExpressionValue(ttsExecutor, "ttsExecutor");
        this.ttsDispatcher = ExecutorsKt.from(ttsExecutor);
        this.chimeExecutor = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.jarvis.app.JarvisSpeaker$$ExternalSyntheticLambda6
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                return JarvisSpeaker.chimeExecutor$lambda$0(runnable);
            }
        });
        this.playExecutor = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.jarvis.app.JarvisSpeaker$$ExternalSyntheticLambda7
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                return JarvisSpeaker.playExecutor$lambda$0(runnable);
            }
        });
        this.readAhead = new Semaphore(2);
        Random random = null;
        Object[] objArr = 0;
        int i = 1;
        this.scope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(this.ttsDispatcher));
        Iterator<T> it = INSTANCE.voiceConfigs(context).iterator();
        do {
            if (!it.hasNext()) {
                jarvisTtsConfig = null;
                break;
            }
            JarvisTtsConfig jarvisTtsConfig2 = (JarvisTtsConfig) it.next();
            try {
                Result.Companion companion = Result.INSTANCE;
                this.tts = new JarvisTts(jarvisTtsConfig2);
                objM392constructorimpl = Result.m392constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
            }
            Throwable thM395exceptionOrNullimpl = Result.m395exceptionOrNullimpl(objM392constructorimpl);
            if (thM395exceptionOrNullimpl != null) {
                Log.e(TAG, "TTS init failed: " + jarvisTtsConfig2.getModelDir().getName() + ": " + thM395exceptionOrNullimpl.getMessage());
            }
            if (Result.m399isSuccessimpl(objM392constructorimpl)) {
                Result.Companion companion3 = Result.INSTANCE;
                objM392constructorimpl2 = Result.m392constructorimpl(jarvisTtsConfig2);
            } else {
                objM392constructorimpl2 = Result.m392constructorimpl(objM392constructorimpl);
            }
            jarvisTtsConfig = (JarvisTtsConfig) (Result.m398isFailureimpl(objM392constructorimpl2) ? null : objM392constructorimpl2);
        } while (jarvisTtsConfig == null);
        JarvisTtsConfig jarvisTtsConfig3 = jarvisTtsConfig;
        Context appCtx = this.appCtx;
        Intrinsics.checkNotNullExpressionValue(appCtx, "appCtx");
        this.hybrid = new HybridTtsBridge(appCtx, (jarvisTtsConfig3 == null || (modelDir = jarvisTtsConfig3.getModelDir()) == null || (name = modelDir.getName()) == null) ? TTS_MODEL_DIR : name, new Function0() { // from class: com.jarvis.app.JarvisSpeaker$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.tts;
            }
        });
        if (jarvisTtsConfig3 != null) {
            JarvisTts jarvisTts = this.tts;
            Intrinsics.checkNotNull(jarvisTts);
            Log.i(TAG, "TTS ready (" + jarvisTts.getSampleRate() + " Hz) dir=" + jarvisTtsConfig3.getModelDir().getName() + " speed=" + jarvisTtsConfig3.getSpeed());
            HybridTtsBridge hybridTtsBridge = this.hybrid;
            Intrinsics.checkNotNull(hybridTtsBridge);
            Log.i(TAG, "hybrid TTS " + hybridTtsBridge.decisionLabel());
            BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AnonymousClass2(null), 3, null);
        } else {
            Integer.valueOf(Log.w(TAG, "TTS: нет ни одного локального голоса"));
        }
        this.utteranceQueue = new ArrayDeque<>();
        this.address = new AddressRhythm(random, i, objArr == true ? 1 : 0);
    }

    /* JADX INFO: compiled from: JarvisSpeaker.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/jarvis/app/JarvisSpeaker$Companion;", "", "<init>", "()V", "TTS_MODEL_DIR", "", "TTS_MODEL_DIR_INT8", "JARVIS_VOICE_DIR", "JARVIS_VOICE_ONNX", "JARVIS_VOICE_SPEED", "", "DMITRI_ONNX", "DMITRI_SPEED", "TAG", "FULL_SYNTH_MAX_CHARS", "", "MAX_QUEUE_DEPTH", "READ_AHEAD_CHUNKS", "voiceConfigs", "", "Lcom/jarvis/tts/JarvisTtsConfig;", "context", "Landroid/content/Context;", "isVoiceComplete", "", "dir", "Ljava/io/File;", "onnx", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<JarvisTtsConfig> voiceConfigs(Context context) {
            Object obj;
            Intrinsics.checkNotNullParameter(context, "context");
            File root = new File(context.getExternalFilesDir(null), "models");
            File own = new File(root, JarvisSpeaker.JARVIS_VOICE_DIR);
            List $this$voiceConfigs_u24lambda_u240 = CollectionsKt.createListBuilder();
            if (JarvisSpeaker.INSTANCE.isVoiceComplete(own, JarvisSpeaker.JARVIS_VOICE_ONNX)) {
                $this$voiceConfigs_u24lambda_u240.add(new JarvisTtsConfig(own, JarvisSpeaker.JARVIS_VOICE_ONNX, 4, JarvisSpeaker.JARVIS_VOICE_SPEED));
            }
            Iterable $this$map$iv = CollectionsKt.listOf((Object[]) new String[]{JarvisSpeaker.TTS_MODEL_DIR, JarvisSpeaker.TTS_MODEL_DIR_INT8});
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                destination$iv$iv.add(new File(root, (String) item$iv$iv));
            }
            Iterable $this$firstOrNull$iv = (List) destination$iv$iv;
            Iterator it = $this$firstOrNull$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                Object element$iv = it.next();
                if (new File((File) element$iv, JarvisSpeaker.DMITRI_ONNX).isFile()) {
                    obj = element$iv;
                    break;
                }
            }
            File it2 = (File) obj;
            if (it2 != null) {
                $this$voiceConfigs_u24lambda_u240.add(new JarvisTtsConfig(it2, null, 4, JarvisSpeaker.DMITRI_SPEED, 2, null));
            }
            return CollectionsKt.build($this$voiceConfigs_u24lambda_u240);
        }

        private final boolean isVoiceComplete(File dir, String onnx) {
            return new File(dir, onnx).isFile() && new File(dir, "tokens.txt").isFile() && new File(dir, "espeak-ng-data/ru_dict").isFile();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final AndroidSystemTtsBackend foreignVoice_delegate$lambda$0(JarvisSpeaker jarvisSpeaker) {
        Context appCtx = jarvisSpeaker.appCtx;
        Intrinsics.checkNotNullExpressionValue(appCtx, "appCtx");
        return new AndroidSystemTtsBackend(appCtx, null, 2, 0 == true ? 1 : 0);
    }

    private final AndroidSystemTtsBackend getForeignVoice() {
        return (AndroidSystemTtsBackend) this.foreignVoice.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Thread ttsExecutor$lambda$0(Runnable r) {
        Thread $this$ttsExecutor_u24lambda_u240_u240 = new Thread(r, "jarvis-tts");
        $this$ttsExecutor_u24lambda_u240_u240.setPriority(10);
        return $this$ttsExecutor_u24lambda_u240_u240;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Thread chimeExecutor$lambda$0(Runnable r) {
        return new Thread(r, "jarvis-chime");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Thread playExecutor$lambda$0(Runnable r) {
        Thread $this$playExecutor_u24lambda_u240_u240 = new Thread(r, "jarvis-play");
        $this$playExecutor_u24lambda_u240_u240.setPriority(10);
        return $this$playExecutor_u24lambda_u240_u240;
    }

    public final TtsTelemetry getLastTtsTelemetry() {
        return this.lastTtsTelemetry;
    }

    public final boolean getTtsAvailable() {
        return (this.tts == null && this.hybrid == null) ? false : true;
    }

    public final boolean getPlaying() {
        return this.player.isPlaying();
    }

    /* JADX INFO: renamed from: getSpeaking, reason: from getter */
    public final boolean getSpeechPlaying() {
        return this.speechPlaying;
    }

    /* JADX INFO: renamed from: getLastPlayEndAtMs, reason: from getter */
    public final long get_lastPlayEndAtMs() {
        return this._lastPlayEndAtMs;
    }

    public final boolean getSaysOwnName() {
        return this.saysOwnName;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setPlayEnd() {
        this._lastPlayEndAtMs = SystemClock.elapsedRealtime();
    }

    /* JADX INFO: renamed from: com.jarvis.app.JarvisSpeaker$2, reason: invalid class name */
    /* JADX INFO: compiled from: JarvisSpeaker.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.JarvisSpeaker$2", f = "JarvisSpeaker.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = JarvisSpeaker.this.new AnonymousClass2(continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object objM392constructorimpl;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    JarvisSpeaker jarvisSpeaker = JarvisSpeaker.this;
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        Process.setThreadPriority(-16);
                        JarvisTts jarvisTts = jarvisSpeaker.tts;
                        if (jarvisTts != null) {
                            jarvisTts.synthesize("ок");
                        }
                        objM392constructorimpl = Result.m392constructorimpl(Boxing.boxInt(Log.d(JarvisSpeaker.TAG, "TTS warm-up done")));
                        break;
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
                    }
                    Throwable it = Result.m395exceptionOrNullimpl(objM392constructorimpl);
                    if (it != null) {
                        Log.w(JarvisSpeaker.TAG, "TTS warm-up failed: " + it.getMessage());
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public static /* synthetic */ void onWakeDetected$default(JarvisSpeaker jarvisSpeaker, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        jarvisSpeaker.onWakeDetected(z);
    }

    public final void onWakeDetected(boolean chime) {
        stopSpeaking();
        if (chime) {
            this.chimeExecutor.execute(new Runnable() { // from class: com.jarvis.app.JarvisSpeaker$$ExternalSyntheticLambda11
                @Override // java.lang.Runnable
                public final void run() {
                    JarvisSpeaker.onWakeDetected$lambda$0(this.f$0);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onWakeDetected$lambda$0(JarvisSpeaker this$0) {
        try {
            Result.Companion companion = Result.INSTANCE;
            this$0.chimePlayer.playChime();
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
    }

    public final void sayListening() {
        JarvisStyle jarvisStyle = JarvisStyle.INSTANCE;
        int i = this.listeningTurn;
        this.listeningTurn = i + 1;
        speak$default(this, jarvisStyle.listening(i), null, null, 6, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void speakResponse$default(JarvisSpeaker jarvisSpeaker, String str, Function0 function0, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = new Function0() { // from class: com.jarvis.app.JarvisSpeaker$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 4) != 0) {
            function1 = null;
        }
        jarvisSpeaker.speakResponse(str, function0, function1);
    }

    public final void speakResponse(String text, Function0<Unit> onComplete, Function1<? super Long, Unit> onFirstAudio) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        speak(text, onComplete, onFirstAudio);
    }

    public final void stop() {
        stopSpeaking();
    }

    public final boolean isSpeaking() {
        return this.speechPlaying;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void enqueueSpeak$default(JarvisSpeaker jarvisSpeaker, String str, int i, Function1 function1, Function0 function0, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            function1 = null;
        }
        if ((i2 & 8) != 0) {
            function0 = new Function0() { // from class: com.jarvis.app.JarvisSpeaker$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            };
        }
        jarvisSpeaker.enqueueSpeak(str, i, function1, function0);
    }

    public final void enqueueSpeak(String text, int streamGeneration, Function1<? super Long, Unit> onFirstAudio, Function0<Unit> onChunkDone) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(onChunkDone, "onChunkDone");
        String cleaned = this.address.apply(new Regex("\\s+").replace(StringsKt.trim((CharSequence) text).toString(), StringUtils.SPACE));
        if (cleaned.length() == 0) {
            onChunkDone.invoke();
            return;
        }
        if (this.hybrid == null && this.tts == null) {
            if (onFirstAudio != null) {
                onFirstAudio.invoke(0L);
            }
            onChunkDone.invoke();
            return;
        }
        synchronized (this.utteranceQueue) {
            while (this.utteranceQueue.size() >= 12) {
                QueuedUtterance dropped = this.utteranceQueue.removeFirst();
                dropped.getOnDone().invoke();
            }
            this.utteranceQueue.addLast(new QueuedUtterance(cleaned, streamGeneration, onFirstAudio, onChunkDone, false, 16, null));
            if (!this.queuePumpRunning) {
                this.queuePumpRunning = true;
                pumpUtteranceQueue();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final int beginStreamSpeak() {
        stopSpeaking();
        this.streamSpeakGeneration = this.speakGeneration;
        this.streamFirstAudioSent = false;
        this.address.nextReply();
        return this.speakGeneration;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void endStreamSpeak$default(JarvisSpeaker jarvisSpeaker, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = new Function0() { // from class: com.jarvis.app.JarvisSpeaker$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            };
        }
        jarvisSpeaker.endStreamSpeak(function0);
    }

    public final void endStreamSpeak(Function0<Unit> onComplete) {
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        synchronized (this.utteranceQueue) {
            this.utteranceQueue.addLast(new QueuedUtterance("", this.streamSpeakGeneration, null, onComplete, true));
            if (!this.queuePumpRunning) {
                this.queuePumpRunning = true;
                pumpUtteranceQueue();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: JarvisSpeaker.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0016\b\u0082\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\u0017\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007HÆ\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\t0\u000bHÆ\u0003J\t\u0010\u001d\u001a\u00020\rHÆ\u0003JO\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u00072\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b2\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0013\u0010\u001f\u001a\u00020\r2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\u0005HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001f\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0018¨\u0006#"}, d2 = {"Lcom/jarvis/app/JarvisSpeaker$QueuedUtterance;", "", VoiceService.EXTRA_TEXT, "", "streamGen", "", "onFirstAudio", "Lkotlin/Function1;", "", "", "onDone", "Lkotlin/Function0;", "isEndMarker", "", "<init>", "(Ljava/lang/String;ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Z)V", "getText", "()Ljava/lang/String;", "getStreamGen", "()I", "getOnFirstAudio", "()Lkotlin/jvm/functions/Function1;", "getOnDone", "()Lkotlin/jvm/functions/Function0;", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    static final /* data */ class QueuedUtterance {
        private final boolean isEndMarker;
        private final Function0<Unit> onDone;
        private final Function1<Long, Unit> onFirstAudio;
        private final int streamGen;
        private final String text;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ QueuedUtterance copy$default(QueuedUtterance queuedUtterance, String str, int i, Function1 function1, Function0 function0, boolean z, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = queuedUtterance.text;
            }
            if ((i2 & 2) != 0) {
                i = queuedUtterance.streamGen;
            }
            int i3 = i;
            if ((i2 & 4) != 0) {
                function1 = queuedUtterance.onFirstAudio;
            }
            Function1 function2 = function1;
            if ((i2 & 8) != 0) {
                function0 = queuedUtterance.onDone;
            }
            Function0 function3 = function0;
            if ((i2 & 16) != 0) {
                z = queuedUtterance.isEndMarker;
            }
            return queuedUtterance.copy(str, i3, function2, function3, z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getText() {
            return this.text;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getStreamGen() {
            return this.streamGen;
        }

        public final Function1<Long, Unit> component3() {
            return this.onFirstAudio;
        }

        public final Function0<Unit> component4() {
            return this.onDone;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getIsEndMarker() {
            return this.isEndMarker;
        }

        public final QueuedUtterance copy(String text, int streamGen, Function1<? super Long, Unit> onFirstAudio, Function0<Unit> onDone, boolean isEndMarker) {
            Intrinsics.checkNotNullParameter(text, "text");
            Intrinsics.checkNotNullParameter(onDone, "onDone");
            return new QueuedUtterance(text, streamGen, onFirstAudio, onDone, isEndMarker);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof QueuedUtterance)) {
                return false;
            }
            QueuedUtterance queuedUtterance = (QueuedUtterance) other;
            return Intrinsics.areEqual(this.text, queuedUtterance.text) && this.streamGen == queuedUtterance.streamGen && Intrinsics.areEqual(this.onFirstAudio, queuedUtterance.onFirstAudio) && Intrinsics.areEqual(this.onDone, queuedUtterance.onDone) && this.isEndMarker == queuedUtterance.isEndMarker;
        }

        public int hashCode() {
            return (((((((this.text.hashCode() * 31) + Integer.hashCode(this.streamGen)) * 31) + (this.onFirstAudio == null ? 0 : this.onFirstAudio.hashCode())) * 31) + this.onDone.hashCode()) * 31) + Boolean.hashCode(this.isEndMarker);
        }

        public String toString() {
            return "QueuedUtterance(text=" + this.text + ", streamGen=" + this.streamGen + ", onFirstAudio=" + this.onFirstAudio + ", onDone=" + this.onDone + ", isEndMarker=" + this.isEndMarker + ")";
        }

        /* JADX WARN: Multi-variable type inference failed */
        public QueuedUtterance(String text, int streamGen, Function1<? super Long, Unit> function1, Function0<Unit> onDone, boolean isEndMarker) {
            Intrinsics.checkNotNullParameter(text, "text");
            Intrinsics.checkNotNullParameter(onDone, "onDone");
            this.text = text;
            this.streamGen = streamGen;
            this.onFirstAudio = function1;
            this.onDone = onDone;
            this.isEndMarker = isEndMarker;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public /* synthetic */ QueuedUtterance(String str, int i, Function1 function1, Function0 function0, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            boolean z2;
            if ((i2 & 16) == 0) {
                z2 = z;
            } else {
                z2 = false;
            }
            this(str, i, function1, function0, z2);
        }

        public final String getText() {
            return this.text;
        }

        public final int getStreamGen() {
            return this.streamGen;
        }

        public final Function1<Long, Unit> getOnFirstAudio() {
            return this.onFirstAudio;
        }

        public final Function0<Unit> getOnDone() {
            return this.onDone;
        }

        public final boolean isEndMarker() {
            return this.isEndMarker;
        }
    }

    private final Pair<float[], Integer> foreignPcm(String raw, String locale, final int gen) {
        TtsAudioResult result = getForeignVoice().synthesize(raw, new TtsVoiceConfig(locale, null, null, 0.0f, 0.0f, 0, 0, 126, null), new Function0() { // from class: com.jarvis.app.JarvisSpeaker$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(JarvisSpeaker.foreignPcm$lambda$0(gen, this));
            }
        });
        float[] it = result.getSamples();
        if (it.length == 0) {
            it = null;
        }
        if (it != null) {
            return TuplesKt.to(JarvisVoiceFx.INSTANCE.process(it, result.getSampleRateHz()), Integer.valueOf(result.getSampleRateHz()));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean foreignPcm$lambda$0(int $gen, JarvisSpeaker this$0) {
        return $gen != this$0.speakGeneration;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pair<float[], Integer> synthPcm(String raw, final int gen, int queueDepth) {
        Pair<float[], Integer> pairForeignPcm;
        String locale = Translator.INSTANCE.speechLocale(raw);
        if (locale != null && (pairForeignPcm = foreignPcm(raw, locale, gen)) != null) {
            return pairForeignPcm;
        }
        this.saysOwnName = StringsKt.contains((CharSequence) raw, (CharSequence) "джарвис", true);
        String text = SpeechTextNormalizer.INSTANCE.forSpeech(raw);
        if (StringsKt.isBlank(text)) {
            return TuplesKt.to(new float[0], 22050);
        }
        HybridTtsBridge bridge = this.hybrid;
        if (bridge != null) {
            TtsAudioResult result = bridge.synthesize(text, new Function0() { // from class: com.jarvis.app.JarvisSpeaker$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(JarvisSpeaker.synthPcm$lambda$1(gen, this));
                }
            }, queueDepth);
            this.lastTtsTelemetry = bridge.getLastTelemetry();
            return TuplesKt.to(JarvisVoiceFx.INSTANCE.process(result.getSamples(), result.getSampleRateHz()), Integer.valueOf(result.getSampleRateHz()));
        }
        JarvisTts engine = this.tts;
        if (engine == null) {
            return TuplesKt.to(new float[0], 22050);
        }
        return gen != this.speakGeneration ? TuplesKt.to(new float[0], Integer.valueOf(engine.getSampleRate())) : TuplesKt.to(JarvisVoiceFx.INSTANCE.process(engine.synthesize(text), engine.getSampleRate()), Integer.valueOf(engine.getSampleRate()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean synthPcm$lambda$1(int $gen, JarvisSpeaker this$0) {
        return $gen != this$0.speakGeneration;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playQueued(QueuedUtterance next, float[] samples, int rate, long t0, long synthMs) {
        Long ttsPlaybackMs;
        if (next.getStreamGen() != this.speakGeneration) {
            next.getOnDone().invoke();
            return;
        }
        long jLongValue = 0;
        if (!(samples.length == 0)) {
            if (!this.streamFirstAudioSent) {
                this.streamFirstAudioSent = true;
                long audioMs = SystemClock.elapsedRealtime() - t0;
                TtsTelemetry ttsTelemetry = this.lastTtsTelemetry;
                if (ttsTelemetry != null) {
                    ttsTelemetry.setTtsFirstAudioMs(Long.valueOf(audioMs));
                }
                TtsTelemetry ttsTelemetry2 = this.lastTtsTelemetry;
                if (ttsTelemetry2 != null) {
                    ttsTelemetry2.setTtsFirstPlaybackMs(Long.valueOf(audioMs));
                }
                Function1<Long, Unit> onFirstAudio = next.getOnFirstAudio();
                if (onFirstAudio != null) {
                    onFirstAudio.invoke(Long.valueOf(audioMs));
                }
            }
            long playStart = SystemClock.elapsedRealtime();
            long waited = (playStart - t0) - synthMs;
            this.player.play(samples, rate);
            long playMs = SystemClock.elapsedRealtime() - playStart;
            TtsTelemetry ttsTelemetry3 = this.lastTtsTelemetry;
            if (ttsTelemetry3 != null) {
                TtsTelemetry ttsTelemetry4 = this.lastTtsTelemetry;
                if (ttsTelemetry4 != null && (ttsPlaybackMs = ttsTelemetry4.getTtsPlaybackMs()) != null) {
                    jLongValue = ttsPlaybackMs.longValue();
                }
                ttsTelemetry3.setTtsPlaybackMs(Long.valueOf(jLongValue + playMs));
            }
            Log.d(TAG, "chunk chars=" + next.getText().length() + " audio=" + ((((long) samples.length) * 1000) / ((long) rate)) + "ms synth=" + synthMs + "ms wait=" + waited + "ms play=" + playMs + "ms");
        } else if (!this.streamFirstAudioSent) {
            this.streamFirstAudioSent = true;
            Function1<Long, Unit> onFirstAudio2 = next.getOnFirstAudio();
            if (onFirstAudio2 != null) {
                onFirstAudio2.invoke(0L);
            }
        }
        next.getOnDone().invoke();
    }

    /* JADX INFO: renamed from: com.jarvis.app.JarvisSpeaker$pumpUtteranceQueue$1, reason: invalid class name */
    /* JADX INFO: compiled from: JarvisSpeaker.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.JarvisSpeaker$pumpUtteranceQueue$1", f = "JarvisSpeaker.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return JarvisSpeaker.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Pair pair;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    JarvisSpeaker.this.speechPlaying = true;
                    Object obj = null;
                    try {
                        Process.setThreadPriority(-19);
                        Future<?> futureSubmit = null;
                        while (true) {
                            ArrayDeque arrayDeque = JarvisSpeaker.this.utteranceQueue;
                            JarvisSpeaker jarvisSpeaker = JarvisSpeaker.this;
                            synchronized (arrayDeque) {
                                if (jarvisSpeaker.utteranceQueue.isEmpty()) {
                                    jarvisSpeaker.queuePumpRunning = false;
                                    pair = TuplesKt.to(obj, Boxing.boxInt(0));
                                } else {
                                    pair = TuplesKt.to(jarvisSpeaker.utteranceQueue.removeFirst(), Boxing.boxInt(jarvisSpeaker.utteranceQueue.size()));
                                }
                            }
                            final QueuedUtterance next = (QueuedUtterance) pair.component1();
                            int depth = ((Number) pair.component2()).intValue();
                            if (next == null) {
                                if (futureSubmit != null) {
                                    futureSubmit.get();
                                }
                                JarvisSpeaker.this.speechPlaying = false;
                                JarvisSpeaker.this.setPlayEnd();
                                TtsTelemetry lastTtsTelemetry = JarvisSpeaker.this.getLastTtsTelemetry();
                                if (lastTtsTelemetry != null) {
                                    TtsTelemetry lastTtsTelemetry2 = JarvisSpeaker.this.getLastTtsTelemetry();
                                    lastTtsTelemetry.setTtsTotalMs(lastTtsTelemetry2 != null ? lastTtsTelemetry2.getTtsFirstAudioMs() : null);
                                }
                                ArrayDeque arrayDeque2 = JarvisSpeaker.this.utteranceQueue;
                                JarvisSpeaker jarvisSpeaker2 = JarvisSpeaker.this;
                                synchronized (arrayDeque2) {
                                    if (jarvisSpeaker2.utteranceQueue.isEmpty()) {
                                        jarvisSpeaker2.queuePumpRunning = false;
                                    } else {
                                        jarvisSpeaker2.queuePumpRunning = true;
                                        jarvisSpeaker2.pumpUtteranceQueue();
                                    }
                                    Unit unit = Unit.INSTANCE;
                                }
                                return Unit.INSTANCE;
                            }
                            if (next.getStreamGen() != JarvisSpeaker.this.speakGeneration) {
                                next.getOnDone().invoke();
                            } else if (next.isEndMarker()) {
                                futureSubmit = JarvisSpeaker.this.playExecutor.submit(new Runnable() { // from class: com.jarvis.app.JarvisSpeaker$pumpUtteranceQueue$1$$ExternalSyntheticLambda0
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        JarvisSpeaker.AnonymousClass1.invokeSuspend$lambda$1(next);
                                    }
                                });
                            } else {
                                final long t0 = SystemClock.elapsedRealtime();
                                Pair pairSynthPcm = JarvisSpeaker.this.synthPcm(next.getText(), next.getStreamGen(), depth);
                                final float[] samples = (float[]) pairSynthPcm.component1();
                                final int rate = ((Number) pairSynthPcm.component2()).intValue();
                                final long synthMs = SystemClock.elapsedRealtime() - t0;
                                if (next.getStreamGen() != JarvisSpeaker.this.speakGeneration) {
                                    next.getOnDone().invoke();
                                } else {
                                    JarvisSpeaker.this.readAhead.acquire();
                                    ExecutorService executorService = JarvisSpeaker.this.playExecutor;
                                    final JarvisSpeaker jarvisSpeaker3 = JarvisSpeaker.this;
                                    futureSubmit = executorService.submit(new Runnable() { // from class: com.jarvis.app.JarvisSpeaker$pumpUtteranceQueue$1$$ExternalSyntheticLambda1
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            JarvisSpeaker.AnonymousClass1.invokeSuspend$lambda$2(jarvisSpeaker3, next, samples, rate, t0, synthMs);
                                        }
                                    });
                                    obj = null;
                                }
                            }
                        }
                    } catch (Throwable th) {
                        JarvisSpeaker.this.speechPlaying = false;
                        JarvisSpeaker.this.setPlayEnd();
                        TtsTelemetry lastTtsTelemetry3 = JarvisSpeaker.this.getLastTtsTelemetry();
                        if (lastTtsTelemetry3 != null) {
                            TtsTelemetry lastTtsTelemetry4 = JarvisSpeaker.this.getLastTtsTelemetry();
                            lastTtsTelemetry3.setTtsTotalMs(lastTtsTelemetry4 != null ? lastTtsTelemetry4.getTtsFirstAudioMs() : null);
                        }
                        ArrayDeque arrayDeque3 = JarvisSpeaker.this.utteranceQueue;
                        JarvisSpeaker jarvisSpeaker4 = JarvisSpeaker.this;
                        synchronized (arrayDeque3) {
                            if (jarvisSpeaker4.utteranceQueue.isEmpty()) {
                                jarvisSpeaker4.queuePumpRunning = false;
                            } else {
                                jarvisSpeaker4.queuePumpRunning = true;
                                jarvisSpeaker4.pumpUtteranceQueue();
                            }
                            Unit unit2 = Unit.INSTANCE;
                            throw th;
                        }
                    }
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$1(QueuedUtterance $next) {
            $next.getOnDone().invoke();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$2(JarvisSpeaker this$0, QueuedUtterance $next, float[] $samples, int $rate, long $t0, long $synthMs) {
            try {
                this$0.playQueued($next, $samples, $rate, $t0, $synthMs);
            } finally {
                this$0.readAhead.release();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void pumpUtteranceQueue() {
        this.speakJob = BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AnonymousClass1(null), 3, null);
    }

    private final void stopSpeaking() {
        this.speakGeneration++;
        this.streamFirstAudioSent = false;
        TtsTelemetry ttsTelemetry = this.lastTtsTelemetry;
        if (ttsTelemetry != null) {
            ttsTelemetry.setTtsCancelled(true);
        }
        synchronized (this.utteranceQueue) {
            this.utteranceQueue.clear();
            this.queuePumpRunning = false;
            Unit unit = Unit.INSTANCE;
        }
        Job job = this.speakJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.speechPlaying = false;
        this.player.stop();
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void speak$default(JarvisSpeaker jarvisSpeaker, String str, Function0 function0, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = new Function0() { // from class: com.jarvis.app.JarvisSpeaker$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 4) != 0) {
            function1 = null;
        }
        jarvisSpeaker.speak(str, function0, function1);
    }

    private final void speak(String text, Function0<Unit> onComplete, Function1<? super Long, Unit> onFirstAudio) {
        if (this.hybrid == null && this.tts == null) {
            Log.w(TAG, "speak: TTS engine null");
            if (onFirstAudio != null) {
                onFirstAudio.invoke(0L);
            }
            onComplete.invoke();
            return;
        }
        Job job = this.speakJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.player.stop();
        this.address.nextReply();
        String cleaned = this.address.apply(new Regex("\\s+").replace(StringsKt.trim((CharSequence) text).toString(), StringUtils.SPACE));
        if (!(cleaned.length() == 0)) {
            this.speakGeneration++;
            int gen = this.speakGeneration;
            long t0 = SystemClock.elapsedRealtime();
            this.speakJob = BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C00602(cleaned, gen, t0, onFirstAudio, onComplete, null), 3, null);
            return;
        }
        if (onFirstAudio != null) {
            onFirstAudio.invoke(0L);
        }
        onComplete.invoke();
    }

    /* JADX INFO: renamed from: com.jarvis.app.JarvisSpeaker$speak$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: JarvisSpeaker.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.JarvisSpeaker$speak$2", f = "JarvisSpeaker.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C00602 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $cleaned;
        final /* synthetic */ int $gen;
        final /* synthetic */ Function0<Unit> $onComplete;
        final /* synthetic */ Function1<Long, Unit> $onFirstAudio;
        final /* synthetic */ long $t0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C00602(String str, int i, long j, Function1<? super Long, Unit> function1, Function0<Unit> function0, Continuation<? super C00602> continuation) {
            super(2, continuation);
            this.$cleaned = str;
            this.$gen = i;
            this.$t0 = j;
            this.$onFirstAudio = function1;
            this.$onComplete = function0;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return JarvisSpeaker.this.new C00602(this.$cleaned, this.$gen, this.$t0, this.$onFirstAudio, this.$onComplete, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00602) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:41:0x01b3  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    boolean z = true;
                    JarvisSpeaker.this.speechPlaying = true;
                    try {
                        try {
                            Process.setThreadPriority(-19);
                            final Ref.BooleanRef firstAudioLogged = new Ref.BooleanRef();
                            if (this.$cleaned.length() <= 160) {
                                Log.d(JarvisSpeaker.TAG, "speak full chars=" + this.$cleaned.length());
                                Pair pairSynthPcm = JarvisSpeaker.this.synthPcm(this.$cleaned, this.$gen, 0);
                                float[] samples = (float[]) pairSynthPcm.component1();
                                int rate = ((Number) pairSynthPcm.component2()).intValue();
                                if (this.$gen != JarvisSpeaker.this.speakGeneration) {
                                    Unit unit = Unit.INSTANCE;
                                    JarvisSpeaker.this.speechPlaying = false;
                                    if (this.$gen == JarvisSpeaker.this.speakGeneration) {
                                        this.$onComplete.invoke();
                                    }
                                    return unit;
                                }
                                if (samples.length != 0) {
                                    z = false;
                                }
                                if (z) {
                                    invokeSuspend$noteFirst(firstAudioLogged, this.$gen, JarvisSpeaker.this, this.$t0, this.$onFirstAudio);
                                } else {
                                    invokeSuspend$noteFirst(firstAudioLogged, this.$gen, JarvisSpeaker.this, this.$t0, this.$onFirstAudio);
                                    JarvisSpeaker.this.player.play(samples, rate);
                                }
                            } else {
                                List<String> listSplitSentences$app_debug = JarvisSpeaker.this.splitSentences$app_debug(this.$cleaned);
                                Log.d(JarvisSpeaker.TAG, "speak continuous sentences=" + listSplitSentences$app_debug.size() + " chars=" + this.$cleaned.length());
                                Ref.IntRef rate2 = new Ref.IntRef();
                                rate2.element = 22050;
                                Sequence<float[]> sequence = SequencesKt.sequence(new JarvisSpeaker$speak$2$blocks$1(listSplitSentences$app_debug, this.$gen, JarvisSpeaker.this, rate2, null));
                                TtsPlayer ttsPlayer = JarvisSpeaker.this.player;
                                int i = rate2.element;
                                final int i2 = this.$gen;
                                final JarvisSpeaker jarvisSpeaker = JarvisSpeaker.this;
                                final long j = this.$t0;
                                final Function1<Long, Unit> function1 = this.$onFirstAudio;
                                ttsPlayer.playContinuous(i, new Function0() { // from class: com.jarvis.app.JarvisSpeaker$speak$2$$ExternalSyntheticLambda0
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return JarvisSpeaker.C00602.invokeSuspend$lambda$0(firstAudioLogged, i2, jarvisSpeaker, j, function1);
                                    }
                                }, sequence);
                                if (!firstAudioLogged.element) {
                                    invokeSuspend$noteFirst(firstAudioLogged, this.$gen, JarvisSpeaker.this, this.$t0, this.$onFirstAudio);
                                }
                            }
                            if (this.$gen == JarvisSpeaker.this.speakGeneration) {
                                JarvisSpeaker.this.setPlayEnd();
                                Log.d(JarvisSpeaker.TAG, "tts done " + (SystemClock.elapsedRealtime() - this.$t0) + "ms");
                            }
                            JarvisSpeaker.this.speechPlaying = false;
                            if (this.$gen == JarvisSpeaker.this.speakGeneration) {
                                this.$onComplete.invoke();
                            }
                        } catch (Exception e) {
                            Log.e(JarvisSpeaker.TAG, "speak failed: " + e.getMessage());
                            Function1<Long, Unit> function2 = this.$onFirstAudio;
                            if (function2 != null) {
                                function2.invoke(Boxing.boxLong(0L));
                            }
                            JarvisSpeaker.this.speechPlaying = false;
                            if (this.$gen == JarvisSpeaker.this.speakGeneration) {
                                this.$onComplete.invoke();
                            }
                        }
                        return Unit.INSTANCE;
                    } catch (Throwable th) {
                        JarvisSpeaker.this.speechPlaying = false;
                        if (this.$gen == JarvisSpeaker.this.speakGeneration) {
                            this.$onComplete.invoke();
                        }
                        throw th;
                    }
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        private static final void invokeSuspend$noteFirst(Ref.BooleanRef firstAudioLogged, int gen, JarvisSpeaker this$0, long t0, Function1<? super Long, Unit> function1) {
            if (firstAudioLogged.element || gen != this$0.speakGeneration) {
                return;
            }
            firstAudioLogged.element = true;
            long ms = SystemClock.elapsedRealtime() - t0;
            Log.d(JarvisSpeaker.TAG, "tts first-audio " + ms + "ms");
            TtsTelemetry lastTtsTelemetry = this$0.getLastTtsTelemetry();
            if (lastTtsTelemetry != null) {
                lastTtsTelemetry.setTtsFirstAudioMs(Long.valueOf(ms));
            }
            TtsTelemetry lastTtsTelemetry2 = this$0.getLastTtsTelemetry();
            if (lastTtsTelemetry2 != null) {
                lastTtsTelemetry2.setTtsFirstPlaybackMs(Long.valueOf(ms));
            }
            if (function1 != null) {
                function1.invoke(Long.valueOf(ms));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invokeSuspend$lambda$0(Ref.BooleanRef $firstAudioLogged, int $gen, JarvisSpeaker this$0, long $t0, Function1 $onFirstAudio) {
            invokeSuspend$noteFirst($firstAudioLogged, $gen, this$0, $t0, $onFirstAudio);
            return Unit.INSTANCE;
        }
    }

    public final List<String> splitSentences$app_debug(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        String cleaned = new Regex("\\s+").replace(StringsKt.trim((CharSequence) text).toString(), StringUtils.SPACE);
        if (cleaned.length() == 0) {
            return CollectionsKt.listOf("");
        }
        Iterable $this$map$iv = new Regex("(?<=[.!?…])\\s+").split(cleaned, 0);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            destination$iv$iv.add(StringsKt.trim((CharSequence) it).toString());
        }
        Iterable $this$filter$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it2 = (String) element$iv$iv;
            if (it2.length() > 0) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        List parts = (List) destination$iv$iv2;
        List listListOf = parts;
        if (listListOf.isEmpty()) {
            listListOf = CollectionsKt.listOf(cleaned);
        }
        return listListOf;
    }

    public final void release() {
        this.speakGeneration++;
        CoroutineScopeKt.cancel$default(this.scope, null, 1, null);
        this.ttsExecutor.shutdownNow();
        this.player.release();
        JarvisTts jarvisTts = this.tts;
        if (jarvisTts != null) {
            jarvisTts.release();
        }
        this.tts = null;
    }
}
