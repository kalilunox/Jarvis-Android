package com.jarvis.core.behavior;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.jarvis.core.companion.ObservationType;
import com.jarvis.core.companion.PhoneObservation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.Grouping;
import kotlin.collections.GroupingKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: RoutineDetector.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B?\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\t¢\u0006\u0004\b\n\u0010\u000bJ\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u0010J\u001e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0006H\u0002J\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0006H\u0002J\u0018\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0003H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/jarvis/core/behavior/RoutineDetector;", "", "minEvidence", "", "minUniqueDays", "sequenceGapMs", "", "hourSlack", "clock", "Lkotlin/Function0;", "<init>", "(IIJILkotlin/jvm/functions/Function0;)V", "detect", "", "Lcom/jarvis/core/behavior/BehaviorCandidate;", "window", "Lcom/jarvis/core/behavior/ObservationWindow;", "detectHeadphonesToAppRoutine", "now", "detectAppSequenceRoutine", "absHourDiff", "a", "b", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RoutineDetector {
    private final Function0<Long> clock;
    private final int hourSlack;
    private final int minEvidence;
    private final int minUniqueDays;
    private final long sequenceGapMs;

    public RoutineDetector() {
        this(0, 0, 0L, 0, null, 31, null);
    }

    public RoutineDetector(int minEvidence, int minUniqueDays, long sequenceGapMs, int hourSlack, Function0<Long> clock) {
        Intrinsics.checkNotNullParameter(clock, "clock");
        this.minEvidence = minEvidence;
        this.minUniqueDays = minUniqueDays;
        this.sequenceGapMs = sequenceGapMs;
        this.hourSlack = hourSlack;
        this.clock = clock;
    }

    public /* synthetic */ RoutineDetector(int i, int i2, long j, int i3, Function0 function0, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 4 : i, (i4 & 2) != 0 ? 3 : i2, (i4 & 4) != 0 ? 300000L : j, (i4 & 8) != 0 ? 2 : i3, (i4 & 16) != 0 ? new Function0() { // from class: com.jarvis.core.behavior.RoutineDetector$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Long.valueOf(System.currentTimeMillis());
            }
        } : function0);
    }

    public final List<BehaviorCandidate> detect(ObservationWindow window) {
        Intrinsics.checkNotNullParameter(window, "window");
        long now = this.clock.invoke().longValue();
        return CollectionsKt.plus((Collection) detectHeadphonesToAppRoutine(window, now), (Iterable) detectAppSequenceRoutine(window, now));
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0088  */
    private final List<BehaviorCandidate> detectHeadphonesToAppRoutine(ObservationWindow window, long now) {
        Map $this$mapNotNullTo$iv$iv;
        Object next;
        int $i$f$mapNotNullTo;
        BehaviorCandidate behaviorCandidate;
        int count$iv;
        Object maxElem$iv;
        Object answer$iv$iv$iv;
        Object obj;
        Object answer$iv;
        boolean z;
        List<PhoneObservation> observations = window.getObservations();
        Map byPkg = new LinkedHashMap();
        int size = observations.size();
        for (int i = 0; i < size; i++) {
            PhoneObservation a = observations.get(i);
            if (a.getType() == ObservationType.HEADPHONES_CONNECTED) {
                Iterable $this$firstOrNull$iv = CollectionsKt.drop(observations, i + 1);
                Iterator it = $this$firstOrNull$iv.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    Object element$iv = it.next();
                    PhoneObservation it2 = (PhoneObservation) element$iv;
                    if (it2.getType() != ObservationType.APP_FOREGROUND) {
                        z = false;
                    } else {
                        String packageName = it2.getPackageName();
                        if ((packageName == null || StringsKt.isBlank(packageName)) || BehaviorObservationSanitizer.INSTANCE.isNoisyPackage(it2.getPackageName())) {
                            z = false;
                        } else {
                            long j = this.sequenceGapMs;
                            long timestampMillis = it2.getTimestampMillis() - a.getTimestampMillis();
                            if (1 <= timestampMillis && timestampMillis <= j) {
                                z = true;
                            } else {
                                z = false;
                            }
                        }
                    }
                    if (z) {
                        obj = element$iv;
                        break;
                    }
                }
                PhoneObservation b = (PhoneObservation) obj;
                if (b != null) {
                    String packageName2 = b.getPackageName();
                    Intrinsics.checkNotNull(packageName2);
                    Object value$iv = byPkg.get(packageName2);
                    if (value$iv == null) {
                        answer$iv = new ArrayList();
                        byPkg.put(packageName2, answer$iv);
                    } else {
                        answer$iv = value$iv;
                    }
                    ((List) answer$iv).add(b);
                }
            }
        }
        Map $this$mapNotNull$iv = byPkg;
        int $i$f$mapNotNull = 0;
        Collection destination$iv$iv = new ArrayList();
        Map $this$mapNotNullTo$iv$iv2 = $this$mapNotNull$iv;
        int $i$f$mapNotNullTo2 = 0;
        for (Map.Entry element$iv$iv$iv : $this$mapNotNullTo$iv$iv2.entrySet()) {
            String pkg = (String) element$iv$iv$iv.getKey();
            Iterable list = (List) element$iv$iv$iv.getValue();
            Iterable $this$distinctBy$iv = list;
            HashSet set$iv = new HashSet();
            ArrayList list$iv = new ArrayList();
            for (Object e$iv : $this$distinctBy$iv) {
                PhoneObservation it3 = (PhoneObservation) e$iv;
                String id = it3.getId();
                observations = observations;
                HashSet set$iv2 = set$iv;
                byPkg = byPkg;
                if (set$iv2.add(id)) {
                    list$iv.add(e$iv);
                    set$iv = set$iv2;
                } else {
                    set$iv = set$iv2;
                }
            }
            List<PhoneObservation> list2 = observations;
            Map byPkg2 = byPkg;
            ArrayList $this$groupBy$iv = list$iv;
            int $i$f$groupBy = 0;
            Map destination$iv$iv2 = new LinkedHashMap();
            for (Object element$iv$iv : $this$groupBy$iv) {
                Iterable $this$groupBy$iv2 = $this$groupBy$iv;
                PhoneObservation it4 = (PhoneObservation) element$iv$iv;
                String strDayKey$default = PatternDetector.Companion.dayKey$default(PatternDetector.INSTANCE, it4.getTimestampMillis(), null, 2, null);
                int $i$f$groupBy2 = $i$f$groupBy;
                Map $this$getOrPut$iv$iv$iv = $this$mapNotNull$iv;
                Object value$iv$iv$iv = destination$iv$iv2.get(strDayKey$default);
                if (value$iv$iv$iv == null) {
                    answer$iv$iv$iv = new ArrayList();
                    destination$iv$iv2.put(strDayKey$default, answer$iv$iv$iv);
                } else {
                    answer$iv$iv$iv = value$iv$iv$iv;
                }
                List list$iv$iv = (List) answer$iv$iv$iv;
                list$iv$iv.add(element$iv$iv);
                $this$groupBy$iv = $this$groupBy$iv2;
                $this$mapNotNull$iv = $this$getOrPut$iv$iv$iv;
                $i$f$groupBy = $i$f$groupBy2;
                $i$f$mapNotNull = $i$f$mapNotNull;
            }
            Map $this$mapNotNull$iv2 = $this$mapNotNull$iv;
            int $i$f$mapNotNull2 = $i$f$mapNotNull;
            Map $this$mapValues$iv = destination$iv$iv2;
            int $i$f$mapValues = 0;
            Map destination$iv$iv$iv = new LinkedHashMap(MapsKt.mapCapacity($this$mapValues$iv.size()));
            Iterable $this$associateByTo$iv$iv$iv = $this$mapValues$iv.entrySet();
            for (Object element$iv$iv$iv2 : $this$associateByTo$iv$iv$iv) {
                Map.Entry it$iv$iv = (Map.Entry) element$iv$iv$iv2;
                Map $this$mapValues$iv2 = $this$mapValues$iv;
                Object key = it$iv$iv.getKey();
                Iterable v = (List) ((Map.Entry) element$iv$iv$iv2).getValue();
                Iterable $this$maxByOrNull$iv = v;
                Iterator iterator$iv = $this$maxByOrNull$iv.iterator();
                if (iterator$iv.hasNext()) {
                    maxElem$iv = iterator$iv.next();
                    if (iterator$iv.hasNext()) {
                        PhoneObservation it5 = (PhoneObservation) maxElem$iv;
                        long maxValue$iv = it5.getTimestampMillis();
                        while (true) {
                            Object e$iv2 = iterator$iv.next();
                            PhoneObservation it6 = (PhoneObservation) e$iv2;
                            long v$iv = it6.getTimestampMillis();
                            if (maxValue$iv < v$iv) {
                                maxElem$iv = e$iv2;
                                maxValue$iv = v$iv;
                            }
                            if (!iterator$iv.hasNext()) {
                                break;
                            }
                            destination$iv$iv$iv = destination$iv$iv$iv;
                        }
                    }
                } else {
                    maxElem$iv = null;
                }
                Intrinsics.checkNotNull(maxElem$iv);
                destination$iv$iv$iv.put(key, (PhoneObservation) maxElem$iv);
                $this$mapValues$iv = $this$mapValues$iv2;
                $i$f$mapValues = $i$f$mapValues;
                destination$iv$iv$iv = destination$iv$iv$iv;
            }
            List perDay = CollectionsKt.toList(destination$iv$iv$iv.values());
            if (perDay.size() < this.minEvidence) {
                $this$mapNotNullTo$iv$iv = $this$mapNotNullTo$iv$iv2;
                $i$f$mapNotNullTo = $i$f$mapNotNullTo2;
                behaviorCandidate = null;
            } else {
                PatternDetector.Companion companion = PatternDetector.INSTANCE;
                List $this$map$iv = perDay;
                $this$mapNotNullTo$iv$iv = $this$mapNotNullTo$iv$iv2;
                Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    PhoneObservation it7 = (PhoneObservation) item$iv$iv;
                    destination$iv$iv3.add(Long.valueOf(it7.getTimestampMillis()));
                }
                int days = PatternDetector.Companion.uniqueDays$default(companion, (List) destination$iv$iv3, null, 2, null);
                if (days < this.minUniqueDays) {
                    behaviorCandidate = null;
                    $i$f$mapNotNullTo = $i$f$mapNotNullTo2;
                } else {
                    List $this$map$iv2 = perDay;
                    int $i$f$map = CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10);
                    Collection destination$iv$iv4 = new ArrayList($i$f$map);
                    for (Object item$iv$iv2 : $this$map$iv2) {
                        PhoneObservation it8 = (PhoneObservation) item$iv$iv2;
                        destination$iv$iv4.add(Integer.valueOf(PatternDetector.Companion.hourOf$default(PatternDetector.INSTANCE, it8.getTimestampMillis(), null, 2, null)));
                        $this$map$iv2 = $this$map$iv2;
                    }
                    Iterable hours = (List) destination$iv$iv4;
                    final Iterable $this$groupingBy$iv = hours;
                    Iterator it9 = GroupingKt.eachCount(new Grouping<Integer, Integer>() { // from class: com.jarvis.core.behavior.RoutineDetector$detectHeadphonesToAppRoutine$lambda$2$$inlined$groupingBy$1
                        @Override // kotlin.collections.Grouping
                        public Iterator<Integer> sourceIterator() {
                            return $this$groupingBy$iv.iterator();
                        }

                        @Override // kotlin.collections.Grouping
                        public Integer keyOf(Integer element) {
                            int it10 = element.intValue();
                            return Integer.valueOf(it10);
                        }
                    }).entrySet().iterator();
                    if (it9.hasNext()) {
                        next = it9.next();
                        if (it9.hasNext()) {
                            Map.Entry it10 = (Map.Entry) next;
                            int iIntValue = ((Number) it10.getValue()).intValue();
                            while (true) {
                                Object next2 = it9.next();
                                Map.Entry it11 = (Map.Entry) next2;
                                Object obj2 = next;
                                int iIntValue2 = ((Number) it11.getValue()).intValue();
                                if (iIntValue < iIntValue2) {
                                    iIntValue = iIntValue2;
                                    next = next2;
                                } else {
                                    next = obj2;
                                }
                                if (!it9.hasNext()) {
                                    break;
                                }
                                $i$f$mapNotNullTo2 = $i$f$mapNotNullTo2;
                            }
                        }
                    } else {
                        next = null;
                    }
                    Map.Entry entry = (Map.Entry) next;
                    if (entry != null) {
                        int modeHour = ((Number) entry.getKey()).intValue();
                        Iterable $this$count$iv = hours;
                        int $i$f$count = 0;
                        if (($this$count$iv instanceof Collection) && ((Collection) $this$count$iv).isEmpty()) {
                            count$iv = 0;
                        } else {
                            count$iv = 0;
                            Iterator it12 = $this$count$iv.iterator();
                            while (it12.hasNext()) {
                                $this$count$iv = $this$count$iv;
                                int it13 = ((Number) it12.next()).intValue();
                                $i$f$count = $i$f$count;
                                int $i$f$count2 = absHourDiff(it13, modeHour);
                                int it14 = this.hourSlack;
                                if ($i$f$count2 <= it14) {
                                    count$iv++;
                                    if (count$iv < 0) {
                                        CollectionsKt.throwCountOverflow();
                                    }
                                }
                            }
                        }
                        if (count$iv < this.minEvidence) {
                            $i$f$mapNotNullTo = $i$f$mapNotNullTo2;
                            behaviorCandidate = null;
                        } else {
                            float regularity = count$iv / perDay.size();
                            ConfidenceModel confidenceModel = ConfidenceModel.INSTANCE;
                            int size2 = perDay.size();
                            Iterator it15 = perDay.iterator();
                            if (!it15.hasNext()) {
                                throw new NoSuchElementException();
                            }
                            PhoneObservation it16 = (PhoneObservation) it15.next();
                            long timestampMillis2 = it16.getTimestampMillis();
                            while (it15.hasNext()) {
                                PhoneObservation it17 = (PhoneObservation) it15.next();
                                long timestampMillis3 = it17.getTimestampMillis();
                                if (timestampMillis2 < timestampMillis3) {
                                    timestampMillis2 = timestampMillis3;
                                }
                            }
                            float conf = confidenceModel.score(size2, days, timestampMillis2, now, (32 & 16) != 0 ? 0.85f : 0.9f, (32 & 32) != 0 ? 0 : 0, (32 & 64) != 0 ? 0.5f : regularity);
                            BehaviorKind behaviorKind = BehaviorKind.ROUTINE;
                            PatternFamily patternFamily = PatternFamily.SEQUENCE;
                            String str = "Привычка: после наушников около " + modeHour + ":00 запускается " + pkg;
                            int size3 = perDay.size();
                            Iterator it18 = perDay.iterator();
                            if (!it18.hasNext()) {
                                throw new NoSuchElementException();
                            }
                            PhoneObservation it19 = (PhoneObservation) it18.next();
                            long timestampMillis4 = it19.getTimestampMillis();
                            while (it18.hasNext()) {
                                PhoneObservation it20 = (PhoneObservation) it18.next();
                                long timestampMillis5 = it20.getTimestampMillis();
                                if (timestampMillis4 > timestampMillis5) {
                                    timestampMillis4 = timestampMillis5;
                                }
                            }
                            Iterator it21 = perDay.iterator();
                            if (!it21.hasNext()) {
                                throw new NoSuchElementException();
                            }
                            PhoneObservation it22 = (PhoneObservation) it21.next();
                            long timestampMillis6 = it22.getTimestampMillis();
                            while (it21.hasNext()) {
                                PhoneObservation it23 = (PhoneObservation) it21.next();
                                long timestampMillis7 = it23.getTimestampMillis();
                                if (timestampMillis6 < timestampMillis7) {
                                    timestampMillis6 = timestampMillis7;
                                }
                            }
                            List $this$map$iv3 = perDay;
                            $i$f$mapNotNullTo = $i$f$mapNotNullTo2;
                            int $i$f$mapNotNullTo3 = CollectionsKt.collectionSizeOrDefault($this$map$iv3, 10);
                            Collection destination$iv$iv5 = new ArrayList($i$f$mapNotNullTo3);
                            for (Object item$iv$iv3 : $this$map$iv3) {
                                PhoneObservation it24 = (PhoneObservation) item$iv$iv3;
                                destination$iv$iv5.add(it24.getId());
                                $this$map$iv3 = $this$map$iv3;
                            }
                            behaviorCandidate = new BehaviorCandidate(null, behaviorKind, patternFamily, str, conf, size3, timestampMillis4, timestampMillis6, CollectionsKt.takeLast((List) destination$iv$iv5, 16), null, "routine:HEADPHONES→" + pkg + "@" + modeHour, MapsKt.mapOf(TuplesKt.to("trigger", "HEADPHONES_CONNECTED"), TuplesKt.to("follow", pkg), TuplesKt.to("hour", String.valueOf(modeHour))), days, 0, perDay.size() + " повторов в " + days + " днях; час ≈" + modeHour + " (±" + this.hourSlack + ")", 8705, null);
                        }
                    } else {
                        $i$f$mapNotNullTo = $i$f$mapNotNullTo2;
                        behaviorCandidate = null;
                    }
                }
            }
            if (behaviorCandidate != null) {
                destination$iv$iv.add(behaviorCandidate);
            }
            $this$mapNotNullTo$iv$iv2 = $this$mapNotNullTo$iv$iv;
            byPkg = byPkg2;
            observations = list2;
            $this$mapNotNull$iv = $this$mapNotNull$iv2;
            $i$f$mapNotNullTo2 = $i$f$mapNotNullTo;
            $i$f$mapNotNull = $i$f$mapNotNull2;
        }
        return (List) destination$iv$iv;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0043  */
    private final List<BehaviorCandidate> detectAppSequenceRoutine(ObservationWindow window, long now) {
        Object next;
        Iterator it;
        Object answer$iv;
        boolean z;
        RoutineDetector routineDetector = this;
        Iterable $this$filter$iv = window.getObservations();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            PhoneObservation it2 = (PhoneObservation) element$iv$iv;
            if (it2.getType() != ObservationType.APP_FOREGROUND) {
                z = false;
            } else {
                String packageName = it2.getPackageName();
                if (packageName == null || StringsKt.isBlank(packageName)) {
                    z = false;
                } else {
                    z = true;
                }
            }
            if (z) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List apps = (List) destination$iv$iv;
        Map transitions = new LinkedHashMap();
        int size = apps.size() - 1;
        for (int i = 0; i < size; i++) {
            String a = ((PhoneObservation) apps.get(i)).getPackageName();
            Intrinsics.checkNotNull(a);
            String b = ((PhoneObservation) apps.get(i + 1)).getPackageName();
            Intrinsics.checkNotNull(b);
            if (!Intrinsics.areEqual(a, b)) {
                long dt = ((PhoneObservation) apps.get(i + 1)).getTimestampMillis() - ((PhoneObservation) apps.get(i)).getTimestampMillis();
                if (1 <= dt && dt <= routineDetector.sequenceGapMs) {
                    String key = a + "→" + b;
                    Object value$iv = transitions.get(key);
                    if (value$iv == null) {
                        answer$iv = new ArrayList();
                        transitions.put(key, answer$iv);
                    } else {
                        answer$iv = value$iv;
                    }
                    ((List) answer$iv).add(Long.valueOf(((PhoneObservation) apps.get(i + 1)).getTimestampMillis()));
                }
            }
        }
        Map $this$mapNotNull$iv = transitions;
        int $i$f$mapNotNull = 0;
        Collection destination$iv$iv2 = new ArrayList();
        Map $this$mapNotNullTo$iv$iv = $this$mapNotNull$iv;
        int $i$f$mapNotNullTo = 0;
        Map $this$forEach$iv$iv$iv = $this$mapNotNullTo$iv$iv;
        int $i$f$forEach = 0;
        Iterator it3 = $this$forEach$iv$iv$iv.entrySet().iterator();
        while (it3.hasNext()) {
            Map.Entry element$iv$iv$iv = (Map.Entry) it3.next();
            String key2 = (String) element$iv$iv$iv.getKey();
            List apps2 = apps;
            List<Long> list = (List) element$iv$iv$iv.getValue();
            Map transitions2 = transitions;
            Map $this$mapNotNull$iv2 = $this$mapNotNull$iv;
            int $i$f$mapNotNull2 = $i$f$mapNotNull;
            BehaviorCandidate behaviorCandidate = null;
            if (list.size() < routineDetector.minEvidence) {
                it = it3;
            } else {
                int days = PatternDetector.Companion.uniqueDays$default(PatternDetector.INSTANCE, list, null, 2, null);
                if (days < routineDetector.minUniqueDays) {
                    it = it3;
                    behaviorCandidate = null;
                } else {
                    List<Long> $this$map$iv = list;
                    Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                    Iterable $this$mapTo$iv$iv = $this$map$iv;
                    for (Object item$iv$iv : $this$mapTo$iv$iv) {
                        long it4 = ((Number) item$iv$iv).longValue();
                        destination$iv$iv3.add(Integer.valueOf(PatternDetector.Companion.hourOf$default(PatternDetector.INSTANCE, it4, null, 2, null)));
                        $this$mapTo$iv$iv = $this$mapTo$iv$iv;
                    }
                    Iterable hours = (List) destination$iv$iv3;
                    final Iterable $this$groupingBy$iv = hours;
                    Iterator it5 = GroupingKt.eachCount(new Grouping<Integer, Integer>() { // from class: com.jarvis.core.behavior.RoutineDetector$detectAppSequenceRoutine$lambda$2$$inlined$groupingBy$1
                        @Override // kotlin.collections.Grouping
                        public Iterator<Integer> sourceIterator() {
                            return $this$groupingBy$iv.iterator();
                        }

                        @Override // kotlin.collections.Grouping
                        public Integer keyOf(Integer element) {
                            int it6 = element.intValue();
                            return Integer.valueOf(it6);
                        }
                    }).entrySet().iterator();
                    if (it5.hasNext()) {
                        next = it5.next();
                        if (it5.hasNext()) {
                            Map.Entry it6 = (Map.Entry) next;
                            int iIntValue = ((Number) it6.getValue()).intValue();
                            while (true) {
                                Object next2 = it5.next();
                                Map.Entry it7 = (Map.Entry) next2;
                                Object obj = next;
                                int iIntValue2 = ((Number) it7.getValue()).intValue();
                                if (iIntValue < iIntValue2) {
                                    iIntValue = iIntValue2;
                                    next = next2;
                                } else {
                                    next = obj;
                                }
                                if (!it5.hasNext()) {
                                    break;
                                }
                                it3 = it3;
                            }
                        }
                    } else {
                        next = null;
                    }
                    Map.Entry entry = (Map.Entry) next;
                    int modeHour = entry != null ? ((Number) entry.getKey()).intValue() : 0;
                    float regularity = PatternDetector.INSTANCE.regularityByHour(list);
                    if (regularity < 0.4f) {
                        it = it3;
                        behaviorCandidate = null;
                    } else {
                        ConfidenceModel confidenceModel = ConfidenceModel.INSTANCE;
                        int size2 = list.size();
                        Long l = (Long) CollectionsKt.maxOrNull((Iterable) list);
                        float conf = confidenceModel.score(size2, days, l != null ? l.longValue() : now, now, (32 & 16) != 0 ? 0.85f : 0.85f, (32 & 32) != 0 ? 0 : 0, (32 & 64) != 0 ? 0.5f : regularity);
                        List listSplit$default = StringsKt.split$default((CharSequence) key2, new String[]{"→"}, false, 0, 6, (Object) null);
                        String from = (String) listSplit$default.get(0);
                        String to = (String) listSplit$default.get(1);
                        BehaviorKind behaviorKind = BehaviorKind.ROUTINE;
                        PatternFamily patternFamily = PatternFamily.SEQUENCE;
                        it = it3;
                        String str = "Привычка: после " + from + " часто открывается " + to + " (≈" + modeHour + ":00)";
                        int size3 = list.size();
                        Long l2 = (Long) CollectionsKt.minOrNull((Iterable) list);
                        long jLongValue = l2 != null ? l2.longValue() : now;
                        Long l3 = (Long) CollectionsKt.maxOrNull((Iterable) list);
                        long jLongValue2 = l3 != null ? l3.longValue() : now;
                        String str2 = "routine:APP:" + key2 + "@" + modeHour;
                        Map mapMapOf = MapsKt.mapOf(TuplesKt.to(TypedValues.TransitionType.S_FROM, from), TuplesKt.to(TypedValues.TransitionType.S_TO, to), TuplesKt.to("hour", String.valueOf(modeHour)));
                        int size4 = list.size();
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        String str3 = String.format(Locale.US, "%.2f", Arrays.copyOf(new Object[]{Float.valueOf(regularity)}, 1));
                        Intrinsics.checkNotNullExpressionValue(str3, "format(...)");
                        behaviorCandidate = new BehaviorCandidate(null, behaviorKind, patternFamily, str, conf, size3, jLongValue, jLongValue2, null, null, str2, mapMapOf, days, 0, size4 + " переходов " + from + "→" + to + " за " + days + " дн.; regularity=" + str3, 8961, null);
                    }
                }
            }
            if (behaviorCandidate != null) {
                destination$iv$iv2.add(behaviorCandidate);
            }
            routineDetector = this;
            transitions = transitions2;
            apps = apps2;
            $this$mapNotNull$iv = $this$mapNotNull$iv2;
            $i$f$mapNotNull = $i$f$mapNotNull2;
            $this$mapNotNullTo$iv$iv = $this$mapNotNullTo$iv$iv;
            $i$f$mapNotNullTo = $i$f$mapNotNullTo;
            $this$forEach$iv$iv$iv = $this$forEach$iv$iv$iv;
            $i$f$forEach = $i$f$forEach;
            it3 = it;
        }
        return (List) destination$iv$iv2;
    }

    private final int absHourDiff(int a, int b) {
        int d = Math.abs(a - b);
        return Math.min(d, 24 - d);
    }
}
