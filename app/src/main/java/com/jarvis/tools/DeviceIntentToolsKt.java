package com.jarvis.tools;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.actions.SearchIntents;
import com.jarvis.core.common.Tool;
import com.jarvis.core.common.model.OnlineRequirement;
import com.jarvis.core.common.model.ParameterType;
import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.common.model.ToolCall;
import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.common.model.ToolParameter;
import com.jarvis.core.notes.PlaceBook;
import com.jarvis.core.notes.Places;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: DeviceIntentTools.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0002\u001a\u001a\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u001a\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u001a\u000e\u0010\f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u001a\u000e\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\"\u000e\u0010\r\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"textParameter", "Lcom/jarvis/core/common/model/ToolParameter;", "name", "", "description", "navigationTool", "Lcom/jarvis/core/common/Tool;", "context", "Landroid/content/Context;", "places", "Lcom/jarvis/core/notes/PlaceBook;", "webSearchTool", "appActionTool", "TAXI_PACKAGE", "cameraTool", "tools_debug"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class DeviceIntentToolsKt {
    private static final String TAXI_PACKAGE = "ru.yandex.taxi";

    /* JADX INFO: Access modifiers changed from: private */
    public static final ToolParameter textParameter(String name, String description) {
        return new ToolParameter(name, description, ParameterType.STRING, true, null, 16, null);
    }

    public static final Tool navigationTool(Context context, final PlaceBook places) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new IntentTool(context) { // from class: com.jarvis.tools.DeviceIntentToolsKt.navigationTool.1
            private final ToolDefinition definition = new ToolDefinition("navigate", "Навигация", "Проложить маршрут или показать место на карте", CollectionsKt.listOf(DeviceIntentToolsKt.textParameter("destination", "куда")), RiskLevel.SAFE, null, OnlineRequirement.LOCAL_ONLY, null, 160, null);
            private final String done = "Открываю маршрут, сэр.";
            private String missing = "Куда прокладывать маршрут, сэр?";

            @Override // com.jarvis.core.common.Tool
            public ToolDefinition getDefinition() {
                return this.definition;
            }

            @Override // com.jarvis.tools.IntentTool
            protected String getDone() {
                return this.done;
            }

            @Override // com.jarvis.tools.IntentTool
            protected String getMissing() {
                return this.missing;
            }

            protected void setMissing(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.missing = str;
            }

            @Override // com.jarvis.tools.IntentTool
            protected Intent intentFor(ToolCall call) {
                String spoken;
                String place;
                Intrinsics.checkNotNullParameter(call, "call");
                Object obj = call.getArguments().get("destination");
                if (obj != null && (spoken = obj.toString()) != null) {
                    if (StringsKt.isBlank(spoken)) {
                        spoken = null;
                    }
                    if (spoken != null) {
                        String key = Places.INSTANCE.keyOf(spoken);
                        if (key == null) {
                            place = spoken;
                        } else {
                            PlaceBook placeBook = places;
                            place = placeBook != null ? placeBook.get(key) : null;
                        }
                        if (place == null) {
                            String what = Intrinsics.areEqual(key, "home") ? "дома" : "работы";
                            setMissing("Адрес " + what + " не сохранён, сэр. Скажите: запомни адрес " + what + ", и сам адрес.");
                            return null;
                        }
                        return new Intent("android.intent.action.VIEW", Uri.parse("geo:0,0?q=" + Uri.encode(place)));
                    }
                }
                return null;
            }
        };
    }

    public static /* synthetic */ Tool navigationTool$default(Context context, PlaceBook placeBook, int i, Object obj) {
        if ((i & 2) != 0) {
            placeBook = null;
        }
        return navigationTool(context, placeBook);
    }

    public static final Tool webSearchTool(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new IntentTool(context) { // from class: com.jarvis.tools.DeviceIntentToolsKt.webSearchTool.1
            private final ToolDefinition definition = new ToolDefinition("web.search", "Поиск в сети", "Открыть поиск в браузере", CollectionsKt.listOf(DeviceIntentToolsKt.textParameter(SearchIntents.EXTRA_QUERY, "что искать")), RiskLevel.SAFE, null, OnlineRequirement.LOCAL_ONLY, null, 160, null);
            private final String done = "Открываю результаты, сэр.";
            private final String missing = "Что искать, сэр?";

            @Override // com.jarvis.core.common.Tool
            public ToolDefinition getDefinition() {
                return this.definition;
            }

            @Override // com.jarvis.tools.IntentTool
            protected String getDone() {
                return this.done;
            }

            @Override // com.jarvis.tools.IntentTool
            protected String getMissing() {
                return this.missing;
            }

            @Override // com.jarvis.tools.IntentTool
            protected Intent intentFor(ToolCall call) {
                String query;
                Intrinsics.checkNotNullParameter(call, "call");
                Object obj = call.getArguments().get(SearchIntents.EXTRA_QUERY);
                if (obj != null && (query = obj.toString()) != null) {
                    if (StringsKt.isBlank(query)) {
                        query = null;
                    }
                    if (query != null) {
                        return new Intent("android.intent.action.VIEW", Uri.parse("https://yandex.ru/search/?text=" + Uri.encode(query)));
                    }
                }
                return null;
            }
        };
    }

    public static final Tool appActionTool(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new IntentTool(context) { // from class: com.jarvis.tools.DeviceIntentToolsKt.appActionTool.1
            final /* synthetic */ Context $context;
            private final ToolDefinition definition;
            private final String done;
            private final String missing;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(context);
                this.$context = context;
                this.definition = new ToolDefinition("app.action", "Действие в приложении", "Поиск на ютубе, в картах, в магазине приложений, такси, сайт", CollectionsKt.listOf((Object[]) new ToolParameter[]{new ToolParameter("kind", "youtube, maps, market, taxi или site", ParameterType.ENUM, true, CollectionsKt.listOf((Object[]) new String[]{"youtube", "maps", "market", "taxi", "site"})), DeviceIntentToolsKt.textParameter(SearchIntents.EXTRA_QUERY, "что искать")}), RiskLevel.SAFE, null, OnlineRequirement.LOCAL_ONLY, null, 160, null);
                this.done = "Открываю, сэр.";
                this.missing = "Что именно, сэр?";
            }

            @Override // com.jarvis.core.common.Tool
            public ToolDefinition getDefinition() {
                return this.definition;
            }

            @Override // com.jarvis.tools.IntentTool
            protected String getDone() {
                return this.done;
            }

            @Override // com.jarvis.tools.IntentTool
            protected String getMissing() {
                return this.missing;
            }

            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            @Override // com.jarvis.tools.IntentTool
            protected Intent intentFor(ToolCall call) {
                Intrinsics.checkNotNullParameter(call, "call");
                Object obj = call.getArguments().get(SearchIntents.EXTRA_QUERY);
                String query = obj != null ? obj.toString() : null;
                if (query == null) {
                    query = "";
                }
                String encoded = Uri.encode(query);
                Object obj2 = call.getArguments().get("kind");
                String string = obj2 != null ? obj2.toString() : null;
                if (string == null) {
                    return null;
                }
                switch (string.hashCode()) {
                    case -1081306052:
                        if (string.equals("market")) {
                            return view("market://search?q=" + encoded);
                        }
                        return null;
                    case -991745245:
                        if (string.equals("youtube")) {
                            return view("https://www.youtube.com/results?search_query=" + encoded);
                        }
                        return null;
                    case 3344023:
                        if (string.equals("maps")) {
                            return view("geo:0,0?q=" + encoded);
                        }
                        return null;
                    case 3530567:
                        if (string.equals("site")) {
                            return view((StringsKt.contains$default((CharSequence) query, '.', false, 2, (Object) null) ? new StringBuilder().append("https://").append(query) : new StringBuilder().append("https://yandex.ru/search/?text=").append(encoded)).toString());
                        }
                        return null;
                    case 3552798:
                        if (!string.equals("taxi")) {
                            return null;
                        }
                        Intent launchIntentForPackage = this.$context.getPackageManager().getLaunchIntentForPackage(DeviceIntentToolsKt.TAXI_PACKAGE);
                        return launchIntentForPackage == null ? view("https://taxi.yandex.ru") : launchIntentForPackage;
                    default:
                        return null;
                }
            }

            private final Intent view(String uri) {
                return new Intent("android.intent.action.VIEW", Uri.parse(uri));
            }
        };
    }

    public static final Tool cameraTool(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new IntentTool(context) { // from class: com.jarvis.tools.DeviceIntentToolsKt.cameraTool.1
            private final ToolDefinition definition = new ToolDefinition("camera", "Камера", "Открыть камеру для фото или видео", CollectionsKt.listOf(new ToolParameter("mode", "photo или video", ParameterType.ENUM, false, CollectionsKt.listOf((Object[]) new String[]{"photo", "video"}))), RiskLevel.SAFE, null, OnlineRequirement.LOCAL_ONLY, null, 160, null);
            private final String done = "Камера готова, сэр.";
            private final String missing = "";

            @Override // com.jarvis.core.common.Tool
            public ToolDefinition getDefinition() {
                return this.definition;
            }

            @Override // com.jarvis.tools.IntentTool
            protected String getDone() {
                return this.done;
            }

            @Override // com.jarvis.tools.IntentTool
            protected String getMissing() {
                return this.missing;
            }

            @Override // com.jarvis.tools.IntentTool
            protected Intent intentFor(ToolCall call) {
                String str;
                Intrinsics.checkNotNullParameter(call, "call");
                Object obj = call.getArguments().get("mode");
                if (Intrinsics.areEqual(obj != null ? obj.toString() : null, "video")) {
                    str = "android.media.action.VIDEO_CAMERA";
                } else {
                    str = "android.media.action.STILL_IMAGE_CAMERA";
                }
                return new Intent(str);
            }
        };
    }
}
