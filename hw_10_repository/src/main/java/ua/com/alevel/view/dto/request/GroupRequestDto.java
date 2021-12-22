package ua.com.alevel.view.dto.request;

import java.util.List;

public class GroupRequestDto extends RequestDto {

    private String name;
    private List<Long> studentsIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getStudentsIds() {
        return studentsIds;
    }

    public void setStudentsIds(List<Long> studentsIds) {
        this.studentsIds = studentsIds;
    }
}