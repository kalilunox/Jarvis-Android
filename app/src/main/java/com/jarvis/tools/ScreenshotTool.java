package com.jarvis.tools;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import com.jarvis.core.common.Tool;
import com.jarvis.core.common.model.OnlineRequirement;
import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import com.jarvis.screencontrol.ScreenController;
import java.io.IOException;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScreenshotTool.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0014"}, d2 = {"Lcom/jarvis/tools/ScreenshotTool;", "Lcom/jarvis/core/common/Tool;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "saveToGallery", "Landroid/net/Uri;", "bitmap", "Landroid/graphics/Bitmap;", "name", "", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ScreenshotTool implements Tool {
    private final Context context;
    private final ToolDefinition definition;

    public ScreenshotTool(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.definition = new ToolDefinition("screenshot", "Скриншот", "Делает снимок текущего экрана и сохраняет в Галерею", CollectionsKt.emptyList(), RiskLevel.LOW, null, OnlineRequirement.LOCAL_ONLY, null, 160, null);
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) {
        ToolResult toolResult;
        Intrinsics.checkNotNullParameter(call, "call");
        if (!ScreenController.INSTANCE.isAvailable()) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.UNAVAILABLE, "Нужна служба спец. возможностей Jarvis для скриншота.", null, 0L, 48, null);
        }
        if (Build.VERSION.SDK_INT < 30) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.UNAVAILABLE, "Скриншот через Accessibility доступен с Android 11+.", null, 0L, 48, null);
        }
        Bitmap bitmap = ScreenController.takeScreenshotBitmap$default(ScreenController.INSTANCE, 0L, 1, null);
        if (bitmap != null) {
            try {
                String name = "jarvis_" + System.currentTimeMillis() + ".png";
                try {
                    Uri uri = saveToGallery(bitmap, name);
                    bitmap.recycle();
                    if (uri == null) {
                        toolResult = new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.FAILED, "Не сохранил скриншот.", null, 0L, 48, null);
                    } else {
                        toolResult = new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, "Скриншот сохранён, сэр.", MapsKt.mapOf(TuplesKt.to("uri", uri.toString()), TuplesKt.to("name", name)), 0L, 32, null);
                    }
                    return toolResult;
                } catch (Throwable th) {
                    t = th;
                    return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.FAILED, t.getMessage(), null, 0L, 48, null);
                }
            } catch (Throwable th2) {
                t = th2;
            }
        } else {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.FAILED, "Не удалось сделать скриншот.", null, 0L, 48, null);
        }
    }

    private final Uri saveToGallery(Bitmap bitmap, String name) throws IOException {
        OutputStream outputStreamOpenOutputStream;
        ContentValues values = new ContentValues();
        values.put("_display_name", name);
        values.put("mime_type", "image/png");
        values.put("relative_path", Environment.DIRECTORY_PICTURES + "/Jarvis");
        values.put("is_pending", (Integer) 1);
        ContentResolver resolver = this.context.getContentResolver();
        Uri uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        if (uri == null || (outputStreamOpenOutputStream = resolver.openOutputStream(uri)) == null) {
            return null;
        }
        OutputStream outputStream = outputStreamOpenOutputStream;
        try {
            OutputStream out = outputStream;
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            CloseableKt.closeFinally(outputStream, null);
            values.clear();
            values.put("is_pending", (Integer) 0);
            resolver.update(uri, values, null, null);
            return uri;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(outputStream, th);
                throw th2;
            }
        }
    }
}
