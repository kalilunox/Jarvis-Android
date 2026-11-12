package com.jarvis.app;

import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import com.jarvis.brain.ChatLlmEngine;
import com.jarvis.brain.LlmDecisionKind;
import com.jarvis.brain.LlmDecisionParser;
import com.jarvis.brain.LlmStructuredDecision;
import com.jarvis.brain.aiprovider.ModelNamed;
import com.jarvis.core.llm.AssembledLlmContext;
import com.jarvis.core.llm.LlmContextBudget;
import com.jarvis.core.llm.LlmOutputPolicy;
import com.jarvis.core.storage.JarvisSchema;
import dev.ffmpegkit.llama.Llama;
import dev.ffmpegkit.llama.LlamaModel;
import dev.ffmpegkit.llama.LlamaResult;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequencesKt;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import kotlin.time.DurationKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: LlamaChatLlmEngine.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 ;2\u00020\u00012\u00020\u0002:\u0002:;B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u0012H\u0016J\b\u0010\u001f\u001a\u00020\u0012H\u0016J\u0006\u0010 \u001a\u00020!J\b\u0010\"\u001a\u00020!H\u0016J\u0010\u0010#\u001a\u00020!2\u0006\u0010$\u001a\u00020\u0010H\u0002J:\u0010'\u001a\u0004\u0018\u00010\u00072\u0006\u0010(\u001a\u00020\u00072\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00070*2\u0006\u0010+\u001a\u00020\u00072\u0006\u0010,\u001a\u00020\u00072\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J0\u0010/\u001a\u0004\u0018\u0001002\u0006\u0010(\u001a\u00020\u00072\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00070*2\u0006\u0010+\u001a\u00020\u00072\u0006\u0010,\u001a\u00020\u0007H\u0016J\u0006\u00101\u001a\u00020!J\u0012\u00102\u001a\u0004\u0018\u00010\u00102\u0006\u00103\u001a\u00020\u0012H\u0002J.\u00104\u001a\u00020\u00072\u0006\u0010,\u001a\u00020\u00072\u0006\u0010+\u001a\u00020\u00072\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00070*2\u0006\u0010(\u001a\u00020\u0007H\u0002J\u0010\u00105\u001a\u00020\u00122\u0006\u00106\u001a\u00020\u0007H\u0002J\u0016\u00107\u001a\u00020\u00072\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00070*H\u0002J\u0012\u00108\u001a\u0004\u0018\u00010\u00072\u0006\u00109\u001a\u00020\u0007H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0015\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\fR\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"Lcom/jarvis/app/LlamaChatLlmEngine;", "Lcom/jarvis/brain/ChatLlmEngine;", "Lcom/jarvis/brain/aiprovider/ModelNamed;", "modelFile", "Ljava/io/File;", "profileProvider", "Lkotlin/Function0;", "", "<init>", "(Ljava/io/File;Lkotlin/jvm/functions/Function0;)V", "modelId", "getModelId", "()Ljava/lang/String;", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "model", "Ldev/ffmpegkit/llama/LlamaModel;", "loadFailed", "", "warmed", "loadedProfile", "modelPath", "getModelPath", "threads", "", "nameLower", "isFastTier", "isQwen3", "params", "Lcom/jarvis/app/LlamaChatLlmEngine$ProfileParams;", "isReady", "isLoaded", "preload", "", "warm", "keepHotLocked", "m", "lastKeepHotAt", "", "generate", "userText", "history", "", "memories", "persona", "assembled", "Lcom/jarvis/core/llm/AssembledLlmContext;", "decide", "Lcom/jarvis/brain/LlmStructuredDecision;", "unload", "ensureLoadedLocked", "forceReloadIfProfileChanged", "compactRole", "looksLikeFollowUp", "t", "shortContext", "cleanup", "raw", "ProfileParams", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LlamaChatLlmEngine implements ChatLlmEngine, ModelNamed {
    private static final long KEEP_HOT_MIN_MS = 45000;
    private static final String TAG = "Jarvis.ChatLlm";
    private final boolean isFastTier;
    private final boolean isQwen3;
    private volatile long lastKeepHotAt;
    private volatile boolean loadFailed;
    private volatile String loadedProfile;
    private final ReentrantLock lock;
    private volatile LlamaModel model;
    private final File modelFile;
    private final String nameLower;
    private final Function0<String> profileProvider;
    private final int threads;
    private volatile boolean warmed;

    /* JADX INFO: compiled from: LlamaChatLlmEngine.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LlmContextBudget.values().length];
            try {
                iArr[LlmContextBudget.FAST.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[LlmContextBudget.BALANCED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public LlamaChatLlmEngine(File modelFile, Function0<String> profileProvider) {
        Intrinsics.checkNotNullParameter(modelFile, "modelFile");
        Intrinsics.checkNotNullParameter(profileProvider, "profileProvider");
        this.modelFile = modelFile;
        this.profileProvider = profileProvider;
        this.lock = new ReentrantLock();
        this.threads = Math.min(4, RangesKt.coerceAtLeast(Runtime.getRuntime().availableProcessors(), 2));
        String name = this.modelFile.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        String lowerCase = name.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        this.nameLower = lowerCase;
        this.isFastTier = StringsKt.contains$default((CharSequence) this.nameLower, (CharSequence) "0.6b", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) this.nameLower, (CharSequence) "0.5b", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) this.nameLower, (CharSequence) "0_6b", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) this.nameLower, (CharSequence) "0_5b", false, 2, (Object) null);
        this.isQwen3 = StringsKt.contains$default((CharSequence) this.nameLower, (CharSequence) "qwen3", false, 2, (Object) null);
    }

    public /* synthetic */ LlamaChatLlmEngine(File file, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(file, (i & 2) != 0 ? new Function0() { // from class: com.jarvis.app.LlamaChatLlmEngine$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return LlamaChatLlmEngine._init_$lambda$0();
            }
        } : function0);
    }

    @Override // com.jarvis.brain.ChatLlmEngine
    public /* bridge */ String generateWithContext(String userText, String persona, AssembledLlmContext assembled) {
        return super.generateWithContext(userText, persona, assembled);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String _init_$lambda$0() {
        return "BALANCED";
    }

    @Override // com.jarvis.brain.aiprovider.ModelNamed
    public String getModelId() {
        String name = this.modelFile.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        return name;
    }

    public final String getModelPath() {
        String absolutePath = this.modelFile.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
        return absolutePath;
    }

    /* JADX INFO: compiled from: LlamaChatLlmEngine.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u001e"}, d2 = {"Lcom/jarvis/app/LlamaChatLlmEngine$ProfileParams;", "", "contextSize", "", "maxTokens", "temperature", "", "topP", "topK", "<init>", "(IIFFI)V", "getContextSize", "()I", "getMaxTokens", "getTemperature", "()F", "getTopP", "getTopK", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class ProfileParams {
        private final int contextSize;
        private final int maxTokens;
        private final float temperature;
        private final int topK;
        private final float topP;

        public static /* synthetic */ ProfileParams copy$default(ProfileParams profileParams, int i, int i2, float f, float f2, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                i = profileParams.contextSize;
            }
            if ((i4 & 2) != 0) {
                i2 = profileParams.maxTokens;
            }
            int i5 = i2;
            if ((i4 & 4) != 0) {
                f = profileParams.temperature;
            }
            float f3 = f;
            if ((i4 & 8) != 0) {
                f2 = profileParams.topP;
            }
            float f4 = f2;
            if ((i4 & 16) != 0) {
                i3 = profileParams.topK;
            }
            return profileParams.copy(i, i5, f3, f4, i3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getContextSize() {
            return this.contextSize;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getMaxTokens() {
            return this.maxTokens;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final float getTemperature() {
            return this.temperature;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final float getTopP() {
            return this.topP;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getTopK() {
            return this.topK;
        }

        public final ProfileParams copy(int contextSize, int maxTokens, float temperature, float topP, int topK) {
            return new ProfileParams(contextSize, maxTokens, temperature, topP, topK);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ProfileParams)) {
                return false;
            }
            ProfileParams profileParams = (ProfileParams) other;
            return this.contextSize == profileParams.contextSize && this.maxTokens == profileParams.maxTokens && Float.compare(this.temperature, profileParams.temperature) == 0 && Float.compare(this.topP, profileParams.topP) == 0 && this.topK == profileParams.topK;
        }

        public int hashCode() {
            return (((((((Integer.hashCode(this.contextSize) * 31) + Integer.hashCode(this.maxTokens)) * 31) + Float.hashCode(this.temperature)) * 31) + Float.hashCode(this.topP)) * 31) + Integer.hashCode(this.topK);
        }

        public String toString() {
            return "ProfileParams(contextSize=" + this.contextSize + ", maxTokens=" + this.maxTokens + ", temperature=" + this.temperature + ", topP=" + this.topP + ", topK=" + this.topK + ")";
        }

        public ProfileParams(int contextSize, int maxTokens, float temperature, float topP, int topK) {
            this.contextSize = contextSize;
            this.maxTokens = maxTokens;
            this.temperature = temperature;
            this.topP = topP;
            this.topK = topK;
        }

        public final int getContextSize() {
            return this.contextSize;
        }

        public final int getMaxTokens() {
            return this.maxTokens;
        }

        public final float getTemperature() {
            return this.temperature;
        }

        public final float getTopP() {
            return this.topP;
        }

        public final int getTopK() {
            return this.topK;
        }
    }

    private final ProfileParams params() {
        ProfileParams profileParams;
        String profile = this.profileProvider.invoke().toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(profile, "toUpperCase(...)");
        if (Intrinsics.areEqual(profile, "FAST")) {
            profileParams = new ProfileParams(this.isFastTier ? 160 : 192, this.isFastTier ? 18 : 14, 0.1f, 0.7f, 15);
        } else if (Intrinsics.areEqual(profile, "QUALITY")) {
            profileParams = new ProfileParams(this.isFastTier ? 256 : 320, this.isFastTier ? 36 : 32, 0.25f, 0.85f, 35);
        } else {
            profileParams = new ProfileParams(this.isFastTier ? 176 : 208, this.isFastTier ? 22 : 18, 0.12f, 0.72f, 16);
        }
        return profileParams;
    }

    @Override // com.jarvis.brain.ChatLlmEngine
    public boolean isReady() {
        return this.modelFile.isFile() && !this.loadFailed;
    }

    @Override // com.jarvis.brain.ChatLlmEngine
    public boolean isLoaded() {
        return (this.model == null || this.loadFailed) ? false : true;
    }

    public final void preload() {
        if (this.modelFile.isFile() && !this.loadFailed && this.lock.tryLock(30L, TimeUnit.SECONDS)) {
            try {
                ensureLoadedLocked(true);
                Log.i(TAG, "preloaded in RAM model=" + this.modelFile.getName() + " profile=" + ((Object) this.profileProvider.invoke()));
            } finally {
                this.lock.unlock();
            }
        }
    }

    @Override // com.jarvis.brain.ChatLlmEngine
    public void warm() {
        Object objM392constructorimpl;
        if (!this.modelFile.isFile() || this.loadFailed) {
            return;
        }
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            LlamaModel m = ensureLoadedLocked(true);
            if (m != null) {
                if (!this.warmed) {
                    long t0 = SystemClock.elapsedRealtime();
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        LlamaChatLlmEngine $this$warm_u24lambda_u240_u240 = this;
                        Process.setThreadPriority(-1);
                        BuildersKt__BuildersKt.runBlocking$default(null, new LlamaChatLlmEngine$warm$1$1$1(m, $this$warm_u24lambda_u240_u240, null), 1, null);
                        objM392constructorimpl = Result.m392constructorimpl(Integer.valueOf(Log.i(TAG, "warm-up " + (SystemClock.elapsedRealtime() - t0) + "ms model=" + $this$warm_u24lambda_u240_u240.modelFile.getName() + " profile=" + ((Object) $this$warm_u24lambda_u240_u240.profileProvider.invoke()))));
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
                    }
                    Throwable e = Result.m395exceptionOrNullimpl(objM392constructorimpl);
                    if (e != null) {
                        Log.w(TAG, "warm-up failed: " + e.getMessage());
                    }
                    this.warmed = true;
                    Unit unit = Unit.INSTANCE;
                    reentrantLock.unlock();
                    return;
                }
                keepHotLocked(m);
            }
            reentrantLock.unlock();
        } catch (Throwable th2) {
            reentrantLock.unlock();
            throw th2;
        }
    }

    private final void keepHotLocked(LlamaModel m) {
        long last = this.lastKeepHotAt;
        long now = SystemClock.elapsedRealtime();
        if (now - last < KEEP_HOT_MIN_MS) {
            return;
        }
        this.lastKeepHotAt = now;
        try {
            Result.Companion companion = Result.INSTANCE;
            LlamaChatLlmEngine $this$keepHotLocked_u24lambda_u240 = this;
            BuildersKt__BuildersKt.runBlocking$default(null, new LlamaChatLlmEngine$keepHotLocked$1$1(m, $this$keepHotLocked_u24lambda_u240, null), 1, null);
            Result.m392constructorimpl(Integer.valueOf(Log.d(TAG, "keep-hot ping ok")));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
    }

    /* JADX WARN: Code duplicated, block: B:174:0x00f5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:28:0x008c A[Catch: all -> 0x02d3, TRY_ENTER, TRY_LEAVE, TryCatch #11 {all -> 0x02d3, blocks: (B:21:0x007a, B:28:0x008c), top: B:162:0x007a }] */
    /* JADX WARN: Code duplicated, block: B:38:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:40:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:45:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:55:0x00db  */
    /* JADX WARN: Code duplicated, block: B:63:0x0104  */
    /* JADX WARN: Code duplicated, block: B:72:0x013c  */
    /* JADX WARN: Code duplicated, block: B:77:0x0157  */
    /* JADX WARN: Code duplicated, block: B:93:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:98:0x01b2  */
    @Override // com.jarvis.brain.ChatLlmEngine
    public String generate(String userText, List<String> history, String memories, String persona, AssembledLlmContext assembled) throws Throwable {
        LlmContextBudget budget;
        String strTake;
        List<String> historyLines;
        List<String> list;
        String str;
        String mem;
        String roleHint;
        StringBuilder $this$generate_u24lambda_u241;
        StringBuilder $this$generate_u24lambda_u242;
        String prompt;
        String roleHint2;
        String mem2;
        List<String> list2;
        long tLoad0;
        LlmContextBudget budget2;
        LlamaModel m;
        String prompt2;
        String str2;
        LlmContextBudget budget3;
        String it;
        String strApply;
        String text;
        String strTake2;
        String legacyMemoriesSnippet;
        String systemPrompt;
        int i;
        LlamaChatLlmEngine llamaChatLlmEngine = this;
        Intrinsics.checkNotNullParameter(userText, "userText");
        Intrinsics.checkNotNullParameter(history, "history");
        Intrinsics.checkNotNullParameter(memories, "memories");
        Intrinsics.checkNotNullParameter(persona, "persona");
        if (!llamaChatLlmEngine.modelFile.isFile() || llamaChatLlmEngine.loadFailed) {
            return null;
        }
        String q = StringsKt.take(StringsKt.trim((CharSequence) userText).toString(), 180);
        if (StringsKt.isBlank(q)) {
            return null;
        }
        if (!llamaChatLlmEngine.lock.tryLock(15L, TimeUnit.SECONDS)) {
            Log.w(TAG, "generate skipped: busy");
            return null;
        }
        long tAll = SystemClock.elapsedRealtime();
        try {
            long tLoad1 = SystemClock.elapsedRealtime();
            LlamaModel m2 = llamaChatLlmEngine.ensureLoadedLocked(true);
            if (m2 == null) {
                llamaChatLlmEngine.lock.unlock();
                return null;
            }
            long loadMs = SystemClock.elapsedRealtime() - tLoad1;
            try {
                ProfileParams p = params();
                if (assembled == null) {
                    budget = LlmContextBudget.FAST;
                    if (assembled != null) {
                        strTake = null;
                    } else {
                        strTake = null;
                    }
                    if (strTake == null) {
                        strTake = "";
                    }
                    String system = strTake;
                    if (assembled != null) {
                        historyLines = history;
                    } else {
                        historyLines = history;
                    }
                    list = historyLines;
                    if (assembled != null) {
                        str = memories;
                    } else {
                        str = memories;
                    }
                    mem = str;
                    roleHint = llamaChatLlmEngine.compactRole(persona, mem, list, q);
                    $this$generate_u24lambda_u241 = new StringBuilder();
                    if (llamaChatLlmEngine.isQwen3) {
                        $this$generate_u24lambda_u242 = $this$generate_u24lambda_u241;
                        $this$generate_u24lambda_u242.append("/no_think ");
                    } else {
                        $this$generate_u24lambda_u242 = $this$generate_u24lambda_u241;
                    }
                    $this$generate_u24lambda_u242.append(roleHint);
                    $this$generate_u24lambda_u242.append(q);
                    prompt = $this$generate_u24lambda_u241.toString();
                    Process.setThreadPriority(-19);
                    budget3 = budget;
                    prompt2 = prompt;
                    LlamaResult result = (LlamaResult) BuildersKt__BuildersKt.runBlocking$default(null, new LlamaChatLlmEngine$generate$result$1(m2, prompt, system, p, null), 1, null);
                    it = llamaChatLlmEngine.cleanup(result.getText());
                    if (it != null) {
                        strApply = LlmOutputPolicy.INSTANCE.apply(it, budget3);
                    } else {
                        strApply = null;
                    }
                    text = strApply;
                    roleHint2 = roleHint;
                    long jElapsedRealtime = SystemClock.elapsedRealtime() - tAll;
                    String name = llamaChatLlmEngine.modelFile.getName();
                    String strInvoke = llamaChatLlmEngine.profileProvider.invoke();
                    mem2 = mem;
                    int tokensGenerated = result.getTokensGenerated();
                    list2 = list;
                    m = m2;
                    tLoad0 = tLoad1;
                    String str3 = String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf(result.getTokensPerSecond())}, 1));
                    Intrinsics.checkNotNullExpressionValue(str3, "format(...)");
                    long promptEvalTimeMs = result.getPromptEvalTimeMs();
                    long generateTimeMs = result.getGenerateTimeMs();
                    if (text != null) {
                        strTake2 = StringsKt.take(text, 100);
                    } else {
                        strTake2 = null;
                    }
                    str2 = text;
                    budget2 = budget3;
                    Log.i(TAG, "generate ok total=" + jElapsedRealtime + "ms loadOr0=" + loadMs + "ms model=" + name + " profile=" + ((Object) strInvoke) + " tok=" + tokensGenerated + " tps=" + str3 + " prefill=" + promptEvalTimeMs + "ms gen=" + generateTimeMs + "ms out='" + strTake2 + "'");
                    this.lock.unlock();
                    return str2;
                }
                try {
                    budget = assembled.getOutputBudget();
                    if (budget == null) {
                        budget = LlmContextBudget.FAST;
                    }
                    if (assembled != null || (systemPrompt = assembled.getSystemPrompt()) == null) {
                        strTake = null;
                    } else {
                        switch (WhenMappings.$EnumSwitchMapping$0[budget.ordinal()]) {
                            case 1:
                                i = 720;
                                break;
                            case 2:
                                i = 1200;
                                break;
                            default:
                                i = 1800;
                                break;
                        }
                        strTake = StringsKt.take(systemPrompt, i);
                    }
                    if (strTake == null) {
                        strTake = "";
                    }
                    String system2 = strTake;
                    if (assembled != null || (historyLines = assembled.getHistoryLines()) == null) {
                        historyLines = history;
                    }
                    list = historyLines;
                    if (assembled != null || (legacyMemoriesSnippet = assembled.getLegacyMemoriesSnippet()) == null) {
                        str = memories;
                    } else {
                        String str4 = legacyMemoriesSnippet;
                        if (StringsKt.isBlank(str4)) {
                            str4 = memories;
                        }
                        str = str4;
                    }
                    mem = str;
                    try {
                        roleHint = llamaChatLlmEngine.compactRole(persona, mem, list, q);
                        $this$generate_u24lambda_u241 = new StringBuilder();
                        if (llamaChatLlmEngine.isQwen3) {
                            try {
                                $this$generate_u24lambda_u242 = $this$generate_u24lambda_u241;
                                $this$generate_u24lambda_u242.append("/no_think ");
                            } catch (Throwable th) {
                                th = th;
                            }
                        } else {
                            $this$generate_u24lambda_u242 = $this$generate_u24lambda_u241;
                        }
                        $this$generate_u24lambda_u242.append(roleHint);
                        $this$generate_u24lambda_u242.append(q);
                        prompt = $this$generate_u24lambda_u241.toString();
                        Process.setThreadPriority(-19);
                        try {
                            budget3 = budget;
                            try {
                                prompt2 = prompt;
                                try {
                                    LlamaResult result2 = (LlamaResult) BuildersKt__BuildersKt.runBlocking$default(null, new LlamaChatLlmEngine$generate$result$1(m2, prompt, system2, p, null), 1, null);
                                    it = llamaChatLlmEngine.cleanup(result2.getText());
                                    if (it != null) {
                                        try {
                                            strApply = LlmOutputPolicy.INSTANCE.apply(it, budget3);
                                        } catch (Throwable th2) {
                                            t = th2;
                                            budget2 = budget3;
                                            roleHint2 = roleHint;
                                            mem2 = mem;
                                            list2 = list;
                                            tLoad0 = tLoad1;
                                            m = m2;
                                            try {
                                                Log.e(TAG, "generate failed: " + t.getMessage(), t);
                                                str2 = null;
                                                this.lock.unlock();
                                                return str2;
                                            } catch (Throwable th3) {
                                                th = th3;
                                                llamaChatLlmEngine = this;
                                            }
                                        }
                                    } else {
                                        strApply = null;
                                    }
                                    text = strApply;
                                    roleHint2 = roleHint;
                                    long jElapsedRealtime2 = SystemClock.elapsedRealtime() - tAll;
                                    try {
                                        String name2 = llamaChatLlmEngine.modelFile.getName();
                                        String strInvoke2 = llamaChatLlmEngine.profileProvider.invoke();
                                        mem2 = mem;
                                        try {
                                            int tokensGenerated2 = result2.getTokensGenerated();
                                            list2 = list;
                                            try {
                                                m = m2;
                                                try {
                                                    tLoad0 = tLoad1;
                                                    try {
                                                        String str5 = String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf(result2.getTokensPerSecond())}, 1));
                                                        Intrinsics.checkNotNullExpressionValue(str5, "format(...)");
                                                        long promptEvalTimeMs2 = result2.getPromptEvalTimeMs();
                                                        try {
                                                            long generateTimeMs2 = result2.getGenerateTimeMs();
                                                            if (text != null) {
                                                                try {
                                                                    strTake2 = StringsKt.take(text, 100);
                                                                } catch (Throwable th4) {
                                                                    t = th4;
                                                                    budget2 = budget3;
                                                                    Log.e(TAG, "generate failed: " + t.getMessage(), t);
                                                                    str2 = null;
                                                                    this.lock.unlock();
                                                                    return str2;
                                                                }
                                                            } else {
                                                                strTake2 = null;
                                                            }
                                                            str2 = text;
                                                            budget2 = budget3;
                                                            try {
                                                                Log.i(TAG, "generate ok total=" + jElapsedRealtime2 + "ms loadOr0=" + loadMs + "ms model=" + name2 + " profile=" + ((Object) strInvoke2) + " tok=" + tokensGenerated2 + " tps=" + str5 + " prefill=" + promptEvalTimeMs2 + "ms gen=" + generateTimeMs2 + "ms out='" + strTake2 + "'");
                                                            } catch (Throwable th5) {
                                                                t = th5;
                                                                Log.e(TAG, "generate failed: " + t.getMessage(), t);
                                                                str2 = null;
                                                            }
                                                        } catch (Throwable th6) {
                                                            t = th6;
                                                            budget2 = budget3;
                                                        }
                                                    } catch (Throwable th7) {
                                                        t = th7;
                                                        budget2 = budget3;
                                                    }
                                                } catch (Throwable th8) {
                                                    t = th8;
                                                    budget2 = budget3;
                                                    tLoad0 = tLoad1;
                                                }
                                            } catch (Throwable th9) {
                                                t = th9;
                                                budget2 = budget3;
                                                tLoad0 = tLoad1;
                                                m = m2;
                                            }
                                        } catch (Throwable th10) {
                                            t = th10;
                                            budget2 = budget3;
                                            list2 = list;
                                            tLoad0 = tLoad1;
                                            m = m2;
                                        }
                                    } catch (Throwable th11) {
                                        t = th11;
                                        budget2 = budget3;
                                        list2 = list;
                                        tLoad0 = tLoad1;
                                        m = m2;
                                        mem2 = mem;
                                    }
                                } catch (Throwable th12) {
                                    t = th12;
                                    budget2 = budget3;
                                    roleHint2 = roleHint;
                                    mem2 = mem;
                                    list2 = list;
                                    tLoad0 = tLoad1;
                                    m = m2;
                                }
                            } catch (Throwable th13) {
                                t = th13;
                                budget2 = budget3;
                                roleHint2 = roleHint;
                                mem2 = mem;
                                list2 = list;
                                tLoad0 = tLoad1;
                                m = m2;
                                prompt2 = prompt;
                            }
                        } catch (Throwable th14) {
                            t = th14;
                            roleHint2 = roleHint;
                            mem2 = mem;
                            list2 = list;
                            tLoad0 = tLoad1;
                            budget2 = budget;
                            m = m2;
                            prompt2 = prompt;
                        }
                        this.lock.unlock();
                        return str2;
                    } catch (Throwable th15) {
                        th = th15;
                    }
                } catch (Throwable th16) {
                    th = th16;
                }
            } catch (Throwable th17) {
                th = th17;
            }
        } catch (Throwable th18) {
            th = th18;
        }
        llamaChatLlmEngine.lock.unlock();
        throw th;
    }

    @Override // com.jarvis.brain.ChatLlmEngine
    public LlmStructuredDecision decide(String userText, List<String> history, String memories, String persona) {
        Intrinsics.checkNotNullParameter(userText, "userText");
        Intrinsics.checkNotNullParameter(history, "history");
        Intrinsics.checkNotNullParameter(memories, "memories");
        Intrinsics.checkNotNullParameter(persona, "persona");
        String text = ChatLlmEngine.generate$default(this, userText, history, memories, persona, null, 16, null);
        if (text == null) {
            return null;
        }
        LlmStructuredDecision llmStructuredDecision = LlmDecisionParser.INSTANCE.parse("ANSWER: " + text);
        if (llmStructuredDecision != null) {
            return llmStructuredDecision;
        }
        return new LlmStructuredDecision(LlmDecisionKind.ANSWER, StringsKt.take(text, 360), 0.0f, null, null, text, 28, null);
    }

    public final void unload() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            LlamaModel m = this.model;
            if (m == null) {
                reentrantLock.unlock();
                return;
            }
            try {
                Result.Companion companion = Result.INSTANCE;
                LlamaChatLlmEngine llamaChatLlmEngine = this;
                Llama.INSTANCE.releaseModel(m);
                Result.m392constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m392constructorimpl(ResultKt.createFailure(th));
            }
            this.model = null;
            this.warmed = false;
            this.loadedProfile = null;
            Unit unit = Unit.INSTANCE;
            reentrantLock.unlock();
        } catch (Throwable th2) {
            reentrantLock.unlock();
            throw th2;
        }
    }

    private final LlamaModel ensureLoadedLocked(boolean forceReloadIfProfileChanged) {
        String want = this.profileProvider.invoke().toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(want, "toUpperCase(...)");
        if (this.model != null && forceReloadIfProfileChanged && this.loadedProfile != null && !Intrinsics.areEqual(this.loadedProfile, want)) {
            Log.i(TAG, "profile " + this.loadedProfile + " → " + want + ", reload sampler/ctx");
            try {
                Result.Companion companion = Result.INSTANCE;
                LlamaChatLlmEngine $this$ensureLoadedLocked_u24lambda_u240 = this;
                Llama llama = Llama.INSTANCE;
                LlamaModel llamaModel = $this$ensureLoadedLocked_u24lambda_u240.model;
                Intrinsics.checkNotNull(llamaModel);
                llama.releaseModel(llamaModel);
                Result.m392constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m392constructorimpl(ResultKt.createFailure(th));
            }
            this.model = null;
            this.warmed = false;
            this.loadedProfile = null;
        }
        LlamaModel it = this.model;
        if (it != null) {
            return it;
        }
        if (this.loadFailed || !this.modelFile.isFile()) {
            return null;
        }
        ProfileParams p = params();
        long t0 = SystemClock.elapsedRealtime();
        Log.i(TAG, "loading " + this.modelFile.getName() + " (" + (this.modelFile.length() / ((long) DurationKt.NANOS_IN_MILLIS)) + "MB) threads=" + this.threads + " ctx=" + p.getContextSize() + " temp=" + p.getTemperature() + " profile=" + want + "…");
        try {
            Process.setThreadPriority(-1);
            LlamaModel loaded = (LlamaModel) BuildersKt__BuildersKt.runBlocking$default(null, new LlamaChatLlmEngine$ensureLoadedLocked$loaded$1(this, p, null), 1, null);
            this.model = loaded;
            this.loadedProfile = want;
            Log.i(TAG, "Chat LLM loaded in " + (SystemClock.elapsedRealtime() - t0) + "ms");
            return loaded;
        } catch (Throwable t) {
            this.loadFailed = true;
            Log.e(TAG, "Chat LLM load failed: " + t.getMessage(), t);
            return null;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:17:0x002c  */
    private final String compactRole(String persona, String memories, List<String> history, String userText) {
        String who;
        String mem;
        switch (persona) {
            case "DEVELOPER":
                who = "Developer JARVIS: технично и точно, термины ок, статусы/ошибки если уместны, без сюсюканья.";
                break;
            case "FRIENDLY":
                who = "Дружелюбный JARVIS: тепло, на «ты», живые короткие фразы, лёгкий юмор, без канцелярита.";
                break;
            case "MINIMAL":
                who = "Минимальный JARVIS: максимум одно короткое предложение, только суть, без воды и эмодзи.";
                break;
            default:
                who = "JARVIS Classic: спокойный вежливый дворецкий, чёткие ответы, сдержанный тон.";
                break;
        }
        if (StringsKt.isBlank(memories)) {
            mem = "";
        } else {
            mem = " mem=" + StringsKt.take(memories, 20) + ";";
        }
        boolean follow = looksLikeFollowUp(userText);
        String ctx = follow ? shortContext(history) : "";
        String ctxPart = StringsKt.isBlank(ctx) ? "" : " ctx=" + ctx + ";";
        return "[" + who + mem + ctxPart + " RU] ";
    }

    private final boolean looksLikeFollowUp(String t) {
        String lowerCase = t.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String s = StringsKt.trim((CharSequence) lowerCase).toString();
        if (s.length() > 48) {
            return false;
        }
        return StringsKt.startsWith$default(s, "а ", false, 2, (Object) null) || StringsKt.startsWith$default(s, "ну ", false, 2, (Object) null) || SetsKt.setOf((Object[]) new String[]{"почему", "зачем", "подробнее", "дальше", "ещё", "еще"}).contains(s) || StringsKt.startsWith$default(s, "а почему", false, 2, (Object) null) || StringsKt.startsWith$default(s, "а как", false, 2, (Object) null) || new Regex("\\b(они|их|им|это|него|неё|нее)\\b").containsMatchIn(s);
    }

    private final String shortContext(List<String> history) {
        boolean z;
        Object element$iv;
        String strSubstringAfter$default;
        String string;
        List<String> $this$filter$iv = history;
        Collection destination$iv$iv = new ArrayList();
        Iterator it = $this$filter$iv.iterator();
        while (true) {
            z = false;
            if (!it.hasNext()) {
                break;
            }
            Object element$iv$iv = it.next();
            String it2 = (String) element$iv$iv;
            if (StringsKt.startsWith(it2, "user:", true) || StringsKt.startsWith(it2, "assistant:", true)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List turns = (List) destination$iv$iv;
        String str = (String) CollectionsKt.lastOrNull(turns);
        if (str != null && StringsKt.startsWith(str, "user:", true)) {
            z = true;
        }
        List prior = z ? CollectionsKt.dropLast(turns, 1) : turns;
        List $this$lastOrNull$iv = prior;
        ListIterator iterator$iv = $this$lastOrNull$iv.listIterator($this$lastOrNull$iv.size());
        do {
            if (!iterator$iv.hasPrevious()) {
                element$iv = null;
                break;
            }
            element$iv = iterator$iv.previous();
        } while (!StringsKt.startsWith((String) element$iv, "user:", true));
        String str2 = (String) element$iv;
        String u = (str2 == null || (strSubstringAfter$default = StringsKt.substringAfter$default(str2, ':', (String) null, 2, (Object) null)) == null || (string = StringsKt.trim((CharSequence) strSubstringAfter$default).toString()) == null) ? null : StringsKt.take(string, 36);
        if (u == null) {
            u = "";
        }
        String it3 = !StringsKt.isBlank(u) ? u : null;
        String str3 = it3 != null ? "«" + it3 + "»" : null;
        return str3 == null ? "" : str3;
    }

    private final String cleanup(String raw) {
        String raw2;
        String t = StringsKt.trim((CharSequence) raw).toString();
        String str = null;
        if (StringsKt.isBlank(t)) {
            return null;
        }
        String t2 = new Regex("^(Ответь|Ответ|Сейчас|Кратко)[:\\s]+", RegexOption.IGNORE_CASE).replace(StringsKt.trim((CharSequence) new Regex("\\s+").replace(new Regex("[\\p{So}\\p{Cn}]+").replace(new Regex("[\\u3040-\\u30ff\\u3400-\\u4dbf\\u4e00-\\u9fff\\uf900-\\ufaff]+").replace(new Regex("#{1,6}\\s*").replace(new Regex("\\*+").replace(new Regex("(?i)^(sure[,!]?\\s*|of course[,!]?\\s*|okay[,!]?\\s*|как ии[,\\s]*)").replace(new Regex("(?i)^(ANSWER|QUESTION|CLARIFICATION|REFUSAL|FOLLOW_UP)\\s*[:|\\-–]\\s*").replace(new Regex("<\\|.*?\\|>").replace(new Regex("<\\|im_end\\|>.*", RegexOption.DOT_MATCHES_ALL).replace(StringsKt.trim((CharSequence) SequencesKt.joinToString$default(SequencesKt.takeWhile(SequencesKt.map(StringsKt.lineSequence(StringsKt.trim((CharSequence) new Regex("(?i)/no_think\\s*").replace(new Regex("(?s)<think>.*").replace(new Regex("(?s)<think>.*?</think>").replace(t, StringUtils.SPACE), StringUtils.SPACE), "")).toString()), new Function1() { // from class: com.jarvis.app.LlamaChatLlmEngine$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LlamaChatLlmEngine.cleanup$lambda$0((String) obj);
            }
        }), new Function1() { // from class: com.jarvis.app.LlamaChatLlmEngine$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(LlamaChatLlmEngine.cleanup$lambda$1((String) obj));
            }
        }), StringUtils.SPACE, null, null, 0, null, null, 62, null)).toString(), ""), ""), ""), ""), ""), ""), StringUtils.SPACE), StringUtils.SPACE), StringUtils.SPACE)).toString(), "");
        Iterable $this$filter$iv = new Regex("(?<=[.!?…])\\s+").split(t2, 0);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            if (!StringsKt.isBlank(it)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List parts = (List) destination$iv$iv;
        if (parts.isEmpty()) {
            String strTake = StringsKt.take(t2, 240);
            if (!StringsKt.isBlank(strTake)) {
                str = strTake;
            }
            return str;
        }
        List $this$filter$iv2 = parts;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv2 : $this$filter$iv2) {
            String it2 = (String) element$iv$iv2;
            if (CollectionsKt.contains(SetsKt.setOf((Object[]) new Character[]{'.', '!', '?', Character.valueOf(Typography.ellipsis)}), StringsKt.lastOrNull(it2))) {
                destination$iv$iv2.add(element$iv$iv2);
            }
        }
        List complete = (List) destination$iv$iv2;
        if (!complete.isEmpty()) {
            raw2 = CollectionsKt.joinToString$default(CollectionsKt.take(complete, 2), StringUtils.SPACE, null, null, 0, null, null, 62, null);
        } else {
            String raw3 = StringsKt.trimEnd(CollectionsKt.joinToString$default(CollectionsKt.take(parts, 2), StringUtils.SPACE, null, null, 0, null, null, 62, null), ',', ';', ':', ' ');
            int cut = StringsKt.lastIndexOfAny$default((CharSequence) raw3, new char[]{',', Typography.mdash, Typography.ndash, ';'}, 0, false, 6, (Object) null);
            if (24 <= cut && cut < StringsKt.getLastIndex(raw3)) {
                raw3 = StringsKt.trimEnd(StringsKt.take(raw3, cut), ',', ' ', Typography.mdash, Typography.ndash, ';');
            }
            raw2 = !CollectionsKt.contains(SetsKt.setOf((Object[]) new Character[]{'.', '!', '?', Character.valueOf(Typography.ellipsis)}), StringsKt.lastOrNull(raw3)) ? raw3 + "." : raw3;
        }
        String strTake2 = StringsKt.take(raw2, 260);
        if (StringsKt.isBlank(strTake2)) {
            strTake2 = null;
        }
        return strTake2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String cleanup$lambda$0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return StringsKt.trim((CharSequence) it).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean cleanup$lambda$1(String line) {
        Intrinsics.checkNotNullParameter(line, "line");
        String l = line.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(l, "toLowerCase(...)");
        return (!(l.length() > 0) || StringsKt.startsWith$default(l, JarvisSchema.ROLE_USER, false, 2, (Object) null) || StringsKt.startsWith$default(l, "human", false, 2, (Object) null) || StringsKt.startsWith$default(l, JarvisSchema.ROLE_ASSISTANT, false, 2, (Object) null) || StringsKt.startsWith$default(l, "контекст", false, 2, (Object) null) || StringsKt.startsWith$default(l, "вопрос про", false, 2, (Object) null) || StringsKt.startsWith$default(l, "<|", false, 2, (Object) null) || StringsKt.startsWith$default(l, "system", false, 2, (Object) null) || StringsKt.startsWith$default(l, "as an ai", false, 2, (Object) null) || StringsKt.startsWith$default(l, "sure,", false, 2, (Object) null) || StringsKt.startsWith$default(l, "of course", false, 2, (Object) null)) ? false : true;
    }
}
