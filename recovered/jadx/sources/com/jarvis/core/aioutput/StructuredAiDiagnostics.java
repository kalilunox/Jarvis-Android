package com.jarvis.core.aioutput;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StructuredAiModels.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u001e\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B_\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\u0006\u0010 \u001a\u00020\u0005J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010$\u001a\u00020\bHÆ\u0003J\t\u0010%\u001a\u00020\nHÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010)\u001a\u00020\u000fHÆ\u0003Jk\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u000fHÆ\u0001J\u0013\u0010+\u001a\u00020\u00032\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010-\u001a\u00020.HÖ\u0001J\t\u0010/\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u00060"}, d2 = {"Lcom/jarvis/core/aioutput/StructuredAiDiagnostics;", "", "structured", "", "schemaVersion", "", "responseType", "parse", "Lcom/jarvis/core/aioutput/StructuredParseStatus;", "validation", "Lcom/jarvis/core/aioutput/StructuredValidationStatus;", "repairAttempted", "repairResult", "validationReason", "parseMs", "", "<init>", "(ZLjava/lang/String;Ljava/lang/String;Lcom/jarvis/core/aioutput/StructuredParseStatus;Lcom/jarvis/core/aioutput/StructuredValidationStatus;ZLjava/lang/String;Ljava/lang/String;J)V", "getStructured", "()Z", "getSchemaVersion", "()Ljava/lang/String;", "getResponseType", "getParse", "()Lcom/jarvis/core/aioutput/StructuredParseStatus;", "getValidation", "()Lcom/jarvis/core/aioutput/StructuredValidationStatus;", "getRepairAttempted", "getRepairResult", "getValidationReason", "getParseMs", "()J", "logLine", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class StructuredAiDiagnostics {
    private final StructuredParseStatus parse;
    private final long parseMs;
    private final boolean repairAttempted;
    private final String repairResult;
    private final String responseType;
    private final String schemaVersion;
    private final boolean structured;
    private final StructuredValidationStatus validation;
    private final String validationReason;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getStructured() {
        return this.structured;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSchemaVersion() {
        return this.schemaVersion;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getResponseType() {
        return this.responseType;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final StructuredParseStatus getParse() {
        return this.parse;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final StructuredValidationStatus getValidation() {
        return this.validation;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getRepairAttempted() {
        return this.repairAttempted;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getRepairResult() {
        return this.repairResult;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getValidationReason() {
        return this.validationReason;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final long getParseMs() {
        return this.parseMs;
    }

    public final StructuredAiDiagnostics copy(boolean structured, String schemaVersion, String responseType, StructuredParseStatus parse, StructuredValidationStatus validation, boolean repairAttempted, String repairResult, String validationReason, long parseMs) {
        Intrinsics.checkNotNullParameter(parse, "parse");
        Intrinsics.checkNotNullParameter(validation, "validation");
        return new StructuredAiDiagnostics(structured, schemaVersion, responseType, parse, validation, repairAttempted, repairResult, validationReason, parseMs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StructuredAiDiagnostics)) {
            return false;
        }
        StructuredAiDiagnostics structuredAiDiagnostics = (StructuredAiDiagnostics) other;
        return this.structured == structuredAiDiagnostics.structured && Intrinsics.areEqual(this.schemaVersion, structuredAiDiagnostics.schemaVersion) && Intrinsics.areEqual(this.responseType, structuredAiDiagnostics.responseType) && this.parse == structuredAiDiagnostics.parse && this.validation == structuredAiDiagnostics.validation && this.repairAttempted == structuredAiDiagnostics.repairAttempted && Intrinsics.areEqual(this.repairResult, structuredAiDiagnostics.repairResult) && Intrinsics.areEqual(this.validationReason, structuredAiDiagnostics.validationReason) && this.parseMs == structuredAiDiagnostics.parseMs;
    }

    public int hashCode() {
        return (((((((((((((((Boolean.hashCode(this.structured) * 31) + (this.schemaVersion == null ? 0 : this.schemaVersion.hashCode())) * 31) + (this.responseType == null ? 0 : this.responseType.hashCode())) * 31) + this.parse.hashCode()) * 31) + this.validation.hashCode()) * 31) + Boolean.hashCode(this.repairAttempted)) * 31) + (this.repairResult == null ? 0 : this.repairResult.hashCode())) * 31) + (this.validationReason != null ? this.validationReason.hashCode() : 0)) * 31) + Long.hashCode(this.parseMs);
    }

    public String toString() {
        return "StructuredAiDiagnostics(structured=" + this.structured + ", schemaVersion=" + this.schemaVersion + ", responseType=" + this.responseType + ", parse=" + this.parse + ", validation=" + this.validation + ", repairAttempted=" + this.repairAttempted + ", repairResult=" + this.repairResult + ", validationReason=" + this.validationReason + ", parseMs=" + this.parseMs + ")";
    }

    public StructuredAiDiagnostics(boolean structured, String schemaVersion, String responseType, StructuredParseStatus parse, StructuredValidationStatus validation, boolean repairAttempted, String repairResult, String validationReason, long parseMs) {
        Intrinsics.checkNotNullParameter(parse, "parse");
        Intrinsics.checkNotNullParameter(validation, "validation");
        this.structured = structured;
        this.schemaVersion = schemaVersion;
        this.responseType = responseType;
        this.parse = parse;
        this.validation = validation;
        this.repairAttempted = repairAttempted;
        this.repairResult = repairResult;
        this.validationReason = validationReason;
        this.parseMs = parseMs;
    }

    public /* synthetic */ StructuredAiDiagnostics(boolean z, String str, String str2, StructuredParseStatus structuredParseStatus, StructuredValidationStatus structuredValidationStatus, boolean z2, String str3, String str4, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, str, str2, structuredParseStatus, structuredValidationStatus, (i & 32) != 0 ? false : z2, (i & 64) != 0 ? null : str3, (i & 128) != 0 ? null : str4, (i & 256) != 0 ? 0L : j);
    }

    public final boolean getStructured() {
        return this.structured;
    }

    public final String getSchemaVersion() {
        return this.schemaVersion;
    }

    public final String getResponseType() {
        return this.responseType;
    }

    public final StructuredParseStatus getParse() {
        return this.parse;
    }

    public final StructuredValidationStatus getValidation() {
        return this.validation;
    }

    public final boolean getRepairAttempted() {
        return this.repairAttempted;
    }

    public final String getRepairResult() {
        return this.repairResult;
    }

    public final String getValidationReason() {
        return this.validationReason;
    }

    public final long getParseMs() {
        return this.parseMs;
    }

    public final String logLine() {
        boolean z = this.structured;
        String str = this.schemaVersion;
        if (str == null) {
            str = "-";
        }
        String str2 = this.responseType;
        if (str2 == null) {
            str2 = "-";
        }
        StructuredParseStatus structuredParseStatus = this.parse;
        StructuredValidationStatus structuredValidationStatus = this.validation;
        boolean z2 = this.repairAttempted;
        String str3 = this.repairResult;
        if (str3 == null) {
            str3 = "-";
        }
        String str4 = this.validationReason;
        return "structured=" + z + " schema=" + str + " type=" + str2 + " parse=" + structuredParseStatus + " validation=" + structuredValidationStatus + " repair=" + z2 + " repairResult=" + str3 + " reason=" + (str4 != null ? str4 : "-") + " parseMs=" + this.parseMs;
    }
}
