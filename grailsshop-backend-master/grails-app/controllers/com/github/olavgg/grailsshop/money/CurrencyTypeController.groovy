package com.github.olavgg.grailsshop.money

import org.springframework.dao.DataIntegrityViolationException

class CurrencyTypeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [currencyTypeInstanceList: CurrencyType.list(params), currencyTypeInstanceTotal: CurrencyType.count()]
    }

    def create() {
        [currencyTypeInstance: new CurrencyType(params)]
    }

    def save() {
        def currencyTypeInstance = new CurrencyType(params)
        if (!currencyTypeInstance.save(flush: true)) {
            render(view: "create", model: [currencyTypeInstance: currencyTypeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'currencyType.label', default: 'CurrencyType'), currencyTypeInstance.id])
        redirect(action: "show", id: currencyTypeInstance.id)
    }

    def show(Long id) {
        def currencyTypeInstance = CurrencyType.get(id)
        if (!currencyTypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'currencyType.label', default: 'CurrencyType'), id])
            redirect(action: "list")
            return
        }

        [currencyTypeInstance: currencyTypeInstance]
    }

    def edit(Long id) {
        def currencyTypeInstance = CurrencyType.get(id)
        if (!currencyTypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'currencyType.label', default: 'CurrencyType'), id])
            redirect(action: "list")
            return
        }

        [currencyTypeInstance: currencyTypeInstance]
    }

    def update(Long id, Long version) {
        def currencyTypeInstance = CurrencyType.get(id)
        if (!currencyTypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'currencyType.label', default: 'CurrencyType'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (currencyTypeInstance.version > version) {
                currencyTypeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'currencyType.label', default: 'CurrencyType')] as Object[],
                          "Another user has updated this CurrencyType while you were editing")
                render(view: "edit", model: [currencyTypeInstance: currencyTypeInstance])
                return
            }
        }

        currencyTypeInstance.properties = params

        if (!currencyTypeInstance.save(flush: true)) {
            render(view: "edit", model: [currencyTypeInstance: currencyTypeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'currencyType.label', default: 'CurrencyType'), currencyTypeInstance.id])
        redirect(action: "show", id: currencyTypeInstance.id)
    }

    def delete(Long id) {
        def currencyTypeInstance = CurrencyType.get(id)
        if (!currencyTypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'currencyType.label', default: 'CurrencyType'), id])
            redirect(action: "list")
            return
        }

        try {
            currencyTypeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'currencyType.label', default: 'CurrencyType'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'currencyType.label', default: 'CurrencyType'), id])
            redirect(action: "show", id: id)
        }
    }
}
