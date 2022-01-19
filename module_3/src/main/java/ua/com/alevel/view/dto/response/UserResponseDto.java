package ua.com.alevel.view.dto.response;

import lombok.Data;

import ua.com.alevel.persistence.entity.User;

@Data
public class UserResponseDto extends ResponseDto{

    private String firstName;
    private String lastName;
    private int age;

    public UserResponseDto() {}

    public UserResponseDto(User user) {
        this();
        setId(user.getId());
        setCreated(user.getCreated());
        setUpdated(user.getUpdated());
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.age = user.getAge();
    }
}