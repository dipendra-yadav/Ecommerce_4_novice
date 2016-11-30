<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Product Form</title>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ include file="header.jsp"%>

</head>
<body>
	<c:url value="/admin/product/editProduct" var="url"></c:url>
	<form:form method="post" action="${url}" commandName="editProductObj">
		<table>
			<tr>
				<td><form:label path="productId">Product Id</form:label></td>
				<td><form:input path="productId" disabled="true" /> <form:hidden
						path="productId" /></td>
			</tr>
			<tr>
				<td><form:label path="productDescription">Product Description</form:label></td>
				<td><form:input path="productDescription"></form:input></td>
			</tr>
			<tr>
				<td><form:label path="productManufacturer">Product Manufacturer</form:label></td>
				<td><form:input path="productManufacturer"></form:input></td>
			</tr>
			<tr>
				<td><form:label path="productName">Product Name</form:label></td>
				<td><form:input path="productName"></form:input></td>
			</tr>
			<tr>
				<td><form:label path="productPrice">Product Price</form:label></td>
				<td><form:input path="productPrice"></form:input></td>
			</tr>
			<tr>
				<td><form:label path="unitInStock">Unit In Stock</form:label></td>
				<td><form:input path="unitInStock"></form:input></td>
			</tr>

			<tr>
				<td><form:label path="category">CATEGORY</form:label></td>
				<td><form:radiobutton path="category.cId" value="1" />New
					Arrivals <form:radiobutton path="category.cId" value="2" />Special
					Edition <form:radiobutton path="category.cId" value="3" />Discount
					Sale <form:radiobutton path="category.cId" value="4" />General</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="EditProduct"></td>
			</tr>
		</table>
	</form:form>
</body>
</html>