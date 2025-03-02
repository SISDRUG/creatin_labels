package nastya.proj.NastyaProj.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Text cannot be empty")
    @Column(name = "text", nullable = false)
    private String text;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
