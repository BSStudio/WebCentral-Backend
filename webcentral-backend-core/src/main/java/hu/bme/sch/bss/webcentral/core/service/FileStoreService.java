package hu.bme.sch.bss.webcentral.core.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public final class FileStoreService {

    private static final String DIRECTORY_CREATED = "Directory created at {}";
    private static final String FAILED_TO_STORE_EMPTY = "Failed to store empty file ";
    private static final String IMAGE_RATIO_IS_NOT_1_BY_1 = "Image ratio is not 1:1";
    private static final String FILE_DELETED_WITH_NAME = "File deleted with name {}";

    @Value("${wc.storageLocation}") //TODO make it work
    private String storageLocation = "C:/public/";
    private Path rootLocation = Path.of(storageLocation);

    private final Logger logger;

    FileStoreService(final Logger logger) {
        this.logger = logger;
    }

    void setRootLocation(final Path rootLocation) {
        this.rootLocation = rootLocation;
    }

    @PostConstruct
    void init() {
        try {
            logger.info(DIRECTORY_CREATED, rootLocation);
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }

    public String storeImage(final MultipartFile file) throws IOException {
        final String fileName = UUID.randomUUID().toString() + "." + getImageType(file);
        if (file.isEmpty()) {
            throw new RuntimeException(FAILED_TO_STORE_EMPTY + file.getName());
        }
        try (InputStream inputStream = file.getInputStream()) {
            final BufferedImage image = ImageIO.read(inputStream);
            if (imageRatio(image) != 1.0) {
                throw new RuntimeException(IMAGE_RATIO_IS_NOT_1_BY_1);
            }
            Files.copy(inputStream, rootLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        }
        return fileName;
    }

    public void deleteFileWithNameOf(final String fileName) throws IOException {
        Files.deleteIfExists(rootLocation.resolve(fileName));
        logger.info(FILE_DELETED_WITH_NAME, fileName);
    }

    String getImageType(final MultipartFile file) {
        final String context = Objects.requireNonNull(file.getContentType());
        final String[] contentTypeInfo = context.split("/");
        if (!contentTypeInfo[0].equals("image")) {
            throw new RuntimeException();
        }
        return contentTypeInfo[1];
    }

    double imageRatio(final BufferedImage image) {
        final double height = image.getHeight();
        final double width = image.getWidth();
        if (width > height) {
            return height / width;
        } else {
            return width / height;
        }
    }

}
