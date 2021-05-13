package com.example.caigouapp.data;

import java.io.Serializable;
import java.util.List;

public class OrderResponse implements Serializable {

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
            private int price;
            private int menuId;
            private String multiple;
            private List<ListBean> list;

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

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getMenuId() {
                return menuId;
            }

            public void setMenuId(int menuId) {
                this.menuId = menuId;
            }

            public String getMultiple() {
                return multiple;
            }

            public void setMultiple(String multiple) {
                this.multiple = multiple;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean implements Serializable {

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
