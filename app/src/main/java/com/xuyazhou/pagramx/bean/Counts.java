package com.xuyazhou.pagramx.bean;

/**
* Created by yazhou on 2015/3/8 0008.
*/
public class Counts {
    /**
     * follows : 63
     * followed_by : 5
     * media : 5
     */
    private int follows;
    private int followed_by;
    private int media;

    public void setFollows(int follows) {
        this.follows = follows;
    }

    public void setFollowed_by(int followed_by) {
        this.followed_by = followed_by;
    }

    public void setMedia(int media) {
        this.media = media;
    }

    public int getFollows() {
        return follows;
    }

    public int getFollowed_by() {
        return followed_by;
    }

    public int getMedia() {
        return media;
    }
}
