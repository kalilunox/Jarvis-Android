package com.jarvis.core.capability;

import com.google.android.gms.actions.SearchIntents;
import com.jarvis.app.VoiceController;
import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.routines.EventMatcher;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: CapabilityDiscovery.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\u0018\u0000 \u001d2\u00020\u0001:\u0002\u001c\u001dB\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J(\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u000fJ(\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\t2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u000fJ\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u000fH\u0002J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\nH\u0002J\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\f0\t2\u0006\u0010\u0018\u001a\u00020\fH\u0002J\u0010\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/jarvis/core/capability/CapabilityDiscovery;", "", "registry", "Lcom/jarvis/core/capability/CapabilityRegistry;", "maxResults", "", "<init>", "(Lcom/jarvis/core/capability/CapabilityRegistry;I)V", "discover", "", "Lcom/jarvis/core/capability/CapabilityDescriptor;", SearchIntents.EXTRA_QUERY, "", "limit", "constraints", "Lcom/jarvis/core/capability/CapabilityDiscoveryConstraints;", "discoverSummaries", "Lcom/jarvis/core/capability/CapabilitySummary;", "passesConstraints", "", "cap", "c", "score", "", "q", "synonymBoost", "fold", "s", "Scored", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CapabilityDiscovery {
    public static final int HARD_CAP = 12;
    private final int maxResults;
    private final CapabilityRegistry registry;

    /* JADX INFO: compiled from: CapabilityDiscovery.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CapabilityAvailability.values().length];
            try {
                iArr[CapabilityAvailability.NEEDS_ONLINE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[CapabilityAvailability.NEEDS_PERMISSION.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[CapabilityAvailability.NEEDS_ACCESSIBILITY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public CapabilityDiscovery(CapabilityRegistry registry, int maxResults) {
        Intrinsics.checkNotNullParameter(registry, "registry");
        this.registry = registry;
        this.maxResults = maxResults;
    }

    public /* synthetic */ CapabilityDiscovery(CapabilityRegistry capabilityRegistry, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(capabilityRegistry, (i2 & 2) != 0 ? 6 : i);
    }

    public static /* synthetic */ List discover$default(CapabilityDiscovery capabilityDiscovery, String str, int i, CapabilityDiscoveryConstraints capabilityDiscoveryConstraints, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = capabilityDiscovery.maxResults;
        }
        if ((i2 & 4) != 0) {
            capabilityDiscoveryConstraints = new CapabilityDiscoveryConstraints(null, null, null, 7, null);
        }
        return capabilityDiscovery.discover(str, i, capabilityDiscoveryConstraints);
    }

    public final List<CapabilityDescriptor> discover(String query, int limit, CapabilityDiscoveryConstraints constraints) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(constraints, "constraints");
        int capped = RangesKt.coerceIn(limit, 1, 12);
        String q = fold(query);
        Iterable $this$filter$iv = this.registry.all();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            if (passesConstraints((CapabilityDescriptor) element$iv$iv, constraints)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable pool = (List) destination$iv$iv;
        if (StringsKt.isBlank(q)) {
            Iterable $this$filter$iv2 = pool;
            Collection destination$iv$iv2 = new ArrayList();
            for (Object element$iv$iv2 : $this$filter$iv2) {
                CapabilityDescriptor it = (CapabilityDescriptor) element$iv$iv2;
                if (it.getRiskLevel().ordinal() <= RiskLevel.LOW.ordinal() && it.getAvailability() == CapabilityAvailability.ALWAYS) {
                    destination$iv$iv2.add(element$iv$iv2);
                }
            }
            return CollectionsKt.take((List) destination$iv$iv2, RangesKt.coerceAtMost(capped, 4));
        }
        Iterable $this$map$iv = pool;
        Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            CapabilityDescriptor cap = (CapabilityDescriptor) item$iv$iv;
            destination$iv$iv3.add(new Scored(cap, score(q, cap)));
        }
        Iterable $this$filter$iv3 = (List) destination$iv$iv3;
        Collection destination$iv$iv4 = new ArrayList();
        for (Object element$iv$iv3 : $this$filter$iv3) {
            if (((Scored) element$iv$iv3).getScore() > 0.0f) {
                destination$iv$iv4.add(element$iv$iv3);
            }
        }
        Iterable $this$sortedByDescending$iv = (List) destination$iv$iv4;
        List scored = CollectionsKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: com.jarvis.core.capability.CapabilityDiscovery$discover$$inlined$sortedByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                CapabilityDiscovery.Scored it2 = (CapabilityDiscovery.Scored) t2;
                CapabilityDiscovery.Scored it3 = (CapabilityDiscovery.Scored) t;
                return ComparisonsKt.compareValues(Float.valueOf(it2.getScore()), Float.valueOf(it3.getScore()));
            }
        });
        if (scored.isEmpty()) {
            Iterable $this$filter$iv4 = pool;
            Collection destination$iv$iv5 = new ArrayList();
            for (Object element$iv$iv4 : $this$filter$iv4) {
                CapabilityDescriptor it2 = (CapabilityDescriptor) element$iv$iv4;
                if (it2.getRiskLevel().ordinal() <= RiskLevel.LOW.ordinal() && it2.getAvailability() == CapabilityAvailability.ALWAYS) {
                    destination$iv$iv5.add(element$iv$iv4);
                }
            }
            return CollectionsKt.take((List) destination$iv$iv5, RangesKt.coerceAtMost(capped, 4));
        }
        Iterable $this$map$iv2 = CollectionsKt.take(scored, capped);
        Collection destination$iv$iv6 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
        for (Object item$iv$iv2 : $this$map$iv2) {
            destination$iv$iv6.add(((Scored) item$iv$iv2).getDescriptor());
        }
        return (List) destination$iv$iv6;
    }

    public static /* synthetic */ List discoverSummaries$default(CapabilityDiscovery capabilityDiscovery, String str, int i, CapabilityDiscoveryConstraints capabilityDiscoveryConstraints, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = capabilityDiscovery.maxResults;
        }
        if ((i2 & 4) != 0) {
            capabilityDiscoveryConstraints = new CapabilityDiscoveryConstraints(null, null, null, 7, null);
        }
        return capabilityDiscovery.discoverSummaries(str, i, capabilityDiscoveryConstraints);
    }

    public final List<CapabilitySummary> discoverSummaries(String query, int limit, CapabilityDiscoveryConstraints constraints) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(constraints, "constraints");
        Iterable $this$map$iv = discover(query, limit, constraints);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            CapabilityDescriptor it = (CapabilityDescriptor) item$iv$iv;
            destination$iv$iv.add(CapabilityRegistryKt.toSummary(it));
        }
        return (List) destination$iv$iv;
    }

    private final boolean passesConstraints(CapabilityDescriptor cap, CapabilityDiscoveryConstraints c) {
        if (cap.getRiskLevel().ordinal() > c.getMaxRisk().ordinal()) {
            return false;
        }
        switch (WhenMappings.$EnumSwitchMapping$0[cap.getAvailability().ordinal()]) {
            case 1:
                if (Intrinsics.areEqual((Object) c.isOnline(), (Object) false)) {
                    return false;
                }
                break;
            case 2:
                Set<String> grantedPermissions = c.getGrantedPermissions();
                if (grantedPermissions == null) {
                    return true;
                }
                if (!cap.getPermissions().isEmpty() && !grantedPermissions.containsAll(cap.getPermissions())) {
                    return false;
                }
                break;
            case 3:
                Set<String> grantedPermissions2 = c.getGrantedPermissions();
                if (grantedPermissions2 == null) {
                    return true;
                }
                if (!grantedPermissions2.contains("capability:ACCESSIBILITY")) {
                    return false;
                }
                break;
        }
        Set<String> grantedPermissions3 = c.getGrantedPermissions();
        return grantedPermissions3 == null || cap.getPermissions().isEmpty() || grantedPermissions3.containsAll(cap.getPermissions());
    }

    private final float score(String q, CapabilityDescriptor cap) {
        float s = 0.0f;
        String idFold = fold(StringsKt.replace$default(StringsKt.replace$default(cap.getId(), '_', ' ', false, 4, (Object) null), '.', ' ', false, 4, (Object) null));
        String title = fold(cap.getTitle());
        String desc = fold(cap.getDescription());
        String tags = CollectionsKt.joinToString$default(cap.getTags(), StringUtils.SPACE, null, null, 0, null, new Function1() { // from class: com.jarvis.core.capability.CapabilityDiscovery$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CapabilityDiscovery.score$lambda$0(this.f$0, (String) obj);
            }
        }, 30, null);
        String examples = CollectionsKt.joinToString$default(cap.getExamples(), StringUtils.SPACE, null, null, 0, null, new Function1() { // from class: com.jarvis.core.capability.CapabilityDiscovery$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CapabilityDiscovery.score$lambda$1(this.f$0, (String) obj);
            }
        }, 30, null);
        String ops = CollectionsKt.joinToString$default(cap.getOperations(), StringUtils.SPACE, null, null, 0, null, new Function1() { // from class: com.jarvis.core.capability.CapabilityDiscovery$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CapabilityDiscovery.score$lambda$2(this.f$0, (String) obj);
            }
        }, 30, null);
        Iterable $this$filter$iv = new Regex("\\s+").split(q, 0);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            float s2 = s;
            if (it.length() >= 2) {
                destination$iv$iv.add(element$iv$iv);
            }
            s = s2;
        }
        float s3 = s;
        List<String> tokens = (List) destination$iv$iv;
        for (String t : tokens) {
            if (StringsKt.contains$default((CharSequence) idFold, (CharSequence) t, false, 2, (Object) null)) {
                s3 += 3.0f;
            }
            if (StringsKt.contains$default((CharSequence) title, (CharSequence) t, false, 2, (Object) null)) {
                s3 += 2.5f;
            }
            if (StringsKt.contains$default((CharSequence) tags, (CharSequence) t, false, 2, (Object) null)) {
                s3 += 2.0f;
            }
            if (StringsKt.contains$default((CharSequence) ops, (CharSequence) t, false, 2, (Object) null)) {
                s3 += 1.5f;
            }
            if (StringsKt.contains$default((CharSequence) examples, (CharSequence) t, false, 2, (Object) null)) {
                s3 += 2.0f;
            }
            if (StringsKt.contains$default((CharSequence) desc, (CharSequence) t, false, 2, (Object) null)) {
                s3 += 0.8f;
            }
        }
        for (String ex : cap.getExamples()) {
            String e = fold(ex);
            if (!StringsKt.isBlank(e) && (StringsKt.contains$default((CharSequence) q, (CharSequence) e, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) e, (CharSequence) q, false, 2, (Object) null))) {
                s3 += 4.0f;
            }
        }
        for (String syn : synonymBoost(q)) {
            if (StringsKt.contains$default((CharSequence) tags, (CharSequence) syn, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) idFold, (CharSequence) syn, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) title, (CharSequence) syn, false, 2, (Object) null)) {
                s3 += 2.0f;
            }
        }
        return s3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence score$lambda$0(CapabilityDiscovery this$0, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return this$0.fold(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence score$lambda$1(CapabilityDiscovery this$0, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return this$0.fold(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence score$lambda$2(CapabilityDiscovery this$0, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return this$0.fold(it);
    }

    private final List<String> synonymBoost(String q) {
        List $this$synonymBoost_u24lambda_u240 = CollectionsKt.createListBuilder();
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "фонар", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "свети", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "темно", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "свет", false, 2, (Object) null)) {
            $this$synonymBoost_u24lambda_u240.add("light");
            $this$synonymBoost_u24lambda_u240.add("flashlight");
            $this$synonymBoost_u24lambda_u240.add("фонарик");
        }
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "громк", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "тише", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "звук", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "volume", false, 2, (Object) null)) {
            $this$synonymBoost_u24lambda_u240.add("volume");
            $this$synonymBoost_u24lambda_u240.add("audio");
            $this$synonymBoost_u24lambda_u240.add("громкость");
        }
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "ярк", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "brightness", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "экран", false, 2, (Object) null)) {
            $this$synonymBoost_u24lambda_u240.add("brightness");
            $this$synonymBoost_u24lambda_u240.add("яркость");
        }
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "погод", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "градус", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "weather", false, 2, (Object) null)) {
            $this$synonymBoost_u24lambda_u240.add("weather");
            $this$synonymBoost_u24lambda_u240.add("погода");
        }
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "музык", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "песн", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "трек", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "пауза", false, 2, (Object) null)) {
            $this$synonymBoost_u24lambda_u240.add("music");
            $this$synonymBoost_u24lambda_u240.add("media");
        }
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "звон", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "набери", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "call", false, 2, (Object) null)) {
            $this$synonymBoost_u24lambda_u240.add("call");
            $this$synonymBoost_u24lambda_u240.add("phone");
        }
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "смс", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "sms", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "сообщен", false, 2, (Object) null)) {
            $this$synonymBoost_u24lambda_u240.add("sms");
            $this$synonymBoost_u24lambda_u240.add("message");
        }
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "телеграм", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "telegram", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "тг", false, 2, (Object) null)) {
            $this$synonymBoost_u24lambda_u240.add("telegram");
        }
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "открой", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "запусти", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "приложение", false, 2, (Object) null)) {
            $this$synonymBoost_u24lambda_u240.add("launcher");
            $this$synonymBoost_u24lambda_u240.add("app");
        }
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "таймер", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "будильник", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "напоми", false, 2, (Object) null)) {
            $this$synonymBoost_u24lambda_u240.add("timer");
        }
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "вики", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "расскажи", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "что такое", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "кто такой", false, 2, (Object) null)) {
            $this$synonymBoost_u24lambda_u240.add("knowledge");
            $this$synonymBoost_u24lambda_u240.add("wiki");
        }
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "настрой", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) EventMatcher.WIFI, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "bluetooth", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) EventMatcher.WIFI, false, 2, (Object) null)) {
            $this$synonymBoost_u24lambda_u240.add("setting");
            $this$synonymBoost_u24lambda_u240.add("system");
        }
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "нажми", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "тапни", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "свайп", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "на экран", false, 2, (Object) null)) {
            $this$synonymBoost_u24lambda_u240.add(VoiceController.ENTRY_UI);
            $this$synonymBoost_u24lambda_u240.add("screen");
        }
        return CollectionsKt.build($this$synonymBoost_u24lambda_u240);
    }

    private final String fold(String s) {
        String lowerCase = s.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return StringsKt.trim((CharSequence) new Regex("\\s+").replace(StringsKt.replace$default(lowerCase, Typography.nbsp, ' ', false, 4, (Object) null), StringUtils.SPACE)).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: CapabilityDiscovery.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/jarvis/core/capability/CapabilityDiscovery$Scored;", "", "descriptor", "Lcom/jarvis/core/capability/CapabilityDescriptor;", "score", "", "<init>", "(Lcom/jarvis/core/capability/CapabilityDescriptor;F)V", "getDescriptor", "()Lcom/jarvis/core/capability/CapabilityDescriptor;", "getScore", "()F", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    static final /* data */ class Scored {
        private final CapabilityDescriptor descriptor;
        private final float score;

        public static /* synthetic */ Scored copy$default(Scored scored, CapabilityDescriptor capabilityDescriptor, float f, int i, Object obj) {
            if ((i & 1) != 0) {
                capabilityDescriptor = scored.descriptor;
            }
            if ((i & 2) != 0) {
                f = scored.score;
            }
            return scored.copy(capabilityDescriptor, f);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final CapabilityDescriptor getDescriptor() {
            return this.descriptor;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final float getScore() {
            return this.score;
        }

        public final Scored copy(CapabilityDescriptor descriptor, float score) {
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            return new Scored(descriptor, score);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Scored)) {
                return false;
            }
            Scored scored = (Scored) other;
            return Intrinsics.areEqual(this.descriptor, scored.descriptor) && Float.compare(this.score, scored.score) == 0;
        }

        public int hashCode() {
            return (this.descriptor.hashCode() * 31) + Float.hashCode(this.score);
        }

        public String toString() {
            return "Scored(descriptor=" + this.descriptor + ", score=" + this.score + ")";
        }

        public Scored(CapabilityDescriptor descriptor, float score) {
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            this.descriptor = descriptor;
            this.score = score;
        }

        public final CapabilityDescriptor getDescriptor() {
            return this.descriptor;
        }

        public final float getScore() {
            return this.score;
        }
    }
}
