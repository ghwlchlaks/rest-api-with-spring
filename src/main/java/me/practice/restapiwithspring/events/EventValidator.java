package me.practice.restapiwithspring.events;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;

@Component
public class EventValidator {

    public void validate(EventDto eventDto, Errors errors) {
        if (eventDto.getBasePrice() > eventDto.getMaxPrice() && eventDto.getMaxPrice() > 0) {
            // global error
            errors.reject("wrong prices", "values to prices wrong");
        }

        LocalDateTime endEventDateTime = eventDto.getEndEventDateTime();
        if (endEventDateTime.isBefore(eventDto.getBeginEventDateTime()) ||
        endEventDateTime.isBefore(eventDto.getCloseEnrollmentDatetime()) ||
        endEventDateTime.isBefore(eventDto.getBeginEnrollmentDateTime())) {
            // field error
            errors.rejectValue("endEventDateTime", "wrongvalue", "endEventDateTime wrong");
        }

        // TODO beginEventDateTime
        // TODO closeEnrollmentDateTime
    }
}
