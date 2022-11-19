package com.bsuir.server.repositories;

import com.bsuir.server.entities.Role;
import com.bsuir.server.repositories.exception.RepositoryException;

public interface RoleRepository {
    Role get(int userStatusId) throws RepositoryException;
}
