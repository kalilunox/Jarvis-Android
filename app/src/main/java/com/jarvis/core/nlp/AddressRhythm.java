package com.jarvis.core.nlp;

import com.jarvis.app.VoiceService;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AddressRhythm.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/jarvis/core/nlp/AddressRhythm;", "", "random", "Lkotlin/random/Random;", "<init>", "(Lkotlin/random/Random;)V", "pause", "", "keep", "", "nextReply", "", "apply", "", VoiceService.EXTRA_TEXT, "Companion", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AddressRhythm {
    private static final int MAX_PAUSE = 3;
    private static final int MIN_PAUSE = 1;
    private boolean keep;
    private int pause;
    private final Random random;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Regex AT_START = new Regex("^\\s*сэр\\s*[,.!]?\\s*", RegexOption.IGNORE_CASE);
    private static final Regex INSIDE = new Regex("[,\\s]+сэр(?![\\p{L}\\p{N}])", RegexOption.IGNORE_CASE);

    /* JADX WARN: Multi-variable type inference failed */
    public AddressRhythm() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public AddressRhythm(Random random) {
        Intrinsics.checkNotNullParameter(random, "random");
        this.random = random;
        this.keep = true;
    }

    public /* synthetic */ AddressRhythm(Random.Companion companion, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Random.INSTANCE : companion);
    }

    public final void nextReply() {
        this.keep = this.pause == 0;
        this.pause = this.keep ? this.random.nextInt(1, 4) : this.pause - 1;
    }

    public final String apply(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        return this.keep ? text : INSTANCE.strip(text);
    }

    /* JADX INFO: compiled from: AddressRhythm.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/jarvis/core/nlp/AddressRhythm$Companion;", "", "<init>", "()V", "MIN_PAUSE", "", "MAX_PAUSE", "AT_START", "Lkotlin/text/Regex;", "INSIDE", "strip", "", VoiceService.EXTRA_TEXT, "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String strip(String text) {
            boolean z;
            Intrinsics.checkNotNullParameter(text, "text");
            String out = StringsKt.trim((CharSequence) AddressRhythm.INSIDE.replace(AddressRhythm.AT_START.replace(text, ""), "")).toString();
            String $this$none$iv = out;
            int i = 0;
            while (true) {
                if (i < $this$none$iv.length()) {
                    char element$iv = $this$none$iv.charAt(i);
                    if (Character.isLetterOrDigit(element$iv)) {
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
                return text;
            }
            if (!(out.length() > 0)) {
                return out;
            }
            StringBuilder sb = new StringBuilder();
            char it = out.charAt(0);
            String strValueOf = String.valueOf(it);
            Intrinsics.checkNotNull(strValueOf, "null cannot be cast to non-null type java.lang.String");
            String upperCase = strValueOf.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            StringBuilder sbAppend = sb.append((Object) upperCase);
            String strSubstring = out.substring(1);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            return sbAppend.append(strSubstring).toString();
        }
    }
}
