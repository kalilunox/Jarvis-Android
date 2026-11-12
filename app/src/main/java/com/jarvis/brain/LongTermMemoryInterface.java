package com.jarvis.brain;

import com.google.android.gms.actions.SearchIntents;
import com.jarvis.app.VoiceService;
import com.jarvis.core.storage.JarvisSchema;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: LongTermMemoryInterface.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH&J\"\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u0006H&J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u0010\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H&J\b\u0010\u0012\u001a\u00020\u0013H&J\b\u0010\u0014\u001a\u00020\bH&¨\u0006\u0015À\u0006\u0003"}, d2 = {"Lcom/jarvis/brain/LongTermMemoryInterface;", "", "retrieve", "", "Lcom/jarvis/brain/LongTermMemory$MemoryFact;", SearchIntents.EXTRA_QUERY, "", "limit", "", "add", "", VoiceService.EXTRA_TEXT, "category", "source", "all", "remove", "id", "", "clear", "", "count", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface LongTermMemoryInterface {
    boolean add(String text, String category, String source);

    List<LongTermMemory.MemoryFact> all();

    void clear();

    int count();

    boolean remove(long id);

    List<LongTermMemory.MemoryFact> retrieve(String query, int limit);

    /* JADX INFO: compiled from: LongTermMemoryInterface.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ List retrieve$default(LongTermMemoryInterface longTermMemoryInterface, String str, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: retrieve");
        }
        if ((i2 & 2) != 0) {
            i = 3;
        }
        return longTermMemoryInterface.retrieve(str, i);
    }

    static /* synthetic */ boolean add$default(LongTermMemoryInterface longTermMemoryInterface, String str, String str2, String str3, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: add");
        }
        if ((i & 4) != 0) {
            str3 = JarvisSchema.ROLE_USER;
        }
        return longTermMemoryInterface.add(str, str2, str3);
    }
}
