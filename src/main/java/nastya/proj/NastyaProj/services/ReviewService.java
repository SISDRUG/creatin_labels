package nastya.proj.NastyaProj.services;

import lombok.RequiredArgsConstructor;
import nastya.proj.NastyaProj.models.Review;
import nastya.proj.NastyaProj.models.User;
import nastya.proj.NastyaProj.repositories.ReviewRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public boolean createReview(Review review) {
        User user = getAuthenicatedUser();

        if (user == null) return false;
        if (isExistReview(user)) return false;

        review.setUser(user);
        reviewRepository.save(review);

        return true;
    }

    private boolean isAuthenticateUser(Authentication authentication) {
        return authentication != null && authentication.isAuthenticated();
    }

    private User getAuthenicatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (isAuthenticateUser(authentication)){
            return (User) authentication.getPrincipal();
        }

        return null;
    }

    private boolean isExistReview(User user) {
        Review review = reviewRepository.findByUserId(user.getId());
        return review != null;
    }
}
