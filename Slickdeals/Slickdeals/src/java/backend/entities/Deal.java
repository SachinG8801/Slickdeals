/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.entities;

import java.sql.Date;

public class Deal 
{
    private int dealId;
    private String title;
    private String desciption;
    private Date dateCreated;
    private int rating;
    private String img_url;
    private String status;
    private float price;
    private String userID;

    public Deal(int dealId, String title, String desciption, Date dateCreated, int rating, String img_url, String status, float price, String userID) {
        this.dealId = dealId;
        this.title = title;
        this.desciption = desciption;
        this.dateCreated = dateCreated;
        this.rating = rating;
        this.img_url = img_url;
        this.status = status;
        this.price = price;
        this.userID = userID;
    }

    public Deal(int dealId ,String title, Date dateCreated, int rating, float price, String userID) {
        this.dealId=dealId;
        this.title = title;
        this.dateCreated = dateCreated;
        this.rating = rating;
        this.price = price;
        this.userID = userID;
    }

    public Deal(int dealId, String title, String desciption, Date dateCreated, int rating, String img_url, float price, String userID) {
        this.dealId = dealId;
        this.title = title;
        this.desciption = desciption;
        this.dateCreated = dateCreated;
        this.rating = rating;
        this.img_url = img_url;
        this.price = price;
        this.userID = userID;
    }

    public Deal(int dealId, String title, String desciption, int rating, String img_url, float price) {
        this.dealId = dealId;
        this.title = title;
        this.desciption = desciption;
        this.rating = rating;
        this.img_url = img_url;
        this.price = price;
    }

    public Deal(int dealId, String title) {
        this.dealId = dealId;
        this.title = title;
    }
    

    public Deal() {
    }

    public int getDealId() {
        return dealId;
    }

    public void setDealId(int dealId) {
        this.dealId = dealId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    } 
   
    public String getTitle() {
        return title;
    }

    public String getDesciption() {
        return desciption;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getStatus() {
        return status;
    }

    public String getUserID() {
        return userID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }
    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
}
