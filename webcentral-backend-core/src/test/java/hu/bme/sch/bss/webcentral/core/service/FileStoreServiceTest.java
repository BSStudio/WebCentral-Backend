package hu.bme.sch.bss.webcentral.core.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.function.ThrowingSupplier;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.MockitoAnnotations.initMocks;

final class FileStoreServiceTest {

    private static final String IMAGE_NAME_PREFIX = "test_";
    private static final String IMAGE_NAME_SUFFIX = ".png";

    @TempDir
    static Path tempDirectory;
    private Path tempFile;

    @Mock
    private Logger mockLogger;

    private FileStoreService underTest;

    @BeforeEach
    void init() {
        initMocks(this);
        underTest = spy(new FileStoreService(mockLogger));
        try {
            tempFile = Files.createTempFile(tempDirectory, IMAGE_NAME_PREFIX, IMAGE_NAME_SUFFIX);
        } catch (IOException e) {
            e.printStackTrace();
        }
        underTest.setRootLocation(tempDirectory);
    }

    @Test
    void testStoreImage() {
        // GIVEN
        final MultipartFile file = mock(MultipartFile.class);
        given(file.isEmpty()).willReturn(true);

        // WHEN
        final Executable testSubject = () -> underTest.storeImage(file);

        // THEN
        assertThrows(RuntimeException.class, testSubject);
    }

    @Test
    void testDeleteFileWithNameOf() {
        // GIVEN
        final String fileName = tempFile.getFileName().toString();

        // WHEN
        final Executable testSubject = () -> underTest.deleteFileWithNameOf(fileName);

        // THEN
        assertDoesNotThrow(testSubject);
        assertFalse(Files.exists(tempFile));
    }

    @Test
    void testGetImageType() {
        // GIVEN
        final MultipartFile mockFile = mock(MultipartFile.class);
        given(mockFile.getContentType()).willReturn(MediaType.IMAGE_GIF_VALUE);

        // WHEN
        final String result = underTest.getImageType(mockFile);

        // THEN
        assertEquals("gif", result);
    }

    @Test
    void testGetImageTypeThrowsRuntimeExceptionIfItsNotAnImage() {
        // GIVEN
        final MultipartFile mockFile = mock(MultipartFile.class);
        given(mockFile.getContentType()).willReturn(MediaType.TEXT_MARKDOWN_VALUE);

        // WHEN
        final Executable testSubject = () -> underTest.getImageType(mockFile);

        // THEN
        assertThrows(RuntimeException.class, testSubject);
    }

    @Test
    void testGetImageTypeThrowsNullPointerException() {
        // GIVEN

        // WHEN
        final Executable testSubject = () -> underTest.getImageType(null);

        // THEN
        assertThrows(NullPointerException.class, testSubject);
    }

    @Test
    void testImageRatio() {
        // GIVEN
        final BufferedImage image = mock(BufferedImage.class);
        given(image.getHeight()).willReturn(500);
        given(image.getWidth()).willReturn(1000);

        // WHEN
        final ThrowingSupplier<Double> testSubject = () -> underTest.imageRatio(image);

        // THEN
        final double testResult = assertDoesNotThrow(testSubject);
        assertEquals(0.5, testResult);
    }

    @Test
    void testImageRatioReturnsWithNumberSmallerThenOne() {
        // GIVEN
        final BufferedImage image = mock(BufferedImage.class);
        given(image.getHeight()).willReturn(1000);
        given(image.getWidth()).willReturn(500);

        // WHEN
        final ThrowingSupplier<Double> testSubject = () -> underTest.imageRatio(image);

        // THEN
        final double testResult = assertDoesNotThrow(testSubject);
        assertEquals(0.5, testResult);
    }

}
