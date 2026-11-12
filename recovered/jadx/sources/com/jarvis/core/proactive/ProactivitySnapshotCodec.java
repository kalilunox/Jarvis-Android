package com.jarvis.core.proactive;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: ProactivitySnapshotCodec.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0005J\u001c\u0010\u000b\u001a\u00020\u00052\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\rH\u0002J$\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0005H\u0002J\u0010\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/jarvis/core/proactive/ProactivitySnapshotCodec;", "", "<init>", "()V", "VERSION", "", "encode", "state", "Lcom/jarvis/core/proactive/ProactivityState;", "decode", "raw", "encodeMap", "map", "", "", "decodeMap", "max", "", "esc", "s", "unesc", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ProactivitySnapshotCodec {
    public static final ProactivitySnapshotCodec INSTANCE = new ProactivitySnapshotCodec();
    private static final String VERSION = "v1";

    private ProactivitySnapshotCodec() {
    }

    public final String encode(ProactivityState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        StringBuilder $this$encode_u24lambda_u240 = new StringBuilder();
        $this$encode_u24lambda_u240.append(VERSION).append('\n');
        $this$encode_u24lambda_u240.append("lastDecisionAt=" + state.getLastDecisionAtMillis()).append('\n');
        ProactiveDecisionType lastDecisionType = state.getLastDecisionType();
        String strName = lastDecisionType != null ? lastDecisionType.name() : null;
        if (strName == null) {
            strName = "";
        }
        $this$encode_u24lambda_u240.append("lastDecisionType=" + strName).append('\n');
        $this$encode_u24lambda_u240.append("globalCooldownUntil=" + state.getGlobalCooldownUntilMillis()).append('\n');
        $this$encode_u24lambda_u240.append("budgetTokens=" + state.getBudgetTokens()).append('\n');
        $this$encode_u24lambda_u240.append("budgetLastRefillAt=" + state.getBudgetLastRefillAtMillis()).append('\n');
        $this$encode_u24lambda_u240.append("suggestionCount=" + state.getSuggestionCount()).append('\n');
        $this$encode_u24lambda_u240.append("questionCount=" + state.getQuestionCount()).append('\n');
        $this$encode_u24lambda_u240.append("confirmationCount=" + state.getConfirmationCount()).append('\n');
        $this$encode_u24lambda_u240.append("recordedNoOpCount=" + state.getRecordedNoOpCount()).append('\n');
        $this$encode_u24lambda_u240.append("behaviorCooldown=" + INSTANCE.encodeMap(state.getBehaviorCooldownUntil())).append('\n');
        $this$encode_u24lambda_u240.append("fingerprintCooldown=" + INSTANCE.encodeMap(state.getFingerprintCooldownUntil())).append('\n');
        $this$encode_u24lambda_u240.append("rejectCooldown=" + INSTANCE.encodeMap(state.getRejectCooldownUntil())).append('\n');
        return $this$encode_u24lambda_u240.toString();
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final ProactivityState decode(String raw) {
        boolean z;
        String str;
        ProactiveDecisionType proactiveDecisionType;
        Object objM392constructorimpl;
        ProactivitySnapshotCodec proactivitySnapshotCodec = this;
        Intrinsics.checkNotNullParameter(raw, "raw");
        if (StringsKt.isBlank(raw)) {
            return ProactivityState.Companion.initial$default(ProactivityState.INSTANCE, null, 1, null);
        }
        List lines = SequencesKt.toList(SequencesKt.filter(SequencesKt.map(StringsKt.lineSequence(raw), new Function1() { // from class: com.jarvis.core.proactive.ProactivitySnapshotCodec$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ProactivitySnapshotCodec.decode$lambda$0((String) obj);
            }
        }), new Function1() { // from class: com.jarvis.core.proactive.ProactivitySnapshotCodec$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ProactivitySnapshotCodec.decode$lambda$1((String) obj));
            }
        }));
        if (lines.isEmpty() || !Intrinsics.areEqual(lines.get(0), VERSION)) {
            return ProactivityState.Companion.initial$default(ProactivityState.INSTANCE, null, 1, null);
        }
        int budgetTokens = ProactivityConfig.INSTANCE.getDEFAULT().getBudgetMaxTokens();
        Map<String, Long> mapEmptyMap = MapsKt.emptyMap();
        Map<String, Long> mapEmptyMap2 = MapsKt.emptyMap();
        Map<String, Long> mapEmptyMap3 = MapsKt.emptyMap();
        Iterator it = CollectionsKt.drop(lines, 1).iterator();
        Map<String, Long> mapDecodeMap = mapEmptyMap3;
        Map<String, Long> mapDecodeMap2 = mapEmptyMap2;
        Map<String, Long> mapDecodeMap3 = mapEmptyMap;
        int recordedNoOpCount = 0;
        int confirmationCount = 0;
        int questionCount = 0;
        int suggestionCount = 0;
        long budgetLastRefillAt = 0;
        int budgetTokens2 = budgetTokens;
        long globalCooldownUntil = 0;
        long lastDecisionAt = 0;
        ProactiveDecisionType lastDecisionType = null;
        while (true) {
            ProactiveDecisionType lastDecisionType2 = lastDecisionType;
            if (!it.hasNext()) {
                return new ProactivityState(RangesKt.coerceAtLeast(lastDecisionAt, 0L), lastDecisionType2, RangesKt.coerceAtLeast(globalCooldownUntil, 0L), mapDecodeMap3, mapDecodeMap2, mapDecodeMap, budgetTokens2, RangesKt.coerceAtLeast(budgetLastRefillAt, 0L), suggestionCount, questionCount, confirmationCount, recordedNoOpCount);
            }
            String line = (String) it.next();
            int eq = StringsKt.indexOf$default((CharSequence) line, '=', 0, false, 6, (Object) null);
            if (eq > 0) {
                String key = line.substring(0, eq);
                Intrinsics.checkNotNullExpressionValue(key, "substring(...)");
                String value = line.substring(eq + 1);
                Intrinsics.checkNotNullExpressionValue(value, "substring(...)");
                switch (key.hashCode()) {
                    case -1675478677:
                        if (key.equals("suggestionCount")) {
                            Integer intOrNull = StringsKt.toIntOrNull(value);
                            suggestionCount = RangesKt.coerceAtLeast(intOrNull != null ? intOrNull.intValue() : 0, 0);
                            lastDecisionType = lastDecisionType2;
                            proactivitySnapshotCodec = this;
                        }
                        break;
                    case -1668359873:
                        if (key.equals("budgetTokens")) {
                            Integer intOrNull2 = StringsKt.toIntOrNull(value);
                            budgetTokens2 = RangesKt.coerceAtMost(RangesKt.coerceIn(intOrNull2 != null ? intOrNull2.intValue() : ProactivityConfig.INSTANCE.getDEFAULT().getBudgetMaxTokens(), 0, ProactivityConfig.INSTANCE.getDEFAULT().getBudgetMaxTokens() * 4), ProactivityConfig.INSTANCE.getDEFAULT().getBudgetMaxTokens());
                            proactivitySnapshotCodec = this;
                            lastDecisionType = lastDecisionType2;
                        }
                        break;
                    case -1595777947:
                        if (key.equals("lastDecisionAt")) {
                            Long longOrNull = StringsKt.toLongOrNull(value);
                            lastDecisionAt = longOrNull != null ? longOrNull.longValue() : 0L;
                            lastDecisionType = lastDecisionType2;
                        }
                        break;
                    case -1056332599:
                        if (key.equals("questionCount")) {
                            Integer intOrNull3 = StringsKt.toIntOrNull(value);
                            questionCount = RangesKt.coerceAtLeast(intOrNull3 != null ? intOrNull3.intValue() : 0, 0);
                            lastDecisionType = lastDecisionType2;
                        }
                        break;
                    case -422236049:
                        if (key.equals("fingerprintCooldown")) {
                            mapDecodeMap2 = proactivitySnapshotCodec.decodeMap(value, ProactivityConfig.INSTANCE.getDEFAULT().getMaxFingerprints());
                            lastDecisionType = lastDecisionType2;
                        }
                        break;
                    case -238707988:
                        if (key.equals("lastDecisionType")) {
                            if (!(value.length() > 0)) {
                                str = null;
                            }
                            if (str != null) {
                                str = value;
                                String it2 = str;
                                ProactivitySnapshotCodec proactivitySnapshotCodec2 = INSTANCE;
                                try {
                                    Result.Companion companion = Result.INSTANCE;
                                    objM392constructorimpl = Result.m392constructorimpl(ProactiveDecisionType.valueOf(it2));
                                } catch (Throwable th) {
                                    Result.Companion companion2 = Result.INSTANCE;
                                    objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
                                }
                                if (Result.m398isFailureimpl(objM392constructorimpl)) {
                                    objM392constructorimpl = null;
                                }
                                proactiveDecisionType = (ProactiveDecisionType) objM392constructorimpl;
                            } else {
                                str = value;
                                proactiveDecisionType = null;
                            }
                            lastDecisionType = proactiveDecisionType;
                            break;
                        }
                        break;
                    case 62644176:
                        if (key.equals("globalCooldownUntil")) {
                            Long longOrNull2 = StringsKt.toLongOrNull(value);
                            globalCooldownUntil = longOrNull2 != null ? longOrNull2.longValue() : 0L;
                            lastDecisionType = lastDecisionType2;
                        }
                        break;
                    case 167791012:
                        if (key.equals("budgetLastRefillAt")) {
                            Long longOrNull3 = StringsKt.toLongOrNull(value);
                            budgetLastRefillAt = longOrNull3 != null ? longOrNull3.longValue() : 0L;
                            lastDecisionType = lastDecisionType2;
                        }
                        break;
                    case 653578282:
                        if (key.equals("rejectCooldown")) {
                            mapDecodeMap = proactivitySnapshotCodec.decodeMap(value, ProactivityConfig.INSTANCE.getDEFAULT().getMaxRejectCooldowns());
                            z = false;
                            lastDecisionType = lastDecisionType2;
                            proactivitySnapshotCodec = this;
                        }
                        break;
                    case 1231672285:
                        if (key.equals("recordedNoOpCount")) {
                            Integer intOrNull4 = StringsKt.toIntOrNull(value);
                            recordedNoOpCount = RangesKt.coerceAtLeast(intOrNull4 != null ? intOrNull4.intValue() : 0, 0);
                            lastDecisionType = lastDecisionType2;
                        }
                        break;
                    case 1248734682:
                        if (key.equals("confirmationCount")) {
                            Integer intOrNull5 = StringsKt.toIntOrNull(value);
                            confirmationCount = RangesKt.coerceAtLeast(intOrNull5 != null ? intOrNull5.intValue() : 0, 0);
                            lastDecisionType = lastDecisionType2;
                        }
                        break;
                    case 1651878877:
                        if (key.equals("behaviorCooldown")) {
                            mapDecodeMap3 = proactivitySnapshotCodec.decodeMap(value, ProactivityConfig.INSTANCE.getDEFAULT().getMaxBehaviorCooldowns());
                            lastDecisionType = lastDecisionType2;
                        }
                        break;
                    default:
                        z = false;
                        lastDecisionType = lastDecisionType2;
                        proactivitySnapshotCodec = this;
                        break;
                }
            }
            lastDecisionType = lastDecisionType2;
            proactivitySnapshotCodec = this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String decode$lambda$0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return StringsKt.trim((CharSequence) it).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean decode$lambda$1(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.length() > 0;
    }

    private final String encodeMap(Map<String, Long> map) {
        return CollectionsKt.joinToString$default(map.entrySet(), ",", null, null, 0, null, new Function1() { // from class: com.jarvis.core.proactive.ProactivitySnapshotCodec$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ProactivitySnapshotCodec.encodeMap$lambda$0((Map.Entry) obj);
            }
        }, 30, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence encodeMap$lambda$0(Map.Entry it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return INSTANCE.esc((String) it.getKey()) + "@" + it.getValue();
    }

    private final Map<String, Long> decodeMap(String raw, int max) {
        int at;
        if (StringsKt.isBlank(raw)) {
            return MapsKt.emptyMap();
        }
        LinkedHashMap out = new LinkedHashMap();
        for (String part : StringsKt.split$default((CharSequence) raw, new char[]{','}, false, 0, 6, (Object) null)) {
            if (!StringsKt.isBlank(part) && (at = StringsKt.lastIndexOf$default((CharSequence) part, '@', 0, false, 6, (Object) null)) > 0) {
                String strSubstring = part.substring(0, at);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                String k = unesc(strSubstring);
                String strSubstring2 = part.substring(at + 1);
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                Long longOrNull = StringsKt.toLongOrNull(strSubstring2);
                if (longOrNull != null) {
                    long v = longOrNull.longValue();
                    if (k.length() > 0) {
                        out.put(k, Long.valueOf(v));
                    }
                }
            }
        }
        return ProactivityState.INSTANCE.boundMap(out, max);
    }

    private final String esc(String s) {
        return StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(s, "\\", "\\\\", false, 4, (Object) null), ",", "\\,", false, 4, (Object) null), "@", "\\@", false, 4, (Object) null), StringUtils.LF, "\\n", false, 4, (Object) null);
    }

    private final String unesc(String s) {
        StringBuilder out = new StringBuilder(s.length());
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '\\' && i + 1 < s.length()) {
                if (s.charAt(i + 1) == 'n') {
                    out.append('\n');
                } else {
                    out.append(s.charAt(i + 1));
                }
                i += 2;
            } else {
                out.append(s.charAt(i));
                i++;
            }
        }
        String string = out.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }
}
