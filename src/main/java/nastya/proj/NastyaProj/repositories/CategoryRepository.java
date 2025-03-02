package nastya.proj.NastyaProj.repositories;

import nastya.proj.NastyaProj.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
