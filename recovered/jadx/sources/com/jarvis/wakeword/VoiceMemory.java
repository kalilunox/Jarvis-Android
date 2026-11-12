package com.jarvis.wakeword;

import com.jarvis.app.AndroidReminderBook;
import com.jarvis.app.VoiceService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: VoicePrint.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0006J\u0006\u0010\u0014\u001a\u00020\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0017"}, d2 = {"Lcom/jarvis/wakeword/VoiceMemory;", "", "capacity", "", "initial", "", "", "<init>", "(ILjava/util/List;)V", AndroidReminderBook.KEY_ITEMS, "Lkotlin/collections/ArrayDeque;", "samples", "getSamples", "()Ljava/util/List;", "size", "getSize", "()I", "add", "", "embedding", "encode", "", "Companion", "wakeword-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VoiceMemory {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int capacity;
    private final ArrayDeque<float[]> items;

    public VoiceMemory(int capacity, List<float[]> initial) {
        Intrinsics.checkNotNullParameter(initial, "initial");
        this.capacity = capacity;
        this.items = new ArrayDeque<>(CollectionsKt.takeLast(initial, this.capacity));
    }

    public /* synthetic */ VoiceMemory(int i, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final List<float[]> getSamples() {
        return CollectionsKt.toList(this.items);
    }

    public final int getSize() {
        return this.items.size();
    }

    public final void add(float[] embedding) {
        Intrinsics.checkNotNullParameter(embedding, "embedding");
        this.items.addLast(embedding);
        while (this.items.size() > this.capacity) {
            this.items.removeFirst();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence encode$lambda$0(float[] it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return ArraysKt.joinToString$default(it, (CharSequence) ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    public final String encode() {
        return CollectionsKt.joinToString$default(this.items, StringUtils.LF, null, null, 0, null, new Function1() { // from class: com.jarvis.wakeword.VoiceMemory$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return VoiceMemory.encode$lambda$0((float[]) obj);
            }
        }, 30, null);
    }

    /* JADX INFO: compiled from: VoicePrint.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/jarvis/wakeword/VoiceMemory$Companion;", "", "<init>", "()V", "decode", "Lcom/jarvis/wakeword/VoiceMemory;", VoiceService.EXTRA_TEXT, "", "capacity", "", "wakeword-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final VoiceMemory decode(String text, int capacity) {
            Iterable $this$mapNotNull$iv;
            Object objM392constructorimpl;
            Intrinsics.checkNotNullParameter(text, "text");
            Iterable $this$filter$iv = StringsKt.lines(text);
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                String it = (String) element$iv$iv;
                if (!StringsKt.isBlank(it)) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            Iterable $this$mapNotNull$iv2 = (List) destination$iv$iv;
            Collection destination$iv$iv2 = new ArrayList();
            for (Object element$iv$iv$iv : $this$mapNotNull$iv2) {
                String line = (String) element$iv$iv$iv;
                Companion companion = VoiceMemory.INSTANCE;
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    Iterable $this$map$iv = StringsKt.split$default((CharSequence) line, new char[]{','}, false, 0, 6, (Object) null);
                    $this$mapNotNull$iv = $this$mapNotNull$iv2;
                    try {
                        Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                        for (Object item$iv$iv : $this$map$iv) {
                            String it2 = (String) item$iv$iv;
                            destination$iv$iv3.add(Float.valueOf(Float.parseFloat(it2)));
                            $this$map$iv = $this$map$iv;
                        }
                        objM392constructorimpl = Result.m392constructorimpl(CollectionsKt.toFloatArray((List) destination$iv$iv3));
                    } catch (Throwable th) {
                        th = th;
                        Result.Companion companion3 = Result.INSTANCE;
                        objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
                    }
                } catch (Throwable th2) {
                    th = th2;
                    $this$mapNotNull$iv = $this$mapNotNull$iv2;
                }
                if (Result.m398isFailureimpl(objM392constructorimpl)) {
                    objM392constructorimpl = null;
                }
                float[] fArr = (float[]) objM392constructorimpl;
                if (fArr != null) {
                    destination$iv$iv2.add(fArr);
                }
                $this$mapNotNull$iv2 = $this$mapNotNull$iv;
            }
            return new VoiceMemory(capacity, (List) destination$iv$iv2);
        }
    }
}
