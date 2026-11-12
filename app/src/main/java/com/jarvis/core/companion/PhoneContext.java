package com.jarvis.core.companion;

import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: PhoneContext.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b8\b\u0086\b\u0018\u00002\u00020\u0001B¯\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\f\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0015\u001a\u00020\n\u0012\b\b\u0002\u0010\u0016\u001a\u00020\n¢\u0006\u0004\b\u0017\u0010\u0018J\u0006\u00100\u001a\u00020\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u00104\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u001fJ\t\u00105\u001a\u00020\nHÆ\u0003J\t\u00106\u001a\u00020\fHÆ\u0003J\u0010\u00107\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0002\u0010&J\t\u00108\u001a\u00020\fHÆ\u0003J\t\u00109\u001a\u00020\fHÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010>\u001a\u00020\nHÆ\u0003J\t\u0010?\u001a\u00020\nHÆ\u0003J¶\u0001\u0010@\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0015\u001a\u00020\n2\b\b\u0002\u0010\u0016\u001a\u00020\nHÆ\u0001¢\u0006\u0002\u0010AJ\u0013\u0010B\u001a\u00020\f2\b\u0010C\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010D\u001a\u00020\u000eHÖ\u0001J\t\u0010E\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010 \u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0015\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\n\n\u0002\u0010'\u001a\u0004\b%\u0010&R\u0011\u0010\u000f\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b(\u0010$R\u0011\u0010\u0010\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b)\u0010$R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001aR\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001aR\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001aR\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001aR\u0011\u0010\u0015\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\"R\u0011\u0010\u0016\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\"¨\u0006F"}, d2 = {"Lcom/jarvis/core/companion/PhoneContext;", "", "currentApp", "", "previousApp", "currentAppSource", "Lcom/jarvis/core/companion/ObservationSource;", "currentAppConfidence", "", "currentAppAtMillis", "", "screenOn", "", "batteryPercent", "", "charging", "headphonesConnected", "networkType", "lastNotificationPackage", "lastNotificationSummary", "lastObservationSummary", "lastObservationAtMillis", "observationCount", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/core/companion/ObservationSource;Ljava/lang/Float;JZLjava/lang/Integer;ZZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJ)V", "getCurrentApp", "()Ljava/lang/String;", "getPreviousApp", "getCurrentAppSource", "()Lcom/jarvis/core/companion/ObservationSource;", "getCurrentAppConfidence", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getCurrentAppAtMillis", "()J", "getScreenOn", "()Z", "getBatteryPercent", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getCharging", "getHeadphonesConnected", "getNetworkType", "getLastNotificationPackage", "getLastNotificationSummary", "getLastObservationSummary", "getLastObservationAtMillis", "getObservationCount", "describe", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "copy", "(Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/core/companion/ObservationSource;Ljava/lang/Float;JZLjava/lang/Integer;ZZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJ)Lcom/jarvis/core/companion/PhoneContext;", "equals", "other", "hashCode", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class PhoneContext {
    private final Integer batteryPercent;
    private final boolean charging;
    private final String currentApp;
    private final long currentAppAtMillis;
    private final Float currentAppConfidence;
    private final ObservationSource currentAppSource;
    private final boolean headphonesConnected;
    private final String lastNotificationPackage;
    private final String lastNotificationSummary;
    private final long lastObservationAtMillis;
    private final String lastObservationSummary;
    private final String networkType;
    private final long observationCount;
    private final String previousApp;
    private final boolean screenOn;

    public PhoneContext() {
        this(null, null, null, null, 0L, false, null, false, false, null, null, null, null, 0L, 0L, 32767, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getCurrentApp() {
        return this.currentApp;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getNetworkType() {
        return this.networkType;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getLastNotificationPackage() {
        return this.lastNotificationPackage;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getLastNotificationSummary() {
        return this.lastNotificationSummary;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getLastObservationSummary() {
        return this.lastObservationSummary;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final long getLastObservationAtMillis() {
        return this.lastObservationAtMillis;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final long getObservationCount() {
        return this.observationCount;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPreviousApp() {
        return this.previousApp;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ObservationSource getCurrentAppSource() {
        return this.currentAppSource;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Float getCurrentAppConfidence() {
        return this.currentAppConfidence;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getCurrentAppAtMillis() {
        return this.currentAppAtMillis;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getScreenOn() {
        return this.screenOn;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Integer getBatteryPercent() {
        return this.batteryPercent;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final boolean getCharging() {
        return this.charging;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final boolean getHeadphonesConnected() {
        return this.headphonesConnected;
    }

    public final PhoneContext copy(String currentApp, String previousApp, ObservationSource currentAppSource, Float currentAppConfidence, long currentAppAtMillis, boolean screenOn, Integer batteryPercent, boolean charging, boolean headphonesConnected, String networkType, String lastNotificationPackage, String lastNotificationSummary, String lastObservationSummary, long lastObservationAtMillis, long observationCount) {
        return new PhoneContext(currentApp, previousApp, currentAppSource, currentAppConfidence, currentAppAtMillis, screenOn, batteryPercent, charging, headphonesConnected, networkType, lastNotificationPackage, lastNotificationSummary, lastObservationSummary, lastObservationAtMillis, observationCount);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PhoneContext)) {
            return false;
        }
        PhoneContext phoneContext = (PhoneContext) other;
        return Intrinsics.areEqual(this.currentApp, phoneContext.currentApp) && Intrinsics.areEqual(this.previousApp, phoneContext.previousApp) && this.currentAppSource == phoneContext.currentAppSource && Intrinsics.areEqual((Object) this.currentAppConfidence, (Object) phoneContext.currentAppConfidence) && this.currentAppAtMillis == phoneContext.currentAppAtMillis && this.screenOn == phoneContext.screenOn && Intrinsics.areEqual(this.batteryPercent, phoneContext.batteryPercent) && this.charging == phoneContext.charging && this.headphonesConnected == phoneContext.headphonesConnected && Intrinsics.areEqual(this.networkType, phoneContext.networkType) && Intrinsics.areEqual(this.lastNotificationPackage, phoneContext.lastNotificationPackage) && Intrinsics.areEqual(this.lastNotificationSummary, phoneContext.lastNotificationSummary) && Intrinsics.areEqual(this.lastObservationSummary, phoneContext.lastObservationSummary) && this.lastObservationAtMillis == phoneContext.lastObservationAtMillis && this.observationCount == phoneContext.observationCount;
    }

    public int hashCode() {
        return ((((((((((((((((((((((((((((this.currentApp == null ? 0 : this.currentApp.hashCode()) * 31) + (this.previousApp == null ? 0 : this.previousApp.hashCode())) * 31) + (this.currentAppSource == null ? 0 : this.currentAppSource.hashCode())) * 31) + (this.currentAppConfidence == null ? 0 : this.currentAppConfidence.hashCode())) * 31) + Long.hashCode(this.currentAppAtMillis)) * 31) + Boolean.hashCode(this.screenOn)) * 31) + (this.batteryPercent == null ? 0 : this.batteryPercent.hashCode())) * 31) + Boolean.hashCode(this.charging)) * 31) + Boolean.hashCode(this.headphonesConnected)) * 31) + (this.networkType == null ? 0 : this.networkType.hashCode())) * 31) + (this.lastNotificationPackage == null ? 0 : this.lastNotificationPackage.hashCode())) * 31) + (this.lastNotificationSummary == null ? 0 : this.lastNotificationSummary.hashCode())) * 31) + (this.lastObservationSummary != null ? this.lastObservationSummary.hashCode() : 0)) * 31) + Long.hashCode(this.lastObservationAtMillis)) * 31) + Long.hashCode(this.observationCount);
    }

    public String toString() {
        return "PhoneContext(currentApp=" + this.currentApp + ", previousApp=" + this.previousApp + ", currentAppSource=" + this.currentAppSource + ", currentAppConfidence=" + this.currentAppConfidence + ", currentAppAtMillis=" + this.currentAppAtMillis + ", screenOn=" + this.screenOn + ", batteryPercent=" + this.batteryPercent + ", charging=" + this.charging + ", headphonesConnected=" + this.headphonesConnected + ", networkType=" + this.networkType + ", lastNotificationPackage=" + this.lastNotificationPackage + ", lastNotificationSummary=" + this.lastNotificationSummary + ", lastObservationSummary=" + this.lastObservationSummary + ", lastObservationAtMillis=" + this.lastObservationAtMillis + ", observationCount=" + this.observationCount + ")";
    }

    public PhoneContext(String currentApp, String previousApp, ObservationSource currentAppSource, Float currentAppConfidence, long currentAppAtMillis, boolean screenOn, Integer batteryPercent, boolean charging, boolean headphonesConnected, String networkType, String lastNotificationPackage, String lastNotificationSummary, String lastObservationSummary, long lastObservationAtMillis, long observationCount) {
        this.currentApp = currentApp;
        this.previousApp = previousApp;
        this.currentAppSource = currentAppSource;
        this.currentAppConfidence = currentAppConfidence;
        this.currentAppAtMillis = currentAppAtMillis;
        this.screenOn = screenOn;
        this.batteryPercent = batteryPercent;
        this.charging = charging;
        this.headphonesConnected = headphonesConnected;
        this.networkType = networkType;
        this.lastNotificationPackage = lastNotificationPackage;
        this.lastNotificationSummary = lastNotificationSummary;
        this.lastObservationSummary = lastObservationSummary;
        this.lastObservationAtMillis = lastObservationAtMillis;
        this.observationCount = observationCount;
    }

    public /* synthetic */ PhoneContext(String str, String str2, ObservationSource observationSource, Float f, long j, boolean z, Integer num, boolean z2, boolean z3, String str3, String str4, String str5, String str6, long j2, long j3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : observationSource, (i & 8) != 0 ? null : f, (i & 16) != 0 ? 0L : j, (i & 32) != 0 ? true : z, (i & 64) != 0 ? null : num, (i & 128) != 0 ? false : z2, (i & 256) == 0 ? z3 : false, (i & 512) != 0 ? null : str3, (i & 1024) != 0 ? null : str4, (i & 2048) != 0 ? null : str5, (i & 4096) != 0 ? null : str6, (i & 8192) != 0 ? 0L : j2, (i & 16384) != 0 ? 0L : j3);
    }

    public final String getCurrentApp() {
        return this.currentApp;
    }

    public final String getPreviousApp() {
        return this.previousApp;
    }

    public final ObservationSource getCurrentAppSource() {
        return this.currentAppSource;
    }

    public final Float getCurrentAppConfidence() {
        return this.currentAppConfidence;
    }

    public final long getCurrentAppAtMillis() {
        return this.currentAppAtMillis;
    }

    public final boolean getScreenOn() {
        return this.screenOn;
    }

    public final Integer getBatteryPercent() {
        return this.batteryPercent;
    }

    public final boolean getCharging() {
        return this.charging;
    }

    public final boolean getHeadphonesConnected() {
        return this.headphonesConnected;
    }

    public final String getNetworkType() {
        return this.networkType;
    }

    public final String getLastNotificationPackage() {
        return this.lastNotificationPackage;
    }

    public final String getLastNotificationSummary() {
        return this.lastNotificationSummary;
    }

    public final String getLastObservationSummary() {
        return this.lastObservationSummary;
    }

    public final long getLastObservationAtMillis() {
        return this.lastObservationAtMillis;
    }

    public final long getObservationCount() {
        return this.observationCount;
    }

    public final String describe() {
        StringBuilder $this$describe_u24lambda_u240 = new StringBuilder();
        StringBuilder sbAppend = $this$describe_u24lambda_u240.append("app=");
        String str = this.currentApp;
        if (str == null) {
            str = "—";
        }
        sbAppend.append(str);
        String str2 = this.previousApp;
        if (!(str2 == null || StringsKt.isBlank(str2))) {
            $this$describe_u24lambda_u240.append(" (←").append(this.previousApp).append(")");
        }
        ObservationSource it = this.currentAppSource;
        if (it != null) {
            $this$describe_u24lambda_u240.append(" via=").append(StringsKt.take(it.name(), 6));
        }
        Float f = this.currentAppConfidence;
        if (f != null) {
            float it2 = f.floatValue();
            StringBuilder sbAppend2 = $this$describe_u24lambda_u240.append("@");
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str3 = String.format(Locale.US, "%.1f", Arrays.copyOf(new Object[]{Float.valueOf(it2)}, 1));
            Intrinsics.checkNotNullExpressionValue(str3, "format(...)");
            sbAppend2.append(str3);
        }
        $this$describe_u24lambda_u240.append(" · screen=").append(this.screenOn ? "on" : "off");
        Integer num = this.batteryPercent;
        if (num != null) {
            $this$describe_u24lambda_u240.append(" · battery=").append(num.intValue()).append("%");
        }
        if (this.charging) {
            $this$describe_u24lambda_u240.append(" charging");
        }
        if (this.headphonesConnected) {
            $this$describe_u24lambda_u240.append(" · headphones");
        }
        String it3 = this.networkType;
        if (it3 != null) {
            $this$describe_u24lambda_u240.append(" · net=").append(it3);
        }
        String it4 = this.lastNotificationPackage;
        if (it4 != null) {
            $this$describe_u24lambda_u240.append(" · notif=").append(it4);
        }
        $this$describe_u24lambda_u240.append(" · events=").append(this.observationCount);
        return $this$describe_u24lambda_u240.toString();
    }
}
