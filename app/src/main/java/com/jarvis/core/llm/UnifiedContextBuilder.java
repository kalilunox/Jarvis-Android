package com.jarvis.core.llm;

import com.jarvis.app.VoiceService;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* JADX INFO: compiled from: UnifiedContextBuilder.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002¨\u0006\f"}, d2 = {"Lcom/jarvis/core/llm/UnifiedContextBuilder;", "", "<init>", "()V", "build", "Lcom/jarvis/core/llm/AssembledLlmContext;", "input", "Lcom/jarvis/core/llm/ContextBuildInput;", "sha256Prefix", "", VoiceService.EXTRA_TEXT, "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class UnifiedContextBuilder {

    @Deprecated
    public static final int ABOUT_USER_LIMIT = 8;
    private static final Companion Companion = new Companion(null);

    /* JADX INFO: compiled from: UnifiedContextBuilder.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LlmContextBudget.values().length];
            try {
                iArr[LlmContextBudget.FAST.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[LlmContextBudget.BALANCED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[LlmContextBudget.QUALITY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[LlmContextBudget.REASONING.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0329  */
    /* JADX WARN: Code duplicated, block: B:101:0x032e  */
    /* JADX WARN: Code duplicated, block: B:105:0x0336  */
    /* JADX WARN: Code duplicated, block: B:108:0x0348  */
    /* JADX WARN: Code duplicated, block: B:110:0x035f  */
    /* JADX WARN: Code duplicated, block: B:111:0x0361  */
    /* JADX WARN: Code duplicated, block: B:113:0x0369  */
    /* JADX WARN: Code duplicated, block: B:116:0x0379  */
    /* JADX WARN: Code duplicated, block: B:119:0x038d  */
    /* JADX WARN: Code duplicated, block: B:122:0x039c  */
    /* JADX WARN: Code duplicated, block: B:124:0x03a0  */
    /* JADX WARN: Code duplicated, block: B:125:0x03cd  */
    /* JADX WARN: Code duplicated, block: B:128:0x03df  */
    /* JADX WARN: Code duplicated, block: B:131:0x041e  */
    /* JADX WARN: Code duplicated, block: B:134:0x044b  */
    /* JADX WARN: Code duplicated, block: B:136:0x0471  */
    /* JADX WARN: Code duplicated, block: B:148:0x04cf  */
    /* JADX WARN: Code duplicated, block: B:149:0x04e4  */
    /* JADX WARN: Code duplicated, block: B:152:0x04ef  */
    /* JADX WARN: Code duplicated, block: B:155:0x050c  */
    /* JADX WARN: Code duplicated, block: B:158:0x0527 A[LOOP:6: B:156:0x0521->B:158:0x0527, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:160:0x055b  */
    /* JADX WARN: Code duplicated, block: B:163:0x0566  */
    /* JADX WARN: Code duplicated, block: B:166:0x057f A[LOOP:7: B:164:0x0579->B:166:0x057f, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:168:0x05b3  */
    /* JADX WARN: Code duplicated, block: B:171:0x0609  */
    /* JADX WARN: Code duplicated, block: B:172:0x060b  */
    /* JADX WARN: Code duplicated, block: B:175:0x0637  */
    /* JADX WARN: Code duplicated, block: B:177:0x063d  */
    /* JADX WARN: Code duplicated, block: B:178:0x0640  */
    /* JADX WARN: Code duplicated, block: B:179:0x0643  */
    /* JADX WARN: Code duplicated, block: B:180:0x0646  */
    /* JADX WARN: Code duplicated, block: B:184:0x0668 A[LOOP:8: B:182:0x0662->B:184:0x0668, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:202:0x028d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:63:0x022c  */
    /* JADX WARN: Code duplicated, block: B:66:0x0231  */
    /* JADX WARN: Code duplicated, block: B:70:0x024f  */
    /* JADX WARN: Code duplicated, block: B:72:0x0267  */
    /* JADX WARN: Code duplicated, block: B:73:0x027c  */
    /* JADX WARN: Code duplicated, block: B:75:0x0286  */
    /* JADX WARN: Code duplicated, block: B:80:0x02ac  */
    /* JADX WARN: Code duplicated, block: B:83:0x02b4  */
    /* JADX WARN: Code duplicated, block: B:86:0x02ce  */
    /* JADX WARN: Code duplicated, block: B:88:0x02d4  */
    /* JADX WARN: Code duplicated, block: B:89:0x02d7  */
    /* JADX WARN: Code duplicated, block: B:90:0x02da  */
    /* JADX WARN: Code duplicated, block: B:91:0x02dd  */
    /* JADX WARN: Code duplicated, block: B:94:0x02f1  */
    /* JADX WARN: Code duplicated, block: B:97:0x031b  */
    /*  JADX ERROR: JadxRuntimeException in pass: IfRegionVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r11v43 'element$iv'  java.lang.Object, still in use, count: 2, list:
          (r11v43 'element$iv' java.lang.Object) from 0x0212: PHI (r11 I:??) = (r11v5 'element$iv' java.lang.Object), (r11v43 'element$iv' java.lang.Object) binds: [B:57:0x0211, B:198:0x0212] A[DONT_GENERATE, DONT_INLINE]
          (r11v43 'element$iv' java.lang.Object A[D('element$iv' java.lang.Object)]) from 0x0205: CHECK_CAST (java.lang.String) (r11v43 'element$iv' java.lang.Object A[D('element$iv' java.lang.Object)])
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:93)
        	at jadx.core.dex.visitors.regions.TernaryMod.makeTernaryInsn(TernaryMod.java:132)
        	at jadx.core.dex.visitors.regions.TernaryMod.processRegion(TernaryMod.java:67)
        	at jadx.core.dex.visitors.regions.TernaryMod.enterRegion(TernaryMod.java:50)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:96)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:27)
        	at jadx.core.dex.visitors.regions.TernaryMod.process(TernaryMod.java:36)
        	at jadx.core.dex.visitors.regions.IfRegionVisitor.process(IfRegionVisitor.java:44)
        	at jadx.core.dex.visitors.regions.IfRegionVisitor.visit(IfRegionVisitor.java:30)
        */
    public final com.jarvis.core.llm.AssembledLlmContext build(com.jarvis.core.llm.ContextBuildInput r53) {
        /*
            Method dump skipped, instruction units count: 1794
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jarvis.core.llm.UnifiedContextBuilder.build(com.jarvis.core.llm.ContextBuildInput):com.jarvis.core.llm.AssembledLlmContext");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence build$lambda$6(MemorySnippet it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return StringsKt.take(it.getText(), 48);
    }

    private final String sha256Prefix(String text) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.SHA_256);
        byte[] bytes = text.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] d = messageDigest.digest(bytes);
        Intrinsics.checkNotNull(d);
        return CollectionsKt.joinToString$default(ArraysKt.take(d, 4), "", null, null, 0, null, new Function1() { // from class: com.jarvis.core.llm.UnifiedContextBuilder$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UnifiedContextBuilder.sha256Prefix$lambda$0(((Byte) obj).byteValue());
            }
        }, 30, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence sha256Prefix$lambda$0(byte it) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(it)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    /* JADX INFO: compiled from: UnifiedContextBuilder.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/jarvis/core/llm/UnifiedContextBuilder$Companion;", "", "<init>", "()V", "ABOUT_USER_LIMIT", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
