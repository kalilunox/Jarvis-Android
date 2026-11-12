package com.jarvis.core.common.model;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Intent.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 %2\u00020\u0001:\u0001%BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\u0015\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\t\u0010\u001c\u001a\u00020\nHÆ\u0003J\t\u0010\u001d\u001a\u00020\fHÆ\u0003JG\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006&"}, d2 = {"Lcom/jarvis/core/common/model/Intent;", "", "type", "Lcom/jarvis/core/common/model/IntentType;", "normalizedText", "", "slots", "", "Lcom/jarvis/core/common/model/Slot;", "confidence", "", "confidenceLevel", "Lcom/jarvis/core/common/model/ConfidenceLevel;", "<init>", "(Lcom/jarvis/core/common/model/IntentType;Ljava/lang/String;Ljava/util/Map;FLcom/jarvis/core/common/model/ConfidenceLevel;)V", "getType", "()Lcom/jarvis/core/common/model/IntentType;", "getNormalizedText", "()Ljava/lang/String;", "getSlots", "()Ljava/util/Map;", "getConfidence", "()F", "getConfidenceLevel", "()Lcom/jarvis/core/common/model/ConfidenceLevel;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class Intent {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final float confidence;
    private final ConfidenceLevel confidenceLevel;
    private final String normalizedText;
    private final Map<String, Slot> slots;
    private final IntentType type;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Intent copy$default(Intent intent, IntentType intentType, String str, Map map, float f, ConfidenceLevel confidenceLevel, int i, Object obj) {
        if ((i & 1) != 0) {
            intentType = intent.type;
        }
        if ((i & 2) != 0) {
            str = intent.normalizedText;
        }
        String str2 = str;
        if ((i & 4) != 0) {
            map = intent.slots;
        }
        Map map2 = map;
        if ((i & 8) != 0) {
            f = intent.confidence;
        }
        float f2 = f;
        if ((i & 16) != 0) {
            confidenceLevel = intent.confidenceLevel;
        }
        return intent.copy(intentType, str2, map2, f2, confidenceLevel);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final IntentType getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getNormalizedText() {
        return this.normalizedText;
    }

    public final Map<String, Slot> component3() {
        return this.slots;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final float getConfidence() {
        return this.confidence;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final ConfidenceLevel getConfidenceLevel() {
        return this.confidenceLevel;
    }

    public final Intent copy(IntentType type, String normalizedText, Map<String, Slot> slots, float confidence, ConfidenceLevel confidenceLevel) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(normalizedText, "normalizedText");
        Intrinsics.checkNotNullParameter(slots, "slots");
        Intrinsics.checkNotNullParameter(confidenceLevel, "confidenceLevel");
        return new Intent(type, normalizedText, slots, confidence, confidenceLevel);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Intent)) {
            return false;
        }
        Intent intent = (Intent) other;
        return this.type == intent.type && Intrinsics.areEqual(this.normalizedText, intent.normalizedText) && Intrinsics.areEqual(this.slots, intent.slots) && Float.compare(this.confidence, intent.confidence) == 0 && this.confidenceLevel == intent.confidenceLevel;
    }

    public int hashCode() {
        return (((((((this.type.hashCode() * 31) + this.normalizedText.hashCode()) * 31) + this.slots.hashCode()) * 31) + Float.hashCode(this.confidence)) * 31) + this.confidenceLevel.hashCode();
    }

    public String toString() {
        return "Intent(type=" + this.type + ", normalizedText=" + this.normalizedText + ", slots=" + this.slots + ", confidence=" + this.confidence + ", confidenceLevel=" + this.confidenceLevel + ")";
    }

    public Intent(IntentType type, String normalizedText, Map<String, Slot> slots, float confidence, ConfidenceLevel confidenceLevel) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(normalizedText, "normalizedText");
        Intrinsics.checkNotNullParameter(slots, "slots");
        Intrinsics.checkNotNullParameter(confidenceLevel, "confidenceLevel");
        this.type = type;
        this.normalizedText = normalizedText;
        this.slots = slots;
        this.confidence = confidence;
        this.confidenceLevel = confidenceLevel;
    }

    public /* synthetic */ Intent(IntentType intentType, String str, Map map, float f, ConfidenceLevel confidenceLevel, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(intentType, str, (i & 4) != 0 ? MapsKt.emptyMap() : map, (i & 8) != 0 ? 0.0f : f, (i & 16) != 0 ? ConfidenceLevel.LOW : confidenceLevel);
    }

    public final IntentType getType() {
        return this.type;
    }

    public final String getNormalizedText() {
        return this.normalizedText;
    }

    public final Map<String, Slot> getSlots() {
        return this.slots;
    }

    public final float getConfidence() {
        return this.confidence;
    }

    public final ConfidenceLevel getConfidenceLevel() {
        return this.confidenceLevel;
    }

    /* JADX INFO: compiled from: Intent.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/jarvis/core/common/model/Intent$Companion;", "", "<init>", "()V", "unknown", "Lcom/jarvis/core/common/model/Intent;", "normalizedText", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Intent unknown(String normalizedText) {
            Intrinsics.checkNotNullParameter(normalizedText, "normalizedText");
            return new Intent(IntentType.UNKNOWN, normalizedText, null, 0.0f, null, 28, null);
        }
    }
}
