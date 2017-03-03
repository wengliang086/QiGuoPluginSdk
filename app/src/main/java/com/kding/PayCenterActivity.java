package com.kding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.kding.utils.PayConfirmHelper;

public class PayCenterActivity
  extends Activity
  implements PayConfirmHelper
{
  public static final String EXTRA = "extra";
  public static final String QXZ_ORDER_NAME = "name";
  public static final String QXZ_ORDER_PRICE = "price";
  private PayCenterFragment payCenterFragment;
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    this.payCenterFragment.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onCancel(int paramInt1, int paramInt2)
  {
    this.payCenterFragment.coinConfirm(paramInt2, false);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903045);
    Intent localIntent = getIntent();
    this.payCenterFragment = PayCenterFragment.newInstance(localIntent.getStringExtra("name"), localIntent.getStringExtra("price"), localIntent.getStringExtra("extra"));
    this.payCenterFragment.show(getFragmentManager(), "");
  }
  
  public void onSuc(int paramInt1, int paramInt2)
  {
    this.payCenterFragment.coinConfirm(paramInt2, true);
  }
}


/* Location:              C:\Users\Administrator\Desktop\app-debug\assets\app\classes_dex2jar.jar!\com\kding\PayCenterActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */