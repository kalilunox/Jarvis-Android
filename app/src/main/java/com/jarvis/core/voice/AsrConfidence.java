package com.jarvis.core.voice;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: AsrContracts.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/jarvis/core/voice/AsrConfidence;", "", "<init>", "()V", "Unknown", "Known", "Lcom/jarvis/core/voice/AsrConfidence$Known;", "Lcom/jarvis/core/voice/AsrConfidence$Unknown;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class AsrConfidence {
    public /* synthetic */ AsrConfidence(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: AsrContracts.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/jarvis/core/voice/AsrConfidence$Unknown;", "Lcom/jarvis/core/voice/AsrConfidence;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Unknown extends AsrConfidence {
        public static final Unknown INSTANCE = new Unknown();

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Unknown)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -899197193;
        }

        public String toString() {
            return "Unknown";
        }

        private Unknown() {
            super(null);
        }
    }

    private AsrConfidence() {
    }

    /* JADX INFO: compiled from: AsrContracts.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/jarvis/core/voice/AsrConfidence$Known;", "Lcom/jarvis/core/voice/AsrConfidence;", "value", "", "<init>", "(F)V", "getValue", "()F", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Known extends AsrConfidence {
        private final float value;

        public static /* synthetic */ Known copy$default(Known known, float f, int i, Object obj) {
            if ((i & 1) != 0) {
                f = known.value;
            }
            return known.copy(f);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final float getValue() {
            return this.value;
        }

        public final Known copy(float value) {
            return new Known(value);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Known) && Float.compare(this.value, ((Known) other).value) == 0;
        }

        public int hashCode() {
            return Float.hashCode(this.value);
        }

        public String toString() {
            return "Known(value=" + this.value + ")";
        }

        public Known(float value) {
            super(null);
            this.value = value;
        }

        public final float getValue() {
            return this.value;
        }
    }
}
