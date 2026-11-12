package com.jarvis.brain.llm;

import com.google.android.gms.common.Scopes;
import com.jarvis.brain.LongTermMemory;
import com.jarvis.brain.LongTermMemoryInterface;
import com.jarvis.core.common.model.ConversationContext;
import com.jarvis.core.common.model.PendingAction;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.companion.PhoneContext;
import com.jarvis.core.discovery.CatalogToolDiscovery;
import com.jarvis.core.discovery.DiscoveredItem;
import com.jarvis.core.discovery.DiscoveryContext;
import com.jarvis.core.discovery.DiscoveryResult;
import com.jarvis.core.llm.ContextBuildInput;
import com.jarvis.core.llm.LlmContextBudget;
import com.jarvis.core.llm.LlmContextKind;
import com.jarvis.core.llm.MemoryContextRetriever;
import com.jarvis.core.llm.MemorySnippet;
import com.jarvis.core.memory.Memory2Record;
import com.jarvis.core.memory.Memory2Store;
import com.jarvis.core.memory.MemoryEntry;
import com.jarvis.core.memory.MemoryRecall;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: LlmContextAssembly.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \"2\u00020\u0001:\u0001\"BI\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b¢\u0006\u0004\b\r\u0010\u000eJX\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00142\u0006\u0010\u0015\u001a\u00020\u00122\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00122\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001eJ\u0018\u0010\u001f\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\u0019H\u0002R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/jarvis/brain/llm/LlmContextAssembly;", "", "longTermMemory", "Lcom/jarvis/brain/LongTermMemoryInterface;", "companionRecall", "Lcom/jarvis/core/memory/MemoryRecall;", "memory2", "Lcom/jarvis/core/memory/Memory2Store;", "toolDiscovery", "Lcom/jarvis/core/discovery/CatalogToolDiscovery;", "phoneContext", "Lkotlin/Function0;", "Lcom/jarvis/core/companion/PhoneContext;", "<init>", "(Lcom/jarvis/brain/LongTermMemoryInterface;Lcom/jarvis/core/memory/MemoryRecall;Lcom/jarvis/core/memory/Memory2Store;Lcom/jarvis/core/discovery/CatalogToolDiscovery;Lkotlin/jvm/functions/Function0;)V", "gatherInput", "Lcom/jarvis/core/llm/ContextBuildInput;", "userText", "", "conversationLines", "", "persona", "kind", "Lcom/jarvis/core/llm/LlmContextKind;", "budget", "Lcom/jarvis/core/llm/LlmContextBudget;", "cloudMemoryAllowed", "", "chatProfile", "pendingContext", "Lcom/jarvis/core/common/model/ConversationContext;", "budgetFromProfile", Scopes.PROFILE, "fallback", "Companion", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LlmContextAssembly {

    @Deprecated
    public static final int ABOUT_USER_FACTS = 12;
    private static final Companion Companion = new Companion(null);
    private final MemoryRecall companionRecall;
    private final LongTermMemoryInterface longTermMemory;
    private final Memory2Store memory2;
    private final Function0<PhoneContext> phoneContext;
    private final CatalogToolDiscovery toolDiscovery;

    public LlmContextAssembly() {
        this(null, null, null, null, null, 31, null);
    }

    public LlmContextAssembly(LongTermMemoryInterface longTermMemory, MemoryRecall companionRecall, Memory2Store memory2, CatalogToolDiscovery toolDiscovery, Function0<PhoneContext> phoneContext) {
        Intrinsics.checkNotNullParameter(phoneContext, "phoneContext");
        this.longTermMemory = longTermMemory;
        this.companionRecall = companionRecall;
        this.memory2 = memory2;
        this.toolDiscovery = toolDiscovery;
        this.phoneContext = phoneContext;
    }

    public /* synthetic */ LlmContextAssembly(LongTermMemoryInterface longTermMemoryInterface, MemoryRecall memoryRecall, Memory2Store memory2Store, CatalogToolDiscovery catalogToolDiscovery, AnonymousClass1 anonymousClass1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : longTermMemoryInterface, (i & 2) != 0 ? null : memoryRecall, (i & 4) != 0 ? null : memory2Store, (i & 8) != 0 ? null : catalogToolDiscovery, (i & 16) != 0 ? new Function0() { // from class: com.jarvis.brain.llm.LlmContextAssembly.1
            @Override // kotlin.jvm.functions.Function0
            public final Void invoke() {
                return null;
            }
        } : anonymousClass1);
    }

    /* JADX WARN: Code duplicated, block: B:118:0x033a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:120:0x0309 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:77:0x02f0  */
    /* JADX WARN: Code duplicated, block: B:81:0x030f  */
    /* JADX WARN: Code duplicated, block: B:83:0x0319  */
    /* JADX WARN: Code duplicated, block: B:86:0x0333  */
    /* JADX WARN: Code duplicated, block: B:87:0x0335  */
    /* JADX WARN: Code duplicated, block: B:93:0x0351  */
    /* JADX WARN: Code duplicated, block: B:94:0x0358  */
    /* JADX WARN: Code duplicated, block: B:96:0x035c  */
    /* JADX WARN: Code duplicated, block: B:97:0x0363  */
    public final ContextBuildInput gatherInput(String userText, List<String> conversationLines, String persona, LlmContextKind kind, LlmContextBudget budget, boolean cloudMemoryAllowed, String chatProfile, ConversationContext pendingContext) {
        Iterable iterableTake;
        Iterable iterableEmptyList;
        char c;
        List listEmptyList;
        Collection destination$iv$iv;
        PendingAction pendingAction;
        ToolCall lastToolCall;
        MemorySnippet it;
        char c2;
        String[] strArr;
        Iterable items;
        Intrinsics.checkNotNullParameter(userText, "userText");
        Intrinsics.checkNotNullParameter(conversationLines, "conversationLines");
        Intrinsics.checkNotNullParameter(persona, "persona");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(budget, "budget");
        Intrinsics.checkNotNullParameter(chatProfile, "chatProfile");
        LlmContextBudget resolvedBudget = budgetFromProfile(chatProfile, budget);
        LongTermMemoryInterface longTermMemoryInterface = this.longTermMemory;
        Iterable iterableRetrieve = longTermMemoryInterface != null ? longTermMemoryInterface.retrieve(userText, 6) : null;
        if (iterableRetrieve == null) {
            iterableRetrieve = CollectionsKt.emptyList();
        }
        Iterable iterable = iterableRetrieve;
        if (MemoryContextRetriever.INSTANCE.isAboutUser(userText)) {
            List<LongTermMemory.MemoryFact> list = (Collection) iterable;
            LongTermMemoryInterface longTermMemoryInterface2 = this.longTermMemory;
            List<LongTermMemory.MemoryFact> listAll = longTermMemoryInterface2 != null ? longTermMemoryInterface2.all() : null;
            if (listAll == null) {
                listAll = CollectionsKt.emptyList();
            }
            Iterable $this$distinctBy$iv = CollectionsKt.plus((Collection) list, (Iterable) listAll);
            HashSet set$iv = new HashSet();
            ArrayList list$iv = new ArrayList();
            for (Object e$iv : $this$distinctBy$iv) {
                LongTermMemory.MemoryFact it2 = (LongTermMemory.MemoryFact) e$iv;
                if (set$iv.add(Long.valueOf(it2.getId()))) {
                    list$iv.add(e$iv);
                }
            }
            ArrayList $this$distinctBy$iv2 = list$iv;
            iterableTake = CollectionsKt.take($this$distinctBy$iv2, 12);
        } else {
            iterableTake = iterable;
        }
        Iterable $this$map$iv = iterableTake;
        int $i$f$map = 0;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        Iterator it3 = $this$map$iv.iterator();
        while (true) {
            String str = "PRIVATE";
            if (!it3.hasNext()) {
                break;
            }
            Object item$iv$iv = it3.next();
            LongTermMemory.MemoryFact it4 = (LongTermMemory.MemoryFact) item$iv$iv;
            String text = it4.getText();
            Iterable $this$map$iv2 = $this$map$iv;
            int $i$f$map2 = $i$f$map;
            if (!StringsKt.equals(it4.getCategory(), "private", true)) {
                str = "NORMAL";
            }
            destination$iv$iv2.add(new MemorySnippet(text, "long_term", str, 0.3f));
            $this$map$iv = $this$map$iv2;
            $i$f$map = $i$f$map2;
        }
        List lt = (List) destination$iv$iv2;
        MemoryRecall recall = this.companionRecall;
        if (recall != null) {
            int i = 0;
            String q = StringsKt.trim((CharSequence) userText).toString();
            List<MemoryEntry> listTextSearch = q.length() >= 3 ? recall.textSearch(q, 6) : CollectionsKt.emptyList();
            List prefs = CollectionsKt.take(recall.preferences(), 4);
            Iterable $this$distinctBy$iv3 = CollectionsKt.plus((Collection) listTextSearch, (Iterable) prefs);
            HashSet set$iv2 = new HashSet();
            ArrayList list$iv2 = new ArrayList();
            for (Object e$iv2 : $this$distinctBy$iv3) {
                MemoryEntry it5 = (MemoryEntry) e$iv2;
                if (set$iv2.add(it5.getId())) {
                    list$iv2.add(e$iv2);
                    recall = recall;
                } else {
                    recall = recall;
                }
            }
            ArrayList $this$map$iv3 = list$iv2;
            int $i$f$map3 = 0;
            Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv3, 10));
            for (Object item$iv$iv2 : $this$map$iv3) {
                MemoryEntry it6 = (MemoryEntry) item$iv$iv2;
                destination$iv$iv3.add(new MemorySnippet(it6.getContent(), "companion", it6.getSensitivity().name(), it6.getConfidence()));
                $this$map$iv3 = $this$map$iv3;
                $i$f$map3 = $i$f$map3;
                q = q;
                i = i;
                listTextSearch = listTextSearch;
            }
            iterableEmptyList = (List) destination$iv$iv3;
        } else {
            iterableEmptyList = null;
        }
        if (iterableEmptyList == null) {
            iterableEmptyList = CollectionsKt.emptyList();
        }
        Iterable companion = iterableEmptyList;
        Memory2Store memory2Store = this.memory2;
        Iterable iterableSearchRelevant = memory2Store != null ? memory2Store.searchRelevant(userText, 4) : null;
        if (iterableSearchRelevant == null) {
            iterableSearchRelevant = CollectionsKt.emptyList();
        }
        Iterable $this$map$iv4 = iterableSearchRelevant;
        Collection destination$iv$iv4 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv4, 10));
        for (Object item$iv$iv3 : $this$map$iv4) {
            Memory2Record it7 = (Memory2Record) item$iv$iv3;
            destination$iv$iv4.add(new MemorySnippet(it7.getText(), "memory2", null, it7.getScore(), 4, null));
        }
        List m2 = (List) destination$iv$iv4;
        CatalogToolDiscovery catalogToolDiscovery = this.toolDiscovery;
        if (catalogToolDiscovery != null) {
            c = 1;
            DiscoveryResult discoveryResultDiscover = catalogToolDiscovery.discover(new DiscoveryContext(userText, 6, null, null, null, false, false, 124, null));
            if (discoveryResultDiscover != null && (items = discoveryResultDiscover.getItems()) != null) {
                Iterable $this$map$iv5 = items;
                Collection destination$iv$iv5 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv5, 10));
                for (Object item$iv$iv4 : $this$map$iv5) {
                    DiscoveredItem item = (DiscoveredItem) item$iv$iv4;
                    destination$iv$iv5.add(item.getToolId() + ": " + StringsKt.take(item.getDescription(), 80));
                    $this$map$iv5 = $this$map$iv5;
                }
                listEmptyList = (List) destination$iv$iv5;
            }
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            List caps = listEmptyList;
            Iterable $this$filter$iv = companion;
            destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                it = (MemorySnippet) element$iv$iv;
                if (cloudMemoryAllowed) {
                    c2 = c;
                } else {
                    strArr = new String[2];
                    strArr[0] = "PRIVATE";
                    strArr[c] = "SECRET";
                    if (SetsKt.setOf((Object[]) strArr).contains(it.getSensitivity())) {
                        c2 = 0;
                    } else {
                        c2 = c;
                    }
                }
                if (c2 != 0) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            ArrayList arrayList = (List) destination$iv$iv;
            PhoneContext phoneContextInvoke = this.phoneContext.invoke();
            if (pendingContext != null) {
                pendingAction = pendingContext.getPendingAction();
            } else {
                pendingAction = null;
            }
            if (pendingContext != null) {
                lastToolCall = pendingContext.getLastToolCall();
            } else {
                lastToolCall = null;
            }
            return new ContextBuildInput(userText, kind, resolvedBudget, persona, conversationLines, lt, arrayList, m2, phoneContextInvoke, caps, null, null, pendingAction, lastToolCall, cloudMemoryAllowed, null, 35840, null);
        }
        c = 1;
        listEmptyList = null;
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        List caps2 = listEmptyList;
        Iterable $this$filter$iv2 = companion;
        destination$iv$iv = new ArrayList();
        while (r6.hasNext()) {
            it = (MemorySnippet) element$iv$iv;
            if (cloudMemoryAllowed) {
                strArr = new String[2];
                strArr[0] = "PRIVATE";
                strArr[c] = "SECRET";
                if (SetsKt.setOf((Object[]) strArr).contains(it.getSensitivity())) {
                    c2 = c;
                } else {
                    c2 = 0;
                }
            } else {
                c2 = c;
            }
            if (c2 != 0) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        ArrayList arrayList2 = (List) destination$iv$iv;
        PhoneContext phoneContextInvoke2 = this.phoneContext.invoke();
        if (pendingContext != null) {
            pendingAction = pendingContext.getPendingAction();
        } else {
            pendingAction = null;
        }
        if (pendingContext != null) {
            lastToolCall = pendingContext.getLastToolCall();
        } else {
            lastToolCall = null;
        }
        return new ContextBuildInput(userText, kind, resolvedBudget, persona, conversationLines, lt, arrayList2, m2, phoneContextInvoke2, caps2, null, null, pendingAction, lastToolCall, cloudMemoryAllowed, null, 35840, null);
    }

    /* JADX INFO: compiled from: LlmContextAssembly.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/jarvis/brain/llm/LlmContextAssembly$Companion;", "", "<init>", "()V", "ABOUT_USER_FACTS", "", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final LlmContextBudget budgetFromProfile(String profile, LlmContextBudget fallback) {
        String upperCase = profile.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        switch (upperCase.hashCode()) {
            case 2150492:
                if (upperCase.equals("FAST")) {
                    return LlmContextBudget.FAST;
                }
                return fallback;
            case 1369197791:
                if (upperCase.equals("QUALITY")) {
                    return LlmContextBudget.QUALITY;
                }
                return fallback;
            case 2117189374:
                if (upperCase.equals("REASONING")) {
                    return LlmContextBudget.REASONING;
                }
                return fallback;
            default:
                return fallback;
        }
    }
}
