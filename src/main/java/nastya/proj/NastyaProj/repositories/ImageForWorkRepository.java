package nastya.proj.NastyaProj.repositories;

import nastya.proj.NastyaProj.models.ImageForWork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageForWorkRepository extends JpaRepository<ImageForWork, Long> {
    List<ImageForWork> findAllByTypeOfCategoryId(Long id);
}
