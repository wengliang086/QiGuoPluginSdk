//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.kding.bean.IssueDetailBean.QuestionContentArrBean;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDetailAdpater extends BaseAdapter {
  private static final int TYPE_ANSWER = 1;
  private static final int TYPE_ASK = 0;
  private List<QuestionContentArrBean> datas = new ArrayList();
  private Context mContext;
  private String mType;

  public FeedbackDetailAdpater(Context var1) {
    this.mContext = var1;
  }

  public int getCount() {
    return this.datas.size();
  }

  public Object getItem(int var1) {
    return this.datas.get(var1);
  }

  public long getItemId(int var1) {
    return (long)var1;
  }

  public int getItemViewType(int var1) {
    return ((QuestionContentArrBean)this.datas.get(var1)).is_ask()?0:1;
  }

  public View getView(int var1, View var2, ViewGroup var3) {
//    int var4 = this.getItemViewType(var1);
//    FeedbackDetailAdpater.AskHolder var5 = null;
//    QuestionContentArrBean var6 = null;
//    FeedbackDetailAdpater.AnswerHolder var7;
//    if(var4 == 0) {
//      if(var2 == null) {
//        var2 = LayoutInflater.from(this.mContext).inflate(2130903047, var3, false);
//        var5 = new FeedbackDetailAdpater.AskHolder(var2);
//        var2.setTag(var5);
//        var7 = var6;
//      } else {
//        var5 = (FeedbackDetailAdpater.AskHolder)var2.getTag();
//        var7 = var6;
//      }
//    } else if(var2 == null) {
//      var2 = LayoutInflater.from(this.mContext).inflate(2130903046, var3, false);
//      var7 = new FeedbackDetailAdpater.AnswerHolder(var2);
//      var2.setTag(var7);
//    } else {
//      var7 = (FeedbackDetailAdpater.AnswerHolder)var2.getTag();
//    }
//
//    var6 = (QuestionContentArrBean)this.datas.get(var1);
//    if(!var6.is_ask()) {
//      var7.tv_time.setText(var6.getTime());
//      var7.tv_content.setText(var6.getContent());
//      return var2;
//    } else {
//      var5.tv_time.setText(var6.getTime());
//      var5.tv_content.setText(var6.getContent());
//      var5.tv_type.setText(this.mType);
//      if(var6.getImgs() != null && var6.getImgs().size() != 0) {
//        for(var1 = 0; var1 < var6.getImgs().size(); ++var1) {
//          Picasso.with(this.mContext).load((String)var6.getImgs().get(var1)).into((ImageView)var5.imgs.get(var1));
//        }
//
//        var5.imgsLayout.setVisibility(0);
//        return var2;
//      } else {
//        var5.imgsLayout.setVisibility(8);
//        return var2;
//      }
//    }
    return null;
  }

  public int getViewTypeCount() {
    return 2;
  }

  public void setDatas(String var1, List<QuestionContentArrBean> var2) {
    this.mType = var1;
    this.datas.clear();
    this.datas.addAll(var2);
    this.notifyDataSetChanged();
  }

  class AnswerHolder {
    private TextView tv_content;
    private TextView tv_time;

    public AnswerHolder(View var2) {
      this.tv_time = (TextView)var2.findViewById(2131230735);
      this.tv_content = (TextView)var2.findViewById(2131230736);
    }
  }

  class AskHolder {
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private List<ImageView> imgs = new ArrayList();
    private View imgsLayout;
    private TextView tv_content;
    private TextView tv_time;
    private TextView tv_type;

    public AskHolder(View var2) {
      this.imgsLayout = var2.findViewById(2131230739);
      this.tv_time = (TextView)var2.findViewById(2131230735);
      this.tv_type = (TextView)var2.findViewById(2131230737);
      this.tv_content = (TextView)var2.findViewById(2131230736);
      this.img1 = (ImageView)var2.findViewById(2131230741);
      this.img2 = (ImageView)var2.findViewById(2131230742);
      this.img3 = (ImageView)var2.findViewById(2131230743);
      this.imgs.add(this.img1);
      this.imgs.add(this.img2);
      this.imgs.add(this.img3);
    }
  }
}
