package com.jarvis.core.proactive;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.jarvis.core.behavior.BehaviorCandidate;
import com.jarvis.core.behavior.BehaviorKind;
import com.jarvis.core.behavior.PatternFamily;
import com.jarvis.core.companion.ContextSnapshot;
import com.jarvis.core.companion.PhoneContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ContextMatcher.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00020\u0001B!\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0018\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0018\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u0017H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/jarvis/core/proactive/ContextMatcher;", "", "weights", "Lcom/jarvis/core/proactive/ContextMatchWeights;", "timeZone", "Lkotlin/Function0;", "Ljava/util/TimeZone;", "<init>", "(Lcom/jarvis/core/proactive/ContextMatchWeights;Lkotlin/jvm/functions/Function0;)V", "match", "Lcom/jarvis/core/proactive/ContextMatchResult;", "candidate", "Lcom/jarvis/core/behavior/BehaviorCandidate;", "snapshot", "Lcom/jarvis/core/companion/ContextSnapshot;", "isAlreadySatisfied", "", "shouldUseHour", "appsCompatible", "current", "", "expected", "hourOf", "", "ts", "", "hourDiff", "a", "b", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ContextMatcher {
    private final Function0<TimeZone> timeZone;
    private final ContextMatchWeights weights;

    /* JADX WARN: Multi-variable type inference failed */
    public ContextMatcher() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ContextMatcher(ContextMatchWeights weights, Function0<? extends TimeZone> timeZone) {
        Intrinsics.checkNotNullParameter(weights, "weights");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        this.weights = weights;
        this.timeZone = timeZone;
    }

    public /* synthetic */ ContextMatcher(ContextMatchWeights contextMatchWeights, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ContextMatchWeights(0.0f, 0.0f, 0.0f, 0.0f, 0, 31, null) : contextMatchWeights, (i & 2) != 0 ? new Function0() { // from class: com.jarvis.core.proactive.ContextMatcher$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TimeZone.getDefault();
            }
        } : function0);
    }

    /* JADX WARN: Code duplicated, block: B:100:0x02e0 A[LOOP:1: B:85:0x0292->B:100:0x02e0, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:103:0x02f4  */
    /* JADX WARN: Code duplicated, block: B:108:0x0308  */
    /* JADX WARN: Code duplicated, block: B:111:0x0312  */
    /* JADX WARN: Code duplicated, block: B:113:0x0323 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:116:0x032a  */
    /* JADX WARN: Code duplicated, block: B:122:0x0335  */
    /* JADX WARN: Code duplicated, block: B:123:0x0337  */
    /* JADX WARN: Code duplicated, block: B:127:0x033d A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:132:0x034b  */
    /* JADX WARN: Code duplicated, block: B:135:0x0351  */
    /* JADX WARN: Code duplicated, block: B:136:0x0359  */
    /* JADX WARN: Code duplicated, block: B:139:0x032d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:140:0x0330 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:143:? A[LOOP:0: B:109:0x030c->B:143:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:144:0x02dd A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:145:0x02e9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:84:0x028e  */
    /* JADX WARN: Code duplicated, block: B:87:0x0298  */
    /* JADX WARN: Code duplicated, block: B:89:0x02a8  */
    /* JADX WARN: Code duplicated, block: B:91:0x02b2  */
    /* JADX WARN: Code duplicated, block: B:94:0x02c6  */
    /* JADX WARN: Code duplicated, block: B:95:0x02c8  */
    /* JADX WARN: Code duplicated, block: B:96:0x02d1  */
    public final ContextMatchResult match(BehaviorCandidate candidate, ContextSnapshot snapshot) {
        String str;
        List mismatched;
        boolean z;
        float weighted;
        float weightSum;
        Object obj;
        boolean z2;
        List $this$any$iv;
        Iterator it;
        boolean z3;
        String it2;
        String pkgOnly;
        ContextMatchWeights w;
        Integer hourEntity;
        Map<String, String> map;
        boolean z4;
        String pkgOnly2;
        boolean z5;
        boolean z6;
        boolean matchedFinal;
        float fMin;
        List $this$any$iv2;
        Iterator it3;
        boolean z7;
        Object element$iv;
        boolean z8;
        Intrinsics.checkNotNullParameter(candidate, "candidate");
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        ContextMatchWeights w2 = this.weights.normalized();
        PhoneContext ctx = snapshot.getContext();
        Map<String, String> entities = candidate.getEntities();
        List matched = new ArrayList();
        List mismatched2 = new ArrayList();
        float weighted2 = 0.0f;
        float weightSum2 = 0.0f;
        boolean alreadySatisfied = isAlreadySatisfied(candidate, snapshot);
        String trigger = entities.get("trigger");
        if (trigger != null) {
            weightSum2 = 0.0f + w2.getTrigger();
            if (Intrinsics.areEqual(trigger, "HEADPHONES_CONNECTED")) {
                if (ctx.getHeadphonesConnected()) {
                    weighted2 = 0.0f + w2.getTrigger();
                    matched.add("headphones");
                } else {
                    mismatched2.add("headphones");
                }
            } else if (Intrinsics.areEqual(trigger, "BATTERY_LOW")) {
                Integer pct = ctx.getBatteryPercent();
                if (pct == null || pct.intValue() > 20) {
                    mismatched2.add("battery_low");
                } else {
                    weighted2 = 0.0f + w2.getTrigger();
                    matched.add("battery_low");
                }
            } else {
                weightSum2 -= w2.getTrigger();
            }
        }
        String follow = entities.get("follow");
        if (follow == null) {
            follow = entities.get(TypedValues.TransitionType.S_TO);
        }
        String str2 = follow;
        if (!(str2 == null || StringsKt.isBlank(str2))) {
            weightSum2 += w2.getFollowApp();
            if (alreadySatisfied) {
                mismatched2.add("follow_already_open");
            } else {
                String currentApp = ctx.getCurrentApp();
                if (currentApp == null || StringsKt.isBlank(currentApp)) {
                    weighted2 += w2.getFollowApp() * 0.55f;
                    matched.add("follow_pending:" + follow);
                } else {
                    weighted2 += w2.getFollowApp() * 0.85f;
                    matched.add("follow_pending:" + follow);
                }
            }
        }
        String str3 = entities.get("hour");
        Integer hourEntity2 = str3 != null ? StringsKt.toIntOrNull(str3) : null;
        if (hourEntity2 == null || !shouldUseHour(candidate)) {
            str = "battery_low";
            mismatched = mismatched2;
            z = true;
        } else {
            float weightSum3 = weightSum2 + w2.getHour();
            mismatched = mismatched2;
            int nowHour = hourOf(snapshot.getTakenAtMillis());
            int diff = hourDiff(nowHour, hourEntity2.intValue());
            str = "battery_low";
            if (diff <= w2.getHourTolerance()) {
                z = true;
                float factor = 1.0f - (diff / (w2.getHourTolerance() + 1));
                weighted2 += w2.getHour() * RangesKt.coerceIn(factor, 0.4f, 1.0f);
                matched.add("hour~" + hourEntity2 + "(now=" + nowHour + ")");
                weightSum2 = weightSum3;
            } else {
                z = true;
                mismatched.add("hour~" + hourEntity2 + "(now=" + nowHour + ")");
                weightSum2 = weightSum3;
            }
        }
        String pkgOnly3 = entities.get("package");
        if (pkgOnly3 != null && trigger == null && follow == null) {
            float weightSum4 = weightSum2 + w2.getPackageEntity();
            if (alreadySatisfied) {
                mismatched.add("package_already_fg");
                weighted = weighted2;
                weightSum = weightSum4;
            } else {
                float weighted3 = weighted2 + (w2.getPackageEntity() * 0.35f);
                mismatched.add("package_only_weak:" + pkgOnly3);
                weighted = weighted3;
                weightSum = weightSum4;
            }
        } else {
            weighted = weighted2;
            weightSum = weightSum2;
        }
        if (weightSum <= 0.0f) {
            return new ContextMatchResult(false, 0.0f, matched, CollectionsKt.plus((Collection<? extends String>) mismatched, "no_matchable_entities"), alreadySatisfied);
        }
        float score = RangesKt.coerceIn(weighted / weightSum, 0.0f, 1.0f);
        boolean headphonesRequired = Intrinsics.areEqual(trigger, "HEADPHONES_CONNECTED");
        if (headphonesRequired) {
            obj = "headphones";
            if (!matched.contains(obj)) {
                z2 = false;
            }
            boolean headphonesOk = z2;
            $this$any$iv = matched;
            if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
                it = $this$any$iv.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z3 = false;
                        break;
                    }
                    Object element$iv2 = it.next();
                    it2 = (String) element$iv2;
                    if (Intrinsics.areEqual(it2, obj)) {
                        pkgOnly = pkgOnly3;
                        w = w2;
                        hourEntity = hourEntity2;
                        map = entities;
                    } else {
                        pkgOnly = pkgOnly3;
                        pkgOnly2 = str;
                        if (Intrinsics.areEqual(it2, pkgOnly2)) {
                            str = pkgOnly2;
                            w = w2;
                            hourEntity = hourEntity2;
                            map = entities;
                        } else {
                            str = pkgOnly2;
                            w = w2;
                            hourEntity = hourEntity2;
                            map = entities;
                            if (!StringsKt.startsWith$default(it2, "follow_pending", false, 2, (Object) null)) {
                                z4 = false;
                            }
                            if (z4) {
                                z3 = z;
                                break;
                            }
                            pkgOnly3 = pkgOnly;
                            w2 = w;
                            hourEntity2 = hourEntity;
                            entities = map;
                        }
                    }
                    z4 = z;
                    if (z4) {
                        z3 = z;
                        break;
                    }
                    pkgOnly3 = pkgOnly;
                    w2 = w;
                    hourEntity2 = hourEntity;
                    entities = map;
                }
            } else {
                z3 = false;
            }
            if (!z3) {
                $this$any$iv2 = matched;
                if (!($this$any$iv2 instanceof Collection) || !$this$any$iv2.isEmpty()) {
                    it3 = $this$any$iv2.iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            z5 = false;
                            z7 = false;
                            break;
                        }
                        element$iv = it3.next();
                        z5 = false;
                        if (StringsKt.startsWith$default((String) element$iv, "hour~", false, 2, (Object) null) || (trigger == null && follow == null)) {
                            z8 = false;
                        } else {
                            z8 = z;
                        }
                        if (z8) {
                            z7 = z;
                            break;
                        }
                    }
                } else {
                    z7 = false;
                    z5 = false;
                }
                if (z7) {
                    z6 = z5;
                }
                boolean hasStrongSignal = z6;
                if (alreadySatisfied && headphonesOk && hasStrongSignal && score >= 0.4f) {
                    matchedFinal = z;
                } else {
                    matchedFinal = z5;
                }
                if (alreadySatisfied) {
                    fMin = Math.min(score, 0.25f);
                } else {
                    fMin = score;
                }
                return new ContextMatchResult(matchedFinal, fMin, matched, mismatched, alreadySatisfied);
            }
            z5 = false;
            z6 = z;
            boolean hasStrongSignal2 = z6;
            if (alreadySatisfied) {
                matchedFinal = z5;
            } else {
                matchedFinal = z5;
            }
            if (alreadySatisfied) {
                fMin = Math.min(score, 0.25f);
            } else {
                fMin = score;
            }
            return new ContextMatchResult(matchedFinal, fMin, matched, mismatched, alreadySatisfied);
        }
        obj = r11;
        z2 = z;
        boolean headphonesOk2 = z2;
        $this$any$iv = matched;
        if (!($this$any$iv instanceof Collection)) {
            it = $this$any$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z3 = false;
                    break;
                }
                Object element$iv3 = it.next();
                it2 = (String) element$iv3;
                if (Intrinsics.areEqual(it2, obj)) {
                    pkgOnly = pkgOnly3;
                    pkgOnly2 = str;
                    if (Intrinsics.areEqual(it2, pkgOnly2)) {
                        str = pkgOnly2;
                        w = w2;
                        hourEntity = hourEntity2;
                        map = entities;
                        if (!StringsKt.startsWith$default(it2, "follow_pending", false, 2, (Object) null)) {
                            z4 = false;
                        }
                        if (z4) {
                            z3 = z;
                            break;
                        }
                        pkgOnly3 = pkgOnly;
                        w2 = w;
                        hourEntity2 = hourEntity;
                        entities = map;
                    } else {
                        str = pkgOnly2;
                        w = w2;
                        hourEntity = hourEntity2;
                        map = entities;
                    }
                } else {
                    pkgOnly = pkgOnly3;
                    w = w2;
                    hourEntity = hourEntity2;
                    map = entities;
                }
                z4 = z;
                if (z4) {
                    z3 = z;
                    break;
                }
                pkgOnly3 = pkgOnly;
                w2 = w;
                hourEntity2 = hourEntity;
                entities = map;
            }
        } else {
            it = $this$any$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z3 = false;
                    break;
                }
                Object element$iv4 = it.next();
                it2 = (String) element$iv4;
                if (Intrinsics.areEqual(it2, obj)) {
                    pkgOnly = pkgOnly3;
                    pkgOnly2 = str;
                    if (Intrinsics.areEqual(it2, pkgOnly2)) {
                        str = pkgOnly2;
                        w = w2;
                        hourEntity = hourEntity2;
                        map = entities;
                        if (!StringsKt.startsWith$default(it2, "follow_pending", false, 2, (Object) null)) {
                            z4 = false;
                        }
                        if (z4) {
                            z3 = z;
                            break;
                        }
                        pkgOnly3 = pkgOnly;
                        w2 = w;
                        hourEntity2 = hourEntity;
                        entities = map;
                    } else {
                        str = pkgOnly2;
                        w = w2;
                        hourEntity = hourEntity2;
                        map = entities;
                    }
                } else {
                    pkgOnly = pkgOnly3;
                    w = w2;
                    hourEntity = hourEntity2;
                    map = entities;
                }
                z4 = z;
                if (z4) {
                    z3 = z;
                    break;
                }
                pkgOnly3 = pkgOnly;
                w2 = w;
                hourEntity2 = hourEntity;
                entities = map;
            }
        }
        if (!z3) {
            $this$any$iv2 = matched;
            if (!($this$any$iv2 instanceof Collection)) {
                it3 = $this$any$iv2.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        z5 = false;
                        z7 = false;
                        break;
                    }
                    element$iv = it3.next();
                    z5 = false;
                    if (StringsKt.startsWith$default((String) element$iv, "hour~", false, 2, (Object) null)) {
                        z8 = false;
                    } else {
                        z8 = false;
                    }
                    if (z8) {
                        z7 = z;
                        break;
                    }
                }
            } else {
                it3 = $this$any$iv2.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        z5 = false;
                        z7 = false;
                        break;
                    }
                    element$iv = it3.next();
                    z5 = false;
                    if (StringsKt.startsWith$default((String) element$iv, "hour~", false, 2, (Object) null)) {
                        z8 = false;
                    } else {
                        z8 = false;
                    }
                    if (z8) {
                        z7 = z;
                        break;
                    }
                }
            }
            if (z7) {
                z6 = z5;
            }
            boolean hasStrongSignal3 = z6;
            if (alreadySatisfied) {
                matchedFinal = z5;
            } else {
                matchedFinal = z5;
            }
            if (alreadySatisfied) {
                fMin = Math.min(score, 0.25f);
            } else {
                fMin = score;
            }
            return new ContextMatchResult(matchedFinal, fMin, matched, mismatched, alreadySatisfied);
        }
        z5 = false;
        z6 = z;
        boolean hasStrongSignal4 = z6;
        if (alreadySatisfied) {
            matchedFinal = z5;
        } else {
            matchedFinal = z5;
        }
        if (alreadySatisfied) {
            fMin = Math.min(score, 0.25f);
        } else {
            fMin = score;
        }
        return new ContextMatchResult(matchedFinal, fMin, matched, mismatched, alreadySatisfied);
    }

    public final boolean isAlreadySatisfied(BehaviorCandidate candidate, ContextSnapshot snapshot) {
        String current;
        Intrinsics.checkNotNullParameter(candidate, "candidate");
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        String expected = candidate.getEntities().get("follow");
        if ((expected == null && (expected = candidate.getEntities().get(TypedValues.TransitionType.S_TO)) == null && (expected = candidate.getEntities().get("package")) == null) || (current = snapshot.getContext().getCurrentApp()) == null) {
            return false;
        }
        return appsCompatible(current, expected);
    }

    private final boolean shouldUseHour(BehaviorCandidate candidate) {
        if (candidate.getEntities().get("hour") == null) {
            return false;
        }
        return candidate.getFamily() == PatternFamily.TEMPORAL || candidate.getKind() == BehaviorKind.ROUTINE;
    }

    private final boolean appsCompatible(String current, String expected) {
        if (Intrinsics.areEqual(current, expected)) {
            return true;
        }
        if (expected.length() < 4 || !StringsKt.contains((CharSequence) current, (CharSequence) expected, true)) {
            return current.length() >= 4 && StringsKt.contains((CharSequence) expected, (CharSequence) current, true);
        }
        return true;
    }

    private final int hourOf(long ts) {
        Calendar cal = Calendar.getInstance(this.timeZone.invoke());
        cal.setTimeInMillis(ts);
        return cal.get(11);
    }

    private final int hourDiff(int a, int b) {
        int d = Math.abs(a - b);
        return Math.min(d, 24 - d);
    }
}
