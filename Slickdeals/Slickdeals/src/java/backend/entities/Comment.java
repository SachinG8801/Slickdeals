/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.entities;

import java.sql.Date;

/**
 *
 * @author sandeep
 */
public class Comment{
    private int deal_id;
    private String message;
    private String user_id;
    private Date reply_date;

    public Comment(int deal_id, String message, String user_id, Date reply_date) {
        this.deal_id = deal_id;
        this.message = message;
        this.user_id = user_id;
        this.reply_date = reply_date;
    }

    public Comment() {
    }

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
    
    
}
