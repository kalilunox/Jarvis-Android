package com.jarvis.app;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.jarvis.app.ui.JarvisActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JarvisTileService.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016¨\u0006\u0007"}, d2 = {"Lcom/jarvis/app/JarvisTileService;", "Landroid/service/quicksettings/TileService;", "<init>", "()V", "onStartListening", "", "onClick", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JarvisTileService extends TileService {
    @Override // android.service.quicksettings.TileService
    public void onStartListening() {
        Tile $this$onStartListening_u24lambda_u240 = getQsTile();
        if ($this$onStartListening_u24lambda_u240 != null) {
            $this$onStartListening_u24lambda_u240.setLabel("J.A.R.V.I.S.");
            $this$onStartListening_u24lambda_u240.setState(VoiceController.INSTANCE.isRunning() ? 2 : 1);
            $this$onStartListening_u24lambda_u240.updateTile();
        }
    }

    @Override // android.service.quicksettings.TileService
    public void onClick() {
        Intent intent = new Intent(this, (Class<?>) JarvisActivity.class).setAction("android.intent.action.ASSIST").addFlags(805306368);
        Intrinsics.checkNotNullExpressionValue(intent, "addFlags(...)");
        if (Build.VERSION.SDK_INT >= 34) {
            startActivityAndCollapse(PendingIntent.getActivity(this, 0, intent, AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL));
        } else {
            startActivityAndCollapse(intent);
        }
    }
}
