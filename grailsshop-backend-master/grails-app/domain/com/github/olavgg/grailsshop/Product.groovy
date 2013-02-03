package com.github.olavgg.grailsshop

class Product {
    
    def productTypeService
    
    String title
    
    static constraints = {
    }
    
    public void setProductType(ProductType type){
        new ProductTypeMapping(
            product: this,
            productType: type
        ).save(flush:true)
    }
    
    public void setProductType(Set<ProductType> typeList){
        productTypeService.insert(this, typeList)
    }
    
    
}
