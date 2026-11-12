package com.jarvis.screencontrol;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.DisplayMetrics;
import com.google.android.gms.actions.SearchIntents;
import com.jarvis.app.VoiceService;
import com.jarvis.app.ui.JarvisActivity;
import com.jarvis.core.nlp.Transliterator;
import com.jarvis.core.routines.EventMatcher;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequencesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.apache.commons.io.IOUtils;

/* JADX INFO: compiled from: ActionCoordinator.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u000b\u0018\u00002\u00020\u0001:\u0001:B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J,\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fJ\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bJ:\u0010\u0011\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u0012\u001a\u00020\u000f2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u0015\u001a\u00020\u000bJ\u0018\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\t2\b\b\u0002\u0010\u0012\u001a\u00020\u000fJ\u0010\u0010\u0017\u001a\u00020\u00072\b\b\u0002\u0010\u0018\u001a\u00020\rJ\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0018\u001a\u00020\r2\b\u0010\u001a\u001a\u0004\u0018\u00010\tH\u0002J\"\u0010\u001b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u001c\u001a\u00020\t2\b\b\u0002\u0010\u001d\u001a\u00020\rJ \u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\b\b\u0002\u0010\n\u001a\u00020\u000bJ\u001e\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020 \u0018\u00010#2\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010$\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010%\u001a\u00020\tJ\u000e\u0010&\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\tJ\u000e\u0010'\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\tJ\u000e\u0010(\u001a\u00020\u00072\u0006\u0010)\u001a\u00020\tJ$\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010,\u001a\u00020\u000b2\b\b\u0002\u0010\u0015\u001a\u00020\u000bJ\u0018\u0010-\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010.\u001a\u00020+H\u0002J\u0016\u0010/\u001a\b\u0012\u0004\u0012\u00020\t002\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u00101\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010.\u001a\u00020+H\u0002J\u0018\u00102\u001a\u00020\u000b2\u0006\u00103\u001a\u00020\t2\u0006\u00104\u001a\u00020\tH\u0002J\u0018\u00105\u001a\u00020\r2\u0006\u00103\u001a\u00020\t2\u0006\u00104\u001a\u00020\tH\u0002J\u0010\u00106\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tJ\u0006\u00107\u001a\u00020\u0007J\u000e\u00108\u001a\u00020\u00072\u0006\u00109\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006;"}, d2 = {"Lcom/jarvis/screencontrol/ActionCoordinator;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "findAndClick", "Lcom/jarvis/screencontrol/ActionCoordinator$ActionResult;", SearchIntents.EXTRA_QUERY, "", "longPress", "", "scrollAttempts", "", "waitTimeoutMs", "", "findAndClickByOcr", "waitForNode", "timeoutMs", "packageHint", "viewId", "forClick", "waitForPackage", "describeScreen", "maxItems", "describeByOcr", "pkgShort", "scrollUntil", "direction", "maxAttempts", "tapFraction", "fx", "", "fy", "parseTapPoint", "Lkotlin/Pair;", "findAndType", VoiceService.EXTRA_TEXT, "findAndTypeInBottomField", "swipe", "global", "action", "findBestNode", "Lcom/jarvis/screencontrol/UiNode;", "preferEditable", "exactLabelMatch", "node", "expandQuery", "", "matchScore", "fuzzyClose", "a", "b", "levenshtein", "tryIntentFallback", "openWifiSettings", "openAppDetails", "packageName", "ActionResult", "screen-control_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ActionCoordinator {
    private final Context context;

    public ActionCoordinator(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    /* JADX INFO: compiled from: ActionCoordinator.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J3\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00032\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001a"}, d2 = {"Lcom/jarvis/screencontrol/ActionCoordinator$ActionResult;", "", "success", "", "message", "", "matchedText", "usedFallback", "<init>", "(ZLjava/lang/String;Ljava/lang/String;Z)V", "getSuccess", "()Z", "getMessage", "()Ljava/lang/String;", "getMatchedText", "getUsedFallback", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "screen-control_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class ActionResult {
        private final String matchedText;
        private final String message;
        private final boolean success;
        private final boolean usedFallback;

        public static /* synthetic */ ActionResult copy$default(ActionResult actionResult, boolean z, String str, String str2, boolean z2, int i, Object obj) {
            if ((i & 1) != 0) {
                z = actionResult.success;
            }
            if ((i & 2) != 0) {
                str = actionResult.message;
            }
            if ((i & 4) != 0) {
                str2 = actionResult.matchedText;
            }
            if ((i & 8) != 0) {
                z2 = actionResult.usedFallback;
            }
            return actionResult.copy(z, str, str2, z2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getSuccess() {
            return this.success;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getMatchedText() {
            return this.matchedText;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getUsedFallback() {
            return this.usedFallback;
        }

        public final ActionResult copy(boolean success, String message, String matchedText, boolean usedFallback) {
            Intrinsics.checkNotNullParameter(message, "message");
            return new ActionResult(success, message, matchedText, usedFallback);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ActionResult)) {
                return false;
            }
            ActionResult actionResult = (ActionResult) other;
            return this.success == actionResult.success && Intrinsics.areEqual(this.message, actionResult.message) && Intrinsics.areEqual(this.matchedText, actionResult.matchedText) && this.usedFallback == actionResult.usedFallback;
        }

        public int hashCode() {
            return (((((Boolean.hashCode(this.success) * 31) + this.message.hashCode()) * 31) + (this.matchedText == null ? 0 : this.matchedText.hashCode())) * 31) + Boolean.hashCode(this.usedFallback);
        }

        public String toString() {
            return "ActionResult(success=" + this.success + ", message=" + this.message + ", matchedText=" + this.matchedText + ", usedFallback=" + this.usedFallback + ")";
        }

        public ActionResult(boolean success, String message, String matchedText, boolean usedFallback) {
            Intrinsics.checkNotNullParameter(message, "message");
            this.success = success;
            this.message = message;
            this.matchedText = matchedText;
            this.usedFallback = usedFallback;
        }

        public /* synthetic */ ActionResult(boolean z, String str, String str2, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? false : z2);
        }

        public final boolean getSuccess() {
            return this.success;
        }

        public final String getMessage() {
            return this.message;
        }

        public final String getMatchedText() {
            return this.matchedText;
        }

        public final boolean getUsedFallback() {
            return this.usedFallback;
        }
    }

    public final ActionResult findAndClick(String query, boolean longPress, int scrollAttempts, long waitTimeoutMs) throws InterruptedException {
        Intrinsics.checkNotNullParameter(query, "query");
        if (!ScreenController.INSTANCE.isAvailable()) {
            ActionResult it = tryIntentFallback(query);
            if (it != null) {
                return it;
            }
            return new ActionResult(false, "Служба спец. возможностей Jarvis не включена.", null, false, 12, null);
        }
        ScreenController.INSTANCE.dismissShadeIfNeeded();
        Pair<Float, Float> tapPoint = parseTapPoint(query);
        if (tapPoint != null) {
            float x = tapPoint.component1().floatValue();
            float y = tapPoint.component2().floatValue();
            boolean ok = ScreenController.INSTANCE.tap(x, y, longPress);
            if (ok) {
                return new ActionResult(true, "Нажал по координатам.", null, false, 12, null);
            }
            return new ActionResult(false, "Тап по координатам не выполнен.", null, false, 12, null);
        }
        if (waitTimeoutMs > 0) {
            waitForNode$default(this, query, waitTimeoutMs, null, null, true, 12, null);
        }
        int i = scrollAttempts + 1;
        for (int i2 = 0; i2 < i; i2++) {
            int attempt = i2;
            UiNode node = findBestNode(query, false, true);
            if (node != null) {
                boolean ok2 = ScreenController.INSTANCE.tapCenter(node, longPress);
                if (ok2) {
                    String text = node.getText();
                    if (StringsKt.isBlank(text)) {
                        text = node.getContentDescription();
                    }
                    String str = text;
                    if (StringsKt.isBlank(str)) {
                        str = query;
                    }
                    return new ActionResult(true, "Нажал «" + ((Object) str) + "».", node.getText(), false, 8, null);
                }
                return new ActionResult(false, "Нашёл «" + node.getSearchable() + "», но нажать не удалось.", null, false, 12, null);
            }
            if (attempt < scrollAttempts) {
                ScreenController.swipeDirection$default(ScreenController.INSTANCE, "up", 0.0f, 2, null);
                Thread.sleep(450L);
            }
        }
        ActionResult fallback = tryIntentFallback(query);
        if (fallback != null) {
            return fallback;
        }
        ActionResult ocrHit = findAndClickByOcr(query, longPress);
        if (ocrHit != null) {
            return ocrHit;
        }
        return new ActionResult(false, "На экране не нашёл элемент «" + query + "».", null, false, 12, null);
    }

    public static /* synthetic */ ActionResult findAndClickByOcr$default(ActionCoordinator actionCoordinator, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return actionCoordinator.findAndClickByOcr(str, z);
    }

    public final ActionResult findAndClickByOcr(String query, boolean longPress) {
        Object objM392constructorimpl;
        ScreenOcr.Block hit;
        Intrinsics.checkNotNullParameter(query, "query");
        if (!ScreenController.INSTANCE.isAvailable()) {
            return null;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            ActionCoordinator actionCoordinator = this;
            objM392constructorimpl = Result.m392constructorimpl(ScreenOcr.recognizeScreen$default(ScreenOcr.INSTANCE, 0L, 1, null));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        List listEmptyList = CollectionsKt.emptyList();
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = listEmptyList;
        }
        List<ScreenOcr.Block> list = (List) objM392constructorimpl;
        if (list.isEmpty() || (hit = ScreenOcr.INSTANCE.findBest(query, list)) == null) {
            return null;
        }
        float cx = hit.getBounds().exactCenterX();
        float cy = hit.getBounds().exactCenterY();
        boolean ok = ScreenController.INSTANCE.tap(cx, cy, longPress);
        if (ok) {
            return new ActionResult(true, "Нажал по OCR «" + StringsKt.take(hit.getText(), 40) + "».", hit.getText(), true);
        }
        return new ActionResult(false, "OCR нашёл «" + StringsKt.take(hit.getText(), 40) + "», но тап не прошёл.", hit.getText(), true);
    }

    public static /* synthetic */ ActionResult waitForNode$default(ActionCoordinator actionCoordinator, String str, long j, String str2, String str3, boolean z, int i, Object obj) {
        return actionCoordinator.waitForNode(str, (i & 2) != 0 ? 5000L : j, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? false : z);
    }

    /* JADX WARN: Code duplicated, block: B:131:0x00e8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:132:0x0129 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:133:0x01a4 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:135:0x00e2 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:136:0x00dc A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:30:0x007b  */
    /* JADX WARN: Code duplicated, block: B:42:0x0099  */
    /* JADX WARN: Code duplicated, block: B:48:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:51:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:54:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:57:0x00df A[LOOP:1: B:52:0x00bb->B:57:0x00df, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:65:0x011c  */
    /* JADX WARN: Code duplicated, block: B:69:0x0135  */
    /* JADX WARN: Code duplicated, block: B:72:0x0164  */
    /* JADX WARN: Code duplicated, block: B:73:0x0168  */
    /* JADX WARN: Code duplicated, block: B:79:0x017d  */
    /* JADX WARN: Code duplicated, block: B:81:0x0180  */
    /* JADX WARN: Code duplicated, block: B:87:0x018e  */
    /* JADX WARN: Code duplicated, block: B:89:0x0191  */
    /* JADX WARN: Code duplicated, block: B:90:0x0193  */
    /* JADX WARN: Code duplicated, block: B:92:0x0198  */
    /* JADX WARN: Code duplicated, block: B:96:0x01a1  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v25 */
    /* JADX WARN: Type inference failed for: r0v28 */
    /* JADX WARN: Type inference failed for: r0v29 */
    /* JADX WARN: Type inference failed for: r0v31 */
    /* JADX WARN: Type inference failed for: r0v33 */
    /* JADX WARN: Type inference failed for: r0v40 */
    /* JADX WARN: Type inference failed for: r0v48 */
    /* JADX WARN: Type inference failed for: r0v49 */
    /* JADX WARN: Type inference failed for: r0v51 */
    /* JADX WARN: Type inference failed for: r11v0 */
    /* JADX WARN: Type inference failed for: r11v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r11v21 */
    /* JADX WARN: Type inference failed for: r12v18 */
    /* JADX WARN: Type inference failed for: r12v8 */
    /* JADX WARN: Type inference failed for: r12v9 */
    public final ActionResult waitForNode(String query, long timeoutMs, String packageHint, String viewId, boolean forClick) {
        int i;
        String what;
        boolean sawPackage;
        String str;
        ?? r0;
        long deadline;
        String str2;
        ?? r1;
        String str3;
        ?? r2;
        String str4;
        ?? r3;
        UiNode node;
        String searchable;
        Iterator it;
        Object obj;
        UiNode byId;
        Object element$iv;
        UiNode it2;
        Intrinsics.checkNotNullParameter(query, "query");
        if (!ScreenController.INSTANCE.isAvailable()) {
            return new ActionResult(false, "Служба спец. возможностей Jarvis не включена.", null, false, 12, null);
        }
        long deadline2 = System.currentTimeMillis() + RangesKt.coerceAtLeast(timeoutMs, 200L);
        long backoff = 120;
        String str5 = packageHint;
        int i2 = 0;
        ?? r11 = 1;
        boolean sawPackage2 = str5 == null || StringsKt.isBlank(str5);
        while (System.currentTimeMillis() < deadline2) {
            ScreenController.INSTANCE.dismissShadeIfNeeded();
            String str6 = packageHint;
            if (((str6 == null || StringsKt.isBlank(str6)) ? r11 : i2) == 0 && !sawPackage2) {
                String pkg = ScreenController.INSTANCE.activePackageName();
                if (pkg == null) {
                    pkg = "";
                }
                if (StringsKt.contains(pkg, packageHint, (boolean) r11)) {
                    sawPackage = true;
                } else {
                    sawPackage = sawPackage2;
                }
            } else {
                sawPackage = sawPackage2;
            }
            if (sawPackage) {
                str = viewId;
                if (str != null || StringsKt.isBlank(str)) {
                    r0 = r11;
                } else {
                    r0 = i2;
                }
                if (r0 == 0) {
                    Iterable $this$firstOrNull$iv = ScreenController.dumpActiveTree$default(ScreenController.INSTANCE, i2, r11, null);
                    it = $this$firstOrNull$iv.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            element$iv = it.next();
                            it2 = (UiNode) element$iv;
                            if (StringsKt.contains(it2.getViewId(), viewId, (boolean) r11)) {
                                obj = element$iv;
                                break;
                            }
                        } else {
                            obj = null;
                            break;
                        }
                    }
                    byId = (UiNode) obj;
                    if (byId != null) {
                        return new ActionResult(true, "Узел id«" + viewId + "» на экране.", byId.getText(), false, 8, null);
                    }
                }
                if (StringsKt.isBlank(query)) {
                    deadline = deadline2;
                    i = 0;
                    str2 = viewId;
                    if (str2 != null || StringsKt.isBlank(str2)) {
                        r1 = r11;
                    } else {
                        r1 = 0;
                    }
                    if (r1 == 0) {
                        str4 = packageHint;
                        if (str4 != null || StringsKt.isBlank(str4)) {
                            r3 = r11;
                        } else {
                            r3 = 0;
                        }
                        if (r3 == 0) {
                            if (sawPackage) {
                                str3 = viewId;
                                if (str3 != null) {
                                    r2 = r11;
                                } else {
                                    r2 = r11;
                                }
                                if (r2 != 0) {
                                    return new ActionResult(true, "Пакет «" + packageHint + "» активен.", null, false, 12, null);
                                }
                            }
                        }
                    } else if (sawPackage) {
                        str3 = viewId;
                        if (str3 != null || StringsKt.isBlank(str3)) {
                            r2 = r11;
                        } else {
                            r2 = 0;
                        }
                        if (r2 != 0) {
                            return new ActionResult(true, "Пакет «" + packageHint + "» активен.", null, false, 12, null);
                        }
                    }
                } else {
                    i = 0;
                    node = findBestNode(query, false, forClick);
                    if (node != null) {
                        deadline = deadline2;
                    } else {
                        searchable = node.getSearchable();
                        if (StringsKt.isBlank(searchable)) {
                            searchable = query;
                        }
                        return new ActionResult(true, "Узел «" + ((Object) searchable) + "» на экране.", node.getText(), false, 8, null);
                    }
                }
            } else {
                String str7 = packageHint;
                if (((str7 == null || StringsKt.isBlank(str7)) ? r11 : i2) != 0) {
                    str = viewId;
                    if (str != null) {
                        r0 = r11;
                    } else {
                        r0 = r11;
                    }
                    if (r0 == 0) {
                        Iterable $this$firstOrNull$iv2 = ScreenController.dumpActiveTree$default(ScreenController.INSTANCE, i2, r11, null);
                        it = $this$firstOrNull$iv2.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                element$iv = it.next();
                                it2 = (UiNode) element$iv;
                                if (StringsKt.contains(it2.getViewId(), viewId, (boolean) r11)) {
                                    obj = element$iv;
                                    break;
                                }
                            } else {
                                obj = null;
                                break;
                            }
                        }
                        byId = (UiNode) obj;
                        if (byId != null) {
                            return new ActionResult(true, "Узел id«" + viewId + "» на экране.", byId.getText(), false, 8, null);
                        }
                    }
                    if (StringsKt.isBlank(query)) {
                        i = 0;
                        node = findBestNode(query, false, forClick);
                        if (node != null) {
                            deadline = deadline2;
                        } else {
                            searchable = node.getSearchable();
                            if (StringsKt.isBlank(searchable)) {
                                searchable = query;
                            }
                            return new ActionResult(true, "Узел «" + ((Object) searchable) + "» на экране.", node.getText(), false, 8, null);
                        }
                    } else {
                        deadline = deadline2;
                        i = 0;
                        str2 = viewId;
                        if (str2 != null) {
                            r1 = r11;
                        } else {
                            r1 = r11;
                        }
                        if (r1 == 0) {
                            str4 = packageHint;
                            if (str4 != null) {
                                r3 = r11;
                            } else {
                                r3 = r11;
                            }
                            if (r3 == 0) {
                                if (sawPackage) {
                                    str3 = viewId;
                                    if (str3 != null) {
                                        r2 = r11;
                                    } else {
                                        r2 = r11;
                                    }
                                    if (r2 != 0) {
                                        return new ActionResult(true, "Пакет «" + packageHint + "» активен.", null, false, 12, null);
                                    }
                                }
                            }
                        } else if (sawPackage) {
                            str3 = viewId;
                            if (str3 != null) {
                                r2 = r11;
                            } else {
                                r2 = r11;
                            }
                            if (r2 != 0) {
                                return new ActionResult(true, "Пакет «" + packageHint + "» активен.", null, false, 12, null);
                            }
                        }
                    }
                } else {
                    deadline = deadline2;
                    i = i2;
                }
            }
            try {
                Thread.sleep(backoff);
                backoff = RangesKt.coerceAtMost(((long) 2) * backoff, 700L);
                sawPackage2 = sawPackage;
                i2 = i;
                deadline2 = deadline;
                r11 = 1;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        i = i2;
        if (StringsKt.isBlank(query)) {
            String str8 = viewId;
            if (((str8 == null || StringsKt.isBlank(str8)) ? 1 : i) == 0) {
                what = "id«" + viewId + "»";
            } else {
                String str9 = packageHint;
                if (str9 == null || StringsKt.isBlank(str9)) {
                    i = 1;
                }
                what = i == 0 ? "пакет «" + packageHint + "»" : "узел";
            }
        } else {
            what = "«" + query + "»";
        }
        return new ActionResult(false, "Таймаут " + timeoutMs + "мс: " + what + " не появился.", null, false, 12, null);
    }

    public static /* synthetic */ ActionResult waitForPackage$default(ActionCoordinator actionCoordinator, String str, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 4000;
        }
        return actionCoordinator.waitForPackage(str, j);
    }

    public final ActionResult waitForPackage(String packageHint, long timeoutMs) {
        Intrinsics.checkNotNullParameter(packageHint, "packageHint");
        return waitForNode$default(this, "", timeoutMs, packageHint, null, false, 24, null);
    }

    public static /* synthetic */ ActionResult describeScreen$default(ActionCoordinator actionCoordinator, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 30;
        }
        return actionCoordinator.describeScreen(i);
    }

    public final ActionResult describeScreen(int maxItems) {
        boolean z;
        ActionResult ocr;
        Object element$iv;
        if (!ScreenController.INSTANCE.isAvailable()) {
            return new ActionResult(false, "Служба спец. возможностей Jarvis не включена.", null, false, 12, null);
        }
        List tree = ScreenController.dumpActiveTree$default(ScreenController.INSTANCE, 0, 1, null);
        String activePkg = ScreenController.INSTANCE.activePackageName();
        if (activePkg == null) {
            List $this$firstOrNull$iv = tree;
            Iterator it = $this$firstOrNull$iv.iterator();
            do {
                if (!it.hasNext()) {
                    element$iv = null;
                    break;
                }
                element$iv = it.next();
            } while (StringsKt.isBlank(((UiNode) element$iv).getPackageName()));
            UiNode uiNode = (UiNode) element$iv;
            activePkg = uiNode != null ? uiNode.getPackageName() : null;
        }
        String pkgShort = activePkg != null ? StringsKt.substringAfterLast$default(activePkg, '.', (String) null, 2, (Object) null) : null;
        final Set noise = SetsKt.setOf((Object[]) new String[]{"scrim", "blur", "aod_", "legacy_window", "shade_", "notification_animation", "keyguard", "footer_clip", "header_clip", "fold_header"});
        List nodes = SequencesKt.toList(SequencesKt.take(SequencesKt.distinct(SequencesKt.filterNot(SequencesKt.filterNot(SequencesKt.filter(SequencesKt.map(SequencesKt.filter(CollectionsKt.asSequence(tree), new Function1() { // from class: com.jarvis.screencontrol.ActionCoordinator$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ActionCoordinator.describeScreen$lambda$1((UiNode) obj));
            }
        }), new Function1() { // from class: com.jarvis.screencontrol.ActionCoordinator$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ActionCoordinator.describeScreen$lambda$2((UiNode) obj);
            }
        }), new Function1() { // from class: com.jarvis.screencontrol.ActionCoordinator$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ActionCoordinator.describeScreen$lambda$3((String) obj));
            }
        }), new Function1() { // from class: com.jarvis.screencontrol.ActionCoordinator$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ActionCoordinator.describeScreen$lambda$4((String) obj));
            }
        }), new Function1() { // from class: com.jarvis.screencontrol.ActionCoordinator$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ActionCoordinator.describeScreen$lambda$5(noise, (String) obj));
            }
        })), maxItems));
        if (nodes.size() < 3 && (ocr = describeByOcr(maxItems, pkgShort)) != null) {
            return ocr;
        }
        if (tree.isEmpty()) {
            return new ActionResult(false, "Не вижу содержимое экрана. Открой любой экран поверх Jarvis и повтори «что на экране».", null, false, 12, null);
        }
        if (!nodes.isEmpty()) {
            String prefix = pkgShort != null ? "Экран «" + pkgShort + "»: " : "На экране: ";
            return new ActionResult(true, prefix + CollectionsKt.joinToString$default(nodes, "; ", null, null, 0, null, null, 62, null) + ".", CollectionsKt.joinToString$default(nodes, "|", null, null, 0, null, null, 62, null), false, 8, null);
        }
        List $this$map$iv = tree;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            destination$iv$iv.add(StringsKt.substringAfterLast$default(((UiNode) item$iv$iv).getPackageName(), '.', (String) null, 2, (Object) null));
        }
        Iterable pkgs = CollectionsKt.take(CollectionsKt.distinct((List) destination$iv$iv), 6);
        Iterable $this$all$iv = pkgs;
        if (($this$all$iv instanceof Collection) && ((Collection) $this$all$iv).isEmpty()) {
            z = true;
        } else {
            Iterator it2 = $this$all$iv.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z = true;
                    break;
                }
                Object element$iv2 = it2.next();
                String it3 = (String) element$iv2;
                List tree2 = tree;
                if (!(StringsKt.contains((CharSequence) it3, (CharSequence) "systemui", true) || StringsKt.contains((CharSequence) it3, (CharSequence) "aod", true) || StringsKt.contains((CharSequence) it3, (CharSequence) "plugin", true) || StringsKt.contains((CharSequence) it3, (CharSequence) "keyguard", true))) {
                    z = false;
                    break;
                }
                tree = tree2;
            }
        }
        boolean onlySystem = z;
        String hint = onlySystem ? " Вижу только системные окна — в Настройках → Спец. возможности выключи и снова включи Jarvis (после установки HyperOS часто режет доступ)." : "";
        return new ActionResult(false, "Экран открыт (" + CollectionsKt.joinToString$default(pkgs, ",", null, null, 0, null, null, 62, null) + "), но текстовых элементов не видно." + hint, null, false, 12, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean describeScreen$lambda$1(UiNode it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getEnabled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String describeScreen$lambda$2(UiNode node) {
        Object element$iv;
        Intrinsics.checkNotNullParameter(node, "node");
        boolean z = false;
        Iterable $this$firstOrNull$iv = CollectionsKt.listOf((Object[]) new String[]{node.getText(), node.getContentDescription()});
        Iterator it = $this$firstOrNull$iv.iterator();
        do {
            if (!it.hasNext()) {
                element$iv = null;
                break;
            }
            element$iv = it.next();
        } while (StringsKt.isBlank((String) element$iv));
        String str = (String) element$iv;
        if (str == null) {
            String it2 = StringsKt.substringAfterLast$default(node.getViewId(), IOUtils.DIR_SEPARATOR_UNIX, (String) null, 2, (Object) null);
            if (!StringsKt.isBlank(it2) && (node.getClickable() || node.getEditable() || StringsKt.contains((CharSequence) it2, (CharSequence) "search", true))) {
                z = true;
            }
            String str2 = z ? it2 : null;
            str = str2 == null ? "" : str2;
        }
        String label = str;
        return StringsKt.trim((CharSequence) label).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean describeScreen$lambda$3(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        int length = it.length();
        return 1 <= length && length < 81;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean describeScreen$lambda$4(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return new Regex("^\\d+$").matches(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean describeScreen$lambda$5(Set $noise, String label) {
        Intrinsics.checkNotNullParameter(label, "label");
        Set $this$any$iv = $noise;
        if (($this$any$iv instanceof Collection) && $this$any$iv.isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            String it = (String) element$iv;
            if (StringsKt.contains((CharSequence) label, (CharSequence) it, true)) {
                return true;
            }
        }
        return false;
    }

    private final ActionResult describeByOcr(int maxItems, String pkgShort) {
        Object objM392constructorimpl;
        String prefix;
        try {
            Result.Companion companion = Result.INSTANCE;
            ActionCoordinator actionCoordinator = this;
            objM392constructorimpl = Result.m392constructorimpl(ScreenOcr.recognizeScreen$default(ScreenOcr.INSTANCE, 0L, 1, null));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        List listEmptyList = CollectionsKt.emptyList();
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = listEmptyList;
        }
        List blocks = (List) objM392constructorimpl;
        if (blocks.isEmpty()) {
            return null;
        }
        List $this$map$iv = blocks;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            ScreenOcr.Block it = (ScreenOcr.Block) item$iv$iv;
            destination$iv$iv.add(StringsKt.replace$default(StringsKt.trim((CharSequence) it.getText()).toString(), '\n', ' ', false, 4, (Object) null));
        }
        Iterable $this$filter$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it2 = (String) element$iv$iv;
            int length = it2.length();
            boolean z = false;
            if (1 <= length && length < 81) {
                z = true;
            }
            if (z) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        List labels = CollectionsKt.take(CollectionsKt.distinct((List) destination$iv$iv2), maxItems);
        if (labels.isEmpty()) {
            return null;
        }
        if (pkgShort != null) {
            prefix = "Экран «" + pkgShort + "» (OCR): ";
        } else {
            prefix = "На экране (OCR): ";
        }
        return new ActionResult(true, prefix + CollectionsKt.joinToString$default(labels, "; ", null, null, 0, null, null, 62, null) + ".", CollectionsKt.joinToString$default(labels, "|", null, null, 0, null, null, 62, null), true);
    }

    public static /* synthetic */ ActionResult scrollUntil$default(ActionCoordinator actionCoordinator, String str, String str2, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str2 = "up";
        }
        if ((i2 & 4) != 0) {
            i = 8;
        }
        return actionCoordinator.scrollUntil(str, str2, i);
    }

    public final ActionResult scrollUntil(String query, String direction, int maxAttempts) throws InterruptedException {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(direction, "direction");
        if (!ScreenController.INSTANCE.isAvailable()) {
            return new ActionResult(false, "Служба спец. возможностей Jarvis не включена.", null, false, 12, null);
        }
        for (int i = 0; i < maxAttempts; i++) {
            int attempt = i;
            UiNode node = findBestNode$default(this, query, false, false, 6, null);
            if (node != null) {
                return new ActionResult(true, "Нашёл «" + node.getSearchable() + "» после прокрутки (" + attempt + ").", node.getText(), false, 8, null);
            }
            ScreenController.swipeDirection$default(ScreenController.INSTANCE, direction, 0.0f, 2, null);
            Thread.sleep(400L);
        }
        return new ActionResult(false, "Не нашёл «" + query + "» при прокрутке.", null, false, 12, null);
    }

    public static /* synthetic */ ActionResult tapFraction$default(ActionCoordinator actionCoordinator, float f, float f2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return actionCoordinator.tapFraction(f, f2, z);
    }

    public final ActionResult tapFraction(float fx, float fy, boolean longPress) {
        if (!ScreenController.INSTANCE.isAvailable()) {
            return new ActionResult(false, "Служба спец. возможностей Jarvis не включена.", null, false, 12, null);
        }
        DisplayMetrics dm = this.context.getResources().getDisplayMetrics();
        float x = dm.widthPixels * RangesKt.coerceIn(fx, 0.05f, 0.95f);
        float y = dm.heightPixels * RangesKt.coerceIn(fy, 0.05f, 0.95f);
        boolean ok = ScreenController.INSTANCE.tap(x, y, longPress);
        if (ok) {
            return new ActionResult(true, "Нажал по точке экрана.", null, false, 12, null);
        }
        return new ActionResult(false, "Тап не выполнен.", null, false, 12, null);
    }

    private final Pair<Float, Float> parseTapPoint(String query) {
        Float floatOrNull;
        String lowerCase = query.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String q = StringsKt.trim((CharSequence) lowerCase).toString();
        DisplayMetrics dm = this.context.getResources().getDisplayMetrics();
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "центр", false, 2, (Object) null) || Intrinsics.areEqual(q, "посередине")) {
            return TuplesKt.to(Float.valueOf(dm.widthPixels / 2.0f), Float.valueOf(dm.heightPixels / 2.0f));
        }
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "верх", false, 2, (Object) null) && StringsKt.contains$default((CharSequence) q, (CharSequence) "лев", false, 2, (Object) null)) {
            return TuplesKt.to(Float.valueOf(dm.widthPixels * 0.15f), Float.valueOf(dm.heightPixels * 0.12f));
        }
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "верх", false, 2, (Object) null) && StringsKt.contains$default((CharSequence) q, (CharSequence) "прав", false, 2, (Object) null)) {
            return TuplesKt.to(Float.valueOf(dm.widthPixels * 0.85f), Float.valueOf(dm.heightPixels * 0.12f));
        }
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "низ", false, 2, (Object) null) && StringsKt.contains$default((CharSequence) q, (CharSequence) "лев", false, 2, (Object) null)) {
            return TuplesKt.to(Float.valueOf(dm.widthPixels * 0.15f), Float.valueOf(dm.heightPixels * 0.88f));
        }
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "низ", false, 2, (Object) null) && StringsKt.contains$default((CharSequence) q, (CharSequence) "прав", false, 2, (Object) null)) {
            return TuplesKt.to(Float.valueOf(dm.widthPixels * 0.85f), Float.valueOf(dm.heightPixels * 0.88f));
        }
        MatchResult frac = Regex.find$default(new Regex("(?:x\\s*=\\s*)?([01](?:\\.\\d+)?)\\s*[;,\\s]\\s*(?:y\\s*=\\s*)?([01](?:\\.\\d+)?)"), q, 0, 2, null);
        if (frac == null || (floatOrNull = StringsKt.toFloatOrNull(frac.getGroupValues().get(1))) == null) {
            return null;
        }
        float fx = floatOrNull.floatValue();
        Float floatOrNull2 = StringsKt.toFloatOrNull(frac.getGroupValues().get(2));
        if (floatOrNull2 == null) {
            return null;
        }
        float fy = floatOrNull2.floatValue();
        return TuplesKt.to(Float.valueOf(dm.widthPixels * fx), Float.valueOf(dm.heightPixels * fy));
    }

    public final ActionResult findAndType(String query, String text) throws InterruptedException {
        Intrinsics.checkNotNullParameter(text, "text");
        if (!ScreenController.INSTANCE.isAvailable()) {
            return new ActionResult(false, "Служба спец. возможностей Jarvis не включена.", null, false, 12, null);
        }
        if (StringsKt.isBlank(text)) {
            return new ActionResult(false, "Нечего вводить.", null, false, 12, null);
        }
        String str = query;
        boolean ok = true;
        if (!(str == null || StringsKt.isBlank(str))) {
            waitForNode$default(this, query, 2000L, null, null, false, 28, null);
            UiNode field = findBestNode$default(this, query, true, false, 4, null);
            if (field != null) {
                ScreenController.tapCenter$default(ScreenController.INSTANCE, field, false, 2, null);
                Thread.sleep(200L);
                if (!ScreenController.INSTANCE.setTextOnNode(field, text) && !ScreenController.INSTANCE.setTextOnFocused(text)) {
                    ok = false;
                }
                if (ok) {
                    return new ActionResult(true, "Ввёл текст в «" + field.getSearchable() + "».", null, false, 12, null);
                }
                return new ActionResult(false, "Поле найдено, но ввод не удался.", null, false, 12, null);
            }
        }
        if (ScreenController.INSTANCE.setTextOnFocused(text)) {
            return new ActionResult(true, "Ввёл текст в активное поле.", null, false, 12, null);
        }
        return new ActionResult(false, "Нет активного поля ввода на экране.", null, false, 12, null);
    }

    public final ActionResult findAndTypeInBottomField(String text) throws InterruptedException {
        Object maxElem$iv;
        Intrinsics.checkNotNullParameter(text, "text");
        if (!ScreenController.INSTANCE.isAvailable()) {
            return new ActionResult(false, "Служба спец. возможностей Jarvis не включена.", null, false, 12, null);
        }
        if (StringsKt.isBlank(text)) {
            return new ActionResult(false, "Нечего вводить.", null, false, 12, null);
        }
        List listDumpActiveTree$default = ScreenController.dumpActiveTree$default(ScreenController.INSTANCE, 0, 1, null);
        if (listDumpActiveTree$default.isEmpty()) {
            listDumpActiveTree$default = ScreenController.dumpTree$default(ScreenController.INSTANCE, 0, 1, null);
        }
        List nodes = listDumpActiveTree$default;
        List $this$filter$iv = nodes;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            UiNode it = (UiNode) element$iv$iv;
            if (it.getEditable() && it.getEnabled()) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$maxByOrNull$iv = (List) destination$iv$iv;
        Iterator iterator$iv = $this$maxByOrNull$iv.iterator();
        if (iterator$iv.hasNext()) {
            maxElem$iv = iterator$iv.next();
            if (iterator$iv.hasNext()) {
                UiNode it2 = (UiNode) maxElem$iv;
                int maxValue$iv = it2.getBounds().centerY();
                do {
                    Object e$iv = iterator$iv.next();
                    UiNode it3 = (UiNode) e$iv;
                    int v$iv = it3.getBounds().centerY();
                    if (maxValue$iv < v$iv) {
                        maxElem$iv = e$iv;
                        maxValue$iv = v$iv;
                    }
                } while (iterator$iv.hasNext());
            }
        } else {
            maxElem$iv = null;
        }
        UiNode field = (UiNode) maxElem$iv;
        if (field != null) {
            ScreenController.tapCenter$default(ScreenController.INSTANCE, field, false, 2, null);
            Thread.sleep(250L);
            boolean ok = ScreenController.INSTANCE.setTextOnNode(field, text) || ScreenController.INSTANCE.setTextOnFocused(text);
            if (ok) {
                return new ActionResult(true, "Ввёл текст в поле сообщения.", null, false, 12, null);
            }
        }
        return findAndType(null, text);
    }

    public final ActionResult swipe(String direction) {
        Intrinsics.checkNotNullParameter(direction, "direction");
        if (!ScreenController.INSTANCE.isAvailable()) {
            return new ActionResult(false, "Служба спец. возможностей Jarvis не включена.", null, false, 12, null);
        }
        boolean ok = ScreenController.swipeDirection$default(ScreenController.INSTANCE, direction, 0.0f, 2, null);
        if (ok) {
            return new ActionResult(true, "Свайп: " + direction, null, false, 12, null);
        }
        return new ActionResult(false, "Свайп «" + direction + "» не выполнен.", null, false, 12, null);
    }

    public final ActionResult global(String action) {
        Intrinsics.checkNotNullParameter(action, "action");
        boolean z = true;
        if (!StringsKt.equals(action, "home", true)) {
            if (!ScreenController.INSTANCE.isAvailable()) {
                return new ActionResult(false, "Служба спец. возможностей Jarvis не включена.", null, false, 12, null);
            }
            boolean ok = ScreenController.INSTANCE.globalAction(action);
            if (ok) {
                return new ActionResult(true, "Выполнил действие «" + action + "».", null, false, 12, null);
            }
            return new ActionResult(false, "Неизвестное или недоступное действие «" + action + "».", null, false, 12, null);
        }
        try {
            Intent intent = new Intent("android.intent.action.MAIN").addCategory("android.intent.category.HOME").addFlags(268435456);
            Intrinsics.checkNotNullExpressionValue(intent, "addFlags(...)");
            try {
                this.context.startActivity(intent);
                if (ScreenController.INSTANCE.isAvailable()) {
                    ScreenController.INSTANCE.globalAction("home");
                }
                String str = "Нажал «Домой».";
                if (ScreenController.INSTANCE.isAvailable()) {
                    z = false;
                }
                return new ActionResult(true, str, null, z, 4, null);
            } catch (Throwable th) {
                t = th;
                String message = t.getMessage();
                if (message == null) {
                    message = "home failed";
                }
                return new ActionResult(false, message, null, false, 12, null);
            }
        } catch (Throwable th2) {
            t = th2;
        }
    }

    public static /* synthetic */ UiNode findBestNode$default(ActionCoordinator actionCoordinator, String str, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        return actionCoordinator.findBestNode(str, z, z2);
    }

    /* JADX WARN: Code duplicated, block: B:86:0x01ff  */
    public final UiNode findBestNode(String query, boolean preferEditable, boolean forClick) {
        List ordered;
        Iterable $this$filter$iv;
        Intrinsics.checkNotNullParameter(query, "query");
        List listDumpActiveTree$default = ScreenController.dumpActiveTree$default(ScreenController.INSTANCE, 0, 1, null);
        if (listDumpActiveTree$default.isEmpty()) {
            listDumpActiveTree$default = ScreenController.dumpTree$default(ScreenController.INSTANCE, 0, 1, null);
        }
        List nodes = listDumpActiveTree$default;
        if (nodes.isEmpty()) {
            return null;
        }
        final String q = StringsKt.trim((CharSequence) query).toString();
        if (StringsKt.isBlank(q)) {
            return null;
        }
        List<String> listExpandQuery = expandQuery(q);
        List $this$mapNotNull$iv = nodes;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            UiNode node = (UiNode) element$iv$iv$iv;
            Iterator<T> it = listExpandQuery.iterator();
            if (!it.hasNext()) {
                throw new NoSuchElementException();
            }
            int score = matchScore((String) it.next(), node);
            while (it.hasNext()) {
                int iMatchScore = matchScore((String) it.next(), node);
                if (score < iMatchScore) {
                    score = iMatchScore;
                }
            }
            Pair pair = score <= 0 ? null : TuplesKt.to(node, Integer.valueOf(score));
            if (pair != null) {
                destination$iv$iv.add(pair);
            }
        }
        List scored = (List) destination$iv$iv;
        if (scored.isEmpty()) {
            return null;
        }
        if (forClick || !preferEditable) {
            List $this$filter$iv2 = scored;
            Collection destination$iv$iv2 = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv2) {
                Pair it2 = (Pair) element$iv$iv;
                if ((((UiNode) it2.getFirst()).getEditable() || ((UiNode) it2.getFirst()).getFocused()) ? false : true) {
                    destination$iv$iv2.add(element$iv$iv);
                }
            }
            List nonEdit = (List) destination$iv$iv2;
            if (!nonEdit.isEmpty()) {
                scored = nonEdit;
            }
        }
        if (preferEditable) {
            final Comparator comparator = new Comparator() { // from class: com.jarvis.screencontrol.ActionCoordinator$findBestNode$$inlined$compareByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    Pair it3 = (Pair) t2;
                    Integer num = ((UiNode) it3.getFirst()).getEditable() ? (Comparable) 1 : (Comparable) 0;
                    Pair it4 = (Pair) t;
                    return ComparisonsKt.compareValues(num, ((UiNode) it4.getFirst()).getEditable() ? (Comparable) 1 : (Comparable) 0);
                }
            };
            ordered = CollectionsKt.sortedWith(scored, new Comparator() { // from class: com.jarvis.screencontrol.ActionCoordinator$findBestNode$$inlined$thenByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    int previousCompare = comparator.compare(t, t2);
                    if (previousCompare != 0) {
                        return previousCompare;
                    }
                    Pair it3 = (Pair) t2;
                    Comparable comparable = (Comparable) it3.getSecond();
                    Pair it4 = (Pair) t;
                    return ComparisonsKt.compareValues(comparable, (Comparable) it4.getSecond());
                }
            });
        } else if (forClick) {
            int screenH = this.context.getResources().getDisplayMetrics().heightPixels;
            float searchBarMaxY = screenH * 0.14f;
            String qLower = q.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(qLower, "toLowerCase(...)");
            boolean isChromeQuery = StringsKt.contains$default((CharSequence) qLower, (CharSequence) "поиск", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) qLower, (CharSequence) "search", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) qLower, (CharSequence) "назад", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) qLower, (CharSequence) "back", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) qLower, (CharSequence) "отправ", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) qLower, (CharSequence) "send", false, 2, (Object) null);
            if (isChromeQuery) {
                $this$filter$iv = scored;
            } else {
                Iterable $this$filter$iv3 = scored;
                Collection destination$iv$iv3 = new ArrayList();
                for (Object element$iv$iv2 : $this$filter$iv3) {
                    if (((float) ((UiNode) ((Pair) element$iv$iv2).getFirst()).getBounds().centerY()) > searchBarMaxY) {
                        destination$iv$iv3.add(element$iv$iv2);
                    }
                }
                $this$filter$iv = (List) destination$iv$iv3;
                if (((Collection) $this$filter$iv).isEmpty()) {
                    $this$filter$iv = scored;
                }
            }
            final Comparator comparator2 = new Comparator() { // from class: com.jarvis.screencontrol.ActionCoordinator$findBestNode$$inlined$compareByDescending$2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    Pair it3 = (Pair) t2;
                    Integer num = !((UiNode) it3.getFirst()).getEditable() ? (Comparable) 2 : (Comparable) 0;
                    Pair it4 = (Pair) t;
                    return ComparisonsKt.compareValues(num, !((UiNode) it4.getFirst()).getEditable() ? (Comparable) 2 : (Comparable) 0);
                }
            };
            final Comparator comparator3 = new Comparator() { // from class: com.jarvis.screencontrol.ActionCoordinator$findBestNode$$inlined$thenByDescending$2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    int previousCompare = comparator2.compare(t, t2);
                    if (previousCompare != 0) {
                        return previousCompare;
                    }
                    Pair it3 = (Pair) t2;
                    Integer numValueOf = Integer.valueOf(this.exactLabelMatch(q, (UiNode) it3.getFirst()));
                    Pair it4 = (Pair) t;
                    return ComparisonsKt.compareValues(numValueOf, Integer.valueOf(this.exactLabelMatch(q, (UiNode) it4.getFirst())));
                }
            };
            final Comparator comparator4 = new Comparator() { // from class: com.jarvis.screencontrol.ActionCoordinator$findBestNode$$inlined$thenBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    int previousCompare = comparator3.compare(t, t2);
                    if (previousCompare != 0) {
                        return previousCompare;
                    }
                    Pair it3 = (Pair) t;
                    Integer numValueOf = Integer.valueOf(((UiNode) it3.getFirst()).getBounds().top);
                    Pair it4 = (Pair) t2;
                    return ComparisonsKt.compareValues(numValueOf, Integer.valueOf(((UiNode) it4.getFirst()).getBounds().top));
                }
            };
            final Comparator comparator5 = new Comparator() { // from class: com.jarvis.screencontrol.ActionCoordinator$findBestNode$$inlined$thenByDescending$3
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    int previousCompare = comparator4.compare(t, t2);
                    if (previousCompare != 0) {
                        return previousCompare;
                    }
                    Pair it3 = (Pair) t2;
                    Integer num = ((UiNode) it3.getFirst()).getClickable() ? 1 : 0;
                    Pair it4 = (Pair) t;
                    return ComparisonsKt.compareValues(num, ((UiNode) it4.getFirst()).getClickable() ? 1 : 0);
                }
            };
            ordered = CollectionsKt.sortedWith($this$filter$iv, new Comparator() { // from class: com.jarvis.screencontrol.ActionCoordinator$findBestNode$$inlined$thenByDescending$4
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    int previousCompare = comparator5.compare(t, t2);
                    if (previousCompare != 0) {
                        return previousCompare;
                    }
                    Pair it3 = (Pair) t2;
                    Comparable comparable = (Comparable) it3.getSecond();
                    Pair it4 = (Pair) t;
                    return ComparisonsKt.compareValues(comparable, (Comparable) it4.getSecond());
                }
            });
        } else {
            final Comparator comparator6 = new Comparator() { // from class: com.jarvis.screencontrol.ActionCoordinator$findBestNode$$inlined$compareByDescending$3
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    Pair it3 = (Pair) t2;
                    Integer num = !((UiNode) it3.getFirst()).getEditable() ? (Comparable) 2 : (Comparable) 0;
                    Pair it4 = (Pair) t;
                    return ComparisonsKt.compareValues(num, !((UiNode) it4.getFirst()).getEditable() ? (Comparable) 2 : (Comparable) 0);
                }
            };
            final Comparator comparator7 = new Comparator() { // from class: com.jarvis.screencontrol.ActionCoordinator$findBestNode$$inlined$thenByDescending$5
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    int previousCompare = comparator6.compare(t, t2);
                    if (previousCompare != 0) {
                        return previousCompare;
                    }
                    Pair it3 = (Pair) t2;
                    Integer num = ((UiNode) it3.getFirst()).getClickable() ? 1 : 0;
                    Pair it4 = (Pair) t;
                    return ComparisonsKt.compareValues(num, ((UiNode) it4.getFirst()).getClickable() ? 1 : 0);
                }
            };
            final Comparator comparator8 = new Comparator() { // from class: com.jarvis.screencontrol.ActionCoordinator$findBestNode$$inlined$thenByDescending$6
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    int previousCompare = comparator7.compare(t, t2);
                    if (previousCompare != 0) {
                        return previousCompare;
                    }
                    Pair it3 = (Pair) t2;
                    Integer numValueOf = Integer.valueOf(this.exactLabelMatch(q, (UiNode) it3.getFirst()));
                    Pair it4 = (Pair) t;
                    return ComparisonsKt.compareValues(numValueOf, Integer.valueOf(this.exactLabelMatch(q, (UiNode) it4.getFirst())));
                }
            };
            final Comparator comparator9 = new Comparator() { // from class: com.jarvis.screencontrol.ActionCoordinator$findBestNode$$inlined$thenByDescending$7
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    int previousCompare = comparator8.compare(t, t2);
                    if (previousCompare != 0) {
                        return previousCompare;
                    }
                    Pair it3 = (Pair) t2;
                    Comparable comparable = (Comparable) it3.getSecond();
                    Pair it4 = (Pair) t;
                    return ComparisonsKt.compareValues(comparable, (Comparable) it4.getSecond());
                }
            };
            ordered = CollectionsKt.sortedWith(scored, new Comparator() { // from class: com.jarvis.screencontrol.ActionCoordinator$findBestNode$$inlined$thenByDescending$8
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    int previousCompare = comparator9.compare(t, t2);
                    if (previousCompare != 0) {
                        return previousCompare;
                    }
                    Pair it3 = (Pair) t2;
                    Integer numValueOf = Integer.valueOf(((UiNode) it3.getFirst()).getBounds().centerY());
                    Pair it4 = (Pair) t;
                    return ComparisonsKt.compareValues(numValueOf, Integer.valueOf(((UiNode) it4.getFirst()).getBounds().centerY()));
                }
            });
        }
        Pair pair2 = (Pair) CollectionsKt.firstOrNull(ordered);
        if (pair2 != null) {
            return (UiNode) pair2.getFirst();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int exactLabelMatch(String query, UiNode node) {
        boolean z;
        boolean z2;
        boolean z3;
        String lowerCase = query.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String q = StringsKt.trim((CharSequence) lowerCase).toString();
        Iterable $this$map$iv = CollectionsKt.listOf((Object[]) new String[]{node.getText(), node.getContentDescription()});
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        Iterator it = $this$map$iv.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object item$iv$iv = it.next();
            String lowerCase2 = ((String) item$iv$iv).toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
            String str = (String) SequencesKt.firstOrNull(StringsKt.lineSequence(StringsKt.trim((CharSequence) lowerCase2).toString()));
            String string = str != null ? StringsKt.trim((CharSequence) str).toString() : null;
            if (string == null) {
                string = "";
            }
            destination$iv$iv.add(string);
        }
        Iterable $this$filter$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            if (((String) element$iv$iv).length() > 0) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        Iterable labels = (List) destination$iv$iv2;
        Iterable $this$any$iv = labels;
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            Iterator it2 = $this$any$iv.iterator();
            while (true) {
                if (it2.hasNext()) {
                    Object element$iv = it2.next();
                    if (Intrinsics.areEqual((String) element$iv, q)) {
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
            return 3;
        }
        Iterable $this$any$iv2 = labels;
        if (!($this$any$iv2 instanceof Collection) || !((Collection) $this$any$iv2).isEmpty()) {
            Iterator it3 = $this$any$iv2.iterator();
            while (true) {
                if (it3.hasNext()) {
                    Object element$iv2 = it3.next();
                    String it4 = (String) element$iv2;
                    if (StringsKt.startsWith$default(it4, q, false, 2, (Object) null) && it4.length() <= q.length() + 8) {
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
            return 2;
        }
        Iterable $this$any$iv3 = labels;
        if (($this$any$iv3 instanceof Collection) && ((Collection) $this$any$iv3).isEmpty()) {
            z3 = false;
        } else {
            for (Object element$iv3 : $this$any$iv3) {
                if (StringsKt.equals((String) element$iv3, q, true)) {
                    z3 = true;
                }
            }
            z3 = false;
        }
        return z3 ? 3 : 0;
    }

    private final List<String> expandQuery(String query) {
        List extra;
        String lowerCase = query.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String q = StringsKt.trim((CharSequence) lowerCase).toString();
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "избран", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "saved", false, 2, (Object) null)) {
            extra = CollectionsKt.listOf((Object[]) new String[]{"Избранное", "избранное", "Избранные", "Saved Messages", "Saved messages", "Saved"});
        } else if (StringsKt.contains$default((CharSequence) q, (CharSequence) "поиск", false, 2, (Object) null) || Intrinsics.areEqual(q, "search") || StringsKt.contains$default((CharSequence) q, (CharSequence) "искать", false, 2, (Object) null)) {
            extra = CollectionsKt.listOf((Object[]) new String[]{"поиск", "search", "искать", "найти", "Search", "search_src_text", "search_bar"});
        } else if (StringsKt.contains$default((CharSequence) q, (CharSequence) "отправ", false, 2, (Object) null) || Intrinsics.areEqual(q, "send")) {
            extra = CollectionsKt.listOf((Object[]) new String[]{"отправить", "send", "отправка", "Send"});
        } else if (StringsKt.contains$default((CharSequence) q, (CharSequence) "сохран", false, 2, (Object) null) || Intrinsics.areEqual(q, "save")) {
            extra = CollectionsKt.listOf((Object[]) new String[]{"сохранить", "save", "Save", "готово", "done"});
        } else if (StringsKt.contains$default((CharSequence) q, (CharSequence) "отмен", false, 2, (Object) null) || Intrinsics.areEqual(q, "cancel")) {
            extra = CollectionsKt.listOf((Object[]) new String[]{"отмена", "cancel", "Cancel", "закрыть"});
        } else if (StringsKt.contains$default((CharSequence) q, (CharSequence) "назад", false, 2, (Object) null) || Intrinsics.areEqual(q, "back")) {
            extra = CollectionsKt.listOf((Object[]) new String[]{"назад", "back", "navigate up", "перейти вверх"});
        } else {
            extra = (StringsKt.contains$default((CharSequence) q, (CharSequence) "меню", false, 2, (Object) null) || Intrinsics.areEqual(q, "menu") || StringsKt.contains$default((CharSequence) q, (CharSequence) "ещё", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "еще", false, 2, (Object) null)) ? CollectionsKt.listOf((Object[]) new String[]{"меню", "menu", "ещё", "еще", JarvisActivity.MORE, "More options"}) : CollectionsKt.emptyList();
        }
        return CollectionsKt.distinct(CollectionsKt.plus((Collection) CollectionsKt.listOf(query), (Iterable) extra));
    }

    /* JADX WARN: Code duplicated, block: B:25:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:27:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:28:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:30:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:31:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:36:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:38:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:41:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:43:0x0107  */
    /* JADX WARN: Code duplicated, block: B:44:0x010a  */
    private final int matchScore(String query, UiNode node) {
        int score;
        boolean z = true;
        Iterable $this$filter$iv = CollectionsKt.listOf((Object[]) new String[]{node.getText(), node.getContentDescription(), StringsKt.substringAfterLast$default(node.getViewId(), IOUtils.DIR_SEPARATOR_UNIX, (String) null, 2, (Object) null)});
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            if (!StringsKt.isBlank(it)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List<String> candidates = (List) destination$iv$iv;
        if (candidates.isEmpty()) {
            return 0;
        }
        String qNorm = Transliterator.INSTANCE.compareKey(query);
        String qLower = query.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(qLower, "toLowerCase(...)");
        int best = 0;
        for (String c : candidates) {
            String cLower = c.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(cLower, "toLowerCase(...)");
            String cNorm = Transliterator.INSTANCE.compareKey(c);
            if (Intrinsics.areEqual(cLower, qLower)) {
                score = 100;
            } else if (Intrinsics.areEqual(cNorm, qNorm)) {
                if (qNorm.length() > 0 ? z : false) {
                    score = 95;
                } else if (StringsKt.startsWith$default(cLower, qLower, false, 2, (Object) null)) {
                    score = 85;
                } else if (StringsKt.contains$default((CharSequence) cLower, (CharSequence) qLower, false, 2, (Object) null)) {
                    score = 75;
                } else if (!StringsKt.contains$default((CharSequence) qLower, (CharSequence) cLower, false, 2, (Object) null)) {
                    if (!StringsKt.contains$default((CharSequence) cNorm, (CharSequence) qNorm, false, 2, (Object) null)) {
                        if (fuzzyClose(qLower, cLower)) {
                            score = 45;
                        } else {
                            score = 0;
                        }
                    } else if (fuzzyClose(qLower, cLower)) {
                        score = 45;
                    } else {
                        score = 0;
                    }
                } else if (!StringsKt.contains$default((CharSequence) cNorm, (CharSequence) qNorm, false, 2, (Object) null)) {
                    if (fuzzyClose(qLower, cLower)) {
                        score = 45;
                    } else {
                        score = 0;
                    }
                } else if (fuzzyClose(qLower, cLower)) {
                    score = 45;
                } else {
                    score = 0;
                }
            } else if (StringsKt.startsWith$default(cLower, qLower, false, 2, (Object) null)) {
                score = 85;
            } else if (StringsKt.contains$default((CharSequence) cLower, (CharSequence) qLower, false, 2, (Object) null)) {
                score = 75;
            } else if (!StringsKt.contains$default((CharSequence) qLower, (CharSequence) cLower, false, 2, (Object) null) && cLower.length() >= 3) {
                score = 60;
            } else if (!StringsKt.contains$default((CharSequence) cNorm, (CharSequence) qNorm, false, 2, (Object) null) && qNorm.length() >= 3) {
                score = 55;
            } else if (fuzzyClose(qLower, cLower)) {
                score = 45;
            } else {
                score = 0;
            }
            best = Math.max(best, score);
            z = true;
        }
        return best;
    }

    private final boolean fuzzyClose(String a, String b) {
        return a.length() >= 3 && b.length() >= 3 && Math.abs(a.length() - b.length()) <= 3 && levenshtein(a, b) <= Math.min(2, Math.min(a.length(), b.length()) / 3);
    }

    private final int levenshtein(String a, String b) {
        int length = b.length() + 1;
        int[] dp = new int[length];
        for (int i = 0; i < length; i++) {
            dp[i] = i;
        }
        int i2 = 1;
        int length2 = a.length();
        if (1 <= length2) {
            while (true) {
                int prev = dp[0];
                dp[0] = i2;
                int j = 1;
                int length3 = b.length();
                if (1 <= length3) {
                    while (true) {
                        int tmp = dp[j];
                        dp[j] = Math.min(dp[j] + 1, Math.min(dp[j - 1] + 1, (a.charAt(i2 + (-1)) == b.charAt(j + (-1)) ? 0 : 1) + prev));
                        prev = tmp;
                        if (j == length3) {
                            break;
                        }
                        j++;
                    }
                }
                if (i2 == length2) {
                    break;
                }
                i2++;
            }
        }
        return dp[b.length()];
    }

    public final ActionResult tryIntentFallback(String query) {
        Intent intent;
        Intrinsics.checkNotNullParameter(query, "query");
        String q = query.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(q, "toLowerCase(...)");
        if ((StringsKt.contains$default((CharSequence) q, (CharSequence) "сеть", false, 2, (Object) null) && (StringsKt.contains$default((CharSequence) q, (CharSequence) "и", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "wi", false, 2, (Object) null) || q.length() < 8)) || StringsKt.contains$default((CharSequence) q, (CharSequence) "wi-fi", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) EventMatcher.WIFI, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "вайфай", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "вай-фай", false, 2, (Object) null) || ((StringsKt.contains$default((CharSequence) q, (CharSequence) "wi", false, 2, (Object) null) && StringsKt.contains$default((CharSequence) q, (CharSequence) "fi", false, 2, (Object) null)) || ((StringsKt.contains$default((CharSequence) q, (CharSequence) "настрой", false, 2, (Object) null) && StringsKt.contains$default((CharSequence) q, (CharSequence) "wi", false, 2, (Object) null)) || Intrinsics.areEqual(q, "сеть") || Intrinsics.areEqual(q, "интернет")))) {
            intent = new Intent("android.settings.WIFI_SETTINGS");
        } else if (StringsKt.contains$default((CharSequence) q, (CharSequence) "bluetooth", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "блютус", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "синезуб", false, 2, (Object) null)) {
            intent = new Intent("android.settings.BLUETOOTH_SETTINGS");
        } else if (StringsKt.contains$default((CharSequence) q, (CharSequence) "самолёт", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "самолет", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "airplane", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "авиарежим", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "режим полёт", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "режим полет", false, 2, (Object) null)) {
            intent = new Intent("android.settings.AIRPLANE_MODE_SETTINGS");
        } else if (StringsKt.contains$default((CharSequence) q, (CharSequence) "яркост", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "дисплей", false, 2, (Object) null) || (StringsKt.contains$default((CharSequence) q, (CharSequence) "экран", false, 2, (Object) null) && StringsKt.contains$default((CharSequence) q, (CharSequence) "настрой", false, 2, (Object) null))) {
            intent = new Intent("android.settings.DISPLAY_SETTINGS");
        } else if (StringsKt.contains$default((CharSequence) q, (CharSequence) "звук", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "громкост", false, 2, (Object) null)) {
            intent = new Intent("android.settings.SOUND_SETTINGS");
        } else if (StringsKt.contains$default((CharSequence) q, (CharSequence) "локац", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "геолокац", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "gps", false, 2, (Object) null)) {
            intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
        } else if (StringsKt.contains$default((CharSequence) q, (CharSequence) "приложен", false, 2, (Object) null) && StringsKt.contains$default((CharSequence) q, (CharSequence) "настрой", false, 2, (Object) null)) {
            intent = new Intent("android.settings.APPLICATION_SETTINGS");
        } else if (StringsKt.contains$default((CharSequence) q, (CharSequence) "настройк", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) q, (CharSequence) "wi", false, 2, (Object) null)) {
            intent = new Intent("android.settings.SETTINGS");
        } else if (StringsKt.contains$default((CharSequence) q, (CharSequence) "звонилк", false, 2, (Object) null) || (StringsKt.contains$default((CharSequence) q, (CharSequence) "телефон", false, 2, (Object) null) && StringsKt.contains$default((CharSequence) q, (CharSequence) "открой", false, 2, (Object) null))) {
            intent = new Intent("android.intent.action.DIAL");
        } else {
            intent = null;
        }
        if (intent == null) {
            return null;
        }
        try {
            intent.addFlags(268435456);
            this.context.startActivity(intent);
            return new ActionResult(true, "Открыл системный экран для «" + query + "».", null, true, 4, null);
        } catch (Throwable t) {
            return new ActionResult(false, "Не удалось открыть настройки: " + t.getMessage(), null, false, 12, null);
        }
    }

    public final ActionResult openWifiSettings() {
        ActionResult actionResultTryIntentFallback = tryIntentFallback(EventMatcher.WIFI);
        if (actionResultTryIntentFallback != null) {
            return actionResultTryIntentFallback;
        }
        return new ActionResult(false, "Не удалось открыть Wi‑Fi", null, false, 12, null);
    }

    public final ActionResult openAppDetails(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        try {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + packageName)).addFlags(268435456);
            Intrinsics.checkNotNullExpressionValue(intent, "addFlags(...)");
            this.context.startActivity(intent);
            return new ActionResult(true, "Открыл сведения о приложении " + packageName + ".", null, true, 4, null);
        } catch (Throwable t) {
            String message = t.getMessage();
            if (message == null) {
                message = "fail";
            }
            return new ActionResult(false, message, null, false, 12, null);
        }
    }
}
