package com.jarvis.core.aioutput;

import com.jarvis.app.VoiceService;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: StructuredAiModels.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b.\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BÍ\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\t\u00102\u001a\u00020\u0003HÆ\u0003J\t\u00103\u001a\u00020\u0005HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u00105\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010!J\u000b\u00106\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\fHÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000fHÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0003HÆ\u0003JØ\u0001\u0010B\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010CJ\u0013\u0010D\u001a\u00020E2\b\u0010F\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010G\u001a\u00020HHÖ\u0001J\t\u0010I\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001cR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b \u0010!R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001cR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001cR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001cR\u001d\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000f¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001cR\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001cR\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001cR\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001cR\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u001cR\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u001c¨\u0006J"}, d2 = {"Lcom/jarvis/core/aioutput/StructuredAiEnvelope;", "", "schemaVersion", "", "responseType", "Lcom/jarvis/core/aioutput/StructuredAiResponseType;", "requestId", "confidence", "", VoiceService.EXTRA_TEXT, "question", "clarificationReason", "Lcom/jarvis/core/aioutput/ClarificationReason;", "toolId", "arguments", "", "goal", "intent", "suggestedRoute", "ambiguity", "planSteps", "", "Lcom/jarvis/core/aioutput/StructuredPlanStepDraft;", "confirmationSummary", "noActionReason", "<init>", "(Ljava/lang/String;Lcom/jarvis/core/aioutput/StructuredAiResponseType;Ljava/lang/String;Ljava/lang/Float;Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/core/aioutput/ClarificationReason;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "getSchemaVersion", "()Ljava/lang/String;", "getResponseType", "()Lcom/jarvis/core/aioutput/StructuredAiResponseType;", "getRequestId", "getConfidence", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getText", "getQuestion", "getClarificationReason", "()Lcom/jarvis/core/aioutput/ClarificationReason;", "getToolId", "getArguments", "()Ljava/util/Map;", "getGoal", "getIntent", "getSuggestedRoute", "getAmbiguity", "getPlanSteps", "()Ljava/util/List;", "getConfirmationSummary", "getNoActionReason", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "copy", "(Ljava/lang/String;Lcom/jarvis/core/aioutput/StructuredAiResponseType;Ljava/lang/String;Ljava/lang/Float;Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/core/aioutput/ClarificationReason;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)Lcom/jarvis/core/aioutput/StructuredAiEnvelope;", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class StructuredAiEnvelope {
    private final String ambiguity;
    private final Map<String, String> arguments;
    private final ClarificationReason clarificationReason;
    private final Float confidence;
    private final String confirmationSummary;
    private final String goal;
    private final String intent;
    private final String noActionReason;
    private final List<StructuredPlanStepDraft> planSteps;
    private final String question;
    private final String requestId;
    private final StructuredAiResponseType responseType;
    private final String schemaVersion;
    private final String suggestedRoute;
    private final String text;
    private final String toolId;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getSchemaVersion() {
        return this.schemaVersion;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getGoal() {
        return this.goal;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getIntent() {
        return this.intent;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getSuggestedRoute() {
        return this.suggestedRoute;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getAmbiguity() {
        return this.ambiguity;
    }

    public final List<StructuredPlanStepDraft> component14() {
        return this.planSteps;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getConfirmationSummary() {
        return this.confirmationSummary;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getNoActionReason() {
        return this.noActionReason;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final StructuredAiResponseType getResponseType() {
        return this.responseType;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getRequestId() {
        return this.requestId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Float getConfidence() {
        return this.confidence;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getQuestion() {
        return this.question;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final ClarificationReason getClarificationReason() {
        return this.clarificationReason;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getToolId() {
        return this.toolId;
    }

    public final Map<String, String> component9() {
        return this.arguments;
    }

    public final StructuredAiEnvelope copy(String schemaVersion, StructuredAiResponseType responseType, String requestId, Float confidence, String text, String question, ClarificationReason clarificationReason, String toolId, Map<String, String> arguments, String goal, String intent, String suggestedRoute, String ambiguity, List<StructuredPlanStepDraft> planSteps, String confirmationSummary, String noActionReason) {
        Intrinsics.checkNotNullParameter(schemaVersion, "schemaVersion");
        Intrinsics.checkNotNullParameter(responseType, "responseType");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        Intrinsics.checkNotNullParameter(planSteps, "planSteps");
        return new StructuredAiEnvelope(schemaVersion, responseType, requestId, confidence, text, question, clarificationReason, toolId, arguments, goal, intent, suggestedRoute, ambiguity, planSteps, confirmationSummary, noActionReason);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StructuredAiEnvelope)) {
            return false;
        }
        StructuredAiEnvelope structuredAiEnvelope = (StructuredAiEnvelope) other;
        return Intrinsics.areEqual(this.schemaVersion, structuredAiEnvelope.schemaVersion) && this.responseType == structuredAiEnvelope.responseType && Intrinsics.areEqual(this.requestId, structuredAiEnvelope.requestId) && Intrinsics.areEqual((Object) this.confidence, (Object) structuredAiEnvelope.confidence) && Intrinsics.areEqual(this.text, structuredAiEnvelope.text) && Intrinsics.areEqual(this.question, structuredAiEnvelope.question) && this.clarificationReason == structuredAiEnvelope.clarificationReason && Intrinsics.areEqual(this.toolId, structuredAiEnvelope.toolId) && Intrinsics.areEqual(this.arguments, structuredAiEnvelope.arguments) && Intrinsics.areEqual(this.goal, structuredAiEnvelope.goal) && Intrinsics.areEqual(this.intent, structuredAiEnvelope.intent) && Intrinsics.areEqual(this.suggestedRoute, structuredAiEnvelope.suggestedRoute) && Intrinsics.areEqual(this.ambiguity, structuredAiEnvelope.ambiguity) && Intrinsics.areEqual(this.planSteps, structuredAiEnvelope.planSteps) && Intrinsics.areEqual(this.confirmationSummary, structuredAiEnvelope.confirmationSummary) && Intrinsics.areEqual(this.noActionReason, structuredAiEnvelope.noActionReason);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((this.schemaVersion.hashCode() * 31) + this.responseType.hashCode()) * 31) + (this.requestId == null ? 0 : this.requestId.hashCode())) * 31) + (this.confidence == null ? 0 : this.confidence.hashCode())) * 31) + (this.text == null ? 0 : this.text.hashCode())) * 31) + (this.question == null ? 0 : this.question.hashCode())) * 31) + (this.clarificationReason == null ? 0 : this.clarificationReason.hashCode())) * 31) + (this.toolId == null ? 0 : this.toolId.hashCode())) * 31) + this.arguments.hashCode()) * 31) + (this.goal == null ? 0 : this.goal.hashCode())) * 31) + (this.intent == null ? 0 : this.intent.hashCode())) * 31) + (this.suggestedRoute == null ? 0 : this.suggestedRoute.hashCode())) * 31) + (this.ambiguity == null ? 0 : this.ambiguity.hashCode())) * 31) + this.planSteps.hashCode()) * 31) + (this.confirmationSummary == null ? 0 : this.confirmationSummary.hashCode())) * 31) + (this.noActionReason != null ? this.noActionReason.hashCode() : 0);
    }

    public String toString() {
        return "StructuredAiEnvelope(schemaVersion=" + this.schemaVersion + ", responseType=" + this.responseType + ", requestId=" + this.requestId + ", confidence=" + this.confidence + ", text=" + this.text + ", question=" + this.question + ", clarificationReason=" + this.clarificationReason + ", toolId=" + this.toolId + ", arguments=" + this.arguments + ", goal=" + this.goal + ", intent=" + this.intent + ", suggestedRoute=" + this.suggestedRoute + ", ambiguity=" + this.ambiguity + ", planSteps=" + this.planSteps + ", confirmationSummary=" + this.confirmationSummary + ", noActionReason=" + this.noActionReason + ")";
    }

    public StructuredAiEnvelope(String schemaVersion, StructuredAiResponseType responseType, String requestId, Float confidence, String text, String question, ClarificationReason clarificationReason, String toolId, Map<String, String> arguments, String goal, String intent, String suggestedRoute, String ambiguity, List<StructuredPlanStepDraft> planSteps, String confirmationSummary, String noActionReason) {
        Intrinsics.checkNotNullParameter(schemaVersion, "schemaVersion");
        Intrinsics.checkNotNullParameter(responseType, "responseType");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        Intrinsics.checkNotNullParameter(planSteps, "planSteps");
        this.schemaVersion = schemaVersion;
        this.responseType = responseType;
        this.requestId = requestId;
        this.confidence = confidence;
        this.text = text;
        this.question = question;
        this.clarificationReason = clarificationReason;
        this.toolId = toolId;
        this.arguments = arguments;
        this.goal = goal;
        this.intent = intent;
        this.suggestedRoute = suggestedRoute;
        this.ambiguity = ambiguity;
        this.planSteps = planSteps;
        this.confirmationSummary = confirmationSummary;
        this.noActionReason = noActionReason;
        if (StringsKt.isBlank(this.schemaVersion)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(this.arguments.size() <= 12)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (this.planSteps.size() <= 16) {
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public /* synthetic */ StructuredAiEnvelope(String str, StructuredAiResponseType structuredAiResponseType, String str2, Float f, String str3, String str4, ClarificationReason clarificationReason, String str5, Map map, String str6, String str7, String str8, String str9, List list, String str10, String str11, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, structuredAiResponseType, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : f, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : clarificationReason, (i & 128) != 0 ? null : str5, (i & 256) != 0 ? MapsKt.emptyMap() : map, (i & 512) != 0 ? null : str6, (i & 1024) != 0 ? null : str7, (i & 2048) != 0 ? null : str8, (i & 4096) != 0 ? null : str9, (i & 8192) != 0 ? CollectionsKt.emptyList() : list, (i & 16384) != 0 ? null : str10, (i & 32768) != 0 ? null : str11);
    }

    public final String getSchemaVersion() {
        return this.schemaVersion;
    }

    public final StructuredAiResponseType getResponseType() {
        return this.responseType;
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public final Float getConfidence() {
        return this.confidence;
    }

    public final String getText() {
        return this.text;
    }

    public final String getQuestion() {
        return this.question;
    }

    public final ClarificationReason getClarificationReason() {
        return this.clarificationReason;
    }

    public final String getToolId() {
        return this.toolId;
    }

    public final Map<String, String> getArguments() {
        return this.arguments;
    }

    public final String getGoal() {
        return this.goal;
    }

    public final String getIntent() {
        return this.intent;
    }

    public final String getSuggestedRoute() {
        return this.suggestedRoute;
    }

    public final String getAmbiguity() {
        return this.ambiguity;
    }

    public final List<StructuredPlanStepDraft> getPlanSteps() {
        return this.planSteps;
    }

    public final String getConfirmationSummary() {
        return this.confirmationSummary;
    }

    public final String getNoActionReason() {
        return this.noActionReason;
    }
}
