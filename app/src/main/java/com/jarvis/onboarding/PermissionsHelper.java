package com.jarvis.onboarding;

import android.app.AppOpsManager;
import android.app.role.RoleManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.os.Process;
import android.provider.Settings;
import androidx.core.content.ContextCompat;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: PermissionsHelper.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000b\u001a\u0004\u0018\u00010\nJ\u000e\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0007J\u001a\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0013J\u000e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010 \u001a\u00020\nJ\u0006\u0010!\u001a\u00020\nJ\u0006\u0010\"\u001a\u00020\nJ\u0006\u0010#\u001a\u00020\nJ\u0016\u0010$\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\n¨\u0006&"}, d2 = {"Lcom/jarvis/onboarding/PermissionsHelper;", "", "<init>", "()V", "isAudioRecordGranted", "", "context", "Landroid/content/Context;", "isPostNotificationsGranted", "audioRecordPermission", "", "postNotificationsPermission", "canDrawOverlays", "openOverlaySettings", "", "isIgnoringBatteryOptimizations", "openBatteryOptimizationSettings", "isAccessibilityServiceEnabled", "serviceClass", "Ljava/lang/Class;", "openAccessibilitySettings", "openAutoStartSettings", "openAppDetailSettings", "isNotificationListenerEnabled", "openNotificationListenerSettings", "isUsageAccessGranted", "openUsageAccessSettings", "isAssistantRoleHeld", "isAssistantRoleAvailable", "openAssistantPickerSettings", "canWriteSettings", "openWriteSettings", "contactsPermission", "callPermission", "smsPermission", "calendarPermission", "isPermissionGranted", "permission", "permissions-onboarding_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PermissionsHelper {
    public static final PermissionsHelper INSTANCE = new PermissionsHelper();

    private PermissionsHelper() {
    }

    public final boolean isAudioRecordGranted(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return ContextCompat.checkSelfPermission(context, "android.permission.RECORD_AUDIO") == 0;
    }

    public final boolean isPostNotificationsGranted(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return Build.VERSION.SDK_INT < 33 || ContextCompat.checkSelfPermission(context, "android.permission.POST_NOTIFICATIONS") == 0;
    }

    public final String audioRecordPermission() {
        return "android.permission.RECORD_AUDIO";
    }

    public final String postNotificationsPermission() {
        if (Build.VERSION.SDK_INT >= 33) {
            return "android.permission.POST_NOTIFICATIONS";
        }
        return null;
    }

    public final boolean canDrawOverlays(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return Settings.canDrawOverlays(context);
    }

    public final void openOverlaySettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + context.getPackageName()));
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    public final boolean isIgnoringBatteryOptimizations(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("power");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.os.PowerManager");
        PowerManager pm = (PowerManager) systemService;
        return pm.isIgnoringBatteryOptimizations(context.getPackageName());
    }

    public final void openBatteryOptimizationSettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent intent = new Intent("android.settings.IGNORE_BATTERY_OPTIMIZATION_SETTINGS");
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    public final boolean isAccessibilityServiceEnabled(Context context, Class<?> serviceClass) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(serviceClass, "serviceClass");
        String serviceName = context.getPackageName() + "/" + serviceClass.getName();
        String enabledServices = Settings.Secure.getString(context.getContentResolver(), "enabled_accessibility_services");
        if (enabledServices == null) {
            return false;
        }
        Iterable $this$any$iv = StringsKt.split$default((CharSequence) enabledServices, new String[]{":"}, false, 0, 6, (Object) null);
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            String it = (String) element$iv;
            if (StringsKt.equals(it, serviceName, true)) {
                return true;
            }
        }
        return false;
    }

    public final void openAccessibilitySettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent intent = new Intent("android.settings.ACCESSIBILITY_SETTINGS");
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    public final void openAutoStartSettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Intent intent = new Intent();
            intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity");
            intent.addFlags(268435456);
            context.startActivity(intent);
        } catch (Exception e) {
            openAppDetailSettings(context);
        }
    }

    public final void openAppDetailSettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    public final boolean isNotificationListenerEnabled(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String flat = Settings.Secure.getString(context.getContentResolver(), "enabled_notification_listeners");
        if (flat == null) {
            return false;
        }
        Iterable $this$any$iv = StringsKt.split$default((CharSequence) flat, new String[]{":"}, false, 0, 6, (Object) null);
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            String it = (String) element$iv;
            String packageName = context.getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "getPackageName(...)");
            if (StringsKt.contains$default((CharSequence) it, (CharSequence) packageName, false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    public final void openNotificationListenerSettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    public final boolean isUsageAccessGranted(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("appops");
        AppOpsManager appOps = systemService instanceof AppOpsManager ? (AppOpsManager) systemService : null;
        if (appOps == null) {
            return false;
        }
        int mode = appOps.unsafeCheckOpNoThrow("android:get_usage_stats", Process.myUid(), context.getPackageName());
        return mode == 0;
    }

    public final void openUsageAccessSettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent intent = new Intent("android.settings.USAGE_ACCESS_SETTINGS");
        intent.addFlags(268435456);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            openAppDetailSettings(context);
        }
    }

    public final boolean isAssistantRoleHeld(Context context) {
        Object objM392constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        RoleManager rm = (RoleManager) context.getSystemService(RoleManager.class);
        if (rm == null) {
            return false;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            PermissionsHelper permissionsHelper = this;
            objM392constructorimpl = Result.m392constructorimpl(Boolean.valueOf(rm.isRoleHeld("android.app.role.ASSISTANT")));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = false;
        }
        return ((Boolean) objM392constructorimpl).booleanValue();
    }

    public final boolean isAssistantRoleAvailable(Context context) {
        Object objM392constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        RoleManager rm = (RoleManager) context.getSystemService(RoleManager.class);
        if (rm == null) {
            return false;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            PermissionsHelper permissionsHelper = this;
            objM392constructorimpl = Result.m392constructorimpl(Boolean.valueOf(rm.isRoleAvailable("android.app.role.ASSISTANT")));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = false;
        }
        return ((Boolean) objM392constructorimpl).booleanValue();
    }

    public final void openAssistantPickerSettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        List<Intent> intents = CollectionsKt.listOf((Object[]) new Intent[]{new Intent("android.settings.VOICE_INPUT_SETTINGS"), new Intent("android.settings.VOICE_INPUT_SETTINGS"), new Intent("android.settings.MANAGE_DEFAULT_APPS_SETTINGS")});
        for (Intent intent : intents) {
            intent.addFlags(268435456);
            try {
                context.startActivity(intent);
                return;
            } catch (Exception e) {
            }
        }
        openAppDetailSettings(context);
    }

    public final boolean canWriteSettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return Settings.System.canWrite(context);
    }

    public final void openWriteSettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent intent = new Intent("android.settings.action.MANAGE_WRITE_SETTINGS", Uri.parse("package:" + context.getPackageName()));
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    public final String contactsPermission() {
        return "android.permission.READ_CONTACTS";
    }

    public final String callPermission() {
        return "android.permission.CALL_PHONE";
    }

    public final String smsPermission() {
        return "android.permission.SEND_SMS";
    }

    public final String calendarPermission() {
        return "android.permission.WRITE_CALENDAR";
    }

    public final boolean isPermissionGranted(Context context, String permission) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(permission, "permission");
        return ContextCompat.checkSelfPermission(context, permission) == 0;
    }
}
