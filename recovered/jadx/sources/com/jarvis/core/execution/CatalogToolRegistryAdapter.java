package com.jarvis.core.execution;

import com.jarvis.core.autonomy.ActionKind;
import com.jarvis.core.capability.CapabilityAvailability;
import com.jarvis.core.capability.CapabilityDescriptor;
import com.jarvis.core.capability.CapabilityRegistry;
import com.jarvis.core.common.Tool;
import com.jarvis.core.common.ToolRegistry;
import com.jarvis.core.common.model.OnlineRequirement;
import com.jarvis.core.common.model.ToolDefinition;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CatalogToolRegistryAdapter.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bJ:\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0018\b\u0002\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u0010J\u000e\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002¨\u0006\u001c"}, d2 = {"Lcom/jarvis/core/execution/CatalogToolRegistryAdapter;", "", "<init>", "()V", "importDefinitionsFromCapabilityRegistry", "", "catalog", "Lcom/jarvis/core/execution/CatalogToolRegistry;", "capabilities", "Lcom/jarvis/core/capability/CapabilityRegistry;", "source", "Lcom/jarvis/core/execution/ToolProviderSource;", "importExecutablesFromToolRegistry", "tools", "Lcom/jarvis/core/common/ToolRegistry;", "actionKindHint", "Lkotlin/Function1;", "Lcom/jarvis/core/common/model/ToolDefinition;", "Lcom/jarvis/core/autonomy/ActionKind;", "definitionFromCapability", "cap", "Lcom/jarvis/core/capability/CapabilityDescriptor;", "mapAvailability", "Lcom/jarvis/core/execution/ToolAvailability;", "a", "Lcom/jarvis/core/capability/CapabilityAvailability;", "localityFrom", "Lcom/jarvis/core/execution/ToolExecutionLocality;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CatalogToolRegistryAdapter {
    public static final CatalogToolRegistryAdapter INSTANCE = new CatalogToolRegistryAdapter();

    /* JADX INFO: compiled from: CatalogToolRegistryAdapter.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[CapabilityAvailability.values().length];
            try {
                iArr[CapabilityAvailability.ALWAYS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[CapabilityAvailability.NEEDS_PERMISSION.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[CapabilityAvailability.NEEDS_ONLINE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[CapabilityAvailability.NEEDS_ACCESSIBILITY.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[CapabilityAvailability.CONDITIONAL.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[OnlineRequirement.values().length];
            try {
                iArr2[OnlineRequirement.LOCAL_ONLY.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr2[OnlineRequirement.ONLINE_REQUIRED.ordinal()] = 2;
            } catch (NoSuchFieldError e7) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    private CatalogToolRegistryAdapter() {
    }

    public static /* synthetic */ int importDefinitionsFromCapabilityRegistry$default(CatalogToolRegistryAdapter catalogToolRegistryAdapter, CatalogToolRegistry catalogToolRegistry, CapabilityRegistry capabilityRegistry, ToolProviderSource toolProviderSource, int i, Object obj) {
        if ((i & 4) != 0) {
            toolProviderSource = ToolProviderSource.NATIVE;
        }
        return catalogToolRegistryAdapter.importDefinitionsFromCapabilityRegistry(catalogToolRegistry, capabilityRegistry, toolProviderSource);
    }

    public final int importDefinitionsFromCapabilityRegistry(CatalogToolRegistry catalog, CapabilityRegistry capabilities, ToolProviderSource source) {
        this = this;
        Intrinsics.checkNotNullParameter(catalog, "catalog");
        Intrinsics.checkNotNullParameter(capabilities, "capabilities");
        Intrinsics.checkNotNullParameter(source, "source");
        int n = 0;
        for (CapabilityDescriptor cap : capabilities.all()) {
            ToolDefinition def = this.definitionFromCapability(cap);
            String id = cap.getId();
            ToolExecutionLocality toolExecutionLocalityLocalityFrom = this.localityFrom(cap);
            ToolAvailability toolAvailabilityMapAvailability = this.mapAvailability(cap.getAvailability());
            Set set = CollectionsKt.toSet(cap.getTags());
            String lowerCase = source.name().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            ToolRegistryResult result = catalog.register(new ToolRegistration(def, id, source, toolExecutionLocalityLocalityFrom, toolAvailabilityMapAvailability, new ToolMetadata(set, lowerCase, CollectionsKt.listOf("from_capability=" + cap.getId()), null, 8, null), null));
            if (result.isOk()) {
                n++;
            }
        }
        return n;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ int importExecutablesFromToolRegistry$default(CatalogToolRegistryAdapter catalogToolRegistryAdapter, CatalogToolRegistry catalogToolRegistry, ToolRegistry toolRegistry, ToolProviderSource toolProviderSource, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            toolProviderSource = ToolProviderSource.NATIVE;
        }
        if ((i & 8) != 0) {
            function1 = null;
        }
        return catalogToolRegistryAdapter.importExecutablesFromToolRegistry(catalogToolRegistry, toolRegistry, toolProviderSource, function1);
    }

    public final int importExecutablesFromToolRegistry(CatalogToolRegistry catalog, ToolRegistry tools, ToolProviderSource source, Function1<? super ToolDefinition, ? extends ActionKind> actionKindHint) {
        actionKindHint = actionKindHint;
        Intrinsics.checkNotNullParameter(catalog, "catalog");
        Intrinsics.checkNotNullParameter(tools, "tools");
        Intrinsics.checkNotNullParameter(source, "source");
        int n = 0;
        for (Tool tool : tools.available()) {
            ToolDefinition def = tool.getDefinition();
            if (catalog.findDefinition(def.getId()) == null) {
                ToolExecutionLocality toolExecutionLocality = ToolExecutionLocality.LOCAL;
                ToolAvailability toolAvailability = ToolAvailability.ALWAYS;
                String lowerCase = source.name().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                catalog.register(new ToolRegistration(def, null, source, toolExecutionLocality, toolAvailability, new ToolMetadata(null, lowerCase, null, null, 13, null), actionKindHint != null ? actionKindHint.invoke(def) : null, 2, null));
            }
            ToolRegistryResult bind = catalog.bindExecutable(tool);
            if (bind.isOk()) {
                n++;
            }
        }
        return n;
    }

    public final ToolDefinition definitionFromCapability(CapabilityDescriptor cap) {
        Intrinsics.checkNotNullParameter(cap, "cap");
        return new ToolDefinition(cap.getBackingToolId(), cap.getTitle(), cap.getDescription(), cap.getArgumentSchema(), cap.getRiskLevel(), cap.getPermissions(), cap.getOnlineRequirement(), null, 128, null);
    }

    public final ToolAvailability mapAvailability(CapabilityAvailability a) {
        Intrinsics.checkNotNullParameter(a, "a");
        switch (WhenMappings.$EnumSwitchMapping$0[a.ordinal()]) {
            case 1:
                return ToolAvailability.ALWAYS;
            case 2:
                return ToolAvailability.NEEDS_PERMISSION;
            case 3:
                return ToolAvailability.NEEDS_ONLINE;
            case 4:
                return ToolAvailability.NEEDS_ACCESSIBILITY;
            case 5:
                return ToolAvailability.CONDITIONAL;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final ToolExecutionLocality localityFrom(CapabilityDescriptor cap) {
        switch (WhenMappings.$EnumSwitchMapping$1[cap.getOnlineRequirement().ordinal()]) {
            case 1:
                return ToolExecutionLocality.LOCAL;
            case 2:
                return ToolExecutionLocality.REMOTE;
            default:
                return ToolExecutionLocality.HYBRID;
        }
    }
}
