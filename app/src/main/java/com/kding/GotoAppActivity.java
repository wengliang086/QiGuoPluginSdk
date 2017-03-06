package com.kding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class GotoAppActivity
        extends Activity {
    private GotoAppFragment gotoAppFragment;

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
        this.gotoAppFragment.onActivityResult(paramInt1, paramInt2);
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.empty);
        if (this.gotoAppFragment == null) {
            this.gotoAppFragment = new GotoAppFragment();
        }
        this.gotoAppFragment.show(getFragmentManager(), "");
    }
}


/* Location:              C:\Users\Administrator\Desktop\app-debug\assets\app\classes_dex2jar.jar!\com\kding\GotoAppActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */