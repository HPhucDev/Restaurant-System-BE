package vn.com.foodsystem.business.ipml;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class FileStorageService {

    private static final Logger logger = LoggerFactory.getLogger(FileStorageService.class);

    public void uploadFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {

        logger.info("------------------ FileStorageService - uploadFile START -----------------------------");

        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new IOException("Could not save image file: " + fileName, ex);
        }

        logger.info("------------------ FileStorageService - uploadFile END -----------------------------");
    }

    /**
     * Delete file and its folder, set fileFolderDirectory = null in case file doesn't separate folder
     */
    public void deleteFileAndFolder(String fileFolderDirectory, String fileDirectory) {
        try {
            Path filePath = Paths.get(fileDirectory);
            Files.delete(filePath);
            if (fileFolderDirectory != null) {
                Path fileFolderPath = Paths.get(fileFolderDirectory);
                Files.delete(fileFolderPath);
            }
            logger.info("------------ DELETE user avatar successfull! ------------");
        } catch (IOException e) {
            logger.info("{}", e.getMessage());
        }
    }

    public void delete(String filename) {
        try {
            boolean result = Files.deleteIfExists(Paths.get(filename));
            if (result) {
                logger.info("{} - File is deleted!", filename);
            } else {
                logger.info("Sorry, unable to delete the file {}", filename);
            }
        } catch (IOException e) {
            logger.info("{}", e.getMessage());
        }

    }

    public boolean buildAvatarFromUrl(String folderDirectory, String pictureUrl, String fileName) {
        boolean buildResult = false;
        try (InputStream inputStream = new URL(pictureUrl).openStream()) {
            Path uploadPath =  Paths.get(folderDirectory);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Files.copy(inputStream, uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            buildResult = true;
        } catch (IOException e) {
            logger.error("{}", e.getMessage());
        }
        return buildResult;
    }

}
