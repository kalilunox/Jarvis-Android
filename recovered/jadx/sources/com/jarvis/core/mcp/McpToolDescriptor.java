package com.jarvis.core.mcp;

import com.jarvis.core.common.model.RiskLevel;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: McpContracts.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\u0004\b\n\u0010\u000bJ\u000e\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0003JG\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010¨\u0006\""}, d2 = {"Lcom/jarvis/core/mcp/McpToolDescriptor;", "", "name", "", "description", "inputParameterNames", "", "riskLevel", "Lcom/jarvis/core/common/model/RiskLevel;", "requiredPermissions", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/jarvis/core/common/model/RiskLevel;Ljava/util/List;)V", "getName", "()Ljava/lang/String;", "getDescription", "getInputParameterNames", "()Ljava/util/List;", "getRiskLevel", "()Lcom/jarvis/core/common/model/RiskLevel;", "getRequiredPermissions", "toolId", "serverId", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class McpToolDescriptor {
    private final String description;
    private final List<String> inputParameterNames;
    private final String name;
    private final List<String> requiredPermissions;
    private final RiskLevel riskLevel;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ McpToolDescriptor copy$default(McpToolDescriptor mcpToolDescriptor, String str, String str2, List list, RiskLevel riskLevel, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = mcpToolDescriptor.name;
        }
        if ((i & 2) != 0) {
            str2 = mcpToolDescriptor.description;
        }
        String str3 = str2;
        if ((i & 4) != 0) {
            list = mcpToolDescriptor.inputParameterNames;
        }
        List list3 = list;
        if ((i & 8) != 0) {
            riskLevel = mcpToolDescriptor.riskLevel;
        }
        RiskLevel riskLevel2 = riskLevel;
        if ((i & 16) != 0) {
            list2 = mcpToolDescriptor.requiredPermissions;
        }
        return mcpToolDescriptor.copy(str, str3, list3, riskLevel2, list2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    public final List<String> component3() {
        return this.inputParameterNames;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final RiskLevel getRiskLevel() {
        return this.riskLevel;
    }

    public final List<String> component5() {
        return this.requiredPermissions;
    }

    public final McpToolDescriptor copy(String name, String description, List<String> inputParameterNames, RiskLevel riskLevel, List<String> requiredPermissions) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(inputParameterNames, "inputParameterNames");
        Intrinsics.checkNotNullParameter(riskLevel, "riskLevel");
        Intrinsics.checkNotNullParameter(requiredPermissions, "requiredPermissions");
        return new McpToolDescriptor(name, description, inputParameterNames, riskLevel, requiredPermissions);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof McpToolDescriptor)) {
            return false;
        }
        McpToolDescriptor mcpToolDescriptor = (McpToolDescriptor) other;
        return Intrinsics.areEqual(this.name, mcpToolDescriptor.name) && Intrinsics.areEqual(this.description, mcpToolDescriptor.description) && Intrinsics.areEqual(this.inputParameterNames, mcpToolDescriptor.inputParameterNames) && this.riskLevel == mcpToolDescriptor.riskLevel && Intrinsics.areEqual(this.requiredPermissions, mcpToolDescriptor.requiredPermissions);
    }

    public int hashCode() {
        return (((((((this.name.hashCode() * 31) + this.description.hashCode()) * 31) + this.inputParameterNames.hashCode()) * 31) + this.riskLevel.hashCode()) * 31) + this.requiredPermissions.hashCode();
    }

    public String toString() {
        return "McpToolDescriptor(name=" + this.name + ", description=" + this.description + ", inputParameterNames=" + this.inputParameterNames + ", riskLevel=" + this.riskLevel + ", requiredPermissions=" + this.requiredPermissions + ")";
    }

    public McpToolDescriptor(String name, String description, List<String> inputParameterNames, RiskLevel riskLevel, List<String> requiredPermissions) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(inputParameterNames, "inputParameterNames");
        Intrinsics.checkNotNullParameter(riskLevel, "riskLevel");
        Intrinsics.checkNotNullParameter(requiredPermissions, "requiredPermissions");
        this.name = name;
        this.description = description;
        this.inputParameterNames = inputParameterNames;
        this.riskLevel = riskLevel;
        this.requiredPermissions = requiredPermissions;
        if (StringsKt.isBlank(this.name)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(this.name.length() <= 64)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(this.description.length() <= 500)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (this.inputParameterNames.size() <= 16) {
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public /* synthetic */ McpToolDescriptor(String str, String str2, List list, RiskLevel riskLevel, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? CollectionsKt.emptyList() : list, (i & 8) != 0 ? RiskLevel.CONFIRM : riskLevel, (i & 16) != 0 ? CollectionsKt.emptyList() : list2);
    }

    public final String getName() {
        return this.name;
    }

    public final String getDescription() {
        return this.description;
    }

    public final List<String> getInputParameterNames() {
        return this.inputParameterNames;
    }

    public final RiskLevel getRiskLevel() {
        return this.riskLevel;
    }

    public final List<String> getRequiredPermissions() {
        return this.requiredPermissions;
    }

    public final String toolId(String serverId) {
        Intrinsics.checkNotNullParameter(serverId, "serverId");
        return "mcp." + serverId + "." + this.name;
    }
}
