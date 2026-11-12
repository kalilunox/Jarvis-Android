package com.jarvis.app;

import android.content.Context;
import android.content.SharedPreferences;
import com.jarvis.core.llm.CloudKeyValidator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SecureKeyMigration.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R \u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/jarvis/app/SecureKeyMigration;", "", "<init>", "()V", "MIGRATION_FLAG", "", "legacyKeys", "", "Lkotlin/Pair;", "lock", "migrate", "", "context", "Landroid/content/Context;", "isMigrated", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SecureKeyMigration {
    private static final String MIGRATION_FLAG = "migrated_keys_v1";
    public static final SecureKeyMigration INSTANCE = new SecureKeyMigration();
    private static final List<Pair<String, String>> legacyKeys = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to("cloud_api_key", "custom"), TuplesKt.to("asr_cloud_api_key", "custom_asr"), TuplesKt.to("tts_cloud_api_key", "custom_tts")});
    private static final Object lock = new Object();

    private SecureKeyMigration() {
    }

    public final void migrate(Context context) {
        Object objM392constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        Context app = context.getApplicationContext();
        synchronized (lock) {
            JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
            Intrinsics.checkNotNull(app);
            SharedPreferences prefs = jarvisSettings.prefs(app);
            if (prefs.getBoolean(MIGRATION_FLAG, false)) {
                return;
            }
            SecureKeyStore store = new SecureKeyStore(app);
            boolean complete = true;
            for (Pair<String, String> pair : legacyKeys) {
                String legacyName = pair.component1();
                String providerId = pair.component2();
                SecureKeyMigration secureKeyMigration = INSTANCE;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    String string = prefs.getString(legacyName, null);
                    if (string == null) {
                        string = "";
                    }
                    objM392constructorimpl = Result.m392constructorimpl(string);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m398isFailureimpl(objM392constructorimpl)) {
                    objM392constructorimpl = "";
                }
                String legacyValue = (String) objM392constructorimpl;
                if (!StringsKt.isBlank(legacyValue)) {
                    String clean = CloudKeyValidator.INSTANCE.sanitizeKey(legacyValue);
                    if (!CloudKeyValidator.INSTANCE.isStubKey(clean)) {
                        store.setKey(providerId, clean);
                        if (!store.hasKey(providerId)) {
                            complete = false;
                        }
                    }
                    prefs.edit().remove(legacyName).apply();
                }
            }
            if (complete) {
                prefs.edit().putBoolean(MIGRATION_FLAG, true).apply();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final boolean isMigrated(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        return jarvisSettings.prefs(applicationContext).getBoolean(MIGRATION_FLAG, false);
    }
}
