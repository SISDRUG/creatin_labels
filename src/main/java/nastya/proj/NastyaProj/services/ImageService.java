package nastya.proj.NastyaProj.services;

import lombok.RequiredArgsConstructor;
import nastya.proj.NastyaProj.models.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ImageService {

    private static final Logger log = LoggerFactory.getLogger(ImageService.class);
    private final String UPLOAD_DIR = "target/classes/static/uploads/";
    private final String PATH = "/uploads/";

    public Image saveImage(MultipartFile file) {
        Path uploadPath = Paths.get(UPLOAD_DIR);
        try {
            // Создаем директорию и все родительские папки
            Files.createDirectories(uploadPath);
        } catch (IOException e) {
            log.info("Директория уже создана!");
        }

        LocalDateTime now = LocalDateTime.now();

        // Определяем формат без двоеточий
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");

        // Форматируем текущее время
        String formattedDateTime = now.format(formatter);
        String name = formattedDateTime + file.getOriginalFilename();
        try {
            // Сохраняем файл
            Path filePath = uploadPath.resolve(name);
            Files.write(filePath, file.getBytes());
        } catch (IOException e) {
            log.info("Произошла ошибка при сохранении изображения!");
        }


        Image image = new Image();
        image.setFileName(name);
        image.setPath(PATH);

        return image;

    }
}
