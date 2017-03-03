package com.kding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.kding.api.QiGuoCallBack;
import com.kding.qxzapi.QiGuoImpl;
import com.kding.qxzapi.QiGuoInfo;

public class MainActivity
        extends Activity {
    private EditText inputMoney;
    private QiGuoImpl qiGuo;

    private void showLogin() {
        QiGuoInfo.INSTANCE.setQiGuoCallBack(new QiGuoCallBack() {
            public void onCancel() {
                Log.e("login ", "登陆取消");
            }

            public void onFailure(String paramAnonymousString) {
                Log.e("login ", paramAnonymousString);
            }

            public void onSuccess() {
                String str = QiGuoInfo.INSTANCE.getUid();
                Log.e("login ", " login  suc   userId = " + str);
            }
        });
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void showPaySelection() {
        QiGuoInfo.INSTANCE.setQiGuoCallBack(new QiGuoCallBack() {
            public void onCancel() {
                Log.e("pay  ", "支付取消");
            }

            public void onFailure(String paramAnonymousString) {
                Log.e("pay  ", paramAnonymousString);
            }

            public void onSuccess() {
                Log.e("pay  ", " pay  suc");
            }
        });
        String str = ((EditText) findViewById(R.id.input_money)).getText().toString();
        if ((str == null) || (str.equals(""))) {
            str = "1";
        }
        Intent localIntent = new Intent(this, PayCenterActivity.class);
        localIntent.putExtra("name", "元宝");
        localIntent.putExtra("price", str);
        localIntent.putExtra("extra", System.currentTimeMillis() + "");
        startActivity(localIntent);
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_main);
        this.qiGuo = new QiGuoImpl();
        findViewById(R.id.init).setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                MainActivity.this.qiGuo.init(MainActivity.this, "20160725-test");
            }
        });
        findViewById(R.id.show_login).setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                MainActivity.this.showLogin();
            }
        });
        findViewById(R.id.show_pay).setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                MainActivity.this.showPaySelection();
            }
        });
        findViewById(R.id.show_float_view).setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                MainActivity.this.qiGuo.showFloatView();
            }
        });
        findViewById(R.id.hide_float_view).setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                MainActivity.this.qiGuo.hideFloatView();
            }
        });
        findViewById(R.id.start_home).setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
        });
    }

    protected void onPause() {
        super.onPause();
        this.qiGuo.hideFloatView();
    }

    protected void onResume() {
        super.onResume();
        this.qiGuo.showFloatView();
    }

    protected void onStop() {
        super.onStop();
    }
}