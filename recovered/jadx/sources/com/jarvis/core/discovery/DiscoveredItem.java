package com.jarvis.core.discovery;

import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.execution.ToolAvailability;
import com.jarvis.core.execution.ToolExecutionLocality;
import com.jarvis.core.execution.ToolProviderSource;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DiscoveryModels.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b*\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0085\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\f\u0012\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\f¢\u0006\u0004\b\u0017\u0010\u0018J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\t\u00102\u001a\u00020\bHÆ\u0003J\t\u00103\u001a\u00020\nHÆ\u0003J\u000f\u00104\u001a\b\u0012\u0004\u0012\u00020\u00030\fHÆ\u0003J\t\u00105\u001a\u00020\u000eHÆ\u0003J\t\u00106\u001a\u00020\u0010HÆ\u0003J\t\u00107\u001a\u00020\u0012HÆ\u0003J\t\u00108\u001a\u00020\u0014HÆ\u0003J\u000f\u00109\u001a\b\u0012\u0004\u0012\u00020\u00030\fHÆ\u0003J\u000f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00030\fHÆ\u0003J\u009f\u0001\u0010;\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\f2\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\fHÆ\u0001J\u0013\u0010<\u001a\u00020\u00142\b\u0010=\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010>\u001a\u00020?HÖ\u0001J\t\u0010@\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001aR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\f¢\u0006\b\n\u0000\u001a\u0004\b,\u0010#R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\f¢\u0006\b\n\u0000\u001a\u0004\b-\u0010#¨\u0006A"}, d2 = {"Lcom/jarvis/core/discovery/DiscoveredItem;", "", "toolId", "", "capabilityId", "name", "description", "score", "", "riskLevel", "Lcom/jarvis/core/common/model/RiskLevel;", "requiredPermissions", "", "availability", "Lcom/jarvis/core/execution/ToolAvailability;", "source", "Lcom/jarvis/core/execution/ToolProviderSource;", "locality", "Lcom/jarvis/core/execution/ToolExecutionLocality;", "hasExecutable", "", "parameterNames", "reasons", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;FLcom/jarvis/core/common/model/RiskLevel;Ljava/util/List;Lcom/jarvis/core/execution/ToolAvailability;Lcom/jarvis/core/execution/ToolProviderSource;Lcom/jarvis/core/execution/ToolExecutionLocality;ZLjava/util/List;Ljava/util/List;)V", "getToolId", "()Ljava/lang/String;", "getCapabilityId", "getName", "getDescription", "getScore", "()F", "getRiskLevel", "()Lcom/jarvis/core/common/model/RiskLevel;", "getRequiredPermissions", "()Ljava/util/List;", "getAvailability", "()Lcom/jarvis/core/execution/ToolAvailability;", "getSource", "()Lcom/jarvis/core/execution/ToolProviderSource;", "getLocality", "()Lcom/jarvis/core/execution/ToolExecutionLocality;", "getHasExecutable", "()Z", "getParameterNames", "getReasons", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class DiscoveredItem {
    private final ToolAvailability availability;
    private final String capabilityId;
    private final String description;
    private final boolean hasExecutable;
    private final ToolExecutionLocality locality;
    private final String name;
    private final List<String> parameterNames;
    private final List<String> reasons;
    private final List<String> requiredPermissions;
    private final RiskLevel riskLevel;
    private final float score;
    private final ToolProviderSource source;
    private final String toolId;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getToolId() {
        return this.toolId;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final ToolExecutionLocality getLocality() {
        return this.locality;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final boolean getHasExecutable() {
        return this.hasExecutable;
    }

    public final List<String> component12() {
        return this.parameterNames;
    }

    public final List<String> component13() {
        return this.reasons;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getCapabilityId() {
        return this.capabilityId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final float getScore() {
        return this.score;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final RiskLevel getRiskLevel() {
        return this.riskLevel;
    }

    public final List<String> component7() {
        return this.requiredPermissions;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final ToolAvailability getAvailability() {
        return this.availability;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final ToolProviderSource getSource() {
        return this.source;
    }

    public final DiscoveredItem copy(String toolId, String capabilityId, String name, String description, float score, RiskLevel riskLevel, List<String> requiredPermissions, ToolAvailability availability, ToolProviderSource source, ToolExecutionLocality locality, boolean hasExecutable, List<String> parameterNames, List<String> reasons) {
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(riskLevel, "riskLevel");
        Intrinsics.checkNotNullParameter(requiredPermissions, "requiredPermissions");
        Intrinsics.checkNotNullParameter(availability, "availability");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(locality, "locality");
        Intrinsics.checkNotNullParameter(parameterNames, "parameterNames");
        Intrinsics.checkNotNullParameter(reasons, "reasons");
        return new DiscoveredItem(toolId, capabilityId, name, description, score, riskLevel, requiredPermissions, availability, source, locality, hasExecutable, parameterNames, reasons);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DiscoveredItem)) {
            return false;
        }
        DiscoveredItem discoveredItem = (DiscoveredItem) other;
        return Intrinsics.areEqual(this.toolId, discoveredItem.toolId) && Intrinsics.areEqual(this.capabilityId, discoveredItem.capabilityId) && Intrinsics.areEqual(this.name, discoveredItem.name) && Intrinsics.areEqual(this.description, discoveredItem.description) && Float.compare(this.score, discoveredItem.score) == 0 && this.riskLevel == discoveredItem.riskLevel && Intrinsics.areEqual(this.requiredPermissions, discoveredItem.requiredPermissions) && this.availability == discoveredItem.availability && this.source == discoveredItem.source && this.locality == discoveredItem.locality && this.hasExecutable == discoveredItem.hasExecutable && Intrinsics.areEqual(this.parameterNames, discoveredItem.parameterNames) && Intrinsics.areEqual(this.reasons, discoveredItem.reasons);
    }

    public int hashCode() {
        return (((((((((((((((((((((((this.toolId.hashCode() * 31) + (this.capabilityId == null ? 0 : this.capabilityId.hashCode())) * 31) + this.name.hashCode()) * 31) + this.description.hashCode()) * 31) + Float.hashCode(this.score)) * 31) + this.riskLevel.hashCode()) * 31) + this.requiredPermissions.hashCode()) * 31) + this.availability.hashCode()) * 31) + this.source.hashCode()) * 31) + this.locality.hashCode()) * 31) + Boolean.hashCode(this.hasExecutable)) * 31) + this.parameterNames.hashCode()) * 31) + this.reasons.hashCode();
    }

    public String toString() {
        return "DiscoveredItem(toolId=" + this.toolId + ", capabilityId=" + this.capabilityId + ", name=" + this.name + ", description=" + this.description + ", score=" + this.score + ", riskLevel=" + this.riskLevel + ", requiredPermissions=" + this.requiredPermissions + ", availability=" + this.availability + ", source=" + this.source + ", locality=" + this.locality + ", hasExecutable=" + this.hasExecutable + ", parameterNames=" + this.parameterNames + ", reasons=" + this.reasons + ")";
    }

    public DiscoveredItem(String toolId, String capabilityId, String name, String description, float score, RiskLevel riskLevel, List<String> requiredPermissions, ToolAvailability availability, ToolProviderSource source, ToolExecutionLocality locality, boolean hasExecutable, List<String> parameterNames, List<String> reasons) {
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(riskLevel, "riskLevel");
        Intrinsics.checkNotNullParameter(requiredPermissions, "requiredPermissions");
        Intrinsics.checkNotNullParameter(availability, "availability");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(locality, "locality");
        Intrinsics.checkNotNullParameter(parameterNames, "parameterNames");
        Intrinsics.checkNotNullParameter(reasons, "reasons");
        this.toolId = toolId;
        this.capabilityId = capabilityId;
        this.name = name;
        this.description = description;
        this.score = score;
        this.riskLevel = riskLevel;
        this.requiredPermissions = requiredPermissions;
        this.availability = availability;
        this.source = source;
        this.locality = locality;
        this.hasExecutable = hasExecutable;
        this.parameterNames = parameterNames;
        this.reasons = reasons;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ DiscoveredItem(String str, String str2, String str3, String str4, float f, RiskLevel riskLevel, List list, ToolAvailability toolAvailability, ToolProviderSource toolProviderSource, ToolExecutionLocality toolExecutionLocality, boolean z, List list2, List list3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        List listEmptyList;
        if ((i & 4096) == 0) {
            listEmptyList = list3;
        } else {
            listEmptyList = CollectionsKt.emptyList();
        }
        this(str, str2, str3, str4, f, riskLevel, list, toolAvailability, toolProviderSource, toolExecutionLocality, z, list2, listEmptyList);
    }

    public final String getToolId() {
        return this.toolId;
    }

    public final String getCapabilityId() {
        return this.capabilityId;
    }

    public final String getName() {
        return this.name;
    }

    public final String getDescription() {
        return this.description;
    }

    public final float getScore() {
        return this.score;
    }

    public final RiskLevel getRiskLevel() {
        return this.riskLevel;
    }

    public final List<String> getRequiredPermissions() {
        return this.requiredPermissions;
    }

    public final ToolAvailability getAvailability() {
        return this.availability;
    }

    public final ToolProviderSource getSource() {
        return this.source;
    }

    public final ToolExecutionLocality getLocality() {
        return this.locality;
    }

    public final boolean getHasExecutable() {
        return this.hasExecutable;
    }

    public final List<String> getParameterNames() {
        return this.parameterNames;
    }

    public final List<String> getReasons() {
        return this.reasons;
    }
}
