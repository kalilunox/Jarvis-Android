package com.jarvis.core.cloud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;

/* JADX INFO: compiled from: ChainRouter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u001bB!\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ<\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0086@¢\u0006\u0002\u0010\u001aR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/jarvis/core/cloud/ChainRouter;", "", "clock", "Lkotlin/Function0;", "", "config", "Lcom/jarvis/core/cloud/ChainConfig;", "<init>", "(Lkotlin/jvm/functions/Function0;Lcom/jarvis/core/cloud/ChainConfig;)V", "acceptedParamIndex", "", "", "", "run", "Lcom/jarvis/core/cloud/ChainResult;", "request", "Lcom/jarvis/core/cloud/ChainRouter$Request;", "availableEntries", "", "Lcom/jarvis/core/cloud/ChainEntry;", "health", "Lcom/jarvis/core/cloud/ChainHealth;", "cloudCall", "Lcom/jarvis/core/cloud/CloudCall;", "sink", "Lcom/jarvis/core/cloud/TokenSink;", "(Lcom/jarvis/core/cloud/ChainRouter$Request;Ljava/util/List;Lcom/jarvis/core/cloud/ChainHealth;Lcom/jarvis/core/cloud/CloudCall;Lcom/jarvis/core/cloud/TokenSink;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Request", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ChainRouter {
    private final Map<String, Integer> acceptedParamIndex;
    private final Function0<Long> clock;
    private final ChainConfig config;

    /* JADX INFO: renamed from: com.jarvis.core.cloud.ChainRouter$run$1, reason: invalid class name */
    /* JADX INFO: compiled from: ChainRouter.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.core.cloud.ChainRouter", f = "ChainRouter.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {70}, m = "run", n = {"request", "availableEntries", "health", "cloudCall", "sink", "eventChannel", "active"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChainRouter.this.run(null, null, null, null, null, this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ChainRouter() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public ChainRouter(Function0<Long> clock, ChainConfig config) {
        Intrinsics.checkNotNullParameter(clock, "clock");
        Intrinsics.checkNotNullParameter(config, "config");
        this.clock = clock;
        this.config = config;
        this.acceptedParamIndex = new LinkedHashMap();
    }

    public /* synthetic */ ChainRouter(Function0 function0, ChainConfig chainConfig, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new Function0() { // from class: com.jarvis.core.cloud.ChainRouter$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Long.valueOf(System.currentTimeMillis());
            }
        } : function0, (i & 2) != 0 ? ChainConfig.INSTANCE.getDefault() : chainConfig);
    }

    /* JADX INFO: compiled from: ChainRouter.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u001a\b\u0002\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00040\u0003HÆ\u0003J%\u0010\u000b\u001a\u00020\u00002\u001a\b\u0002\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00040\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001R#\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, d2 = {"Lcom/jarvis/core/cloud/ChainRouter$Request;", "", "messages", "", "", "", "<init>", "(Ljava/util/List;)V", "getMessages", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Request {
        private final List<Map<String, Object>> messages;

        /* JADX WARN: Multi-variable type inference failed */
        public Request() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Request copy$default(Request request, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = request.messages;
            }
            return request.copy(list);
        }

        public final List<Map<String, Object>> component1() {
            return this.messages;
        }

        public final Request copy(List<? extends Map<String, ? extends Object>> messages) {
            Intrinsics.checkNotNullParameter(messages, "messages");
            return new Request(messages);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Request) && Intrinsics.areEqual(this.messages, ((Request) other).messages);
        }

        public int hashCode() {
            return this.messages.hashCode();
        }

        public String toString() {
            return "Request(messages=" + this.messages + ")";
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Request(List<? extends Map<String, ? extends Object>> messages) {
            Intrinsics.checkNotNullParameter(messages, "messages");
            this.messages = messages;
        }

        public /* synthetic */ Request(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? CollectionsKt.emptyList() : list);
        }

        public final List<Map<String, Object>> getMessages() {
            return this.messages;
        }
    }

    /* JADX WARN: Code duplicated, block: B:27:0x00dd A[LOOP:0: B:25:0x00d7->B:27:0x00dd, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:31:0x00fa A[LOOP:1: B:29:0x00f4->B:31:0x00fa, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:42:0x0131 A[LOOP:2: B:40:0x012b->B:42:0x0131, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:46:0x014e A[LOOP:3: B:44:0x0148->B:46:0x014e, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final Object run(Request request, List<ChainEntry> list, ChainHealth health, CloudCall cloudCall, TokenSink sink, Continuation<? super ChainResult> continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        List active;
        ChainHealth health2;
        List active2;
        Object objCoroutineScope;
        Iterable $this$forEach$iv;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object $result = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (anonymousClass2.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Channel eventChannel = ChannelKt.Channel$default(Integer.MAX_VALUE, null, null, 6, null);
                active = Collections.synchronizedList(new ArrayList());
                try {
                    AnonymousClass2 anonymousClass3 = new AnonymousClass2(list, active, sink, health, eventChannel, cloudCall, null);
                    anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(request);
                    anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(list);
                    health2 = health;
                    try {
                        anonymousClass2.L$2 = health2;
                        anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(cloudCall);
                        anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(sink);
                        anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(eventChannel);
                        anonymousClass2.L$6 = active;
                        anonymousClass2.label = 1;
                        objCoroutineScope = CoroutineScopeKt.coroutineScope(anonymousClass3, anonymousClass2);
                        if (objCoroutineScope == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        Intrinsics.checkNotNull(active);
                        Iterable $this$forEach$iv2 = active;
                        for (Object element$iv : $this$forEach$iv2) {
                            Attempt it = (Attempt) element$iv;
                            it.cancelAttempt();
                        }
                        Iterable $this$forEach$iv3 = active;
                        $this$forEach$iv = $this$forEach$iv3;
                        for (Object element$iv2 : $this$forEach$iv) {
                            Attempt it2 = (Attempt) element$iv2;
                            health2.releaseProbe(it2.getEntry());
                            $this$forEach$iv = $this$forEach$iv;
                        }
                        return objCoroutineScope;
                    } catch (Throwable th) {
                        th = th;
                        active2 = active;
                        Intrinsics.checkNotNull(active2);
                        Iterable $this$forEach$iv4 = active2;
                        for (Object element$iv3 : $this$forEach$iv4) {
                            Attempt it3 = (Attempt) element$iv3;
                            it3.cancelAttempt();
                        }
                        Iterable $this$forEach$iv5 = active2;
                        for (Object element$iv4 : $this$forEach$iv5) {
                            Attempt it4 = (Attempt) element$iv4;
                            health2.releaseProbe(it4.getEntry());
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    health2 = health;
                }
                break;
            case 1:
                active2 = (List) anonymousClass2.L$6;
                ChainHealth health3 = (ChainHealth) anonymousClass2.L$2;
                try {
                    ResultKt.throwOnFailure($result);
                    active = active2;
                    health2 = health3;
                    objCoroutineScope = $result;
                    Intrinsics.checkNotNull(active);
                    Iterable $this$forEach$iv6 = active;
                    while (r9.hasNext()) {
                        Attempt it5 = (Attempt) element$iv;
                        it5.cancelAttempt();
                    }
                    Iterable $this$forEach$iv7 = active;
                    $this$forEach$iv = $this$forEach$iv7;
                    while (r9.hasNext()) {
                        Attempt it6 = (Attempt) element$iv2;
                        health2.releaseProbe(it6.getEntry());
                        $this$forEach$iv = $this$forEach$iv;
                    }
                    return objCoroutineScope;
                } catch (Throwable th3) {
                    th = th3;
                    health2 = health3;
                    Intrinsics.checkNotNull(active2);
                    Iterable $this$forEach$iv8 = active2;
                    while (r10.hasNext()) {
                        Attempt it7 = (Attempt) element$iv3;
                        it7.cancelAttempt();
                    }
                    Iterable $this$forEach$iv9 = active2;
                    while (r10.hasNext()) {
                        Attempt it8 = (Attempt) element$iv4;
                        health2.releaseProbe(it8.getEntry());
                    }
                    throw th;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: renamed from: com.jarvis.core.cloud.ChainRouter$run$2, reason: invalid class name */
    /* JADX INFO: compiled from: ChainRouter.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/jarvis/core/cloud/ChainResult;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.core.cloud.ChainRouter$run$2", f = "ChainRouter.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {328}, m = "invokeSuspend", n = {"$this$coroutineScope", "cfg", "failures", "allAttempts", "winnerRef", "entries", "hedged", "firstTokenMs", "nextEntryIdx", "result", "winner", "$this$select_u24lambda_u241$iv", "startMs", "deadline", "lastTokenAt", "hedgeAt", "now", "canHedge", "timeout", "$i$f$select", "$i$a$-run-SelectKt$select$3$iv"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "J$0", "J$1", "J$2", "J$3", "J$4", "I$0", "J$5", "I$1", "I$2"}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ChainResult>, Object> {
        final /* synthetic */ List<Attempt> $active;
        final /* synthetic */ List<ChainEntry> $availableEntries;
        final /* synthetic */ CloudCall $cloudCall;
        final /* synthetic */ Channel<AttemptEvent> $eventChannel;
        final /* synthetic */ ChainHealth $health;
        final /* synthetic */ TokenSink $sink;
        int I$0;
        int I$1;
        int I$2;
        long J$0;
        long J$1;
        long J$2;
        long J$3;
        long J$4;
        long J$5;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(List<ChainEntry> list, List<Attempt> list2, TokenSink tokenSink, ChainHealth chainHealth, Channel<AttemptEvent> channel, CloudCall cloudCall, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$availableEntries = list;
            this.$active = list2;
            this.$sink = tokenSink;
            this.$health = chainHealth;
            this.$eventChannel = channel;
            this.$cloudCall = cloudCall;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = ChainRouter.this.new AnonymousClass2(this.$availableEntries, this.$active, this.$sink, this.$health, this.$eventChannel, this.$cloudCall, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ChainResult> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:105:0x0527  */
        /* JADX WARN: Code duplicated, block: B:107:0x052b  */
        /* JADX WARN: Code duplicated, block: B:109:0x0538  */
        /* JADX WARN: Code duplicated, block: B:111:0x0557  */
        /* JADX WARN: Code duplicated, block: B:112:0x055c  */
        /* JADX WARN: Code duplicated, block: B:124:0x0604  */
        /* JADX WARN: Code duplicated, block: B:126:0x060c  */
        /* JADX WARN: Code duplicated, block: B:128:0x061b A[ADDED_TO_REGION] */
        /* JADX WARN: Code duplicated, block: B:131:0x0624  */
        /* JADX WARN: Code duplicated, block: B:133:0x062b  */
        /* JADX WARN: Code duplicated, block: B:135:0x0643  */
        /* JADX WARN: Code duplicated, block: B:137:0x0684  */
        /* JADX WARN: Code duplicated, block: B:139:0x06c4  */
        /* JADX WARN: Code duplicated, block: B:143:0x06f0  */
        /* JADX WARN: Code duplicated, block: B:145:0x0716  */
        /* JADX WARN: Code duplicated, block: B:149:0x0742  */
        /* JADX WARN: Code duplicated, block: B:151:0x0755  */
        /* JADX WARN: Code duplicated, block: B:152:0x0765  */
        /* JADX WARN: Code duplicated, block: B:155:0x0781  */
        /* JADX WARN: Code duplicated, block: B:158:0x07ac  */
        /* JADX WARN: Code duplicated, block: B:160:0x07b2  */
        /* JADX WARN: Code duplicated, block: B:166:0x07ea  */
        /* JADX WARN: Code duplicated, block: B:168:0x0806  */
        /* JADX WARN: Code duplicated, block: B:170:0x0849  */
        /* JADX WARN: Code duplicated, block: B:172:0x084f A[ADDED_TO_REGION, REMOVE] */
        /* JADX WARN: Code duplicated, block: B:33:0x01f1  */
        /* JADX WARN: Code duplicated, block: B:35:0x0209  */
        /* JADX WARN: Code duplicated, block: B:37:0x0213  */
        /* JADX WARN: Code duplicated, block: B:38:0x0218  */
        /* JADX WARN: Code duplicated, block: B:40:0x023d  */
        /* JADX WARN: Code duplicated, block: B:42:0x0243  */
        /* JADX WARN: Code duplicated, block: B:50:0x026a  */
        /* JADX WARN: Code duplicated, block: B:55:0x0280  */
        /* JADX WARN: Code duplicated, block: B:58:0x0285  */
        /* JADX WARN: Code duplicated, block: B:59:0x0296 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:60:0x0298  */
        /* JADX WARN: Code duplicated, block: B:61:0x02ab  */
        /* JADX WARN: Code duplicated, block: B:64:0x02b8  */
        /* JADX WARN: Code duplicated, block: B:66:0x0348 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:67:0x0349  */
        /* JADX WARN: Code duplicated, block: B:69:0x037c  */
        /* JADX WARN: Code duplicated, block: B:81:0x040b  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:67:0x0349 -> B:68:0x0361). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:69:0x037c -> B:71:0x03ac). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r61) {
            /*
                Method dump skipped, instruction units count: 2570
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jarvis.core.cloud.ChainRouter.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        private static final List<String> invokeSuspend$attemptReport(List<Attempt> list, AtomicReference<Attempt> atomicReference, ChainRouter this$0) {
            String cls;
            Intrinsics.checkNotNull(list);
            List<Attempt> $this$map$iv = list;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                Attempt a = (Attempt) item$iv$iv;
                if (a.getLastError() != null) {
                    cls = String.valueOf(a.getLastError());
                } else {
                    cls = a == atomicReference.get() ? "OK" : "NO_RESPONSE";
                }
                long ms = RangesKt.coerceAtLeast(((Number) this$0.clock.invoke()).longValue() - a.getStartedAtMs(), 0L);
                destination$iv$iv.add(a.getEntry().getProviderId() + "/" + a.getEntry().getModelId() + "=" + cls + "@" + ms + "ms");
            }
            return (List) destination$iv$iv;
        }

        private static final Attempt invokeSuspend$launchAttempt(ChainHealth $health, Channel<AttemptEvent> channel, ChainRouter this$0, List<Attempt> list, CoroutineScope $this$coroutineScope, List<Attempt> list2, CloudCall $cloudCall, ChainEntry entry, int paramIndex) {
            if (!$health.tryAcquireProbe(entry)) {
                return null;
            }
            Attempt a = new Attempt(entry, paramIndex, channel, new ThoughtStripper());
            a.setStartedAtMs(((Number) this$0.clock.invoke()).longValue());
            list.add(a);
            BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, Dispatchers.getDefault(), null, new ChainRouter$run$2$launchAttempt$1($cloudCall, entry, paramIndex, a, null), 2, null);
            list2.add(a);
            return a;
        }

        static /* synthetic */ Void invokeSuspend$failWinner$default(List list, ChainHealth chainHealth, Ref.BooleanRef booleanRef, List list2, AtomicReference atomicReference, ChainRouter chainRouter, Attempt attempt, ErrorClass errorClass, Integer num, int i, Object obj) {
            return invokeSuspend$failWinner(list, chainHealth, booleanRef, list2, atomicReference, chainRouter, attempt, errorClass, (i & 256) != 0 ? null : num);
        }

        private static final Void invokeSuspend$failWinner(List<ErrorClass> list, ChainHealth $health, Ref.BooleanRef hedged, List<Attempt> list2, AtomicReference<Attempt> atomicReference, ChainRouter this$0, Attempt winner, ErrorClass cls, Integer retryAfter) {
            list.add(cls);
            winner.setLastError(cls);
            ChainHealth.recordFailure$default($health, winner.getEntry(), cls, retryAfter, false, 8, null);
            throw new AllFailedException(CollectionsKt.toList(list), hedged.element, invokeSuspend$attemptReport(list2, atomicReference, this$0), true);
        }

        private static final boolean invokeSuspend$startNext(Ref.IntRef nextEntryIdx, List<ChainEntry> list, ChainRouter this$0, ChainHealth $health, Channel<AttemptEvent> channel, List<Attempt> list2, CoroutineScope $this$coroutineScope, List<Attempt> list3, CloudCall $cloudCall) {
            ChainEntry e;
            Integer num;
            do {
                if (nextEntryIdx.element >= list.size()) {
                    return false;
                }
                int i = nextEntryIdx.element;
                nextEntryIdx.element = i + 1;
                e = list.get(i);
                num = (Integer) this$0.acceptedParamIndex.get(e.getProviderId() + "/" + e.getModelId());
            } while (invokeSuspend$launchAttempt($health, channel, this$0, list2, $this$coroutineScope, list3, $cloudCall, e, num != null ? num.intValue() : 0) == null);
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean invokeSuspend$lambda$5(Attempt $a, Attempt it) {
            return it != $a;
        }
    }
}
