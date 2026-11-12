package com.jarvis.core.nativeandroid;

import com.jarvis.app.VoiceController;
import com.jarvis.core.autonomy.ActionKind;
import com.jarvis.core.common.model.RiskLevel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.apache.commons.codec.language.bm.Rule;

/* JADX INFO: compiled from: NativeAndroidToolCatalog.kt */
/* JADX INFO: loaded from: classes15.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\r\u001a\u00020\u000bJ\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\u000bJ\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011J\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011J\u0006\u0010\u0013\u001a\u00020\u0014J@\u0010\u0015\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u000e\b\u0002\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0005H\u0002R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00060\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/jarvis/core/nativeandroid/NativeAndroidToolCatalog;", "", "<init>", "()V", Rule.ALL, "", "Lcom/jarvis/core/nativeandroid/NativeToolSpec;", "getALL", "()Ljava/util/List;", "byId", "", "", "find", "toolId", "liveAllowed", "", "liveDeviceSafeIds", "", "liveReadOnlyIds", "size", "", "spec", "category", "kind", "Lcom/jarvis/core/autonomy/ActionKind;", "risk", "Lcom/jarvis/core/common/model/RiskLevel;", "mode", "Lcom/jarvis/core/nativeandroid/NativeToolExecMode;", "notes", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NativeAndroidToolCatalog {
    private static final Map<String, NativeToolSpec> byId;
    public static final NativeAndroidToolCatalog INSTANCE = new NativeAndroidToolCatalog();
    private static final List<NativeToolSpec> ALL = CollectionsKt.listOf((Object[]) new NativeToolSpec[]{spec$default(INSTANCE, "flashlight", "device", ActionKind.LOCAL_DEVICE_SAFE, RiskLevel.SAFE, NativeToolExecMode.LIVE_DEVICE_SAFE, null, 32, null), spec$default(INSTANCE, "volume", "device", ActionKind.LOCAL_DEVICE_SAFE, RiskLevel.SAFE, NativeToolExecMode.LIVE_DEVICE_SAFE, null, 32, null), spec$default(INSTANCE, "brightness", "device", ActionKind.LOCAL_DEVICE_SAFE, RiskLevel.SAFE, NativeToolExecMode.LIVE_DEVICE_SAFE, null, 32, null), spec$default(INSTANCE, "weather", "info", ActionKind.READ_ONLY, RiskLevel.SAFE, NativeToolExecMode.LIVE_READ_ONLY, null, 32, null), spec$default(INSTANCE, "knowledge", "info", ActionKind.READ_ONLY, RiskLevel.SAFE, NativeToolExecMode.LIVE_READ_ONLY, null, 32, null), INSTANCE.spec("clipboard", "system", ActionKind.READ_ONLY, RiskLevel.LOW, NativeToolExecMode.LIVE_READ_ONLY, CollectionsKt.listOf("read_preferred")), INSTANCE.spec("timer", "device", ActionKind.LOCAL_DEVICE_SAFE, RiskLevel.LOW, NativeToolExecMode.GATED, CollectionsKt.listOf("stage14_gated_pending_args")), spec$default(INSTANCE, "music", "media", ActionKind.MEDIA_CONTROL, RiskLevel.LOW, NativeToolExecMode.GATED, null, 32, null), spec$default(INSTANCE, "launcher", "apps", ActionKind.LAUNCH_APP, RiskLevel.CONFIRM, NativeToolExecMode.GATED, null, 32, null), spec$default(INSTANCE, "system.setting", "settings", ActionKind.SYSTEM_SETTINGS, RiskLevel.CONFIRM, NativeToolExecMode.GATED, null, 32, null), spec$default(INSTANCE, "calendar", "info", ActionKind.READ_ONLY, RiskLevel.LOW, NativeToolExecMode.GATED, null, 32, null), spec$default(INSTANCE, "contacts.search", "info", ActionKind.READ_ONLY, RiskLevel.CONFIRM, NativeToolExecMode.GATED, null, 32, null), spec$default(INSTANCE, "notifications.list", "system", ActionKind.READ_ONLY, RiskLevel.LOW, NativeToolExecMode.GATED, null, 32, null), INSTANCE.spec("files.search", "files", ActionKind.READ_ONLY, RiskLevel.LOW, NativeToolExecMode.GATED, CollectionsKt.listOf("search_only")), spec$default(INSTANCE, "sms", "comms", ActionKind.COMMUNICATION, RiskLevel.SENSITIVE, NativeToolExecMode.BLOCKED, null, 32, null), spec$default(INSTANCE, "phone.call", "comms", ActionKind.COMMUNICATION, RiskLevel.SENSITIVE, NativeToolExecMode.BLOCKED, null, 32, null), spec$default(INSTANCE, "telegram.send", "comms", ActionKind.COMMUNICATION, RiskLevel.SENSITIVE, NativeToolExecMode.BLOCKED, null, 32, null), spec$default(INSTANCE, "screenshot", VoiceController.ENTRY_UI, ActionKind.UI_AUTOMATION, RiskLevel.CONFIRM, NativeToolExecMode.BLOCKED, null, 32, null), spec$default(INSTANCE, "ui.click", VoiceController.ENTRY_UI, ActionKind.UI_AUTOMATION, RiskLevel.SENSITIVE, NativeToolExecMode.BLOCKED, null, 32, null), spec$default(INSTANCE, "ui.type", VoiceController.ENTRY_UI, ActionKind.UI_AUTOMATION, RiskLevel.SENSITIVE, NativeToolExecMode.BLOCKED, null, 32, null), spec$default(INSTANCE, "ui.swipe", VoiceController.ENTRY_UI, ActionKind.UI_AUTOMATION, RiskLevel.SENSITIVE, NativeToolExecMode.BLOCKED, null, 32, null), spec$default(INSTANCE, "ui.global", VoiceController.ENTRY_UI, ActionKind.UI_AUTOMATION, RiskLevel.SENSITIVE, NativeToolExecMode.BLOCKED, null, 32, null), spec$default(INSTANCE, "ui.read", VoiceController.ENTRY_UI, ActionKind.UI_AUTOMATION, RiskLevel.CONFIRM, NativeToolExecMode.BLOCKED, null, 32, null), spec$default(INSTANCE, "ui.wait", VoiceController.ENTRY_UI, ActionKind.UI_AUTOMATION, RiskLevel.LOW, NativeToolExecMode.BLOCKED, null, 32, null)});

    private NativeAndroidToolCatalog() {
    }

    static {
        Iterable $this$associateBy$iv = ALL;
        int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateBy$iv, 10)), 16);
        Map destination$iv$iv = new LinkedHashMap(capacity$iv);
        for (Object element$iv$iv : $this$associateBy$iv) {
            NativeToolSpec it = (NativeToolSpec) element$iv$iv;
            destination$iv$iv.put(it.getToolId(), element$iv$iv);
        }
        byId = destination$iv$iv;
    }

    public final List<NativeToolSpec> getALL() {
        return ALL;
    }

    public final NativeToolSpec find(String toolId) {
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        return byId.get(toolId);
    }

    public final boolean liveAllowed(String toolId) {
        NativeToolExecMode mode;
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        NativeToolSpec nativeToolSpec = byId.get(toolId);
        if (nativeToolSpec == null || (mode = nativeToolSpec.getExecMode()) == null) {
            return false;
        }
        return mode == NativeToolExecMode.LIVE_DEVICE_SAFE || mode == NativeToolExecMode.LIVE_READ_ONLY;
    }

    public final Set<String> liveDeviceSafeIds() {
        Iterable $this$filter$iv = ALL;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            NativeToolSpec it = (NativeToolSpec) element$iv$iv;
            if (it.getExecMode() == NativeToolExecMode.LIVE_DEVICE_SAFE) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$map$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            NativeToolSpec it2 = (NativeToolSpec) item$iv$iv;
            destination$iv$iv2.add(it2.getToolId());
        }
        return CollectionsKt.toSet((List) destination$iv$iv2);
    }

    public final Set<String> liveReadOnlyIds() {
        Iterable $this$filter$iv = ALL;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            NativeToolSpec it = (NativeToolSpec) element$iv$iv;
            if (it.getExecMode() == NativeToolExecMode.LIVE_READ_ONLY) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$map$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            NativeToolSpec it2 = (NativeToolSpec) item$iv$iv;
            destination$iv$iv2.add(it2.getToolId());
        }
        return CollectionsKt.toSet((List) destination$iv$iv2);
    }

    public final int size() {
        return ALL.size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ NativeToolSpec spec$default(NativeAndroidToolCatalog nativeAndroidToolCatalog, String str, String str2, ActionKind actionKind, RiskLevel riskLevel, NativeToolExecMode nativeToolExecMode, List list, int i, Object obj) {
        List listEmptyList;
        if ((i & 32) == 0) {
            listEmptyList = list;
        } else {
            listEmptyList = CollectionsKt.emptyList();
        }
        return nativeAndroidToolCatalog.spec(str, str2, actionKind, riskLevel, nativeToolExecMode, listEmptyList);
    }

    private final NativeToolSpec spec(String toolId, String category, ActionKind kind, RiskLevel risk, NativeToolExecMode mode, List<String> notes) {
        return new NativeToolSpec(toolId, category, kind, risk, mode, notes);
    }
}
