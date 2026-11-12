package com.jarvis.core.behavior;

import com.jarvis.app.proactive.ProactiveActionReceiver;
import com.jarvis.app.ui.JarvisActivity;
import com.jarvis.core.companion.PhoneObservation;
import com.jarvis.core.companion.Sensitivity;
import com.jarvis.core.memory.CompanionMemoryService;
import com.jarvis.core.memory.MemoryKind;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: BehaviorLearningService.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B=\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007¢\u0006\u0004\b\u000b\u0010\fJ\u0006\u0010\u0002\u001a\u00020\u0003J.\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\bJ$\u0010\u0016\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0016\u001a\u00020\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001bJ\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00180\u000f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/jarvis/core/behavior/BehaviorLearningService;", "", "store", "Lcom/jarvis/core/behavior/BehaviorStore;", "policy", "Lcom/jarvis/core/behavior/BehaviorPolicy;", "clock", "Lkotlin/Function0;", "", "onStoreChanged", "", "<init>", "(Lcom/jarvis/core/behavior/BehaviorStore;Lcom/jarvis/core/behavior/BehaviorPolicy;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "lastEvaluateAt", "evaluate", "", "Lcom/jarvis/core/behavior/BehaviorCandidate;", "recent", "Lcom/jarvis/core/companion/PhoneObservation;", "force", "", "minIntervalMs", "feedback", ProactiveActionReceiver.EXTRA_PATTERN_KEY, "", "Lcom/jarvis/core/behavior/BehaviorFeedback;", JarvisActivity.MEMORY, "Lcom/jarvis/core/memory/CompanionMemoryService;", "relatedPatternKeys", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BehaviorLearningService {
    private final Function0<Long> clock;
    private volatile long lastEvaluateAt;
    private final Function0<Unit> onStoreChanged;
    private final BehaviorPolicy policy;
    private final BehaviorStore store;

    public BehaviorLearningService() {
        this(null, null, null, null, 15, null);
    }

    public BehaviorLearningService(BehaviorStore store, BehaviorPolicy policy, Function0<Long> clock, Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(store, "store");
        Intrinsics.checkNotNullParameter(policy, "policy");
        Intrinsics.checkNotNullParameter(clock, "clock");
        this.store = store;
        this.policy = policy;
        this.clock = clock;
        this.onStoreChanged = function0;
    }

    public /* synthetic */ BehaviorLearningService(BehaviorStore behaviorStore, BehaviorPolicy behaviorPolicy, Function0 function0, Function0 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new BehaviorStore() : behaviorStore, (i & 2) != 0 ? new BehaviorPolicy(0.0f, 0.0f, 0.0f, 7, null) : behaviorPolicy, (i & 4) != 0 ? new Function0() { // from class: com.jarvis.core.behavior.BehaviorLearningService$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Long.valueOf(System.currentTimeMillis());
            }
        } : function0, (i & 8) != 0 ? null : function1);
    }

    /* JADX INFO: renamed from: store, reason: from getter */
    public final BehaviorStore getStore() {
        return this.store;
    }

    public static /* synthetic */ List evaluate$default(BehaviorLearningService behaviorLearningService, List list, boolean z, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            j = 2000;
        }
        return behaviorLearningService.evaluate(list, z, j);
    }

    public final List<BehaviorCandidate> evaluate(List<PhoneObservation> recent, boolean force, long minIntervalMs) {
        Intrinsics.checkNotNullParameter(recent, "recent");
        long now = this.clock.invoke().longValue();
        if (!force && now - this.lastEvaluateAt < minIntervalMs && !this.store.suggestions(now).isEmpty()) {
            return this.store.suggestions(now);
        }
        this.lastEvaluateAt = now;
        List cleaned = BehaviorObservationSanitizer.dedupeEvidence$default(BehaviorObservationSanitizer.INSTANCE, BehaviorObservationSanitizer.INSTANCE.sanitizeForLearning(recent), 0L, 2, null);
        ObservationWindow window = ObservationWindow.INSTANCE.from(cleaned, (12 & 2) != 0 ? System.currentTimeMillis() : now, (12 & 4) != 0 ? 200 : 0, (12 & 8) != 0 ? 1209600000L : 0L);
        int i = 0;
        long j = 0;
        long j2 = 0;
        Iterable raw = CollectionsKt.plus((Collection) new PatternDetector(i, j, j2, this.clock, 7, null).detect(window), (Iterable) new RoutineDetector(i, 0, 0L, 0, this.clock, 15, null).detect(window));
        Iterable $this$map$iv = raw;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        Collection destination$iv$iv2 = destination$iv$iv;
        for (Object item$iv$iv : $this$map$iv) {
            BehaviorCandidate it = (BehaviorCandidate) item$iv$iv;
            Collection destination$iv$iv3 = destination$iv$iv2;
            destination$iv$iv3.add(BehaviorDecay.apply$default(BehaviorDecay.INSTANCE, it, now, 0.0d, 0.0f, 12, null));
            destination$iv$iv2 = destination$iv$iv3;
            $this$map$iv = $this$map$iv;
        }
        List decayed = (List) destination$iv$iv2;
        this.store.upsertSuggestions(this.policy.filter(decayed, this.store.disabledKeys(), this.store.rejectedKeys()), now);
        Function0<Unit> function0 = this.onStoreChanged;
        if (function0 != null) {
            function0.invoke();
        }
        return this.store.suggestions(now);
    }

    public static /* synthetic */ BehaviorCandidate feedback$default(BehaviorLearningService behaviorLearningService, String str, BehaviorFeedback behaviorFeedback, CompanionMemoryService companionMemoryService, int i, Object obj) {
        if ((i & 4) != 0) {
            companionMemoryService = null;
        }
        return behaviorLearningService.feedback(str, behaviorFeedback, companionMemoryService);
    }

    public final BehaviorCandidate feedback(String patternKey, BehaviorFeedback feedback, CompanionMemoryService memory) {
        MemoryKind kind;
        Intrinsics.checkNotNullParameter(patternKey, "patternKey");
        Intrinsics.checkNotNullParameter(feedback, "feedback");
        BehaviorCandidate primary = null;
        for (String key : relatedPatternKeys(patternKey)) {
            BehaviorCandidate updated = BehaviorStore.applyFeedback$default(this.store, key, feedback, this.clock.invoke().longValue(), 0L, 8, null);
            if (updated != null && primary == null) {
                primary = updated;
            }
        }
        if (primary == null) {
            return null;
        }
        Function0<Unit> function0 = this.onStoreChanged;
        if (function0 != null) {
            function0.invoke();
        }
        if (feedback == BehaviorFeedback.ACCEPT && memory != null) {
            if (primary.getKind() == BehaviorKind.ROUTINE) {
                kind = MemoryKind.ROUTINE;
            } else {
                kind = MemoryKind.SEMANTIC;
            }
            memory.rememberUserStatement(primary.getDescription(), kind, RangesKt.coerceAtLeast(primary.getConfidence(), 0.9f), Sensitivity.PRIVATE);
        }
        return primary;
    }

    private final List<String> relatedPatternKeys(String patternKey) {
        LinkedHashSet keys = SetsKt.linkedSetOf(patternKey);
        Iterable $this$map$iv = this.store.all();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            BehaviorCandidate it = (BehaviorCandidate) item$iv$iv;
            destination$iv$iv.add(it.getPatternKey());
        }
        Iterable all = (List) destination$iv$iv;
        if (StringsKt.startsWith$default(patternKey, "routine:HEADPHONES→", false, 2, (Object) null)) {
            String rest = StringsKt.removePrefix(patternKey, (CharSequence) "routine:HEADPHONES→");
            String pkg = StringsKt.substringBefore$default(rest, "@", (String) null, 2, (Object) null);
            keys.add("seq:HEADPHONES→" + pkg);
            Iterable $this$filter$iv = all;
            Collection destination$iv$iv2 = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                String it2 = (String) element$iv$iv;
                String rest2 = rest;
                Iterable $this$filter$iv2 = $this$filter$iv;
                if (StringsKt.startsWith$default(it2, "routine:HEADPHONES→" + pkg, false, 2, (Object) null)) {
                    destination$iv$iv2.add(element$iv$iv);
                }
                rest = rest2;
                $this$filter$iv = $this$filter$iv2;
            }
            Iterable $this$forEach$iv = (List) destination$iv$iv2;
            for (Object element$iv : $this$forEach$iv) {
                String it3 = (String) element$iv;
                keys.add(it3);
            }
        } else if (StringsKt.startsWith$default(patternKey, "seq:HEADPHONES→", false, 2, (Object) null)) {
            String pkg2 = StringsKt.removePrefix(patternKey, (CharSequence) "seq:HEADPHONES→");
            LinkedHashSet linkedHashSet = keys;
            Collection destination$iv$iv3 = new ArrayList();
            for (Object element$iv$iv2 : all) {
                String it4 = (String) element$iv$iv2;
                String pkg3 = pkg2;
                Iterable all2 = all;
                if (StringsKt.startsWith$default(it4, "routine:HEADPHONES→" + pkg2, false, 2, (Object) null)) {
                    destination$iv$iv3.add(element$iv$iv2);
                }
                pkg2 = pkg3;
                all = all2;
            }
            CollectionsKt.addAll(linkedHashSet, (List) destination$iv$iv3);
        }
        return CollectionsKt.toList(keys);
    }
}
