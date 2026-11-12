package com.jarvis.core.nlp;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.sequences.SequencesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: InfoFeeds.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u000e\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\tH\u0002J\u001d\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\t¢\u0006\u0002\u0010\u0015J\u000e\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0012J(\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\tH\u0002J\u0018\u0010\u001d\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\tJ\u0010\u0010\u001f\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0013\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/jarvis/core/nlp/InfoFeeds;", "", "<init>", "()V", "ITEM", "Lkotlin/text/Regex;", "TITLE", "ENTITIES", "", "", "headlines", "", "rss", "limit", "", "clean", "raw", "rate", "", "json", "code", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Double;", "rubles", "value", "plural", "n", "one", "few", "many", "rateReport", "CURRENCY_NAMES", "ratesReport", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class InfoFeeds {
    public static final InfoFeeds INSTANCE = new InfoFeeds();
    private static final Regex ITEM = new Regex("<item>(.*?)</item>", RegexOption.DOT_MATCHES_ALL);
    private static final Regex TITLE = new Regex("<title>(.*?)</title>", RegexOption.DOT_MATCHES_ALL);
    private static final Map<String, String> ENTITIES = MapsKt.mapOf(TuplesKt.to("&quot;", "\""), TuplesKt.to("&amp;", "&"), TuplesKt.to("&lt;", "<"), TuplesKt.to("&gt;", ">"), TuplesKt.to("&#39;", "'"), TuplesKt.to("&apos;", "'"));
    private static final Map<String, String> CURRENCY_NAMES = MapsKt.mapOf(TuplesKt.to("USD", "Доллар"), TuplesKt.to("EUR", "Евро"), TuplesKt.to("CNY", "Юань"));

    private InfoFeeds() {
    }

    public final List<String> headlines(String rss, int limit) {
        Intrinsics.checkNotNullParameter(rss, "rss");
        return SequencesKt.toList(SequencesKt.take(SequencesKt.filter(SequencesKt.map(SequencesKt.mapNotNull(Regex.findAll$default(ITEM, rss, 0, 2, null), new Function1() { // from class: com.jarvis.core.nlp.InfoFeeds$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return InfoFeeds.headlines$lambda$0((MatchResult) obj);
            }
        }), new AnonymousClass2(this)), new Function1() { // from class: com.jarvis.core.nlp.InfoFeeds$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(InfoFeeds.headlines$lambda$1((String) obj));
            }
        }), limit));
    }

    /* JADX INFO: renamed from: com.jarvis.core.nlp.InfoFeeds$headlines$2, reason: invalid class name */
    /* JADX INFO: compiled from: InfoFeeds.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function1<String, String> {
        AnonymousClass2(Object obj) {
            super(1, obj, InfoFeeds.class, "clean", "clean(Ljava/lang/String;)Ljava/lang/String;", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final String invoke(String p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return ((InfoFeeds) this.receiver).clean(p0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String headlines$lambda$0(MatchResult it) {
        List<String> groupValues;
        Intrinsics.checkNotNullParameter(it, "it");
        MatchResult matchResultFind$default = Regex.find$default(TITLE, it.getGroupValues().get(1), 0, 2, null);
        if (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null) {
            return null;
        }
        return groupValues.get(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean headlines$lambda$1(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return !StringsKt.isBlank(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String clean(String raw) {
        String string = StringsKt.trim((CharSequence) StringsKt.replace$default(StringsKt.replace$default(raw, "<![CDATA[", "", false, 4, (Object) null), "]]>", "", false, 4, (Object) null)).toString();
        for (Map.Entry<String, String> entry : ENTITIES.entrySet()) {
            String entity = entry.getKey();
            string = StringsKt.replace$default(string, entity, entry.getValue(), false, 4, (Object) null);
        }
        return string;
    }

    public final Double rate(String json, String code) {
        List<String> groupValues;
        String str;
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(code, "code");
        MatchResult matchResultFind$default = Regex.find$default(new Regex("\"" + code + "\"\\s*:\\s*([0-9.]+)"), json, 0, 2, null);
        if (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null || (str = groupValues.get(1)) == null) {
            return null;
        }
        return StringsKt.toDoubleOrNull(str);
    }

    public final String rubles(double value) {
        int kopecks = MathKt.roundToInt(((double) 100) * value);
        int rub = kopecks / 100;
        int kop = kopecks % 100;
        return rub + StringUtils.SPACE + plural(rub, "рубль", "рубля", "рублей") + StringUtils.SPACE + kop + StringUtils.SPACE + plural(kop, "копейка", "копейки", "копеек");
    }

    private final String plural(int n, String one, String few, String many) {
        int tens = n % 100;
        int units = n % 10;
        if (!(11 <= tens && tens < 15)) {
            if (units == 1) {
                return one;
            }
            if (2 <= units && units < 5) {
                return few;
            }
        }
        return many;
    }

    public final String rateReport(String json, String code) {
        double value;
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(code, "code");
        Double dRate = rate(json, "RUB");
        if (dRate == null) {
            return null;
        }
        double rub = dRate.doubleValue();
        if (!Intrinsics.areEqual(code, "USD")) {
            Double dRate2 = rate(json, code);
            if (dRate2 != null) {
                double it = dRate2.doubleValue();
                if (!(it > 0.0d)) {
                    dRate2 = null;
                }
                if (dRate2 != null) {
                    value = rub / dRate2.doubleValue();
                }
            }
            return null;
        }
        value = rub;
        String str = CURRENCY_NAMES.get(code);
        if (str == null) {
            return null;
        }
        return str + StringUtils.SPACE + rubles(value) + ", сэр.";
    }

    public final String ratesReport(String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        Double dRate = rate(json, "RUB");
        if (dRate == null) {
            return null;
        }
        double rub = dRate.doubleValue();
        Double dRate2 = rate(json, "EUR");
        if (dRate2 != null) {
            double it = dRate2.doubleValue();
            if (!(it > 0.0d)) {
                dRate2 = null;
            }
            if (dRate2 != null) {
                double eur = dRate2.doubleValue();
                Double dRate3 = rate(json, "CNY");
                if (dRate3 != null) {
                    double it2 = dRate3.doubleValue();
                    if (!(it2 > 0.0d)) {
                        dRate3 = null;
                    }
                    if (dRate3 != null) {
                        double cny = dRate3.doubleValue();
                        return "Доллар " + rubles(rub) + ". Евро " + rubles(rub / eur) + ". Юань " + rubles(rub / cny) + ".";
                    }
                }
                return null;
            }
        }
        return null;
    }
}
