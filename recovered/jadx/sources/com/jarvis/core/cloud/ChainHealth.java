package com.jarvis.core.cloud;

import com.jarvis.app.VoiceService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: compiled from: ChainHealth.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010$\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001'B\u001f\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\fH\u0002J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u0011J1\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u0017¢\u0006\u0002\u0010\u001fJ\u000e\u0010 \u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010!\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\"\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010#\u001a\u00020\u00142\u0006\u0010$\u001a\u00020\u000bJ\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0&R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/jarvis/core/cloud/ChainHealth;", "", "clock", "Lkotlin/Function0;", "", "config", "Lcom/jarvis/core/cloud/ChainConfig;", "<init>", "(Lkotlin/jvm/functions/Function0;Lcom/jarvis/core/cloud/ChainConfig;)V", "states", "", "", "Lcom/jarvis/core/cloud/ChainHealth$State;", "lock", "Ljava/lang/Object;", "key", VoiceService.EXTRA_ENTRY, "Lcom/jarvis/core/cloud/ChainEntry;", "get", "set", "", "state", "isAvailable", "", "recordSuccess", "recordFailure", "errorClass", "Lcom/jarvis/core/cloud/ErrorClass;", "retryAfterSeconds", "", "paramsExhausted", "(Lcom/jarvis/core/cloud/ChainEntry;Lcom/jarvis/core/cloud/ErrorClass;Ljava/lang/Integer;Z)V", "markProbeInFlight", "tryAcquireProbe", "releaseProbe", "reset", "providerId", "snapshot", "", "State", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ChainHealth {
    private final Function0<Long> clock;
    private final ChainConfig config;
    private final Object lock;
    private final Map<String, State> states;

    /* JADX INFO: compiled from: ChainHealth.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ErrorClass.values().length];
            try {
                iArr[ErrorClass.RATE_LIMITED_DAILY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ErrorClass.RATE_LIMITED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ErrorClass.AUTH_OR_REGION.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[ErrorClass.FORBIDDEN.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[ErrorClass.BLOCKED_BY_CLOUDFLARE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[ErrorClass.BAD_REQUEST.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[ErrorClass.OVERLOADED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[ErrorClass.NETWORK_ERROR.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[ErrorClass.SERVER_ERROR.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[ErrorClass.EMPTY_RESPONSE.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public ChainHealth(Function0<Long> clock, ChainConfig config) {
        Intrinsics.checkNotNullParameter(clock, "clock");
        Intrinsics.checkNotNullParameter(config, "config");
        this.clock = clock;
        this.config = config;
        this.states = new LinkedHashMap();
        this.lock = new Object();
    }

    public /* synthetic */ ChainHealth(Function0 function0, ChainConfig chainConfig, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(function0, (i & 2) != 0 ? ChainConfig.INSTANCE.getDefault() : chainConfig);
    }

    /* JADX INFO: compiled from: ChainHealth.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001c\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BW\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\nHÆ\u0003J\t\u0010 \u001a\u00020\nHÆ\u0003J\t\u0010!\u001a\u00020\nHÆ\u0003JY\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\nHÆ\u0001J\u0013\u0010#\u001a\u00020\n2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\u0007HÖ\u0001J\t\u0010&\u001a\u00020'HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017¨\u0006("}, d2 = {"Lcom/jarvis/core/cloud/ChainHealth$State;", "", "dailyExhaustedUntil", "", "rateLimitedUntil", "blockedUntil", "consecutiveFailures", "", "circuitOpenUntil", "circuitHalfOpen", "", "circuitProbeInFlight", "disabled", "<init>", "(JJJIJZZZ)V", "getDailyExhaustedUntil", "()J", "getRateLimitedUntil", "getBlockedUntil", "getConsecutiveFailures", "()I", "getCircuitOpenUntil", "getCircuitHalfOpen", "()Z", "getCircuitProbeInFlight", "getDisabled", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class State {
        private final long blockedUntil;
        private final boolean circuitHalfOpen;
        private final long circuitOpenUntil;
        private final boolean circuitProbeInFlight;
        private final int consecutiveFailures;
        private final long dailyExhaustedUntil;
        private final boolean disabled;
        private final long rateLimitedUntil;

        public State() {
            this(0L, 0L, 0L, 0, 0L, false, false, false, 255, null);
        }

        public static /* synthetic */ State copy$default(State state, long j, long j2, long j3, int i, long j4, boolean z, boolean z2, boolean z3, int i2, Object obj) {
            return state.copy((i2 & 1) != 0 ? state.dailyExhaustedUntil : j, (i2 & 2) != 0 ? state.rateLimitedUntil : j2, (i2 & 4) != 0 ? state.blockedUntil : j3, (i2 & 8) != 0 ? state.consecutiveFailures : i, (i2 & 16) != 0 ? state.circuitOpenUntil : j4, (i2 & 32) != 0 ? state.circuitHalfOpen : z, (i2 & 64) != 0 ? state.circuitProbeInFlight : z2, (i2 & 128) != 0 ? state.disabled : z3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getDailyExhaustedUntil() {
            return this.dailyExhaustedUntil;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getRateLimitedUntil() {
            return this.rateLimitedUntil;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final long getBlockedUntil() {
            return this.blockedUntil;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getConsecutiveFailures() {
            return this.consecutiveFailures;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final long getCircuitOpenUntil() {
            return this.circuitOpenUntil;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final boolean getCircuitHalfOpen() {
            return this.circuitHalfOpen;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final boolean getCircuitProbeInFlight() {
            return this.circuitProbeInFlight;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final boolean getDisabled() {
            return this.disabled;
        }

        public final State copy(long dailyExhaustedUntil, long rateLimitedUntil, long blockedUntil, int consecutiveFailures, long circuitOpenUntil, boolean circuitHalfOpen, boolean circuitProbeInFlight, boolean disabled) {
            return new State(dailyExhaustedUntil, rateLimitedUntil, blockedUntil, consecutiveFailures, circuitOpenUntil, circuitHalfOpen, circuitProbeInFlight, disabled);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof State)) {
                return false;
            }
            State state = (State) other;
            return this.dailyExhaustedUntil == state.dailyExhaustedUntil && this.rateLimitedUntil == state.rateLimitedUntil && this.blockedUntil == state.blockedUntil && this.consecutiveFailures == state.consecutiveFailures && this.circuitOpenUntil == state.circuitOpenUntil && this.circuitHalfOpen == state.circuitHalfOpen && this.circuitProbeInFlight == state.circuitProbeInFlight && this.disabled == state.disabled;
        }

        public int hashCode() {
            return (((((((((((((Long.hashCode(this.dailyExhaustedUntil) * 31) + Long.hashCode(this.rateLimitedUntil)) * 31) + Long.hashCode(this.blockedUntil)) * 31) + Integer.hashCode(this.consecutiveFailures)) * 31) + Long.hashCode(this.circuitOpenUntil)) * 31) + Boolean.hashCode(this.circuitHalfOpen)) * 31) + Boolean.hashCode(this.circuitProbeInFlight)) * 31) + Boolean.hashCode(this.disabled);
        }

        public String toString() {
            return "State(dailyExhaustedUntil=" + this.dailyExhaustedUntil + ", rateLimitedUntil=" + this.rateLimitedUntil + ", blockedUntil=" + this.blockedUntil + ", consecutiveFailures=" + this.consecutiveFailures + ", circuitOpenUntil=" + this.circuitOpenUntil + ", circuitHalfOpen=" + this.circuitHalfOpen + ", circuitProbeInFlight=" + this.circuitProbeInFlight + ", disabled=" + this.disabled + ")";
        }

        public State(long dailyExhaustedUntil, long rateLimitedUntil, long blockedUntil, int consecutiveFailures, long circuitOpenUntil, boolean circuitHalfOpen, boolean circuitProbeInFlight, boolean disabled) {
            this.dailyExhaustedUntil = dailyExhaustedUntil;
            this.rateLimitedUntil = rateLimitedUntil;
            this.blockedUntil = blockedUntil;
            this.consecutiveFailures = consecutiveFailures;
            this.circuitOpenUntil = circuitOpenUntil;
            this.circuitHalfOpen = circuitHalfOpen;
            this.circuitProbeInFlight = circuitProbeInFlight;
            this.disabled = disabled;
        }

        public /* synthetic */ State(long j, long j2, long j3, int i, long j4, boolean z, boolean z2, boolean z3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? 0L : j, (i2 & 2) != 0 ? 0L : j2, (i2 & 4) != 0 ? 0L : j3, (i2 & 8) != 0 ? 0 : i, (i2 & 16) == 0 ? j4 : 0L, (i2 & 32) != 0 ? false : z, (i2 & 64) != 0 ? false : z2, (i2 & 128) == 0 ? z3 : false);
        }

        public final long getDailyExhaustedUntil() {
            return this.dailyExhaustedUntil;
        }

        public final long getRateLimitedUntil() {
            return this.rateLimitedUntil;
        }

        public final long getBlockedUntil() {
            return this.blockedUntil;
        }

        public final int getConsecutiveFailures() {
            return this.consecutiveFailures;
        }

        public final long getCircuitOpenUntil() {
            return this.circuitOpenUntil;
        }

        public final boolean getCircuitHalfOpen() {
            return this.circuitHalfOpen;
        }

        public final boolean getCircuitProbeInFlight() {
            return this.circuitProbeInFlight;
        }

        public final boolean getDisabled() {
            return this.disabled;
        }
    }

    private final String key(ChainEntry entry) {
        return entry.getProviderId() + "/" + entry.getModelId();
    }

    private final State get(ChainEntry entry) {
        State state;
        Map<String, State> map = this.states;
        String strKey = key(entry);
        State state2 = map.get(strKey);
        if (state2 == null) {
            state = new State(0L, 0L, 0L, 0, 0L, false, false, false, 255, null);
            map.put(strKey, state);
        } else {
            state = state2;
        }
        return state;
    }

    private final void set(ChainEntry entry, State state) {
        this.states.put(key(entry), state);
    }

    public final synchronized boolean isAvailable(ChainEntry entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        long now = this.clock.invoke().longValue();
        State s = get(entry);
        if (s.getDisabled()) {
            return false;
        }
        if (s.getDailyExhaustedUntil() > now) {
            return false;
        }
        if (s.getRateLimitedUntil() > now) {
            return false;
        }
        if (s.getBlockedUntil() > now) {
            return false;
        }
        if (s.getCircuitOpenUntil() > now) {
            return false;
        }
        if (s.getCircuitOpenUntil() <= now && s.getConsecutiveFailures() >= this.config.getCircuitFailureThreshold() && !s.getCircuitHalfOpen()) {
            set(entry, State.copy$default(s, 0L, 0L, 0L, 0, 0L, true, false, false, 159, null));
        }
        State current = get(entry);
        return (current.getCircuitHalfOpen() && current.getCircuitProbeInFlight()) ? false : true;
    }

    public final synchronized void recordSuccess(ChainEntry entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        State s = get(entry);
        set(entry, State.copy$default(s, 0L, 0L, 0L, 0, 0L, false, false, false, 135, null));
    }

    public static /* synthetic */ void recordFailure$default(ChainHealth chainHealth, ChainEntry chainEntry, ErrorClass errorClass, Integer num, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            num = null;
        }
        if ((i & 8) != 0) {
            z = true;
        }
        chainHealth.recordFailure(chainEntry, errorClass, num, z);
    }

    public final synchronized void recordFailure(ChainEntry entry, ErrorClass errorClass, Integer retryAfterSeconds, boolean paramsExhausted) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        Intrinsics.checkNotNullParameter(errorClass, "errorClass");
        long now = this.clock.invoke().longValue();
        State s = get(entry);
        switch (WhenMappings.$EnumSwitchMapping$0[errorClass.ordinal()]) {
            case 1:
                set(entry, State.copy$default(s, now + this.config.getDailyExhaustedCooldownMs(), 0L, 0L, 0, 0L, false, false, false, 254, null));
                break;
            case 2:
                int seconds = RangesKt.coerceIn(retryAfterSeconds != null ? retryAfterSeconds.intValue() : this.config.getRateLimitedDefaultSeconds(), 1, this.config.getRateLimitedMaxSeconds());
                set(entry, State.copy$default(s, 0L, now + (((long) seconds) * 1000), 0L, 0, 0L, false, false, false, 253, null));
                break;
            case 3:
                set(entry, State.copy$default(s, 0L, 0L, 0L, 0, 0L, false, false, true, WorkQueueKt.MASK, null));
                break;
            case 4:
            case 5:
                set(entry, State.copy$default(s, 0L, 0L, now + this.config.getBlockedCooldownMs(), 0, 0L, false, false, false, 251, null));
                break;
            case 6:
                if (paramsExhausted) {
                    int newFailures = s.getConsecutiveFailures() + 1;
                    if (newFailures >= this.config.getCircuitFailureThreshold()) {
                        set(entry, State.copy$default(s, 0L, 0L, 0L, newFailures, now + this.config.getCircuitOpenMs(), false, false, false, 135, null));
                    } else {
                        set(entry, State.copy$default(s, 0L, 0L, 0L, newFailures, 0L, false, false, false, 247, null));
                    }
                }
                break;
            case 7:
            case 8:
            case 9:
            case 10:
                int newFailures2 = s.getConsecutiveFailures() + 1;
                if (newFailures2 >= this.config.getCircuitFailureThreshold()) {
                    set(entry, State.copy$default(s, 0L, 0L, 0L, newFailures2, now + this.config.getCircuitOpenMs(), false, false, false, 135, null));
                } else {
                    set(entry, State.copy$default(s, 0L, 0L, 0L, newFailures2, 0L, false, false, false, 247, null));
                }
                break;
        }
    }

    public final synchronized void markProbeInFlight(ChainEntry entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        State s = get(entry);
        set(entry, State.copy$default(s, 0L, 0L, 0L, 0, 0L, false, true, false, 191, null));
    }

    public final synchronized boolean tryAcquireProbe(ChainEntry entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        if (!isAvailable(entry)) {
            return false;
        }
        State s = get(entry);
        if (s.getCircuitHalfOpen() && !s.getCircuitProbeInFlight()) {
            set(entry, State.copy$default(s, 0L, 0L, 0L, 0, 0L, false, true, false, 191, null));
        }
        return true;
    }

    public final synchronized void releaseProbe(ChainEntry entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        State s = get(entry);
        if (s.getCircuitHalfOpen() || s.getCircuitProbeInFlight()) {
            set(entry, State.copy$default(s, 0L, 0L, 0L, 0, 0L, false, false, false, 191, null));
        }
    }

    public final synchronized void reset(String providerId) {
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        Iterable $this$filter$iv = this.states.keySet();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            if (StringsKt.startsWith$default(it, providerId + "/", false, 2, (Object) null)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable toRemove = (List) destination$iv$iv;
        Iterable $this$forEach$iv = toRemove;
        for (Object element$iv : $this$forEach$iv) {
            String it2 = (String) element$iv;
            this.states.remove(it2);
        }
    }

    public final synchronized Map<String, State> snapshot() {
        return MapsKt.toMap(this.states);
    }
}
