package com.jarvis.app;

import android.content.Context;
import com.jarvis.app.ui.JarvisActivity;
import com.jarvis.core.notes.CustomRoutines;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: VoiceStores.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H\u0002J\u0018\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\u001e\u0010\u000f\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\r2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\rH\u0016J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/jarvis/app/PrefsCustomRoutines;", "Lcom/jarvis/core/notes/CustomRoutines;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "all", "Lorg/json/JSONObject;", "save", "", "value", "steps", "", "", "name", "put", "remove", "", "names", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PrefsCustomRoutines implements CustomRoutines {
    private final Context context;

    public PrefsCustomRoutines(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    private final JSONObject all() {
        Object objM392constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            PrefsCustomRoutines $this$all_u24lambda_u240 = this;
            String string = VoiceStoresKt.voicePrefs($this$all_u24lambda_u240.context).getString(JarvisActivity.ROUTINES, "{}");
            if (string == null) {
                string = "";
            }
            objM392constructorimpl = Result.m392constructorimpl(new JSONObject(string));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        JSONObject jSONObject = new JSONObject();
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = jSONObject;
        }
        return (JSONObject) objM392constructorimpl;
    }

    private final void save(JSONObject value) {
        VoiceStoresKt.voicePrefs(this.context).edit().putString(JarvisActivity.ROUTINES, value.toString()).apply();
    }

    @Override // com.jarvis.core.notes.CustomRoutines
    public synchronized List<String> steps(String name) {
        JSONArray jSONArrayOptJSONArray;
        Intrinsics.checkNotNullParameter(name, "name");
        JSONObject jSONObjectAll = all();
        String lowerCase = name.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        jSONArrayOptJSONArray = jSONObjectAll.optJSONArray(StringsKt.trim((CharSequence) lowerCase).toString());
        return jSONArrayOptJSONArray != null ? VoiceStoresKt.strings(jSONArrayOptJSONArray) : null;
    }

    @Override // com.jarvis.core.notes.CustomRoutines
    public synchronized void put(String name, List<String> steps) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(steps, "steps");
        JSONObject jSONObjectAll = all();
        String lowerCase = name.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        JSONObject jSONObjectPut = jSONObjectAll.put(StringsKt.trim((CharSequence) lowerCase).toString(), new JSONArray((Collection) steps));
        Intrinsics.checkNotNullExpressionValue(jSONObjectPut, "put(...)");
        save(jSONObjectPut);
    }

    @Override // com.jarvis.core.notes.CustomRoutines
    public synchronized boolean remove(String name) {
        boolean had;
        Intrinsics.checkNotNullParameter(name, "name");
        JSONObject value = all();
        String lowerCase = name.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        had = value.remove(StringsKt.trim((CharSequence) lowerCase).toString()) != null;
        save(value);
        return had;
    }

    @Override // com.jarvis.core.notes.CustomRoutines
    public synchronized List<String> names() {
        Iterator<String> itKeys;
        itKeys = all().keys();
        Intrinsics.checkNotNullExpressionValue(itKeys, "keys(...)");
        return SequencesKt.toList(SequencesKt.asSequence(itKeys));
    }
}
