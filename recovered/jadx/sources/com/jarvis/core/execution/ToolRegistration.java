package com.jarvis.core.execution;

import com.jarvis.core.autonomy.ActionKind;
import com.jarvis.core.common.model.ToolDefinition;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ToolRegistration.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 02\u00020\u0001:\u00010BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010$\u001a\u00020\u0007HÆ\u0003J\t\u0010%\u001a\u00020\tHÆ\u0003J\t\u0010&\u001a\u00020\u000bHÆ\u0003J\t\u0010'\u001a\u00020\rHÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u000fHÆ\u0003JS\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÆ\u0001J\u0013\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010-\u001a\u00020.HÖ\u0001J\t\u0010/\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010 \u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b!\u0010\u0015¨\u00061"}, d2 = {"Lcom/jarvis/core/execution/ToolRegistration;", "", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "capabilityId", "", "source", "Lcom/jarvis/core/execution/ToolProviderSource;", "locality", "Lcom/jarvis/core/execution/ToolExecutionLocality;", "availability", "Lcom/jarvis/core/execution/ToolAvailability;", "metadata", "Lcom/jarvis/core/execution/ToolMetadata;", "actionKindHint", "Lcom/jarvis/core/autonomy/ActionKind;", "<init>", "(Lcom/jarvis/core/common/model/ToolDefinition;Ljava/lang/String;Lcom/jarvis/core/execution/ToolProviderSource;Lcom/jarvis/core/execution/ToolExecutionLocality;Lcom/jarvis/core/execution/ToolAvailability;Lcom/jarvis/core/execution/ToolMetadata;Lcom/jarvis/core/autonomy/ActionKind;)V", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "getCapabilityId", "()Ljava/lang/String;", "getSource", "()Lcom/jarvis/core/execution/ToolProviderSource;", "getLocality", "()Lcom/jarvis/core/execution/ToolExecutionLocality;", "getAvailability", "()Lcom/jarvis/core/execution/ToolAvailability;", "getMetadata", "()Lcom/jarvis/core/execution/ToolMetadata;", "getActionKindHint", "()Lcom/jarvis/core/autonomy/ActionKind;", "toolId", "getToolId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ToolRegistration {
    public static final int MAX_CAPABILITY_ID = 96;
    private final ActionKind actionKindHint;
    private final ToolAvailability availability;
    private final String capabilityId;
    private final ToolDefinition definition;
    private final ToolExecutionLocality locality;
    private final ToolMetadata metadata;
    private final ToolProviderSource source;

    public static /* synthetic */ ToolRegistration copy$default(ToolRegistration toolRegistration, ToolDefinition toolDefinition, String str, ToolProviderSource toolProviderSource, ToolExecutionLocality toolExecutionLocality, ToolAvailability toolAvailability, ToolMetadata toolMetadata, ActionKind actionKind, int i, Object obj) {
        if ((i & 1) != 0) {
            toolDefinition = toolRegistration.definition;
        }
        if ((i & 2) != 0) {
            str = toolRegistration.capabilityId;
        }
        String str2 = str;
        if ((i & 4) != 0) {
            toolProviderSource = toolRegistration.source;
        }
        ToolProviderSource toolProviderSource2 = toolProviderSource;
        if ((i & 8) != 0) {
            toolExecutionLocality = toolRegistration.locality;
        }
        ToolExecutionLocality toolExecutionLocality2 = toolExecutionLocality;
        if ((i & 16) != 0) {
            toolAvailability = toolRegistration.availability;
        }
        ToolAvailability toolAvailability2 = toolAvailability;
        if ((i & 32) != 0) {
            toolMetadata = toolRegistration.metadata;
        }
        ToolMetadata toolMetadata2 = toolMetadata;
        if ((i & 64) != 0) {
            actionKind = toolRegistration.actionKindHint;
        }
        return toolRegistration.copy(toolDefinition, str2, toolProviderSource2, toolExecutionLocality2, toolAvailability2, toolMetadata2, actionKind);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ToolDefinition getDefinition() {
        return this.definition;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getCapabilityId() {
        return this.capabilityId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ToolProviderSource getSource() {
        return this.source;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final ToolExecutionLocality getLocality() {
        return this.locality;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final ToolAvailability getAvailability() {
        return this.availability;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final ToolMetadata getMetadata() {
        return this.metadata;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final ActionKind getActionKindHint() {
        return this.actionKindHint;
    }

    public final ToolRegistration copy(ToolDefinition definition, String capabilityId, ToolProviderSource source, ToolExecutionLocality locality, ToolAvailability availability, ToolMetadata metadata, ActionKind actionKindHint) {
        Intrinsics.checkNotNullParameter(definition, "definition");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(locality, "locality");
        Intrinsics.checkNotNullParameter(availability, "availability");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        return new ToolRegistration(definition, capabilityId, source, locality, availability, metadata, actionKindHint);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ToolRegistration)) {
            return false;
        }
        ToolRegistration toolRegistration = (ToolRegistration) other;
        return Intrinsics.areEqual(this.definition, toolRegistration.definition) && Intrinsics.areEqual(this.capabilityId, toolRegistration.capabilityId) && this.source == toolRegistration.source && this.locality == toolRegistration.locality && this.availability == toolRegistration.availability && Intrinsics.areEqual(this.metadata, toolRegistration.metadata) && this.actionKindHint == toolRegistration.actionKindHint;
    }

    public int hashCode() {
        return (((((((((((this.definition.hashCode() * 31) + (this.capabilityId == null ? 0 : this.capabilityId.hashCode())) * 31) + this.source.hashCode()) * 31) + this.locality.hashCode()) * 31) + this.availability.hashCode()) * 31) + this.metadata.hashCode()) * 31) + (this.actionKindHint != null ? this.actionKindHint.hashCode() : 0);
    }

    public String toString() {
        return "ToolRegistration(definition=" + this.definition + ", capabilityId=" + this.capabilityId + ", source=" + this.source + ", locality=" + this.locality + ", availability=" + this.availability + ", metadata=" + this.metadata + ", actionKindHint=" + this.actionKindHint + ")";
    }

    public ToolRegistration(ToolDefinition definition, String capabilityId, ToolProviderSource source, ToolExecutionLocality locality, ToolAvailability availability, ToolMetadata metadata, ActionKind actionKindHint) {
        Intrinsics.checkNotNullParameter(definition, "definition");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(locality, "locality");
        Intrinsics.checkNotNullParameter(availability, "availability");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        this.definition = definition;
        this.capabilityId = capabilityId;
        this.source = source;
        this.locality = locality;
        this.availability = availability;
        this.metadata = metadata;
        this.actionKindHint = actionKindHint;
        String it = this.capabilityId;
        if (it == null) {
            return;
        }
        if (StringsKt.isBlank(it)) {
            throw new IllegalArgumentException("capabilityId blank".toString());
        }
        if (!(it.length() <= 96)) {
            throw new IllegalArgumentException("capabilityId too long".toString());
        }
    }

    public /* synthetic */ ToolRegistration(ToolDefinition toolDefinition, String str, ToolProviderSource toolProviderSource, ToolExecutionLocality toolExecutionLocality, ToolAvailability toolAvailability, ToolMetadata toolMetadata, ActionKind actionKind, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(toolDefinition, (i & 2) != 0 ? null : str, (i & 4) != 0 ? ToolProviderSource.NATIVE : toolProviderSource, (i & 8) != 0 ? ToolExecutionLocality.LOCAL : toolExecutionLocality, (i & 16) != 0 ? ToolAvailability.ALWAYS : toolAvailability, (i & 32) != 0 ? new ToolMetadata(null, null, null, null, 15, null) : toolMetadata, (i & 64) == 0 ? actionKind : null);
    }

    public final ToolDefinition getDefinition() {
        return this.definition;
    }

    public final String getCapabilityId() {
        return this.capabilityId;
    }

    public final ToolProviderSource getSource() {
        return this.source;
    }

    public final ToolExecutionLocality getLocality() {
        return this.locality;
    }

    public final ToolAvailability getAvailability() {
        return this.availability;
    }

    public final ToolMetadata getMetadata() {
        return this.metadata;
    }

    public final ActionKind getActionKindHint() {
        return this.actionKindHint;
    }

    public final String getToolId() {
        return this.definition.getId();
    }
}
