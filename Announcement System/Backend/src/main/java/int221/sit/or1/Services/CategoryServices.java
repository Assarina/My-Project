package int221.sit.or1.Services;

import int221.sit.or1.entites.Category;
import int221.sit.or1.repositories.CategoryRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;

@Service

public class CategoryServices {
    @Autowired
    private CategoryRepositories categoryRepositories;

    public Category getCategoryById(Integer id){
        return categoryRepositories.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Category ID:" + "DOES NOT EXIST !!!"));
    }
}
