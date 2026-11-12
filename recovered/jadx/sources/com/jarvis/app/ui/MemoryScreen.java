package com.jarvis.app.ui;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jarvis.app.BrainGraph;
import com.jarvis.app.ConversationStore;
import com.jarvis.brain.LongTermMemory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Screens.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0011"}, d2 = {"Lcom/jarvis/app/ui/MemoryScreen;", "Lcom/jarvis/app/ui/Screen;", "host", "Lcom/jarvis/app/ui/JarvisActivity;", "<init>", "(Lcom/jarvis/app/ui/JarvisActivity;)V", "body", "Landroid/widget/LinearLayout;", "tab", "", "view", "Landroid/view/View;", "getView", "()Landroid/view/View;", "onShow", "", "render", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MemoryScreen extends Screen {
    private final LinearLayout body;
    private int tab;
    private final View view;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MemoryScreen(JarvisActivity host) {
        super(host);
        Intrinsics.checkNotNullParameter(host, "host");
        this.body = UiKt.column(host, 10);
        this.tab = 1;
        LinearLayout $this$view_u24lambda_u240 = UiKt.column$default(host, 0, 1, null);
        $this$view_u24lambda_u240.addView(header("Память Jarvis", false));
        View viewPad = UiKt.pad(ScreensKt.segment(host, CollectionsKt.listOf((Object[]) new String[]{"Беседы", "Факты", "Привычки"}), this.tab, new Function1() { // from class: com.jarvis.app.ui.MemoryScreen$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MemoryScreen.view$lambda$0$0(this.f$0, ((Integer) obj).intValue());
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
    public static final Unit view$lambda$0$0(MemoryScreen this$0, int it) {
        this$0.tab = it;
        this$0.render();
        return Unit.INSTANCE;
    }

    @Override // com.jarvis.app.ui.Screen
    public void onShow() {
        render();
    }

    private final void render() {
        Object objM392constructorimpl;
        this.body.removeAllViews();
        switch (this.tab) {
            case 0:
                Iterable $this$filter$iv = ConversationStore.INSTANCE.all();
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv : $this$filter$iv) {
                    if (((ConversationStore.Bubble) element$iv$iv).getRole() == ConversationStore.Bubble.Role.USER) {
                        destination$iv$iv.add(element$iv$iv);
                    }
                }
                List list = CollectionsKt.reversed(CollectionsKt.takeLast((List) destination$iv$iv, 30));
                if (list.isEmpty()) {
                    LinearLayout linearLayout = this.body;
                    JarvisActivity host = getHost();
                    linearLayout.addView(UiKt.label(host, (8 & 1) != 0 ? "" : "Бесед пока нет.", (8 & 2) != 0 ? 14.0f : 14.0f, getSkin().getMuted(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0));
                }
                List $this$forEach$iv = list;
                for (Object element$iv : $this$forEach$iv) {
                    ConversationStore.Bubble it = (ConversationStore.Bubble) element$iv;
                    this.body.addView(Screen.card$default(this, it.getText(), ConversationStore.INSTANCE.formatTime(it.getAtMs()), null, null, 0, 0, null, 124, null));
                }
                break;
            case 1:
                try {
                    Result.Companion companion = Result.INSTANCE;
                    MemoryScreen $this$render_u24lambda_u242 = this;
                    objM392constructorimpl = Result.m392constructorimpl(BrainGraph.INSTANCE.memory($this$render_u24lambda_u242.getHost()).all());
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
                }
                List listEmptyList = CollectionsKt.emptyList();
                if (Result.m398isFailureimpl(objM392constructorimpl)) {
                    objM392constructorimpl = listEmptyList;
                }
                List facts = (List) objM392constructorimpl;
                if (facts.isEmpty()) {
                    LinearLayout linearLayout2 = this.body;
                    JarvisActivity host2 = getHost();
                    linearLayout2.addView(UiKt.label(host2, (8 & 1) != 0 ? "" : "Фактов пока нет. Скажите: «запомни, что мой любимый чай зелёный».", (8 & 2) != 0 ? 14.0f : 14.0f, getSkin().getMuted(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host2, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0));
                }
                List $this$forEach$iv2 = facts;
                for (Object element$iv2 : $this$forEach$iv2) {
                    final LongTermMemory.MemoryFact f = (LongTermMemory.MemoryFact) element$iv2;
                    LinearLayout linearLayout3 = this.body;
                    String text = f.getText();
                    String category = f.getCategory();
                    JarvisActivity host3 = getHost();
                    final TextView $this$render_u24lambda_u243_u240 = UiKt.label(host3, (8 & 1) != 0 ? "" : "Забыть", (8 & 2) != 0 ? 14.0f : 13.0f, getSkin().getDanger(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host3, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0);
                    List facts2 = facts;
                    UiKt.tap($this$render_u24lambda_u243_u240, new Function0() { // from class: com.jarvis.app.ui.MemoryScreen$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MemoryScreen.render$lambda$3$0$0(this.f$0, f, $this$render_u24lambda_u243_u240);
                        }
                    });
                    Unit unit = Unit.INSTANCE;
                    linearLayout3.addView(Screen.card$default(this, text, category, null, $this$render_u24lambda_u243_u240, 0, 0, null, 116, null));
                    facts = facts2;
                }
                break;
            default:
                LinearLayout linearLayout4 = this.body;
                JarvisActivity host4 = getHost();
                linearLayout4.addView(UiKt.label(host4, (8 & 1) != 0 ? "" : "Здесь появятся повторяющиеся действия. Джарвис только предлагает, сам ничего не создаёт.", (8 & 2) != 0 ? 14.0f : 14.0f, getSkin().getMuted(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host4, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0));
                break;
        }
        this.body.addView(Screen.card$default(this, "Чтобы я что-то забыл, скажите: «Забудь это».", null, Ic.INFO, null, 0, 0, null, 122, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit render$lambda$3$0$0(final MemoryScreen this$0, final LongTermMemory.MemoryFact $f, final TextView $this_apply) {
        this$0.getHost().showConfirm($f.getText(), "Забыть этот факт?", new Function0() { // from class: com.jarvis.app.ui.MemoryScreen$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MemoryScreen.render$lambda$3$0$0$0($this_apply, this$0, $f);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit render$lambda$3$0$0$0(TextView $this_apply, MemoryScreen this$0, LongTermMemory.MemoryFact $f) {
        try {
            Result.Companion companion = Result.INSTANCE;
            Result.m392constructorimpl(Boolean.valueOf(BrainGraph.INSTANCE.memory(this$0.getHost()).remove($f.getId())));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        this$0.render();
        return Unit.INSTANCE;
    }
}
