package com.example.caigouapp.data;

import java.io.Serializable;
import java.util.List;

public class CartResponse implements Serializable {

    private int code;
    private DataBean data;
    private String message;

    public static class DataBean implements Serializable {
        private List<InfoBean> info;

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean implements Serializable {

            private int id;
            private String name;
            private String tags;
            private String method;
            private String avatar;
            private String food_weight_list;
            private int price;
            private String multiple;
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

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getMultiple() {
                return multiple;
            }

            public void setMultiple(String multiple) {
                this.multiple = multiple;
            }

            public List<FoodBean> getFood() {
                return food;
            }

            public void setFood(List<FoodBean> food) {
                this.food = food;
            }

            public static class FoodBean implements Serializable {

                private int id;
                private String ingredient;
                private int price;
                private int major;
                private String standard_weight;

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

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
                }

                public int getMajor() {
                    return major;
                }

                public void setMajor(int major) {
                    this.major = major;
                }

                public String getStandard_weight() {
                    return standard_weight;
                }

                public void setStandard_weight(String standard_weight) {
                    this.standard_weight = standard_weight;
                }
            }
        }
    }
}
