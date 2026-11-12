package com.jarvis.core.reminder;

import com.jarvis.core.nlp.RuNumbers;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: ReminderParser.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u001f B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00160\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00180\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u000b0\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000b0\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000b0\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000b0\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/jarvis/core/reminder/ReminderParser;", "", "<init>", "()V", "parse", "Lcom/jarvis/core/reminder/ReminderRequest;", "phrase", "", "now", "Ljava/time/LocalDateTime;", "DEFAULT_HOUR", "", "LAST_AMBIGUOUS_PM", "COLON", "Lkotlin/text/Regex;", "PREPOSITIONS", "", "HOUR_WORDS", "DAY_OFFSETS", "", "", "WEEKDAYS", "Ljava/time/DayOfWeek;", "PARTS", "Lcom/jarvis/core/reminder/ReminderParser$Part;", "DEFAULT_HOURS", "MONTHS", "ORDINALS", "HALF_HOURS", "LEADING_FILLERS", "TRAILING_FILLERS", "Part", "Scan", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ReminderParser {
    private static final int DEFAULT_HOUR = 9;
    private static final int LAST_AMBIGUOUS_PM = 6;
    public static final ReminderParser INSTANCE = new ReminderParser();
    private static final Regex COLON = new Regex("(\\d{1,2})[:.](\\d{2})");
    private static final Set<String> PREPOSITIONS = SetsKt.setOf((Object[]) new String[]{"в", "во", "на", "к"});
    private static final Set<String> HOUR_WORDS = SetsKt.setOf((Object[]) new String[]{"час", "часа", "часов"});
    private static final Map<String, Long> DAY_OFFSETS = MapsKt.mapOf(TuplesKt.to("сегодня", 0L), TuplesKt.to("завтра", 1L), TuplesKt.to("послезавтра", 2L));
    private static final Map<String, DayOfWeek> WEEKDAYS = MapsKt.mapOf(TuplesKt.to("понедельник", DayOfWeek.MONDAY), TuplesKt.to("вторник", DayOfWeek.TUESDAY), TuplesKt.to("среду", DayOfWeek.WEDNESDAY), TuplesKt.to("среда", DayOfWeek.WEDNESDAY), TuplesKt.to("четверг", DayOfWeek.THURSDAY), TuplesKt.to("пятницу", DayOfWeek.FRIDAY), TuplesKt.to("пятница", DayOfWeek.FRIDAY), TuplesKt.to("субботу", DayOfWeek.SATURDAY), TuplesKt.to("суббота", DayOfWeek.SATURDAY), TuplesKt.to("воскресенье", DayOfWeek.SUNDAY));
    private static final Map<String, Part> PARTS = MapsKt.mapOf(TuplesKt.to("утра", Part.MORNING), TuplesKt.to("утром", Part.MORNING), TuplesKt.to("утро", Part.MORNING), TuplesKt.to("дня", Part.DAY), TuplesKt.to("днём", Part.DAY), TuplesKt.to("днем", Part.DAY), TuplesKt.to("вечера", Part.EVENING), TuplesKt.to("вечером", Part.EVENING), TuplesKt.to("вечер", Part.EVENING), TuplesKt.to("ночи", Part.NIGHT), TuplesKt.to("ночью", Part.NIGHT));
    private static final Map<Part, Integer> DEFAULT_HOURS = MapsKt.mapOf(TuplesKt.to(Part.MORNING, 9), TuplesKt.to(Part.DAY, 13), TuplesKt.to(Part.EVENING, 19), TuplesKt.to(Part.NIGHT, 23));
    private static final Map<String, Integer> MONTHS = MapsKt.mapOf(TuplesKt.to("января", 1), TuplesKt.to("февраля", 2), TuplesKt.to("марта", 3), TuplesKt.to("апреля", 4), TuplesKt.to("мая", 5), TuplesKt.to("июня", 6), TuplesKt.to("июля", 7), TuplesKt.to("августа", 8), TuplesKt.to("сентября", 9), TuplesKt.to("октября", 10), TuplesKt.to("ноября", 11), TuplesKt.to("декабря", 12));
    private static final Map<String, Integer> ORDINALS = MapsKt.mapOf(TuplesKt.to("первого", 1), TuplesKt.to("второго", 2), TuplesKt.to("третьего", 3), TuplesKt.to("четвёртого", 4), TuplesKt.to("четвертого", 4), TuplesKt.to("пятого", 5), TuplesKt.to("шестого", 6), TuplesKt.to("седьмого", 7), TuplesKt.to("восьмого", 8), TuplesKt.to("девятого", 9), TuplesKt.to("десятого", 10), TuplesKt.to("одиннадцатого", 11), TuplesKt.to("двенадцатого", 12), TuplesKt.to("тринадцатого", 13), TuplesKt.to("четырнадцатого", 14), TuplesKt.to("пятнадцатого", 15), TuplesKt.to("шестнадцатого", 16), TuplesKt.to("семнадцатого", 17), TuplesKt.to("восемнадцатого", 18), TuplesKt.to("девятнадцатого", 19), TuplesKt.to("двадцатого", 20), TuplesKt.to("тридцатого", 30));
    private static final Map<String, Integer> HALF_HOURS = MapsKt.mapOf(TuplesKt.to("полпервого", 1), TuplesKt.to("полвторого", 2), TuplesKt.to("полтретьего", 3), TuplesKt.to("полчетвёртого", 4), TuplesKt.to("полчетвертого", 4), TuplesKt.to("полпятого", 5), TuplesKt.to("полшестого", 6), TuplesKt.to("полседьмого", 7), TuplesKt.to("полвосьмого", 8), TuplesKt.to("полдевятого", 9), TuplesKt.to("полдесятого", 10), TuplesKt.to("пол-одиннадцатого", 11), TuplesKt.to("полодиннадцатого", 11), TuplesKt.to("полдвенадцатого", 12));
    private static final Set<String> LEADING_FILLERS = SetsKt.setOf((Object[]) new String[]{"джарвис", "поставь", "создай", "сделай", "установи", "добавь", "напомни", "напомните", "напомнить", "напоминание", "напоминалку", "напоминай", "мне", "пожалуйста", "что", "чтобы", "надо", "нужно", "сэр"});
    private static final Set<String> TRAILING_FILLERS = SetsKt.setOf((Object[]) new String[]{"пожалуйста", "мне", "сэр", "в", "на", "к"});

    /* JADX INFO: compiled from: ReminderParser.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/jarvis/core/reminder/ReminderParser$Part;", "", "<init>", "(Ljava/lang/String;I)V", "MORNING", "DAY", "EVENING", "NIGHT", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private enum Part {
        MORNING,
        DAY,
        EVENING,
        NIGHT;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<Part> getEntries() {
            return $ENTRIES;
        }
    }

    private ReminderParser() {
    }

    public final ReminderRequest parse(String phrase, LocalDateTime now) {
        Intrinsics.checkNotNullParameter(phrase, "phrase");
        Intrinsics.checkNotNullParameter(now, "now");
        return new Scan(phrase, now).run();
    }

    /* JADX INFO: compiled from: ReminderParser.kt */
    @Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0018\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\u0018\u001a\u00020\u0019J\u0014\u0010\u001a\u001a\u00020\u001b2\n\u0010\u001c\u001a\u00020\u001d\"\u00020\u0017H\u0002J\u0010\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\u0017H\u0002J\u001e\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u00162\u0006\u0010!\u001a\u00020\u0017H\u0002J\n\u0010\"\u001a\u0004\u0018\u00010\u0005H\u0002J\u0014\u0010#\u001a\u0004\u0018\u00010$2\b\u0010%\u001a\u0004\u0018\u00010\u0003H\u0002J\b\u0010&\u001a\u00020\u001bH\u0002J\b\u0010'\u001a\u00020\u001bH\u0002J\u0010\u0010(\u001a\u00020\u001b2\u0006\u0010)\u001a\u00020\u0017H\u0002J\b\u0010*\u001a\u00020\u001bH\u0002J\u0018\u0010+\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\u00172\u0006\u0010,\u001a\u00020\u0003H\u0002J\b\u0010-\u001a\u00020\u001bH\u0002J\n\u0010.\u001a\u0004\u0018\u00010\u0005H\u0002J\u0010\u0010/\u001a\u00020\u00112\u0006\u00100\u001a\u00020\u0013H\u0002J\"\u00101\u001a\u00020\u00052\u0006\u00102\u001a\u00020\u00172\u0006\u00103\u001a\u00020\u00172\b\u00104\u001a\u0004\u0018\u00010\u0011H\u0002J\"\u00105\u001a\u00020\u00052\b\u00104\u001a\u0004\u0018\u00010\u00112\u0006\u00102\u001a\u00020\u00172\u0006\u00103\u001a\u00020\u0017H\u0002J \u00106\u001a\u00020\u00052\u0006\u00104\u001a\u00020\u00112\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\rH\u0002J\b\u0010:\u001a\u00020\u0003H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006;"}, d2 = {"Lcom/jarvis/core/reminder/ReminderParser$Scan;", "", "phrase", "", "now", "Ljava/time/LocalDateTime;", "<init>", "(Ljava/lang/String;Ljava/time/LocalDateTime;)V", "tokens", "", "used", "", "daily", "", "part", "Lcom/jarvis/core/reminder/ReminderParser$Part;", "date", "Ljava/time/LocalDate;", "weekday", "Ljava/time/DayOfWeek;", "today", "clock", "Lkotlin/Pair;", "", "run", "Lcom/jarvis/core/reminder/ReminderRequest;", "take", "", "indices", "", "takePreposition", "i", "numberAt", "start", "relative", "unitOf", "Ljava/time/Duration;", "word", "scanDaily", "scanDays", "calendarDate", "month", "scanClock", "spokenTime", "prep", "scanParts", "absolute", "dayFor", "dow", "atClock", "hour", "minute", "day", "onDay", "rollForward", "time", "Ljava/time/LocalTime;", "exact", "task", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Scan {
        private Pair<Integer, Integer> clock;
        private boolean daily;
        private LocalDate date;
        private final LocalDateTime now;
        private Part part;
        private boolean today;
        private final List<String> tokens;
        private final boolean[] used;
        private DayOfWeek weekday;

        /* JADX INFO: compiled from: ReminderParser.kt */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Part.values().length];
                try {
                    iArr[Part.MORNING.ordinal()] = 1;
                } catch (NoSuchFieldError e) {
                }
                try {
                    iArr[Part.DAY.ordinal()] = 2;
                } catch (NoSuchFieldError e2) {
                }
                try {
                    iArr[Part.EVENING.ordinal()] = 3;
                } catch (NoSuchFieldError e3) {
                }
                try {
                    iArr[Part.NIGHT.ordinal()] = 4;
                } catch (NoSuchFieldError e4) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public Scan(String phrase, LocalDateTime now) {
            Intrinsics.checkNotNullParameter(phrase, "phrase");
            Intrinsics.checkNotNullParameter(now, "now");
            this.now = now;
            String lowerCase = phrase.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            Iterable $this$map$iv = new Regex("\\s+").split(lowerCase, 0);
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                String it = (String) item$iv$iv;
                destination$iv$iv.add(StringsKt.trim(it, ',', '.', '!', '?', ';', ':', 171, 187, Typography.quote));
            }
            Iterable $this$filter$iv = (List) destination$iv$iv;
            Collection destination$iv$iv2 = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                String it2 = (String) element$iv$iv;
                if (it2.length() > 0) {
                    destination$iv$iv2.add(element$iv$iv);
                }
            }
            this.tokens = (List) destination$iv$iv2;
            this.used = new boolean[this.tokens.size()];
        }

        public final ReminderRequest run() {
            LocalDateTime relative = relative();
            scanDaily();
            scanDays();
            scanClock();
            scanParts();
            return new ReminderRequest(relative == null ? absolute() : relative, task(), this.daily);
        }

        private final void take(int... indices) {
            for (int element$iv : indices) {
                this.used[element$iv] = true;
            }
        }

        private final void takePreposition(int i) {
            if (CollectionsKt.contains(ReminderParser.PREPOSITIONS, CollectionsKt.getOrNull(this.tokens, i - 1))) {
                this.used[i - 1] = true;
            }
        }

        private final Pair<Integer, Integer> numberAt(int start) {
            Integer intOrNull;
            String str = (String) CollectionsKt.getOrNull(this.tokens, start);
            if (str != null && (intOrNull = StringsKt.toIntOrNull(str)) != null) {
                int it = intOrNull.intValue();
                return TuplesKt.to(Integer.valueOf(it), Integer.valueOf(start + 1));
            }
            Integer value = null;
            int i = start;
            while (i < this.tokens.size() && StringsKt.toIntOrNull(this.tokens.get(i)) == null) {
                Integer numEndingAt = RuNumbers.INSTANCE.endingAt(this.tokens.subList(i, i + 1), 0);
                if (numEndingAt == null) {
                    break;
                }
                int own = numEndingAt.intValue();
                int total = (value != null ? value.intValue() : 0) + own;
                Integer numEndingAt2 = RuNumbers.INSTANCE.endingAt(this.tokens.subList(start, i + 1), i - start);
                if (numEndingAt2 == null || numEndingAt2.intValue() != total) {
                    break;
                }
                value = Integer.valueOf(total);
                i++;
            }
            if (value == null) {
                return null;
            }
            int it2 = value.intValue();
            return TuplesKt.to(Integer.valueOf(it2), Integer.valueOf(i));
        }

        private final LocalDateTime relative() {
            long minutes;
            int start = this.tokens.indexOf("через");
            if (start < 0) {
                return null;
            }
            int i = start + 1;
            Duration total = Duration.ZERO;
            while (true) {
                if (i >= this.tokens.size()) {
                    break;
                }
                if (Intrinsics.areEqual(this.tokens.get(i), "и")) {
                    i++;
                } else if (Intrinsics.areEqual(this.tokens.get(i), "полчаса")) {
                    total = total.plus(Duration.ofMinutes(30L));
                    i++;
                } else {
                    boolean fraction = Intrinsics.areEqual(this.tokens.get(i), "полтора") || Intrinsics.areEqual(this.tokens.get(i), "полторы");
                    boolean pair = Intrinsics.areEqual(this.tokens.get(i), "пару");
                    Pair<Integer, Integer> pairNumberAt = (fraction || pair) ? null : numberAt(i);
                    int unitAt = (fraction || pair) ? i + 1 : pairNumberAt != null ? pairNumberAt.getSecond().intValue() : i;
                    Duration unit = unitOf((String) CollectionsKt.getOrNull(this.tokens, unitAt));
                    if (unit == null) {
                        break;
                    }
                    if (fraction) {
                        minutes = (unit.toMinutes() * ((long) 3)) / ((long) 2);
                    } else if (pair) {
                        minutes = unit.toMinutes() * ((long) 2);
                    } else {
                        minutes = unit.toMinutes() * ((long) (pairNumberAt != null ? pairNumberAt.getFirst().intValue() : 1));
                    }
                    long minutes2 = minutes;
                    total = total.plus(Duration.ofMinutes(minutes2));
                    if (unit.compareTo(Duration.ofMinutes(1L)) < 0) {
                        total = total.plus(unit.multipliedBy(pairNumberAt != null ? pairNumberAt.getFirst().intValue() : 1));
                    }
                    i = unitAt + 1;
                }
            }
            if (total.isZero()) {
                return null;
            }
            for (int k = start; k < i; k++) {
                this.used[k] = true;
            }
            return this.now.plus((TemporalAmount) total).truncatedTo(ChronoUnit.SECONDS);
        }

        private final Duration unitOf(String word) {
            if (word == null) {
                return null;
            }
            if (StringsKt.startsWith$default(word, "минут", false, 2, (Object) null)) {
                return Duration.ofMinutes(1L);
            }
            if (StringsKt.startsWith$default(word, "секунд", false, 2, (Object) null)) {
                return Duration.ofSeconds(1L);
            }
            if (Intrinsics.areEqual(word, "час") || Intrinsics.areEqual(word, "часа") || Intrinsics.areEqual(word, "часов")) {
                return Duration.ofHours(1L);
            }
            if (!Intrinsics.areEqual(word, "день") && !Intrinsics.areEqual(word, "дня") && !Intrinsics.areEqual(word, "дней") && !Intrinsics.areEqual(word, "сутки")) {
                if (StringsKt.startsWith$default(word, "недел", false, 2, (Object) null)) {
                    return Duration.ofDays(7L);
                }
                return null;
            }
            return Duration.ofDays(1L);
        }

        private final void scanDaily() {
            int size = this.tokens.size();
            for (int i = 0; i < size; i++) {
                if (Intrinsics.areEqual(this.tokens.get(i), "ежедневно")) {
                    this.daily = true;
                    take(i);
                } else if (Intrinsics.areEqual(this.tokens.get(i), "каждый") || Intrinsics.areEqual(this.tokens.get(i), "каждое")) {
                    String next = (String) CollectionsKt.getOrNull(this.tokens, i + 1);
                    Part everyPart = (Part) ReminderParser.PARTS.get(next);
                    if (Intrinsics.areEqual(next, "день") || everyPart != null) {
                        this.daily = true;
                        take(i, i + 1);
                        if (everyPart != null) {
                            this.part = everyPart;
                        }
                    }
                }
            }
        }

        private final void scanDays() {
            int size = this.tokens.size();
            for (int i = 0; i < size; i++) {
                if (!this.used[i]) {
                    String word = this.tokens.get(i);
                    Long offset = (Long) ReminderParser.DAY_OFFSETS.get(word);
                    DayOfWeek dow = (DayOfWeek) ReminderParser.WEEKDAYS.get(word);
                    if (offset != null) {
                        this.date = this.now.toLocalDate().plusDays(offset.longValue());
                        this.today = offset.longValue() == 0;
                        take(i);
                        takePreposition(i);
                    } else if (dow == null || !CollectionsKt.contains(ReminderParser.PREPOSITIONS, CollectionsKt.getOrNull(this.tokens, i - 1))) {
                        if (ReminderParser.MONTHS.containsKey(word)) {
                            calendarDate(i);
                        }
                    } else {
                        this.weekday = dow;
                        take(i);
                        takePreposition(i);
                    }
                }
            }
        }

        private final void calendarDate(int month) {
            Object objM392constructorimpl;
            String before = (String) CollectionsKt.getOrNull(this.tokens, month - 1);
            if (before == null) {
                return;
            }
            int first = month - 1;
            Object intOrNull = StringsKt.toIntOrNull(before);
            if (intOrNull == null) {
                intOrNull = (Integer) ReminderParser.ORDINALS.get(before);
            }
            Object day = intOrNull;
            if (day != null && ((Number) day).intValue() < 10 && Intrinsics.areEqual(CollectionsKt.getOrNull(this.tokens, month - 2), "двадцать")) {
                day = Integer.valueOf(((Number) day).intValue() + 20);
                first--;
            }
            if (day != null && ((Number) day).intValue() <= 1 && Intrinsics.areEqual(CollectionsKt.getOrNull(this.tokens, month - 2), "тридцать")) {
                day = Integer.valueOf(((Number) day).intValue() + 30);
                first--;
            }
            if (day != null) {
                int iIntValue = ((Number) day).intValue();
                boolean z = false;
                if (1 <= iIntValue && iIntValue < 32) {
                    z = true;
                }
                if (!z) {
                    return;
                }
                try {
                    Result.Companion companion = Result.INSTANCE;
                    Scan $this$calendarDate_u24lambda_u240 = this;
                    objM392constructorimpl = Result.m392constructorimpl($this$calendarDate_u24lambda_u240.now.toLocalDate().withMonth(((Number) MapsKt.getValue(ReminderParser.MONTHS, $this$calendarDate_u24lambda_u240.tokens.get(month))).intValue()).withDayOfMonth(((Number) day).intValue()));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m398isFailureimpl(objM392constructorimpl)) {
                    objM392constructorimpl = null;
                }
                LocalDate parsed = (LocalDate) objM392constructorimpl;
                if (parsed == null) {
                    return;
                }
                this.date = parsed.compareTo((ChronoLocalDate) this.now.toLocalDate()) < 0 ? parsed.plusYears(1L) : parsed;
                int k = first;
                if (k <= month) {
                    while (true) {
                        this.used[k] = true;
                        if (k == month) {
                            break;
                        } else {
                            k++;
                        }
                    }
                }
                takePreposition(first);
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code duplicated, block: B:45:0x012b  */
        /* JADX WARN: Code duplicated, block: B:47:0x0138  */
        /* JADX WARN: Code duplicated, block: B:49:0x013e  */
        /* JADX WARN: Code duplicated, block: B:50:0x0141  */
        /* JADX WARN: Code duplicated, block: B:56:0x015d  */
        /* JADX WARN: Code duplicated, block: B:73:0x0184 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:77:0x0185 A[SYNTHETIC] */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        private final void scanClock() {
            int i;
            int iValueOf;
            int size = this.tokens.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (!this.used[i2]) {
                    String word = this.tokens.get(i2);
                    MatchResult m = ReminderParser.COLON.matchEntire(word);
                    if (m != null) {
                        int h = Integer.parseInt(m.getGroupValues().get(1));
                        int min = Integer.parseInt(m.getGroupValues().get(2));
                        if (h <= 24 && min < 60) {
                            this.clock = TuplesKt.to(Integer.valueOf(h % 24), Integer.valueOf(min));
                            take(i2);
                            takePreposition(i2);
                            return;
                        }
                    }
                    Integer halfOf = (Integer) ReminderParser.HALF_HOURS.get(word);
                    if (halfOf != null) {
                        this.clock = TuplesKt.to(Integer.valueOf(halfOf.intValue() - 1), 30);
                        take(i2);
                        takePreposition(i2);
                        return;
                    }
                    if (Intrinsics.areEqual(word, "половине") && ReminderParser.ORDINALS.containsKey(CollectionsKt.getOrNull(this.tokens, i2 + 1))) {
                        this.clock = TuplesKt.to(Integer.valueOf(((Number) MapsKt.getValue(ReminderParser.ORDINALS, this.tokens.get(i2 + 1))).intValue() - 1), 30);
                        take(i2, i2 + 1);
                        takePreposition(i2);
                        return;
                    }
                    String prep = (String) CollectionsKt.getOrNull(this.tokens, i2 - 1);
                    if (prep != null && ReminderParser.PREPOSITIONS.contains(prep) && !this.used[i2 - 1]) {
                        switch (word.hashCode()) {
                            case 33418642:
                                if (word.equals("обед")) {
                                    if (Intrinsics.areEqual(word, "полночь")) {
                                        iValueOf = 0;
                                    } else {
                                        if (Intrinsics.areEqual(word, "обед")) {
                                            i = 13;
                                        } else {
                                            i = 12;
                                        }
                                        iValueOf = Integer.valueOf(i);
                                    }
                                    this.clock = TuplesKt.to(iValueOf, 0);
                                    take(i2);
                                    takePreposition(i2);
                                }
                                if (!Intrinsics.areEqual(word, "час") && Intrinsics.areEqual(prep, "в")) {
                                    this.clock = TuplesKt.to(1, 0);
                                    take(i2);
                                    takePreposition(i2);
                                } else if (spokenTime(i2, prep)) {
                                }
                                break;
                            case 408644652:
                                if (word.equals("полдень")) {
                                    if (Intrinsics.areEqual(word, "полночь")) {
                                        iValueOf = 0;
                                    } else {
                                        if (Intrinsics.areEqual(word, "обед")) {
                                            i = 13;
                                        } else {
                                            i = 12;
                                        }
                                        iValueOf = Integer.valueOf(i);
                                    }
                                    this.clock = TuplesKt.to(iValueOf, 0);
                                    take(i2);
                                    takePreposition(i2);
                                }
                                if (!Intrinsics.areEqual(word, "час")) {
                                }
                                if (spokenTime(i2, prep)) {
                                }
                                break;
                            case 408921730:
                                if (word.equals("полночь")) {
                                    if (Intrinsics.areEqual(word, "полночь")) {
                                        iValueOf = 0;
                                    } else {
                                        if (Intrinsics.areEqual(word, "обед")) {
                                            i = 13;
                                        } else {
                                            i = 12;
                                        }
                                        iValueOf = Integer.valueOf(i);
                                    }
                                    this.clock = TuplesKt.to(iValueOf, 0);
                                    take(i2);
                                    takePreposition(i2);
                                }
                                if (!Intrinsics.areEqual(word, "час")) {
                                }
                                if (spokenTime(i2, prep)) {
                                }
                                break;
                            default:
                                if (!Intrinsics.areEqual(word, "час")) {
                                }
                                if (spokenTime(i2, prep)) {
                                }
                                break;
                        }
                        return;
                    }
                }
            }
        }

        /* JADX WARN: Code duplicated, block: B:56:0x00e2  */
        private final boolean spokenTime(int i, String prep) {
            int next;
            int minute;
            int minute2;
            int i2;
            int afterHour;
            Pair<Integer, Integer> pairNumberAt = numberAt(i);
            if (pairNumberAt == null) {
                return false;
            }
            int hour = pairNumberAt.component1().intValue();
            int afterHour2 = pairNumberAt.component2().intValue();
            if (hour > 24) {
                return false;
            }
            int next2 = afterHour2;
            boolean hours = CollectionsKt.contains(ReminderParser.HOUR_WORDS, CollectionsKt.getOrNull(this.tokens, next2));
            if (hours) {
                next2++;
            }
            boolean evident = hours || ReminderParser.PARTS.containsKey(CollectionsKt.getOrNull(this.tokens, next2));
            if (!Intrinsics.areEqual(prep, "в") && !evident) {
                return false;
            }
            boolean zero = Intrinsics.areEqual(CollectionsKt.getOrNull(this.tokens, next2), "ноль");
            int minuteAt = zero ? next2 + 1 : next2;
            int minute3 = 0;
            Pair<Integer, Integer> pairNumberAt2 = numberAt(minuteAt);
            if (pairNumberAt2 != null) {
                int value = pairNumberAt2.component1().intValue();
                int after = pairNumberAt2.component2().intValue();
                if (value >= 0 && value < 60) {
                    if (!zero && value < 10) {
                        String str = (String) CollectionsKt.getOrNull(this.tokens, after);
                        if (str != null) {
                            next = next2;
                            afterHour = StringsKt.startsWith$default(str, "минут", false, 2, (Object) null) ? 1 : 0;
                            if (afterHour != 0) {
                            }
                        } else {
                            next = next2;
                        }
                        if (afterHour != 0) {
                        }
                    }
                    next2 = after;
                    String str2 = (String) CollectionsKt.getOrNull(this.tokens, next2);
                    if (str2 != null) {
                        minute = value;
                        minute2 = 0;
                        if (StringsKt.startsWith$default(str2, "минут", false, 2, (Object) null)) {
                            i2 = 1;
                        }
                        if (i2 != 0) {
                            next2++;
                        }
                        minute3 = minute;
                    } else {
                        minute = value;
                        minute2 = 0;
                    }
                    i2 = minute2;
                    if (i2 != 0) {
                        next2++;
                    }
                    minute3 = minute;
                } else {
                    next = next2;
                }
                next2 = next;
            }
            for (int k = i; k < next2; k++) {
                this.used[k] = true;
            }
            takePreposition(i);
            this.clock = TuplesKt.to(Integer.valueOf(hour % 24), Integer.valueOf(minute3));
            return true;
        }

        private final void scanParts() {
            int size = this.tokens.size();
            for (int i = 0; i < size; i++) {
                Part found = (Part) ReminderParser.PARTS.get(this.tokens.get(i));
                if (found != null && !this.used[i]) {
                    this.part = found;
                    take(i);
                }
            }
        }

        private final LocalDateTime absolute() {
            LocalDate exactDay = this.date;
            if (exactDay == null) {
                DayOfWeek it = this.weekday;
                exactDay = it != null ? dayFor(it) : null;
            }
            Pair<Integer, Integer> pair = this.clock;
            if (pair != null) {
                return atClock(pair.getFirst().intValue(), pair.getSecond().intValue(), exactDay);
            }
            Part it2 = this.part;
            Integer defaultHour = it2 != null ? Integer.valueOf(((Number) MapsKt.getValue(ReminderParser.DEFAULT_HOURS, it2)).intValue()) : null;
            if (defaultHour != null) {
                LocalDate localDate = exactDay == null ? this.now.toLocalDate() : exactDay;
                Intrinsics.checkNotNull(localDate);
                LocalTime localTimeOf = LocalTime.of(defaultHour.intValue(), 0);
                Intrinsics.checkNotNullExpressionValue(localTimeOf, "of(...)");
                return rollForward(localDate, localTimeOf, exactDay != null);
            }
            if (this.today) {
                return null;
            }
            if (exactDay != null) {
                return exactDay.atTime(9, 0);
            }
            if (!this.daily) {
                return null;
            }
            LocalDate localDate2 = this.now.toLocalDate();
            Intrinsics.checkNotNullExpressionValue(localDate2, "toLocalDate(...)");
            LocalTime localTimeOf2 = LocalTime.of(9, 0);
            Intrinsics.checkNotNullExpressionValue(localTimeOf2, "of(...)");
            return rollForward(localDate2, localTimeOf2, false);
        }

        private final LocalDate dayFor(DayOfWeek dow) {
            int ahead = ((dow.getValue() - this.now.getDayOfWeek().getValue()) + 7) % 7;
            LocalDate localDatePlusDays = this.now.toLocalDate().plusDays(ahead);
            Intrinsics.checkNotNullExpressionValue(localDatePlusDays, "plusDays(...)");
            return localDatePlusDays;
        }

        /* JADX WARN: Code duplicated, block: B:16:0x0027  */
        private final LocalDateTime atClock(int hour, int minute, LocalDate day) {
            Part p = this.part;
            int i = 0;
            if (p != null) {
                switch (WhenMappings.$EnumSwitchMapping$0[p.ordinal()]) {
                    case 1:
                        i = hour % 12;
                        int fixed = i;
                        return onDay(day, fixed, minute);
                    case 2:
                    case 3:
                        if (1 <= hour && hour < 12) {
                            i = hour + 12;
                        } else {
                            i = hour;
                        }
                        int fixed2 = i;
                        return onDay(day, fixed2, minute);
                    case 4:
                        if (hour != 12) {
                            i = hour;
                        }
                        int fixed3 = i;
                        return onDay(day, fixed3, minute);
                    default:
                        throw new NoWhenBranchMatchedException();
                }
            }
            if (!(1 <= hour && hour < 13)) {
                return onDay(day, hour, minute);
            }
            if (day != null && !this.today) {
                LocalDateTime localDateTimeAtTime = day.atTime(hour <= 6 ? hour + 12 : hour, minute);
                Intrinsics.checkNotNullExpressionValue(localDateTimeAtTime, "atTime(...)");
                return localDateTimeAtTime;
            }
            LocalDate base = this.now.toLocalDate();
            LocalDateTime first = base.atTime(hour % 12, minute);
            LocalDateTime second = first.plusHours(12L);
            if (hour != 12 && first.compareTo((ChronoLocalDateTime<?>) this.now) > 0) {
                Intrinsics.checkNotNull(first);
                return first;
            }
            if (hour == 12 && second.compareTo((ChronoLocalDateTime<?>) this.now) > 0) {
                Intrinsics.checkNotNull(second);
                return second;
            }
            if (second.compareTo((ChronoLocalDateTime<?>) this.now) > 0 && hour != 12) {
                Intrinsics.checkNotNull(second);
                return second;
            }
            LocalDateTime localDateTimeAtTime2 = base.plusDays(1L).atTime(hour <= 6 ? hour + 12 : hour, minute);
            Intrinsics.checkNotNullExpressionValue(localDateTimeAtTime2, "atTime(...)");
            return localDateTimeAtTime2;
        }

        private final LocalDateTime onDay(LocalDate day, int hour, int minute) {
            LocalDate localDate = day == null ? this.now.toLocalDate() : day;
            Intrinsics.checkNotNull(localDate);
            LocalTime localTimeOf = LocalTime.of(hour, minute);
            Intrinsics.checkNotNullExpressionValue(localTimeOf, "of(...)");
            return rollForward(localDate, localTimeOf, (day == null || this.today) ? false : true);
        }

        private final LocalDateTime rollForward(LocalDate day, LocalTime time, boolean exact) {
            LocalDateTime at = day.atTime(time);
            if (exact && this.weekday == null) {
                Intrinsics.checkNotNull(at);
                return at;
            }
            if (at.compareTo((ChronoLocalDateTime<?>) this.now) > 0) {
                Intrinsics.checkNotNull(at);
                return at;
            }
            LocalDateTime localDateTimePlusDays = at.plusDays(this.weekday != null ? 7L : 1L);
            Intrinsics.checkNotNullExpressionValue(localDateTimePlusDays, "plusDays(...)");
            return localDateTimePlusDays;
        }

        private final String task() {
            Iterable $this$filter$iv = CollectionsKt.getIndices(this.tokens);
            Collection destination$iv$iv = new ArrayList();
            for (Integer num : $this$filter$iv) {
                int it = num.intValue();
                if (!this.used[it]) {
                    destination$iv$iv.add(num);
                }
            }
            Iterable $this$map$iv = (List) destination$iv$iv;
            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                int it2 = ((Number) item$iv$iv).intValue();
                destination$iv$iv2.add(this.tokens.get(it2));
            }
            List rest = CollectionsKt.toMutableList(destination$iv$iv2);
            while (!rest.isEmpty() && ReminderParser.LEADING_FILLERS.contains(CollectionsKt.first(rest))) {
                rest.remove(0);
            }
            while (rest.size() >= 2 && Intrinsics.areEqual(rest.get(0), "о") && Intrinsics.areEqual(rest.get(1), "том")) {
                for (int i = 0; i < 2; i++) {
                    rest.remove(0);
                }
            }
            while (!rest.isEmpty() && ReminderParser.LEADING_FILLERS.contains(CollectionsKt.first(rest))) {
                rest.remove(0);
            }
            while (!rest.isEmpty() && ReminderParser.TRAILING_FILLERS.contains(CollectionsKt.last(rest))) {
                rest.remove(CollectionsKt.getLastIndex(rest));
            }
            return CollectionsKt.joinToString$default(rest, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        }
    }
}
