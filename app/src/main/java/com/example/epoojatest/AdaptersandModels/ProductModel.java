package com.example.epoojatest.AdaptersandModels;

import java.util.ArrayList;

public class ProductModel {
    String result;
    String message;
    String productcount;
    ArrayList<category_products> category_products;

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

    public String getProductcount() {
        return productcount;
    }

    public void setProductcount(String productcount) {
        this.productcount = productcount;
    }

    public ArrayList<ProductModel.category_products> getCategory_products() {
        return category_products;
    }

    public void setCategory_products(ArrayList<ProductModel.category_products> category_products) {
        this.category_products = category_products;
    }

    public class category_products {
        String product_id;
        String name;
        String image;
        String rating;
        String stock_status;
        String availability;
        String actualprice;
        String offerprice;
        String symbol;
        String wishlist;

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getStock_status() {
            return stock_status;
        }

        public void setStock_status(String stock_status) {
            this.stock_status = stock_status;
        }

        public String getAvailability() {
            return availability;
        }

        public void setAvailability(String availability) {
            this.availability = availability;
        }

        public String getActualprice() {
            return actualprice;
        }

        public void setActualprice(String actualprice) {
            this.actualprice = actualprice;
        }

        public String getOfferprice() {
            return offerprice;
        }

        public void setOfferprice(String offerprice) {
            this.offerprice = offerprice;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getWishlist() {
            return wishlist;
        }

        public void setWishlist(String wishlist) {
            this.wishlist = wishlist;
        }
    }
}
