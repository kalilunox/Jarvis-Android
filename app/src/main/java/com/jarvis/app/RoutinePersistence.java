package com.jarvis.app;

import android.content.Context;
import com.jarvis.app.ui.JarvisActivity;
import com.jarvis.core.planner.ActionPlan;
import com.jarvis.core.planner.ActionPlanStatus;
import com.jarvis.core.routines.RoutineStatus;
import com.jarvis.core.routines.SavedRoutine;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: RoutinePersistence.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fJ\u001c\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\tJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fJ&\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0018R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/jarvis/app/RoutinePersistence;", "", "<init>", "()V", "PREF", "", "KEY", "SEEDED", "load", "", "Lcom/jarvis/core/routines/SavedRoutine;", "context", "Landroid/content/Context;", "save", "", JarvisActivity.ROUTINES, RoutinePersistence.SEEDED, "", "markSeeded", "stub", "id", "title", "nl", "status", "Lcom/jarvis/core/routines/RoutineStatus;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RoutinePersistence {
    public static final RoutinePersistence INSTANCE = new RoutinePersistence();
    private static final String KEY = "items";
    private static final String PREF = "jarvis_routines_v1";
    private static final String SEEDED = "seeded";

    private RoutinePersistence() {
    }

    public final List<SavedRoutine> load(Context context) {
        Object objM392constructorimpl;
        Object objM392constructorimpl2;
        Intrinsics.checkNotNullParameter(context, "context");
        String raw = context.getSharedPreferences("jarvis_prefs", 0).getString("items", null);
        if (raw == null) {
            return CollectionsKt.emptyList();
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            RoutinePersistence $this$load_u24lambda_u240 = this;
            JSONArray arr = new JSONArray(raw);
            List $this$load_u24lambda_u240_u240 = CollectionsKt.createListBuilder();
            int length = arr.length();
            for (int i = 0; i < length; i++) {
                JSONObject o = arr.getJSONObject(i);
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    String string = o.getString("status");
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    objM392constructorimpl2 = Result.m392constructorimpl(RoutineStatus.valueOf(string));
                } catch (Throwable th) {
                    Result.Companion companion3 = Result.INSTANCE;
                    objM392constructorimpl2 = Result.m392constructorimpl(ResultKt.createFailure(th));
                }
                RoutineStatus routineStatus = RoutineStatus.SAVED;
                if (Result.m398isFailureimpl(objM392constructorimpl2)) {
                    objM392constructorimpl2 = routineStatus;
                }
                RoutineStatus status = (RoutineStatus) objM392constructorimpl2;
                if (status == RoutineStatus.SAVED || status == RoutineStatus.DISABLED) {
                    String string2 = o.getString("id");
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                    String string3 = o.getString("title");
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                    String string4 = o.getString("nl");
                    Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                    $this$load_u24lambda_u240_u240.add($this$load_u24lambda_u240.stub(string2, string3, string4, status));
                }
            }
            objM392constructorimpl = Result.m392constructorimpl(CollectionsKt.build($this$load_u24lambda_u240_u240));
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th2));
        }
        List listEmptyList = CollectionsKt.emptyList();
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = listEmptyList;
        }
        return (List) objM392constructorimpl;
    }

    public final void save(Context context, List<SavedRoutine> routines) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(routines, "routines");
        JSONArray arr = new JSONArray();
        List<SavedRoutine> $this$forEach$iv = routines;
        for (Object element$iv : $this$forEach$iv) {
            SavedRoutine r = (SavedRoutine) element$iv;
            arr.put(new JSONObject().put("id", r.getId()).put("title", r.getTitle()).put("nl", r.getNaturalLanguage()).put("status", r.getStatus().name()));
        }
        context.getSharedPreferences("jarvis_prefs", 0).edit().putString("items", arr.toString()).putBoolean(SEEDED, true).apply();
    }

    public final boolean seeded(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return context.getSharedPreferences("jarvis_prefs", 0).getBoolean(SEEDED, false);
    }

    public final void markSeeded(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        context.getSharedPreferences("jarvis_prefs", 0).edit().putBoolean(SEEDED, true).apply();
    }

    public final SavedRoutine stub(String id, String title, String nl, RoutineStatus status) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(nl, "nl");
        Intrinsics.checkNotNullParameter(status, "status");
        String strTake = StringsKt.take(title, 120);
        if (StringsKt.isBlank(strTake)) {
            strTake = "Протокол";
        }
        return new SavedRoutine(id, strTake, StringsKt.take(nl, 2000), new ActionPlan("plan-" + id, "goal-" + id, ActionPlanStatus.READY, CollectionsKt.emptyList(), null, false, null, CollectionsKt.listOf("persisted"), 112, null), status, System.currentTimeMillis(), null, null, null, 448, null);
    }
}
