package com.example.caigouapp.data;

import org.litepal.crud.LitePalSupport;

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

}
