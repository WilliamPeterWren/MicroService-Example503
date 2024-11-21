package com.trannubichthai.product_service.util;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.trannubichthai.product_service.enumeration.Sort;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class StringToEnumConverter implements Converter<String, Sort> {

    @Override
    public Sort convert(String source) throws ConversionFailedException {
        // Convert camel case to uppercase with underscores (e.g., "DATE_DESC" from
        // "DateDesc")
        String underscore = Arrays.stream(source.split("(?=[A-Z])"))
                .map(String::toUpperCase)
                .collect(Collectors.joining("_"));

        // Return the corresponding Enum value
        return Sort.valueOf(underscore);
    }
}
