package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.dao.GroupDao;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class GroupDaoImpl implements GroupDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Group entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Group entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        entityManager.createQuery("delete from Group g where g.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public boolean existById(Long id) {
        Query query = entityManager.createQuery("select count(g.id) from Group g where g.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Group findById(Long id) {
        return entityManager.find(Group.class, id);
    }

    @Override
    public DataTableResponse<Group> findAll(DataTableRequest request) {
        int page = (request.getPage() - 1) * request.getSize();
        int size = page + request.getSize();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Group> criteriaQuery = criteriaBuilder.createQuery(Group.class);
        Root<Group> from = criteriaQuery.from(Group.class);

        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }
        List<Group> items = entityManager
                .createQuery(criteriaQuery)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();

        DataTableResponse<Group> response = new DataTableResponse<>();
        response.setSort(request.getSort());
        response.setOrder(request.getOrder());
        response.setCurrentPage(request.getPage());
        response.setPageSize(request.getSize());
        response.setItems(items);

        return response;
    }

    @Override
    public long count() {
        Query query = entityManager.createQuery("select count(g.id) from Group g");
        return (Long) query.getSingleResult();
    }

//    @Override
//    public Set<Student> getStudents(Long id) {
//        return entityManager.find(Group.class, id).getStudents();
//    }
//
//    @Override
//    public void addStudent(Long groupId, Long studentId) {
//        Group group = entityManager.find(Group.class, groupId);
//        Student student = entityManager.find(Student.class, studentId);
//        group.addStudent(student);
//
//    }
//
//    @Override
//    public void removeStudent(Long groupId, Long studentId) {
//        Group group = entityManager.find(Group.class, groupId);
//        Student student = entityManager.find(Student.class, studentId);
//        group.removeStudent(student);
//    }
}