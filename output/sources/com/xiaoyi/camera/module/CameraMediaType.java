package com.xiaoyi.camera.module;

/* loaded from: classes3.dex */
public enum CameraMediaType {
    NONE,
    NORMAL_PHOTO,
    MULTI_PHOTO,
    TIMELAPSE_PHOTO,
    BURST_PHOTO,
    PIV_PHOTO,
    SELF_PHOTO,
    NORMAL_VIDEO,
    TIMELAPASE_VIDEO,
    PHOTO_VIDEO,
    LOOP_VIDEO,
    SLOW_MOTION_VIDEO;

    public static boolean isMultiPhoto(CameraMediaType cameraMediaType) {
        return (cameraMediaType == NORMAL_PHOTO || cameraMediaType == SELF_PHOTO) ? false : true;
    }

    public static CameraMediaType parsePhotoType(String str) {
        String realNameType = FileItem.getRealNameType(str);
        return realNameType.equals("DLAY") ? SELF_PHOTO : (realNameType.equals("YDXJ") || realNameType.equals("YIAC")) ? NORMAL_PHOTO : realNameType.startsWith("T") ? PIV_PHOTO : realNameType.startsWith("C") ? BURST_PHOTO : MULTI_PHOTO;
    }

    public static CameraMediaType parseVideoType(String str) {
        return (str.equals("YDTL") || str.startsWith("YT")) ? TIMELAPASE_VIDEO : (str.equals("YPXJ") || str.startsWith("YP")) ? PHOTO_VIDEO : str.startsWith("L") ? LOOP_VIDEO : str.equals("YSXJ") ? SLOW_MOTION_VIDEO : NORMAL_VIDEO;
    }
}
