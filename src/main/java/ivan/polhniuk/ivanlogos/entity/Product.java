package ivan.polhniuk.ivanlogos.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "text")
    private String description;

    private Integer price;

    private Date date_published;

    @ManyToOne
    private SubCategory subCategory;

    @ManyToOne
    private City city;

    @ManyToOne
    private User user;

    @ManyToMany(mappedBy = "favorite")
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Photo> photos = new ArrayList<>();

}
