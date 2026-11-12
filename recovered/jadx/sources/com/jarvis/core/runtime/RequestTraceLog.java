package com.jarvis.core.runtime;

import android.util.Log;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RequestTrace.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0006R(\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/jarvis/core/runtime/RequestTraceLog;", "", "<init>", "()V", "sink", "Lkotlin/Function1;", "Lcom/jarvis/core/runtime/RequestTrace;", "", "getSink", "()Lkotlin/jvm/functions/Function1;", "setSink", "(Lkotlin/jvm/functions/Function1;)V", "emit", "trace", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RequestTraceLog {
    public static final RequestTraceLog INSTANCE = new RequestTraceLog();
    private static volatile Function1<? super RequestTrace, Unit> sink;

    private RequestTraceLog() {
    }

    public final Function1<RequestTrace, Unit> getSink() {
        return sink;
    }

    public final void setSink(Function1<? super RequestTrace, Unit> function1) {
        sink = function1;
    }

    public final void emit(RequestTrace trace) {
        Intrinsics.checkNotNullParameter(trace, "trace");
        try {
            Result.Companion companion = Result.INSTANCE;
            RequestTraceLog requestTraceLog = this;
            Result.m392constructorimpl(Integer.valueOf(Log.i("Jarvis.Trace", trace.summaryLine())));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        try {
            Result.Companion companion3 = Result.INSTANCE;
            RequestTraceLog requestTraceLog2 = this;
            InteractionLatency it = trace.getInteractionLatency();
            Result.m392constructorimpl(it != null ? Integer.valueOf(Log.i("Jarvis.Latency", it.summaryLine())) : null);
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th2));
        }
        try {
            Result.Companion companion5 = Result.INSTANCE;
            RequestTraceLog requestTraceLog3 = this;
            LatencyStatsStore.INSTANCE.recordFrom(trace);
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th3) {
            Result.Companion companion6 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th3));
        }
        Function1<? super RequestTrace, Unit> function1 = sink;
        if (function1 != null) {
            function1.invoke(trace);
        }
    }
}
