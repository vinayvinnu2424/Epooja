package com.example.epoojatest.AdaptersandModels;

import java.util.ArrayList;

public class CategoryModel {
    String result;
    String message;
    ArrayList<Categories> Categories;

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

    public ArrayList<CategoryModel.Categories> getCategories() {
        return Categories;
    }

    public class Categories {

        String category_id;
        String parent_id;
        String name;
        String image;
        String Issubcategories;
        ArrayList<ChildModel> subcategories;

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
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

        public String getIssubcategories() {
            return Issubcategories;
        }

        public void setIssubcategories(String issubcategories) {
            Issubcategories = issubcategories;
        }

        public ArrayList<ChildModel> getSubcategories() {
            return subcategories;
        }

    }

    public class ChildModel {
        String category_id;
        String parent_id;
        String name;
        String image;

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
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
    }
}
