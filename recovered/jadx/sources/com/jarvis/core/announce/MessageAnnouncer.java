package com.jarvis.core.announce;

import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Announcements.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/jarvis/core/announce/MessageAnnouncer;", "", "minGapMs", "", "<init>", "(J)V", "lastSpokenAt", "lastMessage", "", "phrase", "message", "Lcom/jarvis/core/announce/IncomingMessage;", "nowMs", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MessageAnnouncer {
    private static final long DEFAULT_GAP_MS = 20000;
    private static final int MAX_TEXT = 160;
    private String lastMessage;
    private long lastSpokenAt;
    private final long minGapMs;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Regex COUNTER = new Regex("\\s*\\(\\d+[^)]*\\)\\s*$");
    private static final Map<String, String> APPS = MapsKt.mapOf(TuplesKt.to("org.telegram.messenger", "Телеграме"), TuplesKt.to("org.telegram.messenger.web", "Телеграме"), TuplesKt.to("org.thunderdog.challegram", "Телеграме"), TuplesKt.to("com.whatsapp", "Ватсапе"), TuplesKt.to("com.vkontakte.android", "ВКонтакте"), TuplesKt.to("com.viber.voip", "Вайбере"), TuplesKt.to("com.google.android.apps.messaging", "сообщениях"), TuplesKt.to("com.android.mms", "сообщениях"));

    public MessageAnnouncer() {
        this(0L, 1, null);
    }

    public MessageAnnouncer(long minGapMs) {
        this.minGapMs = minGapMs;
        this.lastSpokenAt = -4611686018427387904L;
        this.lastMessage = "";
    }

    public /* synthetic */ MessageAnnouncer(long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? DEFAULT_GAP_MS : j);
    }

    public final String phrase(IncomingMessage message, long nowMs) {
        Intrinsics.checkNotNullParameter(message, "message");
        String app = APPS.get(message.getAppPackage());
        if (app == null) {
            return null;
        }
        String sender = INSTANCE.cleanSender(message.getSender());
        if (message.getGroup() || StringsKt.isBlank(sender) || StringsKt.isBlank(message.getText())) {
            return null;
        }
        String id = sender + "|" + message.getText();
        if (Intrinsics.areEqual(id, this.lastMessage) || nowMs - this.lastSpokenAt < this.minGapMs) {
            return null;
        }
        this.lastMessage = id;
        this.lastSpokenAt = nowMs;
        String text = StringsKt.take(StringsKt.trimEnd(StringsKt.trim((CharSequence) message.getText()).toString(), '.'), 160);
        return "Сэр, " + sender + " в " + app + ": " + text + ".";
    }

    /* JADX INFO: compiled from: Announcements.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/jarvis/core/announce/MessageAnnouncer$Companion;", "", "<init>", "()V", "DEFAULT_GAP_MS", "", "MAX_TEXT", "", "COUNTER", "Lkotlin/text/Regex;", "APPS", "", "", "cleanSender", "raw", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String cleanSender(String raw) {
            Intrinsics.checkNotNullParameter(raw, "raw");
            return StringsKt.trim((CharSequence) MessageAnnouncer.COUNTER.replace(raw, "")).toString();
        }
    }
}
