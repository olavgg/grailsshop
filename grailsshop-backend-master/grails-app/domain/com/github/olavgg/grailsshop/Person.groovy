package com.github.olavgg.grailsshop

import org.apache.commons.codec.digest.DigestUtils;

import com.github.olavgg.grailsshop.security.Salt;

class Person {

    String username
    String password
    String salt = Salt.getSalt()
    boolean enabled = true
    boolean accountExpired = false
    boolean accountLocked = false
    boolean passwordExpired = false
    
    String name
    String firstname
    String lastname
    String telephone
    String email

    static constraints = {
        username blank: false, unique: true, size:3..32, matches:"^[a-zA-Z0-9]+"
        name blank:true, nullable:true
        firstname blank:true, nullable:true
        lastname blank:true, nullable:true
        password blank: false, size: 10..4096
        email blank: false
        salt blank: false, maxSize: 64
        telephone nullable:true, blank:true
    }

    static mapping = {
        table 'person'
        password column: '`password`', length: 128
        id generator: 'sequence', params: [sequence: 'person_seq']
        cache usage:'read-write'
        version false
        
        id index:'person_id_idx'
        name index:'person_name_idx'
        email index:'person_email_idx'
        enabled index:'person_enabled_idx'
    }

    Set<PersonType> getAuthorities() {
        PersonType.findAllByPerson(this).collect { it.type } as Set
    }

    def beforeInsert() {
        encodePassword()
        setFullName()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
        if (isDirty('firstname') || isDirty('lastname')) {
            setFullName()
        }
    }

    protected void encodePassword() {
        password = DigestUtils.sha512Hex(this.password + this.salt)
    }
    
    private void setFullName(){
        this.name = (this.firstname?:"") + " " + (this.lastname?:"")
    }
    
    public boolean isCustomer(){
        return checkPersonType("CUSTOMER")
    }
    
    public boolean isAdmin(){
        return checkPersonType("ADMIN")
    }
    
    private boolean checkPersonType(String type){
        def roles = getAuthorities()
        for(role in roles){
            if(role.type.equals(type)){
                return true
            }
        }
        return false
    }
}
