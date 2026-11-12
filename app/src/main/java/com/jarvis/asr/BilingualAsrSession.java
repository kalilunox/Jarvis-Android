package com.jarvis.asr;

import android.util.Log;
import com.jarvis.core.nlp.AsrPhraseNormalizer;
import com.jarvis.core.nlp.RuEnTextMerger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: BilingualAsrSession.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/jarvis/asr/BilingualAsrSession;", "Lcom/jarvis/asr/AsrSession;", "ru", "en", "<init>", "(Lcom/jarvis/asr/AsrSession;Lcom/jarvis/asr/AsrSession;)V", "lock", "", "processChunk", "Lcom/jarvis/asr/AsrChunkResult;", "samples", "", "reset", "", "release", "Companion", "asr-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BilingualAsrSession implements AsrSession {
    private static final String TAG = "Jarvis.Asr";
    private final AsrSession en;
    private final Object lock;
    private final AsrSession ru;

    public BilingualAsrSession(AsrSession ru, AsrSession en) {
        Intrinsics.checkNotNullParameter(ru, "ru");
        this.ru = ru;
        this.en = en;
        this.lock = new Object();
    }

    @Override // com.jarvis.asr.AsrSession
    public AsrChunkResult processChunk(float[] samples) {
        String out;
        AsrChunkResult asrChunkResult;
        AsrChunkResult asrChunkResultProcessChunk;
        Intrinsics.checkNotNullParameter(samples, "samples");
        synchronized (this.lock) {
            AsrChunkResult ruResult = this.ru.processChunk(samples);
            AsrSession asrSession = this.en;
            String enText = (asrSession == null || (asrChunkResultProcessChunk = asrSession.processChunk(samples)) == null) ? null : asrChunkResultProcessChunk.getText();
            if (enText == null) {
                enText = "";
            }
            if (this.en == null || StringsKt.isBlank(enText)) {
                out = AsrPhraseNormalizer.INSTANCE.normalize(ruResult.getText());
            } else {
                out = RuEnTextMerger.INSTANCE.merge(ruResult.getText(), enText);
                if (ruResult.isEndpoint() && (!StringsKt.isBlank(ruResult.getText()) || !StringsKt.isBlank(enText))) {
                    Log.i(TAG, "ru=\"" + ruResult.getText() + "\" en=\"" + enText + "\" -> \"" + out + "\"");
                }
            }
            asrChunkResult = new AsrChunkResult(out, ruResult.isEndpoint());
        }
        return asrChunkResult;
    }

    @Override // com.jarvis.asr.AsrSession
    public void reset() {
        synchronized (this.lock) {
            this.ru.reset();
            AsrSession asrSession = this.en;
            if (asrSession != null) {
                asrSession.reset();
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    @Override // com.jarvis.asr.AsrSession
    public void release() {
        synchronized (this.lock) {
            this.ru.release();
            AsrSession asrSession = this.en;
            if (asrSession != null) {
                asrSession.release();
                Unit unit = Unit.INSTANCE;
            }
        }
    }
}
