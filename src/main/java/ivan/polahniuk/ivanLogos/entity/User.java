package ivan.polahniuk.ivanLogos.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private UserRole userRole;

    @Column(name = "email", unique = true)
    private String email;

    private String phoneNumber;

    @OneToMany(mappedBy = "user")
    private List<Product> products = new ArrayList<>();

    @ManyToOne
    private City city;

    @ManyToMany
    private List<Product> favorite = new ArrayList<>();

}
