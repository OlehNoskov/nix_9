package ua.com.alevel.view.dto.response;

import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.persistence.entity.Group;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class GroupFullResponseDto extends GroupSimpleResponseDto {
    private Set<StudentSimpleResponseDto> students;

    public GroupFullResponseDto(Group group) {
        super(group);
        setCreated(group.getCreated());
        setUpdated(group.getUpdated());
        setVisible(group.getVisible());
        students = new HashSet<>();
        if (CollectionUtils.isNotEmpty(group.getStudents())) {
            students = group.getStudents()
                    .stream()
                    .map(StudentSimpleResponseDto::new)
                    .collect(Collectors.toSet());
        }
    }

    public Set<StudentSimpleResponseDto> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentSimpleResponseDto> students) {
        this.students = students;
    }
}