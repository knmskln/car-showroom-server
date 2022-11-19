package com.bsuir.server.util.validator;

public class CarInformationValidator {

    private static final CarInformationValidator INSTANCE = new CarInformationValidator();

    public static CarInformationValidator getInstance() {
        return INSTANCE;
    }

    private CarInformationValidator() {
    }

    private static final String CAR_NAME_FORMAT = ".{2,20}";

    public boolean validate(String mark,
                            String model,
                            String color,
                            Integer year,
                            Integer price) {
        return mark.matches(CAR_NAME_FORMAT) &&
                model.matches(CAR_NAME_FORMAT) &&
                color.matches(CAR_NAME_FORMAT) &&
                year > 1950 && year < 2023 &&
                price > 0 && price < 150000;
    }
}