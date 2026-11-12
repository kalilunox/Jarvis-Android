package com.jarvis.core.privacy;

import com.jarvis.app.VoiceService;
import com.jarvis.core.companion.Sensitivity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequencesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SensitivityClassifier.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0005J\u001c\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\u0010\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0005H\u0002J\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\t2\u0006\u0010\u000f\u001a\u00020\u0005H\u0002J\u0010\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0005H\u0002J\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001a0\t2\u0006\u0010\u000f\u001a\u00020\u0005H\u0002J\u0015\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u001fJ\u001e\u0010 \u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001a0\tH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082T¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/jarvis/core/privacy/SensitivityClassifier;", "", "<init>", "()V", "MASK", "", "sensitivePackages", "", "sensitivePackageSubstrings", "", "isSensitivePackage", "", "packageName", "classify", "Lcom/jarvis/core/companion/Sensitivity;", VoiceService.EXTRA_TEXT, "mask", "otpTriggerRegex", "Lkotlin/text/Regex;", "digitRunRegex", "cardRegex", "balanceTriggerRegex", "TRIGGER_WINDOW", "", "hasOtp", "otpRanges", "Lkotlin/ranges/IntRange;", "hasBalance", "validCardRanges", "luhn", "digits", "luhn$core_debug", "replaceRanges", "ranges", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SensitivityClassifier {
    public static final String MASK = "[скрыто]";
    private static final int TRIGGER_WINDOW = 40;
    public static final SensitivityClassifier INSTANCE = new SensitivityClassifier();
    private static final Set<String> sensitivePackages = SetsKt.setOf((Object[]) new String[]{"ru.sberbankmobile", "ru.sberbankmobile.invest", "com.idamob.tinkoff.android", "ru.vtb24.mobilebanking.android", "ru.alfabank.mobile.android", "ru.rostel", "ru.raiffeisennews", "ru.rshb.mobilebank", "ru.gazprombank.android.mobilebank", "ru.mkb.mobilebank", "ru.letobank", "ru.psbank", "ru.yandex.money", "com.yoo.money", "com.paypal.android.p2pmobile", "com.google.android.apps.walletnfcrel", "com.x8bit.bitwarden", "com.lastpass.lpandroid", "com.android.keepass", "keepass2android.keepass2android", "com.onepassword.android", "com.agilebits.onepassword", "com.google.android.apps.authenticator2", "com.azure.authenticator", "com.authy.authy", "org.fedorahosted.freeotp", "com.beemdevelopment.aegis"});
    private static final List<String> sensitivePackageSubstrings = CollectionsKt.listOf((Object[]) new String[]{"bank", "sber", "tinkoff", "tbank", "vtb", "alfa", "gazprom", "raiffeisen", "rshb", "rosbank", "pay", "wallet", "otp", "authenticator", "keepass", "password"});
    private static final Regex otpTriggerRegex = new Regex("(?<![a-zа-яё0-9])(?:код[а-я]{0,2}|парол[а-я]{0,3}|пин[а-я]{0,2}|отп[а-я]{0,1}|подтвержд[а-я]{0,4}|проверочн[а-я]{0,4}|одноразов[а-я]{0,4}|никому\\s+не\\s+сообщ[а-я]{0,4}|confirmation|verification|passcode|password|codes|code|otp|pin|cvv|cvc|confirm)(?![a-zа-яё])", RegexOption.IGNORE_CASE);
    private static final Regex digitRunRegex = new Regex("(?<!\\d)\\d{4,8}(?!\\d)");
    private static final Regex cardRegex = new Regex("(?<!\\d)(?:\\d[ -]?){12,18}\\d(?!\\d)");
    private static final Regex balanceTriggerRegex = new Regex("(?<![a-zа-яё])(?:баланс[а-я]{0,2}|остат[а-я]{0,4}|доступн[а-я]{0,3}|balance|available)(?![a-zа-яё])", RegexOption.IGNORE_CASE);

    private SensitivityClassifier() {
    }

    public final boolean isSensitivePackage(String packageName) {
        String pkg;
        String string;
        if (packageName == null || (string = StringsKt.trim((CharSequence) packageName).toString()) == null) {
            pkg = null;
        } else {
            pkg = string.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(pkg, "toLowerCase(...)");
        }
        if (pkg == null) {
            pkg = "";
        }
        if (pkg.length() == 0) {
            return false;
        }
        if (sensitivePackages.contains(pkg)) {
            return true;
        }
        Iterable $this$any$iv = sensitivePackageSubstrings;
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            String it = (String) element$iv;
            if (StringsKt.contains$default((CharSequence) pkg, (CharSequence) it, false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ Sensitivity classify$default(SensitivityClassifier sensitivityClassifier, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        return sensitivityClassifier.classify(str, str2);
    }

    public final Sensitivity classify(String text, String packageName) {
        if (isSensitivePackage(packageName)) {
            return Sensitivity.SECRET;
        }
        String raw = text == null ? "" : text;
        if (StringsKt.isBlank(raw)) {
            return Sensitivity.NORMAL;
        }
        if (validCardRanges(raw).isEmpty() && !hasOtp(raw)) {
            return hasBalance(raw) ? Sensitivity.PRIVATE : Sensitivity.NORMAL;
        }
        return Sensitivity.SECRET;
    }

    public final String mask(String text) {
        if (text == null) {
            return "";
        }
        if (text.length() == 0) {
            return text;
        }
        List<IntRange> listValidCardRanges = validCardRanges(text);
        String withoutCards = listValidCardRanges.isEmpty() ? text : replaceRanges(text, listValidCardRanges);
        List<IntRange> listOtpRanges = otpRanges(withoutCards);
        return listOtpRanges.isEmpty() ? withoutCards : replaceRanges(withoutCards, listOtpRanges);
    }

    private final boolean hasOtp(String text) {
        return !otpRanges(text).isEmpty();
    }

    private final List<IntRange> otpRanges(String text) {
        List out = new ArrayList();
        for (MatchResult m : Regex.findAll$default(digitRunRegex, text, 0, 2, null)) {
            int start = m.getRange().getFirst();
            int end = m.getRange().getLast();
            int from = RangesKt.coerceAtLeast(start - 40, 0);
            int to = RangesKt.coerceAtMost(end + 40, text.length() - 1);
            String window = text.substring(from, to + 1);
            Intrinsics.checkNotNullExpressionValue(window, "substring(...)");
            if (otpTriggerRegex.containsMatchIn(window)) {
                out.add(new IntRange(start, end));
            }
        }
        return out;
    }

    private final boolean hasBalance(String text) {
        for (MatchResult m : Regex.findAll$default(balanceTriggerRegex, text, 0, 2, null)) {
            int from = m.getRange().getLast() + 1;
            int to = RangesKt.coerceAtMost(from + 40, text.length());
            if (from < to) {
                CharSequence tail = text.substring(from, to);
                Intrinsics.checkNotNullExpressionValue(tail, "substring(...)");
                CharSequence $this$count$iv = tail;
                int count$iv = 0;
                for (int i = 0; i < $this$count$iv.length(); i++) {
                    char element$iv = $this$count$iv.charAt(i);
                    if (Character.isDigit(element$iv)) {
                        count$iv++;
                    }
                }
                int digits = count$iv;
                if (digits >= 2) {
                    return true;
                }
            }
        }
        return false;
    }

    private final List<IntRange> validCardRanges(String text) {
        return SequencesKt.toList(SequencesKt.map(SequencesKt.filter(Regex.findAll$default(cardRegex, text, 0, 2, null), new Function1() { // from class: com.jarvis.core.privacy.SensitivityClassifier$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(SensitivityClassifier.validCardRanges$lambda$0((MatchResult) obj));
            }
        }), new Function1() { // from class: com.jarvis.core.privacy.SensitivityClassifier$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SensitivityClassifier.validCardRanges$lambda$1((MatchResult) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean validCardRanges$lambda$0(MatchResult it) throws IOException {
        Intrinsics.checkNotNullParameter(it, "it");
        SensitivityClassifier sensitivityClassifier = INSTANCE;
        CharSequence $this$filter$iv = it.getValue();
        CharSequence $this$filterTo$iv$iv = $this$filter$iv;
        Appendable destination$iv$iv = new StringBuilder();
        int length = $this$filterTo$iv$iv.length();
        for (int index$iv$iv = 0; index$iv$iv < length; index$iv$iv++) {
            char element$iv$iv = $this$filterTo$iv$iv.charAt(index$iv$iv);
            if (Character.isDigit(element$iv$iv)) {
                destination$iv$iv.append(element$iv$iv);
            }
        }
        String $this$filter$iv2 = ((StringBuilder) destination$iv$iv).toString();
        return sensitivityClassifier.luhn$core_debug($this$filter$iv2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IntRange validCardRanges$lambda$1(MatchResult it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getRange();
    }

    public final boolean luhn$core_debug(String digits) {
        boolean z;
        Intrinsics.checkNotNullParameter(digits, "digits");
        int length = digits.length();
        if (!(13 <= length && length < 20)) {
            return false;
        }
        String $this$any$iv = digits;
        int i = 0;
        while (true) {
            if (i < $this$any$iv.length()) {
                char element$iv = $this$any$iv.charAt(i);
                if (!Character.isDigit(element$iv)) {
                    z = true;
                    break;
                }
                i++;
            } else {
                z = false;
                break;
            }
        }
        if (z) {
            return false;
        }
        int sum = 0;
        boolean z2 = false;
        for (int i2 = digits.length() - 1; -1 < i2; i2--) {
            int d = digits.charAt(i2) - '0';
            if (z2 && (d = d * 2) > 9) {
                d -= 9;
            }
            sum += d;
            z2 = !z2;
        }
        int i3 = sum % 10;
        return i3 == 0;
    }

    private final String replaceRanges(String text, List<IntRange> ranges) {
        if (ranges.isEmpty()) {
            return text;
        }
        List<IntRange> $this$sortedBy$iv = ranges;
        List<IntRange> sorted = CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: com.jarvis.core.privacy.SensitivityClassifier$replaceRanges$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                IntRange it = (IntRange) t;
                IntRange it2 = (IntRange) t2;
                return ComparisonsKt.compareValues(Integer.valueOf(it.getFirst()), Integer.valueOf(it2.getFirst()));
            }
        });
        StringBuilder sb = new StringBuilder(text.length());
        int cursor = 0;
        for (IntRange r : sorted) {
            if (r.getFirst() >= cursor) {
                sb.append((CharSequence) text, cursor, r.getFirst());
                sb.append("[скрыто]");
                cursor = r.getLast() + 1;
            }
        }
        if (cursor < text.length()) {
            sb.append((CharSequence) text, cursor, text.length());
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }
}
