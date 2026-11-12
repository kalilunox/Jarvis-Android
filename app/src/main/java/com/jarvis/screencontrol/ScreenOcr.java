package com.jarvis.screencontrol;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;
import com.jarvis.app.VoiceService;
import com.jarvis.core.nlp.Transliterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ScreenOcr.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0012J\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\b\u0002\u0010\u0011\u001a\u00020\u0012J\u001e\u0010\u0014\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0015\u001a\u00020\u00052\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000e0\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/jarvis/screencontrol/ScreenOcr;", "", "<init>", "()V", "TAG", "", "recognizer", "Lcom/google/mlkit/vision/text/TextRecognizer;", "getRecognizer", "()Lcom/google/mlkit/vision/text/TextRecognizer;", "recognizer$delegate", "Lkotlin/Lazy;", "recognize", "", "Lcom/jarvis/screencontrol/ScreenOcr$Block;", "bitmap", "Landroid/graphics/Bitmap;", "timeoutMs", "", "recognizeScreen", "findBest", SearchIntents.EXTRA_QUERY, "blocks", "Block", "screen-control_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ScreenOcr {
    private static final String TAG = "Jarvis.OCR";
    public static final ScreenOcr INSTANCE = new ScreenOcr();

    /* JADX INFO: renamed from: recognizer$delegate, reason: from kotlin metadata */
    private static final Lazy recognizer = LazyKt.lazy(new Function0() { // from class: com.jarvis.screencontrol.ScreenOcr$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return ScreenOcr.recognizer_delegate$lambda$0();
        }
    });

    private ScreenOcr() {
    }

    /* JADX INFO: compiled from: ScreenOcr.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/jarvis/screencontrol/ScreenOcr$Block;", "", VoiceService.EXTRA_TEXT, "", "bounds", "Landroid/graphics/Rect;", "<init>", "(Ljava/lang/String;Landroid/graphics/Rect;)V", "getText", "()Ljava/lang/String;", "getBounds", "()Landroid/graphics/Rect;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "screen-control_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Block {
        private final Rect bounds;
        private final String text;

        public static /* synthetic */ Block copy$default(Block block, String str, Rect rect, int i, Object obj) {
            if ((i & 1) != 0) {
                str = block.text;
            }
            if ((i & 2) != 0) {
                rect = block.bounds;
            }
            return block.copy(str, rect);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getText() {
            return this.text;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Rect getBounds() {
            return this.bounds;
        }

        public final Block copy(String text, Rect bounds) {
            Intrinsics.checkNotNullParameter(text, "text");
            Intrinsics.checkNotNullParameter(bounds, "bounds");
            return new Block(text, bounds);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Block)) {
                return false;
            }
            Block block = (Block) other;
            return Intrinsics.areEqual(this.text, block.text) && Intrinsics.areEqual(this.bounds, block.bounds);
        }

        public int hashCode() {
            return (this.text.hashCode() * 31) + this.bounds.hashCode();
        }

        public String toString() {
            return "Block(text=" + this.text + ", bounds=" + this.bounds + ")";
        }

        public Block(String text, Rect bounds) {
            Intrinsics.checkNotNullParameter(text, "text");
            Intrinsics.checkNotNullParameter(bounds, "bounds");
            this.text = text;
            this.bounds = bounds;
        }

        public final String getText() {
            return this.text;
        }

        public final Rect getBounds() {
            return this.bounds;
        }
    }

    private final TextRecognizer getRecognizer() {
        return (TextRecognizer) recognizer.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextRecognizer recognizer_delegate$lambda$0() {
        return TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
    }

    public static /* synthetic */ List recognize$default(ScreenOcr screenOcr, Bitmap bitmap, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 8000;
        }
        return screenOcr.recognize(bitmap, j);
    }

    public final List<Block> recognize(Bitmap bitmap, long timeoutMs) throws InterruptedException {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        InputImage image = InputImage.fromBitmap(bitmap, 0);
        Intrinsics.checkNotNullExpressionValue(image, "fromBitmap(...)");
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicReference out = new AtomicReference(CollectionsKt.emptyList());
        Task<Text> taskProcess = getRecognizer().process(image);
        final Function1 function1 = new Function1() { // from class: com.jarvis.screencontrol.ScreenOcr$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ScreenOcr.recognize$lambda$0(out, latch, (Text) obj);
            }
        };
        taskProcess.addOnSuccessListener(new OnSuccessListener() { // from class: com.jarvis.screencontrol.ScreenOcr$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                function1.invoke(obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.jarvis.screencontrol.ScreenOcr$$ExternalSyntheticLambda3
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                ScreenOcr.recognize$lambda$2(latch, exc);
            }
        });
        latch.await(timeoutMs, TimeUnit.MILLISECONDS);
        Object obj = out.get();
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        return (List) obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit recognize$lambda$0(AtomicReference $out, CountDownLatch $latch, Text result) {
        ArrayList arrayList = new ArrayList();
        for (Text.TextBlock block : result.getTextBlocks()) {
            Rect box = block.getBoundingBox();
            if (box != null) {
                String text = block.getText();
                Intrinsics.checkNotNullExpressionValue(text, "getText(...)");
                String t = StringsKt.trim((CharSequence) text).toString();
                if (!StringsKt.isBlank(t)) {
                    arrayList.add(new Block(t, new Rect(box)));
                }
                for (Text.Line line : block.getLines()) {
                    Rect lb = line.getBoundingBox();
                    if (lb != null) {
                        String text2 = line.getText();
                        Intrinsics.checkNotNullExpressionValue(text2, "getText(...)");
                        String lt = StringsKt.trim((CharSequence) text2).toString();
                        if (!StringsKt.isBlank(lt) && !Intrinsics.areEqual(lt, t)) {
                            arrayList.add(new Block(lt, new Rect(lb)));
                        }
                    }
                }
            }
        }
        $out.set(arrayList);
        $latch.countDown();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void recognize$lambda$2(CountDownLatch $latch, Exception e) {
        Intrinsics.checkNotNullParameter(e, "e");
        Log.w(TAG, "OCR failed: " + e.getMessage());
        $latch.countDown();
    }

    public static /* synthetic */ List recognizeScreen$default(ScreenOcr screenOcr, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 8000;
        }
        return screenOcr.recognizeScreen(j);
    }

    public final List<Block> recognizeScreen(long timeoutMs) {
        Bitmap bmp = ScreenController.takeScreenshotBitmap$default(ScreenController.INSTANCE, 0L, 1, null);
        if (bmp == null) {
            return CollectionsKt.emptyList();
        }
        try {
            return recognize(bmp, timeoutMs);
        } finally {
            bmp.recycle();
        }
    }

    public final Block findBest(String query, List<Block> blocks) {
        Object maxElem$iv;
        String str;
        Iterable $this$mapNotNull$iv;
        int $i$f$mapNotNull;
        Iterable $this$mapNotNullTo$iv$iv;
        int $i$f$mapNotNullTo;
        int score;
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(blocks, "blocks");
        String lowerCase = query.toLowerCase(Locale.ROOT);
        String str2 = "toLowerCase(...)";
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String q = StringsKt.trim((CharSequence) lowerCase).toString();
        if (StringsKt.isBlank(q) || blocks.isEmpty()) {
            return null;
        }
        String qKey = Transliterator.INSTANCE.compareKey(q);
        List<Block> $this$mapNotNull$iv2 = blocks;
        int $i$f$mapNotNull2 = 0;
        Collection destination$iv$iv = new ArrayList();
        Iterable $this$mapNotNullTo$iv$iv2 = $this$mapNotNull$iv2;
        int $i$f$mapNotNullTo2 = 0;
        for (Object element$iv$iv$iv : $this$mapNotNullTo$iv$iv2) {
            Block b = (Block) element$iv$iv$iv;
            String t = b.getText().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(t, str2);
            String key = Transliterator.INSTANCE.compareKey(t);
            if (Intrinsics.areEqual(t, q)) {
                $this$mapNotNull$iv = $this$mapNotNull$iv2;
                $i$f$mapNotNull = $i$f$mapNotNull2;
                $this$mapNotNullTo$iv$iv = $this$mapNotNullTo$iv$iv2;
                $i$f$mapNotNullTo = $i$f$mapNotNullTo2;
                str = str2;
                score = 100;
            } else {
                str = str2;
                $this$mapNotNull$iv = $this$mapNotNull$iv2;
                $i$f$mapNotNull = $i$f$mapNotNull2;
                $this$mapNotNullTo$iv$iv = $this$mapNotNullTo$iv$iv2;
                $i$f$mapNotNullTo = $i$f$mapNotNullTo2;
                score = (StringsKt.contains$default((CharSequence) t, (CharSequence) q, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) t, false, 2, (Object) null)) ? 80 : (StringsKt.contains$default((CharSequence) key, (CharSequence) qKey, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) qKey, (CharSequence) key, false, 2, (Object) null)) ? 70 : 0;
            }
            Pair pair = score > 0 ? TuplesKt.to(b, Integer.valueOf(score)) : null;
            if (pair != null) {
                destination$iv$iv.add(pair);
            }
            str2 = str;
            $this$mapNotNull$iv2 = $this$mapNotNull$iv;
            $i$f$mapNotNull2 = $i$f$mapNotNull;
            $this$mapNotNullTo$iv$iv2 = $this$mapNotNullTo$iv$iv;
            $i$f$mapNotNullTo2 = $i$f$mapNotNullTo;
        }
        Iterable $this$maxByOrNull$iv = (List) destination$iv$iv;
        Iterator iterator$iv = $this$maxByOrNull$iv.iterator();
        if (iterator$iv.hasNext()) {
            maxElem$iv = iterator$iv.next();
            if (iterator$iv.hasNext()) {
                Pair it = (Pair) maxElem$iv;
                int maxValue$iv = ((Number) it.getSecond()).intValue();
                do {
                    Object e$iv = iterator$iv.next();
                    Pair it2 = (Pair) e$iv;
                    int v$iv = ((Number) it2.getSecond()).intValue();
                    if (maxValue$iv < v$iv) {
                        maxValue$iv = v$iv;
                        maxElem$iv = e$iv;
                    }
                } while (iterator$iv.hasNext());
            }
        } else {
            maxElem$iv = null;
        }
        Pair pair2 = (Pair) maxElem$iv;
        if (pair2 != null) {
            return (Block) pair2.getFirst();
        }
        return null;
    }
}
