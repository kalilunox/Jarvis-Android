package com.jarvis.core.skills;

import com.google.android.gms.actions.SearchIntents;
import com.jarvis.app.VoiceService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: SkillRetriever.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\u0018\u0000 \u00142\u00020\u0001:\u0002\u0013\u0014B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u001a\b\u0002\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\t0\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\t2\u0006\u0010\u000e\u001a\u00020\bJ$\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\t2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/jarvis/core/skills/SkillRetriever;", "", "registry", "Lcom/jarvis/core/skills/SkillManifestRegistry;", "k", "", "normalize", "Lkotlin/Function1;", "", "", "<init>", "(Lcom/jarvis/core/skills/SkillManifestRegistry;ILkotlin/jvm/functions/Function1;)V", "retrieve", "Lcom/jarvis/core/skills/SkillManifest;", SearchIntents.EXTRA_QUERY, "similarity", "", "qWords", "skillWords", "Scored", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SkillRetriever {
    private final int k;
    private final Function1<String, List<String>> normalize;
    private final SkillManifestRegistry registry;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Set<String> COMMAND_STOPWORDS = SetsKt.setOf((Object[]) new String[]{"включи", "включить", "включите", "выключи", "выключить", "выключите", "открой", "открыть", "закрой", "закрыть", "запусти", "запустить", "сделай", "сделайте", "поставь", "найди", "покажи", "пожалуйста"});

    /* JADX WARN: Multi-variable type inference failed */
    public SkillRetriever(SkillManifestRegistry registry, int k, Function1<? super String, ? extends List<String>> normalize) {
        Intrinsics.checkNotNullParameter(registry, "registry");
        Intrinsics.checkNotNullParameter(normalize, "normalize");
        this.registry = registry;
        this.k = k;
        this.normalize = normalize;
    }

    public /* synthetic */ SkillRetriever(SkillManifestRegistry skillManifestRegistry, int i, AnonymousClass1 anonymousClass1, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(skillManifestRegistry, (i2 & 2) != 0 ? 5 : i, (i2 & 4) != 0 ? new AnonymousClass1(INSTANCE) : anonymousClass1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: com.jarvis.core.skills.SkillRetriever$1, reason: invalid class name */
    /* JADX INFO: compiled from: SkillRetriever.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<String, List<? extends String>> {
        AnonymousClass1(Object obj) {
            super(1, obj, Companion.class, "defaultNormalize", "defaultNormalize(Ljava/lang/String;)Ljava/util/List;", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final List<String> invoke(String p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return ((Companion) this.receiver).defaultNormalize(p0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: SkillRetriever.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/jarvis/core/skills/SkillRetriever$Scored;", "", "manifest", "Lcom/jarvis/core/skills/SkillManifest;", "score", "", "<init>", "(Lcom/jarvis/core/skills/SkillManifest;F)V", "getManifest", "()Lcom/jarvis/core/skills/SkillManifest;", "getScore", "()F", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    static final /* data */ class Scored {
        private final SkillManifest manifest;
        private final float score;

        public static /* synthetic */ Scored copy$default(Scored scored, SkillManifest skillManifest, float f, int i, Object obj) {
            if ((i & 1) != 0) {
                skillManifest = scored.manifest;
            }
            if ((i & 2) != 0) {
                f = scored.score;
            }
            return scored.copy(skillManifest, f);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final SkillManifest getManifest() {
            return this.manifest;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final float getScore() {
            return this.score;
        }

        public final Scored copy(SkillManifest manifest, float score) {
            Intrinsics.checkNotNullParameter(manifest, "manifest");
            return new Scored(manifest, score);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Scored)) {
                return false;
            }
            Scored scored = (Scored) other;
            return Intrinsics.areEqual(this.manifest, scored.manifest) && Float.compare(this.score, scored.score) == 0;
        }

        public int hashCode() {
            return (this.manifest.hashCode() * 31) + Float.hashCode(this.score);
        }

        public String toString() {
            return "Scored(manifest=" + this.manifest + ", score=" + this.score + ")";
        }

        public Scored(SkillManifest manifest, float score) {
            Intrinsics.checkNotNullParameter(manifest, "manifest");
            this.manifest = manifest;
            this.score = score;
        }

        public final SkillManifest getManifest() {
            return this.manifest;
        }

        public final float getScore() {
            return this.score;
        }
    }

    public final List<SkillManifest> retrieve(String query) {
        Object objM392constructorimpl;
        Intrinsics.checkNotNullParameter(query, "query");
        try {
            Result.Companion companion = Result.INSTANCE;
            SkillRetriever $this$retrieve_u24lambda_u240 = this;
            List<String> listInvoke = $this$retrieve_u24lambda_u240.normalize.invoke(query);
            if (listInvoke.isEmpty()) {
                return CollectionsKt.emptyList();
            }
            Iterable $this$map$iv = $this$retrieve_u24lambda_u240.registry.all();
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                SkillManifest m = (SkillManifest) item$iv$iv;
                destination$iv$iv.add(new Scored(m, $this$retrieve_u24lambda_u240.similarity(listInvoke, $this$retrieve_u24lambda_u240.normalize.invoke(m.getSearchableText()))));
            }
            Iterable scored = (List) destination$iv$iv;
            Iterable $this$filter$iv = scored;
            Collection destination$iv$iv2 = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                Scored it = (Scored) element$iv$iv;
                if (it.getScore() > 0.0f) {
                    destination$iv$iv2.add(element$iv$iv);
                }
            }
            Iterable $this$sortedByDescending$iv = (List) destination$iv$iv2;
            Iterable $this$map$iv2 = CollectionsKt.take(CollectionsKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: com.jarvis.core.skills.SkillRetriever$retrieve$lambda$0$$inlined$sortedByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    SkillRetriever.Scored it2 = (SkillRetriever.Scored) t2;
                    SkillRetriever.Scored it3 = (SkillRetriever.Scored) t;
                    return ComparisonsKt.compareValues(Float.valueOf(it2.getScore()), Float.valueOf(it3.getScore()));
                }
            }), $this$retrieve_u24lambda_u240.k);
            Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
            for (Object item$iv$iv2 : $this$map$iv2) {
                Scored it2 = (Scored) item$iv$iv2;
                destination$iv$iv3.add(it2.getManifest());
            }
            objM392constructorimpl = Result.m392constructorimpl((List) destination$iv$iv3);
            List listEmptyList = CollectionsKt.emptyList();
            if (Result.m398isFailureimpl(objM392constructorimpl)) {
                objM392constructorimpl = listEmptyList;
            }
            return (List) objM392constructorimpl;
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
    }

    private final float similarity(List<String> qWords, List<String> skillWords) {
        if (skillWords.isEmpty()) {
            return 0.0f;
        }
        List<String> $this$filterNot$iv = qWords;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterNot$iv) {
            String it = (String) element$iv$iv;
            if (!COMMAND_STOPWORDS.contains(it)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List<String> contentWords = (List) destination$iv$iv;
        if (contentWords.isEmpty()) {
            return 0.0f;
        }
        Set skillSet = CollectionsKt.toSet(skillWords);
        int hits = 0;
        for (String w : contentWords) {
            if (skillSet.contains(w)) {
                hits++;
            }
        }
        return hits / contentWords.size();
    }

    /* JADX INFO: compiled from: SkillRetriever.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b2\u0006\u0010\t\u001a\u00020\u0006R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/jarvis/core/skills/SkillRetriever$Companion;", "", "<init>", "()V", "COMMAND_STOPWORDS", "", "", "defaultNormalize", "", VoiceService.EXTRA_TEXT, "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<String> defaultNormalize(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            String lowerCase = text.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            Iterable $this$filter$iv = new Regex("\\s+").split(new Regex("[^\\p{L}\\p{N} ]").replace(lowerCase, StringUtils.SPACE), 0);
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                String it = (String) element$iv$iv;
                if (it.length() > 2 && !StringsKt.isBlank(it)) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            return (List) destination$iv$iv;
        }
    }
}
