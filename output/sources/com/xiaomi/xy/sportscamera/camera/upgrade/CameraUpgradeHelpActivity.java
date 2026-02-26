package com.xiaomi.xy.sportscamera.camera.upgrade;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.ants360.z13.activity.BaseActivity;
import com.xiaomi.xy.sportscamera.R;

/* loaded from: classes3.dex */
public class CameraUpgradeHelpActivity extends BaseActivity implements View.OnClickListener {
    Button b;
    Button c;

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.upgradeonemore /* 2131755752 */:
                startActivity(new Intent(this, (Class<?>) CameraUpgradeActivity.class));
                finish();
                return;
            case R.id.ok /* 2131755753 */:
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
        setContentView(R.layout.camera_upgrade_help_activity);
        this.b = (Button) findViewById(R.id.upgradeonemore);
        this.b.setOnClickListener(this);
        this.c = (Button) findViewById(R.id.ok);
        this.c.setOnClickListener(this);
    }
}
