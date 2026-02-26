package com.xiaomi.xy.sportscamera.camera;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.ants360.z13.c.x;
import com.xiaoyi.camera.module.CameraMediaType;
import com.xiaoyi.camera.module.FileItem;
import com.xiaoyi.camera.module.PhotoFileItem;
import com.xiaoyi.camera.module.VideoFileItem;
import com.yiaction.common.util.g;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import wseemann.media.BuildConfig;
import wseemann.media.FFmpegMediaMetadataRetriever;

/* loaded from: classes3.dex */
public class b implements com.xiaoyi.camera.c.a {
    private static final String b = b.class.getSimpleName();
    private static b c;

    /* renamed from: a, reason: collision with root package name */
    public List<FileItem> f4544a;
    private com.xiaoyi.camera.b.b e;
    private VideoFileItem f;
    private C0191b h;
    private e i;
    private List<VideoFileItem> l;
    private List<PhotoFileItem> m;
    private LinkedBlockingQueue<VideoFileItem> n;
    private LinkedBlockingQueue<com.xiaoyi.camera.b.b> o;
    private int p;
    private boolean q;
    private volatile boolean r;
    private boolean s;
    private boolean t;
    private List<String> u;
    private SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    private Pattern g = Pattern.compile("[0-9]{3}MEDIA");
    private ExecutorService j = Executors.newFixedThreadPool(3);
    private Handler k = new Handler(Looper.getMainLooper());

    /* loaded from: classes3.dex */
    class a extends com.xiaoyi.camera.b.b {
        private FileItem e;

        public a(FileItem fileItem) {
            this.e = fileItem;
        }

        @Override // com.xiaoyi.camera.b.b
        public FileItem a() {
            return this.e;
        }

        @Override // com.xiaoyi.camera.b.b
        public void a(boolean z) {
        }

        @Override // com.xiaoyi.camera.b.b
        public FileItem.Action b() {
            return FileItem.Action.DELETE;
        }

        @Override // java.lang.Runnable
        public void run() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.xy.sportscamera.camera.b$b, reason: collision with other inner class name */
    /* loaded from: classes3.dex */
    public class C0191b extends Thread {
        private boolean b = false;

        public C0191b() {
        }

        public void a(com.xiaoyi.camera.b.b bVar) {
            if (bVar == null) {
                Log.w("debug_action", "dispatchByAction:: task is null");
                return;
            }
            switch (bVar.b()) {
                case DELETE:
                    b.this.a(false);
                    FileItem a2 = bVar.a();
                    g.a("Strat delete file :" + a2.getName(), new Object[0]);
                    a2.setDownLoadingCancled(true);
                    if (com.xiaomi.xy.sportscamera.camera.a.a().f(a2)) {
                        com.xiaomi.xy.sportscamera.camera.a.a().g(a2);
                        com.xiaomi.xy.sportscamera.camera.a.a().j(a2);
                        com.xiaomi.xy.sportscamera.camera.a.a().i(a2);
                    }
                    com.xiaoyi.camera.g.a().f(bVar.a().getDeletPath(), b.this);
                    return;
                case LISTING:
                    b.this.a(false);
                    com.xiaoyi.camera.g.a().a(bVar.c() + " -D -S", bVar.h(), bVar.i(), b.this);
                    return;
                case THUMBNAIL:
                default:
                    return;
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            b.this.a(true);
            g.a("debug_file", "-------FileControlThread---------isStop:" + this.b, new Object[0]);
            while (!this.b) {
                if (b.this.o == null || b.this.o.size() <= 0) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(150L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    g.a("debug_file", "-------FileControlThread---------Queue size:" + b.this.o.size(), new Object[0]);
                    if (b.this.n()) {
                        b.this.e = (com.xiaoyi.camera.b.b) b.this.o.poll();
                        a(b.this.e);
                    } else {
                        try {
                            TimeUnit.MILLISECONDS.sleep(150L);
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /* loaded from: classes3.dex */
    class c extends com.xiaoyi.camera.b.b {
        private JSONObject e;
        private String f;
        private String g = "bytes\\|";

        public c(String str) {
            this.f = str;
        }

        private void b(JSONObject jSONObject) {
            String[] split;
            try {
                g.a("debug_file", "receive directory command response, json: " + jSONObject, new Object[0]);
                JSONArray jSONArray = jSONObject.getJSONArray("listing");
                if (jSONArray == null || jSONArray.length() == 0) {
                    b.this.p();
                    return;
                }
                ArrayList<FileItem> arrayList = new ArrayList();
                for (int length = jSONArray.length() - 1; length >= 0; length--) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(length);
                    Iterator<String> keys = jSONObject2.keys();
                    if (keys.hasNext()) {
                        String next = keys.next();
                        if (b.this.g.matcher(next).find()) {
                            String optString = jSONObject2.optString(next);
                            if (!TextUtils.isEmpty(optString) && (split = optString.split("\\|")) != null && split.length == 2) {
                                FileItem fileItem = new FileItem();
                                fileItem.setName(next);
                                fileItem.setPath("/tmp/fuse_d/DCIM/" + next);
                                g.a("debug_file", "add InitMediaListTask in Queue, dir: " + next, new Object[0]);
                                try {
                                    fileItem.setDate(b.this.d.parse(split[1]));
                                    String str = split[0];
                                    if (a(str)) {
                                        fileItem.setTotal(Integer.parseInt(str));
                                    }
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                } catch (ParseException e2) {
                                    e2.printStackTrace();
                                }
                                arrayList.add(fileItem);
                            }
                        }
                    }
                }
                if (arrayList == null || arrayList.isEmpty()) {
                    b.this.p();
                    return;
                }
                Collections.sort(arrayList, new Comparator<FileItem>() { // from class: com.xiaomi.xy.sportscamera.camera.b.c.1
                    @Override // java.util.Comparator
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public int compare(FileItem fileItem2, FileItem fileItem3) {
                        if (fileItem2 == null) {
                            return 1;
                        }
                        if (fileItem3 == null) {
                            return -1;
                        }
                        if (fileItem2.getDate() == null) {
                            return 1;
                        }
                        if (fileItem3.getDate() == null) {
                            return -1;
                        }
                        int compareTo = fileItem3.getDate().compareTo(fileItem2.getDate());
                        return compareTo == 0 ? fileItem3.getName().compareTo(fileItem2.getName()) : compareTo;
                    }
                });
                for (FileItem fileItem2 : arrayList) {
                    int total = fileItem2.getTotal();
                    int i = total / 500;
                    g.a(BuildConfig.BUILD_TYPE, "--------------- total = " + total, new Object[0]);
                    g.a(BuildConfig.BUILD_TYPE, "--------------- count = " + i, new Object[0]);
                    if (i >= 1) {
                        for (int i2 = 0; i2 <= i; i2++) {
                            int i3 = i2 * 500;
                            int i4 = (i2 + 1) * 500 > total ? total - 1 : (500 * (i2 + 1)) - 1;
                            g.a(BuildConfig.BUILD_TYPE, "--------------- from = " + i3, new Object[0]);
                            g.a(BuildConfig.BUILD_TYPE, "--------------- to = " + i4, new Object[0]);
                            d dVar = new d(fileItem2.getPath());
                            dVar.a(i3, i4);
                            b.this.o.add(dVar);
                            b.j(b.this);
                        }
                    } else {
                        b.j(b.this);
                        d dVar2 = new d(fileItem2.getPath());
                        dVar2.a(this.b, total - 1);
                        b.this.o.add(dVar2);
                    }
                    g.a(BuildConfig.BUILD_TYPE, "--------------- mWorkingQueue.size = " + b.this.o.size(), new Object[0]);
                }
                b.this.o();
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }

        public void a(int i, int i2) {
            this.b = i;
            this.c = i2;
        }

        @Override // com.xiaoyi.camera.b.b
        public void a(JSONObject jSONObject) {
            this.e = jSONObject;
        }

        @Override // com.xiaoyi.camera.b.b
        public void a(boolean z) {
        }

        public boolean a(String str) {
            return Pattern.compile("[0-9]*").matcher(str).matches();
        }

        @Override // com.xiaoyi.camera.b.b
        public FileItem.Action b() {
            return FileItem.Action.LISTING;
        }

        @Override // com.xiaoyi.camera.b.b
        public String c() {
            return this.f;
        }

        @Override // java.lang.Runnable
        public void run() {
            b(this.e);
            b.this.a(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class d extends com.xiaoyi.camera.b.b {
        private String e = "bytes\\|";
        private String f = "byte\\|";
        private String g;
        private JSONObject h;

        public d(String str) {
            this.g = str;
        }

        private VideoFileItem a(String str, List<FileItem> list) {
            VideoFileItem videoFileItem;
            if (b.this.l == null) {
                return null;
            }
            synchronized (this) {
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= b.this.l.size()) {
                        videoFileItem = null;
                        break;
                    }
                    if (((VideoFileItem) b.this.l.get(i2)).getName().equals(str)) {
                        videoFileItem = (VideoFileItem) b.this.l.remove(i2);
                        list.remove(videoFileItem);
                        break;
                    }
                    i = i2 + 1;
                }
            }
            return videoFileItem;
        }

        private String a(String[] strArr) {
            return (strArr == null || strArr.length <= 1) ? "" : strArr[1];
        }

        private void a(String str, JSONObject jSONObject) {
            try {
                ArrayList arrayList = new ArrayList();
                g.a(BuildConfig.BUILD_TYPE, "get directory files:" + str, new Object[0]);
                JSONArray jSONArray = jSONObject.getJSONArray("listing");
                g.a(BuildConfig.BUILD_TYPE, "--------------- array.length = " + jSONArray.length(), new Object[0]);
                if (jSONArray != null && jSONArray.length() > 0) {
                    for (int length = jSONArray.length() - 1; length >= 0; length--) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(length);
                        String next = jSONObject2.keys().next();
                        String[] split = jSONObject2.getString(next).split(this.e);
                        if (split.length == 1) {
                            split = jSONObject2.getString(next).split(this.f);
                        }
                        if (next.endsWith("mp4") || next.endsWith("MP4") || next.endsWith(".sec") || next.endsWith(".SEC")) {
                            b(str, next, split, arrayList);
                        } else if (next.endsWith("jpg") || next.endsWith("JPG")) {
                            a(str, next, split, arrayList);
                        } else {
                            Log.d(b.b, "unknown file extension");
                        }
                    }
                }
                if (arrayList != null && !arrayList.isEmpty() && b.this.f4544a != null) {
                    b.this.a(arrayList);
                    if (b.this.f4544a == null) {
                        b.this.f4544a = Collections.synchronizedList(new ArrayList());
                    }
                    b.this.f4544a.addAll(arrayList);
                    g.a(BuildConfig.BUILD_TYPE, "--------------- dicFileListItems.size = " + arrayList.size(), new Object[0]);
                }
                b.m(b.this);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private VideoFileItem b(String str, List<FileItem> list) {
            VideoFileItem videoFileItem;
            if (b.this.l == null) {
                return null;
            }
            synchronized (this) {
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= b.this.l.size()) {
                        videoFileItem = null;
                        break;
                    }
                    if (((VideoFileItem) b.this.l.get(i2)).getName().startsWith(str)) {
                        videoFileItem = (VideoFileItem) b.this.l.remove(i2);
                        list.remove(videoFileItem);
                        break;
                    }
                    i = i2 + 1;
                }
            }
            return videoFileItem;
        }

        private boolean f() {
            return com.xiaoyi.camera.g.a().a("model").equals("Z18") || com.xiaoyi.camera.g.a().a("model").equals("J11") || com.xiaoyi.camera.g.a().a("model").equals("J22");
        }

        public void a(int i, int i2) {
            this.b = i;
            this.c = i2;
        }

        public void a(String str, String str2, String[] strArr, List<FileItem> list) {
            PhotoFileItem photoFileItem = new PhotoFileItem();
            photoFileItem.setType("thumb");
            photoFileItem.setName(str2);
            photoFileItem.setPath(str + str2);
            photoFileItem.setNameType(str + str2.substring(0, 4));
            photoFileItem.mediaType = CameraMediaType.parsePhotoType(photoFileItem.getNameType());
            if (strArr != null) {
                if (strArr.length == 1) {
                    g.a(strArr[0], new Object[0]);
                }
                String substring = strArr[1].substring(8, 10);
                String substring2 = strArr[1].substring(5, 7);
                String substring3 = strArr[1].substring(0, 4);
                photoFileItem.setSize(Long.parseLong(strArr[0].trim()));
                try {
                    long parseInt = (Integer.parseInt(substring3) * 10000) + (Integer.parseInt(substring2) * 100) + Integer.parseInt(substring);
                    if (parseInt > 0) {
                        photoFileItem.setHeaderId(parseInt);
                    }
                } catch (Exception e) {
                    g.a(e.toString(), new Object[0]);
                }
                try {
                    photoFileItem.setDate(b.this.d.parse(strArr[1]));
                } catch (ParseException e2) {
                    e2.printStackTrace();
                }
                photoFileItem.setYear(substring3);
                photoFileItem.setMonth(substring2);
                photoFileItem.setDay(substring);
            }
            photoFileItem.setDeletPath(str + str2);
            String replace = photoFileItem.getPath().replace("/tmp/fuse_d/DCIM/", "http://192.168.42.1/DCIM/");
            photoFileItem.setHttpPath(replace + (f() ? "?time=" + URLEncoder.encode(a(strArr)) + "&sn=" + com.xiaoyi.camera.g.a().a("serial_number") : ""));
            photoFileItem.setHttpThumbUrl(replace + "?type=thumb" + (f() ? "&time=" + URLEncoder.encode(a(strArr)) + "&sn=" + com.xiaoyi.camera.g.a().a("serial_number") : ""));
            photoFileItem.setHttpScreenUrl(replace + "?type=screen" + (f() ? "&time=" + URLEncoder.encode(a(strArr)) + "&sn=" + com.xiaoyi.camera.g.a().a("serial_number") : ""));
            if (b.this.m == null) {
                b.this.m = Collections.synchronizedList(new ArrayList());
            }
            if (b.this.u == null) {
                b.this.u = new ArrayList();
            }
            if (b.this.m != null) {
                b.this.m.add(photoFileItem);
                if (!CameraMediaType.isMultiPhoto(photoFileItem.mediaType)) {
                    list.add(photoFileItem);
                } else {
                    if (b.this.u.contains(photoFileItem.getNameType())) {
                        return;
                    }
                    b.this.u.add(photoFileItem.getNameType());
                    list.add(photoFileItem);
                }
            }
        }

        @Override // com.xiaoyi.camera.b.b
        public void a(JSONObject jSONObject) {
            this.h = jSONObject;
        }

        @Override // com.xiaoyi.camera.b.b
        public FileItem.Action b() {
            return FileItem.Action.LISTING;
        }

        public void b(String str, String str2, String[] strArr, List<FileItem> list) {
            VideoFileItem videoFileItem = null;
            VideoFileItem videoFileItem2 = new VideoFileItem();
            videoFileItem2.setType("idr");
            videoFileItem2.setName(str2);
            videoFileItem2.setPath(str + str2);
            videoFileItem2.setDeletPath(videoFileItem2.getPath());
            videoFileItem2.setHttpPath(videoFileItem2.getPath().replace("/tmp/fuse_d/DCIM/", "http://192.168.42.1/DCIM/") + "?time=" + URLEncoder.encode(a(strArr)) + "&sn=" + com.xiaoyi.camera.g.a().a("serial_number"));
            videoFileItem2.setNameType("YDXJ");
            videoFileItem2.mediaType = CameraMediaType.parseVideoType(str2.substring(0, 4));
            if (str2.contains("_thm") || str2.startsWith("QUICK_") || str2.endsWith(".sec") || str2.endsWith(".SEC")) {
                videoFileItem2.setHighDefinition(false);
                if (str2.contains("_thm")) {
                    String httpPath = videoFileItem2.getHttpPath();
                    if (str2.endsWith("_thm.mp4")) {
                        videoFileItem2.setHttpThumbUrl(httpPath.replace("_thm.mp4", ".THM"));
                    }
                    if (str2.endsWith("_thm.MP4")) {
                        videoFileItem2.setHttpThumbUrl(httpPath.replace("_thm.MP4", ".THM"));
                    }
                } else if (str2.startsWith("QUICK_")) {
                    String str3 = str.replace("/tmp/fuse_d/DCIM/", "http://192.168.42.1/DCIM/") + str2.replace("QUICK_", "");
                    if (str3.endsWith(".mp4")) {
                        videoFileItem2.setHttpThumbUrl(str3.replace(".mp4", ".THM"));
                    }
                    if (str3.endsWith(".MP4")) {
                        videoFileItem2.setHttpThumbUrl(str3.replace(".MP4", ".THM"));
                    }
                } else if (str2.endsWith(".sec") || str2.endsWith(".SEC")) {
                    String httpPath2 = videoFileItem2.getHttpPath();
                    videoFileItem2.setHttpThumbUrl(httpPath2.replace(".sec", ".THM"));
                    videoFileItem2.setHttpThumbUrl(httpPath2.replace(".SEC", ".THM"));
                } else {
                    String httpPath3 = videoFileItem2.getHttpPath();
                    if (str2.endsWith(".mp4")) {
                        videoFileItem2.setHttpThumbUrl(httpPath3.replace(".mp4", ".THM"));
                    }
                    if (str2.endsWith(".MP4")) {
                        videoFileItem2.setHttpThumbUrl(httpPath3.replace(".MP4", ".THM"));
                    }
                }
            } else {
                videoFileItem2.setHighDefinition(true);
                videoFileItem2.setHDVideoFileItem(videoFileItem2);
                videoFileItem2.setThmFileItem(null);
                String httpPath4 = videoFileItem2.getHttpPath();
                if (str2.endsWith(".mp4")) {
                    videoFileItem2.setHttpThumbUrl(httpPath4.replace(".mp4", ".THM"));
                }
                if (str2.endsWith(".MP4")) {
                    videoFileItem2.setHttpThumbUrl(httpPath4.replace(".MP4", ".THM"));
                }
            }
            if (strArr != null) {
                String substring = strArr[1].substring(8, 10);
                String substring2 = strArr[1].substring(5, 7);
                String substring3 = strArr[1].substring(0, 4);
                videoFileItem2.setSize(Long.parseLong(strArr[0].trim()));
                videoFileItem2.setDay(substring);
                videoFileItem2.setMonth(substring2);
                videoFileItem2.setYear(substring3);
                videoFileItem2.setHeaderId(Long.valueOf(substring3 + substring2 + substring).longValue());
                try {
                    videoFileItem2.setDate(b.this.d.parse(strArr[1]));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (b.this.l == null) {
                b.this.l = Collections.synchronizedList(new ArrayList());
            }
            if (list != null && list.size() > 0) {
                if (str2.contains("_thm")) {
                    VideoFileItem a2 = a(str2.replace("_thm", ""), list);
                    if (a2 != null) {
                        videoFileItem2.setHDVideoFileItem(a2);
                        a2.setThmFileItem(videoFileItem2);
                        videoFileItem2.setDeletPath(a2.getPath());
                        videoFileItem2.setHighDefinition(false);
                    } else {
                        videoFileItem2.setDeletPath(videoFileItem2.getPath());
                    }
                } else if (str2.endsWith(".sec") || str2.endsWith(".SEC")) {
                    if (str2.endsWith(".SEC")) {
                        videoFileItem = a(str2.replace(".SEC", ".MP4"), list);
                    } else if (str2.endsWith(".sec")) {
                        videoFileItem = a(str2.replace(".sec", ".MP4"), list);
                    }
                    if (videoFileItem != null) {
                        videoFileItem2.setHDVideoFileItem(videoFileItem);
                        videoFileItem2.setDeletPath(videoFileItem.getPath());
                        videoFileItem2.setHighDefinition(false);
                    } else {
                        videoFileItem2.setDeletPath(videoFileItem2.getPath());
                    }
                } else {
                    VideoFileItem b = str2.endsWith(".mp4") ? b(str2.replace(".mp4", ""), list) : str2.endsWith(".MP4") ? b(str2.replace(".MP4", ""), list) : null;
                    if (b != null && b.this.l != null) {
                        b.setHDVideoFileItem(videoFileItem2);
                        videoFileItem2.setThmFileItem(b);
                        b.setDeletPath(videoFileItem2.getPath());
                        b.this.l.add(b);
                        list.add(b);
                        return;
                    }
                    videoFileItem2.setDeletPath(videoFileItem2.getPath());
                }
            }
            if (list == null || b.this.l == null) {
                return;
            }
            b.this.l.add(videoFileItem2);
            list.add(videoFileItem2);
        }

        @Override // com.xiaoyi.camera.b.b
        public String c() {
            return this.g;
        }

        @Override // java.lang.Runnable
        public void run() {
            a(this.g, this.h);
            b.this.p();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class e extends Thread {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ b f4556a;
        private boolean b;

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            this.f4556a.s = true;
            while (!this.b) {
                if (!this.f4556a.s || this.f4556a.n.size() <= 0) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(150L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    this.f4556a.f = (VideoFileItem) this.f4556a.n.poll();
                    if (this.f4556a.f.getTvDudration().getTag().equals(this.f4556a.f.getPath())) {
                        String path = this.f4556a.f.getPath();
                        if (!this.f4556a.f.isHighDefinition() && this.f4556a.f.getHDVideoFileItem() != null) {
                            path = this.f4556a.f.getHDVideoFileItem().getPath();
                        }
                        com.xiaoyi.camera.g.a().d("", path, this.f4556a);
                        this.f4556a.s = false;
                    }
                }
            }
        }
    }

    private b() {
        g.a("debug_file", "-------init---------", new Object[0]);
        m();
    }

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            if (c == null) {
                c = new b();
            }
            bVar = c;
        }
        return bVar;
    }

    public static String a(long j) {
        if (j <= 1024) {
            return j > 0 ? j + "B" : "0B";
        }
        if (j / 1024 > 1024) {
            return new BigDecimal((j / 1024.0d) / 1024.0d).setScale(1, 4).toString() + "MB";
        }
        return (j / 1024) + "KB";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<FileItem> list) {
        if (this.l != null && !this.l.isEmpty()) {
            Collections.sort(this.l, new Comparator<VideoFileItem>() { // from class: com.xiaomi.xy.sportscamera.camera.b.1
                @Override // java.util.Comparator
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public int compare(VideoFileItem videoFileItem, VideoFileItem videoFileItem2) {
                    if (videoFileItem == null) {
                        return 1;
                    }
                    if (videoFileItem2 == null) {
                        return -1;
                    }
                    if (videoFileItem.getDate() == null) {
                        return 1;
                    }
                    if (videoFileItem2.getDate() == null) {
                        return -1;
                    }
                    int compareTo = videoFileItem2.getDate().compareTo(videoFileItem.getDate());
                    return compareTo == 0 ? videoFileItem2.getName().compareTo(videoFileItem.getName()) : compareTo;
                }
            });
        }
        if (this.m != null && !this.m.isEmpty()) {
            Collections.sort(this.m, new Comparator<PhotoFileItem>() { // from class: com.xiaomi.xy.sportscamera.camera.b.2
                @Override // java.util.Comparator
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public int compare(PhotoFileItem photoFileItem, PhotoFileItem photoFileItem2) {
                    if (photoFileItem == null) {
                        return 1;
                    }
                    if (photoFileItem2 == null) {
                        return -1;
                    }
                    if (photoFileItem.getDate() == null) {
                        return 1;
                    }
                    if (photoFileItem2.getDate() == null) {
                        return -1;
                    }
                    int compareTo = photoFileItem2.getDate().compareTo(photoFileItem.getDate());
                    return compareTo == 0 ? photoFileItem2.getName().compareTo(photoFileItem.getName()) : compareTo;
                }
            });
        }
        if (list == null || list.isEmpty()) {
            return;
        }
        Collections.sort(list, new Comparator<FileItem>() { // from class: com.xiaomi.xy.sportscamera.camera.b.3
            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(FileItem fileItem, FileItem fileItem2) {
                if (fileItem == null) {
                    return 1;
                }
                if (fileItem2 == null) {
                    return -1;
                }
                if (fileItem.getDate() == null) {
                    return 1;
                }
                if (fileItem2.getDate() == null) {
                    return -1;
                }
                int compareTo = fileItem2.getDate().compareTo(fileItem.getDate());
                return compareTo == 0 ? fileItem2.getName().compareTo(fileItem.getName()) : compareTo;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(boolean z) {
        Log.d("debug_action", "setActionComplete: state=" + z);
        this.r = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, FileItem fileItem) {
        int i = 0;
        if (!z) {
            while (i < com.xiaoyi.camera.b.a.D().size()) {
                com.xiaoyi.camera.b.a.D().get(i).f(fileItem);
                i++;
            }
            return;
        }
        if (com.xiaomi.xy.sportscamera.camera.a.a().h(fileItem)) {
            com.xiaomi.xy.sportscamera.camera.a.a().j(fileItem);
        }
        if ((fileItem instanceof PhotoFileItem) && this.f4544a != null) {
            this.m.remove(fileItem);
            g.a("Start delete complete:" + fileItem.getName(), new Object[0]);
            int i2 = 0;
            while (true) {
                if (i2 >= this.f4544a.size()) {
                    break;
                }
                if (fileItem.getName().equals(this.f4544a.get(i2).getName())) {
                    this.f4544a.remove(fileItem);
                    if (CameraMediaType.isMultiPhoto(fileItem.mediaType)) {
                        ArrayList<PhotoFileItem> a2 = a(fileItem.getNameType());
                        if (a2.size() > 0) {
                            fileItem.needRefresh = false;
                            this.f4544a.add(i2, a2.get(0));
                        }
                    }
                } else {
                    i2++;
                }
            }
            g.a("End delete complete:" + fileItem.getName(), new Object[0]);
        } else if (fileItem instanceof VideoFileItem) {
            if (this.f4544a != null) {
                this.f4544a.remove(fileItem);
                this.l.remove(fileItem);
            }
            VideoFileItem videoFileItem = (VideoFileItem) fileItem;
            if (videoFileItem.getHDVideoFileItem() != null && com.xiaomi.xy.sportscamera.camera.a.a().h(videoFileItem.getHDVideoFileItem())) {
                com.xiaomi.xy.sportscamera.camera.a.a().j(fileItem);
            }
        }
        while (i < com.xiaoyi.camera.b.a.D().size()) {
            com.xiaoyi.camera.b.a.D().get(i).e(fileItem);
            i++;
        }
    }

    private void a(boolean z, VideoFileItem videoFileItem) {
        if (!z) {
            return;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= com.xiaoyi.camera.b.a.D().size()) {
                return;
            }
            com.xiaoyi.camera.b.a.D().get(i2).a(videoFileItem);
            i = i2 + 1;
        }
    }

    static /* synthetic */ int j(b bVar) {
        int i = bVar.p;
        bVar.p = i + 1;
        return i;
    }

    static /* synthetic */ int m(b bVar) {
        int i = bVar.p;
        bVar.p = i - 1;
        return i;
    }

    private void m() {
        this.e = null;
        this.f = null;
        this.o = new LinkedBlockingQueue<>();
        this.r = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean n() {
        return this.r;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        if (this.h == null || !this.h.isAlive()) {
            this.h = new C0191b();
            this.h.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void p() {
        if (!this.t) {
            a(true);
            if (this.p == 0) {
                a(this.f4544a);
                this.q = true;
            }
            if ((this.f4544a != null && !this.f4544a.isEmpty()) || this.o == null || this.o.size() == 0) {
                for (int i = 0; i < com.xiaoyi.camera.b.a.D().size(); i++) {
                    com.xiaoyi.camera.b.a.D().get(i).a();
                }
            }
            de.greenrobot.event.c.a().c(new com.xiaoyi.camera.a.b());
            g.a("debug_event", getClass() + " post MediaInitEvent", new Object[0]);
        }
    }

    public ArrayList<PhotoFileItem> a(String str) {
        ArrayList<PhotoFileItem> arrayList = new ArrayList<>();
        if (this.m != null && this.m.size() > 0) {
            int size = this.m.size();
            for (int i = 0; i < size; i++) {
                if (this.m.get(i).getNameType().equals(str)) {
                    arrayList.add(this.m.get(i));
                }
            }
        }
        return arrayList;
    }

    @Override // com.xiaoyi.camera.c.a
    public void a(com.xiaoyi.camera.d dVar, JSONObject jSONObject) {
        switch (dVar.a()) {
            case 1026:
                this.s = true;
                this.f.setDuration(jSONObject.optInt(FFmpegMediaMetadataRetriever.METADATA_KEY_DURATION));
                this.f.setAspectRatio(jSONObject.optString("resolution"));
                a(true, this.f);
                return;
            case 1281:
                this.k.post(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.b.4
                    @Override // java.lang.Runnable
                    public void run() {
                        FileItem a2 = b.this.e.a();
                        b.this.a(true, a2);
                        g.a("Receive del command:" + (a2 != null ? a2.getName() : ""), new Object[0]);
                        b.this.a(true);
                    }
                });
                return;
            case 1282:
                this.e.a(jSONObject);
                if (this.j.isShutdown()) {
                    return;
                }
                this.j.execute(this.e);
                return;
            case 1283:
                com.xiaoyi.camera.g.a().h(this);
                return;
            case 1285:
            case 1287:
            default:
                return;
        }
    }

    public void a(FileItem fileItem) {
        if (this.e != null && this.e.a() != null && this.e.a().equals(fileItem) && this.e.b().equals(FileItem.Action.DOWNLOAD)) {
            fileItem.setDownLoadingCancled(true);
        }
        a aVar = new a(fileItem);
        if (this.o.isEmpty() || !this.o.contains(aVar)) {
            this.o.add(aVar);
        }
        o();
    }

    public void b() {
        this.t = false;
        this.q = false;
        a(true);
        if (this.l == null) {
            this.l = Collections.synchronizedList(new ArrayList());
        } else {
            this.l.clear();
        }
        if (this.m == null) {
            this.m = Collections.synchronizedList(new ArrayList());
        } else {
            this.m.clear();
        }
        if (this.f4544a == null) {
            this.f4544a = Collections.synchronizedList(new ArrayList());
        } else {
            this.f4544a.clear();
        }
        if (this.u == null) {
            this.u = new ArrayList();
        } else {
            this.u.clear();
        }
        this.p = 0;
        g.a("debug_file", "add InitDirectoryTask in the working Queue.", new Object[0]);
        c cVar = new c("/tmp/fuse_d/DCIM/");
        cVar.a(0, 1000);
        this.o.add(cVar);
        o();
    }

    @Override // com.xiaoyi.camera.c.a
    public void b(com.xiaoyi.camera.d dVar, JSONObject jSONObject) {
        g.a("debug_file", "onReceiveErrorMessage, message: " + dVar.a(), new Object[0]);
        switch (dVar.a()) {
            case 1026:
                this.s = true;
                return;
            case 1281:
                this.k.post(new Runnable() { // from class: com.xiaomi.xy.sportscamera.camera.b.5
                    @Override // java.lang.Runnable
                    public void run() {
                        b.this.a(false, b.this.e.a());
                        g.a("Error del command:" + b.this.e.a().getName(), new Object[0]);
                        b.this.a(true);
                    }
                });
                return;
            default:
                return;
        }
    }

    public void c() {
        if (this.o != null) {
            this.o.clear();
        }
    }

    public boolean d() {
        return this.q;
    }

    public void e() {
        if (this.o != null) {
            this.o.clear();
        }
        if (this.n != null) {
            this.n.clear();
        }
        if (this.h != null && this.h.isAlive()) {
            this.h.b = true;
            this.h = null;
        }
        if (this.i != null && this.i.isAlive()) {
            this.i.b = true;
            this.i = null;
        }
        if (this.m != null) {
            this.m.clear();
            this.m = null;
        }
        if (this.u != null) {
            this.u.clear();
            this.u = null;
        }
        if (this.l != null) {
            this.l.clear();
            this.l = null;
        }
        if (this.f4544a != null) {
            this.f4544a.clear();
            this.f4544a = null;
        }
        this.q = false;
    }

    public void f() {
        if (this.o != null) {
            this.o.clear();
        }
        if (this.n != null) {
            this.n.clear();
        }
        if (this.h != null && this.h.isAlive()) {
            this.h.b = true;
            this.h = null;
        }
        if (this.i != null && this.i.isAlive()) {
            this.i.b = true;
            this.i = null;
        }
        if (this.m != null) {
            this.m.clear();
            this.m = null;
        }
        if (this.u != null) {
            this.u.clear();
        }
        if (this.l != null) {
            this.l.clear();
            this.l = null;
        }
        if (this.f4544a != null) {
            this.f4544a.clear();
            this.f4544a = null;
        }
        this.q = false;
        a(true);
        this.t = true;
        de.greenrobot.event.c.a().c(new x());
    }

    public void g() {
        com.xiaoyi.camera.g.a().q(this);
    }

    public List<FileItem> h() {
        ArrayList arrayList = new ArrayList();
        if (this.f4544a != null && !this.f4544a.isEmpty()) {
            arrayList.addAll(this.f4544a);
        }
        return arrayList;
    }

    public List<FileItem> i() {
        ArrayList arrayList = new ArrayList();
        if (this.f4544a != null && !this.f4544a.isEmpty()) {
            for (FileItem fileItem : this.f4544a) {
                if (fileItem instanceof PhotoFileItem) {
                    arrayList.add(fileItem);
                }
            }
        }
        return arrayList;
    }

    public List<FileItem> j() {
        ArrayList arrayList = new ArrayList();
        if (this.l != null && !this.l.isEmpty()) {
            arrayList.addAll(this.l);
        }
        return arrayList;
    }

    public int k() {
        if (this.f4544a == null || this.f4544a.size() <= 0) {
            return 0;
        }
        return this.f4544a.size();
    }
}
