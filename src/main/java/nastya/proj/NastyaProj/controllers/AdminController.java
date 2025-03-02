package nastya.proj.NastyaProj.controllers;

import lombok.RequiredArgsConstructor;
import nastya.proj.NastyaProj.DTO.ImageForWorkDTO;
import nastya.proj.NastyaProj.DTO.TypeOfCategoryDTO;
import nastya.proj.NastyaProj.models.Category;
import nastya.proj.NastyaProj.models.ImageForWork;
import nastya.proj.NastyaProj.models.TypeOfCategory;
import nastya.proj.NastyaProj.services.CategoryService;
import nastya.proj.NastyaProj.services.ImageForWorkService;
import nastya.proj.NastyaProj.services.TypeOfCategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {

    private final CategoryService categoryService;
    private final TypeOfCategoryService typeOfCategoryService;
    private final ImageForWorkService imageForWorkService;

    @GetMapping
    public String showAdminPanel(Model model) {
        return "admin/admin";
    }

    @GetMapping("/category")
    public String addCategory() {
        return "admin/category";
    }

    @PostMapping("/category")
    public String createCategory(@RequestParam("file") MultipartFile file, Category category, Model model) {
        categoryService.createCategory(category, file);
        return "redirect:/admin/category";
    }

    @GetMapping("/type-of-category")
    public String addTypeOfCategory(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "admin/type-of-category";
    }

    @PostMapping("/type-of-category")
    public String createTypeOfCategory(@RequestParam("file") MultipartFile file, TypeOfCategoryDTO typeOfCategoryDTO, Model model) {
        typeOfCategoryService.createTypeOfCategory(typeOfCategoryDTO, file);
        return "redirect:/admin/type-of-category";
    }

    @GetMapping("/image-for-work")
    public String addImageForWork(Model model) {
        model.addAttribute("typesOfCategory", typeOfCategoryService.findAll());
        return "admin/image-for-work";
    }

    @PostMapping("/image-for-work")
    public String createImageForWork(@RequestParam("file") MultipartFile file, ImageForWorkDTO imageForWorkDTO, Model model) {
        imageForWorkService.createImageForWork(imageForWorkDTO, file);
        return "redirect:/admin/type-of-category";
    }
}
