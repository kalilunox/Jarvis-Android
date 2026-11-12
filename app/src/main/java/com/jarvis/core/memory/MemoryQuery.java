package com.jarvis.core.memory;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MemoryRepository.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001e\b\u0086\b\u0018\u00002\u00020\u0001Ba\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010%\u001a\u00020\fHÆ\u0003J\t\u0010&\u001a\u00020\fHÆ\u0003J\t\u0010'\u001a\u00020\u000fHÆ\u0003Jc\u0010(\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000fHÆ\u0001J\u0013\u0010)\u001a\u00020\f2\b\u0010*\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010+\u001a\u00020\u000fHÖ\u0001J\t\u0010,\u001a\u00020\tHÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\r\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u0006-"}, d2 = {"Lcom/jarvis/core/memory/MemoryQuery;", "", "type", "Lcom/jarvis/core/memory/MemoryKind;", "status", "Lcom/jarvis/core/memory/MemoryStatus;", "provenance", "Lcom/jarvis/core/memory/MemoryProvenance;", "textContains", "", "packageName", "includeExpired", "", "includeDeleted", "limit", "", "<init>", "(Lcom/jarvis/core/memory/MemoryKind;Lcom/jarvis/core/memory/MemoryStatus;Lcom/jarvis/core/memory/MemoryProvenance;Ljava/lang/String;Ljava/lang/String;ZZI)V", "getType", "()Lcom/jarvis/core/memory/MemoryKind;", "getStatus", "()Lcom/jarvis/core/memory/MemoryStatus;", "getProvenance", "()Lcom/jarvis/core/memory/MemoryProvenance;", "getTextContains", "()Ljava/lang/String;", "getPackageName", "getIncludeExpired", "()Z", "getIncludeDeleted", "getLimit", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class MemoryQuery {
    private final boolean includeDeleted;
    private final boolean includeExpired;
    private final int limit;
    private final String packageName;
    private final MemoryProvenance provenance;
    private final MemoryStatus status;
    private final String textContains;
    private final MemoryKind type;

    public MemoryQuery() {
        this(null, null, null, null, null, false, false, 0, 255, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final MemoryKind getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final MemoryStatus getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final MemoryProvenance getProvenance() {
        return this.provenance;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getTextContains() {
        return this.textContains;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getPackageName() {
        return this.packageName;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getIncludeExpired() {
        return this.includeExpired;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getIncludeDeleted() {
        return this.includeDeleted;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getLimit() {
        return this.limit;
    }

    public final MemoryQuery copy(MemoryKind type, MemoryStatus status, MemoryProvenance provenance, String textContains, String packageName, boolean includeExpired, boolean includeDeleted, int limit) {
        return new MemoryQuery(type, status, provenance, textContains, packageName, includeExpired, includeDeleted, limit);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MemoryQuery)) {
            return false;
        }
        MemoryQuery memoryQuery = (MemoryQuery) other;
        return this.type == memoryQuery.type && this.status == memoryQuery.status && this.provenance == memoryQuery.provenance && Intrinsics.areEqual(this.textContains, memoryQuery.textContains) && Intrinsics.areEqual(this.packageName, memoryQuery.packageName) && this.includeExpired == memoryQuery.includeExpired && this.includeDeleted == memoryQuery.includeDeleted && this.limit == memoryQuery.limit;
    }

    public int hashCode() {
        return ((((((((((((((this.type == null ? 0 : this.type.hashCode()) * 31) + (this.status == null ? 0 : this.status.hashCode())) * 31) + (this.provenance == null ? 0 : this.provenance.hashCode())) * 31) + (this.textContains == null ? 0 : this.textContains.hashCode())) * 31) + (this.packageName != null ? this.packageName.hashCode() : 0)) * 31) + Boolean.hashCode(this.includeExpired)) * 31) + Boolean.hashCode(this.includeDeleted)) * 31) + Integer.hashCode(this.limit);
    }

    public String toString() {
        return "MemoryQuery(type=" + this.type + ", status=" + this.status + ", provenance=" + this.provenance + ", textContains=" + this.textContains + ", packageName=" + this.packageName + ", includeExpired=" + this.includeExpired + ", includeDeleted=" + this.includeDeleted + ", limit=" + this.limit + ")";
    }

    public MemoryQuery(MemoryKind type, MemoryStatus status, MemoryProvenance provenance, String textContains, String packageName, boolean includeExpired, boolean includeDeleted, int limit) {
        this.type = type;
        this.status = status;
        this.provenance = provenance;
        this.textContains = textContains;
        this.packageName = packageName;
        this.includeExpired = includeExpired;
        this.includeDeleted = includeDeleted;
        this.limit = limit;
    }

    public /* synthetic */ MemoryQuery(MemoryKind memoryKind, MemoryStatus memoryStatus, MemoryProvenance memoryProvenance, String str, String str2, boolean z, boolean z2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : memoryKind, (i2 & 2) != 0 ? null : memoryStatus, (i2 & 4) != 0 ? null : memoryProvenance, (i2 & 8) != 0 ? null : str, (i2 & 16) != 0 ? null : str2, (i2 & 32) != 0 ? false : z, (i2 & 64) != 0 ? false : z2, (i2 & 128) != 0 ? 50 : i);
    }

    public final MemoryKind getType() {
        return this.type;
    }

    public final MemoryStatus getStatus() {
        return this.status;
    }

    public final MemoryProvenance getProvenance() {
        return this.provenance;
    }

    public final String getTextContains() {
        return this.textContains;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final boolean getIncludeExpired() {
        return this.includeExpired;
    }

    public final boolean getIncludeDeleted() {
        return this.includeDeleted;
    }

    public final int getLimit() {
        return this.limit;
    }
}
