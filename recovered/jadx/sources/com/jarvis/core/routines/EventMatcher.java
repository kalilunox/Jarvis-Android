package com.jarvis.core.routines;

import androidx.core.app.NotificationCompat;
import com.jarvis.app.EventRoutineRunner;
import com.jarvis.core.companion.ObservationType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EventRoutines.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 #2\u00020\u0001:\u0001#BI\u00126\u0010\u0002\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ*\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00120\u00152\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00120\u00152\u0006\u0010\u0019\u001a\u00020\u000bJ\u0018\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0018\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u000bH\u0002J\u0016\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0018\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0002J\u0018\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0002R>\u0010\u0002\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0010\u001a\u001e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000b0\u0011j\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000b`\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/jarvis/core/routines/EventMatcher;", "", "packageMatches", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "appName", "packageName", "", "cooldownMs", "", "<init>", "(Lkotlin/jvm/functions/Function2;J)V", EventRoutineRunner.NETWORK, "foreground", "firedAt", "Ljava/util/HashMap;", "Lcom/jarvis/core/routines/EventRule;", "Lkotlin/collections/HashMap;", "match", "", NotificationCompat.CATEGORY_EVENT, "Lcom/jarvis/core/routines/PhoneEvent;", "rules", "nowMs", "appFits", "rule", "cooled", "triggers", "", "Lcom/jarvis/core/routines/EventTrigger;", "networkChange", "next", "appChange", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class EventMatcher {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final long DEFAULT_COOLDOWN_MS = 60000;

    @Deprecated
    public static final String WIFI = "wifi";
    private final long cooldownMs;
    private final HashMap<EventRule, Long> firedAt;
    private String foreground;
    private String network;
    private final Function2<String, String, Boolean> packageMatches;

    /* JADX INFO: compiled from: EventRoutines.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ObservationType.values().length];
            try {
                iArr[ObservationType.HEADPHONES_CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ObservationType.HEADPHONES_DISCONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ObservationType.CHARGER_CONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[ObservationType.CHARGER_DISCONNECTED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[ObservationType.DEVICE_UNLOCKED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[ObservationType.NETWORK_CHANGED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[ObservationType.APP_FOREGROUND.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public EventMatcher(Function2<? super String, ? super String, Boolean> packageMatches, long cooldownMs) {
        Intrinsics.checkNotNullParameter(packageMatches, "packageMatches");
        this.packageMatches = packageMatches;
        this.cooldownMs = cooldownMs;
        this.firedAt = new HashMap<>();
    }

    public /* synthetic */ EventMatcher(Function2 function2, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(function2, (i & 2) != 0 ? 60000L : j);
    }

    public final List<EventRule> match(PhoneEvent event, List<EventRule> rules, long nowMs) {
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(rules, "rules");
        Set<EventTrigger> setTriggers = triggers(event);
        if (event.getStartup() || setTriggers.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        List<EventRule> $this$filter$iv = rules;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            EventRule it = (EventRule) element$iv$iv;
            if (setTriggers.contains(it.getTrigger()) && appFits(it, event) && cooled(it, nowMs)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List hit = (List) destination$iv$iv;
        List $this$forEach$iv = hit;
        for (Object element$iv : $this$forEach$iv) {
            this.firedAt.put((EventRule) element$iv, Long.valueOf(nowMs));
        }
        return hit;
    }

    private final boolean appFits(EventRule rule, PhoneEvent event) {
        if (rule.getTrigger() == EventTrigger.APP_OPEN) {
            Function2<String, String, Boolean> function2 = this.packageMatches;
            String arg = rule.getArg();
            String packageName = event.getPackageName();
            if (packageName == null) {
                packageName = "";
            }
            if (!function2.invoke(arg, packageName).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    private final boolean cooled(EventRule rule, long nowMs) {
        Long l = this.firedAt.get(rule);
        if (l == null) {
            return true;
        }
        long last = l.longValue();
        return nowMs - last >= this.cooldownMs;
    }

    private final Set<EventTrigger> triggers(PhoneEvent event) {
        switch (WhenMappings.$EnumSwitchMapping$0[event.getType().ordinal()]) {
            case 1:
                return SetsKt.setOf(EventTrigger.HEADPHONES_ON);
            case 2:
                return SetsKt.setOf(EventTrigger.HEADPHONES_OFF);
            case 3:
                return SetsKt.setOf(EventTrigger.CHARGER_ON);
            case 4:
                return SetsKt.setOf(EventTrigger.CHARGER_OFF);
            case 5:
                return SetsKt.setOf(EventTrigger.UNLOCK);
            case 6:
                return networkChange(event.getNetwork());
            case 7:
                return appChange(event.getPackageName());
            default:
                return SetsKt.emptySet();
        }
    }

    private final Set<EventTrigger> networkChange(String next) {
        String previous = this.network;
        this.network = next;
        if (previous == null || Intrinsics.areEqual(previous, next)) {
            return SetsKt.emptySet();
        }
        if (Intrinsics.areEqual(next, WIFI)) {
            return SetsKt.setOf(EventTrigger.WIFI_ON);
        }
        return Intrinsics.areEqual(previous, WIFI) ? SetsKt.setOf(EventTrigger.WIFI_OFF) : SetsKt.emptySet();
    }

    private final Set<EventTrigger> appChange(String next) {
        boolean z = true;
        boolean changed = !Intrinsics.areEqual(next, this.foreground);
        this.foreground = next;
        if (changed) {
            String str = next;
            if (str != null && str.length() != 0) {
                z = false;
            }
            if (!z) {
                return SetsKt.setOf(EventTrigger.APP_OPEN);
            }
        }
        return SetsKt.emptySet();
    }

    /* JADX INFO: compiled from: EventRoutines.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/jarvis/core/routines/EventMatcher$Companion;", "", "<init>", "()V", "DEFAULT_COOLDOWN_MS", "", "WIFI", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
