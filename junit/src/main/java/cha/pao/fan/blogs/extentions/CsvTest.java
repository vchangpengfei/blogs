package cha.pao.fan.blogs.extentions;

import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by pfchang
 * on 2020/2/28.
 */
@TestTemplate
@ExtendWith(CsvProvider.class)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CsvTest {
    String[] value() default {};
}
