package com.jarvis.core.nlp;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: SystemStatusReport.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/jarvis/core/nlp/SystemStatusReport;", "", "<init>", "()V", "LOW_BATTERY", "", "LOW_STORAGE_GB", "HOT_C", "format", "", "s", "Lcom/jarvis/core/nlp/SystemSnapshot;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SystemStatusReport {
    private static final int HOT_C = 42;
    public static final SystemStatusReport INSTANCE = new SystemStatusReport();
    private static final int LOW_BATTERY = 20;
    private static final int LOW_STORAGE_GB = 2;

    private SystemStatusReport() {
    }

    public final String format(SystemSnapshot s) {
        String network;
        Intrinsics.checkNotNullParameter(s, "s");
        List $this$format_u24lambda_u240 = CollectionsKt.createListBuilder();
        if (s.getBatteryPercent() <= 20 && !s.getCharging()) {
            $this$format_u24lambda_u240.add("низкий заряд");
        }
        if (s.getNetwork() == null) {
            $this$format_u24lambda_u240.add("нет сети");
        }
        if (s.getFreeStorageGb() < 2) {
            $this$format_u24lambda_u240.add("мало места");
        }
        if (s.getBatteryTempC() != null && s.getBatteryTempC().intValue() >= 42) {
            $this$format_u24lambda_u240.add("телефон перегрет");
        }
        List issues = CollectionsKt.build($this$format_u24lambda_u240);
        String head = issues.isEmpty() ? "Все системы в норме, сэр." : "Внимание, сэр: " + CollectionsKt.joinToString$default(issues, ", ", null, null, 0, null, null, 62, null) + ".";
        String battery = "Заряд " + s.getBatteryPercent() + "%" + (s.getCharging() ? ", идёт зарядка." : ".");
        String it = s.getNetwork();
        if (it == null || (network = "Сеть: " + it + ".") == null) {
            network = "Сети нет.";
        }
        Integer batteryTempC = s.getBatteryTempC();
        String temp = batteryTempC != null ? " Температура " + batteryTempC.intValue() + "°." : null;
        if (temp == null) {
            temp = "";
        }
        return head + StringUtils.SPACE + battery + StringUtils.SPACE + network + " Свободно " + s.getFreeStorageGb() + " ГБ." + temp;
    }
}
