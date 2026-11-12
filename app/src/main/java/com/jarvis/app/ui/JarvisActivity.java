package com.jarvis.app.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.jarvis.app.ActivityFeed;
import com.jarvis.app.ConversationStore;
import com.jarvis.app.DashboardActivity;
import com.jarvis.app.JarvisSettings;
import com.jarvis.app.OnboardingActivity;
import com.jarvis.app.OverlayOrbService;
import com.jarvis.app.UiMode;
import com.jarvis.app.VoiceController;
import com.jarvis.app.VoiceService;
import com.jarvis.core.common.model.ToolResultStatus;
import com.jarvis.core.common.model.UserRequest;
import com.jarvis.core.event.AssistantEvent;
import com.jarvis.core.event.JarvisEvents;
import com.jarvis.core.model.AssistantState;
import com.jarvis.core.startup.StartupRoute;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Function;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: JarvisActivity.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 i2\u00020\u0001:\u0001iB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010:\u001a\u00020;2\b\u0010<\u001a\u0004\u0018\u00010=H\u0014J\u0010\u0010>\u001a\u00020;2\u0006\u0010?\u001a\u00020=H\u0014J\u0010\u0010@\u001a\u00020;2\u0006\u0010A\u001a\u00020BH\u0014J\u0012\u0010C\u001a\u00020;2\b\u0010A\u001a\u0004\u0018\u00010BH\u0002J\b\u0010D\u001a\u00020;H\u0014J\b\u0010E\u001a\u00020;H\u0014J\b\u0010F\u001a\u00020;H\u0014J\b\u0010G\u001a\u00020\u001fH\u0002J\b\u0010H\u001a\u00020\u001fH\u0002J\u000e\u0010I\u001a\u00020;2\u0006\u0010J\u001a\u00020\u0011J\u0006\u0010K\u001a\u00020;J\u000e\u0010L\u001a\u00020;2\u0006\u0010M\u001a\u00020NJ\u0010\u0010O\u001a\u00020+2\u0006\u0010J\u001a\u00020\u0011H\u0002J\u0006\u0010P\u001a\u00020;J\u0018\u0010Q\u001a\u00020;2\u0006\u0010R\u001a\u00020\r2\u0006\u0010S\u001a\u00020\u001aH\u0002J\u0006\u0010T\u001a\u00020;J\u0006\u0010U\u001a\u00020;J\b\u0010V\u001a\u00020;H\u0002J\u0006\u0010W\u001a\u00020;J\b\u0010X\u001a\u00020;H\u0002J\u000e\u0010Y\u001a\u00020;2\u0006\u0010Z\u001a\u00020\u0011J\u0010\u0010[\u001a\u00020;2\u0006\u0010\\\u001a\u00020\tH\u0002J\u0010\u0010]\u001a\u00020;2\u0006\u0010^\u001a\u00020_H\u0002J*\u0010`\u001a\u00020;2\u0006\u0010a\u001a\u00020\u00112\b\b\u0002\u0010b\u001a\u00020\u00112\u0010\b\u0002\u0010c\u001a\n\u0012\u0004\u0012\u00020;\u0018\u00010dJ\b\u0010e\u001a\u00020;H\u0002J$\u0010f\u001a\u00020'2\u0006\u0010Z\u001a\u00020\u00112\u0006\u0010g\u001a\u00020\u001a2\f\u0010h\u001a\b\u0012\u0004\u0012\u00020;0dR\u001e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086.¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\n\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\r@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u0011@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u0011@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u001e\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u0011@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\u0019\u001a\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u001dX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001dX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u001dX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u001dX\u0082.¢\u0006\u0002\n\u0000RB\u0010#\u001a6\u0012\u0004\u0012\u00020\u0011\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'0%0$j\u001a\u0012\u0004\u0012\u00020\u0011\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'0%`(X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010)\u001a\u001e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020+0*j\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020+`,X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u0004\u0018\u000100X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u000104X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u00106\u001a\u0010\u0012\f\u0012\n 8*\u0004\u0018\u00010\u00110\u001107X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u00109\u001a\u0010\u0012\f\u0012\n 8*\u0004\u0018\u00010\u00110\u001107X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006j"}, d2 = {"Lcom/jarvis/app/ui/JarvisActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "value", "Lcom/jarvis/app/ui/Skin;", "skin", "getSkin", "()Lcom/jarvis/app/ui/Skin;", "Lcom/jarvis/core/model/AssistantState;", "assistantState", "getAssistantState", "()Lcom/jarvis/core/model/AssistantState;", "", "answerStep", "getAnswerStep", "()I", "", "lastRequest", "getLastRequest", "()Ljava/lang/String;", "lastResponse", "getLastResponse", "lastPartial", "getLastPartial", "isOn", "", "()Z", "content", "Landroid/widget/FrameLayout;", "navHost", "Landroid/view/View;", "liveHost", "sheetHost", "mic", "tabs", "Ljava/util/LinkedHashMap;", "Lkotlin/Pair;", "Lcom/jarvis/app/ui/IconView;", "Landroid/widget/TextView;", "Lkotlin/collections/LinkedHashMap;", "screens", "Ljava/util/HashMap;", "Lcom/jarvis/app/ui/Screen;", "Lkotlin/collections/HashMap;", "current", "previous", JarvisActivity.LIVE, "Lcom/jarvis/app/ui/LiveScreen;", "sectionOnly", "pendingLive", "confirmJob", "Lkotlinx/coroutines/Job;", "askingYesNo", "audioPermission", "Landroidx/activity/result/ActivityResultLauncher;", "kotlin.jvm.PlatformType", "notifyPermission", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "onNewIntent", "intent", "Landroid/content/Intent;", "handleIntent", "onStart", "onResume", "onPause", "buildShell", "buildNav", "show", "id", "back", "openSection", JarvisActivity.SECTION, "Lcom/jarvis/app/ui/MoreSection;", "createScreen", "openLive", "systemBars", TypedValues.Custom.S_COLOR, "light", "closeLive", "powerOn", "askNotificationsThenStart", "powerOff", "onMic", "send", VoiceService.EXTRA_TEXT, "onState", "state", "onEvent", NotificationCompat.CATEGORY_EVENT, "Lcom/jarvis/core/event/AssistantEvent;", "showConfirm", "message", "title", "onYes", "Lkotlin/Function0;", "closeConfirm", "button", "primary", "action", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JarvisActivity extends AppCompatActivity {
    public static final String ACTION_POWER_ON = "com.jarvis.assistant.POWER_ON";
    public static final String CHAT = "chat";
    private static final long CONFIRM_TIMEOUT_MS = 20000;
    private static final int FEED_TEXT = 120;
    public static final String HOME = "home";
    public static final String LIVE = "live";
    public static final String MEMORY = "memory";
    public static final String MORE = "more";
    public static final String ROUTINES = "routines";
    public static final String SCAN = "scan";
    public static final String SECTION = "section";
    public static final String SKILLS = "skills";
    private static final int STARTUP_POLLS = 40;
    private static final long STARTUP_POLL_MS = 500;
    public static final String STATUS = "status";
    private static final long WAKE_SETTLE_MS = 400;
    private static final String YES_OR_NO = "Да или нет";
    private boolean askingYesNo;
    private final ActivityResultLauncher<String> audioPermission;
    private Job confirmJob;
    private FrameLayout content;
    private LiveScreen live;
    private FrameLayout liveHost;
    private FrameLayout mic;
    private View navHost;
    private final ActivityResultLauncher<String> notifyPermission;
    private boolean pendingLive;
    private boolean sectionOnly;
    private FrameLayout sheetHost;
    private Skin skin;
    private AssistantState assistantState = AssistantState.OFFLINE;
    private int answerStep = -1;
    private String lastRequest = "";
    private String lastResponse = "";
    private String lastPartial = "";
    private final LinkedHashMap<String, Pair<IconView, TextView>> tabs = new LinkedHashMap<>();
    private final HashMap<String, Screen> screens = new HashMap<>();
    private String current = "";
    private String previous = "home";

    public JarvisActivity() {
        ActivityResultLauncher<String> activityResultLauncherRegisterForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: com.jarvis.app.ui.JarvisActivity$$ExternalSyntheticLambda1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                JarvisActivity.audioPermission$lambda$0(this.f$0, (Boolean) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "registerForActivityResult(...)");
        this.audioPermission = activityResultLauncherRegisterForActivityResult;
        ActivityResultLauncher<String> activityResultLauncherRegisterForActivityResult2 = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: com.jarvis.app.ui.JarvisActivity$$ExternalSyntheticLambda2
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                JarvisActivity.notifyPermission$lambda$0(this.f$0, (Boolean) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult2, "registerForActivityResult(...)");
        this.notifyPermission = activityResultLauncherRegisterForActivityResult2;
    }

    public final Skin getSkin() {
        Skin skin = this.skin;
        if (skin != null) {
            return skin;
        }
        Intrinsics.throwUninitializedPropertyAccessException("skin");
        return null;
    }

    public final AssistantState getAssistantState() {
        return this.assistantState;
    }

    public final int getAnswerStep() {
        return this.answerStep;
    }

    public final String getLastRequest() {
        return this.lastRequest;
    }

    public final String getLastResponse() {
        return this.lastResponse;
    }

    public final String getLastPartial() {
        return this.lastPartial;
    }

    public final boolean isOn() {
        return (this.assistantState == AssistantState.OFFLINE || this.assistantState == AssistantState.ERROR) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void audioPermission$lambda$0(JarvisActivity this$0, Boolean granted) {
        if (granted.booleanValue()) {
            this$0.askNotificationsThenStart();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void notifyPermission$lambda$0(JarvisActivity this$0, Boolean it) {
        VoiceController.INSTANCE.start(this$0);
    }

    /* JADX WARN: Code duplicated, block: B:38:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:40:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:41:0x00f1  */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        int i;
        String restored;
        String str;
        super.onCreate(savedInstanceState);
        boolean z = false;
        this.sectionOnly = getIntent().getBooleanExtra(UiMode.EXTRA_FORCE_MODERN, false) && !OnboardingActivity.INSTANCE.needsOnboarding(this);
        if (!this.sectionOnly) {
            StartupRoute route = UiMode.INSTANCE.route(this);
            Intent it = UiMode.INSTANCE.stepIntent(this, route);
            if (it != null) {
                startActivity(it);
                finish();
                return;
            } else if (route == StartupRoute.CLASSIC) {
                startActivity(new Intent(getIntent()).setClass(this, DashboardActivity.class));
                finish();
                return;
            }
        }
        this.skin = Skins.INSTANCE.current(this);
        getWindow().setStatusBarColor(getSkin().getBg());
        getWindow().setNavigationBarColor(getSkin().getBg());
        View decorView = getWindow().getDecorView();
        if (getSkin().getLight()) {
            i = 8208;
        } else {
            i = 0;
        }
        decorView.setSystemUiVisibility(i);
        setContentView(buildShell());
        int section = savedInstanceState != null ? savedInstanceState.getInt(SECTION, -1) : -1;
        if (savedInstanceState == null || (restored = savedInstanceState.getString("screen")) == null) {
            restored = "home";
        }
        if (Intrinsics.areEqual(restored, SECTION)) {
            if (section >= 0 && section < Sections.INSTANCE.getALL().size()) {
                openSection(Sections.INSTANCE.getALL().get(section));
            } else {
                if (Intrinsics.areEqual(restored, SECTION)) {
                    str = MORE;
                } else {
                    str = restored;
                }
                show(str);
            }
        } else {
            if (Intrinsics.areEqual(restored, SECTION)) {
                str = MORE;
            } else {
                str = restored;
            }
            show(str);
        }
        if (savedInstanceState != null && savedInstanceState.getBoolean(LIVE)) {
            z = true;
        }
        if (z) {
            openLive();
        }
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback() { // from class: com.jarvis.app.ui.JarvisActivity.onCreate.2
            {
                super(true);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                FrameLayout frameLayout = JarvisActivity.this.sheetHost;
                if (frameLayout == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sheetHost");
                    frameLayout = null;
                }
                if (frameLayout.getChildCount() > 0) {
                    JarvisActivity.this.closeConfirm();
                    return;
                }
                if (JarvisActivity.this.live == null) {
                    if (!JarvisActivity.this.sectionOnly) {
                        if (Intrinsics.areEqual(JarvisActivity.this.current, "home")) {
                            JarvisActivity.this.moveTaskToBack(true);
                            return;
                        } else {
                            JarvisActivity.this.back();
                            return;
                        }
                    }
                    JarvisActivity.this.finish();
                    return;
                }
                JarvisActivity.this.closeLive();
            }
        });
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AnonymousClass3(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AnonymousClass4(null), 3, null);
        handleIntent(getIntent());
    }

    /* JADX INFO: renamed from: com.jarvis.app.ui.JarvisActivity$onCreate$3, reason: invalid class name */
    /* JADX INFO: compiled from: JarvisActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.ui.JarvisActivity$onCreate$3", f = "JarvisActivity.kt", i = {}, l = {125}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return JarvisActivity.this.new AnonymousClass3(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.jarvis.app.ui.JarvisActivity$onCreate$3$1, reason: invalid class name */
        /* JADX INFO: compiled from: JarvisActivity.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.jarvis.app.ui.JarvisActivity$onCreate$3$1", f = "JarvisActivity.kt", i = {}, l = {125}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ JarvisActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(JarvisActivity jarvisActivity, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = jarvisActivity;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX INFO: renamed from: com.jarvis.app.ui.JarvisActivity$onCreate$3$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: JarvisActivity.kt */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            static final /* synthetic */ class C00191 implements FlowCollector, FunctionAdapter {
                final /* synthetic */ JarvisActivity $tmp0;

                C00191(JarvisActivity jarvisActivity) {
                    this.$tmp0 = jarvisActivity;
                }

                public final boolean equals(Object obj) {
                    if ((obj instanceof FlowCollector) && (obj instanceof FunctionAdapter)) {
                        return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
                    }
                    return false;
                }

                @Override // kotlin.jvm.internal.FunctionAdapter
                public final Function<?> getFunctionDelegate() {
                    return new AdaptedFunctionReference(2, this.$tmp0, JarvisActivity.class, "onState", "onState(Lcom/jarvis/core/model/AssistantState;)V", 4);
                }

                public final int hashCode() {
                    return getFunctionDelegate().hashCode();
                }

                public final Object emit(AssistantState p0, Continuation<? super Unit> continuation) {
                    Object objInvokeSuspend$onState = AnonymousClass1.invokeSuspend$onState(this.$tmp0, p0, continuation);
                    return objInvokeSuspend$onState == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objInvokeSuspend$onState : Unit.INSTANCE;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                    return emit((AssistantState) value, (Continuation<? super Unit>) $completion);
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final /* synthetic */ Object invokeSuspend$onState(JarvisActivity $this$invokeSuspend_u24onState, AssistantState p0, Continuation $completion) {
                $this$invokeSuspend_u24onState.onState(p0);
                return Unit.INSTANCE;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.label = 1;
                        if (JarvisEvents.INSTANCE.getAssistantState().collect(new C00191(this.this$0), this) == coroutine_suspended) {
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
                    if (RepeatOnLifecycleKt.repeatOnLifecycle(JarvisActivity.this, Lifecycle.State.STARTED, new AnonymousClass1(JarvisActivity.this, null), this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.jarvis.app.ui.JarvisActivity$onCreate$4, reason: invalid class name */
    /* JADX INFO: compiled from: JarvisActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.ui.JarvisActivity$onCreate$4", f = "JarvisActivity.kt", i = {}, l = {128}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return JarvisActivity.this.new AnonymousClass4(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.jarvis.app.ui.JarvisActivity$onCreate$4$1, reason: invalid class name */
        /* JADX INFO: compiled from: JarvisActivity.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.jarvis.app.ui.JarvisActivity$onCreate$4$1", f = "JarvisActivity.kt", i = {}, l = {128}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ JarvisActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(JarvisActivity jarvisActivity, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = jarvisActivity;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX INFO: renamed from: com.jarvis.app.ui.JarvisActivity$onCreate$4$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: JarvisActivity.kt */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            static final /* synthetic */ class C00201 implements FlowCollector, FunctionAdapter {
                final /* synthetic */ JarvisActivity $tmp0;

                C00201(JarvisActivity jarvisActivity) {
                    this.$tmp0 = jarvisActivity;
                }

                public final boolean equals(Object obj) {
                    if ((obj instanceof FlowCollector) && (obj instanceof FunctionAdapter)) {
                        return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
                    }
                    return false;
                }

                @Override // kotlin.jvm.internal.FunctionAdapter
                public final Function<?> getFunctionDelegate() {
                    return new AdaptedFunctionReference(2, this.$tmp0, JarvisActivity.class, "onEvent", "onEvent(Lcom/jarvis/core/event/AssistantEvent;)V", 4);
                }

                public final int hashCode() {
                    return getFunctionDelegate().hashCode();
                }

                public final Object emit(AssistantEvent p0, Continuation<? super Unit> continuation) {
                    Object objInvokeSuspend$onEvent = AnonymousClass1.invokeSuspend$onEvent(this.$tmp0, p0, continuation);
                    return objInvokeSuspend$onEvent == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objInvokeSuspend$onEvent : Unit.INSTANCE;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                    return emit((AssistantEvent) value, (Continuation<? super Unit>) $completion);
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final /* synthetic */ Object invokeSuspend$onEvent(JarvisActivity $this$invokeSuspend_u24onEvent, AssistantEvent p0, Continuation $completion) {
                $this$invokeSuspend_u24onEvent.onEvent(p0);
                return Unit.INSTANCE;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.label = 1;
                        if (JarvisEvents.INSTANCE.getEvents().collect(new C00201(this.this$0), this) == coroutine_suspended) {
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
                    if (RepeatOnLifecycleKt.repeatOnLifecycle(JarvisActivity.this, Lifecycle.State.STARTED, new AnonymousClass1(JarvisActivity.this, null), this) == coroutine_suspended) {
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

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putString("screen", this.current);
        outState.putBoolean(LIVE, this.live != null);
        List<MoreSection> all = Sections.INSTANCE.getALL();
        Screen screen = this.screens.get(SECTION);
        SectionScreen sectionScreen = screen instanceof SectionScreen ? (SectionScreen) screen : null;
        outState.putInt(SECTION, CollectionsKt.indexOf((List<? extends MoreSection>) all, sectionScreen != null ? sectionScreen.getSection() : null));
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent);
    }

    private final void handleIntent(Intent intent) {
        if (intent == null) {
            return;
        }
        Object obj = null;
        if (Intrinsics.areEqual(intent.getAction(), "android.intent.action.ASSIST")) {
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AnonymousClass1(null), 3, null);
        } else if (Intrinsics.areEqual(intent.getAction(), ACTION_POWER_ON)) {
            powerOn();
        } else if (intent.getBooleanExtra("open_live", false)) {
            openLive();
        }
        String title = intent.getStringExtra(UiMode.EXTRA_OPEN_SECTION);
        if (title != null) {
            Iterable $this$firstOrNull$iv = Sections.INSTANCE.getALL();
            for (Object element$iv : $this$firstOrNull$iv) {
                MoreSection it = (MoreSection) element$iv;
                if (Intrinsics.areEqual(it.getTitle(), title)) {
                    obj = element$iv;
                    break;
                }
            }
            MoreSection p0 = (MoreSection) obj;
            if (p0 != null) {
                openSection(p0);
            }
        }
    }

    /* JADX INFO: renamed from: com.jarvis.app.ui.JarvisActivity$handleIntent$1, reason: invalid class name */
    /* JADX INFO: compiled from: JarvisActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.ui.JarvisActivity$handleIntent$1", f = "JarvisActivity.kt", i = {0, 0, 1, 1}, l = {154, 158}, m = "invokeSuspend", n = {"it", "$i$a$-repeat-JarvisActivity$handleIntent$1$1", "it", "$i$a$-repeat-JarvisActivity$handleIntent$1$1"}, s = {"I$0", "I$1", "I$2", "I$3"}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return JarvisActivity.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:13:0x004a  */
        /* JADX WARN: Code duplicated, block: B:15:0x0054  */
        /* JADX WARN: Code duplicated, block: B:17:0x0062 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:18:0x0063  */
        /* JADX WARN: Code duplicated, block: B:21:0x006c  */
        /* JADX WARN: Code duplicated, block: B:23:0x007f A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:24:0x0080  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0080 -> B:25:0x0086). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                r10 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r10.label
                r2 = 1
                switch(r1) {
                    case 0: goto L30;
                    case 1: goto L25;
                    case 2: goto L12;
                    default: goto La;
                }
            La:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
                r0.<init>(r1)
                throw r0
            L12:
                int r1 = r10.I$3
                int r3 = r10.I$2
                int r4 = r10.I$1
                int r5 = r10.I$0
                kotlin.ResultKt.throwOnFailure(r11)
                r6 = r5
                r5 = r4
                r4 = r3
                r3 = r0
                r0 = r11
                r11 = r10
                goto L86
            L25:
                int r0 = r10.I$1
                int r1 = r10.I$0
                kotlin.ResultKt.throwOnFailure(r11)
                r6 = r0
                r0 = r11
                r11 = r10
                goto L64
            L30:
                kotlin.ResultKt.throwOnFailure(r11)
                com.jarvis.app.VoiceController r1 = com.jarvis.app.VoiceController.INSTANCE
                boolean r1 = r1.isRunning()
                if (r1 != 0) goto L40
                com.jarvis.app.ui.JarvisActivity r1 = com.jarvis.app.ui.JarvisActivity.this
                r1.powerOn()
            L40:
                r1 = 40
                r3 = 0
                r5 = r1
                r4 = r3
                r1 = r0
                r0 = r11
                r11 = r10
            L48:
                if (r4 >= r5) goto L8c
                r3 = r4
                r6 = 0
                com.jarvis.app.VoiceController r7 = com.jarvis.app.VoiceController.INSTANCE
                boolean r7 = r7.isRunning()
                if (r7 == 0) goto L6c
                r11.I$0 = r3
                r11.I$1 = r6
                r11.label = r2
                r4 = 400(0x190, double:1.976E-321)
                java.lang.Object r2 = kotlinx.coroutines.DelayKt.delay(r4, r11)
                if (r2 != r1) goto L63
                return r1
            L63:
                r1 = r3
            L64:
                com.jarvis.app.VoiceController r2 = com.jarvis.app.VoiceController.INSTANCE
                r2.manualWake()
                kotlin.Unit r2 = kotlin.Unit.INSTANCE
                return r2
            L6c:
                r11.I$0 = r5
                r11.I$1 = r4
                r11.I$2 = r3
                r11.I$3 = r6
                r7 = 2
                r11.label = r7
                r7 = 500(0x1f4, double:2.47E-321)
                java.lang.Object r7 = kotlinx.coroutines.DelayKt.delay(r7, r11)
                if (r7 != r1) goto L80
                return r1
            L80:
                r9 = r3
                r3 = r1
                r1 = r6
                r6 = r5
                r5 = r4
                r4 = r9
            L86:
                int r4 = r5 + 1
                r1 = r3
                r5 = r6
                goto L48
            L8c:
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jarvis.app.ui.JarvisActivity.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        if (!JarvisSettings.INSTANCE.voiceWanted(this) || VoiceController.INSTANCE.isRunning()) {
            return;
        }
        powerOn();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        VoiceController.INSTANCE.hideOverlay();
        OverlayOrbService.INSTANCE.hide(this);
        Screen screen = this.screens.get(this.current);
        if (screen != null) {
            screen.onShow();
        }
        if (VoiceController.INSTANCE.getLiveTalk()) {
            openLive();
        }
        LiveScreen liveScreen = this.live;
        if (liveScreen != null) {
            liveScreen.sync();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        if (isFinishing() || !VoiceController.INSTANCE.getLiveTalk() || !Settings.canDrawOverlays(this)) {
            return;
        }
        VoiceController.INSTANCE.showOverlay();
    }

    private final View buildShell() {
        FrameLayout root = new FrameLayout(this);
        root.setFitsSystemWindows(true);
        GradientDrawable.Orientation orientation = GradientDrawable.Orientation.TOP_BOTTOM;
        int bg = getSkin().getBg();
        int it = UiKt.alpha(getSkin().getAccent(), 1.0f);
        root.setBackground(new GradientDrawable(orientation, new int[]{bg, JarvisActivityKt.mix(getSkin().getBg(), it, 0.1f), getSkin().getBg()}));
        FrameLayout frameLayout = null;
        LinearLayout col = UiKt.column$default(this, 0, 1, null);
        this.content = new FrameLayout(this);
        FrameLayout frameLayout2 = this.content;
        if (frameLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("content");
            frameLayout2 = null;
        }
        col.addView(frameLayout2, UiKt.lp$default(0, 0, 1.0f, 1, null));
        this.navHost = buildNav();
        View view = this.navHost;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navHost");
            view = null;
        }
        LinearLayout.LayoutParams $this$buildShell_u24lambda_u241 = UiKt.lp$default(0, UiKt.dp((Context) this, 92), 0.0f, 5, null);
        $this$buildShell_u24lambda_u241.topMargin = -UiKt.dp((Context) this, 24);
        Unit unit = Unit.INSTANCE;
        col.addView(view, $this$buildShell_u24lambda_u241);
        root.addView(col, new FrameLayout.LayoutParams(-1, -1));
        this.liveHost = new FrameLayout(this);
        FrameLayout frameLayout3 = this.liveHost;
        if (frameLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("liveHost");
            frameLayout3 = null;
        }
        root.addView(frameLayout3, new FrameLayout.LayoutParams(-1, -1));
        this.sheetHost = new FrameLayout(this);
        FrameLayout frameLayout4 = this.sheetHost;
        if (frameLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sheetHost");
        } else {
            frameLayout = frameLayout4;
        }
        FrameLayout.LayoutParams $this$buildShell_u24lambda_u242 = new FrameLayout.LayoutParams(-1, -1);
        $this$buildShell_u24lambda_u242.gravity = 80;
        Unit unit2 = Unit.INSTANCE;
        root.addView(frameLayout, $this$buildShell_u24lambda_u242);
        return root;
    }

    private final View buildNav() {
        FrameLayout host = new FrameLayout(this);
        host.setClipChildren(false);
        LinearLayout bar = new LinearLayout(this);
        bar.setOrientation(0);
        bar.setGravity(16);
        GradientDrawable $this$buildNav_u24lambda_u241_u240 = new GradientDrawable();
        $this$buildNav_u24lambda_u241_u240.setColor(JarvisActivityKt.mix(getSkin().getBg(), getSkin().getAccent(), 0.1f));
        $this$buildNav_u24lambda_u241_u240.setCornerRadii(new float[]{UiKt.dp((Context) this, 26.0f), UiKt.dp((Context) this, 26.0f), UiKt.dp((Context) this, 26.0f), UiKt.dp((Context) this, 26.0f), 0.0f, 0.0f, 0.0f, 0.0f});
        $this$buildNav_u24lambda_u241_u240.setStroke(1, getSkin().getLine());
        bar.setBackground($this$buildNav_u24lambda_u241_u240);
        buildNav$tab(this, bar, "home", Ic.HOME, "Главная");
        buildNav$tab(this, bar, CHAT, Ic.CHAT, "Диалог");
        buildNav$tab(this, bar, MEMORY, Ic.BRAIN, "Память");
        FrameLayout frameLayout = null;
        bar.addView(new View(this), UiKt.lp$default(UiKt.dp((Context) this, 82), 1, 0.0f, 4, null));
        buildNav$tab(this, bar, LIVE, Ic.LIVE, "Live");
        buildNav$tab(this, bar, SKILLS, Ic.GRID, "Навыки");
        buildNav$tab(this, bar, MORE, Ic.DOTS, "Ещё");
        FrameLayout.LayoutParams $this$buildNav_u24lambda_u243 = new FrameLayout.LayoutParams(-1, UiKt.dp((Context) this, 68));
        $this$buildNav_u24lambda_u243.gravity = 80;
        Unit unit = Unit.INSTANCE;
        host.addView(bar, $this$buildNav_u24lambda_u243);
        FrameLayout $this$buildNav_u24lambda_u244 = new FrameLayout(this);
        $this$buildNav_u24lambda_u244.setContentDescription("Говорить");
        GradientDrawable $this$buildNav_u24lambda_u244_u240 = new GradientDrawable();
        $this$buildNav_u24lambda_u244_u240.setShape(1);
        $this$buildNav_u24lambda_u244_u240.setGradientType(1);
        $this$buildNav_u24lambda_u244_u240.setGradientRadius(UiKt.dp((Context) this, 40.0f));
        $this$buildNav_u24lambda_u244_u240.setColors(new int[]{UiKt.alpha(getSkin().getAccent(), 0.55f), JarvisActivityKt.mix(getSkin().getBg(), getSkin().getAccent(), 0.08f)});
        $this$buildNav_u24lambda_u244_u240.setStroke(UiKt.dp((Context) this, 2), getSkin().getAccent());
        $this$buildNav_u24lambda_u244.setBackground($this$buildNav_u24lambda_u244_u240);
        $this$buildNav_u24lambda_u244.setElevation(UiKt.dp((Context) this, 8.0f));
        Context context = $this$buildNav_u24lambda_u244.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        $this$buildNav_u24lambda_u244.addView(new IconView(context, Ic.MIC, getSkin().getText(), 30), new FrameLayout.LayoutParams(-2, -2, 17));
        UiKt.tap($this$buildNav_u24lambda_u244, new Function0() { // from class: com.jarvis.app.ui.JarvisActivity$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JarvisActivity.buildNav$lambda$4$1(this.f$0);
            }
        });
        this.mic = $this$buildNav_u24lambda_u244;
        FrameLayout frameLayout2 = this.mic;
        if (frameLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mic");
        } else {
            frameLayout = frameLayout2;
        }
        FrameLayout.LayoutParams $this$buildNav_u24lambda_u245 = new FrameLayout.LayoutParams(UiKt.dp((Context) this, 74), UiKt.dp((Context) this, 74));
        $this$buildNav_u24lambda_u245.gravity = 49;
        Unit unit2 = Unit.INSTANCE;
        host.addView(frameLayout, $this$buildNav_u24lambda_u245);
        return host;
    }

    private static final void buildNav$tab(final JarvisActivity this$0, LinearLayout bar, final String id, String icon, String title) {
        IconView ic = new IconView(this$0, icon, this$0.getSkin().getMuted(), 20);
        JarvisActivity jarvisActivity = this$0;
        TextView t = UiKt.label(jarvisActivity, (8 & 1) != 0 ? "" : title, (8 & 2) != 0 ? 14.0f : 10.5f, this$0.getSkin().getMuted(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, jarvisActivity, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 17, (8 & 32) != 0 ? Integer.MAX_VALUE : 1);
        LinearLayout box = UiKt.column$default(this$0, 0, 1, null);
        box.setGravity(17);
        box.addView(ic);
        LinearLayout.LayoutParams $this$buildNav_u24tab_u24lambda_u242_u240 = UiKt.lp$default(0, -2, 0.0f, 5, null);
        $this$buildNav_u24tab_u24lambda_u242_u240.topMargin = UiKt.dp((Context) this$0, 3);
        Unit unit = Unit.INSTANCE;
        box.addView(t, $this$buildNav_u24tab_u24lambda_u242_u240);
        UiKt.tap(box, new Function0() { // from class: com.jarvis.app.ui.JarvisActivity$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JarvisActivity.buildNav$tab$lambda$2$1(id, this$0);
            }
        });
        box.setContentDescription(title);
        this$0.tabs.put(id, TuplesKt.to(ic, t));
        bar.addView(box, UiKt.lp(0, UiKt.dp((Context) this$0, 60), 1.0f));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit buildNav$tab$lambda$2$1(String $id, JarvisActivity this$0) {
        if (Intrinsics.areEqual($id, LIVE)) {
            this$0.openLive();
        } else {
            this$0.show($id);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit buildNav$lambda$4$1(JarvisActivity this$0) {
        this$0.onMic();
        return Unit.INSTANCE;
    }

    public final void show(String id) {
        Screen screenCreateScreen;
        String str;
        Intrinsics.checkNotNullParameter(id, "id");
        if (Intrinsics.areEqual(id, this.current)) {
            return;
        }
        boolean zAreEqual = Intrinsics.areEqual(id, SECTION);
        String str2 = MORE;
        if (!zAreEqual) {
            if (Intrinsics.areEqual(this.current, SECTION)) {
                str = MORE;
            } else {
                String str3 = this.current;
                if (str3.length() == 0) {
                    str3 = "home";
                }
                str = str3;
            }
            this.previous = str;
        }
        Screen screen = this.screens.get(this.current);
        if (screen != null) {
            screen.onHide();
        }
        this.current = id;
        Map $this$getOrPut$iv = this.screens;
        Screen screen2 = $this$getOrPut$iv.get(id);
        if (screen2 == null) {
            screenCreateScreen = createScreen(id);
            $this$getOrPut$iv.put(id, screenCreateScreen);
        } else {
            screenCreateScreen = screen2;
        }
        Screen screen3 = screenCreateScreen;
        FrameLayout frameLayout = this.content;
        FrameLayout frameLayout2 = null;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("content");
            frameLayout = null;
        }
        frameLayout.removeAllViews();
        FrameLayout frameLayout3 = this.content;
        if (frameLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("content");
        } else {
            frameLayout2 = frameLayout3;
        }
        frameLayout2.addView(screen3.getView(), new FrameLayout.LayoutParams(-1, -1));
        screen3.onShow();
        if (!Intrinsics.areEqual(id, SECTION)) {
            str2 = id;
        }
        String active = str2;
        Map $this$forEach$iv = this.tabs;
        for (Map.Entry<String, Pair<IconView, TextView>> entry : $this$forEach$iv.entrySet()) {
            String key = entry.getKey();
            Pair<IconView, TextView> value = entry.getValue();
            int c = Intrinsics.areEqual(key, active) ? getSkin().getAccent() : getSkin().getMuted();
            value.getFirst().setTint(c);
            value.getFirst().invalidate();
            value.getSecond().setTextColor(c);
        }
    }

    public final void back() {
        String it;
        if (this.sectionOnly) {
            finish();
            return;
        }
        if (Intrinsics.areEqual(this.current, SECTION)) {
            it = MORE;
        } else {
            it = this.previous;
            if (Intrinsics.areEqual(it, this.current)) {
                it = null;
            }
            if (it == null) {
                it = "home";
            }
        }
        show(it);
    }

    public final void openSection(MoreSection section) {
        Screen sectionScreen;
        Intrinsics.checkNotNullParameter(section, "section");
        Map $this$getOrPut$iv = this.screens;
        Screen screen = $this$getOrPut$iv.get(SECTION);
        if (screen == null) {
            sectionScreen = new SectionScreen(this);
            $this$getOrPut$iv.put(SECTION, sectionScreen);
        } else {
            sectionScreen = screen;
        }
        ((SectionScreen) sectionScreen).bind(section);
        show(SECTION);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final Screen createScreen(String id) {
        switch (id.hashCode()) {
            case -1077756671:
                if (id.equals(MEMORY)) {
                    return new MemoryScreen(this);
                }
                break;
            case -900562878:
                if (id.equals(SKILLS)) {
                    return new SkillsScreen(this);
                }
                break;
            case -892481550:
                if (id.equals("status")) {
                    return new StatusScreen(this);
                }
                break;
            case 3052376:
                if (id.equals(CHAT)) {
                    return new ChatScreen(this);
                }
                break;
            case 3357525:
                if (id.equals(MORE)) {
                    return new MoreScreen(this);
                }
                break;
            case 3524221:
                if (id.equals(SCAN)) {
                    return new ScanScreen(this);
                }
                break;
            case 5552175:
                if (id.equals(ROUTINES)) {
                    return new RoutinesScreen(this);
                }
                break;
            case 1970241253:
                if (id.equals(SECTION)) {
                    return new SectionScreen(this);
                }
                break;
        }
        return new HomeScreen(this);
    }

    public final void openLive() {
        if (this.live != null) {
            return;
        }
        LiveScreen screen = new LiveScreen(this);
        this.live = screen;
        FrameLayout frameLayout = this.liveHost;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("liveHost");
            frameLayout = null;
        }
        frameLayout.addView(screen.getView(), new FrameLayout.LayoutParams(-1, -1));
        systemBars(screen.getStageColor(), false);
        screen.onShow();
        screen.onState(this.assistantState);
    }

    private final void systemBars(int color, boolean light) {
        int i;
        getWindow().setStatusBarColor(color);
        getWindow().setNavigationBarColor(color);
        View decorView = getWindow().getDecorView();
        if (light) {
            i = 8208;
        } else {
            i = 0;
        }
        decorView.setSystemUiVisibility(i);
    }

    public final void closeLive() {
        LiveScreen screen = this.live;
        if (screen == null) {
            return;
        }
        screen.onHide();
        FrameLayout frameLayout = this.liveHost;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("liveHost");
            frameLayout = null;
        }
        frameLayout.removeAllViews();
        this.live = null;
        systemBars(getSkin().getBg(), getSkin().getLight());
    }

    public final void powerOn() {
        this.pendingLive = false;
        if (ContextCompat.checkSelfPermission(this, "android.permission.RECORD_AUDIO") != 0) {
            this.audioPermission.launch("android.permission.RECORD_AUDIO");
        } else {
            askNotificationsThenStart();
        }
    }

    private final void askNotificationsThenStart() {
        if (Build.VERSION.SDK_INT >= 33 && ContextCompat.checkSelfPermission(this, "android.permission.POST_NOTIFICATIONS") != 0) {
            this.notifyPermission.launch("android.permission.POST_NOTIFICATIONS");
        } else {
            VoiceController.INSTANCE.start(this);
        }
    }

    public final void powerOff() {
        VoiceController.INSTANCE.setLiveTalk(false);
        VoiceController.INSTANCE.stop(this);
    }

    private final void onMic() {
        if (!Intrinsics.areEqual(this.current, "home")) {
            show("home");
        }
        if (!isOn()) {
            Screen screen = this.screens.get("home");
            HomeScreen homeScreen = screen instanceof HomeScreen ? (HomeScreen) screen : null;
            if (homeScreen != null) {
                homeScreen.hint("Сначала нажмите на ядро, сэр");
                return;
            }
            return;
        }
        if (this.assistantState == AssistantState.LISTENING || this.assistantState == AssistantState.THINKING || this.assistantState == AssistantState.EXECUTING) {
            VoiceController.INSTANCE.cancelTurn(this);
        } else {
            VoiceController.INSTANCE.manualWake();
        }
    }

    public final void send(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        String clean = StringsKt.trim((CharSequence) text).toString();
        if (clean.length() == 0) {
            return;
        }
        if (!VoiceController.INSTANCE.isRunning()) {
            powerOn();
        }
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new C00641(clean, null), 3, null);
    }

    /* JADX INFO: renamed from: com.jarvis.app.ui.JarvisActivity$send$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: JarvisActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.ui.JarvisActivity$send$1", f = "JarvisActivity.kt", i = {0, 0}, l = {379}, m = "invokeSuspend", n = {"it", "$i$a$-repeat-JarvisActivity$send$1$1"}, s = {"I$2", "I$3"}, v = 1)
    static final class C00641 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $clean;
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00641(String str, Continuation<? super C00641> continuation) {
            super(2, continuation);
            this.$clean = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return JarvisActivity.this.new C00641(this.$clean, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00641) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:10:0x0049  */
        /* JADX WARN: Code duplicated, block: B:12:0x0053  */
        /* JADX WARN: Code duplicated, block: B:14:0x0064  */
        /* JADX WARN: Code duplicated, block: B:17:0x0082  */
        /* JADX WARN: Code duplicated, block: B:19:0x009a A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:20:0x009b  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x009b -> B:21:0x00a4). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r19) {
            /*
                Method dump skipped, instruction units count: 210
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jarvis.app.ui.JarvisActivity.C00641.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onState(AssistantState state) {
        this.assistantState = state;
        if (state == AssistantState.IDLE || state == AssistantState.READY) {
            this.answerStep = -1;
        }
        if (state == AssistantState.LISTENING) {
            this.answerStep = 0;
        }
        if (state == AssistantState.SPEAKING) {
            this.answerStep = 4;
        }
        Screen screen = this.screens.get("home");
        if (screen != null) {
            screen.onState(state);
        }
        LiveScreen liveScreen = this.live;
        if (liveScreen != null) {
            liveScreen.onState(state);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onEvent(AssistantEvent event) {
        if (event instanceof AssistantEvent.TranscriptUpdated) {
            this.lastPartial = ((AssistantEvent.TranscriptUpdated) event).getText();
        } else {
            if (event instanceof AssistantEvent.RequestReceived) {
                this.lastRequest = ((AssistantEvent.RequestReceived) event).getText();
                this.lastPartial = "";
                this.answerStep = 1;
                ConversationStore.INSTANCE.pushUser(((AssistantEvent.RequestReceived) event).getText());
                ActivityFeed.push$default(ActivityFeed.INSTANCE, "Запрос", ((AssistantEvent.RequestReceived) event).getText(), ActivityFeed.Item.Kind.VOICE, false, false, 24, null);
                if (this.askingYesNo) {
                    closeConfirm();
                }
            } else if (event instanceof AssistantEvent.ResponseGenerated) {
                this.lastResponse = ((AssistantEvent.ResponseGenerated) event).getText();
                this.answerStep = 3;
                ConversationStore.pushAi$default(ConversationStore.INSTANCE, ((AssistantEvent.ResponseGenerated) event).getText(), null, null, 6, null);
                ActivityFeed.push$default(ActivityFeed.INSTANCE, "Ответ", StringsKt.take(((AssistantEvent.ResponseGenerated) event).getText(), FEED_TEXT), ActivityFeed.Item.Kind.VOICE, false, false, 24, null);
                if (StringsKt.contains$default((CharSequence) ((AssistantEvent.ResponseGenerated) event).getText(), (CharSequence) YES_OR_NO, false, 2, (Object) null)) {
                    showConfirm$default(this, StringsKt.trim((CharSequence) StringsKt.substringBefore$default(((AssistantEvent.ResponseGenerated) event).getText(), YES_OR_NO, (String) null, 2, (Object) null)).toString(), null, null, 6, null);
                } else if (this.askingYesNo) {
                    closeConfirm();
                }
            } else if (event instanceof AssistantEvent.IntentResolved) {
                this.answerStep = 2;
            } else if (event instanceof AssistantEvent.ToolExecuted) {
                boolean pending = ((AssistantEvent.ToolExecuted) event).getResult().getStatus() == ToolResultStatus.NEEDS_CONFIRMATION;
                ActivityFeed activityFeed = ActivityFeed.INSTANCE;
                String toolId = ((AssistantEvent.ToolExecuted) event).getResult().getToolId();
                String message = ((AssistantEvent.ToolExecuted) event).getResult().getMessage();
                ActivityFeed.push$default(activityFeed, toolId, message == null ? "" : message, pending ? ActivityFeed.Item.Kind.CONFIRM : ActivityFeed.Item.Kind.TOOL, pending, false, 16, null);
                if (pending) {
                    String message2 = ((AssistantEvent.ToolExecuted) event).getResult().getMessage();
                    if (message2 == null) {
                        message2 = "Подтвердите действие, сэр.";
                    }
                    showConfirm$default(this, message2, null, null, 6, null);
                }
            } else if (!Intrinsics.areEqual(event, AssistantEvent.WakeDetected.INSTANCE)) {
                return;
            } else {
                ActivityFeed.push$default(ActivityFeed.INSTANCE, "Активация", "Услышал обращение", ActivityFeed.Item.Kind.SYSTEM, false, false, 24, null);
            }
        }
        Screen screen = this.screens.get("home");
        if (screen != null) {
            screen.onData();
        }
        Screen screen2 = this.screens.get(CHAT);
        if (screen2 != null) {
            screen2.onData();
        }
        LiveScreen liveScreen = this.live;
        if (liveScreen != null) {
            liveScreen.onData();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void showConfirm$default(JarvisActivity jarvisActivity, String str, String str2, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "Подтвердите, сэр";
        }
        if ((i & 4) != 0) {
            function0 = null;
        }
        jarvisActivity.showConfirm(str, str2, function0);
    }

    public final void showConfirm(String message, String title, final Function0<Unit> onYes) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(title, "title");
        FrameLayout frameLayout = this.sheetHost;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sheetHost");
            frameLayout = null;
        }
        frameLayout.removeAllViews();
        Job job = this.confirmJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.askingYesNo = onYes == null;
        View viewPad = UiKt.pad(UiKt.column(this, 12), 16, 18);
        LinearLayout $this$showConfirm_u24lambda_u240 = (LinearLayout) viewPad;
        GradientDrawable $this$showConfirm_u24lambda_u240_u240 = new GradientDrawable();
        $this$showConfirm_u24lambda_u240_u240.setColor(JarvisActivityKt.mix(getSkin().getBg(), getSkin().getAccent(), 0.12f));
        $this$showConfirm_u24lambda_u240_u240.setCornerRadii(new float[]{UiKt.dp((Context) this, 20.0f), UiKt.dp((Context) this, 20.0f), UiKt.dp((Context) this, 20.0f), UiKt.dp((Context) this, 20.0f), 0.0f, 0.0f, 0.0f, 0.0f});
        $this$showConfirm_u24lambda_u240_u240.setStroke(1, getSkin().getWarn());
        $this$showConfirm_u24lambda_u240.setBackground($this$showConfirm_u24lambda_u240_u240);
        $this$showConfirm_u24lambda_u240.setClickable(true);
        LinearLayout box = (LinearLayout) viewPad;
        JarvisActivity jarvisActivity = this;
        box.addView(UiKt.label(jarvisActivity, (8 & 1) != 0 ? "" : title, (8 & 2) != 0 ? 14.0f : 20.0f, getSkin().getText(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, jarvisActivity, 0, 2, null) : Fonts.display$default(Fonts.INSTANCE, this, 0, 2, null), (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0));
        JarvisActivity jarvisActivity2 = this;
        box.addView(UiKt.label(jarvisActivity2, (8 & 1) != 0 ? "" : message, (8 & 2) != 0 ? 14.0f : 14.0f, getSkin().getMuted(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, jarvisActivity2, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0));
        LinearLayout row = UiKt.rowLayout(this, 10);
        row.addView(button("Нет", false, new Function0() { // from class: com.jarvis.app.ui.JarvisActivity$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JarvisActivity.showConfirm$lambda$3(this.f$0, onYes);
            }
        }), UiKt.lp(0, UiKt.dp((Context) this, 48), 1.0f));
        row.addView(button("Да", true, new Function0() { // from class: com.jarvis.app.ui.JarvisActivity$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JarvisActivity.showConfirm$lambda$4(this.f$0, onYes);
            }
        }), UiKt.lp(0, UiKt.dp((Context) this, 48), 1.0f));
        box.addView(row);
        FrameLayout frameLayout2 = this.sheetHost;
        if (frameLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sheetHost");
            frameLayout2 = null;
        }
        FrameLayout.LayoutParams $this$showConfirm_u24lambda_u245 = new FrameLayout.LayoutParams(-1, -2);
        $this$showConfirm_u24lambda_u245.gravity = 80;
        Unit unit = Unit.INSTANCE;
        frameLayout2.addView(box, $this$showConfirm_u24lambda_u245);
        if (onYes == null) {
            this.confirmJob = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new C00654(null), 3, null);
        }
    }

    private static final void showConfirm$answer(JarvisActivity this$0, Function0<Unit> function0, boolean yes) {
        Object element$iv;
        this$0.closeConfirm();
        if (function0 != null) {
            if (yes) {
                function0.invoke();
                return;
            }
            return;
        }
        Iterable $this$firstOrNull$iv = ActivityFeed.INSTANCE.all();
        Iterator it = $this$firstOrNull$iv.iterator();
        do {
            if (!it.hasNext()) {
                element$iv = null;
                break;
            }
            element$iv = it.next();
        } while (!((ActivityFeed.Item) element$iv).getPendingConfirm());
        ActivityFeed.Item it2 = (ActivityFeed.Item) element$iv;
        if (it2 != null) {
            ActivityFeed.INSTANCE.markResolved(it2.getId(), yes);
        }
        if (VoiceController.INSTANCE.isRunning()) {
            VoiceController.INSTANCE.notifyRequest$app_debug(new UserRequest(yes ? "да" : "нет", null, null, 0L, null, 30, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showConfirm$lambda$3(JarvisActivity this$0, Function0 $onYes) {
        showConfirm$answer(this$0, $onYes, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showConfirm$lambda$4(JarvisActivity this$0, Function0 $onYes) {
        showConfirm$answer(this$0, $onYes, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.jarvis.app.ui.JarvisActivity$showConfirm$4, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: JarvisActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.ui.JarvisActivity$showConfirm$4", f = "JarvisActivity.kt", i = {}, l = {465}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C00654 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C00654(Continuation<? super C00654> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return JarvisActivity.this.new C00654(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00654) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (DelayKt.delay(JarvisActivity.CONFIRM_TIMEOUT_MS, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            JarvisActivity.this.closeConfirm();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void closeConfirm() {
        this.askingYesNo = false;
        Job job = this.confirmJob;
        FrameLayout frameLayout = null;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        FrameLayout frameLayout2 = this.sheetHost;
        if (frameLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sheetHost");
        } else {
            frameLayout = frameLayout2;
        }
        frameLayout.removeAllViews();
    }

    public final TextView button(String text, boolean primary, Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(action, "action");
        JarvisActivity jarvisActivity = this;
        String str = text;
        Skin skin = getSkin();
        TextView $this$button_u24lambda_u240 = UiKt.label(jarvisActivity, (8 & 1) != 0 ? "" : str, (8 & 2) != 0 ? 14.0f : 15.0f, primary ? skin.getOnAccent() : skin.getText(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, jarvisActivity, 0, 2, null) : Fonts.INSTANCE.body(this, TypedValues.TransitionType.TYPE_DURATION), (8 & 16) != 0 ? 8388611 : 17, (8 & 32) != 0 ? Integer.MAX_VALUE : 0);
        JarvisActivity jarvisActivity2 = this;
        Skin skin2 = getSkin();
        Skin skin3 = getSkin();
        int accent = primary ? skin3.getAccent() : skin3.getGlass();
        Skin skin4 = getSkin();
        $this$button_u24lambda_u240.setBackground(UiKt.glassBg(jarvisActivity2, skin2, 12, accent, primary ? skin4.getAccent() : skin4.getLine()));
        $this$button_u24lambda_u240.setGravity(17);
        UiKt.tap($this$button_u24lambda_u240, action);
        return $this$button_u24lambda_u240;
    }
}
