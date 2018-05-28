package com.benmu.framework.http.okhttp.callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by zhy on 15/12/14.
 */
public abstract class StringCallback extends Callback<String> {
    public int code;
    public Response response;

    @Override
    public String parseNetworkResponse(Response response, int id) throws IOException {
        code = response.code();
        this.response = response;
        return response.body().string();
    }
}
