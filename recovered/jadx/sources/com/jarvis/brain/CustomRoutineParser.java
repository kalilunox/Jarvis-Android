package com.jarvis.brain;

import androidx.core.app.NotificationCompat;
import com.jarvis.app.VoiceService;
import com.jarvis.core.routines.EventMatcher;
import com.jarvis.core.routines.EventRule;
import com.jarvis.core.routines.EventTrigger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: CustomRoutineParser.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0019B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\fJ\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0012\u001a\u00020\fH\u0002J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\fH\u0002J\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\f0\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\f0\u0017H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/jarvis/brain/CustomRoutineParser;", "", "<init>", "()V", "DEFINE", "Lkotlin/text/Regex;", "EVENT", "LEAVING", "OPENING", "DELETE", "VERBS", "", "", "LINKS", "parse", "Lcom/jarvis/brain/CustomRoutineParser$Request;", "phrase", NotificationCompat.CATEGORY_EVENT, VoiceService.EXTRA_TEXT, "rule", "Lcom/jarvis/core/routines/EventRule;", "condition", "steps", "", "tokens", "Request", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CustomRoutineParser {
    public static final CustomRoutineParser INSTANCE = new CustomRoutineParser();
    private static final Regex DEFINE = new Regex("^(?:когда|если)\\s+(?:я\\s+)?(?:говорю|скажу)\\s+(.+)$|^(?:создай|сделай|запомни)\\s+сценарий\\s+(.+)$");
    private static final Regex EVENT = new Regex("^(?:когда|если|как только)\\s+(.+)$");
    private static final Regex LEAVING = new Regex("отключ|сним|выним|вытащ|отсоедин|теря|пропад|убира");
    private static final Regex OPENING = new Regex("(?:открываю|запускаю|захожу в|открою|запущу|зайду в)\\s+(.+)$");
    private static final Regex DELETE = new Regex("^(?:удали|убери|забудь)\\s+сценарий\\s+(.+)$");
    private static final Set<String> VERBS = SetsKt.setOf((Object[]) new String[]{"включи", "выключи", "открой", "запусти", "поставь", "сделай", "останови", "убавь", "прибавь", "отключи", "закрой", "скажи", "расскажи", "прочитай"});
    private static final Set<String> LINKS = SetsKt.setOf((Object[]) new String[]{"то", "тогда", "это"});

    /* JADX INFO: compiled from: CustomRoutineParser.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lcom/jarvis/brain/CustomRoutineParser$Request;", "", "<init>", "()V", "Define", "DefineEvent", "Delete", "Names", "Lcom/jarvis/brain/CustomRoutineParser$Request$Define;", "Lcom/jarvis/brain/CustomRoutineParser$Request$DefineEvent;", "Lcom/jarvis/brain/CustomRoutineParser$Request$Delete;", "Lcom/jarvis/brain/CustomRoutineParser$Request$Names;", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Request {
        public /* synthetic */ Request(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: compiled from: CustomRoutineParser.kt */
        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/jarvis/brain/CustomRoutineParser$Request$Define;", "Lcom/jarvis/brain/CustomRoutineParser$Request;", "name", "", "steps", "", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "getName", "()Ljava/lang/String;", "getSteps", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Define extends Request {
            private final String name;
            private final List<String> steps;

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ Define copy$default(Define define, String str, List list, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = define.name;
                }
                if ((i & 2) != 0) {
                    list = define.steps;
                }
                return define.copy(str, list);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getName() {
                return this.name;
            }

            public final List<String> component2() {
                return this.steps;
            }

            public final Define copy(String name, List<String> steps) {
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(steps, "steps");
                return new Define(name, steps);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Define)) {
                    return false;
                }
                Define define = (Define) other;
                return Intrinsics.areEqual(this.name, define.name) && Intrinsics.areEqual(this.steps, define.steps);
            }

            public int hashCode() {
                return (this.name.hashCode() * 31) + this.steps.hashCode();
            }

            public String toString() {
                return "Define(name=" + this.name + ", steps=" + this.steps + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Define(String name, List<String> steps) {
                super(null);
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(steps, "steps");
                this.name = name;
                this.steps = steps;
            }

            public final String getName() {
                return this.name;
            }

            public final List<String> getSteps() {
                return this.steps;
            }
        }

        private Request() {
        }

        /* JADX INFO: compiled from: CustomRoutineParser.kt */
        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/jarvis/brain/CustomRoutineParser$Request$DefineEvent;", "Lcom/jarvis/brain/CustomRoutineParser$Request;", "rule", "Lcom/jarvis/core/routines/EventRule;", "steps", "", "", "<init>", "(Lcom/jarvis/core/routines/EventRule;Ljava/util/List;)V", "getRule", "()Lcom/jarvis/core/routines/EventRule;", "getSteps", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class DefineEvent extends Request {
            private final EventRule rule;
            private final List<String> steps;

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ DefineEvent copy$default(DefineEvent defineEvent, EventRule eventRule, List list, int i, Object obj) {
                if ((i & 1) != 0) {
                    eventRule = defineEvent.rule;
                }
                if ((i & 2) != 0) {
                    list = defineEvent.steps;
                }
                return defineEvent.copy(eventRule, list);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final EventRule getRule() {
                return this.rule;
            }

            public final List<String> component2() {
                return this.steps;
            }

            public final DefineEvent copy(EventRule rule, List<String> steps) {
                Intrinsics.checkNotNullParameter(rule, "rule");
                Intrinsics.checkNotNullParameter(steps, "steps");
                return new DefineEvent(rule, steps);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof DefineEvent)) {
                    return false;
                }
                DefineEvent defineEvent = (DefineEvent) other;
                return Intrinsics.areEqual(this.rule, defineEvent.rule) && Intrinsics.areEqual(this.steps, defineEvent.steps);
            }

            public int hashCode() {
                return (this.rule.hashCode() * 31) + this.steps.hashCode();
            }

            public String toString() {
                return "DefineEvent(rule=" + this.rule + ", steps=" + this.steps + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public DefineEvent(EventRule rule, List<String> steps) {
                super(null);
                Intrinsics.checkNotNullParameter(rule, "rule");
                Intrinsics.checkNotNullParameter(steps, "steps");
                this.rule = rule;
                this.steps = steps;
            }

            public final EventRule getRule() {
                return this.rule;
            }

            public final List<String> getSteps() {
                return this.steps;
            }
        }

        /* JADX INFO: compiled from: CustomRoutineParser.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/jarvis/brain/CustomRoutineParser$Request$Delete;", "Lcom/jarvis/brain/CustomRoutineParser$Request;", "name", "", "<init>", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Delete extends Request {
            private final String name;

            public static /* synthetic */ Delete copy$default(Delete delete, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = delete.name;
                }
                return delete.copy(str);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getName() {
                return this.name;
            }

            public final Delete copy(String name) {
                Intrinsics.checkNotNullParameter(name, "name");
                return new Delete(name);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Delete) && Intrinsics.areEqual(this.name, ((Delete) other).name);
            }

            public int hashCode() {
                return this.name.hashCode();
            }

            public String toString() {
                return "Delete(name=" + this.name + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Delete(String name) {
                super(null);
                Intrinsics.checkNotNullParameter(name, "name");
                this.name = name;
            }

            public final String getName() {
                return this.name;
            }
        }

        /* JADX INFO: compiled from: CustomRoutineParser.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/jarvis/brain/CustomRoutineParser$Request$Names;", "Lcom/jarvis/brain/CustomRoutineParser$Request;", "<init>", "()V", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Names extends Request {
            public static final Names INSTANCE = new Names();

            private Names() {
                super(null);
            }
        }
    }

    private CustomRoutineParser() {
    }

    public final Request parse(String phrase) {
        List listEmptyList;
        Intrinsics.checkNotNullParameter(phrase, "phrase");
        String lowerCase = phrase.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String text = new Regex("\\s+").replace(StringsKt.replace$default(StringsKt.trimEnd(StringsKt.trim((CharSequence) lowerCase).toString(), '.', '!', '?'), ",", StringUtils.SPACE, false, 4, (Object) null), StringUtils.SPACE);
        if (Intrinsics.areEqual(text, "какие у меня сценарии") || Intrinsics.areEqual(text, "мои сценарии")) {
            return Request.Names.INSTANCE;
        }
        int verb = 0;
        MatchResult it = Regex.find$default(DELETE, text, 0, 2, null);
        if (it != null) {
            return new Request.Delete(it.getGroupValues().get(1));
        }
        MatchResult match = Regex.find$default(DEFINE, text, 0, 2, null);
        if (match == null) {
            return event(text);
        }
        Iterable $this$first$iv = CollectionsKt.drop(match.getGroupValues(), 1);
        for (Object element$iv : $this$first$iv) {
            if ((((String) element$iv).length() > 0 ? 1 : verb) != 0) {
                char[] cArr = new char[1];
                cArr[verb] = ' ';
                List tokens = StringsKt.split$default((CharSequence) element$iv, cArr, false, 0, 6, (Object) null);
                int index$iv = 0;
                Iterator it2 = tokens.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        Object item$iv = it2.next();
                        if (VERBS.contains((String) item$iv)) {
                            break;
                        }
                        index$iv++;
                    } else {
                        index$iv = -1;
                        break;
                    }
                }
                int verb2 = index$iv;
                if (verb2 <= 0) {
                    return null;
                }
                List $this$dropLastWhile$iv = CollectionsKt.take(tokens, verb2);
                if (!$this$dropLastWhile$iv.isEmpty()) {
                    ListIterator iterator$iv = $this$dropLastWhile$iv.listIterator($this$dropLastWhile$iv.size());
                    while (true) {
                        if (iterator$iv.hasPrevious()) {
                            if (!LINKS.contains((String) iterator$iv.previous())) {
                                listEmptyList = CollectionsKt.take($this$dropLastWhile$iv, iterator$iv.nextIndex() + 1);
                                break;
                            }
                        } else {
                            listEmptyList = CollectionsKt.emptyList();
                            break;
                        }
                    }
                } else {
                    listEmptyList = CollectionsKt.emptyList();
                    break;
                }
                String name = CollectionsKt.joinToString$default(listEmptyList, StringUtils.SPACE, null, null, 0, null, null, 62, null);
                List<String> listSteps = steps(CollectionsKt.drop(tokens, verb2));
                if ((name.length() == 0) || listSteps.isEmpty()) {
                    return null;
                }
                return new Request.Define(name, listSteps);
            }
            verb = 0;
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    private final Request event(String text) {
        List<String> groupValues;
        String str;
        List tokens;
        List listEmptyList;
        MatchResult matchResultFind$default = Regex.find$default(EVENT, text, 0, 2, null);
        if (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null || (str = groupValues.get(1)) == null || (tokens = StringsKt.split$default((CharSequence) str, new char[]{' '}, false, 0, 6, (Object) null)) == null) {
            return null;
        }
        int index$iv = 0;
        Iterator it = tokens.iterator();
        while (true) {
            if (it.hasNext()) {
                Object item$iv = it.next();
                String it2 = (String) item$iv;
                if (VERBS.contains(it2)) {
                    break;
                }
                index$iv++;
            } else {
                index$iv = -1;
                break;
            }
        }
        int verb = index$iv;
        if (verb <= 0) {
            return null;
        }
        List $this$dropLastWhile$iv = CollectionsKt.take(tokens, verb);
        if (!$this$dropLastWhile$iv.isEmpty()) {
            ListIterator iterator$iv = $this$dropLastWhile$iv.listIterator($this$dropLastWhile$iv.size());
            while (true) {
                if (iterator$iv.hasPrevious()) {
                    String it3 = (String) iterator$iv.previous();
                    if (!LINKS.contains(it3)) {
                        listEmptyList = CollectionsKt.take($this$dropLastWhile$iv, iterator$iv.nextIndex() + 1);
                        break;
                    }
                } else {
                    listEmptyList = CollectionsKt.emptyList();
                    break;
                }
            }
        } else {
            listEmptyList = CollectionsKt.emptyList();
            break;
        }
        EventRule rule = rule(CollectionsKt.joinToString$default(listEmptyList, StringUtils.SPACE, null, null, 0, null, null, 62, null));
        if (rule == null) {
            return null;
        }
        List<String> listSteps = steps(CollectionsKt.drop(tokens, verb));
        if (listSteps.isEmpty()) {
            return null;
        }
        return new Request.DefineEvent(rule, listSteps);
    }

    private final EventRule rule(String condition) {
        boolean off = LEAVING.containsMatchIn(condition);
        if (StringsKt.contains$default((CharSequence) condition, (CharSequence) "наушник", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) condition, (CharSequence) "гарнитур", false, 2, (Object) null)) {
            return new EventRule(off ? EventTrigger.HEADPHONES_OFF : EventTrigger.HEADPHONES_ON, null, 2, null);
        }
        if (StringsKt.contains$default((CharSequence) condition, (CharSequence) "заряд", false, 2, (Object) null)) {
            return new EventRule(off ? EventTrigger.CHARGER_OFF : EventTrigger.CHARGER_ON, null, 2, null);
        }
        if (StringsKt.contains$default((CharSequence) condition, (CharSequence) "вайфа", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) condition, (CharSequence) "вай-фа", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) condition, (CharSequence) "вай фа", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) condition, (CharSequence) "wi-fi", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) condition, (CharSequence) EventMatcher.WIFI, false, 2, (Object) null)) {
            return new EventRule(off ? EventTrigger.WIFI_OFF : EventTrigger.WIFI_ON, null, 2, null);
        }
        if (StringsKt.contains$default((CharSequence) condition, (CharSequence) "разблокир", false, 2, (Object) null)) {
            return new EventRule(EventTrigger.UNLOCK, null, 2, null);
        }
        MatchResult it = Regex.find$default(OPENING, condition, 0, 2, null);
        if (it != null) {
            return new EventRule(EventTrigger.APP_OPEN, it.getGroupValues().get(1));
        }
        return null;
    }

    private final List<String> steps(List<String> tokens) {
        ArrayList result = new ArrayList();
        ArrayList current = new ArrayList();
        String verb = (String) CollectionsKt.first((List) tokens);
        for (String token : tokens) {
            if (Intrinsics.areEqual(token, "и") || Intrinsics.areEqual(token, "потом") || Intrinsics.areEqual(token, "затем")) {
                if (!current.isEmpty()) {
                    result.add(CollectionsKt.joinToString$default(current, StringUtils.SPACE, null, null, 0, null, null, 62, null));
                }
                current = new ArrayList();
            } else if (!current.isEmpty() || VERBS.contains(token)) {
                if (current.isEmpty()) {
                    verb = token;
                }
                current.add(token);
            } else {
                current.addAll(CollectionsKt.listOf((Object[]) new String[]{verb, token}));
            }
        }
        if (!current.isEmpty()) {
            result.add(CollectionsKt.joinToString$default(current, StringUtils.SPACE, null, null, 0, null, null, 62, null));
        }
        return result;
    }
}
