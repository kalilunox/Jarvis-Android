package com.jarvis.core.aioutput;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StructuredAiModels.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/jarvis/core/aioutput/StructuredAiProcessResult;", "", "<init>", "()V", "Valid", "Invalid", "Lcom/jarvis/core/aioutput/StructuredAiProcessResult$Invalid;", "Lcom/jarvis/core/aioutput/StructuredAiProcessResult$Valid;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class StructuredAiProcessResult {
    public /* synthetic */ StructuredAiProcessResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: StructuredAiModels.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/jarvis/core/aioutput/StructuredAiProcessResult$Valid;", "Lcom/jarvis/core/aioutput/StructuredAiProcessResult;", "envelope", "Lcom/jarvis/core/aioutput/StructuredAiEnvelope;", "diagnostics", "Lcom/jarvis/core/aioutput/StructuredAiDiagnostics;", "<init>", "(Lcom/jarvis/core/aioutput/StructuredAiEnvelope;Lcom/jarvis/core/aioutput/StructuredAiDiagnostics;)V", "getEnvelope", "()Lcom/jarvis/core/aioutput/StructuredAiEnvelope;", "getDiagnostics", "()Lcom/jarvis/core/aioutput/StructuredAiDiagnostics;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Valid extends StructuredAiProcessResult {
        private final StructuredAiDiagnostics diagnostics;
        private final StructuredAiEnvelope envelope;

        public static /* synthetic */ Valid copy$default(Valid valid, StructuredAiEnvelope structuredAiEnvelope, StructuredAiDiagnostics structuredAiDiagnostics, int i, Object obj) {
            if ((i & 1) != 0) {
                structuredAiEnvelope = valid.envelope;
            }
            if ((i & 2) != 0) {
                structuredAiDiagnostics = valid.diagnostics;
            }
            return valid.copy(structuredAiEnvelope, structuredAiDiagnostics);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final StructuredAiEnvelope getEnvelope() {
            return this.envelope;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final StructuredAiDiagnostics getDiagnostics() {
            return this.diagnostics;
        }

        public final Valid copy(StructuredAiEnvelope envelope, StructuredAiDiagnostics diagnostics) {
            Intrinsics.checkNotNullParameter(envelope, "envelope");
            Intrinsics.checkNotNullParameter(diagnostics, "diagnostics");
            return new Valid(envelope, diagnostics);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Valid)) {
                return false;
            }
            Valid valid = (Valid) other;
            return Intrinsics.areEqual(this.envelope, valid.envelope) && Intrinsics.areEqual(this.diagnostics, valid.diagnostics);
        }

        public int hashCode() {
            return (this.envelope.hashCode() * 31) + this.diagnostics.hashCode();
        }

        public String toString() {
            return "Valid(envelope=" + this.envelope + ", diagnostics=" + this.diagnostics + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Valid(StructuredAiEnvelope envelope, StructuredAiDiagnostics diagnostics) {
            super(null);
            Intrinsics.checkNotNullParameter(envelope, "envelope");
            Intrinsics.checkNotNullParameter(diagnostics, "diagnostics");
            this.envelope = envelope;
            this.diagnostics = diagnostics;
        }

        public final StructuredAiEnvelope getEnvelope() {
            return this.envelope;
        }

        public final StructuredAiDiagnostics getDiagnostics() {
            return this.diagnostics;
        }
    }

    private StructuredAiProcessResult() {
    }

    /* JADX INFO: compiled from: StructuredAiModels.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/jarvis/core/aioutput/StructuredAiProcessResult$Invalid;", "Lcom/jarvis/core/aioutput/StructuredAiProcessResult;", "diagnostics", "Lcom/jarvis/core/aioutput/StructuredAiDiagnostics;", "rawPreview", "", "<init>", "(Lcom/jarvis/core/aioutput/StructuredAiDiagnostics;Ljava/lang/String;)V", "getDiagnostics", "()Lcom/jarvis/core/aioutput/StructuredAiDiagnostics;", "getRawPreview", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Invalid extends StructuredAiProcessResult {
        private final StructuredAiDiagnostics diagnostics;
        private final String rawPreview;

        public static /* synthetic */ Invalid copy$default(Invalid invalid, StructuredAiDiagnostics structuredAiDiagnostics, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                structuredAiDiagnostics = invalid.diagnostics;
            }
            if ((i & 2) != 0) {
                str = invalid.rawPreview;
            }
            return invalid.copy(structuredAiDiagnostics, str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final StructuredAiDiagnostics getDiagnostics() {
            return this.diagnostics;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getRawPreview() {
            return this.rawPreview;
        }

        public final Invalid copy(StructuredAiDiagnostics diagnostics, String rawPreview) {
            Intrinsics.checkNotNullParameter(diagnostics, "diagnostics");
            Intrinsics.checkNotNullParameter(rawPreview, "rawPreview");
            return new Invalid(diagnostics, rawPreview);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Invalid)) {
                return false;
            }
            Invalid invalid = (Invalid) other;
            return Intrinsics.areEqual(this.diagnostics, invalid.diagnostics) && Intrinsics.areEqual(this.rawPreview, invalid.rawPreview);
        }

        public int hashCode() {
            return (this.diagnostics.hashCode() * 31) + this.rawPreview.hashCode();
        }

        public String toString() {
            return "Invalid(diagnostics=" + this.diagnostics + ", rawPreview=" + this.rawPreview + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Invalid(StructuredAiDiagnostics diagnostics, String rawPreview) {
            super(null);
            Intrinsics.checkNotNullParameter(diagnostics, "diagnostics");
            Intrinsics.checkNotNullParameter(rawPreview, "rawPreview");
            this.diagnostics = diagnostics;
            this.rawPreview = rawPreview;
        }

        public final StructuredAiDiagnostics getDiagnostics() {
            return this.diagnostics;
        }

        public final String getRawPreview() {
            return this.rawPreview;
        }
    }
}
