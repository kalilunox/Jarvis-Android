package com.jarvis.core.remote;

import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.common.model.ToolDefinition;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RemoteDeviceModels.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/jarvis/core/remote/RemoteToolBridge;", "", "<init>", "()V", "toDefinition", "Lcom/jarvis/core/common/model/ToolDefinition;", "ref", "Lcom/jarvis/core/remote/RemoteToolRef;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RemoteToolBridge {
    public static final RemoteToolBridge INSTANCE = new RemoteToolBridge();

    private RemoteToolBridge() {
    }

    public final ToolDefinition toDefinition(RemoteToolRef ref) {
        RiskLevel riskLevel;
        Intrinsics.checkNotNullParameter(ref, "ref");
        ToolDefinition definition = ref.getDefinition();
        String strRegistryId = ref.registryId();
        String str = ref.getDefinition().getName() + " @" + ref.getDeviceId();
        String str2 = "[" + ref.getDeviceId() + "] " + ref.getDefinition().getDescription();
        if (ref.getDefinition().getRiskLevel().ordinal() >= RiskLevel.SENSITIVE.ordinal()) {
            riskLevel = ref.getDefinition().getRiskLevel();
        } else {
            riskLevel = RiskLevel.CONFIRM;
        }
        return definition.copy((232 & 1) != 0 ? definition.id : strRegistryId, (232 & 2) != 0 ? definition.name : str, (232 & 4) != 0 ? definition.description : str2, (232 & 8) != 0 ? definition.parameters : null, (232 & 16) != 0 ? definition.riskLevel : riskLevel, (232 & 32) != 0 ? definition.requiredPermissions : null, (232 & 64) != 0 ? definition.onlineRequirement : null, (232 & 128) != 0 ? definition.contractVersion : null);
    }
}
