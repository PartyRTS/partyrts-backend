package utils;

import org.springframework.messaging.handler.annotation.Payload;

import javax.validation.Constraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Email(message = "Please provide a valid email address")
@Pattern(regexp = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message = "Please provide a valid email address")
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface ExtendedEmail {
    String message() default "Please provide a valid email address";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
