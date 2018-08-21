package br.com.entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.enums.TransferType;

@Entity
public class MonitoringHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String company;
	private TransferType transferType;
	private double sharePrice;
	private double sharesQuantity;
	private double bankBalanceAfter;
	private String dateTransfer;
	
	public MonitoringHistory() {
		super();
	}

	public MonitoringHistory(String company, TransferType typeTransfer, double sharePrice,double sharesQuantity ,double bankBalanceAfter, String string) {
		super();
		this.company = company;
		this.transferType = typeTransfer;
		this.sharePrice = sharePrice;
		this.setSharesQuantity(sharesQuantity);
		this.bankBalanceAfter = bankBalanceAfter;
		this.setDateTransfer(string);
		
	}

	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public TransferType getTypeTransfer() {
		return transferType;
	}
	public void setTypeTransfer(TransferType typeTransfer) {
		this.transferType = typeTransfer;
	}
	public double getSharePrice() {
		return sharePrice;
	}
	public void setSharePrice(double purchaseBalance) {
		this.sharePrice = purchaseBalance;
	}
	public double getBankBalanceAfter() {
		return bankBalanceAfter;
	}
	public void setBankBalanceAfter(double bankBalanceAfter) {
		this.bankBalanceAfter = bankBalanceAfter;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public double getSharesQuantity() {
		return sharesQuantity;
	}

	public void setSharesQuantity(double sharesQuantity) {
		this.sharesQuantity = sharesQuantity;
	}

	public String getDateTransfer() {
		return dateTransfer;
	}

	public void setDateTransfer(String dateTransfer) {
		this.dateTransfer = dateTransfer;
	}	
	
}
