package com.jarvis.core.behavior;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: BehaviorSnapshotCodec.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0005J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0002J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0002J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0002J\u0010\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0002J\u0010\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0002J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u00112\u0006\u0010\u0012\u001a\u00020\u0005H\u0002¨\u0006\u0013"}, d2 = {"Lcom/jarvis/core/behavior/BehaviorSnapshotCodec;", "", "<init>", "()V", "encode", "", "snapshot", "Lcom/jarvis/core/behavior/BehaviorStoreSnapshot;", "decode", "raw", "escField", "s", "unescField", "escToken", "unescToken", "unescape", "splitFields", "", "input", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BehaviorSnapshotCodec {
    public static final BehaviorSnapshotCodec INSTANCE = new BehaviorSnapshotCodec();

    private BehaviorSnapshotCodec() {
    }

    public final String encode(BehaviorStoreSnapshot snapshot) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        StringBuilder $this$encode_u24lambda_u240 = new StringBuilder();
        $this$encode_u24lambda_u240.append("v1").append('\n');
        $this$encode_u24lambda_u240.append("disabled=" + CollectionsKt.joinToString$default(snapshot.getDisabledKeys(), ",", null, null, 0, null, new Function1() { // from class: com.jarvis.core.behavior.BehaviorSnapshotCodec$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BehaviorSnapshotCodec.encode$lambda$0$0((String) obj);
            }
        }, 30, null)).append('\n');
        $this$encode_u24lambda_u240.append("rejected=" + CollectionsKt.joinToString$default(snapshot.getRejectedKeys(), ",", null, null, 0, null, new Function1() { // from class: com.jarvis.core.behavior.BehaviorSnapshotCodec$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BehaviorSnapshotCodec.encode$lambda$0$1((String) obj);
            }
        }, 30, null)).append('\n');
        $this$encode_u24lambda_u240.append("ignore=" + CollectionsKt.joinToString$default(snapshot.getIgnoreUntil().entrySet(), ",", null, null, 0, null, new Function1() { // from class: com.jarvis.core.behavior.BehaviorSnapshotCodec$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BehaviorSnapshotCodec.encode$lambda$0$2((Map.Entry) obj);
            }
        }, 30, null)).append('\n');
        for (BehaviorCandidate c : snapshot.getCandidates()) {
            $this$encode_u24lambda_u240.append("C|");
            $this$encode_u24lambda_u240.append(INSTANCE.escField(c.getPatternKey())).append('|');
            $this$encode_u24lambda_u240.append(c.getKind().name()).append('|');
            $this$encode_u24lambda_u240.append(c.getFamily().name()).append('|');
            $this$encode_u24lambda_u240.append(c.getStatus().name()).append('|');
            $this$encode_u24lambda_u240.append(c.getConfidence()).append('|');
            $this$encode_u24lambda_u240.append(c.getEvidenceCount()).append('|');
            $this$encode_u24lambda_u240.append(c.getFirstObservedMillis()).append('|');
            $this$encode_u24lambda_u240.append(c.getLastObservedMillis()).append('|');
            $this$encode_u24lambda_u240.append(c.getUniqueDays()).append('|');
            $this$encode_u24lambda_u240.append(c.getNegativeEvidenceCount()).append('|');
            $this$encode_u24lambda_u240.append(INSTANCE.escField(c.getDescription())).append('|');
            $this$encode_u24lambda_u240.append(INSTANCE.escField(c.getWhy())).append('|');
            $this$encode_u24lambda_u240.append(CollectionsKt.joinToString$default(c.getSupportingEvidenceIds(), ";", null, null, 0, null, new Function1() { // from class: com.jarvis.core.behavior.BehaviorSnapshotCodec$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return BehaviorSnapshotCodec.encode$lambda$0$3((String) obj);
                }
            }, 30, null)).append('|');
            $this$encode_u24lambda_u240.append(CollectionsKt.joinToString$default(c.getEntities().entrySet(), ";", null, null, 0, null, new Function1() { // from class: com.jarvis.core.behavior.BehaviorSnapshotCodec$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return BehaviorSnapshotCodec.encode$lambda$0$4((Map.Entry) obj);
                }
            }, 30, null));
            $this$encode_u24lambda_u240.append('\n');
        }
        return $this$encode_u24lambda_u240.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence encode$lambda$0$0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return INSTANCE.escToken(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence encode$lambda$0$1(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return INSTANCE.escToken(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence encode$lambda$0$2(Map.Entry it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return INSTANCE.escToken((String) it.getKey()) + "@" + it.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence encode$lambda$0$3(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return INSTANCE.escToken(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence encode$lambda$0$4(Map.Entry it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return INSTANCE.escToken((String) it.getKey()) + "=" + INSTANCE.escToken((String) it.getValue());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v24 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v30 */
    /* JADX WARN: Type inference failed for: r3v39 */
    /* JADX WARN: Type inference failed for: r3v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r3v9 */
    public final BehaviorStoreSnapshot decode(String raw) {
        Pair pair;
        Pair pair2;
        Pair pair3;
        BehaviorSnapshotCodec behaviorSnapshotCodec = this;
        Intrinsics.checkNotNullParameter(raw, "raw");
        if (StringsKt.isBlank(raw)) {
            return new BehaviorStoreSnapshot(CollectionsKt.emptyList(), SetsKt.emptySet(), SetsKt.emptySet(), MapsKt.emptyMap());
        }
        List lines = SequencesKt.toList(SequencesKt.filter(StringsKt.lineSequence(raw), new Function1() { // from class: com.jarvis.core.behavior.BehaviorSnapshotCodec$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(BehaviorSnapshotCodec.decode$lambda$0((String) obj));
            }
        }));
        if (!lines.isEmpty()) {
            ?? r3 = 0;
            if (Intrinsics.areEqual(lines.get(0), "v1")) {
                Set disabled = SetsKt.emptySet();
                Set rejected = SetsKt.emptySet();
                Map ignore = MapsKt.emptyMap();
                List candidates = new ArrayList();
                int i = 1;
                Iterator it = CollectionsKt.drop(lines, 1).iterator();
                while (it.hasNext()) {
                    String line = (String) it.next();
                    if (StringsKt.startsWith$default(line, "disabled=", (boolean) r3, 2, (Object) null)) {
                        String strRemovePrefix = StringsKt.removePrefix(line, (CharSequence) "disabled=");
                        char[] cArr = new char[i];
                        cArr[r3] = ',';
                        Iterable $this$map$iv = StringsKt.split$default((CharSequence) strRemovePrefix, cArr, false, 0, 6, (Object) null);
                        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                        for (Object item$iv$iv : $this$map$iv) {
                            String it2 = (String) item$iv$iv;
                            destination$iv$iv.add(INSTANCE.unescToken(StringsKt.trim((CharSequence) it2).toString()));
                        }
                        Iterable $this$filter$iv = (List) destination$iv$iv;
                        Collection destination$iv$iv2 = new ArrayList();
                        for (Object element$iv$iv : $this$filter$iv) {
                            String it3 = (String) element$iv$iv;
                            if (it3.length() > 0) {
                                destination$iv$iv2.add(element$iv$iv);
                            }
                        }
                        disabled = CollectionsKt.toSet((List) destination$iv$iv2);
                        r3 = 0;
                        i = 1;
                    } else if (StringsKt.startsWith$default(line, "rejected=", false, 2, (Object) null)) {
                        Iterable $this$map$iv2 = StringsKt.split$default((CharSequence) StringsKt.removePrefix(line, (CharSequence) "rejected="), new char[]{','}, false, 0, 6, (Object) null);
                        Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
                        for (Object item$iv$iv2 : $this$map$iv2) {
                            String it4 = (String) item$iv$iv2;
                            destination$iv$iv3.add(INSTANCE.unescToken(StringsKt.trim((CharSequence) it4).toString()));
                            lines = lines;
                        }
                        List lines2 = lines;
                        Iterable $this$filter$iv2 = (List) destination$iv$iv3;
                        Collection destination$iv$iv4 = new ArrayList();
                        for (Object element$iv$iv2 : $this$filter$iv2) {
                            String it5 = (String) element$iv$iv2;
                            if (it5.length() > 0) {
                                destination$iv$iv4.add(element$iv$iv2);
                            }
                        }
                        rejected = CollectionsKt.toSet((List) destination$iv$iv4);
                        lines = lines2;
                        r3 = 0;
                        i = 1;
                    } else {
                        List lines3 = lines;
                        if (StringsKt.startsWith$default(line, "ignore=", false, 2, (Object) null)) {
                            Iterable $this$mapNotNull$iv = StringsKt.split$default((CharSequence) StringsKt.removePrefix(line, (CharSequence) "ignore="), new char[]{','}, false, 0, 6, (Object) null);
                            int $i$f$mapNotNull = 0;
                            Collection destination$iv$iv5 = new ArrayList();
                            Iterable $this$mapNotNullTo$iv$iv = $this$mapNotNull$iv;
                            for (Object element$iv$iv$iv : $this$mapNotNullTo$iv$iv) {
                                String part = (String) element$iv$iv$iv;
                                Iterable $this$mapNotNull$iv2 = $this$mapNotNull$iv;
                                int i2 = StringsKt.lastIndexOf$default((CharSequence) part, '@', 0, false, 6, (Object) null);
                                if (i2 <= 0) {
                                    pair2 = null;
                                } else {
                                    BehaviorSnapshotCodec behaviorSnapshotCodec2 = INSTANCE;
                                    String strSubstring = part.substring(0, i2);
                                    Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                                    String strUnescToken = behaviorSnapshotCodec2.unescToken(strSubstring);
                                    String strSubstring2 = part.substring(i2 + 1);
                                    Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                                    pair2 = TuplesKt.to(strUnescToken, StringsKt.toLongOrNull(strSubstring2));
                                }
                                if (pair2 != null) {
                                    destination$iv$iv5.add(pair2);
                                }
                                $i$f$mapNotNull = $i$f$mapNotNull;
                                it = it;
                                $this$mapNotNullTo$iv$iv = $this$mapNotNullTo$iv$iv;
                                $this$mapNotNull$iv = $this$mapNotNull$iv2;
                            }
                            Iterator it6 = it;
                            Iterable $this$mapNotNull$iv3 = (List) destination$iv$iv5;
                            int $i$f$mapNotNull2 = 0;
                            Collection destination$iv$iv6 = new ArrayList();
                            for (Object element$iv$iv$iv2 : $this$mapNotNull$iv3) {
                                Pair pair4 = (Pair) element$iv$iv$iv2;
                                Iterable $this$mapNotNull$iv4 = $this$mapNotNull$iv3;
                                String k = (String) pair4.component1();
                                Long v = (Long) pair4.component2();
                                if (v != null) {
                                    long it7 = v.longValue();
                                    pair = TuplesKt.to(k, Long.valueOf(it7));
                                } else {
                                    pair = null;
                                }
                                if (pair != null) {
                                    destination$iv$iv6.add(pair);
                                }
                                $this$mapNotNull$iv3 = $this$mapNotNull$iv4;
                                $i$f$mapNotNull2 = $i$f$mapNotNull2;
                            }
                            ignore = MapsKt.toMap((List) destination$iv$iv6);
                            lines = lines3;
                            it = it6;
                            r3 = 0;
                            i = 1;
                        } else {
                            Iterator it8 = it;
                            if (StringsKt.startsWith$default(line, "C|", false, 2, (Object) null)) {
                                List<String> listSplitFields = behaviorSnapshotCodec.splitFields(StringsKt.removePrefix(line, (CharSequence) "C|"));
                                if (listSplitFields.size() >= 14) {
                                    List list = candidates;
                                    String strUnescField = behaviorSnapshotCodec.unescField(listSplitFields.get(0));
                                    BehaviorKind behaviorKindValueOf = BehaviorKind.valueOf(listSplitFields.get(1));
                                    PatternFamily patternFamilyValueOf = PatternFamily.valueOf(listSplitFields.get(2));
                                    BehaviorCandidateStatus behaviorCandidateStatusValueOf = BehaviorCandidateStatus.valueOf(listSplitFields.get(3));
                                    Float floatOrNull = StringsKt.toFloatOrNull(listSplitFields.get(4));
                                    float fFloatValue = floatOrNull != null ? floatOrNull.floatValue() : 0.0f;
                                    Integer intOrNull = StringsKt.toIntOrNull(listSplitFields.get(5));
                                    int iIntValue = intOrNull != null ? intOrNull.intValue() : 0;
                                    Long longOrNull = StringsKt.toLongOrNull(listSplitFields.get(6));
                                    long jLongValue = longOrNull != null ? longOrNull.longValue() : 0L;
                                    Long longOrNull2 = StringsKt.toLongOrNull(listSplitFields.get(7));
                                    long jLongValue2 = longOrNull2 != null ? longOrNull2.longValue() : 0L;
                                    Integer intOrNull2 = StringsKt.toIntOrNull(listSplitFields.get(8));
                                    int iIntValue2 = intOrNull2 != null ? intOrNull2.intValue() : 1;
                                    Integer intOrNull3 = StringsKt.toIntOrNull(listSplitFields.get(9));
                                    int iIntValue3 = intOrNull3 != null ? intOrNull3.intValue() : 0;
                                    String strUnescField2 = behaviorSnapshotCodec.unescField(listSplitFields.get(10));
                                    String strUnescField3 = behaviorSnapshotCodec.unescField(listSplitFields.get(11));
                                    Iterable $this$map$iv3 = StringsKt.split$default((CharSequence) listSplitFields.get(12), new char[]{';'}, false, 0, 6, (Object) null);
                                    Collection destination$iv$iv7 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv3, 10));
                                    for (Object item$iv$iv3 : $this$map$iv3) {
                                        String it9 = (String) item$iv$iv3;
                                        destination$iv$iv7.add(INSTANCE.unescToken(it9));
                                    }
                                    Iterable $this$filter$iv3 = (List) destination$iv$iv7;
                                    Collection destination$iv$iv8 = new ArrayList();
                                    for (Object element$iv$iv3 : $this$filter$iv3) {
                                        String it10 = (String) element$iv$iv3;
                                        if (it10.length() > 0) {
                                            destination$iv$iv8.add(element$iv$iv3);
                                        }
                                    }
                                    ArrayList arrayList = (List) destination$iv$iv8;
                                    Iterable $this$mapNotNull$iv5 = StringsKt.split$default((CharSequence) listSplitFields.get(13), new char[]{';'}, false, 0, 6, (Object) null);
                                    Collection destination$iv$iv9 = new ArrayList();
                                    for (Object element$iv$iv$iv3 : $this$mapNotNull$iv5) {
                                        String e = (String) element$iv$iv$iv3;
                                        int i3 = StringsKt.indexOf$default((CharSequence) e, '=', 0, false, 6, (Object) null);
                                        if (i3 <= 0) {
                                            pair3 = null;
                                        } else {
                                            BehaviorSnapshotCodec behaviorSnapshotCodec3 = INSTANCE;
                                            String line2 = e.substring(0, i3);
                                            Intrinsics.checkNotNullExpressionValue(line2, "substring(...)");
                                            String strUnescToken2 = behaviorSnapshotCodec3.unescToken(line2);
                                            BehaviorSnapshotCodec behaviorSnapshotCodec4 = INSTANCE;
                                            String strSubstring3 = e.substring(i3 + 1);
                                            Intrinsics.checkNotNullExpressionValue(strSubstring3, "substring(...)");
                                            pair3 = TuplesKt.to(strUnescToken2, behaviorSnapshotCodec4.unescToken(strSubstring3));
                                        }
                                        if (pair3 != null) {
                                            destination$iv$iv9.add(pair3);
                                        }
                                        line = line;
                                        $this$mapNotNull$iv5 = $this$mapNotNull$iv5;
                                        listSplitFields = listSplitFields;
                                    }
                                    list.add(new BehaviorCandidate(null, behaviorKindValueOf, patternFamilyValueOf, strUnescField2, fFloatValue, iIntValue, jLongValue, jLongValue2, arrayList, behaviorCandidateStatusValueOf, strUnescField, MapsKt.toMap((List) destination$iv$iv9), iIntValue2, iIntValue3, strUnescField3, 1, null));
                                }
                            }
                            behaviorSnapshotCodec = this;
                            lines = lines3;
                            it = it8;
                            r3 = 0;
                            i = 1;
                        }
                    }
                }
                return new BehaviorStoreSnapshot(candidates, disabled, rejected, ignore);
            }
        }
        return new BehaviorStoreSnapshot(CollectionsKt.emptyList(), SetsKt.emptySet(), SetsKt.emptySet(), MapsKt.emptyMap());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean decode$lambda$0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return !StringsKt.isBlank(it);
    }

    private final String escField(String s) {
        return StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(s, "\\", "\\\\", false, 4, (Object) null), "|", "\\|", false, 4, (Object) null), StringUtils.LF, "\\n", false, 4, (Object) null);
    }

    private final String unescField(String s) {
        return unescape(s);
    }

    private final String escToken(String s) {
        return StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(s, "\\", "\\\\", false, 4, (Object) null), ",", "\\,", false, 4, (Object) null), ";", "\\;", false, 4, (Object) null), "=", "\\=", false, 4, (Object) null), "@", "\\@", false, 4, (Object) null), "|", "\\|", false, 4, (Object) null), StringUtils.LF, "\\n", false, 4, (Object) null);
    }

    private final String unescToken(String s) {
        return unescape(s);
    }

    private final String unescape(String s) {
        StringBuilder out = new StringBuilder(s.length());
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '\\' && i + 1 < s.length()) {
                if (s.charAt(i + 1) == 'n') {
                    out.append('\n');
                } else {
                    out.append(s.charAt(i + 1));
                }
                i += 2;
            } else {
                out.append(s.charAt(i));
                i++;
            }
        }
        String string = out.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    private final List<String> splitFields(String input) {
        List out = new ArrayList();
        StringBuilder cur = new StringBuilder();
        int i = 0;
        while (i < input.length()) {
            if (input.charAt(i) == '\\' && i + 1 < input.length()) {
                cur.append(IOUtils.DIR_SEPARATOR_WINDOWS).append(input.charAt(i + 1));
                i += 2;
            } else if (input.charAt(i) == '|') {
                out.add(cur.toString());
                StringsKt.clear(cur);
                i++;
            } else {
                cur.append(input.charAt(i));
                i++;
            }
        }
        out.add(cur.toString());
        return out;
    }
}
