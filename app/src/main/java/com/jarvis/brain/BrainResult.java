package com.jarvis.brain;

import com.jarvis.core.common.model.Intent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrainRouter.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004\u0082\u0001\u0003\u0005\u0006\u0007¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lcom/jarvis/brain/BrainResult;", "", "SkillCall", "Clarify", "Chat", "Lcom/jarvis/brain/BrainResult$Chat;", "Lcom/jarvis/brain/BrainResult$Clarify;", "Lcom/jarvis/brain/BrainResult$SkillCall;", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface BrainResult {

    /* JADX INFO: compiled from: BrainRouter.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/jarvis/brain/BrainResult$SkillCall;", "Lcom/jarvis/brain/BrainResult;", "intent", "Lcom/jarvis/core/common/model/Intent;", "confidence", "", "<init>", "(Lcom/jarvis/core/common/model/Intent;F)V", "getIntent", "()Lcom/jarvis/core/common/model/Intent;", "getConfidence", "()F", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class SkillCall implements BrainResult {
        private final float confidence;
        private final Intent intent;

        public static /* synthetic */ SkillCall copy$default(SkillCall skillCall, Intent intent, float f, int i, Object obj) {
            if ((i & 1) != 0) {
                intent = skillCall.intent;
            }
            if ((i & 2) != 0) {
                f = skillCall.confidence;
            }
            return skillCall.copy(intent, f);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Intent getIntent() {
            return this.intent;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final float getConfidence() {
            return this.confidence;
        }

        public final SkillCall copy(Intent intent, float confidence) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            return new SkillCall(intent, confidence);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SkillCall)) {
                return false;
            }
            SkillCall skillCall = (SkillCall) other;
            return Intrinsics.areEqual(this.intent, skillCall.intent) && Float.compare(this.confidence, skillCall.confidence) == 0;
        }

        public int hashCode() {
            return (this.intent.hashCode() * 31) + Float.hashCode(this.confidence);
        }

        public String toString() {
            return "SkillCall(intent=" + this.intent + ", confidence=" + this.confidence + ")";
        }

        public SkillCall(Intent intent, float confidence) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            this.intent = intent;
            this.confidence = confidence;
        }

        public final Intent getIntent() {
            return this.intent;
        }

        public final float getConfidence() {
            return this.confidence;
        }
    }

    /* JADX INFO: compiled from: BrainRouter.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/jarvis/brain/BrainResult$Clarify;", "Lcom/jarvis/brain/BrainResult;", "question", "", "confidence", "", "<init>", "(Ljava/lang/String;F)V", "getQuestion", "()Ljava/lang/String;", "getConfidence", "()F", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Clarify implements BrainResult {
        private final float confidence;
        private final String question;

        public static /* synthetic */ Clarify copy$default(Clarify clarify, String str, float f, int i, Object obj) {
            if ((i & 1) != 0) {
                str = clarify.question;
            }
            if ((i & 2) != 0) {
                f = clarify.confidence;
            }
            return clarify.copy(str, f);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getQuestion() {
            return this.question;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final float getConfidence() {
            return this.confidence;
        }

        public final Clarify copy(String question, float confidence) {
            Intrinsics.checkNotNullParameter(question, "question");
            return new Clarify(question, confidence);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Clarify)) {
                return false;
            }
            Clarify clarify = (Clarify) other;
            return Intrinsics.areEqual(this.question, clarify.question) && Float.compare(this.confidence, clarify.confidence) == 0;
        }

        public int hashCode() {
            return (this.question.hashCode() * 31) + Float.hashCode(this.confidence);
        }

        public String toString() {
            return "Clarify(question=" + this.question + ", confidence=" + this.confidence + ")";
        }

        public Clarify(String question, float confidence) {
            Intrinsics.checkNotNullParameter(question, "question");
            this.question = question;
            this.confidence = confidence;
        }

        public final String getQuestion() {
            return this.question;
        }

        public final float getConfidence() {
            return this.confidence;
        }
    }

    /* JADX INFO: compiled from: BrainRouter.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/jarvis/brain/BrainResult$Chat;", "Lcom/jarvis/brain/BrainResult;", "confidence", "", "<init>", "(F)V", "getConfidence", "()F", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Chat implements BrainResult {
        private final float confidence;

        public static /* synthetic */ Chat copy$default(Chat chat, float f, int i, Object obj) {
            if ((i & 1) != 0) {
                f = chat.confidence;
            }
            return chat.copy(f);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final float getConfidence() {
            return this.confidence;
        }

        public final Chat copy(float confidence) {
            return new Chat(confidence);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Chat) && Float.compare(this.confidence, ((Chat) other).confidence) == 0;
        }

        public int hashCode() {
            return Float.hashCode(this.confidence);
        }

        public String toString() {
            return "Chat(confidence=" + this.confidence + ")";
        }

        public Chat(float confidence) {
            this.confidence = confidence;
        }

        public final float getConfidence() {
            return this.confidence;
        }
    }
}
