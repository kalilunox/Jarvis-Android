package com.jarvis.core.storage;

import android.content.ContentValues;
import android.database.Cursor;
import com.jarvis.app.VoiceService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ConversationRepository.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001%B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J0\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\u000eJ&\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000e2\b\b\u0002\u0010\u0015\u001a\u00020\u000eJ\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u0017J\u0010\u0010\u0019\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u000eJ\u0006\u0010\u001a\u001a\u00020\u0007J\u0006\u0010\u001b\u001a\u00020\u0013J\u0006\u0010\u001c\u001a\u00020\u0013J\u000e\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u000eJ\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0 J\u000e\u0010!\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\tJ\u0010\u0010#\u001a\u00020\u00132\u0006\u0010$\u001a\u00020\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/jarvis/core/storage/ConversationRepository;", "", "helper", "Lcom/jarvis/core/storage/JarvisDb;", "<init>", "(Lcom/jarvis/core/storage/JarvisDb;)V", "append", "", "id", "", "role", "maskedText", "sessionId", "createdAt", "", "loadRecent", "", "Lcom/jarvis/core/storage/ConversationRepository$Message;", "limit", "", "maxAgeMs", "now", "setKeep", "", "keep", "expire", "clear", "count", "keepCount", "countSince", "sinceMillis", "boundaries", "Lkotlin/Pair;", "containsText", "needle", "countWhere", "where", "Message", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ConversationRepository {
    private final JarvisDb helper;

    public ConversationRepository(JarvisDb helper) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        this.helper = helper;
    }

    /* JADX INFO: compiled from: ConversationRepository.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\bHÆ\u0003J\t\u0010\u001b\u001a\u00020\nHÆ\u0003JE\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\n2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\""}, d2 = {"Lcom/jarvis/core/storage/ConversationRepository$Message;", "", "id", "", "sessionId", "role", VoiceService.EXTRA_TEXT, "createdAt", "", "keep", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JZ)V", "getId", "()Ljava/lang/String;", "getSessionId", "getRole", "getText", "getCreatedAt", "()J", "getKeep", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Message {
        private final long createdAt;
        private final String id;
        private final boolean keep;
        private final String role;
        private final String sessionId;
        private final String text;

        public static /* synthetic */ Message copy$default(Message message, String str, String str2, String str3, String str4, long j, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                str = message.id;
            }
            if ((i & 2) != 0) {
                str2 = message.sessionId;
            }
            String str5 = str2;
            if ((i & 4) != 0) {
                str3 = message.role;
            }
            String str6 = str3;
            if ((i & 8) != 0) {
                str4 = message.text;
            }
            String str7 = str4;
            if ((i & 16) != 0) {
                j = message.createdAt;
            }
            long j2 = j;
            if ((i & 32) != 0) {
                z = message.keep;
            }
            return message.copy(str, str5, str6, str7, j2, z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSessionId() {
            return this.sessionId;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getRole() {
            return this.role;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getText() {
            return this.text;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final long getCreatedAt() {
            return this.createdAt;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final boolean getKeep() {
            return this.keep;
        }

        public final Message copy(String id, String sessionId, String role, String text, long createdAt, boolean keep) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(sessionId, "sessionId");
            Intrinsics.checkNotNullParameter(role, "role");
            Intrinsics.checkNotNullParameter(text, "text");
            return new Message(id, sessionId, role, text, createdAt, keep);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Message)) {
                return false;
            }
            Message message = (Message) other;
            return Intrinsics.areEqual(this.id, message.id) && Intrinsics.areEqual(this.sessionId, message.sessionId) && Intrinsics.areEqual(this.role, message.role) && Intrinsics.areEqual(this.text, message.text) && this.createdAt == message.createdAt && this.keep == message.keep;
        }

        public int hashCode() {
            return (((((((((this.id.hashCode() * 31) + this.sessionId.hashCode()) * 31) + this.role.hashCode()) * 31) + this.text.hashCode()) * 31) + Long.hashCode(this.createdAt)) * 31) + Boolean.hashCode(this.keep);
        }

        public String toString() {
            return "Message(id=" + this.id + ", sessionId=" + this.sessionId + ", role=" + this.role + ", text=" + this.text + ", createdAt=" + this.createdAt + ", keep=" + this.keep + ")";
        }

        public Message(String id, String sessionId, String role, String text, long createdAt, boolean keep) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(sessionId, "sessionId");
            Intrinsics.checkNotNullParameter(role, "role");
            Intrinsics.checkNotNullParameter(text, "text");
            this.id = id;
            this.sessionId = sessionId;
            this.role = role;
            this.text = text;
            this.createdAt = createdAt;
            this.keep = keep;
        }

        public final String getId() {
            return this.id;
        }

        public final String getSessionId() {
            return this.sessionId;
        }

        public final String getRole() {
            return this.role;
        }

        public final String getText() {
            return this.text;
        }

        public final long getCreatedAt() {
            return this.createdAt;
        }

        public final boolean getKeep() {
            return this.keep;
        }
    }

    public static /* synthetic */ void append$default(ConversationRepository conversationRepository, String str, String str2, String str3, String str4, long j, int i, Object obj) {
        long jCurrentTimeMillis;
        if ((i & 16) == 0) {
            jCurrentTimeMillis = j;
        } else {
            jCurrentTimeMillis = System.currentTimeMillis();
        }
        conversationRepository.append(str, str2, str3, str4, jCurrentTimeMillis);
    }

    public final void append(String id, String role, String maskedText, String sessionId, long createdAt) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(role, "role");
        Intrinsics.checkNotNullParameter(maskedText, "maskedText");
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        ContentValues $this$append_u24lambda_u240 = new ContentValues();
        $this$append_u24lambda_u240.put("id", id);
        $this$append_u24lambda_u240.put("session_id", sessionId);
        $this$append_u24lambda_u240.put("role", role);
        $this$append_u24lambda_u240.put(VoiceService.EXTRA_TEXT, maskedText);
        $this$append_u24lambda_u240.put("created_at", Long.valueOf(createdAt));
        $this$append_u24lambda_u240.put("keep", (Integer) 0);
        this.helper.getWritableDatabase().insertWithOnConflict(JarvisSchema.TABLE_MESSAGES, null, $this$append_u24lambda_u240, 4);
    }

    public static /* synthetic */ List loadRecent$default(ConversationRepository conversationRepository, int i, long j, long j2, int i2, Object obj) {
        long jCurrentTimeMillis;
        if ((i2 & 4) == 0) {
            jCurrentTimeMillis = j2;
        } else {
            jCurrentTimeMillis = System.currentTimeMillis();
        }
        return conversationRepository.loadRecent(i, j, jCurrentTimeMillis);
    }

    public final List<Message> loadRecent(int limit, long maxAgeMs, long now) throws IOException {
        String str = "getString(...)";
        if (limit <= 0) {
            return CollectionsKt.emptyList();
        }
        long from = now - maxAgeMs;
        ArrayList out = new ArrayList(limit);
        int i = 2;
        int i2 = 0;
        int i3 = 1;
        Cursor cursorRawQuery = this.helper.getReadableDatabase().rawQuery("SELECT id, session_id, role, text, created_at, keep FROM conversation_messages WHERE created_at >= ? ORDER BY created_at DESC, rowid DESC LIMIT ?", new String[]{String.valueOf(from), String.valueOf(limit)});
        try {
            Cursor c = cursorRawQuery;
            while (c.moveToNext()) {
                ArrayList arrayList = out;
                String string = c.getString(i2);
                Intrinsics.checkNotNullExpressionValue(string, str);
                String string2 = c.getString(i3);
                Intrinsics.checkNotNullExpressionValue(string2, str);
                String string3 = c.getString(i);
                Intrinsics.checkNotNullExpressionValue(string3, str);
                String string4 = c.getString(3);
                Intrinsics.checkNotNullExpressionValue(string4, str);
                arrayList.add(new Message(string, string2, string3, string4, c.getLong(4), c.getInt(5) != 0));
                str = str;
                i = 2;
                i2 = 0;
                i3 = 1;
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            CollectionsKt.reverse(out);
            return out;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    public final boolean setKeep(String id, boolean keep) {
        Intrinsics.checkNotNullParameter(id, "id");
        ContentValues contentValues = new ContentValues();
        contentValues.put("keep", Integer.valueOf(keep ? 1 : 0));
        return this.helper.getWritableDatabase().update(JarvisSchema.TABLE_MESSAGES, contentValues, "id = ?", new String[]{id}) > 0;
    }

    public static /* synthetic */ int expire$default(ConversationRepository conversationRepository, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = System.currentTimeMillis();
        }
        return conversationRepository.expire(j);
    }

    public final int expire(long now) {
        return this.helper.getWritableDatabase().delete(JarvisSchema.TABLE_MESSAGES, "keep = 0 AND created_at < ?", new String[]{String.valueOf(RetentionPolicy.cutoff$default(RetentionPolicy.INSTANCE, now, 0L, 2, null))});
    }

    public final void clear() {
        this.helper.getWritableDatabase().delete(JarvisSchema.TABLE_MESSAGES, null, null);
    }

    public final int count() {
        return countWhere("1 = 1");
    }

    public final int keepCount() {
        return countWhere("keep = 1");
    }

    public final int countSince(long sinceMillis) {
        return countWhere("created_at >= " + sinceMillis);
    }

    public final Pair<Long, Long> boundaries() throws IOException {
        Cursor cursorRawQuery = this.helper.getReadableDatabase().rawQuery("SELECT MIN(created_at), MAX(created_at) FROM conversation_messages", null);
        try {
            Cursor c = cursorRawQuery;
            long j = 0;
            if (!c.moveToFirst()) {
                Pair<Long, Long> pair = TuplesKt.to(0L, 0L);
                CloseableKt.closeFinally(cursorRawQuery, null);
                return pair;
            }
            long oldest = c.isNull(0) ? 0L : c.getLong(0);
            if (!c.isNull(1)) {
                j = c.getLong(1);
            }
            long newest = j;
            Pair<Long, Long> pair2 = TuplesKt.to(Long.valueOf(oldest), Long.valueOf(newest));
            CloseableKt.closeFinally(cursorRawQuery, null);
            return pair2;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    public final boolean containsText(String needle) throws IOException {
        Intrinsics.checkNotNullParameter(needle, "needle");
        if (needle.length() == 0) {
            return false;
        }
        Cursor cursorRawQuery = this.helper.getReadableDatabase().rawQuery("SELECT 1 FROM conversation_messages WHERE text LIKE ? LIMIT 1", new String[]{"%" + needle + "%"});
        try {
            Cursor c = cursorRawQuery;
            boolean zMoveToFirst = c.moveToFirst();
            CloseableKt.closeFinally(cursorRawQuery, null);
            return zMoveToFirst;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final int countWhere(String where) throws IOException {
        Cursor cursorRawQuery = this.helper.getReadableDatabase().rawQuery("SELECT COUNT(*) FROM conversation_messages WHERE " + where, null);
        try {
            Cursor c = cursorRawQuery;
            int i = c.moveToFirst() ? c.getInt(0) : 0;
            CloseableKt.closeFinally(cursorRawQuery, null);
            return i;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }
}
