package com.jarvis.core.skills;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.jarvis.core.common.model.ParameterType;
import com.jarvis.core.common.model.RiskLevel;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: SkillManifestParser.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0007H\u0002¨\u0006\u000e"}, d2 = {"Lcom/jarvis/core/skills/SkillManifestParser;", "", "<init>", "()V", "parse", "Lcom/jarvis/core/skills/SkillManifest;", "json", "", "parseType", "Lcom/jarvis/core/common/model/ParameterType;", "type", "parseRisk", "Lcom/jarvis/core/common/model/RiskLevel;", "risk", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SkillManifestParser {
    public static final SkillManifestParser INSTANCE = new SkillManifestParser();

    private SkillManifestParser() {
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0112  */
    public final SkillManifest parse(String json) throws JSONException {
        List listEmptyList;
        int i;
        List listEmptyList2;
        Intrinsics.checkNotNullParameter(json, "json");
        JSONObject root = new JSONObject(json);
        JSONArray params = root.optJSONArray("parameters");
        List $this$parse_u24lambda_u240 = CollectionsKt.createListBuilder();
        int i2 = 0;
        if (params != null) {
            int i3 = 0;
            int length = params.length();
            while (i3 < length) {
                JSONObject p = params.getJSONObject(i3);
                String string = p.getString("name");
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String strOptString = p.optString("description", "");
                Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
                SkillManifestParser skillManifestParser = INSTANCE;
                JSONArray params2 = params;
                String strOptString2 = p.optString("type", TypedValues.Custom.S_STRING);
                Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
                ParameterType type = skillManifestParser.parseType(strOptString2);
                boolean zOptBoolean = p.optBoolean("required", false);
                JSONArray arr = p.optJSONArray("allowedValues");
                if (arr != null) {
                    List $this$parse_u24lambda_u240_u240_u240 = CollectionsKt.createListBuilder();
                    int length2 = arr.length();
                    i = i2;
                    int j = 0;
                    while (j < length2) {
                        int i4 = length2;
                        String string2 = arr.getString(j);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                        $this$parse_u24lambda_u240_u240_u240.add(string2);
                        j++;
                        length2 = i4;
                        arr = arr;
                    }
                    listEmptyList2 = CollectionsKt.build($this$parse_u24lambda_u240_u240_u240);
                    if (listEmptyList2 != null) {
                    }
                    $this$parse_u24lambda_u240.add(new SkillManifest.ManifestParameter(string, strOptString, type, zOptBoolean, listEmptyList2));
                    i3++;
                    params = params2;
                    i2 = i;
                } else {
                    i = i2;
                }
                listEmptyList2 = CollectionsKt.emptyList();
                $this$parse_u24lambda_u240.add(new SkillManifest.ManifestParameter(string, strOptString, type, zOptBoolean, listEmptyList2));
                i3++;
                params = params2;
                i2 = i;
            }
        }
        List parameters = CollectionsKt.build($this$parse_u24lambda_u240);
        String string3 = root.getString("id");
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        String string4 = root.getString("name");
        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        String string5 = root.getString("description");
        Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
        JSONArray arr2 = root.optJSONArray("examples");
        if (arr2 != null) {
            List $this$parse_u24lambda_u241_u240 = CollectionsKt.createListBuilder();
            int length3 = arr2.length();
            for (int i5 = 0; i5 < length3; i5++) {
                String string6 = arr2.getString(i5);
                Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                $this$parse_u24lambda_u241_u240.add(string6);
            }
            List listBuild = CollectionsKt.build($this$parse_u24lambda_u241_u240);
            if (listBuild != null) {
                listEmptyList = listBuild;
            } else {
                listEmptyList = CollectionsKt.emptyList();
            }
        } else {
            listEmptyList = CollectionsKt.emptyList();
        }
        String strOptString3 = root.optString("riskLevel", "low");
        Intrinsics.checkNotNullExpressionValue(strOptString3, "optString(...)");
        return new SkillManifest(string3, string4, string5, listEmptyList, parameters, parseRisk(strOptString3), root.optBoolean("onlineRequired", false));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0037, code lost:
    
        if (r0.equals("bool") == false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0043, code lost:
    
        if (r0.equals("int") == false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:?, code lost:
    
        return com.jarvis.core.common.model.ParameterType.BOOLEAN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:?, code lost:
    
        return com.jarvis.core.common.model.ParameterType.INTEGER;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0019, code lost:
    
        if (r0.equals(androidx.constraintlayout.core.motion.utils.TypedValues.Custom.S_INT) == false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0022, code lost:
    
        if (r0.equals(androidx.constraintlayout.core.motion.utils.TypedValues.Custom.S_BOOLEAN) == false) goto L23;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.jarvis.core.common.model.ParameterType parseType(java.lang.String r3) {
        /*
            r2 = this;
            java.util.Locale r0 = java.util.Locale.ROOT
            java.lang.String r0 = r3.toLowerCase(r0)
            java.lang.String r1 = "toLowerCase(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            int r1 = r0.hashCode()
            switch(r1) {
                case 104431: goto L3d;
                case 3029738: goto L31;
                case 3118337: goto L25;
                case 64711720: goto L1c;
                case 1958052158: goto L13;
                default: goto L12;
            }
        L12:
            goto L49
        L13:
            java.lang.String r1 = "integer"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L46
            goto L12
        L1c:
            java.lang.String r1 = "boolean"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L3a
            goto L12
        L25:
            java.lang.String r1 = "enum"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L2e
            goto L12
        L2e:
            com.jarvis.core.common.model.ParameterType r0 = com.jarvis.core.common.model.ParameterType.ENUM
            goto L4b
        L31:
            java.lang.String r1 = "bool"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L3a
            goto L12
        L3a:
            com.jarvis.core.common.model.ParameterType r0 = com.jarvis.core.common.model.ParameterType.BOOLEAN
            goto L4b
        L3d:
            java.lang.String r1 = "int"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L46
            goto L12
        L46:
            com.jarvis.core.common.model.ParameterType r0 = com.jarvis.core.common.model.ParameterType.INTEGER
            goto L4b
        L49:
            com.jarvis.core.common.model.ParameterType r0 = com.jarvis.core.common.model.ParameterType.STRING
        L4b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jarvis.core.skills.SkillManifestParser.parseType(java.lang.String):com.jarvis.core.common.model.ParameterType");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final RiskLevel parseRisk(String risk) {
        String lowerCase = risk.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        switch (lowerCase.hashCode()) {
            case -1078030475:
                if (lowerCase.equals("medium")) {
                    return RiskLevel.LOW;
                }
                break;
            case -21437972:
                if (lowerCase.equals("blocked")) {
                    return RiskLevel.BLOCKED;
                }
                break;
            case 465391254:
                if (lowerCase.equals("sensitive")) {
                    return RiskLevel.SENSITIVE;
                }
                break;
            case 951117504:
                if (lowerCase.equals("confirm")) {
                    return RiskLevel.CONFIRM;
                }
                break;
        }
        return RiskLevel.SAFE;
    }
}
