package com.jarvis.tools;

import android.content.Context;
import android.media.AudioManager;
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

/* JADX INFO: compiled from: VolumeTool.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011H\u0002J\u0018\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/jarvis/tools/VolumeTool;", "Lcom/jarvis/core/common/Tool;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "audioManager", "Landroid/media/AudioManager;", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "percent", "", "max", "value", "succes", "message", "", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VolumeTool implements Tool {
    private final AudioManager audioManager;
    private final Context context;
    private final ToolDefinition definition;

    public VolumeTool(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        Object systemService = this.context.getSystemService("audio");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
        this.audioManager = (AudioManager) systemService;
        this.definition = new ToolDefinition("volume", "Громкость", "Меняет громкость мультимедиа: громче, тише, без звука, на максимум или на заданный процент", CollectionsKt.listOf((Object[]) new ToolParameter[]{new ToolParameter("direction", "up — громче, down — тише, mute — без звука, unmute — со звуком, max — максимум, set — заданный процент", ParameterType.ENUM, true, CollectionsKt.listOf((Object[]) new String[]{"up", "down", "mute", "unmute", "max", "set"})), new ToolParameter("percent", "целевой уровень громкости 0-100", ParameterType.INTEGER, false, null, 16, null)}), RiskLevel.SAFE, null, OnlineRequirement.LOCAL_ONLY, null, 160, null);
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        Object obj = call.getArguments().get("direction");
        if (obj == null) {
            obj = call.getArguments().get("action");
        }
        String direction = obj instanceof String ? (String) obj : null;
        try {
            int max = this.audioManager.getStreamMaxVolume(3);
            int current = this.audioManager.getStreamVolume(3);
            if (direction != null) {
                switch (direction.hashCode()) {
                    case -840405966:
                        if (direction.equals("unmute")) {
                            this.audioManager.setStreamVolume(3, (int) (((double) max) * 0.5d), 0);
                            return succes(call, "Звук 50%.");
                        }
                        break;
                    case 3739:
                        if (direction.equals("up")) {
                            int target = Math.min(current + 1, max);
                            this.audioManager.setStreamVolume(3, target, 0);
                            return succes(call, "Громче.");
                        }
                        break;
                    case 107876:
                        if (direction.equals("max")) {
                            this.audioManager.setStreamVolume(3, max, 0);
                            return succes(call, "Максимум.");
                        }
                        break;
                    case 113762:
                        if (direction.equals("set")) {
                            Object obj2 = call.getArguments().get("percent");
                            Number percent = obj2 instanceof Number ? (Number) obj2 : null;
                            if (percent == null) {
                                return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "Какой процент громкости?", null, 0L, 48, null);
                            }
                            int target2 = RangesKt.coerceIn((int) ((percent.intValue() / 100.0f) * max), 0, max);
                            this.audioManager.setStreamVolume(3, target2, 0);
                            return succes(call, percent.intValue() + "%.");
                        }
                        break;
                    case 3089570:
                        if (direction.equals("down")) {
                            int target3 = Math.max(current - 1, 0);
                            this.audioManager.setStreamVolume(3, target3, 0);
                            return succes(call, "Тише.");
                        }
                        break;
                    case 3363353:
                        if (direction.equals("mute")) {
                            this.audioManager.setStreamVolume(3, 0, 0);
                            return succes(call, "Без звука.");
                        }
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
            return new ToolResult(requestId, id, toolResultStatus, "Не удалось изменить громкость: " + message, null, 0L, 48, null);
        }
    }

    private final int percent(int max, int value) {
        if (max <= 0) {
            return 0;
        }
        return RangesKt.coerceIn((value * 100) / max, 0, 100);
    }

    private final ToolResult succes(ToolCall call, String message) {
        return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, message, null, 0L, 48, null);
    }
}
