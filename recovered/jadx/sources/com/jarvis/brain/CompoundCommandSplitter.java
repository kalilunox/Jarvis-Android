package com.jarvis.brain;

import com.jarvis.app.VoiceService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: CompoundCommandSplitter.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\t2\u0006\u0010\r\u001a\u00020\u0006J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0006H\u0002J\u001e\u0010\u0011\u001a\u00020\u000f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\t2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\t2\u0006\u0010\r\u001a\u00020\u0006H\u0002J\u001e\u0010\u0016\u001a\u00020\u000f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\t2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u000e\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\u0006R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/jarvis/brain/CompoundCommandSplitter;", "", "<init>", "()V", "stepVerbs", "", "", "commandTriggers", "hardSeparators", "", "Lkotlin/text/Regex;", "SELF_DIRECTED", "split", VoiceService.EXTRA_TEXT, "looksLikeCommand", "", "part", "isScreenQuestion", "tokens", "idx", "", "splitOnAndBeforeVerb", "isStepStart", "isCompound", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CompoundCommandSplitter {
    public static final CompoundCommandSplitter INSTANCE = new CompoundCommandSplitter();
    private static final Set<String> stepVerbs = SetsKt.setOf((Object[]) new String[]{"открой", "откройте", "запусти", "закрой", "сверни", "нажми", "тапни", "кликни", "выбери", "найди", "поищи", "напиши", "напечатай", "введи", "отправь", "включи", "выключи", "поставь", "сделай", "пролистай", "прокрути", "свайпни", "свайп", "позвони", "набери", "покажи", "прочитай", "прочти", "опиши", "вернись", "назад", "домой", "подожди", "дождись", "жди"});
    private static final Set<String> commandTriggers = SetsKt.setOf((Object[]) new String[]{"громче", "тише", "стоп", "пауза", "продолжи", "дальше", "далее", "отмена", "отмени", "повтори"});
    private static final List<Regex> hardSeparators = CollectionsKt.listOf((Object[]) new Regex[]{new Regex("\\s*,\\s*и\\s+", RegexOption.IGNORE_CASE), new Regex("\\s+потом\\s+", RegexOption.IGNORE_CASE), new Regex("\\s+затем\\s+", RegexOption.IGNORE_CASE), new Regex("\\s+после\\s+этого\\s+", RegexOption.IGNORE_CASE), new Regex("\\s*,\\s+", RegexOption.IGNORE_CASE)});
    private static final Regex SELF_DIRECTED = new Regex("^(джарвис[,\\s]+)?(сделай сам|сам сделай)", RegexOption.IGNORE_CASE);

    private CompoundCommandSplitter() {
    }

    public final List<String> split(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        String trimmed = StringsKt.trim((CharSequence) text).toString();
        if (StringsKt.isBlank(trimmed)) {
            return CollectionsKt.emptyList();
        }
        if (SELF_DIRECTED.containsMatchIn(trimmed)) {
            return CollectionsKt.listOf(trimmed);
        }
        List<String> parts = CollectionsKt.listOf(trimmed);
        Iterator<Regex> it = hardSeparators.iterator();
        while (true) {
            int i = 10;
            int i2 = 0;
            if (!it.hasNext()) {
                break;
            }
            Regex sep = it.next();
            Iterable<String> $this$flatMap$iv = parts;
            Collection destination$iv$iv = new ArrayList();
            for (String chunk : $this$flatMap$iv) {
                Iterable $this$map$iv = sep.split(chunk, i2);
                Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, i));
                for (Object item$iv$iv : $this$map$iv) {
                    String it2 = (String) item$iv$iv;
                    destination$iv$iv2.add(StringsKt.trim((CharSequence) it2).toString());
                }
                Iterable $this$filter$iv = (List) destination$iv$iv2;
                Collection destination$iv$iv3 = new ArrayList();
                for (Object element$iv$iv : $this$filter$iv) {
                    Iterable $this$filter$iv2 = $this$filter$iv;
                    String it3 = (String) element$iv$iv;
                    if (it3.length() > 0) {
                        destination$iv$iv3.add(element$iv$iv);
                    }
                    $this$filter$iv = $this$filter$iv2;
                }
                Iterable list$iv$iv = (List) destination$iv$iv3;
                CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
                i = 10;
                i2 = 0;
            }
            parts = (List) destination$iv$iv;
        }
        Iterable steps = (List) new ArrayList();
        for (String part : parts) {
            CollectionsKt.addAll((Collection) steps, splitOnAndBeforeVerb(part));
        }
        Iterable $this$map$iv2 = steps;
        Collection destination$iv$iv4 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
        for (Object item$iv$iv2 : $this$map$iv2) {
            String it4 = (String) item$iv$iv2;
            destination$iv$iv4.add(StringsKt.trim((CharSequence) it4).toString());
        }
        Iterable $this$filter$iv3 = (List) destination$iv$iv4;
        Collection destination$iv$iv5 = new ArrayList();
        for (Object element$iv$iv2 : $this$filter$iv3) {
            String it5 = (String) element$iv$iv2;
            if (it5.length() > 0) {
                destination$iv$iv5.add(element$iv$iv2);
            }
        }
        List candidate = (List) destination$iv$iv5;
        int size = candidate.size();
        boolean z = true;
        if (size <= 1) {
            return CollectionsKt.listOf(trimmed);
        }
        List $this$all$iv = candidate;
        if (!($this$all$iv instanceof Collection) || !$this$all$iv.isEmpty()) {
            for (Object element$iv : $this$all$iv) {
                String it6 = (String) element$iv;
                if (!INSTANCE.looksLikeCommand(it6)) {
                    z = false;
                    break;
                }
            }
        }
        return z ? candidate : CollectionsKt.listOf(trimmed);
    }

    private final boolean looksLikeCommand(String part) {
        Iterable $this$filter$iv = new Regex("\\s+").split(StringsKt.trim((CharSequence) part).toString(), 0);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            if (!StringsKt.isBlank(it)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List tokens = (List) destination$iv$iv;
        if (tokens.isEmpty()) {
            return false;
        }
        String lowerCase = tokens.get(0).toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String first = StringsKt.trim(lowerCase, ',', '.', '!', '?', ':', ';');
        if (stepVerbs.contains(first) || commandTriggers.contains(first)) {
            return true;
        }
        return isScreenQuestion(tokens, 0);
    }

    private final boolean isScreenQuestion(List<String> tokens, int idx) {
        if (idx >= tokens.size()) {
            return false;
        }
        String lowerCase = tokens.get(idx).toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String w = StringsKt.trim(lowerCase, ',', '.', '!', '?');
        if (!Intrinsics.areEqual(w, "что")) {
            return false;
        }
        String tail = CollectionsKt.joinToString$default(tokens.subList(idx, Math.min(idx + 4, tokens.size())), StringUtils.SPACE, null, null, 0, null, null, 62, null).toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(tail, "toLowerCase(...)");
        return StringsKt.contains$default((CharSequence) tail, (CharSequence) "на экране", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) tail, (CharSequence) "видно", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) tail, (CharSequence) "на экрану", false, 2, (Object) null);
    }

    private final List<String> splitOnAndBeforeVerb(String text) {
        List<String> listSplit = new Regex("\\s+").split(text, 0);
        if (listSplit.size() < 4) {
            return CollectionsKt.listOf(text);
        }
        List out = new ArrayList();
        int start = 0;
        int i = 1;
        while (true) {
            if (i >= listSplit.size() - 1) {
                break;
            }
            String lowerCase = listSplit.get(i).toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            String tok = StringsKt.trim(lowerCase, ',', '.', '!', '?');
            if (Intrinsics.areEqual(tok, "и") && isStepStart(listSplit, i + 1)) {
                String left = StringsKt.trim((CharSequence) CollectionsKt.joinToString$default(listSplit.subList(start, i), StringUtils.SPACE, null, null, 0, null, null, 62, null)).toString();
                if (left.length() > 0) {
                    out.add(left);
                }
                start = i + 1;
            }
            i++;
        }
        String rest = StringsKt.trim((CharSequence) CollectionsKt.joinToString$default(listSplit.subList(start, listSplit.size()), StringUtils.SPACE, null, null, 0, null, null, 62, null)).toString();
        if (rest.length() > 0) {
            out.add(rest);
        }
        return out.size() <= 1 ? CollectionsKt.listOf(text) : out;
    }

    private final boolean isStepStart(List<String> tokens, int idx) {
        if (idx >= tokens.size()) {
            return false;
        }
        String lowerCase = tokens.get(idx).toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String w = StringsKt.trim(lowerCase, ',', '.', '!', '?');
        if (stepVerbs.contains(w)) {
            return true;
        }
        if (Intrinsics.areEqual(w, "что") && idx + 1 < tokens.size()) {
            String tail = CollectionsKt.joinToString$default(tokens.subList(idx, Math.min(idx + 4, tokens.size())), StringUtils.SPACE, null, null, 0, null, null, 62, null).toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(tail, "toLowerCase(...)");
            if (StringsKt.contains$default((CharSequence) tail, (CharSequence) "на экране", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) tail, (CharSequence) "видно", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) tail, (CharSequence) "на экрану", false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    public final boolean isCompound(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        return split(text).size() > 1;
    }
}
