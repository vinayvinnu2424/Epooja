package com.example.epoojatest.AdaptersandModels;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductDetailsModel implements Serializable {

    String result;
    String message;

    ProductDetailsDataModel data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ProductDetailsDataModel getProductDetailsDataModel() {
        return data;
    }

    public void setProductDetailsDataModel(ProductDetailsDataModel productDetailsDataModel) {
        this.data = productDetailsDataModel;
    }
}
