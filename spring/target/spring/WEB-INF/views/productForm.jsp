<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ include file="header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Form</title>
</head>
<body>

	<c:url value="/admin/product/addProduct" var="url"></c:url>
	<form:form method="post" action="${url}" commandName="productFormObj"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td><form:label path="productId">Product Id</form:label></td>
				<td><form:hidden path="productId" /></td>
			</tr>
			<tr>
				<td><form:label path="productDescription">Product Description</form:label></td>
				<td><form:input path="productDescription"></form:input> <form:errors
						path="productDescription"></form:errors></td>
			</tr>
			<tr>
				<td><form:label path="productManufacturer">Product Manufacturer</form:label></td>
				<td><form:input path="productManufacturer"></form:input> <form:errors
						path="productManufacturer"></form:errors></td>
			</tr>
			<tr>
				<td><form:label path="productName">Product Name</form:label></td>
				<td><form:input path="productName"></form:input> <form:errors
						path="productName"></form:errors></td>
			</tr>
			<tr>
				<td><form:label path="productPrice">Product Price</form:label></td>
				<td><form:input path="productPrice"></form:input> <form:errors
						path="productPrice"></form:errors></td>
			</tr>
			<tr>
				<td><form:label path="unitInStock">Unit In Stock</form:label></td>
				<td><form:input path="unitInStock"></form:input> <form:errors
						path="unitInStock"></form:errors></td>
			</tr>
			<tr>
				<td><form:label path="category">CATEGORY</form:label></td>
				<td><form:radiobutton path="category.cId" value="1" />New
					Arrivals <form:radiobutton path="category.cId" value="2" />Special
					Edition <form:radiobutton path="category.cId" value="3" />Discount
					Sale <form:radiobutton path="category.cId" value="4" />General</td>
			</tr>

			<tr>

				<td><form:input path="productImage" type="file" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add Product"></td>
			</tr>


		</table>
	</form:form>
</body>
</html>