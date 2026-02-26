package com.xiaoyi.camera.d;

import android.content.Context;
import android.text.TextUtils;
import com.xiaoyi.camera.downloadfiledao.DownloadFileDao;
import com.xiaoyi.camera.downloadfiledao.DownloadFileDaoMaster;
import com.xiaoyi.camera.downloadfiledao.DownloadFileDaoSession;
import com.xiaoyi.camera.downloadfiledao.FileDownloadLogDao;
import de.greenrobot.dao.query.WhereCondition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private static d f4845a;
    private DownloadFileDao b;
    private DownloadFileDaoMaster c;
    private DownloadFileDaoSession d;
    private FileDownloadLogDao e;

    private d(Context context) {
        this.d = c(context);
        this.b = this.d.b();
        this.e = this.d.a();
    }

    public static d a(Context context) {
        if (f4845a == null) {
            f4845a = new d(context);
        }
        return f4845a;
    }

    public Map<Integer, Integer> a(String str) {
        List<com.xiaoyi.camera.downloadfiledao.b> list = this.e.queryBuilder().where(FileDownloadLogDao.Properties.b.eq(str), new WhereCondition[0]).orderAsc(FileDownloadLogDao.Properties.f4856a).list();
        HashMap hashMap = new HashMap();
        for (com.xiaoyi.camera.downloadfiledao.b bVar : list) {
            if (!TextUtils.isEmpty(bVar.c()) && !TextUtils.isEmpty(bVar.d())) {
                hashMap.put(Integer.valueOf(bVar.c()), Integer.valueOf(bVar.d()));
            }
        }
        return hashMap;
    }

    public void a(com.xiaoyi.camera.downloadfiledao.a aVar) {
        if (this.b.load(aVar.a()) == null) {
            this.b.insert(aVar);
        }
    }

    public void a(String str, int i, int i2) {
        com.xiaoyi.camera.downloadfiledao.b unique = this.e.queryBuilder().where(FileDownloadLogDao.Properties.b.eq(str), FileDownloadLogDao.Properties.c.eq(Integer.valueOf(i))).unique();
        if (unique != null) {
            unique.b(str);
            unique.c(String.valueOf(i));
            unique.d(String.valueOf(i2));
            this.e.update(unique);
        }
    }

    public void a(String str, Map<Integer, Integer> map) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            com.xiaoyi.camera.downloadfiledao.b bVar = new com.xiaoyi.camera.downloadfiledao.b();
            bVar.b(str);
            bVar.c(entry.getKey().toString());
            bVar.d(entry.getValue().toString());
            bVar.a(String.valueOf(System.currentTimeMillis()) + entry.getKey().toString());
            arrayList.add(bVar);
        }
        this.e.insertOrReplaceInTx(arrayList);
    }

    public DownloadFileDaoMaster b(Context context) {
        if (this.c == null) {
            this.c = new DownloadFileDaoMaster(new DownloadFileDaoMaster.a(context, "camera_download_db", null).getWritableDatabase());
        }
        return this.c;
    }

    public void b(String str) {
        List<com.xiaoyi.camera.downloadfiledao.b> list = this.e.queryBuilder().where(FileDownloadLogDao.Properties.b.eq(str), new WhereCondition[0]).list();
        if (list == null || list.size() == 0) {
            return;
        }
        this.e.deleteInTx(list);
    }

    public DownloadFileDaoSession c(Context context) {
        if (this.d == null) {
            if (this.c == null) {
                this.c = b(context);
            }
            this.d = this.c.newSession();
        }
        return this.d;
    }
}
