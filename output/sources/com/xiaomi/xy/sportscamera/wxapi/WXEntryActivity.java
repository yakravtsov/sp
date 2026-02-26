package com.xiaomi.xy.sportscamera.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.ants360.z13.activity.BaseActivity;
import com.ants360.z13.c.ak;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.xiaomi.xy.sportscamera.R;
import com.yiaction.common.util.g;
import de.greenrobot.event.c;

/* loaded from: classes3.dex */
public class WXEntryActivity extends BaseActivity {
    private IWXAPI b;
    private IWXAPIEventHandler c = new IWXAPIEventHandler() { // from class: com.xiaomi.xy.sportscamera.wxapi.WXEntryActivity.1
        @Override // com.tencent.mm.sdk.openapi.IWXAPIEventHandler
        public void onReq(BaseReq baseReq) {
            Log.d("weixin", "onReq:" + baseReq);
        }

        @Override // com.tencent.mm.sdk.openapi.IWXAPIEventHandler
        public void onResp(BaseResp baseResp) {
            Log.d("weixin", "onResp:" + baseResp);
            switch (baseResp.errCode) {
                case -3:
                    if (baseResp instanceof SendAuth.Resp) {
                        c.a().c(new ak(((SendAuth.Resp) baseResp).code));
                        break;
                    }
                    break;
                case -2:
                    if (baseResp instanceof SendAuth.Resp) {
                        c.a().c(new ak(((SendAuth.Resp) baseResp).code));
                        break;
                    }
                    break;
                case 0:
                    if (baseResp instanceof SendAuth.Resp) {
                        c.a().c(new ak(((SendAuth.Resp) baseResp).code));
                        break;
                    }
                    break;
            }
            WXEntryActivity.this.finish();
        }
    };

    private void f() {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "xiaoyi_wx_login";
        this.b.sendReq(req);
        g.a("WXEntryActivity", "start login wechat", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityGingerbread, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.b == null) {
            this.b = WXAPIFactory.createWXAPI(this, "wxb6411cbea5c79269", false);
        }
        if (!this.b.isWXAppInstalled()) {
            a(R.string.wechat_client_inavailable);
            finish();
            return;
        }
        this.b.handleIntent(getIntent(), this.c);
        this.b.registerApp("wxb6411cbea5c79269");
        if (getIntent().getBooleanExtra("is_login", false)) {
            f();
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.b.unregisterApp();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.b.handleIntent(intent, this.c);
    }
}
