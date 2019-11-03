package hu.bme.sch.bss.webcentral.core.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.function.ThrowingSupplier;

import org.mockito.Mock;

import org.slf4j.Logger;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.MockitoAnnotations.initMocks;

final class FileStoreServiceTest {
    @Mock
    private Logger mockLogger;

    private FileStoreService underTest;

    @BeforeEach
    void init() {
        initMocks(this);
        underTest = spy(new FileStoreService(mockLogger));
    }

    @Test
    void testStoreImageTrowsExceptionToEmptyFile() {
        // GIVEN
        final MultipartFile file = mock(MultipartFile.class);
        given(file.isEmpty()).willReturn(true);

        // WHEN
        final Executable testSubject = () -> underTest.storeImage(file);

        // THEN
        assertThrows(RuntimeException.class, testSubject);
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
