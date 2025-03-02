package nastya.proj.NastyaProj.services;

import lombok.RequiredArgsConstructor;
import nastya.proj.NastyaProj.DTO.TypeOfCategoryDTO;
import nastya.proj.NastyaProj.models.TypeOfCategory;
import nastya.proj.NastyaProj.repositories.TypeOfCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TypeOfCategoryService {

    private final CategoryService categoryService;
    private final TypeOfCategoryRepository typeOfCategoryRepository;

    public void createTypeOfCategory(TypeOfCategoryDTO typeOfCategoryDTO, MultipartFile file) {
        categoryService.updateCategory(typeOfCategoryDTO, file);
    }

    public List<TypeOfCategory> findAll() {
        return typeOfCategoryRepository.findAll();
    }

    public Optional<TypeOfCategory> findById(Long id) {
        return typeOfCategoryRepository.findById(id);
    }
}
