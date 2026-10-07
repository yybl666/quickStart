package com.yblpj.quickstart.anno;

import com.yblpj.quickstart.Validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.FIELD})  //表示该注解是用在哪一方面，是属性，还是参数，还是方法等等。此为属性，pojo的属性
@Retention(RetentionPolicy.RUNTIME)  //表示注解运行时机，因为pojo类属性的校验是运行过程中就得一直存在的
@Documented  //表示可以写入到注解文档
@Constraint(
        validatedBy = {StateValidation.class}
) //这个就是联动可以实现注解的类。
public @interface State {
    //校验失败时给出的提示信息
    String message() default "文章类型只能是草稿或者已发布！！";
    //校验分组？？？
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
