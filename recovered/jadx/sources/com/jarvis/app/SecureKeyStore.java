package com.jarvis.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Log;
import com.jarvis.core.llm.CloudKeyValidator;
import java.security.Key;
import java.security.KeyStore;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SecureKeyStore.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bJ\u0010\u0010\r\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\u0010\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0012J\n\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/jarvis/app/SecureKeyStore;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "prefs", "Landroid/content/SharedPreferences;", "setKey", "", "providerId", "", "key", "getKey", "hasKey", "", "clearKey", "providersWithKeys", "", "getOrCreateSecretKey", "Ljavax/crypto/SecretKey;", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SecureKeyStore {

    @Deprecated
    public static final String ANDROID_KEYSTORE = "AndroidKeyStore";
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final int GCM_TAG_LENGTH = 128;

    @Deprecated
    public static final String KEY_ALIAS = "JarvisMasterKey";

    @Deprecated
    public static final String PREFS_NAME = "jarvis_keys";

    @Deprecated
    public static final String TAG = "Jarvis.SecureKeyStore";

    @Deprecated
    public static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private final SharedPreferences prefs;

    public SecureKeyStore(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(PREFS_NAME, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(...)");
        this.prefs = sharedPreferences;
    }

    public final void setKey(String providerId, String key) {
        Object objM392constructorimpl;
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        Intrinsics.checkNotNullParameter(key, "key");
        String id = CloudKeyValidator.INSTANCE.normalizeProviderId(providerId);
        if (StringsKt.isBlank(id)) {
            return;
        }
        String clean = CloudKeyValidator.INSTANCE.sanitizeKey(key);
        if (CloudKeyValidator.INSTANCE.isStubKey(clean)) {
            clearKey(id);
            return;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            SecureKeyStore $this$setKey_u24lambda_u240 = this;
            SecretKey secretKey = $this$setKey_u24lambda_u240.getOrCreateSecretKey();
            if (secretKey == null) {
                return;
            }
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(1, secretKey);
            CloudKeyValidator cloudKeyValidator = CloudKeyValidator.INSTANCE;
            byte[] iv = cipher.getIV();
            Intrinsics.checkNotNullExpressionValue(iv, "getIV(...)");
            byte[] bytes = clean.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            byte[] bArrDoFinal = cipher.doFinal(bytes);
            Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "doFinal(...)");
            String envelope = cloudKeyValidator.encodeEnvelope(iv, bArrDoFinal);
            $this$setKey_u24lambda_u240.prefs.edit().putString(id, envelope).apply();
            objM392constructorimpl = Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m395exceptionOrNullimpl(objM392constructorimpl) != null) {
            Log.w(TAG, "Unable to encrypt provider key");
        }
    }

    public final String getKey(String providerId) {
        Pair<byte[], byte[]> pairDecodeEnvelope;
        Object objM392constructorimpl;
        String str;
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        String id = CloudKeyValidator.INSTANCE.normalizeProviderId(providerId);
        Object obj = null;
        if (StringsKt.isBlank(id) || (pairDecodeEnvelope = CloudKeyValidator.INSTANCE.decodeEnvelope(this.prefs.getString(id, null))) == null) {
            return null;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            SecureKeyStore $this$getKey_u24lambda_u240 = this;
            byte[] iv = pairDecodeEnvelope.component1();
            byte[] ciphertext = pairDecodeEnvelope.component2();
            SecretKey secretKey = $this$getKey_u24lambda_u240.getOrCreateSecretKey();
            if (secretKey == null) {
                str = null;
            } else {
                Cipher cipher = Cipher.getInstance(TRANSFORMATION);
                cipher.init(2, secretKey, new GCMParameterSpec(128, iv));
                byte[] bArrDoFinal = cipher.doFinal(ciphertext);
                Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "doFinal(...)");
                str = new String(bArrDoFinal, Charsets.UTF_8);
            }
            objM392constructorimpl = Result.m392constructorimpl(str);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m395exceptionOrNullimpl(objM392constructorimpl) == null) {
            obj = objM392constructorimpl;
        } else {
            Log.w(TAG, "Unable to decrypt provider key");
        }
        return (String) obj;
    }

    public final boolean hasKey(String providerId) {
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        String id = CloudKeyValidator.INSTANCE.normalizeProviderId(providerId);
        return (StringsKt.isBlank(id) || CloudKeyValidator.INSTANCE.decodeEnvelope(this.prefs.getString(id, null)) == null) ? false : true;
    }

    public final void clearKey(String providerId) {
        Intrinsics.checkNotNullParameter(providerId, "providerId");
        String id = CloudKeyValidator.INSTANCE.normalizeProviderId(providerId);
        if (!StringsKt.isBlank(id)) {
            this.prefs.edit().remove(id).apply();
        }
    }

    public final Set<String> providersWithKeys() {
        Iterable $this$filterTo$iv = this.prefs.getAll().keySet();
        Collection destination$iv = (Set) new LinkedHashSet();
        for (Object element$iv : $this$filterTo$iv) {
            String it = (String) element$iv;
            if (CloudKeyValidator.INSTANCE.decodeEnvelope(this.prefs.getString(it, null)) != null) {
                destination$iv.add(element$iv);
            }
        }
        return (Set) destination$iv;
    }

    private final SecretKey getOrCreateSecretKey() {
        Object objM392constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            SecureKeyStore secureKeyStore = this;
            KeyStore keyStore = KeyStore.getInstance(ANDROID_KEYSTORE);
            keyStore.load(null);
            Key key = keyStore.getKey(KEY_ALIAS, null);
            SecretKey secretKeyGenerateKey = key instanceof SecretKey ? (SecretKey) key : null;
            if (secretKeyGenerateKey == null) {
                KeyGenerator generator = KeyGenerator.getInstance("AES", ANDROID_KEYSTORE);
                generator.init(new KeyGenParameterSpec.Builder(KEY_ALIAS, 3).setBlockModes("GCM").setEncryptionPaddings("NoPadding").setKeySize(256).setUserAuthenticationRequired(false).build());
                secretKeyGenerateKey = generator.generateKey();
            }
            objM392constructorimpl = Result.m392constructorimpl(secretKeyGenerateKey);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m395exceptionOrNullimpl(objM392constructorimpl) != null) {
            Log.w(TAG, "Unable to access AndroidKeyStore key");
        }
        return (SecretKey) (Result.m398isFailureimpl(objM392constructorimpl) ? null : objM392constructorimpl);
    }

    /* JADX INFO: compiled from: SecureKeyStore.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/jarvis/app/SecureKeyStore$Companion;", "", "<init>", "()V", "TAG", "", "PREFS_NAME", "ANDROID_KEYSTORE", "KEY_ALIAS", "TRANSFORMATION", "GCM_TAG_LENGTH", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
