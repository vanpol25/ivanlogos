package ivan.polhniuk.ivanlogos.entity;

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

    private String name;

    private String email;

    private String password;

    private String phone_number;

    @OneToMany(mappedBy = "user")
    private List<Product> products = new ArrayList<>();

    @ManyToMany
    private List<Product> favorite = new ArrayList<>();

}
