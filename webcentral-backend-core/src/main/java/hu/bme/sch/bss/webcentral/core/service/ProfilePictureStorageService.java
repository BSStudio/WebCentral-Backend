package hu.bme.sch.bss.webcentral.core.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public final class ProfilePictureStorageService {

    private static final String DIRECTORY_CREATED = "Directory created at {}";
    private static final String FAILED_TO_STORE_EMPTY = "Failed to store empty file ";
    private static final String CAN_NOT_STORE_OUTSIDE_DIRECTORY = "Cannot store file with relative path outside current directory ";
    private static final Integer IMG_SIZE = 1000;
    public static final String IMAGE_RATIO_IS_NOT_1_BY_1 = "Image ratio is not 1:1";

    private final Logger logger;
    private final Path rootLocation = Paths.get(URI.create("file:///C:/public/"));

    public ProfilePictureStorageService(final Logger logger) {
        this.logger = logger;
    }

    @PostConstruct
    public void init() {
        try {
            logger.info(DIRECTORY_CREATED, rootLocation);
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }

    public String storeImage(final MultipartFile file) {
        final String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            if (file.isEmpty()) {
                throw new RuntimeException(FAILED_TO_STORE_EMPTY + filename);
            }
            if (filename.contains("..")) {
                throw new RuntimeException(CAN_NOT_STORE_OUTSIDE_DIRECTORY + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, rootLocation.resolve(filename), StandardCopyOption.ATOMIC_MOVE);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file: " + filename + ". It might be an invalid image format.", e);
        }
        return "";
    }

    private double imageRatio(final InputStream inputStream) throws IOException {
        final double height = ImageIO.read(inputStream).getHeight();
        final double width = ImageIO.read(inputStream).getWidth();
        return width / height;
    }

}
