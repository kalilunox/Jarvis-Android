package com.jarvis.core.cloud;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.jarvis.app.VoiceService;
import kotlin.Metadata;

/* JADX INFO: compiled from: CloudCall.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lcom/jarvis/core/cloud/CloudCall;", "", "start", "Lcom/jarvis/core/cloud/CancelHandle;", VoiceService.EXTRA_ENTRY, "Lcom/jarvis/core/cloud/ChainEntry;", "paramCandidateIndex", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/jarvis/core/cloud/CallListener;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface CloudCall {
    CancelHandle start(ChainEntry entry, int paramCandidateIndex, CallListener listener);
}
