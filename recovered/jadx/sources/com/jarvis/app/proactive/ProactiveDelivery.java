package com.jarvis.app.proactive;

import android.content.Context;
import android.util.Log;
import com.google.android.material.internal.ViewUtils;
import com.jarvis.app.VoiceService;
import com.jarvis.core.proactive.DeliveryDecider;
import com.jarvis.core.proactive.DeliveryDecision;
import com.jarvis.core.proactive.DeliveryMode;
import com.jarvis.core.proactive.DeliveryReason;
import com.jarvis.core.proactive.PhoneStateSnapshot;
import com.jarvis.core.proactive.ProactiveDecision;
import com.jarvis.core.proactive.ProactiveDecisionType;
import com.jarvis.core.proactive.ProactiveText;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ProactiveDelivery.kt */
/* JADX INFO: loaded from: classes23.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001 B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u0007J8\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0005J2\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u00052\b\u0010\u001a\u001a\u0004\u0018\u00010\u0005JN\u0010\u001b\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00052\b\u0010\u001a\u001a\u0004\u0018\u00010\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/jarvis/app/proactive/ProactiveDelivery;", "", "<init>", "()V", "TAG", "", "SNOOZE_MS", "", "sequence", "Ljava/util/concurrent/atomic/AtomicLong;", "newDeliveryId", "now", "deliver", "Lcom/jarvis/app/proactive/ProactiveDelivery$Outcome;", "context", "Landroid/content/Context;", "decision", "Lcom/jarvis/core/proactive/ProactiveDecision;", VoiceService.EXTRA_TEXT, "Lcom/jarvis/core/proactive/ProactiveText;", "fakeState", "modeOverride", "handleAction", "action", ProactiveActionReceiver.EXTRA_DELIVERY_ID, ProactiveActionReceiver.EXTRA_PATTERN_KEY, ProactiveActionReceiver.EXTRA_FINGERPRINT, "suppress", "reason", "Lcom/jarvis/core/proactive/DeliveryReason;", "duplicate", "", "Outcome", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ProactiveDelivery {
    private static final long SNOOZE_MS = 3600000;
    private static final String TAG = "Jarvis.ProactiveDelivery";
    public static final ProactiveDelivery INSTANCE = new ProactiveDelivery();
    private static final AtomicLong sequence = new AtomicLong(0);

    private ProactiveDelivery() {
    }

    /* JADX INFO: compiled from: ProactiveDelivery.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0018\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001b\u001a\u00020\tHÆ\u0003J\t\u0010\u001c\u001a\u00020\tHÆ\u0003J\t\u0010\u001d\u001a\u00020\tHÆ\u0003JE\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001f\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015¨\u0006$"}, d2 = {"Lcom/jarvis/app/proactive/ProactiveDelivery$Outcome;", "", ProactiveActionReceiver.EXTRA_DELIVERY_ID, "", "mode", "Lcom/jarvis/core/proactive/DeliveryMode;", "reason", "Lcom/jarvis/core/proactive/DeliveryReason;", "notificationShown", "", "voiceRequested", "duplicate", "<init>", "(Ljava/lang/String;Lcom/jarvis/core/proactive/DeliveryMode;Lcom/jarvis/core/proactive/DeliveryReason;ZZZ)V", "getDeliveryId", "()Ljava/lang/String;", "getMode", "()Lcom/jarvis/core/proactive/DeliveryMode;", "getReason", "()Lcom/jarvis/core/proactive/DeliveryReason;", "getNotificationShown", "()Z", "getVoiceRequested", "getDuplicate", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Outcome {
        private final String deliveryId;
        private final boolean duplicate;
        private final DeliveryMode mode;
        private final boolean notificationShown;
        private final DeliveryReason reason;
        private final boolean voiceRequested;

        public static /* synthetic */ Outcome copy$default(Outcome outcome, String str, DeliveryMode deliveryMode, DeliveryReason deliveryReason, boolean z, boolean z2, boolean z3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = outcome.deliveryId;
            }
            if ((i & 2) != 0) {
                deliveryMode = outcome.mode;
            }
            DeliveryMode deliveryMode2 = deliveryMode;
            if ((i & 4) != 0) {
                deliveryReason = outcome.reason;
            }
            DeliveryReason deliveryReason2 = deliveryReason;
            if ((i & 8) != 0) {
                z = outcome.notificationShown;
            }
            boolean z4 = z;
            if ((i & 16) != 0) {
                z2 = outcome.voiceRequested;
            }
            boolean z5 = z2;
            if ((i & 32) != 0) {
                z3 = outcome.duplicate;
            }
            return outcome.copy(str, deliveryMode2, deliveryReason2, z4, z5, z3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getDeliveryId() {
            return this.deliveryId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final DeliveryMode getMode() {
            return this.mode;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final DeliveryReason getReason() {
            return this.reason;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getNotificationShown() {
            return this.notificationShown;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getVoiceRequested() {
            return this.voiceRequested;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final boolean getDuplicate() {
            return this.duplicate;
        }

        public final Outcome copy(String deliveryId, DeliveryMode mode, DeliveryReason reason, boolean notificationShown, boolean voiceRequested, boolean duplicate) {
            Intrinsics.checkNotNullParameter(deliveryId, "deliveryId");
            Intrinsics.checkNotNullParameter(mode, "mode");
            Intrinsics.checkNotNullParameter(reason, "reason");
            return new Outcome(deliveryId, mode, reason, notificationShown, voiceRequested, duplicate);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Outcome)) {
                return false;
            }
            Outcome outcome = (Outcome) other;
            return Intrinsics.areEqual(this.deliveryId, outcome.deliveryId) && this.mode == outcome.mode && this.reason == outcome.reason && this.notificationShown == outcome.notificationShown && this.voiceRequested == outcome.voiceRequested && this.duplicate == outcome.duplicate;
        }

        public int hashCode() {
            return (((((((((this.deliveryId.hashCode() * 31) + this.mode.hashCode()) * 31) + this.reason.hashCode()) * 31) + Boolean.hashCode(this.notificationShown)) * 31) + Boolean.hashCode(this.voiceRequested)) * 31) + Boolean.hashCode(this.duplicate);
        }

        public String toString() {
            return "Outcome(deliveryId=" + this.deliveryId + ", mode=" + this.mode + ", reason=" + this.reason + ", notificationShown=" + this.notificationShown + ", voiceRequested=" + this.voiceRequested + ", duplicate=" + this.duplicate + ")";
        }

        public Outcome(String deliveryId, DeliveryMode mode, DeliveryReason reason, boolean notificationShown, boolean voiceRequested, boolean duplicate) {
            Intrinsics.checkNotNullParameter(deliveryId, "deliveryId");
            Intrinsics.checkNotNullParameter(mode, "mode");
            Intrinsics.checkNotNullParameter(reason, "reason");
            this.deliveryId = deliveryId;
            this.mode = mode;
            this.reason = reason;
            this.notificationShown = notificationShown;
            this.voiceRequested = voiceRequested;
            this.duplicate = duplicate;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public /* synthetic */ Outcome(String str, DeliveryMode deliveryMode, DeliveryReason deliveryReason, boolean z, boolean z2, boolean z3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            boolean z4;
            if ((i & 32) == 0) {
                z4 = z3;
            } else {
                z4 = false;
            }
            this(str, deliveryMode, deliveryReason, z, z2, z4);
        }

        public final String getDeliveryId() {
            return this.deliveryId;
        }

        public final DeliveryMode getMode() {
            return this.mode;
        }

        public final DeliveryReason getReason() {
            return this.reason;
        }

        public final boolean getNotificationShown() {
            return this.notificationShown;
        }

        public final boolean getVoiceRequested() {
            return this.voiceRequested;
        }

        public final boolean getDuplicate() {
            return this.duplicate;
        }
    }

    public static /* synthetic */ String newDeliveryId$default(ProactiveDelivery proactiveDelivery, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = System.currentTimeMillis();
        }
        return proactiveDelivery.newDeliveryId(j);
    }

    public final String newDeliveryId(long now) {
        return "pd-" + now + "-" + sequence.incrementAndGet();
    }

    public final Outcome deliver(Context context, ProactiveDecision decision, ProactiveText text, String fakeState, String modeOverride) {
        String lowerCase;
        DeliveryDecision resolved;
        String deliveryId;
        String fingerprint;
        long now;
        ProactiveVoice.Result voice;
        String string;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(decision, "decision");
        Intrinsics.checkNotNullParameter(text, "text");
        long now2 = System.currentTimeMillis();
        String deliveryId2 = newDeliveryId(now2);
        String fingerprint2 = decision.getFingerprint();
        String patternKey = decision.getBehaviorPatternKey();
        PhoneStateSnapshot base = PhoneStateReader.INSTANCE.read(context);
        PhoneStateSnapshot state = PhoneStateReader.INSTANCE.applyFake(base, fakeState);
        DeliveryDecision resolved2 = DeliveryDecider.INSTANCE.decide(decision.getType(), state);
        if (modeOverride == null || (string = StringsKt.trim((CharSequence) modeOverride).toString()) == null) {
            lowerCase = null;
        } else {
            lowerCase = string.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        }
        if (lowerCase == null) {
            lowerCase = "";
        }
        String requestedMode = lowerCase;
        if (Intrinsics.areEqual(requestedMode, "silent") && resolved2.getMode() != DeliveryMode.SUPPRESS) {
            resolved = new DeliveryDecision(DeliveryMode.SILENT_NOTIFICATION_ONLY, resolved2.getReason());
        } else if (Intrinsics.areEqual(requestedMode, "voice") && resolved2.getMode() != DeliveryMode.SUPPRESS) {
            resolved = new DeliveryDecision(DeliveryMode.VOICE_AND_NOTIFICATION, resolved2.getReason());
        } else {
            resolved = resolved2;
        }
        if (decision.getType() == ProactiveDecisionType.NO_OP) {
            deliveryId = deliveryId2;
            fingerprint = fingerprint2;
            now = now2;
        } else {
            if (ProactiveDeliveryLog.INSTANCE.isSnoozed(context, fingerprint2, patternKey, now2)) {
                return suppress$default(this, context, deliveryId2, fingerprint2, patternKey, decision, DeliveryReason.SNOOZED, now2, false, 128, null);
            }
            deliveryId = deliveryId2;
            fingerprint = fingerprint2;
            now = now2;
            if (ProactiveDeliveryLog.INSTANCE.hasDelivered(context, fingerprint)) {
                return suppress(context, deliveryId, fingerprint, patternKey, decision, DeliveryReason.DUPLICATE, now, true);
            }
        }
        if (resolved.getMode() == DeliveryMode.SUPPRESS) {
            return suppress$default(this, context, deliveryId, fingerprint, patternKey, decision, resolved.getReason(), now, false, 128, null);
        }
        boolean notificationShown = ProactiveNotification.INSTANCE.show(context, deliveryId, text, patternKey, fingerprint);
        ProactiveVoice.Result voice2 = new ProactiveVoice.Result(false, "silent_only");
        if (resolved.getMode() != DeliveryMode.VOICE_AND_NOTIFICATION) {
            voice = voice2;
        } else {
            ProactiveVoice.Result voice3 = ProactiveVoice.INSTANCE.speak(context, text.getVoice());
            voice = voice3;
        }
        ProactiveDeliveryLog.INSTANCE.append(context, deliveryId, fingerprint, patternKey, resolved.getMode(), resolved.getReason(), decision.getReason(), voice.getRequested(), (ViewUtils.EDGE_TO_EDGE_FLAGS & 256) != 0 ? null : null, (ViewUtils.EDGE_TO_EDGE_FLAGS & 512) != 0 ? null : null, (ViewUtils.EDGE_TO_EDGE_FLAGS & 1024) != 0 ? System.currentTimeMillis() : now);
        ProactiveDecisionType type = decision.getType();
        String str = fakeState == null ? PhoneStateReader.FAKE_NONE : fakeState;
        String deliveryId3 = deliveryId;
        Log.i(TAG, "deliver id=" + deliveryId3 + " type=" + type + " fake=" + str + " mode=" + resolved.getMode() + " reason=" + resolved.getReason() + " notification=" + notificationShown + " voiceRequested=" + voice.getRequested() + " note=" + voice.getNote());
        return new Outcome(deliveryId3, resolved.getMode(), resolved.getReason(), notificationShown, voice.getRequested(), false, 32, null);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:44:0x0118  */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0073, code lost:
    
        if (r22.equals(com.jarvis.app.proactive.ProactiveActionReceiver.ACTION_NO) == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x002c, code lost:
    
        if (r22.equals(com.jarvis.app.proactive.ProactiveActionReceiver.ACTION_YES) == false) goto L46;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String handleAction(android.content.Context r21, java.lang.String r22, java.lang.String r23, java.lang.String r24, java.lang.String r25) {
        /*
            Method dump skipped, instruction units count: 398
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jarvis.app.proactive.ProactiveDelivery.handleAction(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    static /* synthetic */ Outcome suppress$default(ProactiveDelivery proactiveDelivery, Context context, String str, String str2, String str3, ProactiveDecision proactiveDecision, DeliveryReason deliveryReason, long j, boolean z, int i, Object obj) {
        boolean z2;
        if ((i & 128) == 0) {
            z2 = z;
        } else {
            z2 = false;
        }
        return proactiveDelivery.suppress(context, str, str2, str3, proactiveDecision, deliveryReason, j, z2);
    }

    private final Outcome suppress(Context context, String deliveryId, String fingerprint, String patternKey, ProactiveDecision decision, DeliveryReason reason, long now, boolean duplicate) {
        ProactiveDeliveryLog.INSTANCE.append(context, deliveryId, fingerprint, patternKey, DeliveryMode.SUPPRESS, reason, decision.getReason(), false, (ViewUtils.EDGE_TO_EDGE_FLAGS & 256) != 0 ? null : null, (ViewUtils.EDGE_TO_EDGE_FLAGS & 512) != 0 ? null : null, (ViewUtils.EDGE_TO_EDGE_FLAGS & 1024) != 0 ? System.currentTimeMillis() : now);
        Log.i(TAG, "deliver id=" + deliveryId + " type=" + decision.getType() + " mode=SUPPRESS reason=" + reason + " notification=false voiceRequested=false dup=" + duplicate);
        return new Outcome(deliveryId, DeliveryMode.SUPPRESS, reason, false, false, duplicate);
    }
}
