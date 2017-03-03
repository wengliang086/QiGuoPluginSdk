//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.kding.MyGiftAdapter;
import com.kding.bean.PersonalGiftBean;
import com.kding.net.ResponseCallBack;
import com.kding.net.SDKService;
import com.kding.utils.ToastUtils;
import com.kding.view.XListView;
import com.kding.view.XListView.IXListViewListener;
import java.util.ArrayList;
import java.util.List;

public class MineGiftFragment extends Fragment {
  private static final int TYPE_LOADMORE = 1;
  private static final int TYPE_REFRESH = 0;
  private boolean isMyGiftLoading = false;
  private XListView lv_my_gift;
  private MyGiftAdapter mMyGiftAdapter;
  private int mMyGiftNpi = 0;
  private List<PersonalGiftBean> personalGifts = new ArrayList();

  public MineGiftFragment() {
  }

  private void getMyGift(int var1, final int var2) {
    if(!this.isMyGiftLoading) {
      this.isMyGiftLoading = true;
      SDKService.getInstance(this.getActivity()).getPersonalGift(var1, new ResponseCallBack<List<PersonalGiftBean>>() {
        public void onError(int var1, String var2x, Throwable var3) {
          MineGiftFragment.this.isMyGiftLoading = false;
          ToastUtils.showToast(MineGiftFragment.this.getActivity(), var2x);
          if(var2 == 0) {
            MineGiftFragment.this.lv_my_gift.stopRefresh();
          } else {
            MineGiftFragment.this.lv_my_gift.stopLoadMore();
          }
        }

        public void onSuccess(int var1, List<PersonalGiftBean> var2x) {
          MineGiftFragment.this.isMyGiftLoading = false;
          MineGiftFragment.this.mMyGiftNpi = var1;
          if(var2 == 0) {
            MineGiftFragment.this.personalGifts.clear();
          }

          MineGiftFragment.this.personalGifts.addAll(var2x);
          MineGiftFragment.this.mMyGiftAdapter.setDatas(MineGiftFragment.this.personalGifts);
          if(MineGiftFragment.this.mMyGiftNpi == -1) {
            MineGiftFragment.this.lv_my_gift.setPullLoadEnable(false);
          } else {
            MineGiftFragment.this.lv_my_gift.setPullLoadEnable(true);
          }

          if(var2 == 0) {
            MineGiftFragment.this.lv_my_gift.stopRefresh();
          } else {
            MineGiftFragment.this.lv_my_gift.stopLoadMore();
          }
        }
      });
    }
  }

  public static MineGiftFragment newInstance() {
    MineGiftFragment var0 = new MineGiftFragment();
    var0.setArguments(new Bundle());
    return var0;
  }

  public void onCreate(Bundle var1) {
    super.onCreate(var1);
  }

  public View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
    View var4 = var1.inflate(2130903061, var2, false);
    this.lv_my_gift = (XListView)var4.findViewById(2131230745);
    this.mMyGiftAdapter = new MyGiftAdapter(this.getActivity());
    this.lv_my_gift.setAdapter(this.mMyGiftAdapter);
    this.lv_my_gift.setPullRefreshEnable(true);
    this.lv_my_gift.setPullLoadEnable(false);
    this.lv_my_gift.setAutoLoadEnable(false);
    this.lv_my_gift.setXListViewListener(new IXListViewListener() {
      public void onLoadMore() {
        if(MineGiftFragment.this.mMyGiftNpi != -1) {
          MineGiftFragment.this.getMyGift(MineGiftFragment.this.mMyGiftNpi, 1);
        } else {
          MineGiftFragment.this.lv_my_gift.setPullLoadEnable(false);
          ToastUtils.showToast(MineGiftFragment.this.getActivity(), "没有更多了");
        }
      }

      public void onRefresh() {
        MineGiftFragment.this.lv_my_gift.setPullLoadEnable(false);
        MineGiftFragment.this.getMyGift(0, 0);
      }
    });
    this.getMyGift(0, 0);
    return var4;
  }
}
