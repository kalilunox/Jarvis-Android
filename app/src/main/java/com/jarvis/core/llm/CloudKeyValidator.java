package com.jarvis.core.llm;

import java.util.Base64;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: CloudKeyValidator.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\u0007\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\t\u001a\u00020\n2\b\u0010\b\u001a\u0004\u0018\u00010\u0005J\u0016\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rJ\u001e\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005¨\u0006\u0012"}, d2 = {"Lcom/jarvis/core/llm/CloudKeyValidator;", "", "<init>", "()V", "normalizeProviderId", "", "id", "sanitizeKey", "key", "isStubKey", "", "encodeEnvelope", "iv", "", "ciphertext", "decodeEnvelope", "Lkotlin/Pair;", "value", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CloudKeyValidator {
    public static final CloudKeyValidator INSTANCE = new CloudKeyValidator();

    private CloudKeyValidator() {
    }

    public final String normalizeProviderId(String id) {
        String lowerCase;
        String string;
        if (id == null || (string = StringsKt.trim((CharSequence) id).toString()) == null) {
            lowerCase = null;
        } else {
            lowerCase = string.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        }
        return lowerCase == null ? "" : lowerCase;
    }

    public final String sanitizeKey(String key) {
        if (key == null) {
            return "";
        }
        String cleaned = StringsKt.trim((CharSequence) StringsKt.replace$default(StringsKt.replace$default(StringsKt.trim((CharSequence) key).toString(), StringUtils.CR, "", false, 4, (Object) null), StringUtils.LF, "", false, 4, (Object) null)).toString();
        if (cleaned.length() >= 2 && ((StringsKt.startsWith$default((CharSequence) cleaned, Typography.quote, false, 2, (Object) null) && StringsKt.endsWith$default((CharSequence) cleaned, Typography.quote, false, 2, (Object) null)) || (StringsKt.startsWith$default((CharSequence) cleaned, '\'', false, 2, (Object) null) && StringsKt.endsWith$default((CharSequence) cleaned, '\'', false, 2, (Object) null)))) {
            String strSubstring = cleaned.substring(1, cleaned.length() - 1);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            cleaned = StringsKt.trim((CharSequence) strSubstring).toString();
        }
        return StringsKt.trim((CharSequence) StringsKt.replace$default(StringsKt.replace$default(cleaned, StringUtils.CR, "", false, 4, (Object) null), StringUtils.LF, "", false, 4, (Object) null)).toString();
    }

    public final boolean isStubKey(String key) {
        String sanitized = sanitizeKey(key);
        return StringsKt.isBlank(sanitized) || Intrinsics.areEqual(sanitized, "...") || StringsKt.contains$default((CharSequence) sanitized, Typography.less, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) sanitized, Typography.greater, false, 2, (Object) null) || sanitized.length() < 20;
    }

    public final String encodeEnvelope(byte[] iv, byte[] ciphertext) {
        Intrinsics.checkNotNullParameter(iv, "iv");
        Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
        return Base64.getEncoder().encodeToString(iv) + ":" + Base64.getEncoder().encodeToString(ciphertext);
    }

    public final Pair<byte[], byte[]> decodeEnvelope(String value) {
        int separator;
        Object objM392constructorimpl;
        Pair pair;
        String str = value;
        if ((str == null || StringsKt.isBlank(str)) || (separator = StringsKt.indexOf$default((CharSequence) value, ':', 0, false, 6, (Object) null)) <= 0 || separator == StringsKt.getLastIndex(value)) {
            return null;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            CloudKeyValidator cloudKeyValidator = this;
            Base64.Decoder decoder = Base64.getDecoder();
            String strSubstring = value.substring(0, separator);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            byte[] iv = decoder.decode(strSubstring);
            Base64.Decoder decoder2 = Base64.getDecoder();
            String strSubstring2 = value.substring(separator + 1);
            Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
            byte[] ciphertext = decoder2.decode(strSubstring2);
            Intrinsics.checkNotNull(iv);
            if (!(iv.length == 0)) {
                Intrinsics.checkNotNull(ciphertext);
                pair = ciphertext.length == 0 ? null : TuplesKt.to(iv, ciphertext);
            }
            objM392constructorimpl = Result.m392constructorimpl(pair);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        return (Pair) (Result.m398isFailureimpl(objM392constructorimpl) ? null : objM392constructorimpl);
    }
}
