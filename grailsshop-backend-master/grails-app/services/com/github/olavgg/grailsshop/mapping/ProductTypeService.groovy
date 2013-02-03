package com.github.olavgg.grailsshop.mapping

import com.github.olavgg.grailsshop.Product
import com.github.olavgg.grailsshop.ProductType
import com.github.olavgg.grailsshop.ProductTypeMapping
import org.codehaus.groovy.grails.plugins.DomainClassGrailsPlugin

class ProductTypeService {
    
    static transactional = true
    
    def sessionFactory
    def propertyInstanceMap = DomainClassGrailsPlugin.PROPERTY_INSTANCE_MAP

    def insert(Product product, Set<ProductType> typeList) {
        typeList.each{
            new ProductTypeMapping(product:product, productType:it).save()
        }
        sessionFactory.currentSession.flush()
        sessionFactory.currentSession.clear()
        propertyInstanceMap.get().clear()
    }
}
