package com.jarvis.app.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.SystemClock;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.ViewCompat;
import com.jarvis.core.aioutput.CloudToolAction;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: ReactorView.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u0000 82\u00020\u0001:\u000278B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\u0014\u001a\u00020\u0015JH\u0010&\u001a\u00020\u00152\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u001f2\u0006\u0010*\u001a\u00020\u001f2\u0006\u0010+\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020\u000f2\u0006\u0010/\u001a\u00020\u001fH\u0002JH\u00100\u001a\u00020\u00152\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u001f2\u0006\u0010*\u001a\u00020\u001f2\u0006\u0010+\u001a\u00020\u001f2\u0006\u00101\u001a\u00020\u000f2\u0006\u00102\u001a\u00020\u001f2\u0006\u00103\u001a\u00020\u001f2\u0006\u00104\u001a\u00020\u001fH\u0002J\u0010\u00105\u001a\u00020\u00152\u0006\u00106\u001a\u00020(H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/jarvis/app/ui/ReactorView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "skin", "Lcom/jarvis/app/ui/Skin;", "<init>", "(Landroid/content/Context;Lcom/jarvis/app/ui/Skin;)V", "mode", "Lcom/jarvis/app/ui/ReactorView$Mode;", "getMode", "()Lcom/jarvis/app/ui/ReactorView$Mode;", "setMode", "(Lcom/jarvis/app/ui/ReactorView$Mode;)V", "step", "", "getStep", "()I", "setStep", "(I)V", "kick", "", "add", "Landroid/graphics/PorterDuffXfermode;", "dstIn", "stroke", "Landroid/graphics/Paint;", "fill", "oval", "Landroid/graphics/RectF;", "energy", "", "spin", "power", "boost", TypedValues.CycleType.S_WAVE_PHASE, "last", "", "arc", "c", "Landroid/graphics/Canvas;", "cx", "cy", "r", "startRad", "sweepRad", TypedValues.Custom.S_COLOR, "width", "plates", "n", "w", "span", "rot", "onDraw", "canvas", "Mode", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ReactorView extends View {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final long FRAME_MS = 33;

    @Deprecated
    public static final float HALF_PI = 1.5707964f;

    @Deprecated
    public static final float TAU = 6.2831855f;
    private final PorterDuffXfermode add;
    private float boost;
    private final PorterDuffXfermode dstIn;
    private float energy;
    private final Paint fill;
    private long last;
    private Mode mode;
    private final RectF oval;
    private float phase;
    private float power;
    private final Skin skin;
    private float spin;
    private int step;
    private final Paint stroke;

    /* JADX INFO: compiled from: ReactorView.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Mode.values().length];
            try {
                iArr[Mode.OFF.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Mode.SPEAK.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Mode.LISTEN.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: compiled from: ReactorView.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0007\n\u0002\b\r\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lcom/jarvis/app/ui/ReactorView$Mode;", "", "energy", "", "speed", "<init>", "(Ljava/lang/String;IFF)V", "getEnergy", "()F", "getSpeed", "OFF", "IDLE", "LISTEN", "THINK", CloudToolAction.MARKER, "SPEAK", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum Mode {
        OFF(0.0f, 0.0f),
        IDLE(0.2f, 0.3f),
        LISTEN(0.6f, 0.7f),
        THINK(0.45f, 1.7f),
        ACT(0.55f, 1.1f),
        SPEAK(0.85f, 0.55f);

        private final float energy;
        private final float speed;
        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<Mode> getEntries() {
            return $ENTRIES;
        }

        Mode(float energy, float speed) {
            this.energy = energy;
            this.speed = speed;
        }

        public final float getEnergy() {
            return this.energy;
        }

        public final float getSpeed() {
            return this.speed;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReactorView(Context context, Skin skin) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(skin, "skin");
        this.skin = skin;
        this.mode = Mode.OFF;
        this.step = -1;
        this.add = new PorterDuffXfermode(PorterDuff.Mode.ADD);
        this.dstIn = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        Paint $this$stroke_u24lambda_u240 = new Paint(1);
        $this$stroke_u24lambda_u240.setStyle(Paint.Style.STROKE);
        $this$stroke_u24lambda_u240.setStrokeCap(Paint.Cap.BUTT);
        this.stroke = $this$stroke_u24lambda_u240;
        this.fill = new Paint(1);
        this.oval = new RectF();
    }

    public final Mode getMode() {
        return this.mode;
    }

    public final void setMode(Mode mode) {
        Intrinsics.checkNotNullParameter(mode, "<set-?>");
        this.mode = mode;
    }

    public final int getStep() {
        return this.step;
    }

    public final void setStep(int i) {
        this.step = i;
    }

    public final void kick() {
        this.boost = 1.0f;
    }

    private final void arc(Canvas c, float cx, float cy, float r, float startRad, float sweepRad, int color, float width) {
        this.stroke.setColor(color);
        this.stroke.setStrokeWidth(width);
        this.oval.set(cx - r, cy - r, cx + r, cy + r);
        c.drawArc(this.oval, (float) Math.toDegrees(startRad), (float) Math.toDegrees(sweepRad), false, this.stroke);
    }

    private final void plates(Canvas c, float cx, float cy, float r, int n, float w, float span, float rot) {
        for (int i = 0; i < n; i++) {
            float a = ((i * 6.2831855f) / n) + rot;
            arc(c, cx, cy, r, a, span, this.skin.getMetal(), w);
            float f = 2;
            arc(c, cx, cy, r + (w / f), a, span, UiKt.alpha(Color.parseColor("#BED4EB"), 0.42f), 1.0f);
            arc(c, cx, cy, r - (w / f), a, span, UiKt.alpha(ViewCompat.MEASURED_STATE_MASK, 0.5f), 1.0f);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float fAbs;
        float f;
        int i;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        long now = SystemClock.uptimeMillis();
        float dt = this.last == 0 ? 0.0f : RangesKt.coerceAtMost((now - this.last) / 1000.0f, 0.05f);
        this.last = now;
        float t = now;
        this.energy += (this.mode.getEnergy() - this.energy) * 0.06f;
        this.spin += (this.mode.getSpeed() - this.spin) * 0.05f;
        this.power += ((this.mode == Mode.OFF ? 0.0f : 1.0f) - this.power) * 0.06f;
        this.boost *= 0.965f;
        this.phase += (this.spin + (this.boost * 2.6f)) * dt;
        float e = this.energy + (this.boost * 0.5f);
        switch (WhenMappings.$EnumSwitchMapping$0[this.mode.ordinal()]) {
            case 1:
                fAbs = 0.0f;
                break;
            case 2:
                fAbs = Math.abs(((float) Math.sin(t / 130.0f)) * ((float) Math.sin(t / 410.0f)));
                break;
            case 3:
                fAbs = Math.abs((float) Math.sin(t / 240.0f)) * 0.6f;
                break;
            default:
                fAbs = (((float) Math.sin(t / 1500.0f)) * 0.2f) + 0.2f;
                break;
        }
        float beat = fAbs;
        float w = getWidth();
        float h = getHeight();
        float f2 = 2;
        float cx = w / f2;
        float cy = h / f2;
        float r = Math.min(w, h) * 0.47f;
        float dim = (this.power * 0.7f) + 0.3f;
        int layer = canvas.saveLayer(0.0f, 0.0f, w, h, null);
        this.fill.setXfermode(this.add);
        this.fill.setShader(new RadialGradient(cx, cy, r * 1.25f, new int[]{UiKt.alpha(this.skin.getAccent(), (e * 0.35f) + 0.3f), UiKt.alpha(this.skin.getAccent(), (e * 0.1f) + 0.1f), UiKt.alpha(this.skin.getAccent(), 0.0f)}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP));
        float cy2 = cy;
        canvas.drawCircle(cx, cy2, 1.25f * r, this.fill);
        Object obj = null;
        this.fill.setShader(null);
        this.fill.setXfermode(null);
        float f3 = 0.55f;
        this.stroke.setColor(UiKt.alpha(this.skin.getAccent(), 0.55f));
        this.stroke.setStrokeWidth(1.0f);
        int i2 = 0;
        while (i2 < 90) {
            float a = ((i2 * 6.2831855f) / 90) + (this.phase * 0.12f);
            float l = i2 % 5 == 0 ? 0.94f : 0.97f;
            canvas.drawLine(cx + (((float) Math.cos(a)) * r * l), cy2 + (((float) Math.sin(a)) * r * l), cx + (((float) Math.cos(a)) * r), cy2 + (((float) Math.sin(a)) * r), this.stroke);
            i2++;
            obj = obj;
            layer = layer;
            f3 = 0.55f;
            t = t;
            dt = dt;
            cy2 = cy2;
        }
        float f4 = f3;
        float cy3 = cy2;
        int layer2 = layer;
        float t2 = t;
        float hotA = 0.22f;
        plates(canvas, cx, cy3, r * 0.88f, 12, r * 0.085f, 0.34f, (-this.phase) * 0.22f);
        plates(canvas, cx, cy3, r * 0.6f, 10, r * 0.06f, 0.42f, this.phase * 0.34f);
        this.stroke.setXfermode(this.add);
        float hotA2 = (e * f4) + 0.35f;
        int i3 = 0;
        while (i3 < 6) {
            float a2 = (((i3 * 6.2831855f) / 6) - (this.phase * hotA)) + 0.42f;
            float hotA3 = hotA2;
            arc(canvas, cx, cy3, r * 0.77f, a2, 0.46f, UiKt.alpha(this.skin.getHot(), 0.28f * hotA2), r * 0.035f);
            arc(canvas, cx, cy3, r * 0.77f, a2, 0.46f, UiKt.alpha(this.skin.getHot(), RangesKt.coerceAtMost(hotA3, 1.0f)), 2.0f);
            i3++;
            hotA2 = hotA3;
            hotA = 0.22f;
        }
        int n = 28;
        float sweepFrac = (t2 / 1800.0f) % 1.0f;
        int i4 = 0;
        while (true) {
            if (i4 < n) {
                float a3 = ((i4 * 6.2831855f) / n) + (this.phase * 0.42f);
                float v = (e * 0.3f) + 0.26f;
                if (this.mode == Mode.SPEAK) {
                    v += Math.abs((float) Math.sin((t2 / 160.0f) + (i4 * 0.9f))) * f4 * beat;
                }
                if (this.mode == Mode.LISTEN) {
                    v += Math.abs((float) Math.sin((t2 / 300.0f) + (i4 * 0.4f))) * 0.45f;
                }
                arc(canvas, cx, cy3, r * 0.69f, a3, 0.17f, UiKt.alpha(this.skin.getAccent(), RangesKt.coerceAtMost(this.mode == Mode.ACT ? ((float) i4) / ((float) n) >= sweepFrac ? 0.2f : 0.95f : v, 1.0f)), r * 0.052f);
                i4++;
                n = n;
            } else {
                for (int i5 = 0; i5 < 16; i5++) {
                    arc(canvas, cx, cy3, r * 0.46f, ((i5 * 6.2831855f) / 16) - (this.phase * 0.7f), 0.3f, UiKt.alpha(this.skin.getCyan(), RangesKt.coerceAtMost((e * 0.5f) + 0.35f, 1.0f) * 0.7f), r * 0.09f);
                }
                arc(canvas, cx, cy3, r * 0.36f, 0.0f, 6.2831855f, UiKt.alpha(this.skin.getCyan(), 0.6f), 1.0f);
                if (this.mode == Mode.THINK) {
                    int i6 = 0;
                    while (i6 < 5) {
                        float a4 = ((i6 * 1.2566371f) - 1.5707964f) + (0.12f / f2);
                        float sw = 1.2566371f - 0.12f;
                        int i7 = i6;
                        arc(canvas, cx, cy3, r * 0.965f, a4, sw, UiKt.alpha(this.skin.getAccent(), 0.22f), 3.0f);
                        if (i7 < this.step) {
                            arc(canvas, cx, cy3, r * 0.965f, a4, sw, this.skin.getCyan(), 4.0f);
                        }
                        if (i7 == this.step) {
                            float f5 = (Math.abs((float) Math.sin(t2 / 260.0f)) * 0.75f) + 0.25f;
                            arc(canvas, cx, cy3, r * 0.965f, a4, sw, UiKt.alpha(this.skin.getCyan(), f5), 4.0f);
                            i = 1065353216;
                            arc(canvas, cx, cy3, r * 0.8f, a4 + (((t2 / 700.0f) % 1.0f) * sw), 0.5f, UiKt.alpha(this.skin.getCyan(), 0.95f), 3.0f);
                        } else {
                            i = 1065353216;
                        }
                        i6 = i7 + 1;
                        now = now;
                    }
                    f = 0.95f;
                } else {
                    f = 0.95f;
                }
                this.stroke.setXfermode(null);
                float core = ((0.09f * e) + 0.24f + (0.05f * beat)) * r * ((this.power * 0.65f) + 0.35f);
                this.fill.setXfermode(this.add);
                this.fill.setShader(new RadialGradient(cx, cy3, core * 1.5f, new int[]{-1, UiKt.alpha(this.skin.getCyan(), f), UiKt.alpha(this.skin.getAccent(), 0.45f), UiKt.alpha(this.skin.getAccent(), 0.0f)}, new float[]{0.0f, 0.28f, 0.62f, 1.0f}, Shader.TileMode.CLAMP));
                canvas.drawCircle(cx, cy3, core * 1.5f, this.fill);
                this.fill.setShader(null);
                this.fill.setXfermode(null);
                this.stroke.setColor(UiKt.alpha(-1, 0.75f));
                this.stroke.setStrokeWidth(1.5f);
                this.stroke.setXfermode(this.add);
                for (int i8 = 0; i8 < 8; i8++) {
                    float a5 = ((i8 * 6.2831855f) / 8) + (this.phase * 0.2f);
                    float l2 = core * (i8 % 2 == 1 ? 1.1f : 1.7f + (beat * 0.5f));
                    canvas.drawLine(cx, cy3, cx + (((float) Math.cos(a5)) * l2), cy3 + (((float) Math.sin(a5)) * l2), this.stroke);
                }
                this.stroke.setXfermode(null);
                if (dim < 0.999f) {
                    this.fill.setXfermode(this.dstIn);
                    this.fill.setColor(UiKt.alpha(ViewCompat.MEASURED_STATE_MASK, dim));
                    canvas.drawRect(0.0f, 0.0f, w, h, this.fill);
                    this.fill.setXfermode(null);
                }
                canvas.restoreToCount(layer2);
                if (isShown()) {
                    postInvalidateDelayed(33L);
                    return;
                }
                return;
            }
        }
    }

    /* JADX INFO: compiled from: ReactorView.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/jarvis/app/ui/ReactorView$Companion;", "", "<init>", "()V", "FRAME_MS", "", "TAU", "", "HALF_PI", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
