package com.jarvis.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.actions.SearchIntents;
import com.jarvis.core.agent.AgentShortcut;
import com.jarvis.core.agent.AgentShortcuts;
import com.jarvis.core.agent.AgentStep;
import com.jarvis.core.agent.AppAgentRules;
import com.jarvis.core.agent.CompactItem;
import com.jarvis.core.agent.RawNode;
import com.jarvis.core.agent.ScreenItem;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolResultStatus;
import com.jarvis.core.notes.Places;
import com.jarvis.screencontrol.ScreenController;
import com.jarvis.screencontrol.UiNode;
import com.jarvis.tools.LauncherTool;
import com.jarvis.tools.SystemSettingTool;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.AbstractCircuitBreaker;

/* JADX INFO: compiled from: AppAgentRunner.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u0000 *2\u00020\u0001:\u0002)*B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006J\u0012\u0010\r\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\u0006H\u0002J\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0006H\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0006H\u0002J\u0010\u0010\u0015\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H\u0002J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u001e\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 H\u0002J\u001e\u0010\"\u001a\u00020#2\u0006\u0010\u001d\u001a\u00020\u001e2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001b0 H\u0002J\u0010\u0010%\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u0006H\u0002J\u0010\u0010'\u001a\u00020#2\u0006\u0010(\u001a\u00020\u0006H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/jarvis/app/AppAgentRunner;", "", "context", "Landroid/content/Context;", "ask", "Lkotlin/Function1;", "", "<init>", "(Landroid/content/Context;Lkotlin/jvm/functions/Function1;)V", "launcher", "Lcom/jarvis/tools/LauncherTool;", "start", "goal", "shortcut", AbstractCircuitBreaker.PROPERTY_NAME, "intent", "Landroid/content/Intent;", "reply", "settings", "", "name", Places.WORK, "askTwice", "prompt", "raw", "Lcom/jarvis/core/agent/RawNode;", "node", "Lcom/jarvis/screencontrol/UiNode;", "describe", "step", "Lcom/jarvis/core/agent/AgentStep;", "screen", "", "Lcom/jarvis/core/agent/ScreenItem;", "perform", "", "nodes", "confirmed", "question", "show", VoiceService.EXTRA_TEXT, "Answer", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AppAgentRunner {
    private static final long CONFIRM_WAIT_MS = 10000;
    private static final int MAX_NODES = 300;
    private static final int MAX_QUERY = 40;
    private static final int MAX_REJECTED_DONE = 2;
    private static final long RETRY_PAUSE_MS = 3000;
    private static final long SPEECH_MARGIN_MS = 6000;
    private static final long START_PAUSE_MS = 1500;
    private static final long STEP_PAUSE_MS = 1500;
    private static final String TAG = "Jarvis.Agent";
    private static final String YOUTUBE = "com.google.android.youtube";
    private static volatile boolean cancelled;
    private static volatile Answer pending;
    private final Function1<String, String> ask;
    private final Context context;
    private final LauncherTool launcher;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Regex STOP = new Regex("^(стоп|хватит|отмена|отмени|останови(сь)?|прекрати)\\b");
    private static final Regex YES = new Regex("^(да|ага|нажми|подтверждаю|давай)\\b");
    private static final AtomicBoolean running = new AtomicBoolean(false);

    /* JADX WARN: Multi-variable type inference failed */
    public AppAgentRunner(Context context, Function1<? super String, String> ask) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(ask, "ask");
        this.context = context;
        this.ask = ask;
        this.launcher = new LauncherTool(this.context);
    }

    public final String start(final String goal) {
        Intrinsics.checkNotNullParameter(goal, "goal");
        String it = shortcut(goal);
        if (it != null) {
            return it;
        }
        if (!ScreenController.INSTANCE.isAvailable()) {
            return "Для этого нужна служба спец. возможностей Jarvis, сэр.";
        }
        if (!running.compareAndSet(false, true)) {
            return "Я уже занят задачей, сэр. Скажите «стоп», чтобы прервать.";
        }
        cancelled = false;
        new Thread(new Runnable() { // from class: com.jarvis.app.AppAgentRunner$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AppAgentRunner.start$lambda$1(this.f$0, goal);
            }
        }, "jarvis-agent").start();
        return "Приступаю, сэр.";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void start$lambda$1(AppAgentRunner this$0, String $goal) {
        Object objM392constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(this$0.work($goal));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        Throwable it = Result.m395exceptionOrNullimpl(objM392constructorimpl);
        if (it != null) {
            Log.e(TAG, "agent failed", it);
            VoiceHealthLog.INSTANCE.write(this$0.context, "agent error " + it.getClass().getSimpleName());
            objM392constructorimpl = "Не получилось, сэр: произошла ошибка.";
        }
        String reply = (String) objM392constructorimpl;
        running.set(false);
        pending = null;
        VoiceHealthLog.INSTANCE.write(this$0.context, "agent finished");
        VoiceController.speak$default(VoiceController.INSTANCE, reply, null, null, 6, null);
    }

    private final String shortcut(String goal) {
        Object objM392constructorimpl;
        AgentShortcut found = AgentShortcuts.INSTANCE.match(goal);
        if (found instanceof AgentShortcut.YouTubeSearch) {
            try {
                Result.Companion companion = Result.INSTANCE;
                AppAgentRunner $this$shortcut_u24lambda_u240 = this;
                Intent search = new Intent("android.intent.action.SEARCH");
                search.setPackage(YOUTUBE);
                search.putExtra(SearchIntents.EXTRA_QUERY, ((AgentShortcut.YouTubeSearch) found).getQuery());
                search.addFlags(268435456);
                $this$shortcut_u24lambda_u240.context.startActivity(search);
                VoiceHealthLog.INSTANCE.write($this$shortcut_u24lambda_u240.context, "agent shortcut youtube");
                objM392constructorimpl = Result.m392constructorimpl("Открыл результаты, сэр.");
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
            }
            return (String) (Result.m398isFailureimpl(objM392constructorimpl) ? null : objM392constructorimpl);
        }
        if (found instanceof AgentShortcut.Setting) {
            boolean done = settings(((AgentShortcut.Setting) found).getName());
            if (done) {
                VoiceHealthLog.INSTANCE.write(this.context, "agent shortcut setting " + ((AgentShortcut.Setting) found).getName());
            }
            if (done) {
                return "Открыл настройки, сэр.";
            }
            return null;
        }
        if (found instanceof AgentShortcut.MapSearch) {
            return open(new Intent("android.intent.action.VIEW", Uri.parse("geo:0,0?q=" + Uri.encode(((AgentShortcut.MapSearch) found).getPlace()))), "Открыл карту, сэр.");
        }
        if (found instanceof AgentShortcut.WebSearch) {
            return open(new Intent("android.intent.action.VIEW", Uri.parse("https://yandex.ru/search/?text=" + Uri.encode(((AgentShortcut.WebSearch) found).getQuery()))), "Открыл поиск, сэр.");
        }
        if (found == null) {
            return null;
        }
        throw new NoWhenBranchMatchedException();
    }

    private final String open(Intent intent, String reply) {
        Object objM392constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            AppAgentRunner $this$open_u24lambda_u240 = this;
            $this$open_u24lambda_u240.context.startActivity(intent.addFlags(268435456));
            VoiceHealthLog.INSTANCE.write($this$open_u24lambda_u240.context, "agent shortcut view");
            objM392constructorimpl = Result.m392constructorimpl(reply);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = null;
        }
        return (String) objM392constructorimpl;
    }

    private final boolean settings(String name) {
        Object objM392constructorimpl;
        boolean z = true;
        ToolCall call = new ToolCall(null, "agent", "system.setting", MapsKt.mapOf(TuplesKt.to("name", name), TuplesKt.to("value", AbstractCircuitBreaker.PROPERTY_NAME)), 1, null);
        try {
            Result.Companion companion = Result.INSTANCE;
            AppAgentRunner $this$settings_u24lambda_u240 = this;
            if (new SystemSettingTool($this$settings_u24lambda_u240.context).execute(call).getStatus() != ToolResultStatus.SUCCESS) {
                z = false;
            }
            objM392constructorimpl = Result.m392constructorimpl(Boolean.valueOf(z));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = false;
        }
        return ((Boolean) objM392constructorimpl).booleanValue();
    }

    private final String work(String goal) throws InterruptedException {
        AppAgentRules.LoopGuard guard;
        long j;
        Class<?> cls;
        Class<?> cls2;
        ArrayList history = new ArrayList();
        AppAgentRules.LoopGuard guard2 = new AppAgentRules.LoopGuard();
        HashSet queries = new HashSet();
        List<ScreenItem> listEmptyList = CollectionsKt.emptyList();
        if (Intrinsics.areEqual(ScreenController.INSTANCE.activePackageName(), this.context.getPackageName())) {
            ScreenController.INSTANCE.globalAction("home");
        }
        Thread.sleep(1500L);
        int number = 1;
        int rejected = 0;
        boolean typed = false;
        AgentStep lastStep = null;
        List<ScreenItem> list = listEmptyList;
        while (number < 11) {
            if (cancelled) {
                return "Остановился, сэр.";
            }
            String strActivePackageName = ScreenController.INSTANCE.activePackageName();
            if (strActivePackageName == null) {
                strActivePackageName = "";
            }
            String packageName = strActivePackageName;
            if (AppAgentRules.INSTANCE.forbiddenApp(packageName)) {
                return "В банковских приложениях я не работаю, сэр.";
            }
            List<UiNode> listVisibleNodes = ScreenController.INSTANCE.visibleNodes(300);
            AppAgentRules appAgentRules = AppAgentRules.INSTANCE;
            List<UiNode> $this$map$iv = listVisibleNodes;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                UiNode p0 = (UiNode) item$iv$iv;
                destination$iv$iv.add(raw(p0));
            }
            Iterable iterableCompact = appAgentRules.compact((List) destination$iv$iv);
            Iterable $this$map$iv2 = iterableCompact;
            int $i$f$map = CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10);
            Collection destination$iv$iv2 = new ArrayList($i$f$map);
            for (Object item$iv$iv2 : $this$map$iv2) {
                destination$iv$iv2.add(((CompactItem) item$iv$iv2).getItem());
                $this$map$iv2 = $this$map$iv2;
            }
            ArrayList arrayList = (List) destination$iv$iv2;
            Iterable $this$map$iv3 = iterableCompact;
            int $i$f$map2 = CollectionsKt.collectionSizeOrDefault($this$map$iv3, 10);
            Collection destination$iv$iv3 = new ArrayList($i$f$map2);
            for (Object item$iv$iv3 : $this$map$iv3) {
                destination$iv$iv3.add(listVisibleNodes.get(((CompactItem) item$iv$iv3).getSource()));
                $this$map$iv3 = $this$map$iv3;
            }
            List nodes = (List) destination$iv$iv3;
            boolean z = false;
            int number2 = number;
            AppAgentRules.LoopGuard guard3 = guard2;
            List<ScreenItem> list2 = list;
            String str = "Готово, сэр.";
            if (AppAgentRules.INSTANCE.resultOpened(goal, arrayList, list, typed && (lastStep instanceof AgentStep.Tap), queries)) {
                return "Готово, сэр.";
            }
            if ((lastStep instanceof AgentStep.Enter) && Intrinsics.areEqual(arrayList, list2)) {
                history.add("ENTER не сработал, нажми подходящую подсказку из списка");
            }
            String answer = askTwice(AppAgentRules.INSTANCE.prompt(goal, packageName, arrayList, history, typed));
            if (answer == null) {
                VoiceHealthLog.INSTANCE.write(this.context, "agent no answer step " + number2 + " items=" + arrayList.size());
                return "Облако не ответило, сэр.";
            }
            if (cancelled) {
                return "Остановился, сэр.";
            }
            AgentStep step = AppAgentRules.INSTANCE.parse(answer);
            String simpleName = null;
            Log.i(TAG, "шаг " + number2 + ": " + ((step == null || (cls2 = step.getClass()) == null) ? null : cls2.getSimpleName()));
            VoiceHealthLog voiceHealthLog = VoiceHealthLog.INSTANCE;
            Context context = this.context;
            if (step != null && (cls = step.getClass()) != null) {
                simpleName = cls.getSimpleName();
            }
            voiceHealthLog.write(context, "agent step " + number2 + StringUtils.SPACE + simpleName);
            if (step == null) {
                return "Я не понял, что делать дальше, сэр.";
            }
            if (step instanceof AgentStep.Done) {
                if (AppAgentRules.INSTANCE.goalVisible(goal, arrayList) && !AppAgentRules.INSTANCE.mustSearch(goal, arrayList, typed)) {
                    z = true;
                }
                boolean finished = z;
                if (rejected >= 2 || finished) {
                    String reply = ((AgentStep.Done) step).getReply();
                    if (!StringsKt.isBlank(reply)) {
                        str = reply;
                    }
                    return str;
                }
                rejected++;
                history.add("DONE отклонён: цель не выполнена, воспользуйся поиском или продолжай шагами");
                guard = guard3;
                j = 1500;
            } else {
                String it = AppAgentRules.INSTANCE.blocked(step, arrayList);
                if (it != null) {
                    return "Остановился, сэр: " + it + ".";
                }
                if ((step instanceof AgentStep.Tap) && AppAgentRules.INSTANCE.unwantedShorts(goal, arrayList.get(((AgentStep.Tap) step).getIndex()).getLabel())) {
                    history.add("Shorts не подходит, нажми обычное видео");
                } else {
                    if (typed && (step instanceof AgentStep.Tap)) {
                        int index = ((AgentStep.Tap) step).getIndex();
                        Integer numSearchIndex = AppAgentRules.INSTANCE.searchIndex(arrayList);
                        if ((numSearchIndex != null && index == numSearchIndex.intValue()) || queries.contains(arrayList.get(((AgentStep.Tap) step).getIndex()).getLabel())) {
                            history.add("повторный поиск запрещён, нажми результат");
                        }
                    }
                    guard = guard3;
                    if (guard.repeated(arrayList, step)) {
                        return "Я хожу по кругу, сэр, поэтому остановился.";
                    }
                    show("Шаг " + number2 + " из 10: " + describe(step, arrayList));
                    if ((step instanceof AgentStep.Tap) && AppAgentRules.INSTANCE.needsConfirmation(arrayList.get(((AgentStep.Tap) step).getIndex()).getLabel())) {
                        if (!confirmed("Нажать «" + arrayList.get(((AgentStep.Tap) step).getIndex()).getLabel() + "», сэр?")) {
                            return "Хорошо, сэр, не нажимаю.";
                        }
                    }
                    if ((step instanceof AgentStep.Enter) && AppAgentRules.INSTANCE.enterIsRisky(arrayList) && !confirmed("Отправить введённое, сэр?")) {
                        return "Хорошо, сэр, не отправляю.";
                    }
                    perform(step, nodes);
                    if (step instanceof AgentStep.Type) {
                        queries.add(((AgentStep.Type) step).getText());
                    }
                    if ((step instanceof AgentStep.Tap) && arrayList.get(((AgentStep.Tap) step).getIndex()).getLabel().length() <= 40 && typed) {
                        queries.add(arrayList.get(((AgentStep.Tap) step).getIndex()).getLabel());
                    }
                    boolean typed2 = typed || (step instanceof AgentStep.Type);
                    history.add(describe(step, arrayList));
                    j = 1500;
                    Thread.sleep(1500L);
                    lastStep = step;
                    typed = typed2;
                }
                guard = guard3;
                j = 1500;
            }
            number = number2 + 1;
            list = arrayList;
            guard2 = guard;
        }
        return "Не уложился в 10 шагов, сэр, и остановился.";
    }

    private final String askTwice(String prompt) throws InterruptedException {
        String it = this.ask.invoke(prompt);
        if (it != null) {
            if (StringsKt.isBlank(it)) {
                it = null;
            }
            if (it != null) {
                return it;
            }
        }
        Thread.sleep(RETRY_PAUSE_MS);
        String it2 = this.ask.invoke(prompt);
        if (it2 == null || StringsKt.isBlank(it2)) {
            return null;
        }
        return it2;
    }

    private final RawNode raw(UiNode node) {
        String str;
        if (node.getPassword()) {
            str = "";
        } else {
            String text = node.getText();
            if (StringsKt.isBlank(text)) {
                text = node.getContentDescription();
            }
            str = text;
        }
        return new RawNode(str, node.getBounds().left, node.getBounds().top, node.getBounds().right, node.getBounds().bottom, node.getClickable(), node.getEditable(), node.getPassword());
    }

    private final String describe(AgentStep step, List<ScreenItem> screen) {
        if (step instanceof AgentStep.Tap) {
            ScreenItem screenItem = (ScreenItem) CollectionsKt.getOrNull(screen, ((AgentStep.Tap) step).getIndex());
            String label = screenItem != null ? screenItem.getLabel() : null;
            if (label == null) {
                label = "";
            }
            return "нажал «" + label + "»";
        }
        if (step instanceof AgentStep.Type) {
            return "ввёл текст";
        }
        if (step instanceof AgentStep.Open) {
            return "открыл " + ((AgentStep.Open) step).getApp();
        }
        if (step instanceof AgentStep.Scroll) {
            return "пролистал";
        }
        if (Intrinsics.areEqual(step, AgentStep.Back.INSTANCE)) {
            return "назад";
        }
        if (Intrinsics.areEqual(step, AgentStep.Enter.INSTANCE)) {
            return "нажал ввод";
        }
        if (step instanceof AgentStep.Done) {
            return "готово";
        }
        throw new NoWhenBranchMatchedException();
    }

    private final void perform(AgentStep step, List<UiNode> nodes) {
        if (step instanceof AgentStep.Tap) {
            Rect it = nodes.get(((AgentStep.Tap) step).getIndex()).getBounds();
            ScreenController.tap$default(ScreenController.INSTANCE, it.exactCenterX(), it.exactCenterY(), false, 4, null);
            return;
        }
        if (!(step instanceof AgentStep.Type)) {
            if (step instanceof AgentStep.Open) {
                if (this.launcher.execute(new ToolCall(null, "agent", "launcher", MapsKt.mapOf(TuplesKt.to("action", AbstractCircuitBreaker.PROPERTY_NAME), TuplesKt.to("app", ((AgentStep.Open) step).getApp())), 1, null)).getStatus() != ToolResultStatus.SUCCESS) {
                    Log.w(TAG, "не открылось: " + ((AgentStep.Open) step).getApp());
                    return;
                }
                return;
            } else {
                if (!(step instanceof AgentStep.Scroll)) {
                    if (!Intrinsics.areEqual(step, AgentStep.Back.INSTANCE)) {
                        if (!Intrinsics.areEqual(step, AgentStep.Enter.INSTANCE)) {
                            if (!(step instanceof AgentStep.Done)) {
                                throw new NoWhenBranchMatchedException();
                            }
                            return;
                        } else {
                            ScreenController.INSTANCE.pressEnter();
                            return;
                        }
                    }
                    ScreenController.INSTANCE.globalAction("back");
                    return;
                }
                ScreenController.swipeDirection$default(ScreenController.INSTANCE, ((AgentStep.Scroll) step).getDown() ? "up" : "down", 0.0f, 2, null);
                return;
            }
        }
        ScreenController.INSTANCE.setTextOnNode(nodes.get(((AgentStep.Type) step).getIndex()), ((AgentStep.Type) step).getText());
    }

    private final boolean confirmed(String question) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Answer answer = new Answer(latch);
        pending = answer;
        VoiceController.speak$default(VoiceController.INSTANCE, question, null, new Function1() { // from class: com.jarvis.app.AppAgentRunner$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppAgentRunner.confirmed$lambda$0(((Long) obj).longValue());
            }
        }, 2, null);
        latch.await(16000L, TimeUnit.MILLISECONDS);
        pending = null;
        return answer.getYes();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit confirmed$lambda$0(long it) {
        VoiceController.INSTANCE.listenForFollowUp(10000L);
        return Unit.INSTANCE;
    }

    private final void show(final String text) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jarvis.app.AppAgentRunner$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                AppAgentRunner.show$lambda$0(this.f$0, text);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void show$lambda$0(AppAgentRunner this$0, String $text) {
        Toast.makeText(this$0.context, $text, 0).show();
    }

    /* JADX INFO: compiled from: AppAgentRunner.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/jarvis/app/AppAgentRunner$Answer;", "", "latch", "Ljava/util/concurrent/CountDownLatch;", "<init>", "(Ljava/util/concurrent/CountDownLatch;)V", "getLatch", "()Ljava/util/concurrent/CountDownLatch;", "yes", "", "getYes", "()Z", "setYes", "(Z)V", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Answer {
        private final CountDownLatch latch;
        private volatile boolean yes;

        public Answer(CountDownLatch latch) {
            Intrinsics.checkNotNullParameter(latch, "latch");
            this.latch = latch;
        }

        public final CountDownLatch getLatch() {
            return this.latch;
        }

        public final boolean getYes() {
            return this.yes;
        }

        public final void setYes(boolean z) {
            this.yes = z;
        }
    }

    /* JADX INFO: compiled from: AppAgentRunner.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/jarvis/app/AppAgentRunner$Companion;", "", "<init>", "()V", "TAG", "", "YOUTUBE", "START_PAUSE_MS", "", "STEP_PAUSE_MS", "CONFIRM_WAIT_MS", "SPEECH_MARGIN_MS", "MAX_NODES", "", "MAX_REJECTED_DONE", "MAX_QUERY", "RETRY_PAUSE_MS", "STOP", "Lkotlin/text/Regex;", "YES", "running", "Ljava/util/concurrent/atomic/AtomicBoolean;", "cancelled", "", "pending", "Lcom/jarvis/app/AppAgentRunner$Answer;", "consume", VoiceService.EXTRA_TEXT, "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean consume(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            if (!AppAgentRunner.running.get()) {
                return false;
            }
            String lowerCase = text.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            String said = StringsKt.trim((CharSequence) lowerCase).toString();
            Answer answer = AppAgentRunner.pending;
            if (answer != null) {
                answer.setYes(AppAgentRunner.YES.containsMatchIn(said));
                answer.getLatch().countDown();
                return true;
            }
            if (!AppAgentRunner.STOP.containsMatchIn(said)) {
                return false;
            }
            AppAgentRunner.cancelled = true;
            return true;
        }
    }
}
