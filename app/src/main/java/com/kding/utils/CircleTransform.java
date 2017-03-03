package com.kding.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import com.squareup.picasso.Transformation;

public class CircleTransform
  implements Transformation
{
  public String key()
  {
    return "circle";
  }
  
  public Bitmap transform(Bitmap paramBitmap)
  {
    int i = Math.min(paramBitmap.getWidth(), paramBitmap.getHeight());
    Bitmap localBitmap1 = Bitmap.createBitmap(paramBitmap, (paramBitmap.getWidth() - i) / 2, (paramBitmap.getHeight() - i) / 2, i, i);
    if (localBitmap1 != paramBitmap) {
      paramBitmap.recycle();
    }
    if (paramBitmap.getConfig() != null) {}
    for (Config localConfig = paramBitmap.getConfig();; localConfig = Config.ARGB_8888)
    {
      Bitmap localBitmap2 = Bitmap.createBitmap(i, i, localConfig);
      Canvas localCanvas = new Canvas(localBitmap2);
      Paint localPaint = new Paint();
      localPaint.setShader(new BitmapShader(localBitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
      localPaint.setAntiAlias(true);
      float f = i / 2.0F;
      localCanvas.drawCircle(f, f, f, localPaint);
      localBitmap1.recycle();
      return localBitmap2;
    }
  }
}