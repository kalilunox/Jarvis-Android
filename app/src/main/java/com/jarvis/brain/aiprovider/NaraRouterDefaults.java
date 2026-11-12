package com.jarvis.brain.aiprovider;

import kotlin.Metadata;

/* JADX INFO: compiled from: OpenAiCompatibleCloudClient.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/jarvis/brain/aiprovider/NaraRouterDefaults;", "", "<init>", "()V", "MODEL_FAST", "", "MODEL_QUALITY", "CONNECT_TIMEOUT_MS", "", "READ_TIMEOUT_MS", "MAX_OUTPUT_TOKENS", "USER_AGENT", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NaraRouterDefaults {
    public static final int CONNECT_TIMEOUT_MS = 12000;
    public static final NaraRouterDefaults INSTANCE = new NaraRouterDefaults();
    public static final int MAX_OUTPUT_TOKENS = 256;
    public static final String MODEL_FAST = "agnes-2.5-flash";
    public static final String MODEL_QUALITY = "mistral-medium-3-5";
    public static final int READ_TIMEOUT_MS = 45000;
    public static final String USER_AGENT = "Jarvis/1.0 (Android; personal assistant)";

    private NaraRouterDefaults() {
    }
}
