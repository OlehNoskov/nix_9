package ua.com.alevel.view.dto.request;

import lombok.Getter;
import lombok.Setter;

public class UserRequestDto extends RequestDto{

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private Integer age;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;
}