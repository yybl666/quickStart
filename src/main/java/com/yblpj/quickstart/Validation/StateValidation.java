package com.yblpj.quickstart.Validation;

import com.yblpj.quickstart.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

//public class StateValidation implements ConstraintValidator<给哪一个注解进行规则实现，注解对象的类型>
public class StateValidation implements ConstraintValidator<State,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s==null){
            return false;
        }
        if(s.equals("草稿") || s.equals("已发布")){
            return true;
        }
        return false;
    }
}
