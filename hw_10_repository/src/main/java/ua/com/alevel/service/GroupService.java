package ua.com.alevel.service;
;
import ua.com.alevel.persistence.entity.Group;

import java.util.Set;

public interface GroupService extends BaseService<Group>{
    Set<Group> findStudentByGroupId(Long id);
}