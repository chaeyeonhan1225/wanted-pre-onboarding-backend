package com.wanted.wantedpreonboardingbackend.infrastructure;

import com.wanted.wantedpreonboardingbackend.domain.common.CommonState;
import jakarta.persistence.AttributeConverter;
import org.springframework.stereotype.Component;

@Component
public class CommonStateConverter implements AttributeConverter<CommonState, Integer> {


    @Override
    public Integer convertToDatabaseColumn(CommonState attribute) {
        System.out.println("attribute = " + attribute);
        return attribute.getValue();
    }

    @Override
    public CommonState convertToEntityAttribute(Integer dbData) {
        return CommonState.parse(dbData);
    }
}
