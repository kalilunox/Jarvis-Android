package com.jarvis.app.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import androidx.core.graphics.PathParser;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Icons.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0007H\u0014J\u0010\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001cH\u0014R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/jarvis/app/ui/IconView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "data", "", "tint", "", "sizeDp", "<init>", "(Landroid/content/Context;Ljava/lang/String;II)V", "getTint", "()I", "setTint", "(I)V", "source", "Landroid/graphics/Path;", "scaled", "matrix", "Landroid/graphics/Matrix;", "paint", "Landroid/graphics/Paint;", "onMeasure", "", "w", "h", "onDraw", "c", "Landroid/graphics/Canvas;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class IconView extends View {
    private final Matrix matrix;
    private final Paint paint;
    private final Path scaled;
    private final int sizeDp;
    private final Path source;
    private int tint;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IconView(Context context, String data, int tint, int sizeDp) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
        this.tint = tint;
        this.sizeDp = sizeDp;
        Path pathCreatePathFromPathData = PathParser.createPathFromPathData(data);
        Intrinsics.checkNotNullExpressionValue(pathCreatePathFromPathData, "createPathFromPathData(...)");
        this.source = pathCreatePathFromPathData;
        this.scaled = new Path();
        this.matrix = new Matrix();
        Paint $this$paint_u24lambda_u240 = new Paint(1);
        $this$paint_u24lambda_u240.setStyle(Paint.Style.STROKE);
        $this$paint_u24lambda_u240.setStrokeCap(Paint.Cap.ROUND);
        $this$paint_u24lambda_u240.setStrokeJoin(Paint.Join.ROUND);
        this.paint = $this$paint_u24lambda_u240;
    }

    public /* synthetic */ IconView(Context context, String str, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, str, i, (i3 & 8) != 0 ? 22 : i2);
    }

    public final int getTint() {
        return this.tint;
    }

    public final void setTint(int i) {
        this.tint = i;
    }

    @Override // android.view.View
    protected void onMeasure(int w, int h) {
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        int iDp = UiKt.dp(context, this.sizeDp);
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        setMeasuredDimension(iDp, UiKt.dp(context2, this.sizeDp));
    }

    @Override // android.view.View
    protected void onDraw(Canvas c) {
        Intrinsics.checkNotNullParameter(c, "c");
        float k = getWidth() / 24.0f;
        this.matrix.setScale(k, k);
        this.source.transform(this.matrix, this.scaled);
        this.paint.setColor(this.tint);
        this.paint.setStrokeWidth(1.7f * k);
        c.drawPath(this.scaled, this.paint);
    }
}
