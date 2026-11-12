package com.jarvis.core.capability;

import com.jarvis.core.common.model.RiskLevel;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CapabilityDiscovery.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0011\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\rJ\t\u0010\u0013\u001a\u00020\bHÆ\u0003J6\u0010\u0014\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001¢\u0006\u0002\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u00062\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0004HÖ\u0001R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0005\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/jarvis/core/capability/CapabilityDiscoveryConstraints;", "", "grantedPermissions", "", "", "isOnline", "", "maxRisk", "Lcom/jarvis/core/common/model/RiskLevel;", "<init>", "(Ljava/util/Set;Ljava/lang/Boolean;Lcom/jarvis/core/common/model/RiskLevel;)V", "getGrantedPermissions", "()Ljava/util/Set;", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getMaxRisk", "()Lcom/jarvis/core/common/model/RiskLevel;", "component1", "component2", "component3", "copy", "(Ljava/util/Set;Ljava/lang/Boolean;Lcom/jarvis/core/common/model/RiskLevel;)Lcom/jarvis/core/capability/CapabilityDiscoveryConstraints;", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class CapabilityDiscoveryConstraints {
    private final Set<String> grantedPermissions;
    private final Boolean isOnline;
    private final RiskLevel maxRisk;

    public CapabilityDiscoveryConstraints() {
        this(null, null, null, 7, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CapabilityDiscoveryConstraints copy$default(CapabilityDiscoveryConstraints capabilityDiscoveryConstraints, Set set, Boolean bool, RiskLevel riskLevel, int i, Object obj) {
        if ((i & 1) != 0) {
            set = capabilityDiscoveryConstraints.grantedPermissions;
        }
        if ((i & 2) != 0) {
            bool = capabilityDiscoveryConstraints.isOnline;
        }
        if ((i & 4) != 0) {
            riskLevel = capabilityDiscoveryConstraints.maxRisk;
        }
        return capabilityDiscoveryConstraints.copy(set, bool, riskLevel);
    }

    public final Set<String> component1() {
        return this.grantedPermissions;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Boolean getIsOnline() {
        return this.isOnline;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final RiskLevel getMaxRisk() {
        return this.maxRisk;
    }

    public final CapabilityDiscoveryConstraints copy(Set<String> grantedPermissions, Boolean isOnline, RiskLevel maxRisk) {
        Intrinsics.checkNotNullParameter(maxRisk, "maxRisk");
        return new CapabilityDiscoveryConstraints(grantedPermissions, isOnline, maxRisk);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CapabilityDiscoveryConstraints)) {
            return false;
        }
        CapabilityDiscoveryConstraints capabilityDiscoveryConstraints = (CapabilityDiscoveryConstraints) other;
        return Intrinsics.areEqual(this.grantedPermissions, capabilityDiscoveryConstraints.grantedPermissions) && Intrinsics.areEqual(this.isOnline, capabilityDiscoveryConstraints.isOnline) && this.maxRisk == capabilityDiscoveryConstraints.maxRisk;
    }

    public int hashCode() {
        return ((((this.grantedPermissions == null ? 0 : this.grantedPermissions.hashCode()) * 31) + (this.isOnline != null ? this.isOnline.hashCode() : 0)) * 31) + this.maxRisk.hashCode();
    }

    public String toString() {
        return "CapabilityDiscoveryConstraints(grantedPermissions=" + this.grantedPermissions + ", isOnline=" + this.isOnline + ", maxRisk=" + this.maxRisk + ")";
    }

    public CapabilityDiscoveryConstraints(Set<String> set, Boolean isOnline, RiskLevel maxRisk) {
        Intrinsics.checkNotNullParameter(maxRisk, "maxRisk");
        this.grantedPermissions = set;
        this.isOnline = isOnline;
        this.maxRisk = maxRisk;
    }

    public /* synthetic */ CapabilityDiscoveryConstraints(Set set, Boolean bool, RiskLevel riskLevel, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : set, (i & 2) != 0 ? null : bool, (i & 4) != 0 ? RiskLevel.BLOCKED : riskLevel);
    }

    public final Set<String> getGrantedPermissions() {
        return this.grantedPermissions;
    }

    public final Boolean isOnline() {
        return this.isOnline;
    }

    public final RiskLevel getMaxRisk() {
        return this.maxRisk;
    }
}
