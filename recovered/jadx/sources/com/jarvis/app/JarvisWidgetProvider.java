package com.jarvis.app;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.BatteryManager;
import android.widget.RemoteViews;
import com.jarvis.app.ui.JarvisActivity;
import com.jarvis.app.ui.ReactorArt;
import com.jarvis.app.ui.Skin;
import com.jarvis.app.ui.Skins;
import com.jarvis.core.common.model.UserRequest;
import com.jarvis.core.event.JarvisEvents;
import com.jarvis.core.model.AssistantState;
import com.jarvis.core.reminder.HudText;
import com.jarvis.core.reminder.Reminder;
import java.time.LocalDateTime;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JarvisWidgetProvider.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0015\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u001cR\u0014\u0010\u0004\u001a\u00020\u0005X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u001e"}, d2 = {"Lcom/jarvis/app/JarvisWidgetProvider;", "Landroid/appwidget/AppWidgetProvider;", "<init>", "()V", "layout", "", "getLayout", "()I", "coreDp", "getCoreDp", "compact", "", "getCompact", "()Z", "onUpdate", "", "context", "Landroid/content/Context;", "manager", "Landroid/appwidget/AppWidgetManager;", "ids", "", "onReceive", "intent", "Landroid/content/Intent;", "brief", "views", "Landroid/widget/RemoteViews;", "views$app_debug", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public class JarvisWidgetProvider extends AppWidgetProvider {
    private static final String ACTION_BRIEF = "com.jarvis.assistant.WIDGET_BRIEF";
    private static final String ACTION_OFF = "com.jarvis.assistant.WIDGET_OFF";
    private static final long BRIEF_NOTIFICATION = 9000;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String PREF_WEATHER = "widget_weather";
    private static final int REQUEST_BRIEF = 12;
    private static final int REQUEST_LISTEN = 11;
    private static final int REQUEST_POWER = 10;
    private final boolean compact;
    private final int layout = R.layout.widget_jarvis;
    private final int coreDp = 88;

    protected int getLayout() {
        return this.layout;
    }

    protected int getCoreDp() {
        return this.coreDp;
    }

    protected boolean getCompact() {
        return this.compact;
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onUpdate(Context context, AppWidgetManager manager, int[] ids) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(manager, "manager");
        Intrinsics.checkNotNullParameter(ids, "ids");
        manager.updateAppWidget(ids, views$app_debug(context));
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // android.appwidget.AppWidgetProvider, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onReceive(context, intent);
        String action = intent.getAction();
        if (action != null) {
            switch (action.hashCode()) {
                case -1021272800:
                    if (action.equals(ACTION_OFF)) {
                        VoiceController voiceController = VoiceController.INSTANCE;
                        Context applicationContext = context.getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                        voiceController.stop(applicationContext);
                        break;
                    }
                    break;
                case 2092706795:
                    if (action.equals(ACTION_BRIEF)) {
                        Context applicationContext2 = context.getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext2, "getApplicationContext(...)");
                        brief(applicationContext2);
                    }
                    break;
            }
        }
    }

    private final void brief(final Context context) {
        final BroadcastReceiver.PendingResult pending = goAsync();
        new Thread(new Runnable() { // from class: com.jarvis.app.JarvisWidgetProvider$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                JarvisWidgetProvider.brief$lambda$0(context, pending);
            }
        }, "jarvis-widget-brief").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void brief$lambda$0(Context $context, BroadcastReceiver.PendingResult $pending) {
        try {
            String reply = BrainGraph.INSTANCE.build($context).process(new UserRequest("сводка на день", null, null, 0L, null, 30, null)).getReplyText();
            ReminderReceiver.INSTANCE.deliver($context, 9000L, reply);
        } finally {
            $pending.finish();
        }
    }

    public final RemoteViews views$app_debug(final Context context) {
        Object objM392constructorimpl;
        String str;
        Intrinsics.checkNotNullParameter(context, "context");
        Skin skin = Skins.INSTANCE.current(context);
        AssistantState state = JarvisEvents.INSTANCE.getAssistantState().getValue();
        boolean on = VoiceController.INSTANCE.isRunning();
        BatteryManager batteryManager = (BatteryManager) context.getSystemService(BatteryManager.class);
        int battery = batteryManager != null ? batteryManager.getIntProperty(4) : 0;
        try {
            Result.Companion companion = Result.INSTANCE;
            JarvisWidgetProvider jarvisWidgetProvider = this;
            objM392constructorimpl = Result.m392constructorimpl(new AndroidReminderBook(context).all());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        List listEmptyList = CollectionsKt.emptyList();
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = listEmptyList;
        }
        List<Reminder> list = (List) objM392constructorimpl;
        Function1 open = new Function1() { // from class: com.jarvis.app.JarvisWidgetProvider$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JarvisWidgetProvider.views$lambda$1(context, (String) obj);
            }
        };
        PendingIntent power = on ? PendingIntent.getBroadcast(context, 10, new Intent(context, (Class<?>) JarvisWidgetProvider.class).setAction(ACTION_OFF), 201326592) : PendingIntent.getActivity(context, 10, (Intent) open.invoke(JarvisActivity.ACTION_POWER_ON), 201326592);
        PendingIntent listen = PendingIntent.getActivity(context, 11, (Intent) open.invoke("android.intent.action.ASSIST"), 201326592);
        PendingIntent brief = PendingIntent.getBroadcast(context, 12, new Intent(context, (Class<?>) JarvisWidgetProvider.class).setAction(ACTION_BRIEF), 201326592);
        if (!on) {
            str = getCompact() ? "Выключен" : "Выключен, нажмите на ядро";
        } else if (state == AssistantState.LISTENING) {
            str = "Слушает";
        } else if (state == AssistantState.THINKING || state == AssistantState.EXECUTING) {
            str = "Думает";
        } else if (state == AssistantState.SPEAKING) {
            str = "Говорит";
        } else {
            str = getCompact() ? "Ожидает" : "Слушает «Джарвис», заряд " + battery + "%";
        }
        String status = str;
        Resources res = context.getResources();
        RemoteViews $this$views_u24lambda_u242 = new RemoteViews(context.getPackageName(), getLayout());
        $this$views_u24lambda_u242.setInt(R.id.widgetRoot, "setBackgroundResource", views$id(res, context, "bg_widget_" + skin.getId()));
        $this$views_u24lambda_u242.setInt(R.id.widgetListen, "setBackgroundResource", views$id(res, context, "bg_widget_main_" + skin.getId()));
        $this$views_u24lambda_u242.setInt(R.id.widgetMicIcon, "setColorFilter", skin.getText());
        $this$views_u24lambda_u242.setImageViewBitmap(R.id.widgetCore, ReactorArt.INSTANCE.render(context, skin, getCoreDp(), on, state == AssistantState.SPEAKING));
        $this$views_u24lambda_u242.setTextViewText(R.id.widgetStatus, status);
        $this$views_u24lambda_u242.setTextColor(R.id.widgetStatus, on ? skin.getGood() : skin.getMuted());
        int i = R.id.widgetNext;
        HudText hudText = HudText.INSTANCE;
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        Intrinsics.checkNotNullExpressionValue(localDateTimeNow, "now(...)");
        $this$views_u24lambda_u242.setTextViewText(i, hudText.next(list, localDateTimeNow));
        $this$views_u24lambda_u242.setTextColor(R.id.widgetNext, skin.getText());
        int i2 = R.id.widgetSub;
        String string = JarvisSettings.INSTANCE.prefs(context).getString(PREF_WEATHER, "");
        $this$views_u24lambda_u242.setTextViewText(i2, string != null ? string : "");
        $this$views_u24lambda_u242.setTextColor(R.id.widgetSub, skin.getMuted());
        $this$views_u24lambda_u242.setTextColor(R.id.widgetBrand, skin.getText());
        $this$views_u24lambda_u242.setTextColor(R.id.widgetBrief, skin.getText());
        $this$views_u24lambda_u242.setTextColor(R.id.widgetListenText, skin.getText());
        $this$views_u24lambda_u242.setOnClickPendingIntent(R.id.widgetCore, power);
        $this$views_u24lambda_u242.setOnClickPendingIntent(R.id.widgetListen, listen);
        $this$views_u24lambda_u242.setOnClickPendingIntent(R.id.widgetBrief, brief);
        return $this$views_u24lambda_u242;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Intent views$lambda$1(Context $context, String action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return new Intent($context, (Class<?>) JarvisActivity.class).setAction(action).addFlags(805306368);
    }

    private static final int views$id(Resources res, Context $context, String name) {
        return res.getIdentifier(name, "drawable", $context.getPackageName());
    }

    /* JADX INFO: compiled from: JarvisWidgetProvider.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/jarvis/app/JarvisWidgetProvider$Companion;", "", "<init>", "()V", "ACTION_BRIEF", "", "ACTION_OFF", "BRIEF_NOTIFICATION", "", "REQUEST_POWER", "", "REQUEST_LISTEN", "REQUEST_BRIEF", "PREF_WEATHER", "refresh", "", "context", "Landroid/content/Context;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void refresh(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Context app = context.getApplicationContext();
            AppWidgetManager manager = AppWidgetManager.getInstance(app);
            if (manager == null) {
                return;
            }
            Iterable $this$forEach$iv = CollectionsKt.listOf((Object[]) new JarvisWidgetProvider[]{new JarvisWidgetProvider(), new JarvisWidgetSquare(), new JarvisWidgetStrip()});
            for (Object element$iv : $this$forEach$iv) {
                JarvisWidgetProvider provider = (JarvisWidgetProvider) element$iv;
                int[] ids = manager.getAppWidgetIds(new ComponentName(app, provider.getClass()));
                Intrinsics.checkNotNull(ids);
                if (!(ids.length == 0)) {
                    Intrinsics.checkNotNull(app);
                    manager.updateAppWidget(ids, provider.views$app_debug(app));
                }
            }
        }
    }
}
