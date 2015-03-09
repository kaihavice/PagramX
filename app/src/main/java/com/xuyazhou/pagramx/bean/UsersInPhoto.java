package com.xuyazhou.pagramx.bean;

import com.xuyazhou.bean.UserBean;

/**
* Author: xuyazhou(xuyazhou18@gmail.com)
* <p/>
* Date: 2015-03-05
*/
public class UsersInPhoto {
    /**
     * position : {"y":0.8997422,"x":0.21481481} user :
     * {"id":"6105173","username":"garethgetty","profile_picture":
     * "http://images.ak.instagram.com/profiles/profile_6105173_75sq_1356776155.jpg"
     * ,"full_name":"Gareth Cattermole"}
     */
    private Position position;
    private UserBean user;

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public Position getPosition() {
        return position;
    }

    public UserBean getUser() {
        return user;
    }

    public class Position {
        /**
         * y : 0.8997422 x : 0.21481481
         */
        private double y;
        private double x;

        public void setY(double y) {
            this.y = y;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public double getX() {
            return x;
        }
    }


}
