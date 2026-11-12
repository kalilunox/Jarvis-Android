package com.jarvis.app.ui;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import com.jarvis.app.ConversationStore;
import com.jarvis.app.VoiceService;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Screens.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\u0018\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0011H\u0016J\b\u0010\u0018\u001a\u00020\u0011H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/jarvis/app/ui/ChatScreen;", "Lcom/jarvis/app/ui/Screen;", "host", "Lcom/jarvis/app/ui/JarvisActivity;", "<init>", "(Lcom/jarvis/app/ui/JarvisActivity;)V", "list", "Landroid/widget/LinearLayout;", "scroller", "Landroid/widget/ScrollView;", "input", "Landroid/widget/EditText;", "view", "Landroid/view/View;", "getView", "()Landroid/view/View;", "submit", "", "bubble", VoiceService.EXTRA_TEXT, "", "mine", "", "onShow", "onData", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ChatScreen extends Screen {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final int MAX_ITEMS = 60;
    private final EditText input;
    private final LinearLayout list;
    private final ScrollView scroller;
    private final View view;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChatScreen(JarvisActivity host) {
        super(host);
        Intrinsics.checkNotNullParameter(host, "host");
        this.list = UiKt.column(host, 10);
        View viewScroll = scroll(this.list);
        Intrinsics.checkNotNull(viewScroll, "null cannot be cast to non-null type android.widget.ScrollView");
        this.scroller = (ScrollView) viewScroll;
        EditText $this$input_u24lambda_u240 = new EditText(host);
        $this$input_u24lambda_u240.setHint("Написать Джарвису");
        $this$input_u24lambda_u240.setHintTextColor(getSkin().getMuted());
        $this$input_u24lambda_u240.setTextColor(getSkin().getText());
        $this$input_u24lambda_u240.setTextSize(15.0f);
        $this$input_u24lambda_u240.setTypeface(Fonts.body$default(Fonts.INSTANCE, host, 0, 2, null));
        $this$input_u24lambda_u240.setBackground(UiKt.glassBg$default(host, getSkin(), 22, 0, 0, 12, null));
        $this$input_u24lambda_u240.setPadding(UiKt.dp((Context) host, 16), 0, UiKt.dp((Context) host, 16), 0);
        $this$input_u24lambda_u240.setInputType(16385);
        $this$input_u24lambda_u240.setImeOptions(4);
        $this$input_u24lambda_u240.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.jarvis.app.ui.ChatScreen$$ExternalSyntheticLambda1
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return ChatScreen.input$lambda$0$0(this.f$0, textView, i, keyEvent);
            }
        });
        this.input = $this$input_u24lambda_u240;
        LinearLayout $this$view_u24lambda_u240 = UiKt.column$default(host, 0, 1, null);
        $this$view_u24lambda_u240.addView(header("Диалог", false));
        $this$view_u24lambda_u240.addView(this.scroller, UiKt.lp$default(0, 0, 1.0f, 1, null));
        LinearLayout row = (LinearLayout) UiKt.pad(UiKt.rowLayout(host, 8), 14, 6);
        row.addView(this.input, UiKt.lp(0, UiKt.dp((Context) host, 44), 1.0f));
        FrameLayout $this$view_u24lambda_u240_u240 = new FrameLayout(host);
        $this$view_u24lambda_u240_u240.setBackground(UiKt.glassBg(host, getSkin(), 22, getSkin().getAccent(), getSkin().getAccent()));
        $this$view_u24lambda_u240_u240.setContentDescription("Отправить");
        $this$view_u24lambda_u240_u240.addView(new IconView(host, Ic.SEND, getSkin().getOnAccent(), 0, 8, null), new FrameLayout.LayoutParams(-2, -2, 17));
        UiKt.tap($this$view_u24lambda_u240_u240, new Function0() { // from class: com.jarvis.app.ui.ChatScreen$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ChatScreen.view$lambda$0$0$0(this.f$0);
            }
        });
        row.addView($this$view_u24lambda_u240_u240, UiKt.lp$default(UiKt.dp((Context) host, 44), UiKt.dp((Context) host, 44), 0.0f, 4, null));
        LinearLayout.LayoutParams $this$view_u24lambda_u240_u241 = UiKt.lp$default(0, 0, 0.0f, 7, null);
        $this$view_u24lambda_u240_u241.bottomMargin = UiKt.dp((Context) host, 30);
        Unit unit = Unit.INSTANCE;
        $this$view_u24lambda_u240.addView(row, $this$view_u24lambda_u240_u241);
        this.view = $this$view_u24lambda_u240;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean input$lambda$0$0(ChatScreen this$0, TextView textView, int i, KeyEvent keyEvent) {
        this$0.submit();
        return true;
    }

    @Override // com.jarvis.app.ui.Screen
    public View getView() {
        return this.view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit view$lambda$0$0$0(ChatScreen this$0) {
        this$0.submit();
        return Unit.INSTANCE;
    }

    private final void submit() {
        Editable text = this.input.getText();
        String text2 = text != null ? text.toString() : null;
        if (text2 == null) {
            text2 = "";
        }
        if (StringsKt.isBlank(text2)) {
            return;
        }
        this.input.setText("");
        getHost().send(text2);
    }

    private final View bubble(String text, boolean mine) {
        GradientDrawable gradientDrawableGlassBg$default;
        JarvisActivity host = getHost();
        TextView t = UiKt.label(host, (8 & 1) != 0 ? "" : text, (8 & 2) != 0 ? 14.0f : 15.0f, (!mine || getSkin().getLight()) ? getSkin().getText() : -1, (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0);
        t.setPadding(UiKt.dp((Context) getHost(), 13), UiKt.dp((Context) getHost(), 10), UiKt.dp((Context) getHost(), 13), UiKt.dp((Context) getHost(), 10));
        JarvisActivity host2 = getHost();
        if (mine) {
            gradientDrawableGlassBg$default = UiKt.glassBg(host2, getSkin(), 14, UiKt.alpha(getSkin().getAccent(), getSkin().getLight() ? 0.25f : 0.62f), getSkin().getAccent());
        } else {
            gradientDrawableGlassBg$default = UiKt.glassBg$default(host2, getSkin(), 14, 0, 0, 12, null);
        }
        t.setBackground(gradientDrawableGlassBg$default);
        t.setMaxWidth((int) (getHost().getResources().getDisplayMetrics().widthPixels * 0.82f));
        FrameLayout $this$bubble_u24lambda_u240 = new FrameLayout(getHost());
        $this$bubble_u24lambda_u240.addView(t, new FrameLayout.LayoutParams(-2, -2, mine ? GravityCompat.END : GravityCompat.START));
        return $this$bubble_u24lambda_u240;
    }

    @Override // com.jarvis.app.ui.Screen
    public void onShow() {
        onData();
    }

    @Override // com.jarvis.app.ui.Screen
    public void onData() {
        this.list.removeAllViews();
        List<ConversationStore.Bubble> listAll = ConversationStore.INSTANCE.all();
        if (listAll.isEmpty()) {
            LinearLayout linearLayout = this.list;
            JarvisActivity host = getHost();
            linearLayout.addView(UiKt.label(host, (8 & 1) != 0 ? "" : "Здесь будет ваш разговор с Джарвисом, голосом и текстом.", (8 & 2) != 0 ? 14.0f : 14.0f, getSkin().getMuted(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0));
        }
        Iterable $this$forEach$iv = CollectionsKt.takeLast(listAll, 60);
        for (Object element$iv : $this$forEach$iv) {
            ConversationStore.Bubble b = (ConversationStore.Bubble) element$iv;
            this.list.addView(bubble(b.getText(), b.getRole() == ConversationStore.Bubble.Role.USER));
        }
        this.scroller.post(new Runnable() { // from class: com.jarvis.app.ui.ChatScreen$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ChatScreen.onData$lambda$1(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onData$lambda$1(ChatScreen this$0) {
        this$0.scroller.fullScroll(130);
    }

    /* JADX INFO: compiled from: Screens.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/jarvis/app/ui/ChatScreen$Companion;", "", "<init>", "()V", "MAX_ITEMS", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
