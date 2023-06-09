package mx.finerio.pfm.api.services.gorm

import grails.gorm.services.Query
import grails.gorm.services.Service
import mx.finerio.pfm.api.domain.Account
import mx.finerio.pfm.api.domain.Transaction
import mx.finerio.pfm.api.domain.Category

@Service(Transaction)
interface TransactionGormService {
    Transaction save(Transaction transaction)
    Transaction getById(Long id)
    List<Transaction> findAll()
    List<Transaction> findAllByDateDeletedIsNull(Map args)
    List<Transaction> findAllByDateDeletedIsNullAndIdLessThanEquals(Long id, Map args)
    List<Transaction> findAllByAccountAndIdLessThanEqualsAndDateDeletedIsNull(Account account, Long id, Map args)
    List<Transaction> findAllByAccountAndChargeAndDateDeletedIsNullAndDateBetween(
            Account account, Boolean charge, Date from, Date to, Map args)
    List<Transaction> findAllByAccountAndDateDeletedIsNull(Account account, Map args)
    List<Transaction> findAllByAccount(Account account)
    List<Transaction> findAllByCategory(Category category)

    void delete(Serializable id)
    @Query("from ${Transaction a} where $a.id = $id and a.dateDeleted is Null")
    Transaction findByIdAndDateDeletedIsNull(Long id)

    @Query("update ${Transaction t} set t.dateDeleted = ${new Date()} where $t.account = $account")
    void deleteAllByAccount(Account account)

}
