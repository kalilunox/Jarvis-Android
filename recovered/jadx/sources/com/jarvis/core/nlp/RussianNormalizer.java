package com.jarvis.core.nlp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: RussianNormalizer.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tJ\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\f2\u0006\u0010\n\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/jarvis/core/nlp/RussianNormalizer;", "", "<init>", "()V", "punctuation", "Lkotlin/text/Regex;", "nonAlpha", "whitespace", "normalize", "", "raw", "normalizeWords", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RussianNormalizer {
    public static final RussianNormalizer INSTANCE = new RussianNormalizer();
    private static final Regex punctuation = new Regex("[«»\"'….,!?]");
    private static final Regex nonAlpha = new Regex("[^\\p{L}\\p{N}\\s-]");
    private static final Regex whitespace = new Regex("\\s+");

    private RussianNormalizer() {
    }

    public final String normalize(String raw) {
        Intrinsics.checkNotNullParameter(raw, "raw");
        String lowerCase = raw.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return whitespace.replace(StringsKt.trim((CharSequence) nonAlpha.replace(punctuation.replace(lowerCase, ""), StringUtils.SPACE)).toString(), StringUtils.SPACE);
    }

    public final List<String> normalizeWords(String raw) {
        Intrinsics.checkNotNullParameter(raw, "raw");
        Iterable $this$filter$iv = StringsKt.split$default((CharSequence) normalize(raw), new char[]{' '}, false, 0, 6, (Object) null);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            if (!StringsKt.isBlank(it)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }
}
