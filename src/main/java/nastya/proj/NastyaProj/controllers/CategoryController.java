package nastya.proj.NastyaProj.controllers;

import lombok.RequiredArgsConstructor;
import nastya.proj.NastyaProj.models.Category;
import nastya.proj.NastyaProj.services.CategoryService;
import nastya.proj.NastyaProj.services.ImageForWorkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final ImageForWorkService imageForWorkService;

    @GetMapping("/category")
    public String showCategoryList(Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);

        return "categories";
    }

    @GetMapping("/category/{id}")
    public String showTypeOfCategoryList(@PathVariable Long id, Model model) {
        Category categoryWithTypeList = categoryService.findById(id).get();
        model.addAttribute("category", categoryWithTypeList);
        return "type-of-category";
    }

    @GetMapping("/creating-label/{id}")
    public String showWorkspace(@PathVariable Long id, Model model) {
        model.addAttribute("images", imageForWorkService.findAllWithId(id));
        return "work-page";
    }
}
