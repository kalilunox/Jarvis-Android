package com.jarvis.app.ui;

import android.graphics.Color;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HomeLive.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0000¨\u0006\u0003"}, d2 = {"darkened", "Lcom/jarvis/app/ui/Skin;", "skin", "app_debug"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class HomeLiveKt {
    public static final Skin darkened(Skin skin) {
        Intrinsics.checkNotNullParameter(skin, "skin");
        return !skin.getLight() ? skin : skin.copy((96131 & 1) != 0 ? skin.id : null, (96131 & 2) != 0 ? skin.title : null, (96131 & 4) != 0 ? skin.bg : Color.parseColor("#05070D"), (96131 & 8) != 0 ? skin.glass : Color.parseColor("#8F0B1C34"), (96131 & 16) != 0 ? skin.line : Color.parseColor("#524696FF"), (96131 & 32) != 0 ? skin.text : Color.parseColor("#ECF4FF"), (96131 & 64) != 0 ? skin.muted : Color.parseColor("#8FA8C6"), (96131 & 128) != 0 ? skin.accent : 0, (96131 & 256) != 0 ? skin.cyan : 0, (96131 & 512) != 0 ? skin.hot : 0, (96131 & 1024) != 0 ? skin.violet : 0, (96131 & 2048) != 0 ? skin.onAccent : Color.parseColor("#FFFFFF"), (96131 & 4096) != 0 ? skin.good : 0, (96131 & 8192) != 0 ? skin.warn : 0, (96131 & 16384) != 0 ? skin.danger : 0, (96131 & 32768) != 0 ? skin.light : false, (96131 & 65536) != 0 ? skin.metal : 0);
    }
}
