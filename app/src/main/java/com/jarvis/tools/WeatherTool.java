package com.jarvis.tools;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.text.HtmlCompat;
import com.jarvis.app.EventRoutineRunner;
import com.jarvis.core.common.Tool;
import com.jarvis.core.common.model.OnlineRequirement;
import com.jarvis.core.common.model.ParameterType;
import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.common.model.ToolParameter;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import com.jarvis.core.reminder.ReminderSpeech;
import com.jarvis.tools.net.SimpleHttp;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt;
import kotlin.text.CharsKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: WeatherTool.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0019B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00132\u0006\u0010\u0014\u001a\u00020\u0011H\u0002J\n\u0010\u0015\u001a\u0004\u0018\u00010\u000fH\u0002J\u0010\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0018H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u001a"}, d2 = {"Lcom/jarvis/tools/WeatherTool;", "Lcom/jarvis/core/common/Tool;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "geocode", "Lcom/jarvis/tools/WeatherTool$Place;", "city", "", "cityQueryVariants", "", "raw", "locateDevice", "weatherCodeRu", "code", "", "Place", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WeatherTool implements Tool {
    private final Context context;
    private final ToolDefinition definition;

    public WeatherTool(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.definition = new ToolDefinition("weather", "Погода", "Текущая погода и краткий прогноз по городу или координатам телефона", CollectionsKt.listOf((Object[]) new ToolParameter[]{new ToolParameter("city", "Город (опционально)", ParameterType.STRING, false, null, 16, null), new ToolParameter("day", "0=сегодня, 1=завтра, 2=послезавтра", ParameterType.INTEGER, false, null, 16, null)}), RiskLevel.SAFE, null, OnlineRequirement.ONLINE_REQUIRED, null, 160, null);
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    /* JADX WARN: Code duplicated, block: B:120:0x03dd  */
    /* JADX WARN: Code duplicated, block: B:125:0x0404  */
    /* JADX WARN: Code duplicated, block: B:128:0x040f A[Catch: all -> 0x049f, TryCatch #2 {all -> 0x049f, blocks: (B:41:0x00f8, B:43:0x00fe, B:47:0x0109, B:51:0x0114, B:59:0x012d, B:60:0x0167, B:62:0x016d, B:63:0x0189, B:64:0x01aa, B:66:0x01b0, B:67:0x01ce, B:68:0x01ef, B:70:0x01f5, B:71:0x0213, B:77:0x0272, B:81:0x0284, B:83:0x028a, B:85:0x0294, B:87:0x029a, B:89:0x02a7, B:91:0x02af, B:93:0x02b5, B:94:0x02b8, B:98:0x02d0, B:99:0x02e6, B:102:0x02f0, B:104:0x0342, B:103:0x0311, B:106:0x0387, B:109:0x03ae, B:111:0x03b4, B:113:0x03bc, B:117:0x03cb, B:119:0x03d1, B:124:0x03e5, B:128:0x040f, B:130:0x042d), top: B:164:0x00f8 }] */
    /* JADX WARN: Code duplicated, block: B:129:0x042b  */
    /* JADX WARN: Code duplicated, block: B:144:0x04c6  */
    /* JADX WARN: Code duplicated, block: B:146:0x04c9  */
    /* JADX WARN: Code duplicated, block: B:151:0x04de  */
    /* JADX WARN: Code duplicated, block: B:154:0x04e2  */
    /* JADX WARN: Code duplicated, block: B:155:0x04e5  */
    /* JADX WARN: Code duplicated, block: B:158:0x04ee  */
    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) {
        ToolResult toolResult;
        String message;
        boolean z;
        ToolResultStatus toolResultStatus;
        String message2;
        String message3;
        boolean z2;
        Place place;
        int dayOffset;
        String string;
        JSONObject daily;
        Double tmax;
        Double dValueOf;
        String dayPart;
        String humPart;
        JSONArray jSONArrayOptJSONArray;
        String strWeekdayPhrase;
        String reply;
        String string2;
        Intrinsics.checkNotNullParameter(call, "call");
        boolean z3 = false;
        try {
            Object obj = call.getArguments().get("city");
            String cityRaw = (obj == null || (string2 = obj.toString()) == null) ? null : StringsKt.trim((CharSequence) string2).toString();
            if (cityRaw == null) {
                cityRaw = "";
            }
            if (StringsKt.isBlank(cityRaw)) {
                place = locateDevice();
                if (place == null) {
                    place = geocode("Москва");
                }
            } else {
                place = geocode(cityRaw);
            }
            if (place == null) {
                return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.FAILED, "Не удалось определить место. Назовите город, сэр.", null, 0L, 48, null);
            }
            String url = "https://api.open-meteo.com/v1/forecast?latitude=" + place.getLat() + "&longitude=" + place.getLon() + "&current=temperature_2m,apparent_temperature,weather_code,wind_speed_10m,relative_humidity_2m&daily=temperature_2m_max,temperature_2m_min,weather_code&forecast_days=7&timezone=auto";
            JSONObject json = SimpleHttp.INSTANCE.getJson(url, 720000L);
            Object obj2 = call.getArguments().get("day");
            Number number = obj2 instanceof Number ? (Number) obj2 : null;
            if (number != null) {
                dayOffset = number.intValue();
            } else {
                Object obj3 = call.getArguments().get("day");
                Integer intOrNull = (obj3 == null || (string = obj3.toString()) == null) ? null : StringsKt.toIntOrNull(string);
                dayOffset = intOrNull != null ? intOrNull.intValue() : 0;
            }
            int dayIdx = RangesKt.coerceIn(dayOffset, 0, 6);
            if (Intrinsics.areEqual(call.getArguments().get("week"), (Object) true)) {
                try {
                    JSONObject daily2 = json.optJSONObject("daily");
                    JSONArray highs = daily2 != null ? daily2.optJSONArray("temperature_2m_max") : null;
                    JSONArray lows = daily2 != null ? daily2.optJSONArray("temperature_2m_min") : null;
                    JSONArray codes = daily2 != null ? daily2.optJSONArray("weather_code") : null;
                    JSONArray highs2 = highs;
                    if (highs2 != null && lows != null && codes != null) {
                        Iterable range = RangesKt.until(0, Math.min(highs2.length(), Math.min(lows.length(), codes.length())));
                        String name = place.getName();
                        Iterable $this$map$iv = range;
                        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                        Iterator<Integer> it = $this$map$iv.iterator();
                        while (it.hasNext()) {
                            int item$iv$iv = ((IntIterator) it).nextInt();
                            destination$iv$iv.add(Double.valueOf(lows.optDouble(item$iv$iv)));
                        }
                        ArrayList arrayList = (List) destination$iv$iv;
                        Iterable $this$map$iv2 = range;
                        int $i$f$map = CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10);
                        Collection destination$iv$iv2 = new ArrayList($i$f$map);
                        Iterator<Integer> it2 = $this$map$iv2.iterator();
                        while (it2.hasNext()) {
                            int item$iv$iv2 = ((IntIterator) it2).nextInt();
                            destination$iv$iv2.add(Double.valueOf(highs2.optDouble(item$iv$iv2)));
                            $this$map$iv2 = $this$map$iv2;
                        }
                        ArrayList arrayList2 = (List) destination$iv$iv2;
                        Iterable $this$map$iv3 = range;
                        Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv3, 10));
                        Iterator<Integer> it3 = $this$map$iv3.iterator();
                        while (it3.hasNext()) {
                            int item$iv$iv3 = ((IntIterator) it3).nextInt();
                            int it4 = codes.optInt(item$iv$iv3);
                            destination$iv$iv3.add(Integer.valueOf(it4));
                            highs2 = highs2;
                        }
                        LocalDate localDateNow = LocalDate.now();
                        Intrinsics.checkNotNullExpressionValue(localDateNow, "now(...)");
                        return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, WeatherToolKt.weekSummary(name, arrayList, arrayList2, (List) destination$iv$iv3, localDateNow), MapsKt.mapOf(TuplesKt.to("city", place.getName())), 0L, 32, null);
                    }
                } catch (Throwable th) {
                    t = th;
                    z3 = false;
                    String requestId = call.getRequestId();
                    String id = getDefinition().getId();
                    message = t.getMessage();
                    if (message == null) {
                        z = z3;
                    } else {
                        z = z3;
                    }
                    if (z) {
                        toolResultStatus = ToolResultStatus.OFFLINE;
                    } else {
                        message3 = t.getMessage();
                        if (message3 == null) {
                            z2 = z3;
                        } else {
                            z2 = z3;
                        }
                        if (z2) {
                            toolResultStatus = ToolResultStatus.OFFLINE;
                        } else {
                            toolResultStatus = ToolResultStatus.FAILED;
                        }
                    }
                    ToolResultStatus toolResultStatus2 = toolResultStatus;
                    message2 = t.getMessage();
                    if (message2 == null) {
                        message2 = t.getClass().getSimpleName();
                    }
                    toolResult = new ToolResult(requestId, id, toolResultStatus2, "Не получил погоду: " + message2, null, 0L, 48, null);
                    return toolResult;
                }
            }
            JSONObject current = json.getJSONObject("current");
            JSONObject daily3 = json.optJSONObject("daily");
            if (dayIdx > 0 && daily3 != null) {
                JSONArray jSONArrayOptJSONArray2 = daily3.optJSONArray("temperature_2m_max");
                Double tmax2 = jSONArrayOptJSONArray2 != null ? Double.valueOf(jSONArrayOptJSONArray2.optDouble(dayIdx)) : null;
                JSONArray jSONArrayOptJSONArray3 = daily3.optJSONArray("temperature_2m_min");
                Double tmin = jSONArrayOptJSONArray3 != null ? Double.valueOf(jSONArrayOptJSONArray3.optDouble(dayIdx)) : null;
                JSONArray jSONArrayOptJSONArray4 = daily3.optJSONArray("weather_code");
                int code = jSONArrayOptJSONArray4 != null ? jSONArrayOptJSONArray4.optInt(dayIdx) : 0;
                switch (dayIdx) {
                    case 0:
                        strWeekdayPhrase = "сегодня";
                        break;
                    case 1:
                        strWeekdayPhrase = "завтра";
                        break;
                    case 2:
                        strWeekdayPhrase = "послезавтра";
                        break;
                    default:
                        ReminderSpeech reminderSpeech = ReminderSpeech.INSTANCE;
                        LocalDate localDatePlusDays = LocalDate.now().plusDays(dayIdx);
                        Intrinsics.checkNotNullExpressionValue(localDatePlusDays, "plusDays(...)");
                        strWeekdayPhrase = reminderSpeech.weekdayPhrase(localDatePlusDays);
                        break;
                }
                String label = strWeekdayPhrase;
                String desc = weatherCodeRu(code);
                if (tmax2 == null || tmin == null) {
                    reply = place.getName() + ", " + label + ": " + desc + ".";
                } else {
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String str = String.format(Locale.US, "%s, %s: от %.0f° до %.0f°. %s.", Arrays.copyOf(new Object[]{place.getName(), label, tmin, tmax2, desc}, 5));
                    Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                    reply = str;
                }
                return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, reply, MapsKt.mapOf(TuplesKt.to("city", place.getName()), TuplesKt.to("day", Integer.valueOf(dayIdx)), TuplesKt.to("code", Integer.valueOf(code))), 0L, 32, null);
            }
            double temp = current.getDouble("temperature_2m");
            double feels = current.optDouble("apparent_temperature", temp);
            double wind = current.optDouble("wind_speed_10m", 0.0d);
            int humidity = current.optInt("relative_humidity_2m", -1);
            try {
                int code2 = current.optInt("weather_code", 0);
                String desc2 = weatherCodeRu(code2);
                if (daily3 != null) {
                    daily = daily3;
                    JSONArray jSONArrayOptJSONArray5 = daily.optJSONArray("temperature_2m_max");
                    tmax = jSONArrayOptJSONArray5 != null ? Double.valueOf(jSONArrayOptJSONArray5.optDouble(0)) : null;
                    if (daily != null || (jSONArrayOptJSONArray = daily.optJSONArray("temperature_2m_min")) == null) {
                        dValueOf = null;
                    } else {
                        dValueOf = Double.valueOf(jSONArrayOptJSONArray.optDouble(0));
                    }
                    Double tmin2 = dValueOf;
                    if (tmax != null || tmin2 == null) {
                        dayPart = r8;
                    } else {
                        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                        String str2 = String.format(Locale.US, " Сегодня от %.0f° до %.0f°.", Arrays.copyOf(new Object[]{tmin2, tmax}, 2));
                        Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
                        dayPart = str2;
                    }
                    if (humidity >= 0) {
                        humPart = " Влажность " + humidity + "%.";
                    } else {
                        humPart = "";
                    }
                    StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                    String reply2 = String.format(Locale.US, "%s, сейчас %.0f°, ощущается как %.0f°. %s. Ветер %.0f м/с.%s%s", Arrays.copyOf(new Object[]{place.getName(), Double.valueOf(temp), Double.valueOf(feels), desc2, Double.valueOf(wind), humPart, dayPart}, 7));
                    Intrinsics.checkNotNullExpressionValue(reply2, "format(...)");
                    String requestId2 = call.getRequestId();
                    String id2 = getDefinition().getId();
                    ToolResultStatus toolResultStatus3 = ToolResultStatus.SUCCESS;
                    Pair[] pairArr = new Pair[3];
                    pairArr[0] = TuplesKt.to("city", place.getName());
                    pairArr[1] = TuplesKt.to("temp", Double.valueOf(temp));
                    pairArr[2] = TuplesKt.to("code", Integer.valueOf(code2));
                    toolResult = new ToolResult(requestId2, id2, toolResultStatus3, reply2, MapsKt.mapOf(pairArr), 0L, 32, null);
                    return toolResult;
                }
                daily = daily3;
                if (daily != null) {
                    dValueOf = null;
                } else {
                    dValueOf = null;
                }
                Double tmin3 = dValueOf;
                if (tmax != null) {
                    dayPart = r8;
                } else {
                    dayPart = r8;
                }
                if (humidity >= 0) {
                    humPart = " Влажность " + humidity + "%.";
                } else {
                    humPart = "";
                }
                StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
                String reply3 = String.format(Locale.US, "%s, сейчас %.0f°, ощущается как %.0f°. %s. Ветер %.0f м/с.%s%s", Arrays.copyOf(new Object[]{place.getName(), Double.valueOf(temp), Double.valueOf(feels), desc2, Double.valueOf(wind), humPart, dayPart}, 7));
                Intrinsics.checkNotNullExpressionValue(reply3, "format(...)");
                String requestId3 = call.getRequestId();
                String id3 = getDefinition().getId();
                ToolResultStatus toolResultStatus4 = ToolResultStatus.SUCCESS;
                Pair[] pairArr2 = new Pair[3];
                pairArr2[0] = TuplesKt.to("city", place.getName());
                pairArr2[1] = TuplesKt.to("temp", Double.valueOf(temp));
                pairArr2[2] = TuplesKt.to("code", Integer.valueOf(code2));
                toolResult = new ToolResult(requestId3, id3, toolResultStatus4, reply3, MapsKt.mapOf(pairArr2), 0L, 32, null);
            } catch (Throwable th2) {
                t = th2;
                z3 = false;
                String requestId4 = call.getRequestId();
                String id4 = getDefinition().getId();
                message = t.getMessage();
                if (message == null && StringsKt.contains((CharSequence) message, (CharSequence) "Unable to resolve", true)) {
                    z = true;
                } else {
                    z = z3;
                }
                if (z) {
                    toolResultStatus = ToolResultStatus.OFFLINE;
                } else {
                    message3 = t.getMessage();
                    if (message3 == null && StringsKt.contains((CharSequence) message3, (CharSequence) "Failed to connect", true)) {
                        z2 = true;
                    } else {
                        z2 = z3;
                    }
                    if (z2) {
                        toolResultStatus = ToolResultStatus.OFFLINE;
                    } else {
                        toolResultStatus = ToolResultStatus.FAILED;
                    }
                }
                ToolResultStatus toolResultStatus5 = toolResultStatus;
                message2 = t.getMessage();
                if (message2 == null) {
                    message2 = t.getClass().getSimpleName();
                }
                toolResult = new ToolResult(requestId4, id4, toolResultStatus5, "Не получил погоду: " + message2, null, 0L, 48, null);
            }
            return toolResult;
        } catch (Throwable th3) {
            t = th3;
        }
    }

    /* JADX INFO: compiled from: WeatherTool.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/jarvis/tools/WeatherTool$Place;", "", "name", "", "lat", "", "lon", "<init>", "(Ljava/lang/String;DD)V", "getName", "()Ljava/lang/String;", "getLat", "()D", "getLon", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final /* data */ class Place {
        private final double lat;
        private final double lon;
        private final String name;

        public static /* synthetic */ Place copy$default(Place place, String str, double d, double d2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = place.name;
            }
            if ((i & 2) != 0) {
                d = place.lat;
            }
            double d3 = d;
            if ((i & 4) != 0) {
                d2 = place.lon;
            }
            return place.copy(str, d3, d2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final double getLat() {
            return this.lat;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final double getLon() {
            return this.lon;
        }

        public final Place copy(String name, double lat, double lon) {
            Intrinsics.checkNotNullParameter(name, "name");
            return new Place(name, lat, lon);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Place)) {
                return false;
            }
            Place place = (Place) other;
            return Intrinsics.areEqual(this.name, place.name) && Double.compare(this.lat, place.lat) == 0 && Double.compare(this.lon, place.lon) == 0;
        }

        public int hashCode() {
            return (((this.name.hashCode() * 31) + Double.hashCode(this.lat)) * 31) + Double.hashCode(this.lon);
        }

        public String toString() {
            return "Place(name=" + this.name + ", lat=" + this.lat + ", lon=" + this.lon + ")";
        }

        public Place(String name, double lat, double lon) {
            Intrinsics.checkNotNullParameter(name, "name");
            this.name = name;
            this.lat = lat;
            this.lon = lon;
        }

        public final double getLat() {
            return this.lat;
        }

        public final double getLon() {
            return this.lon;
        }

        public final String getName() {
            return this.name;
        }
    }

    private final Place geocode(String city) throws JSONException, UnsupportedEncodingException {
        Object objM392constructorimpl;
        JSONArray results;
        Iterator<String> it = cityQueryVariants(city).iterator();
        while (true) {
            if (!it.hasNext()) {
                return null;
            }
            String candidate = it.next();
            String q = URLEncoder.encode(candidate, Charsets.UTF_8.name());
            String url = "https://geocoding-api.open-meteo.com/v1/search?name=" + q + "&count=1&language=ru&format=json";
            try {
                Result.Companion companion = Result.INSTANCE;
                WeatherTool weatherTool = this;
                objM392constructorimpl = Result.m392constructorimpl(SimpleHttp.INSTANCE.getJson(url, 86400000L));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
            }
            JSONObject json = (JSONObject) (Result.m398isFailureimpl(objM392constructorimpl) ? null : objM392constructorimpl);
            if (json != null && (results = json.optJSONArray("results")) != null && results.length() != 0) {
                JSONObject first = results.getJSONObject(0);
                String strOptString = first.optString("name", candidate);
                Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
                return new Place(strOptString, first.getDouble("latitude"), first.getDouble("longitude"));
            }
        }
    }

    private final List<String> cityQueryVariants(String raw) {
        String lowerCase = raw.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String c = StringsKt.trim((CharSequence) lowerCase).toString();
        if (StringsKt.isBlank(c)) {
            return CollectionsKt.emptyList();
        }
        Map known = MapsKt.mapOf(TuplesKt.to("москве", "Москва"), TuplesKt.to("москвы", "Москва"), TuplesKt.to("москву", "Москва"), TuplesKt.to("москва", "Москва"), TuplesKt.to("питере", "Санкт-Петербург"), TuplesKt.to("питера", "Санкт-Петербург"), TuplesKt.to("петербурге", "Санкт-Петербург"), TuplesKt.to("петербург", "Санкт-Петербург"), TuplesKt.to("спб", "Санкт-Петербург"), TuplesKt.to("казани", "Казань"), TuplesKt.to("казань", "Казань"), TuplesKt.to("новосибирске", "Новосибирск"), TuplesKt.to("новосибирск", "Новосибирск"), TuplesKt.to("екб", "Екатеринбург"), TuplesKt.to("екатеринбурге", "Екатеринбург"), TuplesKt.to("екатеринбург", "Екатеринбург"), TuplesKt.to("сочи", "Сочи"), TuplesKt.to("минске", "Минск"), TuplesKt.to("минск", "Минск"));
        String primary = (String) known.get(c);
        if (primary == null) {
            primary = StringsKt.trim((CharSequence) raw).toString();
            if (primary.length() > 0) {
                StringBuilder sb = new StringBuilder();
                char it = primary.charAt(0);
                StringBuilder sbAppend = sb.append((Object) (Character.isLowerCase(it) ? CharsKt.titlecase(it, new Locale("ru")) : String.valueOf(it)));
                String strSubstring = primary.substring(1);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                primary = sbAppend.append(strSubstring).toString();
            }
        }
        String strDropLast = null;
        if (StringsKt.endsWith$default(c, "е", false, 2, (Object) null) && c.length() > 4) {
            strDropLast = StringsKt.dropLast(c, 1);
            if (strDropLast.length() > 0) {
                StringBuilder sb2 = new StringBuilder();
                char it2 = strDropLast.charAt(0);
                String strValueOf = String.valueOf(it2);
                Intrinsics.checkNotNull(strValueOf, "null cannot be cast to non-null type java.lang.String");
                String upperCase = strValueOf.toUpperCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                StringBuilder sbAppend2 = sb2.append((Object) upperCase);
                String strSubstring2 = strDropLast.substring(1);
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                strDropLast = sbAppend2.append(strSubstring2).toString();
            }
        } else if (StringsKt.endsWith$default(c, "и", false, 2, (Object) null) && c.length() > 4) {
            strDropLast = StringsKt.dropLast(c, 1);
            if (strDropLast.length() > 0) {
                StringBuilder sb3 = new StringBuilder();
                char it3 = strDropLast.charAt(0);
                String strValueOf2 = String.valueOf(it3);
                Intrinsics.checkNotNull(strValueOf2, "null cannot be cast to non-null type java.lang.String");
                String upperCase2 = strValueOf2.toUpperCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(upperCase2, "toUpperCase(...)");
                StringBuilder sbAppend3 = sb3.append((Object) upperCase2);
                String strSubstring3 = strDropLast.substring(1);
                Intrinsics.checkNotNullExpressionValue(strSubstring3, "substring(...)");
                strDropLast = sbAppend3.append(strSubstring3).toString();
            }
        }
        String stemmed = strDropLast;
        return CollectionsKt.distinct(CollectionsKt.listOfNotNull((Object[]) new String[]{primary, stemmed, StringsKt.trim((CharSequence) raw).toString()}));
    }

    private final Place locateDevice() {
        Object objM392constructorimpl;
        if (this.context.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0) {
            return null;
        }
        Object systemService = this.context.getSystemService("location");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.location.LocationManager");
        LocationManager lm = (LocationManager) systemService;
        List<String> providers = CollectionsKt.listOf((Object[]) new String[]{EventRoutineRunner.NETWORK, "gps"});
        for (String p : providers) {
            try {
                Result.Companion companion = Result.INSTANCE;
                WeatherTool weatherTool = this;
                objM392constructorimpl = Result.m392constructorimpl(lm.getLastKnownLocation(p));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m398isFailureimpl(objM392constructorimpl)) {
                objM392constructorimpl = null;
            }
            Location loc = (Location) objM392constructorimpl;
            if (loc != null) {
                return new Place("В вашем районе", loc.getLatitude(), loc.getLongitude());
            }
        }
        return null;
    }

    private final String weatherCodeRu(int code) {
        switch (code) {
            case 0:
                return "Ясно";
            case 1:
            case 2:
                return "Малооблачно";
            case 3:
                return "Пасмурно";
            case 45:
            case 48:
                return "Туман";
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
            case 55:
                return "Морось";
            case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
            case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                return "Дождь";
            case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
            case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                return "Ледяной дождь";
            case 71:
            case 73:
            case 75:
            case 77:
                return "Снег";
            case 80:
            case 81:
            case 82:
                return "Ливень";
            case 85:
            case 86:
                return "Снегопад";
            case 95:
                return "Гроза";
            case 96:
            case 99:
                return "Гроза с градом";
            default:
                return "Переменная облачность";
        }
    }
}
