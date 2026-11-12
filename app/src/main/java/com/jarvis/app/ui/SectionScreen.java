package com.jarvis.app.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.lifecycle.LifecycleOwnerKt;
import com.jarvis.app.BrainGraph;
import com.jarvis.app.JarvisSettings;
import com.jarvis.app.ModelCatalog;
import com.jarvis.app.ModelManager;
import com.jarvis.app.SecureKeyStore;
import com.jarvis.app.VoiceController;
import com.jarvis.app.VoiceIdentity;
import com.jarvis.app.VoiceService;
import com.jarvis.core.llm.CloudKeyValidator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlin.time.DurationKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: More.kt */
/* JADX INFO: loaded from: classes21.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\tJ\b\u0010\u0014\u001a\u00020\u0012H\u0016J\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ4\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\u001f2\b\u0010#\u001a\u0004\u0018\u00010\u001f2\u0006\u0010\b\u001a\u00020$2\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u00120&J(\u0010'\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\u001f2\b\u0010#\u001a\u0004\u0018\u00010\u001f2\u0006\u0010(\u001a\u00020\u001f2\u0006\u0010)\u001a\u00020$J8\u0010*\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\u001f2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u001f0,2\u0006\u0010-\u001a\u00020.2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u00120&J\u001e\u00100\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020\u001f2\u0006\u00101\u001a\u00020\u001fJ\"\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u001f2\b\b\u0002\u00105\u001a\u00020$2\b\b\u0002\u0010\b\u001a\u00020\u001fJ*\u00106\u001a\u00020\u000e2\u0006\u00104\u001a\u00020\u001f2\u0006\u0010\b\u001a\u00020\u001f2\u0012\u00107\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00120&J&\u00108\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\u001f2\b\u0010#\u001a\u0004\u0018\u00010\u001f2\f\u00109\u001a\b\u0012\u0004\u0012\u00020\u00120:J\u0006\u0010;\u001a\u00020\u000eJ\u000e\u0010<\u001a\u00020\u00122\u0006\u0010=\u001a\u00020\u0007J\u0006\u0010>\u001a\u00020\u000eJ\u0006\u0010?\u001a\u00020\u0012J\u0006\u0010@\u001a\u00020\u000eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\b\u001a\u0004\u0018\u00010\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0015\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006A"}, d2 = {"Lcom/jarvis/app/ui/SectionScreen;", "Lcom/jarvis/app/ui/Screen;", "host", "Lcom/jarvis/app/ui/JarvisActivity;", "<init>", "(Lcom/jarvis/app/ui/JarvisActivity;)V", "root", "Landroid/widget/LinearLayout;", "value", "Lcom/jarvis/app/ui/MoreSection;", JarvisActivity.SECTION, "getSection", "()Lcom/jarvis/app/ui/MoreSection;", "view", "Landroid/view/View;", "getView", "()Landroid/view/View;", "bind", "", "s", "onShow", "app", "getApp", "()Lcom/jarvis/app/ui/JarvisActivity;", "prefs", "Landroid/content/SharedPreferences;", "getPrefs", "()Landroid/content/SharedPreferences;", "head", "Landroid/widget/TextView;", VoiceService.EXTRA_TEXT, "", "note", "toggle", "title", "sub", "", "onChange", "Lkotlin/Function1;", "pref", "key", "default", "options", "labels", "", "selected", "", "onPick", "keyRow", "provider", "textField", "Landroid/widget/EditText;", "hint", "secret", "urlField", "save", "link", "action", "Lkotlin/Function0;", "threshold", "models", "into", "voiceLock", "restartVoice", "skins", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SectionScreen extends Screen {
    private final LinearLayout root;
    private MoreSection section;
    private final View view;

    /* JADX INFO: compiled from: More.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ModelManager.State.values().length];
            try {
                iArr[ModelManager.State.READY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ModelManager.State.DOWNLOADING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SectionScreen(JarvisActivity host) {
        super(host);
        Intrinsics.checkNotNullParameter(host, "host");
        this.root = UiKt.column$default(host, 0, 1, null);
        this.view = this.root;
    }

    public final MoreSection getSection() {
        return this.section;
    }

    @Override // com.jarvis.app.ui.Screen
    public View getView() {
        return this.view;
    }

    public final void bind(MoreSection s) {
        Intrinsics.checkNotNullParameter(s, "s");
        this.section = s;
    }

    @Override // com.jarvis.app.ui.Screen
    public void onShow() {
        MoreSection s = this.section;
        if (s == null) {
            return;
        }
        this.root.removeAllViews();
        this.root.addView(header(s.getTitle(), true));
        LinearLayout body = UiKt.column(getHost(), 10);
        s.getBuild().invoke(this, body);
        this.root.addView(scroll(body), UiKt.lp$default(0, 0, 1.0f, 1, null));
    }

    public final JarvisActivity getApp() {
        return getHost();
    }

    public final SharedPreferences getPrefs() {
        return JarvisSettings.INSTANCE.prefs(getHost());
    }

    public final TextView head(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        return section(text);
    }

    public final TextView note(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        JarvisActivity host = getHost();
        TextView $this$note_u24lambda_u240 = UiKt.label(host, (8 & 1) != 0 ? "" : text, (8 & 2) != 0 ? 14.0f : 13.0f, getSkin().getMuted(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0);
        $this$note_u24lambda_u240.setPadding(UiKt.dp((Context) getHost(), 4), 0, UiKt.dp((Context) getHost(), 4), 0);
        return $this$note_u24lambda_u240;
    }

    public final View toggle(String title, String sub, boolean value, final Function1<? super Boolean, Unit> onChange) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(onChange, "onChange");
        final SwitchView sw = new SwitchView(getHost(), getSkin(), value);
        LinearLayout $this$toggle_u24lambda_u240 = Screen.card$default(this, title, sub, null, sw, 0, 0, null, 116, null);
        UiKt.tap($this$toggle_u24lambda_u240, new Function0() { // from class: com.jarvis.app.ui.SectionScreen$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SectionScreen.toggle$lambda$0$0(sw, onChange);
            }
        });
        return $this$toggle_u24lambda_u240;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit toggle$lambda$0$0(SwitchView $sw, Function1 $onChange) {
        $sw.setChecked(!$sw.getChecked());
        $onChange.invoke(Boolean.valueOf($sw.getChecked()));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit pref$lambda$0(SectionScreen this$0, String $key, boolean it) {
        this$0.getPrefs().edit().putBoolean($key, it).apply();
        return Unit.INSTANCE;
    }

    public final View pref(String title, String sub, final String key, boolean z) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(key, "key");
        return toggle(title, sub, getPrefs().getBoolean(key, z), new Function1() { // from class: com.jarvis.app.ui.SectionScreen$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SectionScreen.pref$lambda$0(this.f$0, key, ((Boolean) obj).booleanValue());
            }
        });
    }

    public final View options(String title, List<String> labels, int selected, Function1<? super Integer, Unit> onPick) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(labels, "labels");
        Intrinsics.checkNotNullParameter(onPick, "onPick");
        View viewPad = UiKt.pad(UiKt.column(getHost(), 9), 12);
        LinearLayout $this$options_u24lambda_u240 = (LinearLayout) viewPad;
        $this$options_u24lambda_u240.setBackground(UiKt.glassBg$default(getHost(), getSkin(), 0, 0, 0, 14, null));
        LinearLayout box = (LinearLayout) viewPad;
        if (title.length() > 0) {
            JarvisActivity host = getHost();
            box.addView(UiKt.label(host, (8 & 1) != 0 ? "" : title, (8 & 2) != 0 ? 14.0f : 15.0f, getSkin().getText(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host, 0, 2, null) : Fonts.INSTANCE.body(getHost(), 600), (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0));
        }
        box.addView(ScreensKt.segment(getHost(), labels, RangesKt.coerceAtLeast(selected, 0), onPick), UiKt.lp$default(0, UiKt.dp((Context) getHost(), 36), 0.0f, 5, null));
        return box;
    }

    public final View keyRow(String title, String sub, final String provider) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(sub, "sub");
        Intrinsics.checkNotNullParameter(provider, "provider");
        final SecureKeyStore store = new SecureKeyStore(getHost());
        LinearLayout box = UiKt.column(getHost(), 8);
        JarvisActivity host = getHost();
        final TextView tag = UiKt.label(host, (8 & 1) != 0 ? "" : "", (8 & 2) != 0 ? 14.0f : 12.0f, getSkin().getMuted(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0);
        View viewPad = UiKt.pad(UiKt.column(getHost(), 8), 10);
        LinearLayout $this$keyRow_u24lambda_u240 = (LinearLayout) viewPad;
        $this$keyRow_u24lambda_u240.setBackground(UiKt.glassBg$default(getHost(), getSkin(), 0, 0, 0, 14, null));
        $this$keyRow_u24lambda_u240.setVisibility(8);
        final LinearLayout form = (LinearLayout) viewPad;
        final EditText input = textField$default(this, "Вставьте ключ", true, null, 4, null);
        form.addView(input, UiKt.lp$default(0, UiKt.dp((Context) getHost(), 42), 0.0f, 5, null));
        LinearLayout btns = UiKt.rowLayout(getHost(), 10);
        btns.addView(getHost().button("Удалить", false, new Function0() { // from class: com.jarvis.app.ui.SectionScreen$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SectionScreen.keyRow$lambda$1(store, provider, input, tag, this);
            }
        }), UiKt.lp(0, UiKt.dp((Context) getHost(), 44), 1.0f));
        btns.addView(getHost().button("Сохранить", true, new Function0() { // from class: com.jarvis.app.ui.SectionScreen$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SectionScreen.keyRow$lambda$2(input, this, store, provider, form, tag);
            }
        }), UiKt.lp(0, UiKt.dp((Context) getHost(), 44), 1.0f));
        form.addView(btns);
        box.addView(Screen.card$default(this, title, sub, null, tag, 0, 0, new Function0() { // from class: com.jarvis.app.ui.SectionScreen$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SectionScreen.keyRow$lambda$3(form);
            }
        }, 52, null));
        box.addView(form);
        keyRow$refresh(store, provider, tag, this);
        return box;
    }

    private static final void keyRow$refresh(SecureKeyStore store, String $provider, TextView tag, SectionScreen this$0) {
        boolean has = store.hasKey($provider);
        tag.setText(has ? "Сохранён" : "Нет");
        Skin skin = this$0.getSkin();
        tag.setTextColor(has ? skin.getGood() : skin.getMuted());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit keyRow$lambda$1(SecureKeyStore $store, String $provider, EditText $input, TextView $tag, SectionScreen this$0) {
        $store.clearKey($provider);
        $input.setText("");
        BrainGraph.INSTANCE.onCloudKeyChanged($provider);
        BrainGraph.INSTANCE.invalidate();
        keyRow$refresh($store, $provider, $tag, this$0);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit keyRow$lambda$2(EditText $input, SectionScreen this$0, SecureKeyStore $store, String $provider, LinearLayout $form, TextView $tag) {
        CloudKeyValidator cloudKeyValidator = CloudKeyValidator.INSTANCE;
        Editable text = $input.getText();
        String clean = cloudKeyValidator.sanitizeKey(text != null ? text.toString() : null);
        if (CloudKeyValidator.INSTANCE.isStubKey(clean)) {
            Toast.makeText(this$0.getHost(), "Ключ не подходит", 0).show();
        } else {
            $store.setKey($provider, clean);
            $input.setText("");
            BrainGraph.INSTANCE.onCloudKeyChanged($provider);
            BrainGraph.INSTANCE.invalidate();
            keyRow$refresh($store, $provider, $tag, this$0);
            $form.setVisibility(8);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit keyRow$lambda$3(LinearLayout $form) {
        $form.setVisibility($form.getVisibility() == 8 ? 0 : 8);
        return Unit.INSTANCE;
    }

    public static /* synthetic */ EditText textField$default(SectionScreen sectionScreen, String str, boolean z, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            str2 = "";
        }
        return sectionScreen.textField(str, z, str2);
    }

    public final EditText textField(String hint, boolean secret, String value) {
        Intrinsics.checkNotNullParameter(hint, "hint");
        Intrinsics.checkNotNullParameter(value, "value");
        EditText $this$textField_u24lambda_u240 = new EditText(getHost());
        $this$textField_u24lambda_u240.setHint(hint);
        $this$textField_u24lambda_u240.setText(value);
        $this$textField_u24lambda_u240.setHintTextColor(getSkin().getMuted());
        $this$textField_u24lambda_u240.setTextColor(getSkin().getText());
        $this$textField_u24lambda_u240.setTextSize(14.0f);
        $this$textField_u24lambda_u240.setBackground(UiKt.glassBg$default(getHost(), getSkin(), 10, 0, 0, 12, null));
        $this$textField_u24lambda_u240.setPadding(UiKt.dp((Context) getHost(), 12), 0, UiKt.dp((Context) getHost(), 12), 0);
        $this$textField_u24lambda_u240.setInputType(secret ? 129 : 17);
        return $this$textField_u24lambda_u240;
    }

    public final View urlField(String hint, String value, final Function1<? super String, Unit> save) {
        Intrinsics.checkNotNullParameter(hint, "hint");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(save, "save");
        final EditText $this$urlField_u24lambda_u240 = textField$default(this, hint, false, value, 2, null);
        $this$urlField_u24lambda_u240.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.jarvis.app.ui.SectionScreen$$ExternalSyntheticLambda7
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                SectionScreen.urlField$lambda$0$0(save, $this$urlField_u24lambda_u240, view, z);
            }
        });
        $this$urlField_u24lambda_u240.setLayoutParams(UiKt.lp$default(0, UiKt.dp((Context) getHost(), 42), 0.0f, 5, null));
        return $this$urlField_u24lambda_u240;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void urlField$lambda$0$0(Function1 $save, EditText $this_apply, View view, boolean focused) {
        if (focused) {
            return;
        }
        $save.invoke($this_apply.getText().toString());
        BrainGraph.INSTANCE.invalidate();
    }

    public final LinearLayout link(String title, String sub, Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(action, "action");
        return Screen.card$default(this, title, sub, null, null, 0, 0, action, 60, null);
    }

    public final View threshold() {
        View viewPad = UiKt.pad(UiKt.column(getHost(), 8), 12);
        LinearLayout $this$threshold_u24lambda_u240 = (LinearLayout) viewPad;
        $this$threshold_u24lambda_u240.setBackground(UiKt.glassBg$default(getHost(), getSkin(), 0, 0, 0, 14, null));
        LinearLayout box = (LinearLayout) viewPad;
        JarvisActivity host = getHost();
        final TextView value = UiKt.label(host, (8 & 1) != 0 ? "" : "", (8 & 2) != 0 ? 14.0f : 15.0f, getSkin().getAccent(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host, 0, 2, null) : Fonts.display$default(Fonts.INSTANCE, getHost(), 0, 2, null), (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0);
        float cur = JarvisSettings.INSTANCE.brainConfidenceThreshold(getHost());
        threshold$show(value, cur);
        LinearLayout $this$threshold_u24lambda_u241 = UiKt.rowLayout$default(getHost(), 0, 1, null);
        JarvisActivity host2 = getHost();
        $this$threshold_u24lambda_u241.addView(UiKt.label(host2, (8 & 1) != 0 ? "" : "Порог уверенности", (8 & 2) != 0 ? 14.0f : 15.0f, getSkin().getText(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host2, 0, 2, null) : Fonts.INSTANCE.body(getHost(), 600), (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0), UiKt.lp(0, -2, 1.0f));
        $this$threshold_u24lambda_u241.addView(value);
        box.addView($this$threshold_u24lambda_u241);
        SeekBar $this$threshold_u24lambda_u242 = new SeekBar(getHost());
        $this$threshold_u24lambda_u242.setMax(60);
        $this$threshold_u24lambda_u242.setProgress((int) ((cur - 0.3f) * 100));
        $this$threshold_u24lambda_u242.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.jarvis.app.ui.SectionScreen$threshold$2$1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar s, int p, boolean user) {
                if (user) {
                    SectionScreen.threshold$show(value, (p / 100.0f) + 0.3f);
                    JarvisSettings.INSTANCE.setBrainConfidenceThreshold(this.this$0.getHost(), (p / 100.0f) + 0.3f);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar s) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar s) {
            }
        });
        box.addView($this$threshold_u24lambda_u242);
        JarvisActivity host3 = getHost();
        box.addView(UiKt.label(host3, (8 & 1) != 0 ? "" : "Ниже: чаще выполняет сам. Выше: чаще переспрашивает.", (8 & 2) != 0 ? 14.0f : 12.0f, getSkin().getMuted(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host3, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0));
        return box;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void threshold$show(TextView value, float v) {
        String str = String.format("%.2f", Arrays.copyOf(new Object[]{Float.valueOf(v)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        value.setText(str);
    }

    public final void models(LinearLayout into) {
        Intrinsics.checkNotNullParameter(into, "into");
        ModelManager mgr = ModelManager.INSTANCE.get(getHost());
        mgr.refreshAll();
        LinearLayout list = UiKt.column(getHost(), 10);
        into.addView(list);
        models$render(list, mgr, this);
        final Job job = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(getHost()), null, null, new SectionScreen$models$job$1(this, mgr, list, null), 3, null);
        list.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.jarvis.app.ui.SectionScreen.models.1
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View v) {
                Intrinsics.checkNotNullParameter(v, "v");
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View v) {
                Intrinsics.checkNotNullParameter(v, "v");
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void models$render(LinearLayout list, final ModelManager mgr, SectionScreen this$0) {
        TextView $this$models_u24render_u24lambda_u240_u240;
        list.removeAllViews();
        Iterable $this$forEach$iv = ModelCatalog.INSTANCE.getALL();
        for (Object element$iv : $this$forEach$iv) {
            final ModelCatalog.Entry e = (ModelCatalog.Entry) element$iv;
            ModelManager.Status st = mgr.status(e.getId());
            switch (WhenMappings.$EnumSwitchMapping$0[st.getState().ordinal()]) {
                case 1:
                    JarvisActivity host = this$0.getHost();
                    $this$models_u24render_u24lambda_u240_u240 = UiKt.label(host, (8 & 1) != 0 ? "" : "Готово", (8 & 2) != 0 ? 14.0f : 12.0f, this$0.getSkin().getGood(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0);
                    break;
                case 2:
                    JarvisActivity host2 = this$0.getHost();
                    $this$models_u24render_u24lambda_u240_u240 = UiKt.label(host2, (8 & 1) != 0 ? "" : ((int) (st.getProgress() * 100)) + "%", (8 & 2) != 0 ? 14.0f : 12.0f, this$0.getSkin().getAccent(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host2, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0);
                    break;
                default:
                    JarvisActivity host3 = this$0.getHost();
                    $this$models_u24render_u24lambda_u240_u240 = UiKt.label(host3, (8 & 1) != 0 ? "" : "Скачать", (8 & 2) != 0 ? 14.0f : 12.0f, this$0.getSkin().getOnAccent(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host3, 0, 2, null) : Fonts.INSTANCE.body(this$0.getHost(), TypedValues.TransitionType.TYPE_DURATION), (8 & 16) != 0 ? 8388611 : 0, (8 & 32) != 0 ? Integer.MAX_VALUE : 0);
                    $this$models_u24render_u24lambda_u240_u240.setBackground(UiKt.glassBg(this$0.getHost(), this$0.getSkin(), 14, this$0.getSkin().getAccent(), this$0.getSkin().getAccent()));
                    $this$models_u24render_u24lambda_u240_u240.setPadding(UiKt.dp((Context) this$0.getHost(), 12), UiKt.dp((Context) this$0.getHost(), 6), UiKt.dp((Context) this$0.getHost(), 12), UiKt.dp((Context) this$0.getHost(), 6));
                    UiKt.tap($this$models_u24render_u24lambda_u240_u240, new Function0() { // from class: com.jarvis.app.ui.SectionScreen$$ExternalSyntheticLambda8
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return SectionScreen.models$render$lambda$0$0$0(mgr, e);
                        }
                    });
                    break;
            }
            list.addView(Screen.card$default(this$0, e.getTitle(), e.getSubtitle() + ", " + (e.getSizeBytes() / ((long) DurationKt.NANOS_IN_MILLIS)) + " МБ", null, $this$models_u24render_u24lambda_u240_u240, 0, 0, null, 116, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit models$render$lambda$0$0$0(ModelManager $mgr, ModelCatalog.Entry $e) {
        $mgr.download($e.getId());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r11v0, types: [T, com.jarvis.app.ui.SectionScreen$$ExternalSyntheticLambda10] */
    public final View voiceLock() {
        Function0 function0;
        final LinearLayout box = UiKt.column(getHost(), 10);
        final ModelManager mgr = ModelManager.INSTANCE.get(getHost());
        final ArrayList samples = new ArrayList();
        final ArrayList phrases = new ArrayList();
        final Ref.BooleanRef recording = new Ref.BooleanRef();
        final Ref.ObjectRef hint = new Ref.ObjectRef();
        final Ref.ObjectRef render = new Ref.ObjectRef();
        render.element = new Function0() { // from class: com.jarvis.app.ui.SectionScreen$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SectionScreen.voiceLock$lambda$1(box, this, mgr, samples, recording, hint, phrases, render);
            }
        };
        if (render.element == 0) {
            Intrinsics.throwUninitializedPropertyAccessException("render");
            function0 = null;
        } else {
            function0 = (Function0) render.element;
        }
        function0.invoke();
        final Job job = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(getHost()), null, null, new SectionScreen$voiceLock$job$1(this, mgr, box, samples, phrases, recording, render, null), 3, null);
        box.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.jarvis.app.ui.SectionScreen.voiceLock.2
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View v) {
                Intrinsics.checkNotNullParameter(v, "v");
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View v) {
                Intrinsics.checkNotNullParameter(v, "v");
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
        });
        return box;
    }

    private static final void voiceLock$record(final Ref.BooleanRef recording, Ref.ObjectRef<String> objectRef, final Ref.ObjectRef<Function0<Unit>> objectRef2, final SectionScreen this$0, int ms, final Function2<? super float[], ? super Continuation<? super Unit>, ? extends Object> function2) {
        Function0<Unit> function0;
        recording.element = true;
        Function0<Unit> function1 = null;
        objectRef.element = null;
        if (objectRef2.element == null) {
            Intrinsics.throwUninitializedPropertyAccessException("render");
            function0 = null;
        } else {
            function0 = objectRef2.element;
        }
        function0.invoke();
        boolean started = VoiceController.INSTANCE.recordVoiceSample(ms, new Function1() { // from class: com.jarvis.app.ui.SectionScreen$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SectionScreen.voiceLock$record$lambda$0(this.f$0, function2, recording, objectRef2, (float[]) obj);
            }
        });
        if (!started) {
            recording.element = false;
            objectRef.element = "Сначала включите ассистента.";
            if (objectRef2.element == null) {
                Intrinsics.throwUninitializedPropertyAccessException("render");
            } else {
                function1 = objectRef2.element;
            }
            function1.invoke();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit voiceLock$record$lambda$0(SectionScreen this$0, Function2 $onDone, Ref.BooleanRef $recording, Ref.ObjectRef $render, float[] pcm) {
        Intrinsics.checkNotNullParameter(pcm, "pcm");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$0.getHost()), null, null, new SectionScreen$voiceLock$record$started$1$1($onDone, pcm, $recording, $render, null), 3, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit voiceLock$lambda$1(LinearLayout $box, final SectionScreen this$0, final ModelManager $mgr, final ArrayList $samples, final Ref.BooleanRef $recording, final Ref.ObjectRef $hint, final ArrayList $phrases, final Ref.ObjectRef $render) {
        String str;
        String it;
        $box.removeAllViews();
        final int needed = 10;
        if (!VoiceIdentity.INSTANCE.modelReady(this$0.getHost())) {
            ModelManager.Status st = $mgr.status(VoiceIdentity.MODEL_ID);
            if (st.getState() == ModelManager.State.DOWNLOADING) {
                $box.addView(Screen.card$default(this$0, "Скачивается модель", ((int) (st.getProgress() * 100)) + "%", null, null, 0, 0, null, 124, null));
            } else {
                $box.addView(Screen.card$default(this$0, "Скачать модель узнавания", "Нужна один раз, 28 МБ", null, null, 0, 0, new Function0() { // from class: com.jarvis.app.ui.SectionScreen$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SectionScreen.voiceLock$lambda$1$0($mgr);
                    }
                }, 60, null));
            }
        } else if (!$samples.isEmpty() || !VoiceIdentity.INSTANCE.enrolled(this$0.getHost())) {
            String title = $recording.element ? "Говорите: «Джарвис»" : "Запись " + ($samples.size() + 1) + " из 10";
            String str2 = (String) $hint.element;
            if (str2 == null) {
                str2 = "Нажмите и скажите «Джарвис» как обычно. Три последние записи с двух шагов.";
            }
            String sub = str2;
            $box.addView(Screen.card$default(this$0, title, sub, null, null, 0, 0, new Function0() { // from class: com.jarvis.app.ui.SectionScreen$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SectionScreen.voiceLock$lambda$1$1($recording, $hint, $samples, needed, this$0, $render);
                }
            }, 60, null));
        } else if (VoiceIdentity.INSTANCE.phrasesEnrolled(this$0.getHost())) {
            $box.addView(this$0.toggle("Только мой голос", "Чужой голос не разбудит Джарвиса и не отдаст команду", JarvisSettings.INSTANCE.voiceLock(this$0.getHost()), new Function1() { // from class: com.jarvis.app.ui.SectionScreen$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SectionScreen.voiceLock$lambda$1$4(this.f$0, ((Boolean) obj).booleanValue());
                }
            }));
            $box.addView(this$0.note("Выучено на ваших командах: " + VoiceIdentity.INSTANCE.learnedCount(this$0.getHost())));
            $box.addView(Screen.card$default(this$0, "Записать голос заново", "Предыдущий образец будет заменён", null, null, 0, 0, new Function0() { // from class: com.jarvis.app.ui.SectionScreen$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SectionScreen.voiceLock$lambda$1$5(this.f$0, $render);
                }
            }, 60, null));
        } else {
            final List<String> phrases = VoiceIdentity.INSTANCE.getPHRASES();
            if ($recording.element) {
                str = "Читайте вслух, 6 секунд";
            } else {
                str = "Фраза " + ($phrases.size() + 1) + " из " + phrases.size();
            }
            String title2 = str;
            String str3 = phrases.get($phrases.size());
            String it2 = (String) $hint.element;
            if (it2 == null || (it = StringUtils.LF + it2) == null) {
                it = "";
            }
            String sub2 = "«" + ((Object) str3) + "»" + it;
            $box.addView(Screen.card$default(this$0, title2, sub2, null, null, 0, 0, new Function0() { // from class: com.jarvis.app.ui.SectionScreen$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SectionScreen.voiceLock$lambda$1$3($recording, $hint, $phrases, phrases, this$0, $render);
                }
            }, 60, null));
            $box.addView(this$0.note("Фразы нужны, чтобы Джарвис принимал команды только от вас."));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit voiceLock$lambda$1$0(ModelManager $mgr) {
        $mgr.download(VoiceIdentity.MODEL_ID);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit voiceLock$lambda$1$1(Ref.BooleanRef $recording, Ref.ObjectRef $hint, ArrayList $samples, int $needed, SectionScreen this$0, Ref.ObjectRef $render) {
        if ($recording.element) {
            return Unit.INSTANCE;
        }
        voiceLock$record($recording, $hint, $render, this$0, 2500, new SectionScreen$voiceLock$1$2$1($hint, $samples, $needed, this$0, null));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit voiceLock$lambda$1$3(Ref.BooleanRef $recording, Ref.ObjectRef $hint, ArrayList $phrases, List $all, SectionScreen this$0, Ref.ObjectRef $render) {
        if ($recording.element) {
            return Unit.INSTANCE;
        }
        voiceLock$record($recording, $hint, $render, this$0, VoiceIdentity.PHRASE_MS, new SectionScreen$voiceLock$1$3$1($hint, $phrases, $all, this$0, null));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit voiceLock$lambda$1$4(SectionScreen this$0, boolean it) {
        JarvisSettings.INSTANCE.setVoiceLock(this$0.getHost(), it);
        VoiceIdentity.INSTANCE.warm(this$0.getHost());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit voiceLock$lambda$1$5(SectionScreen this$0, Ref.ObjectRef $render) {
        Function0 function0;
        VoiceIdentity.INSTANCE.forget(this$0.getHost());
        if ($render.element == 0) {
            Intrinsics.throwUninitializedPropertyAccessException("render");
            function0 = null;
        } else {
            function0 = (Function0) $render.element;
        }
        function0.invoke();
        return Unit.INSTANCE;
    }

    public final void restartVoice() {
        if (VoiceController.INSTANCE.isRunning()) {
            VoiceController.INSTANCE.stop(getHost());
            VoiceController.INSTANCE.start(getHost());
        }
    }

    public final View skins() {
        LinearLayout row = UiKt.rowLayout(getHost(), 6);
        String cur = Skins.INSTANCE.current(getHost()).getId();
        Iterable $this$forEach$iv = Skins.INSTANCE.getALL();
        for (Object element$iv : $this$forEach$iv) {
            final Skin s = (Skin) element$iv;
            View viewPad = UiKt.pad(UiKt.column$default(getHost(), 0, 1, null), 2, 9);
            LinearLayout $this$skins_u24lambda_u240_u240 = (LinearLayout) viewPad;
            $this$skins_u24lambda_u240_u240.setGravity(17);
            $this$skins_u24lambda_u240_u240.setBackground(UiKt.glassBg(getHost(), getSkin(), 14, getSkin().getGlass(), Intrinsics.areEqual(s.getId(), cur) ? getSkin().getAccent() : getSkin().getLine()));
            View $this$skins_u24lambda_u240_u240_u240 = new View(getHost());
            GradientDrawable $this$skins_u24lambda_u240_u240_u240_u240 = new GradientDrawable();
            $this$skins_u24lambda_u240_u240_u240_u240.setShape(1);
            $this$skins_u24lambda_u240_u240_u240_u240.setColor(s.getAccent());
            $this$skins_u24lambda_u240_u240_u240_u240.setStroke(1, getSkin().getLine());
            $this$skins_u24lambda_u240_u240_u240.setBackground($this$skins_u24lambda_u240_u240_u240_u240);
            $this$skins_u24lambda_u240_u240.addView($this$skins_u24lambda_u240_u240_u240, UiKt.lp$default(UiKt.dp((Context) getHost(), 24), UiKt.dp((Context) getHost(), 24), 0.0f, 4, null));
            JarvisActivity host = getHost();
            TextView $this$skins_u24lambda_u240_u240_u241 = UiKt.label(host, (8 & 1) != 0 ? "" : s.getTitle(), (8 & 2) != 0 ? 14.0f : 10.5f, getSkin().getText(), (8 & 8) != 0 ? Fonts.body$default(Fonts.INSTANCE, host, 0, 2, null) : null, (8 & 16) != 0 ? 8388611 : 17, (8 & 32) != 0 ? Integer.MAX_VALUE : 1);
            $this$skins_u24lambda_u240_u240_u241.setPadding(0, UiKt.dp((Context) getHost(), 6), 0, 0);
            $this$skins_u24lambda_u240_u240.addView($this$skins_u24lambda_u240_u240_u241);
            UiKt.tap($this$skins_u24lambda_u240_u240, new Function0() { // from class: com.jarvis.app.ui.SectionScreen$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SectionScreen.skins$lambda$0$0$2(this.f$0, s);
                }
            });
            LinearLayout box = (LinearLayout) viewPad;
            row.addView(box, UiKt.lp(0, -2, 1.0f));
            cur = cur;
            $this$forEach$iv = $this$forEach$iv;
        }
        return row;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit skins$lambda$0$0$2(SectionScreen this$0, Skin $s) {
        Skins.INSTANCE.set(this$0.getHost(), $s.getId());
        this$0.getHost().recreate();
        return Unit.INSTANCE;
    }
}
