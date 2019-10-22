package hu.bme.sch.bss.webcentral;

import hu.bme.sch.bss.webcentral.core.DomainAuditModel;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public final class WcTestUtil {

    private WcTestUtil() {
        throw new UnsupportedOperationException();
    }

    public static <T extends DomainAuditModel> void thenValidationFails(Set<ConstraintViolation<T>> violations,
                                     String expectedMessage, String expectedProperty) {
        assertThat(violations.size(), is(1));
        ConstraintViolation<T> violation = violations.stream().findFirst().get();
        assertThat(violation.getMessage(), is(expectedMessage));
        assertThat(violation.getPropertyPath().toString(), is(expectedProperty));
    }
}
