package com.jarvis.asr;

import com.jarvis.app.VoiceService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: CommandPcmRefiner.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\n\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\tJ#\u0010\f\u001a\u00020\t2\u0016\u0010\r\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\t0\u000e\"\u0004\u0018\u00010\t¢\u0006\u0002\u0010\u000fJ\u000e\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\tJ\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u0005J@\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00122\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\t0\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00052\b\b\u0002\u0010\u0019\u001a\u00020\u00052\b\b\u0002\u0010\u001a\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\t0\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\t0\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/jarvis/asr/CommandPcmRefiner;", "", "<init>", "()V", "TAIL_SAMPLES", "", "needsLatinRefine", "", "streamingRu", "", "hasUsefulLatin", VoiceService.EXTRA_TEXT, "bestLatinHint", "candidates", "", "([Ljava/lang/String;)Ljava/lang/String;", "latinScore", "tailOf", "", "pcm", "maxSamples", "scanLatinWindows", "decode", "Lkotlin/Function1;", "windowSamples", "hopSamples", "maxWindows", "MUSIC_HINTS", "", "OPEN_HINTS", "WINDOW_SAMPLES", "HOP_SAMPLES", "asr-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CommandPcmRefiner {
    private static final int HOP_SAMPLES = 16000;
    public static final CommandPcmRefiner INSTANCE = new CommandPcmRefiner();
    private static final List<String> MUSIC_HINTS = CollectionsKt.listOf((Object[]) new String[]{"песн", "песен", "трек", "музык", "альбом", "исполнит", "артист", "композиц"});
    private static final List<String> OPEN_HINTS = CollectionsKt.listOf((Object[]) new String[]{"открой", "открыть", "запусти", "запустить"});
    public static final int TAIL_SAMPLES = 24000;
    private static final int WINDOW_SAMPLES = 24000;

    private CommandPcmRefiner() {
    }

    public final boolean needsLatinRefine(String streamingRu) {
        boolean z;
        boolean z2;
        Intrinsics.checkNotNullParameter(streamingRu, "streamingRu");
        String t = streamingRu.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(t, "toLowerCase(...)");
        if (StringsKt.isBlank(t)) {
            return false;
        }
        Iterable $this$any$iv = MUSIC_HINTS;
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    String it2 = (String) element$iv;
                    if (StringsKt.contains$default((CharSequence) t, (CharSequence) it2, false, 2, (Object) null)) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
        } else {
            z = false;
        }
        if (z) {
            return true;
        }
        Iterable $this$any$iv2 = OPEN_HINTS;
        if (!($this$any$iv2 instanceof Collection) || !((Collection) $this$any$iv2).isEmpty()) {
            Iterator it3 = $this$any$iv2.iterator();
            while (true) {
                if (it3.hasNext()) {
                    Object element$iv2 = it3.next();
                    String it4 = (String) element$iv2;
                    if (StringsKt.contains$default((CharSequence) t, (CharSequence) it4, false, 2, (Object) null)) {
                        z2 = true;
                        break;
                    }
                } else {
                    z2 = false;
                    break;
                }
            }
        } else {
            z2 = false;
        }
        if (!z2) {
            return false;
        }
        Iterable $this$filter$iv = new Regex("\\s+").split(t, 0);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it5 = (String) element$iv$iv;
            if (it5.length() > 1) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List words = (List) destination$iv$iv;
        return words.size() >= 2;
    }

    public final boolean hasUsefulLatin(String text) {
        String t = text != null ? StringsKt.trim((CharSequence) text).toString() : null;
        if (t == null) {
            t = "";
        }
        return !(t.length() == 0) && latinScore(t) >= 4;
    }

    public final String bestLatinHint(String... candidates) {
        int score;
        Intrinsics.checkNotNullParameter(candidates, "candidates");
        String best = "";
        int bestScore = 0;
        int length = candidates.length;
        for (int i = 0; i < length; i++) {
            String raw = candidates[i];
            String t = raw != null ? StringsKt.trim((CharSequence) raw).toString() : null;
            if (t == null) {
                t = "";
            }
            if (!StringsKt.isBlank(t) && (score = latinScore(t)) > bestScore) {
                bestScore = score;
                best = t;
            }
        }
        return best;
    }

    /* JADX WARN: Code duplicated, block: B:27:0x003f  */
    public final int latinScore(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        int latin = 0;
        int cyr = 0;
        int length = text.length();
        for (int i = 0; i < length; i++) {
            char ch = text.charAt(i);
            if ('a' <= ch && ch < '{') {
                latin++;
            } else if ('A' <= ch && ch < '[') {
                latin++;
            } else if (1024 <= ch && ch < 1280) {
                cyr++;
            }
        }
        if (latin == 0) {
            return 0;
        }
        return (latin * 2) - cyr;
    }

    public static /* synthetic */ float[] tailOf$default(CommandPcmRefiner commandPcmRefiner, float[] fArr, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 24000;
        }
        return commandPcmRefiner.tailOf(fArr, i);
    }

    public final float[] tailOf(float[] pcm, int maxSamples) {
        Intrinsics.checkNotNullParameter(pcm, "pcm");
        return pcm.length <= maxSamples ? pcm : ArraysKt.copyOfRange(pcm, pcm.length - maxSamples, pcm.length);
    }

    public final String scanLatinWindows(float[] pcm, Function1<? super float[], String> decode, int windowSamples, int hopSamples, int maxWindows) {
        Intrinsics.checkNotNullParameter(pcm, "pcm");
        Intrinsics.checkNotNullParameter(decode, "decode");
        if (pcm.length == 0) {
            return "";
        }
        if (pcm.length <= windowSamples) {
            String strInvoke = decode.invoke(pcm);
            String it = strInvoke;
            if (!(INSTANCE.latinScore(it) > 0)) {
                strInvoke = null;
            }
            String str = strInvoke;
            return str == null ? "" : str;
        }
        ArrayList hits = new ArrayList(maxWindows);
        int i = 0;
        int n = 0;
        while (i < pcm.length && n < maxWindows) {
            int end = Math.min(i + windowSamples, pcm.length);
            if (end - i >= windowSamples / 2) {
                String t = decode.invoke(ArraysKt.copyOfRange(pcm, i, end));
                if (latinScore(t) >= 3) {
                    hits.add(t);
                }
                n++;
            }
            if (end >= pcm.length) {
                break;
            }
            i += hopSamples;
        }
        ArrayList $this$toTypedArray$iv = hits;
        String[] strArr = (String[]) $this$toTypedArray$iv.toArray(new String[0]);
        return bestLatinHint((String[]) Arrays.copyOf(strArr, strArr.length));
    }
}
