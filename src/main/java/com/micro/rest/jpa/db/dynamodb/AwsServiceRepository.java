package com.micro.rest.jpa.db.dynamodb;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.micro.rest.service.AwsService;

@Repository("aws")
@EnableScan
public interface AwsServiceRepository extends CrudRepository<AwsService, String> {
}