package br.com.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.entity.StockExchangeMonitoring;

public interface MonitoringRepository extends CrudRepository <StockExchangeMonitoring,Long>{

}
