package com.jarvis.core.proactive;

import com.jarvis.app.proactive.ProactiveActionReceiver;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CandidateFilterResult.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u001b\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\u0007HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\rHÆ\u0003J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00070\u000fHÆ\u0003J[\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000fHÆ\u0001J\u0013\u0010(\u001a\u00020\u00032\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020+HÖ\u0001J\t\u0010,\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u0006-"}, d2 = {"Lcom/jarvis/core/proactive/CandidateFilterResult;", "", "eligible", "", "reasonCode", "Lcom/jarvis/core/proactive/ProactiveReasonCode;", ProactiveActionReceiver.EXTRA_FINGERPRINT, "", "match", "Lcom/jarvis/core/proactive/ContextMatchResult;", "relevance", "Lcom/jarvis/core/proactive/RelevanceResult;", "cooldown", "Lcom/jarvis/core/proactive/CooldownCheckResult;", "notes", "", "<init>", "(ZLcom/jarvis/core/proactive/ProactiveReasonCode;Ljava/lang/String;Lcom/jarvis/core/proactive/ContextMatchResult;Lcom/jarvis/core/proactive/RelevanceResult;Lcom/jarvis/core/proactive/CooldownCheckResult;Ljava/util/List;)V", "getEligible", "()Z", "getReasonCode", "()Lcom/jarvis/core/proactive/ProactiveReasonCode;", "getFingerprint", "()Ljava/lang/String;", "getMatch", "()Lcom/jarvis/core/proactive/ContextMatchResult;", "getRelevance", "()Lcom/jarvis/core/proactive/RelevanceResult;", "getCooldown", "()Lcom/jarvis/core/proactive/CooldownCheckResult;", "getNotes", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class CandidateFilterResult {
    private final CooldownCheckResult cooldown;
    private final boolean eligible;
    private final String fingerprint;
    private final ContextMatchResult match;
    private final List<String> notes;
    private final ProactiveReasonCode reasonCode;
    private final RelevanceResult relevance;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CandidateFilterResult copy$default(CandidateFilterResult candidateFilterResult, boolean z, ProactiveReasonCode proactiveReasonCode, String str, ContextMatchResult contextMatchResult, RelevanceResult relevanceResult, CooldownCheckResult cooldownCheckResult, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            z = candidateFilterResult.eligible;
        }
        if ((i & 2) != 0) {
            proactiveReasonCode = candidateFilterResult.reasonCode;
        }
        ProactiveReasonCode proactiveReasonCode2 = proactiveReasonCode;
        if ((i & 4) != 0) {
            str = candidateFilterResult.fingerprint;
        }
        String str2 = str;
        if ((i & 8) != 0) {
            contextMatchResult = candidateFilterResult.match;
        }
        ContextMatchResult contextMatchResult2 = contextMatchResult;
        if ((i & 16) != 0) {
            relevanceResult = candidateFilterResult.relevance;
        }
        RelevanceResult relevanceResult2 = relevanceResult;
        if ((i & 32) != 0) {
            cooldownCheckResult = candidateFilterResult.cooldown;
        }
        CooldownCheckResult cooldownCheckResult2 = cooldownCheckResult;
        if ((i & 64) != 0) {
            list = candidateFilterResult.notes;
        }
        return candidateFilterResult.copy(z, proactiveReasonCode2, str2, contextMatchResult2, relevanceResult2, cooldownCheckResult2, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getEligible() {
        return this.eligible;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ProactiveReasonCode getReasonCode() {
        return this.reasonCode;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getFingerprint() {
        return this.fingerprint;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final ContextMatchResult getMatch() {
        return this.match;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final RelevanceResult getRelevance() {
        return this.relevance;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final CooldownCheckResult getCooldown() {
        return this.cooldown;
    }

    public final List<String> component7() {
        return this.notes;
    }

    public final CandidateFilterResult copy(boolean eligible, ProactiveReasonCode reasonCode, String fingerprint, ContextMatchResult match, RelevanceResult relevance, CooldownCheckResult cooldown, List<String> notes) {
        Intrinsics.checkNotNullParameter(reasonCode, "reasonCode");
        Intrinsics.checkNotNullParameter(fingerprint, "fingerprint");
        Intrinsics.checkNotNullParameter(notes, "notes");
        return new CandidateFilterResult(eligible, reasonCode, fingerprint, match, relevance, cooldown, notes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CandidateFilterResult)) {
            return false;
        }
        CandidateFilterResult candidateFilterResult = (CandidateFilterResult) other;
        return this.eligible == candidateFilterResult.eligible && this.reasonCode == candidateFilterResult.reasonCode && Intrinsics.areEqual(this.fingerprint, candidateFilterResult.fingerprint) && Intrinsics.areEqual(this.match, candidateFilterResult.match) && Intrinsics.areEqual(this.relevance, candidateFilterResult.relevance) && Intrinsics.areEqual(this.cooldown, candidateFilterResult.cooldown) && Intrinsics.areEqual(this.notes, candidateFilterResult.notes);
    }

    public int hashCode() {
        return (((((((((((Boolean.hashCode(this.eligible) * 31) + this.reasonCode.hashCode()) * 31) + this.fingerprint.hashCode()) * 31) + (this.match == null ? 0 : this.match.hashCode())) * 31) + (this.relevance == null ? 0 : this.relevance.hashCode())) * 31) + (this.cooldown != null ? this.cooldown.hashCode() : 0)) * 31) + this.notes.hashCode();
    }

    public String toString() {
        return "CandidateFilterResult(eligible=" + this.eligible + ", reasonCode=" + this.reasonCode + ", fingerprint=" + this.fingerprint + ", match=" + this.match + ", relevance=" + this.relevance + ", cooldown=" + this.cooldown + ", notes=" + this.notes + ")";
    }

    public CandidateFilterResult(boolean eligible, ProactiveReasonCode reasonCode, String fingerprint, ContextMatchResult match, RelevanceResult relevance, CooldownCheckResult cooldown, List<String> notes) {
        Intrinsics.checkNotNullParameter(reasonCode, "reasonCode");
        Intrinsics.checkNotNullParameter(fingerprint, "fingerprint");
        Intrinsics.checkNotNullParameter(notes, "notes");
        this.eligible = eligible;
        this.reasonCode = reasonCode;
        this.fingerprint = fingerprint;
        this.match = match;
        this.relevance = relevance;
        this.cooldown = cooldown;
        this.notes = notes;
    }

    public /* synthetic */ CandidateFilterResult(boolean z, ProactiveReasonCode proactiveReasonCode, String str, ContextMatchResult contextMatchResult, RelevanceResult relevanceResult, CooldownCheckResult cooldownCheckResult, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, proactiveReasonCode, str, (i & 8) != 0 ? null : contextMatchResult, (i & 16) != 0 ? null : relevanceResult, (i & 32) != 0 ? null : cooldownCheckResult, (i & 64) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final boolean getEligible() {
        return this.eligible;
    }

    public final ProactiveReasonCode getReasonCode() {
        return this.reasonCode;
    }

    public final String getFingerprint() {
        return this.fingerprint;
    }

    public final ContextMatchResult getMatch() {
        return this.match;
    }

    public final RelevanceResult getRelevance() {
        return this.relevance;
    }

    public final CooldownCheckResult getCooldown() {
        return this.cooldown;
    }

    public final List<String> getNotes() {
        return this.notes;
    }
}
