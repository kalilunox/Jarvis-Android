package com.jarvis.screencontrol;

import com.jarvis.core.privacy.SensitivityClassifier;
import kotlin.Metadata;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: NotificationPrivacy.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\n\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0005J\u001a\u0010\u000e\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/jarvis/screencontrol/NotificationPrivacy;", "", "<init>", "()V", "MASK", "", "MAX_CACHE_ENTRIES", "", "MAX_TEXT_CHARS", "SUMMARY_TITLE_CHARS", "sanitize", "raw", "", "packageName", "summaryTitle", "screen-control_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NotificationPrivacy {
    public static final NotificationPrivacy INSTANCE = new NotificationPrivacy();
    public static final String MASK = "[скрыто]";
    public static final int MAX_CACHE_ENTRIES = 50;
    public static final int MAX_TEXT_CHARS = 200;
    private static final int SUMMARY_TITLE_CHARS = 40;

    private NotificationPrivacy() {
    }

    public final String sanitize(CharSequence raw, String packageName) {
        String text = raw != null ? raw.toString() : null;
        if (text == null) {
            text = "";
        }
        if (SensitivityClassifier.INSTANCE.isSensitivePackage(packageName)) {
            return StringsKt.isBlank(text) ? "" : "[скрыто]";
        }
        return StringsKt.take(SensitivityClassifier.INSTANCE.mask(text), 200);
    }

    public final String summaryTitle(CharSequence raw, String packageName) {
        String safe = sanitize(raw, packageName);
        String it = StringsKt.take(safe, 40);
        return safe.length() > 40 ? it + "…" : it;
    }
}
