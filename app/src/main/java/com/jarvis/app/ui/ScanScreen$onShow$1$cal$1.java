package com.jarvis.app.ui;

import com.jarvis.tools.CalendarTool;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Screens.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.jarvis.app.ui.ScanScreen$onShow$1$cal$1", f = "Screens.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class ScanScreen$onShow$1$cal$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    int label;
    final /* synthetic */ ScanScreen this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ScanScreen$onShow$1$cal$1(ScanScreen scanScreen, Continuation<? super ScanScreen$onShow$1$cal$1> continuation) {
        super(2, continuation);
        this.this$0 = scanScreen;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ScanScreen$onShow$1$cal$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((ScanScreen$onShow$1$cal$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String invokeSuspend$lambda$0(ScanScreen this$0) {
        return new CalendarTool(this$0.getHost()).execute(this$0.call("calendar", MapsKt.mapOf(TuplesKt.to("action", "list"), TuplesKt.to("day", 0)))).getMessage();
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                ScanScreen scanScreen = this.this$0;
                final ScanScreen scanScreen2 = this.this$0;
                return scanScreen.tool(new Function0() { // from class: com.jarvis.app.ui.ScanScreen$onShow$1$cal$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ScanScreen$onShow$1$cal$1.invokeSuspend$lambda$0(scanScreen2);
                    }
                });
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
