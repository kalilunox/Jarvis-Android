package com.jarvis.core.planner;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ActionPlanValidator.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0015"}, d2 = {"Lcom/jarvis/core/planner/ActionPlanValidation;", "", "ok", "", "errors", "", "", "<init>", "(ZLjava/util/List;)V", "getOk", "()Z", "getErrors", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ActionPlanValidation {
    private final List<String> errors;
    private final boolean ok;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ActionPlanValidation copy$default(ActionPlanValidation actionPlanValidation, boolean z, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            z = actionPlanValidation.ok;
        }
        if ((i & 2) != 0) {
            list = actionPlanValidation.errors;
        }
        return actionPlanValidation.copy(z, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getOk() {
        return this.ok;
    }

    public final List<String> component2() {
        return this.errors;
    }

    public final ActionPlanValidation copy(boolean ok, List<String> errors) {
        Intrinsics.checkNotNullParameter(errors, "errors");
        return new ActionPlanValidation(ok, errors);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ActionPlanValidation)) {
            return false;
        }
        ActionPlanValidation actionPlanValidation = (ActionPlanValidation) other;
        return this.ok == actionPlanValidation.ok && Intrinsics.areEqual(this.errors, actionPlanValidation.errors);
    }

    public int hashCode() {
        return (Boolean.hashCode(this.ok) * 31) + this.errors.hashCode();
    }

    public String toString() {
        return "ActionPlanValidation(ok=" + this.ok + ", errors=" + this.errors + ")";
    }

    public ActionPlanValidation(boolean ok, List<String> errors) {
        Intrinsics.checkNotNullParameter(errors, "errors");
        this.ok = ok;
        this.errors = errors;
    }

    public final boolean getOk() {
        return this.ok;
    }

    public final List<String> getErrors() {
        return this.errors;
    }
}
