package hu.bme.sch.bss.webcentral;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import javax.validation.ConstraintViolation;
import java.util.Set;

public final class WcTestUtil {

    private WcTestUtil() {
        throw new UnsupportedOperationException();
    }

    public static <T> void thenValidationFails(final Set<ConstraintViolation<T>> violations,
                                               final String expectedMessage, final String expectedProperty) {
        assertThat(violations.size(), is(1));
        violations.stream()
                .findFirst()
                .ifPresent(constraintViolation -> {
                    assertThat(constraintViolation.getMessage(), is(expectedMessage));
                    assertThat(constraintViolation.getPropertyPath().toString(), is(expectedProperty));
                });
    }

}
