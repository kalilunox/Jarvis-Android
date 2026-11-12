package com.jarvis.app;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.jarvis.brain.AssistantOrchestrator;
import com.jarvis.core.common.model.UserRequest;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* JADX INFO: compiled from: TestConsoleActivity.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J(\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016H\u0002J\u0018\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J-\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u001c2\u000e\u0010\u001d\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001f0\u001e2\u0006\u0010 \u001a\u00020!H\u0016¢\u0006\u0002\u0010\"J\b\u0010#\u001a\u00020\u000fH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b¨\u0006$"}, d2 = {"Lcom/jarvis/app/TestConsoleActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "scope", "Lkotlinx/coroutines/CoroutineScope;", "voiceListener", "Lcom/jarvis/app/VoiceController$Listener;", "brain", "Lcom/jarvis/brain/AssistantOrchestrator;", "getBrain", "()Lcom/jarvis/brain/AssistantOrchestrator;", "brain$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "startVoice", "btnVoice", "Landroid/widget/Button;", "voiceStatus", "Landroid/widget/TextView;", "status", "result", "stopVoice", "onRequestPermissionsResult", "requestCode", "", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onDestroy", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TestConsoleActivity extends AppCompatActivity {
    private VoiceController.Listener voiceListener;
    private final CoroutineScope scope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getMain()));

    /* JADX INFO: renamed from: brain$delegate, reason: from kotlin metadata */
    private final Lazy brain = LazyKt.lazy(new Function0() { // from class: com.jarvis.app.TestConsoleActivity$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return TestConsoleActivity.brain_delegate$lambda$0(this.f$0);
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    public final AssistantOrchestrator getBrain() {
        return (AssistantOrchestrator) this.brain.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AssistantOrchestrator brain_delegate$lambda$0(TestConsoleActivity this$0) {
        return BrainGraph.INSTANCE.build(this$0);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_console);
        final EditText input = (EditText) findViewById(R.id.input);
        final TextView status = (TextView) findViewById(R.id.status);
        final TextView result = (TextView) findViewById(R.id.result);
        final TextView voiceStatus = (TextView) findViewById(R.id.voiceStatus);
        final Button btnVoice = (Button) findViewById(R.id.btnVoice);
        ((Button) findViewById(R.id.btnSend)).setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.TestConsoleActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TestConsoleActivity.onCreate$lambda$0(input, status, result, this, view);
            }
        });
        btnVoice.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.TestConsoleActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TestConsoleActivity.onCreate$lambda$1(this.f$0, btnVoice, voiceStatus, status, result, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(EditText $input, TextView $status, TextView $result, TestConsoleActivity this$0, View it) {
        String string;
        Editable text = $input.getText();
        String text2 = (text == null || (string = text.toString()) == null) ? null : StringsKt.trim((CharSequence) string).toString();
        if (text2 == null) {
            text2 = "";
        }
        if (text2.length() == 0) {
            return;
        }
        $status.setText("processing…");
        $result.setText("");
        BuildersKt__Builders_commonKt.launch$default(this$0.scope, null, null, new TestConsoleActivity$onCreate$1$1($status, $result, this$0, text2, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(TestConsoleActivity this$0, Button $btnVoice, TextView $voiceStatus, TextView $status, TextView $result, View it) {
        if (VoiceController.INSTANCE.isRunning()) {
            Intrinsics.checkNotNull($btnVoice);
            Intrinsics.checkNotNull($voiceStatus);
            this$0.stopVoice($btnVoice, $voiceStatus);
        } else {
            Intrinsics.checkNotNull($btnVoice);
            Intrinsics.checkNotNull($voiceStatus);
            Intrinsics.checkNotNull($status);
            Intrinsics.checkNotNull($result);
            this$0.startVoice($btnVoice, $voiceStatus, $status, $result);
        }
    }

    private final void startVoice(Button btnVoice, final TextView voiceStatus, final TextView status, final TextView result) {
        if (checkSelfPermission("android.permission.RECORD_AUDIO") != 0) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.RECORD_AUDIO"}, 100);
            return;
        }
        VoiceController.Listener listener = new VoiceController.Listener() { // from class: com.jarvis.app.TestConsoleActivity$startVoice$listener$1
            @Override // com.jarvis.app.VoiceController.Listener
            public /* bridge */ void onPartial(String text) {
                super.onPartial(text);
            }

            @Override // com.jarvis.app.VoiceController.Listener
            public void onWake() {
                voiceStatus.setText("Wake! speak the command…");
            }

            @Override // com.jarvis.app.VoiceController.Listener
            public void onRequest(UserRequest userRequest) {
                Intrinsics.checkNotNullParameter(userRequest, "userRequest");
                String text = userRequest.getText();
                if (StringsKt.isBlank(text)) {
                    status.setText("[VOICE] wake — say the command");
                    result.setText("");
                } else {
                    status.setText("[VOICE] \"" + text + "\"");
                    result.setText("обрабатывается сервисом…");
                }
            }
        };
        VoiceController.INSTANCE.addListener(listener);
        this.voiceListener = listener;
        VoiceController voiceController = VoiceController.INSTANCE;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        voiceController.start(applicationContext);
        btnVoice.setText("Stop Voice");
        voiceStatus.setText("Listening... say \"джарвис, ...\"");
    }

    private final void stopVoice(Button btnVoice, TextView voiceStatus) {
        VoiceController.Listener it = this.voiceListener;
        if (it != null) {
            VoiceController.INSTANCE.removeListener(it);
        }
        this.voiceListener = null;
        VoiceController voiceController = VoiceController.INSTANCE;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        voiceController.stop(applicationContext);
        btnVoice.setText("Start Voice — say \"джарвис\"");
        voiceStatus.setText("Stopped");
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Integer numFirstOrNull;
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && (numFirstOrNull = ArraysKt.firstOrNull(grantResults)) != null && numFirstOrNull.intValue() == 0) {
            Button btnVoice = (Button) findViewById(R.id.btnVoice);
            TextView voiceStatus = (TextView) findViewById(R.id.voiceStatus);
            TextView status = (TextView) findViewById(R.id.status);
            TextView result = (TextView) findViewById(R.id.result);
            Intrinsics.checkNotNull(btnVoice);
            Intrinsics.checkNotNull(voiceStatus);
            Intrinsics.checkNotNull(status);
            Intrinsics.checkNotNull(result);
            startVoice(btnVoice, voiceStatus, status, result);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        VoiceController.Listener it = this.voiceListener;
        if (it != null) {
            VoiceController.INSTANCE.removeListener(it);
        }
        CoroutineScopeKt.cancel$default(this.scope, null, 1, null);
        super.onDestroy();
    }
}
