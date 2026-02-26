package com.xiaomi.xy.sportscamera.camera.upgrade;

import android.os.Bundle;
import android.view.View;
import com.ants360.z13.activity.BaseActivity;
import com.xiaomi.xy.sportscamera.R;

/* loaded from: classes3.dex */
public class CameraUpgradeFailActivity extends BaseActivity implements View.OnClickListener {
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack /* 2131755751 */:
                finish();
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ants360.z13.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityGingerbread, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.camera_upgrade_fail_activity);
        findViewById(R.id.btnBack).setOnClickListener(this);
    }
}
