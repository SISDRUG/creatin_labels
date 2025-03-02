package nastya.proj.NastyaProj.services;

import lombok.RequiredArgsConstructor;
import nastya.proj.NastyaProj.DTO.TypeOfCategoryDTO;
import nastya.proj.NastyaProj.models.Category;
import nastya.proj.NastyaProj.models.TypeOfCategory;
import nastya.proj.NastyaProj.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ImageService imageService;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public void createCategory(Category category, MultipartFile file) {
        category.setImage(imageService.saveImage(file));
        categoryRepository.save(category);
    }

    public void updateCategory(TypeOfCategoryDTO typeOfCategoryDTO, MultipartFile file) {
        Category category = findById(typeOfCategoryDTO.getCategoryId()).get();
        TypeOfCategory typeOfCategory = new TypeOfCategory();
        typeOfCategory.setTitle(typeOfCategoryDTO.getTitle());
        typeOfCategory.setText(typeOfCategoryDTO.getText());
        typeOfCategory.setImage(imageService.saveImage(file));
        category.addToCategory(typeOfCategory);
        categoryRepository.save(category);
    }
}
