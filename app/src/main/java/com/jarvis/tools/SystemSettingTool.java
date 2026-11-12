package com.jarvis.tools;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.provider.Settings;
import com.jarvis.app.proactive.PhoneStateReader;
import com.jarvis.core.common.Tool;
import com.jarvis.core.common.model.OnlineRequirement;
import com.jarvis.core.common.model.ParameterType;
import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.common.model.ToolParameter;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import com.jarvis.core.routines.EventMatcher;
import com.jarvis.screencontrol.ActionCoordinator;
import com.jarvis.screencontrol.ScreenController;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.AbstractCircuitBreaker;

/* JADX INFO: compiled from: SystemSettingTool.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\u0018\u0000  2\u00020\u0001:\u0001 B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J \u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0012H\u0002J\u0018\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0012H\u0002J\u0010\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J(\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u0012H\u0002J\u0018\u0010\u001a\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u0012H\u0002J\u0018\u0010\u001c\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u0012H\u0002J\u0018\u0010\u001d\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0012H\u0002J\u0018\u0010\u001e\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0012H\u0002J\u0018\u0010\u001f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0012H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006!"}, d2 = {"Lcom/jarvis/tools/SystemSettingTool;", "Lcom/jarvis/core/common/Tool;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "coordinator", "Lcom/jarvis/screencontrol/ActionCoordinator;", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "ringer", "requestId", "", "name", "value", "doNotDisturb", "needDndAccess", "systemSwitch", "key", "label", "openPanel", "action", "openSection", "handleWifi", "handleBluetooth", "handleAirplane", "Companion", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SystemSettingTool implements Tool {
    private static final Companion Companion = new Companion(null);
    private static final Map<String, String> PANELS = MapsKt.mapOf(TuplesKt.to("dark_mode", "android.settings.DISPLAY_SETTINGS"), TuplesKt.to("battery_saver", "android.settings.BATTERY_SAVER_SETTINGS"), TuplesKt.to("hotspot", "android.settings.WIRELESS_SETTINGS"), TuplesKt.to("mobile_data", "android.settings.panel.action.INTERNET_CONNECTIVITY"));
    private static final Map<String, String> SECTIONS = MapsKt.mapOf(TuplesKt.to("battery", "android.intent.action.POWER_USAGE_SUMMARY"), TuplesKt.to("display", "android.settings.DISPLAY_SETTINGS"), TuplesKt.to("sound", "android.settings.SOUND_SETTINGS"), TuplesKt.to("storage", "android.settings.INTERNAL_STORAGE_SETTINGS"), TuplesKt.to("apps", "android.settings.APPLICATION_SETTINGS"), TuplesKt.to("location", "android.settings.LOCATION_SOURCE_SETTINGS"), TuplesKt.to("notifications", "android.settings.NOTIFICATION_ASSISTANT_SETTINGS"), TuplesKt.to("security", "android.settings.SECURITY_SETTINGS"));
    private final Context context;
    private final ActionCoordinator coordinator;
    private final ToolDefinition definition;

    public SystemSettingTool(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.coordinator = new ActionCoordinator(this.context);
        this.definition = new ToolDefinition("system.setting", "Системная настройка", "Включает или выключает Wi‑Fi, Bluetooth или режим полёта; либо открывает соответствующий экран настроек", CollectionsKt.listOf((Object[]) new ToolParameter[]{new ToolParameter("name", "wifi, bluetooth или airplane", ParameterType.ENUM, true, CollectionsKt.listOf((Object[]) new String[]{EventMatcher.WIFI, "bluetooth", "airplane", "silent", "vibrate", PhoneStateReader.FAKE_DND, "auto_brightness", "auto_rotate", "dark_mode", "battery_saver", "hotspot", "mobile_data"})), new ToolParameter("value", "on, off или open", ParameterType.ENUM, false, CollectionsKt.listOf((Object[]) new String[]{"on", "off", AbstractCircuitBreaker.PROPERTY_NAME}))}), RiskLevel.LOW, null, OnlineRequirement.LOCAL_ONLY, null, 160, null);
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0045  */
    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) {
        String name;
        String lowerCase;
        String string;
        String string2;
        Intrinsics.checkNotNullParameter(call, "call");
        Object obj = call.getArguments().get("name");
        if (obj == null || (string2 = obj.toString()) == null) {
            name = null;
        } else {
            name = string2.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(name, "toLowerCase(...)");
        }
        if (name == null) {
            name = "";
        }
        Object obj2 = call.getArguments().get("value");
        if (obj2 == null || (string = obj2.toString()) == null) {
            lowerCase = AbstractCircuitBreaker.PROPERTY_NAME;
        } else {
            lowerCase = string.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (lowerCase == null) {
                lowerCase = AbstractCircuitBreaker.PROPERTY_NAME;
            }
        }
        String value = lowerCase;
        if (Intrinsics.areEqual(name, EventMatcher.WIFI)) {
            return handleWifi(call.getRequestId(), value);
        }
        if (Intrinsics.areEqual(name, "bluetooth")) {
            return handleBluetooth(call.getRequestId(), value);
        }
        if (Intrinsics.areEqual(name, "airplane") || Intrinsics.areEqual(name, "flight") || Intrinsics.areEqual(name, "aero")) {
            return handleAirplane(call.getRequestId(), value);
        }
        if (Intrinsics.areEqual(name, "silent") || Intrinsics.areEqual(name, "vibrate")) {
            return ringer(call.getRequestId(), name, value);
        }
        if (Intrinsics.areEqual(name, PhoneStateReader.FAKE_DND)) {
            return doNotDisturb(call.getRequestId(), value);
        }
        if (Intrinsics.areEqual(name, "auto_brightness")) {
            return systemSwitch(call.getRequestId(), "screen_brightness_mode", value, "Автояркость");
        }
        if (Intrinsics.areEqual(name, "auto_rotate")) {
            return systemSwitch(call.getRequestId(), "accelerometer_rotation", value, "Автоповорот");
        }
        if (PANELS.containsKey(name)) {
            return openPanel(call.getRequestId(), (String) MapsKt.getValue(PANELS, name));
        }
        return SECTIONS.containsKey(name) ? openSection(call.getRequestId(), (String) MapsKt.getValue(SECTIONS, name)) : new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "Неизвестная настройка «" + name + "». Доступны: wifi, bluetooth, airplane.", null, 0L, 48, null);
    }

    private final ToolResult ringer(String requestId, String name, String value) {
        int i;
        String reply;
        AudioManager audio = (AudioManager) this.context.getSystemService(AudioManager.class);
        if (Intrinsics.areEqual(value, "off")) {
            i = 2;
        } else {
            i = Intrinsics.areEqual(name, "vibrate") ? 1 : 0;
        }
        int wanted = i;
        try {
            audio.setRingerMode(wanted);
            switch (wanted) {
                case 1:
                    reply = "Только вибрация, сэр.";
                    break;
                case 2:
                    reply = "Обычный режим, сэр.";
                    break;
                default:
                    reply = "Беззвучный режим, сэр.";
                    break;
            }
            return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.SUCCESS, reply, null, 0L, 48, null);
        } catch (SecurityException e) {
            return needDndAccess(requestId);
        }
    }

    private final ToolResult doNotDisturb(String requestId, String value) {
        NotificationManager manager = (NotificationManager) this.context.getSystemService(NotificationManager.class);
        if (!manager.isNotificationPolicyAccessGranted()) {
            return needDndAccess(requestId);
        }
        manager.setInterruptionFilter(Intrinsics.areEqual(value, "off") ? 1 : 2);
        String reply = Intrinsics.areEqual(value, "off") ? "Режим «Не беспокоить» выключен, сэр." : "Режим «Не беспокоить» включён, сэр.";
        return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.SUCCESS, reply, null, 0L, 48, null);
    }

    private final ToolResult needDndAccess(String requestId) {
        try {
            Result.Companion companion = Result.INSTANCE;
            SystemSettingTool $this$needDndAccess_u24lambda_u240 = this;
            $this$needDndAccess_u24lambda_u240.context.startActivity(new Intent("android.settings.NOTIFICATION_POLICY_ACCESS_SETTINGS").addFlags(268435456));
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.DENIED, "Нужен доступ к режиму «Не беспокоить», сэр. Открыл настройки.", null, 0L, 48, null);
    }

    private final ToolResult systemSwitch(String requestId, String key, String value, String label) {
        if (!Settings.System.canWrite(this.context)) {
            return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.DENIED, "Нет разрешения менять настройки, сэр.", null, 0L, 48, null);
        }
        boolean on = !Intrinsics.areEqual(value, "off");
        Settings.System.putInt(this.context.getContentResolver(), key, on ? 1 : 0);
        return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.SUCCESS, label + StringUtils.SPACE + (on ? "включён" : "выключен") + ", сэр.", null, 0L, 48, null);
    }

    private final ToolResult openPanel(String requestId, String action) {
        try {
            this.context.startActivity(new Intent(action).addFlags(268435456));
            return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.SUCCESS, "Открыл настройку, сэр. Переключатель за вами.", null, 0L, 48, null);
        } catch (Throwable th) {
            return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.FAILED, "Не удалось открыть настройку, сэр.", null, 0L, 48, null);
        }
    }

    private final ToolResult openSection(String requestId, String action) {
        try {
            this.context.startActivity(new Intent(action).addFlags(268435456));
            return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.SUCCESS, "Открыл настройки.", null, 0L, 48, null);
        } catch (Throwable t) {
            return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.FAILED, t.getMessage(), null, 0L, 48, null);
        }
    }

    private final ToolResult handleWifi(String requestId, String value) throws InterruptedException {
        String message;
        Intrinsics.areEqual(value, AbstractCircuitBreaker.PROPERTY_NAME);
        ActionCoordinator.ActionResult r = this.coordinator.openWifiSettings();
        if (Intrinsics.areEqual(value, "on") || Intrinsics.areEqual(value, "off")) {
            Thread.sleep(800L);
            if (ScreenController.INSTANCE.isAvailable()) {
                this.coordinator.findAndClick("Wi‑Fi", (14 & 2) != 0 ? false : false, (14 & 4) != 0 ? 5 : 0, (14 & 8) != 0 ? 4000L : 0L);
                Thread.sleep(300L);
                Iterable $this$forEach$iv = CollectionsKt.listOf((Object[]) new String[]{"Использовать Wi‑Fi", "Wi-Fi", "Вкл", "On"});
                for (Object element$iv : $this$forEach$iv) {
                    String it = (String) element$iv;
                    this.coordinator.findAndClick(it, (14 & 2) != 0 ? false : false, (14 & 4) != 0 ? 5 : 0, (14 & 8) != 0 ? 4000L : 0L);
                }
            }
        }
        String id = getDefinition().getId();
        ToolResultStatus toolResultStatus = r.getSuccess() ? ToolResultStatus.SUCCESS : ToolResultStatus.FAILED;
        if (Intrinsics.areEqual(value, "on")) {
            message = "Открыл настройки Wi-Fi, сэр. Если тумблер не сработал, включите вручную.";
        } else if (Intrinsics.areEqual(value, "off")) {
            message = "Открыл настройки Wi-Fi, сэр. Если тумблер не сработал, выключите вручную.";
        } else {
            message = r.getSuccess() ? "Открыл настройки Wi-Fi, сэр." : r.getMessage();
        }
        return new ToolResult(requestId, id, toolResultStatus, message, null, 0L, 48, null);
    }

    private final ToolResult handleBluetooth(String requestId, String value) {
        Intent intent = new Intent("android.settings.BLUETOOTH_SETTINGS").addFlags(268435456);
        Intrinsics.checkNotNullExpressionValue(intent, "addFlags(...)");
        try {
            this.context.startActivity(intent);
            if ((Intrinsics.areEqual(value, "on") || Intrinsics.areEqual(value, "off")) && ScreenController.INSTANCE.isAvailable()) {
                Thread.sleep(800L);
                this.coordinator.findAndClick("Bluetooth", (14 & 2) != 0 ? false : false, (14 & 4) != 0 ? 5 : 0, (14 & 8) != 0 ? 4000L : 0L);
            }
            return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.SUCCESS, "Открыл настройки Bluetooth.", null, 0L, 48, null);
        } catch (Throwable t) {
            return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.FAILED, t.getMessage(), null, 0L, 48, null);
        }
    }

    private final ToolResult handleAirplane(String requestId, String value) {
        Intent intent = new Intent("android.settings.AIRPLANE_MODE_SETTINGS").addFlags(268435456);
        Intrinsics.checkNotNullExpressionValue(intent, "addFlags(...)");
        try {
            this.context.startActivity(intent);
            return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.SUCCESS, "Открыл настройки режима полёта" + ((Intrinsics.areEqual(value, "on") || Intrinsics.areEqual(value, "off")) ? " — переключи тумблер." : "."), null, 0L, 48, null);
        } catch (Throwable t) {
            return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.FAILED, t.getMessage(), null, 0L, 48, null);
        }
    }

    /* JADX INFO: compiled from: SystemSettingTool.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/jarvis/tools/SystemSettingTool$Companion;", "", "<init>", "()V", "PANELS", "", "", "SECTIONS", "getSECTIONS", "()Ljava/util/Map;", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Map<String, String> getSECTIONS() {
            return SystemSettingTool.SECTIONS;
        }
    }
}
