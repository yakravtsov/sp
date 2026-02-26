package com.xiaoyi.camera.module;

import android.graphics.Bitmap;
import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes3.dex */
public class FileItem implements Serializable {
    private static final long serialVersionUID = 4085157680984317659L;
    private VideoFileItem HDVideoFileItem;
    protected long currentSize;
    protected Date date;
    protected String day;
    protected String deletPath;
    protected String destPath;
    protected volatile DownLoadState downloadState;
    private int fileTotal;
    private long headerId;
    protected String httpPath;
    protected String httpScreenUrl;
    protected String httpThumbUrl;
    protected boolean isDownLoadCompleted;
    protected boolean isDownLoading;
    protected volatile boolean isDownLoadingCancled;
    private boolean isHighDefinition;
    private boolean isSelected;
    protected String key;
    protected String md5;
    protected String month;
    protected String name;
    protected String nameType;
    protected String path;
    protected long size;
    protected String speed;
    protected Bitmap thumbnail;
    protected long thumbnailSize;
    protected String type;
    protected String year;
    public boolean needRefresh = true;
    public boolean thumbLoaded = false;
    public CameraMediaType mediaType = CameraMediaType.NONE;
    private AtomicBoolean isLoadingImage = new AtomicBoolean(false);

    /* loaded from: classes3.dex */
    public enum Action implements Serializable {
        THUMBNAIL,
        DOWNLOAD,
        DELETE,
        LISTING
    }

    /* loaded from: classes3.dex */
    public enum DownLoadState implements Serializable {
        WAIT,
        DOWNLOADING,
        STOP
    }

    public FileItem() {
    }

    public FileItem(String str, long j, String str2, String str3, String str4, Date date) {
        this.type = str;
        this.size = j;
        this.name = str2;
        this.path = str3;
        this.httpPath = str4;
        this.date = date;
    }

    public static String getRealNameType(String str) {
        return str.contains("MEDIA/") ? str.split("MEDIA/")[1] : str;
    }

    public long getCurrentSize() {
        return this.currentSize;
    }

    public Date getDate() {
        return this.date;
    }

    public String getDay() {
        return this.day;
    }

    public String getDeletPath() {
        return this.deletPath;
    }

    public String getDestPath() {
        return this.destPath;
    }

    public DownLoadState getDownloadState() {
        return this.downloadState;
    }

    public VideoFileItem getHDVideoFileItem() {
        return this.HDVideoFileItem;
    }

    public long getHeaderId() {
        return this.headerId;
    }

    public String getHttpPath() {
        return this.httpPath;
    }

    public String getHttpPath(boolean z) {
        return (z && this.httpPath.contains("_thm")) ? this.httpPath.replace("_thm", "") : this.httpPath;
    }

    public String getHttpThumbUrl() {
        return this.httpThumbUrl;
    }

    public String getKey() {
        return this.key;
    }

    public String getMd5() {
        return this.md5;
    }

    public String getMonth() {
        return this.month;
    }

    public String getName() {
        return this.name;
    }

    public String getNameType() {
        return this.nameType;
    }

    public String getPath() {
        return this.path;
    }

    public String getScreenThumbUrl() {
        return this.httpScreenUrl;
    }

    public long getSize() {
        return this.size;
    }

    public String getSpeed() {
        return this.speed;
    }

    public Bitmap getThumbnail() {
        return this.thumbnail;
    }

    public long getThumbnailSize() {
        return this.thumbnailSize;
    }

    public int getTotal() {
        return this.fileTotal;
    }

    public String getType() {
        return this.type;
    }

    public String getYear() {
        return this.year;
    }

    public boolean isDownLoadCompleted() {
        return this.isDownLoadCompleted;
    }

    public boolean isDownLoading() {
        return this.isDownLoading;
    }

    public boolean isDownLoadingCancled() {
        return this.isDownLoadingCancled;
    }

    public boolean isHighDefinition() {
        return this.isHighDefinition;
    }

    public boolean isLoadingImage() {
        return this.isLoadingImage.get();
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setCurrentSize(long j) {
        this.currentSize = j;
        if (this.size == 0 || this.currentSize != this.size) {
            return;
        }
        this.isDownLoadCompleted = true;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDay(String str) {
        this.day = str;
    }

    public void setDeletPath(String str) {
        this.deletPath = str;
    }

    public void setDestPath(String str) {
        this.destPath = str;
    }

    public void setDownLoadCompleted(boolean z) {
        this.isDownLoadCompleted = z;
    }

    public void setDownLoading(boolean z) {
        this.isDownLoading = z;
    }

    public void setDownLoadingCancled(boolean z) {
        this.isDownLoadingCancled = z;
    }

    public void setDownloadState(DownLoadState downLoadState) {
        this.downloadState = downLoadState;
    }

    public void setHDVideoFileItem(VideoFileItem videoFileItem) {
        this.HDVideoFileItem = videoFileItem;
    }

    public void setHeaderId(long j) {
        this.headerId = j;
    }

    public void setHighDefinition(boolean z) {
        this.isHighDefinition = z;
    }

    public void setHttpPath(String str) {
        this.httpPath = str;
    }

    public void setHttpScreenUrl(String str) {
        this.httpScreenUrl = str;
    }

    public void setHttpThumbUrl(String str) {
        this.httpThumbUrl = str;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public void setLoadingImage(boolean z) {
        this.isLoadingImage.set(z);
    }

    public void setMd5(String str) {
        this.md5 = str;
    }

    public void setMonth(String str) {
        this.month = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setNameType(String str) {
        this.nameType = str;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public void setSpeed(String str) {
        this.speed = str;
    }

    public void setThumbnail(Bitmap bitmap) {
        this.thumbnail = bitmap;
    }

    public void setThumbnailSize(long j) {
        this.thumbnailSize = j;
    }

    public void setTotal(int i) {
        this.fileTotal = i;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setYear(String str) {
        this.year = str;
    }
}
