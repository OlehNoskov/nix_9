package ua.com.alevel.view.dto.response;

import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.type.GroupType;

public class GroupSimpleResponseDto extends ResponseDto {

    private GroupType groupType;
    private String name;
    private long studentCount;

    public GroupSimpleResponseDto(Group group) {
        setId(group.getId());
        this.groupType = group.getGroupType();
        this.name = group.getName();
        if (CollectionUtils.isNotEmpty(group.getStudents())) {
            this.studentCount = group.getStudents().size();
        }
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    public long getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(long studentCount) {
        this.studentCount = studentCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}