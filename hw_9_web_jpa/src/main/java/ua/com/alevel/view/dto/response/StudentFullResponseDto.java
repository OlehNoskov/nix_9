package ua.com.alevel.view.dto.response;

import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.persistence.entity.Student;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentFullResponseDto extends StudentSimpleResponseDto{

    private Set<GroupSimpleResponseDto> groups;

    public StudentFullResponseDto(Student student) {
        super(student);
        setCreated(student.getCreated());
        setUpdated(student.getUpdated());
        setVisible(student.getVisible());
        this.groups = new HashSet<>();
        if (CollectionUtils.isNotEmpty(student.getGroups())) {
            this.groups = student.getGroups()
                    .stream()
                    .map(GroupSimpleResponseDto::new)
                    .collect(Collectors.toSet());
        }
    }
}