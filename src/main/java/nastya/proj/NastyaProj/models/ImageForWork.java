package nastya.proj.NastyaProj.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "images_for_work")
public class ImageForWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", nullable = false)
    private Image image;

    @ManyToOne
    @JoinColumn(name = "type_of_category_id", nullable = false)
    private TypeOfCategory typeOfCategory;
}
