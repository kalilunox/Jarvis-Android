package com.jarvis.core.behavior;

import androidx.core.view.InputDeviceCompat;
import com.jarvis.core.companion.ObservationType;
import com.jarvis.core.companion.PhoneObservation;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.Grouping;
import kotlin.collections.GroupingKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: PatternDetector.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B5\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b¢\u0006\u0004\b\t\u0010\nJ\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000fJ\u001e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0005H\u0002J\u001e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0005H\u0002J\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0005H\u0002J\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0005H\u0002J\u001e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0005H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/jarvis/core/behavior/PatternDetector;", "", "minEvidence", "", "sequenceGapMs", "", "coOccurGapMs", "clock", "Lkotlin/Function0;", "<init>", "(IJJLkotlin/jvm/functions/Function0;)V", "detect", "", "Lcom/jarvis/core/behavior/BehaviorCandidate;", "window", "Lcom/jarvis/core/behavior/ObservationWindow;", "detectSequences", "now", "detectFrequency", "detectTemporal", "detectCoOccurrence", "detectContextBattery", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PatternDetector {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Function0<Long> clock;
    private final long coOccurGapMs;
    private final int minEvidence;
    private final long sequenceGapMs;

    public PatternDetector() {
        this(0, 0L, 0L, null, 15, null);
    }

    public PatternDetector(int minEvidence, long sequenceGapMs, long coOccurGapMs, Function0<Long> clock) {
        Intrinsics.checkNotNullParameter(clock, "clock");
        this.minEvidence = minEvidence;
        this.sequenceGapMs = sequenceGapMs;
        this.coOccurGapMs = coOccurGapMs;
        this.clock = clock;
    }

    public /* synthetic */ PatternDetector(int i, long j, long j2, Function0 function0, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 3 : i, (i2 & 2) != 0 ? 300000L : j, (i2 & 4) != 0 ? 180000L : j2, (i2 & 8) != 0 ? new Function0() { // from class: com.jarvis.core.behavior.PatternDetector$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Long.valueOf(System.currentTimeMillis());
            }
        } : function0);
    }

    public final List<BehaviorCandidate> detect(ObservationWindow window) {
        Intrinsics.checkNotNullParameter(window, "window");
        long now = this.clock.invoke().longValue();
        Iterable out = (List) new ArrayList();
        CollectionsKt.addAll((Collection) out, detectSequences(window, now));
        CollectionsKt.addAll((Collection) out, detectFrequency(window, now));
        CollectionsKt.addAll((Collection) out, detectTemporal(window, now));
        CollectionsKt.addAll((Collection) out, detectCoOccurrence(window, now));
        CollectionsKt.addAll((Collection) out, detectContextBattery(window, now));
        Iterable $this$distinctBy$iv = out;
        HashSet set$iv = new HashSet();
        ArrayList list$iv = new ArrayList();
        for (Object e$iv : $this$distinctBy$iv) {
            BehaviorCandidate it = (BehaviorCandidate) e$iv;
            if (set$iv.add(it.getPatternKey())) {
                list$iv.add(e$iv);
            }
        }
        return list$iv;
    }

    /* JADX INFO: compiled from: PatternDetector.kt */
    @Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\u008a\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\"\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001¢\u0006\u0002\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"com/jarvis/core/behavior/PatternDetector$detectSequences$Follow", "", "hpId", "", "app", "Lcom/jarvis/core/companion/PhoneObservation;", "<init>", "(Ljava/lang/String;Lcom/jarvis/core/companion/PhoneObservation;)V", "getHpId", "()Ljava/lang/String;", "getApp", "()Lcom/jarvis/core/companion/PhoneObservation;", "component1", "component2", "copy", "(Ljava/lang/String;Lcom/jarvis/core/companion/PhoneObservation;)Lcom/jarvis/core/behavior/PatternDetector$detectSequences$Follow;", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Follow {
        private final PhoneObservation app;
        private final String hpId;

        public static /* synthetic */ Follow copy$default(Follow follow, String str, PhoneObservation phoneObservation, int i, Object obj) {
            if ((i & 1) != 0) {
                str = follow.hpId;
            }
            if ((i & 2) != 0) {
                phoneObservation = follow.app;
            }
            return follow.copy(str, phoneObservation);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getHpId() {
            return this.hpId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final PhoneObservation getApp() {
            return this.app;
        }

        public final Follow copy(String hpId, PhoneObservation app) {
            Intrinsics.checkNotNullParameter(hpId, "hpId");
            Intrinsics.checkNotNullParameter(app, "app");
            return new Follow(hpId, app);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Follow)) {
                return false;
            }
            Follow follow = (Follow) other;
            return Intrinsics.areEqual(this.hpId, follow.hpId) && Intrinsics.areEqual(this.app, follow.app);
        }

        public int hashCode() {
            return (this.hpId.hashCode() * 31) + this.app.hashCode();
        }

        public String toString() {
            return "Follow(hpId=" + this.hpId + ", app=" + this.app + ")";
        }

        public Follow(String hpId, PhoneObservation app) {
            Intrinsics.checkNotNullParameter(hpId, "hpId");
            Intrinsics.checkNotNullParameter(app, "app");
            this.hpId = hpId;
            this.app = app;
        }

        public final PhoneObservation getApp() {
            return this.app;
        }

        public final String getHpId() {
            return this.hpId;
        }
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0088  */
    private final List<BehaviorCandidate> detectSequences(ObservationWindow window, long now) {
        BehaviorCandidate behaviorCandidate;
        Object maxElem$iv;
        Object answer$iv$iv$iv;
        Object answer$iv$iv$iv2;
        Object obj;
        boolean z;
        PatternDetector patternDetector = this;
        List<PhoneObservation> observations = window.getObservations();
        List follows = new ArrayList();
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
                            long j = patternDetector.sequenceGapMs;
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
                    follows.add(new Follow(a.getId(), b));
                }
            }
        }
        List $this$distinctBy$iv = follows;
        HashSet set$iv = new HashSet();
        ArrayList list$iv = new ArrayList();
        for (Object e$iv : $this$distinctBy$iv) {
            Follow it3 = (Follow) e$iv;
            if (set$iv.add(it3.getHpId())) {
                list$iv.add(e$iv);
            }
        }
        ArrayList uniqueFollows = list$iv;
        Map $this$mapNotNull$iv = new LinkedHashMap();
        for (Object element$iv$iv : uniqueFollows) {
            Follow it4 = (Follow) element$iv$iv;
            String packageName2 = it4.getApp().getPackageName();
            Intrinsics.checkNotNull(packageName2);
            Object value$iv$iv$iv = $this$mapNotNull$iv.get(packageName2);
            if (value$iv$iv$iv == null) {
                answer$iv$iv$iv2 = new ArrayList();
                $this$mapNotNull$iv.put(packageName2, answer$iv$iv$iv2);
            } else {
                answer$iv$iv$iv2 = value$iv$iv$iv;
            }
            Object key$iv$iv$iv = answer$iv$iv$iv2;
            List list$iv$iv = (List) key$iv$iv$iv;
            list$iv$iv.add(element$iv$iv);
        }
        Map byPkg = $this$mapNotNull$iv;
        int totalSessions = uniqueFollows.size();
        int $i$f$mapNotNull = 0;
        Collection destination$iv$iv = new ArrayList();
        for (Map.Entry element$iv$iv$iv : $this$mapNotNull$iv.entrySet()) {
            String pkg = (String) element$iv$iv$iv.getKey();
            List list = (List) element$iv$iv$iv.getValue();
            List $this$distinctBy$iv2 = list;
            HashSet set$iv2 = new HashSet();
            ArrayList list$iv2 = new ArrayList();
            for (Object e$iv2 : $this$distinctBy$iv2) {
                Follow it5 = (Follow) e$iv2;
                String id = it5.getApp().getId();
                observations = observations;
                HashSet set$iv3 = set$iv2;
                follows = follows;
                if (set$iv3.add(id)) {
                    list$iv2.add(e$iv2);
                    set$iv2 = set$iv3;
                } else {
                    set$iv2 = set$iv3;
                }
            }
            List<PhoneObservation> list2 = observations;
            List follows2 = follows;
            ArrayList $this$map$iv = list$iv2;
            List uniqueFollows2 = uniqueFollows;
            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                Follow it6 = (Follow) item$iv$iv;
                destination$iv$iv2.add(it6.getApp());
            }
            Object element$iv$iv2 = (List) destination$iv$iv2;
            Iterable $this$groupBy$iv = (Iterable) element$iv$iv2;
            int $i$f$groupBy = 0;
            Map destination$iv$iv3 = new LinkedHashMap();
            for (Object element$iv$iv3 : $this$groupBy$iv) {
                Object deduped = element$iv$iv2;
                PhoneObservation it7 = (PhoneObservation) element$iv$iv3;
                String strDayKey$default = Companion.dayKey$default(INSTANCE, it7.getTimestampMillis(), null, 2, null);
                Iterable $this$groupBy$iv2 = $this$groupBy$iv;
                int $i$f$groupBy2 = $i$f$groupBy;
                Object value$iv$iv$iv2 = destination$iv$iv3.get(strDayKey$default);
                if (value$iv$iv$iv2 == null) {
                    answer$iv$iv$iv = new ArrayList();
                    destination$iv$iv3.put(strDayKey$default, answer$iv$iv$iv);
                } else {
                    answer$iv$iv$iv = value$iv$iv$iv2;
                }
                List list$iv$iv2 = (List) answer$iv$iv$iv;
                list$iv$iv2.add(element$iv$iv3);
                element$iv$iv2 = deduped;
                $i$f$groupBy = $i$f$groupBy2;
                $this$groupBy$iv = $this$groupBy$iv2;
                byPkg = byPkg;
            }
            Map byPkg2 = byPkg;
            Map $this$mapValues$iv = destination$iv$iv3;
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
                        PhoneObservation it8 = (PhoneObservation) maxElem$iv;
                        long maxValue$iv = it8.getTimestampMillis();
                        while (true) {
                            Object e$iv3 = iterator$iv.next();
                            PhoneObservation it9 = (PhoneObservation) e$iv3;
                            long v$iv = it9.getTimestampMillis();
                            if (maxValue$iv < v$iv) {
                                maxElem$iv = e$iv3;
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
            if (perDay.size() < patternDetector.minEvidence) {
                behaviorCandidate = null;
            } else {
                Companion companion = INSTANCE;
                List $this$map$iv2 = perDay;
                Collection destination$iv$iv4 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
                for (Object item$iv$iv2 : $this$map$iv2) {
                    PhoneObservation it10 = (PhoneObservation) item$iv$iv2;
                    destination$iv$iv4.add(Long.valueOf(it10.getTimestampMillis()));
                    $this$map$iv2 = $this$map$iv2;
                }
                int days = Companion.uniqueDays$default(companion, (List) destination$iv$iv4, null, 2, null);
                int negative = RangesKt.coerceAtLeast(totalSessions - list.size(), 0);
                ConfidenceModel confidenceModel = ConfidenceModel.INSTANCE;
                int size2 = perDay.size();
                Iterator it11 = perDay.iterator();
                if (!it11.hasNext()) {
                    throw new NoSuchElementException();
                }
                PhoneObservation it12 = (PhoneObservation) it11.next();
                long timestampMillis2 = it12.getTimestampMillis();
                while (it11.hasNext()) {
                    PhoneObservation it13 = (PhoneObservation) it11.next();
                    long timestampMillis3 = it13.getTimestampMillis();
                    if (timestampMillis2 < timestampMillis3) {
                        timestampMillis2 = timestampMillis3;
                    }
                }
                Companion companion2 = INSTANCE;
                List $this$map$iv3 = perDay;
                int $i$f$mapNotNull2 = CollectionsKt.collectionSizeOrDefault($this$map$iv3, 10);
                Collection destination$iv$iv5 = new ArrayList($i$f$mapNotNull2);
                Iterable $this$mapTo$iv$iv = $this$map$iv3;
                for (Object item$iv$iv3 : $this$mapTo$iv$iv) {
                    PhoneObservation it14 = (PhoneObservation) item$iv$iv3;
                    destination$iv$iv5.add(Long.valueOf(it14.getTimestampMillis()));
                    $this$mapTo$iv$iv = $this$mapTo$iv$iv;
                }
                float conf = confidenceModel.score(size2, days, timestampMillis2, now, 0.88f, negative, companion2.regularityByHour((List) destination$iv$iv5));
                String key2 = "seq:HEADPHONES→" + pkg;
                BehaviorKind behaviorKind = BehaviorKind.PATTERN;
                PatternFamily patternFamily = PatternFamily.SEQUENCE;
                String str = "После подключения наушников часто открывается " + pkg;
                int size3 = perDay.size();
                Iterator it15 = perDay.iterator();
                if (!it15.hasNext()) {
                    throw new NoSuchElementException();
                }
                PhoneObservation it16 = (PhoneObservation) it15.next();
                long timestampMillis4 = it16.getTimestampMillis();
                while (it15.hasNext()) {
                    PhoneObservation it17 = (PhoneObservation) it15.next();
                    long timestampMillis5 = it17.getTimestampMillis();
                    if (timestampMillis4 > timestampMillis5) {
                        timestampMillis4 = timestampMillis5;
                    }
                }
                Iterator it18 = perDay.iterator();
                if (!it18.hasNext()) {
                    throw new NoSuchElementException();
                }
                PhoneObservation it19 = (PhoneObservation) it18.next();
                long timestampMillis6 = it19.getTimestampMillis();
                while (it18.hasNext()) {
                    PhoneObservation it20 = (PhoneObservation) it18.next();
                    long timestampMillis7 = it20.getTimestampMillis();
                    if (timestampMillis6 < timestampMillis7) {
                        timestampMillis6 = timestampMillis7;
                    }
                }
                List $this$map$iv4 = perDay;
                int $i$f$map = CollectionsKt.collectionSizeOrDefault($this$map$iv4, 10);
                Collection destination$iv$iv6 = new ArrayList($i$f$map);
                for (Object item$iv$iv4 : $this$map$iv4) {
                    PhoneObservation it21 = (PhoneObservation) item$iv$iv4;
                    destination$iv$iv6.add(it21.getId());
                    $this$map$iv4 = $this$map$iv4;
                }
                behaviorCandidate = new BehaviorCandidate(null, behaviorKind, patternFamily, str, conf, size3, timestampMillis4, timestampMillis6, CollectionsKt.takeLast((List) destination$iv$iv6, 16), null, key2, MapsKt.mapOf(TuplesKt.to("trigger", "HEADPHONES_CONNECTED"), TuplesKt.to("follow", pkg), TuplesKt.to("negative", String.valueOf(negative))), days, negative, perDay.size() + " подтв. / " + negative + " наруш. за " + days + " дн.; окно ≤" + (patternDetector.sequenceGapMs / ((long) 60000)) + " мин", InputDeviceCompat.SOURCE_DPAD, null);
            }
            if (behaviorCandidate != null) {
                destination$iv$iv.add(behaviorCandidate);
            }
            patternDetector = this;
            uniqueFollows = uniqueFollows2;
            $this$mapNotNull$iv = $this$mapNotNull$iv;
            follows = follows2;
            observations = list2;
            totalSessions = totalSessions;
            $i$f$mapNotNull = $i$f$mapNotNull;
            byPkg = byPkg2;
        }
        return (List) destination$iv$iv;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x004e  */
    private final List<BehaviorCandidate> detectFrequency(ObservationWindow window, long now) {
        Object answer$iv$iv$iv;
        boolean z;
        Iterable $this$filter$iv = window.getObservations();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            PhoneObservation it = (PhoneObservation) element$iv$iv;
            if (it.getType() != ObservationType.APP_FOREGROUND) {
                z = false;
            } else {
                String packageName = it.getPackageName();
                if ((packageName == null || StringsKt.isBlank(packageName)) || BehaviorObservationSanitizer.INSTANCE.isNoisyPackage(it.getPackageName())) {
                    z = false;
                } else {
                    z = true;
                }
            }
            if (z) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$groupBy$iv = (List) destination$iv$iv;
        Map destination$iv$iv2 = new LinkedHashMap();
        for (Object element$iv$iv2 : $this$groupBy$iv) {
            PhoneObservation it2 = (PhoneObservation) element$iv$iv2;
            String packageName2 = it2.getPackageName();
            Intrinsics.checkNotNull(packageName2);
            Object value$iv$iv$iv = destination$iv$iv2.get(packageName2);
            if (value$iv$iv$iv == null) {
                answer$iv$iv$iv = new ArrayList();
                destination$iv$iv2.put(packageName2, answer$iv$iv$iv);
            } else {
                answer$iv$iv$iv = value$iv$iv$iv;
            }
            Object key$iv$iv$iv = answer$iv$iv$iv;
            List list$iv$iv = (List) key$iv$iv$iv;
            list$iv$iv.add(element$iv$iv2);
        }
        Map byPkg = destination$iv$iv2;
        Map $this$mapNotNull$iv = byPkg;
        int $i$f$mapNotNull = 0;
        Collection destination$iv$iv3 = new ArrayList();
        Map $this$mapNotNullTo$iv$iv = $this$mapNotNull$iv;
        int $i$f$mapNotNullTo = 0;
        Map $this$forEach$iv$iv$iv = $this$mapNotNullTo$iv$iv;
        for (Map.Entry element$iv$iv$iv : $this$forEach$iv$iv$iv.entrySet()) {
            String pkg = (String) element$iv$iv$iv.getKey();
            Iterable list = (List) element$iv$iv$iv.getValue();
            Iterable $this$distinctBy$iv = list;
            HashSet set$iv = new HashSet();
            ArrayList list$iv = new ArrayList();
            for (Object e$iv : $this$distinctBy$iv) {
                PhoneObservation it3 = (PhoneObservation) e$iv;
                String id = it3.getId();
                byPkg = byPkg;
                HashSet set$iv2 = set$iv;
                $this$mapNotNull$iv = $this$mapNotNull$iv;
                if (set$iv2.add(id)) {
                    list$iv.add(e$iv);
                    set$iv = set$iv2;
                } else {
                    set$iv = set$iv2;
                }
            }
            Map byPkg2 = byPkg;
            Map $this$mapNotNull$iv2 = $this$mapNotNull$iv;
            ArrayList deduped = list$iv;
            ArrayList $this$map$iv = deduped;
            int $i$f$mapNotNull2 = $i$f$mapNotNull;
            Map $this$mapNotNullTo$iv$iv2 = $this$mapNotNullTo$iv$iv;
            int $i$f$mapNotNullTo2 = $i$f$mapNotNullTo;
            int $i$f$mapNotNullTo3 = CollectionsKt.collectionSizeOrDefault($this$map$iv, 10);
            Collection destination$iv$iv4 = new ArrayList($i$f$mapNotNullTo3);
            for (Object item$iv$iv : $this$map$iv) {
                PhoneObservation it4 = (PhoneObservation) item$iv$iv;
                destination$iv$iv4.add(Companion.dayKey$default(INSTANCE, it4.getTimestampMillis(), null, 2, null));
            }
            int dayCount = CollectionsKt.toSet((List) destination$iv$iv4).size();
            BehaviorCandidate behaviorCandidate = null;
            if (deduped.size() >= this.minEvidence + 1 && (dayCount >= 2 || deduped.size() >= 6)) {
                ConfidenceModel confidenceModel = ConfidenceModel.INSTANCE;
                int size = deduped.size();
                Iterator it5 = deduped.iterator();
                if (!it5.hasNext()) {
                    throw new NoSuchElementException();
                }
                PhoneObservation it6 = (PhoneObservation) it5.next();
                long timestampMillis = it6.getTimestampMillis();
                while (it5.hasNext()) {
                    PhoneObservation it7 = (PhoneObservation) it5.next();
                    long timestampMillis2 = it7.getTimestampMillis();
                    if (timestampMillis < timestampMillis2) {
                        timestampMillis = timestampMillis2;
                    }
                }
                Companion companion = INSTANCE;
                ArrayList $this$map$iv2 = deduped;
                Collection destination$iv$iv5 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
                for (Object item$iv$iv2 : $this$map$iv2) {
                    PhoneObservation it8 = (PhoneObservation) item$iv$iv2;
                    destination$iv$iv5.add(Long.valueOf(it8.getTimestampMillis()));
                    $this$map$iv2 = $this$map$iv2;
                }
                float conf = confidenceModel.score(size, dayCount, timestampMillis, now, (32 & 16) != 0 ? 0.85f : 0.85f, (32 & 32) != 0 ? 0 : 0, (32 & 64) != 0 ? 0.5f : companion.regularityByHour((List) destination$iv$iv5));
                BehaviorKind behaviorKind = BehaviorKind.PATTERN;
                PatternFamily patternFamily = PatternFamily.FREQUENCY;
                String str = "Приложение " + pkg + " открывается часто";
                int size2 = deduped.size();
                Iterator it9 = deduped.iterator();
                if (!it9.hasNext()) {
                    throw new NoSuchElementException();
                }
                PhoneObservation it10 = (PhoneObservation) it9.next();
                long timestampMillis3 = it10.getTimestampMillis();
                while (it9.hasNext()) {
                    PhoneObservation it11 = (PhoneObservation) it9.next();
                    long timestampMillis4 = it11.getTimestampMillis();
                    if (timestampMillis3 > timestampMillis4) {
                        timestampMillis3 = timestampMillis4;
                    }
                }
                Iterator it12 = deduped.iterator();
                if (!it12.hasNext()) {
                    throw new NoSuchElementException();
                }
                PhoneObservation it13 = (PhoneObservation) it12.next();
                long timestampMillis5 = it13.getTimestampMillis();
                while (it12.hasNext()) {
                    PhoneObservation it14 = (PhoneObservation) it12.next();
                    long timestampMillis6 = it14.getTimestampMillis();
                    if (timestampMillis5 < timestampMillis6) {
                        timestampMillis5 = timestampMillis6;
                    }
                }
                ArrayList $this$map$iv3 = deduped;
                Collection destination$iv$iv6 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv3, 10));
                for (Object item$iv$iv3 : $this$map$iv3) {
                    PhoneObservation it15 = (PhoneObservation) item$iv$iv3;
                    destination$iv$iv6.add(it15.getId());
                    $this$map$iv3 = $this$map$iv3;
                }
                behaviorCandidate = new BehaviorCandidate(null, behaviorKind, patternFamily, str, conf, size2, timestampMillis3, timestampMillis5, CollectionsKt.takeLast((List) destination$iv$iv6, 16), null, "freq:APP:" + pkg, MapsKt.mapOf(TuplesKt.to("package", pkg)), dayCount, 0, deduped.size() + " открытий за " + dayCount + " дн.", 8705, null);
            }
            if (behaviorCandidate != null) {
                destination$iv$iv3.add(behaviorCandidate);
            }
            $i$f$mapNotNull = $i$f$mapNotNull2;
            $this$mapNotNullTo$iv$iv = $this$mapNotNullTo$iv$iv2;
            $i$f$mapNotNullTo = $i$f$mapNotNullTo2;
            $this$mapNotNull$iv = $this$mapNotNull$iv2;
            byPkg = byPkg2;
            $this$forEach$iv$iv$iv = $this$forEach$iv$iv$iv;
        }
        return (List) destination$iv$iv3;
    }

    private final List<BehaviorCandidate> detectTemporal(ObservationWindow window, long now) {
        String str;
        int $i$f$mapNotNull;
        Map $this$mapNotNullTo$iv$iv;
        BehaviorCandidate behaviorCandidate;
        Object answer$iv;
        Map byHourPkg = new LinkedHashMap();
        Iterator<PhoneObservation> it = window.getObservations().iterator();
        while (true) {
            str = ":";
            if (!it.hasNext()) {
                break;
            }
            PhoneObservation o = it.next();
            if (o.getType() == ObservationType.APP_FOREGROUND) {
                String packageName = o.getPackageName();
                if (!(packageName == null || StringsKt.isBlank(packageName)) && !BehaviorObservationSanitizer.INSTANCE.isNoisyPackage(o.getPackageName())) {
                    String key = "temporal:" + Companion.hourOf$default(INSTANCE, o.getTimestampMillis(), null, 2, null) + ":" + o.getPackageName();
                    Object value$iv = byHourPkg.get(key);
                    if (value$iv == null) {
                        answer$iv = new ArrayList();
                        byHourPkg.put(key, answer$iv);
                    } else {
                        answer$iv = value$iv;
                    }
                    ((List) answer$iv).add(o);
                }
            }
        }
        Map $this$mapNotNull$iv = byHourPkg;
        int $i$f$mapNotNull2 = 0;
        Collection destination$iv$iv = new ArrayList();
        Map $this$mapNotNullTo$iv$iv2 = $this$mapNotNull$iv;
        int $i$f$mapNotNullTo = 0;
        for (Map.Entry element$iv$iv$iv : $this$mapNotNullTo$iv$iv2.entrySet()) {
            String key2 = (String) element$iv$iv$iv.getKey();
            List list = (List) element$iv$iv$iv.getValue();
            Map byHourPkg2 = byHourPkg;
            Map $this$mapNotNull$iv2 = $this$mapNotNull$iv;
            if (list.size() < this.minEvidence) {
                $i$f$mapNotNull = $i$f$mapNotNull2;
                $this$mapNotNullTo$iv$iv = $this$mapNotNullTo$iv$iv2;
                behaviorCandidate = null;
            } else {
                Companion companion = INSTANCE;
                List $this$map$iv = list;
                $i$f$mapNotNull = $i$f$mapNotNull2;
                $this$mapNotNullTo$iv$iv = $this$mapNotNullTo$iv$iv2;
                Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    PhoneObservation it2 = (PhoneObservation) item$iv$iv;
                    destination$iv$iv2.add(Long.valueOf(it2.getTimestampMillis()));
                }
                int days = Companion.uniqueDays$default(companion, (List) destination$iv$iv2, null, 2, null);
                if (days < 2) {
                    behaviorCandidate = null;
                } else {
                    String hour = (String) StringsKt.split$default((CharSequence) key2, new String[]{str}, false, 0, 6, (Object) null).get(1);
                    String pkg = StringsKt.substringAfterLast$default(key2, str, (String) null, 2, (Object) null);
                    ConfidenceModel confidenceModel = ConfidenceModel.INSTANCE;
                    int size = list.size();
                    Iterator it3 = list.iterator();
                    if (!it3.hasNext()) {
                        throw new NoSuchElementException();
                    }
                    PhoneObservation it4 = (PhoneObservation) it3.next();
                    long timestampMillis = it4.getTimestampMillis();
                    while (it3.hasNext()) {
                        PhoneObservation it5 = (PhoneObservation) it3.next();
                        long timestampMillis2 = it5.getTimestampMillis();
                        if (timestampMillis < timestampMillis2) {
                            timestampMillis = timestampMillis2;
                        }
                    }
                    float conf = confidenceModel.score(size, days, timestampMillis, now, (32 & 16) != 0 ? 0.85f : 0.85f, (32 & 32) != 0 ? 0 : 0, (32 & 64) != 0 ? 0.5f : 0.75f);
                    BehaviorKind behaviorKind = BehaviorKind.PATTERN;
                    PatternFamily patternFamily = PatternFamily.TEMPORAL;
                    String str2 = "Около " + hour + ":00 часто открывается " + pkg;
                    int size2 = list.size();
                    Iterator it6 = list.iterator();
                    if (!it6.hasNext()) {
                        throw new NoSuchElementException();
                    }
                    PhoneObservation it7 = (PhoneObservation) it6.next();
                    long timestampMillis3 = it7.getTimestampMillis();
                    while (it6.hasNext()) {
                        PhoneObservation it8 = (PhoneObservation) it6.next();
                        long timestampMillis4 = it8.getTimestampMillis();
                        if (timestampMillis3 > timestampMillis4) {
                            timestampMillis3 = timestampMillis4;
                        }
                    }
                    Iterator it9 = list.iterator();
                    if (!it9.hasNext()) {
                        throw new NoSuchElementException();
                    }
                    PhoneObservation it10 = (PhoneObservation) it9.next();
                    long timestampMillis5 = it10.getTimestampMillis();
                    while (it9.hasNext()) {
                        PhoneObservation it11 = (PhoneObservation) it9.next();
                        long timestampMillis6 = it11.getTimestampMillis();
                        if (timestampMillis5 < timestampMillis6) {
                            timestampMillis5 = timestampMillis6;
                        }
                    }
                    List $this$map$iv2 = list;
                    int $i$f$mapNotNullTo2 = CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10);
                    Collection destination$iv$iv3 = new ArrayList($i$f$mapNotNullTo2);
                    for (Object item$iv$iv2 : $this$map$iv2) {
                        PhoneObservation it12 = (PhoneObservation) item$iv$iv2;
                        destination$iv$iv3.add(it12.getId());
                        $this$map$iv2 = $this$map$iv2;
                    }
                    behaviorCandidate = new BehaviorCandidate(null, behaviorKind, patternFamily, str2, conf, size2, timestampMillis3, timestampMillis5, CollectionsKt.takeLast((List) destination$iv$iv3, 16), null, key2, MapsKt.mapOf(TuplesKt.to("hour", hour), TuplesKt.to("package", pkg)), days, 0, list.size() + " раз около " + hour + ":00 в " + days + " разных днях", 8705, null);
                }
            }
            if (behaviorCandidate != null) {
                destination$iv$iv.add(behaviorCandidate);
            }
            byHourPkg = byHourPkg2;
            $this$mapNotNull$iv = $this$mapNotNull$iv2;
            $i$f$mapNotNull2 = $i$f$mapNotNull;
            $this$mapNotNullTo$iv$iv2 = $this$mapNotNullTo$iv$iv;
            str = str;
            $i$f$mapNotNullTo = $i$f$mapNotNullTo;
        }
        return (List) destination$iv$iv;
    }

    private final List<BehaviorCandidate> detectCoOccurrence(ObservationWindow window, long now) {
        char c;
        String str;
        String str2;
        BehaviorCandidate behaviorCandidate;
        int j;
        Object answer$iv;
        Iterable $this$filter$iv = window.getObservations();
        Collection destination$iv$iv = new ArrayList();
        Iterator it = $this$filter$iv.iterator();
        while (true) {
            c = 0;
            if (!it.hasNext()) {
                break;
            }
            Object element$iv$iv = it.next();
            PhoneObservation it2 = (PhoneObservation) element$iv$iv;
            if (it2.getType() == ObservationType.APP_FOREGROUND) {
                String packageName = it2.getPackageName();
                if (!(packageName == null || StringsKt.isBlank(packageName)) && !BehaviorObservationSanitizer.INSTANCE.isNoisyPackage(it2.getPackageName())) {
                    c = 1;
                }
            }
            if (c != 0) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List apps = (List) destination$iv$iv;
        Map pairCounts = new LinkedHashMap();
        int i = 0;
        int size = apps.size();
        while (true) {
            str = "|";
            str2 = "co:";
            int i2 = 2;
            if (i >= size) {
                break;
            }
            int j2 = i + 1;
            int size2 = apps.size();
            while (j2 < size2) {
                PhoneObservation a = (PhoneObservation) apps.get(i);
                PhoneObservation b = (PhoneObservation) apps.get(j2);
                long dt = Math.abs(b.getTimestampMillis() - a.getTimestampMillis());
                if (dt >= 1) {
                    j = j2;
                    if (dt <= this.coOccurGapMs) {
                        String p1 = a.getPackageName();
                        Intrinsics.checkNotNull(p1);
                        String p2 = b.getPackageName();
                        Intrinsics.checkNotNull(p2);
                        if (!Intrinsics.areEqual(p1, p2)) {
                            String[] strArr = new String[i2];
                            strArr[c] = p1;
                            strArr[1] = p2;
                            String str3 = "co:" + CollectionsKt.joinToString$default(CollectionsKt.sorted(CollectionsKt.listOf((Object[]) strArr)), "|", null, null, 0, null, null, 62, null);
                            Object value$iv = pairCounts.get(str3);
                            if (value$iv == null) {
                                answer$iv = new ArrayList();
                                pairCounts.put(str3, answer$iv);
                            } else {
                                answer$iv = value$iv;
                            }
                            ((List) answer$iv).add(Long.valueOf(Math.min(a.getTimestampMillis(), b.getTimestampMillis())));
                        }
                    }
                } else {
                    j = j2;
                }
                j2 = j + 1;
                apps = apps;
                i2 = 2;
                c = 0;
            }
            i++;
            c = 0;
        }
        Map $this$mapNotNull$iv = pairCounts;
        int $i$f$mapNotNull = 0;
        Collection destination$iv$iv2 = new ArrayList();
        Map $this$mapNotNullTo$iv$iv = $this$mapNotNull$iv;
        int $i$f$mapNotNullTo = 0;
        Map $this$forEach$iv$iv$iv = $this$mapNotNullTo$iv$iv;
        int $i$f$forEach = 0;
        for (Iterator it3 = $this$forEach$iv$iv$iv.entrySet().iterator(); it3.hasNext(); it3 = it3) {
            Map.Entry element$iv$iv$iv = (Map.Entry) it3.next();
            Map $this$mapNotNull$iv2 = $this$mapNotNull$iv;
            String key = (String) element$iv$iv$iv.getKey();
            Map pairCounts2 = pairCounts;
            List times = (List) element$iv$iv$iv.getValue();
            int $i$f$mapNotNull2 = $i$f$mapNotNull;
            int distinct = CollectionsKt.distinct(times).size();
            Map $this$mapNotNullTo$iv$iv2 = $this$mapNotNullTo$iv$iv;
            int $i$f$mapNotNullTo2 = $i$f$mapNotNullTo;
            if (distinct < this.minEvidence) {
                behaviorCandidate = null;
            } else {
                List pkgs = StringsKt.split$default((CharSequence) StringsKt.removePrefix(key, (CharSequence) str2), new String[]{str}, false, 0, 6, (Object) null);
                int days = Companion.uniqueDays$default(INSTANCE, times, null, 2, null);
                ConfidenceModel confidenceModel = ConfidenceModel.INSTANCE;
                Long l = (Long) CollectionsKt.maxOrNull((Iterable) times);
                float conf = confidenceModel.score(distinct, days, l != null ? l.longValue() : now, now, (32 & 16) != 0 ? 0.85f : 0.85f, (32 & 32) != 0 ? 0 : 0, (32 & 64) != 0 ? 0.5f : 0.65f);
                BehaviorKind behaviorKind = BehaviorKind.PATTERN;
                PatternFamily patternFamily = PatternFamily.CO_OCCURRENCE;
                String str4 = pkgs.get(0) + " и " + pkgs.get(1) + " часто открываются рядом";
                Long l2 = (Long) CollectionsKt.minOrNull((Iterable) times);
                long jLongValue = l2 != null ? l2.longValue() : now;
                Long l3 = (Long) CollectionsKt.maxOrNull((Iterable) times);
                behaviorCandidate = new BehaviorCandidate(null, behaviorKind, patternFamily, str4, conf, distinct, jLongValue, l3 != null ? l3.longValue() : now, null, null, key, MapsKt.mapOf(TuplesKt.to("a", pkgs.get(0)), TuplesKt.to("b", pkgs.get(1))), days, 0, distinct + " совместных появлений (≤" + (this.coOccurGapMs / ((long) 60000)) + " мин) за " + days + " дн.", 8961, null);
            }
            if (behaviorCandidate != null) {
                destination$iv$iv2.add(behaviorCandidate);
            }
            $this$mapNotNullTo$iv$iv = $this$mapNotNullTo$iv$iv2;
            $this$mapNotNull$iv = $this$mapNotNull$iv2;
            pairCounts = pairCounts2;
            $i$f$mapNotNull = $i$f$mapNotNull2;
            $i$f$mapNotNullTo = $i$f$mapNotNullTo2;
            str = str;
            str2 = str2;
            $this$forEach$iv$iv$iv = $this$forEach$iv$iv$iv;
            $i$f$forEach = $i$f$forEach;
        }
        return (List) destination$iv$iv2;
    }

    /* JADX WARN: Code duplicated, block: B:106:0x00c4 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0089  */
    /* JADX WARN: Code duplicated, block: B:31:0x009f  */
    /* JADX WARN: Code duplicated, block: B:33:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:35:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:38:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:40:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:45:0x00c6 A[LOOP:1: B:14:0x0055->B:45:0x00c6, LOOP_END] */
    private final List<BehaviorCandidate> detectContextBattery(ObservationWindow window, long now) {
        List<PhoneObservation> list;
        Integer intOrNull;
        Object obj;
        boolean z;
        boolean z2;
        long timestampMillis;
        boolean z3;
        String packageName;
        boolean z4;
        List<PhoneObservation> observations = window.getObservations();
        List hits = new ArrayList();
        int i = 0;
        int size = observations.size();
        while (true) {
            Object obj2 = null;
            if (i >= size) {
                break;
            }
            PhoneObservation bat = observations.get(i);
            if (bat.getType() == ObservationType.BATTERY_CHANGED) {
                String str = bat.getEntities().get("percent");
                if (str == null || (intOrNull = StringsKt.toIntOrNull(str)) == null) {
                    list = observations;
                } else {
                    int pct = intOrNull.intValue();
                    if (pct <= 20) {
                        Iterable $this$firstOrNull$iv = CollectionsKt.drop(observations, i + 1);
                        Iterator it = $this$firstOrNull$iv.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                list = observations;
                                obj = null;
                                break;
                            }
                            Object element$iv = it.next();
                            PhoneObservation it2 = (PhoneObservation) element$iv;
                            if (it2.getType() == ObservationType.APP_FOREGROUND) {
                                String packageName2 = it2.getPackageName();
                                if (packageName2 != null) {
                                    list = observations;
                                    z2 = StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "settings", false, 2, obj2);
                                    if (z2) {
                                        long j = this.sequenceGapMs;
                                        timestampMillis = it2.getTimestampMillis() - bat.getTimestampMillis();
                                        if (1 <= timestampMillis || timestampMillis > j) {
                                            z3 = false;
                                        } else {
                                            z3 = true;
                                        }
                                        z = z3;
                                    } else {
                                        packageName = it2.getPackageName();
                                        if (packageName == null && StringsKt.contains$default((CharSequence) packageName, (CharSequence) "securitycenter", false, 2, obj2)) {
                                            z4 = true;
                                        } else {
                                            z4 = false;
                                        }
                                        if (z4) {
                                            long j2 = this.sequenceGapMs;
                                            timestampMillis = it2.getTimestampMillis() - bat.getTimestampMillis();
                                            if (1 <= timestampMillis) {
                                                z3 = false;
                                            } else {
                                                z3 = false;
                                            }
                                            if (z3) {
                                            }
                                        }
                                    }
                                    if (z) {
                                        obj = element$iv;
                                        break;
                                    }
                                    observations = list;
                                    obj2 = null;
                                } else {
                                    list = observations;
                                }
                                if (z2) {
                                    long j3 = this.sequenceGapMs;
                                    timestampMillis = it2.getTimestampMillis() - bat.getTimestampMillis();
                                    if (1 <= timestampMillis) {
                                        z3 = false;
                                    } else {
                                        z3 = false;
                                    }
                                    if (z3) {
                                    }
                                } else {
                                    packageName = it2.getPackageName();
                                    if (packageName == null) {
                                        z4 = false;
                                    } else {
                                        z4 = false;
                                    }
                                    if (z4) {
                                        long j4 = this.sequenceGapMs;
                                        timestampMillis = it2.getTimestampMillis() - bat.getTimestampMillis();
                                        if (1 <= timestampMillis) {
                                            z3 = false;
                                        } else {
                                            z3 = false;
                                        }
                                        if (z3) {
                                        }
                                    }
                                }
                                if (z) {
                                    obj = element$iv;
                                    break;
                                }
                                observations = list;
                                obj2 = null;
                            } else {
                                list = observations;
                            }
                            if (z) {
                                obj = element$iv;
                                break;
                            }
                            observations = list;
                            obj2 = null;
                        }
                        PhoneObservation follow = (PhoneObservation) obj;
                        if (follow != null) {
                            hits.add(follow);
                        }
                    } else {
                        list = observations;
                    }
                }
            } else {
                list = observations;
            }
            i++;
            observations = list;
        }
        int i2 = hits.size();
        if (i2 < this.minEvidence) {
            return CollectionsKt.emptyList();
        }
        Companion companion = INSTANCE;
        List $this$map$iv = hits;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            PhoneObservation it3 = (PhoneObservation) item$iv$iv;
            destination$iv$iv.add(Long.valueOf(it3.getTimestampMillis()));
        }
        int days = Companion.uniqueDays$default(companion, (List) destination$iv$iv, null, 2, null);
        ConfidenceModel confidenceModel = ConfidenceModel.INSTANCE;
        int size2 = hits.size();
        Iterator it4 = hits.iterator();
        if (!it4.hasNext()) {
            throw new NoSuchElementException();
        }
        PhoneObservation it5 = (PhoneObservation) it4.next();
        long timestampMillis2 = it5.getTimestampMillis();
        while (it4.hasNext()) {
            PhoneObservation it6 = (PhoneObservation) it4.next();
            long timestampMillis3 = it6.getTimestampMillis();
            if (timestampMillis2 < timestampMillis3) {
                timestampMillis2 = timestampMillis3;
            }
        }
        float conf = confidenceModel.score(size2, days, timestampMillis2, now, (32 & 16) != 0 ? 0.85f : 0.8f, (32 & 32) != 0 ? 0 : 0, (32 & 64) != 0 ? 0.5f : 0.5f);
        BehaviorKind behaviorKind = BehaviorKind.PATTERN;
        PatternFamily patternFamily = PatternFamily.CONTEXT;
        int size3 = hits.size();
        Iterator it7 = hits.iterator();
        if (!it7.hasNext()) {
            throw new NoSuchElementException();
        }
        PhoneObservation it8 = (PhoneObservation) it7.next();
        long timestampMillis4 = it8.getTimestampMillis();
        while (it7.hasNext()) {
            PhoneObservation it9 = (PhoneObservation) it7.next();
            long timestampMillis5 = it9.getTimestampMillis();
            if (timestampMillis4 > timestampMillis5) {
                timestampMillis4 = timestampMillis5;
            }
        }
        Iterator it10 = hits.iterator();
        if (!it10.hasNext()) {
            throw new NoSuchElementException();
        }
        PhoneObservation it11 = (PhoneObservation) it10.next();
        long timestampMillis6 = it11.getTimestampMillis();
        while (it10.hasNext()) {
            PhoneObservation it12 = (PhoneObservation) it10.next();
            long timestampMillis7 = it12.getTimestampMillis();
            if (timestampMillis6 < timestampMillis7) {
                timestampMillis6 = timestampMillis7;
            }
        }
        List $this$map$iv2 = hits;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
        for (Object item$iv$iv2 : $this$map$iv2) {
            PhoneObservation it13 = (PhoneObservation) item$iv$iv2;
            destination$iv$iv2.add(it13.getId());
        }
        return CollectionsKt.listOf(new BehaviorCandidate(null, behaviorKind, patternFamily, "При низком заряде часто открываются настройки", conf, size3, timestampMillis4, timestampMillis6, CollectionsKt.takeLast((List) destination$iv$iv2, 12), null, "ctx:BATTERY_LOW→SETTINGS", MapsKt.mapOf(TuplesKt.to("trigger", "BATTERY_LOW"), TuplesKt.to("follow", "settings")), days, 0, hits.size() + " раз после battery≤20% за " + days + " дн.", 8705, null));
    }

    /* JADX INFO: compiled from: PatternDetector.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\nJ\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nJ\u0018\u0010\u000e\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nJ\u0014\u0010\u000f\u001a\u00020\u00102\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/behavior/PatternDetector$Companion;", "", "<init>", "()V", "uniqueDays", "", "timestamps", "", "", "tz", "Ljava/util/TimeZone;", "dayKey", "", "ts", "hourOf", "regularityByHour", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ int uniqueDays$default(Companion companion, List list, TimeZone timeZone, int i, Object obj) {
            if ((i & 2) != 0) {
                timeZone = TimeZone.getDefault();
                Intrinsics.checkNotNullExpressionValue(timeZone, "getDefault(...)");
            }
            return companion.uniqueDays(list, timeZone);
        }

        public final int uniqueDays(List<Long> timestamps, TimeZone tz) {
            Intrinsics.checkNotNullParameter(timestamps, "timestamps");
            Intrinsics.checkNotNullParameter(tz, "tz");
            List<Long> $this$map$iv = timestamps;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                long it = ((Number) item$iv$iv).longValue();
                destination$iv$iv.add(PatternDetector.INSTANCE.dayKey(it, tz));
            }
            return CollectionsKt.toSet((List) destination$iv$iv).size();
        }

        public static /* synthetic */ String dayKey$default(Companion companion, long j, TimeZone timeZone, int i, Object obj) {
            if ((i & 2) != 0) {
                timeZone = TimeZone.getDefault();
                Intrinsics.checkNotNullExpressionValue(timeZone, "getDefault(...)");
            }
            return companion.dayKey(j, timeZone);
        }

        public final String dayKey(long ts, TimeZone tz) {
            Intrinsics.checkNotNullParameter(tz, "tz");
            Calendar cal = Calendar.getInstance(tz);
            cal.setTimeInMillis(ts);
            return cal.get(1) + "-" + cal.get(6);
        }

        public static /* synthetic */ int hourOf$default(Companion companion, long j, TimeZone timeZone, int i, Object obj) {
            if ((i & 2) != 0) {
                timeZone = TimeZone.getDefault();
                Intrinsics.checkNotNullExpressionValue(timeZone, "getDefault(...)");
            }
            return companion.hourOf(j, timeZone);
        }

        public final int hourOf(long ts, TimeZone tz) {
            Intrinsics.checkNotNullParameter(tz, "tz");
            Calendar cal = Calendar.getInstance(tz);
            cal.setTimeInMillis(ts);
            return cal.get(11);
        }

        public final float regularityByHour(List<Long> timestamps) {
            Object obj;
            Intrinsics.checkNotNullParameter(timestamps, "timestamps");
            if (timestamps.size() < 2) {
                return 0.3f;
            }
            List<Long> $this$map$iv = timestamps;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                long it = ((Number) item$iv$iv).longValue();
                destination$iv$iv.add(Integer.valueOf(hourOf$default(PatternDetector.INSTANCE, it, null, 2, null)));
            }
            Iterable hours = (List) destination$iv$iv;
            final Iterable $this$groupingBy$iv = hours;
            Iterator it2 = GroupingKt.eachCount(new Grouping<Integer, Integer>() { // from class: com.jarvis.core.behavior.PatternDetector$Companion$regularityByHour$$inlined$groupingBy$1
                @Override // kotlin.collections.Grouping
                public Iterator<Integer> sourceIterator() {
                    return $this$groupingBy$iv.iterator();
                }

                @Override // kotlin.collections.Grouping
                public Integer keyOf(Integer element) {
                    int it3 = element.intValue();
                    return Integer.valueOf(it3);
                }
            }).entrySet().iterator();
            if (it2.hasNext()) {
                Object next = it2.next();
                if (it2.hasNext()) {
                    Map.Entry it3 = (Map.Entry) next;
                    int iIntValue = ((Number) it3.getValue()).intValue();
                    do {
                        Object next2 = it2.next();
                        Map.Entry it4 = (Map.Entry) next2;
                        int iIntValue2 = ((Number) it4.getValue()).intValue();
                        if (iIntValue < iIntValue2) {
                            next = next2;
                            iIntValue = iIntValue2;
                        }
                    } while (it2.hasNext());
                }
                obj = next;
            } else {
                obj = null;
            }
            Map.Entry entry = (Map.Entry) obj;
            int mode = entry != null ? ((Number) entry.getValue()).intValue() : 1;
            return RangesKt.coerceIn(mode / timestamps.size(), 0.2f, 1.0f);
        }
    }
}
