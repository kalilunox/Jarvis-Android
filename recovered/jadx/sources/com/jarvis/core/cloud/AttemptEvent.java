package com.jarvis.core.cloud;

import com.jarvis.app.VoiceService;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ChainRouter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lcom/jarvis/core/cloud/AttemptEvent;", "", "<init>", "()V", "VisibleToken", "Completed", "Errored", "Lcom/jarvis/core/cloud/AttemptEvent$Completed;", "Lcom/jarvis/core/cloud/AttemptEvent$Errored;", "Lcom/jarvis/core/cloud/AttemptEvent$VisibleToken;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class AttemptEvent {
    public /* synthetic */ AttemptEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: ChainRouter.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/jarvis/core/cloud/AttemptEvent$VisibleToken;", "Lcom/jarvis/core/cloud/AttemptEvent;", VoiceService.EXTRA_TEXT, "", "attempt", "Lcom/jarvis/core/cloud/Attempt;", "<init>", "(Ljava/lang/String;Lcom/jarvis/core/cloud/Attempt;)V", "getText", "()Ljava/lang/String;", "getAttempt", "()Lcom/jarvis/core/cloud/Attempt;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class VisibleToken extends AttemptEvent {
        private final Attempt attempt;
        private final String text;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public VisibleToken(String text, Attempt attempt) {
            super(null);
            Intrinsics.checkNotNullParameter(text, "text");
            Intrinsics.checkNotNullParameter(attempt, "attempt");
            this.text = text;
            this.attempt = attempt;
        }

        public final Attempt getAttempt() {
            return this.attempt;
        }

        public final String getText() {
            return this.text;
        }
    }

    private AttemptEvent() {
    }

    /* JADX INFO: compiled from: ChainRouter.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B%\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u001f\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/jarvis/core/cloud/AttemptEvent$Completed;", "Lcom/jarvis/core/cloud/AttemptEvent;", "usage", "", "", "", "attempt", "Lcom/jarvis/core/cloud/Attempt;", "<init>", "(Ljava/util/Map;Lcom/jarvis/core/cloud/Attempt;)V", "getUsage", "()Ljava/util/Map;", "getAttempt", "()Lcom/jarvis/core/cloud/Attempt;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Completed extends AttemptEvent {
        private final Attempt attempt;
        private final Map<String, Object> usage;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Completed(Map<String, ? extends Object> map, Attempt attempt) {
            super(null);
            Intrinsics.checkNotNullParameter(attempt, "attempt");
            this.usage = map;
            this.attempt = attempt;
        }

        public final Attempt getAttempt() {
            return this.attempt;
        }

        public final Map<String, Object> getUsage() {
            return this.usage;
        }
    }

    /* JADX INFO: compiled from: ChainRouter.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/cloud/AttemptEvent$Errored;", "Lcom/jarvis/core/cloud/AttemptEvent;", "httpStatus", "", "body", "", "attempt", "Lcom/jarvis/core/cloud/Attempt;", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Lcom/jarvis/core/cloud/Attempt;)V", "getHttpStatus", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getBody", "()Ljava/lang/String;", "getAttempt", "()Lcom/jarvis/core/cloud/Attempt;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Errored extends AttemptEvent {
        private final Attempt attempt;
        private final String body;
        private final Integer httpStatus;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Errored(Integer httpStatus, String body, Attempt attempt) {
            super(null);
            Intrinsics.checkNotNullParameter(attempt, "attempt");
            this.httpStatus = httpStatus;
            this.body = body;
            this.attempt = attempt;
        }

        public final Attempt getAttempt() {
            return this.attempt;
        }

        public final String getBody() {
            return this.body;
        }

        public final Integer getHttpStatus() {
            return this.httpStatus;
        }
    }
}
