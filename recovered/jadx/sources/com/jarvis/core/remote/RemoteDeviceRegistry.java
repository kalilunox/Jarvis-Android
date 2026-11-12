package com.jarvis.core.remote;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RemoteDeviceModels.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0007J\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\nJ\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010J\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010J\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\n0\u00102\u0006\u0010\u0013\u001a\u00020\u0006J\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\n0\u0010J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0016\u001a\u00020\u0006J\u0006\u0010\u0017\u001a\u00020\u0018R*\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\t\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n0\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n`\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/jarvis/core/remote/RemoteDeviceRegistry;", "", "<init>", "()V", "devices", "Ljava/util/LinkedHashMap;", "", "Lcom/jarvis/core/remote/RemoteDevice;", "Lkotlin/collections/LinkedHashMap;", "tools", "Lcom/jarvis/core/remote/RemoteToolRef;", "registerDevice", "", "device", "registerTool", "ref", "", "onlineDevices", "toolsFor", "deviceId", "allTools", "findTool", "registryId", "size", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RemoteDeviceRegistry {
    private final LinkedHashMap<String, RemoteDevice> devices = new LinkedHashMap<>();
    private final LinkedHashMap<String, RemoteToolRef> tools = new LinkedHashMap<>();

    public final void registerDevice(RemoteDevice device) {
        Intrinsics.checkNotNullParameter(device, "device");
        this.devices.put(device.getDeviceId(), device);
    }

    public final void registerTool(RemoteToolRef ref) {
        Intrinsics.checkNotNullParameter(ref, "ref");
        if (!this.devices.containsKey(ref.getDeviceId())) {
            throw new IllegalArgumentException("device not registered".toString());
        }
        this.tools.put(ref.registryId(), ref);
    }

    public final List<RemoteDevice> devices() {
        Collection<RemoteDevice> collectionValues = this.devices.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
        return CollectionsKt.toList(collectionValues);
    }

    public final List<RemoteDevice> onlineDevices() {
        Iterable iterableValues = this.devices.values();
        Intrinsics.checkNotNullExpressionValue(iterableValues, "<get-values>(...)");
        Iterable $this$filter$iv = iterableValues;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            RemoteDevice it = (RemoteDevice) element$iv$iv;
            if (it.getStatus() == RemoteDeviceStatus.ONLINE) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    public final List<RemoteToolRef> toolsFor(String deviceId) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Iterable iterableValues = this.tools.values();
        Intrinsics.checkNotNullExpressionValue(iterableValues, "<get-values>(...)");
        Iterable $this$filter$iv = iterableValues;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            RemoteToolRef it = (RemoteToolRef) element$iv$iv;
            if (Intrinsics.areEqual(it.getDeviceId(), deviceId)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    public final List<RemoteToolRef> allTools() {
        Collection<RemoteToolRef> collectionValues = this.tools.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
        return CollectionsKt.toList(collectionValues);
    }

    public final RemoteToolRef findTool(String registryId) {
        Intrinsics.checkNotNullParameter(registryId, "registryId");
        return this.tools.get(registryId);
    }

    public final int size() {
        return this.devices.size();
    }
}
