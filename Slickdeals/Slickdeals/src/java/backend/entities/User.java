/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.entities;


public class User {
    
    private String id;
    private String password;
    private  String type;  

    public User(String String, String password, String type) {
        this.id = id;
        this.password = password;
        this.type = type;
    }

    public User() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
    public String getType() {
        return type;
    }
}
