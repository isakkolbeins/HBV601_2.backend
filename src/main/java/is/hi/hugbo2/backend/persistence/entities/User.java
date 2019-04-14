package is.hi.hugbo2.backend.persistence.entities;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class for the User
 * The system generates a table schema based on this class for this entity.
 * Be sure to annotate any entities you have with the @Entity annotation.
 */
@Entity
@Table(name="`User`")
public class User {
    // Declare that this attribute is the id
    @Id
    @SequenceGenerator(name = "userGenerator", sequenceName = "USER_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userGenerator")
    @Column(name = "userid", updatable = false, nullable = false)
    private Long id;

    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    // List of friends user id´s
    private ArrayList<Long> friendlist = null;

    public User() {
    }

    public User(String username, String firstname, String lastname, String email, String password) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Long> getFriendlist() { return this.friendlist; }

    public void setFriendlist(ArrayList<Long> friendlist) { this.friendlist = friendlist; }

    public Long getId() {return id;}
}