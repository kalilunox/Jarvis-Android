package com.jarvis.core.behavior;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.jarvis.core.companion.PhoneObservation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: ObservationWindow.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB%\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J-\u0010\u0016\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0010HÖ\u0001J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u000f\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcom/jarvis/core/behavior/ObservationWindow;", "", "observations", "", "Lcom/jarvis/core/companion/PhoneObservation;", "fromMillis", "", "toMillis", "<init>", "(Ljava/util/List;JJ)V", "getObservations", "()Ljava/util/List;", "getFromMillis", "()J", "getToMillis", "size", "", "getSize", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ObservationWindow {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final long fromMillis;
    private final List<PhoneObservation> observations;
    private final long toMillis;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ObservationWindow copy$default(ObservationWindow observationWindow, List list, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = observationWindow.observations;
        }
        if ((i & 2) != 0) {
            j = observationWindow.fromMillis;
        }
        long j3 = j;
        if ((i & 4) != 0) {
            j2 = observationWindow.toMillis;
        }
        return observationWindow.copy(list, j3, j2);
    }

    public final List<PhoneObservation> component1() {
        return this.observations;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getFromMillis() {
        return this.fromMillis;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getToMillis() {
        return this.toMillis;
    }

    public final ObservationWindow copy(List<PhoneObservation> observations, long fromMillis, long toMillis) {
        Intrinsics.checkNotNullParameter(observations, "observations");
        return new ObservationWindow(observations, fromMillis, toMillis);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ObservationWindow)) {
            return false;
        }
        ObservationWindow observationWindow = (ObservationWindow) other;
        return Intrinsics.areEqual(this.observations, observationWindow.observations) && this.fromMillis == observationWindow.fromMillis && this.toMillis == observationWindow.toMillis;
    }

    public int hashCode() {
        return (((this.observations.hashCode() * 31) + Long.hashCode(this.fromMillis)) * 31) + Long.hashCode(this.toMillis);
    }

    public String toString() {
        return "ObservationWindow(observations=" + this.observations + ", fromMillis=" + this.fromMillis + ", toMillis=" + this.toMillis + ")";
    }

    public ObservationWindow(List<PhoneObservation> observations, long fromMillis, long toMillis) {
        Intrinsics.checkNotNullParameter(observations, "observations");
        this.observations = observations;
        this.fromMillis = fromMillis;
        this.toMillis = toMillis;
    }

    public final List<PhoneObservation> getObservations() {
        return this.observations;
    }

    public final long getFromMillis() {
        return this.fromMillis;
    }

    public final long getToMillis() {
        return this.toMillis;
    }

    public final int getSize() {
        return this.observations.size();
    }

    /* JADX INFO: compiled from: ObservationWindow.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J2\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\n¨\u0006\u000e"}, d2 = {"Lcom/jarvis/core/behavior/ObservationWindow$Companion;", "", "<init>", "()V", TypedValues.TransitionType.S_FROM, "Lcom/jarvis/core/behavior/ObservationWindow;", "recent", "", "Lcom/jarvis/core/companion/PhoneObservation;", "nowMillis", "", "maxCount", "", "maxAgeMs", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ObservationWindow from(List<PhoneObservation> recent, long nowMillis, int maxCount, long maxAgeMs) {
            Intrinsics.checkNotNullParameter(recent, "recent");
            long from = nowMillis - maxAgeMs;
            List<PhoneObservation> $this$filter$iv = recent;
            Collection destination$iv$iv = new ArrayList();
            Iterator it = $this$filter$iv.iterator();
            while (true) {
                boolean z = false;
                if (!it.hasNext()) {
                    break;
                }
                Object element$iv$iv = it.next();
                PhoneObservation it2 = (PhoneObservation) element$iv$iv;
                long timestampMillis = it2.getTimestampMillis();
                if (from <= timestampMillis && timestampMillis <= nowMillis) {
                    z = true;
                }
                if (z) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            Iterable $this$sortedBy$iv = (List) destination$iv$iv;
            List filtered = CollectionsKt.takeLast(CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: com.jarvis.core.behavior.ObservationWindow$Companion$from$$inlined$sortedBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    PhoneObservation it3 = (PhoneObservation) t;
                    PhoneObservation it4 = (PhoneObservation) t2;
                    return ComparisonsKt.compareValues(Long.valueOf(it3.getTimestampMillis()), Long.valueOf(it4.getTimestampMillis()));
                }
            }), RangesKt.coerceAtLeast(maxCount, 0));
            PhoneObservation phoneObservation = (PhoneObservation) CollectionsKt.firstOrNull(filtered);
            long timestampMillis2 = phoneObservation != null ? phoneObservation.getTimestampMillis() : from;
            PhoneObservation phoneObservation2 = (PhoneObservation) CollectionsKt.lastOrNull(filtered);
            return new ObservationWindow(filtered, timestampMillis2, phoneObservation2 != null ? phoneObservation2.getTimestampMillis() : nowMillis);
        }
    }
}
