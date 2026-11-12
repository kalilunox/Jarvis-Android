package com.jarvis.core.execution;

import com.jarvis.core.autonomy.ActionKind;
import com.jarvis.core.common.model.RiskLevel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ToolRegistration.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0087\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u0010\u0012\u0006\u0010\u0018\u001a\u00020\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\t\u00102\u001a\u00020\u0003HÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00106\u001a\u00020\bHÆ\u0003J\t\u00107\u001a\u00020\nHÆ\u0003J\t\u00108\u001a\u00020\fHÆ\u0003J\t\u00109\u001a\u00020\u000eHÆ\u0003J\u000f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00030\u0010HÆ\u0003J\t\u0010;\u001a\u00020\u0012HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0014HÆ\u0003J\t\u0010=\u001a\u00020\u0016HÆ\u0003J\u000f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00030\u0010HÆ\u0003J\t\u0010?\u001a\u00020\u0003HÆ\u0003J¥\u0001\u0010@\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u00102\b\b\u0002\u0010\u0018\u001a\u00020\u0003HÆ\u0001J\u0013\u0010A\u001a\u00020\u00122\b\u0010B\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010C\u001a\u00020DHÖ\u0001J\t\u0010E\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001cR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001cR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0010¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u0010¢\u0006\b\n\u0000\u001a\u0004\b0\u0010)R\u0011\u0010\u0018\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u001c¨\u0006F"}, d2 = {"Lcom/jarvis/core/execution/ToolCatalogEntry;", "", "toolId", "", "name", "description", "capabilityId", "source", "Lcom/jarvis/core/execution/ToolProviderSource;", "locality", "Lcom/jarvis/core/execution/ToolExecutionLocality;", "availability", "Lcom/jarvis/core/execution/ToolAvailability;", "riskLevel", "Lcom/jarvis/core/common/model/RiskLevel;", "requiredPermissions", "", "hasExecutable", "", "actionKindHint", "Lcom/jarvis/core/autonomy/ActionKind;", "metadata", "Lcom/jarvis/core/execution/ToolMetadata;", "parameterNames", "contractVersion", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/core/execution/ToolProviderSource;Lcom/jarvis/core/execution/ToolExecutionLocality;Lcom/jarvis/core/execution/ToolAvailability;Lcom/jarvis/core/common/model/RiskLevel;Ljava/util/List;ZLcom/jarvis/core/autonomy/ActionKind;Lcom/jarvis/core/execution/ToolMetadata;Ljava/util/List;Ljava/lang/String;)V", "getToolId", "()Ljava/lang/String;", "getName", "getDescription", "getCapabilityId", "getSource", "()Lcom/jarvis/core/execution/ToolProviderSource;", "getLocality", "()Lcom/jarvis/core/execution/ToolExecutionLocality;", "getAvailability", "()Lcom/jarvis/core/execution/ToolAvailability;", "getRiskLevel", "()Lcom/jarvis/core/common/model/RiskLevel;", "getRequiredPermissions", "()Ljava/util/List;", "getHasExecutable", "()Z", "getActionKindHint", "()Lcom/jarvis/core/autonomy/ActionKind;", "getMetadata", "()Lcom/jarvis/core/execution/ToolMetadata;", "getParameterNames", "getContractVersion", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ToolCatalogEntry {
    private final ActionKind actionKindHint;
    private final ToolAvailability availability;
    private final String capabilityId;
    private final String contractVersion;
    private final String description;
    private final boolean hasExecutable;
    private final ToolExecutionLocality locality;
    private final ToolMetadata metadata;
    private final String name;
    private final List<String> parameterNames;
    private final List<String> requiredPermissions;
    private final RiskLevel riskLevel;
    private final ToolProviderSource source;
    private final String toolId;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getToolId() {
        return this.toolId;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final boolean getHasExecutable() {
        return this.hasExecutable;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final ActionKind getActionKindHint() {
        return this.actionKindHint;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final ToolMetadata getMetadata() {
        return this.metadata;
    }

    public final List<String> component13() {
        return this.parameterNames;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getContractVersion() {
        return this.contractVersion;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getCapabilityId() {
        return this.capabilityId;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final ToolProviderSource getSource() {
        return this.source;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final ToolExecutionLocality getLocality() {
        return this.locality;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final ToolAvailability getAvailability() {
        return this.availability;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final RiskLevel getRiskLevel() {
        return this.riskLevel;
    }

    public final List<String> component9() {
        return this.requiredPermissions;
    }

    public final ToolCatalogEntry copy(String toolId, String name, String description, String capabilityId, ToolProviderSource source, ToolExecutionLocality locality, ToolAvailability availability, RiskLevel riskLevel, List<String> requiredPermissions, boolean hasExecutable, ActionKind actionKindHint, ToolMetadata metadata, List<String> parameterNames, String contractVersion) {
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(locality, "locality");
        Intrinsics.checkNotNullParameter(availability, "availability");
        Intrinsics.checkNotNullParameter(riskLevel, "riskLevel");
        Intrinsics.checkNotNullParameter(requiredPermissions, "requiredPermissions");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        Intrinsics.checkNotNullParameter(parameterNames, "parameterNames");
        Intrinsics.checkNotNullParameter(contractVersion, "contractVersion");
        return new ToolCatalogEntry(toolId, name, description, capabilityId, source, locality, availability, riskLevel, requiredPermissions, hasExecutable, actionKindHint, metadata, parameterNames, contractVersion);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ToolCatalogEntry)) {
            return false;
        }
        ToolCatalogEntry toolCatalogEntry = (ToolCatalogEntry) other;
        return Intrinsics.areEqual(this.toolId, toolCatalogEntry.toolId) && Intrinsics.areEqual(this.name, toolCatalogEntry.name) && Intrinsics.areEqual(this.description, toolCatalogEntry.description) && Intrinsics.areEqual(this.capabilityId, toolCatalogEntry.capabilityId) && this.source == toolCatalogEntry.source && this.locality == toolCatalogEntry.locality && this.availability == toolCatalogEntry.availability && this.riskLevel == toolCatalogEntry.riskLevel && Intrinsics.areEqual(this.requiredPermissions, toolCatalogEntry.requiredPermissions) && this.hasExecutable == toolCatalogEntry.hasExecutable && this.actionKindHint == toolCatalogEntry.actionKindHint && Intrinsics.areEqual(this.metadata, toolCatalogEntry.metadata) && Intrinsics.areEqual(this.parameterNames, toolCatalogEntry.parameterNames) && Intrinsics.areEqual(this.contractVersion, toolCatalogEntry.contractVersion);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((this.toolId.hashCode() * 31) + this.name.hashCode()) * 31) + this.description.hashCode()) * 31) + (this.capabilityId == null ? 0 : this.capabilityId.hashCode())) * 31) + this.source.hashCode()) * 31) + this.locality.hashCode()) * 31) + this.availability.hashCode()) * 31) + this.riskLevel.hashCode()) * 31) + this.requiredPermissions.hashCode()) * 31) + Boolean.hashCode(this.hasExecutable)) * 31) + (this.actionKindHint != null ? this.actionKindHint.hashCode() : 0)) * 31) + this.metadata.hashCode()) * 31) + this.parameterNames.hashCode()) * 31) + this.contractVersion.hashCode();
    }

    public String toString() {
        return "ToolCatalogEntry(toolId=" + this.toolId + ", name=" + this.name + ", description=" + this.description + ", capabilityId=" + this.capabilityId + ", source=" + this.source + ", locality=" + this.locality + ", availability=" + this.availability + ", riskLevel=" + this.riskLevel + ", requiredPermissions=" + this.requiredPermissions + ", hasExecutable=" + this.hasExecutable + ", actionKindHint=" + this.actionKindHint + ", metadata=" + this.metadata + ", parameterNames=" + this.parameterNames + ", contractVersion=" + this.contractVersion + ")";
    }

    public ToolCatalogEntry(String toolId, String name, String description, String capabilityId, ToolProviderSource source, ToolExecutionLocality locality, ToolAvailability availability, RiskLevel riskLevel, List<String> requiredPermissions, boolean hasExecutable, ActionKind actionKindHint, ToolMetadata metadata, List<String> parameterNames, String contractVersion) {
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(locality, "locality");
        Intrinsics.checkNotNullParameter(availability, "availability");
        Intrinsics.checkNotNullParameter(riskLevel, "riskLevel");
        Intrinsics.checkNotNullParameter(requiredPermissions, "requiredPermissions");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        Intrinsics.checkNotNullParameter(parameterNames, "parameterNames");
        Intrinsics.checkNotNullParameter(contractVersion, "contractVersion");
        this.toolId = toolId;
        this.name = name;
        this.description = description;
        this.capabilityId = capabilityId;
        this.source = source;
        this.locality = locality;
        this.availability = availability;
        this.riskLevel = riskLevel;
        this.requiredPermissions = requiredPermissions;
        this.hasExecutable = hasExecutable;
        this.actionKindHint = actionKindHint;
        this.metadata = metadata;
        this.parameterNames = parameterNames;
        this.contractVersion = contractVersion;
    }

    public final String getToolId() {
        return this.toolId;
    }

    public final String getName() {
        return this.name;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getCapabilityId() {
        return this.capabilityId;
    }

    public final ToolProviderSource getSource() {
        return this.source;
    }

    public final ToolExecutionLocality getLocality() {
        return this.locality;
    }

    public final ToolAvailability getAvailability() {
        return this.availability;
    }

    public final RiskLevel getRiskLevel() {
        return this.riskLevel;
    }

    public final List<String> getRequiredPermissions() {
        return this.requiredPermissions;
    }

    public final boolean getHasExecutable() {
        return this.hasExecutable;
    }

    public final ActionKind getActionKindHint() {
        return this.actionKindHint;
    }

    public final ToolMetadata getMetadata() {
        return this.metadata;
    }

    public final List<String> getParameterNames() {
        return this.parameterNames;
    }

    public final String getContractVersion() {
        return this.contractVersion;
    }
}
