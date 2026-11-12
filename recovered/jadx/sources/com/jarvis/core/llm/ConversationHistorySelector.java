package com.jarvis.core.llm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ConversationHistorySelector.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bJ \u0010\f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0006H\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/llm/ConversationHistorySelector;", "", "<init>", "()V", "followUpMarkers", "", "", "select", "lines", "userText", "budget", "Lcom/jarvis/core/llm/LlmContextBudget;", "referenceHint", "lastAssistant", "prevUser", "looksLikeReferenceFollowUp", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ConversationHistorySelector {
    public static final ConversationHistorySelector INSTANCE = new ConversationHistorySelector();
    private static final List<String> followUpMarkers = CollectionsKt.listOf((Object[]) new String[]{"первый", "второй", "третий", "четверт", "последн", "вариант", "сравни", "дешевле", "дороже", "их", "оба", "обе", "этот", "тот", "а теперь", "а под", "ещё раз", "еще раз", "продолж", "подробнее"});

    /* JADX INFO: compiled from: ConversationHistorySelector.kt */
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

    private ConversationHistorySelector() {
    }

    public final List<String> select(List<String> lines, String userText, LlmContextBudget budget) {
        int maxTurns;
        Intrinsics.checkNotNullParameter(lines, "lines");
        Intrinsics.checkNotNullParameter(userText, "userText");
        Intrinsics.checkNotNullParameter(budget, "budget");
        List<String> $this$filter$iv = lines;
        Collection destination$iv$iv = new ArrayList();
        Iterator it = $this$filter$iv.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object element$iv$iv = it.next();
            String it2 = (String) element$iv$iv;
            if (StringsKt.startsWith(it2, "user:", true) || StringsKt.startsWith(it2, "assistant:", true)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List turns = (List) destination$iv$iv;
        if (turns.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        switch (WhenMappings.$EnumSwitchMapping$0[budget.ordinal()]) {
            case 1:
                maxTurns = 4;
                break;
            case 2:
                maxTurns = 8;
                break;
            case 3:
                maxTurns = 12;
                break;
            case 4:
                maxTurns = 16;
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        List recent = CollectionsKt.toMutableList((Collection) CollectionsKt.takeLast(turns, maxTurns));
        if (looksLikeReferenceFollowUp(userText) && turns.size() > recent.size()) {
            List older = CollectionsKt.takeLast(CollectionsKt.dropLast(turns, recent.size()), 2);
            recent.addAll(0, older);
        }
        return CollectionsKt.take(CollectionsKt.distinct(recent), maxTurns + 2);
    }

    public final String referenceHint(String userText, String lastAssistant, String prevUser) {
        Intrinsics.checkNotNullParameter(userText, "userText");
        Intrinsics.checkNotNullParameter(lastAssistant, "lastAssistant");
        Intrinsics.checkNotNullParameter(prevUser, "prevUser");
        if (!looksLikeReferenceFollowUp(userText)) {
            return null;
        }
        List $this$referenceHint_u24lambda_u240 = CollectionsKt.createListBuilder();
        if (!StringsKt.isBlank(prevUser)) {
            $this$referenceHint_u24lambda_u240.add("предыдущий запрос: «" + StringsKt.take(prevUser, 120) + "»");
        }
        if (!StringsKt.isBlank(lastAssistant)) {
            $this$referenceHint_u24lambda_u240.add("предыдущий ответ: «" + StringsKt.take(lastAssistant, 280) + "»");
        }
        List parts = CollectionsKt.build($this$referenceHint_u24lambda_u240);
        List list = !parts.isEmpty() ? parts : null;
        if (list != null) {
            return CollectionsKt.joinToString$default(list, "; ", null, null, 0, null, null, 62, null);
        }
        return null;
    }

    private final boolean looksLikeReferenceFollowUp(String userText) {
        boolean z;
        String t = userText.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(t, "toLowerCase(...)");
        if (t.length() > 120) {
            return false;
        }
        Iterable $this$any$iv = followUpMarkers;
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                Object element$iv = it.next();
                String it2 = (String) element$iv;
                if (StringsKt.contains$default((CharSequence) t, (CharSequence) it2, false, 2, (Object) null)) {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
        }
        return z || StringsKt.startsWith$default(t, "а ", false, 2, (Object) null) || StringsKt.startsWith$default(t, "ну ", false, 2, (Object) null);
    }
}
