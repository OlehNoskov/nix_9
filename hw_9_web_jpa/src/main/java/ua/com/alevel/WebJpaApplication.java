package ua.com.alevel;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import ua.com.alevel.facade.StudentFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.type.GroupType;
import ua.com.alevel.service.GroupService;
import ua.com.alevel.service.StudentService;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class WebJpaApplication {

    private final StudentService studentService;
    private final GroupService groupService;
    private final StudentFacade studentFacade;

    public WebJpaApplication(StudentService studentService, GroupService groupService, StudentFacade studentFacade) {
        this.studentService = studentService;
        this.groupService = groupService;
        this.studentFacade = studentFacade;
    }

    public static void main(String[] args) {
        SpringApplication.run(WebJpaApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void test() {

        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setSize(2);
        dataTableRequest.setPage(1);
        dataTableRequest.setSort("id");
        dataTableRequest.setOrder("desc");

        DataTableResponse<Group> dataTableResponse = groupService.findAll(dataTableRequest);

        for (Group item : dataTableResponse.getItems()) {
            System.out.println("department id = " + item.getId());
            System.out.println("department name = " + item.getName());
            System.out.println("department type = " + item.getGroupType().name());
        }

        Student student = studentService.findById(5L);
        student.setFirstName("testing");
        Set<Group> groups = new HashSet<>(dataTableResponse.getItems());
        student.setGroups(groups);

        studentService.update(student);

        DataTableResponse<Student> dataTableResponse1 = studentService.findAll(dataTableRequest);

        for (Student item : dataTableResponse1.getItems()) {
            System.out.println("id = " + item.getId());
            System.out.println("age = " + item.getAge());
            System.out.println("f = " + item.getFirstName());
            System.out.println("l = " + item.getLastName());
            if (CollectionUtils.isNotEmpty(item.getGroups())) {
                System.out.println("dep size = " + item.getGroups().size());
            }
        }
    }
}