package com.jarvis.app;

import android.content.Context;
import com.jarvis.core.routines.EventRoutines;
import com.jarvis.core.routines.EventRule;
import com.jarvis.core.routines.EventTrigger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;

/* JADX INFO: compiled from: VoiceStores.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002J\u0016\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\bH\u0016J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/jarvis/app/PrefsEventRoutines;", "Lcom/jarvis/core/routines/EventRoutines;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "load", "", "Lcom/jarvis/core/routines/EventRule;", "save", "", "rules", "put", "rule", "remove", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PrefsEventRoutines implements EventRoutines {
    private final Context context;

    public PrefsEventRoutines(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    private final List<EventRule> load() {
        Object objM392constructorimpl;
        int $i$f$mapNotNull;
        Iterable $this$mapNotNullTo$iv$iv;
        Object element$iv;
        try {
            Result.Companion companion = Result.INSTANCE;
            PrefsEventRoutines $this$load_u24lambda_u240 = this;
            objM392constructorimpl = Result.m392constructorimpl(VoiceStoresKt.strings(new JSONArray(VoiceStoresKt.voicePrefs($this$load_u24lambda_u240.context).getString("event_rules", "[]"))));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        List listEmptyList = CollectionsKt.emptyList();
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = listEmptyList;
        }
        Iterable $this$mapNotNull$iv = (Iterable) objM392constructorimpl;
        int $i$f$mapNotNull2 = 0;
        Collection destination$iv$iv = new ArrayList();
        Iterable $this$mapNotNullTo$iv$iv2 = $this$mapNotNull$iv;
        for (Object element$iv$iv$iv : $this$mapNotNullTo$iv$iv2) {
            String line = (String) element$iv$iv$iv;
            Iterable $this$mapNotNull$iv2 = $this$mapNotNull$iv;
            List parts = StringsKt.split$default((CharSequence) line, new char[]{'|'}, false, 2, 2, (Object) null);
            Iterable $this$firstOrNull$iv = EventTrigger.getEntries();
            Iterator<EventTrigger> it = $this$firstOrNull$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    $i$f$mapNotNull = $i$f$mapNotNull2;
                    $this$mapNotNullTo$iv$iv = $this$mapNotNullTo$iv$iv2;
                    element$iv = null;
                    break;
                }
                element$iv = it.next();
                EventTrigger it2 = (EventTrigger) element$iv;
                $i$f$mapNotNull = $i$f$mapNotNull2;
                $this$mapNotNullTo$iv$iv = $this$mapNotNullTo$iv$iv2;
                if (Intrinsics.areEqual(it2.name(), parts.get(0))) {
                    break;
                }
                $i$f$mapNotNull2 = $i$f$mapNotNull;
                $this$mapNotNullTo$iv$iv2 = $this$mapNotNullTo$iv$iv;
            }
            EventTrigger trigger = (EventTrigger) element$iv;
            EventRule eventRule = trigger != null ? new EventRule(trigger, (String) (1 < parts.size() ? parts.get(1) : "")) : null;
            if (eventRule != null) {
                destination$iv$iv.add(eventRule);
            }
            $this$mapNotNull$iv = $this$mapNotNull$iv2;
            $i$f$mapNotNull2 = $i$f$mapNotNull;
            $this$mapNotNullTo$iv$iv2 = $this$mapNotNullTo$iv$iv;
        }
        return (List) destination$iv$iv;
    }

    private final void save(List<EventRule> rules) {
        List<EventRule> $this$map$iv = rules;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            EventRule it = (EventRule) item$iv$iv;
            destination$iv$iv.add(it.getTrigger().name() + "|" + it.getArg());
        }
        List lines = (List) destination$iv$iv;
        VoiceStoresKt.voicePrefs(this.context).edit().putString("event_rules", new JSONArray((Collection) lines).toString()).apply();
    }

    @Override // com.jarvis.core.routines.EventRoutines
    public synchronized List<EventRule> rules() {
        return load();
    }

    @Override // com.jarvis.core.routines.EventRoutines
    public synchronized void put(EventRule rule) {
        Intrinsics.checkNotNullParameter(rule, "rule");
        save(CollectionsKt.plus((Collection<? extends EventRule>) CollectionsKt.minus(load(), rule), rule));
    }

    @Override // com.jarvis.core.routines.EventRoutines
    public synchronized void remove(EventRule rule) {
        Intrinsics.checkNotNullParameter(rule, "rule");
        save(CollectionsKt.minus(load(), rule));
    }
}
