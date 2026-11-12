package com.jarvis.app.ui;

import android.content.Intent;
import android.widget.LinearLayout;
import com.jarvis.app.BrainGraph;
import com.jarvis.app.JarvisSettings;
import com.jarvis.app.TestConsoleActivity;
import com.jarvis.app.UiMode;
import com.jarvis.app.UsageGuideActivity;
import com.jarvis.core.startup.UiStyle;
import com.jarvis.onboarding.PermissionsHelper;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.codec.language.bm.Rule;

/* JADX INFO: compiled from: More.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/jarvis/app/ui/Sections;", "", "<init>", "()V", "ASR_MODES", "", "", "MODE_LABELS", "PERSONA_LABELS", "PROFILE_LABELS", "ROUTER_MODES", Rule.ALL, "Lcom/jarvis/app/ui/MoreSection;", "getALL", "()Ljava/util/List;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Sections {
    public static final Sections INSTANCE = new Sections();
    private static final List<String> ASR_MODES = CollectionsKt.listOf((Object[]) new String[]{"AUTO", "CLOUD_PREFERRED", "LOCAL_ONLY"});
    private static final List<String> MODE_LABELS = CollectionsKt.listOf((Object[]) new String[]{"Авто", "Облако", "Локально"});
    private static final List<String> PERSONA_LABELS = CollectionsKt.listOf((Object[]) new String[]{"Джарвис", "Друг", "Кратко", "Инженер"});
    private static final List<String> PROFILE_LABELS = CollectionsKt.listOf((Object[]) new String[]{"Быстро", "Баланс", "Качество"});
    private static final List<String> ROUTER_MODES = CollectionsKt.listOf((Object[]) new String[]{"HYBRID", "LOCAL", "CLOUD"});
    private static final List<MoreSection> ALL = CollectionsKt.listOf((Object[]) new MoreSection[]{new MoreSection("Голос и слух", "Слово активации, распознавание, синтез", Ic.MIC, new Function2() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda29
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return Sections.ALL$lambda$0((SectionScreen) obj, (LinearLayout) obj2);
        }
    }), new MoreSection("Характер и ответы", "Личность, скорость, уверенность", Ic.FACE, new Function2() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda30
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return Sections.ALL$lambda$1((SectionScreen) obj, (LinearLayout) obj2);
        }
    }), new MoreSection("ИИ и модели", "Облако, ключи, локальные модели", Ic.CHIP, new Function2() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda31
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return Sections.ALL$lambda$2((SectionScreen) obj, (LinearLayout) obj2);
        }
    }), new MoreSection("Оформление", "Тема, звуки, плавающий орб", Ic.PAINT, new Function2() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda32
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return Sections.ALL$lambda$3((SectionScreen) obj, (LinearLayout) obj2);
        }
    }), new MoreSection("Присутствие", "Подсказки, объявления, тихие часы", Ic.BELL, new Function2() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda33
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return Sections.ALL$lambda$4((SectionScreen) obj, (LinearLayout) obj2);
        }
    }), new MoreSection("Память и безопасность", "Факты, подтверждения, данные", Ic.SHIELD, new Function2() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda34
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return Sections.ALL$lambda$5((SectionScreen) obj, (LinearLayout) obj2);
        }
    }), new MoreSection("Помощь и диагностика", "Гид, консоль, разработка", Ic.HELP, new Function2() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda35
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return Sections.ALL$lambda$6((SectionScreen) obj, (LinearLayout) obj2);
        }
    })});

    private Sections() {
    }

    public final List<MoreSection> getALL() {
        return ALL;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$0(final SectionScreen MoreSection, LinearLayout b) {
        Intrinsics.checkNotNullParameter(MoreSection, "$this$MoreSection");
        Intrinsics.checkNotNullParameter(b, "b");
        final JarvisActivity c = MoreSection.getApp();
        b.addView(MoreSection.head("Слово активации"));
        b.addView(MoreSection.toggle("Слово «Джарвис»", "Команда начинается с обращения", JarvisSettings.INSTANCE.wakeEnabled(c), new Function1() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Sections.ALL$lambda$0$0(c, MoreSection, ((Boolean) obj).booleanValue());
            }
        }));
        b.addView(MoreSection.options("Чувствительность", CollectionsKt.listOf((Object[]) new String[]{"Низкая", "Средняя", "Высокая"}), JarvisSettings.INSTANCE.wakeSensitivity(c), new Function1() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Sections.ALL$lambda$0$1(c, MoreSection, ((Integer) obj).intValue());
            }
        }));
        b.addView(MoreSection.voiceLock());
        b.addView(MoreSection.head("Разговор"));
        b.addView(MoreSection.toggle("Продолжать разговор", "Без повторного обращения", JarvisSettings.INSTANCE.conversationMode(c), new Function1() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Sections.ALL$lambda$0$2(c, ((Boolean) obj).booleanValue());
            }
        }));
        b.addView(MoreSection.pref("Подстраивать слух", "Адаптивное прослушивание", "adaptive_listening", true));
        b.addView(MoreSection.pref("Беречь батарею ниже 20%", "Экономный режим прослушивания", "low_power", false));
        b.addView(MoreSection.head("Распознавание речи"));
        b.addView(MoreSection.options("Режим", MODE_LABELS, ASR_MODES.indexOf(JarvisSettings.INSTANCE.asrMode(c)), new Function1() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Sections.ALL$lambda$0$3(c, ((Integer) obj).intValue());
            }
        }));
        b.addView(MoreSection.urlField("Адрес облачного распознавания", JarvisSettings.INSTANCE.asrCloudBaseUrl(c), new Function1() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Sections.ALL$lambda$0$4(c, (String) obj);
            }
        }));
        b.addView(MoreSection.keyRow("Ключ распознавания", "Для своего сервера", "custom_asr"));
        b.addView(MoreSection.head("Синтез речи"));
        b.addView(MoreSection.options("Режим", MODE_LABELS, ASR_MODES.indexOf(JarvisSettings.INSTANCE.ttsMode(c)), new Function1() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Sections.ALL$lambda$0$5(c, ((Integer) obj).intValue());
            }
        }));
        b.addView(MoreSection.note("Голос Джарвиса, Dmitri запасной"));
        b.addView(MoreSection.urlField("Адрес облачного синтеза", JarvisSettings.INSTANCE.ttsCloudBaseUrl(c), new Function1() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Sections.ALL$lambda$0$6(c, (String) obj);
            }
        }));
        b.addView(MoreSection.keyRow("Ключ синтеза", "Для своего сервера", "custom_tts"));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$0$0(JarvisActivity $c, SectionScreen $this_MoreSection, boolean it) {
        JarvisSettings.INSTANCE.setWakeEnabled($c, it);
        $this_MoreSection.restartVoice();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$0$1(JarvisActivity $c, SectionScreen $this_MoreSection, int it) {
        JarvisSettings.INSTANCE.setWakeSensitivity($c, it);
        $this_MoreSection.restartVoice();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$0$2(JarvisActivity $c, boolean it) {
        JarvisSettings.INSTANCE.setConversationMode($c, it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$0$3(JarvisActivity $c, int it) {
        JarvisSettings.INSTANCE.setAsrMode($c, ASR_MODES.get(it));
        BrainGraph.INSTANCE.invalidate();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$0$4(JarvisActivity $c, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        JarvisSettings.INSTANCE.setAsrCloudBaseUrl($c, it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$0$5(JarvisActivity $c, int it) {
        JarvisSettings.INSTANCE.setTtsMode($c, ASR_MODES.get(it));
        BrainGraph.INSTANCE.invalidate();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$0$6(JarvisActivity $c, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        JarvisSettings.INSTANCE.setTtsCloudBaseUrl($c, it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$1(SectionScreen MoreSection, LinearLayout b) {
        Intrinsics.checkNotNullParameter(MoreSection, "$this$MoreSection");
        Intrinsics.checkNotNullParameter(b, "b");
        final JarvisActivity c = MoreSection.getApp();
        b.addView(MoreSection.head("Личность"));
        b.addView(MoreSection.options("", PERSONA_LABELS, JarvisSettings.INSTANCE.getPERSONAS().indexOf(JarvisSettings.INSTANCE.persona(c)), new Function1() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Sections.ALL$lambda$1$0(c, ((Integer) obj).intValue());
            }
        }));
        b.addView(MoreSection.note("Джарвис: дворецкий, обращение «сэр»"));
        b.addView(MoreSection.head("Ответ"));
        b.addView(MoreSection.options("Скорость и качество", PROFILE_LABELS, JarvisSettings.INSTANCE.getCHAT_PROFILES().indexOf(JarvisSettings.INSTANCE.chatProfile(c)), new Function1() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Sections.ALL$lambda$1$1(c, ((Integer) obj).intValue());
            }
        }));
        b.addView(MoreSection.head("Понимание"));
        b.addView(MoreSection.threshold());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$1$0(JarvisActivity $c, int it) {
        JarvisSettings.INSTANCE.setPersona($c, JarvisSettings.INSTANCE.getPERSONAS().get(it));
        BrainGraph.INSTANCE.invalidate();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$1$1(JarvisActivity $c, int it) {
        JarvisSettings.INSTANCE.setChatProfile($c, JarvisSettings.INSTANCE.getCHAT_PROFILES().get(it));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$2(SectionScreen MoreSection, LinearLayout b) {
        Intrinsics.checkNotNullParameter(MoreSection, "$this$MoreSection");
        Intrinsics.checkNotNullParameter(b, "b");
        final JarvisActivity c = MoreSection.getApp();
        b.addView(MoreSection.head("Где думать"));
        b.addView(MoreSection.options("Маршрут", CollectionsKt.listOf((Object[]) new String[]{"Гибрид", "Локально", "Облако"}), ROUTER_MODES.indexOf(JarvisSettings.INSTANCE.routerMode(c)), new Function1() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Sections.ALL$lambda$2$0(c, ((Integer) obj).intValue());
            }
        }));
        b.addView(MoreSection.toggle("Использовать ИИ-модель", "Иначе только правила и команды", JarvisSettings.INSTANCE.llmEnabled(c), new Function1() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Sections.ALL$lambda$2$1(c, ((Boolean) obj).booleanValue());
            }
        }));
        b.addView(MoreSection.toggle("Предпочитать облако", "Есть сеть и ключ: облако, иначе локально", JarvisSettings.INSTANCE.preferCloudLlm(c), new Function1() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Sections.ALL$lambda$2$2(c, ((Boolean) obj).booleanValue());
            }
        }));
        b.addView(MoreSection.toggle("Предзагрузка в память", "Модель готова до первой фразы", JarvisSettings.INSTANCE.chatPreloadEnabled(c), new Function1() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Sections.ALL$lambda$2$3(c, ((Boolean) obj).booleanValue());
            }
        }));
        b.addView(MoreSection.head("Облачные ключи"));
        b.addView(MoreSection.keyRow("Groq", "Основная быстрая модель", "groq"));
        b.addView(MoreSection.keyRow("Gemini", "Запасная цепочка", "gemini"));
        b.addView(MoreSection.urlField("Адрес своего сервера", JarvisSettings.INSTANCE.cloudBaseUrl(c), new Function1() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Sections.ALL$lambda$2$4(c, (String) obj);
            }
        }));
        b.addView(MoreSection.keyRow("Свой сервер", "Совместимый с OpenAI", "custom"));
        b.addView(MoreSection.note("Личные данные в облако не отправляются"));
        b.addView(MoreSection.head("Локальные модели"));
        MoreSection.models(b);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$2$0(JarvisActivity $c, int pick) {
        int size = ROUTER_MODES.size();
        for (int i = 0; i < size; i++) {
            if (!Intrinsics.areEqual(JarvisSettings.INSTANCE.routerMode($c), ROUTER_MODES.get(pick))) {
                JarvisSettings.INSTANCE.cycleRouterMode($c);
            }
        }
        BrainGraph.INSTANCE.invalidate();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$2$1(JarvisActivity $c, boolean it) {
        JarvisSettings.INSTANCE.setLlmEnabled($c, it);
        BrainGraph.INSTANCE.invalidate();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$2$2(JarvisActivity $c, boolean it) {
        JarvisSettings.INSTANCE.setPreferCloudLlm($c, it);
        BrainGraph.INSTANCE.invalidate();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$2$3(JarvisActivity $c, boolean it) {
        JarvisSettings.INSTANCE.setChatPreloadEnabled($c, it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$2$4(JarvisActivity $c, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        JarvisSettings.INSTANCE.setCloudBaseUrl($c, it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$3(SectionScreen MoreSection, LinearLayout b) {
        Intrinsics.checkNotNullParameter(MoreSection, "$this$MoreSection");
        Intrinsics.checkNotNullParameter(b, "b");
        final JarvisActivity c = MoreSection.getApp();
        b.addView(MoreSection.head("Интерфейс"));
        b.addView(MoreSection.options("Вид", CollectionsKt.listOf((Object[]) new String[]{"Современный", "Классический"}), 0, new Function1() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Sections.ALL$lambda$3$0(c, ((Integer) obj).intValue());
            }
        }));
        b.addView(MoreSection.note("Классический: инженерная консоль. Вернуться можно в его «Профиле»."));
        b.addView(MoreSection.head("Тема"));
        b.addView(MoreSection.skins());
        b.addView(MoreSection.head("Звуки и орб"));
        b.addView(MoreSection.toggle("Звуки интерфейса", "Сигнал активации и эффекты", JarvisSettings.INSTANCE.hudSfx(c), new Function1() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Sections.ALL$lambda$3$1(c, ((Boolean) obj).booleanValue());
            }
        }));
        b.addView(MoreSection.pref("Орб прилипает к краю", "Плавающая кнопка поверх приложений", "companion_orb_snap", true));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$3$0(JarvisActivity $c, int picked) {
        if (picked == 1) {
            UiMode.INSTANCE.switchTo($c, UiStyle.CLASSIC);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$3$1(JarvisActivity $c, boolean it) {
        JarvisSettings.INSTANCE.setHudSfx($c, it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$4(SectionScreen MoreSection, LinearLayout b) {
        Intrinsics.checkNotNullParameter(MoreSection, "$this$MoreSection");
        Intrinsics.checkNotNullParameter(b, "b");
        final JarvisActivity c = MoreSection.getApp();
        b.addView(MoreSection.head("Подсказки"));
        b.addView(MoreSection.pref("Проактивность", "Сам предлагает помощь", "companion_proactive", true));
        b.addView(MoreSection.pref("Фоновые уведомления", "Подсказки в шторке", "companion_bg_notify", true));
        b.addView(MoreSection.pref("Тихие часы", "С 23:00 до 07:00", "companion_quiet", false));
        b.addView(MoreSection.head("Объявления вслух"));
        b.addView(MoreSection.toggle("Звонки", "Кто звонит", JarvisSettings.INSTANCE.announceCalls(c), new Function1() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda23
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Sections.ALL$lambda$4$0(c, ((Boolean) obj).booleanValue());
            }
        }));
        b.addView(MoreSection.toggle("Сообщения", "Кроме групп и ночи", JarvisSettings.INSTANCE.announceMessages(c), new Function1() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda24
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Sections.ALL$lambda$4$1(c, ((Boolean) obj).booleanValue());
            }
        }));
        b.addView(MoreSection.toggle("Встречи и сводка дня", "За 15 минут и после будильника", JarvisSettings.INSTANCE.announceHints(c), new Function1() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda25
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Sections.ALL$lambda$4$2(c, ((Boolean) obj).booleanValue());
            }
        }));
        b.addView(MoreSection.head("Доступы"));
        b.addView(MoreSection.link("Цифровой помощник", "Выбрать Jarvis", new Function0() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda26
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Sections.ALL$lambda$4$3(c);
            }
        }));
        b.addView(MoreSection.link("Чтение уведомлений", "Системная настройка", new Function0() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda27
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Sections.ALL$lambda$4$4(c);
            }
        }));
        b.addView(MoreSection.link("Статистика приложений", "Системная настройка", new Function0() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda28
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Sections.ALL$lambda$4$5(c);
            }
        }));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$4$0(JarvisActivity $c, boolean it) {
        JarvisSettings.INSTANCE.setAnnounce($c, "calls", it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$4$1(JarvisActivity $c, boolean it) {
        JarvisSettings.INSTANCE.setAnnounce($c, "messages", it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$4$2(JarvisActivity $c, boolean it) {
        JarvisSettings.INSTANCE.setAnnounce($c, "hints", it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$4$3(JarvisActivity $c) {
        PermissionsHelper.INSTANCE.openAssistantPickerSettings($c);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$4$4(JarvisActivity $c) {
        PermissionsHelper.INSTANCE.openNotificationListenerSettings($c);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$4$5(JarvisActivity $c) {
        PermissionsHelper.INSTANCE.openUsageAccessSettings($c);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$5(final SectionScreen MoreSection, LinearLayout b) {
        Object objM392constructorimpl;
        Intrinsics.checkNotNullParameter(MoreSection, "$this$MoreSection");
        Intrinsics.checkNotNullParameter(b, "b");
        final JarvisActivity c = MoreSection.getApp();
        b.addView(MoreSection.head("Безопасность"));
        b.addView(MoreSection.pref("Подтверждать важные действия", "Звонки, сообщения, удаление", "confirm_sensitive", true));
        b.addView(MoreSection.note("Шифрование данных включено"));
        b.addView(MoreSection.head("Память"));
        b.addView(MoreSection.toggle("Запоминать факты", "Долгосрочная память на телефоне", JarvisSettings.INSTANCE.memoryEnabled(c), new Function1() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Sections.ALL$lambda$5$0(c, ((Boolean) obj).booleanValue());
            }
        }));
        try {
            Result.Companion companion = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(Integer.valueOf(BrainGraph.INSTANCE.memory(c).count()));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = 0;
        }
        b.addView(MoreSection.link("Сохранённые факты", objM392constructorimpl + ", открыть список", new Function0() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Sections.ALL$lambda$5$2(c);
            }
        }));
        b.addView(MoreSection.link("Очистить память", "Все факты будут удалены", new Function0() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Sections.ALL$lambda$5$3(c, MoreSection);
            }
        }));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$5$0(JarvisActivity $c, boolean it) {
        JarvisSettings.INSTANCE.setMemoryEnabled($c, it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$5$2(JarvisActivity $c) {
        $c.show(JarvisActivity.MEMORY);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$5$3(final JarvisActivity $c, final SectionScreen $this_MoreSection) {
        $c.showConfirm("Все сохранённые факты будут удалены без возможности восстановления.", "Очистить память?", new Function0() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda22
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Sections.ALL$lambda$5$3$0($this_MoreSection, $c);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$5$3$0(SectionScreen $this_MoreSection, JarvisActivity $c) {
        try {
            Result.Companion companion = Result.INSTANCE;
            BrainGraph.INSTANCE.memory($c).clear();
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$6(SectionScreen MoreSection, LinearLayout b) {
        Intrinsics.checkNotNullParameter(MoreSection, "$this$MoreSection");
        Intrinsics.checkNotNullParameter(b, "b");
        final JarvisActivity c = MoreSection.getApp();
        b.addView(MoreSection.head("Помощь"));
        b.addView(MoreSection.link("Как пользоваться", "Короткий гид", new Function0() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda19
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Sections.ALL$lambda$6$0(c);
            }
        }));
        b.addView(MoreSection.link("Текущая ситуация", "Что он сейчас видит", new Function0() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Sections.ALL$lambda$6$1(c);
            }
        }));
        b.addView(MoreSection.head("Для разработчика"));
        b.addView(MoreSection.link("Консоль", "Проверка команд", new Function0() { // from class: com.jarvis.app.ui.Sections$$ExternalSyntheticLambda21
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Sections.ALL$lambda$6$2(c);
            }
        }));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$6$0(JarvisActivity $c) {
        $c.startActivity(new Intent($c, (Class<?>) UsageGuideActivity.class));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$6$1(JarvisActivity $c) {
        $c.show(JarvisActivity.SCAN);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ALL$lambda$6$2(JarvisActivity $c) {
        $c.startActivity(new Intent($c, (Class<?>) TestConsoleActivity.class));
        return Unit.INSTANCE;
    }
}
