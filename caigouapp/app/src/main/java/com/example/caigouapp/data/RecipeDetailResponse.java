package com.example.caigouapp.data;

import java.io.Serializable;
import java.util.List;

public class RecipeDetailResponse implements Serializable {

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean implements Serializable {

        private int id;
        private String name;
        private String tags;
        private String method;
        private String avatar;//image
        private String food_weight_list;//this string contains weights of every food,sort by order,please split by ','.
        private List<FoodBean> food;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getFood_weight_list() {
            return food_weight_list;
        }

        public void setFood_weight_list(String food_weight_list) {
            this.food_weight_list = food_weight_list;
        }

        public List<FoodBean> getFood() {
            return food;
        }

        public void setFood(List<FoodBean> food) {
            this.food = food;
        }

        public static class FoodBean implements Serializable {

            private int id;
            private String ingredient;//name
            private double price;
            private int major;// 1 = ingredient 0 = sideIngredient
            private Object standard_weight;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIngredient() {
                return ingredient;
            }

            public void setIngredient(String ingredient) {
                this.ingredient = ingredient;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public int getMajor() {
                return major;
            }

            public void setMajor(int major) {
                this.major = major;
            }

            public Object getStandard_weight() {
                return standard_weight;
            }

            public void setStandard_weight(Object standard_weight) {
                this.standard_weight = standard_weight;
            }
        }
    }
}
