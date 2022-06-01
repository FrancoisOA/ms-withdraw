package pe.com.bootcamp.microservice.account.withdraw.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import pe.com.bootcamp.microservice.account.withdraw.entity.Withdraw;

@Repository
public interface IWithdrawRepository extends ReactiveMongoRepository<Withdraw, String>{
}
