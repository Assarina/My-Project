package int221.sit.or1.entites;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "announcement")
public class Announce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcementId")
    private Integer id;

    @Column(name = "announcementTitle" ,nullable = false , length = 200)
    private String announcementTitle;

    @Column(name = "announcementDescription" ,nullable = false , length = 10000)
    private String announcementDescription;

    @Column(name = "publishDate")
    private ZonedDateTime publishDate;

    @Column(name = "closeDate")
    private ZonedDateTime closeDate;

    @Column(name = "announcementDisplay" ,nullable = false , length = 1)
    private String announcementDisplay;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "categories_categoryId", referencedColumnName = "categoryId")
    private Category announcementCategory ;


}
