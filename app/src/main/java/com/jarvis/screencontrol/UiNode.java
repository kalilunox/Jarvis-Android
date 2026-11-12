package com.jarvis.screencontrol;

import android.graphics.Rect;
import com.jarvis.app.VoiceService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: UiNode.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b'\b\u0086\b\u0018\u00002\u00020\u0001Bi\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\u000b\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u000b¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\tHÆ\u0003J\t\u0010,\u001a\u00020\u000bHÆ\u0003J\t\u0010-\u001a\u00020\u000bHÆ\u0003J\t\u0010.\u001a\u00020\u000bHÆ\u0003J\t\u0010/\u001a\u00020\u000bHÆ\u0003J\t\u00100\u001a\u00020\u0010HÆ\u0003J\t\u00101\u001a\u00020\u000bHÆ\u0003J\u0081\u0001\u00102\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u000bHÆ\u0001J\u0013\u00103\u001a\u00020\u000b2\b\u00104\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00105\u001a\u00020\u0010HÖ\u0001J\t\u00106\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001dR\u0011\u0010\r\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001dR\u0011\u0010\u000e\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001dR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0011\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001dR\u0011\u0010$\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b%\u0010\u0015¨\u00067"}, d2 = {"Lcom/jarvis/screencontrol/UiNode;", "", VoiceService.EXTRA_TEXT, "", "contentDescription", "viewId", "className", "packageName", "bounds", "Landroid/graphics/Rect;", "clickable", "", "editable", "focused", "enabled", "depth", "", "password", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Rect;ZZZZIZ)V", "getText", "()Ljava/lang/String;", "getContentDescription", "getViewId", "getClassName", "getPackageName", "getBounds", "()Landroid/graphics/Rect;", "getClickable", "()Z", "getEditable", "getFocused", "getEnabled", "getDepth", "()I", "getPassword", "searchable", "getSearchable", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "equals", "other", "hashCode", "toString", "screen-control_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class UiNode {
    private final Rect bounds;
    private final String className;
    private final boolean clickable;
    private final String contentDescription;
    private final int depth;
    private final boolean editable;
    private final boolean enabled;
    private final boolean focused;
    private final String packageName;
    private final boolean password;
    private final String text;
    private final String viewId;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final boolean getEnabled() {
        return this.enabled;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final int getDepth() {
        return this.depth;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final boolean getPassword() {
        return this.password;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getContentDescription() {
        return this.contentDescription;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getViewId() {
        return this.viewId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getClassName() {
        return this.className;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getPackageName() {
        return this.packageName;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Rect getBounds() {
        return this.bounds;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getClickable() {
        return this.clickable;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final boolean getEditable() {
        return this.editable;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final boolean getFocused() {
        return this.focused;
    }

    public final UiNode copy(String text, String contentDescription, String viewId, String className, String packageName, Rect bounds, boolean clickable, boolean editable, boolean focused, boolean enabled, int depth, boolean password) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(contentDescription, "contentDescription");
        Intrinsics.checkNotNullParameter(viewId, "viewId");
        Intrinsics.checkNotNullParameter(className, "className");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        return new UiNode(text, contentDescription, viewId, className, packageName, bounds, clickable, editable, focused, enabled, depth, password);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UiNode)) {
            return false;
        }
        UiNode uiNode = (UiNode) other;
        return Intrinsics.areEqual(this.text, uiNode.text) && Intrinsics.areEqual(this.contentDescription, uiNode.contentDescription) && Intrinsics.areEqual(this.viewId, uiNode.viewId) && Intrinsics.areEqual(this.className, uiNode.className) && Intrinsics.areEqual(this.packageName, uiNode.packageName) && Intrinsics.areEqual(this.bounds, uiNode.bounds) && this.clickable == uiNode.clickable && this.editable == uiNode.editable && this.focused == uiNode.focused && this.enabled == uiNode.enabled && this.depth == uiNode.depth && this.password == uiNode.password;
    }

    public int hashCode() {
        return (((((((((((((((((((((this.text.hashCode() * 31) + this.contentDescription.hashCode()) * 31) + this.viewId.hashCode()) * 31) + this.className.hashCode()) * 31) + this.packageName.hashCode()) * 31) + this.bounds.hashCode()) * 31) + Boolean.hashCode(this.clickable)) * 31) + Boolean.hashCode(this.editable)) * 31) + Boolean.hashCode(this.focused)) * 31) + Boolean.hashCode(this.enabled)) * 31) + Integer.hashCode(this.depth)) * 31) + Boolean.hashCode(this.password);
    }

    public String toString() {
        return "UiNode(text=" + this.text + ", contentDescription=" + this.contentDescription + ", viewId=" + this.viewId + ", className=" + this.className + ", packageName=" + this.packageName + ", bounds=" + this.bounds + ", clickable=" + this.clickable + ", editable=" + this.editable + ", focused=" + this.focused + ", enabled=" + this.enabled + ", depth=" + this.depth + ", password=" + this.password + ")";
    }

    public UiNode(String text, String contentDescription, String viewId, String className, String packageName, Rect bounds, boolean clickable, boolean editable, boolean focused, boolean enabled, int depth, boolean password) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(contentDescription, "contentDescription");
        Intrinsics.checkNotNullParameter(viewId, "viewId");
        Intrinsics.checkNotNullParameter(className, "className");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        this.text = text;
        this.contentDescription = contentDescription;
        this.viewId = viewId;
        this.className = className;
        this.packageName = packageName;
        this.bounds = bounds;
        this.clickable = clickable;
        this.editable = editable;
        this.focused = focused;
        this.enabled = enabled;
        this.depth = depth;
        this.password = password;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ UiNode(String str, String str2, String str3, String str4, String str5, Rect rect, boolean z, boolean z2, boolean z3, boolean z4, int i, boolean z5, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        boolean z6;
        if ((i2 & 2048) == 0) {
            z6 = z5;
        } else {
            z6 = false;
        }
        this(str, str2, str3, str4, str5, rect, z, z2, z3, z4, i, z6);
    }

    public final String getText() {
        return this.text;
    }

    public final String getContentDescription() {
        return this.contentDescription;
    }

    public final String getViewId() {
        return this.viewId;
    }

    public final String getClassName() {
        return this.className;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final Rect getBounds() {
        return this.bounds;
    }

    public final boolean getClickable() {
        return this.clickable;
    }

    public final boolean getEditable() {
        return this.editable;
    }

    public final boolean getFocused() {
        return this.focused;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final int getDepth() {
        return this.depth;
    }

    public final boolean getPassword() {
        return this.password;
    }

    public final String getSearchable() {
        Iterable $this$filter$iv = CollectionsKt.listOf((Object[]) new String[]{this.text, this.contentDescription, StringsKt.substringAfterLast$default(this.viewId, IOUtils.DIR_SEPARATOR_UNIX, (String) null, 2, (Object) null)});
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            if (!StringsKt.isBlank(it)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return CollectionsKt.joinToString$default((List) destination$iv$iv, StringUtils.SPACE, null, null, 0, null, null, 62, null);
    }
}
