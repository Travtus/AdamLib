package com.travtusworks.adam.utils;

import com.travtusworks.adam.api.ErrorDAO;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by teodora on 12.06.2018.
 */

public class ErrorUtils {

    public static ErrorDAO parseError(Response response, Retrofit retrofit){

        Converter<ResponseBody, ErrorDAO> converter =
                retrofit.responseBodyConverter(ErrorDAO.class, new Annotation[0]);

        ErrorDAO error = null;

        try{
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return error;

    }

}
