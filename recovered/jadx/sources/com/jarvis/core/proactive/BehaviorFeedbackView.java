package com.jarvis.core.proactive;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.jarvis.core.behavior.BehaviorStore;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProactivityState.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB=\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0015\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J?\u0010\u0013\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/jarvis/core/proactive/BehaviorFeedbackView;", "", "disabledKeys", "", "", "rejectedKeys", "ignoreUntil", "", "", "<init>", "(Ljava/util/Set;Ljava/util/Set;Ljava/util/Map;)V", "getDisabledKeys", "()Ljava/util/Set;", "getRejectedKeys", "getIgnoreUntil", "()Ljava/util/Map;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class BehaviorFeedbackView {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Set<String> disabledKeys;
    private final Map<String, Long> ignoreUntil;
    private final Set<String> rejectedKeys;

    public BehaviorFeedbackView() {
        this(null, null, null, 7, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BehaviorFeedbackView copy$default(BehaviorFeedbackView behaviorFeedbackView, Set set, Set set2, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            set = behaviorFeedbackView.disabledKeys;
        }
        if ((i & 2) != 0) {
            set2 = behaviorFeedbackView.rejectedKeys;
        }
        if ((i & 4) != 0) {
            map = behaviorFeedbackView.ignoreUntil;
        }
        return behaviorFeedbackView.copy(set, set2, map);
    }

    public final Set<String> component1() {
        return this.disabledKeys;
    }

    public final Set<String> component2() {
        return this.rejectedKeys;
    }

    public final Map<String, Long> component3() {
        return this.ignoreUntil;
    }

    public final BehaviorFeedbackView copy(Set<String> disabledKeys, Set<String> rejectedKeys, Map<String, Long> ignoreUntil) {
        Intrinsics.checkNotNullParameter(disabledKeys, "disabledKeys");
        Intrinsics.checkNotNullParameter(rejectedKeys, "rejectedKeys");
        Intrinsics.checkNotNullParameter(ignoreUntil, "ignoreUntil");
        return new BehaviorFeedbackView(disabledKeys, rejectedKeys, ignoreUntil);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BehaviorFeedbackView)) {
            return false;
        }
        BehaviorFeedbackView behaviorFeedbackView = (BehaviorFeedbackView) other;
        return Intrinsics.areEqual(this.disabledKeys, behaviorFeedbackView.disabledKeys) && Intrinsics.areEqual(this.rejectedKeys, behaviorFeedbackView.rejectedKeys) && Intrinsics.areEqual(this.ignoreUntil, behaviorFeedbackView.ignoreUntil);
    }

    public int hashCode() {
        return (((this.disabledKeys.hashCode() * 31) + this.rejectedKeys.hashCode()) * 31) + this.ignoreUntil.hashCode();
    }

    public String toString() {
        return "BehaviorFeedbackView(disabledKeys=" + this.disabledKeys + ", rejectedKeys=" + this.rejectedKeys + ", ignoreUntil=" + this.ignoreUntil + ")";
    }

    public BehaviorFeedbackView(Set<String> disabledKeys, Set<String> rejectedKeys, Map<String, Long> ignoreUntil) {
        Intrinsics.checkNotNullParameter(disabledKeys, "disabledKeys");
        Intrinsics.checkNotNullParameter(rejectedKeys, "rejectedKeys");
        Intrinsics.checkNotNullParameter(ignoreUntil, "ignoreUntil");
        this.disabledKeys = disabledKeys;
        this.rejectedKeys = rejectedKeys;
        this.ignoreUntil = ignoreUntil;
    }

    public /* synthetic */ BehaviorFeedbackView(Set set, Set set2, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? SetsKt.emptySet() : set, (i & 2) != 0 ? SetsKt.emptySet() : set2, (i & 4) != 0 ? MapsKt.emptyMap() : map);
    }

    public final Set<String> getDisabledKeys() {
        return this.disabledKeys;
    }

    public final Set<String> getRejectedKeys() {
        return this.rejectedKeys;
    }

    public final Map<String, Long> getIgnoreUntil() {
        return this.ignoreUntil;
    }

    /* JADX INFO: compiled from: ProactivityState.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/jarvis/core/proactive/BehaviorFeedbackView$Companion;", "", "<init>", "()V", TypedValues.TransitionType.S_FROM, "Lcom/jarvis/core/proactive/BehaviorFeedbackView;", "store", "Lcom/jarvis/core/behavior/BehaviorStore;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BehaviorFeedbackView from(BehaviorStore store) {
            Intrinsics.checkNotNullParameter(store, "store");
            return new BehaviorFeedbackView(store.disabledKeys(), store.rejectedKeys(), store.ignoreUntilMap());
        }
    }
}
