package nastya.proj.NastyaProj.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "types_of_category")
public class TypeOfCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @NotBlank(message = "Text cannot be empty")
    @Column(name = "text", nullable = false, length = 500)
    private String text;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", nullable = false)
    private Image image;
}
