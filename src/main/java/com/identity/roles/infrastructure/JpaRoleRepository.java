package com.identity.roles.infrastructure;

import com.identity.exeptions.exceptions.RoleNotFound;
import com.identity.roles.domain.entity.Role;
import com.identity.roles.domain.repository.RoleRepository;
import com.identity.roles.domain.value_objects.RoleID;
import com.identity.shared.PaginatedList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

import static com.identity.shared.Pagination.makeOffset;

@Repository
@Transactional @Slf4j
public class JpaRoleRepository implements RoleRepository {
    private final EntityManager em;

    public JpaRoleRepository(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public Role getById(RoleID id) {
        return getRoleModelByID(id.getValue()).toRole();
    }

    @Override
    public Role getByName(String name) {
        return getRoleModel(name).toRole();
    }

    @Override
    public PaginatedList<Role> getRoles(int page, int perPage) {
        Query query = em.createQuery("SELECT count(r.id) FROM RoleModel r", Long.class);
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
        RoleModel model = getRoleModelByID(role.getId().getValue());
        model.setName(role.getName());
    }

    @Override
    public void deleteRoles(RoleID roleID) {
        em.remove(getRoleModelByID(roleID.getValue()));
    }

    @Override
    public void saveRole(Role role) {
        if(!isRoleSaved(role.getName())) {
            em.persist(new RoleModel(role));
        }else{
            log.error("The role {} is already saved", role.getName());
        }
    }

    private RoleModel getRoleModel(String name){
        try {
            return em.createQuery("FROM RoleModel r WHERE r.name =:name", RoleModel.class)
                    .setParameter("name", name)
                    .getSingleResult();
        }catch (NoResultException no){
            return null;
        }
    }

    private RoleModel getRoleModelByID(String id) throws RoleNotFound{
        try {
            return em.createQuery("FROM RoleModel r WHERE r.id=:id", RoleModel.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }catch (Exception e){
            throw new RoleNotFound();
        }
    }

    private boolean isRoleSaved(String roleName){
        return getRoleModel(roleName) != null;
    }

}
