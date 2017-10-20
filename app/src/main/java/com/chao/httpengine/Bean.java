package com.chao.httpengine;

/**
 * Created by yang2 on 2017/10/19.
 */

public class Bean {

    /**
     * status : 0
     * data : {"uid":"9b12cf9de7f14e92b4bfd77a96b2711b","user":{"id":"0000000000000001","username":"大涵哥","password":"","phone":"13666913334"}}
     */

    private int status;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uid : 9b12cf9de7f14e92b4bfd77a96b2711b
         * user : {"id":"0000000000000001","username":"大涵哥","password":"","phone":"13666913334"}
         */

        private String uid;
        private UserBean user;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * id : 0000000000000001
             * username : 大涵哥
             * password :
             * phone : 13666913334
             */

            private String id;
            private String username;
            private String password;
            private String phone;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }
        }
    }
}
