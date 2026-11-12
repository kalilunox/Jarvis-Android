package com.jarvis.screencontrol;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.Path;
import android.graphics.Rect;
import android.hardware.HardwareBuffer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.jarvis.app.VoiceService;
import com.jarvis.app.proactive.ProactiveActionReceiver;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ScreenController.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J\u000e\u0010\t\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\u0005J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u0011J\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014J\u0006\u0010\u0015\u001a\u00020\u0007J\u0018\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0002J\b\u0010\u001a\u001a\u0004\u0018\u00010\u000fJ\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u0011J&\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u00182\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0018\u0010 \u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u00182\u0006\u0010!\u001a\u00020\u0011H\u0002J.\u0010\"\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u00182\u0006\u0010!\u001a\u00020\u00112\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J \u0010#\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%2\b\b\u0002\u0010'\u001a\u00020\u000bJ\u0018\u0010(\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u000f2\b\b\u0002\u0010'\u001a\u00020\u000bJ0\u0010)\u001a\u00020\u000b2\u0006\u0010*\u001a\u00020%2\u0006\u0010+\u001a\u00020%2\u0006\u0010,\u001a\u00020%2\u0006\u0010-\u001a\u00020%2\b\b\u0002\u0010.\u001a\u00020/J\u0018\u00100\u001a\u00020\u000b2\u0006\u00101\u001a\u00020\u00142\b\b\u0002\u00102\u001a\u00020%J\u000e\u00103\u001a\u00020\u000b2\u0006\u00101\u001a\u00020\u0014J\u0012\u00104\u001a\u0004\u0018\u00010\u00182\u0006\u00105\u001a\u00020\u0018H\u0002J\u0006\u00106\u001a\u00020\u000bJ0\u00107\u001a\u00020\u000b2\u0006\u0010*\u001a\u00020%2\u0006\u0010+\u001a\u00020%2\u0006\u0010,\u001a\u00020%2\u0006\u0010-\u001a\u00020%2\b\b\u0002\u0010.\u001a\u00020/J\u0010\u00108\u001a\u00020\u000b2\u0006\u00109\u001a\u00020\u000fH\u0002J\u001a\u0010:\u001a\u0004\u0018\u00010\u00182\u0006\u00105\u001a\u00020\u00182\u0006\u00109\u001a\u00020\u000fH\u0002J\u0018\u0010;\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020<2\u0006\u0010=\u001a\u00020>H\u0002J\u000e\u0010?\u001a\u00020\u000b2\u0006\u0010@\u001a\u00020\u0014J\u0016\u0010A\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u000f2\u0006\u0010@\u001a\u00020\u0014J \u0010A\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020<2\u0006\u0010\u001d\u001a\u00020\u00182\u0006\u0010@\u001a\u00020\u0014H\u0002J\u0012\u0010B\u001a\u0004\u0018\u00010\u00182\u0006\u00105\u001a\u00020\u0018H\u0002J\u000e\u0010C\u001a\u00020\u000b2\u0006\u0010D\u001a\u00020\u0014J\u0012\u0010E\u001a\u0004\u0018\u00010F2\b\b\u0002\u0010G\u001a\u00020/R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006H"}, d2 = {"Lcom/jarvis/screencontrol/ScreenController;", "", "<init>", "()V", NotificationCompat.CATEGORY_SERVICE, "Lcom/jarvis/screencontrol/JarvisAccessibilityService;", "bind", "", "svc", "unbind", "isAvailable", "", "requireService", "dumpTree", "", "Lcom/jarvis/screencontrol/UiNode;", "maxNodes", "", "dumpActiveTree", "activePackageName", "", "dismissShadeIfNeeded", "sameRoot", "a", "Landroid/view/accessibility/AccessibilityNodeInfo;", "b", "findFocusedEditable", "visibleNodes", "walkVisible", "node", "out", "", "uiNode", "depth", "walk", "tap", "x", "", "y", "longPress", "tapCenter", "swipe", "x1", "y1", "x2", "y2", "durationMs", "", "swipeDirection", "direction", "distanceFraction", "scrollNode", "findScrollable", "root", "pressEnter", "drag", "clickNodeByBounds", TypedValues.AttributesType.S_TARGET, "findNodeMatching", "dispatchSync", "Landroid/accessibilityservice/AccessibilityService;", "gesture", "Landroid/accessibilityservice/GestureDescription;", "setTextOnFocused", VoiceService.EXTRA_TEXT, "setTextOnNode", "findEditable", "globalAction", "action", "takeScreenshotBitmap", "Landroid/graphics/Bitmap;", "timeoutMs", "screen-control_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ScreenController {
    public static final ScreenController INSTANCE = new ScreenController();
    private static volatile JarvisAccessibilityService service;

    private ScreenController() {
    }

    public final void bind(JarvisAccessibilityService svc) {
        Intrinsics.checkNotNullParameter(svc, "svc");
        service = svc;
    }

    public final void unbind(JarvisAccessibilityService svc) {
        Intrinsics.checkNotNullParameter(svc, "svc");
        if (service == svc) {
            service = null;
        }
    }

    public final boolean isAvailable() {
        return service != null;
    }

    public final JarvisAccessibilityService requireService() {
        JarvisAccessibilityService jarvisAccessibilityService = service;
        if (jarvisAccessibilityService != null) {
            return jarvisAccessibilityService;
        }
        throw new IllegalStateException("Accessibility service is not enabled".toString());
    }

    public static /* synthetic */ List dumpTree$default(ScreenController screenController, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 500;
        }
        return screenController.dumpTree(i);
    }

    /* JADX WARN: Code duplicated, block: B:61:0x0119  */
    public final List<UiNode> dumpTree(int maxNodes) throws Throwable {
        int priority;
        Iterable $this$forEach$iv;
        boolean z;
        JarvisAccessibilityService svc = service;
        if (svc == null) {
            return CollectionsKt.emptyList();
        }
        ArrayList out = new ArrayList(128);
        List roots = new ArrayList();
        AccessibilityNodeInfo activeRoot = svc.getRootInActiveWindow();
        if (activeRoot != null) {
            roots.add(TuplesKt.to(activeRoot, 10));
        }
        Iterable windows = svc.getWindows();
        if (windows != null) {
            Iterable $this$forEach$iv2 = windows;
            for (Object element$iv : $this$forEach$iv2) {
                AccessibilityWindowInfo window = (AccessibilityWindowInfo) element$iv;
                AccessibilityNodeInfo root = window.getRoot();
                if (root == null) {
                    $this$forEach$iv = $this$forEach$iv2;
                } else {
                    switch (window.getType()) {
                        case 1:
                            priority = 100;
                            break;
                        case 2:
                            priority = 1;
                            break;
                        case 3:
                            priority = 2;
                            break;
                        case 4:
                            priority = 5;
                            break;
                        default:
                            priority = 20;
                            break;
                    }
                    List $this$none$iv = roots;
                    if (($this$none$iv instanceof Collection) && $this$none$iv.isEmpty()) {
                        $this$forEach$iv = $this$forEach$iv2;
                        z = true;
                    } else {
                        Iterator it = $this$none$iv.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                Object element$iv2 = it.next();
                                Pair it2 = (Pair) element$iv2;
                                $this$forEach$iv = $this$forEach$iv2;
                                if (INSTANCE.sameRoot((AccessibilityNodeInfo) it2.getFirst(), root)) {
                                    z = false;
                                } else {
                                    $this$forEach$iv2 = $this$forEach$iv;
                                }
                            } else {
                                $this$forEach$iv = $this$forEach$iv2;
                                z = true;
                            }
                        }
                    }
                    if (z) {
                        roots.add(TuplesKt.to(root, Integer.valueOf(priority)));
                    } else {
                        root.recycle();
                    }
                }
                $this$forEach$iv2 = $this$forEach$iv;
            }
        }
        if (roots.size() > 1) {
            CollectionsKt.sortWith(roots, new Comparator() { // from class: com.jarvis.screencontrol.ScreenController$dumpTree$$inlined$sortByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    Pair it3 = (Pair) t2;
                    Integer num = (Integer) it3.getSecond();
                    Pair it4 = (Pair) t;
                    return ComparisonsKt.compareValues(num, (Integer) it4.getSecond());
                }
            });
        }
        try {
            Iterator it3 = roots.iterator();
            while (it3.hasNext()) {
                try {
                    walk((AccessibilityNodeInfo) ((Pair) it3.next()).component1(), 0, out, maxNodes);
                    if (out.size() >= maxNodes) {
                        List $this$forEach$iv3 = roots;
                        for (Object element$iv3 : $this$forEach$iv3) {
                            Pair it4 = (Pair) element$iv3;
                            ScreenController screenController = INSTANCE;
                            try {
                                Result.Companion companion = Result.INSTANCE;
                                ((AccessibilityNodeInfo) it4.getFirst()).recycle();
                                Result.m392constructorimpl(Unit.INSTANCE);
                            } catch (Throwable th) {
                                Result.Companion companion2 = Result.INSTANCE;
                                Result.m392constructorimpl(ResultKt.createFailure(th));
                            }
                        }
                        return out;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    Throwable th3 = th;
                    List $this$forEach$iv4 = roots;
                    for (Object element$iv4 : $this$forEach$iv4) {
                        Pair it5 = (Pair) element$iv4;
                        ScreenController screenController2 = INSTANCE;
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            ((AccessibilityNodeInfo) it5.getFirst()).recycle();
                            Result.m392constructorimpl(Unit.INSTANCE);
                        } catch (Throwable th4) {
                            Result.Companion companion4 = Result.INSTANCE;
                            Result.m392constructorimpl(ResultKt.createFailure(th4));
                        }
                    }
                    throw th3;
                }
            }
            List $this$forEach$iv5 = roots;
            while (r9.hasNext()) {
                Pair it6 = (Pair) element$iv3;
                ScreenController screenController3 = INSTANCE;
                Result.Companion companion5 = Result.INSTANCE;
                ((AccessibilityNodeInfo) it6.getFirst()).recycle();
                Result.m392constructorimpl(Unit.INSTANCE);
            }
            return out;
        } catch (Throwable th5) {
            th = th5;
        }
    }

    public static /* synthetic */ List dumpActiveTree$default(ScreenController screenController, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 500;
        }
        return screenController.dumpActiveTree(i);
    }

    public final List<UiNode> dumpActiveTree(int maxNodes) throws Throwable {
        dismissShadeIfNeeded();
        List<UiNode> listDumpTree = dumpTree(maxNodes);
        if (listDumpTree.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        List overlayHints = CollectionsKt.listOf((Object[]) new String[]{"aod", "systemui", "keyguard", "miui.aod", ProactiveActionReceiver.EXTRA_FINGERPRINT, "biometrics"});
        String activePkg = activePackageName();
        if (activePkg != null) {
            List<UiNode> $this$filter$iv = listDumpTree;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                UiNode it = (UiNode) element$iv$iv;
                if (Intrinsics.areEqual(it.getPackageName(), activePkg)) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            List focused = (List) destination$iv$iv;
            if (!focused.isEmpty()) {
                return focused;
            }
        }
        List<UiNode> $this$filter$iv2 = listDumpTree;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv2 : $this$filter$iv2) {
            UiNode it2 = (UiNode) element$iv$iv2;
            if (!dumpActiveTree$isOverlay(overlayHints, it2.getPackageName())) {
                destination$iv$iv2.add(element$iv$iv2);
            }
        }
        List main = (List) destination$iv$iv2;
        List list = main;
        if (list.isEmpty()) {
            list = listDumpTree;
        }
        return list;
    }

    private static final boolean dumpActiveTree$isOverlay(List<String> list, String pkg) {
        List<String> $this$any$iv = list;
        if (($this$any$iv instanceof Collection) && $this$any$iv.isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            String it = (String) element$iv;
            if (StringsKt.contains((CharSequence) pkg, (CharSequence) it, true)) {
                return true;
            }
        }
        return false;
    }

    public final String activePackageName() {
        AccessibilityWindowInfo accessibilityWindowInfo;
        CharSequence packageName;
        String pkg;
        CharSequence packageName2;
        Object maxElem$iv;
        JarvisAccessibilityService svc = service;
        if (svc == null) {
            return null;
        }
        Iterable windows = svc.getWindows();
        boolean z = false;
        if (windows != null) {
            Iterable $this$filter$iv = windows;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                AccessibilityWindowInfo it = (AccessibilityWindowInfo) element$iv$iv;
                if (it.getType() == 1) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            Iterable $this$maxByOrNull$iv = (List) destination$iv$iv;
            Iterator iterator$iv = $this$maxByOrNull$iv.iterator();
            if (iterator$iv.hasNext()) {
                maxElem$iv = iterator$iv.next();
                if (iterator$iv.hasNext()) {
                    AccessibilityWindowInfo it2 = (AccessibilityWindowInfo) maxElem$iv;
                    int maxValue$iv = it2.getLayer();
                    do {
                        Object e$iv = iterator$iv.next();
                        AccessibilityWindowInfo it3 = (AccessibilityWindowInfo) e$iv;
                        int v$iv = it3.getLayer();
                        if (maxValue$iv < v$iv) {
                            maxElem$iv = e$iv;
                            maxValue$iv = v$iv;
                        }
                    } while (iterator$iv.hasNext());
                }
            } else {
                maxElem$iv = null;
            }
            accessibilityWindowInfo = (AccessibilityWindowInfo) maxElem$iv;
        } else {
            accessibilityWindowInfo = null;
        }
        AccessibilityWindowInfo appWin = accessibilityWindowInfo;
        AccessibilityNodeInfo root = appWin != null ? appWin.getRoot() : null;
        String pkg2 = (root == null || (packageName2 = root.getPackageName()) == null) ? null : packageName2.toString();
        if (root != null) {
            root.recycle();
        }
        String str = pkg2;
        if (!(str == null || StringsKt.isBlank(str))) {
            return pkg2;
        }
        AccessibilityNodeInfo rootInActiveWindow = svc.getRootInActiveWindow();
        if (rootInActiveWindow == null || (packageName = rootInActiveWindow.getPackageName()) == null || (pkg = packageName.toString()) == null) {
            return null;
        }
        Iterable $this$any$iv = CollectionsKt.listOf((Object[]) new String[]{"aod", "systemui", "keyguard"});
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            for (Object element$iv : $this$any$iv) {
                String it4 = (String) element$iv;
                if (StringsKt.contains((CharSequence) pkg, (CharSequence) it4, true)) {
                    z = true;
                    break;
                }
            }
        }
        if (z) {
            return null;
        }
        return pkg;
    }

    public final void dismissShadeIfNeeded() {
        CharSequence packageName;
        JarvisAccessibilityService svc = service;
        if (svc == null) {
            return;
        }
        AccessibilityNodeInfo rootInActiveWindow = svc.getRootInActiveWindow();
        String pkg = (rootInActiveWindow == null || (packageName = rootInActiveWindow.getPackageName()) == null) ? null : packageName.toString();
        if (pkg == null) {
            pkg = "";
        }
        if (StringsKt.contains((CharSequence) pkg, (CharSequence) "systemui", true)) {
            svc.performGlobalAction(1);
            try {
                Thread.sleep(350L);
            } catch (InterruptedException e) {
            }
        }
    }

    private final boolean sameRoot(AccessibilityNodeInfo a, AccessibilityNodeInfo b) {
        Rect ra = new Rect();
        Rect rb = new Rect();
        a.getBoundsInScreen(ra);
        b.getBoundsInScreen(rb);
        return Intrinsics.areEqual(ra, rb) && Intrinsics.areEqual(a.getPackageName(), b.getPackageName());
    }

    public final UiNode findFocusedEditable() {
        Object element$iv;
        UiNode it;
        Object obj = null;
        Iterable $this$firstOrNull$iv = dumpTree$default(this, 0, 1, null);
        Iterator it2 = $this$firstOrNull$iv.iterator();
        do {
            if (!it2.hasNext()) {
                element$iv = null;
                break;
            }
            element$iv = it2.next();
            it = (UiNode) element$iv;
        } while (!(it.getFocused() && it.getEditable()));
        UiNode uiNode = (UiNode) element$iv;
        if (uiNode == null) {
            Iterable $this$firstOrNull$iv2 = dumpTree$default(this, 0, 1, null);
            for (Object element$iv2 : $this$firstOrNull$iv2) {
                if (((UiNode) element$iv2).getEditable()) {
                    obj = element$iv2;
                    break;
                }
            }
            return (UiNode) obj;
        }
        return uiNode;
    }

    public static /* synthetic */ List visibleNodes$default(ScreenController screenController, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 500;
        }
        return screenController.visibleNodes(i);
    }

    public final List<UiNode> visibleNodes(int maxNodes) {
        AccessibilityNodeInfo root;
        JarvisAccessibilityService jarvisAccessibilityService = service;
        if (jarvisAccessibilityService == null || (root = jarvisAccessibilityService.getRootInActiveWindow()) == null) {
            return CollectionsKt.emptyList();
        }
        ArrayList out = new ArrayList();
        try {
            walkVisible(root, out, maxNodes);
            return out;
        } finally {
            root.recycle();
        }
    }

    private final void walkVisible(AccessibilityNodeInfo node, List<UiNode> out, int maxNodes) {
        if (out.size() >= maxNodes || !node.isVisibleToUser()) {
            return;
        }
        UiNode item = uiNode(node, 0);
        if (item.getBounds().isEmpty()) {
            return;
        }
        out.add(item);
        int childCount = node.getChildCount();
        for (int i = 0; i < childCount; i++) {
            AccessibilityNodeInfo child = node.getChild(i);
            if (child != null) {
                try {
                    walkVisible(child, out, maxNodes);
                    child.recycle();
                } catch (Throwable th) {
                    child.recycle();
                    throw th;
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:47:0x0087  */
    private final UiNode uiNode(AccessibilityNodeInfo node, int depth) {
        boolean z;
        String string;
        Rect bounds = new Rect();
        node.getBoundsInScreen(bounds);
        CharSequence text = node.getText();
        String string2 = text != null ? text.toString() : null;
        String str = string2 == null ? "" : string2;
        CharSequence contentDescription = node.getContentDescription();
        String string3 = contentDescription != null ? contentDescription.toString() : null;
        String str2 = string3 == null ? "" : string3;
        String viewIdResourceName = node.getViewIdResourceName();
        String str3 = viewIdResourceName == null ? "" : viewIdResourceName;
        CharSequence className = node.getClassName();
        String string4 = className != null ? className.toString() : null;
        String str4 = string4 == null ? "" : string4;
        CharSequence packageName = node.getPackageName();
        String string5 = packageName != null ? packageName.toString() : null;
        String str5 = string5 == null ? "" : string5;
        boolean zIsClickable = node.isClickable();
        if (node.isEditable()) {
            z = true;
        } else {
            CharSequence className2 = node.getClassName();
            if ((className2 == null || (string = className2.toString()) == null || !StringsKt.contains((CharSequence) string, (CharSequence) "EditText", true)) ? false : true) {
                z = true;
            } else {
                z = false;
            }
        }
        return new UiNode(str, str2, str3, str4, str5, bounds, zIsClickable, z, node.isFocused(), node.isEnabled(), depth, node.isPassword());
    }

    private final void walk(AccessibilityNodeInfo node, int depth, List<UiNode> out, int maxNodes) {
        if (out.size() >= maxNodes) {
            return;
        }
        out.add(uiNode(node, depth));
        int childCount = node.getChildCount();
        for (int i = 0; i < childCount; i++) {
            AccessibilityNodeInfo child = node.getChild(i);
            if (child != null) {
                try {
                    walk(child, depth + 1, out, maxNodes);
                    child.recycle();
                    if (out.size() >= maxNodes) {
                        return;
                    }
                } catch (Throwable th) {
                    child.recycle();
                    throw th;
                }
            }
        }
    }

    public static /* synthetic */ boolean tap$default(ScreenController screenController, float f, float f2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return screenController.tap(f, f2, z);
    }

    public final boolean tap(float x, float y, boolean longPress) {
        JarvisAccessibilityService svc = service;
        if (svc == null) {
            return false;
        }
        Path $this$tap_u24lambda_u240 = new Path();
        $this$tap_u24lambda_u240.moveTo(x, y);
        long duration = longPress ? 600L : 50L;
        GestureDescription.StrokeDescription stroke = new GestureDescription.StrokeDescription($this$tap_u24lambda_u240, 0L, duration);
        GestureDescription gesture = new GestureDescription.Builder().addStroke(stroke).build();
        Intrinsics.checkNotNull(gesture);
        return dispatchSync(svc, gesture);
    }

    public static /* synthetic */ boolean tapCenter$default(ScreenController screenController, UiNode uiNode, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return screenController.tapCenter(uiNode, z);
    }

    public final boolean tapCenter(UiNode node, boolean longPress) {
        Intrinsics.checkNotNullParameter(node, "node");
        float cx = node.getBounds().exactCenterX();
        float cy = node.getBounds().exactCenterY();
        if (cx <= 0.0f && cy <= 0.0f) {
            return false;
        }
        if (longPress || !clickNodeByBounds(node)) {
            return tap(cx, cy, longPress);
        }
        return true;
    }

    public static /* synthetic */ boolean swipe$default(ScreenController screenController, float f, float f2, float f3, float f4, long j, int i, Object obj) {
        long j2;
        if ((i & 16) == 0) {
            j2 = j;
        } else {
            j2 = 350;
        }
        return screenController.swipe(f, f2, f3, f4, j2);
    }

    public final boolean swipe(float x1, float y1, float x2, float y2, long durationMs) {
        JarvisAccessibilityService svc = service;
        if (svc == null) {
            return false;
        }
        Path $this$swipe_u24lambda_u240 = new Path();
        $this$swipe_u24lambda_u240.moveTo(x1, y1);
        $this$swipe_u24lambda_u240.lineTo(x2, y2);
        GestureDescription.StrokeDescription stroke = new GestureDescription.StrokeDescription($this$swipe_u24lambda_u240, 0L, Math.max(100L, durationMs));
        GestureDescription gesture = new GestureDescription.Builder().addStroke(stroke).build();
        Intrinsics.checkNotNull(gesture);
        return dispatchSync(svc, gesture);
    }

    public static /* synthetic */ boolean swipeDirection$default(ScreenController screenController, String str, float f, int i, Object obj) {
        if ((i & 2) != 0) {
            f = 0.45f;
        }
        return screenController.swipeDirection(str, f);
    }

    public final boolean swipeDirection(String direction, float distanceFraction) {
        Intrinsics.checkNotNullParameter(direction, "direction");
        JarvisAccessibilityService svc = service;
        if (svc == null) {
            return false;
        }
        if (scrollNode(direction)) {
            return true;
        }
        DisplayMetrics dm = svc.getResources().getDisplayMetrics();
        float w = dm.widthPixels;
        float h = dm.heightPixels;
        float cx = w / 2.0f;
        float cy = h / 2.0f;
        float distX = w * distanceFraction;
        float distY = h * distanceFraction;
        String lowerCase = direction.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        switch (lowerCase) {
            case "up":
                float f = 2;
                return swipe$default(this, cx, cy + (distY / f), cx, cy - (distY / f), 0L, 16, null);
            case "down":
                float f2 = 2;
                return swipe$default(this, cx, cy - (distY / f2), cx, cy + (distY / f2), 0L, 16, null);
            case "left":
                float f3 = 2;
                return swipe$default(this, cx + (distX / f3), cy, cx - (distX / f3), cy, 0L, 16, null);
            case "right":
                float f4 = 2;
                return swipe$default(this, cx - (distX / f4), cy, cx + (distX / f4), cy, 0L, 16, null);
            default:
                return false;
        }
    }

    public final boolean scrollNode(String direction) {
        AccessibilityNodeInfo root;
        int action;
        Intrinsics.checkNotNullParameter(direction, "direction");
        JarvisAccessibilityService svc = service;
        boolean z = false;
        if (svc == null || (root = svc.getRootInActiveWindow()) == null) {
            return false;
        }
        Set of = SetsKt.setOf((Object[]) new String[]{"down", "right", "forward", "вниз"});
        String lowerCase = direction.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        boolean forward = of.contains(lowerCase);
        if (forward) {
            action = 4096;
        } else {
            action = 8192;
        }
        try {
            AccessibilityNodeInfo node = findScrollable(root);
            if (node != null) {
                try {
                    boolean zPerformAction = node.performAction(action);
                    node.recycle();
                    z = zPerformAction;
                } catch (Throwable th) {
                    node.recycle();
                    throw th;
                }
            }
            root.recycle();
            return z;
        } catch (Throwable th2) {
            root.recycle();
            throw th2;
        }
    }

    private final AccessibilityNodeInfo findScrollable(AccessibilityNodeInfo root) {
        if (root.isScrollable()) {
            return AccessibilityNodeInfo.obtain(root);
        }
        int childCount = root.getChildCount();
        for (int i = 0; i < childCount; i++) {
            AccessibilityNodeInfo child = root.getChild(i);
            if (child != null) {
                AccessibilityNodeInfo found = findScrollable(child);
                child.recycle();
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    public final boolean pressEnter() {
        AccessibilityNodeInfo root;
        JarvisAccessibilityService svc = service;
        if (svc == null || (root = svc.getRootInActiveWindow()) == null) {
            return false;
        }
        try {
            AccessibilityNodeInfo focused = root.findFocus(1);
            if (focused == null) {
                root.recycle();
                return false;
            }
            try {
                if (Build.VERSION.SDK_INT >= 30 && focused.performAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_IME_ENTER.getId())) {
                    focused.recycle();
                    root.recycle();
                    return true;
                }
                Bundle args = new Bundle();
                args.putInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT, 4);
                if (focused.performAction(256, args)) {
                    focused.recycle();
                    root.recycle();
                    return true;
                }
                boolean zPerformAction = focused.performAction(16);
                focused.recycle();
                root.recycle();
                return zPerformAction;
            } catch (Throwable th) {
                focused.recycle();
                throw th;
            }
        } catch (Throwable th2) {
            root.recycle();
            throw th2;
        }
    }

    public static /* synthetic */ boolean drag$default(ScreenController screenController, float f, float f2, float f3, float f4, long j, int i, Object obj) {
        long j2;
        if ((i & 16) == 0) {
            j2 = j;
        } else {
            j2 = 500;
        }
        return screenController.drag(f, f2, f3, f4, j2);
    }

    public final boolean drag(float x1, float y1, float x2, float y2, long durationMs) {
        return swipe(x1, y1, x2, y2, durationMs);
    }

    private final boolean clickNodeByBounds(UiNode target) {
        AccessibilityNodeInfo root;
        boolean ok;
        boolean zPerformAction;
        JarvisAccessibilityService svc = service;
        if (svc == null || (root = svc.getRootInActiveWindow()) == null) {
            return false;
        }
        try {
            AccessibilityNodeInfo node = findNodeMatching(root, target);
            if (node != null) {
                try {
                    if (node.isClickable()) {
                        zPerformAction = node.performAction(16);
                    } else {
                        AccessibilityNodeInfo p = node.getParent();
                        while (true) {
                            if (p == null) {
                                ok = false;
                                break;
                            }
                            if (p.isClickable()) {
                                ok = p.performAction(16);
                                break;
                            }
                            AccessibilityNodeInfo next = p.getParent();
                            p.recycle();
                            p = next;
                        }
                        if (p != null) {
                            p.recycle();
                        }
                        zPerformAction = ok;
                    }
                    node.recycle();
                    root.recycle();
                    return zPerformAction;
                } catch (Throwable th) {
                    node.recycle();
                    throw th;
                }
            }
            root.recycle();
            return false;
        } catch (Throwable th2) {
            root.recycle();
            throw th2;
        }
    }

    private final AccessibilityNodeInfo findNodeMatching(AccessibilityNodeInfo root, UiNode target) {
        Rect bounds = new Rect();
        root.getBoundsInScreen(bounds);
        CharSequence text = root.getText();
        String text2 = text != null ? text.toString() : null;
        if (text2 == null) {
            text2 = "";
        }
        CharSequence contentDescription = root.getContentDescription();
        String desc = contentDescription != null ? contentDescription.toString() : null;
        if (desc == null) {
            desc = "";
        }
        String viewIdResourceName = root.getViewIdResourceName();
        String id = viewIdResourceName != null ? viewIdResourceName : "";
        if (Intrinsics.areEqual(bounds, target.getBounds()) && Intrinsics.areEqual(text2, target.getText()) && Intrinsics.areEqual(desc, target.getContentDescription()) && Intrinsics.areEqual(id, target.getViewId())) {
            return AccessibilityNodeInfo.obtain(root);
        }
        int childCount = root.getChildCount();
        for (int i = 0; i < childCount; i++) {
            AccessibilityNodeInfo child = root.getChild(i);
            if (child != null) {
                AccessibilityNodeInfo found = findNodeMatching(child, target);
                child.recycle();
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    private final boolean dispatchSync(final AccessibilityService svc, final GestureDescription gesture) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicReference ok = new AtomicReference(false);
        Handler main = new Handler(Looper.getMainLooper());
        final CountDownLatch posted = new CountDownLatch(1);
        main.post(new Runnable() { // from class: com.jarvis.screencontrol.ScreenController$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                ScreenController.dispatchSync$lambda$0(svc, gesture, posted, ok, latch);
            }
        });
        posted.await(1L, TimeUnit.SECONDS);
        latch.await(2L, TimeUnit.SECONDS);
        Object obj = ok.get();
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        return ((Boolean) obj).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void dispatchSync$lambda$0(AccessibilityService $svc, GestureDescription $gesture, CountDownLatch $posted, final AtomicReference $ok, final CountDownLatch $latch) {
        $svc.dispatchGesture($gesture, new AccessibilityService.GestureResultCallback() { // from class: com.jarvis.screencontrol.ScreenController$dispatchSync$1$1
            @Override // android.accessibilityservice.AccessibilityService.GestureResultCallback
            public void onCompleted(GestureDescription gestureDescription) {
                $ok.set(true);
                $latch.countDown();
            }

            @Override // android.accessibilityservice.AccessibilityService.GestureResultCallback
            public void onCancelled(GestureDescription gestureDescription) {
                $ok.set(false);
                $latch.countDown();
            }
        }, null);
        $posted.countDown();
    }

    public final boolean setTextOnFocused(String text) {
        AccessibilityNodeInfo root;
        Intrinsics.checkNotNullParameter(text, "text");
        JarvisAccessibilityService svc = service;
        if (svc == null || (root = svc.getRootInActiveWindow()) == null) {
            return false;
        }
        try {
            AccessibilityNodeInfo accessibilityNodeInfoFindFocus = root.findFocus(1);
            if (accessibilityNodeInfoFindFocus != null || (accessibilityNodeInfoFindFocus = findEditable(root)) != null) {
                AccessibilityNodeInfo focused = accessibilityNodeInfoFindFocus;
                try {
                    boolean textOnNode = setTextOnNode(svc, focused, text);
                    focused.recycle();
                    root.recycle();
                    return textOnNode;
                } catch (Throwable th) {
                    focused.recycle();
                    throw th;
                }
            }
            root.recycle();
            return false;
        } catch (Throwable th2) {
            root.recycle();
            throw th2;
        }
    }

    public final boolean setTextOnNode(UiNode node, String text) {
        AccessibilityNodeInfo root;
        Intrinsics.checkNotNullParameter(node, "node");
        Intrinsics.checkNotNullParameter(text, "text");
        JarvisAccessibilityService svc = service;
        if (svc == null || (root = svc.getRootInActiveWindow()) == null) {
            return false;
        }
        try {
            AccessibilityNodeInfo n = findNodeMatching(root, node);
            if (n != null) {
                try {
                    boolean textOnNode = setTextOnNode(svc, n, text);
                    n.recycle();
                    root.recycle();
                    return textOnNode;
                } catch (Throwable th) {
                    n.recycle();
                    throw th;
                }
            }
            root.recycle();
            return false;
        } catch (Throwable th2) {
            root.recycle();
            throw th2;
        }
    }

    private final boolean setTextOnNode(AccessibilityService svc, AccessibilityNodeInfo node, String text) {
        node.performAction(1);
        Bundle args = new Bundle();
        args.putCharSequence(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, text);
        if (node.performAction(2097152, args)) {
            return true;
        }
        Object systemService = svc.getSystemService("clipboard");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
        ClipboardManager cm = (ClipboardManager) systemService;
        cm.setPrimaryClip(ClipData.newPlainText("jarvis", text));
        return node.performAction(32768);
    }

    private final AccessibilityNodeInfo findEditable(AccessibilityNodeInfo root) {
        String string;
        if (!root.isEditable()) {
            CharSequence className = root.getClassName();
            boolean z = false;
            if (className != null && (string = className.toString()) != null && StringsKt.contains((CharSequence) string, (CharSequence) "EditText", true)) {
                z = true;
            }
            if (!z) {
                int childCount = root.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    AccessibilityNodeInfo child = root.getChild(i);
                    if (child != null) {
                        AccessibilityNodeInfo found = findEditable(child);
                        child.recycle();
                        if (found != null) {
                            return found;
                        }
                    }
                }
                return null;
            }
        }
        return AccessibilityNodeInfo.obtain(root);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002e, code lost:
    
        if (r2.equals("recents") == false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0037, code lost:
    
        if (r2.equals("notification") == false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003a, code lost:
    
        r1 = 4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0042, code lost:
    
        if (r2.equals("overview") == false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0078, code lost:
    
        if (r2.equals("qs") == false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0081, code lost:
    
        if (r2.equals("quick_settings") == false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0084, code lost:
    
        r1 = 5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x008c, code lost:
    
        if (r2.equals("recent") == false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x008f, code lost:
    
        r1 = 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0025, code lost:
    
        if (r2.equals("notifications") == false) goto L50;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean globalAction(java.lang.String r5) {
        /*
            r4 = this;
            java.lang.String r0 = "action"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            com.jarvis.screencontrol.JarvisAccessibilityService r0 = com.jarvis.screencontrol.ScreenController.service
            r1 = 0
            if (r0 != 0) goto Lb
            return r1
        Lb:
            java.util.Locale r2 = java.util.Locale.ROOT
            java.lang.String r2 = r5.toLowerCase(r2)
            java.lang.String r3 = "toLowerCase(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            int r3 = r2.hashCode()
            switch(r3) {
                case -934918565: goto L86;
                case -424664523: goto L7b;
                case 3618: goto L72;
                case 3015911: goto L67;
                case 3208415: goto L5c;
                case 3327275: goto L50;
                case 106858757: goto L45;
                case 530115961: goto L3c;
                case 595233003: goto L31;
                case 1082295672: goto L28;
                case 1272354024: goto L1f;
                default: goto L1d;
            }
        L1d:
            goto L96
        L1f:
            java.lang.String r3 = "notifications"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L3a
            goto L1d
        L28:
            java.lang.String r3 = "recents"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L8f
            goto L1d
        L31:
            java.lang.String r3 = "notification"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L3a
            goto L1d
        L3a:
            r1 = 4
            goto L90
        L3c:
            java.lang.String r3 = "overview"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L8f
            goto L1d
        L45:
            java.lang.String r3 = "power"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L4e
            goto L1d
        L4e:
            r1 = 6
            goto L90
        L50:
            java.lang.String r3 = "lock"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L59
            goto L1d
        L59:
            r1 = 8
            goto L90
        L5c:
            java.lang.String r3 = "home"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L65
            goto L1d
        L65:
            r1 = 2
            goto L90
        L67:
            java.lang.String r3 = "back"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L70
            goto L1d
        L70:
            r1 = 1
            goto L90
        L72:
            java.lang.String r3 = "qs"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L84
            goto L1d
        L7b:
            java.lang.String r3 = "quick_settings"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L84
            goto L1d
        L84:
            r1 = 5
            goto L90
        L86:
            java.lang.String r3 = "recent"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L8f
            goto L1d
        L8f:
            r1 = 3
        L90:
            boolean r2 = r0.performGlobalAction(r1)
            return r2
        L96:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jarvis.screencontrol.ScreenController.globalAction(java.lang.String):boolean");
    }

    public static /* synthetic */ Bitmap takeScreenshotBitmap$default(ScreenController screenController, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 4000;
        }
        return screenController.takeScreenshotBitmap(j);
    }

    public final Bitmap takeScreenshotBitmap(long timeoutMs) throws InterruptedException {
        final JarvisAccessibilityService svc = service;
        if (svc == null || Build.VERSION.SDK_INT < 30) {
            return null;
        }
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicReference result = new AtomicReference(null);
        Handler main = new Handler(Looper.getMainLooper());
        main.post(new Runnable() { // from class: com.jarvis.screencontrol.ScreenController$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                ScreenController.takeScreenshotBitmap$lambda$0(svc, result, latch);
            }
        });
        latch.await(timeoutMs, TimeUnit.MILLISECONDS);
        return (Bitmap) result.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void takeScreenshotBitmap$lambda$0(JarvisAccessibilityService $svc, final AtomicReference $result, final CountDownLatch $latch) {
        $svc.takeScreenshot(0, new Executor() { // from class: com.jarvis.screencontrol.ScreenController$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                runnable.run();
            }
        }, new AccessibilityService.TakeScreenshotCallback() { // from class: com.jarvis.screencontrol.ScreenController$takeScreenshotBitmap$1$2
            @Override // android.accessibilityservice.AccessibilityService.TakeScreenshotCallback
            public void onSuccess(AccessibilityService.ScreenshotResult screenshot) {
                Intrinsics.checkNotNullParameter(screenshot, "screenshot");
                try {
                    HardwareBuffer hw = screenshot.getHardwareBuffer();
                    Intrinsics.checkNotNullExpressionValue(hw, "getHardwareBuffer(...)");
                    ColorSpace color = screenshot.getColorSpace();
                    Intrinsics.checkNotNullExpressionValue(color, "getColorSpace(...)");
                    AtomicReference<Bitmap> atomicReference = $result;
                    Bitmap bitmapWrapHardwareBuffer = Bitmap.wrapHardwareBuffer(hw, color);
                    atomicReference.set(bitmapWrapHardwareBuffer != null ? bitmapWrapHardwareBuffer.copy(Bitmap.Config.ARGB_8888, false) : null);
                    hw.close();
                } catch (Throwable th) {
                    try {
                        $result.set(null);
                    } finally {
                        $latch.countDown();
                    }
                }
            }

            @Override // android.accessibilityservice.AccessibilityService.TakeScreenshotCallback
            public void onFailure(int errorCode) {
                $latch.countDown();
            }
        });
    }
}
