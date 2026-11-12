package com.jarvis.app;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.google.android.gms.common.Scopes;
import com.google.android.material.timepicker.TimeModel;
import com.jarvis.app.asr.CloudAsrFactory;
import com.jarvis.app.companion.CompanionBehavior;
import com.jarvis.app.companion.CompanionMemory;
import com.jarvis.app.companion.CompanionProactive;
import com.jarvis.app.companion.SystemStateObserver;
import com.jarvis.app.companion.UsageStatsObserver;
import com.jarvis.app.tts.CloudTtsFactory;
import com.jarvis.app.ui.JarvisActivity;
import com.jarvis.brain.LongTermMemory;
import com.jarvis.brain.aiprovider.AICompletionRequest;
import com.jarvis.brain.aiprovider.NaraRouterDefaults;
import com.jarvis.core.behavior.BehaviorCandidate;
import com.jarvis.core.behavior.BehaviorFeedback;
import com.jarvis.core.behavior.BehaviorLearningService;
import com.jarvis.core.behavior.BehaviorStore;
import com.jarvis.core.common.model.ToolResultStatus;
import com.jarvis.core.common.model.UserRequest;
import com.jarvis.core.companion.ContextSnapshot;
import com.jarvis.core.companion.ObservationDiagnosticsBuffer;
import com.jarvis.core.companion.ObservationSource;
import com.jarvis.core.companion.ObservationType;
import com.jarvis.core.companion.PhoneContext;
import com.jarvis.core.companion.PhoneContextBus;
import com.jarvis.core.companion.PhoneObservation;
import com.jarvis.core.companion.Sensitivity;
import com.jarvis.core.diagnostics.DiagnosticsFormatter;
import com.jarvis.core.diagnostics.SystemDiagnosticsSnapshot;
import com.jarvis.core.event.AssistantEvent;
import com.jarvis.core.event.JarvisEvents;
import com.jarvis.core.llm.CloudKeyValidator;
import com.jarvis.core.memory.CompanionMemoryService;
import com.jarvis.core.memory.MemoryCandidate;
import com.jarvis.core.memory.MemoryCandidatePipeline;
import com.jarvis.core.memory.MemoryDisposition;
import com.jarvis.core.memory.MemoryEntry;
import com.jarvis.core.memory.MemoryKind;
import com.jarvis.core.memory.MemoryPolicyDecision;
import com.jarvis.core.model.AssistantState;
import com.jarvis.core.planner.PlanStep;
import com.jarvis.core.proactive.ProactiveDecision;
import com.jarvis.core.proactive.ProactiveDecisionType;
import com.jarvis.core.reminder.HudText;
import com.jarvis.core.reminder.Reminder;
import com.jarvis.core.routines.RoutineStatus;
import com.jarvis.core.routines.RoutineStore;
import com.jarvis.core.routines.SavedRoutine;
import com.jarvis.core.startup.UiStyle;
import com.jarvis.core.storage.ConversationJournal;
import com.jarvis.core.storage.FactPolicies;
import com.jarvis.core.storage.JarvisSchema;
import com.jarvis.onboarding.PermissionsHelper;
import com.jarvis.screencontrol.JarvisAccessibilityService;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Function;
import kotlin.KotlinNothingValueException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt;
import kotlin.text.CharsKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.time.DurationKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: DashboardActivity.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000É\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\t*\u0001f\u0018\u0000 \u009e\u00012\u00020\u0001:\u0002\u009e\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u000100H\u0014J\u0010\u00101\u001a\u00020.2\u0006\u00102\u001a\u000200H\u0014J\u0010\u00103\u001a\u00020.2\u0006\u00104\u001a\u000205H\u0014J\u0012\u00106\u001a\u00020.2\b\u00104\u001a\u0004\u0018\u000105H\u0002J\b\u00107\u001a\u00020.H\u0014J\b\u00108\u001a\u00020.H\u0014J\b\u00109\u001a\u00020.H\u0014J\b\u0010:\u001a\u00020.H\u0014J\u0010\u0010;\u001a\u00020\u00162\u0006\u0010<\u001a\u00020\u0011H\u0002J\b\u0010=\u001a\u00020.H\u0002J\b\u0010>\u001a\u00020.H\u0002J\u0010\u0010?\u001a\u00020.2\u0006\u0010@\u001a\u00020\u0016H\u0002J\u0010\u0010A\u001a\u00020.2\u0006\u0010<\u001a\u00020\u0011H\u0002J\u0010\u0010B\u001a\u00020.2\u0006\u0010C\u001a\u00020DH\u0002J\b\u0010E\u001a\u00020.H\u0002J\u0010\u0010F\u001a\u00020.2\u0006\u0010C\u001a\u00020DH\u0002J\b\u0010G\u001a\u00020.H\u0002J\u0010\u0010H\u001a\u00020.2\u0006\u0010C\u001a\u00020DH\u0002J\u0010\u0010I\u001a\u00020.2\u0006\u0010J\u001a\u00020\u0016H\u0002J\u0010\u0010K\u001a\u00020.2\u0006\u0010L\u001a\u00020\rH\u0002J\u0010\u0010V\u001a\u00020N2\u0006\u0010W\u001a\u00020\u0016H\u0002J\b\u0010X\u001a\u00020QH\u0002J\u0010\u0010Y\u001a\u00020.2\u0006\u0010C\u001a\u00020DH\u0002J\b\u0010Z\u001a\u00020.H\u0002J\u0010\u0010[\u001a\u00020.2\u0006\u0010L\u001a\u00020\rH\u0002J\u0010\u0010\\\u001a\u00020.2\u0006\u0010C\u001a\u00020DH\u0002J\u0010\u0010]\u001a\u00020.2\u0006\u0010C\u001a\u00020DH\u0002J\u0010\u0010^\u001a\u00020.2\u0006\u0010C\u001a\u00020DH\u0002J\u0010\u0010_\u001a\u00020\u00112\u0006\u0010`\u001a\u00020\u0011H\u0002J\u0010\u0010a\u001a\u00020.2\u0006\u0010C\u001a\u00020DH\u0002J\u0010\u0010h\u001a\u00020.2\u0006\u0010C\u001a\u00020DH\u0002J\b\u0010i\u001a\u00020.H\u0014J\u0010\u0010j\u001a\u00020.2\u0006\u0010k\u001a\u00020\u0013H\u0002J\u0010\u0010n\u001a\u00020.2\u0006\u0010o\u001a\u00020(H\u0002J\b\u0010p\u001a\u00020.H\u0002J\u0012\u0010q\u001a\u00020.2\b\b\u0002\u0010r\u001a\u00020(H\u0002J\u0012\u0010s\u001a\u00020.2\b\b\u0002\u0010r\u001a\u00020(H\u0002J\u0010\u0010t\u001a\u00020.2\u0006\u0010u\u001a\u00020vH\u0002J\b\u0010w\u001a\u00020.H\u0002J\u0010\u0010x\u001a\u00020\u00162\u0006\u0010k\u001a\u00020\u0013H\u0002J\u0010\u0010y\u001a\u00020\u00162\u0006\u0010k\u001a\u00020\u0013H\u0002J\u0010\u0010|\u001a\u00020(2\u0006\u0010}\u001a\u00020(H\u0002J\b\u0010~\u001a\u00020\rH\u0002J\t\u0010\u007f\u001a\u00030\u0080\u0001H\u0002J\t\u0010\u0081\u0001\u001a\u00020\rH\u0002J\t\u0010\u0082\u0001\u001a\u00020\rH\u0002J\u0011\u0010\u0083\u0001\u001a\u00020.2\u0006\u0010C\u001a\u00020DH\u0002J\u0012\u0010\u0084\u0001\u001a\u00020.2\u0007\u0010\u0085\u0001\u001a\u00020\u0016H\u0002J\u0011\u0010\u0086\u0001\u001a\u00020.2\u0006\u0010C\u001a\u00020DH\u0002J+\u0010\u0087\u0001\u001a\u00020.2\u0006\u0010C\u001a\u00020D2\u0007\u0010\u0088\u0001\u001a\u00020\u00112\u0006\u0010@\u001a\u00020\u00162\u0007\u0010\u0089\u0001\u001a\u00020(H\u0002J\u0012\u0010\u008a\u0001\u001a\u00020.2\u0007\u0010\u008b\u0001\u001a\u00020DH\u0002J\u001b\u0010\u008e\u0001\u001a\u00020.2\u0007\u0010\u008f\u0001\u001a\u00020\u00162\u0007\u0010\u0090\u0001\u001a\u00020\u0016H\u0002J\u0011\u0010\u0097\u0001\u001a\u00020.2\u0006\u0010C\u001a\u00020DH\u0002J\u0011\u0010\u0098\u0001\u001a\u00020.2\u0006\u0010C\u001a\u00020DH\u0002J-\u0010\u0099\u0001\u001a\u00020.2\u0007\u0010\u009a\u0001\u001a\u00020\r2\u0007\u0010\u009b\u0001\u001a\u00020\u00162\u0007\u0010\u009c\u0001\u001a\u00020\u00162\u0007\u0010\u009d\u0001\u001a\u00020\u0016H\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R \u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010$\u001a\u0010\u0012\f\u0012\n &*\u0004\u0018\u00010\u00160\u00160%X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0004\n\u0002\u0010)R\u000e\u0010*\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010+\u001a\u0010\u0012\f\u0012\n &*\u0004\u0018\u00010\u00160\u00160%X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010,\u001a\u0010\u0012\f\u0012\n &*\u0004\u0018\u00010\u00160\u00160%X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010M\u001a\u00020NX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010O\u001a\u00020NX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u00020QX\u0082\u000e¢\u0006\u0002\n\u0000RB\u0010R\u001a6\u0012\u0004\u0012\u00020\u0011\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020N\u0012\u0004\u0012\u00020N0T0Sj\u001a\u0012\u0004\u0012\u00020\u0011\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020N\u0012\u0004\u0012\u00020N0T`UX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010b\u001a\u00020NX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010c\u001a\u00020dX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010e\u001a\u00020fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010gR\u000e\u0010l\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010m\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010z\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010{\u001a\u00020dX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u008c\u0001\u001a\u0005\u0018\u00010\u008d\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0091\u0001\u001a\u00020dX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0092\u0001\u001a\u00030\u0093\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0094\u0001\u001a\n\u0012\u0005\u0012\u00030\u0096\u00010\u0095\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u009f\u0001"}, d2 = {"Lcom/jarvis/app/DashboardActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "reminderBook", "Lcom/jarvis/app/AndroidReminderBook;", "getReminderBook", "()Lcom/jarvis/app/AndroidReminderBook;", "reminderBook$delegate", "Lkotlin/Lazy;", "screenContainer", "Landroid/widget/FrameLayout;", "bottomNav", "Landroid/widget/LinearLayout;", "railTabs", "", "Lkotlin/Pair;", "", "assistantState", "Lcom/jarvis/core/model/AssistantState;", "currentScreenIndex", "commandsFilter", "", "commandsCategory", "pendingSettingsTab", "controlSecondary", "activityFilter", "Lcom/jarvis/app/ActivityFeed$Item$Kind;", "assistantThreadFilter", "activitySearchQuery", "memoryCategory", "latestTranscript", "latestRequest", "latestResponse", "latestIntent", "latestTool", "audioPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "kotlin.jvm.PlatformType", "pendingTorch", "", "Ljava/lang/Boolean;", "pendingViewfinder", "cameraPermissionLauncher", "notificationPermissionLauncher", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "onNewIntent", "intent", "Landroid/content/Intent;", "maybeHandleSystemAssist", "onStart", "onResume", "onPause", "onStop", "screenTitle", "index", "buildBottomNavigation", "tintBottomNav", "openControlSecondary", "key", "showScreen", "wireSecondaryBack", "screen", "Landroid/view/View;", "updateTopRuntimeState", "setupHomeScreen", "tickShellClock", "setupAssistantScreen", "dispatchAssistantPrompt", VoiceService.EXTRA_TEXT, "renderAssistantThread", "container", "cpuPrevMs", "", "cpuPrevWall", "cpuEma", "", "cpuTimePrev", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "sysKhz", "path", "readCpuLoad", "setupActivityScreen", "updateJournalStats", "renderActivityFeed", "setupControlCenter", "setupAutomationsScreen", "setupMemoryScreen", "dp2px", "v", "wireLiveControls", "liveTimerStartMs", "liveTimerHandler", "Landroid/os/Handler;", "liveTimerRunnable", "com/jarvis/app/DashboardActivity$liveTimerRunnable$1", "Lcom/jarvis/app/DashboardActivity$liveTimerRunnable$1;", "startLiveTimer", "onDestroy", "renderAssistantState", "state", "pendingLiveMode", "liveScreenLinked", "setLiveViewfinder", "show", "flashScreenBadge", "activateAssistant", "liveMode", "requestNotificationPermissionOrStart", "handleAssistantEvent", NotificationCompat.CATEGORY_EVENT, "Lcom/jarvis/core/event/AssistantEvent;", "renderVoiceContent", "stateLabel", "stateDetail", "torchOn", "uiTimerHandler", "setTorch", "on", "toolFlashRow", "toolVolumeSlider", "Landroid/widget/SeekBar;", "toolBrightnessBlock", "toolTimerRow", "setupCommandsScreen", "runVoiceCommand", "phrase", "setupSettingsToggles", "bindCompanionToggle", "id", "default", "styleCompanionButtons", "root", "confirmDialog", "Landroid/app/Dialog;", "showHudConfirm", "toolId", "message", "confirmHandler", "memoryPipeline", "Lcom/jarvis/core/memory/MemoryCandidatePipeline;", "behaviorSimBuffer", "", "Lcom/jarvis/core/companion/PhoneObservation;", "setupCompanionPanel", "setupModelsPanel", "addCloudModelCard", "list", "titleText", Scopes.PROFILE, "modelId", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DashboardActivity extends AppCompatActivity {
    public static final String EXTRA_OPEN_SECONDARY = "open_secondary";
    public static final String EXTRA_OPEN_TAB = "open_tab";
    public static final int TAB_ACTIVITY = 3;
    public static final int TAB_ASSISTANT = 1;
    public static final int TAB_COMMANDS = 4;
    public static final int TAB_HOME = 0;
    public static final int TAB_LIVE = 2;
    public static final int TAB_MEMORY = 101;
    public static final int TAB_MEMORY_SCREEN = 6;
    public static final int TAB_MORE = 4;
    public static final int TAB_SCENARIOS = 5;
    public static final int TAB_SETTINGS = 4;
    public static final int TAB_TOOLS = 7;
    private ActivityFeed.Item.Kind activityFilter;
    private final ActivityResultLauncher<String> audioPermissionLauncher;
    private final List<PhoneObservation> behaviorSimBuffer;
    private LinearLayout bottomNav;
    private final ActivityResultLauncher<String> cameraPermissionLauncher;
    private String commandsCategory;
    private Dialog confirmDialog;
    private final Handler confirmHandler;
    private String controlSecondary;
    private float cpuEma;
    private long cpuPrevMs;
    private long cpuPrevWall;
    private final HashMap<Integer, Map<Long, Long>> cpuTimePrev;
    private int currentScreenIndex;
    private boolean liveScreenLinked;
    private final Handler liveTimerHandler;
    private final DashboardActivity$liveTimerRunnable$1 liveTimerRunnable;
    private long liveTimerStartMs;
    private String memoryCategory;
    private final MemoryCandidatePipeline memoryPipeline;
    private final ActivityResultLauncher<String> notificationPermissionLauncher;
    private boolean pendingLiveMode;
    private String pendingSettingsTab;
    private Boolean pendingTorch;
    private boolean pendingViewfinder;
    private FrameLayout screenContainer;
    private boolean torchOn;
    private final Handler uiTimerHandler;

    /* JADX INFO: renamed from: reminderBook$delegate, reason: from kotlin metadata */
    private final Lazy reminderBook = LazyKt.lazy(new Function0() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda15
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return DashboardActivity.reminderBook_delegate$lambda$0(this.f$0);
        }
    });
    private final List<Pair<Integer, Integer>> railTabs = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(0, Integer.valueOf(R.drawable.ic_fui_core)), TuplesKt.to(2, Integer.valueOf(R.drawable.ic_fui_live)), TuplesKt.to(1, Integer.valueOf(R.drawable.ic_fui_chat)), TuplesKt.to(5, Integer.valueOf(R.drawable.ic_fui_bolt)), TuplesKt.to(6, Integer.valueOf(R.drawable.ic_fui_grid)), TuplesKt.to(7, Integer.valueOf(R.drawable.ic_fui_tools)), TuplesKt.to(3, Integer.valueOf(R.drawable.ic_fui_hex)), TuplesKt.to(4, Integer.valueOf(R.drawable.ic_fui_profile))});
    private AssistantState assistantState = AssistantState.OFFLINE;
    private String commandsFilter = "";
    private String assistantThreadFilter = "today";
    private String activitySearchQuery = "";
    private String latestTranscript = "";
    private String latestRequest = "";
    private String latestResponse = "";
    private String latestIntent = "";
    private String latestTool = "";

    /* JADX INFO: compiled from: DashboardActivity.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;

        static {
            int[] iArr = new int[AssistantState.values().length];
            try {
                iArr[AssistantState.ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[AssistantState.THINKING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[AssistantState.EXECUTING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[AssistantState.SPEAKING.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[AssistantState.LISTENING.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[AssistantState.OFFLINE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[AssistantState.STARTING.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[AssistantState.IDLE.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[AssistantState.READY.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[AssistantCapabilities.RiskTag.values().length];
            try {
                iArr2[AssistantCapabilities.RiskTag.SAFE.ordinal()] = 1;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr2[AssistantCapabilities.RiskTag.ONLINE.ordinal()] = 2;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr2[AssistantCapabilities.RiskTag.CONFIRM.ordinal()] = 3;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr2[AssistantCapabilities.RiskTag.SENSITIVE.ordinal()] = 4;
            } catch (NoSuchFieldError e13) {
            }
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[ProactiveDecisionType.values().length];
            try {
                iArr3[ProactiveDecisionType.SUGGESTION.ordinal()] = 1;
            } catch (NoSuchFieldError e14) {
            }
            try {
                iArr3[ProactiveDecisionType.QUESTION.ordinal()] = 2;
            } catch (NoSuchFieldError e15) {
            }
            try {
                iArr3[ProactiveDecisionType.CONFIRMATION_REQUEST.ordinal()] = 3;
            } catch (NoSuchFieldError e16) {
            }
            try {
                iArr3[ProactiveDecisionType.NO_OP.ordinal()] = 4;
            } catch (NoSuchFieldError e17) {
            }
            $EnumSwitchMapping$2 = iArr3;
            int[] iArr4 = new int[ModelManager.State.values().length];
            try {
                iArr4[ModelManager.State.READY.ordinal()] = 1;
            } catch (NoSuchFieldError e18) {
            }
            try {
                iArr4[ModelManager.State.DOWNLOADING.ordinal()] = 2;
            } catch (NoSuchFieldError e19) {
            }
            try {
                iArr4[ModelManager.State.ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError e20) {
            }
            try {
                iArr4[ModelManager.State.CORRUPT.ordinal()] = 4;
            } catch (NoSuchFieldError e21) {
            }
            $EnumSwitchMapping$3 = iArr4;
        }
    }

    /* JADX WARN: Type inference failed for: r0v19, types: [com.jarvis.app.DashboardActivity$liveTimerRunnable$1] */
    public DashboardActivity() {
        ActivityResultLauncher<String> activityResultLauncherRegisterForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda16
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                DashboardActivity.audioPermissionLauncher$lambda$0(this.f$0, (Boolean) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "registerForActivityResult(...)");
        this.audioPermissionLauncher = activityResultLauncherRegisterForActivityResult;
        ActivityResultLauncher<String> activityResultLauncherRegisterForActivityResult2 = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda17
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                DashboardActivity.cameraPermissionLauncher$lambda$0(this.f$0, (Boolean) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult2, "registerForActivityResult(...)");
        this.cameraPermissionLauncher = activityResultLauncherRegisterForActivityResult2;
        ActivityResultLauncher<String> activityResultLauncherRegisterForActivityResult3 = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda18
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                DashboardActivity.notificationPermissionLauncher$lambda$0(this.f$0, (Boolean) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult3, "registerForActivityResult(...)");
        this.notificationPermissionLauncher = activityResultLauncherRegisterForActivityResult3;
        this.cpuEma = -1.0f;
        this.cpuTimePrev = new HashMap<>();
        this.liveTimerHandler = new Handler(Looper.getMainLooper());
        this.liveTimerRunnable = new Runnable() { // from class: com.jarvis.app.DashboardActivity$liveTimerRunnable$1
            @Override // java.lang.Runnable
            public void run() {
                long elapsed = SystemClock.elapsedRealtime() - this.this$0.liveTimerStartMs;
                int secs = (int) (elapsed / ((long) 1000));
                String mm = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Arrays.copyOf(new Object[]{Integer.valueOf(secs / 60)}, 1));
                Intrinsics.checkNotNullExpressionValue(mm, "format(...)");
                String ss = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Arrays.copyOf(new Object[]{Integer.valueOf(secs % 60)}, 1));
                Intrinsics.checkNotNullExpressionValue(ss, "format(...)");
                FrameLayout frameLayout = this.this$0.screenContainer;
                if (frameLayout == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
                    frameLayout = null;
                }
                TextView textView = (TextView) frameLayout.findViewById(R.id.liveTimer);
                if (textView != null) {
                    textView.setText(mm + ":" + ss);
                }
                this.this$0.liveTimerHandler.postDelayed(this, 1000L);
            }
        };
        this.uiTimerHandler = new Handler(Looper.getMainLooper());
        this.confirmHandler = new Handler(Looper.getMainLooper());
        this.memoryPipeline = new MemoryCandidatePipeline(null, null, null, 7, null);
        this.behaviorSimBuffer = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AndroidReminderBook getReminderBook() {
        return (AndroidReminderBook) this.reminderBook.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AndroidReminderBook reminderBook_delegate$lambda$0(DashboardActivity this$0) {
        return new AndroidReminderBook(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void audioPermissionLauncher$lambda$0(DashboardActivity this$0, Boolean granted) {
        if (granted.booleanValue()) {
            requestNotificationPermissionOrStart$default(this$0, false, 1, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void cameraPermissionLauncher$lambda$0(DashboardActivity this$0, Boolean granted) {
        Boolean want = this$0.pendingTorch;
        this$0.pendingTorch = null;
        if (granted.booleanValue() && want != null) {
            this$0.setTorch(want.booleanValue());
        }
        if (this$0.pendingViewfinder) {
            this$0.pendingViewfinder = false;
            if (granted.booleanValue()) {
                this$0.setLiveViewfinder(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void notificationPermissionLauncher$lambda$0(DashboardActivity this$0, Boolean it) {
        VoiceController.INSTANCE.start(this$0);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws Throwable {
        super.onCreate(savedInstanceState);
        Intent it = UiMode.INSTANCE.stepIntent(this, UiMode.INSTANCE.route(this));
        if (it != null) {
            startActivity(it);
            finish();
            return;
        }
        setContentView(R.layout.activity_dashboard_shell);
        ThemeApplier.INSTANCE.applyToActivity(this);
        View viewFindViewById = findViewById(R.id.screenContainer);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        this.screenContainer = (FrameLayout) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.bottomNav);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
        this.bottomNav = (LinearLayout) viewFindViewById2;
        buildBottomNavigation();
        TextView textView = (TextView) findViewById(R.id.btnAccentCycle);
        if (textView != null) {
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda57
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.onCreate$lambda$1(this.f$0, view);
                }
            });
        }
        int openTab = 0;
        if (savedInstanceState != null) {
            this.controlSecondary = savedInstanceState.getString("control_secondary");
            this.currentScreenIndex = savedInstanceState.getInt("current_tab", 0);
        }
        String it2 = getIntent().getStringExtra(EXTRA_OPEN_SECONDARY);
        if (it2 != null) {
            this.controlSecondary = it2;
        }
        int rawTab = getIntent().getIntExtra(EXTRA_OPEN_TAB, -1);
        if (savedInstanceState != null) {
            openTab = this.currentScreenIndex;
        } else if (getIntent().getBooleanExtra("open_live", false)) {
            openTab = 2;
        } else if (rawTab == 101) {
            openTab = 6;
        } else if (Intrinsics.areEqual(this.controlSecondary, "commands")) {
            openTab = 7;
        } else if (Intrinsics.areEqual(this.controlSecondary, JarvisActivity.MEMORY)) {
            openTab = 6;
        } else if (Intrinsics.areEqual(this.controlSecondary, "automations")) {
            openTab = 5;
        } else {
            if (rawTab >= 0 && rawTab < 8) {
                openTab = rawTab;
            }
        }
        showScreen(openTab);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AnonymousClass4(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AnonymousClass5(null), 3, null);
        maybeHandleSystemAssist(getIntent());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(DashboardActivity this$0, View it) {
        List<String> themes = JarvisSettings.INSTANCE.getTHEMES();
        String cur = JarvisSettings.INSTANCE.theme(this$0);
        Integer numValueOf = Integer.valueOf(themes.indexOf(cur));
        int it2 = numValueOf.intValue();
        if (!(it2 >= 0)) {
            numValueOf = null;
        }
        String next = themes.get(((numValueOf != null ? numValueOf.intValue() : -1) + 1) % themes.size());
        JarvisSettings.INSTANCE.setTheme(this$0, next);
        ThemeApplier.INSTANCE.recreateWithTheme(this$0);
    }

    /* JADX INFO: renamed from: com.jarvis.app.DashboardActivity$onCreate$4, reason: invalid class name */
    /* JADX INFO: compiled from: DashboardActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.DashboardActivity$onCreate$4", f = "DashboardActivity.kt", i = {}, l = {178}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DashboardActivity.this.new AnonymousClass4(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.jarvis.app.DashboardActivity$onCreate$4$1, reason: invalid class name */
        /* JADX INFO: compiled from: DashboardActivity.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.jarvis.app.DashboardActivity$onCreate$4$1", f = "DashboardActivity.kt", i = {}, l = {179}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DashboardActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(DashboardActivity dashboardActivity, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = dashboardActivity;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX INFO: renamed from: com.jarvis.app.DashboardActivity$onCreate$4$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: DashboardActivity.kt */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            static final /* synthetic */ class C00111 implements FlowCollector, FunctionAdapter {
                final /* synthetic */ DashboardActivity $tmp0;

                C00111(DashboardActivity dashboardActivity) {
                    this.$tmp0 = dashboardActivity;
                }

                public final boolean equals(Object obj) {
                    if ((obj instanceof FlowCollector) && (obj instanceof FunctionAdapter)) {
                        return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
                    }
                    return false;
                }

                @Override // kotlin.jvm.internal.FunctionAdapter
                public final Function<?> getFunctionDelegate() {
                    return new AdaptedFunctionReference(2, this.$tmp0, DashboardActivity.class, "renderAssistantState", "renderAssistantState(Lcom/jarvis/core/model/AssistantState;)V", 4);
                }

                public final int hashCode() {
                    return getFunctionDelegate().hashCode();
                }

                public final Object emit(AssistantState p0, Continuation<? super Unit> continuation) {
                    Object objInvokeSuspend$renderAssistantState = AnonymousClass1.invokeSuspend$renderAssistantState(this.$tmp0, p0, continuation);
                    return objInvokeSuspend$renderAssistantState == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objInvokeSuspend$renderAssistantState : Unit.INSTANCE;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                    return emit((AssistantState) value, (Continuation<? super Unit>) $completion);
                }
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.label = 1;
                        if (JarvisEvents.INSTANCE.getAssistantState().collect(new C00111(this.this$0), this) == coroutine_suspended) {
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

            /* JADX INFO: Access modifiers changed from: private */
            public static final /* synthetic */ Object invokeSuspend$renderAssistantState(DashboardActivity $this$invokeSuspend_u24renderAssistantState, AssistantState p0, Continuation $completion) {
                $this$invokeSuspend_u24renderAssistantState.renderAssistantState(p0);
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (RepeatOnLifecycleKt.repeatOnLifecycle(DashboardActivity.this, Lifecycle.State.STARTED, new AnonymousClass1(DashboardActivity.this, null), this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.jarvis.app.DashboardActivity$onCreate$5, reason: invalid class name */
    /* JADX INFO: compiled from: DashboardActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.DashboardActivity$onCreate$5", f = "DashboardActivity.kt", i = {}, l = {183}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass5(Continuation<? super AnonymousClass5> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DashboardActivity.this.new AnonymousClass5(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass5) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.jarvis.app.DashboardActivity$onCreate$5$1, reason: invalid class name */
        /* JADX INFO: compiled from: DashboardActivity.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.jarvis.app.DashboardActivity$onCreate$5$1", f = "DashboardActivity.kt", i = {}, l = {184}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DashboardActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(DashboardActivity dashboardActivity, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = dashboardActivity;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX INFO: renamed from: com.jarvis.app.DashboardActivity$onCreate$5$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: DashboardActivity.kt */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            static final /* synthetic */ class C00121 implements FlowCollector, FunctionAdapter {
                final /* synthetic */ DashboardActivity $tmp0;

                C00121(DashboardActivity dashboardActivity) {
                    this.$tmp0 = dashboardActivity;
                }

                public final boolean equals(Object obj) {
                    if ((obj instanceof FlowCollector) && (obj instanceof FunctionAdapter)) {
                        return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
                    }
                    return false;
                }

                @Override // kotlin.jvm.internal.FunctionAdapter
                public final Function<?> getFunctionDelegate() {
                    return new AdaptedFunctionReference(2, this.$tmp0, DashboardActivity.class, "handleAssistantEvent", "handleAssistantEvent(Lcom/jarvis/core/event/AssistantEvent;)V", 4);
                }

                public final int hashCode() {
                    return getFunctionDelegate().hashCode();
                }

                public final Object emit(AssistantEvent p0, Continuation<? super Unit> continuation) {
                    Object objInvokeSuspend$handleAssistantEvent = AnonymousClass1.invokeSuspend$handleAssistantEvent(this.$tmp0, p0, continuation);
                    return objInvokeSuspend$handleAssistantEvent == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objInvokeSuspend$handleAssistantEvent : Unit.INSTANCE;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                    return emit((AssistantEvent) value, (Continuation<? super Unit>) $completion);
                }
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.label = 1;
                        if (JarvisEvents.INSTANCE.getEvents().collect(new C00121(this.this$0), this) == coroutine_suspended) {
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

            /* JADX INFO: Access modifiers changed from: private */
            public static final /* synthetic */ Object invokeSuspend$handleAssistantEvent(DashboardActivity $this$invokeSuspend_u24handleAssistantEvent, AssistantEvent p0, Continuation $completion) {
                $this$invokeSuspend_u24handleAssistantEvent.handleAssistantEvent(p0);
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (RepeatOnLifecycleKt.repeatOnLifecycle(DashboardActivity.this, Lifecycle.State.STARTED, new AnonymousClass1(DashboardActivity.this, null), this) == coroutine_suspended) {
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
        outState.putInt("current_tab", this.currentScreenIndex);
        outState.putString("control_secondary", this.controlSecondary);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) throws Throwable {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onNewIntent(intent);
        setIntent(intent);
        maybeHandleSystemAssist(intent);
        String it = intent.getStringExtra(EXTRA_OPEN_SECONDARY);
        if (it != null) {
            this.controlSecondary = it;
        }
        int tab = intent.getIntExtra(EXTRA_OPEN_TAB, -1);
        if (tab == 101) {
            if (this.screenContainer != null) {
                showScreen(6);
                return;
            }
            return;
        }
        boolean z = false;
        if (tab >= 0 && tab < 8) {
            z = true;
        }
        if (z && this.screenContainer != null) {
            showScreen(tab);
        }
    }

    private final void maybeHandleSystemAssist(Intent intent) throws Throwable {
        if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, "android.intent.action.ASSIST")) {
            Log.i("Jarvis.Assist", "ACTION_ASSIST → VoicePipeline");
            VoiceController voiceController = VoiceController.INSTANCE;
            Context applicationContext = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            voiceController.startFromSystemAssist(applicationContext);
            if (this.screenContainer != null) {
                showScreen(2);
            }
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        VoiceController.INSTANCE.hideOverlay();
        OverlayOrbService.INSTANCE.hide(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        VoiceController.INSTANCE.hideOverlay();
        OverlayOrbService.INSTANCE.hide(this);
        if (this.screenContainer != null) {
            tickShellClock();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        if (!isFinishing() && VoiceController.INSTANCE.isLiveSession() && Settings.canDrawOverlays(this)) {
            VoiceController.INSTANCE.showOverlay();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
    }

    private final String screenTitle(int index) {
        switch (index) {
            case 0:
                return "Ядро";
            case 1:
                return "Диалог";
            case 2:
                return "Live";
            case 3:
                return "Система";
            case 4:
                return "Профиль";
            case 5:
                return "Сценарии";
            case 6:
                return "Память";
            case 7:
                return "Инструменты";
            default:
                return "JARVIS";
        }
    }

    private final void buildBottomNavigation() {
        LinearLayout linearLayout = this.bottomNav;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bottomNav");
            linearLayout = null;
        }
        linearLayout.removeAllViews();
        Iterable $this$forEach$iv = this.railTabs;
        for (Object element$iv : $this$forEach$iv) {
            Pair pair = (Pair) element$iv;
            final int tab = ((Number) pair.component1()).intValue();
            int icon = ((Number) pair.component2()).intValue();
            final ImageView $this$buildBottomNavigation_u24lambda_u240_u240 = new ImageView(this);
            $this$buildBottomNavigation_u24lambda_u240_u240.setLayoutParams(new LinearLayout.LayoutParams(-1, dp2px(44)));
            $this$buildBottomNavigation_u24lambda_u240_u240.setScaleType(ImageView.ScaleType.CENTER);
            $this$buildBottomNavigation_u24lambda_u240_u240.setImageResource(icon);
            $this$buildBottomNavigation_u24lambda_u240_u240.setTag(Integer.valueOf(tab));
            $this$buildBottomNavigation_u24lambda_u240_u240.setContentDescription(screenTitle(tab));
            $this$buildBottomNavigation_u24lambda_u240_u240.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda131
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) throws Throwable {
                    DashboardActivity.buildBottomNavigation$lambda$0$0$0($this$buildBottomNavigation_u24lambda_u240_u240, this, tab, view);
                }
            });
            LinearLayout linearLayout2 = this.bottomNav;
            if (linearLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bottomNav");
                linearLayout2 = null;
            }
            linearLayout2.addView($this$buildBottomNavigation_u24lambda_u240_u240);
        }
        tintBottomNav();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void buildBottomNavigation$lambda$0$0$0(ImageView $this_apply, DashboardActivity this$0, int $tab, View it) throws Throwable {
        HudFeedback.INSTANCE.tick($this_apply);
        this$0.controlSecondary = null;
        this$0.showScreen($tab);
    }

    private final void tintBottomNav() {
        if (this.bottomNav == null) {
            return;
        }
        String theme = JarvisSettings.INSTANCE.theme(this);
        int active = ThemeApplier.INSTANCE.primaryColor(theme);
        int muted = ThemeApplier.INSTANCE.mutedColor(theme);
        LinearLayout linearLayout = this.bottomNav;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bottomNav");
            linearLayout = null;
        }
        int childCount = linearLayout.getChildCount();
        for (int position = 0; position < childCount; position++) {
            LinearLayout linearLayout2 = this.bottomNav;
            if (linearLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bottomNav");
                linearLayout2 = null;
            }
            View childAt = linearLayout2.getChildAt(position);
            ImageView item = childAt instanceof ImageView ? (ImageView) childAt : null;
            if (item != null) {
                boolean on = Intrinsics.areEqual(item.getTag(), Integer.valueOf(this.currentScreenIndex));
                item.setColorFilter(on ? active : muted);
                item.setBackground(on ? ContextCompat.getDrawable(this, R.drawable.bg_nav_mark) : null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openControlSecondary(String key) throws Throwable {
        this.controlSecondary = key;
        showScreen(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:100:0x021c  */
    /* JADX WARN: Code duplicated, block: B:102:0x0224  */
    /* JADX WARN: Code duplicated, block: B:103:0x0227  */
    /* JADX WARN: Code duplicated, block: B:27:0x0051  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void showScreen(int index) throws Throwable {
        int screenLayout;
        String pageName;
        String str;
        this.currentScreenIndex = index;
        FrameLayout frameLayout = this.screenContainer;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
            frameLayout = null;
        }
        frameLayout.removeAllViews();
        switch (index) {
            case 0:
                screenLayout = R.layout.screen_home;
                break;
            case 1:
                screenLayout = R.layout.screen_assistant;
                break;
            case 2:
                screenLayout = R.layout.screen_live;
                break;
            case 3:
                screenLayout = R.layout.screen_activity;
                break;
            case 4:
                String str2 = this.controlSecondary;
                if (str2 != null) {
                    switch (str2) {
                        case "memory":
                            screenLayout = R.layout.screen_memory;
                            break;
                        case "commands":
                            screenLayout = R.layout.screen_skills;
                            break;
                        case "automations":
                            screenLayout = R.layout.screen_automations;
                            break;
                        default:
                            screenLayout = R.layout.screen_settings;
                            break;
                    }
                } else {
                    screenLayout = R.layout.screen_settings;
                    break;
                }
                break;
            case 5:
                screenLayout = R.layout.screen_automations;
                break;
            case 6:
                screenLayout = R.layout.screen_memory;
                break;
            case 7:
                screenLayout = R.layout.screen_skills;
                break;
            default:
                screenLayout = R.layout.screen_not_implemented;
                break;
        }
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this);
        FrameLayout frameLayout2 = this.screenContainer;
        if (frameLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
            frameLayout2 = null;
        }
        View screen = layoutInflaterFrom.inflate(screenLayout, (ViewGroup) frameLayout2, false);
        if (index != 4 || this.controlSecondary == null || CollectionsKt.contains(SetsKt.setOf((Object[]) new String[]{"automations", "companion", "models", "advanced"}), this.controlSecondary)) {
            FrameLayout frameLayout3 = this.screenContainer;
            if (frameLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
                frameLayout3 = null;
            }
            frameLayout3.addView(screen);
        } else {
            LinearLayout wrap = new LinearLayout(this);
            wrap.setOrientation(1);
            wrap.setBackgroundColor(ContextCompat.getColor(this, R.color.jarvis_bg));
            TextView $this$showScreen_u24lambda_u241 = new TextView(this);
            $this$showScreen_u24lambda_u241.setText("← Кабинет");
            int screenLayout2 = R.color.jarvis_cyan;
            $this$showScreen_u24lambda_u241.setTextColor(ContextCompat.getColor(this, screenLayout2));
            $this$showScreen_u24lambda_u241.setTextSize(12.0f);
            $this$showScreen_u24lambda_u241.setPadding(dp2px(16), dp2px(10), dp2px(16), dp2px(6));
            $this$showScreen_u24lambda_u241.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda41
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) throws Throwable {
                    DashboardActivity.showScreen$lambda$1$0(this.f$0, view);
                }
            });
            wrap.addView($this$showScreen_u24lambda_u241);
            wrap.addView(screen, new LinearLayout.LayoutParams(-1, -1));
            FrameLayout frameLayout4 = this.screenContainer;
            if (frameLayout4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
                frameLayout4 = null;
            }
            frameLayout4.addView(wrap);
        }
        if (index == 4 && Intrinsics.areEqual(this.controlSecondary, "companion")) {
            pageName = "Присутствие";
        } else if (index == 4 && Intrinsics.areEqual(this.controlSecondary, "models")) {
            pageName = "Модели";
        } else {
            pageName = (index == 4 && Intrinsics.areEqual(this.controlSecondary, "advanced")) ? "Профиль" : screenTitle(index);
        }
        TextView textView = (TextView) findViewById(R.id.statusTitle);
        if (textView != null) {
            textView.setText(pageName);
        }
        tickShellClock();
        TextView textView2 = (TextView) screen.findViewById(R.id.placeholderTitle);
        if (textView2 != null) {
            textView2.setText(screenTitle(index) + " · скоро");
        }
        switch (index) {
            case 0:
                Intrinsics.checkNotNull(screen);
                setupHomeScreen(screen);
                break;
            case 1:
                Intrinsics.checkNotNull(screen);
                setupAssistantScreen(screen);
                break;
            case 2:
                ConsciousnessView consciousnessView = (ConsciousnessView) screen.findViewById(R.id.liveOrb);
                if (consciousnessView != null) {
                    consciousnessView.setOnTap(new Function0() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda42
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DashboardActivity.showScreen$lambda$2(this.f$0);
                        }
                    });
                }
                View viewFindViewById = screen.findViewById(R.id.liveActivate);
                if (viewFindViewById != null) {
                    viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda43
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            DashboardActivity.showScreen$lambda$3(this.f$0, view);
                        }
                    });
                }
                View viewFindViewById2 = screen.findViewById(R.id.liveStop);
                if (viewFindViewById2 != null) {
                    viewFindViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda45
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            DashboardActivity.showScreen$lambda$4(this.f$0, view);
                        }
                    });
                }
                View viewFindViewById3 = screen.findViewById(R.id.liveMinimize);
                if (viewFindViewById3 != null) {
                    viewFindViewById3.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda46
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            DashboardActivity.showScreen$lambda$5(this.f$0, view);
                        }
                    });
                }
                View viewFindViewById4 = screen.findViewById(R.id.liveInterrupt);
                if (viewFindViewById4 != null) {
                    viewFindViewById4.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda47
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            DashboardActivity.showScreen$lambda$6(this.f$0, view);
                        }
                    });
                }
                View viewFindViewById5 = screen.findViewById(R.id.liveOpenAssistant);
                if (viewFindViewById5 != null) {
                    viewFindViewById5.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda48
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) throws Throwable {
                            this.f$0.showScreen(1);
                        }
                    });
                }
                String strPersona = JarvisSettings.INSTANCE.persona(this);
                Locale locale = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
                String persona = strPersona.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(persona, "toLowerCase(...)");
                TextView textView3 = (TextView) screen.findViewById(R.id.livePersonaChip);
                if (textView3 != null) {
                    textView3.setText("персона · " + persona);
                }
                TextView textView4 = (TextView) screen.findViewById(R.id.liveModelChip);
                if (textView4 != null) {
                    textView4.setText(JarvisSettings.INSTANCE.preferCloudLlm(this) ? "модель · hybrid" : "модель · local");
                }
                TextView textView5 = (TextView) screen.findViewById(R.id.liveNetChip);
                if (textView5 != null) {
                    String strAsrMode = JarvisSettings.INSTANCE.asrMode(this);
                    Locale locale2 = Locale.getDefault();
                    Intrinsics.checkNotNullExpressionValue(locale2, "getDefault(...)");
                    String lowerCase = strAsrMode.toLowerCase(locale2);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                    textView5.setText("asr · " + lowerCase);
                }
                Intrinsics.checkNotNull(screen);
                wireLiveControls(screen);
                startLiveTimer(screen);
                break;
            case 3:
                Intrinsics.checkNotNull(screen);
                setupActivityScreen(screen);
                break;
            case 4:
                String str3 = this.controlSecondary;
                if (str3 != null) {
                    switch (str3.hashCode()) {
                        case -1412832500:
                            if (!str3.equals("companion")) {
                                if (Intrinsics.areEqual(this.controlSecondary, "advanced")) {
                                    str = "main";
                                } else {
                                    str = this.pendingSettingsTab;
                                }
                                this.pendingSettingsTab = str;
                                Intrinsics.checkNotNull(screen);
                                setupSettingsToggles(screen);
                            } else {
                                this.pendingSettingsTab = "companion";
                                Intrinsics.checkNotNull(screen);
                                setupSettingsToggles(screen);
                            }
                            break;
                        case -1077756671:
                            if (!str3.equals(JarvisActivity.MEMORY)) {
                                if (Intrinsics.areEqual(this.controlSecondary, "advanced")) {
                                    str = "main";
                                } else {
                                    str = this.pendingSettingsTab;
                                }
                                this.pendingSettingsTab = str;
                                Intrinsics.checkNotNull(screen);
                                setupSettingsToggles(screen);
                            } else {
                                Intrinsics.checkNotNull(screen);
                                setupMemoryScreen(screen);
                            }
                            break;
                        case -1068799382:
                            if (!str3.equals("models")) {
                                if (Intrinsics.areEqual(this.controlSecondary, "advanced")) {
                                    str = "main";
                                } else {
                                    str = this.pendingSettingsTab;
                                }
                                this.pendingSettingsTab = str;
                                Intrinsics.checkNotNull(screen);
                                setupSettingsToggles(screen);
                            } else {
                                this.pendingSettingsTab = "models";
                                Intrinsics.checkNotNull(screen);
                                setupSettingsToggles(screen);
                            }
                            break;
                        case -602535288:
                            if (!str3.equals("commands")) {
                                if (Intrinsics.areEqual(this.controlSecondary, "advanced")) {
                                    str = "main";
                                } else {
                                    str = this.pendingSettingsTab;
                                }
                                this.pendingSettingsTab = str;
                                Intrinsics.checkNotNull(screen);
                                setupSettingsToggles(screen);
                            } else {
                                Intrinsics.checkNotNull(screen);
                                setupCommandsScreen(screen);
                            }
                            break;
                        case 2080280124:
                            if (!str3.equals("automations")) {
                                if (Intrinsics.areEqual(this.controlSecondary, "advanced")) {
                                    str = "main";
                                } else {
                                    str = this.pendingSettingsTab;
                                }
                                this.pendingSettingsTab = str;
                                Intrinsics.checkNotNull(screen);
                                setupSettingsToggles(screen);
                            } else {
                                Intrinsics.checkNotNull(screen);
                                setupAutomationsScreen(screen);
                            }
                            break;
                        default:
                            if (Intrinsics.areEqual(this.controlSecondary, "advanced")) {
                                str = "main";
                            } else {
                                str = this.pendingSettingsTab;
                            }
                            this.pendingSettingsTab = str;
                            Intrinsics.checkNotNull(screen);
                            setupSettingsToggles(screen);
                            break;
                    }
                } else {
                    if (Intrinsics.areEqual(this.controlSecondary, "advanced")) {
                        str = "main";
                    } else {
                        str = this.pendingSettingsTab;
                    }
                    this.pendingSettingsTab = str;
                    Intrinsics.checkNotNull(screen);
                    setupSettingsToggles(screen);
                    break;
                }
                break;
            case 5:
                Intrinsics.checkNotNull(screen);
                setupAutomationsScreen(screen);
                break;
            case 6:
                Intrinsics.checkNotNull(screen);
                setupMemoryScreen(screen);
                break;
            case 7:
                Intrinsics.checkNotNull(screen);
                setupCommandsScreen(screen);
                break;
        }
        renderAssistantState(this.assistantState);
        renderVoiceContent();
        updateTopRuntimeState();
        ThemeApplier.INSTANCE.applyToActivity(this);
        ThemeApplier themeApplier = ThemeApplier.INSTANCE;
        FrameLayout frameLayout5 = this.screenContainer;
        if (frameLayout5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
            frameLayout5 = null;
        }
        ThemeApplier.applyToScreen$default(themeApplier, frameLayout5, null, 2, null);
        tintBottomNav();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showScreen$lambda$1$0(DashboardActivity this$0, View it) throws Throwable {
        this$0.controlSecondary = null;
        this$0.showScreen(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showScreen$lambda$2(DashboardActivity this$0) {
        if (this$0.assistantState == AssistantState.OFFLINE || this$0.assistantState == AssistantState.ERROR) {
            this$0.activateAssistant(true);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showScreen$lambda$3(DashboardActivity this$0, View it) {
        if (this$0.assistantState == AssistantState.OFFLINE || this$0.assistantState == AssistantState.ERROR) {
            this$0.activateAssistant(true);
        } else if (this$0.assistantState != AssistantState.STARTING) {
            VoiceController.INSTANCE.stop(this$0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showScreen$lambda$4(DashboardActivity this$0, View it) {
        HudFeedback hudFeedback = HudFeedback.INSTANCE;
        Intrinsics.checkNotNull(it);
        hudFeedback.key(it);
        if (this$0.assistantState != AssistantState.OFFLINE) {
            VoiceController.INSTANCE.stop(this$0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showScreen$lambda$5(DashboardActivity this$0, View it) {
        HudFeedback hudFeedback = HudFeedback.INSTANCE;
        Intrinsics.checkNotNull(it);
        hudFeedback.key(it);
        if (Settings.canDrawOverlays(this$0)) {
            OverlayOrbService.INSTANCE.show(this$0);
            this$0.moveTaskToBack(true);
        } else {
            Toast.makeText(this$0, "Нужно разрешение «поверх других окон» для свернуть в оверлей.", 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showScreen$lambda$6(DashboardActivity this$0, View it) {
        HudFeedback hudFeedback = HudFeedback.INSTANCE;
        Intrinsics.checkNotNull(it);
        hudFeedback.key(it);
        VoiceController.INSTANCE.stopSpeaking();
        Toast.makeText(this$0, "Ответ прерван", 0).show();
    }

    private final void wireSecondaryBack(View screen) {
        TextView existing = (TextView) screen.findViewById(R.id.btnBackToControl);
        if (existing != null) {
            existing.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda7
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) throws Throwable {
                    DashboardActivity.wireSecondaryBack$lambda$0(this.f$0, view);
                }
            });
            return;
        }
        LinearLayout linearLayout = null;
        LinearLayout linearLayout2 = screen instanceof LinearLayout ? (LinearLayout) screen : null;
        if (linearLayout2 == null) {
            ScrollView scrollView = screen instanceof ScrollView ? (ScrollView) screen : null;
            View childAt = scrollView != null ? scrollView.getChildAt(0) : null;
            if (childAt instanceof LinearLayout) {
                linearLayout = (LinearLayout) childAt;
            }
        } else {
            linearLayout = linearLayout2;
        }
        LinearLayout root = linearLayout;
        if (root != null) {
            TextView $this$wireSecondaryBack_u24lambda_u241_u240 = new TextView(this);
            $this$wireSecondaryBack_u24lambda_u241_u240.setId(R.id.btnBackToControl);
            $this$wireSecondaryBack_u24lambda_u241_u240.setText("← Кабинет");
            $this$wireSecondaryBack_u24lambda_u241_u240.setTextColor(ContextCompat.getColor(this, R.color.jarvis_cyan));
            $this$wireSecondaryBack_u24lambda_u241_u240.setTextSize(12.0f);
            $this$wireSecondaryBack_u24lambda_u241_u240.setPadding(0, dp2px(4), 0, dp2px(8));
            $this$wireSecondaryBack_u24lambda_u241_u240.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) throws Throwable {
                    DashboardActivity.wireSecondaryBack$lambda$1$0$0(this.f$0, view);
                }
            });
            root.addView($this$wireSecondaryBack_u24lambda_u241_u240, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void wireSecondaryBack$lambda$0(DashboardActivity this$0, View it) throws Throwable {
        this$0.controlSecondary = null;
        this$0.showScreen(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void wireSecondaryBack$lambda$1$0$0(DashboardActivity this$0, View it) throws Throwable {
        this$0.controlSecondary = null;
        this$0.pendingSettingsTab = null;
        this$0.showScreen(4);
    }

    private final void updateTopRuntimeState() {
        TextView $this$updateTopRuntimeState_u24lambda_u240 = (TextView) findViewById(R.id.topRuntimeState);
        if ($this$updateTopRuntimeState_u24lambda_u240 != null) {
            boolean cloud = JarvisSettings.INSTANCE.preferCloudLlm(this) && JarvisSettings.INSTANCE.cloudConfigured(this);
            $this$updateTopRuntimeState_u24lambda_u240.setText(cloud ? "гибрид" : "локально");
            $this$updateTopRuntimeState_u24lambda_u240.setTextColor(ContextCompat.getColor(this, cloud ? R.color.jarvis_cyan : R.color.jarvis_green));
        }
    }

    private final void setupHomeScreen(final View screen) {
        View viewFindViewById = screen.findViewById(R.id.activateAssistant);
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda36
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupHomeScreen$lambda$0(this.f$0, view);
                }
            });
        }
        View viewFindViewById2 = screen.findViewById(R.id.homeToChat);
        if (viewFindViewById2 != null) {
            viewFindViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda37
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) throws Throwable {
                    this.f$0.showScreen(1);
                }
            });
        }
        setupHomeScreen$paintHome(this, screen);
        TextView textView = (TextView) screen.findViewById(R.id.homeRouterCycle);
        if (textView != null) {
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda38
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupHomeScreen$lambda$2(this.f$0, screen, view);
                }
            });
        }
        TextView textView2 = (TextView) screen.findViewById(R.id.homeWakeToggle);
        if (textView2 != null) {
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda39
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupHomeScreen$lambda$3(this.f$0, screen, view);
                }
            });
        }
        TextView textView3 = (TextView) screen.findViewById(R.id.homeOverlayToggle);
        if (textView3 != null) {
            textView3.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda40
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupHomeScreen$lambda$4(this.f$0, view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupHomeScreen$lambda$0(DashboardActivity this$0, View it) {
        Log.d("Jarvis.Debug", "activate clicked state=" + this$0.assistantState);
        if (this$0.assistantState == AssistantState.OFFLINE || this$0.assistantState == AssistantState.ERROR) {
            activateAssistant$default(this$0, false, 1, null);
        } else if (this$0.assistantState != AssistantState.STARTING) {
            VoiceController.INSTANCE.stop(this$0);
        }
    }

    private static final void setupHomeScreen$paintHome(DashboardActivity this$0, View $screen) {
        boolean wake = JarvisSettings.INSTANCE.wakeEnabled(this$0);
        String router = JarvisSettings.INSTANCE.routerMode(this$0);
        String model = Intrinsics.areEqual(router, "LOCAL") ? "LOCAL LLM" : JarvisSettings.INSTANCE.cloudModelForProfile(this$0);
        boolean live = VoiceController.INSTANCE.isRunning();
        TextView textView = (TextView) $screen.findViewById(R.id.homeRouterMode);
        if (textView != null) {
            textView.setText("● ROUTER  " + router);
        }
        TextView textView2 = (TextView) $screen.findViewById(R.id.homeRouterCycle);
        if (textView2 != null) {
            textView2.setText("AI ROUTER\n● " + router);
        }
        TextView textView3 = (TextView) $screen.findViewById(R.id.homeWakeToggle);
        if (textView3 != null) {
            textView3.setText(wake ? "WAKE ENGINE\n● ARMED" : "WAKE ENGINE\n● PAUSED");
        }
        TextView textView4 = (TextView) $screen.findViewById(R.id.homeOverlayToggle);
        if (textView4 != null) {
            textView4.setText("OVERLAY\n● LAUNCH ORB");
        }
        TextView textView5 = (TextView) $screen.findViewById(R.id.homeModelChip);
        if (textView5 != null) {
            textView5.setText("MODEL  " + model);
        }
        TextView textView6 = (TextView) $screen.findViewById(R.id.homeLatencyChip);
        if (textView6 != null) {
            textView6.setText("LAT  —");
        }
        TextView textView7 = (TextView) $screen.findViewById(R.id.homeCoreChip);
        if (textView7 != null) {
            textView7.setText(live ? "CORE  ACTIVE" : "CORE  STANDBY");
        }
        Object systemService = this$0.getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        ActivityManager am = (ActivityManager) systemService;
        ActivityManager.MemoryInfo mem = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mem);
        int ramPct = mem.totalMem == 0 ? 0 : (int) (((mem.totalMem - mem.availMem) * ((long) 100)) / mem.totalMem);
        String ramTag = ramPct < 85 ? "OK" : "HIGH";
        TextView textView8 = (TextView) $screen.findViewById(R.id.homeClock);
        if (textView8 != null) {
            textView8.setText("RAM " + ramTag + " · " + ramPct + "%");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupHomeScreen$lambda$2(DashboardActivity this$0, View $screen, View it) {
        JarvisSettings.INSTANCE.cycleRouterMode(this$0);
        setupHomeScreen$paintHome(this$0, $screen);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupHomeScreen$lambda$3(DashboardActivity this$0, View $screen, View it) {
        JarvisSettings.INSTANCE.setWakeEnabled(this$0, !JarvisSettings.INSTANCE.wakeEnabled(this$0));
        setupHomeScreen$paintHome(this$0, $screen);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupHomeScreen$lambda$4(DashboardActivity this$0, View it) {
        if (Settings.canDrawOverlays(this$0)) {
            OverlayOrbService.INSTANCE.show(this$0);
        } else {
            this$0.startActivity(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + this$0.getPackageName())));
        }
    }

    private final void tickShellClock() {
        TextView textView = (TextView) findViewById(R.id.shellClock);
        if (textView != null) {
            textView.setText(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date()));
        }
    }

    private final void setupAssistantScreen(final View screen) {
        final LinearLayout thread = (LinearLayout) screen.findViewById(R.id.assistantThread);
        if (thread == null) {
            return;
        }
        final EditText composer = (EditText) screen.findViewById(R.id.assistantComposer);
        TextView textView = (TextView) screen.findViewById(R.id.chipThreadToday);
        if (textView != null) {
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda96
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupAssistantScreen$applyFilter(this.f$0, thread, screen, "today");
                }
            });
        }
        TextView textView2 = (TextView) screen.findViewById(R.id.chipThreadWeek);
        if (textView2 != null) {
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda97
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupAssistantScreen$applyFilter(this.f$0, thread, screen, "week");
                }
            });
        }
        TextView textView3 = (TextView) screen.findViewById(R.id.chipThreadFav);
        if (textView3 != null) {
            textView3.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda99
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupAssistantScreen$applyFilter(this.f$0, thread, screen, "fav");
                }
            });
        }
        TextView textView4 = (TextView) screen.findViewById(R.id.btnClearThread);
        if (textView4 != null) {
            textView4.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda100
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupAssistantScreen$lambda$4(this.f$0, thread, view);
                }
            });
        }
        View viewFindViewById = screen.findViewById(R.id.assistantMic);
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda101
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupAssistantScreen$lambda$5(this.f$0, view);
                }
            });
        }
        TextView textView5 = (TextView) screen.findViewById(R.id.assistantSend);
        if (textView5 != null) {
            textView5.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda102
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupAssistantScreen$sendComposer(this.f$0, composer);
                }
            });
        }
        if (composer != null) {
            composer.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda103
                @Override // android.widget.TextView.OnEditorActionListener
                public final boolean onEditorAction(TextView textView6, int i, KeyEvent keyEvent) {
                    return DashboardActivity.setupAssistantScreen$lambda$7(this.f$0, composer, textView6, i, keyEvent);
                }
            });
        }
        TextView textView6 = (TextView) screen.findViewById(R.id.suggestPlan);
        if (textView6 != null) {
            textView6.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda104
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupAssistantScreen$queuePrompt(composer, this, "Помоги расставить приоритеты на сегодня");
                }
            });
        }
        TextView textView7 = (TextView) screen.findViewById(R.id.suggestIdea);
        if (textView7 != null) {
            textView7.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda105
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupAssistantScreen$queuePrompt(composer, this, "Помоги разобраться с новой идеей и разложить её на шаги");
                }
            });
        }
        setupAssistantScreen$applyFilter(this, thread, screen, this.assistantThreadFilter);
    }

    private static final void setupAssistantScreen$styleChip(View $screen, DashboardActivity this$0, int id, boolean active) {
        TextView $this$setupAssistantScreen_u24styleChip_u24lambda_u240 = (TextView) $screen.findViewById(id);
        if ($this$setupAssistantScreen_u24styleChip_u24lambda_u240 != null) {
            $this$setupAssistantScreen_u24styleChip_u24lambda_u240.setBackground(ContextCompat.getDrawable(this$0, active ? R.drawable.bg_fui_active : R.drawable.bg_hud_panel));
            $this$setupAssistantScreen_u24styleChip_u24lambda_u240.setTextColor(ContextCompat.getColor(this$0, active ? R.color.jarvis_text : R.color.jarvis_muted));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupAssistantScreen$applyFilter(DashboardActivity this$0, LinearLayout thread, View $screen, String filter) {
        this$0.assistantThreadFilter = filter;
        setupAssistantScreen$styleChip($screen, this$0, R.id.chipThreadToday, Intrinsics.areEqual(filter, "today"));
        setupAssistantScreen$styleChip($screen, this$0, R.id.chipThreadWeek, Intrinsics.areEqual(filter, "week"));
        setupAssistantScreen$styleChip($screen, this$0, R.id.chipThreadFav, Intrinsics.areEqual(filter, "fav"));
        this$0.renderAssistantThread(thread);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupAssistantScreen$lambda$4(DashboardActivity this$0, LinearLayout $thread, View it) {
        ConversationStore.INSTANCE.clear();
        this$0.renderAssistantThread($thread);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupAssistantScreen$lambda$5(DashboardActivity this$0, View it) {
        if (this$0.assistantState == AssistantState.OFFLINE || this$0.assistantState == AssistantState.ERROR) {
            activateAssistant$default(this$0, false, 1, null);
        } else {
            Toast.makeText(this$0, "Слушаю… скажи команду", 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupAssistantScreen$sendComposer(DashboardActivity this$0, EditText composer) {
        Editable text;
        String string = (composer == null || (text = composer.getText()) == null) ? null : text.toString();
        if (string == null) {
            string = "";
        }
        this$0.dispatchAssistantPrompt(string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean setupAssistantScreen$lambda$7(DashboardActivity this$0, EditText $composer, TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == 4) {
            setupAssistantScreen$sendComposer(this$0, $composer);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupAssistantScreen$queuePrompt(EditText composer, DashboardActivity this$0, String text) {
        if (composer != null) {
            composer.setText(text);
        }
        setupAssistantScreen$sendComposer(this$0, composer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void dispatchAssistantPrompt(String text) {
        String clean = StringsKt.trim((CharSequence) text).toString();
        if (StringsKt.isBlank(clean)) {
            return;
        }
        if (VoiceController.INSTANCE.isRunning()) {
            HudFeedback.INSTANCE.beep(this, 25);
        }
        FrameLayout frameLayout = this.screenContainer;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
            frameLayout = null;
        }
        EditText editText = (EditText) frameLayout.findViewById(R.id.assistantComposer);
        if (editText != null) {
            editText.setText("");
        }
        ConversationStore.INSTANCE.pushUser(clean);
        ActivityFeed.push$default(ActivityFeed.INSTANCE, "Текстовый запрос", clean, ActivityFeed.Item.Kind.VOICE, false, false, 24, null);
        FrameLayout frameLayout2 = this.screenContainer;
        if (frameLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
            frameLayout2 = null;
        }
        LinearLayout it = (LinearLayout) frameLayout2.findViewById(R.id.assistantThread);
        if (it != null) {
            renderAssistantThread(it);
        }
        if (!VoiceController.INSTANCE.isRunning()) {
            activateAssistant$default(this, false, 1, null);
        }
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AnonymousClass2(clean, this, null), 3, null);
    }

    /* JADX INFO: renamed from: com.jarvis.app.DashboardActivity$dispatchAssistantPrompt$2, reason: invalid class name */
    /* JADX INFO: compiled from: DashboardActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.DashboardActivity$dispatchAssistantPrompt$2", f = "DashboardActivity.kt", i = {}, l = {617}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $clean;
        int label;
        final /* synthetic */ DashboardActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, DashboardActivity dashboardActivity, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$clean = str;
            this.this$0 = dashboardActivity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$clean, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (DelayKt.delay(350L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            if (VoiceController.INSTANCE.isRunning()) {
                VoiceController.INSTANCE.notifyRequest$app_debug(new UserRequest(this.$clean, null, null, 0L, null, 30, null));
            } else {
                ConversationStore.pushAi$default(ConversationStore.INSTANCE, "Сервис голоса ещё не запущен. Нажми активацию на Главной и повтори.", "RETRY", null, 4, null);
                FrameLayout frameLayout = this.this$0.screenContainer;
                if (frameLayout == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
                    frameLayout = null;
                }
                LinearLayout it = (LinearLayout) frameLayout.findViewById(R.id.assistantThread);
                if (it != null) {
                    this.this$0.renderAssistantThread(it);
                }
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderAssistantThread(final LinearLayout container) {
        Object objM392constructorimpl;
        Object objM392constructorimpl2;
        container.removeAllViews();
        String str = this.assistantThreadFilter;
        List<ConversationStore.Bubble> listWeek = Intrinsics.areEqual(str, "week") ? ConversationStore.INSTANCE.week() : Intrinsics.areEqual(str, "fav") ? ConversationStore.INSTANCE.favorites() : ConversationStore.INSTANCE.today();
        int i = -2;
        int i2 = -1;
        int i3 = 8;
        int i4 = 0;
        if (!listWeek.isEmpty()) {
            List<ConversationStore.Bubble> $this$forEach$iv = listWeek;
            int $i$f$forEach = 0;
            for (Object element$iv : $this$forEach$iv) {
                final ConversationStore.Bubble b = (ConversationStore.Bubble) element$iv;
                LinearLayout $this$renderAssistantThread_u24lambda_u248_u240 = new LinearLayout(this);
                $this$renderAssistantThread_u24lambda_u248_u240.setOrientation(1);
                $this$renderAssistantThread_u24lambda_u248_u240.setBackground(ContextCompat.getDrawable(this, b.getRole() == ConversationStore.Bubble.Role.USER ? R.drawable.bg_fui_active : R.drawable.bg_hud_panel));
                Iterable $this$forEach$iv2 = $this$forEach$iv;
                $this$renderAssistantThread_u24lambda_u248_u240.setPadding(dp2px(12), dp2px(10), dp2px(12), dp2px(10));
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2);
                lp.bottomMargin = dp2px(8);
                if (b.getRole() == ConversationStore.Bubble.Role.USER) {
                    lp.setMarginStart(dp2px(28));
                } else {
                    lp.setMarginEnd(dp2px(12));
                }
                $this$renderAssistantThread_u24lambda_u248_u240.setLayoutParams(lp);
                $this$renderAssistantThread_u24lambda_u248_u240.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda13
                    @Override // android.view.View.OnLongClickListener
                    public final boolean onLongClick(View view) {
                        return DashboardActivity.renderAssistantThread$lambda$8$0$0(b, this, container, view);
                    }
                });
                if (b.getBadge() != null) {
                    TextView $this$renderAssistantThread_u24lambda_u248_u241 = new TextView(this);
                    $this$renderAssistantThread_u24lambda_u248_u241.setText(b.getBadge());
                    $this$renderAssistantThread_u24lambda_u248_u241.setTextColor(ContextCompat.getColor(this, R.color.jarvis_green));
                    $this$renderAssistantThread_u24lambda_u248_u241.setTextSize(9.0f);
                    $this$renderAssistantThread_u24lambda_u248_u241.setTypeface(Typeface.MONOSPACE);
                    $this$renderAssistantThread_u24lambda_u248_u240.addView($this$renderAssistantThread_u24lambda_u248_u241);
                }
                TextView $this$renderAssistantThread_u24lambda_u248_u242 = new TextView(this);
                $this$renderAssistantThread_u24lambda_u248_u242.setText(b.getText());
                $this$renderAssistantThread_u24lambda_u248_u242.setTextColor(ContextCompat.getColor(this, R.color.jarvis_text));
                $this$renderAssistantThread_u24lambda_u248_u242.setTextSize(13.0f);
                $this$renderAssistantThread_u24lambda_u248_u242.setPadding(0, b.getBadge() != null ? dp2px(6) : 0, 0, 0);
                $this$renderAssistantThread_u24lambda_u248_u240.addView($this$renderAssistantThread_u24lambda_u248_u242);
                String plan = b.getPlan();
                if (!(plan == null || StringsKt.isBlank(plan))) {
                    TextView $this$renderAssistantThread_u24lambda_u248_u243 = new TextView(this);
                    $this$renderAssistantThread_u24lambda_u248_u243.setText("PLAN · " + b.getPlan());
                    $this$renderAssistantThread_u24lambda_u248_u243.setTextColor(ContextCompat.getColor(this, R.color.jarvis_cyan));
                    $this$renderAssistantThread_u24lambda_u248_u243.setTextSize(10.0f);
                    $this$renderAssistantThread_u24lambda_u248_u243.setTypeface(Typeface.MONOSPACE);
                    $this$renderAssistantThread_u24lambda_u248_u243.setPadding(0, dp2px(6), 0, 0);
                    $this$renderAssistantThread_u24lambda_u248_u240.addView($this$renderAssistantThread_u24lambda_u248_u243);
                }
                TextView $this$renderAssistantThread_u24lambda_u248_u244 = new TextView(this);
                StringBuilder $this$renderAssistantThread_u24lambda_u248_u244_u240 = new StringBuilder();
                List<ConversationStore.Bubble> list = listWeek;
                int $i$f$forEach2 = $i$f$forEach;
                $this$renderAssistantThread_u24lambda_u248_u244_u240.append(ConversationStore.INSTANCE.formatTime(b.getAtMs()));
                $this$renderAssistantThread_u24lambda_u248_u244_u240.append(b.getRole() == ConversationStore.Bubble.Role.USER ? " · you" : " · JARVIS");
                if (b.getFavorite()) {
                    $this$renderAssistantThread_u24lambda_u248_u244_u240.append(" · ★");
                }
                $this$renderAssistantThread_u24lambda_u248_u244.setText($this$renderAssistantThread_u24lambda_u248_u244_u240.toString());
                $this$renderAssistantThread_u24lambda_u248_u244.setTextColor(ContextCompat.getColor(this, R.color.jarvis_faint));
                $this$renderAssistantThread_u24lambda_u248_u244.setTextSize(9.0f);
                $this$renderAssistantThread_u24lambda_u248_u244.setPadding(0, dp2px(6), 0, 0);
                $this$renderAssistantThread_u24lambda_u248_u240.addView($this$renderAssistantThread_u24lambda_u248_u244);
                container.addView($this$renderAssistantThread_u24lambda_u248_u240);
                $this$forEach$iv = $this$forEach$iv2;
                listWeek = list;
                $i$f$forEach = $i$f$forEach2;
            }
            FrameLayout frameLayout = this.screenContainer;
            if (frameLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
                frameLayout = null;
            }
            ScrollView scrollView = (ScrollView) frameLayout.findViewById(R.id.assistantThreadScroll);
            if (scrollView != null) {
                scrollView.post(new Runnable() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda14
                    @Override // java.lang.Runnable
                    public final void run() {
                        DashboardActivity.renderAssistantThread$lambda$9(this.f$0);
                    }
                });
                return;
            }
            return;
        }
        List prompts = CollectionsKt.listOf((Object[]) new String[]{"Запусти диагностику", "Включи фонарик", "Что в моей памяти?", "Статус батареи и сети"});
        LinearLayout $this$renderAssistantThread_u24lambda_u240 = new LinearLayout(this);
        $this$renderAssistantThread_u24lambda_u240.setOrientation(1);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(-1, -2);
        lp2.topMargin = dp2px(8);
        $this$renderAssistantThread_u24lambda_u240.setLayoutParams(lp2);
        Iterable $this$forEach$iv3 = CollectionsKt.chunked(prompts, 2);
        int $i$f$forEach3 = 0;
        for (Object element$iv2 : $this$forEach$iv3) {
            Iterable rowLabels = (List) element$iv2;
            LinearLayout $this$renderAssistantThread_u24lambda_u241_u240 = new LinearLayout(this);
            $this$renderAssistantThread_u24lambda_u241_u240.setOrientation(i4);
            LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(i2, i);
            lp3.bottomMargin = dp2px(i3);
            $this$renderAssistantThread_u24lambda_u241_u240.setLayoutParams(lp3);
            Iterable $this$forEachIndexed$iv = rowLabels;
            int $i$f$forEachIndexed = 0;
            int index = 0;
            for (Object item$iv : $this$forEachIndexed$iv) {
                int index$iv = index + 1;
                if (index < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                final String label = (String) item$iv;
                TextView $this$renderAssistantThread_u24lambda_u241_u241_u240 = new TextView(this);
                Iterable $this$forEach$iv4 = $this$forEach$iv3;
                $this$renderAssistantThread_u24lambda_u241_u241_u240.setText(label);
                int $i$f$forEach4 = $i$f$forEach3;
                $this$renderAssistantThread_u24lambda_u241_u241_u240.setGravity(17);
                int $i$f$forEach5 = R.color.jarvis_cyan;
                $this$renderAssistantThread_u24lambda_u241_u241_u240.setTextColor(ContextCompat.getColor(this, $i$f$forEach5));
                $this$renderAssistantThread_u24lambda_u241_u241_u240.setTextSize(11.0f);
                $this$renderAssistantThread_u24lambda_u241_u241_u240.setTypeface(Typeface.MONOSPACE);
                $this$renderAssistantThread_u24lambda_u241_u241_u240.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_hud_panel));
                Iterable $this$forEachIndexed$iv2 = $this$forEachIndexed$iv;
                int $i$f$forEachIndexed2 = $i$f$forEachIndexed;
                LinearLayout.LayoutParams lp4 = new LinearLayout.LayoutParams(0, dp2px(64), 1.0f);
                if (index == 0) {
                    lp4.setMarginEnd(dp2px(8));
                }
                $this$renderAssistantThread_u24lambda_u241_u241_u240.setLayoutParams(lp4);
                $this$renderAssistantThread_u24lambda_u241_u241_u240.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda10
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.dispatchAssistantPrompt(label);
                    }
                });
                $this$renderAssistantThread_u24lambda_u241_u240.addView($this$renderAssistantThread_u24lambda_u241_u241_u240);
                index = index$iv;
                $this$forEach$iv3 = $this$forEach$iv4;
                $i$f$forEach3 = $i$f$forEach4;
                $this$forEachIndexed$iv = $this$forEachIndexed$iv2;
                $i$f$forEachIndexed = $i$f$forEachIndexed2;
            }
            $this$renderAssistantThread_u24lambda_u240.addView($this$renderAssistantThread_u24lambda_u241_u240);
            $this$forEach$iv3 = $this$forEach$iv3;
            i = -2;
            i2 = -1;
            i3 = 8;
            i4 = 0;
        }
        container.addView($this$renderAssistantThread_u24lambda_u240);
        try {
            Result.Companion companion = Result.INSTANCE;
            DashboardActivity $this$renderAssistantThread_u24lambda_u242 = this;
            objM392constructorimpl = Result.m392constructorimpl(Integer.valueOf(BrainGraph.INSTANCE.memory($this$renderAssistantThread_u24lambda_u242).count()));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = 0;
        }
        int facts = ((Number) objM392constructorimpl).intValue();
        String router = JarvisSettings.INSTANCE.routerMode(this);
        String model = Intrinsics.areEqual(router, "LOCAL") ? "LOCAL LLM" : JarvisSettings.INSTANCE.cloudModelForProfile(this);
        try {
            Result.Companion companion3 = Result.INSTANCE;
            DashboardActivity $this$renderAssistantThread_u24lambda_u243 = this;
            Object systemService = $this$renderAssistantThread_u24lambda_u243.getSystemService("connectivity");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
            ConnectivityManager cm = (ConnectivityManager) systemService;
            objM392constructorimpl2 = Result.m392constructorimpl(Boolean.valueOf(cm.getActiveNetwork() != null));
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            objM392constructorimpl2 = Result.m392constructorimpl(ResultKt.createFailure(th2));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl2)) {
            objM392constructorimpl2 = false;
        }
        boolean online = ((Boolean) objM392constructorimpl2).booleanValue();
        LinearLayout brief = new LinearLayout(this);
        brief.setOrientation(1);
        brief.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_hud_panel));
        brief.setPadding(dp2px(12), dp2px(12), dp2px(12), dp2px(12));
        LinearLayout.LayoutParams lp5 = new LinearLayout.LayoutParams(-1, -2);
        lp5.topMargin = dp2px(8);
        brief.setLayoutParams(lp5);
        TextView $this$renderAssistantThread_u24lambda_u245 = new TextView(this);
        $this$renderAssistantThread_u24lambda_u245.setText("СТАТУС СЕССИИ");
        $this$renderAssistantThread_u24lambda_u245.setTextColor(ContextCompat.getColor(this, R.color.jarvis_cyan));
        $this$renderAssistantThread_u24lambda_u245.setTextSize(10.0f);
        $this$renderAssistantThread_u24lambda_u245.setTypeface(Typeface.MONOSPACE);
        brief.addView($this$renderAssistantThread_u24lambda_u245);
        TextView $this$renderAssistantThread_u24lambda_u246 = new TextView(this);
        $this$renderAssistantThread_u24lambda_u246.setText("MODEL  " + model + "\nMEMORY  " + facts + " фактов\nNET  " + (online ? "ONLINE" : "OFFLINE") + " · ROUTER  " + router);
        $this$renderAssistantThread_u24lambda_u246.setTextColor(ContextCompat.getColor(this, R.color.jarvis_text));
        $this$renderAssistantThread_u24lambda_u246.setTextSize(12.0f);
        $this$renderAssistantThread_u24lambda_u246.setTypeface(Typeface.MONOSPACE);
        $this$renderAssistantThread_u24lambda_u246.setPadding(0, dp2px(8), 0, dp2px(8));
        brief.addView($this$renderAssistantThread_u24lambda_u246);
        TextView $this$renderAssistantThread_u24lambda_u247 = new TextView(this);
        $this$renderAssistantThread_u24lambda_u247.setText("ЗАПУСТИТЬ ГОЛОСОВОЙ БРИФИНГ");
        $this$renderAssistantThread_u24lambda_u247.setGravity(17);
        $this$renderAssistantThread_u24lambda_u247.setTextColor(ContextCompat.getColor(this, R.color.jarvis_cyan));
        $this$renderAssistantThread_u24lambda_u247.setTextSize(11.0f);
        $this$renderAssistantThread_u24lambda_u247.setTypeface(Typeface.MONOSPACE);
        $this$renderAssistantThread_u24lambda_u247.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_fui_active));
        $this$renderAssistantThread_u24lambda_u247.setPadding(dp2px(10), dp2px(10), dp2px(10), dp2px(10));
        $this$renderAssistantThread_u24lambda_u247.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.dispatchAssistantPrompt("Кратко: статус батареи, сети и что есть в памяти");
            }
        });
        brief.addView($this$renderAssistantThread_u24lambda_u247);
        container.addView(brief);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean renderAssistantThread$lambda$8$0$0(ConversationStore.Bubble $b, DashboardActivity this$0, LinearLayout $container, View it) {
        ConversationStore.INSTANCE.toggleFavorite($b.getId());
        this$0.renderAssistantThread($container);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void renderAssistantThread$lambda$9(DashboardActivity this$0) {
        FrameLayout frameLayout = this$0.screenContainer;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
            frameLayout = null;
        }
        ScrollView scrollView = (ScrollView) frameLayout.findViewById(R.id.assistantThreadScroll);
        if (scrollView != null) {
            scrollView.fullScroll(130);
        }
    }

    private final long sysKhz(String path) {
        Object objM392constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            DashboardActivity dashboardActivity = this;
            objM392constructorimpl = Result.m392constructorimpl(Long.valueOf(Long.parseLong(StringsKt.trim((CharSequence) FilesKt.readText$default(new File(path), null, 1, null)).toString())));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = 0L;
        }
        return ((Number) objM392constructorimpl).longValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float readCpuLoad() {
        DashboardActivity dashboardActivity;
        float acc;
        Object objM392constructorimpl;
        int i = 1;
        int cores = RangesKt.coerceAtLeast(Runtime.getRuntime().availableProcessors(), 1);
        double totalDt = 0.0d;
        double weighted = 0.0d;
        int i2 = 0;
        while (true) {
            int i3 = 0;
            if (i2 >= cores) {
                break;
            }
            try {
                Result.Companion companion = Result.INSTANCE;
                DashboardActivity dashboardActivity2 = this;
                objM392constructorimpl = Result.m392constructorimpl(FilesKt.readLines$default(new File("/sys/devices/system/cpu/cpu" + i2 + "/cpufreq/stats/time_in_state"), null, i, null));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
            }
            List<String> lines = (List) (Result.m398isFailureimpl(objM392constructorimpl) ? null : objM392constructorimpl);
            if (lines != null) {
                HashMap now = new HashMap();
                for (String line : lines) {
                    List<String> listSplit = new Regex("\\s+").split(StringsKt.trim((CharSequence) line).toString(), i3);
                    if (listSplit.size() >= 2) {
                        Long longOrNull = StringsKt.toLongOrNull(listSplit.get(i3));
                        if (longOrNull != null) {
                            long freq = longOrNull.longValue();
                            Long longOrNull2 = StringsKt.toLongOrNull(listSplit.get(i));
                            if (longOrNull2 != null) {
                                now.put(Long.valueOf(freq), Long.valueOf(longOrNull2.longValue()));
                                i = 1;
                                i3 = 0;
                            } else {
                                i = 1;
                                i3 = 0;
                            }
                        } else {
                            i = 1;
                            i3 = 0;
                        }
                    } else {
                        i = 1;
                        i3 = 0;
                    }
                }
                if (!now.isEmpty()) {
                    Map<Long, Long> map = this.cpuTimePrev.get(Integer.valueOf(i2));
                    this.cpuTimePrev.put(Integer.valueOf(i2), now);
                    if (map != null) {
                        Set setKeySet = now.keySet();
                        Intrinsics.checkNotNullExpressionValue(setKeySet, "<get-keys>(...)");
                        Long l = (Long) CollectionsKt.minOrNull((Iterable) setKeySet);
                        if (l != null) {
                            long minF = l.longValue();
                            Set setKeySet2 = now.keySet();
                            Intrinsics.checkNotNullExpressionValue(setKeySet2, "<get-keys>(...)");
                            Long l2 = (Long) CollectionsKt.maxOrNull((Iterable) setKeySet2);
                            if (l2 != null) {
                                long maxF = l2.longValue();
                                List lines2 = lines;
                                long span = RangesKt.coerceAtLeast(maxF - minF, 1L);
                                Iterator it = now.entrySet().iterator();
                                while (it.hasNext()) {
                                    Map.Entry entry = (Map.Entry) it.next();
                                    long freq2 = ((Number) entry.getKey()).longValue();
                                    long ticks = ((Number) entry.getValue()).longValue();
                                    Long l3 = map.get(Long.valueOf(freq2));
                                    it = it;
                                    long dt = ticks - (l3 != null ? l3.longValue() : ticks);
                                    if (dt > 0) {
                                        weighted += ((freq2 - minF) / span) * dt;
                                        totalDt += dt;
                                        lines2 = lines2;
                                        now = now;
                                        minF = minF;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            i2++;
            i = 1;
        }
        if (totalDt > 0.0d) {
            acc = (float) ((weighted / totalDt) * 100.0d);
            dashboardActivity = this;
        } else {
            float acc2 = 0.0f;
            int counted = 0;
            int i4 = 0;
            while (i4 < cores) {
                long cur = sysKhz("/sys/devices/system/cpu/cpu" + i4 + "/cpufreq/scaling_cur_freq");
                Long lValueOf = Long.valueOf(sysKhz("/sys/devices/system/cpu/cpu" + i4 + "/cpufreq/cpuinfo_min_freq"));
                long it2 = lValueOf.longValue();
                if ((it2 > 0 ? 1 : 0) == 0) {
                    lValueOf = null;
                }
                long min = lValueOf != null ? lValueOf.longValue() : sysKhz("/sys/devices/system/cpu/cpu" + i4 + "/cpufreq/scaling_min_freq");
                Long lValueOf2 = Long.valueOf(sysKhz("/sys/devices/system/cpu/cpu" + i4 + "/cpufreq/cpuinfo_max_freq"));
                long it3 = lValueOf2.longValue();
                if ((it3 > 0 ? 1 : 0) == 0) {
                    lValueOf2 = null;
                }
                long max = lValueOf2 != null ? lValueOf2.longValue() : sysKhz("/sys/devices/system/cpu/cpu" + i4 + "/cpufreq/scaling_max_freq");
                if (cur > 0 && max > min && min > 0) {
                    acc2 += RangesKt.coerceIn((cur - min) / (max - min), 0.0f, 1.0f);
                    counted++;
                }
                i4++;
                weighted = weighted;
            }
            dashboardActivity = this;
            if (counted > 0) {
                acc = (acc2 / counted) * 100.0f;
            } else {
                long nowCpu = Process.getElapsedCpuTime();
                long nowWall = SystemClock.elapsedRealtime();
                if (dashboardActivity.cpuPrevWall == 0) {
                    dashboardActivity.cpuPrevMs = nowCpu;
                    dashboardActivity.cpuPrevWall = nowWall;
                    acc = 12.0f;
                } else {
                    long dw = RangesKt.coerceAtLeast(nowWall - dashboardActivity.cpuPrevWall, 1L);
                    float proc = ((RangesKt.coerceAtLeast(nowCpu - dashboardActivity.cpuPrevMs, 0L) * 100.0f) / dw) / cores;
                    dashboardActivity.cpuPrevMs = nowCpu;
                    dashboardActivity.cpuPrevWall = nowWall;
                    acc = proc;
                }
            }
        }
        dashboardActivity.cpuEma = dashboardActivity.cpuEma < 0.0f ? acc : (dashboardActivity.cpuEma * 0.72f) + (0.28f * acc);
        return RangesKt.coerceIn(dashboardActivity.cpuEma, 0.0f, 100.0f);
    }

    private final void setupActivityScreen(final View screen) {
        Object objM392constructorimpl;
        final LinearLayout container = (LinearLayout) screen.findViewById(R.id.activityFeedContainer);
        if (container == null) {
            return;
        }
        TextView textView = (TextView) screen.findViewById(R.id.actChipAll);
        if (textView != null) {
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda108
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupActivityScreen$apply(this.f$0, container, screen, null);
                }
            });
        }
        TextView textView2 = (TextView) screen.findViewById(R.id.actChipVoice);
        if (textView2 != null) {
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda110
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupActivityScreen$lambda$1(this.f$0, container, screen, view);
                }
            });
        }
        TextView textView3 = (TextView) screen.findViewById(R.id.actChipTools);
        if (textView3 != null) {
            textView3.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda111
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupActivityScreen$lambda$2(this.f$0, container, screen, view);
                }
            });
        }
        TextView textView4 = (TextView) screen.findViewById(R.id.actChipConfirm);
        if (textView4 != null) {
            textView4.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda112
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupActivityScreen$lambda$3(this.f$0, container, screen, view);
                }
            });
        }
        TextView textView5 = (TextView) screen.findViewById(R.id.activitySearch);
        if (textView5 != null) {
            textView5.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda113
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupActivityScreen$lambda$4(this.f$0, container, view);
                }
            });
        }
        TextView textView6 = (TextView) screen.findViewById(R.id.btnClearActivity);
        if (textView6 != null) {
            textView6.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda114
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupActivityScreen$lambda$5(this.f$0, container, view);
                }
            });
        }
        setupActivityScreen$apply(this, container, screen, this.activityFilter);
        updateJournalStats();
        TextView textView7 = (TextView) screen.findViewById(R.id.btnOpenTestConsole);
        if (textView7 != null) {
            textView7.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda115
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupActivityScreen$lambda$6(this.f$0, view);
                }
            });
        }
        final GaugeView cpuGauge = (GaugeView) screen.findViewById(R.id.gaugeCpu);
        final GaugeView ramGauge = (GaugeView) screen.findViewById(R.id.gaugeRam);
        final GaugeView batGauge = (GaugeView) screen.findViewById(R.id.gaugeBattery);
        final TelemetryChartView chart = (TelemetryChartView) screen.findViewById(R.id.systemChart);
        if (cpuGauge != null) {
            cpuGauge.setLabel("CPU");
        }
        if (ramGauge != null) {
            ramGauge.setLabel("RAM");
        }
        if (batGauge != null) {
            batGauge.setLabel("BAT");
        }
        Runnable runnable = new Runnable() { // from class: com.jarvis.app.DashboardActivity$setupActivityScreen$sample$1
            @Override // java.lang.Runnable
            public void run() {
                if (screen.isAttachedToWindow()) {
                    float cpu = this.readCpuLoad();
                    Object systemService = this.getSystemService("activity");
                    Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
                    ActivityManager am = (ActivityManager) systemService;
                    ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
                    am.getMemoryInfo(info);
                    float ramPct = info.totalMem == 0 ? 0.0f : ((info.totalMem - info.availMem) * 100.0f) / info.totalMem;
                    long j = 1048576;
                    long usedMb = (info.totalMem - info.availMem) / j;
                    long totalMb = info.totalMem / j;
                    Intent bat = this.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                    int level = bat != null ? bat.getIntExtra("level", 0) : 0;
                    int scale = bat != null ? bat.getIntExtra("scale", 100) : 100;
                    float batPct = scale == 0 ? 0.0f : (level * 100.0f) / scale;
                    float temp = (bat != null ? bat.getIntExtra("temperature", 0) : 0) / 10.0f;
                    float volt = (bat != null ? bat.getIntExtra("voltage", 0) : 0) / 1000.0f;
                    GaugeView gaugeView = cpuGauge;
                    if (gaugeView != null) {
                        gaugeView.setValue(cpu);
                    }
                    GaugeView gaugeView2 = cpuGauge;
                    if (gaugeView2 != null) {
                        gaugeView2.setCaption(((int) cpu) + "%");
                    }
                    GaugeView gaugeView3 = ramGauge;
                    if (gaugeView3 != null) {
                        gaugeView3.setValue(ramPct);
                    }
                    GaugeView gaugeView4 = ramGauge;
                    if (gaugeView4 != null) {
                        gaugeView4.setCaption(usedMb + "/" + totalMb);
                    }
                    GaugeView gaugeView5 = batGauge;
                    if (gaugeView5 != null) {
                        gaugeView5.setValue(batPct);
                    }
                    GaugeView gaugeView6 = batGauge;
                    if (gaugeView6 != null) {
                        gaugeView6.setCaption(((int) temp) + "° " + volt + ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
                    }
                    TelemetryChartView telemetryChartView = chart;
                    if (telemetryChartView != null) {
                        telemetryChartView.push(cpu, ramPct);
                    }
                    run$plaque(screen, this, R.id.svcVoice, "VOICE", VoiceController.INSTANCE.isRunning() ? "RUN" : "IDLE", VoiceController.INSTANCE.isRunning());
                    run$plaque(screen, this, R.id.svcWake, "WAKE", JarvisSettings.INSTANCE.wakeEnabled(this) ? "ARMED" : "PAUSED", JarvisSettings.INSTANCE.wakeEnabled(this));
                    String router = JarvisSettings.INSTANCE.routerMode(this);
                    run$plaque(screen, this, R.id.svcRouter, "ROUTER", router, !Intrinsics.areEqual(router, "LOCAL"));
                    HudText hudText = HudText.INSTANCE;
                    List<Reminder> listAll = this.getReminderBook().all();
                    LocalDateTime localDateTimeNow = LocalDateTime.now();
                    Intrinsics.checkNotNullExpressionValue(localDateTimeNow, "now(...)");
                    String next = hudText.next(listAll, localDateTimeNow);
                    run$plaque(screen, this, R.id.svcNext, "NEXT", next, !StringsKt.startsWith$default(next, "Напоминаний нет", false, 2, (Object) null));
                    run$plaque(screen, this, R.id.svcPipeline, "ASR / TTS", JarvisSettings.INSTANCE.asrMode(this) + " · " + JarvisSettings.INSTANCE.ttsMode(this), true);
                    TextView textView8 = (TextView) screen.findViewById(R.id.latLlm);
                    if (textView8 != null) {
                        textView8.setText(StringsKt.isBlank(this.latestTool) ? "LLM\n—" : "LLM\n" + StringsKt.trim((CharSequence) StringsKt.substringAfterLast$default(this.latestTool, "·", (String) null, 2, (Object) null)).toString());
                    }
                    TelemetryChartView telemetryChartView2 = chart;
                    if (telemetryChartView2 != null) {
                        telemetryChartView2.postDelayed(this, 1000L);
                    }
                }
            }

            private static final void run$plaque(View $screen, DashboardActivity this$0, int id, String title, String value, boolean hot) {
                TextView $this$run_u24plaque_u24lambda_u240 = (TextView) $screen.findViewById(id);
                if ($this$run_u24plaque_u24lambda_u240 != null) {
                    $this$run_u24plaque_u24lambda_u240.setText("● " + title + StringUtils.LF + value);
                    $this$run_u24plaque_u24lambda_u240.setTextColor(ContextCompat.getColor(this$0, hot ? R.color.jarvis_green : R.color.jarvis_muted));
                }
            }
        };
        try {
            Result.Companion companion = Result.INSTANCE;
            DashboardActivity $this$setupActivityScreen_u24lambda_u247 = this;
            Object systemService = $this$setupActivityScreen_u24lambda_u247.getSystemService("activity");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
            ActivityManager am = (ActivityManager) systemService;
            ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
            am.getMemoryInfo(info);
            objM392constructorimpl = Result.m392constructorimpl(Float.valueOf(info.totalMem == 0 ? 40.0f : ((info.totalMem - info.availMem) * 100.0f) / info.totalMem));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        Float fValueOf = Float.valueOf(40.0f);
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = fValueOf;
        }
        float seedRam = ((Number) objM392constructorimpl).floatValue();
        if (chart != null) {
            chart.seed(14.0f, seedRam);
        }
        if (chart != null) {
            chart.post(runnable);
        }
    }

    private static final void setupActivityScreen$style(View $screen, DashboardActivity this$0, int id, boolean on) {
        TextView textView = (TextView) $screen.findViewById(id);
        if (textView != null) {
            textView.setBackground(ContextCompat.getDrawable(this$0, on ? R.drawable.bg_fui_active : R.drawable.bg_hud_panel));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupActivityScreen$apply(DashboardActivity this$0, LinearLayout container, View $screen, ActivityFeed.Item.Kind kind) {
        this$0.activityFilter = kind;
        setupActivityScreen$style($screen, this$0, R.id.actChipAll, kind == null);
        setupActivityScreen$style($screen, this$0, R.id.actChipVoice, kind == ActivityFeed.Item.Kind.VOICE);
        setupActivityScreen$style($screen, this$0, R.id.actChipTools, kind == ActivityFeed.Item.Kind.TOOL);
        setupActivityScreen$style($screen, this$0, R.id.actChipConfirm, kind == ActivityFeed.Item.Kind.CONFIRM);
        this$0.renderActivityFeed(container);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupActivityScreen$lambda$1(DashboardActivity this$0, LinearLayout $container, View $screen, View it) {
        setupActivityScreen$apply(this$0, $container, $screen, ActivityFeed.Item.Kind.VOICE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupActivityScreen$lambda$2(DashboardActivity this$0, LinearLayout $container, View $screen, View it) {
        setupActivityScreen$apply(this$0, $container, $screen, ActivityFeed.Item.Kind.TOOL);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupActivityScreen$lambda$3(DashboardActivity this$0, LinearLayout $container, View $screen, View it) {
        setupActivityScreen$apply(this$0, $container, $screen, ActivityFeed.Item.Kind.CONFIRM);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupActivityScreen$lambda$4(final DashboardActivity this$0, final LinearLayout $container, View it) {
        final EditText $this$setupActivityScreen_u24lambda_u244_u240 = new EditText(this$0);
        $this$setupActivityScreen_u24lambda_u244_u240.setHint("Поиск…");
        $this$setupActivityScreen_u24lambda_u244_u240.setText(this$0.activitySearchQuery);
        new AlertDialog.Builder(this$0).setTitle("Найти в активности").setView($this$setupActivityScreen_u24lambda_u244_u240).setNegativeButton("Сброс", new DialogInterface.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda88
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DashboardActivity.setupActivityScreen$lambda$4$1(this.f$0, $container, dialogInterface, i);
            }
        }).setPositiveButton("Найти", new DialogInterface.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda89
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DashboardActivity.setupActivityScreen$lambda$4$2(this.f$0, $this$setupActivityScreen_u24lambda_u244_u240, $container, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupActivityScreen$lambda$4$1(DashboardActivity this$0, LinearLayout $container, DialogInterface dialogInterface, int i) {
        this$0.activitySearchQuery = "";
        this$0.renderActivityFeed($container);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupActivityScreen$lambda$4$2(DashboardActivity this$0, EditText $input, LinearLayout $container, DialogInterface dialogInterface, int i) {
        this$0.activitySearchQuery = StringsKt.trim((CharSequence) $input.getText().toString()).toString();
        this$0.renderActivityFeed($container);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupActivityScreen$lambda$5(DashboardActivity this$0, LinearLayout $container, View it) {
        ActivityFeed.INSTANCE.clear();
        this$0.renderActivityFeed($container);
        this$0.updateJournalStats();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupActivityScreen$lambda$6(DashboardActivity this$0, View it) {
        this$0.startActivity(new Intent(this$0, (Class<?>) TestConsoleActivity.class));
    }

    private final void updateJournalStats() {
        int count$iv;
        List<ActivityFeed.Item> listAll = ActivityFeed.INSTANCE.all();
        FrameLayout frameLayout = this.screenContainer;
        FrameLayout frameLayout2 = null;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
            frameLayout = null;
        }
        TextView textView = (TextView) frameLayout.findViewById(R.id.journalRequests);
        if (textView != null) {
            textView.setText(String.valueOf(listAll.size()));
        }
        FrameLayout frameLayout3 = this.screenContainer;
        if (frameLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
        } else {
            frameLayout2 = frameLayout3;
        }
        TextView textView2 = (TextView) frameLayout2.findViewById(R.id.journalFailed);
        if (textView2 != null) {
            List<ActivityFeed.Item> $this$count$iv = listAll;
            if (($this$count$iv instanceof Collection) && $this$count$iv.isEmpty()) {
                count$iv = 0;
            } else {
                count$iv = 0;
                for (Object element$iv : $this$count$iv) {
                    ActivityFeed.Item it = (ActivityFeed.Item) element$iv;
                    if (it.getFailed() && (count$iv = count$iv + 1) < 0) {
                        CollectionsKt.throwCountOverflow();
                    }
                }
            }
            textView2.setText(String.valueOf(count$iv));
        }
    }

    private final void renderActivityFeed(final LinearLayout container) {
        String badge;
        int i;
        int $i$f$forEach;
        Iterator it;
        container.removeAllViews();
        List<ActivityFeed.Item> listFilter = ActivityFeed.INSTANCE.filter(this.activityFilter);
        int i2 = 1;
        if (!StringsKt.isBlank(this.activitySearchQuery)) {
            List<ActivityFeed.Item> $this$filter$iv = listFilter;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                ActivityFeed.Item it2 = (ActivityFeed.Item) element$iv$iv;
                if (StringsKt.contains((CharSequence) it2.getTitle(), (CharSequence) this.activitySearchQuery, true) || StringsKt.contains((CharSequence) it2.getDetail(), (CharSequence) this.activitySearchQuery, true)) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            listFilter = (List) destination$iv$iv;
        }
        if (listFilter.isEmpty()) {
            TextView $this$renderActivityFeed_u24lambda_u241 = new TextView(this);
            $this$renderActivityFeed_u24lambda_u241.setText("Событий пока нет. Активируй JARVIS и выполни команду — лента заполнится сама.");
            $this$renderActivityFeed_u24lambda_u241.setTextColor(ContextCompat.getColor(this, R.color.jarvis_muted));
            $this$renderActivityFeed_u24lambda_u241.setTextSize(12.0f);
            container.addView($this$renderActivityFeed_u24lambda_u241);
            return;
        }
        Iterable $this$forEach$iv = listFilter;
        int $i$f$forEach2 = 0;
        Iterator it3 = $this$forEach$iv.iterator();
        while (it3.hasNext()) {
            Object element$iv = it3.next();
            final ActivityFeed.Item item = (ActivityFeed.Item) element$iv;
            LinearLayout $this$renderActivityFeed_u24lambda_u242_u240 = new LinearLayout(this);
            $this$renderActivityFeed_u24lambda_u242_u240.setOrientation(i2);
            $this$renderActivityFeed_u24lambda_u242_u240.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_hud_panel));
            List<ActivityFeed.Item> list = listFilter;
            $this$renderActivityFeed_u24lambda_u242_u240.setPadding(dp2px(12), dp2px(12), dp2px(12), dp2px(12));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2);
            lp.bottomMargin = dp2px(8);
            $this$renderActivityFeed_u24lambda_u242_u240.setLayoutParams(lp);
            LinearLayout head = new LinearLayout(this);
            head.setOrientation(0);
            head.setGravity(16);
            TextView $this$renderActivityFeed_u24lambda_u242_u242 = new TextView(this);
            Iterable $this$forEach$iv2 = $this$forEach$iv;
            $this$renderActivityFeed_u24lambda_u242_u242.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
            $this$renderActivityFeed_u24lambda_u242_u242.setText(ActivityFeed.INSTANCE.formatTime(item.getAtMs()) + " · " + item.getKind().name());
            $this$renderActivityFeed_u24lambda_u242_u242.setTextColor(ContextCompat.getColor(this, R.color.jarvis_faint));
            $this$renderActivityFeed_u24lambda_u242_u242.setTextSize(9.0f);
            $this$renderActivityFeed_u24lambda_u242_u242.setTypeface(Typeface.MONOSPACE);
            head.addView($this$renderActivityFeed_u24lambda_u242_u242);
            if (item.getPendingConfirm()) {
                badge = "WAITING";
            } else {
                badge = item.getFailed() ? "FAILED" : "OK";
            }
            TextView $this$renderActivityFeed_u24lambda_u242_u243 = new TextView(this);
            $this$renderActivityFeed_u24lambda_u242_u243.setText(badge);
            DashboardActivity dashboardActivity = this;
            if (Intrinsics.areEqual(badge, "WAITING")) {
                i = R.color.jarvis_primary;
            } else {
                i = Intrinsics.areEqual(badge, "FAILED") ? R.color.jarvis_danger : R.color.jarvis_green;
            }
            $this$renderActivityFeed_u24lambda_u242_u243.setTextColor(ContextCompat.getColor(dashboardActivity, i));
            $this$renderActivityFeed_u24lambda_u242_u243.setTextSize(9.0f);
            $this$renderActivityFeed_u24lambda_u242_u243.setTypeface(Typeface.MONOSPACE);
            head.addView($this$renderActivityFeed_u24lambda_u242_u243);
            $this$renderActivityFeed_u24lambda_u242_u240.addView(head);
            TextView $this$renderActivityFeed_u24lambda_u242_u244 = new TextView(this);
            $this$renderActivityFeed_u24lambda_u242_u244.setText(item.getTitle());
            $this$renderActivityFeed_u24lambda_u242_u244.setTextColor(ContextCompat.getColor(this, R.color.jarvis_text));
            $this$renderActivityFeed_u24lambda_u242_u244.setTextSize(13.0f);
            $this$renderActivityFeed_u24lambda_u242_u244.setPadding(0, dp2px(6), 0, 0);
            $this$renderActivityFeed_u24lambda_u242_u240.addView($this$renderActivityFeed_u24lambda_u242_u244);
            TextView $this$renderActivityFeed_u24lambda_u242_u245 = new TextView(this);
            $this$renderActivityFeed_u24lambda_u242_u245.setText(item.getDetail());
            $this$renderActivityFeed_u24lambda_u242_u245.setTextColor(ContextCompat.getColor(this, R.color.jarvis_muted));
            $this$renderActivityFeed_u24lambda_u242_u245.setTextSize(10.0f);
            $this$renderActivityFeed_u24lambda_u242_u245.setPadding(0, dp2px(4), 0, 0);
            $this$renderActivityFeed_u24lambda_u242_u240.addView($this$renderActivityFeed_u24lambda_u242_u245);
            if (item.getPendingConfirm()) {
                LinearLayout actions = new LinearLayout(this);
                actions.setOrientation(0);
                actions.setPadding(0, dp2px(8), 0, 0);
                TextView $this$renderActivityFeed_u24lambda_u242_u247 = new TextView(this);
                $this$renderActivityFeed_u24lambda_u242_u247.setText("ПОДТВЕРДИТЬ");
                $this$renderActivityFeed_u24lambda_u242_u247.setTextColor(ContextCompat.getColor(this, R.color.jarvis_cyan));
                $this$renderActivityFeed_u24lambda_u242_u247.setTextSize(12.0f);
                int iDp2px = dp2px(12);
                $i$f$forEach = $i$f$forEach2;
                int $i$f$forEach3 = dp2px(8);
                it = it3;
                $this$renderActivityFeed_u24lambda_u242_u247.setPadding(iDp2px, $i$f$forEach3, dp2px(12), dp2px(8));
                $this$renderActivityFeed_u24lambda_u242_u247.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_fui_active));
                $this$renderActivityFeed_u24lambda_u242_u247.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda20
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        DashboardActivity.renderActivityFeed$lambda$2$7$0(item, this, container, view);
                    }
                });
                actions.addView($this$renderActivityFeed_u24lambda_u242_u247);
                TextView $this$renderActivityFeed_u24lambda_u242_u248 = new TextView(this);
                $this$renderActivityFeed_u24lambda_u242_u248.setText("ОТМЕНА");
                $this$renderActivityFeed_u24lambda_u242_u248.setTextColor(ContextCompat.getColor(this, R.color.jarvis_danger));
                $this$renderActivityFeed_u24lambda_u242_u248.setTextSize(12.0f);
                $this$renderActivityFeed_u24lambda_u242_u248.setPadding(dp2px(12), dp2px(8), dp2px(12), dp2px(8));
                $this$renderActivityFeed_u24lambda_u242_u248.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_hud_panel));
                LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(-2, -2);
                lp2.setMarginStart(dp2px(8));
                $this$renderActivityFeed_u24lambda_u242_u248.setLayoutParams(lp2);
                $this$renderActivityFeed_u24lambda_u242_u248.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda21
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        DashboardActivity.renderActivityFeed$lambda$2$8$0(item, this, container, view);
                    }
                });
                actions.addView($this$renderActivityFeed_u24lambda_u242_u248);
                $this$renderActivityFeed_u24lambda_u242_u240.addView(actions);
            } else {
                $i$f$forEach = $i$f$forEach2;
                it = it3;
                if (item.getFailed()) {
                    TextView $this$renderActivityFeed_u24lambda_u242_u249 = new TextView(this);
                    $this$renderActivityFeed_u24lambda_u242_u249.setText("Повторить запрос");
                    $this$renderActivityFeed_u24lambda_u242_u249.setTextColor(ContextCompat.getColor(this, R.color.jarvis_text2));
                    $this$renderActivityFeed_u24lambda_u242_u249.setTextSize(11.0f);
                    $this$renderActivityFeed_u24lambda_u242_u249.setPadding(dp2px(10), dp2px(8), dp2px(10), dp2px(8));
                    $this$renderActivityFeed_u24lambda_u242_u249.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_btn_ghost_3));
                    LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(-2, -2);
                    lp3.topMargin = dp2px(8);
                    $this$renderActivityFeed_u24lambda_u242_u249.setLayoutParams(lp3);
                    $this$renderActivityFeed_u24lambda_u242_u249.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda23
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            DashboardActivity.renderActivityFeed$lambda$2$9$0(this.f$0, view);
                        }
                    });
                    $this$renderActivityFeed_u24lambda_u242_u240.addView($this$renderActivityFeed_u24lambda_u242_u249);
                }
            }
            container.addView($this$renderActivityFeed_u24lambda_u242_u240);
            listFilter = list;
            $this$forEach$iv = $this$forEach$iv2;
            $i$f$forEach2 = $i$f$forEach;
            it3 = it;
            i2 = 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void renderActivityFeed$lambda$2$7$0(ActivityFeed.Item $item, DashboardActivity this$0, LinearLayout $container, View it) {
        ActivityFeed.INSTANCE.markResolved($item.getId(), true);
        this$0.renderActivityFeed($container);
        Toast.makeText(this$0, "Подтверждено в UI. Исполнение идёт через голосовой pipeline.", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void renderActivityFeed$lambda$2$8$0(ActivityFeed.Item $item, DashboardActivity this$0, LinearLayout $container, View it) {
        ActivityFeed.INSTANCE.markResolved($item.getId(), false);
        this$0.renderActivityFeed($container);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void renderActivityFeed$lambda$2$9$0(DashboardActivity this$0, View it) {
        if (!StringsKt.isBlank(this$0.latestRequest)) {
            if (!VoiceController.INSTANCE.isRunning()) {
                activateAssistant$default(this$0, false, 1, null);
            }
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$0), null, null, new DashboardActivity$renderActivityFeed$3$7$1$1(this$0, null), 3, null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void setupControlCenter(final View screen) {
        View viewFindViewById = screen.findViewById(R.id.tileMemory);
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda119
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) throws Throwable {
                    this.f$0.openControlSecondary(JarvisActivity.MEMORY);
                }
            });
        }
        View viewFindViewById2 = screen.findViewById(R.id.tileCommands);
        if (viewFindViewById2 != null) {
            viewFindViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda126
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) throws Throwable {
                    this.f$0.openControlSecondary("commands");
                }
            });
        }
        View viewFindViewById3 = screen.findViewById(R.id.tileAutomations);
        if (viewFindViewById3 != null) {
            viewFindViewById3.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda127
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) throws Throwable {
                    this.f$0.openControlSecondary("automations");
                }
            });
        }
        View viewFindViewById4 = screen.findViewById(R.id.tileCompanion);
        if (viewFindViewById4 != null) {
            viewFindViewById4.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda128
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) throws Throwable {
                    this.f$0.openControlSecondary("companion");
                }
            });
        }
        View viewFindViewById5 = screen.findViewById(R.id.tileModels);
        if (viewFindViewById5 != null) {
            viewFindViewById5.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda129
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) throws Throwable {
                    this.f$0.openControlSecondary("models");
                }
            });
        }
        View viewFindViewById6 = screen.findViewById(R.id.tileDeveloper);
        if (viewFindViewById6 != null) {
            viewFindViewById6.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda130
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupControlCenter$lambda$5(this.f$0, view);
                }
            });
        }
        TextView textView = (TextView) screen.findViewById(R.id.btnOpenAdvancedSettings);
        if (textView != null) {
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda132
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) throws Throwable {
                    this.f$0.openControlSecondary("advanced");
                }
            });
        }
        TextView textView2 = (TextView) screen.findViewById(R.id.btnOpenUsageGuide);
        if (textView2 != null) {
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda133
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupControlCenter$lambda$7(this.f$0, view);
                }
            });
        }
        TextView textView3 = (TextView) screen.findViewById(R.id.btnSwitchModern);
        if (textView3 != null) {
            textView3.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda134
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupControlCenter$lambda$8(this.f$0, view);
                }
            });
        }
        JarvisToggle $this$setupControlCenter_u24lambda_u249 = (JarvisToggle) screen.findViewById(R.id.toggleAdaptive);
        if ($this$setupControlCenter_u24lambda_u249 != null) {
            $this$setupControlCenter_u24lambda_u249.setChecked(JarvisSettings.INSTANCE.adaptiveListening(this));
            $this$setupControlCenter_u24lambda_u249.setOnCheckedChanged(new Function1() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda135
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DashboardActivity.setupControlCenter$lambda$9$0(this.f$0, ((Boolean) obj).booleanValue());
                }
            });
        }
        JarvisToggle $this$setupControlCenter_u24lambda_u2410 = (JarvisToggle) screen.findViewById(R.id.toggleLowPower);
        if ($this$setupControlCenter_u24lambda_u2410 != null) {
            $this$setupControlCenter_u24lambda_u2410.setChecked(JarvisSettings.INSTANCE.lowPowerMode(this));
            $this$setupControlCenter_u24lambda_u2410.setOnCheckedChanged(new Function1() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda121
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DashboardActivity.setupControlCenter$lambda$10$0(this.f$0, ((Boolean) obj).booleanValue());
                }
            });
        }
        JarvisToggle $this$setupControlCenter_u24lambda_u2411 = (JarvisToggle) screen.findViewById(R.id.toggleSensitive);
        if ($this$setupControlCenter_u24lambda_u2411 != null) {
            $this$setupControlCenter_u24lambda_u2411.setChecked(JarvisSettings.INSTANCE.confirmSensitive(this));
            $this$setupControlCenter_u24lambda_u2411.setOnCheckedChanged(new Function1() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda122
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DashboardActivity.setupControlCenter$lambda$11$0(this.f$0, ((Boolean) obj).booleanValue());
                }
            });
        }
        TextView textView4 = (TextView) screen.findViewById(R.id.statWake);
        if (textView4 != null) {
            textView4.setText(JarvisSettings.INSTANCE.wakeEnabled(this) ? "READY" : "OFF");
        }
        TextView textView5 = (TextView) screen.findViewById(R.id.statAsr);
        if (textView5 != null) {
            textView5.setText(JarvisSettings.INSTANCE.asrMode(this));
        }
        TextView textView6 = (TextView) screen.findViewById(R.id.statTts);
        if (textView6 != null) {
            textView6.setText(JarvisSettings.INSTANCE.ttsMode(this));
        }
        final List personaChips = CollectionsKt.listOf((Object[]) new TextView[]{screen.findViewById(R.id.personaClassic), screen.findViewById(R.id.personaFriendly), screen.findViewById(R.id.personaMinimal)});
        List personaNames = CollectionsKt.listOf((Object[]) new String[]{"CLASSIC", "FRIENDLY", "MINIMAL"});
        final TextView personaLabel = (TextView) screen.findViewById(R.id.personaCurrentLabel);
        setupControlCenter$renderPersona(this, personaLabel, personaChips, this, personaNames);
        List $this$forEachIndexed$iv = personaChips;
        int index$iv = 0;
        for (Object item$iv : $this$forEachIndexed$iv) {
            int index$iv2 = index$iv + 1;
            if (index$iv < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TextView chip = (TextView) item$iv;
            final int i = index$iv;
            if (chip != null) {
                final List list = personaNames;
                chip.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda123
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        DashboardActivity.setupControlCenter$lambda$14$0(this.f$0, list, i, this, personaLabel, personaChips, view);
                    }
                });
            }
            index$iv = index$iv2;
            personaLabel = personaLabel;
            personaNames = personaNames;
        }
        final List chatProfiles = CollectionsKt.listOf((Object[]) new String[]{"FAST", "BALANCED", "QUALITY"});
        final List chatChips = CollectionsKt.listOf((Object[]) new TextView[]{screen.findViewById(R.id.chatProfileFast), screen.findViewById(R.id.chatProfileBalanced), screen.findViewById(R.id.chatProfileQuality)});
        TextView chatHint = (TextView) screen.findViewById(R.id.chatProfileHint);
        setupControlCenter$renderChat(this, chatHint, chatChips, this, chatProfiles);
        List $this$forEachIndexed$iv2 = chatChips;
        int index$iv3 = 0;
        for (Object item$iv2 : $this$forEachIndexed$iv2) {
            int index$iv4 = index$iv3 + 1;
            if (index$iv3 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TextView chip2 = (TextView) item$iv2;
            final int i2 = index$iv3;
            if (chip2 != null) {
                final TextView chip3 = chatHint;
                chip2.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda124
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) throws Throwable {
                        DashboardActivity.setupControlCenter$lambda$16$0(this.f$0, chatProfiles, i2, this, chip3, chatChips, view);
                    }
                });
            }
            index$iv3 = index$iv4;
            chatHint = chatHint;
        }
        final List themeChips = CollectionsKt.listOf((Object[]) new TextView[]{screen.findViewById(R.id.themeClassic), screen.findViewById(R.id.themeStealth), screen.findViewById(R.id.themeArc), screen.findViewById(R.id.themeLight)});
        final List themeNames = CollectionsKt.listOf((Object[]) new String[]{"CLASSIC", "STEALTH", "ARC", "LIGHT"});
        setupControlCenter$renderTheme(this, themeChips, screen, this, themeNames);
        List $this$forEachIndexed$iv3 = themeChips;
        int index$iv5 = 0;
        for (Object item$iv3 : $this$forEachIndexed$iv3) {
            int index$iv6 = index$iv5 + 1;
            if (index$iv5 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TextView chip4 = (TextView) item$iv3;
            final int i3 = index$iv5;
            if (chip4 != null) {
                chip4.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda125
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        DashboardActivity.setupControlCenter$lambda$18$0(this.f$0, themeNames, i3, this, themeChips, screen, view);
                    }
                });
            }
            index$iv5 = index$iv6;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupControlCenter$lambda$5(DashboardActivity this$0, View it) {
        this$0.startActivity(new Intent(this$0, (Class<?>) TestConsoleActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupControlCenter$lambda$7(DashboardActivity this$0, View it) {
        this$0.startActivity(new Intent(this$0, (Class<?>) UsageGuideActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupControlCenter$lambda$8(DashboardActivity this$0, View it) {
        UiMode.INSTANCE.switchTo(this$0, UiStyle.MODERN);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setupControlCenter$lambda$9$0(DashboardActivity $ctx, boolean it) {
        JarvisSettings.INSTANCE.prefs($ctx).edit().putBoolean("adaptive_listening", it).apply();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setupControlCenter$lambda$10$0(DashboardActivity $ctx, boolean it) {
        JarvisSettings.INSTANCE.setLowPowerMode($ctx, it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setupControlCenter$lambda$11$0(DashboardActivity $ctx, boolean it) {
        JarvisSettings.INSTANCE.prefs($ctx).edit().putBoolean("confirm_sensitive", it).apply();
        return Unit.INSTANCE;
    }

    private static final void setupControlCenter$renderPersona(DashboardActivity ctx, TextView personaLabel, List<? extends TextView> list, DashboardActivity this$0, List<String> list2) {
        String cur = JarvisSettings.INSTANCE.persona(ctx);
        if (personaLabel != null) {
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
            String lowerCase = cur.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (lowerCase.length() > 0) {
                StringBuilder sb = new StringBuilder();
                char it = lowerCase.charAt(0);
                Locale locale2 = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale2, "getDefault(...)");
                StringBuilder sbAppend = sb.append((Object) CharsKt.titlecase(it, locale2));
                String strSubstring = lowerCase.substring(1);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                lowerCase = sbAppend.append(strSubstring).toString();
            }
            personaLabel.setText("JARVIS " + lowerCase);
        }
        List<? extends TextView> $this$forEachIndexed$iv = list;
        int i = 0;
        for (Object item$iv : $this$forEachIndexed$iv) {
            int index$iv = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TextView chip = (TextView) item$iv;
            if (chip != null) {
                chip.setBackground(ContextCompat.getDrawable(this$0, Intrinsics.areEqual(list2.get(i), cur) ? R.drawable.bg_fui_active : R.drawable.bg_hud_panel));
            }
            i = index$iv;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupControlCenter$lambda$14$0(DashboardActivity $ctx, List $personaNames, int $i, DashboardActivity this$0, TextView $personaLabel, List $personaChips, View it) {
        JarvisSettings.INSTANCE.setPersona($ctx, (String) $personaNames.get($i));
        setupControlCenter$renderPersona($ctx, $personaLabel, $personaChips, this$0, $personaNames);
        BrainGraph.INSTANCE.invalidateChatLlm();
        ActivityFeed.push$default(ActivityFeed.INSTANCE, "Персона", (String) $personaNames.get($i), ActivityFeed.Item.Kind.SYSTEM, false, false, 24, null);
        Toast.makeText(this$0, "Характер: " + $personaNames.get($i) + ". Новый стиль применится к следующим ответам.", 0).show();
    }

    private static final String setupControlCenter$chatHintText(String p) {
        if (Intrinsics.areEqual(p, "FAST")) {
            return "Быстро · ~2–4 с";
        }
        return Intrinsics.areEqual(p, "QUALITY") ? "Качество · ~4–7 с" : "Баланс · 3–5 с";
    }

    private static final void setupControlCenter$renderChat(DashboardActivity ctx, TextView chatHint, List<? extends TextView> list, DashboardActivity this$0, List<String> list2) {
        String cur = JarvisSettings.INSTANCE.chatProfile(ctx);
        if (chatHint != null) {
            chatHint.setText(setupControlCenter$chatHintText(cur));
        }
        List<? extends TextView> $this$forEachIndexed$iv = list;
        int i = 0;
        for (Object item$iv : $this$forEachIndexed$iv) {
            int index$iv = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TextView chip = (TextView) item$iv;
            if (chip != null) {
                chip.setBackground(ContextCompat.getDrawable(this$0, Intrinsics.areEqual(list2.get(i), cur) ? R.drawable.bg_fui_active : R.drawable.bg_hud_panel));
            }
            i = index$iv;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupControlCenter$lambda$16$0(DashboardActivity $ctx, List $chatProfiles, int $i, DashboardActivity this$0, TextView $chatHint, List $chatChips, View it) throws Throwable {
        JarvisSettings.INSTANCE.setChatProfile($ctx, (String) $chatProfiles.get($i));
        setupControlCenter$renderChat($ctx, $chatHint, $chatChips, this$0, $chatProfiles);
        BrainGraph.INSTANCE.invalidateChatLlm();
        BrainGraph.INSTANCE.warmChatLlm(this$0);
    }

    private static final void setupControlCenter$renderTheme(DashboardActivity ctx, List<? extends TextView> list, View $screen, DashboardActivity this$0, List<String> list2) {
        String cur = JarvisSettings.INSTANCE.theme(ctx);
        List<? extends TextView> $this$forEachIndexed$iv = list;
        int i = 0;
        for (Object item$iv : $this$forEachIndexed$iv) {
            int index$iv = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TextView chip = (TextView) item$iv;
            if (chip != null) {
                chip.setBackground(ContextCompat.getDrawable(this$0, Intrinsics.areEqual(list2.get(i), cur) ? R.drawable.bg_fui_active : R.drawable.bg_hud_panel));
            }
            i = index$iv;
        }
        TextView textView = (TextView) $screen.findViewById(R.id.themeTag);
        if (textView != null) {
            textView.setText("Активна: " + cur + " · применяется ко всем поверхностям");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupControlCenter$lambda$18$0(DashboardActivity $ctx, List $themeNames, int $i, DashboardActivity this$0, List $themeChips, View $screen, View it) {
        JarvisSettings.INSTANCE.setTheme($ctx, (String) $themeNames.get($i));
        ActivityFeed.push$default(ActivityFeed.INSTANCE, "Тема", (String) $themeNames.get($i), ActivityFeed.Item.Kind.SYSTEM, false, false, 24, null);
        ThemeApplier.INSTANCE.applyToActivity(this$0);
        ThemeApplier themeApplier = ThemeApplier.INSTANCE;
        FrameLayout frameLayout = this$0.screenContainer;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
            frameLayout = null;
        }
        ThemeApplier.applyToScreen$default(themeApplier, frameLayout, null, 2, null);
        this$0.buildBottomNavigation();
        setupControlCenter$renderTheme($ctx, $themeChips, $screen, this$0, $themeNames);
        ThemeApplier.INSTANCE.recreateWithTheme(this$0);
    }

    private final void setupAutomationsScreen(View screen) throws Throwable {
        TextView textView = (TextView) screen.findViewById(R.id.btnBackToControl);
        if (textView != null) {
            textView.setVisibility(8);
        }
        final LinearLayout container = (LinearLayout) screen.findViewById(R.id.routinesContainer);
        if (container == null) {
            return;
        }
        final TextView preview = (TextView) screen.findViewById(R.id.routinePreview);
        TextView textView2 = (TextView) screen.findViewById(R.id.btnCreateRoutine);
        if (textView2 != null) {
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda116
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupAutomationsScreen$lambda$4(this.f$0, preview, container, view);
                }
            });
        }
        setupAutomationsScreen$ensureSeed(this);
        setupAutomationsScreen$render(container, this, preview);
    }

    private static final void setupAutomationsScreen$ensureSeed(DashboardActivity this$0) throws Throwable {
        BrainGraph.INSTANCE.build(this$0);
        RoutineStore store = BrainGraph.INSTANCE.routineStore();
        if (store == null) {
            return;
        }
        if (store.size() == 0) {
            Iterable iterableLoad = RoutinePersistence.INSTANCE.load(this$0);
            if (!((Collection) iterableLoad).isEmpty()) {
                Iterable $this$forEach$iv = iterableLoad;
                for (Object element$iv : $this$forEach$iv) {
                    SavedRoutine it = (SavedRoutine) element$iv;
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        store.save(it);
                        Result.m392constructorimpl(Unit.INSTANCE);
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        Result.m392constructorimpl(ResultKt.createFailure(th));
                    }
                }
            } else if (!RoutinePersistence.INSTANCE.seeded(this$0)) {
                Iterable $this$forEach$iv2 = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to("focus", TuplesKt.to("Протокол Фокус", "Режим не беспокоить и рабочие инструменты")), TuplesKt.to("brief", TuplesKt.to("Утренний брифинг", "Календарь, заряд и задачи из памяти")), TuplesKt.to("power", TuplesKt.to("Энергосбережение ИИ", "Ниже 20% перейти на лёгкую модель")), TuplesKt.to("road", TuplesKt.to("В дороге", "Hands-free и повышенный VAD"))});
                for (Object element$iv2 : $this$forEach$iv2) {
                    Pair pair = (Pair) element$iv2;
                    String id = (String) pair.component1();
                    Pair pair2 = (Pair) pair.component2();
                    try {
                        Result.Companion companion3 = Result.INSTANCE;
                        store.save(RoutinePersistence.INSTANCE.stub("routine-" + id, (String) pair2.getFirst(), (String) pair2.getSecond(), RoutineStatus.SAVED));
                        Result.m392constructorimpl(Unit.INSTANCE);
                    } catch (Throwable th2) {
                        Result.Companion companion4 = Result.INSTANCE;
                        Result.m392constructorimpl(ResultKt.createFailure(th2));
                    }
                }
                RoutinePersistence.INSTANCE.markSeeded(this$0);
            }
        }
        RoutinePersistence.INSTANCE.save(this$0, store.all());
    }

    private static final void setupAutomationsScreen$render(final LinearLayout container, final DashboardActivity this$0, final TextView preview) throws Throwable {
        final DashboardActivity dashboardActivity = this$0;
        container.removeAllViews();
        BrainGraph.INSTANCE.build(dashboardActivity);
        RoutineStore store = BrainGraph.INSTANCE.routineStore();
        List<SavedRoutine> listAll = store != null ? store.all() : null;
        if (listAll == null) {
            listAll = CollectionsKt.emptyList();
        }
        List<SavedRoutine> list = listAll;
        if (list.isEmpty()) {
            TextView $this$setupAutomationsScreen_u24render_u24lambda_u242 = new TextView(dashboardActivity);
            $this$setupAutomationsScreen_u24render_u24lambda_u242.setText("Протоколов пока нет.");
            $this$setupAutomationsScreen_u24render_u24lambda_u242.setTextColor(ContextCompat.getColor(dashboardActivity, R.color.jarvis_muted));
            $this$setupAutomationsScreen_u24render_u24lambda_u242.setTextSize(12.0f);
            container.addView($this$setupAutomationsScreen_u24render_u24lambda_u242);
            if (preview != null) {
                preview.setText("TRIGGER → ACTION → CONFIRM");
                return;
            }
            return;
        }
        List<SavedRoutine> $this$forEach$iv = list;
        for (Object element$iv : $this$forEach$iv) {
            final SavedRoutine routine = (SavedRoutine) element$iv;
            LinearLayout $this$setupAutomationsScreen_u24render_u24lambda_u243_u240 = new LinearLayout(dashboardActivity);
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u240.setOrientation(0);
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u240.setGravity(16);
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u240.setBackground(ContextCompat.getDrawable(dashboardActivity, R.drawable.bg_hud_panel));
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u240.setPadding(dashboardActivity.dp2px(12), dashboardActivity.dp2px(12), dashboardActivity.dp2px(12), dashboardActivity.dp2px(12));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2);
            lp.bottomMargin = dashboardActivity.dp2px(8);
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u240.setLayoutParams(lp);
            LinearLayout $this$setupAutomationsScreen_u24render_u24lambda_u243_u241 = new LinearLayout(dashboardActivity);
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u241.setOrientation(1);
            List<SavedRoutine> list2 = list;
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u241.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
            TextView $this$setupAutomationsScreen_u24render_u24lambda_u243_u242 = new TextView(dashboardActivity);
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u242.setText(routine.getTitle());
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u242.setTextColor(ContextCompat.getColor(dashboardActivity, R.color.jarvis_text));
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u242.setTextSize(13.0f);
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u242.setTypeface($this$setupAutomationsScreen_u24render_u24lambda_u243_u242.getTypeface(), 1);
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u241.addView($this$setupAutomationsScreen_u24render_u24lambda_u243_u242);
            TextView $this$setupAutomationsScreen_u24render_u24lambda_u243_u243 = new TextView(dashboardActivity);
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u243.setText(StringsKt.take(routine.getNaturalLanguage(), 80));
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u243.setTextColor(ContextCompat.getColor(dashboardActivity, R.color.jarvis_muted));
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u243.setTextSize(10.0f);
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u243.setPadding(0, dashboardActivity.dp2px(3), 0, 0);
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u241.addView($this$setupAutomationsScreen_u24render_u24lambda_u243_u243);
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u240.addView($this$setupAutomationsScreen_u24render_u24lambda_u243_u241);
            TextView $this$setupAutomationsScreen_u24render_u24lambda_u243_u244 = new TextView(dashboardActivity);
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u244.setText("ПУСК");
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u244.setGravity(17);
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u244.setTextColor(ContextCompat.getColor(dashboardActivity, R.color.jarvis_cyan));
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u244.setTextSize(10.0f);
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u244.setTypeface(Typeface.MONOSPACE);
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u244.setBackground(ContextCompat.getDrawable(dashboardActivity, R.drawable.bg_fui_active));
            Iterable $this$forEach$iv2 = $this$forEach$iv;
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u244.setPadding(dashboardActivity.dp2px(10), dashboardActivity.dp2px(8), dashboardActivity.dp2px(10), dashboardActivity.dp2px(8));
            LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(-2, -2);
            lp2.setMarginEnd(dashboardActivity.dp2px(8));
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u244.setLayoutParams(lp2);
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u244.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda81
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupAutomationsScreen$render$lambda$3$4$0(routine, preview, dashboardActivity, view);
                }
            });
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u240.addView($this$setupAutomationsScreen_u24render_u24lambda_u243_u244);
            boolean enabled = routine.getStatus() == RoutineStatus.SAVED;
            JarvisToggle $this$setupAutomationsScreen_u24render_u24lambda_u243_u245 = new JarvisToggle(dashboardActivity, null, 0, 6, null);
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u245.setLayoutParams(new LinearLayout.LayoutParams(dashboardActivity.dp2px(48), dashboardActivity.dp2px(28)));
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u245.setChecked(enabled);
            final RoutineStore routineStore = store;
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u245.setOnCheckedChanged(new Function1() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda82
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DashboardActivity.setupAutomationsScreen$render$lambda$3$5$0(routineStore, routine, this$0, container, preview, ((Boolean) obj).booleanValue());
                }
            });
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u240.addView($this$setupAutomationsScreen_u24render_u24lambda_u243_u245);
            $this$setupAutomationsScreen_u24render_u24lambda_u243_u240.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda83
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupAutomationsScreen$render$lambda$3$6(routine, preview, view);
                }
            });
            container.addView($this$setupAutomationsScreen_u24render_u24lambda_u243_u240);
            dashboardActivity = this$0;
            store = store;
            list = list2;
            $this$forEach$iv = $this$forEach$iv2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupAutomationsScreen$render$lambda$3$4$0(SavedRoutine $routine, TextView $preview, DashboardActivity this$0, View it) {
        String strJoinToString$default = CollectionsKt.joinToString$default($routine.getPlan().getSteps(), StringUtils.LF, null, null, 0, null, new Function1() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda120
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DashboardActivity.setupAutomationsScreen$render$lambda$3$4$0$0((PlanStep) obj);
            }
        }, 30, null);
        if (StringsKt.isBlank(strJoinToString$default)) {
            strJoinToString$default = "• " + $routine.getNaturalLanguage();
        }
        String steps = strJoinToString$default;
        if ($preview != null) {
            $preview.setText("TRIGGER  " + $routine.getTitle() + "\nACTION\n" + steps + "\nCONFIRM  запуск");
        }
        this$0.dispatchAssistantPrompt($routine.getNaturalLanguage());
        if ($preview != null) {
            $preview.append("\nSTATUS  команда отправлена в пайплайн");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence setupAutomationsScreen$render$lambda$3$4$0$0(PlanStep it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String toolId = it.getToolId();
        if (toolId == null) {
            toolId = it.getDisplaySummary();
        }
        return "• " + toolId;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setupAutomationsScreen$render$lambda$3$5$0(RoutineStore $store, SavedRoutine $routine, DashboardActivity this$0, LinearLayout $container, TextView $preview, boolean on) throws Throwable {
        if (on) {
            if ($store != null) {
                $store.save($routine.copy((495 & 1) != 0 ? $routine.id : null, (495 & 2) != 0 ? $routine.title : null, (495 & 4) != 0 ? $routine.naturalLanguage : null, (495 & 8) != 0 ? $routine.plan : null, (495 & 16) != 0 ? $routine.status : RoutineStatus.SAVED, (495 & 32) != 0 ? $routine.createdAtMillis : 0L, (495 & 64) != 0 ? $routine.approvedAtMillis : null, (495 & 128) != 0 ? $routine.sourceBehaviorKey : null, (495 & 256) != 0 ? $routine.notes : null));
            }
        } else if ($store != null) {
            $store.disable($routine.getId());
        }
        if ($store != null) {
            RoutinePersistence.INSTANCE.save(this$0, $store.all());
        }
        setupAutomationsScreen$render($container, this$0, $preview);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupAutomationsScreen$render$lambda$3$6(SavedRoutine $routine, TextView $preview, View it) {
        String strJoinToString$default = CollectionsKt.joinToString$default($routine.getPlan().getSteps(), StringUtils.LF, null, null, 0, null, new Function1() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda26
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DashboardActivity.setupAutomationsScreen$render$lambda$3$6$0((PlanStep) obj);
            }
        }, 30, null);
        if (StringsKt.isBlank(strJoinToString$default)) {
            strJoinToString$default = "• " + $routine.getNaturalLanguage();
        }
        String steps = strJoinToString$default;
        if ($preview != null) {
            $preview.setText("TRIGGER  " + $routine.getTitle() + "\nACTION\n" + steps + "\nCONFIRM  " + $routine.getStatus().name());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence setupAutomationsScreen$render$lambda$3$6$0(PlanStep it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String toolId = it.getToolId();
        if (toolId == null) {
            toolId = it.getDisplaySummary();
        }
        return "• " + toolId;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void setupAutomationsScreen$lambda$4(final DashboardActivity this$0, final TextView $preview, final LinearLayout $container, View it) {
        final EditText $this$setupAutomationsScreen_u24lambda_u244_u240 = new EditText(this$0);
        $this$setupAutomationsScreen_u24lambda_u244_u240.setHint("Название");
        final String[] strArr = {"По фразе", "Утро", "Батарея ниже 20%", "В дороге"};
        final String[] strArr2 = {"Режим фокуса", "Утренний брифинг", "Лёгкая модель", "Hands-free"};
        final Ref.ObjectRef trigger = new Ref.ObjectRef();
        trigger.element = strArr[0];
        final Ref.ObjectRef action = new Ref.ObjectRef();
        action.element = strArr2[0];
        final TextView $this$setupAutomationsScreen_u24lambda_u244_u241 = new TextView(this$0);
        $this$setupAutomationsScreen_u24lambda_u244_u241.setText("ТРИГГЕР  " + trigger.element);
        $this$setupAutomationsScreen_u24lambda_u244_u241.setTextColor(ContextCompat.getColor(this$0, R.color.jarvis_cyan));
        $this$setupAutomationsScreen_u24lambda_u244_u241.setPadding(0, this$0.dp2px(12), 0, this$0.dp2px(8));
        $this$setupAutomationsScreen_u24lambda_u244_u241.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda32
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DashboardActivity.setupAutomationsScreen$lambda$4$1$0(this.f$0, strArr, trigger, $this$setupAutomationsScreen_u24lambda_u244_u241, view);
            }
        });
        final TextView $this$setupAutomationsScreen_u24lambda_u244_u242 = new TextView(this$0);
        $this$setupAutomationsScreen_u24lambda_u244_u242.setText("ДЕЙСТВИЕ  " + action.element);
        $this$setupAutomationsScreen_u24lambda_u244_u242.setTextColor(ContextCompat.getColor(this$0, R.color.jarvis_cyan));
        $this$setupAutomationsScreen_u24lambda_u244_u242.setPadding(0, this$0.dp2px(8), 0, this$0.dp2px(8));
        $this$setupAutomationsScreen_u24lambda_u244_u242.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda34
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DashboardActivity.setupAutomationsScreen$lambda$4$2$0(this.f$0, strArr2, action, $this$setupAutomationsScreen_u24lambda_u244_u242, view);
            }
        });
        LinearLayout $this$setupAutomationsScreen_u24lambda_u244_u243 = new LinearLayout(this$0);
        $this$setupAutomationsScreen_u24lambda_u244_u243.setOrientation(1);
        $this$setupAutomationsScreen_u24lambda_u244_u243.setPadding(this$0.dp2px(16), this$0.dp2px(8), this$0.dp2px(16), this$0.dp2px(8));
        $this$setupAutomationsScreen_u24lambda_u244_u243.addView($this$setupAutomationsScreen_u24lambda_u244_u240);
        $this$setupAutomationsScreen_u24lambda_u244_u243.addView($this$setupAutomationsScreen_u24lambda_u244_u241);
        $this$setupAutomationsScreen_u24lambda_u244_u243.addView($this$setupAutomationsScreen_u24lambda_u244_u242);
        new AlertDialog.Builder(this$0).setTitle("Новый протокол").setView($this$setupAutomationsScreen_u24lambda_u244_u243).setNegativeButton("Отмена", (DialogInterface.OnClickListener) null).setPositiveButton("Создать", new DialogInterface.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda35
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) throws Throwable {
                DashboardActivity.setupAutomationsScreen$lambda$4$4($this$setupAutomationsScreen_u24lambda_u244_u240, trigger, action, this$0, $preview, $container, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupAutomationsScreen$lambda$4$1$0(DashboardActivity this$0, final String[] $triggers, final Ref.ObjectRef $trigger, final TextView $this_apply, View it) {
        new AlertDialog.Builder(this$0).setTitle("Триггер").setItems($triggers, new DialogInterface.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda19
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DashboardActivity.setupAutomationsScreen$lambda$4$1$0$0($trigger, $triggers, $this_apply, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void setupAutomationsScreen$lambda$4$1$0$0(Ref.ObjectRef $trigger, String[] strArr, TextView $this_apply, DialogInterface dialogInterface, int which) {
        $trigger.element = strArr[which];
        $this_apply.setText("ТРИГГЕР  " + $trigger.element);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupAutomationsScreen$lambda$4$2$0(DashboardActivity this$0, final String[] $actions, final Ref.ObjectRef $action, final TextView $this_apply, View it) {
        new AlertDialog.Builder(this$0).setTitle("Действие").setItems($actions, new DialogInterface.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda59
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DashboardActivity.setupAutomationsScreen$lambda$4$2$0$0($action, $actions, $this_apply, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void setupAutomationsScreen$lambda$4$2$0$0(Ref.ObjectRef $action, String[] strArr, TextView $this_apply, DialogInterface dialogInterface, int which) {
        $action.element = strArr[which];
        $this_apply.setText("ДЕЙСТВИЕ  " + $action.element);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void setupAutomationsScreen$lambda$4$4(EditText $nameInput, Ref.ObjectRef $trigger, Ref.ObjectRef $action, DashboardActivity this$0, TextView $preview, LinearLayout $container, DialogInterface dialogInterface, int i) throws Throwable {
        Unit unit;
        String string = StringsKt.trim((CharSequence) $nameInput.getText().toString()).toString();
        if (StringsKt.isBlank(string)) {
            string = (String) $action.element;
        }
        String title = string;
        String nl = $trigger.element + ": " + $action.element;
        BrainGraph.INSTANCE.build(this$0);
        RoutineStore store = BrainGraph.INSTANCE.routineStore();
        SavedRoutine saved = RoutinePersistence.INSTANCE.stub("routine-ui-" + System.currentTimeMillis(), title, nl, RoutineStatus.SAVED);
        try {
            Result.Companion companion = Result.INSTANCE;
            if (store != null) {
                store.save(saved);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            Result.m392constructorimpl(unit);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (store != null) {
            RoutinePersistence.INSTANCE.save(this$0, store.all());
        }
        if ($preview != null) {
            $preview.setText("TRIGGER  " + $trigger.element + "\nACTION  " + $action.element + "\nCONFIRM  сохранено");
        }
        setupAutomationsScreen$render($container, this$0, $preview);
    }

    private final void setupMemoryScreen(final View screen) {
        TextView search = (TextView) screen.findViewById(R.id.memorySearch);
        final LinearLayout container = (LinearLayout) screen.findViewById(R.id.factsContainer);
        TextView clearBtn = (TextView) screen.findViewById(R.id.clearMemoryButton);
        TextView clearChatBtn = (TextView) screen.findViewById(R.id.clearChatHistoryButton);
        TextView addBtn = (TextView) screen.findViewById(R.id.btnAddMemoryFact);
        TextView modelsStatus = (TextView) screen.findViewById(R.id.memoryModelsStatus);
        TextView openModels = (TextView) screen.findViewById(R.id.btnOpenModelsFromMemory);
        search.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda90
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DashboardActivity.setupMemoryScreen$lambda$7(this.f$0, screen, container, view);
            }
        });
        if (addBtn != null) {
            addBtn.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda91
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupMemoryScreen$lambda$8(this.f$0, screen, container, view);
                }
            });
        }
        if (openModels != null) {
            openModels.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda92
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) throws Throwable {
                    this.f$0.openControlSecondary("models");
                }
            });
        }
        clearBtn.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda93
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DashboardActivity.setupMemoryScreen$lambda$10(this.f$0, screen, container, view);
            }
        });
        if (clearChatBtn != null) {
            clearChatBtn.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda94
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupMemoryScreen$lambda$11(this.f$0, view);
                }
            });
        }
        TextView textView = (TextView) screen.findViewById(R.id.btnSeedMemory);
        if (textView != null) {
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda95
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupMemoryScreen$lambda$13(this.f$0, screen, container, view);
                }
            });
        }
        setupMemoryScreen$paintMemChips(screen, this, container);
        setupMemoryScreen$renderModelsHint(this, modelsStatus);
        setupMemoryScreen$render$default(this, screen, container, null, 8, null);
    }

    private static final void setupMemoryScreen$renderModelsHint(DashboardActivity this$0, TextView modelsStatus) {
        ModelManager mm = ModelManager.INSTANCE.get(this$0);
        mm.refreshAll();
        Iterable $this$count$iv = ModelCatalog.INSTANCE.getALL();
        int i = 0;
        if (!($this$count$iv instanceof Collection) || !((Collection) $this$count$iv).isEmpty()) {
            int count$iv = 0;
            for (Object element$iv : $this$count$iv) {
                ModelCatalog.Entry it = (ModelCatalog.Entry) element$iv;
                if ((mm.status(it.getId()).getState() == ModelManager.State.READY) && (count$iv = count$iv + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
            i = count$iv;
        }
        int ready = i;
        if (modelsStatus != null) {
            modelsStatus.setText(ready == 0 ? "Модели ещё не скачаны. Для быстрого диалога скачай «Qwen3 0.6B», плюс FunctionGemma + TTS/ASR." : "Готово моделей: " + ready + " / " + ModelCatalog.INSTANCE.getALL().size() + ". Недостающие — ЕЩЁ → Модели (для голоса: Qwen3 0.6B).");
        }
    }

    static /* synthetic */ void setupMemoryScreen$render$default(DashboardActivity dashboardActivity, View view, LinearLayout linearLayout, String str, int i, Object obj) {
        if ((i & 8) != 0) {
            str = "";
        }
        setupMemoryScreen$render(dashboardActivity, view, linearLayout, str);
    }

    private static final void setupMemoryScreen$render(final DashboardActivity this$0, final View $screen, final LinearLayout container, final String filter) {
        Object objM392constructorimpl;
        Collection destination$iv$iv;
        Iterator it;
        int i;
        int i2;
        Object objM392constructorimpl2;
        String str;
        try {
            Result.Companion companion = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(BrainGraph.INSTANCE.memory(this$0).all());
            while (true) {
                i = 1;
                i2 = 0;
                if (!it.hasNext()) {
                    break;
                }
                Object element$iv$iv = it.next();
                LongTermMemory.MemoryFact it2 = (LongTermMemory.MemoryFact) element$iv$iv;
                if (this$0.memoryCategory != null && !Intrinsics.areEqual(it2.getCategory(), this$0.memoryCategory)) {
                    i = 0;
                }
                if (i != 0) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        List listEmptyList = CollectionsKt.emptyList();
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = listEmptyList;
        }
        Iterable $this$filter$iv = (Iterable) objM392constructorimpl;
        destination$iv$iv = new ArrayList();
        it = $this$filter$iv.iterator();
        Iterable $this$filter$iv2 = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv2 : $this$filter$iv2) {
            LongTermMemory.MemoryFact it3 = (LongTermMemory.MemoryFact) element$iv$iv2;
            if (StringsKt.isBlank(filter) || StringsKt.contains((CharSequence) it3.getText(), (CharSequence) filter, true)) {
                destination$iv$iv2.add(element$iv$iv2);
            }
        }
        List facts = (List) destination$iv$iv2;
        try {
            Result.Companion companion3 = Result.INSTANCE;
            objM392constructorimpl2 = Result.m392constructorimpl(Integer.valueOf(BrainGraph.INSTANCE.memory(this$0).count()));
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            objM392constructorimpl2 = Result.m392constructorimpl(ResultKt.createFailure(th2));
        }
        Integer numValueOf = Integer.valueOf(facts.size());
        if (Result.m398isFailureimpl(objM392constructorimpl2)) {
            objM392constructorimpl2 = numValueOf;
        }
        int total = ((Number) objM392constructorimpl2).intValue();
        TextView textView = (TextView) $screen.findViewById(R.id.memSlots);
        if (textView != null) {
            textView.setText("MEMORY SLOTS: " + total + " / 50 · CONTEXT WEIGHT: " + (total < 40 ? "OK" : "HIGH"));
        }
        container.removeAllViews();
        float f = 12.0f;
        if (facts.isEmpty()) {
            TextView $this$setupMemoryScreen_u24render_u24lambda_u245 = new TextView(this$0);
            if (StringsKt.isBlank(filter)) {
            }
            $this$setupMemoryScreen_u24render_u24lambda_u245.setText(str);
            $this$setupMemoryScreen_u24render_u24lambda_u245.setTextColor(Color.parseColor("#9DA5AE"));
            $this$setupMemoryScreen_u24render_u24lambda_u245.setTextSize(12.0f);
            container.addView($this$setupMemoryScreen_u24render_u24lambda_u245);
            return;
        }
        List $this$forEach$iv = facts;
        for (Object element$iv : $this$forEach$iv) {
            final LongTermMemory.MemoryFact fact = (LongTermMemory.MemoryFact) element$iv;
            LinearLayout $this$setupMemoryScreen_u24render_u24lambda_u246_u240 = new LinearLayout(this$0);
            $this$setupMemoryScreen_u24render_u24lambda_u246_u240.setOrientation(i);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2);
            lp.bottomMargin = this$0.dp2px(12);
            $this$setupMemoryScreen_u24render_u24lambda_u246_u240.setLayoutParams(lp);
            LinearLayout $this$setupMemoryScreen_u24render_u24lambda_u246_u241 = new LinearLayout(this$0);
            $this$setupMemoryScreen_u24render_u24lambda_u246_u241.setOrientation(i2);
            $this$setupMemoryScreen_u24render_u24lambda_u246_u241.setGravity(16);
            TextView $this$setupMemoryScreen_u24render_u24lambda_u246_u242 = new TextView(this$0);
            $this$setupMemoryScreen_u24render_u24lambda_u246_u242.setLayoutParams(new LinearLayout.LayoutParams(i2, -2, 1.0f));
            $this$setupMemoryScreen_u24render_u24lambda_u246_u242.setText(fact.getText());
            $this$setupMemoryScreen_u24render_u24lambda_u246_u242.setTextColor(Color.parseColor("#F4F0E7"));
            $this$setupMemoryScreen_u24render_u24lambda_u246_u242.setTextSize(f);
            final TextView $this$setupMemoryScreen_u24render_u24lambda_u246_u243 = new TextView(this$0);
            $this$setupMemoryScreen_u24render_u24lambda_u246_u243.setText("удалить");
            $this$setupMemoryScreen_u24render_u24lambda_u246_u243.setTextColor(Color.parseColor("#E46B58"));
            $this$setupMemoryScreen_u24render_u24lambda_u246_u243.setTextSize(10.0f);
            $this$setupMemoryScreen_u24render_u24lambda_u246_u243.setPadding(this$0.dp2px(8), this$0.dp2px(4), i2, this$0.dp2px(4));
            $this$setupMemoryScreen_u24render_u24lambda_u246_u243.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda29
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupMemoryScreen$render$lambda$6$3$0($this$setupMemoryScreen_u24render_u24lambda_u246_u243, filter, this$0, fact, $screen, container, view);
                }
            });
            TextView $this$setupMemoryScreen_u24render_u24lambda_u246_u244 = new TextView(this$0);
            $this$setupMemoryScreen_u24render_u24lambda_u246_u244.setText("править");
            $this$setupMemoryScreen_u24render_u24lambda_u246_u244.setTextColor(Color.parseColor("#62E9F4"));
            $this$setupMemoryScreen_u24render_u24lambda_u246_u244.setTextSize(10.0f);
            $this$setupMemoryScreen_u24render_u24lambda_u246_u244.setPadding(this$0.dp2px(8), this$0.dp2px(4), 0, this$0.dp2px(4));
            $this$setupMemoryScreen_u24render_u24lambda_u246_u244.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda30
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupMemoryScreen$render$lambda$6$4$0(this.f$0, fact, filter, $screen, container, view);
                }
            });
            $this$setupMemoryScreen_u24render_u24lambda_u246_u241.addView($this$setupMemoryScreen_u24render_u24lambda_u246_u242);
            $this$setupMemoryScreen_u24render_u24lambda_u246_u241.addView($this$setupMemoryScreen_u24render_u24lambda_u246_u244);
            $this$setupMemoryScreen_u24render_u24lambda_u246_u241.addView($this$setupMemoryScreen_u24render_u24lambda_u246_u243);
            TextView $this$setupMemoryScreen_u24render_u24lambda_u246_u245 = new TextView(this$0);
            $this$setupMemoryScreen_u24render_u24lambda_u246_u245.setText(fact.getSource() + " · " + new SimpleDateFormat("d MMM", new Locale("ru")).format(new Date(fact.getCreatedAt())));
            $this$setupMemoryScreen_u24render_u24lambda_u246_u245.setTextColor(Color.parseColor("#626B76"));
            $this$setupMemoryScreen_u24render_u24lambda_u246_u245.setTextSize(9.0f);
            $this$setupMemoryScreen_u24render_u24lambda_u246_u245.setTypeface(Typeface.MONOSPACE);
            LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(-2, -2);
            lp2.topMargin = this$0.dp2px(3);
            $this$setupMemoryScreen_u24render_u24lambda_u246_u245.setLayoutParams(lp2);
            $this$setupMemoryScreen_u24render_u24lambda_u246_u240.addView($this$setupMemoryScreen_u24render_u24lambda_u246_u241);
            $this$setupMemoryScreen_u24render_u24lambda_u246_u240.addView($this$setupMemoryScreen_u24render_u24lambda_u246_u245);
            container.addView($this$setupMemoryScreen_u24render_u24lambda_u246_u240);
            View $this$setupMemoryScreen_u24render_u24lambda_u246_u246 = new View(this$0);
            $this$setupMemoryScreen_u24render_u24lambda_u246_u246.setBackgroundColor(Color.parseColor("#2B333D"));
            $this$setupMemoryScreen_u24render_u24lambda_u246_u246.setLayoutParams(new LinearLayout.LayoutParams(-1, this$0.dp2px(1)));
            container.addView($this$setupMemoryScreen_u24render_u24lambda_u246_u246);
            facts = facts;
            i2 = 0;
            total = total;
            i = 1;
            f = 12.0f;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupMemoryScreen$render$lambda$6$3$0(TextView $this_apply, String $filter, DashboardActivity this$0, LongTermMemory.MemoryFact $fact, View $screen, LinearLayout $container, View it) {
        try {
            Result.Companion companion = Result.INSTANCE;
            Result.m392constructorimpl(Boolean.valueOf(BrainGraph.INSTANCE.memory(this$0).remove($fact.getId())));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        setupMemoryScreen$render(this$0, $screen, $container, $filter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupMemoryScreen$render$lambda$6$4$0(final DashboardActivity this$0, final LongTermMemory.MemoryFact $fact, final String $filter, final View $screen, final LinearLayout $container, View it) {
        final EditText $this$setupMemoryScreen_u24render_u24lambda_u246_u244_u240_u240 = new EditText(this$0);
        $this$setupMemoryScreen_u24render_u24lambda_u246_u244_u240_u240.setText($fact.getText());
        new AlertDialog.Builder(this$0).setTitle("Факт").setView($this$setupMemoryScreen_u24render_u24lambda_u246_u244_u240_u240).setNegativeButton("Отмена", (DialogInterface.OnClickListener) null).setPositiveButton("Сохранить", new DialogInterface.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda79
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DashboardActivity.setupMemoryScreen$render$lambda$6$4$0$1($this$setupMemoryScreen_u24render_u24lambda_u246_u244_u240_u240, this$0, $fact, $filter, $screen, $container, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupMemoryScreen$render$lambda$6$4$0$1(EditText $input, DashboardActivity this$0, LongTermMemory.MemoryFact $fact, String $filter, View $screen, LinearLayout $container, DialogInterface dialogInterface, int i) {
        String next = StringsKt.trim((CharSequence) $input.getText().toString()).toString();
        if (!StringsKt.isBlank(next)) {
            BrainGraph.INSTANCE.memory(this$0).remove($fact.getId());
            BrainGraph.INSTANCE.memory(this$0).add(next, $fact.getCategory(), $fact.getSource());
            setupMemoryScreen$render(this$0, $screen, $container, $filter);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupMemoryScreen$lambda$7(final DashboardActivity this$0, final View $screen, final LinearLayout $container, View it) {
        final EditText $this$setupMemoryScreen_u24lambda_u247_u240 = new EditText(this$0);
        $this$setupMemoryScreen_u24lambda_u247_u240.setHint("Поиск в памяти…");
        new AlertDialog.Builder(this$0).setTitle("Поиск").setView($this$setupMemoryScreen_u24lambda_u247_u240).setNegativeButton("Отмена", (DialogInterface.OnClickListener) null).setPositiveButton("Найти", new DialogInterface.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda117
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DashboardActivity.setupMemoryScreen$lambda$7$1($this$setupMemoryScreen_u24lambda_u247_u240, this$0, $screen, $container, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupMemoryScreen$lambda$7$1(EditText $input, DashboardActivity this$0, View $screen, LinearLayout $container, DialogInterface dialogInterface, int i) {
        setupMemoryScreen$render(this$0, $screen, $container, StringsKt.trim((CharSequence) $input.getText().toString()).toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupMemoryScreen$lambda$8(final DashboardActivity this$0, final View $screen, final LinearLayout $container, View it) {
        final EditText $this$setupMemoryScreen_u24lambda_u248_u240 = new EditText(this$0);
        $this$setupMemoryScreen_u24lambda_u248_u240.setHint("Факт о тебе…");
        new AlertDialog.Builder(this$0).setTitle("Добавить в память").setView($this$setupMemoryScreen_u24lambda_u248_u240).setNegativeButton("Отмена", (DialogInterface.OnClickListener) null).setPositiveButton("Сохранить", new DialogInterface.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda31
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DashboardActivity.setupMemoryScreen$lambda$8$1($this$setupMemoryScreen_u24lambda_u248_u240, this$0, $screen, $container, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupMemoryScreen$lambda$8$1(EditText $input, DashboardActivity this$0, View $screen, LinearLayout $container, DialogInterface dialogInterface, int i) {
        String t = StringsKt.trim((CharSequence) $input.getText().toString()).toString();
        if (t.length() > 0) {
            try {
                Result.Companion companion = Result.INSTANCE;
                Result.m392constructorimpl(Boolean.valueOf(BrainGraph.INSTANCE.memory(this$0).add(t, JarvisSchema.ROLE_USER, "manual")));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m392constructorimpl(ResultKt.createFailure(th));
            }
            setupMemoryScreen$render$default(this$0, $screen, $container, null, 8, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupMemoryScreen$lambda$10(final DashboardActivity this$0, final View $screen, final LinearLayout $container, View it) {
        new AlertDialog.Builder(this$0).setTitle(R.string.memory_clear_facts_title).setMessage(R.string.memory_clear_facts_message).setNegativeButton(R.string.memory_confirm_no, (DialogInterface.OnClickListener) null).setPositiveButton(R.string.memory_confirm_yes, new DialogInterface.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda25
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DashboardActivity.setupMemoryScreen$lambda$10$0(this.f$0, $screen, $container, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupMemoryScreen$lambda$10$0(DashboardActivity this$0, View $screen, LinearLayout $container, DialogInterface dialogInterface, int i) {
        try {
            Result.Companion companion = Result.INSTANCE;
            BrainGraph.INSTANCE.memory(this$0).clear();
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        setupMemoryScreen$render$default(this$0, $screen, $container, null, 8, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupMemoryScreen$lambda$11(final DashboardActivity this$0, View it) {
        new AlertDialog.Builder(this$0).setTitle(R.string.memory_clear_chat_title).setMessage(R.string.memory_clear_chat_message).setNegativeButton(R.string.memory_confirm_no, (DialogInterface.OnClickListener) null).setPositiveButton(R.string.memory_confirm_yes, new DialogInterface.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda56
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DashboardActivity.setupMemoryScreen$lambda$11$0(this.f$0, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupMemoryScreen$lambda$11$0(DashboardActivity this$0, DialogInterface dialogInterface, int i) {
        try {
            Result.Companion companion = Result.INSTANCE;
            ConversationJournal.INSTANCE.clearAll();
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        ConversationStore.INSTANCE.clear();
        try {
            Result.Companion companion3 = Result.INSTANCE;
            BrainGraph.INSTANCE.clearDialogueHistory();
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th2));
        }
    }

    private static final void setupMemoryScreen$paintMemChips(final View $screen, final DashboardActivity this$0, final LinearLayout container) {
        Iterable chips = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(Integer.valueOf(R.id.memChipAll), null), TuplesKt.to(Integer.valueOf(R.id.memChipProfile), Scopes.PROFILE), TuplesKt.to(Integer.valueOf(R.id.memChipHabits), "habit"), TuplesKt.to(Integer.valueOf(R.id.memChipProjects), "project"), TuplesKt.to(Integer.valueOf(R.id.memChipNotes), "note")});
        Iterable $this$forEach$iv = chips;
        for (Object element$iv : $this$forEach$iv) {
            Pair pair = (Pair) element$iv;
            int id = ((Number) pair.component1()).intValue();
            final String cat = (String) pair.component2();
            TextView $this$setupMemoryScreen_u24paintMemChips_u24lambda_u2412_u240 = (TextView) $screen.findViewById(id);
            if ($this$setupMemoryScreen_u24paintMemChips_u24lambda_u2412_u240 != null) {
                boolean on = Intrinsics.areEqual(this$0.memoryCategory, cat);
                $this$setupMemoryScreen_u24paintMemChips_u24lambda_u2412_u240.setTextColor(ContextCompat.getColor(this$0, on ? R.color.jarvis_cyan : R.color.jarvis_muted));
                $this$setupMemoryScreen_u24paintMemChips_u24lambda_u2412_u240.setBackground(ContextCompat.getDrawable(this$0, on ? R.drawable.bg_fui_active : R.drawable.bg_hud_panel));
                $this$setupMemoryScreen_u24paintMemChips_u24lambda_u2412_u240.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda76
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        DashboardActivity.setupMemoryScreen$paintMemChips$lambda$12$0$0(this.f$0, cat, $screen, container, view);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupMemoryScreen$paintMemChips$lambda$12$0$0(DashboardActivity this$0, String $cat, View $screen, LinearLayout $container, View it) {
        this$0.memoryCategory = $cat;
        setupMemoryScreen$paintMemChips($screen, this$0, $container);
        setupMemoryScreen$render$default(this$0, $screen, $container, null, 8, null);
    }

    private static final void setupMemoryScreen$seedDemo(DashboardActivity this$0) {
        LongTermMemory memory = BrainGraph.INSTANCE.memory(this$0);
        memory.add("Пользователь предпочитает короткие ответы.", Scopes.PROFILE, FactPolicies.SEED_SOURCE);
        memory.add("Вечером лучше не беспокоить без причины.", "habit", FactPolicies.SEED_SOURCE);
        memory.add("Текущий проект — Jarvis for Android.", "project", FactPolicies.SEED_SOURCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupMemoryScreen$lambda$13(DashboardActivity this$0, View $screen, LinearLayout $container, View it) {
        setupMemoryScreen$seedDemo(this$0);
        setupMemoryScreen$render$default(this$0, $screen, $container, null, 8, null);
    }

    private final int dp2px(int v) {
        return (int) (v * getResources().getDisplayMetrics().density);
    }

    private final void wireLiveControls(final View screen) {
        final Ref.BooleanRef micOn = new Ref.BooleanRef();
        micOn.element = true;
        View viewFindViewById = screen.findViewById(R.id.liveMic);
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda138
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.wireLiveControls$lambda$0(this.f$0, micOn, view);
                }
            });
        }
        final Ref.BooleanRef camOn = new Ref.BooleanRef();
        View viewFindViewById2 = screen.findViewById(R.id.liveCam);
        if (viewFindViewById2 != null) {
            viewFindViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda139
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.wireLiveControls$lambda$1(camOn, this, view);
                }
            });
        }
        final Ref.BooleanRef screenOn = new Ref.BooleanRef();
        View viewFindViewById3 = screen.findViewById(R.id.liveScreen);
        if (viewFindViewById3 != null) {
            viewFindViewById3.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda140
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.wireLiveControls$lambda$2(screenOn, this, screen, view);
                }
            });
        }
        View viewFindViewById4 = screen.findViewById(R.id.liveShareStop);
        if (viewFindViewById4 != null) {
            viewFindViewById4.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda141
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.wireLiveControls$lambda$3(screenOn, this, screen, view);
                }
            });
        }
        final Ref.BooleanRef capsOn = new Ref.BooleanRef();
        capsOn.element = true;
        View viewFindViewById5 = screen.findViewById(R.id.liveCaps);
        if (viewFindViewById5 != null) {
            viewFindViewById5.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda143
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.wireLiveControls$lambda$4(capsOn, screen, this, view);
                }
            });
        }
        wireLiveControls$styleCtl(this, screen.findViewById(R.id.liveMic), true);
        wireLiveControls$styleCtl(this, screen.findViewById(R.id.liveCaps), true);
    }

    private static final void wireLiveControls$styleCtl(DashboardActivity this$0, View v, boolean on) {
        int color = ContextCompat.getColor(this$0, on ? R.color.jarvis_cyan : R.color.jarvis_muted);
        if (v instanceof ImageView) {
            if (((ImageView) v).getId() == R.id.liveMic) {
                ((ImageView) v).setAlpha(on ? 1.0f : 0.45f);
                ((ImageView) v).setColorFilter(color);
                return;
            } else {
                ((ImageView) v).setBackground(ContextCompat.getDrawable(this$0, on ? R.drawable.bg_fui_active : R.drawable.bg_hud_panel));
                ((ImageView) v).setColorFilter(color);
                return;
            }
        }
        if (v instanceof TextView) {
            ((TextView) v).setTextColor(color);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void wireLiveControls$lambda$0(DashboardActivity this$0, Ref.BooleanRef $micOn, View it) {
        HudFeedback hudFeedback = HudFeedback.INSTANCE;
        Intrinsics.checkNotNull(it);
        hudFeedback.key(it);
        boolean idle = this$0.assistantState == AssistantState.OFFLINE || this$0.assistantState == AssistantState.ERROR;
        if (idle) {
            this$0.activateAssistant(true);
            return;
        }
        $micOn.element = !$micOn.element;
        wireLiveControls$styleCtl(this$0, it, $micOn.element);
        this$0.getSharedPreferences("jarvis_prefs", 0).edit().putBoolean("live_mic_on", $micOn.element).apply();
        Toast.makeText(this$0, $micOn.element ? "Микрофон включён" : "Микрофон приглушён", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void wireLiveControls$lambda$1(Ref.BooleanRef $camOn, DashboardActivity this$0, View it) {
        HudFeedback hudFeedback = HudFeedback.INSTANCE;
        Intrinsics.checkNotNull(it);
        hudFeedback.key(it);
        $camOn.element = !$camOn.element;
        wireLiveControls$styleCtl(this$0, it, $camOn.element);
        if (!$camOn.element) {
            this$0.setLiveViewfinder(false);
        } else if (ContextCompat.checkSelfPermission(this$0, "android.permission.CAMERA") == 0) {
            this$0.setLiveViewfinder(true);
        } else {
            this$0.pendingViewfinder = true;
            this$0.cameraPermissionLauncher.launch("android.permission.CAMERA");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void wireLiveControls$lambda$2(Ref.BooleanRef $screenOn, DashboardActivity this$0, View $screen, View it) {
        HudFeedback hudFeedback = HudFeedback.INSTANCE;
        Intrinsics.checkNotNull(it);
        hudFeedback.key(it);
        $screenOn.element = !$screenOn.element;
        this$0.liveScreenLinked = $screenOn.element;
        wireLiveControls$styleCtl(this$0, it, $screenOn.element);
        LinearLayout linearLayout = (LinearLayout) $screen.findViewById(R.id.liveShareCard);
        if (linearLayout != null) {
            linearLayout.setVisibility($screenOn.element ? 0 : 8);
        }
        if ($screenOn.element) {
            Toast.makeText(this$0, "Метка экрана включена. Снимок кадра не снимается.", 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void wireLiveControls$lambda$3(Ref.BooleanRef $screenOn, DashboardActivity this$0, View $screen, View it) {
        $screenOn.element = false;
        this$0.liveScreenLinked = false;
        wireLiveControls$styleCtl(this$0, $screen.findViewById(R.id.liveScreen), false);
        LinearLayout linearLayout = (LinearLayout) $screen.findViewById(R.id.liveShareCard);
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void wireLiveControls$lambda$4(Ref.BooleanRef $capsOn, View $screen, DashboardActivity this$0, View it) {
        HudFeedback hudFeedback = HudFeedback.INSTANCE;
        Intrinsics.checkNotNull(it);
        hudFeedback.key(it);
        $capsOn.element = !$capsOn.element;
        wireLiveControls$styleCtl(this$0, it, $capsOn.element);
        float panelAlpha = $capsOn.element ? 1.0f : 0.35f;
        TextView textView = (TextView) $screen.findViewById(R.id.liveTranscript);
        if (textView != null) {
            textView.setAlpha(panelAlpha);
        }
        TextView textView2 = (TextView) $screen.findViewById(R.id.liveTranscriptSaid);
        if (textView2 != null) {
            textView2.setAlpha(panelAlpha);
        }
    }

    private final void startLiveTimer(View screen) {
        if (VoiceController.INSTANCE.isLiveSession()) {
            this.liveTimerStartMs = SystemClock.elapsedRealtime();
            this.liveTimerHandler.removeCallbacks(this.liveTimerRunnable);
            this.liveTimerHandler.postDelayed(this.liveTimerRunnable, 0L);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.liveTimerHandler.removeCallbacks(this.liveTimerRunnable);
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderAssistantState(AssistantState state) {
        float orbAlpha;
        FrameLayout frameLayout;
        String str;
        String str2;
        String str3;
        String str4;
        this.assistantState = state;
        String themeName = JarvisSettings.INSTANCE.theme(this);
        TextView textView = (TextView) findViewById(R.id.statusTitle);
        if (textView != null) {
            textView.setText(screenTitle(this.currentScreenIndex));
        }
        ThemeApplier.Palette palette = ThemeApplier.INSTANCE.palette(themeName);
        CoreTheme.CoreColors c = CoreTheme.INSTANCE.forState(state, palette.getCyan());
        switch (WhenMappings.$EnumSwitchMapping$0[state.ordinal()]) {
            case 1:
                orbAlpha = 0.55f;
                break;
            case 2:
            case 3:
                orbAlpha = 0.78f;
                break;
            case 4:
                orbAlpha = 1.0f;
                break;
            case 5:
                orbAlpha = 1.0f;
                break;
            default:
                orbAlpha = 0.9f;
                break;
        }
        FrameLayout frameLayout2 = this.screenContainer;
        if (frameLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
            frameLayout2 = null;
        }
        TextView $this$renderAssistantState_u24lambda_u240 = (TextView) frameLayout2.findViewById(R.id.assistantState);
        if ($this$renderAssistantState_u24lambda_u240 != null) {
            switch (WhenMappings.$EnumSwitchMapping$0[state.ordinal()]) {
                case 1:
                case 6:
                    str4 = "● VOICE SERVICE: OFFLINE (TAP MIC TO START)";
                    break;
                case 7:
                    str4 = "● VOICE SERVICE: STARTING";
                    break;
                default:
                    str4 = "● VOICE SERVICE: ACTIVE";
                    break;
            }
            $this$renderAssistantState_u24lambda_u240.setText(str4);
            $this$renderAssistantState_u24lambda_u240.setTextColor(c.getCore());
        }
        FrameLayout frameLayout3 = this.screenContainer;
        if (frameLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
            frameLayout3 = null;
        }
        TextView textView2 = (TextView) frameLayout3.findViewById(R.id.assistantStateDetail);
        if (textView2 != null) {
            textView2.setText(stateDetail(state));
        }
        FrameLayout frameLayout4 = this.screenContainer;
        if (frameLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
            frameLayout4 = null;
        }
        ReactorCoreView $this$renderAssistantState_u24lambda_u241 = (ReactorCoreView) frameLayout4.findViewById(R.id.homeOrb);
        if ($this$renderAssistantState_u24lambda_u241 != null) {
            $this$renderAssistantState_u24lambda_u241.setState(state);
            $this$renderAssistantState_u24lambda_u241.setAccent(palette.getCyan());
            $this$renderAssistantState_u24lambda_u241.setHot(palette.getPrimary());
            $this$renderAssistantState_u24lambda_u241.setAlpha(orbAlpha);
        }
        FrameLayout frameLayout5 = this.screenContainer;
        if (frameLayout5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
            frameLayout5 = null;
        }
        EqualizerView $this$renderAssistantState_u24lambda_u242 = (EqualizerView) frameLayout5.findViewById(R.id.homeEq);
        boolean z = true;
        boolean z2 = false;
        if ($this$renderAssistantState_u24lambda_u242 != null) {
            $this$renderAssistantState_u24lambda_u242.setActive(state == AssistantState.LISTENING || state == AssistantState.SPEAKING);
            $this$renderAssistantState_u24lambda_u242.setAccent(palette.getCyan());
        }
        Pair[] pairArr = new Pair[5];
        pairArr[0] = TuplesKt.to(Integer.valueOf(R.id.pipeWake), Boolean.valueOf(state == AssistantState.IDLE || state == AssistantState.READY || state == AssistantState.OFFLINE));
        pairArr[1] = TuplesKt.to(Integer.valueOf(R.id.pipeAsr), Boolean.valueOf(state == AssistantState.LISTENING));
        pairArr[2] = TuplesKt.to(Integer.valueOf(R.id.pipeRouter), Boolean.valueOf(state == AssistantState.THINKING));
        pairArr[3] = TuplesKt.to(Integer.valueOf(R.id.pipeLlm), Boolean.valueOf(state == AssistantState.THINKING || state == AssistantState.EXECUTING));
        pairArr[4] = TuplesKt.to(Integer.valueOf(R.id.pipeTts), Boolean.valueOf(state == AssistantState.SPEAKING));
        Iterable pipes = CollectionsKt.listOf((Object[]) pairArr);
        boolean wakeHot = JarvisSettings.INSTANCE.wakeEnabled(this) && (state == AssistantState.OFFLINE || state == AssistantState.IDLE || state == AssistantState.READY);
        Iterable $this$forEach$iv = pipes;
        for (Object element$iv : $this$forEach$iv) {
            Pair pair = (Pair) element$iv;
            int id = ((Number) pair.component1()).intValue();
            boolean on = ((Boolean) pair.component2()).booleanValue();
            boolean hot = (on || (id == R.id.pipeWake && wakeHot)) ? z : z2;
            FrameLayout frameLayout6 = this.screenContainer;
            if (frameLayout6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
                frameLayout6 = null;
            }
            TextView $this$renderAssistantState_u24lambda_u243_u240 = (TextView) frameLayout6.findViewById(id);
            if ($this$renderAssistantState_u24lambda_u243_u240 != null) {
                $this$renderAssistantState_u24lambda_u243_u240.setTextColor(ContextCompat.getColor(this, hot ? R.color.jarvis_cyan : R.color.jarvis_faint));
                $this$renderAssistantState_u24lambda_u243_u240.setBackground(ContextCompat.getDrawable(this, hot ? R.drawable.bg_fui_active : R.drawable.bg_hud_panel));
            }
            themeName = themeName;
            z = true;
            z2 = false;
        }
        FrameLayout frameLayout7 = this.screenContainer;
        if (frameLayout7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
            frameLayout7 = null;
        }
        View viewFindViewById = frameLayout7.findViewById(R.id.activateAssistant);
        if (viewFindViewById != null) {
            viewFindViewById.setEnabled(state != AssistantState.STARTING);
        }
        FrameLayout frameLayout8 = this.screenContainer;
        if (frameLayout8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
            frameLayout8 = null;
        }
        ConsciousnessView $this$renderAssistantState_u24lambda_u244 = (ConsciousnessView) frameLayout8.findViewById(R.id.liveOrb);
        if ($this$renderAssistantState_u24lambda_u244 != null) {
            $this$renderAssistantState_u24lambda_u244.setState(state);
            $this$renderAssistantState_u24lambda_u244.setAlpha(orbAlpha);
        }
        FrameLayout frameLayout9 = this.screenContainer;
        if (frameLayout9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
            frameLayout9 = null;
        }
        TextView $this$renderAssistantState_u24lambda_u245 = (TextView) frameLayout9.findViewById(R.id.liveState);
        if ($this$renderAssistantState_u24lambda_u245 != null) {
            switch (WhenMappings.$EnumSwitchMapping$0[state.ordinal()]) {
                case 1:
                    str3 = "LINK ERROR";
                    break;
                case 2:
                case 3:
                    str3 = "PROCESSING";
                    break;
                case 4:
                    str3 = "SPEAKING";
                    break;
                case 5:
                    str3 = "LISTENING";
                    break;
                case 6:
                default:
                    str3 = "DUPLEX STANDBY";
                    break;
                case 7:
                    str3 = "CONNECTING";
                    break;
            }
            $this$renderAssistantState_u24lambda_u245.setText(str3);
            $this$renderAssistantState_u24lambda_u245.setTextColor(c.getCore());
        }
        FrameLayout frameLayout10 = this.screenContainer;
        if (frameLayout10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
            frameLayout10 = null;
        }
        TextView $this$renderAssistantState_u24lambda_u246 = (TextView) frameLayout10.findViewById(R.id.liveActivate);
        if ($this$renderAssistantState_u24lambda_u246 != null) {
            switch (WhenMappings.$EnumSwitchMapping$0[state.ordinal()]) {
                case 1:
                case 6:
                    str2 = "СТАРТ";
                    break;
                case 7:
                    str2 = "ЖДУ";
                    break;
                default:
                    str2 = "LIVE";
                    break;
            }
            $this$renderAssistantState_u24lambda_u246.setText(str2);
            $this$renderAssistantState_u24lambda_u246.setEnabled(state != AssistantState.STARTING);
            $this$renderAssistantState_u24lambda_u246.setAlpha(state == AssistantState.STARTING ? 0.6f : 1.0f);
        }
        FrameLayout frameLayout11 = this.screenContainer;
        if (frameLayout11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
            frameLayout11 = null;
        }
        View viewFindViewById2 = frameLayout11.findViewById(R.id.liveStop);
        if (viewFindViewById2 != null) {
            viewFindViewById2.setEnabled((state == AssistantState.OFFLINE || state == AssistantState.STARTING) ? false : true);
        }
        FrameLayout frameLayout12 = this.screenContainer;
        if (frameLayout12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
            frameLayout = null;
        } else {
            frameLayout = frameLayout12;
        }
        TextView textView3 = (TextView) frameLayout.findViewById(R.id.liveVoiceBadge);
        if (textView3 != null) {
            switch (WhenMappings.$EnumSwitchMapping$0[state.ordinal()]) {
                case 1:
                case 6:
                    str = "IDLE";
                    break;
                case 2:
                case 3:
                    str = "THINK";
                    break;
                case 4:
                    str = "SPEAK";
                    break;
                case 5:
                    str = "LISTEN";
                    break;
                default:
                    str = "VOICE";
                    break;
            }
            textView3.setText(str);
        }
    }

    private final void setLiveViewfinder(boolean show) {
        FrameLayout frameLayout = this.screenContainer;
        FrameLayout frameLayout2 = null;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
            frameLayout = null;
        }
        View finder = frameLayout.findViewById(R.id.liveViewfinder);
        if (finder == null) {
            return;
        }
        finder.setVisibility(show ? 0 : 8);
        FrameLayout frameLayout3 = this.screenContainer;
        if (frameLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
        } else {
            frameLayout2 = frameLayout3;
        }
        TextView textView = (TextView) frameLayout2.findViewById(R.id.liveCamThumb);
        if (textView != null) {
            textView.setVisibility(8);
        }
    }

    private final void flashScreenBadge() {
        FrameLayout frameLayout = this.screenContainer;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
            frameLayout = null;
        }
        final View badge = frameLayout.findViewById(R.id.liveShareCard);
        if (badge != null && badge.getVisibility() == 0) {
            badge.animate().alpha(0.25f).setDuration(80L).withEndAction(new Runnable() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda80
                @Override // java.lang.Runnable
                public final void run() {
                    DashboardActivity.flashScreenBadge$lambda$0(badge);
                }
            }).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void flashScreenBadge$lambda$0(View $badge) {
        $badge.animate().alpha(1.0f).setDuration(180L).start();
    }

    static /* synthetic */ void activateAssistant$default(DashboardActivity dashboardActivity, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        dashboardActivity.activateAssistant(z);
    }

    private final void activateAssistant(boolean liveMode) {
        HudFeedback.INSTANCE.beep(this, liveMode ? 28 : 24);
        this.pendingLiveMode = liveMode;
        if (ContextCompat.checkSelfPermission(this, "android.permission.RECORD_AUDIO") != 0) {
            this.audioPermissionLauncher.launch("android.permission.RECORD_AUDIO");
        } else {
            requestNotificationPermissionOrStart(liveMode);
        }
    }

    static /* synthetic */ void requestNotificationPermissionOrStart$default(DashboardActivity dashboardActivity, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = dashboardActivity.pendingLiveMode;
        }
        dashboardActivity.requestNotificationPermissionOrStart(z);
    }

    private final void requestNotificationPermissionOrStart(boolean liveMode) {
        if (Build.VERSION.SDK_INT >= 33 && ContextCompat.checkSelfPermission(this, "android.permission.POST_NOTIFICATIONS") != 0) {
            this.notificationPermissionLauncher.launch("android.permission.POST_NOTIFICATIONS");
            return;
        }
        VoiceController voiceController = VoiceController.INSTANCE;
        DashboardActivity dashboardActivity = this;
        if (liveMode) {
            voiceController.startLive(dashboardActivity);
        } else {
            voiceController.start(dashboardActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleAssistantEvent(AssistantEvent event) {
        String str;
        FrameLayout frameLayout = null;
        if (event instanceof AssistantEvent.TranscriptUpdated) {
            this.latestTranscript = ((AssistantEvent.TranscriptUpdated) event).getText();
        } else if (event instanceof AssistantEvent.RequestReceived) {
            this.latestRequest = ((AssistantEvent.RequestReceived) event).getText();
            ConversationStore.INSTANCE.pushUser(((AssistantEvent.RequestReceived) event).getText());
            ActivityFeed.push$default(ActivityFeed.INSTANCE, "Запрос", ((AssistantEvent.RequestReceived) event).getText(), ActivityFeed.Item.Kind.VOICE, false, false, 24, null);
            if (this.liveScreenLinked) {
                flashScreenBadge();
            }
        } else if (event instanceof AssistantEvent.ResponseGenerated) {
            this.latestResponse = ((AssistantEvent.ResponseGenerated) event).getText();
            ConversationStore conversationStore = ConversationStore.INSTANCE;
            String text = ((AssistantEvent.ResponseGenerated) event).getText();
            String it = this.latestIntent;
            if (StringsKt.isBlank(it)) {
                it = null;
            }
            conversationStore.pushAi(text, "DONE", it);
            ActivityFeed.push$default(ActivityFeed.INSTANCE, "Ответ JARVIS", StringsKt.take(((AssistantEvent.ResponseGenerated) event).getText(), 120), ActivityFeed.Item.Kind.VOICE, false, false, 24, null);
        } else if (event instanceof AssistantEvent.IntentResolved) {
            this.latestIntent = ((AssistantEvent.IntentResolved) event).getIntent().getType() + " · " + ((int) (((AssistantEvent.IntentResolved) event).getIntent().getConfidence() * 100)) + "%";
            ActivityFeed.push$default(ActivityFeed.INSTANCE, "Intent", this.latestIntent, ActivityFeed.Item.Kind.SYSTEM, false, false, 24, null);
        } else if (event instanceof AssistantEvent.ToolExecuted) {
            this.latestTool = ((AssistantEvent.ToolExecuted) event).getResult().getToolId() + " · " + ((AssistantEvent.ToolExecuted) event).getResult().getStatus() + " · " + ((AssistantEvent.ToolExecuted) event).getResult().getDurationMs() + " ms";
            boolean pending = ((AssistantEvent.ToolExecuted) event).getResult().getStatus() == ToolResultStatus.NEEDS_CONFIRMATION;
            boolean failed = ((AssistantEvent.ToolExecuted) event).getResult().getStatus() == ToolResultStatus.FAILED || ((AssistantEvent.ToolExecuted) event).getResult().getStatus() == ToolResultStatus.DENIED || ((AssistantEvent.ToolExecuted) event).getResult().getStatus() == ToolResultStatus.UNAVAILABLE;
            ActivityFeed activityFeed = ActivityFeed.INSTANCE;
            String str2 = "Tool · " + ((AssistantEvent.ToolExecuted) event).getResult().getToolId();
            String message = ((AssistantEvent.ToolExecuted) event).getResult().getMessage();
            if (message == null) {
                str = ((AssistantEvent.ToolExecuted) event).getResult().getStatus() + " · " + ((AssistantEvent.ToolExecuted) event).getResult().getDurationMs() + " ms";
            } else {
                str = message;
            }
            activityFeed.push(str2, str, pending ? ActivityFeed.Item.Kind.CONFIRM : ActivityFeed.Item.Kind.TOOL, pending, failed);
            if (pending) {
                String toolId = ((AssistantEvent.ToolExecuted) event).getResult().getToolId();
                String message2 = ((AssistantEvent.ToolExecuted) event).getResult().getMessage();
                if (message2 == null) {
                    message2 = ((AssistantEvent.ToolExecuted) event).getResult().getToolId();
                }
                showHudConfirm(toolId, message2);
            }
        } else if (Intrinsics.areEqual(event, AssistantEvent.WakeDetected.INSTANCE)) {
            this.latestResponse = "Слушаю команду…";
            ActivityFeed.push$default(ActivityFeed.INSTANCE, "Wake", "Вейк-ворд обнаружен", ActivityFeed.Item.Kind.VOICE, false, false, 24, null);
        } else {
            return;
        }
        renderVoiceContent();
        if (this.currentScreenIndex == 1) {
            FrameLayout frameLayout2 = this.screenContainer;
            if (frameLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
                frameLayout2 = null;
            }
            LinearLayout it2 = (LinearLayout) frameLayout2.findViewById(R.id.assistantThread);
            if (it2 != null) {
                renderAssistantThread(it2);
            }
        }
        if (this.currentScreenIndex == 3) {
            FrameLayout frameLayout3 = this.screenContainer;
            if (frameLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
            } else {
                frameLayout = frameLayout3;
            }
            LinearLayout it3 = (LinearLayout) frameLayout.findViewById(R.id.activityFeedContainer);
            if (it3 != null) {
                renderActivityFeed(it3);
            }
            updateJournalStats();
        }
    }

    private final void renderVoiceContent() {
        FrameLayout frameLayout = this.screenContainer;
        FrameLayout frameLayout2 = null;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
            frameLayout = null;
        }
        TextView textView = (TextView) frameLayout.findViewById(R.id.currentTranscript);
        if (textView != null) {
            String str = this.latestTranscript;
            if (StringsKt.isBlank(str)) {
                str = "Ожидание голосовой команды…";
            }
            textView.setText(str);
        }
        FrameLayout frameLayout3 = this.screenContainer;
        if (frameLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
            frameLayout3 = null;
        }
        TextView textView2 = (TextView) frameLayout3.findViewById(R.id.currentResponse);
        if (textView2 != null) {
            String str2 = this.latestResponse;
            if (StringsKt.isBlank(str2)) {
                str2 = "После обработки команды здесь появится ответ. Текст можно продолжить в Ассистенте.";
            }
            textView2.setText(str2);
        }
        FrameLayout frameLayout4 = this.screenContainer;
        if (frameLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
            frameLayout4 = null;
        }
        TextView textView3 = (TextView) frameLayout4.findViewById(R.id.liveTranscript);
        if (textView3 != null) {
            String str3 = this.latestResponse;
            if (StringsKt.isBlank(str3)) {
                str3 = "Дуплексный канал. Говорите без вейк-ворда.";
            }
            textView3.setText(str3);
        }
        FrameLayout frameLayout5 = this.screenContainer;
        if (frameLayout5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
            frameLayout5 = null;
        }
        TextView textView4 = (TextView) frameLayout5.findViewById(R.id.liveTranscriptSaid);
        if (textView4 != null) {
            String str4 = this.latestRequest;
            if (StringsKt.isBlank(str4)) {
                str4 = "ожидание сессии";
            }
            String it = str4;
            textView4.setText("«" + it + "»");
        }
        String str5 = this.latestTool;
        if (StringsKt.isBlank(str5)) {
            str5 = "";
        }
        String toolLine = str5;
        FrameLayout frameLayout6 = this.screenContainer;
        if (frameLayout6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
            frameLayout6 = null;
        }
        TextView textView5 = (TextView) frameLayout6.findViewById(R.id.liveToolChip);
        if (textView5 != null) {
            textView5.setText(StringsKt.isBlank(toolLine) ? "" : "[ TOOL: " + toolLine + " ]");
        }
        FrameLayout frameLayout7 = this.screenContainer;
        if (frameLayout7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
            frameLayout7 = null;
        }
        TextView textView6 = (TextView) frameLayout7.findViewById(R.id.liveIntent);
        if (textView6 != null) {
            textView6.setText(this.latestIntent);
        }
        FrameLayout frameLayout8 = this.screenContainer;
        if (frameLayout8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
        } else {
            frameLayout2 = frameLayout8;
        }
        TextView textView7 = (TextView) frameLayout2.findViewById(R.id.liveTool);
        if (textView7 != null) {
            textView7.setText(this.latestTool);
        }
    }

    private final String stateLabel(AssistantState state) {
        switch (WhenMappings.$EnumSwitchMapping$0[state.ordinal()]) {
            case 1:
                return "● ERROR";
            case 2:
                return "● THINKING";
            case 3:
                return "● EXECUTING";
            case 4:
                return "● SPEAKING";
            case 5:
                return "● LISTENING";
            case 6:
                return "● OFFLINE";
            case 7:
                return "● STARTING";
            case 8:
            case 9:
                return "● READY";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final String stateDetail(AssistantState state) {
        switch (WhenMappings.$EnumSwitchMapping$0[state.ordinal()]) {
            case 1:
                return "Требуется внимание к состоянию сервиса.";
            case 2:
                return "Обрабатываю запрос.";
            case 3:
                return "Выполняю действие через инструмент.";
            case 4:
                return "Озвучиваю ответ.";
            case 5:
                return "Слушаю голосовую команду.";
            case 6:
                return "Фоновый сервис не запущен.";
            case 7:
                return "Запускаю локальное ядро.";
            case 8:
            case 9:
                return "Готов к голосовой команде.";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final boolean setTorch(boolean on) {
        Object objM392constructorimpl;
        Object element$iv;
        try {
            Result.Companion companion = Result.INSTANCE;
            DashboardActivity $this$setTorch_u24lambda_u240 = this;
            Object systemService = $this$setTorch_u24lambda_u240.getSystemService("camera");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.hardware.camera2.CameraManager");
            CameraManager cm = (CameraManager) systemService;
            Object[] cameraIdList = cm.getCameraIdList();
            Intrinsics.checkNotNullExpressionValue(cameraIdList, "getCameraIdList(...)");
            Object[] $this$firstOrNull$iv = cameraIdList;
            int length = $this$firstOrNull$iv.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    element$iv = $this$firstOrNull$iv[i];
                    String cam = (String) element$iv;
                    if (Intrinsics.areEqual(cm.getCameraCharacteristics(cam).get(CameraCharacteristics.FLASH_INFO_AVAILABLE), (Object) true)) {
                        break;
                    }
                    i++;
                } else {
                    element$iv = null;
                    break;
                }
            }
            String id = (String) element$iv;
            if (id == null) {
                return false;
            }
            cm.setTorchMode(id, on);
            $this$setTorch_u24lambda_u240.torchOn = on;
            objM392constructorimpl = Result.m392constructorimpl(true);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = false;
        }
        return ((Boolean) objM392constructorimpl).booleanValue();
    }

    private final LinearLayout toolFlashRow() {
        LinearLayout $this$toolFlashRow_u24lambda_u240 = new LinearLayout(this);
        $this$toolFlashRow_u24lambda_u240.setOrientation(0);
        $this$toolFlashRow_u24lambda_u240.setGravity(16);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2);
        lp.topMargin = dp2px(8);
        $this$toolFlashRow_u24lambda_u240.setLayoutParams(lp);
        TextView $this$toolFlashRow_u24lambda_u240_u240 = new TextView(this);
        $this$toolFlashRow_u24lambda_u240_u240.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        $this$toolFlashRow_u24lambda_u240_u240.setText(this.torchOn ? "ФОНАРИК ВКЛ" : "ФОНАРИК ВЫКЛ");
        $this$toolFlashRow_u24lambda_u240_u240.setTextColor(ContextCompat.getColor(this, R.color.jarvis_cyan));
        $this$toolFlashRow_u24lambda_u240_u240.setTextSize(11.0f);
        $this$toolFlashRow_u24lambda_u240_u240.setTypeface(Typeface.MONOSPACE);
        $this$toolFlashRow_u24lambda_u240.addView($this$toolFlashRow_u24lambda_u240_u240);
        final JarvisToggle $this$toolFlashRow_u24lambda_u240_u241 = new JarvisToggle(this, null, 0, 6, null);
        $this$toolFlashRow_u24lambda_u240_u241.setLayoutParams(new LinearLayout.LayoutParams(dp2px(48), dp2px(28)));
        final Ref.BooleanRef guard = new Ref.BooleanRef();
        $this$toolFlashRow_u24lambda_u240_u241.setChecked(this.torchOn);
        $this$toolFlashRow_u24lambda_u240_u241.setOnCheckedChanged(new Function1() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda107
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DashboardActivity.toolFlashRow$lambda$0$1$0(guard, this, $this$toolFlashRow_u24lambda_u240_u241, ((Boolean) obj).booleanValue());
            }
        });
        $this$toolFlashRow_u24lambda_u240.addView($this$toolFlashRow_u24lambda_u240_u241);
        return $this$toolFlashRow_u24lambda_u240;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit toolFlashRow$lambda$0$1$0(Ref.BooleanRef $guard, DashboardActivity this$0, JarvisToggle $this_apply, boolean on) {
        if ($guard.element) {
            return Unit.INSTANCE;
        }
        if (ContextCompat.checkSelfPermission(this$0, "android.permission.CAMERA") != 0) {
            this$0.pendingTorch = Boolean.valueOf(on);
            $guard.element = true;
            $this_apply.setChecked(this$0.torchOn);
            $guard.element = false;
            this$0.cameraPermissionLauncher.launch("android.permission.CAMERA");
            return Unit.INSTANCE;
        }
        boolean ok = this$0.setTorch(on);
        if (!ok) {
            $guard.element = true;
            $this_apply.setChecked(this$0.torchOn);
            $guard.element = false;
            Toast.makeText(this$0, "Фонарик недоступен", 0).show();
        }
        return Unit.INSTANCE;
    }

    private final SeekBar toolVolumeSlider() {
        Object systemService = getSystemService("audio");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
        final AudioManager audio = (AudioManager) systemService;
        final int max = RangesKt.coerceAtLeast(audio.getStreamMaxVolume(3), 1);
        int cur = audio.getStreamVolume(3);
        SeekBar $this$toolVolumeSlider_u24lambda_u240 = new SeekBar(this);
        $this$toolVolumeSlider_u24lambda_u240.setMax(100);
        $this$toolVolumeSlider_u24lambda_u240.setProgress((cur * 100) / max);
        $this$toolVolumeSlider_u24lambda_u240.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#62E9F4")));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2);
        lp.topMargin = dp2px(8);
        $this$toolVolumeSlider_u24lambda_u240.setLayoutParams(lp);
        $this$toolVolumeSlider_u24lambda_u240.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.jarvis.app.DashboardActivity$toolVolumeSlider$1$1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int value, boolean fromUser) {
                if (fromUser) {
                    if (seekBar != null) {
                        HudFeedback.INSTANCE.tick(seekBar);
                    }
                    audio.setStreamVolume(3, RangesKt.coerceIn((int) ((max * value) / 100.0f), 0, max), 0);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        return $this$toolVolumeSlider_u24lambda_u240;
    }

    private final LinearLayout toolBrightnessBlock() {
        Object objM392constructorimpl;
        LinearLayout $this$toolBrightnessBlock_u24lambda_u240 = new LinearLayout(this);
        $this$toolBrightnessBlock_u24lambda_u240.setOrientation(1);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2);
        lp.topMargin = dp2px(8);
        $this$toolBrightnessBlock_u24lambda_u240.setLayoutParams(lp);
        if (!Settings.System.canWrite(this)) {
            TextView $this$toolBrightnessBlock_u24lambda_u240_u240 = new TextView(this);
            $this$toolBrightnessBlock_u24lambda_u240_u240.setText("РАЗРЕШИТЬ ЯРКОСТЬ");
            $this$toolBrightnessBlock_u24lambda_u240_u240.setGravity(17);
            $this$toolBrightnessBlock_u24lambda_u240_u240.setTextColor(ContextCompat.getColor(this, R.color.jarvis_cyan));
            $this$toolBrightnessBlock_u24lambda_u240_u240.setTextSize(11.0f);
            $this$toolBrightnessBlock_u24lambda_u240_u240.setTypeface(Typeface.MONOSPACE);
            $this$toolBrightnessBlock_u24lambda_u240_u240.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_hud_panel));
            $this$toolBrightnessBlock_u24lambda_u240_u240.setPadding(dp2px(8), dp2px(8), dp2px(8), dp2px(8));
            $this$toolBrightnessBlock_u24lambda_u240_u240.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda106
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.toolBrightnessBlock$lambda$0$0$0(this.f$0, view);
                }
            });
            $this$toolBrightnessBlock_u24lambda_u240.addView($this$toolBrightnessBlock_u24lambda_u240_u240);
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(Integer.valueOf(Settings.System.getInt(getContentResolver(), "screen_brightness")));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = 128;
        }
        int current = ((Number) objM392constructorimpl).intValue();
        SeekBar $this$toolBrightnessBlock_u24lambda_u240_u242 = new SeekBar(this);
        $this$toolBrightnessBlock_u24lambda_u240_u242.setMax(100);
        $this$toolBrightnessBlock_u24lambda_u240_u242.setProgress(RangesKt.coerceIn((current * 100) / 255, 0, 100));
        $this$toolBrightnessBlock_u24lambda_u240_u242.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#62E9F4")));
        $this$toolBrightnessBlock_u24lambda_u240_u242.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.jarvis.app.DashboardActivity$toolBrightnessBlock$1$2$1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int value, boolean fromUser) {
                if (fromUser) {
                    if (seekBar != null) {
                        HudFeedback.INSTANCE.tick(seekBar);
                    }
                    int level = RangesKt.coerceIn((value * 255) / 100, 1, 255);
                    if (Settings.System.canWrite(this.this$0)) {
                        Settings.System.putInt(this.this$0.getContentResolver(), "screen_brightness", level);
                    }
                    WindowManager.LayoutParams attr = this.this$0.getWindow().getAttributes();
                    attr.screenBrightness = level / 255.0f;
                    this.this$0.getWindow().setAttributes(attr);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        $this$toolBrightnessBlock_u24lambda_u240.addView($this$toolBrightnessBlock_u24lambda_u240_u242);
        return $this$toolBrightnessBlock_u24lambda_u240;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void toolBrightnessBlock$lambda$0$0$0(DashboardActivity this$0, View it) {
        this$0.startActivity(new Intent("android.settings.action.MANAGE_WRITE_SETTINGS", Uri.parse("package:" + this$0.getPackageName())));
    }

    private final LinearLayout toolTimerRow() {
        LinearLayout $this$toolTimerRow_u24lambda_u240 = new LinearLayout(this);
        int i = 0;
        $this$toolTimerRow_u24lambda_u240.setOrientation(0);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2);
        lp.topMargin = dp2px(8);
        $this$toolTimerRow_u24lambda_u240.setLayoutParams(lp);
        Iterable $this$forEachIndexed$iv = CollectionsKt.listOf((Object[]) new Integer[]{1, 5, 10});
        int index$iv = 0;
        for (Object item$iv : $this$forEachIndexed$iv) {
            int index$iv2 = index$iv + 1;
            if (index$iv < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            final int minutes = ((Number) item$iv).intValue();
            TextView $this$toolTimerRow_u24lambda_u240_u240_u240 = new TextView(this);
            $this$toolTimerRow_u24lambda_u240_u240_u240.setText("+" + minutes + " МИН");
            $this$toolTimerRow_u24lambda_u240_u240_u240.setGravity(17);
            $this$toolTimerRow_u24lambda_u240_u240_u240.setTextColor(ContextCompat.getColor(this, R.color.jarvis_cyan));
            $this$toolTimerRow_u24lambda_u240_u240_u240.setTextSize(10.0f);
            $this$toolTimerRow_u24lambda_u240_u240_u240.setTypeface(Typeface.MONOSPACE);
            $this$toolTimerRow_u24lambda_u240_u240_u240.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_hud_panel));
            int i2 = i;
            LinearLayout.LayoutParams lp2 = lp;
            Iterable $this$forEachIndexed$iv2 = $this$forEachIndexed$iv;
            $this$toolTimerRow_u24lambda_u240_u240_u240.setPadding(dp2px(6), dp2px(8), dp2px(6), dp2px(8));
            LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(0, -2, 1.0f);
            if (index$iv < 2) {
                lp3.setMarginEnd(dp2px(8));
            }
            $this$toolTimerRow_u24lambda_u240_u240_u240.setLayoutParams(lp3);
            $this$toolTimerRow_u24lambda_u240_u240_u240.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda144
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.toolTimerRow$lambda$0$0$0$0(this.f$0, minutes, view);
                }
            });
            $this$toolTimerRow_u24lambda_u240.addView($this$toolTimerRow_u24lambda_u240_u240_u240);
            index$iv = index$iv2;
            lp = lp2;
            i = i2;
            $this$forEachIndexed$iv = $this$forEachIndexed$iv2;
        }
        return $this$toolTimerRow_u24lambda_u240;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void toolTimerRow$lambda$0$0$0$0(final DashboardActivity this$0, final int $minutes, View it) {
        this$0.uiTimerHandler.postDelayed(new Runnable() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda145
            @Override // java.lang.Runnable
            public final void run() {
                DashboardActivity.toolTimerRow$lambda$0$0$0$0$0(this.f$0, $minutes);
            }
        }, ((long) $minutes) * 60000);
        Toast.makeText(this$0, "Таймер на " + $minutes + " мин", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void toolTimerRow$lambda$0$0$0$0$0(DashboardActivity this$0, int $minutes) {
        Object systemService = this$0.getSystemService("vibrator");
        Vibrator vibrator = systemService instanceof Vibrator ? (Vibrator) systemService : null;
        if (vibrator != null) {
            try {
                vibrator.vibrate(VibrationEffect.createOneShot(700L, -1));
            } catch (SecurityException e) {
                Log.w("Jarvis.Dashboard", "vibrate: нет разрешения VIBRATE", e);
            } catch (Exception e2) {
                Log.w("Jarvis.Dashboard", "vibrate failed: " + e2.getMessage(), e2);
            }
        }
        Toast.makeText(this$0, "Таймер " + $minutes + " мин истёк", 1).show();
    }

    private final void setupCommandsScreen(View screen) {
        final LinearLayout list = (LinearLayout) screen.findViewById(R.id.commandsList);
        if (list == null) {
            return;
        }
        LinearLayout chips = (LinearLayout) screen.findViewById(R.id.commandsCategoryChips);
        final TextView search = (TextView) screen.findViewById(R.id.commandsSearch);
        if (search != null) {
            search.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda9
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupCommandsScreen$lambda$11(this.f$0, search, list, view);
                }
            });
        }
        setupCommandsScreen$renderChips(chips, this, list);
        setupCommandsScreen$renderList(list, this);
    }

    private static final int setupCommandsScreen$riskColor(AssistantCapabilities.RiskTag r) {
        switch (WhenMappings.$EnumSwitchMapping$1[r.ordinal()]) {
            case 1:
                return Color.parseColor("#62D9DF");
            case 2:
                return Color.parseColor("#E0AD55");
            case 3:
                return Color.parseColor("#E0AD55");
            case 4:
                return Color.parseColor("#E46B58");
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX WARN: Code duplicated, block: B:44:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:46:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:88:0x00f4 A[SYNTHETIC] */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private static final void setupCommandsScreen$renderList(LinearLayout list, final DashboardActivity this$0) {
        Iterable $this$filter$iv;
        int $i$f$filter;
        Iterable $this$filterTo$iv$iv;
        boolean textOk;
        boolean z;
        boolean z2;
        list.removeAllViews();
        Iterable $this$filter$iv2 = AssistantCapabilities.INSTANCE.getALL();
        int $i$f$filter2 = 0;
        Collection destination$iv$iv = new ArrayList();
        Iterable $this$filterTo$iv$iv2 = $this$filter$iv2;
        for (Object element$iv$iv : $this$filterTo$iv$iv2) {
            AssistantCapabilities.Command cmd = (AssistantCapabilities.Command) element$iv$iv;
            boolean catOk = this$0.commandsCategory == null || Intrinsics.areEqual(cmd.getCategory(), this$0.commandsCategory);
            String q = this$0.commandsFilter;
            if (StringsKt.isBlank(q) || StringsKt.contains((CharSequence) cmd.getTitle(), (CharSequence) q, true) || StringsKt.contains((CharSequence) cmd.getDescription(), (CharSequence) q, true)) {
                $this$filter$iv = $this$filter$iv2;
                $i$f$filter = $i$f$filter2;
                $this$filterTo$iv$iv = $this$filterTo$iv$iv2;
            } else {
                Iterable $this$any$iv = cmd.getExamples();
                if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
                    $this$filter$iv = $this$filter$iv2;
                    $i$f$filter = $i$f$filter2;
                    $this$filterTo$iv$iv = $this$filterTo$iv$iv2;
                    z2 = false;
                } else {
                    Iterator it = $this$any$iv.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            $this$filter$iv = $this$filter$iv2;
                            $i$f$filter = $i$f$filter2;
                            $this$filterTo$iv$iv = $this$filterTo$iv$iv2;
                            z2 = false;
                            break;
                        }
                        Object element$iv = it.next();
                        String it2 = (String) element$iv;
                        $this$filter$iv = $this$filter$iv2;
                        $i$f$filter = $i$f$filter2;
                        $this$filterTo$iv$iv = $this$filterTo$iv$iv2;
                        if (StringsKt.contains((CharSequence) it2, (CharSequence) q, true)) {
                            z2 = true;
                            break;
                        } else {
                            $this$filter$iv2 = $this$filter$iv;
                            $i$f$filter2 = $i$f$filter;
                            $this$filterTo$iv$iv2 = $this$filterTo$iv$iv;
                        }
                    }
                }
                if (!z2 && !StringsKt.contains((CharSequence) cmd.getCategory(), (CharSequence) q, true)) {
                    textOk = false;
                }
                if (catOk || !textOk) {
                    z = false;
                } else {
                    z = true;
                }
                if (z) {
                    destination$iv$iv.add(element$iv$iv);
                }
                $this$filter$iv2 = $this$filter$iv;
                $i$f$filter2 = $i$f$filter;
                $this$filterTo$iv$iv2 = $this$filterTo$iv$iv;
            }
            textOk = true;
            if (catOk) {
                z = false;
            } else {
                z = false;
            }
            if (z) {
                destination$iv$iv.add(element$iv$iv);
            }
            $this$filter$iv2 = $this$filter$iv;
            $i$f$filter2 = $i$f$filter;
            $this$filterTo$iv$iv2 = $this$filterTo$iv$iv;
        }
        List filtered = (List) destination$iv$iv;
        String str = "#9DA5AE";
        if (filtered.isEmpty()) {
            TextView $this$setupCommandsScreen_u24renderList_u24lambda_u241 = new TextView(this$0);
            $this$setupCommandsScreen_u24renderList_u24lambda_u241.setText("Ничего не найдено.");
            $this$setupCommandsScreen_u24renderList_u24lambda_u241.setTextColor(Color.parseColor("#9DA5AE"));
            $this$setupCommandsScreen_u24renderList_u24lambda_u241.setTextSize(12.0f);
            list.addView($this$setupCommandsScreen_u24renderList_u24lambda_u241);
            return;
        }
        String lastCat = "";
        Iterator it3 = filtered.iterator();
        while (it3.hasNext()) {
            AssistantCapabilities.Command cmd2 = (AssistantCapabilities.Command) it3.next();
            if (!Intrinsics.areEqual(cmd2.getCategory(), lastCat)) {
                lastCat = cmd2.getCategory();
                TextView $this$setupCommandsScreen_u24renderList_u24lambda_u242 = new TextView(this$0);
                String upperCase = cmd2.getCategory().toUpperCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                $this$setupCommandsScreen_u24renderList_u24lambda_u242.setText(upperCase);
                $this$setupCommandsScreen_u24renderList_u24lambda_u242.setTextColor(Color.parseColor("#626B76"));
                $this$setupCommandsScreen_u24renderList_u24lambda_u242.setTextSize(11.0f);
                $this$setupCommandsScreen_u24renderList_u24lambda_u242.setLetterSpacing(0.06f);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-2, -2);
                lp.topMargin = this$0.dp2px(14);
                lp.bottomMargin = this$0.dp2px(8);
                $this$setupCommandsScreen_u24renderList_u24lambda_u242.setLayoutParams(lp);
                list.addView($this$setupCommandsScreen_u24renderList_u24lambda_u242);
            }
            LinearLayout card = new LinearLayout(this$0);
            card.setOrientation(1);
            card.setBackground(ContextCompat.getDrawable(this$0, R.drawable.bg_hud_panel));
            card.setPadding(this$0.dp2px(14), this$0.dp2px(12), this$0.dp2px(14), this$0.dp2px(12));
            LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(-1, -2);
            lp2.bottomMargin = this$0.dp2px(10);
            card.setLayoutParams(lp2);
            LinearLayout head = new LinearLayout(this$0);
            head.setOrientation(0);
            head.setGravity(16);
            TextView $this$setupCommandsScreen_u24renderList_u24lambda_u245 = new TextView(this$0);
            $this$setupCommandsScreen_u24renderList_u24lambda_u245.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
            $this$setupCommandsScreen_u24renderList_u24lambda_u245.setText(cmd2.getTitle());
            $this$setupCommandsScreen_u24renderList_u24lambda_u245.setTextColor(Color.parseColor("#F4F0E7"));
            $this$setupCommandsScreen_u24renderList_u24lambda_u245.setTextSize(14.0f);
            $this$setupCommandsScreen_u24renderList_u24lambda_u245.setTypeface($this$setupCommandsScreen_u24renderList_u24lambda_u245.getTypeface(), 1);
            head.addView($this$setupCommandsScreen_u24renderList_u24lambda_u245);
            TextView $this$setupCommandsScreen_u24renderList_u24lambda_u246 = new TextView(this$0);
            $this$setupCommandsScreen_u24renderList_u24lambda_u246.setText(cmd2.getRisk().name());
            $this$setupCommandsScreen_u24renderList_u24lambda_u246.setTextColor(setupCommandsScreen$riskColor(cmd2.getRisk()));
            $this$setupCommandsScreen_u24renderList_u24lambda_u246.setTextSize(9.0f);
            $this$setupCommandsScreen_u24renderList_u24lambda_u246.setTypeface(Typeface.MONOSPACE);
            List filtered2 = filtered;
            $this$setupCommandsScreen_u24renderList_u24lambda_u246.setPadding(this$0.dp2px(8), this$0.dp2px(3), this$0.dp2px(8), this$0.dp2px(3));
            $this$setupCommandsScreen_u24renderList_u24lambda_u246.setBackground(ContextCompat.getDrawable(this$0, R.drawable.bg_tag_cyan));
            head.addView($this$setupCommandsScreen_u24renderList_u24lambda_u246);
            card.addView(head);
            TextView $this$setupCommandsScreen_u24renderList_u24lambda_u247 = new TextView(this$0);
            $this$setupCommandsScreen_u24renderList_u24lambda_u247.setText(cmd2.getDescription() + (StringsKt.isBlank(cmd2.getNeeds()) ? "" : "\nНужно: " + cmd2.getNeeds()));
            $this$setupCommandsScreen_u24renderList_u24lambda_u247.setTextColor(Color.parseColor(str));
            $this$setupCommandsScreen_u24renderList_u24lambda_u247.setTextSize(12.0f);
            LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(-1, -2);
            lp3.topMargin = this$0.dp2px(6);
            $this$setupCommandsScreen_u24renderList_u24lambda_u247.setLayoutParams(lp3);
            card.addView($this$setupCommandsScreen_u24renderList_u24lambda_u247);
            String id = cmd2.getId();
            switch (id.hashCode()) {
                case -1183073498:
                    if (id.equals("flashlight")) {
                        card.addView(this$0.toolFlashRow());
                    }
                    break;
                case -810883302:
                    if (id.equals("volume")) {
                        card.addView(this$0.toolVolumeSlider());
                    }
                    break;
                case 110364485:
                    if (id.equals("timer")) {
                        card.addView(this$0.toolTimerRow());
                    }
                    break;
                case 648162385:
                    if (id.equals("brightness")) {
                        card.addView(this$0.toolBrightnessBlock());
                    }
                    break;
            }
            Iterable $this$forEach$iv = cmd2.getExamples();
            int $i$f$forEach = 0;
            for (Object element$iv2 : $this$forEach$iv) {
                final String ex = (String) element$iv2;
                LinearLayout $this$setupCommandsScreen_u24renderList_u24lambda_u248_u240 = new LinearLayout(this$0);
                Iterable $this$forEach$iv2 = $this$forEach$iv;
                $this$setupCommandsScreen_u24renderList_u24lambda_u248_u240.setOrientation(0);
                $this$setupCommandsScreen_u24renderList_u24lambda_u248_u240.setGravity(16);
                String lastCat2 = lastCat;
                LinearLayout.LayoutParams lp4 = new LinearLayout.LayoutParams(-1, -2);
                lp4.topMargin = this$0.dp2px(8);
                $this$setupCommandsScreen_u24renderList_u24lambda_u248_u240.setLayoutParams(lp4);
                TextView $this$setupCommandsScreen_u24renderList_u24lambda_u248_u241 = new TextView(this$0);
                AssistantCapabilities.Command cmd3 = cmd2;
                $this$setupCommandsScreen_u24renderList_u24lambda_u248_u241.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                $this$setupCommandsScreen_u24renderList_u24lambda_u248_u241.setText("«" + ex + "»");
                $this$setupCommandsScreen_u24renderList_u24lambda_u248_u241.setTextColor(Color.parseColor("#E0AD55"));
                $this$setupCommandsScreen_u24renderList_u24lambda_u248_u241.setTextSize(12.0f);
                $this$setupCommandsScreen_u24renderList_u24lambda_u248_u241.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda53
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        DashboardActivity.setupCommandsScreen$renderList$lambda$8$1$0(this.f$0, ex, view);
                    }
                });
                $this$setupCommandsScreen_u24renderList_u24lambda_u248_u240.addView($this$setupCommandsScreen_u24renderList_u24lambda_u248_u241);
                TextView $this$setupCommandsScreen_u24renderList_u24lambda_u248_u242 = new TextView(this$0);
                $this$setupCommandsScreen_u24renderList_u24lambda_u248_u242.setText("RUN");
                $this$setupCommandsScreen_u24renderList_u24lambda_u248_u242.setTextColor(Color.parseColor("#62E9F4"));
                $this$setupCommandsScreen_u24renderList_u24lambda_u248_u242.setTextSize(11.0f);
                $this$setupCommandsScreen_u24renderList_u24lambda_u248_u242.setTypeface(Typeface.MONOSPACE);
                int iDp2px = this$0.dp2px(12);
                int iDp2px2 = this$0.dp2px(6);
                int $i$f$forEach2 = $i$f$forEach;
                int $i$f$forEach3 = this$0.dp2px(12);
                $this$setupCommandsScreen_u24renderList_u24lambda_u248_u242.setPadding(iDp2px, iDp2px2, $i$f$forEach3, this$0.dp2px(6));
                $this$setupCommandsScreen_u24renderList_u24lambda_u248_u242.setBackground(ContextCompat.getDrawable(this$0, R.drawable.bg_hud_panel));
                $this$setupCommandsScreen_u24renderList_u24lambda_u248_u242.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda54
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.runVoiceCommand(ex);
                    }
                });
                $this$setupCommandsScreen_u24renderList_u24lambda_u248_u240.addView($this$setupCommandsScreen_u24renderList_u24lambda_u248_u242);
                card.addView($this$setupCommandsScreen_u24renderList_u24lambda_u248_u240);
                cmd2 = cmd3;
                str = str;
                head = head;
                $this$forEach$iv = $this$forEach$iv2;
                lastCat = lastCat2;
                it3 = it3;
                $i$f$forEach = $i$f$forEach2;
            }
            list.addView(card);
            filtered = filtered2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupCommandsScreen$renderList$lambda$8$1$0(DashboardActivity this$0, String $ex, View it) {
        Object systemService = this$0.getSystemService("clipboard");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
        ClipboardManager cm = (ClipboardManager) systemService;
        cm.setPrimaryClip(ClipData.newPlainText("jarvis", $ex));
        Toast.makeText(this$0, "Скопировано. Скажи это после активации.", 0).show();
    }

    private static final void setupCommandsScreen$renderChips(final LinearLayout chips, final DashboardActivity this$0, final LinearLayout list) {
        if (chips != null) {
            chips.removeAllViews();
        }
        List listListOf = CollectionsKt.listOf(TuplesKt.to(null, "Все"));
        Iterable $this$map$iv = AssistantCapabilities.INSTANCE.byCategory().keySet();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            destination$iv$iv.add(TuplesKt.to(it, it));
        }
        List cats = CollectionsKt.plus((Collection) listListOf, destination$iv$iv);
        Iterable $this$forEach$iv = CollectionsKt.chunked(cats, 2);
        int $i$f$forEach = 0;
        Iterator it2 = $this$forEach$iv.iterator();
        while (it2.hasNext()) {
            Object element$iv = it2.next();
            List pair = (List) element$iv;
            int i = 0;
            LinearLayout row = new LinearLayout(this$0);
            row.setOrientation(0);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2);
            lp.bottomMargin = this$0.dp2px(8);
            row.setLayoutParams(lp);
            List $this$forEachIndexed$iv = pair;
            int index$iv = 0;
            for (Object item$iv : $this$forEachIndexed$iv) {
                int index$iv2 = index$iv + 1;
                if (index$iv < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Pair pair2 = (Pair) item$iv;
                final String cat = (String) pair2.component1();
                String label = (String) pair2.component2();
                List cats2 = cats;
                TextView $this$setupCommandsScreen_u24renderChips_u24lambda_u2410_u241_u240 = new TextView(this$0);
                Iterable $this$forEach$iv2 = $this$forEach$iv;
                $this$setupCommandsScreen_u24renderChips_u24lambda_u2410_u241_u240.setText(label);
                $this$setupCommandsScreen_u24renderChips_u24lambda_u2410_u241_u240.setGravity(17);
                $this$setupCommandsScreen_u24renderChips_u24lambda_u2410_u241_u240.setTextSize(11.0f);
                $this$setupCommandsScreen_u24renderChips_u24lambda_u2410_u241_u240.setMinHeight(this$0.dp2px(36));
                int $i$f$forEach2 = $i$f$forEach;
                int $i$f$forEach3 = this$0.dp2px(8);
                Iterator it3 = it2;
                Object element$iv2 = element$iv;
                int i2 = i;
                $this$setupCommandsScreen_u24renderChips_u24lambda_u2410_u241_u240.setPadding($i$f$forEach3, this$0.dp2px(8), this$0.dp2px(8), this$0.dp2px(8));
                boolean active = Intrinsics.areEqual(this$0.commandsCategory, cat);
                $this$setupCommandsScreen_u24renderChips_u24lambda_u2410_u241_u240.setTextColor(Color.parseColor(active ? "#62E9F4" : "#8F9BAE"));
                $this$setupCommandsScreen_u24renderChips_u24lambda_u2410_u241_u240.setBackground(ContextCompat.getDrawable(this$0, active ? R.drawable.bg_fui_active : R.drawable.bg_hud_panel));
                LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(0, -2, 1.0f);
                if (index$iv == 0) {
                    lp2.setMarginEnd(this$0.dp2px(8));
                }
                $this$setupCommandsScreen_u24renderChips_u24lambda_u2410_u241_u240.setLayoutParams(lp2);
                $this$setupCommandsScreen_u24renderChips_u24lambda_u2410_u241_u240.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda118
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        DashboardActivity.setupCommandsScreen$renderChips$lambda$10$1$0$0(this.f$0, cat, chips, list, view);
                    }
                });
                row.addView($this$setupCommandsScreen_u24renderChips_u24lambda_u2410_u241_u240);
                index$iv = index$iv2;
                cats = cats2;
                $this$forEach$iv = $this$forEach$iv2;
                $i$f$forEach = $i$f$forEach2;
                it2 = it3;
                element$iv = element$iv2;
                i = i2;
            }
            List cats3 = cats;
            Iterable $this$forEach$iv3 = $this$forEach$iv;
            int $i$f$forEach4 = $i$f$forEach;
            Iterator it4 = it2;
            if (pair.size() == 1) {
                View $this$setupCommandsScreen_u24renderChips_u24lambda_u2410_u242 = new View(this$0);
                $this$setupCommandsScreen_u24renderChips_u24lambda_u2410_u242.setLayoutParams(new LinearLayout.LayoutParams(0, 0, 1.0f));
                row.addView($this$setupCommandsScreen_u24renderChips_u24lambda_u2410_u242);
            }
            if (chips != null) {
                chips.addView(row);
            }
            cats = cats3;
            $this$forEach$iv = $this$forEach$iv3;
            $i$f$forEach = $i$f$forEach4;
            it2 = it4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupCommandsScreen$renderChips$lambda$10$1$0$0(DashboardActivity this$0, String $cat, LinearLayout $chips, LinearLayout $list, View it) {
        this$0.commandsCategory = $cat;
        setupCommandsScreen$renderChips($chips, this$0, $list);
        setupCommandsScreen$renderList($list, this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupCommandsScreen$lambda$11(final DashboardActivity this$0, final TextView $search, final LinearLayout $list, View it) {
        final EditText $this$setupCommandsScreen_u24lambda_u2411_u240 = new EditText(this$0);
        $this$setupCommandsScreen_u24lambda_u2411_u240.setHint("Поиск…");
        $this$setupCommandsScreen_u24lambda_u2411_u240.setText(this$0.commandsFilter);
        new AlertDialog.Builder(this$0).setTitle("Поиск команд").setView($this$setupCommandsScreen_u24lambda_u2411_u240).setNegativeButton("Сброс", new DialogInterface.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DashboardActivity.setupCommandsScreen$lambda$11$1(this.f$0, $search, $list, dialogInterface, i);
            }
        }).setPositiveButton("Найти", new DialogInterface.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda65
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DashboardActivity.setupCommandsScreen$lambda$11$2(this.f$0, $this$setupCommandsScreen_u24lambda_u2411_u240, $search, $list, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupCommandsScreen$lambda$11$1(DashboardActivity this$0, TextView $search, LinearLayout $list, DialogInterface dialogInterface, int i) {
        this$0.commandsFilter = "";
        $search.setText("⌕  Найти команду…");
        setupCommandsScreen$renderList($list, this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupCommandsScreen$lambda$11$2(DashboardActivity this$0, EditText $input, TextView $search, LinearLayout $list, DialogInterface dialogInterface, int i) {
        String str;
        this$0.commandsFilter = StringsKt.trim((CharSequence) $input.getText().toString()).toString();
        if (!StringsKt.isBlank(this$0.commandsFilter)) {
            str = "⌕  " + this$0.commandsFilter;
        }
        $search.setText(str);
        setupCommandsScreen$renderList($list, this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void runVoiceCommand(String phrase) {
        String string = StringsKt.trim((CharSequence) StringsKt.removePrefix(StringsKt.removePrefix(phrase, (CharSequence) "джарвис,"), (CharSequence) "джарвис")).toString();
        if (StringsKt.isBlank(string)) {
            string = phrase;
        }
        String text = string;
        if (!VoiceController.INSTANCE.isRunning()) {
            activateAssistant$default(this, false, 1, null);
            Toast.makeText(this, "Запускаю ассистента… повтори через секунду или скажи фразу вслух.", 1).show();
        }
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AnonymousClass1(text, this, null), 3, null);
    }

    /* JADX INFO: renamed from: com.jarvis.app.DashboardActivity$runVoiceCommand$1, reason: invalid class name */
    /* JADX INFO: compiled from: DashboardActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.DashboardActivity$runVoiceCommand$1", f = "DashboardActivity.kt", i = {}, l = {2475}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $text;
        int label;
        final /* synthetic */ DashboardActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, DashboardActivity dashboardActivity, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$text = str;
            this.this$0 = dashboardActivity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$text, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (DelayKt.delay(400L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            if (VoiceController.INSTANCE.isRunning()) {
                VoiceController.INSTANCE.notifyRequest$app_debug(new UserRequest(this.$text, null, null, 0L, null, 30, null));
                this.this$0.showScreen(0);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void setupSettingsToggles(final View screen) {
        final DashboardActivity dashboardActivity;
        final DashboardActivity dashboardActivity2;
        final List list;
        final List list2;
        String str;
        final int i;
        final int i2;
        final List list3;
        final List list4;
        final View viewFindViewById = screen.findViewById(R.id.settingsMainPanel);
        final View viewFindViewById2 = screen.findViewById(R.id.modelsPanel);
        final View viewFindViewById3 = screen.findViewById(R.id.companionPanel);
        final TextView textView = (TextView) screen.findViewById(R.id.tabSettingsMain);
        final TextView textView2 = (TextView) screen.findViewById(R.id.tabSettingsModels);
        final TextView textView3 = (TextView) screen.findViewById(R.id.tabSettingsCompanion);
        if (textView != null) {
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda142
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupSettingsToggles$showSettingsTab(viewFindViewById, viewFindViewById2, viewFindViewById3, textView, textView2, textView3, this, screen, "main");
                }
            });
            Unit unit = Unit.INSTANCE;
        }
        if (textView2 != 0) {
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda64
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupSettingsToggles$showSettingsTab(viewFindViewById, viewFindViewById2, viewFindViewById3, textView, textView2, textView3, this, screen, "models");
                }
            });
            Unit unit2 = Unit.INSTANCE;
        }
        final TextView textView4 = textView3;
        if (textView4 != null) {
            textView4.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda70
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupSettingsToggles$showSettingsTab(viewFindViewById, viewFindViewById2, viewFindViewById3, textView, textView2, textView4, this, screen, "companion");
                }
            });
            Unit unit3 = Unit.INSTANCE;
        }
        String str2 = this.pendingSettingsTab;
        if (str2 == null) {
            str2 = "main";
        }
        String str3 = str2;
        this.pendingSettingsTab = null;
        setupSettingsToggles$showSettingsTab(viewFindViewById, viewFindViewById2, viewFindViewById3, textView, textView2, textView4, this, screen, str3);
        Object obj = textView2;
        TextView textView5 = (TextView) screen.findViewById(R.id.btnOpenUsageGuide);
        if (textView5 != null) {
            textView5.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda71
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupSettingsToggles$lambda$3(this.f$0, view);
                }
            });
            Unit unit4 = Unit.INSTANCE;
        }
        TextView textView6 = (TextView) screen.findViewById(R.id.btnSwitchModern);
        if (textView6 != null) {
            textView6.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda72
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupSettingsToggles$lambda$4(this.f$0, view);
                }
            });
            Unit unit5 = Unit.INSTANCE;
        }
        JarvisToggle jarvisToggle = (JarvisToggle) screen.findViewById(R.id.toggleHudSfx);
        if (jarvisToggle != null) {
            jarvisToggle.setChecked(JarvisSettings.INSTANCE.hudSfx((Context) this));
            final DashboardActivity dashboardActivity3 = this;
            jarvisToggle.setOnCheckedChanged(new Function1() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda73
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return DashboardActivity.setupSettingsToggles$lambda$5$0(this.f$0, ((Boolean) obj2).booleanValue());
                }
            });
            dashboardActivity = dashboardActivity3;
        } else {
            dashboardActivity = this;
        }
        JarvisToggle jarvisToggle2 = (JarvisToggle) screen.findViewById(R.id.toggleAdaptive);
        if (jarvisToggle2 != null) {
            jarvisToggle2.setChecked(JarvisSettings.INSTANCE.adaptiveListening(dashboardActivity));
            jarvisToggle2.setOnCheckedChanged(new Function1() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda74
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return DashboardActivity.setupSettingsToggles$lambda$6$0(this.f$0, ((Boolean) obj2).booleanValue());
                }
            });
        }
        JarvisToggle jarvisToggle3 = (JarvisToggle) screen.findViewById(R.id.toggleLowPower);
        if (jarvisToggle3 != null) {
            jarvisToggle3.setChecked(JarvisSettings.INSTANCE.lowPowerMode(dashboardActivity));
            jarvisToggle3.setOnCheckedChanged(new Function1() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda75
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return DashboardActivity.setupSettingsToggles$lambda$7$0(this.f$0, ((Boolean) obj2).booleanValue());
                }
            });
        }
        JarvisToggle jarvisToggle4 = (JarvisToggle) screen.findViewById(R.id.toggleSensitive);
        if (jarvisToggle4 != null) {
            jarvisToggle4.setChecked(JarvisSettings.INSTANCE.confirmSensitive(dashboardActivity));
            jarvisToggle4.setOnCheckedChanged(new Function1() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda77
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return DashboardActivity.setupSettingsToggles$lambda$8$0(this.f$0, ((Boolean) obj2).booleanValue());
                }
            });
        }
        JarvisToggle jarvisToggle5 = (JarvisToggle) screen.findViewById(R.id.toggleWake);
        if (jarvisToggle5 != null) {
            jarvisToggle5.setChecked(JarvisSettings.INSTANCE.wakeEnabled(dashboardActivity));
            jarvisToggle5.setOnCheckedChanged(new Function1() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda78
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return DashboardActivity.setupSettingsToggles$lambda$9$0(this.f$0, this, ((Boolean) obj2).booleanValue());
                }
            });
        }
        TextView textView7 = (TextView) screen.findViewById(R.id.wakeEngineStatus);
        if (textView7 != null) {
            textView7.setText("Движок: openWakeWord · «Джарвис» (ONNX KWS)");
            Unit unit6 = Unit.INSTANCE;
        }
        boolean z = true;
        List listListOf = CollectionsKt.listOf((Object[]) new Integer[]{0, 1, 2});
        List listListOf2 = CollectionsKt.listOf((Object[]) new TextView[]{screen.findViewById(R.id.wakeLow), screen.findViewById(R.id.wakeMedium), screen.findViewById(R.id.wakeHigh)});
        setupSettingsToggles$renderWakeLevels(dashboardActivity, listListOf2, this, listListOf);
        final int i3 = 0;
        for (Object obj2 : listListOf2) {
            int i4 = i3 + 1;
            if (i3 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str4 = str3;
            TextView textView8 = (TextView) obj2;
            if (textView8 != null) {
                list3 = listListOf2;
                list4 = listListOf;
                final DashboardActivity dashboardActivity4 = dashboardActivity;
                textView8.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda153
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        DashboardActivity.setupSettingsToggles$lambda$11$0(this.f$0, list4, i3, this, list3, view);
                    }
                });
                Unit unit7 = Unit.INSTANCE;
            } else {
                list3 = listListOf2;
                list4 = listListOf;
            }
            z = z;
            i3 = i4;
            str3 = str4;
            obj = obj;
            listListOf2 = list3;
            listListOf = list4;
            textView4 = textView4;
        }
        boolean z2 = z;
        final TextView textView9 = (TextView) screen.findViewById(R.id.brainThreshold);
        JarvisSlider jarvisSlider = (JarvisSlider) screen.findViewById(R.id.sliderThreshold);
        jarvisSlider.setValue(setupSettingsToggles$thresholdToSlider(JarvisSettings.INSTANCE.brainConfidenceThreshold(dashboardActivity)));
        if (textView9 != null) {
            String str5 = String.format("%.2f", Arrays.copyOf(new Object[]{Float.valueOf(setupSettingsToggles$sliderToThreshold(jarvisSlider.getValue()))}, z2 ? 1 : 0));
            Intrinsics.checkNotNullExpressionValue(str5, "format(...)");
            textView9.setText(str5);
            Unit unit8 = Unit.INSTANCE;
        }
        jarvisSlider.setOnValueChanged(new Function1() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj3) {
                return DashboardActivity.setupSettingsToggles$lambda$12(this.f$0, textView9, ((Float) obj3).floatValue());
            }
        });
        final TextView textView10 = (TextView) screen.findViewById(R.id.memoryCount);
        JarvisToggle jarvisToggle6 = (JarvisToggle) screen.findViewById(R.id.toggleMemory);
        if (jarvisToggle6 != null) {
            jarvisToggle6.setChecked(JarvisSettings.INSTANCE.prefs(dashboardActivity).getBoolean("memory_enabled", z2));
            jarvisToggle6.setOnCheckedChanged(new Function1() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda22
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj3) {
                    return DashboardActivity.setupSettingsToggles$lambda$14$0(this.f$0, ((Boolean) obj3).booleanValue());
                }
            });
        }
        Button button = (Button) screen.findViewById(R.id.btnClearMemory);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda33
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupSettingsToggles$lambda$15(this.f$0, textView10, view);
                }
            });
            Unit unit9 = Unit.INSTANCE;
        }
        setupSettingsToggles$refreshMemoryCount(this, textView10);
        View[] viewArr = new View[4];
        viewArr[0] = screen.findViewById(R.id.themeClassic);
        viewArr[z2 ? 1 : 0] = screen.findViewById(R.id.themeArc);
        viewArr[2] = screen.findViewById(R.id.themeStealth);
        viewArr[3] = screen.findViewById(R.id.themeLight);
        List listListOf3 = CollectionsKt.listOf((Object[]) viewArr);
        String[] strArr = new String[4];
        strArr[0] = "CLASSIC";
        strArr[z2 ? 1 : 0] = "ARC";
        strArr[2] = "STEALTH";
        strArr[3] = "LIGHT";
        final List listListOf4 = CollectionsKt.listOf((Object[]) strArr);
        String strTheme = JarvisSettings.INSTANCE.theme(this);
        final TextView textView11 = (TextView) screen.findViewById(R.id.themeTag);
        int i5 = 0;
        for (Object obj3 : listListOf3) {
            int i6 = i5 + 1;
            if (i5 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            View view = (View) obj3;
            int i7 = i5;
            if (view != null) {
                i2 = i7;
                view.setSelected(Intrinsics.areEqual(listListOf4.get(i2), strTheme));
                Unit unit10 = Unit.INSTANCE;
            } else {
                i2 = i7;
            }
            if (view != null) {
                view.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda44
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        DashboardActivity.setupSettingsToggles$lambda$16$0(this.f$0, listListOf4, i2, textView11, view2);
                    }
                });
                Unit unit11 = Unit.INSTANCE;
            }
            i5 = i6;
            textView10 = textView10;
        }
        final List listListOf5 = CollectionsKt.listOf((Object[]) new TextView[]{screen.findViewById(R.id.personaClassic), screen.findViewById(R.id.personaFriendly), screen.findViewById(R.id.personaMinimal), screen.findViewById(R.id.personaDeveloper)});
        final List listListOf6 = CollectionsKt.listOf((Object[]) new String[]{"CLASSIC", "FRIENDLY", "MINIMAL", "DEVELOPER"});
        String strPersona = JarvisSettings.INSTANCE.persona(this);
        List list5 = listListOf5;
        int i8 = 0;
        for (Object obj4 : list5) {
            int i9 = i8 + 1;
            if (i8 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            List list6 = list5;
            TextView textView12 = (TextView) obj4;
            if (textView12 != null) {
                i = i8;
                textView12.setBackground(ContextCompat.getDrawable(this, Intrinsics.areEqual(listListOf6.get(i), strPersona) ? R.drawable.bg_fui_active : R.drawable.bg_hud_panel));
                Unit unit12 = Unit.INSTANCE;
            } else {
                i = i8;
            }
            if (textView12 != null) {
                textView12.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda55
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        DashboardActivity.setupSettingsToggles$lambda$17$0(this.f$0, listListOf6, i, listListOf5, view2);
                    }
                });
                Unit unit13 = Unit.INSTANCE;
            }
            i8 = i9;
            list5 = list6;
            listListOf4 = listListOf4;
            textView11 = textView11;
            textView9 = textView9;
        }
        TextView textView13 = textView11;
        String str6 = null;
        List listListOf7 = CollectionsKt.listOf((Object[]) new String[]{"FAST", "BALANCED", "QUALITY"});
        List listListOf8 = CollectionsKt.listOf((Object[]) new TextView[]{screen.findViewById(R.id.chatProfileFast), screen.findViewById(R.id.chatProfileBalanced), screen.findViewById(R.id.chatProfileQuality)});
        TextView textView14 = (TextView) screen.findViewById(R.id.chatProfileHint);
        setupSettingsToggles$renderChatProfile(this, listListOf8, textView14, listListOf7);
        int i10 = 0;
        for (Object obj5 : listListOf8) {
            int i11 = i10 + 1;
            if (i10 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TextView textView15 = (TextView) obj5;
            final TextView textView16 = textView14;
            TextView textView17 = textView13;
            JarvisSlider jarvisSlider2 = jarvisSlider;
            final int i12 = i10;
            if (textView15 != null) {
                list = listListOf8;
                list2 = listListOf7;
                str = null;
                textView15.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda60
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) throws Throwable {
                        DashboardActivity.setupSettingsToggles$lambda$19$0(this.f$0, list2, i12, list, textView16, view2);
                    }
                });
                Unit unit14 = Unit.INSTANCE;
            } else {
                list = listListOf8;
                list2 = listListOf7;
                str = null;
            }
            str6 = str;
            i10 = i11;
            listListOf6 = listListOf6;
            textView14 = textView16;
            jarvisSlider = jarvisSlider2;
            listListOf8 = list;
            listListOf7 = list2;
            strPersona = strPersona;
            strTheme = strTheme;
            listListOf5 = listListOf5;
            textView13 = textView17;
        }
        Object obj6 = str6;
        JarvisToggle jarvisToggle7 = (JarvisToggle) screen.findViewById(R.id.toggleChatPreload);
        if (jarvisToggle7 != null) {
            jarvisToggle7.setChecked(JarvisSettings.INSTANCE.chatPreloadEnabled(dashboardActivity));
            jarvisToggle7.setOnCheckedChanged(new Function1() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda61
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj7) {
                    return DashboardActivity.setupSettingsToggles$lambda$20$0(this.f$0, ((Boolean) obj7).booleanValue());
                }
            });
        }
        final EditText editText = (EditText) screen.findViewById(R.id.cloudApiKeyInput);
        final TextView textView18 = (TextView) screen.findViewById(R.id.cloudApiKeyHint);
        if (editText != null) {
            editText.setText("");
            Unit unit15 = Unit.INSTANCE;
        }
        setupSettingsToggles$refreshCloudHint(this, textView18);
        if (editText != null) {
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda62
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view2, boolean z3) {
                    DashboardActivity.setupSettingsToggles$lambda$21(editText, this, textView18, view2, z3);
                }
            });
            Unit unit16 = Unit.INSTANCE;
        }
        JarvisToggle jarvisToggle8 = (JarvisToggle) screen.findViewById(R.id.togglePreferCloud);
        if (jarvisToggle8 != null) {
            jarvisToggle8.setChecked(JarvisSettings.INSTANCE.preferCloudLlm(dashboardActivity));
            jarvisToggle8.setOnCheckedChanged(new Function1() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda63
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj7) {
                    return DashboardActivity.setupSettingsToggles$lambda$22$0(this.f$0, this, textView18, ((Boolean) obj7).booleanValue());
                }
            });
        }
        DashboardActivity dashboardActivity5 = dashboardActivity;
        setupSettingsToggles$bindCloudProviderKey(screen, dashboardActivity, "groq", R.id.groqKeyInput, R.id.groqKeyStatus, R.id.groqKeySave, R.id.groqKeyClear);
        setupSettingsToggles$bindCloudProviderKey(screen, dashboardActivity5, "gemini", R.id.geminiKeyInput, R.id.geminiKeyStatus, R.id.geminiKeySave, R.id.geminiKeyClear);
        String[] strArr2 = new String[3];
        strArr2[obj6 == true ? 1 : 0] = "AUTO";
        strArr2[1] = "CLOUD_PREFERRED";
        strArr2[2] = "LOCAL_ONLY";
        final List listListOf9 = CollectionsKt.listOf((Object[]) strArr2);
        String[] strArr3 = new String[3];
        strArr3[obj6 == true ? 1 : 0] = "Auto";
        strArr3[1] = "Cloud preferred";
        strArr3[2] = "Local only";
        List listListOf10 = CollectionsKt.listOf((Object[]) strArr3);
        Spinner spinner = (Spinner) screen.findViewById(R.id.asrModeSpinner);
        final TextView textView19 = (TextView) screen.findViewById(R.id.asrModeHint);
        if (spinner != null) {
            spinner.setAdapter((SpinnerAdapter) new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listListOf10));
            Unit unit17 = Unit.INSTANCE;
        }
        String strAsrMode = JarvisSettings.INSTANCE.asrMode(this);
        if (spinner != null) {
            spinner.setSelection(RangesKt.coerceAtLeast(listListOf9.indexOf(strAsrMode), 0));
            Unit unit18 = Unit.INSTANCE;
        }
        setupSettingsToggles$refreshAsrHint(textView19, this);
        if (spinner != null) {
            DashboardActivity dashboardActivity6 = dashboardActivity5;
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.jarvis.app.DashboardActivity.setupSettingsToggles.21
                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onItemSelected(AdapterView<?> parent, View view2, int position, long id) {
                    JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
                    DashboardActivity dashboardActivity7 = DashboardActivity.this;
                    List<String> list7 = listListOf9;
                    boolean z3 = false;
                    if (position >= 0 && position < list7.size()) {
                        z3 = true;
                    }
                    jarvisSettings.setAsrMode(dashboardActivity7, z3 ? list7.get(position) : "AUTO");
                    BrainGraph.INSTANCE.invalidate();
                    DashboardActivity.setupSettingsToggles$refreshAsrHint(textView19, this);
                }

                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
            Unit unit19 = Unit.INSTANCE;
            dashboardActivity2 = dashboardActivity6;
        } else {
            dashboardActivity2 = dashboardActivity5;
        }
        final EditText editText2 = (EditText) screen.findViewById(R.id.asrCloudBaseUrlInput);
        if (editText2 != null) {
            editText2.setText(JarvisSettings.INSTANCE.asrCloudBaseUrl(dashboardActivity2));
            editText2.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda66
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view2, boolean z3) {
                    DashboardActivity.setupSettingsToggles$lambda$25$0(this.f$0, editText2, textView19, this, view2, z3);
                }
            });
        }
        final EditText editText3 = (EditText) screen.findViewById(R.id.asrCloudApiKeyInput);
        if (editText3 != null) {
            editText3.setText(JarvisSettings.INSTANCE.asrCloudApiKey(dashboardActivity2));
            editText3.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda67
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view2, boolean z3) {
                    DashboardActivity.setupSettingsToggles$lambda$26$0(this.f$0, editText3, textView19, this, view2, z3);
                }
            });
        }
        final List listListOf11 = CollectionsKt.listOf((Object[]) new String[]{"AUTO", "CLOUD_PREFERRED", "LOCAL_ONLY"});
        List listListOf12 = CollectionsKt.listOf((Object[]) new String[]{"Auto", "Cloud preferred", "Local only"});
        Spinner spinner2 = (Spinner) screen.findViewById(R.id.ttsModeSpinner);
        final TextView textView20 = (TextView) screen.findViewById(R.id.ttsModeHint);
        if (spinner2 != null) {
            spinner2.setAdapter((SpinnerAdapter) new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listListOf12));
            Unit unit20 = Unit.INSTANCE;
        }
        if (spinner2 != null) {
            spinner2.setSelection(RangesKt.coerceAtLeast(listListOf11.indexOf(JarvisSettings.INSTANCE.ttsMode(this)), 0));
            Unit unit21 = Unit.INSTANCE;
        }
        setupSettingsToggles$refreshTtsHint(textView20, this);
        if (spinner2 != null) {
            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.jarvis.app.DashboardActivity.setupSettingsToggles.24
                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onItemSelected(AdapterView<?> parent, View view2, int position, long id) {
                    JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
                    DashboardActivity dashboardActivity7 = DashboardActivity.this;
                    List<String> list7 = listListOf11;
                    boolean z3 = false;
                    if (position >= 0 && position < list7.size()) {
                        z3 = true;
                    }
                    jarvisSettings.setTtsMode(dashboardActivity7, z3 ? list7.get(position) : "AUTO");
                    BrainGraph.INSTANCE.invalidate();
                    DashboardActivity.setupSettingsToggles$refreshTtsHint(textView20, this);
                }

                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
            Unit unit22 = Unit.INSTANCE;
        }
        final EditText editText4 = (EditText) screen.findViewById(R.id.ttsCloudBaseUrlInput);
        if (editText4 != null) {
            editText4.setText(JarvisSettings.INSTANCE.ttsCloudBaseUrl(dashboardActivity2));
            editText4.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda68
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view2, boolean z3) {
                    DashboardActivity.setupSettingsToggles$lambda$27$0(this.f$0, editText4, textView20, this, view2, z3);
                }
            });
        }
        final EditText editText5 = (EditText) screen.findViewById(R.id.ttsCloudApiKeyInput);
        if (editText5 != null) {
            editText5.setText(JarvisSettings.INSTANCE.ttsCloudApiKey(dashboardActivity2));
            editText5.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda69
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view2, boolean z3) {
                    DashboardActivity.setupSettingsToggles$lambda$28$0(this.f$0, editText5, textView20, this, view2, z3);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupSettingsToggles$showSettingsTab(View mainPanel, View modelsPanel, View companionPanel, TextView tabMain, TextView tabModels, TextView tabCompanion, DashboardActivity this$0, View $screen, String which) {
        if (mainPanel != null) {
            mainPanel.setVisibility(Intrinsics.areEqual(which, "main") ? 0 : 8);
        }
        if (modelsPanel != null) {
            modelsPanel.setVisibility(Intrinsics.areEqual(which, "models") ? 0 : 8);
        }
        if (companionPanel != null) {
            companionPanel.setVisibility(Intrinsics.areEqual(which, "companion") ? 0 : 8);
        }
        setupSettingsToggles$showSettingsTab$style(this$0, tabMain, Intrinsics.areEqual(which, "main"));
        setupSettingsToggles$showSettingsTab$style(this$0, tabModels, Intrinsics.areEqual(which, "models"));
        setupSettingsToggles$showSettingsTab$style(this$0, tabCompanion, Intrinsics.areEqual(which, "companion"));
        if (Intrinsics.areEqual(which, "models")) {
            this$0.setupModelsPanel($screen);
        }
        if (Intrinsics.areEqual(which, "companion")) {
            this$0.setupCompanionPanel($screen);
        }
    }

    private static final void setupSettingsToggles$showSettingsTab$style(DashboardActivity this$0, TextView tab, boolean active) {
        if (tab != null) {
            tab.setBackground(ContextCompat.getDrawable(this$0, active ? R.drawable.bg_fui_active : R.drawable.bg_hud_panel));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupSettingsToggles$lambda$3(DashboardActivity this$0, View it) {
        this$0.startActivity(new Intent(this$0, (Class<?>) UsageGuideActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupSettingsToggles$lambda$4(DashboardActivity this$0, View it) {
        UiMode.INSTANCE.switchTo(this$0, UiStyle.MODERN);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setupSettingsToggles$lambda$5$0(DashboardActivity $ctx, boolean it) {
        JarvisSettings.INSTANCE.setHudSfx($ctx, it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setupSettingsToggles$lambda$6$0(DashboardActivity $ctx, boolean it) {
        JarvisSettings.INSTANCE.prefs($ctx).edit().putBoolean("adaptive_listening", it).apply();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setupSettingsToggles$lambda$7$0(DashboardActivity $ctx, boolean it) {
        JarvisSettings.INSTANCE.prefs($ctx).edit().putBoolean("low_power", it).apply();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setupSettingsToggles$lambda$8$0(DashboardActivity $ctx, boolean it) {
        JarvisSettings.INSTANCE.prefs($ctx).edit().putBoolean("confirm_sensitive", it).apply();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setupSettingsToggles$lambda$9$0(DashboardActivity $ctx, DashboardActivity this$0, boolean enabled) {
        JarvisSettings.INSTANCE.setWakeEnabled($ctx, enabled);
        if (VoiceController.INSTANCE.isRunning()) {
            VoiceController.INSTANCE.stop(this$0);
            VoiceController.INSTANCE.start(this$0);
        }
        return Unit.INSTANCE;
    }

    private static final void setupSettingsToggles$renderWakeLevels(DashboardActivity ctx, List<? extends TextView> list, DashboardActivity this$0, List<Integer> list2) {
        int cur = JarvisSettings.INSTANCE.wakeSensitivity(ctx);
        List<? extends TextView> $this$forEachIndexed$iv = list;
        int i = 0;
        for (Object item$iv : $this$forEachIndexed$iv) {
            int index$iv = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TextView tag = (TextView) item$iv;
            if (tag != null) {
                tag.setBackground(ContextCompat.getDrawable(this$0, cur == list2.get(i).intValue() ? R.drawable.bg_fui_active : R.drawable.bg_hud_panel));
            }
            i = index$iv;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupSettingsToggles$lambda$11$0(DashboardActivity $ctx, List $wakeLevels, int $i, DashboardActivity this$0, List $wakeTags, View it) {
        JarvisSettings.INSTANCE.setWakeSensitivity($ctx, ((Number) $wakeLevels.get($i)).intValue());
        setupSettingsToggles$renderWakeLevels($ctx, $wakeTags, this$0, $wakeLevels);
        if (VoiceController.INSTANCE.isRunning()) {
            VoiceController.INSTANCE.stop(this$0);
            VoiceController.INSTANCE.start(this$0);
        }
    }

    private static final float setupSettingsToggles$thresholdToSlider(float t) {
        return (t - 0.3f) / 0.6f;
    }

    private static final float setupSettingsToggles$sliderToThreshold(float s) {
        return (0.6f * s) + 0.3f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setupSettingsToggles$lambda$12(DashboardActivity $ctx, TextView $thresholdText, float v) {
        JarvisSettings.INSTANCE.setBrainConfidenceThreshold($ctx, setupSettingsToggles$sliderToThreshold(v));
        if ($thresholdText != null) {
            String str = String.format("%.2f", Arrays.copyOf(new Object[]{Float.valueOf(setupSettingsToggles$sliderToThreshold(v))}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            $thresholdText.setText(str);
        }
        return Unit.INSTANCE;
    }

    private static final void setupSettingsToggles$refreshMemoryCount(DashboardActivity this$0, TextView memoryCount) {
        Object objM392constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(Integer.valueOf(BrainGraph.INSTANCE.memory(this$0).count()));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = 0;
        }
        int n = ((Number) objM392constructorimpl).intValue();
        if (memoryCount != null) {
            memoryCount.setText(String.valueOf(n));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setupSettingsToggles$lambda$14$0(DashboardActivity $ctx, boolean it) {
        JarvisSettings.INSTANCE.prefs($ctx).edit().putBoolean("memory_enabled", it).apply();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupSettingsToggles$lambda$15(DashboardActivity this$0, TextView $memoryCount, View it) {
        try {
            Result.Companion companion = Result.INSTANCE;
            BrainGraph.INSTANCE.memory(this$0).clear();
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        setupSettingsToggles$refreshMemoryCount(this$0, $memoryCount);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupSettingsToggles$lambda$16$0(DashboardActivity this$0, List $themeNames, int $i, TextView $themeTag, View it) {
        JarvisSettings.INSTANCE.setTheme(this$0, (String) $themeNames.get($i));
        if ($themeTag != null) {
            $themeTag.setText((CharSequence) $themeNames.get($i));
        }
        ThemeApplier.INSTANCE.recreateWithTheme(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupSettingsToggles$lambda$17$0(DashboardActivity this$0, List $personaNames, int $i, List $personaChips, View it) {
        JarvisSettings.INSTANCE.setPersona(this$0, (String) $personaNames.get($i));
        List $this$forEachIndexed$iv = $personaChips;
        int j = 0;
        for (Object item$iv : $this$forEachIndexed$iv) {
            int index$iv = j + 1;
            if (j < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TextView c = (TextView) item$iv;
            if (c != null) {
                c.setBackground(ContextCompat.getDrawable(this$0, j == $i ? R.drawable.bg_fui_active : R.drawable.bg_hud_panel));
            }
            j = index$iv;
        }
        BrainGraph.INSTANCE.invalidateChatLlm();
        Toast.makeText(this$0, "Характер: " + $personaNames.get($i), 0).show();
    }

    private static final String setupSettingsToggles$chatHintText(String p) {
        if (Intrinsics.areEqual(p, "FAST")) {
            return "Быстро: короче ответы, ниже latency (~2–4 с).";
        }
        return Intrinsics.areEqual(p, "QUALITY") ? "Качество: чуть дольше, полнее формулировки (~4–7 с)." : "Баланс: ~3–5 с, ясные ответы без воды.";
    }

    private static final void setupSettingsToggles$renderChatProfile(DashboardActivity this$0, List<? extends TextView> list, TextView chatHint, List<String> list2) {
        String cur = JarvisSettings.INSTANCE.chatProfile(this$0);
        List<? extends TextView> $this$forEachIndexed$iv = list;
        int i = 0;
        for (Object item$iv : $this$forEachIndexed$iv) {
            int index$iv = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TextView chip = (TextView) item$iv;
            if (chip != null) {
                chip.setBackground(ContextCompat.getDrawable(this$0, Intrinsics.areEqual(list2.get(i), cur) ? R.drawable.bg_fui_active : R.drawable.bg_hud_panel));
            }
            i = index$iv;
        }
        if (chatHint != null) {
            chatHint.setText(setupSettingsToggles$chatHintText(cur));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupSettingsToggles$lambda$19$0(DashboardActivity this$0, List $chatProfiles, int $i, List $chatChips, TextView $chatHint, View it) throws Throwable {
        JarvisSettings.INSTANCE.setChatProfile(this$0, (String) $chatProfiles.get($i));
        setupSettingsToggles$renderChatProfile(this$0, $chatChips, $chatHint, $chatProfiles);
        BrainGraph.INSTANCE.invalidateChatLlm();
        BrainGraph.INSTANCE.warmChatLlm(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setupSettingsToggles$lambda$20$0(DashboardActivity $ctx, boolean enabled) {
        JarvisSettings.INSTANCE.setChatPreloadEnabled($ctx, enabled);
        if (enabled) {
            BrainGraph.INSTANCE.preloadChatModel($ctx);
        }
        return Unit.INSTANCE;
    }

    private static final void setupSettingsToggles$refreshCloudHint(DashboardActivity this$0, TextView cloudHint) {
        CharSequence charSequence;
        boolean set = JarvisSettings.INSTANCE.cloudConfigured(this$0);
        if (cloudHint != null) {
            if (set) {
                charSequence = "Ключ сохранён · модель: " + JarvisSettings.INSTANCE.cloudModelForProfile(this$0) + " · preferCloud=" + JarvisSettings.INSTANCE.preferCloudLlm(this$0);
            }
            cloudHint.setText(charSequence);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupSettingsToggles$lambda$21(EditText $cloudKeyInput, DashboardActivity this$0, TextView $cloudHint, View view, boolean hasFocus) {
        if (!hasFocus) {
            Editable text = $cloudKeyInput.getText();
            String key = text != null ? text.toString() : null;
            if (key == null) {
                key = "";
            }
            JarvisSettings.INSTANCE.setCloudApiKey(this$0, key);
            BrainGraph.INSTANCE.onCloudKeyChanged("custom");
            BrainGraph.INSTANCE.invalidate();
            setupSettingsToggles$refreshCloudHint(this$0, $cloudHint);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setupSettingsToggles$lambda$22$0(DashboardActivity $ctx, DashboardActivity this$0, TextView $cloudHint, boolean enabled) {
        JarvisSettings.INSTANCE.setPreferCloudLlm($ctx, enabled);
        BrainGraph.INSTANCE.invalidate();
        setupSettingsToggles$refreshCloudHint(this$0, $cloudHint);
        return Unit.INSTANCE;
    }

    private static final void setupSettingsToggles$bindCloudProviderKey(View $screen, final DashboardActivity ctx, final String providerId, int inputId, int statusId, int saveId, int clearId) {
        final TextView status;
        final EditText input = (EditText) $screen.findViewById(inputId);
        if (input == null || (status = (TextView) $screen.findViewById(statusId)) == null) {
            return;
        }
        final SecureKeyStore store = new SecureKeyStore(ctx);
        input.setText("");
        setupSettingsToggles$bindCloudProviderKey$refreshStatus(status, store, providerId);
        Button button = (Button) $screen.findViewById(saveId);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda136
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupSettingsToggles$bindCloudProviderKey$lambda$23(input, ctx, store, providerId, status, view);
                }
            });
        }
        Button button2 = (Button) $screen.findViewById(clearId);
        if (button2 != null) {
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda137
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupSettingsToggles$bindCloudProviderKey$lambda$24(store, providerId, input, ctx, status, view);
                }
            });
        }
    }

    private static final void setupSettingsToggles$bindCloudProviderKey$refreshStatus(TextView status, SecureKeyStore store, String $providerId) {
        status.setText(store.hasKey($providerId) ? R.string.cloud_key_set : R.string.cloud_key_missing);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupSettingsToggles$bindCloudProviderKey$lambda$23(EditText $input, DashboardActivity $ctx, SecureKeyStore $store, String $providerId, TextView $status, View it) {
        CloudKeyValidator cloudKeyValidator = CloudKeyValidator.INSTANCE;
        Editable text = $input.getText();
        String clean = cloudKeyValidator.sanitizeKey(text != null ? text.toString() : null);
        if (CloudKeyValidator.INSTANCE.isStubKey(clean)) {
            Toast.makeText($ctx, R.string.cloud_key_rejected, 0).show();
            return;
        }
        $store.setKey($providerId, clean);
        $input.setText("");
        setupSettingsToggles$bindCloudProviderKey$refreshStatus($status, $store, $providerId);
        BrainGraph.INSTANCE.onCloudKeyChanged($providerId);
        BrainGraph.INSTANCE.invalidate();
        Toast.makeText($ctx, R.string.cloud_key_saved, 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupSettingsToggles$bindCloudProviderKey$lambda$24(SecureKeyStore $store, String $providerId, EditText $input, DashboardActivity $ctx, TextView $status, View it) {
        $store.clearKey($providerId);
        $input.setText("");
        setupSettingsToggles$bindCloudProviderKey$refreshStatus($status, $store, $providerId);
        BrainGraph.INSTANCE.onCloudKeyChanged($providerId);
        BrainGraph.INSTANCE.invalidate();
        Toast.makeText($ctx, R.string.cloud_key_deleted, 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupSettingsToggles$refreshAsrHint(TextView asrHint, DashboardActivity this$0) {
        if (asrHint != null) {
            asrHint.setText("Режим: " + JarvisSettings.INSTANCE.asrMode(this$0) + " · provider: " + CloudAsrFactory.INSTANCE.cloudConfiguredLabel(this$0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupSettingsToggles$lambda$25$0(DashboardActivity $ctx, EditText $this_apply, TextView $asrHint, DashboardActivity this$0, View view, boolean hasFocus) {
        if (!hasFocus) {
            JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
            DashboardActivity dashboardActivity = $ctx;
            Editable text = $this_apply.getText();
            String string = text != null ? text.toString() : null;
            if (string == null) {
                string = "";
            }
            jarvisSettings.setAsrCloudBaseUrl(dashboardActivity, string);
            BrainGraph.INSTANCE.invalidate();
            setupSettingsToggles$refreshAsrHint($asrHint, this$0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupSettingsToggles$lambda$26$0(DashboardActivity $ctx, EditText $this_apply, TextView $asrHint, DashboardActivity this$0, View view, boolean hasFocus) {
        if (!hasFocus) {
            JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
            DashboardActivity dashboardActivity = $ctx;
            Editable text = $this_apply.getText();
            String string = text != null ? text.toString() : null;
            if (string == null) {
                string = "";
            }
            jarvisSettings.setAsrCloudApiKey(dashboardActivity, string);
            BrainGraph.INSTANCE.invalidate();
            setupSettingsToggles$refreshAsrHint($asrHint, this$0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupSettingsToggles$refreshTtsHint(TextView ttsHint, DashboardActivity this$0) {
        if (ttsHint != null) {
            ttsHint.setText("Режим: " + JarvisSettings.INSTANCE.ttsMode(this$0) + " · provider: " + CloudTtsFactory.INSTANCE.cloudConfiguredLabel(this$0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupSettingsToggles$lambda$27$0(DashboardActivity $ctx, EditText $this_apply, TextView $ttsHint, DashboardActivity this$0, View view, boolean hasFocus) {
        if (!hasFocus) {
            JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
            DashboardActivity dashboardActivity = $ctx;
            Editable text = $this_apply.getText();
            String string = text != null ? text.toString() : null;
            if (string == null) {
                string = "";
            }
            jarvisSettings.setTtsCloudBaseUrl(dashboardActivity, string);
            BrainGraph.INSTANCE.invalidate();
            setupSettingsToggles$refreshTtsHint($ttsHint, this$0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupSettingsToggles$lambda$28$0(DashboardActivity $ctx, EditText $this_apply, TextView $ttsHint, DashboardActivity this$0, View view, boolean hasFocus) {
        if (!hasFocus) {
            JarvisSettings jarvisSettings = JarvisSettings.INSTANCE;
            DashboardActivity dashboardActivity = $ctx;
            Editable text = $this_apply.getText();
            String string = text != null ? text.toString() : null;
            if (string == null) {
                string = "";
            }
            jarvisSettings.setTtsCloudApiKey(dashboardActivity, string);
            BrainGraph.INSTANCE.invalidate();
            setupSettingsToggles$refreshTtsHint($ttsHint, this$0);
        }
    }

    private final void bindCompanionToggle(View screen, int id, final String key, boolean z) {
        JarvisToggle $this$bindCompanionToggle_u24lambda_u240 = (JarvisToggle) screen.findViewById(id);
        if ($this$bindCompanionToggle_u24lambda_u240 != null) {
            $this$bindCompanionToggle_u24lambda_u240.setChecked(JarvisSettings.INSTANCE.prefs(this).getBoolean(key, z));
            $this$bindCompanionToggle_u24lambda_u240.setOnCheckedChanged(new Function1() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda58
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DashboardActivity.bindCompanionToggle$lambda$0$0(this.f$0, key, ((Boolean) obj).booleanValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindCompanionToggle$lambda$0$0(DashboardActivity this$0, String $key, boolean checked) {
        JarvisSettings.INSTANCE.prefs(this$0).edit().putBoolean($key, checked).apply();
        return Unit.INSTANCE;
    }

    private final void styleCompanionButtons(View root) {
        if (root instanceof Button) {
            ((Button) root).setBackground(ContextCompat.getDrawable(this, R.drawable.bg_hud_panel));
            ((Button) root).setBackgroundTintList(null);
            ((Button) root).setTextColor(ContextCompat.getColor(this, R.color.jarvis_cyan));
        }
        if (root instanceof ViewGroup) {
            int childCount = ((ViewGroup) root).getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = ((ViewGroup) root).getChildAt(i);
                Intrinsics.checkNotNullExpressionValue(childAt, "getChildAt(...)");
                styleCompanionButtons(childAt);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r7v18, types: [com.jarvis.app.DashboardActivity$showHudConfirm$tick$1] */
    private final void showHudConfirm(String toolId, String message) {
        Dialog dialog = this.confirmDialog;
        if (dialog != null) {
            dialog.dismiss();
        }
        final Dialog dialog2 = new Dialog(this);
        float density = getResources().getDisplayMetrics().density;
        int pad = (int) (16 * density);
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(1);
        box.setPadding(pad, pad, pad, pad);
        box.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_hud_panel));
        final TextView $this$showHudConfirm_u24lambda_u241 = new TextView(this);
        $this$showHudConfirm_u24lambda_u241.setText("АВТООТМЕНА  10s");
        $this$showHudConfirm_u24lambda_u241.setTextColor(ContextCompat.getColor(this, R.color.jarvis_primary));
        $this$showHudConfirm_u24lambda_u241.setTextSize(11.0f);
        $this$showHudConfirm_u24lambda_u241.setTypeface(Typeface.MONOSPACE);
        TextView $this$showHudConfirm_u24lambda_u242 = new TextView(this);
        $this$showHudConfirm_u24lambda_u242.setText("CONFIRM · " + toolId);
        $this$showHudConfirm_u24lambda_u242.setTextColor(ContextCompat.getColor(this, R.color.jarvis_cyan));
        $this$showHudConfirm_u24lambda_u242.setTextSize(12.0f);
        $this$showHudConfirm_u24lambda_u242.setTypeface(Typeface.MONOSPACE);
        box.addView($this$showHudConfirm_u24lambda_u242);
        TextView $this$showHudConfirm_u24lambda_u243 = new TextView(this);
        $this$showHudConfirm_u24lambda_u243.setText(message);
        $this$showHudConfirm_u24lambda_u243.setTextColor(ContextCompat.getColor(this, R.color.jarvis_text));
        $this$showHudConfirm_u24lambda_u243.setTextSize(14.0f);
        $this$showHudConfirm_u24lambda_u243.setPadding(0, pad / 2, 0, pad / 2);
        box.addView($this$showHudConfirm_u24lambda_u243);
        box.addView($this$showHudConfirm_u24lambda_u241);
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(0);
        row.setPadding(0, pad / 2, 0, 0);
        TextView $this$showHudConfirm_u24lambda_u248 = new TextView(this);
        $this$showHudConfirm_u24lambda_u248.setText("ПОДТВЕРДИТЬ");
        $this$showHudConfirm_u24lambda_u248.setTextColor(ContextCompat.getColor(this, R.color.jarvis_cyan));
        $this$showHudConfirm_u24lambda_u248.setTextSize(12.0f);
        $this$showHudConfirm_u24lambda_u248.setTypeface(Typeface.MONOSPACE);
        $this$showHudConfirm_u24lambda_u248.setPadding(pad, pad / 2, pad, pad / 2);
        $this$showHudConfirm_u24lambda_u248.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_fui_active));
        $this$showHudConfirm_u24lambda_u248.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda87
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DashboardActivity.showHudConfirm$finish(dialog2, this, true);
            }
        });
        row.addView($this$showHudConfirm_u24lambda_u248);
        TextView $this$showHudConfirm_u24lambda_u249 = new TextView(this);
        $this$showHudConfirm_u24lambda_u249.setText("ОТМЕНА");
        $this$showHudConfirm_u24lambda_u249.setTextColor(ContextCompat.getColor(this, R.color.jarvis_danger));
        $this$showHudConfirm_u24lambda_u249.setTextSize(12.0f);
        $this$showHudConfirm_u24lambda_u249.setTypeface(Typeface.MONOSPACE);
        $this$showHudConfirm_u24lambda_u249.setPadding(pad, pad / 2, pad, pad / 2);
        $this$showHudConfirm_u24lambda_u249.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_hud_panel));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-2, -2);
        lp.setMarginStart(pad / 2);
        $this$showHudConfirm_u24lambda_u249.setLayoutParams(lp);
        $this$showHudConfirm_u24lambda_u249.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda98
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DashboardActivity.showHudConfirm$finish(dialog2, this, false);
            }
        });
        row.addView($this$showHudConfirm_u24lambda_u249);
        box.addView(row);
        dialog2.setContentView(box);
        Window window = dialog2.getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(android.R.color.transparent);
        }
        final Ref.IntRef left = new Ref.IntRef();
        left.element = 10;
        final ?? r7 = new Runnable() { // from class: com.jarvis.app.DashboardActivity$showHudConfirm$tick$1
            @Override // java.lang.Runnable
            public void run() {
                if (dialog2.isShowing()) {
                    $this$showHudConfirm_u24lambda_u241.setText("АВТООТМЕНА  " + left.element + "s");
                    if (left.element <= 0) {
                        DashboardActivity.showHudConfirm$finish(dialog2, this, false);
                        return;
                    }
                    left.element--;
                    this.confirmHandler.postDelayed(this, 1000L);
                }
            }
        };
        dialog2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda109
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                DashboardActivity.showHudConfirm$lambda$10(this.f$0, r7, dialogInterface);
            }
        });
        dialog2.show();
        this.confirmDialog = dialog2;
        this.confirmHandler.post((Runnable) r7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showHudConfirm$finish(Dialog dialog, DashboardActivity this$0, boolean approved) {
        FrameLayout frameLayout;
        Object element$iv;
        dialog.dismiss();
        Iterable $this$firstOrNull$iv = ActivityFeed.INSTANCE.all();
        Iterator it = $this$firstOrNull$iv.iterator();
        do {
            frameLayout = null;
            if (!it.hasNext()) {
                element$iv = null;
                break;
            }
            element$iv = it.next();
        } while (!((ActivityFeed.Item) element$iv).getPendingConfirm());
        ActivityFeed.Item it2 = (ActivityFeed.Item) element$iv;
        if (it2 != null) {
            ActivityFeed.INSTANCE.markResolved(it2.getId(), approved);
        }
        if (approved && VoiceController.INSTANCE.isRunning()) {
            VoiceController.INSTANCE.notifyRequest$app_debug(new UserRequest("да", null, null, 0L, null, 30, null));
        }
        if (this$0.currentScreenIndex == 3) {
            FrameLayout frameLayout2 = this$0.screenContainer;
            if (frameLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("screenContainer");
            } else {
                frameLayout = frameLayout2;
            }
            LinearLayout it3 = (LinearLayout) frameLayout.findViewById(R.id.activityFeedContainer);
            if (it3 != null) {
                this$0.renderActivityFeed(it3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showHudConfirm$lambda$10(DashboardActivity this$0, DashboardActivity$showHudConfirm$tick$1 $tick, DialogInterface it) {
        this$0.confirmHandler.removeCallbacks($tick);
    }

    private final void setupCompanionPanel(View screen) {
        final DashboardActivity dashboardActivity;
        styleCompanionButtons(screen);
        bindCompanionToggle(screen, R.id.toggleCompanionProactive, "companion_proactive", true);
        bindCompanionToggle(screen, R.id.toggleCompanionNotify, "companion_bg_notify", true);
        bindCompanionToggle(screen, R.id.toggleQuietHours, "companion_quiet", false);
        bindCompanionToggle(screen, R.id.toggleOrbSnap, "companion_orb_snap", true);
        SystemStateObserver.INSTANCE.ensureStarted(this);
        UsageStatsObserver.INSTANCE.ensureStarted(this);
        final CompanionMemoryService mem = CompanionMemory.INSTANCE.get(this);
        final BehaviorLearningService behavior = CompanionBehavior.INSTANCE.get(this);
        final TextView contextView = (TextView) screen.findViewById(R.id.companionContextText);
        final TextView eventsView = (TextView) screen.findViewById(R.id.companionEventsText);
        final TextView sourcesView = (TextView) screen.findViewById(R.id.companionSourcesText);
        final TextView memoryView = (TextView) screen.findViewById(R.id.companionMemoryText);
        final TextView durableView = (TextView) screen.findViewById(R.id.companionDurableMemoryText);
        final TextView behaviorView = (TextView) screen.findViewById(R.id.companionBehaviorText);
        final TextView proactiveView = (TextView) screen.findViewById(R.id.companionProactiveText);
        final View proactiveButtons = screen.findViewById(R.id.companionProactiveButtons);
        final TextView diagnosticsView = (TextView) screen.findViewById(R.id.companionDiagnosticsText);
        final SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        Button button = (Button) screen.findViewById(R.id.btnCompanionRefresh);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda146
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupCompanionPanel$render(this.f$0, sourcesView, contextView, mem, memoryView, durableView, behavior, behaviorView, proactiveView, proactiveButtons, eventsView, fmt, diagnosticsView);
                }
            });
        }
        Button button2 = (Button) screen.findViewById(R.id.btnCompanionNls);
        if (button2 != null) {
            dashboardActivity = this;
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda150
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupCompanionPanel$lambda$12(this.f$0, view);
                }
            });
        } else {
            dashboardActivity = this;
        }
        Button button3 = (Button) screen.findViewById(R.id.btnCompanionUsage);
        if (button3 != null) {
            button3.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda151
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupCompanionPanel$lambda$13(this.f$0, view);
                }
            });
        }
        Button button4 = (Button) screen.findViewById(R.id.btnCompanionAssistant);
        if (button4 != null) {
            button4.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda152
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupCompanionPanel$lambda$14(this.f$0, view);
                }
            });
        }
        Button button5 = (Button) screen.findViewById(R.id.btnCompanionRemember);
        if (button5 != null) {
            button5.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupCompanionPanel$lambda$15(mem, this, sourcesView, contextView, memoryView, durableView, behavior, behaviorView, proactiveView, proactiveButtons, eventsView, fmt, diagnosticsView, view);
                }
            });
        }
        Button button6 = (Button) screen.findViewById(R.id.btnBehaviorAccept);
        if (button6 != null) {
            button6.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupCompanionPanel$lambda$16(behavior, mem, this, sourcesView, contextView, memoryView, durableView, behaviorView, proactiveView, proactiveButtons, eventsView, fmt, diagnosticsView, view);
                }
            });
        }
        Button button7 = (Button) screen.findViewById(R.id.btnBehaviorIgnore);
        if (button7 != null) {
            button7.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupCompanionPanel$lambda$17(behavior, mem, this, sourcesView, contextView, memoryView, durableView, behaviorView, proactiveView, proactiveButtons, eventsView, fmt, diagnosticsView, view);
                }
            });
        }
        Button button8 = (Button) screen.findViewById(R.id.btnBehaviorDisable);
        if (button8 != null) {
            button8.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupCompanionPanel$lambda$18(behavior, mem, this, sourcesView, contextView, memoryView, durableView, behaviorView, proactiveView, proactiveButtons, eventsView, fmt, diagnosticsView, view);
                }
            });
        }
        Button button9 = (Button) screen.findViewById(R.id.btnProactiveAccept);
        if (button9 != null) {
            button9.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupCompanionPanel$lambda$19(this.f$0, behavior, mem, sourcesView, contextView, memoryView, durableView, behaviorView, proactiveView, proactiveButtons, eventsView, fmt, diagnosticsView, view);
                }
            });
        }
        Button button10 = (Button) screen.findViewById(R.id.btnProactiveIgnore);
        if (button10 != null) {
            button10.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupCompanionPanel$lambda$20(this.f$0, behavior, mem, sourcesView, contextView, memoryView, durableView, behaviorView, proactiveView, proactiveButtons, eventsView, fmt, diagnosticsView, view);
                }
            });
        }
        Button button11 = (Button) screen.findViewById(R.id.btnProactiveDisable);
        if (button11 != null) {
            button11.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda147
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupCompanionPanel$lambda$21(this.f$0, behavior, mem, sourcesView, contextView, memoryView, durableView, behaviorView, proactiveView, proactiveButtons, eventsView, fmt, diagnosticsView, view);
                }
            });
        }
        Button button12 = (Button) screen.findViewById(R.id.btnProactiveHeadphonesNow);
        if (button12 != null) {
            button12.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda148
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupCompanionPanel$lambda$22(this.f$0, sourcesView, contextView, mem, memoryView, durableView, behavior, behaviorView, proactiveView, proactiveButtons, eventsView, fmt, diagnosticsView, view);
                }
            });
        }
        Button button13 = (Button) screen.findViewById(R.id.btnBehaviorSimulate);
        if (button13 != null) {
            button13.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda149
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardActivity.setupCompanionPanel$lambda$23(this.f$0, sourcesView, contextView, mem, memoryView, durableView, behavior, behaviorView, proactiveView, proactiveButtons, eventsView, fmt, diagnosticsView, view);
                }
            });
        }
        setupCompanionPanel$render(this, sourcesView, contextView, mem, memoryView, durableView, behavior, behaviorView, proactiveView, proactiveButtons, eventsView, fmt, diagnosticsView);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AnonymousClass14(sourcesView, contextView, mem, memoryView, durableView, behavior, behaviorView, proactiveView, proactiveButtons, eventsView, fmt, diagnosticsView, null), 3, null);
    }

    private static final String setupCompanionPanel$sourceLine(boolean ok, String label) {
        return (ok ? "●" : "○") + StringUtils.SPACE + label;
    }

    private static final String setupCompanionPanel$topBehaviorKey(BehaviorLearningService behavior) {
        BehaviorCandidate behaviorCandidate = (BehaviorCandidate) CollectionsKt.firstOrNull(BehaviorStore.suggestions$default(behavior.getStore(), 0L, 1, null));
        if (behaviorCandidate != null) {
            return behaviorCandidate.getPatternKey();
        }
        return null;
    }

    private static final String setupCompanionPanel$formatProactiveUserText(ProactiveDecision d) {
        String it;
        StringBuilder $this$setupCompanionPanel_u24formatProactiveUserText_u24lambda_u240 = new StringBuilder();
        switch (WhenMappings.$EnumSwitchMapping$2[d.getType().ordinal()]) {
            case 1:
                $this$setupCompanionPanel_u24formatProactiveUserText_u24lambda_u240.append("Предложение\n");
                break;
            case 2:
                $this$setupCompanionPanel_u24formatProactiveUserText_u24lambda_u240.append("Вопрос\n");
                break;
            case 3:
                $this$setupCompanionPanel_u24formatProactiveUserText_u24lambda_u240.append("Подтверждение\n");
                break;
            case 4:
                return $this$setupCompanionPanel_u24formatProactiveUserText_u24lambda_u240.toString();
            default:
                throw new NoWhenBranchMatchedException();
        }
        String strTake = StringsKt.take(d.getReason(), 160);
        if (StringsKt.isBlank(strTake)) {
            strTake = "Контекст совпадает с привычным поведением.";
        }
        $this$setupCompanionPanel_u24formatProactiveUserText_u24lambda_u240.append(strTake);
        if (BuildConfig.DEBUG && (it = d.getBehaviorPatternKey()) != null) {
            $this$setupCompanionPanel_u24formatProactiveUserText_u24lambda_u240.append(StringUtils.LF).append(StringsKt.take(it, 64));
        }
        return $this$setupCompanionPanel_u24formatProactiveUserText_u24lambda_u240.toString();
    }

    private static final String setupCompanionPanel$formatProactiveDebug(ProactiveDecision d) {
        StringBuilder $this$setupCompanionPanel_u24formatProactiveDebug_u24lambda_u241 = new StringBuilder();
        $this$setupCompanionPanel_u24formatProactiveDebug_u24lambda_u241.append("debug NO_OP · ").append(d.getReasonCode().name());
        if (!StringsKt.isBlank(d.getReason())) {
            $this$setupCompanionPanel_u24formatProactiveDebug_u24lambda_u241.append(" · ").append(StringsKt.take(d.getReason(), 48));
        }
        return $this$setupCompanionPanel_u24formatProactiveDebug_u24lambda_u241.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupCompanionPanel$render(DashboardActivity this$0, TextView sourcesView, TextView contextView, CompanionMemoryService mem, TextView memoryView, TextView durableView, BehaviorLearningService behavior, TextView behaviorView, TextView proactiveView, View proactiveButtons, TextView eventsView, final SimpleDateFormat fmt, TextView diagnosticsView) {
        TextView textView;
        Object objM392constructorimpl;
        int i;
        boolean a11y = PermissionsHelper.INSTANCE.isAccessibilityServiceEnabled(this$0, JarvisAccessibilityService.class);
        boolean nls = PermissionsHelper.INSTANCE.isNotificationListenerEnabled(this$0);
        boolean usage = PermissionsHelper.INSTANCE.isUsageAccessGranted(this$0);
        boolean assist = PermissionsHelper.INSTANCE.isAssistantRoleHeld(this$0);
        boolean voiceOn = VoiceController.INSTANCE.isRunning();
        if (sourcesView != null) {
            sourcesView.setText(CollectionsKt.joinToString$default(CollectionsKt.listOf((Object[]) new String[]{setupCompanionPanel$sourceLine(assist, "Digital assistant"), setupCompanionPanel$sourceLine(a11y, "Accessibility"), setupCompanionPanel$sourceLine(nls, "Notifications"), setupCompanionPanel$sourceLine(usage, "Usage access"), setupCompanionPanel$sourceLine(voiceOn, "VoicePipeline (" + VoiceController.INSTANCE.getLastEntry() + ")")}), StringUtils.LF, null, null, 0, null, null, 62, null));
        }
        ContextSnapshot snap = PhoneContextBus.INSTANCE.snapshot();
        PhoneContext ctx = snap.getContext();
        if (contextView != null) {
            StringBuilder $this$setupCompanionPanel_u24render_u24lambda_u242 = new StringBuilder();
            $this$setupCompanionPanel_u24render_u24lambda_u242.append(ctx.describe());
            String it = ctx.getLastNotificationSummary();
            if (it != null) {
                $this$setupCompanionPanel_u24render_u24lambda_u242.append("\nlast: ").append(it);
            }
            contextView.setText($this$setupCompanionPanel_u24render_u24lambda_u242.toString());
        }
        List<PhoneObservation> listRecent = ObservationDiagnosticsBuffer.INSTANCE.recent(40);
        mem.ingest(snap, listRecent);
        mem.expireNow();
        Iterable $this$filter$iv = this$0.memoryPipeline.evaluate(snap, listRecent);
        int $i$f$filter = 0;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            Iterable $this$filter$iv2 = $this$filter$iv;
            int $i$f$filter2 = $i$f$filter;
            if (((MemoryPolicyDecision) element$iv$iv).getDisposition() != MemoryDisposition.REJECT) {
                destination$iv$iv.add(element$iv$iv);
            }
            $this$filter$iv = $this$filter$iv2;
            $i$f$filter = $i$f$filter2;
        }
        List decisions = (List) destination$iv$iv;
        if (memoryView != null) {
            memoryView.setText(decisions.isEmpty() ? "Нет новых кандидатов (≥3 FG / app switch)." : CollectionsKt.joinToString$default(CollectionsKt.asReversed(CollectionsKt.takeLast(decisions, 4)), StringUtils.LF, null, null, 0, null, new Function1() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda84
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DashboardActivity.setupCompanionPanel$render$lambda$4((MemoryPolicyDecision) obj);
                }
            }, 30, null));
        }
        List<MemoryEntry> listActive = mem.getRecall().active(12);
        List<MemoryEntry> listTemporary = mem.getRecall().temporary(8);
        List<MemoryEntry> listStaleOrExpired = mem.getRecall().staleOrExpired(6);
        if (durableView != null) {
            StringBuilder $this$setupCompanionPanel_u24render_u24lambda_u245 = new StringBuilder();
            StringBuilder $this$setupCompanionPanel_u24render_u24lambda_u246 = $this$setupCompanionPanel_u24render_u24lambda_u245;
            $this$setupCompanionPanel_u24render_u24lambda_u246.append("active=").append(listActive.size());
            $this$setupCompanionPanel_u24render_u24lambda_u246.append(" temp=").append(listTemporary.size());
            $this$setupCompanionPanel_u24render_u24lambda_u246.append(" stale/exp=").append(listStaleOrExpired.size());
            $this$setupCompanionPanel_u24render_u24lambda_u246.append('\n');
            if (listActive.isEmpty() && listTemporary.isEmpty()) {
                $this$setupCompanionPanel_u24render_u24lambda_u246.append("Пусто. «Запомнить тест» или накопить FG.");
            } else {
                Iterable $this$distinctBy$iv = CollectionsKt.plus((Collection) listActive, (Iterable) listTemporary);
                int $i$f$distinctBy = 0;
                HashSet set$iv = new HashSet();
                ArrayList list$iv = new ArrayList();
                for (Object e$iv : $this$distinctBy$iv) {
                    $this$distinctBy$iv = $this$distinctBy$iv;
                    listActive = listActive;
                    HashSet set$iv2 = set$iv;
                    $i$f$distinctBy = $i$f$distinctBy;
                    if (set$iv2.add(((MemoryEntry) e$iv).getId())) {
                        list$iv.add(e$iv);
                        set$iv = set$iv2;
                    } else {
                        set$iv = set$iv2;
                    }
                }
                Iterable $this$forEach$iv = CollectionsKt.take(list$iv, 6);
                int $i$f$forEach = 0;
                for (Iterator it2 = $this$forEach$iv.iterator(); it2.hasNext(); it2 = it2) {
                    Object element$iv = it2.next();
                    MemoryEntry e = (MemoryEntry) element$iv;
                    Iterable $this$forEach$iv2 = $this$forEach$iv;
                    $this$setupCompanionPanel_u24render_u24lambda_u246.append(e.getStatus().name()).append(' ');
                    $this$setupCompanionPanel_u24render_u24lambda_u246.append(StringsKt.take(e.getDisposition().name(), 4)).append(' ');
                    $this$setupCompanionPanel_u24render_u24lambda_u246.append('[').append(StringsKt.take(e.getProvenance().name(), 6)).append(IOUtils.DIR_SEPARATOR_UNIX);
                    $this$setupCompanionPanel_u24render_u24lambda_u246.append(StringsKt.take(e.getType().name(), 6)).append("] ");
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String str = String.format(Locale.US, "%.2f", Arrays.copyOf(new Object[]{Float.valueOf(e.getConfidence())}, 1));
                    Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                    $this$setupCompanionPanel_u24render_u24lambda_u246.append(str);
                    $this$setupCompanionPanel_u24render_u24lambda_u246.append(" ev=").append(e.getEvidenceCount());
                    $this$setupCompanionPanel_u24render_u24lambda_u246.append('\n');
                    $this$setupCompanionPanel_u24render_u24lambda_u246.append(StringsKt.take(e.getContent(), 70)).append('\n');
                    $i$f$forEach = $i$f$forEach;
                    $this$forEach$iv = $this$forEach$iv2;
                }
                if (!listStaleOrExpired.isEmpty()) {
                    $this$setupCompanionPanel_u24render_u24lambda_u246.append("--- stale/expired ---\n");
                    Iterable $this$forEach$iv3 = CollectionsKt.take(listStaleOrExpired, 2);
                    int $i$f$forEach2 = 0;
                    for (Object element$iv2 : $this$forEach$iv3) {
                        MemoryEntry e2 = (MemoryEntry) element$iv2;
                        $this$setupCompanionPanel_u24render_u24lambda_u246.append(e2.getStatus().name()).append(": ").append(StringsKt.take(e2.getContent(), 50)).append('\n');
                        $this$setupCompanionPanel_u24render_u24lambda_u246 = $this$setupCompanionPanel_u24render_u24lambda_u246;
                        $this$forEach$iv3 = $this$forEach$iv3;
                        $i$f$forEach2 = $i$f$forEach2;
                    }
                }
            }
            durableView.setText($this$setupCompanionPanel_u24render_u24lambda_u245.toString());
        }
        Iterable $this$distinctBy$iv2 = CollectionsKt.plus((Collection) this$0.behaviorSimBuffer, (Iterable) listRecent);
        int $i$f$distinctBy2 = 0;
        HashSet set$iv3 = new HashSet();
        ArrayList list$iv2 = new ArrayList();
        for (Object e$iv2 : $this$distinctBy$iv2) {
            $this$distinctBy$iv2 = $this$distinctBy$iv2;
            $i$f$distinctBy2 = $i$f$distinctBy2;
            if (set$iv3.add(((PhoneObservation) e$iv2).getId())) {
                list$iv2.add(e$iv2);
            }
        }
        ArrayList forLearning = list$iv2;
        BehaviorLearningService.evaluate$default(behavior, forLearning, false, 0L, 6, null);
        List suggestions = BehaviorStore.suggestions$default(behavior.getStore(), 0L, 1, null);
        if (behaviorView != null) {
            behaviorView.setText(suggestions.isEmpty() ? "Нет паттернов. Симуляция «наушники→Music ×5» или реальные повторы." : CollectionsKt.joinToString$default(CollectionsKt.take(suggestions, 3), "\n\n", null, null, 0, null, new Function1() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda85
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DashboardActivity.setupCompanionPanel$render$lambda$7((BehaviorCandidate) obj);
                }
            }, 30, null));
        }
        CompanionProactive.tick$default(CompanionProactive.INSTANCE, this$0, behavior, snap, false, false, 24, null);
        ProactiveDecision proactive = CompanionProactive.INSTANCE.visibleDecision(this$0);
        if (proactive == null) {
            if (BuildConfig.DEBUG) {
                ProactiveDecision evaluated = CompanionProactive.INSTANCE.lastEvaluatedDecision(this$0);
                if (evaluated == null || evaluated.getType() != ProactiveDecisionType.NO_OP) {
                    if (proactiveView != null) {
                        proactiveView.setVisibility(8);
                    }
                    if (proactiveView != null) {
                        proactiveView.setText("");
                    }
                } else {
                    if (proactiveView != null) {
                        proactiveView.setVisibility(0);
                    }
                    if (proactiveView != null) {
                        proactiveView.setText(setupCompanionPanel$formatProactiveDebug(evaluated));
                    }
                }
            } else {
                if (proactiveView != null) {
                    proactiveView.setVisibility(8);
                }
                if (proactiveView != null) {
                    proactiveView.setText("");
                }
            }
            if (proactiveButtons != null) {
                proactiveButtons.setVisibility(8);
            }
        } else {
            if (proactiveView != null) {
                proactiveView.setVisibility(0);
            }
            if (proactiveView != null) {
                proactiveView.setText(setupCompanionPanel$formatProactiveUserText(proactive));
            }
            if (proactiveButtons != null) {
                proactiveButtons.setVisibility(0);
            }
        }
        if (!JarvisSettings.INSTANCE.prefs(this$0).getBoolean("companion_proactive", true)) {
            if (proactiveButtons != null) {
                i = 8;
                proactiveButtons.setVisibility(8);
            } else {
                i = 8;
            }
            if (proactiveView != null) {
                proactiveView.setVisibility(i);
            }
        }
        List recent = CollectionsKt.take(CollectionsKt.asReversed(listRecent), 20);
        if (eventsView != null) {
            eventsView.setText(recent.isEmpty() ? "Пока нет событий." : CollectionsKt.joinToString$default(recent, StringUtils.LF, null, null, 0, null, new Function1() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda86
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DashboardActivity.setupCompanionPanel$render$lambda$8(fmt, (PhoneObservation) obj);
                }
            }, 30, null));
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            try {
                BrainGraph.INSTANCE.build(this$0);
                SystemDiagnosticsSnapshot snapDiag = BrainGraph.INSTANCE.diagnosticsSnapshot(this$0);
                textView = diagnosticsView;
                if (textView != null) {
                    try {
                        textView.setText(DiagnosticsFormatter.INSTANCE.render(snapDiag));
                    } catch (Throwable th) {
                        th = th;
                        Result.Companion companion2 = Result.INSTANCE;
                        objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
                    }
                }
                objM392constructorimpl = Result.m392constructorimpl(Unit.INSTANCE);
            } catch (Throwable th2) {
                th = th2;
                textView = diagnosticsView;
            }
        } catch (Throwable th3) {
            th = th3;
            textView = diagnosticsView;
        }
        Throwable it3 = Result.m395exceptionOrNullimpl(objM392constructorimpl);
        if (it3 != null && textView != null) {
            textView.setText("diagnostics unavailable: " + it3.getClass().getSimpleName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence setupCompanionPanel$render$lambda$4(MemoryPolicyDecision d) {
        Intrinsics.checkNotNullParameter(d, "d");
        MemoryCandidate c = d.getCandidate();
        String strName = d.getDisposition().name();
        String strName2 = c.getProvenance().name();
        String strName3 = c.getKind().name();
        int evidenceCount = c.getEvidenceCount();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(Locale.US, "%.2f", Arrays.copyOf(new Object[]{Float.valueOf(c.getConfidence())}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return strName + " [" + strName2 + "/" + strName3 + "] ev=" + evidenceCount + " conf=" + str + StringUtils.LF + StringsKt.take(c.getStatement(), 72);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence setupCompanionPanel$render$lambda$7(BehaviorCandidate c) {
        Intrinsics.checkNotNullParameter(c, "c");
        StringBuilder $this$setupCompanionPanel_u24render_u24lambda_u247_u240 = new StringBuilder();
        $this$setupCompanionPanel_u24render_u24lambda_u247_u240.append(c.getKind().name()).append(IOUtils.DIR_SEPARATOR_UNIX).append(c.getFamily().name());
        StringBuilder sbAppend = $this$setupCompanionPanel_u24render_u24lambda_u247_u240.append(" conf=");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(Locale.US, "%.0f%%", Arrays.copyOf(new Object[]{Float.valueOf(c.getConfidence() * 100)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        sbAppend.append(str);
        $this$setupCompanionPanel_u24render_u24lambda_u247_u240.append(" ev=").append(c.getEvidenceCount());
        $this$setupCompanionPanel_u24render_u24lambda_u247_u240.append('\n').append(c.getDescription());
        $this$setupCompanionPanel_u24render_u24lambda_u247_u240.append('\n').append("Why: ").append(StringsKt.take(c.getWhy(), 90));
        return $this$setupCompanionPanel_u24render_u24lambda_u247_u240.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence setupCompanionPanel$render$lambda$8(SimpleDateFormat $fmt, PhoneObservation o) {
        Intrinsics.checkNotNullParameter(o, "o");
        String t = $fmt.format(new Date(o.getTimestampMillis()));
        String src = StringsKt.take(o.getSource().name(), 6);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String conf = String.format(Locale.US, "%.1f", Arrays.copyOf(new Object[]{Float.valueOf(o.getConfidence())}, 1));
        Intrinsics.checkNotNullExpressionValue(conf, "format(...)");
        String strName = o.getType().name();
        String packageName = o.getPackageName();
        if (packageName == null) {
            packageName = "—";
        }
        return t + " · " + src + "@" + conf + " · " + strName + " · " + packageName;
    }

    private static final void setupCompanionPanel$applyBehaviorFeedback(BehaviorLearningService behavior, CompanionMemoryService mem, DashboardActivity this$0, TextView sourcesView, TextView contextView, TextView memoryView, TextView durableView, TextView behaviorView, TextView proactiveView, View proactiveButtons, TextView eventsView, SimpleDateFormat fmt, TextView diagnosticsView, BehaviorFeedback fb) {
        String key = setupCompanionPanel$topBehaviorKey(behavior);
        if (key == null) {
            return;
        }
        behavior.feedback(key, fb, mem);
        CompanionBehavior.INSTANCE.persistFeedback(this$0, behavior);
        setupCompanionPanel$render(this$0, sourcesView, contextView, mem, memoryView, durableView, behavior, behaviorView, proactiveView, proactiveButtons, eventsView, fmt, diagnosticsView);
    }

    private static final void setupCompanionPanel$applyProactiveFeedback(DashboardActivity this$0, BehaviorLearningService behavior, CompanionMemoryService mem, TextView sourcesView, TextView contextView, TextView memoryView, TextView durableView, TextView behaviorView, TextView proactiveView, View proactiveButtons, TextView eventsView, SimpleDateFormat fmt, TextView diagnosticsView, BehaviorFeedback fb) {
        CompanionProactive.applyFeedback$default(CompanionProactive.INSTANCE, this$0, behavior, fb, mem, null, 16, null);
        setupCompanionPanel$render(this$0, sourcesView, contextView, mem, memoryView, durableView, behavior, behaviorView, proactiveView, proactiveButtons, eventsView, fmt, diagnosticsView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupCompanionPanel$lambda$12(DashboardActivity this$0, View it) {
        PermissionsHelper.INSTANCE.openNotificationListenerSettings(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupCompanionPanel$lambda$13(DashboardActivity this$0, View it) {
        PermissionsHelper.INSTANCE.openUsageAccessSettings(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupCompanionPanel$lambda$14(DashboardActivity this$0, View it) {
        PermissionsHelper.INSTANCE.openAssistantPickerSettings(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupCompanionPanel$lambda$15(CompanionMemoryService $mem, DashboardActivity this$0, TextView $sourcesView, TextView $contextView, TextView $memoryView, TextView $durableView, BehaviorLearningService $behavior, TextView $behaviorView, TextView $proactiveView, View $proactiveButtons, TextView $eventsView, SimpleDateFormat $fmt, TextView $diagnosticsView, View it) {
        CompanionMemoryService.rememberUserStatement$default($mem, "Тест Stage 5.5: предпочитаю Jarvis как помощника", MemoryKind.PREFERENCE, 1.0f, null, 8, null);
        setupCompanionPanel$render(this$0, $sourcesView, $contextView, $mem, $memoryView, $durableView, $behavior, $behaviorView, $proactiveView, $proactiveButtons, $eventsView, $fmt, $diagnosticsView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupCompanionPanel$lambda$16(BehaviorLearningService $behavior, CompanionMemoryService $mem, DashboardActivity this$0, TextView $sourcesView, TextView $contextView, TextView $memoryView, TextView $durableView, TextView $behaviorView, TextView $proactiveView, View $proactiveButtons, TextView $eventsView, SimpleDateFormat $fmt, TextView $diagnosticsView, View it) {
        setupCompanionPanel$applyBehaviorFeedback($behavior, $mem, this$0, $sourcesView, $contextView, $memoryView, $durableView, $behaviorView, $proactiveView, $proactiveButtons, $eventsView, $fmt, $diagnosticsView, BehaviorFeedback.ACCEPT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupCompanionPanel$lambda$17(BehaviorLearningService $behavior, CompanionMemoryService $mem, DashboardActivity this$0, TextView $sourcesView, TextView $contextView, TextView $memoryView, TextView $durableView, TextView $behaviorView, TextView $proactiveView, View $proactiveButtons, TextView $eventsView, SimpleDateFormat $fmt, TextView $diagnosticsView, View it) {
        setupCompanionPanel$applyBehaviorFeedback($behavior, $mem, this$0, $sourcesView, $contextView, $memoryView, $durableView, $behaviorView, $proactiveView, $proactiveButtons, $eventsView, $fmt, $diagnosticsView, BehaviorFeedback.IGNORE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupCompanionPanel$lambda$18(BehaviorLearningService $behavior, CompanionMemoryService $mem, DashboardActivity this$0, TextView $sourcesView, TextView $contextView, TextView $memoryView, TextView $durableView, TextView $behaviorView, TextView $proactiveView, View $proactiveButtons, TextView $eventsView, SimpleDateFormat $fmt, TextView $diagnosticsView, View it) {
        setupCompanionPanel$applyBehaviorFeedback($behavior, $mem, this$0, $sourcesView, $contextView, $memoryView, $durableView, $behaviorView, $proactiveView, $proactiveButtons, $eventsView, $fmt, $diagnosticsView, BehaviorFeedback.DISABLE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupCompanionPanel$lambda$19(DashboardActivity this$0, BehaviorLearningService $behavior, CompanionMemoryService $mem, TextView $sourcesView, TextView $contextView, TextView $memoryView, TextView $durableView, TextView $behaviorView, TextView $proactiveView, View $proactiveButtons, TextView $eventsView, SimpleDateFormat $fmt, TextView $diagnosticsView, View it) {
        setupCompanionPanel$applyProactiveFeedback(this$0, $behavior, $mem, $sourcesView, $contextView, $memoryView, $durableView, $behaviorView, $proactiveView, $proactiveButtons, $eventsView, $fmt, $diagnosticsView, BehaviorFeedback.ACCEPT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupCompanionPanel$lambda$20(DashboardActivity this$0, BehaviorLearningService $behavior, CompanionMemoryService $mem, TextView $sourcesView, TextView $contextView, TextView $memoryView, TextView $durableView, TextView $behaviorView, TextView $proactiveView, View $proactiveButtons, TextView $eventsView, SimpleDateFormat $fmt, TextView $diagnosticsView, View it) {
        setupCompanionPanel$applyProactiveFeedback(this$0, $behavior, $mem, $sourcesView, $contextView, $memoryView, $durableView, $behaviorView, $proactiveView, $proactiveButtons, $eventsView, $fmt, $diagnosticsView, BehaviorFeedback.IGNORE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupCompanionPanel$lambda$21(DashboardActivity this$0, BehaviorLearningService $behavior, CompanionMemoryService $mem, TextView $sourcesView, TextView $contextView, TextView $memoryView, TextView $durableView, TextView $behaviorView, TextView $proactiveView, View $proactiveButtons, TextView $eventsView, SimpleDateFormat $fmt, TextView $diagnosticsView, View it) {
        setupCompanionPanel$applyProactiveFeedback(this$0, $behavior, $mem, $sourcesView, $contextView, $memoryView, $durableView, $behaviorView, $proactiveView, $proactiveButtons, $eventsView, $fmt, $diagnosticsView, BehaviorFeedback.DISABLE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupCompanionPanel$lambda$22(DashboardActivity this$0, TextView $sourcesView, TextView $contextView, CompanionMemoryService $mem, TextView $memoryView, TextView $durableView, BehaviorLearningService $behavior, TextView $behaviorView, TextView $proactiveView, View $proactiveButtons, TextView $eventsView, SimpleDateFormat $fmt, TextView $diagnosticsView, View it) {
        PhoneContextBus.INSTANCE.publish(new PhoneObservation(null, System.currentTimeMillis(), ObservationSource.SYSTEM_BROADCAST, ObservationType.HEADPHONES_CONNECTED, null, null, "Наушники подключены", null, Sensitivity.PUBLIC, 1.0f, 177, null));
        PhoneContextBus.INSTANCE.publish(new PhoneObservation(null, 1 + System.currentTimeMillis(), ObservationSource.ACCESSIBILITY, ObservationType.APP_FOREGROUND, "com.miui.home", null, "На переднем плане: launcher", null, null, 0.9f, 417, null));
        setupCompanionPanel$render(this$0, $sourcesView, $contextView, $mem, $memoryView, $durableView, $behavior, $behaviorView, $proactiveView, $proactiveButtons, $eventsView, $fmt, $diagnosticsView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupCompanionPanel$lambda$23(DashboardActivity this$0, TextView $sourcesView, TextView $contextView, CompanionMemoryService $mem, TextView $memoryView, TextView $durableView, BehaviorLearningService $behavior, TextView $behaviorView, TextView $proactiveView, View $proactiveButtons, TextView $eventsView, SimpleDateFormat $fmt, TextView $diagnosticsView, View it) {
        this$0.behaviorSimBuffer.clear();
        Calendar cal = Calendar.getInstance();
        int i = 11;
        int hourNow = cal.get(11);
        int d = 0;
        while (d < 5) {
            cal.setTimeInMillis(System.currentTimeMillis());
            cal.add(6, -(4 - d));
            cal.set(i, hourNow);
            cal.set(12, 0);
            cal.set(13, 0);
            cal.set(14, 0);
            long t0 = cal.getTimeInMillis();
            PhoneObservation hp = new PhoneObservation(null, t0, ObservationSource.SYSTEM_BROADCAST, ObservationType.HEADPHONES_CONNECTED, null, null, "Наушники подключены", null, Sensitivity.PUBLIC, 1.0f, 177, null);
            PhoneObservation fg = new PhoneObservation(null, t0 + AnnouncementWatcher.BRIEF_AFTER_MS, ObservationSource.ACCESSIBILITY, ObservationType.APP_FOREGROUND, "com.google.android.apps.youtube.music", null, "На переднем плане: com.google.android.apps.youtube.music", null, null, 0.9f, 417, null);
            this$0.behaviorSimBuffer.add(hp);
            this$0.behaviorSimBuffer.add(fg);
            d++;
            cal = cal;
            hourNow = hourNow;
            i = i;
        }
        setupCompanionPanel$render(this$0, $sourcesView, $contextView, $mem, $memoryView, $durableView, $behavior, $behaviorView, $proactiveView, $proactiveButtons, $eventsView, $fmt, $diagnosticsView);
    }

    /* JADX INFO: renamed from: com.jarvis.app.DashboardActivity$setupCompanionPanel$14, reason: invalid class name */
    /* JADX INFO: compiled from: DashboardActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.DashboardActivity$setupCompanionPanel$14", f = "DashboardActivity.kt", i = {}, l = {3306}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass14 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ BehaviorLearningService $behavior;
        final /* synthetic */ TextView $behaviorView;
        final /* synthetic */ TextView $contextView;
        final /* synthetic */ TextView $diagnosticsView;
        final /* synthetic */ TextView $durableView;
        final /* synthetic */ TextView $eventsView;
        final /* synthetic */ SimpleDateFormat $fmt;
        final /* synthetic */ CompanionMemoryService $mem;
        final /* synthetic */ TextView $memoryView;
        final /* synthetic */ View $proactiveButtons;
        final /* synthetic */ TextView $proactiveView;
        final /* synthetic */ TextView $sourcesView;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass14(TextView textView, TextView textView2, CompanionMemoryService companionMemoryService, TextView textView3, TextView textView4, BehaviorLearningService behaviorLearningService, TextView textView5, TextView textView6, View view, TextView textView7, SimpleDateFormat simpleDateFormat, TextView textView8, Continuation<? super AnonymousClass14> continuation) {
            super(2, continuation);
            this.$sourcesView = textView;
            this.$contextView = textView2;
            this.$mem = companionMemoryService;
            this.$memoryView = textView3;
            this.$durableView = textView4;
            this.$behavior = behaviorLearningService;
            this.$behaviorView = textView5;
            this.$proactiveView = textView6;
            this.$proactiveButtons = view;
            this.$eventsView = textView7;
            this.$fmt = simpleDateFormat;
            this.$diagnosticsView = textView8;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DashboardActivity.this.new AnonymousClass14(this.$sourcesView, this.$contextView, this.$mem, this.$memoryView, this.$durableView, this.$behavior, this.$behaviorView, this.$proactiveView, this.$proactiveButtons, this.$eventsView, this.$fmt, this.$diagnosticsView, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass14) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.jarvis.app.DashboardActivity$setupCompanionPanel$14$1, reason: invalid class name */
        /* JADX INFO: compiled from: DashboardActivity.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.jarvis.app.DashboardActivity$setupCompanionPanel$14$1", f = "DashboardActivity.kt", i = {}, l = {3307}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ BehaviorLearningService $behavior;
            final /* synthetic */ TextView $behaviorView;
            final /* synthetic */ TextView $contextView;
            final /* synthetic */ TextView $diagnosticsView;
            final /* synthetic */ TextView $durableView;
            final /* synthetic */ TextView $eventsView;
            final /* synthetic */ SimpleDateFormat $fmt;
            final /* synthetic */ CompanionMemoryService $mem;
            final /* synthetic */ TextView $memoryView;
            final /* synthetic */ View $proactiveButtons;
            final /* synthetic */ TextView $proactiveView;
            final /* synthetic */ TextView $sourcesView;
            int label;
            final /* synthetic */ DashboardActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(DashboardActivity dashboardActivity, TextView textView, TextView textView2, CompanionMemoryService companionMemoryService, TextView textView3, TextView textView4, BehaviorLearningService behaviorLearningService, TextView textView5, TextView textView6, View view, TextView textView7, SimpleDateFormat simpleDateFormat, TextView textView8, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = dashboardActivity;
                this.$sourcesView = textView;
                this.$contextView = textView2;
                this.$mem = companionMemoryService;
                this.$memoryView = textView3;
                this.$durableView = textView4;
                this.$behavior = behaviorLearningService;
                this.$behaviorView = textView5;
                this.$proactiveView = textView6;
                this.$proactiveButtons = view;
                this.$eventsView = textView7;
                this.$fmt = simpleDateFormat;
                this.$diagnosticsView = textView8;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, this.$sourcesView, this.$contextView, this.$mem, this.$memoryView, this.$durableView, this.$behavior, this.$behaviorView, this.$proactiveView, this.$proactiveButtons, this.$eventsView, this.$fmt, this.$diagnosticsView, continuation);
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
                        SharedFlow<PhoneObservation> observations = PhoneContextBus.INSTANCE.getObservations();
                        final DashboardActivity dashboardActivity = this.this$0;
                        final TextView textView = this.$sourcesView;
                        final TextView textView2 = this.$contextView;
                        final CompanionMemoryService companionMemoryService = this.$mem;
                        final TextView textView3 = this.$memoryView;
                        final TextView textView4 = this.$durableView;
                        final BehaviorLearningService behaviorLearningService = this.$behavior;
                        final TextView textView5 = this.$behaviorView;
                        final TextView textView6 = this.$proactiveView;
                        final View view = this.$proactiveButtons;
                        final TextView textView7 = this.$eventsView;
                        final SimpleDateFormat simpleDateFormat = this.$fmt;
                        final TextView textView8 = this.$diagnosticsView;
                        this.label = 1;
                        if (observations.collect(new FlowCollector() { // from class: com.jarvis.app.DashboardActivity.setupCompanionPanel.14.1.1
                            public final Object emit(PhoneObservation it, Continuation<? super Unit> continuation) {
                                DashboardActivity.setupCompanionPanel$render(dashboardActivity, textView, textView2, companionMemoryService, textView3, textView4, behaviorLearningService, textView5, textView6, view, textView7, simpleDateFormat, textView8);
                                return Unit.INSTANCE;
                            }

                            @Override // kotlinx.coroutines.flow.FlowCollector
                            public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                                return emit((PhoneObservation) value, (Continuation<? super Unit>) $completion);
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
                    if (RepeatOnLifecycleKt.repeatOnLifecycle(DashboardActivity.this, Lifecycle.State.STARTED, new AnonymousClass1(DashboardActivity.this, this.$sourcesView, this.$contextView, this.$mem, this.$memoryView, this.$durableView, this.$behavior, this.$behaviorView, this.$proactiveView, this.$proactiveButtons, this.$eventsView, this.$fmt, this.$diagnosticsView, null), this) == coroutine_suspended) {
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

    private final void setupModelsPanel(View screen) {
        LinearLayout list = (LinearLayout) screen.findViewById(R.id.modelsList);
        if (list == null) {
            return;
        }
        TextView pathHint = (TextView) screen.findViewById(R.id.modelsPathHint);
        ModelManager mgr = ModelManager.INSTANCE.get(this);
        if (pathHint != null) {
            pathHint.setText(mgr.modelsRoot().getAbsolutePath());
        }
        mgr.refreshAll();
        setupModelsPanel$render(list, mgr, this);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new C00561(mgr, screen, list, this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupModelsPanel$render(LinearLayout list, ModelManager mgr, DashboardActivity this$0) {
        list.removeAllViews();
        Map<String, ModelManager.Status> value = mgr.getStatuses().getValue();
        Iterable $this$forEach$iv = ModelCatalog.INSTANCE.getALL();
        for (Object element$iv : $this$forEach$iv) {
            ModelCatalog.Entry entry = (ModelCatalog.Entry) element$iv;
            View card = LayoutInflater.from(this$0).inflate(R.layout.item_model_card, (ViewGroup) list, false);
            TextView title = (TextView) card.findViewById(R.id.modelTitle);
            TextView subtitle = (TextView) card.findViewById(R.id.modelSubtitle);
            TextView statusTag = (TextView) card.findViewById(R.id.modelStatus);
            ProgressBar progress = (ProgressBar) card.findViewById(R.id.modelProgress);
            TextView message = (TextView) card.findViewById(R.id.modelMessage);
            Button btnAction = (Button) card.findViewById(R.id.btnModelAction);
            Button btnDelete = (Button) card.findViewById(R.id.btnModelDelete);
            title.setText(entry.getTitle());
            long mb = entry.getSizeBytes() / ((long) DurationKt.NANOS_IN_MILLIS);
            subtitle.setText(entry.getSubtitle() + " · " + mb + " МБ");
            ModelManager.Status status = value.get(entry.getId());
            if (status == null) {
                status = new ModelManager.Status(entry.getId(), ModelManager.State.MISSING, 0.0f, null, 0L, 0L, 60, null);
            }
            ModelManager.Status st = status;
            setupModelsPanel$render$lambda$0$applyStatus(statusTag, progress, message, btnAction, btnDelete, mb, this$0, entry, mgr, list, st);
            list.addView(card);
            value = value;
        }
        this$0.addCloudModelCard(list, "Облако · agnes-2.5-flash", "FAST", NaraRouterDefaults.MODEL_FAST);
        this$0.addCloudModelCard(list, "Облако · mistral-medium-3-5", "QUALITY", NaraRouterDefaults.MODEL_QUALITY);
    }

    private static final void setupModelsPanel$render$lambda$0$applyStatus(TextView statusTag, ProgressBar progress, final TextView message, Button btnAction, Button btnDelete, final long mb, final DashboardActivity this$0, final ModelCatalog.Entry $entry, final ModelManager mgr, final LinearLayout list, ModelManager.Status s) {
        int color;
        CharSequence charSequence;
        statusTag.setText(s.getState().name());
        switch (WhenMappings.$EnumSwitchMapping$3[s.getState().ordinal()]) {
            case 1:
                color = Color.parseColor("#62D9DF");
                break;
            case 2:
                color = Color.parseColor("#E0AD55");
                break;
            case 3:
            case 4:
                color = Color.parseColor("#E07070");
                break;
            default:
                color = Color.parseColor("#E0AD55");
                break;
        }
        statusTag.setTextColor(color);
        switch (WhenMappings.$EnumSwitchMapping$3[s.getState().ordinal()]) {
            case 1:
                progress.setVisibility(0);
                progress.setProgress(100);
                message.setVisibility(0);
                message.setText(mb + " МБ · готово");
                boolean active = Intrinsics.areEqual(JarvisSettings.INSTANCE.prefs(this$0).getString("active_chat_model", null), $entry.getRelativePath());
                btnAction.setEnabled($entry.getKind() == ModelCatalog.Kind.CHAT_LLM);
                if (!active) {
                    charSequence = $entry.getKind() == ModelCatalog.Kind.CHAT_LLM ? "ВЫБРАТЬ" : "ПРОВЕРИТЬ";
                }
                btnAction.setText(charSequence);
                btnAction.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda50
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        DashboardActivity.setupModelsPanel$render$lambda$0$applyStatus$2($entry, this$0, mgr, message, mb, list, view);
                    }
                });
                btnDelete.setVisibility(0);
                btnDelete.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda51
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        DashboardActivity.setupModelsPanel$render$lambda$0$applyStatus$3(mgr, $entry, list, this$0, view);
                    }
                });
                break;
            case 2:
                progress.setVisibility(0);
                progress.setProgress(RangesKt.coerceIn((int) (s.getProgress() * 100), 0, 100));
                message.setVisibility(0);
                String message2 = s.getMessage();
                if (StringsKt.isBlank(message2)) {
                    long bytesDownloaded = s.getBytesDownloaded();
                    long j = DurationKt.NANOS_IN_MILLIS;
                    message2 = (bytesDownloaded / j) + " / " + (s.getBytesTotal() / j) + " МБ";
                }
                message.setText(message2);
                btnAction.setText("Отмена");
                btnAction.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda49
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        DashboardActivity.setupModelsPanel$render$lambda$0$applyStatus$1(mgr, $entry, view);
                    }
                });
                btnDelete.setVisibility(8);
                break;
            default:
                progress.setVisibility(8);
                message.setVisibility(!StringsKt.isBlank(s.getMessage()) ? 0 : 8);
                message.setText(s.getMessage());
                btnAction.setEnabled(true);
                btnAction.setText("Скачать");
                btnAction.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda52
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        DashboardActivity.setupModelsPanel$render$lambda$0$applyStatus$4(mgr, $entry, view);
                    }
                });
                btnDelete.setVisibility(8);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupModelsPanel$render$lambda$0$applyStatus$1(ModelManager $mgr, ModelCatalog.Entry $entry, View it) {
        $mgr.cancel($entry.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupModelsPanel$render$lambda$0$applyStatus$2(ModelCatalog.Entry $entry, DashboardActivity this$0, ModelManager $mgr, TextView $message, long $mb, LinearLayout $list, View it) {
        if ($entry.getKind() == ModelCatalog.Kind.CHAT_LLM) {
            JarvisSettings.INSTANCE.prefs(this$0).edit().putString("active_chat_model", $entry.getRelativePath()).apply();
            BrainGraph.INSTANCE.invalidateChatLlm();
            setupModelsPanel$render($list, $mgr, this$0);
        } else {
            $mgr.refreshAll();
            $message.setText($mb + " МБ · файл на месте");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupModelsPanel$render$lambda$0$applyStatus$3(ModelManager $mgr, ModelCatalog.Entry $entry, LinearLayout $list, DashboardActivity this$0, View it) {
        $mgr.delete($entry.getId());
        setupModelsPanel$render($list, $mgr, this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupModelsPanel$render$lambda$0$applyStatus$4(ModelManager $mgr, ModelCatalog.Entry $entry, View it) {
        $mgr.download($entry.getId());
    }

    /* JADX INFO: renamed from: com.jarvis.app.DashboardActivity$setupModelsPanel$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DashboardActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.DashboardActivity$setupModelsPanel$1", f = "DashboardActivity.kt", i = {}, l = {3406}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C00561 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ LinearLayout $list;
        final /* synthetic */ ModelManager $mgr;
        final /* synthetic */ View $screen;
        int label;
        final /* synthetic */ DashboardActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00561(ModelManager modelManager, View view, LinearLayout linearLayout, DashboardActivity dashboardActivity, Continuation<? super C00561> continuation) {
            super(2, continuation);
            this.$mgr = modelManager;
            this.$screen = view;
            this.$list = linearLayout;
            this.this$0 = dashboardActivity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C00561(this.$mgr, this.$screen, this.$list, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00561) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    StateFlow<Map<String, ModelManager.Status>> statuses = this.$mgr.getStatuses();
                    final View view = this.$screen;
                    final LinearLayout linearLayout = this.$list;
                    final ModelManager modelManager = this.$mgr;
                    final DashboardActivity dashboardActivity = this.this$0;
                    this.label = 1;
                    if (statuses.collect(new FlowCollector() { // from class: com.jarvis.app.DashboardActivity.setupModelsPanel.1.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit((Map<String, ModelManager.Status>) value, (Continuation<? super Unit>) $completion);
                        }

                        public final Object emit(Map<String, ModelManager.Status> map, Continuation<? super Unit> continuation) {
                            View viewFindViewById = view.findViewById(R.id.modelsPanel);
                            boolean z = false;
                            if (viewFindViewById != null && viewFindViewById.getVisibility() == 0) {
                                z = true;
                            }
                            if (z) {
                                DashboardActivity.setupModelsPanel$render(linearLayout, modelManager, dashboardActivity);
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

    private final void addCloudModelCard(LinearLayout list, String titleText, final String profile, String modelId) {
        View card = LayoutInflater.from(this).inflate(R.layout.item_model_card, (ViewGroup) list, false);
        ((TextView) card.findViewById(R.id.modelTitle)).setText(titleText);
        ((TextView) card.findViewById(R.id.modelSubtitle)).setText("OpenAI-compatible · " + modelId);
        final TextView status = (TextView) card.findViewById(R.id.modelStatus);
        final boolean active = StringsKt.equals(JarvisSettings.INSTANCE.chatProfile(this), profile, true);
        status.setText(active ? "ACTIVE" : "CLOUD");
        ProgressBar progress = (ProgressBar) card.findViewById(R.id.modelProgress);
        progress.setVisibility(0);
        progress.setProgress(active ? 100 : 8);
        final TextView message = (TextView) card.findViewById(R.id.modelMessage);
        message.setVisibility(0);
        message.setText("пинг не измерен");
        ((Button) card.findViewById(R.id.btnModelDelete)).setVisibility(8);
        Button action = (Button) card.findViewById(R.id.btnModelAction);
        action.setText("PING");
        action.setOnClickListener(new View.OnClickListener() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda27
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DashboardActivity.addCloudModelCard$lambda$0(active, this, profile, status, message, view);
            }
        });
        list.addView(card);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addCloudModelCard$lambda$0(boolean $active, final DashboardActivity this$0, String $profile, final TextView $status, final TextView $message, View it) {
        if (!$active) {
            JarvisSettings.INSTANCE.setChatProfile(this$0, $profile);
            $status.setText("ACTIVE");
        }
        new Thread(new Runnable() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda28
            @Override // java.lang.Runnable
            public final void run() {
                DashboardActivity.addCloudModelCard$lambda$0$0(this.f$0, $message, $status);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addCloudModelCard$lambda$0$0(DashboardActivity this$0, final TextView $message, final TextView $status) {
        Object objM392constructorimpl;
        long t0 = SystemClock.elapsedRealtime();
        String baseUrl = StringsKt.trimEnd(StringsKt.trim((CharSequence) JarvisSettings.INSTANCE.cloudBaseUrl(this$0)).toString(), IOUtils.DIR_SEPARATOR_UNIX);
        long jLongValue = -1;
        if (!StringsKt.isBlank(baseUrl)) {
            try {
                Result.Companion companion = Result.INSTANCE;
                URLConnection uRLConnectionOpenConnection = new URL(baseUrl + "/models").openConnection();
                Intrinsics.checkNotNull(uRLConnectionOpenConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
                HttpURLConnection conn = (HttpURLConnection) uRLConnectionOpenConnection;
                conn.setConnectTimeout(AICompletionRequest.MAX_USER);
                conn.setReadTimeout(AICompletionRequest.MAX_USER);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Authorization", "Bearer " + JarvisSettings.INSTANCE.cloudApiKey(this$0));
                conn.getResponseCode();
                objM392constructorimpl = Result.m392constructorimpl(Long.valueOf(SystemClock.elapsedRealtime() - t0));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m398isFailureimpl(objM392constructorimpl)) {
                objM392constructorimpl = -1L;
            }
            jLongValue = ((Number) objM392constructorimpl).longValue();
        }
        final long ms = jLongValue;
        this$0.runOnUiThread(new Runnable() { // from class: com.jarvis.app.DashboardActivity$$ExternalSyntheticLambda24
            @Override // java.lang.Runnable
            public final void run() {
                DashboardActivity.addCloudModelCard$lambda$0$0$1($message, ms, $status);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addCloudModelCard$lambda$0$0$1(TextView $message, long $ms, TextView $status) {
        $message.setText($ms >= 0 ? "Cloud · " + $ms + " мс" : "Cloud · нет ответа");
        if ($ms >= 0) {
            $status.setText($ms + "ms");
        }
    }
}
