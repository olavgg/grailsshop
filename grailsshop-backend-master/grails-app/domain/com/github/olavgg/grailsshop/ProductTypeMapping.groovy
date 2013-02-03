package com.github.olavgg.grailsshop

class ProductTypeMapping {
    
    Product product
    ProductType productType

    static constraints = {
        product: nullable:false
        productType: nullable:false
    }
    
    static mapping = {
        id composite: ['product', 'productType']
        version false
        table 'mapping_product_type'
        cache usage:'read-write'
        version false
        product index:'mapping_product_id_idx'
        product index:'mapping_product_type_idx'
    }
}

