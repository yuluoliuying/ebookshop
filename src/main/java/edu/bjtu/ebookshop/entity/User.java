package edu.bjtu.ebookshop.entity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String name;
    private String phone;
    private String pwd;

//    public User(String id, String name, String phone, String pwd) {
//        this.id=id;
//        this.name=name;
//        this.phone=phone;
//        this.pwd=pwd;
//    }
}
