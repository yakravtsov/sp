package com.xiaoyi.camera.devicedao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

/* loaded from: classes3.dex */
public class SportsCameraDao extends AbstractDao<a, String> {
    public static final String TABLENAME = "SPORTS_CAMERA";

    /* loaded from: classes3.dex */
    public static class Properties {

        /* renamed from: a, reason: collision with root package name */
        public static final Property f4852a = new Property(0, String.class, "macAdress", true, "MAC_ADRESS");
        public static final Property b = new Property(1, String.class, "ssid", false, "SSID");
        public static final Property c = new Property(2, String.class, "password", false, "PASSWORD");
    }

    public SportsCameraDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void a(SQLiteDatabase sQLiteDatabase, boolean z) {
        sQLiteDatabase.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "'SPORTS_CAMERA' ('MAC_ADRESS' TEXT PRIMARY KEY NOT NULL ,'SSID' TEXT,'PASSWORD' TEXT);");
    }

    public static void b(SQLiteDatabase sQLiteDatabase, boolean z) {
        sQLiteDatabase.execSQL("DROP TABLE " + (z ? "IF EXISTS " : "") + "'SPORTS_CAMERA'");
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
        aVar.b(cursor.isNull(i + 1) ? null : cursor.getString(i + 1));
        aVar.c(cursor.isNull(i + 2) ? null : cursor.getString(i + 2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // de.greenrobot.dao.AbstractDao
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void bindValues(SQLiteStatement sQLiteStatement, a aVar) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindString(1, aVar.a());
        String b = aVar.b();
        if (b != null) {
            sQLiteStatement.bindString(2, b);
        }
        String c = aVar.c();
        if (c != null) {
            sQLiteStatement.bindString(3, c);
        }
    }

    @Override // de.greenrobot.dao.AbstractDao
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public a readEntity(Cursor cursor, int i) {
        return new a(cursor.getString(i + 0), cursor.isNull(i + 1) ? null : cursor.getString(i + 1), cursor.isNull(i + 2) ? null : cursor.getString(i + 2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // de.greenrobot.dao.AbstractDao
    public boolean isEntityUpdateable() {
        return true;
    }
}
