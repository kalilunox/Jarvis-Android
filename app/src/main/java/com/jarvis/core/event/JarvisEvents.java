package com.jarvis.core.event;

import androidx.core.app.NotificationCompat;
import com.jarvis.core.model.AssistantState;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: JarvisEvents.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0006R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/jarvis/core/event/JarvisEvents;", "", "<init>", "()V", "_events", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/jarvis/core/event/AssistantEvent;", "events", "Lkotlinx/coroutines/flow/SharedFlow;", "getEvents", "()Lkotlinx/coroutines/flow/SharedFlow;", "_assistantState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/jarvis/core/model/AssistantState;", "assistantState", "Lkotlinx/coroutines/flow/StateFlow;", "getAssistantState", "()Lkotlinx/coroutines/flow/StateFlow;", "publish", "", NotificationCompat.CATEGORY_EVENT, "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JarvisEvents {
    public static final JarvisEvents INSTANCE = new JarvisEvents();
    private static final MutableSharedFlow<AssistantEvent> _events = SharedFlowKt.MutableSharedFlow$default(0, 64, null, 5, null);
    private static final SharedFlow<AssistantEvent> events = FlowKt.asSharedFlow(_events);
    private static final MutableStateFlow<AssistantState> _assistantState = StateFlowKt.MutableStateFlow(AssistantState.OFFLINE);
    private static final StateFlow<AssistantState> assistantState = FlowKt.asStateFlow(_assistantState);

    private JarvisEvents() {
    }

    public final SharedFlow<AssistantEvent> getEvents() {
        return events;
    }

    public final StateFlow<AssistantState> getAssistantState() {
        return assistantState;
    }

    public final void publish(AssistantEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event instanceof AssistantEvent.StateChanged) {
            _assistantState.setValue(((AssistantEvent.StateChanged) event).getState());
        }
        _events.tryEmit(event);
    }
}
