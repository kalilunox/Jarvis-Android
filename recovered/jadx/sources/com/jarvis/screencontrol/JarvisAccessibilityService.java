package com.jarvis.screencontrol;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import androidx.core.app.NotificationCompat;
import com.jarvis.core.companion.ObservationSource;
import com.jarvis.core.companion.ObservationType;
import com.jarvis.core.companion.PhoneContextBus;
import com.jarvis.core.companion.PhoneObservation;
import com.jarvis.core.companion.Sensitivity;
import com.jarvis.core.privacy.SensitivityClassifier;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: JarvisAccessibilityService.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 &2\u00020\u0001:\u0001&B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\rH\u0014J\u0012\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0013\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0015\u001a\u00020\u0005H\u0002J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0018\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u001a\u0010\u001d\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00052\b\u0010\u001e\u001a\u0004\u0018\u00010\u0005H\u0002J\u001a\u0010\u001f\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00052\b\u0010 \u001a\u0004\u0018\u00010\u0005H\u0002J\b\u0010!\u001a\u00020\rH\u0016J\u0012\u0010\"\u001a\u00020\u00172\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\b\u0010%\u001a\u00020\rH\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/jarvis/screencontrol/JarvisAccessibilityService;", "Landroid/accessibilityservice/AccessibilityService;", "<init>", "()V", "lastFgPackage", "", "lastFgAtElapsed", "", "lastWindowKey", "lastWindowAtElapsed", "lastContentKey", "lastContentAtElapsed", "onServiceConnected", "", "onAccessibilityEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/accessibility/AccessibilityEvent;", "onWindowStateChanged", "onWindowsChanged", "onWindowContentChanged", "lightweightContentHint", "pkg", "hasPasswordField", "", "node", "Landroid/view/accessibility/AccessibilityNodeInfo;", "depth", "", "firstText", "publishAppForeground", "detail", "publishWindowChanged", "className", "onInterrupt", "onUnbind", "intent", "Landroid/content/Intent;", "onDestroy", "Companion", "screen-control_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JarvisAccessibilityService extends AccessibilityService {
    private static final Set<String> IGNORED_PACKAGES = SetsKt.setOf((Object[]) new String[]{"com.android.systemui", "com.miui.home", "com.mi.android.globallauncher", "com.google.android.permissioncontroller", "com.google.android.packageinstaller", "com.android.packageinstaller", "com.lbe.security.miui"});
    private static final long LOCAL_CONTENT_DEBOUNCE_MS = 4000;
    private static final long LOCAL_FG_DEBOUNCE_MS = 700;
    private static final long LOCAL_WINDOW_DEBOUNCE_MS = 1200;
    private static final String TAG = "Jarvis.A11y";
    private volatile long lastContentAtElapsed;
    private volatile String lastContentKey;
    private volatile long lastFgAtElapsed;
    private volatile String lastFgPackage;
    private volatile long lastWindowAtElapsed;
    private volatile String lastWindowKey;

    @Override // android.accessibilityservice.AccessibilityService
    protected void onServiceConnected() {
        CharSequence packageName;
        super.onServiceConnected();
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.eventTypes = 4196384;
        info.feedbackType = 16;
        info.flags = 82;
        info.notificationTimeout = 100L;
        String string = null;
        info.packageNames = null;
        if (Build.VERSION.SDK_INT >= 31) {
            try {
                Result.Companion companion = Result.INSTANCE;
                JarvisAccessibilityService jarvisAccessibilityService = this;
                Result.m392constructorimpl(AccessibilityServiceInfo.class.getMethod("setAccessibilityTool", Boolean.TYPE).invoke(info, true));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m392constructorimpl(ResultKt.createFailure(th));
            }
        }
        setServiceInfo(info);
        ScreenController.INSTANCE.bind(this);
        List<AccessibilityWindowInfo> windows = getWindows();
        int winCount = windows != null ? windows.size() : -1;
        AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
        if (rootInActiveWindow != null && (packageName = rootInActiveWindow.getPackageName()) != null) {
            string = packageName.toString();
        }
        String active = string;
        Log.i(TAG, "Accessibility service connected windows=" + winCount + " active=" + active);
        String str = active;
        if (!(str == null || StringsKt.isBlank(str))) {
            publishAppForeground(active, "A11y connected");
        }
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event == null) {
        }
        switch (event.getEventType()) {
            case 32:
                onWindowStateChanged(event);
                break;
            case 2048:
                onWindowContentChanged(event);
                break;
            case 4194304:
                onWindowsChanged(event);
                break;
        }
    }

    private final void onWindowStateChanged(AccessibilityEvent event) {
        String pkg;
        String string;
        CharSequence packageName = event.getPackageName();
        if (packageName == null || (pkg = packageName.toString()) == null) {
            return;
        }
        String className = null;
        if (StringsKt.isBlank(pkg)) {
            pkg = null;
        }
        if (pkg == null || IGNORED_PACKAGES.contains(pkg)) {
            return;
        }
        long now = SystemClock.elapsedRealtime();
        CharSequence className2 = event.getClassName();
        if (className2 != null && (string = className2.toString()) != null) {
            className = StringsKt.substringAfterLast$default(string, '.', (String) null, 2, (Object) null);
        }
        if (!Intrinsics.areEqual(pkg, this.lastFgPackage) || now - this.lastFgAtElapsed >= 700) {
            this.lastFgPackage = pkg;
            this.lastFgAtElapsed = now;
            publishAppForeground(pkg, className);
        }
        String windowKey = pkg + "|" + (className == null ? "" : className);
        if (!Intrinsics.areEqual(windowKey, this.lastWindowKey) || now - this.lastWindowAtElapsed >= LOCAL_WINDOW_DEBOUNCE_MS) {
            this.lastWindowKey = windowKey;
            this.lastWindowAtElapsed = now;
            publishWindowChanged(pkg, className);
        }
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0022  */
    /* JADX WARN: Code duplicated, block: B:21:0x003c A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:22:0x003d  */
    private final void onWindowsChanged(AccessibilityEvent event) {
        CharSequence packageName;
        String pkg;
        String it;
        CharSequence packageName2;
        AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
        String str = null;
        if (rootInActiveWindow == null || (packageName2 = rootInActiveWindow.getPackageName()) == null || (pkg = packageName2.toString()) == null) {
            packageName = event.getPackageName();
            if (packageName != null && (it = packageName.toString()) != null && !StringsKt.isBlank(it)) {
                str = it;
            }
            if (str == null) {
                return;
            } else {
                pkg = str;
            }
        } else {
            if (StringsKt.isBlank(pkg)) {
                pkg = null;
            }
            if (pkg == null) {
                packageName = event.getPackageName();
                if (packageName != null) {
                    str = it;
                }
                if (str == null) {
                    return;
                } else {
                    pkg = str;
                }
            }
        }
        if (IGNORED_PACKAGES.contains(pkg)) {
            return;
        }
        long now = SystemClock.elapsedRealtime();
        if (!Intrinsics.areEqual(pkg, this.lastFgPackage) || now - this.lastFgAtElapsed >= 700) {
            this.lastFgPackage = pkg;
            this.lastFgAtElapsed = now;
            publishAppForeground(pkg, "windows_changed");
        }
    }

    private final void onWindowContentChanged(AccessibilityEvent event) {
        String pkg;
        String it;
        String str;
        CharSequence packageName = event.getPackageName();
        if (packageName == null || (pkg = packageName.toString()) == null) {
            return;
        }
        if (StringsKt.isBlank(pkg)) {
            pkg = null;
        }
        if (pkg == null || IGNORED_PACKAGES.contains(pkg)) {
            return;
        }
        long now = SystemClock.elapsedRealtime();
        if (now - this.lastContentAtElapsed < LOCAL_CONTENT_DEBOUNCE_MS) {
            return;
        }
        String hint = lightweightContentHint(pkg);
        String key = pkg + "|" + hint;
        if (Intrinsics.areEqual(key, this.lastContentKey)) {
            return;
        }
        this.lastContentKey = key;
        this.lastContentAtElapsed = now;
        boolean sensitivePackage = SensitivityClassifier.INSTANCE.isSensitivePackage(pkg);
        PhoneContextBus phoneContextBus = PhoneContextBus.INSTANCE;
        ObservationSource observationSource = ObservationSource.ACCESSIBILITY;
        ObservationType observationType = ObservationType.WINDOW_CHANGED;
        if (sensitivePackage) {
            str = "Контент: " + pkg + " · скрыто";
        } else {
            if (hint == null || (it = " · " + hint) == null) {
                it = "";
            }
            str = "Контент: " + pkg + it;
        }
        Map $this$onWindowContentChanged_u24lambda_u242 = MapsKt.createMapBuilder();
        $this$onWindowContentChanged_u24lambda_u242.put("package", pkg);
        $this$onWindowContentChanged_u24lambda_u242.put("kind", "content");
        String str2 = hint;
        if (!(str2 == null || StringsKt.isBlank(str2))) {
            $this$onWindowContentChanged_u24lambda_u242.put("hint", StringsKt.take(hint, 48));
        }
        Unit unit = Unit.INSTANCE;
        phoneContextBus.publish(new PhoneObservation(null, 0L, observationSource, observationType, pkg, hint, str, MapsKt.build($this$onWindowContentChanged_u24lambda_u242), sensitivePackage ? Sensitivity.SECRET : Sensitivity.NORMAL, 0.55f, 3, null));
        Log.d(TAG, "WINDOW_CONTENT " + pkg + " hint=" + hint);
    }

    private final String lightweightContentHint(String pkg) {
        AccessibilityNodeInfo root;
        Object objM392constructorimpl;
        String it;
        String string;
        String string2;
        if (SensitivityClassifier.INSTANCE.isSensitivePackage(pkg) || (root = getRootInActiveWindow()) == null) {
            return null;
        }
        CharSequence packageName = root.getPackageName();
        if (!Intrinsics.areEqual(packageName != null ? packageName.toString() : null, pkg)) {
            return null;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            JarvisAccessibilityService $this$lightweightContentHint_u24lambda_u240 = this;
            if ($this$lightweightContentHint_u24lambda_u240.hasPasswordField(root, 0)) {
                it = null;
            } else {
                CharSequence contentDescription = root.getContentDescription();
                String string3 = (contentDescription == null || (string2 = contentDescription.toString()) == null) ? null : StringsKt.trim((CharSequence) string2).toString();
                String str = "";
                if (string3 == null) {
                    string3 = "";
                }
                String str2 = string3;
                if (StringsKt.isBlank(str2)) {
                    CharSequence text = root.getText();
                    String string4 = (text == null || (string = text.toString()) == null) ? null : StringsKt.trim((CharSequence) string).toString();
                    if (string4 != null) {
                        str = string4;
                    }
                    str2 = str;
                }
                String strFirstText = str2;
                if (StringsKt.isBlank(strFirstText)) {
                    strFirstText = $this$lightweightContentHint_u24lambda_u240.firstText(root, 0);
                }
                String title = strFirstText;
                if (SensitivityClassifier.INSTANCE.classify(title, pkg) != Sensitivity.SECRET) {
                    it = StringsKt.take(title, 40);
                    if (StringsKt.isBlank(it)) {
                        it = null;
                    }
                } else {
                    it = null;
                }
            }
            objM392constructorimpl = Result.m392constructorimpl(it);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        Object obj = Result.m398isFailureimpl(objM392constructorimpl) ? null : objM392constructorimpl;
        try {
            Result.Companion companion3 = Result.INSTANCE;
            JarvisAccessibilityService jarvisAccessibilityService = this;
            root.recycle();
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th2));
        }
        return (String) obj;
    }

    private final boolean hasPasswordField(AccessibilityNodeInfo node, int depth) {
        Object objM392constructorimpl;
        if (depth > 3) {
            return false;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            JarvisAccessibilityService jarvisAccessibilityService = this;
            objM392constructorimpl = Result.m392constructorimpl(Boolean.valueOf(node.isPassword()));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = false;
        }
        if (((Boolean) objM392constructorimpl).booleanValue()) {
            return true;
        }
        int iCoerceAtMost = RangesKt.coerceAtMost(node.getChildCount(), 6);
        for (int i = 0; i < iCoerceAtMost; i++) {
            AccessibilityNodeInfo child = node.getChild(i);
            if (child != null) {
                boolean found = hasPasswordField(child, depth + 1);
                try {
                    Result.Companion companion3 = Result.INSTANCE;
                    JarvisAccessibilityService jarvisAccessibilityService2 = this;
                    child.recycle();
                    Result.m392constructorimpl(Unit.INSTANCE);
                } catch (Throwable th2) {
                    Result.Companion companion4 = Result.INSTANCE;
                    Result.m392constructorimpl(ResultKt.createFailure(th2));
                }
                if (found) {
                    return true;
                }
            }
        }
        return false;
    }

    private final String firstText(AccessibilityNodeInfo node, int depth) {
        String string;
        if (depth > 3) {
            return "";
        }
        CharSequence text = node.getText();
        String own = (text == null || (string = text.toString()) == null) ? null : StringsKt.trim((CharSequence) string).toString();
        if (own == null) {
            own = "";
        }
        if (own.length() >= 2) {
            return own;
        }
        int iCoerceAtMost = RangesKt.coerceAtMost(node.getChildCount(), 6);
        for (int i = 0; i < iCoerceAtMost; i++) {
            AccessibilityNodeInfo child = node.getChild(i);
            if (child != null) {
                String t = firstText(child, depth + 1);
                try {
                    Result.Companion companion = Result.INSTANCE;
                    JarvisAccessibilityService jarvisAccessibilityService = this;
                    child.recycle();
                    Result.m392constructorimpl(Unit.INSTANCE);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    Result.m392constructorimpl(ResultKt.createFailure(th));
                }
                if (!StringsKt.isBlank(t)) {
                    return t;
                }
            }
        }
        return "";
    }

    private final void publishAppForeground(String pkg, String detail) {
        PhoneContextBus phoneContextBus = PhoneContextBus.INSTANCE;
        ObservationSource observationSource = ObservationSource.ACCESSIBILITY;
        ObservationType observationType = ObservationType.APP_FOREGROUND;
        String str = "На переднем плане: " + pkg;
        Map $this$publishAppForeground_u24lambda_u240 = MapsKt.createMapBuilder();
        $this$publishAppForeground_u24lambda_u240.put("package", pkg);
        String str2 = detail;
        if (!(str2 == null || StringsKt.isBlank(str2))) {
            $this$publishAppForeground_u24lambda_u240.put("class", detail);
        }
        Unit unit = Unit.INSTANCE;
        phoneContextBus.publish(new PhoneObservation(null, 0L, observationSource, observationType, pkg, detail, str, MapsKt.build($this$publishAppForeground_u24lambda_u240), Sensitivity.NORMAL, 0.9f, 3, null));
        Log.d(TAG, "APP_FOREGROUND " + pkg + " detail=" + detail);
    }

    private final void publishWindowChanged(String pkg, String className) {
        String it;
        PhoneContextBus phoneContextBus = PhoneContextBus.INSTANCE;
        ObservationSource observationSource = ObservationSource.ACCESSIBILITY;
        ObservationType observationType = ObservationType.WINDOW_CHANGED;
        if (className == null || (it = " · " + className) == null) {
            it = "";
        }
        String str = "Окно: " + pkg + it;
        Map $this$publishWindowChanged_u24lambda_u241 = MapsKt.createMapBuilder();
        $this$publishWindowChanged_u24lambda_u241.put("package", pkg);
        $this$publishWindowChanged_u24lambda_u241.put("kind", "window_state");
        String str2 = className;
        if (!(str2 == null || StringsKt.isBlank(str2))) {
            $this$publishWindowChanged_u24lambda_u241.put("class", className);
        }
        Unit unit = Unit.INSTANCE;
        phoneContextBus.publish(new PhoneObservation(null, 0L, observationSource, observationType, pkg, className, str, MapsKt.build($this$publishWindowChanged_u24lambda_u241), Sensitivity.NORMAL, 0.8f, 3, null));
        Log.d(TAG, "WINDOW_CHANGED " + pkg + " class=" + className);
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onInterrupt() {
        Log.w(TAG, "Accessibility service interrupted");
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        ScreenController.INSTANCE.unbind(this);
        Log.i(TAG, "Accessibility service unbound");
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public void onDestroy() {
        ScreenController.INSTANCE.unbind(this);
        super.onDestroy();
    }
}
