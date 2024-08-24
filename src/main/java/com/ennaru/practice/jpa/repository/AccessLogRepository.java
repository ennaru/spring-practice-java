package com.ennaru.practice.jpa.repository;

import com.ennaru.practice.jpa.dto.AccessLog;
import org.springframework.data.repository.CrudRepository;

public interface AccessLogRepository extends CrudRepository<AccessLog, String> {

}
