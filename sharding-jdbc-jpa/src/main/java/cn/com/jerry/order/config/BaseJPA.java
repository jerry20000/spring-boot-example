package cn.com.jerry.order.config;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface BaseJPA<T> extends CrudRepository<T, Long>, JpaRepository<T, Long>, JpaSpecificationExecutor<T>, Serializable {

}