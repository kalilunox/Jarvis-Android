package com.jarvis.app.ui;

import android.widget.LinearLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: More.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B>\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u001d\u0010\u0006\u001a\u0019\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0007¢\u0006\u0002\b\u000b¢\u0006\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR(\u0010\u0006\u001a\u0019\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0007¢\u0006\u0002\b\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/jarvis/app/ui/MoreSection;", "", "title", "", "sub", "icon", "build", "Lkotlin/Function2;", "Lcom/jarvis/app/ui/SectionScreen;", "Landroid/widget/LinearLayout;", "", "Lkotlin/ExtensionFunctionType;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function2;)V", "getTitle", "()Ljava/lang/String;", "getSub", "getIcon", "getBuild", "()Lkotlin/jvm/functions/Function2;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MoreSection {
    private final Function2<SectionScreen, LinearLayout, Unit> build;
    private final String icon;
    private final String sub;
    private final String title;

    /* JADX WARN: Multi-variable type inference failed */
    public MoreSection(String title, String sub, String icon, Function2<? super SectionScreen, ? super LinearLayout, Unit> build) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(sub, "sub");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(build, "build");
        this.title = title;
        this.sub = sub;
        this.icon = icon;
        this.build = build;
    }

    public final Function2<SectionScreen, LinearLayout, Unit> getBuild() {
        return this.build;
    }

    public final String getIcon() {
        return this.icon;
    }

    public final String getSub() {
        return this.sub;
    }

    public final String getTitle() {
        return this.title;
    }
}
