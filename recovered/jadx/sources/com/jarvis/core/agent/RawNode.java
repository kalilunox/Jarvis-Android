package com.jarvis.core.agent;

import com.jarvis.app.VoiceService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppAgentRules.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\t\n\u0002\b\u0011\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\u000e\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u0000J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\t\u0010#\u001a\u00020\u0005HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003J\t\u0010%\u001a\u00020\nHÆ\u0003J\t\u0010&\u001a\u00020\nHÆ\u0003J\t\u0010'\u001a\u00020\nHÆ\u0003JY\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\nHÆ\u0001J\u0013\u0010)\u001a\u00020\n2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020\u0005HÖ\u0001J\t\u0010+\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0011\u0010\u001a\u001a\u00020\u001b8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u0006,"}, d2 = {"Lcom/jarvis/core/agent/RawNode;", "", VoiceService.EXTRA_TEXT, "", "left", "", "top", "right", "bottom", "clickable", "", "editable", "password", "<init>", "(Ljava/lang/String;IIIIZZZ)V", "getText", "()Ljava/lang/String;", "getLeft", "()I", "getTop", "getRight", "getBottom", "getClickable", "()Z", "getEditable", "getPassword", "area", "", "getArea", "()J", "contains", "other", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "hashCode", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class RawNode {
    private final int bottom;
    private final boolean clickable;
    private final boolean editable;
    private final int left;
    private final boolean password;
    private final int right;
    private final String text;
    private final int top;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getLeft() {
        return this.left;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getTop() {
        return this.top;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getRight() {
        return this.right;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getBottom() {
        return this.bottom;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getClickable() {
        return this.clickable;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getEditable() {
        return this.editable;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final boolean getPassword() {
        return this.password;
    }

    public final RawNode copy(String text, int left, int top, int right, int bottom, boolean clickable, boolean editable, boolean password) {
        Intrinsics.checkNotNullParameter(text, "text");
        return new RawNode(text, left, top, right, bottom, clickable, editable, password);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RawNode)) {
            return false;
        }
        RawNode rawNode = (RawNode) other;
        return Intrinsics.areEqual(this.text, rawNode.text) && this.left == rawNode.left && this.top == rawNode.top && this.right == rawNode.right && this.bottom == rawNode.bottom && this.clickable == rawNode.clickable && this.editable == rawNode.editable && this.password == rawNode.password;
    }

    public int hashCode() {
        return (((((((((((((this.text.hashCode() * 31) + Integer.hashCode(this.left)) * 31) + Integer.hashCode(this.top)) * 31) + Integer.hashCode(this.right)) * 31) + Integer.hashCode(this.bottom)) * 31) + Boolean.hashCode(this.clickable)) * 31) + Boolean.hashCode(this.editable)) * 31) + Boolean.hashCode(this.password);
    }

    public String toString() {
        return "RawNode(text=" + this.text + ", left=" + this.left + ", top=" + this.top + ", right=" + this.right + ", bottom=" + this.bottom + ", clickable=" + this.clickable + ", editable=" + this.editable + ", password=" + this.password + ")";
    }

    public RawNode(String text, int left, int top, int right, int bottom, boolean clickable, boolean editable, boolean password) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.text = text;
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.clickable = clickable;
        this.editable = editable;
        this.password = password;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ RawNode(String str, int i, int i2, int i3, int i4, boolean z, boolean z2, boolean z3, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        boolean z4;
        if ((i5 & 128) == 0) {
            z4 = z3;
        } else {
            z4 = false;
        }
        this(str, i, i2, i3, i4, z, z2, z4);
    }

    public final String getText() {
        return this.text;
    }

    public final int getLeft() {
        return this.left;
    }

    public final int getTop() {
        return this.top;
    }

    public final int getRight() {
        return this.right;
    }

    public final int getBottom() {
        return this.bottom;
    }

    public final boolean getClickable() {
        return this.clickable;
    }

    public final boolean getEditable() {
        return this.editable;
    }

    public final boolean getPassword() {
        return this.password;
    }

    public final long getArea() {
        return ((long) (this.right - this.left)) * ((long) (this.bottom - this.top));
    }

    public final boolean contains(RawNode other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return this.left <= other.left && this.top <= other.top && this.right >= other.right && this.bottom >= other.bottom;
    }
}
