package com.jarvis.tools;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import com.google.android.gms.actions.SearchIntents;
import com.jarvis.core.common.Tool;
import com.jarvis.core.common.model.OnlineRequirement;
import com.jarvis.core.common.model.ParameterType;
import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.common.model.ToolParameter;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.concurrent.AbstractCircuitBreaker;

/* JADX INFO: compiled from: FilesTool.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0012B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Lcom/jarvis/tools/FilesTool;", "Lcom/jarvis/core/common/Tool;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "definition", "Lcom/jarvis/core/common/model/ToolDefinition;", "getDefinition", "()Lcom/jarvis/core/common/model/ToolDefinition;", "execute", "Lcom/jarvis/core/common/model/ToolResult;", "call", "Lcom/jarvis/core/common/model/ToolCall;", "findMedia", "Lcom/jarvis/tools/FilesTool$Hit;", SearchIntents.EXTRA_QUERY, "", "Hit", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FilesTool implements Tool {
    private final Context context;
    private final ToolDefinition definition;

    public FilesTool(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.definition = new ToolDefinition("files.search", "Файлы", "Ищет медиафайл по имени и открывает его", CollectionsKt.listOf((Object[]) new ToolParameter[]{new ToolParameter(SearchIntents.EXTRA_QUERY, "Часть имени файла", ParameterType.STRING, true, null, 16, null), new ToolParameter("action", "open или share", ParameterType.ENUM, false, CollectionsKt.listOf((Object[]) new String[]{AbstractCircuitBreaker.PROPERTY_NAME, "share"}))}), RiskLevel.LOW, null, OnlineRequirement.LOCAL_ONLY, null, 160, null);
    }

    @Override // com.jarvis.core.common.Tool
    public ToolDefinition getDefinition() {
        return this.definition;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0072  */
    @Override // com.jarvis.core.common.Tool
    public ToolResult execute(ToolCall call) throws IOException {
        String action;
        Intent $this$execute_u24lambda_u240;
        String string;
        String string2;
        Intrinsics.checkNotNullParameter(call, "call");
        Object obj = call.getArguments().get(SearchIntents.EXTRA_QUERY);
        String query = (obj == null || (string2 = obj.toString()) == null) ? null : StringsKt.trim((CharSequence) string2).toString();
        if (query == null) {
            query = "";
        }
        if (StringsKt.isBlank(query)) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.AMBIGUOUS, "Что искать?", null, 0L, 48, null);
        }
        Object obj2 = call.getArguments().get("action");
        if (obj2 == null || (string = obj2.toString()) == null) {
            action = AbstractCircuitBreaker.PROPERTY_NAME;
        } else {
            action = string.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(action, "toLowerCase(...)");
            if (action == null) {
                action = AbstractCircuitBreaker.PROPERTY_NAME;
            }
        }
        Hit hit = findMedia(query);
        if (hit == null) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.FAILED, "Файл «" + query + "» не найден.", null, 0L, 48, null);
        }
        try {
            if (Intrinsics.areEqual(action, "share")) {
                $this$execute_u24lambda_u240 = new Intent("android.intent.action.SEND");
                $this$execute_u24lambda_u240.setType(hit.getMime());
                $this$execute_u24lambda_u240.putExtra("android.intent.extra.STREAM", hit.getUri());
                $this$execute_u24lambda_u240.addFlags(268435457);
            } else {
                $this$execute_u24lambda_u240 = new Intent("android.intent.action.VIEW");
                $this$execute_u24lambda_u240.setDataAndType(hit.getUri(), hit.getMime());
                $this$execute_u24lambda_u240.addFlags(268435457);
            }
            this.context.startActivity(Intent.createChooser($this$execute_u24lambda_u240, hit.getName()).addFlags(268435456));
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.SUCCESS, (Intrinsics.areEqual(action, "share") ? new StringBuilder().append("Открыл отправку «").append(hit.getName()).append("».") : new StringBuilder().append("Открываю «").append(hit.getName()).append("».")).toString(), MapsKt.mapOf(TuplesKt.to("name", hit.getName()), TuplesKt.to("uri", hit.getUri().toString())), 0L, 32, null);
        } catch (Throwable t) {
            return new ToolResult(call.getRequestId(), getDefinition().getId(), ToolResultStatus.FAILED, t.getMessage(), null, 0L, 48, null);
        }
    }

    /* JADX INFO: compiled from: FilesTool.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/jarvis/tools/FilesTool$Hit;", "", "name", "", "uri", "Landroid/net/Uri;", "mime", "<init>", "(Ljava/lang/String;Landroid/net/Uri;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getUri", "()Landroid/net/Uri;", "getMime", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "tools_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final /* data */ class Hit {
        private final String mime;
        private final String name;
        private final Uri uri;

        public static /* synthetic */ Hit copy$default(Hit hit, String str, Uri uri, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = hit.name;
            }
            if ((i & 2) != 0) {
                uri = hit.uri;
            }
            if ((i & 4) != 0) {
                str2 = hit.mime;
            }
            return hit.copy(str, uri, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Uri getUri() {
            return this.uri;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getMime() {
            return this.mime;
        }

        public final Hit copy(String name, Uri uri, String mime) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(uri, "uri");
            Intrinsics.checkNotNullParameter(mime, "mime");
            return new Hit(name, uri, mime);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Hit)) {
                return false;
            }
            Hit hit = (Hit) other;
            return Intrinsics.areEqual(this.name, hit.name) && Intrinsics.areEqual(this.uri, hit.uri) && Intrinsics.areEqual(this.mime, hit.mime);
        }

        public int hashCode() {
            return (((this.name.hashCode() * 31) + this.uri.hashCode()) * 31) + this.mime.hashCode();
        }

        public String toString() {
            return "Hit(name=" + this.name + ", uri=" + this.uri + ", mime=" + this.mime + ")";
        }

        public Hit(String name, Uri uri, String mime) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(uri, "uri");
            Intrinsics.checkNotNullParameter(mime, "mime");
            this.name = name;
            this.uri = uri;
            this.mime = mime;
        }

        public final String getMime() {
            return this.mime;
        }

        public final String getName() {
            return this.name;
        }

        public final Uri getUri() {
            return this.uri;
        }
    }

    private final Hit findMedia(String query) throws IOException {
        String q = "%" + query + "%";
        List<Pair> collections = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*"), TuplesKt.to(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, "video/*"), TuplesKt.to(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, "audio/*"), TuplesKt.to(MediaStore.Files.getContentUri("external"), "*/*")});
        for (Pair pair : collections) {
            Uri uri = (Uri) pair.component1();
            String mime = (String) pair.component2();
            Cursor cursor = this.context.getContentResolver().query(uri, new String[]{"_id", "_display_name", "mime_type"}, "_display_name LIKE ?", new String[]{q}, "date_added DESC");
            if (cursor != null) {
                Cursor cursor2 = cursor;
                try {
                    Cursor it = cursor2;
                    if (it.moveToFirst()) {
                        long id = it.getLong(0);
                        String name = it.getString(1);
                        if (name == null) {
                            name = "";
                        }
                        String m = it.getString(2);
                        if (m == null) {
                            m = mime;
                        }
                        Uri uriWithAppendedPath = Uri.withAppendedPath(uri, String.valueOf(id));
                        Intrinsics.checkNotNullExpressionValue(uriWithAppendedPath, "withAppendedPath(...)");
                        Hit hit = new Hit(name, uriWithAppendedPath, m);
                        CloseableKt.closeFinally(cursor2, null);
                        return hit;
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursor2, null);
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(cursor2, th);
                        throw th2;
                    }
                }
            }
        }
        return null;
    }
}
