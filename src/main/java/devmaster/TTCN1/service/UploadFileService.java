package devmaster.TTCN1.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class UploadFileService {
    // tham khảo cách upload file
    // https://www.youtube.com/watch?v=WFrczTORsWE
    // https://spring.io/guides/gs/uploading-files

    // nơi lưu
    private final Path rootLocation;

    public UploadFileService(){
        this.rootLocation = Paths.get("E:\\Documents\\SpringBoot\\TTCN1\\src\\main\\resources\\static\\product_image");
    }

    public void store(MultipartFile file) {
        try {

            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
