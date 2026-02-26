package com.xiaoyi.camera.downloadfiledao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

/* loaded from: classes3.dex */
public class FileDownloadLogDao extends AbstractDao<b, String> {
    public static final String TABLENAME = "FileDownloadLog";

    /* loaded from: classes3.dex */
    public static class Properties {

        /* renamed from: a, reason: collision with root package name */
        public static final Property f4856a = new Property(0, String.class, "targetId", true, "TARGET_ID");
        public static final Property b = new Property(1, String.class, "downpath", false, "DOWNPATH");
        public static final Property c = new Property(2, String.class, "threadid", false, "THREADID");
        public static final Property d = new Property(3, String.class, "downlength", false, "DOWNLENGTH");
    }

    public FileDownloadLogDao(DaoConfig daoConfig, DownloadFileDaoSession downloadFileDaoSession) {
        super(daoConfig, downloadFileDaoSession);
    }

    public static void a(SQLiteDatabase sQLiteDatabase, boolean z) {
        sQLiteDatabase.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "'FileDownloadLog' ('TARGET_ID' TEXT PRIMARY KEY NOT NULL ,'DOWNPATH' TEXT,'THREADID' TEXT,'DOWNLENGTH' TEXT);");
    }

    public static void b(SQLiteDatabase sQLiteDatabase, boolean z) {
        sQLiteDatabase.execSQL("DROP TABLE " + (z ? "IF EXISTS " : "") + "'FileDownloadLog'");
    }

    @Override // de.greenrobot.dao.AbstractDao
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String readKey(Cursor cursor, int i) {
        return cursor.getString(i + 0);
    }

    @Override // de.greenrobot.dao.AbstractDao
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String getKey(b bVar) {
        if (bVar != null) {
            return bVar.a();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // de.greenrobot.dao.AbstractDao
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String updateKeyAfterInsert(b bVar, long j) {
        return bVar.a();
    }

    @Override // de.greenrobot.dao.AbstractDao
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void readEntity(Cursor cursor, b bVar, int i) {
        bVar.a(cursor.getString(i + 0));
        bVar.b(cursor.isNull(i + 1) ? null : cursor.getString(i + 1));
        bVar.c(cursor.isNull(i + 2) ? null : cursor.getString(i + 2));
        bVar.d(cursor.isNull(i + 3) ? null : cursor.getString(i + 3));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // de.greenrobot.dao.AbstractDao
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void bindValues(SQLiteStatement sQLiteStatement, b bVar) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindString(1, bVar.a());
        String b = bVar.b();
        if (b != null) {
            sQLiteStatement.bindString(2, b);
        }
        String c = bVar.c();
        if (c != null) {
            sQLiteStatement.bindString(3, c);
        }
        String d = bVar.d();
        if (d != null) {
            sQLiteStatement.bindString(4, d);
        }
    }

    @Override // de.greenrobot.dao.AbstractDao
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public b readEntity(Cursor cursor, int i) {
        return new b(cursor.getString(i + 0), cursor.isNull(i + 1) ? null : cursor.getString(i + 1), cursor.isNull(i + 2) ? null : cursor.getString(i + 2), cursor.isNull(i + 3) ? null : cursor.getString(i + 3));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // de.greenrobot.dao.AbstractDao
    public boolean isEntityUpdateable() {
        return true;
    }
}
