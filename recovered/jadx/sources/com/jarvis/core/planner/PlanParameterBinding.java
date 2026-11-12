package com.jarvis.core.planner;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ActionPlanModels.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/jarvis/core/planner/PlanParameterBinding;", "", "name", "", "fromStepId", "fromKey", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getFromStepId", "getFromKey", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class PlanParameterBinding {
    private final String fromKey;
    private final String fromStepId;
    private final String name;

    public static /* synthetic */ PlanParameterBinding copy$default(PlanParameterBinding planParameterBinding, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = planParameterBinding.name;
        }
        if ((i & 2) != 0) {
            str2 = planParameterBinding.fromStepId;
        }
        if ((i & 4) != 0) {
            str3 = planParameterBinding.fromKey;
        }
        return planParameterBinding.copy(str, str2, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getFromStepId() {
        return this.fromStepId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getFromKey() {
        return this.fromKey;
    }

    public final PlanParameterBinding copy(String name, String fromStepId, String fromKey) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(fromStepId, "fromStepId");
        Intrinsics.checkNotNullParameter(fromKey, "fromKey");
        return new PlanParameterBinding(name, fromStepId, fromKey);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlanParameterBinding)) {
            return false;
        }
        PlanParameterBinding planParameterBinding = (PlanParameterBinding) other;
        return Intrinsics.areEqual(this.name, planParameterBinding.name) && Intrinsics.areEqual(this.fromStepId, planParameterBinding.fromStepId) && Intrinsics.areEqual(this.fromKey, planParameterBinding.fromKey);
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + this.fromStepId.hashCode()) * 31) + this.fromKey.hashCode();
    }

    public String toString() {
        return "PlanParameterBinding(name=" + this.name + ", fromStepId=" + this.fromStepId + ", fromKey=" + this.fromKey + ")";
    }

    public PlanParameterBinding(String name, String fromStepId, String fromKey) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(fromStepId, "fromStepId");
        Intrinsics.checkNotNullParameter(fromKey, "fromKey");
        this.name = name;
        this.fromStepId = fromStepId;
        this.fromKey = fromKey;
        if (StringsKt.isBlank(this.name)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (StringsKt.isBlank(this.fromStepId)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (StringsKt.isBlank(this.fromKey)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(this.name.length() <= 64)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (this.fromKey.length() <= 64) {
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public /* synthetic */ PlanParameterBinding(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? "output" : str3);
    }

    public final String getName() {
        return this.name;
    }

    public final String getFromStepId() {
        return this.fromStepId;
    }

    public final String getFromKey() {
        return this.fromKey;
    }
}
