package com.jarvis.core.verification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: DefaultResultVerifier.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002J\u0012\u0010\f\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002J\u0012\u0010\r\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002¨\u0006\u000e"}, d2 = {"Lcom/jarvis/core/verification/DefaultResultVerifier;", "Lcom/jarvis/core/verification/ResultVerifier;", "<init>", "()V", "verify", "Lcom/jarvis/core/verification/VerificationResult;", "request", "Lcom/jarvis/core/verification/VerificationRequest;", "isToolSuccess", "", "status", "", "isToolFailure", "isRetryableStatus", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DefaultResultVerifier implements ResultVerifier {
    @Override // com.jarvis.core.verification.ResultVerifier
    public VerificationResult verify(VerificationRequest request) {
        int count$iv;
        VerificationStatus status;
        boolean z;
        Iterable $this$none$iv;
        int $i$f$none;
        boolean z2;
        Intrinsics.checkNotNullParameter(request, "request");
        boolean toolOk = isToolSuccess(request.getToolResultStatus());
        boolean toolFailed = isToolFailure(request.getToolResultStatus());
        if (toolFailed) {
            if (request.getIrreversibleAction()) {
                VerificationStatus verificationStatus = VerificationStatus.IRREVERSIBLE;
                String[] strArr = new String[2];
                strArr[0] = "tool_failed_irreversible";
                String toolResultMessage = request.getToolResultMessage();
                strArr[1] = StringsKt.take(toolResultMessage != null ? toolResultMessage : "", 80);
                return new VerificationResult(verificationStatus, null, null, null, CollectionsKt.listOf((Object[]) strArr), 14, null);
            }
            if (isRetryableStatus(request.getToolResultStatus())) {
                return new VerificationResult(VerificationStatus.RETRYABLE, null, null, null, CollectionsKt.listOf((Object[]) new String[]{"tool_failed_retryable", "status=" + request.getToolResultStatus()}), 14, null);
            }
            VerificationStatus verificationStatus2 = VerificationStatus.FAILURE;
            String[] strArr2 = new String[3];
            strArr2[0] = "tool_failed";
            strArr2[1] = "status=" + request.getToolResultStatus();
            String toolResultMessage2 = request.getToolResultMessage();
            strArr2[2] = StringsKt.take(toolResultMessage2 != null ? toolResultMessage2 : "", 80);
            return new VerificationResult(verificationStatus2, null, null, null, CollectionsKt.listOf((Object[]) strArr2), 14, null);
        }
        List<ExpectedEffect> expectedEffects = request.getExpectedEffects();
        if (expectedEffects.isEmpty()) {
            if (!toolOk) {
                String toolResultStatus = request.getToolResultStatus();
                if (!(toolResultStatus == null || StringsKt.isBlank(toolResultStatus))) {
                    return new VerificationResult(VerificationStatus.UNKNOWN, null, null, null, CollectionsKt.listOf((Object[]) new String[]{"no_expected_effects", "tool_status=" + request.getToolResultStatus()}), 14, null);
                }
            }
            return new VerificationResult(VerificationStatus.SUCCESS, null, null, null, CollectionsKt.listOf((Object[]) new String[]{"no_expected_effects", "tool_ok=" + toolOk}), 14, null);
        }
        ObservedState observed = request.getObserved();
        List<ExpectedEffect> $this$filter$iv = expectedEffects;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            ExpectedEffect it = (ExpectedEffect) element$iv$iv;
            if (it.getRequired()) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List required = (List) destination$iv$iv;
        if (observed == null) {
            if (required.isEmpty() && toolOk) {
                return new VerificationResult(VerificationStatus.SUCCESS, null, null, null, CollectionsKt.listOf((Object[]) new String[]{"optional_effects_only", "no_observed"}), 14, null);
            }
            VerificationStatus verificationStatus3 = VerificationStatus.UNKNOWN;
            List $this$map$iv = required;
            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                ExpectedEffect it2 = (ExpectedEffect) item$iv$iv;
                destination$iv$iv2.add(it2.getKey());
            }
            return new VerificationResult(verificationStatus3, null, null, (List) destination$iv$iv2, CollectionsKt.listOf((Object[]) new String[]{"no_observed_state", "required=" + required.size()}), 6, null);
        }
        List matched = new ArrayList();
        List mismatched = new ArrayList();
        List missing = new ArrayList();
        for (ExpectedEffect effect : expectedEffects) {
            String actual = observed.get(effect.getKey());
            if (actual == null) {
                actual = request.getToolResultData().get(effect.getKey());
            }
            if (actual == null) {
                if (effect.getRequired()) {
                    missing.add(effect.getKey());
                }
            } else if (StringsKt.equals(actual, effect.getExpectedValue(), true)) {
                matched.add(effect.getKey());
            } else {
                effect.getRequired();
                mismatched.add(effect.getKey() + ":expected=" + effect.getExpectedValue() + ":actual=" + actual);
            }
        }
        List $this$map$iv2 = required;
        Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
        for (Object item$iv$iv2 : $this$map$iv2) {
            ExpectedEffect it3 = (ExpectedEffect) item$iv$iv2;
            destination$iv$iv3.add(it3.getKey());
            $this$map$iv2 = $this$map$iv2;
        }
        Set requiredKeys = CollectionsKt.toSet((List) destination$iv$iv3);
        List $this$count$iv = matched;
        if (($this$count$iv instanceof Collection) && $this$count$iv.isEmpty()) {
            count$iv = 0;
        } else {
            count$iv = 0;
            for (Object element$iv : $this$count$iv) {
                String it4 = (String) element$iv;
                if (requiredKeys.contains(it4) && (count$iv = count$iv + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        int requiredMatched = count$iv;
        int requiredTotal = requiredKeys.size();
        if (missing.isEmpty()) {
            List $this$none$iv2 = mismatched;
            int $i$f$none2 = 0;
            if (($this$none$iv2 instanceof Collection) && $this$none$iv2.isEmpty()) {
                z = true;
            } else {
                Iterator it5 = $this$none$iv2.iterator();
                while (true) {
                    if (!it5.hasNext()) {
                        z = true;
                        break;
                    }
                    Object element$iv2 = it5.next();
                    boolean toolFailed2 = toolFailed;
                    String line = (String) element$iv2;
                    List required2 = required;
                    Iterable $this$any$iv = requiredKeys;
                    Set requiredKeys2 = requiredKeys;
                    if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
                        $this$none$iv = $this$none$iv2;
                        $i$f$none = $i$f$none2;
                        z2 = false;
                    } else {
                        Iterator it6 = $this$any$iv.iterator();
                        while (true) {
                            if (!it6.hasNext()) {
                                $this$none$iv = $this$none$iv2;
                                $i$f$none = $i$f$none2;
                                z2 = false;
                                break;
                            }
                            Object element$iv3 = it6.next();
                            Iterator it7 = it6;
                            String it8 = (String) element$iv3;
                            Iterable $this$any$iv2 = $this$any$iv;
                            $this$none$iv = $this$none$iv2;
                            $i$f$none = $i$f$none2;
                            if (StringsKt.startsWith$default(line, it8 + ":", false, 2, (Object) null)) {
                                z2 = true;
                                break;
                            }
                            it6 = it7;
                            $this$any$iv = $this$any$iv2;
                            $this$none$iv2 = $this$none$iv;
                            $i$f$none2 = $i$f$none;
                        }
                    }
                    if (z2) {
                        z = false;
                        break;
                    }
                    toolFailed = toolFailed2;
                    required = required2;
                    requiredKeys = requiredKeys2;
                    $this$none$iv2 = $this$none$iv;
                    $i$f$none2 = $i$f$none;
                }
            }
            if (z && requiredMatched == requiredTotal && matched.size() == expectedEffects.size()) {
                status = VerificationStatus.SUCCESS;
            }
            return new VerificationResult(status, matched, mismatched, missing, CollectionsKt.listOf((Object[]) new String[]{"tool_ok=" + toolOk, "observed_source=" + observed.getSource(), "facts=" + observed.getFacts().size()}));
        }
        if (missing.isEmpty() && requiredMatched == requiredTotal && mismatched.isEmpty()) {
            status = VerificationStatus.SUCCESS;
        } else if (requiredMatched > 0 && !(missing.isEmpty() && mismatched.isEmpty() && requiredMatched >= requiredTotal)) {
            status = VerificationStatus.PARTIAL_SUCCESS;
        } else if (requiredTotal == 0 && !matched.isEmpty() && mismatched.isEmpty() && missing.isEmpty()) {
            status = VerificationStatus.SUCCESS;
        } else if (requiredTotal != 0 || matched.isEmpty()) {
            status = (missing.isEmpty() && mismatched.isEmpty()) ? VerificationStatus.UNKNOWN : VerificationStatus.FAILURE;
        } else {
            status = VerificationStatus.PARTIAL_SUCCESS;
        }
        return new VerificationResult(status, matched, mismatched, missing, CollectionsKt.listOf((Object[]) new String[]{"tool_ok=" + toolOk, "observed_source=" + observed.getSource(), "facts=" + observed.getFacts().size()}));
    }

    private final boolean isToolSuccess(String status) {
        String str = status;
        if (str == null || StringsKt.isBlank(str)) {
            return false;
        }
        String s = status.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(s, "toUpperCase(...)");
        return Intrinsics.areEqual(s, "SUCCESS") || Intrinsics.areEqual(s, "DRY_RUN_READY") || Intrinsics.areEqual(s, "OK");
    }

    private final boolean isToolFailure(String status) {
        String str = status;
        if (str == null || StringsKt.isBlank(str)) {
            return false;
        }
        String s = status.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(s, "toUpperCase(...)");
        return SetsKt.setOf((Object[]) new String[]{"FAILED", "FAILURE", "DENIED", "ERROR", "BLOCKED"}).contains(s);
    }

    private final boolean isRetryableStatus(String status) {
        String str = status;
        if (str == null || StringsKt.isBlank(str)) {
            return false;
        }
        String s = status.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(s, "toUpperCase(...)");
        return SetsKt.setOf((Object[]) new String[]{"FAILED", "FAILURE", "UNAVAILABLE", "OFFLINE", "TIMEOUT"}).contains(s);
    }
}
