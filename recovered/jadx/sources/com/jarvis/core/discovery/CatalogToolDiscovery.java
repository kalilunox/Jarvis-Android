package com.jarvis.core.discovery;

import com.google.android.gms.actions.SearchIntents;
import com.jarvis.app.VoiceService;
import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.execution.CatalogToolRegistry;
import com.jarvis.core.execution.ToolAvailability;
import com.jarvis.core.execution.ToolCatalogEntry;
import com.jarvis.core.execution.ToolExecutionLocality;
import com.jarvis.core.routines.EventMatcher;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
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

/* JADX INFO: compiled from: CatalogToolDiscovery.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0010 \n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u001cB\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u0005J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000bH\u0002J*\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00170\u00152\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\r0\u00172\u0006\u0010\u0018\u001a\u00020\rH\u0002J\u0010\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\rH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/jarvis/core/discovery/CatalogToolDiscovery;", "", "catalog", "Lcom/jarvis/core/execution/CatalogToolRegistry;", "defaultLimit", "", "<init>", "(Lcom/jarvis/core/execution/CatalogToolRegistry;I)V", "discover", "Lcom/jarvis/core/discovery/DiscoveryResult;", "context", "Lcom/jarvis/core/discovery/DiscoveryContext;", SearchIntents.EXTRA_QUERY, "", "limit", "passesConstraints", "", VoiceService.EXTRA_ENTRY, "Lcom/jarvis/core/execution/ToolCatalogEntry;", "ctx", "score", "Lkotlin/Pair;", "", "", "q", "synonymBoost", "fold", "s", "Scored", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CatalogToolDiscovery {
    private final CatalogToolRegistry catalog;
    private final int defaultLimit;

    /* JADX INFO: compiled from: CatalogToolDiscovery.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ToolAvailability.values().length];
            try {
                iArr[ToolAvailability.NEEDS_ONLINE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ToolAvailability.NEEDS_PERMISSION.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ToolAvailability.NEEDS_ACCESSIBILITY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public CatalogToolDiscovery(CatalogToolRegistry catalog, int defaultLimit) {
        Intrinsics.checkNotNullParameter(catalog, "catalog");
        this.catalog = catalog;
        this.defaultLimit = defaultLimit;
    }

    public /* synthetic */ CatalogToolDiscovery(CatalogToolRegistry catalogToolRegistry, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(catalogToolRegistry, (i2 & 2) != 0 ? 6 : i);
    }

    public final DiscoveryResult discover(DiscoveryContext context) {
        Iterable iterableTake;
        CatalogToolDiscovery catalogToolDiscovery = this;
        Intrinsics.checkNotNullParameter(context, "context");
        int limit = RangesKt.coerceIn(context.getLimit(), 1, 12);
        List<ToolCatalogEntry> listCatalogEntries = catalogToolDiscovery.catalog.catalogEntries();
        List<ToolCatalogEntry> $this$filter$iv = listCatalogEntries;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            if (catalogToolDiscovery.passesConstraints((ToolCatalogEntry) element$iv$iv, context)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List eligible = (List) destination$iv$iv;
        String q = catalogToolDiscovery.fold(context.getQuery());
        if (StringsKt.isBlank(q)) {
            List $this$filter$iv2 = eligible;
            Collection destination$iv$iv2 = new ArrayList();
            for (Object element$iv$iv2 : $this$filter$iv2) {
                ToolCatalogEntry it = (ToolCatalogEntry) element$iv$iv2;
                if (it.getRiskLevel().ordinal() <= RiskLevel.LOW.ordinal() && it.getAvailability() == ToolAvailability.ALWAYS) {
                    destination$iv$iv2.add(element$iv$iv2);
                }
            }
            Iterable $this$sortedBy$iv = (List) destination$iv$iv2;
            Iterable $this$map$iv = CollectionsKt.take(CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: com.jarvis.core.discovery.CatalogToolDiscovery$discover$$inlined$sortedBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    ToolCatalogEntry it2 = (ToolCatalogEntry) t;
                    ToolCatalogEntry it3 = (ToolCatalogEntry) t2;
                    return ComparisonsKt.compareValues(it2.getToolId(), it3.getToolId());
                }
            }), RangesKt.coerceAtMost(limit, 4));
            Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                destination$iv$iv3.add(new Scored((ToolCatalogEntry) item$iv$iv, 0.1f, CollectionsKt.listOf("blank_query_safe_subset")));
                $this$map$iv = $this$map$iv;
            }
            iterableTake = (List) destination$iv$iv3;
        } else {
            List $this$mapNotNull$iv = eligible;
            int $i$f$mapNotNull = 0;
            Collection destination$iv$iv4 = new ArrayList();
            for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                Iterable $this$mapNotNull$iv2 = $this$mapNotNull$iv;
                ToolCatalogEntry entry = (ToolCatalogEntry) element$iv$iv$iv;
                Pair<Float, List<String>> pairScore = catalogToolDiscovery.score(q, entry);
                float score = pairScore.component1().floatValue();
                int $i$f$mapNotNull2 = $i$f$mapNotNull;
                Scored scored = score <= 0.0f ? null : new Scored(entry, score, pairScore.component2());
                if (scored != null) {
                    destination$iv$iv4.add(scored);
                }
                catalogToolDiscovery = this;
                $this$mapNotNull$iv = $this$mapNotNull$iv2;
                q = q;
                $i$f$mapNotNull = $i$f$mapNotNull2;
            }
            Iterable $this$sortedByDescending$iv = (List) destination$iv$iv4;
            iterableTake = CollectionsKt.take(CollectionsKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: com.jarvis.core.discovery.CatalogToolDiscovery$discover$$inlined$sortedByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    CatalogToolDiscovery.Scored it2 = (CatalogToolDiscovery.Scored) t2;
                    CatalogToolDiscovery.Scored it3 = (CatalogToolDiscovery.Scored) t;
                    return ComparisonsKt.compareValues(Float.valueOf(it2.getScore()), Float.valueOf(it3.getScore()));
                }
            }), limit);
        }
        Iterable scored2 = iterableTake;
        Iterable $this$map$iv2 = scored2;
        Collection destination$iv$iv5 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
        for (Object item$iv$iv2 : $this$map$iv2) {
            destination$iv$iv5.add(((Scored) item$iv$iv2).toItem());
        }
        List items = (List) destination$iv$iv5;
        return new DiscoveryResult(context.getQuery(), items, eligible.size() > items.size(), eligible.size(), CollectionsKt.listOf((Object[]) new String[]{"catalog_size=" + listCatalogEntries.size(), "eligible=" + eligible.size(), "returned=" + items.size(), "limit=" + limit}));
    }

    public static /* synthetic */ DiscoveryResult discover$default(CatalogToolDiscovery catalogToolDiscovery, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = catalogToolDiscovery.defaultLimit;
        }
        return catalogToolDiscovery.discover(str, i);
    }

    public final DiscoveryResult discover(String query, int limit) {
        Intrinsics.checkNotNullParameter(query, "query");
        return discover(new DiscoveryContext(query, limit, null, null, null, false, false, 124, null));
    }

    private final boolean passesConstraints(ToolCatalogEntry entry, DiscoveryContext ctx) {
        if (ctx.getRequireCapability()) {
            String capabilityId = entry.getCapabilityId();
            if (capabilityId == null || StringsKt.isBlank(capabilityId)) {
                return false;
            }
        }
        if (entry.getRiskLevel().ordinal() > ctx.getMaxRisk().ordinal()) {
            return false;
        }
        if (ctx.getExcludeUnavailable() && entry.getAvailability() == ToolAvailability.UNAVAILABLE) {
            return false;
        }
        switch (WhenMappings.$EnumSwitchMapping$0[entry.getAvailability().ordinal()]) {
            case 1:
                if (Intrinsics.areEqual((Object) ctx.isOnline(), (Object) false)) {
                    return false;
                }
                break;
            case 2:
                Set<String> grantedPermissions = ctx.getGrantedPermissions();
                if (grantedPermissions != null) {
                    Set required = CollectionsKt.toSet(entry.getRequiredPermissions());
                    if (!required.isEmpty() && !grantedPermissions.containsAll(required)) {
                        return false;
                    }
                }
                break;
            case 3:
                Set<String> grantedPermissions2 = ctx.getGrantedPermissions();
                if (grantedPermissions2 != null && !grantedPermissions2.contains("capability:ACCESSIBILITY")) {
                    return false;
                }
                break;
        }
        Set<String> grantedPermissions3 = ctx.getGrantedPermissions();
        if (grantedPermissions3 == null || entry.getRequiredPermissions().isEmpty() || grantedPermissions3.containsAll(entry.getRequiredPermissions())) {
            return (Intrinsics.areEqual((Object) ctx.isOnline(), (Object) false) && entry.getLocality() == ToolExecutionLocality.REMOTE) ? false : true;
        }
        return false;
    }

    private final Pair<Float, List<String>> score(String q, ToolCatalogEntry entry) {
        float f;
        float s = 0.0f;
        List reasons = new ArrayList();
        String idFold = fold(StringsKt.replace$default(StringsKt.replace$default(entry.getToolId(), '_', ' ', false, 4, (Object) null), '.', ' ', false, 4, (Object) null));
        String name = fold(entry.getName());
        String desc = fold(entry.getDescription());
        String capabilityId = entry.getCapabilityId();
        if (capabilityId == null) {
            capabilityId = "";
        }
        String cap = fold(StringsKt.replace$default(capabilityId, '_', ' ', false, 4, (Object) null));
        String tags = CollectionsKt.joinToString$default(entry.getMetadata().getTags(), StringUtils.SPACE, null, null, 0, null, new Function1() { // from class: com.jarvis.core.discovery.CatalogToolDiscovery$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CatalogToolDiscovery.score$lambda$0(this.f$0, (String) obj);
            }
        }, 30, null);
        String params = CollectionsKt.joinToString$default(entry.getParameterNames(), StringUtils.SPACE, null, null, 0, null, new Function1() { // from class: com.jarvis.core.discovery.CatalogToolDiscovery$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CatalogToolDiscovery.score$lambda$1(this.f$0, (String) obj);
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
        List tokens = (List) destination$iv$iv;
        Iterator it2 = tokens.iterator();
        while (true) {
            f = 2.0f;
            if (!it2.hasNext()) {
                break;
            }
            String t = (String) it2.next();
            if (StringsKt.contains$default((CharSequence) idFold, (CharSequence) t, false, 2, (Object) null)) {
                s3 += 3.0f;
                reasons.add("id:" + t);
            }
            if (StringsKt.contains$default((CharSequence) name, (CharSequence) t, false, 2, (Object) null)) {
                s3 += 2.5f;
                reasons.add("name:" + t);
            }
            if (StringsKt.contains$default((CharSequence) cap, (CharSequence) t, false, 2, (Object) null)) {
                s3 += 2.2f;
                reasons.add("cap:" + t);
            }
            if (StringsKt.contains$default((CharSequence) tags, (CharSequence) t, false, 2, (Object) null)) {
                s3 += 2.0f;
                reasons.add("tag:" + t);
            }
            if (StringsKt.contains$default((CharSequence) params, (CharSequence) t, false, 2, (Object) null)) {
                s3 += 1.0f;
                reasons.add("param:" + t);
            }
            if (StringsKt.contains$default((CharSequence) desc, (CharSequence) t, false, 2, (Object) null)) {
                s3 += 0.8f;
            }
        }
        for (String syn : synonymBoost(q)) {
            if (StringsKt.contains$default((CharSequence) tags, (CharSequence) syn, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) idFold, (CharSequence) syn, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) name, (CharSequence) syn, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) cap, (CharSequence) syn, false, 2, (Object) null)) {
                s3 += f;
                reasons.add("syn:" + syn);
                f = 2.0f;
            }
        }
        if (entry.getRiskLevel() == RiskLevel.SAFE) {
            s3 += 0.05f;
        }
        return TuplesKt.to(Float.valueOf(s3), CollectionsKt.take(CollectionsKt.distinct(reasons), 6));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence score$lambda$0(CatalogToolDiscovery this$0, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return this$0.fold(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence score$lambda$1(CatalogToolDiscovery this$0, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return this$0.fold(it);
    }

    private final List<String> synonymBoost(String q) {
        List $this$synonymBoost_u24lambda_u240 = CollectionsKt.createListBuilder();
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "фонар", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "свети", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "темно", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "свет", false, 2, (Object) null)) {
            $this$synonymBoost_u24lambda_u240.add("light");
            $this$synonymBoost_u24lambda_u240.add("flashlight");
            $this$synonymBoost_u24lambda_u240.add("фонарик");
            $this$synonymBoost_u24lambda_u240.add("device light");
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
        if (StringsKt.contains$default((CharSequence) q, (CharSequence) "настрой", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) EventMatcher.WIFI, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "bluetooth", false, 2, (Object) null)) {
            $this$synonymBoost_u24lambda_u240.add("setting");
            $this$synonymBoost_u24lambda_u240.add("system");
        }
        return CollectionsKt.build($this$synonymBoost_u24lambda_u240);
    }

    private final String fold(String s) {
        String lowerCase = s.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return StringsKt.trim((CharSequence) new Regex("\\s+").replace(StringsKt.replace$default(lowerCase, Typography.nbsp, ' ', false, 4, (Object) null), StringUtils.SPACE)).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: CatalogToolDiscovery.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\u0006\u0010\u0011\u001a\u00020\u0012J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J-\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001d"}, d2 = {"Lcom/jarvis/core/discovery/CatalogToolDiscovery$Scored;", "", VoiceService.EXTRA_ENTRY, "Lcom/jarvis/core/execution/ToolCatalogEntry;", "score", "", "reasons", "", "", "<init>", "(Lcom/jarvis/core/execution/ToolCatalogEntry;FLjava/util/List;)V", "getEntry", "()Lcom/jarvis/core/execution/ToolCatalogEntry;", "getScore", "()F", "getReasons", "()Ljava/util/List;", "toItem", "Lcom/jarvis/core/discovery/DiscoveredItem;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    static final /* data */ class Scored {
        private final ToolCatalogEntry entry;
        private final List<String> reasons;
        private final float score;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Scored copy$default(Scored scored, ToolCatalogEntry toolCatalogEntry, float f, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                toolCatalogEntry = scored.entry;
            }
            if ((i & 2) != 0) {
                f = scored.score;
            }
            if ((i & 4) != 0) {
                list = scored.reasons;
            }
            return scored.copy(toolCatalogEntry, f, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ToolCatalogEntry getEntry() {
            return this.entry;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final float getScore() {
            return this.score;
        }

        public final List<String> component3() {
            return this.reasons;
        }

        public final Scored copy(ToolCatalogEntry entry, float score, List<String> reasons) {
            Intrinsics.checkNotNullParameter(entry, "entry");
            Intrinsics.checkNotNullParameter(reasons, "reasons");
            return new Scored(entry, score, reasons);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Scored)) {
                return false;
            }
            Scored scored = (Scored) other;
            return Intrinsics.areEqual(this.entry, scored.entry) && Float.compare(this.score, scored.score) == 0 && Intrinsics.areEqual(this.reasons, scored.reasons);
        }

        public int hashCode() {
            return (((this.entry.hashCode() * 31) + Float.hashCode(this.score)) * 31) + this.reasons.hashCode();
        }

        public String toString() {
            return "Scored(entry=" + this.entry + ", score=" + this.score + ", reasons=" + this.reasons + ")";
        }

        public Scored(ToolCatalogEntry entry, float score, List<String> reasons) {
            Intrinsics.checkNotNullParameter(entry, "entry");
            Intrinsics.checkNotNullParameter(reasons, "reasons");
            this.entry = entry;
            this.score = score;
            this.reasons = reasons;
        }

        public final ToolCatalogEntry getEntry() {
            return this.entry;
        }

        public final float getScore() {
            return this.score;
        }

        public final List<String> getReasons() {
            return this.reasons;
        }

        public final DiscoveredItem toItem() {
            return new DiscoveredItem(this.entry.getToolId(), this.entry.getCapabilityId(), this.entry.getName(), this.entry.getDescription(), this.score, this.entry.getRiskLevel(), this.entry.getRequiredPermissions(), this.entry.getAvailability(), this.entry.getSource(), this.entry.getLocality(), this.entry.getHasExecutable(), this.entry.getParameterNames(), this.reasons);
        }
    }
}
