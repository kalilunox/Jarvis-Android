package com.jarvis.core.memory;

import com.jarvis.core.companion.Sensitivity;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MemoryCandidate.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010$\n\u0002\b%\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0083\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u0014\u0012\u0014\b\u0002\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0005HÆ\u0003J\t\u00100\u001a\u00020\u0007HÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\t\u00102\u001a\u00020\nHÆ\u0003J\t\u00103\u001a\u00020\fHÆ\u0003J\t\u00104\u001a\u00020\u000eHÆ\u0003J\t\u00105\u001a\u00020\u000eHÆ\u0003J\t\u00106\u001a\u00020\u0011HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u00108\u001a\b\u0012\u0004\u0012\u00020\u00030\u0014HÆ\u0003J\u0015\u00109\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0016HÆ\u0003J\u0095\u0001\u0010:\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u00142\u0014\b\u0002\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0016HÆ\u0001J\u0013\u0010;\u001a\u00020<2\b\u0010=\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010>\u001a\u00020\nHÖ\u0001J\t\u0010?\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001aR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u000f\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b&\u0010%R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001aR\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u0014¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u001d\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0016¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-¨\u0006@"}, d2 = {"Lcom/jarvis/core/memory/MemoryCandidate;", "", "id", "", "kind", "Lcom/jarvis/core/memory/MemoryKind;", "provenance", "Lcom/jarvis/core/memory/MemoryProvenance;", "statement", "evidenceCount", "", "confidence", "", "firstSeenMillis", "", "lastSeenMillis", "sensitivity", "Lcom/jarvis/core/companion/Sensitivity;", "packageName", "evidenceObservationIds", "", "entities", "", "<init>", "(Ljava/lang/String;Lcom/jarvis/core/memory/MemoryKind;Lcom/jarvis/core/memory/MemoryProvenance;Ljava/lang/String;IFJJLcom/jarvis/core/companion/Sensitivity;Ljava/lang/String;Ljava/util/List;Ljava/util/Map;)V", "getId", "()Ljava/lang/String;", "getKind", "()Lcom/jarvis/core/memory/MemoryKind;", "getProvenance", "()Lcom/jarvis/core/memory/MemoryProvenance;", "getStatement", "getEvidenceCount", "()I", "getConfidence", "()F", "getFirstSeenMillis", "()J", "getLastSeenMillis", "getSensitivity", "()Lcom/jarvis/core/companion/Sensitivity;", "getPackageName", "getEvidenceObservationIds", "()Ljava/util/List;", "getEntities", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "equals", "", "other", "hashCode", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class MemoryCandidate {
    private final float confidence;
    private final Map<String, String> entities;
    private final int evidenceCount;
    private final List<String> evidenceObservationIds;
    private final long firstSeenMillis;
    private final String id;
    private final MemoryKind kind;
    private final long lastSeenMillis;
    private final String packageName;
    private final MemoryProvenance provenance;
    private final Sensitivity sensitivity;
    private final String statement;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getPackageName() {
        return this.packageName;
    }

    public final List<String> component11() {
        return this.evidenceObservationIds;
    }

    public final Map<String, String> component12() {
        return this.entities;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final MemoryKind getKind() {
        return this.kind;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final MemoryProvenance getProvenance() {
        return this.provenance;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getStatement() {
        return this.statement;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getEvidenceCount() {
        return this.evidenceCount;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final float getConfidence() {
        return this.confidence;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final long getFirstSeenMillis() {
        return this.firstSeenMillis;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final long getLastSeenMillis() {
        return this.lastSeenMillis;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final Sensitivity getSensitivity() {
        return this.sensitivity;
    }

    public final MemoryCandidate copy(String id, MemoryKind kind, MemoryProvenance provenance, String statement, int evidenceCount, float confidence, long firstSeenMillis, long lastSeenMillis, Sensitivity sensitivity, String packageName, List<String> evidenceObservationIds, Map<String, String> entities) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(provenance, "provenance");
        Intrinsics.checkNotNullParameter(statement, "statement");
        Intrinsics.checkNotNullParameter(sensitivity, "sensitivity");
        Intrinsics.checkNotNullParameter(evidenceObservationIds, "evidenceObservationIds");
        Intrinsics.checkNotNullParameter(entities, "entities");
        return new MemoryCandidate(id, kind, provenance, statement, evidenceCount, confidence, firstSeenMillis, lastSeenMillis, sensitivity, packageName, evidenceObservationIds, entities);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MemoryCandidate)) {
            return false;
        }
        MemoryCandidate memoryCandidate = (MemoryCandidate) other;
        return Intrinsics.areEqual(this.id, memoryCandidate.id) && this.kind == memoryCandidate.kind && this.provenance == memoryCandidate.provenance && Intrinsics.areEqual(this.statement, memoryCandidate.statement) && this.evidenceCount == memoryCandidate.evidenceCount && Float.compare(this.confidence, memoryCandidate.confidence) == 0 && this.firstSeenMillis == memoryCandidate.firstSeenMillis && this.lastSeenMillis == memoryCandidate.lastSeenMillis && this.sensitivity == memoryCandidate.sensitivity && Intrinsics.areEqual(this.packageName, memoryCandidate.packageName) && Intrinsics.areEqual(this.evidenceObservationIds, memoryCandidate.evidenceObservationIds) && Intrinsics.areEqual(this.entities, memoryCandidate.entities);
    }

    public int hashCode() {
        return (((((((((((((((((((((this.id.hashCode() * 31) + this.kind.hashCode()) * 31) + this.provenance.hashCode()) * 31) + this.statement.hashCode()) * 31) + Integer.hashCode(this.evidenceCount)) * 31) + Float.hashCode(this.confidence)) * 31) + Long.hashCode(this.firstSeenMillis)) * 31) + Long.hashCode(this.lastSeenMillis)) * 31) + this.sensitivity.hashCode()) * 31) + (this.packageName == null ? 0 : this.packageName.hashCode())) * 31) + this.evidenceObservationIds.hashCode()) * 31) + this.entities.hashCode();
    }

    public String toString() {
        return "MemoryCandidate(id=" + this.id + ", kind=" + this.kind + ", provenance=" + this.provenance + ", statement=" + this.statement + ", evidenceCount=" + this.evidenceCount + ", confidence=" + this.confidence + ", firstSeenMillis=" + this.firstSeenMillis + ", lastSeenMillis=" + this.lastSeenMillis + ", sensitivity=" + this.sensitivity + ", packageName=" + this.packageName + ", evidenceObservationIds=" + this.evidenceObservationIds + ", entities=" + this.entities + ")";
    }

    public MemoryCandidate(String id, MemoryKind kind, MemoryProvenance provenance, String statement, int evidenceCount, float confidence, long firstSeenMillis, long lastSeenMillis, Sensitivity sensitivity, String packageName, List<String> evidenceObservationIds, Map<String, String> entities) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(provenance, "provenance");
        Intrinsics.checkNotNullParameter(statement, "statement");
        Intrinsics.checkNotNullParameter(sensitivity, "sensitivity");
        Intrinsics.checkNotNullParameter(evidenceObservationIds, "evidenceObservationIds");
        Intrinsics.checkNotNullParameter(entities, "entities");
        this.id = id;
        this.kind = kind;
        this.provenance = provenance;
        this.statement = statement;
        this.evidenceCount = evidenceCount;
        this.confidence = confidence;
        this.firstSeenMillis = firstSeenMillis;
        this.lastSeenMillis = lastSeenMillis;
        this.sensitivity = sensitivity;
        this.packageName = packageName;
        this.evidenceObservationIds = evidenceObservationIds;
        this.entities = entities;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ MemoryCandidate(String str, MemoryKind memoryKind, MemoryProvenance memoryProvenance, String str2, int i, float f, long j, long j2, Sensitivity sensitivity, String str3, List list, Map map, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        String str4;
        if ((i2 & 1) != 0) {
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            str4 = string;
        } else {
            str4 = str;
        }
        this(str4, memoryKind, memoryProvenance, str2, i, f, j, j2, sensitivity, (i2 & 512) != 0 ? null : str3, (i2 & 1024) != 0 ? CollectionsKt.emptyList() : list, (i2 & 2048) != 0 ? MapsKt.emptyMap() : map);
    }

    public final String getId() {
        return this.id;
    }

    public final MemoryKind getKind() {
        return this.kind;
    }

    public final MemoryProvenance getProvenance() {
        return this.provenance;
    }

    public final String getStatement() {
        return this.statement;
    }

    public final int getEvidenceCount() {
        return this.evidenceCount;
    }

    public final float getConfidence() {
        return this.confidence;
    }

    public final long getFirstSeenMillis() {
        return this.firstSeenMillis;
    }

    public final long getLastSeenMillis() {
        return this.lastSeenMillis;
    }

    public final Sensitivity getSensitivity() {
        return this.sensitivity;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final List<String> getEvidenceObservationIds() {
        return this.evidenceObservationIds;
    }

    public final Map<String, String> getEntities() {
        return this.entities;
    }
}
