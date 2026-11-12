package com.jarvis.core.cloud;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: CloudErrorClassifier.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rJ\u001f\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/jarvis/core/cloud/CloudErrorClassifier;", "", "<init>", "()V", "DAILY_RE", "Lkotlin/text/Regex;", "GEMINI_RETRY_RE", "GROQ_RETRY_RE", "GROQ_SEGMENT_RE", "parseRetryAfterSeconds", "", "body", "", "(Ljava/lang/String;)Ljava/lang/Integer;", "classify", "Lcom/jarvis/core/cloud/ErrorClass;", "httpStatus", "(Ljava/lang/Integer;Ljava/lang/String;)Lcom/jarvis/core/cloud/ErrorClass;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CloudErrorClassifier {
    public static final CloudErrorClassifier INSTANCE = new CloudErrorClassifier();
    private static final Regex DAILY_RE = new Regex("per\\s*day|daily|\\brpd\\b|\\btpd\\b|requests per day|tokens per day", RegexOption.IGNORE_CASE);
    private static final Regex GEMINI_RETRY_RE = new Regex("\"retryDelay\"\\s*:\\s*\"(\\d+(?:\\.\\d+)?)s\"");
    private static final Regex GROQ_RETRY_RE = new Regex("try again in ((?:\\d+(?:\\.\\d+)?\\s*(?:ms|s|m)\\s*)+)", RegexOption.IGNORE_CASE);
    private static final Regex GROQ_SEGMENT_RE = new Regex("(\\d+(?:\\.\\d+)?)\\s*(ms|s|m)", RegexOption.IGNORE_CASE);

    private CloudErrorClassifier() {
    }

    public final Integer parseRetryAfterSeconds(String body) {
        double d;
        if (body == null) {
            return null;
        }
        MatchResult m = Regex.find$default(GEMINI_RETRY_RE, body, 0, 2, null);
        int i = 1;
        if (m == null) {
            MatchResult m2 = Regex.find$default(GROQ_RETRY_RE, body, 0, 2, null);
            if (m2 == null) {
                return null;
            }
            double seconds = 0.0d;
            Sequence $this$forEach$iv = Regex.findAll$default(GROQ_SEGMENT_RE, m2.getGroupValues().get(1), 0, 2, null);
            for (Object element$iv : $this$forEach$iv) {
                MatchResult seg = (MatchResult) element$iv;
                double value = Double.parseDouble(seg.getGroupValues().get(i));
                String lowerCase = seg.getGroupValues().get(2).toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                if (Intrinsics.areEqual(lowerCase, "ms")) {
                    d = value / 1000.0d;
                } else {
                    d = Intrinsics.areEqual(lowerCase, "m") ? 60.0d * value : value;
                }
                seconds += d;
                i = 1;
            }
            return Integer.valueOf((int) Math.ceil(seconds));
        }
        return Integer.valueOf((int) Math.ceil(Double.parseDouble(m.getGroupValues().get(1))));
    }

    public final ErrorClass classify(Integer httpStatus, String body) {
        String text = body == null ? "" : body;
        if (httpStatus == null) {
            return Intrinsics.areEqual(text, "missing_key") ? ErrorClass.AUTH_OR_REGION : ErrorClass.NETWORK_ERROR;
        }
        if (httpStatus.intValue() == 403 && StringsKt.contains$default((CharSequence) text, (CharSequence) "1010", false, 2, (Object) null)) {
            return ErrorClass.BLOCKED_BY_CLOUDFLARE;
        }
        if (httpStatus.intValue() == 401) {
            return ErrorClass.AUTH_OR_REGION;
        }
        if (httpStatus.intValue() == 403) {
            String lower = text.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lower, "toLowerCase(...)");
            if (StringsKt.contains$default((CharSequence) lower, (CharSequence) "api key not valid", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) lower, (CharSequence) "api_key_invalid", false, 2, (Object) null)) {
                return ErrorClass.AUTH_OR_REGION;
            }
            return ErrorClass.FORBIDDEN;
        }
        if (httpStatus.intValue() == 429) {
            return DAILY_RE.containsMatchIn(text) ? ErrorClass.RATE_LIMITED_DAILY : ErrorClass.RATE_LIMITED;
        }
        if (httpStatus.intValue() == 503) {
            return ErrorClass.OVERLOADED;
        }
        if (httpStatus.intValue() == 500 || httpStatus.intValue() == 502 || httpStatus.intValue() == 504) {
            return ErrorClass.SERVER_ERROR;
        }
        if (httpStatus.intValue() == 400) {
            String lower2 = text.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lower2, "toLowerCase(...)");
            if (StringsKt.contains$default((CharSequence) lower2, (CharSequence) "api key not valid", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) lower2, (CharSequence) "api_key_invalid", false, 2, (Object) null)) {
                return ErrorClass.AUTH_OR_REGION;
            }
            return StringsKt.contains$default((CharSequence) lower2, (CharSequence) "location is not supported", false, 2, (Object) null) ? ErrorClass.FORBIDDEN : ErrorClass.BAD_REQUEST;
        }
        return ErrorClass.OTHER;
    }
}
