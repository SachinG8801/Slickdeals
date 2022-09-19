/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.database;

import backend.DealFunctions;
import backend.UserAccount;
import backend.entities.Comment;
import backend.entities.Deal;
import backend.entities.Feedback;
import backend.entities.User;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author sandeep
 */

public interface DataStorage {
    
      ArrayList<Deal> fetchDealList();
    
    public void saveDeal
    (
        int dealId, 
        String title, 
        String description, 
        Date dateCreated, 
        int rating, 
        String img_url, 
        String status, 
        int price, 
        String userID
    );
    
    public void registerUser(String id, String pwd);
    public String login(String id, String pwd);
    ArrayList<Deal> fetchDeal(int dealId);
     void ratingPlusOne(int dealId);
     public void comment(int deal_id,String message,String user_id);
     void ratingMinusOne(int dealId); 
     ArrayList<Comment> fetchComments(int deal_id);
     ArrayList<Deal> fetchDealsAboveTwo();
     ArrayList<Deal> reviewList();
     void approveDeal(int dealId);
     void rejectDeal(int dealId);
     public String createFeedback(String user_id,String message,String experience);
     public ArrayList<Feedback> fecthFeedback();
}
