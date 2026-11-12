package com.jarvis.app;

import kotlin.Metadata;

/* JADX INFO: compiled from: JarvisWidgetProvider.kt */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/jarvis/app/JarvisWidgetStrip;", "Lcom/jarvis/app/JarvisWidgetProvider;", "<init>", "()V", "layout", "", "getLayout", "()I", "coreDp", "getCoreDp", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JarvisWidgetStrip extends JarvisWidgetProvider {
    private final int layout = R.layout.widget_jarvis_strip;
    private final int coreDp = 46;

    @Override // com.jarvis.app.JarvisWidgetProvider
    protected int getLayout() {
        return this.layout;
    }

    @Override // com.jarvis.app.JarvisWidgetProvider
    protected int getCoreDp() {
        return this.coreDp;
    }
}
