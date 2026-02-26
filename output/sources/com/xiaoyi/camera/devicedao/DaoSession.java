package com.xiaoyi.camera.devicedao;

import android.database.sqlite.SQLiteDatabase;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;
import java.util.Map;

/* loaded from: classes3.dex */
public class DaoSession extends AbstractDaoSession {

    /* renamed from: a, reason: collision with root package name */
    private final DaoConfig f4851a;
    private final SportsCameraDao b;

    public DaoSession(SQLiteDatabase sQLiteDatabase, IdentityScopeType identityScopeType, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig> map) {
        super(sQLiteDatabase);
        this.f4851a = map.get(SportsCameraDao.class).m27clone();
        this.f4851a.initIdentityScope(identityScopeType);
        this.b = new SportsCameraDao(this.f4851a, this);
        registerDao(a.class, this.b);
    }

    public SportsCameraDao a() {
        return this.b;
    }
}
