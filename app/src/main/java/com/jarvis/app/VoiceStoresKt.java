package com.jarvis.app;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import org.json.JSONArray;

/* JADX INFO: compiled from: VoiceStores.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H\u0002\u001a\u0012\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\u00020\u0007H\u0002¨\u0006\b"}, d2 = {"voicePrefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "Landroid/content/Context;", "strings", "", "", "Lorg/json/JSONArray;", "app_debug"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class VoiceStoresKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final SharedPreferences voicePrefs(Context $this$voicePrefs) {
        return $this$voicePrefs.getApplicationContext().getSharedPreferences("jarvis_voice_data", 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<String> strings(JSONArray $this$strings) {
        int length = $this$strings.length();
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            int it = i;
            arrayList.add($this$strings.getString(it));
        }
        return arrayList;
    }
}
