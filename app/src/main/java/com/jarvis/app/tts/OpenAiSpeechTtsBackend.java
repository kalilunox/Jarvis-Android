package com.jarvis.app.tts;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.jarvis.app.VoiceService;
import com.jarvis.core.voice.StreamingTtsBackend;
import com.jarvis.core.voice.TtsAudioResult;
import com.jarvis.core.voice.TtsErrorKind;
import com.jarvis.core.voice.TtsVoiceConfig;
import com.jarvis.core.voice.VoiceProviderAvailability;
import com.jarvis.core.voice.VoiceProviderKind;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLConnection;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

/* JADX INFO: compiled from: OpenAiSpeechTtsBackend.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0003\u0018\u0000 22\u00020\u0001:\u00012Bg\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\b\u0010\u001d\u001a\u00020\u001eH\u0016J&\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020#2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\t0\u0003H\u0016J\u0010\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u000bH\u0002J\"\u0010(\u001a\u00020 2\u0006\u0010\u0012\u001a\u00020&2\u0006\u0010\"\u001a\u00020#2\b\b\u0002\u0010)\u001a\u00020*H\u0002J\u0010\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0002J\u0010\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020,H\u0002R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u0013X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0011R\u0014\u0010\u0018\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0011R\u0014\u0010\u001a\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c¨\u00063"}, d2 = {"Lcom/jarvis/app/tts/OpenAiSpeechTtsBackend;", "Lcom/jarvis/core/voice/StreamingTtsBackend;", "baseUrlProvider", "Lkotlin/Function0;", "", "apiKeyProvider", "modelProvider", "voiceProvider", "online", "", "connectTimeoutMs", "", "readTimeoutMs", "<init>", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;II)V", "id", "getId", "()Ljava/lang/String;", "kind", "Lcom/jarvis/core/voice/VoiceProviderKind;", "getKind", "()Lcom/jarvis/core/voice/VoiceProviderKind;", "model", "getModel", "defaultVoice", "getDefaultVoice", "supportsStreamingAudio", "getSupportsStreamingAudio", "()Z", "availability", "Lcom/jarvis/core/voice/VoiceProviderAvailability;", "synthesize", "Lcom/jarvis/core/voice/TtsAudioResult;", VoiceService.EXTRA_TEXT, "voice", "Lcom/jarvis/core/voice/TtsVoiceConfig;", "cancelFlag", "mapHttp", "Lcom/jarvis/core/voice/TtsErrorKind;", "code", NotificationCompat.CATEGORY_ERROR, "setupMs", "", "readAll", "", "input", "Ljava/io/InputStream;", "pcm16leToFloat", "", "pcm", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class OpenAiSpeechTtsBackend implements StreamingTtsBackend {
    public static final String DEFAULT_BASE_URL = "https://api.openai.com/v1";
    public static final String DEFAULT_MODEL = "tts-1";
    public static final String DEFAULT_VOICE = "alloy";
    public static final int OPENAI_PCM_RATE = 24000;
    private static final String TAG = "Jarvis.Tts";
    private final Function0<String> apiKeyProvider;
    private final Function0<String> baseUrlProvider;
    private final int connectTimeoutMs;
    private final String id;
    private final VoiceProviderKind kind;
    private final Function0<String> modelProvider;
    private final Function0<Boolean> online;
    private final int readTimeoutMs;
    private final boolean supportsStreamingAudio;
    private final Function0<String> voiceProvider;

    public OpenAiSpeechTtsBackend(Function0<String> baseUrlProvider, Function0<String> apiKeyProvider, Function0<String> modelProvider, Function0<String> voiceProvider, Function0<Boolean> online, int connectTimeoutMs, int readTimeoutMs) {
        Intrinsics.checkNotNullParameter(baseUrlProvider, "baseUrlProvider");
        Intrinsics.checkNotNullParameter(apiKeyProvider, "apiKeyProvider");
        Intrinsics.checkNotNullParameter(modelProvider, "modelProvider");
        Intrinsics.checkNotNullParameter(voiceProvider, "voiceProvider");
        Intrinsics.checkNotNullParameter(online, "online");
        this.baseUrlProvider = baseUrlProvider;
        this.apiKeyProvider = apiKeyProvider;
        this.modelProvider = modelProvider;
        this.voiceProvider = voiceProvider;
        this.online = online;
        this.connectTimeoutMs = connectTimeoutMs;
        this.readTimeoutMs = readTimeoutMs;
        this.id = "tts.cloud.openai_speech";
        this.kind = VoiceProviderKind.CLOUD;
    }

    public /* synthetic */ OpenAiSpeechTtsBackend(Function0 function0, Function0 function1, Function0 function2, Function0 function3, Function0 function4, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(function0, function1, (i3 & 4) != 0 ? new Function0() { // from class: com.jarvis.app.tts.OpenAiSpeechTtsBackend$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return OpenAiSpeechTtsBackend.DEFAULT_MODEL;
            }
        } : function2, (i3 & 8) != 0 ? new Function0() { // from class: com.jarvis.app.tts.OpenAiSpeechTtsBackend$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return OpenAiSpeechTtsBackend.DEFAULT_VOICE;
            }
        } : function3, (i3 & 16) != 0 ? new Function0() { // from class: com.jarvis.app.tts.OpenAiSpeechTtsBackend$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(OpenAiSpeechTtsBackend._init_$lambda$2());
            }
        } : function4, (i3 & 32) != 0 ? 8000 : i, (i3 & 64) != 0 ? 30000 : i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean _init_$lambda$2() {
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
        String strInvoke = this.modelProvider.invoke();
        if (StringsKt.isBlank(strInvoke)) {
            strInvoke = DEFAULT_MODEL;
        }
        return strInvoke;
    }

    @Override // com.jarvis.core.voice.StreamingTtsBackend
    public String getDefaultVoice() {
        String strInvoke = this.voiceProvider.invoke();
        if (StringsKt.isBlank(strInvoke)) {
            strInvoke = DEFAULT_VOICE;
        }
        return strInvoke;
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
        if (StringsKt.isBlank(this.apiKeyProvider.invoke()) || StringsKt.isBlank(this.baseUrlProvider.invoke())) {
            return VoiceProviderAvailability.UNAVAILABLE;
        }
        return VoiceProviderAvailability.AVAILABLE;
    }

    /* JADX WARN: Code duplicated, block: B:80:0x021c A[Catch: all -> 0x02d2, TRY_ENTER, TryCatch #7 {all -> 0x02d2, blocks: (B:57:0x01e5, B:81:0x0220, B:93:0x0265, B:102:0x027b, B:108:0x0293, B:80:0x021c, B:66:0x01fb), top: B:157:0x01e5 }] */
    @Override // com.jarvis.core.voice.StreamingTtsBackend
    public TtsAudioResult synthesize(String text, TtsVoiceConfig voice, Function0<Boolean> cancelFlag) {
        Throwable th;
        byte[] all;
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(voice, "voice");
        Intrinsics.checkNotNullParameter(cancelFlag, "cancelFlag");
        if (cancelFlag.invoke().booleanValue()) {
            return err$default(this, TtsErrorKind.CANCELLED, voice, 0L, 4, null);
        }
        String key = this.apiKeyProvider.invoke();
        String base = StringsKt.trimEnd(StringsKt.trim((CharSequence) this.baseUrlProvider.invoke()).toString(), IOUtils.DIR_SEPARATOR_UNIX);
        if (StringsKt.isBlank(key) || StringsKt.isBlank(base)) {
            return err$default(this, TtsErrorKind.AUTH, voice, 0L, 4, null);
        }
        String model = voice.getModel();
        if (StringsKt.isBlank(model)) {
            model = getModel();
        }
        String modelId = model;
        String voice2 = voice.getVoice();
        if (StringsKt.isBlank(voice2)) {
            voice2 = getDefaultVoice();
        }
        String it = voice2;
        if (Intrinsics.areEqual(it, "default") || Intrinsics.areEqual(it, "dmitri")) {
            it = getDefaultVoice();
        }
        String voiceId = it;
        String body = new JSONObject().put("model", modelId).put("input", StringsKt.take(text, 4096)).put("voice", voiceId).put("response_format", "pcm").toString();
        Intrinsics.checkNotNullExpressionValue(body, "toString(...)");
        String url = base + "/audio/speech";
        long t0 = System.currentTimeMillis();
        try {
            if (cancelFlag.invoke().booleanValue()) {
                try {
                    try {
                        return err$default(this, TtsErrorKind.CANCELLED, voice, 0L, 4, null);
                    } catch (Throwable th2) {
                        t = th2;
                    }
                } catch (Throwable th3) {
                    t = th3;
                }
            } else {
                try {
                    try {
                        URLConnection uRLConnectionOpenConnection = new URI(url).toURL().openConnection();
                        Intrinsics.checkNotNull(uRLConnectionOpenConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
                        HttpURLConnection $this$synthesize_u24lambda_u243 = (HttpURLConnection) uRLConnectionOpenConnection;
                        $this$synthesize_u24lambda_u243.setRequestMethod("POST");
                        $this$synthesize_u24lambda_u243.setDoInput(true);
                        $this$synthesize_u24lambda_u243.setDoOutput(true);
                        $this$synthesize_u24lambda_u243.setConnectTimeout(this.connectTimeoutMs);
                        $this$synthesize_u24lambda_u243.setReadTimeout(this.readTimeoutMs);
                        $this$synthesize_u24lambda_u243.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                        $this$synthesize_u24lambda_u243.setRequestProperty("Authorization", "Bearer " + key);
                        try {
                            try {
                                try {
                                    OutputStream outputStream = $this$synthesize_u24lambda_u243.getOutputStream();
                                    try {
                                        OutputStream os = outputStream;
                                        try {
                                            byte[] bytes = body.getBytes(Charsets.UTF_8);
                                            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                                            os.write(bytes);
                                            os.flush();
                                            Unit unit = Unit.INSTANCE;
                                            try {
                                                CloseableKt.closeFinally(outputStream, null);
                                                try {
                                                    try {
                                                        if (cancelFlag.invoke().booleanValue()) {
                                                            try {
                                                                TtsAudioResult ttsAudioResultErr$default = err$default(this, TtsErrorKind.CANCELLED, voice, 0L, 4, null);
                                                                $this$synthesize_u24lambda_u243.disconnect();
                                                                return ttsAudioResultErr$default;
                                                            } catch (Throwable th4) {
                                                                th = th4;
                                                            }
                                                        } else {
                                                            try {
                                                                int code = $this$synthesize_u24lambda_u243.getResponseCode();
                                                                InputStream stream = 200 <= code && code < 300 ? $this$synthesize_u24lambda_u243.getInputStream() : $this$synthesize_u24lambda_u243.getErrorStream();
                                                                if (stream != null) {
                                                                    InputStream it2 = stream;
                                                                    try {
                                                                        all = readAll(it2);
                                                                        CloseableKt.closeFinally(it2, null);
                                                                        if (all == null) {
                                                                            all = new byte[0];
                                                                        }
                                                                    } catch (Throwable th5) {
                                                                        try {
                                                                            throw th5;
                                                                        } catch (Throwable th6) {
                                                                            CloseableKt.closeFinally(it2, th5);
                                                                            throw th6;
                                                                        }
                                                                    }
                                                                } else {
                                                                    all = new byte[0];
                                                                }
                                                                byte[] bytes2 = all;
                                                                long setupMs = RangesKt.coerceAtLeast(System.currentTimeMillis() - t0, 0L);
                                                                if (!(200 <= code && code < 300)) {
                                                                    Log.w(TAG, "speech HTTP " + code + " (key redacted)");
                                                                    TtsAudioResult ttsAudioResultErr = err(mapHttp(code), voice, setupMs);
                                                                    $this$synthesize_u24lambda_u243.disconnect();
                                                                    return ttsAudioResultErr;
                                                                }
                                                                if (bytes2.length == 0) {
                                                                    TtsAudioResult ttsAudioResultErr2 = err(TtsErrorKind.DECODING, voice, setupMs);
                                                                    $this$synthesize_u24lambda_u243.disconnect();
                                                                    return ttsAudioResultErr2;
                                                                }
                                                                if (cancelFlag.invoke().booleanValue()) {
                                                                    TtsAudioResult ttsAudioResultErr3 = err(TtsErrorKind.CANCELLED, voice, setupMs);
                                                                    $this$synthesize_u24lambda_u243.disconnect();
                                                                    return ttsAudioResultErr3;
                                                                }
                                                                float[] samples = pcm16leToFloat(bytes2);
                                                                long synthMs = RangesKt.coerceAtLeast(System.currentTimeMillis() - t0, 0L);
                                                                TtsAudioResult ttsAudioResult = new TtsAudioResult(samples, 24000, getId(), modelId, voiceId, setupMs, synthMs, false, false, null, null, 1792, null);
                                                                $this$synthesize_u24lambda_u243.disconnect();
                                                                return ttsAudioResult;
                                                            } catch (Throwable th7) {
                                                                th = th7;
                                                            }
                                                        }
                                                    } catch (Throwable th8) {
                                                        t = th8;
                                                    }
                                                } catch (Throwable th9) {
                                                    th = th9;
                                                }
                                            } catch (Throwable th10) {
                                                th = th10;
                                            }
                                        } catch (Throwable th11) {
                                            th = th11;
                                            try {
                                                throw th;
                                            } catch (Throwable th12) {
                                                CloseableKt.closeFinally(outputStream, th);
                                                throw th12;
                                            }
                                        }
                                    } catch (Throwable th13) {
                                        th = th13;
                                    }
                                } catch (Throwable th14) {
                                    th = th14;
                                }
                            } catch (Throwable th15) {
                                th = th15;
                            }
                            $this$synthesize_u24lambda_u243.disconnect();
                            throw th;
                        } catch (Throwable th16) {
                            t = th16;
                        }
                    } catch (Throwable th17) {
                        t = th17;
                    }
                } catch (Throwable th18) {
                    t = th18;
                }
            }
        } catch (Throwable th19) {
            t = th19;
        }
        Log.w(TAG, "speech transport: " + t.getClass().getSimpleName());
        return err$default(this, TtsErrorKind.NETWORK, voice, 0L, 4, null);
    }

    private final TtsErrorKind mapHttp(int code) {
        if (code == 401 || code == 403) {
            return TtsErrorKind.AUTH;
        }
        if (code == 429) {
            return TtsErrorKind.RATE_LIMIT;
        }
        if (code == 404 || code == 501) {
            return TtsErrorKind.UNSUPPORTED_VOICE;
        }
        boolean z = false;
        if (500 <= code && code < 600) {
            z = true;
        }
        return z ? TtsErrorKind.PROVIDER_UNAVAILABLE : TtsErrorKind.NETWORK;
    }

    static /* synthetic */ TtsAudioResult err$default(OpenAiSpeechTtsBackend openAiSpeechTtsBackend, TtsErrorKind ttsErrorKind, TtsVoiceConfig ttsVoiceConfig, long j, int i, Object obj) {
        if ((i & 4) != 0) {
            j = 0;
        }
        return openAiSpeechTtsBackend.err(ttsErrorKind, ttsVoiceConfig, j);
    }

    private final TtsAudioResult err(TtsErrorKind kind, TtsVoiceConfig voice, long setupMs) {
        float[] fArr = new float[0];
        String id = getId();
        String model = getModel();
        String voice2 = voice.getVoice();
        if (StringsKt.isBlank(voice2)) {
            voice2 = getDefaultVoice();
        }
        return new TtsAudioResult(fArr, 24000, id, model, voice2, setupMs, 0L, false, false, null, kind, 832, null);
    }

    private final byte[] readAll(InputStream input) throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        byte[] tmp = new byte[8192];
        while (true) {
            int n = input.read(tmp);
            if (n > 0) {
                buf.write(tmp, 0, n);
            } else {
                byte[] byteArray = buf.toByteArray();
                Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
                return byteArray;
            }
        }
    }

    private final float[] pcm16leToFloat(byte[] pcm) {
        int n = pcm.length / 2;
        float[] out = new float[n];
        int i = 0;
        for (int o = 0; o < n; o++) {
            int lo = pcm[i] & UByte.MAX_VALUE;
            int hi = pcm[i + 1];
            short v = (short) ((hi << 8) | lo);
            out[o] = v / 32768.0f;
            i += 2;
        }
        return out;
    }
}
