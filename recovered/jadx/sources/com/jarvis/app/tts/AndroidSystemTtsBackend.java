package com.jarvis.app.tts;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.jarvis.app.VoiceService;
import com.jarvis.core.voice.StreamingTtsBackend;
import com.jarvis.core.voice.TtsAudioResult;
import com.jarvis.core.voice.TtsErrorKind;
import com.jarvis.core.voice.TtsVoiceConfig;
import com.jarvis.core.voice.VoiceProviderAvailability;
import com.jarvis.core.voice.VoiceProviderKind;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.apache.commons.codec.language.Soundex;

/* JADX INFO: compiled from: AndroidSystemTtsBackend.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 .2\u00020\u0001:\u0002-.B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020 H\u0002J&\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\n2\u0006\u0010$\u001a\u00020%2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0016J\u0018\u0010'\u001a\u00020\"2\u0006\u0010\r\u001a\u00020(2\u0006\u0010$\u001a\u00020%H\u0002J\u0010\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\nX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\nX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\fR\u0014\u0010\u0013\u001a\u00020\nX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\fR\u0014\u0010\u0015\u001a\u00020\u0006X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/jarvis/app/tts/AndroidSystemTtsBackend;", "Lcom/jarvis/core/voice/StreamingTtsBackend;", "appContext", "Landroid/content/Context;", "online", "Lkotlin/Function0;", "", "<init>", "(Landroid/content/Context;Lkotlin/jvm/functions/Function0;)V", "id", "", "getId", "()Ljava/lang/String;", "kind", "Lcom/jarvis/core/voice/VoiceProviderKind;", "getKind", "()Lcom/jarvis/core/voice/VoiceProviderKind;", "model", "getModel", "defaultVoice", "getDefaultVoice", "supportsStreamingAudio", "getSupportsStreamingAudio", "()Z", "engine", "Landroid/speech/tts/TextToSpeech;", "ready", "initLock", "", "availability", "Lcom/jarvis/core/voice/VoiceProviderAvailability;", "ensureInit", "", "synthesize", "Lcom/jarvis/core/voice/TtsAudioResult;", VoiceService.EXTRA_TEXT, "voice", "Lcom/jarvis/core/voice/TtsVoiceConfig;", "cancelFlag", NotificationCompat.CATEGORY_ERROR, "Lcom/jarvis/core/voice/TtsErrorKind;", "readWavPcmFloat", "Lcom/jarvis/app/tts/AndroidSystemTtsBackend$Decoded;", "file", "Ljava/io/File;", "Decoded", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AndroidSystemTtsBackend implements StreamingTtsBackend {
    private static final String TAG = "Jarvis.Tts";
    private final Context appContext;
    private final String defaultVoice;
    private volatile TextToSpeech engine;
    private final String id;
    private final Object initLock;
    private final VoiceProviderKind kind;
    private final String model;
    private final Function0<Boolean> online;
    private volatile boolean ready;
    private final boolean supportsStreamingAudio;

    public AndroidSystemTtsBackend(Context appContext, Function0<Boolean> online) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(online, "online");
        this.appContext = appContext;
        this.online = online;
        this.id = "tts.cloud.android_tts";
        this.kind = VoiceProviderKind.CLOUD;
        this.model = "android.speech.tts.TextToSpeech";
        this.defaultVoice = "ru-RU";
        this.initLock = new Object();
    }

    public /* synthetic */ AndroidSystemTtsBackend(Context context, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? new Function0() { // from class: com.jarvis.app.tts.AndroidSystemTtsBackend$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(AndroidSystemTtsBackend._init_$lambda$0());
            }
        } : function0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean _init_$lambda$0() {
        return true;
    }

    @Override // com.jarvis.core.voice.StreamingTtsBackend
    public String getId() {
        return this.id;
    }

    @Override // com.jarvis.core.voice.StreamingTtsBackend
    public VoiceProviderKind getKind() {
        return this.kind;
    }

    @Override // com.jarvis.core.voice.StreamingTtsBackend
    public String getModel() {
        return this.model;
    }

    @Override // com.jarvis.core.voice.StreamingTtsBackend
    public String getDefaultVoice() {
        return this.defaultVoice;
    }

    @Override // com.jarvis.core.voice.StreamingTtsBackend
    public boolean getSupportsStreamingAudio() {
        return this.supportsStreamingAudio;
    }

    @Override // com.jarvis.core.voice.StreamingTtsBackend
    public VoiceProviderAvailability availability() {
        if (!this.online.invoke().booleanValue()) {
            return VoiceProviderAvailability.UNAVAILABLE;
        }
        ensureInit();
        return this.ready ? VoiceProviderAvailability.AVAILABLE : VoiceProviderAvailability.UNAVAILABLE;
    }

    private final void ensureInit() {
        if (!this.ready || this.engine == null) {
            synchronized (this.initLock) {
                if (!this.ready || this.engine == null) {
                    final CountDownLatch latch = new CountDownLatch(1);
                    TextToSpeech tts = new TextToSpeech(this.appContext, new TextToSpeech.OnInitListener() { // from class: com.jarvis.app.tts.AndroidSystemTtsBackend$$ExternalSyntheticLambda1
                        @Override // android.speech.tts.TextToSpeech.OnInitListener
                        public final void onInit(int i) {
                            AndroidSystemTtsBackend.ensureInit$lambda$0$0(this.f$0, latch, i);
                        }
                    });
                    this.engine = tts;
                    latch.await(3L, TimeUnit.SECONDS);
                    if (this.ready) {
                        tts.setLanguage(new Locale("ru", "RU"));
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ensureInit$lambda$0$0(AndroidSystemTtsBackend this$0, CountDownLatch $latch, int status) {
        this$0.ready = status == 0;
        $latch.countDown();
    }

    @Override // com.jarvis.core.voice.StreamingTtsBackend
    public TtsAudioResult synthesize(String text, TtsVoiceConfig voice, Function0<Boolean> cancelFlag) {
        Object objM392constructorimpl;
        TtsAudioResult ttsAudioResultErr;
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(voice, "voice");
        Intrinsics.checkNotNullParameter(cancelFlag, "cancelFlag");
        if (cancelFlag.invoke().booleanValue()) {
            return err(TtsErrorKind.CANCELLED, voice);
        }
        ensureInit();
        TextToSpeech tts = this.engine;
        if (!this.ready || tts == null) {
            return err(TtsErrorKind.PROVIDER_UNAVAILABLE, voice);
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            AndroidSystemTtsBackend androidSystemTtsBackend = this;
            List parts = StringsKt.split$default((CharSequence) StringsKt.replace$default(voice.getLanguage(), '_', Soundex.SILENT_MARKER, false, 4, (Object) null), new char[]{Soundex.SILENT_MARKER}, false, 0, 6, (Object) null);
            objM392constructorimpl = Result.m392constructorimpl(parts.size() >= 2 ? new Locale((String) parts.get(0), (String) parts.get(1)) : new Locale("ru", "RU"));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        Locale locale = new Locale("ru", "RU");
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = locale;
        }
        Locale lang = (Locale) objM392constructorimpl;
        int avail = tts.isLanguageAvailable(lang);
        if (avail < 0) {
            return err(TtsErrorKind.UNSUPPORTED_VOICE, voice);
        }
        tts.setLanguage(lang);
        tts.setSpeechRate(RangesKt.coerceIn(voice.getSpeakingRate(), 0.5f, 2.0f));
        tts.setPitch(RangesKt.coerceIn(voice.getPitch(), 0.5f, 2.0f));
        File outFile = new File(this.appContext.getCacheDir(), "jarvis_tts_" + UUID.randomUUID() + ".wav");
        final CountDownLatch done = new CountDownLatch(1);
        final AtomicBoolean failed = new AtomicBoolean(false);
        long t0 = System.currentTimeMillis();
        String utteranceId = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(utteranceId, "toString(...)");
        tts.setOnUtteranceProgressListener(new UtteranceProgressListener() { // from class: com.jarvis.app.tts.AndroidSystemTtsBackend.synthesize.1
            @Override // android.speech.tts.UtteranceProgressListener
            public void onStart(String utteranceId2) {
            }

            @Override // android.speech.tts.UtteranceProgressListener
            public void onDone(String utteranceId2) {
                done.countDown();
            }

            @Override // android.speech.tts.UtteranceProgressListener
            @Deprecated(message = "Deprecated in Java")
            public void onError(String utteranceId2) {
                failed.set(true);
                done.countDown();
            }

            @Override // android.speech.tts.UtteranceProgressListener
            public void onError(String utteranceId2, int errorCode) {
                failed.set(true);
                done.countDown();
            }
        });
        Bundle params = new Bundle();
        int result = tts.synthesizeToFile(text, params, outFile, utteranceId);
        if (result != 0) {
            outFile.delete();
            return err(TtsErrorKind.PROVIDER_UNAVAILABLE, voice);
        }
        boolean ok = done.await(25L, TimeUnit.SECONDS);
        if (cancelFlag.invoke().booleanValue()) {
            try {
                Result.Companion companion3 = Result.INSTANCE;
                AndroidSystemTtsBackend androidSystemTtsBackend2 = this;
                Result.m392constructorimpl(Integer.valueOf(tts.stop()));
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.INSTANCE;
                Result.m392constructorimpl(ResultKt.createFailure(th2));
            }
            outFile.delete();
            return err(TtsErrorKind.CANCELLED, voice);
        }
        if (!ok || failed.get() || !outFile.isFile()) {
            outFile.delete();
            return err(TtsErrorKind.TIMEOUT, voice);
        }
        try {
            Decoded decoded = readWavPcmFloat(outFile);
            try {
                long ms = RangesKt.coerceAtLeast(System.currentTimeMillis() - t0, 0L);
                float[] samples = decoded.getSamples();
                int sampleRateHz = decoded.getSampleRateHz();
                String id = getId();
                String model = getModel();
                String languageTag = lang.toLanguageTag();
                Intrinsics.checkNotNullExpressionValue(languageTag, "toLanguageTag(...)");
                ttsAudioResultErr = new TtsAudioResult(samples, sampleRateHz, id, model, languageTag, 0L, ms, false, false, null, null, 1792, null);
            } catch (Throwable th3) {
                t = th3;
                try {
                    Log.w(TAG, "wav decode: " + t.getClass().getSimpleName());
                    ttsAudioResultErr = err(TtsErrorKind.DECODING, voice);
                } finally {
                    outFile.delete();
                }
            }
        } catch (Throwable th4) {
            t = th4;
        }
        return ttsAudioResultErr;
    }

    private final TtsAudioResult err(TtsErrorKind kind, TtsVoiceConfig voice) {
        return new TtsAudioResult(new float[0], 22050, getId(), getModel(), voice.getVoice(), 0L, 0L, false, false, null, kind, 992, null);
    }

    /* JADX INFO: compiled from: AndroidSystemTtsBackend.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/jarvis/app/tts/AndroidSystemTtsBackend$Decoded;", "", "samples", "", "sampleRateHz", "", "<init>", "([FI)V", "getSamples", "()[F", "getSampleRateHz", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final /* data */ class Decoded {
        private final int sampleRateHz;
        private final float[] samples;

        public static /* synthetic */ Decoded copy$default(Decoded decoded, float[] fArr, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                fArr = decoded.samples;
            }
            if ((i2 & 2) != 0) {
                i = decoded.sampleRateHz;
            }
            return decoded.copy(fArr, i);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final float[] getSamples() {
            return this.samples;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getSampleRateHz() {
            return this.sampleRateHz;
        }

        public final Decoded copy(float[] samples, int sampleRateHz) {
            Intrinsics.checkNotNullParameter(samples, "samples");
            return new Decoded(samples, sampleRateHz);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Decoded)) {
                return false;
            }
            Decoded decoded = (Decoded) other;
            return Intrinsics.areEqual(this.samples, decoded.samples) && this.sampleRateHz == decoded.sampleRateHz;
        }

        public int hashCode() {
            return (Arrays.hashCode(this.samples) * 31) + Integer.hashCode(this.sampleRateHz);
        }

        public String toString() {
            return "Decoded(samples=" + Arrays.toString(this.samples) + ", sampleRateHz=" + this.sampleRateHz + ")";
        }

        public Decoded(float[] samples, int sampleRateHz) {
            Intrinsics.checkNotNullParameter(samples, "samples");
            this.samples = samples;
            this.sampleRateHz = sampleRateHz;
        }

        public final int getSampleRateHz() {
            return this.sampleRateHz;
        }

        public final float[] getSamples() {
            return this.samples;
        }
    }

    private final Decoded readWavPcmFloat(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        try {
            RandomAccessFile raf = randomAccessFile;
            int i = 0;
            int i2 = 4;
            byte[] it = new byte[4];
            raf.readFully(it);
            if (!Intrinsics.areEqual(new String(it, Charsets.UTF_8), "RIFF")) {
                throw new IllegalArgumentException("not riff".toString());
            }
            raf.skipBytes(4);
            byte[] it2 = new byte[4];
            raf.readFully(it2);
            if (!Intrinsics.areEqual(new String(it2, Charsets.UTF_8), "WAVE")) {
                throw new IllegalArgumentException("not wave".toString());
            }
            int sampleRate = 22050;
            int bits = 16;
            int channels = 1;
            int dataSize = 0;
            long dataOffset = 0;
            while (raf.getFilePointer() < raf.length()) {
                byte[] it3 = new byte[i2];
                raf.readFully(it3);
                int size = Integer.reverseBytes(raf.readInt());
                String name = new String(it3, Charsets.UTF_8);
                if (Intrinsics.areEqual(name, "fmt ")) {
                    int audioFormat = Short.reverseBytes(raf.readShort());
                    channels = Short.reverseBytes(raf.readShort());
                    sampleRate = Integer.reverseBytes(raf.readInt());
                    raf.skipBytes(6);
                    bits = Short.reverseBytes(raf.readShort());
                    if (size > 16) {
                        raf.skipBytes(size - 16);
                    }
                    if (!(audioFormat == 1)) {
                        throw new IllegalArgumentException("not pcm".toString());
                    }
                    i2 = 4;
                } else {
                    int i3 = i;
                    if (Intrinsics.areEqual(name, "data")) {
                        dataOffset = raf.getFilePointer();
                        dataSize = size;
                        raf.skipBytes(size);
                        i = i3;
                        i2 = 4;
                    } else {
                        raf.skipBytes(size);
                        i = i3;
                        i2 = 4;
                    }
                }
            }
            if (!(dataSize > 0)) {
                throw new IllegalArgumentException("no data".toString());
            }
            raf.seek(dataOffset);
            byte[] pcm = new byte[dataSize];
            raf.readFully(pcm);
            if (bits != 16) {
                throw new IllegalArgumentException("bits=" + bits);
            }
            int n = (pcm.length / 2) / channels;
            float[] out = new float[n];
            int i4 = 0;
            int o = 0;
            while (o < n) {
                int lo = pcm[i4] & UByte.MAX_VALUE;
                int hi = pcm[i4 + 1];
                RandomAccessFile raf2 = raf;
                short v = (short) ((hi << 8) | lo);
                out[o] = v / 32768.0f;
                i4 += channels * 2;
                o++;
                raf = raf2;
                pcm = pcm;
            }
            Decoded decoded = new Decoded(out, sampleRate);
            CloseableKt.closeFinally(randomAccessFile, null);
            return decoded;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(randomAccessFile, th);
                throw th2;
            }
        }
    }
}
