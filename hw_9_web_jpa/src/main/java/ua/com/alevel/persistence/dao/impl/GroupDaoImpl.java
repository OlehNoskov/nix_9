package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Repository;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.persistence.dao.GroupDao;
import ua.com.alevel.persistence.entity.Group;

import javax.persistence.EntityManager;
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
        int isSuccessful = entityManager.createQuery("delete from Group g where g.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        if (isSuccessful == 0) {
            throw new OptimisticLockException("group modified concurrently");
        }
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
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Group> criteriaQuery = criteriaBuilder.createQuery(Group.class);
        Root<Group> from = criteriaQuery.from(Group.class);
        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }

        int page = (request.getPage() - 1) * request.getSize();
        int size = page + request.getSize();

        List<Group> items = entityManager.createQuery(criteriaQuery)
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
}