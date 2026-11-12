package com.jarvis.core.companion;

import com.jarvis.app.EventRoutineRunner;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.compress.archivers.zip.UnixStat;

/* JADX INFO: compiled from: ContextAggregator.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001e2\u00020\u0001:\u0002\u001d\u001eB\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0012\u001a\u00020\u0013J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u001a\u0010\u001a\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0018\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/jarvis/core/companion/ContextAggregator;", "", "config", "Lcom/jarvis/core/companion/ContextAggregator$Config;", "<init>", "(Lcom/jarvis/core/companion/ContextAggregator$Config;)V", "lastFgPackage", "", "lastFgAtMillis", "", "lastWindowKey", "lastWindowAtMillis", "lastBatteryKey", "lastBatteryAtMillis", "lastNetworkKey", "lastNetworkAtMillis", "lastNotifKey", "lastNotifAtMillis", "reset", "", "aggregate", "Lcom/jarvis/core/companion/AggregateResult;", "prev", "Lcom/jarvis/core/companion/PhoneContext;", "o", "Lcom/jarvis/core/companion/PhoneObservation;", "dropReason", "rememberAccepted", "apply", "Config", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ContextAggregator {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Config config;
    private long lastBatteryAtMillis;
    private String lastBatteryKey;
    private long lastFgAtMillis;
    private String lastFgPackage;
    private long lastNetworkAtMillis;
    private String lastNetworkKey;
    private long lastNotifAtMillis;
    private String lastNotifKey;
    private long lastWindowAtMillis;
    private String lastWindowKey;

    /* JADX INFO: compiled from: ContextAggregator.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ObservationType.values().length];
            try {
                iArr[ObservationType.APP_FOREGROUND.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ObservationType.WINDOW_CHANGED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ObservationType.BATTERY_CHANGED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[ObservationType.NETWORK_CHANGED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[ObservationType.NOTIFICATION_POSTED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[ObservationType.SCREEN_ON.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[ObservationType.SCREEN_OFF.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[ObservationType.CHARGER_CONNECTED.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[ObservationType.CHARGER_DISCONNECTED.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[ObservationType.HEADPHONES_CONNECTED.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[ObservationType.HEADPHONES_DISCONNECTED.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[ObservationType.NOTIFICATION_REMOVED.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ContextAggregator() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public ContextAggregator(Config config) {
        Intrinsics.checkNotNullParameter(config, "config");
        this.config = config;
    }

    public /* synthetic */ ContextAggregator(Config config, int i, DefaultConstructorMarker defaultConstructorMarker) {
        Config config2;
        if ((i & 1) == 0) {
            config2 = config;
        } else {
            config2 = new Config(0L, 0L, 0L, 0L, 0L, 0L, 63, null);
        }
        this(config2);
    }

    /* JADX INFO: compiled from: ContextAggregator.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003JE\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006 "}, d2 = {"Lcom/jarvis/core/companion/ContextAggregator$Config;", "", "sameFgDebounceMs", "", "windowCoalesceMs", "batteryCoalesceMs", "networkCoalesceMs", "notificationDebounceMs", "usageStatsStaleOverrideMs", "<init>", "(JJJJJJ)V", "getSameFgDebounceMs", "()J", "getWindowCoalesceMs", "getBatteryCoalesceMs", "getNetworkCoalesceMs", "getNotificationDebounceMs", "getUsageStatsStaleOverrideMs", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Config {
        private final long batteryCoalesceMs;
        private final long networkCoalesceMs;
        private final long notificationDebounceMs;
        private final long sameFgDebounceMs;
        private final long usageStatsStaleOverrideMs;
        private final long windowCoalesceMs;

        public Config() {
            this(0L, 0L, 0L, 0L, 0L, 0L, 63, null);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getSameFgDebounceMs() {
            return this.sameFgDebounceMs;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getWindowCoalesceMs() {
            return this.windowCoalesceMs;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final long getBatteryCoalesceMs() {
            return this.batteryCoalesceMs;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final long getNetworkCoalesceMs() {
            return this.networkCoalesceMs;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final long getNotificationDebounceMs() {
            return this.notificationDebounceMs;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final long getUsageStatsStaleOverrideMs() {
            return this.usageStatsStaleOverrideMs;
        }

        public final Config copy(long sameFgDebounceMs, long windowCoalesceMs, long batteryCoalesceMs, long networkCoalesceMs, long notificationDebounceMs, long usageStatsStaleOverrideMs) {
            return new Config(sameFgDebounceMs, windowCoalesceMs, batteryCoalesceMs, networkCoalesceMs, notificationDebounceMs, usageStatsStaleOverrideMs);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Config)) {
                return false;
            }
            Config config = (Config) other;
            return this.sameFgDebounceMs == config.sameFgDebounceMs && this.windowCoalesceMs == config.windowCoalesceMs && this.batteryCoalesceMs == config.batteryCoalesceMs && this.networkCoalesceMs == config.networkCoalesceMs && this.notificationDebounceMs == config.notificationDebounceMs && this.usageStatsStaleOverrideMs == config.usageStatsStaleOverrideMs;
        }

        public int hashCode() {
            return (((((((((Long.hashCode(this.sameFgDebounceMs) * 31) + Long.hashCode(this.windowCoalesceMs)) * 31) + Long.hashCode(this.batteryCoalesceMs)) * 31) + Long.hashCode(this.networkCoalesceMs)) * 31) + Long.hashCode(this.notificationDebounceMs)) * 31) + Long.hashCode(this.usageStatsStaleOverrideMs);
        }

        public String toString() {
            return "Config(sameFgDebounceMs=" + this.sameFgDebounceMs + ", windowCoalesceMs=" + this.windowCoalesceMs + ", batteryCoalesceMs=" + this.batteryCoalesceMs + ", networkCoalesceMs=" + this.networkCoalesceMs + ", notificationDebounceMs=" + this.notificationDebounceMs + ", usageStatsStaleOverrideMs=" + this.usageStatsStaleOverrideMs + ")";
        }

        public Config(long sameFgDebounceMs, long windowCoalesceMs, long batteryCoalesceMs, long networkCoalesceMs, long notificationDebounceMs, long usageStatsStaleOverrideMs) {
            this.sameFgDebounceMs = sameFgDebounceMs;
            this.windowCoalesceMs = windowCoalesceMs;
            this.batteryCoalesceMs = batteryCoalesceMs;
            this.networkCoalesceMs = networkCoalesceMs;
            this.notificationDebounceMs = notificationDebounceMs;
            this.usageStatsStaleOverrideMs = usageStatsStaleOverrideMs;
        }

        public /* synthetic */ Config(long j, long j2, long j3, long j4, long j5, long j6, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? 1500L : j, (i & 2) != 0 ? 2500L : j2, (i & 4) == 0 ? j3 : 2500L, (i & 8) != 0 ? 3000L : j4, (i & 16) != 0 ? 1200L : j5, (i & 32) != 0 ? 45000L : j6);
        }

        public final long getSameFgDebounceMs() {
            return this.sameFgDebounceMs;
        }

        public final long getWindowCoalesceMs() {
            return this.windowCoalesceMs;
        }

        public final long getBatteryCoalesceMs() {
            return this.batteryCoalesceMs;
        }

        public final long getNetworkCoalesceMs() {
            return this.networkCoalesceMs;
        }

        public final long getNotificationDebounceMs() {
            return this.notificationDebounceMs;
        }

        public final long getUsageStatsStaleOverrideMs() {
            return this.usageStatsStaleOverrideMs;
        }
    }

    public final void reset() {
        this.lastFgPackage = null;
        this.lastFgAtMillis = 0L;
        this.lastWindowKey = null;
        this.lastWindowAtMillis = 0L;
        this.lastBatteryKey = null;
        this.lastBatteryAtMillis = 0L;
        this.lastNetworkKey = null;
        this.lastNetworkAtMillis = 0L;
        this.lastNotifKey = null;
        this.lastNotifAtMillis = 0L;
    }

    public final AggregateResult aggregate(PhoneContext prev, PhoneObservation o) {
        Intrinsics.checkNotNullParameter(prev, "prev");
        Intrinsics.checkNotNullParameter(o, "o");
        String drop = dropReason(prev, o);
        if (drop != null) {
            return new AggregateResult(false, prev, drop);
        }
        PhoneContext next = apply(prev, o);
        rememberAccepted(o);
        return new AggregateResult(true, next, null, 4, null);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:95:0x01e0 A[RETURN] */
    private final String dropReason(PhoneContext prev, PhoneObservation o) {
        switch (WhenMappings.$EnumSwitchMapping$0[o.getType().ordinal()]) {
            case 1:
                String pkg = o.getPackageName();
                if (pkg != null) {
                    if (StringsKt.isBlank(pkg)) {
                        pkg = null;
                    }
                    if (pkg != null) {
                        if (o.getSource() == ObservationSource.USAGE_STATS) {
                            boolean a11yFresh = prev.getCurrentAppSource() == ObservationSource.ACCESSIBILITY && prev.getCurrentAppAtMillis() > 0 && o.getTimestampMillis() - prev.getCurrentAppAtMillis() < this.config.getUsageStatsStaleOverrideMs();
                            if (a11yFresh) {
                                if (Intrinsics.areEqual(pkg, prev.getCurrentApp())) {
                                    return "usage_stats_dup_a11y";
                                }
                                return "usage_stats_blocked_by_a11y";
                            }
                            if (Intrinsics.areEqual(pkg, prev.getCurrentApp())) {
                                return "usage_stats_same_app";
                            }
                        }
                        if (!Intrinsics.areEqual(pkg, this.lastFgPackage) || o.getTimestampMillis() - this.lastFgAtMillis >= this.config.getSameFgDebounceMs()) {
                            return null;
                        }
                        return "fg_debounce";
                    }
                }
                return "empty_package";
            case 2:
                String pkg2 = o.getPackageName();
                if (pkg2 != null) {
                    if (StringsKt.isBlank(pkg2)) {
                        pkg2 = null;
                    }
                    if (pkg2 != null) {
                        String screenId = o.getScreenId();
                        if (screenId == null) {
                            screenId = "";
                        }
                        String str = o.getEntities().get("kind");
                        String key = pkg2 + "|" + screenId + "|" + (str != null ? str : "");
                        if (Intrinsics.areEqual(key, this.lastWindowKey) && o.getTimestampMillis() - this.lastWindowAtMillis < this.config.getWindowCoalesceMs()) {
                            return "window_coalesce";
                        }
                        if (Intrinsics.areEqual(pkg2, this.lastFgPackage) && Intrinsics.areEqual(o.getEntities().get("kind"), "window_state") && o.getTimestampMillis() - this.lastFgAtMillis < this.config.getSameFgDebounceMs()) {
                            return "window_after_fg";
                        }
                        return null;
                    }
                }
                return "empty_package";
            case 3:
                String key2 = ((Object) o.getEntities().get("percent")) + "|" + ((Object) o.getEntities().get("charging"));
                if (!Intrinsics.areEqual(key2, this.lastBatteryKey) || o.getTimestampMillis() - this.lastBatteryAtMillis >= this.config.getBatteryCoalesceMs()) {
                    return null;
                }
                return "battery_coalesce";
            case 4:
                String summary = o.getEntities().get(EventRoutineRunner.NETWORK);
                String key3 = (summary == null && (summary = o.getSummary()) == null) ? "" : summary;
                if (!Intrinsics.areEqual(key3, this.lastNetworkKey) || o.getTimestampMillis() - this.lastNetworkAtMillis >= this.config.getNetworkCoalesceMs()) {
                    return null;
                }
                return "network_coalesce";
            case 5:
                String key4 = o.getPackageName() + "|" + o.getSummary();
                if (!Intrinsics.areEqual(key4, this.lastNotifKey) || o.getTimestampMillis() - this.lastNotifAtMillis >= this.config.getNotificationDebounceMs()) {
                    return null;
                }
                return "notif_debounce";
            default:
                return null;
        }
    }

    private final void rememberAccepted(PhoneObservation o) {
        switch (WhenMappings.$EnumSwitchMapping$0[o.getType().ordinal()]) {
            case 1:
                this.lastFgPackage = o.getPackageName();
                this.lastFgAtMillis = o.getTimestampMillis();
                break;
            case 2:
                String packageName = o.getPackageName();
                String screenId = o.getScreenId();
                if (screenId == null) {
                    screenId = "";
                }
                String str = o.getEntities().get("kind");
                this.lastWindowKey = packageName + "|" + screenId + "|" + (str != null ? str : "");
                this.lastWindowAtMillis = o.getTimestampMillis();
                break;
            case 3:
                this.lastBatteryKey = ((Object) o.getEntities().get("percent")) + "|" + ((Object) o.getEntities().get("charging"));
                this.lastBatteryAtMillis = o.getTimestampMillis();
                break;
            case 4:
                String summary = o.getEntities().get(EventRoutineRunner.NETWORK);
                this.lastNetworkKey = (summary == null && (summary = o.getSummary()) == null) ? "" : summary;
                this.lastNetworkAtMillis = o.getTimestampMillis();
                break;
            case 5:
                this.lastNotifKey = o.getPackageName() + "|" + o.getSummary();
                this.lastNotifAtMillis = o.getTimestampMillis();
                break;
        }
    }

    private final PhoneContext apply(PhoneContext prev, PhoneObservation o) {
        PhoneContext phoneContextCopy;
        String summary = o.getSummary();
        if (summary == null) {
            summary = o.getType().name();
        }
        PhoneContext phoneContextCopy2 = prev.copy((UnixStat.PERM_MASK & 1) != 0 ? prev.currentApp : null, (UnixStat.PERM_MASK & 2) != 0 ? prev.previousApp : null, (UnixStat.PERM_MASK & 4) != 0 ? prev.currentAppSource : null, (UnixStat.PERM_MASK & 8) != 0 ? prev.currentAppConfidence : null, (UnixStat.PERM_MASK & 16) != 0 ? prev.currentAppAtMillis : 0L, (UnixStat.PERM_MASK & 32) != 0 ? prev.screenOn : false, (UnixStat.PERM_MASK & 64) != 0 ? prev.batteryPercent : null, (UnixStat.PERM_MASK & 128) != 0 ? prev.charging : false, (UnixStat.PERM_MASK & 256) != 0 ? prev.headphonesConnected : false, (UnixStat.PERM_MASK & 512) != 0 ? prev.networkType : null, (UnixStat.PERM_MASK & 1024) != 0 ? prev.lastNotificationPackage : null, (UnixStat.PERM_MASK & 2048) != 0 ? prev.lastNotificationSummary : null, (UnixStat.PERM_MASK & 4096) != 0 ? prev.lastObservationSummary : summary, (UnixStat.PERM_MASK & 8192) != 0 ? prev.lastObservationAtMillis : o.getTimestampMillis(), (UnixStat.PERM_MASK & 16384) != 0 ? prev.observationCount : prev.getObservationCount() + 1);
        String str = null;
        str = null;
        String str2 = null;
        str = null;
        switch (WhenMappings.$EnumSwitchMapping$0[o.getType().ordinal()]) {
            case 1:
                String packageName = o.getPackageName();
                if (packageName != null && !StringsKt.isBlank(packageName)) {
                    str = packageName;
                }
                String str3 = str;
                if (str3 != null) {
                    if (!Intrinsics.areEqual(str3, prev.getCurrentApp())) {
                        phoneContextCopy = phoneContextCopy2.copy((UnixStat.PERM_MASK & 1) != 0 ? phoneContextCopy2.currentApp : str3, (UnixStat.PERM_MASK & 2) != 0 ? phoneContextCopy2.previousApp : prev.getCurrentApp(), (UnixStat.PERM_MASK & 4) != 0 ? phoneContextCopy2.currentAppSource : o.getSource(), (UnixStat.PERM_MASK & 8) != 0 ? phoneContextCopy2.currentAppConfidence : Float.valueOf(o.getConfidence()), (UnixStat.PERM_MASK & 16) != 0 ? phoneContextCopy2.currentAppAtMillis : o.getTimestampMillis(), (UnixStat.PERM_MASK & 32) != 0 ? phoneContextCopy2.screenOn : false, (UnixStat.PERM_MASK & 64) != 0 ? phoneContextCopy2.batteryPercent : null, (UnixStat.PERM_MASK & 128) != 0 ? phoneContextCopy2.charging : false, (UnixStat.PERM_MASK & 256) != 0 ? phoneContextCopy2.headphonesConnected : false, (UnixStat.PERM_MASK & 512) != 0 ? phoneContextCopy2.networkType : null, (UnixStat.PERM_MASK & 1024) != 0 ? phoneContextCopy2.lastNotificationPackage : null, (UnixStat.PERM_MASK & 2048) != 0 ? phoneContextCopy2.lastNotificationSummary : null, (UnixStat.PERM_MASK & 4096) != 0 ? phoneContextCopy2.lastObservationSummary : null, (UnixStat.PERM_MASK & 8192) != 0 ? phoneContextCopy2.lastObservationAtMillis : 0L, (UnixStat.PERM_MASK & 16384) != 0 ? phoneContextCopy2.observationCount : 0L);
                    } else {
                        phoneContextCopy = phoneContextCopy2.copy((UnixStat.PERM_MASK & 1) != 0 ? phoneContextCopy2.currentApp : str3, (UnixStat.PERM_MASK & 2) != 0 ? phoneContextCopy2.previousApp : null, (UnixStat.PERM_MASK & 4) != 0 ? phoneContextCopy2.currentAppSource : o.getSource(), (UnixStat.PERM_MASK & 8) != 0 ? phoneContextCopy2.currentAppConfidence : Float.valueOf(o.getConfidence()), (UnixStat.PERM_MASK & 16) != 0 ? phoneContextCopy2.currentAppAtMillis : o.getTimestampMillis(), (UnixStat.PERM_MASK & 32) != 0 ? phoneContextCopy2.screenOn : false, (UnixStat.PERM_MASK & 64) != 0 ? phoneContextCopy2.batteryPercent : null, (UnixStat.PERM_MASK & 128) != 0 ? phoneContextCopy2.charging : false, (UnixStat.PERM_MASK & 256) != 0 ? phoneContextCopy2.headphonesConnected : false, (UnixStat.PERM_MASK & 512) != 0 ? phoneContextCopy2.networkType : null, (UnixStat.PERM_MASK & 1024) != 0 ? phoneContextCopy2.lastNotificationPackage : null, (UnixStat.PERM_MASK & 2048) != 0 ? phoneContextCopy2.lastNotificationSummary : null, (UnixStat.PERM_MASK & 4096) != 0 ? phoneContextCopy2.lastObservationSummary : null, (UnixStat.PERM_MASK & 8192) != 0 ? phoneContextCopy2.lastObservationAtMillis : 0L, (UnixStat.PERM_MASK & 16384) != 0 ? phoneContextCopy2.observationCount : 0L);
                    }
                    return phoneContextCopy;
                }
                return phoneContextCopy2;
            case 2:
                String packageName2 = o.getPackageName();
                if (packageName2 != null && !StringsKt.isBlank(packageName2)) {
                    str2 = packageName2;
                }
                String str4 = str2;
                if (str4 != null && !Intrinsics.areEqual(str4, prev.getCurrentApp())) {
                    return phoneContextCopy2.copy((UnixStat.PERM_MASK & 1) != 0 ? phoneContextCopy2.currentApp : str4, (UnixStat.PERM_MASK & 2) != 0 ? phoneContextCopy2.previousApp : prev.getCurrentApp(), (UnixStat.PERM_MASK & 4) != 0 ? phoneContextCopy2.currentAppSource : o.getSource(), (UnixStat.PERM_MASK & 8) != 0 ? phoneContextCopy2.currentAppConfidence : Float.valueOf(o.getConfidence()), (UnixStat.PERM_MASK & 16) != 0 ? phoneContextCopy2.currentAppAtMillis : o.getTimestampMillis(), (UnixStat.PERM_MASK & 32) != 0 ? phoneContextCopy2.screenOn : false, (UnixStat.PERM_MASK & 64) != 0 ? phoneContextCopy2.batteryPercent : null, (UnixStat.PERM_MASK & 128) != 0 ? phoneContextCopy2.charging : false, (UnixStat.PERM_MASK & 256) != 0 ? phoneContextCopy2.headphonesConnected : false, (UnixStat.PERM_MASK & 512) != 0 ? phoneContextCopy2.networkType : null, (UnixStat.PERM_MASK & 1024) != 0 ? phoneContextCopy2.lastNotificationPackage : null, (UnixStat.PERM_MASK & 2048) != 0 ? phoneContextCopy2.lastNotificationSummary : null, (UnixStat.PERM_MASK & 4096) != 0 ? phoneContextCopy2.lastObservationSummary : null, (UnixStat.PERM_MASK & 8192) != 0 ? phoneContextCopy2.lastObservationAtMillis : 0L, (UnixStat.PERM_MASK & 16384) != 0 ? phoneContextCopy2.observationCount : 0L);
                }
                return phoneContextCopy2;
            case 3:
                String str5 = o.getEntities().get("percent");
                Integer intOrNull = str5 != null ? StringsKt.toIntOrNull(str5) : null;
                String str6 = o.getEntities().get("charging");
                Boolean booleanStrictOrNull = str6 != null ? StringsKt.toBooleanStrictOrNull(str6) : null;
                return phoneContextCopy2.copy((UnixStat.PERM_MASK & 1) != 0 ? phoneContextCopy2.currentApp : null, (UnixStat.PERM_MASK & 2) != 0 ? phoneContextCopy2.previousApp : null, (UnixStat.PERM_MASK & 4) != 0 ? phoneContextCopy2.currentAppSource : null, (UnixStat.PERM_MASK & 8) != 0 ? phoneContextCopy2.currentAppConfidence : null, (UnixStat.PERM_MASK & 16) != 0 ? phoneContextCopy2.currentAppAtMillis : 0L, (UnixStat.PERM_MASK & 32) != 0 ? phoneContextCopy2.screenOn : false, (UnixStat.PERM_MASK & 64) != 0 ? phoneContextCopy2.batteryPercent : intOrNull == null ? prev.getBatteryPercent() : intOrNull, (UnixStat.PERM_MASK & 128) != 0 ? phoneContextCopy2.charging : booleanStrictOrNull != null ? booleanStrictOrNull.booleanValue() : prev.getCharging(), (UnixStat.PERM_MASK & 256) != 0 ? phoneContextCopy2.headphonesConnected : false, (UnixStat.PERM_MASK & 512) != 0 ? phoneContextCopy2.networkType : null, (UnixStat.PERM_MASK & 1024) != 0 ? phoneContextCopy2.lastNotificationPackage : null, (UnixStat.PERM_MASK & 2048) != 0 ? phoneContextCopy2.lastNotificationSummary : null, (UnixStat.PERM_MASK & 4096) != 0 ? phoneContextCopy2.lastObservationSummary : null, (UnixStat.PERM_MASK & 8192) != 0 ? phoneContextCopy2.lastObservationAtMillis : 0L, (UnixStat.PERM_MASK & 16384) != 0 ? phoneContextCopy2.observationCount : 0L);
            case 4:
                String summary2 = o.getEntities().get(EventRoutineRunner.NETWORK);
                if (summary2 == null) {
                    summary2 = o.getSummary();
                }
                return phoneContextCopy2.copy((UnixStat.PERM_MASK & 1) != 0 ? phoneContextCopy2.currentApp : null, (UnixStat.PERM_MASK & 2) != 0 ? phoneContextCopy2.previousApp : null, (UnixStat.PERM_MASK & 4) != 0 ? phoneContextCopy2.currentAppSource : null, (UnixStat.PERM_MASK & 8) != 0 ? phoneContextCopy2.currentAppConfidence : null, (UnixStat.PERM_MASK & 16) != 0 ? phoneContextCopy2.currentAppAtMillis : 0L, (UnixStat.PERM_MASK & 32) != 0 ? phoneContextCopy2.screenOn : false, (UnixStat.PERM_MASK & 64) != 0 ? phoneContextCopy2.batteryPercent : null, (UnixStat.PERM_MASK & 128) != 0 ? phoneContextCopy2.charging : false, (UnixStat.PERM_MASK & 256) != 0 ? phoneContextCopy2.headphonesConnected : false, (UnixStat.PERM_MASK & 512) != 0 ? phoneContextCopy2.networkType : summary2, (UnixStat.PERM_MASK & 1024) != 0 ? phoneContextCopy2.lastNotificationPackage : null, (UnixStat.PERM_MASK & 2048) != 0 ? phoneContextCopy2.lastNotificationSummary : null, (UnixStat.PERM_MASK & 4096) != 0 ? phoneContextCopy2.lastObservationSummary : null, (UnixStat.PERM_MASK & 8192) != 0 ? phoneContextCopy2.lastObservationAtMillis : 0L, (UnixStat.PERM_MASK & 16384) != 0 ? phoneContextCopy2.observationCount : 0L);
            case 5:
                String packageName3 = o.getPackageName();
                if (packageName3 == null) {
                    packageName3 = prev.getLastNotificationPackage();
                }
                String str7 = packageName3;
                String summary3 = o.getSummary();
                if (summary3 == null) {
                    summary3 = prev.getLastNotificationSummary();
                }
                return phoneContextCopy2.copy((UnixStat.PERM_MASK & 1) != 0 ? phoneContextCopy2.currentApp : null, (UnixStat.PERM_MASK & 2) != 0 ? phoneContextCopy2.previousApp : null, (UnixStat.PERM_MASK & 4) != 0 ? phoneContextCopy2.currentAppSource : null, (UnixStat.PERM_MASK & 8) != 0 ? phoneContextCopy2.currentAppConfidence : null, (UnixStat.PERM_MASK & 16) != 0 ? phoneContextCopy2.currentAppAtMillis : 0L, (UnixStat.PERM_MASK & 32) != 0 ? phoneContextCopy2.screenOn : false, (UnixStat.PERM_MASK & 64) != 0 ? phoneContextCopy2.batteryPercent : null, (UnixStat.PERM_MASK & 128) != 0 ? phoneContextCopy2.charging : false, (UnixStat.PERM_MASK & 256) != 0 ? phoneContextCopy2.headphonesConnected : false, (UnixStat.PERM_MASK & 512) != 0 ? phoneContextCopy2.networkType : null, (UnixStat.PERM_MASK & 1024) != 0 ? phoneContextCopy2.lastNotificationPackage : str7, (UnixStat.PERM_MASK & 2048) != 0 ? phoneContextCopy2.lastNotificationSummary : summary3, (UnixStat.PERM_MASK & 4096) != 0 ? phoneContextCopy2.lastObservationSummary : null, (UnixStat.PERM_MASK & 8192) != 0 ? phoneContextCopy2.lastObservationAtMillis : 0L, (UnixStat.PERM_MASK & 16384) != 0 ? phoneContextCopy2.observationCount : 0L);
            case 6:
                return phoneContextCopy2.copy((UnixStat.PERM_MASK & 1) != 0 ? phoneContextCopy2.currentApp : null, (UnixStat.PERM_MASK & 2) != 0 ? phoneContextCopy2.previousApp : null, (UnixStat.PERM_MASK & 4) != 0 ? phoneContextCopy2.currentAppSource : null, (UnixStat.PERM_MASK & 8) != 0 ? phoneContextCopy2.currentAppConfidence : null, (UnixStat.PERM_MASK & 16) != 0 ? phoneContextCopy2.currentAppAtMillis : 0L, (UnixStat.PERM_MASK & 32) != 0 ? phoneContextCopy2.screenOn : true, (UnixStat.PERM_MASK & 64) != 0 ? phoneContextCopy2.batteryPercent : null, (UnixStat.PERM_MASK & 128) != 0 ? phoneContextCopy2.charging : false, (UnixStat.PERM_MASK & 256) != 0 ? phoneContextCopy2.headphonesConnected : false, (UnixStat.PERM_MASK & 512) != 0 ? phoneContextCopy2.networkType : null, (UnixStat.PERM_MASK & 1024) != 0 ? phoneContextCopy2.lastNotificationPackage : null, (UnixStat.PERM_MASK & 2048) != 0 ? phoneContextCopy2.lastNotificationSummary : null, (UnixStat.PERM_MASK & 4096) != 0 ? phoneContextCopy2.lastObservationSummary : null, (UnixStat.PERM_MASK & 8192) != 0 ? phoneContextCopy2.lastObservationAtMillis : 0L, (UnixStat.PERM_MASK & 16384) != 0 ? phoneContextCopy2.observationCount : 0L);
            case 7:
                return phoneContextCopy2.copy((UnixStat.PERM_MASK & 1) != 0 ? phoneContextCopy2.currentApp : null, (UnixStat.PERM_MASK & 2) != 0 ? phoneContextCopy2.previousApp : null, (UnixStat.PERM_MASK & 4) != 0 ? phoneContextCopy2.currentAppSource : null, (UnixStat.PERM_MASK & 8) != 0 ? phoneContextCopy2.currentAppConfidence : null, (UnixStat.PERM_MASK & 16) != 0 ? phoneContextCopy2.currentAppAtMillis : 0L, (UnixStat.PERM_MASK & 32) != 0 ? phoneContextCopy2.screenOn : false, (UnixStat.PERM_MASK & 64) != 0 ? phoneContextCopy2.batteryPercent : null, (UnixStat.PERM_MASK & 128) != 0 ? phoneContextCopy2.charging : false, (UnixStat.PERM_MASK & 256) != 0 ? phoneContextCopy2.headphonesConnected : false, (UnixStat.PERM_MASK & 512) != 0 ? phoneContextCopy2.networkType : null, (UnixStat.PERM_MASK & 1024) != 0 ? phoneContextCopy2.lastNotificationPackage : null, (UnixStat.PERM_MASK & 2048) != 0 ? phoneContextCopy2.lastNotificationSummary : null, (UnixStat.PERM_MASK & 4096) != 0 ? phoneContextCopy2.lastObservationSummary : null, (UnixStat.PERM_MASK & 8192) != 0 ? phoneContextCopy2.lastObservationAtMillis : 0L, (UnixStat.PERM_MASK & 16384) != 0 ? phoneContextCopy2.observationCount : 0L);
            case 8:
                return phoneContextCopy2.copy((UnixStat.PERM_MASK & 1) != 0 ? phoneContextCopy2.currentApp : null, (UnixStat.PERM_MASK & 2) != 0 ? phoneContextCopy2.previousApp : null, (UnixStat.PERM_MASK & 4) != 0 ? phoneContextCopy2.currentAppSource : null, (UnixStat.PERM_MASK & 8) != 0 ? phoneContextCopy2.currentAppConfidence : null, (UnixStat.PERM_MASK & 16) != 0 ? phoneContextCopy2.currentAppAtMillis : 0L, (UnixStat.PERM_MASK & 32) != 0 ? phoneContextCopy2.screenOn : false, (UnixStat.PERM_MASK & 64) != 0 ? phoneContextCopy2.batteryPercent : null, (UnixStat.PERM_MASK & 128) != 0 ? phoneContextCopy2.charging : true, (UnixStat.PERM_MASK & 256) != 0 ? phoneContextCopy2.headphonesConnected : false, (UnixStat.PERM_MASK & 512) != 0 ? phoneContextCopy2.networkType : null, (UnixStat.PERM_MASK & 1024) != 0 ? phoneContextCopy2.lastNotificationPackage : null, (UnixStat.PERM_MASK & 2048) != 0 ? phoneContextCopy2.lastNotificationSummary : null, (UnixStat.PERM_MASK & 4096) != 0 ? phoneContextCopy2.lastObservationSummary : null, (UnixStat.PERM_MASK & 8192) != 0 ? phoneContextCopy2.lastObservationAtMillis : 0L, (UnixStat.PERM_MASK & 16384) != 0 ? phoneContextCopy2.observationCount : 0L);
            case 9:
                return phoneContextCopy2.copy((UnixStat.PERM_MASK & 1) != 0 ? phoneContextCopy2.currentApp : null, (UnixStat.PERM_MASK & 2) != 0 ? phoneContextCopy2.previousApp : null, (UnixStat.PERM_MASK & 4) != 0 ? phoneContextCopy2.currentAppSource : null, (UnixStat.PERM_MASK & 8) != 0 ? phoneContextCopy2.currentAppConfidence : null, (UnixStat.PERM_MASK & 16) != 0 ? phoneContextCopy2.currentAppAtMillis : 0L, (UnixStat.PERM_MASK & 32) != 0 ? phoneContextCopy2.screenOn : false, (UnixStat.PERM_MASK & 64) != 0 ? phoneContextCopy2.batteryPercent : null, (UnixStat.PERM_MASK & 128) != 0 ? phoneContextCopy2.charging : false, (UnixStat.PERM_MASK & 256) != 0 ? phoneContextCopy2.headphonesConnected : false, (UnixStat.PERM_MASK & 512) != 0 ? phoneContextCopy2.networkType : null, (UnixStat.PERM_MASK & 1024) != 0 ? phoneContextCopy2.lastNotificationPackage : null, (UnixStat.PERM_MASK & 2048) != 0 ? phoneContextCopy2.lastNotificationSummary : null, (UnixStat.PERM_MASK & 4096) != 0 ? phoneContextCopy2.lastObservationSummary : null, (UnixStat.PERM_MASK & 8192) != 0 ? phoneContextCopy2.lastObservationAtMillis : 0L, (UnixStat.PERM_MASK & 16384) != 0 ? phoneContextCopy2.observationCount : 0L);
            case 10:
                return phoneContextCopy2.copy((UnixStat.PERM_MASK & 1) != 0 ? phoneContextCopy2.currentApp : null, (UnixStat.PERM_MASK & 2) != 0 ? phoneContextCopy2.previousApp : null, (UnixStat.PERM_MASK & 4) != 0 ? phoneContextCopy2.currentAppSource : null, (UnixStat.PERM_MASK & 8) != 0 ? phoneContextCopy2.currentAppConfidence : null, (UnixStat.PERM_MASK & 16) != 0 ? phoneContextCopy2.currentAppAtMillis : 0L, (UnixStat.PERM_MASK & 32) != 0 ? phoneContextCopy2.screenOn : false, (UnixStat.PERM_MASK & 64) != 0 ? phoneContextCopy2.batteryPercent : null, (UnixStat.PERM_MASK & 128) != 0 ? phoneContextCopy2.charging : false, (UnixStat.PERM_MASK & 256) != 0 ? phoneContextCopy2.headphonesConnected : true, (UnixStat.PERM_MASK & 512) != 0 ? phoneContextCopy2.networkType : null, (UnixStat.PERM_MASK & 1024) != 0 ? phoneContextCopy2.lastNotificationPackage : null, (UnixStat.PERM_MASK & 2048) != 0 ? phoneContextCopy2.lastNotificationSummary : null, (UnixStat.PERM_MASK & 4096) != 0 ? phoneContextCopy2.lastObservationSummary : null, (UnixStat.PERM_MASK & 8192) != 0 ? phoneContextCopy2.lastObservationAtMillis : 0L, (UnixStat.PERM_MASK & 16384) != 0 ? phoneContextCopy2.observationCount : 0L);
            case 11:
                return phoneContextCopy2.copy((UnixStat.PERM_MASK & 1) != 0 ? phoneContextCopy2.currentApp : null, (UnixStat.PERM_MASK & 2) != 0 ? phoneContextCopy2.previousApp : null, (UnixStat.PERM_MASK & 4) != 0 ? phoneContextCopy2.currentAppSource : null, (UnixStat.PERM_MASK & 8) != 0 ? phoneContextCopy2.currentAppConfidence : null, (UnixStat.PERM_MASK & 16) != 0 ? phoneContextCopy2.currentAppAtMillis : 0L, (UnixStat.PERM_MASK & 32) != 0 ? phoneContextCopy2.screenOn : false, (UnixStat.PERM_MASK & 64) != 0 ? phoneContextCopy2.batteryPercent : null, (UnixStat.PERM_MASK & 128) != 0 ? phoneContextCopy2.charging : false, (UnixStat.PERM_MASK & 256) != 0 ? phoneContextCopy2.headphonesConnected : false, (UnixStat.PERM_MASK & 512) != 0 ? phoneContextCopy2.networkType : null, (UnixStat.PERM_MASK & 1024) != 0 ? phoneContextCopy2.lastNotificationPackage : null, (UnixStat.PERM_MASK & 2048) != 0 ? phoneContextCopy2.lastNotificationSummary : null, (UnixStat.PERM_MASK & 4096) != 0 ? phoneContextCopy2.lastObservationSummary : null, (UnixStat.PERM_MASK & 8192) != 0 ? phoneContextCopy2.lastObservationAtMillis : 0L, (UnixStat.PERM_MASK & 16384) != 0 ? phoneContextCopy2.observationCount : 0L);
            case 12:
                if (o.getPackageName() != null && Intrinsics.areEqual(o.getPackageName(), prev.getLastNotificationPackage())) {
                    return phoneContextCopy2.copy((UnixStat.PERM_MASK & 1) != 0 ? phoneContextCopy2.currentApp : null, (UnixStat.PERM_MASK & 2) != 0 ? phoneContextCopy2.previousApp : null, (UnixStat.PERM_MASK & 4) != 0 ? phoneContextCopy2.currentAppSource : null, (UnixStat.PERM_MASK & 8) != 0 ? phoneContextCopy2.currentAppConfidence : null, (UnixStat.PERM_MASK & 16) != 0 ? phoneContextCopy2.currentAppAtMillis : 0L, (UnixStat.PERM_MASK & 32) != 0 ? phoneContextCopy2.screenOn : false, (UnixStat.PERM_MASK & 64) != 0 ? phoneContextCopy2.batteryPercent : null, (UnixStat.PERM_MASK & 128) != 0 ? phoneContextCopy2.charging : false, (UnixStat.PERM_MASK & 256) != 0 ? phoneContextCopy2.headphonesConnected : false, (UnixStat.PERM_MASK & 512) != 0 ? phoneContextCopy2.networkType : null, (UnixStat.PERM_MASK & 1024) != 0 ? phoneContextCopy2.lastNotificationPackage : null, (UnixStat.PERM_MASK & 2048) != 0 ? phoneContextCopy2.lastNotificationSummary : null, (UnixStat.PERM_MASK & 4096) != 0 ? phoneContextCopy2.lastObservationSummary : null, (UnixStat.PERM_MASK & 8192) != 0 ? phoneContextCopy2.lastObservationAtMillis : 0L, (UnixStat.PERM_MASK & 16384) != 0 ? phoneContextCopy2.observationCount : 0L);
                }
                return phoneContextCopy2;
            default:
                return phoneContextCopy2;
        }
    }

    /* JADX INFO: compiled from: ContextAggregator.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u00020\u0005*\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/jarvis/core/companion/ContextAggregator$Companion;", "", "<init>", "()V", "priority", "", "Lcom/jarvis/core/companion/ObservationSource;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: ContextAggregator.kt */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[ObservationSource.values().length];
                try {
                    iArr[ObservationSource.ACCESSIBILITY.ordinal()] = 1;
                } catch (NoSuchFieldError e) {
                }
                try {
                    iArr[ObservationSource.USAGE_STATS.ordinal()] = 2;
                } catch (NoSuchFieldError e2) {
                }
                try {
                    iArr[ObservationSource.SYSTEM_BROADCAST.ordinal()] = 3;
                } catch (NoSuchFieldError e3) {
                }
                try {
                    iArr[ObservationSource.NOTIFICATION_LISTENER.ordinal()] = 4;
                } catch (NoSuchFieldError e4) {
                }
                try {
                    iArr[ObservationSource.VOICE.ordinal()] = 5;
                } catch (NoSuchFieldError e5) {
                }
                try {
                    iArr[ObservationSource.TOOL.ordinal()] = 6;
                } catch (NoSuchFieldError e6) {
                }
                try {
                    iArr[ObservationSource.INTERNAL.ordinal()] = 7;
                } catch (NoSuchFieldError e7) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int priority(ObservationSource $this$priority) {
            Intrinsics.checkNotNullParameter($this$priority, "<this>");
            switch (WhenMappings.$EnumSwitchMapping$0[$this$priority.ordinal()]) {
                case 1:
                    return 30;
                case 2:
                    return 10;
                case 3:
                    return 20;
                case 4:
                    return 15;
                case 5:
                case 6:
                case 7:
                    return 5;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
    }
}
