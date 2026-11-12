package com.jarvis.core.diagnostics;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SystemDiagnostics.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/jarvis/core/diagnostics/DiagnosticsFormatter;", "", "<init>", "()V", "render", "", "snapshot", "Lcom/jarvis/core/diagnostics/SystemDiagnosticsSnapshot;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DiagnosticsFormatter {
    public static final DiagnosticsFormatter INSTANCE = new DiagnosticsFormatter();

    private DiagnosticsFormatter() {
    }

    public final String render(SystemDiagnosticsSnapshot snapshot) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        StringBuilder $this$render_u24lambda_u240 = new StringBuilder();
        $this$render_u24lambda_u240.append("JARVIS DIAGNOSTICS").append('\n');
        $this$render_u24lambda_u240.append("at=" + snapshot.getCapturedAtMillis()).append('\n');
        Iterable $this$forEach$iv = snapshot.lines();
        for (Object element$iv : $this$forEach$iv) {
            String it = (String) element$iv;
            $this$render_u24lambda_u240.append(it).append('\n');
        }
        return $this$render_u24lambda_u240.toString();
    }
}
