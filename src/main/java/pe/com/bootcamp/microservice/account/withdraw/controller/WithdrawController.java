package pe.com.bootcamp.microservice.account.withdraw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.com.bootcamp.microservice.account.withdraw.entity.Withdraw;
import pe.com.bootcamp.microservice.account.withdraw.model.Account;
import pe.com.bootcamp.microservice.account.withdraw.service.WithdrawService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path="/withdraw")
public class WithdrawController {

    @Autowired
    WithdrawService withdrawService;
 

    @GetMapping("/withdraw")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Withdraw>getWithdraw(){
    	log.info("Metodo getDeposit");
        return withdrawService.findAll();
    }

    @PostMapping("/withdraw")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Withdraw> saveWithdraw(@RequestBody Withdraw withdraw){
    	log.info("Metodo saveWithdraw");
        return withdrawService.save(withdraw);
    }

    @GetMapping("/withdraw/account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Account>getWithdrawByBankAccount(@PathVariable String id){
    	log.info("Metodo getDepositByAccount");
        return withdrawService.getAccount(id);
    }
         
}