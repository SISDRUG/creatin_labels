package nastya.proj.NastyaProj.repositories;

import nastya.proj.NastyaProj.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findByUserId(Long id);
}
