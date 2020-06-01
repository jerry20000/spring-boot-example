package cn.com.jerry.order.config;

import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        //实体管理引用
        entityManagerFactoryRef = "entityManagerFactoryUser",
        //事务管理引用
        transactionManagerRef = "transactionManagerUser",
        //设置Repository所在位置
        basePackages = {"cn.com.jerry.order.repository"})
public class UserDataSourceConfig {

    /**
     * 注入用户数据源
     */
    @Resource
    private DataSource userShardingDataSource;

    /**
     * 配置EntityManager实体
     */
    @Bean(name = "entityManagerUser")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryUser(builder).getObject().createEntityManager();
    }

    /**
     * 配置EntityManager工厂实体
     */
    @Bean(name = "entityManagerFactoryUser")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryUser(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(userShardingDataSource)
                .properties(getVendorProperties())
                //设置实体类所在位置
                .packages("cn.com.jerry.order.domain.model")
                .persistenceUnit("userShardingPersistenceUnit")
                .build();
    }

    /**
     * 注入jpa配置实体
     */
    @Resource
    private JpaProperties jpaProperties;

    @Resource
    private HibernateProperties hibernateProperties;

    /**
     * 获取jpa配置信息
     */
    private Map<String, Object> getVendorProperties() {
        return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
    }

    /**
     * 配置事务
     */
    @Bean(name = "transactionManagerUser")
    public PlatformTransactionManager transactionManagerUser(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryUser(builder).getObject());
    }
}
