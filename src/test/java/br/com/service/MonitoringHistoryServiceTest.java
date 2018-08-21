package br.com.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.entity.MonitoringHistory;
import br.com.enums.TransferType;
import br.com.repository.MonitoringHistoryRepository;

@RunWith(SpringRunner.class)
public class MonitoringHistoryServiceTest {
	private static final long ID = 1;
	private static final String COMPANY = "Softexpert";
	private static final TransferType ACTION = TransferType.PURCHASE;
	private static final double BANK_BALANCE = 10000;
	private static final double PURCHASE_BALANCE = 10.17;
	private static final double BANK_BALANCE_AFTER = 0;
	private static final String TRANFERENCE_DATE = "20/08/2018 23:59:59";
	
	private MonitoringHistory monitoringHistory;
	
	@MockBean
	private MonitoringHistoryRepository repository;
	
	private MonitoringHistoryService service;

	@Before
	public void setUp() {
		service = new MonitoringHistoryService(repository);
		monitoringHistory = new MonitoringHistory(COMPANY, ACTION, BANK_BALANCE,PURCHASE_BALANCE, BANK_BALANCE_AFTER,TRANFERENCE_DATE);
	}

	@Test
	public void createTest() {
		MonitoringHistory newHistory = monitoringHistory;
		newHistory.setId(null);
		Mockito.when(repository.save(newHistory)).thenReturn(monitoringHistory);
		assertEquals(monitoringHistory, service.save(newHistory));
	}
	
	@Test
	public void deleteTest() {
		service.delete(monitoringHistory);
		Mockito.verify(repository).delete(monitoringHistory);
	}

}
