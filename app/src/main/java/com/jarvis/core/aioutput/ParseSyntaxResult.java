package com.jarvis.core.aioutput;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StructuredAiOutputParser.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/jarvis/core/aioutput/ParseSyntaxResult;", "", "<init>", "()V", "Ok", "Fail", "Lcom/jarvis/core/aioutput/ParseSyntaxResult$Fail;", "Lcom/jarvis/core/aioutput/ParseSyntaxResult$Ok;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class ParseSyntaxResult {
    public /* synthetic */ ParseSyntaxResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: StructuredAiOutputParser.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/jarvis/core/aioutput/ParseSyntaxResult$Ok;", "Lcom/jarvis/core/aioutput/ParseSyntaxResult;", "json", "Lcom/jarvis/core/aioutput/JsonVal$Obj;", "jsonText", "", "parseMs", "", "<init>", "(Lcom/jarvis/core/aioutput/JsonVal$Obj;Ljava/lang/String;J)V", "getJson", "()Lcom/jarvis/core/aioutput/JsonVal$Obj;", "getJsonText", "()Ljava/lang/String;", "getParseMs", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Ok extends ParseSyntaxResult {
        private final JsonVal.Obj json;
        private final String jsonText;
        private final long parseMs;

        public static /* synthetic */ Ok copy$default(Ok ok, JsonVal.Obj obj, String str, long j, int i, Object obj2) {
            if ((i & 1) != 0) {
                obj = ok.json;
            }
            if ((i & 2) != 0) {
                str = ok.jsonText;
            }
            if ((i & 4) != 0) {
                j = ok.parseMs;
            }
            return ok.copy(obj, str, j);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final JsonVal.Obj getJson() {
            return this.json;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getJsonText() {
            return this.jsonText;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final long getParseMs() {
            return this.parseMs;
        }

        public final Ok copy(JsonVal.Obj json, String jsonText, long parseMs) {
            Intrinsics.checkNotNullParameter(json, "json");
            Intrinsics.checkNotNullParameter(jsonText, "jsonText");
            return new Ok(json, jsonText, parseMs);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Ok)) {
                return false;
            }
            Ok ok = (Ok) other;
            return Intrinsics.areEqual(this.json, ok.json) && Intrinsics.areEqual(this.jsonText, ok.jsonText) && this.parseMs == ok.parseMs;
        }

        public int hashCode() {
            return (((this.json.hashCode() * 31) + this.jsonText.hashCode()) * 31) + Long.hashCode(this.parseMs);
        }

        public String toString() {
            return "Ok(json=" + this.json + ", jsonText=" + this.jsonText + ", parseMs=" + this.parseMs + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Ok(JsonVal.Obj json, String jsonText, long parseMs) {
            super(null);
            Intrinsics.checkNotNullParameter(json, "json");
            Intrinsics.checkNotNullParameter(jsonText, "jsonText");
            this.json = json;
            this.jsonText = jsonText;
            this.parseMs = parseMs;
        }

        public final JsonVal.Obj getJson() {
            return this.json;
        }

        public final String getJsonText() {
            return this.jsonText;
        }

        public final long getParseMs() {
            return this.parseMs;
        }
    }

    private ParseSyntaxResult() {
    }

    /* JADX INFO: compiled from: StructuredAiOutputParser.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/jarvis/core/aioutput/ParseSyntaxResult$Fail;", "Lcom/jarvis/core/aioutput/ParseSyntaxResult;", "reason", "", "parseMs", "", "<init>", "(Ljava/lang/String;J)V", "getReason", "()Ljava/lang/String;", "getParseMs", "()J", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Fail extends ParseSyntaxResult {
        private final long parseMs;
        private final String reason;

        public static /* synthetic */ Fail copy$default(Fail fail, String str, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                str = fail.reason;
            }
            if ((i & 2) != 0) {
                j = fail.parseMs;
            }
            return fail.copy(str, j);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getReason() {
            return this.reason;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getParseMs() {
            return this.parseMs;
        }

        public final Fail copy(String reason, long parseMs) {
            Intrinsics.checkNotNullParameter(reason, "reason");
            return new Fail(reason, parseMs);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Fail)) {
                return false;
            }
            Fail fail = (Fail) other;
            return Intrinsics.areEqual(this.reason, fail.reason) && this.parseMs == fail.parseMs;
        }

        public int hashCode() {
            return (this.reason.hashCode() * 31) + Long.hashCode(this.parseMs);
        }

        public String toString() {
            return "Fail(reason=" + this.reason + ", parseMs=" + this.parseMs + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Fail(String reason, long parseMs) {
            super(null);
            Intrinsics.checkNotNullParameter(reason, "reason");
            this.reason = reason;
            this.parseMs = parseMs;
        }

        public final long getParseMs() {
            return this.parseMs;
        }

        public final String getReason() {
            return this.reason;
        }
    }
}
