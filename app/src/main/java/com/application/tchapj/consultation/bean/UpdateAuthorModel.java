package com.application.tchapj.consultation.bean;

import com.application.tchapj.base.BaseModel;

public class UpdateAuthorModel extends BaseModel {

    private UpdateAuthorData data;

    public UpdateAuthorData getData() {
        return data;
    }

    public void setData(UpdateAuthorData data) {
        this.data = data;
    }
}
