package com.jarvis.app.ui;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jarvis.app.VoiceController;
import com.jarvis.core.runtime.LatencyStatsStore;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Screens.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000I\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0001\u0012\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\fH\u0002J\b\u0010\u001c\u001a\u00020\u0015H\u0002J\b\u0010\u001d\u001a\u00020\u0015H\u0016J\b\u0010\u001e\u001a\u00020\u0015H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bj\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013R\u0014\u0010\u0018\u001a\u00020\u0019X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006 "}, d2 = {"Lcom/jarvis/app/ui/StatusScreen;", "Lcom/jarvis/app/ui/Screen;", "host", "Lcom/jarvis/app/ui/JarvisActivity;", "<init>", "(Lcom/jarvis/app/ui/JarvisActivity;)V", "cpu", "Lcom/jarvis/app/ui/RingView;", "ram", "bat", "values", "Ljava/util/HashMap;", "", "Landroid/widget/TextView;", "Lkotlin/collections/HashMap;", "services", "Landroid/widget/LinearLayout;", "tick", "com/jarvis/app/ui/StatusScreen$tick$1", "Lcom/jarvis/app/ui/StatusScreen$tick$1;", "kv", "", "box", "name", "view", "Landroid/view/View;", "getView", "()Landroid/view/View;", "refresh", "onShow", "onHide", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class StatusScreen extends Screen {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final long TICK_MS = 2000;
    private final RingView bat;
    private final RingView cpu;
    private final RingView ram;
    private final LinearLayout services;
    private final StatusScreen$tick$1 tick;
    private final HashMap<String, TextView> values;
    private final View view;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v8, types: [com.jarvis.app.ui.StatusScreen$tick$1] */
    public StatusScreen(JarvisActivity host) {
        super(host);
        Intrinsics.checkNotNullParameter(host, "host");
        this.cpu = new RingView(host, host.getSkin(), "Процессор");
        this.ram = new RingView(host, host.getSkin(), "Память");
        this.bat = new RingView(host, host.getSkin(), "Заряд");
        this.values = new HashMap<>();
        this.services = UiKt.column$default(host, 0, 1, null);
        this.tick = new Runnable() { // from class: com.jarvis.app.ui.StatusScreen$tick$1
            @Override // java.lang.Runnable
            public void run() {
                this.this$0.refresh();
                this.this$0.getView().postDelayed(this, 2000L);
            }
        };
        LinearLayout $this$view_u24lambda_u240 = UiKt.column$default(host, 0, 1, null);
        $this$view_u24lambda_u240.addView(header("Система Jarvis", true));
        LinearLayout body = UiKt.column(host, 10);
        View viewPad = UiKt.pad(UiKt.rowLayout(host, 8), 12);
        LinearLayout $this$view_u24lambda_u240_u240 = (LinearLayout) viewPad;
        $this$view_u24lambda_u240_u240.setBackground(UiKt.glassBg$default(host, getSkin(), 0, 0, 0, 14, null));
        LinearLayout rings = (LinearLayout) viewPad;
        Iterable $this$forEach$iv = CollectionsKt.listOf((Object[]) new RingView[]{this.cpu, this.ram, this.bat});
        for (Object element$iv : $this$forEach$iv) {
            RingView it = (RingView) element$iv;
            rings.addView(it, UiKt.lp(0, UiKt.dp((Context) host, 92), 1.0f));
        }
        body.addView(rings);
        LinearLayout table = UiKt.column$default(host, 0, 1, null);
        table.setBackground(UiKt.glassBg$default(host, getSkin(), 0, 0, 0, 14, null));
        Iterable $this$forEach$iv2 = CollectionsKt.listOf((Object[]) new String[]{"Температура", "Сеть", "Свободно", "Ответ, среднее"});
        for (Object element$iv2 : $this$forEach$iv2) {
            String it2 = (String) element$iv2;
            kv(table, it2);
        }
        body.addView(table);
        body.addView(section("Службы"));
        LinearLayout $this$view_u24lambda_u240_u244 = this.services;
        $this$view_u24lambda_u240_u244.setBackground(UiKt.glassBg$default(host, getSkin(), 0, 0, 0, 14, null));
        body.addView($this$view_u24lambda_u240_u244);
        $this$view_u24lambda_u240.addView(scroll(body), UiKt.lp$default(0, 0, 1.0f, 1, null));
        this.view = $this$view_u24lambda_u240;
    }

    private final void kv(LinearLayout box, String name) {
        JarvisActivity host = getHost();
        TextView v = UiKt.label(host, (8 & 1) != 0 ? "" : "", (8 & 2) != 0 ? 14.0f : 14.0f, getSkin().getAccent(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host, 0, 2, null) : Fonts.display$default(Fonts.INSTANCE, getHost(), 0, 2, null), (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0);
        this.values.put(name, v);
        View viewPad = UiKt.pad(UiKt.rowLayout$default(getHost(), 0, 1, null), 12, 10);
        LinearLayout $this$kv_u24lambda_u240 = (LinearLayout) viewPad;
        JarvisActivity host2 = getHost();
        $this$kv_u24lambda_u240.addView(UiKt.label(host2, (8 & 1) != 0 ? "" : name, (8 & 2) != 0 ? 14.0f : 14.0f, getSkin().getText(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host2, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0), UiKt.lp(0, -2, 1.0f));
        $this$kv_u24lambda_u240.addView(v);
        box.addView(viewPad);
    }

    @Override // com.jarvis.app.ui.Screen
    public View getView() {
        return this.view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refresh() {
        String strSubstringAfter;
        String string;
        String strTake;
        StatusScreen statusScreen = this;
        statusScreen.cpu.setPercent(Device.INSTANCE.cpuPercent());
        statusScreen.ram.setPercent(Device.INSTANCE.ramPercent(getHost()));
        statusScreen.bat.setPercent(Device.INSTANCE.battery(getHost()));
        TextView textView = statusScreen.values.get("Температура");
        if (textView != null) {
            textView.setText(Device.INSTANCE.temperature(getHost()) + "°C");
        }
        TextView textView2 = statusScreen.values.get("Сеть");
        if (textView2 != null) {
            textView2.setText(Device.INSTANCE.network(getHost()));
        }
        TextView textView3 = statusScreen.values.get("Свободно");
        if (textView3 != null) {
            textView3.setText(Device.INSTANCE.freeStorageGb() + " ГБ");
        }
        TextView textView4 = statusScreen.values.get("Ответ, среднее");
        if (textView4 != null) {
            String str = (String) CollectionsKt.firstOrNull((List) LatencyStatsStore.INSTANCE.summaryLines());
            String str2 = "—";
            if (str != null && (strSubstringAfter = StringsKt.substringAfter(str, ':', "")) != null && (string = StringsKt.trim((CharSequence) strSubstringAfter).toString()) != null && (strTake = StringsKt.take(string, 14)) != null) {
                String str3 = strTake;
                if (!StringsKt.isBlank(str3)) {
                    str2 = str3;
                }
                str2 = str2;
            }
            textView4.setText(str2);
        }
        statusScreen.services.removeAllViews();
        boolean on = getHost().isOn();
        Pair[] pairArr = new Pair[3];
        pairArr[0] = TuplesKt.to("Голосовая служба", on ? "работает" : "выключена");
        int i = 1;
        pairArr[1] = TuplesKt.to("Голос", VoiceController.INSTANCE.isRunning() ? "модель загружена" : "не загружен");
        pairArr[2] = TuplesKt.to("Режим Live", VoiceController.INSTANCE.getLiveTalk() ? "идёт разговор" : "выключен");
        Iterable $this$forEach$iv = CollectionsKt.listOf((Object[]) pairArr);
        for (Object element$iv : $this$forEach$iv) {
            Pair pair = (Pair) element$iv;
            String name = (String) pair.component1();
            String state = (String) pair.component2();
            LinearLayout linearLayout = statusScreen.services;
            View viewPad = UiKt.pad(UiKt.rowLayout$default(getHost(), 0, i, null), 12, 9);
            LinearLayout $this$refresh_u24lambda_u241_u240 = (LinearLayout) viewPad;
            JarvisActivity host = getHost();
            $this$refresh_u24lambda_u241_u240.addView(UiKt.label(host, (8 & 1) != 0 ? "" : name, (8 & 2) != 0 ? 14.0f : 14.0f, getSkin().getText(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0), UiKt.lp(0, -2, 1.0f));
            JarvisActivity host2 = getHost();
            String str4 = state;
            Skin skin = getSkin();
            $this$refresh_u24lambda_u241_u240.addView(UiKt.label(host2, (8 & 1) != 0 ? "" : str4, (8 & 2) != 0 ? 14.0f : 12.0f, on ? skin.getGood() : skin.getMuted(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host2, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0));
            linearLayout.addView(viewPad);
            i = 1;
            statusScreen = this;
        }
    }

    @Override // com.jarvis.app.ui.Screen
    public void onShow() {
        getView().removeCallbacks(this.tick);
        getView().post(this.tick);
    }

    @Override // com.jarvis.app.ui.Screen
    public void onHide() {
        getView().removeCallbacks(this.tick);
    }

    /* JADX INFO: compiled from: Screens.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/jarvis/app/ui/StatusScreen$Companion;", "", "<init>", "()V", "TICK_MS", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
