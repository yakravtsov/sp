package com.xiaoyi.camera.module;

import com.xiaoyi.camera.controller.CameraMainController;
import com.xiaoyi.camera.g;
import java.io.Serializable;

/* loaded from: classes3.dex */
public class CameraSettingItem implements Serializable {
    public static String SETTING_ITEM = "setting_item";
    public static String SETTING_OPTIONS = "setting_options";
    public static final int TYPE_CLICK = 4;
    public static final int TYPE_CLICK_NO_RIGHT_ARRAY = 6;
    public static final int TYPE_CLICK_NO_VALUE = 5;
    public static final int TYPE_EMPTY = 7;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_PICKER = 1;
    public static final int TYPE_SELECTOR = 2;
    public static final int TYPE_SWITCH = 3;
    private static final long serialVersionUID = 1;
    public String displayName;
    public boolean enable;
    public String optionName;
    public String optionValue;
    public boolean stopVf;
    public int type;

    public CameraSettingItem(String str, String str2, int i) {
        this(str, str2, i, false);
    }

    public CameraSettingItem(String str, String str2, int i, boolean z) {
        this.enable = true;
        this.stopVf = true;
        this.displayName = str;
        this.optionName = str2;
        this.optionValue = g.a().a(str2);
        this.type = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            CameraSettingItem cameraSettingItem = (CameraSettingItem) obj;
            return this.optionName == null ? cameraSettingItem.optionName == null : this.optionName.equals(cameraSettingItem.optionName);
        }
        return false;
    }

    public int hashCode() {
        return (this.optionName == null ? 0 : this.optionName.hashCode()) + 31;
    }

    public void setOptionValue(String str) {
        if (this.optionName.equals("video_resolution")) {
            if (str.contains(CameraMainController.f4814a)) {
                this.optionValue = str.replace(CameraMainController.f4814a, CameraMainController.d);
                return;
            } else {
                this.optionValue = str;
                return;
            }
        }
        if (!this.optionName.equals("photo_size")) {
            this.optionValue = str;
        } else if (str.contains("fov")) {
            this.optionValue = str.replace("fov:", "");
        } else {
            this.optionValue = str;
        }
    }
}
