package com.kding;

import android.app.Activity;
import android.os.Bundle;

public class GuideDownloadActivity
  extends Activity
{
  private GuideDownloadFragment guideDownloadFragment;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903045);
    if (this.guideDownloadFragment == null) {
      this.guideDownloadFragment = new GuideDownloadFragment();
    }
    this.guideDownloadFragment.show(getFragmentManager(), "");
  }
}


/* Location:              C:\Users\Administrator\Desktop\app-debug\assets\app\classes_dex2jar.jar!\com\kding\GuideDownloadActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */