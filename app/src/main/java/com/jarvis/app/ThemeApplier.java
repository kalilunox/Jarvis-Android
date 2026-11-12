package com.jarvis.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.core.view.WindowCompat;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: ThemeApplier.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001.B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0018\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u000f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0007J\u0018\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J\u0018\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J(\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u00112\u0006\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"J0\u0010$\u001a\u00020\u001e2\u0006\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u00112\u0006\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"J\u0017\u0010%\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0018\u001a\u00020\u000eH\u0002¢\u0006\u0002\u0010&J\u0010\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0011H\u0002J\"\u0010*\u001a\u00020(2\u0006\u0010+\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\u00112\b\b\u0002\u0010-\u001a\u00020\u0011H\u0002¨\u0006/"}, d2 = {"Lcom/jarvis/app/ThemeApplier;", "", "<init>", "()V", "palette", "Lcom/jarvis/app/ThemeApplier$Palette;", "theme", "", "applyToActivity", "", "activity", "Landroid/app/Activity;", "applyToScreen", "root", "Landroid/view/View;", "recreateWithTheme", "primaryColor", "", "mutedColor", "bgColor", "textColor", "personaSystemHint", "persona", "walk", "view", "p", "remapText", "tv", "Landroid/widget/TextView;", "rounded", "Landroid/graphics/drawable/GradientDrawable;", "fill", "stroke", "radiusDp", "", "strokeDp", "roundedDp", "extractSolidColor", "(Landroid/view/View;)Ljava/lang/Integer;", "isDarkish", "", "c", "isNear", "a", "b", "tol", "Palette", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ThemeApplier {
    public static final ThemeApplier INSTANCE = new ThemeApplier();

    private ThemeApplier() {
    }

    /* JADX INFO: compiled from: ThemeApplier.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b1\b\u0086\b\u0018\u00002\u00020\u0001B\u009f\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u0005\u0012\u0006\u0010\u0011\u001a\u00020\u0005\u0012\u0006\u0010\u0012\u001a\u00020\u0005\u0012\u0006\u0010\u0013\u001a\u00020\u0005\u0012\u0006\u0010\u0014\u001a\u00020\u0005\u0012\u0006\u0010\u0015\u001a\u00020\u0005\u0012\u0006\u0010\u0016\u001a\u00020\u0017¢\u0006\u0004\b\u0018\u0010\u0019J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0005HÆ\u0003J\t\u00102\u001a\u00020\u0005HÆ\u0003J\t\u00103\u001a\u00020\u0005HÆ\u0003J\t\u00104\u001a\u00020\u0005HÆ\u0003J\t\u00105\u001a\u00020\u0005HÆ\u0003J\t\u00106\u001a\u00020\u0005HÆ\u0003J\t\u00107\u001a\u00020\u0005HÆ\u0003J\t\u00108\u001a\u00020\u0005HÆ\u0003J\t\u00109\u001a\u00020\u0005HÆ\u0003J\t\u0010:\u001a\u00020\u0005HÆ\u0003J\t\u0010;\u001a\u00020\u0005HÆ\u0003J\t\u0010<\u001a\u00020\u0005HÆ\u0003J\t\u0010=\u001a\u00020\u0005HÆ\u0003J\t\u0010>\u001a\u00020\u0005HÆ\u0003J\t\u0010?\u001a\u00020\u0005HÆ\u0003J\t\u0010@\u001a\u00020\u0005HÆ\u0003J\t\u0010A\u001a\u00020\u0005HÆ\u0003J\t\u0010B\u001a\u00020\u0017HÆ\u0003JÇ\u0001\u0010C\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u00052\b\b\u0002\u0010\u0016\u001a\u00020\u0017HÆ\u0001J\u0013\u0010D\u001a\u00020\u00172\b\u0010E\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010F\u001a\u00020\u0005HÖ\u0001J\t\u0010G\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001dR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001dR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001dR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001dR\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001dR\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001dR\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001dR\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001dR\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001dR\u0011\u0010\u000f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001dR\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001dR\u0011\u0010\u0011\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001dR\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001dR\u0011\u0010\u0013\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001dR\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001dR\u0011\u0010\u0015\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001dR\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/¨\u0006H"}, d2 = {"Lcom/jarvis/app/ThemeApplier$Palette;", "", "id", "", "bg", "", "bg2", "bg3", "panel", "panelSoft", "border", VoiceService.EXTRA_TEXT, "text2", "muted", "faint", "primary", "primaryDark", "cyan", "green", "danger", "onPrimary", "navBg", "lightBars", "", "<init>", "(Ljava/lang/String;IIIIIIIIIIIIIIIIIZ)V", "getId", "()Ljava/lang/String;", "getBg", "()I", "getBg2", "getBg3", "getPanel", "getPanelSoft", "getBorder", "getText", "getText2", "getMuted", "getFaint", "getPrimary", "getPrimaryDark", "getCyan", "getGreen", "getDanger", "getOnPrimary", "getNavBg", "getLightBars", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "copy", "equals", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Palette {
        private final int bg;
        private final int bg2;
        private final int bg3;
        private final int border;
        private final int cyan;
        private final int danger;
        private final int faint;
        private final int green;
        private final String id;
        private final boolean lightBars;
        private final int muted;
        private final int navBg;
        private final int onPrimary;
        private final int panel;
        private final int panelSoft;
        private final int primary;
        private final int primaryDark;
        private final int text;
        private final int text2;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final int getMuted() {
            return this.muted;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final int getFaint() {
            return this.faint;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final int getPrimary() {
            return this.primary;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final int getPrimaryDark() {
            return this.primaryDark;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final int getCyan() {
            return this.cyan;
        }

        /* JADX INFO: renamed from: component15, reason: from getter */
        public final int getGreen() {
            return this.green;
        }

        /* JADX INFO: renamed from: component16, reason: from getter */
        public final int getDanger() {
            return this.danger;
        }

        /* JADX INFO: renamed from: component17, reason: from getter */
        public final int getOnPrimary() {
            return this.onPrimary;
        }

        /* JADX INFO: renamed from: component18, reason: from getter */
        public final int getNavBg() {
            return this.navBg;
        }

        /* JADX INFO: renamed from: component19, reason: from getter */
        public final boolean getLightBars() {
            return this.lightBars;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getBg() {
            return this.bg;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getBg2() {
            return this.bg2;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getBg3() {
            return this.bg3;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getPanel() {
            return this.panel;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final int getPanelSoft() {
            return this.panelSoft;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final int getBorder() {
            return this.border;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final int getText() {
            return this.text;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final int getText2() {
            return this.text2;
        }

        public final Palette copy(String id, int bg, int bg2, int bg3, int panel, int panelSoft, int border, int text, int text2, int muted, int faint, int primary, int primaryDark, int cyan, int green, int danger, int onPrimary, int navBg, boolean lightBars) {
            Intrinsics.checkNotNullParameter(id, "id");
            return new Palette(id, bg, bg2, bg3, panel, panelSoft, border, text, text2, muted, faint, primary, primaryDark, cyan, green, danger, onPrimary, navBg, lightBars);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Palette)) {
                return false;
            }
            Palette palette = (Palette) other;
            return Intrinsics.areEqual(this.id, palette.id) && this.bg == palette.bg && this.bg2 == palette.bg2 && this.bg3 == palette.bg3 && this.panel == palette.panel && this.panelSoft == palette.panelSoft && this.border == palette.border && this.text == palette.text && this.text2 == palette.text2 && this.muted == palette.muted && this.faint == palette.faint && this.primary == palette.primary && this.primaryDark == palette.primaryDark && this.cyan == palette.cyan && this.green == palette.green && this.danger == palette.danger && this.onPrimary == palette.onPrimary && this.navBg == palette.navBg && this.lightBars == palette.lightBars;
        }

        public int hashCode() {
            return (((((((((((((((((((((((((((((((((((this.id.hashCode() * 31) + Integer.hashCode(this.bg)) * 31) + Integer.hashCode(this.bg2)) * 31) + Integer.hashCode(this.bg3)) * 31) + Integer.hashCode(this.panel)) * 31) + Integer.hashCode(this.panelSoft)) * 31) + Integer.hashCode(this.border)) * 31) + Integer.hashCode(this.text)) * 31) + Integer.hashCode(this.text2)) * 31) + Integer.hashCode(this.muted)) * 31) + Integer.hashCode(this.faint)) * 31) + Integer.hashCode(this.primary)) * 31) + Integer.hashCode(this.primaryDark)) * 31) + Integer.hashCode(this.cyan)) * 31) + Integer.hashCode(this.green)) * 31) + Integer.hashCode(this.danger)) * 31) + Integer.hashCode(this.onPrimary)) * 31) + Integer.hashCode(this.navBg)) * 31) + Boolean.hashCode(this.lightBars);
        }

        public String toString() {
            return "Palette(id=" + this.id + ", bg=" + this.bg + ", bg2=" + this.bg2 + ", bg3=" + this.bg3 + ", panel=" + this.panel + ", panelSoft=" + this.panelSoft + ", border=" + this.border + ", text=" + this.text + ", text2=" + this.text2 + ", muted=" + this.muted + ", faint=" + this.faint + ", primary=" + this.primary + ", primaryDark=" + this.primaryDark + ", cyan=" + this.cyan + ", green=" + this.green + ", danger=" + this.danger + ", onPrimary=" + this.onPrimary + ", navBg=" + this.navBg + ", lightBars=" + this.lightBars + ")";
        }

        public Palette(String id, int bg, int bg2, int bg3, int panel, int panelSoft, int border, int text, int text2, int muted, int faint, int primary, int primaryDark, int cyan, int green, int danger, int onPrimary, int navBg, boolean lightBars) {
            Intrinsics.checkNotNullParameter(id, "id");
            this.id = id;
            this.bg = bg;
            this.bg2 = bg2;
            this.bg3 = bg3;
            this.panel = panel;
            this.panelSoft = panelSoft;
            this.border = border;
            this.text = text;
            this.text2 = text2;
            this.muted = muted;
            this.faint = faint;
            this.primary = primary;
            this.primaryDark = primaryDark;
            this.cyan = cyan;
            this.green = green;
            this.danger = danger;
            this.onPrimary = onPrimary;
            this.navBg = navBg;
            this.lightBars = lightBars;
        }

        public final String getId() {
            return this.id;
        }

        public final int getBg() {
            return this.bg;
        }

        public final int getBg2() {
            return this.bg2;
        }

        public final int getBg3() {
            return this.bg3;
        }

        public final int getPanel() {
            return this.panel;
        }

        public final int getPanelSoft() {
            return this.panelSoft;
        }

        public final int getBorder() {
            return this.border;
        }

        public final int getText() {
            return this.text;
        }

        public final int getText2() {
            return this.text2;
        }

        public final int getMuted() {
            return this.muted;
        }

        public final int getFaint() {
            return this.faint;
        }

        public final int getPrimary() {
            return this.primary;
        }

        public final int getPrimaryDark() {
            return this.primaryDark;
        }

        public final int getCyan() {
            return this.cyan;
        }

        public final int getGreen() {
            return this.green;
        }

        public final int getDanger() {
            return this.danger;
        }

        public final int getOnPrimary() {
            return this.onPrimary;
        }

        public final int getNavBg() {
            return this.navBg;
        }

        public final boolean getLightBars() {
            return this.lightBars;
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final Palette palette(String theme) {
        Intrinsics.checkNotNullParameter(theme, "theme");
        switch (theme.hashCode()) {
            case -1176022045:
                if (theme.equals("STEALTH")) {
                    return new Palette("STEALTH", Color.parseColor("#0A0A0C"), Color.parseColor("#121216"), Color.parseColor("#1A1A20"), Color.parseColor("#14141A"), Color.parseColor("#1C1C24"), Color.parseColor("#2C2C36"), Color.parseColor("#ECECF0"), Color.parseColor("#C8C8D0"), Color.parseColor("#8A8A98"), Color.parseColor("#555566"), Color.parseColor("#A8B0BC"), Color.parseColor("#7E8694"), Color.parseColor("#8EC8D4"), Color.parseColor("#6BCB8F"), Color.parseColor("#E07070"), Color.parseColor("#101014"), Color.parseColor("#070709"), false);
                }
                break;
            case 65074:
                if (theme.equals("ARC")) {
                    return new Palette("ARC", Color.parseColor("#020B12"), Color.parseColor("#041822"), Color.parseColor("#072433"), Color.parseColor("#0A1C28"), Color.parseColor("#0D2433"), Color.parseColor("#1E4A5C"), Color.parseColor("#E8FBFF"), Color.parseColor("#C5E8F0"), Color.parseColor("#7AA8B8"), Color.parseColor("#3E6A7A"), Color.parseColor("#62E9F4"), Color.parseColor("#2FB9CA"), Color.parseColor("#9AF6FF"), Color.parseColor("#61D89D"), Color.parseColor("#F05D52"), Color.parseColor("#021018"), Color.parseColor("#01080E"), false);
                }
                break;
            case 72432886:
                if (theme.equals("LIGHT")) {
                    return new Palette("LIGHT", Color.parseColor("#F4F1EA"), Color.parseColor("#EBE6DC"), Color.parseColor("#E0DAD0"), Color.parseColor("#FFFFFF"), Color.parseColor("#F8F5EF"), Color.parseColor("#D5CFC4"), Color.parseColor("#1A1F28"), Color.parseColor("#2C3340"), Color.parseColor("#5A6574"), Color.parseColor("#8A93A1"), Color.parseColor("#C4892A"), Color.parseColor("#A06E1E"), Color.parseColor("#2A8A9A"), Color.parseColor("#2F8F5B"), Color.parseColor("#C4453B"), Color.parseColor("#1A1408"), Color.parseColor("#E8E2D6"), true);
                }
                break;
        }
        return new Palette("CLASSIC", Color.parseColor("#03060B"), Color.parseColor("#07101A"), Color.parseColor("#0B1522"), Color.parseColor("#0A131F"), Color.parseColor("#0D1A28"), Color.parseColor("#2A3A4C"), Color.parseColor("#F7F8FB"), Color.parseColor("#DCE4EE"), Color.parseColor("#8F9BAE"), Color.parseColor("#4D5A6D"), Color.parseColor("#F3BB58"), Color.parseColor("#D99738"), Color.parseColor("#62E9F4"), Color.parseColor("#61D89D"), Color.parseColor("#F05D52"), Color.parseColor("#17130B"), Color.parseColor("#03080D"), false);
    }

    public final void applyToActivity(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Palette p = palette(JarvisSettings.INSTANCE.theme(activity));
        activity.getWindow().addFlags(Integer.MIN_VALUE);
        activity.getWindow().setStatusBarColor(p.getBg2());
        activity.getWindow().setNavigationBarColor(p.getNavBg());
        WindowCompat.getInsetsController(activity.getWindow(), activity.getWindow().getDecorView()).setAppearanceLightStatusBars(p.getLightBars());
        View viewFindViewById = activity.findViewById(android.R.id.content);
        if (viewFindViewById != null) {
            viewFindViewById.setBackgroundColor(p.getBg());
        }
        View viewFindViewById2 = activity.findViewById(R.id.dashboardRoot);
        if (viewFindViewById2 != null) {
            viewFindViewById2.setBackgroundColor(p.getBg());
        }
        View viewFindViewById3 = activity.findViewById(R.id.shellTop);
        if (viewFindViewById3 != null) {
            viewFindViewById3.setBackgroundColor(p.getBg2());
        }
        View viewFindViewById4 = activity.findViewById(R.id.bottomNav);
        if (viewFindViewById4 != null) {
            viewFindViewById4.setBackgroundColor(p.getNavBg());
        }
        TextView textView = (TextView) activity.findViewById(R.id.statusTitle);
        if (textView != null) {
            textView.setTextColor(p.getText());
        }
        TextView textView2 = (TextView) activity.findViewById(R.id.topRuntimeState);
        if (textView2 != null) {
            textView2.setTextColor(p.getGreen());
        }
    }

    public static /* synthetic */ void applyToScreen$default(ThemeApplier themeApplier, View view, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
            Context context = view.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            str = jarvisSettings.theme(context);
        }
        themeApplier.applyToScreen(view, str);
    }

    public final void applyToScreen(View root, String theme) {
        Intrinsics.checkNotNullParameter(root, "root");
        Intrinsics.checkNotNullParameter(theme, "theme");
        Palette p = palette(theme);
        walk(root, p);
    }

    public final void recreateWithTheme(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        activity.recreate();
    }

    public final int primaryColor(String theme) {
        Intrinsics.checkNotNullParameter(theme, "theme");
        return palette(theme).getPrimary();
    }

    public final int mutedColor(String theme) {
        Intrinsics.checkNotNullParameter(theme, "theme");
        return palette(theme).getMuted();
    }

    public final int bgColor(String theme) {
        Intrinsics.checkNotNullParameter(theme, "theme");
        return palette(theme).getBg();
    }

    public final int textColor(String theme) {
        Intrinsics.checkNotNullParameter(theme, "theme");
        return palette(theme).getText();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:17:0x0031 A[ORIG_RETURN, RETURN] */
    public final String personaSystemHint(String persona) {
        Intrinsics.checkNotNullParameter(persona, "persona");
        switch (persona) {
            case "DEVELOPER":
                return "Ты JARVIS в developer-режиме: технично, точно, с терминами, упоминай статусы/ошибки если уместно, без сюсюканья.";
            case "FRIENDLY":
                return "Ты JARVIS в дружелюбном режиме: теплый, живой, с лёгким юмором, обращайся на «ты», короткие живые фразы, без канцелярита.";
            case "MINIMAL":
                return "Ты JARVIS в минимальном режиме: максимум 1 короткое предложение, без воды, без эмодзи, только суть.";
            default:
                return "Ты JARVIS Classic: спокойный, вежливый, собранный дворецкий. Чёткие ответы, лёгкая сдержанность, без панибратства.";
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final void walk(View view, Palette p) {
        view.getBackground();
        Object tag = view.getTag();
        String tag2 = tag instanceof String ? (String) tag : null;
        if (tag2 != null) {
            switch (tag2.hashCode()) {
                case -1876469463:
                    if (tag2.equals("theme_primary_btn")) {
                        view.setBackground(rounded$default(this, p.getPrimary(), p.getPrimary(), 13.0f, 0.0f, 8, null));
                    }
                    break;
                case -721829450:
                    if (tag2.equals("theme_ghost_btn")) {
                        view.setBackground(rounded$default(this, p.getBg3(), p.getBorder(), 13.0f, 0.0f, 8, null));
                    }
                    break;
                case -157243378:
                    if (tag2.equals("theme_panel")) {
                        view.setBackground(rounded$default(this, p.getPanel(), p.getBorder(), 18.0f, 0.0f, 8, null));
                    }
                    break;
                case 548729798:
                    if (tag2.equals("theme_card")) {
                        view.setBackground(rounded$default(this, p.getBg3(), p.getBorder(), 14.0f, 0.0f, 8, null));
                    }
                    break;
                case 548736258:
                    if (tag2.equals("theme_chip")) {
                        view.setBackground(rounded$default(this, p.getBg3(), p.getBorder(), 999.0f, 0.0f, 8, null));
                    }
                    break;
                case 549219552:
                    if (tag2.equals("theme_soft")) {
                        view.setBackground(rounded$default(this, p.getPanelSoft(), p.getBorder(), 14.0f, 0.0f, 8, null));
                    }
                    break;
            }
        }
        if (view instanceof EditText) {
            ((EditText) view).setTextColor(p.getText());
            ((EditText) view).setHintTextColor(p.getFaint());
        } else if (view instanceof Button) {
            int c = ((Button) view).getCurrentTextColor();
            if (isNear$default(this, c, Color.parseColor("#17130B"), 0, 4, null) || isNear$default(this, c, Color.parseColor("#1A1408"), 0, 4, null) || isNear$default(this, c, Color.parseColor("#0A0A0D"), 0, 4, null)) {
                ((Button) view).setTextColor(p.getOnPrimary());
                ((Button) view).setBackground(rounded$default(this, p.getPrimary(), p.getPrimary(), 13.0f, 0.0f, 8, null));
            } else if (isNear$default(this, c, Color.parseColor("#9A96A8"), 0, 4, null) || isNear$default(this, c, Color.parseColor("#8F9BAE"), 0, 4, null)) {
                ((Button) view).setTextColor(p.getMuted());
            }
        } else if (view instanceof TextView) {
            remapText((TextView) view, p);
        }
        if (view instanceof ViewGroup) {
            Integer solid = extractSolidColor(view);
            if (solid != null && isDarkish(solid.intValue())) {
                if (isNear$default(this, solid.intValue(), Color.parseColor("#03060B"), 0, 4, null) || isNear$default(this, solid.intValue(), Color.parseColor("#0F0E14"), 0, 4, null) || isNear$default(this, solid.intValue(), Color.parseColor("#0A0C10"), 0, 4, null) || isNear$default(this, solid.intValue(), Color.parseColor("#08070B"), 0, 4, null)) {
                    view.setBackgroundColor(p.getBg());
                } else if (isNear$default(this, solid.intValue(), Color.parseColor("#07101A"), 0, 4, null) || isNear$default(this, solid.intValue(), Color.parseColor("#1A1822"), 0, 4, null) || isNear$default(this, solid.intValue(), Color.parseColor("#0A131F"), 0, 4, null)) {
                    view.setBackgroundColor(p.getBg2());
                }
            }
            int childCount = ((ViewGroup) view).getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = ((ViewGroup) view).getChildAt(i);
                Intrinsics.checkNotNullExpressionValue(childAt, "getChildAt(...)");
                walk(childAt, p);
            }
        }
    }

    private final void remapText(TextView tv, Palette p) {
        int c = tv.getCurrentTextColor();
        if (isNear$default(this, c, Color.parseColor("#F3BB58"), 0, 4, null) || isNear$default(this, c, Color.parseColor("#E3A542"), 0, 4, null) || isNear$default(this, c, Color.parseColor("#FFD47B"), 0, 4, null) || isNear$default(this, c, Color.parseColor("#D99738"), 0, 4, null)) {
            tv.setTextColor(p.getPrimary());
            return;
        }
        if (isNear$default(this, c, Color.parseColor("#62E9F4"), 0, 4, null) || isNear$default(this, c, Color.parseColor("#2FB9CA"), 0, 4, null) || isNear$default(this, c, Color.parseColor("#4FB8AC"), 0, 4, null)) {
            tv.setTextColor(p.getCyan());
            return;
        }
        if (!isNear$default(this, c, Color.parseColor("#61D89D"), 0, 4, null)) {
            if (isNear$default(this, c, Color.parseColor("#F05D52"), 0, 4, null) || isNear$default(this, c, Color.parseColor("#D9634A"), 0, 4, null) || isNear$default(this, c, Color.parseColor("#E46B58"), 0, 4, null)) {
                tv.setTextColor(p.getDanger());
                return;
            }
            if (isNear$default(this, c, Color.parseColor("#F7F8FB"), 0, 4, null) || isNear$default(this, c, Color.parseColor("#F2EEE6"), 0, 4, null) || isNear$default(this, c, Color.parseColor("#F4F0E7"), 0, 4, null) || isNear$default(this, c, Color.parseColor("#E8FBFF"), 0, 4, null)) {
                tv.setTextColor(p.getText());
                return;
            }
            if (isNear$default(this, c, Color.parseColor("#DCE4EE"), 0, 4, null) || isNear$default(this, c, Color.parseColor("#C5E8F0"), 0, 4, null)) {
                tv.setTextColor(p.getText2());
                return;
            }
            if (isNear$default(this, c, Color.parseColor("#8F9BAE"), 0, 4, null) || isNear$default(this, c, Color.parseColor("#9A96A8"), 0, 4, null) || isNear$default(this, c, Color.parseColor("#9DA5AE"), 0, 4, null)) {
                tv.setTextColor(p.getMuted());
                return;
            }
            if (isNear$default(this, c, Color.parseColor("#4D5A6D"), 0, 4, null) || isNear$default(this, c, Color.parseColor("#635D71"), 0, 4, null) || isNear$default(this, c, Color.parseColor("#626B76"), 0, 4, null)) {
                tv.setTextColor(p.getFaint());
                return;
            } else {
                if (isNear$default(this, c, Color.parseColor("#17130B"), 0, 4, null) || isNear$default(this, c, Color.parseColor("#1A1408"), 0, 4, null)) {
                    tv.setTextColor(p.getOnPrimary());
                    return;
                }
                return;
            }
        }
        tv.setTextColor(p.getGreen());
    }

    public static /* synthetic */ GradientDrawable rounded$default(ThemeApplier themeApplier, int i, int i2, float f, float f2, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            f2 = 1.0f;
        }
        return themeApplier.rounded(i, i2, f, f2);
    }

    public final GradientDrawable rounded(int fill, int stroke, float radiusDp, float strokeDp) {
        GradientDrawable $this$rounded_u24lambda_u240 = new GradientDrawable();
        $this$rounded_u24lambda_u240.setShape(0);
        $this$rounded_u24lambda_u240.setCornerRadius(radiusDp * 3.0f);
        $this$rounded_u24lambda_u240.setColor(fill);
        $this$rounded_u24lambda_u240.setStroke(RangesKt.coerceAtLeast((int) (3.0f * strokeDp), 1), stroke);
        return $this$rounded_u24lambda_u240;
    }

    public static /* synthetic */ GradientDrawable roundedDp$default(ThemeApplier themeApplier, View view, int i, int i2, float f, float f2, int i3, Object obj) {
        if ((i3 & 16) != 0) {
            f2 = 1.0f;
        }
        return themeApplier.roundedDp(view, i, i2, f, f2);
    }

    public final GradientDrawable roundedDp(View view, int fill, int stroke, float radiusDp, float strokeDp) {
        Intrinsics.checkNotNullParameter(view, "view");
        float d = view.getResources().getDisplayMetrics().density;
        GradientDrawable $this$roundedDp_u24lambda_u240 = new GradientDrawable();
        $this$roundedDp_u24lambda_u240.setShape(0);
        $this$roundedDp_u24lambda_u240.setCornerRadius(radiusDp * d);
        $this$roundedDp_u24lambda_u240.setColor(fill);
        $this$roundedDp_u24lambda_u240.setStroke(RangesKt.coerceAtLeast((int) (strokeDp * d), 1), stroke);
        return $this$roundedDp_u24lambda_u240;
    }

    private final Integer extractSolidColor(View view) {
        Object element$iv;
        Drawable bg = view.getBackground();
        if (bg == null) {
            return null;
        }
        try {
            Object[] methods = bg.getClass().getMethods();
            Intrinsics.checkNotNullExpressionValue(methods, "getMethods(...)");
            Object[] $this$firstOrNull$iv = methods;
            int length = $this$firstOrNull$iv.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    element$iv = $this$firstOrNull$iv[i];
                    Method it = (Method) element$iv;
                    if (Intrinsics.areEqual(it.getName(), "getColor") && it.getParameterCount() == 0) {
                        break;
                    }
                    i++;
                } else {
                    element$iv = null;
                    break;
                }
            }
            Method field = (Method) element$iv;
            Object objInvoke = field != null ? field.invoke(bg, new Object[0]) : null;
            if (objInvoke instanceof Integer) {
                return (Integer) objInvoke;
            }
            return null;
        } catch (Throwable th) {
            return null;
        }
    }

    private final boolean isDarkish(int c) {
        int r = Color.red(c);
        int g = Color.green(c);
        int b = Color.blue(c);
        return ((r + g) + b) / 3 < 80;
    }

    static /* synthetic */ boolean isNear$default(ThemeApplier themeApplier, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i3 = 18;
        }
        return themeApplier.isNear(i, i2, i3);
    }

    private final boolean isNear(int a, int b, int tol) {
        return Math.abs(Color.red(a) - Color.red(b)) <= tol && Math.abs(Color.green(a) - Color.green(b)) <= tol && Math.abs(Color.blue(a) - Color.blue(b)) <= tol;
    }
}
