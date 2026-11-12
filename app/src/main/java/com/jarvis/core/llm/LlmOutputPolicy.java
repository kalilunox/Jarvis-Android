package com.jarvis.core.llm;

import com.jarvis.app.VoiceService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: LlmOutputPolicy.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007J\u001d\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lcom/jarvis/core/llm/LlmOutputPolicy;", "", "<init>", "()V", "maxChars", "", "budget", "Lcom/jarvis/core/llm/LlmContextBudget;", "apply", "", "raw", "sentenceAwareTruncate", VoiceService.EXTRA_TEXT, "limit", "sentenceAwareTruncate$core_debug", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LlmOutputPolicy {
    public static final LlmOutputPolicy INSTANCE = new LlmOutputPolicy();

    /* JADX INFO: compiled from: LlmOutputPolicy.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LlmContextBudget.values().length];
            try {
                iArr[LlmContextBudget.FAST.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[LlmContextBudget.BALANCED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[LlmContextBudget.QUALITY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[LlmContextBudget.REASONING.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private LlmOutputPolicy() {
    }

    public final int maxChars(LlmContextBudget budget) {
        Intrinsics.checkNotNullParameter(budget, "budget");
        switch (WhenMappings.$EnumSwitchMapping$0[budget.ordinal()]) {
            case 1:
                return 280;
            case 2:
                return 480;
            case 3:
                return 720;
            case 4:
                return 960;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final String apply(String raw, LlmContextBudget budget) {
        Intrinsics.checkNotNullParameter(raw, "raw");
        Intrinsics.checkNotNullParameter(budget, "budget");
        String t = StringsKt.trim((CharSequence) raw).toString();
        if (StringsKt.isBlank(t)) {
            return t;
        }
        String t2 = new Regex("(?i)^ANSWER\\s*[:|\\-–]\\s*").replace(StringsKt.trim((CharSequence) new Regex("(?i)^(RESULT|TOOL|STATUS)\\s*=\\s*\\S+.*").replace(t, "")).toString(), "");
        int limit = maxChars(budget);
        return t2.length() <= limit ? t2 : sentenceAwareTruncate$core_debug(t2, limit);
    }

    public final String sentenceAwareTruncate$core_debug(String text, int limit) {
        String out;
        Intrinsics.checkNotNullParameter(text, "text");
        if (text.length() <= limit) {
            return text;
        }
        Iterable $this$filter$iv = new Regex("(?<=[.!?…])\\s+").split(text, 0);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            if (!StringsKt.isBlank(it)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List parts = (List) destination$iv$iv;
        if (parts.isEmpty()) {
            return StringsKt.trimEnd((CharSequence) StringsKt.take(text, limit)).toString() + "…";
        }
        List $this$filter$iv2 = parts;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv2 : $this$filter$iv2) {
            String it2 = (String) element$iv$iv2;
            if (CollectionsKt.contains(SetsKt.setOf((Object[]) new Character[]{'.', '!', '?', Character.valueOf(Typography.ellipsis)}), StringsKt.lastOrNull(it2))) {
                destination$iv$iv2.add(element$iv$iv2);
            }
        }
        List complete = (List) destination$iv$iv2;
        if (!complete.isEmpty()) {
            out = CollectionsKt.joinToString$default(CollectionsKt.take(complete, 3), StringUtils.SPACE, null, null, 0, null, null, 62, null);
        } else {
            out = CollectionsKt.joinToString$default(CollectionsKt.take(parts, 2), StringUtils.SPACE, null, null, 0, null, null, 62, null);
        }
        if (out.length() > limit) {
            String out2 = StringsKt.trimEnd(StringsKt.take(out, limit - 1), ',', ';', ':', ' ');
            return !StringsKt.endsWith$default((CharSequence) out2, Typography.ellipsis, false, 2, (Object) null) ? out2 + "…" : out2;
        }
        return out;
    }
}
