package com.jarvis.app.ui;

import android.widget.LinearLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.jarvis.app.ModelManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: More.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.jarvis.app.ui.SectionScreen$voiceLock$job$1", f = "More.kt", i = {}, l = {306}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class SectionScreen$voiceLock$job$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ LinearLayout $box;
    final /* synthetic */ ModelManager $mgr;
    final /* synthetic */ ArrayList<List<float[]>> $phrases;
    final /* synthetic */ Ref.BooleanRef $recording;
    final /* synthetic */ Ref.ObjectRef<Function0<Unit>> $render;
    final /* synthetic */ ArrayList<float[]> $samples;
    int label;
    final /* synthetic */ SectionScreen this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SectionScreen$voiceLock$job$1(SectionScreen sectionScreen, ModelManager modelManager, LinearLayout linearLayout, ArrayList<float[]> arrayList, ArrayList<List<float[]>> arrayList2, Ref.BooleanRef booleanRef, Ref.ObjectRef<Function0<Unit>> objectRef, Continuation<? super SectionScreen$voiceLock$job$1> continuation) {
        super(2, continuation);
        this.this$0 = sectionScreen;
        this.$mgr = modelManager;
        this.$box = linearLayout;
        this.$samples = arrayList;
        this.$phrases = arrayList2;
        this.$recording = booleanRef;
        this.$render = objectRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SectionScreen$voiceLock$job$1(this.this$0, this.$mgr, this.$box, this.$samples, this.$phrases, this.$recording, this.$render, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SectionScreen$voiceLock$job$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: renamed from: com.jarvis.app.ui.SectionScreen$voiceLock$job$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: More.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.ui.SectionScreen$voiceLock$job$1$1", f = "More.kt", i = {}, l = {307}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ LinearLayout $box;
        final /* synthetic */ ModelManager $mgr;
        final /* synthetic */ ArrayList<List<float[]>> $phrases;
        final /* synthetic */ Ref.BooleanRef $recording;
        final /* synthetic */ Ref.ObjectRef<Function0<Unit>> $render;
        final /* synthetic */ ArrayList<float[]> $samples;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(ModelManager modelManager, LinearLayout linearLayout, ArrayList<float[]> arrayList, ArrayList<List<float[]>> arrayList2, Ref.BooleanRef booleanRef, Ref.ObjectRef<Function0<Unit>> objectRef, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$mgr = modelManager;
            this.$box = linearLayout;
            this.$samples = arrayList;
            this.$phrases = arrayList2;
            this.$recording = booleanRef;
            this.$render = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$mgr, this.$box, this.$samples, this.$phrases, this.$recording, this.$render, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    StateFlow<Map<String, ModelManager.Status>> statuses = this.$mgr.getStatuses();
                    final LinearLayout linearLayout = this.$box;
                    final ArrayList<float[]> arrayList = this.$samples;
                    final ArrayList<List<float[]>> arrayList2 = this.$phrases;
                    final Ref.BooleanRef booleanRef = this.$recording;
                    final Ref.ObjectRef<Function0<Unit>> objectRef = this.$render;
                    this.label = 1;
                    if (statuses.collect(new FlowCollector() { // from class: com.jarvis.app.ui.SectionScreen.voiceLock.job.1.1.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit((Map<String, ModelManager.Status>) value, (Continuation<? super Unit>) $completion);
                        }

                        public final Object emit(Map<String, ModelManager.Status> map, Continuation<? super Unit> continuation) {
                            Function0<Unit> function0;
                            if (linearLayout.isAttachedToWindow() && arrayList.isEmpty() && arrayList2.isEmpty() && !booleanRef.element) {
                                if (objectRef.element == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("render");
                                    function0 = null;
                                } else {
                                    function0 = objectRef.element;
                                }
                                function0.invoke();
                            }
                            return Unit.INSTANCE;
                        }
                    }, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            throw new KotlinNothingValueException();
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(this.this$0.getHost(), Lifecycle.State.STARTED, new AnonymousClass1(this.$mgr, this.$box, this.$samples, this.$phrases, this.$recording, this.$render, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
