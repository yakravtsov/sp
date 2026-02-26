package com.yiaction.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/* loaded from: classes3.dex */
public class l {
    public static String a(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }
}
