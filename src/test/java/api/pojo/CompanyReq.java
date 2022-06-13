package api.pojo;


public class CompanyReq {
    private String company_name;
    private String company_type;
    private String[] company_users;
    private String email_owner;

    public CompanyReq() {
    }

    public CompanyReq(String company_name, String company_type, String[] company_users, String email_owner) {
        this.company_name = company_name;
        this.company_type = company_type;
        this.company_users = company_users;
        this.email_owner = email_owner;
    }

    public String[] getCompany_users() {
        return company_users;
    }

    public void setCompany_users(String[] company_users) {
        this.company_users = company_users;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_type() {
        return company_type;
    }

    public void setCompany_type(String company_type) {
        this.company_type = company_type;
    }

    public String getEmail_owner() {
        return email_owner;
    }

    public void setEmail_owner(String email_owner) {
        this.email_owner = email_owner;
    }
}
