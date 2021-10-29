package com.identity.roles.aplication.services;

import com.identity.roles.aplication.RoleDto;
import com.identity.roles.infrastructure.JpaRoleRepository;
import com.identity.shared.PaginatedList;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class GetRoles {
    private final JpaRoleRepository repository;

    public GetRoles(JpaRoleRepository repository) {
        this.repository = repository;
    }

    public PaginatedList<RoleDto> get(int page, int perPage){
        var paginatedList = repository.getRoles(page, perPage);
        return new PaginatedList<>(
                paginatedList.getTotal(),
                page, perPage,
                paginatedList.getData().stream()
                .map(RoleDto::new).collect(Collectors.toList())
        );
    }
}
