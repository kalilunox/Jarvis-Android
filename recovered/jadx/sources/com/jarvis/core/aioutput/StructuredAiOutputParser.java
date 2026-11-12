package com.jarvis.core.aioutput;

import com.jarvis.app.VoiceService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import kotlin.time.DurationKt;

/* JADX INFO: compiled from: StructuredAiOutputParser.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006J\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0017\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u0010J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/jarvis/core/aioutput/StructuredAiOutputParser;", "", "<init>", "()V", "ALLOWED_TOP_LEVEL", "", "", "extractJsonObject", "Lcom/jarvis/core/aioutput/ParseSyntaxResult;", "raw", "unknownTopLevelKeys", "", "obj", "Lcom/jarvis/core/aioutput/JsonVal$Obj;", "isolateJson", VoiceService.EXTRA_TEXT, "isolateJson$core_debug", "ms", "", "t0", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class StructuredAiOutputParser {
    public static final StructuredAiOutputParser INSTANCE = new StructuredAiOutputParser();
    private static final Set<String> ALLOWED_TOP_LEVEL = SetsKt.setOf((Object[]) new String[]{"schemaVersion", "type", "requestId", "confidence", VoiceService.EXTRA_TEXT, "question", "reason", "toolId", "arguments", "goal", "intent", "suggestedRoute", "ambiguity", "clarify", "steps", "summary", "noActionReason"});

    private StructuredAiOutputParser() {
    }

    public final ParseSyntaxResult extractJsonObject(String raw) {
        Intrinsics.checkNotNullParameter(raw, "raw");
        long t0 = System.nanoTime();
        String trimmed = StringsKt.trim((CharSequence) raw).toString();
        if (StringsKt.isBlank(trimmed)) {
            return new ParseSyntaxResult.Fail("empty", ms(t0));
        }
        if (trimmed.length() > 4096) {
            return new ParseSyntaxResult.Fail("oversized", ms(t0));
        }
        String jsonText = isolateJson$core_debug(trimmed);
        if (jsonText == null) {
            return new ParseSyntaxResult.Fail("no_json", ms(t0));
        }
        if (jsonText.length() > 4096) {
            return new ParseSyntaxResult.Fail("oversized", ms(t0));
        }
        try {
            JsonVal root = MiniJson.INSTANCE.parse(jsonText);
            JsonVal.Obj obj = root.asObj();
            if (obj == null) {
                return new ParseSyntaxResult.Fail("not_object", ms(t0));
            }
            if (JsonVal.nestingDepth$default(root, 0, 1, null) > 4) {
                return new ParseSyntaxResult.Fail("too_deep", ms(t0));
            }
            return new ParseSyntaxResult.Ok(obj, jsonText, ms(t0));
        } catch (Exception e) {
            return new ParseSyntaxResult.Fail("malformed_json", ms(t0));
        }
    }

    public final List<String> unknownTopLevelKeys(JsonVal.Obj obj) {
        Intrinsics.checkNotNullParameter(obj, "obj");
        Iterable $this$filter$iv = obj.keys();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            if (!ALLOWED_TOP_LEVEL.contains(it)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    public final String isolateJson$core_debug(String text) {
        List<String> groupValues;
        Intrinsics.checkNotNullParameter(text, "text");
        MatchResult matchResultFind$default = Regex.find$default(new Regex("```(?:json)?\\s*(\\{[\\s\\S]*?\\})\\s*```", RegexOption.IGNORE_CASE), text, 0, 2, null);
        String fenced = (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null) ? null : groupValues.get(1);
        if (fenced != null) {
            return StringsKt.trim((CharSequence) fenced).toString();
        }
        int start = StringsKt.indexOf$default((CharSequence) text, '{', 0, false, 6, (Object) null);
        int end = StringsKt.lastIndexOf$default((CharSequence) text, '}', 0, false, 6, (Object) null);
        if (start < 0 || end <= start) {
            return null;
        }
        String strSubstring = text.substring(start, end + 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    private final long ms(long t0) {
        return (System.nanoTime() - t0) / ((long) DurationKt.NANOS_IN_MILLIS);
    }
}
