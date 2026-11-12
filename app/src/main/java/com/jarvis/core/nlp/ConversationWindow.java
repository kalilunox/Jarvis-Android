package com.jarvis.core.nlp;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ConversationWindow.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/jarvis/core/nlp/ConversationWindow;", "", "<init>", "()V", "CONVERSATION_MS", "", "CONFIRMATION_MS", "keepListening", "", "reply", "", "isChat", "enabled", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ConversationWindow {
    public static final long CONFIRMATION_MS = 10000;
    public static final long CONVERSATION_MS = 7000;
    public static final ConversationWindow INSTANCE = new ConversationWindow();

    private ConversationWindow() {
    }

    public final boolean keepListening(String reply, boolean isChat, boolean enabled) {
        Intrinsics.checkNotNullParameter(reply, "reply");
        if (enabled) {
            return isChat || StringsKt.endsWith$default(StringsKt.trimEnd((CharSequence) reply).toString(), "?", false, 2, (Object) null);
        }
        return false;
    }
}
