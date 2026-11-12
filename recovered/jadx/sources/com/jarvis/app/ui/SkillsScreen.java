package com.jarvis.app.ui;

import android.view.View;
import android.widget.LinearLayout;
import com.jarvis.app.AssistantCapabilities;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.concurrent.AbstractCircuitBreaker;

/* JADX INFO: compiled from: Screens.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0014"}, d2 = {"Lcom/jarvis/app/ui/SkillsScreen;", "Lcom/jarvis/app/ui/Screen;", "host", "Lcom/jarvis/app/ui/JarvisActivity;", "<init>", "(Lcom/jarvis/app/ui/JarvisActivity;)V", "body", "Landroid/widget/LinearLayout;", AbstractCircuitBreaker.PROPERTY_NAME, "Ljava/util/HashSet;", "", "Lkotlin/collections/HashSet;", "view", "Landroid/view/View;", "getView", "()Landroid/view/View;", "onShow", "", "render", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SkillsScreen extends Screen {
    private static final Companion Companion = new Companion(null);
    private static final Map<String, String> ICONS = MapsKt.mapOf(TuplesKt.to("Устройство", Ic.SUN), TuplesKt.to("Приложения", Ic.GRID), TuplesKt.to("Связь", Ic.PHONE), TuplesKt.to("Музыка", Ic.MUSIC), TuplesKt.to("Информация", Ic.INFO));
    private final LinearLayout body;
    private final HashSet<String> open;
    private final View view;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkillsScreen(JarvisActivity host) {
        super(host);
        Intrinsics.checkNotNullParameter(host, "host");
        this.body = UiKt.column(host, 10);
        this.open = new HashSet<>();
        LinearLayout $this$view_u24lambda_u240 = UiKt.column$default(host, 0, 1, null);
        $this$view_u24lambda_u240.addView(header("Навыки Jarvis", false));
        $this$view_u24lambda_u240.addView(scroll(this.body), UiKt.lp$default(0, 0, 1.0f, 1, null));
        this.view = $this$view_u24lambda_u240;
    }

    @Override // com.jarvis.app.ui.Screen
    public View getView() {
        return this.view;
    }

    @Override // com.jarvis.app.ui.Screen
    public void onShow() {
        render();
    }

    private final void render() {
        Object answer$iv$iv$iv;
        this.body.removeAllViews();
        Iterable $this$groupBy$iv = AssistantCapabilities.INSTANCE.getALL();
        Map destination$iv$iv = new LinkedHashMap();
        for (Object element$iv$iv : $this$groupBy$iv) {
            AssistantCapabilities.Command it = (AssistantCapabilities.Command) element$iv$iv;
            String category = it.getCategory();
            Object value$iv$iv$iv = destination$iv$iv.get(category);
            if (value$iv$iv$iv == null) {
                answer$iv$iv$iv = new ArrayList();
                destination$iv$iv.put(category, answer$iv$iv$iv);
            } else {
                answer$iv$iv$iv = value$iv$iv$iv;
            }
            Object key$iv$iv$iv = answer$iv$iv$iv;
            List list$iv$iv = (List) key$iv$iv$iv;
            list$iv$iv.add(element$iv$iv);
        }
        Map $this$forEach$iv = destination$iv$iv;
        int $i$f$forEach = 0;
        for (Map.Entry element$iv : $this$forEach$iv.entrySet()) {
            final String category2 = (String) element$iv.getKey();
            Iterable<AssistantCapabilities.Command> commands = (List) element$iv.getValue();
            LinearLayout linearLayout = this.body;
            SkillsScreen skillsScreen = this;
            String strJoinToString$default = CollectionsKt.joinToString$default(CollectionsKt.take(commands, 3), ", ", null, null, 0, null, new Function1() { // from class: com.jarvis.app.ui.SkillsScreen$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SkillsScreen.render$lambda$1$0((AssistantCapabilities.Command) obj);
                }
            }, 30, null);
            String str = ICONS.get(category2);
            if (str == null) {
                str = Ic.GRID;
            }
            linearLayout.addView(Screen.card$default(skillsScreen, category2, strJoinToString$default, str, null, 0, 0, new Function0() { // from class: com.jarvis.app.ui.SkillsScreen$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SkillsScreen.render$lambda$1$1(this.f$0, category2);
                }
            }, 56, null));
            if (this.open.contains(category2)) {
                for (AssistantCapabilities.Command c : commands) {
                    String str2 = (String) CollectionsKt.firstOrNull((List) c.getExamples());
                    final String example = str2 != null ? StringsKt.removePrefix(str2, (CharSequence) "джарвис, ") : null;
                    if (example == null) {
                        example = "";
                    }
                    boolean safe = c.getRisk() == AssistantCapabilities.RiskTag.SAFE || c.getRisk() == AssistantCapabilities.RiskTag.ONLINE;
                    Map $this$forEach$iv2 = $this$forEach$iv;
                    int $i$f$forEach2 = $i$f$forEach;
                    this.body.addView(Screen.card$default(this, c.getTitle(), "«" + example + "»", null, null, 0, 0, (!safe || StringsKt.isBlank(example)) ? null : new Function0() { // from class: com.jarvis.app.ui.SkillsScreen$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return SkillsScreen.render$lambda$1$2$0(this.f$0, example);
                        }
                    }, 60, null));
                    $this$forEach$iv = $this$forEach$iv2;
                    $i$f$forEach = $i$f$forEach2;
                }
            }
            $this$forEach$iv = $this$forEach$iv;
            $i$f$forEach = $i$f$forEach;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence render$lambda$1$0(AssistantCapabilities.Command it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getTitle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit render$lambda$1$1(SkillsScreen this$0, String $category) {
        if (!this$0.open.add($category)) {
            this$0.open.remove($category);
        }
        this$0.render();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit render$lambda$1$2$0(SkillsScreen this$0, String $example) {
        this$0.getHost().send($example);
        this$0.getHost().show(JarvisActivity.CHAT);
        return Unit.INSTANCE;
    }

    /* JADX INFO: compiled from: Screens.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/jarvis/app/ui/SkillsScreen$Companion;", "", "<init>", "()V", "ICONS", "", "", "getICONS", "()Ljava/util/Map;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Map<String, String> getICONS() {
            return SkillsScreen.ICONS;
        }
    }
}
