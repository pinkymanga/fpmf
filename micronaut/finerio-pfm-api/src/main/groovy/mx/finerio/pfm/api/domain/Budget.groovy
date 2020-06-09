package mx.finerio.pfm.api.domain

import grails.gorm.annotation.Entity
import mx.finerio.pfm.api.validation.BudgetCommand
import org.grails.datastore.gorm.GormEntity

@Entity
class Budget implements GormEntity<Budget> {
    Long id
    User user
    Category category
    String name
    Float amount
    Long parentBudgetId
    Date dateCreated
    Date lastUpdated
    Date dateDeleted

    Budget(){}

    Budget(BudgetCommand cmd, User user, Category category){
        this.user = user
        this.category = category
        this.name = cmd.name
        this.amount = cmd.amount
        this.parentBudgetId = cmd.parentBudgetId
    }

    static constraints = {
        name nullable: false, blank:false
        dateDeleted nullable:true
    }

    static mapping = {
        autoTimestamp true
    }

}