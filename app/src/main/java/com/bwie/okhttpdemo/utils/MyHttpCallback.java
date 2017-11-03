package com.bwie.okhttpdemo.utils;

import com.google.gson.internal.$Gson$Types;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
/**
 * Created by 张乔君 on 2017/11/3.
 */

public abstract class MyHttpCallback<T> {
    Type type;
    static Type getSuperclassTypeParameter(Class<?> subclass){
        Type superclass =subclass.getGenericSuperclass();
        if(superclass instanceof Class){
            throw new RuntimeException("Missing type parameter");

        }
        ParameterizedType parameterized=(ParameterizedType)superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }
    public MyHttpCallback(){
        type=getSuperclassTypeParameter(getClass());
    }
    public abstract void onBeforeRequest(Request request);
    public abstract void onFailure(Call call, IOException e);
    public abstract void onSuccess(Response response, T t);
    public abstract void onError(Response response,String errorMsg);

}
