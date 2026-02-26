package com.yiaction.common.http;

import java.io.Serializable;

/* loaded from: classes3.dex */
public class ProgressModel implements Serializable {
    public long allSize;
    public long curSize;
    public boolean done;

    public ProgressModel(long j, long j2, boolean z) {
        this.curSize = j;
        this.allSize = j2;
        this.done = z;
    }
}
