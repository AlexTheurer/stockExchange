package br.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.entity.StockExchangeMonitoring;
import br.com.service.StockMovementService;

@Controller
public class IndexController {
	
	@Autowired
	private StockMovementService service;

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	@RequestMapping(value="resultGeneration", method= RequestMethod.POST)
	@ResponseBody
	public String resultGeneration(@RequestParam String companyName, String nameUser, double bankBalance,double purchasePrice, double salePrice){
		try{
			StockExchangeMonitoring starCompanyResult = new StockExchangeMonitoring(nameUser,companyName,bankBalance,purchasePrice,salePrice);
			service.result(starCompanyResult);
			return "Ok! processamento efetuado com sucesso!";
		}catch(Exception e){
			e.printStackTrace();
			return "Ops! Ocorreu um imprevisto durante o processamento.";
		}
	}
}
