package brido.com.hashring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            String res = isTxReadOnly() ? "secondary" : "primary";
            log.info("selected datasource = {}", res);
            return res;
        }
        return null;
    }

    private boolean isTxReadOnly() {
        return TransactionSynchronizationManager.isCurrentTransactionReadOnly();
    }
}