/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import backend.database.DataStorage;
import backend.database.SQlStorage;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import backend.entities.Deal;
import backend.entities.Feedback;
import backend.entities.Comment;
import java.io.IOException;
import javax.faces.context.FacesContext;

/**
 *
 * @author sandeep
 */
public class UserAccount {
    
     private String user_id;
     private String password;
     private String type;
     private int dealId;
    private String title; 
    private String description; 
    private Date dateCreated; 
    private int rating;
    private String category;
    private String img_url;
    private String status;
    private float price;
    private DataStorage data;
    private ArrayList<Deal> dealFunctions =new ArrayList<Deal>();
    private int choosedDeal;
    private CommentFunctions commentFunctions;
    public ArrayList<Deal> getDealFunctions() {
        return dealFunctions;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public UserAccount() {
    }
             
    public UserAccount(String user_id, String password) {
        this.user_id = user_id;
        this.password = password;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }
     public void setData(DataStorage data) {
        this.data = data;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(String type) {
        this.type = type;
    }  
    public ArrayList<Deal> showAllThreads(){
       
        return data.fetchDealList();
    }
    public List<Deal> showDeal(){  
        List<Deal> deal =data.fetchDeal(choosedDeal);   
        return deal;
    }
    public String selectDeal(int deal_id){
        choosedDeal =deal_id;
        Deal deal = new Deal();
        deal.setDealId(choosedDeal);
        return "deal.xhtml";
    }
    public int selectedDeal(){
        return choosedDeal;
    }
    public int dealThread(){
        int thread =choosedDeal;
        return thread;
    }
    public List<Comment> reply_thread(){      
        ArrayList<Comment> replies = data.fetchComments(choosedDeal);
       return replies;
    }
    public void increaseRating() throws Exception{
        //DataStorage data = new SQlStorage();
        try {
            data.ratingPlusOne(choosedDeal);        
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }  
    }
    public void decreaseRating() throws Exception{
       // DataStorage data = new SQlStorage();
        try {
            data.ratingMinusOne(choosedDeal);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } 
    }
    public ArrayList<Deal> showAllDealsAboveTwoStars(){  
        return data.fetchDealsAboveTwo();
    }
    public ArrayList<Deal> showReviewList(){  
        return data.reviewList();
    }
    public String editorApproval(int deal_id)throws Exception{
       // DataStorage data = new SQlStorage();
        try{
            data.approveDeal(deal_id);
            return "welcomeEditor";
        }catch(Exception se){
            se.printStackTrace();
            throw se;
        }
    }
    public String editorRejection(int deal_id)throws Exception{
       // DataStorage data = new SQlStorage();
        try{
            data.rejectDeal(deal_id);
            return "welcomeEditor";
        }catch(Exception se){
            se.printStackTrace();
            throw se;
        }
    }
    public ArrayList<Feedback> displayTestimonials(){
        return data.fecthFeedback();
    }
   public void logout() {
	    	FacesContext context = FacesContext.getCurrentInstance();
	    	context.getExternalContext().invalidateSession();
	        try {
				context.getExternalContext().redirect("login.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }    
}
