package com.jarvis.core.cloud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ThoughtStripper.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006J\u0006\u0010\u000f\u001a\u00020\u0006J\u001e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00062\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00060\tj\u0002`\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/jarvis/core/cloud/ThoughtStripper;", "", "<init>", "()V", "openTags", "", "", "closeTags", "buffer", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "inThought", "", "strip", "chunk", "flush", "longestSuffixPrefixOfTags", "", "s", "tags", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ThoughtStripper {
    private boolean inThought;
    private final List<String> openTags = CollectionsKt.listOf((Object[]) new String[]{"<think>", "<thought>"});
    private final List<String> closeTags = CollectionsKt.listOf((Object[]) new String[]{"</think>", "</thought>"});
    private StringBuilder buffer = new StringBuilder();

    public final String strip(String chunk) {
        Object element$iv;
        String it;
        StringBuilder sb;
        Object element$iv2;
        String it2;
        Intrinsics.checkNotNullParameter(chunk, "chunk");
        this.buffer.append(chunk);
        StringBuilder out = new StringBuilder();
        int i = 0;
        while (i < this.buffer.length()) {
            if (this.inThought) {
                Iterable $this$map$iv = this.closeTags;
                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    String t = (String) item$iv$iv;
                    destination$iv$iv.add(Integer.valueOf(this.buffer.indexOf(t, i)));
                }
                Iterable $this$filter$iv = (List) destination$iv$iv;
                Collection destination$iv$iv2 = new ArrayList();
                for (Object element$iv$iv : $this$filter$iv) {
                    int it3 = ((Number) element$iv$iv).intValue();
                    int it4 = it3 >= 0 ? 1 : 0;
                    if (it4 != 0) {
                        destination$iv$iv2.add(element$iv$iv);
                    }
                }
                Integer closeIdx = (Integer) CollectionsKt.minOrNull(destination$iv$iv2);
                if (closeIdx != null && closeIdx.intValue() == i) {
                    Iterable $this$first$iv = this.closeTags;
                    Iterator it5 = $this$first$iv.iterator();
                    do {
                        if (!it5.hasNext()) {
                            throw new NoSuchElementException("Collection contains no element matching the predicate.");
                        }
                        element$iv2 = it5.next();
                        it2 = (String) element$iv2;
                    } while (!StringsKt.startsWith$default((CharSequence) this.buffer, (CharSequence) it2, i, false, 4, (Object) null));
                    String tag = (String) element$iv2;
                    this.inThought = false;
                    i += tag.length();
                } else {
                    if (closeIdx == null) {
                        String tail = this.buffer.substring(i);
                        Intrinsics.checkNotNull(tail);
                        int keep = longestSuffixPrefixOfTags(tail, this.closeTags);
                        if (keep > 0) {
                            String strSubstring = tail.substring(tail.length() - keep);
                            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                            sb = new StringBuilder(strSubstring);
                        } else {
                            sb = new StringBuilder();
                        }
                        this.buffer = sb;
                        String string = out.toString();
                        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                        return string;
                    }
                    i = closeIdx.intValue();
                }
            } else {
                Iterable $this$map$iv2 = this.openTags;
                Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
                for (Object item$iv$iv2 : $this$map$iv2) {
                    String t2 = (String) item$iv$iv2;
                    destination$iv$iv3.add(Integer.valueOf(this.buffer.indexOf(t2, i)));
                }
                Iterable $this$filter$iv2 = (List) destination$iv$iv3;
                Collection destination$iv$iv4 = new ArrayList();
                for (Object element$iv$iv2 : $this$filter$iv2) {
                    int it6 = ((Number) element$iv$iv2).intValue();
                    int it7 = it6 >= 0 ? 1 : 0;
                    if (it7 != 0) {
                        destination$iv$iv4.add(element$iv$iv2);
                    }
                }
                Integer openIdx = (Integer) CollectionsKt.minOrNull(destination$iv$iv4);
                if (openIdx != null && openIdx.intValue() == i) {
                    Iterable $this$first$iv2 = this.openTags;
                    Iterator it8 = $this$first$iv2.iterator();
                    do {
                        if (!it8.hasNext()) {
                            throw new NoSuchElementException("Collection contains no element matching the predicate.");
                        }
                        element$iv = it8.next();
                        it = (String) element$iv;
                    } while (!StringsKt.startsWith$default((CharSequence) this.buffer, (CharSequence) it, i, false, 4, (Object) null));
                    String tag2 = (String) element$iv;
                    this.inThought = true;
                    i += tag2.length();
                } else {
                    if (openIdx == null) {
                        String tail2 = this.buffer.substring(i);
                        Intrinsics.checkNotNull(tail2);
                        int keep2 = longestSuffixPrefixOfTags(tail2, this.openTags);
                        out.append((CharSequence) tail2, 0, tail2.length() - keep2);
                        String strSubstring2 = tail2.substring(tail2.length() - keep2);
                        Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                        this.buffer = new StringBuilder(strSubstring2);
                        String string2 = out.toString();
                        Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                        return string2;
                    }
                    out.append(this.buffer.substring(i, openIdx.intValue()));
                    i = openIdx.intValue();
                }
            }
        }
        this.buffer = new StringBuilder();
        String string3 = out.toString();
        Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
        return string3;
    }

    public final String flush() {
        if (this.inThought) {
            this.buffer = new StringBuilder();
            return "";
        }
        String out = this.buffer.toString();
        Intrinsics.checkNotNullExpressionValue(out, "toString(...)");
        this.buffer = new StringBuilder();
        return out;
    }

    private final int longestSuffixPrefixOfTags(String s, List<String> tags) {
        int best = 0;
        for (String t : tags) {
            int maxKeep = Math.min(t.length() - 1, s.length());
            int i = best + 1;
            if (i <= maxKeep) {
                int keep = maxKeep;
                while (true) {
                    int keep2 = s.length();
                    if (!StringsKt.regionMatches$default(s, keep2 - keep, t, 0, keep, false, 16, (Object) null)) {
                        if (keep == i) {
                            break;
                        }
                        keep--;
                    } else {
                        best = keep;
                        break;
                    }
                }
            }
        }
        return best;
    }
}
