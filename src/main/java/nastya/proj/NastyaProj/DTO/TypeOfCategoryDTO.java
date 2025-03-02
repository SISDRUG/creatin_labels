package nastya.proj.NastyaProj.DTO;

import lombok.Data;
import nastya.proj.NastyaProj.models.Category;

@Data
public class TypeOfCategoryDTO {

    private Long categoryId;
    private String title;
    private String text;
}
