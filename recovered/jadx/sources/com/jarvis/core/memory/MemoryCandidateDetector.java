package com.jarvis.core.memory;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.jarvis.core.companion.ContextSnapshot;
import com.jarvis.core.companion.ObservationType;
import com.jarvis.core.companion.PhoneObservation;
import com.jarvis.core.companion.Sensitivity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: MemoryCandidateDetector.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J,\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\t2\b\b\u0002\u0010\u000f\u001a\u00020\u0005J(\u0010\u0010\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\t2\u0006\u0010\u000f\u001a\u00020\u0005H\u0002J(\u0010\u0011\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\t2\u0006\u0010\u000f\u001a\u00020\u0005H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/jarvis/core/memory/MemoryCandidateDetector;", "", "routineMinEvidence", "", "routineWindowMs", "", "<init>", "(IJ)V", "detect", "", "Lcom/jarvis/core/memory/MemoryCandidate;", "snapshot", "Lcom/jarvis/core/companion/ContextSnapshot;", "recent", "Lcom/jarvis/core/companion/PhoneObservation;", "nowMillis", "detectRoutineHint", "detectEpisodicAppSwitch", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MemoryCandidateDetector {
    private final int routineMinEvidence;
    private final long routineWindowMs;

    public MemoryCandidateDetector() {
        this(0, 0L, 3, null);
    }

    public MemoryCandidateDetector(int routineMinEvidence, long routineWindowMs) {
        this.routineMinEvidence = routineMinEvidence;
        this.routineWindowMs = routineWindowMs;
    }

    public /* synthetic */ MemoryCandidateDetector(int i, long j, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 3 : i, (i2 & 2) != 0 ? 10800000L : j);
    }

    public static /* synthetic */ List detect$default(MemoryCandidateDetector memoryCandidateDetector, ContextSnapshot contextSnapshot, List list, long j, int i, Object obj) {
        if ((i & 4) != 0) {
            j = contextSnapshot.getTakenAtMillis();
        }
        return memoryCandidateDetector.detect(contextSnapshot, list, j);
    }

    public final List<MemoryCandidate> detect(ContextSnapshot snapshot, List<PhoneObservation> recent, long nowMillis) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        Intrinsics.checkNotNullParameter(recent, "recent");
        List out = new ArrayList();
        MemoryCandidate it = detectRoutineHint(snapshot, recent, nowMillis);
        if (it != null) {
            out.add(it);
        }
        MemoryCandidate it2 = detectEpisodicAppSwitch(snapshot, recent, nowMillis);
        if (it2 != null) {
            out.add(it2);
        }
        return out;
    }

    private final MemoryCandidate detectRoutineHint(ContextSnapshot snapshot, List<PhoneObservation> recent, long nowMillis) {
        String pkg = snapshot.getContext().getCurrentApp();
        if (pkg == null) {
            return null;
        }
        List<PhoneObservation> $this$filter$iv = recent;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            PhoneObservation it = (PhoneObservation) element$iv$iv;
            if (it.getType() == ObservationType.APP_FOREGROUND && Intrinsics.areEqual(it.getPackageName(), pkg) && nowMillis - it.getTimestampMillis() <= this.routineWindowMs) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List fg = (List) destination$iv$iv;
        if (fg.size() < this.routineMinEvidence) {
            return null;
        }
        Iterator it2 = fg.iterator();
        if (!it2.hasNext()) {
            throw new NoSuchElementException();
        }
        PhoneObservation it3 = (PhoneObservation) it2.next();
        long first = it3.getTimestampMillis();
        while (it2.hasNext()) {
            PhoneObservation it4 = (PhoneObservation) it2.next();
            long timestampMillis = it4.getTimestampMillis();
            if (first > timestampMillis) {
                first = timestampMillis;
            }
        }
        Iterator it5 = fg.iterator();
        if (!it5.hasNext()) {
            throw new NoSuchElementException();
        }
        PhoneObservation it6 = (PhoneObservation) it5.next();
        long last = it6.getTimestampMillis();
        while (it5.hasNext()) {
            PhoneObservation it7 = (PhoneObservation) it5.next();
            long timestampMillis2 = it7.getTimestampMillis();
            if (last < timestampMillis2) {
                last = timestampMillis2;
            }
        }
        float conf = RangesKt.coerceAtMost((fg.size() * 0.08f) + 0.45f, 0.85f);
        MemoryKind memoryKind = MemoryKind.ROUTINE;
        MemoryProvenance memoryProvenance = MemoryProvenance.OBSERVED;
        String str = "Пользователь часто открывает " + pkg + " (наблюдение)";
        int size = fg.size();
        Sensitivity sensitivity = Sensitivity.PRIVATE;
        List $this$map$iv = fg;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            PhoneObservation it8 = (PhoneObservation) item$iv$iv;
            destination$iv$iv2.add(it8.getId());
        }
        return new MemoryCandidate(null, memoryKind, memoryProvenance, str, size, conf, first, last, sensitivity, pkg, CollectionsKt.takeLast((List) destination$iv$iv2, 12), MapsKt.mapOf(TuplesKt.to("type", "ROUTINE_HINT"), TuplesKt.to("package", pkg)), 1, null);
    }

    private final MemoryCandidate detectEpisodicAppSwitch(ContextSnapshot snapshot, List<PhoneObservation> recent, long nowMillis) {
        String prev;
        Object element$iv;
        PhoneObservation it;
        String cur = snapshot.getContext().getCurrentApp();
        if (cur == null || (prev = snapshot.getContext().getPreviousApp()) == null || Intrinsics.areEqual(cur, prev)) {
            return null;
        }
        ListIterator<PhoneObservation> listIterator = recent.listIterator(recent.size());
        do {
            if (listIterator.hasPrevious()) {
                element$iv = listIterator.previous();
                it = (PhoneObservation) element$iv;
            } else {
                element$iv = null;
                break;
            }
        } while (!(it.getType() == ObservationType.APP_FOREGROUND && Intrinsics.areEqual(it.getPackageName(), cur)));
        PhoneObservation lastFg = (PhoneObservation) element$iv;
        if (lastFg != null && nowMillis - lastFg.getTimestampMillis() <= 60000) {
            return new MemoryCandidate(null, MemoryKind.EPISODIC, MemoryProvenance.OBSERVED, "Переключение " + prev + " → " + cur, 1, RangesKt.coerceAtMost(lastFg.getConfidence(), 0.7f), lastFg.getTimestampMillis(), lastFg.getTimestampMillis(), Sensitivity.NORMAL, cur, CollectionsKt.listOf(lastFg.getId()), MapsKt.mapOf(TuplesKt.to("type", "APP_SWITCH"), TuplesKt.to(TypedValues.TransitionType.S_FROM, prev), TuplesKt.to(TypedValues.TransitionType.S_TO, cur)), 1, null);
        }
        return null;
    }
}
