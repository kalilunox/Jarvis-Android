package com.jarvis.core.nlp;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.jarvis.app.VoiceService;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Translator.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001dB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u0006J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0019\u001a\u00020\u0006H\u0002J\u0016\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0006J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001b\u001a\u00020\u0006R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082T¢\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u001e"}, d2 = {"Lcom/jarvis/core/nlp/Translator;", "", "<init>", "()V", "TARGETS", "", "", "Lcom/jarvis/core/nlp/TranslationTarget;", "ONCE", "Lkotlin/text/Regex;", "ONCE_TAIL", "START", "STOP", "", "MIN_CYRILLIC_SHARE", "", "active", "getActive", "()Lcom/jarvis/core/nlp/TranslationTarget;", "setActive", "(Lcom/jarvis/core/nlp/TranslationTarget;)V", "parse", "Lcom/jarvis/core/nlp/Translator$Request;", "phrase", TypedValues.AttributesType.S_TARGET, "word", "prompt", VoiceService.EXTRA_TEXT, "speechLocale", "Request", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Translator {
    private static final double MIN_CYRILLIC_SHARE = 0.3d;
    private static volatile TranslationTarget active;
    public static final Translator INSTANCE = new Translator();
    private static final Map<String, TranslationTarget> TARGETS = MapsKt.mapOf(TuplesKt.to("английск", new TranslationTarget("английский", "en-US")), TuplesKt.to("немецк", new TranslationTarget("немецкий", "de-DE")), TuplesKt.to("французск", new TranslationTarget("французский", "fr-FR")), TuplesKt.to("испанск", new TranslationTarget("испанский", "es-ES")), TuplesKt.to("итальянск", new TranslationTarget("итальянский", "it-IT")), TuplesKt.to("китайск", new TranslationTarget("китайский", "zh-CN")), TuplesKt.to("турецк", new TranslationTarget("турецкий", "tr-TR")));
    private static final Regex ONCE = new Regex("^(?:переведи|перевод|как будет|как сказать)\\s+(?:на\\s+)?(\\S+)\\s+(?:язык\\s+)?(.+)$");
    private static final Regex ONCE_TAIL = new Regex("^(?:переведи|как будет|как сказать)\\s+(.+?)\\s+(?:на|по)\\s+(\\S+)(?:\\s+язык)?$");
    private static final Regex START = new Regex("(?:режим переводчика|включи переводчик|будь переводчиком|режим перевода)\\s+(?:на\\s+)?(\\S+)");
    private static final Set<String> STOP = SetsKt.setOf((Object[]) new String[]{"выключи переводчик", "стоп перевод", "хватит переводить", "закончи перевод", "выключи режим переводчика"});

    /* JADX INFO: compiled from: Translator.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lcom/jarvis/core/nlp/Translator$Request;", "", "<init>", "()V", "Once", "Start", "Stop", "Lcom/jarvis/core/nlp/Translator$Request$Once;", "Lcom/jarvis/core/nlp/Translator$Request$Start;", "Lcom/jarvis/core/nlp/Translator$Request$Stop;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Request {
        public /* synthetic */ Request(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: compiled from: Translator.kt */
        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/jarvis/core/nlp/Translator$Request$Once;", "Lcom/jarvis/core/nlp/Translator$Request;", TypedValues.AttributesType.S_TARGET, "Lcom/jarvis/core/nlp/TranslationTarget;", VoiceService.EXTRA_TEXT, "", "<init>", "(Lcom/jarvis/core/nlp/TranslationTarget;Ljava/lang/String;)V", "getTarget", "()Lcom/jarvis/core/nlp/TranslationTarget;", "getText", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Once extends Request {
            private final TranslationTarget target;
            private final String text;

            public static /* synthetic */ Once copy$default(Once once, TranslationTarget translationTarget, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    translationTarget = once.target;
                }
                if ((i & 2) != 0) {
                    str = once.text;
                }
                return once.copy(translationTarget, str);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final TranslationTarget getTarget() {
                return this.target;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getText() {
                return this.text;
            }

            public final Once copy(TranslationTarget target, String text) {
                Intrinsics.checkNotNullParameter(target, "target");
                Intrinsics.checkNotNullParameter(text, "text");
                return new Once(target, text);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Once)) {
                    return false;
                }
                Once once = (Once) other;
                return Intrinsics.areEqual(this.target, once.target) && Intrinsics.areEqual(this.text, once.text);
            }

            public int hashCode() {
                return (this.target.hashCode() * 31) + this.text.hashCode();
            }

            public String toString() {
                return "Once(target=" + this.target + ", text=" + this.text + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Once(TranslationTarget target, String text) {
                super(null);
                Intrinsics.checkNotNullParameter(target, "target");
                Intrinsics.checkNotNullParameter(text, "text");
                this.target = target;
                this.text = text;
            }

            public final TranslationTarget getTarget() {
                return this.target;
            }

            public final String getText() {
                return this.text;
            }
        }

        private Request() {
        }

        /* JADX INFO: compiled from: Translator.kt */
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/jarvis/core/nlp/Translator$Request$Start;", "Lcom/jarvis/core/nlp/Translator$Request;", TypedValues.AttributesType.S_TARGET, "Lcom/jarvis/core/nlp/TranslationTarget;", "<init>", "(Lcom/jarvis/core/nlp/TranslationTarget;)V", "getTarget", "()Lcom/jarvis/core/nlp/TranslationTarget;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Start extends Request {
            private final TranslationTarget target;

            public static /* synthetic */ Start copy$default(Start start, TranslationTarget translationTarget, int i, Object obj) {
                if ((i & 1) != 0) {
                    translationTarget = start.target;
                }
                return start.copy(translationTarget);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final TranslationTarget getTarget() {
                return this.target;
            }

            public final Start copy(TranslationTarget target) {
                Intrinsics.checkNotNullParameter(target, "target");
                return new Start(target);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Start) && Intrinsics.areEqual(this.target, ((Start) other).target);
            }

            public int hashCode() {
                return this.target.hashCode();
            }

            public String toString() {
                return "Start(target=" + this.target + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Start(TranslationTarget target) {
                super(null);
                Intrinsics.checkNotNullParameter(target, "target");
                this.target = target;
            }

            public final TranslationTarget getTarget() {
                return this.target;
            }
        }

        /* JADX INFO: compiled from: Translator.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/jarvis/core/nlp/Translator$Request$Stop;", "Lcom/jarvis/core/nlp/Translator$Request;", "<init>", "()V", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Stop extends Request {
            public static final Stop INSTANCE = new Stop();

            private Stop() {
                super(null);
            }
        }
    }

    private Translator() {
    }

    public final TranslationTarget getActive() {
        return active;
    }

    public final void setActive(TranslationTarget translationTarget) {
        active = translationTarget;
    }

    public final Request parse(String phrase) {
        TranslationTarget it;
        TranslationTarget it2;
        TranslationTarget it3;
        Intrinsics.checkNotNullParameter(phrase, "phrase");
        String lowerCase = phrase.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String text = StringsKt.trimEnd(StringsKt.trim((CharSequence) lowerCase).toString(), '.', '!', '?');
        if (STOP.contains(text)) {
            return Request.Stop.INSTANCE;
        }
        MatchResult m = Regex.find$default(START, text, 0, 2, null);
        if (m != null && (it3 = INSTANCE.target(m.getGroupValues().get(1))) != null) {
            return new Request.Start(it3);
        }
        MatchResult m2 = Regex.find$default(ONCE, text, 0, 2, null);
        if (m2 != null && (it2 = INSTANCE.target(m2.getGroupValues().get(1))) != null) {
            return new Request.Once(it2, m2.getGroupValues().get(2));
        }
        MatchResult m3 = Regex.find$default(ONCE_TAIL, text, 0, 2, null);
        if (m3 == null || (it = INSTANCE.target(m3.getGroupValues().get(2))) == null) {
            return null;
        }
        return new Request.Once(it, m3.getGroupValues().get(1));
    }

    private final TranslationTarget target(String word) {
        Object element$iv;
        Map.Entry it;
        Iterable $this$firstOrNull$iv = TARGETS.entrySet();
        Iterator it2 = $this$firstOrNull$iv.iterator();
        do {
            if (!it2.hasNext()) {
                element$iv = null;
                break;
            }
            element$iv = it2.next();
            it = (Map.Entry) element$iv;
        } while (!StringsKt.startsWith$default(word, (String) it.getKey(), false, 2, (Object) null));
        Map.Entry entry = (Map.Entry) element$iv;
        if (entry != null) {
            return (TranslationTarget) entry.getValue();
        }
        return null;
    }

    public final String prompt(TranslationTarget target, String text) {
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(text, "text");
        return "Переведи на " + target.getName() + " язык. Ответь только переводом, без кавычек и пояснений: " + text;
    }

    /* JADX WARN: Code duplicated, block: B:37:0x007b  */
    public final String speechLocale(String text) {
        String locale;
        char it;
        Intrinsics.checkNotNullParameter(text, "text");
        String $this$filterTo$iv$iv = text;
        Appendable destination$iv$iv = new StringBuilder();
        int length = $this$filterTo$iv$iv.length();
        for (int index$iv$iv = 0; index$iv$iv < length; index$iv$iv++) {
            char element$iv$iv = $this$filterTo$iv$iv.charAt(index$iv$iv);
            if (Character.isLetter(element$iv$iv)) {
                destination$iv$iv.append(element$iv$iv);
            }
        }
        String $this$filter$iv = ((StringBuilder) destination$iv$iv).toString();
        if ($this$filter$iv.length() == 0) {
            return null;
        }
        String $this$count$iv = $this$filter$iv;
        int count$iv = 0;
        for (int i = 0; i < $this$count$iv.length(); i++) {
            char element$iv = $this$count$iv.charAt(i);
            if (1072 <= element$iv && element$iv < 1104) {
                it = 1;
            } else if ((1040 <= element$iv && element$iv < 1072) || element$iv == 1105 || element$iv == 1025) {
                it = 1;
            } else {
                it = 0;
            }
            if (it != 0) {
                count$iv++;
            }
        }
        int cyrillic = count$iv;
        if (((double) cyrillic) / ((double) $this$filter$iv.length()) >= MIN_CYRILLIC_SHARE) {
            return null;
        }
        TranslationTarget translationTarget = active;
        return (translationTarget == null || (locale = translationTarget.getLocale()) == null) ? "en-US" : locale;
    }
}
