package com.jarvis.app;

import android.content.Context;
import android.os.SystemClock;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.jarvis.app.ui.JarvisActivity;
import com.jarvis.core.companion.PhoneContextBus;
import com.jarvis.core.companion.PhoneObservation;
import com.jarvis.core.routines.EventMatcher;
import com.jarvis.core.routines.EventRule;
import com.jarvis.core.routines.PhoneEvent;
import com.jarvis.tools.LauncherTool;
import java.util.HashMap;
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
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SharedFlow;

/* JADX INFO: compiled from: EventRoutineRunner.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0014\u001a\u00020\u0015J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0017\u001a\u00020\u0010H\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u001eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R.\u0010\u000e\u001a\"\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000fj\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0010`\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/jarvis/app/EventRoutineRunner;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "scope", "Lkotlinx/coroutines/CoroutineScope;", "store", "Lcom/jarvis/app/PrefsEventRoutines;", JarvisActivity.ROUTINES, "Lcom/jarvis/app/PrefsCustomRoutines;", "launcher", "Lcom/jarvis/tools/LauncherTool;", "packages", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "matcher", "Lcom/jarvis/core/routines/EventMatcher;", "start", "", "packageOf", "name", NotificationCompat.CATEGORY_EVENT, "Lcom/jarvis/core/routines/PhoneEvent;", "observation", "Lcom/jarvis/core/companion/PhoneObservation;", "launchRule", "rule", "Lcom/jarvis/core/routines/EventRule;", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class EventRoutineRunner {
    public static final String NETWORK = "network";
    public static final String STARTUP = "startup";
    private static final int WAIT_POLLS = 15;
    private static final long WAIT_POLL_MS = 2000;
    private final Context context;
    private final LauncherTool launcher;
    private final EventMatcher matcher;
    private final HashMap<String, String> packages;
    private final PrefsCustomRoutines routines;
    private final CoroutineScope scope;
    private final PrefsEventRoutines store;

    public EventRoutineRunner(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.scope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getDefault()));
        this.store = new PrefsEventRoutines(this.context);
        this.routines = new PrefsCustomRoutines(this.context);
        this.launcher = new LauncherTool(this.context);
        this.packages = new HashMap<>();
        this.matcher = new EventMatcher(new Function2() { // from class: com.jarvis.app.EventRoutineRunner$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(EventRoutineRunner.matcher$lambda$0(this.f$0, (String) obj, (String) obj2));
            }
        }, 0L, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean matcher$lambda$0(EventRoutineRunner this$0, String name, String pkg) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(pkg, "pkg");
        return Intrinsics.areEqual(this$0.packageOf(name), pkg);
    }

    /* JADX INFO: renamed from: com.jarvis.app.EventRoutineRunner$start$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: EventRoutineRunner.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.EventRoutineRunner$start$1", f = "EventRoutineRunner.kt", i = {}, l = {31}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C00591 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C00591(Continuation<? super C00591> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return EventRoutineRunner.this.new C00591(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00591) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    SharedFlow<PhoneObservation> observations = PhoneContextBus.INSTANCE.getObservations();
                    final EventRoutineRunner eventRoutineRunner = EventRoutineRunner.this;
                    this.label = 1;
                    if (observations.collect(new FlowCollector() { // from class: com.jarvis.app.EventRoutineRunner.start.1.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit((PhoneObservation) value, (Continuation<? super Unit>) $completion);
                        }

                        public final Object emit(PhoneObservation observation, Continuation<? super Unit> continuation) {
                            List<EventRule> listRules = eventRoutineRunner.store.rules();
                            if (listRules.isEmpty()) {
                                return Unit.INSTANCE;
                            }
                            Iterable $this$forEach$iv = eventRoutineRunner.matcher.match(eventRoutineRunner.event(observation), listRules, SystemClock.elapsedRealtime());
                            EventRoutineRunner eventRoutineRunner2 = eventRoutineRunner;
                            for (Object element$iv : $this$forEach$iv) {
                                EventRule p0 = (EventRule) element$iv;
                                eventRoutineRunner2.launchRule(p0);
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

    public final void start() {
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C00591(null), 3, null);
    }

    private final String packageOf(String name) {
        String strResolvePackage;
        Map $this$getOrPut$iv = this.packages;
        String str = $this$getOrPut$iv.get(name);
        if (str == null) {
            strResolvePackage = this.launcher.resolvePackage(name);
            $this$getOrPut$iv.put(name, strResolvePackage);
        } else {
            strResolvePackage = str;
        }
        return strResolvePackage;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PhoneEvent event(PhoneObservation observation) {
        return new PhoneEvent(observation.getType(), observation.getEntities().get(NETWORK), observation.getPackageName(), Intrinsics.areEqual(observation.getEntities().get(STARTUP), "true"));
    }

    /* JADX INFO: renamed from: com.jarvis.app.EventRoutineRunner$launchRule$1, reason: invalid class name */
    /* JADX INFO: compiled from: EventRoutineRunner.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.EventRoutineRunner$launchRule$1", f = "EventRoutineRunner.kt", i = {0, 0}, l = {ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE}, m = "invokeSuspend", n = {"it", "$i$a$-repeat-EventRoutineRunner$launchRule$1$1"}, s = {"I$2", "I$3"}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ EventRule $rule;
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(EventRule eventRule, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$rule = eventRule;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return EventRoutineRunner.this.new AnonymousClass1(this.$rule, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:13:0x0044  */
        /* JADX WARN: Code duplicated, block: B:15:0x004e  */
        /* JADX WARN: Code duplicated, block: B:17:0x0060 A[RETURN] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x004c -> B:19:0x0062). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x005e -> B:19:0x0062). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r14) {
            /*
                Method dump skipped, instruction units count: 208
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jarvis.app.EventRoutineRunner.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void launchRule(EventRule rule) {
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AnonymousClass1(rule, null), 3, null);
    }
}
