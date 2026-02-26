package com.xiaoyi.camera.downloadfiledao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

/* loaded from: classes3.dex */
public class DownloadFileDao extends AbstractDao<a, String> {
    public static final String TABLENAME = "DOWNLOAD_FILE";

    /* loaded from: classes3.dex */
    public static class Properties {

        /* renamed from: a, reason: collision with root package name */
        public static final Property f4854a = new Property(0, String.class, "filepath", true, "FILEPATH");
        public static final Property b = new Property(1, Boolean.TYPE, "addsuccess", false, "ADDSUCCESS");
    }

    public DownloadFileDao(DaoConfig daoConfig, DownloadFileDaoSession downloadFileDaoSession) {
        super(daoConfig, downloadFileDaoSession);
    }

    public static void a(SQLiteDatabase sQLiteDatabase, boolean z) {
        sQLiteDatabase.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "'DOWNLOAD_FILE' ('FILEPATH' TEXT PRIMARY KEY NOT NULL ,'ADDSUCCESS' INTEGER NOT NULL );");
    }

    public static void b(SQLiteDatabase sQLiteDatabase, boolean z) {
        sQLiteDatabase.execSQL("DROP TABLE " + (z ? "IF EXISTS " : "") + "'DOWNLOAD_FILE'");
    }

    @Override // de.greenrobot.dao.AbstractDao
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String readKey(Cursor cursor, int i) {
        return cursor.getString(i + 0);
    }

    @Override // de.greenrobot.dao.AbstractDao
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String getKey(a aVar) {
        if (aVar != null) {
            return aVar.a();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // de.greenrobot.dao.AbstractDao
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String updateKeyAfterInsert(a aVar, long j) {
        return aVar.a();
    }

    @Override // de.greenrobot.dao.AbstractDao
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void readEntity(Cursor cursor, a aVar, int i) {
        aVar.a(cursor.getString(i + 0));
        aVar.a(cursor.getShort(i + 1) != 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // de.greenrobot.dao.AbstractDao
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void bindValues(SQLiteStatement sQLiteStatement, a aVar) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindString(1, aVar.a());
        sQLiteStatement.bindLong(2, aVar.b() ? 1L : 0L);
    }

    @Override // de.greenrobot.dao.AbstractDao
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public a readEntity(Cursor cursor, int i) {
        return new a(cursor.getString(i + 0), cursor.getShort(i + 1) != 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // de.greenrobot.dao.AbstractDao
    public boolean isEntityUpdateable() {
        return true;
    }
}
