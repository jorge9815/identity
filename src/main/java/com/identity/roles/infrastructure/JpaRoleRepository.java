package com.identity.roles.infrastructure;

import com.identity.roles.domain.entity.Role;
import com.identity.roles.domain.repository.RoleRepository;
import com.identity.roles.domain.value_objects.RoleID;
import com.identity.shared.PaginatedList;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

import static com.identity.shared.Pagination.makeOffset;

@Repository
@Transactional
public class JpaRoleRepository implements RoleRepository {
    private EntityManager em;

    public JpaRoleRepository(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public Role getById(RoleID id) {
        return em.createQuery("FROM RoleModel r WHERE r.id=:id", RoleModel.class)
                .setParameter("id", id.getValue())
                .getSingleResult()
                .toRole();
    }

    @Override
    public Role getByName(String name) {
        return em.createQuery("FROM RoleModel r WHERE r.name =:name", RoleModel.class)
                .setParameter("name", name)
                .getSingleResult()
                .toRole();
    }

    @Override
    public PaginatedList<Role> getRoles(int page, int perPage) {
        Query query = em.createQuery("SELECT r.id FROM RoleModel r", Long.class);
        Long total = (Long) query.getSingleResult();

        List<Role> roles = em.createQuery("FROM RoleModel r ", RoleModel.class)
                .setFirstResult(makeOffset(page, perPage))
                .setMaxResults(perPage)
                .getResultList()
                .stream().map(RoleModel::toRole)
                .collect(Collectors.toList());
        return new PaginatedList<>(total, page, perPage, roles);
    }

    @Override
    public void updateRoles(Role role) {
        RoleModel model = em.createQuery("FROM RoleModel r WHERE r.id =: id", RoleModel.class)
                .setParameter("id", role.getId().getValue())
                .getSingleResult();
        model.setName(role.getName());//todo comprobar q funcione correctamente el update y borrar esto
    }

    @Override
    public void deleteRoles(RoleID roleID) {
        var model = em.createQuery("FROM RoleModel r WHERE r.id=:id",RoleModel.class)
                .setParameter("id", roleID.getValue())
                .getSingleResult();
        em.remove(model);
    }

    @Override
    public void saveRole(Role role) {
        em.persist(new RoleModel(role));
    }
}
