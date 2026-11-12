package com.jarvis.core.nlp;

import com.jarvis.core.common.IntentResolver;
import com.jarvis.core.common.model.ConfidenceLevel;
import com.jarvis.core.common.model.Intent;
import com.jarvis.core.common.model.IntentType;
import com.jarvis.core.common.model.Slot;
import com.jarvis.core.common.model.UserRequest;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: RuleBasedIntentResolver.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B#\u0012\u001a\b\u0002\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R \u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/jarvis/core/nlp/RuleBasedIntentResolver;", "Lcom/jarvis/core/common/IntentResolver;", "normalize", "Lkotlin/Function1;", "", "", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "lightNouns", "", "onVerbs", "offVerbs", "resolve", "Lcom/jarvis/core/common/model/Intent;", "request", "Lcom/jarvis/core/common/model/UserRequest;", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RuleBasedIntentResolver implements IntentResolver {
    private final Set<String> lightNouns;
    private final Function1<String, List<String>> normalize;
    private final Set<String> offVerbs;
    private final Set<String> onVerbs;

    /* JADX WARN: Multi-variable type inference failed */
    public RuleBasedIntentResolver() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX INFO: renamed from: com.jarvis.core.nlp.RuleBasedIntentResolver$1, reason: invalid class name */
    /* JADX INFO: compiled from: RuleBasedIntentResolver.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<String, List<? extends String>> {
        AnonymousClass1(Object obj) {
            super(1, obj, RussianNormalizer.class, "normalizeWords", "normalizeWords(Ljava/lang/String;)Ljava/util/List;", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final List<String> invoke(String p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return ((RussianNormalizer) this.receiver).normalizeWords(p0);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RuleBasedIntentResolver(Function1<? super String, ? extends List<String>> normalize) {
        Intrinsics.checkNotNullParameter(normalize, "normalize");
        this.normalize = normalize;
        this.lightNouns = SetsKt.setOf((Object[]) new String[]{"фонарик", "фонарика", "фонарику", "фонариком", "фонарике", "фонарь", "фонаря", "фонарю", "свет", "подсветка", "подсветку", "подсветки", "подсветкой", "освещение", "освещения"});
        this.onVerbs = SetsKt.setOf((Object[]) new String[]{"включи", "включить", "включите", "включил", "зажги", "зажечь", "зажигай", "дай", "дайте", "сделай", "сделаете", "включить"});
        this.offVerbs = SetsKt.setOf((Object[]) new String[]{"выключи", "выключить", "выключите", "погаси", "погасить", "потуши", "потушить", "отключи", "отключить"});
    }

    public /* synthetic */ RuleBasedIntentResolver(AnonymousClass1 anonymousClass1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new AnonymousClass1(RussianNormalizer.INSTANCE) : anonymousClass1);
    }

    @Override // com.jarvis.core.common.IntentResolver
    public Intent resolve(UserRequest request) {
        boolean nounMatch;
        boolean wantOn;
        String it;
        String action;
        Intrinsics.checkNotNullParameter(request, "request");
        List<String> listInvoke = this.normalize.invoke(request.getText());
        if (listInvoke.isEmpty()) {
            return Intent.INSTANCE.unknown("");
        }
        List<String> $this$any$iv = listInvoke;
        boolean wantOff = true;
        if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
            Iterator it2 = $this$any$iv.iterator();
            while (true) {
                if (it2.hasNext()) {
                    Object element$iv = it2.next();
                    String it3 = (String) element$iv;
                    if (this.lightNouns.contains(it3)) {
                        nounMatch = true;
                        break;
                    }
                } else {
                    nounMatch = false;
                    break;
                }
            }
        } else {
            nounMatch = false;
        }
        if (!nounMatch) {
            return Intent.INSTANCE.unknown(CollectionsKt.joinToString$default(listInvoke, StringUtils.SPACE, null, null, 0, null, null, 62, null));
        }
        List<String> $this$any$iv2 = listInvoke;
        if (!($this$any$iv2 instanceof Collection) || !$this$any$iv2.isEmpty()) {
            Iterator it4 = $this$any$iv2.iterator();
            while (true) {
                if (it4.hasNext()) {
                    Object element$iv2 = it4.next();
                    String it5 = (String) element$iv2;
                    if (this.onVerbs.contains(it5)) {
                        wantOn = true;
                        break;
                    }
                } else {
                    wantOn = false;
                    break;
                }
            }
        } else {
            wantOn = false;
        }
        List<String> $this$any$iv3 = listInvoke;
        if (!($this$any$iv3 instanceof Collection) || !$this$any$iv3.isEmpty()) {
            Iterator it6 = $this$any$iv3.iterator();
            do {
                if (!it6.hasNext()) {
                    wantOff = false;
                    break;
                }
                Object element$iv3 = it6.next();
                it = (String) element$iv3;
            } while (!this.offVerbs.contains(it));
        } else {
            wantOff = false;
        }
        if (!wantOn || wantOff) {
            action = (!wantOff || wantOn) ? null : "off";
        } else {
            action = "on";
        }
        if (action == null) {
            return Intent.INSTANCE.unknown(CollectionsKt.joinToString$default(listInvoke, StringUtils.SPACE, null, null, 0, null, null, 62, null));
        }
        return new Intent(IntentType.FLASHLIGHT, CollectionsKt.joinToString$default(listInvoke, StringUtils.SPACE, null, null, 0, null, null, 62, null), MapsKt.mapOf(TuplesKt.to("action", new Slot("action", action, 0.0f, 4, null))), 0.95f, ConfidenceLevel.HIGH);
    }
}
