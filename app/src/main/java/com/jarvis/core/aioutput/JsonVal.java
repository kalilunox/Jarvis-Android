package com.jarvis.core.aioutput;

import com.jarvis.app.AndroidReminderBook;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MiniJson.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0006\u0013\u0014\u0015\u0016\u0017\u0018B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005J\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007J\b\u0010\b\u001a\u0004\u0018\u00010\tJ\u0010\u0010\n\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u000b\u001a\u00020\tJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\tJ\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000fJ\u0010\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0011\u0082\u0001\u0006\u0007\u0019\u001a\u001b\u0005\u001c¨\u0006\u001d"}, d2 = {"Lcom/jarvis/core/aioutput/JsonVal;", "", "<init>", "()V", "asObj", "Lcom/jarvis/core/aioutput/JsonVal$Obj;", "asArr", "Lcom/jarvis/core/aioutput/JsonVal$Arr;", "asString", "", "opt", "key", "has", "", "keys", "", "nestingDepth", "", "depth", "Obj", "Arr", "Str", "Num", "Bool", "Null", "Lcom/jarvis/core/aioutput/JsonVal$Bool;", "Lcom/jarvis/core/aioutput/JsonVal$Null;", "Lcom/jarvis/core/aioutput/JsonVal$Num;", "Lcom/jarvis/core/aioutput/JsonVal$Str;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class JsonVal {
    public /* synthetic */ JsonVal(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: MiniJson.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\"\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001`\u0005¢\u0006\u0004\b\u0006\u0010\u0007J%\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001`\u0005HÆ\u0003J/\u0010\u000b\u001a\u00020\u00002$\b\u0002\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001`\u0005HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0004HÖ\u0001R-\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Lcom/jarvis/core/aioutput/JsonVal$Obj;", "Lcom/jarvis/core/aioutput/JsonVal;", "map", "Ljava/util/LinkedHashMap;", "", "Lkotlin/collections/LinkedHashMap;", "<init>", "(Ljava/util/LinkedHashMap;)V", "getMap", "()Ljava/util/LinkedHashMap;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Obj extends JsonVal {
        private final LinkedHashMap<String, JsonVal> map;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Obj copy$default(Obj obj, LinkedHashMap linkedHashMap, int i, Object obj2) {
            if ((i & 1) != 0) {
                linkedHashMap = obj.map;
            }
            return obj.copy(linkedHashMap);
        }

        public final LinkedHashMap<String, JsonVal> component1() {
            return this.map;
        }

        public final Obj copy(LinkedHashMap<String, JsonVal> map) {
            Intrinsics.checkNotNullParameter(map, "map");
            return new Obj(map);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Obj) && Intrinsics.areEqual(this.map, ((Obj) other).map);
        }

        public int hashCode() {
            return this.map.hashCode();
        }

        public String toString() {
            return "Obj(map=" + this.map + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Obj(LinkedHashMap<String, JsonVal> map) {
            super(null);
            Intrinsics.checkNotNullParameter(map, "map");
            this.map = map;
        }

        public final LinkedHashMap<String, JsonVal> getMap() {
            return this.map;
        }
    }

    private JsonVal() {
    }

    /* JADX INFO: compiled from: MiniJson.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/jarvis/core/aioutput/JsonVal$Arr;", "Lcom/jarvis/core/aioutput/JsonVal;", AndroidReminderBook.KEY_ITEMS, "", "<init>", "(Ljava/util/List;)V", "getItems", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Arr extends JsonVal {
        private final List<JsonVal> items;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Arr copy$default(Arr arr, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = arr.items;
            }
            return arr.copy(list);
        }

        public final List<JsonVal> component1() {
            return this.items;
        }

        public final Arr copy(List<? extends JsonVal> items) {
            Intrinsics.checkNotNullParameter(items, "items");
            return new Arr(items);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Arr) && Intrinsics.areEqual(this.items, ((Arr) other).items);
        }

        public int hashCode() {
            return this.items.hashCode();
        }

        public String toString() {
            return "Arr(items=" + this.items + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public Arr(List<? extends JsonVal> items) {
            super(null);
            Intrinsics.checkNotNullParameter(items, "items");
            this.items = items;
        }

        public final List<JsonVal> getItems() {
            return this.items;
        }
    }

    /* JADX INFO: compiled from: MiniJson.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/aioutput/JsonVal$Str;", "Lcom/jarvis/core/aioutput/JsonVal;", "value", "", "<init>", "(Ljava/lang/String;)V", "getValue", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Str extends JsonVal {
        private final String value;

        public static /* synthetic */ Str copy$default(Str str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str2 = str.value;
            }
            return str.copy(str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getValue() {
            return this.value;
        }

        public final Str copy(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new Str(value);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Str) && Intrinsics.areEqual(this.value, ((Str) other).value);
        }

        public int hashCode() {
            return this.value.hashCode();
        }

        public String toString() {
            return "Str(value=" + this.value + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Str(String value) {
            super(null);
            Intrinsics.checkNotNullParameter(value, "value");
            this.value = value;
        }

        public final String getValue() {
            return this.value;
        }
    }

    /* JADX INFO: compiled from: MiniJson.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/jarvis/core/aioutput/JsonVal$Num;", "Lcom/jarvis/core/aioutput/JsonVal;", "value", "", "<init>", "(D)V", "getValue", "()D", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Num extends JsonVal {
        private final double value;

        public static /* synthetic */ Num copy$default(Num num, double d, int i, Object obj) {
            if ((i & 1) != 0) {
                d = num.value;
            }
            return num.copy(d);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final double getValue() {
            return this.value;
        }

        public final Num copy(double value) {
            return new Num(value);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Num) && Double.compare(this.value, ((Num) other).value) == 0;
        }

        public int hashCode() {
            return Double.hashCode(this.value);
        }

        public String toString() {
            return "Num(value=" + this.value + ")";
        }

        public Num(double value) {
            super(null);
            this.value = value;
        }

        public final double getValue() {
            return this.value;
        }
    }

    /* JADX INFO: compiled from: MiniJson.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/aioutput/JsonVal$Bool;", "Lcom/jarvis/core/aioutput/JsonVal;", "value", "", "<init>", "(Z)V", "getValue", "()Z", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Bool extends JsonVal {
        private final boolean value;

        public static /* synthetic */ Bool copy$default(Bool bool, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = bool.value;
            }
            return bool.copy(z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getValue() {
            return this.value;
        }

        public final Bool copy(boolean value) {
            return new Bool(value);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Bool) && this.value == ((Bool) other).value;
        }

        public int hashCode() {
            return Boolean.hashCode(this.value);
        }

        public String toString() {
            return "Bool(value=" + this.value + ")";
        }

        public Bool(boolean value) {
            super(null);
            this.value = value;
        }

        public final boolean getValue() {
            return this.value;
        }
    }

    /* JADX INFO: compiled from: MiniJson.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/jarvis/core/aioutput/JsonVal$Null;", "Lcom/jarvis/core/aioutput/JsonVal;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Null extends JsonVal {
        public static final Null INSTANCE = new Null();

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Null)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -253933234;
        }

        public String toString() {
            return "Null";
        }

        private Null() {
            super(null);
        }
    }

    public final Obj asObj() {
        if (this instanceof Obj) {
            return (Obj) this;
        }
        return null;
    }

    public final Arr asArr() {
        if (this instanceof Arr) {
            return (Arr) this;
        }
        return null;
    }

    public final String asString() {
        if (this instanceof Str) {
            return ((Str) this).getValue();
        }
        if (this instanceof Num) {
            return (((Num) this).getValue() > ((double) ((long) ((Num) this).getValue())) ? 1 : (((Num) this).getValue() == ((double) ((long) ((Num) this).getValue())) ? 0 : -1)) == 0 ? String.valueOf((long) ((Num) this).getValue()) : String.valueOf(((Num) this).getValue());
        }
        if (this instanceof Bool) {
            return String.valueOf(((Bool) this).getValue());
        }
        return null;
    }

    public final JsonVal opt(String key) {
        LinkedHashMap<String, JsonVal> map;
        Intrinsics.checkNotNullParameter(key, "key");
        Obj objAsObj = asObj();
        if (objAsObj == null || (map = objAsObj.getMap()) == null) {
            return null;
        }
        return map.get(key);
    }

    public final boolean has(String key) {
        LinkedHashMap<String, JsonVal> map;
        Intrinsics.checkNotNullParameter(key, "key");
        Obj objAsObj = asObj();
        return (objAsObj == null || (map = objAsObj.getMap()) == null || !map.containsKey(key)) ? false : true;
    }

    public final Set<String> keys() {
        LinkedHashMap<String, JsonVal> map;
        Obj objAsObj = asObj();
        Set<String> setKeySet = (objAsObj == null || (map = objAsObj.getMap()) == null) ? null : map.keySet();
        return setKeySet == null ? SetsKt.emptySet() : setKeySet;
    }

    public static /* synthetic */ int nestingDepth$default(JsonVal jsonVal, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: nestingDepth");
        }
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return jsonVal.nestingDepth(i);
    }

    public final int nestingDepth(int depth) {
        Integer numValueOf = null;
        if (this instanceof Obj) {
            Collection<JsonVal> collectionValues = ((Obj) this).getMap().values();
            Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
            Iterator<T> it = collectionValues.iterator();
            if (it.hasNext()) {
                JsonVal it2 = (JsonVal) it.next();
                numValueOf = Integer.valueOf(it2.nestingDepth(depth + 1));
                while (it.hasNext()) {
                    JsonVal it3 = (JsonVal) it.next();
                    Integer numValueOf2 = Integer.valueOf(it3.nestingDepth(depth + 1));
                    if (numValueOf.compareTo(numValueOf2) < 0) {
                        numValueOf = numValueOf2;
                    }
                }
            }
            Integer num = numValueOf;
            if (num != null) {
                return num.intValue();
            }
        } else if (this instanceof Arr) {
            Iterator<T> it4 = ((Arr) this).getItems().iterator();
            if (it4.hasNext()) {
                JsonVal it5 = (JsonVal) it4.next();
                numValueOf = Integer.valueOf(it5.nestingDepth(depth + 1));
                while (it4.hasNext()) {
                    JsonVal it6 = (JsonVal) it4.next();
                    Integer numValueOf3 = Integer.valueOf(it6.nestingDepth(depth + 1));
                    if (numValueOf.compareTo(numValueOf3) < 0) {
                        numValueOf = numValueOf3;
                    }
                }
            }
            Integer num2 = numValueOf;
            if (num2 != null) {
                return num2.intValue();
            }
        }
        return depth;
    }
}
