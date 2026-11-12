package com.jarvis.brain;

import android.content.Context;
import com.jarvis.app.VoiceService;
import com.jarvis.core.skills.SkillManifest;
import com.jarvis.core.skills.SkillManifestParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: SkillManifestLoader.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tJ\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002J\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/jarvis/brain/SkillManifestLoader;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "cacheDir", "Ljava/io/File;", "loadAll", "", "Lcom/jarvis/core/skills/SkillManifest;", "cacheManifestText", "", "fileName", "", VoiceService.EXTRA_TEXT, "loadManifestSchema", "Lorg/json/JSONArray;", "Companion", "brain_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SkillManifestLoader {
    private static final String SkillsDir = "skills";
    private final File cacheDir;
    private final Context context;

    public SkillManifestLoader(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.cacheDir = new File(this.context.getFilesDir(), "skill_cache");
    }

    public final List<SkillManifest> loadAll() {
        Object objM392constructorimpl;
        File[] fileArrListFiles;
        this.cacheDir.mkdirs();
        List manifests = new ArrayList();
        try {
            Result.Companion companion = Result.INSTANCE;
            SkillManifestLoader $this$loadAll_u24lambda_u240 = this;
            String[] list = $this$loadAll_u24lambda_u240.context.getAssets().list("skills");
            if (list == null) {
                list = new String[0];
            }
            objM392constructorimpl = Result.m392constructorimpl(list);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
        }
        String[] strArr = new String[0];
        if (Result.m398isFailureimpl(objM392constructorimpl)) {
            objM392constructorimpl = strArr;
        }
        String[] strArr2 = (String[]) objM392constructorimpl;
        String[] assetSkills = strArr2;
        Collection destination$iv$iv = new ArrayList();
        int length = strArr2.length;
        int i = 0;
        while (i < length) {
            String str = strArr2[i];
            Intrinsics.checkNotNull(str);
            String[] strArr3 = strArr2;
            String[] assetSkills2 = assetSkills;
            if (StringsKt.endsWith$default(str, ".json", false, 2, (Object) null)) {
                destination$iv$iv.add(str);
            }
            i++;
            strArr2 = strArr3;
            assetSkills = assetSkills2;
        }
        for (String name : (List) destination$iv$iv) {
            try {
                Result.Companion companion3 = Result.INSTANCE;
                SkillManifestLoader $this$loadAll_u24lambda_u242 = this;
                InputStream inputStreamOpen = $this$loadAll_u24lambda_u242.context.getAssets().open("skills/" + name);
                Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "open(...)");
                Reader inputStreamReader = new InputStreamReader(inputStreamOpen, Charsets.UTF_8);
                BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
                try {
                    BufferedReader reader = bufferedReader;
                    String json = TextStreamsKt.readText(reader);
                    SkillManifest manifest = SkillManifestParser.INSTANCE.parse(json);
                    manifests.add(manifest);
                    Intrinsics.checkNotNull(name);
                    $this$loadAll_u24lambda_u242.cacheManifestText(name, manifest.getSearchableText());
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(bufferedReader, null);
                    Result.m392constructorimpl(Unit.INSTANCE);
                } catch (Throwable th2) {
                    try {
                        throw th2;
                    } catch (Throwable th3) {
                        CloseableKt.closeFinally(bufferedReader, th2);
                        throw th3;
                    }
                }
            } catch (Throwable th4) {
                Result.Companion companion4 = Result.INSTANCE;
                Result.m392constructorimpl(ResultKt.createFailure(th4));
            }
        }
        File extDir = new File(this.context.getExternalFilesDir(null), "skills");
        if (extDir.isDirectory() && (fileArrListFiles = extDir.listFiles()) != null) {
            Collection destination$iv$iv2 = new ArrayList();
            for (File file : fileArrListFiles) {
                Intrinsics.checkNotNull(file);
                if (Intrinsics.areEqual(FilesKt.getExtension(file), "json")) {
                    destination$iv$iv2.add(file);
                }
            }
            Iterable $this$forEach$iv = (List) destination$iv$iv2;
            for (Object element$iv : $this$forEach$iv) {
                File file2 = (File) element$iv;
                try {
                    Result.Companion companion5 = Result.INSTANCE;
                    SkillManifestLoader $this$loadAll_u24lambda_u244_u240 = this;
                    Intrinsics.checkNotNull(file2);
                    String json2 = FilesKt.readText$default(file2, null, 1, null);
                    SkillManifest manifest2 = SkillManifestParser.INSTANCE.parse(json2);
                    manifests.add(manifest2);
                    String name2 = file2.getName();
                    Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
                    $this$loadAll_u24lambda_u244_u240.cacheManifestText(name2, manifest2.getSearchableText());
                    Result.m392constructorimpl(Unit.INSTANCE);
                } catch (Throwable th5) {
                    Result.Companion companion6 = Result.INSTANCE;
                    Result.m392constructorimpl(ResultKt.createFailure(th5));
                }
            }
        }
        return manifests;
    }

    private final void cacheManifestText(String fileName, String text) {
        try {
            Result.Companion companion = Result.INSTANCE;
            SkillManifestLoader $this$cacheManifestText_u24lambda_u240 = this;
            FilesKt.writeText$default(new File($this$cacheManifestText_u24lambda_u240.cacheDir, fileName + ".cache"), text, null, 2, null);
            Result.m392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m392constructorimpl(ResultKt.createFailure(th));
        }
    }

    public final JSONArray loadManifestSchema() throws JSONException {
        Object[] declaredFields = SkillManifest.class.getDeclaredFields();
        Intrinsics.checkNotNullExpressionValue(declaredFields, "getDeclaredFields(...)");
        Object[] $this$map$iv = declaredFields;
        Collection destination$iv$iv = new ArrayList($this$map$iv.length);
        for (Object item$iv$iv : $this$map$iv) {
            Field it = (Field) item$iv$iv;
            destination$iv$iv.add(it.getName());
        }
        JSONArray json = new JSONArray();
        for (SkillManifest m : loadAll()) {
            JSONObject skillJson = new JSONObject();
            skillJson.put("id", m.getId());
            skillJson.put("name", m.getName());
            skillJson.put("description", m.getDescription());
            skillJson.put("examples", new JSONArray((Collection) m.getExamples()));
            json.put(skillJson);
        }
        return json;
    }
}
