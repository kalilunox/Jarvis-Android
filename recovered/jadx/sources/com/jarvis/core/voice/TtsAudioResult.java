package com.jarvis.core.voice;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TtsContracts.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b&\b\u0086\b\u0018\u00002\u00020\u0001Bo\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0007HÆ\u0003J\t\u0010,\u001a\u00020\u0007HÆ\u0003J\t\u0010-\u001a\u00020\u0007HÆ\u0003J\t\u0010.\u001a\u00020\u000bHÆ\u0003J\t\u0010/\u001a\u00020\u000bHÆ\u0003J\t\u00100\u001a\u00020\u000eHÆ\u0003J\t\u00101\u001a\u00020\u000eHÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0013HÆ\u0003J{\u00104\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÆ\u0001J\u0013\u00105\u001a\u00020\u000e2\b\u00106\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00107\u001a\u00020\u0005HÖ\u0001J\t\u00108\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u000f\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\"R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010(\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b(\u0010\"¨\u00069"}, d2 = {"Lcom/jarvis/core/voice/TtsAudioResult;", "", "samples", "", "sampleRateHz", "", "provider", "", "model", "voice", "setupMs", "", "synthesisMs", "streamingSupported", "", "fallback", "fallbackReason", "Lcom/jarvis/core/voice/TtsRouteReason;", "errorKind", "Lcom/jarvis/core/voice/TtsErrorKind;", "<init>", "([FILjava/lang/String;Ljava/lang/String;Ljava/lang/String;JJZZLcom/jarvis/core/voice/TtsRouteReason;Lcom/jarvis/core/voice/TtsErrorKind;)V", "getSamples", "()[F", "getSampleRateHz", "()I", "getProvider", "()Ljava/lang/String;", "getModel", "getVoice", "getSetupMs", "()J", "getSynthesisMs", "getStreamingSupported", "()Z", "getFallback", "getFallbackReason", "()Lcom/jarvis/core/voice/TtsRouteReason;", "getErrorKind", "()Lcom/jarvis/core/voice/TtsErrorKind;", "isEmpty", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "equals", "other", "hashCode", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class TtsAudioResult {
    private final TtsErrorKind errorKind;
    private final boolean fallback;
    private final TtsRouteReason fallbackReason;
    private final String model;
    private final String provider;
    private final int sampleRateHz;
    private final float[] samples;
    private final long setupMs;
    private final boolean streamingSupported;
    private final long synthesisMs;
    private final String voice;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final float[] getSamples() {
        return this.samples;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final TtsRouteReason getFallbackReason() {
        return this.fallbackReason;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final TtsErrorKind getErrorKind() {
        return this.errorKind;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getSampleRateHz() {
        return this.sampleRateHz;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getProvider() {
        return this.provider;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getModel() {
        return this.model;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getVoice() {
        return this.voice;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getSetupMs() {
        return this.setupMs;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final long getSynthesisMs() {
        return this.synthesisMs;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final boolean getStreamingSupported() {
        return this.streamingSupported;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final boolean getFallback() {
        return this.fallback;
    }

    public final TtsAudioResult copy(float[] samples, int sampleRateHz, String provider, String model, String voice, long setupMs, long synthesisMs, boolean streamingSupported, boolean fallback, TtsRouteReason fallbackReason, TtsErrorKind errorKind) {
        Intrinsics.checkNotNullParameter(samples, "samples");
        Intrinsics.checkNotNullParameter(provider, "provider");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(voice, "voice");
        return new TtsAudioResult(samples, sampleRateHz, provider, model, voice, setupMs, synthesisMs, streamingSupported, fallback, fallbackReason, errorKind);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TtsAudioResult)) {
            return false;
        }
        TtsAudioResult ttsAudioResult = (TtsAudioResult) other;
        return Intrinsics.areEqual(this.samples, ttsAudioResult.samples) && this.sampleRateHz == ttsAudioResult.sampleRateHz && Intrinsics.areEqual(this.provider, ttsAudioResult.provider) && Intrinsics.areEqual(this.model, ttsAudioResult.model) && Intrinsics.areEqual(this.voice, ttsAudioResult.voice) && this.setupMs == ttsAudioResult.setupMs && this.synthesisMs == ttsAudioResult.synthesisMs && this.streamingSupported == ttsAudioResult.streamingSupported && this.fallback == ttsAudioResult.fallback && this.fallbackReason == ttsAudioResult.fallbackReason && this.errorKind == ttsAudioResult.errorKind;
    }

    public int hashCode() {
        return (((((((((((((((((((Arrays.hashCode(this.samples) * 31) + Integer.hashCode(this.sampleRateHz)) * 31) + this.provider.hashCode()) * 31) + this.model.hashCode()) * 31) + this.voice.hashCode()) * 31) + Long.hashCode(this.setupMs)) * 31) + Long.hashCode(this.synthesisMs)) * 31) + Boolean.hashCode(this.streamingSupported)) * 31) + Boolean.hashCode(this.fallback)) * 31) + (this.fallbackReason == null ? 0 : this.fallbackReason.hashCode())) * 31) + (this.errorKind != null ? this.errorKind.hashCode() : 0);
    }

    public String toString() {
        return "TtsAudioResult(samples=" + Arrays.toString(this.samples) + ", sampleRateHz=" + this.sampleRateHz + ", provider=" + this.provider + ", model=" + this.model + ", voice=" + this.voice + ", setupMs=" + this.setupMs + ", synthesisMs=" + this.synthesisMs + ", streamingSupported=" + this.streamingSupported + ", fallback=" + this.fallback + ", fallbackReason=" + this.fallbackReason + ", errorKind=" + this.errorKind + ")";
    }

    public TtsAudioResult(float[] samples, int sampleRateHz, String provider, String model, String voice, long setupMs, long synthesisMs, boolean streamingSupported, boolean fallback, TtsRouteReason fallbackReason, TtsErrorKind errorKind) {
        Intrinsics.checkNotNullParameter(samples, "samples");
        Intrinsics.checkNotNullParameter(provider, "provider");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(voice, "voice");
        this.samples = samples;
        this.sampleRateHz = sampleRateHz;
        this.provider = provider;
        this.model = model;
        this.voice = voice;
        this.setupMs = setupMs;
        this.synthesisMs = synthesisMs;
        this.streamingSupported = streamingSupported;
        this.fallback = fallback;
        this.fallbackReason = fallbackReason;
        this.errorKind = errorKind;
    }

    public /* synthetic */ TtsAudioResult(float[] fArr, int i, String str, String str2, String str3, long j, long j2, boolean z, boolean z2, TtsRouteReason ttsRouteReason, TtsErrorKind ttsErrorKind, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(fArr, i, str, str2, str3, (i2 & 32) != 0 ? 0L : j, (i2 & 64) != 0 ? 0L : j2, (i2 & 128) != 0 ? false : z, (i2 & 256) != 0 ? false : z2, (i2 & 512) != 0 ? null : ttsRouteReason, (i2 & 1024) != 0 ? null : ttsErrorKind);
    }

    public final float[] getSamples() {
        return this.samples;
    }

    public final int getSampleRateHz() {
        return this.sampleRateHz;
    }

    public final String getProvider() {
        return this.provider;
    }

    public final String getModel() {
        return this.model;
    }

    public final String getVoice() {
        return this.voice;
    }

    public final long getSetupMs() {
        return this.setupMs;
    }

    public final long getSynthesisMs() {
        return this.synthesisMs;
    }

    public final boolean getStreamingSupported() {
        return this.streamingSupported;
    }

    public final boolean getFallback() {
        return this.fallback;
    }

    public final TtsRouteReason getFallbackReason() {
        return this.fallbackReason;
    }

    public final TtsErrorKind getErrorKind() {
        return this.errorKind;
    }

    public final boolean isEmpty() {
        return this.samples.length == 0;
    }
}
