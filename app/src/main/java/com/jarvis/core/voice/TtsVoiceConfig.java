package com.jarvis.core.voice;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: compiled from: TtsContracts.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001d\u001a\u00020\nHÆ\u0003J\t\u0010\u001e\u001a\u00020\nHÆ\u0003JO\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\nHÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\nHÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016¨\u0006%"}, d2 = {"Lcom/jarvis/core/voice/TtsVoiceConfig;", "", "language", "", "voice", "model", "speakingRate", "", "pitch", "sampleRateHz", "", "channels", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;FFII)V", "getLanguage", "()Ljava/lang/String;", "getVoice", "getModel", "getSpeakingRate", "()F", "getPitch", "getSampleRateHz", "()I", "getChannels", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class TtsVoiceConfig {
    private final int channels;
    private final String language;
    private final String model;
    private final float pitch;
    private final int sampleRateHz;
    private final float speakingRate;
    private final String voice;

    public TtsVoiceConfig() {
        this(null, null, null, 0.0f, 0.0f, 0, 0, WorkQueueKt.MASK, null);
    }

    public static /* synthetic */ TtsVoiceConfig copy$default(TtsVoiceConfig ttsVoiceConfig, String str, String str2, String str3, float f, float f2, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = ttsVoiceConfig.language;
        }
        if ((i3 & 2) != 0) {
            str2 = ttsVoiceConfig.voice;
        }
        String str4 = str2;
        if ((i3 & 4) != 0) {
            str3 = ttsVoiceConfig.model;
        }
        String str5 = str3;
        if ((i3 & 8) != 0) {
            f = ttsVoiceConfig.speakingRate;
        }
        float f3 = f;
        if ((i3 & 16) != 0) {
            f2 = ttsVoiceConfig.pitch;
        }
        float f4 = f2;
        if ((i3 & 32) != 0) {
            i = ttsVoiceConfig.sampleRateHz;
        }
        int i4 = i;
        if ((i3 & 64) != 0) {
            i2 = ttsVoiceConfig.channels;
        }
        return ttsVoiceConfig.copy(str, str4, str5, f3, f4, i4, i2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getLanguage() {
        return this.language;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getVoice() {
        return this.voice;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getModel() {
        return this.model;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final float getSpeakingRate() {
        return this.speakingRate;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final float getPitch() {
        return this.pitch;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getSampleRateHz() {
        return this.sampleRateHz;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getChannels() {
        return this.channels;
    }

    public final TtsVoiceConfig copy(String language, String voice, String model, float speakingRate, float pitch, int sampleRateHz, int channels) {
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(voice, "voice");
        Intrinsics.checkNotNullParameter(model, "model");
        return new TtsVoiceConfig(language, voice, model, speakingRate, pitch, sampleRateHz, channels);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TtsVoiceConfig)) {
            return false;
        }
        TtsVoiceConfig ttsVoiceConfig = (TtsVoiceConfig) other;
        return Intrinsics.areEqual(this.language, ttsVoiceConfig.language) && Intrinsics.areEqual(this.voice, ttsVoiceConfig.voice) && Intrinsics.areEqual(this.model, ttsVoiceConfig.model) && Float.compare(this.speakingRate, ttsVoiceConfig.speakingRate) == 0 && Float.compare(this.pitch, ttsVoiceConfig.pitch) == 0 && this.sampleRateHz == ttsVoiceConfig.sampleRateHz && this.channels == ttsVoiceConfig.channels;
    }

    public int hashCode() {
        return (((((((((((this.language.hashCode() * 31) + this.voice.hashCode()) * 31) + this.model.hashCode()) * 31) + Float.hashCode(this.speakingRate)) * 31) + Float.hashCode(this.pitch)) * 31) + Integer.hashCode(this.sampleRateHz)) * 31) + Integer.hashCode(this.channels);
    }

    public String toString() {
        return "TtsVoiceConfig(language=" + this.language + ", voice=" + this.voice + ", model=" + this.model + ", speakingRate=" + this.speakingRate + ", pitch=" + this.pitch + ", sampleRateHz=" + this.sampleRateHz + ", channels=" + this.channels + ")";
    }

    public TtsVoiceConfig(String language, String voice, String model, float speakingRate, float pitch, int sampleRateHz, int channels) {
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(voice, "voice");
        Intrinsics.checkNotNullParameter(model, "model");
        this.language = language;
        this.voice = voice;
        this.model = model;
        this.speakingRate = speakingRate;
        this.pitch = pitch;
        this.sampleRateHz = sampleRateHz;
        this.channels = channels;
    }

    public /* synthetic */ TtsVoiceConfig(String str, String str2, String str3, float f, float f2, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? "ru-RU" : str, (i3 & 2) != 0 ? "default" : str2, (i3 & 4) != 0 ? "" : str3, (i3 & 8) != 0 ? 1.0f : f, (i3 & 16) != 0 ? 1.0f : f2, (i3 & 32) != 0 ? 22050 : i, (i3 & 64) != 0 ? 1 : i2);
    }

    public final String getLanguage() {
        return this.language;
    }

    public final String getVoice() {
        return this.voice;
    }

    public final String getModel() {
        return this.model;
    }

    public final float getSpeakingRate() {
        return this.speakingRate;
    }

    public final float getPitch() {
        return this.pitch;
    }

    public final int getSampleRateHz() {
        return this.sampleRateHz;
    }

    public final int getChannels() {
        return this.channels;
    }
}
