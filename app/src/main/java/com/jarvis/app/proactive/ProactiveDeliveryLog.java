package com.jarvis.app.proactive;

import android.content.Context;
import com.jarvis.core.privacy.SensitivityClassifier;
import com.jarvis.core.proactive.DeliveryMode;
import com.jarvis.core.proactive.DeliveryReason;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ProactiveDeliveryLog.kt */
/* JADX INFO: loaded from: classes23.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\f\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001/B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003Jq\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001b¢\u0006\u0002\u0010\u001dJ\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\f0\u001f2\u0006\u0010\r\u001a\u00020\u000eJ\u0018\u0010 \u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005J\u0018\u0010!\u001a\u00020\u00182\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005J.\u0010\"\u001a\u00020\u00182\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u001c\u001a\u00020\u001bJ/\u0010#\u001a\u00020$2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u00052\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b¢\u0006\u0002\u0010&J\u000e\u0010'\u001a\u00020$2\u0006\u0010\r\u001a\u00020\u000eJ\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00050\u001f2\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010)\u001a\b\u0012\u0004\u0012\u00020\f0\u001f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u001e\u0010*\u001a\u00020$2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\f0\u001fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/jarvis/app/proactive/ProactiveDeliveryLog;", "", "<init>", "()V", "PREFS", "", "KEY_ENTRIES", "MAX_ENTRIES", "", "MAX_REASON_CHARS", "lock", "append", "Lcom/jarvis/app/proactive/ProactiveDeliveryLog$Entry;", "context", "Landroid/content/Context;", ProactiveActionReceiver.EXTRA_DELIVERY_ID, ProactiveActionReceiver.EXTRA_FINGERPRINT, ProactiveActionReceiver.EXTRA_PATTERN_KEY, "mode", "Lcom/jarvis/core/proactive/DeliveryMode;", "reason", "Lcom/jarvis/core/proactive/DeliveryReason;", "reasonText", "voiceShown", "", "userAction", "snoozeUntil", "", "now", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/core/proactive/DeliveryMode;Lcom/jarvis/core/proactive/DeliveryReason;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/Long;J)Lcom/jarvis/app/proactive/ProactiveDeliveryLog$Entry;", "all", "", "find", "hasDelivered", "isSnoozed", "setAction", "", "action", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)V", "clear", "deliveryIds", "readRaw", "writeRaw", ProactiveDeliveryLog.KEY_ENTRIES, "ACTION_YES", "ACTION_NO", "ACTION_LATER", "Entry", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ProactiveDeliveryLog {
    public static final String ACTION_LATER = "later";
    public static final String ACTION_NO = "no";
    public static final String ACTION_YES = "yes";
    private static final String KEY_ENTRIES = "entries";
    private static final int MAX_ENTRIES = 100;
    private static final int MAX_REASON_CHARS = 100;
    private static final String PREFS = "jarvis_proactive_delivery_v1";
    public static final ProactiveDeliveryLog INSTANCE = new ProactiveDeliveryLog();
    private static final Object lock = new Object();

    private ProactiveDeliveryLog() {
    }

    /* JADX INFO: compiled from: ProactiveDeliveryLog.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\"\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B_\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003J\t\u0010%\u001a\u00020\u0005HÆ\u0003J\t\u0010&\u001a\u00020\u0005HÆ\u0003J\t\u0010'\u001a\u00020\fHÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001eJz\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010+J\u0013\u0010,\u001a\u00020\f2\b\u0010-\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010.\u001a\u00020/HÖ\u0001J\t\u00100\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\r\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b\u001d\u0010\u001e¨\u00061"}, d2 = {"Lcom/jarvis/app/proactive/ProactiveDeliveryLog$Entry;", "", "ts", "", ProactiveActionReceiver.EXTRA_DELIVERY_ID, "", ProactiveActionReceiver.EXTRA_FINGERPRINT, ProactiveActionReceiver.EXTRA_PATTERN_KEY, "mode", "reason", "reasonText", "voiceShown", "", "userAction", "snoozeUntil", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/Long;)V", "getTs", "()J", "getDeliveryId", "()Ljava/lang/String;", "getFingerprint", "getPatternKey", "getMode", "getReason", "getReasonText", "getVoiceShown", "()Z", "getUserAction", "getSnoozeUntil", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/Long;)Lcom/jarvis/app/proactive/ProactiveDeliveryLog$Entry;", "equals", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Entry {
        private final String deliveryId;
        private final String fingerprint;
        private final String mode;
        private final String patternKey;
        private final String reason;
        private final String reasonText;
        private final Long snoozeUntil;
        private final long ts;
        private final String userAction;
        private final boolean voiceShown;

        public static /* synthetic */ Entry copy$default(Entry entry, long j, String str, String str2, String str3, String str4, String str5, String str6, boolean z, String str7, Long l, int i, Object obj) {
            return entry.copy((i & 1) != 0 ? entry.ts : j, (i & 2) != 0 ? entry.deliveryId : str, (i & 4) != 0 ? entry.fingerprint : str2, (i & 8) != 0 ? entry.patternKey : str3, (i & 16) != 0 ? entry.mode : str4, (i & 32) != 0 ? entry.reason : str5, (i & 64) != 0 ? entry.reasonText : str6, (i & 128) != 0 ? entry.voiceShown : z, (i & 256) != 0 ? entry.userAction : str7, (i & 512) != 0 ? entry.snoozeUntil : l);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getTs() {
            return this.ts;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final Long getSnoozeUntil() {
            return this.snoozeUntil;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getDeliveryId() {
            return this.deliveryId;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getFingerprint() {
            return this.fingerprint;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getPatternKey() {
            return this.patternKey;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getMode() {
            return this.mode;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getReason() {
            return this.reason;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getReasonText() {
            return this.reasonText;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final boolean getVoiceShown() {
            return this.voiceShown;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getUserAction() {
            return this.userAction;
        }

        public final Entry copy(long ts, String deliveryId, String fingerprint, String patternKey, String mode, String reason, String reasonText, boolean voiceShown, String userAction, Long snoozeUntil) {
            Intrinsics.checkNotNullParameter(deliveryId, "deliveryId");
            Intrinsics.checkNotNullParameter(mode, "mode");
            Intrinsics.checkNotNullParameter(reason, "reason");
            Intrinsics.checkNotNullParameter(reasonText, "reasonText");
            return new Entry(ts, deliveryId, fingerprint, patternKey, mode, reason, reasonText, voiceShown, userAction, snoozeUntil);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) other;
            return this.ts == entry.ts && Intrinsics.areEqual(this.deliveryId, entry.deliveryId) && Intrinsics.areEqual(this.fingerprint, entry.fingerprint) && Intrinsics.areEqual(this.patternKey, entry.patternKey) && Intrinsics.areEqual(this.mode, entry.mode) && Intrinsics.areEqual(this.reason, entry.reason) && Intrinsics.areEqual(this.reasonText, entry.reasonText) && this.voiceShown == entry.voiceShown && Intrinsics.areEqual(this.userAction, entry.userAction) && Intrinsics.areEqual(this.snoozeUntil, entry.snoozeUntil);
        }

        public int hashCode() {
            return (((((((((((((((((Long.hashCode(this.ts) * 31) + this.deliveryId.hashCode()) * 31) + (this.fingerprint == null ? 0 : this.fingerprint.hashCode())) * 31) + (this.patternKey == null ? 0 : this.patternKey.hashCode())) * 31) + this.mode.hashCode()) * 31) + this.reason.hashCode()) * 31) + this.reasonText.hashCode()) * 31) + Boolean.hashCode(this.voiceShown)) * 31) + (this.userAction == null ? 0 : this.userAction.hashCode())) * 31) + (this.snoozeUntil != null ? this.snoozeUntil.hashCode() : 0);
        }

        public String toString() {
            return "Entry(ts=" + this.ts + ", deliveryId=" + this.deliveryId + ", fingerprint=" + this.fingerprint + ", patternKey=" + this.patternKey + ", mode=" + this.mode + ", reason=" + this.reason + ", reasonText=" + this.reasonText + ", voiceShown=" + this.voiceShown + ", userAction=" + this.userAction + ", snoozeUntil=" + this.snoozeUntil + ")";
        }

        public Entry(long ts, String deliveryId, String fingerprint, String patternKey, String mode, String reason, String reasonText, boolean voiceShown, String userAction, Long snoozeUntil) {
            Intrinsics.checkNotNullParameter(deliveryId, "deliveryId");
            Intrinsics.checkNotNullParameter(mode, "mode");
            Intrinsics.checkNotNullParameter(reason, "reason");
            Intrinsics.checkNotNullParameter(reasonText, "reasonText");
            this.ts = ts;
            this.deliveryId = deliveryId;
            this.fingerprint = fingerprint;
            this.patternKey = patternKey;
            this.mode = mode;
            this.reason = reason;
            this.reasonText = reasonText;
            this.voiceShown = voiceShown;
            this.userAction = userAction;
            this.snoozeUntil = snoozeUntil;
        }

        public final long getTs() {
            return this.ts;
        }

        public final String getDeliveryId() {
            return this.deliveryId;
        }

        public final String getFingerprint() {
            return this.fingerprint;
        }

        public final String getPatternKey() {
            return this.patternKey;
        }

        public final String getMode() {
            return this.mode;
        }

        public final String getReason() {
            return this.reason;
        }

        public final String getReasonText() {
            return this.reasonText;
        }

        public final boolean getVoiceShown() {
            return this.voiceShown;
        }

        public final String getUserAction() {
            return this.userAction;
        }

        public final Long getSnoozeUntil() {
            return this.snoozeUntil;
        }
    }

    public final Entry append(Context context, String deliveryId, String fingerprint, String patternKey, DeliveryMode mode, DeliveryReason reason, String reasonText, boolean voiceShown, String userAction, Long snoozeUntil, long now) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(deliveryId, "deliveryId");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(reason, "reason");
        Intrinsics.checkNotNullParameter(reasonText, "reasonText");
        Entry entry = new Entry(now, deliveryId, fingerprint, patternKey, mode.name(), reason.name(), StringsKt.take(SensitivityClassifier.INSTANCE.mask(reasonText), 100), voiceShown, userAction, snoozeUntil);
        synchronized (lock) {
            List<Entry> mutableList = CollectionsKt.toMutableList((Collection) INSTANCE.readRaw(context));
            mutableList.add(entry);
            while (mutableList.size() > 100) {
                mutableList.remove(0);
            }
            INSTANCE.writeRaw(context, mutableList);
            Unit unit = Unit.INSTANCE;
        }
        return entry;
    }

    public final List<Entry> all(Context context) {
        List<Entry> raw;
        Intrinsics.checkNotNullParameter(context, "context");
        synchronized (lock) {
            raw = INSTANCE.readRaw(context);
        }
        return raw;
    }

    public final Entry find(Context context, String deliveryId) {
        Object element$iv;
        Entry entry;
        Entry it;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(deliveryId, "deliveryId");
        synchronized (lock) {
            List<Entry> raw = INSTANCE.readRaw(context);
            ListIterator<Entry> listIterator = raw.listIterator(raw.size());
            do {
                if (listIterator.hasPrevious()) {
                    element$iv = listIterator.previous();
                    it = (Entry) element$iv;
                } else {
                    element$iv = null;
                    break;
                }
            } while (!Intrinsics.areEqual(it.getDeliveryId(), deliveryId));
            entry = (Entry) element$iv;
        }
        return entry;
    }

    public final boolean hasDelivered(Context context, String fingerprint) {
        Intrinsics.checkNotNullParameter(context, "context");
        String str = fingerprint;
        boolean z = true;
        if (str == null || StringsKt.isBlank(str)) {
            return false;
        }
        synchronized (lock) {
            Iterable $this$any$iv = INSTANCE.readRaw(context);
            if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
                z = false;
            } else {
                for (Object element$iv : $this$any$iv) {
                    Entry it = (Entry) element$iv;
                    if (Intrinsics.areEqual(it.getFingerprint(), fingerprint) && !Intrinsics.areEqual(it.getMode(), "SUPPRESS")) {
                    }
                }
                z = false;
            }
        }
        return z;
    }

    public static /* synthetic */ boolean isSnoozed$default(ProactiveDeliveryLog proactiveDeliveryLog, Context context, String str, String str2, long j, int i, Object obj) {
        String str3;
        long jCurrentTimeMillis;
        if ((i & 4) == 0) {
            str3 = str2;
        } else {
            str3 = null;
        }
        if ((i & 8) == 0) {
            jCurrentTimeMillis = j;
        } else {
            jCurrentTimeMillis = System.currentTimeMillis();
        }
        return proactiveDeliveryLog.isSnoozed(context, str, str3, jCurrentTimeMillis);
    }

    /* JADX WARN: Code duplicated, block: B:42:0x0094  */
    public final boolean isSnoozed(Context context, String fingerprint, String patternKey, long now) {
        boolean z;
        Intrinsics.checkNotNullParameter(context, "context");
        String str = null;
        String fp = (fingerprint == null || StringsKt.isBlank(fingerprint)) ? null : fingerprint;
        if (patternKey != null && !StringsKt.isBlank(patternKey)) {
            str = patternKey;
        }
        String key = str;
        boolean z2 = false;
        if (fp == null && key == null) {
            return false;
        }
        synchronized (lock) {
            Iterable $this$any$iv = INSTANCE.readRaw(context);
            if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                for (Object element$iv : $this$any$iv) {
                    Entry it = (Entry) element$iv;
                    if ((!Intrinsics.areEqual(it.getFingerprint(), fp) && (key == null || !Intrinsics.areEqual(it.getPatternKey(), key))) || !Intrinsics.areEqual(it.getUserAction(), ACTION_LATER)) {
                        z = false;
                    } else {
                        Long snoozeUntil = it.getSnoozeUntil();
                        if ((snoozeUntil != null ? snoozeUntil.longValue() : 0L) > now) {
                            z = true;
                        } else {
                            z = false;
                        }
                    }
                    if (z) {
                        z2 = true;
                        break;
                    }
                }
            }
        }
        return z2;
    }

    public static /* synthetic */ void setAction$default(ProactiveDeliveryLog proactiveDeliveryLog, Context context, String str, String str2, Long l, int i, Object obj) {
        if ((i & 8) != 0) {
            l = null;
        }
        proactiveDeliveryLog.setAction(context, str, str2, l);
    }

    public final void setAction(Context context, String deliveryId, String action, Long snoozeUntil) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(deliveryId, "deliveryId");
        Intrinsics.checkNotNullParameter(action, "action");
        synchronized (lock) {
            Iterable $this$map$iv = INSTANCE.readRaw(context);
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            Collection destination$iv$iv2 = destination$iv$iv;
            for (Object item$iv$iv : $this$map$iv) {
                Entry it = (Entry) item$iv$iv;
                Entry entryCopy$default = Intrinsics.areEqual(it.getDeliveryId(), deliveryId) ? Entry.copy$default(it, 0L, null, null, null, null, null, null, false, action, snoozeUntil, 255, null) : it;
                Collection destination$iv$iv3 = destination$iv$iv2;
                destination$iv$iv3.add(entryCopy$default);
                destination$iv$iv2 = destination$iv$iv3;
                $this$map$iv = $this$map$iv;
            }
            List all = (List) destination$iv$iv2;
            INSTANCE.writeRaw(context, all);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void clear(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        synchronized (lock) {
            context.getApplicationContext().getSharedPreferences(PREFS, 0).edit().remove(KEY_ENTRIES).apply();
            Unit unit = Unit.INSTANCE;
        }
    }

    public final List<String> deliveryIds(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Iterable $this$map$iv = all(context);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            Entry it = (Entry) item$iv$iv;
            destination$iv$iv.add(it.getDeliveryId());
        }
        return CollectionsKt.distinct((List) destination$iv$iv);
    }

    /* JADX WARN: Code duplicated, block: B:36:0x0118  */
    private final List<Entry> readRaw(Context context) {
        Object objM392constructorimpl;
        List listEmptyList;
        String str = "optString(...)";
        String str2 = null;
        String raw = context.getApplicationContext().getSharedPreferences(PREFS, 0).getString(KEY_ENTRIES, null);
        if (raw == null) {
            return CollectionsKt.emptyList();
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            ProactiveDeliveryLog proactiveDeliveryLog = this;
            JSONArray array = new JSONArray(raw);
            List $this$readRaw_u24lambda_u240_u240 = CollectionsKt.createListBuilder();
            int i = 0;
            int length = array.length();
            while (i < length) {
                JSONObject o = array.getJSONObject(i);
                long jOptLong = o.optLong("ts");
                String strOptString = o.optString(ProactiveActionReceiver.EXTRA_DELIVERY_ID);
                Intrinsics.checkNotNullExpressionValue(strOptString, str);
                String it = o.optString(ProactiveActionReceiver.EXTRA_FINGERPRINT);
                Intrinsics.checkNotNull(it);
                String str3 = !StringsKt.isBlank(it) ? it : str2;
                String it2 = o.optString(ProactiveActionReceiver.EXTRA_PATTERN_KEY);
                Intrinsics.checkNotNull(it2);
                String str4 = !StringsKt.isBlank(it2) ? it2 : str2;
                String strOptString2 = o.optString("mode");
                Intrinsics.checkNotNullExpressionValue(strOptString2, str);
                String strOptString3 = o.optString("reason");
                Intrinsics.checkNotNullExpressionValue(strOptString3, str);
                String raw2 = raw;
                try {
                    String strOptString4 = o.optString("reasonText");
                    Intrinsics.checkNotNullExpressionValue(strOptString4, str);
                    String str5 = str;
                    boolean zOptBoolean = o.optBoolean("voiceShown");
                    String it3 = o.optString("userAction");
                    Intrinsics.checkNotNull(it3);
                    $this$readRaw_u24lambda_u240_u240.add(new Entry(jOptLong, strOptString, str3, str4, strOptString2, strOptString3, strOptString4, zOptBoolean, !StringsKt.isBlank(it3) ? it3 : null, o.has("snoozeUntil") ? Long.valueOf(o.optLong("snoozeUntil")) : null));
                    i++;
                    raw = raw2;
                    str = str5;
                    str2 = null;
                } catch (Throwable th) {
                    th = th;
                    Result.Companion companion2 = Result.INSTANCE;
                    objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
                    listEmptyList = CollectionsKt.emptyList();
                    if (Result.m398isFailureimpl(objM392constructorimpl)) {
                        objM392constructorimpl = listEmptyList;
                    }
                    return (List) objM392constructorimpl;
                }
            }
            objM392constructorimpl = Result.m392constructorimpl(CollectionsKt.build($this$readRaw_u24lambda_u240_u240));
        } catch (Throwable th2) {
            th = th2;
        }
        listEmptyList = CollectionsKt.emptyList();
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = listEmptyList;
        }
        return (List) objM392constructorimpl;
    }

    private final void writeRaw(Context context, List<Entry> entries) throws JSONException {
        JSONArray array = new JSONArray();
        List<Entry> $this$forEach$iv = entries;
        for (Object element$iv : $this$forEach$iv) {
            Entry e = (Entry) element$iv;
            JSONObject $this$writeRaw_u24lambda_u240_u240 = new JSONObject();
            $this$writeRaw_u24lambda_u240_u240.put("ts", e.getTs());
            $this$writeRaw_u24lambda_u240_u240.put(ProactiveActionReceiver.EXTRA_DELIVERY_ID, e.getDeliveryId());
            String fingerprint = e.getFingerprint();
            if (fingerprint == null) {
                fingerprint = "";
            }
            $this$writeRaw_u24lambda_u240_u240.put(ProactiveActionReceiver.EXTRA_FINGERPRINT, fingerprint);
            String patternKey = e.getPatternKey();
            if (patternKey == null) {
                patternKey = "";
            }
            $this$writeRaw_u24lambda_u240_u240.put(ProactiveActionReceiver.EXTRA_PATTERN_KEY, patternKey);
            $this$writeRaw_u24lambda_u240_u240.put("mode", e.getMode());
            $this$writeRaw_u24lambda_u240_u240.put("reason", e.getReason());
            $this$writeRaw_u24lambda_u240_u240.put("reasonText", e.getReasonText());
            $this$writeRaw_u24lambda_u240_u240.put("voiceShown", e.getVoiceShown());
            String userAction = e.getUserAction();
            $this$writeRaw_u24lambda_u240_u240.put("userAction", userAction != null ? userAction : "");
            Long snoozeUntil = e.getSnoozeUntil();
            if (snoozeUntil != null) {
                long it = snoozeUntil.longValue();
                $this$writeRaw_u24lambda_u240_u240.put("snoozeUntil", it);
            }
            array.put($this$writeRaw_u24lambda_u240_u240);
        }
        context.getApplicationContext().getSharedPreferences(PREFS, 0).edit().putString(KEY_ENTRIES, array.toString()).apply();
    }
}
