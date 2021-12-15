package ua.com.alevel.persistence.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.dao.StudentDao;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;

import javax.persistence.OptimisticLockException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import java.util.List;

@Repository
@Transactional
public class StudentDaoImpl implements StudentDao {

    private final SessionFactory sessionFactory;

    public StudentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Student entity) {
        this.sessionFactory.getCurrentSession().persist(entity);
    }

    @Override
    public void update(Student entity) {
        this.sessionFactory.getCurrentSession().merge(entity);
    }

    @Override
    public void delete(Long id) {
        int isSuccessful = this.sessionFactory.getCurrentSession().createQuery("delete from Student s where s.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        if (isSuccessful == 0) {
            throw new OptimisticLockException("student modified concurrently");
        }
    }

    @Override
    public boolean existById(Long id) {
        Query query = this.sessionFactory.getCurrentSession().createQuery("select count(s.id) from Student s where s.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Student findById(Long id) {
        return this.sessionFactory.getCurrentSession().find(Student.class, id);
    }

    @Override
    public DataTableResponse<Student> findAll(DataTableRequest request) {
        CriteriaBuilder criteriaBuilder = this.sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> from = criteriaQuery.from(Student.class);
        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }

        int page = (request.getPage() - 1) * request.getSize();
        int size = page + request.getSize();

        List<Student> items = this.sessionFactory.getCurrentSession().createQuery(criteriaQuery)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();

        DataTableResponse<Student> response = new DataTableResponse<>();
        response.setSort(request.getSort());
        response.setOrder(request.getOrder());
        response.setCurrentPage(request.getPage());
        response.setPageSize(request.getSize());
        response.setItems(items);

        return response;
    }

    @Override
    public long count() {
        Query query = this.sessionFactory.getCurrentSession().createQuery("select count(s.id) from Student s");
        return (Long) query.getSingleResult();
    }

    @Override
    public List<Group> getGroups(Long id) {
        return null;
    }
}