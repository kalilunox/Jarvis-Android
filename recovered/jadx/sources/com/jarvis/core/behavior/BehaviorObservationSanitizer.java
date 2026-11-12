package com.jarvis.core.behavior;

import com.jarvis.core.companion.ObservationType;
import com.jarvis.core.companion.PhoneObservation;
import com.jarvis.core.companion.Sensitivity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: BehaviorObservationSanitizer.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJ$\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u0010J\u0010\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0006R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0014"}, d2 = {"Lcom/jarvis/core/behavior/BehaviorObservationSanitizer;", "", "<init>", "()V", "SENSITIVE_PACKAGES", "", "", "NOISY_PACKAGES", "getNOISY_PACKAGES", "()Ljava/util/Set;", "sanitizeForLearning", "", "Lcom/jarvis/core/companion/PhoneObservation;", "observations", "dedupeEvidence", "bucketMs", "", "isNoisyPackage", "", "pkg", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BehaviorObservationSanitizer {
    public static final BehaviorObservationSanitizer INSTANCE = new BehaviorObservationSanitizer();
    private static final Set<String> SENSITIVE_PACKAGES = SetsKt.setOf((Object[]) new String[]{"com.google.android.apps.authenticator2", "com.whatsapp", "org.telegram.messenger", "com.viber.voip", "com.android.mms", "com.google.android.apps.messaging", "com.android.contacts", "com.android.dialer", "com.google.android.dialer", "com.android.settings"});
    private static final Set<String> NOISY_PACKAGES = SetsKt.setOf((Object[]) new String[]{"com.android.systemui", "com.miui.home", "com.mi.android.globallauncher", "com.google.android.permissioncontroller", "com.android.packageinstaller", "com.google.android.packageinstaller", "com.lbe.security.miui", "com.jarvis.assistant"});

    private BehaviorObservationSanitizer() {
    }

    public final Set<String> getNOISY_PACKAGES() {
        return NOISY_PACKAGES;
    }

    /* JADX WARN: Code duplicated, block: B:50:0x013c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:52:0x012d A[SYNTHETIC] */
    public final List<PhoneObservation> sanitizeForLearning(List<PhoneObservation> observations) {
        boolean z;
        Intrinsics.checkNotNullParameter(observations, "observations");
        List<PhoneObservation> $this$mapNotNull$iv = observations;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            PhoneObservation o = (PhoneObservation) element$iv$iv$iv;
            if (o.getSensitivity() == Sensitivity.SECRET) {
                $this$mapNotNull$iv = $this$mapNotNull$iv;
                o = null;
            } else if (o.getType() == ObservationType.NOTIFICATION_POSTED || o.getType() == ObservationType.NOTIFICATION_REMOVED) {
                String it = o.getPackageName();
                String str = it != null ? "notif:" + it : null;
                Map<String, String> entities = o.getEntities();
                LinkedHashMap result$iv = new LinkedHashMap();
                for (Map.Entry<String, String> entry : entities.entrySet()) {
                    String it2 = entry.getKey();
                    if (Intrinsics.areEqual(it2, "package")) {
                        entities = entities;
                    } else {
                        entities = entities;
                        if (!Intrinsics.areEqual(it2, "ongoing")) {
                            z = false;
                        }
                        if (z) {
                            result$iv.put(entry.getKey(), entry.getValue());
                        }
                    }
                    z = true;
                    if (z) {
                        result$iv.put(entry.getKey(), entry.getValue());
                    }
                }
                LinkedHashMap $this$filterKeys$iv = result$iv;
                o = o.copy((575 & 1) != 0 ? o.id : null, (575 & 2) != 0 ? o.timestampMillis : 0L, (575 & 4) != 0 ? o.source : null, (575 & 8) != 0 ? o.type : null, (575 & 16) != 0 ? o.packageName : null, (575 & 32) != 0 ? o.screenId : null, (575 & 64) != 0 ? o.summary : str, (575 & 128) != 0 ? o.entities : $this$filterKeys$iv, (575 & 256) != 0 ? o.sensitivity : Sensitivity.PRIVATE, (575 & 512) != 0 ? o.confidence : 0.0f);
            } else if (o.getType() == ObservationType.WINDOW_CHANGED && Intrinsics.areEqual(o.getEntities().get("kind"), "content")) {
                $this$mapNotNull$iv = $this$mapNotNull$iv;
                String str2 = "window:" + o.getPackageName();
                Pair[] pairArr = new Pair[2];
                String packageName = o.getPackageName();
                if (packageName == null) {
                    packageName = "";
                }
                pairArr[0] = TuplesKt.to("package", packageName);
                pairArr[1] = TuplesKt.to("kind", "content");
                o = o.copy((575 & 1) != 0 ? o.id : null, (575 & 2) != 0 ? o.timestampMillis : 0L, (575 & 4) != 0 ? o.source : null, (575 & 8) != 0 ? o.type : null, (575 & 16) != 0 ? o.packageName : null, (575 & 32) != 0 ? o.screenId : null, (575 & 64) != 0 ? o.summary : str2, (575 & 128) != 0 ? o.entities : MapsKt.mapOf(pairArr), (575 & 256) != 0 ? o.sensitivity : null, (575 & 512) != 0 ? o.confidence : 0.0f);
            } else {
                $this$mapNotNull$iv = $this$mapNotNull$iv;
            }
            if (o != null) {
                destination$iv$iv.add(o);
            }
            $this$mapNotNull$iv = $this$mapNotNull$iv;
        }
        return (List) destination$iv$iv;
    }

    public static /* synthetic */ List dedupeEvidence$default(BehaviorObservationSanitizer behaviorObservationSanitizer, List list, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 90000;
        }
        return behaviorObservationSanitizer.dedupeEvidence(list, j);
    }

    public final List<PhoneObservation> dedupeEvidence(List<PhoneObservation> observations, long bucketMs) {
        PhoneObservation prev;
        Intrinsics.checkNotNullParameter(observations, "observations");
        if (observations.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        List<PhoneObservation> $this$sortedBy$iv = observations;
        List<PhoneObservation> sorted = CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: com.jarvis.core.behavior.BehaviorObservationSanitizer$dedupeEvidence$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                PhoneObservation it = (PhoneObservation) t;
                PhoneObservation it2 = (PhoneObservation) t2;
                return ComparisonsKt.compareValues(Long.valueOf(it.getTimestampMillis()), Long.valueOf(it2.getTimestampMillis()));
            }
        });
        ArrayList out = new ArrayList(sorted.size());
        HashSet seenIds = new HashSet();
        for (PhoneObservation o : sorted) {
            if (seenIds.add(o.getId()) && ((prev = (PhoneObservation) CollectionsKt.lastOrNull((List) out)) == null || prev.getType() != o.getType() || !Intrinsics.areEqual(prev.getPackageName(), o.getPackageName()) || o.getTimestampMillis() - prev.getTimestampMillis() >= bucketMs)) {
                out.add(o);
            }
        }
        return out;
    }

    public final boolean isNoisyPackage(String pkg) {
        if (pkg != null) {
            return NOISY_PACKAGES.contains(pkg) || StringsKt.startsWith$default(pkg, "com.android.systemui", false, 2, (Object) null);
        }
        return false;
    }
}
