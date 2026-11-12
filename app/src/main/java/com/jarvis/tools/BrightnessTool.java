package com.jarvis.tools;

import android.content.Context;
import android.provider.Settings;
import com.jarvis.core.common.Tool;
import com.jarvis.core.common.model.OnlineRequirement;
import com.jarvis.core.common.model.ParameterType;
import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.common.model.ToolParameter;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: BrightnessTool.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Lcom/jarvis/tools/BrightnessTool;", "Lcom/jarvis/core/common/Tool;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BrightnessTool implements Tool {
    private final Context context;
    private final ToolDefinition definition;

    public BrightnessTool(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.definition = new ToolDefinition("brightness", "Яркость экрана", "Изменяет яркость экрана устройства", CollectionsKt.listOf((Object[]) new ToolParameter[]{new ToolParameter("direction", "up — ярче, down — тусклее, max — максимум, set — заданный процент", ParameterType.ENUM, true, CollectionsKt.listOf((Object[]) new String[]{"up", "down", "max", "set"})), new ToolParameter("percent", "целевая яркость 0-100", ParameterType.INTEGER, false, null, 16, null)}), RiskLevel.SAFE, null, OnlineRequirement.LOCAL_ONLY, null, 160, null);
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    /* JADX WARN: Code duplicated, block: B:38:0x00cc A[Catch: all -> 0x0165, TryCatch #0 {all -> 0x0165, blocks: (B:3:0x000c, B:5:0x0026, B:6:0x0030, B:8:0x0035, B:11:0x003b, B:12:0x003f, B:14:0x0044, B:17:0x004e, B:36:0x00c3, B:38:0x00cc, B:40:0x00ea, B:18:0x0058, B:21:0x0062, B:23:0x0070, B:25:0x0075, B:27:0x0093, B:28:0x00a7, B:32:0x00b3, B:35:0x00bd, B:41:0x0134), top: B:49:0x000c }] */
    /* JADX WARN: Code duplicated, block: B:40:0x00ea A[Catch: all -> 0x0165, TryCatch #0 {all -> 0x0165, blocks: (B:3:0x000c, B:5:0x0026, B:6:0x0030, B:8:0x0035, B:11:0x003b, B:12:0x003f, B:14:0x0044, B:17:0x004e, B:36:0x00c3, B:38:0x00cc, B:40:0x00ea, B:18:0x0058, B:21:0x0062, B:23:0x0070, B:25:0x0075, B:27:0x0093, B:28:0x00a7, B:32:0x00b3, B:35:0x00bd, B:41:0x0134), top: B:49:0x000c }] */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) {
        int target;
        Intrinsics.checkNotNullParameter(call, "call");
        try {
            int current = Settings.System.getInt(this.context.getContentResolver(), "screen_brightness", 128);
            Object obj = call.getArguments().get("direction");
            if (obj == null) {
                obj = call.getArguments().get("action");
            }
            String direction = obj instanceof String ? (String) obj : null;
            if (direction != null) {
                switch (direction.hashCode()) {
                    case 3739:
                        if (direction.equals("up")) {
                            target = Math.min(current + 30, 255);
                            if (!Settings.System.canWrite(this.context)) {
                                return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.DENIED, "Нет права менять яркость. Выдайте разрешение через настройки или adb: pm grant com.jarvis.assistant android.permission.WRITE_SETTINGS", null, 0L, 48, null);
                            }
                            Settings.System.putInt(this.context.getContentResolver(), "screen_brightness_mode", 0);
                            Settings.System.putInt(this.context.getContentResolver(), "screen_brightness", target);
                            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, ((target * 100) / 255) + "%.", null, 0L, 48, null);
                        }
                        break;
                    case 107876:
                        if (direction.equals("max")) {
                            target = 255;
                            if (!Settings.System.canWrite(this.context)) {
                                return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.DENIED, "Нет права менять яркость. Выдайте разрешение через настройки или adb: pm grant com.jarvis.assistant android.permission.WRITE_SETTINGS", null, 0L, 48, null);
                            }
                            Settings.System.putInt(this.context.getContentResolver(), "screen_brightness_mode", 0);
                            Settings.System.putInt(this.context.getContentResolver(), "screen_brightness", target);
                            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, ((target * 100) / 255) + "%.", null, 0L, 48, null);
                        }
                        break;
                    case 113762:
                        if (direction.equals("set")) {
                            Object obj2 = call.getArguments().get("percent");
                            Number number = obj2 instanceof Number ? (Number) obj2 : null;
                            if (number == null) {
                                return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "Не указан уровень яркости в процентах.", null, 0L, 48, null);
                            }
                            Number pct = number;
                            target = RangesKt.coerceIn((int) ((pct.intValue() / 100.0f) * 255), 10, 255);
                            if (!Settings.System.canWrite(this.context)) {
                                return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.DENIED, "Нет права менять яркость. Выдайте разрешение через настройки или adb: pm grant com.jarvis.assistant android.permission.WRITE_SETTINGS", null, 0L, 48, null);
                            }
                            Settings.System.putInt(this.context.getContentResolver(), "screen_brightness_mode", 0);
                            Settings.System.putInt(this.context.getContentResolver(), "screen_brightness", target);
                            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, ((target * 100) / 255) + "%.", null, 0L, 48, null);
                        }
                        break;
                    case 3089570:
                        if (direction.equals("down")) {
                            target = Math.max(current - 30, 20);
                            if (!Settings.System.canWrite(this.context)) {
                                return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.DENIED, "Нет права менять яркость. Выдайте разрешение через настройки или adb: pm grant com.jarvis.assistant android.permission.WRITE_SETTINGS", null, 0L, 48, null);
                            }
                            Settings.System.putInt(this.context.getContentResolver(), "screen_brightness_mode", 0);
                            Settings.System.putInt(this.context.getContentResolver(), "screen_brightness", target);
                            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, ((target * 100) / 255) + "%.", null, 0L, 48, null);
                        }
                        break;
                    default:
                        break;
                }
            }
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "Неизвестное направление: " + direction, null, 0L, 48, null);
        } catch (Throwable t) {
            String requestId = call.getRequestId();
            String id = getDefinition().getId();
            ToolResultStatus toolResultStatus = ToolResultStatus.FAILED;
            String message = t.getMessage();
            if (message == null) {
                message = t.getClass().getSimpleName();
            }
            return new ToolResult(requestId, id, toolResultStatus, "Не удалось изменить яркость: " + message, null, 0L, 48, null);
        }
    }
}
