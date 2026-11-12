package com.jarvis.core.execution;

import com.jarvis.core.autonomy.ActionKind;
import com.jarvis.core.common.Tool;
import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.common.model.ToolParameter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: CatalogToolRegistry.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000eJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0007J\u000e\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\nJ\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0006J\u000e\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0006J\u0016\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0006J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0017\u001a\u00020\u0006J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0017\u001a\u00020\u0006J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0017\u001a\u00020\u0006J\u000e\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0006J\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00070!2\u0006\u0010\u001a\u001a\u00020\u0006J\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00070!J\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001d0!J\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00060!J\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00060!J\f\u0010&\u001a\b\u0012\u0004\u0012\u00020'0!J\u0010\u0010(\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0017\u001a\u00020\u0006J\u0018\u0010)\u001a\u00020*2\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0002J\u0018\u0010+\u001a\u00020*2\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0002R*\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\t\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n0\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n`\bX\u0082\u0004¢\u0006\u0002\n\u0000R6\u0010\u000b\u001a*\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\f0\u0005j\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\f`\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/jarvis/core/execution/CatalogToolRegistry;", "", "<init>", "()V", "registrations", "Ljava/util/LinkedHashMap;", "", "Lcom/jarvis/core/execution/ToolRegistration;", "Lkotlin/collections/LinkedHashMap;", "executables", "Lcom/jarvis/core/common/Tool;", "byCapability", "", "size", "", "executableCount", "register", "Lcom/jarvis/core/execution/ToolRegistryResult;", "registration", "bindExecutable", "executable", "unbindExecutable", "", "toolId", "unregister", "linkCapability", "capabilityId", "findRegistration", "findDefinition", "Lcom/jarvis/core/common/model/ToolDefinition;", "findExecutable", "hasExecutable", "findByCapability", "", "allRegistrations", "allDefinitions", "definitionOnlyIds", "executableIds", "catalogEntries", "Lcom/jarvis/core/execution/ToolCatalogEntry;", "resolveDefinitionForExecution", "addCapabilityIndex", "", "removeCapabilityIndex", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CatalogToolRegistry {
    private final LinkedHashMap<String, ToolRegistration> registrations = new LinkedHashMap<>();
    private final LinkedHashMap<String, Tool> executables = new LinkedHashMap<>();
    private final LinkedHashMap<String, Set<String>> byCapability = new LinkedHashMap<>();

    public final int size() {
        return this.registrations.size();
    }

    public final int executableCount() {
        return this.executables.size();
    }

    public final ToolRegistryResult register(ToolRegistration registration) {
        String it;
        Intrinsics.checkNotNullParameter(registration, "registration");
        String id = registration.getToolId();
        ToolRegistration existing = this.registrations.get(id);
        if (existing != null && !Intrinsics.areEqual(existing.getDefinition(), registration.getDefinition())) {
            return new ToolRegistryResult.Rejected(ToolRegistryRejectReason.DEFINITION_CONFLICT, "Tool id=" + id + " already registered with different ToolDefinition");
        }
        if (existing != null && existing.getCapabilityId() != null && registration.getCapabilityId() != null && !Intrinsics.areEqual(existing.getCapabilityId(), registration.getCapabilityId())) {
            return new ToolRegistryResult.Rejected(ToolRegistryRejectReason.CAPABILITY_CONFLICT, "Tool id=" + id + " capability conflict: " + existing.getCapabilityId() + " vs " + registration.getCapabilityId());
        }
        if (existing != null && (it = existing.getCapabilityId()) != null) {
            removeCapabilityIndex(it, id);
        }
        this.registrations.put(id, registration);
        String it2 = registration.getCapabilityId();
        if (it2 != null) {
            addCapabilityIndex(it2, id);
        }
        return new ToolRegistryResult.Ok(id);
    }

    public final ToolRegistryResult bindExecutable(Tool executable) {
        Intrinsics.checkNotNullParameter(executable, "executable");
        String id = executable.getDefinition().getId();
        ToolRegistration reg = this.registrations.get(id);
        if (reg == null) {
            return new ToolRegistryResult.Rejected(ToolRegistryRejectReason.DEFINITION_MISSING, "Cannot bind executable: definition not registered for id=" + id);
        }
        if (!Intrinsics.areEqual(executable.getDefinition(), reg.getDefinition())) {
            return new ToolRegistryResult.Rejected(ToolRegistryRejectReason.EXECUTABLE_DEFINITION_MISMATCH, "Executable ToolDefinition differs from registered definition for id=" + id);
        }
        this.executables.put(id, executable);
        return new ToolRegistryResult.Ok(id);
    }

    public final boolean unbindExecutable(String toolId) {
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        return this.executables.remove(toolId) != null;
    }

    public final boolean unregister(String toolId) {
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        ToolRegistration reg = this.registrations.remove(toolId);
        if (reg == null) {
            return false;
        }
        this.executables.remove(toolId);
        String it = reg.getCapabilityId();
        if (it != null) {
            removeCapabilityIndex(it, toolId);
            return true;
        }
        return true;
    }

    public final ToolRegistryResult linkCapability(String toolId, String capabilityId) {
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        Intrinsics.checkNotNullParameter(capabilityId, "capabilityId");
        ToolRegistration reg = this.registrations.get(toolId);
        if (reg == null) {
            return new ToolRegistryResult.Rejected(ToolRegistryRejectReason.DEFINITION_MISSING, "Tool not registered: " + toolId);
        }
        if (StringsKt.isBlank(capabilityId)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        String it = reg.getCapabilityId();
        if (it != null) {
            removeCapabilityIndex(it, toolId);
        }
        this.registrations.put(toolId, ToolRegistration.copy$default(reg, null, capabilityId, null, null, null, null, null, 125, null));
        addCapabilityIndex(capabilityId, toolId);
        return new ToolRegistryResult.Ok(toolId);
    }

    public final ToolRegistration findRegistration(String toolId) {
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        return this.registrations.get(toolId);
    }

    public final ToolDefinition findDefinition(String toolId) {
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        ToolRegistration toolRegistration = this.registrations.get(toolId);
        if (toolRegistration != null) {
            return toolRegistration.getDefinition();
        }
        return null;
    }

    public final Tool findExecutable(String toolId) {
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        return this.executables.get(toolId);
    }

    public final boolean hasExecutable(String toolId) {
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        return this.executables.containsKey(toolId);
    }

    public final List<ToolRegistration> findByCapability(String capabilityId) {
        Intrinsics.checkNotNullParameter(capabilityId, "capabilityId");
        Iterable iterableEmptySet = (Set) this.byCapability.get(capabilityId);
        if (iterableEmptySet == null) {
            iterableEmptySet = SetsKt.emptySet();
        }
        Iterable $this$mapNotNull$iv = iterableEmptySet;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            String it = (String) element$iv$iv$iv;
            ToolRegistration toolRegistration = this.registrations.get(it);
            if (toolRegistration != null) {
                destination$iv$iv.add(toolRegistration);
            }
        }
        return (List) destination$iv$iv;
    }

    public final List<ToolRegistration> allRegistrations() {
        Collection<ToolRegistration> collectionValues = this.registrations.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
        return CollectionsKt.toList(collectionValues);
    }

    public final List<ToolDefinition> allDefinitions() {
        Iterable iterableValues = this.registrations.values();
        Intrinsics.checkNotNullExpressionValue(iterableValues, "<get-values>(...)");
        Iterable $this$map$iv = iterableValues;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            ToolRegistration it = (ToolRegistration) item$iv$iv;
            destination$iv$iv.add(it.getDefinition());
        }
        return (List) destination$iv$iv;
    }

    public final List<String> definitionOnlyIds() {
        Iterable iterableKeySet = this.registrations.keySet();
        Intrinsics.checkNotNullExpressionValue(iterableKeySet, "<get-keys>(...)");
        Iterable $this$filter$iv = iterableKeySet;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            if (!this.executables.containsKey(it)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    public final List<String> executableIds() {
        Set<String> setKeySet = this.executables.keySet();
        Intrinsics.checkNotNullExpressionValue(setKeySet, "<get-keys>(...)");
        return CollectionsKt.toList(setKeySet);
    }

    public final List<ToolCatalogEntry> catalogEntries() {
        CatalogToolRegistry catalogToolRegistry = this;
        Iterable iterableValues = catalogToolRegistry.registrations.values();
        Intrinsics.checkNotNullExpressionValue(iterableValues, "<get-values>(...)");
        Iterable $this$map$iv = iterableValues;
        int i = 10;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            ToolRegistration reg = (ToolRegistration) item$iv$iv;
            String toolId = reg.getToolId();
            String name = reg.getDefinition().getName();
            String description = reg.getDefinition().getDescription();
            String capabilityId = reg.getCapabilityId();
            ToolProviderSource source = reg.getSource();
            ToolExecutionLocality locality = reg.getLocality();
            ToolAvailability availability = reg.getAvailability();
            RiskLevel riskLevel = reg.getDefinition().getRiskLevel();
            List<String> requiredPermissions = reg.getDefinition().getRequiredPermissions();
            boolean zHasExecutable = catalogToolRegistry.hasExecutable(reg.getToolId());
            ActionKind actionKindHint = reg.getActionKindHint();
            ToolMetadata metadata = reg.getMetadata();
            Iterable $this$map$iv2 = reg.getDefinition().getParameters();
            Iterable $this$map$iv3 = $this$map$iv;
            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, i));
            for (Object item$iv$iv2 : $this$map$iv2) {
                ToolParameter it = (ToolParameter) item$iv$iv2;
                destination$iv$iv2.add(it.getName());
            }
            destination$iv$iv.add(new ToolCatalogEntry(toolId, name, description, capabilityId, source, locality, availability, riskLevel, requiredPermissions, zHasExecutable, actionKindHint, metadata, (List) destination$iv$iv2, reg.getDefinition().getContractVersion()));
            i = 10;
            catalogToolRegistry = this;
            $this$map$iv = $this$map$iv3;
        }
        return (List) destination$iv$iv;
    }

    public final ToolDefinition resolveDefinitionForExecution(String toolId) {
        Intrinsics.checkNotNullParameter(toolId, "toolId");
        return findDefinition(toolId);
    }

    private final void addCapabilityIndex(String capabilityId, String toolId) {
        Set<String> linkedHashSet;
        Map $this$getOrPut$iv = this.byCapability;
        Set<String> set = $this$getOrPut$iv.get(capabilityId);
        if (set == null) {
            linkedHashSet = new LinkedHashSet();
            $this$getOrPut$iv.put(capabilityId, linkedHashSet);
        } else {
            linkedHashSet = set;
        }
        linkedHashSet.add(toolId);
    }

    private final void removeCapabilityIndex(String capabilityId, String toolId) {
        Set<String> set = this.byCapability.get(capabilityId);
        if (set == null) {
            return;
        }
        set.remove(toolId);
        if (set.isEmpty()) {
            this.byCapability.remove(capabilityId);
        }
    }
}
