package com.jarvis.app.asr;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import com.google.android.gms.common.internal.ImagesContract;
import com.jarvis.app.JarvisSettings;
import com.jarvis.asr.AsrModelSpec;
import com.jarvis.asr.AsrSession;
import com.jarvis.asr.LocalStreamingAsrBackend;
import com.jarvis.core.voice.StreamingAsrBackend;
import com.jarvis.core.voice.StreamingAsrRouter;
import com.jarvis.core.voice.VoiceProviderAvailability;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: CloudAsrFactory.kt */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\"\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bJ\u000e\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\tJ\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\u0015"}, d2 = {"Lcom/jarvis/app/asr/CloudAsrFactory;", "", "<init>", "()V", "isOnline", "", "context", "Landroid/content/Context;", "localBackend", "Lcom/jarvis/core/voice/StreamingAsrBackend;", "sessionFactory", "Lkotlin/Function0;", "Lcom/jarvis/asr/AsrSession;", "ready", "cloudBackend", "router", "Lcom/jarvis/core/voice/StreamingAsrRouter;", ImagesContract.LOCAL, "cloud", "cloudConfiguredLabel", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CloudAsrFactory {
    public static final CloudAsrFactory INSTANCE = new CloudAsrFactory();

    /* JADX INFO: compiled from: CloudAsrFactory.kt */
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

    private CloudAsrFactory() {
    }

    public final boolean isOnline(Context context) {
        Network net;
        NetworkCapabilities caps;
        Intrinsics.checkNotNullParameter(context, "context");
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(ConnectivityManager.class);
        return (cm == null || (net = cm.getActiveNetwork()) == null || (caps = cm.getNetworkCapabilities(net)) == null || !caps.hasCapability(12) || !caps.hasCapability(16)) ? false : true;
    }

    public final StreamingAsrBackend localBackend(Function0<? extends AsrSession> sessionFactory, Function0<Boolean> ready) {
        Intrinsics.checkNotNullParameter(sessionFactory, "sessionFactory");
        Intrinsics.checkNotNullParameter(ready, "ready");
        return new LocalStreamingAsrBackend(sessionFactory, "asr.local.sherpa", AsrModelSpec.INSTANCE.getRU_STREAMING().getDirName(), ready);
    }

    public final StreamingAsrBackend cloudBackend(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        final Context app = context.getApplicationContext();
        JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
        Intrinsics.checkNotNull(app);
        String whisperUrl = jarvisSettings.asrCloudBaseUrl(app);
        String whisperKey = JarvisSettings.INSTANCE.asrCloudApiKey(app);
        if (!StringsKt.isBlank(whisperUrl) && !StringsKt.isBlank(whisperKey)) {
            return new OpenAiWhisperStreamingAsrBackend(new Function0() { // from class: com.jarvis.app.asr.CloudAsrFactory$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return CloudAsrFactory.cloudBackend$lambda$0(app);
                }
            }, new Function0() { // from class: com.jarvis.app.asr.CloudAsrFactory$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return CloudAsrFactory.cloudBackend$lambda$1(app);
                }
            }, new Function0() { // from class: com.jarvis.app.asr.CloudAsrFactory$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return CloudAsrFactory.cloudBackend$lambda$2(app);
                }
            }, new Function0() { // from class: com.jarvis.app.asr.CloudAsrFactory$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(CloudAsrFactory.cloudBackend$lambda$3(app));
                }
            }, 0L, 0, 0, 112, null);
        }
        return new AndroidSpeechCloudAsrBackend(app, new Function0() { // from class: com.jarvis.app.asr.CloudAsrFactory$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(CloudAsrFactory.cloudBackend$lambda$4(app));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String cloudBackend$lambda$0(Context $app) {
        JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
        Intrinsics.checkNotNull($app);
        return jarvisSettings.asrCloudBaseUrl($app);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String cloudBackend$lambda$1(Context $app) {
        JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
        Intrinsics.checkNotNull($app);
        return jarvisSettings.asrCloudApiKey($app);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String cloudBackend$lambda$2(Context $app) {
        JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
        Intrinsics.checkNotNull($app);
        return jarvisSettings.asrCloudModel($app);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean cloudBackend$lambda$3(Context $app) {
        CloudAsrFactory cloudAsrFactory = INSTANCE;
        Intrinsics.checkNotNull($app);
        return cloudAsrFactory.isOnline($app);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean cloudBackend$lambda$4(Context $app) {
        CloudAsrFactory cloudAsrFactory = INSTANCE;
        Intrinsics.checkNotNull($app);
        return cloudAsrFactory.isOnline($app);
    }

    public final StreamingAsrRouter router(StreamingAsrBackend local, StreamingAsrBackend cloud) {
        Intrinsics.checkNotNullParameter(local, "local");
        return new StreamingAsrRouter(local, cloud);
    }

    public final String cloudConfiguredLabel(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        StreamingAsrBackend cloud = cloudBackend(context);
        switch (WhenMappings.$EnumSwitchMapping$0[cloud.availability().ordinal()]) {
            case 1:
                return cloud.getId() + "/" + cloud.getModel();
            case 2:
                return cloud.getId() + "/degraded";
            case 3:
                return cloud.getId() + "/unavailable";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
