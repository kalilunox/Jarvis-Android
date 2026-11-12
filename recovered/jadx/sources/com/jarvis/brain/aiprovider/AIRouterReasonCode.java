package com.jarvis.brain.aiprovider;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: AIRouter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0012\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012¨\u0006\u0013"}, d2 = {"Lcom/jarvis/brain/aiprovider/AIRouterReasonCode;", "", "<init>", "(Ljava/lang/String;I)V", "PREFERRED", "PRIVACY_LOCAL_ONLY", "OFFLINE_LOCAL", "ONLINE_COMPLEX_CLOUD", "ONLINE_SIMPLE_LOCAL", "ONLINE_SIMPLE_CLOUD_PREF", "FALLBACK_LOCAL", "FALLBACK_ANY_AVAILABLE", "NONE_AVAILABLE", "ONLINE_QUALITY", "LATENCY_PRIORITY", "STRUCTURED_LOCAL", "STREAMING_CAPABLE", "CLOUD_UNAVAILABLE", "HEALTH_SKIP", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum AIRouterReasonCode {
    PREFERRED,
    PRIVACY_LOCAL_ONLY,
    OFFLINE_LOCAL,
    ONLINE_COMPLEX_CLOUD,
    ONLINE_SIMPLE_LOCAL,
    ONLINE_SIMPLE_CLOUD_PREF,
    FALLBACK_LOCAL,
    FALLBACK_ANY_AVAILABLE,
    NONE_AVAILABLE,
    ONLINE_QUALITY,
    LATENCY_PRIORITY,
    STRUCTURED_LOCAL,
    STREAMING_CAPABLE,
    CLOUD_UNAVAILABLE,
    HEALTH_SKIP;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    public static EnumEntries<AIRouterReasonCode> getEntries() {
        return $ENTRIES;
    }
}
