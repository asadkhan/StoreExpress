package com.example.irfan.storeexpressagas.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileDetailsResponse {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("iserror")
    @Expose
    private Boolean iserror;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("value")
    @Expose
    private Value value;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getIserror() {
        return iserror;
    }

    public void setIserror(Boolean iserror) {
        this.iserror = iserror;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
    public class Value {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("username")
        @Expose
        private Object username;
        @SerializedName("fullName")
        @Expose
        private String fullName;
        @SerializedName("phoneNumber")
        @Expose
        private String phoneNumber;
        @SerializedName("roleName")
        @Expose
        private Object roleName;
        @SerializedName("password")
        @Expose
        private Object password;
        @SerializedName("addedOn")
        @Expose
        private String addedOn;
        @SerializedName("level")
        @Expose
        private Integer level;
        @SerializedName("userAddress")
        @Expose
        private List<Object> userAddress = null;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Object getUsername() {
            return username;
        }

        public void setUsername(Object username) {
            this.username = username;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public Object getRoleName() {
            return roleName;
        }

        public void setRoleName(Object roleName) {
            this.roleName = roleName;
        }

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }

        public String getAddedOn() {
            return addedOn;
        }

        public void setAddedOn(String addedOn) {
            this.addedOn = addedOn;
        }

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        public List<Object> getUserAddress() {
            return userAddress;
        }

        public void setUserAddress(List<Object> userAddress) {
            this.userAddress = userAddress;
        }

    }
}
