package br.com.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class StockExchangeMonitoring {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToOne(cascade=CascadeType.PERSIST)
	private Account account;
	private String companyName;
	private double bankBalance;
	private double ownedShare;
	private double purchaseShare;
	private double saleShare;
	
	public StockExchangeMonitoring() {
		super();
	}

	public StockExchangeMonitoring(String accountName,String companyName, double bankBalance,double purchaseShare,double saleShare) {
		setAccount(new Account(accountName));
		setCompayName(companyName);
		setPurchaseShare(purchaseShare);
		setSaleShare(saleShare);
		setBankBalance(bankBalance);
		setOwnedShare(0);
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompayName(String name) {
		this.companyName = name;
	}
	public double getBankBalance() {
		return bankBalance;
	}
	public void setBankBalance(double bankBalance) {
		this.bankBalance = bankBalance;
	}
	public double getOwnedShare() {
		return ownedShare;
	}
	public void setOwnedShare(double ownedShare) {
		this.ownedShare = ownedShare;
	}

	public double getPurchaseShare() {
		return purchaseShare;
	}

	public void setPurchaseShare(double purchaseShare) {
		this.purchaseShare = purchaseShare;
	}

	public double getSaleShare() {
		return saleShare;
	}

	public void setSaleShare(double saleShare) {
		this.saleShare = saleShare;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
