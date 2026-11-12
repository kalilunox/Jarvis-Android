package com.jarvis.wakeword;

import com.jarvis.app.VoiceService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: VoicePrint.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0004J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\u0004J\u0014\u0010\u0011\u001a\u00020\u00002\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003J\u0006\u0010\u0013\u001a\u00020\u0014R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/jarvis/wakeword/VoicePrint;", "", "samples", "", "", "threshold", "", "<init>", "(Ljava/util/List;F)V", "getSamples", "()Ljava/util/List;", "getThreshold", "()F", "score", "embedding", "accepts", "", "with", "extra", "encode", "", "Companion", "wakeword-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VoicePrint {
    private static final int BEST_MATCHES = 3;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final float MARGIN = 0.15f;
    private static final float MAX_THRESHOLD = 0.5f;
    private static final float MIN_THRESHOLD = 0.3f;
    public static final int SAMPLES_NEEDED = 10;
    private final List<float[]> samples;
    private final float threshold;

    public VoicePrint(List<float[]> samples, float threshold) {
        Intrinsics.checkNotNullParameter(samples, "samples");
        this.samples = samples;
        this.threshold = threshold;
    }

    public final List<float[]> getSamples() {
        return this.samples;
    }

    public final float getThreshold() {
        return this.threshold;
    }

    public final float score(float[] embedding) {
        Intrinsics.checkNotNullParameter(embedding, "embedding");
        return INSTANCE.bestMean(embedding, this.samples);
    }

    public final boolean accepts(float[] embedding) {
        Intrinsics.checkNotNullParameter(embedding, "embedding");
        return score(embedding) >= this.threshold;
    }

    public final VoicePrint with(List<float[]> extra) {
        Intrinsics.checkNotNullParameter(extra, "extra");
        return new VoicePrint(CollectionsKt.plus((Collection) this.samples, (Iterable) extra), this.threshold);
    }

    public final String encode() {
        StringBuilder $this$encode_u24lambda_u240 = new StringBuilder();
        $this$encode_u24lambda_u240.append(this.threshold).append('\n');
        Iterable $this$forEach$iv = this.samples;
        for (Object element$iv : $this$forEach$iv) {
            float[] it = (float[]) element$iv;
            $this$encode_u24lambda_u240.append(ArraysKt.joinToString$default(it, (CharSequence) ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null)).append('\n');
        }
        return $this$encode_u24lambda_u240.toString();
    }

    /* JADX INFO: compiled from: VoicePrint.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eJ\u001a\u0010\u0010\u001a\u00020\f2\u0012\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u000eJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0013\u001a\u00020\u0014J\u0016\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u000fJ\u001e\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u000f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/jarvis/wakeword/VoicePrint$Companion;", "", "<init>", "()V", "SAMPLES_NEEDED", "", "BEST_MATCHES", "MARGIN", "", "MIN_THRESHOLD", "MAX_THRESHOLD", "enroll", "Lcom/jarvis/wakeword/VoicePrint;", "samples", "", "", "enrollGroups", "groups", "decode", VoiceService.EXTRA_TEXT, "", "cosine", "a", "b", "bestMean", "embedding", "wakeword-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final VoicePrint enroll(List<float[]> samples) {
            Intrinsics.checkNotNullParameter(samples, "samples");
            List<float[]> $this$map$iv = samples;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                float[] it = (float[]) item$iv$iv;
                destination$iv$iv.add(CollectionsKt.listOf(it));
            }
            return enrollGroups((List) destination$iv$iv);
        }

        public final VoicePrint enrollGroups(List<? extends List<float[]>> groups) {
            Intrinsics.checkNotNullParameter(groups, "groups");
            if (!(groups.size() >= 2)) {
                throw new IllegalArgumentException("need at least two samples".toString());
            }
            Iterable $this$flatMap$iv = CollectionsKt.getIndices(groups);
            int $i$f$flatMap = 0;
            Collection destination$iv$iv = new ArrayList();
            Iterator<Integer> it = $this$flatMap$iv.iterator();
            while (it.hasNext()) {
                int element$iv$iv = ((IntIterator) it).nextInt();
                List<? extends List<float[]>> $this$filterIndexed$iv = groups;
                Collection destination$iv$iv2 = new ArrayList();
                int index$iv$iv = 0;
                for (Object item$iv$iv$iv : $this$filterIndexed$iv) {
                    int index$iv$iv$iv = index$iv$iv + 1;
                    if (index$iv$iv < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    int j = index$iv$iv;
                    int j2 = j != element$iv$iv ? 1 : 0;
                    if (j2 != 0) {
                        destination$iv$iv2.add(item$iv$iv$iv);
                    }
                    index$iv$iv = index$iv$iv$iv;
                }
                List<float[]> listFlatten = CollectionsKt.flatten((List) destination$iv$iv2);
                Iterable $this$map$iv = groups.get(element$iv$iv);
                Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    Iterable $this$flatMap$iv2 = $this$flatMap$iv;
                    float[] it2 = (float[]) item$iv$iv;
                    destination$iv$iv3.add(Float.valueOf(VoicePrint.INSTANCE.bestMean(it2, listFlatten)));
                    $this$flatMap$iv = $this$flatMap$iv2;
                    $i$f$flatMap = $i$f$flatMap;
                }
                Iterable $this$flatMap$iv3 = $this$flatMap$iv;
                Iterable list$iv$iv = (List) destination$iv$iv3;
                CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
                $this$flatMap$iv = $this$flatMap$iv3;
            }
            List own = (List) destination$iv$iv;
            float threshold = RangesKt.coerceIn(((float) CollectionsKt.averageOfFloat(own)) - VoicePrint.MARGIN, VoicePrint.MIN_THRESHOLD, 0.5f);
            return new VoicePrint(CollectionsKt.flatten(groups), threshold);
        }

        /* JADX WARN: Code duplicated, block: B:35:0x0126  */
        /* JADX WARN: Code duplicated, block: B:41:0x0130  */
        public final VoicePrint decode(String text) {
            Object objM392constructorimpl;
            boolean z;
            boolean z2;
            boolean z3;
            Intrinsics.checkNotNullParameter(text, "text");
            try {
                Result.Companion companion = Result.INSTANCE;
                Companion $this$decode_u24lambda_u240 = this;
                Iterable $this$filter$iv = StringsKt.lines(text);
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv : $this$filter$iv) {
                    if (!StringsKt.isBlank((String) element$iv$iv)) {
                        destination$iv$iv.add(element$iv$iv);
                    }
                }
                List lines = (List) destination$iv$iv;
                float threshold = Float.parseFloat((String) CollectionsKt.first(lines));
                int i = 1;
                Iterable $this$map$iv = CollectionsKt.drop(lines, 1);
                int i2 = 10;
                Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    String line = (String) item$iv$iv;
                    char[] cArr = new char[i];
                    cArr[0] = ',';
                    Iterable $this$map$iv2 = StringsKt.split$default((CharSequence) line, cArr, false, 0, 6, (Object) null);
                    Companion $this$decode_u24lambda_u241 = $this$decode_u24lambda_u240;
                    Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, i2));
                    for (Object item$iv$iv2 : $this$map$iv2) {
                        destination$iv$iv3.add(Float.valueOf(Float.parseFloat((String) item$iv$iv2)));
                    }
                    destination$iv$iv2.add(CollectionsKt.toFloatArray((List) destination$iv$iv3));
                    $this$decode_u24lambda_u240 = $this$decode_u24lambda_u241;
                    i = 1;
                    i2 = 10;
                }
                List samples = (List) destination$iv$iv2;
                if (samples.size() >= 2) {
                    List $this$all$iv = samples;
                    if (!($this$all$iv instanceof Collection) || !$this$all$iv.isEmpty()) {
                        Iterator it = $this$all$iv.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                Object element$iv = it.next();
                                float[] it2 = (float[]) element$iv;
                                if (it2.length != ((float[]) samples.get(0)).length) {
                                    z3 = false;
                                } else if (it2.length == 0) {
                                    z3 = false;
                                } else {
                                    z3 = true;
                                }
                                if (!z3) {
                                    z2 = false;
                                    break;
                                }
                            } else {
                                z2 = true;
                                break;
                            }
                        }
                    } else {
                        z2 = true;
                    }
                    if (z2) {
                        z = true;
                    } else {
                        z = false;
                    }
                } else {
                    z = false;
                }
                boolean valid = z;
                objM392constructorimpl = Result.m392constructorimpl(valid ? new VoicePrint(samples, threshold) : null);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
            }
            return (VoicePrint) (Result.m398isFailureimpl(objM392constructorimpl) ? null : objM392constructorimpl);
        }

        public final float cosine(float[] a, float[] b) {
            Intrinsics.checkNotNullParameter(a, "a");
            Intrinsics.checkNotNullParameter(b, "b");
            if (a.length == b.length) {
                if (!(a.length == 0)) {
                    double dot = 0.0d;
                    double na = 0.0d;
                    double nb = 0.0d;
                    int length = a.length;
                    for (int i = 0; i < length; i++) {
                        dot += (double) (a[i] * b[i]);
                        na += (double) (a[i] * a[i]);
                        nb += (double) (b[i] * b[i]);
                    }
                    if (!(na == 0.0d)) {
                        if (!(nb == 0.0d)) {
                            return (float) (dot / (Math.sqrt(na) * Math.sqrt(nb)));
                        }
                    }
                    return 0.0f;
                }
            }
            return 0.0f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final float bestMean(float[] embedding, List<float[]> samples) {
            List<float[]> $this$map$iv = samples;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                float[] it = (float[]) item$iv$iv;
                destination$iv$iv.add(Float.valueOf(VoicePrint.INSTANCE.cosine(embedding, it)));
            }
            List scores = CollectionsKt.sortedDescending((List) destination$iv$iv);
            return (float) CollectionsKt.averageOfFloat(CollectionsKt.take(scores, 3));
        }
    }
}
