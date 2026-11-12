package com.jarvis.core.nlp;

import com.jarvis.app.VoiceService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.time.DurationKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: SpeechTextNormalizer.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u00010B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u0006J\u0018\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!J\u0018\u0010\"\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010 \u001a\u00020!H\u0002J\u0018\u0010#\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020\u0012H\u0002J\u0018\u0010%\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020\u0012H\u0002J\u0018\u0010&\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\r2\u0006\u0010(\u001a\u00020\rH\u0002J\u0010\u0010)\u001a\u00020\u00062\u0006\u0010*\u001a\u00020+H\u0002J\u0010\u0010,\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\rH\u0002J\u0010\u0010.\u001a\u00020\u00062\u0006\u0010*\u001a\u00020+H\u0002J\u0010\u0010/\u001a\u00020\u00062\u0006\u0010*\u001a\u00020+H\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00060\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R \u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u00100\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00060\u00100\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lcom/jarvis/core/nlp/SpeechTextNormalizer;", "", "<init>", "()V", "ONES_M", "", "", "TEENS", "TENS", "HUNDREDS", "ORDINAL_DAY", "YEAR_ORDINAL", "", "", "MONTHS", "UNITS", "Lkotlin/Pair;", "Lkotlin/text/Regex;", "Lcom/jarvis/core/nlp/SpeechTextNormalizer$Unit;", "WORDS", "EMOJI", "MARKUP", "TIME", "DATE", "PERCENT", "DEGREE", "NUMBER", "forSpeech", VoiceService.EXTRA_TEXT, "cardinal", "n", "", "feminine", "", "under1000", "form", "unit", "counted", "time", "hour", "minute", "date", "m", "Lkotlin/text/MatchResult;", "yearGenitive", "year", "percent", "degrees", "Unit", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SpeechTextNormalizer {
    private static final String MONTHS = "января|февраля|марта|апреля|мая|июня|июля|августа|сентября|октября|ноября|декабря";
    public static final SpeechTextNormalizer INSTANCE = new SpeechTextNormalizer();
    private static final List<String> ONES_M = CollectionsKt.listOf((Object[]) new String[]{"ноль", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"});
    private static final List<String> TEENS = CollectionsKt.listOf((Object[]) new String[]{"десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"});
    private static final List<String> TENS = CollectionsKt.listOf((Object[]) new String[]{"", "", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"});
    private static final List<String> HUNDREDS = CollectionsKt.listOf((Object[]) new String[]{"", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"});
    private static final List<String> ORDINAL_DAY = CollectionsKt.listOf((Object[]) new String[]{"", "первое", "второе", "третье", "четвёртое", "пятое", "шестое", "седьмое", "восьмое", "девятое", "десятое", "одиннадцатое", "двенадцатое", "тринадцатое", "четырнадцатое", "пятнадцатое", "шестнадцатое", "семнадцатое", "восемнадцатое", "девятнадцатое", "двадцатое", "двадцать первое", "двадцать второе", "двадцать третье", "двадцать четвёртое", "двадцать пятое", "двадцать шестое", "двадцать седьмое", "двадцать восьмое", "двадцать девятое", "тридцатое", "тридцать первое"});
    private static final Map<Integer, String> YEAR_ORDINAL = MapsKt.mapOf(TuplesKt.to(0, ""), TuplesKt.to(1, "первого"), TuplesKt.to(2, "второго"), TuplesKt.to(3, "третьего"), TuplesKt.to(4, "четвёртого"), TuplesKt.to(5, "пятого"), TuplesKt.to(6, "шестого"), TuplesKt.to(7, "седьмого"), TuplesKt.to(8, "восьмого"), TuplesKt.to(9, "девятого"), TuplesKt.to(10, "десятого"), TuplesKt.to(20, "двадцатого"), TuplesKt.to(30, "тридцатого"));
    private static final List<Pair<Regex, Unit>> UNITS = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(new Regex("(?:мин|минут[аыу]?)"), new Unit(true, "минута", "минуты", "минут")), TuplesKt.to(new Regex("(?:сек|секунд[аыу]?)"), new Unit(true, "секунда", "секунды", "секунд")), TuplesKt.to(new Regex("(?:ч|час(?:а|ов)?)"), new Unit(false, "час", "часа", "часов")), TuplesKt.to(new Regex("(?:ГБ|Гб|гигабайт(?:а|ов)?)"), new Unit(false, "гигабайт", "гигабайта", "гигабайт")), TuplesKt.to(new Regex("(?:МБ|Мб|мегабайт(?:а|ов)?)"), new Unit(false, "мегабайт", "мегабайта", "мегабайт")), TuplesKt.to(new Regex("км/ч"), new Unit(false, "километр в час", "километра в час", "километров в час")), TuplesKt.to(new Regex("км"), new Unit(false, "километр", "километра", "километров")), TuplesKt.to(new Regex("м/с"), new Unit(false, "метр в секунду", "метра в секунду", "метров в секунду")), TuplesKt.to(new Regex("(?:руб|₽)"), new Unit(false, "рубль", "рубля", "рублей"))});
    private static final List<Pair<Regex, String>> WORDS = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(new Regex("(?i)wi[‑-]?fi"), "вай-фай"), TuplesKt.to(new Regex("(?i)bluetooth"), "блютуз"), TuplesKt.to(new Regex("(?i)telegram"), "телеграм"), TuplesKt.to(new Regex("(?i)youtube"), "ютуб"), TuplesKt.to(new Regex("(?i)whatsapp"), "ватсап"), TuplesKt.to(new Regex("(?i)google"), "гугл"), TuplesKt.to(new Regex("(?i)android"), "андроид"), TuplesKt.to(new Regex("(?i)\\bjarvis\\b"), "джарвис")});
    private static final Regex EMOJI = new Regex("[\\x{1F000}-\\x{1FAFF}\\x{2600}-\\x{27BF}\\x{2B00}-\\x{2BFF}\\x{FE0F}\\x{200D}]");
    private static final Regex MARKUP = new Regex("[*_#`~]+");
    private static final Regex TIME = new Regex("(?<![\\d:])([01]?\\d|2[0-3]):([0-5]\\d)(?![\\d:])");
    private static final Regex DATE = new Regex("(?<!\\d)(\\d{1,2})\\s+(января|февраля|марта|апреля|мая|июня|июля|августа|сентября|октября|ноября|декабря)(?:\\s+(\\d{4})(?:\\s*(?:года|г\\.?))?)?");
    private static final Regex PERCENT = new Regex("(?<![\\d,.])(-?)(\\d{1,6})(?:[,.](\\d))?\\s*%");
    private static final Regex DEGREE = new Regex("(?<![\\d,.])([-−]?)(\\d{1,3})\\s*°\\s*[CС]?");
    private static final Regex NUMBER = new Regex("(?<![\\p{L}\\d,.])(-|−)?(\\d{1,9})(?![\\d\\p{L}])");

    private SpeechTextNormalizer() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: SpeechTextNormalizer.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/jarvis/core/nlp/SpeechTextNormalizer$Unit;", "", "feminine", "", "one", "", "few", "many", "<init>", "(ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getFeminine", "()Z", "getOne", "()Ljava/lang/String;", "getFew", "getMany", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    static final class Unit {
        private final boolean feminine;
        private final String few;
        private final String many;
        private final String one;

        public Unit(boolean feminine, String one, String few, String many) {
            Intrinsics.checkNotNullParameter(one, "one");
            Intrinsics.checkNotNullParameter(few, "few");
            Intrinsics.checkNotNullParameter(many, "many");
            this.feminine = feminine;
            this.one = one;
            this.few = few;
            this.many = many;
        }

        public final boolean getFeminine() {
            return this.feminine;
        }

        public final String getFew() {
            return this.few;
        }

        public final String getMany() {
            return this.many;
        }

        public final String getOne() {
            return this.one;
        }
    }

    public final String forSpeech(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        String s = EMOJI.replace(text, "");
        String s2 = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(MARKUP.replace(s, ""), "×", " умножить на ", false, 4, (Object) null), "÷", " разделить на ", false, 4, (Object) null), "≈", " примерно ", false, 4, (Object) null), " = ", " равно ", false, 4, (Object) null), "—", ",", false, 4, (Object) null), " - ", ", ", false, 4, (Object) null), "…", ".", false, 4, (Object) null);
        for (Pair<Regex, String> pair : WORDS) {
            Regex rx = pair.component1();
            String word = pair.component2();
            s2 = rx.replace(s2, word);
        }
        String s3 = DEGREE.replace(PERCENT.replace(DATE.replace(TIME.replace(s2, new Function1() { // from class: com.jarvis.core.nlp.SpeechTextNormalizer$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SpeechTextNormalizer.forSpeech$lambda$0((MatchResult) obj);
            }
        }), new Function1() { // from class: com.jarvis.core.nlp.SpeechTextNormalizer$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SpeechTextNormalizer.forSpeech$lambda$1((MatchResult) obj);
            }
        }), new Function1() { // from class: com.jarvis.core.nlp.SpeechTextNormalizer$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SpeechTextNormalizer.forSpeech$lambda$2((MatchResult) obj);
            }
        }), new Function1() { // from class: com.jarvis.core.nlp.SpeechTextNormalizer$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SpeechTextNormalizer.forSpeech$lambda$3((MatchResult) obj);
            }
        });
        for (Pair<Regex, Unit> pair2 : UNITS) {
            Regex rx2 = pair2.component1();
            final Unit unit = pair2.component2();
            s3 = new Regex("(?<![\\p{L}\\d,.])(\\d{1,6})\\s*" + rx2.getPattern() + "(?![\\p{L}])").replace(s3, new Function1() { // from class: com.jarvis.core.nlp.SpeechTextNormalizer$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SpeechTextNormalizer.forSpeech$lambda$4(unit, (MatchResult) obj);
                }
            });
        }
        return StringsKt.trim((CharSequence) StringsKt.replace$default(new Regex("\\s+([,.!?])").replace(new Regex("\\s+").replace(NUMBER.replace(s3, new Function1() { // from class: com.jarvis.core.nlp.SpeechTextNormalizer$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SpeechTextNormalizer.forSpeech$lambda$5((MatchResult) obj);
            }
        }), StringUtils.SPACE), "$1"), ",,", ",", false, 4, (Object) null)).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence forSpeech$lambda$0(MatchResult it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return INSTANCE.time(Integer.parseInt(it.getGroupValues().get(1)), Integer.parseInt(it.getGroupValues().get(2)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence forSpeech$lambda$1(MatchResult it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return INSTANCE.date(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence forSpeech$lambda$2(MatchResult it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return INSTANCE.percent(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence forSpeech$lambda$3(MatchResult it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return INSTANCE.degrees(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence forSpeech$lambda$4(Unit $unit, MatchResult m) {
        Intrinsics.checkNotNullParameter(m, "m");
        return INSTANCE.counted(Long.parseLong(m.getGroupValues().get(1)), $unit);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence forSpeech$lambda$5(MatchResult m) {
        Intrinsics.checkNotNullParameter(m, "m");
        String minus = m.getGroupValues().get(1).length() > 0 ? "минус " : "";
        return minus + cardinal$default(INSTANCE, Long.parseLong(m.getGroupValues().get(2)), false, 2, null);
    }

    public static /* synthetic */ String cardinal$default(SpeechTextNormalizer speechTextNormalizer, long j, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return speechTextNormalizer.cardinal(j, z);
    }

    public final String cardinal(long n, boolean feminine) {
        if (n == 0) {
            return ONES_M.get(0);
        }
        ArrayList parts = new ArrayList();
        long millions = n / ((long) DurationKt.NANOS_IN_MILLIS);
        long j = 1000;
        long thousands = (n / j) % j;
        long rest = n % j;
        if (millions > 0) {
            parts.add(counted(millions, new Unit(false, "миллион", "миллиона", "миллионов")));
        }
        if (thousands > 0) {
            parts.add(counted(thousands, new Unit(true, "тысяча", "тысячи", "тысяч")));
        }
        if (rest > 0) {
            parts.add(under1000((int) rest, feminine));
        }
        return CollectionsKt.joinToString$default(parts, StringUtils.SPACE, null, null, 0, null, null, 62, null);
    }

    private final String under1000(int n, boolean feminine) {
        String str;
        ArrayList out = new ArrayList();
        if (n >= 100) {
            out.add(HUNDREDS.get(n / 100));
        }
        int r = n % 100;
        boolean z = false;
        if (10 <= r && r < 20) {
            z = true;
        }
        if (z) {
            out.add(TEENS.get(r - 10));
        } else {
            if (r >= 20) {
                out.add(TENS.get(r / 10));
            }
            int u = r % 10;
            if (u > 0) {
                ArrayList arrayList = out;
                if (feminine && u == 1) {
                    str = "одна";
                } else {
                    str = (feminine && u == 2) ? "две" : ONES_M.get(u);
                }
                arrayList.add(str);
            }
        }
        return CollectionsKt.joinToString$default(out, StringUtils.SPACE, null, null, 0, null, null, 62, null);
    }

    private final String form(long n, Unit unit) {
        long r100 = n % ((long) 100);
        long r10 = n % ((long) 10);
        if (11 <= r100 && r100 < 15) {
            return unit.getMany();
        }
        if (r10 == 1) {
            return unit.getOne();
        }
        return 2 <= r10 && r10 < 5 ? unit.getFew() : unit.getMany();
    }

    private final String counted(long n, Unit unit) {
        return cardinal(n, unit.getFeminine()) + StringUtils.SPACE + form(n, unit);
    }

    private final String time(int hour, int minute) {
        String h = cardinal$default(this, hour, false, 2, null);
        if (minute == 0) {
            return h + " ноль ноль";
        }
        if (minute < 10) {
            return h + " ноль " + cardinal$default(this, minute, false, 2, null);
        }
        return h + StringUtils.SPACE + cardinal(minute, true);
    }

    private final String date(MatchResult m) {
        int day = Integer.parseInt(m.getGroupValues().get(1));
        if (!(1 <= day && day < 32)) {
            return m.getValue();
        }
        Integer year = StringsKt.toIntOrNull(m.getGroupValues().get(3));
        String head = ((Object) ORDINAL_DAY.get(day)) + StringUtils.SPACE + ((Object) m.getGroupValues().get(2));
        if (year == null) {
            return head;
        }
        return head + StringUtils.SPACE + yearGenitive(year.intValue()) + " года";
    }

    private final String yearGenitive(int year) {
        String last;
        int base = year - (year % 100);
        int r = year % 100;
        if (11 <= r && r < 20) {
            last = StringsKt.removeSuffix(TEENS.get(r - 10), (CharSequence) "ь") + "ого";
        } else if (r % 10 == 0) {
            last = YEAR_ORDINAL.get(Integer.valueOf((r / 10) * 10));
            if (last == null) {
                last = "";
            }
        } else {
            String[] strArr = new String[2];
            strArr[0] = TENS.get(r / 10);
            String str = YEAR_ORDINAL.get(Integer.valueOf(r % 10));
            strArr[1] = str != null ? str : "";
            Iterable $this$filter$iv = CollectionsKt.listOf((Object[]) strArr);
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                String it = (String) element$iv$iv;
                if (it.length() > 0) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            last = CollectionsKt.joinToString$default((List) destination$iv$iv, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        }
        Iterable $this$filter$iv2 = CollectionsKt.listOf((Object[]) new String[]{cardinal$default(this, base, false, 2, null), last});
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv2 : $this$filter$iv2) {
            String it2 = (String) element$iv$iv2;
            if (it2.length() > 0) {
                destination$iv$iv2.add(element$iv$iv2);
            }
        }
        return CollectionsKt.joinToString$default((List) destination$iv$iv2, StringUtils.SPACE, null, null, 0, null, null, 62, null);
    }

    private final String percent(MatchResult m) {
        String minus = m.getGroupValues().get(1).length() > 0 ? "минус " : "";
        long whole = Long.parseLong(m.getGroupValues().get(2));
        String frac = m.getGroupValues().get(3);
        if (!(frac.length() > 0)) {
            return minus + counted(whole, new Unit(false, "процент", "процента", "процентов"));
        }
        return minus + cardinal(whole, true) + " целых " + cardinal(Long.parseLong(frac), true) + " десятых процента";
    }

    private final String degrees(MatchResult m) {
        String minus = m.getGroupValues().get(1).length() > 0 ? "минус " : "";
        return minus + counted(Long.parseLong(m.getGroupValues().get(2)), new Unit(false, "градус", "градуса", "градусов"));
    }
}
