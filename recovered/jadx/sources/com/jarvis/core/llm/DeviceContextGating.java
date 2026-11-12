package com.jarvis.core.llm;

import com.google.android.gms.actions.SearchIntents;
import com.jarvis.core.companion.PhoneContext;
import com.jarvis.core.routines.EventMatcher;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: DeviceContextGating.kt */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006J,\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b0\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/jarvis/core/llm/DeviceContextGating;", "", "<init>", "()V", "deviceMarkers", "", "", "isRelevant", "", SearchIntents.EXTRA_QUERY, "format", "Lkotlin/Pair;", "phone", "Lcom/jarvis/core/companion/PhoneContext;", "maxChars", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DeviceContextGating {
    public static final DeviceContextGating INSTANCE = new DeviceContextGating();
    private static final List<String> deviceMarkers = CollectionsKt.listOf((Object[]) new String[]{"громк", "звук", "музык", "слыш", "наушник", "батар", "заряд", "экран", "приложен", "открыт", "сейчас", "телефон", "интернет", "сеть", EventMatcher.WIFI, "wi-fi", "уведомлен"});

    private DeviceContextGating() {
    }

    public final boolean isRelevant(String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        String q = query.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(q, "toLowerCase(...)");
        Iterable $this$any$iv = deviceMarkers;
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            String it = (String) element$iv;
            if (StringsKt.contains$default((CharSequence) q, (CharSequence) it, false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    public final Pair<String, Boolean> format(PhoneContext phone, String query, int maxChars) {
        Intrinsics.checkNotNullParameter(query, "query");
        if (phone == null || !isRelevant(query)) {
            return TuplesKt.to("", false);
        }
        List lines = new ArrayList();
        String it = phone.getCurrentApp();
        if (it != null) {
            lines.add("текущее приложение: " + it);
        }
        Integer batteryPercent = phone.getBatteryPercent();
        if (batteryPercent != null) {
            lines.add("батарея: " + batteryPercent.intValue() + "%");
        }
        if (phone.getCharging()) {
            lines.add("зарядка: да");
        }
        if (phone.getHeadphonesConnected()) {
            lines.add("наушники: подключены");
        }
        String it2 = phone.getNetworkType();
        if (it2 != null) {
            lines.add("сеть: " + it2);
        }
        if (!phone.getScreenOn()) {
            lines.add("экран: выключен");
        }
        String block = StringsKt.take(CollectionsKt.joinToString$default(lines, "; ", null, null, 0, null, null, 62, null), maxChars);
        if (phone.getCurrentApp() != null) {
        }
        return TuplesKt.to(block, Boolean.valueOf(!StringsKt.isBlank(block)));
    }
}
