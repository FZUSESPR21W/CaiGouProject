package com.example.caigouapp.data;

import java.io.Serializable;
import java.util.List;

public class UserAddressResponse {

    private String msg;
    private String code;
    private List<AddressBean> Address;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<AddressBean> getAddress() {
        return Address;
    }

    public void setAddress(List<AddressBean> address) {
        Address = address;
    }

    public static class AddressBean {

        private int id;
        private String address;
        private String phone;
        private String name;
        private int user_id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }
    }
}
