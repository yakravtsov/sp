package com.xiaoyi.camera;

import com.amap.api.services.core.AMapException;
import com.google.android.exoplayer.hls.HlsChunkSource;
import com.xiaoyi.player.NetworkUtil;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import org.json.JSONObject;
import wseemann.media.BuildConfig;

/* loaded from: classes3.dex */
public class b {
    public static boolean b = false;

    /* renamed from: a, reason: collision with root package name */
    com.xiaoyi.camera.c.b f4809a;
    private LinkedBlockingQueue<d> d;
    private d e;
    private a f;
    private Timer i;
    private TimerTask j;
    private final String c = "\\{[^\\{\\}]*(\\{[^\\{\\}]*\\}[^\\{\\}]*)*(\\[\\{[^\\{\\}]*\\}\\][^\\{\\}]*)*\\}";
    private boolean g = true;
    private boolean h = false;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class a extends Thread {
        private boolean b;
        private Pattern c;
        private StringBuilder d;
        private char[] e;
        private InputStreamReader f;
        private OutputStreamWriter g;
        private int h;
        private Socket i;

        private a() {
            this.h = -1;
        }

        private void a(d dVar) {
            if (dVar == null) {
                b.this.g = true;
                return;
            }
            try {
                if (dVar.a() == 257) {
                    dVar.a("token", 0);
                } else if (dVar.a() == 1793) {
                    dVar.a("token", Integer.valueOf(this.h));
                    b.this.g = true;
                } else {
                    dVar.a("token", Integer.valueOf(this.h));
                }
                JSONObject c = dVar.c();
                if (this.g != null && c != null) {
                    this.g.write(c.toString());
                    this.g.flush();
                }
                com.yiaction.common.util.g.a("debug_command", "req: " + c.toString(), new Object[0]);
            } catch (Exception e) {
                com.yiaction.common.util.g.a("debug_command", "req failed: " + dVar.c().toString() + "\n" + e, new Object[0]);
                b.this.g = true;
                if (b.this.e != null) {
                    if (b.this.e.b() != null) {
                        b.this.e.b().b(b.this.e, null);
                    }
                    b.this.e = null;
                }
                e.printStackTrace();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x00d4, code lost:
        
            com.yiaction.common.util.g.a("debug_json", "checkJson==> find, but not containt filter", new java.lang.Object[0]);
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void a(java.lang.String r9) {
            /*
                r8 = this;
                r7 = 1
                r3 = 0
                java.util.regex.Pattern r0 = r8.c
                java.util.regex.Matcher r0 = r0.matcher(r9)
                r1 = 0
                java.lang.String r2 = "debug_json"
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                java.lang.String r5 = "checkJson===>in.  arg="
                java.lang.StringBuilder r4 = r4.append(r5)
                java.lang.StringBuilder r4 = r4.append(r9)
                java.lang.String r4 = r4.toString()
                java.lang.Object[] r5 = new java.lang.Object[r3]
                com.yiaction.common.util.g.a(r2, r4, r5)
                r2 = r3
            L24:
                boolean r4 = r0.find()     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                if (r4 == 0) goto Lca
                java.lang.String r1 = r0.group()     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                java.lang.String r4 = "msg_id"
                boolean r4 = r1.contains(r4)     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                if (r4 == 0) goto Ld4
                int r2 = r0.start()     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                java.lang.String r4 = "debug_json"
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                r5.<init>()     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                java.lang.String r6 = "checkJson==> find..matcher.group=result="
                java.lang.StringBuilder r5 = r5.append(r6)     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                java.lang.StringBuilder r5 = r5.append(r1)     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                java.lang.String r6 = "matcher.start="
                java.lang.StringBuilder r5 = r5.append(r6)     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                java.lang.StringBuilder r5 = r5.append(r2)     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                java.lang.String r5 = r5.toString()     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                r6 = 0
                java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                com.yiaction.common.util.g.a(r4, r5, r6)     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                int r4 = r0.groupCount()     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                if (r4 <= r7) goto L66
                r2 = r3
            L66:
                r8.b(r1)     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                java.lang.String r4 = "debug_json"
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                r5.<init>()     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                java.lang.String r6 = "checkJson==> find, will dispatch:"
                java.lang.StringBuilder r5 = r5.append(r6)     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                java.lang.StringBuilder r5 = r5.append(r1)     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                java.lang.String r6 = ". and delete:"
                java.lang.StringBuilder r5 = r5.append(r6)     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                java.lang.StringBuilder r5 = r5.append(r2)     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                java.lang.String r6 = ","
                java.lang.StringBuilder r5 = r5.append(r6)     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                int r6 = r1.length()     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                java.lang.StringBuilder r5 = r5.append(r6)     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                java.lang.String r6 = ". from strBuilder"
                java.lang.StringBuilder r5 = r5.append(r6)     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                java.lang.String r5 = r5.toString()     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                r6 = 0
                java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                com.yiaction.common.util.g.a(r4, r5, r6)     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                java.lang.StringBuilder r4 = r8.d     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                int r5 = r1.length()     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                r4.delete(r2, r5)     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                goto L24
            Lad:
                r0 = move-exception
                java.lang.StringBuilder r4 = r8.d
                int r1 = r1.length()
                r4.delete(r2, r1)
                com.xiaoyi.camera.b r1 = com.xiaoyi.camera.b.this
                com.xiaoyi.camera.b.a(r1, r7)
                java.lang.String r1 = "debug_command"
                java.lang.String r2 = r0.getMessage()
                java.lang.Object[] r4 = new java.lang.Object[r3]
                com.yiaction.common.util.g.a(r1, r2, r4)
                r0.printStackTrace()
            Lca:
                java.lang.String r0 = "debug_json"
                java.lang.String r1 = "checkJson===>out"
                java.lang.Object[] r2 = new java.lang.Object[r3]
                com.yiaction.common.util.g.a(r0, r1, r2)
                return
            Ld4:
                java.lang.String r0 = "debug_json"
                java.lang.String r4 = "checkJson==> find, but not containt filter"
                r5 = 0
                java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                com.yiaction.common.util.g.a(r0, r4, r5)     // Catch: org.json.JSONException -> Lad java.io.IOException -> Ldf
                goto Lca
            Ldf:
                r0 = move-exception
                r0.printStackTrace()
                com.xiaoyi.camera.b r1 = com.xiaoyi.camera.b.this
                com.xiaoyi.camera.b.a(r1, r7)
                java.lang.String r1 = "debug_command"
                java.lang.String r0 = r0.getMessage()
                java.lang.Object[] r2 = new java.lang.Object[r3]
                com.yiaction.common.util.g.a(r1, r0, r2)
                goto Lca
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaoyi.camera.b.a.a(java.lang.String):void");
        }

        private boolean a() {
            for (int i = 2; i >= 0; i--) {
                if (this.b) {
                    return false;
                }
                try {
                    com.yiaction.common.util.g.a("debug_wifi", "socket creating", new Object[0]);
                    this.i = new Socket();
                    NetworkUtil.bindSocket(this.i);
                    InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getByName("192.168.42.1"), 7878);
                    this.i.setTcpNoDelay(true);
                    this.i.setTrafficClass(20);
                    this.i.setKeepAlive(true);
                    this.i.setPerformancePreferences(0, 1, 1);
                    this.i.setSoTimeout(2000);
                    this.i.setReuseAddress(true);
                    this.i.connect(inetSocketAddress, AMapException.CODE_AMAP_SHARE_LICENSE_IS_EXPIRED);
                    this.f = new InputStreamReader(this.i.getInputStream());
                    this.g = new OutputStreamWriter(this.i.getOutputStream());
                    this.c = Pattern.compile("\\{[^\\{\\}]*(\\{[^\\{\\}]*\\}[^\\{\\}]*)*(\\[\\{[^\\{\\}]*\\}\\][^\\{\\}]*)*\\}");
                    this.e = new char[32768];
                    this.d = new StringBuilder();
                    b.this.g = true;
                    com.yiaction.common.util.g.a("debug_wifi", "socket establish", new Object[0]);
                    return true;
                } catch (SocketTimeoutException e) {
                    e.printStackTrace();
                } catch (Exception e2) {
                    e2.printStackTrace();
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000L);
                    } catch (InterruptedException e3) {
                        e3.printStackTrace();
                    }
                }
            }
            com.yiaction.common.util.g.a("debug_wifi", "create socket failed!", new Object[0]);
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b() {
            if (this.i != null) {
                try {
                    this.i.close();
                    com.yiaction.common.util.g.a("debug_wifi", "socket closed", new Object[0]);
                } catch (IOException e) {
                    com.yiaction.common.util.g.a("debug_wifi", "socket close exception", new Object[0]);
                    e.printStackTrace();
                }
            }
        }

        private void b(String str) {
            if (str == null && b.this.e == null) {
                com.yiaction.common.util.g.a("debug_command", "json == null && mCurrentMessage == null", new Object[0]);
                com.yiaction.common.util.g.a("debug_command", "req response: " + str, new Object[0]);
                b.this.g = true;
                return;
            }
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("msg_id");
            if (optInt == 1793) {
                d dVar = new d(1793, null);
                dVar.a("rval", 0);
                b.this.b(dVar);
            } else if (optInt == 257) {
                if (jSONObject.optInt("rval") < 0 || jSONObject.optInt("param") < 0) {
                    if (b.this.e != null && b.this.e.b() != null) {
                        b.this.e.b().b(b.this.e, jSONObject);
                    }
                    b.this.g = true;
                    return;
                }
                d();
                b.b = true;
                this.h = jSONObject.optInt("param");
            } else if (optInt == 7) {
                com.yiaction.common.util.g.a("debug_command", "Notification: " + str, new Object[0]);
                b.this.f4809a.a(jSONObject);
                if (b.this.e != null && b.this.e.a() == 259) {
                    b.this.g = true;
                }
            }
            com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "mCurrentMessage != null  is  " + (b.this.e != null), new Object[0]);
            if (b.this.e != null) {
                com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "mCurrentMessage.getCommand()  is  " + b.this.e.a(), new Object[0]);
            }
            com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "msgId  is  " + optInt, new Object[0]);
            if (258 == optInt && jSONObject.optInt("rval") == 0) {
                b.b = false;
                de.greenrobot.event.c.a().c(new com.xiaoyi.camera.a.a(true));
                return;
            }
            com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "mCurrentMessage != null  is  " + (b.this.e != null), new Object[0]);
            if (b.this.e != null) {
                com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "mCurrentMessage.getCommand()  is  " + b.this.e.a(), new Object[0]);
            }
            com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "msgId  is  " + optInt, new Object[0]);
            if (b.this.e == null || b.this.e.a() != optInt) {
                return;
            }
            com.yiaction.common.util.g.a("debug_command", "req response: " + str, new Object[0]);
            b.this.g = true;
            if (b.this.e.b() != null) {
                int optInt2 = jSONObject.optInt("rval");
                if (optInt2 != 0) {
                    b.this.e.b().b(b.this.e, jSONObject);
                } else {
                    b.this.e.b().a(b.this.e, jSONObject);
                }
                if (optInt2 == -4) {
                    de.greenrobot.event.c.a().c(new com.xiaoyi.camera.a.a());
                }
            }
            b.this.e = null;
        }

        private void c() {
            try {
                if (this.f.ready()) {
                    int read = this.f.read(this.e);
                    if (read != -1) {
                        com.yiaction.common.util.g.a("debug_command", "recv raw(" + read + "):" + String.valueOf(this.e, 0, read), new Object[0]);
                        if (!this.b) {
                            this.d.append(this.e, 0, read);
                            com.yiaction.common.util.g.a("debug_command", "recv strBuilder====" + ((Object) this.d), new Object[0]);
                            if (this.d.charAt(0) == '{' && this.d.charAt(this.d.length() - 1) == '}') {
                                a(this.d.toString());
                            } else {
                                com.yiaction.common.util.g.a("debug_command", "recv: json not ready.len:=" + this.d.length(), new Object[0]);
                            }
                        }
                    } else {
                        com.yiaction.common.util.g.a("debug_command", "---------------------- socket disconnect", new Object[0]);
                    }
                } else {
                    TimeUnit.MILLISECONDS.sleep(50L);
                }
            } catch (IOException e) {
                e.printStackTrace();
                b.this.g = true;
                com.yiaction.common.util.g.a("debug_command", e.getMessage(), new Object[0]);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
                b.this.g = true;
                com.yiaction.common.util.g.a("debug_command", e3.getMessage(), new Object[0]);
            }
        }

        private void d() {
            try {
                c.b();
                c.a();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            if (a()) {
                while (!this.b) {
                    if (b.this.d == null || b.this.d.size() <= 0 || !b.this.g) {
                        c();
                    } else {
                        b.this.e = (d) b.this.d.poll();
                        b.this.g = false;
                        a(b.this.e);
                        if ("Z16".equals(g.a().a("model")) || "Z18".equals(g.a().a("model")) || "J11".equals(g.a().a("model")) || "J22".equals(g.a().a("model"))) {
                            if (b.this.e != null && b.this.e.a() != 16777244) {
                                com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "--------------------------------- cancelCheckHeartbeat ", new Object[0]);
                                b.this.d();
                                b.this.f();
                            }
                        }
                    }
                }
            } else if (b.this.d == null || b.this.d.size() <= 0) {
                com.yiaction.common.util.g.a("debug_wifi", "create socket failed  :  SocketInitFailEvent 2", new Object[0]);
                de.greenrobot.event.c.a().c(new com.xiaoyi.camera.a.e());
            } else {
                b.this.e = (d) b.this.d.poll();
                if (b.this.e == null || b.this.e.a() != 257 || b.this.e.b() == null) {
                    com.yiaction.common.util.g.a("debug_wifi", "create socket failed  :  SocketInitFailEvent 1", new Object[0]);
                    de.greenrobot.event.c.a().c(new com.xiaoyi.camera.a.e());
                } else {
                    com.yiaction.common.util.g.a("debug_wifi", "create socket failed  :  onReceiveErrorMessage", new Object[0]);
                    b.this.e.b().b(b.this.e, null);
                }
            }
            com.yiaction.common.util.g.a(BuildConfig.BUILD_TYPE, "receiver != null  sender != null  socket != null", new Object[0]);
            if (this.f != null) {
                try {
                    this.f.close();
                    this.f = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (this.g != null) {
                try {
                    this.g.close();
                    this.g = null;
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            if (this.i != null) {
                try {
                    this.i.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(com.xiaoyi.camera.c.b bVar) {
        this.f4809a = bVar;
    }

    private void c(d dVar) {
        if (!this.d.contains(dVar)) {
            this.d.add(dVar);
        } else {
            if (dVar.a() == 515 || dVar.a() == 16777241) {
                return;
            }
            this.d.add(dVar);
        }
    }

    private void e() {
        c.b();
        if (this.f != null) {
            this.f.b = true;
            this.f.b();
            this.f.interrupt();
            try {
                this.f.join(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.f = null;
        }
        if (this.d != null) {
            this.d.clear();
            this.d = null;
        }
        this.g = true;
        this.e = null;
        b = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.j = new TimerTask() { // from class: com.xiaoyi.camera.b.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                b.this.g();
            }
        };
        this.i = new Timer();
        this.i.schedule(this.j, HlsChunkSource.DEFAULT_MIN_BUFFER_TO_SWITCH_UP_MS, HlsChunkSource.DEFAULT_MIN_BUFFER_TO_SWITCH_UP_MS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        b(new d(16777244, null));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        if (this.d != null) {
            this.d.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(d dVar) {
        if (this.f == null || !this.f.isAlive()) {
            return;
        }
        this.g = true;
        b(dVar);
        this.h = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        e();
        if (this.d == null) {
            this.d = new LinkedBlockingQueue<>();
        }
        this.h = false;
        if (this.f == null || !this.f.isAlive()) {
            this.f = new a();
            this.f.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(d dVar) {
        if (this.d != null && !this.h) {
            c(dVar);
            return;
        }
        com.xiaoyi.camera.c.a b2 = dVar.b();
        if (b2 != null) {
            b2.b(dVar, null);
        }
    }

    public void c() {
        e();
    }

    public void d() {
        if (this.i != null) {
            this.j.cancel();
            this.j = null;
            this.i.cancel();
            this.i = null;
        }
    }
}
