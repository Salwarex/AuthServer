package ru.trifonov.users;



public class User {
    private final long id;
    private String username;
    private String hashcode;
    private int access;

    public User(long _id, String _username, String _hashcode, int _access){
        this.id = _id;
        this.username = _username;
        this.hashcode = _hashcode;
        this.access = _access;
    }

    public boolean isProvidedPasswordCorrectly(String password){


        return false;
    }

    public void setUsername(String newUsername){
        if(newUsername == null) return;
        this.username = newUsername;

    }

    public Long getId(){ return this.id; }
    public String getUsername(){ return this.username; }
    public String getHashcode(){ return this.hashcode; }
    public int getAccess(){ return this.access; }
}
