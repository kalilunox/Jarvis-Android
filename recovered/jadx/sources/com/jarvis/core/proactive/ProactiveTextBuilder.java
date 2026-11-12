package com.jarvis.core.proactive;

import com.jarvis.app.VoiceService;
import com.jarvis.core.privacy.SensitivityClassifier;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.apache.commons.codec.language.Soundex;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: ProactiveTextBuilder.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005J\u0010\u0010\u000e\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005H\u0002J\u0010\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/jarvis/core/proactive/ProactiveTextBuilder;", "", "<init>", "()V", "ADDRESS", "", "MAX_VOICE_CHARS", "", "MAX_REASON_CHARS", "build", "Lcom/jarvis/core/proactive/ProactiveText;", "type", "Lcom/jarvis/core/proactive/ProactiveDecisionType;", "reason", "cleanReason", "defaultReason", "clamp", VoiceService.EXTRA_TEXT, "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ProactiveTextBuilder {
    public static final String ADDRESS = "";
    public static final ProactiveTextBuilder INSTANCE = new ProactiveTextBuilder();
    private static final int MAX_REASON_CHARS = 100;
    public static final int MAX_VOICE_CHARS = 160;

    /* JADX INFO: compiled from: ProactiveTextBuilder.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ProactiveDecisionType.values().length];
            try {
                iArr[ProactiveDecisionType.SUGGESTION.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ProactiveDecisionType.QUESTION.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ProactiveDecisionType.CONFIRMATION_REQUEST.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[ProactiveDecisionType.NO_OP.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private ProactiveTextBuilder() {
    }

    public final ProactiveText build(ProactiveDecisionType type, String reason) {
        ProactiveText text;
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(reason, "reason");
        if (type == ProactiveDecisionType.NO_OP) {
            return new ProactiveText("", "", "");
        }
        String strCleanReason = cleanReason(reason);
        if (StringsKt.isBlank(strCleanReason)) {
            strCleanReason = INSTANCE.defaultReason(type);
        }
        String cleaned = strCleanReason;
        String address = StringsKt.isBlank("") ? "" : ", ";
        switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
            case 1:
                text = new ProactiveText(address + "Я заметил: " + cleaned + ". Хочешь, помогу?", "Jarvis: предложение", cleaned + ". Хочешь, помогу?");
                break;
            case 2:
                text = new ProactiveText(address + "Можно вопрос: " + cleaned + "?", "Jarvis: вопрос", cleaned + "?");
                break;
            case 3:
                text = new ProactiveText(address + "Подтверди, пожалуйста: " + cleaned + ".", "Jarvis: подтверждение", cleaned + ". Подтверждаешь?");
                break;
            case 4:
                text = new ProactiveText("", "", "");
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        return ProactiveText.copy$default(text, clamp(text.getVoice()), null, null, 6, null);
    }

    private final String cleanReason(String reason) {
        String strSubstring;
        String head;
        String masked = StringsKt.trim((CharSequence) new Regex("\\s+").replace(SensitivityClassifier.INSTANCE.mask(reason), StringUtils.SPACE)).toString();
        if (masked.length() == 0) {
            return "";
        }
        if (masked.length() <= 100) {
            head = masked;
        } else {
            String head2 = StringsKt.take(masked, 100);
            int lastSpace = StringsKt.lastIndexOf$default((CharSequence) head2, ' ', 0, false, 6, (Object) null);
            if (lastSpace > 40) {
                strSubstring = head2.substring(0, lastSpace);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            } else {
                strSubstring = head2;
            }
            head = StringsKt.trimEnd((CharSequence) strSubstring).toString();
        }
        return StringsKt.trimEnd(head, '.', ',', ';', ':', '!', '?', Typography.mdash, Typography.ndash, Soundex.SILENT_MARKER);
    }

    private final String defaultReason(ProactiveDecisionType type) {
        switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
            case 1:
                return "похоже, тебе может пригодиться помощь";
            case 2:
                return "есть кое-что, что стоит уточнить";
            case 3:
                return "нужно твоё подтверждение";
            case 4:
                return "";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final String clamp(String text) {
        String normalized = StringsKt.trim((CharSequence) new Regex("\\s+").replace(text, StringUtils.SPACE)).toString();
        if (normalized.length() <= 160) {
            return normalized;
        }
        return StringsKt.trimEnd((CharSequence) StringsKt.take(normalized, 159)).toString() + "…";
    }
}
