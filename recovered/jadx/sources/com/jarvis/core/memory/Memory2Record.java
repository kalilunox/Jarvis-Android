package com.jarvis.core.memory;

import com.jarvis.app.VoiceService;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Memory2.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010$\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\bHÆ\u0003J\t\u0010\"\u001a\u00020\nHÆ\u0003J\t\u0010#\u001a\u00020\fHÆ\u0003J\u0015\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000eHÆ\u0003J[\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000eHÆ\u0001J\u0013\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020*HÖ\u0001J\t\u0010+\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001d\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006,"}, d2 = {"Lcom/jarvis/core/memory/Memory2Record;", "", "id", "", "kind", "Lcom/jarvis/core/memory/Memory2Kind;", VoiceService.EXTRA_TEXT, "durability", "Lcom/jarvis/core/memory/Memory2Durability;", "createdAtMillis", "", "score", "", "metadata", "", "<init>", "(Ljava/lang/String;Lcom/jarvis/core/memory/Memory2Kind;Ljava/lang/String;Lcom/jarvis/core/memory/Memory2Durability;JFLjava/util/Map;)V", "getId", "()Ljava/lang/String;", "getKind", "()Lcom/jarvis/core/memory/Memory2Kind;", "getText", "getDurability", "()Lcom/jarvis/core/memory/Memory2Durability;", "getCreatedAtMillis", "()J", "getScore", "()F", "getMetadata", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class Memory2Record {
    private final long createdAtMillis;
    private final Memory2Durability durability;
    private final String id;
    private final Memory2Kind kind;
    private final Map<String, String> metadata;
    private final float score;
    private final String text;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Memory2Kind getKind() {
        return this.kind;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Memory2Durability getDurability() {
        return this.durability;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getCreatedAtMillis() {
        return this.createdAtMillis;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final float getScore() {
        return this.score;
    }

    public final Map<String, String> component7() {
        return this.metadata;
    }

    public final Memory2Record copy(String id, Memory2Kind kind, String text, Memory2Durability durability, long createdAtMillis, float score, Map<String, String> metadata) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(durability, "durability");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        return new Memory2Record(id, kind, text, durability, createdAtMillis, score, metadata);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Memory2Record)) {
            return false;
        }
        Memory2Record memory2Record = (Memory2Record) other;
        return Intrinsics.areEqual(this.id, memory2Record.id) && this.kind == memory2Record.kind && Intrinsics.areEqual(this.text, memory2Record.text) && this.durability == memory2Record.durability && this.createdAtMillis == memory2Record.createdAtMillis && Float.compare(this.score, memory2Record.score) == 0 && Intrinsics.areEqual(this.metadata, memory2Record.metadata);
    }

    public int hashCode() {
        return (((((((((((this.id.hashCode() * 31) + this.kind.hashCode()) * 31) + this.text.hashCode()) * 31) + this.durability.hashCode()) * 31) + Long.hashCode(this.createdAtMillis)) * 31) + Float.hashCode(this.score)) * 31) + this.metadata.hashCode();
    }

    public String toString() {
        return "Memory2Record(id=" + this.id + ", kind=" + this.kind + ", text=" + this.text + ", durability=" + this.durability + ", createdAtMillis=" + this.createdAtMillis + ", score=" + this.score + ", metadata=" + this.metadata + ")";
    }

    public Memory2Record(String id, Memory2Kind kind, String text, Memory2Durability durability, long createdAtMillis, float score, Map<String, String> metadata) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(durability, "durability");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        this.id = id;
        this.kind = kind;
        this.text = text;
        this.durability = durability;
        this.createdAtMillis = createdAtMillis;
        this.score = score;
        this.metadata = metadata;
        if (StringsKt.isBlank(this.id)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(!StringsKt.isBlank(this.text) && this.text.length() <= 2000)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        float f = this.score;
        if (!(0.0f <= f && f <= 1.0f)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (this.metadata.size() <= 16) {
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ Memory2Record(String str, Memory2Kind memory2Kind, String str2, Memory2Durability memory2Durability, long j, float f, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        float f2;
        Map mapEmptyMap;
        if ((i & 32) == 0) {
            f2 = f;
        } else {
            f2 = 0.5f;
        }
        if ((i & 64) == 0) {
            mapEmptyMap = map;
        } else {
            mapEmptyMap = MapsKt.emptyMap();
        }
        this(str, memory2Kind, str2, memory2Durability, j, f2, mapEmptyMap);
    }

    public final String getId() {
        return this.id;
    }

    public final Memory2Kind getKind() {
        return this.kind;
    }

    public final String getText() {
        return this.text;
    }

    public final Memory2Durability getDurability() {
        return this.durability;
    }

    public final long getCreatedAtMillis() {
        return this.createdAtMillis;
    }

    public final float getScore() {
        return this.score;
    }

    public final Map<String, String> getMetadata() {
        return this.metadata;
    }
}
