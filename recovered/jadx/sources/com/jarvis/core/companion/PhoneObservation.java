package com.jarvis.core.companion;

import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PhoneObservation.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b!\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001By\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u0007HÆ\u0003J\t\u0010+\u001a\u00020\tHÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000eHÆ\u0003J\t\u00100\u001a\u00020\u0010HÆ\u0003J\t\u00101\u001a\u00020\u0012HÆ\u0003J\u007f\u00102\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0012HÆ\u0001J\u0013\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00106\u001a\u000207HÖ\u0001J\t\u00108\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0016R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0016R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0016R\u001d\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000e¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010&\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b'\u0010\u001a¨\u00069"}, d2 = {"Lcom/jarvis/core/companion/PhoneObservation;", "", "id", "", "timestampMillis", "", "source", "Lcom/jarvis/core/companion/ObservationSource;", "type", "Lcom/jarvis/core/companion/ObservationType;", "packageName", "screenId", "summary", "entities", "", "sensitivity", "Lcom/jarvis/core/companion/Sensitivity;", "confidence", "", "<init>", "(Ljava/lang/String;JLcom/jarvis/core/companion/ObservationSource;Lcom/jarvis/core/companion/ObservationType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Lcom/jarvis/core/companion/Sensitivity;F)V", "getId", "()Ljava/lang/String;", "getTimestampMillis", "()J", "getSource", "()Lcom/jarvis/core/companion/ObservationSource;", "getType", "()Lcom/jarvis/core/companion/ObservationType;", "getPackageName", "getScreenId", "getSummary", "getEntities", "()Ljava/util/Map;", "getSensitivity", "()Lcom/jarvis/core/companion/Sensitivity;", "getConfidence", "()F", "provenance", "getProvenance", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class PhoneObservation {
    private final float confidence;
    private final Map<String, String> entities;
    private final String id;
    private final String packageName;
    private final String screenId;
    private final Sensitivity sensitivity;
    private final ObservationSource source;
    private final String summary;
    private final long timestampMillis;
    private final ObservationType type;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final float getConfidence() {
        return this.confidence;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getTimestampMillis() {
        return this.timestampMillis;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ObservationSource getSource() {
        return this.source;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final ObservationType getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getPackageName() {
        return this.packageName;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getScreenId() {
        return this.screenId;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getSummary() {
        return this.summary;
    }

    public final Map<String, String> component8() {
        return this.entities;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final Sensitivity getSensitivity() {
        return this.sensitivity;
    }

    public final PhoneObservation copy(String id, long timestampMillis, ObservationSource source, ObservationType type, String packageName, String screenId, String summary, Map<String, String> entities, Sensitivity sensitivity, float confidence) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(entities, "entities");
        Intrinsics.checkNotNullParameter(sensitivity, "sensitivity");
        return new PhoneObservation(id, timestampMillis, source, type, packageName, screenId, summary, entities, sensitivity, confidence);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PhoneObservation)) {
            return false;
        }
        PhoneObservation phoneObservation = (PhoneObservation) other;
        return Intrinsics.areEqual(this.id, phoneObservation.id) && this.timestampMillis == phoneObservation.timestampMillis && this.source == phoneObservation.source && this.type == phoneObservation.type && Intrinsics.areEqual(this.packageName, phoneObservation.packageName) && Intrinsics.areEqual(this.screenId, phoneObservation.screenId) && Intrinsics.areEqual(this.summary, phoneObservation.summary) && Intrinsics.areEqual(this.entities, phoneObservation.entities) && this.sensitivity == phoneObservation.sensitivity && Float.compare(this.confidence, phoneObservation.confidence) == 0;
    }

    public int hashCode() {
        return (((((((((((((((((this.id.hashCode() * 31) + Long.hashCode(this.timestampMillis)) * 31) + this.source.hashCode()) * 31) + this.type.hashCode()) * 31) + (this.packageName == null ? 0 : this.packageName.hashCode())) * 31) + (this.screenId == null ? 0 : this.screenId.hashCode())) * 31) + (this.summary != null ? this.summary.hashCode() : 0)) * 31) + this.entities.hashCode()) * 31) + this.sensitivity.hashCode()) * 31) + Float.hashCode(this.confidence);
    }

    public String toString() {
        return "PhoneObservation(id=" + this.id + ", timestampMillis=" + this.timestampMillis + ", source=" + this.source + ", type=" + this.type + ", packageName=" + this.packageName + ", screenId=" + this.screenId + ", summary=" + this.summary + ", entities=" + this.entities + ", sensitivity=" + this.sensitivity + ", confidence=" + this.confidence + ")";
    }

    public PhoneObservation(String id, long timestampMillis, ObservationSource source, ObservationType type, String packageName, String screenId, String summary, Map<String, String> entities, Sensitivity sensitivity, float confidence) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(entities, "entities");
        Intrinsics.checkNotNullParameter(sensitivity, "sensitivity");
        this.id = id;
        this.timestampMillis = timestampMillis;
        this.source = source;
        this.type = type;
        this.packageName = packageName;
        this.screenId = screenId;
        this.summary = summary;
        this.entities = entities;
        this.sensitivity = sensitivity;
        this.confidence = confidence;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ PhoneObservation(String str, long j, ObservationSource observationSource, ObservationType observationType, String str2, String str3, String str4, Map map, Sensitivity sensitivity, float f, int i, DefaultConstructorMarker defaultConstructorMarker) {
        String str5;
        if ((i & 1) != 0) {
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            str5 = string;
        } else {
            str5 = str;
        }
        this(str5, (i & 2) != 0 ? System.currentTimeMillis() : j, observationSource, observationType, (i & 16) != 0 ? null : str2, (i & 32) != 0 ? null : str3, (i & 64) != 0 ? null : str4, (i & 128) != 0 ? MapsKt.emptyMap() : map, (i & 256) != 0 ? Sensitivity.NORMAL : sensitivity, (i & 512) != 0 ? 1.0f : f);
    }

    public final String getId() {
        return this.id;
    }

    public final long getTimestampMillis() {
        return this.timestampMillis;
    }

    public final ObservationSource getSource() {
        return this.source;
    }

    public final ObservationType getType() {
        return this.type;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final String getScreenId() {
        return this.screenId;
    }

    public final String getSummary() {
        return this.summary;
    }

    public final Map<String, String> getEntities() {
        return this.entities;
    }

    public final Sensitivity getSensitivity() {
        return this.sensitivity;
    }

    public final float getConfidence() {
        return this.confidence;
    }

    public final ObservationSource getProvenance() {
        return this.source;
    }
}
