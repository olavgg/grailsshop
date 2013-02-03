package com.github.olavgg.grailsshop.money

import java.util.Currency

class CurrencyType {
    
    String code
    String description
    
    public CurrencyType(String code, String description){
        this.code = code.toUpperCase()
        this.description = description
    }

    static constraints = {
        code nullable: false, blank: false, size: 3..3
        description nullable: false, blank: false, size: 3..4096
    }
    
    static mapping = {
        table 'currency_type'
        id generator: 'sequence', params: [sequence: 'currency_type_seq']
        cache usage:'read-write'
        version false
        description column: 'description', length: 4096
        id index:'currency_type_id_idx'
        currency index:'currency_type_description_idx'
    }
    
    void setCodeField( String s ){
        this.code = s?.toUpperCase()
    }
    
    public Currency getAsCurrency(){
        return Currency.getInstance(this.code)
    }
}
