package web.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="role")
    private String role;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "role"),
            inverseJoinColumns = @JoinColumn(name = "user_crud_id")
    )
    private List<User> usersList;

    public Role() {

    }

    public Role(String role) {
        this.role = role;
    }

    public void  addUserToRolen(User user){
        if(usersList == null){
            usersList = new ArrayList<>();
        }
        usersList.add(user);
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public List<User> getUsersList() {
        return usersList;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role=" + role +
                '}';
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
