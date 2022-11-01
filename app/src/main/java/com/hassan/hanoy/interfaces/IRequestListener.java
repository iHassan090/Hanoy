package com.hassan.hanoy.interfaces;

import org.json.JSONObject;

public interface IRequestListener {
    public void onResponse(JSONObject response);

    public void onError(String error);
}
