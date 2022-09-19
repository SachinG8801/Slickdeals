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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * Tables 
 *  1. deal_thread  - 
 *  2. forum
 *  3. reply_thread
 *  4. user - Signup, user login
 *  5. feedback
 *  6.deal review
 */
public class SQlStorage implements DataStorage {
    
    final String url = 
                "jdbc:mysql://mis-sql.uhcl.edu/muralidhars5496?useSSL=false";
    final String db_id = "muralidhars5496";
    final String db_psw = "1893894";
    final Connection conn;
    ResultSet result =null;
    Statement stat =null ;
    // Table Names
    final static String DEALS_TABLE = "deal_thread";
    final static String USER_TABLE = "user";
    
    public SQlStorage() throws Exception {
         Class.forName("com.mysql.jdbc.Driver");
        this.conn = DriverManager.getConnection(url, db_id, db_psw);
    }

    

    @Override
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
    ) 
    {
        System.out.println("Saving the Deal with DealId " + dealId + " postedb by " + userID);
        try 
        {
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            Statement stat =  conn.createStatement();
            status="new";
            final String queryString = 
                    "insert into " + DEALS_TABLE + " values "
                    + "(" 
                        + "'" + dealId + "'" + ","
                        + "'" + title + "'" +  ","
                        + "'" + description + "'" + ","
                        + "'" + formatter.format(date) + "'" +  ","
                        + "'" + rating + "'" + ","
                        + "'" + img_url + "'" +  ","
                        + "'" + status + "'" + ","
                        + "'" + price + "'" + ","
                        + "'" + userID + "'"
                    + ")";
            System.out.println("Query to be execuetd : " + queryString);
            
            stat.executeUpdate(queryString);
            
            System.out.println("Deal Creation Successful");
        } 
        catch (SQLException ex) 
        {
            System.out.println("Deal creation Failed");
            Logger.getLogger(SQlStorage.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
            //close the database
            try
            {
                conn.close();
                stat.close();
                result.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        } 
    }
    
    @Override
    public void registerUser(String id, String password)
    {
        try 
        {
             stat =  conn.createStatement();
            final String queryString = 
                    "insert into user values "
                    + "(" 
                        + "'" + id + "'" + ","
                        + "'" + password + "'" +  ","
                        + "'user'" + ")";
            stat.executeUpdate(queryString);
        }
         
        catch (SQLException ex) 
        {
            Logger.getLogger(SQlStorage.class.getName())
                    .log(Level.SEVERE, "User Registration Failed", ex);
            
        }finally
        {
            //close the database
            try
            {
                conn.close();
                stat.close();
                result.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
        
    }

    @Override
    public String login(String id, String pwd) 
    {
        ResultSet result =null;
          Statement stat =null ;
        try 
        {

            final String queryString = 
                "select * from user"
                + " where user_id = '" +  id + "'";
            
            stat =  conn.createStatement();
            
            result = stat.executeQuery(queryString);
   
           if(result.next())
            {
                //the id is found, check the password
                if(pwd.equals(result.getString("password")))
                {
                    if(result.getString("type").equalsIgnoreCase("editor")){
                        return "editor";
                    }else{
                            return "welcome";
                    }
                   
                }
                else
                {
                    return "index";
                     
                }
            }
            else
            {
                return "index";
            }
     
    } 
        catch (SQLException ex) 
        {
            System.out.println("Account creation Failed");
            Logger.getLogger(SQlStorage.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally
        {
            //close the database
            try
            {
                conn.close();
                stat.close();
                result.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public ArrayList<Deal> fetchDealList() {
        
        ArrayList<Deal> deals = new ArrayList<Deal>();
        try{
             final String queryString = 
                "select * from deal_thread";
      
         Connection conn;
             conn = DriverManager.getConnection(url, db_id, db_psw);
             stat =  conn.createStatement();    
              result = stat.executeQuery(queryString);
            while(result.next()){
               Deal deal = new Deal(result.getInt("deal_id"),result.getString("title"), result.getDate("date_created"),result.getInt("rating"), result.getFloat("price"), result.getString("user_id"));
              deals.add(deal);
              
            }
           
               return deals;
        } catch (Exception e) {
                e.printStackTrace();
                return deals;
    }finally
        {
            //close the database
            try
            {
                conn.close();
                stat.close();
                result.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        } 
    }

    @Override
    public ArrayList<Deal> fetchDeal(int dealId) {
        ArrayList<Deal> deal = new ArrayList<Deal>();
       try{
             final String queryString = 
                "select * from deal_thread where deal_id='"+dealId+"'";
     
         Connection conn;
             conn = DriverManager.getConnection(url, db_id, db_psw);
             stat =  conn.createStatement();    
              result = stat.executeQuery(queryString);
            if(result.next()){
               Deal single_deal = new Deal(result.getInt("deal_id"),result.getString("title"),result.getString("description"), result.getDate("date_created"),result.getInt("rating"),result.getString("img_url"), result.getFloat("price"), result.getString("user_id"));
              deal.add(single_deal);
            }
               return deal;
        } catch (Exception e) {
                e.printStackTrace();
                return deal;
    }finally
        {
            //close the database
            try
            {
                conn.close();
                stat.close();
                result.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        } 
    }

    @Override
    public void ratingPlusOne(int dealId) {
        try{
              String queryString = 
                "select rating from deal_thread where deal_id='"+dealId+"'";
            int rating=0;  
            Connection conn;
             conn = DriverManager.getConnection(url, db_id, db_psw);
             stat =  conn.createStatement();    
              result = stat.executeQuery(queryString);
              if(result.next()){
                  System.out.println("Rating "+result.getInt("rating")+" of"+dealId);
                   rating = result.getShort("rating");
                  rating = rating+1;
              }
              Statement statement =null ;
                  
                   String sql2 ="Update deal_thread SET rating='"+rating+"' where deal_id='"+dealId+"'";                          
                   Statement stTwo = conn.createStatement() ;
                   stTwo.execute(sql2);
                   conn.commit();
                   System.out.println("Increased");
            }catch (SQLException e) {
                e.printStackTrace();
               
    }finally
        {
            //close the database
            try
            {
                conn.close();
                stat.close();
                result.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        } 
    }

    @Override
    public void ratingMinusOne(int dealId) {
        try{
             final String queryString = 
                "select rating from deal_thread where deal_id='"+dealId+"'";
                int decreaseRating =0;
            Connection conn;
             Statement st = null;
             conn = DriverManager.getConnection(url, db_id, db_psw);
             stat =  conn.createStatement();    
              result = stat.executeQuery(queryString);
              if(result.next()){
                  decreaseRating = result.getShort("rating");
                  decreaseRating --;
              }
              Statement statement =null ;
                  
                   String sql2 ="Update deal_thread SET rating='"+decreaseRating+"' where deal_id='"+dealId+"'";                          
                   Statement stTwo = conn.createStatement() ;
                   stTwo.execute(sql2); 
            }catch (Exception e) {
                e.printStackTrace();
               
    }finally
        {
            //close the database
            try
            {
                conn.close();
                stat.close();
                result.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        } 
    }

    @Override
    public void comment(int deal_id,String message,String user_id) {
        try 
        {
            Connection conn;
             conn = DriverManager.getConnection(url, db_id, db_psw);
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            Statement stat =conn.createStatement();
           
         int r = stat.executeUpdate("insert into reply_thread values "
                    + "(" 
                        + "'" + deal_id + "'" + ","
                        + "'" + message + "'" +  ","
                        + "'" + user_id + "'" + ","
                        + "'" + formatter.format(date) + "'"                        
                    + ")");
            
            System.out.println("Commented Successfully");
        } 
        catch (SQLException ex) 
        {
            System.out.println("Failed to insert Comment");
            Logger.getLogger(SQlStorage.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
            //close the database
            try
            {
                conn.close();
                stat.close();
                result.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }        
        } 
    }

    @Override
    public ArrayList<Comment> fetchComments(int deal_id) {
         ArrayList<Comment> comment = new ArrayList<Comment>();
       try{
             final String queryString = 
                "select * from reply_thread where deal_id='"+deal_id+"'";
     
         Connection conn;
             conn = DriverManager.getConnection(url, db_id, db_psw);
             stat =  conn.createStatement();    
              result = stat.executeQuery(queryString);
            while(result.next()){
               Comment replies = new Comment(result.getInt("deal_id"),result.getString("message"),result.getString("user_id"),result.getDate("reply_date"));
              comment.add(replies);
            }
               return comment;
        } catch (Exception e) {
                e.printStackTrace();
                return comment;
    }finally
        {
            //close the database
            try
            {
                conn.close();
                stat.close();
                result.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }   
        } 
    }

    @Override
    public ArrayList<Deal> fetchDealsAboveTwo() {

        ArrayList<Deal> deals = new ArrayList<Deal>();
        try{
             final String queryString = 
                "select * from deal_thread where status ='active'";
      
         Connection conn;
             conn = DriverManager.getConnection(url, db_id, db_psw);
             stat =  conn.createStatement();    
              result = stat.executeQuery(queryString);
            while(result.next()){
               Deal deal = new Deal(result.getInt("deal_id"),result.getString("title"),result.getString("description"), result.getInt("rating"), result.getString("img_url"),result.getFloat("price"));
              deals.add(deal);    
            }
           
               return deals;
        } catch (Exception e) {
                e.printStackTrace();
                return deals;
    }finally
        {
            //close the database
            try
            {
                conn.close();
                stat.close();
                result.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }    
        } 
    }

    @Override
    public ArrayList<Deal> reviewList() {
 ArrayList<Deal> deals = new ArrayList<Deal>();
        try{
             final String queryString = 
                "select * from deal_thread where status='new' and rating >=2";
      
         Connection conn;
             conn = DriverManager.getConnection(url, db_id, db_psw);
             stat =  conn.createStatement();    
              result = stat.executeQuery(queryString);
            while(result.next()){
               Deal deal = new Deal(result.getInt("deal_id"),result.getString("title"));
              deals.add(deal);    
            }
           
               return deals;
        } catch (Exception e) {
                e.printStackTrace();
                return deals;
    }finally
        {
            //close the database
            try
            {
                conn.close();
                stat.close();
                result.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        } 
    }

    @Override
    public void approveDeal(int deal_id) {
        try{
            Connection conn;
             Statement st = null;
             conn = DriverManager.getConnection(url, db_id, db_psw);
             stat =  conn.createStatement();   
              Statement statement =null ;
                  
                   String sql2 ="Update deal_thread SET status='active' where deal_id='"+deal_id+"'";                          
                   Statement stTwo = conn.createStatement() ;
                   stTwo.execute(sql2); 
            }catch (Exception e) {
                e.printStackTrace();              
    }finally
        {
            //close the database
            try
            {
                conn.close();
                stat.close();
                result.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }       
        }     
    }

    @Override
    public void rejectDeal(int deal_id) {
    try{         
            Connection conn;
             Statement st = null;
             conn = DriverManager.getConnection(url, db_id, db_psw);
             stat =  conn.createStatement();    
                Statement statement =null ;      
                   String sql2 ="Update deal_thread SET status='rejected' where deal_id='"+deal_id+"'";                          
                   Statement stTwo = conn.createStatement() ;
                   stTwo.execute(sql2); 
            }catch (Exception e) {
                e.printStackTrace();            
    }finally
        {
            //close the database
            try
            {
                conn.close();
                stat.close();
                result.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
                }
   
    }

    @Override
    public String createFeedback(String user_id, String message, String experience) {
         try 
        {
            Connection conn;
             conn = DriverManager.getConnection(url, db_id, db_psw);
            Statement stat =conn.createStatement();
           
         int r = stat.executeUpdate("insert into feedback values "
                    + "(" 
                        + "'" + message + "'" + ","
                        + "'" + user_id + "'" +  ","
                        + "'" + experience + "')");
            
            System.out.println("Submitted feedback Successfully");
            return "feedbackSubmitted";
        } 
        catch (SQLException ex) 
        {
            System.out.println("Failed to insert feedback");
            Logger.getLogger(SQlStorage.class.getName()).log(Level.SEVERE, null, ex);
            return "welcome";
        }finally
        {
            //close the database
            try
            {
                conn.close();
                stat.close();
                result.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }        
        } 
    }

    @Override
    public ArrayList<Feedback> fecthFeedback() {
            
        ArrayList<Feedback> feedbacks = new  ArrayList<Feedback>();
        try{
             final String queryString = 
                "select * from feedback";
     
         Connection conn;
             conn = DriverManager.getConnection(url, db_id, db_psw);
             stat =  conn.createStatement();    
              result = stat.executeQuery(queryString);
            while(result.next()){
                Feedback feedback = new Feedback(result.getString("user_id"), result.getString("feedback_message"), result.getString("experience"));
                feedbacks.add(feedback);
            }
              return feedbacks;  
        } catch (Exception e) {
                e.printStackTrace();
                return feedbacks;  
    }finally
        {
            //close the database
            try
            {
                conn.close();
                stat.close();
                result.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }   
        } 
             
    }
    
}
      