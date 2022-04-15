package api.registration;

public class UnSuccessUserReg extends Register {
    private String error;

    public String getError() {
        return error;
    }

    public String getEmail(){
        return super.getEmail();
    }

    public void setEmail(String email){
        super.setEmail(email);
    }

    public String getPassword(){
        return super.getPassword();
    }

    public void setPassword(String password){
        super.setPassword(password);
    }
}
