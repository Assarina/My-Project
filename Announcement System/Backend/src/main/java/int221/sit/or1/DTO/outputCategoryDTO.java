package int221.sit.or1.DTO;

import int221.sit.or1.entites.Category;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class outputCategoryDTO {
    private Integer id;
    private Category announcementCategory;
    private String categoryName;
}
