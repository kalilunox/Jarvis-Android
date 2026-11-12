package com.jarvis.tools;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import com.jarvis.app.EventRoutineRunner;
import com.jarvis.core.common.Tool;
import com.jarvis.core.common.model.OnlineRequirement;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.nlp.InfoFeeds;
import com.jarvis.tools.net.SimpleHttp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InfoTools.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u0006\u0010\b\u001a\u00020\t\u001a\u0006\u0010\n\u001a\u00020\t\u001a\u000e\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"NEWS_URL", "", "RATES_URL", "NEWS_COUNT", "", "NEWS_TTL_MS", "", "RATES_TTL_MS", "newsTool", "Lcom/jarvis/core/common/Tool;", "ratesTool", "locationTool", "context", "Landroid/content/Context;", "tools_debug"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class InfoToolsKt {
    private static final int NEWS_COUNT = 5;
    private static final long NEWS_TTL_MS = 600000;
    private static final String NEWS_URL = "https://ria.ru/export/rss2/archive/index.xml";
    private static final long RATES_TTL_MS = 3600000;
    private static final String RATES_URL = "https://open.er-api.com/v6/latest/USD";

    public static final Tool newsTool() {
        return new ReadTool(OnlineRequirement.ONLINE_ALLOWED) { // from class: com.jarvis.tools.InfoToolsKt.newsTool.1
            private final String failure = "Новости сейчас недоступны, сэр.";

            @Override // com.jarvis.tools.ReadTool
            protected String getFailure() {
                return this.failure;
            }

            @Override // com.jarvis.tools.ReadTool
            protected String read() {
                List<String> listHeadlines = InfoFeeds.INSTANCE.headlines(SimpleHttp.get$default(SimpleHttp.INSTANCE, InfoToolsKt.NEWS_URL, InfoToolsKt.NEWS_TTL_MS, 0, null, 0, 28, null), 5);
                List<String> list = !listHeadlines.isEmpty() ? listHeadlines : null;
                if (list != null) {
                    return CollectionsKt.joinToString$default(list, ". ", "Главные новости, сэр. ", ".", 0, null, null, 56, null);
                }
                return null;
            }
        };
    }

    public static final Tool ratesTool() {
        return new ReadTool(OnlineRequirement.ONLINE_ALLOWED) { // from class: com.jarvis.tools.InfoToolsKt.ratesTool.1
            private final String failure = "Курсы сейчас недоступны, сэр.";

            @Override // com.jarvis.tools.ReadTool
            protected String getFailure() {
                return this.failure;
            }

            @Override // com.jarvis.tools.ReadTool
            protected String read() {
                return InfoFeeds.INSTANCE.ratesReport(SimpleHttp.get$default(SimpleHttp.INSTANCE, InfoToolsKt.RATES_URL, 3600000L, 0, null, 0, 28, null));
            }

            @Override // com.jarvis.tools.ReadTool
            protected String read(ToolCall call) {
                String code;
                Intrinsics.checkNotNullParameter(call, "call");
                Object obj = call.getArguments().get("code");
                if (obj == null || (code = obj.toString()) == null) {
                    return read();
                }
                return InfoFeeds.INSTANCE.rateReport(SimpleHttp.get$default(SimpleHttp.INSTANCE, InfoToolsKt.RATES_URL, 3600000L, 0, null, 0, 28, null), code);
            }
        };
    }

    public static final Tool locationTool(final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ReadTool(OnlineRequirement.LOCAL_ONLY) { // from class: com.jarvis.tools.InfoToolsKt.locationTool.1
            private final String failure = "Не могу определить местоположение, сэр.";

            @Override // com.jarvis.tools.ReadTool
            protected String getFailure() {
                return this.failure;
            }

            @Override // com.jarvis.tools.ReadTool
            protected String read() {
                Object objM392constructorimpl;
                List it;
                Location location = lastKnown();
                String place = null;
                if (location == null) {
                    return null;
                }
                Context context2 = context;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    AnonymousClass1 anonymousClass1 = this;
                    List<Address> fromLocation = new Geocoder(context2, new Locale("ru")).getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    objM392constructorimpl = Result.m392constructorimpl(fromLocation != null ? (Address) CollectionsKt.firstOrNull((List) fromLocation) : null);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m398isFailureimpl(objM392constructorimpl)) {
                    objM392constructorimpl = null;
                }
                Address address = (Address) objM392constructorimpl;
                if (address != null && (it = CollectionsKt.listOfNotNull((Object[]) new String[]{address.getLocality(), address.getThoroughfare(), address.getSubThoroughfare()})) != null) {
                    if (it.isEmpty()) {
                        it = null;
                    }
                    if (it != null) {
                        place = CollectionsKt.joinToString$default(it, ", ", null, null, 0, null, null, 62, null);
                    }
                }
                if (place != null) {
                    return "Вы здесь, сэр: " + place + ".";
                }
                String str = String.format(Locale.US, "Координаты, сэр: %.4f, %.4f.", Arrays.copyOf(new Object[]{Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude())}, 2));
                Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                return str;
            }

            private final Location lastKnown() {
                LocationManager manager;
                Object objM392constructorimpl;
                Object maxElem$iv = null;
                if (context.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0 || (manager = (LocationManager) context.getSystemService(LocationManager.class)) == null) {
                    return null;
                }
                Iterable $this$mapNotNull$iv = CollectionsKt.listOf((Object[]) new String[]{"gps", EventRoutineRunner.NETWORK, "passive"});
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                    String it = (String) element$iv$iv$iv;
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        AnonymousClass1 anonymousClass1 = this;
                        objM392constructorimpl = Result.m392constructorimpl(manager.getLastKnownLocation(it));
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m398isFailureimpl(objM392constructorimpl)) {
                        objM392constructorimpl = null;
                    }
                    Location location = (Location) objM392constructorimpl;
                    if (location != null) {
                        destination$iv$iv.add(location);
                    }
                }
                Iterable $this$maxByOrNull$iv = (List) destination$iv$iv;
                Iterator iterator$iv = $this$maxByOrNull$iv.iterator();
                if (iterator$iv.hasNext()) {
                    maxElem$iv = iterator$iv.next();
                    if (iterator$iv.hasNext()) {
                        Location it2 = (Location) maxElem$iv;
                        long maxValue$iv = it2.getTime();
                        do {
                            Object e$iv = iterator$iv.next();
                            Location it3 = (Location) e$iv;
                            long v$iv = it3.getTime();
                            if (maxValue$iv < v$iv) {
                                maxElem$iv = e$iv;
                                maxValue$iv = v$iv;
                            }
                        } while (iterator$iv.hasNext());
                    }
                }
                return (Location) maxElem$iv;
            }
        };
    }
}
