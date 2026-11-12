package com.jarvis.core.agent;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppAgentRules.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/jarvis/core/agent/ScreenItem;", "", "label", "", "editable", "", "password", "<init>", "(Ljava/lang/String;ZZ)V", "getLabel", "()Ljava/lang/String;", "getEditable", "()Z", "getPassword", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ScreenItem {
    private final boolean editable;
    private final String label;
    private final boolean password;

    public static /* synthetic */ ScreenItem copy$default(ScreenItem screenItem, String str, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = screenItem.label;
        }
        if ((i & 2) != 0) {
            z = screenItem.editable;
        }
        if ((i & 4) != 0) {
            z2 = screenItem.password;
        }
        return screenItem.copy(str, z, z2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getEditable() {
        return this.editable;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getPassword() {
        return this.password;
    }

    public final ScreenItem copy(String label, boolean editable, boolean password) {
        Intrinsics.checkNotNullParameter(label, "label");
        return new ScreenItem(label, editable, password);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ScreenItem)) {
            return false;
        }
        ScreenItem screenItem = (ScreenItem) other;
        return Intrinsics.areEqual(this.label, screenItem.label) && this.editable == screenItem.editable && this.password == screenItem.password;
    }

    public int hashCode() {
        return (((this.label.hashCode() * 31) + Boolean.hashCode(this.editable)) * 31) + Boolean.hashCode(this.password);
    }

    public String toString() {
        return "ScreenItem(label=" + this.label + ", editable=" + this.editable + ", password=" + this.password + ")";
    }

    public ScreenItem(String label, boolean editable, boolean password) {
        Intrinsics.checkNotNullParameter(label, "label");
        this.label = label;
        this.editable = editable;
        this.password = password;
    }

    public /* synthetic */ ScreenItem(String str, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, z, (i & 4) != 0 ? false : z2);
    }

    public final boolean getEditable() {
        return this.editable;
    }

    public final String getLabel() {
        return this.label;
    }

    public final boolean getPassword() {
        return this.password;
    }
}
