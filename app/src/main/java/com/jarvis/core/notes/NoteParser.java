package com.jarvis.core.notes;

import com.jarvis.app.AndroidReminderBook;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: Notes.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0005J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0006\u0010\u0016\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R \u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/jarvis/core/notes/NoteParser;", "", "<init>", "()V", "SHOPPING", "", "TODO", "NOTES", "LISTS", "", "Lkotlin/Pair;", "Lkotlin/text/Regex;", "ADD", "", "READ", "REMOVE", "CLEAR", "SEPARATOR", "parse", "Lcom/jarvis/core/notes/NoteCommand;", "phrase", AndroidReminderBook.KEY_ITEMS, "body", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NoteParser {
    public static final NoteParser INSTANCE = new NoteParser();
    public static final String SHOPPING = "покупки";
    public static final String TODO = "дела";
    public static final String NOTES = "заметки";
    private static final List<Pair<Regex, String>> LISTS = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(new Regex("(?:в |из |на )?(?:список |списке |списка )?покуп\\S*"), SHOPPING), TuplesKt.to(new Regex("(?:в |из |на )?(?:список |списке |списка )дел\\b|(?:в |из )дела\\b"), TODO), TuplesKt.to(new Regex("(?:в |из )?замет\\S*"), NOTES)});
    private static final Set<String> ADD = SetsKt.setOf((Object[]) new String[]{"добавь", "запиши", "внеси"});
    private static final Set<String> READ = SetsKt.setOf((Object[]) new String[]{"что", "какие", "прочитай", "покажи", "прочти", "озвучь"});
    private static final Set<String> REMOVE = SetsKt.setOf((Object[]) new String[]{"убери", "удали", "вычеркни"});
    private static final Set<String> CLEAR = SetsKt.setOf((Object[]) new String[]{"очисти", "сотри"});
    private static final Regex SEPARATOR = new Regex(",\\s*|\\s+и\\s+");

    private NoteParser() {
    }

    public final NoteCommand parse(String phrase) {
        Pair pair;
        List<String> groupValues;
        Intrinsics.checkNotNullParameter(phrase, "phrase");
        String lowerCase = phrase.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String text = StringsKt.trimEnd(StringsKt.trim((CharSequence) lowerCase).toString(), '.', '!', '?', Typography.ellipsis);
        String first = StringsKt.substringBefore$default(text, ' ', (String) null, 2, (Object) null);
        MatchResult matchResultFind$default = Regex.find$default(new Regex("^(?:запиши|запомни) мысль[:\\s]+(.+)$"), text, 0, 2, null);
        String thought = (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null) ? null : groupValues.get(1);
        if (thought != null) {
            return new NoteCommand.Add(NOTES, CollectionsKt.listOf(thought));
        }
        Iterator<T> it = LISTS.iterator();
        do {
            if (!it.hasNext()) {
                pair = null;
                break;
            }
            Pair pair2 = (Pair) it.next();
            Regex re = (Regex) pair2.component1();
            String name = (String) pair2.component2();
            MatchResult it2 = Regex.find$default(re, text, 0, 2, null);
            pair = it2 != null ? TuplesKt.to(it2, name) : null;
        } while (pair == null);
        if (pair == null) {
            return null;
        }
        MatchResult match = (MatchResult) pair.component1();
        String list = (String) pair.component2();
        String rest = StringsKt.trim((CharSequence) new Regex("\\s+").replace(StringsKt.removeRange((CharSequence) text, match.getRange()).toString(), StringUtils.SPACE)).toString();
        String body = StringsKt.trim((CharSequence) StringsKt.substringAfter(rest, ' ', "")).toString();
        if (ADD.contains(first)) {
            List<String> listItems = items(body);
            if (listItems.isEmpty()) {
                listItems = null;
            }
            return listItems != null ? new NoteCommand.Add(list, listItems) : null;
        }
        if (CLEAR.contains(first)) {
            return new NoteCommand.Clear(list);
        }
        if (REMOVE.contains(first)) {
            return ((body.length() == 0) || Intrinsics.areEqual(body, "всё") || Intrinsics.areEqual(body, "все")) ? new NoteCommand.Clear(list) : new NoteCommand.Remove(list, body);
        }
        if (READ.contains(first)) {
            return new NoteCommand.Read(list);
        }
        return null;
    }

    private final List<String> items(String body) {
        Iterable $this$map$iv = SEPARATOR.split(body, 0);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            destination$iv$iv.add(StringsKt.trim((CharSequence) it).toString());
        }
        Iterable $this$filter$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it2 = (String) element$iv$iv;
            if (it2.length() > 0) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv2;
    }
}
