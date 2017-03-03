package com.kding.utils;

import com.kding.base.BaseDrawerFragment;

public abstract interface FragmentHelper
{
  public abstract void dialogDismiss();
  
  public abstract void finishFragment(BaseDrawerFragment paramBaseDrawerFragment);
  
  public abstract void startFragment(BaseDrawerFragment paramBaseDrawerFragment, int paramInt);
}