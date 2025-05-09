package dev.bd.work.socialnetwork.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author Alexey Bodyak
 */
@Slf4j
public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceType = TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? "slave" : "master";
        log.debug("Current data source type = {}, {}", dataSourceType, TransactionSynchronizationManager.isCurrentTransactionReadOnly());
        return dataSourceType;
    }
}
