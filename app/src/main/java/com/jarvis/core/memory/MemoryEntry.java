package com.jarvis.core.memory;

import com.jarvis.core.companion.Sensitivity;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: MemoryEntry.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u000b\n\u0002\b\u001a\b\u0086\b\u0018\u00002\u00020\u0001B\u00ad\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u000e\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u000e\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u0016\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u0012\u0014\b\u0002\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0019\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u001b\u0012\u0006\u0010\u001c\u001a\u00020\u001d¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010>\u001a\u00020?2\b\b\u0002\u0010@\u001a\u00020\u000eJ\u0006\u0010A\u001a\u00020\u0003J\t\u0010B\u001a\u00020\u0003HÆ\u0003J\t\u0010C\u001a\u00020\u0005HÆ\u0003J\t\u0010D\u001a\u00020\u0003HÆ\u0003J\t\u0010E\u001a\u00020\bHÆ\u0003J\t\u0010F\u001a\u00020\nHÆ\u0003J\t\u0010G\u001a\u00020\fHÆ\u0003J\t\u0010H\u001a\u00020\u000eHÆ\u0003J\t\u0010I\u001a\u00020\u000eHÆ\u0003J\t\u0010J\u001a\u00020\u000eHÆ\u0003J\t\u0010K\u001a\u00020\u000eHÆ\u0003J\u0010\u0010L\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0002\u00101J\t\u0010M\u001a\u00020\u0014HÆ\u0003J\u000f\u0010N\u001a\b\u0012\u0004\u0012\u00020\u00030\u0016HÆ\u0003J\u000b\u0010O\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010P\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0019HÆ\u0003J\t\u0010Q\u001a\u00020\u001bHÆ\u0003J\t\u0010R\u001a\u00020\u001dHÆ\u0003JÎ\u0001\u0010S\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u000e2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00032\u0014\b\u0002\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001dHÆ\u0001¢\u0006\u0002\u0010TJ\u0013\u0010U\u001a\u00020?2\b\u0010V\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010W\u001a\u00020\u0014HÖ\u0001J\t\u0010X\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010!R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0011\u0010\u000f\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b-\u0010,R\u0011\u0010\u0010\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b.\u0010,R\u0011\u0010\u0011\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b/\u0010,R\u0015\u0010\u0012\u001a\u0004\u0018\u00010\u000e¢\u0006\n\n\u0002\u00102\u001a\u0004\b0\u00101R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u0016¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b7\u0010!R\u001d\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0019¢\u0006\b\n\u0000\u001a\u0004\b8\u00109R\u0011\u0010\u001a\u001a\u00020\u001b¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u0011\u0010\u001c\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b<\u0010=¨\u0006Y"}, d2 = {"Lcom/jarvis/core/memory/MemoryEntry;", "", "id", "", "type", "Lcom/jarvis/core/memory/MemoryKind;", "content", "provenance", "Lcom/jarvis/core/memory/MemoryProvenance;", "confidence", "", "sensitivity", "Lcom/jarvis/core/companion/Sensitivity;", "createdAtMillis", "", "updatedAtMillis", "firstObservedAtMillis", "lastObservedAtMillis", "expiresAtMillis", "evidenceCount", "", "evidenceObservationIds", "", "packageName", "entities", "", "status", "Lcom/jarvis/core/memory/MemoryStatus;", "disposition", "Lcom/jarvis/core/memory/MemoryDisposition;", "<init>", "(Ljava/lang/String;Lcom/jarvis/core/memory/MemoryKind;Ljava/lang/String;Lcom/jarvis/core/memory/MemoryProvenance;FLcom/jarvis/core/companion/Sensitivity;JJJJLjava/lang/Long;ILjava/util/List;Ljava/lang/String;Ljava/util/Map;Lcom/jarvis/core/memory/MemoryStatus;Lcom/jarvis/core/memory/MemoryDisposition;)V", "getId", "()Ljava/lang/String;", "getType", "()Lcom/jarvis/core/memory/MemoryKind;", "getContent", "getProvenance", "()Lcom/jarvis/core/memory/MemoryProvenance;", "getConfidence", "()F", "getSensitivity", "()Lcom/jarvis/core/companion/Sensitivity;", "getCreatedAtMillis", "()J", "getUpdatedAtMillis", "getFirstObservedAtMillis", "getLastObservedAtMillis", "getExpiresAtMillis", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getEvidenceCount", "()I", "getEvidenceObservationIds", "()Ljava/util/List;", "getPackageName", "getEntities", "()Ljava/util/Map;", "getStatus", "()Lcom/jarvis/core/memory/MemoryStatus;", "getDisposition", "()Lcom/jarvis/core/memory/MemoryDisposition;", "isAlive", "", "nowMillis", "explain", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "copy", "(Ljava/lang/String;Lcom/jarvis/core/memory/MemoryKind;Ljava/lang/String;Lcom/jarvis/core/memory/MemoryProvenance;FLcom/jarvis/core/companion/Sensitivity;JJJJLjava/lang/Long;ILjava/util/List;Ljava/lang/String;Ljava/util/Map;Lcom/jarvis/core/memory/MemoryStatus;Lcom/jarvis/core/memory/MemoryDisposition;)Lcom/jarvis/core/memory/MemoryEntry;", "equals", "other", "hashCode", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class MemoryEntry {
    private final float confidence;
    private final String content;
    private final long createdAtMillis;
    private final MemoryDisposition disposition;
    private final Map<String, String> entities;
    private final int evidenceCount;
    private final List<String> evidenceObservationIds;
    private final Long expiresAtMillis;
    private final long firstObservedAtMillis;
    private final String id;
    private final long lastObservedAtMillis;
    private final String packageName;
    private final MemoryProvenance provenance;
    private final Sensitivity sensitivity;
    private final MemoryStatus status;
    private final MemoryKind type;
    private final long updatedAtMillis;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final long getLastObservedAtMillis() {
        return this.lastObservedAtMillis;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final Long getExpiresAtMillis() {
        return this.expiresAtMillis;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final int getEvidenceCount() {
        return this.evidenceCount;
    }

    public final List<String> component13() {
        return this.evidenceObservationIds;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getPackageName() {
        return this.packageName;
    }

    public final Map<String, String> component15() {
        return this.entities;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final MemoryStatus getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final MemoryDisposition getDisposition() {
        return this.disposition;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final MemoryKind getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final MemoryProvenance getProvenance() {
        return this.provenance;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final float getConfidence() {
        return this.confidence;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Sensitivity getSensitivity() {
        return this.sensitivity;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final long getCreatedAtMillis() {
        return this.createdAtMillis;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final long getUpdatedAtMillis() {
        return this.updatedAtMillis;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final long getFirstObservedAtMillis() {
        return this.firstObservedAtMillis;
    }

    public final MemoryEntry copy(String id, MemoryKind type, String content, MemoryProvenance provenance, float confidence, Sensitivity sensitivity, long createdAtMillis, long updatedAtMillis, long firstObservedAtMillis, long lastObservedAtMillis, Long expiresAtMillis, int evidenceCount, List<String> evidenceObservationIds, String packageName, Map<String, String> entities, MemoryStatus status, MemoryDisposition disposition) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(provenance, "provenance");
        Intrinsics.checkNotNullParameter(sensitivity, "sensitivity");
        Intrinsics.checkNotNullParameter(evidenceObservationIds, "evidenceObservationIds");
        Intrinsics.checkNotNullParameter(entities, "entities");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(disposition, "disposition");
        return new MemoryEntry(id, type, content, provenance, confidence, sensitivity, createdAtMillis, updatedAtMillis, firstObservedAtMillis, lastObservedAtMillis, expiresAtMillis, evidenceCount, evidenceObservationIds, packageName, entities, status, disposition);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MemoryEntry)) {
            return false;
        }
        MemoryEntry memoryEntry = (MemoryEntry) other;
        return Intrinsics.areEqual(this.id, memoryEntry.id) && this.type == memoryEntry.type && Intrinsics.areEqual(this.content, memoryEntry.content) && this.provenance == memoryEntry.provenance && Float.compare(this.confidence, memoryEntry.confidence) == 0 && this.sensitivity == memoryEntry.sensitivity && this.createdAtMillis == memoryEntry.createdAtMillis && this.updatedAtMillis == memoryEntry.updatedAtMillis && this.firstObservedAtMillis == memoryEntry.firstObservedAtMillis && this.lastObservedAtMillis == memoryEntry.lastObservedAtMillis && Intrinsics.areEqual(this.expiresAtMillis, memoryEntry.expiresAtMillis) && this.evidenceCount == memoryEntry.evidenceCount && Intrinsics.areEqual(this.evidenceObservationIds, memoryEntry.evidenceObservationIds) && Intrinsics.areEqual(this.packageName, memoryEntry.packageName) && Intrinsics.areEqual(this.entities, memoryEntry.entities) && this.status == memoryEntry.status && this.disposition == memoryEntry.disposition;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((this.id.hashCode() * 31) + this.type.hashCode()) * 31) + this.content.hashCode()) * 31) + this.provenance.hashCode()) * 31) + Float.hashCode(this.confidence)) * 31) + this.sensitivity.hashCode()) * 31) + Long.hashCode(this.createdAtMillis)) * 31) + Long.hashCode(this.updatedAtMillis)) * 31) + Long.hashCode(this.firstObservedAtMillis)) * 31) + Long.hashCode(this.lastObservedAtMillis)) * 31) + (this.expiresAtMillis == null ? 0 : this.expiresAtMillis.hashCode())) * 31) + Integer.hashCode(this.evidenceCount)) * 31) + this.evidenceObservationIds.hashCode()) * 31) + (this.packageName != null ? this.packageName.hashCode() : 0)) * 31) + this.entities.hashCode()) * 31) + this.status.hashCode()) * 31) + this.disposition.hashCode();
    }

    public String toString() {
        return "MemoryEntry(id=" + this.id + ", type=" + this.type + ", content=" + this.content + ", provenance=" + this.provenance + ", confidence=" + this.confidence + ", sensitivity=" + this.sensitivity + ", createdAtMillis=" + this.createdAtMillis + ", updatedAtMillis=" + this.updatedAtMillis + ", firstObservedAtMillis=" + this.firstObservedAtMillis + ", lastObservedAtMillis=" + this.lastObservedAtMillis + ", expiresAtMillis=" + this.expiresAtMillis + ", evidenceCount=" + this.evidenceCount + ", evidenceObservationIds=" + this.evidenceObservationIds + ", packageName=" + this.packageName + ", entities=" + this.entities + ", status=" + this.status + ", disposition=" + this.disposition + ")";
    }

    public MemoryEntry(String id, MemoryKind type, String content, MemoryProvenance provenance, float confidence, Sensitivity sensitivity, long createdAtMillis, long updatedAtMillis, long firstObservedAtMillis, long lastObservedAtMillis, Long expiresAtMillis, int evidenceCount, List<String> evidenceObservationIds, String packageName, Map<String, String> entities, MemoryStatus status, MemoryDisposition disposition) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(provenance, "provenance");
        Intrinsics.checkNotNullParameter(sensitivity, "sensitivity");
        Intrinsics.checkNotNullParameter(evidenceObservationIds, "evidenceObservationIds");
        Intrinsics.checkNotNullParameter(entities, "entities");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(disposition, "disposition");
        this.id = id;
        this.type = type;
        this.content = content;
        this.provenance = provenance;
        this.confidence = confidence;
        this.sensitivity = sensitivity;
        this.createdAtMillis = createdAtMillis;
        this.updatedAtMillis = updatedAtMillis;
        this.firstObservedAtMillis = firstObservedAtMillis;
        this.lastObservedAtMillis = lastObservedAtMillis;
        this.expiresAtMillis = expiresAtMillis;
        this.evidenceCount = evidenceCount;
        this.evidenceObservationIds = evidenceObservationIds;
        this.packageName = packageName;
        this.entities = entities;
        this.status = status;
        this.disposition = disposition;
    }

    public /* synthetic */ MemoryEntry(String str, MemoryKind memoryKind, String str2, MemoryProvenance memoryProvenance, float f, Sensitivity sensitivity, long j, long j2, long j3, long j4, Long l, int i, List list, String str3, Map map, MemoryStatus memoryStatus, MemoryDisposition memoryDisposition, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, memoryKind, str2, memoryProvenance, f, sensitivity, j, j2, j3, j4, l, i, (i2 & 4096) != 0 ? CollectionsKt.emptyList() : list, (i2 & 8192) != 0 ? null : str3, (i2 & 16384) != 0 ? MapsKt.emptyMap() : map, (i2 & 32768) != 0 ? MemoryStatus.ACTIVE : memoryStatus, memoryDisposition);
    }

    public final String getId() {
        return this.id;
    }

    public final MemoryKind getType() {
        return this.type;
    }

    public final String getContent() {
        return this.content;
    }

    public final MemoryProvenance getProvenance() {
        return this.provenance;
    }

    public final float getConfidence() {
        return this.confidence;
    }

    public final Sensitivity getSensitivity() {
        return this.sensitivity;
    }

    public final long getCreatedAtMillis() {
        return this.createdAtMillis;
    }

    public final long getUpdatedAtMillis() {
        return this.updatedAtMillis;
    }

    public final long getFirstObservedAtMillis() {
        return this.firstObservedAtMillis;
    }

    public final long getLastObservedAtMillis() {
        return this.lastObservedAtMillis;
    }

    public final Long getExpiresAtMillis() {
        return this.expiresAtMillis;
    }

    public final int getEvidenceCount() {
        return this.evidenceCount;
    }

    public final List<String> getEvidenceObservationIds() {
        return this.evidenceObservationIds;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final Map<String, String> getEntities() {
        return this.entities;
    }

    public final MemoryStatus getStatus() {
        return this.status;
    }

    public final MemoryDisposition getDisposition() {
        return this.disposition;
    }

    public static /* synthetic */ boolean isAlive$default(MemoryEntry memoryEntry, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = System.currentTimeMillis();
        }
        return memoryEntry.isAlive(j);
    }

    public final boolean isAlive(long nowMillis) {
        if (this.status == MemoryStatus.DELETED || this.status == MemoryStatus.EXPIRED || this.status == MemoryStatus.ARCHIVED) {
            return false;
        }
        Long l = this.expiresAtMillis;
        if (l == null) {
            return true;
        }
        long exp = l.longValue();
        return nowMillis < exp;
    }

    public final String explain() {
        StringBuilder $this$explain_u24lambda_u240 = new StringBuilder();
        $this$explain_u24lambda_u240.append(this.content);
        $this$explain_u24lambda_u240.append(" [").append(this.provenance.name()).append("/").append(this.type.name()).append("]");
        StringBuilder sbAppend = $this$explain_u24lambda_u240.append(" conf=");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(Locale.US, "%.2f", Arrays.copyOf(new Object[]{Float.valueOf(this.confidence)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        sbAppend.append(str);
        $this$explain_u24lambda_u240.append(" sens=").append(this.sensitivity.name());
        $this$explain_u24lambda_u240.append(" status=").append(this.status.name());
        $this$explain_u24lambda_u240.append(" evidence=").append(this.evidenceCount);
        return $this$explain_u24lambda_u240.toString();
    }
}
