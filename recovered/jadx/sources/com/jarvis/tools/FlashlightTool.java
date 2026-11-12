package com.jarvis.tools;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
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
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FlashlightTool.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\n\u0010\u0015\u001a\u0004\u0018\u00010\u0012H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/jarvis/tools/FlashlightTool;", "Lcom/jarvis/core/common/Tool;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "cameraManager", "Landroid/hardware/camera2/CameraManager;", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "setTorch", "requestId", "", "wantOn", "", "findBackCamera", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FlashlightTool implements Tool {
    private final CameraManager cameraManager;
    private final Context context;
    private final ToolDefinition definition;

    public FlashlightTool(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        Object systemService = this.context.getSystemService("camera");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.hardware.camera2.CameraManager");
        this.cameraManager = (CameraManager) systemService;
        this.definition = new ToolDefinition("flashlight", "Фонарик", "Включить или выключить фонарик устройства", CollectionsKt.listOf(new ToolParameter("action", "on — включить, off — выключить", ParameterType.ENUM, true, CollectionsKt.listOf((Object[]) new String[]{"on", "off"}))), RiskLevel.SAFE, null, OnlineRequirement.LOCAL_ONLY, null, 160, null);
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        try {
            Object obj = call.getArguments().get("action");
            String action = obj instanceof String ? (String) obj : null;
            if (Intrinsics.areEqual(action, "on")) {
                return setTorch(call.getRequestId(), true);
            }
            return Intrinsics.areEqual(action, "off") ? setTorch(call.getRequestId(), false) : new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "Не указано действие: on или off", null, 0L, 48, null);
        } catch (Throwable t) {
            String requestId = call.getRequestId();
            String id = getDefinition().getId();
            ToolResultStatus toolResultStatus = ToolResultStatus.FAILED;
            String message = t.getMessage();
            if (message == null) {
                message = t.getClass().getSimpleName();
            }
            return new ToolResult(requestId, id, toolResultStatus, "Не удалось переключить фонарик: " + message, null, 0L, 48, null);
        }
    }

    private final ToolResult setTorch(String requestId, boolean wantOn) throws CameraAccessException {
        String cameraId = findBackCamera();
        if (cameraId == null) {
            return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.UNAVAILABLE, "На устройстве нет камеры с фонариком", null, 0L, 48, null);
        }
        this.cameraManager.setTorchMode(cameraId, wantOn);
        return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.SUCCESS, wantOn ? "Включён." : "Выключен.", MapsKt.mapOf(TuplesKt.to("on", Boolean.valueOf(wantOn))), 0L, 32, null);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0030  */
    private final String findBackCamera() {
        Object objM392constructorimpl;
        Object element$iv;
        boolean z;
        try {
            Result.Companion companion = Result.INSTANCE;
            FlashlightTool $this$findBackCamera_u24lambda_u240 = this;
            Object[] cameraIdList = $this$findBackCamera_u24lambda_u240.cameraManager.getCameraIdList();
            Intrinsics.checkNotNullExpressionValue(cameraIdList, "getCameraIdList(...)");
            Object[] $this$firstOrNull$iv = cameraIdList;
            int length = $this$firstOrNull$iv.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    element$iv = $this$firstOrNull$iv[i];
                    String id = (String) element$iv;
                    Integer num = (Integer) $this$findBackCamera_u24lambda_u240.cameraManager.getCameraCharacteristics(id).get(CameraCharacteristics.LENS_FACING);
                    if (num == null) {
                        z = false;
                    } else {
                        z = true;
                        if (num.intValue() != 1) {
                            z = false;
                        }
                    }
                    if (z) {
                        break;
                    }
                    i++;
                } else {
                    element$iv = null;
                    break;
                }
            }
            objM392constructorimpl = Result.m392constructorimpl((String) element$iv);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        return (String) (Result.m398isFailureimpl(objM392constructorimpl) ? null : objM392constructorimpl);
    }
}
