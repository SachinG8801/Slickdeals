/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.entities;

/**
 *
 * @author sandeep
 */
public class Feedback {
    private String user_id;
    private String message;
    private String experience;

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

    public Feedback(String user_id, String message, String experience) {
        this.user_id = user_id;
        this.message = message;
        this.experience = experience;
    }

    public Feedback() {
    }
    
}
