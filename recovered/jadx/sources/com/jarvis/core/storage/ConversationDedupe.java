package com.jarvis.core.storage;

import com.jarvis.app.VoiceService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ConversationRepository.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JD\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/jarvis/core/storage/ConversationDedupe;", "", "<init>", "()V", "WINDOW_MS", "", "isDuplicate", "", "previousRole", "", "previousText", "previousAt", "role", VoiceService.EXTRA_TEXT, "nowMillis", "windowMs", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ConversationDedupe {
    public static final ConversationDedupe INSTANCE = new ConversationDedupe();
    public static final long WINDOW_MS = 2000;

    private ConversationDedupe() {
    }

    public static /* synthetic */ boolean isDuplicate$default(ConversationDedupe conversationDedupe, String str, String str2, long j, String str3, String str4, long j2, long j3, int i, Object obj) {
        long j4;
        if ((i & 64) == 0) {
            j4 = j3;
        } else {
            j4 = 2000;
        }
        return conversationDedupe.isDuplicate(str, str2, j, str3, str4, j2, j4);
    }

    public final boolean isDuplicate(String previousRole, String previousText, long previousAt, String role, String text, long nowMillis, long windowMs) {
        Intrinsics.checkNotNullParameter(role, "role");
        Intrinsics.checkNotNullParameter(text, "text");
        if (!Intrinsics.areEqual(previousRole, role) || !Intrinsics.areEqual(previousText, text) || previousAt <= 0) {
            return false;
        }
        long j = nowMillis - previousAt;
        return 0 <= j && j <= windowMs;
    }
}
