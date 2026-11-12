package com.jarvis.screencontrol;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: compiled from: JarvisNotificationListenerService.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010'\n\u0000*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u001e\u0010\u0004\u001a\u00020\u00052\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007H\u0014¨\u0006\b"}, d2 = {"com/jarvis/screencontrol/JarvisNotificationListenerService$Companion$cache$1", "Ljava/util/LinkedHashMap;", "", "Lcom/jarvis/screencontrol/NotificationInfo;", "removeEldestEntry", "", "eldest", "", "screen-control_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JarvisNotificationListenerService$Companion$cache$1 extends LinkedHashMap<String, NotificationInfo> {
    JarvisNotificationListenerService$Companion$cache$1() {
        super(16, 0.75f, false);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ boolean containsKey(Object key) {
        if (key instanceof String) {
            return containsKey((String) key);
        }
        return false;
    }

    public /* bridge */ boolean containsKey(String p0) {
        return super.containsKey((Object) p0);
    }

    public /* bridge */ boolean containsValue(NotificationInfo p0) {
        return super.containsValue((Object) p0);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ boolean containsValue(Object value) {
        if (value instanceof NotificationInfo) {
            return containsValue((NotificationInfo) value);
        }
        return false;
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Set<Map.Entry<String, NotificationInfo>> entrySet() {
        return getEntries();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ NotificationInfo get(Object key) {
        if (key instanceof String) {
            return get((String) key);
        }
        return null;
    }

    public /* bridge */ NotificationInfo get(String p0) {
        return (NotificationInfo) super.get((Object) p0);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ /* synthetic */ Object get(Object key) {
        if (key instanceof String) {
            return get((String) key);
        }
        return null;
    }

    public /* bridge */ Set<Map.Entry<String, NotificationInfo>> getEntries() {
        return super.entrySet();
    }

    public /* bridge */ Set<String> getKeys() {
        return super.keySet();
    }

    public final /* bridge */ NotificationInfo getOrDefault(Object key, NotificationInfo defaultValue) {
        return !(key instanceof String) ? defaultValue : getOrDefault((String) key, defaultValue);
    }

    public /* bridge */ NotificationInfo getOrDefault(String p0, NotificationInfo p1) {
        return (NotificationInfo) super.getOrDefault((Object) p0, p1);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.Map
    public final /* bridge */ /* synthetic */ Object getOrDefault(Object key, Object defaultValue) {
        return !(key instanceof String) ? defaultValue : getOrDefault((String) key, (NotificationInfo) defaultValue);
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ Collection<NotificationInfo> getValues() {
        return super.values();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Set<String> keySet() {
        return getKeys();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ NotificationInfo remove(Object key) {
        if (key instanceof String) {
            return remove((String) key);
        }
        return null;
    }

    public /* bridge */ NotificationInfo remove(String p0) {
        return (NotificationInfo) super.remove((Object) p0);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ /* synthetic */ Object remove(Object key) {
        if (key instanceof String) {
            return remove((String) key);
        }
        return null;
    }

    @Override // java.util.HashMap, java.util.Map
    public final /* bridge */ boolean remove(Object key, Object value) {
        if ((key instanceof String) && (value instanceof NotificationInfo)) {
            return remove((String) key, (NotificationInfo) value);
        }
        return false;
    }

    public /* bridge */ boolean remove(String p0, NotificationInfo p1) {
        return super.remove((Object) p0, (Object) p1);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Collection<NotificationInfo> values() {
        return getValues();
    }

    @Override // java.util.LinkedHashMap
    protected boolean removeEldestEntry(Map.Entry<String, NotificationInfo> eldest) {
        return size() > 50;
    }
}
