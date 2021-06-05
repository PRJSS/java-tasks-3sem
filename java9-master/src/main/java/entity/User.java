package entity;

import java.io.Serializable;

public class User implements Serializable, Identifiable {

    private static final long serialVersionUID = -5363773994153628499L;

    private int id;
    private String login = "";
    private String name = "";
    private String password = "";
    private String email = "";
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int hashCode(){
        return id;
    }

    public boolean equals (Object obj){
        if(this == obj)
            return true;

        if(obj == null)
            return false;

        if(getClass() != obj.getClass())
            return false;

        User other = (User) obj;

        if (id != other.id)
            return false;

        return true;
    }
}