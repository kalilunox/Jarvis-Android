package com.jarvis.brain;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.actions.SearchIntents;
import com.jarvis.app.VoiceService;
import com.jarvis.app.proactive.PhoneStateReader;
import com.jarvis.app.ui.JarvisActivity;
import com.jarvis.core.common.model.ConfidenceLevel;
import com.jarvis.core.common.model.Intent;
import com.jarvis.core.common.model.IntentType;
import com.jarvis.core.common.model.Slot;
import com.jarvis.core.common.model.UserRequest;
import com.jarvis.core.nlp.ContactPhraseNormalizer;
import com.jarvis.core.nlp.RuNumbers;
import com.jarvis.core.nlp.Transliterator;
import com.jarvis.core.notes.NoteParser;
import com.jarvis.core.notes.Places;
import com.jarvis.core.reminder.ReminderParser;
import com.jarvis.core.reminder.ReminderRequest;
import com.jarvis.core.routines.EventMatcher;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.AbstractCircuitBreaker;

/* JADX INFO: compiled from: RuleBasedBrain.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\u0018\u0000 _2\u00020\u0001:\u0001_B\u0017\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J,\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\fH\u0016J\u0018\u0010\u0010\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\u0018\u0010\u0012\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\u0018\u0010\u0013\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\u0018\u0010\u0015\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\u0018\u0010\u0016\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\u0018\u0010\u0017\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\u0018\u0010\u0018\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\u0018\u0010\u0019\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J \u0010\u001a\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\t\u001a\u00020\nH\u0002J \u0010\u001b\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\t\u001a\u00020\nH\u0002J \u0010\u001c\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\t\u001a\u00020\nH\u0002J \u0010\u001d\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\t\u001a\u00020\nH\u0002J(\u0010\u001e\u001a\u0004\u0018\u00010\b2\u0006\u0010\u001f\u001a\u00020\r2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\t\u001a\u00020\nH\u0002J \u0010 \u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\t\u001a\u00020\nH\u0002J \u0010!\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0018\u0010\"\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\u0010\u0010#\u001a\u00020\r2\u0006\u0010$\u001a\u00020\rH\u0002J\u0018\u0010%\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\u0018\u0010&\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J \u0010'\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\t\u001a\u00020\nH\u0002J \u0010(\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\t\u001a\u00020\nH\u0002J\u001e\u0010)\u001a\u00020\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010*\u001a\u00020\rH\u0002J \u0010+\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\t\u001a\u00020\nH\u0002J\u001e\u0010,\u001a\u00020\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010*\u001a\u00020\rH\u0002J\u0018\u0010-\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010*\u001a\u00020\rH\u0002J\u0016\u0010.\u001a\u00020\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\u001d\u0010/\u001a\u0004\u0018\u0001002\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002¢\u0006\u0002\u00101J\u001d\u00102\u001a\u0004\u0018\u0001002\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002¢\u0006\u0002\u00101J \u00103\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\rH\u0002J\u0010\u00106\u001a\u00020\u00042\u0006\u00105\u001a\u00020\rH\u0002J\u0010\u00107\u001a\u00020\r2\u0006\u00108\u001a\u00020\rH\u0002J \u00109\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\t\u001a\u00020\nH\u0002J \u0010:\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\t\u001a\u00020\nH\u0002J \u0010;\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\t\u001a\u00020\nH\u0002J \u0010<\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\t\u001a\u00020\nH\u0002J \u0010=\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\t\u001a\u00020\nH\u0002J \u0010>\u001a\u0004\u0018\u00010\r2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010*\u001a\u00020\rH\u0002J \u0010?\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\t\u001a\u00020\nH\u0002J \u0010@\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010A\u001a\u0002002\u0006\u0010B\u001a\u00020CH\u0002J \u0010D\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\t\u001a\u00020\nH\u0002J \u0010E\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\t\u001a\u00020\nH\u0002J$\u0010F\u001a\u00020\r2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010G\u001a\b\u0012\u0004\u0012\u00020\r0HH\u0002J \u0010I\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\t\u001a\u00020\nH\u0002J&\u0010J\u001a\u0004\u0018\u00010\r2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010K\u001a\b\u0012\u0004\u0012\u00020\r0HH\u0002J\u0012\u0010L\u001a\u0004\u0018\u00010\r2\u0006\u00108\u001a\u00020\rH\u0002J \u0010M\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\t\u001a\u00020\nH\u0002J \u0010N\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\t\u001a\u00020\nH\u0002J \u0010O\u001a\u0004\u0018\u00010\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010P\u001a\u0004\u0018\u00010\r2\u0006\u0010*\u001a\u00020\rH\u0002J\u0012\u0010Q\u001a\u0004\u0018\u00010\r2\u0006\u0010*\u001a\u00020\rH\u0002J4\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020U2\u0012\u0010V\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020X0W2\u0006\u0010*\u001a\u00020\r2\u0006\u0010Y\u001a\u00020ZH\u0002J\u001d\u0010[\u001a\u0004\u0018\u0001002\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002¢\u0006\u0002\u00101J\u001d\u0010\\\u001a\u0004\u0018\u0001002\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002¢\u0006\u0002\u00101J\u0016\u0010]\u001a\u00020\u00042\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\u0016\u0010^\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010*\u001a\u00020\rH\u0002R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006`"}, d2 = {"Lcom/jarvis/brain/RuleBasedBrain;", "Lcom/jarvis/brain/BrainRouter;", "cloudAvailable", "Lkotlin/Function0;", "", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "route", "Lcom/jarvis/brain/BrainResult;", "request", "Lcom/jarvis/core/common/model/UserRequest;", "history", "", "", "topSkills", "Lcom/jarvis/brain/BrainRouter$SkillInfo;", "infoRequest", "words", "announceSettings", "replyToMessage", "forget", "briefing", "camera", "navigate", "webSearch", "systemStatus", "callHistory", "smsRead", "calendarList", "flashLight", "volume", "rawText", "brightness", "personalData", "appAction", "siteAddress", "spoken", "selfDirected", "webAnswer", "scheduledCommand", NotificationCompat.CATEGORY_REMINDER, "cancelReminder", VoiceService.EXTRA_TEXT, "timer", NotificationCompat.CATEGORY_STOPWATCH, "alarmAfter", NotificationCompat.CATEGORY_ALARM, "bareLevel", "", "(Ljava/util/List;)Ljava/lang/Integer;", "percentOf", "sendTelegram", "isDative", "w", "isTelegramToken", "normalizeChatName", "raw", "sendSms", "createEvent", "launchApp", "callPhone", "contacts", "settingName", "systemSetting", "uiActions", "daysUntil", "day", "Ljava/time/DayOfWeek;", "weather", "music", "extractAfter", "dropTokens", "", "knowledge", "extractCityAfter", "markers", "normalizeCity", "screenshot", "clipboard", "notifications", "extractNotificationReplyText", "extractNotificationQuery", "skill", "Lcom/jarvis/brain/BrainResult$SkillCall;", "type", "Lcom/jarvis/core/common/model/IntentType;", "slots", "", "", "confidence", "", "extractMinutes", "extractSeconds", "looksLikeCommand", "normalize", "Companion", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RuleBasedBrain implements BrainRouter {
    private static final int DAYS_IN_WEEK = 7;
    private static final int HOUR_AND_HALF_MINUTES = 90;
    private static final int MINUTE_AND_HALF_SECONDS = 90;
    private static final int MIN_BRIGHTNESS = 5;
    private final Function0<Boolean> cloudAvailable;
    private static final Set<String> LIGHT_NOUNS = SetsKt.setOf((Object[]) new String[]{"фонарик", "фонарика", "фонарику", "фонариком", "фонарике", "фонарь", "свет", "подсветка", "подсветку", "подсветки", "освещение", "освещения"});
    private static final Set<String> ON_VERBS = SetsKt.setOf((Object[]) new String[]{"включи", "включить", "включите", "включил", "зажги", "зажечь", "зажигай", "дай", "дайте", "сделай", "сделайте"});
    private static final Set<String> OFF_VERBS = SetsKt.setOf((Object[]) new String[]{"выключи", "выключить", "выключите", "погаси", "погасить", "потуши", "потушить", "отключи", "отключить"});
    private static final Set<String> VOLUME_UP = SetsKt.setOf((Object[]) new String[]{"громче", "прибавь", "прибавить", "увеличь", "увеличить", "погромче", "громкость", "звук", "повысь", "повысить", "прибавьте"});
    private static final Set<String> VOLUME_DOWN = SetsKt.setOf((Object[]) new String[]{"тише", "потише", "убавь", "убавить", "уменьши", "уменьшить", "приглуши", "приглушить", "сбавь", "сбавить"});
    private static final Set<String> VOLUME_MUTE = SetsKt.setOf((Object[]) new String[]{"выключи звук", "без звука", "заглуши", "заглушить", "мут", "мама звук"});
    private static final Set<String> VOLUME_UNMUTE = SetsKt.setOf((Object[]) new String[]{"включи звук", "верни звук", "размуть"});
    private static final Set<String> VOLUME_MAX = SetsKt.setOf((Object[]) new String[]{"максимальную", "максимальная", "на максимум", "громкость на макс"});
    private static final Set<String> ALARM_STATUS_WORDS = SetsKt.setOf((Object[]) new String[]{"какие", "какой", "когда", "покажи", "стоит", "ближайший", "следующий"});
    private static final Set<String> CITY_END_WORDS = SetsKt.setOf((Object[]) new String[]{"завтра", "послезавтра", "сегодня", "сейчас", "будет", "выходные", "неделю", "неделе"});
    private static final Map<String, String> CURRENCY_CODES = MapsKt.mapOf(TuplesKt.to("доллар", "USD"), TuplesKt.to("евро", "EUR"), TuplesKt.to("юан", "CNY"));
    private static final List<String> TOO_BRIGHT = CollectionsKt.listOf((Object[]) new String[]{"слишком ярко", "очень ярко", "глаза режет", "слепит"});
    private static final List<String> TOO_DARK = CollectionsKt.listOf((Object[]) new String[]{"слишком темно", "очень темно", "плохо видно экран", "экран тёмный", "экран темный"});
    private static final Set<String> BRIGHTNESS_NOUNS = SetsKt.setOf((Object[]) new String[]{"яркость", "ярче", "экран", "дисплей", "темнее", "тусклее"});
    private static final Set<String> BRIGHT_UP = SetsKt.setOf((Object[]) new String[]{"ярче", "прибавь", "прибавить", "увеличь", "увеличить", "максимальную", "максимальная", "поярче"});
    private static final Set<String> BRIGHT_DOWN = SetsKt.setOf((Object[]) new String[]{"темнее", "тусклее", "уменьши", "уменьшить", "приглуши", "приглушить"});
    private static final Set<String> TIMER_NOUNS = SetsKt.setOf((Object[]) new String[]{"таймер", "таймера", "таймере", "будильник", "секундомер"});
    private static final Set<String> TIMER_CANCEL_VERBS = SetsKt.setOf((Object[]) new String[]{"отмени", "отменить", "сбрось", "останови", "выключи", "убери", "удали", "отключи"});
    private static final Set<String> ALARM_PM_WORDS = SetsKt.setOf((Object[]) new String[]{"вечера", "вечером", "дня", "днём", "днем"});
    private static final Set<String> PERCENT_WORDS = SetsKt.setOf((Object[]) new String[]{"процент", "процента", "процентов"});
    private static final Regex SELF_DIRECTED = new Regex("^(?:сделай сам|сам сделай)\\s*(.*)$");
    private static final Set<String> MUSIC_PLAY_VERBS = SetsKt.setOf((Object[]) new String[]{"включи", "включить", "включите", "поставь", "играй"});
    private static final Regex MOOD_REQUEST = new Regex("что[- ](?:нибудь|то)(?:\\s+из\\s+музыки)?(?!\\s+от\\s)\\s*(.*)$");
    private static final LinkedHashMap<String, String> SETTINGS_SECTIONS = MapsKt.linkedMapOf(TuplesKt.to("батаре", "battery"), TuplesKt.to("экран", "display"), TuplesKt.to("дисплей", "display"), TuplesKt.to("звук", "sound"), TuplesKt.to("памят", "storage"), TuplesKt.to("хранилищ", "storage"), TuplesKt.to("приложени", "apps"), TuplesKt.to("геолокац", "location"), TuplesKt.to("местоположен", "location"), TuplesKt.to("уведомлен", "notifications"), TuplesKt.to("безопасност", "security"), TuplesKt.to("блютус", "bluetooth"), TuplesKt.to("блютуз", "bluetooth"), TuplesKt.to("bluetooth", "bluetooth"), TuplesKt.to("вайфа", EventMatcher.WIFI), TuplesKt.to("вай фа", EventMatcher.WIFI), TuplesKt.to(EventMatcher.WIFI, EventMatcher.WIFI), TuplesKt.to("wi fi", EventMatcher.WIFI));
    private static final List<String> STATUS_PHRASES = CollectionsKt.listOf((Object[]) new String[]{"статус систем", "состояние систем", "отчёт о систем", "отчет о систем", "доложи о систем", "диагностик", "состояние телефона", "статус телефона", "как там телефон", "сколько заряда", "какой заряд", "уровень заряда", "заряд батареи", "сколько батареи", "сколько памяти", "сколько места", "свободной памяти", "свободного места"});
    private static final Set<String> ANSWER_VERBS = SetsKt.setOf((Object[]) new String[]{"узнай", "выясни", "разузнай"});
    private static final List<Pair<Regex, String>> APP_PATTERNS = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(new Regex("^(?:включи|найди|открой|поставь|покажи)\\s+(?:на|в)\\s+(?:ютубе|ютьюбе|youtube)\\s+(.+)$|^(?:включи|найди|поставь|покажи)\\s+(.+?)\\s+(?:на|в)\\s+(?:ютубе|ютьюбе|youtube)$"), "youtube"), TuplesKt.to(new Regex("^(?:найди|покажи|открой)\\s+(?:на|в)\\s+(?:яндекс\\s+)?картах\\s+(.+)$|^(?:найди|покажи)\\s+(.+?)\\s+(?:на|в)\\s+(?:яндекс\\s+)?картах$"), "maps"), TuplesKt.to(new Regex("^(?:найди|поищи)\\s+(?:в|на)\\s+(?:плей маркете|гугл плей|play market)\\s+(.+)$"), "market"), TuplesKt.to(new Regex("^(?:вызови|закажи)\\s+такси()"), "taxi"), TuplesKt.to(new Regex("^(?:открой|зайди на)\\s+сайт\\s+(.+)$"), "site")});
    private static final Set<String> ANNOUNCE_VERBS = SetsKt.setOf((Object[]) new String[]{"объявляй", "озвучивай", "читай", "сообщай", "говори", "докладывай"});
    private static final Set<String> REPLY_FILLERS = SetsKt.setOf((Object[]) new String[]{"ему", "ей", "им", "что", "чтобы", "пожалуйста"});
    private static final Set<String> TIME_MARKERS = SetsKt.setOf((Object[]) new String[]{"через", "завтра", "послезавтра", "каждый", "каждое", "ежедневно", "утра", "утром", "вечера", "вечером"});
    private static final Set<String> NOT_SCHEDULED = SetsKt.setOf((Object[]) new String[]{"таймер", "будильник", "разбуди", "секундомер", "напоминай", "погода", "погоду"});
    private static final Set<String> SCHEDULED_VERBS = SetsKt.setOf((Object[]) new String[]{"включи", "выключи", "отключи", "поставь", "останови", "открой", "запусти", "сделай", "делай", "прибавь", "убавь", "докладывай", "читай"});
    private static final Set<String> APP_WORDS = SetsKt.setOf((Object[]) new String[]{"ютуб", "ютьюб", "youtube", "телеграм", "телеграмм", "вк", "вконтакте", "ватсап", "вотсап", "whatsapp", "тикток", "камеру", "браузер", "хром", "chrome", "карты", "яндекс карты", "навигатор", "часы", "галерею"});
    private static final Map<String, DayOfWeek> WEEKDAY_WORDS = MapsKt.mapOf(TuplesKt.to("понедельник", DayOfWeek.MONDAY), TuplesKt.to("вторник", DayOfWeek.TUESDAY), TuplesKt.to("среду", DayOfWeek.WEDNESDAY), TuplesKt.to("четверг", DayOfWeek.THURSDAY), TuplesKt.to("пятницу", DayOfWeek.FRIDAY), TuplesKt.to("субботу", DayOfWeek.SATURDAY), TuplesKt.to("воскресенье", DayOfWeek.SUNDAY));
    private static final LinkedHashMap<String, String> PHRASE_SETTINGS = MapsKt.linkedMapOf(TuplesKt.to("не беспокоить", PhoneStateReader.FAKE_DND), TuplesKt.to("беззвучн", "silent"), TuplesKt.to("вибрац", "vibrate"), TuplesKt.to("автояркост", "auto_brightness"), TuplesKt.to("авто яркост", "auto_brightness"), TuplesKt.to("автоповорот", "auto_rotate"), TuplesKt.to("поверни экран", "auto_rotate"), TuplesKt.to("поворот экрана", "auto_rotate"), TuplesKt.to("тёмную тему", "dark_mode"), TuplesKt.to("темную тему", "dark_mode"), TuplesKt.to("тёмная тема", "dark_mode"), TuplesKt.to("тёмный режим", "dark_mode"), TuplesKt.to("энергосбереж", "battery_saver"), TuplesKt.to("экономию заряда", "battery_saver"), TuplesKt.to("точку доступа", "hotspot"), TuplesKt.to("точка доступа", "hotspot"), TuplesKt.to("раздай интернет", "hotspot"), TuplesKt.to("раздачу интернета", "hotspot"), TuplesKt.to("мобильный интернет", "mobile_data"), TuplesKt.to("мобильные данные", "mobile_data"), TuplesKt.to("передачу данных", "mobile_data"));
    private static final Set<String> SWITCH_SETTINGS = SetsKt.setOf((Object[]) new String[]{PhoneStateReader.FAKE_DND, "silent", "vibrate", "auto_brightness", "auto_rotate"});
    private static final Set<String> NOTIFICATION_CLEAR_VERBS = SetsKt.setOf((Object[]) new String[]{"очисти", "очистить", "убери", "удали", "закрой", "смахни", "скрой"});
    private static final List<String> NEWS_PHRASES = CollectionsKt.listOf((Object[]) new String[]{"какие новости", "что нового в мире", "последние новости", "новости сегодня", "главные новости", "расскажи новости"});
    private static final List<String> RATE_PHRASES = CollectionsKt.listOf((Object[]) new String[]{"курс доллара", "курс евро", "курс юаня", "курс валют", "курсы валют", "сколько стоит доллар", "сколько стоит евро", "почём доллар", "почем доллар", "почём евро", "почем евро"});
    private static final List<String> LOCATION_PHRASES = CollectionsKt.listOf((Object[]) new String[]{"где я", "где я нахожусь", "где мы", "мой адрес", "моё местоположение", "мое местоположение"});
    private static final List<String> APP_LIST_PHRASES = CollectionsKt.listOf((Object[]) new String[]{"какие у меня", "какие приложения", "список приложений", "список установленных"});
    private static final Map<String, String> CALL_CONTROL = MapsKt.mapOf(TuplesKt.to("перезвони", "redial"), TuplesKt.to("перезвони ему", "redial"), TuplesKt.to("перезвони ей", "redial"), TuplesKt.to("набери ещё раз", "redial"), TuplesKt.to("ответь на звонок", "answer"), TuplesKt.to("ответь", "answer"), TuplesKt.to("прими звонок", "answer"), TuplesKt.to("возьми трубку", "answer"), TuplesKt.to("сбрось звонок", "end"), TuplesKt.to("сбрось", "end"), TuplesKt.to("отклони звонок", "end"), TuplesKt.to("положи трубку", "end"), TuplesKt.to("заверши звонок", "end"));
    private static final Set<String> FORGET_FILLERS = SetsKt.setOf((Object[]) new String[]{"про", "о", "об", "что", "это", "пожалуйста"});
    private static final List<String> BRIEFING_PHRASES = CollectionsKt.listOf((Object[]) new String[]{"сводк", "брифинг", "что у меня на сегодня", "что у меня сегодня", "что на сегодня", "планы на сегодня", "план на сегодня", "план на день", "планы на день"});
    private static final Set<String> PHOTO_VERBS = SetsKt.setOf((Object[]) new String[]{"сфотографируй", "сфоткай", "сфотай", "сфотографировать"});
    private static final List<String> PHOTO_PHRASES = CollectionsKt.listOf((Object[]) new String[]{"сделай фото", "сделай фотку", "сделай снимок", "сделай селфи", "сними фото"});
    private static final List<String> VIDEO_PHRASES = CollectionsKt.listOf((Object[]) new String[]{"сними видео", "запиши видео", "снять видео", "сделай видео"});
    private static final Regex NAVIGATION = new Regex("(?:маршрут|как доехать|как добраться|как проехать|как пройти|отвези меня|вези меня|навигаци\\S*|(?:покажи|найди|открой) на карте)\\s+(?:(?:до|в|во|на|к|ко)\\s+)?(.+)$|^поехали\\s+(?:(?:до|в|во|на|к|ко)\\s+)?(домой|\\S+\\s*\\S*)$");
    private static final Regex POST_GO = new Regex("^поехали (домой|до |в |во |на |к |ко )");
    private static final List<String> WEB_MARKERS = CollectionsKt.listOf((Object[]) new String[]{"в интернете", "в сети", "в яндексе", "в гугле", "в браузере"});
    private static final Set<String> SEARCH_VERBS = SetsKt.setOf((Object[]) new String[]{"найди", "поищи", "ищи", "загугли", "погугли", "посмотри", "открой"});
    private static final Set<String> SEARCH_FILLERS = SetsKt.setOf((Object[]) new String[]{"мне", "пожалуйста", "джарвис"});
    private static final Set<String> REMINDER_LIST_WORDS = SetsKt.setOf((Object[]) new String[]{"какие", "мои", "список", "покажи", "есть", "прочитай"});
    private static final Set<String> REMINDER_QUERY_FILLERS = SetsKt.setOf((Object[]) new String[]{"все", "всё", "мне", "про", "о", "об", "пожалуйста", "моё", "мое"});
    private static final Set<String> REMIND_VERBS = SetsKt.setOf((Object[]) new String[]{"напомни", "напомнить", "напоминание"});
    private static final Set<String> MINUTE_WORDS = SetsKt.setOf((Object[]) new String[]{"минут", "минуту", "минуты", "минута"});
    private static final Set<String> SECOND_WORDS = SetsKt.setOf((Object[]) new String[]{"секунд", "секунду", "секунды", "секунда"});
    private static final Set<String> HOUR_WORDS = SetsKt.setOf((Object[]) new String[]{"час", "часа", "часов"});
    private static final Set<String> EVENT_VERBS = SetsKt.setOf((Object[]) new String[]{"создай", "запланируй", "запиши", "создай заявку", "составь событие", "спланируй", "назначь"});
    private static final Set<String> EVENT_NOUNS = SetsKt.setOf((Object[]) new String[]{"событие", "события", "напоминание", "заявку"});
    private static final Set<String> SMS_VERBS = SetsKt.setOf((Object[]) new String[]{"напиши", "отправь", "передай", "сообщение", "смс", "напечатай", "отправить", "отправь смс"});
    private static final Set<String> TG_WRITE_VERBS = SetsKt.setOf((Object[]) new String[]{"напиши", "напечатай", "отправь", "передай"});
    private static final Set<String> LAUNCH_VERBS = SetsKt.setOf((Object[]) new String[]{"открой", "открыть", "запусти", "запустить", "включи приложение", "зайди", "перейди"});
    private static final Set<String> LAUNCH_FILLERS = SetsKt.setOf((Object[]) new String[]{"в", "во", "на", "мне", "пожалуйста", "приложение", "сейчас"});
    private static final Set<String> CALL_VERBS = SetsKt.setOf((Object[]) new String[]{"позвони", "набери", "вызови", "звонок"});
    private static final Set<String> CONTACT_WORDS = SetsKt.setOf((Object[]) new String[]{"контакт", "контакты", "контактe"});
    private static final Set<String> WIFI_WORDS = SetsKt.setOf((Object[]) new String[]{EventMatcher.WIFI, "wi-fi", "вайфай", "вай-фай"});
    private static final Set<String> BT_WORDS = SetsKt.setOf((Object[]) new String[]{"bluetooth", "блютус", "блютуз", "синезуб"});
    private static final Set<String> AIRPLANE_WORDS = SetsKt.setOf((Object[]) new String[]{"airplane", "авиарежим", "авиарежима"});
    private static final Set<String> SCREENSHOT_WORDS = SetsKt.setOf((Object[]) new String[]{"скриншот", "screenshot", "снимок"});
    private static final Set<String> WEATHER_WORDS = SetsKt.setOf((Object[]) new String[]{"погода", "погоду", "погоде", "погодой", "температура", "температуру", "дождь", "снег", "ветер", "прогноз", "облачно", "ясно"});
    private static final Set<String> MUSIC_NOUNS = SetsKt.setOf((Object[]) new String[]{"музыку", "музыка", "музыки", "плеер", "плеере", "spotify", "яндекс", "воспроизведение", "воспроизведения", "кион", "треки"});
    private static final Set<String> RESUME_VERBS = SetsKt.setOf((Object[]) new String[]{"продолжи", "продолжить", "продолжай", "возобнови", "возобновить", "продолжим", "продолжай"});
    private static final Set<String> KNOWLEDGE_VERBS = SetsKt.setOf((Object[]) new String[]{"расскажи", "рассказать", "объясни", "объяснить", "загугли"});
    private static final Set<String> COMMAND_VERBS = SetsKt.setOf((Object[]) new String[]{"включи", "выключи", "открой", "закрой", "сделай", "прибавь", "убавь", "напиши", "покажи", "запусти", "установи", "позвони", "отправь", "создай", "добавь", "удали", "прочитай", "переведи", "напомни", "нажми", "тапни", "пролистай", "скопируй"});

    /* JADX WARN: Multi-variable type inference failed */
    public RuleBasedBrain() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public RuleBasedBrain(Function0<Boolean> cloudAvailable) {
        Intrinsics.checkNotNullParameter(cloudAvailable, "cloudAvailable");
        this.cloudAvailable = cloudAvailable;
    }

    public /* synthetic */ RuleBasedBrain(Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new Function0() { // from class: com.jarvis.brain.RuleBasedBrain$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(RuleBasedBrain._init_$lambda$0());
            }
        } : function0);
    }

    @Override // com.jarvis.brain.BrainRouter
    public /* bridge */ BrainResult continueAfterTool(UserRequest originalRequest, List<String> list, List<BrainRouter.SkillInfo> list2, String lastToolId, String lastToolMessage) {
        return super.continueAfterTool(originalRequest, list, list2, lastToolId, lastToolMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean _init_$lambda$0() {
        return false;
    }

    @Override // com.jarvis.brain.BrainRouter
    public BrainResult route(UserRequest request, List<String> history, List<BrainRouter.SkillInfo> topSkills) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(history, "history");
        Intrinsics.checkNotNullParameter(topSkills, "topSkills");
        List<String> listNormalize = normalize(request.getText());
        if (listNormalize.isEmpty()) {
            return new BrainResult.Clarify("Не расслышал, сэр. Повторите, пожалуйста.", 0.3f);
        }
        BrainResult it = selfDirected(listNormalize);
        if (it != null) {
            return it;
        }
        BrainResult it2 = personalData(listNormalize, request);
        if (it2 != null) {
            return it2;
        }
        BrainResult it3 = scheduledCommand(listNormalize, request);
        if (it3 != null) {
            return it3;
        }
        BrainResult it4 = flashLight(listNormalize, request);
        if (it4 != null) {
            return it4;
        }
        BrainResult it5 = callHistory(listNormalize, request);
        if (it5 != null) {
            return it5;
        }
        BrainResult it6 = smsRead(listNormalize, request);
        if (it6 != null) {
            return it6;
        }
        BrainResult it7 = calendarList(listNormalize, request);
        if (it7 != null) {
            return it7;
        }
        BrainResult it8 = systemStatus(listNormalize);
        if (it8 != null) {
            return it8;
        }
        BrainResult it9 = infoRequest(listNormalize);
        if (it9 != null) {
            return it9;
        }
        BrainResult it10 = announceSettings(listNormalize);
        if (it10 != null) {
            return it10;
        }
        BrainResult it11 = replyToMessage(listNormalize);
        if (it11 != null) {
            return it11;
        }
        BrainResult it12 = forget(listNormalize);
        if (it12 != null) {
            return it12;
        }
        BrainResult it13 = briefing(listNormalize);
        if (it13 != null) {
            return it13;
        }
        BrainResult it14 = volume(request.getText(), listNormalize, request);
        if (it14 != null) {
            return it14;
        }
        BrainResult it15 = brightness(listNormalize, request);
        if (it15 != null) {
            return it15;
        }
        BrainResult it16 = reminder(listNormalize, request);
        if (it16 != null) {
            return it16;
        }
        BrainResult it17 = timer(listNormalize, request);
        if (it17 != null) {
            return it17;
        }
        BrainResult it18 = sendTelegram(listNormalize, request);
        if (it18 != null) {
            return it18;
        }
        BrainResult it19 = sendSms(listNormalize, request);
        if (it19 != null) {
            return it19;
        }
        BrainResult it20 = systemSetting(listNormalize, request);
        if (it20 != null) {
            return it20;
        }
        BrainResult it21 = screenshot(listNormalize, request);
        if (it21 != null) {
            return it21;
        }
        BrainResult it22 = appAction(listNormalize);
        if (it22 != null) {
            return it22;
        }
        BrainResult it23 = webAnswer(listNormalize);
        if (it23 != null) {
            return it23;
        }
        BrainResult it24 = camera(listNormalize);
        if (it24 != null) {
            return it24;
        }
        BrainResult it25 = navigate(listNormalize);
        if (it25 != null) {
            return it25;
        }
        BrainResult it26 = webSearch(listNormalize);
        if (it26 != null) {
            return it26;
        }
        BrainResult it27 = weather(listNormalize, request);
        if (it27 != null) {
            return it27;
        }
        BrainResult it28 = music(listNormalize, request);
        if (it28 != null) {
            return it28;
        }
        BrainResult it29 = knowledge(listNormalize, request);
        if (it29 != null) {
            return it29;
        }
        BrainResult it30 = launchApp(listNormalize, request);
        if (it30 != null) {
            return it30;
        }
        BrainResult it31 = uiActions(listNormalize, request);
        if (it31 != null) {
            return it31;
        }
        BrainResult it32 = clipboard(listNormalize, request);
        if (it32 != null) {
            return it32;
        }
        BrainResult it33 = notifications(listNormalize, request);
        if (it33 != null) {
            return it33;
        }
        BrainResult it34 = createEvent(listNormalize, request);
        if (it34 != null) {
            return it34;
        }
        BrainResult it35 = callPhone(listNormalize, request);
        if (it35 != null) {
            return it35;
        }
        BrainResult it36 = contacts(listNormalize, request);
        if (it36 != null) {
            return it36;
        }
        return (!looksLikeCommand(listNormalize) || this.cloudAvailable.invoke().booleanValue()) ? new BrainResult.Chat(0.9f) : new BrainResult.Clarify("Что именно сделать, сэр?", 0.4f);
    }

    private final BrainResult infoRequest(List<String> words) {
        boolean z;
        boolean z2;
        boolean z3;
        IntentType type;
        String it;
        String text = CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        Iterable $this$any$iv = NEWS_PHRASES;
        boolean z4 = true;
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            Iterator it2 = $this$any$iv.iterator();
            while (true) {
                if (it2.hasNext()) {
                    Object element$iv = it2.next();
                    String it3 = (String) element$iv;
                    if (StringsKt.contains$default((CharSequence) text, (CharSequence) it3, false, 2, (Object) null)) {
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
            type = IntentType.NEWS;
        } else {
            Iterable $this$any$iv2 = RATE_PHRASES;
            if (!($this$any$iv2 instanceof Collection) || !((Collection) $this$any$iv2).isEmpty()) {
                Iterator it4 = $this$any$iv2.iterator();
                while (true) {
                    if (it4.hasNext()) {
                        Object element$iv2 = it4.next();
                        String it5 = (String) element$iv2;
                        if (StringsKt.contains$default((CharSequence) text, (CharSequence) it5, false, 2, (Object) null)) {
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
            if (z2) {
                type = IntentType.RATES;
            } else {
                Iterable $this$any$iv3 = LOCATION_PHRASES;
                if (!($this$any$iv3 instanceof Collection) || !((Collection) $this$any$iv3).isEmpty()) {
                    Iterator it6 = $this$any$iv3.iterator();
                    while (true) {
                        if (it6.hasNext()) {
                            Object element$iv3 = it6.next();
                            String it7 = (String) element$iv3;
                            if (Intrinsics.areEqual(text, it7) || StringsKt.startsWith$default(text, new StringBuilder().append(it7).append(StringUtils.SPACE).toString(), false, 2, (Object) null)) {
                                z3 = true;
                                break;
                            }
                        } else {
                            z3 = false;
                            break;
                        }
                    }
                } else {
                    z3 = false;
                }
                type = z3 ? IntentType.LOCATION : null;
            }
        }
        if (type == IntentType.RATES) {
            Map<String, String> map = CURRENCY_CODES;
            LinkedHashMap result$iv = new LinkedHashMap();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String it8 = entry.getKey();
                if (StringsKt.contains$default((CharSequence) text, (CharSequence) it8, false, 2, (Object) null)) {
                    result$iv.put(entry.getKey(), entry.getValue());
                }
            }
            LinkedHashMap $this$filterKeys$iv = result$iv;
            Collection named = $this$filterKeys$iv.values();
            return skill(type, named.size() == 1 ? MapsKt.mapOf(TuplesKt.to("code", CollectionsKt.first(named))) : MapsKt.emptyMap(), text, 0.93f);
        }
        if (type != null) {
            return skill(type, MapsKt.emptyMap(), text, 0.93f);
        }
        if (StringsKt.contains$default((CharSequence) text, (CharSequence) "приложени", false, 2, (Object) null)) {
            Iterable $this$any$iv4 = APP_LIST_PHRASES;
            if (($this$any$iv4 instanceof Collection) && ((Collection) $this$any$iv4).isEmpty()) {
                z4 = false;
            } else {
                Iterator it9 = $this$any$iv4.iterator();
                do {
                    if (!it9.hasNext()) {
                        z4 = false;
                        break;
                    }
                    Object element$iv4 = it9.next();
                    it = (String) element$iv4;
                } while (!StringsKt.contains$default((CharSequence) text, (CharSequence) it, false, 2, (Object) null));
            }
            if (z4) {
                return skill(IntentType.LAUNCH_APP, MapsKt.mapOf(TuplesKt.to("action", "list")), text, 0.9f);
            }
        }
        String call = CALL_CONTROL.get(StringsKt.removeSuffix(text, (CharSequence) " пожалуйста"));
        if (call == null) {
            return null;
        }
        return Intrinsics.areEqual(call, "redial") ? skill(IntentType.CALL, MapsKt.mapOf(TuplesKt.to("action", "redial")), text, 0.93f) : skill(IntentType.PHONE_CONTROL, MapsKt.mapOf(TuplesKt.to("action", call)), text, 0.93f);
    }

    private final BrainResult announceSettings(List<String> words) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        String kind;
        String text = CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        List<String> $this$none$iv = words;
        if (!($this$none$iv instanceof Collection) || !$this$none$iv.isEmpty()) {
            Iterator it = $this$none$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    if (ANNOUNCE_VERBS.contains((String) element$iv)) {
                        z = false;
                        break;
                    }
                } else {
                    z = true;
                    break;
                }
            }
        } else {
            z = true;
        }
        if (z) {
            return null;
        }
        List<String> $this$any$iv = words;
        if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
            Iterator it2 = $this$any$iv.iterator();
            while (true) {
                if (it2.hasNext()) {
                    Object element$iv2 = it2.next();
                    String it3 = (String) element$iv2;
                    if (StringsKt.startsWith$default(it3, "звонк", false, 2, (Object) null) || StringsKt.startsWith$default(it3, "звонок", false, 2, (Object) null)) {
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
        if (z2) {
            kind = "calls";
        } else {
            List<String> $this$any$iv2 = words;
            if (!($this$any$iv2 instanceof Collection) || !$this$any$iv2.isEmpty()) {
                Iterator it4 = $this$any$iv2.iterator();
                while (true) {
                    if (it4.hasNext()) {
                        Object element$iv3 = it4.next();
                        if (StringsKt.startsWith$default((String) element$iv3, "сообщени", false, 2, (Object) null)) {
                            z3 = true;
                            break;
                        }
                    } else {
                        z3 = false;
                        break;
                    }
                }
            } else {
                z3 = false;
            }
            if (z3) {
                kind = "messages";
            } else {
                List<String> $this$any$iv3 = words;
                if (!($this$any$iv3 instanceof Collection) || !$this$any$iv3.isEmpty()) {
                    Iterator it5 = $this$any$iv3.iterator();
                    while (true) {
                        if (it5.hasNext()) {
                            Object element$iv4 = it5.next();
                            String it6 = (String) element$iv4;
                            if (StringsKt.startsWith$default(it6, "подсказ", false, 2, (Object) null) || StringsKt.startsWith$default(it6, "встреч", false, 2, (Object) null)) {
                                z4 = true;
                                break;
                            }
                        } else {
                            z4 = false;
                            break;
                        }
                    }
                } else {
                    z4 = false;
                }
                if (!z4) {
                    return null;
                }
                kind = "hints";
            }
        }
        return skill(IntentType.ANNOUNCE_SETTINGS, MapsKt.mapOf(TuplesKt.to("kind", kind), TuplesKt.to("on", String.valueOf(!words.contains("не")))), text, 0.92f);
    }

    private final BrainResult replyToMessage(List<String> words) {
        boolean z;
        if (!Intrinsics.areEqual(CollectionsKt.firstOrNull((List) words), "ответь") || words.size() < 2) {
            return null;
        }
        List<String> $this$any$iv = words;
        if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    String it2 = (String) element$iv;
                    if (StringsKt.startsWith$default(it2, "уведомлен", false, 2, (Object) null) || StringsKt.startsWith$default(it2, "звон", false, 2, (Object) null)) {
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
            return null;
        }
        Iterable $this$dropWhile$iv = CollectionsKt.drop(words, 1);
        boolean yielding$iv = false;
        ArrayList list$iv = new ArrayList();
        for (Object item$iv : $this$dropWhile$iv) {
            if (yielding$iv) {
                list$iv.add(item$iv);
            } else if (!REPLY_FILLERS.contains((String) item$iv)) {
                list$iv.add(item$iv);
                yielding$iv = true;
            }
        }
        ArrayList body = list$iv;
        if (body.isEmpty()) {
            return null;
        }
        return skill(IntentType.NOTIFICATIONS, MapsKt.mapOf(TuplesKt.to("action", "reply"), TuplesKt.to(VoiceService.EXTRA_TEXT, CollectionsKt.joinToString$default(body, StringUtils.SPACE, null, null, 0, null, null, 62, null))), CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null), 0.9f);
    }

    private final BrainResult forget(List<String> words) {
        if (!Intrinsics.areEqual(CollectionsKt.firstOrNull((List) words), "забудь")) {
            return null;
        }
        Iterable $this$filter$iv = CollectionsKt.drop(words, 1);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            if (!FORGET_FILLERS.contains(it)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        String query = CollectionsKt.joinToString$default((List) destination$iv$iv, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        return skill(IntentType.FORGET, MapsKt.mapOf(TuplesKt.to(SearchIntents.EXTRA_QUERY, query)), CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null), 0.92f);
    }

    private final BrainResult briefing(List<String> words) {
        String text = CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        Iterable $this$none$iv = BRIEFING_PHRASES;
        boolean z = true;
        if (!($this$none$iv instanceof Collection) || !((Collection) $this$none$iv).isEmpty()) {
            for (Object element$iv : $this$none$iv) {
                String it = (String) element$iv;
                if (StringsKt.contains$default((CharSequence) text, (CharSequence) it, false, 2, (Object) null)) {
                    z = false;
                    break;
                }
            }
        }
        if (z) {
            return null;
        }
        return skill(IntentType.BRIEFING, MapsKt.emptyMap(), text, 0.93f);
    }

    private final BrainResult camera(List<String> words) {
        boolean video;
        boolean z;
        String text = CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        Iterable $this$any$iv = VIDEO_PHRASES;
        boolean z2 = true;
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    video = false;
                    break;
                }
                Object element$iv = it.next();
                String it2 = (String) element$iv;
                if (StringsKt.contains$default((CharSequence) text, (CharSequence) it2, false, 2, (Object) null)) {
                    video = true;
                    break;
                }
            }
        } else {
            video = false;
        }
        if (!video) {
            List<String> $this$none$iv = words;
            if (!($this$none$iv instanceof Collection) || !$this$none$iv.isEmpty()) {
                Iterator it3 = $this$none$iv.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        z = true;
                        break;
                    }
                    Object element$iv2 = it3.next();
                    String it4 = (String) element$iv2;
                    if (PHOTO_VERBS.contains(it4)) {
                        z = false;
                        break;
                    }
                }
            } else {
                z = true;
            }
            if (z) {
                Iterable $this$none$iv2 = PHOTO_PHRASES;
                if (!($this$none$iv2 instanceof Collection) || !((Collection) $this$none$iv2).isEmpty()) {
                    for (Object element$iv3 : $this$none$iv2) {
                        String it5 = (String) element$iv3;
                        if (StringsKt.contains$default((CharSequence) text, (CharSequence) it5, false, 2, (Object) null)) {
                            z2 = false;
                            break;
                        }
                    }
                }
                if (z2) {
                    return null;
                }
            }
        }
        return skill(IntentType.CAMERA, MapsKt.mapOf(TuplesKt.to("mode", video ? "video" : "photo")), text, 0.92f);
    }

    /* JADX WARN: Code duplicated, block: B:18:0x005e  */
    private final BrainResult navigate(List<String> words) {
        String place;
        List<String> groupValues;
        Iterable iterableDrop;
        Object element$iv;
        String it;
        String text = CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        MatchResult matchResultFind$default = Regex.find$default(NAVIGATION, text, 0, 2, null);
        if (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null || (iterableDrop = CollectionsKt.drop(groupValues, 1)) == null) {
            place = null;
        } else {
            Iterable $this$firstOrNull$iv = iterableDrop;
            Iterator it2 = $this$firstOrNull$iv.iterator();
            do {
                if (!it2.hasNext()) {
                    element$iv = null;
                    break;
                }
                element$iv = it2.next();
                it = (String) element$iv;
            } while (StringsKt.isBlank(it));
            String str = (String) element$iv;
            if (str != null) {
                place = StringsKt.trim((CharSequence) str).toString();
            } else {
                place = null;
            }
        }
        if (place == null) {
            place = "";
        }
        if (StringsKt.startsWith$default(text, "поехали", false, 2, (Object) null) && !POST_GO.containsMatchIn(text)) {
            return null;
        }
        if (place.length() == 0) {
            return null;
        }
        return skill(IntentType.NAVIGATE, MapsKt.mapOf(TuplesKt.to("destination", place)), text, 0.92f);
    }

    private final BrainResult webSearch(List<String> words) {
        Object element$iv;
        boolean z;
        String text = CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        Iterable $this$firstOrNull$iv = WEB_MARKERS;
        Iterator it = $this$firstOrNull$iv.iterator();
        do {
            if (!it.hasNext()) {
                element$iv = null;
                break;
            }
            element$iv = it.next();
        } while (!StringsKt.contains$default((CharSequence) text, (CharSequence) element$iv, false, 2, (Object) null));
        String marker = (String) element$iv;
        if (marker == null) {
            return null;
        }
        List<String> $this$none$iv = words;
        if (!($this$none$iv instanceof Collection) || !$this$none$iv.isEmpty()) {
            Iterator it2 = $this$none$iv.iterator();
            while (true) {
                if (it2.hasNext()) {
                    Object element$iv2 = it2.next();
                    if (SEARCH_VERBS.contains((String) element$iv2)) {
                        z = false;
                        break;
                    }
                } else {
                    z = true;
                    break;
                }
            }
        } else {
            z = true;
        }
        if (z) {
            return null;
        }
        Iterable $this$filter$iv = StringsKt.split$default((CharSequence) StringsKt.replace$default(text, marker, StringUtils.SPACE, false, 4, (Object) null), new String[]{StringUtils.SPACE}, false, 0, 6, (Object) null);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it3 = (String) element$iv$iv;
            if ((StringsKt.isBlank(it3) || SEARCH_VERBS.contains(it3) || SEARCH_FILLERS.contains(it3)) ? false : true) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        String query = CollectionsKt.joinToString$default((List) destination$iv$iv, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        return skill(IntentType.WEB_SEARCH, MapsKt.mapOf(TuplesKt.to(SearchIntents.EXTRA_QUERY, query)), text, 0.92f);
    }

    private final BrainResult systemStatus(List<String> words) {
        String text = CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        Iterable $this$none$iv = STATUS_PHRASES;
        boolean z = true;
        if (!($this$none$iv instanceof Collection) || !((Collection) $this$none$iv).isEmpty()) {
            for (Object element$iv : $this$none$iv) {
                String it = (String) element$iv;
                if (StringsKt.contains$default((CharSequence) text, (CharSequence) it, false, 2, (Object) null)) {
                    z = false;
                    break;
                }
            }
        }
        if (z) {
            return null;
        }
        return skill(IntentType.SYSTEM_STATUS, MapsKt.emptyMap(), text, 0.93f);
    }

    /* JADX WARN: Code duplicated, block: B:40:0x00cb  */
    private final BrainResult callHistory(List<String> words, UserRequest request) {
        boolean z;
        boolean z2;
        String text = CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        boolean z3 = false;
        if (StringsKt.contains$default((CharSequence) text, (CharSequence) "история звон", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "историю звон", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "последние звон", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "кто звонил", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "кто мне звонил", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "пропущенные", false, 2, (Object) null)) {
            z3 = true;
        } else {
            List<String> $this$any$iv = words;
            if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
                Iterator it = $this$any$iv.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    Object element$iv = it.next();
                    String it2 = (String) element$iv;
                    if (StringsKt.startsWith$default(it2, "истор", false, 2, (Object) null)) {
                        z = true;
                        break;
                    }
                }
            } else {
                z = false;
            }
            if (z) {
                List<String> $this$any$iv2 = words;
                if (!($this$any$iv2 instanceof Collection) || !$this$any$iv2.isEmpty()) {
                    Iterator it3 = $this$any$iv2.iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            z2 = false;
                            break;
                        }
                        Object element$iv2 = it3.next();
                        String it4 = (String) element$iv2;
                        if (StringsKt.startsWith$default(it4, "звон", false, 2, (Object) null)) {
                            z2 = true;
                            break;
                        }
                    }
                } else {
                    z2 = false;
                }
                if (z2) {
                    z3 = true;
                }
            }
        }
        boolean hit = z3;
        if (hit) {
            return new BrainResult.SkillCall(new Intent(IntentType.CALL, text, MapsKt.mapOf(TuplesKt.to("action", new Slot("action", "history", 0.0f, 4, null))), 0.9f, ConfidenceLevel.HIGH), 0.9f);
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:50:0x0123  */
    private final BrainResult smsRead(List<String> words, UserRequest request) {
        boolean hit;
        boolean z;
        boolean z2;
        String text = CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        boolean z3 = false;
        if (!StringsKt.contains$default((CharSequence) text, (CharSequence) "прочитай смс", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) text, (CharSequence) "прочитай sms", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) text, (CharSequence) "последние смс", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) text, (CharSequence) "входящие смс", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) text, (CharSequence) "какие смс", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) text, (CharSequence) "что в смс", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) text, (CharSequence) "последнее сообщение", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) text, (CharSequence) "новые сообщения", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) text, (CharSequence) "есть сообщения", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) text, (CharSequence) "непрочитанные", false, 2, (Object) null)) {
            List<String> $this$any$iv = words;
            if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
                Iterator it = $this$any$iv.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Object element$iv = it.next();
                        String it2 = (String) element$iv;
                        if (SetsKt.setOf((Object[]) new String[]{"прочитай", "покажи", "какие"}).contains(it2)) {
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
                List<String> $this$any$iv2 = words;
                if (!($this$any$iv2 instanceof Collection) || !$this$any$iv2.isEmpty()) {
                    Iterator it3 = $this$any$iv2.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            Object element$iv2 = it3.next();
                            String it4 = (String) element$iv2;
                            if (SetsKt.setOf((Object[]) new String[]{"смс", "sms", "сообщения"}).contains(it4)) {
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
                hit = z2;
            }
        }
        if (!hit) {
            return null;
        }
        List<String> $this$any$iv3 = words;
        if (!($this$any$iv3 instanceof Collection) || !$this$any$iv3.isEmpty()) {
            for (Object element$iv3 : $this$any$iv3) {
                String it5 = (String) element$iv3;
                if (SetsKt.setOf((Object[]) new String[]{"напиши", "отправь", "передай", "напечатай", "отправить"}).contains(it5)) {
                    z3 = true;
                    break;
                }
            }
        }
        if (z3) {
            return null;
        }
        return new BrainResult.SkillCall(new Intent(IntentType.SEND_SMS, text, MapsKt.mapOf(TuplesKt.to("action", new Slot("action", "read", 0.0f, 4, null))), 0.9f, ConfidenceLevel.HIGH), 0.9f);
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0092  */
    /* JADX WARN: Code duplicated, block: B:86:0x01a8  */
    private final BrainResult calendarList(List<String> words, UserRequest request) {
        Integer day;
        boolean z;
        boolean hit;
        boolean z2;
        boolean z3;
        boolean z4;
        String text = CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        boolean z5 = false;
        if (StringsKt.contains$default((CharSequence) text, (CharSequence) "послезавтра", false, 2, (Object) null)) {
            day = 2;
        } else if (StringsKt.contains$default((CharSequence) text, (CharSequence) "завтра", false, 2, (Object) null)) {
            day = 1;
        } else {
            day = StringsKt.contains$default((CharSequence) text, (CharSequence) "сегодня", false, 2, (Object) null) ? 0 : null;
        }
        if (StringsKt.contains$default((CharSequence) text, (CharSequence) "недел", false, 2, (Object) null)) {
            List<String> $this$any$iv = words;
            if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
                Iterator it = $this$any$iv.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Object element$iv = it.next();
                        String it2 = (String) element$iv;
                        if (EVENT_VERBS.contains(it2)) {
                            z4 = true;
                            break;
                        }
                    } else {
                        z4 = false;
                        break;
                    }
                }
            } else {
                z4 = false;
            }
            if (z4) {
                z = false;
            } else {
                z = true;
            }
        } else {
            z = false;
        }
        boolean week = z;
        if (!StringsKt.contains$default((CharSequence) text, (CharSequence) "что в календаре", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) text, (CharSequence) "какие события", false, 2, (Object) null) && ((!week || (!StringsKt.contains$default((CharSequence) text, (CharSequence) "что у меня", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) text, (CharSequence) "планы", false, 2, (Object) null))) && !StringsKt.contains$default((CharSequence) text, (CharSequence) "что у меня завтра", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) text, (CharSequence) "что у меня послезавтра", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) text, (CharSequence) "планы на завтра", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) text, (CharSequence) "планы на послезавтра", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) text, (CharSequence) "ближайшие события", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) text, (CharSequence) "расписание", false, 2, (Object) null))) {
            List<String> $this$any$iv2 = words;
            if (!($this$any$iv2 instanceof Collection) || !$this$any$iv2.isEmpty()) {
                Iterator it3 = $this$any$iv2.iterator();
                while (true) {
                    if (it3.hasNext()) {
                        Object element$iv2 = it3.next();
                        String it4 = (String) element$iv2;
                        if (SetsKt.setOf((Object[]) new String[]{"покажи", "какие", "что"}).contains(it4)) {
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
            if (z2) {
                List<String> $this$any$iv3 = words;
                if (!($this$any$iv3 instanceof Collection) || !$this$any$iv3.isEmpty()) {
                    Iterator it5 = $this$any$iv3.iterator();
                    while (true) {
                        if (it5.hasNext()) {
                            Object element$iv3 = it5.next();
                            String it6 = (String) element$iv3;
                            if (StringsKt.startsWith$default(it6, "календар", false, 2, (Object) null) || StringsKt.startsWith$default(it6, "событ", false, 2, (Object) null) || Intrinsics.areEqual(it6, "встречи")) {
                                z3 = true;
                                break;
                            }
                        } else {
                            z3 = false;
                            break;
                        }
                    }
                } else {
                    z3 = false;
                }
                hit = z3;
            }
        }
        if (!hit) {
            return null;
        }
        List<String> $this$any$iv4 = words;
        if (!($this$any$iv4 instanceof Collection) || !$this$any$iv4.isEmpty()) {
            for (Object element$iv4 : $this$any$iv4) {
                String it7 = (String) element$iv4;
                if (EVENT_VERBS.contains(it7)) {
                    z5 = true;
                    break;
                }
            }
        }
        if (z5) {
            return null;
        }
        IntentType intentType = IntentType.CREATE_EVENT;
        Map $this$calendarList_u24lambda_u244 = MapsKt.createMapBuilder();
        $this$calendarList_u24lambda_u244.put("action", new Slot("action", "list", 0.0f, 4, null));
        if (week) {
            $this$calendarList_u24lambda_u244.put("days", new Slot("days", 7, 0.0f, 4, null));
        } else if (day != null) {
            $this$calendarList_u24lambda_u244.put("day", new Slot("day", day, 0.0f, 4, null));
        }
        Unit unit = Unit.INSTANCE;
        return new BrainResult.SkillCall(new Intent(intentType, text, MapsKt.build($this$calendarList_u24lambda_u244), 0.9f, ConfidenceLevel.HIGH), 0.9f);
    }

    private final BrainResult flashLight(List<String> words, UserRequest request) {
        boolean z;
        boolean z2;
        boolean z3;
        String action;
        String text = CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        boolean off = true;
        boolean freeOn = Intrinsics.areEqual(text, "посвети") || Intrinsics.areEqual(text, "подсвети") || StringsKt.contains$default((CharSequence) text, (CharSequence) "сделай светло", false, 2, (Object) null);
        List<String> $this$any$iv = words;
        if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    String it2 = (String) element$iv;
                    if (LIGHT_NOUNS.contains(it2)) {
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
        boolean noun = z || freeOn;
        if (!noun) {
            return null;
        }
        List<String> $this$any$iv2 = words;
        if (!($this$any$iv2 instanceof Collection) || !$this$any$iv2.isEmpty()) {
            Iterator it3 = $this$any$iv2.iterator();
            while (true) {
                if (it3.hasNext()) {
                    Object element$iv2 = it3.next();
                    String it4 = (String) element$iv2;
                    if (ON_VERBS.contains(it4)) {
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
        boolean on = z2 || freeOn;
        List<String> $this$any$iv3 = words;
        if (!($this$any$iv3 instanceof Collection) || !$this$any$iv3.isEmpty()) {
            Iterator it5 = $this$any$iv3.iterator();
            while (true) {
                if (it5.hasNext()) {
                    Object element$iv3 = it5.next();
                    String it6 = (String) element$iv3;
                    if (OFF_VERBS.contains(it6)) {
                        z3 = true;
                        break;
                    }
                } else {
                    z3 = false;
                    break;
                }
            }
        } else {
            z3 = false;
        }
        if (!z3 && !words.contains("убери")) {
            off = false;
        }
        if (on && !off) {
            action = "on";
        } else {
            if (!off || on) {
                return null;
            }
            action = "off";
        }
        return new BrainResult.SkillCall(new Intent(IntentType.FLASHLIGHT, CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null), MapsKt.mapOf(TuplesKt.to("action", new Slot("action", action, 0.0f, 4, null))), 0.95f, ConfidenceLevel.HIGH), 0.95f);
    }

    /* JADX WARN: Code duplicated, block: B:154:0x0270  */
    /* JADX WARN: Code duplicated, block: B:155:0x0275  */
    private final BrainResult volume(String rawText, List<String> words, UserRequest request) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        String direction;
        boolean target;
        String str;
        Map slots;
        boolean dirUp;
        List<String> $this$any$iv = words;
        if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                Object element$iv = it.next();
                String it2 = (String) element$iv;
                if (StringsKt.startsWith$default(it2, "яркост", false, 2, (Object) null) || Intrinsics.areEqual(it2, "экран")) {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
        }
        if (z) {
            return null;
        }
        String text = rawText.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(text, "toLowerCase(...)");
        List<String> $this$any$iv2 = words;
        if (!($this$any$iv2 instanceof Collection) || !$this$any$iv2.isEmpty()) {
            Iterator it3 = $this$any$iv2.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    z2 = false;
                    break;
                }
                Object element$iv2 = it3.next();
                if (VOLUME_UP.contains((String) element$iv2)) {
                    z2 = true;
                    break;
                }
            }
        } else {
            z2 = false;
        }
        boolean dirUp2 = z2 || StringsKt.contains$default((CharSequence) text, (CharSequence) "громче", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "погромче", false, 2, (Object) null);
        List<String> $this$any$iv3 = words;
        if (!($this$any$iv3 instanceof Collection) || !$this$any$iv3.isEmpty()) {
            Iterator it4 = $this$any$iv3.iterator();
            while (true) {
                if (!it4.hasNext()) {
                    z3 = false;
                    break;
                }
                Object element$iv3 = it4.next();
                if (VOLUME_DOWN.contains((String) element$iv3)) {
                    z3 = true;
                    break;
                }
            }
        } else {
            z3 = false;
        }
        boolean dirDown = z3 || StringsKt.contains$default((CharSequence) text, (CharSequence) "тише", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "потише", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "не так громко", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "слишком громко", false, 2, (Object) null);
        boolean mute = StringsKt.contains$default((CharSequence) text, (CharSequence) "выруби звук", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "отключи звук", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "убери звук", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "выключи звук", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "без звука", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "заглуши", false, 2, (Object) null);
        boolean unmute = StringsKt.contains$default((CharSequence) text, (CharSequence) "включи звук", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "верни звук", false, 2, (Object) null);
        List<String> $this$any$iv4 = words;
        if (!($this$any$iv4 instanceof Collection) || !$this$any$iv4.isEmpty()) {
            Iterator it5 = $this$any$iv4.iterator();
            while (true) {
                if (!it5.hasNext()) {
                    z4 = false;
                    break;
                }
                Object element$iv4 = it5.next();
                if (VOLUME_MAX.contains((String) element$iv4)) {
                    z4 = true;
                    break;
                }
            }
        } else {
            z4 = false;
        }
        boolean max = z4 || StringsKt.contains$default((CharSequence) text, (CharSequence) "на максимум", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "максимальную", false, 2, (Object) null);
        if (mute && !unmute) {
            direction = "mute";
        } else if (unmute && !mute) {
            direction = "unmute";
        } else if (max) {
            direction = "max";
        } else if (dirDown) {
            direction = "down";
        } else {
            if (!dirUp2) {
                return null;
            }
            direction = "up";
        }
        Integer percent = percentOf(words);
        if (percent == null) {
            percent = bareLevel(words);
        }
        if (percent != null && !mute && !unmute && !max) {
            List<String> $this$any$iv5 = words;
            if (!($this$any$iv5 instanceof Collection) || !$this$any$iv5.isEmpty()) {
                Iterator it6 = $this$any$iv5.iterator();
                while (true) {
                    if (!it6.hasNext()) {
                        dirUp = false;
                        break;
                    }
                    Object element$iv5 = it6.next();
                    String it7 = (String) element$iv5;
                    String text2 = text;
                    boolean dirUp3 = dirUp2;
                    if (StringsKt.startsWith$default(it7, "громкост", false, 2, (Object) null) || StringsKt.startsWith$default(it7, "звук", false, 2, (Object) null)) {
                        dirUp = true;
                        break;
                    }
                    dirUp2 = dirUp3;
                    text = text2;
                }
            } else {
                dirUp = false;
            }
            boolean dirUp4 = dirUp;
            target = dirUp4;
            Pair[] pairArr = new Pair[1];
            if (target) {
                str = "set";
            } else {
                str = direction;
            }
            pairArr[0] = TuplesKt.to("direction", new Slot("direction", str, 0.0f, 4, null));
            slots = MapsKt.mutableMapOf(pairArr);
            if (percent != null && (target || Intrinsics.areEqual(direction, "down"))) {
                slots.put("percent", new Slot("percent", percent, 0.0f, 4, null));
            }
            return new BrainResult.SkillCall(new Intent(IntentType.VOLUME, CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null), slots, 0.92f, ConfidenceLevel.HIGH), 0.92f);
        }
        target = dirUp4;
        Pair[] pairArr2 = new Pair[1];
        if (target) {
            str = "set";
        } else {
            str = direction;
        }
        pairArr2[0] = TuplesKt.to("direction", new Slot("direction", str, 0.0f, 4, null));
        slots = MapsKt.mutableMapOf(pairArr2);
        if (percent != null) {
            slots.put("percent", new Slot("percent", percent, 0.0f, 4, null));
        }
        return new BrainResult.SkillCall(new Intent(IntentType.VOLUME, CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null), slots, 0.92f, ConfidenceLevel.HIGH), 0.92f);
    }

    private final BrainResult brightness(List<String> words, UserRequest request) {
        boolean z;
        boolean z2;
        String comfort;
        boolean isBright;
        boolean up;
        boolean z3;
        String direction;
        String phrase = CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        Iterable $this$any$iv = TOO_BRIGHT;
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    String it2 = (String) element$iv;
                    if (StringsKt.contains$default((CharSequence) phrase, (CharSequence) it2, false, 2, (Object) null)) {
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
            comfort = "down";
        } else {
            Iterable $this$any$iv2 = TOO_DARK;
            if (!($this$any$iv2 instanceof Collection) || !((Collection) $this$any$iv2).isEmpty()) {
                Iterator it3 = $this$any$iv2.iterator();
                while (true) {
                    if (it3.hasNext()) {
                        Object element$iv2 = it3.next();
                        String it4 = (String) element$iv2;
                        if (StringsKt.contains$default((CharSequence) phrase, (CharSequence) it4, false, 2, (Object) null)) {
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
            if (z2) {
                comfort = "up";
            } else {
                comfort = null;
            }
        }
        if (comfort != null) {
            return skill(IntentType.BRIGHTNESS, MapsKt.mapOf(TuplesKt.to("direction", comfort)), phrase, 0.9f);
        }
        List<String> $this$any$iv3 = words;
        if (!($this$any$iv3 instanceof Collection) || !$this$any$iv3.isEmpty()) {
            Iterator it5 = $this$any$iv3.iterator();
            while (true) {
                if (it5.hasNext()) {
                    Object element$iv3 = it5.next();
                    String it6 = (String) element$iv3;
                    if (BRIGHTNESS_NOUNS.contains(it6)) {
                        isBright = true;
                        break;
                    }
                } else {
                    isBright = false;
                    break;
                }
            }
        } else {
            isBright = false;
        }
        if (!isBright) {
            return null;
        }
        List<String> $this$any$iv4 = words;
        if (!($this$any$iv4 instanceof Collection) || !$this$any$iv4.isEmpty()) {
            Iterator it7 = $this$any$iv4.iterator();
            while (true) {
                if (it7.hasNext()) {
                    Object element$iv4 = it7.next();
                    String it8 = (String) element$iv4;
                    if (BRIGHT_UP.contains(it8)) {
                        up = true;
                        break;
                    }
                } else {
                    up = false;
                    break;
                }
            }
        } else {
            up = false;
        }
        List<String> $this$any$iv5 = words;
        if (!($this$any$iv5 instanceof Collection) || !$this$any$iv5.isEmpty()) {
            Iterator it9 = $this$any$iv5.iterator();
            while (true) {
                if (it9.hasNext()) {
                    Object element$iv5 = it9.next();
                    String it10 = (String) element$iv5;
                    if (BRIGHT_DOWN.contains(it10) || VOLUME_DOWN.contains(it10)) {
                        z3 = true;
                        break;
                    }
                } else {
                    z3 = false;
                    break;
                }
            }
        } else {
            z3 = false;
        }
        boolean down = z3;
        String text = CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        Integer numPercentOf = percentOf(words);
        if (numPercentOf == null && (numPercentOf = bareLevel(words)) == null) {
            if (StringsKt.contains$default((CharSequence) text, (CharSequence) "на максимум", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "максимальн", false, 2, (Object) null)) {
                numPercentOf = 100;
            } else if (StringsKt.contains$default((CharSequence) text, (CharSequence) "на минимум", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "минимальн", false, 2, (Object) null)) {
                numPercentOf = 5;
            } else {
                numPercentOf = null;
            }
        }
        Integer percent = numPercentOf;
        if (percent != null) {
            direction = "set";
        } else if (up && !down) {
            direction = "up";
        } else {
            if (!down || up) {
                return null;
            }
            direction = "down";
        }
        IntentType intentType = IntentType.BRIGHTNESS;
        String strJoinToString$default = CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        Map $this$brightness_u24lambda_u245 = MapsKt.createMapBuilder();
        $this$brightness_u24lambda_u245.put("direction", new Slot("direction", direction, 0.0f, 4, null));
        if (percent != null) {
            $this$brightness_u24lambda_u245.put("percent", new Slot("percent", percent, 0.0f, 4, null));
        }
        Unit unit = Unit.INSTANCE;
        return new BrainResult.SkillCall(new Intent(intentType, strJoinToString$default, MapsKt.build($this$brightness_u24lambda_u245), 0.9f, ConfidenceLevel.HIGH), 0.9f);
    }

    private final BrainResult personalData(List<String> words, UserRequest request) {
        String text = CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        Map<String, ? extends Object> mapMapOf = MapsKt.mapOf(TuplesKt.to("phrase", request.getText()));
        if (CustomRoutineParser.INSTANCE.parse(request.getText()) != null) {
            return skill(IntentType.CUSTOM_ROUTINE, mapMapOf, text, 0.93f);
        }
        if (Places.INSTANCE.saveRequest(text) != null) {
            return skill(IntentType.PLACES, mapMapOf, text, 0.93f);
        }
        if (NoteParser.INSTANCE.parse(text) != null) {
            List<String> $this$none$iv = words;
            boolean z = true;
            if (!($this$none$iv instanceof Collection) || !$this$none$iv.isEmpty()) {
                for (Object element$iv : $this$none$iv) {
                    String it = (String) element$iv;
                    if (REMIND_VERBS.contains(it)) {
                        z = false;
                        break;
                    }
                }
            }
            if (z) {
                return skill(IntentType.NOTES, mapMapOf, text, 0.92f);
            }
        }
        return null;
    }

    private final BrainResult appAction(List<String> words) {
        Object obj;
        String kind;
        MatchResult match;
        String text = CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        Iterator<Pair<Regex, String>> it = APP_PATTERNS.iterator();
        do {
            obj = null;
            if (!it.hasNext()) {
                return null;
            }
            Pair<Regex, String> next = it.next();
            Regex pattern = next.component1();
            kind = next.component2();
            match = Regex.find$default(pattern, text, 0, 2, null);
        } while (match == null);
        Iterable $this$firstOrNull$iv = CollectionsKt.drop(match.getGroupValues(), 1);
        for (Object element$iv : $this$firstOrNull$iv) {
            String it2 = (String) element$iv;
            if (!StringsKt.isBlank(it2)) {
                obj = element$iv;
                break;
            }
        }
        String str = (String) obj;
        if (str == null) {
            str = "";
        }
        String query = StringsKt.trim((CharSequence) str).toString();
        String value = Intrinsics.areEqual(kind, "site") ? siteAddress(query) : query;
        return skill(IntentType.APP_ACTION, MapsKt.mapOf(TuplesKt.to("kind", kind), TuplesKt.to(SearchIntents.EXTRA_QUERY, value)), text, 0.92f);
    }

    private final String siteAddress(String spoken) {
        return Transliterator.INSTANCE.toLatin(StringsKt.replace$default(new Regex("[ .](ком|com)$").replace(new Regex("[ .](ру|ru)$").replace(StringsKt.replace$default(spoken, " точка ", ".", false, 4, (Object) null), ".ru"), ".com"), StringUtils.SPACE, "", false, 4, (Object) null));
    }

    private final BrainResult selfDirected(List<String> words) {
        List<String> groupValues;
        String str;
        String goal;
        String text = CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        MatchResult matchResultFind$default = Regex.find$default(SELF_DIRECTED, text, 0, 2, null);
        if (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null || (str = groupValues.get(1)) == null || (goal = StringsKt.trim((CharSequence) str).toString()) == null) {
            return null;
        }
        return skill(IntentType.APP_AGENT, MapsKt.mapOf(TuplesKt.to("goal", goal)), text, 0.95f);
    }

    private final BrainResult webAnswer(List<String> words) {
        if (CollectionsKt.contains(ANSWER_VERBS, CollectionsKt.firstOrNull((List) words)) && words.size() >= 2) {
            String text = CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
            Iterable $this$fold$iv = WEB_MARKERS;
            String strJoinToString$default = CollectionsKt.joinToString$default(CollectionsKt.drop(words, 1), StringUtils.SPACE, null, null, 0, null, null, 62, null);
            for (Object element$iv : $this$fold$iv) {
                String marker = (String) element$iv;
                String acc = strJoinToString$default;
                strJoinToString$default = StringsKt.replace$default(acc, marker, StringUtils.SPACE, false, 4, (Object) null);
            }
            String query = StringsKt.trim((CharSequence) new Regex("\\s+").replace(strJoinToString$default, StringUtils.SPACE)).toString();
            return skill(IntentType.WEB_ANSWER, MapsKt.mapOf(TuplesKt.to(SearchIntents.EXTRA_QUERY, query)), text, 0.92f);
        }
        return null;
    }

    private final BrainResult scheduledCommand(List<String> words, UserRequest request) {
        boolean z;
        boolean z2;
        List<String> $this$none$iv = words;
        if (!($this$none$iv instanceof Collection) || !$this$none$iv.isEmpty()) {
            Iterator it = $this$none$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = true;
                    break;
                }
                Object element$iv = it.next();
                if (TIME_MARKERS.contains((String) element$iv)) {
                    z = false;
                    break;
                }
            }
        } else {
            z = true;
        }
        if (!z) {
            List<String> $this$any$iv = words;
            if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
                Iterator it2 = $this$any$iv.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        z2 = false;
                        break;
                    }
                    Object element$iv2 = it2.next();
                    String it3 = (String) element$iv2;
                    if (REMIND_VERBS.contains(it3) || NOT_SCHEDULED.contains(it3)) {
                        z2 = true;
                        break;
                    }
                }
            } else {
                z2 = false;
            }
            if (!z2) {
                ReminderParser reminderParser = ReminderParser.INSTANCE;
                String text = request.getText();
                LocalDateTime localDateTimeNow = LocalDateTime.now();
                Intrinsics.checkNotNullExpressionValue(localDateTimeNow, "now(...)");
                ReminderRequest parsed = reminderParser.parse(text, localDateTimeNow);
                if (parsed.getAt() == null || !SCHEDULED_VERBS.contains(CollectionsKt.first(StringsKt.split$default((CharSequence) parsed.getText(), new char[]{' '}, false, 0, 6, (Object) null)))) {
                    return null;
                }
                return skill(IntentType.REMINDER, MapsKt.mapOf(TuplesKt.to("action", "add"), TuplesKt.to("phrase", request.getText()), TuplesKt.to("deferred", true)), CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null), 0.93f);
            }
        }
        return null;
    }

    private final BrainResult reminder(List<String> words, UserRequest request) {
        boolean noun;
        boolean z;
        boolean z2;
        boolean z3;
        String text = CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        List<String> $this$any$iv = words;
        if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    if (StringsKt.startsWith$default((String) element$iv, "напоминан", false, 2, (Object) null)) {
                        noun = true;
                        break;
                    }
                } else {
                    noun = false;
                    break;
                }
            }
        } else {
            noun = false;
        }
        if (noun) {
            List<String> $this$any$iv2 = words;
            if (!($this$any$iv2 instanceof Collection) || !$this$any$iv2.isEmpty()) {
                Iterator it2 = $this$any$iv2.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        Object element$iv2 = it2.next();
                        if (TIMER_CANCEL_VERBS.contains((String) element$iv2)) {
                            z3 = true;
                            break;
                        }
                    } else {
                        z3 = false;
                        break;
                    }
                }
            } else {
                z3 = false;
            }
            if (z3) {
                return cancelReminder(words, text);
            }
        }
        if (noun) {
            List<String> $this$any$iv3 = words;
            if (!($this$any$iv3 instanceof Collection) || !$this$any$iv3.isEmpty()) {
                Iterator it3 = $this$any$iv3.iterator();
                while (true) {
                    if (it3.hasNext()) {
                        Object element$iv3 = it3.next();
                        if (REMINDER_LIST_WORDS.contains((String) element$iv3)) {
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
            if (z2) {
                return skill(IntentType.REMINDER, MapsKt.mapOf(TuplesKt.to("action", "list")), text, 0.94f);
            }
        }
        if (!noun) {
            List<String> $this$none$iv = words;
            if (!($this$none$iv instanceof Collection) || !$this$none$iv.isEmpty()) {
                Iterator it4 = $this$none$iv.iterator();
                while (true) {
                    if (it4.hasNext()) {
                        Object element$iv4 = it4.next();
                        String it5 = (String) element$iv4;
                        if (REMIND_VERBS.contains(it5) || Intrinsics.areEqual(it5, "напоминай") || Intrinsics.areEqual(it5, "напоминалку")) {
                            z = false;
                            break;
                        }
                    } else {
                        z = true;
                        break;
                    }
                }
            } else {
                z = true;
            }
            if (z) {
                return null;
            }
        }
        ReminderParser reminderParser = ReminderParser.INSTANCE;
        String text2 = request.getText();
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        Intrinsics.checkNotNullExpressionValue(localDateTimeNow, "now(...)");
        ReminderRequest parsed = reminderParser.parse(text2, localDateTimeNow);
        if (StringsKt.isBlank(parsed.getText()) && words.contains("через")) {
            return null;
        }
        return skill(IntentType.REMINDER, MapsKt.mapOf(TuplesKt.to("action", "add"), TuplesKt.to("phrase", request.getText())), text, 0.94f);
    }

    private final BrainResult cancelReminder(List<String> words, String text) {
        boolean z;
        List<String> $this$filter$iv = words;
        Collection destination$iv$iv = new ArrayList();
        Iterator it = $this$filter$iv.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object element$iv$iv = it.next();
            String it2 = (String) element$iv$iv;
            if ((TIMER_CANCEL_VERBS.contains(it2) || StringsKt.startsWith$default(it2, "напоминан", false, 2, (Object) null) || REMINDER_QUERY_FILLERS.contains(it2)) ? false : true) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        String query = CollectionsKt.joinToString$default((List) destination$iv$iv, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        Map $this$cancelReminder_u24lambda_u241 = MapsKt.createMapBuilder();
        $this$cancelReminder_u24lambda_u241.put("action", "cancel");
        List<String> $this$any$iv = words;
        if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
            Iterator it3 = $this$any$iv.iterator();
            while (true) {
                if (it3.hasNext()) {
                    Object element$iv = it3.next();
                    String it4 = (String) element$iv;
                    if (Intrinsics.areEqual(it4, "все") || Intrinsics.areEqual(it4, "всё")) {
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
            $this$cancelReminder_u24lambda_u241.put("all", true);
        }
        if (query.length() > 0) {
            $this$cancelReminder_u24lambda_u241.put(SearchIntents.EXTRA_QUERY, query);
        }
        return skill(IntentType.REMINDER, MapsKt.build($this$cancelReminder_u24lambda_u241), text, 0.94f);
    }

    /* JADX WARN: Code duplicated, block: B:64:0x00dc  */
    private final BrainResult timer(List<String> words, UserRequest request) {
        boolean z;
        boolean isTimer;
        boolean z2;
        boolean z3;
        int i;
        boolean z4;
        boolean z5;
        boolean z6;
        List<String> $this$any$iv = words;
        int i2 = 0;
        if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                Object element$iv = it.next();
                String it2 = (String) element$iv;
                if (TIMER_NOUNS.contains(it2) || StringsKt.startsWith$default(it2, "секундомер", false, 2, (Object) null) || StringsKt.startsWith$default(it2, "будильник", false, 2, (Object) null)) {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
        }
        if (!z) {
            List<String> $this$any$iv2 = words;
            if (!($this$any$iv2 instanceof Collection) || !$this$any$iv2.isEmpty()) {
                Iterator it3 = $this$any$iv2.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        z5 = false;
                        break;
                    }
                    Object element$iv2 = it3.next();
                    if (REMIND_VERBS.contains((String) element$iv2)) {
                        z5 = true;
                        break;
                    }
                }
            } else {
                z5 = false;
            }
            if (!z5 && !words.contains("разбуди")) {
                if (words.contains("засеки")) {
                    List<String> $this$any$iv3 = words;
                    if (!($this$any$iv3 instanceof Collection) || !$this$any$iv3.isEmpty()) {
                        Iterator it4 = $this$any$iv3.iterator();
                        while (true) {
                            if (!it4.hasNext()) {
                                z6 = false;
                                break;
                            }
                            Object element$iv3 = it4.next();
                            String it5 = (String) element$iv3;
                            if (MINUTE_WORDS.contains(it5) || SECOND_WORDS.contains(it5) || HOUR_WORDS.contains(it5)) {
                                z6 = true;
                                break;
                            }
                        }
                    } else {
                        z6 = false;
                    }
                    isTimer = z6;
                }
            }
        }
        if (!isTimer) {
            return null;
        }
        String text = CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        List<String> $this$any$iv4 = words;
        if (!($this$any$iv4 instanceof Collection) || !$this$any$iv4.isEmpty()) {
            Iterator it6 = $this$any$iv4.iterator();
            while (true) {
                if (!it6.hasNext()) {
                    z2 = false;
                    break;
                }
                Object element$iv4 = it6.next();
                if (StringsKt.startsWith$default((String) element$iv4, "секундомер", false, 2, (Object) null)) {
                    z2 = true;
                    break;
                }
            }
        } else {
            z2 = false;
        }
        if (z2) {
            return stopwatch(words, text);
        }
        List<String> $this$any$iv5 = words;
        if (!($this$any$iv5 instanceof Collection) || !$this$any$iv5.isEmpty()) {
            Iterator it7 = $this$any$iv5.iterator();
            while (true) {
                if (!it7.hasNext()) {
                    z3 = false;
                    break;
                }
                Object element$iv5 = it7.next();
                if (StringsKt.startsWith$default((String) element$iv5, "будильник", false, 2, (Object) null)) {
                    z3 = true;
                    break;
                }
            }
        } else {
            z3 = false;
        }
        if (z3 || words.contains("разбуди")) {
            int i3 = 1;
            List<String> $this$any$iv6 = words;
            if (!($this$any$iv6 instanceof Collection) || !$this$any$iv6.isEmpty()) {
                Iterator it8 = $this$any$iv6.iterator();
                while (true) {
                    if (!it8.hasNext()) {
                        i = 0;
                        break;
                    }
                    Object element$iv6 = it8.next();
                    if (TIMER_CANCEL_VERBS.contains((String) element$iv6)) {
                        i = i3;
                        break;
                    }
                }
            } else {
                i = 0;
            }
            if (i != 0) {
                return skill(IntentType.TIMER, MapsKt.mapOf(TuplesKt.to("action", "alarm_cancel")), text, 0.94f);
            }
            List<String> $this$any$iv7 = words;
            if (!($this$any$iv7 instanceof Collection) || !$this$any$iv7.isEmpty()) {
                for (Object element$iv7 : $this$any$iv7) {
                    if (ALARM_STATUS_WORDS.contains((String) element$iv7)) {
                        i2 = i3;
                        break;
                    }
                }
            }
            if (i2 != 0) {
                return skill(IntentType.TIMER, MapsKt.mapOf(TuplesKt.to("action", "alarm_status")), text, 0.94f);
            }
            return words.contains("через") ? alarmAfter(request, text) : alarm(words);
        }
        List<String> $this$any$iv8 = words;
        if (!($this$any$iv8 instanceof Collection) || !$this$any$iv8.isEmpty()) {
            Iterator it9 = $this$any$iv8.iterator();
            while (true) {
                if (!it9.hasNext()) {
                    z4 = false;
                    break;
                }
                Object element$iv8 = it9.next();
                if (TIMER_CANCEL_VERBS.contains((String) element$iv8)) {
                    z4 = true;
                    break;
                }
            }
        } else {
            z4 = false;
        }
        if (z4) {
            return skill(IntentType.TIMER, MapsKt.mapOf(TuplesKt.to("action", "cancel")), text, 0.94f);
        }
        if (StringsKt.contains$default((CharSequence) text, (CharSequence) "сколько", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "осталось", false, 2, (Object) null)) {
            return skill(IntentType.TIMER, MapsKt.mapOf(TuplesKt.to("action", "status")), text, 0.94f);
        }
        Integer minutes = extractMinutes(words);
        Integer seconds = extractSeconds(words);
        if (minutes == null && seconds == null) {
            return new BrainResult.Clarify("На сколько поставить таймер, сэр?", 0.5f);
        }
        IntentType intentType = IntentType.TIMER;
        String strJoinToString$default = CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        Pair[] pairArr = new Pair[2];
        pairArr[0] = TuplesKt.to("minutes", new Slot("minutes", Integer.valueOf(minutes != null ? minutes.intValue() : 0), 0.0f, 4, null));
        pairArr[1] = TuplesKt.to("seconds", new Slot("seconds", Integer.valueOf(seconds != null ? seconds.intValue() : 0), 0.0f, 4, null));
        return new BrainResult.SkillCall(new Intent(intentType, strJoinToString$default, MapsKt.mapOf(pairArr), 0.94f, ConfidenceLevel.HIGH), 0.94f);
    }

    private final BrainResult stopwatch(List<String> words, String text) {
        boolean z;
        String mode;
        List<String> $this$any$iv = words;
        if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    String it2 = (String) element$iv;
                    if (TIMER_CANCEL_VERBS.contains(it2)) {
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
            mode = "stop";
        } else {
            mode = StringsKt.contains$default((CharSequence) text, (CharSequence) "сколько", false, 2, (Object) null) ? "status" : "start";
        }
        return skill(IntentType.TIMER, MapsKt.mapOf(TuplesKt.to("action", NotificationCompat.CATEGORY_STOPWATCH), TuplesKt.to("mode", mode)), text, 0.94f);
    }

    private final BrainResult alarmAfter(UserRequest request, String text) {
        ReminderParser reminderParser = ReminderParser.INSTANCE;
        String text2 = request.getText();
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        Intrinsics.checkNotNullExpressionValue(localDateTimeNow, "now(...)");
        LocalDateTime at = reminderParser.parse(text2, localDateTimeNow).getAt();
        return at == null ? new BrainResult.Clarify("Через сколько разбудить, сэр?", 0.5f) : skill(IntentType.TIMER, MapsKt.mapOf(TuplesKt.to("action", NotificationCompat.CATEGORY_ALARM), TuplesKt.to("hour", Integer.valueOf(at.getHour())), TuplesKt.to("minute", Integer.valueOf(at.getMinute()))), text, 0.94f);
    }

    /* JADX WARN: Code duplicated, block: B:70:0x0129  */
    /* JADX WARN: Code duplicated, block: B:92:0x016c  */
    private final BrainResult alarm(List<String> words) {
        Object element$iv;
        int minute;
        boolean pm;
        int hour;
        int it;
        int index$iv = 0;
        Iterator<String> it2 = words.iterator();
        while (true) {
            if (it2.hasNext()) {
                Object item$iv = it2.next();
                String it3 = (String) item$iv;
                if (Intrinsics.areEqual(it3, "на") || Intrinsics.areEqual(it3, "в")) {
                    break;
                }
                index$iv++;
            } else {
                index$iv = -1;
                break;
            }
        }
        int na = index$iv;
        Iterable $this$filter$iv = RangesKt.until(na + 1, words.size());
        Collection destination$iv$iv = new ArrayList();
        for (Integer num : $this$filter$iv) {
            int i = num.intValue();
            if (((RuNumbers.INSTANCE.endingAt(words, i) == null || RuNumbers.INSTANCE.endingAt(words, i + 1) != null) ? 0 : 1) != 0) {
                destination$iv$iv.add(num);
            }
        }
        List numberEnds = (List) destination$iv$iv;
        Iterable $this$firstOrNull$iv = RangesKt.until(na + 1, words.size());
        Iterator<Integer> it4 = $this$firstOrNull$iv.iterator();
        do {
            if (it4.hasNext()) {
                element$iv = it4.next();
                int it5 = ((Number) element$iv).intValue();
                it = RuNumbers.INSTANCE.endingAt(words, it5) != null ? 1 : 0;
            } else {
                element$iv = null;
                break;
            }
        } while (it == 0);
        Integer num2 = (Integer) element$iv;
        if (num2 == null) {
            return new BrainResult.Clarify("На какое время поставить будильник, сэр?", 0.5f);
        }
        int first = num2.intValue();
        int hourEnd = first;
        Integer numEndingAt = RuNumbers.INSTANCE.endingAt(words, first);
        int tens = numEndingAt != null ? numEndingAt.intValue() : 0;
        Integer compound = RuNumbers.INSTANCE.endingAt(words, first + 1);
        if (tens == 20 && compound != null) {
            int iIntValue = compound.intValue() - tens;
            if (1 <= iIntValue && iIntValue < 4) {
                hourEnd = first + 1;
            }
        }
        Integer numEndingAt2 = RuNumbers.INSTANCE.endingAt(words, hourEnd);
        int hourRaw = numEndingAt2 != null ? numEndingAt2.intValue() : 0;
        Integer num3 = (Integer) CollectionsKt.lastOrNull(numberEnds);
        if (num3 != null) {
            int it6 = num3.intValue();
            int it7 = it6 > hourEnd ? 1 : 0;
            Integer num4 = it7 != 0 ? num3 : null;
            if (num4 != null) {
                int it8 = num4.intValue();
                Integer numEndingAt3 = RuNumbers.INSTANCE.endingAt(words, it8);
                if (numEndingAt3 != null) {
                    minute = numEndingAt3.intValue();
                } else {
                    minute = 0;
                }
            } else {
                minute = 0;
            }
        } else {
            minute = 0;
        }
        List<String> $this$any$iv = words;
        if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
            Iterator it9 = $this$any$iv.iterator();
            while (true) {
                if (it9.hasNext()) {
                    Object element$iv2 = it9.next();
                    String it10 = (String) element$iv2;
                    if (ALARM_PM_WORDS.contains(it10)) {
                        pm = true;
                        break;
                    }
                } else {
                    pm = false;
                    break;
                }
            }
        } else {
            pm = false;
        }
        if (!pm) {
            hour = hourRaw;
        } else if (1 <= hourRaw && hourRaw < 12) {
            hour = hourRaw + 12;
        } else {
            hour = hourRaw;
        }
        return skill(IntentType.TIMER, MapsKt.mapOf(TuplesKt.to("action", NotificationCompat.CATEGORY_ALARM), TuplesKt.to("hour", Integer.valueOf(hour)), TuplesKt.to("minute", Integer.valueOf(minute))), CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null), 0.94f);
    }

    private final Integer bareLevel(List<String> words) {
        Integer numEndingAt;
        int na = words.lastIndexOf("на");
        if (na >= 0) {
            int size = (words.size() - 1) - na;
            if (!(1 <= size && size < 4) || (numEndingAt = RuNumbers.INSTANCE.endingAt(words, CollectionsKt.getLastIndex(words))) == null) {
                return null;
            }
            int it = numEndingAt.intValue();
            if (it >= 0 && it < 101) {
                return numEndingAt;
            }
            return null;
        }
        return null;
    }

    private final Integer percentOf(List<String> words) {
        Object obj;
        String strTrimEnd;
        Integer intOrNull;
        List<String> $this$firstOrNull$iv = words;
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            obj = null;
            if (!it.hasNext()) {
                break;
            }
            Object element$iv = it.next();
            String it2 = (String) element$iv;
            if (StringsKt.endsWith$default(it2, "%", false, 2, (Object) null)) {
                obj = element$iv;
                break;
            }
        }
        String str = (String) obj;
        if (str == null || (strTrimEnd = StringsKt.trimEnd(str, '%')) == null || (intOrNull = StringsKt.toIntOrNull(strTrimEnd)) == null) {
            return RuNumbers.INSTANCE.before(words, PERCENT_WORDS);
        }
        int it3 = intOrNull.intValue();
        return Integer.valueOf(it3);
    }

    private final BrainResult sendTelegram(List<String> words, UserRequest request) {
        int i;
        List afterTg;
        int index$iv = 0;
        Iterator<String> it = words.iterator();
        while (true) {
            i = -1;
            if (it.hasNext()) {
                Object item$iv = it.next();
                String it2 = (String) item$iv;
                if (isTelegramToken(it2)) {
                    break;
                }
                index$iv++;
            } else {
                index$iv = -1;
                break;
            }
        }
        int tgIdx = index$iv;
        if (tgIdx < 0) {
            return null;
        }
        int index$iv2 = 0;
        for (Object item$iv2 : words) {
            String it3 = (String) item$iv2;
            if (TG_WRITE_VERBS.contains(it3)) {
                i = index$iv2;
                break;
            }
            index$iv2++;
        }
        int verbIdx = i;
        if (verbIdx < 0 || verbIdx > tgIdx) {
            return null;
        }
        Set skip = SetsKt.setOf((Object[]) new String[]{"в", "во", "на", "чат", "чате", "сообщение", "смс", "sms", "пожалуйста"});
        Iterable $this$filterNot$iv = words.subList(verbIdx + 1, tgIdx);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterNot$iv) {
            String it4 = (String) element$iv$iv;
            if (!skip.contains(it4)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List beforeTg = (List) destination$iv$iv;
        if (tgIdx + 1 < words.size()) {
            Iterable $this$filterNot$iv2 = words.subList(tgIdx + 1, words.size());
            Collection destination$iv$iv2 = new ArrayList();
            for (Object element$iv$iv2 : $this$filterNot$iv2) {
                String it5 = (String) element$iv$iv2;
                if (!skip.contains(it5)) {
                    destination$iv$iv2.add(element$iv$iv2);
                }
            }
            afterTg = (List) destination$iv$iv2;
        } else {
            afterTg = CollectionsKt.emptyList();
        }
        boolean chatAfter = beforeTg.isEmpty() && afterTg.size() >= 2 && isDative((String) CollectionsKt.first(afterTg));
        String chatRaw = chatAfter ? (String) CollectionsKt.first(afterTg) : StringsKt.trim((CharSequence) CollectionsKt.joinToString$default(beforeTg, StringUtils.SPACE, null, null, 0, null, null, 62, null)).toString();
        String message = StringsKt.trim((CharSequence) CollectionsKt.joinToString$default(chatAfter ? CollectionsKt.drop(afterTg, 1) : afterTg, StringUtils.SPACE, null, null, 0, null, null, 62, null)).toString();
        String chat = StringsKt.isBlank(chatRaw) ? null : normalizeChatName(chatRaw);
        IntentType intentType = IntentType.SEND_TELEGRAM;
        String strJoinToString$default = CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        Map $this$sendTelegram_u24lambda_u244 = MapsKt.createMapBuilder();
        String str = chat;
        if (!(str == null || StringsKt.isBlank(str))) {
            $this$sendTelegram_u24lambda_u244.put(JarvisActivity.CHAT, new Slot(JarvisActivity.CHAT, chat, 0.0f, 4, null));
        }
        if (!StringsKt.isBlank(message)) {
            $this$sendTelegram_u24lambda_u244.put(VoiceService.EXTRA_TEXT, new Slot(VoiceService.EXTRA_TEXT, message, 0.0f, 4, null));
        }
        Unit unit = Unit.INSTANCE;
        return new BrainResult.SkillCall(new Intent(intentType, strJoinToString$default, MapsKt.build($this$sendTelegram_u24lambda_u244), 0.94f, ConfidenceLevel.HIGH), 0.94f);
    }

    private final boolean isDative(String w) {
        return w.length() >= 3 && StringsKt.contains$default((CharSequence) "еуюи", StringsKt.last(w), false, 2, (Object) null);
    }

    private final boolean isTelegramToken(String w) {
        return Intrinsics.areEqual(w, "telegram") || Intrinsics.areEqual(w, "тг") || StringsKt.startsWith$default(w, "телеграм", false, 2, (Object) null);
    }

    private final String normalizeChatName(String raw) {
        String lowerCase = raw.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String n = StringsKt.trim((CharSequence) lowerCase).toString();
        return (StringsKt.startsWith$default(n, "избран", false, 2, (Object) null) || Intrinsics.areEqual(n, "saved") || StringsKt.contains$default((CharSequence) n, (CharSequence) "saved messages", false, 2, (Object) null)) ? "Избранное" : ContactPhraseNormalizer.INSTANCE.canonical(raw);
    }

    private final BrainResult sendSms(List<String> words, UserRequest request) {
        boolean hasVerb;
        boolean z;
        Object recipient;
        String text;
        List<String> $this$any$iv = words;
        if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    hasVerb = false;
                    break;
                }
                Object element$iv = it.next();
                if (SMS_VERBS.contains((String) element$iv)) {
                    hasVerb = true;
                    break;
                }
            }
        } else {
            hasVerb = false;
        }
        if (!hasVerb) {
            return null;
        }
        List<String> $this$any$iv2 = words;
        int i = 8;
        if (($this$any$iv2 instanceof Collection) && $this$any$iv2.isEmpty()) {
            z = false;
        } else {
            Iterator it2 = $this$any$iv2.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z = false;
                    break;
                }
                Object element$iv2 = it2.next();
                String it3 = (String) element$iv2;
                String[] strArr = new String[i];
                strArr[0] = "открой";
                strArr[1] = "откройте";
                strArr[2] = "запусти";
                strArr[3] = "нажми";
                strArr[4] = "найди";
                strArr[5] = "подожди";
                strArr[6] = "telegram";
                strArr[7] = "тг";
                if (SetsKt.setOf((Object[]) strArr).contains(it3) || StringsKt.startsWith$default(it3, "телеграм", false, 2, (Object) null)) {
                    z = true;
                    break;
                }
                i = 8;
            }
        }
        if (z) {
            return null;
        }
        List<String> $this$dropWhile$iv = words;
        boolean yielding$iv = false;
        ArrayList list$iv = new ArrayList();
        for (Object item$iv : $this$dropWhile$iv) {
            if (yielding$iv) {
                list$iv.add(item$iv);
            } else if (SMS_VERBS.contains((String) item$iv)) {
                list$iv.add(item$iv);
                yielding$iv = true;
            }
        }
        ArrayList $this$dropWhile$iv2 = list$iv;
        List after = CollectionsKt.drop($this$dropWhile$iv2, 1);
        if (CollectionsKt.contains(SetsKt.setOf((Object[]) new String[]{"в", "сюда", "тут", "там"}), CollectionsKt.firstOrNull(after))) {
            return null;
        }
        if (after.isEmpty()) {
            return new BrainResult.Clarify("Что написать в сообщении?", 0.5f);
        }
        if (after.size() == 1 && isDative((String) CollectionsKt.first(after))) {
            return new BrainResult.Clarify("Что написать, сэр?", 0.5f);
        }
        int index$iv = 0;
        Iterator it4 = after.iterator();
        while (true) {
            if (!it4.hasNext()) {
                index$iv = -1;
                break;
            }
            if (SetsKt.setOf((Object[]) new String[]{"сообщение", "смс", "sms"}).contains((String) it4.next())) {
                break;
            }
            index$iv++;
        }
        int msgIdx = index$iv;
        Set recipientStop = SetsKt.setOf((Object[]) new String[]{"что", "текст", "сообщение", "смс", "sms", "мне", "сюда", "туда", "пожалуйста", "на", "в", "поле", "экране"});
        if (msgIdx >= 0) {
            String strJoinToString$default = CollectionsKt.joinToString$default(CollectionsKt.take(after, msgIdx), StringUtils.SPACE, null, null, 0, null, null, 62, null);
            recipient = StringsKt.isBlank(strJoinToString$default) ? null : strJoinToString$default;
            List body = CollectionsKt.drop(after, msgIdx + 1);
            if (recipient == null && body.size() >= 2 && isDative((String) CollectionsKt.first(body))) {
                recipient = CollectionsKt.first((List<? extends Object>) body);
                text = StringsKt.trim((CharSequence) CollectionsKt.joinToString$default(CollectionsKt.drop(body, 1), StringUtils.SPACE, null, null, 0, null, null, 62, null)).toString();
            } else {
                text = StringsKt.trim((CharSequence) CollectionsKt.joinToString$default(body, StringUtils.SPACE, null, null, 0, null, null, 62, null)).toString();
            }
        } else if (after.size() < 2 || recipientStop.contains(CollectionsKt.first(after))) {
            recipient = null;
            text = StringsKt.trim((CharSequence) CollectionsKt.joinToString$default(after, StringUtils.SPACE, null, null, 0, null, null, 62, null)).toString();
        } else {
            recipient = CollectionsKt.first((List<? extends Object>) after);
            text = StringsKt.trim((CharSequence) CollectionsKt.joinToString$default(CollectionsKt.drop(after, 1), StringUtils.SPACE, null, null, 0, null, null, 62, null)).toString();
        }
        if (StringsKt.isBlank(text)) {
            return new BrainResult.Clarify("Что написать в сообщении?", 0.5f);
        }
        IntentType intentType = IntentType.SEND_SMS;
        String strJoinToString$default2 = CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        Map $this$sendSms_u24lambda_u245 = MapsKt.createMapBuilder();
        $this$sendSms_u24lambda_u245.put(VoiceService.EXTRA_TEXT, new Slot(VoiceService.EXTRA_TEXT, text, 0.0f, 4, null));
        CharSequence charSequence = (CharSequence) recipient;
        if (!(charSequence == null || StringsKt.isBlank(charSequence))) {
            $this$sendSms_u24lambda_u245.put("recipient", new Slot("recipient", recipient, 0.0f, 4, null));
        }
        Unit unit = Unit.INSTANCE;
        return new BrainResult.SkillCall(new Intent(intentType, strJoinToString$default2, MapsKt.build($this$sendSms_u24lambda_u245), 0.92f, ConfidenceLevel.HIGH), 0.92f);
    }

    /* JADX WARN: Code duplicated, block: B:58:0x00d4  */
    private final BrainResult createEvent(List<String> words, UserRequest request) {
        boolean hasVerb;
        boolean z;
        boolean hasDuration;
        boolean z2;
        boolean z3;
        List<String> $this$any$iv = words;
        if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    hasVerb = false;
                    break;
                }
                Object element$iv = it.next();
                String it2 = (String) element$iv;
                if (EVENT_VERBS.contains(it2)) {
                    hasVerb = true;
                    break;
                }
            }
        } else {
            hasVerb = false;
        }
        if (!hasVerb) {
            return null;
        }
        List<String> $this$any$iv2 = words;
        if (!($this$any$iv2 instanceof Collection) || !$this$any$iv2.isEmpty()) {
            Iterator it3 = $this$any$iv2.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    z = false;
                    break;
                }
                Object element$iv2 = it3.next();
                String it4 = (String) element$iv2;
                if (MINUTE_WORDS.contains(it4)) {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
        }
        if (z) {
            hasDuration = true;
        } else {
            List<String> $this$any$iv3 = words;
            if (!($this$any$iv3 instanceof Collection) || !$this$any$iv3.isEmpty()) {
                Iterator it5 = $this$any$iv3.iterator();
                while (true) {
                    if (!it5.hasNext()) {
                        z2 = false;
                        break;
                    }
                    Object element$iv3 = it5.next();
                    String it6 = (String) element$iv3;
                    if (SECOND_WORDS.contains(it6)) {
                        z2 = true;
                        break;
                    }
                }
            } else {
                z2 = false;
            }
            if (z2) {
                hasDuration = true;
            } else {
                List<String> $this$any$iv4 = words;
                if (!($this$any$iv4 instanceof Collection) || !$this$any$iv4.isEmpty()) {
                    Iterator it7 = $this$any$iv4.iterator();
                    while (true) {
                        if (!it7.hasNext()) {
                            z3 = false;
                            break;
                        }
                        Object element$iv4 = it7.next();
                        String it8 = (String) element$iv4;
                        if (HOUR_WORDS.contains(it8)) {
                            z3 = true;
                            break;
                        }
                    }
                } else {
                    z3 = false;
                }
                if (z3) {
                    hasDuration = true;
                } else {
                    hasDuration = false;
                }
            }
        }
        if (hasDuration) {
            return null;
        }
        List<String> $this$dropWhile$iv = words;
        boolean yielding$iv = false;
        ArrayList list$iv = new ArrayList();
        for (Object item$iv : $this$dropWhile$iv) {
            if (yielding$iv) {
                list$iv.add(item$iv);
            } else {
                String it9 = (String) item$iv;
                if (EVENT_VERBS.contains(it9)) {
                    list$iv.add(item$iv);
                    yielding$iv = true;
                }
            }
        }
        ArrayList $this$dropWhile$iv2 = list$iv;
        Iterable $this$dropWhile$iv3 = CollectionsKt.drop($this$dropWhile$iv2, 1);
        boolean yielding$iv2 = false;
        ArrayList list$iv2 = new ArrayList();
        for (Object item$iv2 : $this$dropWhile$iv3) {
            if (yielding$iv2) {
                list$iv2.add(item$iv2);
            } else {
                String it10 = (String) item$iv2;
                if (!EVENT_NOUNS.contains(it10)) {
                    list$iv2.add(item$iv2);
                    yielding$iv2 = true;
                }
            }
        }
        ArrayList $this$dropWhile$iv4 = list$iv2;
        List titleWords = $this$dropWhile$iv4;
        if (Intrinsics.areEqual(CollectionsKt.take(titleWords, 2), CollectionsKt.listOf((Object[]) new String[]{"в", "календарь"}))) {
            titleWords = CollectionsKt.drop(titleWords, 2);
        }
        List $this$dropWhile$iv5 = titleWords;
        boolean yielding$iv3 = false;
        ArrayList list$iv3 = new ArrayList();
        for (Object item$iv3 : $this$dropWhile$iv5) {
            if (yielding$iv3) {
                list$iv3.add(item$iv3);
            } else {
                String it11 = (String) item$iv3;
                if (!EVENT_NOUNS.contains(it11)) {
                    list$iv3.add(item$iv3);
                    yielding$iv3 = true;
                }
            }
        }
        ArrayList arrayList = list$iv3;
        return arrayList.isEmpty() ? new BrainResult.Clarify("Какое событие добавить в календарь?", 0.5f) : new BrainResult.SkillCall(new Intent(IntentType.CREATE_EVENT, CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null), MapsKt.mapOf(TuplesKt.to("title", new Slot("title", CollectionsKt.joinToString$default(arrayList, StringUtils.SPACE, null, null, 0, null, null, 62, null), 0.0f, 4, null))), 0.7f, ConfidenceLevel.MEDIUM), 0.7f);
    }

    /* JADX WARN: Code duplicated, block: B:44:0x00e0  */
    private final BrainResult launchApp(List<String> words, UserRequest request) {
        boolean force;
        String str;
        boolean z;
        boolean z2;
        boolean hasVerb;
        Iterable $this$dropWhile$iv;
        boolean z3;
        boolean z4;
        String text = CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        String str2 = "процесс";
        if (!StringsKt.contains$default((CharSequence) text, (CharSequence) "принудительн", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) text, (CharSequence) "force", false, 2, (Object) null)) {
            List<String> $this$any$iv = words;
            if (($this$any$iv instanceof Collection) && $this$any$iv.isEmpty()) {
                z3 = false;
            } else {
                Iterator it = $this$any$iv.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z3 = false;
                        break;
                    }
                    Object element$iv = it.next();
                    Iterable $this$any$iv2 = $this$any$iv;
                    if (SetsKt.setOf((Object[]) new String[]{"убей", "прибей", "выгрузи"}).contains((String) element$iv)) {
                        z3 = true;
                        break;
                    }
                    $this$any$iv = $this$any$iv2;
                }
            }
            if (!z3) {
                if (words.contains("останови")) {
                    List<String> $this$any$iv3 = words;
                    int $i$f$any = 0;
                    if (($this$any$iv3 instanceof Collection) && $this$any$iv3.isEmpty()) {
                        z4 = false;
                    } else {
                        Iterator it2 = $this$any$iv3.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                z4 = false;
                                break;
                            }
                            Object element$iv2 = it2.next();
                            String it3 = (String) element$iv2;
                            Iterable $this$any$iv4 = $this$any$iv3;
                            int $i$f$any2 = $i$f$any;
                            if (StringsKt.startsWith$default(it3, "приложен", false, 2, (Object) null) || StringsKt.startsWith$default(it3, "процесс", false, 2, (Object) null)) {
                                z4 = true;
                                break;
                            }
                            $this$any$iv3 = $this$any$iv4;
                            $i$f$any = $i$f$any2;
                        }
                    }
                    force = z4;
                }
            }
        }
        if (force) {
            str = "процесс";
        } else {
            List<String> $this$any$iv5 = words;
            boolean force2 = $this$any$iv5 instanceof Collection;
            if (force2 && $this$any$iv5.isEmpty()) {
                str = "процесс";
                z = false;
            } else {
                Iterator it4 = $this$any$iv5.iterator();
                while (true) {
                    if (!it4.hasNext()) {
                        str = str2;
                        z = false;
                        break;
                    }
                    Object element$iv3 = it4.next();
                    Iterator it5 = it4;
                    str = str2;
                    Iterable $this$any$iv6 = $this$any$iv5;
                    if (SetsKt.setOf((Object[]) new String[]{"закрой", "сверни"}).contains((String) element$iv3)) {
                        z = true;
                        break;
                    }
                    it4 = it5;
                    $this$any$iv5 = $this$any$iv6;
                    str2 = str;
                }
            }
            if (!z || !StringsKt.contains$default((CharSequence) text, (CharSequence) "насовсем", false, 2, (Object) null)) {
                List<String> $this$any$iv7 = words;
                if (!($this$any$iv7 instanceof Collection) || !$this$any$iv7.isEmpty()) {
                    Iterator it6 = $this$any$iv7.iterator();
                    while (true) {
                        if (!it6.hasNext()) {
                            z2 = false;
                            break;
                        }
                        Object element$iv4 = it6.next();
                        if (SetsKt.setOf((Object[]) new String[]{"закрой", "сверни"}).contains((String) element$iv4)) {
                            z2 = true;
                            break;
                        }
                    }
                } else {
                    z2 = false;
                }
                if (z2) {
                    List<String> $this$dropWhile$iv2 = words;
                    boolean yielding$iv = false;
                    ArrayList list$iv = new ArrayList();
                    for (Object item$iv : $this$dropWhile$iv2) {
                        if (yielding$iv) {
                            list$iv.add(item$iv);
                            $this$dropWhile$iv = $this$dropWhile$iv2;
                        } else {
                            $this$dropWhile$iv = $this$dropWhile$iv2;
                            if (SetsKt.setOf((Object[]) new String[]{"закрой", "сверни"}).contains((String) item$iv)) {
                                list$iv.add(item$iv);
                                yielding$iv = true;
                                $this$dropWhile$iv2 = $this$dropWhile$iv;
                            }
                        }
                        $this$dropWhile$iv2 = $this$dropWhile$iv;
                    }
                    Iterable $this$filterNot$iv = CollectionsKt.drop(list$iv, 1);
                    int $i$f$filterNot = 0;
                    Collection destination$iv$iv = new ArrayList();
                    for (Object element$iv$iv : $this$filterNot$iv) {
                        Iterable $this$filterNot$iv2 = $this$filterNot$iv;
                        int $i$f$filterNot2 = $i$f$filterNot;
                        if (!SetsKt.setOf((Object[]) new String[]{"приложение", "апп"}).contains((String) element$iv$iv)) {
                            destination$iv$iv.add(element$iv$iv);
                        }
                        $this$filterNot$iv = $this$filterNot$iv2;
                        $i$f$filterNot = $i$f$filterNot2;
                    }
                    String appName = StringsKt.trim((CharSequence) CollectionsKt.joinToString$default((List) destination$iv$iv, StringUtils.SPACE, null, null, 0, null, null, 62, null)).toString();
                    IntentType intentType = IntentType.LAUNCH_APP;
                    Map $this$launchApp_u24lambda_u248 = MapsKt.createMapBuilder();
                    $this$launchApp_u24lambda_u248.put("action", new Slot("action", "minimize", 0.0f, 4, null));
                    if (!StringsKt.isBlank(appName)) {
                        $this$launchApp_u24lambda_u248.put("app", new Slot("app", appName, 0.0f, 4, null));
                    }
                    Unit unit = Unit.INSTANCE;
                    return new BrainResult.SkillCall(new Intent(intentType, text, MapsKt.build($this$launchApp_u24lambda_u248), 0.88f, ConfidenceLevel.HIGH), 0.88f);
                }
                List<String> $this$any$iv8 = words;
                if (!($this$any$iv8 instanceof Collection) || !$this$any$iv8.isEmpty()) {
                    Iterator it7 = $this$any$iv8.iterator();
                    while (true) {
                        if (!it7.hasNext()) {
                            hasVerb = false;
                            break;
                        }
                        Object element$iv5 = it7.next();
                        if (LAUNCH_VERBS.contains((String) element$iv5)) {
                            hasVerb = true;
                            break;
                        }
                    }
                } else {
                    hasVerb = false;
                }
                if (!hasVerb) {
                    return null;
                }
                List<String> $this$dropWhile$iv3 = words;
                boolean yielding$iv2 = false;
                ArrayList list$iv2 = new ArrayList();
                for (Object item$iv2 : $this$dropWhile$iv3) {
                    if (yielding$iv2) {
                        list$iv2.add(item$iv2);
                    } else if (LAUNCH_VERBS.contains((String) item$iv2)) {
                        list$iv2.add(item$iv2);
                        yielding$iv2 = true;
                    }
                }
                Iterable $this$filterNot$iv3 = CollectionsKt.drop(list$iv2, 1);
                Collection destination$iv$iv2 = new ArrayList();
                for (Object element$iv$iv2 : $this$filterNot$iv3) {
                    if (!LAUNCH_FILLERS.contains((String) element$iv$iv2)) {
                        destination$iv$iv2.add(element$iv$iv2);
                    }
                }
                String appName2 = StringsKt.trim((CharSequence) CollectionsKt.joinToString$default((List) destination$iv$iv2, StringUtils.SPACE, null, null, 0, null, null, 62, null)).toString();
                return StringsKt.isBlank(appName2) ? new BrainResult.Clarify("Какое приложение открыть?", 0.5f) : new BrainResult.SkillCall(new Intent(IntentType.LAUNCH_APP, CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null), MapsKt.mapOf(TuplesKt.to("action", new Slot("action", AbstractCircuitBreaker.PROPERTY_NAME, 0.0f, 4, null)), TuplesKt.to("app", new Slot("app", appName2, 0.0f, 4, null))), 0.88f, ConfidenceLevel.HIGH), 0.88f);
            }
        }
        Set skip = SetsKt.setOf((Object[]) new String[]{"закрой", "сверни", "убей", "прибей", "выгрузи", "останови", "force", "stop", "приложение", "апп", str, "насовсем"});
        List<String> $this$filterNot$iv4 = words;
        int $i$f$filterNot3 = 0;
        Collection destination$iv$iv3 = new ArrayList();
        for (Object element$iv$iv3 : $this$filterNot$iv4) {
            String it8 = (String) element$iv$iv3;
            Iterable $this$filterNot$iv5 = $this$filterNot$iv4;
            int $i$f$filterNot4 = $i$f$filterNot3;
            if (!(StringsKt.startsWith$default(it8, "принудит", false, 2, (Object) null) || skip.contains(it8))) {
                destination$iv$iv3.add(element$iv$iv3);
            }
            $this$filterNot$iv4 = $this$filterNot$iv5;
            $i$f$filterNot3 = $i$f$filterNot4;
        }
        String appName3 = StringsKt.trim((CharSequence) CollectionsKt.joinToString$default((List) destination$iv$iv3, StringUtils.SPACE, null, null, 0, null, null, 62, null)).toString();
        IntentType intentType2 = IntentType.LAUNCH_APP;
        Map $this$launchApp_u24lambda_u244 = MapsKt.createMapBuilder();
        $this$launchApp_u24lambda_u244.put("action", new Slot("action", "force_stop", 0.0f, 4, null));
        if (!StringsKt.isBlank(appName3)) {
            $this$launchApp_u24lambda_u244.put("app", new Slot("app", appName3, 0.0f, 4, null));
        }
        Unit unit2 = Unit.INSTANCE;
        return new BrainResult.SkillCall(new Intent(intentType2, text, MapsKt.build($this$launchApp_u24lambda_u244), 0.9f, ConfidenceLevel.HIGH), 0.9f);
    }

    private final BrainResult callPhone(List<String> words, UserRequest request) {
        boolean z;
        List<String> $this$none$iv = words;
        if (!($this$none$iv instanceof Collection) || !$this$none$iv.isEmpty()) {
            Iterator it = $this$none$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    String it2 = (String) element$iv;
                    if (CALL_VERBS.contains(it2)) {
                        z = false;
                        break;
                    }
                } else {
                    z = true;
                    break;
                }
            }
        } else {
            z = true;
        }
        if (z) {
            return null;
        }
        List<String> $this$dropWhile$iv = words;
        boolean yielding$iv = false;
        ArrayList list$iv = new ArrayList();
        for (Object item$iv : $this$dropWhile$iv) {
            if (yielding$iv) {
                list$iv.add(item$iv);
            } else {
                String it3 = (String) item$iv;
                if (CALL_VERBS.contains(it3)) {
                    list$iv.add(item$iv);
                    yielding$iv = true;
                }
            }
        }
        ArrayList $this$dropWhile$iv2 = list$iv;
        String contact = StringsKt.trim((CharSequence) CollectionsKt.joinToString$default(CollectionsKt.drop($this$dropWhile$iv2, 1), StringUtils.SPACE, null, null, 0, null, null, 62, null)).toString();
        if (StringsKt.isBlank(contact)) {
            return new BrainResult.Clarify("Кому позвонить, сэр?", 0.5f);
        }
        return new BrainResult.SkillCall(new Intent(IntentType.CALL, CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null), MapsKt.mapOf(TuplesKt.to("contact", new Slot("contact", contact, 0.0f, 4, null))), 0.9f, ConfidenceLevel.HIGH), 0.9f);
    }

    /* JADX WARN: Code duplicated, block: B:50:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:66:0x0110  */
    /* JADX WARN: Code duplicated, block: B:81:0x0113 A[SYNTHETIC] */
    private final BrainResult contacts(List<String> words, UserRequest request) {
        boolean z;
        boolean has;
        char c;
        boolean z2;
        boolean z3;
        boolean z4;
        List<String> $this$any$iv = words;
        if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                Object element$iv = it.next();
                if (CONTACT_WORDS.contains((String) element$iv)) {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
        }
        if (!z) {
            List<String> $this$any$iv2 = words;
            if (!($this$any$iv2 instanceof Collection) || !$this$any$iv2.isEmpty()) {
                Iterator it2 = $this$any$iv2.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        z3 = false;
                        break;
                    }
                    Object element$iv2 = it2.next();
                    if (SetsKt.setOf((Object[]) new String[]{"найди", "какой"}).contains((String) element$iv2)) {
                        z3 = true;
                        break;
                    }
                }
            } else {
                z3 = false;
            }
            if (z3) {
                List<String> $this$any$iv3 = words;
                if (!($this$any$iv3 instanceof Collection) || !$this$any$iv3.isEmpty()) {
                    Iterator it3 = $this$any$iv3.iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            z4 = false;
                            break;
                        }
                        Object element$iv3 = it3.next();
                        String it4 = (String) element$iv3;
                        if (StringsKt.contains$default((CharSequence) it4, (CharSequence) "номер", false, 2, (Object) null) || Intrinsics.areEqual(it4, "контакт")) {
                            z4 = true;
                            break;
                        }
                    }
                } else {
                    z4 = false;
                }
                has = z4;
            }
        }
        if (!has) {
            return null;
        }
        List<String> $this$filterNot$iv = words;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterNot$iv) {
            String it5 = (String) element$iv$iv;
            if (CONTACT_WORDS.contains(it5)) {
                c = 2;
            } else {
                c = 2;
                if (!SetsKt.setOf((Object[]) new String[]{"найди", "какой", "номер", "у"}).contains(it5)) {
                    z2 = false;
                }
                if (!z2) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            z2 = true;
            if (!z2) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        String query = StringsKt.trim((CharSequence) CollectionsKt.joinToString$default((List) destination$iv$iv, StringUtils.SPACE, null, null, 0, null, null, 62, null)).toString();
        return StringsKt.isBlank(query) ? new BrainResult.Clarify("Какой контакт найти?", 0.5f) : new BrainResult.SkillCall(new Intent(IntentType.CONTACTS, CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null), MapsKt.mapOf(TuplesKt.to(SearchIntents.EXTRA_QUERY, new Slot(SearchIntents.EXTRA_QUERY, query, 0.0f, 4, null))), 0.85f, ConfidenceLevel.MEDIUM), 0.85f);
    }

    private final String settingName(List<String> words, String text) {
        boolean z;
        boolean z2;
        Object element$iv;
        Object element$iv2;
        String phrase;
        List<String> $this$any$iv = words;
        boolean z3 = true;
        if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                Object element$iv3 = it.next();
                if (WIFI_WORDS.contains((String) element$iv3)) {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
        }
        if (z || StringsKt.contains$default((CharSequence) text, (CharSequence) "вай фай", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "wi fi", false, 2, (Object) null)) {
            return EventMatcher.WIFI;
        }
        List<String> $this$any$iv2 = words;
        if (!($this$any$iv2 instanceof Collection) || !$this$any$iv2.isEmpty()) {
            Iterator it2 = $this$any$iv2.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z2 = false;
                    break;
                }
                Object element$iv4 = it2.next();
                String it3 = (String) element$iv4;
                if (BT_WORDS.contains(it3) || StringsKt.startsWith$default(it3, "блютуз", false, 2, (Object) null)) {
                    z2 = true;
                    break;
                }
            }
        } else {
            z2 = false;
        }
        if (z2 || StringsKt.contains$default((CharSequence) text, (CharSequence) "блютус", false, 2, (Object) null)) {
            return "bluetooth";
        }
        List<String> $this$any$iv3 = words;
        if (!($this$any$iv3 instanceof Collection) || !$this$any$iv3.isEmpty()) {
            Iterator it4 = $this$any$iv3.iterator();
            do {
                if (!it4.hasNext()) {
                    z3 = false;
                    break;
                }
                element$iv = it4.next();
            } while (!AIRPLANE_WORDS.contains((String) element$iv));
        } else {
            z3 = false;
        }
        if (z3 || StringsKt.contains$default((CharSequence) text, (CharSequence) "режим полёта", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "режим полета", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "режим самол", false, 2, (Object) null)) {
            return "airplane";
        }
        Iterable iterableEntrySet = PHRASE_SETTINGS.entrySet();
        Intrinsics.checkNotNullExpressionValue(iterableEntrySet, "<get-entries>(...)");
        Iterable $this$firstOrNull$iv = iterableEntrySet;
        Iterator it5 = $this$firstOrNull$iv.iterator();
        do {
            if (!it5.hasNext()) {
                element$iv2 = null;
                break;
            }
            element$iv2 = it5.next();
            Map.Entry entry = (Map.Entry) element$iv2;
            Intrinsics.checkNotNull(entry);
            Object key = entry.getKey();
            Intrinsics.checkNotNullExpressionValue(key, "component1(...)");
            phrase = (String) key;
        } while (!StringsKt.contains$default((CharSequence) text, (CharSequence) phrase, false, 2, (Object) null));
        Map.Entry entry2 = (Map.Entry) element$iv2;
        if (entry2 != null) {
            return (String) entry2.getValue();
        }
        return null;
    }

    private final BrainResult systemSetting(List<String> words, UserRequest request) {
        boolean z;
        boolean z2;
        String name;
        boolean z3;
        boolean z4;
        boolean z5;
        String value;
        Object element$iv;
        String key;
        List<String> $this$any$iv = words;
        if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv2 = it.next();
                    if (SetsKt.setOf((Object[]) new String[]{"введи", "напиши", "напечатай", "нажми", "тапни", "кликни", "найди"}).contains((String) element$iv2)) {
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
            return null;
        }
        String text = CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        if (StringsKt.contains$default((CharSequence) text, (CharSequence) "настройк", false, 2, (Object) null)) {
            Iterable iterableEntrySet = SETTINGS_SECTIONS.entrySet();
            Intrinsics.checkNotNullExpressionValue(iterableEntrySet, "<get-entries>(...)");
            Iterable $this$firstOrNull$iv = iterableEntrySet;
            Iterator it2 = $this$firstOrNull$iv.iterator();
            do {
                if (!it2.hasNext()) {
                    element$iv = null;
                    break;
                }
                element$iv = it2.next();
                Map.Entry entry = (Map.Entry) element$iv;
                Intrinsics.checkNotNull(entry);
                Object key2 = entry.getKey();
                Intrinsics.checkNotNullExpressionValue(key2, "component1(...)");
                key = (String) key2;
            } while (!StringsKt.contains$default((CharSequence) text, (CharSequence) key, false, 2, (Object) null));
            Map.Entry entry2 = (Map.Entry) element$iv;
            if (entry2 != null) {
                Object value2 = entry2.getValue();
                Intrinsics.checkNotNullExpressionValue(value2, "component2(...)");
                String section = (String) value2;
                return skill(IntentType.SYSTEM_SETTING, MapsKt.mapOf(TuplesKt.to("name", section), TuplesKt.to("value", AbstractCircuitBreaker.PROPERTY_NAME)), text, 0.9f);
            }
        }
        List<String> $this$any$iv2 = words;
        if (!($this$any$iv2 instanceof Collection) || !$this$any$iv2.isEmpty()) {
            Iterator it3 = $this$any$iv2.iterator();
            while (true) {
                if (it3.hasNext()) {
                    Object element$iv3 = it3.next();
                    String it4 = (String) element$iv3;
                    if (StringsKt.startsWith$default(it4, "песн", false, 2, (Object) null) || Intrinsics.areEqual(it4, "трек") || MUSIC_NOUNS.contains(it4)) {
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
        if (z2 || (name = settingName(words, text)) == null) {
            return null;
        }
        List<String> $this$any$iv3 = words;
        if (!($this$any$iv3 instanceof Collection) || !$this$any$iv3.isEmpty()) {
            Iterator it5 = $this$any$iv3.iterator();
            while (true) {
                if (it5.hasNext()) {
                    Object element$iv4 = it5.next();
                    String it6 = (String) element$iv4;
                    if (OFF_VERBS.contains(it6) || Intrinsics.areEqual(it6, "убери")) {
                        z3 = true;
                        break;
                    }
                } else {
                    z3 = false;
                    break;
                }
            }
        } else {
            z3 = false;
        }
        if (z3) {
            value = "off";
        } else if (SWITCH_SETTINGS.contains(name)) {
            value = "on";
        } else {
            List<String> $this$any$iv4 = words;
            if (!($this$any$iv4 instanceof Collection) || !$this$any$iv4.isEmpty()) {
                Iterator it7 = $this$any$iv4.iterator();
                while (true) {
                    if (it7.hasNext()) {
                        Object element$iv5 = it7.next();
                        if (ON_VERBS.contains((String) element$iv5)) {
                            z4 = true;
                            break;
                        }
                    } else {
                        z4 = false;
                        break;
                    }
                }
            } else {
                z4 = false;
            }
            if (z4) {
                value = "on";
            } else {
                List<String> $this$any$iv5 = words;
                if (!($this$any$iv5 instanceof Collection) || !$this$any$iv5.isEmpty()) {
                    Iterator it8 = $this$any$iv5.iterator();
                    while (true) {
                        if (it8.hasNext()) {
                            Object element$iv6 = it8.next();
                            if (SetsKt.setOf((Object[]) new String[]{"открой", "откройте", "покажи"}).contains((String) element$iv6)) {
                                z5 = true;
                                break;
                            }
                        } else {
                            z5 = false;
                            break;
                        }
                    }
                } else {
                    z5 = false;
                }
                value = z5 ? AbstractCircuitBreaker.PROPERTY_NAME : AbstractCircuitBreaker.PROPERTY_NAME;
            }
        }
        return new BrainResult.SkillCall(new Intent(IntentType.SYSTEM_SETTING, text, MapsKt.mapOf(TuplesKt.to("name", new Slot("name", name, 0.0f, 4, null)), TuplesKt.to("value", new Slot("value", value, 0.0f, 4, null))), 0.9f, ConfidenceLevel.HIGH), 0.9f);
    }

    /* JADX WARN: Code duplicated, block: B:299:0x06b9  */
    /* JADX WARN: Code duplicated, block: B:377:0x08a6  */
    /* JADX WARN: Code duplicated, block: B:466:0x06bc A[SYNTHETIC] */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00a6, code lost:
    
        if (r3 != false) goto L435;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.jarvis.brain.BrainResult uiActions(java.util.List<java.lang.String> r38, com.jarvis.core.common.model.UserRequest r39) {
        /*
            Method dump skipped, instruction units count: 2902
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jarvis.brain.RuleBasedBrain.uiActions(java.util.List, com.jarvis.core.common.model.UserRequest):com.jarvis.brain.BrainResult");
    }

    private final int daysUntil(DayOfWeek day) {
        return Math.floorMod(day.getValue() - LocalDate.now().getDayOfWeek().getValue(), 7);
    }

    /* JADX WARN: Code duplicated, block: B:59:0x010d  */
    /* JADX WARN: Code duplicated, block: B:60:0x0113  */
    /* JADX WARN: Code duplicated, block: B:62:0x0120  */
    /* JADX WARN: Code duplicated, block: B:63:0x0127  */
    /* JADX WARN: Code duplicated, block: B:65:0x0134  */
    /* JADX WARN: Code duplicated, block: B:66:0x0139  */
    /* JADX WARN: Code duplicated, block: B:68:0x0144  */
    /* JADX WARN: Code duplicated, block: B:69:0x014f  */
    /* JADX WARN: Code duplicated, block: B:72:0x0162  */
    /* JADX WARN: Code duplicated, block: B:78:0x017a  */
    /* JADX WARN: Code duplicated, block: B:79:0x018b  */
    /* JADX WARN: Code duplicated, block: B:82:0x019f  */
    /* JADX WARN: Code duplicated, block: B:86:0x01a8  */
    /* JADX WARN: Code duplicated, block: B:88:0x01ab  */
    /* JADX WARN: Code duplicated, block: B:91:0x01ca  */
    /* JADX WARN: Code duplicated, block: B:92:0x01e1 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:93:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:94:0x01f7  */
    /* JADX WARN: Code duplicated, block: B:97:0x0175 A[SYNTHETIC] */
    private final BrainResult weather(List<String> words, UserRequest request) {
        boolean z;
        String city;
        Iterator it;
        Object element$iv;
        Map.Entry it2;
        Integer day;
        Map $this$weather_u24lambda_u244;
        String str;
        boolean z2;
        boolean hit;
        boolean hit2;
        String text = CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        List<String> $this$any$iv = words;
        if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
            Iterator it3 = $this$any$iv.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    z = false;
                    break;
                }
                Object element$iv2 = it3.next();
                if (WEATHER_WORDS.contains((String) element$iv2)) {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
        }
        boolean hit3 = z || StringsKt.contains$default((CharSequence) text, (CharSequence) "градус", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "на улице", false, 2, (Object) null);
        if (!hit3) {
            return null;
        }
        String strExtractCityAfter = extractCityAfter(words, SetsKt.setOf((Object[]) new String[]{"в", "во"}));
        if (strExtractCityAfter == null) {
            strExtractCityAfter = extractCityAfter(words, SetsKt.setOf("погода"));
        }
        if (strExtractCityAfter != null) {
            String c = strExtractCityAfter;
            String low = c.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(low, "toLowerCase(...)");
            if (!StringsKt.startsWith$default(low, "на ", false, 2, (Object) null) && !StringsKt.startsWith$default(low, "выходн", false, 2, (Object) null)) {
                Iterable $this$any$iv2 = WEEKDAY_WORDS.keySet();
                if (!($this$any$iv2 instanceof Collection) || !((Collection) $this$any$iv2).isEmpty()) {
                    Iterator it4 = $this$any$iv2.iterator();
                    while (true) {
                        if (!it4.hasNext()) {
                            hit2 = false;
                            break;
                        }
                        Object element$iv3 = it4.next();
                        boolean hit4 = hit3;
                        if (StringsKt.startsWith$default((String) element$iv3, low, false, 2, (Object) null)) {
                            hit2 = true;
                            break;
                        }
                        hit3 = hit4;
                    }
                } else {
                    hit2 = false;
                }
                if (!hit2) {
                    hit = false;
                }
                if (hit) {
                }
                city = strExtractCityAfter;
                if (StringsKt.contains$default((CharSequence) text, (CharSequence) "послезавтра", false, 2, (Object) null)) {
                    day = 2;
                } else if (StringsKt.contains$default((CharSequence) text, (CharSequence) "завтра", false, 2, (Object) null)) {
                    day = 1;
                } else if (StringsKt.contains$default((CharSequence) text, (CharSequence) "сегодня", false, 2, (Object) null)) {
                    day = 0;
                } else if (StringsKt.contains$default((CharSequence) text, (CharSequence) "выходн", false, 2, (Object) null)) {
                    day = Integer.valueOf(daysUntil(DayOfWeek.SATURDAY));
                } else {
                    Iterable $this$firstOrNull$iv = WEEKDAY_WORDS.entrySet();
                    it = $this$firstOrNull$iv.iterator();
                    do {
                        if (!it.hasNext()) {
                            element$iv = null;
                            break;
                        }
                        element$iv = it.next();
                    } while (!words.contains(((Map.Entry) element$iv).getKey()));
                    it2 = (Map.Entry) element$iv;
                    if (it2 != null) {
                        day = Integer.valueOf(daysUntil((DayOfWeek) it2.getValue()));
                    } else {
                        day = null;
                    }
                }
                IntentType intentType = IntentType.WEATHER;
                $this$weather_u24lambda_u244 = MapsKt.createMapBuilder();
                str = city;
                if (str != null || StringsKt.isBlank(str)) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (!z2) {
                    $this$weather_u24lambda_u244.put("city", new Slot("city", city, 0.0f, 4, null));
                }
                if (StringsKt.contains$default((CharSequence) text, (CharSequence) "недел", false, 2, (Object) null)) {
                    $this$weather_u24lambda_u244.put("week", new Slot("week", true, 0.0f, 4, null));
                } else if (day != null) {
                    $this$weather_u24lambda_u244.put("day", new Slot("day", day, 0.0f, 4, null));
                }
                Unit unit = Unit.INSTANCE;
                return new BrainResult.SkillCall(new Intent(intentType, text, MapsKt.build($this$weather_u24lambda_u244), 0.92f, ConfidenceLevel.HIGH), 0.92f);
            }
            hit = true;
            if (hit) {
            }
            city = strExtractCityAfter;
            if (StringsKt.contains$default((CharSequence) text, (CharSequence) "послезавтра", false, 2, (Object) null)) {
                day = 2;
            } else if (StringsKt.contains$default((CharSequence) text, (CharSequence) "завтра", false, 2, (Object) null)) {
                day = 1;
            } else if (StringsKt.contains$default((CharSequence) text, (CharSequence) "сегодня", false, 2, (Object) null)) {
                day = 0;
            } else if (StringsKt.contains$default((CharSequence) text, (CharSequence) "выходн", false, 2, (Object) null)) {
                day = Integer.valueOf(daysUntil(DayOfWeek.SATURDAY));
            } else {
                Iterable $this$firstOrNull$iv2 = WEEKDAY_WORDS.entrySet();
                it = $this$firstOrNull$iv2.iterator();
                do {
                    if (!it.hasNext()) {
                        element$iv = null;
                        break;
                    }
                    element$iv = it.next();
                } while (!words.contains(((Map.Entry) element$iv).getKey()));
                it2 = (Map.Entry) element$iv;
                if (it2 != null) {
                    day = Integer.valueOf(daysUntil((DayOfWeek) it2.getValue()));
                } else {
                    day = null;
                }
            }
            IntentType intentType2 = IntentType.WEATHER;
            $this$weather_u24lambda_u244 = MapsKt.createMapBuilder();
            str = city;
            if (str != null) {
                z2 = true;
            } else {
                z2 = true;
            }
            if (!z2) {
                $this$weather_u24lambda_u244.put("city", new Slot("city", city, 0.0f, 4, null));
            }
            if (StringsKt.contains$default((CharSequence) text, (CharSequence) "недел", false, 2, (Object) null)) {
                $this$weather_u24lambda_u244.put("week", new Slot("week", true, 0.0f, 4, null));
            } else if (day != null) {
                $this$weather_u24lambda_u244.put("day", new Slot("day", day, 0.0f, 4, null));
            }
            Unit unit2 = Unit.INSTANCE;
            return new BrainResult.SkillCall(new Intent(intentType2, text, MapsKt.build($this$weather_u24lambda_u244), 0.92f, ConfidenceLevel.HIGH), 0.92f);
        }
        strExtractCityAfter = null;
        city = strExtractCityAfter;
        if (StringsKt.contains$default((CharSequence) text, (CharSequence) "послезавтра", false, 2, (Object) null)) {
            day = 2;
        } else if (StringsKt.contains$default((CharSequence) text, (CharSequence) "завтра", false, 2, (Object) null)) {
            day = 1;
        } else if (StringsKt.contains$default((CharSequence) text, (CharSequence) "сегодня", false, 2, (Object) null)) {
            day = 0;
        } else if (StringsKt.contains$default((CharSequence) text, (CharSequence) "выходн", false, 2, (Object) null)) {
            day = Integer.valueOf(daysUntil(DayOfWeek.SATURDAY));
        } else {
            Iterable $this$firstOrNull$iv3 = WEEKDAY_WORDS.entrySet();
            it = $this$firstOrNull$iv3.iterator();
            do {
                if (!it.hasNext()) {
                    element$iv = null;
                    break;
                }
                element$iv = it.next();
            } while (!words.contains(((Map.Entry) element$iv).getKey()));
            it2 = (Map.Entry) element$iv;
            if (it2 != null) {
                day = Integer.valueOf(daysUntil((DayOfWeek) it2.getValue()));
            } else {
                day = null;
            }
        }
        IntentType intentType3 = IntentType.WEATHER;
        $this$weather_u24lambda_u244 = MapsKt.createMapBuilder();
        str = city;
        if (str != null) {
            z2 = true;
        } else {
            z2 = true;
        }
        if (!z2) {
            $this$weather_u24lambda_u244.put("city", new Slot("city", city, 0.0f, 4, null));
        }
        if (StringsKt.contains$default((CharSequence) text, (CharSequence) "недел", false, 2, (Object) null)) {
            $this$weather_u24lambda_u244.put("week", new Slot("week", true, 0.0f, 4, null));
        } else if (day != null) {
            $this$weather_u24lambda_u244.put("day", new Slot("day", day, 0.0f, 4, null));
        }
        Unit unit3 = Unit.INSTANCE;
        return new BrainResult.SkillCall(new Intent(intentType3, text, MapsKt.build($this$weather_u24lambda_u244), 0.92f, ConfidenceLevel.HIGH), 0.92f);
    }

    /* JADX WARN: Code duplicated, block: B:100:0x01a7  */
    /* JADX WARN: Code duplicated, block: B:102:0x01af  */
    /* JADX WARN: Code duplicated, block: B:109:0x01d7  */
    /* JADX WARN: Code duplicated, block: B:112:0x01e2  */
    /* JADX WARN: Code duplicated, block: B:115:0x01ec  */
    /* JADX WARN: Code duplicated, block: B:118:0x0218 A[LOOP:25: B:113:0x01e6->B:118:0x0218, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:122:0x0223  */
    /* JADX WARN: Code duplicated, block: B:124:0x022c  */
    /* JADX WARN: Code duplicated, block: B:127:0x0237  */
    /* JADX WARN: Code duplicated, block: B:130:0x0241  */
    /* JADX WARN: Code duplicated, block: B:133:0x0255 A[LOOP:24: B:128:0x023b->B:133:0x0255, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:136:0x025b  */
    /* JADX WARN: Code duplicated, block: B:138:0x0263  */
    /* JADX WARN: Code duplicated, block: B:141:0x026e  */
    /* JADX WARN: Code duplicated, block: B:144:0x0278  */
    /* JADX WARN: Code duplicated, block: B:147:0x02a5 A[LOOP:23: B:142:0x0272->B:147:0x02a5, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:150:0x02b3  */
    /* JADX WARN: Code duplicated, block: B:152:0x02c4  */
    /* JADX WARN: Code duplicated, block: B:154:0x02d0  */
    /* JADX WARN: Code duplicated, block: B:158:0x02f1  */
    /* JADX WARN: Code duplicated, block: B:161:0x02fb  */
    /* JADX WARN: Code duplicated, block: B:164:0x0306  */
    /* JADX WARN: Code duplicated, block: B:167:0x0310  */
    /* JADX WARN: Code duplicated, block: B:170:0x033a A[LOOP:22: B:165:0x030a->B:170:0x033a, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:173:0x0346  */
    /* JADX WARN: Code duplicated, block: B:175:0x034e  */
    /* JADX WARN: Code duplicated, block: B:178:0x0359  */
    /* JADX WARN: Code duplicated, block: B:181:0x0363  */
    /* JADX WARN: Code duplicated, block: B:183:0x0373  */
    /* JADX WARN: Code duplicated, block: B:186:0x0389  */
    /* JADX WARN: Code duplicated, block: B:187:0x038b  */
    /* JADX WARN: Code duplicated, block: B:191:0x0392 A[LOOP:2: B:179:0x035d->B:191:0x0392, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:194:0x039a  */
    /* JADX WARN: Code duplicated, block: B:200:0x03d0  */
    /* JADX WARN: Code duplicated, block: B:214:0x0426  */
    /* JADX WARN: Code duplicated, block: B:216:0x042e  */
    /* JADX WARN: Code duplicated, block: B:219:0x0439  */
    /* JADX WARN: Code duplicated, block: B:222:0x0443  */
    /* JADX WARN: Code duplicated, block: B:230:0x0462  */
    /* JADX WARN: Code duplicated, block: B:537:0x0b29  */
    /* JADX WARN: Code duplicated, block: B:55:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:611:0x01a4 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:612:0x01a0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:613:0x0395 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:614:0x0390 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:615:0x0455 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:616:0x0453 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:617:? A[LOOP:3: B:220:0x043d->B:617:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:636:0x0b2c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:65:0x011d  */
    /* JADX WARN: Code duplicated, block: B:673:0x033f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:674:0x0338 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:675:0x02a3 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:676:0x02ac A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:677:0x0258 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:678:0x0253 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:679:0x021a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:680:0x0216 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:681:0x0139 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:682:0x0137 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:683:? A[LOOP:26: B:66:0x0121->B:683:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:68:0x0127  */
    /* JADX WARN: Code duplicated, block: B:80:0x0166  */
    /* JADX WARN: Code duplicated, block: B:85:0x0178  */
    /* JADX WARN: Code duplicated, block: B:88:0x0182  */
    /* JADX WARN: Code duplicated, block: B:90:0x0193  */
    /* JADX WARN: Code duplicated, block: B:94:0x019d  */
    /* JADX WARN: Code duplicated, block: B:97:0x01a2 A[LOOP:1: B:86:0x017c->B:97:0x01a2, LOOP_END] */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x02db, code lost:
    
        if (kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r2, (java.lang.CharSequence) "музык", false, 2, (java.lang.Object) null) == false) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:226:0x0456, code lost:
    
        if (r4 != false) goto L596;
     */
    /* JADX WARN: Code restructure failed: missing block: B:506:0x0a76, code lost:
    
        if (r37.contains("композицию") == false) goto L544;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.jarvis.brain.BrainResult music(java.util.List<java.lang.String> r37, com.jarvis.core.common.model.UserRequest r38) {
        /*
            Method dump skipped, instruction units count: 3477
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jarvis.brain.RuleBasedBrain.music(java.util.List, com.jarvis.core.common.model.UserRequest):com.jarvis.brain.BrainResult");
    }

    private final String extractAfter(List<String> words, Set<String> dropTokens) {
        List<String> $this$filterNot$iv = words;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterNot$iv) {
            String it = (String) element$iv$iv;
            if (!(dropTokens.contains(it) || Intrinsics.areEqual(it, "мне") || Intrinsics.areEqual(it, "пожалуйста"))) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return StringsKt.trim((CharSequence) CollectionsKt.joinToString$default((List) destination$iv$iv, StringUtils.SPACE, null, null, 0, null, null, 62, null)).toString();
    }

    /* JADX WARN: Code duplicated, block: B:167:0x0124 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:168:0x0130 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:53:0x00cd A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:54:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:59:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:62:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:64:0x010e  */
    /* JADX WARN: Code duplicated, block: B:70:0x0121  */
    /* JADX WARN: Code duplicated, block: B:73:0x0126 A[LOOP:1: B:60:0x00eb->B:73:0x0126, LOOP_END] */
    private final BrainResult knowledge(List<String> words, UserRequest request) {
        boolean z;
        CharSequence charSequence;
        boolean trigger;
        List<String> $this$any$iv;
        Iterator it;
        String str;
        boolean z2;
        String it2;
        boolean trigger2;
        Iterator it3;
        Iterable $this$any$iv2;
        boolean z3;
        String query;
        String text = CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        List<String> $this$any$iv3 = words;
        if (!($this$any$iv3 instanceof Collection) || !$this$any$iv3.isEmpty()) {
            Iterator it4 = $this$any$iv3.iterator();
            while (true) {
                if (!it4.hasNext()) {
                    z = false;
                    break;
                }
                Object element$iv = it4.next();
                if (KNOWLEDGE_VERBS.contains((String) element$iv)) {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
        }
        String str2 = "расскажи о";
        if (!z && !StringsKt.startsWith$default(text, "кто такой", false, 2, (Object) null) && !StringsKt.startsWith$default(text, "кто такая", false, 2, (Object) null) && !StringsKt.startsWith$default(text, "кто такие", false, 2, (Object) null) && !StringsKt.startsWith$default(text, "кто это", false, 2, (Object) null) && !StringsKt.startsWith$default(text, "что такое", false, 2, (Object) null) && !StringsKt.startsWith$default(text, "что это такое", false, 2, (Object) null) && !StringsKt.startsWith$default(text, "что за ", false, 2, (Object) null) && !StringsKt.startsWith$default(text, "расскажи про", false, 2, (Object) null) && !StringsKt.startsWith$default(text, "расскажи о", false, 2, (Object) null)) {
            charSequence = StringUtils.SPACE;
            if (!StringsKt.startsWith$default(text, "расскажи коротко", false, 2, (Object) null) && !StringsKt.startsWith$default(text, "расскажи кратко", false, 2, (Object) null) && !StringsKt.startsWith$default(text, "коротко про", false, 2, (Object) null) && !StringsKt.startsWith$default(text, "кратко про", false, 2, (Object) null)) {
                trigger = false;
            }
            if (!trigger) {
                return null;
            }
            $this$any$iv = words;
            if (($this$any$iv instanceof Collection) && $this$any$iv.isEmpty()) {
                str = "расскажи о";
                z2 = false;
            } else {
                it = $this$any$iv.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        str = str2;
                        z2 = false;
                        break;
                    }
                    Object element$iv2 = it.next();
                    it2 = (String) element$iv2;
                    trigger2 = trigger;
                    it3 = it;
                    str = str2;
                    $this$any$iv2 = $this$any$iv;
                    if (!StringsKt.startsWith$default(it2, "анекдот", false, 2, (Object) null) || Intrinsics.areEqual(it2, "шутку") || Intrinsics.areEqual(it2, "joke")) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (z3) {
                        z2 = true;
                        break;
                    }
                    trigger = trigger2;
                    it = it3;
                    $this$any$iv = $this$any$iv2;
                    str2 = str;
                }
            }
            if (z2 && !Intrinsics.areEqual(text, "кто ты") && !StringsKt.startsWith$default(text, "кто ты ", false, 2, (Object) null)) {
                if (!StringsKt.contains$default((CharSequence) text, (CharSequence) "думаешь", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "мнение", false, 2, (Object) null)) {
                    return null;
                }
                if (StringsKt.contains$default((CharSequence) text, (CharSequence) "как считаешь", false, 2, (Object) null) || StringsKt.startsWith$default(text, "как тебе", false, 2, (Object) null)) {
                    return null;
                }
                if (StringsKt.startsWith$default(text, "кто такие", false, 2, (Object) null)) {
                    query = StringsKt.trim((CharSequence) StringsKt.removePrefix(text, (CharSequence) "кто такие")).toString();
                } else if (StringsKt.startsWith$default(text, "кто такой", false, 2, (Object) null)) {
                    query = StringsKt.trim((CharSequence) StringsKt.removePrefix(text, (CharSequence) "кто такой")).toString();
                } else if (StringsKt.startsWith$default(text, "кто такая", false, 2, (Object) null)) {
                    query = StringsKt.trim((CharSequence) StringsKt.removePrefix(text, (CharSequence) "кто такая")).toString();
                } else if (StringsKt.startsWith$default(text, "кто это", false, 2, (Object) null)) {
                    query = StringsKt.trim((CharSequence) StringsKt.removePrefix(text, (CharSequence) "кто это")).toString();
                } else if (StringsKt.startsWith$default(text, "что такое", false, 2, (Object) null)) {
                    query = StringsKt.trim((CharSequence) StringsKt.removePrefix(text, (CharSequence) "что такое")).toString();
                } else if (StringsKt.startsWith$default(text, "что это такое", false, 2, (Object) null)) {
                    query = StringsKt.trim((CharSequence) StringsKt.removePrefix(text, (CharSequence) "что это такое")).toString();
                } else if (StringsKt.startsWith$default(text, "что за ", false, 2, (Object) null)) {
                    query = StringsKt.trim((CharSequence) StringsKt.removePrefix(text, (CharSequence) "что за ")).toString();
                } else if (StringsKt.startsWith$default(text, "расскажи коротко про", false, 2, (Object) null)) {
                    query = StringsKt.trim((CharSequence) StringsKt.removePrefix(text, (CharSequence) "расскажи коротко про")).toString();
                } else if (StringsKt.startsWith$default(text, "расскажи кратко про", false, 2, (Object) null)) {
                    query = StringsKt.trim((CharSequence) StringsKt.removePrefix(text, (CharSequence) "расскажи кратко про")).toString();
                } else if (StringsKt.startsWith$default(text, "расскажи коротко о", false, 2, (Object) null)) {
                    query = StringsKt.trim((CharSequence) StringsKt.removePrefix(text, (CharSequence) "расскажи коротко о")).toString();
                } else if (StringsKt.startsWith$default(text, "расскажи кратко о", false, 2, (Object) null)) {
                    query = StringsKt.trim((CharSequence) StringsKt.removePrefix(text, (CharSequence) "расскажи кратко о")).toString();
                } else if (StringsKt.startsWith$default(text, "коротко про", false, 2, (Object) null)) {
                    query = StringsKt.trim((CharSequence) StringsKt.removePrefix(text, (CharSequence) "коротко про")).toString();
                } else if (StringsKt.startsWith$default(text, "кратко про", false, 2, (Object) null)) {
                    query = StringsKt.trim((CharSequence) StringsKt.removePrefix(text, (CharSequence) "кратко про")).toString();
                } else if (StringsKt.startsWith$default(text, "расскажи про", false, 2, (Object) null)) {
                    query = StringsKt.trim((CharSequence) StringsKt.removePrefix(text, (CharSequence) "расскажи про")).toString();
                } else {
                    String str3 = str;
                    if (StringsKt.startsWith$default(text, str3, false, 2, (Object) null)) {
                        query = StringsKt.trim((CharSequence) StringsKt.removePrefix(text, (CharSequence) str3)).toString();
                    } else {
                        List<String> $this$dropWhile$iv = words;
                        boolean yielding$iv = false;
                        ArrayList list$iv = new ArrayList();
                        for (Object item$iv : $this$dropWhile$iv) {
                            if (yielding$iv) {
                                list$iv.add(item$iv);
                            } else if (KNOWLEDGE_VERBS.contains((String) item$iv)) {
                                list$iv.add(item$iv);
                                yielding$iv = true;
                            }
                        }
                        ArrayList $this$dropWhile$iv2 = list$iv;
                        query = StringsKt.trim((CharSequence) StringsKt.removePrefix(StringsKt.removePrefix(StringsKt.removePrefix(StringsKt.removePrefix(StringsKt.removePrefix(StringsKt.trim((CharSequence) CollectionsKt.joinToString$default(CollectionsKt.drop($this$dropWhile$iv2, 1), charSequence, null, null, 0, null, null, 62, null)).toString(), (CharSequence) "про "), (CharSequence) "о "), (CharSequence) "коротко "), (CharSequence) "кратко "), (CharSequence) "про ")).toString();
                    }
                }
                String cleanedQuery = StringsKt.trim((CharSequence) StringsKt.removePrefix(StringsKt.removePrefix(StringsKt.removePrefix(StringsKt.removePrefix(query, (CharSequence) "коротко про "), (CharSequence) "кратко про "), (CharSequence) "коротко о "), (CharSequence) "кратко о ")).toString();
                return StringsKt.isBlank(cleanedQuery) ? new BrainResult.Clarify("О чём рассказать?", 0.5f) : skill(IntentType.KNOWLEDGE, MapsKt.mapOf(TuplesKt.to(SearchIntents.EXTRA_QUERY, cleanedQuery)), text, 0.88f);
            }
            return null;
        }
        charSequence = StringUtils.SPACE;
        trigger = true;
        if (!trigger) {
            return null;
        }
        $this$any$iv = words;
        if ($this$any$iv instanceof Collection) {
            it = $this$any$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    str = str2;
                    z2 = false;
                    break;
                }
                Object element$iv3 = it.next();
                it2 = (String) element$iv3;
                trigger2 = trigger;
                it3 = it;
                str = str2;
                $this$any$iv2 = $this$any$iv;
                if (StringsKt.startsWith$default(it2, "анекдот", false, 2, (Object) null)) {
                    z3 = true;
                } else {
                    z3 = true;
                }
                if (z3) {
                    z2 = true;
                    break;
                }
                trigger = trigger2;
                it = it3;
                $this$any$iv = $this$any$iv2;
                str2 = str;
            }
        } else {
            it = $this$any$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    str = str2;
                    z2 = false;
                    break;
                }
                Object element$iv4 = it.next();
                it2 = (String) element$iv4;
                trigger2 = trigger;
                it3 = it;
                str = str2;
                $this$any$iv2 = $this$any$iv;
                if (StringsKt.startsWith$default(it2, "анекдот", false, 2, (Object) null)) {
                    z3 = true;
                } else {
                    z3 = true;
                }
                if (z3) {
                    z2 = true;
                    break;
                }
                trigger = trigger2;
                it = it3;
                $this$any$iv = $this$any$iv2;
                str2 = str;
            }
        }
        if (z2) {
            return null;
        }
        if (StringsKt.contains$default((CharSequence) text, (CharSequence) "думаешь", false, 2, (Object) null)) {
        }
        return null;
    }

    private final String extractCityAfter(List<String> words, Set<String> markers) {
        int index$iv = 0;
        Iterator<String> it = words.iterator();
        while (true) {
            if (!it.hasNext()) {
                index$iv = -1;
                break;
            }
            if (markers.contains((String) it.next())) {
                break;
            }
            index$iv++;
        }
        int idx = index$iv;
        if (idx < 0 || idx >= CollectionsKt.getLastIndex(words)) {
            return null;
        }
        List tail = CollectionsKt.drop(words, idx + 1);
        Iterable $this$takeWhile$iv = CollectionsKt.withIndex(tail);
        ArrayList list$iv = new ArrayList();
        Iterator it2 = $this$takeWhile$iv.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            Object item$iv = it2.next();
            IndexedValue indexedValue = (IndexedValue) item$iv;
            int i = indexedValue.getIndex();
            String word = (String) indexedValue.component2();
            if (!((WEATHER_WORDS.contains(word) || CITY_END_WORDS.contains(word) || (Intrinsics.areEqual(word, "на") && !Intrinsics.areEqual(CollectionsKt.getOrNull(tail, i + 1), "дону"))) ? false : true)) {
                break;
            }
            list$iv.add(item$iv);
        }
        ArrayList $this$takeWhile$iv2 = list$iv;
        String city = StringsKt.trim((CharSequence) CollectionsKt.joinToString$default($this$takeWhile$iv2, StringUtils.SPACE, null, null, 0, null, new Function1() { // from class: com.jarvis.brain.RuleBasedBrain$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return RuleBasedBrain.extractCityAfter$lambda$2((IndexedValue) obj);
            }
        }, 30, null)).toString();
        String it3 = normalizeCity(city);
        if (it3 == null) {
            return null;
        }
        if (it3.length() >= 2) {
            return it3;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence extractCityAfter$lambda$2(IndexedValue it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return (CharSequence) it.getValue();
    }

    private final String normalizeCity(String raw) {
        String stemmed;
        if (StringsKt.isBlank(raw)) {
            return null;
        }
        String lowerCase = raw.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String c = StringsKt.trim((CharSequence) lowerCase).toString();
        Map known = MapsKt.mapOf(TuplesKt.to("москве", "Москва"), TuplesKt.to("москвы", "Москва"), TuplesKt.to("москву", "Москва"), TuplesKt.to("москва", "Москва"), TuplesKt.to("питере", "Санкт-Петербург"), TuplesKt.to("питера", "Санкт-Петербург"), TuplesKt.to("петербурге", "Санкт-Петербург"), TuplesKt.to("петербург", "Санкт-Петербург"), TuplesKt.to("спб", "Санкт-Петербург"), TuplesKt.to("казани", "Казань"), TuplesKt.to("казань", "Казань"), TuplesKt.to("новосибирске", "Новосибирск"), TuplesKt.to("новосибирск", "Новосибирск"), TuplesKt.to("екб", "Екатеринбург"), TuplesKt.to("екатеринбурге", "Екатеринбург"), TuplesKt.to("екатеринбург", "Екатеринбург"), TuplesKt.to("сочи", "Сочи"), TuplesKt.to("минске", "Минск"), TuplesKt.to("минск", "Минск"), TuplesKt.to("киеве", "Киев"), TuplesKt.to("киев", "Киев"), TuplesKt.to("києві", "Київ"), TuplesKt.to("донецке", "Донецк"), TuplesKt.to("донецка", "Донецк"), TuplesKt.to("донецк", "Донецк"), TuplesKt.to("питере", "Санкт-Петербург"), TuplesKt.to("питера", "Санкт-Петербург"), TuplesKt.to("питер", "Санкт-Петербург"));
        String it = (String) known.get(c);
        if (it != null) {
            return it;
        }
        if (StringsKt.endsWith$default(c, "е", false, 2, (Object) null) && c.length() > 4) {
            stemmed = StringsKt.dropLast(c, 1);
        } else if (!StringsKt.endsWith$default(c, "у", false, 2, (Object) null) || c.length() <= 4) {
            stemmed = (!StringsKt.endsWith$default(c, "ы", false, 2, (Object) null) || c.length() <= 4) ? c : StringsKt.dropLast(c, 1) + "а";
        } else {
            stemmed = StringsKt.dropLast(c, 1);
        }
        if (!(stemmed.length() > 0)) {
            return stemmed;
        }
        StringBuilder sb = new StringBuilder();
        String strValueOf = String.valueOf(stemmed.charAt(0));
        Intrinsics.checkNotNull(strValueOf, "null cannot be cast to non-null type java.lang.String");
        String upperCase = strValueOf.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        StringBuilder sbAppend = sb.append((Object) upperCase);
        String strSubstring = stemmed.substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return sbAppend.append(strSubstring).toString();
    }

    private final BrainResult screenshot(List<String> words, UserRequest request) {
        List<String> $this$none$iv = words;
        boolean z = true;
        if (!($this$none$iv instanceof Collection) || !$this$none$iv.isEmpty()) {
            for (Object element$iv : $this$none$iv) {
                String it = (String) element$iv;
                if (SCREENSHOT_WORDS.contains(it)) {
                    z = false;
                    break;
                }
            }
        }
        if (!z || StringsKt.contains$default((CharSequence) CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null), (CharSequence) "скрин", false, 2, (Object) null)) {
            return skill(IntentType.SCREENSHOT, MapsKt.emptyMap(), CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null), 0.92f);
        }
        return null;
    }

    private final BrainResult clipboard(List<String> words, UserRequest request) {
        boolean z;
        Iterable $this$dropWhile$iv;
        boolean z2;
        String text = CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        if (!StringsKt.contains$default((CharSequence) text, (CharSequence) "буфер", false, 2, (Object) null)) {
            List<String> $this$none$iv = words;
            if (!($this$none$iv instanceof Collection) || !$this$none$iv.isEmpty()) {
                Iterator it = $this$none$iv.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z2 = true;
                        break;
                    }
                    Object element$iv = it.next();
                    String it2 = (String) element$iv;
                    if (SetsKt.setOf((Object[]) new String[]{"скопируй", "скопировать"}).contains(it2)) {
                        z2 = false;
                        break;
                    }
                }
            } else {
                z2 = true;
            }
            if (z2) {
                return null;
            }
        }
        List<String> $this$any$iv = words;
        if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
            Iterator it3 = $this$any$iv.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    z = false;
                    break;
                }
                Object element$iv2 = it3.next();
                String it4 = (String) element$iv2;
                if (SetsKt.setOf((Object[]) new String[]{"скопируй", "скопировать"}).contains(it4)) {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
        }
        if (!z) {
            return skill(IntentType.CLIPBOARD, MapsKt.mapOf(TuplesKt.to("action", "get")), text, 0.85f);
        }
        List<String> $this$dropWhile$iv2 = words;
        boolean yielding$iv = false;
        ArrayList list$iv = new ArrayList();
        for (Object item$iv : $this$dropWhile$iv2) {
            if (yielding$iv) {
                list$iv.add(item$iv);
                $this$dropWhile$iv = $this$dropWhile$iv2;
            } else {
                String it5 = (String) item$iv;
                $this$dropWhile$iv = $this$dropWhile$iv2;
                if (SetsKt.setOf((Object[]) new String[]{"скопируй", "скопировать"}).contains(it5)) {
                    list$iv.add(item$iv);
                    yielding$iv = true;
                    $this$dropWhile$iv2 = $this$dropWhile$iv;
                }
            }
            $this$dropWhile$iv2 = $this$dropWhile$iv;
        }
        String body = CollectionsKt.joinToString$default(CollectionsKt.drop(list$iv, 1), StringUtils.SPACE, null, null, 0, null, null, 62, null);
        return skill(IntentType.CLIPBOARD, MapsKt.mapOf(TuplesKt.to("action", "set"), TuplesKt.to(VoiceService.EXTRA_TEXT, body)), text, 0.85f);
    }

    private final BrainResult notifications(List<String> words, UserRequest request) {
        boolean z;
        boolean z2;
        String text = CollectionsKt.joinToString$default(words, StringUtils.SPACE, null, null, 0, null, null, 62, null);
        boolean z3 = false;
        boolean replyHit = StringsKt.contains$default((CharSequence) text, (CharSequence) "ответь", false, 2, (Object) null) && StringsKt.contains$default((CharSequence) text, (CharSequence) "уведомлен", false, 2, (Object) null);
        if (replyHit) {
            String replyText = extractNotificationReplyText(text);
            if (replyText == null) {
                return new BrainResult.Clarify("Что ответить в уведомление?", 0.55f);
            }
            String query = extractNotificationQuery(text);
            IntentType intentType = IntentType.NOTIFICATIONS;
            Map $this$notifications_u24lambda_u240 = MapsKt.createMapBuilder();
            $this$notifications_u24lambda_u240.put("action", "reply");
            $this$notifications_u24lambda_u240.put(VoiceService.EXTRA_TEXT, replyText);
            if (query != null) {
                $this$notifications_u24lambda_u240.put(SearchIntents.EXTRA_QUERY, query);
            }
            Unit unit = Unit.INSTANCE;
            return skill(intentType, MapsKt.build($this$notifications_u24lambda_u240), text, 0.88f);
        }
        if (!StringsKt.contains$default((CharSequence) text, (CharSequence) "уведомлен", false, 2, (Object) null)) {
            return null;
        }
        List<String> $this$any$iv = words;
        if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                Object element$iv = it.next();
                String it2 = (String) element$iv;
                if (SetsKt.setOf((Object[]) new String[]{"открой", "покажи"}).contains(it2)) {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
        }
        if (z) {
            List<String> $this$none$iv = words;
            if (!($this$none$iv instanceof Collection) || !$this$none$iv.isEmpty()) {
                Iterator it3 = $this$none$iv.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        z2 = true;
                        break;
                    }
                    Object element$iv2 = it3.next();
                    String it4 = (String) element$iv2;
                    if (SetsKt.setOf((Object[]) new String[]{"какие", "что", "прочитай"}).contains(it4)) {
                        z2 = false;
                        break;
                    }
                }
            } else {
                z2 = true;
            }
            if (z2) {
                return null;
            }
        }
        List<String> $this$any$iv2 = words;
        if (!($this$any$iv2 instanceof Collection) || !$this$any$iv2.isEmpty()) {
            for (Object element$iv3 : $this$any$iv2) {
                String it5 = (String) element$iv3;
                if (NOTIFICATION_CLEAR_VERBS.contains(it5)) {
                    z3 = true;
                    break;
                }
            }
        }
        return z3 ? skill(IntentType.NOTIFICATIONS, MapsKt.mapOf(TuplesKt.to("action", "clear")), text, 0.88f) : skill(IntentType.NOTIFICATIONS, MapsKt.mapOf(TuplesKt.to("action", "list")), text, 0.85f);
    }

    private final String extractNotificationReplyText(String text) {
        Object element$iv;
        String it;
        boolean z = false;
        Iterable markers = CollectionsKt.listOf((Object[]) new String[]{"ответь в уведомление", "ответь на уведомление", "ответь уведомление"});
        String lower = text.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lower, "toLowerCase(...)");
        Iterable $this$firstOrNull$iv = markers;
        Iterator it2 = $this$firstOrNull$iv.iterator();
        do {
            if (!it2.hasNext()) {
                element$iv = null;
                break;
            }
            element$iv = it2.next();
            it = (String) element$iv;
        } while (!StringsKt.contains$default((CharSequence) lower, (CharSequence) it, false, 2, (Object) null));
        String marker = (String) element$iv;
        if (marker == null) {
            return null;
        }
        String rest = StringsKt.trim((CharSequence) StringsKt.substringAfter(text, marker, "")).toString();
        if (StringsKt.startsWith$default(rest, ":", false, 2, (Object) null)) {
            rest = StringsKt.trim((CharSequence) StringsKt.drop(rest, 1)).toString();
        }
        String afterColon = StringsKt.trim((CharSequence) StringsKt.substringAfter(rest, ':', "")).toString();
        if (!StringsKt.isBlank(afterColon) && StringsKt.contains$default((CharSequence) rest, ':', false, 2, (Object) null)) {
            return afterColon;
        }
        String dropQuery = StringsKt.trim((CharSequence) new Regex("^(от\\s+\\S+\\s+|в\\s+\\S+\\s+)", RegexOption.IGNORE_CASE).replace(rest, "")).toString();
        if (!StringsKt.isBlank(dropQuery) && dropQuery.length() >= 1) {
            z = true;
        }
        if (z) {
            return dropQuery;
        }
        return null;
    }

    private final String extractNotificationQuery(String text) {
        MatchResult m = Regex.find$default(new Regex("(?:от|в)\\s+([A-Za-zА-Яа-яёЁ0-9_.\\-]+)", RegexOption.IGNORE_CASE), text, 0, 2, null);
        if (m == null) {
            return null;
        }
        String str = m.getGroupValues().get(1);
        String it = str;
        return StringsKt.isBlank(it) ? null : str;
    }

    private final BrainResult.SkillCall skill(IntentType type, Map<String, ? extends Object> slots, String text, float confidence) {
        Map<String, ? extends Object> map = slots;
        Map destination$iv$iv = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        Iterable $this$associateByTo$iv$iv$iv = map.entrySet();
        for (Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
            Map.Entry it$iv$iv = (Map.Entry) element$iv$iv$iv;
            Map.Entry it = (Map.Entry) element$iv$iv$iv;
            destination$iv$iv.put(it$iv$iv.getKey(), new Slot((String) it.getKey(), it.getValue(), 0.0f, 4, null));
            map = map;
        }
        return new BrainResult.SkillCall(new Intent(type, text, destination$iv$iv, confidence, confidence >= 0.9f ? ConfidenceLevel.HIGH : ConfidenceLevel.MEDIUM), confidence);
    }

    private final Integer extractMinutes(List<String> words) {
        boolean z;
        int hours;
        boolean z2;
        int minutes;
        boolean z3;
        boolean z4;
        List<String> $this$any$iv = words;
        if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    String it2 = (String) element$iv;
                    if (HOUR_WORDS.contains(it2)) {
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
            Integer numBefore = RuNumbers.INSTANCE.before(words, HOUR_WORDS);
            hours = numBefore != null ? numBefore.intValue() : 1;
        } else {
            hours = 0;
        }
        if (words.contains("полторы")) {
            List<String> $this$any$iv2 = words;
            if (!($this$any$iv2 instanceof Collection) || !$this$any$iv2.isEmpty()) {
                Iterator it3 = $this$any$iv2.iterator();
                while (true) {
                    if (it3.hasNext()) {
                        Object element$iv2 = it3.next();
                        String it4 = (String) element$iv2;
                        if (MINUTE_WORDS.contains(it4)) {
                            z4 = true;
                            break;
                        }
                    } else {
                        z4 = false;
                        break;
                    }
                }
            } else {
                z4 = false;
            }
            if (z4) {
                return null;
            }
        }
        if (words.contains("полтора")) {
            List<String> $this$any$iv3 = words;
            if (!($this$any$iv3 instanceof Collection) || !$this$any$iv3.isEmpty()) {
                Iterator it5 = $this$any$iv3.iterator();
                while (true) {
                    if (it5.hasNext()) {
                        Object element$iv3 = it5.next();
                        String it6 = (String) element$iv3;
                        if (HOUR_WORDS.contains(it6)) {
                            z3 = true;
                            break;
                        }
                    } else {
                        z3 = false;
                        break;
                    }
                }
            } else {
                z3 = false;
            }
            if (z3) {
                return 90;
            }
        }
        int half = words.contains("полчаса") ? 30 : 0;
        List<String> $this$any$iv4 = words;
        if (!($this$any$iv4 instanceof Collection) || !$this$any$iv4.isEmpty()) {
            Iterator it7 = $this$any$iv4.iterator();
            while (true) {
                if (it7.hasNext()) {
                    Object element$iv4 = it7.next();
                    String it8 = (String) element$iv4;
                    if (MINUTE_WORDS.contains(it8)) {
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
        if (z2) {
            Integer numBefore2 = RuNumbers.INSTANCE.before(words, MINUTE_WORDS);
            minutes = numBefore2 != null ? numBefore2.intValue() : 1;
        } else {
            minutes = 0;
        }
        Integer numValueOf = Integer.valueOf((hours * 60) + half + minutes);
        int it9 = numValueOf.intValue();
        if (it9 > 0) {
            return numValueOf;
        }
        return null;
    }

    private final Integer extractSeconds(List<String> words) {
        boolean z;
        boolean z2 = false;
        if (words.contains("полторы")) {
            List<String> $this$any$iv = words;
            if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
                Iterator it = $this$any$iv.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Object element$iv = it.next();
                        String it2 = (String) element$iv;
                        if (MINUTE_WORDS.contains(it2)) {
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
                return 90;
            }
        }
        List<String> $this$any$iv2 = words;
        if (!($this$any$iv2 instanceof Collection) || !$this$any$iv2.isEmpty()) {
            for (Object element$iv2 : $this$any$iv2) {
                String it3 = (String) element$iv2;
                if (SECOND_WORDS.contains(it3)) {
                    z2 = true;
                    break;
                }
            }
        }
        if (!z2) {
            return null;
        }
        Integer numBefore = RuNumbers.INSTANCE.before(words, SECOND_WORDS);
        return Integer.valueOf(numBefore != null ? numBefore.intValue() : 1);
    }

    private final boolean looksLikeCommand(List<String> words) {
        List<String> $this$any$iv = words;
        if (($this$any$iv instanceof Collection) && $this$any$iv.isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            String it = (String) element$iv;
            if (COMMAND_VERBS.contains(it)) {
                return true;
            }
        }
        return false;
    }

    private final List<String> normalize(String text) {
        String lowerCase = text.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        Iterable $this$filter$iv = new Regex("\\s+").split(new Regex("[^\\p{L}\\p{N}% ]").replace(lowerCase, StringUtils.SPACE), 0);
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
