package com.jarvis.app.ui;

import android.view.View;
import android.widget.LinearLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: More.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/jarvis/app/ui/MoreScreen;", "Lcom/jarvis/app/ui/Screen;", "host", "Lcom/jarvis/app/ui/JarvisActivity;", "<init>", "(Lcom/jarvis/app/ui/JarvisActivity;)V", "body", "Landroid/widget/LinearLayout;", "view", "Landroid/view/View;", "getView", "()Landroid/view/View;", "onShow", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MoreScreen extends Screen {
    private final LinearLayout body;
    private final View view;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoreScreen(JarvisActivity host) {
        super(host);
        Intrinsics.checkNotNullParameter(host, "host");
        this.body = UiKt.column(host, 10);
        LinearLayout $this$view_u24lambda_u240 = UiKt.column$default(host, 0, 1, null);
        $this$view_u24lambda_u240.addView(header("Ещё", false));
        $this$view_u24lambda_u240.addView(scroll(this.body), UiKt.lp$default(0, 0, 1.0f, 1, null));
        this.view = $this$view_u24lambda_u240;
    }

    @Override // com.jarvis.app.ui.Screen
    public View getView() {
        return this.view;
    }

    @Override // com.jarvis.app.ui.Screen
    public void onShow() {
        this.body.removeAllViews();
        boolean on = getHost().isOn();
        this.body.addView(Screen.card$default(this, "J.A.R.V.I.S.", on ? "Ассистент включён" : "Ассистент выключен", Ic.GLOBE, null, 0, 0, null, 120, null));
        LinearLayout $this$onShow_u24lambda_u240 = UiKt.column$default(getHost(), 0, 1, null);
        $this$onShow_u24lambda_u240.setBackground(UiKt.glassBg$default(getHost(), getSkin(), 0, 0, 0, 14, null));
        onShow$add($this$onShow_u24lambda_u240, this, "Рутины", "Сценарии, рекомендации, дела", Ic.CLOCK, new Function0() { // from class: com.jarvis.app.ui.MoreScreen$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MoreScreen.onShow$lambda$2(this.f$0);
            }
        });
        Iterable $this$forEach$iv = CollectionsKt.take(Sections.INSTANCE.getALL(), 6);
        for (Object element$iv : $this$forEach$iv) {
            final MoreSection s = (MoreSection) element$iv;
            onShow$add($this$onShow_u24lambda_u240, this, s.getTitle(), s.getSub(), s.getIcon(), new Function0() { // from class: com.jarvis.app.ui.MoreScreen$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MoreScreen.onShow$lambda$3$0(this.f$0, s);
                }
            });
        }
        onShow$add($this$onShow_u24lambda_u240, this, "Система", "Службы, нагрузка, состояние", Ic.CHART, new Function0() { // from class: com.jarvis.app.ui.MoreScreen$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MoreScreen.onShow$lambda$4(this.f$0);
            }
        });
        Iterable $this$forEach$iv2 = CollectionsKt.drop(Sections.INSTANCE.getALL(), 6);
        for (Object element$iv2 : $this$forEach$iv2) {
            final MoreSection s2 = (MoreSection) element$iv2;
            onShow$add($this$onShow_u24lambda_u240, this, s2.getTitle(), s2.getSub(), s2.getIcon(), new Function0() { // from class: com.jarvis.app.ui.MoreScreen$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MoreScreen.onShow$lambda$5$0(this.f$0, s2);
                }
            });
        }
        this.body.addView($this$onShow_u24lambda_u240);
    }

    private static final void onShow$add(LinearLayout menu, MoreScreen this$0, String title, String sub, String icon, Function0<Unit> function0) {
        LinearLayout $this$onShow_u24add_u24lambda_u241 = Screen.card$default(this$0, title, sub, icon, null, 0, 0, function0, 56, null);
        $this$onShow_u24add_u24lambda_u241.setBackground(null);
        menu.addView($this$onShow_u24add_u24lambda_u241);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onShow$lambda$2(MoreScreen this$0) {
        this$0.getHost().show(JarvisActivity.ROUTINES);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onShow$lambda$3$0(MoreScreen this$0, MoreSection $s) {
        this$0.getHost().openSection($s);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onShow$lambda$4(MoreScreen this$0) {
        this$0.getHost().show("status");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onShow$lambda$5$0(MoreScreen this$0, MoreSection $s) {
        this$0.getHost().openSection($s);
        return Unit.INSTANCE;
    }
}
