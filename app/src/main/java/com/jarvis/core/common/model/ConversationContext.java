package com.jarvis.core.common.model;

import com.jarvis.app.VoiceService;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ConversationContext.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\bJ\u000e\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\bJ\u0006\u0010 \u001a\u00020\u001aJ\u0006\u0010!\u001a\u00020\u001aJ\u0010\u0010\"\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020\bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00168F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u001d\u001a\u0004\u0018\u00010\b8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u0006$"}, d2 = {"Lcom/jarvis/core/common/model/ConversationContext;", "", "maxMessages", "", "<init>", "(I)V", "inMemory", "Lkotlin/collections/ArrayDeque;", "", "pendingAction", "Lcom/jarvis/core/common/model/PendingAction;", "getPendingAction", "()Lcom/jarvis/core/common/model/PendingAction;", "setPendingAction", "(Lcom/jarvis/core/common/model/PendingAction;)V", "lastToolCall", "Lcom/jarvis/core/common/model/ToolCall;", "getLastToolCall", "()Lcom/jarvis/core/common/model/ToolCall;", "setLastToolCall", "(Lcom/jarvis/core/common/model/ToolCall;)V", "messages", "", "getMessages", "()Ljava/util/List;", "addUser", "", VoiceService.EXTRA_TEXT, "addAssistant", "lastUserRequest", "getLastUserRequest", "()Ljava/lang/String;", "clearPending", "clear", "add", "line", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ConversationContext {
    private final ArrayDeque<String> inMemory;
    private volatile ToolCall lastToolCall;
    private final int maxMessages;
    private volatile PendingAction pendingAction;

    public ConversationContext() {
        this(0, 1, null);
    }

    public ConversationContext(int maxMessages) {
        this.maxMessages = maxMessages;
        this.inMemory = new ArrayDeque<>(this.maxMessages);
    }

    public /* synthetic */ ConversationContext(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 20 : i);
    }

    public final PendingAction getPendingAction() {
        return this.pendingAction;
    }

    public final void setPendingAction(PendingAction pendingAction) {
        this.pendingAction = pendingAction;
    }

    public final ToolCall getLastToolCall() {
        return this.lastToolCall;
    }

    public final void setLastToolCall(ToolCall toolCall) {
        this.lastToolCall = toolCall;
    }

    public final List<String> getMessages() {
        return CollectionsKt.toList(this.inMemory);
    }

    public final void addUser(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        add("user: " + text);
    }

    public final void addAssistant(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        add("assistant: " + text);
    }

    public final String getLastUserRequest() {
        Object obj;
        Object element$iv;
        String it;
        List $this$lastOrNull$iv = this.inMemory;
        ListIterator<String> listIterator = $this$lastOrNull$iv.listIterator($this$lastOrNull$iv.size());
        do {
            obj = null;
            if (listIterator.hasPrevious()) {
                element$iv = listIterator.previous();
                it = (String) element$iv;
            }
            return (String) obj;
        } while (!StringsKt.startsWith$default(it, "user:", false, 2, (Object) null));
        obj = element$iv;
        return (String) obj;
    }

    public final void clearPending() {
        this.pendingAction = null;
    }

    public final void clear() {
        this.inMemory.clear();
        this.pendingAction = null;
        this.lastToolCall = null;
    }

    private final void add(String line) {
        if (this.inMemory.size() >= this.maxMessages) {
            this.inMemory.removeFirst();
        }
        this.inMemory.addLast(line);
    }
}
