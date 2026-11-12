package com.jarvis.brain.aiprovider;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AIProviderModels.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/jarvis/brain/aiprovider/AIProviderError;", "", "category", "Lcom/jarvis/brain/aiprovider/AIProviderErrorCategory;", "code", "", "message", "<init>", "(Lcom/jarvis/brain/aiprovider/AIProviderErrorCategory;Ljava/lang/String;Ljava/lang/String;)V", "getCategory", "()Lcom/jarvis/brain/aiprovider/AIProviderErrorCategory;", "getCode", "()Ljava/lang/String;", "getMessage", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AIProviderError {
    private final AIProviderErrorCategory category;
    private final String code;
    private final String message;

    public static /* synthetic */ AIProviderError copy$default(AIProviderError aIProviderError, AIProviderErrorCategory aIProviderErrorCategory, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            aIProviderErrorCategory = aIProviderError.category;
        }
        if ((i & 2) != 0) {
            str = aIProviderError.code;
        }
        if ((i & 4) != 0) {
            str2 = aIProviderError.message;
        }
        return aIProviderError.copy(aIProviderErrorCategory, str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final AIProviderErrorCategory getCategory() {
        return this.category;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getCode() {
        return this.code;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    public final AIProviderError copy(AIProviderErrorCategory category, String code, String message) {
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(message, "message");
        return new AIProviderError(category, code, message);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AIProviderError)) {
            return false;
        }
        AIProviderError aIProviderError = (AIProviderError) other;
        return this.category == aIProviderError.category && Intrinsics.areEqual(this.code, aIProviderError.code) && Intrinsics.areEqual(this.message, aIProviderError.message);
    }

    public int hashCode() {
        return (((this.category.hashCode() * 31) + this.code.hashCode()) * 31) + this.message.hashCode();
    }

    public String toString() {
        return "AIProviderError(category=" + this.category + ", code=" + this.code + ", message=" + this.message + ")";
    }

    public AIProviderError(AIProviderErrorCategory category, String code, String message) {
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(message, "message");
        this.category = category;
        this.code = code;
        this.message = message;
        if (StringsKt.isBlank(this.code)) {
            throw new IllegalArgumentException("error code blank".toString());
        }
        if (!(this.message.length() <= 240)) {
            throw new IllegalArgumentException("error message too long".toString());
        }
    }

    public final AIProviderErrorCategory getCategory() {
        return this.category;
    }

    public final String getCode() {
        return this.code;
    }

    public final String getMessage() {
        return this.message;
    }
}
