package com.jarvis.core.execution;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ToolMetadata.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010$\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fBG\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007\u0012\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\t¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0004HÆ\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007HÆ\u0003J\u0015\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\tHÆ\u0003JI\u0010\u0018\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\tHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006 "}, d2 = {"Lcom/jarvis/core/execution/ToolMetadata;", "", "tags", "", "", "source", "notes", "", "extras", "", "<init>", "(Ljava/util/Set;Ljava/lang/String;Ljava/util/List;Ljava/util/Map;)V", "getTags", "()Ljava/util/Set;", "getSource", "()Ljava/lang/String;", "getNotes", "()Ljava/util/List;", "getExtras", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ToolMetadata {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Set<String> FORBIDDEN_SECURITY_KEYS = SetsKt.setOf((Object[]) new String[]{"autonomy_level", "force_allow", "bypass_gate", "bypass_policy", "risk_class", "verdict", "gate_status", "integrity_fingerprint", "allow", "deny"});
    public static final int MAX_EXTRAS = 12;
    public static final int MAX_KEY = 48;
    public static final int MAX_NOTES = 8;
    public static final int MAX_SOURCE = 32;
    public static final int MAX_TAGS = 16;
    public static final int MAX_VALUE = 96;
    private final Map<String, String> extras;
    private final List<String> notes;
    private final String source;
    private final Set<String> tags;

    public ToolMetadata() {
        this(null, null, null, null, 15, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ToolMetadata copy$default(ToolMetadata toolMetadata, Set set, String str, List list, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            set = toolMetadata.tags;
        }
        if ((i & 2) != 0) {
            str = toolMetadata.source;
        }
        if ((i & 4) != 0) {
            list = toolMetadata.notes;
        }
        if ((i & 8) != 0) {
            map = toolMetadata.extras;
        }
        return toolMetadata.copy(set, str, list, map);
    }

    public final Set<String> component1() {
        return this.tags;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSource() {
        return this.source;
    }

    public final List<String> component3() {
        return this.notes;
    }

    public final Map<String, String> component4() {
        return this.extras;
    }

    public final ToolMetadata copy(Set<String> tags, String source, List<String> notes, Map<String, String> extras) {
        Intrinsics.checkNotNullParameter(tags, "tags");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(notes, "notes");
        Intrinsics.checkNotNullParameter(extras, "extras");
        return new ToolMetadata(tags, source, notes, extras);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ToolMetadata)) {
            return false;
        }
        ToolMetadata toolMetadata = (ToolMetadata) other;
        return Intrinsics.areEqual(this.tags, toolMetadata.tags) && Intrinsics.areEqual(this.source, toolMetadata.source) && Intrinsics.areEqual(this.notes, toolMetadata.notes) && Intrinsics.areEqual(this.extras, toolMetadata.extras);
    }

    public int hashCode() {
        return (((((this.tags.hashCode() * 31) + this.source.hashCode()) * 31) + this.notes.hashCode()) * 31) + this.extras.hashCode();
    }

    public String toString() {
        return "ToolMetadata(tags=" + this.tags + ", source=" + this.source + ", notes=" + this.notes + ", extras=" + this.extras + ")";
    }

    public ToolMetadata(Set<String> tags, String source, List<String> notes, Map<String, String> extras) {
        Intrinsics.checkNotNullParameter(tags, "tags");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(notes, "notes");
        Intrinsics.checkNotNullParameter(extras, "extras");
        this.tags = tags;
        this.source = source;
        this.notes = notes;
        this.extras = extras;
        if (!(this.source.length() <= 32)) {
            throw new IllegalArgumentException("metadata.source too long".toString());
        }
        if (!(this.tags.size() <= 16)) {
            throw new IllegalArgumentException("too many metadata tags".toString());
        }
        if (!(this.notes.size() <= 8)) {
            throw new IllegalArgumentException("too many metadata notes".toString());
        }
        if (!(this.extras.size() <= 12)) {
            throw new IllegalArgumentException("too many metadata extras".toString());
        }
        for (Map.Entry<String, String> entry : this.extras.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            if (!(k.length() <= 48)) {
                throw new IllegalArgumentException("metadata extra key too long".toString());
            }
            if (!(v.length() <= 96)) {
                throw new IllegalArgumentException("metadata extra value too long".toString());
            }
            String lk = k.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lk, "toLowerCase(...)");
            if (FORBIDDEN_SECURITY_KEYS.contains(lk)) {
                throw new IllegalArgumentException(("ToolMetadata must not carry security override key: " + k).toString());
            }
        }
        Iterable $this$forEach$iv = this.tags;
        for (Object element$iv : $this$forEach$iv) {
            String t = (String) element$iv;
            if (!(t.length() <= 48)) {
                throw new IllegalArgumentException("metadata tag too long".toString());
            }
            Set<String> set = FORBIDDEN_SECURITY_KEYS;
            String lowerCase = t.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (set.contains(lowerCase)) {
                throw new IllegalArgumentException(("ToolMetadata tag must not override security: " + t).toString());
            }
        }
    }

    public /* synthetic */ ToolMetadata(Set set, String str, List list, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? SetsKt.emptySet() : set, (i & 2) != 0 ? "native" : str, (i & 4) != 0 ? CollectionsKt.emptyList() : list, (i & 8) != 0 ? MapsKt.emptyMap() : map);
    }

    public final Set<String> getTags() {
        return this.tags;
    }

    public final String getSource() {
        return this.source;
    }

    public final List<String> getNotes() {
        return this.notes;
    }

    public final Map<String, String> getExtras() {
        return this.extras;
    }

    /* JADX INFO: compiled from: ToolMetadata.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/jarvis/core/execution/ToolMetadata$Companion;", "", "<init>", "()V", "MAX_SOURCE", "", "MAX_TAGS", "MAX_NOTES", "MAX_EXTRAS", "MAX_KEY", "MAX_VALUE", "FORBIDDEN_SECURITY_KEYS", "", "", "getFORBIDDEN_SECURITY_KEYS", "()Ljava/util/Set;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Set<String> getFORBIDDEN_SECURITY_KEYS() {
            return ToolMetadata.FORBIDDEN_SECURITY_KEYS;
        }
    }
}
