package com.jarvis.app.ui;

import android.graphics.Color;
import kotlin.Metadata;

/* JADX INFO: compiled from: JarvisActivity.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"mix", "", "a", "b", "k", "", "app_debug"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class JarvisActivityKt {
    public static final int mix(int a, int b, float k) {
        float r = Color.red(a) + ((Color.red(b) - Color.red(a)) * k);
        float g = Color.green(a) + ((Color.green(b) - Color.green(a)) * k);
        float bl = Color.blue(a) + ((Color.blue(b) - Color.blue(a)) * k);
        return Color.rgb((int) r, (int) g, (int) bl);
    }
}
