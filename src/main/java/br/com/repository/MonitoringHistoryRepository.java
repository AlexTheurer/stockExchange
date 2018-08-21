package br.com.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.entity.MonitoringHistory;

public interface MonitoringHistoryRepository extends CrudRepository <MonitoringHistory,Long>{

}
