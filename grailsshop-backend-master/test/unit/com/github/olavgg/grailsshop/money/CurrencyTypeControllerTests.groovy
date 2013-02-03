package com.github.olavgg.grailsshop.money



import org.junit.*
import grails.test.mixin.*

@TestFor(CurrencyTypeController)
@Mock(CurrencyType)
class CurrencyTypeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/currencyType/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.currencyTypeInstanceList.size() == 0
        assert model.currencyTypeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.currencyTypeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.currencyTypeInstance != null
        assert view == '/currencyType/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/currencyType/show/1'
        assert controller.flash.message != null
        assert CurrencyType.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/currencyType/list'

        populateValidParams(params)
        def currencyType = new CurrencyType(params)

        assert currencyType.save() != null

        params.id = currencyType.id

        def model = controller.show()

        assert model.currencyTypeInstance == currencyType
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/currencyType/list'

        populateValidParams(params)
        def currencyType = new CurrencyType(params)

        assert currencyType.save() != null

        params.id = currencyType.id

        def model = controller.edit()

        assert model.currencyTypeInstance == currencyType
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/currencyType/list'

        response.reset()

        populateValidParams(params)
        def currencyType = new CurrencyType(params)

        assert currencyType.save() != null

        // test invalid parameters in update
        params.id = currencyType.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/currencyType/edit"
        assert model.currencyTypeInstance != null

        currencyType.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/currencyType/show/$currencyType.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        currencyType.clearErrors()

        populateValidParams(params)
        params.id = currencyType.id
        params.version = -1
        controller.update()

        assert view == "/currencyType/edit"
        assert model.currencyTypeInstance != null
        assert model.currencyTypeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/currencyType/list'

        response.reset()

        populateValidParams(params)
        def currencyType = new CurrencyType(params)

        assert currencyType.save() != null
        assert CurrencyType.count() == 1

        params.id = currencyType.id

        controller.delete()

        assert CurrencyType.count() == 0
        assert CurrencyType.get(currencyType.id) == null
        assert response.redirectedUrl == '/currencyType/list'
    }
}
