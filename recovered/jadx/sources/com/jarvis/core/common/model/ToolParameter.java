package com.jarvis.core.common.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ToolModels.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00030\nHÆ\u0003JA\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\nHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006!"}, d2 = {"Lcom/jarvis/core/common/model/ToolParameter;", "", "name", "", "description", "type", "Lcom/jarvis/core/common/model/ParameterType;", "required", "", "allowedValues", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/core/common/model/ParameterType;ZLjava/util/List;)V", "getName", "()Ljava/lang/String;", "getDescription", "getType", "()Lcom/jarvis/core/common/model/ParameterType;", "getRequired", "()Z", "getAllowedValues", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ToolParameter {
    private final List<String> allowedValues;
    private final String description;
    private final String name;
    private final boolean required;
    private final ParameterType type;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ToolParameter copy$default(ToolParameter toolParameter, String str, String str2, ParameterType parameterType, boolean z, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = toolParameter.name;
        }
        if ((i & 2) != 0) {
            str2 = toolParameter.description;
        }
        String str3 = str2;
        if ((i & 4) != 0) {
            parameterType = toolParameter.type;
        }
        ParameterType parameterType2 = parameterType;
        if ((i & 8) != 0) {
            z = toolParameter.required;
        }
        boolean z2 = z;
        if ((i & 16) != 0) {
            list = toolParameter.allowedValues;
        }
        return toolParameter.copy(str, str3, parameterType2, z2, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ParameterType getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getRequired() {
        return this.required;
    }

    public final List<String> component5() {
        return this.allowedValues;
    }

    public final ToolParameter copy(String name, String description, ParameterType type, boolean required, List<String> allowedValues) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(allowedValues, "allowedValues");
        return new ToolParameter(name, description, type, required, allowedValues);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ToolParameter)) {
            return false;
        }
        ToolParameter toolParameter = (ToolParameter) other;
        return Intrinsics.areEqual(this.name, toolParameter.name) && Intrinsics.areEqual(this.description, toolParameter.description) && this.type == toolParameter.type && this.required == toolParameter.required && Intrinsics.areEqual(this.allowedValues, toolParameter.allowedValues);
    }

    public int hashCode() {
        return (((((((this.name.hashCode() * 31) + this.description.hashCode()) * 31) + this.type.hashCode()) * 31) + Boolean.hashCode(this.required)) * 31) + this.allowedValues.hashCode();
    }

    public String toString() {
        return "ToolParameter(name=" + this.name + ", description=" + this.description + ", type=" + this.type + ", required=" + this.required + ", allowedValues=" + this.allowedValues + ")";
    }

    public ToolParameter(String name, String description, ParameterType type, boolean required, List<String> allowedValues) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(allowedValues, "allowedValues");
        this.name = name;
        this.description = description;
        this.type = type;
        this.required = required;
        this.allowedValues = allowedValues;
    }

    public /* synthetic */ ToolParameter(String str, String str2, ParameterType parameterType, boolean z, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? ParameterType.STRING : parameterType, (i & 8) != 0 ? false : z, (i & 16) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final String getName() {
        return this.name;
    }

    public final String getDescription() {
        return this.description;
    }

    public final ParameterType getType() {
        return this.type;
    }

    public final boolean getRequired() {
        return this.required;
    }

    public final List<String> getAllowedValues() {
        return this.allowedValues;
    }
}
