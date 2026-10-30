package com.javahub.basics.service;
import com.javahub.basics.entity.Account;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;
@Service
public class PersistenceContextDemoService {
    private final EntityManager em;
    public PersistenceContextDemoService(EntityManager em) { this.em = em; }
    @Transactional
    public Map<String, Object> demonstrateDirtyChecking() {
        Account acct = new Account("Sarah Connor", 5000.0);
        em.persist(acct); // TRANSIENT -> MANAGED
        em.flush();
        Long id = acct.getId();
        // Modify managed entity without explicit em.merge() or repo.save()
        acct.setBalance(7500.0);
        // Hibernate dirty checking automatically synchronizes on transaction commit!
        return Map.of("id", id, "newBalance", acct.getBalance(), "state", "MANAGED_DIRTY_CHECKED");
    }
}
