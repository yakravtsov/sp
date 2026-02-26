package com.yiaction.common.http;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.i;
import okhttp3.t;

/* loaded from: classes3.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private t f4932a;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private static final c f4935a = new c();
    }

    private c() {
        SSLContext sSLContext;
        NoSuchAlgorithmException e;
        KeyManagementException e2;
        X509TrustManager x509TrustManager = new X509TrustManager() { // from class: com.yiaction.common.http.c.1
            @Override // javax.net.ssl.X509TrustManager
            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            @Override // javax.net.ssl.X509TrustManager
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        try {
            sSLContext = SSLContext.getInstance("SSL");
            try {
                sSLContext.init(null, new TrustManager[]{x509TrustManager}, new SecureRandom());
            } catch (KeyManagementException e3) {
                e2 = e3;
                e2.printStackTrace();
                HostnameVerifier hostnameVerifier = new HostnameVerifier() { // from class: com.yiaction.common.http.c.2
                    @Override // javax.net.ssl.HostnameVerifier
                    public boolean verify(String str, SSLSession sSLSession) {
                        return true;
                    }
                };
                t.a x = new t().x();
                x.a(10L, TimeUnit.SECONDS).c(30L, TimeUnit.SECONDS).b(30L, TimeUnit.SECONDS).a(new i()).a(sSLContext.getSocketFactory()).a(hostnameVerifier);
                this.f4932a = x.a();
            } catch (NoSuchAlgorithmException e4) {
                e = e4;
                e.printStackTrace();
                HostnameVerifier hostnameVerifier2 = new HostnameVerifier() { // from class: com.yiaction.common.http.c.2
                    @Override // javax.net.ssl.HostnameVerifier
                    public boolean verify(String str, SSLSession sSLSession) {
                        return true;
                    }
                };
                t.a x2 = new t().x();
                x2.a(10L, TimeUnit.SECONDS).c(30L, TimeUnit.SECONDS).b(30L, TimeUnit.SECONDS).a(new i()).a(sSLContext.getSocketFactory()).a(hostnameVerifier2);
                this.f4932a = x2.a();
            }
        } catch (KeyManagementException e5) {
            sSLContext = null;
            e2 = e5;
        } catch (NoSuchAlgorithmException e6) {
            sSLContext = null;
            e = e6;
        }
        HostnameVerifier hostnameVerifier22 = new HostnameVerifier() { // from class: com.yiaction.common.http.c.2
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String str, SSLSession sSLSession) {
                return true;
            }
        };
        t.a x22 = new t().x();
        x22.a(10L, TimeUnit.SECONDS).c(30L, TimeUnit.SECONDS).b(30L, TimeUnit.SECONDS).a(new i()).a(sSLContext.getSocketFactory()).a(hostnameVerifier22);
        this.f4932a = x22.a();
    }

    public static c a() {
        return a.f4935a;
    }

    public t b() {
        return this.f4932a;
    }
}
