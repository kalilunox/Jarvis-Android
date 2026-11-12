package com.jarvis.core.voice;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AsrContracts.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b:\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u00ad\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u0006\u00108\u001a\u00020\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010=\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010 J\u0010\u0010>\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010 J\u0010\u0010?\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010 J\u0010\u0010@\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010 J\u0010\u0010A\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010 J\u0010\u0010B\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010 J\u0010\u0010C\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010 J\u0010\u0010D\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010 J\t\u0010E\u001a\u00020\u0011HÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0003HÆ\u0003J´\u0001\u0010G\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010HJ\u0013\u0010I\u001a\u00020\u00112\b\u0010J\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010K\u001a\u00020LHÖ\u0001J\t\u0010M\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0016\"\u0004\b\u001a\u0010\u0018R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0016\"\u0004\b\u001c\u0010\u0018R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0016\"\u0004\b\u001e\u0010\u0018R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001e\u0010\t\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b$\u0010 \"\u0004\b%\u0010\"R\u001e\u0010\n\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b&\u0010 \"\u0004\b'\u0010\"R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b(\u0010 \"\u0004\b)\u0010\"R\u001e\u0010\f\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b*\u0010 \"\u0004\b+\u0010\"R\u001e\u0010\r\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b,\u0010 \"\u0004\b-\u0010\"R\u001e\u0010\u000e\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b.\u0010 \"\u0004\b/\u0010\"R\u001e\u0010\u000f\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b0\u0010 \"\u0004\b1\u0010\"R\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u0016\"\u0004\b7\u0010\u0018¨\u0006N"}, d2 = {"Lcom/jarvis/core/voice/AsrTelemetry;", "", "asrProvider", "", "asrModel", "asrRoute", "asrRouteReason", "asrSessionStartAt", "", "asrFirstPartialAt", "asrFirstStablePartialAt", "asrFinalAt", "asrSetupMs", "asrFirstPartialMs", "asrFinalizeMs", "asrTotalMs", "asrFallback", "", "asrFallbackReason", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;ZLjava/lang/String;)V", "getAsrProvider", "()Ljava/lang/String;", "setAsrProvider", "(Ljava/lang/String;)V", "getAsrModel", "setAsrModel", "getAsrRoute", "setAsrRoute", "getAsrRouteReason", "setAsrRouteReason", "getAsrSessionStartAt", "()Ljava/lang/Long;", "setAsrSessionStartAt", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getAsrFirstPartialAt", "setAsrFirstPartialAt", "getAsrFirstStablePartialAt", "setAsrFirstStablePartialAt", "getAsrFinalAt", "setAsrFinalAt", "getAsrSetupMs", "setAsrSetupMs", "getAsrFirstPartialMs", "setAsrFirstPartialMs", "getAsrFinalizeMs", "setAsrFinalizeMs", "getAsrTotalMs", "setAsrTotalMs", "getAsrFallback", "()Z", "setAsrFallback", "(Z)V", "getAsrFallbackReason", "setAsrFallbackReason", "summary", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;ZLjava/lang/String;)Lcom/jarvis/core/voice/AsrTelemetry;", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AsrTelemetry {
    private boolean asrFallback;
    private String asrFallbackReason;
    private Long asrFinalAt;
    private Long asrFinalizeMs;
    private Long asrFirstPartialAt;
    private Long asrFirstPartialMs;
    private Long asrFirstStablePartialAt;
    private String asrModel;
    private String asrProvider;
    private String asrRoute;
    private String asrRouteReason;
    private Long asrSessionStartAt;
    private Long asrSetupMs;
    private Long asrTotalMs;

    public AsrTelemetry() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, false, null, 16383, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAsrProvider() {
        return this.asrProvider;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final Long getAsrFirstPartialMs() {
        return this.asrFirstPartialMs;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final Long getAsrFinalizeMs() {
        return this.asrFinalizeMs;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final Long getAsrTotalMs() {
        return this.asrTotalMs;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final boolean getAsrFallback() {
        return this.asrFallback;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getAsrFallbackReason() {
        return this.asrFallbackReason;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getAsrModel() {
        return this.asrModel;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getAsrRoute() {
        return this.asrRoute;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getAsrRouteReason() {
        return this.asrRouteReason;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Long getAsrSessionStartAt() {
        return this.asrSessionStartAt;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Long getAsrFirstPartialAt() {
        return this.asrFirstPartialAt;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Long getAsrFirstStablePartialAt() {
        return this.asrFirstStablePartialAt;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final Long getAsrFinalAt() {
        return this.asrFinalAt;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final Long getAsrSetupMs() {
        return this.asrSetupMs;
    }

    public final AsrTelemetry copy(String asrProvider, String asrModel, String asrRoute, String asrRouteReason, Long asrSessionStartAt, Long asrFirstPartialAt, Long asrFirstStablePartialAt, Long asrFinalAt, Long asrSetupMs, Long asrFirstPartialMs, Long asrFinalizeMs, Long asrTotalMs, boolean asrFallback, String asrFallbackReason) {
        return new AsrTelemetry(asrProvider, asrModel, asrRoute, asrRouteReason, asrSessionStartAt, asrFirstPartialAt, asrFirstStablePartialAt, asrFinalAt, asrSetupMs, asrFirstPartialMs, asrFinalizeMs, asrTotalMs, asrFallback, asrFallbackReason);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AsrTelemetry)) {
            return false;
        }
        AsrTelemetry asrTelemetry = (AsrTelemetry) other;
        return Intrinsics.areEqual(this.asrProvider, asrTelemetry.asrProvider) && Intrinsics.areEqual(this.asrModel, asrTelemetry.asrModel) && Intrinsics.areEqual(this.asrRoute, asrTelemetry.asrRoute) && Intrinsics.areEqual(this.asrRouteReason, asrTelemetry.asrRouteReason) && Intrinsics.areEqual(this.asrSessionStartAt, asrTelemetry.asrSessionStartAt) && Intrinsics.areEqual(this.asrFirstPartialAt, asrTelemetry.asrFirstPartialAt) && Intrinsics.areEqual(this.asrFirstStablePartialAt, asrTelemetry.asrFirstStablePartialAt) && Intrinsics.areEqual(this.asrFinalAt, asrTelemetry.asrFinalAt) && Intrinsics.areEqual(this.asrSetupMs, asrTelemetry.asrSetupMs) && Intrinsics.areEqual(this.asrFirstPartialMs, asrTelemetry.asrFirstPartialMs) && Intrinsics.areEqual(this.asrFinalizeMs, asrTelemetry.asrFinalizeMs) && Intrinsics.areEqual(this.asrTotalMs, asrTelemetry.asrTotalMs) && this.asrFallback == asrTelemetry.asrFallback && Intrinsics.areEqual(this.asrFallbackReason, asrTelemetry.asrFallbackReason);
    }

    public int hashCode() {
        return ((((((((((((((((((((((((((this.asrProvider == null ? 0 : this.asrProvider.hashCode()) * 31) + (this.asrModel == null ? 0 : this.asrModel.hashCode())) * 31) + (this.asrRoute == null ? 0 : this.asrRoute.hashCode())) * 31) + (this.asrRouteReason == null ? 0 : this.asrRouteReason.hashCode())) * 31) + (this.asrSessionStartAt == null ? 0 : this.asrSessionStartAt.hashCode())) * 31) + (this.asrFirstPartialAt == null ? 0 : this.asrFirstPartialAt.hashCode())) * 31) + (this.asrFirstStablePartialAt == null ? 0 : this.asrFirstStablePartialAt.hashCode())) * 31) + (this.asrFinalAt == null ? 0 : this.asrFinalAt.hashCode())) * 31) + (this.asrSetupMs == null ? 0 : this.asrSetupMs.hashCode())) * 31) + (this.asrFirstPartialMs == null ? 0 : this.asrFirstPartialMs.hashCode())) * 31) + (this.asrFinalizeMs == null ? 0 : this.asrFinalizeMs.hashCode())) * 31) + (this.asrTotalMs == null ? 0 : this.asrTotalMs.hashCode())) * 31) + Boolean.hashCode(this.asrFallback)) * 31) + (this.asrFallbackReason != null ? this.asrFallbackReason.hashCode() : 0);
    }

    public String toString() {
        return "AsrTelemetry(asrProvider=" + this.asrProvider + ", asrModel=" + this.asrModel + ", asrRoute=" + this.asrRoute + ", asrRouteReason=" + this.asrRouteReason + ", asrSessionStartAt=" + this.asrSessionStartAt + ", asrFirstPartialAt=" + this.asrFirstPartialAt + ", asrFirstStablePartialAt=" + this.asrFirstStablePartialAt + ", asrFinalAt=" + this.asrFinalAt + ", asrSetupMs=" + this.asrSetupMs + ", asrFirstPartialMs=" + this.asrFirstPartialMs + ", asrFinalizeMs=" + this.asrFinalizeMs + ", asrTotalMs=" + this.asrTotalMs + ", asrFallback=" + this.asrFallback + ", asrFallbackReason=" + this.asrFallbackReason + ")";
    }

    public AsrTelemetry(String asrProvider, String asrModel, String asrRoute, String asrRouteReason, Long asrSessionStartAt, Long asrFirstPartialAt, Long asrFirstStablePartialAt, Long asrFinalAt, Long asrSetupMs, Long asrFirstPartialMs, Long asrFinalizeMs, Long asrTotalMs, boolean asrFallback, String asrFallbackReason) {
        this.asrProvider = asrProvider;
        this.asrModel = asrModel;
        this.asrRoute = asrRoute;
        this.asrRouteReason = asrRouteReason;
        this.asrSessionStartAt = asrSessionStartAt;
        this.asrFirstPartialAt = asrFirstPartialAt;
        this.asrFirstStablePartialAt = asrFirstStablePartialAt;
        this.asrFinalAt = asrFinalAt;
        this.asrSetupMs = asrSetupMs;
        this.asrFirstPartialMs = asrFirstPartialMs;
        this.asrFinalizeMs = asrFinalizeMs;
        this.asrTotalMs = asrTotalMs;
        this.asrFallback = asrFallback;
        this.asrFallbackReason = asrFallbackReason;
    }

    public /* synthetic */ AsrTelemetry(String str, String str2, String str3, String str4, Long l, Long l2, Long l3, Long l4, Long l5, Long l6, Long l7, Long l8, boolean z, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : l, (i & 32) != 0 ? null : l2, (i & 64) != 0 ? null : l3, (i & 128) != 0 ? null : l4, (i & 256) != 0 ? null : l5, (i & 512) != 0 ? null : l6, (i & 1024) != 0 ? null : l7, (i & 2048) != 0 ? null : l8, (i & 4096) != 0 ? false : z, (i & 8192) == 0 ? str5 : null);
    }

    public final String getAsrProvider() {
        return this.asrProvider;
    }

    public final void setAsrProvider(String str) {
        this.asrProvider = str;
    }

    public final String getAsrModel() {
        return this.asrModel;
    }

    public final void setAsrModel(String str) {
        this.asrModel = str;
    }

    public final String getAsrRoute() {
        return this.asrRoute;
    }

    public final void setAsrRoute(String str) {
        this.asrRoute = str;
    }

    public final String getAsrRouteReason() {
        return this.asrRouteReason;
    }

    public final void setAsrRouteReason(String str) {
        this.asrRouteReason = str;
    }

    public final Long getAsrSessionStartAt() {
        return this.asrSessionStartAt;
    }

    public final void setAsrSessionStartAt(Long l) {
        this.asrSessionStartAt = l;
    }

    public final Long getAsrFirstPartialAt() {
        return this.asrFirstPartialAt;
    }

    public final void setAsrFirstPartialAt(Long l) {
        this.asrFirstPartialAt = l;
    }

    public final Long getAsrFirstStablePartialAt() {
        return this.asrFirstStablePartialAt;
    }

    public final void setAsrFirstStablePartialAt(Long l) {
        this.asrFirstStablePartialAt = l;
    }

    public final Long getAsrFinalAt() {
        return this.asrFinalAt;
    }

    public final void setAsrFinalAt(Long l) {
        this.asrFinalAt = l;
    }

    public final Long getAsrSetupMs() {
        return this.asrSetupMs;
    }

    public final void setAsrSetupMs(Long l) {
        this.asrSetupMs = l;
    }

    public final Long getAsrFirstPartialMs() {
        return this.asrFirstPartialMs;
    }

    public final void setAsrFirstPartialMs(Long l) {
        this.asrFirstPartialMs = l;
    }

    public final Long getAsrFinalizeMs() {
        return this.asrFinalizeMs;
    }

    public final void setAsrFinalizeMs(Long l) {
        this.asrFinalizeMs = l;
    }

    public final Long getAsrTotalMs() {
        return this.asrTotalMs;
    }

    public final void setAsrTotalMs(Long l) {
        this.asrTotalMs = l;
    }

    public final boolean getAsrFallback() {
        return this.asrFallback;
    }

    public final void setAsrFallback(boolean z) {
        this.asrFallback = z;
    }

    public final String getAsrFallbackReason() {
        return this.asrFallbackReason;
    }

    public final void setAsrFallbackReason(String str) {
        this.asrFallbackReason = str;
    }

    public final String summary() {
        String str = this.asrProvider;
        if (str == null) {
            str = "-";
        }
        String str2 = this.asrModel;
        if (str2 == null) {
            str2 = "-";
        }
        String str3 = this.asrRoute;
        if (str3 == null) {
            str3 = "-";
        }
        String str4 = this.asrRouteReason;
        if (str4 == null) {
            str4 = "-";
        }
        Object obj = this.asrSetupMs;
        if (obj == null) {
            obj = "-";
        }
        Object obj2 = this.asrFirstPartialMs;
        if (obj2 == null) {
            obj2 = "-";
        }
        Object obj3 = this.asrFinalizeMs;
        if (obj3 == null) {
            obj3 = "-";
        }
        Object obj4 = this.asrTotalMs;
        if (obj4 == null) {
            obj4 = "-";
        }
        boolean z = this.asrFallback;
        String str5 = this.asrFallbackReason;
        return "asr provider=" + str + " model=" + str2 + " route=" + str3 + " reason=" + str4 + " setup=" + obj + " firstPartial=" + obj2 + " final=" + obj3 + " total=" + obj4 + " fallback=" + z + "/" + (str5 != null ? str5 : "-");
    }
}
