package com.jarvis.core.vision;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: VisionModels.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\b\b\u0086\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u0012\u001a\u00020\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J1\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001d"}, d2 = {"Lcom/jarvis/core/vision/VisionRequest;", "", "requestId", "", "imageJpeg", "", "prompt", "wantStructured", "", "<init>", "(Ljava/lang/String;[BLjava/lang/String;Z)V", "getRequestId", "()Ljava/lang/String;", "getImageJpeg", "()[B", "getPrompt", "getWantStructured", "()Z", "equals", "other", "hashCode", "", "component1", "component2", "component3", "component4", "copy", "toString", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class VisionRequest {
    public static final int MAX_BYTES = 5000000;
    private final byte[] imageJpeg;
    private final String prompt;
    private final String requestId;
    private final boolean wantStructured;

    public static /* synthetic */ VisionRequest copy$default(VisionRequest visionRequest, String str, byte[] bArr, String str2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = visionRequest.requestId;
        }
        if ((i & 2) != 0) {
            bArr = visionRequest.imageJpeg;
        }
        if ((i & 4) != 0) {
            str2 = visionRequest.prompt;
        }
        if ((i & 8) != 0) {
            z = visionRequest.wantStructured;
        }
        return visionRequest.copy(str, bArr, str2, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getRequestId() {
        return this.requestId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final byte[] getImageJpeg() {
        return this.imageJpeg;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getPrompt() {
        return this.prompt;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getWantStructured() {
        return this.wantStructured;
    }

    public final VisionRequest copy(String requestId, byte[] imageJpeg, String prompt, boolean wantStructured) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(imageJpeg, "imageJpeg");
        Intrinsics.checkNotNullParameter(prompt, "prompt");
        return new VisionRequest(requestId, imageJpeg, prompt, wantStructured);
    }

    public String toString() {
        return "VisionRequest(requestId=" + this.requestId + ", imageJpeg=" + Arrays.toString(this.imageJpeg) + ", prompt=" + this.prompt + ", wantStructured=" + this.wantStructured + ")";
    }

    public VisionRequest(String requestId, byte[] imageJpeg, String prompt, boolean wantStructured) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(imageJpeg, "imageJpeg");
        Intrinsics.checkNotNullParameter(prompt, "prompt");
        this.requestId = requestId;
        this.imageJpeg = imageJpeg;
        this.prompt = prompt;
        this.wantStructured = wantStructured;
        if (StringsKt.isBlank(this.requestId)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(this.imageJpeg.length <= 5000000)) {
            throw new IllegalArgumentException("image too large".toString());
        }
        if (this.prompt.length() <= 500) {
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public /* synthetic */ VisionRequest(String str, byte[] bArr, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, bArr, (i & 4) != 0 ? "Describe briefly" : str2, (i & 8) != 0 ? false : z);
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public final byte[] getImageJpeg() {
        return this.imageJpeg;
    }

    public final String getPrompt() {
        return this.prompt;
    }

    public final boolean getWantStructured() {
        return this.wantStructured;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof VisionRequest) {
            return Intrinsics.areEqual(this.requestId, ((VisionRequest) other).requestId) && Arrays.equals(this.imageJpeg, ((VisionRequest) other).imageJpeg) && Intrinsics.areEqual(this.prompt, ((VisionRequest) other).prompt);
        }
        return false;
    }

    public int hashCode() {
        return this.requestId.hashCode();
    }
}
