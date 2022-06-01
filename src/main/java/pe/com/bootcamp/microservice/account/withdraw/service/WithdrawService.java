package pe.com.bootcamp.microservice.account.withdraw.service;

import pe.com.bootcamp.microservice.account.withdraw.entity.Withdraw;
import pe.com.bootcamp.microservice.account.withdraw.model.Account;
import reactor.core.publisher.Mono;

public interface WithdrawService extends CrudService<Withdraw, String> {
	Mono<Account> getAccount(String idAccount);
}
