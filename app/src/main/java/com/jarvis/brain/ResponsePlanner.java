package com.jarvis.brain;

import com.jarvis.core.common.model.AssistantResponseStatus;
import com.jarvis.core.common.model.ToolResult;
import com.jarvis.core.common.model.ToolResultStatus;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ResponsePlanner.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\n"}, d2 = {"Lcom/jarvis/brain/ResponsePlanner;", "", "<init>", "()V", "plan", "", "result", "Lcom/jarvis/core/common/model/ToolResult;", "assistantStatusOf", "Lcom/jarvis/core/common/model/AssistantResponseStatus;", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ResponsePlanner {
    public static final ResponsePlanner INSTANCE = new ResponsePlanner();

    /* JADX INFO: compiled from: ResponsePlanner.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ToolResultStatus.values().length];
            try {
                iArr[ToolResultStatus.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ToolResultStatus.FAILED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ToolResultStatus.DENIED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[ToolResultStatus.NEEDS_CONFIRMATION.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[ToolResultStatus.UNAVAILABLE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[ToolResultStatus.OFFLINE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[ToolResultStatus.AMBIGUOUS.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private ResponsePlanner() {
    }

    public final String plan(ToolResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        switch (WhenMappings.$EnumSwitchMapping$0[result.getStatus().ordinal()]) {
            case 1:
                String message = result.getMessage();
                return message == null ? "Готово." : message;
            case 2:
                String message2 = result.getMessage();
                return message2 == null ? "Не получилось выполнить действие." : message2;
            case 3:
                String message3 = result.getMessage();
                return message3 == null ? "Политика запрещает это действие." : message3;
            case 4:
                String message4 = result.getMessage();
                return message4 == null ? "Требуется подтверждение." : message4;
            case 5:
                String message5 = result.getMessage();
                return message5 == null ? "Функция недоступна на этом устройстве." : message5;
            case 6:
                String message6 = result.getMessage();
                return message6 == null ? "Для этого нужен интернет, сейчас он недоступен." : message6;
            case 7:
                String message7 = result.getMessage();
                return message7 == null ? "Уточните, сэр." : message7;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final AssistantResponseStatus assistantStatusOf(ToolResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        switch (WhenMappings.$EnumSwitchMapping$0[result.getStatus().ordinal()]) {
            case 1:
                return AssistantResponseStatus.PROCESSED;
            case 2:
            default:
                return AssistantResponseStatus.FAILED;
            case 3:
                return AssistantResponseStatus.DENIED;
            case 4:
                return AssistantResponseStatus.NEEDS_CONFIRMATION;
        }
    }
}
