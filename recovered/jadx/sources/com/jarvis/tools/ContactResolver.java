package com.jarvis.tools;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import com.google.android.gms.actions.SearchIntents;
import com.jarvis.core.nlp.Transliterator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ContactResolver.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0012B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ&\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\rJ \u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\tH\u0002¨\u0006\u0013"}, d2 = {"Lcom/jarvis/tools/ContactResolver;", "", "<init>", "()V", "resolvePhone", "Lcom/jarvis/tools/ContactResolver$Match;", "context", "Landroid/content/Context;", SearchIntents.EXTRA_QUERY, "", "resolveAll", "", "limit", "", "score", "raw", "rawNorm", "name", "Match", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ContactResolver {
    public static final ContactResolver INSTANCE = new ContactResolver();

    private ContactResolver() {
    }

    /* JADX INFO: compiled from: ContactResolver.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lcom/jarvis/tools/ContactResolver$Match;", "", "name", "", "number", "score", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;I)V", "getName", "()Ljava/lang/String;", "getNumber", "getScore", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Match {
        private final String name;
        private final String number;
        private final int score;

        public static /* synthetic */ Match copy$default(Match match, String str, String str2, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = match.name;
            }
            if ((i2 & 2) != 0) {
                str2 = match.number;
            }
            if ((i2 & 4) != 0) {
                i = match.score;
            }
            return match.copy(str, str2, i);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getNumber() {
            return this.number;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getScore() {
            return this.score;
        }

        public final Match copy(String name, String number, int score) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(number, "number");
            return new Match(name, number, score);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Match)) {
                return false;
            }
            Match match = (Match) other;
            return Intrinsics.areEqual(this.name, match.name) && Intrinsics.areEqual(this.number, match.number) && this.score == match.score;
        }

        public int hashCode() {
            return (((this.name.hashCode() * 31) + this.number.hashCode()) * 31) + Integer.hashCode(this.score);
        }

        public String toString() {
            return "Match(name=" + this.name + ", number=" + this.number + ", score=" + this.score + ")";
        }

        public Match(String name, String number, int score) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(number, "number");
            this.name = name;
            this.number = number;
            this.score = score;
        }

        public /* synthetic */ Match(String str, String str2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i2 & 4) != 0 ? 100 : i);
        }

        public final String getName() {
            return this.name;
        }

        public final String getNumber() {
            return this.number;
        }

        public final int getScore() {
            return this.score;
        }
    }

    public final Match resolvePhone(Context context, String query) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(query, "query");
        return (Match) CollectionsKt.firstOrNull(resolveAll$default(this, context, query, 0, 4, null));
    }

    public static /* synthetic */ List resolveAll$default(ContactResolver contactResolver, Context context, String str, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 5;
        }
        return contactResolver.resolveAll(context, str, i);
    }

    /* JADX WARN: Code duplicated, block: B:85:0x018c A[Catch: all -> 0x02b5, TryCatch #0 {all -> 0x02b5, blocks: (B:63:0x0130, B:64:0x0134, B:66:0x013a, B:70:0x0143, B:74:0x014b, B:76:0x0167, B:85:0x018c, B:87:0x0198, B:88:0x01a5, B:90:0x01bc, B:93:0x01c6, B:95:0x01d0, B:97:0x01fa, B:101:0x020b, B:104:0x0234), top: B:127:0x0130 }] */
    /* JADX WARN: Code duplicated, block: B:86:0x0194  */
    public final List<Match> resolveAll(Context context, String query, int limit) throws IOException {
        boolean z;
        Cursor it;
        char ch;
        Appendable destination$iv$iv;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(query, "query");
        String q = StringsKt.trim((CharSequence) query).toString();
        int i = 0;
        int i2 = 1;
        if (q.length() == 0) {
            return CollectionsKt.emptyList();
        }
        String $this$any$iv = q;
        int i3 = 0;
        while (true) {
            if (i3 >= $this$any$iv.length()) {
                z = false;
                break;
            }
            char element$iv = $this$any$iv.charAt(i3);
            if (Character.isDigit(element$iv)) {
                z = true;
                break;
            }
            i3++;
        }
        if (z) {
            String $this$filterTo$iv$iv = q;
            Appendable destination$iv$iv2 = new StringBuilder();
            int length = $this$filterTo$iv$iv.length();
            for (int index$iv$iv = 0; index$iv$iv < length; index$iv$iv++) {
                char element$iv$iv = $this$filterTo$iv$iv.charAt(index$iv$iv);
                char it2 = (Character.isDigit(element$iv$iv) || element$iv$iv == '+') ? (char) 1 : (char) 0;
                if (it2 != 0) {
                    destination$iv$iv2.append(element$iv$iv);
                }
            }
            String $this$filter$iv = ((StringBuilder) destination$iv$iv2).toString();
            if ($this$filter$iv.length() >= 5) {
                String $this$count$iv = q;
                int count$iv = 0;
                for (int i4 = 0; i4 < $this$count$iv.length(); i4++) {
                    char element$iv2 = $this$count$iv.charAt(i4);
                    if (Character.isLetter(element$iv2)) {
                        count$iv++;
                    }
                }
                if (count$iv == 0) {
                    String $this$filterTo$iv$iv2 = q;
                    Appendable destination$iv$iv3 = new StringBuilder();
                    int length2 = $this$filterTo$iv$iv2.length();
                    for (int index$iv$iv2 = 0; index$iv$iv2 < length2; index$iv$iv2++) {
                        char element$iv$iv2 = $this$filterTo$iv$iv2.charAt(index$iv$iv2);
                        char it3 = (Character.isDigit(element$iv$iv2) || element$iv$iv2 == '+') ? (char) 1 : (char) 0;
                        if (it3 != 0) {
                            destination$iv$iv3.append(element$iv$iv2);
                        }
                    }
                    String $this$filter$iv2 = ((StringBuilder) destination$iv$iv3).toString();
                    return CollectionsKt.listOf(new Match(q, $this$filter$iv2, 100));
                }
            }
        }
        if (context.checkSelfPermission("android.permission.READ_CONTACTS") != 0) {
            return CollectionsKt.emptyList();
        }
        String qNorm = Transliterator.INSTANCE.compareKey(q);
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{"display_name", "data1"}, null, null, null);
        if (cursor == null) {
            return CollectionsKt.emptyList();
        }
        LinkedHashMap scored = new LinkedHashMap();
        Cursor cursor2 = cursor;
        try {
            Cursor it4 = cursor2;
            while (it4.moveToNext()) {
                String name = it4.getString(i);
                CharSequence $this$filter$iv3 = "";
                if (name == null) {
                    name = "";
                }
                CharSequence string = it4.getString(i2);
                if (string != null) {
                    $this$filter$iv3 = string;
                }
                CharSequence $this$filterTo$iv$iv3 = $this$filter$iv3;
                Appendable destination$iv$iv4 = new StringBuilder();
                int length3 = $this$filterTo$iv$iv3.length();
                int index$iv$iv3 = 0;
                while (index$iv$iv3 < length3) {
                    CharSequence $this$filterTo$iv$iv4 = $this$filterTo$iv$iv3;
                    char ch2 = $this$filterTo$iv$iv4.charAt(index$iv$iv3);
                    if (Character.isDigit(ch2)) {
                        it = it4;
                    } else {
                        it = it4;
                        if (ch2 != '+') {
                            ch = 0;
                        }
                        if (ch != 0) {
                            destination$iv$iv = destination$iv$iv4;
                            destination$iv$iv.append(ch2);
                        } else {
                            destination$iv$iv = destination$iv$iv4;
                        }
                        index$iv$iv3++;
                        destination$iv$iv4 = destination$iv$iv;
                        $this$filterTo$iv$iv3 = $this$filterTo$iv$iv4;
                        it4 = it;
                    }
                    ch = 1;
                    if (ch != 0) {
                        destination$iv$iv = destination$iv$iv4;
                        destination$iv$iv.append(ch2);
                    } else {
                        destination$iv$iv = destination$iv$iv4;
                    }
                    index$iv$iv3++;
                    destination$iv$iv4 = destination$iv$iv;
                    $this$filterTo$iv$iv3 = $this$filterTo$iv$iv4;
                    it4 = it;
                }
                it4 = it4;
                String number = ((StringBuilder) destination$iv$iv4).toString();
                if (StringsKt.isBlank(name) || StringsKt.isBlank(number)) {
                    i = 0;
                    i2 = 1;
                } else {
                    int s = INSTANCE.score(q, qNorm, name);
                    if (s >= 50) {
                        String lowerCase = name.toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                        String key = lowerCase + "|" + number;
                        Match prev = (Match) scored.get(key);
                        if (prev == null || s > prev.getScore()) {
                            scored.put(key, new Match(name, number, s));
                            i = 0;
                            i2 = 1;
                        } else {
                            i = 0;
                            i2 = 1;
                        }
                    } else {
                        i = 0;
                        i2 = 1;
                    }
                }
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursor2, null);
            Iterable iterableValues = scored.values();
            Intrinsics.checkNotNullExpressionValue(iterableValues, "<get-values>(...)");
            Iterable $this$sortedByDescending$iv = iterableValues;
            List list = CollectionsKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: com.jarvis.tools.ContactResolver$resolveAll$$inlined$sortedByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    ContactResolver.Match it5 = (ContactResolver.Match) t2;
                    ContactResolver.Match it6 = (ContactResolver.Match) t;
                    return ComparisonsKt.compareValues(Integer.valueOf(it5.getScore()), Integer.valueOf(it6.getScore()));
                }
            });
            if (list.isEmpty()) {
                return CollectionsKt.emptyList();
            }
            int best = ((Match) CollectionsKt.first(list)).getScore();
            List $this$filter$iv4 = list;
            Collection destination$iv$iv5 = new ArrayList();
            for (Object element$iv$iv3 : $this$filter$iv4) {
                Match it5 = (Match) element$iv$iv3;
                List list2 = list;
                int best2 = best;
                if (best - it5.getScore() <= 15) {
                    destination$iv$iv5.add(element$iv$iv3);
                }
                best = best2;
                list = list2;
            }
            return CollectionsKt.take((List) destination$iv$iv5, limit);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursor2, th);
                throw th2;
            }
        }
    }

    private final int score(String raw, String rawNorm, String name) {
        String n = name.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(n, "toLowerCase(...)");
        String r = raw.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(r, "toLowerCase(...)");
        String nNorm = Transliterator.INSTANCE.compareKey(name);
        if (Intrinsics.areEqual(n, r)) {
            return 100;
        }
        if (Intrinsics.areEqual(nNorm, rawNorm)) {
            if (rawNorm.length() > 0) {
                return 95;
            }
        }
        if (StringsKt.startsWith$default(n, r, false, 2, (Object) null)) {
            return 85;
        }
        if (StringsKt.contains$default((CharSequence) n, (CharSequence) r, false, 2, (Object) null)) {
            return 75;
        }
        return (!StringsKt.contains$default((CharSequence) nNorm, (CharSequence) rawNorm, false, 2, (Object) null) || rawNorm.length() < 3) ? 0 : 65;
    }
}
