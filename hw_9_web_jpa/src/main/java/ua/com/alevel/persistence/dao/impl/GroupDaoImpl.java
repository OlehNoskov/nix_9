package ua.com.alevel.persistence.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.dao.GroupDao;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;

import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import javax.transaction.Transactional;

import java.util.List;

@Repository
@Transactional
public class GroupDaoImpl implements GroupDao {

    private final SessionFactory sessionFactory;

    public GroupDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Group entity) {
        this.sessionFactory.getCurrentSession().persist(entity);
    }

    @Override
    public void update(Group entity) {
        this.sessionFactory.getCurrentSession().merge(entity);
    }

    @Override
    public void delete(Long id) {
        int isSuccessful = this.sessionFactory.getCurrentSession().createQuery("delete from Group g where g.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        if (isSuccessful == 0) {
            throw new OptimisticLockException("group modified concurrently");
        }
    }

    @Override
    public boolean existById(Long id) {
        Query query = this.sessionFactory.getCurrentSession().createQuery("select count(g.id) from Group g where g.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Group findById(Long id) {
        return this.sessionFactory.getCurrentSession().find(Group.class, id);
    }

    @Override
    public DataTableResponse<Group> findAll(DataTableRequest request) {
        CriteriaBuilder criteriaBuilder = this.sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Group> criteriaQuery = criteriaBuilder.createQuery(Group.class);
        Root<Group> from = criteriaQuery.from(Group.class);
        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }

        int page = (request.getPage() - 1) * request.getSize();
        int size = page + request.getSize();

        List<Group> items = this.sessionFactory.getCurrentSession().createQuery(criteriaQuery)
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
        Query query = this.sessionFactory.getCurrentSession().createQuery("select count(g.id) from Group g");
        return (Long) query.getSingleResult();
    }

    @Override
    public List<Student> getStudents(Long id) {
        return null;
    }

    @Override
    public void addStudent(Long groupId, Long studentId) {

    }

    @Override
    public void removeStudent(Long groupId, Long studentId) {

    }
}