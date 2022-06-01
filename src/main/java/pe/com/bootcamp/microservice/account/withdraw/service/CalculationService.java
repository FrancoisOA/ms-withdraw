package pe.com.bootcamp.microservice.account.withdraw.service;

@FunctionalInterface
public interface CalculationService {
	Double Calcule(Double amount, Double balance);
}
