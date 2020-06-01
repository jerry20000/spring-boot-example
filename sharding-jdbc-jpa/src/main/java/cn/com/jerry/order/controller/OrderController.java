package cn.com.jerry.order.controller;

import cn.com.jerry.order.domain.model.Order;
import cn.com.jerry.order.repository.OrderRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Jerry
 * @date ：Created in 2019/12/30 15:33
 */
@Slf4j
@RestController
@RequestMapping("/api/order")
@Api(description = "Order相关接口")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @ApiOperation(value = "create")
    @PostMapping("/create")
    public Object add() {
        for (int i = 0; i < 10; i++) {
            Order order = new Order();
            order.setUserId((long) i);
            order.setStatus(String.valueOf(i));
            orderRepository.save(order);
        }
        for (int i = 10; i < 20; i++) {
            Order order = new Order();
            order.setUserId((long) i + 1);
            order.setStatus(String.valueOf(i));
            orderRepository.save(order);
        }

        return "success";
    }

    @ApiOperation(value = "query")
    @GetMapping("query")
    private Object queryAll() {
        return orderRepository.findAll();
    }
}
