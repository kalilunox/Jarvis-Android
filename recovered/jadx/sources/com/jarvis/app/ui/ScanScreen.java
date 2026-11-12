package com.jarvis.app.ui;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.lifecycle.LifecycleOwnerKt;
import com.jarvis.app.VoiceController;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.screencontrol.JarvisNotificationListenerService;
import com.jarvis.screencontrol.NotificationInfo;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: Screens.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016J$\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000f2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u001b0\u001aH\u0002J\u0018\u0010\u001c\u001a\u00020\u000f2\u000e\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u001eH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lcom/jarvis/app/ui/ScanScreen;", "Lcom/jarvis/app/ui/Screen;", "host", "Lcom/jarvis/app/ui/JarvisActivity;", "<init>", "(Lcom/jarvis/app/ui/JarvisActivity;)V", "weatherNow", "Landroid/widget/TextView;", "eventsText", "notifText", "today", "Landroid/widget/LinearLayout;", "chip", "value", "name", "", "view", "Landroid/view/View;", "getView", "()Landroid/view/View;", "onShow", "", "call", "Lcom/jarvis/core/common/model/ToolCall;", "id", "args", "", "", "tool", "block", "Lkotlin/Function0;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ScanScreen extends Screen {
    private final TextView eventsText;
    private final TextView notifText;
    private final LinearLayout today;
    private final View view;
    private final TextView weatherNow;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScanScreen(final JarvisActivity host) {
        super(host);
        Intrinsics.checkNotNullParameter(host, "host");
        JarvisActivity jarvisActivity = host;
        this.weatherNow = UiKt.label(jarvisActivity, (8 & 1) != 0 ? "" : "…", (8 & 2) != 0 ? 14.0f : 13.0f, getSkin().getText(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, jarvisActivity, 0, 2, null) : Fonts.INSTANCE.body(host, 600), (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0);
        JarvisActivity jarvisActivity2 = host;
        this.eventsText = UiKt.label(jarvisActivity2, (8 & 1) != 0 ? "" : "…", (8 & 2) != 0 ? 14.0f : 13.0f, getSkin().getText(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, jarvisActivity2, 0, 2, null) : Fonts.INSTANCE.body(host, 600), (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0);
        JarvisActivity jarvisActivity3 = host;
        this.notifText = UiKt.label(jarvisActivity3, (8 & 1) != 0 ? "" : "…", (8 & 2) != 0 ? 14.0f : 13.0f, getSkin().getText(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, jarvisActivity3, 0, 2, null) : Fonts.INSTANCE.body(host, 600), (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0);
        this.today = UiKt.column(host, 10);
        LinearLayout $this$view_u24lambda_u240 = UiKt.column$default(host, 0, 1, null);
        $this$view_u24lambda_u240.addView(header("Анализ ситуации", true));
        LinearLayout body = UiKt.column(host, 10);
        LinearLayout top = UiKt.rowLayout(host, 8);
        top.addView(new GlobeView(host, getSkin()), UiKt.lp(0, UiKt.dp((Context) host, 220), 1.0f));
        LinearLayout chips = UiKt.column(host, 6);
        chips.addView(chip(this.weatherNow, "Погода"));
        chips.addView(chip(this.eventsText, "Календарь"));
        chips.addView(chip(this.notifText, "Уведомления"));
        top.addView(chips, UiKt.lp$default(UiKt.dp((Context) host, 140), -2, 0.0f, 4, null));
        body.addView(top);
        body.addView(this.today);
        $this$view_u24lambda_u240.addView(scroll(body), UiKt.lp$default(0, 0, 1.0f, 1, null));
        body.addView(host.button("Сводка дня голосом", true, new Function0() { // from class: com.jarvis.app.ui.ScanScreen$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ScanScreen.view$lambda$0$0(host);
            }
        }), UiKt.lp$default(0, UiKt.dp((Context) host, 48), 0.0f, 5, null));
        this.view = $this$view_u24lambda_u240;
    }

    private final LinearLayout chip(TextView value, String name) {
        View viewPad = UiKt.pad(UiKt.column$default(getHost(), 0, 1, null), 10, 7);
        LinearLayout $this$chip_u24lambda_u240 = (LinearLayout) viewPad;
        $this$chip_u24lambda_u240.setBackground(UiKt.glassBg$default(getHost(), getSkin(), 12, 0, 0, 12, null));
        $this$chip_u24lambda_u240.addView(value);
        JarvisActivity host = getHost();
        $this$chip_u24lambda_u240.addView(UiKt.label(host, (8 & 1) != 0 ? "" : name, (8 & 2) != 0 ? 14.0f : 12.0f, getSkin().getMuted(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0));
        return (LinearLayout) viewPad;
    }

    @Override // com.jarvis.app.ui.Screen
    public View getView() {
        return this.view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit view$lambda$0$0(JarvisActivity $host) {
        $host.send("сводка на день");
        $host.show("home");
        return Unit.INSTANCE;
    }

    @Override // com.jarvis.app.ui.Screen
    public void onShow() {
        Object objM392constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            ScanScreen scanScreen = this;
            objM392constructorimpl = Result.m392constructorimpl(JarvisNotificationListenerService.INSTANCE.snapshot());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        List listEmptyList = CollectionsKt.emptyList();
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = listEmptyList;
        }
        Iterable $this$count$iv = (Iterable) objM392constructorimpl;
        int i = 0;
        if (!($this$count$iv instanceof Collection) || !((Collection) $this$count$iv).isEmpty()) {
            int count$iv = 0;
            for (Object element$iv : $this$count$iv) {
                NotificationInfo it = (NotificationInfo) element$iv;
                if (((Intrinsics.areEqual(it.getPackageName(), getHost().getPackageName()) || StringsKt.isBlank(it.getTitle())) ? false : true) && (count$iv = count$iv + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
            i = count$iv;
        }
        int notifications = i;
        this.notifText.setText(notifications + " новых");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(getHost()), null, null, new AnonymousClass1(null), 3, null);
    }

    /* JADX INFO: renamed from: com.jarvis.app.ui.ScanScreen$onShow$1, reason: invalid class name */
    /* JADX INFO: compiled from: Screens.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.ui.ScanScreen$onShow$1", f = "Screens.kt", i = {1, 2, 2}, l = {357, 358, 359}, m = "invokeSuspend", n = {"now", "now", "week"}, s = {"L$0", "L$0", "L$1"}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ScanScreen.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:14:0x0070 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:17:0x0092 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:18:0x0093  */
        /* JADX WARN: Code duplicated, block: B:21:0x00ae  */
        /* JADX WARN: Code duplicated, block: B:26:0x00bd  */
        /* JADX WARN: Code duplicated, block: B:29:0x00cd  */
        /* JADX WARN: Code duplicated, block: B:35:0x00e5  */
        /* JADX WARN: Code duplicated, block: B:36:0x00e8  */
        /* JADX WARN: Code duplicated, block: B:39:0x0105  */
        /* JADX WARN: Code duplicated, block: B:40:0x0108  */
        /* JADX WARN: Code duplicated, block: B:43:0x012f  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object objWithContext;
            String now;
            Object objWithContext2;
            String week;
            Object objWithContext3;
            String week2;
            String cal;
            MatchResult matchResultFind$default;
            String str;
            boolean empty;
            String str2;
            String strRemovePrefix;
            List<String> groupValues;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            boolean z = true;
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new ScanScreen$onShow$1$now$1(ScanScreen.this, null), this);
                    if (objWithContext == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    now = (String) objWithContext;
                    this.L$0 = now;
                    this.label = 2;
                    objWithContext2 = BuildersKt.withContext(Dispatchers.getIO(), new ScanScreen$onShow$1$week$1(ScanScreen.this, null), this);
                    if (objWithContext2 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    week = (String) objWithContext2;
                    this.L$0 = now;
                    this.L$1 = week;
                    this.label = 3;
                    objWithContext3 = BuildersKt.withContext(Dispatchers.getIO(), new ScanScreen$onShow$1$cal$1(ScanScreen.this, null), this);
                    if (objWithContext3 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    week2 = week;
                    cal = (String) objWithContext3;
                    TextView textView = ScanScreen.this.weatherNow;
                    matchResultFind$default = Regex.find$default(new Regex("сейчас (-?\\d+°)"), now, 0, 2, null);
                    if (matchResultFind$default != null || (groupValues = matchResultFind$default.getGroupValues()) == null || (str = groupValues.get(1)) == null) {
                        str = "нет данных";
                    }
                    textView.setText(str);
                    if (!StringsKt.isBlank(cal) && !StringsKt.contains((CharSequence) cal, (CharSequence) "нет", true)) {
                        z = false;
                    }
                    empty = z;
                    TextView textView2 = ScanScreen.this.eventsText;
                    if (empty) {
                        str2 = "свободно";
                    } else {
                        str2 = "есть события";
                    }
                    textView2.setText(str2);
                    ScanScreen.this.today.removeAllViews();
                    LinearLayout linearLayout = ScanScreen.this.today;
                    ScanScreen scanScreen = ScanScreen.this;
                    if (empty) {
                        strRemovePrefix = "Событий в календаре нет";
                    } else {
                        strRemovePrefix = StringsKt.removePrefix(cal, (CharSequence) "События: ");
                    }
                    linearLayout.addView(Screen.card$default(scanScreen, "На сегодня", strRemovePrefix, Ic.CAL, null, 0, 0, null, 120, null));
                    if (!StringsKt.isBlank(week2)) {
                        ScanScreen.this.today.addView(Screen.card$default(ScanScreen.this, "Неделя", week2, Ic.SUN, null, 0, 0, null, 120, null));
                    }
                    return Unit.INSTANCE;
                case 1:
                    ResultKt.throwOnFailure($result);
                    objWithContext = $result;
                    now = (String) objWithContext;
                    this.L$0 = now;
                    this.label = 2;
                    objWithContext2 = BuildersKt.withContext(Dispatchers.getIO(), new ScanScreen$onShow$1$week$1(ScanScreen.this, null), this);
                    if (objWithContext2 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    week = (String) objWithContext2;
                    this.L$0 = now;
                    this.L$1 = week;
                    this.label = 3;
                    objWithContext3 = BuildersKt.withContext(Dispatchers.getIO(), new ScanScreen$onShow$1$cal$1(ScanScreen.this, null), this);
                    if (objWithContext3 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    week2 = week;
                    cal = (String) objWithContext3;
                    TextView textView3 = ScanScreen.this.weatherNow;
                    matchResultFind$default = Regex.find$default(new Regex("сейчас (-?\\d+°)"), now, 0, 2, null);
                    if (matchResultFind$default != null) {
                        str = "нет данных";
                    } else {
                        str = "нет данных";
                    }
                    textView3.setText(str);
                    if (!StringsKt.isBlank(cal)) {
                        z = false;
                    }
                    empty = z;
                    TextView textView4 = ScanScreen.this.eventsText;
                    if (empty) {
                        str2 = "свободно";
                    } else {
                        str2 = "есть события";
                    }
                    textView4.setText(str2);
                    ScanScreen.this.today.removeAllViews();
                    LinearLayout linearLayout2 = ScanScreen.this.today;
                    ScanScreen scanScreen2 = ScanScreen.this;
                    if (empty) {
                        strRemovePrefix = "Событий в календаре нет";
                    } else {
                        strRemovePrefix = StringsKt.removePrefix(cal, (CharSequence) "События: ");
                    }
                    linearLayout2.addView(Screen.card$default(scanScreen2, "На сегодня", strRemovePrefix, Ic.CAL, null, 0, 0, null, 120, null));
                    if (!StringsKt.isBlank(week2)) {
                        ScanScreen.this.today.addView(Screen.card$default(ScanScreen.this, "Неделя", week2, Ic.SUN, null, 0, 0, null, 120, null));
                    }
                    return Unit.INSTANCE;
                case 2:
                    now = (String) this.L$0;
                    ResultKt.throwOnFailure($result);
                    objWithContext2 = $result;
                    week = (String) objWithContext2;
                    this.L$0 = now;
                    this.L$1 = week;
                    this.label = 3;
                    objWithContext3 = BuildersKt.withContext(Dispatchers.getIO(), new ScanScreen$onShow$1$cal$1(ScanScreen.this, null), this);
                    if (objWithContext3 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    week2 = week;
                    cal = (String) objWithContext3;
                    TextView textView5 = ScanScreen.this.weatherNow;
                    matchResultFind$default = Regex.find$default(new Regex("сейчас (-?\\d+°)"), now, 0, 2, null);
                    if (matchResultFind$default != null) {
                        str = "нет данных";
                    } else {
                        str = "нет данных";
                    }
                    textView5.setText(str);
                    if (!StringsKt.isBlank(cal)) {
                        z = false;
                    }
                    empty = z;
                    TextView textView6 = ScanScreen.this.eventsText;
                    if (empty) {
                        str2 = "свободно";
                    } else {
                        str2 = "есть события";
                    }
                    textView6.setText(str2);
                    ScanScreen.this.today.removeAllViews();
                    LinearLayout linearLayout3 = ScanScreen.this.today;
                    ScanScreen scanScreen3 = ScanScreen.this;
                    if (empty) {
                        strRemovePrefix = "Событий в календаре нет";
                    } else {
                        strRemovePrefix = StringsKt.removePrefix(cal, (CharSequence) "События: ");
                    }
                    linearLayout3.addView(Screen.card$default(scanScreen3, "На сегодня", strRemovePrefix, Ic.CAL, null, 0, 0, null, 120, null));
                    if (!StringsKt.isBlank(week2)) {
                        ScanScreen.this.today.addView(Screen.card$default(ScanScreen.this, "Неделя", week2, Ic.SUN, null, 0, 0, null, 120, null));
                    }
                    return Unit.INSTANCE;
                case 3:
                    week2 = (String) this.L$1;
                    now = (String) this.L$0;
                    ResultKt.throwOnFailure($result);
                    objWithContext3 = $result;
                    cal = (String) objWithContext3;
                    TextView textView7 = ScanScreen.this.weatherNow;
                    matchResultFind$default = Regex.find$default(new Regex("сейчас (-?\\d+°)"), now, 0, 2, null);
                    if (matchResultFind$default != null) {
                        str = "нет данных";
                    } else {
                        str = "нет данных";
                    }
                    textView7.setText(str);
                    if (!StringsKt.isBlank(cal)) {
                        z = false;
                    }
                    empty = z;
                    TextView textView8 = ScanScreen.this.eventsText;
                    if (empty) {
                        str2 = "свободно";
                    } else {
                        str2 = "есть события";
                    }
                    textView8.setText(str2);
                    ScanScreen.this.today.removeAllViews();
                    LinearLayout linearLayout4 = ScanScreen.this.today;
                    ScanScreen scanScreen4 = ScanScreen.this;
                    if (empty) {
                        strRemovePrefix = "Событий в календаре нет";
                    } else {
                        strRemovePrefix = StringsKt.removePrefix(cal, (CharSequence) "События: ");
                    }
                    linearLayout4.addView(Screen.card$default(scanScreen4, "На сегодня", strRemovePrefix, Ic.CAL, null, 0, 0, null, 120, null));
                    if (!StringsKt.isBlank(week2)) {
                        ScanScreen.this.today.addView(Screen.card$default(ScanScreen.this, "Неделя", week2, Ic.SUN, null, 0, 0, null, 120, null));
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ToolCall call(String id, Map<String, ? extends Object> args) {
        return new ToolCall(null, VoiceController.ENTRY_UI, id, args, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String tool(Function0<String> block) {
        Object objM392constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(block.invoke());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = null;
        }
        String str = (String) objM392constructorimpl;
        return str == null ? "" : str;
    }
}
