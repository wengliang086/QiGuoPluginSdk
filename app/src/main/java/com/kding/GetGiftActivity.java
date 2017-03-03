package com.kding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class GetGiftActivity
  extends Activity
{
  private static final String GRAB_KEY = "GRAB_KEY";
  private GetGiftFragment getGiftFragment;
  private String mGrabKey;
  
  public static Intent getIntent(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent(paramContext, GetGiftActivity.class);
    localIntent.putExtra("GRAB_KEY", paramString);
    return localIntent;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903045);
    if (this.getGiftFragment == null) {
      this.getGiftFragment = new GetGiftFragment();
    }
    this.mGrabKey = getIntent().getStringExtra("GRAB_KEY");
    this.getGiftFragment.setGrabKey(this.mGrabKey);
    this.getGiftFragment.show(getFragmentManager(), "");
  }
}


/* Location:              C:\Users\Administrator\Desktop\app-debug\assets\app\classes_dex2jar.jar!\com\kding\GetGiftActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */