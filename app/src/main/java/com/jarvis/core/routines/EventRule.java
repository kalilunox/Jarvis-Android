package com.jarvis.core.routines;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: EventRoutines.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/jarvis/core/routines/EventRule;", "", "trigger", "Lcom/jarvis/core/routines/EventTrigger;", "arg", "", "<init>", "(Lcom/jarvis/core/routines/EventTrigger;Ljava/lang/String;)V", "getTrigger", "()Lcom/jarvis/core/routines/EventTrigger;", "getArg", "()Ljava/lang/String;", "name", "getName", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class EventRule {
    private final String arg;
    private final String name;
    private final EventTrigger trigger;

    public static /* synthetic */ EventRule copy$default(EventRule eventRule, EventTrigger eventTrigger, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            eventTrigger = eventRule.trigger;
        }
        if ((i & 2) != 0) {
            str = eventRule.arg;
        }
        return eventRule.copy(eventTrigger, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final EventTrigger getTrigger() {
        return this.trigger;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getArg() {
        return this.arg;
    }

    public final EventRule copy(EventTrigger trigger, String arg) {
        Intrinsics.checkNotNullParameter(trigger, "trigger");
        Intrinsics.checkNotNullParameter(arg, "arg");
        return new EventRule(trigger, arg);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EventRule)) {
            return false;
        }
        EventRule eventRule = (EventRule) other;
        return this.trigger == eventRule.trigger && Intrinsics.areEqual(this.arg, eventRule.arg);
    }

    public int hashCode() {
        return (this.trigger.hashCode() * 31) + this.arg.hashCode();
    }

    public String toString() {
        return "EventRule(trigger=" + this.trigger + ", arg=" + this.arg + ")";
    }

    public EventRule(EventTrigger trigger, String arg) {
        Intrinsics.checkNotNullParameter(trigger, "trigger");
        Intrinsics.checkNotNullParameter(arg, "arg");
        this.trigger = trigger;
        this.arg = arg;
        Iterable $this$filter$iv = CollectionsKt.listOf((Object[]) new String[]{this.trigger.getTitle(), this.arg});
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            if (it.length() > 0) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        this.name = CollectionsKt.joinToString$default((List) destination$iv$iv, StringUtils.SPACE, null, null, 0, null, null, 62, null);
    }

    public /* synthetic */ EventRule(EventTrigger eventTrigger, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(eventTrigger, (i & 2) != 0 ? "" : str);
    }

    public final String getArg() {
        return this.arg;
    }

    public final EventTrigger getTrigger() {
        return this.trigger;
    }

    public final String getName() {
        return this.name;
    }
}
