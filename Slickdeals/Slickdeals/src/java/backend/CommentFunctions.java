/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import backend.database.DataStorage;
import backend.database.SQlStorage;
import backend.entities.Comment;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author sandeep
 */
@ManagedBean
@SessionScoped
public class CommentFunctions{
     private int deal_id;
    private String message;
    private String user_id;
    private Date reply_date;
    private DataStorage data;
    private ArrayList<Comment> comments =new ArrayList<Comment>();
    public int getDeal_id() {
        return deal_id;
    }

    public void setDeal_id(int deal_id) {
        this.deal_id = deal_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Date getReply_date() {
        return reply_date;
    }

    public void setReply_date(Date reply_date) {
        this.reply_date = reply_date;
    }

    public CommentFunctions() {
    }

    public CommentFunctions(int deal_id, String message, String user_id, Date reply_date) {
        this.deal_id = deal_id;
        this.message = message;
        this.user_id = user_id;
        this.reply_date = reply_date;
    }
    public String submitComment() throws Exception{
         DataStorage data = new SQlStorage();
         try{
            data.comment(deal_id, message, user_id);
             return "welcome";
         }catch(Exception ex){
             ex.printStackTrace();
             throw ex;
         }
    }
}
