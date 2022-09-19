/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import backend.database.DataStorage;
import backend.database.SQlStorage;
import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author sandeep
 */

@ManagedBean
@SessionScoped
public class DealFunctions implements Serializable{
   private int dealId;
    private String title; 
    private String description; 
    private Date dateCreated; 
    private int rating;
    private String img_url;
    private String status;
    private int price;
    private String userID;

    public DealFunctions(int dealId, String title, String description, Date dateCreated, int rating,String img_url, String status, int price, String userID) {
        this.dealId = dealId;
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.rating = rating;
        this.img_url = img_url;
        this.status = status;
        this.price = price;
        this.userID = userID;   
    }
    
   public DealFunctions()
    {    
        
    }
    
    public int getDealId() {
        return dealId;
    }

    public void setDealId(int dealId) {
        this.dealId = dealId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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


    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    public String saveDeal() throws Exception{
        DataStorage data = new SQlStorage();
        try
        {
            data.saveDeal(dealId, title, description, dateCreated, rating, img_url, status, price, userID);
            System.out.println("Saved the Deal with id " + dealId);
            return "forums";
        }
        catch(Exception ex)
        {
          ex.printStackTrace();
            throw ex;
        }      
    }
}
