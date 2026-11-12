package com.jarvis.app;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlin.sequences.SequenceScope;

/* JADX INFO: compiled from: JarvisSpeaker.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0014\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", ""}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.jarvis.app.JarvisSpeaker$speak$2$blocks$1", f = "JarvisSpeaker.kt", i = {0, 0, 0, 0}, l = {407}, m = "invokeSuspend", n = {"$this$sequence", "s", "pcm", "r"}, s = {"L$0", "L$2", "L$3", "I$0"}, v = 1)
final class JarvisSpeaker$speak$2$blocks$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super float[]>, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $gen;
    final /* synthetic */ Ref.IntRef $rate;
    final /* synthetic */ List<String> $sentences;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ JarvisSpeaker this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    JarvisSpeaker$speak$2$blocks$1(List<String> list, int i, JarvisSpeaker jarvisSpeaker, Ref.IntRef intRef, Continuation<? super JarvisSpeaker$speak$2$blocks$1> continuation) {
        super(2, continuation);
        this.$sentences = list;
        this.$gen = i;
        this.this$0 = jarvisSpeaker;
        this.$rate = intRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        JarvisSpeaker$speak$2$blocks$1 jarvisSpeaker$speak$2$blocks$1 = new JarvisSpeaker$speak$2$blocks$1(this.$sentences, this.$gen, this.this$0, this.$rate, continuation);
        jarvisSpeaker$speak$2$blocks$1.L$0 = obj;
        return jarvisSpeaker$speak$2$blocks$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super float[]> sequenceScope, Continuation<? super Unit> continuation) {
        return ((JarvisSpeaker$speak$2$blocks$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0041  */
    /* JADX WARN: Code duplicated, block: B:12:0x0052  */
    /* JADX WARN: Code duplicated, block: B:14:0x0073  */
    /* JADX WARN: Code duplicated, block: B:18:0x0093 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:19:0x0094  */
    /* JADX WARN: Code duplicated, block: B:23:0x0099 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:24:0x0099 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:27:0x0074 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0094 -> B:20:0x0097). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:18:0x0093
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = r11.L$0
            kotlin.sequences.SequenceScope r0 = (kotlin.sequences.SequenceScope) r0
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r11.label
            switch(r2) {
                case 0: goto L2d;
                case 1: goto L15;
                default: goto Ld;
            }
        Ld:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L15:
            int r2 = r11.I$0
            java.lang.Object r3 = r11.L$3
            float[] r3 = (float[]) r3
            java.lang.Object r4 = r11.L$2
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r5 = r11.L$1
            java.util.Iterator r5 = (java.util.Iterator) r5
            kotlin.ResultKt.throwOnFailure(r12)
            r6 = r3
            r3 = r1
            r1 = r0
            r0 = r12
            r12 = r11
            goto L97
        L2d:
            kotlin.ResultKt.throwOnFailure(r12)
            java.util.List<java.lang.String> r2 = r11.$sentences
            java.util.Iterator r2 = r2.iterator()
            r5 = r2
            r2 = r1
            r1 = r0
            r0 = r12
            r12 = r11
        L3b:
            boolean r3 = r5.hasNext()
            if (r3 == 0) goto L99
            java.lang.Object r3 = r5.next()
            r4 = r3
            java.lang.String r4 = (java.lang.String) r4
            int r3 = r12.$gen
            com.jarvis.app.JarvisSpeaker r6 = r12.this$0
            int r6 = com.jarvis.app.JarvisSpeaker.access$getSpeakGeneration$p(r6)
            if (r3 != r6) goto L99
            com.jarvis.app.JarvisSpeaker r3 = r12.this$0
            int r6 = r12.$gen
            r7 = 0
            kotlin.Pair r3 = com.jarvis.app.JarvisSpeaker.access$synthPcm(r3, r4, r6, r7)
            java.lang.Object r6 = r3.component1()
            float[] r6 = (float[]) r6
            java.lang.Object r3 = r3.component2()
            java.lang.Number r3 = (java.lang.Number) r3
            int r3 = r3.intValue()
            kotlin.jvm.internal.Ref$IntRef r8 = r12.$rate
            r8.element = r3
            int r8 = r6.length
            r9 = 1
            if (r8 != 0) goto L74
            r7 = r9
        L74:
            if (r7 != 0) goto L3b
            r7 = r12
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r12.L$0 = r1
            r12.L$1 = r5
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)
            r12.L$2 = r8
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)
            r12.L$3 = r8
            r12.I$0 = r3
            r12.label = r9
            java.lang.Object r7 = r1.yield(r6, r7)
            if (r7 != r2) goto L94
            return r2
        L94:
            r10 = r3
            r3 = r2
            r2 = r10
        L97:
            r2 = r3
            goto L3b
        L99:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jarvis.app.JarvisSpeaker$speak$2$blocks$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
