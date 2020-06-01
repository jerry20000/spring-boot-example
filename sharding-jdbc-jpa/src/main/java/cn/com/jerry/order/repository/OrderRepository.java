package cn.com.jerry.order.repository;

import cn.com.jerry.order.config.BaseJPA;
import cn.com.jerry.order.domain.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderRepository extends BaseJPA<Order> {

    Page<Order> findAllByUserIdOrderByOrderIdAsc(long userId, Pageable pageable);


}
