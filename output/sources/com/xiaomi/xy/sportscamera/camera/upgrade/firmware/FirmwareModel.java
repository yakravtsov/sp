package com.xiaomi.xy.sportscamera.camera.upgrade.firmware;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.ants360.z13.module.FastJsonModel;
import com.sina.weibo.sdk.component.ShareRequestParam;
import com.yiaction.common.util.g;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class FirmwareModel implements FastJsonModel {
    public static final int GZIP = 1;
    public String firmwareCode;
    public String firmwareMemo;
    public long firmwareSize;
    public String firmwareUrl;
    public String hardwareCode;
    public int hardwareVersion;
    public int isGzip;
    public String md5Code;
    public String originMd5Code;
    public String snPrefix = "Z13";

    public static List<FirmwareModel> parse(String str) {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONArray optJSONArray = new JSONObject(str).optJSONObject(ShareRequestParam.RESP_UPLOAD_PIC_PARAM_DATA).optJSONArray("firmwareList");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    int length = optJSONArray.length();
                    for (int i = 0; i < length; i++) {
                        FirmwareModel firmwareModel = (FirmwareModel) JSON.parseObject(optJSONArray.optString(i), FirmwareModel.class);
                        if (firmwareModel.isGzip == 1) {
                            arrayList.add(firmwareModel);
                        }
                    }
                }
            } catch (Exception e) {
                g.a("FirmwareModel", e.toString(), new Object[0]);
            }
        }
        return arrayList;
    }

    public static List<FirmwareModel> parse(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str2)) {
            try {
                JSONArray optJSONArray = new JSONObject(str2).optJSONObject(ShareRequestParam.RESP_UPLOAD_PIC_PARAM_DATA).optJSONArray("firmwareList");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    int length = optJSONArray.length();
                    for (int i = 0; i < length; i++) {
                        FirmwareModel firmwareModel = (FirmwareModel) JSON.parseObject(optJSONArray.optString(i), FirmwareModel.class);
                        if (firmwareModel.isGzip == 1 && str.equals(firmwareModel.snPrefix)) {
                            arrayList.add(firmwareModel);
                        }
                    }
                }
            } catch (Exception e) {
                g.a("FirmwareModel", e.toString(), new Object[0]);
            }
        }
        return arrayList;
    }
}
