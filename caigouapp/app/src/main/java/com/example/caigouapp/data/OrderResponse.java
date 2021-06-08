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
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        private List<InfoBean> info;

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean implements Serializable {

            private int id;
            private String storeName;
            private int orderState;
            private String orderNumber;
            private String oderCreatTime;
            private String oderServeTime;
            private String phone;
            private String remark;
            private double price;
            private String address;
            private List<ListBean> info;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public int getOrderState() {
                return orderState;
            }

            public void setOrderState(int orderState) {
                this.orderState = orderState;
            }

            public String getOrderNumber() {
                return orderNumber;
            }

            public void setOrderNumber(String orderNumber) {
                this.orderNumber = orderNumber;
            }

            public String getOderCreatTime() {
                return oderCreatTime;
            }

            public void setOderCreatTime(String oderCreatTime) {
                this.oderCreatTime = oderCreatTime;
            }

            public String getOderServeTime() {
                return oderServeTime;
            }

            public void setOderServeTime(String oderServeTime) {
                this.oderServeTime = oderServeTime;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public List<ListBean> getInfo() {
                return info;
            }

            public void setInfo(List<ListBean> info) {
                this.info = info;
            }

            public static class ListBean implements Serializable {

                private int id;
                private String name;
                private String tags;
                private String method;
                private String avatar;
                private String multiple;
                private double price;
                private int menuId;
                private List<FoodBean> list;

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

                public double getPrice() {
                    return price;
                }

                public void setPrice(double price) {
                    this.price = price;
                }

                public int getMenuId() {
                    return menuId;
                }

                public void setMenuId(int menuId) {
                    this.menuId = menuId;
                }

                public List<FoodBean> getList() {
                    return list;
                }

                public void setList(List<FoodBean> list) {
                    this.list = list;
                }

                public static class FoodBean implements Serializable {
                    private int id;
                    private int major;
                    private String ingredient;
                    private String standard_weight;
                    private double price;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public int getMajor() {
                        return major;
                    }

                    public void setMajor(int major) {
                        this.major = major;
                    }

                    public String getIngredient() {
                        return ingredient;
                    }

                    public void setIngredient(String ingredient) {
                        this.ingredient = ingredient;
                    }

                    public String getStandard_weight() {
                        return standard_weight;
                    }

                    public void setStandard_weight(String standard_weight) {
                        this.standard_weight = standard_weight;
                    }

                    public double getPrice() {
                        return price;
                    }

                    public void setPrice(double price) {
                        this.price = price;
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "OrderResponse{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}