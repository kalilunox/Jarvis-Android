package com.jarvis.app.ui;

import android.content.Context;
import android.graphics.Typeface;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Ui.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002¢\u0006\u0002\u0010\u000fJ\u000e\u0010\u0010\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bJ\u0018\u0010\u0011\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000eJ\u0018\u0010\u0012\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000eR*\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/jarvis/app/ui/Fonts;", "", "<init>", "()V", "cache", "Ljava/util/HashMap;", "", "Landroid/graphics/Typeface;", "Lkotlin/collections/HashMap;", "load", "context", "Landroid/content/Context;", "file", "weight", "", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Integer;)Landroid/graphics/Typeface;", "brand", "display", "body", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Fonts {
    public static final Fonts INSTANCE = new Fonts();
    private static final HashMap<String, Typeface> cache = new HashMap<>();

    private Fonts() {
    }

    private final Typeface load(Context context, String file, Integer weight) {
        Typeface typeface;
        Typeface typeface2;
        Object objM392constructorimpl;
        synchronized (cache) {
            Map $this$getOrPut$iv = cache;
            String str = file + weight;
            Typeface typeface3 = $this$getOrPut$iv.get(str);
            if (typeface3 == null) {
                Fonts fonts = INSTANCE;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    Typeface.Builder builder = new Typeface.Builder(context.getAssets(), "fonts/" + file + ".ttf");
                    if (weight != null) {
                        builder.setFontVariationSettings("'wght' " + weight);
                    }
                    objM392constructorimpl = Result.m392constructorimpl(builder.build());
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM392constructorimpl = Result.m392constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m398isFailureimpl(objM392constructorimpl)) {
                    objM392constructorimpl = null;
                }
                typeface = (Typeface) objM392constructorimpl;
                if (typeface == null) {
                    typeface = Typeface.DEFAULT;
                }
                Intrinsics.checkNotNull(typeface);
                $this$getOrPut$iv.put(str, typeface);
            } else {
                typeface = typeface3;
            }
            typeface2 = typeface;
        }
        return typeface2;
    }

    public final Typeface brand(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return load(context, "michroma", null);
    }

    public static /* synthetic */ Typeface display$default(Fonts fonts, Context context, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 600;
        }
        return fonts.display(context, i);
    }

    public final Typeface display(Context context, int weight) {
        Intrinsics.checkNotNullParameter(context, "context");
        return load(context, "jura", Integer.valueOf(weight));
    }

    public static /* synthetic */ Typeface body$default(Fonts fonts, Context context, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 500;
        }
        return fonts.body(context, i);
    }

    public final Typeface body(Context context, int weight) {
        Intrinsics.checkNotNullParameter(context, "context");
        return load(context, "manrope", Integer.valueOf(weight));
    }
}
