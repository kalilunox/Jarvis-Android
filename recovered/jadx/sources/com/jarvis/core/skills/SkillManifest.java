package com.jarvis.core.skills;

import com.jarvis.core.common.model.ParameterType;
import com.jarvis.core.common.model.RiskLevel;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: SkillManifest.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0001*BS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\t0\u0007HÆ\u0003J\t\u0010\"\u001a\u00020\u000bHÆ\u0003J\t\u0010#\u001a\u00020\rHÆ\u0003J[\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0013\u0010%\u001a\u00020\r2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010'\u001a\u00020(HÖ\u0001J\t\u0010)\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001b\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0011¨\u0006+"}, d2 = {"Lcom/jarvis/core/skills/SkillManifest;", "", "id", "", "name", "description", "examples", "", "parameters", "Lcom/jarvis/core/skills/SkillManifest$ManifestParameter;", "riskLevel", "Lcom/jarvis/core/common/model/RiskLevel;", "onlineRequired", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Lcom/jarvis/core/common/model/RiskLevel;Z)V", "getId", "()Ljava/lang/String;", "getName", "getDescription", "getExamples", "()Ljava/util/List;", "getParameters", "getRiskLevel", "()Lcom/jarvis/core/common/model/RiskLevel;", "getOnlineRequired", "()Z", "searchableText", "getSearchableText", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "ManifestParameter", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class SkillManifest {
    private final String description;
    private final List<String> examples;
    private final String id;
    private final String name;
    private final boolean onlineRequired;
    private final List<ManifestParameter> parameters;
    private final RiskLevel riskLevel;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SkillManifest copy$default(SkillManifest skillManifest, String str, String str2, String str3, List list, List list2, RiskLevel riskLevel, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = skillManifest.id;
        }
        if ((i & 2) != 0) {
            str2 = skillManifest.name;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            str3 = skillManifest.description;
        }
        String str5 = str3;
        if ((i & 8) != 0) {
            list = skillManifest.examples;
        }
        List list3 = list;
        if ((i & 16) != 0) {
            list2 = skillManifest.parameters;
        }
        List list4 = list2;
        if ((i & 32) != 0) {
            riskLevel = skillManifest.riskLevel;
        }
        RiskLevel riskLevel2 = riskLevel;
        if ((i & 64) != 0) {
            z = skillManifest.onlineRequired;
        }
        return skillManifest.copy(str, str4, str5, list3, list4, riskLevel2, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    public final List<String> component4() {
        return this.examples;
    }

    public final List<ManifestParameter> component5() {
        return this.parameters;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final RiskLevel getRiskLevel() {
        return this.riskLevel;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getOnlineRequired() {
        return this.onlineRequired;
    }

    public final SkillManifest copy(String id, String name, String description, List<String> examples, List<ManifestParameter> parameters, RiskLevel riskLevel, boolean onlineRequired) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(examples, "examples");
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        Intrinsics.checkNotNullParameter(riskLevel, "riskLevel");
        return new SkillManifest(id, name, description, examples, parameters, riskLevel, onlineRequired);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SkillManifest)) {
            return false;
        }
        SkillManifest skillManifest = (SkillManifest) other;
        return Intrinsics.areEqual(this.id, skillManifest.id) && Intrinsics.areEqual(this.name, skillManifest.name) && Intrinsics.areEqual(this.description, skillManifest.description) && Intrinsics.areEqual(this.examples, skillManifest.examples) && Intrinsics.areEqual(this.parameters, skillManifest.parameters) && this.riskLevel == skillManifest.riskLevel && this.onlineRequired == skillManifest.onlineRequired;
    }

    public int hashCode() {
        return (((((((((((this.id.hashCode() * 31) + this.name.hashCode()) * 31) + this.description.hashCode()) * 31) + this.examples.hashCode()) * 31) + this.parameters.hashCode()) * 31) + this.riskLevel.hashCode()) * 31) + Boolean.hashCode(this.onlineRequired);
    }

    public String toString() {
        return "SkillManifest(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", examples=" + this.examples + ", parameters=" + this.parameters + ", riskLevel=" + this.riskLevel + ", onlineRequired=" + this.onlineRequired + ")";
    }

    public SkillManifest(String id, String name, String description, List<String> examples, List<ManifestParameter> parameters, RiskLevel riskLevel, boolean onlineRequired) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(examples, "examples");
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        Intrinsics.checkNotNullParameter(riskLevel, "riskLevel");
        this.id = id;
        this.name = name;
        this.description = description;
        this.examples = examples;
        this.parameters = parameters;
        this.riskLevel = riskLevel;
        this.onlineRequired = onlineRequired;
    }

    public /* synthetic */ SkillManifest(String str, String str2, String str3, List list, List list2, RiskLevel riskLevel, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? CollectionsKt.emptyList() : list, (i & 16) != 0 ? CollectionsKt.emptyList() : list2, (i & 32) != 0 ? RiskLevel.SAFE : riskLevel, (i & 64) != 0 ? false : z);
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getDescription() {
        return this.description;
    }

    public final List<String> getExamples() {
        return this.examples;
    }

    public final List<ManifestParameter> getParameters() {
        return this.parameters;
    }

    public final RiskLevel getRiskLevel() {
        return this.riskLevel;
    }

    public final boolean getOnlineRequired() {
        return this.onlineRequired;
    }

    public final String getSearchableText() {
        return CollectionsKt.joinToString$default(CollectionsKt.plus((Collection) CollectionsKt.listOf(this.description), (Iterable) this.examples), StringUtils.SPACE, null, null, 0, null, null, 62, null);
    }

    /* JADX INFO: compiled from: SkillManifest.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00030\nHÆ\u0003JA\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\nHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006!"}, d2 = {"Lcom/jarvis/core/skills/SkillManifest$ManifestParameter;", "", "name", "", "description", "type", "Lcom/jarvis/core/common/model/ParameterType;", "required", "", "allowedValues", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/jarvis/core/common/model/ParameterType;ZLjava/util/List;)V", "getName", "()Ljava/lang/String;", "getDescription", "getType", "()Lcom/jarvis/core/common/model/ParameterType;", "getRequired", "()Z", "getAllowedValues", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class ManifestParameter {
        private final List<String> allowedValues;
        private final String description;
        private final String name;
        private final boolean required;
        private final ParameterType type;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ManifestParameter copy$default(ManifestParameter manifestParameter, String str, String str2, ParameterType parameterType, boolean z, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = manifestParameter.name;
            }
            if ((i & 2) != 0) {
                str2 = manifestParameter.description;
            }
            String str3 = str2;
            if ((i & 4) != 0) {
                parameterType = manifestParameter.type;
            }
            ParameterType parameterType2 = parameterType;
            if ((i & 8) != 0) {
                z = manifestParameter.required;
            }
            boolean z2 = z;
            if ((i & 16) != 0) {
                list = manifestParameter.allowedValues;
            }
            return manifestParameter.copy(str, str3, parameterType2, z2, list);
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

        public final ManifestParameter copy(String name, String description, ParameterType type, boolean required, List<String> allowedValues) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(description, "description");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(allowedValues, "allowedValues");
            return new ManifestParameter(name, description, type, required, allowedValues);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ManifestParameter)) {
                return false;
            }
            ManifestParameter manifestParameter = (ManifestParameter) other;
            return Intrinsics.areEqual(this.name, manifestParameter.name) && Intrinsics.areEqual(this.description, manifestParameter.description) && this.type == manifestParameter.type && this.required == manifestParameter.required && Intrinsics.areEqual(this.allowedValues, manifestParameter.allowedValues);
        }

        public int hashCode() {
            return (((((((this.name.hashCode() * 31) + this.description.hashCode()) * 31) + this.type.hashCode()) * 31) + Boolean.hashCode(this.required)) * 31) + this.allowedValues.hashCode();
        }

        public String toString() {
            return "ManifestParameter(name=" + this.name + ", description=" + this.description + ", type=" + this.type + ", required=" + this.required + ", allowedValues=" + this.allowedValues + ")";
        }

        public ManifestParameter(String name, String description, ParameterType type, boolean required, List<String> allowedValues) {
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

        public /* synthetic */ ManifestParameter(String str, String str2, ParameterType parameterType, boolean z, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
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
}
