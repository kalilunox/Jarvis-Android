package com.jarvis.app.companion;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.jarvis.core.memory.CompanionMemoryService;
import com.jarvis.core.memory.SqliteMemoryRepository;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CompanionMemory.kt */
/* JADX INFO: loaded from: classes18.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/jarvis/app/companion/CompanionMemory;", "", "<init>", "()V", NotificationCompat.CATEGORY_SERVICE, "Lcom/jarvis/core/memory/CompanionMemoryService;", "get", "context", "Landroid/content/Context;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CompanionMemory {
    public static final CompanionMemory INSTANCE = new CompanionMemory();
    private static volatile CompanionMemoryService service;

    private CompanionMemory() {
    }

    public final CompanionMemoryService get(Context context) {
        CompanionMemoryService it;
        Intrinsics.checkNotNullParameter(context, "context");
        CompanionMemoryService it2 = service;
        if (it2 != null) {
            return it2;
        }
        synchronized (this) {
            it = service;
            if (it == null) {
                Context applicationContext = context.getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                it = new CompanionMemoryService(new SqliteMemoryRepository(applicationContext), null, null, null, null, null, 62, null);
                service = it;
            }
        }
        return it;
    }
}
