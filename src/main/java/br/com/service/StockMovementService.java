package br.com.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.entity.MonitoringHistory;
import br.com.entity.StockExchangeMonitoring;
import br.com.enums.TransferType;
import br.com.repository.MonitoringRepository;

@Service
public class StockMovementService {

	@Autowired
	private MonitoringRepository repositoryCompany;

	@Autowired
	private MonitoringHistoryService monitoringHistoryService;	

	public StockMovementService() {
		super();
	}

	public StockMovementService(MonitoringRepository repositoryCompany,
			MonitoringHistoryService monitoringHistoryService) {
		super();
		this.repositoryCompany = repositoryCompany;
		this.monitoringHistoryService = monitoringHistoryService;
	}

	public boolean result(StockExchangeMonitoring monitoring) throws InterruptedException {
		int total = 0;
		if (verifyMonitoring(monitoring) == false) {
			return false;
		} else {
			while (total <= 11) {

				Double sharePrice = ThreadLocalRandom.current().nextDouble(
						pricePurchaseShare(monitoring.getPurchaseShare()), priceSaleShare(monitoring.getSaleShare()));

				if (isValidPurchase(monitoring.getPurchaseShare(), sharePrice, monitoring.getBankBalance())) {
					Double shareOwned = monitoring.getBankBalance() / sharePrice;

					monitoring.setOwnedShare(shareOwned);
					monitoring.setBankBalance(0);

					// Saving the new Updates
					repositoryCompany.save(monitoring);

					// Adds transaction relative data into history table
					addToHistory(monitoring.getCompanyName(), TransferType.PURCHASE, sharePrice, shareOwned,
							monitoring.getBankBalance());

					// Print Log Transactions
					System.out.println("Empresa: " + monitoring.getCompanyName() + " Valor de Compra: "
							+ formatValues(sharePrice) + " Total de ações: " + formatValues(shareOwned) + " saldo: "
							+ formatValues(monitoring.getBankBalance()));
					total = total + 1;
				} else if (isValidSale(monitoring.getSaleShare(), sharePrice, monitoring.getOwnedShare())) {
					Double balance = monitoring.getOwnedShare() * sharePrice;
					monitoring.setBankBalance(balance);

					// Adds transaction relative data into history table
					addToHistory(monitoring.getCompanyName(), TransferType.SALE, sharePrice, monitoring.getOwnedShare(),
							monitoring.getBankBalance());

					monitoring.setOwnedShare(0);
					// Saving the new Updates
					repositoryCompany.save(monitoring);

					// Print Log Transactions
					System.out.println("Empresa: " + monitoring.getCompanyName() + " Valor de Venda: "
							+ formatValues(sharePrice) + " Total de ações: " + formatValues(monitoring.getOwnedShare())
							+ " saldo: " + formatValues(monitoring.getBankBalance()));
					total = total + 1;
				}
				Thread.sleep(5000);
			}
			return true;
		}
	}

	public BigDecimal formatValues(double value) {
		return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public double pricePurchaseShare(double price) {
		return (price * 0.9);
	}

	public double priceSaleShare(double price) {
		return (price * 1.1);
	}

	public boolean isValidPurchase(Double pricePurchase, Double priceShare, Double bankBalance) {
		return pricePurchase > priceShare && bankBalance > 0;
	}

	public boolean isValidSale(Double priceSale, Double priceShare, Double ownedShare) {
		return priceSale < priceShare && ownedShare > 0;
	}

	public void addToHistory(String company, TransferType typeTransfer, double sharePrice, double sharesQuantity,
			double bankBalanceAfter) {
		monitoringHistoryService.save(company, typeTransfer, sharePrice, sharesQuantity, bankBalanceAfter, transactionDate());
	}

	public String transactionDate() {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		return timeStamp;
	}

	public boolean verifyMonitoring(StockExchangeMonitoring monitoring) {
		if (monitoring.getAccount() == null || monitoring.getBankBalance() == 0 || monitoring.getCompanyName().isEmpty()
				|| monitoring.getPurchaseShare() == 0 || monitoring.getSaleShare() == 0) {
			return false;
		}
		return true;
	}
}
