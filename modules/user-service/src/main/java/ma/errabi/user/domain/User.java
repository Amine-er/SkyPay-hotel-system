package ma.errabi.user.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users", schema = "schema_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;
    private String address;
}
