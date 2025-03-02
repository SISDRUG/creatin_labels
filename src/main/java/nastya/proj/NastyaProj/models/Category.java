package nastya.proj.NastyaProj.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Text cannot be empty")
    @Column(name = "text", nullable = false, length = 500)
    private String text;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private List<TypeOfCategory> typesOfCategory;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", nullable = false)
    private Image image;

    public void addToCategory(TypeOfCategory typeOfCategory) {
        typesOfCategory.add(typeOfCategory);
    }
}
