package ivan.polahniuk.ivanLogos.entity;

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

    private Date date;

    private Long reviews = 0L;

    @ManyToOne
    private SubCategory subCategory;

    @ManyToOne
    private City city;

    @ManyToOne
    private User user;

    private String mainImg;

    @ManyToMany(mappedBy = "favorite")
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<Photo> photos = new ArrayList<>();

}
