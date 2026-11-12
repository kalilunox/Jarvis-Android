package com.jarvis.core.storage;

import com.jarvis.app.VoiceService;
import com.jarvis.core.companion.Sensitivity;
import com.jarvis.core.privacy.SensitivityClassifier;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: FactPolicies.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bJ\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\bJ\u0010\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\bJ\u000e\u0010\u0011\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bJ\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\bJ\u000e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/jarvis/core/storage/FactPolicies;", "", "<init>", "()V", "MIN_LENGTH", "", "MAX_LENGTH", "MIGRATED_FLAG", "", "LEGACY_DB_NAME", "SEED_SOURCE", "normalizeForDedup", VoiceService.EXTRA_TEXT, "isStorable", "", "shouldMigrateSource", "source", "diskText", "sensitivityOf", "Lcom/jarvis/core/companion/Sensitivity;", "isSecret", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FactPolicies {
    public static final FactPolicies INSTANCE = new FactPolicies();
    public static final String LEGACY_DB_NAME = "jarvis_memory.db";
    public static final int MAX_LENGTH = 240;
    public static final String MIGRATED_FLAG = "migrated_facts_v1";
    public static final int MIN_LENGTH = 3;
    public static final String SEED_SOURCE = "seed";

    private FactPolicies() {
    }

    public final String normalizeForDedup(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        String lowerCase = text.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String s = StringsKt.trim((CharSequence) new Regex("\\s+").replace(lowerCase, StringUtils.SPACE)).toString();
        return StringsKt.replace$default(StringsKt.trimEnd(s, '.', ',', '!', '?', ';', ':', Typography.ellipsis, ' '), (char) 1105, (char) 1077, false, 4, (Object) null);
    }

    public final boolean isStorable(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        String t = StringsKt.trim((CharSequence) text).toString();
        int length = t.length();
        return 3 <= length && length < 241;
    }

    public final boolean shouldMigrateSource(String source) {
        String lowerCase;
        String string;
        if (source == null || (string = StringsKt.trim((CharSequence) source).toString()) == null) {
            lowerCase = null;
        } else {
            lowerCase = string.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        }
        return !Intrinsics.areEqual(lowerCase, SEED_SOURCE);
    }

    public final String diskText(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        return SensitivityClassifier.INSTANCE.mask(text);
    }

    public final Sensitivity sensitivityOf(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        return SensitivityClassifier.classify$default(SensitivityClassifier.INSTANCE, text, null, 2, null);
    }

    public final boolean isSecret(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        return sensitivityOf(text) == Sensitivity.SECRET;
    }
}
