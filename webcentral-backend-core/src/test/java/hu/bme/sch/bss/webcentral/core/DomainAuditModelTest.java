package hu.bme.sch.bss.webcentral.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertNull;

final class DomainAuditModelTest {

    private static DomainAuditModel mockDomainAuditModel;

    @BeforeEach
    void init() {
        mockDomainAuditModel = Mockito.mock(
            DomainAuditModel.class,
            Mockito.CALLS_REAL_METHODS
        );
    }

    @Test
    void testGetCreatedAt() {
        assertNull(mockDomainAuditModel.getCreatedAt());
    }

    @Test
    void testGetUpdatedAt() {
        assertNull(mockDomainAuditModel.getUpdatedAt());
    }

}
