package com.jarvis.core.event;

import com.jarvis.app.VoiceService;
import com.jarvis.core.common.model.Intent;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.model.AssistantState;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AssistantEvent.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\t\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u0082\u0001\t\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013¨\u0006\u0014À\u0006\u0003"}, d2 = {"Lcom/jarvis/core/event/AssistantEvent;", "", "StateChanged", "TranscriptUpdated", "WakeDetected", "RequestReceived", "ResponseGenerated", "ServiceStarted", "IntentResolved", "ToolExecuted", "Error", "Lcom/jarvis/core/event/AssistantEvent$Error;", "Lcom/jarvis/core/event/AssistantEvent$IntentResolved;", "Lcom/jarvis/core/event/AssistantEvent$RequestReceived;", "Lcom/jarvis/core/event/AssistantEvent$ResponseGenerated;", "Lcom/jarvis/core/event/AssistantEvent$ServiceStarted;", "Lcom/jarvis/core/event/AssistantEvent$StateChanged;", "Lcom/jarvis/core/event/AssistantEvent$ToolExecuted;", "Lcom/jarvis/core/event/AssistantEvent$TranscriptUpdated;", "Lcom/jarvis/core/event/AssistantEvent$WakeDetected;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface AssistantEvent {

    /* JADX INFO: compiled from: AssistantEvent.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/jarvis/core/event/AssistantEvent$StateChanged;", "Lcom/jarvis/core/event/AssistantEvent;", "state", "Lcom/jarvis/core/model/AssistantState;", "<init>", "(Lcom/jarvis/core/model/AssistantState;)V", "getState", "()Lcom/jarvis/core/model/AssistantState;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class StateChanged implements AssistantEvent {
        private final AssistantState state;

        public static /* synthetic */ StateChanged copy$default(StateChanged stateChanged, AssistantState assistantState, int i, Object obj) {
            if ((i & 1) != 0) {
                assistantState = stateChanged.state;
            }
            return stateChanged.copy(assistantState);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final AssistantState getState() {
            return this.state;
        }

        public final StateChanged copy(AssistantState state) {
            Intrinsics.checkNotNullParameter(state, "state");
            return new StateChanged(state);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof StateChanged) && this.state == ((StateChanged) other).state;
        }

        public int hashCode() {
            return this.state.hashCode();
        }

        public String toString() {
            return "StateChanged(state=" + this.state + ")";
        }

        public StateChanged(AssistantState state) {
            Intrinsics.checkNotNullParameter(state, "state");
            this.state = state;
        }

        public final AssistantState getState() {
            return this.state;
        }
    }

    /* JADX INFO: compiled from: AssistantEvent.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/event/AssistantEvent$TranscriptUpdated;", "Lcom/jarvis/core/event/AssistantEvent;", VoiceService.EXTRA_TEXT, "", "<init>", "(Ljava/lang/String;)V", "getText", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class TranscriptUpdated implements AssistantEvent {
        private final String text;

        public static /* synthetic */ TranscriptUpdated copy$default(TranscriptUpdated transcriptUpdated, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = transcriptUpdated.text;
            }
            return transcriptUpdated.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getText() {
            return this.text;
        }

        public final TranscriptUpdated copy(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            return new TranscriptUpdated(text);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof TranscriptUpdated) && Intrinsics.areEqual(this.text, ((TranscriptUpdated) other).text);
        }

        public int hashCode() {
            return this.text.hashCode();
        }

        public String toString() {
            return "TranscriptUpdated(text=" + this.text + ")";
        }

        public TranscriptUpdated(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            this.text = text;
        }

        public final String getText() {
            return this.text;
        }
    }

    /* JADX INFO: compiled from: AssistantEvent.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/jarvis/core/event/AssistantEvent$WakeDetected;", "Lcom/jarvis/core/event/AssistantEvent;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class WakeDetected implements AssistantEvent {
        public static final WakeDetected INSTANCE = new WakeDetected();

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof WakeDetected)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1095196943;
        }

        public String toString() {
            return "WakeDetected";
        }

        private WakeDetected() {
        }
    }

    /* JADX INFO: compiled from: AssistantEvent.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/event/AssistantEvent$RequestReceived;", "Lcom/jarvis/core/event/AssistantEvent;", VoiceService.EXTRA_TEXT, "", "<init>", "(Ljava/lang/String;)V", "getText", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class RequestReceived implements AssistantEvent {
        private final String text;

        public static /* synthetic */ RequestReceived copy$default(RequestReceived requestReceived, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = requestReceived.text;
            }
            return requestReceived.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getText() {
            return this.text;
        }

        public final RequestReceived copy(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            return new RequestReceived(text);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof RequestReceived) && Intrinsics.areEqual(this.text, ((RequestReceived) other).text);
        }

        public int hashCode() {
            return this.text.hashCode();
        }

        public String toString() {
            return "RequestReceived(text=" + this.text + ")";
        }

        public RequestReceived(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            this.text = text;
        }

        public final String getText() {
            return this.text;
        }
    }

    /* JADX INFO: compiled from: AssistantEvent.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/event/AssistantEvent$ResponseGenerated;", "Lcom/jarvis/core/event/AssistantEvent;", VoiceService.EXTRA_TEXT, "", "<init>", "(Ljava/lang/String;)V", "getText", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class ResponseGenerated implements AssistantEvent {
        private final String text;

        public static /* synthetic */ ResponseGenerated copy$default(ResponseGenerated responseGenerated, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = responseGenerated.text;
            }
            return responseGenerated.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getText() {
            return this.text;
        }

        public final ResponseGenerated copy(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            return new ResponseGenerated(text);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof ResponseGenerated) && Intrinsics.areEqual(this.text, ((ResponseGenerated) other).text);
        }

        public int hashCode() {
            return this.text.hashCode();
        }

        public String toString() {
            return "ResponseGenerated(text=" + this.text + ")";
        }

        public ResponseGenerated(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            this.text = text;
        }

        public final String getText() {
            return this.text;
        }
    }

    /* JADX INFO: compiled from: AssistantEvent.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/event/AssistantEvent$ServiceStarted;", "Lcom/jarvis/core/event/AssistantEvent;", "uptimeTag", "", "<init>", "(Ljava/lang/String;)V", "getUptimeTag", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class ServiceStarted implements AssistantEvent {
        private final String uptimeTag;

        public static /* synthetic */ ServiceStarted copy$default(ServiceStarted serviceStarted, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = serviceStarted.uptimeTag;
            }
            return serviceStarted.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUptimeTag() {
            return this.uptimeTag;
        }

        public final ServiceStarted copy(String uptimeTag) {
            Intrinsics.checkNotNullParameter(uptimeTag, "uptimeTag");
            return new ServiceStarted(uptimeTag);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof ServiceStarted) && Intrinsics.areEqual(this.uptimeTag, ((ServiceStarted) other).uptimeTag);
        }

        public int hashCode() {
            return this.uptimeTag.hashCode();
        }

        public String toString() {
            return "ServiceStarted(uptimeTag=" + this.uptimeTag + ")";
        }

        public ServiceStarted(String uptimeTag) {
            Intrinsics.checkNotNullParameter(uptimeTag, "uptimeTag");
            this.uptimeTag = uptimeTag;
        }

        public final String getUptimeTag() {
            return this.uptimeTag;
        }
    }

    /* JADX INFO: compiled from: AssistantEvent.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/jarvis/core/event/AssistantEvent$IntentResolved;", "Lcom/jarvis/core/event/AssistantEvent;", "intent", "Lcom/jarvis/core/common/model/Intent;", "<init>", "(Lcom/jarvis/core/common/model/Intent;)V", "getIntent", "()Lcom/jarvis/core/common/model/Intent;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class IntentResolved implements AssistantEvent {
        private final Intent intent;

        public static /* synthetic */ IntentResolved copy$default(IntentResolved intentResolved, Intent intent, int i, Object obj) {
            if ((i & 1) != 0) {
                intent = intentResolved.intent;
            }
            return intentResolved.copy(intent);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Intent getIntent() {
            return this.intent;
        }

        public final IntentResolved copy(Intent intent) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            return new IntentResolved(intent);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof IntentResolved) && Intrinsics.areEqual(this.intent, ((IntentResolved) other).intent);
        }

        public int hashCode() {
            return this.intent.hashCode();
        }

        public String toString() {
            return "IntentResolved(intent=" + this.intent + ")";
        }

        public IntentResolved(Intent intent) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            this.intent = intent;
        }

        public final Intent getIntent() {
            return this.intent;
        }
    }

    /* JADX INFO: compiled from: AssistantEvent.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/jarvis/core/event/AssistantEvent$ToolExecuted;", "Lcom/jarvis/core/event/AssistantEvent;", "result", "Lcom/jarvis/core/common/model/ToolResult;", "<init>", "(Lcom/jarvis/core/common/model/ToolResult;)V", "getResult", "()Lcom/jarvis/core/common/model/ToolResult;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class ToolExecuted implements AssistantEvent {
        private final ToolResult result;

        public static /* synthetic */ ToolExecuted copy$default(ToolExecuted toolExecuted, ToolResult toolResult, int i, Object obj) {
            if ((i & 1) != 0) {
                toolResult = toolExecuted.result;
            }
            return toolExecuted.copy(toolResult);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ToolResult getResult() {
            return this.result;
        }

        public final ToolExecuted copy(ToolResult result) {
            Intrinsics.checkNotNullParameter(result, "result");
            return new ToolExecuted(result);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof ToolExecuted) && Intrinsics.areEqual(this.result, ((ToolExecuted) other).result);
        }

        public int hashCode() {
            return this.result.hashCode();
        }

        public String toString() {
            return "ToolExecuted(result=" + this.result + ")";
        }

        public ToolExecuted(ToolResult result) {
            Intrinsics.checkNotNullParameter(result, "result");
            this.result = result;
        }

        public final ToolResult getResult() {
            return this.result;
        }
    }

    /* JADX INFO: compiled from: AssistantEvent.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0006HÆ\u0003J)\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lcom/jarvis/core/event/AssistantEvent$Error;", "Lcom/jarvis/core/event/AssistantEvent;", "source", "", "message", "throwable", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V", "getSource", "()Ljava/lang/String;", "getMessage", "getThrowable", "()Ljava/lang/Throwable;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Error implements AssistantEvent {
        private final String message;
        private final String source;
        private final Throwable throwable;

        public static /* synthetic */ Error copy$default(Error error, String str, String str2, Throwable th, int i, Object obj) {
            if ((i & 1) != 0) {
                str = error.source;
            }
            if ((i & 2) != 0) {
                str2 = error.message;
            }
            if ((i & 4) != 0) {
                th = error.throwable;
            }
            return error.copy(str, str2, th);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getSource() {
            return this.source;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Throwable getThrowable() {
            return this.throwable;
        }

        public final Error copy(String source, String message, Throwable throwable) {
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(message, "message");
            return new Error(source, message, throwable);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Error)) {
                return false;
            }
            Error error = (Error) other;
            return Intrinsics.areEqual(this.source, error.source) && Intrinsics.areEqual(this.message, error.message) && Intrinsics.areEqual(this.throwable, error.throwable);
        }

        public int hashCode() {
            return (((this.source.hashCode() * 31) + this.message.hashCode()) * 31) + (this.throwable == null ? 0 : this.throwable.hashCode());
        }

        public String toString() {
            return "Error(source=" + this.source + ", message=" + this.message + ", throwable=" + this.throwable + ")";
        }

        public Error(String source, String message, Throwable throwable) {
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(message, "message");
            this.source = source;
            this.message = message;
            this.throwable = throwable;
        }

        public /* synthetic */ Error(String str, String str2, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i & 4) != 0 ? null : th);
        }

        public final String getSource() {
            return this.source;
        }

        public final String getMessage() {
            return this.message;
        }

        public final Throwable getThrowable() {
            return this.throwable;
        }
    }
}
