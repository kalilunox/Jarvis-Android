package com.jarvis.core.routines;

import com.jarvis.core.planner.ActionPlan;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RoutineModels.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001b\u001a\u00020\bHÆ\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00030\nHÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003JK\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\b\b\u0002\u0010\u000b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001f\u001a\u00020\b2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000f¨\u0006$"}, d2 = {"Lcom/jarvis/core/routines/RoutineDraft;", "", "draftId", "", "naturalLanguage", "plan", "Lcom/jarvis/core/planner/ActionPlan;", "validationOk", "", "validationErrors", "", "previewSummary", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/core/planner/ActionPlan;ZLjava/util/List;Ljava/lang/String;)V", "getDraftId", "()Ljava/lang/String;", "getNaturalLanguage", "getPlan", "()Lcom/jarvis/core/planner/ActionPlan;", "getValidationOk", "()Z", "getValidationErrors", "()Ljava/util/List;", "getPreviewSummary", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class RoutineDraft {
    private final String draftId;
    private final String naturalLanguage;
    private final ActionPlan plan;
    private final String previewSummary;
    private final List<String> validationErrors;
    private final boolean validationOk;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ RoutineDraft copy$default(RoutineDraft routineDraft, String str, String str2, ActionPlan actionPlan, boolean z, List list, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = routineDraft.draftId;
        }
        if ((i & 2) != 0) {
            str2 = routineDraft.naturalLanguage;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            actionPlan = routineDraft.plan;
        }
        ActionPlan actionPlan2 = actionPlan;
        if ((i & 8) != 0) {
            z = routineDraft.validationOk;
        }
        boolean z2 = z;
        if ((i & 16) != 0) {
            list = routineDraft.validationErrors;
        }
        List list2 = list;
        if ((i & 32) != 0) {
            str3 = routineDraft.previewSummary;
        }
        return routineDraft.copy(str, str4, actionPlan2, z2, list2, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getDraftId() {
        return this.draftId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getNaturalLanguage() {
        return this.naturalLanguage;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ActionPlan getPlan() {
        return this.plan;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getValidationOk() {
        return this.validationOk;
    }

    public final List<String> component5() {
        return this.validationErrors;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getPreviewSummary() {
        return this.previewSummary;
    }

    public final RoutineDraft copy(String draftId, String naturalLanguage, ActionPlan plan, boolean validationOk, List<String> validationErrors, String previewSummary) {
        Intrinsics.checkNotNullParameter(draftId, "draftId");
        Intrinsics.checkNotNullParameter(naturalLanguage, "naturalLanguage");
        Intrinsics.checkNotNullParameter(plan, "plan");
        Intrinsics.checkNotNullParameter(validationErrors, "validationErrors");
        Intrinsics.checkNotNullParameter(previewSummary, "previewSummary");
        return new RoutineDraft(draftId, naturalLanguage, plan, validationOk, validationErrors, previewSummary);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RoutineDraft)) {
            return false;
        }
        RoutineDraft routineDraft = (RoutineDraft) other;
        return Intrinsics.areEqual(this.draftId, routineDraft.draftId) && Intrinsics.areEqual(this.naturalLanguage, routineDraft.naturalLanguage) && Intrinsics.areEqual(this.plan, routineDraft.plan) && this.validationOk == routineDraft.validationOk && Intrinsics.areEqual(this.validationErrors, routineDraft.validationErrors) && Intrinsics.areEqual(this.previewSummary, routineDraft.previewSummary);
    }

    public int hashCode() {
        return (((((((((this.draftId.hashCode() * 31) + this.naturalLanguage.hashCode()) * 31) + this.plan.hashCode()) * 31) + Boolean.hashCode(this.validationOk)) * 31) + this.validationErrors.hashCode()) * 31) + this.previewSummary.hashCode();
    }

    public String toString() {
        return "RoutineDraft(draftId=" + this.draftId + ", naturalLanguage=" + this.naturalLanguage + ", plan=" + this.plan + ", validationOk=" + this.validationOk + ", validationErrors=" + this.validationErrors + ", previewSummary=" + this.previewSummary + ")";
    }

    public RoutineDraft(String draftId, String naturalLanguage, ActionPlan plan, boolean validationOk, List<String> validationErrors, String previewSummary) {
        Intrinsics.checkNotNullParameter(draftId, "draftId");
        Intrinsics.checkNotNullParameter(naturalLanguage, "naturalLanguage");
        Intrinsics.checkNotNullParameter(plan, "plan");
        Intrinsics.checkNotNullParameter(validationErrors, "validationErrors");
        Intrinsics.checkNotNullParameter(previewSummary, "previewSummary");
        this.draftId = draftId;
        this.naturalLanguage = naturalLanguage;
        this.plan = plan;
        this.validationOk = validationOk;
        this.validationErrors = validationErrors;
        this.previewSummary = previewSummary;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ RoutineDraft(String str, String str2, ActionPlan actionPlan, boolean z, List list, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        List listEmptyList;
        if ((i & 16) == 0) {
            listEmptyList = list;
        } else {
            listEmptyList = CollectionsKt.emptyList();
        }
        this(str, str2, actionPlan, z, listEmptyList, str3);
    }

    public final String getDraftId() {
        return this.draftId;
    }

    public final String getNaturalLanguage() {
        return this.naturalLanguage;
    }

    public final ActionPlan getPlan() {
        return this.plan;
    }

    public final boolean getValidationOk() {
        return this.validationOk;
    }

    public final List<String> getValidationErrors() {
        return this.validationErrors;
    }

    public final String getPreviewSummary() {
        return this.previewSummary;
    }
}
