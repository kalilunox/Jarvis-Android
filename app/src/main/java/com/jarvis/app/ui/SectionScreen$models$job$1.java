package com.jarvis.app.ui;

import android.widget.LinearLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.google.mlkit.common.MlKitException;
import com.jarvis.app.ModelManager;
import java.util.Map;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: More.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.jarvis.app.ui.SectionScreen$models$job$1", f = "More.kt", i = {}, l = {MlKitException.CODE_SCANNER_PIPELINE_INITIALIZATION_ERROR}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class SectionScreen$models$job$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ LinearLayout $list;
    final /* synthetic */ ModelManager $mgr;
    int label;
    final /* synthetic */ SectionScreen this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SectionScreen$models$job$1(SectionScreen sectionScreen, ModelManager modelManager, LinearLayout linearLayout, Continuation<? super SectionScreen$models$job$1> continuation) {
        super(2, continuation);
        this.this$0 = sectionScreen;
        this.$mgr = modelManager;
        this.$list = linearLayout;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SectionScreen$models$job$1(this.this$0, this.$mgr, this.$list, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SectionScreen$models$job$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: renamed from: com.jarvis.app.ui.SectionScreen$models$job$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: More.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.ui.SectionScreen$models$job$1$1", f = "More.kt", i = {}, l = {MlKitException.CODE_SCANNER_PIPELINE_INITIALIZATION_ERROR}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ LinearLayout $list;
        final /* synthetic */ ModelManager $mgr;
        int label;
        final /* synthetic */ SectionScreen this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(ModelManager modelManager, LinearLayout linearLayout, SectionScreen sectionScreen, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$mgr = modelManager;
            this.$list = linearLayout;
            this.this$0 = sectionScreen;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$mgr, this.$list, this.this$0, continuation);
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
                    final LinearLayout linearLayout = this.$list;
                    final ModelManager modelManager = this.$mgr;
                    final SectionScreen sectionScreen = this.this$0;
                    this.label = 1;
                    if (statuses.collect(new FlowCollector() { // from class: com.jarvis.app.ui.SectionScreen.models.job.1.1.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit((Map<String, ModelManager.Status>) value, (Continuation<? super Unit>) $completion);
                        }

                        public final Object emit(Map<String, ModelManager.Status> map, Continuation<? super Unit> continuation) {
                            if (linearLayout.isAttachedToWindow()) {
                                SectionScreen.models$render(linearLayout, modelManager, sectionScreen);
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
                if (RepeatOnLifecycleKt.repeatOnLifecycle(this.this$0.getHost(), Lifecycle.State.STARTED, new AnonymousClass1(this.$mgr, this.$list, this.this$0, null), this) == coroutine_suspended) {
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
