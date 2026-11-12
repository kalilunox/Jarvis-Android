package com.jarvis.asr;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BufferedEnAsr.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/jarvis/asr/CommandRefinePasses;", "", "multilingual", "", "english", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getMultilingual", "()Ljava/lang/String;", "getEnglish", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "asr-engine_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class CommandRefinePasses {
    private final String english;
    private final String multilingual;

    /* JADX WARN: Multi-variable type inference failed */
    public CommandRefinePasses() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ CommandRefinePasses copy$default(CommandRefinePasses commandRefinePasses, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = commandRefinePasses.multilingual;
        }
        if ((i & 2) != 0) {
            str2 = commandRefinePasses.english;
        }
        return commandRefinePasses.copy(str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getMultilingual() {
        return this.multilingual;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getEnglish() {
        return this.english;
    }

    public final CommandRefinePasses copy(String multilingual, String english) {
        return new CommandRefinePasses(multilingual, english);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CommandRefinePasses)) {
            return false;
        }
        CommandRefinePasses commandRefinePasses = (CommandRefinePasses) other;
        return Intrinsics.areEqual(this.multilingual, commandRefinePasses.multilingual) && Intrinsics.areEqual(this.english, commandRefinePasses.english);
    }

    public int hashCode() {
        return ((this.multilingual == null ? 0 : this.multilingual.hashCode()) * 31) + (this.english != null ? this.english.hashCode() : 0);
    }

    public String toString() {
        return "CommandRefinePasses(multilingual=" + this.multilingual + ", english=" + this.english + ")";
    }

    public CommandRefinePasses(String multilingual, String english) {
        this.multilingual = multilingual;
        this.english = english;
    }

    public /* synthetic */ CommandRefinePasses(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2);
    }

    public final String getMultilingual() {
        return this.multilingual;
    }

    public final String getEnglish() {
        return this.english;
    }
}
