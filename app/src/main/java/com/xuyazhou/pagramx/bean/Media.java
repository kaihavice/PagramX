package com.xuyazhou.pagramx.bean;

import com.xuyazhou.bean.UserBean;

import java.util.ArrayList;

/**
 * Author: xuyazhou(xuyazhou18@gmail.com)
 * <p/>
 * Date: 2015-03-05
 */

public class Media {

	private ArrayList<String> tags;
	private Location location;
	private String link;
	private boolean user_has_liked;
	private Caption caption;
	private String type;
	private String id;
	private Likes likes;
	private Images images;
	private ArrayList<UsersInPhoto> users_in_photo;
	private String created_time;
	private UserBean user;
	private String filter;
	private Comments comments;
	private String attribution;

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isUser_has_liked() {
        return user_has_liked;
    }

    public void setUser_has_liked(boolean user_has_liked) {
        this.user_has_liked = user_has_liked;
    }

    public Caption getCaption() {
        return caption;
    }

    public void setCaption(Caption caption) {
        this.caption = caption;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public ArrayList<UsersInPhoto> getUsers_in_photo() {
        return users_in_photo;
    }

    public void setUsers_in_photo(ArrayList<UsersInPhoto> users_in_photo) {
        this.users_in_photo = users_in_photo;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }
}
