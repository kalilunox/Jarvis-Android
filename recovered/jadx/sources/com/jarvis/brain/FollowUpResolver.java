package com.jarvis.brain;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.actions.SearchIntents;
import com.jarvis.app.VoiceService;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.reminder.ReminderParser;
import com.jarvis.core.reminder.ReminderRequest;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.AbstractCircuitBreaker;

/* JADX INFO: compiled from: FollowUpResolver.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\rJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u0006J\u0010\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0006H\u0002J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0006H\u0002J\u0010\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0006H\u0002J\u0010\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0006H\u0002J\u0010\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0006H\u0002J\u001a\u0010\u0018\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\rH\u0002J\u001a\u0010\u0019\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\rH\u0002J\u001a\u0010\u001a\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\rH\u0002J\u0010\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u0006H\u0002J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001e\u001a\u00020\u0006H\u0002J\u001a\u0010\u001f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\rH\u0002J\u001a\u0010 \u001a\u0004\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\rH\u0002J\u001a\u0010!\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\rH\u0002J\u0010\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u0006H\u0002J\u0018\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u0006H\u0002J\u0012\u0010'\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0013\u001a\u00020\u0006H\u0002J\u001a\u0010(\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\rH\u0002J\u001a\u0010)\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\rH\u0002J\u001a\u0010*\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\rH\u0002J\u001a\u0010+\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\rH\u0002J\b\u0010,\u001a\u00020\u0006H\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020.X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000600X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lcom/jarvis/brain/FollowUpResolver;", "", "<init>", "()V", "ACTION_TOOLS", "", "", "TIMER_NOUNS", "", "HOUR_AND_HALF_MINUTES", "", "HALF_HOUR_MINUTES", "resolve", "Lcom/jarvis/core/common/model/ToolCall;", VoiceService.EXTRA_TEXT, "last", "looksLikeFollowUp", "", "isCityOnlyFollowUp", "t", "isWeatherCityPatch", "isAppSwitchPhrase", "isPlainReplay", "isKnowledgeFollowUpPhrase", "weatherFollowUp", "cityOnlyPatch", "launcherFollowUp", "appLooksLikeOtherCommand", "app", "extractAppName", "raw", "reminderFollowUp", "timerFollowUp", "knowledgeFollowUp", "extractKnowledgeTopic", "prevQuery", "rewriteKnowledgeFollowUp", "followUp", "topic", "extractCity", "volumeFollowUp", "brightnessFollowUp", "flashlightFollowUp", "musicFollowUp", "newId", "BUT_PREFIX", "Lkotlin/text/Regex;", "CITY_ALIASES", "", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FollowUpResolver {
    private static final int HALF_HOUR_MINUTES = 30;
    private static final int HOUR_AND_HALF_MINUTES = 90;
    public static final FollowUpResolver INSTANCE = new FollowUpResolver();
    private static final Set<String> ACTION_TOOLS = SetsKt.setOf((Object[]) new String[]{"flashlight", "volume", "brightness", "music"});
    private static final List<String> TIMER_NOUNS = CollectionsKt.listOf((Object[]) new String[]{"таймер", "будильник", "секундомер"});
    private static final Regex BUT_PREFIX = new Regex("^(?:то\\s*же\\s*самое\\s*[,:]?\\s*)?(?:но|а\\s+теперь|а\\s+сейчас|теперь|только)\\s+(.+)$");
    private static final Map<String, String> CITY_ALIASES = MapsKt.mapOf(TuplesKt.to("москве", "Москва"), TuplesKt.to("москвы", "Москва"), TuplesKt.to("москву", "Москва"), TuplesKt.to("москва", "Москва"), TuplesKt.to("казани", "Казань"), TuplesKt.to("казань", "Казань"), TuplesKt.to("питере", "Санкт-Петербург"), TuplesKt.to("питера", "Санкт-Петербург"), TuplesKt.to("питер", "Санкт-Петербург"), TuplesKt.to("петербурге", "Санкт-Петербург"), TuplesKt.to("петербург", "Санкт-Петербург"), TuplesKt.to("спб", "Санкт-Петербург"), TuplesKt.to("санкт-петербург", "Санкт-Петербург"), TuplesKt.to("сочи", "Сочи"), TuplesKt.to("донецке", "Донецк"), TuplesKt.to("донецка", "Донецк"), TuplesKt.to("донецк", "Донецк"), TuplesKt.to("новосибирске", "Новосибирск"), TuplesKt.to("новосибирск", "Новосибирск"), TuplesKt.to("екатеринбурге", "Екатеринбург"), TuplesKt.to("екатеринбург", "Екатеринбург"), TuplesKt.to("екб", "Екатеринбург"), TuplesKt.to("минске", "Минск"), TuplesKt.to("минск", "Минск"), TuplesKt.to("киеве", "Киев"), TuplesKt.to("киев", "Киев"));

    private FollowUpResolver() {
    }

    public final ToolCall resolve(String text, ToolCall last) {
        String app;
        List<String> groupValues;
        String str;
        Intrinsics.checkNotNullParameter(text, "text");
        if (CustomRoutineParser.INSTANCE.parse(text) != null || last == null) {
            return null;
        }
        String lowerCase = text.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String t = StringsKt.trimEnd(StringsKt.trim((CharSequence) lowerCase).toString(), '?', '!', '.', Typography.ellipsis);
        if (StringsKt.isBlank(t)) {
            return null;
        }
        if (isPlainReplay(t)) {
            return ToolCall.copy$default(last, null, newId(), null, null, 13, null);
        }
        if (ACTION_TOOLS.contains(last.getToolId())) {
            ReminderParser reminderParser = ReminderParser.INSTANCE;
            LocalDateTime localDateTimeNow = LocalDateTime.now();
            Intrinsics.checkNotNullExpressionValue(localDateTimeNow, "now(...)");
            ReminderRequest delayed = reminderParser.parse(text, localDateTimeNow);
            if (delayed.getAt() != null && !StringsKt.isBlank(delayed.getText())) {
                return null;
            }
        }
        ToolCall it = volumeFollowUp(t, last);
        if (it != null) {
            return it;
        }
        ToolCall it2 = brightnessFollowUp(t, last);
        if (it2 != null) {
            return it2;
        }
        ToolCall it3 = flashlightFollowUp(t, last);
        if (it3 != null) {
            return it3;
        }
        ToolCall it4 = musicFollowUp(t, last);
        if (it4 != null) {
            return it4;
        }
        ToolCall it5 = timerFollowUp(t, last);
        if (it5 != null) {
            return it5;
        }
        ToolCall it6 = launcherFollowUp(t, last);
        if (it6 != null) {
            return it6;
        }
        ToolCall it7 = reminderFollowUp(text, last);
        if (it7 != null) {
            return it7;
        }
        ToolCall it8 = knowledgeFollowUp(t, last);
        if (it8 != null) {
            return it8;
        }
        if (!looksLikeFollowUp(t) && !isCityOnlyFollowUp(t) && !isAppSwitchPhrase(t)) {
            return null;
        }
        ToolCall it9 = weatherFollowUp(t, last);
        if (it9 != null) {
            return it9;
        }
        MatchResult matchResultFind$default = Regex.find$default(BUT_PREFIX, t, 0, 2, null);
        String string = (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null || (str = (String) CollectionsKt.getOrNull(groupValues, 1)) == null) ? null : StringsKt.trim((CharSequence) str).toString();
        if (string == null) {
            string = "";
        }
        String but = string;
        if (!StringsKt.isBlank(but)) {
            ToolCall it10 = launcherFollowUp(but, last);
            if (it10 != null) {
                return it10;
            }
            ToolCall it11 = weatherFollowUp(but, last);
            if (it11 != null) {
                return it11;
            }
            ToolCall it12 = volumeFollowUp(but, last);
            if (it12 != null) {
                return it12;
            }
            ToolCall it13 = brightnessFollowUp(but, last);
            if (it13 != null) {
                return it13;
            }
            ToolCall it14 = timerFollowUp(but, last);
            if (it14 != null) {
                return it14;
            }
            ToolCall it15 = cityOnlyPatch(but, last);
            if (it15 != null) {
                return it15;
            }
            ToolCall it16 = knowledgeFollowUp(but, last);
            if (it16 != null) {
                return it16;
            }
            if (Intrinsics.areEqual(last.getToolId(), "launcher") && (app = extractAppName(but)) != null) {
                return ToolCall.copy$default(last, null, INSTANCE.newId(), null, MapsKt.mapOf(TuplesKt.to("action", AbstractCircuitBreaker.PROPERTY_NAME), TuplesKt.to("app", app)), 5, null);
            }
        }
        ToolCall it17 = cityOnlyPatch(t, last);
        if (it17 != null) {
            return it17;
        }
        ToolCall it18 = launcherFollowUp(t, last);
        if (it18 != null) {
            return it18;
        }
        return null;
    }

    public final boolean looksLikeFollowUp(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        String lowerCase = text.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String t = StringsKt.trimEnd(StringsKt.trim((CharSequence) lowerCase).toString(), '?', '!', '.');
        return isPlainReplay(t) || BUT_PREFIX.containsMatchIn(t) || StringsKt.startsWith$default(t, "а ", false, 2, (Object) null) || StringsKt.startsWith$default(t, "а,", false, 2, (Object) null) || StringsKt.startsWith$default(t, "а теперь", false, 2, (Object) null) || StringsKt.startsWith$default(t, "теперь ", false, 2, (Object) null) || SetsKt.setOf((Object[]) new String[]{"завтра", "послезавтра", "сегодня"}).contains(t) || isCityOnlyFollowUp(t) || isKnowledgeFollowUpPhrase(t) || isAppSwitchPhrase(t);
    }

    private final boolean isCityOnlyFollowUp(String t) {
        return new Regex("^в(?:о)?\\s+[а-яёa-z\\-]{2,}", RegexOption.IGNORE_CASE).containsMatchIn(t);
    }

    private final boolean isWeatherCityPatch(String t) {
        if (isCityOnlyFollowUp(t)) {
            return true;
        }
        return new Regex("^а\\s+в(?:о)?\\s+[а-яёa-z\\-]{2,}", RegexOption.IGNORE_CASE).containsMatchIn(t);
    }

    private final boolean isAppSwitchPhrase(String t) {
        return StringsKt.startsWith$default(t, "а теперь", false, 2, (Object) null) || StringsKt.startsWith$default(t, "теперь ", false, 2, (Object) null) || StringsKt.startsWith$default(t, "а сейчас", false, 2, (Object) null) || new Regex("^а\\s+(открой|запусти)\\s+\\S+").containsMatchIn(t);
    }

    private final boolean isPlainReplay(String t) {
        return SetsKt.setOf((Object[]) new String[]{"ещё раз", "еще раз", "повтори", "повторы", "снова", "то же самое", "тоже самое", "то же", "повтори ещё раз", "повтори еще раз"}).contains(t);
    }

    private final boolean isKnowledgeFollowUpPhrase(String t) {
        if (StringsKt.contains$default((CharSequence) t, (CharSequence) "погод", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "фонарик", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "громк", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "тише", false, 2, (Object) null) || StringsKt.startsWith$default(t, "открой", false, 2, (Object) null) || StringsKt.startsWith$default(t, "включи", false, 2, (Object) null) || StringsKt.startsWith$default(t, "выключи", false, 2, (Object) null) || StringsKt.startsWith$default(t, "запусти", false, 2, (Object) null)) {
            return false;
        }
        if (SetsKt.setOf((Object[]) new String[]{"подробнее", "продолжи", "дальше", "ещё", "еще"}).contains(t) || StringsKt.startsWith$default(t, "почему", false, 2, (Object) null) || StringsKt.startsWith$default(t, "зачем", false, 2, (Object) null) || StringsKt.startsWith$default(t, "как ", false, 2, (Object) null) || StringsKt.startsWith$default(t, "где ", false, 2, (Object) null) || StringsKt.startsWith$default(t, "когда ", false, 2, (Object) null) || StringsKt.startsWith$default(t, "сколько ", false, 2, (Object) null) || StringsKt.startsWith$default(t, "чем ", false, 2, (Object) null) || StringsKt.startsWith$default(t, "кем ", false, 2, (Object) null) || StringsKt.startsWith$default(t, "откуда ", false, 2, (Object) null) || StringsKt.startsWith$default(t, "куда ", false, 2, (Object) null)) {
            return true;
        }
        return ((StringsKt.startsWith$default(t, "какой ", false, 2, (Object) null) || StringsKt.startsWith$default(t, "какая ", false, 2, (Object) null) || StringsKt.startsWith$default(t, "какое ", false, 2, (Object) null) || StringsKt.startsWith$default(t, "какие ", false, 2, (Object) null)) && (StringsKt.contains$default((CharSequence) t, (CharSequence) " он", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) " она", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) " они", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) " это", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) " его", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) " её", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) " их", false, 2, (Object) null) || t.length() < 28)) || StringsKt.contains$default((CharSequence) t, (CharSequence) " их ", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) " они ", false, 2, (Object) null) || StringsKt.endsWith$default(t, " их", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) " он ", false, 2, (Object) null) || StringsKt.startsWith$default(t, "он ", false, 2, (Object) null) || StringsKt.endsWith$default(t, " он", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) " она ", false, 2, (Object) null) || StringsKt.startsWith$default(t, "она ", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) " его ", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) " её ", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) " ее ", false, 2, (Object) null) || StringsKt.startsWith$default(t, "а почему", false, 2, (Object) null) || StringsKt.startsWith$default(t, "а зачем", false, 2, (Object) null) || StringsKt.startsWith$default(t, "а где", false, 2, (Object) null) || Intrinsics.areEqual(t, "это") || StringsKt.startsWith$default(t, "а это", false, 2, (Object) null) || StringsKt.startsWith$default(t, "про это", false, 2, (Object) null);
    }

    private final ToolCall weatherFollowUp(String t, ToolCall last) {
        Integer day;
        if (!Intrinsics.areEqual(last.getToolId(), "weather")) {
            return null;
        }
        if (StringsKt.contains$default((CharSequence) t, (CharSequence) "погод", false, 2, (Object) null) || StringsKt.startsWith$default(t, "какая ", false, 2, (Object) null) || StringsKt.startsWith$default(t, "какой ", false, 2, (Object) null)) {
            return null;
        }
        if (StringsKt.contains$default((CharSequence) t, (CharSequence) "послезавтра", false, 2, (Object) null)) {
            day = 2;
        } else if (StringsKt.contains$default((CharSequence) t, (CharSequence) "завтра", false, 2, (Object) null)) {
            day = 1;
        } else {
            day = StringsKt.contains$default((CharSequence) t, (CharSequence) "сегодня", false, 2, (Object) null) ? 0 : null;
        }
        String city = extractCity(t);
        if (day == null && city == null) {
            return null;
        }
        String strNewId = newId();
        Map $this$weatherFollowUp_u24lambda_u240 = MapsKt.toMutableMap(last.getArguments());
        if (day != null) {
            $this$weatherFollowUp_u24lambda_u240.put("day", day);
        }
        String str = city;
        if (!(str == null || StringsKt.isBlank(str))) {
            $this$weatherFollowUp_u24lambda_u240.put("city", city);
        }
        Unit unit = Unit.INSTANCE;
        return ToolCall.copy$default(last, null, strNewId, null, $this$weatherFollowUp_u24lambda_u240, 5, null);
    }

    private final ToolCall cityOnlyPatch(String t, ToolCall last) {
        String city;
        if (!Intrinsics.areEqual(last.getToolId(), "weather") || StringsKt.contains$default((CharSequence) t, (CharSequence) "погод", false, 2, (Object) null) || StringsKt.startsWith$default(t, "какая ", false, 2, (Object) null) || StringsKt.startsWith$default(t, "какой ", false, 2, (Object) null) || (city = extractCity(t)) == null) {
            return null;
        }
        return ToolCall.copy$default(last, null, newId(), null, MapsKt.plus(last.getArguments(), MapsKt.mapOf(TuplesKt.to("city", city))), 5, null);
    }

    private final ToolCall launcherFollowUp(String t, ToolCall last) {
        String prevAction;
        String app;
        if (!Intrinsics.areEqual(last.getToolId(), "launcher")) {
            return null;
        }
        Object obj = last.getArguments().get("action");
        if (obj == null || (prevAction = obj.toString()) == null) {
            prevAction = AbstractCircuitBreaker.PROPERTY_NAME;
        }
        if (Intrinsics.areEqual(prevAction, "force_stop")) {
            return null;
        }
        if (SetsKt.setOf((Object[]) new String[]{"закрой", "сверни", "убери"}).contains(t) || StringsKt.startsWith$default(t, "закрой ", false, 2, (Object) null) || StringsKt.startsWith$default(t, "сверни ", false, 2, (Object) null)) {
            Object obj2 = last.getArguments().get("app");
            String app2 = obj2 != null ? obj2.toString() : null;
            if (app2 == null) {
                app2 = "";
            }
            String strNewId = newId();
            Map $this$launcherFollowUp_u24lambda_u240 = MapsKt.createMapBuilder();
            $this$launcherFollowUp_u24lambda_u240.put("action", "minimize");
            if (!StringsKt.isBlank(app2)) {
                $this$launcherFollowUp_u24lambda_u240.put("app", app2);
            }
            Unit unit = Unit.INSTANCE;
            return ToolCall.copy$default(last, null, strNewId, null, MapsKt.build($this$launcherFollowUp_u24lambda_u240), 5, null);
        }
        boolean looksLikeSwitch = isAppSwitchPhrase(t) || StringsKt.startsWith$default(t, "открой ", false, 2, (Object) null) || StringsKt.startsWith$default(t, "запусти ", false, 2, (Object) null) || StringsKt.startsWith$default(t, "а открой", false, 2, (Object) null) || StringsKt.startsWith$default(t, "а запусти", false, 2, (Object) null) || new Regex("^а\\s+[a-zа-яё0-9.\\-]{2,24}$").matches(t);
        if (!looksLikeSwitch || (app = extractAppName(t)) == null || appLooksLikeOtherCommand(app)) {
            return null;
        }
        return ToolCall.copy$default(last, null, newId(), null, MapsKt.mapOf(TuplesKt.to("action", AbstractCircuitBreaker.PROPERTY_NAME), TuplesKt.to("app", app)), 5, null);
    }

    private final boolean appLooksLikeOtherCommand(String app) {
        String lowerCase = app.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        Iterable iterableSplit = new Regex("\\s+").split(lowerCase, 0);
        Set verbs = SetsKt.setOf((Object[]) new String[]{"сделай", "включи", "выключи", "поставь", "скажи", "какая", "какой", "громче", "тише", "ярче", "темнее", "погода", "таймер", "фонарик", "кто", "что", "почему", "зачем", "расскажи", "найди", "напиши"});
        Iterable $this$any$iv = iterableSplit;
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            String it = (String) element$iv;
            if (verbs.contains(it)) {
                return true;
            }
        }
        return false;
    }

    private final String extractAppName(String raw) {
        String lowerCase = raw.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String s = StringsKt.trimEnd(StringsKt.trim((CharSequence) StringsKt.removePrefix(StringsKt.removePrefix(StringsKt.removePrefix(StringsKt.removePrefix(StringsKt.trim((CharSequence) StringsKt.removePrefix(StringsKt.removePrefix(StringsKt.removePrefix(StringsKt.removePrefix(StringsKt.removePrefix(StringsKt.removePrefix(StringsKt.trim((CharSequence) lowerCase).toString(), (CharSequence) "а теперь "), (CharSequence) "а сейчас "), (CharSequence) "теперь "), (CharSequence) "а давай "), (CharSequence) "давай "), (CharSequence) "а ")).toString(), (CharSequence) "открой "), (CharSequence) "запусти "), (CharSequence) "открыть "), (CharSequence) "приложение ")).toString(), '?', '!', '.', ',', Typography.ellipsis);
        if (StringsKt.isBlank(s) || s.length() > 40 || SetsKt.setOf((Object[]) new String[]{"ещё", "еще", "раз", "то", "же", "пожалуйста", "его", "её", "ее", "это", "туда", "сюда", "громче", "тише", "ярче"}).contains(s) || StringsKt.startsWith$default(s, "в ", false, 2, (Object) null) || StringsKt.startsWith$default(s, "во ", false, 2, (Object) null) || StringsKt.startsWith$default(s, "почему", false, 2, (Object) null) || StringsKt.startsWith$default(s, "что ", false, 2, (Object) null) || StringsKt.startsWith$default(s, "кто ", false, 2, (Object) null)) {
            return null;
        }
        return s;
    }

    private final ToolCall reminderFollowUp(String text, ToolCall last) {
        Object obj;
        String phrase;
        if (!Intrinsics.areEqual(last.getToolId(), NotificationCompat.CATEGORY_REMINDER) || (obj = last.getArguments().get("phrase")) == null || (phrase = obj.toString()) == null) {
            return null;
        }
        LocalDateTime now = LocalDateTime.now();
        ReminderParser reminderParser = ReminderParser.INSTANCE;
        Intrinsics.checkNotNull(now);
        if (reminderParser.parse(phrase, now).getAt() != null) {
            return null;
        }
        ReminderRequest answer = ReminderParser.INSTANCE.parse(text, now);
        if (answer.getAt() == null || !StringsKt.isBlank(answer.getText())) {
            return null;
        }
        return ToolCall.copy$default(last, null, newId(), null, MapsKt.plus(last.getArguments(), TuplesKt.to("phrase", phrase + StringUtils.SPACE + text)), 5, null);
    }

    private final ToolCall timerFollowUp(String t, ToolCall last) {
        boolean z;
        Integer numValueOf;
        List<String> groupValues;
        String str;
        if (!Intrinsics.areEqual(last.getToolId(), "timer") || last.getArguments().containsKey("action")) {
            return null;
        }
        Iterable $this$any$iv = TIMER_NOUNS;
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
            return null;
        }
        MatchResult matchResultFind$default = Regex.find$default(new Regex("(\\d+)\\s*(?:мин|минут)"), t, 0, 2, null);
        if (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null || (str = (String) CollectionsKt.getOrNull(groupValues, 1)) == null || (numValueOf = StringsKt.toIntOrNull(str)) == null) {
            if (StringsKt.contains$default((CharSequence) t, (CharSequence) "полтора", false, 2, (Object) null)) {
                numValueOf = Integer.valueOf(HOUR_AND_HALF_MINUTES);
            } else if (StringsKt.contains$default((CharSequence) t, (CharSequence) "полчаса", false, 2, (Object) null)) {
                numValueOf = 30;
            } else {
                numValueOf = (!StringsKt.contains$default((CharSequence) t, (CharSequence) "час", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "минут", false, 2, (Object) null)) ? null : 60;
            }
            if (numValueOf == null) {
                return null;
            }
        }
        int minutes = numValueOf.intValue();
        return ToolCall.copy$default(last, null, newId(), null, MapsKt.plus(last.getArguments(), MapsKt.mapOf(TuplesKt.to("minutes", Integer.valueOf(minutes)))), 5, null);
    }

    private final ToolCall knowledgeFollowUp(String t, ToolCall last) {
        String string;
        String string2;
        String string3;
        String string4;
        if (Intrinsics.areEqual(last.getToolId(), "knowledge") && !isWeatherCityPatch(t) && !isAppSwitchPhrase(t)) {
            if (StringsKt.contains$default((CharSequence) t, (CharSequence) "погод", false, 2, (Object) null) || StringsKt.startsWith$default(t, "открой", false, 2, (Object) null) || StringsKt.startsWith$default(t, "включи", false, 2, (Object) null) || StringsKt.startsWith$default(t, "выключи", false, 2, (Object) null) || StringsKt.startsWith$default(t, "запусти", false, 2, (Object) null) || StringsKt.startsWith$default(t, "сделай ", false, 2, (Object) null)) {
                return null;
            }
            if (!looksLikeFollowUp(t) && !isKnowledgeFollowUpPhrase(t)) {
                return null;
            }
            if (StringsKt.startsWith$default(t, "кто такой", false, 2, (Object) null) || StringsKt.startsWith$default(t, "кто такая", false, 2, (Object) null) || StringsKt.startsWith$default(t, "кто такие", false, 2, (Object) null) || StringsKt.startsWith$default(t, "что такое", false, 2, (Object) null) || StringsKt.startsWith$default(t, "расскажи про", false, 2, (Object) null) || StringsKt.startsWith$default(t, "расскажи о", false, 2, (Object) null) || StringsKt.startsWith$default(t, "загугли", false, 2, (Object) null)) {
                return null;
            }
            Object obj = last.getArguments().get(SearchIntents.EXTRA_QUERY);
            String prev = (obj == null || (string4 = obj.toString()) == null) ? null : StringsKt.trim((CharSequence) string4).toString();
            if (prev == null) {
                prev = "";
            }
            Object obj2 = last.getArguments().get("topic");
            String storedTopic = (obj2 == null || (string3 = obj2.toString()) == null) ? null : StringsKt.trim((CharSequence) string3).toString();
            if (storedTopic == null) {
                storedTopic = "";
            }
            Object obj3 = last.getArguments().get("exclude_title");
            String string5 = (obj3 == null || (string2 = obj3.toString()) == null) ? null : StringsKt.trim((CharSequence) string2).toString();
            if (string5 == null) {
                string5 = "";
            }
            String str = string5;
            if (StringsKt.isBlank(str)) {
                Object obj4 = last.getArguments().get("title");
                String string6 = (obj4 == null || (string = obj4.toString()) == null) ? null : StringsKt.trim((CharSequence) string).toString();
                str = string6 != null ? string6 : "";
            }
            String exclude = str;
            if (StringsKt.isBlank(prev) && StringsKt.isBlank(storedTopic)) {
                return null;
            }
            String strExtractKnowledgeTopic = storedTopic;
            if (StringsKt.isBlank(strExtractKnowledgeTopic)) {
                strExtractKnowledgeTopic = INSTANCE.extractKnowledgeTopic(prev);
            }
            String topic = strExtractKnowledgeTopic;
            if (StringsKt.isBlank(topic)) {
                return null;
            }
            String newQuery = rewriteKnowledgeFollowUp(t, topic);
            String strNewId = newId();
            Map $this$knowledgeFollowUp_u24lambda_u242 = MapsKt.createMapBuilder();
            String str2 = newQuery;
            if (StringsKt.isBlank(str2)) {
                str2 = topic + ": " + t;
            }
            $this$knowledgeFollowUp_u24lambda_u242.put(SearchIntents.EXTRA_QUERY, str2);
            $this$knowledgeFollowUp_u24lambda_u242.put("topic", topic);
            if (!StringsKt.isBlank(exclude)) {
                $this$knowledgeFollowUp_u24lambda_u242.put("exclude_title", exclude);
            }
            Unit unit = Unit.INSTANCE;
            return ToolCall.copy$default(last, null, strNewId, null, MapsKt.build($this$knowledgeFollowUp_u24lambda_u242), 5, null);
        }
        return null;
    }

    private final String extractKnowledgeTopic(String prevQuery) {
        String lowerCase = prevQuery.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String s = StringsKt.trim((CharSequence) lowerCase).toString();
        for (String p : CollectionsKt.listOf((Object[]) new String[]{"кто такие ", "кто такой ", "кто такая ", "что такое ", "что это такое ", "расскажи про ", "расскажи о ", "загугли "})) {
            if (StringsKt.startsWith$default(s, p, false, 2, (Object) null)) {
                s = StringsKt.trim((CharSequence) StringsKt.removePrefix(s, (CharSequence) p)).toString();
                break;
            }
        }
        return StringsKt.take(StringsKt.trim((CharSequence) CollectionsKt.joinToString$default(CollectionsKt.take(new Regex("\\s+").split(s, 0), 3), StringUtils.SPACE, null, null, 0, null, null, 62, null)).toString(), 40);
    }

    private final String rewriteKnowledgeFollowUp(String followUp, String topic) {
        String genitive;
        String t = StringsKt.trim((CharSequence) StringsKt.removePrefix(StringsKt.removePrefix(StringsKt.trim((CharSequence) followUp).toString(), (CharSequence) "а "), (CharSequence) "А ")).toString();
        if (!StringsKt.endsWith$default(topic, "ы", false, 2, (Object) null) && !StringsKt.endsWith$default(topic, "и", false, 2, (Object) null)) {
            if (StringsKt.endsWith$default(topic, "а", false, 2, (Object) null) || StringsKt.endsWith$default(topic, "я", false, 2, (Object) null)) {
                genitive = StringsKt.dropLast(topic, 1) + "ов";
            } else {
                genitive = topic;
            }
        } else {
            genitive = StringsKt.dropLast(topic, 1) + "ов";
        }
        String t2 = new Regex("\\bэто\\b", RegexOption.IGNORE_CASE).replace(new Regex("\\bее\\b", RegexOption.IGNORE_CASE).replace(new Regex("\\bеё\\b", RegexOption.IGNORE_CASE).replace(new Regex("\\bего\\b", RegexOption.IGNORE_CASE).replace(new Regex("\\bона\\b", RegexOption.IGNORE_CASE).replace(new Regex("\\bон\\b", RegexOption.IGNORE_CASE).replace(new Regex("\\bим\\b", RegexOption.IGNORE_CASE).replace(new Regex("\\bони\\b", RegexOption.IGNORE_CASE).replace(new Regex("\\bих\\b", RegexOption.IGNORE_CASE).replace(t, genitive), topic), topic), topic), topic), genitive), genitive), genitive), topic);
        if (!StringsKt.contains((CharSequence) t2, (CharSequence) topic, true) && !StringsKt.contains((CharSequence) t2, (CharSequence) genitive, true)) {
            t2 = t2 + " (" + topic + ")";
        }
        return StringsKt.trim((CharSequence) t2).toString();
    }

    private final String extractCity(String t) {
        String stemmed;
        List<String> groupValues;
        String str;
        MatchResult m = Regex.find$default(new Regex("(?:^|\\s)(?:а\\s+)?в(?:о)?\\s+([а-яёa-z\\-]{2,})", RegexOption.IGNORE_CASE), t, 0, 2, null);
        String string = (m == null || (groupValues = m.getGroupValues()) == null || (str = (String) CollectionsKt.getOrNull(groupValues, 1)) == null) ? null : StringsKt.trim((CharSequence) str).toString();
        if (string == null) {
            string = "";
        }
        String city = StringsKt.trimEnd(string, '?', '!', '.', ',', Typography.ellipsis);
        if (StringsKt.isBlank(city) || SetsKt.setOf((Object[]) new String[]{"том", "этом", "чём", "чем", "погоду", "погоде"}).contains(city)) {
            return null;
        }
        Map<String, String> map = CITY_ALIASES;
        String lowerCase = city.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String it = map.get(lowerCase);
        if (it != null) {
            return it;
        }
        if (StringsKt.endsWith$default(city, "е", false, 2, (Object) null) && city.length() > 4) {
            stemmed = StringsKt.dropLast(city, 1);
        } else if (StringsKt.endsWith$default(city, "у", false, 2, (Object) null) && city.length() > 4) {
            stemmed = StringsKt.dropLast(city, 1);
        } else {
            stemmed = (!StringsKt.endsWith$default(city, "ы", false, 2, (Object) null) || city.length() <= 4) ? city : StringsKt.dropLast(city, 1) + "а";
        }
        Map<String, String> map2 = CITY_ALIASES;
        String lowerCase2 = stemmed.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
        String it2 = map2.get(lowerCase2);
        if (it2 != null) {
            return it2;
        }
        if (!(stemmed.length() > 0)) {
            return stemmed;
        }
        StringBuilder sb = new StringBuilder();
        char it3 = stemmed.charAt(0);
        StringBuilder sbAppend = sb.append((Object) (Character.isLowerCase(it3) ? CharsKt.titlecase(it3) : String.valueOf(it3)));
        String strSubstring = stemmed.substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return sbAppend.append(strSubstring).toString();
    }

    private final ToolCall volumeFollowUp(String t, ToolCall last) {
        String direction;
        if (!Intrinsics.areEqual(last.getToolId(), "volume")) {
            return null;
        }
        if (StringsKt.contains$default((CharSequence) t, (CharSequence) "громче", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "погромче", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "выше", false, 2, (Object) null)) {
            direction = "up";
        } else if (StringsKt.contains$default((CharSequence) t, (CharSequence) "тише", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "потише", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "ниже", false, 2, (Object) null)) {
            direction = "down";
        } else if (StringsKt.contains$default((CharSequence) t, (CharSequence) "макс", false, 2, (Object) null)) {
            direction = "max";
        } else if (StringsKt.contains$default((CharSequence) t, (CharSequence) "мут", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "без звука", false, 2, (Object) null)) {
            direction = "mute";
        } else {
            direction = null;
        }
        if (direction == null) {
            return null;
        }
        return ToolCall.copy$default(last, null, newId(), null, MapsKt.plus(last.getArguments(), MapsKt.mapOf(TuplesKt.to("direction", direction))), 5, null);
    }

    private final ToolCall brightnessFollowUp(String t, ToolCall last) {
        String direction;
        if (!Intrinsics.areEqual(last.getToolId(), "brightness")) {
            return null;
        }
        if (StringsKt.contains$default((CharSequence) t, (CharSequence) "ярче", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "поярче", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "светлее", false, 2, (Object) null)) {
            direction = "up";
        } else if (StringsKt.contains$default((CharSequence) t, (CharSequence) "темнее", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "тусклее", false, 2, (Object) null)) {
            direction = "down";
        } else {
            direction = null;
        }
        if (direction == null) {
            return null;
        }
        return ToolCall.copy$default(last, null, newId(), null, MapsKt.plus(last.getArguments(), MapsKt.mapOf(TuplesKt.to("direction", direction))), 5, null);
    }

    private final ToolCall flashlightFollowUp(String t, ToolCall last) {
        String action;
        if (!Intrinsics.areEqual(last.getToolId(), "flashlight")) {
            return null;
        }
        if (StringsKt.contains$default((CharSequence) t, (CharSequence) "выключ", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "погас", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "отключ", false, 2, (Object) null)) {
            action = "off";
        } else if (StringsKt.contains$default((CharSequence) t, (CharSequence) "включ", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "зажг", false, 2, (Object) null)) {
            action = "on";
        } else {
            action = null;
        }
        if (action == null) {
            return null;
        }
        return ToolCall.copy$default(last, null, newId(), null, MapsKt.mapOf(TuplesKt.to("action", action)), 5, null);
    }

    private final ToolCall musicFollowUp(String t, ToolCall last) {
        String str;
        if (!Intrinsics.areEqual(last.getToolId(), "music")) {
            return null;
        }
        if (StringsKt.contains$default((CharSequence) t, (CharSequence) "пауз", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "стоп", false, 2, (Object) null)) {
            str = "pause";
        } else if (StringsKt.contains$default((CharSequence) t, (CharSequence) "продолж", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) t, (CharSequence) "возобнов", false, 2, (Object) null)) {
            str = "resume";
        } else {
            str = "next";
            if (!StringsKt.contains$default((CharSequence) t, (CharSequence) "дальше", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) t, (CharSequence) "следующ", false, 2, (Object) null) && !Intrinsics.areEqual(t, "next")) {
                str = StringsKt.contains$default((CharSequence) t, (CharSequence) "предыдущ", false, 2, (Object) null) ? "previous" : null;
            }
        }
        if (str == null) {
            return null;
        }
        String action = str;
        return ToolCall.copy$default(last, null, newId(), null, MapsKt.mapOf(TuplesKt.to("action", action)), 5, null);
    }

    private final String newId() {
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }
}
