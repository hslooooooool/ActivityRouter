package com.github.mzule.activityrouter;

import com.github.mzule.activityrouter.router.Routers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by CaoDongping on 4/7/16.
 */
public class LaunchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        ViewGroup container = (ViewGroup) findViewById(R.id.container);
        for (int i = 0; i < container.getChildCount(); i++) {
            final View view = container.getChildAt(i);
            if (view instanceof TextView) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*
                        // 第三方app通过url打开本app的activity时，通过ACTION_VIEW的方式
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(((TextView) view).getText().toString()));
                        startActivity(intent);
                        */
                        // app内打开页面可以使用Routers.open(Context, Uri)
                        // Routers.open(LaunchActivity.this, Uri.parse(((TextView) view).getText().toString()), ((RouterCallbackProvider) getApplication()).provideRouterCallback());
                        Routers.openForResult(LaunchActivity.this, ((TextView) view).getText().toString(), Constant.REQUEST_CODE_DEMO);
                    }
                });
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == Constant.REQUEST_CODE_DEMO) {
            String msg;
            if (data == null) {
                msg = "success";
            } else {
                msg = data.getStringExtra("msg");
                msg = TextUtils.isEmpty(msg) ? "success" : msg;
            }
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
