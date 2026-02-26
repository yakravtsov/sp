package com.xiaoyi.camera.module;

import android.widget.TextView;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class VideoFileItem extends FileItem implements Serializable {
    private static final long serialVersionUID = -8518846895253491522L;
    private VideoFileItem HDVideoFileItem;
    private String aspectRatio;
    private int duration;
    private boolean isHighDefinition;
    private boolean isOnlinePlayEnable;
    private ArrayList<VideoFileItem> mSplitFileList;
    private VideoFileItem thmFileItem;
    private TextView tvDudration;
    private int[] width_height = new int[2];

    public String getAspectRatio() {
        return this.aspectRatio;
    }

    public String getDuration() {
        if (this.duration != 0) {
            return com.yiaction.common.util.a.a(this.duration);
        }
        return null;
    }

    @Override // com.xiaoyi.camera.module.FileItem
    public VideoFileItem getHDVideoFileItem() {
        return this.HDVideoFileItem;
    }

    public int getIntDuration() {
        return this.duration;
    }

    public VideoFileItem getThmFileItem() {
        return this.thmFileItem;
    }

    public TextView getTvDudration() {
        return this.tvDudration;
    }

    public int[] getWidth_height() {
        return this.width_height;
    }

    public ArrayList<VideoFileItem> getmSplitFileList() {
        return this.mSplitFileList;
    }

    @Override // com.xiaoyi.camera.module.FileItem
    public boolean isHighDefinition() {
        return this.isHighDefinition;
    }

    public boolean isOnlinePlayEnable() {
        return this.isOnlinePlayEnable;
    }

    public void setAspectRatio(String str) {
        if (str != null) {
            String[] split = str.split("x");
            int intValue = Integer.valueOf(split[0]).intValue();
            int intValue2 = Integer.valueOf(split[1]).intValue();
            this.width_height[0] = intValue;
            this.width_height[1] = intValue2;
            if (intValue * 9 == intValue2 * 16) {
                this.aspectRatio = "16:9";
            } else if (intValue * 3 == intValue2 * 4) {
                this.aspectRatio = "4:3";
            }
        }
    }

    public void setDuration(int i) {
        if (i == 0) {
            this.duration = 1;
        } else {
            this.duration = i;
        }
    }

    @Override // com.xiaoyi.camera.module.FileItem
    public void setHDVideoFileItem(VideoFileItem videoFileItem) {
        this.HDVideoFileItem = videoFileItem;
    }

    @Override // com.xiaoyi.camera.module.FileItem
    public void setHighDefinition(boolean z) {
        this.isHighDefinition = z;
    }

    public void setOnlinePlayEnable(boolean z) {
        this.isOnlinePlayEnable = z;
    }

    public void setThmFileItem(VideoFileItem videoFileItem) {
        this.thmFileItem = videoFileItem;
    }

    public void setTvDudration(TextView textView) {
        this.tvDudration = textView;
    }

    public void setVideoAspectRatio(String str) {
        if (str != null) {
            String[] split = str.split(" ");
            this.aspectRatio = split[2];
            String[] split2 = split[0].split("x");
            int intValue = Integer.valueOf(split2[0]).intValue();
            int intValue2 = Integer.valueOf(split2[1]).intValue();
            this.width_height[0] = intValue;
            this.width_height[1] = intValue2;
        }
    }

    public void setmSplitFileList(ArrayList<VideoFileItem> arrayList) {
        this.mSplitFileList = arrayList;
    }
}
