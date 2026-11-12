package com.jarvis.core.llm;

import com.google.android.gms.actions.SearchIntents;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: CapabilityContextSelector.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bJ*\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/llm/CapabilityContextSelector;", "", "<init>", "()V", "capabilityMarkers", "", "", "needsCapabilities", "", SearchIntents.EXTRA_QUERY, "kind", "Lcom/jarvis/core/llm/LlmContextKind;", "formatLines", "lines", "maxLines", "", "maxChars", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CapabilityContextSelector {
    public static final CapabilityContextSelector INSTANCE = new CapabilityContextSelector();
    private static final List<String> capabilityMarkers = CollectionsKt.listOf((Object[]) new String[]{"открой", "запусти", "включи", "выключи", "найди", "браузер", "telegram", "фонарик", "громк", "погод", "таймер", "позвони", "отправ", "сделай", "помоги", "план", "шаг"});

    private CapabilityContextSelector() {
    }

    public final boolean needsCapabilities(String query, LlmContextKind kind) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(kind, "kind");
        if (kind == LlmContextKind.PLAN) {
            return true;
        }
        String q = query.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(q, "toLowerCase(...)");
        Iterable $this$any$iv = capabilityMarkers;
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            String it = (String) element$iv;
            if (StringsKt.contains$default((CharSequence) q, (CharSequence) it, false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    public final List<String> formatLines(List<String> lines, int maxLines, int maxChars) {
        Intrinsics.checkNotNullParameter(lines, "lines");
        Iterable $this$map$iv = CollectionsKt.take(lines, maxLines);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            destination$iv$iv.add(StringsKt.take(it, 120));
        }
        Iterable $this$filter$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it2 = (String) element$iv$iv;
            if (!StringsKt.isBlank(it2)) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        List<String> list = (List) destination$iv$iv2;
        int total = 0;
        List out = new ArrayList();
        for (String l : list) {
            if (l.length() + total > maxChars) {
                break;
            }
            out.add(l);
            total += l.length();
        }
        return out;
    }
}
