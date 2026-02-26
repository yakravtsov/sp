package com.xiaoyi.camera.downloadfiledao;

import android.database.sqlite.SQLiteDatabase;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;
import java.util.Map;

/* loaded from: classes3.dex */
public class DownloadFileDaoSession extends AbstractDaoSession {

    /* renamed from: a, reason: collision with root package name */
    private final DaoConfig f4855a;
    private final DaoConfig b;
    private final FileDownloadLogDao c;
    private final DownloadFileDao d;

    public DownloadFileDaoSession(SQLiteDatabase sQLiteDatabase, IdentityScopeType identityScopeType, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig> map) {
        super(sQLiteDatabase);
        this.b = map.get(FileDownloadLogDao.class).m27clone();
        this.b.initIdentityScope(identityScopeType);
        this.f4855a = map.get(DownloadFileDao.class).m27clone();
        this.f4855a.initIdentityScope(identityScopeType);
        this.d = new DownloadFileDao(this.f4855a, this);
        this.c = new FileDownloadLogDao(this.b, this);
        registerDao(a.class, this.d);
        registerDao(b.class, this.c);
    }

    public FileDownloadLogDao a() {
        return this.c;
    }

    public DownloadFileDao b() {
        return this.d;
    }
}
