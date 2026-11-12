package com.jarvis.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SurfaceTexture;
import android.graphics.Typeface;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.jarvis.app.proactive.PhoneStateReader;
import com.jarvis.audio.AudioCapture;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HudViewfinderView.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u001aB\u001d\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u0016\u001a\u00020\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0017H\u0002J\b\u0010\u0019\u001a\u00020\u0017H\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/jarvis/app/HudViewfinderView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "texture", "Landroid/view/TextureView;", "overlay", "Lcom/jarvis/app/HudViewfinderView$ScanOverlay;", "flip", "Landroid/widget/TextView;", "front", "", "camera", "Landroid/hardware/camera2/CameraDevice;", PhoneStateReader.FAKE_SESSION, "Landroid/hardware/camera2/CameraCaptureSession;", "handler", "Landroid/os/Handler;", "onDetachedFromWindow", "", "openCamera", "closeCamera", "ScanOverlay", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class HudViewfinderView extends FrameLayout {
    private CameraDevice camera;
    private final TextView flip;
    private boolean front;
    private final Handler handler;
    private final ScanOverlay overlay;
    private CameraCaptureSession session;
    private final TextureView texture;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public HudViewfinderView(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HudViewfinderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        this.texture = new TextureView(context);
        this.overlay = new ScanOverlay(context);
        this.flip = new TextView(context);
        this.handler = new Handler(Looper.getMainLooper());
        addView(this.texture, new FrameLayout.LayoutParams(-1, -1));
        addView(this.overlay, new FrameLayout.LayoutParams(-1, -1));
        this.flip.setText("LENS");
        this.flip.setTextColor(Color.parseColor("#62E9F4"));
        this.flip.setTextSize(8.0f);
        this.flip.setTypeface(Typeface.MONOSPACE);
        this.flip.setPadding(8, 4, 8, 4);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(-2, -2, 8388693);
        addView(this.flip, lp);
        this.flip.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.HudViewfinderView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws CameraAccessException {
                HudViewfinderView._init_$lambda$0(this.f$0, view);
            }
        });
        this.texture.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: com.jarvis.app.HudViewfinderView.2
            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) throws CameraAccessException {
                Intrinsics.checkNotNullParameter(surface, "surface");
                HudViewfinderView.this.openCamera();
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
                Intrinsics.checkNotNullParameter(surface, "surface");
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                Intrinsics.checkNotNullParameter(surface, "surface");
                HudViewfinderView.this.closeCamera();
                return true;
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
                Intrinsics.checkNotNullParameter(surface, "surface");
            }
        });
    }

    public /* synthetic */ HudViewfinderView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(HudViewfinderView this$0, View it) throws CameraAccessException {
        this$0.front = !this$0.front;
        this$0.flip.setText(this$0.front ? "FRONT" : "REAR");
        this$0.openCamera();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        closeCamera();
        super.onDetachedFromWindow();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openCamera() throws CameraAccessException {
        Object element$iv;
        final SurfaceTexture textureSurface = this.texture.getSurfaceTexture();
        if (textureSurface == null) {
            return;
        }
        closeCamera();
        Object systemService = getContext().getSystemService("camera");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.hardware.camera2.CameraManager");
        CameraManager manager = (CameraManager) systemService;
        Object[] cameraIdList = manager.getCameraIdList();
        Intrinsics.checkNotNullExpressionValue(cameraIdList, "getCameraIdList(...)");
        Object[] $this$firstOrNull$iv = cameraIdList;
        int length = $this$firstOrNull$iv.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                element$iv = null;
                break;
            }
            element$iv = $this$firstOrNull$iv[i];
            String cam = (String) element$iv;
            Integer facing = (Integer) manager.getCameraCharacteristics(cam).get(CameraCharacteristics.LENS_FACING);
            boolean z = true;
            if (this.front) {
                if (facing == null || facing.intValue() != 0) {
                    z = false;
                }
            } else if (facing == null || facing.intValue() != 1) {
                z = false;
            }
            if (z) {
                break;
            } else {
                i++;
            }
        }
        String str = (String) element$iv;
        if (str == null) {
            String[] cameraIdList2 = manager.getCameraIdList();
            Intrinsics.checkNotNullExpressionValue(cameraIdList2, "getCameraIdList(...)");
            str = (String) ArraysKt.firstOrNull(cameraIdList2);
            if (str == null) {
                return;
            }
        }
        String id = str;
        try {
            Result.Companion companion = Result.INSTANCE;
            final HudViewfinderView $this$openCamera_u24lambda_u241 = this;
            manager.openCamera(id, new CameraDevice.StateCallback() { // from class: com.jarvis.app.HudViewfinderView$openCamera$1$1
                @Override // android.hardware.camera2.CameraDevice.StateCallback
                public void onOpened(final CameraDevice device) throws CameraAccessException {
                    Intrinsics.checkNotNullParameter(device, "device");
                    this.$this_runCatching.camera = device;
                    final Surface surface = new Surface(textureSurface);
                    List<Surface> listListOf = CollectionsKt.listOf(surface);
                    final HudViewfinderView hudViewfinderView = this.$this_runCatching;
                    device.createCaptureSession(listListOf, new CameraCaptureSession.StateCallback() { // from class: com.jarvis.app.HudViewfinderView$openCamera$1$1$onOpened$1
                        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
                        public void onConfigured(CameraCaptureSession capture) throws CameraAccessException {
                            Intrinsics.checkNotNullParameter(capture, "capture");
                            hudViewfinderView.session = capture;
                            CaptureRequest.Builder request = device.createCaptureRequest(1);
                            request.addTarget(surface);
                            Intrinsics.checkNotNullExpressionValue(request, "apply(...)");
                            capture.setRepeatingRequest(request.build(), null, hudViewfinderView.handler);
                        }

                        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
                        public void onConfigureFailed(CameraCaptureSession capture) {
                            Intrinsics.checkNotNullParameter(capture, "capture");
                        }
                    }, this.$this_runCatching.handler);
                }

                @Override // android.hardware.camera2.CameraDevice.StateCallback
                public void onDisconnected(CameraDevice device) {
                    Intrinsics.checkNotNullParameter(device, "device");
                    device.close();
                }

                @Override // android.hardware.camera2.CameraDevice.StateCallback
                public void onError(CameraDevice device, int error) {
                    Intrinsics.checkNotNullParameter(device, "device");
                    device.close();
                }
            }, $this$openCamera_u24lambda_u241.handler);
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void closeCamera() {
        Unit unit;
        Unit unit2;
        try {
            Result.Companion companion = Result.INSTANCE;
            HudViewfinderView $this$closeCamera_u24lambda_u240 = this;
            CameraCaptureSession cameraCaptureSession = $this$closeCamera_u24lambda_u240.session;
            if (cameraCaptureSession != null) {
                cameraCaptureSession.close();
                unit2 = Unit.INSTANCE;
            } else {
                unit2 = null;
            }
            Result.m392constructorimpl(unit2);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        try {
            Result.Companion companion3 = Result.INSTANCE;
            HudViewfinderView $this$closeCamera_u24lambda_u241 = this;
            CameraDevice cameraDevice = $this$closeCamera_u24lambda_u241.camera;
            if (cameraDevice != null) {
                cameraDevice.close();
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            Result.m392constructorimpl(unit);
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th2));
        }
        this.session = null;
        this.camera = null;
    }

    /* JADX INFO: compiled from: HudViewfinderView.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/jarvis/app/HudViewfinderView$ScanOverlay;", "Landroid/view/View;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "paint", "Landroid/graphics/Paint;", "t0", "", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class ScanOverlay extends View {
        private final Paint paint;
        private final long t0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ScanOverlay(Context context) {
            super(context);
            Intrinsics.checkNotNullParameter(context, "context");
            this.paint = new Paint(1);
            this.t0 = SystemClock.uptimeMillis();
        }

        @Override // android.view.View
        protected void onDraw(Canvas canvas) {
            Intrinsics.checkNotNullParameter(canvas, "canvas");
            float t = ((SystemClock.uptimeMillis() - this.t0) % ((long) AudioCapture.CHUNK_SAMPLES)) / 1600.0f;
            this.paint.setStyle(Paint.Style.STROKE);
            this.paint.setStrokeWidth(2.0f);
            this.paint.setColor(Color.parseColor("#3347EAFF"));
            float step = getWidth() / 4.0f;
            for (float x = step; x < getWidth(); x += step) {
                canvas.drawLine(x, 0.0f, x, getHeight(), this.paint);
            }
            float y = getHeight() / 5.0f;
            while (y < getHeight()) {
                canvas.drawLine(0.0f, y, getWidth(), y, this.paint);
                y += getHeight() / 5.0f;
            }
            this.paint.setColor(Color.parseColor("#62E9F4"));
            this.paint.setStrokeWidth(3.0f);
            float a = Math.min(getWidth(), getHeight()) * 0.16f;
            canvas.drawLine(0.0f, 0.0f, a, 0.0f, this.paint);
            canvas.drawLine(0.0f, 0.0f, 0.0f, a, this.paint);
            canvas.drawLine(getWidth(), 0.0f, getWidth() - a, 0.0f, this.paint);
            canvas.drawLine(getWidth(), 0.0f, getWidth(), a, this.paint);
            canvas.drawLine(0.0f, getHeight(), a, getHeight(), this.paint);
            canvas.drawLine(0.0f, getHeight(), 0.0f, getHeight() - a, this.paint);
            canvas.drawLine(getWidth(), getHeight(), getWidth() - a, getHeight(), this.paint);
            canvas.drawLine(getWidth(), getHeight(), getWidth(), getHeight() - a, this.paint);
            this.paint.setAlpha(140);
            canvas.drawLine(0.0f, getHeight() * t, getWidth(), getHeight() * t, this.paint);
            this.paint.setAlpha(255);
            this.paint.setStyle(Paint.Style.FILL);
            this.paint.setTextSize(18.0f);
            canvas.drawText("VISION FEED", 8.0f, getHeight() - 22.0f, this.paint);
            postInvalidateOnAnimation();
        }
    }
}
