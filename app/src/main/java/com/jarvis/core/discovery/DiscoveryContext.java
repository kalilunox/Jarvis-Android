package com.jarvis.core.discovery;

import com.google.android.gms.actions.SearchIntents;
import com.jarvis.core.common.model.RiskLevel;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DiscoveryModels.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b \b\u0086\b\u0018\u0000 *2\u00020\u0001:\u0001*BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\t\u0012\b\b\u0002\u0010\r\u001a\u00020\t¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\u0011\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007HÆ\u0003J\u0010\u0010 \u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0016J\t\u0010!\u001a\u00020\u000bHÆ\u0003J\t\u0010\"\u001a\u00020\tHÆ\u0003J\t\u0010#\u001a\u00020\tHÆ\u0003J^\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\tHÆ\u0001¢\u0006\u0002\u0010%J\u0013\u0010&\u001a\u00020\t2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020\u0005HÖ\u0001J\t\u0010)\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\b\u0010\u0016R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\r\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001b¨\u0006+"}, d2 = {"Lcom/jarvis/core/discovery/DiscoveryContext;", "", SearchIntents.EXTRA_QUERY, "", "limit", "", "grantedPermissions", "", "isOnline", "", "maxRisk", "Lcom/jarvis/core/common/model/RiskLevel;", "excludeUnavailable", "requireCapability", "<init>", "(Ljava/lang/String;ILjava/util/Set;Ljava/lang/Boolean;Lcom/jarvis/core/common/model/RiskLevel;ZZ)V", "getQuery", "()Ljava/lang/String;", "getLimit", "()I", "getGrantedPermissions", "()Ljava/util/Set;", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getMaxRisk", "()Lcom/jarvis/core/common/model/RiskLevel;", "getExcludeUnavailable", "()Z", "getRequireCapability", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;ILjava/util/Set;Ljava/lang/Boolean;Lcom/jarvis/core/common/model/RiskLevel;ZZ)Lcom/jarvis/core/discovery/DiscoveryContext;", "equals", "other", "hashCode", "toString", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class DiscoveryContext {
    public static final int DEFAULT_LIMIT = 6;
    public static final int HARD_CAP = 12;
    private final boolean excludeUnavailable;
    private final Set<String> grantedPermissions;
    private final Boolean isOnline;
    private final int limit;
    private final RiskLevel maxRisk;
    private final String query;
    private final boolean requireCapability;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DiscoveryContext copy$default(DiscoveryContext discoveryContext, String str, int i, Set set, Boolean bool, RiskLevel riskLevel, boolean z, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = discoveryContext.query;
        }
        if ((i2 & 2) != 0) {
            i = discoveryContext.limit;
        }
        int i3 = i;
        if ((i2 & 4) != 0) {
            set = discoveryContext.grantedPermissions;
        }
        Set set2 = set;
        if ((i2 & 8) != 0) {
            bool = discoveryContext.isOnline;
        }
        Boolean bool2 = bool;
        if ((i2 & 16) != 0) {
            riskLevel = discoveryContext.maxRisk;
        }
        RiskLevel riskLevel2 = riskLevel;
        if ((i2 & 32) != 0) {
            z = discoveryContext.excludeUnavailable;
        }
        boolean z3 = z;
        if ((i2 & 64) != 0) {
            z2 = discoveryContext.requireCapability;
        }
        return discoveryContext.copy(str, i3, set2, bool2, riskLevel2, z3, z2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getQuery() {
        return this.query;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getLimit() {
        return this.limit;
    }

    public final Set<String> component3() {
        return this.grantedPermissions;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Boolean getIsOnline() {
        return this.isOnline;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final RiskLevel getMaxRisk() {
        return this.maxRisk;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getExcludeUnavailable() {
        return this.excludeUnavailable;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getRequireCapability() {
        return this.requireCapability;
    }

    public final DiscoveryContext copy(String query, int limit, Set<String> grantedPermissions, Boolean isOnline, RiskLevel maxRisk, boolean excludeUnavailable, boolean requireCapability) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(maxRisk, "maxRisk");
        return new DiscoveryContext(query, limit, grantedPermissions, isOnline, maxRisk, excludeUnavailable, requireCapability);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DiscoveryContext)) {
            return false;
        }
        DiscoveryContext discoveryContext = (DiscoveryContext) other;
        return Intrinsics.areEqual(this.query, discoveryContext.query) && this.limit == discoveryContext.limit && Intrinsics.areEqual(this.grantedPermissions, discoveryContext.grantedPermissions) && Intrinsics.areEqual(this.isOnline, discoveryContext.isOnline) && this.maxRisk == discoveryContext.maxRisk && this.excludeUnavailable == discoveryContext.excludeUnavailable && this.requireCapability == discoveryContext.requireCapability;
    }

    public int hashCode() {
        return (((((((((((this.query.hashCode() * 31) + Integer.hashCode(this.limit)) * 31) + (this.grantedPermissions == null ? 0 : this.grantedPermissions.hashCode())) * 31) + (this.isOnline != null ? this.isOnline.hashCode() : 0)) * 31) + this.maxRisk.hashCode()) * 31) + Boolean.hashCode(this.excludeUnavailable)) * 31) + Boolean.hashCode(this.requireCapability);
    }

    public String toString() {
        return "DiscoveryContext(query=" + this.query + ", limit=" + this.limit + ", grantedPermissions=" + this.grantedPermissions + ", isOnline=" + this.isOnline + ", maxRisk=" + this.maxRisk + ", excludeUnavailable=" + this.excludeUnavailable + ", requireCapability=" + this.requireCapability + ")";
    }

    public DiscoveryContext(String query, int limit, Set<String> set, Boolean isOnline, RiskLevel maxRisk, boolean excludeUnavailable, boolean requireCapability) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(maxRisk, "maxRisk");
        this.query = query;
        this.limit = limit;
        this.grantedPermissions = set;
        this.isOnline = isOnline;
        this.maxRisk = maxRisk;
        this.excludeUnavailable = excludeUnavailable;
        this.requireCapability = requireCapability;
        if (!(this.limit > 0)) {
            throw new IllegalArgumentException("discovery limit must be > 0".toString());
        }
        if (!(this.limit <= 12)) {
            throw new IllegalArgumentException("discovery limit exceeds hard cap 12".toString());
        }
    }

    public /* synthetic */ DiscoveryContext(String str, int i, Set set, Boolean bool, RiskLevel riskLevel, boolean z, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? 6 : i, (i2 & 4) != 0 ? null : set, (i2 & 8) == 0 ? bool : null, (i2 & 16) != 0 ? RiskLevel.SENSITIVE : riskLevel, (i2 & 32) != 0 ? true : z, (i2 & 64) != 0 ? false : z2);
    }

    public final String getQuery() {
        return this.query;
    }

    public final int getLimit() {
        return this.limit;
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

    public final boolean getExcludeUnavailable() {
        return this.excludeUnavailable;
    }

    public final boolean getRequireCapability() {
        return this.requireCapability;
    }
}
