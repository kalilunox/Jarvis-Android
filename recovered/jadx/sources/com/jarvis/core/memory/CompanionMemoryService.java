package com.jarvis.core.memory;

import com.jarvis.core.companion.ContextSnapshot;
import com.jarvis.core.companion.PhoneObservation;
import com.jarvis.core.companion.Sensitivity;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: CompanionMemoryService.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\"\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0013J.\u0010\u0019\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!J\u0006\u0010\"\u001a\u00020#J\u0006\u0010\u0002\u001a\u00020\u0003R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006$"}, d2 = {"Lcom/jarvis/core/memory/CompanionMemoryService;", "", "repository", "Lcom/jarvis/core/memory/MemoryRepository;", "detector", "Lcom/jarvis/core/memory/MemoryCandidateDetector;", "sensitivityFilter", "Lcom/jarvis/core/memory/SensitivityFilter;", "policy", "Lcom/jarvis/core/memory/MemoryPolicy;", "writer", "Lcom/jarvis/core/memory/MemoryWriter;", "recall", "Lcom/jarvis/core/memory/MemoryRecall;", "<init>", "(Lcom/jarvis/core/memory/MemoryRepository;Lcom/jarvis/core/memory/MemoryCandidateDetector;Lcom/jarvis/core/memory/SensitivityFilter;Lcom/jarvis/core/memory/MemoryPolicy;Lcom/jarvis/core/memory/MemoryWriter;Lcom/jarvis/core/memory/MemoryRecall;)V", "getRecall", "()Lcom/jarvis/core/memory/MemoryRecall;", "ingest", "", "Lcom/jarvis/core/memory/MemoryEntry;", "snapshot", "Lcom/jarvis/core/companion/ContextSnapshot;", "recent", "Lcom/jarvis/core/companion/PhoneObservation;", "rememberUserStatement", "statement", "", "kind", "Lcom/jarvis/core/memory/MemoryKind;", "confidence", "", "sensitivity", "Lcom/jarvis/core/companion/Sensitivity;", "expireNow", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CompanionMemoryService {
    private final MemoryCandidateDetector detector;
    private final MemoryPolicy policy;
    private final MemoryRecall recall;
    private final MemoryRepository repository;
    private final SensitivityFilter sensitivityFilter;
    private final MemoryWriter writer;

    public CompanionMemoryService(MemoryRepository repository, MemoryCandidateDetector detector, SensitivityFilter sensitivityFilter, MemoryPolicy policy, MemoryWriter writer, MemoryRecall recall) {
        Intrinsics.checkNotNullParameter(repository, "repository");
        Intrinsics.checkNotNullParameter(detector, "detector");
        Intrinsics.checkNotNullParameter(sensitivityFilter, "sensitivityFilter");
        Intrinsics.checkNotNullParameter(policy, "policy");
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(recall, "recall");
        this.repository = repository;
        this.detector = detector;
        this.sensitivityFilter = sensitivityFilter;
        this.policy = policy;
        this.writer = writer;
        this.recall = recall;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ CompanionMemoryService(MemoryRepository memoryRepository, MemoryCandidateDetector memoryCandidateDetector, SensitivityFilter sensitivityFilter, MemoryPolicy memoryPolicy, MemoryWriter memoryWriter, MemoryRecall memoryRecall, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(memoryRepository, (i & 2) != 0 ? new MemoryCandidateDetector(0, 0L, 3, null) : memoryCandidateDetector, (i & 4) != 0 ? new SensitivityFilter() : sensitivityFilter, (i & 8) != 0 ? new MemoryPolicy(0.0f, 0.0f, 3, null) : memoryPolicy, (i & 16) != 0 ? new MemoryWriter(memoryRepository, null, 2, 0 == true ? 1 : 0) : memoryWriter, (i & 32) != 0 ? new MemoryRecall(memoryRepository) : memoryRecall);
    }

    public final MemoryRecall getRecall() {
        return this.recall;
    }

    public final List<MemoryEntry> ingest(ContextSnapshot snapshot, List<PhoneObservation> recent) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        Intrinsics.checkNotNullParameter(recent, "recent");
        MemoryRepository.expire$default(this.repository, 0L, 1, null);
        List out = new ArrayList();
        for (MemoryCandidate c : MemoryCandidateDetector.detect$default(this.detector, snapshot, recent, 0L, 4, null)) {
            MemoryPolicyDecision decision = this.sensitivityFilter.filter(c);
            if (decision == null) {
                decision = this.policy.decide(c);
            }
            MemoryEntry it = this.writer.persist(decision);
            if (it != null) {
                out.add(it);
            }
        }
        return out;
    }

    public static /* synthetic */ MemoryEntry rememberUserStatement$default(CompanionMemoryService companionMemoryService, String str, MemoryKind memoryKind, float f, Sensitivity sensitivity, int i, Object obj) {
        if ((i & 2) != 0) {
            memoryKind = MemoryKind.PREFERENCE;
        }
        if ((i & 4) != 0) {
            f = 1.0f;
        }
        if ((i & 8) != 0) {
            sensitivity = Sensitivity.PRIVATE;
        }
        return companionMemoryService.rememberUserStatement(str, memoryKind, f, sensitivity);
    }

    public final MemoryEntry rememberUserStatement(String statement, MemoryKind kind, float confidence, Sensitivity sensitivity) {
        Intrinsics.checkNotNullParameter(statement, "statement");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(sensitivity, "sensitivity");
        long now = System.currentTimeMillis();
        MemoryCandidate c = new MemoryCandidate(null, kind, MemoryProvenance.USER_STATED, StringsKt.trim((CharSequence) statement).toString(), 1, confidence, now, now, sensitivity, null, null, MapsKt.mapOf(TuplesKt.to("type", "USER_STATEMENT")), 1537, null);
        MemoryPolicyDecision decision = this.sensitivityFilter.filter(c);
        if (decision == null) {
            decision = this.policy.decide(c);
        }
        return this.writer.persist(decision);
    }

    public final int expireNow() {
        return MemoryRepository.expire$default(this.repository, 0L, 1, null);
    }

    /* JADX INFO: renamed from: repository, reason: from getter */
    public final MemoryRepository getRepository() {
        return this.repository;
    }
}
