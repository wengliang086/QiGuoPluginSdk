package com.kding.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T>
  implements Converter<ResponseBody, T>
{
  private final TypeAdapter<T> adapter;
  private final Gson gson;
  
  GsonResponseBodyConverter(Gson paramGson, TypeAdapter<T> paramTypeAdapter)
  {
    this.gson = paramGson;
    this.adapter = paramTypeAdapter;
  }
  
  public T convert(ResponseBody paramResponseBody)
    throws IOException
  {
    JsonReader localJsonReader = this.gson.newJsonReader(paramResponseBody.charStream());
    try
    {
      Object localObject2 = this.adapter.read(localJsonReader);
      return (T)localObject2;
    }
    finally
    {
      paramResponseBody.close();
    }
  }
}


/* Location:              C:\Users\Administrator\Desktop\app-debug\assets\app\classes_dex2jar.jar!\com\kding\converter\GsonResponseBodyConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */