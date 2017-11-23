package gerenciadordesenhas;

public class Conta {
    String id;
    String username;
    String password;
    Hash hash;
    
    boolean isNew;
    
    boolean editable;
    
    
    public Conta(String _id, String _username, String _password) {
        id = _id;
        username = _username;
        password = _password;
        hash = new Hash(password);
        editable = false;
        
        isNew = false;
    }
    
    // Getters
    public String getId() {
        return id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getHashValue() {
        return hash.getHash();
    }
    
    public boolean isEditable() {
        return editable;
    }
 
    
    
    // Setters
    public void setId(String _id) {
        id = _id;
    }
    
    public void setUsername(String _username) {
        username = _username;
    }
    
    public void setPassword(String _password) {
        password = _password;
        hash = new Hash(password);
    }
    
    public void setEditable() {
        editable = true;
    }
    
    public void setUneditable() {
        editable = false;
    }
    
    public void setNew(boolean b) {
        isNew = b;
    }
    
    public boolean isNew() {
        return isNew;
    }
    
}
