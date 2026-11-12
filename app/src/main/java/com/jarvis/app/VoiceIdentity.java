package com.jarvis.app;

import android.content.Context;
import android.os.SystemClock;
import com.jarvis.app.ui.JarvisActivity;
import com.jarvis.asr.SpeakerEmbedder;
import com.jarvis.wakeword.VoiceMemory;
import com.jarvis.wakeword.VoicePrint;
import com.jarvis.wakeword.VoiceTrim;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VoiceIdentity.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001MB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010(\u001a\u00020)H\u0002J\u0010\u0010*\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0002J\u0010\u0010+\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0002J\u000e\u0010,\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020)J\u000e\u0010-\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020)J\u000e\u0010.\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020)J\u0012\u0010/\u001a\u0004\u0018\u00010\u001d2\u0006\u0010(\u001a\u00020)H\u0002J\u0012\u00100\u001a\u0004\u0018\u00010\u001d2\u0006\u0010(\u001a\u00020)H\u0002J\u0018\u00101\u001a\u0004\u0018\u0001022\u0006\u0010(\u001a\u00020)2\u0006\u00103\u001a\u000202J\u001c\u00104\u001a\b\u0012\u0004\u0012\u0002020\u00172\u0006\u0010(\u001a\u00020)2\u0006\u00103\u001a\u000202J\"\u00105\u001a\u0002062\u0006\u0010(\u001a\u00020)2\u0012\u00107\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002020\u00170\u0017J\u001c\u00108\u001a\u0002062\u0006\u0010(\u001a\u00020)2\f\u00109\u001a\b\u0012\u0004\u0012\u0002020\u0017J\u000e\u0010:\u001a\u0002062\u0006\u0010(\u001a\u00020)J\u000e\u0010;\u001a\u0002062\u0006\u0010(\u001a\u00020)J\u000e\u0010<\u001a\u00020\t2\u0006\u0010(\u001a\u00020)J\u0010\u0010=\u001a\u00020#2\u0006\u0010(\u001a\u00020)H\u0002J\u0010\u0010>\u001a\u00020#2\u0006\u0010(\u001a\u00020)H\u0002J\u0018\u0010?\u001a\u00020#2\u0006\u0010(\u001a\u00020)2\u0006\u0010@\u001a\u00020\u0005H\u0002J(\u0010A\u001a\u0002062\u0006\u0010(\u001a\u00020)2\u0006\u0010B\u001a\u00020#2\u0006\u0010@\u001a\u00020\u00052\u0006\u0010C\u001a\u000202H\u0002J,\u0010D\u001a\u0004\u0018\u00010E2\u0006\u0010(\u001a\u00020)2\u0006\u00103\u001a\u0002022\b\b\u0002\u0010F\u001a\u00020\u001f2\b\b\u0002\u0010G\u001a\u00020\u001fJ\u000e\u0010H\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020)J*\u0010I\u001a\u0004\u0018\u00010E2\u0006\u0010(\u001a\u00020)2\u0006\u00103\u001a\u0002022\u0006\u0010J\u001a\u00020\u001f2\b\b\u0002\u0010F\u001a\u00020\u001fJ\u001a\u0010K\u001a\u0004\u0018\u0001022\u0006\u0010(\u001a\u00020)2\u0006\u00103\u001a\u000202H\u0002J\u0012\u0010L\u001a\u0004\u0018\u00010\u001b2\u0006\u0010(\u001a\u00020)H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006N"}, d2 = {"Lcom/jarvis/app/VoiceIdentity;", "", "<init>", "()V", "MODEL_ID", "", "PRINT_FILE", "PHRASE_FILE", "MIN_COMMAND_SAMPLES", "", "COMMAND_AFTER_WAKE", "", "COMMAND_AWAITED", "WAKE_OVER_MEDIA", "LEARN_CAPACITY", "LEARN_WORD_MARGIN", "LEARN_COMMAND_SCORE", "STRONG_WAKE_MS", "", "WORD_MEMORY_FILE", "PHRASE_MEMORY_FILE", "PHRASE_MS", "PHRASES", "", "getPHRASES", "()Ljava/util/List;", "embedder", "Lcom/jarvis/asr/SpeakerEmbedder;", "print", "Lcom/jarvis/wakeword/VoicePrint;", "printLoaded", "", "phrases", "phrasesLoaded", "wordMemory", "Lcom/jarvis/wakeword/VoiceMemory;", "phraseMemory", "strongWakeAt", "modelFile", "Ljava/io/File;", "context", "Landroid/content/Context;", "printFile", "phraseFile", "modelReady", "enrolled", "phrasesEnrolled", "currentPhrases", "currentPrint", "embedSample", "", "pcm", "embedPhrase", "savePhrases", "", "recordings", "save", "samples", "forget", "warm", "learnedCount", "words", "commands", "loadMemory", "name", "remember", JarvisActivity.MEMORY, "embedding", "check", "Lcom/jarvis/app/VoiceIdentity$Check;", "clean", "media", "commandLocked", "checkCommand", "awaited", "embed", "loadEmbedder", "Check", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VoiceIdentity {
    private static final float COMMAND_AFTER_WAKE = 0.38f;
    private static final float COMMAND_AWAITED = 0.45f;
    private static final int LEARN_CAPACITY = 30;
    private static final float LEARN_COMMAND_SCORE = 0.45f;
    private static final float LEARN_WORD_MARGIN = 0.12f;
    private static final int MIN_COMMAND_SAMPLES = 2400;
    public static final String MODEL_ID = "speaker_campp";
    private static final String PHRASE_FILE = "voice_phrases.txt";
    private static final String PHRASE_MEMORY_FILE = "voice_learned_phrases.txt";
    public static final int PHRASE_MS = 6000;
    private static final String PRINT_FILE = "voice_print.txt";
    private static final long STRONG_WAKE_MS = 15000;
    private static final float WAKE_OVER_MEDIA = 0.14f;
    private static final String WORD_MEMORY_FILE = "voice_learned_words.txt";
    private static SpeakerEmbedder embedder;
    private static VoiceMemory phraseMemory;
    private static VoicePrint phrases;
    private static boolean phrasesLoaded;
    private static VoicePrint print;
    private static boolean printLoaded;
    private static long strongWakeAt;
    private static VoiceMemory wordMemory;
    public static final VoiceIdentity INSTANCE = new VoiceIdentity();
    private static final List<String> PHRASES = CollectionsKt.listOf((Object[]) new String[]{"Открой тик ток, сделай громче и поставь таймер на десять минут.", "Какая завтра погода, и нужно ли брать с собой зонт?", "Напомни через час позвонить маме и купить продукты домой.", "Включи музыку, выключи фонарик и скажи, сколько сейчас времени.", "Да, всё верно. Нет, отмена. Стоп, хватит. Продолжай, спасибо большое."});

    private VoiceIdentity() {
    }

    public final List<String> getPHRASES() {
        return PHRASES;
    }

    /* JADX INFO: compiled from: VoiceIdentity.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0012\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00032\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u001b"}, d2 = {"Lcom/jarvis/app/VoiceIdentity$Check;", "", "accepted", "", "score", "", "threshold", "seconds", "<init>", "(ZFFF)V", "getAccepted", "()Z", "getScore", "()F", "getThreshold", "getSeconds", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Check {
        private final boolean accepted;
        private final float score;
        private final float seconds;
        private final float threshold;

        public static /* synthetic */ Check copy$default(Check check, boolean z, float f, float f2, float f3, int i, Object obj) {
            if ((i & 1) != 0) {
                z = check.accepted;
            }
            if ((i & 2) != 0) {
                f = check.score;
            }
            if ((i & 4) != 0) {
                f2 = check.threshold;
            }
            if ((i & 8) != 0) {
                f3 = check.seconds;
            }
            return check.copy(z, f, f2, f3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getAccepted() {
            return this.accepted;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final float getScore() {
            return this.score;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final float getThreshold() {
            return this.threshold;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final float getSeconds() {
            return this.seconds;
        }

        public final Check copy(boolean accepted, float score, float threshold, float seconds) {
            return new Check(accepted, score, threshold, seconds);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Check)) {
                return false;
            }
            Check check = (Check) other;
            return this.accepted == check.accepted && Float.compare(this.score, check.score) == 0 && Float.compare(this.threshold, check.threshold) == 0 && Float.compare(this.seconds, check.seconds) == 0;
        }

        public int hashCode() {
            return (((((Boolean.hashCode(this.accepted) * 31) + Float.hashCode(this.score)) * 31) + Float.hashCode(this.threshold)) * 31) + Float.hashCode(this.seconds);
        }

        public String toString() {
            return "Check(accepted=" + this.accepted + ", score=" + this.score + ", threshold=" + this.threshold + ", seconds=" + this.seconds + ")";
        }

        public Check(boolean accepted, float score, float threshold, float seconds) {
            this.accepted = accepted;
            this.score = score;
            this.threshold = threshold;
            this.seconds = seconds;
        }

        public /* synthetic */ Check(boolean z, float f, float f2, float f3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, f, f2, (i & 8) != 0 ? 0.0f : f3);
        }

        public final boolean getAccepted() {
            return this.accepted;
        }

        public final float getScore() {
            return this.score;
        }

        public final float getSeconds() {
            return this.seconds;
        }

        public final float getThreshold() {
            return this.threshold;
        }
    }

    private final File modelFile(Context context) {
        ModelCatalog.Entry entry = ModelCatalog.INSTANCE.byId(MODEL_ID);
        if (entry == null) {
            return null;
        }
        File it = new File(ModelManager.INSTANCE.get(context).modelsRoot(), entry.getRelativePath());
        if (it.isFile()) {
            return it;
        }
        return null;
    }

    private final File printFile(Context context) {
        return new File(context.getFilesDir(), PRINT_FILE);
    }

    private final File phraseFile(Context context) {
        return new File(context.getFilesDir(), PHRASE_FILE);
    }

    public final boolean modelReady(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return modelFile(context) != null;
    }

    public final synchronized boolean enrolled(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return currentPrint(context) != null;
    }

    public final synchronized boolean phrasesEnrolled(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return currentPhrases(context) != null;
    }

    private final synchronized VoicePrint currentPhrases(Context context) {
        Object objM392constructorimpl;
        if (!phrasesLoaded) {
            Object obj = null;
            try {
                Result.Companion companion = Result.INSTANCE;
                VoiceIdentity $this$currentPhrases_u24lambda_u240 = this;
                objM392constructorimpl = Result.m392constructorimpl(VoicePrint.INSTANCE.decode(FilesKt.readText$default($this$currentPhrases_u24lambda_u240.phraseFile(context), null, 1, null)));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
            }
            if (!Result.m398isFailureimpl(objM392constructorimpl)) {
                obj = objM392constructorimpl;
            }
            phrases = (VoicePrint) obj;
            phrasesLoaded = true;
        }
        return phrases;
    }

    private final synchronized VoicePrint currentPrint(Context context) {
        Object objM392constructorimpl;
        if (!printLoaded) {
            Object obj = null;
            try {
                Result.Companion companion = Result.INSTANCE;
                VoiceIdentity $this$currentPrint_u24lambda_u240 = this;
                objM392constructorimpl = Result.m392constructorimpl(VoicePrint.INSTANCE.decode(FilesKt.readText$default($this$currentPrint_u24lambda_u240.printFile(context), null, 1, null)));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
            }
            if (!Result.m398isFailureimpl(objM392constructorimpl)) {
                obj = objM392constructorimpl;
            }
            print = (VoicePrint) obj;
            printLoaded = true;
        }
        return print;
    }

    public final synchronized float[] embedSample(Context context, float[] pcm) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pcm, "pcm");
        return embed(context, pcm);
    }

    public final synchronized List<float[]> embedPhrase(Context context, float[] pcm) {
        Object objM392constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pcm, "pcm");
        SpeakerEmbedder engine = loadEmbedder(context);
        if (engine == null) {
            return CollectionsKt.emptyList();
        }
        Iterable $this$mapNotNull$iv = VoiceTrim.INSTANCE.windows(VoiceTrim.speech$default(VoiceTrim.INSTANCE, pcm, 0, 2, null));
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            float[] piece = (float[]) element$iv$iv$iv;
            VoiceIdentity voiceIdentity = INSTANCE;
            try {
                Result.Companion companion = Result.INSTANCE;
                try {
                    objM392constructorimpl = Result.m392constructorimpl(engine.embed(piece));
                } catch (Throwable th) {
                    th = th;
                    Result.Companion companion2 = Result.INSTANCE;
                    objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
                }
            } catch (Throwable th2) {
                th = th2;
            }
            if (Result.m398isFailureimpl(objM392constructorimpl)) {
                objM392constructorimpl = null;
            }
            float[] fArr = (float[]) objM392constructorimpl;
            if (fArr != null) {
                destination$iv$iv.add(fArr);
            }
        }
        return (List) destination$iv$iv;
    }

    public final synchronized void savePhrases(Context context, List<? extends List<float[]>> recordings) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(recordings, "recordings");
        VoicePrint next = VoicePrint.INSTANCE.enrollGroups(recordings);
        FilesKt.writeText$default(phraseFile(context), next.encode(), null, 2, null);
        phrases = next;
        phrasesLoaded = true;
    }

    public final synchronized void save(Context context, List<float[]> samples) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(samples, "samples");
        VoicePrint next = VoicePrint.INSTANCE.enroll(samples);
        FilesKt.writeText$default(printFile(context), next.encode(), null, 2, null);
        print = next;
        printLoaded = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final synchronized void forget(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        printFile(context).delete();
        phraseFile(context).delete();
        new File(context.getFilesDir(), WORD_MEMORY_FILE).delete();
        new File(context.getFilesDir(), PHRASE_MEMORY_FILE).delete();
        print = null;
        phrases = null;
        printLoaded = true;
        phrasesLoaded = true;
        int i = 2;
        int i2 = 30;
        wordMemory = new VoiceMemory(i2, null, i, 0 == true ? 1 : 0);
        phraseMemory = new VoiceMemory(i2, 0 == true ? 1 : 0, i, 0 == true ? 1 : 0);
        strongWakeAt = 0L;
    }

    public final synchronized void warm(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (JarvisSettings.INSTANCE.voiceLock(context) && currentPrint(context) != null) {
            loadEmbedder(context);
        }
    }

    public final synchronized int learnedCount(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return words(context).getSize() + commands(context).getSize();
    }

    private final VoiceMemory words(Context context) {
        VoiceMemory voiceMemory = wordMemory;
        if (voiceMemory != null) {
            return voiceMemory;
        }
        VoiceMemory it = loadMemory(context, WORD_MEMORY_FILE);
        wordMemory = it;
        return it;
    }

    private final VoiceMemory commands(Context context) {
        VoiceMemory voiceMemory = phraseMemory;
        if (voiceMemory != null) {
            return voiceMemory;
        }
        VoiceMemory it = loadMemory(context, PHRASE_MEMORY_FILE);
        phraseMemory = it;
        return it;
    }

    private final VoiceMemory loadMemory(Context context, String name) {
        Object objM392constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            VoiceIdentity voiceIdentity = this;
            objM392constructorimpl = Result.m392constructorimpl(FilesKt.readText$default(new File(context.getFilesDir(), name), null, 1, null));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = "";
        }
        String text = (String) objM392constructorimpl;
        return VoiceMemory.INSTANCE.decode(text, 30);
    }

    private final void remember(Context context, VoiceMemory memory, String name, float[] embedding) {
        memory.add(embedding);
        try {
            Result.Companion companion = Result.INSTANCE;
            VoiceIdentity voiceIdentity = this;
            FilesKt.writeText$default(new File(context.getFilesDir(), name), memory.encode(), null, 2, null);
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
    }

    public static /* synthetic */ Check check$default(VoiceIdentity voiceIdentity, Context context, float[] fArr, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        return voiceIdentity.check(context, fArr, z, z2);
    }

    public final synchronized Check check(Context context, float[] pcm, boolean clean, boolean media) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pcm, "pcm");
        if (!JarvisSettings.INSTANCE.voiceLock(context)) {
            return null;
        }
        VoicePrint owner = currentPrint(context);
        if (owner == null) {
            return null;
        }
        float[] embedding = embed(context, pcm);
        if (embedding == null) {
            return null;
        }
        float score = owner.with(words(context).getSamples()).score(embedding);
        boolean z = true;
        boolean strong = owner.score(embedding) >= owner.getThreshold() + 0.12f;
        if (strong) {
            strongWakeAt = SystemClock.elapsedRealtime();
        }
        if (strong && clean) {
            remember(context, words(context), WORD_MEMORY_FILE, embedding);
        }
        float threshold = media ? Math.min(WAKE_OVER_MEDIA, owner.getThreshold()) : owner.getThreshold();
        if (score < threshold) {
            z = false;
        }
        return new Check(z, score, threshold, 0.0f, 8, null);
    }

    public final synchronized boolean commandLocked(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (!JarvisSettings.INSTANCE.voiceLock(context) || currentPhrases(context) == null || loadEmbedder(context) == null) ? false : true;
    }

    public static /* synthetic */ Check checkCommand$default(VoiceIdentity voiceIdentity, Context context, float[] fArr, boolean z, boolean z2, int i, Object obj) {
        if ((i & 8) != 0) {
            z2 = false;
        }
        return voiceIdentity.checkCommand(context, fArr, z, z2);
    }

    public final synchronized Check checkCommand(Context context, float[] pcm, boolean awaited, boolean clean) {
        Object maxElem$iv;
        Object objM392constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pcm, "pcm");
        if (!commandLocked(context)) {
            return null;
        }
        VoicePrint owner = currentPhrases(context);
        if (owner == null) {
            return null;
        }
        SpeakerEmbedder engine = loadEmbedder(context);
        if (engine == null) {
            return null;
        }
        float[] speech = VoiceTrim.INSTANCE.speech(pcm, MIN_COMMAND_SAMPLES);
        Iterable $this$mapNotNull$iv = VoiceTrim.INSTANCE.windows(speech);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            float[] piece = (float[]) element$iv$iv$iv;
            VoiceIdentity voiceIdentity = INSTANCE;
            try {
                Result.Companion companion = Result.INSTANCE;
                try {
                    objM392constructorimpl = Result.m392constructorimpl(engine.embed(piece));
                } catch (Throwable th) {
                    th = th;
                    Result.Companion companion2 = Result.INSTANCE;
                    objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
                }
            } catch (Throwable th2) {
                th = th2;
            }
            if (Result.m398isFailureimpl(objM392constructorimpl)) {
                objM392constructorimpl = null;
            }
            float[] fArr = (float[]) objM392constructorimpl;
            if (fArr != null) {
                destination$iv$iv.add(fArr);
            }
        }
        Iterable pieces = (List) destination$iv$iv;
        VoicePrint known = owner.with(commands(context).getSamples());
        Iterable $this$maxByOrNull$iv = pieces;
        Iterator iterator$iv = $this$maxByOrNull$iv.iterator();
        if (iterator$iv.hasNext()) {
            maxElem$iv = iterator$iv.next();
            if (iterator$iv.hasNext()) {
                float[] p0 = (float[]) maxElem$iv;
                float maxValue$iv = known.score(p0);
                do {
                    Object e$iv = iterator$iv.next();
                    float[] p1 = (float[]) e$iv;
                    float v$iv = known.score(p1);
                    if (Float.compare(maxValue$iv, v$iv) < 0) {
                        maxElem$iv = e$iv;
                        maxValue$iv = v$iv;
                    }
                } while (iterator$iv.hasNext());
            }
        } else {
            maxElem$iv = null;
        }
        float[] top = (float[]) maxElem$iv;
        if (top == null) {
            return null;
        }
        float best = known.score(top);
        float threshold = Math.min(owner.getThreshold(), awaited ? 0.45f : 0.38f);
        boolean z = true;
        boolean afterStrongWake = !awaited && SystemClock.elapsedRealtime() - strongWakeAt < 15000;
        if (clean && afterStrongWake && owner.score(top) >= 0.45f) {
            remember(context, commands(context), PHRASE_MEMORY_FILE, top);
        }
        if (best < threshold) {
            z = false;
        }
        return new Check(z, best, threshold, speech.length / 16000.0f);
    }

    private final float[] embed(Context context, float[] pcm) {
        SpeakerEmbedder engine;
        Object objM392constructorimpl;
        float[] speech = VoiceTrim.speech$default(VoiceTrim.INSTANCE, pcm, 0, 2, null);
        if ((speech.length == 0) || (engine = loadEmbedder(context)) == null) {
            return null;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            VoiceIdentity voiceIdentity = this;
            objM392constructorimpl = Result.m392constructorimpl(engine.embed(speech));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        return (float[]) (Result.m398isFailureimpl(objM392constructorimpl) ? null : objM392constructorimpl);
    }

    private final SpeakerEmbedder loadEmbedder(Context context) {
        Object objM392constructorimpl;
        SpeakerEmbedder it = embedder;
        if (it != null) {
            return it;
        }
        File model = modelFile(context);
        if (model == null) {
            return null;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            VoiceIdentity voiceIdentity = this;
            String absolutePath = model.getAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
            objM392constructorimpl = Result.m392constructorimpl(new SpeakerEmbedder(absolutePath));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = null;
        }
        SpeakerEmbedder it2 = (SpeakerEmbedder) objM392constructorimpl;
        if (it2 == null) {
            return null;
        }
        embedder = it2;
        return it2;
    }
}
