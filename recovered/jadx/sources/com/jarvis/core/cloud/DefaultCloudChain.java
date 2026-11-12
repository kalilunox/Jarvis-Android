package com.jarvis.core.cloud;

import com.jarvis.app.proactive.PhoneStateReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ChainEntry.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J(\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¨\u0006\u000b"}, d2 = {"Lcom/jarvis/core/cloud/DefaultCloudChain;", "", "<init>", "()V", "entries", "", "Lcom/jarvis/core/cloud/ChainEntry;", "availableEntries", "providersWithKeys", "", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DefaultCloudChain {
    public static final DefaultCloudChain INSTANCE = new DefaultCloudChain();

    private DefaultCloudChain() {
    }

    public final List<ChainEntry> entries() {
        return CollectionsKt.listOf((Object[]) new ChainEntry[]{new ChainEntry("groq", "openai/gpt-oss-120b", "groq gpt-oss-120b", "https://api.groq.com/openai/v1", CollectionsKt.listOf((Object[]) new Map[]{MapsKt.mapOf(TuplesKt.to("reasoning_effort", "low"), TuplesKt.to("include_reasoning", false)), MapsKt.mapOf(TuplesKt.to("reasoning_effort", "low")), MapsKt.emptyMap()})), new ChainEntry("gemini", "gemini-3.5-flash-lite", "gemini 3.5 flash lite", "https://generativelanguage.googleapis.com/v1beta/openai", CollectionsKt.listOf((Object[]) new Map[]{MapsKt.mapOf(TuplesKt.to("reasoning_effort", "minimal")), MapsKt.mapOf(TuplesKt.to("reasoning_effort", PhoneStateReader.FAKE_NONE)), MapsKt.emptyMap()})), new ChainEntry("groq", "qwen/qwen3.8-27b", "groq qwen3.8-27b", "https://api.groq.com/openai/v1", CollectionsKt.listOf((Object[]) new Map[]{MapsKt.mapOf(TuplesKt.to("reasoning_effort", PhoneStateReader.FAKE_NONE), TuplesKt.to("reasoning_format", "hidden")), MapsKt.mapOf(TuplesKt.to("reasoning_effort", PhoneStateReader.FAKE_NONE)), MapsKt.mapOf(TuplesKt.to("reasoning_format", "hidden")), MapsKt.emptyMap()})), new ChainEntry("gemini", "gemini-3.1-flash-lite", "gemini 3.1 flash lite", "https://generativelanguage.googleapis.com/v1beta/openai", CollectionsKt.listOf((Object[]) new Map[]{MapsKt.mapOf(TuplesKt.to("reasoning_effort", PhoneStateReader.FAKE_NONE)), MapsKt.mapOf(TuplesKt.to("reasoning_effort", "minimal")), MapsKt.emptyMap()}))});
    }

    public final List<ChainEntry> availableEntries(List<ChainEntry> entries, Set<String> providersWithKeys) {
        Intrinsics.checkNotNullParameter(entries, "entries");
        Intrinsics.checkNotNullParameter(providersWithKeys, "providersWithKeys");
        List<ChainEntry> $this$filter$iv = entries;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            ChainEntry it = (ChainEntry) element$iv$iv;
            if (providersWithKeys.contains(it.getProviderId())) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }
}
