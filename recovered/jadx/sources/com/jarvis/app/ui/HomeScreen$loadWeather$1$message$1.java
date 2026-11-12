package com.jarvis.app.ui;

import com.jarvis.app.VoiceController;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.tools.WeatherTool;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: HomeLive.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.jarvis.app.ui.HomeScreen$loadWeather$1$message$1", f = "HomeLive.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class HomeScreen$loadWeather$1$message$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ HomeScreen this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HomeScreen$loadWeather$1$message$1(HomeScreen homeScreen, Continuation<? super HomeScreen$loadWeather$1$message$1> continuation) {
        super(2, continuation);
        this.this$0 = homeScreen;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        HomeScreen$loadWeather$1$message$1 homeScreen$loadWeather$1$message$1 = new HomeScreen$loadWeather$1$message$1(this.this$0, continuation);
        homeScreen$loadWeather$1$message$1.L$0 = obj;
        return homeScreen$loadWeather$1$message$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((HomeScreen$loadWeather$1$message$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object objM392constructorimpl;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                HomeScreen homeScreen = this.this$0;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM392constructorimpl = Result.m392constructorimpl(new WeatherTool(homeScreen.getHost()).execute(new ToolCall(null, VoiceController.ENTRY_UI, "weather", MapsKt.emptyMap(), 1, null)).getMessage());
                    break;
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m398isFailureimpl(objM392constructorimpl)) {
                    return null;
                }
                return objM392constructorimpl;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
