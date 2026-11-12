package com.jarvis.app.ui;

import android.graphics.Color;
import com.jarvis.app.VoiceService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Ui.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b.\b\u0086\b\u0018\u00002\u00020\u0001B\u0099\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u0006\u0012\u0006\u0010\r\u001a\u00020\u0006\u0012\u0006\u0010\u000e\u001a\u00020\u0006\u0012\u0006\u0010\u000f\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0006¢\u0006\u0004\b\u0016\u0010\u0017J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0006HÆ\u0003J\t\u0010/\u001a\u00020\u0006HÆ\u0003J\t\u00100\u001a\u00020\u0006HÆ\u0003J\t\u00101\u001a\u00020\u0006HÆ\u0003J\t\u00102\u001a\u00020\u0006HÆ\u0003J\t\u00103\u001a\u00020\u0006HÆ\u0003J\t\u00104\u001a\u00020\u0006HÆ\u0003J\t\u00105\u001a\u00020\u0006HÆ\u0003J\t\u00106\u001a\u00020\u0006HÆ\u0003J\t\u00107\u001a\u00020\u0006HÆ\u0003J\t\u00108\u001a\u00020\u0006HÆ\u0003J\t\u00109\u001a\u00020\u0006HÆ\u0003J\t\u0010:\u001a\u00020\u0006HÆ\u0003J\t\u0010;\u001a\u00020\u0014HÆ\u0003J\t\u0010<\u001a\u00020\u0006HÆ\u0003J³\u0001\u0010=\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u00062\b\b\u0002\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u00062\b\b\u0002\u0010\u0010\u001a\u00020\u00062\b\b\u0002\u0010\u0011\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0006HÆ\u0001J\u0013\u0010>\u001a\u00020\u00142\b\u0010?\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010@\u001a\u00020\u0006HÖ\u0001J\t\u0010A\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001cR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001cR\u0011\u0010\n\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001cR\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001cR\u0011\u0010\f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001cR\u0011\u0010\r\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001cR\u0011\u0010\u000e\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001cR\u0011\u0010\u000f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001cR\u0011\u0010\u0010\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001cR\u0011\u0010\u0011\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001cR\u0011\u0010\u0012\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001cR\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0011\u0010\u0015\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001c¨\u0006B"}, d2 = {"Lcom/jarvis/app/ui/Skin;", "", "id", "", "title", "bg", "", "glass", "line", VoiceService.EXTRA_TEXT, "muted", "accent", "cyan", "hot", "violet", "onAccent", "good", "warn", "danger", "light", "", "metal", "<init>", "(Ljava/lang/String;Ljava/lang/String;IIIIIIIIIIIIIZI)V", "getId", "()Ljava/lang/String;", "getTitle", "getBg", "()I", "getGlass", "getLine", "getText", "getMuted", "getAccent", "getCyan", "getHot", "getViolet", "getOnAccent", "getGood", "getWarn", "getDanger", "getLight", "()Z", "getMetal", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "copy", "equals", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class Skin {
    private final int accent;
    private final int bg;
    private final int cyan;
    private final int danger;
    private final int glass;
    private final int good;
    private final int hot;
    private final String id;
    private final boolean light;
    private final int line;
    private final int metal;
    private final int muted;
    private final int onAccent;
    private final int text;
    private final String title;
    private final int violet;
    private final int warn;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final int getHot() {
        return this.hot;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final int getViolet() {
        return this.violet;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final int getOnAccent() {
        return this.onAccent;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final int getGood() {
        return this.good;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final int getWarn() {
        return this.warn;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final int getDanger() {
        return this.danger;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final boolean getLight() {
        return this.light;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final int getMetal() {
        return this.metal;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getBg() {
        return this.bg;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getGlass() {
        return this.glass;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getLine() {
        return this.line;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getText() {
        return this.text;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getMuted() {
        return this.muted;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getAccent() {
        return this.accent;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getCyan() {
        return this.cyan;
    }

    public final Skin copy(String id, String title, int bg, int glass, int line, int text, int muted, int accent, int cyan, int hot, int violet, int onAccent, int good, int warn, int danger, boolean light, int metal) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        return new Skin(id, title, bg, glass, line, text, muted, accent, cyan, hot, violet, onAccent, good, warn, danger, light, metal);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Skin)) {
            return false;
        }
        Skin skin = (Skin) other;
        return Intrinsics.areEqual(this.id, skin.id) && Intrinsics.areEqual(this.title, skin.title) && this.bg == skin.bg && this.glass == skin.glass && this.line == skin.line && this.text == skin.text && this.muted == skin.muted && this.accent == skin.accent && this.cyan == skin.cyan && this.hot == skin.hot && this.violet == skin.violet && this.onAccent == skin.onAccent && this.good == skin.good && this.warn == skin.warn && this.danger == skin.danger && this.light == skin.light && this.metal == skin.metal;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((this.id.hashCode() * 31) + this.title.hashCode()) * 31) + Integer.hashCode(this.bg)) * 31) + Integer.hashCode(this.glass)) * 31) + Integer.hashCode(this.line)) * 31) + Integer.hashCode(this.text)) * 31) + Integer.hashCode(this.muted)) * 31) + Integer.hashCode(this.accent)) * 31) + Integer.hashCode(this.cyan)) * 31) + Integer.hashCode(this.hot)) * 31) + Integer.hashCode(this.violet)) * 31) + Integer.hashCode(this.onAccent)) * 31) + Integer.hashCode(this.good)) * 31) + Integer.hashCode(this.warn)) * 31) + Integer.hashCode(this.danger)) * 31) + Boolean.hashCode(this.light)) * 31) + Integer.hashCode(this.metal);
    }

    public String toString() {
        return "Skin(id=" + this.id + ", title=" + this.title + ", bg=" + this.bg + ", glass=" + this.glass + ", line=" + this.line + ", text=" + this.text + ", muted=" + this.muted + ", accent=" + this.accent + ", cyan=" + this.cyan + ", hot=" + this.hot + ", violet=" + this.violet + ", onAccent=" + this.onAccent + ", good=" + this.good + ", warn=" + this.warn + ", danger=" + this.danger + ", light=" + this.light + ", metal=" + this.metal + ")";
    }

    public Skin(String id, String title, int bg, int glass, int line, int text, int muted, int accent, int cyan, int hot, int violet, int onAccent, int good, int warn, int danger, boolean light, int metal) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        this.id = id;
        this.title = title;
        this.bg = bg;
        this.glass = glass;
        this.line = line;
        this.text = text;
        this.muted = muted;
        this.accent = accent;
        this.cyan = cyan;
        this.hot = hot;
        this.violet = violet;
        this.onAccent = onAccent;
        this.good = good;
        this.warn = warn;
        this.danger = danger;
        this.light = light;
        this.metal = metal;
    }

    public /* synthetic */ Skin(String str, String str2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, boolean z, int i14, int i15, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, i, i2, i3, i4, i5, i6, i7, i8, i9, i10, (i15 & 4096) != 0 ? Color.parseColor("#3FE59B") : i11, (i15 & 8192) != 0 ? Color.parseColor("#FFB648") : i12, (i15 & 16384) != 0 ? Color.parseColor("#FF4F45") : i13, (32768 & i15) != 0 ? false : z, (i15 & 65536) != 0 ? Color.parseColor("#2B3A50") : i14);
    }

    public final String getId() {
        return this.id;
    }

    public final String getTitle() {
        return this.title;
    }

    public final int getBg() {
        return this.bg;
    }

    public final int getGlass() {
        return this.glass;
    }

    public final int getLine() {
        return this.line;
    }

    public final int getText() {
        return this.text;
    }

    public final int getMuted() {
        return this.muted;
    }

    public final int getAccent() {
        return this.accent;
    }

    public final int getCyan() {
        return this.cyan;
    }

    public final int getHot() {
        return this.hot;
    }

    public final int getViolet() {
        return this.violet;
    }

    public final int getOnAccent() {
        return this.onAccent;
    }

    public final int getGood() {
        return this.good;
    }

    public final int getWarn() {
        return this.warn;
    }

    public final int getDanger() {
        return this.danger;
    }

    public final boolean getLight() {
        return this.light;
    }

    public final int getMetal() {
        return this.metal;
    }
}
