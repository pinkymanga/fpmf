package mx.finerio.pfm.api.dtos

import groovy.transform.ToString

import mx.finerio.pfm.api.domain.Account

@ToString(includeNames = true, includePackage = false,
    includeSuperProperties = true)
class AccountDto  extends ResourceDto{
    String nature
    String name
    String number
    BigDecimal balance

    AccountDto() {}

    AccountDto(Account account) {
        this.id = account.id
        this.nature = account.nature
        this.name = account.name
        this.number = account.number
        this.balance = account.balance
        this.dateCreated = account.dateCreated
        this.lastUpdated = account.lastUpdated
    }

}
