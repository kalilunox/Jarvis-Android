package com.jarvis.core.proactive;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PhoneStateSnapshot.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/jarvis/core/proactive/DeliveryDecider;", "", "<init>", "()V", "decide", "Lcom/jarvis/core/proactive/DeliveryDecision;", "type", "Lcom/jarvis/core/proactive/ProactiveDecisionType;", "state", "Lcom/jarvis/core/proactive/PhoneStateSnapshot;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DeliveryDecider {
    public static final DeliveryDecider INSTANCE = new DeliveryDecider();

    /* JADX INFO: compiled from: PhoneStateSnapshot.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[InterruptionKind.values().length];
            try {
                iArr[InterruptionKind.PRIORITY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[InterruptionKind.ALARMS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[InterruptionKind.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private DeliveryDecider() {
    }

    public final DeliveryDecision decide(ProactiveDecisionType type, PhoneStateSnapshot state) {
        DeliveryReason deliveryReason;
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(state, "state");
        if (type == ProactiveDecisionType.NO_OP) {
            return new DeliveryDecision(DeliveryMode.SUPPRESS, DeliveryReason.NO_OP);
        }
        if (!state.getNotificationsAllowed()) {
            return new DeliveryDecision(DeliveryMode.SUPPRESS, DeliveryReason.NOTIFICATIONS_DISABLED);
        }
        if (state.getJarvisSessionActive()) {
            return new DeliveryDecision(DeliveryMode.SUPPRESS, DeliveryReason.JARVIS_SESSION_ACTIVE);
        }
        if (state.getJarvisSpeaking()) {
            return new DeliveryDecision(DeliveryMode.SUPPRESS, DeliveryReason.JARVIS_SPEAKING);
        }
        if (state.getCallActive()) {
            return new DeliveryDecision(DeliveryMode.SILENT_NOTIFICATION_ONLY, DeliveryReason.CALL_ACTIVE);
        }
        if (state.getRingerMode() == RingerKind.SILENT) {
            return new DeliveryDecision(DeliveryMode.SILENT_NOTIFICATION_ONLY, DeliveryReason.RINGER_SILENT);
        }
        if (state.getRingerMode() == RingerKind.VIBRATE) {
            return new DeliveryDecision(DeliveryMode.SILENT_NOTIFICATION_ONLY, DeliveryReason.RINGER_VIBRATE);
        }
        if (state.getInterruptionFilter() != InterruptionKind.ALL) {
            DeliveryMode deliveryMode = DeliveryMode.SILENT_NOTIFICATION_ONLY;
            switch (WhenMappings.$EnumSwitchMapping$0[state.getInterruptionFilter().ordinal()]) {
                case 1:
                    deliveryReason = DeliveryReason.DND_PRIORITY;
                    break;
                case 2:
                    deliveryReason = DeliveryReason.DND_ALARMS;
                    break;
                case 3:
                    deliveryReason = DeliveryReason.DND_NONE;
                    break;
                default:
                    deliveryReason = DeliveryReason.DND_UNKNOWN;
                    break;
            }
            return new DeliveryDecision(deliveryMode, deliveryReason);
        }
        if (!state.getVoiceAvailable()) {
            return new DeliveryDecision(DeliveryMode.SILENT_NOTIFICATION_ONLY, DeliveryReason.VOICE_UNAVAILABLE);
        }
        return new DeliveryDecision(DeliveryMode.VOICE_AND_NOTIFICATION, DeliveryReason.READY);
    }
}
