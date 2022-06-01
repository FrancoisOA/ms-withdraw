package pe.com.bootcamp.microservice.account.withdraw.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.com.bootcamp.microservice.account.withdraw.config.WebclientConfig;
import pe.com.bootcamp.microservice.account.withdraw.entity.Withdraw;
import pe.com.bootcamp.microservice.account.withdraw.model.Account;
import pe.com.bootcamp.microservice.account.withdraw.repository.IWithdrawRepository;
import pe.com.bootcamp.microservice.account.withdraw.service.CalculationService;
import pe.com.bootcamp.microservice.account.withdraw.service.WithdrawService;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class WithdrawServiceImpl implements WithdrawService {

	@Autowired
	IWithdrawRepository witRepo;

	private WebclientConfig webclient = new WebclientConfig();

	@Override
	public Flux<Withdraw> findAll() {
		log.info("Dentro de findAll");
		return witRepo.findAll();
	}

	@Override
	public Mono<Withdraw> save(Withdraw tran) {
		tran.setStatus(true);
		tran.setDateTransaction(new Date());
		return witRepo.save(tran).doOnSuccess(x -> {
			log.info("Dentro de doOnSuccess");
			x.setStatus(true);
			CalculationService ca = (amount, balance) -> balance - amount;
			webclient.getAccount(x.getIdAccount()).switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT))).flatMap(f -> {
				f.setBalance(ca.Calcule(x.getAmount(), f.getBalance()));
				tran.setInitialBalance(f.getBalance());
				log.info("Dentro de subscribe");
				return webclient.updateAccount(f);
			});
		});
	}

	@Override
	public Mono<Withdraw> update(Withdraw t) {
		return witRepo.findById(t.getId()).switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT))).flatMap(o -> {
			if (t.getIdAccount() != null) {
				o.setIdAccount(t.getIdAccount());
			}
			if (t.getAmount() != null) {
				o.setAmount(t.getAmount());
			}
			if (t.getCurrency() != null) {
				o.setCurrency(t.getCurrency());
			}
			if (t.getChannel() != null) {
				o.setChannel(t.getChannel());
			}
			if (t.getStatus() != null) {
				o.setStatus(t.getStatus());
			}
			return witRepo.save(o);
		});
	}

	@Override
	public Mono<Withdraw> deleteById(String id) {
		return witRepo.findById(id).switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT))).flatMap(o -> {
			o.setStatus(false);
			return witRepo.save(o);
		});
	}

	@Override
	public Mono<Withdraw> findById(String id) {
		return witRepo.findById(id);
	}

	@Override
	public Mono<Account> getAccount(String idAccount) {
		return webclient.getAccount(idAccount);
	}
}
