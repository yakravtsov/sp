package com.xiaomi.xy.sportscamera.camera.filemanager;

import com.xiaoyi.camera.module.FileItem;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes3.dex */
public class a {
    public static boolean a(List<FileItem> list, FileItem fileItem) {
        Iterator<FileItem> it2 = list.iterator();
        while (it2.hasNext()) {
            if (it2.next().getPath().equals(fileItem.getPath())) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(LinkedBlockingQueue<FileItem> linkedBlockingQueue, FileItem fileItem) {
        Iterator<FileItem> it2 = linkedBlockingQueue.iterator();
        while (it2.hasNext()) {
            if (it2.next().getPath().equals(fileItem.getPath())) {
                return true;
            }
        }
        return false;
    }
}
