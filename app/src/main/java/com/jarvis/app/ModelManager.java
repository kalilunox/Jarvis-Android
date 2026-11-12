package com.jarvis.app;

import android.content.Context;
import android.os.StatFs;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.text.StringsKt;
import kotlin.time.DurationKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;

/* JADX INFO: compiled from: ModelManager.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 92\u00020\u0001:\u0003789B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\fJ\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\fJ\u000e\u0010\u001f\u001a\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\fJ\u000e\u0010 \u001a\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\fJ\u0010\u0010!\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020#H\u0002J\u0010\u0010$\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020#H\u0002J\u0010\u0010%\u001a\u00020\u001d2\u0006\u0010\u001a\u001a\u00020\u0013H\u0002J\u0010\u0010&\u001a\u00020\u000f2\u0006\u0010'\u001a\u00020(H\u0002J\u0010\u0010)\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020#H\u0002J\u0010\u0010*\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020#H\u0002JX\u0010+\u001a\u00020\u001d2\u0006\u0010,\u001a\u00020\f2\u0006\u0010-\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020#26\u0010.\u001a2\u0012\u0013\u0012\u00110(¢\u0006\f\b0\u0012\b\b1\u0012\u0004\b\b(2\u0012\u0013\u0012\u00110(¢\u0006\f\b0\u0012\b\b1\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020\u001d0/H\u0002J\u0018\u00104\u001a\u00020\u001d2\u0006\u00105\u001a\u00020\u00192\u0006\u00106\u001a\u00020\u0019H\u0002R\u0016\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u000f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00130\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00130\u00120\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006:"}, d2 = {"Lcom/jarvis/app/ModelManager;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "app", "kotlin.jvm.PlatformType", "scope", "Lkotlinx/coroutines/CoroutineScope;", "jobs", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lkotlinx/coroutines/Job;", "cancelFlags", "", "_statuses", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/jarvis/app/ModelManager$Status;", "statuses", "Lkotlinx/coroutines/flow/StateFlow;", "getStatuses", "()Lkotlinx/coroutines/flow/StateFlow;", "modelsRoot", "Ljava/io/File;", "status", "id", "refreshAll", "", "download", "cancel", "delete", "onModelReady", VoiceService.EXTRA_ENTRY, "Lcom/jarvis/app/ModelCatalog$Entry;", "probe", "publish", "hasSpace", "needBytes", "", "downloadSingle", "downloadAndExtractTarBz2", "httpDownload", "urlStr", "dest", "onProgress", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "downloaded", "total", "extractTarBz2", "archive", "destRoot", "Status", "State", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ModelManager {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "Jarvis.Models";
    private static volatile ModelManager instance;
    private final MutableStateFlow<Map<String, Status>> _statuses;
    private final Context app;
    private final ConcurrentHashMap<String, Boolean> cancelFlags;
    private final ConcurrentHashMap<String, Job> jobs;
    private final CoroutineScope scope;
    private final StateFlow<Map<String, Status>> statuses;

    /* JADX INFO: compiled from: ModelManager.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/jarvis/app/ModelManager$State;", "", "<init>", "(Ljava/lang/String;I)V", "MISSING", "DOWNLOADING", "READY", "ERROR", "CORRUPT", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum State {
        MISSING,
        DOWNLOADING,
        READY,
        ERROR,
        CORRUPT;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<State> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: compiled from: ModelManager.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ModelCatalog.Kind.values().length];
            try {
                iArr[ModelCatalog.Kind.CHAT_LLM.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ModelCatalog.Kind.TOOL_LLM.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ModelCatalog.Kind.TTS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[ModelCatalog.Kind.ASR.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[ModelCatalog.Kind.SPEAKER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public ModelManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.app = context.getApplicationContext();
        this.scope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getIO()));
        this.jobs = new ConcurrentHashMap<>();
        this.cancelFlags = new ConcurrentHashMap<>();
        Iterable $this$associate$iv = ModelCatalog.INSTANCE.getALL();
        int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associate$iv, 10)), 16);
        Map destination$iv$iv = new LinkedHashMap(capacity$iv);
        for (Object element$iv$iv : $this$associate$iv) {
            ModelCatalog.Entry it = (ModelCatalog.Entry) element$iv$iv;
            Pair pair = TuplesKt.to(it.getId(), probe(it));
            destination$iv$iv.put(pair.getFirst(), pair.getSecond());
        }
        this._statuses = StateFlowKt.MutableStateFlow(destination$iv$iv);
        this.statuses = FlowKt.asStateFlow(this._statuses);
    }

    /* JADX INFO: compiled from: ModelManager.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\nHÆ\u0003J\t\u0010\u001d\u001a\u00020\nHÆ\u0003JE\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016¨\u0006%"}, d2 = {"Lcom/jarvis/app/ModelManager$Status;", "", "id", "", "state", "Lcom/jarvis/app/ModelManager$State;", "progress", "", "message", "bytesDownloaded", "", "bytesTotal", "<init>", "(Ljava/lang/String;Lcom/jarvis/app/ModelManager$State;FLjava/lang/String;JJ)V", "getId", "()Ljava/lang/String;", "getState", "()Lcom/jarvis/app/ModelManager$State;", "getProgress", "()F", "getMessage", "getBytesDownloaded", "()J", "getBytesTotal", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Status {
        private final long bytesDownloaded;
        private final long bytesTotal;
        private final String id;
        private final String message;
        private final float progress;
        private final State state;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final State getState() {
            return this.state;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final float getProgress() {
            return this.progress;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final long getBytesDownloaded() {
            return this.bytesDownloaded;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final long getBytesTotal() {
            return this.bytesTotal;
        }

        public final Status copy(String id, State state, float progress, String message, long bytesDownloaded, long bytesTotal) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(state, "state");
            Intrinsics.checkNotNullParameter(message, "message");
            return new Status(id, state, progress, message, bytesDownloaded, bytesTotal);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Status)) {
                return false;
            }
            Status status = (Status) other;
            return Intrinsics.areEqual(this.id, status.id) && this.state == status.state && Float.compare(this.progress, status.progress) == 0 && Intrinsics.areEqual(this.message, status.message) && this.bytesDownloaded == status.bytesDownloaded && this.bytesTotal == status.bytesTotal;
        }

        public int hashCode() {
            return (((((((((this.id.hashCode() * 31) + this.state.hashCode()) * 31) + Float.hashCode(this.progress)) * 31) + this.message.hashCode()) * 31) + Long.hashCode(this.bytesDownloaded)) * 31) + Long.hashCode(this.bytesTotal);
        }

        public String toString() {
            return "Status(id=" + this.id + ", state=" + this.state + ", progress=" + this.progress + ", message=" + this.message + ", bytesDownloaded=" + this.bytesDownloaded + ", bytesTotal=" + this.bytesTotal + ")";
        }

        public Status(String id, State state, float progress, String message, long bytesDownloaded, long bytesTotal) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(state, "state");
            Intrinsics.checkNotNullParameter(message, "message");
            this.id = id;
            this.state = state;
            this.progress = progress;
            this.message = message;
            this.bytesDownloaded = bytesDownloaded;
            this.bytesTotal = bytesTotal;
        }

        public /* synthetic */ Status(String str, State state, float f, String str2, long j, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, state, (i & 4) != 0 ? 0.0f : f, (i & 8) != 0 ? "" : str2, (i & 16) != 0 ? 0L : j, (i & 32) != 0 ? 0L : j2);
        }

        public final String getId() {
            return this.id;
        }

        public final State getState() {
            return this.state;
        }

        public final float getProgress() {
            return this.progress;
        }

        public final String getMessage() {
            return this.message;
        }

        public final long getBytesDownloaded() {
            return this.bytesDownloaded;
        }

        public final long getBytesTotal() {
            return this.bytesTotal;
        }
    }

    public final StateFlow<Map<String, Status>> getStatuses() {
        return this.statuses;
    }

    public final File modelsRoot() {
        File it = new File(this.app.getExternalFilesDir(null), "models");
        it.mkdirs();
        return it;
    }

    public final Status status(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        Status status = this._statuses.getValue().get(id);
        if (status != null) {
            return status;
        }
        return new Status(id, State.MISSING, 0.0f, null, 0L, 0L, 60, null);
    }

    public final void refreshAll() {
        MutableStateFlow<Map<String, Status>> mutableStateFlow = this._statuses;
        List<ModelCatalog.Entry> all = ModelCatalog.INSTANCE.getALL();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(all, 10)), 16));
        for (ModelCatalog.Entry entry : all) {
            Pair pair = TuplesKt.to(entry.getId(), probe(entry));
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        mutableStateFlow.setValue(linkedHashMap);
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0021  */
    public final void download(String id) {
        boolean z;
        Intrinsics.checkNotNullParameter(id, "id");
        ModelCatalog.Entry entry = ModelCatalog.INSTANCE.byId(id);
        if (entry == null) {
            return;
        }
        Job job = this.jobs.get(id);
        if (job != null) {
            z = job.isActive();
        }
        if (z) {
            return;
        }
        if (!hasSpace(entry.getSizeBytes())) {
            publish(new Status(id, State.ERROR, 0.0f, "Недостаточно места (~" + (entry.getSizeBytes() / ((long) DurationKt.NANOS_IN_MILLIS)) + " МБ).", 0L, 0L, 52, null));
            return;
        }
        this.cancelFlags.put(id, false);
        publish(new Status(id, State.DOWNLOADING, 0.0f, "Скачиваю…", 0L, entry.getSizeBytes(), 16, null));
        this.jobs.put(id, BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AnonymousClass1(entry, this, id, null), 3, null));
    }

    /* JADX INFO: renamed from: com.jarvis.app.ModelManager$download$1, reason: invalid class name */
    /* JADX INFO: compiled from: ModelManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.jarvis.app.ModelManager$download$1", f = "ModelManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ModelCatalog.Entry $entry;
        final /* synthetic */ String $id;
        int label;
        final /* synthetic */ ModelManager this$0;

        /* JADX INFO: renamed from: com.jarvis.app.ModelManager$download$1$WhenMappings */
        /* JADX INFO: compiled from: ModelManager.kt */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[ModelCatalog.PackageKind.values().length];
                try {
                    iArr[ModelCatalog.PackageKind.SINGLE_FILE.ordinal()] = 1;
                } catch (NoSuchFieldError e) {
                }
                try {
                    iArr[ModelCatalog.PackageKind.TAR_BZ2.ordinal()] = 2;
                } catch (NoSuchFieldError e2) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(ModelCatalog.Entry entry, ModelManager modelManager, String str, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$entry = entry;
            this.this$0 = modelManager;
            this.$id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$entry, this.this$0, this.$id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    try {
                        switch (WhenMappings.$EnumSwitchMapping$0[this.$entry.getPackageKind().ordinal()]) {
                            case 1:
                                this.this$0.downloadSingle(this.$entry);
                                break;
                            case 2:
                                this.this$0.downloadAndExtractTarBz2(this.$entry);
                                break;
                            default:
                                throw new NoWhenBranchMatchedException();
                        }
                        Status ready = this.this$0.probe(this.$entry);
                        this.this$0.publish(ready);
                        if (ready.getState() == State.READY) {
                            this.this$0.onModelReady(this.$entry);
                        }
                    } catch (Throwable t) {
                        try {
                            if (Intrinsics.areEqual(this.this$0.cancelFlags.get(this.$id), Boxing.boxBoolean(true))) {
                                this.this$0.publish(new Status(this.$id, State.MISSING, 0.0f, "Отменено", 0L, 0L, 52, null));
                            } else {
                                Log.e(ModelManager.TAG, "download " + this.$id + " failed: " + t.getMessage(), t);
                                ModelManager modelManager = this.this$0;
                                String str = this.$id;
                                State state = State.ERROR;
                                String message = t.getMessage();
                                if (message == null) {
                                    message = t.getClass().getSimpleName();
                                }
                                String str2 = message;
                                Intrinsics.checkNotNull(str2);
                                modelManager.publish(new Status(str, state, 0.0f, str2, 0L, 0L, 52, null));
                            }
                        } finally {
                            this.this$0.jobs.remove(this.$id);
                            this.this$0.cancelFlags.remove(this.$id);
                        }
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final void cancel(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.cancelFlags.put(id, true);
        Job job = this.jobs.get(id);
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
    }

    public final void delete(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        cancel(id);
        ModelCatalog.Entry entry = ModelCatalog.INSTANCE.byId(id);
        if (entry == null) {
            return;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            ModelManager $this$delete_u24lambda_u240 = this;
            new File($this$delete_u24lambda_u240.modelsRoot(), entry.getReadyMarker()).delete();
            if (entry.getPackageKind() == ModelCatalog.PackageKind.SINGLE_FILE) {
                new File($this$delete_u24lambda_u240.modelsRoot(), entry.getRelativePath()).delete();
            } else if (!StringsKt.isBlank(entry.getRelativeDir())) {
                FilesKt.deleteRecursively(new File($this$delete_u24lambda_u240.modelsRoot(), entry.getRelativeDir()));
            }
            new File($this$delete_u24lambda_u240.modelsRoot(), entry.getRelativePath()).delete();
            Result.m392constructorimpl(Boolean.valueOf(new File($this$delete_u24lambda_u240.modelsRoot(), entry.getRelativePath() + ".part").delete()));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        publish(probe(entry));
        if (entry.getKind() == ModelCatalog.Kind.CHAT_LLM) {
            BrainGraph.INSTANCE.shutdownChatLlm();
        }
        if (entry.getKind() == ModelCatalog.Kind.TOOL_LLM) {
            BrainGraph.INSTANCE.shutdownLlmBrain();
            BrainGraph.INSTANCE.invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onModelReady(ModelCatalog.Entry entry) throws Throwable {
        switch (WhenMappings.$EnumSwitchMapping$0[entry.getKind().ordinal()]) {
            case 1:
                BrainGraph.INSTANCE.invalidateChatLlm();
                BrainGraph brainGraph = BrainGraph.INSTANCE;
                Context app = this.app;
                Intrinsics.checkNotNullExpressionValue(app, "app");
                brainGraph.preloadChatModel(app);
                BrainGraph brainGraph2 = BrainGraph.INSTANCE;
                Context app2 = this.app;
                Intrinsics.checkNotNullExpressionValue(app2, "app");
                brainGraph2.warmChatLlm(app2);
                return;
            case 2:
                BrainGraph.INSTANCE.shutdownLlmBrain();
                BrainGraph.INSTANCE.invalidate();
                return;
            case 3:
            case 4:
            case 5:
                return;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Status probe(ModelCatalog.Entry entry) {
        File marker = new File(modelsRoot(), entry.getReadyMarker());
        if (!marker.isFile()) {
            if (Intrinsics.areEqual(entry.getId(), "tts_dmitri")) {
                File alt = new File(modelsRoot(), "vits-piper-ru_RU-dmitri-medium-int8/ru_RU-dmitri-medium.onnx");
                if (alt.isFile()) {
                    return new Status(entry.getId(), State.READY, 1.0f, "Готово", alt.length(), 0L, 32, null);
                }
            }
            return new Status(entry.getId(), State.MISSING, 0.0f, null, 0L, 0L, 60, null);
        }
        if (entry.getPackageKind() == ModelCatalog.PackageKind.SINGLE_FILE && marker.length() < entry.getExpectedMinBytes()) {
            return new Status(entry.getId(), State.CORRUPT, 0.0f, "Файл слишком маленький", 0L, 0L, 52, null);
        }
        return new Status(entry.getId(), State.READY, 1.0f, "Готово", marker.length(), marker.length());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void publish(Status status) {
        MutableStateFlow<Map<String, Status>> mutableStateFlow = this._statuses;
        Map<String, Status> mutableMap = MapsKt.toMutableMap(this._statuses.getValue());
        mutableMap.put(status.getId(), status);
        mutableStateFlow.setValue(mutableMap);
    }

    private final boolean hasSpace(long needBytes) {
        File root = modelsRoot();
        StatFs stat = new StatFs(root.getAbsolutePath());
        long free = stat.getAvailableBlocksLong() * stat.getBlockSizeLong();
        return free > 50000000 + needBytes;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void downloadSingle(final ModelCatalog.Entry entry) throws IOException {
        File dest = new File(modelsRoot(), entry.getRelativePath());
        File parentFile = dest.getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
        }
        File part = new File(dest.getParentFile(), dest.getName() + ".part");
        httpDownload(entry.getUrl(), part, entry, new Function2() { // from class: com.jarvis.app.ModelManager$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ModelManager.downloadSingle$lambda$0(this.f$0, entry, ((Long) obj).longValue(), ((Long) obj2).longValue());
            }
        });
        if (Intrinsics.areEqual((Object) this.cancelFlags.get(entry.getId()), (Object) true)) {
            part.delete();
            return;
        }
        if (part.length() < entry.getExpectedMinBytes()) {
            part.delete();
            throw new IllegalStateException("Скачанный файл слишком маленький");
        }
        if (dest.exists()) {
            dest.delete();
        }
        if (!part.renameTo(dest)) {
            FilesKt.copyTo$default(part, dest, true, 0, 4, null);
            part.delete();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit downloadSingle$lambda$0(ModelManager this$0, ModelCatalog.Entry $entry, long done, long total) {
        float p = total > 0 ? done / total : 0.0f;
        this$0.publish(new Status($entry.getId(), State.DOWNLOADING, RangesKt.coerceIn(p, 0.0f, 1.0f), "Скачиваю…", done, Math.max(total, $entry.getSizeBytes())));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void downloadAndExtractTarBz2(final ModelCatalog.Entry entry) throws IOException {
        Object element$iv;
        File parentFile;
        File it;
        File archive = new File(modelsRoot(), entry.getRelativePath());
        File parentFile2 = archive.getParentFile();
        if (parentFile2 != null) {
            parentFile2.mkdirs();
        }
        File part = new File(archive.getParentFile(), archive.getName() + ".part");
        httpDownload(entry.getUrl(), part, entry, new Function2() { // from class: com.jarvis.app.ModelManager$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ModelManager.downloadAndExtractTarBz2$lambda$0(this.f$0, entry, ((Long) obj).longValue(), ((Long) obj2).longValue());
            }
        });
        if (Intrinsics.areEqual((Object) this.cancelFlags.get(entry.getId()), (Object) true)) {
            part.delete();
            return;
        }
        if (archive.exists()) {
            archive.delete();
        }
        if (!part.renameTo(archive)) {
            FilesKt.copyTo$default(part, archive, true, 0, 4, null);
            part.delete();
        }
        publish(new Status(entry.getId(), State.DOWNLOADING, 0.9f, "Распаковываю…", 0L, 0L, 48, null));
        File outDir = new File(modelsRoot(), entry.getRelativeDir());
        if (outDir.exists()) {
            FilesKt.deleteRecursively(outDir);
        }
        extractTarBz2(archive, modelsRoot());
        archive.delete();
        File marker = new File(modelsRoot(), entry.getReadyMarker());
        if (!marker.isFile()) {
            Sequence $this$firstOrNull$iv = FilesKt.walkTopDown(modelsRoot());
            Iterator<File> it2 = $this$firstOrNull$iv.iterator();
            do {
                if (!it2.hasNext()) {
                    element$iv = null;
                    break;
                } else {
                    element$iv = it2.next();
                    it = (File) element$iv;
                }
            } while (!(Intrinsics.areEqual(it.getName(), marker.getName()) && it.isFile()));
            File found = (File) element$iv;
            if (found != null && !StringsKt.isBlank(entry.getRelativeDir())) {
                File expectedParent = new File(modelsRoot(), entry.getRelativeDir());
                if (!expectedParent.exists() && (parentFile = found.getParentFile()) != null) {
                    parentFile.renameTo(expectedParent);
                }
            }
        }
        if (!new File(modelsRoot(), entry.getReadyMarker()).isFile()) {
            throw new IllegalStateException("После распаковки нет " + entry.getReadyMarker());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit downloadAndExtractTarBz2$lambda$0(ModelManager this$0, ModelCatalog.Entry $entry, long done, long total) {
        float p = total > 0 ? (done / total) * 0.85f : 0.0f;
        this$0.publish(new Status($entry.getId(), State.DOWNLOADING, RangesKt.coerceIn(p, 0.0f, 0.85f), "Скачиваю архив…", done, Math.max(total, $entry.getSizeBytes())));
        return Unit.INSTANCE;
    }

    private final void httpDownload(String urlStr, File dest, ModelCatalog.Entry entry, Function2<? super Long, ? super Long, Unit> onProgress) throws IOException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        int n;
        this = this;
        URL url = new URL(urlStr);
        int redirects = 0;
        URL url2 = url;
        while (!Intrinsics.areEqual((Object) this.cancelFlags.get(entry.getId()), (Object) true)) {
            URLConnection uRLConnectionOpenConnection = url2.openConnection();
            Intrinsics.checkNotNull(uRLConnectionOpenConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
            HttpURLConnection conn = (HttpURLConnection) uRLConnectionOpenConnection;
            conn.setInstanceFollowRedirects(false);
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(120000);
            conn.setRequestProperty("User-Agent", "JarvisAssistant/0.1");
            int code = conn.getResponseCode();
            if (301 <= code && code < 400) {
                String loc = conn.getHeaderField("Location");
                if (loc == null) {
                    throw new IllegalStateException("Не удалось скачать");
                }
                conn.disconnect();
                url2 = new URL(loc);
                redirects++;
                if (redirects > 8) {
                    throw new IllegalStateException("Слишком много редиректов");
                }
            } else {
                if (!(200 <= code && code < 300)) {
                    throw new IllegalStateException("HTTP " + code);
                }
                long it = conn.getContentLengthLong();
                if (it <= 0) {
                    it = entry.getSizeBytes();
                }
                BufferedInputStream bufferedInputStream = new BufferedInputStream(conn.getInputStream());
                try {
                    BufferedInputStream input = bufferedInputStream;
                    try {
                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(dest);
                            try {
                                FileOutputStream output = fileOutputStream;
                                byte[] buf = new byte[65536];
                                long done = 0;
                                long lastEmit = 0;
                                while (!Intrinsics.areEqual((Object) this.cancelFlags.get(entry.getId()), (Object) true) && (n = input.read(buf)) >= 0) {
                                    output.write(buf, 0, n);
                                    url2 = url2;
                                    done += (long) n;
                                    if (done - lastEmit > PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED || done == it) {
                                        try {
                                            try {
                                                onProgress.invoke(Long.valueOf(done), Long.valueOf(it));
                                                lastEmit = done;
                                            } catch (Throwable th4) {
                                                th3 = th4;
                                                th2 = th3;
                                                try {
                                                    throw th2;
                                                } catch (Throwable th5) {
                                                    CloseableKt.closeFinally(fileOutputStream, th2);
                                                    throw th5;
                                                }
                                            }
                                        } catch (Throwable th6) {
                                            th3 = th6;
                                            th2 = th3;
                                            throw th2;
                                        }
                                    }
                                }
                                Unit unit = Unit.INSTANCE;
                                CloseableKt.closeFinally(fileOutputStream, null);
                                Unit unit2 = Unit.INSTANCE;
                                CloseableKt.closeFinally(bufferedInputStream, null);
                                conn.disconnect();
                                return;
                            } catch (Throwable th7) {
                                th2 = th7;
                            }
                        } catch (Throwable th8) {
                            th = th8;
                            try {
                                throw th;
                            } catch (Throwable th9) {
                                CloseableKt.closeFinally(bufferedInputStream, th);
                                throw th9;
                            }
                        }
                    } catch (Throwable th10) {
                        th = th10;
                        th = th;
                        throw th;
                    }
                } catch (Throwable th11) {
                    th = th11;
                }
            }
        }
    }

    private final void extractTarBz2(File archive, File destRoot) throws IOException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(archive));
        try {
            BufferedInputStream bin = bufferedInputStream;
            int i = 0;
            BZip2CompressorInputStream bZip2CompressorInputStream = new BZip2CompressorInputStream(bin);
            try {
                BZip2CompressorInputStream bz = bZip2CompressorInputStream;
                TarArchiveInputStream tarArchiveInputStream = new TarArchiveInputStream(bz);
                try {
                    TarArchiveInputStream tar = tarArchiveInputStream;
                    while (true) {
                        TarArchiveEntry entry = tar.getNextEntry();
                        if (entry == null) {
                            try {
                                Unit unit = Unit.INSTANCE;
                                try {
                                    CloseableKt.closeFinally(tarArchiveInputStream, null);
                                    Unit unit2 = Unit.INSTANCE;
                                    CloseableKt.closeFinally(bZip2CompressorInputStream, null);
                                    Unit unit3 = Unit.INSTANCE;
                                    CloseableKt.closeFinally(bufferedInputStream, null);
                                    return;
                                } catch (Throwable th4) {
                                    th = th4;
                                    try {
                                        throw th;
                                    } catch (Throwable th5) {
                                        try {
                                            CloseableKt.closeFinally(bZip2CompressorInputStream, th);
                                            throw th5;
                                        } catch (Throwable th6) {
                                            th = th6;
                                            Throwable th7 = th;
                                            try {
                                                throw th7;
                                            } catch (Throwable th8) {
                                                CloseableKt.closeFinally(bufferedInputStream, th7);
                                                throw th8;
                                            }
                                        }
                                    }
                                }
                            } catch (Throwable th9) {
                                th = th9;
                                th2 = th;
                                throw th2;
                            }
                        }
                        try {
                            File out = new File(destRoot, entry.getName());
                            if (entry.isDirectory()) {
                                try {
                                    out.mkdirs();
                                } catch (Throwable th10) {
                                    th = th10;
                                }
                            } else {
                                File parentFile = out.getParentFile();
                                if (parentFile != null) {
                                    parentFile.mkdirs();
                                }
                                FileOutputStream fileOutputStream = new FileOutputStream(out);
                                try {
                                    FileOutputStream fos = fileOutputStream;
                                    BufferedInputStream bin2 = bin;
                                    int i2 = i;
                                    try {
                                        Long.valueOf(ByteStreamsKt.copyTo$default(tar, fos, 0, 2, null));
                                        try {
                                            CloseableKt.closeFinally(fileOutputStream, null);
                                            bin = bin2;
                                            i = i2;
                                        } catch (Throwable th11) {
                                            th2 = th11;
                                        }
                                    } catch (Throwable th12) {
                                        th3 = th12;
                                        try {
                                            throw th3;
                                        } catch (Throwable th13) {
                                            CloseableKt.closeFinally(fileOutputStream, th3);
                                            throw th13;
                                        }
                                    }
                                } catch (Throwable th14) {
                                    th3 = th14;
                                }
                            }
                        } catch (Throwable th15) {
                            th = th15;
                            th2 = th;
                        }
                        th2 = th;
                        try {
                            throw th2;
                        } catch (Throwable th16) {
                            try {
                                CloseableKt.closeFinally(tarArchiveInputStream, th2);
                                throw th16;
                            } catch (Throwable th17) {
                                th = th17;
                                throw th;
                            }
                        }
                    }
                } catch (Throwable th18) {
                    th = th18;
                }
            } catch (Throwable th19) {
                th = th19;
            }
        } catch (Throwable th20) {
            th = th20;
        }
    }

    /* JADX INFO: compiled from: ModelManager.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/jarvis/app/ModelManager$Companion;", "", "<init>", "()V", "TAG", "", "instance", "Lcom/jarvis/app/ModelManager;", "get", "context", "Landroid/content/Context;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ModelManager get(Context context) {
            ModelManager it;
            Intrinsics.checkNotNullParameter(context, "context");
            ModelManager modelManager = ModelManager.instance;
            if (modelManager != null) {
                return modelManager;
            }
            synchronized (this) {
                it = ModelManager.instance;
                if (it == null) {
                    Context applicationContext = context.getApplicationContext();
                    Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                    it = new ModelManager(applicationContext);
                    Companion companion = ModelManager.INSTANCE;
                    ModelManager.instance = it;
                }
            }
            return it;
        }
    }
}
