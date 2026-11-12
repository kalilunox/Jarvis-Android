package com.jarvis.core.common;

import com.jarvis.core.common.model.Intent;
import com.jarvis.core.common.model.UserRequest;
import kotlin.Metadata;

/* JADX INFO: compiled from: IntentResolver.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lcom/jarvis/core/common/IntentResolver;", "", "resolve", "Lcom/jarvis/core/common/model/Intent;", "request", "Lcom/jarvis/core/common/model/UserRequest;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface IntentResolver {
    Intent resolve(UserRequest request);
}
