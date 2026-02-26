package com.xiaoyi.camera.downloadfiledao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import de.greenrobot.dao.AbstractDaoMaster;
import de.greenrobot.dao.identityscope.IdentityScopeType;

/* loaded from: classes3.dex */
public class DownloadFileDaoMaster extends AbstractDaoMaster {

    /* loaded from: classes3.dex */
    public static class a extends b {
        public a(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory) {
            super(context, str, cursorFactory);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            Log.i("greenDAO", "Upgrading schema from version " + i + " to " + i2 + " by dropping all tables");
            DownloadFileDaoMaster.b(sQLiteDatabase, true);
            onCreate(sQLiteDatabase);
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class b extends SQLiteOpenHelper {
        public b(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory) {
            super(context, str, cursorFactory, 2);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            Log.i("greenDAO", "Creating tables for schema version 2");
            DownloadFileDaoMaster.a(sQLiteDatabase, false);
        }
    }

    public DownloadFileDaoMaster(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase, 2);
        registerDaoClass(DownloadFileDao.class);
        registerDaoClass(FileDownloadLogDao.class);
    }

    public static void a(SQLiteDatabase sQLiteDatabase, boolean z) {
        DownloadFileDao.a(sQLiteDatabase, z);
        FileDownloadLogDao.a(sQLiteDatabase, z);
    }

    public static void b(SQLiteDatabase sQLiteDatabase, boolean z) {
        DownloadFileDao.b(sQLiteDatabase, z);
        FileDownloadLogDao.b(sQLiteDatabase, z);
    }

    @Override // de.greenrobot.dao.AbstractDaoMaster
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DownloadFileDaoSession newSession() {
        return new DownloadFileDaoSession(this.db, IdentityScopeType.Session, this.daoConfigMap);
    }

    @Override // de.greenrobot.dao.AbstractDaoMaster
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public DownloadFileDaoSession newSession(IdentityScopeType identityScopeType) {
        return new DownloadFileDaoSession(this.db, identityScopeType, this.daoConfigMap);
    }
}
