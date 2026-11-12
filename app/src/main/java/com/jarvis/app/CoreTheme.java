package com.jarvis.app;

import android.graphics.Color;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.jarvis.core.model.AssistantState;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: CoreTheme.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002-.B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\b\b\u0002\u0010(\u001a\u00020\u0005J\u0010\u0010)\u001a\u00020\u00052\u0006\u0010*\u001a\u00020\u0005H\u0002J\u0016\u0010+\u001a\u00020\u00052\u0006\u0010*\u001a\u00020\u00052\u0006\u0010,\u001a\u00020\u0005R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0007R\u0011\u0010\u0018\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0007R\u0011\u0010\u001a\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0007R\u0011\u0010\u001c\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0007R\u0011\u0010\u001e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0007R\u0011\u0010 \u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0007R\u0011\u0010\"\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0007¨\u0006/"}, d2 = {"Lcom/jarvis/app/CoreTheme;", "", "<init>", "()V", "BG", "", "getBG", "()I", "BG_DEEP", "getBG_DEEP", "PANEL", "getPANEL", "BORDER", "getBORDER", "TEXT", "getTEXT", "MUTED", "getMUTED", "FAINT", "getFAINT", "BRASS", "getBRASS", "BRASS_DEEP", "getBRASS_DEEP", "TEAL", "getTEAL", "TEAL_DEEP", "getTEAL_DEEP", "VIOLET", "getVIOLET", "VIOLET_DEEP", "getVIOLET_DEEP", "DANGER", "getDANGER", "DANGER_DEEP", "getDANGER_DEEP", "forState", "Lcom/jarvis/app/CoreTheme$CoreColors;", "state", "Lcom/jarvis/core/model/AssistantState;", "accent", "darken", TypedValues.Custom.S_COLOR, "withAlpha", "alpha", "CoreColors", "GlyphMode", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CoreTheme {
    public static final CoreTheme INSTANCE = new CoreTheme();
    private static final int BG = Color.parseColor("#0F0E14");
    private static final int BG_DEEP = Color.parseColor("#08070B");
    private static final int PANEL = Color.parseColor("#1A1822");
    private static final int BORDER = Color.parseColor("#2E2B3A");
    private static final int TEXT = Color.parseColor("#F2EEE6");
    private static final int MUTED = Color.parseColor("#9A96A8");
    private static final int FAINT = Color.parseColor("#635D71");
    private static final int BRASS = Color.parseColor("#E3A542");
    private static final int BRASS_DEEP = Color.parseColor("#8A6A2E");
    private static final int TEAL = Color.parseColor("#4FB8AC");
    private static final int TEAL_DEEP = Color.parseColor("#2A6F68");
    private static final int VIOLET = Color.parseColor("#7C6BD6");
    private static final int VIOLET_DEEP = Color.parseColor("#453B7A");
    private static final int DANGER = Color.parseColor("#D9634A");
    private static final int DANGER_DEEP = Color.parseColor("#7A2E1F");

    /* JADX INFO: compiled from: CoreTheme.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/jarvis/app/CoreTheme$GlyphMode;", "", "<init>", "(Ljava/lang/String;I)V", "OFF", "WAVE_FULL", "WAVE_SOFT", "THINKING", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum GlyphMode {
        OFF,
        WAVE_FULL,
        WAVE_SOFT,
        THINKING;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<GlyphMode> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: compiled from: CoreTheme.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AssistantState.values().length];
            try {
                iArr[AssistantState.OFFLINE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[AssistantState.STARTING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[AssistantState.READY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[AssistantState.IDLE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[AssistantState.LISTENING.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[AssistantState.THINKING.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[AssistantState.EXECUTING.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[AssistantState.SPEAKING.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[AssistantState.ERROR.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private CoreTheme() {
    }

    public final int getBG() {
        return BG;
    }

    public final int getBG_DEEP() {
        return BG_DEEP;
    }

    public final int getPANEL() {
        return PANEL;
    }

    public final int getBORDER() {
        return BORDER;
    }

    public final int getTEXT() {
        return TEXT;
    }

    public final int getMUTED() {
        return MUTED;
    }

    public final int getFAINT() {
        return FAINT;
    }

    public final int getBRASS() {
        return BRASS;
    }

    public final int getBRASS_DEEP() {
        return BRASS_DEEP;
    }

    public final int getTEAL() {
        return TEAL;
    }

    public final int getTEAL_DEEP() {
        return TEAL_DEEP;
    }

    public final int getVIOLET() {
        return VIOLET;
    }

    public final int getVIOLET_DEEP() {
        return VIOLET_DEEP;
    }

    public final int getDANGER() {
        return DANGER;
    }

    public final int getDANGER_DEEP() {
        return DANGER_DEEP;
    }

    /* JADX INFO: compiled from: CoreTheme.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J\t\u0010\u001a\u001a\u00020\nHÆ\u0003J;\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001J\t\u0010 \u001a\u00020\bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006!"}, d2 = {"Lcom/jarvis/app/CoreTheme$CoreColors;", "", "core", "", "deep", "breatheMs", "", "label", "", "glyph", "Lcom/jarvis/app/CoreTheme$GlyphMode;", "<init>", "(IIJLjava/lang/String;Lcom/jarvis/app/CoreTheme$GlyphMode;)V", "getCore", "()I", "getDeep", "getBreatheMs", "()J", "getLabel", "()Ljava/lang/String;", "getGlyph", "()Lcom/jarvis/app/CoreTheme$GlyphMode;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class CoreColors {
        private final long breatheMs;
        private final int core;
        private final int deep;
        private final GlyphMode glyph;
        private final String label;

        public static /* synthetic */ CoreColors copy$default(CoreColors coreColors, int i, int i2, long j, String str, GlyphMode glyphMode, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = coreColors.core;
            }
            if ((i3 & 2) != 0) {
                i2 = coreColors.deep;
            }
            int i4 = i2;
            if ((i3 & 4) != 0) {
                j = coreColors.breatheMs;
            }
            long j2 = j;
            if ((i3 & 8) != 0) {
                str = coreColors.label;
            }
            String str2 = str;
            if ((i3 & 16) != 0) {
                glyphMode = coreColors.glyph;
            }
            return coreColors.copy(i, i4, j2, str2, glyphMode);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getCore() {
            return this.core;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getDeep() {
            return this.deep;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final long getBreatheMs() {
            return this.breatheMs;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getLabel() {
            return this.label;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final GlyphMode getGlyph() {
            return this.glyph;
        }

        public final CoreColors copy(int core, int deep, long breatheMs, String label, GlyphMode glyph) {
            Intrinsics.checkNotNullParameter(label, "label");
            Intrinsics.checkNotNullParameter(glyph, "glyph");
            return new CoreColors(core, deep, breatheMs, label, glyph);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CoreColors)) {
                return false;
            }
            CoreColors coreColors = (CoreColors) other;
            return this.core == coreColors.core && this.deep == coreColors.deep && this.breatheMs == coreColors.breatheMs && Intrinsics.areEqual(this.label, coreColors.label) && this.glyph == coreColors.glyph;
        }

        public int hashCode() {
            return (((((((Integer.hashCode(this.core) * 31) + Integer.hashCode(this.deep)) * 31) + Long.hashCode(this.breatheMs)) * 31) + this.label.hashCode()) * 31) + this.glyph.hashCode();
        }

        public String toString() {
            return "CoreColors(core=" + this.core + ", deep=" + this.deep + ", breatheMs=" + this.breatheMs + ", label=" + this.label + ", glyph=" + this.glyph + ")";
        }

        public CoreColors(int core, int deep, long breatheMs, String label, GlyphMode glyph) {
            Intrinsics.checkNotNullParameter(label, "label");
            Intrinsics.checkNotNullParameter(glyph, "glyph");
            this.core = core;
            this.deep = deep;
            this.breatheMs = breatheMs;
            this.label = label;
            this.glyph = glyph;
        }

        public final int getCore() {
            return this.core;
        }

        public final int getDeep() {
            return this.deep;
        }

        public final long getBreatheMs() {
            return this.breatheMs;
        }

        public final String getLabel() {
            return this.label;
        }

        public final GlyphMode getGlyph() {
            return this.glyph;
        }
    }

    public static /* synthetic */ CoreColors forState$default(CoreTheme coreTheme, AssistantState assistantState, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = BRASS;
        }
        return coreTheme.forState(assistantState, i);
    }

    public final CoreColors forState(AssistantState state, int accent) {
        Intrinsics.checkNotNullParameter(state, "state");
        switch (WhenMappings.$EnumSwitchMapping$0[state.ordinal()]) {
            case 1:
                return new CoreColors(FAINT, Color.parseColor("#3A3644"), 3200L, "● OFFLINE", GlyphMode.OFF);
            case 2:
                return new CoreColors(accent, darken(accent), 1600L, "ЗАПУСК ЯДРА…", GlyphMode.OFF);
            case 3:
            case 4:
                return new CoreColors(accent, darken(accent), 3600L, "готов · скажи «джарвис»", GlyphMode.OFF);
            case 5:
                return new CoreColors(TEAL, TEAL_DEEP, 1800L, "слушаю…", GlyphMode.WAVE_FULL);
            case 6:
                return new CoreColors(VIOLET, VIOLET_DEEP, 2200L, "обрабатываю", GlyphMode.THINKING);
            case 7:
                return new CoreColors(VIOLET, VIOLET_DEEP, 1600L, "выполняю", GlyphMode.THINKING);
            case 8:
                return new CoreColors(accent, darken(accent), 2600L, "отвечаю", GlyphMode.WAVE_SOFT);
            case 9:
                return new CoreColors(DANGER, DANGER_DEEP, 2400L, "ошибка ядра", GlyphMode.OFF);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final int darken(int color) {
        return Color.rgb(RangesKt.coerceIn((int) (Color.red(color) * 0.55f), 0, 255), RangesKt.coerceIn((int) (Color.green(color) * 0.55f), 0, 255), RangesKt.coerceIn((int) (Color.blue(color) * 0.55f), 0, 255));
    }

    public final int withAlpha(int color, int alpha) {
        return Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
    }
}
