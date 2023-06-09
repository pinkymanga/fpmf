package mx.finerio.pfm.api.services.gorm

import grails.gorm.services.Query
import grails.gorm.services.Service
import mx.finerio.pfm.api.domain.Client
import mx.finerio.pfm.api.domain.FinancialEntity

@Service(FinancialEntity)
interface FinancialEntityGormService {
    FinancialEntity save(FinancialEntity financialEntity)

    @Query("from ${FinancialEntity u} where $u.id = $id and u.dateDeleted is Null")
    FinancialEntity findByIdAndDateDeletedIsNull(Long id)
    FinancialEntity findByCodeAndClientAndDateDeletedIsNull(String code, Client client)
    FinancialEntity findByIdAndClientAndDateDeletedIsNull(Long id,Client client)
    FinancialEntity getById(Long id)
    List<FinancialEntity> findAll()
    void delete(Serializable id)
    List<FinancialEntity> findAllByDateDeletedIsNull(Map args)
    List<FinancialEntity> findAllByClientAndDateDeletedIsNull(Client client, Map args)
    List<FinancialEntity> findAllByDateDeletedIsNullAndIdLessThanEquals(Long id, Map args)
}