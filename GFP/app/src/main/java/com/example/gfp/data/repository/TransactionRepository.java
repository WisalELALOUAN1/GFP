package com.example.gfp.data.repository;

import com.example.gfp.data.model.Transaction;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;

@Singleton
public class TransactionRepository {
    private final Realm realm;

    @Inject
    public TransactionRepository() {
        realm = Realm.getDefaultInstance();
    }

    public List<Transaction> getTransactionsByUser(int userId) {
        return realm.where(Transaction.class)
                .equalTo("idUserCategory", userId)
                .findAll();
    }

    public void saveTransaction(Transaction transaction) {
        realm.executeTransaction(r -> {
            Number maxId = r.where(Transaction.class).max("idTransaction");
            int newId = (maxId != null) ? maxId.intValue() + 1 : 1;
            transaction.setIdTransaction(newId);
            r.insertOrUpdate(transaction);
        });
    }
    public void saveTransactionForUser(int userId, Transaction transaction) {
        transaction.setIdUserCategory(userId); // Associe l'utilisateur à la transaction
        saveTransaction(transaction); // Réutilise la méthode existante
    }
}