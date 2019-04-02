package org.uhafactory.tour.jpa;

import org.hibernate.FlushMode;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

public class SequenceIdGenerator implements IdentifierGenerator, Configurable {
    private static final String SELECT_SEQUENCE_QUERY = "SELECT NEXT VALUE FOR %s";

    private String query;

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) {
        String sequenceName = params.getProperty("sequence_name");
        query = String.format(SELECT_SEQUENCE_QUERY, sequenceName);
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        SequenceId sequenceId = (SequenceId)object;

        Number seq = (Number) session.createNativeQuery(query).setFlushMode(FlushMode.MANUAL).uniqueResult();
        return sequenceId.createId(seq);
    }

}
