package com.jarvis.app;

import android.widget.TextView;
import com.jarvis.core.common.model.AssistantResponse;
import com.jarvis.core.common.model.Intent;
import com.jarvis.core.common.model.IntentType;
import com.jarvis.core.common.model.ToolResult;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: TestConsoleActivity.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.jarvis.app.TestConsoleActivity$onCreate$1$1", f = "TestConsoleActivity.kt", i = {}, l = {46}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class TestConsoleActivity$onCreate$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ TextView $result;
    final /* synthetic */ TextView $status;
    final /* synthetic */ String $text;
    int label;
    final /* synthetic */ TestConsoleActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TestConsoleActivity$onCreate$1$1(TextView textView, TextView textView2, TestConsoleActivity testConsoleActivity, String str, Continuation<? super TestConsoleActivity$onCreate$1$1> continuation) {
        super(2, continuation);
        this.$status = textView;
        this.$result = textView2;
        this.this$0 = testConsoleActivity;
        this.$text = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TestConsoleActivity$onCreate$1$1(this.$status, this.$result, this.this$0, this.$text, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TestConsoleActivity$onCreate$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:14:0x007d  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object objWithContext;
        String toolLine;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                objWithContext = BuildersKt.withContext(Dispatchers.getDefault(), new TestConsoleActivity$onCreate$1$1$response$1(this.this$0, this.$text, null), this);
                if (objWithContext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                objWithContext = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        AssistantResponse response = (AssistantResponse) objWithContext;
        ToolResult it = response.getToolResult();
        if (it != null) {
            toolLine = "tool=" + it.getToolId() + " status=" + it.getStatus() + StringUtils.SPACE + it.getDurationMs() + "ms";
            if (toolLine == null) {
                toolLine = "tool=none";
            }
        } else {
            toolLine = "tool=none";
        }
        TextView textView = this.$status;
        Intent intent = response.getIntent();
        IntentType type = intent != null ? intent.getType() : null;
        Intent intent2 = response.getIntent();
        textView.setText("intent=" + type + " conf=" + (intent2 != null ? Boxing.boxFloat(intent2.getConfidence()) : null) + "\nstatus=" + response.getStatus() + " duration=" + response.getDurationMs() + "ms\n" + toolLine);
        this.$result.setText(response.getReplyText());
        return Unit.INSTANCE;
    }
}
