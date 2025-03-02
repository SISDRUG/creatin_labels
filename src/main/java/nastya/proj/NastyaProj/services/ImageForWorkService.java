package nastya.proj.NastyaProj.services;

import lombok.RequiredArgsConstructor;
import nastya.proj.NastyaProj.DTO.ImageForWorkDTO;
import nastya.proj.NastyaProj.models.ImageForWork;
import nastya.proj.NastyaProj.models.TypeOfCategory;
import nastya.proj.NastyaProj.repositories.ImageForWorkRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageForWorkService {

    private final ImageForWorkRepository imageForWorkRepository;
    private final TypeOfCategoryService typeOfCategoryService;
    private final ImageService imageService;

    public void createImageForWork(ImageForWorkDTO imageForWorkDTO, MultipartFile file) {
        TypeOfCategory typeOfCategory = typeOfCategoryService.findById(imageForWorkDTO.getTypeOfCategoryId()).get();
        ImageForWork imageForWork = new ImageForWork();
        imageForWork.setTypeOfCategory(typeOfCategory);
        imageForWork.setImage(imageService.saveImage(file));
        imageForWorkRepository.save(imageForWork);
    }

    public List<ImageForWork> findAllWithId(Long id) {
        return imageForWorkRepository.findAllByTypeOfCategoryId(id);
    }
}
