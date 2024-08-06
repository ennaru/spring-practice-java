package com.ennaru.practice.jpa.repository;

import com.ennaru.practice.jpa.domain.AccessLog;
import org.springframework.data.repository.CrudRepository;

public interface AccessLogRepository extends CrudRepository<AccessLog, String> {

}
