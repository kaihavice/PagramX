package com.xuyazhou.pagramx.bean;

/**
* Author: xuyazhou(xuyazhou18@gmail.com)
* <p/>
* Date: 2015-03-05
*/
public class Image {
    /**
     * height : 150 width : 150 url :
     * http://scontent.cdninstagram.com/hphotos
     * -xaf1/t51.2885-15/s150x150
     * /e15/10985988_910606002293845_928276327_n.jpg
     */
    private int height;
    private int width;
    private String url;

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getUrl() {
        return url;
    }
}
