package com.jd_zpc.bean;

/**
 * Created by DELL on 2017/11/3.
 */

public class Users extends BaseBean{

    /**
     * data : {"age":null,"appkey":"c9187cf1ac1f1163","appsecret":"F117FE15C42EB288638CF94D1E4D6ABC","createtime":"2017-11-03T20:21:45","email":null,"gender":null,"icon":null,"mobile":"18739445222","money":null,"nickname":null,"password":"09B3553322CBAE1ACC2135920852B2AA","token":"34AE9926C0B944794F181D667BDA58B6","uid":1583,"username":"18739445222"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * age : null
         * appkey : c9187cf1ac1f1163
         * appsecret : F117FE15C42EB288638CF94D1E4D6ABC
         * createtime : 2017-11-03T20:21:45
         * email : null
         * gender : null
         * icon : null
         * mobile : 18739445222
         * money : null
         * nickname : null
         * password : 09B3553322CBAE1ACC2135920852B2AA
         * token : 34AE9926C0B944794F181D667BDA58B6
         * uid : 1583
         * username : 18739445222
         */

        private Object age;
        private String appkey;
        private String appsecret;
        private String createtime;
        private Object email;
        private Object gender;
        private Object icon;
        private String mobile;
        private Object money;
        private Object nickname;
        private String password;
        private String token;
        private int uid;
        private String username;

        public Object getAge() {
            return age;
        }

        public void setAge(Object age) {
            this.age = age;
        }

        public String getAppkey() {
            return appkey;
        }

        public void setAppkey(String appkey) {
            this.appkey = appkey;
        }

        public String getAppsecret() {
            return appsecret;
        }

        public void setAppsecret(String appsecret) {
            this.appsecret = appsecret;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getGender() {
            return gender;
        }

        public void setGender(Object gender) {
            this.gender = gender;
        }

        public Object getIcon() {
            return icon;
        }

        public void setIcon(Object icon) {
            this.icon = icon;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Object getMoney() {
            return money;
        }

        public void setMoney(Object money) {
            this.money = money;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
