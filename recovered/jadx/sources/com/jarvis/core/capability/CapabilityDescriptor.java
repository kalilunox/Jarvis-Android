package com.jarvis.core.capability;

import com.jarvis.core.common.model.OnlineRequirement;
import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.common.model.ToolParameter;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CapabilityModels.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b,\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B¿\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0007\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0004\b\u0018\u0010\u0019J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\u000f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J\u000f\u00103\u001a\b\u0012\u0004\u0012\u00020\t0\u0007HÆ\u0003J\u000f\u00104\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J\u000f\u00105\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J\t\u00106\u001a\u00020\rHÆ\u0003J\t\u00107\u001a\u00020\u000fHÆ\u0003J\t\u00108\u001a\u00020\u0011HÆ\u0003J\t\u00109\u001a\u00020\u0013HÆ\u0003J\t\u0010:\u001a\u00020\u0003HÆ\u0003J\u000f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J\u000f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J\u000f\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003JÉ\u0001\u0010>\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00072\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00032\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0001J\u0013\u0010?\u001a\u00020@2\b\u0010A\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010B\u001a\u00020CHÖ\u0001J\t\u0010D\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001fR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0011\u0010\u0014\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001bR\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001fR\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001fR\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001f¨\u0006E"}, d2 = {"Lcom/jarvis/core/capability/CapabilityDescriptor;", "", "id", "", "title", "description", "operations", "", "argumentSchema", "Lcom/jarvis/core/common/model/ToolParameter;", "resultHints", "permissions", "riskLevel", "Lcom/jarvis/core/common/model/RiskLevel;", "onlineRequirement", "Lcom/jarvis/core/common/model/OnlineRequirement;", "availability", "Lcom/jarvis/core/capability/CapabilityAvailability;", "confirmationPolicy", "Lcom/jarvis/core/capability/ConfirmationPolicy;", "backingToolId", "tags", "examples", "constraints", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lcom/jarvis/core/common/model/RiskLevel;Lcom/jarvis/core/common/model/OnlineRequirement;Lcom/jarvis/core/capability/CapabilityAvailability;Lcom/jarvis/core/capability/ConfirmationPolicy;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getId", "()Ljava/lang/String;", "getTitle", "getDescription", "getOperations", "()Ljava/util/List;", "getArgumentSchema", "getResultHints", "getPermissions", "getRiskLevel", "()Lcom/jarvis/core/common/model/RiskLevel;", "getOnlineRequirement", "()Lcom/jarvis/core/common/model/OnlineRequirement;", "getAvailability", "()Lcom/jarvis/core/capability/CapabilityAvailability;", "getConfirmationPolicy", "()Lcom/jarvis/core/capability/ConfirmationPolicy;", "getBackingToolId", "getTags", "getExamples", "getConstraints", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class CapabilityDescriptor {
    private final List<ToolParameter> argumentSchema;
    private final CapabilityAvailability availability;
    private final String backingToolId;
    private final ConfirmationPolicy confirmationPolicy;
    private final List<String> constraints;
    private final String description;
    private final List<String> examples;
    private final String id;
    private final OnlineRequirement onlineRequirement;
    private final List<String> operations;
    private final List<String> permissions;
    private final List<String> resultHints;
    private final RiskLevel riskLevel;
    private final List<String> tags;
    private final String title;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final CapabilityAvailability getAvailability() {
        return this.availability;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final ConfirmationPolicy getConfirmationPolicy() {
        return this.confirmationPolicy;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getBackingToolId() {
        return this.backingToolId;
    }

    public final List<String> component13() {
        return this.tags;
    }

    public final List<String> component14() {
        return this.examples;
    }

    public final List<String> component15() {
        return this.constraints;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    public final List<String> component4() {
        return this.operations;
    }

    public final List<ToolParameter> component5() {
        return this.argumentSchema;
    }

    public final List<String> component6() {
        return this.resultHints;
    }

    public final List<String> component7() {
        return this.permissions;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final RiskLevel getRiskLevel() {
        return this.riskLevel;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final OnlineRequirement getOnlineRequirement() {
        return this.onlineRequirement;
    }

    public final CapabilityDescriptor copy(String id, String title, String description, List<String> operations, List<ToolParameter> argumentSchema, List<String> resultHints, List<String> permissions, RiskLevel riskLevel, OnlineRequirement onlineRequirement, CapabilityAvailability availability, ConfirmationPolicy confirmationPolicy, String backingToolId, List<String> tags, List<String> examples, List<String> constraints) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(operations, "operations");
        Intrinsics.checkNotNullParameter(argumentSchema, "argumentSchema");
        Intrinsics.checkNotNullParameter(resultHints, "resultHints");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(riskLevel, "riskLevel");
        Intrinsics.checkNotNullParameter(onlineRequirement, "onlineRequirement");
        Intrinsics.checkNotNullParameter(availability, "availability");
        Intrinsics.checkNotNullParameter(confirmationPolicy, "confirmationPolicy");
        Intrinsics.checkNotNullParameter(backingToolId, "backingToolId");
        Intrinsics.checkNotNullParameter(tags, "tags");
        Intrinsics.checkNotNullParameter(examples, "examples");
        Intrinsics.checkNotNullParameter(constraints, "constraints");
        return new CapabilityDescriptor(id, title, description, operations, argumentSchema, resultHints, permissions, riskLevel, onlineRequirement, availability, confirmationPolicy, backingToolId, tags, examples, constraints);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CapabilityDescriptor)) {
            return false;
        }
        CapabilityDescriptor capabilityDescriptor = (CapabilityDescriptor) other;
        return Intrinsics.areEqual(this.id, capabilityDescriptor.id) && Intrinsics.areEqual(this.title, capabilityDescriptor.title) && Intrinsics.areEqual(this.description, capabilityDescriptor.description) && Intrinsics.areEqual(this.operations, capabilityDescriptor.operations) && Intrinsics.areEqual(this.argumentSchema, capabilityDescriptor.argumentSchema) && Intrinsics.areEqual(this.resultHints, capabilityDescriptor.resultHints) && Intrinsics.areEqual(this.permissions, capabilityDescriptor.permissions) && this.riskLevel == capabilityDescriptor.riskLevel && this.onlineRequirement == capabilityDescriptor.onlineRequirement && this.availability == capabilityDescriptor.availability && this.confirmationPolicy == capabilityDescriptor.confirmationPolicy && Intrinsics.areEqual(this.backingToolId, capabilityDescriptor.backingToolId) && Intrinsics.areEqual(this.tags, capabilityDescriptor.tags) && Intrinsics.areEqual(this.examples, capabilityDescriptor.examples) && Intrinsics.areEqual(this.constraints, capabilityDescriptor.constraints);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((this.id.hashCode() * 31) + this.title.hashCode()) * 31) + this.description.hashCode()) * 31) + this.operations.hashCode()) * 31) + this.argumentSchema.hashCode()) * 31) + this.resultHints.hashCode()) * 31) + this.permissions.hashCode()) * 31) + this.riskLevel.hashCode()) * 31) + this.onlineRequirement.hashCode()) * 31) + this.availability.hashCode()) * 31) + this.confirmationPolicy.hashCode()) * 31) + this.backingToolId.hashCode()) * 31) + this.tags.hashCode()) * 31) + this.examples.hashCode()) * 31) + this.constraints.hashCode();
    }

    public String toString() {
        return "CapabilityDescriptor(id=" + this.id + ", title=" + this.title + ", description=" + this.description + ", operations=" + this.operations + ", argumentSchema=" + this.argumentSchema + ", resultHints=" + this.resultHints + ", permissions=" + this.permissions + ", riskLevel=" + this.riskLevel + ", onlineRequirement=" + this.onlineRequirement + ", availability=" + this.availability + ", confirmationPolicy=" + this.confirmationPolicy + ", backingToolId=" + this.backingToolId + ", tags=" + this.tags + ", examples=" + this.examples + ", constraints=" + this.constraints + ")";
    }

    public CapabilityDescriptor(String id, String title, String description, List<String> operations, List<ToolParameter> argumentSchema, List<String> resultHints, List<String> permissions, RiskLevel riskLevel, OnlineRequirement onlineRequirement, CapabilityAvailability availability, ConfirmationPolicy confirmationPolicy, String backingToolId, List<String> tags, List<String> examples, List<String> constraints) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(operations, "operations");
        Intrinsics.checkNotNullParameter(argumentSchema, "argumentSchema");
        Intrinsics.checkNotNullParameter(resultHints, "resultHints");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(riskLevel, "riskLevel");
        Intrinsics.checkNotNullParameter(onlineRequirement, "onlineRequirement");
        Intrinsics.checkNotNullParameter(availability, "availability");
        Intrinsics.checkNotNullParameter(confirmationPolicy, "confirmationPolicy");
        Intrinsics.checkNotNullParameter(backingToolId, "backingToolId");
        Intrinsics.checkNotNullParameter(tags, "tags");
        Intrinsics.checkNotNullParameter(examples, "examples");
        Intrinsics.checkNotNullParameter(constraints, "constraints");
        this.id = id;
        this.title = title;
        this.description = description;
        this.operations = operations;
        this.argumentSchema = argumentSchema;
        this.resultHints = resultHints;
        this.permissions = permissions;
        this.riskLevel = riskLevel;
        this.onlineRequirement = onlineRequirement;
        this.availability = availability;
        this.confirmationPolicy = confirmationPolicy;
        this.backingToolId = backingToolId;
        this.tags = tags;
        this.examples = examples;
        this.constraints = constraints;
    }

    public /* synthetic */ CapabilityDescriptor(String str, String str2, String str3, List list, List list2, List list3, List list4, RiskLevel riskLevel, OnlineRequirement onlineRequirement, CapabilityAvailability capabilityAvailability, ConfirmationPolicy confirmationPolicy, String str4, List list5, List list6, List list7, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? CollectionsKt.emptyList() : list, (i & 16) != 0 ? CollectionsKt.emptyList() : list2, (i & 32) != 0 ? CollectionsKt.emptyList() : list3, (i & 64) != 0 ? CollectionsKt.emptyList() : list4, (i & 128) != 0 ? RiskLevel.SAFE : riskLevel, (i & 256) != 0 ? OnlineRequirement.LOCAL_ONLY : onlineRequirement, (i & 512) != 0 ? CapabilityAvailability.ALWAYS : capabilityAvailability, (i & 1024) != 0 ? ConfirmationPolicy.AUTO : confirmationPolicy, str4, (i & 4096) != 0 ? CollectionsKt.emptyList() : list5, (i & 8192) != 0 ? CollectionsKt.emptyList() : list6, (i & 16384) != 0 ? CollectionsKt.emptyList() : list7);
    }

    public final String getId() {
        return this.id;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getDescription() {
        return this.description;
    }

    public final List<String> getOperations() {
        return this.operations;
    }

    public final List<ToolParameter> getArgumentSchema() {
        return this.argumentSchema;
    }

    public final List<String> getResultHints() {
        return this.resultHints;
    }

    public final List<String> getPermissions() {
        return this.permissions;
    }

    public final RiskLevel getRiskLevel() {
        return this.riskLevel;
    }

    public final OnlineRequirement getOnlineRequirement() {
        return this.onlineRequirement;
    }

    public final CapabilityAvailability getAvailability() {
        return this.availability;
    }

    public final ConfirmationPolicy getConfirmationPolicy() {
        return this.confirmationPolicy;
    }

    public final String getBackingToolId() {
        return this.backingToolId;
    }

    public final List<String> getTags() {
        return this.tags;
    }

    public final List<String> getExamples() {
        return this.examples;
    }

    public final List<String> getConstraints() {
        return this.constraints;
    }
}
