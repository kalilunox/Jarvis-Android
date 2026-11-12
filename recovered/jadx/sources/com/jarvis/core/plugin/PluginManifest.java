package com.jarvis.core.plugin;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: PluginContracts.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bo\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000b\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\u0015\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000bHÆ\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003Jw\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020'HÖ\u0001J\t\u0010(\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015¨\u0006)"}, d2 = {"Lcom/jarvis/core/plugin/PluginManifest;", "", "id", "", "name", "version", "description", "capabilities", "", "permissions", "configuration", "", "toolIds", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/Map;Ljava/util/List;)V", "getId", "()Ljava/lang/String;", "getName", "getVersion", "getDescription", "getCapabilities", "()Ljava/util/List;", "getPermissions", "getConfiguration", "()Ljava/util/Map;", "getToolIds", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class PluginManifest {
    private final List<String> capabilities;
    private final Map<String, String> configuration;
    private final String description;
    private final String id;
    private final String name;
    private final List<String> permissions;
    private final List<String> toolIds;
    private final String version;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getVersion() {
        return this.version;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    public final List<String> component5() {
        return this.capabilities;
    }

    public final List<String> component6() {
        return this.permissions;
    }

    public final Map<String, String> component7() {
        return this.configuration;
    }

    public final List<String> component8() {
        return this.toolIds;
    }

    public final PluginManifest copy(String id, String name, String version, String description, List<String> capabilities, List<String> permissions, Map<String, String> configuration, List<String> toolIds) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(capabilities, "capabilities");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(toolIds, "toolIds");
        return new PluginManifest(id, name, version, description, capabilities, permissions, configuration, toolIds);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PluginManifest)) {
            return false;
        }
        PluginManifest pluginManifest = (PluginManifest) other;
        return Intrinsics.areEqual(this.id, pluginManifest.id) && Intrinsics.areEqual(this.name, pluginManifest.name) && Intrinsics.areEqual(this.version, pluginManifest.version) && Intrinsics.areEqual(this.description, pluginManifest.description) && Intrinsics.areEqual(this.capabilities, pluginManifest.capabilities) && Intrinsics.areEqual(this.permissions, pluginManifest.permissions) && Intrinsics.areEqual(this.configuration, pluginManifest.configuration) && Intrinsics.areEqual(this.toolIds, pluginManifest.toolIds);
    }

    public int hashCode() {
        return (((((((((((((this.id.hashCode() * 31) + this.name.hashCode()) * 31) + this.version.hashCode()) * 31) + this.description.hashCode()) * 31) + this.capabilities.hashCode()) * 31) + this.permissions.hashCode()) * 31) + this.configuration.hashCode()) * 31) + this.toolIds.hashCode();
    }

    public String toString() {
        return "PluginManifest(id=" + this.id + ", name=" + this.name + ", version=" + this.version + ", description=" + this.description + ", capabilities=" + this.capabilities + ", permissions=" + this.permissions + ", configuration=" + this.configuration + ", toolIds=" + this.toolIds + ")";
    }

    public PluginManifest(String id, String name, String version, String description, List<String> capabilities, List<String> permissions, Map<String, String> configuration, List<String> toolIds) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(capabilities, "capabilities");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(toolIds, "toolIds");
        this.id = id;
        this.name = name;
        this.version = version;
        this.description = description;
        this.capabilities = capabilities;
        this.permissions = permissions;
        this.configuration = configuration;
        this.toolIds = toolIds;
        if (StringsKt.isBlank(this.id)) {
            throw new IllegalArgumentException("plugin id blank".toString());
        }
        if (StringsKt.isBlank(this.name)) {
            throw new IllegalArgumentException("plugin name blank".toString());
        }
        if (StringsKt.isBlank(this.version)) {
            throw new IllegalArgumentException("plugin version blank".toString());
        }
        if (!(this.version.length() <= 32)) {
            throw new IllegalArgumentException("version too long".toString());
        }
        if (!(this.capabilities.size() <= 32)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(this.permissions.size() <= 32)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(this.configuration.size() <= 32)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(this.toolIds.size() <= 64)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(this.description.length() <= 500)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public /* synthetic */ PluginManifest(String str, String str2, String str3, String str4, List list, List list2, Map map, List list3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? "" : str4, (i & 16) != 0 ? CollectionsKt.emptyList() : list, (i & 32) != 0 ? CollectionsKt.emptyList() : list2, (i & 64) != 0 ? MapsKt.emptyMap() : map, (i & 128) != 0 ? CollectionsKt.emptyList() : list3);
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getVersion() {
        return this.version;
    }

    public final String getDescription() {
        return this.description;
    }

    public final List<String> getCapabilities() {
        return this.capabilities;
    }

    public final List<String> getPermissions() {
        return this.permissions;
    }

    public final Map<String, String> getConfiguration() {
        return this.configuration;
    }

    public final List<String> getToolIds() {
        return this.toolIds;
    }
}
