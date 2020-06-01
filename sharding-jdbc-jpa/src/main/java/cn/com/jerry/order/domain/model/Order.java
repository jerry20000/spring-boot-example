package cn.com.jerry.order.domain.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t_order")
@Data
public class Order implements Serializable {
    private static final long serialVersionUID = 2282070645563320702L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "custom-id") @GenericGenerator(name = "custom-id", strategy = "cn.com.jerry.utils.core.CustomIDGenerator")
    private Long orderId;
    private Long userId;
    private String status;

}
