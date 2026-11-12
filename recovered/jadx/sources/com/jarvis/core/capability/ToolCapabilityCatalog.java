package com.jarvis.core.capability;

import com.google.android.material.internal.ViewUtils;
import com.jarvis.app.VoiceController;
import com.jarvis.core.common.Tool;
import com.jarvis.core.common.ToolRegistry;
import com.jarvis.core.common.model.OnlineRequirement;
import com.jarvis.core.common.model.RiskLevel;
import com.jarvis.core.common.model.ToolDefinition;
import com.jarvis.core.common.model.ToolParameter;
import com.jarvis.core.routines.EventMatcher;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.concurrent.AbstractCircuitBreaker;

/* JADX INFO: compiled from: ToolCapabilityCatalog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u000eB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005J\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u0005R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/jarvis/core/capability/ToolCapabilityCatalog;", "", "<init>", "()V", "SPECS", "", "Lcom/jarvis/core/capability/ToolCapabilityCatalog$Spec;", "buildFromTools", "Lcom/jarvis/core/capability/CapabilityRegistry;", "tools", "Lcom/jarvis/core/common/ToolRegistry;", "knownCapabilityIds", "", "knownToolIds", "Spec", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ToolCapabilityCatalog {
    public static final ToolCapabilityCatalog INSTANCE = new ToolCapabilityCatalog();
    private static final List<Spec> SPECS;

    private ToolCapabilityCatalog() {
    }

    /* JADX INFO: compiled from: ToolCapabilityCatalog.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u008b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\u000f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\t\u0010)\u001a\u00020\fHÆ\u0003J\t\u0010*\u001a\u00020\u000eHÆ\u0003J\u000f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\u000f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J\u0095\u0001\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0001J\u0013\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00101\u001a\u000202HÖ\u0001J\t\u00103\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0019R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0019¨\u00064"}, d2 = {"Lcom/jarvis/core/capability/ToolCapabilityCatalog$Spec;", "", "id", "", "title", "description", "toolId", "operations", "", "tags", "examples", "availability", "Lcom/jarvis/core/capability/CapabilityAvailability;", "confirmation", "Lcom/jarvis/core/capability/ConfirmationPolicy;", "constraints", "resultHints", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lcom/jarvis/core/capability/CapabilityAvailability;Lcom/jarvis/core/capability/ConfirmationPolicy;Ljava/util/List;Ljava/util/List;)V", "getId", "()Ljava/lang/String;", "getTitle", "getDescription", "getToolId", "getOperations", "()Ljava/util/List;", "getTags", "getExamples", "getAvailability", "()Lcom/jarvis/core/capability/CapabilityAvailability;", "getConfirmation", "()Lcom/jarvis/core/capability/ConfirmationPolicy;", "getConstraints", "getResultHints", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "equals", "", "other", "hashCode", "", "toString", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final /* data */ class Spec {
        private final CapabilityAvailability availability;
        private final ConfirmationPolicy confirmation;
        private final List<String> constraints;
        private final String description;
        private final List<String> examples;
        private final String id;
        private final List<String> operations;
        private final List<String> resultHints;
        private final List<String> tags;
        private final String title;
        private final String toolId;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        public final List<String> component10() {
            return this.constraints;
        }

        public final List<String> component11() {
            return this.resultHints;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getDescription() {
            return this.description;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getToolId() {
            return this.toolId;
        }

        public final List<String> component5() {
            return this.operations;
        }

        public final List<String> component6() {
            return this.tags;
        }

        public final List<String> component7() {
            return this.examples;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final CapabilityAvailability getAvailability() {
            return this.availability;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final ConfirmationPolicy getConfirmation() {
            return this.confirmation;
        }

        public final Spec copy(String id, String title, String description, String toolId, List<String> operations, List<String> tags, List<String> examples, CapabilityAvailability availability, ConfirmationPolicy confirmation, List<String> constraints, List<String> resultHints) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(description, "description");
            Intrinsics.checkNotNullParameter(toolId, "toolId");
            Intrinsics.checkNotNullParameter(operations, "operations");
            Intrinsics.checkNotNullParameter(tags, "tags");
            Intrinsics.checkNotNullParameter(examples, "examples");
            Intrinsics.checkNotNullParameter(availability, "availability");
            Intrinsics.checkNotNullParameter(confirmation, "confirmation");
            Intrinsics.checkNotNullParameter(constraints, "constraints");
            Intrinsics.checkNotNullParameter(resultHints, "resultHints");
            return new Spec(id, title, description, toolId, operations, tags, examples, availability, confirmation, constraints, resultHints);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Spec)) {
                return false;
            }
            Spec spec = (Spec) other;
            return Intrinsics.areEqual(this.id, spec.id) && Intrinsics.areEqual(this.title, spec.title) && Intrinsics.areEqual(this.description, spec.description) && Intrinsics.areEqual(this.toolId, spec.toolId) && Intrinsics.areEqual(this.operations, spec.operations) && Intrinsics.areEqual(this.tags, spec.tags) && Intrinsics.areEqual(this.examples, spec.examples) && this.availability == spec.availability && this.confirmation == spec.confirmation && Intrinsics.areEqual(this.constraints, spec.constraints) && Intrinsics.areEqual(this.resultHints, spec.resultHints);
        }

        public int hashCode() {
            return (((((((((((((((((((this.id.hashCode() * 31) + this.title.hashCode()) * 31) + this.description.hashCode()) * 31) + this.toolId.hashCode()) * 31) + this.operations.hashCode()) * 31) + this.tags.hashCode()) * 31) + this.examples.hashCode()) * 31) + this.availability.hashCode()) * 31) + this.confirmation.hashCode()) * 31) + this.constraints.hashCode()) * 31) + this.resultHints.hashCode();
        }

        public String toString() {
            return "Spec(id=" + this.id + ", title=" + this.title + ", description=" + this.description + ", toolId=" + this.toolId + ", operations=" + this.operations + ", tags=" + this.tags + ", examples=" + this.examples + ", availability=" + this.availability + ", confirmation=" + this.confirmation + ", constraints=" + this.constraints + ", resultHints=" + this.resultHints + ")";
        }

        public Spec(String id, String title, String description, String toolId, List<String> operations, List<String> tags, List<String> examples, CapabilityAvailability availability, ConfirmationPolicy confirmation, List<String> constraints, List<String> resultHints) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(description, "description");
            Intrinsics.checkNotNullParameter(toolId, "toolId");
            Intrinsics.checkNotNullParameter(operations, "operations");
            Intrinsics.checkNotNullParameter(tags, "tags");
            Intrinsics.checkNotNullParameter(examples, "examples");
            Intrinsics.checkNotNullParameter(availability, "availability");
            Intrinsics.checkNotNullParameter(confirmation, "confirmation");
            Intrinsics.checkNotNullParameter(constraints, "constraints");
            Intrinsics.checkNotNullParameter(resultHints, "resultHints");
            this.id = id;
            this.title = title;
            this.description = description;
            this.toolId = toolId;
            this.operations = operations;
            this.tags = tags;
            this.examples = examples;
            this.availability = availability;
            this.confirmation = confirmation;
            this.constraints = constraints;
            this.resultHints = resultHints;
        }

        public /* synthetic */ Spec(String str, String str2, String str3, String str4, List list, List list2, List list3, CapabilityAvailability capabilityAvailability, ConfirmationPolicy confirmationPolicy, List list4, List list5, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, str4, (i & 16) != 0 ? CollectionsKt.emptyList() : list, (i & 32) != 0 ? CollectionsKt.emptyList() : list2, (i & 64) != 0 ? CollectionsKt.emptyList() : list3, (i & 128) != 0 ? CapabilityAvailability.ALWAYS : capabilityAvailability, (i & 256) != 0 ? ConfirmationPolicy.BY_RISK : confirmationPolicy, (i & 512) != 0 ? CollectionsKt.emptyList() : list4, (i & 1024) != 0 ? CollectionsKt.emptyList() : list5);
        }

        public final String getId() {
            return this.id;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getDescription() {
            return this.description;
        }

        public final String getToolId() {
            return this.toolId;
        }

        public final List<String> getOperations() {
            return this.operations;
        }

        public final List<String> getTags() {
            return this.tags;
        }

        public final List<String> getExamples() {
            return this.examples;
        }

        public final CapabilityAvailability getAvailability() {
            return this.availability;
        }

        public final ConfirmationPolicy getConfirmation() {
            return this.confirmation;
        }

        public final List<String> getConstraints() {
            return this.constraints;
        }

        public final List<String> getResultHints() {
            return this.resultHints;
        }
    }

    static {
        int i = 1920;
        DefaultConstructorMarker defaultConstructorMarker = null;
        CapabilityAvailability capabilityAvailability = null;
        ConfirmationPolicy confirmationPolicy = null;
        List list = null;
        List list2 = null;
        DefaultConstructorMarker defaultConstructorMarker2 = null;
        CapabilityAvailability capabilityAvailability2 = null;
        List list3 = null;
        List list4 = null;
        int i2 = 1024;
        List list5 = null;
        int i3 = 1792;
        ConfirmationPolicy confirmationPolicy2 = null;
        int i4 = 1792;
        List list6 = null;
        int i5 = 1792;
        int i6 = 1792;
        ConfirmationPolicy confirmationPolicy3 = null;
        SPECS = CollectionsKt.listOf((Object[]) new Spec[]{new Spec("DEVICE_LIGHT", "Фонарик", "Управление вспышкой камеры: включить, выключить, переключить.", "flashlight", CollectionsKt.listOf((Object[]) new String[]{"on", "off", "toggle"}), CollectionsKt.listOf((Object[]) new String[]{"flashlight", "light", "фонарик", "свет"}), CollectionsKt.listOf((Object[]) new String[]{"включи фонарик", "выключи свет", "посвети"}), null, null, null, CollectionsKt.listOf("on|off state"), 896, null), new Spec("DEVICE_AUDIO", "Громкость", "Изменение громкости медиа/звонка: громче, тише, процент.", "volume", CollectionsKt.listOf((Object[]) new String[]{"up", "down", "set", "mute"}), CollectionsKt.listOf((Object[]) new String[]{"volume", "audio", "громкость", "звук"}), CollectionsKt.listOf((Object[]) new String[]{"сделай громче", "тише", "громкость 50"}), null, null, null, null, 1920, null), new Spec("DEVICE_DISPLAY", "Яркость", "Яркость экрана: ярче, темнее, уровень.", "brightness", CollectionsKt.listOf((Object[]) new String[]{"up", "down", "set"}), CollectionsKt.listOf((Object[]) new String[]{"brightness", "яркость", "экран"}), CollectionsKt.listOf((Object[]) new String[]{"сделай ярче", "приглуши экран"}), null, null, null, null, 1920, null), new Spec("DEVICE_TIMER", "Таймер", "Одноразовый таймер / напоминание через N минут.", "timer", CollectionsKt.listOf((Object[]) new String[]{"set", "cancel"}), CollectionsKt.listOf((Object[]) new String[]{"timer", "таймер", "будильник"}), CollectionsKt.listOf((Object[]) new String[]{"таймер на 5 минут", "напомни через час"}), capabilityAvailability, confirmationPolicy, list, list2, i, defaultConstructorMarker), new Spec("MEDIA_PLAYBACK", "Музыка", "Воспроизведение, пауза, следующий трек, избранное.", "music", CollectionsKt.listOf((Object[]) new String[]{"play", "pause", "resume", "next", "previous"}), CollectionsKt.listOf((Object[]) new String[]{"music", "media", "музыка", "песня"}), CollectionsKt.listOf((Object[]) new String[]{"включи музыку", "пауза", "следующий трек"}), capabilityAvailability2, null, list3, list4, 1920, defaultConstructorMarker2), new Spec("APP_LAUNCH", "Приложения", "Открыть / свернуть / force-stop приложение по имени.", "launcher", CollectionsKt.listOf((Object[]) new String[]{AbstractCircuitBreaker.PROPERTY_NAME, "minimize", "force_stop"}), CollectionsKt.listOf((Object[]) new String[]{"launcher", "app", "приложение", "открой"}), CollectionsKt.listOf((Object[]) new String[]{"открой телеграм", "запусти настройки"}), capabilityAvailability, confirmationPolicy, list, list2, i, defaultConstructorMarker), new Spec("SYSTEM_CONFIGURATION", "Системные настройки", "Включить/выключить Wi‑Fi, Bluetooth и другие настройки.", "system.setting", CollectionsKt.listOf((Object[]) new String[]{"enable", "disable", AbstractCircuitBreaker.PROPERTY_NAME}), CollectionsKt.listOf((Object[]) new String[]{"setting", "system", EventMatcher.WIFI, "bluetooth", "настройки"}), CollectionsKt.listOf((Object[]) new String[]{"включи wifi", "выключи bluetooth"}), capabilityAvailability2, ConfirmationPolicy.BY_RISK, list3, list4, 1664, defaultConstructorMarker2), new Spec("COMMUNICATION_CALL", "Звонок", "Позвонить контакту или по номеру.", "phone.call", CollectionsKt.listOf((Object[]) new String[]{"call", "history"}), CollectionsKt.listOf((Object[]) new String[]{"call", "phone", "звонок", "набери"}), CollectionsKt.listOf((Object[]) new String[]{"позвони маме", "набери этот номер"}), CapabilityAvailability.NEEDS_PERMISSION, ConfirmationPolicy.ALWAYS_CONFIRM, CollectionsKt.listOf("requires_confirmation"), list2, i2, defaultConstructorMarker), new Spec("COMMUNICATION_SMS", "SMS", "Отправить или прочитать SMS.", "sms", CollectionsKt.listOf((Object[]) new String[]{"send", "read"}), CollectionsKt.listOf((Object[]) new String[]{"sms", "message", "смс", "сообщение"}), CollectionsKt.listOf((Object[]) new String[]{"напиши смс", "прочитай последние смс"}), CapabilityAvailability.NEEDS_PERMISSION, ConfirmationPolicy.ALWAYS_CONFIRM, CollectionsKt.listOf("requires_confirmation"), list2, i2, defaultConstructorMarker), new Spec("COMMUNICATION_TELEGRAM", "Telegram", "Отправить сообщение в Telegram через UI automation.", "telegram.send", CollectionsKt.listOf("send"), CollectionsKt.listOf((Object[]) new String[]{"telegram", "тг", "телеграм"}), CollectionsKt.listOf("напиши в телеграм"), CapabilityAvailability.NEEDS_ACCESSIBILITY, ConfirmationPolicy.BY_RISK, list5, list2, 1536, defaultConstructorMarker), new Spec("CONTACTS_LOOKUP", "Контакты", "Найти контакт по имени.", "contacts.search", CollectionsKt.listOf("search"), CollectionsKt.listOf((Object[]) new String[]{"contacts", "контакт"}), CollectionsKt.listOf("найди номер мамы"), CapabilityAvailability.NEEDS_PERMISSION, confirmationPolicy2, list5, list2, i3, defaultConstructorMarker), new Spec("CALENDAR_EVENT", "Календарь", "Создать событие или показать ближайшие.", "calendar", CollectionsKt.listOf((Object[]) new String[]{"create", "list"}), CollectionsKt.listOf((Object[]) new String[]{"calendar", "календарь", "событие"}), CollectionsKt.listOf((Object[]) new String[]{"создай событие", "что в календаре"}), CapabilityAvailability.NEEDS_PERMISSION, confirmationPolicy2, list5, list2, i3, defaultConstructorMarker), new Spec("KNOWLEDGE_LOOKUP", "Знания", "Краткий факт из Википедии / поиска.", "knowledge", CollectionsKt.listOf((Object[]) new String[]{"lookup", "follow_up"}), CollectionsKt.listOf((Object[]) new String[]{"knowledge", "wiki", "знания", "расскажи"}), CollectionsKt.listOf((Object[]) new String[]{"кто такой Пушкин", "расскажи про дельфинов"}), CapabilityAvailability.NEEDS_ONLINE, confirmationPolicy2, list5, CollectionsKt.listOf((Object[]) new String[]{"speech_text", "source", "title"}), ViewUtils.EDGE_TO_EDGE_FLAGS, defaultConstructorMarker), new Spec("WEATHER_LOOKUP", "Погода", "Текущая погода и прогноз по городу.", "weather", CollectionsKt.listOf((Object[]) new String[]{"current", "forecast"}), CollectionsKt.listOf((Object[]) new String[]{"weather", "погода"}), CollectionsKt.listOf((Object[]) new String[]{"какая погода", "погода в Москве"}), CapabilityAvailability.NEEDS_ONLINE, confirmationPolicy2, list5, list6, i4, defaultConstructorMarker), new Spec("FILES_SEARCH", "Файлы", "Поиск файлов на устройстве.", "files.search", CollectionsKt.listOf("search"), CollectionsKt.listOf((Object[]) new String[]{"files", "файл"}), CollectionsKt.listOf("найди файл презентация"), CapabilityAvailability.NEEDS_PERMISSION, confirmationPolicy2, list5, list6, i4, defaultConstructorMarker), new Spec("CLIPBOARD_ACCESS", "Буфер обмена", "Прочитать или записать буфер обмена.", "clipboard", CollectionsKt.listOf((Object[]) new String[]{"read", "write"}), CollectionsKt.listOf((Object[]) new String[]{"clipboard", "буфер"}), CollectionsKt.listOf((Object[]) new String[]{"что в буфере", "скопируй текст"}), null, confirmationPolicy2, list5, list6, 1920, defaultConstructorMarker), new Spec("NOTIFICATIONS_READ", "Уведомления", "Список недавних уведомлений.", "notifications.list", CollectionsKt.listOf("list"), CollectionsKt.listOf((Object[]) new String[]{"notifications", "уведомления"}), CollectionsKt.listOf("какие уведомления"), CapabilityAvailability.NEEDS_PERMISSION, confirmationPolicy2, list5, list6, i5, defaultConstructorMarker), new Spec("SCREEN_CAPTURE", "Скриншот", "Сделать снимок экрана.", "screenshot", CollectionsKt.listOf("capture"), CollectionsKt.listOf((Object[]) new String[]{"screenshot", "скриншот"}), CollectionsKt.listOf("сделай скриншот"), CapabilityAvailability.NEEDS_PERMISSION, confirmationPolicy2, list5, list6, i5, defaultConstructorMarker), new Spec("UI_CLICK", "UI · нажатие", "Нажать элемент на экране по тексту/описанию.", "ui.click", CollectionsKt.listOf("click"), CollectionsKt.listOf((Object[]) new String[]{VoiceController.ENTRY_UI, "click", "нажми", "screen"}), CollectionsKt.listOf("нажми отправить"), CapabilityAvailability.NEEDS_ACCESSIBILITY, ConfirmationPolicy.BY_RISK, list5, list6, 1536, defaultConstructorMarker), new Spec("UI_TYPE", "UI · ввод", "Ввести текст в поле на экране.", "ui.type", CollectionsKt.listOf("type"), CollectionsKt.listOf((Object[]) new String[]{VoiceController.ENTRY_UI, "type", "введи", "screen"}), CollectionsKt.listOf("введи привет"), CapabilityAvailability.NEEDS_ACCESSIBILITY, confirmationPolicy3, list5, list6, i6, defaultConstructorMarker), new Spec("UI_SWIPE", "UI · свайп", "Свайп по экрану.", "ui.swipe", CollectionsKt.listOf("swipe"), CollectionsKt.listOf((Object[]) new String[]{VoiceController.ENTRY_UI, "swipe", "свайп", "screen"}), CollectionsKt.listOf("свайпни вверх"), CapabilityAvailability.NEEDS_ACCESSIBILITY, confirmationPolicy3, list5, list6, i6, defaultConstructorMarker), new Spec("UI_GLOBAL", "UI · системные жесты", "Назад, домой, недавние.", "ui.global", CollectionsKt.listOf((Object[]) new String[]{"back", "home", "recents"}), CollectionsKt.listOf((Object[]) new String[]{VoiceController.ENTRY_UI, "global", "назад", "домой", "screen"}), CollectionsKt.listOf((Object[]) new String[]{"назад", "на главный экран"}), CapabilityAvailability.NEEDS_ACCESSIBILITY, confirmationPolicy3, list5, list6, i6, defaultConstructorMarker), new Spec("UI_READ", "UI · чтение экрана", "Прочитать текст / структуру экрана.", "ui.read", CollectionsKt.listOf("read"), CollectionsKt.listOf((Object[]) new String[]{VoiceController.ENTRY_UI, "read", "экран", "screen"}), CollectionsKt.listOf("что на экране"), CapabilityAvailability.NEEDS_ACCESSIBILITY, confirmationPolicy3, list5, list6, i6, defaultConstructorMarker), new Spec("UI_WAIT", "UI · ожидание", "Подождать появления текста/пакета на экране.", "ui.wait", CollectionsKt.listOf("wait"), CollectionsKt.listOf((Object[]) new String[]{VoiceController.ENTRY_UI, "wait", "подожди", "screen"}), CollectionsKt.listOf("подожди пока загрузится"), CapabilityAvailability.NEEDS_ACCESSIBILITY, confirmationPolicy3, list5, list6, i6, defaultConstructorMarker)});
    }

    public final CapabilityRegistry buildFromTools(ToolRegistry tools) {
        CapabilityAvailability availability;
        ConfirmationPolicy confirmation;
        ToolRegistry tools2 = tools;
        Intrinsics.checkNotNullParameter(tools2, "tools");
        CapabilityRegistry reg = new CapabilityRegistry();
        for (Spec spec : SPECS) {
            Tool tool = tools2.find(spec.getToolId());
            if (tool != null) {
                ToolDefinition def = tool.getDefinition();
                String id = spec.getId();
                String title = spec.getTitle();
                String description = spec.getDescription();
                if (StringsKt.isBlank(description)) {
                    description = def.getDescription();
                }
                String str = description;
                List<String> operations = spec.getOperations();
                List<ToolParameter> parameters = def.getParameters();
                List<String> resultHints = spec.getResultHints();
                List<String> requiredPermissions = def.getRequiredPermissions();
                RiskLevel riskLevel = def.getRiskLevel();
                OnlineRequirement onlineRequirement = def.getOnlineRequirement();
                if (def.getOnlineRequirement() == OnlineRequirement.ONLINE_REQUIRED) {
                    availability = CapabilityAvailability.NEEDS_ONLINE;
                } else {
                    availability = spec.getAvailability();
                }
                if (def.getRiskLevel() == RiskLevel.SENSITIVE || def.getRiskLevel() == RiskLevel.CONFIRM || def.getRiskLevel() == RiskLevel.BLOCKED) {
                    confirmation = ConfirmationPolicy.ALWAYS_CONFIRM;
                } else {
                    confirmation = spec.getConfirmation();
                }
                reg.register(new CapabilityDescriptor(id, title, str, operations, parameters, resultHints, requiredPermissions, riskLevel, onlineRequirement, availability, confirmation, spec.getToolId(), spec.getTags(), spec.getExamples(), spec.getConstraints()));
                tools2 = tools;
            }
        }
        return reg;
    }

    public final List<String> knownCapabilityIds() {
        Iterable $this$map$iv = SPECS;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            Spec it = (Spec) item$iv$iv;
            destination$iv$iv.add(it.getId());
        }
        return (List) destination$iv$iv;
    }

    public final List<String> knownToolIds() {
        Iterable $this$map$iv = SPECS;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            Spec it = (Spec) item$iv$iv;
            destination$iv$iv.add(it.getToolId());
        }
        return (List) destination$iv$iv;
    }
}
