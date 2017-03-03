//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Message;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager.LayoutParams;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.kding.net.App;
import com.kding.qxzapi.QiGuoInfo;
import java.util.Timer;
import java.util.TimerTask;

public class FloatView extends FrameLayout implements OnTouchListener {
  private final int HANDLER_TYPE_CANCEL_ANIM = 101;
  private final int HANDLER_TYPE_HIDE_LOGO = 100;
  private boolean mCanHide;
  private Context mContext;
  private boolean mDraging;
  private FrameLayout mFlFloatLogo;
  private boolean mIsRight;
  private ImageView mIvFloatLoader;
  private ImageView mIvFloatLogo;
  private int mScreenHeight;
  private int mScreenWidth;
  private boolean mShowLoader = true;
  private Timer mTimer;
  final Handler mTimerHandler = new Handler() {
    public void handleMessage(Message var1) {
      if(var1.what == 100) {
        if(FloatView.this.mCanHide) {
          FloatView.this.mCanHide = false;
          if(FloatView.this.mIsRight) {
            FloatView.this.mIvFloatLogo.setImageResource(2130837532);
          } else {
            FloatView.this.mIvFloatLogo.setImageResource(2130837531);
          }

//          FloatView.this.mWmParams.alpha = 0.7F;
          FloatView.this.mWindowManager.updateViewLayout(FloatView.this, FloatView.this.mWmParams);
          FloatView.this.refreshFloatMenu(FloatView.this.mIsRight);
        }
      } else if(var1.what == 101) {
        FloatView.this.mIvFloatLoader.clearAnimation();
        FloatView.this.mIvFloatLoader.setVisibility(8);
        FloatView.this.mShowLoader = false;
      }

      super.handleMessage(var1);
    }
  };
  private TimerTask mTimerTask;
  private float mTouchStartX;
  private float mTouchStartY;
  private WindowManager mWindowManager;
  private LayoutParams mWmParams;
  private ImageView newPoint;

  public FloatView(Context var1) {
    super(var1);
    this.init(var1);
  }

  private View createView(Context var1) {
    View var4 = LayoutInflater.from(var1).inflate(2130903076, (ViewGroup)null);
    this.mFlFloatLogo = (FrameLayout)var4.findViewById(2131230830);
    this.mIvFloatLogo = (ImageView)var4.findViewById(2131230831);
    this.mIvFloatLoader = (ImageView)var4.findViewById(2131230832);
    ImageView var2 = (ImageView)var4.findViewById(2131230832);
    ImageView var3 = (ImageView)var4.findViewById(2131230831);
    this.newPoint = (ImageView)var4.findViewById(2131230833);
    var3.setImageResource(2130837533);
    var2.setImageResource(2130837529);
    this.newPoint.setImageResource(2130837522);
    var4.setOnTouchListener(this);
    var4.setOnClickListener(new OnClickListener() {
      public void onClick(View var1) {
        FloatView.this.mIvFloatLogo.setImageResource(2130837533);
        QiGuoInfo.INSTANCE.getOnStartActivityFromPlugin().startActivity(new Intent(), "com.kding.HomeActivity");
        FloatView.this.newPoint.setVisibility(4);
        FloatView.this.mCanHide = true;
      }
    });
    var4.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
    return var4;
  }

  private void init(Context var1) {
    this.mContext = var1;
    this.mWindowManager = (WindowManager)this.mContext.getSystemService("window");
    DisplayMetrics var2 = new DisplayMetrics();
    this.mWindowManager.getDefaultDisplay().getMetrics(var2);
    this.mScreenWidth = var2.widthPixels;
    this.mScreenHeight = var2.heightPixels;
//    this.mWmParams = new LayoutParams();
//    if(VERSION.SDK_INT >= 19) {
//      this.mWmParams.type = 2005;
//    } else {
//      this.mWmParams.type = 2002;
//    }
//
//    this.mWmParams.format = 1;
//    this.mWmParams.flags = 8;
    this.mWmParams.gravity = 51;
    this.mScreenHeight = this.mWindowManager.getDefaultDisplay().getHeight();
//    this.mWmParams.x = 0;
//    this.mWmParams.y = this.mScreenHeight / 2;
    this.mWmParams.width = -2;
    this.mWmParams.height = -2;
    this.addView(this.createView(this.mContext));
    this.mWindowManager.addView(this, this.mWmParams);
    this.mTimer = new Timer();
    this.hide();
  }

  private void refreshFloatMenu(boolean var1) {
    int var2 = (int)TypedValue.applyDimension(1, 55.0F, this.mContext.getResources().getDisplayMetrics());
    android.widget.FrameLayout.LayoutParams var3;
    if(var1) {
      var3 = (android.widget.FrameLayout.LayoutParams)this.mIvFloatLogo.getLayoutParams();
      var3.gravity = 5;
      this.mIvFloatLogo.setLayoutParams(var3);
      var3 = (android.widget.FrameLayout.LayoutParams)this.mFlFloatLogo.getLayoutParams();
      var3.gravity = 5;
      this.mFlFloatLogo.setLayoutParams(var3);
    } else {
      var3 = (android.widget.FrameLayout.LayoutParams)this.mIvFloatLogo.getLayoutParams();
      var3.setMargins(0, 0, 0, 0);
      var3.gravity = 3;
      this.mIvFloatLogo.setLayoutParams(var3);
      var3 = (android.widget.FrameLayout.LayoutParams)this.mFlFloatLogo.getLayoutParams();
      var3.gravity = 3;
      this.mFlFloatLogo.setLayoutParams(var3);
    }
  }

  private void removeFloatView() {
    try {
      this.mWindowManager.removeView(this);
    } catch (Exception var2) {
      var2.printStackTrace();
    }
  }

  private void removeTimerTask() {
    if(this.mTimerTask != null) {
      this.mTimerTask.cancel();
      this.mTimerTask = null;
    }

  }

  private void timerForHide(boolean var1) {
    this.mCanHide = var1;
    if(this.mTimerTask != null) {
      try {
        this.mTimerTask.cancel();
        this.mTimerTask = null;
      } catch (Exception var3) {
        ;
      }
    }

    this.mTimerTask = new TimerTask() {
      public void run() {
        Message var1 = FloatView.this.mTimerHandler.obtainMessage();
        var1.what = 100;
        FloatView.this.mTimerHandler.sendMessage(var1);
      }
    };
    if(this.mCanHide) {
      this.mTimer.schedule(this.mTimerTask, 6000L, 3000L);
    }

  }

  public void destroy() {
    this.hide();
    this.removeFloatView();
    this.removeTimerTask();
    if(this.mTimer != null) {
      this.mTimer.cancel();
      this.mTimer = null;
    }

    try {
      this.mTimerHandler.removeMessages(1);
    } catch (Exception var2) {
      ;
    }
  }

  public void hide() {
    this.setVisibility(8);
    Message var1 = this.mTimerHandler.obtainMessage();
    var1.what = 100;
    this.mTimerHandler.sendMessage(var1);
    this.removeTimerTask();
  }

  protected void onConfigurationChanged(Configuration var1) {
    super.onConfigurationChanged(var1);
    DisplayMetrics var4 = new DisplayMetrics();
    this.mWindowManager.getDefaultDisplay().getMetrics(var4);
    this.mScreenWidth = var4.widthPixels;
    this.mScreenHeight = var4.heightPixels;
//    int var2 = this.mWmParams.x;
//    int var3 = this.mWmParams.y;
    switch(var1.orientation) {
      case 1:
//        if(this.mIsRight) {
//          this.mWmParams.x = this.mScreenWidth;
//          this.mWmParams.y = var3;
//        } else {
//          this.mWmParams.x = var2;
//          this.mWmParams.y = var3;
//        }
        break;
      case 2:
//        if(this.mIsRight) {
//          this.mWmParams.x = this.mScreenWidth;
//          this.mWmParams.y = var3;
//        } else {
//          this.mWmParams.x = var2;
//          this.mWmParams.y = var3;
//        }
    }

    this.mWindowManager.updateViewLayout(this, this.mWmParams);
  }

  public boolean onTouch(View var1, MotionEvent var2) {
    this.removeTimerTask();
    int var5 = (int)var2.getRawX();
    int var6 = (int)var2.getRawY();
    switch(var2.getAction()) {
      case 0:
        if(!this.mIsRight) {
          this.mTouchStartX = var2.getX();
          this.mTouchStartY = var2.getY();
        } else {
          this.mTouchStartX = (float)this.mFlFloatLogo.getWidth();
          this.mTouchStartY = var2.getY();
        }

        this.mIvFloatLogo.setImageResource(2130837533);
//        this.mWmParams.alpha = 1.0F;
        this.mWindowManager.updateViewLayout(this, this.mWmParams);
        this.mDraging = false;
        return false;
      case 1:
      case 3:
//        if(this.mWmParams.x >= this.mScreenWidth / 2) {
//          this.mWmParams.x = this.mScreenWidth;
//          this.mIsRight = true;
//        } else if(this.mWmParams.x < this.mScreenWidth / 2) {
//          this.mIsRight = false;
//          this.mWmParams.x = 0;
//        }

        this.mIvFloatLogo.setImageResource(2130837533);
        this.refreshFloatMenu(this.mIsRight);
        this.timerForHide(true);
        this.mWindowManager.updateViewLayout(this, this.mWmParams);
        this.mTouchStartY = 0.0F;
        this.mTouchStartX = 0.0F;
        return false;
      case 2:
        float var3 = var2.getX();
        float var4 = var2.getY();
        if(Math.abs(this.mTouchStartX - var3) > 3.0F && Math.abs(this.mTouchStartY - var4) > 3.0F) {
          this.mDraging = true;
//          this.mWmParams.x = (int)((float)var5 - this.mTouchStartX);
//          this.mWmParams.y = (int)((float)var6 - this.mTouchStartY);
          this.mWindowManager.updateViewLayout(this, this.mWmParams);
          return false;
        }
      default:
        return false;
    }
  }

  public void show() {
    if(this.getVisibility() != 0) {
      this.setVisibility(0);
      if(this.mShowLoader) {
        this.mIvFloatLogo.setImageResource(2130837533);
//        this.mWmParams.alpha = 1.0F;
        this.mWindowManager.updateViewLayout(this, this.mWmParams);
        this.mShowLoader = false;
        RotateAnimation var2 = new RotateAnimation(0.0F, 360.0F, 1, 0.5F, 1, 0.5F);
        var2.setRepeatCount(-1);
        var2.setRepeatMode(1);
        var2.setDuration(800L);
        var2.setInterpolator(new LinearInterpolator());
        this.mIvFloatLoader.startAnimation(var2);
        final boolean var1;
        if(App.getCornerState().getLibao() == 0 && App.getCornerState().getNotice() == 0 && App.getCornerState().getQuestion() == 0) {
          this.newPoint.setVisibility(4);
          var1 = true;
        } else {
          this.newPoint.setVisibility(0);
          var1 = false;
        }

        this.mTimer.schedule(new TimerTask() {
          public void run() {
            FloatView.this.mTimerHandler.sendEmptyMessage(101);
            FloatView.this.timerForHide(var1);
          }
        }, 3000L);
      }
    }

  }
}
