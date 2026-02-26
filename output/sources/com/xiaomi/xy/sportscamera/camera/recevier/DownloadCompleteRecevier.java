package com.xiaomi.xy.sportscamera.camera.recevier;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import com.ants360.z13.module.b;
import com.xiaomi.xy.sportscamera.camera.d;
import java.io.File;

/* loaded from: classes3.dex */
public class DownloadCompleteRecevier extends BroadcastReceiver {

    /* renamed from: a, reason: collision with root package name */
    d f4644a;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        this.f4644a = d.a();
        long longExtra = intent.getLongExtra("extra_download_id", 0L);
        DownloadManager downloadManager = (DownloadManager) context.getSystemService("download");
        if (longExtra == b.a().b()) {
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(longExtra);
            Cursor query2 = downloadManager.query(query);
            if (query2.moveToFirst()) {
                String string = query2.getString(query2.getColumnIndex("local_filename"));
                if (query2.getInt(query2.getColumnIndex("status")) == 8 && string != null) {
                    Intent intent2 = new Intent("android.intent.action.VIEW");
                    intent2.setDataAndType(Uri.fromFile(new File(string)), "application/vnd.android.package-archive");
                    intent2.addFlags(268435456);
                    context.startActivity(intent2);
                }
            }
            query2.close();
        }
    }
}
