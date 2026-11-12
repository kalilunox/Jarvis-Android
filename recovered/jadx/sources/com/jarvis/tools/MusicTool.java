package com.jarvis.tools;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSessionManager;
import android.media.session.PlaybackState;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.view.KeyEvent;
import androidx.core.app.NotificationCompat;
import androidx.media.utils.MediaConstants;
import com.google.android.gms.actions.SearchIntents;
import com.jarvis.app.AndroidReminderBook;
import com.jarvis.app.VoiceIdentity;
import com.jarvis.app.VoiceService;
import com.jarvis.app.proactive.PhoneStateReader;
import com.jarvis.brain.aiprovider.AICompletionRequest;
import com.jarvis.core.common.Tool;
import com.jarvis.core.common.model.OnlineRequirement;
import com.jarvis.core.common.model.ParameterType;
import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.common.model.ToolParameter;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import com.jarvis.core.nlp.MusicQueryMatcher;
import com.jarvis.screencontrol.JarvisNotificationListenerService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.random.Random;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.scheduling.WorkQueueKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: MusicTool.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u0000 i2\u00020\u0001:\u0004fghiB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J \u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\"\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u0010H\u0002J\u0010\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u001a\u0010\u0018\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010H\u0002J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J\u0010\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J0\u0010\u001b\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\u001a\u0010\u001e\u001a\u0016\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u001fH\u0002J\"\u0010\"\u001a\u0004\u0018\u00010\u00102\u0006\u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020!2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J*\u0010%\u001a\u0004\u0018\u00010\u00102\u0006\u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020!2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010&\u001a\u00020'H\u0002J2\u0010(\u001a\u0004\u0018\u00010\u00102\u0006\u0010$\u001a\u00020!2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00102\u0006\u0010,\u001a\u00020\u00102\u0006\u0010-\u001a\u00020\u0014H\u0002J\"\u0010.\u001a\u0004\u0018\u00010\u00102\u0006\u0010$\u001a\u00020!2\u0006\u0010/\u001a\u00020\u00102\u0006\u00100\u001a\u00020\u0010H\u0002J.\u00101\u001a\n\u0012\u0004\u0012\u00020*\u0018\u0001022\f\u00103\u001a\b\u0012\u0004\u0012\u00020*042\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u00105\u001a\u000206H\u0002J\u0010\u00107\u001a\u00020\u00102\u0006\u0010)\u001a\u00020*H\u0002J\u001a\u00108\u001a\u0004\u0018\u00010\u00102\u0006\u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020!H\u0002J*\u00109\u001a\u0004\u0018\u00010\u00102\u0006\u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020!2\u0006\u0010:\u001a\u00020\u00102\u0006\u0010;\u001a\u00020\u0014H\u0002J\u0016\u0010<\u001a\b\u0012\u0004\u0012\u00020*042\u0006\u0010#\u001a\u00020 H\u0002J\u0012\u0010=\u001a\u0004\u0018\u00010\u00102\u0006\u0010)\u001a\u00020*H\u0002J.\u0010>\u001a\u0004\u0018\u00010\u00102\u0006\u0010$\u001a\u00020!2\b\u0010?\u001a\u0004\u0018\u00010\u00102\b\u0010@\u001a\u0004\u0018\u00010A2\u0006\u0010B\u001a\u00020\u0010H\u0002J,\u0010C\u001a\u00020\u00102\u0006\u0010$\u001a\u00020!2\b\u0010@\u001a\u0004\u0018\u00010A2\u0006\u0010D\u001a\u00020\u00102\b\b\u0002\u0010E\u001a\u00020\u0014H\u0002J \u0010F\u001a\u00020G2\u0006\u0010#\u001a\u00020 2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010H\u001a\u00020IH\u0002J \u0010J\u001a\u00020G2\u0006\u0010#\u001a\u00020 2\u0006\u0010:\u001a\u00020\u00102\u0006\u0010H\u001a\u00020IH\u0002J\u0010\u0010K\u001a\u00020L2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J0\u0010M\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010+\u001a\u00020\u00102\u0016\u0010\u001e\u001a\u0012\u0012\b\u0012\u00060OR\u00020P\u0012\u0004\u0012\u00020Q0NH\u0002J\u0010\u0010R\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010S\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010T\u001a\u00020Q2\u0006\u0010U\u001a\u00020\u0010H\u0002J\n\u0010Z\u001a\u0004\u0018\u00010PH\u0002J\u0010\u0010[\u001a\u00020\u00142\u0006\u0010\\\u001a\u00020PH\u0002J\u0012\u0010]\u001a\u00020\u00142\b\u0010$\u001a\u0004\u0018\u00010PH\u0002J\u000e\u0010^\u001a\b\u0012\u0004\u0012\u00020P04H\u0002J\u0012\u0010_\u001a\u00020Q2\b\u0010`\u001a\u0004\u0018\u00010\u0010H\u0002J\u0012\u0010a\u001a\u00020Q2\b\u0010b\u001a\u0004\u0018\u00010\u0010H\u0002J\u0010\u0010c\u001a\u00020\u00142\u0006\u0010b\u001a\u00020\u0010H\u0002J\u0010\u0010d\u001a\u00020Q2\u0006\u0010e\u001a\u000206H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010V\u001a\u00020WX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010X\u001a\u0004\u0018\u00010YX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006j"}, d2 = {"Lcom/jarvis/tools/MusicTool;", "Lcom/jarvis/core/common/Tool;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "play", "requestId", "", SearchIntents.EXTRA_QUERY, "scope", "hasInternet", "", "playOffline", "playSoundCloud", "soundCloudPlaying", "playViaKion", "playViaActiveSessionSearch", "playViaSystemSearchIntent", "withBrowser", NotificationCompat.CATEGORY_SERVICE, "Landroid/content/ComponentName;", "block", "Lkotlin/Function2;", "Landroid/support/v4/media/MediaBrowserCompat;", "Landroid/support/v4/media/session/MediaControllerCompat;", "playTrackQuery", "browser", "ctrl", "playSearchAs", "prefer", "Lcom/jarvis/tools/MusicTool$Prefer;", "playResolved", "item", "Landroid/support/v4/media/MediaBrowserCompat$MediaItem;", "label", "originalQuery", "preferSearch", "playFromSearchQuery", "searchQuery", "expectedTitle", "pickBestItem", "Lcom/jarvis/core/nlp/MusicQueryMatcher$Scored;", AndroidReminderBook.KEY_ITEMS, "", "minScore", "", "itemLabel", "playDownloaded", "playChildren", "parentId", "preferShuffle", "loadDownloadedTracks", "toPlayableTrackId", "playMediaId", "mediaId", "description", "Landroid/support/v4/media/MediaDescriptionCompat;", "fallbackName", "labelFrom", "fallback", "preferDescription", "search", "Lcom/jarvis/tools/MusicTool$BrowseResult;", "timeoutMs", "", "loadChildren", "searchExtras", "Landroid/os/Bundle;", NotificationCompat.CATEGORY_TRANSPORT, "Lkotlin/Function1;", "Landroid/media/session/MediaController$TransportControls;", "Landroid/media/session/MediaController;", "", "confirmPlaying", "status", "cacheStatus", VoiceService.EXTRA_TEXT, "statusCacheLock", "", "statusCache", "Lcom/jarvis/tools/MusicTool$StatusCache;", "activeController", "isFrozen", "controller", "hasLivePlayer", "allControllers", "pauseOtherPlayers", "except", "rememberPackage", "packageName", "isInstalled", "dispatchMediaKey", "keyCode", "Prefer", "BrowseResult", "StatusCache", "Companion", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MusicTool implements Tool {
    private static final long BACKGROUND_PLAY_MS = 6000;
    private static final long BROWSE_TIMEOUT_MS = 5000;
    private static final long FROZEN_SESSION_MS = 15000;
    private static final String PKG_SOUNDCLOUD = "com.soundcloud.android";
    private static final String PKG_YANDEX = "ru.yandex.music";
    private static final String PREFS = "jarvis_prefs";
    private static final String PREF_PLAYER = "music_player_package";
    private static final long RESUME_CONFIRM_MS = 2500;
    private static final long RESUME_POLL_MS = 200;
    private static final String SOUNDCLOUD_LIKES = "likes";
    private static final long STATUS_CACHE_TTL_MS = 12000;
    private static final String SVC_KION = "ru.mts.music.common.service.player.MusicService";
    private static final String TAG = "Jarvis.Music";
    private final Context context;
    private final ToolDefinition definition;
    private volatile StatusCache statusCache;
    private final Object statusCacheLock;
    private static final Regex UNSPEAKABLE = new Regex("[^\\p{L}\\p{N}\\s,.'&()!?-]");
    public static final String PKG_KION = "ru.mts.music.android";
    private static final String PKG_YT_MUSIC = "com.google.android.apps.youtube.music";
    private static final String PKG_SPOTIFY = "com.spotify.music";
    private static final Set<String> PLAYERS = SetsKt.setOf((Object[]) new String[]{"com.soundcloud.android", "com.ymliberty.app", "ru.yandex.music", PKG_KION, PKG_YT_MUSIC, PKG_SPOTIFY, "com.metrolist.music", "com.uma.musicvk"});

    /* JADX INFO: compiled from: MusicTool.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/jarvis/tools/MusicTool$Prefer;", "", "<init>", "(Ljava/lang/String;I)V", "TRACK", "ARTIST", "ALBUM", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private enum Prefer {
        TRACK,
        ARTIST,
        ALBUM;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<Prefer> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: compiled from: MusicTool.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[PlayerResult.values().length];
            try {
                iArr[PlayerResult.PLAYING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[PlayerResult.LOCKED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[PlayerResult.NO_ACCESS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[PlayerResult.FAILED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[Prefer.values().length];
            try {
                iArr2[Prefer.ARTIST.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr2[Prefer.ALBUM.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr2[Prefer.TRACK.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public MusicTool(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.definition = new ToolDefinition("music", "Музыка", "Включить трек/артиста/альбом (Кион или любой плеер), пауза, дальше, статус", CollectionsKt.listOf((Object[]) new ToolParameter[]{new ToolParameter("action", "play, pause, resume, next, previous или status", ParameterType.ENUM, false, CollectionsKt.listOf((Object[]) new String[]{"play", "pause", "resume", "next", "previous", "forward", "rewind", "status"})), new ToolParameter(SearchIntents.EXTRA_QUERY, "Трек, артист или альбом", ParameterType.STRING, false, null, 16, null), new ToolParameter("scope", "track, artist, album, favorites, downloaded, mood, wave", ParameterType.ENUM, false, CollectionsKt.listOf((Object[]) new String[]{"track", "artist", "album", "favorites", "downloaded"}))}), RiskLevel.SAFE, null, OnlineRequirement.LOCAL_PREFERRED, null, 160, null);
        this.statusCacheLock = new Object();
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:10:0x0040  */
    /* JADX WARN: Code duplicated, block: B:11:0x0042  */
    /* JADX WARN: Code duplicated, block: B:8:0x0036  */
    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) {
        String action;
        String string;
        String string2;
        String string3;
        Intrinsics.checkNotNullParameter(call, "call");
        Object obj = call.getArguments().get("action");
        if (obj != null && (string3 = obj.toString()) != null) {
            action = string3.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(action, "toLowerCase(...)");
            if (action == null) {
                if (call.getArguments().get(SearchIntents.EXTRA_QUERY) != null) {
                    action = "play";
                } else {
                    action = "status";
                }
            }
        } else if (call.getArguments().get(SearchIntents.EXTRA_QUERY) != null) {
            action = "play";
        } else {
            action = "status";
        }
        Object obj2 = call.getArguments().get(SearchIntents.EXTRA_QUERY);
        String lowerCase = null;
        String query = (obj2 == null || (string2 = obj2.toString()) == null) ? null : StringsKt.trim((CharSequence) string2).toString();
        if (query == null) {
            query = "";
        }
        Object obj3 = call.getArguments().get("scope");
        if (obj3 != null && (string = obj3.toString()) != null) {
            lowerCase = string.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        }
        String str = lowerCase != null ? lowerCase : "";
        if (StringsKt.isBlank(str)) {
            str = StringsKt.isBlank(query) ? "favorites" : "track";
        }
        String scope = str;
        try {
            switch (action.hashCode()) {
                case -1273775369:
                    if (!action.equals("previous")) {
                    }
                    return transport(call.getRequestId(), "previous", new Function1() { // from class: com.jarvis.tools.MusicTool$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj4) {
                            return MusicTool.execute$lambda$4((MediaController.TransportControls) obj4);
                        }
                    });
                case -934426579:
                    if (!action.equals("resume")) {
                    }
                    return transport(call.getRequestId(), "resume", new Function1() { // from class: com.jarvis.tools.MusicTool$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj4) {
                            return MusicTool.execute$lambda$2((MediaController.TransportControls) obj4);
                        }
                    });
                case -934318917:
                    return !action.equals("rewind") ? new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "Скажи: включи [трек/артиста/альбом], избранное, скачанное, пауза, продолжить, дальше.", null, 0L, 48, null) : transport(call.getRequestId(), "rewind", new Function1() { // from class: com.jarvis.tools.MusicTool$$ExternalSyntheticLambda7
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj4) {
                            return MusicTool.execute$lambda$6((MediaController.TransportControls) obj4);
                        }
                    });
                case -892481550:
                    if (action.equals("status")) {
                        return status(call.getRequestId());
                    }
                case -677145915:
                    if (action.equals("forward")) {
                        return transport(call.getRequestId(), "forward", new Function1() { // from class: com.jarvis.tools.MusicTool$$ExternalSyntheticLambda6
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj4) {
                                return MusicTool.execute$lambda$5((MediaController.TransportControls) obj4);
                            }
                        });
                    }
                case -280604931:
                    if (!action.equals("unpause")) {
                    }
                    return transport(call.getRequestId(), "resume", new Function1() { // from class: com.jarvis.tools.MusicTool$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj4) {
                            return MusicTool.execute$lambda$2((MediaController.TransportControls) obj4);
                        }
                    });
                case 3377907:
                    if (action.equals("next")) {
                        return transport(call.getRequestId(), "next", new Function1() { // from class: com.jarvis.tools.MusicTool$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj4) {
                                return MusicTool.execute$lambda$3((MediaController.TransportControls) obj4);
                            }
                        });
                    }
                case 3443508:
                    if (action.equals("play")) {
                        return play(call.getRequestId(), query, scope);
                    }
                case 3449395:
                    if (!action.equals("prev")) {
                    }
                    return transport(call.getRequestId(), "previous", new Function1() { // from class: com.jarvis.tools.MusicTool$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj4) {
                            return MusicTool.execute$lambda$4((MediaController.TransportControls) obj4);
                        }
                    });
                case 106440182:
                    if (action.equals("pause")) {
                        return transport(call.getRequestId(), "pause", new Function1() { // from class: com.jarvis.tools.MusicTool$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj4) {
                                return MusicTool.execute$lambda$1((MediaController.TransportControls) obj4);
                            }
                        });
                    }
                default:
            }
        } catch (Throwable t) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.FAILED, t.getMessage(), null, 0L, 48, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit execute$lambda$1(MediaController.TransportControls it) {
        Intrinsics.checkNotNullParameter(it, "it");
        it.pause();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit execute$lambda$2(MediaController.TransportControls it) {
        Intrinsics.checkNotNullParameter(it, "it");
        it.play();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit execute$lambda$3(MediaController.TransportControls it) {
        Intrinsics.checkNotNullParameter(it, "it");
        it.skipToNext();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit execute$lambda$4(MediaController.TransportControls it) {
        Intrinsics.checkNotNullParameter(it, "it");
        it.skipToPrevious();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit execute$lambda$5(MediaController.TransportControls it) {
        Intrinsics.checkNotNullParameter(it, "it");
        it.fastForward();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit execute$lambda$6(MediaController.TransportControls it) {
        Intrinsics.checkNotNullParameter(it, "it");
        it.rewind();
        return Unit.INSTANCE;
    }

    private final ToolResult play(String requestId, String query, String scope) throws InterruptedException {
        String str;
        String request;
        if (!hasInternet() && isInstalled("ru.yandex.music")) {
            return playOffline(requestId);
        }
        if (!isInstalled("com.soundcloud.android") || Intrinsics.areEqual(scope, "downloaded")) {
            if (isInstalled(PKG_KION)) {
                String kion = playViaKion(query, scope);
                if (kion == null || StringsKt.startsWith$default(kion, "ERR:", false, 2, (Object) null)) {
                    Log.w(TAG, "Kion play weak: " + kion + " — trying generic");
                } else {
                    rememberPackage(PKG_KION);
                    return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.SUCCESS, "Ок: " + kion + ".", MapsKt.mapOf(TuplesKt.to(SearchIntents.EXTRA_QUERY, query), TuplesKt.to("scope", scope), TuplesKt.to("package", PKG_KION), TuplesKt.to("track", kion)), 0L, 32, null);
                }
            }
            if (SetsKt.setOf((Object[]) new String[]{"favorites", "downloaded"}).contains(scope) && StringsKt.isBlank(query)) {
                String id = getDefinition().getId();
                ToolResultStatus toolResultStatus = ToolResultStatus.FAILED;
                if (isInstalled(PKG_KION)) {
                    str = "Не удалось открыть " + scope + " в Кионе. Открой приложение один раз и повтори.";
                } else {
                    str = "Для избранного/скачанного нужен Кион. Или скажи название трека.";
                }
                return new ToolResult(requestId, id, toolResultStatus, str, null, 0L, 48, null);
            }
            String str2 = query;
            if (StringsKt.isBlank(str2)) {
                str2 = "music";
            }
            String searchQ = str2;
            String sessionHit = playViaActiveSessionSearch(searchQ);
            if (sessionHit != null) {
                return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.SUCCESS, "Включаю: " + sessionHit + ".", MapsKt.mapOf(TuplesKt.to(SearchIntents.EXTRA_QUERY, query), TuplesKt.to("scope", scope), TuplesKt.to("track", sessionHit), TuplesKt.to("via", PhoneStateReader.FAKE_SESSION)), 0L, 32, null);
            }
            boolean intentHit = playViaSystemSearchIntent(searchQ);
            if (intentHit) {
                return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.SUCCESS, "Открыл поиск «" + searchQ + "» в плеере, сэр. Сам трек включить не удалось.", MapsKt.mapOf(TuplesKt.to(SearchIntents.EXTRA_QUERY, query), TuplesKt.to("scope", scope), TuplesKt.to("via", "intent")), 0L, 32, null);
            }
            return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.FAILED, "Не нашёл плеер. Установи Кион/YouTube Music/Spotify или открой плеер один раз.", null, 0L, 48, null);
        }
        String str3 = "";
        if (Intrinsics.areEqual(scope, "mood")) {
            request = query + " music";
        } else {
            request = StringsKt.isBlank(query) ? "" : query;
        }
        pauseOtherPlayers("com.soundcloud.android");
        if (StringsKt.isBlank(request)) {
            String str4 = Intrinsics.areEqual(scope, "favorites") ? null : "wave";
            str3 = str4 == null ? scope : str4;
        }
        return playSoundCloud(requestId, request, str3);
    }

    private final boolean hasInternet() {
        ConnectivityManager manager = (ConnectivityManager) this.context.getSystemService(ConnectivityManager.class);
        if (manager == null) {
            return true;
        }
        NetworkCapabilities caps = manager.getNetworkCapabilities(manager.getActiveNetwork());
        if (caps == null) {
            return false;
        }
        return caps.hasCapability(16);
    }

    private static final MediaController playOffline$session(MusicTool this$0) {
        Object element$iv;
        Iterable $this$firstOrNull$iv = this$0.allControllers();
        Iterator it = $this$firstOrNull$iv.iterator();
        while (it.hasNext()) {
            element$iv = it.next();
            MediaController it2 = (MediaController) element$iv;
            if (Intrinsics.areEqual(it2.getPackageName(), "ru.yandex.music")) {
                return (MediaController) element$iv;
            }
        }
        element$iv = null;
        return (MediaController) element$iv;
    }

    private final ToolResult playOffline(String requestId) throws InterruptedException {
        PlayerResult result;
        MediaMetadata metadata;
        String reply;
        MediaController.TransportControls transportControls;
        PlaybackState playbackState;
        pauseOtherPlayers("ru.yandex.music");
        Function0 playing = new Function0() { // from class: com.jarvis.tools.MusicTool$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(MusicTool.playOffline$lambda$1(this.f$0));
            }
        };
        MediaController mediaControllerPlayOffline$session = playOffline$session(this);
        boolean z = false;
        if (mediaControllerPlayOffline$session != null && (playbackState = mediaControllerPlayOffline$session.getPlaybackState()) != null && playbackState.getState() == 2) {
            z = true;
        }
        if (z) {
            MediaController mediaControllerPlayOffline$session2 = playOffline$session(this);
            if (mediaControllerPlayOffline$session2 != null && (transportControls = mediaControllerPlayOffline$session2.getTransportControls()) != null) {
                transportControls.play();
            }
            Thread.sleep(RESUME_CONFIRM_MS);
            result = ((Boolean) playing.invoke()).booleanValue() ? PlayerResult.PLAYING : new YandexOfflineDriver(this.context, playing).play();
        } else {
            result = new YandexOfflineDriver(this.context, playing).play();
        }
        switch (WhenMappings.$EnumSwitchMapping$0[result.ordinal()]) {
            case 1:
                rememberPackage("ru.yandex.music");
                MediaController mediaControllerPlayOffline$session3 = playOffline$session(this);
                String title = (mediaControllerPlayOffline$session3 == null || (metadata = mediaControllerPlayOffline$session3.getMetadata()) == null) ? null : metadata.getString(MediaMetadataCompat.METADATA_KEY_TITLE);
                if (title == null) {
                    title = "";
                }
                return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.SUCCESS, "Интернета нет, сэр, включил Яндекс Музыку. Играет: " + UNSPEAKABLE.replace(title, "") + ".", null, 0L, 48, null);
            case 2:
                reply = "Интернета нет, сэр. Разблокируйте телефон, и я включу Яндекс Музыку.";
                break;
            case 3:
                reply = "Для Яндекс Музыки нужна служба спец. возможностей Jarvis, сэр.";
                break;
            case 4:
                reply = "Интернета нет, сэр, а Яндекс Музыка не заиграла. Откройте скачанное в ней.";
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.FAILED, reply, null, 0L, 48, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean playOffline$lambda$1(MusicTool this$0) {
        PlaybackState playbackState;
        MediaController mediaControllerPlayOffline$session = playOffline$session(this$0);
        return (mediaControllerPlayOffline$session == null || (playbackState = mediaControllerPlayOffline$session.getPlaybackState()) == null || playbackState.getState() != 3) ? false : true;
    }

    static /* synthetic */ ToolResult playSoundCloud$default(MusicTool musicTool, String str, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = "";
        }
        return musicTool.playSoundCloud(str, str2, str3);
    }

    private static final MediaController playSoundCloud$session(MusicTool this$0) {
        Object element$iv;
        Iterable $this$firstOrNull$iv = this$0.allControllers();
        Iterator it = $this$firstOrNull$iv.iterator();
        while (it.hasNext()) {
            element$iv = it.next();
            MediaController it2 = (MediaController) element$iv;
            if (Intrinsics.areEqual(it2.getPackageName(), "com.soundcloud.android")) {
                return (MediaController) element$iv;
            }
        }
        element$iv = null;
        return (MediaController) element$iv;
    }

    private static final String playSoundCloud$title(MusicTool this$0) {
        MediaMetadata metadata;
        MediaController mediaControllerPlaySoundCloud$session = playSoundCloud$session(this$0);
        if (mediaControllerPlaySoundCloud$session == null || (metadata = mediaControllerPlaySoundCloud$session.getMetadata()) == null) {
            return null;
        }
        return metadata.getString(MediaMetadataCompat.METADATA_KEY_TITLE);
    }

    private final ToolResult playSoundCloud(String requestId, final String query, final String scope) throws InterruptedException {
        PlaybackState playbackState;
        final String before = playSoundCloud$title(this);
        MediaController mediaControllerPlaySoundCloud$session = playSoundCloud$session(this);
        boolean z = false;
        if (mediaControllerPlaySoundCloud$session != null && (playbackState = mediaControllerPlaySoundCloud$session.getPlaybackState()) != null && playbackState.getState() == 3) {
            z = true;
        }
        final boolean wasPlaying = z;
        final Function0 started = new Function0() { // from class: com.jarvis.tools.MusicTool$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(MusicTool.playSoundCloud$lambda$1(wasPlaying, before, this));
            }
        };
        String background = withBrowser(new ComponentName("com.soundcloud.android", SoundCloudDriver.SERVICE), new Function2() { // from class: com.jarvis.tools.MusicTool$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return MusicTool.playSoundCloud$lambda$2(this.f$0, started, query, scope, (MediaBrowserCompat) obj, (MediaControllerCompat) obj2);
            }
        });
        Log.i(TAG, "SoundCloud в фоне: " + background);
        if (Intrinsics.areEqual(background, "ok")) {
            return soundCloudPlaying(requestId);
        }
        if (StringsKt.isBlank(query)) {
            return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.FAILED, "Скажите, что включить, сэр.", null, 0L, 48, null);
        }
        switch (WhenMappings.$EnumSwitchMapping$0[new SoundCloudDriver(this.context, started).playSearch(query).ordinal()]) {
            case 1:
                return soundCloudPlaying(requestId);
            case 2:
                return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.FAILED, "Разблокируйте телефон, сэр, иначе я не смогу открыть плеер.", null, 0L, 48, null);
            case 3:
                return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.FAILED, "Для плеера нужна служба спец. возможностей Jarvis, сэр.", null, 0L, 48, null);
            case 4:
                return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.FAILED, "Не получилось включить, сэр. Плеер не отозвался.", null, 0L, 48, null);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean playSoundCloud$lambda$1(boolean $wasPlaying, String $before, MusicTool this$0) {
        PlaybackState playbackState;
        MediaController mediaControllerPlaySoundCloud$session = playSoundCloud$session(this$0);
        return (mediaControllerPlaySoundCloud$session != null && (playbackState = mediaControllerPlaySoundCloud$session.getPlaybackState()) != null && playbackState.getState() == 3) && !($wasPlaying && Intrinsics.areEqual(playSoundCloud$title(this$0), $before));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String playSoundCloud$lambda$2(MusicTool this$0, final Function0 $started, String $query, String $scope, MediaBrowserCompat browser, MediaControllerCompat ctrl) throws InterruptedException {
        Unit unit;
        Unit unit2;
        String it;
        Intrinsics.checkNotNullParameter(browser, "browser");
        Intrinsics.checkNotNullParameter(ctrl, "ctrl");
        Function1 waitPlaying = new Function1() { // from class: com.jarvis.tools.MusicTool$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(MusicTool.playSoundCloud$lambda$2$0($started, ((Long) obj).longValue()));
            }
        };
        try {
            Result.Companion companion = Result.INSTANCE;
            if (!StringsKt.isBlank($query)) {
                ctrl.getTransportControls().playFromSearch($query, this$0.searchExtras($query));
                unit2 = Unit.INSTANCE;
            } else {
                if (StringsKt.isBlank($scope)) {
                    ctrl.getTransportControls().play();
                    if (((Boolean) waitPlaying.invoke(Long.valueOf(RESUME_CONFIRM_MS))).booleanValue()) {
                        return "ok";
                    }
                }
                Iterable $this$filter$iv = this$0.loadChildren(browser, SOUNDCLOUD_LIKES, 5000L).getItems();
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv : $this$filter$iv) {
                    if (((MediaBrowserCompat.MediaItem) element$iv$iv).isPlayable()) {
                        destination$iv$iv.add(element$iv$iv);
                    }
                }
                List likes = (List) destination$iv$iv;
                MediaBrowserCompat.MediaItem pick = (MediaBrowserCompat.MediaItem) (Intrinsics.areEqual($scope, "favorites") ? CollectionsKt.firstOrNull(likes) : CollectionsKt.randomOrNull(likes, Random.INSTANCE));
                if (pick == null || (it = pick.getMediaId()) == null) {
                    unit = null;
                } else {
                    ctrl.getTransportControls().playFromMediaId(it, Bundle.EMPTY);
                    unit = Unit.INSTANCE;
                }
                unit2 = unit;
            }
            Result.m392constructorimpl(unit2);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        long deadline = SystemClock.elapsedRealtime() + BACKGROUND_PLAY_MS;
        while (SystemClock.elapsedRealtime() < deadline) {
            if (((Boolean) $started.invoke()).booleanValue()) {
                return "ok";
            }
            Thread.sleep(RESUME_POLL_MS);
        }
        return "ERR:тишина";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean playSoundCloud$lambda$2$0(Function0 $started, long timeoutMs) throws InterruptedException {
        long until = SystemClock.elapsedRealtime() + timeoutMs;
        while (SystemClock.elapsedRealtime() < until && !((Boolean) $started.invoke()).booleanValue()) {
            Thread.sleep(RESUME_POLL_MS);
        }
        return ((Boolean) $started.invoke()).booleanValue();
    }

    private final ToolResult soundCloudPlaying(String requestId) {
        Object element$iv;
        MediaController it;
        rememberPackage("com.soundcloud.android");
        Iterable $this$firstOrNull$iv = allControllers();
        Iterator it2 = $this$firstOrNull$iv.iterator();
        do {
            if (!it2.hasNext()) {
                element$iv = null;
                break;
            }
            element$iv = it2.next();
            it = (MediaController) element$iv;
        } while (!Intrinsics.areEqual(it.getPackageName(), "com.soundcloud.android"));
        MediaController mediaController = (MediaController) element$iv;
        MediaMetadata meta = mediaController != null ? mediaController.getMetadata() : null;
        String title = meta != null ? meta.getString(MediaMetadataCompat.METADATA_KEY_TITLE) : null;
        if (title == null) {
            title = "";
        }
        String string = meta != null ? meta.getString(MediaMetadataCompat.METADATA_KEY_ARTIST) : null;
        if (string == null) {
            string = "";
        }
        String artist = string;
        Iterable $this$filter$iv = CollectionsKt.listOf((Object[]) new String[]{artist, title});
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it3 = (String) element$iv$iv;
            if (!StringsKt.isBlank(it3)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        String track = StringsKt.trim(UNSPEAKABLE.replace(CollectionsKt.joinToString$default((List) destination$iv$iv, ", ", null, null, 0, null, null, 62, null), ""), ' ', ',');
        return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.SUCCESS, StringsKt.isBlank(track) ? "Играю." : "Играет: " + track + ".", MapsKt.mapOf(TuplesKt.to("package", "com.soundcloud.android"), TuplesKt.to("track", track)), 0L, 32, null);
    }

    private final String playViaKion(final String query, final String scope) {
        pauseOtherPlayers(PKG_KION);
        return withBrowser$default(this, null, new Function2() { // from class: com.jarvis.tools.MusicTool$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return MusicTool.playViaKion$lambda$0(scope, this, query, (MediaBrowserCompat) obj, (MediaControllerCompat) obj2);
            }
        }, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final String playViaKion$lambda$0(String $scope, MusicTool this$0, String $query, MediaBrowserCompat browser, MediaControllerCompat ctrl) {
        Intrinsics.checkNotNullParameter(browser, "browser");
        Intrinsics.checkNotNullParameter(ctrl, "ctrl");
        switch ($scope.hashCode()) {
            case -1785238953:
                if ($scope.equals("favorites")) {
                    return this$0.playChildren(browser, ctrl, "__TRACKS__", true);
                }
                break;
            case -1409097913:
                if ($scope.equals("artist")) {
                    return this$0.playSearchAs(browser, ctrl, $query, Prefer.ARTIST);
                }
                break;
            case 92896879:
                if ($scope.equals("album")) {
                    return this$0.playSearchAs(browser, ctrl, $query, Prefer.ALBUM);
                }
                break;
            case 2039141159:
                if ($scope.equals("downloaded")) {
                    return this$0.playDownloaded(browser, ctrl);
                }
                break;
        }
        return StringsKt.isBlank($query) ? this$0.playChildren(browser, ctrl, "__TRACKS__", true) : this$0.playTrackQuery(browser, ctrl, $query);
    }

    private final String playViaActiveSessionSearch(String query) throws InterruptedException {
        Object objM392constructorimpl;
        MediaController ctrl = activeController();
        if (ctrl == null) {
            return null;
        }
        MediaMetadata metadata = ctrl.getMetadata();
        String prev = metadata != null ? metadata.getString(MediaMetadataCompat.METADATA_KEY_TITLE) : null;
        if (prev == null) {
            prev = "";
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            MusicTool $this$playViaActiveSessionSearch_u24lambda_u240 = this;
            ctrl.getTransportControls().playFromSearch(query, $this$playViaActiveSessionSearch_u24lambda_u240.searchExtras(query));
            objM392constructorimpl = Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        Throwable it = Result.m395exceptionOrNullimpl(objM392constructorimpl);
        if (it != null) {
            Log.w(TAG, "session playFromSearch failed: " + it.getMessage());
            return null;
        }
        long deadline = System.currentTimeMillis() + ((long) AICompletionRequest.MAX_USER);
        while (System.currentTimeMillis() < deadline) {
            Thread.sleep(RESUME_POLL_MS);
            PlaybackState playbackState = ctrl.getPlaybackState();
            Integer st = playbackState != null ? Integer.valueOf(playbackState.getState()) : null;
            MediaMetadata metadata2 = ctrl.getMetadata();
            String title = metadata2 != null ? metadata2.getString(MediaMetadataCompat.METADATA_KEY_TITLE) : null;
            if (title == null) {
                title = "";
            }
            if ((st != null && st.intValue() == 3) || (!StringsKt.isBlank(title) && !StringsKt.equals(title, prev, true))) {
                rememberPackage(ctrl.getPackageName());
                MediaMetadata metadata3 = ctrl.getMetadata();
                String string = metadata3 != null ? metadata3.getString(MediaMetadataCompat.METADATA_KEY_ARTIST) : null;
                String artist = string != null ? string : "";
                if (!StringsKt.isBlank(artist) && !StringsKt.isBlank(title)) {
                    return artist + " — " + title;
                }
                String str = title;
                if (StringsKt.isBlank(str)) {
                    str = query;
                }
                return str;
            }
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0022  */
    /* JADX WARN: Code duplicated, block: B:13:0x0048  */
    /* JADX WARN: Code duplicated, block: B:45:0x0057 A[SYNTHETIC] */
    private final boolean playViaSystemSearchIntent(String query) {
        Iterator it;
        Object element$iv;
        String it2;
        Object objM392constructorimpl;
        String preferred = this.context.getSharedPreferences("jarvis_prefs", 0).getString(PREF_PLAYER, null);
        if (preferred == null) {
            Iterable $this$firstOrNull$iv = CollectionsKt.listOf((Object[]) new String[]{PKG_KION, PKG_YT_MUSIC, PKG_SPOTIFY, "ru.yandex.music"});
            it = $this$firstOrNull$iv.iterator();
            do {
                if (it.hasNext()) {
                    element$iv = null;
                    break;
                }
                element$iv = it.next();
                it2 = (String) element$iv;
            } while (!isInstalled(it2));
            preferred = (String) element$iv;
        } else {
            if (!isInstalled(preferred)) {
                preferred = null;
            }
            if (preferred == null) {
                Iterable $this$firstOrNull$iv2 = CollectionsKt.listOf((Object[]) new String[]{PKG_KION, PKG_YT_MUSIC, PKG_SPOTIFY, "ru.yandex.music"});
                it = $this$firstOrNull$iv2.iterator();
                do {
                    if (it.hasNext()) {
                        element$iv = null;
                        break;
                    }
                    element$iv = it.next();
                    it2 = (String) element$iv;
                } while (!isInstalled(it2));
                preferred = (String) element$iv;
            }
        }
        List<String> attempts = CollectionsKt.distinct(CollectionsKt.listOfNotNull((Object[]) new String[]{preferred, null}));
        for (String pkg : attempts) {
            try {
                Result.Companion companion = Result.INSTANCE;
                MusicTool $this$playViaSystemSearchIntent_u24lambda_u243 = this;
                $this$playViaSystemSearchIntent_u24lambda_u243.context.startActivity(playViaSystemSearchIntent$buildIntent(query, pkg));
                objM392constructorimpl = Result.m392constructorimpl(true);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m398isFailureimpl(objM392constructorimpl)) {
                objM392constructorimpl = false;
            }
            boolean ok = ((Boolean) objM392constructorimpl).booleanValue();
            if (ok) {
                Log.i(TAG, "PLAY_FROM_SEARCH intent pkg=" + pkg + " q=" + query);
                String str = pkg;
                if (!(str == null || StringsKt.isBlank(str))) {
                    rememberPackage(pkg);
                }
                return true;
            }
        }
        return false;
    }

    private static final Intent playViaSystemSearchIntent$buildIntent(String $query, String pkg) {
        Intent $this$playViaSystemSearchIntent_u24buildIntent_u24lambda_u242 = new Intent("android.media.action.MEDIA_PLAY_FROM_SEARCH");
        $this$playViaSystemSearchIntent_u24buildIntent_u24lambda_u242.putExtra(SearchIntents.EXTRA_QUERY, $query);
        $this$playViaSystemSearchIntent_u24buildIntent_u24lambda_u242.putExtra("android.intent.extra.focus", "vnd.android.cursor.item/audio");
        $this$playViaSystemSearchIntent_u24buildIntent_u24lambda_u242.putExtra("android.intent.extra.title", $query);
        $this$playViaSystemSearchIntent_u24buildIntent_u24lambda_u242.addFlags(268435456);
        String str = pkg;
        if (!(str == null || StringsKt.isBlank(str))) {
            $this$playViaSystemSearchIntent_u24buildIntent_u24lambda_u242.setPackage(pkg);
        }
        return $this$playViaSystemSearchIntent_u24buildIntent_u24lambda_u242;
    }

    /* JADX INFO: compiled from: MusicTool.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B#\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0006HÆ\u0003J%\u0010\u000f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0006HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/jarvis/tools/MusicTool$BrowseResult;", "", AndroidReminderBook.KEY_ITEMS, "", "Landroid/support/v4/media/MediaBrowserCompat$MediaItem;", "error", "", "<init>", "(Ljava/util/List;Ljava/lang/String;)V", "getItems", "()Ljava/util/List;", "getError", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final /* data */ class BrowseResult {
        private final String error;
        private final List<MediaBrowserCompat.MediaItem> items;

        /* JADX WARN: Multi-variable type inference failed */
        public BrowseResult() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ BrowseResult copy$default(BrowseResult browseResult, List list, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                list = browseResult.items;
            }
            if ((i & 2) != 0) {
                str = browseResult.error;
            }
            return browseResult.copy(list, str);
        }

        public final List<MediaBrowserCompat.MediaItem> component1() {
            return this.items;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getError() {
            return this.error;
        }

        public final BrowseResult copy(List<? extends MediaBrowserCompat.MediaItem> items, String error) {
            Intrinsics.checkNotNullParameter(items, "items");
            return new BrowseResult(items, error);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BrowseResult)) {
                return false;
            }
            BrowseResult browseResult = (BrowseResult) other;
            return Intrinsics.areEqual(this.items, browseResult.items) && Intrinsics.areEqual(this.error, browseResult.error);
        }

        public int hashCode() {
            return (this.items.hashCode() * 31) + (this.error == null ? 0 : this.error.hashCode());
        }

        public String toString() {
            return "BrowseResult(items=" + this.items + ", error=" + this.error + ")";
        }

        /* JADX WARN: Multi-variable type inference failed */
        public BrowseResult(List<? extends MediaBrowserCompat.MediaItem> items, String error) {
            Intrinsics.checkNotNullParameter(items, "items");
            this.items = items;
            this.error = error;
        }

        public /* synthetic */ BrowseResult(List list, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? CollectionsKt.emptyList() : list, (i & 2) != 0 ? null : str);
        }

        public final List<MediaBrowserCompat.MediaItem> getItems() {
            return this.items;
        }

        public final String getError() {
            return this.error;
        }
    }

    static /* synthetic */ String withBrowser$default(MusicTool musicTool, ComponentName componentName, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            componentName = new ComponentName(PKG_KION, SVC_KION);
        }
        return musicTool.withBrowser(componentName, function2);
    }

    private final String withBrowser(final ComponentName service, final Function2<? super MediaBrowserCompat, ? super MediaControllerCompat, String> block) throws InterruptedException {
        Unit unit = null;
        final AtomicReference result = new AtomicReference(null);
        final CountDownLatch done = new CountDownLatch(1);
        final AtomicBoolean finished = new AtomicBoolean(false);
        final Handler main = new Handler(Looper.getMainLooper());
        final AtomicReference browserRef = new AtomicReference(null);
        final Bundle $this$withBrowser_u24lambda_u240 = new Bundle();
        $this$withBrowser_u24lambda_u240.putBoolean("android.media.browse.CONTENT_STYLE_SUPPORTED", true);
        $this$withBrowser_u24lambda_u240.putInt(MediaConstants.DESCRIPTION_EXTRAS_KEY_CONTENT_STYLE_BROWSABLE, 1);
        $this$withBrowser_u24lambda_u240.putInt(MediaConstants.DESCRIPTION_EXTRAS_KEY_CONTENT_STYLE_PLAYABLE, 2);
        main.post(new Runnable() { // from class: com.jarvis.tools.MusicTool$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                MusicTool.withBrowser$lambda$1(this.f$0, service, $this$withBrowser_u24lambda_u240, browserRef, finished, result, done, main, block);
            }
        });
        done.await(45L, TimeUnit.SECONDS);
        try {
            Result.Companion companion = Result.INSTANCE;
            MusicTool musicTool = this;
            MediaBrowserCompat mediaBrowserCompat = (MediaBrowserCompat) browserRef.get();
            if (mediaBrowserCompat != null) {
                mediaBrowserCompat.disconnect();
                unit = Unit.INSTANCE;
            }
            Result.m392constructorimpl(unit);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        return (String) result.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void withBrowser$finish(AtomicBoolean finished, AtomicReference<String> atomicReference, CountDownLatch done, String value) {
        if (finished.compareAndSet(false, true)) {
            atomicReference.set(value);
            done.countDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [T, android.support.v4.media.MediaBrowserCompat] */
    public static final void withBrowser$lambda$1(MusicTool this$0, ComponentName $service, Bundle $rootHints, AtomicReference $browserRef, AtomicBoolean $finished, AtomicReference $result, CountDownLatch $done, Handler $main, Function2 $block) {
        MediaBrowserCompat mediaBrowserCompat;
        Object objM392constructorimpl;
        Ref.ObjectRef browser = new Ref.ObjectRef();
        browser.element = new MediaBrowserCompat(this$0.context, $service, new MusicTool$withBrowser$1$1(browser, this$0, $finished, $result, $done, $main, $block), $rootHints);
        MediaBrowserCompat mediaBrowserCompat2 = null;
        if (browser.element == 0) {
            Intrinsics.throwUninitializedPropertyAccessException("browser");
            mediaBrowserCompat = null;
        } else {
            mediaBrowserCompat = (MediaBrowserCompat) browser.element;
        }
        $browserRef.set(mediaBrowserCompat);
        try {
            Result.Companion companion = Result.INSTANCE;
            if (browser.element == 0) {
                Intrinsics.throwUninitializedPropertyAccessException("browser");
            } else {
                mediaBrowserCompat2 = (MediaBrowserCompat) browser.element;
            }
            mediaBrowserCompat2.connect();
            objM392constructorimpl = Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        Throwable it = Result.m395exceptionOrNullimpl(objM392constructorimpl);
        if (it != null) {
            withBrowser$finish($finished, $result, $done, "ERR:" + it.getMessage());
        }
    }

    private final String playTrackQuery(MediaBrowserCompat browser, MediaControllerCompat ctrl, String query) {
        Iterable $this$filter$iv = loadChildren(browser, "__TRACKS__", 5000L).getItems();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            MediaBrowserCompat.MediaItem it = (MediaBrowserCompat.MediaItem) element$iv$iv;
            if (it.isPlayable()) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List library = (List) destination$iv$iv;
        MusicQueryMatcher.Scored<MediaBrowserCompat.MediaItem> scoredPickBestItem = pickBestItem(library, query, 55);
        if (scoredPickBestItem != null) {
            Log.i(TAG, "library hit score=" + scoredPickBestItem.getScore() + " «" + scoredPickBestItem.getLabel() + "» for «" + query + "»");
            return playMediaId(ctrl, scoredPickBestItem.getItem().getMediaId(), scoredPickBestItem.getItem().getDescription(), scoredPickBestItem.getLabel());
        }
        Iterable $this$filter$iv2 = loadDownloadedTracks(browser);
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv2 : $this$filter$iv2) {
            MediaBrowserCompat.MediaItem it2 = (MediaBrowserCompat.MediaItem) element$iv$iv2;
            if (it2.isPlayable()) {
                destination$iv$iv2.add(element$iv$iv2);
            }
        }
        List dl = (List) destination$iv$iv2;
        MusicQueryMatcher.Scored<MediaBrowserCompat.MediaItem> scoredPickBestItem2 = pickBestItem(dl, query, 55);
        if (scoredPickBestItem2 == null) {
            return playSearchAs(browser, ctrl, query, Prefer.TRACK);
        }
        Log.i(TAG, "downloaded hit score=" + scoredPickBestItem2.getScore() + " «" + scoredPickBestItem2.getLabel() + "» for «" + query + "»");
        return playMediaId(ctrl, scoredPickBestItem2.getItem().getMediaId(), scoredPickBestItem2.getItem().getDescription(), scoredPickBestItem2.getLabel());
    }

    /* JADX WARN: Code duplicated, block: B:103:0x02d7  */
    /* JADX WARN: Code duplicated, block: B:107:0x02e3  */
    /* JADX WARN: Code duplicated, block: B:167:0x023d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:170:0x02e6 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:72:0x022f  */
    /* JADX WARN: Code duplicated, block: B:76:0x023a  */
    private final String playSearchAs(MediaBrowserCompat browser, MediaControllerCompat ctrl, String query, Prefer prefer) throws InterruptedException {
        boolean z;
        MediaBrowserCompat.MediaItem item;
        MediaBrowserCompat.MediaItem item2;
        Iterable $this$filter$iv;
        int $i$f$filter;
        Iterable $this$filterTo$iv$iv;
        boolean z2;
        boolean z3;
        boolean z4;
        MediaBrowserCompat.MediaItem item3;
        Iterable $this$filter$iv2;
        int $i$f$filter2;
        Iterable $this$filterTo$iv$iv2;
        boolean z5;
        boolean z6;
        MusicQueryMatcher.Scored<MediaBrowserCompat.MediaItem> scored;
        if (StringsKt.isBlank(query)) {
            return "ERR:Не понял, что включить.";
        }
        List<String> listQueryVariants = MusicQueryMatcher.INSTANCE.queryVariants(query);
        Log.i(TAG, "search variants=" + listQueryVariants + " prefer=" + prefer);
        LinkedHashMap pooled = new LinkedHashMap();
        String lastError = null;
        for (String v : listQueryVariants) {
            BrowseResult found = search(browser, v, 4500L);
            if (found.getError() != null) {
                lastError = found.getError();
            }
            for (MediaBrowserCompat.MediaItem item4 : found.getItems()) {
                String mediaId = item4.getMediaId();
                if (mediaId == null) {
                    MediaDescriptionCompat description = item4.getDescription();
                    CharSequence title = description != null ? description.getTitle() : null;
                    MediaDescriptionCompat description2 = item4.getDescription();
                    mediaId = ((Object) title) + "|" + ((Object) (description2 != null ? description2.getSubtitle() : null));
                }
                String key = mediaId;
                pooled.putIfAbsent(key, item4);
            }
        }
        if (pooled.isEmpty()) {
            return "ERR:" + (lastError == null ? "Ничего не нашёл по «" + query + "»." : lastError);
        }
        Collection collectionValues = pooled.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
        List items = CollectionsKt.toList(collectionValues);
        switch (WhenMappings.$EnumSwitchMapping$1[prefer.ordinal()]) {
            case 1:
                List $this$filter$iv3 = items;
                int $i$f$filter3 = 0;
                Collection destination$iv$iv = new ArrayList();
                Iterable $this$filterTo$iv$iv3 = $this$filter$iv3;
                for (Object element$iv$iv : $this$filterTo$iv$iv3) {
                    MediaBrowserCompat.MediaItem it = (MediaBrowserCompat.MediaItem) element$iv$iv;
                    if (it.isBrowsable()) {
                        $this$filter$iv = $this$filter$iv3;
                        $i$f$filter = $i$f$filter3;
                        $this$filterTo$iv$iv = $this$filterTo$iv$iv3;
                        z2 = true;
                    } else {
                        String mediaId2 = it.getMediaId();
                        if (mediaId2 != null) {
                            $this$filter$iv = $this$filter$iv3;
                            $i$f$filter = $i$f$filter3;
                            $this$filterTo$iv$iv = $this$filterTo$iv$iv3;
                            z2 = true;
                            z4 = StringsKt.contains((CharSequence) mediaId2, (CharSequence) "ARTIST", true);
                            if (z4) {
                                z3 = false;
                            }
                            if (z3) {
                                destination$iv$iv.add(element$iv$iv);
                            }
                            $this$filterTo$iv$iv3 = $this$filterTo$iv$iv;
                            $this$filter$iv3 = $this$filter$iv;
                            $i$f$filter3 = $i$f$filter;
                        } else {
                            $this$filter$iv = $this$filter$iv3;
                            $i$f$filter = $i$f$filter3;
                            $this$filterTo$iv$iv = $this$filterTo$iv$iv3;
                            z2 = true;
                        }
                        if (z4) {
                            z3 = false;
                        }
                        if (z3) {
                            destination$iv$iv.add(element$iv$iv);
                        }
                        $this$filterTo$iv$iv3 = $this$filterTo$iv$iv;
                        $this$filter$iv3 = $this$filter$iv;
                        $i$f$filter3 = $i$f$filter;
                    }
                    z3 = z2;
                    if (z3) {
                        destination$iv$iv.add(element$iv$iv);
                    }
                    $this$filterTo$iv$iv3 = $this$filterTo$iv$iv;
                    $this$filter$iv3 = $this$filter$iv;
                    $i$f$filter3 = $i$f$filter;
                }
                z = true;
                List artists = (List) destination$iv$iv;
                List list = artists;
                if (list.isEmpty()) {
                    list = items;
                }
                MusicQueryMatcher.Scored<MediaBrowserCompat.MediaItem> scoredPickBestItem = pickBestItem(list, query, 55);
                if ((scoredPickBestItem == null || (item = scoredPickBestItem.getItem()) == null) && (item = (MediaBrowserCompat.MediaItem) CollectionsKt.firstOrNull(artists)) == null) {
                    Object objFirst = CollectionsKt.first((List<? extends Object>) items);
                    Intrinsics.checkNotNullExpressionValue(objFirst, "first(...)");
                    item2 = (MediaBrowserCompat.MediaItem) objFirst;
                } else {
                    item2 = item;
                }
                break;
            case 2:
                List $this$filter$iv4 = items;
                int $i$f$filter4 = 0;
                Collection destination$iv$iv2 = new ArrayList();
                Iterable $this$filterTo$iv$iv4 = $this$filter$iv4;
                for (Object element$iv$iv2 : $this$filterTo$iv$iv4) {
                    MediaBrowserCompat.MediaItem it2 = (MediaBrowserCompat.MediaItem) element$iv$iv2;
                    if (it2.isBrowsable()) {
                        $this$filter$iv2 = $this$filter$iv4;
                        $i$f$filter2 = $i$f$filter4;
                        $this$filterTo$iv$iv2 = $this$filterTo$iv$iv4;
                    } else {
                        String mediaId3 = it2.getMediaId();
                        if (mediaId3 != null) {
                            $this$filter$iv2 = $this$filter$iv4;
                            $i$f$filter2 = $i$f$filter4;
                            $this$filterTo$iv$iv2 = $this$filterTo$iv$iv4;
                            z6 = StringsKt.contains((CharSequence) mediaId3, (CharSequence) "ALBUM", true);
                            if (z6) {
                                z5 = false;
                            }
                            if (z5) {
                                destination$iv$iv2.add(element$iv$iv2);
                            }
                            $this$filterTo$iv$iv4 = $this$filterTo$iv$iv2;
                            $this$filter$iv4 = $this$filter$iv2;
                            $i$f$filter4 = $i$f$filter2;
                        } else {
                            $this$filter$iv2 = $this$filter$iv4;
                            $i$f$filter2 = $i$f$filter4;
                            $this$filterTo$iv$iv2 = $this$filterTo$iv$iv4;
                        }
                        if (z6) {
                            z5 = false;
                        }
                        if (z5) {
                            destination$iv$iv2.add(element$iv$iv2);
                        }
                        $this$filterTo$iv$iv4 = $this$filterTo$iv$iv2;
                        $this$filter$iv4 = $this$filter$iv2;
                        $i$f$filter4 = $i$f$filter2;
                    }
                    z5 = true;
                    if (z5) {
                        destination$iv$iv2.add(element$iv$iv2);
                    }
                    $this$filterTo$iv$iv4 = $this$filterTo$iv$iv2;
                    $this$filter$iv4 = $this$filter$iv2;
                    $i$f$filter4 = $i$f$filter2;
                }
                List albums = (List) destination$iv$iv2;
                List list2 = albums;
                if (list2.isEmpty()) {
                    list2 = items;
                }
                MusicQueryMatcher.Scored<MediaBrowserCompat.MediaItem> scoredPickBestItem2 = pickBestItem(list2, query, 55);
                if ((scoredPickBestItem2 == null || (item3 = scoredPickBestItem2.getItem()) == null) && (item3 = (MediaBrowserCompat.MediaItem) CollectionsKt.firstOrNull(albums)) == null) {
                    Object objFirst2 = CollectionsKt.first((List<? extends Object>) items);
                    Intrinsics.checkNotNullExpressionValue(objFirst2, "first(...)");
                    item2 = (MediaBrowserCompat.MediaItem) objFirst2;
                    z = true;
                } else {
                    item2 = item3;
                    z = true;
                }
                break;
            case 3:
                Collection destination$iv$iv3 = new ArrayList();
                for (Object element$iv$iv3 : items) {
                    if (((MediaBrowserCompat.MediaItem) element$iv$iv3).isPlayable()) {
                        destination$iv$iv3.add(element$iv$iv3);
                    }
                }
                List playable = (List) destination$iv$iv3;
                List list3 = playable;
                if (list3.isEmpty()) {
                    list3 = items;
                }
                MusicQueryMatcher.Scored<MediaBrowserCompat.MediaItem> scoredPickBestItem3 = pickBestItem(list3, query, 55);
                if (scoredPickBestItem3 == null) {
                    List list4 = playable;
                    if (list4.isEmpty()) {
                        list4 = items;
                    }
                    MusicQueryMatcher.Scored<MediaBrowserCompat.MediaItem> scoredPickBestItem4 = pickBestItem(list4, query, 1);
                    return scoredPickBestItem4 != null ? "ERR:По «" + query + "» близкого трека нет. Похоже: «" + scoredPickBestItem4.getLabel() + "» — уточни название." : "ERR:Ничего похожего на «" + query + "».";
                }
                Log.i(TAG, "search hit score=" + scoredPickBestItem3.getScore() + " «" + scoredPickBestItem3.getLabel() + "»");
                item2 = scoredPickBestItem3.getItem();
                z = true;
                break;
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        MediaBrowserCompat.MediaItem item5 = item2;
        if (item5.isBrowsable()) {
            String mediaId4 = item5.getMediaId();
            if (mediaId4 != null && !StringsKt.isBlank(mediaId4)) {
                z = false;
            }
            if (!z) {
                String mediaId5 = item5.getMediaId();
                Intrinsics.checkNotNull(mediaId5);
                Iterable $this$filter$iv5 = loadChildren(browser, mediaId5, 5000L).getItems();
                Collection destination$iv$iv4 = new ArrayList();
                for (Object element$iv$iv4 : $this$filter$iv5) {
                    if (((MediaBrowserCompat.MediaItem) element$iv$iv4).isPlayable()) {
                        destination$iv$iv4.add(element$iv$iv4);
                    }
                }
                List kids = (List) destination$iv$iv4;
                MusicQueryMatcher.Scored<MediaBrowserCompat.MediaItem> scoredPickBestItem5 = pickBestItem(kids, query, 55);
                if (scoredPickBestItem5 == null) {
                    MediaBrowserCompat.MediaItem it3 = (MediaBrowserCompat.MediaItem) CollectionsKt.firstOrNull(kids);
                    scored = it3 != null ? new MusicQueryMatcher.Scored<>(it3, 0, itemLabel(it3)) : null;
                } else {
                    scored = scoredPickBestItem5;
                }
                MusicQueryMatcher.Scored<MediaBrowserCompat.MediaItem> scored2 = scored;
                if (scored2 != null) {
                    return playResolved(ctrl, scored2.getItem(), scored2.getLabel(), query, false);
                }
            }
        }
        String strItemLabel = itemLabel(item5);
        if (StringsKt.isBlank(strItemLabel)) {
            strItemLabel = query;
        }
        return playResolved(ctrl, item5, strItemLabel, query, true);
    }

    private final String playResolved(MediaControllerCompat ctrl, MediaBrowserCompat.MediaItem item, String label, String originalQuery, boolean preferSearch) throws InterruptedException {
        String precise;
        CharSequence subtitle;
        CharSequence title;
        MediaDescriptionCompat description = item.getDescription();
        String title2 = (description == null || (title = description.getTitle()) == null) ? null : title.toString();
        if (title2 == null) {
            title2 = "";
        }
        MediaDescriptionCompat description2 = item.getDescription();
        String string = (description2 == null || (subtitle = description2.getSubtitle()) == null) ? null : subtitle.toString();
        String artist = string != null ? string : "";
        if (StringsKt.isBlank(title2) || StringsKt.isBlank(artist)) {
            precise = !StringsKt.isBlank(title2) ? title2 : originalQuery;
        } else {
            precise = title2 + StringUtils.SPACE + artist;
        }
        if (preferSearch && !StringsKt.isBlank(precise)) {
            Log.i(TAG, "prefer playFromSearch «" + precise + "» (label=" + label + ")");
            String str = title2;
            if (StringsKt.isBlank(str)) {
                str = precise;
            }
            String viaSearch = playFromSearchQuery(ctrl, precise, str);
            if (viaSearch != null && !StringsKt.startsWith$default(viaSearch, "ERR:", false, 2, (Object) null)) {
                return viaSearch;
            }
            Log.w(TAG, "playFromSearch weak — fallback mediaId");
        }
        String trackId = toPlayableTrackId(item);
        if (trackId == null) {
            if (preferSearch) {
                String str2 = title2;
                if (StringsKt.isBlank(str2)) {
                    str2 = originalQuery;
                }
                return playFromSearchQuery(ctrl, originalQuery, str2);
            }
            return "ERR:Нашёл «" + title2 + "», но не могу включить в фоне.";
        }
        MediaDescriptionCompat description3 = item.getDescription();
        String str3 = label;
        if (StringsKt.isBlank(str3)) {
            str3 = precise;
        }
        return playMediaId(ctrl, trackId, description3, str3);
    }

    private final String playFromSearchQuery(MediaControllerCompat ctrl, String searchQuery, String expectedTitle) throws InterruptedException {
        CharSequence errorMessage;
        MediaMetadataCompat metadata = ctrl.getMetadata();
        String string = metadata != null ? metadata.getString(MediaMetadataCompat.METADATA_KEY_TITLE) : null;
        if (string == null) {
            string = "";
        }
        String prevTitle = string;
        Log.i(TAG, "playFromSearch «" + searchQuery + "» expected='" + expectedTitle + "' prev='" + prevTitle + "'");
        try {
            Result.Companion companion = Result.INSTANCE;
            MusicTool musicTool = this;
            ctrl.getTransportControls().stop();
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        Thread.sleep(250L);
        try {
            Result.Companion companion3 = Result.INSTANCE;
            MusicTool $this$playFromSearchQuery_u24lambda_u241 = this;
            ctrl.getTransportControls().playFromSearch(searchQuery, $this$playFromSearchQuery_u24lambda_u241.searchExtras(searchQuery));
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th2));
        }
        try {
            Result.Companion companion5 = Result.INSTANCE;
            MusicTool musicTool2 = this;
            ctrl.getTransportControls().play();
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th3) {
            Result.Companion companion6 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th3));
        }
        long deadline = System.currentTimeMillis() + ((long) 10000);
        while (System.currentTimeMillis() < deadline) {
            PlaybackStateCompat playbackState = ctrl.getPlaybackState();
            Integer st = playbackState != null ? Integer.valueOf(playbackState.getState()) : null;
            PlaybackStateCompat playbackState2 = ctrl.getPlaybackState();
            String string2 = (playbackState2 == null || (errorMessage = playbackState2.getErrorMessage()) == null) ? null : errorMessage.toString();
            if (string2 == null) {
                string2 = "";
            }
            String err = string2;
            if (!StringsKt.isBlank(err) && (StringsKt.contains((CharSequence) err, (CharSequence) "подписк", true) || StringsKt.contains((CharSequence) err, (CharSequence) "Android Auto", true))) {
                return "ERR:" + err;
            }
            MediaMetadataCompat metadata2 = ctrl.getMetadata();
            String string3 = metadata2 != null ? metadata2.getString(MediaMetadataCompat.METADATA_KEY_TITLE) : null;
            if (string3 == null) {
                string3 = "";
            }
            String metaTitle = string3;
            MediaMetadataCompat metadata3 = ctrl.getMetadata();
            String string4 = metadata3 != null ? metadata3.getString(MediaMetadataCompat.METADATA_KEY_ARTIST) : null;
            if (string4 == null) {
                string4 = "";
            }
            String metaArtist = string4;
            boolean playing = (st != null && st.intValue() == 3) || (st != null && st.intValue() == 6);
            boolean matched = StringsKt.isBlank(expectedTitle) || MusicQueryMatcher.INSTANCE.score(expectedTitle, metaTitle, metaArtist) >= 55 || MusicQueryMatcher.INSTANCE.score(searchQuery, metaTitle, metaArtist) >= 55;
            boolean switched = (StringsKt.isBlank(metaTitle) || Intrinsics.areEqual(metaTitle, prevTitle)) ? false : true;
            if (playing && (matched || switched)) {
                if (!StringsKt.isBlank(metaTitle) && !StringsKt.isBlank(metaArtist)) {
                    return metaArtist + " — " + metaTitle;
                }
                if (!StringsKt.isBlank(metaTitle)) {
                    return metaTitle;
                }
                String str = expectedTitle;
                if (StringsKt.isBlank(str)) {
                    str = searchQuery;
                }
                return str;
            }
            if ((st != null && st.intValue() == 2) || (st != null && st.intValue() == 1)) {
                try {
                    Result.Companion companion7 = Result.INSTANCE;
                    MusicTool musicTool3 = this;
                    ctrl.getTransportControls().play();
                    Result.m392constructorimpl(Unit.INSTANCE);
                } catch (Throwable th4) {
                    Result.Companion companion8 = Result.INSTANCE;
                    Result.m392constructorimpl(ResultKt.createFailure(th4));
                }
            }
            Thread.sleep(250L);
        }
        MediaMetadataCompat metadata4 = ctrl.getMetadata();
        String string5 = metadata4 != null ? metadata4.getString(MediaMetadataCompat.METADATA_KEY_TITLE) : null;
        String str2 = string5 != null ? string5 : "";
        if (StringsKt.isBlank(str2)) {
            str2 = "тишина";
        }
        return "ERR:Не включил «" + searchQuery + "». Сейчас: «" + ((Object) str2) + "».";
    }

    private final MusicQueryMatcher.Scored<MediaBrowserCompat.MediaItem> pickBestItem(List<? extends MediaBrowserCompat.MediaItem> items, String query, int minScore) {
        return MusicQueryMatcher.INSTANCE.bestMatch(items, query, new Function1() { // from class: com.jarvis.tools.MusicTool$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MusicTool.pickBestItem$lambda$0((MediaBrowserCompat.MediaItem) obj);
            }
        }, minScore);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pair pickBestItem$lambda$0(MediaBrowserCompat.MediaItem item) {
        CharSequence subtitle;
        CharSequence title;
        Intrinsics.checkNotNullParameter(item, "item");
        MediaDescriptionCompat description = item.getDescription();
        String string = null;
        String title2 = (description == null || (title = description.getTitle()) == null) ? null : title.toString();
        if (title2 == null) {
            title2 = "";
        }
        MediaDescriptionCompat description2 = item.getDescription();
        if (description2 != null && (subtitle = description2.getSubtitle()) != null) {
            string = subtitle.toString();
        }
        String sub = string != null ? string : "";
        return TuplesKt.to(title2, sub);
    }

    private final String itemLabel(MediaBrowserCompat.MediaItem item) {
        CharSequence subtitle;
        CharSequence title;
        MediaDescriptionCompat description = item.getDescription();
        String string = null;
        String title2 = (description == null || (title = description.getTitle()) == null) ? null : title.toString();
        if (title2 == null) {
            title2 = "";
        }
        MediaDescriptionCompat description2 = item.getDescription();
        if (description2 != null && (subtitle = description2.getSubtitle()) != null) {
            string = subtitle.toString();
        }
        String sub = string != null ? string : "";
        if (StringsKt.isBlank(title2) || StringsKt.isBlank(sub)) {
            return !StringsKt.isBlank(title2) ? title2 : sub;
        }
        return sub + " — " + title2;
    }

    private final String playDownloaded(MediaBrowserCompat browser, MediaControllerCompat ctrl) {
        String string;
        CharSequence title;
        Iterable $this$filter$iv = loadDownloadedTracks(browser);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            MediaBrowserCompat.MediaItem it = (MediaBrowserCompat.MediaItem) element$iv$iv;
            if (it.isPlayable()) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List playable = (List) destination$iv$iv;
        if (playable.isEmpty()) {
            return "ERR:Нет скачанных треков. Скачай музыку в Кионе (Избранное → Скачанные).";
        }
        MediaBrowserCompat.MediaItem item = (MediaBrowserCompat.MediaItem) CollectionsKt.random(playable, Random.INSTANCE);
        String mediaId = item.getMediaId();
        MediaDescriptionCompat description = item.getDescription();
        MediaDescriptionCompat description2 = item.getDescription();
        if (description2 == null || (title = description2.getTitle()) == null || (string = title.toString()) == null) {
            string = "скачанное";
        }
        return playMediaId(ctrl, mediaId, description, string);
    }

    private final String playChildren(MediaBrowserCompat browser, MediaControllerCompat ctrl, String parentId, boolean preferShuffle) {
        String string;
        CharSequence title;
        Iterable $this$filter$iv = loadChildren(browser, parentId, 5000L).getItems();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            MediaBrowserCompat.MediaItem it = (MediaBrowserCompat.MediaItem) element$iv$iv;
            if (it.isPlayable()) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List kids = (List) destination$iv$iv;
        if (kids.isEmpty()) {
            return "ERR:В «" + parentId + "» пусто. Добавь треки в избранное Киона.";
        }
        MediaBrowserCompat.MediaItem item = (MediaBrowserCompat.MediaItem) (preferShuffle ? CollectionsKt.random(kids, Random.INSTANCE) : CollectionsKt.first(kids));
        if (preferShuffle) {
            try {
                Result.Companion companion = Result.INSTANCE;
                MusicTool musicTool = this;
                ctrl.getTransportControls().setShuffleMode(1);
                Result.m392constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m392constructorimpl(ResultKt.createFailure(th));
            }
        }
        String mediaId = item.getMediaId();
        MediaDescriptionCompat description = item.getDescription();
        MediaDescriptionCompat description2 = item.getDescription();
        if (description2 == null || (title = description2.getTitle()) == null || (string = title.toString()) == null) {
            string = "избранное";
        }
        return playMediaId(ctrl, mediaId, description, string);
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0085  */
    /* JADX WARN: Code duplicated, block: B:60:0x012e  */
    private final List<MediaBrowserCompat.MediaItem> loadDownloadedTracks(MediaBrowserCompat browser) {
        boolean z;
        Iterable items;
        boolean z2;
        CharSequence title;
        boolean z3;
        List<MediaBrowserCompat.MediaItem> items2 = loadChildren(browser, "__DOWNLOADED__", 4000L).getItems();
        List<MediaBrowserCompat.MediaItem> $this$any$iv = items2;
        boolean z4 = true;
        if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    if (((MediaBrowserCompat.MediaItem) element$iv).isPlayable()) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
        } else {
            z = false;
        }
        if (z) {
            return items2;
        }
        ArrayList out = new ArrayList();
        List<MediaBrowserCompat.MediaItem> $this$filter$iv = items2;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            MediaBrowserCompat.MediaItem it2 = (MediaBrowserCompat.MediaItem) element$iv$iv;
            if (it2.isBrowsable()) {
                String mediaId = it2.getMediaId();
                if (mediaId == null || StringsKt.isBlank(mediaId)) {
                    z3 = false;
                } else {
                    z3 = true;
                }
            } else {
                z3 = false;
            }
            if (z3) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        for (MediaBrowserCompat.MediaItem folder : CollectionsKt.take((List) destination$iv$iv, 4)) {
            String mediaId2 = folder.getMediaId();
            Intrinsics.checkNotNull(mediaId2);
            CollectionsKt.addAll(out, loadChildren(browser, mediaId2, 4000L).getItems());
        }
        if (out.isEmpty()) {
            ArrayList arrayList = out;
            Iterable $this$filter$iv2 = loadChildren(browser, "__FAVORITE__", 3000L).getItems();
            Collection destination$iv$iv2 = new ArrayList();
            for (Object element$iv$iv2 : $this$filter$iv2) {
                MediaBrowserCompat.MediaItem it3 = (MediaBrowserCompat.MediaItem) element$iv$iv2;
                String mediaId3 = it3.getMediaId();
                if (mediaId3 != null && StringsKt.contains((CharSequence) mediaId3, (CharSequence) "DOWN", true)) {
                    z2 = true;
                } else {
                    MediaDescriptionCompat description = it3.getDescription();
                    if ((description == null || (title = description.getTitle()) == null || !StringsKt.contains(title, (CharSequence) "кач", true)) ? false : true) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                }
                if (z2) {
                    destination$iv$iv2.add(element$iv$iv2);
                }
            }
            Iterable<MediaBrowserCompat.MediaItem> $this$flatMap$iv = (List) destination$iv$iv2;
            Collection destination$iv$iv3 = new ArrayList();
            int $i$f$flatMapTo = 0;
            for (MediaBrowserCompat.MediaItem f : $this$flatMap$iv) {
                String mediaId4 = f.getMediaId();
                if ((mediaId4 == null || StringsKt.isBlank(mediaId4)) ? z4 : false) {
                    items = CollectionsKt.emptyList();
                } else {
                    String mediaId5 = f.getMediaId();
                    Intrinsics.checkNotNull(mediaId5);
                    items = loadChildren(browser, mediaId5, 3000L).getItems();
                }
                Iterable list$iv$iv = items;
                CollectionsKt.addAll(destination$iv$iv3, list$iv$iv);
                $i$f$flatMapTo = $i$f$flatMapTo;
                z4 = true;
            }
            CollectionsKt.addAll(arrayList, (List) destination$iv$iv3);
        }
        return out;
    }

    private final String toPlayableTrackId(MediaBrowserCompat.MediaItem item) {
        boolean z;
        Uri mediaUri;
        String id = item.getMediaId();
        if (id == null) {
            return null;
        }
        boolean z2 = false;
        if (StringsKt.startsWith$default(id, "__TRACKS__:", false, 2, (Object) null)) {
            return id;
        }
        if (StringsKt.startsWith$default(id, "__SEARCH__:", false, 2, (Object) null)) {
            String n = StringsKt.substringAfter$default(id, "__SEARCH__:", (String) null, 2, (Object) null);
            if (!StringsKt.isBlank(n)) {
                return "__TRACKS__:" + n;
            }
        }
        MediaDescriptionCompat description = item.getDescription();
        String uri = (description == null || (mediaUri = description.getMediaUri()) == null) ? null : mediaUri.toString();
        if (uri == null) {
            uri = "";
        }
        if (!StringsKt.isBlank(uri)) {
            CharSequence $this$all$iv = uri;
            int i = 0;
            while (true) {
                if (i < $this$all$iv.length()) {
                    char element$iv = $this$all$iv.charAt(i);
                    if (!Character.isDigit(element$iv)) {
                        z = false;
                        break;
                    }
                    i++;
                } else {
                    z = true;
                    break;
                }
            }
            if (z) {
                return "__TRACKS__:" + uri;
            }
        }
        String $this$all$iv2 = id;
        int i2 = 0;
        while (true) {
            if (i2 < $this$all$iv2.length()) {
                char element$iv2 = $this$all$iv2.charAt(i2);
                if (!Character.isDigit(element$iv2)) {
                    break;
                }
                i2++;
            } else {
                z2 = true;
                break;
            }
        }
        if (z2) {
            return "__TRACKS__:" + id;
        }
        if (item.isPlayable()) {
            return id;
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:118:0x0214 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:119:0x0216  */
    /* JADX WARN: Code duplicated, block: B:120:0x0218  */
    /* JADX WARN: Code duplicated, block: B:129:0x0240 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:130:0x0242  */
    /* JADX WARN: Code duplicated, block: B:196:0x03da  */
    /* JADX WARN: Code duplicated, block: B:212:0x03fb A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:269:0x0409 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:92:0x01de  */
    /* JADX WARN: Code duplicated, block: B:97:0x01e6  */
    private final String playMediaId(MediaControllerCompat ctrl, String mediaId, MediaDescriptionCompat description, String fallbackName) throws InterruptedException {
        String str;
        String str2;
        String str3;
        String str4;
        String metaTitle;
        String str5;
        String searchQ;
        String str6;
        boolean z;
        boolean z2;
        boolean z3;
        boolean playing;
        String metaTitle2;
        String str7;
        String str8;
        String prevTitle;
        String str9;
        String expectedTitle;
        int i;
        boolean z4;
        boolean playing2;
        boolean z5;
        CharSequence errorMessage;
        CharSequence title;
        String str10 = mediaId;
        boolean playing3 = true;
        if (str10 == null || StringsKt.isBlank(str10)) {
            return null;
        }
        String string = (description == null || (title = description.getTitle()) == null) ? null : title.toString();
        if (string == null) {
            string = "";
        }
        String expectedTitle2 = string;
        MediaMetadataCompat metadata = ctrl.getMetadata();
        String str11 = MediaMetadataCompat.METADATA_KEY_TITLE;
        String string2 = metadata != null ? metadata.getString(MediaMetadataCompat.METADATA_KEY_TITLE) : null;
        if (string2 == null) {
            string2 = "";
        }
        String prevTitle2 = string2;
        String str12 = "'";
        String str13 = TAG;
        Log.i(TAG, "playFromMediaId " + mediaId + " expected='" + expectedTitle2 + "' prev='" + prevTitle2 + "'");
        try {
            Result.Companion companion = Result.INSTANCE;
            MusicTool musicTool = this;
            ctrl.getTransportControls().stop();
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        Thread.sleep(300L);
        try {
            Result.Companion companion3 = Result.INSTANCE;
            MusicTool musicTool2 = this;
            ctrl.getTransportControls().playFromMediaId(mediaId, null);
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th2));
        }
        Thread.sleep(RESUME_POLL_MS);
        try {
            Result.Companion companion5 = Result.INSTANCE;
            MusicTool musicTool3 = this;
            ctrl.getTransportControls().play();
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th3) {
            Result.Companion companion6 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th3));
        }
        String str14 = "";
        long deadline = System.currentTimeMillis() + ((long) 10000);
        boolean sawSwitch = false;
        while (System.currentTimeMillis() < deadline) {
            PlaybackStateCompat playbackState = ctrl.getPlaybackState();
            Integer st = playbackState != null ? Integer.valueOf(playbackState.getState()) : null;
            PlaybackStateCompat playbackState2 = ctrl.getPlaybackState();
            String string3 = (playbackState2 == null || (errorMessage = playbackState2.getErrorMessage()) == null) ? null : errorMessage.toString();
            if (string3 == null) {
                string3 = str14;
            }
            String err = string3;
            if (!StringsKt.isBlank(err) && (StringsKt.contains(err, "подписк", playing3) || StringsKt.contains(err, "Android Auto", playing3))) {
                return "ERR:" + err;
            }
            MediaMetadataCompat metadata2 = ctrl.getMetadata();
            String string4 = metadata2 != null ? metadata2.getString(str11) : null;
            if (string4 == null) {
                string4 = str14;
            }
            String metaTitle3 = string4;
            boolean titleSwitched = (StringsKt.isBlank(metaTitle3) || StringsKt.isBlank(prevTitle2) || Intrinsics.areEqual(metaTitle3, prevTitle2)) ? false : playing3;
            if (StringsKt.isBlank(expectedTitle2) || StringsKt.isBlank(metaTitle3)) {
                metaTitle2 = metaTitle3;
                str7 = str13;
                str8 = str12;
                prevTitle = prevTitle2;
                str9 = str11;
                expectedTitle = expectedTitle2;
                i = 3;
            } else {
                metaTitle2 = metaTitle3;
                i = 3;
                str7 = str13;
                str8 = str12;
                prevTitle = prevTitle2;
                str9 = str11;
                expectedTitle = expectedTitle2;
                boolean z6 = MusicQueryMatcher.score$default(MusicQueryMatcher.INSTANCE, expectedTitle2, metaTitle2, null, 4, null) >= 70;
                boolean titleMatchesExpected = z6;
                if (StringsKt.isBlank(prevTitle) || StringsKt.isBlank(metaTitle2)) {
                    z4 = false;
                } else {
                    z4 = true;
                }
                boolean freshStart = z4;
                if (titleSwitched || titleMatchesExpected || freshStart) {
                    sawSwitch = true;
                }
                playing2 = (st == null && st.intValue() == i) || (st != null && st.intValue() == 6);
                if (!playing2 && sawSwitch) {
                    return labelFrom(ctrl, description, fallbackName, true);
                }
                try {
                    if (st != null && st.intValue() == 2) {
                        z5 = true;
                    } else {
                        if (st == null) {
                            z5 = true;
                        } else {
                            z5 = true;
                            if (st.intValue() == 1) {
                            }
                        }
                        if (st == null && st.intValue() == 7) {
                            try {
                                Result.Companion companion7 = Result.INSTANCE;
                                MusicTool musicTool4 = this;
                                try {
                                    ctrl.getTransportControls().playFromMediaId(mediaId, null);
                                    Result.m392constructorimpl(Unit.INSTANCE);
                                } catch (Throwable th4) {
                                    th = th4;
                                    Result.Companion companion8 = Result.INSTANCE;
                                    Result.m392constructorimpl(ResultKt.createFailure(th));
                                }
                            } catch (Throwable th5) {
                                th = th5;
                            }
                            try {
                                Result.Companion companion9 = Result.INSTANCE;
                                MusicTool musicTool5 = this;
                                ctrl.getTransportControls().play();
                                Result.m392constructorimpl(Unit.INSTANCE);
                            } catch (Throwable th6) {
                                Result.Companion companion10 = Result.INSTANCE;
                                Result.m392constructorimpl(ResultKt.createFailure(th6));
                            }
                        }
                        Thread.sleep(250L);
                        prevTitle2 = prevTitle;
                        playing3 = z5;
                        str13 = str7;
                        str12 = str8;
                        str11 = str9;
                        expectedTitle2 = expectedTitle;
                    }
                    Result.Companion companion11 = Result.INSTANCE;
                    MusicTool musicTool6 = this;
                    ctrl.getTransportControls().play();
                    Result.m392constructorimpl(Unit.INSTANCE);
                } catch (Throwable th7) {
                    Result.Companion companion12 = Result.INSTANCE;
                    Result.m392constructorimpl(ResultKt.createFailure(th7));
                }
                if (st == null) {
                    Result.Companion companion13 = Result.INSTANCE;
                    MusicTool musicTool7 = this;
                    ctrl.getTransportControls().playFromMediaId(mediaId, null);
                    Result.m392constructorimpl(Unit.INSTANCE);
                    Result.Companion companion14 = Result.INSTANCE;
                    MusicTool musicTool8 = this;
                    ctrl.getTransportControls().play();
                    Result.m392constructorimpl(Unit.INSTANCE);
                }
                Thread.sleep(250L);
                prevTitle2 = prevTitle;
                playing3 = z5;
                str13 = str7;
                str12 = str8;
                str11 = str9;
                expectedTitle2 = expectedTitle;
            }
            boolean titleMatchesExpected2 = z6;
            if (StringsKt.isBlank(prevTitle)) {
                z4 = false;
            } else {
                z4 = false;
            }
            boolean freshStart2 = z4;
            if (titleSwitched) {
                sawSwitch = true;
            } else {
                sawSwitch = true;
            }
            playing2 = (st == null && st.intValue() == i) || (st != null && st.intValue() == 6);
            if (!playing2) {
            }
            if (st != null) {
                z5 = true;
                Result.Companion companion15 = Result.INSTANCE;
                MusicTool musicTool9 = this;
                ctrl.getTransportControls().play();
                Result.m392constructorimpl(Unit.INSTANCE);
            } else if (st == null) {
                z5 = true;
            } else {
                z5 = true;
                if (st.intValue() == 1) {
                    Result.Companion companion16 = Result.INSTANCE;
                    MusicTool musicTool10 = this;
                    ctrl.getTransportControls().play();
                    Result.m392constructorimpl(Unit.INSTANCE);
                }
            }
            if (st == null) {
                Result.Companion companion17 = Result.INSTANCE;
                MusicTool musicTool11 = this;
                ctrl.getTransportControls().playFromMediaId(mediaId, null);
                Result.m392constructorimpl(Unit.INSTANCE);
                Result.Companion companion18 = Result.INSTANCE;
                MusicTool musicTool12 = this;
                ctrl.getTransportControls().play();
                Result.m392constructorimpl(Unit.INSTANCE);
            }
            Thread.sleep(250L);
            prevTitle2 = prevTitle;
            playing3 = z5;
            str13 = str7;
            str12 = str8;
            str11 = str9;
            expectedTitle2 = expectedTitle;
        }
        String str15 = str13;
        String str16 = str12;
        String prevTitle3 = prevTitle2;
        String str17 = str11;
        String expectedTitle3 = expectedTitle2;
        boolean z7 = playing3;
        String str18 = expectedTitle3;
        if (StringsKt.isBlank(str18)) {
            str18 = fallbackName;
        }
        String searchQ2 = str18;
        if (StringsKt.isBlank(searchQ2)) {
            str = str15;
            str2 = str17;
            str3 = null;
        } else {
            String str19 = str15;
            Log.i(str19, "mediaId switch stalled — playFromSearch «" + searchQ2 + "»");
            try {
                Result.Companion companion19 = Result.INSTANCE;
                MusicTool musicTool13 = this;
                ctrl.getTransportControls().stop();
                Result.m392constructorimpl(Unit.INSTANCE);
            } catch (Throwable th8) {
                Result.Companion companion20 = Result.INSTANCE;
                Result.m392constructorimpl(ResultKt.createFailure(th8));
            }
            Thread.sleep(RESUME_POLL_MS);
            try {
                Result.Companion companion21 = Result.INSTANCE;
                MusicTool $this$playMediaId_u24lambda_u248 = this;
                ctrl.getTransportControls().playFromSearch(searchQ2, $this$playMediaId_u24lambda_u248.searchExtras(searchQ2));
                Result.m392constructorimpl(Unit.INSTANCE);
            } catch (Throwable th9) {
                Result.Companion companion22 = Result.INSTANCE;
                Result.m392constructorimpl(ResultKt.createFailure(th9));
            }
            try {
                Result.Companion companion23 = Result.INSTANCE;
                MusicTool musicTool14 = this;
                ctrl.getTransportControls().play();
                Result.m392constructorimpl(Unit.INSTANCE);
            } catch (Throwable th10) {
                Result.Companion companion24 = Result.INSTANCE;
                Result.m392constructorimpl(ResultKt.createFailure(th10));
            }
            long fbDeadline = ((long) VoiceIdentity.PHRASE_MS) + System.currentTimeMillis();
            while (System.currentTimeMillis() < fbDeadline) {
                MediaMetadataCompat metadata3 = ctrl.getMetadata();
                if (metadata3 != null) {
                    str4 = str17;
                    metaTitle = metadata3.getString(str4);
                } else {
                    str4 = str17;
                    metaTitle = null;
                }
                if (metaTitle == null) {
                    metaTitle = str14;
                }
                PlaybackStateCompat playbackState3 = ctrl.getPlaybackState();
                Integer st2 = playbackState3 != null ? Integer.valueOf(playbackState3.getState()) : null;
                if (StringsKt.isBlank(expectedTitle3) || StringsKt.isBlank(metaTitle)) {
                    str5 = str19;
                    searchQ = searchQ2;
                    str6 = str4;
                    z = z7;
                } else {
                    str5 = str19;
                    searchQ = searchQ2;
                    str6 = str4;
                    z = z7;
                    if (MusicQueryMatcher.score$default(MusicQueryMatcher.INSTANCE, expectedTitle3, metaTitle, null, 4, null) < 55 && MusicQueryMatcher.score$default(MusicQueryMatcher.INSTANCE, searchQ, metaTitle, null, 4, null) < 55) {
                        z2 = false;
                    }
                    boolean matched = z2;
                    if (!StringsKt.isBlank(metaTitle) || Intrinsics.areEqual(metaTitle, prevTitle3)) {
                        z3 = false;
                    } else {
                        z3 = z;
                    }
                    boolean switched = z3;
                    if (st2 == null || st2.intValue() != 3) {
                        if (st2 == null || st2.intValue() != 6) {
                            playing = false;
                        }
                        if (!playing && (matched || switched)) {
                            return labelFrom(ctrl, description, fallbackName, false);
                        }
                        Thread.sleep(250L);
                        searchQ2 = searchQ;
                        z7 = z;
                        str19 = str5;
                        str17 = str6;
                    }
                    playing = z;
                    if (!playing) {
                    }
                    Thread.sleep(250L);
                    searchQ2 = searchQ;
                    z7 = z;
                    str19 = str5;
                    str17 = str6;
                }
                z2 = z;
                boolean matched2 = z2;
                if (StringsKt.isBlank(metaTitle)) {
                    z3 = false;
                } else {
                    z3 = false;
                }
                boolean switched2 = z3;
                if (st2 == null) {
                    playing = z;
                    if (!playing) {
                    }
                    Thread.sleep(250L);
                    searchQ2 = searchQ;
                    z7 = z;
                    str19 = str5;
                    str17 = str6;
                }
                if (st2 == null) {
                    playing = z;
                    if (!playing) {
                    }
                    Thread.sleep(250L);
                    searchQ2 = searchQ;
                    z7 = z;
                    str19 = str5;
                    str17 = str6;
                }
                playing = false;
                if (!playing) {
                }
                Thread.sleep(250L);
                searchQ2 = searchQ;
                z7 = z;
                str19 = str5;
                str17 = str6;
            }
            str = str19;
            str2 = str17;
            str3 = null;
        }
        MediaMetadataCompat metadata4 = ctrl.getMetadata();
        String string5 = metadata4 != null ? metadata4.getString(str2) : str3;
        String metaTitle4 = string5 != null ? string5 : "";
        Log.w(str, "play failed expected='" + expectedTitle3 + "' still='" + metaTitle4 + str16);
        String str20 = expectedTitle3;
        if (StringsKt.isBlank(str20)) {
            str20 = fallbackName;
        }
        String str21 = metaTitle4;
        if (StringsKt.isBlank(str21)) {
            str21 = "тишина";
        }
        return "ERR:Не удалось включить «" + ((Object) str20) + "». Сейчас: «" + ((Object) str21) + "».";
    }

    static /* synthetic */ String labelFrom$default(MusicTool musicTool, MediaControllerCompat mediaControllerCompat, MediaDescriptionCompat mediaDescriptionCompat, String str, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = false;
        }
        return musicTool.labelFrom(mediaControllerCompat, mediaDescriptionCompat, str, z);
    }

    /* JADX WARN: Code duplicated, block: B:55:0x0092 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:56:0x0094  */
    /* JADX WARN: Code duplicated, block: B:57:0x0096  */
    /* JADX WARN: Code duplicated, block: B:60:0x00a1  */
    private final String labelFrom(MediaControllerCompat ctrl, MediaDescriptionCompat description, String fallback, boolean preferDescription) {
        String str;
        String title;
        String str2;
        String artist;
        CharSequence subtitle;
        CharSequence title2;
        String descTitle = (description == null || (title2 = description.getTitle()) == null) ? null : title2.toString();
        String string = (description == null || (subtitle = description.getSubtitle()) == null) ? null : subtitle.toString();
        if (string == null) {
            string = "";
        }
        String descArtist = string;
        MediaMetadataCompat metadata = ctrl.getMetadata();
        String metaTitle = metadata != null ? metadata.getString(MediaMetadataCompat.METADATA_KEY_TITLE) : null;
        MediaMetadataCompat metadata2 = ctrl.getMetadata();
        String string2 = metadata2 != null ? metadata2.getString(MediaMetadataCompat.METADATA_KEY_ARTIST) : null;
        String metaArtist = string2 != null ? string2 : "";
        if (preferDescription) {
            String str3 = descTitle;
            if (!(str3 == null || StringsKt.isBlank(str3))) {
                String str4 = metaTitle;
                boolean metaOk = !(str4 == null || StringsKt.isBlank(str4)) && MusicQueryMatcher.score$default(MusicQueryMatcher.INSTANCE, descTitle, metaTitle, null, 4, null) >= 60;
                if (metaOk) {
                    title = metaTitle;
                    String str5 = metaArtist;
                    if (StringsKt.isBlank(str5)) {
                        str5 = descArtist;
                    }
                    artist = str5;
                } else {
                    title = descTitle;
                    artist = descArtist;
                }
            } else {
                if (metaTitle == null) {
                    str = descTitle;
                } else {
                    str = metaTitle;
                }
                title = str;
                str2 = metaArtist;
                if (StringsKt.isBlank(str2)) {
                    str2 = descArtist;
                }
                artist = str2;
            }
        } else {
            if (metaTitle == null) {
                str = descTitle;
            } else {
                str = metaTitle;
            }
            title = str;
            str2 = metaArtist;
            if (StringsKt.isBlank(str2)) {
                str2 = descArtist;
            }
            artist = str2;
        }
        String str6 = title;
        if (!(str6 == null || StringsKt.isBlank(str6)) && !StringsKt.isBlank(artist)) {
            return artist + " — " + title;
        }
        String str7 = title;
        return !(str7 == null || StringsKt.isBlank(str7)) ? title : fallback;
    }

    private final BrowseResult search(final MediaBrowserCompat browser, final String query, long timeoutMs) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicReference items = new AtomicReference(CollectionsKt.emptyList());
        final AtomicReference err = new AtomicReference(null);
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jarvis.tools.MusicTool$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                MusicTool.search$lambda$0(this.f$0, browser, query, items, latch, err);
            }
        });
        latch.await(timeoutMs, TimeUnit.MILLISECONDS);
        Object obj = items.get();
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        return new BrowseResult((List) obj, (String) err.get());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void search$lambda$0(MusicTool this$0, MediaBrowserCompat $browser, String $query, final AtomicReference $items, final CountDownLatch $latch, final AtomicReference $err) {
        Object objM392constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            $browser.search($query, this$0.searchExtras($query), new MediaBrowserCompat.SearchCallback() { // from class: com.jarvis.tools.MusicTool$search$1$1$1
                @Override // android.support.v4.media.MediaBrowserCompat.SearchCallback
                public void onSearchResult(String q, Bundle extras, List<MediaBrowserCompat.MediaItem> list) {
                    Intrinsics.checkNotNullParameter(q, "q");
                    Intrinsics.checkNotNullParameter(list, "list");
                    $items.set(CollectionsKt.toList(list));
                    $latch.countDown();
                }

                @Override // android.support.v4.media.MediaBrowserCompat.SearchCallback
                public void onError(String q, Bundle extras) {
                    Intrinsics.checkNotNullParameter(q, "q");
                    $err.set("Поиск не удался");
                    $latch.countDown();
                }
            });
            objM392constructorimpl = Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        Throwable it = Result.m395exceptionOrNullimpl(objM392constructorimpl);
        if (it != null) {
            $err.set(it.getMessage());
            $latch.countDown();
        }
    }

    private final BrowseResult loadChildren(final MediaBrowserCompat browser, final String parentId, long timeoutMs) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicReference items = new AtomicReference(CollectionsKt.emptyList());
        final AtomicReference err = new AtomicReference(null);
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jarvis.tools.MusicTool$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                MusicTool.loadChildren$lambda$0(this.f$0, browser, parentId, items, latch, err);
            }
        });
        latch.await(timeoutMs, TimeUnit.MILLISECONDS);
        Object obj = items.get();
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        return new BrowseResult((List) obj, (String) err.get());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadChildren$lambda$0(MusicTool this$0, final MediaBrowserCompat $browser, final String $parentId, final AtomicReference $items, final CountDownLatch $latch, final AtomicReference $err) {
        Object objM392constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            $browser.subscribe($parentId, new MediaBrowserCompat.SubscriptionCallback() { // from class: com.jarvis.tools.MusicTool$loadChildren$1$1$1
                @Override // android.support.v4.media.MediaBrowserCompat.SubscriptionCallback
                public void onChildrenLoaded(String parent, List<MediaBrowserCompat.MediaItem> children) {
                    Intrinsics.checkNotNullParameter(parent, "parent");
                    Intrinsics.checkNotNullParameter(children, "children");
                    $items.set(CollectionsKt.toList(children));
                    MediaBrowserCompat mediaBrowserCompat = $browser;
                    String str = $parentId;
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        MusicTool$loadChildren$1$1$1 musicTool$loadChildren$1$1$1 = this;
                        mediaBrowserCompat.unsubscribe(str);
                        Result.m392constructorimpl(Unit.INSTANCE);
                    } catch (Throwable th) {
                        Result.Companion companion3 = Result.INSTANCE;
                        Result.m392constructorimpl(ResultKt.createFailure(th));
                    }
                    $latch.countDown();
                }

                @Override // android.support.v4.media.MediaBrowserCompat.SubscriptionCallback
                public void onError(String id) {
                    Intrinsics.checkNotNullParameter(id, "id");
                    $err.set("Не открыл " + id);
                    MediaBrowserCompat mediaBrowserCompat = $browser;
                    String str = $parentId;
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        MusicTool$loadChildren$1$1$1 musicTool$loadChildren$1$1$1 = this;
                        mediaBrowserCompat.unsubscribe(str);
                        Result.m392constructorimpl(Unit.INSTANCE);
                    } catch (Throwable th) {
                        Result.Companion companion3 = Result.INSTANCE;
                        Result.m392constructorimpl(ResultKt.createFailure(th));
                    }
                    $latch.countDown();
                }
            });
            objM392constructorimpl = Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        Throwable it = Result.m395exceptionOrNullimpl(objM392constructorimpl);
        if (it != null) {
            $err.set(it.getMessage());
            $latch.countDown();
        }
    }

    private final Bundle searchExtras(String query) {
        Bundle $this$searchExtras_u24lambda_u240 = new Bundle();
        $this$searchExtras_u24lambda_u240.putString(SearchIntents.EXTRA_QUERY, query);
        $this$searchExtras_u24lambda_u240.putString("android.intent.extra.focus", "vnd.android.cursor.item/audio");
        $this$searchExtras_u24lambda_u240.putString("android.intent.extra.title", query);
        $this$searchExtras_u24lambda_u240.putString("android.intent.extra.artist", query);
        $this$searchExtras_u24lambda_u240.putString("android.intent.extra.album", query);
        return $this$searchExtras_u24lambda_u240;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:93:0x0181  */
    private final ToolResult transport(String requestId, String label, Function1<? super MediaController.TransportControls, Unit> block) throws InterruptedException {
        int key;
        String str;
        String msg;
        PlaybackState playbackState;
        MediaController ctrl = activeController();
        Integer numValueOf = null;
        String packageName = ctrl != null ? ctrl.getPackageName() : null;
        if (ctrl != null && (playbackState = ctrl.getPlaybackState()) != null) {
            numValueOf = Integer.valueOf(playbackState.getState());
        }
        Log.i(TAG, label + ": " + packageName + StringUtils.SPACE + numValueOf);
        if (Intrinsics.areEqual(label, "resume") && !hasLivePlayer(ctrl) && !hasInternet() && isInstalled("ru.yandex.music")) {
            return playOffline(requestId);
        }
        if (Intrinsics.areEqual(label, "resume") && isInstalled("com.soundcloud.android") && !hasLivePlayer(ctrl)) {
            return playSoundCloud$default(this, requestId, "", null, 4, null);
        }
        if (ctrl != null) {
            MediaController.TransportControls transportControls = ctrl.getTransportControls();
            Intrinsics.checkNotNullExpressionValue(transportControls, "getTransportControls(...)");
            block.invoke(transportControls);
            rememberPackage(ctrl.getPackageName());
            if (Intrinsics.areEqual(label, "resume")) {
                ToolResult played = confirmPlaying(requestId);
                boolean dead = played.getStatus() != ToolResultStatus.SUCCESS && isInstalled("com.soundcloud.android");
                if (dead) {
                    return hasInternet() ? playSoundCloud$default(this, requestId, "", null, 4, null) : playOffline(requestId);
                }
                return played;
            }
            switch (label) {
                case "previous":
                    msg = "Назад.";
                    break;
                case "resume":
                    msg = "Играю.";
                    break;
                case "next":
                    msg = "Дальше.";
                    break;
                case "pause":
                    msg = "Пауза.";
                    break;
                default:
                    msg = "Ок.";
                    break;
            }
            return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.SUCCESS, msg, null, 0L, 48, null);
        }
        switch (label) {
            case "previous":
                key = 88;
                break;
            case "resume":
                key = 126;
                break;
            case "rewind":
                key = 89;
                break;
            case "forward":
                key = 90;
                break;
            case "next":
                key = 87;
                break;
            case "pause":
                key = WorkQueueKt.MASK;
                break;
            default:
                key = 85;
                break;
        }
        dispatchMediaKey(key);
        if (Intrinsics.areEqual(label, "resume") && isInstalled(PKG_KION)) {
            new Thread(new Runnable() { // from class: com.jarvis.tools.MusicTool$$ExternalSyntheticLambda14
                @Override // java.lang.Runnable
                public final void run() {
                    MusicTool.transport$lambda$0(this.f$0);
                }
            }, "jarvis-music-resume-bg").start();
        }
        if (Intrinsics.areEqual(label, "resume")) {
            return confirmPlaying(requestId);
        }
        String id = getDefinition().getId();
        ToolResultStatus toolResultStatus = ToolResultStatus.SUCCESS;
        switch (label) {
            case "previous":
                str = "Назад.";
                break;
            case "resume":
                str = "Играю.";
                break;
            case "next":
                str = "Дальше.";
                break;
            case "pause":
                str = "Пауза.";
                break;
            default:
                str = "Ок.";
                break;
        }
        return new ToolResult(requestId, id, toolResultStatus, str, null, 0L, 48, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void transport$lambda$0(final MusicTool this$0) {
        withBrowser$default(this$0, null, new Function2() { // from class: com.jarvis.tools.MusicTool$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return MusicTool.transport$lambda$0$0(this.f$0, (MediaBrowserCompat) obj, (MediaControllerCompat) obj2);
            }
        }, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String transport$lambda$0$0(MusicTool this$0, MediaBrowserCompat mediaBrowserCompat, MediaControllerCompat ctrlCompat) {
        Intrinsics.checkNotNullParameter(mediaBrowserCompat, "<unused var>");
        Intrinsics.checkNotNullParameter(ctrlCompat, "ctrlCompat");
        try {
            Result.Companion companion = Result.INSTANCE;
            ctrlCompat.getTransportControls().play();
            Result.m392constructorimpl(Unit.INSTANCE);
            return "ok";
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
            return "ok";
        }
    }

    private final ToolResult confirmPlaying(String requestId) throws InterruptedException {
        PlaybackState playbackState;
        long deadline = SystemClock.elapsedRealtime() + RESUME_CONFIRM_MS;
        while (SystemClock.elapsedRealtime() < deadline) {
            MediaController active = activeController();
            boolean z = false;
            if (active != null && (playbackState = active.getPlaybackState()) != null && playbackState.getState() == 3) {
                z = true;
            }
            if (z && !isFrozen(active)) {
                return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.SUCCESS, "Играю.", null, 0L, 48, null);
            }
            Thread.sleep(RESUME_POLL_MS);
        }
        return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.FAILED, "Сейчас нечего продолжать, сэр. Скажите, что включить.", null, 0L, 48, null);
    }

    private final ToolResult status(String requestId) {
        String title;
        long now = System.currentTimeMillis();
        synchronized (this.statusCacheLock) {
            StatusCache cached = this.statusCache;
            if (cached != null && now - cached.getAtMs() < 12000) {
                return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.SUCCESS, cached.getText(), null, 0L, 48, null);
            }
            Unit unit = Unit.INSTANCE;
            MediaController ctrl = activeController();
            if (ctrl == null) {
                ToolResult it = new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.SUCCESS, "Сейчас ничего не играет.", null, 0L, 48, null);
                String message = it.getMessage();
                if (message == null) {
                    message = "";
                }
                cacheStatus(message);
                return it;
            }
            rememberPackage(ctrl.getPackageName());
            MediaMetadata metadata = ctrl.getMetadata();
            if (metadata == null || (title = metadata.getString(MediaMetadataCompat.METADATA_KEY_TITLE)) == null) {
                title = "без названия";
            }
            MediaMetadata metadata2 = ctrl.getMetadata();
            String artist = metadata2 != null ? metadata2.getString(MediaMetadataCompat.METADATA_KEY_ARTIST) : null;
            if (artist == null) {
                artist = "";
            }
            PlaybackState playbackState = ctrl.getPlaybackState();
            boolean z = false;
            if (playbackState != null && playbackState.getState() == 3) {
                z = true;
            }
            boolean playing = z;
            String state = playing ? "играет" : "на паузе";
            String who = !StringsKt.isBlank(artist) ? artist + " — " + title : title;
            String text = "Сейчас " + state + ": " + who + ".";
            cacheStatus(text);
            return new ToolResult(requestId, getDefinition().getId(), ToolResultStatus.SUCCESS, text, null, 0L, 48, null);
        }
    }

    private final void cacheStatus(String text) {
        synchronized (this.statusCacheLock) {
            this.statusCache = new StatusCache(text, System.currentTimeMillis());
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: compiled from: MusicTool.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/jarvis/tools/MusicTool$StatusCache;", "", VoiceService.EXTRA_TEXT, "", "atMs", "", "<init>", "(Ljava/lang/String;J)V", "getText", "()Ljava/lang/String;", "getAtMs", "()J", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final /* data */ class StatusCache {
        private final long atMs;
        private final String text;

        public static /* synthetic */ StatusCache copy$default(StatusCache statusCache, String str, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                str = statusCache.text;
            }
            if ((i & 2) != 0) {
                j = statusCache.atMs;
            }
            return statusCache.copy(str, j);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getText() {
            return this.text;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getAtMs() {
            return this.atMs;
        }

        public final StatusCache copy(String text, long atMs) {
            Intrinsics.checkNotNullParameter(text, "text");
            return new StatusCache(text, atMs);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StatusCache)) {
                return false;
            }
            StatusCache statusCache = (StatusCache) other;
            return Intrinsics.areEqual(this.text, statusCache.text) && this.atMs == statusCache.atMs;
        }

        public int hashCode() {
            return (this.text.hashCode() * 31) + Long.hashCode(this.atMs);
        }

        public String toString() {
            return "StatusCache(text=" + this.text + ", atMs=" + this.atMs + ")";
        }

        public StatusCache(String text, long atMs) {
            Intrinsics.checkNotNullParameter(text, "text");
            this.text = text;
            this.atMs = atMs;
        }

        public final long getAtMs() {
            return this.atMs;
        }

        public final String getText() {
            return this.text;
        }
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0079  */
    /* JADX WARN: Code duplicated, block: B:53:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:72:0x0127  */
    private final MediaController activeController() {
        Object objM392constructorimpl;
        Object element$iv;
        Object element$iv2;
        Object element$iv3;
        Object element$iv4;
        Object element$iv5;
        Object element$iv6;
        MediaController it;
        PlaybackState playbackState;
        boolean z;
        boolean z2;
        PlaybackState playbackState2;
        boolean z3;
        if (!JarvisNotificationListenerService.INSTANCE.isConnected()) {
            return null;
        }
        Object systemService = this.context.getSystemService("media_session");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.media.session.MediaSessionManager");
        MediaSessionManager mgr = (MediaSessionManager) systemService;
        ComponentName cn = new ComponentName(this.context, (Class<?>) JarvisNotificationListenerService.class);
        try {
            Result.Companion companion = Result.INSTANCE;
            MusicTool $this$activeController_u24lambda_u240 = this;
            List<MediaController> activeSessions = mgr.getActiveSessions(cn);
            Intrinsics.checkNotNullExpressionValue(activeSessions, "getActiveSessions(...)");
            String preferred = $this$activeController_u24lambda_u240.context.getSharedPreferences("jarvis_prefs", 0).getString(PREF_PLAYER, "com.soundcloud.android");
            List<MediaController> $this$firstOrNull$iv = activeSessions;
            Iterator it2 = $this$firstOrNull$iv.iterator();
            do {
                if (!it2.hasNext()) {
                    element$iv = null;
                    break;
                }
                element$iv = it2.next();
                MediaController it3 = (MediaController) element$iv;
                PlaybackState playbackState3 = it3.getPlaybackState();
                if (playbackState3 != null && playbackState3.getState() == 3) {
                    Intrinsics.checkNotNull(it3);
                    if ($this$activeController_u24lambda_u240.isFrozen(it3)) {
                        z3 = false;
                    } else {
                        z3 = true;
                    }
                } else {
                    z3 = false;
                }
            } while (!z3);
            MediaController mediaController = (MediaController) element$iv;
            if (mediaController == null) {
                List<MediaController> $this$firstOrNull$iv2 = activeSessions;
                Iterator it4 = $this$firstOrNull$iv2.iterator();
                do {
                    if (!it4.hasNext()) {
                        element$iv2 = null;
                        break;
                    }
                    element$iv2 = it4.next();
                    MediaController it5 = (MediaController) element$iv2;
                    playbackState2 = it5.getPlaybackState();
                } while (!(playbackState2 != null && playbackState2.getState() == 6));
                mediaController = (MediaController) element$iv2;
                if (mediaController == null) {
                    List<MediaController> $this$firstOrNull$iv3 = activeSessions;
                    Iterator it6 = $this$firstOrNull$iv3.iterator();
                    do {
                        if (!it6.hasNext()) {
                            element$iv3 = null;
                            break;
                        }
                        element$iv3 = it6.next();
                        MediaController it7 = (MediaController) element$iv3;
                        if (Intrinsics.areEqual(it7.getPackageName(), preferred)) {
                            PlaybackState playbackState4 = it7.getPlaybackState();
                            if (playbackState4 != null && playbackState4.getState() == 2) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                        } else {
                            z2 = false;
                        }
                    } while (!z2);
                    mediaController = (MediaController) element$iv3;
                    if (mediaController == null) {
                        List<MediaController> $this$firstOrNull$iv4 = activeSessions;
                        Iterator it8 = $this$firstOrNull$iv4.iterator();
                        do {
                            if (!it8.hasNext()) {
                                element$iv4 = null;
                                break;
                            }
                            element$iv4 = it8.next();
                            MediaController it9 = (MediaController) element$iv4;
                            if (PLAYERS.contains(it9.getPackageName())) {
                                PlaybackState playbackState5 = it9.getPlaybackState();
                                if (playbackState5 != null && playbackState5.getState() == 2) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                            } else {
                                z = false;
                            }
                        } while (!z);
                        mediaController = (MediaController) element$iv4;
                        if (mediaController == null) {
                            List<MediaController> $this$firstOrNull$iv5 = activeSessions;
                            Iterator it10 = $this$firstOrNull$iv5.iterator();
                            do {
                                if (!it10.hasNext()) {
                                    element$iv5 = null;
                                    break;
                                }
                                element$iv5 = it10.next();
                                MediaController it11 = (MediaController) element$iv5;
                                playbackState = it11.getPlaybackState();
                            } while (!(playbackState != null && playbackState.getState() == 2));
                            mediaController = (MediaController) element$iv5;
                            if (mediaController == null) {
                                List<MediaController> $this$firstOrNull$iv6 = activeSessions;
                                Iterator it12 = $this$firstOrNull$iv6.iterator();
                                do {
                                    if (!it12.hasNext()) {
                                        element$iv6 = null;
                                        break;
                                    }
                                    element$iv6 = it12.next();
                                    it = (MediaController) element$iv6;
                                } while (!Intrinsics.areEqual(it.getPackageName(), preferred));
                                mediaController = (MediaController) element$iv6;
                                if (mediaController == null) {
                                    mediaController = (MediaController) CollectionsKt.firstOrNull((List) activeSessions);
                                }
                            }
                        }
                    }
                }
            }
            objM392constructorimpl = Result.m392constructorimpl(mediaController);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        return (MediaController) (Result.m398isFailureimpl(objM392constructorimpl) ? null : objM392constructorimpl);
    }

    private final boolean isFrozen(MediaController controller) {
        PlaybackState state = controller.getPlaybackState();
        if (state == null) {
            return false;
        }
        long age = SystemClock.elapsedRealtime() - state.getLastPositionUpdateTime();
        return state.getPosition() == 0 && age > 15000;
    }

    private final boolean hasLivePlayer(MediaController ctrl) {
        PlaybackState playbackState;
        Integer numValueOf = (ctrl == null || (playbackState = ctrl.getPlaybackState()) == null) ? null : Integer.valueOf(playbackState.getState());
        if (numValueOf != null && numValueOf.intValue() == 3) {
            return !isFrozen(ctrl);
        }
        if (numValueOf != null && numValueOf.intValue() == 2) {
            return true;
        }
        return numValueOf != null && numValueOf.intValue() == 6;
    }

    private final List<MediaController> allControllers() {
        Object objM392constructorimpl;
        if (!JarvisNotificationListenerService.INSTANCE.isConnected()) {
            return CollectionsKt.emptyList();
        }
        Object systemService = this.context.getSystemService("media_session");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.media.session.MediaSessionManager");
        MediaSessionManager mgr = (MediaSessionManager) systemService;
        ComponentName cn = new ComponentName(this.context, (Class<?>) JarvisNotificationListenerService.class);
        try {
            Result.Companion companion = Result.INSTANCE;
            MusicTool musicTool = this;
            objM392constructorimpl = Result.m392constructorimpl(mgr.getActiveSessions(cn));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        List listEmptyList = CollectionsKt.emptyList();
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = listEmptyList;
        }
        return (List) objM392constructorimpl;
    }

    private final void pauseOtherPlayers(String except) {
        for (MediaController c : allControllers()) {
            if (except == null || !Intrinsics.areEqual(c.getPackageName(), except)) {
                try {
                    Result.Companion companion = Result.INSTANCE;
                    MusicTool musicTool = this;
                    c.getTransportControls().pause();
                    Result.m392constructorimpl(Unit.INSTANCE);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    Result.m392constructorimpl(ResultKt.createFailure(th));
                }
            }
        }
    }

    private final void rememberPackage(String packageName) {
        if (CollectionsKt.contains(PLAYERS, packageName)) {
            if (!isInstalled(packageName == null ? "" : packageName)) {
                return;
            }
            this.context.getSharedPreferences("jarvis_prefs", 0).edit().putString(PREF_PLAYER, packageName).apply();
        }
    }

    private final boolean isInstalled(String packageName) {
        Object objM392constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            MusicTool $this$isInstalled_u24lambda_u240 = this;
            $this$isInstalled_u24lambda_u240.context.getPackageManager().getPackageInfo(packageName, 0);
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

    private final void dispatchMediaKey(int keyCode) {
        Object systemService = this.context.getSystemService("audio");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
        AudioManager am = (AudioManager) systemService;
        am.dispatchMediaKeyEvent(new KeyEvent(0, keyCode));
        am.dispatchMediaKeyEvent(new KeyEvent(1, keyCode));
    }
}
