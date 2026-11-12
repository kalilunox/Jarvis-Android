package com.jarvis.app.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.SystemClock;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: MindView.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 12\u00020\u0001:\u0004./01B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J(\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020'H\u0014J\u0010\u0010+\u001a\u00020%2\u0006\u0010,\u001a\u00020-H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lcom/jarvis/app/ui/MindView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "skin", "Lcom/jarvis/app/ui/Skin;", "<init>", "(Landroid/content/Context;Lcom/jarvis/app/ui/Skin;)V", "mood", "Lcom/jarvis/app/ui/MindView$Mood;", "getMood", "()Lcom/jarvis/app/ui/MindView$Mood;", "setMood", "(Lcom/jarvis/app/ui/MindView$Mood;)V", "ribbons", "", "Lcom/jarvis/app/ui/MindView$Ribbon;", "motes", "Lcom/jarvis/app/ui/MindView$Mote;", "add", "Landroid/graphics/PorterDuffXfermode;", "line", "Landroid/graphics/Paint;", "fill", "path", "Landroid/graphics/Path;", "oval", "Landroid/graphics/RectF;", "shaders", "Landroid/graphics/Shader;", "energy", "", "speed", TypedValues.CycleType.S_WAVE_PHASE, "last", "", "onSizeChanged", "", "w", "", "h", "oldw", "oldh", "onDraw", "canvas", "Landroid/graphics/Canvas;", "Mood", "Ribbon", "Mote", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MindView extends View {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final float TAU = 6.2831855f;
    private final PorterDuffXfermode add;
    private float energy;
    private final Paint fill;
    private long last;
    private final Paint line;
    private Mood mood;
    private final List<Mote> motes;
    private final RectF oval;
    private final Path path;
    private float phase;
    private final List<Ribbon> ribbons;
    private List<? extends Shader> shaders;
    private final Skin skin;
    private float speed;

    /* JADX INFO: compiled from: MindView.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Mood.values().length];
            try {
                iArr[Mood.SPEAK.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Mood.LISTEN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Mood.THINK.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[Mood.SLEEP.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: compiled from: MindView.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lcom/jarvis/app/ui/MindView$Mood;", "", "energy", "", "speed", "<init>", "(Ljava/lang/String;IFF)V", "getEnergy", "()F", "getSpeed", "SLEEP", "LISTEN", "THINK", "SPEAK", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum Mood {
        SLEEP(0.08f, 0.16f),
        LISTEN(0.45f, 0.55f),
        THINK(0.3f, 1.9f),
        SPEAK(0.95f, 0.8f);

        private final float energy;
        private final float speed;
        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<Mood> getEntries() {
            return $ENTRIES;
        }

        Mood(float energy, float speed) {
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
    public MindView(Context context, Skin skin) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(skin, "skin");
        this.skin = skin;
        this.mood = Mood.SLEEP;
        ArrayList arrayList = new ArrayList(8);
        for (int i = 0; i < 8; i++) {
            int it = i;
            arrayList.add(new Ribbon((it / 8.0f) * 6.2831855f, ((it % 3) * 0.8f) + 2.2f, ((it % 4) * 0.14f) + 0.72f, it % 3));
        }
        this.ribbons = arrayList;
        ArrayList arrayList2 = new ArrayList(90);
        for (int i2 = 0; i2 < 90; i2++) {
            arrayList2.add(new Mote(Random.INSTANCE.nextFloat(), Random.INSTANCE.nextFloat() * 6.2831855f, Random.INSTANCE.nextFloat(), (Random.INSTANCE.nextFloat() * 0.16f) + 0.05f));
        }
        this.motes = arrayList2;
        this.add = new PorterDuffXfermode(PorterDuff.Mode.ADD);
        Paint $this$line_u24lambda_u240 = new Paint(1);
        $this$line_u24lambda_u240.setStyle(Paint.Style.STROKE);
        $this$line_u24lambda_u240.setStrokeCap(Paint.Cap.ROUND);
        $this$line_u24lambda_u240.setStrokeJoin(Paint.Join.ROUND);
        $this$line_u24lambda_u240.setXfermode(this.add);
        this.line = $this$line_u24lambda_u240;
        Paint $this$fill_u24lambda_u240 = new Paint(1);
        $this$fill_u24lambda_u240.setXfermode(this.add);
        this.fill = $this$fill_u24lambda_u240;
        this.path = new Path();
        this.oval = new RectF();
        this.shaders = CollectionsKt.emptyList();
        this.energy = 0.08f;
        this.speed = 0.16f;
    }

    public final Mood getMood() {
        return this.mood;
    }

    public final void setMood(Mood mood) {
        Intrinsics.checkNotNullParameter(mood, "<set-?>");
        this.mood = mood;
    }

    /* JADX INFO: compiled from: MindView.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/jarvis/app/ui/MindView$Ribbon;", "", TypedValues.CycleType.S_WAVE_OFFSET, "", "turns", "radius", "tone", "", "<init>", "(FFFI)V", "getOffset", "()F", "getTurns", "getRadius", "getTone", "()I", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Ribbon {
        private final float offset;
        private final float radius;
        private final int tone;
        private final float turns;

        public Ribbon(float offset, float turns, float radius, int tone) {
            this.offset = offset;
            this.turns = turns;
            this.radius = radius;
            this.tone = tone;
        }

        public final float getOffset() {
            return this.offset;
        }

        public final float getRadius() {
            return this.radius;
        }

        public final int getTone() {
            return this.tone;
        }

        public final float getTurns() {
            return this.turns;
        }
    }

    /* JADX INFO: compiled from: MindView.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\r\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\n¨\u0006\u0010"}, d2 = {"Lcom/jarvis/app/ui/MindView$Mote;", "", "u", "", "a", "r", "s", "<init>", "(FFFF)V", "getU", "()F", "setU", "(F)V", "getA", "getR", "getS", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Mote {
        private final float a;
        private final float r;
        private final float s;
        private float u;

        public Mote(float u, float a, float r, float s) {
            this.u = u;
            this.a = a;
            this.r = r;
            this.s = s;
        }

        public final float getA() {
            return this.a;
        }

        public final float getR() {
            return this.r;
        }

        public final float getS() {
            return this.s;
        }

        public final float getU() {
            return this.u;
        }

        public final void setU(float f) {
            this.u = f;
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        float f = 0.9f;
        float base = h * 0.9f;
        float top = h * 0.02f;
        Iterable $this$map$iv = CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(this.skin.getAccent()), Integer.valueOf(this.skin.getViolet()), Integer.valueOf(this.skin.getHot())});
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        Collection destination$iv$iv2 = destination$iv$iv;
        for (Object item$iv$iv : $this$map$iv) {
            int tone = ((Number) item$iv$iv).intValue();
            Collection destination$iv$iv3 = destination$iv$iv2;
            destination$iv$iv3.add(new LinearGradient(0.0f, base, 0.0f, top, new int[]{UiKt.alpha(this.skin.getCyan(), 0.15f), UiKt.alpha(tone, f), UiKt.alpha(this.skin.getViolet(), 0.8f), UiKt.alpha(this.skin.getAccent(), 0.1f)}, new float[]{0.0f, 0.35f, 0.75f, 1.0f}, Shader.TileMode.CLAMP));
            destination$iv$iv2 = destination$iv$iv3;
            f = 0.9f;
        }
        this.shaders = (List) destination$iv$iv2;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float fAbs;
        float f;
        String str;
        float dt;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        long now = SystemClock.uptimeMillis();
        float dt2 = this.last == 0 ? 0.0f : RangesKt.coerceAtMost((now - this.last) / 1000.0f, 0.05f);
        this.last = now;
        float t = now;
        this.energy += (this.mood.getEnergy() - this.energy) * 0.05f;
        this.speed += (this.mood.getSpeed() - this.speed) * 0.05f;
        this.phase += this.speed * dt2;
        switch (WhenMappings.$EnumSwitchMapping$0[this.mood.ordinal()]) {
            case 1:
                fAbs = Math.abs(((float) Math.sin(t / 140.0f)) * ((float) Math.sin(t / 430.0f)));
                break;
            case 2:
                fAbs = Math.abs((float) Math.sin(t / 320.0f)) * 0.5f;
                break;
            case 3:
                fAbs = 0.15f;
                break;
            case 4:
                fAbs = 0.0f;
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        float beat = fAbs;
        float w = getWidth();
        float h = getHeight();
        float cx = w * 0.5f;
        float base = h * 0.9f;
        float top = h * 0.02f;
        float span = base - top;
        switch (WhenMappings.$EnumSwitchMapping$0[this.mood.ordinal()]) {
            case 3:
                f = 0.78f;
                break;
            case 4:
                f = 0.62f;
                break;
            default:
                f = 1.0f;
                break;
        }
        float shrink = f;
        float f2 = 1;
        float r = Math.min(0.34f * w, h * 0.3f) * shrink * (f2 + (0.12f * beat));
        float bw = (this.energy * 34.0f) + 12.0f + (28.0f * beat);
        this.fill.setShader(new LinearGradient(cx - bw, 0.0f, cx + bw, 0.0f, new int[]{UiKt.alpha(this.skin.getAccent(), 0.0f), UiKt.alpha(this.skin.getCyan(), (this.energy * 0.3f) + 0.28f), UiKt.alpha(this.skin.getAccent(), 0.0f)}, (float[]) null, Shader.TileMode.CLAMP));
        float f3 = 1.0f;
        canvas.drawRect(cx - bw, top, cx + bw, base, this.fill);
        this.fill.setShader(null);
        this.fill.setColor(UiKt.alpha(-1, 0.75f));
        canvas.drawRect(cx - 1.5f, top, cx + 1.5f, base, this.fill);
        float f4 = 3.1415927f;
        String str2 = "getContext(...)";
        if (this.shaders.isEmpty()) {
            str = "getContext(...)";
            dt = dt2;
        } else {
            Iterable $this$forEachIndexed$iv = this.ribbons;
            int $i$f$forEachIndexed = 0;
            int index$iv = 0;
            for (Object item$iv : $this$forEachIndexed$iv) {
                int index$iv2 = index$iv + 1;
                if (index$iv < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Ribbon rb = (Ribbon) item$iv;
                this.path.reset();
                int k = 0;
                while (k < 65) {
                    float u = k / 64.0f;
                    float dt3 = dt2;
                    String str3 = str2;
                    float env = (float) Math.pow(RangesKt.coerceAtLeast((float) Math.sin(RangesKt.coerceAtMost(u * 1.03f, f3) * f4), 0.0f), 0.72f);
                    int i = index$iv;
                    float wob = (((float) Math.sin((9 * u) + (this.phase * 2) + i)) * 0.22f) + f2;
                    Iterable $this$forEachIndexed$iv2 = $this$forEachIndexed$iv;
                    float ang = (rb.getTurns() * u * 6.2831855f) + (this.phase * (i % 2 == 1 ? 1.5f : -1.5f)) + rb.getOffset();
                    float rad = rb.getRadius() * r * env * wob;
                    int $i$f$forEachIndexed2 = $i$f$forEachIndexed;
                    float x = cx + (((float) Math.cos(ang)) * rad);
                    float y = (base - (u * span)) + (((float) Math.sin(ang)) * rad * 0.24f);
                    Path path = this.path;
                    if (k == 0) {
                        path.moveTo(x, y);
                    } else {
                        path.lineTo(x, y);
                    }
                    k++;
                    $i$f$forEachIndexed = $i$f$forEachIndexed2;
                    dt2 = dt3;
                    str2 = str3;
                    $this$forEachIndexed$iv = $this$forEachIndexed$iv2;
                    f4 = 3.1415927f;
                    f3 = 1.0f;
                }
                Iterable $this$forEachIndexed$iv3 = $this$forEachIndexed$iv;
                String str4 = str2;
                this.line.setShader(this.shaders.get(rb.getTone()));
                Paint paint = this.line;
                Context context = getContext();
                Intrinsics.checkNotNullExpressionValue(context, str4);
                paint.setStrokeWidth(UiKt.dp(context, (this.energy * 3.0f) + 4.0f));
                this.line.setAlpha(26);
                canvas.drawPath(this.path, this.line);
                Paint paint2 = this.line;
                Context context2 = getContext();
                Intrinsics.checkNotNullExpressionValue(context2, str4);
                paint2.setStrokeWidth(UiKt.dp(context2, (this.energy * 0.7f) + 1.0f));
                this.line.setAlpha(216);
                canvas.drawPath(this.path, this.line);
                str2 = str4;
                index$iv = index$iv2;
                dt2 = dt2;
                $this$forEachIndexed$iv = $this$forEachIndexed$iv3;
                f4 = 3.1415927f;
                f3 = 1.0f;
            }
            str = str2;
            dt = dt2;
        }
        this.line.setShader(null);
        Iterable $this$forEach$iv = this.motes;
        int $i$f$forEach = 0;
        Iterator it = $this$forEach$iv.iterator();
        while (it.hasNext()) {
            Object element$iv = it.next();
            Mote m = (Mote) element$iv;
            Iterable $this$forEach$iv2 = $this$forEach$iv;
            m.setU(m.getU() + (m.getS() * dt * (this.speed + 0.6f)));
            if (m.getU() > 1.0f) {
                m.setU(0.0f);
            }
            float t2 = t;
            float env2 = (float) Math.sin(m.getU() * 3.1415927f);
            float a = m.getA() + (m.getU() * 12) + this.phase;
            float rad2 = 0.95f * r * env2 * m.getR();
            int $i$f$forEach2 = $i$f$forEach;
            this.fill.setColor(UiKt.alpha(m.getR() > 0.6f ? this.skin.getHot() : this.skin.getCyan(), (m.getR() * 0.6f) + 0.35f));
            float fCos = cx + (((float) Math.cos(a)) * rad2);
            Iterator it2 = it;
            float u2 = (base - (m.getU() * span)) + (((float) Math.sin(a)) * rad2 * 0.24f);
            Context context3 = getContext();
            Intrinsics.checkNotNullExpressionValue(context3, str);
            canvas.drawCircle(fCos, u2, UiKt.dp(context3, (m.getR() * 1.4f) + 0.8f), this.fill);
            it = it2;
            $this$forEach$iv = $this$forEach$iv2;
            $i$f$forEach = $i$f$forEach2;
            t = t2;
        }
        float t3 = t;
        for (int i2 = 0; i2 < 4; i2++) {
            float p = ((t3 / 2600.0f) + (i2 / 4.0f)) % 1.0f;
            this.line.setColor(UiKt.alpha(this.skin.getCyan(), (f2 - p) * 0.6f));
            this.line.setStrokeWidth(2.0f);
            float rx = ((1.1f * p) + 0.2f) * r * ((beat * 0.2f) + f2);
            float ry = ((0.2f * p) + 0.05f) * r;
            this.oval.set(cx - rx, base - ry, cx + rx, base + ry);
            canvas.drawOval(this.oval, this.line);
        }
        this.fill.setShader(new RadialGradient(cx, base, r * 0.5f, new int[]{UiKt.alpha(-1, 0.9f), UiKt.alpha(this.skin.getCyan(), 0.5f), UiKt.alpha(this.skin.getAccent(), 0.0f)}, new float[]{0.0f, 0.3f, 1.0f}, Shader.TileMode.CLAMP));
        this.oval.set(cx - (r * 0.5f), base - (r * 0.13f), cx + (r * 0.5f), base + (0.13f * r));
        canvas.drawOval(this.oval, this.fill);
        this.fill.setShader(null);
        if (isShown()) {
            postInvalidateDelayed(33L);
        }
    }

    /* JADX INFO: compiled from: MindView.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/jarvis/app/ui/MindView$Companion;", "", "<init>", "()V", "TAU", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
