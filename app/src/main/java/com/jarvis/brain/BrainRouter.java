package com.jarvis.brain;

import com.jarvis.core.common.model.UserRequest;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrainRouter.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u0000 \u00112\u00020\u0001:\u0003\u000f\u0010\u0011J,\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007H&J>\u0010\u000b\u001a\u0004\u0018\u00010\u00032\u0006\u0010\f\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00072\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0016¨\u0006\u0012À\u0006\u0003"}, d2 = {"Lcom/jarvis/brain/BrainRouter;", "", "route", "Lcom/jarvis/brain/BrainResult;", "request", "Lcom/jarvis/core/common/model/UserRequest;", "history", "", "", "topSkills", "Lcom/jarvis/brain/BrainRouter$SkillInfo;", "continueAfterTool", "originalRequest", "lastToolId", "lastToolMessage", "SkillInfo", "SkillParam", "Companion", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface BrainRouter {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;
    public static final int MAX_SKILLS = 8;

    BrainResult route(UserRequest request, List<String> history, List<SkillInfo> topSkills);

    /* JADX INFO: compiled from: BrainRouter.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static BrainResult continueAfterTool(BrainRouter $this, UserRequest originalRequest, List<String> history, List<SkillInfo> topSkills, String lastToolId, String lastToolMessage) {
            Intrinsics.checkNotNullParameter(originalRequest, "originalRequest");
            Intrinsics.checkNotNullParameter(history, "history");
            Intrinsics.checkNotNullParameter(topSkills, "topSkills");
            Intrinsics.checkNotNullParameter(lastToolId, "lastToolId");
            Intrinsics.checkNotNullParameter(lastToolMessage, "lastToolMessage");
            return BrainRouter.super.continueAfterTool(originalRequest, history, topSkills, lastToolId, lastToolMessage);
        }
    }

    default BrainResult continueAfterTool(UserRequest originalRequest, List<String> history, List<SkillInfo> topSkills, String lastToolId, String lastToolMessage) {
        Intrinsics.checkNotNullParameter(originalRequest, "originalRequest");
        Intrinsics.checkNotNullParameter(history, "history");
        Intrinsics.checkNotNullParameter(topSkills, "topSkills");
        Intrinsics.checkNotNullParameter(lastToolId, "lastToolId");
        Intrinsics.checkNotNullParameter(lastToolMessage, "lastToolMessage");
        return null;
    }

    /* JADX INFO: compiled from: BrainRouter.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\t0\u0007HÆ\u0003JG\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0007HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011¨\u0006\u001f"}, d2 = {"Lcom/jarvis/brain/BrainRouter$SkillInfo;", "", "id", "", "name", "description", "examples", "", "parameters", "Lcom/jarvis/brain/BrainRouter$SkillParam;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getId", "()Ljava/lang/String;", "getName", "getDescription", "getExamples", "()Ljava/util/List;", "getParameters", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class SkillInfo {
        private final String description;
        private final List<String> examples;
        private final String id;
        private final String name;
        private final List<SkillParam> parameters;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SkillInfo copy$default(SkillInfo skillInfo, String str, String str2, String str3, List list, List list2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = skillInfo.id;
            }
            if ((i & 2) != 0) {
                str2 = skillInfo.name;
            }
            String str4 = str2;
            if ((i & 4) != 0) {
                str3 = skillInfo.description;
            }
            String str5 = str3;
            if ((i & 8) != 0) {
                list = skillInfo.examples;
            }
            List list3 = list;
            if ((i & 16) != 0) {
                list2 = skillInfo.parameters;
            }
            return skillInfo.copy(str, str4, str5, list3, list2);
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

        public final List<SkillParam> component5() {
            return this.parameters;
        }

        public final SkillInfo copy(String id, String name, String description, List<String> examples, List<SkillParam> parameters) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(description, "description");
            Intrinsics.checkNotNullParameter(examples, "examples");
            Intrinsics.checkNotNullParameter(parameters, "parameters");
            return new SkillInfo(id, name, description, examples, parameters);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SkillInfo)) {
                return false;
            }
            SkillInfo skillInfo = (SkillInfo) other;
            return Intrinsics.areEqual(this.id, skillInfo.id) && Intrinsics.areEqual(this.name, skillInfo.name) && Intrinsics.areEqual(this.description, skillInfo.description) && Intrinsics.areEqual(this.examples, skillInfo.examples) && Intrinsics.areEqual(this.parameters, skillInfo.parameters);
        }

        public int hashCode() {
            return (((((((this.id.hashCode() * 31) + this.name.hashCode()) * 31) + this.description.hashCode()) * 31) + this.examples.hashCode()) * 31) + this.parameters.hashCode();
        }

        public String toString() {
            return "SkillInfo(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", examples=" + this.examples + ", parameters=" + this.parameters + ")";
        }

        public SkillInfo(String id, String name, String description, List<String> examples, List<SkillParam> parameters) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(description, "description");
            Intrinsics.checkNotNullParameter(examples, "examples");
            Intrinsics.checkNotNullParameter(parameters, "parameters");
            this.id = id;
            this.name = name;
            this.description = description;
            this.examples = examples;
            this.parameters = parameters;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public /* synthetic */ SkillInfo(String str, String str2, String str3, List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            List listEmptyList;
            if ((i & 16) == 0) {
                listEmptyList = list2;
            } else {
                listEmptyList = CollectionsKt.emptyList();
            }
            this(str, str2, str3, list, listEmptyList);
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

        public final List<SkillParam> getParameters() {
            return this.parameters;
        }
    }

    /* JADX INFO: compiled from: BrainRouter.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\tHÆ\u0003JA\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\tHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u00072\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lcom/jarvis/brain/BrainRouter$SkillParam;", "", "name", "", "description", "type", "required", "", "allowedValues", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/util/List;)V", "getName", "()Ljava/lang/String;", "getDescription", "getType", "getRequired", "()Z", "getAllowedValues", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class SkillParam {
        private final List<String> allowedValues;
        private final String description;
        private final String name;
        private final boolean required;
        private final String type;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SkillParam copy$default(SkillParam skillParam, String str, String str2, String str3, boolean z, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = skillParam.name;
            }
            if ((i & 2) != 0) {
                str2 = skillParam.description;
            }
            String str4 = str2;
            if ((i & 4) != 0) {
                str3 = skillParam.type;
            }
            String str5 = str3;
            if ((i & 8) != 0) {
                z = skillParam.required;
            }
            boolean z2 = z;
            if ((i & 16) != 0) {
                list = skillParam.allowedValues;
            }
            return skillParam.copy(str, str4, str5, z2, list);
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
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getRequired() {
            return this.required;
        }

        public final List<String> component5() {
            return this.allowedValues;
        }

        public final SkillParam copy(String name, String description, String type, boolean required, List<String> allowedValues) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(description, "description");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(allowedValues, "allowedValues");
            return new SkillParam(name, description, type, required, allowedValues);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SkillParam)) {
                return false;
            }
            SkillParam skillParam = (SkillParam) other;
            return Intrinsics.areEqual(this.name, skillParam.name) && Intrinsics.areEqual(this.description, skillParam.description) && Intrinsics.areEqual(this.type, skillParam.type) && this.required == skillParam.required && Intrinsics.areEqual(this.allowedValues, skillParam.allowedValues);
        }

        public int hashCode() {
            return (((((((this.name.hashCode() * 31) + this.description.hashCode()) * 31) + this.type.hashCode()) * 31) + Boolean.hashCode(this.required)) * 31) + this.allowedValues.hashCode();
        }

        public String toString() {
            return "SkillParam(name=" + this.name + ", description=" + this.description + ", type=" + this.type + ", required=" + this.required + ", allowedValues=" + this.allowedValues + ")";
        }

        public SkillParam(String name, String description, String type, boolean required, List<String> allowedValues) {
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

        public /* synthetic */ SkillParam(String str, String str2, String str3, boolean z, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i & 4) != 0 ? "STRING" : str3, (i & 8) != 0 ? false : z, (i & 16) != 0 ? CollectionsKt.emptyList() : list);
        }

        public final String getName() {
            return this.name;
        }

        public final String getDescription() {
            return this.description;
        }

        public final String getType() {
            return this.type;
        }

        public final boolean getRequired() {
            return this.required;
        }

        public final List<String> getAllowedValues() {
            return this.allowedValues;
        }
    }

    /* JADX INFO: compiled from: BrainRouter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/jarvis/brain/BrainRouter$Companion;", "", "<init>", "()V", "MAX_SKILLS", "", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final int MAX_SKILLS = 8;

        private Companion() {
        }
    }
}
