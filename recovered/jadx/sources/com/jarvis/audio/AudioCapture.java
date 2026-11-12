package com.jarvis.audio;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioRecordingConfiguration;
import android.media.audiofx.AcousticEchoCanceler;
import android.os.SystemClock;
import androidx.core.content.ContextCompat;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;

/* JADX INFO: compiled from: AudioCapture.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 42\u00020\u0001:\u00014B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010*\u001a\u00020\u0012J\u000e\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.J\u0006\u0010/\u001a\u00020,J\u0010\u00100\u001a\u00020,2\u0006\u00101\u001a\u00020\u0012H\u0002J\b\u00102\u001a\u00020,H\u0002J\b\u00103\u001a\u00020,H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0013R\u001e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0015@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0015@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u001e\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0014\u001a\u00020\u001b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001e\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0012@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0013R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010&\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0013\"\u0004\b(\u0010)¨\u00065"}, d2 = {"Lcom/jarvis/audio/AudioCapture;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "audioRecord", "Landroid/media/AudioRecord;", "job", "Lkotlinx/coroutines/Job;", "_chunks", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/jarvis/audio/AudioChunk;", "chunks", "Lkotlinx/coroutines/flow/SharedFlow;", "getChunks", "()Lkotlinx/coroutines/flow/SharedFlow;", "isRecording", "", "()Z", "value", "", "lastSoundAtMs", "getLastSoundAtMs", "()J", "lastChunkAtMs", "getLastChunkAtMs", "", "readErrors", "getReadErrors", "()I", "clientSilenced", "getClientSilenced", "silenceCallback", "Landroid/media/AudioManager$AudioRecordingCallback;", "echoCanceler", "Landroid/media/audiofx/AcousticEchoCanceler;", "sourceHasEchoCancel", "echoCancel", "getEchoCancel", "setEchoCancel", "(Z)V", "hasPermission", "start", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "stop", "openRecord", "cancelEcho", "closeRecord", "registerSilenceCallback", "Companion", "audio-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AudioCapture {
    private static final int AUDIO_FORMAT = 2;
    private static final int CHANNEL_CONFIG = 16;
    public static final int CHUNK_SAMPLES = 1600;
    private static final long READ_ERROR_DELAY_MS = 200;
    public static final int SAMPLE_RATE = 16000;
    private final MutableSharedFlow<AudioChunk> _chunks;
    private AudioRecord audioRecord;
    private final SharedFlow<AudioChunk> chunks;
    private volatile boolean clientSilenced;
    private final Context context;
    private volatile boolean echoCancel;
    private AcousticEchoCanceler echoCanceler;
    private Job job;
    private volatile long lastChunkAtMs;
    private volatile long lastSoundAtMs;
    private volatile int readErrors;
    private AudioManager.AudioRecordingCallback silenceCallback;
    private boolean sourceHasEchoCancel;

    public AudioCapture(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this._chunks = SharedFlowKt.MutableSharedFlow$default(0, 64, null, 5, null);
        this.chunks = this._chunks;
    }

    public final SharedFlow<AudioChunk> getChunks() {
        return this.chunks;
    }

    public final boolean isRecording() {
        Job job = this.job;
        return job != null && job.isActive();
    }

    public final long getLastSoundAtMs() {
        return this.lastSoundAtMs;
    }

    public final long getLastChunkAtMs() {
        return this.lastChunkAtMs;
    }

    public final int getReadErrors() {
        return this.readErrors;
    }

    public final boolean getClientSilenced() {
        return this.clientSilenced;
    }

    public final boolean getEchoCancel() {
        return this.echoCancel;
    }

    public final void setEchoCancel(boolean z) {
        this.echoCancel = z;
    }

    public final boolean hasPermission() {
        return ContextCompat.checkSelfPermission(this.context, "android.permission.RECORD_AUDIO") == 0;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0012  */
    public final void start(CoroutineScope scope) {
        boolean z;
        Intrinsics.checkNotNullParameter(scope, "scope");
        Job job = this.job;
        if (job != null) {
            z = job.isActive();
        }
        if (z) {
            return;
        }
        openRecord(this.echoCancel);
        long now = SystemClock.elapsedRealtime();
        this.lastSoundAtMs = now;
        this.lastChunkAtMs = now;
        this.readErrors = 0;
        this.job = BuildersKt__Builders_commonKt.launch$default(scope, Dispatchers.getIO(), null, new AnonymousClass1(null), 2, null);
    }

    /* JADX INFO: renamed from: com.jarvis.audio.AudioCapture$start$1, reason: invalid class name */
    /* JADX INFO: compiled from: AudioCapture.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.audio.AudioCapture$start$1", f = "AudioCapture.kt", i = {0, 0, 0, 1, 1, 1, 1, 1}, l = {80, 91}, m = "invokeSuspend", n = {"$this$launch", "buf", "read", "$this$launch", "buf", "floatBuf", "read", "at"}, s = {"L$0", "L$1", "I$0", "L$0", "L$1", "L$2", "I$0", "J$0"}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int I$0;
        long J$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = AudioCapture.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:11:0x0054  */
        /* JADX WARN: Code duplicated, block: B:13:0x0062  */
        /* JADX WARN: Code duplicated, block: B:16:0x007b  */
        /* JADX WARN: Code duplicated, block: B:17:0x0081  */
        /* JADX WARN: Code duplicated, block: B:22:0x00a8  */
        /* JADX WARN: Code duplicated, block: B:27:0x00bb  */
        /* JADX WARN: Code duplicated, block: B:30:0x00c5 A[LOOP:1: B:26:0x00b9->B:30:0x00c5, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:33:0x00cc A[LOOP:2: B:32:0x00ca->B:33:0x00cc, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:36:0x00fc A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:37:0x00fd  */
        /* JADX WARN: Code duplicated, block: B:43:0x00ad A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:44:0x00a7 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:45:0x0084 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:46:0x00af A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:48:0x004e A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:52:0x00bf A[SYNTHETIC] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x00fd -> B:38:0x0103). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:11:0x0054
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r14) {
            /*
                Method dump skipped, instruction units count: 276
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jarvis.audio.AudioCapture.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final void stop() {
        Job job = this.job;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.job = null;
        closeRecord();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openRecord(boolean cancelEcho) {
        int bufSize = AudioRecord.getMinBufferSize(16000, 16, 2);
        int source = cancelEcho ? 7 : 1;
        AudioRecord record = new AudioRecord(source, 16000, 16, 2, bufSize * 2);
        this.audioRecord = record;
        this.sourceHasEchoCancel = cancelEcho;
        if (cancelEcho) {
            this.echoCanceler = AcousticEchoCanceler.create(record.getAudioSessionId());
            AcousticEchoCanceler acousticEchoCanceler = this.echoCanceler;
            if (acousticEchoCanceler != null) {
                acousticEchoCanceler.setEnabled(true);
            }
        }
        registerSilenceCallback();
        record.startRecording();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void closeRecord() {
        AudioRecord audioRecord;
        AudioManager.AudioRecordingCallback it = this.silenceCallback;
        if (it != null && (audioRecord = this.audioRecord) != null) {
            audioRecord.unregisterAudioRecordingCallback(it);
        }
        this.silenceCallback = null;
        this.clientSilenced = false;
        AcousticEchoCanceler acousticEchoCanceler = this.echoCanceler;
        if (acousticEchoCanceler != null) {
            acousticEchoCanceler.release();
        }
        this.echoCanceler = null;
        AudioRecord audioRecord2 = this.audioRecord;
        if (audioRecord2 != null) {
            audioRecord2.stop();
        }
        AudioRecord audioRecord3 = this.audioRecord;
        if (audioRecord3 != null) {
            audioRecord3.release();
        }
        this.audioRecord = null;
    }

    private final void registerSilenceCallback() {
        final AudioRecord record = this.audioRecord;
        if (record == null) {
            return;
        }
        AudioManager.AudioRecordingCallback audioRecordingCallback = new AudioManager.AudioRecordingCallback() { // from class: com.jarvis.audio.AudioCapture$registerSilenceCallback$callback$1
            @Override // android.media.AudioManager.AudioRecordingCallback
            public void onRecordingConfigChanged(List<AudioRecordingConfiguration> configs) {
                Object element$iv;
                AudioRecordingConfiguration it;
                Intrinsics.checkNotNullParameter(configs, "configs");
                AudioCapture audioCapture = this.this$0;
                List<AudioRecordingConfiguration> $this$firstOrNull$iv = configs;
                AudioRecord audioRecord = record;
                Iterator it2 = $this$firstOrNull$iv.iterator();
                do {
                    if (!it2.hasNext()) {
                        element$iv = null;
                        break;
                    } else {
                        element$iv = it2.next();
                        it = (AudioRecordingConfiguration) element$iv;
                    }
                } while (!(it.getClientAudioSessionId() == audioRecord.getAudioSessionId()));
                AudioRecordingConfiguration audioRecordingConfiguration = (AudioRecordingConfiguration) element$iv;
                audioCapture.clientSilenced = audioRecordingConfiguration != null ? audioRecordingConfiguration.isClientSilenced() : false;
            }
        };
        record.registerAudioRecordingCallback(this.context.getMainExecutor(), audioRecordingCallback);
        this.silenceCallback = audioRecordingCallback;
    }
}
