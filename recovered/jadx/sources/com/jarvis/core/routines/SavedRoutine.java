package com.jarvis.core.routines;

import com.jarvis.core.planner.ActionPlan;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: RoutineModels.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B_\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0007HÆ\u0003J\t\u0010&\u001a\u00020\tHÆ\u0003J\t\u0010'\u001a\u00020\u000bHÆ\u0003J\u0010\u0010(\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010\u001dJ\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00030\u000fHÆ\u0003Jr\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u000fHÆ\u0001¢\u0006\u0002\u0010,J\u0013\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00100\u001a\u000201HÖ\u0001J\t\u00102\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0015\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0013R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u000f¢\u0006\b\n\u0000\u001a\u0004\b \u0010!¨\u00063"}, d2 = {"Lcom/jarvis/core/routines/SavedRoutine;", "", "id", "", "title", "naturalLanguage", "plan", "Lcom/jarvis/core/planner/ActionPlan;", "status", "Lcom/jarvis/core/routines/RoutineStatus;", "createdAtMillis", "", "approvedAtMillis", "sourceBehaviorKey", "notes", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/core/planner/ActionPlan;Lcom/jarvis/core/routines/RoutineStatus;JLjava/lang/Long;Ljava/lang/String;Ljava/util/List;)V", "getId", "()Ljava/lang/String;", "getTitle", "getNaturalLanguage", "getPlan", "()Lcom/jarvis/core/planner/ActionPlan;", "getStatus", "()Lcom/jarvis/core/routines/RoutineStatus;", "getCreatedAtMillis", "()J", "getApprovedAtMillis", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getSourceBehaviorKey", "getNotes", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/core/planner/ActionPlan;Lcom/jarvis/core/routines/RoutineStatus;JLjava/lang/Long;Ljava/lang/String;Ljava/util/List;)Lcom/jarvis/core/routines/SavedRoutine;", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class SavedRoutine {
    private final Long approvedAtMillis;
    private final long createdAtMillis;
    private final String id;
    private final String naturalLanguage;
    private final List<String> notes;
    private final ActionPlan plan;
    private final String sourceBehaviorKey;
    private final RoutineStatus status;
    private final String title;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getNaturalLanguage() {
        return this.naturalLanguage;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final ActionPlan getPlan() {
        return this.plan;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final RoutineStatus getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getCreatedAtMillis() {
        return this.createdAtMillis;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Long getApprovedAtMillis() {
        return this.approvedAtMillis;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getSourceBehaviorKey() {
        return this.sourceBehaviorKey;
    }

    public final List<String> component9() {
        return this.notes;
    }

    public final SavedRoutine copy(String id, String title, String naturalLanguage, ActionPlan plan, RoutineStatus status, long createdAtMillis, Long approvedAtMillis, String sourceBehaviorKey, List<String> notes) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(naturalLanguage, "naturalLanguage");
        Intrinsics.checkNotNullParameter(plan, "plan");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(notes, "notes");
        return new SavedRoutine(id, title, naturalLanguage, plan, status, createdAtMillis, approvedAtMillis, sourceBehaviorKey, notes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SavedRoutine)) {
            return false;
        }
        SavedRoutine savedRoutine = (SavedRoutine) other;
        return Intrinsics.areEqual(this.id, savedRoutine.id) && Intrinsics.areEqual(this.title, savedRoutine.title) && Intrinsics.areEqual(this.naturalLanguage, savedRoutine.naturalLanguage) && Intrinsics.areEqual(this.plan, savedRoutine.plan) && this.status == savedRoutine.status && this.createdAtMillis == savedRoutine.createdAtMillis && Intrinsics.areEqual(this.approvedAtMillis, savedRoutine.approvedAtMillis) && Intrinsics.areEqual(this.sourceBehaviorKey, savedRoutine.sourceBehaviorKey) && Intrinsics.areEqual(this.notes, savedRoutine.notes);
    }

    public int hashCode() {
        return (((((((((((((((this.id.hashCode() * 31) + this.title.hashCode()) * 31) + this.naturalLanguage.hashCode()) * 31) + this.plan.hashCode()) * 31) + this.status.hashCode()) * 31) + Long.hashCode(this.createdAtMillis)) * 31) + (this.approvedAtMillis == null ? 0 : this.approvedAtMillis.hashCode())) * 31) + (this.sourceBehaviorKey != null ? this.sourceBehaviorKey.hashCode() : 0)) * 31) + this.notes.hashCode();
    }

    public String toString() {
        return "SavedRoutine(id=" + this.id + ", title=" + this.title + ", naturalLanguage=" + this.naturalLanguage + ", plan=" + this.plan + ", status=" + this.status + ", createdAtMillis=" + this.createdAtMillis + ", approvedAtMillis=" + this.approvedAtMillis + ", sourceBehaviorKey=" + this.sourceBehaviorKey + ", notes=" + this.notes + ")";
    }

    public SavedRoutine(String id, String title, String naturalLanguage, ActionPlan plan, RoutineStatus status, long createdAtMillis, Long approvedAtMillis, String sourceBehaviorKey, List<String> notes) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(naturalLanguage, "naturalLanguage");
        Intrinsics.checkNotNullParameter(plan, "plan");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(notes, "notes");
        this.id = id;
        this.title = title;
        this.naturalLanguage = naturalLanguage;
        this.plan = plan;
        this.status = status;
        this.createdAtMillis = createdAtMillis;
        this.approvedAtMillis = approvedAtMillis;
        this.sourceBehaviorKey = sourceBehaviorKey;
        this.notes = notes;
        if (StringsKt.isBlank(this.id)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(!StringsKt.isBlank(this.title) && this.title.length() <= 120)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (this.naturalLanguage.length() <= 2000) {
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public /* synthetic */ SavedRoutine(String str, String str2, String str3, ActionPlan actionPlan, RoutineStatus routineStatus, long j, Long l, String str4, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, actionPlan, routineStatus, j, (i & 64) != 0 ? null : l, (i & 128) != 0 ? null : str4, (i & 256) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final String getId() {
        return this.id;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getNaturalLanguage() {
        return this.naturalLanguage;
    }

    public final ActionPlan getPlan() {
        return this.plan;
    }

    public final RoutineStatus getStatus() {
        return this.status;
    }

    public final long getCreatedAtMillis() {
        return this.createdAtMillis;
    }

    public final Long getApprovedAtMillis() {
        return this.approvedAtMillis;
    }

    public final String getSourceBehaviorKey() {
        return this.sourceBehaviorKey;
    }

    public final List<String> getNotes() {
        return this.notes;
    }
}
