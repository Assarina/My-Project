package int221.sit.or1.entites;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "category")

public class Category {
    @Id
    @Column(name = "categoryId" , nullable = false)
    private Integer categoryId;

    @Column(name = "categoryName" , nullable = false, length = 50)
    private String categoryName;

    @OneToMany(mappedBy = "announcementCategory")
    private List<Announce> announces;
}
