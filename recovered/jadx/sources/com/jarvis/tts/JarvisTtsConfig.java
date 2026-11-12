package com.jarvis.tts;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JarvisTts.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\tHÆ\u0003J1\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001e"}, d2 = {"Lcom/jarvis/tts/JarvisTtsConfig;", "", "modelDir", "Ljava/io/File;", "modelOnnx", "", "numThreads", "", "speed", "", "<init>", "(Ljava/io/File;Ljava/lang/String;IF)V", "getModelDir", "()Ljava/io/File;", "getModelOnnx", "()Ljava/lang/String;", "getNumThreads", "()I", "getSpeed", "()F", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "tts-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class JarvisTtsConfig {
    private final File modelDir;
    private final String modelOnnx;
    private final int numThreads;
    private final float speed;

    public static /* synthetic */ JarvisTtsConfig copy$default(JarvisTtsConfig jarvisTtsConfig, File file, String str, int i, float f, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            file = jarvisTtsConfig.modelDir;
        }
        if ((i2 & 2) != 0) {
            str = jarvisTtsConfig.modelOnnx;
        }
        if ((i2 & 4) != 0) {
            i = jarvisTtsConfig.numThreads;
        }
        if ((i2 & 8) != 0) {
            f = jarvisTtsConfig.speed;
        }
        return jarvisTtsConfig.copy(file, str, i, f);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final File getModelDir() {
        return this.modelDir;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getModelOnnx() {
        return this.modelOnnx;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getNumThreads() {
        return this.numThreads;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final float getSpeed() {
        return this.speed;
    }

    public final JarvisTtsConfig copy(File modelDir, String modelOnnx, int numThreads, float speed) {
        Intrinsics.checkNotNullParameter(modelDir, "modelDir");
        Intrinsics.checkNotNullParameter(modelOnnx, "modelOnnx");
        return new JarvisTtsConfig(modelDir, modelOnnx, numThreads, speed);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JarvisTtsConfig)) {
            return false;
        }
        JarvisTtsConfig jarvisTtsConfig = (JarvisTtsConfig) other;
        return Intrinsics.areEqual(this.modelDir, jarvisTtsConfig.modelDir) && Intrinsics.areEqual(this.modelOnnx, jarvisTtsConfig.modelOnnx) && this.numThreads == jarvisTtsConfig.numThreads && Float.compare(this.speed, jarvisTtsConfig.speed) == 0;
    }

    public int hashCode() {
        return (((((this.modelDir.hashCode() * 31) + this.modelOnnx.hashCode()) * 31) + Integer.hashCode(this.numThreads)) * 31) + Float.hashCode(this.speed);
    }

    public String toString() {
        return "JarvisTtsConfig(modelDir=" + this.modelDir + ", modelOnnx=" + this.modelOnnx + ", numThreads=" + this.numThreads + ", speed=" + this.speed + ")";
    }

    public JarvisTtsConfig(File modelDir, String modelOnnx, int numThreads, float speed) {
        Intrinsics.checkNotNullParameter(modelDir, "modelDir");
        Intrinsics.checkNotNullParameter(modelOnnx, "modelOnnx");
        this.modelDir = modelDir;
        this.modelOnnx = modelOnnx;
        this.numThreads = numThreads;
        this.speed = speed;
    }

    public /* synthetic */ JarvisTtsConfig(File file, String str, int i, float f, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(file, (i2 & 2) != 0 ? "ru_RU-dmitri-medium.onnx" : str, (i2 & 4) != 0 ? 4 : i, (i2 & 8) != 0 ? 1.12f : f);
    }

    public final File getModelDir() {
        return this.modelDir;
    }

    public final String getModelOnnx() {
        return this.modelOnnx;
    }

    public final int getNumThreads() {
        return this.numThreads;
    }

    public final float getSpeed() {
        return this.speed;
    }
}
