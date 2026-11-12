package com.jarvis.core.agent;

import com.google.android.gms.actions.SearchIntents;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AgentShortcuts.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lcom/jarvis/core/agent/AgentShortcut;", "", "<init>", "()V", "YouTubeSearch", "Setting", "MapSearch", "WebSearch", "Lcom/jarvis/core/agent/AgentShortcut$MapSearch;", "Lcom/jarvis/core/agent/AgentShortcut$Setting;", "Lcom/jarvis/core/agent/AgentShortcut$WebSearch;", "Lcom/jarvis/core/agent/AgentShortcut$YouTubeSearch;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class AgentShortcut {
    public /* synthetic */ AgentShortcut(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: AgentShortcuts.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/agent/AgentShortcut$YouTubeSearch;", "Lcom/jarvis/core/agent/AgentShortcut;", SearchIntents.EXTRA_QUERY, "", "<init>", "(Ljava/lang/String;)V", "getQuery", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class YouTubeSearch extends AgentShortcut {
        private final String query;

        public static /* synthetic */ YouTubeSearch copy$default(YouTubeSearch youTubeSearch, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = youTubeSearch.query;
            }
            return youTubeSearch.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getQuery() {
            return this.query;
        }

        public final YouTubeSearch copy(String query) {
            Intrinsics.checkNotNullParameter(query, "query");
            return new YouTubeSearch(query);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof YouTubeSearch) && Intrinsics.areEqual(this.query, ((YouTubeSearch) other).query);
        }

        public int hashCode() {
            return this.query.hashCode();
        }

        public String toString() {
            return "YouTubeSearch(query=" + this.query + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public YouTubeSearch(String query) {
            super(null);
            Intrinsics.checkNotNullParameter(query, "query");
            this.query = query;
        }

        public final String getQuery() {
            return this.query;
        }
    }

    private AgentShortcut() {
    }

    /* JADX INFO: compiled from: AgentShortcuts.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/agent/AgentShortcut$Setting;", "Lcom/jarvis/core/agent/AgentShortcut;", "name", "", "<init>", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Setting extends AgentShortcut {
        private final String name;

        public static /* synthetic */ Setting copy$default(Setting setting, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = setting.name;
            }
            return setting.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        public final Setting copy(String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            return new Setting(name);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Setting) && Intrinsics.areEqual(this.name, ((Setting) other).name);
        }

        public int hashCode() {
            return this.name.hashCode();
        }

        public String toString() {
            return "Setting(name=" + this.name + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Setting(String name) {
            super(null);
            Intrinsics.checkNotNullParameter(name, "name");
            this.name = name;
        }

        public final String getName() {
            return this.name;
        }
    }

    /* JADX INFO: compiled from: AgentShortcuts.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/agent/AgentShortcut$MapSearch;", "Lcom/jarvis/core/agent/AgentShortcut;", "place", "", "<init>", "(Ljava/lang/String;)V", "getPlace", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class MapSearch extends AgentShortcut {
        private final String place;

        public static /* synthetic */ MapSearch copy$default(MapSearch mapSearch, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = mapSearch.place;
            }
            return mapSearch.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getPlace() {
            return this.place;
        }

        public final MapSearch copy(String place) {
            Intrinsics.checkNotNullParameter(place, "place");
            return new MapSearch(place);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof MapSearch) && Intrinsics.areEqual(this.place, ((MapSearch) other).place);
        }

        public int hashCode() {
            return this.place.hashCode();
        }

        public String toString() {
            return "MapSearch(place=" + this.place + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MapSearch(String place) {
            super(null);
            Intrinsics.checkNotNullParameter(place, "place");
            this.place = place;
        }

        public final String getPlace() {
            return this.place;
        }
    }

    /* JADX INFO: compiled from: AgentShortcuts.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/agent/AgentShortcut$WebSearch;", "Lcom/jarvis/core/agent/AgentShortcut;", SearchIntents.EXTRA_QUERY, "", "<init>", "(Ljava/lang/String;)V", "getQuery", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class WebSearch extends AgentShortcut {
        private final String query;

        public static /* synthetic */ WebSearch copy$default(WebSearch webSearch, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = webSearch.query;
            }
            return webSearch.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getQuery() {
            return this.query;
        }

        public final WebSearch copy(String query) {
            Intrinsics.checkNotNullParameter(query, "query");
            return new WebSearch(query);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof WebSearch) && Intrinsics.areEqual(this.query, ((WebSearch) other).query);
        }

        public int hashCode() {
            return this.query.hashCode();
        }

        public String toString() {
            return "WebSearch(query=" + this.query + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public WebSearch(String query) {
            super(null);
            Intrinsics.checkNotNullParameter(query, "query");
            this.query = query;
        }

        public final String getQuery() {
            return this.query;
        }
    }
}
