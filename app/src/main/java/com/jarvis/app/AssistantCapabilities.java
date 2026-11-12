package com.jarvis.app;

import com.jarvis.app.ui.JarvisActivity;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.codec.language.bm.Rule;

/* JADX INFO: compiled from: AssistantCapabilities.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\f\rB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000e"}, d2 = {"Lcom/jarvis/app/AssistantCapabilities;", "", "<init>", "()V", Rule.ALL, "", "Lcom/jarvis/app/AssistantCapabilities$Command;", "getALL", "()Ljava/util/List;", "byCategory", "", "", "RiskTag", "Command", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AssistantCapabilities {
    private static final List<Command> ALL;
    public static final AssistantCapabilities INSTANCE = new AssistantCapabilities();

    /* JADX INFO: compiled from: AssistantCapabilities.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/jarvis/app/AssistantCapabilities$RiskTag;", "", "<init>", "(Ljava/lang/String;I)V", "SAFE", "CONFIRM", "SENSITIVE", "ONLINE", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum RiskTag {
        SAFE,
        CONFIRM,
        SENSITIVE,
        ONLINE;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<RiskTag> getEntries() {
            return $ENTRIES;
        }
    }

    private AssistantCapabilities() {
    }

    /* JADX INFO: compiled from: AssistantCapabilities.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BQ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u000bHÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003Ja\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u0003HÆ\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020'HÖ\u0001J\t\u0010(\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010¨\u0006)"}, d2 = {"Lcom/jarvis/app/AssistantCapabilities$Command;", "", "id", "", "title", "category", "description", "examples", "", "toolId", "risk", "Lcom/jarvis/app/AssistantCapabilities$RiskTag;", "needs", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/jarvis/app/AssistantCapabilities$RiskTag;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getTitle", "getCategory", "getDescription", "getExamples", "()Ljava/util/List;", "getToolId", "getRisk", "()Lcom/jarvis/app/AssistantCapabilities$RiskTag;", "getNeeds", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Command {
        private final String category;
        private final String description;
        private final List<String> examples;
        private final String id;
        private final String needs;
        private final RiskTag risk;
        private final String title;
        private final String toolId;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getCategory() {
            return this.category;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getDescription() {
            return this.description;
        }

        public final List<String> component5() {
            return this.examples;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getToolId() {
            return this.toolId;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final RiskTag getRisk() {
            return this.risk;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getNeeds() {
            return this.needs;
        }

        public final Command copy(String id, String title, String category, String description, List<String> examples, String toolId, RiskTag risk, String needs) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(category, "category");
            Intrinsics.checkNotNullParameter(description, "description");
            Intrinsics.checkNotNullParameter(examples, "examples");
            Intrinsics.checkNotNullParameter(risk, "risk");
            Intrinsics.checkNotNullParameter(needs, "needs");
            return new Command(id, title, category, description, examples, toolId, risk, needs);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Command)) {
                return false;
            }
            Command command = (Command) other;
            return Intrinsics.areEqual(this.id, command.id) && Intrinsics.areEqual(this.title, command.title) && Intrinsics.areEqual(this.category, command.category) && Intrinsics.areEqual(this.description, command.description) && Intrinsics.areEqual(this.examples, command.examples) && Intrinsics.areEqual(this.toolId, command.toolId) && this.risk == command.risk && Intrinsics.areEqual(this.needs, command.needs);
        }

        public int hashCode() {
            return (((((((((((((this.id.hashCode() * 31) + this.title.hashCode()) * 31) + this.category.hashCode()) * 31) + this.description.hashCode()) * 31) + this.examples.hashCode()) * 31) + (this.toolId == null ? 0 : this.toolId.hashCode())) * 31) + this.risk.hashCode()) * 31) + this.needs.hashCode();
        }

        public String toString() {
            return "Command(id=" + this.id + ", title=" + this.title + ", category=" + this.category + ", description=" + this.description + ", examples=" + this.examples + ", toolId=" + this.toolId + ", risk=" + this.risk + ", needs=" + this.needs + ")";
        }

        public Command(String id, String title, String category, String description, List<String> examples, String toolId, RiskTag risk, String needs) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(category, "category");
            Intrinsics.checkNotNullParameter(description, "description");
            Intrinsics.checkNotNullParameter(examples, "examples");
            Intrinsics.checkNotNullParameter(risk, "risk");
            Intrinsics.checkNotNullParameter(needs, "needs");
            this.id = id;
            this.title = title;
            this.category = category;
            this.description = description;
            this.examples = examples;
            this.toolId = toolId;
            this.risk = risk;
            this.needs = needs;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public /* synthetic */ Command(String str, String str2, String str3, String str4, List list, String str5, RiskTag riskTag, String str6, int i, DefaultConstructorMarker defaultConstructorMarker) {
            String str7;
            if ((i & 128) == 0) {
                str7 = str6;
            } else {
                str7 = "";
            }
            this(str, str2, str3, str4, list, str5, riskTag, str7);
        }

        public final String getId() {
            return this.id;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getCategory() {
            return this.category;
        }

        public final String getDescription() {
            return this.description;
        }

        public final List<String> getExamples() {
            return this.examples;
        }

        public final String getToolId() {
            return this.toolId;
        }

        public final RiskTag getRisk() {
            return this.risk;
        }

        public final String getNeeds() {
            return this.needs;
        }
    }

    static {
        int i = 128;
        DefaultConstructorMarker defaultConstructorMarker = null;
        String str = null;
        ALL = CollectionsKt.listOf((Object[]) new Command[]{new Command("flashlight", "Фонарик", "Устройство", "Включить или выключить фонарик камеры.", CollectionsKt.listOf((Object[]) new String[]{"джарвис, включи фонарик", "джарвис, выключи фонарик"}), "flashlight", RiskTag.SAFE, null, 128, null), new Command("volume", "Громкость", "Устройство", "Сделать громче или тише.", CollectionsKt.listOf((Object[]) new String[]{"джарвис, громче", "джарвис, тише", "джарвис, поставь громкость на 50"}), "volume", RiskTag.SAFE, str, i, defaultConstructorMarker), new Command("brightness", "Яркость", "Устройство", "Изменить яркость экрана (нужно разрешение «Изменение системных настроек»).", CollectionsKt.listOf((Object[]) new String[]{"джарвис, ярче", "джарвис, темнее"}), "brightness", RiskTag.SAFE, "WRITE_SETTINGS"), new Command("timer", "Таймер", "Устройство", "Поставить таймер.", CollectionsKt.listOf((Object[]) new String[]{"джарвис, поставь таймер на 5 минут", "джарвис, таймер 10 секунд"}), "timer", RiskTag.SAFE, str, i, defaultConstructorMarker), new Command("launcher", "Запуск приложений", "Приложения", "Открыть приложение по имени.", CollectionsKt.listOf((Object[]) new String[]{"джарвис, открой YouTube", "джарвис, запусти Telegram", "джарвис, открой музыку"}), "launcher", RiskTag.SAFE, null, 128, null), new Command("music", "Музыка", "Приложения", "Управление воспроизведением (play / pause / next).", CollectionsKt.listOf((Object[]) new String[]{"джарвис, включи музыку", "джарвис, пауза", "джарвис, следующая песня"}), "music", RiskTag.SAFE, str, i, defaultConstructorMarker), new Command("weather", "Погода", "Информация", "Погода по городу (нужен интернет).", CollectionsKt.listOf((Object[]) new String[]{"джарвис, какая погода в Москве", "джарвис, погода завтра"}), "weather", RiskTag.ONLINE, str, i, defaultConstructorMarker), new Command("knowledge", "Знания / вопросы", "Информация", "Ответ на вопрос через локальную модель или knowledge-tool.", CollectionsKt.listOf((Object[]) new String[]{"джарвис, что такое квантовая физика", "джарвис, расскажи анекдот"}), "knowledge", RiskTag.SAFE, "Модель диалога"), new Command("calendar", "Календарь", "Органайзер", "Список дел или создание события.", CollectionsKt.listOf((Object[]) new String[]{"джарвис, что у меня сегодня в календаре", "джарвис, создай встречу завтра в 15:00"}), "calendar", RiskTag.CONFIRM, "Календарь"), new Command("contacts", "Контакты", "Связь", "Поиск контакта.", CollectionsKt.listOf("джарвис, найди контакт Маша"), "contacts.search", RiskTag.CONFIRM, "Контакты"), new Command("call", "Звонки", "Связь", "Показать историю или набрать номер (с подтверждением).", CollectionsKt.listOf((Object[]) new String[]{"джарвис, позвони маме", "джарвис, история звонков"}), "phone.call", RiskTag.SENSITIVE, "Телефон"), new Command("sms", "SMS", "Связь", "Прочитать входящие или отправить SMS (с подтверждением).", CollectionsKt.listOf((Object[]) new String[]{"джарвис, прочитай SMS", "джарвис, напиши SMS маме: я опаздываю"}), "sms", RiskTag.SENSITIVE, "SMS"), new Command("telegram", "Telegram", "Связь", "Отправить сообщение в Telegram (с подтверждением).", CollectionsKt.listOf("джарвис, напиши в телеграм Ивану: привет"), "telegram.send", RiskTag.SENSITIVE, "Telegram"), new Command("notifications", "Уведомления", "Система", "Список недавних уведомлений (нужен Notification Listener).", CollectionsKt.listOf((Object[]) new String[]{"джарвис, какие уведомления", "джарвис, что пришло"}), "notifications.list", RiskTag.CONFIRM, "Notification Listener"), new Command("clipboard", "Буфер обмена", "Система", "Прочитать или записать буфер.", CollectionsKt.listOf((Object[]) new String[]{"джарвис, что в буфере", "джарвис, скопируй текст привет"}), "clipboard", RiskTag.SAFE, str, i, defaultConstructorMarker), new Command("screenshot", "Скриншот", "Система", "Сделать снимок экрана.", CollectionsKt.listOf("джарвис, сделай скриншот"), "screenshot", RiskTag.CONFIRM, str, i, defaultConstructorMarker), new Command("files", "Файлы", "Система", "Поиск файлов по имени.", CollectionsKt.listOf("джарвис, найди файл отчёт"), "files.search", RiskTag.CONFIRM, str, i, defaultConstructorMarker), new Command("settings", "Настройки", "Система", "Переключить Wi‑Fi / Bluetooth / режим полёта (где доступно).", CollectionsKt.listOf((Object[]) new String[]{"джарвис, включи Wi‑Fi", "джарвис, выключи Bluetooth"}), "system.setting", RiskTag.CONFIRM, "WRITE_SETTINGS"), new Command(VoiceController.ENTRY_UI, "Управление экраном", "Автоматизация", "Нажатия, ввод, свайпы через Accessibility (нужна служба JARVIS).", CollectionsKt.listOf((Object[]) new String[]{"джарвис, нажми назад", "джарвис, прочитай экран", "джарвис, свайпни вниз"}), "ui.global", RiskTag.SENSITIVE, "Accessibility"), new Command(JarvisActivity.MEMORY, "Память", "Память", "Запомнить факт о тебе (вкладка ПАМЯТЬ).", CollectionsKt.listOf((Object[]) new String[]{"джарвис, запомни: мой день рождения 15 марта", "джарвис, я живу в Москве"}), null, RiskTag.SAFE, str, i, defaultConstructorMarker), new Command("assist", "Системный помощник", "Система", "Долгое нажатие кнопки Home / жест ассистента, если JARVIS выбран цифровым помощником.", CollectionsKt.listOf((Object[]) new String[]{"(жест ассистента)", "долгий Home → JARVIS"}), null, RiskTag.SAFE, "ROLE_ASSISTANT"), new Command(JarvisActivity.LIVE, "LIVE-режим", "Режимы", "Непрерывный диалог без wake-word (вкладка LIVE).", CollectionsKt.listOf("Открой LIVE → Активировать"), null, RiskTag.SAFE, null, 128, null)});
    }

    public final List<Command> getALL() {
        return ALL;
    }

    public final Map<String, List<Command>> byCategory() {
        Object answer$iv$iv$iv;
        Iterable $this$groupBy$iv = ALL;
        Map destination$iv$iv = new LinkedHashMap();
        for (Object element$iv$iv : $this$groupBy$iv) {
            Command it = (Command) element$iv$iv;
            String category = it.getCategory();
            Object value$iv$iv$iv = destination$iv$iv.get(category);
            if (value$iv$iv$iv == null) {
                answer$iv$iv$iv = new ArrayList();
                destination$iv$iv.put(category, answer$iv$iv$iv);
            } else {
                answer$iv$iv$iv = value$iv$iv$iv;
            }
            Object key$iv$iv$iv = answer$iv$iv$iv;
            List list$iv$iv = (List) key$iv$iv$iv;
            list$iv$iv.add(element$iv$iv);
        }
        return destination$iv$iv;
    }
}
