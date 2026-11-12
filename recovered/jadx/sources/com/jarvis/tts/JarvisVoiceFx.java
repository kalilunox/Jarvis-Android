package com.jarvis.tts;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JarvisVoiceFx.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\r\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\"#B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0005J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0018\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J(\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0011H\u0002J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0005H\u0002J\u0018\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0005H\u0002J\u0018\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0005H\u0002J\u0018\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0005H\u0002J\u0010\u0010\u001c\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\nH\u0002J)\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020\u00052\b\b\u0002\u0010 \u001a\u00020\u0005H\u0000¢\u0006\u0002\b!R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0011X\u0082T¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/jarvis/tts/JarvisVoiceFx;", "", "<init>", "()V", "TAIL_MS", "", "TARGET_PEAK", "", "MAX_GAIN", "process", "", "input", "sampleRate", "highPass", "Lcom/jarvis/tts/JarvisVoiceFx$Biquad;", "sr", "f", "", "lowPass", "peaking", "q", "gainDb", "compress", "", "x", "chorus", "flanger", "echo", "normalized", "SQRT1_2_Q", "rmsDb", TypedValues.TransitionType.S_FROM, TypedValues.TransitionType.S_TO, "rmsDb$tts_engine_debug", "Biquad", "DelayLine", "tts-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JarvisVoiceFx {
    public static final JarvisVoiceFx INSTANCE = new JarvisVoiceFx();
    private static final float MAX_GAIN = 8.0f;
    private static final double SQRT1_2_Q = 0.7071067811865476d;
    private static final int TAIL_MS = 90;
    private static final float TARGET_PEAK = 0.79f;

    private JarvisVoiceFx() {
    }

    public final float[] process(float[] input, int sampleRate) {
        Intrinsics.checkNotNullParameter(input, "input");
        if (input.length == 0) {
            return input;
        }
        int tail = (sampleRate * TAIL_MS) / 1000;
        float[] x = Arrays.copyOf(input, input.length + tail);
        Intrinsics.checkNotNullExpressionValue(x, "copyOf(...)");
        highPass(sampleRate, 80.0d).run(x);
        peaking(sampleRate, 2600.0d, 1.0d, 1.5d).run(x);
        compress(x, sampleRate);
        highPass(sampleRate, 170.0d).run(x);
        lowPass(sampleRate, 7200.0d).run(x);
        chorus(x, sampleRate);
        flanger(x, sampleRate);
        echo(x, sampleRate);
        return normalized(x);
    }

    /* JADX INFO: compiled from: JarvisVoiceFx.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\r\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\b\u0002\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/jarvis/tts/JarvisVoiceFx$Biquad;", "", "b0", "", "b1", "b2", "a1", "a2", "<init>", "(DDDDD)V", "getB0", "()D", "getB1", "getB2", "getA1", "getA2", "run", "", "x", "", "tts-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Biquad {
        private final double a1;
        private final double a2;
        private final double b0;
        private final double b1;
        private final double b2;

        public Biquad(double b0, double b1, double b2, double a1, double a2) {
            this.b0 = b0;
            this.b1 = b1;
            this.b2 = b2;
            this.a1 = a1;
            this.a2 = a2;
        }

        public final double getA1() {
            return this.a1;
        }

        public final double getA2() {
            return this.a2;
        }

        public final double getB0() {
            return this.b0;
        }

        public final double getB1() {
            return this.b1;
        }

        public final double getB2() {
            return this.b2;
        }

        public final void run(float[] x) {
            Intrinsics.checkNotNullParameter(x, "x");
            double z1 = 0.0d;
            double z2 = 0.0d;
            int length = x.length;
            for (int i = 0; i < length; i++) {
                double inV = x[i];
                double out = (this.b0 * inV) + z1;
                z1 = ((this.b1 * inV) - (this.a1 * out)) + z2;
                z2 = (this.b2 * inV) - (this.a2 * out);
                x[i] = (float) out;
            }
        }
    }

    private final Biquad highPass(int sr, double f) {
        double w = (6.283185307179586d * f) / ((double) sr);
        double alpha = Math.sin(w) / 1.4142135623730951d;
        double d = 1;
        double a0 = d + alpha;
        double c = Math.cos(w);
        double d2 = 2;
        return new Biquad(((d + c) / d2) / a0, (-(d + c)) / a0, ((d + c) / d2) / a0, (((double) (-2)) * c) / a0, (d - alpha) / a0);
    }

    private final Biquad lowPass(int sr, double f) {
        double w = (6.283185307179586d * f) / ((double) sr);
        double alpha = Math.sin(w) / 1.4142135623730951d;
        double d = 1;
        double a0 = d + alpha;
        double c = Math.cos(w);
        double d2 = 2;
        return new Biquad(((d - c) / d2) / a0, (d - c) / a0, ((d - c) / d2) / a0, (((double) (-2)) * c) / a0, (d - alpha) / a0);
    }

    private final Biquad peaking(int sr, double f, double q, double gainDb) {
        double w = (6.283185307179586d * f) / ((double) sr);
        double a = Math.pow(10.0d, gainDb / ((double) 40));
        double alpha = Math.sin(w) / (((double) 2) * q);
        double c = Math.cos(w);
        double d = 1;
        double a0 = (alpha / a) + d;
        double w2 = -2;
        return new Biquad(((alpha * a) + d) / a0, (w2 * c) / a0, (d - (alpha * a)) / a0, (w2 * c) / a0, (d - (alpha / a)) / a0);
    }

    private final void compress(float[] x, int sr) {
        double threshold = Math.pow(10.0d, -1.1d);
        double d = 1;
        double attack = d - Math.exp((-1.0d) / (((double) sr) * 0.012d));
        double release = d - Math.exp((-1.0d) / (((double) sr) * 0.16d));
        double env = 0.0d;
        int i = 0;
        int length = x.length;
        while (i < length) {
            double release2 = release;
            double release3 = x[i] * x[i];
            env += (release3 - env) * (release3 > env ? attack : release2);
            double level = Math.sqrt(env);
            if (level > threshold) {
                double power = x[i];
                double attack2 = threshold / level;
                x[i] = (float) (power * Math.pow(attack2, d - (d / 2.0d)));
            }
            i++;
            release = release2;
            attack = attack;
            threshold = threshold;
        }
    }

    private final void chorus(float[] x, int sr) {
        double base = ((double) sr) * 0.01d;
        double depth = ((double) sr) * 0.0012d;
        DelayLine line = new DelayLine(((int) (base + depth)) + 4);
        int length = x.length;
        for (int i = 0; i < length; i++) {
            double lfo = Math.sin((((double) i) * 1.5707963267948966d) / ((double) sr));
            double wet = line.read((depth * lfo) + base);
            line.write(x[i]);
            x[i] = (float) (((((double) x[i]) * 0.8d) + (0.12d * wet)) * 0.9d);
        }
    }

    private final void flanger(float[] x, int sr) {
        int i = sr;
        double base = ((double) i) * 5.0E-4d;
        double depth = ((double) i) * 6.0E-4d;
        double d = 1;
        double dryGain = Math.sqrt(d - ((-0.1d) * (-0.1d))) * 0.8d;
        double wetGain = 0.2d;
        double wetGain2 = base + depth;
        DelayLine line = new DelayLine(((int) wetGain2) + 4);
        int i2 = 0;
        int length = x.length;
        while (i2 < length) {
            double wetGain3 = wetGain;
            int i3 = length;
            double dryGain2 = dryGain;
            double dryGain3 = i;
            double lfo = (Math.sin((((double) i2) * 1.2566370614359172d) / dryGain3) + d) / ((double) 2);
            double wet = line.read((depth * lfo) + base);
            line.write((float) (((double) x[i2]) + (wet * (-0.1d))));
            x[i2] = (float) ((((double) x[i2]) * dryGain2) + (wet * wetGain3));
            i2++;
            i = sr;
            length = i3;
            dryGain = dryGain2;
            wetGain = wetGain3;
            base = base;
        }
    }

    private final void echo(float[] x, int sr) {
        int d1 = (int) (((double) sr) * 0.018d);
        int d2 = (int) (((double) sr) * 0.034d);
        float[] dry = Arrays.copyOf(x, x.length);
        Intrinsics.checkNotNullExpressionValue(dry, "copyOf(...)");
        int i = 0;
        int length = x.length;
        while (i < length) {
            float e2 = 0.0f;
            float e1 = i >= d1 ? dry[i - d1] : 0.0f;
            if (i >= d2) {
                e2 = dry[i - d2];
            }
            x[i] = (float) (((((double) dry[i]) * 0.9d) + (((double) e1) * 0.11d) + (((double) e2) * 0.06d)) * 0.6d);
            i++;
        }
    }

    private final float[] normalized(float[] x) {
        float peak = 0.0f;
        for (float v : x) {
            peak = Math.max(peak, Math.abs(v));
        }
        if (peak < 1.0E-5f) {
            return x;
        }
        float gain = Math.min(TARGET_PEAK / peak, MAX_GAIN);
        int length = x.length;
        for (int i = 0; i < length; i++) {
            x[i] = x[i] * gain;
        }
        return x;
    }

    /* JADX INFO: compiled from: JarvisVoiceFx.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/jarvis/tts/JarvisVoiceFx$DelayLine;", "", "size", "", "<init>", "(I)V", "buf", "", "pos", "write", "", "v", "", "read", "", "delay", "tts-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class DelayLine {
        private final float[] buf;
        private int pos;

        public DelayLine(int size) {
            this.buf = new float[size];
        }

        public final void write(float v) {
            this.buf[this.pos] = v;
            this.pos = (this.pos + 1) % this.buf.length;
        }

        public final double read(double delay) {
            double d = 1;
            double back = delay + d;
            int whole = (int) back;
            double frac = back - ((double) whole);
            float a = this.buf[((this.pos - whole) + (this.buf.length * 2)) % this.buf.length];
            float b = this.buf[(((this.pos - whole) - 1) + (this.buf.length * 2)) % this.buf.length];
            return (((double) a) * (d - frac)) + (((double) b) * frac);
        }
    }

    public static /* synthetic */ double rmsDb$tts_engine_debug$default(JarvisVoiceFx jarvisVoiceFx, float[] fArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length;
        }
        return jarvisVoiceFx.rmsDb$tts_engine_debug(fArr, i, i2);
    }

    public final double rmsDb$tts_engine_debug(float[] x, int from, int to) {
        Intrinsics.checkNotNullParameter(x, "x");
        double sum = 0.0d;
        for (int i = from; i < to; i++) {
            sum += (double) (x[i] * x[i]);
        }
        return ((double) 10) * Math.log10((sum / ((double) Math.max(1, to - from))) + 1.0E-12d);
    }
}
