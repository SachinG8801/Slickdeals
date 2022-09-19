/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import backend.database.DataStorage;
import backend.database.SQlStorage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author sandeep
 */
@ManagedBean
@SessionScoped
public class FeedbackFunctions {
    
    private String user_id;
    private String message;
    private String experience;

    public FeedbackFunctions() {
    }

    public FeedbackFunctions(String user_id, String message, String experience) {
        this.user_id = user_id;
        this.message = message;
        this.experience = experience;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
    
    public String submitFeedback() throws Exception{
        DataStorage data = new SQlStorage();    
        return  data.createFeedback(user_id, message, experience);
    }
    
}
