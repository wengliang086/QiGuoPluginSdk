package com.kding.converter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Converter.Factory;
import retrofit2.Retrofit;

public final class GsonConverterFactory
  extends Converter.Factory
{
  private final Gson gson;
  
  private GsonConverterFactory(Gson paramGson)
  {
    if (paramGson == null) {
      throw new NullPointerException("gson == null");
    }
    this.gson = paramGson;
  }
  
  public static GsonConverterFactory create()
  {
    return create(new Gson());
  }
  
  public static GsonConverterFactory create(Gson paramGson)
  {
    return new GsonConverterFactory(paramGson);
  }
  
  public Converter<?, RequestBody> requestBodyConverter(Type paramType, Annotation[] paramArrayOfAnnotation1, Annotation[] paramArrayOfAnnotation2, Retrofit paramRetrofit)
  {
    TypeAdapter localTypeAdapter = this.gson.getAdapter(TypeToken.get(paramType));
    return new GsonRequestBodyConverter(this.gson, localTypeAdapter);
  }
  
  public Converter<ResponseBody, ?> responseBodyConverter(Type paramType, Annotation[] paramArrayOfAnnotation, Retrofit paramRetrofit)
  {
    TypeAdapter localTypeAdapter = this.gson.getAdapter(TypeToken.get(paramType));
    return new GsonResponseBodyConverter(this.gson, localTypeAdapter);
  }
}


/* Location:              C:\Users\Administrator\Desktop\app-debug\assets\app\classes_dex2jar.jar!\com\kding\converter\GsonConverterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */