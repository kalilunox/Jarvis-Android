package com.jarvis.app.tts;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import com.jarvis.app.JarvisSettings;
import com.jarvis.core.voice.StreamingTtsBackend;
import com.jarvis.core.voice.VoiceProviderAvailability;
import com.jarvis.tts.JarvisTts;
import com.jarvis.tts.LocalPiperTtsBackend;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: CloudTtsFactory.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u001e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rJ\u000e\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\u0011"}, d2 = {"Lcom/jarvis/app/tts/CloudTtsFactory;", "", "<init>", "()V", "isOnline", "", "context", "Landroid/content/Context;", "localBackend", "Lcom/jarvis/core/voice/StreamingTtsBackend;", "voiceDir", "", "engine", "Lkotlin/Function0;", "Lcom/jarvis/tts/JarvisTts;", "cloudBackend", "cloudConfiguredLabel", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CloudTtsFactory {
    public static final CloudTtsFactory INSTANCE = new CloudTtsFactory();

    /* JADX INFO: compiled from: CloudTtsFactory.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[VoiceProviderAvailability.values().length];
            try {
                iArr[VoiceProviderAvailability.AVAILABLE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[VoiceProviderAvailability.DEGRADED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[VoiceProviderAvailability.UNAVAILABLE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private CloudTtsFactory() {
    }

    public final boolean isOnline(Context context) {
        Network net;
        NetworkCapabilities caps;
        Intrinsics.checkNotNullParameter(context, "context");
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(ConnectivityManager.class);
        return (cm == null || (net = cm.getActiveNetwork()) == null || (caps = cm.getNetworkCapabilities(net)) == null || !caps.hasCapability(12) || !caps.hasCapability(16)) ? false : true;
    }

    public final StreamingTtsBackend localBackend(String voiceDir, Function0<JarvisTts> engine) {
        Intrinsics.checkNotNullParameter(voiceDir, "voiceDir");
        Intrinsics.checkNotNullParameter(engine, "engine");
        return new LocalPiperTtsBackend(engine, null, voiceDir, StringsKt.substringBefore$default(voiceDir, "-medium", (String) null, 2, (Object) null), 2, null);
    }

    public final StreamingTtsBackend cloudBackend(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        final Context app = context.getApplicationContext();
        JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
        Intrinsics.checkNotNull(app);
        String url = jarvisSettings.ttsCloudBaseUrl(app);
        String key = JarvisSettings.INSTANCE.ttsCloudApiKey(app);
        if (!StringsKt.isBlank(url) && !StringsKt.isBlank(key)) {
            return new OpenAiSpeechTtsBackend(new Function0() { // from class: com.jarvis.app.tts.CloudTtsFactory$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return CloudTtsFactory.cloudBackend$lambda$0(app);
                }
            }, new Function0() { // from class: com.jarvis.app.tts.CloudTtsFactory$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return CloudTtsFactory.cloudBackend$lambda$1(app);
                }
            }, new Function0() { // from class: com.jarvis.app.tts.CloudTtsFactory$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return CloudTtsFactory.cloudBackend$lambda$2(app);
                }
            }, new Function0() { // from class: com.jarvis.app.tts.CloudTtsFactory$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return CloudTtsFactory.cloudBackend$lambda$3(app);
                }
            }, new Function0() { // from class: com.jarvis.app.tts.CloudTtsFactory$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(CloudTtsFactory.cloudBackend$lambda$4(app));
                }
            }, 0, 0, 96, null);
        }
        return new AndroidSystemTtsBackend(app, new Function0() { // from class: com.jarvis.app.tts.CloudTtsFactory$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(CloudTtsFactory.cloudBackend$lambda$5(app));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String cloudBackend$lambda$0(Context $app) {
        JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
        Intrinsics.checkNotNull($app);
        return jarvisSettings.ttsCloudBaseUrl($app);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String cloudBackend$lambda$1(Context $app) {
        JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
        Intrinsics.checkNotNull($app);
        return jarvisSettings.ttsCloudApiKey($app);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String cloudBackend$lambda$2(Context $app) {
        JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
        Intrinsics.checkNotNull($app);
        return jarvisSettings.ttsCloudModel($app);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String cloudBackend$lambda$3(Context $app) {
        JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
        Intrinsics.checkNotNull($app);
        return jarvisSettings.ttsCloudVoice($app);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean cloudBackend$lambda$4(Context $app) {
        CloudTtsFactory cloudTtsFactory = INSTANCE;
        Intrinsics.checkNotNull($app);
        return cloudTtsFactory.isOnline($app);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean cloudBackend$lambda$5(Context $app) {
        CloudTtsFactory cloudTtsFactory = INSTANCE;
        Intrinsics.checkNotNull($app);
        return cloudTtsFactory.isOnline($app);
    }

    public final String cloudConfiguredLabel(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        StreamingTtsBackend c = cloudBackend(context);
        switch (WhenMappings.$EnumSwitchMapping$0[c.availability().ordinal()]) {
            case 1:
                return c.getId() + "/" + c.getModel() + "/" + c.getDefaultVoice();
            case 2:
                return c.getId() + "/degraded";
            case 3:
                return c.getId() + "/unavailable";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
