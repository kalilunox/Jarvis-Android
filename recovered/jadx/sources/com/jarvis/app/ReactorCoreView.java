package com.jarvis.app;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.mlkit.common.MlKitException;
import com.jarvis.core.model.AssistantState;
import com.jarvis.core.storage.FactPolicies;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReactorCoreView.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010$\u001a\u00020%H\u0014J\b\u0010&\u001a\u00020%H\u0014J\u0010\u0010'\u001a\u00020%2\u0006\u0010(\u001a\u00020#H\u0016J\u0010\u0010)\u001a\u00020%2\u0006\u0010*\u001a\u00020+H\u0014J\u0010\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u0010H\u0002J\u0018\u0010/\u001a\u00020\u00102\u0006\u00100\u001a\u00020\u00102\u0006\u00101\u001a\u00020\u0010H\u0002R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014R\u001a\u0010\u0018\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0012\"\u0004\b\u001a\u0010\u0014R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lcom/jarvis/app/ReactorCoreView;", "Landroid/view/View;", "Landroid/view/Choreographer$FrameCallback;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "state", "Lcom/jarvis/core/model/AssistantState;", "getState", "()Lcom/jarvis/core/model/AssistantState;", "setState", "(Lcom/jarvis/core/model/AssistantState;)V", "accent", "", "getAccent", "()I", "setAccent", "(I)V", "accent2", "getAccent2", "setAccent2", "hot", "getHot", "setHot", "paint", "Landroid/graphics/Paint;", "glow", "Landroid/graphics/BlurMaskFilter;", "glowSoft", "running", "", "t0", "", "onAttachedToWindow", "", "onDetachedFromWindow", "doFrame", "frameTimeNanos", "onDraw", "canvas", "Landroid/graphics/Canvas;", "rand", "", FactPolicies.SEED_SOURCE, "alpha", TypedValues.Custom.S_COLOR, "a", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ReactorCoreView extends View implements Choreographer.FrameCallback {
    private int accent;
    private int accent2;
    private final BlurMaskFilter glow;
    private final BlurMaskFilter glowSoft;
    private int hot;
    private final Paint paint;
    private boolean running;
    private AssistantState state;
    private final long t0;

    /* JADX INFO: compiled from: ReactorCoreView.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AssistantState.values().length];
            try {
                iArr[AssistantState.THINKING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[AssistantState.EXECUTING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[AssistantState.SPEAKING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[AssistantState.LISTENING.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ReactorCoreView(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReactorCoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        this.state = AssistantState.READY;
        this.accent = Color.parseColor("#62E9F4");
        this.accent2 = Color.parseColor("#2FB9CA");
        this.hot = Color.parseColor("#F3BB58");
        Paint $this$paint_u24lambda_u240 = new Paint(1);
        $this$paint_u24lambda_u240.setStrokeCap(Paint.Cap.ROUND);
        this.paint = $this$paint_u24lambda_u240;
        this.glow = new BlurMaskFilter(18.0f, BlurMaskFilter.Blur.NORMAL);
        this.glowSoft = new BlurMaskFilter(28.0f, BlurMaskFilter.Blur.NORMAL);
        this.t0 = SystemClock.uptimeMillis();
        setLayerType(1, null);
    }

    public /* synthetic */ ReactorCoreView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    public final AssistantState getState() {
        return this.state;
    }

    public final void setState(AssistantState assistantState) {
        Intrinsics.checkNotNullParameter(assistantState, "<set-?>");
        this.state = assistantState;
    }

    public final int getAccent() {
        return this.accent;
    }

    public final void setAccent(int i) {
        this.accent = i;
    }

    public final int getAccent2() {
        return this.accent2;
    }

    public final void setAccent2(int i) {
        this.accent2 = i;
    }

    public final int getHot() {
        return this.hot;
    }

    public final void setHot(int i) {
        this.hot = i;
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.running = true;
        Choreographer.getInstance().postFrameCallback(this);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        this.running = false;
        Choreographer.getInstance().removeFrameCallback(this);
        super.onDetachedFromWindow();
    }

    @Override // android.view.Choreographer.FrameCallback
    public void doFrame(long frameTimeNanos) {
        if (this.running) {
            invalidate();
            Choreographer.getInstance().postFrameCallback(this);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float f;
        int iAlpha;
        int iAlpha2;
        int iAlpha3;
        DashPathEffect dashPathEffect;
        Canvas canvas2 = canvas;
        Intrinsics.checkNotNullParameter(canvas2, "canvas");
        float tm = SystemClock.uptimeMillis() - this.t0;
        float t = tm * 2.4E-4f;
        float cx = getWidth() / 2.0f;
        float cy = getHeight() / 2.0f;
        float r = Math.min(getWidth(), getHeight()) * 0.4f;
        switch (WhenMappings.$EnumSwitchMapping$0[this.state.ordinal()]) {
            case 1:
            case 2:
                f = 2.2f;
                break;
            case 3:
                f = 1.5f;
                break;
            case 4:
                f = 1.15f;
                break;
            default:
                f = 1.0f;
                break;
        }
        float speed = f;
        canvas.save();
        canvas2.translate(cx, cy);
        RadialGradient fog = new RadialGradient(0.0f, 0.0f, r * 1.16f, new int[]{alpha(this.accent, 85), alpha(this.accent2, 40), 0}, new float[]{0.0f, 0.44f, 1.0f}, Shader.TileMode.CLAMP);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setShader(fog);
        this.paint.setMaskFilter(this.glowSoft);
        boolean z = false;
        canvas2.drawCircle(0.0f, 0.0f, r * 1.2f, this.paint);
        this.paint.setShader(null);
        this.paint.setMaskFilter(null);
        this.paint.setStyle(Paint.Style.STROKE);
        int ring = 0;
        while (true) {
            int i = 4;
            if (ring < 6) {
                float rr = ((ring * 0.092f) + 0.49f) * r;
                this.paint.setColor(ring % 2 == 0 ? alpha(this.accent, 128) : alpha(this.accent2, 85));
                this.paint.setStrokeWidth(ring == 5 ? 1.6f : 1.1f);
                Paint paint = this.paint;
                switch (ring) {
                    case 2:
                        dashPathEffect = new DashPathEffect(new float[]{4.0f, 8.0f}, 0.0f);
                        break;
                    case 3:
                    default:
                        dashPathEffect = null;
                        break;
                    case 4:
                        dashPathEffect = new DashPathEffect(new float[]{14.0f, 6.0f, 2.0f, 6.0f}, 0.0f);
                        break;
                }
                paint.setPathEffect(dashPathEffect);
                canvas2.drawCircle(0.0f, 0.0f, rr, this.paint);
                ring++;
            } else {
                this.paint.setPathEffect(null);
                canvas.save();
                canvas2.rotate((-t) * 40.0f * speed);
                int i2 = 0;
                while (i2 < 180) {
                    float ang = ((i2 * 3.1415927f) * 2.0f) / 180.0f;
                    boolean major = i2 % 15 == 0;
                    boolean mid = i2 % 5 == 0;
                    float inner = r * 0.925f;
                    float outer = inner - (major ? 14.0f : mid ? 8.0f : 4.0f);
                    Paint paint2 = this.paint;
                    if (major) {
                        iAlpha3 = this.accent;
                    } else {
                        iAlpha3 = mid ? alpha(this.accent, 160) : alpha(this.accent, 69);
                    }
                    paint2.setColor(iAlpha3);
                    this.paint.setStrokeWidth(major ? 1.6f : 0.8f);
                    canvas.drawLine(((float) Math.cos(ang)) * inner, ((float) Math.sin(ang)) * inner, ((float) Math.cos(ang)) * outer, ((float) Math.sin(ang)) * outer, this.paint);
                    i2++;
                    i = 4;
                    cx = cx;
                }
                int i3 = i;
                canvas.restore();
                int[] layers = {28, 18, 12, 8};
                float[] widths = new float[i3];
                // fill-array-data instruction
                widths[0] = 2.4f;
                widths[1] = 3.2f;
                widths[2] = 2.6f;
                widths[3] = 2.2f;
                int layer = 0;
                while (layer < i3) {
                    canvas.save();
                    float dir = layer % 2 == 0 ? -1.0f : 1.0f;
                    canvas2.rotate(t * dir * ((layer * 18.0f) + 40.0f) * speed);
                    float rr2 = r * (0.69f - (layer * 0.092f));
                    int n = layers[layer];
                    int i4 = 0;
                    while (i4 < n) {
                        float cy2 = cy;
                        float start = (float) Math.toDegrees((((((double) i4) * 2.0d) * 3.141592653589793d) / ((double) n)) + 0.035d);
                        float sweep = (float) Math.toDegrees((6.283185307179586d / ((double) n)) - 0.105d);
                        Paint paint3 = this.paint;
                        if (i4 % 4 == 0) {
                            iAlpha2 = this.accent;
                        } else {
                            iAlpha2 = i4 % 2 == 0 ? alpha(this.accent2, MlKitException.CODE_SCANNER_GOOGLE_PLAY_SERVICES_VERSION_TOO_OLD) : alpha(this.accent, 74);
                        }
                        paint3.setColor(iAlpha2);
                        this.paint.setStrokeWidth(widths[layer]);
                        this.paint.setMaskFilter(i4 % 4 == 0 ? this.glow : null);
                        canvas.drawArc(-rr2, -rr2, rr2, rr2, start, sweep, false, this.paint);
                        i4++;
                        layer = layer;
                        cy = cy2;
                        n = n;
                        rr2 = rr2;
                        z = false;
                    }
                    this.paint.setMaskFilter(null);
                    canvas.restore();
                    layer++;
                    canvas2 = canvas;
                    cy = cy;
                    z = false;
                    i3 = 4;
                }
                this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
                this.paint.setStrokeWidth(1.2f);
                for (int i5 = 0; i5 < 12; i5++) {
                    canvas.save();
                    canvas.rotate((float) Math.toDegrees(((((double) i5) * 3.141592653589793d) / 6.0d) + (((double) t) * 0.9d)));
                    this.paint.setColor(alpha(this.accent, 153));
                    canvas.drawLine(r * 0.3f, 0.0f, r * 0.61f, 0.0f, this.paint);
                    canvas.restore();
                }
                canvas.save();
                canvas.rotate((((-t) * 50.0f) * speed) - 90.0f);
                this.paint.setStyle(Paint.Style.STROKE);
                this.paint.setStrokeWidth(2.4f);
                this.paint.setColor(this.accent);
                this.paint.setMaskFilter(this.glow);
                Path path = new Path();
                float pr = r * 0.34f;
                int i6 = 0;
                while (i6 < 4) {
                    float an = ((i6 * 3.1415927f) * 2.0f) / 3.0f;
                    float rr3 = (i6 == 1 ? 0.96f : 1.0f) * pr;
                    float x = ((float) Math.cos(an)) * rr3;
                    float y = ((float) Math.sin(an)) * rr3;
                    if (i6 == 0) {
                        path.moveTo(x, y);
                    } else {
                        path.lineTo(x, y);
                    }
                    i6++;
                }
                path.close();
                canvas.drawPath(path, this.paint);
                this.paint.setMaskFilter(null);
                canvas.restore();
                canvas.save();
                canvas.rotate(70.0f * t * speed);
                this.paint.setColor(this.accent2);
                this.paint.setStrokeWidth(2.0f);
                this.paint.setMaskFilter(this.glow);
                canvas.drawCircle(0.0f, 0.0f, 0.245f * r, this.paint);
                this.paint.setMaskFilter(null);
                this.paint.setPathEffect(new DashPathEffect(new float[]{4.0f, 8.0f}, 0.0f));
                this.paint.setColor(alpha(this.accent, 170));
                canvas.drawCircle(0.0f, 0.0f, 0.2f * r, this.paint);
                this.paint.setPathEffect(null);
                canvas.restore();
                float pulse = (((float) Math.sin(0.003f * tm)) * 0.04f) + 1.0f;
                float coreR = 0.22f * r * pulse;
                this.paint.setStyle(Paint.Style.FILL);
                this.paint.setMaskFilter(this.glowSoft);
                this.paint.setShader(new RadialGradient(0.0f, 0.0f, coreR, new int[]{Color.parseColor("#F4FEFF"), this.accent, alpha(this.accent2, 238), 0}, new float[]{0.0f, 0.12f, 0.42f, 1.0f}, Shader.TileMode.CLAMP));
                canvas.drawCircle(0.0f, 0.0f, coreR, this.paint);
                this.paint.setShader(null);
                this.paint.setMaskFilter(null);
                this.paint.setStyle(Paint.Style.STROKE);
                this.paint.setMaskFilter(null);
                this.paint.setColor(Color.parseColor("#F4FEFF"));
                this.paint.setStrokeWidth(1.5f);
                float tm2 = t * 0.55f;
                Path hex = new Path();
                float hexR = r * 0.145f;
                int i7 = 0;
                while (i7 < 6) {
                    float ang2 = ((i7 * 3.1415927f) / 3.0f) + tm2;
                    int i8 = i7;
                    float x2 = ((float) Math.cos(ang2)) * hexR;
                    float coreR2 = coreR;
                    float pulse2 = pulse;
                    float y2 = ((float) Math.sin(ang2)) * hexR;
                    if (i8 == 0) {
                        hex.moveTo(x2, y2);
                    } else {
                        hex.lineTo(x2, y2);
                    }
                    float tick = hexR * 1.22f;
                    canvas.drawLine(((float) Math.cos(ang2)) * hexR, ((float) Math.sin(ang2)) * hexR, ((float) Math.cos(ang2)) * tick, ((float) Math.sin(ang2)) * tick, this.paint);
                    i7 = i8 + 1;
                    pulse = pulse2;
                    coreR = coreR2;
                    hex = hex;
                }
                Path hex2 = hex;
                hex2.close();
                canvas.drawPath(hex2, this.paint);
                this.paint.setStrokeWidth(1.2f);
                Path tri = new Path();
                float triR = 0.078f * r;
                int i9 = 0;
                while (i9 < 3) {
                    float ang3 = ((((i9 * 3.1415927f) * 2.0f) / 3.0f) - (1.4f * tm2)) - 1.5707964f;
                    float spin = tm2;
                    float x3 = ((float) Math.cos(ang3)) * triR;
                    Path path2 = path;
                    float tm3 = tm;
                    float y3 = ((float) Math.sin(ang3)) * triR;
                    if (i9 == 0) {
                        tri.moveTo(x3, y3);
                    } else {
                        tri.lineTo(x3, y3);
                    }
                    i9++;
                    tm = tm3;
                    tm2 = spin;
                    path = path2;
                }
                float t2 = tm;
                tri.close();
                canvas.drawPath(tri, this.paint);
                this.paint.setColor(Color.parseColor("#FFFFFF"));
                Paint $this$onDraw_u24lambda_u240 = this.paint;
                $this$onDraw_u24lambda_u240.setStyle(Paint.Style.FILL);
                Unit unit = Unit.INSTANCE;
                canvas.drawCircle(0.0f, 0.0f, 0.012f * r, $this$onDraw_u24lambda_u240);
                this.paint.setStyle(Paint.Style.STROKE);
                this.paint.setStyle(Paint.Style.FILL);
                int i10 = 0;
                while (i10 < 78) {
                    float angle = (rand(i10 + 2) * 3.1415927f * 2.0f) + (((rand(i10) * 0.5f) + 0.7f) * t * speed);
                    float rad = ((rand(i10 + 8) * 0.46f) + 0.68f) * r;
                    float sz = (rand(i10 + 70) * 2.2f) + 1.1f;
                    Paint paint4 = this.paint;
                    if (i10 % 7 == 0) {
                        iAlpha = this.hot;
                    } else {
                        iAlpha = i10 % 5 == 0 ? this.accent : alpha(this.accent2, 187);
                    }
                    paint4.setColor(iAlpha);
                    this.paint.setMaskFilter(i10 % 9 == 0 ? this.glow : null);
                    canvas.drawCircle(((float) Math.cos(angle)) * rad, ((float) Math.sin(angle)) * rad, sz, this.paint);
                    i10++;
                    tri = tri;
                }
                this.paint.setMaskFilter(null);
                int i11 = 0;
                while (i11 < 4) {
                    float an2 = ((i11 % 2 == 0 ? 2.4f : -2.8f) * t * speed) + ((i11 * 3.1415927f) / 2.0f);
                    float rad2 = (((i11 % 2) * 0.05f) + 0.75f) * r;
                    this.paint.setColor(i11 == 1 ? this.hot : this.accent);
                    this.paint.setMaskFilter(this.glow);
                    canvas.drawCircle(((float) Math.cos(an2)) * rad2, ((float) Math.sin(an2)) * rad2, 3.4f, this.paint);
                    i11++;
                    t = t;
                    t2 = t2;
                }
                this.paint.setMaskFilter(null);
                canvas.restore();
                return;
            }
        }
    }

    private final float rand(int seed) {
        double x = Math.sin((((double) seed) * 127.1d) + 311.7d) * 43758.5453d;
        return (float) (x - Math.floor(x));
    }

    private final int alpha(int color, int a) {
        return Color.argb(a, Color.red(color), Color.green(color), Color.blue(color));
    }
}
