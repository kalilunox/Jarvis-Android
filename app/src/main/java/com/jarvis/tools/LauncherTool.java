package com.jarvis.tools;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import com.google.android.gms.actions.SearchIntents;
import com.jarvis.core.common.Tool;
import com.jarvis.core.common.model.OnlineRequirement;
import com.jarvis.core.common.model.ParameterType;
import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.common.model.ToolParameter;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import com.jarvis.core.nlp.Transliterator;
import com.jarvis.screencontrol.ActionCoordinator;
import com.jarvis.screencontrol.ScreenController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.concurrent.AbstractCircuitBreaker;

/* JADX INFO: compiled from: LauncherTool.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\u0018\u0000 (2\u00020\u0001:\u0001(B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u001a\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0002J\u0010\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u001a\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0002J\u001a\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0002J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u001c\u001a\u00020\u0016J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001c\u001a\u00020\u0016H\u0002J0\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\u00162\u0006\u0010#\u001a\u00020\u00162\u0006\u0010$\u001a\u00020\u00162\u0006\u0010%\u001a\u00020\u0016H\u0002J\u0010\u0010&\u001a\u00020\u00162\u0006\u0010'\u001a\u00020\u0016H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006)"}, d2 = {"Lcom/jarvis/tools/LauncherTool;", "Lcom/jarvis/core/common/Tool;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "coordinator", "Lcom/jarvis/screencontrol/ActionCoordinator;", "getCoordinator", "()Lcom/jarvis/screencontrol/ActionCoordinator;", "coordinator$delegate", "Lkotlin/Lazy;", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "openApp", "requestId", "", "queryRaw", "listApps", "minimizeApp", "forceStopApp", "resolvePackage", SearchIntents.EXTRA_QUERY, "findByLabel", "Landroid/content/Intent;", "matchScore", "", "rawQuery", "capitalized", "normalizedQuery", "label", "normalizedLabel", "normalizeName", "value", "Companion", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LauncherTool implements Tool {
    private static final Companion Companion = new Companion(null);
    private static final Set<String> OPEN_VERBS = SetsKt.setOf((Object[]) new String[]{"открой", "запусти", "открыть", "запустить", "приложение"});
    private final Context context;

    /* JADX INFO: renamed from: coordinator$delegate, reason: from kotlin metadata */
    private final Lazy coordinator;
    private final ToolDefinition definition;

    public LauncherTool(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.coordinator = LazyKt.lazy(new Function0() { // from class: com.jarvis.tools.LauncherTool$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return LauncherTool.coordinator_delegate$lambda$0(this.f$0);
            }
        });
        this.definition = new ToolDefinition("launcher", "Приложения", "Открыть, свернуть (Home), принудительно остановить или перечислить приложения", CollectionsKt.listOf((Object[]) new ToolParameter[]{new ToolParameter("action", "open, list, minimize/close или force_stop", ParameterType.ENUM, false, CollectionsKt.listOf((Object[]) new String[]{AbstractCircuitBreaker.PROPERTY_NAME, "list", "minimize", "close", "force_stop"})), new ToolParameter("app", "Название приложения (для open/minimize/force_stop)", ParameterType.STRING, false, null, 16, null)}), RiskLevel.SAFE, CollectionsKt.listOf("android.permission.KILL_BACKGROUND_PROCESSES"), OnlineRequirement.LOCAL_ONLY, null, 128, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ActionCoordinator coordinator_delegate$lambda$0(LauncherTool this$0) {
        return new ActionCoordinator(this$0.context);
    }

    private final ActionCoordinator getCoordinator() {
        return (ActionCoordinator) this.coordinator.getValue();
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:10:0x0032  */
    /* JADX WARN: Code duplicated, block: B:11:0x0035  */
    /* JADX WARN: Code duplicated, block: B:44:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:8:0x0028  */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0047, code lost:
    
        if (r0.equals("forcestop") == false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0050, code lost:
    
        if (r0.equals("force_stop") == false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0059, code lost:
    
        if (r0.equals("close") == false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0072, code lost:
    
        if (r0.equals("kill") == false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0075, code lost:
    
        r1 = r6.getRequestId();
        r2 = r6.getArguments().get("app");
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0081, code lost:
    
        if (r2 == null) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0083, code lost:
    
        r4 = r2.toString();
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0092, code lost:
    
        if (r0.equals("minimize") == false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0095, code lost:
    
        r1 = r6.getRequestId();
        r2 = r6.getArguments().get("app");
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00a1, code lost:
    
        if (r2 == null) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00a3, code lost:
    
        r4 = r2.toString();
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:?, code lost:
    
        return forceStopApp(r1, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:?, code lost:
    
        return minimizeApp(r1, r4);
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.jarvis.core.common.Tool
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.jarvis.core.common.model.ToolResult execute(com.jarvis.core.common.model.ToolCall r6) {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jarvis.tools.LauncherTool.execute(com.jarvis.core.common.model.ToolCall):com.jarvis.core.common.model.ToolResult");
    }

    private final ToolResult openApp(String requestId, String queryRaw) {
        String pkg;
        String it;
        String str = null;
        if (queryRaw != null) {
            String lowerCase = queryRaw.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (lowerCase != null && (it = StringsKt.trim((CharSequence) lowerCase).toString()) != null && !OPEN_VERBS.contains(it)) {
                str = it;
            }
        }
        String query = str;
        String str2 = query;
        if (str2 == null || StringsKt.isBlank(str2)) {
            return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "Что открыть, сэр?", null, 0L, 48, null);
        }
        try {
            PackageManager pm = this.context.getPackageManager();
            Intent launchIntent = pm.getLaunchIntentForPackage(query);
            Intent intent = launchIntent == null ? findByLabel(query) : launchIntent;
            if (intent == null) {
                return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.FAILED, "Приложение «" + query + "» не найдено.", null, 0L, 48, null);
            }
            ComponentName component = intent.getComponent();
            if (component == null || (pkg = component.getPackageName()) == null) {
                pkg = "приложение";
            }
            intent.addFlags(268435456);
            this.context.startActivity(intent);
            if (ScreenController.INSTANCE.isAvailable()) {
                getCoordinator().waitForPackage(pkg, 3500L);
            } else {
                Thread.sleep(800L);
            }
            return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.SUCCESS, "Открыл.", MapsKt.mapOf(TuplesKt.to("app", pkg)), 0L, 32, null);
        } catch (Throwable t) {
            return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.FAILED, t.getMessage(), null, 0L, 48, null);
        }
    }

    private final ToolResult listApps(String requestId) {
        PackageManager pm = this.context.getPackageManager();
        Intent main = new Intent("android.intent.action.MAIN").addCategory("android.intent.category.LAUNCHER");
        Intrinsics.checkNotNullExpressionValue(main, "addCategory(...)");
        Iterable iterableQueryIntentActivities = pm.queryIntentActivities(main, 0);
        Intrinsics.checkNotNullExpressionValue(iterableQueryIntentActivities, "queryIntentActivities(...)");
        Iterable $this$mapNotNull$iv = iterableQueryIntentActivities;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            ResolveInfo it = (ResolveInfo) element$iv$iv$iv;
            CharSequence charSequenceLoadLabel = it.loadLabel(pm);
            String string = charSequenceLoadLabel != null ? charSequenceLoadLabel.toString() : null;
            if (string != null) {
                destination$iv$iv.add(string);
            }
        }
        List labels = CollectionsKt.sorted(CollectionsKt.distinct((List) destination$iv$iv));
        return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.SUCCESS, "Установлено приложений: " + labels.size() + ". Например: " + CollectionsKt.joinToString$default(CollectionsKt.take(labels, 12), ", ", null, null, 0, null, null, 62, null), MapsKt.mapOf(TuplesKt.to("count", Integer.valueOf(labels.size()))), 0L, 32, null);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x003f  */
    private final ToolResult minimizeApp(String requestId, String queryRaw) {
        Intent home = new Intent("android.intent.action.MAIN").addCategory("android.intent.category.HOME").addFlags(268435456);
        Intrinsics.checkNotNullExpressionValue(home, "addFlags(...)");
        this.context.startActivity(home);
        if (ScreenController.INSTANCE.isAvailable()) {
            ScreenController.INSTANCE.globalAction("home");
        }
        if (queryRaw != null) {
            if ((!StringsKt.isBlank(queryRaw) ? queryRaw : null) == null) {
            }
        }
        return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.SUCCESS, "Свернул.", MapsKt.mapOf(TuplesKt.to("action", "minimize")), 0L, 32, null);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x001e  */
    private final ToolResult forceStopApp(String requestId, String queryRaw) {
        String string;
        if (queryRaw != null) {
            String lowerCase = queryRaw.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (lowerCase != null) {
                string = StringsKt.trim((CharSequence) lowerCase).toString();
            } else {
                string = null;
            }
        } else {
            string = null;
        }
        String query = string;
        String str = query;
        if (str == null || StringsKt.isBlank(str)) {
            return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "Какое приложение принудительно закрыть?", null, 0L, 48, null);
        }
        String pkg = resolvePackage(query);
        if (pkg == null) {
            return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.FAILED, "Приложение «" + query + "» не найдено.", null, 0L, 48, null);
        }
        try {
            Object systemService = this.context.getSystemService("activity");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
            ActivityManager am = (ActivityManager) systemService;
            am.killBackgroundProcesses(pkg);
            Intent details = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            details.setData(Uri.parse("package:" + pkg));
            details.addFlags(268435456);
            this.context.startActivity(details);
            if (ScreenController.INSTANCE.isAvailable()) {
                getCoordinator().waitForPackage(pkg, 2500L);
            }
            return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.SUCCESS, "Остановил фоновые процессы «" + pkg + "» и открыл сведения о приложении. Полный force-stop — кнопкой «Остановить» в настройках (ограничение Android).", MapsKt.mapOf(TuplesKt.to("action", "force_stop"), TuplesKt.to("app", pkg)), 0L, 32, null);
        } catch (Throwable t) {
            return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.FAILED, t.getMessage(), null, 0L, 48, null);
        }
    }

    public final String resolvePackage(String query) {
        ComponentName component;
        Intrinsics.checkNotNullParameter(query, "query");
        PackageManager pm = this.context.getPackageManager();
        if (pm.getLaunchIntentForPackage(query) != null) {
            return query;
        }
        Intent intent = findByLabel(query);
        if (intent == null || (component = intent.getComponent()) == null) {
            return null;
        }
        return component.getPackageName();
    }

    private final Intent findByLabel(final String query) {
        Object objM392constructorimpl;
        final String appName;
        Object maxElem$iv;
        Intent launchIntentForPackage;
        try {
            Result.Companion companion = Result.INSTANCE;
            final LauncherTool $this$findByLabel_u24lambda_u240 = this;
            final PackageManager pm = $this$findByLabel_u24lambda_u240.context.getPackageManager();
            Intent main = new Intent("android.intent.action.MAIN").addCategory("android.intent.category.LAUNCHER");
            Intrinsics.checkNotNullExpressionValue(main, "addCategory(...)");
            if (query.length() > 0) {
                StringBuilder sb = new StringBuilder();
                char it = query.charAt(0);
                String strValueOf = String.valueOf(it);
                Intrinsics.checkNotNull(strValueOf, "null cannot be cast to non-null type java.lang.String");
                String upperCase = strValueOf.toUpperCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                StringBuilder sbAppend = sb.append((Object) upperCase);
                String strSubstring = query.substring(1);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                appName = sbAppend.append(strSubstring).toString();
            } else {
                appName = query;
            }
            final String q = $this$findByLabel_u24lambda_u240.normalizeName(query);
            List<ResolveInfo> listQueryIntentActivities = pm.queryIntentActivities(main, 0);
            Intrinsics.checkNotNullExpressionValue(listQueryIntentActivities, "queryIntentActivities(...)");
            Sequence $this$maxByOrNull$iv = SequencesKt.filter(SequencesKt.map(SequencesKt.map(CollectionsKt.asSequence(listQueryIntentActivities), new Function1() { // from class: com.jarvis.tools.LauncherTool$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return LauncherTool.findByLabel$lambda$0$1(pm, $this$findByLabel_u24lambda_u240, (ResolveInfo) obj);
                }
            }), new Function1() { // from class: com.jarvis.tools.LauncherTool$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return LauncherTool.findByLabel$lambda$0$2(this.f$0, query, appName, q, (Triple) obj);
                }
            }), new Function1() { // from class: com.jarvis.tools.LauncherTool$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(LauncherTool.findByLabel$lambda$0$3((Triple) obj));
                }
            });
            Iterator iterator$iv = $this$maxByOrNull$iv.iterator();
            if (iterator$iv.hasNext()) {
                maxElem$iv = iterator$iv.next();
                if (iterator$iv.hasNext()) {
                    Triple it2 = (Triple) maxElem$iv;
                    int maxValue$iv = ((Number) it2.getThird()).intValue();
                    do {
                        Object e$iv = iterator$iv.next();
                        Triple it3 = (Triple) e$iv;
                        int v$iv = ((Number) it3.getThird()).intValue();
                        if (maxValue$iv < v$iv) {
                            maxElem$iv = e$iv;
                            maxValue$iv = v$iv;
                        }
                    } while (iterator$iv.hasNext());
                }
            } else {
                maxElem$iv = null;
            }
            Triple triple = (Triple) maxElem$iv;
            if (triple != null) {
                String pkg = (String) triple.component1();
                launchIntentForPackage = pm.getLaunchIntentForPackage(pkg);
            } else {
                launchIntentForPackage = null;
            }
            objM392constructorimpl = Result.m392constructorimpl(launchIntentForPackage);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        return (Intent) (Result.m398isFailureimpl(objM392constructorimpl) ? null : objM392constructorimpl);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Triple findByLabel$lambda$0$1(PackageManager $pm, LauncherTool $this_runCatching, ResolveInfo info) {
        String label;
        String pkg = info.activityInfo.packageName;
        CharSequence charSequenceLoadLabel = info.loadLabel($pm);
        if (charSequenceLoadLabel == null || (label = charSequenceLoadLabel.toString()) == null) {
            label = "";
        }
        return new Triple(pkg, label, $this_runCatching.normalizeName(label));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Triple findByLabel$lambda$0$2(LauncherTool $this_runCatching, String $query, String $appName, String $q, Triple triple) {
        Intrinsics.checkNotNullParameter(triple, "<destruct>");
        String pkg = (String) triple.component1();
        String label = (String) triple.component2();
        String norm = (String) triple.component3();
        int score = $this_runCatching.matchScore($query, $appName, $q, label, norm);
        return new Triple(pkg, label, Integer.valueOf(score));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean findByLabel$lambda$0$3(Triple it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return ((Number) it.getThird()).intValue() > 0;
    }

    private final int matchScore(String rawQuery, String capitalized, String normalizedQuery, String label, String normalizedLabel) {
        if (StringsKt.isBlank(label)) {
            return 0;
        }
        String lc = label.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lc, "toLowerCase(...)");
        String qlc = rawQuery.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(qlc, "toLowerCase(...)");
        if (Intrinsics.areEqual(lc, qlc)) {
            return 100;
        }
        if (Intrinsics.areEqual(normalizedLabel, normalizedQuery)) {
            if (normalizedQuery.length() > 0) {
                return 95;
            }
        }
        if (StringsKt.startsWith$default(lc, qlc, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) lc, (CharSequence) qlc, false, 2, (Object) null)) {
            return 80;
        }
        if (StringsKt.contains$default((CharSequence) normalizedLabel, (CharSequence) normalizedQuery, false, 2, (Object) null)) {
            if (normalizedQuery.length() > 0) {
                return 70;
            }
        }
        return StringsKt.startsWith(label, capitalized, true) ? 60 : 0;
    }

    private final String normalizeName(String value) {
        return Transliterator.INSTANCE.compareKey(value);
    }

    /* JADX INFO: compiled from: LauncherTool.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/jarvis/tools/LauncherTool$Companion;", "", "<init>", "()V", "OPEN_VERBS", "", "", "getOPEN_VERBS", "()Ljava/util/Set;", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Set<String> getOPEN_VERBS() {
            return LauncherTool.OPEN_VERBS;
        }
    }
}
