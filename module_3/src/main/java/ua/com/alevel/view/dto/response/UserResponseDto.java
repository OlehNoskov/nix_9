package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.User;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}