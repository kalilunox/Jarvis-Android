package com.jarvis.core.filesagent;

import com.google.android.gms.actions.SearchIntents;
import com.jarvis.app.VoiceService;
import com.jarvis.core.planner.ActionPlan;
import com.jarvis.core.planner.ActionPlanStatus;
import com.jarvis.core.planner.ActionPlanValidation;
import com.jarvis.core.planner.ActionPlanValidator;
import com.jarvis.core.planner.PlanConditionOp;
import com.jarvis.core.planner.PlanRetryPolicy;
import com.jarvis.core.planner.PlanStep;
import com.jarvis.core.planner.PlanStepCondition;
import com.jarvis.core.planner.PlanStepKind;
import com.jarvis.core.planner.UserGoal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: FilesAgent.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ&\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000b¨\u0006\u0011"}, d2 = {"Lcom/jarvis/core/filesagent/FilesAgent;", "", "<init>", "()V", "plan", "Lcom/jarvis/core/planner/ActionPlan;", "request", "Lcom/jarvis/core/filesagent/FileAgentRequest;", "inferOperation", "Lcom/jarvis/core/filesagent/FileOperation;", VoiceService.EXTRA_TEXT, "", "planFromGoal", "goal", "Lcom/jarvis/core/planner/UserGoal;", "path", SearchIntents.EXTRA_QUERY, "core_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FilesAgent {
    public final ActionPlan plan(FileAgentRequest request) {
        String prev;
        Intrinsics.checkNotNullParameter(request, "request");
        String planId = "files-" + UUID.randomUUID();
        FileOperation op = request.getOperation();
        boolean needsConfirm = FileOperationPolicy.INSTANCE.requiresConfirmation(op);
        List steps = new ArrayList();
        if (needsConfirm) {
            PlanStepKind planStepKind = PlanStepKind.CONFIRM;
            String lowerCase = op.name().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            steps.add(new PlanStep("files-confirm", planStepKind, null, null, null, null, null, null, true, null, 0L, null, null, false, "Confirm file " + lowerCase, CollectionsKt.listOf((Object[]) new String[]{"risk=" + FileOperationPolicy.INSTANCE.riskLevel(op), "hint=" + FileOperationPolicy.INSTANCE.riskHint(op)}), 16124, null));
            prev = "files-confirm";
        } else {
            prev = null;
        }
        String toolId = FileOperationPolicy.INSTANCE.toolIdFor(op);
        LinkedHashMap params = new LinkedHashMap();
        String it = request.getQuery();
        if (it != null) {
            if (StringsKt.isBlank(it)) {
                it = null;
            }
            if (it != null) {
                params.put(SearchIntents.EXTRA_QUERY, it);
            }
        }
        String it2 = request.getPath();
        if (it2 != null) {
            if (StringsKt.isBlank(it2)) {
                it2 = null;
            }
            if (it2 != null) {
                params.put("path", it2);
            }
        }
        String it3 = request.getDestination();
        if (it3 != null) {
            if (StringsKt.isBlank(it3)) {
                it3 = null;
            }
            if (it3 != null) {
                params.put("destination", it3);
            }
        }
        String it4 = request.getContentHint();
        if (it4 != null) {
            if (StringsKt.isBlank(it4)) {
                it4 = null;
            }
            if (it4 != null) {
                params.put("content", StringsKt.take(it4, 512));
            }
        }
        if (op == FileOperation.SEARCH) {
            CharSequence charSequence = (CharSequence) params.get(SearchIntents.EXTRA_QUERY);
            if (charSequence == null || StringsKt.isBlank(charSequence)) {
                params.put(SearchIntents.EXTRA_QUERY, StringsKt.take(request.getGoal().getText(), 200));
            }
        }
        List list = steps;
        PlanStepKind planStepKind2 = PlanStepKind.TOOL;
        LinkedHashMap linkedHashMap = params;
        List listListOf = prev != null ? CollectionsKt.listOf(prev) : null;
        if (listListOf == null) {
            listListOf = CollectionsKt.emptyList();
        }
        List list2 = listListOf;
        PlanStepCondition planStepCondition = prev != null ? new PlanStepCondition(PlanConditionOp.PREV_SUCCESS, prev, null, null, 12, null) : new PlanStepCondition(null, null, null, null, 15, null);
        PlanRetryPolicy planRetryPolicy = new PlanRetryPolicy(op == FileOperation.SEARCH ? 2 : 1, false, 2, null);
        String strName = op.name();
        String path = request.getPath();
        if (path == null && (path = request.getQuery()) == null) {
            path = StringsKt.take(request.getGoal().getText(), 40);
        }
        list.add(new PlanStep("files-op", planStepKind2, toolId, null, linkedHashMap, null, list2, planStepCondition, needsConfirm, planRetryPolicy, 0L, null, null, needsConfirm, strName + ": " + path, CollectionsKt.listOf((Object[]) new String[]{"files_agent=true", "operation=" + op, "risk=" + FileOperationPolicy.INSTANCE.riskLevel(op)}), 7208, null));
        ActionPlan plan = new ActionPlan(planId, request.getGoal().getGoalId(), ActionPlanStatus.READY, steps, ((PlanStep) CollectionsKt.first(steps)).getStepId(), false, MapsKt.mapOf(TuplesKt.to("files-op", "output")), CollectionsKt.listOf((Object[]) new String[]{"planner=files_agent", "op=" + op}), 32, null);
        ActionPlanValidation v = ActionPlanValidator.INSTANCE.validate(plan);
        if (v.getOk()) {
            return plan;
        }
        ActionPlanStatus actionPlanStatus = ActionPlanStatus.INVALID;
        List<String> notes = plan.getNotes();
        Iterable $this$map$iv = v.getErrors();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            destination$iv$iv.add("err=" + ((String) item$iv$iv));
            planId = planId;
        }
        return plan.copy((91 & 1) != 0 ? plan.planId : null, (91 & 2) != 0 ? plan.goalId : null, (91 & 4) != 0 ? plan.status : actionPlanStatus, (91 & 8) != 0 ? plan.steps : null, (91 & 16) != 0 ? plan.entryStepId : null, (91 & 32) != 0 ? plan.cancellationRequested : false, (91 & 64) != 0 ? plan.resultSlots : null, (91 & 128) != 0 ? plan.notes : CollectionsKt.plus((Collection) notes, destination$iv$iv));
    }

    public final FileOperation inferOperation(String text) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        Intrinsics.checkNotNullParameter(text, "text");
        String t = text.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(t, "toLowerCase(...)");
        boolean z8 = false;
        Iterable $this$any$iv = CollectionsKt.listOf((Object[]) new String[]{"удали", "delete", "стереть", "remove"});
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    String it2 = (String) element$iv;
                    if (StringsKt.contains$default((CharSequence) t, (CharSequence) it2, false, 2, (Object) null)) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
        } else {
            z = false;
        }
        if (z) {
            return FileOperation.DELETE;
        }
        Iterable $this$any$iv2 = CollectionsKt.listOf((Object[]) new String[]{"переимен", "rename"});
        if (!($this$any$iv2 instanceof Collection) || !((Collection) $this$any$iv2).isEmpty()) {
            Iterator it3 = $this$any$iv2.iterator();
            while (true) {
                if (it3.hasNext()) {
                    Object element$iv2 = it3.next();
                    String it4 = (String) element$iv2;
                    if (StringsKt.contains$default((CharSequence) t, (CharSequence) it4, false, 2, (Object) null)) {
                        z2 = true;
                        break;
                    }
                } else {
                    z2 = false;
                    break;
                }
            }
        } else {
            z2 = false;
        }
        if (z2) {
            return FileOperation.RENAME;
        }
        Iterable $this$any$iv3 = CollectionsKt.listOf((Object[]) new String[]{"перемест", "move", "перенеси"});
        if (!($this$any$iv3 instanceof Collection) || !((Collection) $this$any$iv3).isEmpty()) {
            Iterator it5 = $this$any$iv3.iterator();
            while (true) {
                if (it5.hasNext()) {
                    Object element$iv3 = it5.next();
                    String it6 = (String) element$iv3;
                    if (StringsKt.contains$default((CharSequence) t, (CharSequence) it6, false, 2, (Object) null)) {
                        z3 = true;
                        break;
                    }
                } else {
                    z3 = false;
                    break;
                }
            }
        } else {
            z3 = false;
        }
        if (z3) {
            return FileOperation.MOVE;
        }
        Iterable $this$any$iv4 = CollectionsKt.listOf((Object[]) new String[]{"создай", "create", "новый файл"});
        if (!($this$any$iv4 instanceof Collection) || !((Collection) $this$any$iv4).isEmpty()) {
            Iterator it7 = $this$any$iv4.iterator();
            while (true) {
                if (it7.hasNext()) {
                    Object element$iv4 = it7.next();
                    String it8 = (String) element$iv4;
                    if (StringsKt.contains$default((CharSequence) t, (CharSequence) it8, false, 2, (Object) null)) {
                        z4 = true;
                        break;
                    }
                } else {
                    z4 = false;
                    break;
                }
            }
        } else {
            z4 = false;
        }
        if (z4) {
            return FileOperation.CREATE;
        }
        Iterable $this$any$iv5 = CollectionsKt.listOf((Object[]) new String[]{"организ", "разложи", "organize"});
        if (!($this$any$iv5 instanceof Collection) || !((Collection) $this$any$iv5).isEmpty()) {
            Iterator it9 = $this$any$iv5.iterator();
            while (true) {
                if (it9.hasNext()) {
                    Object element$iv5 = it9.next();
                    String it10 = (String) element$iv5;
                    if (StringsKt.contains$default((CharSequence) t, (CharSequence) it10, false, 2, (Object) null)) {
                        z5 = true;
                        break;
                    }
                } else {
                    z5 = false;
                    break;
                }
            }
        } else {
            z5 = false;
        }
        if (z5) {
            return FileOperation.ORGANIZE;
        }
        Iterable $this$any$iv6 = CollectionsKt.listOf((Object[]) new String[]{"метадан", "metadata", "свойств"});
        if (!($this$any$iv6 instanceof Collection) || !((Collection) $this$any$iv6).isEmpty()) {
            Iterator it11 = $this$any$iv6.iterator();
            while (true) {
                if (it11.hasNext()) {
                    Object element$iv6 = it11.next();
                    String it12 = (String) element$iv6;
                    if (StringsKt.contains$default((CharSequence) t, (CharSequence) it12, false, 2, (Object) null)) {
                        z6 = true;
                        break;
                    }
                } else {
                    z6 = false;
                    break;
                }
            }
        } else {
            z6 = false;
        }
        if (z6) {
            return FileOperation.METADATA;
        }
        Iterable $this$any$iv7 = CollectionsKt.listOf((Object[]) new String[]{"прочитай", "read", "открой файл", "содержим"});
        if (!($this$any$iv7 instanceof Collection) || !((Collection) $this$any$iv7).isEmpty()) {
            Iterator it13 = $this$any$iv7.iterator();
            while (true) {
                if (it13.hasNext()) {
                    Object element$iv7 = it13.next();
                    String it14 = (String) element$iv7;
                    if (StringsKt.contains$default((CharSequence) t, (CharSequence) it14, false, 2, (Object) null)) {
                        z7 = true;
                        break;
                    }
                } else {
                    z7 = false;
                    break;
                }
            }
        } else {
            z7 = false;
        }
        if (z7) {
            return FileOperation.READ;
        }
        Iterable $this$any$iv8 = CollectionsKt.listOf((Object[]) new String[]{"inspect", "проверь файл", "осмотр"});
        if (!($this$any$iv8 instanceof Collection) || !((Collection) $this$any$iv8).isEmpty()) {
            for (Object element$iv8 : $this$any$iv8) {
                String it15 = (String) element$iv8;
                if (StringsKt.contains$default((CharSequence) t, (CharSequence) it15, false, 2, (Object) null)) {
                    z8 = true;
                    break;
                }
            }
        }
        return z8 ? FileOperation.INSPECT : FileOperation.SEARCH;
    }

    public static /* synthetic */ ActionPlan planFromGoal$default(FilesAgent filesAgent, UserGoal userGoal, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            str2 = null;
        }
        return filesAgent.planFromGoal(userGoal, str, str2);
    }

    public final ActionPlan planFromGoal(UserGoal goal, String path, String query) {
        Intrinsics.checkNotNullParameter(goal, "goal");
        return plan(new FileAgentRequest(goal, inferOperation(goal.getText()), path, query, null, null, 48, null));
    }
}
