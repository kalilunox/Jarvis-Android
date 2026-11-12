package com.jarvis.core.execution;

import com.jarvis.core.autonomy.ProposedAction;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ToolInput.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u001d\u0012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0015\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0004HÖ\u0001R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/jarvis/core/execution/ToolInput;", "", "parameters", "", "", "<init>", "(Ljava/util/Map;)V", "getParameters", "()Ljava/util/Map;", "matches", "", "action", "Lcom/jarvis/core/autonomy/ProposedAction;", "component1", "copy", "equals", "other", "hashCode", "", "toString", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ToolInput {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Map<String, String> parameters;

    /* JADX WARN: Multi-variable type inference failed */
    public ToolInput() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ToolInput copy$default(ToolInput toolInput, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            map = toolInput.parameters;
        }
        return toolInput.copy(map);
    }

    public final Map<String, String> component1() {
        return this.parameters;
    }

    public final ToolInput copy(Map<String, String> parameters) {
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        return new ToolInput(parameters);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ToolInput) && Intrinsics.areEqual(this.parameters, ((ToolInput) other).parameters);
    }

    public int hashCode() {
        return this.parameters.hashCode();
    }

    public String toString() {
        return "ToolInput(parameters=" + this.parameters + ")";
    }

    public ToolInput(Map<String, String> parameters) {
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        this.parameters = parameters;
        if (!(this.parameters.size() <= 12)) {
            throw new IllegalArgumentException("too many ToolInput parameters".toString());
        }
        for (Map.Entry<String, String> entry : this.parameters.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            if (StringsKt.isBlank(k)) {
                throw new IllegalArgumentException("ToolInput key blank".toString());
            }
            if (!(k.length() <= 48)) {
                throw new IllegalArgumentException("ToolInput key too long".toString());
            }
            if (!(v.length() <= 96)) {
                throw new IllegalArgumentException("ToolInput value too long".toString());
            }
        }
    }

    public /* synthetic */ ToolInput(Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? MapsKt.emptyMap() : map);
    }

    public final Map<String, String> getParameters() {
        return this.parameters;
    }

    public final boolean matches(ProposedAction action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return Intrinsics.areEqual(this.parameters, action.getArguments());
    }

    /* JADX INFO: compiled from: ToolInput.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\u0005¨\u0006\t"}, d2 = {"Lcom/jarvis/core/execution/ToolInput$Companion;", "", "<init>", "()V", "fromProposedAction", "Lcom/jarvis/core/execution/ToolInput;", "action", "Lcom/jarvis/core/autonomy/ProposedAction;", "empty", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ToolInput fromProposedAction(ProposedAction action) {
            Intrinsics.checkNotNullParameter(action, "action");
            return new ToolInput(action.getArguments());
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final ToolInput empty() {
            return new ToolInput(null, 1, 0 == true ? 1 : 0);
        }
    }
}
