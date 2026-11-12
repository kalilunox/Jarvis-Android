package com.jarvis.core.nlp;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Routines.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0004HÆ\u0003J/\u0010\u0011\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/jarvis/core/nlp/Routine;", "", "steps", "", "", "closing", "report", "<init>", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "getSteps", "()Ljava/util/List;", "getClosing", "()Ljava/lang/String;", "getReport", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class Routine {
    private final String closing;
    private final String report;
    private final List<String> steps;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Routine copy$default(Routine routine, List list, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = routine.steps;
        }
        if ((i & 2) != 0) {
            str = routine.closing;
        }
        if ((i & 4) != 0) {
            str2 = routine.report;
        }
        return routine.copy(list, str, str2);
    }

    public final List<String> component1() {
        return this.steps;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getClosing() {
        return this.closing;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getReport() {
        return this.report;
    }

    public final Routine copy(List<String> steps, String closing, String report) {
        Intrinsics.checkNotNullParameter(steps, "steps");
        Intrinsics.checkNotNullParameter(closing, "closing");
        return new Routine(steps, closing, report);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Routine)) {
            return false;
        }
        Routine routine = (Routine) other;
        return Intrinsics.areEqual(this.steps, routine.steps) && Intrinsics.areEqual(this.closing, routine.closing) && Intrinsics.areEqual(this.report, routine.report);
    }

    public int hashCode() {
        return (((this.steps.hashCode() * 31) + this.closing.hashCode()) * 31) + (this.report == null ? 0 : this.report.hashCode());
    }

    public String toString() {
        return "Routine(steps=" + this.steps + ", closing=" + this.closing + ", report=" + this.report + ")";
    }

    public Routine(List<String> steps, String closing, String report) {
        Intrinsics.checkNotNullParameter(steps, "steps");
        Intrinsics.checkNotNullParameter(closing, "closing");
        this.steps = steps;
        this.closing = closing;
        this.report = report;
    }

    public /* synthetic */ Routine(List list, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, str, (i & 4) != 0 ? null : str2);
    }

    public final List<String> getSteps() {
        return this.steps;
    }

    public final String getClosing() {
        return this.closing;
    }

    public final String getReport() {
        return this.report;
    }
}
