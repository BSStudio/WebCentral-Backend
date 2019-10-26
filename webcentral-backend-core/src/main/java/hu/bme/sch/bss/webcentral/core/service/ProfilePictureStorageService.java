package hu.bme.sch.bss.webcentral.core.service;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;

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

@Service
public final class ProfilePictureStorageService {

    private final static String FAILED_TO_STORE_EMPTY = "Failed to store empty file ";
    private final static String CAN_NOT_STORE_OUTSIDE_DIRECTORY = "Cannot store file with relative path outside current directory ";

    private final Logger logger;
    private final Path rootLocation = Paths.get(URI.create("file:///C:/public/"));

    public ProfilePictureStorageService(final Logger logger) {
        this.logger = logger;
    }

    public void storeImage(final MultipartFile file) {
        final String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            if (file.isEmpty()) {
                throw new RuntimeException(FAILED_TO_STORE_EMPTY + filename);
            }
            if (filename.contains("..")) {
                throw new RuntimeException(CAN_NOT_STORE_OUTSIDE_DIRECTORY + filename);
            }
            try (final InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, rootLocation.resolve(filename), StandardCopyOption.ATOMIC_MOVE);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file: " + filename + ". It might be an invalid image format.", e);
        }
    }

    public Image checkImagePropertiesAndScale(final InputStream inputStream) {
        try {
            final BufferedImage image = ImageIO.read(inputStream);
                return image.getScaledInstance(1000, 1000, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }

}
