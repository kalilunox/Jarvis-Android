package com.jarvis.asr;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AsrModelSpec.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 !2\u00020\u0001:\u0001!B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u0013J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003JE\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\""}, d2 = {"Lcom/jarvis/asr/AsrModelSpec;", "", "dirName", "", "modelType", "encoderFile", "decoderFile", "joinerFile", "tokensFile", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDirName", "()Ljava/lang/String;", "getModelType", "getEncoderFile", "getDecoderFile", "getJoinerFile", "getTokensFile", "requiredFiles", "", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "asr-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AsrModelSpec {
    private final String decoderFile;
    private final String dirName;
    private final String encoderFile;
    private final String joinerFile;
    private final String modelType;
    private final String tokensFile;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AsrModelSpec RU_STREAMING = new AsrModelSpec(SherpaAsrSession.MODEL_DIR, "zipformer2", null, null, null, null, 60, null);
    private static final AsrModelSpec EN_STREAMING = new AsrModelSpec("sherpa-onnx-streaming-zipformer-en-20M-2023-02-17", "", null, null, null, null, 60, null);

    public static /* synthetic */ AsrModelSpec copy$default(AsrModelSpec asrModelSpec, String str, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = asrModelSpec.dirName;
        }
        if ((i & 2) != 0) {
            str2 = asrModelSpec.modelType;
        }
        String str7 = str2;
        if ((i & 4) != 0) {
            str3 = asrModelSpec.encoderFile;
        }
        String str8 = str3;
        if ((i & 8) != 0) {
            str4 = asrModelSpec.decoderFile;
        }
        String str9 = str4;
        if ((i & 16) != 0) {
            str5 = asrModelSpec.joinerFile;
        }
        String str10 = str5;
        if ((i & 32) != 0) {
            str6 = asrModelSpec.tokensFile;
        }
        return asrModelSpec.copy(str, str7, str8, str9, str10, str6);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getDirName() {
        return this.dirName;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getModelType() {
        return this.modelType;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getEncoderFile() {
        return this.encoderFile;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getDecoderFile() {
        return this.decoderFile;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getJoinerFile() {
        return this.joinerFile;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getTokensFile() {
        return this.tokensFile;
    }

    public final AsrModelSpec copy(String dirName, String modelType, String encoderFile, String decoderFile, String joinerFile, String tokensFile) {
        Intrinsics.checkNotNullParameter(dirName, "dirName");
        Intrinsics.checkNotNullParameter(modelType, "modelType");
        Intrinsics.checkNotNullParameter(encoderFile, "encoderFile");
        Intrinsics.checkNotNullParameter(decoderFile, "decoderFile");
        Intrinsics.checkNotNullParameter(joinerFile, "joinerFile");
        Intrinsics.checkNotNullParameter(tokensFile, "tokensFile");
        return new AsrModelSpec(dirName, modelType, encoderFile, decoderFile, joinerFile, tokensFile);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AsrModelSpec)) {
            return false;
        }
        AsrModelSpec asrModelSpec = (AsrModelSpec) other;
        return Intrinsics.areEqual(this.dirName, asrModelSpec.dirName) && Intrinsics.areEqual(this.modelType, asrModelSpec.modelType) && Intrinsics.areEqual(this.encoderFile, asrModelSpec.encoderFile) && Intrinsics.areEqual(this.decoderFile, asrModelSpec.decoderFile) && Intrinsics.areEqual(this.joinerFile, asrModelSpec.joinerFile) && Intrinsics.areEqual(this.tokensFile, asrModelSpec.tokensFile);
    }

    public int hashCode() {
        return (((((((((this.dirName.hashCode() * 31) + this.modelType.hashCode()) * 31) + this.encoderFile.hashCode()) * 31) + this.decoderFile.hashCode()) * 31) + this.joinerFile.hashCode()) * 31) + this.tokensFile.hashCode();
    }

    public String toString() {
        return "AsrModelSpec(dirName=" + this.dirName + ", modelType=" + this.modelType + ", encoderFile=" + this.encoderFile + ", decoderFile=" + this.decoderFile + ", joinerFile=" + this.joinerFile + ", tokensFile=" + this.tokensFile + ")";
    }

    public AsrModelSpec(String dirName, String modelType, String encoderFile, String decoderFile, String joinerFile, String tokensFile) {
        Intrinsics.checkNotNullParameter(dirName, "dirName");
        Intrinsics.checkNotNullParameter(modelType, "modelType");
        Intrinsics.checkNotNullParameter(encoderFile, "encoderFile");
        Intrinsics.checkNotNullParameter(decoderFile, "decoderFile");
        Intrinsics.checkNotNullParameter(joinerFile, "joinerFile");
        Intrinsics.checkNotNullParameter(tokensFile, "tokensFile");
        this.dirName = dirName;
        this.modelType = modelType;
        this.encoderFile = encoderFile;
        this.decoderFile = decoderFile;
        this.joinerFile = joinerFile;
        this.tokensFile = tokensFile;
    }

    public /* synthetic */ AsrModelSpec(String str, String str2, String str3, String str4, String str5, String str6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? "encoder.int8.onnx" : str3, (i & 8) != 0 ? "decoder.onnx" : str4, (i & 16) != 0 ? "joiner.int8.onnx" : str5, (i & 32) != 0 ? "tokens.txt" : str6);
    }

    public final String getDirName() {
        return this.dirName;
    }

    public final String getModelType() {
        return this.modelType;
    }

    public final String getEncoderFile() {
        return this.encoderFile;
    }

    public final String getDecoderFile() {
        return this.decoderFile;
    }

    public final String getJoinerFile() {
        return this.joinerFile;
    }

    public final String getTokensFile() {
        return this.tokensFile;
    }

    public final List<String> requiredFiles() {
        return CollectionsKt.listOf((Object[]) new String[]{this.encoderFile, this.decoderFile, this.joinerFile, this.tokensFile});
    }

    /* JADX INFO: compiled from: AsrModelSpec.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/jarvis/asr/AsrModelSpec$Companion;", "", "<init>", "()V", "RU_STREAMING", "Lcom/jarvis/asr/AsrModelSpec;", "getRU_STREAMING", "()Lcom/jarvis/asr/AsrModelSpec;", "EN_STREAMING", "getEN_STREAMING", "asr-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AsrModelSpec getRU_STREAMING() {
            return AsrModelSpec.RU_STREAMING;
        }

        public final AsrModelSpec getEN_STREAMING() {
            return AsrModelSpec.EN_STREAMING;
        }
    }
}
