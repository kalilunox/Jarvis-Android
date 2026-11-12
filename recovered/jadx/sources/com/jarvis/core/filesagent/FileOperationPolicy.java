package com.jarvis.core.filesagent;

import com.jarvis.core.common.model.RiskLevel;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FilesAgent.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\u000e"}, d2 = {"Lcom/jarvis/core/filesagent/FileOperationPolicy;", "", "<init>", "()V", "riskHint", "Lcom/jarvis/core/filesagent/FileOpRiskHint;", "op", "Lcom/jarvis/core/filesagent/FileOperation;", "riskLevel", "Lcom/jarvis/core/common/model/RiskLevel;", "requiresConfirmation", "", "toolIdFor", "", "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FileOperationPolicy {
    public static final FileOperationPolicy INSTANCE = new FileOperationPolicy();

    /* JADX INFO: compiled from: FilesAgent.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[FileOperation.values().length];
            try {
                iArr[FileOperation.SEARCH.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[FileOperation.INSPECT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[FileOperation.READ.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[FileOperation.METADATA.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[FileOperation.CREATE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[FileOperation.RENAME.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[FileOperation.MOVE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[FileOperation.ORGANIZE.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[FileOperation.DELETE.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[FileOpRiskHint.values().length];
            try {
                iArr2[FileOpRiskHint.SAFE_READ.ordinal()] = 1;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr2[FileOpRiskHint.MUTATING.ordinal()] = 2;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr2[FileOpRiskHint.DESTRUCTIVE.ordinal()] = 3;
            } catch (NoSuchFieldError e12) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    private FileOperationPolicy() {
    }

    public final FileOpRiskHint riskHint(FileOperation op) {
        Intrinsics.checkNotNullParameter(op, "op");
        switch (WhenMappings.$EnumSwitchMapping$0[op.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                return FileOpRiskHint.SAFE_READ;
            case 5:
            case 6:
            case 7:
            case 8:
                return FileOpRiskHint.MUTATING;
            case 9:
                return FileOpRiskHint.DESTRUCTIVE;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final RiskLevel riskLevel(FileOperation op) {
        Intrinsics.checkNotNullParameter(op, "op");
        switch (WhenMappings.$EnumSwitchMapping$1[riskHint(op).ordinal()]) {
            case 1:
                return RiskLevel.LOW;
            case 2:
                return RiskLevel.CONFIRM;
            case 3:
                return RiskLevel.SENSITIVE;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final boolean requiresConfirmation(FileOperation op) {
        Intrinsics.checkNotNullParameter(op, "op");
        return riskHint(op) != FileOpRiskHint.SAFE_READ;
    }

    public final String toolIdFor(FileOperation op) {
        Intrinsics.checkNotNullParameter(op, "op");
        if (WhenMappings.$EnumSwitchMapping$0[op.ordinal()] == 1) {
            return "files.search";
        }
        String lowerCase = op.name().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return "files." + lowerCase;
    }
}
