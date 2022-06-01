package pe.com.bootcamp.microservice.account.withdraw.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Document(collection = "tb_withdraw")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Withdraw {
	@Id
	private String id;
	private String idAccount;// id cuenta
	private Double Amount; //monto de transaccion
	private String currency; //divisa
	private String channel; //canal

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateTransaction; //fecha transaccion	
	private Double initialBalance; //saldo inicial
	private Boolean status;
}