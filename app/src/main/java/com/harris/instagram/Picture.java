package com.harris.instagram;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by harris on 2/4/15.
 */
public class Picture {
  private String imageUrl;
  private String caption;
  private int imageHeight;
  private int likesCount;
  private String username;

  public static Picture parseJSON(JSONObject jsonObject) {
    Picture picture = new Picture();
    try {
      picture.setCaption(jsonObject.getJSONObject("caption").getString("text"));
      picture.setUsername(jsonObject.getJSONObject("user").getString("username"));
      picture.setImageHeight(
          jsonObject.getJSONObject("images").getJSONObject("standard_resolution").getInt("height"));
      picture.setImageUrl(
          jsonObject.getJSONObject("images").getJSONObject("standard_resolution").getString("url"));
      picture.setLikesCount(jsonObject.getJSONObject("likes").getInt("count"));
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return picture;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  public int getImageHeight() {
    return imageHeight;
  }

  public void setImageHeight(int imageHeight) {
    this.imageHeight = imageHeight;
  }

  public int getLikesCount() {
    return likesCount;
  }

  public void setLikesCount(int likesCount) {
    this.likesCount = likesCount;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
