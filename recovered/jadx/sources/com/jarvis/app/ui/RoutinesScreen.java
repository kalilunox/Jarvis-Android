package com.jarvis.app.ui;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.jarvis.app.AndroidReminderBook;
import com.jarvis.app.PrefsCustomRoutines;
import com.jarvis.app.PrefsEventRoutines;
import com.jarvis.app.PrefsNoteBook;
import com.jarvis.core.notes.NoteParser;
import com.jarvis.core.reminder.Reminder;
import com.jarvis.core.reminder.ReminderSpeech;
import com.jarvis.core.routines.EventRule;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Screens.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u000fH\u0002J\b\u0010\u0015\u001a\u00020\u000fH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lcom/jarvis/app/ui/RoutinesScreen;", "Lcom/jarvis/app/ui/Screen;", "host", "Lcom/jarvis/app/ui/JarvisActivity;", "<init>", "(Lcom/jarvis/app/ui/JarvisActivity;)V", "body", "Landroid/widget/LinearLayout;", "tab", "", "view", "Landroid/view/View;", "getView", "()Landroid/view/View;", "onShow", "", "run", "Landroid/widget/TextView;", "phrase", "", "render", "tasks", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RoutinesScreen extends Screen {
    private final LinearLayout body;
    private int tab;
    private final View view;
    private static final Companion Companion = new Companion(null);
    private static final List<Triple<String, String, String>> BUILT_IN = CollectionsKt.listOf((Object[]) new Triple[]{new Triple("Режим сна", "Музыка, фонарик, яркость, тишина", "режим сна"), new Triple("Режим кино", "Яркость 30%, тишина", "режим кино"), new Triple("Режим работы", "Тишина и никакой музыки", "режим работы"), new Triple("Я ухожу", "Музыка и фонарик выключаются", "я ухожу"), new Triple("Я проснулся", "Сводка и обычный режим", "я проснулся")});

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RoutinesScreen(JarvisActivity host) {
        super(host);
        Intrinsics.checkNotNullParameter(host, "host");
        this.body = UiKt.column(host, 10);
        LinearLayout $this$view_u24lambda_u240 = UiKt.column$default(host, 0, 1, null);
        $this$view_u24lambda_u240.addView(header("Рутины", true));
        View viewPad = UiKt.pad(ScreensKt.segment(host, CollectionsKt.listOf((Object[]) new String[]{"Мои", "Рекомендации", "Дела"}), this.tab, new Function1() { // from class: com.jarvis.app.ui.RoutinesScreen$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return RoutinesScreen.view$lambda$0$0(this.f$0, ((Integer) obj).intValue());
            }
        }), 14, 0);
        LinearLayout.LayoutParams $this$view_u24lambda_u240_u241 = UiKt.lp$default(0, UiKt.dp((Context) host, 36), 0.0f, 5, null);
        $this$view_u24lambda_u240_u241.bottomMargin = UiKt.dp((Context) host, 10);
        Unit unit = Unit.INSTANCE;
        $this$view_u24lambda_u240.addView(viewPad, $this$view_u24lambda_u240_u241);
        $this$view_u24lambda_u240.addView(scroll(this.body), UiKt.lp$default(0, 0, 1.0f, 1, null));
        this.view = $this$view_u24lambda_u240;
    }

    @Override // com.jarvis.app.ui.Screen
    public View getView() {
        return this.view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit view$lambda$0$0(RoutinesScreen this$0, int it) {
        this$0.tab = it;
        this$0.render();
        return Unit.INSTANCE;
    }

    @Override // com.jarvis.app.ui.Screen
    public void onShow() {
        render();
    }

    private final TextView run(final String phrase) {
        JarvisActivity host = getHost();
        TextView $this$run_u24lambda_u240 = UiKt.label(host, (8 & 1) != 0 ? "" : "Запустить", (8 & 2) != 0 ? 14.0f : 12.0f, getSkin().getOnAccent(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host, 0, 2, null) : Fonts.INSTANCE.body(getHost(), TypedValues.TransitionType.TYPE_DURATION), (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0);
        $this$run_u24lambda_u240.setBackground(UiKt.glassBg(getHost(), getSkin(), 14, getSkin().getAccent(), getSkin().getAccent()));
        $this$run_u24lambda_u240.setPadding(UiKt.dp((Context) getHost(), 12), UiKt.dp((Context) getHost(), 6), UiKt.dp((Context) getHost(), 12), UiKt.dp((Context) getHost(), 6));
        UiKt.tap($this$run_u24lambda_u240, new Function0() { // from class: com.jarvis.app.ui.RoutinesScreen$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return RoutinesScreen.run$lambda$0$0(this.f$0, phrase);
            }
        });
        return $this$run_u24lambda_u240;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit run$lambda$0$0(RoutinesScreen this$0, String $phrase) {
        this$0.getHost().send($phrase);
        this$0.getHost().show("home");
        return Unit.INSTANCE;
    }

    private final void render() {
        this.body.removeAllViews();
        switch (this.tab) {
            case 0:
                Iterable $this$forEach$iv = BUILT_IN;
                for (Object element$iv : $this$forEach$iv) {
                    Triple triple = (Triple) element$iv;
                    String title = (String) triple.component1();
                    String sub = (String) triple.component2();
                    String phrase = (String) triple.component3();
                    this.body.addView(Screen.card$default(this, title, sub, Ic.CLOCK, run(phrase), 0, 0, null, 112, null));
                }
                final PrefsCustomRoutines own = new PrefsCustomRoutines(getHost());
                final PrefsEventRoutines events = new PrefsEventRoutines(getHost());
                Iterable $this$filter$iv = events.rules();
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv : $this$filter$iv) {
                    EventRule it = (EventRule) element$iv$iv;
                    if (own.steps(it.getName()) != null) {
                        destination$iv$iv.add(element$iv$iv);
                    }
                }
                List rules = (List) destination$iv$iv;
                List $this$map$iv = rules;
                Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    EventRule it2 = (EventRule) item$iv$iv;
                    destination$iv$iv2.add(it2.getName());
                }
                Set ruleNames = CollectionsKt.toSet((List) destination$iv$iv2);
                Iterable $this$filter$iv2 = own.names();
                Collection destination$iv$iv3 = new ArrayList();
                for (Object element$iv$iv2 : $this$filter$iv2) {
                    String it3 = (String) element$iv$iv2;
                    if (!ruleNames.contains(it3)) {
                        destination$iv$iv3.add(element$iv$iv2);
                    }
                }
                Iterable $this$forEach$iv2 = (List) destination$iv$iv3;
                for (Object element$iv2 : $this$forEach$iv2) {
                    String name = (String) element$iv2;
                    LinearLayout linearLayout = this.body;
                    RoutinesScreen routinesScreen = this;
                    List<String> listSteps = own.steps(name);
                    if (listSteps == null) {
                        listSteps = CollectionsKt.emptyList();
                    }
                    linearLayout.addView(Screen.card$default(routinesScreen, name, CollectionsKt.joinToString$default(listSteps, ", ", null, null, 0, null, null, 62, null), Ic.BOLT, run(name), 0, 0, null, 112, null));
                    ruleNames = ruleNames;
                }
                if (!rules.isEmpty()) {
                    this.body.addView(section("По событиям"));
                }
                List $this$forEach$iv3 = rules;
                int $i$f$forEach = 0;
                for (Object element$iv3 : $this$forEach$iv3) {
                    final EventRule rule = (EventRule) element$iv3;
                    JarvisActivity host = getHost();
                    List rules2 = rules;
                    TextView $this$render_u24lambda_u245_u240 = UiKt.label(host, (8 & 1) != 0 ? "" : "Удалить", (8 & 2) != 0 ? 14.0f : 12.0f, getSkin().getText(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host, 0, 2, null) : Fonts.INSTANCE.body(getHost(), TypedValues.TransitionType.TYPE_DURATION), (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0);
                    $this$render_u24lambda_u245_u240.setBackground(UiKt.glassBg$default(getHost(), getSkin(), 14, 0, 0, 12, null));
                    int iDp = UiKt.dp((Context) getHost(), 12);
                    int iDp2 = UiKt.dp((Context) getHost(), 6);
                    Iterable $this$forEach$iv4 = $this$forEach$iv3;
                    int iDp3 = UiKt.dp((Context) getHost(), 12);
                    int $i$f$forEach2 = $i$f$forEach;
                    int $i$f$forEach3 = UiKt.dp((Context) getHost(), 6);
                    $this$render_u24lambda_u245_u240.setPadding(iDp, iDp2, iDp3, $i$f$forEach3);
                    UiKt.tap($this$render_u24lambda_u245_u240, new Function0() { // from class: com.jarvis.app.ui.RoutinesScreen$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return RoutinesScreen.render$lambda$5$0$0(events, rule, own, this);
                        }
                    });
                    LinearLayout linearLayout2 = this.body;
                    RoutinesScreen routinesScreen2 = this;
                    String name2 = rule.getName();
                    List<String> listSteps2 = own.steps(rule.getName());
                    if (listSteps2 == null) {
                        listSteps2 = CollectionsKt.emptyList();
                    }
                    linearLayout2.addView(Screen.card$default(routinesScreen2, name2, CollectionsKt.joinToString$default(listSteps2, ", ", null, null, 0, null, null, 62, null), Ic.BOLT, $this$render_u24lambda_u245_u240, 0, 0, null, 112, null));
                    $i$f$forEach = $i$f$forEach2;
                    $this$forEach$iv3 = $this$forEach$iv4;
                    rules = rules2;
                }
                this.body.addView(Screen.card$default(this, "Создать рутину", "Скажите: «когда я говорю поехали, включи блютуз и музыку» или «когда подключаю наушники, включи музыку»", Ic.INFO, null, 0, 0, null, 120, null));
                break;
            case 1:
                LinearLayout linearLayout3 = this.body;
                JarvisActivity host2 = getHost();
                linearLayout3.addView(UiKt.label(host2, (8 & 1) != 0 ? "" : "Пока нет рекомендаций. Когда Джарвис заметит повторяющиеся действия, предложит их здесь.", (8 & 2) != 0 ? 14.0f : 14.0f, getSkin().getMuted(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host2, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0));
                break;
            default:
                tasks();
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit render$lambda$5$0$0(PrefsEventRoutines $events, EventRule $rule, PrefsCustomRoutines $own, RoutinesScreen this$0) {
        $events.remove($rule);
        $own.remove($rule.getName());
        this$0.render();
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v15, types: [java.lang.Object, java.time.LocalDateTime] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private final void tasks() {
        Object objM392constructorimpl;
        LocalDateTime now = LocalDateTime.now();
        Long lNextAlarm = Device.INSTANCE.nextAlarm(getHost());
        if (lNextAlarm != null) {
            ?? localDateTime = Instant.ofEpochMilli(lNextAlarm.longValue()).atZone(ZoneId.systemDefault()).toLocalDateTime();
            LinearLayout linearLayout = this.body;
            ReminderSpeech reminderSpeech = ReminderSpeech.INSTANCE;
            Intrinsics.checkNotNull(localDateTime);
            Intrinsics.checkNotNull(now);
            linearLayout.addView(Screen.card$default(this, "Ближайший будильник", reminderSpeech.whenPhrase(localDateTime, now), Ic.CLOCK, null, 0, 0, null, 120, null));
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            RoutinesScreen $this$tasks_u24lambda_u241 = this;
            objM392constructorimpl = Result.m392constructorimpl(new AndroidReminderBook($this$tasks_u24lambda_u241.getHost()).all());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        List listEmptyList = CollectionsKt.emptyList();
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = listEmptyList;
        }
        Iterable $this$sortedBy$iv = (Iterable) objM392constructorimpl;
        Iterable $this$forEach$iv = CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: com.jarvis.app.ui.RoutinesScreen$tasks$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                Reminder it = (Reminder) t;
                Reminder it2 = (Reminder) t2;
                return ComparisonsKt.compareValues(it.getAt(), it2.getAt());
            }
        });
        for (Object element$iv : $this$forEach$iv) {
            Reminder it = (Reminder) element$iv;
            LinearLayout linearLayout2 = this.body;
            String text = it.getText();
            ReminderSpeech reminderSpeech2 = ReminderSpeech.INSTANCE;
            LocalDateTime at = it.getAt();
            Intrinsics.checkNotNull(now);
            linearLayout2.addView(Screen.card$default(this, text, reminderSpeech2.whenPhrase(at, now), Ic.BELL, null, 0, 0, null, 120, null));
        }
        PrefsNoteBook notes = new PrefsNoteBook(getHost());
        Iterable $this$forEach$iv2 = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(NoteParser.SHOPPING, "Покупки"), TuplesKt.to(NoteParser.TODO, "Дела"), TuplesKt.to(NoteParser.NOTES, "Заметки")});
        for (Object element$iv2 : $this$forEach$iv2) {
            Pair pair = (Pair) element$iv2;
            String key = (String) pair.component1();
            String title = (String) pair.component2();
            List<String> listItems = notes.items(key);
            if (!listItems.isEmpty()) {
                this.body.addView(Screen.card$default(this, title, CollectionsKt.joinToString$default(listItems, ", ", null, null, 0, null, null, 62, null), Ic.CAL, null, 0, 0, null, 120, null));
            }
        }
        if (this.body.getChildCount() == 0) {
            LinearLayout linearLayout3 = this.body;
            JarvisActivity host = getHost();
            linearLayout3.addView(UiKt.label(host, (8 & 1) != 0 ? "" : "Дел пока нет. Скажите: «напомни завтра в девять позвонить маме».", (8 & 2) != 0 ? 14.0f : 14.0f, getSkin().getMuted(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0));
        }
    }

    /* JADX INFO: compiled from: Screens.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R)\u0010\u0004\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/jarvis/app/ui/RoutinesScreen$Companion;", "", "<init>", "()V", "BUILT_IN", "", "Lkotlin/Triple;", "", "getBUILT_IN", "()Ljava/util/List;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<Triple<String, String, String>> getBUILT_IN() {
            return RoutinesScreen.BUILT_IN;
        }
    }
}
