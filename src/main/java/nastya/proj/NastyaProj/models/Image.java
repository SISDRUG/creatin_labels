package nastya.proj.NastyaProj.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "File name cannot be empty")
    @Column(name = "file_name", nullable = false, unique = true)
    private String fileName;

    @NotBlank(message = "Path cannot be empty")
    @Column(name = "path", nullable = false)
    private String path;
}
