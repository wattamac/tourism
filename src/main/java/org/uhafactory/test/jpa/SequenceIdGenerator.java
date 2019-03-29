package org.uhafactory.test.jpa;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Properties;

// CREATE SEQUENCE IF NOT EXISTS TEST START WITH 1
// SELECT NEXT VALUE FOR TEST;
public class SequenceIdGenerator implements IdentifierGenerator, Configurable {
    // return "SELECT NEXT VALUE FOR " + sequenceName + " FROM SYSIBM.SYSDUMMY1";
    private static String SELECT_SEQUENCE_QUERY = "SELECT NEXT VALUE FOR %s";

    private String query;

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        String sequenceName = params.getProperty("sequence_name");
        query = String.format(SELECT_SEQUENCE_QUERY, sequenceName);
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        SequenceId sequenceId = (SequenceId)object;

        Number seq = (Number) session.createNativeQuery(query).setFlushMode(FlushMode.MANUAL).uniqueResult();
        return sequenceId.createId(seq);
    }

}
