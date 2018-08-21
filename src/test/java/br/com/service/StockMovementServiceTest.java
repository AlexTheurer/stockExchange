package br.com.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.entity.MonitoringHistory;
import br.com.entity.StockExchangeMonitoring;
import br.com.enums.TransferType;
import br.com.repository.MonitoringRepository;

@RunWith(SpringRunner.class)
public class StockMovementServiceTest {
	
	private static final double PRICE_PURCHASE = 10.20;
	private static final double ZERO_PRICE_PURCHASE = 0;
	
	private static final double PRICE_SHARE = 10;
	private static final double PRICE_SALE_SHARE = 14.00;
	private static final double ZERO_PRICE_SALE_SHARE = 0;
	
	private static final double BANK_BALANCE = 10000;
	private static final double ZERO_BALANCE = 0;
	
	private static final double PRICE_SALE= 12.00;
	
	private static final String TRANFERENCE_DATE = "20/08/2018 23:59:59";
	
	private static final double QUANTITY_SHARE_PURCHASE = 1523.52;
	
	private static final String ACCOUNT_NAME = "Pedro";
	private static final String COMPANY_NAME = "SoftExpert";
	
	private static final double RETURN_PRICE_PURCHASE_SHARE = 9.18;
	private static final double RETURN_PRICE_SALE_SHARE = 13.200000000000001;
	
	@MockBean
	private MonitoringRepository repositoryCompany;
	
	@MockBean
	private MonitoringHistoryService monitoringHistoryService;
	
	private StockExchangeMonitoring stockExchangeMonitoring;
	
	private StockMovementService service;

	@Before
	public void setUp() {
		stockExchangeMonitoring = new StockExchangeMonitoring(ACCOUNT_NAME,COMPANY_NAME,BANK_BALANCE,PRICE_PURCHASE,PRICE_SALE);
		service = new StockMovementService(repositoryCompany, monitoringHistoryService);
	}
	
	@Test
	public void isValidPurchaseTest() {
		assertEquals(true, service.isValidPurchase(PRICE_PURCHASE, PRICE_SHARE, BANK_BALANCE));
	}
	
	@Test
	public void isValidPurchaseZeroBalanceTest() {
		assertEquals(false, service.isValidPurchase(PRICE_PURCHASE, PRICE_SHARE, ZERO_BALANCE));
	}
	
	@Test
	public void isValidPurchaseZeroPricePurchaseTest() {
		assertEquals(false, service.isValidPurchase(ZERO_PRICE_PURCHASE, PRICE_SHARE, BANK_BALANCE));
	}
	
	@Test
	public void isValidSaleTest(){
		assertEquals(true, service.isValidSale(PRICE_SALE, PRICE_SALE_SHARE, BANK_BALANCE));
	}
	
	@Test
	public void isValidSaleZeroBalanceTest(){
		assertEquals(false, service.isValidSale(PRICE_SALE, PRICE_SALE_SHARE, ZERO_BALANCE));
	}
	
	@Test
	public void isValidSaleZeroPriceSaleTest(){
		assertEquals(false, service.isValidSale(PRICE_SALE, ZERO_PRICE_SALE_SHARE, ZERO_BALANCE));
	}
	
	@Test
	public void pricePurchaseShareTest(){
		assertEquals(RETURN_PRICE_PURCHASE_SHARE, service.pricePurchaseShare(PRICE_PURCHASE),1e-15); 
	}
	
	@Test
	public void priceSaleShareTest(){
		assertEquals(RETURN_PRICE_SALE_SHARE ,service.priceSaleShare(PRICE_SALE),1e-15);
	}
	
	@Test
	public void verifyMonitoringTest() {
		assertEquals(true,service.verifyMonitoring(stockExchangeMonitoring));
	}
	
	@Test
	public void resultTest() throws InterruptedException {
		Mockito.when(repositoryCompany.save(stockExchangeMonitoring)).thenReturn(stockExchangeMonitoring);
		Mockito.when(monitoringHistoryService.save(COMPANY_NAME, TransferType.PURCHASE,PRICE_SHARE,QUANTITY_SHARE_PURCHASE,ZERO_BALANCE,TRANFERENCE_DATE)).thenReturn(new MonitoringHistory());
		
		assertEquals(true,service.result(stockExchangeMonitoring));
	}
	
	@Test
	public void resultBankBalanceZeroTest() throws InterruptedException {
		StockExchangeMonitoring newStockExchange = stockExchangeMonitoring;
		newStockExchange.setBankBalance(0);
		assertEquals(false,service.result(newStockExchange));
	}
	
	@Test
	public void resultPurchaseShareZeroTest() throws InterruptedException {
		StockExchangeMonitoring newStockExchange = stockExchangeMonitoring;
		newStockExchange.setPurchaseShare(0);
		assertEquals(false,service.result(newStockExchange));
	}
	
	@Test
	public void resultSaleShareZeroTest() throws InterruptedException {
		StockExchangeMonitoring newStockExchange = stockExchangeMonitoring;
		newStockExchange.setSaleShare(0);
		assertEquals(false,service.result(newStockExchange));
	}
	
	@Test
	public void resultAccountIsNullTest() throws InterruptedException {
		StockExchangeMonitoring newStockExchange = stockExchangeMonitoring;
		newStockExchange.setAccount(null);
		assertEquals(false,service.result(newStockExchange));
	}
	
	@Test
	public void resultBreakTest() throws InterruptedException {
		StockExchangeMonitoring newStockExchange = stockExchangeMonitoring;
		newStockExchange.setBankBalance(0);
		newStockExchange.setPurchaseShare(0);
		newStockExchange.setSaleShare(0);
		assertEquals(false,service.result(newStockExchange));
	}
}
