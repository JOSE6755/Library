package com.library.library.util;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.stream.Collectors;

@Component
public class SortPropertyValidator {

    public static boolean validate(final String propertyName, Class<?> clazz)
    {
        try {
            clazz.getDeclaredField(propertyName);
            return true;

        } catch (NoSuchFieldException e) {
            return false;
        }
    }

}
