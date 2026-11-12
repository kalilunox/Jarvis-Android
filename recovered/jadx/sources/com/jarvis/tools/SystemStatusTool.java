package com.jarvis.tools;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Environment;
import android.os.StatFs;
import com.jarvis.app.EventRoutineRunner;
import com.jarvis.core.common.Tool;
import com.jarvis.core.common.model.OnlineRequirement;
import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import com.jarvis.core.nlp.SystemSnapshot;
import com.jarvis.core.nlp.SystemStatusReport;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: SystemStatusTool.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\n\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Lcom/jarvis/tools/SystemStatusTool;", "Lcom/jarvis/core/common/Tool;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "snapshot", "Lcom/jarvis/core/nlp/SystemSnapshot;", EventRoutineRunner.NETWORK, "", "Companion", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SystemStatusTool implements Tool {

    @Deprecated
    public static final long BYTES_IN_GB = 1073741824;
    private static final Companion Companion = new Companion(null);
    private final Context context;
    private final ToolDefinition definition;

    public SystemStatusTool(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.definition = new ToolDefinition("system.status", "Состояние систем", "Заряд батареи, сеть, свободная память и температура телефона", CollectionsKt.emptyList(), RiskLevel.SAFE, null, OnlineRequirement.LOCAL_ONLY, null, 160, null);
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        try {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, SystemStatusReport.INSTANCE.format(snapshot()), null, 0L, 48, null);
        } catch (Throwable t) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.FAILED, "Не удалось снять показания: " + t.getMessage(), null, 0L, 48, null);
        }
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0055  */
    private final SystemSnapshot snapshot() {
        Integer temp;
        Intent battery = this.context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        int level = battery != null ? battery.getIntExtra("level", 0) : 0;
        int scale = RangesKt.coerceAtLeast(battery != null ? battery.getIntExtra("scale", 100) : 100, 1);
        Integer state = battery != null ? Integer.valueOf(battery.getIntExtra("status", -1)) : null;
        if (battery != null) {
            temp = Integer.valueOf(battery.getIntExtra("temperature", -1));
            int it = temp.intValue();
            int it2 = it > 0 ? 1 : 0;
            if (it2 == 0) {
                temp = null;
            }
        } else {
            temp = null;
        }
        return new SystemSnapshot((level * 100) / scale, (state != null && state.intValue() == 2) || (state != null && state.intValue() == 5), network(), (int) (new StatFs(Environment.getDataDirectory().getPath()).getAvailableBytes() / 1073741824), temp != null ? Integer.valueOf(temp.intValue() / 10) : null);
    }

    private final String network() {
        NetworkCapabilities caps;
        ConnectivityManager cm = (ConnectivityManager) this.context.getSystemService(ConnectivityManager.class);
        if (cm == null || (caps = cm.getNetworkCapabilities(cm.getActiveNetwork())) == null) {
            return null;
        }
        if (caps.hasTransport(1)) {
            return "Wi-Fi";
        }
        if (caps.hasTransport(0)) {
            return "мобильная";
        }
        return caps.hasTransport(4) ? "VPN" : "есть";
    }

    /* JADX INFO: compiled from: SystemStatusTool.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/jarvis/tools/SystemStatusTool$Companion;", "", "<init>", "()V", "BYTES_IN_GB", "", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
