package danyliuk.mykola.dao;

import danyliuk.mykola.model.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository("transactionDao")
public class TransactionDao {

    static final Logger logger = LoggerFactory.getLogger(TransactionDao.class);

    static SessionFactory sessionFactoryObj;
    static Session sessionObj;

    //This Method Is Used To Create The Hibernate's SessionFactory Object
    private static SessionFactory buildSessionFactory() {
        // Creating Configuration Instance & Passing Hibernate Configuration File

        Configuration configObj = new Configuration().configure();

        if (sessionFactoryObj == null) {
            // Since Hibernate Version 4.x, ServiceRegistry Is Being Used
            StandardServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().
                    configure().loadProperties("hibernate.cfg.xml").build();
            // Creating Hibernate SessionFactory Instance
            sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
        }

        return sessionFactoryObj;
    }

    // This Method To Find Particular Record In The Database Table
    public Transaction findRecordById(Integer find_transaction_id) {
        Transaction findTransactionObj = null;
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            findTransactionObj = (Transaction) sessionObj.load(Transaction.class, find_transaction_id);
        } catch (Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        }
        return findTransactionObj;
    }


    public void save(Transaction transaction) {
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
            sessionObj.save(transaction);
            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
        } catch (Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }

}
