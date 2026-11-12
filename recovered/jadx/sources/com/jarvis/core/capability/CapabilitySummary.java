package com.jarvis.core.capability;

import com.jarvis.core.common.model.RiskLevel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CapabilityModels.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0003¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J\t\u0010\u001c\u001a\u00020\tHÆ\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J[\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\t\u0010%\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000f¨\u0006&"}, d2 = {"Lcom/jarvis/core/capability/CapabilitySummary;", "", "id", "", "title", "description", "operations", "", "riskLevel", "Lcom/jarvis/core/common/model/RiskLevel;", "examples", "backingToolId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/jarvis/core/common/model/RiskLevel;Ljava/util/List;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getTitle", "getDescription", "getOperations", "()Ljava/util/List;", "getRiskLevel", "()Lcom/jarvis/core/common/model/RiskLevel;", "getExamples", "getBackingToolId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class CapabilitySummary {
    private final String backingToolId;
    private final String description;
    private final List<String> examples;
    private final String id;
    private final List<String> operations;
    private final RiskLevel riskLevel;
    private final String title;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CapabilitySummary copy$default(CapabilitySummary capabilitySummary, String str, String str2, String str3, List list, RiskLevel riskLevel, List list2, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = capabilitySummary.id;
        }
        if ((i & 2) != 0) {
            str2 = capabilitySummary.title;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = capabilitySummary.description;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            list = capabilitySummary.operations;
        }
        List list3 = list;
        if ((i & 16) != 0) {
            riskLevel = capabilitySummary.riskLevel;
        }
        RiskLevel riskLevel2 = riskLevel;
        if ((i & 32) != 0) {
            list2 = capabilitySummary.examples;
        }
        List list4 = list2;
        if ((i & 64) != 0) {
            str4 = capabilitySummary.backingToolId;
        }
        return capabilitySummary.copy(str, str5, str6, list3, riskLevel2, list4, str4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
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

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final RiskLevel getRiskLevel() {
        return this.riskLevel;
    }

    public final List<String> component6() {
        return this.examples;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getBackingToolId() {
        return this.backingToolId;
    }

    public final CapabilitySummary copy(String id, String title, String description, List<String> operations, RiskLevel riskLevel, List<String> examples, String backingToolId) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(operations, "operations");
        Intrinsics.checkNotNullParameter(riskLevel, "riskLevel");
        Intrinsics.checkNotNullParameter(examples, "examples");
        Intrinsics.checkNotNullParameter(backingToolId, "backingToolId");
        return new CapabilitySummary(id, title, description, operations, riskLevel, examples, backingToolId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CapabilitySummary)) {
            return false;
        }
        CapabilitySummary capabilitySummary = (CapabilitySummary) other;
        return Intrinsics.areEqual(this.id, capabilitySummary.id) && Intrinsics.areEqual(this.title, capabilitySummary.title) && Intrinsics.areEqual(this.description, capabilitySummary.description) && Intrinsics.areEqual(this.operations, capabilitySummary.operations) && this.riskLevel == capabilitySummary.riskLevel && Intrinsics.areEqual(this.examples, capabilitySummary.examples) && Intrinsics.areEqual(this.backingToolId, capabilitySummary.backingToolId);
    }

    public int hashCode() {
        return (((((((((((this.id.hashCode() * 31) + this.title.hashCode()) * 31) + this.description.hashCode()) * 31) + this.operations.hashCode()) * 31) + this.riskLevel.hashCode()) * 31) + this.examples.hashCode()) * 31) + this.backingToolId.hashCode();
    }

    public String toString() {
        return "CapabilitySummary(id=" + this.id + ", title=" + this.title + ", description=" + this.description + ", operations=" + this.operations + ", riskLevel=" + this.riskLevel + ", examples=" + this.examples + ", backingToolId=" + this.backingToolId + ")";
    }

    public CapabilitySummary(String id, String title, String description, List<String> operations, RiskLevel riskLevel, List<String> examples, String backingToolId) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(operations, "operations");
        Intrinsics.checkNotNullParameter(riskLevel, "riskLevel");
        Intrinsics.checkNotNullParameter(examples, "examples");
        Intrinsics.checkNotNullParameter(backingToolId, "backingToolId");
        this.id = id;
        this.title = title;
        this.description = description;
        this.operations = operations;
        this.riskLevel = riskLevel;
        this.examples = examples;
        this.backingToolId = backingToolId;
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

    public final RiskLevel getRiskLevel() {
        return this.riskLevel;
    }

    public final List<String> getExamples() {
        return this.examples;
    }

    public final String getBackingToolId() {
        return this.backingToolId;
    }
}
