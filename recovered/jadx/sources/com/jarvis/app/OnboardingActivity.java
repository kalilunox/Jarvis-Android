package com.jarvis.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.android.material.card.MaterialCardView;
import com.jarvis.onboarding.PermissionsHelper;
import com.jarvis.screencontrol.JarvisAccessibilityService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OnboardingActivity.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\b\u0010\u000f\u001a\u00020\fH\u0014J\b\u0010\u0010\u001a\u00020\fH\u0002J\b\u0010\u0011\u001a\u00020\fH\u0002J\b\u0010\u0012\u001a\u00020\fH\u0002J \u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u0019\u001a\n \u0007*\u0004\u0018\u00010\u001a0\u001aH\u0002R\u001c\u0010\u0004\u001a\u0010\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0010\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\t\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0006 \u0007*\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\n0\n0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/jarvis/app/OnboardingActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "audioLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "kotlin.jvm.PlatformType", "notificationLauncher", "runtimeBatchLauncher", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "setupButtons", "requestExtraRuntime", "refreshUi", "setStatus", "cardId", "", "btnId", "ok", "", "prefs", "Landroid/content/SharedPreferences;", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class OnboardingActivity extends AppCompatActivity {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String PREFS = "jarvis_prefs";
    public static final String PREF_AUTOSTART_VISITED = "autostart_visited_v1";
    public static final String PREF_ONBOARDING_DONE = "onboarding_done_v1";
    private final ActivityResultLauncher<String> audioLauncher;
    private final ActivityResultLauncher<String> notificationLauncher;
    private final ActivityResultLauncher<String[]> runtimeBatchLauncher;

    public OnboardingActivity() {
        ActivityResultLauncher<String> activityResultLauncherRegisterForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: com.jarvis.app.OnboardingActivity$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                this.f$0.refreshUi();
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "registerForActivityResult(...)");
        this.audioLauncher = activityResultLauncherRegisterForActivityResult;
        ActivityResultLauncher<String> activityResultLauncherRegisterForActivityResult2 = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: com.jarvis.app.OnboardingActivity$$ExternalSyntheticLambda4
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                this.f$0.refreshUi();
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult2, "registerForActivityResult(...)");
        this.notificationLauncher = activityResultLauncherRegisterForActivityResult2;
        ActivityResultLauncher<String[]> activityResultLauncherRegisterForActivityResult3 = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback() { // from class: com.jarvis.app.OnboardingActivity$$ExternalSyntheticLambda5
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                this.f$0.refreshUi();
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult3, "registerForActivityResult(...)");
        this.runtimeBatchLauncher = activityResultLauncherRegisterForActivityResult3;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        setupButtons();
        refreshUi();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        refreshUi();
    }

    private final void setupButtons() {
        ((Button) findViewById(R.id.btnRecordAudio)).setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.OnboardingActivity$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OnboardingActivity.setupButtons$lambda$0(this.f$0, view);
            }
        });
        ((Button) findViewById(R.id.btnNotifications)).setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.OnboardingActivity$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OnboardingActivity.setupButtons$lambda$1(this.f$0, view);
            }
        });
        ((Button) findViewById(R.id.btnOverlay)).setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.OnboardingActivity$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OnboardingActivity.setupButtons$lambda$2(this.f$0, view);
            }
        });
        ((Button) findViewById(R.id.btnBattery)).setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.OnboardingActivity$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OnboardingActivity.setupButtons$lambda$3(this.f$0, view);
            }
        });
        ((Button) findViewById(R.id.btnAutoStart)).setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.OnboardingActivity$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OnboardingActivity.setupButtons$lambda$4(this.f$0, view);
            }
        });
        ((Button) findViewById(R.id.btnAccessibility)).setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.OnboardingActivity$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OnboardingActivity.setupButtons$lambda$5(this.f$0, view);
            }
        });
        ((Button) findViewById(R.id.btnNotificationListener)).setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.OnboardingActivity$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OnboardingActivity.setupButtons$lambda$6(this.f$0, view);
            }
        });
        ((Button) findViewById(R.id.btnWriteSettings)).setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.OnboardingActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OnboardingActivity.setupButtons$lambda$7(this.f$0, view);
            }
        });
        ((Button) findViewById(R.id.btnStart)).setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.OnboardingActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OnboardingActivity.setupButtons$lambda$8(this.f$0, view);
            }
        });
        if (BuildConfig.DEBUG) {
            ((MaterialCardView) findViewById(R.id.stepDebugConsole)).setVisibility(0);
            ((Button) findViewById(R.id.btnDebugConsole)).setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.OnboardingActivity$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    OnboardingActivity.setupButtons$lambda$9(this.f$0, view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupButtons$lambda$0(OnboardingActivity this$0, View it) {
        this$0.audioLauncher.launch(PermissionsHelper.INSTANCE.audioRecordPermission());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupButtons$lambda$1(OnboardingActivity this$0, View it) {
        String it2 = PermissionsHelper.INSTANCE.postNotificationsPermission();
        if (it2 != null) {
            this$0.notificationLauncher.launch(it2);
        } else {
            this$0.refreshUi();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupButtons$lambda$2(OnboardingActivity this$0, View it) {
        PermissionsHelper.INSTANCE.openOverlaySettings(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupButtons$lambda$3(OnboardingActivity this$0, View it) {
        PermissionsHelper.INSTANCE.openBatteryOptimizationSettings(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupButtons$lambda$4(OnboardingActivity this$0, View it) {
        this$0.prefs().edit().putBoolean(PREF_AUTOSTART_VISITED, true).apply();
        this$0.refreshUi();
        PermissionsHelper.INSTANCE.openAutoStartSettings(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupButtons$lambda$5(OnboardingActivity this$0, View it) {
        PermissionsHelper.INSTANCE.openAccessibilitySettings(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupButtons$lambda$6(OnboardingActivity this$0, View it) {
        PermissionsHelper.INSTANCE.openNotificationListenerSettings(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupButtons$lambda$7(OnboardingActivity this$0, View it) {
        PermissionsHelper.INSTANCE.openWriteSettings(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupButtons$lambda$8(OnboardingActivity this$0, View it) {
        this$0.requestExtraRuntime();
        this$0.prefs().edit().putBoolean(PREF_ONBOARDING_DONE, true).putBoolean(PREF_AUTOSTART_VISITED, true).apply();
        VoiceController.INSTANCE.start(this$0);
        UiMode.INSTANCE.continueFrom(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupButtons$lambda$9(OnboardingActivity this$0, View it) {
        this$0.startActivity(new Intent(this$0, (Class<?>) TestConsoleActivity.class));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private final void requestExtraRuntime() {
        Iterable $this$filter$iv = CollectionsKt.listOf((Object[]) new String[]{"android.permission.READ_CONTACTS", "android.permission.CALL_PHONE", "android.permission.READ_CALL_LOG", "android.permission.SEND_SMS", "android.permission.READ_SMS", "android.permission.WRITE_CALENDAR", "android.permission.READ_CALENDAR", "android.permission.READ_PHONE_STATE", "android.permission.ACCESS_COARSE_LOCATION"});
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            if (ContextCompat.checkSelfPermission(this, it) != 0) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Collection needed = (List) destination$iv$iv;
        if (needed.isEmpty()) {
            return;
        }
        Collection $this$toTypedArray$iv = needed;
        this.runtimeBatchLauncher.launch($this$toTypedArray$iv.toArray(new String[0]));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshUi() {
        boolean audioOk = PermissionsHelper.INSTANCE.isAudioRecordGranted(this);
        boolean notifOk = PermissionsHelper.INSTANCE.isPostNotificationsGranted(this);
        boolean overlayOk = PermissionsHelper.INSTANCE.canDrawOverlays(this);
        boolean batteryOk = PermissionsHelper.INSTANCE.isIgnoringBatteryOptimizations(this);
        boolean a11yOk = PermissionsHelper.INSTANCE.isAccessibilityServiceEnabled(this, JarvisAccessibilityService.class);
        boolean nlsOk = PermissionsHelper.INSTANCE.isNotificationListenerEnabled(this);
        boolean writeOk = PermissionsHelper.INSTANCE.canWriteSettings(this);
        setStatus(R.id.stepRecordAudio, R.id.btnRecordAudio, audioOk);
        setStatus(R.id.stepNotifications, R.id.btnNotifications, notifOk);
        setStatus(R.id.stepOverlay, R.id.btnOverlay, overlayOk);
        setStatus(R.id.stepBattery, R.id.btnBattery, batteryOk);
        boolean autostartOk = prefs().getBoolean(PREF_AUTOSTART_VISITED, false);
        setStatus(R.id.stepAutoStart, R.id.btnAutoStart, autostartOk);
        setStatus(R.id.stepAccessibility, R.id.btnAccessibility, a11yOk);
        setStatus(R.id.stepNotificationListener, R.id.btnNotificationListener, nlsOk);
        setStatus(R.id.stepWriteSettings, R.id.btnWriteSettings, writeOk);
        boolean criticalsOk = audioOk && notifOk;
        ((Button) findViewById(R.id.btnStart)).setEnabled(criticalsOk || BuildConfig.DEBUG);
    }

    private final void setStatus(int cardId, int btnId, boolean ok) {
        MaterialCardView card = (MaterialCardView) findViewById(cardId);
        Button btn = (Button) findViewById(btnId);
        if (ok) {
            card.setStrokeColor(ContextCompat.getColor(this, R.color.status_ok));
            card.setStrokeWidth(3);
            btn.setText("Готово");
            btn.setEnabled(false);
            return;
        }
        card.setStrokeColor(ContextCompat.getColor(this, R.color.jarvis_primary));
        card.setStrokeWidth(2);
        btn.setEnabled(true);
        btn.setText("Настроить");
    }

    private final SharedPreferences prefs() {
        return getSharedPreferences("jarvis_prefs", 0);
    }

    /* JADX INFO: compiled from: OnboardingActivity.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/jarvis/app/OnboardingActivity$Companion;", "", "<init>", "()V", "PREFS", "", "PREF_ONBOARDING_DONE", "PREF_AUTOSTART_VISITED", "needsOnboarding", "", "activity", "Landroidx/appcompat/app/AppCompatActivity;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean needsOnboarding(AppCompatActivity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            SharedPreferences prefs = activity.getSharedPreferences("jarvis_prefs", 0);
            return !prefs.getBoolean(OnboardingActivity.PREF_ONBOARDING_DONE, false);
        }
    }
}
