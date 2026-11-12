package com.jarvis.app;

import com.google.android.gms.common.internal.ImagesContract;
import com.jarvis.asr.SherpaAsrSession;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.codec.language.bm.Rule;

/* JADX INFO: compiled from: ModelCatalog.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003\u0010\u0011\u0012B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\t\u001a\u0004\u0018\u00010\u00062\u0006\u0010\n\u001a\u00020\u000bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\bR\u0016\u0010\u000e\u001a\u00020\u000b8\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u000f\u0010\u0003¨\u0006\u0013"}, d2 = {"Lcom/jarvis/app/ModelCatalog;", "", "<init>", "()V", Rule.ALL, "", "Lcom/jarvis/app/ModelCatalog$Entry;", "getALL", "()Ljava/util/List;", "byId", "id", "", "CHAT_MODEL_CANDIDATES", "getCHAT_MODEL_CANDIDATES", "CHAT_MODEL_REL_PATH", "getCHAT_MODEL_REL_PATH$annotations", "Kind", "PackageKind", "Entry", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ModelCatalog {
    public static final ModelCatalog INSTANCE = new ModelCatalog();
    public static final String CHAT_MODEL_REL_PATH = "llm/chat/Qwen3-0.6B-Q4_K_M.gguf";
    private static final List<Entry> ALL = CollectionsKt.listOf((Object[]) new Entry[]{new Entry("chat_qwen3_06", "Диалог · Qwen3 0.6B (быстрый, рекомендуется)", "Новое поколение Qwen: ~4× быстрее 1.5B на Helio G99, ~397 MB GGUF Q4_K_M. Для голоса.", Kind.CHAT_LLM, PackageKind.SINGLE_FILE, "https://huggingface.co/unsloth/Qwen3-0.6B-GGUF/resolve/main/Qwen3-0.6B-Q4_K_M.gguf", CHAT_MODEL_REL_PATH, null, CHAT_MODEL_REL_PATH, 396705472, 0, 1152, null), new Entry("chat_qwen05", "Диалог · Qwen 2.5 0.5B (лёгкий)", "Запасной быстрый вариант (~398 MB). Чуть слабее Qwen3 0.6B.", Kind.CHAT_LLM, PackageKind.SINGLE_FILE, "https://huggingface.co/Qwen/Qwen2.5-0.5B-Instruct-GGUF/resolve/main/qwen2.5-0.5b-instruct-q4_k_m.gguf", "llm/chat/qwen2.5-0.5b-instruct-q4_k_m.gguf", null, "llm/chat/qwen2.5-0.5b-instruct-q4_k_m.gguf", 397808864, 0, 1152, null), new Entry("chat_qwen15", "Диалог · Qwen 2.5 1.5B (качество)", "Лучшее качество, но медленно на среднем CPU (~1.1 GB, 15–30 с на фразу).", Kind.CHAT_LLM, PackageKind.SINGLE_FILE, "https://huggingface.co/Qwen/Qwen2.5-1.5B-Instruct-GGUF/resolve/main/qwen2.5-1.5b-instruct-q4_k_m.gguf", "llm/chat/qwen2.5-1.5b-instruct-q4_k_m.gguf", null, "llm/chat/qwen2.5-1.5b-instruct-q4_k_m.gguf", 1117320736, 0, 1152, null), new Entry("tool_functiongemma", "Команды · FunctionGemma", "Tool-calling LiteRT (~280 MB)", Kind.TOOL_LLM, PackageKind.SINGLE_FILE, "https://huggingface.co/litert-community/functiongemma-mobile-actions_q8_ekv1024.litertlm/resolve/main/mobile-actions_q8_ekv1024.litertlm", "llm/brain.litertlm", null, "llm/brain.litertlm", 300000000, 200000000, 128, null), new Entry("tts_dmitri", "TTS · Piper Dmitri", "Русский офлайн-голос (~63 MB archive)", Kind.TTS, PackageKind.TAR_BZ2, "https://github.com/k2-fsa/sherpa-onnx/releases/download/tts-models/vits-piper-ru_RU-dmitri-medium.tar.bz2", "vits-piper-ru_RU-dmitri-medium.tar.bz2", JarvisSpeaker.TTS_MODEL_DIR, "vits-piper-ru_RU-dmitri-medium/ru_RU-dmitri-medium.onnx", 63000000, 40000000), new Entry("asr_ru", "ASR · Sherpa RU", "Распознавание речи RU (~45 MB archive)", Kind.ASR, PackageKind.TAR_BZ2, "https://github.com/k2-fsa/sherpa-onnx/releases/download/asr-models/sherpa-onnx-streaming-zipformer-small-ru-vosk-int8-2025-08-16.tar.bz2", "sherpa-onnx-streaming-zipformer-small-ru-vosk-int8-2025-08-16.tar.bz2", SherpaAsrSession.MODEL_DIR, "sherpa-onnx-streaming-zipformer-small-ru-vosk-int8-2025-08-16/encoder.int8.onnx", 45000000, 25000000), new Entry(VoiceIdentity.MODEL_ID, "Узнавание голоса · CAM++", "Для режима «Только мой голос»", Kind.SPEAKER, PackageKind.SINGLE_FILE, "https://github.com/k2-fsa/sherpa-onnx/releases/download/speaker-recongition-models/3dspeaker_speech_campplus_sv_zh_en_16k-common_advanced.onnx", "speaker/campplus_zh_en.onnx", null, "speaker/campplus_zh_en.onnx", 28281164, 0, 1152, null)});
    private static final List<String> CHAT_MODEL_CANDIDATES = CollectionsKt.listOf((Object[]) new String[]{CHAT_MODEL_REL_PATH, "llm/chat/qwen2.5-0.5b-instruct-q4_k_m.gguf", "llm/chat/qwen2.5-1.5b-instruct-q4_k_m.gguf"});

    /* JADX INFO: compiled from: ModelCatalog.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/jarvis/app/ModelCatalog$Kind;", "", "<init>", "(Ljava/lang/String;I)V", "CHAT_LLM", "TOOL_LLM", "TTS", "ASR", "SPEAKER", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum Kind {
        CHAT_LLM,
        TOOL_LLM,
        TTS,
        ASR,
        SPEAKER;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<Kind> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: compiled from: ModelCatalog.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/jarvis/app/ModelCatalog$PackageKind;", "", "<init>", "(Ljava/lang/String;I)V", "SINGLE_FILE", "TAR_BZ2", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum PackageKind {
        SINGLE_FILE,
        TAR_BZ2;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<PackageKind> getEntries() {
            return $ENTRIES;
        }
    }

    @Deprecated(message = "Use CHAT_MODEL_CANDIDATES", replaceWith = @ReplaceWith(expression = "CHAT_MODEL_CANDIDATES.first()", imports = {}))
    public static /* synthetic */ void getCHAT_MODEL_REL_PATH$annotations() {
    }

    private ModelCatalog() {
    }

    /* JADX INFO: compiled from: ModelCatalog.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bc\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0007HÆ\u0003J\t\u0010&\u001a\u00020\tHÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u000fHÆ\u0003J\t\u0010,\u001a\u00020\u000fHÆ\u0003Jw\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000fHÆ\u0001J\u0013\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00101\u001a\u000202HÖ\u0001J\t\u00103\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0014R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0014R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0010\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b!\u0010 ¨\u00064"}, d2 = {"Lcom/jarvis/app/ModelCatalog$Entry;", "", "id", "", "title", "subtitle", "kind", "Lcom/jarvis/app/ModelCatalog$Kind;", "packageKind", "Lcom/jarvis/app/ModelCatalog$PackageKind;", ImagesContract.URL, "relativePath", "relativeDir", "readyMarker", "sizeBytes", "", "expectedMinBytes", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/app/ModelCatalog$Kind;Lcom/jarvis/app/ModelCatalog$PackageKind;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJ)V", "getId", "()Ljava/lang/String;", "getTitle", "getSubtitle", "getKind", "()Lcom/jarvis/app/ModelCatalog$Kind;", "getPackageKind", "()Lcom/jarvis/app/ModelCatalog$PackageKind;", "getUrl", "getRelativePath", "getRelativeDir", "getReadyMarker", "getSizeBytes", "()J", "getExpectedMinBytes", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Entry {
        private final long expectedMinBytes;
        private final String id;
        private final Kind kind;
        private final PackageKind packageKind;
        private final String readyMarker;
        private final String relativeDir;
        private final String relativePath;
        private final long sizeBytes;
        private final String subtitle;
        private final String title;
        private final String url;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final long getSizeBytes() {
            return this.sizeBytes;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final long getExpectedMinBytes() {
            return this.expectedMinBytes;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getSubtitle() {
            return this.subtitle;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Kind getKind() {
            return this.kind;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final PackageKind getPackageKind() {
            return this.packageKind;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getRelativePath() {
            return this.relativePath;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getRelativeDir() {
            return this.relativeDir;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getReadyMarker() {
            return this.readyMarker;
        }

        public final Entry copy(String id, String title, String subtitle, Kind kind, PackageKind packageKind, String url, String relativePath, String relativeDir, String readyMarker, long sizeBytes, long expectedMinBytes) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(subtitle, "subtitle");
            Intrinsics.checkNotNullParameter(kind, "kind");
            Intrinsics.checkNotNullParameter(packageKind, "packageKind");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(relativePath, "relativePath");
            Intrinsics.checkNotNullParameter(relativeDir, "relativeDir");
            Intrinsics.checkNotNullParameter(readyMarker, "readyMarker");
            return new Entry(id, title, subtitle, kind, packageKind, url, relativePath, relativeDir, readyMarker, sizeBytes, expectedMinBytes);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) other;
            return Intrinsics.areEqual(this.id, entry.id) && Intrinsics.areEqual(this.title, entry.title) && Intrinsics.areEqual(this.subtitle, entry.subtitle) && this.kind == entry.kind && this.packageKind == entry.packageKind && Intrinsics.areEqual(this.url, entry.url) && Intrinsics.areEqual(this.relativePath, entry.relativePath) && Intrinsics.areEqual(this.relativeDir, entry.relativeDir) && Intrinsics.areEqual(this.readyMarker, entry.readyMarker) && this.sizeBytes == entry.sizeBytes && this.expectedMinBytes == entry.expectedMinBytes;
        }

        public int hashCode() {
            return (((((((((((((((((((this.id.hashCode() * 31) + this.title.hashCode()) * 31) + this.subtitle.hashCode()) * 31) + this.kind.hashCode()) * 31) + this.packageKind.hashCode()) * 31) + this.url.hashCode()) * 31) + this.relativePath.hashCode()) * 31) + this.relativeDir.hashCode()) * 31) + this.readyMarker.hashCode()) * 31) + Long.hashCode(this.sizeBytes)) * 31) + Long.hashCode(this.expectedMinBytes);
        }

        public String toString() {
            return "Entry(id=" + this.id + ", title=" + this.title + ", subtitle=" + this.subtitle + ", kind=" + this.kind + ", packageKind=" + this.packageKind + ", url=" + this.url + ", relativePath=" + this.relativePath + ", relativeDir=" + this.relativeDir + ", readyMarker=" + this.readyMarker + ", sizeBytes=" + this.sizeBytes + ", expectedMinBytes=" + this.expectedMinBytes + ")";
        }

        public Entry(String id, String title, String subtitle, Kind kind, PackageKind packageKind, String url, String relativePath, String relativeDir, String readyMarker, long sizeBytes, long expectedMinBytes) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(subtitle, "subtitle");
            Intrinsics.checkNotNullParameter(kind, "kind");
            Intrinsics.checkNotNullParameter(packageKind, "packageKind");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(relativePath, "relativePath");
            Intrinsics.checkNotNullParameter(relativeDir, "relativeDir");
            Intrinsics.checkNotNullParameter(readyMarker, "readyMarker");
            this.id = id;
            this.title = title;
            this.subtitle = subtitle;
            this.kind = kind;
            this.packageKind = packageKind;
            this.url = url;
            this.relativePath = relativePath;
            this.relativeDir = relativeDir;
            this.readyMarker = readyMarker;
            this.sizeBytes = sizeBytes;
            this.expectedMinBytes = expectedMinBytes;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public /* synthetic */ Entry(String str, String str2, String str3, Kind kind, PackageKind packageKind, String str4, String str5, String str6, String str7, long j, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            String str8;
            if ((i & 128) == 0) {
                str8 = str6;
            } else {
                str8 = "";
            }
            this(str, str2, str3, kind, packageKind, str4, str5, str8, str7, j, (i & 1024) != 0 ? (long) (j * 0.85d) : j2);
        }

        public final String getId() {
            return this.id;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getSubtitle() {
            return this.subtitle;
        }

        public final Kind getKind() {
            return this.kind;
        }

        public final PackageKind getPackageKind() {
            return this.packageKind;
        }

        public final String getUrl() {
            return this.url;
        }

        public final String getRelativePath() {
            return this.relativePath;
        }

        public final String getRelativeDir() {
            return this.relativeDir;
        }

        public final String getReadyMarker() {
            return this.readyMarker;
        }

        public final long getSizeBytes() {
            return this.sizeBytes;
        }

        public final long getExpectedMinBytes() {
            return this.expectedMinBytes;
        }
    }

    public final List<Entry> getALL() {
        return ALL;
    }

    public final Entry byId(String id) {
        Object next;
        Intrinsics.checkNotNullParameter(id, "id");
        Iterator<T> it = ALL.iterator();
        while (it.hasNext()) {
            next = it.next();
            Entry it2 = (Entry) next;
            if (Intrinsics.areEqual(it2.getId(), id)) {
                return (Entry) next;
            }
        }
        next = null;
        return (Entry) next;
    }

    public final List<String> getCHAT_MODEL_CANDIDATES() {
        return CHAT_MODEL_CANDIDATES;
    }
}
