package com.jarvis.core.agent;

import com.jarvis.core.routines.EventMatcher;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: AgentShortcuts.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u000ej\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f`\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/jarvis/core/agent/AgentShortcuts;", "", "<init>", "()V", "YOUTUBE", "Lkotlin/text/Regex;", "AFTER_SEARCH", "YOUTUBE_WORD", "SETTINGS", "MAPS", "MAPS_AFTER", "WEB", "MORE_ACTIONS", "SECTIONS", "Ljava/util/LinkedHashMap;", "", "Lkotlin/collections/LinkedHashMap;", "match", "Lcom/jarvis/core/agent/AgentShortcut;", "goal", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AgentShortcuts {
    public static final AgentShortcuts INSTANCE = new AgentShortcuts();
    private static final Regex YOUTUBE = new Regex("ютуб|ютьюб|youtube");
    private static final Regex AFTER_SEARCH = new Regex("(?:найди|найти|поищи|ищи|включи|покажи)\\s+(?:мне\\s+)?(?:видео\\s+|ролик\\s+|ролики\\s+)?(?:про\\s+|о\\s+|об\\s+)?(.+)$");
    private static final Regex YOUTUBE_WORD = new Regex("\\s*(?:на|в)\\s+(?:ютубе|ютьюбе|youtube)\\s*");
    private static final Regex SETTINGS = new Regex("настрой");
    private static final Regex MAPS = new Regex("(?:найди|покажи)\\s+(?:на\\s+карт[еах]+|в\\s+(?:яндекс\\s+)?картах)\\s+(.+)$");
    private static final Regex MAPS_AFTER = new Regex("(?:найди|покажи)\\s+(.+?)\\s+(?:на\\s+карт[еах]+|в\\s+(?:яндекс\\s+)?картах)$");
    private static final Regex WEB = new Regex("(?:найди|поищи)\\s+в\\s+(?:браузере|интернете|сети|гугле|яндексе)\\s+(.+)$");
    private static final Regex MORE_ACTIONS = new Regex("\\bи\\s+(?:включи|выключи|открой|запусти|нажми|напиши|отправь|поставь)\\b");
    private static final LinkedHashMap<String, String> SECTIONS = MapsKt.linkedMapOf(TuplesKt.to("точк", "hotspot"), TuplesKt.to("блютуз", "bluetooth"), TuplesKt.to("блютус", "bluetooth"), TuplesKt.to("bluetooth", "bluetooth"), TuplesKt.to("вайфа", EventMatcher.WIFI), TuplesKt.to("вай фа", EventMatcher.WIFI), TuplesKt.to(EventMatcher.WIFI, EventMatcher.WIFI), TuplesKt.to("батаре", "battery"), TuplesKt.to("звук", "sound"), TuplesKt.to("экран", "display"), TuplesKt.to("памят", "storage"));

    private AgentShortcuts() {
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0065  */
    public final AgentShortcut match(String goal) {
        Object element$iv;
        Object key;
        String query;
        List<String> groupValues;
        String str;
        Intrinsics.checkNotNullParameter(goal, "goal");
        String lowerCase = goal.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String text = StringsKt.trim((CharSequence) lowerCase).toString();
        AgentShortcut setting = null;
        if (MORE_ACTIONS.containsMatchIn(text)) {
            return null;
        }
        if (YOUTUBE.containsMatchIn(text)) {
            MatchResult matchResultFind$default = Regex.find$default(AFTER_SEARCH, text, 0, 2, null);
            if (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null || (str = groupValues.get(1)) == null) {
                query = null;
            } else {
                String strReplace = YOUTUBE_WORD.replace(str, StringUtils.SPACE);
                if (strReplace != null) {
                    query = StringsKt.trim((CharSequence) strReplace).toString();
                } else {
                    query = null;
                }
            }
            if (query != null) {
                String it = query;
                String it2 = (it.length() > 0) && !YOUTUBE.matches(it) ? query : null;
                if (it2 != null) {
                    setting = new AgentShortcut.YouTubeSearch(it2);
                }
            }
            return setting;
        }
        MatchResult place = Regex.find$default(MAPS, text, 0, 2, null);
        if (place == null) {
            place = Regex.find$default(MAPS_AFTER, text, 0, 2, null);
        }
        if (place != null) {
            return new AgentShortcut.MapSearch(StringsKt.trim((CharSequence) place.getGroupValues().get(1)).toString());
        }
        MatchResult it3 = Regex.find$default(WEB, text, 0, 2, null);
        if (it3 != null) {
            return new AgentShortcut.WebSearch(StringsKt.trim((CharSequence) it3.getGroupValues().get(1)).toString());
        }
        if (!SETTINGS.containsMatchIn(text)) {
            return null;
        }
        Iterable iterableEntrySet = SECTIONS.entrySet();
        Intrinsics.checkNotNullExpressionValue(iterableEntrySet, "<get-entries>(...)");
        Iterable $this$firstOrNull$iv = iterableEntrySet;
        Iterator it4 = $this$firstOrNull$iv.iterator();
        do {
            if (!it4.hasNext()) {
                element$iv = null;
                break;
            }
            element$iv = it4.next();
            key = ((Map.Entry) element$iv).getKey();
            Intrinsics.checkNotNullExpressionValue(key, "<get-key>(...)");
        } while (!StringsKt.contains$default((CharSequence) text, (CharSequence) key, false, 2, (Object) null));
        Map.Entry it5 = (Map.Entry) element$iv;
        if (it5 != null) {
            Object value = it5.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-value>(...)");
            setting = new AgentShortcut.Setting((String) value);
        }
        return setting;
    }
}
