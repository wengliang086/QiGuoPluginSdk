/*     */ package com.kding.core.plugin.reflect;
/*     */ 
/*     */ import android.annotation.SuppressLint;
/*     */ import java.lang.reflect.AccessibleObject;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationHandler;
/*     */ import java.lang.reflect.Member;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.lang.reflect.Proxy;
/*     */ import java.util.Arrays;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SuppressLint({"DefaultLocale"})
/*     */ public class Reflect
/*     */ {
/*     */   private final Object object;
/*     */   private final boolean isClass;
/*     */   
/*     */   private Reflect(Class<?> type)
/*     */   {
/*  31 */     this.object = type;
/*  32 */     this.isClass = true;
/*     */   }
/*     */   
/*     */   private Reflect(Object object) {
/*  36 */     this.object = object;
/*  37 */     this.isClass = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Reflect on(String name)
/*     */     throws ReflectException
/*     */   {
/*  49 */     return on(forName(name));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Reflect on(String name, ClassLoader classLoader)
/*     */     throws ReflectException
/*     */   {
/*  63 */     return on(forName(name, classLoader));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Reflect on(Class<?> clazz)
/*     */   {
/*  76 */     return new Reflect(clazz);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Reflect on(Object object)
/*     */   {
/*  93 */     return new Reflect(object);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <T extends AccessibleObject> T accessible(T accessible)
/*     */   {
/* 104 */     if (accessible == null) {
/* 105 */       return null;
/*     */     }
/*     */     
/* 108 */     if ((accessible instanceof Member)) {
/* 109 */       Member member = (Member)accessible;
/*     */       
/* 111 */       if ((Modifier.isPublic(member.getModifiers())) && 
/* 112 */         (Modifier.isPublic(member.getDeclaringClass().getModifiers())))
/*     */       {
/* 114 */         return accessible;
/*     */       }
/*     */     }
/* 117 */     if (!accessible.isAccessible()) {
/* 118 */       accessible.setAccessible(true);
/*     */     }
/*     */     
/* 121 */     return accessible;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SuppressLint({"DefaultLocale"})
/*     */   private static String property(String string)
/*     */   {
/* 136 */     int length = string.length();
/*     */     
/* 138 */     if (length == 0)
/* 139 */       return "";
/* 140 */     if (length == 1) {
/* 141 */       return string.toLowerCase();
/*     */     }
/* 143 */     return string.substring(0, 1).toLowerCase() + string.substring(1);
/*     */   }
/*     */   
/*     */   private static Reflect on(Constructor<?> constructor, Object... args) throws ReflectException
/*     */   {
/*     */     try {
/* 149 */       return on(((Constructor)accessible(constructor)).newInstance(args));
/*     */     } catch (Exception e) {
/* 151 */       throw new ReflectException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   private static Reflect on(Method method, Object object, Object... args) throws ReflectException {
/*     */     try {
/* 157 */       accessible(method);
/*     */       
/* 159 */       if (method.getReturnType() == Void.TYPE) {
/* 160 */         method.invoke(object, args);
/* 161 */         return on(object);
/*     */       }
/* 163 */       return on(method.invoke(object, args));
/*     */     }
/*     */     catch (Exception e) {
/* 166 */       throw new ReflectException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static Object unwrap(Object object)
/*     */   {
/* 174 */     if ((object instanceof Reflect)) {
/* 175 */       return ((Reflect)object).get();
/*     */     }
/*     */     
/* 178 */     return object;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static Class<?>[] types(Object... values)
/*     */   {
/* 188 */     if (values == null) {
/* 189 */       return new Class[0];
/*     */     }
/*     */     
/* 192 */     Class<?>[] result = new Class[values.length];
/*     */     
/* 194 */     for (int i = 0; i < values.length; i++) {
/* 195 */       Object value = values[i];
/* 196 */       result[i] = (value == null ? NULL.class : value.getClass());
/*     */     }
/*     */     
/* 199 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static Class<?> forName(String name)
/*     */     throws ReflectException
/*     */   {
/*     */     try
/*     */     {
/* 209 */       return Class.forName(name);
/*     */     } catch (Exception e) {
/* 211 */       throw new ReflectException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   private static Class<?> forName(String name, ClassLoader classLoader) throws ReflectException {
/*     */     try {
/* 217 */       return Class.forName(name, true, classLoader);
/*     */     } catch (Exception e) {
/* 219 */       throw new ReflectException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Class<?> wrapper(Class<?> type)
/*     */   {
/* 228 */     if (type == null)
/* 229 */       return null;
/* 230 */     if (type.isPrimitive()) {
/* 231 */       if (Boolean.TYPE == type)
/* 232 */         return Boolean.class;
/* 233 */       if (Integer.TYPE == type)
/* 234 */         return Integer.class;
/* 235 */       if (Long.TYPE == type)
/* 236 */         return Long.class;
/* 237 */       if (Short.TYPE == type)
/* 238 */         return Short.class;
/* 239 */       if (Byte.TYPE == type)
/* 240 */         return Byte.class;
/* 241 */       if (Double.TYPE == type)
/* 242 */         return Double.class;
/* 243 */       if (Float.TYPE == type)
/* 244 */         return Float.class;
/* 245 */       if (Character.TYPE == type)
/* 246 */         return Character.class;
/* 247 */       if (Void.TYPE == type) {
/* 248 */         return Void.class;
/*     */       }
/*     */     }
/*     */     
/* 252 */     return type;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <T> T get()
/*     */   {
/* 263 */     return (T)this.object;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Reflect set(String name, Object value)
/*     */     throws ReflectException
/*     */   {
/*     */     try
/*     */     {
/* 276 */       Field field = field0(name);
/* 277 */       field.setAccessible(true);
/* 278 */       field.set(this.object, unwrap(value));
/* 279 */       return this;
/*     */     } catch (Exception e) {
/* 281 */       throw new ReflectException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <T> T get(String name)
/*     */     throws ReflectException
/*     */   {
/* 292 */     return (T)field(name).get();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Reflect field(String name)
/*     */     throws ReflectException
/*     */   {
/*     */     try
/*     */     {
/* 304 */       Field field = field0(name);
/* 305 */       return on(field.get(this.object));
/*     */     } catch (Exception e) {
/* 307 */       throw new ReflectException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   private Field field0(String name) throws ReflectException {
/* 312 */     Class<?> type = type();
/*     */     
/*     */     try
/*     */     {
/* 316 */       return type.getField(name);
/*     */     }
/*     */     catch (NoSuchFieldException e)
/*     */     {
/*     */       do
/*     */       {
/*     */         try {
/* 323 */           return (Field)accessible(type.getDeclaredField(name));
/*     */         }
/*     */         catch (NoSuchFieldException ignore)
/*     */         {
/* 327 */           type = type.getSuperclass();
/*     */         }
/* 329 */       } while (type != null);
/*     */       
/* 331 */       throw new ReflectException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Map<String, Reflect> fields()
/*     */   {
/* 341 */     Map<String, Reflect> result = new LinkedHashMap();
/* 342 */     Class<?> type = type();
/*     */     do
/*     */     {
/* 345 */       for (Field field : type.getDeclaredFields()) {
/* 346 */         if ((!this.isClass ^ Modifier.isStatic(field.getModifiers()))) {
/* 347 */           String name = field.getName();
/*     */           
/* 349 */           if (!result.containsKey(name)) {
/* 350 */             result.put(name, field(name));
/*     */           }
/*     */         }
/*     */       }
/* 354 */       type = type.getSuperclass();
/*     */     }
/* 356 */     while (type != null);
/*     */     
/* 358 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Reflect call(String name)
/*     */     throws ReflectException
/*     */   {
/* 369 */     return call(name, new Object[0]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Reflect call(String name, Object... args)
/*     */     throws ReflectException
/*     */   {
/* 381 */     Class<?>[] types = types(args);
/*     */     
/*     */     try
/*     */     {
/* 385 */       Method method = exactMethod(name, types);
/* 386 */       return on(method, this.object, args);
/*     */     } catch (NoSuchMethodException e) {
/*     */       try {
/* 389 */         Method method = similarMethod(name, types);
/* 390 */         return on(method, this.object, args);
/*     */       } catch (NoSuchMethodException e1) {
/* 392 */         throw new ReflectException(e1);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private Method exactMethod(String name, Class<?>[] types) throws NoSuchMethodException {
/* 398 */     Class<?> type = type();
/*     */     try
/*     */     {
/* 401 */       return type.getMethod(name, types);
/*     */     } catch (NoSuchMethodException e) {
/*     */       do {
/*     */         try {
/* 405 */           return type.getDeclaredMethod(name, types);
/*     */         }
/*     */         catch (NoSuchMethodException ignore)
/*     */         {
/* 409 */           type = type.getSuperclass();
/*     */         }
/* 411 */       } while (type != null);
/*     */       
/* 413 */       throw new NoSuchMethodException();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private Method similarMethod(String name, Class<?>[] types)
/*     */     throws NoSuchMethodException
/*     */   {
/* 421 */     Class<?> type = type();
/*     */     
/* 423 */     for (Method method : type.getMethods()) {
/* 424 */       if (isSimilarSignature(method, name, types)) {
/* 425 */         return method;
/*     */       }
/*     */     }
/*     */     do
/*     */     {
/* 430 */       for (Method method : type.getDeclaredMethods()) {
/* 431 */         if (isSimilarSignature(method, name, types)) {
/* 432 */           return method;
/*     */         }
/*     */       }
/*     */       
/* 436 */       type = type.getSuperclass();
/*     */     }
/* 438 */     while (type != null);
/*     */     
/* 440 */     throw new NoSuchMethodException("No similar method " + name + " with params " + Arrays.toString(types) + " could be found on type " + type() + ".");
/*     */   }
/*     */   
/*     */   private boolean isSimilarSignature(Method possiblyMatchingMethod, String desiredMethodName, Class<?>[] desiredParamTypes) {
/* 444 */     return (possiblyMatchingMethod.getName().equals(desiredMethodName)) && (match(possiblyMatchingMethod.getParameterTypes(), desiredParamTypes));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Reflect create()
/*     */     throws ReflectException
/*     */   {
/* 454 */     return create(new Object[0]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Reflect create(Object... args)
/*     */     throws ReflectException
/*     */   {
/* 465 */     Class<?>[] types = types(args);
/*     */     
/*     */     try
/*     */     {
/* 469 */       Constructor<?> constructor = type().getDeclaredConstructor(types);
/* 470 */       return on(constructor, args);
/*     */     } catch (NoSuchMethodException e) {
/* 472 */       for (Constructor<?> constructor : type().getDeclaredConstructors()) {
/* 473 */         if (match(constructor.getParameterTypes(), types)) {
/* 474 */           return on(constructor, args);
/*     */         }
/*     */       }
/*     */       
/* 478 */       throw new ReflectException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <P> P as(Class<P> proxyType)
/*     */   {
/* 491 */     final boolean isMap = this.object instanceof Map;
/* 492 */     InvocationHandler handler = new InvocationHandler()
/*     */     {
/*     */       public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
/* 495 */         String name = method.getName();
/*     */         try {
/* 497 */           return Reflect.on(Reflect.this.object).call(name, args).get();
/*     */         } catch (ReflectException e) {
/* 499 */           if (isMap) {
/* 500 */             Map<String, Object> map = (Map)Reflect.this.object;
/* 501 */             int length = args == null ? 0 : args.length;
/*     */             
/* 503 */             if ((length == 0) && (name.startsWith("get")))
/* 504 */               return map.get(Reflect.property(name.substring(3)));
/* 505 */             if ((length == 0) && (name.startsWith("is")))
/* 506 */               return map.get(Reflect.property(name.substring(2)));
/* 507 */             if ((length == 1) && (name.startsWith("set"))) {
/* 508 */               map.put(Reflect.property(name.substring(3)), args[0]);
/* 509 */               return null;
/*     */             }
/*     */           }
/*     */           
/* 513 */           throw e;
/*     */         }
/*     */         
/*     */       }
/* 517 */     };
/* 518 */     return (P)Proxy.newProxyInstance(proxyType.getClassLoader(), new Class[] { proxyType }, handler);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private boolean match(Class<?>[] declaredTypes, Class<?>[] actualTypes)
/*     */   {
/* 525 */     if (declaredTypes.length == actualTypes.length) {
/* 526 */       for (int i = 0; i < actualTypes.length; i++) {
/* 527 */         if (actualTypes[i] != NULL.class)
/*     */         {
/*     */ 
/* 530 */           if (!wrapper(declaredTypes[i]).isAssignableFrom(wrapper(actualTypes[i])))
/*     */           {
/*     */ 
/* 533 */             return false; }
/*     */         }
/*     */       }
/* 536 */       return true;
/*     */     }
/* 538 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 547 */     return this.object.hashCode();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 555 */     if ((obj instanceof Reflect)) {
/* 556 */       return this.object.equals(((Reflect)obj).get());
/*     */     }
/*     */     
/* 559 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 567 */     return this.object.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Class<?> type()
/*     */   {
/* 576 */     if (this.isClass) {
/* 577 */       return (Class)this.object;
/*     */     }
/* 579 */     return this.object.getClass();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\QiguoSdk.jar!\com\kding\core\plugin\reflect\Reflect.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */