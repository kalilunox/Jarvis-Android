package com.jarvis.core.runtime.semantic;

import com.jarvis.core.runtime.RouteDecision;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SemanticModels.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b7\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B÷\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000b\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u001d\u0012\u000e\b\u0002\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\u0004\b\u001f\u0010 J\t\u0010=\u001a\u00020\u0003HÆ\u0003J\t\u0010>\u001a\u00020\u0003HÆ\u0003J\t\u0010?\u001a\u00020\u0003HÆ\u0003J\t\u0010@\u001a\u00020\u0003HÆ\u0003J\u000f\u0010A\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J\u0015\u0010B\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000bHÆ\u0003J\u000f\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\u000f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\u000f\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\t\u0010F\u001a\u00020\u0010HÆ\u0003J\t\u0010G\u001a\u00020\u0012HÆ\u0003J\t\u0010H\u001a\u00020\u0014HÆ\u0003J\u000f\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\t\u0010J\u001a\u00020\u0017HÆ\u0003J\t\u0010K\u001a\u00020\u0003HÆ\u0003J\u000b\u0010L\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010M\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\u000b\u0010N\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010O\u001a\u00020\u001dHÆ\u0003J\u000f\u0010P\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\u008b\u0002\u0010Q\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00032\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\u000e\b\u0002\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0001J\u0013\u0010R\u001a\u00020\u001d2\b\u0010S\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010T\u001a\u00020UHÖ\u0001J\t\u0010V\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\"R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\"R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\"R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b*\u0010'R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b+\u0010'R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b,\u0010'R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b3\u0010'R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u0011\u0010\u0018\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\"R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\"R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b8\u0010'R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\"R\u0011\u0010\u001c\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b<\u0010'¨\u0006W"}, d2 = {"Lcom/jarvis/core/runtime/semantic/SemanticUnderstanding;", "", "rawInput", "", "normalizedInput", "goal", "intent", "entities", "", "Lcom/jarvis/core/runtime/semantic/SemanticEntity;", "arguments", "", "constraints", "contextRefs", "conversationRefs", "confidence", "", "ambiguity", "Lcom/jarvis/core/runtime/semantic/AmbiguityLevel;", "clarity", "Lcom/jarvis/core/runtime/semantic/SemanticClarity;", "requiredCapabilities", "suggestedRoute", "Lcom/jarvis/core/runtime/RouteDecision;", "routeReason", "clarifyQuestion", "candidateInterpretations", "rewrittenCommand", "llmInterpretationUsed", "", "notes", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/Map;Ljava/util/List;Ljava/util/List;Ljava/util/List;FLcom/jarvis/core/runtime/semantic/AmbiguityLevel;Lcom/jarvis/core/runtime/semantic/SemanticClarity;Ljava/util/List;Lcom/jarvis/core/runtime/RouteDecision;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;ZLjava/util/List;)V", "getRawInput", "()Ljava/lang/String;", "getNormalizedInput", "getGoal", "getIntent", "getEntities", "()Ljava/util/List;", "getArguments", "()Ljava/util/Map;", "getConstraints", "getContextRefs", "getConversationRefs", "getConfidence", "()F", "getAmbiguity", "()Lcom/jarvis/core/runtime/semantic/AmbiguityLevel;", "getClarity", "()Lcom/jarvis/core/runtime/semantic/SemanticClarity;", "getRequiredCapabilities", "getSuggestedRoute", "()Lcom/jarvis/core/runtime/RouteDecision;", "getRouteReason", "getClarifyQuestion", "getCandidateInterpretations", "getRewrittenCommand", "getLlmInterpretationUsed", "()Z", "getNotes", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class SemanticUnderstanding {
    private final AmbiguityLevel ambiguity;
    private final Map<String, String> arguments;
    private final List<String> candidateInterpretations;
    private final String clarifyQuestion;
    private final SemanticClarity clarity;
    private final float confidence;
    private final List<String> constraints;
    private final List<String> contextRefs;
    private final List<String> conversationRefs;
    private final List<SemanticEntity> entities;
    private final String goal;
    private final String intent;
    private final boolean llmInterpretationUsed;
    private final String normalizedInput;
    private final List<String> notes;
    private final String rawInput;
    private final List<String> requiredCapabilities;
    private final String rewrittenCommand;
    private final String routeReason;
    private final RouteDecision suggestedRoute;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getRawInput() {
        return this.rawInput;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final float getConfidence() {
        return this.confidence;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final AmbiguityLevel getAmbiguity() {
        return this.ambiguity;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final SemanticClarity getClarity() {
        return this.clarity;
    }

    public final List<String> component13() {
        return this.requiredCapabilities;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final RouteDecision getSuggestedRoute() {
        return this.suggestedRoute;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getRouteReason() {
        return this.routeReason;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getClarifyQuestion() {
        return this.clarifyQuestion;
    }

    public final List<String> component17() {
        return this.candidateInterpretations;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final String getRewrittenCommand() {
        return this.rewrittenCommand;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final boolean getLlmInterpretationUsed() {
        return this.llmInterpretationUsed;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getNormalizedInput() {
        return this.normalizedInput;
    }

    public final List<String> component20() {
        return this.notes;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getGoal() {
        return this.goal;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getIntent() {
        return this.intent;
    }

    public final List<SemanticEntity> component5() {
        return this.entities;
    }

    public final Map<String, String> component6() {
        return this.arguments;
    }

    public final List<String> component7() {
        return this.constraints;
    }

    public final List<String> component8() {
        return this.contextRefs;
    }

    public final List<String> component9() {
        return this.conversationRefs;
    }

    public final SemanticUnderstanding copy(String rawInput, String normalizedInput, String goal, String intent, List<SemanticEntity> entities, Map<String, String> arguments, List<String> constraints, List<String> contextRefs, List<String> conversationRefs, float confidence, AmbiguityLevel ambiguity, SemanticClarity clarity, List<String> requiredCapabilities, RouteDecision suggestedRoute, String routeReason, String clarifyQuestion, List<String> candidateInterpretations, String rewrittenCommand, boolean llmInterpretationUsed, List<String> notes) {
        Intrinsics.checkNotNullParameter(rawInput, "rawInput");
        Intrinsics.checkNotNullParameter(normalizedInput, "normalizedInput");
        Intrinsics.checkNotNullParameter(goal, "goal");
        Intrinsics.checkNotNullParameter(intent, "intent");
        Intrinsics.checkNotNullParameter(entities, "entities");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        Intrinsics.checkNotNullParameter(constraints, "constraints");
        Intrinsics.checkNotNullParameter(contextRefs, "contextRefs");
        Intrinsics.checkNotNullParameter(conversationRefs, "conversationRefs");
        Intrinsics.checkNotNullParameter(ambiguity, "ambiguity");
        Intrinsics.checkNotNullParameter(clarity, "clarity");
        Intrinsics.checkNotNullParameter(requiredCapabilities, "requiredCapabilities");
        Intrinsics.checkNotNullParameter(suggestedRoute, "suggestedRoute");
        Intrinsics.checkNotNullParameter(routeReason, "routeReason");
        Intrinsics.checkNotNullParameter(candidateInterpretations, "candidateInterpretations");
        Intrinsics.checkNotNullParameter(notes, "notes");
        return new SemanticUnderstanding(rawInput, normalizedInput, goal, intent, entities, arguments, constraints, contextRefs, conversationRefs, confidence, ambiguity, clarity, requiredCapabilities, suggestedRoute, routeReason, clarifyQuestion, candidateInterpretations, rewrittenCommand, llmInterpretationUsed, notes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SemanticUnderstanding)) {
            return false;
        }
        SemanticUnderstanding semanticUnderstanding = (SemanticUnderstanding) other;
        return Intrinsics.areEqual(this.rawInput, semanticUnderstanding.rawInput) && Intrinsics.areEqual(this.normalizedInput, semanticUnderstanding.normalizedInput) && Intrinsics.areEqual(this.goal, semanticUnderstanding.goal) && Intrinsics.areEqual(this.intent, semanticUnderstanding.intent) && Intrinsics.areEqual(this.entities, semanticUnderstanding.entities) && Intrinsics.areEqual(this.arguments, semanticUnderstanding.arguments) && Intrinsics.areEqual(this.constraints, semanticUnderstanding.constraints) && Intrinsics.areEqual(this.contextRefs, semanticUnderstanding.contextRefs) && Intrinsics.areEqual(this.conversationRefs, semanticUnderstanding.conversationRefs) && Float.compare(this.confidence, semanticUnderstanding.confidence) == 0 && this.ambiguity == semanticUnderstanding.ambiguity && this.clarity == semanticUnderstanding.clarity && Intrinsics.areEqual(this.requiredCapabilities, semanticUnderstanding.requiredCapabilities) && this.suggestedRoute == semanticUnderstanding.suggestedRoute && Intrinsics.areEqual(this.routeReason, semanticUnderstanding.routeReason) && Intrinsics.areEqual(this.clarifyQuestion, semanticUnderstanding.clarifyQuestion) && Intrinsics.areEqual(this.candidateInterpretations, semanticUnderstanding.candidateInterpretations) && Intrinsics.areEqual(this.rewrittenCommand, semanticUnderstanding.rewrittenCommand) && this.llmInterpretationUsed == semanticUnderstanding.llmInterpretationUsed && Intrinsics.areEqual(this.notes, semanticUnderstanding.notes);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((this.rawInput.hashCode() * 31) + this.normalizedInput.hashCode()) * 31) + this.goal.hashCode()) * 31) + this.intent.hashCode()) * 31) + this.entities.hashCode()) * 31) + this.arguments.hashCode()) * 31) + this.constraints.hashCode()) * 31) + this.contextRefs.hashCode()) * 31) + this.conversationRefs.hashCode()) * 31) + Float.hashCode(this.confidence)) * 31) + this.ambiguity.hashCode()) * 31) + this.clarity.hashCode()) * 31) + this.requiredCapabilities.hashCode()) * 31) + this.suggestedRoute.hashCode()) * 31) + this.routeReason.hashCode()) * 31) + (this.clarifyQuestion == null ? 0 : this.clarifyQuestion.hashCode())) * 31) + this.candidateInterpretations.hashCode()) * 31) + (this.rewrittenCommand != null ? this.rewrittenCommand.hashCode() : 0)) * 31) + Boolean.hashCode(this.llmInterpretationUsed)) * 31) + this.notes.hashCode();
    }

    public String toString() {
        return "SemanticUnderstanding(rawInput=" + this.rawInput + ", normalizedInput=" + this.normalizedInput + ", goal=" + this.goal + ", intent=" + this.intent + ", entities=" + this.entities + ", arguments=" + this.arguments + ", constraints=" + this.constraints + ", contextRefs=" + this.contextRefs + ", conversationRefs=" + this.conversationRefs + ", confidence=" + this.confidence + ", ambiguity=" + this.ambiguity + ", clarity=" + this.clarity + ", requiredCapabilities=" + this.requiredCapabilities + ", suggestedRoute=" + this.suggestedRoute + ", routeReason=" + this.routeReason + ", clarifyQuestion=" + this.clarifyQuestion + ", candidateInterpretations=" + this.candidateInterpretations + ", rewrittenCommand=" + this.rewrittenCommand + ", llmInterpretationUsed=" + this.llmInterpretationUsed + ", notes=" + this.notes + ")";
    }

    public SemanticUnderstanding(String rawInput, String normalizedInput, String goal, String intent, List<SemanticEntity> entities, Map<String, String> arguments, List<String> constraints, List<String> contextRefs, List<String> conversationRefs, float confidence, AmbiguityLevel ambiguity, SemanticClarity clarity, List<String> requiredCapabilities, RouteDecision suggestedRoute, String routeReason, String clarifyQuestion, List<String> candidateInterpretations, String rewrittenCommand, boolean llmInterpretationUsed, List<String> notes) {
        Intrinsics.checkNotNullParameter(rawInput, "rawInput");
        Intrinsics.checkNotNullParameter(normalizedInput, "normalizedInput");
        Intrinsics.checkNotNullParameter(goal, "goal");
        Intrinsics.checkNotNullParameter(intent, "intent");
        Intrinsics.checkNotNullParameter(entities, "entities");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        Intrinsics.checkNotNullParameter(constraints, "constraints");
        Intrinsics.checkNotNullParameter(contextRefs, "contextRefs");
        Intrinsics.checkNotNullParameter(conversationRefs, "conversationRefs");
        Intrinsics.checkNotNullParameter(ambiguity, "ambiguity");
        Intrinsics.checkNotNullParameter(clarity, "clarity");
        Intrinsics.checkNotNullParameter(requiredCapabilities, "requiredCapabilities");
        Intrinsics.checkNotNullParameter(suggestedRoute, "suggestedRoute");
        Intrinsics.checkNotNullParameter(routeReason, "routeReason");
        Intrinsics.checkNotNullParameter(candidateInterpretations, "candidateInterpretations");
        Intrinsics.checkNotNullParameter(notes, "notes");
        this.rawInput = rawInput;
        this.normalizedInput = normalizedInput;
        this.goal = goal;
        this.intent = intent;
        this.entities = entities;
        this.arguments = arguments;
        this.constraints = constraints;
        this.contextRefs = contextRefs;
        this.conversationRefs = conversationRefs;
        this.confidence = confidence;
        this.ambiguity = ambiguity;
        this.clarity = clarity;
        this.requiredCapabilities = requiredCapabilities;
        this.suggestedRoute = suggestedRoute;
        this.routeReason = routeReason;
        this.clarifyQuestion = clarifyQuestion;
        this.candidateInterpretations = candidateInterpretations;
        this.rewrittenCommand = rewrittenCommand;
        this.llmInterpretationUsed = llmInterpretationUsed;
        this.notes = notes;
        float f = this.confidence;
        boolean z = false;
        if (0.0f <= f && f <= 1.0f) {
            z = true;
        }
        if (!z) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (StringsKt.isBlank(this.goal)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!StringsKt.isBlank(this.intent)) {
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public /* synthetic */ SemanticUnderstanding(String str, String str2, String str3, String str4, List list, Map map, List list2, List list3, List list4, float f, AmbiguityLevel ambiguityLevel, SemanticClarity semanticClarity, List list5, RouteDecision routeDecision, String str5, String str6, List list6, String str7, boolean z, List list7, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, (i & 16) != 0 ? CollectionsKt.emptyList() : list, (i & 32) != 0 ? MapsKt.emptyMap() : map, (i & 64) != 0 ? CollectionsKt.emptyList() : list2, (i & 128) != 0 ? CollectionsKt.emptyList() : list3, (i & 256) != 0 ? CollectionsKt.emptyList() : list4, f, ambiguityLevel, semanticClarity, (i & 4096) != 0 ? CollectionsKt.emptyList() : list5, routeDecision, str5, (32768 & i) != 0 ? null : str6, (65536 & i) != 0 ? CollectionsKt.emptyList() : list6, (131072 & i) != 0 ? null : str7, (262144 & i) != 0 ? false : z, (i & 524288) != 0 ? CollectionsKt.emptyList() : list7);
    }

    public final String getRawInput() {
        return this.rawInput;
    }

    public final String getNormalizedInput() {
        return this.normalizedInput;
    }

    public final String getGoal() {
        return this.goal;
    }

    public final String getIntent() {
        return this.intent;
    }

    public final List<SemanticEntity> getEntities() {
        return this.entities;
    }

    public final Map<String, String> getArguments() {
        return this.arguments;
    }

    public final List<String> getConstraints() {
        return this.constraints;
    }

    public final List<String> getContextRefs() {
        return this.contextRefs;
    }

    public final List<String> getConversationRefs() {
        return this.conversationRefs;
    }

    public final float getConfidence() {
        return this.confidence;
    }

    public final AmbiguityLevel getAmbiguity() {
        return this.ambiguity;
    }

    public final SemanticClarity getClarity() {
        return this.clarity;
    }

    public final List<String> getRequiredCapabilities() {
        return this.requiredCapabilities;
    }

    public final RouteDecision getSuggestedRoute() {
        return this.suggestedRoute;
    }

    public final String getRouteReason() {
        return this.routeReason;
    }

    public final String getClarifyQuestion() {
        return this.clarifyQuestion;
    }

    public final List<String> getCandidateInterpretations() {
        return this.candidateInterpretations;
    }

    public final String getRewrittenCommand() {
        return this.rewrittenCommand;
    }

    public final boolean getLlmInterpretationUsed() {
        return this.llmInterpretationUsed;
    }

    public final List<String> getNotes() {
        return this.notes;
    }
}
