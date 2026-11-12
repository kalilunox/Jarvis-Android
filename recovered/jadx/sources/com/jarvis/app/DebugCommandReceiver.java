package com.jarvis.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.AudioRecord;
import android.media.audiofx.AcousticEchoCanceler;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import com.jarvis.app.companion.CompanionProactive;
import com.jarvis.app.proactive.PhoneStateReader;
import com.jarvis.app.proactive.ProactiveActionReceiver;
import com.jarvis.app.proactive.ProactiveDelivery;
import com.jarvis.app.proactive.ProactiveDeliveryLog;
import com.jarvis.app.proactive.ProactiveNotification;
import com.jarvis.app.ui.JarvisActivity;
import com.jarvis.asr.AsrChunkResult;
import com.jarvis.asr.AsrModelSpec;
import com.jarvis.asr.BufferedEnAsr;
import com.jarvis.asr.CommandPcmRefiner;
import com.jarvis.asr.OfflineMultilingualAsr;
import com.jarvis.asr.SherpaAsrSession;
import com.jarvis.audio.AudioCapture;
import com.jarvis.core.aioutput.StructuredAiLimits;
import com.jarvis.core.autonomy.AutonomyActionPlan;
import com.jarvis.core.autonomy.AutonomyPlanStatus;
import com.jarvis.core.autonomy.AutonomyVerdictType;
import com.jarvis.core.autonomy.ProposedAction;
import com.jarvis.core.autonomy.ProposedActionMapper;
import com.jarvis.core.autonomy.SafeAutonomousResult;
import com.jarvis.core.common.ToolExecutor;
import com.jarvis.core.common.model.AssistantResponse;
import com.jarvis.core.common.model.IntentType;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import com.jarvis.core.common.model.UserRequest;
import com.jarvis.core.companion.ObservationSource;
import com.jarvis.core.companion.ObservationType;
import com.jarvis.core.companion.PhoneContext;
import com.jarvis.core.companion.PhoneContextBus;
import com.jarvis.core.companion.PhoneObservation;
import com.jarvis.core.companion.Sensitivity;
import com.jarvis.core.nlp.CommandTextSelector;
import com.jarvis.core.privacy.SensitivityClassifier;
import com.jarvis.core.proactive.DeliveryDecider;
import com.jarvis.core.proactive.DeliveryDecision;
import com.jarvis.core.proactive.PhoneStateSnapshot;
import com.jarvis.core.proactive.ProactiveDecision;
import com.jarvis.core.proactive.ProactiveDecisionType;
import com.jarvis.core.proactive.ProactiveReasonCode;
import com.jarvis.core.proactive.ProactiveText;
import com.jarvis.core.storage.ConversationJournal;
import com.jarvis.core.storage.ConversationRepository;
import com.jarvis.core.storage.FactPolicies;
import com.jarvis.core.storage.FactsRepository;
import com.jarvis.core.storage.JarvisDb;
import com.jarvis.core.storage.JarvisSchema;
import com.jarvis.core.storage.RetentionPolicy;
import com.jarvis.tts.JarvisTts;
import com.jarvis.tts.JarvisTtsConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.Dispatchers;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: DebugCommandReceiver.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u0000 #2\u00020\u0001:\u0001#B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0002J\b\u0010\u000b\u001a\u00020\u0005H\u0002J\u0018\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0018\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0018\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\b\u0010\u0015\u001a\u00020\u0005H\u0003J\u0010\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0018\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001aH\u0002J\u0018\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\u001f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0018\u0010 \u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010!\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010\"\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002¨\u0006$"}, d2 = {"Lcom/jarvis/app/DebugCommandReceiver;", "Landroid/content/BroadcastReceiver;", "<init>", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "handleSanitize", "handleContextSnapshot", "handleMemory", "handleProactiveState", "handleProactive", "handleProactiveAction", "handleProactiveClear", "handleCommand", "handleKeys", "handleKeysSet", "handleKeysClear", "handleEchoProbe", "handleWidgets", "handleTtsDump", "handleTtsSample", "probeSearch", "", DebugCommandReceiver.EXTRA_KEY, "model", "handleVoice", "startVoice", "stopVoice", "handleSettings", "handleAutonomy", "handleAsrTest", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DebugCommandReceiver extends BroadcastReceiver {
    public static final String ACTION_ASR_TEST = "com.jarvis.assistant.ASR_TEST";
    public static final String ACTION_DEBUG_AUTONOMY = "com.jarvis.assistant.DEBUG_AUTONOMY";
    public static final String ACTION_DEBUG_COMMAND = "com.jarvis.assistant.DEBUG_COMMAND";
    public static final String ACTION_DEBUG_CONTEXT = "com.jarvis.assistant.DEBUG_CONTEXT";
    public static final String ACTION_DEBUG_ECHO_PROBE = "com.jarvis.assistant.DEBUG_ECHO_PROBE";
    public static final String ACTION_DEBUG_KEYS = "com.jarvis.assistant.DEBUG_KEYS";
    public static final String ACTION_DEBUG_KEYS_CLEAR = "com.jarvis.assistant.DEBUG_KEYS_CLEAR";
    public static final String ACTION_DEBUG_KEYS_SET = "com.jarvis.assistant.DEBUG_KEYS_SET";
    public static final String ACTION_DEBUG_MEMORY = "com.jarvis.assistant.DEBUG_MEMORY";
    public static final String ACTION_DEBUG_PROACTIVE = "com.jarvis.assistant.DEBUG_PROACTIVE";
    public static final String ACTION_DEBUG_PROACTIVE_ACTION = "com.jarvis.assistant.DEBUG_PROACTIVE_ACTION";
    public static final String ACTION_DEBUG_PROACTIVE_CLEAR = "com.jarvis.assistant.DEBUG_PROACTIVE_CLEAR";
    public static final String ACTION_DEBUG_PROACTIVE_STATE = "com.jarvis.assistant.DEBUG_PROACTIVE_STATE";
    public static final String ACTION_DEBUG_SANITIZE = "com.jarvis.assistant.DEBUG_SANITIZE";
    public static final String ACTION_DEBUG_SETTINGS = "com.jarvis.assistant.DEBUG_SETTINGS";
    public static final String ACTION_DEBUG_TTS = "com.jarvis.assistant.DEBUG_TTS";
    public static final String ACTION_DEBUG_TTS_DUMP = "com.jarvis.assistant.DEBUG_TTS_DUMP";
    public static final String ACTION_DEBUG_VOICE = "com.jarvis.assistant.DEBUG_VOICE";
    public static final String ACTION_DEBUG_WIDGETS = "com.jarvis.assistant.DEBUG_WIDGETS";
    private static final String EXTRA_ACTION = "action";
    private static final String EXTRA_COMMAND = "command";
    private static final String EXTRA_DELIVERY_ID = "deliveryId";
    private static final String EXTRA_FAKE_STATE = "fakeState";
    private static final String EXTRA_KEY = "key";
    private static final String EXTRA_LOG = "log";
    private static final String EXTRA_MODE = "mode";
    private static final String EXTRA_PACKAGE = "package";
    private static final String EXTRA_PROBE = "probe";
    private static final String EXTRA_PROVIDER = "provider";
    private static final String EXTRA_TEXT = "text";
    private static final String PROACTIVE_TAG = "Jarvis.ProactiveDelivery";
    private static final String SYNTHETIC_PATTERN_KEY = "debug:synthetic";
    private static final String SYNTHETIC_PATTERN_PREFIX = "debug:";
    private static final String SYNTHETIC_TEXT = "Синтетика: проверка доставки";
    private static final String TAG = "Jarvis.Debug";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Object asrTestLock = new Object();

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) throws IOException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        String action = intent.getAction();
        Bundle extras = intent.getExtras();
        Log.d(TAG, "onReceive action=" + action + " extras=" + (extras != null ? extras.keySet() : null));
        String action2 = intent.getAction();
        if (action2 != null) {
            switch (action2.hashCode()) {
                case -1423506865:
                    if (action2.equals(ACTION_DEBUG_KEYS_SET) && BuildConfig.DEBUG) {
                        handleKeysSet(context, intent);
                    }
                    break;
                case -1378346775:
                    if (action2.equals(ACTION_DEBUG_PROACTIVE_CLEAR) && BuildConfig.DEBUG) {
                        handleProactiveClear(context, intent);
                    }
                    break;
                case -1363335379:
                    if (action2.equals(ACTION_DEBUG_PROACTIVE_STATE) && BuildConfig.DEBUG) {
                        handleProactiveState(context);
                    }
                    break;
                case -1270931461:
                    if (action2.equals(ACTION_DEBUG_PROACTIVE) && BuildConfig.DEBUG) {
                        handleProactive(context, intent);
                    }
                    break;
                case -1052963367:
                    if (action2.equals(ACTION_DEBUG_MEMORY) && BuildConfig.DEBUG) {
                        handleMemory(context, intent);
                    }
                    break;
                case -995192518:
                    if (action2.equals(ACTION_DEBUG_VOICE)) {
                        handleVoice(context, intent);
                        break;
                    }
                    break;
                case -511912498:
                    if (action2.equals(ACTION_DEBUG_ECHO_PROBE) && BuildConfig.DEBUG) {
                        handleEchoProbe();
                    }
                    break;
                case -508808453:
                    if (action2.equals(ACTION_DEBUG_SETTINGS)) {
                        handleSettings(context, intent);
                        break;
                    }
                    break;
                case -503933900:
                    if (action2.equals(ACTION_DEBUG_AUTONOMY)) {
                        handleAutonomy(context, intent);
                        break;
                    }
                    break;
                case 54533953:
                    if (action2.equals(ACTION_DEBUG_SANITIZE) && BuildConfig.DEBUG) {
                        handleSanitize(intent);
                    }
                    break;
                case 155807482:
                    if (action2.equals(ACTION_DEBUG_PROACTIVE_ACTION) && BuildConfig.DEBUG) {
                        handleProactiveAction(context, intent);
                    }
                    break;
                case 334157819:
                    if (action2.equals(ACTION_DEBUG_TTS) && BuildConfig.DEBUG) {
                        handleTtsSample(context, intent);
                    }
                    break;
                case 1440433757:
                    if (action2.equals(ACTION_ASR_TEST)) {
                        handleAsrTest(context, intent);
                        break;
                    }
                    break;
                case 1643042744:
                    if (action2.equals(ACTION_DEBUG_TTS_DUMP) && BuildConfig.DEBUG) {
                        handleTtsDump(context, intent);
                    }
                    break;
                case 1718987091:
                    if (action2.equals(ACTION_DEBUG_COMMAND)) {
                        handleCommand(context, intent);
                        break;
                    }
                    break;
                case 1768675532:
                    if (action2.equals(ACTION_DEBUG_KEYS) && BuildConfig.DEBUG) {
                        handleKeys(context);
                    }
                    break;
                case 2089890042:
                    if (action2.equals(ACTION_DEBUG_KEYS_CLEAR) && BuildConfig.DEBUG) {
                        handleKeysClear(context, intent);
                    }
                    break;
                case 2108930231:
                    if (action2.equals(ACTION_DEBUG_WIDGETS) && BuildConfig.DEBUG) {
                        handleWidgets(context);
                    }
                    break;
            }
        }
    }

    private final void handleSanitize(Intent intent) {
        String it;
        String raw = intent.getStringExtra("text");
        if (raw == null) {
            raw = "";
        }
        if (raw.length() == 0) {
            Log.d(TAG, "sanitize: --es text \"...\" required");
            return;
        }
        String stringExtra = intent.getStringExtra(EXTRA_PACKAGE);
        String str = null;
        if (stringExtra != null && (it = StringsKt.trim((CharSequence) stringExtra).toString()) != null && !StringsKt.isBlank(it)) {
            str = it;
        }
        String pkg = str;
        Sensitivity verdict = SensitivityClassifier.INSTANCE.classify(raw, pkg);
        String masked = SensitivityClassifier.INSTANCE.mask(raw);
        Log.d(TAG, "sanitize: len=" + raw.length() + " sensitivity=" + verdict + " packageSensitive=" + SensitivityClassifier.INSTANCE.isSensitivePackage(pkg) + " changed=" + (true ^ Intrinsics.areEqual(masked, raw)) + " masked=\"" + masked + "\"");
    }

    private final void handleContextSnapshot() {
        Object objM392constructorimpl;
        PhoneContext ctx = PhoneContextBus.INSTANCE.current();
        Log.d(TAG, "context: " + ctx.describe());
        try {
            Result.Companion companion = Result.INSTANCE;
            DebugCommandReceiver debugCommandReceiver = this;
            objM392constructorimpl = Result.m392constructorimpl(PhoneContextBus.INSTANCE.recent(15));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        List listEmptyList = CollectionsKt.emptyList();
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = listEmptyList;
        }
        List recent = (List) objM392constructorimpl;
        List $this$forEach$iv = recent;
        for (Object element$iv : $this$forEach$iv) {
            PhoneObservation o = (PhoneObservation) element$iv;
            ObservationType type = o.getType();
            ObservationSource source = o.getSource();
            String packageName = o.getPackageName();
            if (packageName == null) {
                packageName = "—";
            }
            Log.d(TAG, "context.event type=" + type + " source=" + source + " pkg=" + packageName + " sensitivity=" + o.getSensitivity());
        }
        int size = recent.size();
        List $this$map$iv = recent;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            PhoneObservation it = (PhoneObservation) item$iv$iv;
            destination$iv$iv.add(it.getType());
        }
        Log.d(TAG, "context.events=" + size + " types=" + CollectionsKt.joinToString$default(CollectionsKt.distinct((List) destination$iv$iv), ",", null, null, 0, null, null, 62, null));
    }

    private final void handleMemory(Context context, Intent intent) throws IOException {
        boolean z;
        Context app = context.getApplicationContext();
        long now = System.currentTimeMillis();
        JarvisDb.Companion companion = JarvisDb.INSTANCE;
        Intrinsics.checkNotNull(app);
        JarvisDb db = companion.get(app);
        FactsRepository facts = new FactsRepository(db);
        ConversationRepository conversations = new ConversationRepository(db);
        File dbFile = app.getDatabasePath(JarvisSchema.DB_NAME);
        File walFile = new File(dbFile.getPath() + "-wal");
        SharedPreferences prefs = app.getSharedPreferences("jarvis_prefs", 0);
        Log.d(TAG, "memory: schema=v" + db.getReadableDatabase().getVersion() + " dbBytes=" + dbFile.length() + " walBytes=" + (walFile.exists() ? walFile.length() : 0L));
        Log.d(TAG, "memory: facts=" + facts.count() + " messages=" + conversations.count() + " keep=" + conversations.keepCount());
        Pair<Long, Long> pairBoundaries = conversations.boundaries();
        Log.d(TAG, "memory: newestAgeMs=" + RetentionPolicy.INSTANCE.ageMs(pairBoundaries.getSecond().longValue(), now) + " oldestAgeMs=" + RetentionPolicy.INSTANCE.ageMs(pairBoundaries.getFirst().longValue(), now) + " ttlDays=90");
        Log.d(TAG, "memory: migratedFacts=" + prefs.getBoolean(FactPolicies.MIGRATED_FLAG, false) + " journalInstalled=" + ConversationJournal.INSTANCE.isInstalled() + " session=" + StringsKt.take(ConversationJournal.INSTANCE.getSessionId(), 8));
        String stringExtra = intent.getStringExtra(EXTRA_PROBE);
        String probe = stringExtra != null ? StringsKt.trim((CharSequence) stringExtra).toString() : null;
        String str = probe;
        if (!(str == null || str.length() == 0)) {
            boolean inMessages = conversations.containsText(probe);
            Iterable $this$any$iv = FactsRepository.list$default(facts, 0L, 1, null);
            int $i$f$any = 0;
            if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
                z = false;
            } else {
                for (Object element$iv : $this$any$iv) {
                    FactsRepository.StoredFact it = (FactsRepository.StoredFact) element$iv;
                    Iterable $this$any$iv2 = $this$any$iv;
                    int $i$f$any2 = $i$f$any;
                    JarvisDb db2 = db;
                    if (StringsKt.contains$default((CharSequence) it.getText(), (CharSequence) probe, false, 2, (Object) null)) {
                        z = true;
                    } else {
                        $this$any$iv = $this$any$iv2;
                        $i$f$any = $i$f$any2;
                        db = db2;
                    }
                }
                z = false;
            }
            boolean inFacts = z;
            Log.d(TAG, "memory: probeLen=" + probe.length() + " inMessages=" + inMessages + " inFacts=" + inFacts);
        }
    }

    private final void handleProactiveState(Context context) {
        PhoneStateSnapshot state = PhoneStateReader.INSTANCE.read(context);
        Log.i(PROACTIVE_TAG, "state ringer=" + state.getRingerMode() + " filter=" + state.getInterruptionFilter() + " call=" + state.getCallActive() + " session=" + state.getJarvisSessionActive() + " speaking=" + state.getJarvisSpeaking() + " notifications=" + state.getNotificationsAllowed() + " voice=" + state.getVoiceAvailable());
        DeliveryDecision decision = DeliveryDecider.INSTANCE.decide(ProactiveDecisionType.SUGGESTION, state);
        Log.i(PROACTIVE_TAG, "state decision type=SUGGESTION mode=" + decision.getMode() + " reason=" + decision.getReason());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:25:0x0059  */
    /* JADX WARN: Code duplicated, block: B:26:0x005b  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final void handleProactive(Context context, Intent intent) {
        String lowerCase;
        String lowerCase2;
        String string;
        String string2;
        Context app = context.getApplicationContext();
        String stringExtra = intent.getStringExtra(EXTRA_MODE);
        String str = null;
        if (stringExtra == null || (string2 = StringsKt.trim((CharSequence) stringExtra).toString()) == null) {
            lowerCase = null;
        } else {
            lowerCase = string2.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        }
        String modeRaw = lowerCase;
        String str2 = DebugKt.DEBUG_PROPERTY_VALUE_AUTO;
        if (modeRaw != null) {
            switch (modeRaw.hashCode()) {
                case -902327211:
                    if (!modeRaw.equals("silent")) {
                        Log.d(TAG, "proactive: unknown mode=" + modeRaw + ", using auto");
                    } else {
                        str2 = modeRaw;
                    }
                    break;
                case 0:
                    if (!modeRaw.equals("")) {
                        Log.d(TAG, "proactive: unknown mode=" + modeRaw + ", using auto");
                    }
                    break;
                case 3005871:
                    if (!modeRaw.equals(DebugKt.DEBUG_PROPERTY_VALUE_AUTO)) {
                        Log.d(TAG, "proactive: unknown mode=" + modeRaw + ", using auto");
                    }
                    break;
                case 112386354:
                    if (!modeRaw.equals("voice")) {
                        Log.d(TAG, "proactive: unknown mode=" + modeRaw + ", using auto");
                    } else {
                        str2 = modeRaw;
                    }
                    break;
                default:
                    Log.d(TAG, "proactive: unknown mode=" + modeRaw + ", using auto");
                    break;
            }
        }
        String mode = str2;
        String stringExtra2 = intent.getStringExtra(EXTRA_FAKE_STATE);
        if (stringExtra2 == null || (string = StringsKt.trim((CharSequence) stringExtra2).toString()) == null) {
            lowerCase2 = null;
        } else {
            lowerCase2 = string.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
        }
        String fakeRaw = lowerCase2;
        String str3 = fakeRaw;
        if (!(str3 == null || StringsKt.isBlank(str3))) {
            if (PhoneStateReader.INSTANCE.knownFakeStates().contains(fakeRaw)) {
                str = fakeRaw;
            } else {
                Log.d(TAG, "proactive: unknown fakeState=" + fakeRaw + ", using real state");
            }
        }
        String fake = str;
        PhoneStateReader phoneStateReader = PhoneStateReader.INSTANCE;
        PhoneStateReader phoneStateReader2 = PhoneStateReader.INSTANCE;
        Intrinsics.checkNotNull(app);
        PhoneStateSnapshot state = phoneStateReader.applyFake(phoneStateReader2.read(app), fake);
        long now = System.currentTimeMillis();
        ProactiveDecision decision = new ProactiveDecision(ProactiveDecisionType.SUGGESTION, SYNTHETIC_PATTERN_KEY, null, "debug:synthetic:" + now + ":" + UUID.randomUUID(), ProactiveReasonCode.SELECTED, SYNTHETIC_TEXT, 1.0f, 1.0f, now, null, null, null, 3588, null);
        ProactiveText text = new ProactiveText(SYNTHETIC_TEXT, "Jarvis: проверка доставки", SYNTHETIC_TEXT);
        ProactiveDelivery.Outcome outcome = ProactiveDelivery.INSTANCE.deliver(app, decision, text, fake, mode);
        String str4 = fake == null ? PhoneStateReader.FAKE_NONE : fake;
        Log.i(PROACTIVE_TAG, "debug proactive mode=" + mode + " fake=" + str4 + " state ringer=" + state.getRingerMode() + " filter=" + state.getInterruptionFilter() + " call=" + state.getCallActive() + " session=" + state.getJarvisSessionActive() + " speaking=" + state.getJarvisSpeaking() + " notifications=" + state.getNotificationsAllowed() + " voice=" + state.getVoiceAvailable());
        Log.i(PROACTIVE_TAG, "debug proactive id=" + outcome.getDeliveryId() + " mode=" + outcome.getMode() + " reason=" + outcome.getReason() + " notification=" + outcome.getNotificationShown() + " voiceRequested=" + outcome.getVoiceRequested() + " duplicate=" + outcome.getDuplicate() + " focus=see VoiceService log budget=untouched");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:26:0x0062  */
    /* JADX WARN: Code duplicated, block: B:28:0x006f  */
    /* JADX WARN: Code duplicated, block: B:31:0x0087  */
    /* JADX WARN: Code duplicated, block: B:38:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:40:0x00c3 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:41:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:42:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:45:0x00d3  */
    /* JADX WARN: Code duplicated, block: B:50:0x009b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:52:? A[LOOP:0: B:29:0x0081->B:52:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final void handleProactiveAction(Context context, Intent intent) {
        String lowerCase;
        String action;
        String requestedId;
        ListIterator<ProactiveDeliveryLog.Entry> listIterator;
        ProactiveDeliveryLog.Entry entryFind;
        Object element$iv;
        ProactiveDeliveryLog.Entry it;
        ProactiveDeliveryLog.Entry entry;
        String deliveryId;
        String patternKey;
        String string;
        Context app = context.getApplicationContext();
        String stringExtra = intent.getStringExtra(EXTRA_ACTION);
        Object obj = null;
        if (stringExtra == null || (string = StringsKt.trim((CharSequence) stringExtra).toString()) == null) {
            lowerCase = null;
        } else {
            lowerCase = string.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        }
        String raw = lowerCase;
        if (raw != null) {
            switch (raw.hashCode()) {
                case 3521:
                    if (raw.equals("no")) {
                        action = ProactiveActionReceiver.ACTION_NO;
                        requestedId = intent.getStringExtra("deliveryId");
                        if (requestedId != null) {
                            ProactiveDeliveryLog proactiveDeliveryLog = ProactiveDeliveryLog.INSTANCE;
                            Intrinsics.checkNotNull(app);
                            entryFind = proactiveDeliveryLog.find(app, requestedId);
                            if (entryFind == null) {
                                ProactiveDeliveryLog proactiveDeliveryLog2 = ProactiveDeliveryLog.INSTANCE;
                                Intrinsics.checkNotNull(app);
                                List<ProactiveDeliveryLog.Entry> listAll = proactiveDeliveryLog2.all(app);
                                listIterator = listAll.listIterator(listAll.size());
                                while (listIterator.hasPrevious()) {
                                    element$iv = listIterator.previous();
                                    it = (ProactiveDeliveryLog.Entry) element$iv;
                                    if (!Intrinsics.areEqual(it.getMode(), "SUPPRESS")) {
                                        obj = element$iv;
                                        entryFind = (ProactiveDeliveryLog.Entry) obj;
                                    }
                                }
                                entryFind = (ProactiveDeliveryLog.Entry) obj;
                            }
                        } else {
                            ProactiveDeliveryLog proactiveDeliveryLog3 = ProactiveDeliveryLog.INSTANCE;
                            Intrinsics.checkNotNull(app);
                            List<ProactiveDeliveryLog.Entry> listAll2 = proactiveDeliveryLog3.all(app);
                            listIterator = listAll2.listIterator(listAll2.size());
                            while (listIterator.hasPrevious()) {
                                element$iv = listIterator.previous();
                                it = (ProactiveDeliveryLog.Entry) element$iv;
                                if (!Intrinsics.areEqual(it.getMode(), "SUPPRESS")) {
                                    obj = element$iv;
                                    entryFind = (ProactiveDeliveryLog.Entry) obj;
                                }
                            }
                            entryFind = (ProactiveDeliveryLog.Entry) obj;
                        }
                        entry = entryFind;
                        if (entry != null) {
                            if (requestedId == null) {
                                deliveryId = entry.getDeliveryId();
                            } else {
                                deliveryId = requestedId;
                            }
                            String deliveryId2 = deliveryId;
                            patternKey = entry.getPatternKey();
                            if (patternKey == null) {
                                patternKey = "—";
                            }
                            Log.i(PROACTIVE_TAG, "debug proactive action=" + raw + " id=" + deliveryId2 + " key=" + patternKey);
                            ProactiveActionReceiver.INSTANCE.perform(app, action, deliveryId2, entry.getPatternKey(), entry.getFingerprint());
                        } else {
                            Log.i(PROACTIVE_TAG, "debug proactive action=" + raw + " skipped: no delivery in log");
                        }
                    }
                    break;
                case 119527:
                    if (raw.equals("yes")) {
                        action = ProactiveActionReceiver.ACTION_YES;
                        requestedId = intent.getStringExtra("deliveryId");
                        if (requestedId != null) {
                            ProactiveDeliveryLog proactiveDeliveryLog4 = ProactiveDeliveryLog.INSTANCE;
                            Intrinsics.checkNotNull(app);
                            entryFind = proactiveDeliveryLog4.find(app, requestedId);
                            if (entryFind == null) {
                                ProactiveDeliveryLog proactiveDeliveryLog5 = ProactiveDeliveryLog.INSTANCE;
                                Intrinsics.checkNotNull(app);
                                List<ProactiveDeliveryLog.Entry> listAll3 = proactiveDeliveryLog5.all(app);
                                listIterator = listAll3.listIterator(listAll3.size());
                                while (listIterator.hasPrevious()) {
                                    element$iv = listIterator.previous();
                                    it = (ProactiveDeliveryLog.Entry) element$iv;
                                    if (!Intrinsics.areEqual(it.getMode(), "SUPPRESS")) {
                                        obj = element$iv;
                                        entryFind = (ProactiveDeliveryLog.Entry) obj;
                                    }
                                }
                                entryFind = (ProactiveDeliveryLog.Entry) obj;
                            }
                        } else {
                            ProactiveDeliveryLog proactiveDeliveryLog6 = ProactiveDeliveryLog.INSTANCE;
                            Intrinsics.checkNotNull(app);
                            List<ProactiveDeliveryLog.Entry> listAll4 = proactiveDeliveryLog6.all(app);
                            listIterator = listAll4.listIterator(listAll4.size());
                            while (listIterator.hasPrevious()) {
                                element$iv = listIterator.previous();
                                it = (ProactiveDeliveryLog.Entry) element$iv;
                                if (!Intrinsics.areEqual(it.getMode(), "SUPPRESS")) {
                                    obj = element$iv;
                                    entryFind = (ProactiveDeliveryLog.Entry) obj;
                                }
                            }
                            entryFind = (ProactiveDeliveryLog.Entry) obj;
                        }
                        entry = entryFind;
                        if (entry != null) {
                            if (requestedId == null) {
                                deliveryId = entry.getDeliveryId();
                            } else {
                                deliveryId = requestedId;
                            }
                            String deliveryId3 = deliveryId;
                            patternKey = entry.getPatternKey();
                            if (patternKey == null) {
                                patternKey = "—";
                            }
                            Log.i(PROACTIVE_TAG, "debug proactive action=" + raw + " id=" + deliveryId3 + " key=" + patternKey);
                            ProactiveActionReceiver.INSTANCE.perform(app, action, deliveryId3, entry.getPatternKey(), entry.getFingerprint());
                        } else {
                            Log.i(PROACTIVE_TAG, "debug proactive action=" + raw + " skipped: no delivery in log");
                        }
                    }
                    break;
                case 102744716:
                    if (raw.equals(ProactiveDeliveryLog.ACTION_LATER)) {
                        action = ProactiveActionReceiver.ACTION_LATER;
                        requestedId = intent.getStringExtra("deliveryId");
                        if (requestedId != null) {
                            ProactiveDeliveryLog proactiveDeliveryLog7 = ProactiveDeliveryLog.INSTANCE;
                            Intrinsics.checkNotNull(app);
                            entryFind = proactiveDeliveryLog7.find(app, requestedId);
                            if (entryFind == null) {
                                ProactiveDeliveryLog proactiveDeliveryLog8 = ProactiveDeliveryLog.INSTANCE;
                                Intrinsics.checkNotNull(app);
                                List<ProactiveDeliveryLog.Entry> listAll5 = proactiveDeliveryLog8.all(app);
                                listIterator = listAll5.listIterator(listAll5.size());
                                while (listIterator.hasPrevious()) {
                                    element$iv = listIterator.previous();
                                    it = (ProactiveDeliveryLog.Entry) element$iv;
                                    if (!Intrinsics.areEqual(it.getMode(), "SUPPRESS")) {
                                        obj = element$iv;
                                        entryFind = (ProactiveDeliveryLog.Entry) obj;
                                    }
                                }
                                entryFind = (ProactiveDeliveryLog.Entry) obj;
                            }
                        } else {
                            ProactiveDeliveryLog proactiveDeliveryLog9 = ProactiveDeliveryLog.INSTANCE;
                            Intrinsics.checkNotNull(app);
                            List<ProactiveDeliveryLog.Entry> listAll6 = proactiveDeliveryLog9.all(app);
                            listIterator = listAll6.listIterator(listAll6.size());
                            while (listIterator.hasPrevious()) {
                                element$iv = listIterator.previous();
                                it = (ProactiveDeliveryLog.Entry) element$iv;
                                if (!Intrinsics.areEqual(it.getMode(), "SUPPRESS")) {
                                    obj = element$iv;
                                    entryFind = (ProactiveDeliveryLog.Entry) obj;
                                }
                            }
                            entryFind = (ProactiveDeliveryLog.Entry) obj;
                        }
                        entry = entryFind;
                        if (entry != null) {
                            if (requestedId == null) {
                                deliveryId = entry.getDeliveryId();
                            } else {
                                deliveryId = requestedId;
                            }
                            String deliveryId4 = deliveryId;
                            patternKey = entry.getPatternKey();
                            if (patternKey == null) {
                                patternKey = "—";
                            }
                            Log.i(PROACTIVE_TAG, "debug proactive action=" + raw + " id=" + deliveryId4 + " key=" + patternKey);
                            ProactiveActionReceiver.INSTANCE.perform(app, action, deliveryId4, entry.getPatternKey(), entry.getFingerprint());
                        } else {
                            Log.i(PROACTIVE_TAG, "debug proactive action=" + raw + " skipped: no delivery in log");
                        }
                    }
                    break;
            }
            return;
        }
        Log.d(TAG, "proactive action: --es action yes|no|later required (got=" + raw + ")");
    }

    private final void handleProactiveClear(Context context, Intent intent) {
        Context app = context.getApplicationContext();
        ProactiveDeliveryLog proactiveDeliveryLog = ProactiveDeliveryLog.INSTANCE;
        Intrinsics.checkNotNull(app);
        Iterable $this$filter$iv = proactiveDeliveryLog.all(app);
        Collection destination$iv$iv = new ArrayList();
        Iterator it = $this$filter$iv.iterator();
        while (true) {
            boolean z = false;
            if (!it.hasNext()) {
                break;
            }
            Object element$iv$iv = it.next();
            ProactiveDeliveryLog.Entry it2 = (ProactiveDeliveryLog.Entry) element$iv$iv;
            String patternKey = it2.getPatternKey();
            if (patternKey != null && StringsKt.startsWith$default(patternKey, SYNTHETIC_PATTERN_PREFIX, false, 2, (Object) null)) {
                z = true;
            }
            if (z) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List synthetic = (List) destination$iv$iv;
        List $this$forEach$iv = synthetic;
        for (Object element$iv : $this$forEach$iv) {
            ProactiveDeliveryLog.Entry it3 = (ProactiveDeliveryLog.Entry) element$iv;
            ProactiveNotification.INSTANCE.cancel(app, it3.getDeliveryId());
        }
        boolean clearLog = intent.getBooleanExtra(EXTRA_LOG, false);
        if (clearLog) {
            ProactiveDeliveryLog.INSTANCE.clear(app);
        }
        Log.i(PROACTIVE_TAG, "debug proactive clear notifications=" + synthetic.size() + " logCleared=" + clearLog);
    }

    private final void handleCommand(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra(EXTRA_COMMAND);
        String text = stringExtra != null ? StringsKt.trim((CharSequence) stringExtra).toString() : null;
        if (text == null) {
            text = "";
        }
        Log.d(TAG, "command text=\"" + SensitivityClassifier.INSTANCE.mask(text) + "\"");
        if (text.length() == 0) {
            Log.d(TAG, "ignored: empty command");
        } else {
            Context app = context.getApplicationContext();
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), null, null, new C00581(app, text, null), 3, null);
        }
    }

    /* JADX INFO: renamed from: com.jarvis.app.DebugCommandReceiver$handleCommand$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DebugCommandReceiver.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.DebugCommandReceiver$handleCommand$1", f = "DebugCommandReceiver.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C00581 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $app;
        final /* synthetic */ String $text;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00581(Context context, String str, Continuation<? super C00581> continuation) {
            super(2, continuation);
            this.$app = context;
            this.$text = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C00581(this.$app, this.$text, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00581) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    try {
                        if (!VoiceController.INSTANCE.isRunning()) {
                            VoiceController voiceController = VoiceController.INSTANCE;
                            Context context = this.$app;
                            Intrinsics.checkNotNull(context);
                            voiceController.start(context);
                            for (int waited = 0; !VoiceController.INSTANCE.isRunning() && waited < 50; waited++) {
                                Thread.sleep(100L);
                            }
                        }
                        if (VoiceController.INSTANCE.isRunning()) {
                            Log.d(DebugCommandReceiver.TAG, "inject via VoiceService e2e: \"" + SensitivityClassifier.INSTANCE.mask(this.$text) + "\"");
                            VoiceController.INSTANCE.notifyRequest$app_debug(new UserRequest(this.$text, null, null, 0L, null, 30, null));
                            return Unit.INSTANCE;
                        }
                        BrainGraph brainGraph = BrainGraph.INSTANCE;
                        Context context2 = this.$app;
                        Intrinsics.checkNotNull(context2);
                        brainGraph.warmLlmBrain(context2);
                        BrainGraph brainGraph2 = BrainGraph.INSTANCE;
                        Context context3 = this.$app;
                        Intrinsics.checkNotNull(context3);
                        AssistantResponse response = brainGraph2.build(context3).process(new UserRequest(this.$text, null, null, 0L, null, 30, null));
                        String strMask = SensitivityClassifier.INSTANCE.mask(this.$text);
                        com.jarvis.core.common.model.Intent intent = response.getIntent();
                        IntentType type = intent != null ? intent.getType() : null;
                        ToolResult toolResult = response.getToolResult();
                        String toolId = toolResult != null ? toolResult.getToolId() : null;
                        ToolResult toolResult2 = response.getToolResult();
                        ToolResultStatus status = toolResult2 != null ? toolResult2.getStatus() : null;
                        Log.d(DebugCommandReceiver.TAG, "request=\"" + strMask + "\" -> intent=" + type + " tool=" + toolId + "/" + status + " status=" + response.getStatus() + " durationMs=" + response.getDurationMs() + " reply=\"" + SensitivityClassifier.INSTANCE.mask(response.getReplyText()) + "\"");
                        return Unit.INSTANCE;
                    } catch (Throwable e) {
                        Log.e(DebugCommandReceiver.TAG, "command processing failed: " + e.getMessage(), e);
                    }
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    private final void handleKeys(Context context) {
        SecureKeyStore store = new SecureKeyStore(context);
        Log.d(TAG, "keys groq=" + store.hasKey("groq") + " gemini=" + store.hasKey("gemini") + " custom=" + store.hasKey("custom") + " migrated=" + SecureKeyMigration.INSTANCE.isMigrated(context));
    }

    private final void handleKeysSet(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra(EXTRA_PROVIDER);
        String provider = stringExtra != null ? StringsKt.trim((CharSequence) stringExtra).toString() : null;
        if (provider == null) {
            provider = "";
        }
        String stringExtra2 = intent.getStringExtra(EXTRA_KEY);
        String key = stringExtra2 != null ? stringExtra2 : "";
        if (StringsKt.isBlank(provider)) {
            Log.d(TAG, "keys set failed reason=missing_provider");
            return;
        }
        new SecureKeyStore(context).setKey(provider, key);
        String lowerCase = provider.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        Log.d(TAG, "keys set provider=" + lowerCase + " configured=" + new SecureKeyStore(context).hasKey(provider));
    }

    private final void handleKeysClear(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra(EXTRA_PROVIDER);
        String provider = stringExtra != null ? StringsKt.trim((CharSequence) stringExtra).toString() : null;
        if (provider == null) {
            provider = "";
        }
        if (StringsKt.isBlank(provider)) {
            Log.d(TAG, "keys clear failed reason=missing_provider");
            return;
        }
        new SecureKeyStore(context).clearKey(provider);
        String lowerCase = provider.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        Log.d(TAG, "keys clear provider=" + lowerCase + " configured=" + new SecureKeyStore(context).hasKey(provider));
    }

    private final void handleEchoProbe() {
        Log.d(TAG, "echo-probe aec=" + AcousticEchoCanceler.isAvailable());
        new Thread(new Runnable() { // from class: com.jarvis.app.DebugCommandReceiver$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DebugCommandReceiver.handleEchoProbe$lambda$0(this.f$0);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:24:0x00c1 A[LOOP:2: B:17:0x00b0->B:24:0x00c1, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:32:0x00f5 A[Catch: all -> 0x00c9, TRY_ENTER, TRY_LEAVE, TryCatch #6 {all -> 0x00c9, blocks: (B:22:0x00bb, B:32:0x00f5), top: B:81:0x00bb }] */
    /* JADX WARN: Code duplicated, block: B:35:0x010e A[Catch: all -> 0x01c3, TRY_LEAVE, TryCatch #3 {all -> 0x01c3, blocks: (B:30:0x00ed, B:33:0x00f8, B:35:0x010e), top: B:75:0x00ed }] */
    /* JADX WARN: Code duplicated, block: B:44:0x015e  */
    /* JADX WARN: Code duplicated, block: B:45:0x0160  */
    /* JADX WARN: Code duplicated, block: B:65:0x0200  */
    /* JADX WARN: Code duplicated, block: B:79:0x00b5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:90:0x0227 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:91:0x00e9 A[SYNTHETIC] */
    public static final void handleEchoProbe$lambda$0(DebugCommandReceiver this$0) {
        List sources;
        int it;
        int i;
        Iterable $this$forEach$iv;
        int $i$f$forEach;
        Iterator it2;
        boolean z;
        Object objM392constructorimpl;
        Throwable it3;
        short[] buf;
        int got;
        DebugCommandReceiver $this$handleEchoProbe_u24lambda_u240_u240_u240_u240;
        AudioRecord record;
        short[] tail;
        int length;
        double d;
        int got2;
        boolean z2;
        int n;
        boolean z3 = true;
        List sources2 = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to("MIC", 1), TuplesKt.to("RECOGNITION", 6), TuplesKt.to("COMMUNICATION", 7)});
        int i2 = 0;
        for (int i3 = 2; i2 < i3; i3 = 2) {
            int it4 = i2;
            int i4 = 0;
            List $this$forEach$iv2 = sources2;
            int $i$f$forEach2 = 0;
            Iterator it5 = $this$forEach$iv2.iterator();
            while (it5.hasNext()) {
                Object element$iv = it5.next();
                Pair pair = (Pair) element$iv;
                String name = (String) pair.component1();
                int source = ((Number) pair.component2()).intValue();
                try {
                    Result.Companion companion = Result.INSTANCE;
                    DebugCommandReceiver $this$handleEchoProbe_u24lambda_u240_u240_u240_u241 = this$0;
                    int size = 16000 * 2 * 2;
                    AudioRecord record2 = new AudioRecord(source, 16000, 16, 2, size);
                    AcousticEchoCanceler aec = AcousticEchoCanceler.create(record2.getAudioSessionId());
                    if (aec != null) {
                        try {
                            aec.setEnabled(z3);
                            record2.startRecording();
                            buf = new short[16000 * 2];
                            got = 0;
                            while (true) {
                                $this$handleEchoProbe_u24lambda_u240_u240_u240_u240 = $this$handleEchoProbe_u24lambda_u240_u240_u240_u241;
                                try {
                                    if (got < buf.length) {
                                        sources = sources2;
                                        record = record2;
                                        break;
                                    }
                                    try {
                                        sources = sources2;
                                        record = record2;
                                        try {
                                            n = record.read(buf, got, buf.length - got);
                                            if (n > 0) {
                                                break;
                                            }
                                            got += n;
                                            record2 = record;
                                            $this$handleEchoProbe_u24lambda_u240_u240_u240_u241 = $this$handleEchoProbe_u24lambda_u240_u240_u240_u240;
                                            sources2 = sources;
                                        } catch (Throwable th) {
                                            th = th;
                                            it = it4;
                                            i = i4;
                                            $this$forEach$iv = $this$forEach$iv2;
                                            $i$f$forEach = $i$f$forEach2;
                                            it2 = it5;
                                            z = true;
                                            Result.Companion companion2 = Result.INSTANCE;
                                            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
                                            it3 = Result.m395exceptionOrNullimpl(objM392constructorimpl);
                                            if (it3 != null) {
                                                Log.d(TAG, "echo-probe " + name + " failed " + it3.getClass().getSimpleName());
                                            }
                                            it5 = it2;
                                            z3 = z;
                                            sources2 = sources;
                                            it4 = it;
                                            i4 = i;
                                            $this$forEach$iv2 = $this$forEach$iv;
                                            $i$f$forEach2 = $i$f$forEach;
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        sources = sources2;
                                        it = it4;
                                        i = i4;
                                        $this$forEach$iv = $this$forEach$iv2;
                                        $i$f$forEach = $i$f$forEach2;
                                        it2 = it5;
                                        z = true;
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    sources = sources2;
                                    it = it4;
                                    i = i4;
                                    $this$forEach$iv = $this$forEach$iv2;
                                    $i$f$forEach = $i$f$forEach2;
                                    it2 = it5;
                                    z = true;
                                }
                            }
                            try {
                                record.stop();
                                record.release();
                                if (aec != null) {
                                    aec.release();
                                }
                                tail = ArraysKt.copyOfRange(buf, 16000 / 2, RangesKt.coerceAtLeast(got, 16000 / 2));
                                length = tail.length;
                                d = 0.0d;
                                got2 = 0;
                                while (got2 < length) {
                                    short it6 = tail[got2];
                                    Iterable $this$forEach$iv3 = $this$forEach$iv2;
                                    double d2 = 32768;
                                    d += (((double) it6) / d2) * (((double) it6) / d2);
                                    got2++;
                                    it5 = it5;
                                    it4 = it4;
                                    length = length;
                                    i4 = i4;
                                    buf = buf;
                                    $this$forEach$iv2 = $this$forEach$iv3;
                                    $i$f$forEach2 = $i$f$forEach2;
                                    element$iv = element$iv;
                                }
                                it = it4;
                                i = i4;
                                $this$forEach$iv = $this$forEach$iv2;
                                $i$f$forEach = $i$f$forEach2;
                                it2 = it5;
                                try {
                                    try {
                                        double rms = Math.sqrt(d / ((double) RangesKt.coerceAtLeast(tail.length, 1)));
                                        if (aec != null) {
                                            z2 = true;
                                        } else {
                                            z2 = false;
                                        }
                                        z = true;
                                        try {
                                            String str = String.format("%.5f", Arrays.copyOf(new Object[]{Double.valueOf(rms)}, 1));
                                            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                                            objM392constructorimpl = Result.m392constructorimpl(Integer.valueOf(Log.d(TAG, "echo-probe " + name + " aecOn=" + z2 + " rms=" + str + " speaking=" + VoiceController.INSTANCE.isSpeaking())));
                                        } catch (Throwable th4) {
                                            th = th4;
                                            Result.Companion companion3 = Result.INSTANCE;
                                            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
                                        }
                                    } catch (Throwable th5) {
                                        th = th5;
                                        z = true;
                                    }
                                } catch (Throwable th6) {
                                    th = th6;
                                    z = true;
                                }
                            } catch (Throwable th7) {
                                th = th7;
                                it = it4;
                                i = i4;
                                $this$forEach$iv = $this$forEach$iv2;
                                $i$f$forEach = $i$f$forEach2;
                                it2 = it5;
                                z = true;
                            }
                        } catch (Throwable th8) {
                            th = th8;
                            sources = sources2;
                            it = it4;
                            i = i4;
                            $this$forEach$iv = $this$forEach$iv2;
                            $i$f$forEach = $i$f$forEach2;
                            it2 = it5;
                            z = z3;
                            Result.Companion companion4 = Result.INSTANCE;
                            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
                            it3 = Result.m395exceptionOrNullimpl(objM392constructorimpl);
                            if (it3 != null) {
                                Log.d(TAG, "echo-probe " + name + " failed " + it3.getClass().getSimpleName());
                            }
                            it5 = it2;
                            z3 = z;
                            sources2 = sources;
                            it4 = it;
                            i4 = i;
                            $this$forEach$iv2 = $this$forEach$iv;
                            $i$f$forEach2 = $i$f$forEach;
                        }
                    } else {
                        record2.startRecording();
                        buf = new short[16000 * 2];
                        got = 0;
                        while (true) {
                            $this$handleEchoProbe_u24lambda_u240_u240_u240_u240 = $this$handleEchoProbe_u24lambda_u240_u240_u240_u241;
                            if (got < buf.length) {
                                sources = sources2;
                                record = record2;
                                break;
                            }
                            sources = sources2;
                            record = record2;
                            n = record.read(buf, got, buf.length - got);
                            if (n > 0) {
                                break;
                            }
                            got += n;
                            record2 = record;
                            $this$handleEchoProbe_u24lambda_u240_u240_u240_u241 = $this$handleEchoProbe_u24lambda_u240_u240_u240_u240;
                            sources2 = sources;
                        }
                        record.stop();
                        record.release();
                        if (aec != null) {
                            aec.release();
                        }
                        tail = ArraysKt.copyOfRange(buf, 16000 / 2, RangesKt.coerceAtLeast(got, 16000 / 2));
                        length = tail.length;
                        d = 0.0d;
                        got2 = 0;
                        while (got2 < length) {
                            short it7 = tail[got2];
                            Iterable $this$forEach$iv4 = $this$forEach$iv2;
                            double d3 = 32768;
                            d += (((double) it7) / d3) * (((double) it7) / d3);
                            got2++;
                            it5 = it5;
                            it4 = it4;
                            length = length;
                            i4 = i4;
                            buf = buf;
                            $this$forEach$iv2 = $this$forEach$iv4;
                            $i$f$forEach2 = $i$f$forEach2;
                            element$iv = element$iv;
                        }
                        it = it4;
                        i = i4;
                        $this$forEach$iv = $this$forEach$iv2;
                        $i$f$forEach = $i$f$forEach2;
                        it2 = it5;
                        double rms2 = Math.sqrt(d / ((double) RangesKt.coerceAtLeast(tail.length, 1)));
                        if (aec != null) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        z = true;
                        String str2 = String.format("%.5f", Arrays.copyOf(new Object[]{Double.valueOf(rms2)}, 1));
                        Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
                        objM392constructorimpl = Result.m392constructorimpl(Integer.valueOf(Log.d(TAG, "echo-probe " + name + " aecOn=" + z2 + " rms=" + str2 + " speaking=" + VoiceController.INSTANCE.isSpeaking())));
                    }
                } catch (Throwable th9) {
                    th = th9;
                    sources = sources2;
                    it = it4;
                    i = i4;
                    $this$forEach$iv = $this$forEach$iv2;
                    $i$f$forEach = $i$f$forEach2;
                    it2 = it5;
                    z = z3;
                }
                it3 = Result.m395exceptionOrNullimpl(objM392constructorimpl);
                if (it3 != null) {
                    Log.d(TAG, "echo-probe " + name + " failed " + it3.getClass().getSimpleName());
                }
                it5 = it2;
                z3 = z;
                sources2 = sources;
                it4 = it;
                i4 = i;
                $this$forEach$iv2 = $this$forEach$iv;
                $i$f$forEach2 = $i$f$forEach;
            }
            i2++;
        }
    }

    private final void handleWidgets(Context context) {
        final Context app = context.getApplicationContext();
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jarvis.app.DebugCommandReceiver$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                DebugCommandReceiver.handleWidgets$lambda$0(this.f$0, app);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void handleWidgets$lambda$0(DebugCommandReceiver this$0, Context $app) {
        Iterable $this$forEach$iv;
        int $i$f$forEach;
        Iterator it;
        Object objM392constructorimpl;
        Iterable $this$forEach$iv2 = CollectionsKt.listOf((Object[]) new Triple[]{new Triple("large", new JarvisWidgetProvider(), TuplesKt.to(340, 140)), new Triple("square", new JarvisWidgetSquare(), TuplesKt.to(160, 160)), new Triple("strip", new JarvisWidgetStrip(), TuplesKt.to(340, 66))});
        int $i$f$forEach2 = 0;
        Iterator it2 = $this$forEach$iv2.iterator();
        while (it2.hasNext()) {
            Object element$iv = it2.next();
            Triple triple = (Triple) element$iv;
            String name = (String) triple.component1();
            JarvisWidgetProvider provider = (JarvisWidgetProvider) triple.component2();
            Pair size = (Pair) triple.component3();
            try {
                Result.Companion companion = Result.INSTANCE;
                float d = $app.getResources().getDisplayMetrics().density;
                int w = (int) (((Number) size.getFirst()).floatValue() * d);
                $this$forEach$iv = $this$forEach$iv2;
                int h = (int) (((Number) size.getSecond()).floatValue() * d);
                try {
                    Intrinsics.checkNotNull($app);
                    $i$f$forEach = $i$f$forEach2;
                    try {
                        View view = provider.views$app_debug($app).apply($app, new FrameLayout($app));
                        view.measure(View.MeasureSpec.makeMeasureSpec(w, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(h, BasicMeasure.EXACTLY));
                        view.layout(0, 0, w, h);
                        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
                        Intrinsics.checkNotNullExpressionValue(bmp, "createBitmap(...)");
                        view.draw(new Canvas(bmp));
                        it = it2;
                        try {
                            FileOutputStream it3 = new FileOutputStream(new File($app.getCacheDir(), "widget_" + name + ".png"));
                            try {
                                bmp.compress(Bitmap.CompressFormat.PNG, 100, it3);
                                CloseableKt.closeFinally(it3, null);
                                objM392constructorimpl = Result.m392constructorimpl(Integer.valueOf(Log.d(TAG, "widget " + name + " ok")));
                            } catch (Throwable th) {
                                try {
                                    throw th;
                                } catch (Throwable th2) {
                                    CloseableKt.closeFinally(it3, th);
                                    throw th2;
                                }
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            Result.Companion companion2 = Result.INSTANCE;
                            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        it = it2;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    $i$f$forEach = $i$f$forEach2;
                    it = it2;
                }
            } catch (Throwable th6) {
                th = th6;
                $this$forEach$iv = $this$forEach$iv2;
                $i$f$forEach = $i$f$forEach2;
                it = it2;
            }
            Throwable it4 = Result.m395exceptionOrNullimpl(objM392constructorimpl);
            if (it4 != null) {
                Log.e(TAG, "widget " + name + " failed: " + it4.getMessage());
            }
            $this$forEach$iv2 = $this$forEach$iv;
            $i$f$forEach2 = $i$f$forEach;
            it2 = it;
        }
    }

    private final void handleTtsDump(Context context, Intent intent) throws IOException {
        String $this$filter$iv;
        final Context app = context.getApplicationContext();
        String stringExtra = intent.getStringExtra("text");
        final String text = stringExtra == null ? "" : stringExtra;
        CharSequence $this$filter$iv2 = intent.getStringExtra("name");
        if ($this$filter$iv2 != null) {
            CharSequence $this$filterTo$iv$iv = $this$filter$iv2;
            Appendable destination$iv$iv = new StringBuilder();
            int length = $this$filterTo$iv$iv.length();
            for (int index$iv$iv = 0; index$iv$iv < length; index$iv$iv++) {
                char element$iv$iv = $this$filterTo$iv$iv.charAt(index$iv$iv);
                if (Character.isLetterOrDigit(element$iv$iv)) {
                    destination$iv$iv.append(element$iv$iv);
                }
            }
            $this$filter$iv = ((StringBuilder) destination$iv$iv).toString();
        } else {
            $this$filter$iv = null;
        }
        String str = $this$filter$iv != null ? $this$filter$iv : "";
        if (str.length() == 0) {
            str = ArchiveStreamFactory.DUMP;
        }
        final String name = str;
        JarvisSpeaker.Companion companion = JarvisSpeaker.INSTANCE;
        Intrinsics.checkNotNull(app);
        List<JarvisTtsConfig> listVoiceConfigs = companion.voiceConfigs(app);
        int index = intent.getIntExtra("voice", 0);
        final JarvisTtsConfig config = (JarvisTtsConfig) CollectionsKt.getOrNull(listVoiceConfigs, index);
        if (config == null) {
            Log.d(TAG, "tts-dump: нет голоса " + index);
        } else {
            final BroadcastReceiver.PendingResult pending = goAsync();
            new Thread(new Runnable() { // from class: com.jarvis.app.DebugCommandReceiver$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    DebugCommandReceiver.handleTtsDump$lambda$3(this.f$0, pending, config, text, app, name);
                }
            }).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void handleTtsDump$lambda$3(DebugCommandReceiver this$0, BroadcastReceiver.PendingResult $pending, JarvisTtsConfig $config, String $text, Context $app, String $name) {
        Object objM392constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            int i = 0;
            JarvisTts tts = new JarvisTts($config);
            long t0 = SystemClock.elapsedRealtime();
            try {
                float[] samples = tts.synthesize($text);
                long ms = SystemClock.elapsedRealtime() - t0;
                ByteBuffer bytes = ByteBuffer.allocate(samples.length * 2).order(ByteOrder.LITTLE_ENDIAN);
                int length = samples.length;
                int i2 = 0;
                while (i2 < length) {
                    float element$iv = samples[i2];
                    bytes.putShort((short) (RangesKt.coerceIn(element$iv, -1.0f, 1.0f) * 32767));
                    i2++;
                    i = i;
                    t0 = t0;
                }
                File file = new File($app.getCacheDir(), "tts_" + $name + ".pcm");
                byte[] bArrArray = bytes.array();
                Intrinsics.checkNotNullExpressionValue(bArrArray, "array(...)");
                FilesKt.writeBytes(file, bArrArray);
                Log.d(TAG, "tts-dump " + $name + " dir=" + $config.getModelDir().getName() + " sr=" + tts.getSampleRate() + " samples=" + samples.length + " synthMs=" + ms);
                tts.release();
                objM392constructorimpl = Result.m392constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                th = th;
                Result.Companion companion2 = Result.INSTANCE;
                objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
            }
        } catch (Throwable th2) {
            th = th2;
        }
        Throwable it = Result.m395exceptionOrNullimpl(objM392constructorimpl);
        if (it != null) {
            Log.e(TAG, "tts-dump failed: " + it.getMessage());
        }
        $pending.finish();
    }

    private final void handleTtsSample(Context context, final Intent intent) {
        final Context app = context.getApplicationContext();
        final String key = new SecureKeyStore(context).getKey("gemini");
        String str = key;
        if (!(str == null || StringsKt.isBlank(str))) {
            final String model = intent.getStringExtra("model");
            String stringExtra = intent.getStringExtra("voice");
            if (stringExtra == null) {
                stringExtra = "Charon";
            }
            final String voice = stringExtra;
            String stringExtra2 = intent.getStringExtra("text");
            if (stringExtra2 == null) {
                stringExtra2 = "";
            }
            final String text = stringExtra2;
            final String searchModel = intent.getStringExtra("search");
            if (searchModel != null) {
                new Thread(new Runnable() { // from class: com.jarvis.app.DebugCommandReceiver$$ExternalSyntheticLambda5
                    @Override // java.lang.Runnable
                    public final void run() {
                        DebugCommandReceiver.handleTtsSample$lambda$0(searchModel, this, key);
                    }
                }).start();
                return;
            } else {
                new Thread(new Runnable() { // from class: com.jarvis.app.DebugCommandReceiver$$ExternalSyntheticLambda6
                    @Override // java.lang.Runnable
                    public final void run() {
                        DebugCommandReceiver.handleTtsSample$lambda$1(this.f$0, model, key, text, voice, intent, app);
                    }
                }).start();
                return;
            }
        }
        Log.d(TAG, "tts: no gemini key");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void handleTtsSample$lambda$0(String $searchModel, DebugCommandReceiver this$0, String $key) {
        Log.d(TAG, "search-probe " + $searchModel + ": " + this$0.probeSearch($key, $searchModel));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:112:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:89:0x0306  */
    /* JADX WARN: Code duplicated, block: B:91:0x0315  */
    /* JADX WARN: Code duplicated, block: B:92:0x031c  */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    public static final void handleTtsSample$lambda$1(DebugCommandReceiver this$0, String $model, String $key, String $text, String $voice, Intent $intent, Context $app) {
        Object objM392constructorimpl;
        Throwable it;
        String message;
        String strTake;
        String str;
        boolean z;
        String reply;
        int iD;
        try {
            Result.Companion companion = Result.INSTANCE;
            if ($model == null) {
                try {
                    str = "https://generativelanguage.googleapis.com/v1beta/models?pageSize=200";
                } catch (Throwable th) {
                    th = th;
                    Result.Companion companion2 = Result.INSTANCE;
                    objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
                    it = Result.m395exceptionOrNullimpl(objM392constructorimpl);
                    if (it != null) {
                        String simpleName = it.getClass().getSimpleName();
                        message = it.getMessage();
                        if (message != null) {
                            strTake = StringsKt.take(message, 160);
                        } else {
                            strTake = null;
                        }
                        Log.d(TAG, "tts: failed " + simpleName + StringUtils.SPACE + strTake);
                    }
                }
            } else {
                str = "https://generativelanguage.googleapis.com/v1beta/models/" + $model + ":generateContent";
            }
            URLConnection uRLConnectionOpenConnection = new URI(str).toURL().openConnection();
            Intrinsics.checkNotNull(uRLConnectionOpenConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
            HttpURLConnection conn = (HttpURLConnection) uRLConnectionOpenConnection;
            try {
                conn.setRequestProperty("x-goog-api-key", $key);
                conn.setConnectTimeout(15000);
                conn.setReadTimeout(60000);
                if ($model != null) {
                    try {
                        try {
                            JSONObject body = new JSONObject().put("contents", new JSONArray().put(new JSONObject().put("parts", new JSONArray().put(new JSONObject().put("text", $text))))).put("generationConfig", new JSONObject().put("responseModalities", new JSONArray().put("AUDIO")).put("speechConfig", new JSONObject().put("voiceConfig", new JSONObject().put("prebuiltVoiceConfig", new JSONObject().put("voiceName", $voice)))));
                            conn.setRequestMethod("POST");
                            z = true;
                            conn.setDoOutput(true);
                            conn.setRequestProperty("Content-Type", "application/json");
                            OutputStream it2 = conn.getOutputStream();
                            try {
                                String string = body.toString();
                                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                                byte[] bytes = string.getBytes(Charsets.UTF_8);
                                Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                                it2.write(bytes);
                                Unit unit = Unit.INSTANCE;
                                CloseableKt.closeFinally(it2, null);
                            } catch (Throwable th2) {
                                try {
                                    throw th2;
                                } catch (Throwable th3) {
                                    CloseableKt.closeFinally(it2, th2);
                                    throw th3;
                                }
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            Result.Companion companion3 = Result.INSTANCE;
                            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
                            it = Result.m395exceptionOrNullimpl(objM392constructorimpl);
                            if (it != null) {
                                String simpleName2 = it.getClass().getSimpleName();
                                message = it.getMessage();
                                if (message != null) {
                                    strTake = StringsKt.take(message, 160);
                                } else {
                                    strTake = null;
                                }
                                Log.d(TAG, "tts: failed " + simpleName2 + StringUtils.SPACE + strTake);
                            }
                        }
                    } catch (Throwable th5) {
                        th = th5;
                    }
                } else {
                    z = true;
                }
                long t0 = System.currentTimeMillis();
                int code = conn.getResponseCode();
                InputStream inputStream = (200 > code || code >= 300) ? false : z ? conn.getInputStream() : conn.getErrorStream();
                if (inputStream != null) {
                    Reader inputStreamReader = new InputStreamReader(inputStream, Charsets.UTF_8);
                    reply = TextStreamsKt.readText(inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192));
                } else {
                    reply = null;
                }
                if (reply == null) {
                    reply = "";
                }
                long ms = System.currentTimeMillis() - t0;
                try {
                    if (!((200 > code || code >= 300) ? false : z)) {
                        iD = Log.d(TAG, "tts: http=" + code + " ms=" + ms + " quota=" + SequencesKt.toList(SequencesKt.map(Regex.findAll$default(new Regex("(Quota exceeded[^\\n\"]*|quotaId\"[^,]*|quotaValue\"[^,]*|retryDelay\"[^,}]*)"), reply, 0, 2, null), new Function1() { // from class: com.jarvis.app.DebugCommandReceiver$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return DebugCommandReceiver.handleTtsSample$lambda$1$0$1((MatchResult) obj);
                            }
                        })));
                    } else if ($model == null) {
                        try {
                            String filter = $intent.getStringExtra("filter");
                            if (filter == null) {
                                filter = "tts";
                            }
                            iD = Log.d(TAG, "tts: models=" + SequencesKt.toSet(SequencesKt.map(Regex.findAll$default(new Regex("models/[a-z0-9.-]*" + filter + "[a-z0-9.-]*"), reply, 0, 2, null), new Function1() { // from class: com.jarvis.app.DebugCommandReceiver$$ExternalSyntheticLambda3
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return DebugCommandReceiver.handleTtsSample$lambda$1$0$2((MatchResult) obj);
                                }
                            })));
                        } catch (Throwable th6) {
                            th = th6;
                            Result.Companion companion4 = Result.INSTANCE;
                            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
                        }
                    } else {
                        JSONObject data = new JSONObject(reply).getJSONArray("candidates").getJSONObject(0).getJSONObject("content").getJSONArray("parts").getJSONObject(0).getJSONObject("inlineData");
                        byte[] pcm = Base64.decode(data.getString("data"), 0);
                        File file = new File($app.getFilesDir(), "gemini_" + $voice + ".pcm");
                        Intrinsics.checkNotNull(pcm);
                        FilesKt.writeBytes(file, pcm);
                        iD = Log.d(TAG, "tts: ok voice=" + $voice + " bytes=" + pcm.length + " mime=" + data.optString("mimeType") + " ms=" + ms);
                    }
                    objM392constructorimpl = Result.m392constructorimpl(Integer.valueOf(iD));
                } catch (Throwable th7) {
                    th = th7;
                }
            } catch (Throwable th8) {
                th = th8;
                Result.Companion companion5 = Result.INSTANCE;
                objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
                it = Result.m395exceptionOrNullimpl(objM392constructorimpl);
                if (it != null) {
                    String simpleName3 = it.getClass().getSimpleName();
                    message = it.getMessage();
                    if (message != null) {
                        strTake = StringsKt.take(message, 160);
                    } else {
                        strTake = null;
                    }
                    Log.d(TAG, "tts: failed " + simpleName3 + StringUtils.SPACE + strTake);
                }
            }
        } catch (Throwable th9) {
            th = th9;
        }
        it = Result.m395exceptionOrNullimpl(objM392constructorimpl);
        if (it != null) {
            String simpleName4 = it.getClass().getSimpleName();
            message = it.getMessage();
            if (message != null) {
                strTake = StringsKt.take(message, 160);
            } else {
                strTake = null;
            }
            Log.d(TAG, "tts: failed " + simpleName4 + StringUtils.SPACE + strTake);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String handleTtsSample$lambda$1$0$1(MatchResult it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String handleTtsSample$lambda$1$0$2(MatchResult it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getValue();
    }

    private final String probeSearch(String key, String model) {
        Object objM392constructorimpl;
        String reply;
        List<String> groupValues;
        try {
            Result.Companion companion = Result.INSTANCE;
            DebugCommandReceiver debugCommandReceiver = this;
            String url = "https://generativelanguage.googleapis.com/v1beta/models/" + model + ":generateContent";
            URLConnection uRLConnectionOpenConnection = new URI(url).toURL().openConnection();
            Intrinsics.checkNotNull(uRLConnectionOpenConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
            HttpURLConnection conn = (HttpURLConnection) uRLConnectionOpenConnection;
            conn.setRequestProperty("x-goog-api-key", key);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(30000);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            OutputStream it = conn.getOutputStream();
            try {
                byte[] bytes = "{\"contents\":[{\"parts\":[{\"text\":\"Какой сегодня курс доллара? Ответь одной фразой.\"}]}],\"tools\":[{\"google_search\":{}}]}".getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                it.write(bytes);
                Unit unit = Unit.INSTANCE;
                String str = null;
                CloseableKt.closeFinally(it, null);
                int code = conn.getResponseCode();
                InputStream inputStream = 200 <= code && code < 300 ? conn.getInputStream() : conn.getErrorStream();
                if (inputStream != null) {
                    Reader inputStreamReader = new InputStreamReader(inputStream, Charsets.UTF_8);
                    reply = TextStreamsKt.readText(inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192));
                } else {
                    reply = null;
                }
                if (reply == null) {
                    reply = "";
                }
                MatchResult matchResultFind$default = Regex.find$default(new Regex("\"quotaId\"\\s*:\\s*\"([^\"]*)"), reply, 0, 2, null);
                if (matchResultFind$default != null && (groupValues = matchResultFind$default.getGroupValues()) != null) {
                    str = groupValues.get(1);
                }
                String quota = str;
                boolean queries = new Regex("webSearchQueries").containsMatchIn(reply);
                objM392constructorimpl = Result.m392constructorimpl("http=" + code + " searched=" + queries + " quota=" + quota);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(it, th);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th3));
        }
        Throwable it2 = Result.m395exceptionOrNullimpl(objM392constructorimpl);
        if (it2 != null) {
            String simpleName = it2.getClass().getSimpleName();
            Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
            objM392constructorimpl = simpleName;
        }
        return (String) objM392constructorimpl;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final void handleVoice(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra(EXTRA_ACTION);
        if (stringExtra != null) {
            switch (stringExtra.hashCode()) {
                case -1355047059:
                    if (stringExtra.equals("dump_stop")) {
                        if (BuildConfig.DEBUG) {
                            WakeAudioDump.INSTANCE.stop();
                            return;
                        }
                        return;
                    }
                    break;
                case -1268519338:
                    if (stringExtra.equals("dump_delete")) {
                        if (BuildConfig.DEBUG) {
                            WakeAudioDump wakeAudioDump = WakeAudioDump.INSTANCE;
                            Context applicationContext = context.getApplicationContext();
                            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                            wakeAudioDump.delete(applicationContext);
                            return;
                        }
                        return;
                    }
                    break;
                case -867585902:
                    if (stringExtra.equals("dump_replay")) {
                        if (BuildConfig.DEBUG) {
                            WakeAudioDump.INSTANCE.requestReplay();
                            return;
                        }
                        return;
                    }
                    break;
                case 3540994:
                    if (stringExtra.equals("stop")) {
                        stopVoice(context);
                        return;
                    }
                    break;
                case 109757538:
                    if (stringExtra.equals("start")) {
                        startVoice(context);
                        return;
                    }
                    break;
                case 943200855:
                    if (stringExtra.equals("dump_start")) {
                        if (BuildConfig.DEBUG) {
                            WakeAudioDump wakeAudioDump2 = WakeAudioDump.INSTANCE;
                            Context applicationContext2 = context.getApplicationContext();
                            Intrinsics.checkNotNullExpressionValue(applicationContext2, "getApplicationContext(...)");
                            wakeAudioDump2.start(applicationContext2);
                            return;
                        }
                        return;
                    }
                    break;
            }
        }
        Log.d(TAG, "voice: known action expected (start|stop|dump_start|dump_stop|dump_replay|dump_delete)");
    }

    private final void startVoice(Context context) {
        VoiceController.INSTANCE.start(context);
        Log.d(TAG, "voice: start requested");
    }

    private final void stopVoice(Context context) {
        VoiceController.INSTANCE.stop(context);
    }

    private final void handleSettings(Context context, Intent intent) {
        String string;
        String it = intent.getStringExtra("persona");
        String lowerCase = null;
        if (it != null) {
            if (!JarvisSettings.INSTANCE.getPERSONAS().contains(it)) {
                it = null;
            }
            if (it != null) {
                JarvisSettings.INSTANCE.setPersona(context, it);
                Log.d(TAG, "settings persona=" + it);
            }
        }
        String stringExtra = intent.getStringExtra("preferCloud");
        if (stringExtra != null && (string = StringsKt.trim((CharSequence) stringExtra).toString()) != null) {
            lowerCase = string.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        }
        String preferCloud = lowerCase;
        if (preferCloud != null) {
            boolean enabled = Intrinsics.areEqual(preferCloud, "true") || Intrinsics.areEqual(preferCloud, StructuredAiLimits.SCHEMA_VERSION) || Intrinsics.areEqual(preferCloud, "on");
            JarvisSettings.INSTANCE.setPreferCloudLlm(context, enabled);
            Log.d(TAG, "settings preferCloud=" + enabled + " cloudConfigured=" + JarvisSettings.INSTANCE.cloudConfigured(context));
            return;
        }
        Log.d(TAG, "settings preferCloud=" + JarvisSettings.INSTANCE.preferCloudLlm(context) + " cloudConfigured=" + JarvisSettings.INSTANCE.cloudConfigured(context));
    }

    private final void handleAutonomy(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra(EXTRA_ACTION);
        String action = stringExtra != null ? StringsKt.trim((CharSequence) stringExtra).toString() : null;
        if (action == null) {
            action = "";
        }
        boolean live = intent.getBooleanExtra(JarvisActivity.LIVE, false);
        Log.d(TAG, "autonomy action=" + action + " live=" + live);
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), null, null, new C00571(context, live, action, null), 3, null);
    }

    /* JADX INFO: renamed from: com.jarvis.app.DebugCommandReceiver$handleAutonomy$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DebugCommandReceiver.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.DebugCommandReceiver$handleAutonomy$1", f = "DebugCommandReceiver.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C00571 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $action;
        final /* synthetic */ Context $context;
        final /* synthetic */ boolean $live;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00571(Context context, boolean z, String str, Continuation<? super C00571> continuation) {
            super(2, continuation);
            this.$context = context;
            this.$live = z;
            this.$action = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C00571(this.$context, this.$live, this.$action, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00571) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            ProposedAction proposedActionFromToolId$default;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    try {
                        BrainGraph brainGraph = BrainGraph.INSTANCE;
                        Context applicationContext = this.$context.getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                        brainGraph.build(applicationContext);
                        ToolExecutor toolExecutor = BrainGraph.INSTANCE.toolExecutor();
                        if (toolExecutor == null) {
                            throw new IllegalStateException("toolExecutor not ready".toString());
                        }
                        CompanionProactive.INSTANCE.attachSafeExecutor(toolExecutor, this.$live);
                        switch (this.$action) {
                            case "volume_up":
                                proposedActionFromToolId$default = ProposedActionMapper.fromToolId$default(ProposedActionMapper.INSTANCE, "volume", "volume_up", MapsKt.mapOf(TuplesKt.to(DebugCommandReceiver.EXTRA_ACTION, "up")), null, 8, null);
                                break;
                            case "flashlight_off":
                                proposedActionFromToolId$default = ProposedActionMapper.fromToolId$default(ProposedActionMapper.INSTANCE, "flashlight", null, MapsKt.mapOf(TuplesKt.to(DebugCommandReceiver.EXTRA_ACTION, "off")), null, 10, null);
                                break;
                            case "flashlight_on":
                                proposedActionFromToolId$default = ProposedActionMapper.fromToolId$default(ProposedActionMapper.INSTANCE, "flashlight", null, MapsKt.mapOf(TuplesKt.to(DebugCommandReceiver.EXTRA_ACTION, "on")), null, 10, null);
                                break;
                            default:
                                proposedActionFromToolId$default = null;
                                break;
                        }
                        if (proposedActionFromToolId$default == null) {
                            Log.d(DebugCommandReceiver.TAG, "autonomy: unknown action (flashlight_on|flashlight_off|volume_up)");
                            return Unit.INSTANCE;
                        }
                        ProactiveDecision proactiveDecision = new ProactiveDecision(ProactiveDecisionType.SUGGESTION, DebugCommandReceiver.SYNTHETIC_PATTERN_PREFIX + this.$action, null, "debug-autonomy", ProactiveReasonCode.SELECTED, "DEBUG_AUTONOMY", 0.9f, 0.9f, System.currentTimeMillis(), null, null, null, 3588, null);
                        if (!this.$live) {
                            AutonomyActionPlan autonomyActionPlanPlanAutonomy$default = CompanionProactive.planAutonomy$default(CompanionProactive.INSTANCE, proactiveDecision, proposedActionFromToolId$default, null, null, 12, null);
                            AutonomyPlanStatus status = autonomyActionPlanPlanAutonomy$default.getStatus();
                            AutonomyVerdictType type = autonomyActionPlanPlanAutonomy$default.getVerdict().getType();
                            ToolCall draftToolCall = autonomyActionPlanPlanAutonomy$default.getDraftToolCall();
                            Log.d(DebugCommandReceiver.TAG, "autonomy dry-run status=" + status + " verdict=" + type + " tool=" + (draftToolCall != null ? draftToolCall.getToolId() : null) + " notes=" + CollectionsKt.take(autonomyActionPlanPlanAutonomy$default.getNotes(), 5));
                            return Unit.INSTANCE;
                        }
                        SafeAutonomousResult safeAutonomousResultTrySafeExecute$default = CompanionProactive.trySafeExecute$default(CompanionProactive.INSTANCE, proactiveDecision, proposedActionFromToolId$default, null, 4, null);
                        boolean executed = safeAutonomousResultTrySafeExecute$default.getExecuted();
                        String reason = safeAutonomousResultTrySafeExecute$default.getReason();
                        AutonomyActionPlan plan = safeAutonomousResultTrySafeExecute$default.getPlan();
                        AutonomyPlanStatus status2 = plan != null ? plan.getStatus() : null;
                        ToolResult result = safeAutonomousResultTrySafeExecute$default.getResult();
                        String toolId = result != null ? result.getToolId() : null;
                        ToolResult result2 = safeAutonomousResultTrySafeExecute$default.getResult();
                        Log.d(DebugCommandReceiver.TAG, "autonomy exec executed=" + executed + " reason=" + reason + " plan=" + status2 + " tool=" + toolId + "/" + (result2 != null ? result2.getStatus() : null));
                        return Unit.INSTANCE;
                    } catch (Throwable th) {
                        Log.e(DebugCommandReceiver.TAG, "autonomy failed: " + th.getMessage(), th);
                    }
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    private final void handleAsrTest(Context context, Intent intent) {
        String path = intent.getStringExtra("path");
        if (path == null) {
            Log.d(TAG, "asrtest: --es path /path/to/file.wav required");
            return;
        }
        Context app = context.getApplicationContext();
        File modelDir = new File(app.getExternalFilesDir(null), "models/sherpa-onnx-streaming-zipformer-small-ru-vosk-int8-2025-08-16");
        if (!modelDir.isDirectory()) {
            Log.d(TAG, "asrtest: model dir missing: " + modelDir.getAbsolutePath());
            return;
        }
        File wavFile = new File(path);
        if (wavFile.isFile()) {
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), null, null, new AnonymousClass1(modelDir, wavFile, app, null), 3, null);
        } else {
            Log.d(TAG, "asrtest: wav not found: " + path);
        }
    }

    /* JADX INFO: renamed from: com.jarvis.app.DebugCommandReceiver$handleAsrTest$1, reason: invalid class name */
    /* JADX INFO: compiled from: DebugCommandReceiver.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.DebugCommandReceiver$handleAsrTest$1", f = "DebugCommandReceiver.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $app;
        final /* synthetic */ File $modelDir;
        final /* synthetic */ File $wavFile;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(File file, File file2, Context context, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$modelDir = file;
            this.$wavFile = file2;
            this.$app = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$modelDir, this.$wavFile, this.$app, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:41:0x00d6  */
        /* JADX WARN: Code duplicated, block: B:42:0x00d8  */
        /* JADX WARN: Code duplicated, block: B:65:0x0263 A[Catch: all -> 0x0288, TRY_LEAVE, TryCatch #1 {, blocks: (B:10:0x0022, B:15:0x0041, B:16:0x0053, B:18:0x0058, B:22:0x0068, B:23:0x008f, B:25:0x0092, B:27:0x0096, B:28:0x009c, B:32:0x00ad, B:34:0x00bd, B:35:0x00c2, B:31:0x00a4, B:39:0x00cf, B:43:0x00d9, B:45:0x0119, B:47:0x0126, B:49:0x013d, B:50:0x0157, B:52:0x016e, B:54:0x0175, B:56:0x01b4, B:58:0x01c1, B:60:0x01de, B:62:0x01e5, B:64:0x01f4, B:65:0x0263, B:70:0x027c, B:71:0x0287), top: B:80:0x0022, outer: #0 }] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            boolean z;
            File multiDir;
            String enFull;
            String enTail;
            float[] fArrCopyOf;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    try {
                        Object obj = DebugCommandReceiver.asrTestLock;
                        File file = this.$modelDir;
                        File file2 = this.$wavFile;
                        Context context = this.$app;
                        synchronized (obj) {
                            String absolutePath = file.getAbsolutePath();
                            Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
                            int i = 2;
                            SherpaAsrSession session = new SherpaAsrSession(absolutePath, null, 2, null);
                            byte[] data = FilesKt.readBytes(file2);
                            if (!(data.length > 44)) {
                                throw new IllegalArgumentException("WAV too small (header corrupted?)".toString());
                            }
                            int offset = DebugCommandReceiver.INSTANCE.findWavDataOffset(data);
                            String lastText = "";
                            boolean endpoint = false;
                            float[] chunk = new float[AudioCapture.CHUNK_SAMPLES];
                            ArrayList all = new ArrayList();
                            while (offset + 1 < data.length) {
                                int remain = (data.length - offset) / i;
                                int n = Math.min(chunk.length, remain);
                                if (n > 0) {
                                    for (int i2 = 0; i2 < n; i2++) {
                                        int b = offset + (i2 * 2);
                                        int s = ((data[b + 1] & UByte.MAX_VALUE) << 8) | (data[b] & UByte.MAX_VALUE);
                                        chunk[i2] = ((short) s) / 32768.0f;
                                        all.add(Boxing.boxFloat(chunk[i2]));
                                    }
                                    int i3 = chunk.length;
                                    if (n < i3) {
                                        int length = chunk.length;
                                        for (int i4 = n; i4 < length; i4++) {
                                            chunk[i4] = 0.0f;
                                        }
                                    }
                                    int i5 = n * 2;
                                    offset += i5;
                                    if (n == chunk.length) {
                                        fArrCopyOf = chunk;
                                    } else {
                                        fArrCopyOf = Arrays.copyOf(chunk, n);
                                        Intrinsics.checkNotNullExpressionValue(fArrCopyOf, "copyOf(...)");
                                    }
                                    AsrChunkResult r = session.processChunk(fArrCopyOf);
                                    if (!StringsKt.isBlank(r.getText())) {
                                        lastText = r.getText();
                                    }
                                    if (r.isEndpoint()) {
                                        endpoint = true;
                                        i = 2;
                                    } else {
                                        i = 2;
                                    }
                                } else {
                                    session.release();
                                    if (endpoint) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    Log.d(DebugCommandReceiver.TAG, "asrtest: streamingRU endpoint=" + z + " text=\"" + lastText + "\" pcm=" + all.size());
                                    multiDir = new File(context.getExternalFilesDir(null), "models/sherpa-onnx-nemo-fast-conformer-ctc-be-de-en-es-fr-hr-it-pl-ru-uk-20k-int8");
                                    if (!multiDir.isDirectory() && new File(multiDir, "tokens.txt").isFile()) {
                                        String absolutePath2 = multiDir.getAbsolutePath();
                                        Intrinsics.checkNotNullExpressionValue(absolutePath2, "getAbsolutePath(...)");
                                        OfflineMultilingualAsr off = new OfflineMultilingualAsr(absolutePath2);
                                        int size = all.size();
                                        float[] pcm = new float[size];
                                        int i6 = 0;
                                        while (i6 < size) {
                                            SherpaAsrSession session2 = session;
                                            Object obj2 = all.get(i6);
                                            Intrinsics.checkNotNullExpressionValue(obj2, "get(...)");
                                            pcm[i6] = ((Number) obj2).floatValue();
                                            i6++;
                                            session = session2;
                                        }
                                        String multi = off.decode(pcm);
                                        float[] tail = CommandPcmRefiner.tailOf$default(CommandPcmRefiner.INSTANCE, pcm, 0, 2, null);
                                        String multiTail = tail.length < pcm.length ? off.decode(tail) : "";
                                        off.close();
                                        File enDir = new File(context.getExternalFilesDir(null), "models/" + AsrModelSpec.INSTANCE.getEN_STREAMING().getDirName());
                                        if (new File(enDir, "encoder.int8.onnx").isFile() && new File(enDir, "tokens.txt").isFile()) {
                                            String absolutePath3 = enDir.getAbsolutePath();
                                            Intrinsics.checkNotNullExpressionValue(absolutePath3, "getAbsolutePath(...)");
                                            BufferedEnAsr en = new BufferedEnAsr(absolutePath3, null, 2, null);
                                            enFull = en.decode(pcm);
                                            enTail = tail.length < pcm.length ? en.decode(tail) : "";
                                            en.close();
                                        } else {
                                            enFull = "";
                                            enTail = "";
                                        }
                                        String enHint = CommandPcmRefiner.INSTANCE.bestLatinHint(enFull, enTail, multiTail);
                                        String selected = CommandTextSelector.INSTANCE.select(lastText, multi, enHint);
                                        Log.d(DebugCommandReceiver.TAG, "asrtest: offlineMulti=\"" + multi + "\" multiTail=\"" + multiTail + "\" en=\"" + enFull + "\" enTail=\"" + enTail + "\" hint=\"" + enHint + "\" selected=\"" + selected + "\"");
                                    } else {
                                        Log.d(DebugCommandReceiver.TAG, "asrtest: offline multilingual model missing");
                                    }
                                }
                            }
                            session.release();
                            if (endpoint) {
                                z = true;
                            } else {
                                z = false;
                            }
                            Log.d(DebugCommandReceiver.TAG, "asrtest: streamingRU endpoint=" + z + " text=\"" + lastText + "\" pcm=" + all.size());
                            multiDir = new File(context.getExternalFilesDir(null), "models/sherpa-onnx-nemo-fast-conformer-ctc-be-de-en-es-fr-hr-it-pl-ru-uk-20k-int8");
                            if (!multiDir.isDirectory()) {
                                Log.d(DebugCommandReceiver.TAG, "asrtest: offline multilingual model missing");
                            } else {
                                Log.d(DebugCommandReceiver.TAG, "asrtest: offline multilingual model missing");
                            }
                        }
                    } catch (Exception e) {
                        Log.d(DebugCommandReceiver.TAG, "asrtest: failed: " + e.getMessage());
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: compiled from: DebugCommandReceiver.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b$\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/jarvis/app/DebugCommandReceiver$Companion;", "", "<init>", "()V", "TAG", "", "PROACTIVE_TAG", "asrTestLock", "ACTION_DEBUG_COMMAND", "ACTION_DEBUG_VOICE", "ACTION_DEBUG_TTS", "ACTION_DEBUG_TTS_DUMP", "ACTION_DEBUG_WIDGETS", "ACTION_DEBUG_ECHO_PROBE", "ACTION_ASR_TEST", "ACTION_DEBUG_AUTONOMY", "ACTION_DEBUG_SETTINGS", "ACTION_DEBUG_SANITIZE", "ACTION_DEBUG_CONTEXT", "ACTION_DEBUG_KEYS", "ACTION_DEBUG_MEMORY", "ACTION_DEBUG_KEYS_SET", "ACTION_DEBUG_KEYS_CLEAR", "ACTION_DEBUG_PROACTIVE_STATE", "ACTION_DEBUG_PROACTIVE", "ACTION_DEBUG_PROACTIVE_ACTION", "ACTION_DEBUG_PROACTIVE_CLEAR", "EXTRA_COMMAND", "EXTRA_PROVIDER", "EXTRA_KEY", "EXTRA_ACTION", "EXTRA_TEXT", "EXTRA_PACKAGE", "EXTRA_PROBE", "EXTRA_MODE", "EXTRA_FAKE_STATE", "EXTRA_DELIVERY_ID", "EXTRA_LOG", "SYNTHETIC_TEXT", "SYNTHETIC_PATTERN_KEY", "SYNTHETIC_PATTERN_PREFIX", "findWavDataOffset", "", "data", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int findWavDataOffset(byte[] data) {
            if (data.length < 44) {
                return 44;
            }
            int pos = 12;
            while (pos + 8 <= data.length) {
                String id = new String(data, pos, 4, Charsets.US_ASCII);
                int size = (data[pos + 4] & UByte.MAX_VALUE) | ((data[pos + 5] & UByte.MAX_VALUE) << 8) | ((data[pos + 6] & UByte.MAX_VALUE) << 16) | ((data[pos + 7] & UByte.MAX_VALUE) << 24);
                if (Intrinsics.areEqual(id, "data")) {
                    return pos + 8;
                }
                pos += size + 8;
                if (size < 0) {
                    break;
                }
            }
            return 44;
        }
    }
}
