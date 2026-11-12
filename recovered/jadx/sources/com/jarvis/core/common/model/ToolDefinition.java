package com.jarvis.core.common.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ToolModels.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 ,2\u00020\u0001:\u0001,B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\t\u0010!\u001a\u00020\nHÆ\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J\t\u0010#\u001a\u00020\rHÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003Je\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u0003HÆ\u0001J\u0013\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020*HÖ\u0001J\t\u0010+\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0012¨\u0006-"}, d2 = {"Lcom/jarvis/core/common/model/ToolDefinition;", "", "id", "", "name", "description", "parameters", "", "Lcom/jarvis/core/common/model/ToolParameter;", "riskLevel", "Lcom/jarvis/core/common/model/RiskLevel;", "requiredPermissions", "onlineRequirement", "Lcom/jarvis/core/common/model/OnlineRequirement;", "contractVersion", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/jarvis/core/common/model/RiskLevel;Ljava/util/List;Lcom/jarvis/core/common/model/OnlineRequirement;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getName", "getDescription", "getParameters", "()Ljava/util/List;", "getRiskLevel", "()Lcom/jarvis/core/common/model/RiskLevel;", "getRequiredPermissions", "getOnlineRequirement", "()Lcom/jarvis/core/common/model/OnlineRequirement;", "getContractVersion", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ToolDefinition {
    public static final String CONTRACT_VERSION_8E = "8E.1";
    private final String contractVersion;
    private final String description;
    private final String id;
    private final String name;
    private final OnlineRequirement onlineRequirement;
    private final List<ToolParameter> parameters;
    private final List<String> requiredPermissions;
    private final RiskLevel riskLevel;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    public final List<ToolParameter> component4() {
        return this.parameters;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final RiskLevel getRiskLevel() {
        return this.riskLevel;
    }

    public final List<String> component6() {
        return this.requiredPermissions;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final OnlineRequirement getOnlineRequirement() {
        return this.onlineRequirement;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getContractVersion() {
        return this.contractVersion;
    }

    public final ToolDefinition copy(String id, String name, String description, List<ToolParameter> parameters, RiskLevel riskLevel, List<String> requiredPermissions, OnlineRequirement onlineRequirement, String contractVersion) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        Intrinsics.checkNotNullParameter(riskLevel, "riskLevel");
        Intrinsics.checkNotNullParameter(requiredPermissions, "requiredPermissions");
        Intrinsics.checkNotNullParameter(onlineRequirement, "onlineRequirement");
        Intrinsics.checkNotNullParameter(contractVersion, "contractVersion");
        return new ToolDefinition(id, name, description, parameters, riskLevel, requiredPermissions, onlineRequirement, contractVersion);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ToolDefinition)) {
            return false;
        }
        ToolDefinition toolDefinition = (ToolDefinition) other;
        return Intrinsics.areEqual(this.id, toolDefinition.id) && Intrinsics.areEqual(this.name, toolDefinition.name) && Intrinsics.areEqual(this.description, toolDefinition.description) && Intrinsics.areEqual(this.parameters, toolDefinition.parameters) && this.riskLevel == toolDefinition.riskLevel && Intrinsics.areEqual(this.requiredPermissions, toolDefinition.requiredPermissions) && this.onlineRequirement == toolDefinition.onlineRequirement && Intrinsics.areEqual(this.contractVersion, toolDefinition.contractVersion);
    }

    public int hashCode() {
        return (((((((((((((this.id.hashCode() * 31) + this.name.hashCode()) * 31) + this.description.hashCode()) * 31) + this.parameters.hashCode()) * 31) + this.riskLevel.hashCode()) * 31) + this.requiredPermissions.hashCode()) * 31) + this.onlineRequirement.hashCode()) * 31) + this.contractVersion.hashCode();
    }

    public String toString() {
        return "ToolDefinition(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", parameters=" + this.parameters + ", riskLevel=" + this.riskLevel + ", requiredPermissions=" + this.requiredPermissions + ", onlineRequirement=" + this.onlineRequirement + ", contractVersion=" + this.contractVersion + ")";
    }

    public ToolDefinition(String id, String name, String description, List<ToolParameter> parameters, RiskLevel riskLevel, List<String> requiredPermissions, OnlineRequirement onlineRequirement, String contractVersion) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        Intrinsics.checkNotNullParameter(riskLevel, "riskLevel");
        Intrinsics.checkNotNullParameter(requiredPermissions, "requiredPermissions");
        Intrinsics.checkNotNullParameter(onlineRequirement, "onlineRequirement");
        Intrinsics.checkNotNullParameter(contractVersion, "contractVersion");
        this.id = id;
        this.name = name;
        this.description = description;
        this.parameters = parameters;
        this.riskLevel = riskLevel;
        this.requiredPermissions = requiredPermissions;
        this.onlineRequirement = onlineRequirement;
        this.contractVersion = contractVersion;
        if (StringsKt.isBlank(this.id)) {
            throw new IllegalArgumentException("ToolDefinition.id blank".toString());
        }
        if (StringsKt.isBlank(this.name)) {
            throw new IllegalArgumentException("ToolDefinition.name blank".toString());
        }
        if (StringsKt.isBlank(this.contractVersion)) {
            throw new IllegalArgumentException("ToolDefinition.contractVersion blank".toString());
        }
    }

    public /* synthetic */ ToolDefinition(String str, String str2, String str3, List list, RiskLevel riskLevel, List list2, OnlineRequirement onlineRequirement, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? CollectionsKt.emptyList() : list, (i & 16) != 0 ? RiskLevel.SAFE : riskLevel, (i & 32) != 0 ? CollectionsKt.emptyList() : list2, (i & 64) != 0 ? OnlineRequirement.LOCAL_ONLY : onlineRequirement, (i & 128) != 0 ? CONTRACT_VERSION_8E : str4);
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getDescription() {
        return this.description;
    }

    public final List<ToolParameter> getParameters() {
        return this.parameters;
    }

    public final RiskLevel getRiskLevel() {
        return this.riskLevel;
    }

    public final List<String> getRequiredPermissions() {
        return this.requiredPermissions;
    }

    public final OnlineRequirement getOnlineRequirement() {
        return this.onlineRequirement;
    }

    public final String getContractVersion() {
        return this.contractVersion;
    }
}
