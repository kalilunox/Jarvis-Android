package com.jarvis.core.aioutput;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;

/* JADX INFO: compiled from: StructuredAiModels.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\"\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lcom/jarvis/core/aioutput/StructuredAiLimits;", "", "<init>", "()V", "SCHEMA_VERSION", "", "MAX_JSON_CHARS", "", "MAX_TEXT_CHARS", "MAX_QUESTION_CHARS", "MAX_ARGS", "MAX_ARG_KEY", "MAX_ARG_VALUE", "MAX_PLAN_STEPS", "MAX_NESTING_DEPTH", "MAX_REPAIR_ATTEMPTS", "MAX_GOAL_CHARS", "MAX_TOOL_ID", "FORBIDDEN_ARG_KEYS", "", "getFORBIDDEN_ARG_KEYS", "()Ljava/util/Set;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class StructuredAiLimits {
    public static final int MAX_ARGS = 12;
    public static final int MAX_ARG_KEY = 48;
    public static final int MAX_ARG_VALUE = 96;
    public static final int MAX_GOAL_CHARS = 160;
    public static final int MAX_JSON_CHARS = 4096;
    public static final int MAX_NESTING_DEPTH = 4;
    public static final int MAX_PLAN_STEPS = 16;
    public static final int MAX_QUESTION_CHARS = 400;
    public static final int MAX_REPAIR_ATTEMPTS = 1;
    public static final int MAX_TEXT_CHARS = 1200;
    public static final int MAX_TOOL_ID = 64;
    public static final String SCHEMA_VERSION = "1";
    public static final StructuredAiLimits INSTANCE = new StructuredAiLimits();
    private static final Set<String> FORBIDDEN_ARG_KEYS = SetsKt.setOf((Object[]) new String[]{"allow", "permission", "autonomy", "confirmed", "confirmation", "bypass", "policy", "risk", "token", "api_key", "password", "secret", "success", "executed", "override"});

    private StructuredAiLimits() {
    }

    public final Set<String> getFORBIDDEN_ARG_KEYS() {
        return FORBIDDEN_ARG_KEYS;
    }
}
