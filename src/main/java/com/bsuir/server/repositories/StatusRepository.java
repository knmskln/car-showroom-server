package com.bsuir.server.repositories;

import com.bsuir.server.entities.Status;
import com.bsuir.server.repositories.exception.RepositoryException;

public interface StatusRepository {
    Status get(int statusId) throws RepositoryException;
}
