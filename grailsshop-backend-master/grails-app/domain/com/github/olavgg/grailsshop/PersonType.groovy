package com.github.olavgg.grailsshop

class PersonType {

    String type

    static mapping = {
        table 'person_type'
        id generator: 'sequence', params: [sequence: 'person_type_seq']
        cache usage:'read-only'
        version false
        id index:'person_type_id_idx'
        type index:'person_type_type_idx'
    }

    static constraints = {
        type blank: false, unique: true, size: 3..64
    }
}

