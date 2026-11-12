package com.jarvis.core.aioutput;

import androidx.core.app.NotificationCompat;
import com.jarvis.app.VoiceService;
import com.jarvis.core.common.model.ToolCall;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.CharsKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: CloudToolAction.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005J\u000e\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0005J\u000e\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005J\u000e\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0005J\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0005J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0019\u001a\u00020\u001aH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/jarvis/core/aioutput/CloudToolAction;", "", "<init>", "()V", "MARKER", "", "PARTIAL_TAIL", "", "ALLOWED", "", "PROMPT", "getPROMPT", "()Ljava/lang/String;", "mayBeAction", "", VoiceService.EXTRA_TEXT, "ACTION_START", "Lkotlin/text/Regex;", "actionStart", "isAction", "speakableLength", "parse", "Lcom/jarvis/core/common/model/ToolCall;", "requestId", "plain", "value", "Lcom/jarvis/core/aioutput/JsonVal;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CloudToolAction {
    public static final String MARKER = "ACT";
    private static final int PARTIAL_TAIL = 4;
    public static final CloudToolAction INSTANCE = new CloudToolAction();
    private static final Set<String> ALLOWED = SetsKt.setOf((Object[]) new String[]{"flashlight", "system.status", "web.answer", "notes", "app.action", "news", "rates", "location", NotificationCompat.CATEGORY_REMINDER, "briefing", "navigate", "web.search", "camera", "volume", "brightness", "timer", "launcher", "music", "weather", "system.setting", "screenshot", "phone.call", "sms", "telegram.send", "calendar", "contacts.search", "notifications.list"});
    private static final String PROMPT = "Если пользователь просит сделать что-то на телефоне, не отвечай текстом и никогда не говори, что сделал сам.\nОтветь ровно одной строкой без пояснений: ACT {\"tool\":\"<id>\",\"args\":{...}}\nЕсли для ответа нужны свежие данные (погода, календарь, состояние телефона), тоже вызови инструмент: ответишь по его результату.\nНа остальные вопросы и разговор отвечай обычным текстом.\nИнструменты:\nflashlight {\"action\":\"on|off\"}\nsystem.status {}\nweb.answer {\"query\":\"вопрос о свежих событиях, ценах, результатах, людях сейчас\"}\nnotes {\"phrase\":\"просьба про покупки, дела или заметки своими словами\"}\napp.action {\"kind\":\"youtube|maps|market|taxi|site\",\"query\":\"что искать\"}\nnews {}\nrates {}\nlocation {}\nbriefing {}\nnavigate {\"destination\":\"куда\"}\nweb.search {\"query\":\"что найти в браузере\"}\ncamera {\"mode\":\"photo|video\"}\nreminder {\"action\":\"add|list|cancel\",\"phrase\":\"что и когда, словами пользователя\",\"query\":\"какое отменить\"}\nvolume {\"direction\":\"up|down|max|mute|unmute\"} или {\"direction\":\"set\",\"percent\":0-100}\nbrightness {\"direction\":\"up|down|max\"} или {\"direction\":\"set\",\"percent\":0-100}\ntimer {\"minutes\":N,\"seconds\":N} или {\"action\":\"cancel|status\"} или {\"action\":\"alarm\",\"hour\":0-23,\"minute\":0-59}\nlauncher {\"action\":\"open\",\"app\":\"название\"}\nmusic {\"action\":\"play\",\"query\":\"что включить\"} или {\"action\":\"pause|resume|next|previous|status\"}\nweather {\"city\":\"город\",\"day\":0|1|2}\nsystem.setting {\"name\":\"wifi|bluetooth|airplane|battery|display|sound|storage|apps|location|notifications|security\",\"value\":\"open\"}\nscreenshot {}\nphone.call {\"contact\":\"имя\"} или {\"action\":\"history\"}\nsms {\"action\":\"send\",\"recipient\":\"имя\",\"text\":\"текст\"}\ntelegram.send {\"chat\":\"имя\",\"text\":\"текст\"}\ncalendar {\"action\":\"list\"}\ncontacts.search {\"query\":\"имя\"}\nnotifications.list {\"action\":\"list\"}";
    private static final Regex ACTION_START = new Regex("(?:^|(?<=[\\s.!?…»\"]))ACT\\s*\\{");

    private CloudToolAction() {
    }

    public final String getPROMPT() {
        return PROMPT;
    }

    public final boolean mayBeAction(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        String t = StringsKt.trimStart((CharSequence) text).toString();
        return StringsKt.startsWith$default(t, MARKER, false, 2, (Object) null) || (t.length() < 3 && StringsKt.startsWith$default(MARKER, t, false, 2, (Object) null));
    }

    public final int actionStart(String text) {
        IntRange range;
        Intrinsics.checkNotNullParameter(text, "text");
        MatchResult matchResultFind$default = Regex.find$default(ACTION_START, text, 0, 2, null);
        if (matchResultFind$default == null || (range = matchResultFind$default.getRange()) == null) {
            return -1;
        }
        return range.getFirst();
    }

    public final boolean isAction(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        return StringsKt.startsWith$default(StringsKt.trimStart((CharSequence) text).toString(), "ACT ", false, 2, (Object) null) || actionStart(text) >= 0;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x005e  */
    public final int speakableLength(String text) {
        boolean atBoundary;
        Intrinsics.checkNotNullParameter(text, "text");
        int start = actionStart(text);
        if (start >= 0) {
            return start;
        }
        int tail = Math.min(text.length(), 4);
        for (int keep = tail; keep > 0; keep--) {
            String strSubstring = text.substring(text.length() - keep);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            String end = StringsKt.trimEnd((CharSequence) strSubstring).toString();
            if (text.length() != keep) {
                char it = text.charAt((text.length() - keep) - 1);
                if (((CharsKt.isWhitespace(it) || StringsKt.contains$default((CharSequence) ".!?…»\"", it, false, 2, (Object) null)) ? (char) 1 : (char) 0) != 0) {
                    atBoundary = true;
                } else {
                    atBoundary = false;
                }
            } else {
                atBoundary = true;
            }
            if (atBoundary) {
                if ((end.length() > 0) && (Intrinsics.areEqual(end, MARKER) || StringsKt.startsWith$default(MARKER, end, false, 2, (Object) null))) {
                    return text.length() - keep;
                }
            }
        }
        int keep2 = text.length();
        return keep2;
    }

    public final ToolCall parse(String text, String requestId) {
        int iIndexOf$default;
        Object objM392constructorimpl;
        String it;
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Integer numValueOf = Integer.valueOf(actionStart(text));
        if (!(numValueOf.intValue() >= 0)) {
            numValueOf = null;
        }
        if (numValueOf != null) {
            iIndexOf$default = numValueOf.intValue();
        } else {
            if (!isAction(text)) {
                return null;
            }
            iIndexOf$default = StringsKt.indexOf$default((CharSequence) text, MARKER, 0, false, 6, (Object) null);
        }
        int start = iIndexOf$default;
        String strSubstring = text.substring(start);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        String body = StringsKt.trim((CharSequence) StringsKt.removePrefix(strSubstring, (CharSequence) MARKER)).toString();
        try {
            Result.Companion companion = Result.INSTANCE;
            CloudToolAction cloudToolAction = this;
            objM392constructorimpl = Result.m392constructorimpl(MiniJson.INSTANCE.parse(body));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = null;
        }
        JsonVal root = (JsonVal) objM392constructorimpl;
        if (root == null) {
            return null;
        }
        JsonVal jsonValOpt = root.opt("tool");
        if (jsonValOpt == null || (it = jsonValOpt.asString()) == null) {
            return null;
        }
        String tool = ALLOWED.contains(it) ? it : null;
        if (tool == null) {
            return null;
        }
        JsonVal jsonValOpt2 = root.opt("args");
        JsonVal.Obj obj = jsonValOpt2 instanceof JsonVal.Obj ? (JsonVal.Obj) jsonValOpt2 : null;
        LinkedHashMap<String, JsonVal> $this$mapNotNull$iv = obj != null ? obj.getMap() : null;
        if ($this$mapNotNull$iv == null) {
            $this$mapNotNull$iv = MapsKt.emptyMap();
        }
        Collection destination$iv$iv = new ArrayList();
        Map $this$mapNotNullTo$iv$iv = $this$mapNotNull$iv;
        for (Map.Entry element$iv$iv$iv : $this$mapNotNullTo$iv$iv.entrySet()) {
            String key = (String) element$iv$iv$iv.getKey();
            JsonVal root2 = root;
            JsonVal value = (JsonVal) element$iv$iv$iv.getValue();
            Object it2 = INSTANCE.plain(value);
            Pair pair = it2 != null ? TuplesKt.to(key, it2) : null;
            if (pair != null) {
                destination$iv$iv.add(pair);
            }
            root = root2;
        }
        Map args = MapsKt.toMap((List) destination$iv$iv);
        return new ToolCall(null, requestId, tool, args, 1, null);
    }

    private final Object plain(JsonVal value) {
        if (value instanceof JsonVal.Str) {
            return ((JsonVal.Str) value).getValue();
        }
        if (value instanceof JsonVal.Bool) {
            return Boolean.valueOf(((JsonVal.Bool) value).getValue());
        }
        if (value instanceof JsonVal.Num) {
            return (((JsonVal.Num) value).getValue() > ((double) ((int) ((JsonVal.Num) value).getValue())) ? 1 : (((JsonVal.Num) value).getValue() == ((double) ((int) ((JsonVal.Num) value).getValue())) ? 0 : -1)) == 0 ? Integer.valueOf((int) ((JsonVal.Num) value).getValue()) : Double.valueOf(((JsonVal.Num) value).getValue());
        }
        return null;
    }
}
