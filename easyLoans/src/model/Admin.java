
package model;


public class Admin extends Usuario{
    
    private String user;
    private String password;

    public Admin(String user, String password, String nombre, String apPaterno, String apMaterno) {
        super(nombre, apPaterno, apMaterno);
        this.user = user;
        this.password = password;
    }
    
    //getters y setters

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    
}
