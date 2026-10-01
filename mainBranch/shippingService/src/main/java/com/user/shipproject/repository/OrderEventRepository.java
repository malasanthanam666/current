package com.user.shipproject.repository;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.user.shipproject.entity.OrderEvent;


@Repository
@EnableMongoRepositories
public interface OrderEventRepository extends MongoRepository<OrderEvent,String>{

}
