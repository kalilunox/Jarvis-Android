package com.jarvis.app;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.internal.ImagesContract;
import com.jarvis.app.asr.OpenAiWhisperStreamingAsrBackend;
import com.jarvis.app.tts.OpenAiSpeechTtsBackend;
import com.jarvis.brain.aiprovider.NaraRouterDefaults;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: JarvisSettings.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b5\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tH\u0002J\u000e\u0010\u0010\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0005J\u000e\u0010\u0015\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\u0016\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0005J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\u0019\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u0018J\u000e\u0010\u001b\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\u001c\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u0018J\u000e\u0010\u001e\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\u001f\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u0018J\u000e\u0010!\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\"\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010#\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\tJ\u001e\u0010$\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010%\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u0018J\u000e\u0010&\u001a\u00020'2\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010(\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010)\u001a\u00020'J\u000e\u0010*\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010+\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u0018J\u000e\u0010,\u001a\u00020-2\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010.\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010)\u001a\u00020-J\u000e\u0010/\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\tJ\u0016\u00100\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u0018J\u000e\u00101\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\tJ\u000e\u00102\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\tJ\u0016\u00103\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u0018J\u000e\u00104\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\tJ\u0016\u00105\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u0018J\u000e\u00106\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\tJ\u0016\u00107\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u0018J\u000e\u0010:\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010;\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010<\u001a\u00020\u0005J\u000e\u0010=\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010>\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u0018J\u000e\u0010?\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010@\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010A\u001a\u00020\u0005J\u000e\u0010B\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010C\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010D\u001a\u00020\u0005J\u000e\u0010E\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010F\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u0018J\u000e\u0010G\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010H\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010I\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010J\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010K\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010L\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010M\u001a\u00020\u0005J\u000e\u0010N\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010O\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010D\u001a\u00020\u0005J\u000e\u0010P\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010Q\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010A\u001a\u00020\u0005J\u000e\u0010R\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010S\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010T\u001a\u00020\u0005J\u000e\u0010U\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010V\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010M\u001a\u00020\u0005J\u000e\u0010W\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010X\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010D\u001a\u00020\u0005J\u000e\u0010Y\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010Z\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010A\u001a\u00020\u0005J\u000e\u0010[\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\\\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010T\u001a\u00020\u0005J\u000e\u0010]\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010^\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\u0006\u0010_\u001a\u00020\u0005J\u000e\u0010`\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010a\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\r¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0017\u00108\u001a\b\u0012\u0004\u0012\u00020\u00050\r¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\u000f¨\u0006b"}, d2 = {"Lcom/jarvis/app/JarvisSettings;", "", "<init>", "()V", "PREFS", "", "prefs", "Landroid/content/SharedPreferences;", "context", "Landroid/content/Context;", "secureKeys", "Lcom/jarvis/app/SecureKeyStore;", "THEMES", "", "getTHEMES", "()Ljava/util/List;", "theme", "setTheme", "", "PERSONAS", "getPERSONAS", "persona", "setPersona", "wakeEnabled", "", "setWakeEnabled", "enabled", "voiceWanted", "setVoiceWanted", "wanted", "conversationMode", "setConversationMode", "on", "announceCalls", "announceMessages", "announceHints", "setAnnounce", "kind", "wakeSensitivity", "", "setWakeSensitivity", "value", "voiceLock", "setVoiceLock", "brainConfidenceThreshold", "", "setBrainConfidenceThreshold", "llmEnabled", "setLlmEnabled", "adaptiveListening", "lowPowerMode", "setLowPowerMode", "hudSfx", "setHudSfx", "memoryEnabled", "setMemoryEnabled", "CHAT_PROFILES", "getCHAT_PROFILES", "chatProfile", "setChatProfile", Scopes.PROFILE, "chatPreloadEnabled", "setChatPreloadEnabled", "cloudApiKey", "setCloudApiKey", "key", "cloudBaseUrl", "setCloudBaseUrl", ImagesContract.URL, "preferCloudLlm", "setPreferCloudLlm", "routerMode", "cycleRouterMode", "cloudModelForProfile", "cloudConfigured", "asrMode", "setAsrMode", "mode", "asrCloudBaseUrl", "setAsrCloudBaseUrl", "asrCloudApiKey", "setAsrCloudApiKey", "asrCloudModel", "setAsrCloudModel", "model", "ttsMode", "setTtsMode", "ttsCloudBaseUrl", "setTtsCloudBaseUrl", "ttsCloudApiKey", "setTtsCloudApiKey", "ttsCloudModel", "setTtsCloudModel", "ttsCloudVoice", "setTtsCloudVoice", "voice", "confirmSensitive", "clear", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JarvisSettings {
    private static final String PREFS = "jarvis_prefs";
    public static final JarvisSettings INSTANCE = new JarvisSettings();
    private static final List<String> THEMES = CollectionsKt.listOf((Object[]) new String[]{"CLASSIC", "ARC", "STEALTH", "LIGHT"});
    private static final List<String> PERSONAS = CollectionsKt.listOf((Object[]) new String[]{"CLASSIC", "FRIENDLY", "MINIMAL", "DEVELOPER"});
    private static final List<String> CHAT_PROFILES = CollectionsKt.listOf((Object[]) new String[]{"FAST", "BALANCED", "QUALITY"});

    private JarvisSettings() {
    }

    public final SharedPreferences prefs(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences sharedPreferences = context.getSharedPreferences("jarvis_prefs", 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(...)");
        return sharedPreferences;
    }

    private final SecureKeyStore secureKeys(Context context) {
        SecureKeyMigration.INSTANCE.migrate(context);
        return new SecureKeyStore(context);
    }

    public final List<String> getTHEMES() {
        return THEMES;
    }

    public final String theme(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = prefs(context).getString("theme", "CLASSIC");
        return string == null ? "CLASSIC" : string;
    }

    public final void setTheme(Context context, String theme) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(theme, "theme");
        prefs(context).edit().putString("theme", theme).apply();
    }

    public final List<String> getPERSONAS() {
        return PERSONAS;
    }

    public final String persona(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = prefs(context).getString("persona", "CLASSIC");
        return string == null ? "CLASSIC" : string;
    }

    public final void setPersona(Context context, String persona) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(persona, "persona");
        prefs(context).edit().putString("persona", persona).apply();
    }

    public final boolean wakeEnabled(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return prefs(context).getBoolean("wake_enabled", true);
    }

    public final void setWakeEnabled(Context context, boolean enabled) {
        Intrinsics.checkNotNullParameter(context, "context");
        prefs(context).edit().putBoolean("wake_enabled", enabled).apply();
    }

    public final boolean voiceWanted(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return prefs(context).getBoolean("voice_wanted", false);
    }

    public final void setVoiceWanted(Context context, boolean wanted) {
        Intrinsics.checkNotNullParameter(context, "context");
        prefs(context).edit().putBoolean("voice_wanted", wanted).apply();
    }

    public final boolean conversationMode(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return prefs(context).getBoolean("conversation_mode", true);
    }

    public final void setConversationMode(Context context, boolean on) {
        Intrinsics.checkNotNullParameter(context, "context");
        prefs(context).edit().putBoolean("conversation_mode", on).apply();
    }

    public final boolean announceCalls(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return prefs(context).getBoolean("announce_calls", true);
    }

    public final boolean announceMessages(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return prefs(context).getBoolean("announce_messages", true);
    }

    public final boolean announceHints(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return prefs(context).getBoolean("announce_hints", true);
    }

    public final void setAnnounce(Context context, String kind, boolean on) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(kind, "kind");
        prefs(context).edit().putBoolean("announce_" + kind, on).apply();
    }

    public final int wakeSensitivity(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return prefs(context).getInt("wake_sensitivity", 2);
    }

    public final void setWakeSensitivity(Context context, int value) {
        Intrinsics.checkNotNullParameter(context, "context");
        prefs(context).edit().putInt("wake_sensitivity", RangesKt.coerceIn(value, 0, 2)).apply();
    }

    public final boolean voiceLock(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return prefs(context).getBoolean("voice_lock", true);
    }

    public final void setVoiceLock(Context context, boolean on) {
        Intrinsics.checkNotNullParameter(context, "context");
        prefs(context).edit().putBoolean("voice_lock", on).apply();
    }

    public final float brainConfidenceThreshold(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return prefs(context).getFloat("brain_threshold", 0.6f);
    }

    public final void setBrainConfidenceThreshold(Context context, float value) {
        Intrinsics.checkNotNullParameter(context, "context");
        prefs(context).edit().putFloat("brain_threshold", RangesKt.coerceIn(value, 0.3f, 0.9f)).apply();
    }

    public final boolean llmEnabled(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return prefs(context).getBoolean("llm_enabled", true);
    }

    public final void setLlmEnabled(Context context, boolean enabled) {
        Intrinsics.checkNotNullParameter(context, "context");
        prefs(context).edit().putBoolean("llm_enabled", enabled).apply();
    }

    public final boolean adaptiveListening(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return prefs(context).getBoolean("adaptive_listening", true);
    }

    public final boolean lowPowerMode(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return prefs(context).getBoolean("low_power", false);
    }

    public final void setLowPowerMode(Context context, boolean enabled) {
        Intrinsics.checkNotNullParameter(context, "context");
        prefs(context).edit().putBoolean("low_power", enabled).apply();
    }

    public final boolean hudSfx(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return prefs(context).getBoolean("hud_sfx", false);
    }

    public final void setHudSfx(Context context, boolean enabled) {
        Intrinsics.checkNotNullParameter(context, "context");
        prefs(context).edit().putBoolean("hud_sfx", enabled).apply();
    }

    public final boolean memoryEnabled(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return prefs(context).getBoolean("memory_enabled", true);
    }

    public final void setMemoryEnabled(Context context, boolean enabled) {
        Intrinsics.checkNotNullParameter(context, "context");
        prefs(context).edit().putBoolean("memory_enabled", enabled).apply();
    }

    public final List<String> getCHAT_PROFILES() {
        return CHAT_PROFILES;
    }

    public final String chatProfile(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = prefs(context).getString("chat_profile", "BALANCED");
        return string == null ? "BALANCED" : string;
    }

    public final void setChatProfile(Context context, String profile) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(profile, "profile");
        String p = CHAT_PROFILES.contains(profile) ? profile : "BALANCED";
        prefs(context).edit().putString("chat_profile", p).apply();
    }

    public final boolean chatPreloadEnabled(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return prefs(context).getBoolean("chat_preload", true);
    }

    public final void setChatPreloadEnabled(Context context, boolean enabled) {
        Intrinsics.checkNotNullParameter(context, "context");
        prefs(context).edit().putBoolean("chat_preload", enabled).apply();
    }

    public final String cloudApiKey(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String key = secureKeys(context).getKey("custom");
        return key == null ? "" : key;
    }

    public final void setCloudApiKey(Context context, String key) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(key, "key");
        secureKeys(context).setKey("custom", key);
    }

    public final String cloudBaseUrl(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = prefs(context).getString("cloud_base_url", null);
        String string2 = string != null ? StringsKt.trim((CharSequence) string).toString() : null;
        return string2 == null ? "" : string2;
    }

    public final void setCloudBaseUrl(Context context, String url) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(url, "url");
        prefs(context).edit().putString("cloud_base_url", StringsKt.trim((CharSequence) url).toString()).apply();
    }

    public final boolean preferCloudLlm(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return prefs(context).getBoolean("prefer_cloud_llm", true);
    }

    public final void setPreferCloudLlm(Context context, boolean enabled) {
        Intrinsics.checkNotNullParameter(context, "context");
        prefs(context).edit().putBoolean("prefer_cloud_llm", enabled).apply();
    }

    public final String routerMode(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = prefs(context).getString("router_mode", null);
        if (string == null) {
            return preferCloudLlm(context) ? "HYBRID" : "LOCAL";
        }
        return string;
    }

    public final String cycleRouterMode(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        List order = CollectionsKt.listOf((Object[]) new String[]{"HYBRID", "LOCAL", "CLOUD"});
        String cur = routerMode(context);
        Integer numValueOf = Integer.valueOf(order.indexOf(cur));
        int it = numValueOf.intValue();
        if (!(it >= 0)) {
            numValueOf = null;
        }
        String next = (String) order.get(((numValueOf != null ? numValueOf.intValue() : -1) + 1) % order.size());
        prefs(context).edit().putString("router_mode", next).apply();
        setPreferCloudLlm(context, true ^ Intrinsics.areEqual(next, "LOCAL"));
        return next;
    }

    public final String cloudModelForProfile(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return Intrinsics.areEqual(chatProfile(context), "QUALITY") ? NaraRouterDefaults.MODEL_QUALITY : NaraRouterDefaults.MODEL_FAST;
    }

    public final boolean cloudConfigured(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SecureKeyStore keys = secureKeys(context);
        boolean customConfigured = keys.hasKey("custom") && !StringsKt.isBlank(cloudBaseUrl(context));
        return customConfigured || keys.hasKey("groq") || keys.hasKey("gemini");
    }

    public final String asrMode(Context context) {
        String upperCase;
        String string;
        Intrinsics.checkNotNullParameter(context, "context");
        String str = "AUTO";
        String string2 = prefs(context).getString("asr_mode", "AUTO");
        if (string2 == null || (string = StringsKt.trim((CharSequence) string2).toString()) == null) {
            upperCase = null;
        } else {
            upperCase = string.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        }
        if (upperCase == null) {
            upperCase = "";
        }
        String str2 = upperCase;
        if (!StringsKt.isBlank(str2)) {
            str = str2;
        }
        return str;
    }

    public final void setAsrMode(Context context, String mode) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mode, "mode");
        String it = StringsKt.trim((CharSequence) mode).toString().toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(it, "toUpperCase(...)");
        if (!SetsKt.setOf((Object[]) new String[]{"AUTO", "CLOUD_PREFERRED", "LOCAL_ONLY"}).contains(it)) {
            it = "AUTO";
        }
        prefs(context).edit().putString("asr_mode", it).apply();
    }

    public final String asrCloudBaseUrl(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = prefs(context).getString("asr_cloud_base_url", "");
        String string2 = string != null ? StringsKt.trim((CharSequence) string).toString() : null;
        return string2 == null ? "" : string2;
    }

    public final void setAsrCloudBaseUrl(Context context, String url) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(url, "url");
        prefs(context).edit().putString("asr_cloud_base_url", StringsKt.trim((CharSequence) url).toString()).apply();
    }

    public final String asrCloudApiKey(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SecureKeyStore keys = secureKeys(context);
        String dedicated = keys.getKey("custom_asr");
        if (dedicated == null) {
            dedicated = "";
        }
        if (StringsKt.isBlank(dedicated)) {
            return !StringsKt.isBlank(asrCloudBaseUrl(context)) ? cloudApiKey(context) : "";
        }
        return dedicated;
    }

    public final void setAsrCloudApiKey(Context context, String key) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(key, "key");
        secureKeys(context).setKey("custom_asr", key);
    }

    public final String asrCloudModel(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = prefs(context).getString("asr_cloud_model", null);
        String string2 = string != null ? StringsKt.trim((CharSequence) string).toString() : null;
        if (string2 == null) {
            string2 = "";
        }
        String v = string2;
        String str = v;
        if (StringsKt.isBlank(str)) {
            str = OpenAiWhisperStreamingAsrBackend.DEFAULT_MODEL;
        }
        return str;
    }

    public final void setAsrCloudModel(Context context, String model) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(model, "model");
        prefs(context).edit().putString("asr_cloud_model", StringsKt.trim((CharSequence) model).toString()).apply();
    }

    public final String ttsMode(Context context) {
        String upperCase;
        String string;
        Intrinsics.checkNotNullParameter(context, "context");
        String str = "AUTO";
        String string2 = prefs(context).getString("tts_mode", "AUTO");
        if (string2 == null || (string = StringsKt.trim((CharSequence) string2).toString()) == null) {
            upperCase = null;
        } else {
            upperCase = string.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        }
        if (upperCase == null) {
            upperCase = "";
        }
        String str2 = upperCase;
        if (!StringsKt.isBlank(str2)) {
            str = str2;
        }
        return str;
    }

    public final void setTtsMode(Context context, String mode) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mode, "mode");
        String it = StringsKt.trim((CharSequence) mode).toString().toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(it, "toUpperCase(...)");
        if (!SetsKt.setOf((Object[]) new String[]{"AUTO", "CLOUD_PREFERRED", "LOCAL_ONLY"}).contains(it)) {
            it = "AUTO";
        }
        prefs(context).edit().putString("tts_mode", it).apply();
    }

    public final String ttsCloudBaseUrl(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = prefs(context).getString("tts_cloud_base_url", "");
        String string2 = string != null ? StringsKt.trim((CharSequence) string).toString() : null;
        String dedicated = string2 != null ? string2 : "";
        return !StringsKt.isBlank(dedicated) ? dedicated : asrCloudBaseUrl(context);
    }

    public final void setTtsCloudBaseUrl(Context context, String url) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(url, "url");
        prefs(context).edit().putString("tts_cloud_base_url", StringsKt.trim((CharSequence) url).toString()).apply();
    }

    public final String ttsCloudApiKey(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SecureKeyStore keys = secureKeys(context);
        String dedicated = keys.getKey("custom_tts");
        if (dedicated == null) {
            dedicated = "";
        }
        if (!StringsKt.isBlank(dedicated)) {
            return dedicated;
        }
        if (StringsKt.isBlank(ttsCloudBaseUrl(context))) {
            return "";
        }
        String key = keys.getKey("custom_asr");
        String asrKey = key != null ? key : "";
        return !StringsKt.isBlank(asrKey) ? asrKey : cloudApiKey(context);
    }

    public final void setTtsCloudApiKey(Context context, String key) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(key, "key");
        secureKeys(context).setKey("custom_tts", key);
    }

    public final String ttsCloudModel(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = prefs(context).getString("tts_cloud_model", null);
        String string2 = string != null ? StringsKt.trim((CharSequence) string).toString() : null;
        if (string2 == null) {
            string2 = "";
        }
        String v = string2;
        String str = v;
        if (StringsKt.isBlank(str)) {
            str = OpenAiSpeechTtsBackend.DEFAULT_MODEL;
        }
        return str;
    }

    public final void setTtsCloudModel(Context context, String model) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(model, "model");
        prefs(context).edit().putString("tts_cloud_model", StringsKt.trim((CharSequence) model).toString()).apply();
    }

    public final String ttsCloudVoice(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = prefs(context).getString("tts_cloud_voice", null);
        String string2 = string != null ? StringsKt.trim((CharSequence) string).toString() : null;
        if (string2 == null) {
            string2 = "";
        }
        String v = string2;
        String str = v;
        if (StringsKt.isBlank(str)) {
            str = OpenAiSpeechTtsBackend.DEFAULT_VOICE;
        }
        return str;
    }

    public final void setTtsCloudVoice(Context context, String voice) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(voice, "voice");
        prefs(context).edit().putString("tts_cloud_voice", StringsKt.trim((CharSequence) voice).toString()).apply();
    }

    public final boolean confirmSensitive(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return prefs(context).getBoolean("confirm_sensitive", true);
    }

    public final void clear(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        prefs(context).edit().clear().apply();
    }
}
