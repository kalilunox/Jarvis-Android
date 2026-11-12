package com.jarvis.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.google.android.gms.common.internal.ImagesContract;
import com.jarvis.app.asr.HybridAsrBridge;
import com.jarvis.app.proactive.PhoneStateReader;
import com.jarvis.asr.AsrChunkResult;
import com.jarvis.asr.AsrModelSpec;
import com.jarvis.asr.AsrSession;
import com.jarvis.asr.BufferedEnAsr;
import com.jarvis.asr.CommandPcmRefiner;
import com.jarvis.asr.CommandRefinePasses;
import com.jarvis.asr.OfflineMultilingualAsr;
import com.jarvis.asr.SherpaAsrSession;
import com.jarvis.asr.SherpaKeywordSpotter;
import com.jarvis.audio.AudioCapture;
import com.jarvis.audio.AudioChunk;
import com.jarvis.core.common.model.UserRequest;
import com.jarvis.core.event.AssistantEvent;
import com.jarvis.core.event.JarvisEvents;
import com.jarvis.core.model.AssistantState;
import com.jarvis.core.nlp.AsrPhraseNormalizer;
import com.jarvis.core.nlp.CommandTextSelector;
import com.jarvis.core.voice.AsrPreference;
import com.jarvis.core.voice.AsrRouteContext;
import com.jarvis.core.voice.AsrTelemetry;
import com.jarvis.wakeword.OwwWakeSpotter;
import com.jarvis.wakeword.RecentAudio;
import com.jarvis.wakeword.WakeSpeechGate;
import com.jarvis.wakeword.WakeWordEngine;
import com.jarvis.wakeword.WakeWordState;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: VoiceEngine.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000ð\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0014\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u0091\u00012\u00020\u0001:\u0004\u0090\u0001\u0091\u0001B[\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00060\b\u0012\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00060\b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010J\u0006\u0010E\u001a\u00020\rJ\u0010\u0010F\u001a\u00020\u00062\u0006\u0010G\u001a\u00020HH\u0002J\b\u0010U\u001a\u00020\u0006H\u0002J\b\u0010V\u001a\u00020\u0006H\u0002J\u0010\u0010W\u001a\u00020\u00062\u0006\u0010X\u001a\u00020\rH\u0002J\b\u0010Y\u001a\u00020\u0006H\u0002J\b\u0010Z\u001a\u00020\u0006H\u0002J\b\u0010[\u001a\u00020\u0006H\u0002J\u0010\u0010\\\u001a\u00020\u00062\u0006\u0010G\u001a\u00020HH\u0002J\u0010\u0010]\u001a\u00020\u000b2\u0006\u0010^\u001a\u00020_H\u0002J\u0018\u0010`\u001a\u00020\r2\u0006\u0010a\u001a\u00020H2\u0006\u0010b\u001a\u00020\rH\u0002J\"\u0010d\u001a\u00020\u00062\u0006\u0010e\u001a\u00020+2\u0012\u0010f\u001a\u000e\u0012\u0004\u0012\u00020H\u0012\u0004\u0012\u00020\u00060\bJ\u0010\u0010g\u001a\u00020\r2\u0006\u0010G\u001a\u00020HH\u0002J\b\u0010i\u001a\u00020\u0006H\u0002J\u0006\u0010j\u001a\u00020\u0006J\u0006\u0010k\u001a\u00020\u0006J\u0010\u0010l\u001a\u00020\u00062\u0006\u0010G\u001a\u00020HH\u0002J\b\u0010m\u001a\u00020\u0006H\u0002J\u000e\u0010n\u001a\u00020\u0006H\u0082@¢\u0006\u0002\u0010oJ\b\u0010p\u001a\u00020\u0006H\u0002J \u0010q\u001a\u00020\u00062\u0006\u0010r\u001a\u00020\u000b2\u0006\u0010s\u001a\u00020t2\u0006\u0010u\u001a\u00020\rH\u0002J\u0006\u0010v\u001a\u00020\u0006J\u0006\u0010w\u001a\u00020\u0006J\u0006\u0010y\u001a\u00020+J.\u0010z\u001a\u00020\u00062\u0006\u0010r\u001a\u00020\u000b2\u0006\u0010{\u001a\u00020+2\u0016\b\u0002\u0010|\u001a\u0010\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u0006\u0018\u00010\bJ\u0016\u0010}\u001a\u00020\u00062\u000e\b\u0002\u0010~\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J8\u0010\u007f\u001a\u00020\u00062\u0006\u0010r\u001a\u00020\u000b2\u0010\b\u0002\u0010~\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0016\b\u0002\u0010|\u001a\u0010\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u0006\u0018\u00010\bJ\u0010\u0010\u0080\u0001\u001a\u00020\u00062\u0007\u0010\u0081\u0001\u001a\u00020)J\u0007\u0010\u0082\u0001\u001a\u00020\u0006J\u0007\u0010\u0083\u0001\u001a\u00020\u0006J\u0012\u0010\u0084\u0001\u001a\u00020\u00062\u0007\u0010\u0085\u0001\u001a\u00020\u000bH\u0002J\u001f\u0010\u0086\u0001\u001a\u0005\u0018\u00010\u0087\u00012\u0006\u0010a\u001a\u00020H2\t\b\u0002\u0010\u0088\u0001\u001a\u00020\u000bH\u0002J\u001d\u0010\u0089\u0001\u001a\u00020\r2\b\u0010\u008a\u0001\u001a\u00030\u008b\u00012\b\u0010\u008c\u0001\u001a\u00030\u008d\u0001H\u0002J\u0012\u0010\u008e\u0001\u001a\u00020+2\u0007\u0010\u008f\u0001\u001a\u00020\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00060\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00060\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00100\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u0010\u00105\u001a\u0004\u0018\u000106X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00107\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u00108\u001a\u0012\u0012\u0004\u0012\u00020:09j\b\u0012\u0004\u0012\u00020:`;X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010>\u001a\u0004\u0018\u00010?X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020BX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010J\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010K\u001a\u00020:X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010L\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010M\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010N\u001a\n P*\u0004\u0018\u00010O0OX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010Q\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010R\u001a\u0004\u0018\u00010SX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010c\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\bc\u00102R\u000e\u0010h\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010x\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\bx\u00102¨\u0006\u0092\u0001"}, d2 = {"Lcom/jarvis/app/VoiceEngine;", "", "app", "Landroid/content/Context;", "onWake", "Lkotlin/Function0;", "", "onRequest", "Lkotlin/Function1;", "Lcom/jarvis/core/common/model/UserRequest;", "onPartial", "", "liveMode", "", "wakeEnabled", "<init>", "(Landroid/content/Context;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;ZZ)V", "sessionId", "scope", "Lkotlinx/coroutines/CoroutineScope;", "audioCapture", "Lcom/jarvis/audio/AudioCapture;", "asrSession", "Lcom/jarvis/asr/AsrSession;", "offlineAsr", "Lcom/jarvis/asr/OfflineMultilingualAsr;", "enAsr", "Lcom/jarvis/asr/BufferedEnAsr;", "wakeWordEngine", "Lcom/jarvis/wakeword/WakeWordEngine;", "kwsSpotter", "Lcom/jarvis/wakeword/OwwWakeSpotter;", "keywordSpotter", "Lcom/jarvis/asr/SherpaKeywordSpotter;", "speechGate", "Lcom/jarvis/wakeword/WakeSpeechGate;", "recentAudio", "Lcom/jarvis/wakeword/RecentAudio;", "sampleTake", "Lcom/jarvis/app/VoiceEngine$SampleTake;", "micRestartAtMs", "", "micRestarts", "", "wakeFedAtMs", "speaker", "Lcom/jarvis/app/JarvisSpeaker;", "disposed", "processingBusy", "getProcessingBusy", "()Z", "setProcessingBusy", "(Z)V", "overlayView", "Landroid/view/View;", "overlayScope", "livePcm", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "livePcmLock", "pendingLivePrefix", "hybridAsr", "Lcom/jarvis/app/asr/HybridAsrBridge;", "pauseCaptureForCloudAsr", "hybridFinalMutex", "Lkotlinx/coroutines/sync/Mutex;", "lastHybridFinalAtMs", "suppressLocalWakeFinals", "start", "handleContinuousChunk", "samples", "", "bargeOwwAt", "bargeKeywordAt", "bargePeak", "bargeWake", "wasSpeaking", "audioManager", "Landroid/media/AudioManager;", "kotlin.jvm.PlatformType", "mediaPlaying", "hushRequest", "Landroid/media/AudioFocusRequest;", "hushAtMs", "hushMedia", "releaseHush", "syncHush", "speaking", "dipVolume", "restoreVolume", "reportBargeWindow", "handleBargeIn", "voiceLine", "check", "Lcom/jarvis/app/VoiceIdentity$Check;", "commandVoiceAllowed", "pcm", "awaited", "isIdle", "recordSample", "durationMs", "done", "takeSample", "liveTalkApplied", "syncLiveTalk", "manualWake", "cancelTurn", "handleWakeChunk", "replayDump", "watchMic", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "maybeStartCloudCommandAfterWake", "deliverHybridFinal", VoiceService.EXTRA_TEXT, "tel", "Lcom/jarvis/core/voice/AsrTelemetry;", "liveRefine", "stop", "stopSpeaking", "isSpeaking", "beginStreamSpeak", "enqueueStreamSpeak", "streamGeneration", "onFirstAudio", "endStreamSpeak", "onComplete", "speak", "listenForFollowUp", "timeoutMs", "showOverlay", "hideOverlay", "publishPartial", "partial", "refineCommandPcm", "Lcom/jarvis/asr/CommandRefinePasses;", "streamingRu", "ensureModelFiles", "modelDir", "Ljava/io/File;", "spec", "Lcom/jarvis/asr/AsrModelSpec;", "log", NotificationCompat.CATEGORY_MESSAGE, "SampleTake", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VoiceEngine {
    private static final long BARGE_WINDOW_MS = 1000;
    private static final long ECHO_HOLD_MS = 700;
    private static final long HUSH_AFTER_SPEECH_MS = 800;
    private static final long HUSH_DIP_DELAY_MS = 300;
    private static final long HUSH_MIN_MS = 1500;
    private static final String HUSH_VOLUME_KEY = "hush_saved_volume";
    private static final float HUSH_VOLUME_SHARE = 0.15f;
    private static final long MIC_DEAF_MS = 5000;
    private static final long MIC_HEARTBEAT_MS = 60000;
    private static final long MIC_RESTART_GAP_MS = 30000;
    private static final long MIC_WATCH_PERIOD_MS = 2000;
    private static final String TAG = "Jarvis.Debug";
    private static final int VOICE_CHECK_SAMPLES = 24000;
    private static final long WAKE_HOLDOFF_AFTER_SPEECH_MS = 1500;
    private static int sessionCounter;
    private final Context app;
    private AsrSession asrSession;
    private AudioCapture audioCapture;
    private final AudioManager audioManager;
    private long bargeKeywordAt;
    private long bargeOwwAt;
    private float bargePeak;
    private volatile boolean bargeWake;
    private volatile boolean disposed;
    private BufferedEnAsr enAsr;
    private long hushAtMs;
    private AudioFocusRequest hushRequest;
    private HybridAsrBridge hybridAsr;
    private final Mutex hybridFinalMutex;
    private SherpaKeywordSpotter keywordSpotter;
    private OwwWakeSpotter kwsSpotter;
    private volatile long lastHybridFinalAtMs;
    private final boolean liveMode;
    private final ArrayList<Float> livePcm;
    private final Object livePcmLock;
    private boolean liveTalkApplied;
    private volatile boolean mediaPlaying;
    private long micRestartAtMs;
    private int micRestarts;
    private OfflineMultilingualAsr offlineAsr;
    private final Function1<String, Unit> onPartial;
    private final Function1<UserRequest, Unit> onRequest;
    private final Function0<Unit> onWake;
    private CoroutineScope overlayScope;
    private View overlayView;
    private volatile boolean pauseCaptureForCloudAsr;
    private volatile String pendingLivePrefix;
    private volatile boolean processingBusy;
    private final RecentAudio recentAudio;
    private SampleTake sampleTake;
    private final CoroutineScope scope;
    private final String sessionId;
    private JarvisSpeaker speaker;
    private final WakeSpeechGate speechGate;
    private volatile boolean suppressLocalWakeFinals;
    private final boolean wakeEnabled;
    private volatile long wakeFedAtMs;
    private WakeWordEngine wakeWordEngine;
    private boolean wasSpeaking;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Set<String> SELF_ECHO = SetsKt.setOf((Object[]) new String[]{"слушаю сэр", "слушаю, сэр", "слушаю"});

    /* JADX INFO: renamed from: com.jarvis.app.VoiceEngine$watchMic$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: VoiceEngine.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.VoiceEngine", f = "VoiceEngine.kt", i = {0}, l = {679}, m = "watchMic", n = {"lastBeat"}, s = {"J$0"}, v = 1)
    static final class C00631 extends ContinuationImpl {
        long J$0;
        int label;
        /* synthetic */ Object result;

        C00631(Continuation<? super C00631> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return VoiceEngine.this.watchMic(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public VoiceEngine(Context app, Function0<Unit> onWake, Function1<? super UserRequest, Unit> onRequest, Function1<? super String, Unit> onPartial, boolean liveMode, boolean wakeEnabled) {
        Intrinsics.checkNotNullParameter(app, "app");
        Intrinsics.checkNotNullParameter(onWake, "onWake");
        Intrinsics.checkNotNullParameter(onRequest, "onRequest");
        Intrinsics.checkNotNullParameter(onPartial, "onPartial");
        this.app = app;
        this.onWake = onWake;
        this.onRequest = onRequest;
        this.onPartial = onPartial;
        this.liveMode = liveMode;
        this.wakeEnabled = wakeEnabled;
        sessionCounter++;
        this.sessionId = ExifInterface.GPS_MEASUREMENT_INTERRUPTED + sessionCounter;
        this.scope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getDefault()));
        this.speechGate = new WakeSpeechGate();
        this.recentAudio = new RecentAudio(24000);
        this.livePcm = new ArrayList<>(96000);
        this.livePcmLock = new Object();
        this.pendingLivePrefix = "";
        this.hybridFinalMutex = MutexKt.Mutex$default(false, 1, null);
        this.audioManager = (AudioManager) this.app.getSystemService(AudioManager.class);
    }

    public /* synthetic */ VoiceEngine(Context context, Function0 function0, Function1 function1, Function1 function2, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, function0, function1, (i & 8) != 0 ? new Function1() { // from class: com.jarvis.app.VoiceEngine$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return VoiceEngine._init_$lambda$0((String) obj);
            }
        } : function2, (i & 16) != 0 ? false : z, (i & 32) != 0 ? true : z2);
    }

    public static final /* synthetic */ CommandRefinePasses access$refineCommandPcm(VoiceEngine $this, float[] pcm, String streamingRu) {
        return $this.refineCommandPcm(pcm, streamingRu);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit _init_$lambda$0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    public final boolean getProcessingBusy() {
        return this.processingBusy;
    }

    public final void setProcessingBusy(boolean z) {
        this.processingBusy = z;
    }

    public final boolean start() {
        log("starting");
        restoreVolume();
        JarvisEvents.INSTANCE.publish(new AssistantEvent.StateChanged(AssistantState.STARTING));
        if (!new AudioCapture(this.app).hasPermission()) {
            log("RECORD_AUDIO permission denied (adb shell pm grant com.jarvis.assistant android.permission.RECORD_AUDIO)");
            JarvisEvents.INSTANCE.publish(new AssistantEvent.StateChanged(AssistantState.ERROR));
            return false;
        }
        File modelsRoot = new File(this.app.getExternalFilesDir(null), "models");
        File ruDir = new File(modelsRoot, AsrModelSpec.INSTANCE.getRU_STREAMING().getDirName());
        if (!ruDir.isDirectory()) {
            ruDir.mkdirs();
        }
        if (ruDir.isDirectory()) {
            BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C00621(ruDir, modelsRoot, null), 3, null);
            return true;
        }
        log("model dir missing: " + ruDir.getAbsolutePath());
        JarvisEvents.INSTANCE.publish(new AssistantEvent.Error("VoiceEngine", "ASR model directory is missing", null, 4, null));
        JarvisEvents.INSTANCE.publish(new AssistantEvent.StateChanged(AssistantState.ERROR));
        return false;
    }

    /* JADX INFO: renamed from: com.jarvis.app.VoiceEngine$start$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: VoiceEngine.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.VoiceEngine$start$1", f = "VoiceEngine.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C00621 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ File $modelsRoot;
        final /* synthetic */ File $ruDir;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00621(File file, File file2, Continuation<? super C00621> continuation) {
            super(2, continuation);
            this.$ruDir = file;
            this.$modelsRoot = file2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C00621 c00621 = VoiceEngine.this.new C00621(this.$ruDir, this.$modelsRoot, continuation);
            c00621.L$0 = obj;
            return c00621;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00621) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object objM392constructorimpl;
            Object objM392constructorimpl2;
            String mode;
            BufferedEnAsr bufferedEnAsr;
            OfflineMultilingualAsr offlineMultilingualAsr;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    try {
                        VoiceEngine.this.speaker = new JarvisSpeaker(VoiceEngine.this.app);
                        long loadStart = SystemClock.elapsedRealtime();
                        if (!VoiceEngine.this.ensureModelFiles(this.$ruDir, AsrModelSpec.INSTANCE.getRU_STREAMING())) {
                            VoiceEngine.this.log("RU model files missing in assets");
                            JarvisEvents.INSTANCE.publish(new AssistantEvent.Error("VoiceEngine", "ASR model files missing in assets", null, 4, null));
                            JarvisEvents.INSTANCE.publish(new AssistantEvent.StateChanged(AssistantState.ERROR));
                            return Unit.INSTANCE;
                        }
                        VoiceEngine.this.log("loading RU ASR");
                        String absolutePath = this.$ruDir.getAbsolutePath();
                        Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
                        final SherpaAsrSession ru = new SherpaAsrSession(absolutePath, AsrModelSpec.INSTANCE.getRU_STREAMING());
                        VoiceEngine.this.asrSession = ru;
                        VoiceEngine voiceEngine = VoiceEngine.this;
                        File file = this.$modelsRoot;
                        VoiceEngine voiceEngine2 = VoiceEngine.this;
                        boolean z = false;
                        try {
                            Result.Companion companion = Result.INSTANCE;
                            File multiDir = new File(file, OfflineMultilingualAsr.MODEL_DIR);
                            if (!multiDir.isDirectory()) {
                                multiDir.mkdirs();
                            }
                            Iterable $this$any$iv = CollectionsKt.listOf((Object[]) new String[]{"model.int8.onnx", "model.onnx"});
                            if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                                Iterator it = $this$any$iv.iterator();
                                while (true) {
                                    if (it.hasNext()) {
                                        Object element$iv = it.next();
                                        if (new File(multiDir, (String) element$iv).isFile()) {
                                            z = true;
                                        }
                                    } else {
                                        z = false;
                                    }
                                }
                            }
                            boolean hasModel = z && new File(multiDir, "tokens.txt").isFile();
                            if (hasModel) {
                                voiceEngine2.log("loading offline multilingual");
                                String absolutePath2 = multiDir.getAbsolutePath();
                                Intrinsics.checkNotNullExpressionValue(absolutePath2, "getAbsolutePath(...)");
                                offlineMultilingualAsr = new OfflineMultilingualAsr(absolutePath2);
                            } else {
                                voiceEngine2.log("offline multilingual missing");
                                offlineMultilingualAsr = null;
                            }
                            objM392constructorimpl = Result.m392constructorimpl(offlineMultilingualAsr);
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.INSTANCE;
                            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
                        }
                        VoiceEngine voiceEngine3 = VoiceEngine.this;
                        Throwable it2 = Result.m395exceptionOrNullimpl(objM392constructorimpl);
                        if (it2 != null) {
                            voiceEngine3.log("offline multilingual failed: " + it2.getMessage());
                            objM392constructorimpl = null;
                        }
                        voiceEngine.offlineAsr = (OfflineMultilingualAsr) objM392constructorimpl;
                        VoiceEngine voiceEngine4 = VoiceEngine.this;
                        File file2 = this.$modelsRoot;
                        VoiceEngine voiceEngine5 = VoiceEngine.this;
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            File enDir = new File(file2, AsrModelSpec.INSTANCE.getEN_STREAMING().getDirName());
                            if (!enDir.isDirectory()) {
                                enDir.mkdirs();
                            }
                            if (voiceEngine5.ensureModelFiles(enDir, AsrModelSpec.INSTANCE.getEN_STREAMING())) {
                                voiceEngine5.log("loading buffered EN ASR");
                                String absolutePath3 = enDir.getAbsolutePath();
                                Intrinsics.checkNotNullExpressionValue(absolutePath3, "getAbsolutePath(...)");
                                bufferedEnAsr = new BufferedEnAsr(absolutePath3, AsrModelSpec.INSTANCE.getEN_STREAMING());
                            } else {
                                voiceEngine5.log("EN model missing");
                                bufferedEnAsr = null;
                            }
                            objM392constructorimpl2 = Result.m392constructorimpl(bufferedEnAsr);
                        } catch (Throwable th2) {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM392constructorimpl2 = Result.m392constructorimpl(ResultKt.createFailure(th2));
                        }
                        VoiceEngine voiceEngine6 = VoiceEngine.this;
                        Throwable it3 = Result.m395exceptionOrNullimpl(objM392constructorimpl2);
                        if (it3 != null) {
                            voiceEngine6.log("EN ASR failed: " + it3.getMessage());
                            objM392constructorimpl2 = null;
                        }
                        voiceEngine4.enAsr = (BufferedEnAsr) objM392constructorimpl2;
                        if (VoiceEngine.this.disposed) {
                            AsrSession asrSession = VoiceEngine.this.asrSession;
                            if (asrSession != null) {
                                asrSession.release();
                            }
                            OfflineMultilingualAsr offlineMultilingualAsr2 = VoiceEngine.this.offlineAsr;
                            if (offlineMultilingualAsr2 != null) {
                                offlineMultilingualAsr2.close();
                            }
                            BufferedEnAsr bufferedEnAsr2 = VoiceEngine.this.enAsr;
                            if (bufferedEnAsr2 != null) {
                                bufferedEnAsr2.close();
                            }
                            return Unit.INSTANCE;
                        }
                        VoiceEngine.this.log("ASR loaded in " + (SystemClock.elapsedRealtime() - loadStart) + "ms (multi=" + (VoiceEngine.this.offlineAsr != null) + ", en=" + (VoiceEngine.this.enAsr != null) + ")");
                        Context context = VoiceEngine.this.app;
                        Function0 function0 = new Function0() { // from class: com.jarvis.app.VoiceEngine$start$1$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return VoiceEngine.C00621.invokeSuspend$lambda$4(ru);
                            }
                        };
                        final VoiceEngine voiceEngine7 = VoiceEngine.this;
                        HybridAsrBridge hybrid = new HybridAsrBridge(context, function0, new Function0() { // from class: com.jarvis.app.VoiceEngine$start$1$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Boolean.valueOf(VoiceEngine.C00621.invokeSuspend$lambda$5(voiceEngine7));
                            }
                        });
                        VoiceEngine.this.hybridAsr = hybrid;
                        VoiceEngine.this.log("hybrid ASR " + hybrid.decisionLabel());
                        AudioCapture capture = new AudioCapture(VoiceEngine.this.app);
                        VoiceEngine.this.audioCapture = capture;
                        if (!VoiceEngine.this.liveMode && VoiceEngine.this.wakeEnabled) {
                            VoiceIdentity.INSTANCE.warm(VoiceEngine.this.app);
                            VoiceEngine.this.kwsSpotter = OwwWakeSpotter.INSTANCE.tryCreate(VoiceEngine.this.app, JarvisSettings.INSTANCE.wakeSensitivity(VoiceEngine.this.app));
                            VoiceEngine voiceEngine8 = VoiceEngine.this;
                            SherpaKeywordSpotter.Companion companion5 = SherpaKeywordSpotter.INSTANCE;
                            String absolutePath4 = this.$ruDir.getAbsolutePath();
                            Intrinsics.checkNotNullExpressionValue(absolutePath4, "getAbsolutePath(...)");
                            voiceEngine8.keywordSpotter = companion5.tryCreate(absolutePath4, new File(VoiceEngine.this.app.getCacheDir(), "wake_keywords.txt"));
                            if (VoiceEngine.this.kwsSpotter != null) {
                                VoiceEngine voiceEngine9 = VoiceEngine.this;
                                CoroutineScope coroutineScope = VoiceEngine.this.scope;
                                AnonymousClass5 anonymousClass5 = new AnonymousClass5(VoiceEngine.this);
                                int iWakeSensitivity = JarvisSettings.INSTANCE.wakeSensitivity(VoiceEngine.this.app);
                                final VoiceEngine voiceEngine10 = VoiceEngine.this;
                                voiceEngine9.wakeWordEngine = new WakeWordEngine(ru, coroutineScope, 0L, 0L, anonymousClass5, iWakeSensitivity, new Function2() { // from class: com.jarvis.app.VoiceEngine$start$1$$ExternalSyntheticLambda2
                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(Object obj, Object obj2) {
                                        return VoiceEngine.access$refineCommandPcm(voiceEngine10, (float[]) obj, (String) obj2);
                                    }
                                }, new AnonymousClass7(VoiceEngine.this), 12, null);
                                VoiceEngine voiceEngine11 = VoiceEngine.this;
                                OwwWakeSpotter owwWakeSpotter = VoiceEngine.this.kwsSpotter;
                                voiceEngine11.log("wake KWS=" + (owwWakeSpotter != null ? owwWakeSpotter.getEngine() : null) + " + sherpa=" + (VoiceEngine.this.keywordSpotter != null) + " (слово=джарвис, idle без ASR)");
                            } else {
                                VoiceEngine.this.log("wake KWS init failed — wake отключён (ASR-wake не используем)");
                            }
                        }
                        if (VoiceEngine.this.speaker == null) {
                            VoiceEngine.this.speaker = new JarvisSpeaker(VoiceEngine.this.app);
                        }
                        boolean continuous = VoiceEngine.this.liveMode || !VoiceEngine.this.wakeEnabled;
                        WakeWordEngine commandEngine = VoiceEngine.this.wakeWordEngine;
                        if (!continuous && commandEngine != null) {
                            BuildersKt__Builders_commonKt.launch$default(VoiceEngine.this.scope, null, null, new AnonymousClass8(commandEngine, VoiceEngine.this, null), 3, null);
                        }
                        if (!continuous && commandEngine != null) {
                            BuildersKt__Builders_commonKt.launch$default(VoiceEngine.this.scope, null, null, new AnonymousClass9(commandEngine, VoiceEngine.this, null), 3, null);
                        }
                        capture.start(VoiceEngine.this.scope);
                        BuildersKt__Builders_commonKt.launch$default(VoiceEngine.this.scope, null, null, new AnonymousClass10(VoiceEngine.this, null), 3, null);
                        BuildersKt__Builders_commonKt.launch$default(VoiceEngine.this.scope, null, null, new AnonymousClass11(capture, VoiceEngine.this, continuous, null), 3, null);
                        if (VoiceEngine.this.disposed) {
                            capture.stop();
                            AsrSession asrSession2 = VoiceEngine.this.asrSession;
                            if (asrSession2 != null) {
                                asrSession2.release();
                            }
                            return Unit.INSTANCE;
                        }
                        if (VoiceEngine.this.liveMode) {
                            mode = "live listening started (hybrid ASR)";
                        } else if (VoiceEngine.this.kwsSpotter != null) {
                            mode = "KWS listening (джарвис) + hybrid command ASR";
                        } else {
                            mode = (!VoiceEngine.this.wakeEnabled || VoiceEngine.this.liveMode) ? "listening started (wake off, hybrid ASR)" : "listening started (wake KWS unavailable)";
                        }
                        VoiceEngine.this.log(mode);
                        JarvisEvents.INSTANCE.publish(new AssistantEvent.StateChanged(AssistantState.READY));
                        break;
                    } catch (Exception e) {
                        VoiceEngine.this.log("start failed: " + e.getMessage());
                        JarvisEvents jarvisEvents = JarvisEvents.INSTANCE;
                        String message = e.getMessage();
                        if (message == null) {
                            message = "Voice start failed";
                        }
                        jarvisEvents.publish(new AssistantEvent.Error("VoiceEngine", message, e));
                        JarvisEvents.INSTANCE.publish(new AssistantEvent.StateChanged(AssistantState.ERROR));
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final AsrSession invokeSuspend$lambda$4(SherpaAsrSession $ruRef) {
            return $ruRef;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean invokeSuspend$lambda$5(VoiceEngine this$0) {
            return (this$0.disposed || this$0.asrSession == null) ? false : true;
        }

        /* JADX INFO: renamed from: com.jarvis.app.VoiceEngine$start$1$5, reason: invalid class name */
        /* JADX INFO: compiled from: VoiceEngine.kt */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        static final /* synthetic */ class AnonymousClass5 extends FunctionReferenceImpl implements Function1<String, Unit> {
            AnonymousClass5(Object obj) {
                super(1, obj, VoiceEngine.class, "publishPartial", "publishPartial(Ljava/lang/String;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                ((VoiceEngine) this.receiver).publishPartial(p0);
            }
        }

        /* JADX INFO: renamed from: com.jarvis.app.VoiceEngine$start$1$7, reason: invalid class name */
        /* JADX INFO: compiled from: VoiceEngine.kt */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        static final /* synthetic */ class AnonymousClass7 extends FunctionReferenceImpl implements Function2<float[], Boolean, Boolean> {
            AnonymousClass7(Object obj) {
                super(2, obj, VoiceEngine.class, "commandVoiceAllowed", "commandVoiceAllowed([FZ)Z", 0);
            }

            public final Boolean invoke(float[] p0, boolean p1) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return Boolean.valueOf(((VoiceEngine) this.receiver).commandVoiceAllowed(p0, p1));
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Boolean invoke(float[] fArr, Boolean bool) {
                return invoke(fArr, bool.booleanValue());
            }
        }

        /* JADX INFO: renamed from: com.jarvis.app.VoiceEngine$start$1$8, reason: invalid class name */
        /* JADX INFO: compiled from: VoiceEngine.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.jarvis.app.VoiceEngine$start$1$8", f = "VoiceEngine.kt", i = {0}, l = {215}, m = "invokeSuspend", n = {"lastWakeAtMs"}, s = {"L$0"}, v = 1)
        static final class AnonymousClass8 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ WakeWordEngine $commandEngine;
            Object L$0;
            int label;
            final /* synthetic */ VoiceEngine this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass8(WakeWordEngine wakeWordEngine, VoiceEngine voiceEngine, Continuation<? super AnonymousClass8> continuation) {
                super(2, continuation);
                this.$commandEngine = wakeWordEngine;
                this.this$0 = voiceEngine;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass8(this.$commandEngine, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass8) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        final Ref.LongRef lastWakeAtMs = new Ref.LongRef();
                        SharedFlow<Unit> wakeDetected = this.$commandEngine.getWakeDetected();
                        final VoiceEngine voiceEngine = this.this$0;
                        this.L$0 = SpillingKt.nullOutSpilledVariable(lastWakeAtMs);
                        this.label = 1;
                        if (wakeDetected.collect(new FlowCollector() { // from class: com.jarvis.app.VoiceEngine.start.1.8.1
                            @Override // kotlinx.coroutines.flow.FlowCollector
                            public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                                return emit((Unit) value, (Continuation<? super Unit>) $completion);
                            }

                            public final Object emit(Unit it, Continuation<? super Unit> continuation) {
                                long now = SystemClock.elapsedRealtime();
                                if (now - lastWakeAtMs.element < 1000) {
                                    return Unit.INSTANCE;
                                }
                                lastWakeAtMs.element = now;
                                voiceEngine.log("wake detected");
                                JarvisEvents.INSTANCE.publish(AssistantEvent.WakeDetected.INSTANCE);
                                JarvisEvents.INSTANCE.publish(new AssistantEvent.StateChanged(AssistantState.LISTENING));
                                JarvisSpeaker jarvisSpeaker = voiceEngine.speaker;
                                if (jarvisSpeaker != null) {
                                    jarvisSpeaker.onWakeDetected(!voiceEngine.bargeWake);
                                }
                                voiceEngine.bargeWake = false;
                                voiceEngine.onWake.invoke();
                                return Unit.INSTANCE;
                            }
                        }, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                throw new KotlinNothingValueException();
            }
        }

        /* JADX INFO: renamed from: com.jarvis.app.VoiceEngine$start$1$9, reason: invalid class name */
        /* JADX INFO: compiled from: VoiceEngine.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.jarvis.app.VoiceEngine$start$1$9", f = "VoiceEngine.kt", i = {}, l = {229}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class AnonymousClass9 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ WakeWordEngine $commandEngine;
            int label;
            final /* synthetic */ VoiceEngine this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass9(WakeWordEngine wakeWordEngine, VoiceEngine voiceEngine, Continuation<? super AnonymousClass9> continuation) {
                super(2, continuation);
                this.$commandEngine = wakeWordEngine;
                this.this$0 = voiceEngine;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass9(this.$commandEngine, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass9) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        SharedFlow<UserRequest> userRequests = this.$commandEngine.getUserRequests();
                        final VoiceEngine voiceEngine = this.this$0;
                        this.label = 1;
                        if (userRequests.collect(new FlowCollector() { // from class: com.jarvis.app.VoiceEngine.start.1.9.1
                            @Override // kotlinx.coroutines.flow.FlowCollector
                            public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                                return emit((UserRequest) value, (Continuation<? super Unit>) $completion);
                            }

                            public final Object emit(UserRequest req, Continuation<? super Unit> continuation) {
                                if (!voiceEngine.suppressLocalWakeFinals) {
                                    HybridAsrBridge hybridAsrBridge = voiceEngine.hybridAsr;
                                    boolean z = false;
                                    if (hybridAsrBridge != null && hybridAsrBridge.isTurnActive()) {
                                        z = true;
                                    }
                                    if (!z) {
                                        if (StringsKt.isBlank(req.getText())) {
                                            voiceEngine.log("bare wake (no command) -> say listening");
                                            JarvisSpeaker jarvisSpeaker = voiceEngine.speaker;
                                            if (jarvisSpeaker != null) {
                                                jarvisSpeaker.sayListening();
                                            }
                                            voiceEngine.onRequest.invoke(req);
                                        } else {
                                            Set<String> self_echo = VoiceEngine.INSTANCE.getSELF_ECHO();
                                            String lowerCase = req.getText().toLowerCase(Locale.ROOT);
                                            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                                            if (self_echo.contains(StringsKt.trim((CharSequence) lowerCase).toString())) {
                                                Boxing.boxInt(voiceEngine.log("ignoring self-echo \"" + req.getText() + "\""));
                                            } else {
                                                voiceEngine.log("req=\"" + req.getText() + "\"");
                                                JarvisEvents.INSTANCE.publish(new AssistantEvent.RequestReceived(req.getText()));
                                                JarvisEvents.INSTANCE.publish(new AssistantEvent.StateChanged(AssistantState.THINKING));
                                                voiceEngine.onRequest.invoke(req);
                                            }
                                        }
                                        return Unit.INSTANCE;
                                    }
                                }
                                voiceEngine.log("suppress local wake final (cloud turn active)");
                                return Unit.INSTANCE;
                            }
                        }, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                throw new KotlinNothingValueException();
            }
        }

        /* JADX INFO: renamed from: com.jarvis.app.VoiceEngine$start$1$10, reason: invalid class name */
        /* JADX INFO: compiled from: VoiceEngine.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.jarvis.app.VoiceEngine$start$1$10", f = "VoiceEngine.kt", i = {}, l = {ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class AnonymousClass10 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ VoiceEngine this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass10(VoiceEngine voiceEngine, Continuation<? super AnonymousClass10> continuation) {
                super(2, continuation);
                this.this$0 = voiceEngine;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass10(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass10) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.label = 1;
                        if (this.this$0.watchMic(this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: com.jarvis.app.VoiceEngine$start$1$11, reason: invalid class name */
        /* JADX INFO: compiled from: VoiceEngine.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.jarvis.app.VoiceEngine$start$1$11", f = "VoiceEngine.kt", i = {}, l = {252}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class AnonymousClass11 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ AudioCapture $capture;
            final /* synthetic */ boolean $continuous;
            int label;
            final /* synthetic */ VoiceEngine this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass11(AudioCapture audioCapture, VoiceEngine voiceEngine, boolean z, Continuation<? super AnonymousClass11> continuation) {
                super(2, continuation);
                this.$capture = audioCapture;
                this.this$0 = voiceEngine;
                this.$continuous = z;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass11(this.$capture, this.this$0, this.$continuous, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass11) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        SharedFlow<AudioChunk> chunks = this.$capture.getChunks();
                        final VoiceEngine voiceEngine = this.this$0;
                        final boolean z = this.$continuous;
                        this.label = 1;
                        if (chunks.collect(new FlowCollector() { // from class: com.jarvis.app.VoiceEngine.start.1.11.1
                            @Override // kotlinx.coroutines.flow.FlowCollector
                            public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                                return emit((AudioChunk) value, (Continuation<? super Unit>) $completion);
                            }

                            public final Object emit(AudioChunk chunk, Continuation<? super Unit> continuation) {
                                voiceEngine.speechGate.onChunk(chunk.getSamples());
                                if (voiceEngine.takeSample(chunk.getSamples())) {
                                    return Unit.INSTANCE;
                                }
                                voiceEngine.reportBargeWindow();
                                voiceEngine.syncLiveTalk();
                                if (VoiceController.INSTANCE.getLiveMuted() && VoiceController.INSTANCE.getLiveTalk()) {
                                    return Unit.INSTANCE;
                                }
                                if (!voiceEngine.getProcessingBusy() && !voiceEngine.disposed) {
                                    JarvisSpeaker sp = voiceEngine.speaker;
                                    if (sp == null) {
                                        return Unit.INSTANCE;
                                    }
                                    boolean talking = z || VoiceController.INSTANCE.getLiveTalk();
                                    if (sp.getSpeechPlaying() && !talking) {
                                        voiceEngine.handleBargeIn(chunk.getSamples());
                                        return Unit.INSTANCE;
                                    }
                                    if (!sp.getSpeechPlaying() && SystemClock.elapsedRealtime() - sp.get_lastPlayEndAtMs() >= 220) {
                                        if (voiceEngine.pauseCaptureForCloudAsr) {
                                            return Unit.INSTANCE;
                                        }
                                        if (talking) {
                                            voiceEngine.handleContinuousChunk(chunk.getSamples());
                                        } else {
                                            voiceEngine.handleWakeChunk(chunk.getSamples());
                                        }
                                        return Unit.INSTANCE;
                                    }
                                    return Unit.INSTANCE;
                                }
                                return Unit.INSTANCE;
                            }
                        }, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                throw new KotlinNothingValueException();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleContinuousChunk(float[] samples) {
        boolean z;
        HybridAsrBridge bridge = this.hybridAsr;
        if (bridge == null) {
            return;
        }
        if (!bridge.isTurnActive()) {
            boolean handoff = HybridAsrBridge.beginTurn$default(bridge, new Function1() { // from class: com.jarvis.app.VoiceEngine$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return VoiceEngine.handleContinuousChunk$lambda$0(this.f$0, (String) obj);
                }
            }, new Function2() { // from class: com.jarvis.app.VoiceEngine$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return VoiceEngine.handleContinuousChunk$lambda$1(this.f$0, (String) obj, (AsrTelemetry) obj2);
                }
            }, false, 4, null);
            this.pauseCaptureForCloudAsr = handoff;
            if (handoff) {
                log("continuous cloud ASR mic handoff");
                AudioCapture audioCapture = this.audioCapture;
                if (audioCapture != null) {
                    audioCapture.stop();
                    return;
                }
                return;
            }
        }
        synchronized (this.livePcmLock) {
            z = false;
            if (this.livePcm.size() < 192000) {
                for (float s : samples) {
                    this.livePcm.add(Float.valueOf(s));
                }
            }
            Unit unit = Unit.INSTANCE;
        }
        bridge.feed(samples);
        if (!this.pauseCaptureForCloudAsr && bridge.preference() == AsrPreference.CLOUD_PREFERRED && bridge.isTurnActive()) {
            AsrSession asrSession = this.asrSession;
            AsrChunkResult probe = asrSession != null ? asrSession.processChunk(samples) : null;
            if (probe != null && probe.isEndpoint()) {
                z = true;
            }
            if (z) {
                AsrSession asrSession2 = this.asrSession;
                if (asrSession2 != null) {
                    asrSession2.reset();
                }
                bridge.finalizeTurn();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit handleContinuousChunk$lambda$0(VoiceEngine this$0, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.publishPartial(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit handleContinuousChunk$lambda$1(VoiceEngine this$0, String text, AsrTelemetry tel) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(tel, "tel");
        this$0.deliverHybridFinal(text, tel, true);
        return Unit.INSTANCE;
    }

    private final void hushMedia() {
        if (this.hushRequest != null) {
            return;
        }
        AudioFocusRequest request = new AudioFocusRequest.Builder(2).setAudioAttributes(new AudioAttributes.Builder().setUsage(16).setContentType(1).build()).setOnAudioFocusChangeListener(new AudioManager.OnAudioFocusChangeListener() { // from class: com.jarvis.app.VoiceEngine$$ExternalSyntheticLambda1
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public final void onAudioFocusChange(int i) {
                VoiceEngine.hushMedia$lambda$0(i);
            }
        }).build();
        AudioManager audioManager = this.audioManager;
        Integer result = audioManager != null ? Integer.valueOf(audioManager.requestAudioFocus(request)) : null;
        this.hushRequest = request;
        this.hushAtMs = SystemClock.elapsedRealtime();
        VoiceHealthLog.INSTANCE.write(this.app, "media hush result=" + result);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void hushMedia$lambda$0(int it) {
    }

    private final void releaseHush() {
        restoreVolume();
        AudioFocusRequest request = this.hushRequest;
        if (request == null) {
            return;
        }
        this.hushRequest = null;
        AudioManager audioManager = this.audioManager;
        if (audioManager != null) {
            audioManager.abandonAudioFocusRequest(request);
        }
        VoiceHealthLog.INSTANCE.write(this.app, "media hush released");
    }

    /* JADX WARN: Code duplicated, block: B:22:0x002a  */
    private final void syncHush(boolean speaking) {
        boolean listening;
        WakeWordEngine wakeWordEngine = this.wakeWordEngine;
        WakeWordState it = wakeWordEngine != null ? wakeWordEngine.getState() : null;
        if ((it == null || it == WakeWordState.IDLE) ? false : true) {
            listening = true;
        } else {
            HybridAsrBridge hybridAsrBridge = this.hybridAsr;
            if (hybridAsrBridge != null && hybridAsrBridge.isTurnActive()) {
                listening = true;
            } else {
                listening = false;
            }
        }
        long now = SystemClock.elapsedRealtime();
        boolean settled = now - this.hushAtMs >= HUSH_DIP_DELAY_MS;
        if (this.hushRequest != null && listening && !speaking && settled && this.mediaPlaying) {
            dipVolume();
        }
        if (!listening || speaking) {
            restoreVolume();
        }
        if (this.hushRequest == null || listening || speaking || this.processingBusy) {
            return;
        }
        JarvisSpeaker jarvisSpeaker = this.speaker;
        long playEnd = jarvisSpeaker != null ? jarvisSpeaker.get_lastPlayEndAtMs() : 0L;
        if (now - this.hushAtMs < 1500 || now - playEnd < HUSH_AFTER_SPEECH_MS) {
            return;
        }
        releaseHush();
    }

    private final void dipVolume() {
        int current;
        int low;
        AudioManager am = this.audioManager;
        if (am == null) {
            return;
        }
        SharedPreferences prefs = JarvisSettings.INSTANCE.prefs(this.app);
        if (!prefs.contains(HUSH_VOLUME_KEY) && (current = am.getStreamVolume(3)) > (low = RangesKt.coerceAtLeast((int) (am.getStreamMaxVolume(3) * HUSH_VOLUME_SHARE), 1))) {
            prefs.edit().putInt(HUSH_VOLUME_KEY, current).commit();
            am.setStreamVolume(3, low, 0);
            VoiceHealthLog.INSTANCE.write(this.app, "media volume dip " + current + " -> " + low);
        }
    }

    private final void restoreVolume() {
        SharedPreferences prefs = JarvisSettings.INSTANCE.prefs(this.app);
        if (prefs.contains(HUSH_VOLUME_KEY)) {
            int saved = prefs.getInt(HUSH_VOLUME_KEY, 0);
            AudioManager audioManager = this.audioManager;
            if (audioManager != null) {
                audioManager.setStreamVolume(3, saved, 0);
            }
            prefs.edit().remove(HUSH_VOLUME_KEY).apply();
            VoiceHealthLog.INSTANCE.write(this.app, "media volume restored " + saved);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reportBargeWindow() {
        JarvisSpeaker jarvisSpeaker = this.speaker;
        boolean z = false;
        boolean speaking = jarvisSpeaker != null && jarvisSpeaker.getSpeechPlaying();
        JarvisSpeaker sp = this.speaker;
        AudioManager audioManager = this.audioManager;
        this.mediaPlaying = audioManager != null && audioManager.isMusicActive();
        syncHush(speaking);
        AudioCapture audioCapture = this.audioCapture;
        if (audioCapture != null) {
            if (speaking || this.mediaPlaying || (sp != null && SystemClock.elapsedRealtime() - sp.get_lastPlayEndAtMs() < 700)) {
                z = true;
            }
            audioCapture.setEchoCancel(z);
        }
        if (this.wasSpeaking && !speaking) {
            VoiceHealthLog voiceHealthLog = VoiceHealthLog.INSTANCE;
            Context context = this.app;
            String str = String.format("%.3f", Arrays.copyOf(new Object[]{Float.valueOf(this.bargePeak)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            voiceHealthLog.write(context, "speech end owwPeak=" + str);
            this.bargePeak = 0.0f;
        }
        this.wasSpeaking = speaking;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleBargeIn(float[] samples) {
        WakeWordEngine engine;
        String strVoiceLine;
        OwwWakeSpotter kws = this.kwsSpotter;
        if (kws == null || (engine = this.wakeWordEngine) == null || engine.getState() != WakeWordState.IDLE) {
            return;
        }
        if (!kws.getArmed()) {
            kws.armForNextWake(0L);
        }
        long now = SystemClock.elapsedRealtime();
        this.recentAudio.push(samples);
        if (kws.process(samples)) {
            this.bargeOwwAt = now;
            kws.armForNextWake(0L);
            VoiceHealthLog.INSTANCE.write(this.app, "barge oww");
        }
        this.bargePeak = Math.max(this.bargePeak, kws.getLastScore());
        SherpaKeywordSpotter sherpaKeywordSpotter = this.keywordSpotter;
        boolean z = false;
        if (sherpaKeywordSpotter != null && sherpaKeywordSpotter.process(samples)) {
            this.bargeKeywordAt = now;
            VoiceHealthLog.INSTANCE.write(this.app, "barge sherpa");
        }
        boolean oww = now - this.bargeOwwAt <= 1000;
        boolean sherpa = now - this.bargeKeywordAt <= 1000;
        JarvisSpeaker jarvisSpeaker = this.speaker;
        boolean heard = !(jarvisSpeaker != null && jarvisSpeaker.getSaysOwnName()) ? !(oww || sherpa) : !(oww && sherpa);
        if (!heard || !this.speechGate.speechPresent()) {
            return;
        }
        this.bargeOwwAt = 0L;
        this.bargeKeywordAt = 0L;
        VoiceIdentity.Check voice = VoiceIdentity.check$default(VoiceIdentity.INSTANCE, this.app, this.recentAudio.snapshot(), false, false, 12, null);
        if (voice != null && !voice.getAccepted()) {
            z = true;
        }
        if (z) {
            VoiceHealthLog.INSTANCE.write(this.app, "barge rejected: voice " + voiceLine(voice));
            this.bargeOwwAt = 0L;
            this.bargeKeywordAt = 0L;
            return;
        }
        VoiceHealthLog voiceHealthLog = VoiceHealthLog.INSTANCE;
        Context context = this.app;
        if (voice == null || (strVoiceLine = voiceLine(voice)) == null) {
            strVoiceLine = "off";
        }
        voiceHealthLog.write(context, "barge-in voice=" + strVoiceLine);
        this.bargeWake = true;
        kws.disarm();
        SherpaKeywordSpotter sherpaKeywordSpotter2 = this.keywordSpotter;
        if (sherpaKeywordSpotter2 != null) {
            sherpaKeywordSpotter2.reset();
        }
        engine.notifyExternalWake();
        maybeStartCloudCommandAfterWake();
    }

    private final String voiceLine(VoiceIdentity.Check check) {
        String str = String.format("%.2f", Arrays.copyOf(new Object[]{Float.valueOf(check.getScore())}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        String str2 = String.format("%.2f", Arrays.copyOf(new Object[]{Float.valueOf(check.getThreshold())}, 1));
        Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
        return str + "/" + str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:14:0x0065  */
    public final boolean commandVoiceAllowed(float[] pcm, boolean awaited) {
        boolean allowed;
        String str;
        VoiceIdentity.Check check = VoiceIdentity.INSTANCE.checkCommand(this.app, pcm, awaited, !this.mediaPlaying);
        if (check != null) {
            allowed = check.getAccepted();
        } else {
            allowed = (awaited && VoiceIdentity.INSTANCE.commandLocked(this.app)) ? false : true;
        }
        if (check != null) {
            String strVoiceLine = voiceLine(check);
            String str2 = String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf(check.getSeconds())}, 1));
            Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
            str = strVoiceLine + StringUtils.SPACE + str2 + "s";
            if (str == null) {
                str = PhoneStateReader.FAKE_NONE;
            }
        } else {
            str = PhoneStateReader.FAKE_NONE;
        }
        String score = str;
        VoiceHealthLog.INSTANCE.write(this.app, "command voice=" + score + " awaited=" + awaited + " media=" + this.mediaPlaying + " allowed=" + allowed);
        if (!allowed) {
            log("command rejected: not the owner's voice");
            JarvisEvents.INSTANCE.publish(new AssistantEvent.StateChanged(AssistantState.READY));
        }
        return allowed;
    }

    public final boolean isIdle() {
        JarvisSpeaker jarvisSpeaker = this.speaker;
        if (!(jarvisSpeaker != null && jarvisSpeaker.getSpeechPlaying()) && !this.processingBusy) {
            WakeWordEngine wakeWordEngine = this.wakeWordEngine;
            if ((wakeWordEngine != null ? wakeWordEngine.getState() : null) == WakeWordState.IDLE) {
                HybridAsrBridge hybridAsrBridge = this.hybridAsr;
                if (!(hybridAsrBridge != null && hybridAsrBridge.isTurnActive())) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void recordSample(int durationMs, Function1<? super float[], Unit> done) {
        Intrinsics.checkNotNullParameter(done, "done");
        this.sampleTake = new SampleTake(durationMs * 16, done);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean takeSample(float[] samples) {
        SampleTake take = this.sampleTake;
        if (take == null) {
            return false;
        }
        if (take.add(samples)) {
            this.sampleTake = null;
            take.getDone().invoke(take.getPcm());
            return true;
        }
        return true;
    }

    /* JADX INFO: compiled from: VoiceEngine.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0014\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0006R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/jarvis/app/VoiceEngine$SampleTake;", "", "size", "", "done", "Lkotlin/Function1;", "", "", "<init>", "(ILkotlin/jvm/functions/Function1;)V", "getDone", "()Lkotlin/jvm/functions/Function1;", "pcm", "getPcm", "()[F", "filled", "add", "", "samples", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class SampleTake {
        private final Function1<float[], Unit> done;
        private int filled;
        private final float[] pcm;

        /* JADX WARN: Multi-variable type inference failed */
        public SampleTake(int size, Function1<? super float[], Unit> done) {
            Intrinsics.checkNotNullParameter(done, "done");
            this.done = done;
            this.pcm = new float[size];
        }

        public final Function1<float[], Unit> getDone() {
            return this.done;
        }

        public final float[] getPcm() {
            return this.pcm;
        }

        public final boolean add(float[] samples) {
            Intrinsics.checkNotNullParameter(samples, "samples");
            int count = Math.min(samples.length, this.pcm.length - this.filled);
            ArraysKt.copyInto(samples, this.pcm, this.filled, 0, count);
            this.filled += count;
            return this.filled == this.pcm.length;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void syncLiveTalk() {
        boolean want = VoiceController.INSTANCE.getLiveTalk();
        if (want == this.liveTalkApplied) {
            return;
        }
        this.liveTalkApplied = want;
        if (want) {
            OwwWakeSpotter owwWakeSpotter = this.kwsSpotter;
            if (owwWakeSpotter != null) {
                owwWakeSpotter.disarm();
            }
            SherpaKeywordSpotter sherpaKeywordSpotter = this.keywordSpotter;
            if (sherpaKeywordSpotter != null) {
                sherpaKeywordSpotter.reset();
            }
            WakeWordEngine wakeWordEngine = this.wakeWordEngine;
            if (wakeWordEngine != null) {
                wakeWordEngine.cancelListening();
            }
            log("live talk on");
            return;
        }
        HybridAsrBridge hybridAsrBridge = this.hybridAsr;
        if (hybridAsrBridge != null) {
            hybridAsrBridge.cancelTurn();
        }
        OwwWakeSpotter owwWakeSpotter2 = this.kwsSpotter;
        if (owwWakeSpotter2 != null) {
            owwWakeSpotter2.armForNextWake(0L);
        }
        SherpaKeywordSpotter sherpaKeywordSpotter2 = this.keywordSpotter;
        if (sherpaKeywordSpotter2 != null) {
            sherpaKeywordSpotter2.reset();
        }
        log("live talk off");
    }

    public final void manualWake() {
        WakeWordEngine engine = this.wakeWordEngine;
        if (engine == null) {
            return;
        }
        JarvisSpeaker jarvisSpeaker = this.speaker;
        this.bargeWake = jarvisSpeaker != null && jarvisSpeaker.getSpeechPlaying();
        if (!this.bargeWake) {
            AudioManager audioManager = this.audioManager;
            if (audioManager != null && audioManager.isMusicActive()) {
                hushMedia();
            }
        }
        OwwWakeSpotter owwWakeSpotter = this.kwsSpotter;
        if (owwWakeSpotter != null) {
            owwWakeSpotter.disarm();
        }
        SherpaKeywordSpotter sherpaKeywordSpotter = this.keywordSpotter;
        if (sherpaKeywordSpotter != null) {
            sherpaKeywordSpotter.reset();
        }
        engine.notifyExternalWake();
        maybeStartCloudCommandAfterWake();
    }

    public final void cancelTurn() {
        WakeWordEngine wakeWordEngine = this.wakeWordEngine;
        if (wakeWordEngine != null) {
            wakeWordEngine.cancelListening();
        }
        OwwWakeSpotter owwWakeSpotter = this.kwsSpotter;
        if (owwWakeSpotter != null) {
            owwWakeSpotter.armForNextWake(0L);
        }
        SherpaKeywordSpotter sherpaKeywordSpotter = this.keywordSpotter;
        if (sherpaKeywordSpotter != null) {
            sherpaKeywordSpotter.reset();
        }
        stopSpeaking();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleWakeChunk(float[] samples) {
        WakeWordEngine engine;
        String strVoiceLine;
        if (WakeAudioDump.INSTANCE.isActive()) {
            WakeAudioDump.INSTANCE.write(samples);
            return;
        }
        if (WakeAudioDump.INSTANCE.takeReplayRequest()) {
            replayDump();
            return;
        }
        OwwWakeSpotter kws = this.kwsSpotter;
        if (kws == null || (engine = this.wakeWordEngine) == null) {
            return;
        }
        HybridAsrBridge bridge = this.hybridAsr;
        WakeWordState before = engine.getState();
        if (bridge != null && bridge.isTurnActive()) {
            synchronized (this.livePcmLock) {
                if (this.livePcm.size() < 192000) {
                    for (float s : samples) {
                        this.livePcm.add(Float.valueOf(s));
                    }
                }
                Unit unit = Unit.INSTANCE;
            }
            bridge.feed(samples);
            if (!this.pauseCaptureForCloudAsr) {
                AsrSession asrSession = this.asrSession;
                AsrChunkResult probe = asrSession != null ? asrSession.processChunk(samples) : null;
                if (probe != null && probe.isEndpoint()) {
                    AsrSession asrSession2 = this.asrSession;
                    if (asrSession2 != null) {
                        asrSession2.reset();
                    }
                    bridge.finalizeTurn();
                    return;
                }
                return;
            }
            return;
        }
        if (before == WakeWordState.IDLE) {
            JarvisSpeaker sp = this.speaker;
            if (sp == null || SystemClock.elapsedRealtime() - sp.get_lastPlayEndAtMs() >= 1500) {
                this.wakeFedAtMs = SystemClock.elapsedRealtime();
                this.recentAudio.push(samples);
                boolean owwHit = kws.process(samples);
                SherpaKeywordSpotter sherpaKeywordSpotter = this.keywordSpotter;
                boolean keywordHit = sherpaKeywordSpotter != null && sherpaKeywordSpotter.process(samples);
                if (owwHit || keywordHit) {
                    if (!this.speechGate.speechPresent()) {
                        VoiceHealthLog voiceHealthLog = VoiceHealthLog.INSTANCE;
                        Context context = this.app;
                        int iVoicedChunks = this.speechGate.voicedChunks();
                        String str = String.format("%.5f", Arrays.copyOf(new Object[]{Float.valueOf(this.speechGate.getFloor())}, 1));
                        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                        voiceHealthLog.write(context, "wake rejected: silence oww=" + owwHit + " sherpa=" + keywordHit + " voiced=" + iVoicedChunks + " floor=" + str);
                        kws.armForNextWake(0L);
                        SherpaKeywordSpotter sherpaKeywordSpotter2 = this.keywordSpotter;
                        if (sherpaKeywordSpotter2 != null) {
                            sherpaKeywordSpotter2.reset();
                            return;
                        }
                        return;
                    }
                    VoiceIdentity.Check voice = VoiceIdentity.INSTANCE.check(this.app, this.recentAudio.snapshot(), !this.mediaPlaying, this.mediaPlaying);
                    if ((voice == null || voice.getAccepted()) ? false : true) {
                        VoiceHealthLog.INSTANCE.write(this.app, "wake rejected: voice " + voiceLine(voice) + " media=" + this.mediaPlaying);
                        OwwWakeSpotter.armForNextWake$default(kws, 0L, 1, null);
                        SherpaKeywordSpotter sherpaKeywordSpotter3 = this.keywordSpotter;
                        if (sherpaKeywordSpotter3 != null) {
                            sherpaKeywordSpotter3.reset();
                            return;
                        }
                        return;
                    }
                    VoiceHealthLog voiceHealthLog2 = VoiceHealthLog.INSTANCE;
                    Context context2 = this.app;
                    if (voice == null || (strVoiceLine = voiceLine(voice)) == null) {
                        strVoiceLine = "off";
                    }
                    String str2 = String.format("%.3f", Arrays.copyOf(new Object[]{Float.valueOf(kws.getLastScore())}, 1));
                    Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
                    int iVoicedChunks2 = this.speechGate.voicedChunks();
                    String str3 = String.format("%.5f", Arrays.copyOf(new Object[]{Float.valueOf(this.speechGate.getFloor())}, 1));
                    Intrinsics.checkNotNullExpressionValue(str3, "format(...)");
                    voiceHealthLog2.write(context2, "wake hit voice=" + strVoiceLine + " oww=" + owwHit + " sherpa=" + keywordHit + " score=" + str2 + " voiced=" + iVoicedChunks2 + " floor=" + str3);
                    log("KWS джарвис hit oww=" + owwHit + " sherpa=" + keywordHit + " → listen command (KWS off)");
                    if (this.mediaPlaying) {
                        hushMedia();
                    }
                    kws.disarm();
                    SherpaKeywordSpotter sherpaKeywordSpotter4 = this.keywordSpotter;
                    if (sherpaKeywordSpotter4 != null) {
                        sherpaKeywordSpotter4.reset();
                    }
                    engine.notifyExternalWake();
                    maybeStartCloudCommandAfterWake();
                    return;
                }
                return;
            }
            SherpaKeywordSpotter sherpaKeywordSpotter5 = this.keywordSpotter;
            if (sherpaKeywordSpotter5 != null) {
                sherpaKeywordSpotter5.reset();
                return;
            }
            return;
        }
        if (!(bridge != null && bridge.isTurnActive())) {
            engine.onAudioChunk(samples);
        }
        if (engine.getState() == WakeWordState.IDLE && before != WakeWordState.IDLE) {
            OwwWakeSpotter.armForNextWake$default(kws, 0L, 1, null);
            SherpaKeywordSpotter sherpaKeywordSpotter6 = this.keywordSpotter;
            if (sherpaKeywordSpotter6 != null) {
                sherpaKeywordSpotter6.reset();
            }
            this.suppressLocalWakeFinals = false;
            log("session done → KWS re-armed");
        }
    }

    private final void replayDump() {
        OwwWakeSpotter kws = this.kwsSpotter;
        if (kws == null) {
            return;
        }
        float[] pcm = WakeAudioDump.INSTANCE.read(this.app);
        long started = SystemClock.elapsedRealtime();
        kws.armForNextWake(0L);
        SherpaKeywordSpotter sherpaKeywordSpotter = this.keywordSpotter;
        if (sherpaKeywordSpotter != null) {
            sherpaKeywordSpotter.reset();
        }
        for (int pos = 0; pos + AudioCapture.CHUNK_SAMPLES <= pcm.length; pos += AudioCapture.CHUNK_SAMPLES) {
            float[] samples = ArraysKt.copyOfRange(pcm, pos, pos + AudioCapture.CHUNK_SAMPLES);
            boolean oww = kws.process(samples);
            SherpaKeywordSpotter sherpaKeywordSpotter2 = this.keywordSpotter;
            boolean z = false;
            if (sherpaKeywordSpotter2 != null && sherpaKeywordSpotter2.process(samples)) {
                z = true;
            }
            boolean sherpa = z;
            if (oww || sherpa) {
                String str = String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf(pos / 16000.0f)}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                log("replay hit t=" + str + " oww=" + oww + " sherpa=" + sherpa);
                kws.armForNextWake(0L);
            }
        }
        log("replay done seconds=" + (pcm.length / 16000) + " tookMs=" + (SystemClock.elapsedRealtime() - started));
        OwwWakeSpotter.armForNextWake$default(kws, 0L, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:16:0x0042  */
    /* JADX WARN: Code duplicated, block: B:18:0x004e A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:21:0x0053 A[ADDED_TO_REGION, REMOVE] */
    /* JADX WARN: Code duplicated, block: B:22:0x005e  */
    /* JADX WARN: Code duplicated, block: B:26:0x0085  */
    /* JADX WARN: Code duplicated, block: B:28:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:29:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:32:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:33:0x00be  */
    /* JADX WARN: Code duplicated, block: B:36:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:38:0x015f  */
    /* JADX WARN: Code duplicated, block: B:41:0x016f  */
    /* JADX WARN: Code duplicated, block: B:47:0x017c  */
    /* JADX WARN: Code duplicated, block: B:49:0x017f  */
    /* JADX WARN: Code duplicated, block: B:51:0x018b  */
    /* JADX WARN: Code duplicated, block: B:52:0x01ea  */
    /* JADX WARN: Code duplicated, block: B:53:0x01f1  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x004c -> B:19:0x004f). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object watchMic(kotlin.coroutines.Continuation<? super kotlin.Unit> r31) {
        /*
            Method dump skipped, instruction units count: 552
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jarvis.app.VoiceEngine.watchMic(kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void maybeStartCloudCommandAfterWake() {
        AudioCapture audioCapture;
        HybridAsrBridge bridge = this.hybridAsr;
        if (bridge != null && bridge.preference() == AsrPreference.CLOUD_PREFERRED) {
            AsrRouteContext ctx = HybridAsrBridge.routeContext$default(bridge, false, 1, null);
            if (ctx.getPrivacyRestricted() || !ctx.isOnline() || !ctx.getCloudHealthy()) {
                return;
            }
            this.suppressLocalWakeFinals = true;
            boolean handoff = HybridAsrBridge.beginTurn$default(bridge, new Function1() { // from class: com.jarvis.app.VoiceEngine$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return VoiceEngine.maybeStartCloudCommandAfterWake$lambda$0(this.f$0, (String) obj);
                }
            }, new Function2() { // from class: com.jarvis.app.VoiceEngine$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return VoiceEngine.maybeStartCloudCommandAfterWake$lambda$1(this.f$0, (String) obj, (AsrTelemetry) obj2);
                }
            }, false, 4, null);
            this.pauseCaptureForCloudAsr = handoff;
            if (handoff && (audioCapture = this.audioCapture) != null) {
                audioCapture.stop();
            }
            log("wake→cloud command handoff=" + handoff + StringUtils.SPACE + bridge.decisionLabel());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit maybeStartCloudCommandAfterWake$lambda$0(VoiceEngine this$0, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.publishPartial(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit maybeStartCloudCommandAfterWake$lambda$1(VoiceEngine this$0, String text, AsrTelemetry tel) {
        AudioCapture audioCapture;
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(tel, "tel");
        this$0.pauseCaptureForCloudAsr = false;
        this$0.suppressLocalWakeFinals = false;
        AudioCapture audioCapture2 = this$0.audioCapture;
        if (!(audioCapture2 != null && audioCapture2.isRecording()) && !this$0.disposed && (audioCapture = this$0.audioCapture) != null) {
            audioCapture.start(this$0.scope);
        }
        this$0.deliverHybridFinal(text, tel, false);
        OwwWakeSpotter owwWakeSpotter = this$0.kwsSpotter;
        if (owwWakeSpotter != null) {
            OwwWakeSpotter.armForNextWake$default(owwWakeSpotter, 0L, 1, null);
        }
        this$0.log("cloud command done → KWS re-armed");
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.jarvis.app.VoiceEngine$deliverHybridFinal$1, reason: invalid class name */
    /* JADX INFO: compiled from: VoiceEngine.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.VoiceEngine$deliverHybridFinal$1", f = "VoiceEngine.kt", i = {0, 0, 0}, l = {1211}, m = "invokeSuspend", n = {"accept", "$this$withLock_u24default$iv", "$i$f$withLock"}, s = {"L$0", "L$1", "I$0"}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $liveRefine;
        final /* synthetic */ AsrTelemetry $tel;
        final /* synthetic */ String $text;
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, boolean z, AsrTelemetry asrTelemetry, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$text = str;
            this.$liveRefine = z;
            this.$tel = asrTelemetry;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return VoiceEngine.this.new AnonymousClass1(this.$text, this.$liveRefine, this.$tel, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Ref.BooleanRef accept;
            Mutex $this$withLock_u24default$iv;
            VoiceEngine voiceEngine;
            Object owner$iv;
            float[] heard;
            AudioCapture audioCapture;
            float[] fArr;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    accept = new Ref.BooleanRef();
                    accept.element = true;
                    $this$withLock_u24default$iv = VoiceEngine.this.hybridFinalMutex;
                    voiceEngine = VoiceEngine.this;
                    owner$iv = null;
                    this.L$0 = accept;
                    this.L$1 = $this$withLock_u24default$iv;
                    this.L$2 = voiceEngine;
                    this.I$0 = 0;
                    this.label = 1;
                    if ($this$withLock_u24default$iv.lock(null, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    int i = this.I$0;
                    voiceEngine = (VoiceEngine) this.L$2;
                    owner$iv = null;
                    $this$withLock_u24default$iv = (Mutex) this.L$1;
                    accept = (Ref.BooleanRef) this.L$0;
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            try {
                long now = SystemClock.elapsedRealtime();
                if (now - voiceEngine.lastHybridFinalAtMs < 400) {
                    accept.element = false;
                } else {
                    voiceEngine.lastHybridFinalAtMs = now;
                }
                Unit unit = Unit.INSTANCE;
                $this$withLock_u24default$iv.unlock(owner$iv);
                if (!accept.element) {
                    VoiceEngine.this.log("hybrid final dedupe skip");
                    return Unit.INSTANCE;
                }
                String out = StringsKt.trim((CharSequence) this.$text).toString();
                Object obj = VoiceEngine.this.livePcmLock;
                VoiceEngine voiceEngine2 = VoiceEngine.this;
                synchronized (obj) {
                    int size = voiceEngine2.livePcm.size();
                    heard = new float[size];
                    for (int i2 = 0; i2 < size; i2++) {
                        Object obj2 = voiceEngine2.livePcm.get(i2);
                        Intrinsics.checkNotNullExpressionValue(obj2, "get(...)");
                        heard[i2] = ((Number) obj2).floatValue();
                    }
                }
                if (!StringsKt.isBlank(out)) {
                    if (!(heard.length == 0) && !VoiceEngine.this.commandVoiceAllowed(heard, this.$liveRefine)) {
                        Object obj3 = VoiceEngine.this.livePcmLock;
                        VoiceEngine voiceEngine3 = VoiceEngine.this;
                        synchronized (obj3) {
                            voiceEngine3.livePcm.clear();
                            Unit unit2 = Unit.INSTANCE;
                        }
                        VoiceEngine.this.pendingLivePrefix = "";
                        return Unit.INSTANCE;
                    }
                }
                if (this.$liveRefine && Intrinsics.areEqual(this.$tel.getAsrRoute(), ImagesContract.LOCAL)) {
                    Object obj4 = VoiceEngine.this.livePcmLock;
                    VoiceEngine voiceEngine4 = VoiceEngine.this;
                    synchronized (obj4) {
                        if (voiceEngine4.livePcm.isEmpty()) {
                            fArr = null;
                        } else {
                            int size2 = voiceEngine4.livePcm.size();
                            fArr = new float[size2];
                            for (int i3 = 0; i3 < size2; i3++) {
                                Object obj5 = voiceEngine4.livePcm.get(i3);
                                Intrinsics.checkNotNullExpressionValue(obj5, "get(...)");
                                fArr[i3] = ((Number) obj5).floatValue();
                            }
                        }
                    }
                    float[] pcmSnapshot = fArr;
                    if (pcmSnapshot != null) {
                        Iterable $this$filter$iv = CollectionsKt.listOf((Object[]) new String[]{VoiceEngine.this.pendingLivePrefix, out});
                        Collection destination$iv$iv = new ArrayList();
                        for (Object element$iv$iv : $this$filter$iv) {
                            String it = (String) element$iv$iv;
                            if (!StringsKt.isBlank(it)) {
                                destination$iv$iv.add(element$iv$iv);
                            }
                        }
                        String streaming = CollectionsKt.joinToString$default((List) destination$iv$iv, StringUtils.SPACE, null, null, 0, null, null, 62, null);
                        CommandRefinePasses refined = VoiceEngine.this.refineCommandPcm(pcmSnapshot, streaming);
                        out = CommandTextSelector.INSTANCE.select(streaming, refined != null ? refined.getMultilingual() : null, refined != null ? refined.getEnglish() : null);
                        if (AsrPhraseNormalizer.INSTANCE.isIncompleteCommand(out)) {
                            VoiceEngine.this.pendingLivePrefix = out;
                            VoiceEngine.this.log("incomplete live utterance \"" + out + "\" — wait for more");
                            Object obj6 = VoiceEngine.this.livePcmLock;
                            VoiceEngine voiceEngine5 = VoiceEngine.this;
                            synchronized (obj6) {
                                voiceEngine5.livePcm.clear();
                                Unit unit3 = Unit.INSTANCE;
                            }
                            HybridAsrBridge hybridAsrBridge = VoiceEngine.this.hybridAsr;
                            if (hybridAsrBridge != null) {
                                final VoiceEngine voiceEngine6 = VoiceEngine.this;
                                Function1 function1 = new Function1() { // from class: com.jarvis.app.VoiceEngine$deliverHybridFinal$1$$ExternalSyntheticLambda0
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj7) {
                                        return VoiceEngine.AnonymousClass1.invokeSuspend$lambda$6(voiceEngine6, (String) obj7);
                                    }
                                };
                                final VoiceEngine voiceEngine7 = VoiceEngine.this;
                                Boxing.boxBoolean(HybridAsrBridge.beginTurn$default(hybridAsrBridge, function1, new Function2() { // from class: com.jarvis.app.VoiceEngine$deliverHybridFinal$1$$ExternalSyntheticLambda1
                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(Object obj7, Object obj8) {
                                        return VoiceEngine.AnonymousClass1.invokeSuspend$lambda$7(voiceEngine7, (String) obj7, (AsrTelemetry) obj8);
                                    }
                                }, false, 4, null));
                            }
                            return Unit.INSTANCE;
                        }
                        VoiceEngine.this.pendingLivePrefix = "";
                    }
                }
                Object obj7 = VoiceEngine.this.livePcmLock;
                VoiceEngine voiceEngine8 = VoiceEngine.this;
                synchronized (obj7) {
                    voiceEngine8.livePcm.clear();
                    Unit unit4 = Unit.INSTANCE;
                }
                VoiceEngine.this.pauseCaptureForCloudAsr = false;
                AudioCapture audioCapture2 = VoiceEngine.this.audioCapture;
                if (!(audioCapture2 != null && audioCapture2.isRecording()) && !VoiceEngine.this.disposed && (audioCapture = VoiceEngine.this.audioCapture) != null) {
                    audioCapture.start(VoiceEngine.this.scope);
                }
                VoiceEngine.this.log("hybrid final provider=" + this.$tel.getAsrProvider() + " route=" + this.$tel.getAsrRoute() + " reason=" + this.$tel.getAsrRouteReason() + " \"" + out + "\"");
                Log.i("Jarvis.Asr", this.$tel.summary());
                if (StringsKt.isBlank(out)) {
                    return Unit.INSTANCE;
                }
                UserRequest request = new UserRequest(out, null, null, 0L, null, 30, null);
                JarvisEvents.INSTANCE.publish(new AssistantEvent.RequestReceived(request.getText()));
                JarvisEvents.INSTANCE.publish(new AssistantEvent.StateChanged(AssistantState.THINKING));
                VoiceEngine.this.onRequest.invoke(request);
                return Unit.INSTANCE;
            } catch (Throwable th) {
                $this$withLock_u24default$iv.unlock(owner$iv);
                throw th;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invokeSuspend$lambda$6(VoiceEngine this$0, String it) {
            this$0.publishPartial(it);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invokeSuspend$lambda$7(VoiceEngine this$0, String t, AsrTelemetry te) {
            this$0.deliverHybridFinal(t, te, true);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deliverHybridFinal(String text, AsrTelemetry tel, boolean liveRefine) {
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AnonymousClass1(text, liveRefine, tel, null), 3, null);
    }

    public final void stop() {
        releaseHush();
        log("stopping");
        this.disposed = true;
        HybridAsrBridge hybridAsrBridge = this.hybridAsr;
        if (hybridAsrBridge != null) {
            hybridAsrBridge.cancelTurn();
        }
        this.hybridAsr = null;
        this.pauseCaptureForCloudAsr = false;
        this.suppressLocalWakeFinals = false;
        AudioCapture audioCapture = this.audioCapture;
        if (audioCapture != null) {
            audioCapture.stop();
        }
        this.audioCapture = null;
        AsrSession asrSession = this.asrSession;
        if (asrSession != null) {
            asrSession.release();
        }
        this.asrSession = null;
        OfflineMultilingualAsr offlineMultilingualAsr = this.offlineAsr;
        if (offlineMultilingualAsr != null) {
            offlineMultilingualAsr.close();
        }
        this.offlineAsr = null;
        BufferedEnAsr bufferedEnAsr = this.enAsr;
        if (bufferedEnAsr != null) {
            bufferedEnAsr.close();
        }
        this.enAsr = null;
        this.wakeWordEngine = null;
        OwwWakeSpotter owwWakeSpotter = this.kwsSpotter;
        if (owwWakeSpotter != null) {
            owwWakeSpotter.close();
        }
        this.kwsSpotter = null;
        SherpaKeywordSpotter sherpaKeywordSpotter = this.keywordSpotter;
        if (sherpaKeywordSpotter != null) {
            sherpaKeywordSpotter.close();
        }
        this.keywordSpotter = null;
        synchronized (this.livePcmLock) {
            this.livePcm.clear();
            Unit unit = Unit.INSTANCE;
        }
        this.pendingLivePrefix = "";
        JarvisSpeaker jarvisSpeaker = this.speaker;
        if (jarvisSpeaker != null) {
            jarvisSpeaker.release();
        }
        this.speaker = null;
        hideOverlay();
        CoroutineScopeKt.cancel$default(this.scope, null, 1, null);
        log("stopped");
        JarvisEvents.INSTANCE.publish(new AssistantEvent.StateChanged(AssistantState.OFFLINE));
    }

    public final void stopSpeaking() {
        JarvisSpeaker jarvisSpeaker = this.speaker;
        if (jarvisSpeaker != null) {
            jarvisSpeaker.stop();
        }
    }

    public final boolean isSpeaking() {
        JarvisSpeaker jarvisSpeaker = this.speaker;
        return jarvisSpeaker != null && jarvisSpeaker.isSpeaking();
    }

    public final int beginStreamSpeak() {
        JarvisSpeaker jarvisSpeaker = this.speaker;
        if (jarvisSpeaker != null) {
            return jarvisSpeaker.beginStreamSpeak();
        }
        return 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void enqueueStreamSpeak$default(VoiceEngine voiceEngine, String str, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            function1 = null;
        }
        voiceEngine.enqueueStreamSpeak(str, i, function1);
    }

    public final void enqueueStreamSpeak(String text, int streamGeneration, Function1<? super Long, Unit> onFirstAudio) {
        Intrinsics.checkNotNullParameter(text, "text");
        JarvisSpeaker jarvisSpeaker = this.speaker;
        if (jarvisSpeaker != null) {
            JarvisSpeaker.enqueueSpeak$default(jarvisSpeaker, text, streamGeneration, onFirstAudio, null, 8, null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void endStreamSpeak$default(VoiceEngine voiceEngine, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = new Function0() { // from class: com.jarvis.app.VoiceEngine$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            };
        }
        voiceEngine.endStreamSpeak(function0);
    }

    public final void endStreamSpeak(Function0<Unit> onComplete) {
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        JarvisSpeaker jarvisSpeaker = this.speaker;
        if (jarvisSpeaker == null) {
            onComplete.invoke();
        } else {
            jarvisSpeaker.endStreamSpeak(onComplete);
        }
    }

    /* JADX INFO: renamed from: com.jarvis.app.VoiceEngine$speak$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: VoiceEngine.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.VoiceEngine$speak$1", f = "VoiceEngine.kt", i = {0, 0}, l = {860}, m = "invokeSuspend", n = {"ready", "waited"}, s = {"L$0", "I$0"}, v = 1)
    static final class C00611 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0<Unit> $onComplete;
        final /* synthetic */ Function1<Long, Unit> $onFirstAudio;
        final /* synthetic */ String $text;
        int I$0;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C00611(Function1<? super Long, Unit> function1, Function0<Unit> function0, String str, Continuation<? super C00611> continuation) {
            super(2, continuation);
            this.$onFirstAudio = function1;
            this.$onComplete = function0;
            this.$text = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return VoiceEngine.this.new C00611(this.$onFirstAudio, this.$onComplete, this.$text, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00611) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:20:0x0063  */
        /* JADX WARN: Code duplicated, block: B:22:0x006e  */
        /* JADX WARN: Code duplicated, block: B:25:0x007b  */
        /* JADX WARN: Code duplicated, block: B:28:0x0081  */
        /* JADX WARN: Code duplicated, block: B:9:0x0030  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0052 -> B:17:0x0055). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            int waited;
            JarvisSpeaker ready;
            Object obj;
            C00611 c00611;
            JarvisSpeaker sp;
            Function1<Long, Unit> function1;
            Function0<Unit> function0;
            Object obj2;
            int waited2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    JarvisSpeaker ready2 = VoiceEngine.this.speaker;
                    waited = 0;
                    ready = ready2;
                    obj = coroutine_suspended;
                    c00611 = this;
                    if (ready != null && !VoiceEngine.this.disposed && waited < 40) {
                        c00611.L$0 = SpillingKt.nullOutSpilledVariable(ready);
                        c00611.I$0 = waited;
                        c00611.label = 1;
                        if (DelayKt.delay(100L, c00611) == obj) {
                            return obj;
                        }
                        int i = waited;
                        obj2 = obj;
                        waited2 = i;
                        ready = VoiceEngine.this.speaker;
                        Object obj3 = obj2;
                        waited = waited2 + 1;
                        obj = obj3;
                        if (ready != null) {
                        }
                        sp = ready;
                        if (sp == null) {
                            VoiceEngine.this.log("speak skipped: TTS not ready");
                            function1 = c00611.$onFirstAudio;
                            if (function1 != null) {
                                function1.invoke(Boxing.boxLong(0L));
                            }
                            function0 = c00611.$onComplete;
                            if (function0 != null) {
                                function0.invoke();
                            }
                            return Unit.INSTANCE;
                        }
                        String str = c00611.$text;
                        final Function0<Unit> function2 = c00611.$onComplete;
                        sp.speakResponse(str, new Function0() { // from class: com.jarvis.app.VoiceEngine$speak$1$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return VoiceEngine.C00611.invokeSuspend$lambda$0(function2);
                            }
                        }, c00611.$onFirstAudio);
                        return Unit.INSTANCE;
                    }
                    sp = ready;
                    if (sp == null) {
                        VoiceEngine.this.log("speak skipped: TTS not ready");
                        function1 = c00611.$onFirstAudio;
                        if (function1 != null) {
                            function1.invoke(Boxing.boxLong(0L));
                        }
                        function0 = c00611.$onComplete;
                        if (function0 != null) {
                            function0.invoke();
                        }
                        return Unit.INSTANCE;
                    }
                    String str2 = c00611.$text;
                    final Function0 function3 = c00611.$onComplete;
                    sp.speakResponse(str2, new Function0() { // from class: com.jarvis.app.VoiceEngine$speak$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return VoiceEngine.C00611.invokeSuspend$lambda$0(function3);
                        }
                    }, c00611.$onFirstAudio);
                    return Unit.INSTANCE;
                case 1:
                    waited2 = this.I$0;
                    ResultKt.throwOnFailure($result);
                    obj2 = coroutine_suspended;
                    c00611 = this;
                    ready = VoiceEngine.this.speaker;
                    Object obj4 = obj2;
                    waited = waited2 + 1;
                    obj = obj4;
                    if (ready != null) {
                        break;
                    }
                    sp = ready;
                    if (sp == null) {
                        VoiceEngine.this.log("speak skipped: TTS not ready");
                        function1 = c00611.$onFirstAudio;
                        if (function1 != null) {
                            function1.invoke(Boxing.boxLong(0L));
                        }
                        function0 = c00611.$onComplete;
                        if (function0 != null) {
                            function0.invoke();
                        }
                        return Unit.INSTANCE;
                    }
                    String str3 = c00611.$text;
                    final Function0 function4 = c00611.$onComplete;
                    sp.speakResponse(str3, new Function0() { // from class: com.jarvis.app.VoiceEngine$speak$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return VoiceEngine.C00611.invokeSuspend$lambda$0(function4);
                        }
                    }, c00611.$onFirstAudio);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invokeSuspend$lambda$0(Function0 $onComplete) {
            JarvisEvents.INSTANCE.publish(new AssistantEvent.StateChanged(AssistantState.READY));
            if ($onComplete != null) {
                $onComplete.invoke();
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void speak$default(VoiceEngine voiceEngine, String str, Function0 function0, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = null;
        }
        if ((i & 4) != 0) {
            function1 = null;
        }
        voiceEngine.speak(str, function0, function1);
    }

    public final void speak(String text, Function0<Unit> onComplete, Function1<? super Long, Unit> onFirstAudio) {
        Intrinsics.checkNotNullParameter(text, "text");
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C00611(onFirstAudio, onComplete, text, null), 3, null);
    }

    public final void listenForFollowUp(long timeoutMs) {
        WakeWordEngine wakeWordEngine = this.wakeWordEngine;
        if (wakeWordEngine != null) {
            wakeWordEngine.listenForFollowUp(timeoutMs);
        }
    }

    public final void showOverlay() {
        OverlayOrbService.INSTANCE.show(this.app);
    }

    public final void hideOverlay() {
        Object objM392constructorimpl;
        CoroutineScope coroutineScope = this.overlayScope;
        if (coroutineScope != null) {
            CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
        }
        this.overlayScope = null;
        View it = this.overlayView;
        if (it != null) {
            this.overlayView = null;
            try {
                Result.Companion companion = Result.INSTANCE;
                VoiceEngine $this$hideOverlay_u24lambda_u240_u240 = this;
                ((WindowManager) $this$hideOverlay_u24lambda_u240_u240.app.getSystemService(WindowManager.class)).removeView(it);
                objM392constructorimpl = Result.m392constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
            }
            Result.m391boximpl(objM392constructorimpl);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void publishPartial(String partial) {
        if (StringsKt.isBlank(partial)) {
            return;
        }
        log("partial \"" + partial + "\"");
        JarvisEvents.INSTANCE.publish(new AssistantEvent.TranscriptUpdated(partial));
        this.onPartial.invoke(partial);
    }

    static /* synthetic */ CommandRefinePasses refineCommandPcm$default(VoiceEngine voiceEngine, float[] fArr, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "";
        }
        return voiceEngine.refineCommandPcm(fArr, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CommandRefinePasses refineCommandPcm(float[] pcm, String streamingRu) {
        String str;
        String str2;
        String enHint;
        String enHint2;
        OfflineMultilingualAsr off;
        Object objM392constructorimpl;
        Object objM392constructorimpl2;
        if (pcm.length == 0) {
            return null;
        }
        long t0 = SystemClock.elapsedRealtime();
        if (!CommandPcmRefiner.INSTANCE.needsLatinRefine(streamingRu)) {
            log("refine skip " + (SystemClock.elapsedRealtime() - t0) + "ms (no EN needed)");
            return null;
        }
        OfflineMultilingualAsr off2 = this.offlineAsr;
        if (off2 != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                VoiceEngine voiceEngine = this;
                objM392constructorimpl2 = Result.m392constructorimpl(off2.decode(pcm));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM392constructorimpl2 = Result.m392constructorimpl(ResultKt.createFailure(th));
            }
            Throwable it = Result.m395exceptionOrNullimpl(objM392constructorimpl2);
            if (it != null) {
                log("multi decode failed: " + it.getMessage());
                objM392constructorimpl2 = null;
            }
            str = (String) objM392constructorimpl2;
        } else {
            str = null;
        }
        String multiFull = str;
        String multiTail = null;
        String enTail = null;
        if (CommandPcmRefiner.INSTANCE.hasUsefulLatin(multiFull)) {
            enHint = multiFull == null ? "" : multiFull;
            enHint2 = "multi-latin";
        } else {
            final float[] tail = CommandPcmRefiner.tailOf$default(CommandPcmRefiner.INSTANCE, pcm, 0, 2, null);
            final BufferedEnAsr eng = this.enAsr;
            CompletableFuture enJob = eng != null ? CompletableFuture.supplyAsync(new Supplier() { // from class: com.jarvis.app.VoiceEngine$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return VoiceEngine.refineCommandPcm$lambda$1$0(this.f$0, eng, tail);
                }
            }) : null;
            if (tail.length >= pcm.length || (off = this.offlineAsr) == null) {
                str2 = null;
            } else {
                try {
                    Result.Companion companion3 = Result.INSTANCE;
                    VoiceEngine voiceEngine2 = this;
                    objM392constructorimpl = Result.m392constructorimpl(off.decode(tail));
                } catch (Throwable th2) {
                    Result.Companion companion4 = Result.INSTANCE;
                    objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th2));
                }
                if (Result.m398isFailureimpl(objM392constructorimpl)) {
                    objM392constructorimpl = null;
                }
                str2 = (String) objM392constructorimpl;
            }
            multiTail = str2;
            enTail = enJob != null ? (String) enJob.get() : null;
            enHint = CommandPcmRefiner.INSTANCE.bestLatinHint(enTail, multiTail);
            enHint2 = "multi+tail";
        }
        long ms = SystemClock.elapsedRealtime() - t0;
        log("refine " + ms + "ms path=" + enHint2 + " pcm=" + pcm.length + " multi=\"" + (multiFull != null ? StringsKt.take(multiFull, 60) : null) + "\" multiTail=\"" + (multiTail != null ? StringsKt.take(multiTail, 30) : null) + "\" enTail=\"" + (enTail != null ? StringsKt.take(enTail, 30) : null) + "\" hint=\"" + StringsKt.take(enHint, 40) + "\"");
        String str3 = multiFull;
        if ((str3 == null || StringsKt.isBlank(str3)) && StringsKt.isBlank(enHint)) {
            return null;
        }
        String str4 = null;
        String str5 = enHint;
        if (!StringsKt.isBlank(str5)) {
            str4 = str5;
        }
        return new CommandRefinePasses(multiFull, str4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String refineCommandPcm$lambda$1$0(VoiceEngine this$0, BufferedEnAsr $eng, float[] $tail) {
        Object objM392constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl($eng.decode($tail));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = null;
        }
        return (String) objM392constructorimpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to apply debug info
    jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 4781. Try increasing type updates limit count.
    	at jadx.core.dex.visitors.typeinference.TypeUpdateInfo.requestUpdate(TypeUpdateInfo.java:61)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:298)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyDebugInfo(TypeUpdate.java:77)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:137)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.searchDebugInfoByOffset(DebugInfoApplyVisitor.java:107)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.searchAndApplyVarDebugInfo(DebugInfoApplyVisitor.java:83)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.lambda$applyDebugInfo$0(DebugInfoApplyVisitor.java:68)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:68)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.visit(DebugInfoApplyVisitor.java:55)
     */
    public final boolean ensureModelFiles(File modelDir, AsrModelSpec spec) {
        boolean z;
        Object objM392constructorimpl;
        String assetPath;
        String[] assets;
        String[] strArr;
        int i;
        boolean z2;
        Throwable th;
        Throwable th2;
        Iterable iterableRequiredFiles = spec.requiredFiles();
        Iterable $this$all$iv = iterableRequiredFiles;
        boolean z3 = false;
        if (!($this$all$iv instanceof Collection) || !((Collection) $this$all$iv).isEmpty()) {
            Iterator it = $this$all$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    String it2 = (String) element$iv;
                    if (!new File(modelDir, it2).exists()) {
                        z = false;
                        break;
                    }
                } else {
                    z = true;
                    break;
                }
            }
        } else {
            z = true;
        }
        if (z) {
            return true;
        }
        String assetPath2 = "models/" + spec.getDirName();
        try {
            Result.Companion companion = Result.INSTANCE;
            String[] list = this.app.getAssets().list(assetPath2);
            if (list == null) {
                list = new String[0];
            }
            objM392constructorimpl = Result.m392constructorimpl(list);
        } catch (Throwable th3) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th3));
        }
        String[] strArr2 = new String[0];
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = strArr2;
        }
        String[] assets2 = (String[]) objM392constructorimpl;
        if (!(assets2.length == 0)) {
            String[] strArr3 = assets2;
            int $i$f$forEach = 0;
            int length = strArr3.length;
            int i2 = 0;
            while (i2 < length) {
                String str = strArr3[i2];
                try {
                    Result.Companion companion3 = Result.INSTANCE;
                    try {
                        InputStream inputStreamOpen = this.app.getAssets().open(assetPath2 + "/" + str);
                        try {
                            InputStream inputStream = inputStreamOpen;
                            assetPath = assetPath2;
                            try {
                                FileOutputStream fileOutputStream = new FileOutputStream(new File(modelDir, str));
                                try {
                                    FileOutputStream fileOutputStream2 = fileOutputStream;
                                    Intrinsics.checkNotNull(inputStream);
                                    assets = assets2;
                                    try {
                                        strArr = strArr3;
                                        i = $i$f$forEach;
                                        z2 = false;
                                        try {
                                            long jCopyTo$default = ByteStreamsKt.copyTo$default(inputStream, fileOutputStream2, 0, 2, null);
                                            try {
                                                CloseableKt.closeFinally(fileOutputStream, null);
                                                try {
                                                    CloseableKt.closeFinally(inputStreamOpen, null);
                                                    Result.m392constructorimpl(Long.valueOf(jCopyTo$default));
                                                } catch (Throwable th4) {
                                                    th = th4;
                                                    Result.Companion companion4 = Result.INSTANCE;
                                                    Result.m392constructorimpl(ResultKt.createFailure(th));
                                                }
                                                i2++;
                                                z3 = z2;
                                                assetPath2 = assetPath;
                                                assets2 = assets;
                                                strArr3 = strArr;
                                                $i$f$forEach = i;
                                            } catch (Throwable th5) {
                                                th = th5;
                                                try {
                                                    throw th;
                                                } catch (Throwable th6) {
                                                    CloseableKt.closeFinally(inputStreamOpen, th);
                                                    throw th6;
                                                }
                                            }
                                        } catch (Throwable th7) {
                                            th2 = th7;
                                            try {
                                                throw th2;
                                            } catch (Throwable th8) {
                                                CloseableKt.closeFinally(fileOutputStream, th2);
                                                throw th8;
                                            }
                                        }
                                    } catch (Throwable th9) {
                                        strArr = strArr3;
                                        i = $i$f$forEach;
                                        z2 = false;
                                        th2 = th9;
                                    }
                                } catch (Throwable th10) {
                                    assets = assets2;
                                    strArr = strArr3;
                                    i = $i$f$forEach;
                                    z2 = false;
                                    th2 = th10;
                                }
                            } catch (Throwable th11) {
                                assets = assets2;
                                strArr = strArr3;
                                i = $i$f$forEach;
                                z2 = false;
                                th = th11;
                            }
                        } catch (Throwable th12) {
                            assetPath = assetPath2;
                            assets = assets2;
                            strArr = strArr3;
                            i = $i$f$forEach;
                            z2 = false;
                            th = th12;
                        }
                    } catch (Throwable th13) {
                        th = th13;
                        assetPath = assetPath2;
                        assets = assets2;
                        strArr = strArr3;
                        i = $i$f$forEach;
                        z2 = false;
                    }
                } catch (Throwable th14) {
                    th = th14;
                    assetPath = assetPath2;
                    assets = assets2;
                    strArr = strArr3;
                    i = $i$f$forEach;
                    z2 = z3;
                }
            }
            boolean z4 = z3;
            Iterable $this$all$iv2 = iterableRequiredFiles;
            if (($this$all$iv2 instanceof Collection) && ((Collection) $this$all$iv2).isEmpty()) {
                return true;
            }
            for (Object element$iv2 : $this$all$iv2) {
                String it3 = (String) element$iv2;
                if (!new File(modelDir, it3).exists()) {
                    return z4;
                }
            }
            return true;
        }
        Iterable iterable = iterableRequiredFiles;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return true;
        }
        Iterator<T> it4 = iterable.iterator();
        while (it4.hasNext()) {
            if (!new File(modelDir, (String) it4.next()).exists()) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int log(String msg) {
        return Log.d(TAG, "voice[" + this.sessionId + "]: " + msg);
    }

    /* JADX INFO: compiled from: VoiceEngine.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Lcom/jarvis/app/VoiceEngine$Companion;", "", "<init>", "()V", "TAG", "", "BARGE_WINDOW_MS", "", "ECHO_HOLD_MS", "HUSH_MIN_MS", "HUSH_AFTER_SPEECH_MS", "HUSH_DIP_DELAY_MS", "HUSH_VOLUME_SHARE", "", "HUSH_VOLUME_KEY", "WAKE_HOLDOFF_AFTER_SPEECH_MS", "VOICE_CHECK_SAMPLES", "", "MIC_WATCH_PERIOD_MS", "MIC_HEARTBEAT_MS", "MIC_DEAF_MS", "MIC_RESTART_GAP_MS", "sessionCounter", "SELF_ECHO", "", "getSELF_ECHO", "()Ljava/util/Set;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Set<String> getSELF_ECHO() {
            return VoiceEngine.SELF_ECHO;
        }
    }
}
