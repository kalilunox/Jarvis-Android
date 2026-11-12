package com.jarvis.wakeword;

import kotlin.Metadata;

/* JADX INFO: compiled from: OwwAudioFeatures.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"melWindowEnd", "", "melSize", "stepsBack", "EMBED_STEP", "wakeword-engine_debug"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class OwwAudioFeaturesKt {
    private static final int EMBED_STEP = 8;

    public static final int melWindowEnd(int melSize, int stepsBack) {
        return melSize - (stepsBack * 8);
    }
}
