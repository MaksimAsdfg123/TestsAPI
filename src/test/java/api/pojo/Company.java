package api.pojo;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class Company extends Root{
    private String name;
    private String type;
    private String inn;
    private String ogrn;
    private String kpp;
    private String phone;
    private String adress;
    private ArrayList<String> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
