package backend;

import backend.UserAccount;
import backend.database.DataStorage;
import backend.database.SQlStorage;
import backend.entities.Deal;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Sandeep Sagar
 */
@ManagedBean
@SessionScoped
public class UserFunctions implements Serializable{

    private String id;
    private String password;
    private String type;
    private UserAccount userAccount;
    private CommentFunctions commentFunctions;
    private FeedbackFunctions feedbackFunctions;
    //get methods and set methods
    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public CommentFunctions getCommentFunctions() {
        return commentFunctions;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void registerUser(){
            DataStorage data=null;
        try {
            data = new SQlStorage();
        } catch (Exception ex) {
            Logger.getLogger(UserFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            
            data.registerUser(
                id, 
                password
            );
            
            System.out.println("Saved the User with Id " + id);
        }
        catch(Exception ex)
        {
            Logger.getLogger(RuntimeException.class.getName())
                    .log(Level.SEVERE, "Faled to register the user", ex);
        }
    }
    
    public String login() throws Exception
    {
        //load the Driver
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //return to internalError.xhtml
            return ("internalError");
        }
        
        DataStorage data = new SQlStorage();
        
        String fileName = data.login(id, password);
        
        if(fileName.equals("welcome"))
        {
           userAccount= new UserAccount(id, password);
           userAccount.setData(data);
           userAccount.showAllThreads();
            return "welcome";
        }else if(fileName.equals("editor")){
           userAccount= new UserAccount(id, password);
           userAccount.setData(data);
           userAccount.showAllThreads();
            return "welcomeEditor";
        }
        else
        {
            return fileName;
        }
          
    } 
}
