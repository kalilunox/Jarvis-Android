package com.jarvis.app.ui;

import android.content.Context;
import android.graphics.Color;
import com.jarvis.app.JarvisSettings;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.codec.language.bm.Rule;
import org.apache.commons.compress.archivers.cpio.CpioConstants;

/* JADX INFO: compiled from: Ui.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u000e\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/jarvis/app/ui/Skins;", "", "<init>", "()V", "c", "", "hex", "", Rule.ALL, "", "Lcom/jarvis/app/ui/Skin;", "getALL", "()Ljava/util/List;", "current", "context", "Landroid/content/Context;", "set", "", "id", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Skins {
    public static final Skins INSTANCE = new Skins();
    private static final List<Skin> ALL = CollectionsKt.listOf((Object[]) new Skin[]{new Skin("reactor", "Реактор", INSTANCE.c("#040811"), INSTANCE.c("#8F0B1C34"), INSTANCE.c("#524696FF"), INSTANCE.c("#ECF4FF"), INSTANCE.c("#8FA8C6"), INSTANCE.c("#35A6FF"), INSTANCE.c("#00DFFF"), INSTANCE.c("#FF8E30"), INSTANCE.c("#8B42FF"), INSTANCE.c("#03121F"), 0, 0, 0, false, 0, 126976, null), new Skin("gold", "Золото", INSTANCE.c("#0B0704"), INSTANCE.c("#8F2C1D0C"), INSTANCE.c("#52ECB258"), INSTANCE.c("#F8EFE0"), INSTANCE.c("#B9A486"), INSTANCE.c("#EEB45A"), INSTANCE.c("#FFD68C"), INSTANCE.c("#FF7832"), INSTANCE.c("#DC6E3C"), INSTANCE.c("#1C1204"), 0, 0, 0, false, INSTANCE.c("#3A2E20"), 61440, null), new Skin("mark", "Марк", INSTANCE.c("#0B0304"), INSTANCE.c("#8F2E0C0E"), INSTANCE.c("#57FF5C4C"), INSTANCE.c("#FAECEA"), INSTANCE.c("#BB9692"), INSTANCE.c("#FF5A48"), INSTANCE.c("#FFBE78"), INSTANCE.c("#FFC446"), INSTANCE.c("#FF5078"), INSTANCE.c("#230503"), 0, 0, 0, false, INSTANCE.c("#3A2426"), 61440, null), new Skin("stealth", "Стелс", INSTANCE.c("#050506"), INSTANCE.c("#8F1C1D21"), INSTANCE.c("#3DCDD2DE"), INSTANCE.c("#EFF0F3"), INSTANCE.c("#9A9CA6"), INSTANCE.c("#D7DBE4"), INSTANCE.c("#FFFFFF"), INSTANCE.c("#AAB0BE"), INSTANCE.c("#969CAC"), INSTANCE.c("#111111"), 0, 0, 0, false, INSTANCE.c("#2E2F35"), 61440, null), new Skin("light", "Светлая", INSTANCE.c("#E7EEF6"), INSTANCE.c("#B8FFFFFF"), INSTANCE.c("#421E5AAA"), INSTANCE.c("#0E2036"), INSTANCE.c("#587089"), INSTANCE.c("#0A74E0"), INSTANCE.c("#00AADC"), INSTANCE.c("#E6781E"), INSTANCE.c("#7846E6"), INSTANCE.c("#FFFFFF"), INSTANCE.c("#0C9A61"), 0, 0, true, INSTANCE.c("#22334A"), CpioConstants.C_ISBLK, null)});

    private Skins() {
    }

    private final int c(String hex) {
        return Color.parseColor(hex);
    }

    public final List<Skin> getALL() {
        return ALL;
    }

    public final Skin current(Context context) {
        Object element$iv;
        Skin it;
        Intrinsics.checkNotNullParameter(context, "context");
        String id = JarvisSettings.INSTANCE.prefs(context).getString("ui_skin", "reactor");
        Iterable $this$firstOrNull$iv = ALL;
        Iterator it2 = $this$firstOrNull$iv.iterator();
        do {
            if (!it2.hasNext()) {
                element$iv = null;
                break;
            }
            element$iv = it2.next();
            it = (Skin) element$iv;
        } while (!Intrinsics.areEqual(it.getId(), id));
        Skin skin = (Skin) element$iv;
        return skin == null ? ALL.get(0) : skin;
    }

    public final void set(Context context, String id) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(id, "id");
        JarvisSettings.INSTANCE.prefs(context).edit().putString("ui_skin", id).apply();
    }
}
