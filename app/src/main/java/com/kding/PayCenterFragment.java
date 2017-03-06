//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.kding.api.QiGuoApi;
import com.kding.api.QiGuoCallBack;
import com.kding.base.BaseDialogFragment;
import com.kding.bean.CouponBean;
import com.kding.bean.PayResult;
import com.kding.bean.UserCoinBean;
import com.kding.net.ResponseCallBack;
import com.kding.net.SDKService;
import com.kding.pay.PayCallBack;
import com.kding.pay.QiguoPay;
import com.kding.qxzalipay.Result;
import com.kding.qxzapi.QiGuoInfo;
import com.kding.utils.AppCallUtil;
import com.kding.utils.ToastUtils;
import com.unionpay.UPPayAssistEx;
import com.unionpay.uppay.PayActivity;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PayCenterFragment extends BaseDialogFragment {
    public static final String EXTRA = "extra";
    public static final String GOOD_NAME = "good_name";
    public static final String ORDER_MONEY = "order_money";
    private static final int PAY_TYPE_ALIPAY = 0;
    private static final int PAY_TYPE_COUPONPAY = 3;
    private static final int PAY_TYPE_QXZPAY = 2;
    private static final int PAY_TYPE_UNCHECK = -1;
    private static final int PAY_TYPE_UNIONPAY = 1;
    private static final String PLATFORM_MONEY_NOT_ENOUGH = "平台币余额不足，请到七果app进行充值";
    private static final int REQUEST_COIN_CONFIRM = 3264;
    private static final int REQUEST_COUPON_CONFIRM = 52225;
    private ArrayAdapter<String> adapter;
    private View aliPayBtn;
    public boolean alipayEnable = true;
    private ImageView alipayImageView;
    QiGuoCallBack apiResult = new QiGuoCallBack() {
        public void onCancel() {
            ToastUtils.showToast(PayCenterFragment.this.mActivity, "支付取消");
            QiGuoInfo.INSTANCE.getQiGuoCallBack().onCancel();
        }

        public void onFailure(String var1) {
            ToastUtils.showToast(PayCenterFragment.this.mActivity, var1);
            QiGuoInfo.INSTANCE.getQiGuoCallBack().onFailure(var1);
        }

        public void onSuccess() {
            PayCenterFragment.this.paying = false;
            ToastUtils.showToast(PayCenterFragment.this.mActivity, "支付成功");
            QiGuoInfo.INSTANCE.getQiGuoCallBack().onSuccess();
            PayCenterFragment.this.mActivity.finish();
        }
    };
    private ImageView closeBtn;
    private Button confirmPayBtn;
    private Spinner couponsSpanner;
    ArrayList<String> couponsTextList = new ArrayList();
    private CouponBean dataBean;
    private List<CouponBean> datas;
    private TextView deductionTextView;
    private String goodName;
    private String mExtra;
    private int mUserCoin = 0;
    private String orderMoney;
    private TextView orderMoneyTextView;
    private int payType = -1;
    private boolean paying = false;
    private TextView platformMoneyTextView;
    private ProgressBar progressBar;
    private View qxzPayBtn;
    public boolean qxzPayEnable = true;
    public boolean qxzPayNeed = true;
    private TextView qxzPlatformMoney;
    private String realPay;
    private ImageView unionImageView;
    private View unionPayBtn;
    public boolean unionPayEnable = true;

    public PayCenterFragment() {
    }

    private void confirmPay(int var1) {
        String var2 = "";
        if (this.dataBean != null) {
            var2 = this.dataBean.getId();
        }

        switch (var1) {
            case 3264:
                QiguoPay.INSTANCE.startPay(this.mActivity, this.goodName, "", var2, this.orderMoney, 3, this.mExtra, new PayCallBack() {
                    public void onError(String var1) {
                        ToastUtils.showToast(PayCenterFragment.this.mActivity, var1);
                        PayCenterFragment.this.finishPay();
                    }

                    public void onFail(String var1) {
                        ToastUtils.showToast(PayCenterFragment.this.mActivity, var1);
                        PayCenterFragment.this.finishPay();
                    }

                    public void onSuc(PayResult var1) {
                        PayCenterFragment.this.apiResult.onSuccess();
                    }
                });
                return;
            case 52225:
                QiguoPay.INSTANCE.startPay(this.mActivity, this.goodName, "", var2, this.orderMoney, 4, this.mExtra, new PayCallBack() {
                    public void onError(String var1) {
                        ToastUtils.showToast(PayCenterFragment.this.mActivity, var1);
                        PayCenterFragment.this.finishPay();
                    }

                    public void onFail(String var1) {
                        ToastUtils.showToast(PayCenterFragment.this.mActivity, var1);
                        PayCenterFragment.this.finishPay();
                    }

                    public void onSuc(PayResult var1) {
                        PayCenterFragment.this.apiResult.onSuccess();
                    }
                });
                return;
            default:
        }
    }

    private void disableQxzPlatformPay() {
        this.qxzPlatformMoney.setText("不可用");
        this.qxzPayEnable = false;
        this.platformMoneyTextView.setTextColor(Color.parseColor("#999999"));
        this.qxzPlatformMoney.setTextColor(Color.parseColor("#999999"));
    }

    private void enableAliandUnionPay() {
        this.alipayEnable = true;
        this.unionPayEnable = true;
        this.alipayImageView.setImageResource(R.drawable.qxz_alipay_icon);
        this.unionImageView.setImageResource(R.drawable.qxz_union_icon);
    }

    private void getCoin() {
        SDKService.getInstance(this.mActivity).getUserCoin(new ResponseCallBack<UserCoinBean>() {
            public void onError(int var1, String var2, Throwable var3) {
                PayCenterFragment.this.disableQxzPlatformPay();
            }

            public void onSuccess(int var1, UserCoinBean var2) {
                PayCenterFragment.this.mUserCoin = var2.getUsercoin();
                if (var2.getEnable()) {
                    PayCenterFragment.this.qxzPlatformMoney.setText("" + PayCenterFragment.this.mUserCoin);
                } else {
                    PayCenterFragment.this.disableQxzPlatformPay();
                }
            }
        });
    }

    private void needQxzPlatformPay() {
        if (this.qxzPayEnable) {
            this.qxzPayNeed = true;
            this.platformMoneyTextView.setTextColor(Color.parseColor("#FF5757"));
            this.qxzPlatformMoney.setTextColor(Color.parseColor("#FF5757"));
        }
    }

    public static PayCenterFragment newInstance(String var0, String var1, String var2) {
        Bundle var3 = new Bundle();
        var3.putString("good_name", var0);
        var3.putString("order_money", var1);
        var3.putString("extra", var2);
        Log.e("qiguo_pay", "goodName " + var0);
        Log.e("qiguo_pay", "orderMoney " + var1);
        Log.e("qiguo_pay", "extra " + var2);
        PayCenterFragment var4 = new PayCenterFragment();
        var4.setArguments(var3);
        return var4;
    }

    private void noCoupons() {
        this.realPay = this.orderMoney;
        this.couponsTextList.add("无可用代金券");
        this.adapter.notifyDataSetChanged();
    }

    private void noNeedQxzPlatformPay() {
        if (this.qxzPayEnable) {
            this.qxzPayNeed = false;
            this.platformMoneyTextView.setTextColor(Color.parseColor("#999999"));
            this.qxzPlatformMoney.setTextColor(Color.parseColor("#999999"));
        }
    }

    private void requestCouponsAndPlatformMoney() {
        SDKService.getInstance(this.mActivity).getUsefulCouponList(new ResponseCallBack<List<CouponBean>>() {
            public void onError(int var1, String var2, Throwable var3) {
                PayCenterFragment.this.progressBar.setVisibility(8);
                PayCenterFragment.this.noCoupons();
            }

            public void onSuccess(int var1, List<CouponBean> var2) {
                PayCenterFragment.this.progressBar.setVisibility(8);
                PayCenterFragment.this.datas = var2;
                if (PayCenterFragment.this.datas != null && PayCenterFragment.this.datas.size() != 0) {
                    Iterator var4 = PayCenterFragment.this.datas.iterator();

                    while (var4.hasNext()) {
                        CouponBean var3 = (CouponBean) var4.next();
                        PayCenterFragment.this.couponsTextList.add(var3.getName() + var3.getPrice() + "元");
                    }

                    PayCenterFragment.this.couponsTextList.add("不使用代金券");
                    PayCenterFragment.this.adapter.notifyDataSetChanged();
                    PayCenterFragment.this.couponsSpanner.setSelection(PayCenterFragment.this.couponsTextList.size() - 1);
                    PayCenterFragment.this.couponsSpanner.setOnItemSelectedListener(new OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> var1, View var2, int var3, long var4) {
                            if (var3 == PayCenterFragment.this.couponsTextList.size() - 1) {
                                PayCenterFragment.this.deductionTextView.setText("-￥0");
                                PayCenterFragment.this.realPay = PayCenterFragment.this.orderMoney;
                                PayCenterFragment.this.dataBean = null;
                                if (PayCenterFragment.this.payType == 3) {
                                    PayCenterFragment.this.payType = -1;
                                }

                                PayCenterFragment.this.enableAliandUnionPay();
                                PayCenterFragment.this.needQxzPlatformPay();
                                PayCenterFragment.this.setConfirmBtnText();
                            } else {
                                PayCenterFragment.this.dataBean = (CouponBean) PayCenterFragment.this.datas.get(var3);
                                BigDecimal var8 = new BigDecimal(PayCenterFragment.this.orderMoney);
                                BigDecimal var9 = new BigDecimal(PayCenterFragment.this.dataBean.getPrice());
                                double var6 = var8.subtract(var9).doubleValue();
                                PayCenterFragment.this.deductionTextView.setText("-￥" + var9);
                                PayCenterFragment.this.realPay = String.valueOf(var6);
                                if (var6 <= 0.0D) {
                                    PayCenterFragment.this.realPay = String.valueOf(0);
                                    PayCenterFragment.this.disableAliandUnionPay();
                                    PayCenterFragment.this.noNeedQxzPlatformPay();
                                    PayCenterFragment.this.payType = 3;
                                    PayCenterFragment.this.switchPayBtnBackground();
                                    if (var6 < 0.0D) {
                                        ToastUtils.showToast(PayCenterFragment.this.mActivity, "本次交易使用此券，将浪费" + Math.abs(var6) + "元面额");
                                    }
                                } else {
                                    if (PayCenterFragment.this.payType == 3) {
                                        PayCenterFragment.this.payType = -1;
                                    }

                                    PayCenterFragment.this.enableAliandUnionPay();
                                    PayCenterFragment.this.needQxzPlatformPay();
                                }

                                PayCenterFragment.this.setConfirmBtnText();
                            }
                        }

                        public void onNothingSelected(AdapterView<?> var1) {
                        }
                    });
                } else {
                    PayCenterFragment.this.noCoupons();
                }
            }
        });
        SDKService.getInstance(this.mActivity).getUserCoin(new ResponseCallBack<UserCoinBean>() {
            public void onError(int var1, String var2, Throwable var3) {
                PayCenterFragment.this.disableQxzPlatformPay();
            }

            public void onSuccess(int var1, UserCoinBean var2) {
                PayCenterFragment.this.mUserCoin = var2.getUsercoin();
                if (var2.getEnable()) {
                    PayCenterFragment.this.qxzPlatformMoney.setText("" + PayCenterFragment.this.mUserCoin);
                } else {
                    PayCenterFragment.this.disableQxzPlatformPay();
                }
            }
        });
    }

    private void setConfirmBtnText() {
        if (this.payType == 2) {
            int var1 = (int) Math.ceil(Double.valueOf(this.realPay).doubleValue() * 10.0D);
            this.confirmPayBtn.setText("需要支付" + var1 + "平台币，请点击支付");
        } else {
            this.confirmPayBtn.setText("需要支付" + this.realPay + "元，请点击支付");
        }
    }

    private void startPay() {
        if (this.payType == -1) {
            ToastUtils.showToast(this.mActivity, "请先选择支付方式");
        } else {
            String var2 = "";
            if (this.dataBean != null) {
                var2 = this.dataBean.getId();
            }

            if (this.paying) {
                ToastUtils.showToast(this.mActivity, "正在启动支付,请稍等...");
            } else {
                this.paying = true;
                this.confirmPayBtn.setText("支付启动中...");
                PayConfirmFragment var3;
                switch (this.payType) {
                    case 0:
                        QiguoPay.INSTANCE.startPay(this.mActivity, this.goodName, "", var2, this.orderMoney, 1, this.mExtra, new PayCallBack() {
                            public void onError(String var1) {
                                ToastUtils.showToast(PayCenterFragment.this.mActivity, var1);
                                PayCenterFragment.this.finishPay();
                            }

                            public void onFail(String var1) {
                                ToastUtils.showToast(PayCenterFragment.this.mActivity, var1);
                                PayCenterFragment.this.finishPay();
                            }

                            public void onSuc(PayResult var1) {
                                try {
                                    final String var3 = URLDecoder.decode(var1.getOrder_info(), "UTF-8");
                                    Log.e("alipay", var3);
                                    (new Thread(new Runnable() {
                                        public void run() {
                                            String var1 = (new Result((new PayTask(PayCenterFragment.this.mActivity)).pay(var3))).resultStatus;
                                            if (TextUtils.equals(var1, "9000")) {
                                                PayCenterFragment.this.mActivity.runOnUiThread(new Runnable() {
                                                    public void run() {
                                                        PayCenterFragment.this.apiResult.onSuccess();
                                                    }
                                                });
                                            } else if (TextUtils.equals(var1, "8000")) {
                                                PayCenterFragment.this.mActivity.runOnUiThread(new Runnable() {
                                                    public void run() {
                                                        ToastUtils.showToast(PayCenterFragment.this.mActivity, "等待支付结果确认");
                                                        PayCenterFragment.this.finishPay();
                                                    }
                                                });
                                            } else {
                                                PayCenterFragment.this.mActivity.runOnUiThread(new Runnable() {
                                                    public void run() {
                                                        ToastUtils.showToast(PayCenterFragment.this.mActivity, "支付失败");
                                                        PayCenterFragment.this.finishPay();
                                                    }
                                                });
                                            }
                                        }
                                    })).start();
                                } catch (UnsupportedEncodingException var2) {
                                    var2.printStackTrace();
                                    ToastUtils.showToast(PayCenterFragment.this.mActivity, "系统异常");
                                }
                            }
                        });
                        return;
                    case 1:
                        QiguoPay.INSTANCE.startPay(this.mActivity, this.goodName, "", var2, this.orderMoney, 2, this.mExtra, new PayCallBack() {
                            public void onError(String var1) {
                                ToastUtils.showToast(PayCenterFragment.this.mActivity, var1);
                                PayCenterFragment.this.finishPay();
                            }

                            public void onFail(String var1) {
                                ToastUtils.showToast(PayCenterFragment.this.mActivity, var1);
                                PayCenterFragment.this.finishPay();
                            }

                            public void onSuc(final PayResult var1) {
                                Log.e("union_pay", "" + var1.getOrder_info());
                                (new Thread(new Runnable() {
                                    public void run() {
                                        UPPayAssistEx.startPayByJAR(PayCenterFragment.this.mActivity, PayActivity.class, (String) null, (String) null, var1.getOrder_info(), "00");
                                    }
                                })).start();
                            }
                        });
                        return;
                    case 2:
                        if ("平台币余额不足，请到七果app进行充值".equals(this.confirmPayBtn.getText())) {
                            AppCallUtil.goToAppRecharge(this.mActivity, QiGuoApi.INSTANCE.getUserId());
                            return;
                        }

                        int var1 = (int) Math.ceil((double) (Float.valueOf(this.realPay).floatValue() * 10.0F));
                        var3 = new PayConfirmFragment();
                        var3.setCoinCost(var1, 3264);
                        var3.show(this.getFragmentManager(), "");
                        return;
                    case 3:
                        var3 = new PayConfirmFragment();
                        var3.setCoinCost(0, '찁');
                        var3.show(this.getFragmentManager(), "");
                        return;
                    default:
                }
            }
        }
    }

    private void switchPayBtnBackground() {
        switch (this.payType) {
            case 0:
                this.aliPayBtn.setBackgroundResource(R.drawable.sdk_dialog_payway_bg_sel);
                this.unionPayBtn.setBackgroundResource(R.drawable.sdk_dialog_payway_bg_nor);
                this.qxzPayBtn.setBackgroundResource(R.drawable.sdk_dialog_payway_bg_nor);
                this.setConfirmBtnText();
                return;
            case 1:
                this.aliPayBtn.setBackgroundResource(R.drawable.sdk_dialog_payway_bg_nor);
                this.unionPayBtn.setBackgroundResource(R.drawable.sdk_dialog_payway_bg_sel);
                this.qxzPayBtn.setBackgroundResource(R.drawable.sdk_dialog_payway_bg_nor);
                this.setConfirmBtnText();
                return;
            case 2:
                this.aliPayBtn.setBackgroundResource(R.drawable.sdk_dialog_payway_bg_nor);
                this.unionPayBtn.setBackgroundResource(R.drawable.sdk_dialog_payway_bg_nor);
                this.qxzPayBtn.setBackgroundResource(R.drawable.sdk_dialog_payway_bg_sel);
                int var1 = (int) Math.ceil((double) (Float.valueOf(this.realPay).floatValue() * 10.0F));
                if (Integer.valueOf(this.mUserCoin).intValue() <= var1) {
                    this.confirmPayBtn.setText("平台币余额不足，请到七果app进行充值");
                    return;
                }

                this.setConfirmBtnText();
                return;
            case 3:
                this.aliPayBtn.setBackgroundResource(R.drawable.sdk_dialog_payway_bg_nor);
                this.unionPayBtn.setBackgroundResource(R.drawable.sdk_dialog_payway_bg_nor);
                this.qxzPayBtn.setBackgroundResource(R.drawable.sdk_dialog_payway_bg_nor);
                this.setConfirmBtnText();
                return;
            default:
        }
    }

    private void updateView() {
        if (this.orderMoney.equals("0")) {
            this.disableAliandUnionPay();
        }

        this.orderMoneyTextView.setText("￥" + this.orderMoney);
        this.setConfirmBtnText();
    }

    public void coinConfirm(int var1, boolean var2) {
        if (var2) {
            this.confirmPay(var1);
        } else {
            ToastUtils.showToast(this.mActivity, "支付取消");
            this.finishPay();
        }
    }

    public void disableAliandUnionPay() {
        this.alipayEnable = false;
        this.unionPayEnable = false;
        this.alipayImageView.setImageResource(R.drawable.qxz_alipay_icon_fade);
        this.unionImageView.setImageResource(R.drawable.qxz_union_icon_fade);
    }

    public void finishPay() {
        this.paying = false;
        this.setConfirmBtnText();
    }

    public void initEvents() {
        this.aliPayBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                if (PayCenterFragment.this.alipayEnable) {
                    PayCenterFragment.this.payType = 0;
                    PayCenterFragment.this.switchPayBtnBackground();
                } else {
                    Toast.makeText(PayCenterFragment.this.mActivity, "支付宝支付不可用", 0).show();
                }
            }
        });
        this.unionPayBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                if (PayCenterFragment.this.unionPayEnable) {
                    PayCenterFragment.this.payType = 1;
                    PayCenterFragment.this.switchPayBtnBackground();
                } else {
                    Toast.makeText(PayCenterFragment.this.mActivity, "银联支付不可用", 0).show();
                }
            }
        });
        this.qxzPayBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                if (PayCenterFragment.this.qxzPayEnable && PayCenterFragment.this.qxzPayNeed) {
                    PayCenterFragment.this.payType = 2;
                    PayCenterFragment.this.switchPayBtnBackground();
                } else {
                    Toast.makeText(PayCenterFragment.this.mActivity, "平台币支付不可用", 0).show();
                }
            }
        });
        this.confirmPayBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                PayCenterFragment.this.startPay();
            }
        });
        this.closeBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                PayCenterFragment.this.apiResult.onCancel();
                PayCenterFragment.this.dismiss();
            }
        });
    }

    public void initViews(View var1) {
        this.closeBtn = (ImageView) var1.findViewById(R.id.close_btn);
        this.orderMoneyTextView = (TextView) var1.findViewById(R.id.tv_money);
        this.qxzPlatformMoney = (TextView) var1.findViewById(R.id.tv_coin);
        this.platformMoneyTextView = (TextView) var1.findViewById(R.id.tv_coin_top);
        this.deductionTextView = (TextView) var1.findViewById(R.id.tv_deduction);
        this.progressBar = (ProgressBar) var1.findViewById(R.id.progressbar);
        this.aliPayBtn = var1.findViewById(R.id.ll_ali_pay);
        this.unionPayBtn = var1.findViewById(R.id.ll_union_pay);
        this.qxzPayBtn = var1.findViewById(R.id.ll_coin_pay);
        this.alipayImageView = (ImageView) var1.findViewById(R.id.iv_ali_pay);
        this.unionImageView = (ImageView) var1.findViewById(R.id.iv_union_pay);
        this.confirmPayBtn = (Button) var1.findViewById(R.id.btn_pay);
        this.couponsSpanner = (Spinner) var1.findViewById(R.id.sp_coupon);
        this.adapter = new ArrayAdapter(this.mActivity, R.layout.qxz_spinner_nomal_item, this.couponsTextList);
        this.adapter.setDropDownViewResource(R.layout.qxz_spinner_dropdown_item);
        this.couponsSpanner.setAdapter(this.adapter);
    }

    public void onActivityResult(int var1, int var2, Intent var3) {
        super.onActivityResult(var1, var2, var3);
        if (var1 == '밁') {
            if (var2 == -1) {
                this.getCoin();
                this.switchPayBtnBackground();
            }

        } else if (var3 == null) {
            ToastUtils.showToast(this.mActivity, "银联支付失败");
            this.finishPay();
        } else {
            String var4 = var3.getExtras().getString("pay_result");
            if (var4.equalsIgnoreCase("success")) {
                this.apiResult.onSuccess();
            } else if (var4.equalsIgnoreCase("fail")) {
                ToastUtils.showToast(this.mActivity, "银联支付失败");
                this.finishPay();
            } else if (var4.equalsIgnoreCase("cancel")) {
                ToastUtils.showToast(this.mActivity, "银联支付取消");
                this.finishPay();
            } else {
                ToastUtils.showToast(this.mActivity, "银联支付失败");
                this.finishPay();
            }
        }
    }

    public void onCancel(DialogInterface var1) {
        super.onCancel(var1);
        this.apiResult.onCancel();
    }

    public void onCreate(Bundle var1) {
        super.onCreate(var1);
        var1 = this.getArguments();
        if (var1 != null) {
            this.goodName = var1.getString("good_name");
            this.orderMoney = var1.getString("order_money");
            this.mExtra = var1.getString("extra");
            this.realPay = "0";
        } else {
            ToastUtils.showToast(this.mActivity, "异常");
            this.dismiss();
        }
    }

    public View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
        View var4 = var1.inflate(R.layout.fragment_pay_center, var2, false);
        this.initViews(var4);
        this.initEvents();
        this.updateView();
        this.requestCouponsAndPlatformMoney();
        return var4;
    }
}
