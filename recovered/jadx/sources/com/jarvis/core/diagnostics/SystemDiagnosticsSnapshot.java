package com.jarvis.core.diagnostics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SystemDiagnostics.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b5\b\u0086\b\u0018\u00002\u00020\u0001B¯\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\u000b\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u000b\u0012\u0006\u0010\u0011\u001a\u00020\u000b\u0012\u0006\u0010\u0012\u001a\u00020\u000b\u0012\u0006\u0010\u0013\u001a\u00020\u0005\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00150\u0018\u0012\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00150\u0018¢\u0006\u0004\b\u001a\u0010\u001bJ\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u00150\u0018J\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\u0005HÆ\u0003J\t\u00107\u001a\u00020\u0005HÆ\u0003J\t\u00108\u001a\u00020\u0005HÆ\u0003J\t\u00109\u001a\u00020\u0005HÆ\u0003J\t\u0010:\u001a\u00020\u0005HÆ\u0003J\t\u0010;\u001a\u00020\u000bHÆ\u0003J\t\u0010<\u001a\u00020\u000bHÆ\u0003J\t\u0010=\u001a\u00020\u000bHÆ\u0003J\t\u0010>\u001a\u00020\u000bHÆ\u0003J\t\u0010?\u001a\u00020\u0005HÆ\u0003J\t\u0010@\u001a\u00020\u000bHÆ\u0003J\t\u0010A\u001a\u00020\u000bHÆ\u0003J\t\u0010B\u001a\u00020\u000bHÆ\u0003J\t\u0010C\u001a\u00020\u0005HÆ\u0003J\t\u0010D\u001a\u00020\u0015HÆ\u0003J\t\u0010E\u001a\u00020\u0015HÆ\u0003J\u000f\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00150\u0018HÆ\u0003J\u000f\u0010G\u001a\b\u0012\u0004\u0012\u00020\u00150\u0018HÆ\u0003JÓ\u0001\u0010H\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u000b2\b\b\u0002\u0010\u0011\u001a\u00020\u000b2\b\b\u0002\u0010\u0012\u001a\u00020\u000b2\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00152\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00150\u00182\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00150\u0018HÆ\u0001J\u0013\u0010I\u001a\u00020\u00052\b\u0010J\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010K\u001a\u00020\u000bHÖ\u0001J\t\u0010L\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001fR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001fR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b&\u0010%R\u0011\u0010\r\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b'\u0010%R\u0011\u0010\u000e\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b(\u0010%R\u0011\u0010\u000f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001fR\u0011\u0010\u0010\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b*\u0010%R\u0011\u0010\u0011\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b+\u0010%R\u0011\u0010\u0012\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b,\u0010%R\u0011\u0010\u0013\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001fR\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0011\u0010\u0016\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b0\u0010/R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00150\u0018¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00150\u0018¢\u0006\b\n\u0000\u001a\u0004\b3\u00102¨\u0006M"}, d2 = {"Lcom/jarvis/core/diagnostics/SystemDiagnosticsSnapshot;", "", "capturedAtMillis", "", "agentLoopPresent", "", "plannerPresent", "aiRouterPresent", "cloudKeyConfigured", "preferCloud", "providersLocal", "", "providersCloud", "providersAvailable", "catalogSize", "discoveryOn", "routinesSaved", "memory2Durable", "remoteDevices", "visionAvailable", "asrRoute", "", "ttsRoute", "notes", "", "featureStatuses", "<init>", "(JZZZZZIIIIZIIIZLjava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getCapturedAtMillis", "()J", "getAgentLoopPresent", "()Z", "getPlannerPresent", "getAiRouterPresent", "getCloudKeyConfigured", "getPreferCloud", "getProvidersLocal", "()I", "getProvidersCloud", "getProvidersAvailable", "getCatalogSize", "getDiscoveryOn", "getRoutinesSaved", "getMemory2Durable", "getRemoteDevices", "getVisionAvailable", "getAsrRoute", "()Ljava/lang/String;", "getTtsRoute", "getNotes", "()Ljava/util/List;", "getFeatureStatuses", "lines", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "copy", "equals", "other", "hashCode", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class SystemDiagnosticsSnapshot {
    private final boolean agentLoopPresent;
    private final boolean aiRouterPresent;
    private final String asrRoute;
    private final long capturedAtMillis;
    private final int catalogSize;
    private final boolean cloudKeyConfigured;
    private final boolean discoveryOn;
    private final List<String> featureStatuses;
    private final int memory2Durable;
    private final List<String> notes;
    private final boolean plannerPresent;
    private final boolean preferCloud;
    private final int providersAvailable;
    private final int providersCloud;
    private final int providersLocal;
    private final int remoteDevices;
    private final int routinesSaved;
    private final String ttsRoute;
    private final boolean visionAvailable;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getCapturedAtMillis() {
        return this.capturedAtMillis;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final int getCatalogSize() {
        return this.catalogSize;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final boolean getDiscoveryOn() {
        return this.discoveryOn;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final int getRoutinesSaved() {
        return this.routinesSaved;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final int getMemory2Durable() {
        return this.memory2Durable;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final int getRemoteDevices() {
        return this.remoteDevices;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final boolean getVisionAvailable() {
        return this.visionAvailable;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getAsrRoute() {
        return this.asrRoute;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final String getTtsRoute() {
        return this.ttsRoute;
    }

    public final List<String> component18() {
        return this.notes;
    }

    public final List<String> component19() {
        return this.featureStatuses;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getAgentLoopPresent() {
        return this.agentLoopPresent;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getPlannerPresent() {
        return this.plannerPresent;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getAiRouterPresent() {
        return this.aiRouterPresent;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getCloudKeyConfigured() {
        return this.cloudKeyConfigured;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getPreferCloud() {
        return this.preferCloud;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getProvidersLocal() {
        return this.providersLocal;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getProvidersCloud() {
        return this.providersCloud;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getProvidersAvailable() {
        return this.providersAvailable;
    }

    public final SystemDiagnosticsSnapshot copy(long capturedAtMillis, boolean agentLoopPresent, boolean plannerPresent, boolean aiRouterPresent, boolean cloudKeyConfigured, boolean preferCloud, int providersLocal, int providersCloud, int providersAvailable, int catalogSize, boolean discoveryOn, int routinesSaved, int memory2Durable, int remoteDevices, boolean visionAvailable, String asrRoute, String ttsRoute, List<String> notes, List<String> featureStatuses) {
        Intrinsics.checkNotNullParameter(asrRoute, "asrRoute");
        Intrinsics.checkNotNullParameter(ttsRoute, "ttsRoute");
        Intrinsics.checkNotNullParameter(notes, "notes");
        Intrinsics.checkNotNullParameter(featureStatuses, "featureStatuses");
        return new SystemDiagnosticsSnapshot(capturedAtMillis, agentLoopPresent, plannerPresent, aiRouterPresent, cloudKeyConfigured, preferCloud, providersLocal, providersCloud, providersAvailable, catalogSize, discoveryOn, routinesSaved, memory2Durable, remoteDevices, visionAvailable, asrRoute, ttsRoute, notes, featureStatuses);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SystemDiagnosticsSnapshot)) {
            return false;
        }
        SystemDiagnosticsSnapshot systemDiagnosticsSnapshot = (SystemDiagnosticsSnapshot) other;
        return this.capturedAtMillis == systemDiagnosticsSnapshot.capturedAtMillis && this.agentLoopPresent == systemDiagnosticsSnapshot.agentLoopPresent && this.plannerPresent == systemDiagnosticsSnapshot.plannerPresent && this.aiRouterPresent == systemDiagnosticsSnapshot.aiRouterPresent && this.cloudKeyConfigured == systemDiagnosticsSnapshot.cloudKeyConfigured && this.preferCloud == systemDiagnosticsSnapshot.preferCloud && this.providersLocal == systemDiagnosticsSnapshot.providersLocal && this.providersCloud == systemDiagnosticsSnapshot.providersCloud && this.providersAvailable == systemDiagnosticsSnapshot.providersAvailable && this.catalogSize == systemDiagnosticsSnapshot.catalogSize && this.discoveryOn == systemDiagnosticsSnapshot.discoveryOn && this.routinesSaved == systemDiagnosticsSnapshot.routinesSaved && this.memory2Durable == systemDiagnosticsSnapshot.memory2Durable && this.remoteDevices == systemDiagnosticsSnapshot.remoteDevices && this.visionAvailable == systemDiagnosticsSnapshot.visionAvailable && Intrinsics.areEqual(this.asrRoute, systemDiagnosticsSnapshot.asrRoute) && Intrinsics.areEqual(this.ttsRoute, systemDiagnosticsSnapshot.ttsRoute) && Intrinsics.areEqual(this.notes, systemDiagnosticsSnapshot.notes) && Intrinsics.areEqual(this.featureStatuses, systemDiagnosticsSnapshot.featureStatuses);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((Long.hashCode(this.capturedAtMillis) * 31) + Boolean.hashCode(this.agentLoopPresent)) * 31) + Boolean.hashCode(this.plannerPresent)) * 31) + Boolean.hashCode(this.aiRouterPresent)) * 31) + Boolean.hashCode(this.cloudKeyConfigured)) * 31) + Boolean.hashCode(this.preferCloud)) * 31) + Integer.hashCode(this.providersLocal)) * 31) + Integer.hashCode(this.providersCloud)) * 31) + Integer.hashCode(this.providersAvailable)) * 31) + Integer.hashCode(this.catalogSize)) * 31) + Boolean.hashCode(this.discoveryOn)) * 31) + Integer.hashCode(this.routinesSaved)) * 31) + Integer.hashCode(this.memory2Durable)) * 31) + Integer.hashCode(this.remoteDevices)) * 31) + Boolean.hashCode(this.visionAvailable)) * 31) + this.asrRoute.hashCode()) * 31) + this.ttsRoute.hashCode()) * 31) + this.notes.hashCode()) * 31) + this.featureStatuses.hashCode();
    }

    public String toString() {
        return "SystemDiagnosticsSnapshot(capturedAtMillis=" + this.capturedAtMillis + ", agentLoopPresent=" + this.agentLoopPresent + ", plannerPresent=" + this.plannerPresent + ", aiRouterPresent=" + this.aiRouterPresent + ", cloudKeyConfigured=" + this.cloudKeyConfigured + ", preferCloud=" + this.preferCloud + ", providersLocal=" + this.providersLocal + ", providersCloud=" + this.providersCloud + ", providersAvailable=" + this.providersAvailable + ", catalogSize=" + this.catalogSize + ", discoveryOn=" + this.discoveryOn + ", routinesSaved=" + this.routinesSaved + ", memory2Durable=" + this.memory2Durable + ", remoteDevices=" + this.remoteDevices + ", visionAvailable=" + this.visionAvailable + ", asrRoute=" + this.asrRoute + ", ttsRoute=" + this.ttsRoute + ", notes=" + this.notes + ", featureStatuses=" + this.featureStatuses + ")";
    }

    public SystemDiagnosticsSnapshot(long capturedAtMillis, boolean agentLoopPresent, boolean plannerPresent, boolean aiRouterPresent, boolean cloudKeyConfigured, boolean preferCloud, int providersLocal, int providersCloud, int providersAvailable, int catalogSize, boolean discoveryOn, int routinesSaved, int memory2Durable, int remoteDevices, boolean visionAvailable, String asrRoute, String ttsRoute, List<String> notes, List<String> featureStatuses) {
        Intrinsics.checkNotNullParameter(asrRoute, "asrRoute");
        Intrinsics.checkNotNullParameter(ttsRoute, "ttsRoute");
        Intrinsics.checkNotNullParameter(notes, "notes");
        Intrinsics.checkNotNullParameter(featureStatuses, "featureStatuses");
        this.capturedAtMillis = capturedAtMillis;
        this.agentLoopPresent = agentLoopPresent;
        this.plannerPresent = plannerPresent;
        this.aiRouterPresent = aiRouterPresent;
        this.cloudKeyConfigured = cloudKeyConfigured;
        this.preferCloud = preferCloud;
        this.providersLocal = providersLocal;
        this.providersCloud = providersCloud;
        this.providersAvailable = providersAvailable;
        this.catalogSize = catalogSize;
        this.discoveryOn = discoveryOn;
        this.routinesSaved = routinesSaved;
        this.memory2Durable = memory2Durable;
        this.remoteDevices = remoteDevices;
        this.visionAvailable = visionAvailable;
        this.asrRoute = asrRoute;
        this.ttsRoute = ttsRoute;
        this.notes = notes;
        this.featureStatuses = featureStatuses;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ SystemDiagnosticsSnapshot(long j, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i, int i2, int i3, int i4, boolean z6, int i5, int i6, int i7, boolean z7, String str, String str2, List list, List list2, int i8, DefaultConstructorMarker defaultConstructorMarker) {
        List listEmptyList;
        List listEmptyList2;
        if ((i8 & 131072) == 0) {
            listEmptyList = list;
        } else {
            listEmptyList = CollectionsKt.emptyList();
        }
        if ((i8 & 262144) == 0) {
            listEmptyList2 = list2;
        } else {
            listEmptyList2 = CollectionsKt.emptyList();
        }
        this(j, z, z2, z3, z4, z5, i, i2, i3, i4, z6, i5, i6, i7, z7, str, str2, listEmptyList, listEmptyList2);
    }

    public final long getCapturedAtMillis() {
        return this.capturedAtMillis;
    }

    public final boolean getAgentLoopPresent() {
        return this.agentLoopPresent;
    }

    public final boolean getPlannerPresent() {
        return this.plannerPresent;
    }

    public final boolean getAiRouterPresent() {
        return this.aiRouterPresent;
    }

    public final boolean getCloudKeyConfigured() {
        return this.cloudKeyConfigured;
    }

    public final boolean getPreferCloud() {
        return this.preferCloud;
    }

    public final int getProvidersLocal() {
        return this.providersLocal;
    }

    public final int getProvidersCloud() {
        return this.providersCloud;
    }

    public final int getProvidersAvailable() {
        return this.providersAvailable;
    }

    public final int getCatalogSize() {
        return this.catalogSize;
    }

    public final boolean getDiscoveryOn() {
        return this.discoveryOn;
    }

    public final int getRoutinesSaved() {
        return this.routinesSaved;
    }

    public final int getMemory2Durable() {
        return this.memory2Durable;
    }

    public final int getRemoteDevices() {
        return this.remoteDevices;
    }

    public final boolean getVisionAvailable() {
        return this.visionAvailable;
    }

    public final String getAsrRoute() {
        return this.asrRoute;
    }

    public final String getTtsRoute() {
        return this.ttsRoute;
    }

    public final List<String> getNotes() {
        return this.notes;
    }

    public final List<String> getFeatureStatuses() {
        return this.featureStatuses;
    }

    public final List<String> lines() {
        List listListOf = CollectionsKt.listOf((Object[]) new String[]{"agentLoop=" + this.agentLoopPresent + " planner=" + this.plannerPresent + " router=" + this.aiRouterPresent, "cloudKey=" + this.cloudKeyConfigured + " preferCloud=" + this.preferCloud, "providers local=" + this.providersLocal + " cloud=" + this.providersCloud + " available=" + this.providersAvailable, "catalog=" + this.catalogSize + " discovery=" + this.discoveryOn, "routines=" + this.routinesSaved + " memory2Durable=" + this.memory2Durable + " remote=" + this.remoteDevices, "vision=" + this.visionAvailable + " asr=" + this.asrRoute + " tts=" + this.ttsRoute});
        Iterable $this$map$iv = this.featureStatuses;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            destination$iv$iv.add("feature " + it);
        }
        return CollectionsKt.plus((Collection) CollectionsKt.plus((Collection) listListOf, destination$iv$iv), (Iterable) this.notes);
    }
}
