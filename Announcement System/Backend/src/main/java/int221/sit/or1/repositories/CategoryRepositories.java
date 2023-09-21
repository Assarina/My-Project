package int221.sit.or1.repositories;

import int221.sit.or1.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepositories extends JpaRepository<Category,Integer> {
  Category findCategoryByCategoryName(String Category);
}