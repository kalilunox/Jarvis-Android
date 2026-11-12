package com.jarvis.core.agent;

import com.jarvis.app.VoiceService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppAgentRules.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0007\u0004\u0005\u0006\u0007\b\t\nB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0007\u000b\f\r\u000e\u000f\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/jarvis/core/agent/AgentStep;", "", "<init>", "()V", "Tap", "Type", "Open", "Scroll", "Back", "Enter", "Done", "Lcom/jarvis/core/agent/AgentStep$Back;", "Lcom/jarvis/core/agent/AgentStep$Done;", "Lcom/jarvis/core/agent/AgentStep$Enter;", "Lcom/jarvis/core/agent/AgentStep$Open;", "Lcom/jarvis/core/agent/AgentStep$Scroll;", "Lcom/jarvis/core/agent/AgentStep$Tap;", "Lcom/jarvis/core/agent/AgentStep$Type;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class AgentStep {
    public /* synthetic */ AgentStep(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: AppAgentRules.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/agent/AgentStep$Tap;", "Lcom/jarvis/core/agent/AgentStep;", "index", "", "<init>", "(I)V", "getIndex", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Tap extends AgentStep {
        private final int index;

        public static /* synthetic */ Tap copy$default(Tap tap, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = tap.index;
            }
            return tap.copy(i);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getIndex() {
            return this.index;
        }

        public final Tap copy(int index) {
            return new Tap(index);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Tap) && this.index == ((Tap) other).index;
        }

        public int hashCode() {
            return Integer.hashCode(this.index);
        }

        public String toString() {
            return "Tap(index=" + this.index + ")";
        }

        public Tap(int index) {
            super(null);
            this.index = index;
        }

        public final int getIndex() {
            return this.index;
        }
    }

    private AgentStep() {
    }

    /* JADX INFO: compiled from: AppAgentRules.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/jarvis/core/agent/AgentStep$Type;", "Lcom/jarvis/core/agent/AgentStep;", "index", "", VoiceService.EXTRA_TEXT, "", "<init>", "(ILjava/lang/String;)V", "getIndex", "()I", "getText", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Type extends AgentStep {
        private final int index;
        private final String text;

        public static /* synthetic */ Type copy$default(Type type, int i, String str, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = type.index;
            }
            if ((i2 & 2) != 0) {
                str = type.text;
            }
            return type.copy(i, str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getIndex() {
            return this.index;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getText() {
            return this.text;
        }

        public final Type copy(int index, String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            return new Type(index, text);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Type)) {
                return false;
            }
            Type type = (Type) other;
            return this.index == type.index && Intrinsics.areEqual(this.text, type.text);
        }

        public int hashCode() {
            return (Integer.hashCode(this.index) * 31) + this.text.hashCode();
        }

        public String toString() {
            return "Type(index=" + this.index + ", text=" + this.text + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Type(int index, String text) {
            super(null);
            Intrinsics.checkNotNullParameter(text, "text");
            this.index = index;
            this.text = text;
        }

        public final int getIndex() {
            return this.index;
        }

        public final String getText() {
            return this.text;
        }
    }

    /* JADX INFO: compiled from: AppAgentRules.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/agent/AgentStep$Open;", "Lcom/jarvis/core/agent/AgentStep;", "app", "", "<init>", "(Ljava/lang/String;)V", "getApp", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Open extends AgentStep {
        private final String app;

        public static /* synthetic */ Open copy$default(Open open, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = open.app;
            }
            return open.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getApp() {
            return this.app;
        }

        public final Open copy(String app) {
            Intrinsics.checkNotNullParameter(app, "app");
            return new Open(app);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Open) && Intrinsics.areEqual(this.app, ((Open) other).app);
        }

        public int hashCode() {
            return this.app.hashCode();
        }

        public String toString() {
            return "Open(app=" + this.app + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Open(String app) {
            super(null);
            Intrinsics.checkNotNullParameter(app, "app");
            this.app = app;
        }

        public final String getApp() {
            return this.app;
        }
    }

    /* JADX INFO: compiled from: AppAgentRules.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/agent/AgentStep$Scroll;", "Lcom/jarvis/core/agent/AgentStep;", "down", "", "<init>", "(Z)V", "getDown", "()Z", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Scroll extends AgentStep {
        private final boolean down;

        public static /* synthetic */ Scroll copy$default(Scroll scroll, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = scroll.down;
            }
            return scroll.copy(z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getDown() {
            return this.down;
        }

        public final Scroll copy(boolean down) {
            return new Scroll(down);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Scroll) && this.down == ((Scroll) other).down;
        }

        public int hashCode() {
            return Boolean.hashCode(this.down);
        }

        public String toString() {
            return "Scroll(down=" + this.down + ")";
        }

        public Scroll(boolean down) {
            super(null);
            this.down = down;
        }

        public final boolean getDown() {
            return this.down;
        }
    }

    /* JADX INFO: compiled from: AppAgentRules.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/jarvis/core/agent/AgentStep$Back;", "Lcom/jarvis/core/agent/AgentStep;", "<init>", "()V", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Back extends AgentStep {
        public static final Back INSTANCE = new Back();

        private Back() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: AppAgentRules.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/jarvis/core/agent/AgentStep$Enter;", "Lcom/jarvis/core/agent/AgentStep;", "<init>", "()V", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Enter extends AgentStep {
        public static final Enter INSTANCE = new Enter();

        private Enter() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: AppAgentRules.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/agent/AgentStep$Done;", "Lcom/jarvis/core/agent/AgentStep;", "reply", "", "<init>", "(Ljava/lang/String;)V", "getReply", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Done extends AgentStep {
        private final String reply;

        public static /* synthetic */ Done copy$default(Done done, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = done.reply;
            }
            return done.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getReply() {
            return this.reply;
        }

        public final Done copy(String reply) {
            Intrinsics.checkNotNullParameter(reply, "reply");
            return new Done(reply);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Done) && Intrinsics.areEqual(this.reply, ((Done) other).reply);
        }

        public int hashCode() {
            return this.reply.hashCode();
        }

        public String toString() {
            return "Done(reply=" + this.reply + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Done(String reply) {
            super(null);
            Intrinsics.checkNotNullParameter(reply, "reply");
            this.reply = reply;
        }

        public final String getReply() {
            return this.reply;
        }
    }
}
