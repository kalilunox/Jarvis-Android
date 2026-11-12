package com.jarvis.brain;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.actions.SearchIntents;
import com.jarvis.app.VoiceService;
import com.jarvis.app.ui.JarvisActivity;
import com.jarvis.core.common.model.Intent;
import com.jarvis.core.common.model.IntentType;
import com.jarvis.core.common.model.Slot;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.tools.NotificationReplyTool;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ResponsePlanner.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u001c\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¨\u0006\f"}, d2 = {"Lcom/jarvis/brain/ToolCallFactory;", "", "<init>", "()V", "build", "Lcom/jarvis/core/common/model/ToolCall;", "intent", "Lcom/jarvis/core/common/model/Intent;", "requestId", "", "slotsToArgs", "", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ToolCallFactory {
    public static final ToolCallFactory INSTANCE = new ToolCallFactory();

    /* JADX INFO: compiled from: ResponsePlanner.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[IntentType.values().length];
            try {
                iArr[IntentType.FLASHLIGHT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[IntentType.VOLUME.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[IntentType.BRIGHTNESS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[IntentType.TIMER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[IntentType.CREATE_EVENT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[IntentType.SEND_SMS.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[IntentType.SEND_TELEGRAM.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[IntentType.LAUNCH_APP.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[IntentType.SYSTEM_SETTING.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[IntentType.CALL.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[IntentType.CONTACTS.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[IntentType.FILES.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr[IntentType.UI_CLICK.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr[IntentType.UI_TYPE.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                iArr[IntentType.UI_SWIPE.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                iArr[IntentType.UI_GLOBAL.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                iArr[IntentType.UI_READ.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                iArr[IntentType.UI_WAIT.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            try {
                iArr[IntentType.NOTIFICATIONS.ordinal()] = 19;
            } catch (NoSuchFieldError e19) {
            }
            try {
                iArr[IntentType.CLIPBOARD.ordinal()] = 20;
            } catch (NoSuchFieldError e20) {
            }
            try {
                iArr[IntentType.SCREENSHOT.ordinal()] = 21;
            } catch (NoSuchFieldError e21) {
            }
            try {
                iArr[IntentType.WEATHER.ordinal()] = 22;
            } catch (NoSuchFieldError e22) {
            }
            try {
                iArr[IntentType.NEWS.ordinal()] = 23;
            } catch (NoSuchFieldError e23) {
            }
            try {
                iArr[IntentType.RATES.ordinal()] = 24;
            } catch (NoSuchFieldError e24) {
            }
            try {
                iArr[IntentType.LOCATION.ordinal()] = 25;
            } catch (NoSuchFieldError e25) {
            }
            try {
                iArr[IntentType.PHONE_CONTROL.ordinal()] = 26;
            } catch (NoSuchFieldError e26) {
            }
            try {
                iArr[IntentType.ANNOUNCE_SETTINGS.ordinal()] = 27;
            } catch (NoSuchFieldError e27) {
            }
            try {
                iArr[IntentType.NOTES.ordinal()] = 28;
            } catch (NoSuchFieldError e28) {
            }
            try {
                iArr[IntentType.PLACES.ordinal()] = 29;
            } catch (NoSuchFieldError e29) {
            }
            try {
                iArr[IntentType.CUSTOM_ROUTINE.ordinal()] = 30;
            } catch (NoSuchFieldError e30) {
            }
            try {
                iArr[IntentType.APP_ACTION.ordinal()] = 31;
            } catch (NoSuchFieldError e31) {
            }
            try {
                iArr[IntentType.APP_AGENT.ordinal()] = 32;
            } catch (NoSuchFieldError e32) {
            }
            try {
                iArr[IntentType.WEB_ANSWER.ordinal()] = 33;
            } catch (NoSuchFieldError e33) {
            }
            try {
                iArr[IntentType.FORGET.ordinal()] = 34;
            } catch (NoSuchFieldError e34) {
            }
            try {
                iArr[IntentType.BRIEFING.ordinal()] = 35;
            } catch (NoSuchFieldError e35) {
            }
            try {
                iArr[IntentType.NAVIGATE.ordinal()] = 36;
            } catch (NoSuchFieldError e36) {
            }
            try {
                iArr[IntentType.WEB_SEARCH.ordinal()] = 37;
            } catch (NoSuchFieldError e37) {
            }
            try {
                iArr[IntentType.CAMERA.ordinal()] = 38;
            } catch (NoSuchFieldError e38) {
            }
            try {
                iArr[IntentType.REMINDER.ordinal()] = 39;
            } catch (NoSuchFieldError e39) {
            }
            try {
                iArr[IntentType.SYSTEM_STATUS.ordinal()] = 40;
            } catch (NoSuchFieldError e40) {
            }
            try {
                iArr[IntentType.MUSIC.ordinal()] = 41;
            } catch (NoSuchFieldError e41) {
            }
            try {
                iArr[IntentType.KNOWLEDGE.ordinal()] = 42;
            } catch (NoSuchFieldError e42) {
            }
            try {
                iArr[IntentType.UNKNOWN.ordinal()] = 43;
            } catch (NoSuchFieldError e43) {
            }
            try {
                iArr[IntentType.CHAT.ordinal()] = 44;
            } catch (NoSuchFieldError e44) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private ToolCallFactory() {
    }

    public final ToolCall build(Intent intent, String requestId) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        boolean z = true;
        Object value = null;
        switch (WhenMappings.$EnumSwitchMapping$0[intent.getType().ordinal()]) {
            case 1:
                Slot slot = intent.getSlots().get("action");
                Object value2 = slot != null ? slot.getValue() : null;
                String action = value2 instanceof String ? (String) value2 : null;
                if (action == null) {
                    return null;
                }
                return new ToolCall(null, requestId, "flashlight", MapsKt.mapOf(TuplesKt.to("action", action)), 1, null);
            case 2:
                Slot slot2 = intent.getSlots().get("direction");
                Object value3 = slot2 != null ? slot2.getValue() : null;
                String direction = value3 instanceof String ? (String) value3 : null;
                if (direction == null) {
                    return null;
                }
                Slot slot3 = intent.getSlots().get("percent");
                Object value4 = slot3 != null ? slot3.getValue() : null;
                value = value4 instanceof Integer ? (Integer) value4 : null;
                Map $this$build_u24lambda_u240 = MapsKt.createMapBuilder();
                $this$build_u24lambda_u240.put("direction", direction);
                if (value != null) {
                    $this$build_u24lambda_u240.put("percent", value);
                }
                Unit unit = Unit.INSTANCE;
                return new ToolCall(null, requestId, "volume", MapsKt.build($this$build_u24lambda_u240), 1, null);
            case 3:
                Slot slot4 = intent.getSlots().get("direction");
                Object value5 = slot4 != null ? slot4.getValue() : null;
                if ((value5 instanceof String ? (String) value5 : null) == null) {
                    return null;
                }
                return new ToolCall(null, requestId, "brightness", slotsToArgs(intent), 1, null);
            case 4:
                if (intent.getSlots().containsKey("action")) {
                    return new ToolCall(null, requestId, "timer", slotsToArgs(intent), 1, null);
                }
                Slot slot5 = intent.getSlots().get("minutes");
                Object value6 = slot5 != null ? slot5.getValue() : null;
                Integer num = value6 instanceof Integer ? (Integer) value6 : null;
                int minutes = num != null ? num.intValue() : 0;
                Slot slot6 = intent.getSlots().get("seconds");
                Object value7 = slot6 != null ? slot6.getValue() : null;
                Integer num2 = value7 instanceof Integer ? (Integer) value7 : null;
                int seconds = num2 != null ? num2.intValue() : 0;
                if (minutes == 0 && seconds == 0) {
                    return null;
                }
                return new ToolCall(null, requestId, "timer", MapsKt.mapOf(TuplesKt.to("minutes", Integer.valueOf(minutes)), TuplesKt.to("seconds", Integer.valueOf(seconds))), 1, null);
            case 5:
                Slot slot7 = intent.getSlots().get("action");
                Object value8 = slot7 != null ? slot7.getValue() : null;
                if (Intrinsics.areEqual(value8 instanceof String ? (String) value8 : null, "list")) {
                    return new ToolCall(null, requestId, "calendar", slotsToArgs(intent), 1, null);
                }
                Slot slot8 = intent.getSlots().get("title");
                Object value9 = slot8 != null ? slot8.getValue() : null;
                String str = value9 instanceof String ? (String) value9 : null;
                if (str == null) {
                    return null;
                }
                String title = str;
                return new ToolCall(null, requestId, "calendar", MapsKt.mapOf(TuplesKt.to("action", "create"), TuplesKt.to("title", title)), 1, null);
            case 6:
                Slot slot9 = intent.getSlots().get("action");
                Object value10 = slot9 != null ? slot9.getValue() : null;
                if (Intrinsics.areEqual(value10 instanceof String ? (String) value10 : null, "read")) {
                    return new ToolCall(null, requestId, "sms", MapsKt.mapOf(TuplesKt.to("action", "read")), 1, null);
                }
                Slot slot10 = intent.getSlots().get(VoiceService.EXTRA_TEXT);
                Object value11 = slot10 != null ? slot10.getValue() : null;
                String str2 = value11 instanceof String ? (String) value11 : null;
                if (str2 == null) {
                    return null;
                }
                String text = str2;
                Slot slot11 = intent.getSlots().get("recipient");
                Object value12 = slot11 != null ? slot11.getValue() : null;
                value = value12 instanceof String ? (String) value12 : null;
                Map $this$build_u24lambda_u241 = MapsKt.createMapBuilder();
                $this$build_u24lambda_u241.put("action", "send");
                $this$build_u24lambda_u241.put(VoiceService.EXTRA_TEXT, text);
                if (value != null) {
                    $this$build_u24lambda_u241.put("recipient", value);
                }
                Unit unit2 = Unit.INSTANCE;
                return new ToolCall(null, requestId, "sms", MapsKt.build($this$build_u24lambda_u241), 1, null);
            case 7:
                Slot slot12 = intent.getSlots().get(JarvisActivity.CHAT);
                Object value13 = slot12 != null ? slot12.getValue() : null;
                String chat = value13 instanceof String ? (String) value13 : null;
                Slot slot13 = intent.getSlots().get(VoiceService.EXTRA_TEXT);
                Object value14 = slot13 != null ? slot13.getValue() : null;
                value = value14 instanceof String ? (String) value14 : null;
                Map $this$build_u24lambda_u242 = MapsKt.createMapBuilder();
                String str3 = chat;
                if (!(str3 == null || StringsKt.isBlank(str3))) {
                    $this$build_u24lambda_u242.put(JarvisActivity.CHAT, chat);
                }
                CharSequence charSequence = (CharSequence) value;
                if (charSequence != null && !StringsKt.isBlank(charSequence)) {
                    z = false;
                }
                if (!z) {
                    $this$build_u24lambda_u242.put(VoiceService.EXTRA_TEXT, value);
                }
                Unit unit3 = Unit.INSTANCE;
                return new ToolCall(null, requestId, "telegram.send", MapsKt.build($this$build_u24lambda_u242), 1, null);
            case 8:
                Map<String, Object> mapSlotsToArgs = slotsToArgs(intent);
                if (mapSlotsToArgs.containsKey("app") || mapSlotsToArgs.containsKey("action")) {
                    return new ToolCall(null, requestId, "launcher", mapSlotsToArgs, 1, null);
                }
                return null;
            case 9:
                Slot slot14 = intent.getSlots().get("name");
                Object value15 = slot14 != null ? slot14.getValue() : null;
                String name = value15 instanceof String ? (String) value15 : null;
                if (name == null) {
                    return null;
                }
                Slot slot15 = intent.getSlots().get("value");
                value = slot15 != null ? slot15.getValue() : null;
                Map $this$build_u24lambda_u243 = MapsKt.createMapBuilder();
                $this$build_u24lambda_u243.put("name", name);
                if (value != null) {
                    $this$build_u24lambda_u243.put("value", value);
                }
                Unit unit4 = Unit.INSTANCE;
                return new ToolCall(null, requestId, "system.setting", MapsKt.build($this$build_u24lambda_u243), 1, null);
            case 10:
                Slot slot16 = intent.getSlots().get("action");
                Object value16 = slot16 != null ? slot16.getValue() : null;
                String action2 = value16 instanceof String ? (String) value16 : null;
                if (action2 == null) {
                    action2 = "call";
                }
                if (Intrinsics.areEqual(action2, "history") || Intrinsics.areEqual(action2, "redial")) {
                    return new ToolCall(null, requestId, "phone.call", MapsKt.mapOf(TuplesKt.to("action", action2)), 1, null);
                }
                Slot slot17 = intent.getSlots().get("contact");
                Object value17 = slot17 != null ? slot17.getValue() : null;
                String str4 = value17 instanceof String ? (String) value17 : null;
                if (str4 == null) {
                    return null;
                }
                String contact = str4;
                return new ToolCall(null, requestId, "phone.call", MapsKt.mapOf(TuplesKt.to("action", "call"), TuplesKt.to("contact", contact)), 1, null);
            case 11:
                Slot slot18 = intent.getSlots().get(SearchIntents.EXTRA_QUERY);
                Object value18 = slot18 != null ? slot18.getValue() : null;
                String query = value18 instanceof String ? (String) value18 : null;
                if (query == null) {
                    return null;
                }
                return new ToolCall(null, requestId, "contacts.search", MapsKt.mapOf(TuplesKt.to(SearchIntents.EXTRA_QUERY, query)), 1, null);
            case 12:
                Slot slot19 = intent.getSlots().get(SearchIntents.EXTRA_QUERY);
                Object value19 = slot19 != null ? slot19.getValue() : null;
                if ((value19 instanceof String ? (String) value19 : null) == null) {
                    return null;
                }
                return new ToolCall(null, requestId, "files.search", slotsToArgs(intent), 1, null);
            case 13:
                Slot slot20 = intent.getSlots().get(SearchIntents.EXTRA_QUERY);
                Object value20 = slot20 != null ? slot20.getValue() : null;
                if ((value20 instanceof String ? (String) value20 : null) == null) {
                    return null;
                }
                return new ToolCall(null, requestId, "ui.click", slotsToArgs(intent), 1, null);
            case 14:
                Slot slot21 = intent.getSlots().get(VoiceService.EXTRA_TEXT);
                Object value21 = slot21 != null ? slot21.getValue() : null;
                if ((value21 instanceof String ? (String) value21 : null) == null) {
                    return null;
                }
                return new ToolCall(null, requestId, "ui.type", slotsToArgs(intent), 1, null);
            case 15:
                return new ToolCall(null, requestId, "ui.swipe", slotsToArgs(intent), 1, null);
            case 16:
                Slot slot22 = intent.getSlots().get("action");
                Object value22 = slot22 != null ? slot22.getValue() : null;
                String action3 = value22 instanceof String ? (String) value22 : null;
                if (action3 == null) {
                    return null;
                }
                return new ToolCall(null, requestId, "ui.global", MapsKt.mapOf(TuplesKt.to("action", action3)), 1, null);
            case 17:
                return new ToolCall(null, requestId, "ui.read", MapsKt.emptyMap(), 1, null);
            case 18:
                return new ToolCall(null, requestId, "ui.wait", slotsToArgs(intent), 1, null);
            case 19:
                Map<String, Object> mapSlotsToArgs2 = slotsToArgs(intent);
                boolean reply = Intrinsics.areEqual(mapSlotsToArgs2.get("action"), "reply");
                return new ToolCall(null, requestId, reply ? NotificationReplyTool.ID : "notifications.list", reply ? MapsKt.minus(mapSlotsToArgs2, "action") : mapSlotsToArgs2, 1, null);
            case 20:
                Slot slot23 = intent.getSlots().get("action");
                Object value23 = slot23 != null ? slot23.getValue() : null;
                if ((value23 instanceof String ? (String) value23 : null) == null) {
                    return null;
                }
                return new ToolCall(null, requestId, "clipboard", slotsToArgs(intent), 1, null);
            case 21:
                return new ToolCall(null, requestId, "screenshot", MapsKt.emptyMap(), 1, null);
            case 22:
                Slot slot24 = intent.getSlots().get("city");
                Object value24 = slot24 != null ? slot24.getValue() : null;
                String city = value24 instanceof String ? (String) value24 : null;
                Slot slot25 = intent.getSlots().get("day");
                Object value25 = slot25 != null ? slot25.getValue() : null;
                Number number = value25 instanceof Number ? (Number) value25 : null;
                if (number != null) {
                    value = Integer.valueOf(number.intValue());
                } else {
                    Slot slot26 = intent.getSlots().get("day");
                    Object value26 = slot26 != null ? slot26.getValue() : null;
                    String str5 = value26 instanceof String ? (String) value26 : null;
                    if (str5 != null) {
                        value = StringsKt.toIntOrNull(str5);
                    }
                }
                Map $this$build_u24lambda_u244 = MapsKt.createMapBuilder();
                if (city != null) {
                    $this$build_u24lambda_u244.put("city", city);
                }
                if (value != null) {
                    $this$build_u24lambda_u244.put("day", value);
                }
                if (intent.getSlots().containsKey("week")) {
                    $this$build_u24lambda_u244.put("week", true);
                }
                Unit unit5 = Unit.INSTANCE;
                return new ToolCall(null, requestId, "weather", MapsKt.build($this$build_u24lambda_u244), 1, null);
            case 23:
                return new ToolCall(null, requestId, "news", MapsKt.emptyMap(), 1, null);
            case 24:
                return new ToolCall(null, requestId, "rates", slotsToArgs(intent), 1, null);
            case 25:
                return new ToolCall(null, requestId, "location", MapsKt.emptyMap(), 1, null);
            case 26:
                return new ToolCall(null, requestId, "phone.control", slotsToArgs(intent), 1, null);
            case 27:
                return new ToolCall(null, requestId, "announce.settings", slotsToArgs(intent), 1, null);
            case 28:
                return new ToolCall(null, requestId, "notes", slotsToArgs(intent), 1, null);
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                return new ToolCall(null, requestId, "places", slotsToArgs(intent), 1, null);
            case 30:
                return new ToolCall(null, requestId, "routine.custom", slotsToArgs(intent), 1, null);
            case 31:
                return new ToolCall(null, requestId, "app.action", slotsToArgs(intent), 1, null);
            case 32:
                return new ToolCall(null, requestId, "agent.run", slotsToArgs(intent), 1, null);
            case 33:
                return new ToolCall(null, requestId, "web.answer", slotsToArgs(intent), 1, null);
            case 34:
                return new ToolCall(null, requestId, "memory.forget", slotsToArgs(intent), 1, null);
            case 35:
                return new ToolCall(null, requestId, "briefing", MapsKt.emptyMap(), 1, null);
            case 36:
                return new ToolCall(null, requestId, "navigate", slotsToArgs(intent), 1, null);
            case 37:
                return new ToolCall(null, requestId, "web.search", slotsToArgs(intent), 1, null);
            case 38:
                return new ToolCall(null, requestId, "camera", slotsToArgs(intent), 1, null);
            case 39:
                return new ToolCall(null, requestId, NotificationCompat.CATEGORY_REMINDER, slotsToArgs(intent), 1, null);
            case 40:
                return new ToolCall(null, requestId, "system.status", MapsKt.emptyMap(), 1, null);
            case 41:
                return new ToolCall(null, requestId, "music", slotsToArgs(intent), 1, null);
            case 42:
                Slot slot27 = intent.getSlots().get(SearchIntents.EXTRA_QUERY);
                Object value27 = slot27 != null ? slot27.getValue() : null;
                String query2 = value27 instanceof String ? (String) value27 : null;
                if (query2 == null) {
                    return null;
                }
                return new ToolCall(null, requestId, "knowledge", MapsKt.mapOf(TuplesKt.to(SearchIntents.EXTRA_QUERY, query2)), 1, null);
            case 43:
            case 44:
                return null;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final Map<String, Object> slotsToArgs(Intent intent) {
        Map<String, Slot> slots = intent.getSlots();
        Map destination$iv$iv = new LinkedHashMap(MapsKt.mapCapacity(slots.size()));
        Iterable $this$associateByTo$iv$iv$iv = slots.entrySet();
        for (Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
            Map.Entry it$iv$iv = (Map.Entry) element$iv$iv$iv;
            Map.Entry it = (Map.Entry) element$iv$iv$iv;
            destination$iv$iv.put(it$iv$iv.getKey(), ((Slot) it.getValue()).getValue());
        }
        return destination$iv$iv;
    }
}
