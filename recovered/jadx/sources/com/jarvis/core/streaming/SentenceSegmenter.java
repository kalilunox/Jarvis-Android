package com.jarvis.core.streaming;

import com.jarvis.app.VoiceService;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import kotlin.text.Typography;

/* JADX INFO: compiled from: SentenceSegmenter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000f2\u0006\u0010\u0010\u001a\u00020\rJ\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\u000fJ\u0006\u0010\u0012\u001a\u00020\rJ\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u000fJ\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\u000f2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0018\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\rH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00060\tj\u0002`\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/jarvis/core/streaming/SentenceSegmenter;", "", "softMaxChars", "", "hardMaxChars", "minFlushChars", "<init>", "(III)V", "buffer", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "emitted", "", "", "push", "", "delta", "flush", "pending", "allEmitted", "drain", "final", "", "findBoundary", VoiceService.EXTRA_TEXT, "looksCompleteSentence", "s", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SentenceSegmenter {
    private final StringBuilder buffer;
    private final List<String> emitted;
    private final int hardMaxChars;
    private final int minFlushChars;
    private final int softMaxChars;

    public SentenceSegmenter() {
        this(0, 0, 0, 7, null);
    }

    public SentenceSegmenter(int softMaxChars, int hardMaxChars, int minFlushChars) {
        this.softMaxChars = softMaxChars;
        this.hardMaxChars = hardMaxChars;
        this.minFlushChars = minFlushChars;
        this.buffer = new StringBuilder();
        this.emitted = new ArrayList();
    }

    public /* synthetic */ SentenceSegmenter(int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 90 : i, (i4 & 2) != 0 ? 160 : i2, (i4 & 4) != 0 ? 12 : i3);
    }

    public final List<String> push(String delta) {
        Intrinsics.checkNotNullParameter(delta, "delta");
        if (delta.length() == 0) {
            return CollectionsKt.emptyList();
        }
        this.buffer.append(delta);
        return drain(false);
    }

    public final List<String> flush() {
        return drain(true);
    }

    public final String pending() {
        String string = this.buffer.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    public final List<String> allEmitted() {
        return CollectionsKt.toList(this.emitted);
    }

    private final List<String> drain(boolean z) {
        List out = new ArrayList();
        while (true) {
            String text = this.buffer.toString();
            Intrinsics.checkNotNullExpressionValue(text, "toString(...)");
            if (StringsKt.isBlank(text)) {
                StringsKt.clear(this.buffer);
                break;
            }
            int boundary = findBoundary(text, z);
            if (boundary < 0) {
                if (z) {
                    String leftover = StringsKt.trim((CharSequence) text).toString();
                    if (leftover.length() > 0) {
                        out.add(leftover);
                        this.emitted.add(leftover);
                    }
                    StringsKt.clear(this.buffer);
                    break;
                }
                if (text.length() < this.hardMaxChars) {
                    break;
                }
                Integer numValueOf = Integer.valueOf(StringsKt.lastIndexOf$default((CharSequence) text, ' ', this.hardMaxChars, false, 4, (Object) null));
                int it = numValueOf.intValue();
                int it2 = it >= this.minFlushChars ? 1 : 0;
                if (it2 == 0) {
                    numValueOf = null;
                }
                int cut = numValueOf != null ? numValueOf.intValue() : this.hardMaxChars;
                String strSubstring = text.substring(0, cut);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                String piece = StringsKt.trim((CharSequence) strSubstring).toString();
                if (piece.length() > 0) {
                    out.add(piece);
                    this.emitted.add(piece);
                }
                this.buffer.delete(0, cut);
            } else {
                String strSubstring2 = text.substring(0, boundary + 1);
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                String piece2 = StringsKt.trim((CharSequence) strSubstring2).toString();
                if (piece2.length() < this.minFlushChars && !z && !looksCompleteSentence(piece2)) {
                    break;
                }
                if (piece2.length() > 0) {
                    out.add(piece2);
                    this.emitted.add(piece2);
                }
                int next = boundary + 1;
                while (next < text.length() && CharsKt.isWhitespace(text.charAt(next))) {
                    next++;
                }
                this.buffer.delete(0, next);
            }
        }
        return out;
    }

    private final int findBoundary(String text, boolean z) {
        char[] punct = {'.', '!', '?', Typography.ellipsis, 12290, 65281, 65311};
        int best = -1;
        int length = text.length();
        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            if (ArraysKt.contains(punct, c) && (c != '.' || i <= 0 || !Character.isUpperCase(text.charAt(i - 1)) || i + 1 >= text.length() || !Character.isLetter(text.charAt(i + 1)))) {
                best = i;
                if (i + 1 >= this.softMaxChars) {
                    return i;
                }
            }
        }
        int i2 = this.minFlushChars;
        if (best >= i2) {
            return best;
        }
        if (text.length() >= this.softMaxChars) {
            char[] softMarks = {',', ';', ':', Typography.mdash, Typography.ndash};
            int i3 = this.softMaxChars - 1;
            int i4 = this.minFlushChars;
            if (i4 <= i3) {
                while (!ArraysKt.contains(softMarks, text.charAt(i3))) {
                    if (i3 != i4) {
                        i3--;
                    }
                }
                return i3;
            }
            int sp = StringsKt.lastIndexOf$default((CharSequence) text, ' ', this.softMaxChars, false, 4, (Object) null);
            if (sp >= this.minFlushChars) {
                return sp;
            }
        }
        if (!z || StringsKt.isBlank(text)) {
            return -1;
        }
        return StringsKt.getLastIndex(text);
    }

    private final boolean looksCompleteSentence(String s) {
        String t = StringsKt.trim((CharSequence) s).toString();
        if (t.length() == 0) {
            return false;
        }
        return SetsKt.setOf((Object[]) new Character[]{'.', '!', '?', Character.valueOf(Typography.ellipsis), (char) 12290, (char) 65281, (char) 65311}).contains(Character.valueOf(StringsKt.last(t)));
    }
}
