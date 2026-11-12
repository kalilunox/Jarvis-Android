package com.jarvis.core.aioutput;

import com.jarvis.app.VoiceService;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.apache.commons.codec.language.Soundex;
import org.apache.commons.lang3.CharUtils;

/* JADX INFO: compiled from: MiniJson.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\t"}, d2 = {"Lcom/jarvis/core/aioutput/MiniJson;", "", "<init>", "()V", "parse", "Lcom/jarvis/core/aioutput/JsonVal;", VoiceService.EXTRA_TEXT, "", "Parser", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MiniJson {
    public static final MiniJson INSTANCE = new MiniJson();

    private MiniJson() {
    }

    public final JsonVal parse(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        Parser p = new Parser(StringsKt.trim((CharSequence) text).toString());
        JsonVal v = p.parseValue();
        p.skipWs();
        if (!p.eof()) {
            throw new IllegalStateException("trailing".toString());
        }
        return v;
    }

    /* JADX INFO: compiled from: MiniJson.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\rJ\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0003H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u0003H\u0002J\u0010\u0010\u0019\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/jarvis/core/aioutput/MiniJson$Parser;", "", "s", "", "<init>", "(Ljava/lang/String;)V", "i", "", "eof", "", "skipWs", "", "parseValue", "Lcom/jarvis/core/aioutput/JsonVal;", "parseObject", "Lcom/jarvis/core/aioutput/JsonVal$Obj;", "parseArray", "Lcom/jarvis/core/aioutput/JsonVal$Arr;", "parseString", "parseNumber", "", "expect", "ch", "", "lit", "peek", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Parser {
        private int i;
        private final String s;

        public Parser(String s) {
            Intrinsics.checkNotNullParameter(s, "s");
            this.s = s;
        }

        public final boolean eof() {
            return this.i >= this.s.length();
        }

        public final void skipWs() {
            while (this.i < this.s.length() && CharsKt.isWhitespace(this.s.charAt(this.i))) {
                this.i++;
            }
        }

        public final JsonVal parseValue() {
            skipWs();
            if (eof()) {
                throw new IllegalStateException("eof".toString());
            }
            char c = this.s.charAt(this.i);
            switch (c) {
                case '\"':
                    return new JsonVal.Str(parseString());
                case '[':
                    return parseArray();
                case 'f':
                    expect("false");
                    return new JsonVal.Bool(false);
                case 'n':
                    expect("null");
                    return JsonVal.Null.INSTANCE;
                case 't':
                    expect("true");
                    return new JsonVal.Bool(true);
                case '{':
                    return parseObject();
                default:
                    if (c == '-' || Character.isDigit(c)) {
                        return new JsonVal.Num(parseNumber());
                    }
                    throw new IllegalStateException("unexpected".toString());
            }
        }

        private final JsonVal.Obj parseObject() {
            expect('{');
            LinkedHashMap map = new LinkedHashMap();
            skipWs();
            if (peek('}')) {
                this.i++;
                return new JsonVal.Obj(map);
            }
            while (true) {
                skipWs();
                String key = parseString();
                skipWs();
                expect(':');
                JsonVal value = parseValue();
                map.put(key, value);
                skipWs();
                if (!peek(',')) {
                    break;
                }
                this.i++;
            }
            if (peek('}')) {
                this.i++;
                return new JsonVal.Obj(map);
            }
            throw new IllegalStateException("object".toString());
        }

        private final JsonVal.Arr parseArray() {
            expect('[');
            List items = new ArrayList();
            skipWs();
            if (peek(']')) {
                this.i++;
                return new JsonVal.Arr(items);
            }
            while (true) {
                items.add(parseValue());
                skipWs();
                if (!peek(',')) {
                    break;
                }
                this.i++;
            }
            if (peek(']')) {
                this.i++;
                return new JsonVal.Arr(items);
            }
            throw new IllegalStateException("array".toString());
        }

        private final String parseString() {
            expect(Typography.quote);
            StringBuilder sb = new StringBuilder();
            while (!eof()) {
                String str = this.s;
                int i = this.i;
                this.i = i + 1;
                char c = str.charAt(i);
                switch (c) {
                    case '\"':
                        String string = sb.toString();
                        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                        return string;
                    case '\\':
                        if (eof()) {
                            throw new IllegalStateException("escape".toString());
                        }
                        String str2 = this.s;
                        int i2 = this.i;
                        this.i = i2 + 1;
                        char e = str2.charAt(i2);
                        switch (e) {
                            case '\"':
                            case '/':
                            case '\\':
                                sb.append(e);
                                break;
                            case 'b':
                                sb.append('\b');
                                break;
                            case 'f':
                                sb.append('\f');
                                break;
                            case 'n':
                                sb.append('\n');
                                break;
                            case 'r':
                                sb.append(CharUtils.CR);
                                break;
                            case 't':
                                sb.append('\t');
                                break;
                            case 'u':
                                if (this.i + 4 > this.s.length()) {
                                    throw new IllegalStateException("unicode".toString());
                                }
                                String hex = this.s.substring(this.i, this.i + 4);
                                Intrinsics.checkNotNullExpressionValue(hex, "substring(...)");
                                sb.append((char) Integer.parseInt(hex, CharsKt.checkRadix(16)));
                                this.i += 4;
                                Unit unit = Unit.INSTANCE;
                                break;
                                break;
                            default:
                                throw new IllegalStateException("bad_escape".toString());
                        }
                        break;
                    default:
                        sb.append(c);
                        break;
                }
            }
            throw new IllegalStateException("unclosed_string".toString());
        }

        private final double parseNumber() {
            int start = this.i;
            if (peek(Soundex.SILENT_MARKER)) {
                this.i++;
            }
            while (this.i < this.s.length() && Character.isDigit(this.s.charAt(this.i))) {
                this.i++;
            }
            if (peek('.')) {
                this.i++;
                while (this.i < this.s.length() && Character.isDigit(this.s.charAt(this.i))) {
                    this.i++;
                }
            }
            if (peek('e') || peek('E')) {
                this.i++;
                if (peek('+') || peek(Soundex.SILENT_MARKER)) {
                    this.i++;
                }
                while (this.i < this.s.length() && Character.isDigit(this.s.charAt(this.i))) {
                    this.i++;
                }
            }
            String strSubstring = this.s.substring(start, this.i);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            return Double.parseDouble(strSubstring);
        }

        private final void expect(char ch) {
            skipWs();
            if (eof() || this.s.charAt(this.i) != ch) {
                throw new IllegalStateException(("expected_" + ch).toString());
            }
            this.i++;
        }

        private final void expect(String lit) {
            skipWs();
            if (!StringsKt.startsWith$default(this.s, lit, this.i, false, 4, (Object) null)) {
                throw new IllegalStateException(("expected_" + lit).toString());
            }
            this.i += lit.length();
        }

        private final boolean peek(char ch) {
            return this.i < this.s.length() && this.s.charAt(this.i) == ch;
        }
    }
}
