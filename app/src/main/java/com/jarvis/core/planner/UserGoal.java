package com.jarvis.core.planner;

import com.jarvis.app.VoiceService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ActionPlanModels.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lcom/jarvis/core/planner/UserGoal;", "", "goalId", "", VoiceService.EXTRA_TEXT, "createdAtMillis", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;J)V", "getGoalId", "()Ljava/lang/String;", "getText", "getCreatedAtMillis", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class UserGoal {
    public static final int MAX_TEXT = 2000;
    private final long createdAtMillis;
    private final String goalId;
    private final String text;

    public static /* synthetic */ UserGoal copy$default(UserGoal userGoal, String str, String str2, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = userGoal.goalId;
        }
        if ((i & 2) != 0) {
            str2 = userGoal.text;
        }
        if ((i & 4) != 0) {
            j = userGoal.createdAtMillis;
        }
        return userGoal.copy(str, str2, j);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getGoalId() {
        return this.goalId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getCreatedAtMillis() {
        return this.createdAtMillis;
    }

    public final UserGoal copy(String goalId, String text, long createdAtMillis) {
        Intrinsics.checkNotNullParameter(goalId, "goalId");
        Intrinsics.checkNotNullParameter(text, "text");
        return new UserGoal(goalId, text, createdAtMillis);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserGoal)) {
            return false;
        }
        UserGoal userGoal = (UserGoal) other;
        return Intrinsics.areEqual(this.goalId, userGoal.goalId) && Intrinsics.areEqual(this.text, userGoal.text) && this.createdAtMillis == userGoal.createdAtMillis;
    }

    public int hashCode() {
        return (((this.goalId.hashCode() * 31) + this.text.hashCode()) * 31) + Long.hashCode(this.createdAtMillis);
    }

    public String toString() {
        return "UserGoal(goalId=" + this.goalId + ", text=" + this.text + ", createdAtMillis=" + this.createdAtMillis + ")";
    }

    public UserGoal(String goalId, String text, long createdAtMillis) {
        Intrinsics.checkNotNullParameter(goalId, "goalId");
        Intrinsics.checkNotNullParameter(text, "text");
        this.goalId = goalId;
        this.text = text;
        this.createdAtMillis = createdAtMillis;
        if (StringsKt.isBlank(this.goalId)) {
            throw new IllegalArgumentException("goalId blank".toString());
        }
        if (!(this.text.length() <= 2000)) {
            throw new IllegalArgumentException("goal text too long".toString());
        }
    }

    public /* synthetic */ UserGoal(String str, String str2, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? 0L : j);
    }

    public final String getGoalId() {
        return this.goalId;
    }

    public final String getText() {
        return this.text;
    }

    public final long getCreatedAtMillis() {
        return this.createdAtMillis;
    }
}
