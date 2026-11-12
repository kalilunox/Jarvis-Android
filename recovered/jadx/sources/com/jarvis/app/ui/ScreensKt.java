package com.jarvis.app.ui;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Screens.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u001a4\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\n0\t¨\u0006\u000b"}, d2 = {"segment", "Landroid/widget/LinearLayout;", "Lcom/jarvis/app/ui/JarvisActivity;", "options", "", "", "selected", "", "onPick", "Lkotlin/Function1;", "", "app_debug"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class ScreensKt {
    public static final LinearLayout segment(final JarvisActivity $this$segment, List<String> options, int selected, final Function1<? super Integer, Unit> onPick) {
        Intrinsics.checkNotNullParameter($this$segment, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(onPick, "onPick");
        LinearLayout row = UiKt.rowLayout($this$segment, 6);
        final ArrayList items = new ArrayList();
        List<String> $this$forEachIndexed$iv = options;
        final int index$iv = 0;
        for (Object item$iv : $this$forEachIndexed$iv) {
            int index$iv2 = index$iv + 1;
            if (index$iv < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String o = (String) item$iv;
            JarvisActivity jarvisActivity = $this$segment;
            TextView $this$segment_u24lambda_u241_u240 = UiKt.label(jarvisActivity, (8 & 1) != 0 ? "" : o, (8 & 2) != 0 ? 14.0f : 13.0f, $this$segment.getSkin().getMuted(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, jarvisActivity, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 17, (8 & 32) != 0 ? Integer.MAX_VALUE : 1);
            $this$segment_u24lambda_u241_u240.setGravity(17);
            UiKt.tap($this$segment_u24lambda_u241_u240, new Function0() { // from class: com.jarvis.app.ui.ScreensKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return ScreensKt.segment$lambda$1$0$0(index$iv, onPick, items, $this$segment);
                }
            });
            items.add($this$segment_u24lambda_u241_u240);
            row.addView($this$segment_u24lambda_u241_u240, UiKt.lp(0, UiKt.dp((Context) $this$segment, 36), 1.0f));
            index$iv = index$iv2;
            $this$forEachIndexed$iv = $this$forEachIndexed$iv;
        }
        segment$paint(items, $this$segment, selected);
        return row;
    }

    private static final void segment$paint(ArrayList<TextView> arrayList, JarvisActivity $this_segment, int sel) {
        int bg;
        float f;
        ArrayList<TextView> $this$forEachIndexed$iv = arrayList;
        int i = 0;
        for (Object item$iv : $this$forEachIndexed$iv) {
            int index$iv = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TextView t = (TextView) item$iv;
            Skin skin = $this_segment.getSkin();
            t.setTextColor(i == sel ? skin.getLight() ? $this_segment.getSkin().getOnAccent() : $this_segment.getSkin().getText() : skin.getMuted());
            JarvisActivity jarvisActivity = $this_segment;
            Skin skin2 = $this_segment.getSkin();
            Skin skin3 = $this_segment.getSkin();
            if (i == sel) {
                bg = skin3.getAccent();
                f = $this_segment.getSkin().getLight() ? 1.0f : 0.32f;
            } else {
                bg = skin3.getBg();
                f = 0.0f;
            }
            int iAlpha = UiKt.alpha(bg, f);
            Skin skin4 = $this_segment.getSkin();
            t.setBackground(UiKt.glassBg(jarvisActivity, skin2, 18, iAlpha, i == sel ? skin4.getAccent() : skin4.getLine()));
            i = index$iv;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit segment$lambda$1$0$0(int $i, Function1 $onPick, ArrayList $items, JarvisActivity $this_segment) {
        segment$paint($items, $this_segment, $i);
        $onPick.invoke(Integer.valueOf($i));
        return Unit.INSTANCE;
    }
}
