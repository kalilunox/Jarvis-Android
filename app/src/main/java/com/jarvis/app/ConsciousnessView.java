package com.jarvis.app;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import androidx.core.app.NotificationCompat;
import com.jarvis.core.model.AssistantState;
import com.jarvis.core.storage.FactPolicies;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: ConsciousnessView.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u00100\u001a\u00020\u0017H\u0014J\b\u00101\u001a\u00020\u0017H\u0014J\u0010\u00102\u001a\u00020\u00172\u0006\u00103\u001a\u00020'H\u0016J\u0010\u00104\u001a\u00020%2\u0006\u00105\u001a\u000206H\u0016J\u0010\u00107\u001a\u00020\u00172\u0006\u00108\u001a\u000209H\u0014J\u0010\u0010:\u001a\u00020\u00102\u0006\u0010;\u001a\u00020\u0010H\u0002R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\"\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"Lcom/jarvis/app/ConsciousnessView;", "Landroid/view/View;", "Landroid/view/Choreographer$FrameCallback;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "state", "Lcom/jarvis/core/model/AssistantState;", "getState", "()Lcom/jarvis/core/model/AssistantState;", "setState", "(Lcom/jarvis/core/model/AssistantState;)V", "level", "", "getLevel", "()F", "setLevel", "(F)V", "onTap", "Lkotlin/Function0;", "", "getOnTap", "()Lkotlin/jvm/functions/Function0;", "setOnTap", "(Lkotlin/jvm/functions/Function0;)V", "paint", "Landroid/graphics/Paint;", "add", "Landroid/graphics/PorterDuffXfermode;", "glow", "Landroid/graphics/BlurMaskFilter;", "ribbon", "Landroid/graphics/Path;", "running", "", "t0", "", "count", "", "yaw", "pitch", "waveAmp", "lastX", "lastY", "dragged", "onAttachedToWindow", "onDetachedFromWindow", "doFrame", "frameTimeNanos", "onTouchEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "onDraw", "canvas", "Landroid/graphics/Canvas;", "rand", FactPolicies.SEED_SOURCE, "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ConsciousnessView extends View implements Choreographer.FrameCallback {
    private final PorterDuffXfermode add;
    private final int count;
    private boolean dragged;
    private final BlurMaskFilter glow;
    private float lastX;
    private float lastY;
    private float level;
    private Function0<Unit> onTap;
    private final Paint paint;
    private float pitch;
    private final Path ribbon;
    private boolean running;
    private AssistantState state;
    private final long t0;
    private float waveAmp;
    private float yaw;

    /* JADX INFO: compiled from: ConsciousnessView.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AssistantState.values().length];
            try {
                iArr[AssistantState.SPEAKING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[AssistantState.THINKING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[AssistantState.EXECUTING.ordinal()] = 3;
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
    public ConsciousnessView(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConsciousnessView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        this.state = AssistantState.READY;
        this.level = 0.2f;
        this.paint = new Paint(1);
        this.add = new PorterDuffXfermode(PorterDuff.Mode.ADD);
        this.glow = new BlurMaskFilter(16.0f, BlurMaskFilter.Blur.NORMAL);
        this.ribbon = new Path();
        this.t0 = SystemClock.uptimeMillis();
        this.count = 1100;
        setLayerType(1, null);
    }

    public /* synthetic */ ConsciousnessView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    public final AssistantState getState() {
        return this.state;
    }

    public final void setState(AssistantState assistantState) {
        Intrinsics.checkNotNullParameter(assistantState, "<set-?>");
        this.state = assistantState;
    }

    public final float getLevel() {
        return this.level;
    }

    public final void setLevel(float f) {
        this.level = f;
    }

    public final Function0<Unit> getOnTap() {
        return this.onTap;
    }

    public final void setOnTap(Function0<Unit> function0) {
        this.onTap = function0;
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
            this.waveAmp *= 0.94f;
            invalidate();
            Choreographer.getInstance().postFrameCallback(this);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        Function0<Unit> function0;
        Intrinsics.checkNotNullParameter(event, "event");
        switch (event.getActionMasked()) {
            case 0:
                this.lastX = event.getX();
                this.lastY = event.getY();
                this.dragged = false;
                ViewParent parent = getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
                return true;
            case 1:
            case 3:
                if (!this.dragged && event.getActionMasked() == 1 && (function0 = this.onTap) != null) {
                    function0.invoke();
                }
                return true;
            case 2:
                float dx = event.getX() - this.lastX;
                float dy = event.getY() - this.lastY;
                if (Math.abs(dx) + Math.abs(dy) > 6.0f) {
                    this.dragged = true;
                }
                this.yaw += 0.012f * dx;
                this.pitch = RangesKt.coerceIn(this.pitch + (0.008f * dy), -0.7f, 0.7f);
                this.waveAmp = 1.0f;
                this.lastX = event.getX();
                this.lastY = event.getY();
                return true;
            default:
                return true;
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float f;
        float f2;
        float f3;
        int iArgb;
        int i;
        Canvas canvas2 = canvas;
        Intrinsics.checkNotNullParameter(canvas2, "canvas");
        float t = (SystemClock.uptimeMillis() - this.t0) / 1000.0f;
        float cx = getWidth() / 2.0f;
        float cy = getHeight() * 0.44f;
        switch (WhenMappings.$EnumSwitchMapping$0[this.state.ordinal()]) {
            case 1:
                f = 1.12f;
                break;
            case 2:
            case 3:
                f = 1.25f;
                break;
            case 4:
                f = this.level + 0.95f;
                break;
            default:
                f = 0.74f;
                break;
        }
        float energy = f;
        switch (WhenMappings.$EnumSwitchMapping$0[this.state.ordinal()]) {
            case 1:
                f2 = 6.0f;
                break;
            case 4:
                f2 = 1.3f;
                break;
            default:
                f2 = 0.55f;
                break;
        }
        float beatHz = f2;
        float beat = ((((float) Math.sin(t * beatHz)) * 0.045f) + 1.0f) * energy;
        float spin = (this.state == AssistantState.THINKING || this.state == AssistantState.EXECUTING) ? 0.65f : 0.19f;
        switch (WhenMappings.$EnumSwitchMapping$0[this.state.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                f3 = 1.15f;
                break;
            default:
                f3 = 1.0f;
                break;
        }
        float session = f3;
        float bright = session > 1.0f ? 1.18f : 1.0f;
        float radius = getWidth() * 0.48f * session;
        int cyan = Color.parseColor("#47EAFF");
        int indigo = Color.parseColor("#7C6BD6");
        int gold = Color.parseColor("#FFD28A");
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setShader(new RadialGradient(cx, cy, radius * 2.1f, new int[]{Color.argb(50, 32, 160, 220), Color.argb(28, 20, 80, 140), 0}, new float[]{0.0f, 0.46f, 1.0f}, Shader.TileMode.CLAMP));
        int cyan2 = cyan;
        float f4 = 1.0f;
        canvas.drawRect(0.0f, 0.0f, getWidth(), getHeight(), this.paint);
        this.paint.setShader(null);
        int layer = canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), null);
        this.paint.setXfermode(this.add);
        int i2 = 0;
        int i3 = this.count;
        while (i2 < i3) {
            float u = rand((i2 * 1.31f) + 4.0f);
            float v = rand((i2 * 2.91f) + 17.0f);
            int cyan3 = cyan2;
            float phi = (float) Math.acos(RangesKt.coerceIn((u * 2.0f) - f4, -1.0f, f4));
            float theta = (3.1415927f * v * 2.0f) + (t * spin) + this.yaw + ((i2 % 8) * 0.012f);
            float deform = (((float) Math.sin((5.0f * phi) + (1.2f * t))) * 0.1f * ((float) Math.cos((3.0f * theta) - (t * 0.7f)))) + 1.0f;
            int layer2 = layer;
            float shell = ((((float) Math.pow(rand(i2 + 44.0f), 0.35f)) * 0.06f) + 0.94f) * radius * deform * ((0.04f * beat) + 0.96f);
            float x = ((float) Math.sin(phi)) * ((float) Math.cos(theta)) * shell;
            float y = ((float) Math.cos(this.pitch + phi)) * shell;
            float z = ((float) Math.sin(phi)) * ((float) Math.sin(theta)) * shell;
            float perspective = 1.0f / (1.15f - ((z / radius) * 0.35f));
            float x2 = x * perspective;
            float y2 = y * perspective;
            float near = ((z / radius) + 1.0f) * 0.5f;
            int i4 = i3;
            float flash = this.waveAmp * ((((float) Math.sin((7.0f * phi) - (9.0f * t))) * 0.45f) + 0.55f);
            float sz = ((rand(i2 + 87.0f) * 2.4f) + 0.9f) * (near + 0.55f) * ((RangesKt.coerceAtLeast(flash, 0.0f) * 0.7f) + 1.0f);
            Paint paint = this.paint;
            if (i2 % 17 == 0) {
                i = gold;
            } else {
                i = i2 % 5 == 0 ? indigo : cyan3;
            }
            paint.setColor(i);
            this.paint.setAlpha(RangesKt.coerceIn((int) (((80 + (160 * near)) * bright) + (RangesKt.coerceAtLeast(flash, 0.0f) * 90.0f)), 50, 255));
            canvas.drawCircle(cx + x2, cy + y2, sz, this.paint);
            i2++;
            canvas2 = canvas;
            cyan2 = cyan3;
            layer = layer2;
            i3 = i4;
            f4 = 1.0f;
        }
        float f5 = 3.1415927f;
        int layer3 = layer;
        Canvas canvas3 = canvas2;
        int cyan4 = cyan2;
        float f6 = 0.9f;
        this.paint.setAlpha(255);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(3.4f);
        this.paint.setMaskFilter(this.glow);
        int k = 0;
        while (k < 6) {
            this.ribbon.reset();
            int s = 0;
            while (s < 91) {
                float ang = (s / 90.0f) * f5 * 2.0f;
                float mod = (((float) Math.sin(((k + 3.0f) * ang) + ((this.state == AssistantState.THINKING ? 3.0f : f6) * t) + k)) * 0.14f) + 1.0f;
                float rr = ((k * 0.018f) + 0.88f) * radius * mod;
                float x3 = (((float) Math.cos((this.yaw * 0.35f) + ang + ((k % 2 == 0 ? 0.35f : -0.28f) * t))) * rr) + cx;
                float y3 = (((float) Math.sin(this.pitch + ang + (k * 0.4f))) * rr * ((k * 0.01f) + 0.92f)) + cy;
                Path path = this.ribbon;
                if (s == 0) {
                    path.moveTo(x3, y3);
                } else {
                    path.lineTo(x3, y3);
                }
                s++;
                f5 = 3.1415927f;
                f6 = 0.9f;
            }
            Paint paint2 = this.paint;
            switch (k % 3) {
                case 1:
                    iArgb = Color.argb(190, 160, 120, 255);
                    break;
                case 2:
                    iArgb = Color.argb(170, 255, 210, 138);
                    break;
                default:
                    iArgb = Color.argb(210, 71, 234, 255);
                    break;
            }
            paint2.setColor(iArgb);
            canvas3.drawPath(this.ribbon, this.paint);
            k++;
            f5 = 3.1415927f;
            f6 = 0.9f;
        }
        this.paint.setMaskFilter(null);
        this.paint.setXfermode(null);
        canvas3.restoreToCount(layer3);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setShader(new RadialGradient(cx, cy, radius * 0.42f, new int[]{Color.parseColor("#F4FEFF"), cyan4, Color.argb(0, 71, 234, 255)}, new float[]{0.0f, 0.28f, 1.0f}, Shader.TileMode.CLAMP));
        this.paint.setMaskFilter(this.glow);
        canvas3.drawCircle(cx, cy, 0.28f * radius * beat, this.paint);
        this.paint.setShader(null);
        this.paint.setMaskFilter(null);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setColor(Color.argb(160, 236, 255, 255));
        this.paint.setStrokeWidth(1.4f);
        float arm = radius * 0.08f;
        float f7 = 2;
        canvas.drawLine(cx - (arm * f7), cy, cx - arm, cy, this.paint);
        canvas.drawLine(cx + arm, cy, cx + (arm * f7), cy, this.paint);
        canvas.drawLine(cx, cy - (arm * f7), cx, cy - arm, this.paint);
        canvas.drawLine(cx, cy + arm, cx, cy + (f7 * arm), this.paint);
    }

    private final float rand(float seed) {
        float x = ((float) Math.sin((127.1f * seed) + 311.7f)) * 43758.547f;
        return x - ((float) Math.floor(x));
    }
}
