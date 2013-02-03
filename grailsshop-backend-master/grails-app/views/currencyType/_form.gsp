<%@ page import="com.github.olavgg.grailsshop.money.CurrencyType" %>



<div class="fieldcontain ${hasErrors(bean: currencyTypeInstance, field: 'code', 'error')} ">
	<label for="code">
		<g:message code="currencyType.code.label" default="Code" />
		
	</label>
	<g:textField name="code" value="${currencyTypeInstance?.code}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: currencyTypeInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="currencyType.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${currencyTypeInstance?.description}"/>
</div>

