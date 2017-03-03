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

import com.kding.bean.GameGiftBean;
import com.kding.bean.GameGiftBean.GiftListBean;
import com.kding.net.ResponseCallBack;
import com.kding.net.SDKService;
import com.kding.utils.ToastUtils;
import com.kding.view.XListView;
import com.kding.view.XListView.IXListViewListener;

import java.util.ArrayList;
import java.util.List;

public class AllGiftFragment extends Fragment {
    private static final int TYPE_LOADMORE = 1;
    private static final int TYPE_REFRESH = 0;
    private List<GiftListBean> gifts = new ArrayList();
    private boolean isGiftLoading = false;
    private XListView lvGift;
    private GiftAdapter mGiftAdapter;
    private int mGiftNpi = 0;
    private AllGiftFragment.SetGameName mSetGameName;

    public AllGiftFragment() {
    }

    private void getGift(int var1, final int var2) {
        if (!this.isGiftLoading) {
            this.isGiftLoading = true;
            SDKService.getInstance(this.getActivity()).getGameGift(var1, new ResponseCallBack<GameGiftBean>() {
                public void onError(int var1, String var2x, Throwable var3) {
                    AllGiftFragment.this.isGiftLoading = false;
                    ToastUtils.showToast(AllGiftFragment.this.getActivity(), var2x);
                    if (var2 == 0) {
                        AllGiftFragment.this.lvGift.stopRefresh();
                    } else {
                        AllGiftFragment.this.lvGift.stopLoadMore();
                    }
                }

                public void onSuccess(int var1, GameGiftBean var2x) {
                    AllGiftFragment.this.isGiftLoading = false;
                    AllGiftFragment.this.mGiftNpi = var1;
                    AllGiftFragment.this.mSetGameName.setGameName(var2x.getGamename());
                    if (var2 == 0) {
                        AllGiftFragment.this.gifts.clear();
                    }

                    AllGiftFragment.this.gifts.addAll(var2x.getGift_list());
                    AllGiftFragment.this.mGiftAdapter.setDatas(AllGiftFragment.this.gifts);
                    if (AllGiftFragment.this.mGiftNpi == -1) {
                        AllGiftFragment.this.lvGift.setPullLoadEnable(false);
                    } else {
                        AllGiftFragment.this.lvGift.setPullLoadEnable(true);
                    }

                    if (var2 == 0) {
                        AllGiftFragment.this.lvGift.stopRefresh();
                    } else {
                        AllGiftFragment.this.lvGift.stopLoadMore();
                    }
                }
            });
        }
    }

    public static AllGiftFragment newInstance() {
        AllGiftFragment var0 = new AllGiftFragment();
        var0.setArguments(new Bundle());
        return var0;
    }

    public void addCallback(AllGiftFragment.SetGameName var1) {
        this.mSetGameName = var1;
    }

    public void onCreate(Bundle var1) {
        super.onCreate(var1);
    }

    public View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
        View var4 = var1.inflate(R.layout.fragment_all_gift, var2, false);
        this.lvGift = (XListView) var4.findViewById(R.id.lv_gift);
        this.mGiftAdapter = new GiftAdapter(this.getActivity());
        this.lvGift.setAdapter(this.mGiftAdapter);
        this.lvGift.setPullRefreshEnable(true);
        this.lvGift.setPullLoadEnable(false);
        this.lvGift.setAutoLoadEnable(false);
        this.lvGift.setXListViewListener(new IXListViewListener() {
            public void onLoadMore() {
                if (AllGiftFragment.this.mGiftNpi != -1) {
                    AllGiftFragment.this.getGift(AllGiftFragment.this.mGiftNpi, 1);
                } else {
                    AllGiftFragment.this.lvGift.setPullLoadEnable(false);
                    ToastUtils.showToast(AllGiftFragment.this.getActivity(), "没有更多了");
                }
            }

            public void onRefresh() {
                AllGiftFragment.this.lvGift.setPullLoadEnable(false);
                AllGiftFragment.this.getGift(0, 0);
            }
        });
        this.getGift(0, 0);
        return var4;
    }

    public interface SetGameName {
        void setGameName(String var1);
    }
}
