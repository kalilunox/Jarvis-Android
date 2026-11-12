package com.jarvis.core.verification;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: VerificationModels.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lcom/jarvis/core/verification/ExpectedEffect;", "", "key", "", "expectedValue", "required", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getKey", "()Ljava/lang/String;", "getExpectedValue", "getRequired", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ExpectedEffect {
    private final String expectedValue;
    private final String key;
    private final boolean required;

    public static /* synthetic */ ExpectedEffect copy$default(ExpectedEffect expectedEffect, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = expectedEffect.key;
        }
        if ((i & 2) != 0) {
            str2 = expectedEffect.expectedValue;
        }
        if ((i & 4) != 0) {
            z = expectedEffect.required;
        }
        return expectedEffect.copy(str, str2, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getKey() {
        return this.key;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getExpectedValue() {
        return this.expectedValue;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getRequired() {
        return this.required;
    }

    public final ExpectedEffect copy(String key, String expectedValue, boolean required) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(expectedValue, "expectedValue");
        return new ExpectedEffect(key, expectedValue, required);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ExpectedEffect)) {
            return false;
        }
        ExpectedEffect expectedEffect = (ExpectedEffect) other;
        return Intrinsics.areEqual(this.key, expectedEffect.key) && Intrinsics.areEqual(this.expectedValue, expectedEffect.expectedValue) && this.required == expectedEffect.required;
    }

    public int hashCode() {
        return (((this.key.hashCode() * 31) + this.expectedValue.hashCode()) * 31) + Boolean.hashCode(this.required);
    }

    public String toString() {
        return "ExpectedEffect(key=" + this.key + ", expectedValue=" + this.expectedValue + ", required=" + this.required + ")";
    }

    public ExpectedEffect(String key, String expectedValue, boolean required) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(expectedValue, "expectedValue");
        this.key = key;
        this.expectedValue = expectedValue;
        this.required = required;
        if (!(!StringsKt.isBlank(this.key) && this.key.length() <= 64)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (this.expectedValue.length() <= 256) {
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public /* synthetic */ ExpectedEffect(String str, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? true : z);
    }

    public final String getKey() {
        return this.key;
    }

    public final String getExpectedValue() {
        return this.expectedValue;
    }

    public final boolean getRequired() {
        return this.required;
    }
}
