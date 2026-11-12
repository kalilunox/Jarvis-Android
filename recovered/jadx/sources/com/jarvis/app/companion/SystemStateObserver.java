package com.jarvis.app.companion;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.BatteryManager;
import android.os.Build;
import android.util.Log;
import com.jarvis.app.EventRoutineRunner;
import com.jarvis.core.companion.ObservationSource;
import com.jarvis.core.companion.ObservationType;
import com.jarvis.core.companion.PhoneContextBus;
import com.jarvis.core.companion.PhoneObservation;
import com.jarvis.core.companion.Sensitivity;
import com.jarvis.core.routines.EventMatcher;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: SystemStateObserver.kt */
/* JADX INFO: loaded from: classes18.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002*\u0002\t\f\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\b\u0010\u0011\u001a\u00020\u000fH\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J.\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00132\u0014\b\u0002\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130\u0019H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u0010\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\r¨\u0006\u001b"}, d2 = {"Lcom/jarvis/app/companion/SystemStateObserver;", "", "appContext", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "registered", "", "receiver", "com/jarvis/app/companion/SystemStateObserver$receiver$1", "Lcom/jarvis/app/companion/SystemStateObserver$receiver$1;", "bluetoothReceiver", "com/jarvis/app/companion/SystemStateObserver$bluetoothReceiver$1", "Lcom/jarvis/app/companion/SystemStateObserver$bluetoothReceiver$1;", "start", "", "stop", "seedInitialState", "currentNetworkLabel", "", "emit", "type", "Lcom/jarvis/core/companion/ObservationType;", "summary", "entities", "", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SystemStateObserver {
    private static final String BLUETOOTH_A2DP_STATE = "android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED";
    private static final String BLUETOOTH_HEADSET_STATE = "android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String STARTUP = "startup";
    private static final String TAG = "Jarvis.Companion";
    private static volatile SystemStateObserver instance;
    private final Context appContext;
    private final SystemStateObserver$bluetoothReceiver$1 bluetoothReceiver;
    private final SystemStateObserver$receiver$1 receiver;
    private volatile boolean registered;

    /* JADX WARN: Type inference failed for: r0v1, types: [com.jarvis.app.companion.SystemStateObserver$receiver$1] */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.jarvis.app.companion.SystemStateObserver$bluetoothReceiver$1] */
    public SystemStateObserver(Context appContext) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        this.appContext = appContext;
        this.receiver = new BroadcastReceiver() { // from class: com.jarvis.app.companion.SystemStateObserver$receiver$1
            /* JADX WARN: Code duplicated, block: B:41:0x0100  */
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                String action;
                String str;
                if (intent == null || (action = intent.getAction()) == null) {
                    return;
                }
                switch (action.hashCode()) {
                    case -2128145023:
                        if (action.equals("android.intent.action.SCREEN_OFF")) {
                            SystemStateObserver.emit$default(this.this$0, ObservationType.SCREEN_OFF, "Экран выключен", null, 4, null);
                            break;
                        }
                        break;
                    case -1886648615:
                        if (action.equals("android.intent.action.ACTION_POWER_DISCONNECTED")) {
                            this.this$0.emit(ObservationType.CHARGER_DISCONNECTED, "Зарядка отключена", MapsKt.mapOf(TuplesKt.to("charging", "false")));
                            break;
                        }
                        break;
                    case -1676458352:
                        if (action.equals("android.intent.action.HEADSET_PLUG")) {
                            int state = intent.getIntExtra("state", 0);
                            Map start = isInitialStickyBroadcast() ? MapsKt.mapOf(TuplesKt.to(EventRoutineRunner.STARTUP, "true")) : MapsKt.emptyMap();
                            if (state != 1) {
                                this.this$0.emit(ObservationType.HEADPHONES_DISCONNECTED, "Наушники отключены", start);
                            } else {
                                this.this$0.emit(ObservationType.HEADPHONES_CONNECTED, "Наушники подключены", start);
                            }
                            break;
                        }
                        break;
                    case -1538406691:
                        if (action.equals("android.intent.action.BATTERY_CHANGED")) {
                            int level = intent.getIntExtra("level", -1);
                            int scale = RangesKt.coerceAtLeast(intent.getIntExtra("scale", 100), 1);
                            Integer pct = level >= 0 ? Integer.valueOf((level * 100) / scale) : null;
                            int status = intent.getIntExtra("status", -1);
                            boolean charging = status == 2 || status == 5;
                            SystemStateObserver systemStateObserver = this.this$0;
                            ObservationType observationType = ObservationType.BATTERY_CHANGED;
                            if (pct != null) {
                                int it = pct.intValue();
                                str = "Батарея " + it + "%";
                                if (str == null) {
                                    str = "Батарея изменилась";
                                }
                            } else {
                                str = "Батарея изменилась";
                            }
                            Map $this$onReceive_u24lambda_u241 = MapsKt.createMapBuilder();
                            if (pct != null) {
                                $this$onReceive_u24lambda_u241.put("percent", String.valueOf(pct.intValue()));
                            }
                            $this$onReceive_u24lambda_u241.put("charging", String.valueOf(charging));
                            Unit unit = Unit.INSTANCE;
                            systemStateObserver.emit(observationType, str, MapsKt.build($this$onReceive_u24lambda_u241));
                            break;
                        }
                        break;
                    case -1454123155:
                        if (action.equals("android.intent.action.SCREEN_ON")) {
                            SystemStateObserver.emit$default(this.this$0, ObservationType.SCREEN_ON, "Экран включён", null, 4, null);
                        }
                        break;
                    case -1172645946:
                        if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                            String net = this.this$0.currentNetworkLabel();
                            this.this$0.emit(ObservationType.NETWORK_CHANGED, "Сеть: " + net, MapsKt.mapOf(TuplesKt.to(EventRoutineRunner.NETWORK, net)));
                            break;
                        }
                        break;
                    case 823795052:
                        if (action.equals("android.intent.action.USER_PRESENT")) {
                            SystemStateObserver.emit$default(this.this$0, ObservationType.DEVICE_UNLOCKED, "Устройство разблокировано", null, 4, null);
                            break;
                        }
                        break;
                    case 1019184907:
                        if (action.equals("android.intent.action.ACTION_POWER_CONNECTED")) {
                            this.this$0.emit(ObservationType.CHARGER_CONNECTED, "Зарядка подключена", MapsKt.mapOf(TuplesKt.to("charging", "true")));
                            break;
                        }
                        break;
                }
            }
        };
        this.bluetoothReceiver = new BroadcastReceiver() { // from class: com.jarvis.app.companion.SystemStateObserver$bluetoothReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                Integer numValueOf = intent != null ? Integer.valueOf(intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1)) : null;
                if (numValueOf != null && numValueOf.intValue() == 2) {
                    SystemStateObserver.emit$default(this.this$0, ObservationType.HEADPHONES_CONNECTED, "Bluetooth-наушники подключены", null, 4, null);
                } else if (numValueOf != null && numValueOf.intValue() == 0) {
                    SystemStateObserver.emit$default(this.this$0, ObservationType.HEADPHONES_DISCONNECTED, "Bluetooth-наушники отключены", null, 4, null);
                }
            }
        };
    }

    public final void start() {
        if (this.registered) {
            return;
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.SCREEN_ON");
        filter.addAction("android.intent.action.SCREEN_OFF");
        filter.addAction("android.intent.action.USER_PRESENT");
        filter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        filter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        filter.addAction("android.intent.action.BATTERY_CHANGED");
        filter.addAction("android.intent.action.HEADSET_PLUG");
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        IntentFilter bluetoothFilter = new IntentFilter();
        bluetoothFilter.addAction(BLUETOOTH_A2DP_STATE);
        bluetoothFilter.addAction(BLUETOOTH_HEADSET_STATE);
        if (Build.VERSION.SDK_INT >= 33) {
            this.appContext.registerReceiver(this.receiver, filter, 4);
            this.appContext.registerReceiver(this.bluetoothReceiver, bluetoothFilter, 2);
        } else {
            this.appContext.registerReceiver(this.receiver, filter);
            this.appContext.registerReceiver(this.bluetoothReceiver, bluetoothFilter);
        }
        this.registered = true;
        seedInitialState();
        Log.i(TAG, "SystemStateObserver started");
    }

    public final void stop() {
        if (this.registered) {
            try {
                Result.Companion companion = Result.INSTANCE;
                SystemStateObserver $this$stop_u24lambda_u240 = this;
                $this$stop_u24lambda_u240.appContext.unregisterReceiver($this$stop_u24lambda_u240.receiver);
                Result.m392constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m392constructorimpl(ResultKt.createFailure(th));
            }
            try {
                Result.Companion companion3 = Result.INSTANCE;
                SystemStateObserver $this$stop_u24lambda_u241 = this;
                $this$stop_u24lambda_u241.appContext.unregisterReceiver($this$stop_u24lambda_u241.bluetoothReceiver);
                Result.m392constructorimpl(Unit.INSTANCE);
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.INSTANCE;
                Result.m392constructorimpl(ResultKt.createFailure(th2));
            }
            this.registered = false;
            Log.i(TAG, "SystemStateObserver stopped");
        }
    }

    private final void seedInitialState() {
        Object systemService = this.appContext.getSystemService("batterymanager");
        BatteryManager bm = systemService instanceof BatteryManager ? (BatteryManager) systemService : null;
        Integer pct = bm != null ? Integer.valueOf(bm.getIntProperty(4)) : null;
        boolean charging = bm != null && bm.isCharging();
        if (pct != null && pct.intValue() >= 0) {
            emit(ObservationType.BATTERY_CHANGED, "Батарея " + pct + "% (старт)", MapsKt.mapOf(TuplesKt.to("percent", String.valueOf(pct.intValue())), TuplesKt.to("charging", String.valueOf(charging))));
        }
        Object systemService2 = this.appContext.getSystemService("audio");
        AudioManager am = systemService2 instanceof AudioManager ? (AudioManager) systemService2 : null;
        boolean wired = am != null && am.isWiredHeadsetOn();
        if (wired) {
            emit(ObservationType.HEADPHONES_CONNECTED, "Наушники (старт)", MapsKt.mapOf(TuplesKt.to("startup", "true")));
        }
        String net = currentNetworkLabel();
        emit(ObservationType.NETWORK_CHANGED, "Сеть: " + net + " (старт)", MapsKt.mapOf(TuplesKt.to(EventRoutineRunner.NETWORK, net)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String currentNetworkLabel() {
        Object systemService = this.appContext.getSystemService("connectivity");
        ConnectivityManager cm = systemService instanceof ConnectivityManager ? (ConnectivityManager) systemService : null;
        if (cm == null) {
            return "unknown";
        }
        Network network = cm.getActiveNetwork();
        if (network == null) {
            return "offline";
        }
        NetworkCapabilities caps = cm.getNetworkCapabilities(network);
        if (caps == null) {
            return "unknown";
        }
        if (caps.hasTransport(1)) {
            return EventMatcher.WIFI;
        }
        if (caps.hasTransport(0)) {
            return "cellular";
        }
        if (caps.hasTransport(3)) {
            return "ethernet";
        }
        return caps.hasTransport(2) ? "bluetooth" : "other";
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void emit$default(SystemStateObserver systemStateObserver, ObservationType observationType, String str, Map map, int i, Object obj) {
        if ((i & 4) != 0) {
            map = MapsKt.emptyMap();
        }
        systemStateObserver.emit(observationType, str, map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void emit(ObservationType type, String summary, Map<String, String> entities) {
        PhoneContextBus.INSTANCE.publish(new PhoneObservation(null, 0L, ObservationSource.SYSTEM_BROADCAST, type, null, null, summary, entities, Sensitivity.PUBLIC, 1.0f, 51, null));
        Log.d(TAG, "obs " + type.name() + ": " + summary);
    }

    /* JADX INFO: compiled from: SystemStateObserver.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/jarvis/app/companion/SystemStateObserver$Companion;", "", "<init>", "()V", "TAG", "", "STARTUP", "BLUETOOTH_A2DP_STATE", "BLUETOOTH_HEADSET_STATE", "instance", "Lcom/jarvis/app/companion/SystemStateObserver;", "ensureStarted", "context", "Landroid/content/Context;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SystemStateObserver ensureStarted(Context context) {
            SystemStateObserver it;
            Intrinsics.checkNotNullParameter(context, "context");
            SystemStateObserver existing = SystemStateObserver.instance;
            if (existing != null) {
                existing.start();
                return existing;
            }
            synchronized (this) {
                it = SystemStateObserver.instance;
                if (it == null) {
                    Context applicationContext = context.getApplicationContext();
                    Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                    it = new SystemStateObserver(applicationContext);
                    Companion companion = SystemStateObserver.INSTANCE;
                    SystemStateObserver.instance = it;
                    it.start();
                }
            }
            return it;
        }
    }
}
