package cn.com.jerry.utils.core;

import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;

import java.io.Serializable;

/**
 * @author ：Jerry
 * @date ：Created in 2019/8/20 15:46
 */
public class CustomIDGenerator extends IdentityGenerator {
    @Override
     public Serializable generate(SharedSessionContractImplementor session, Object object) throws MappingException {
        Object id =  SnowflakeIdHelper.generateId();
        if (id != null) {
            return (Serializable) id;
        }
        return super.generate(session, object);
     }
}
