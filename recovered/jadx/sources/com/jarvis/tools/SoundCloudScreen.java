package com.jarvis.tools;

import com.google.android.gms.actions.SearchIntents;
import com.jarvis.app.VoiceService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: SoundCloudDriver.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001\u0019B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tJ\u001e\u0010\n\u001a\u0004\u0018\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007H\u0002J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u00112\u0006\u0010\u0012\u001a\u00020\fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\f0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/jarvis/tools/SoundCloudScreen;", "", "<init>", "()V", "DURATION", "Lkotlin/text/Regex;", "firstTrack", "Lcom/jarvis/tools/SoundCloudScreen$Row;", "rows", "", "bestTrack", SearchIntents.EXTRA_QUERY, "", "isTrack", "", "row", "words", "", VoiceService.EXTRA_TEXT, "NOT_WORD", "VARIANTS", "TITLE_REACH_PX", "", "VARIANT_PENALTY", "", "Row", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SoundCloudScreen {
    private static final int TITLE_REACH_PX = 220;
    private static final double VARIANT_PENALTY = 0.5d;
    public static final SoundCloudScreen INSTANCE = new SoundCloudScreen();
    private static final Regex DURATION = new Regex("(^|\\s)\\d{1,2}:\\d{2}(:\\d{2})?$");
    private static final Regex NOT_WORD = new Regex("[^\\p{L}\\p{N}]+");
    private static final Set<String> VARIANTS = SetsKt.setOf((Object[]) new String[]{"remix", "rmx", "cover", "nightcore", "slowed", "reverb", "sped", "karaoke", "instrumental", "ремикс", "кавер", "караоке", "минус"});

    private SoundCloudScreen() {
    }

    /* JADX INFO: compiled from: SoundCloudDriver.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/jarvis/tools/SoundCloudScreen$Row;", "", VoiceService.EXTRA_TEXT, "", "top", "", "bottom", "<init>", "(Ljava/lang/String;II)V", "getText", "()Ljava/lang/String;", "getTop", "()I", "getBottom", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Row {
        private final int bottom;
        private final String text;
        private final int top;

        public static /* synthetic */ Row copy$default(Row row, String str, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = row.text;
            }
            if ((i3 & 2) != 0) {
                i = row.top;
            }
            if ((i3 & 4) != 0) {
                i2 = row.bottom;
            }
            return row.copy(str, i, i2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getText() {
            return this.text;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getTop() {
            return this.top;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getBottom() {
            return this.bottom;
        }

        public final Row copy(String text, int top, int bottom) {
            Intrinsics.checkNotNullParameter(text, "text");
            return new Row(text, top, bottom);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Row)) {
                return false;
            }
            Row row = (Row) other;
            return Intrinsics.areEqual(this.text, row.text) && this.top == row.top && this.bottom == row.bottom;
        }

        public int hashCode() {
            return (((this.text.hashCode() * 31) + Integer.hashCode(this.top)) * 31) + Integer.hashCode(this.bottom);
        }

        public String toString() {
            return "Row(text=" + this.text + ", top=" + this.top + ", bottom=" + this.bottom + ")";
        }

        public Row(String text, int top, int bottom) {
            Intrinsics.checkNotNullParameter(text, "text");
            this.text = text;
            this.top = top;
            this.bottom = bottom;
        }

        public final int getBottom() {
            return this.bottom;
        }

        public final String getText() {
            return this.text;
        }

        public final int getTop() {
            return this.top;
        }
    }

    public final Row firstTrack(List<Row> rows) {
        Object minElem$iv;
        Intrinsics.checkNotNullParameter(rows, "rows");
        List<Row> $this$filter$iv = rows;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            Row it = (Row) element$iv$iv;
            if (INSTANCE.isTrack(it)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$minByOrNull$iv = (List) destination$iv$iv;
        Iterator iterator$iv = $this$minByOrNull$iv.iterator();
        if (iterator$iv.hasNext()) {
            minElem$iv = iterator$iv.next();
            if (iterator$iv.hasNext()) {
                Row it2 = (Row) minElem$iv;
                int minValue$iv = it2.getTop();
                do {
                    Object e$iv = iterator$iv.next();
                    Row it3 = (Row) e$iv;
                    int v$iv = it3.getTop();
                    if (minValue$iv > v$iv) {
                        minElem$iv = e$iv;
                        minValue$iv = v$iv;
                    }
                } while (iterator$iv.hasNext());
            }
        } else {
            minElem$iv = null;
        }
        return (Row) minElem$iv;
    }

    /* JADX WARN: Code duplicated, block: B:74:0x01ca  */
    public final Row bestTrack(List<Row> rows, String query) {
        boolean variantAsked;
        Object maxElem$iv;
        int count$iv;
        double d;
        boolean z;
        Iterable rows2 = rows;
        Intrinsics.checkNotNullParameter(rows2, "rows");
        Intrinsics.checkNotNullParameter(query, "query");
        Iterable $this$filter$iv = rows2;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            Row it = (Row) element$iv$iv;
            if (INSTANCE.isTrack(it)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$sortedBy$iv = (List) destination$iv$iv;
        List tracks = CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: com.jarvis.tools.SoundCloudScreen$bestTrack$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                SoundCloudScreen.Row it2 = (SoundCloudScreen.Row) t;
                SoundCloudScreen.Row it3 = (SoundCloudScreen.Row) t2;
                return ComparisonsKt.compareValues(Integer.valueOf(it2.getTop()), Integer.valueOf(it3.getTop()));
            }
        });
        Set<String> setWords = words(query);
        if (setWords.isEmpty()) {
            return (Row) CollectionsKt.firstOrNull(tracks);
        }
        Iterable $this$any$iv = VARIANTS;
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            Iterator it2 = $this$any$iv.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    variantAsked = false;
                    break;
                }
                Object element$iv = it2.next();
                String it3 = (String) element$iv;
                if (setWords.contains(it3)) {
                    variantAsked = true;
                    break;
                }
            }
        } else {
            variantAsked = false;
        }
        int previousBottom = Integer.MIN_VALUE;
        List $this$map$iv = tracks;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            Row track = (Row) item$iv$iv;
            Iterable $this$filter$iv2 = rows2;
            Collection destination$iv$iv3 = new ArrayList();
            for (Object element$iv$iv2 : $this$filter$iv2) {
                Row it4 = (Row) element$iv$iv2;
                if (it4.getBottom() <= track.getBottom() && it4.getTop() >= previousBottom && it4.getTop() >= track.getTop() + (-220)) {
                    destination$iv$iv3.add(element$iv$iv2);
                }
            }
            String text = CollectionsKt.joinToString$default((List) destination$iv$iv3, StringUtils.SPACE, null, null, 0, null, new Function1() { // from class: com.jarvis.tools.SoundCloudScreen$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SoundCloudScreen.bestTrack$lambda$3$1((SoundCloudScreen.Row) obj);
                }
            }, 30, null);
            int previousBottom2 = track.getBottom();
            Set<String> setWords2 = INSTANCE.words(text);
            Iterable $this$count$iv = setWords;
            if (($this$count$iv instanceof Collection) && ((Collection) $this$count$iv).isEmpty()) {
                count$iv = 0;
            } else {
                count$iv = 0;
                for (Object element$iv2 : $this$count$iv) {
                    $this$count$iv = $this$count$iv;
                    String it5 = (String) element$iv2;
                    if (setWords2.contains(it5)) {
                        count$iv++;
                        if (count$iv < 0) {
                            CollectionsKt.throwCountOverflow();
                        }
                    }
                }
            }
            Iterable $this$map$iv2 = $this$map$iv;
            Set<String> set = setWords;
            double matched = ((double) count$iv) / ((double) setWords.size());
            if (variantAsked) {
                d = matched;
            } else {
                Iterable $this$any$iv2 = VARIANTS;
                if (($this$any$iv2 instanceof Collection) && ((Collection) $this$any$iv2).isEmpty()) {
                    z = false;
                } else {
                    Iterator it6 = $this$any$iv2.iterator();
                    while (true) {
                        if (!it6.hasNext()) {
                            z = false;
                            break;
                        }
                        Object element$iv3 = it6.next();
                        Iterable $this$any$iv3 = $this$any$iv2;
                        String it7 = (String) element$iv3;
                        if (setWords2.contains(it7)) {
                            z = true;
                            break;
                        }
                        $this$any$iv2 = $this$any$iv3;
                    }
                }
                if (z) {
                    d = matched - VARIANT_PENALTY;
                } else {
                    d = matched;
                }
            }
            destination$iv$iv2.add(TuplesKt.to(track, Double.valueOf(d)));
            rows2 = rows;
            previousBottom = previousBottom2;
            setWords = set;
            $this$map$iv = $this$map$iv2;
        }
        Iterable scored = (List) destination$iv$iv2;
        Iterable $this$maxByOrNull$iv = scored;
        Iterator iterator$iv = $this$maxByOrNull$iv.iterator();
        if (iterator$iv.hasNext()) {
            maxElem$iv = iterator$iv.next();
            if (iterator$iv.hasNext()) {
                Pair it8 = (Pair) maxElem$iv;
                double maxValue$iv = ((Number) it8.getSecond()).doubleValue();
                do {
                    Object e$iv = iterator$iv.next();
                    Pair it9 = (Pair) e$iv;
                    double v$iv = ((Number) it9.getSecond()).doubleValue();
                    if (Double.compare(maxValue$iv, v$iv) < 0) {
                        maxElem$iv = e$iv;
                        maxValue$iv = v$iv;
                    }
                } while (iterator$iv.hasNext());
            }
        } else {
            maxElem$iv = null;
        }
        Pair best = (Pair) maxElem$iv;
        if (best == null) {
            return null;
        }
        return (Row) (((Number) best.getSecond()).doubleValue() > 0.0d ? best.getFirst() : CollectionsKt.first(tracks));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence bestTrack$lambda$3$1(Row it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getText();
    }

    private final boolean isTrack(Row row) {
        return DURATION.containsMatchIn(StringsKt.trim((CharSequence) row.getText()).toString());
    }

    private final Set<String> words(String text) {
        String lowerCase = text.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        Iterable $this$filter$iv = NOT_WORD.split(StringsKt.replace$default(lowerCase, (char) 1105, (char) 1077, false, 4, (Object) null), 0);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            if (!StringsKt.isBlank(it)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return CollectionsKt.toSet((List) destination$iv$iv);
    }
}
