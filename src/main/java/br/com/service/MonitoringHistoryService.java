package br.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.entity.MonitoringHistory;
import br.com.enums.TransferType;
import br.com.repository.MonitoringHistoryRepository;

@Service
public class MonitoringHistoryService {
	
	@Autowired
	private MonitoringHistoryRepository historicRepository;
	
	public MonitoringHistoryService() {
		super();
	}

	public MonitoringHistoryService(MonitoringHistoryRepository historicRepository) {
		super();
		this.historicRepository = historicRepository;
	}

	public MonitoringHistory save(String company, TransferType typeTransfer, double sharePrice,double sharesQuantity ,double bankBalanceAfter, String string ) {
		return this.save(new MonitoringHistory(company, typeTransfer, sharePrice, sharesQuantity, bankBalanceAfter, string));
	}
	
	public MonitoringHistory save(MonitoringHistory history ) {
		return historicRepository.save(history);
	}
	
	public MonitoringHistory findById(long id) {
		return historicRepository.findById(id).get();
	}
	
	public void delete(MonitoringHistory history ) {
		historicRepository.delete(history);
	}
	
}
