package com.jarvis.brain.aiprovider;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AIRouter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\tHÆ\u0003J=\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u00032\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\tHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcom/jarvis/brain/aiprovider/AIRouterContext;", "", "isOnline", "", "privacyRestricted", "preferCloudWhenOnline", "complexity", "Lcom/jarvis/brain/aiprovider/AIRequestComplexity;", "preferredProviderId", "", "<init>", "(ZZZLcom/jarvis/brain/aiprovider/AIRequestComplexity;Ljava/lang/String;)V", "()Z", "getPrivacyRestricted", "getPreferCloudWhenOnline", "getComplexity", "()Lcom/jarvis/brain/aiprovider/AIRequestComplexity;", "getPreferredProviderId", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AIRouterContext {
    private final AIRequestComplexity complexity;
    private final boolean isOnline;
    private final boolean preferCloudWhenOnline;
    private final String preferredProviderId;
    private final boolean privacyRestricted;

    public static /* synthetic */ AIRouterContext copy$default(AIRouterContext aIRouterContext, boolean z, boolean z2, boolean z3, AIRequestComplexity aIRequestComplexity, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            z = aIRouterContext.isOnline;
        }
        if ((i & 2) != 0) {
            z2 = aIRouterContext.privacyRestricted;
        }
        boolean z4 = z2;
        if ((i & 4) != 0) {
            z3 = aIRouterContext.preferCloudWhenOnline;
        }
        boolean z5 = z3;
        if ((i & 8) != 0) {
            aIRequestComplexity = aIRouterContext.complexity;
        }
        AIRequestComplexity aIRequestComplexity2 = aIRequestComplexity;
        if ((i & 16) != 0) {
            str = aIRouterContext.preferredProviderId;
        }
        return aIRouterContext.copy(z, z4, z5, aIRequestComplexity2, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getIsOnline() {
        return this.isOnline;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getPrivacyRestricted() {
        return this.privacyRestricted;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getPreferCloudWhenOnline() {
        return this.preferCloudWhenOnline;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final AIRequestComplexity getComplexity() {
        return this.complexity;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getPreferredProviderId() {
        return this.preferredProviderId;
    }

    public final AIRouterContext copy(boolean isOnline, boolean privacyRestricted, boolean preferCloudWhenOnline, AIRequestComplexity complexity, String preferredProviderId) {
        Intrinsics.checkNotNullParameter(complexity, "complexity");
        return new AIRouterContext(isOnline, privacyRestricted, preferCloudWhenOnline, complexity, preferredProviderId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AIRouterContext)) {
            return false;
        }
        AIRouterContext aIRouterContext = (AIRouterContext) other;
        return this.isOnline == aIRouterContext.isOnline && this.privacyRestricted == aIRouterContext.privacyRestricted && this.preferCloudWhenOnline == aIRouterContext.preferCloudWhenOnline && this.complexity == aIRouterContext.complexity && Intrinsics.areEqual(this.preferredProviderId, aIRouterContext.preferredProviderId);
    }

    public int hashCode() {
        return (((((((Boolean.hashCode(this.isOnline) * 31) + Boolean.hashCode(this.privacyRestricted)) * 31) + Boolean.hashCode(this.preferCloudWhenOnline)) * 31) + this.complexity.hashCode()) * 31) + (this.preferredProviderId == null ? 0 : this.preferredProviderId.hashCode());
    }

    public String toString() {
        return "AIRouterContext(isOnline=" + this.isOnline + ", privacyRestricted=" + this.privacyRestricted + ", preferCloudWhenOnline=" + this.preferCloudWhenOnline + ", complexity=" + this.complexity + ", preferredProviderId=" + this.preferredProviderId + ")";
    }

    public AIRouterContext(boolean isOnline, boolean privacyRestricted, boolean preferCloudWhenOnline, AIRequestComplexity complexity, String preferredProviderId) {
        Intrinsics.checkNotNullParameter(complexity, "complexity");
        this.isOnline = isOnline;
        this.privacyRestricted = privacyRestricted;
        this.preferCloudWhenOnline = preferCloudWhenOnline;
        this.complexity = complexity;
        this.preferredProviderId = preferredProviderId;
    }

    public /* synthetic */ AIRouterContext(boolean z, boolean z2, boolean z3, AIRequestComplexity aIRequestComplexity, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, (i & 2) != 0 ? false : z2, (i & 4) != 0 ? false : z3, (i & 8) != 0 ? AIRequestComplexity.SIMPLE : aIRequestComplexity, (i & 16) != 0 ? null : str);
    }

    public final boolean isOnline() {
        return this.isOnline;
    }

    public final boolean getPrivacyRestricted() {
        return this.privacyRestricted;
    }

    public final boolean getPreferCloudWhenOnline() {
        return this.preferCloudWhenOnline;
    }

    public final AIRequestComplexity getComplexity() {
        return this.complexity;
    }

    public final String getPreferredProviderId() {
        return this.preferredProviderId;
    }
}
