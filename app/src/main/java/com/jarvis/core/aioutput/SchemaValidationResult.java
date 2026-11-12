package com.jarvis.core.aioutput;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StructuredAiSchemaValidator.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/jarvis/core/aioutput/SchemaValidationResult;", "", "<init>", "()V", "Ok", "Fail", "Lcom/jarvis/core/aioutput/SchemaValidationResult$Fail;", "Lcom/jarvis/core/aioutput/SchemaValidationResult$Ok;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class SchemaValidationResult {
    public /* synthetic */ SchemaValidationResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: StructuredAiSchemaValidator.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/jarvis/core/aioutput/SchemaValidationResult$Ok;", "Lcom/jarvis/core/aioutput/SchemaValidationResult;", "envelope", "Lcom/jarvis/core/aioutput/StructuredAiEnvelope;", "<init>", "(Lcom/jarvis/core/aioutput/StructuredAiEnvelope;)V", "getEnvelope", "()Lcom/jarvis/core/aioutput/StructuredAiEnvelope;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Ok extends SchemaValidationResult {
        private final StructuredAiEnvelope envelope;

        public static /* synthetic */ Ok copy$default(Ok ok, StructuredAiEnvelope structuredAiEnvelope, int i, Object obj) {
            if ((i & 1) != 0) {
                structuredAiEnvelope = ok.envelope;
            }
            return ok.copy(structuredAiEnvelope);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final StructuredAiEnvelope getEnvelope() {
            return this.envelope;
        }

        public final Ok copy(StructuredAiEnvelope envelope) {
            Intrinsics.checkNotNullParameter(envelope, "envelope");
            return new Ok(envelope);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Ok) && Intrinsics.areEqual(this.envelope, ((Ok) other).envelope);
        }

        public int hashCode() {
            return this.envelope.hashCode();
        }

        public String toString() {
            return "Ok(envelope=" + this.envelope + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Ok(StructuredAiEnvelope envelope) {
            super(null);
            Intrinsics.checkNotNullParameter(envelope, "envelope");
            this.envelope = envelope;
        }

        public final StructuredAiEnvelope getEnvelope() {
            return this.envelope;
        }
    }

    private SchemaValidationResult() {
    }

    /* JADX INFO: compiled from: StructuredAiSchemaValidator.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/aioutput/SchemaValidationResult$Fail;", "Lcom/jarvis/core/aioutput/SchemaValidationResult;", "reason", "", "<init>", "(Ljava/lang/String;)V", "getReason", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Fail extends SchemaValidationResult {
        private final String reason;

        public static /* synthetic */ Fail copy$default(Fail fail, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = fail.reason;
            }
            return fail.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getReason() {
            return this.reason;
        }

        public final Fail copy(String reason) {
            Intrinsics.checkNotNullParameter(reason, "reason");
            return new Fail(reason);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Fail) && Intrinsics.areEqual(this.reason, ((Fail) other).reason);
        }

        public int hashCode() {
            return this.reason.hashCode();
        }

        public String toString() {
            return "Fail(reason=" + this.reason + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Fail(String reason) {
            super(null);
            Intrinsics.checkNotNullParameter(reason, "reason");
            this.reason = reason;
        }

        public final String getReason() {
            return this.reason;
        }
    }
}
