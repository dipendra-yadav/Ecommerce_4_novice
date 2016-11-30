<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ include file="header.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product List</title>

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>

<script src="<c:url value="/resources/js/controller.js"></c:url>"></script>

</head>
<body>
	<div data-ng-app="myapp">
		<div data-ng-controller="productController"
			data-ng-init="getAllProducts()">

			Search: <input type="text" data-ng-model="searchCondition">

			<h1>List of Products</h1>

			<table border="1" class="table table-striped table-hover">
				<thead>
					<tr>
						<th>IMAGE</th>
						<th>PRODUCT ID</th>
						<th>PRODUCT NAME</th>
						<th>CATEGORY NAME</th>
						<th>VIEW/EDIT/DELETE</th>
					</tr>
				</thead>

				<tbody>
					<tr data-ng-repeat="b in products | filter:searchCondition">
						<c:url value="resources/images/{{b.productId}}.png" var="src" />
						<td><img src="${src }" style="width: 30%" align="middle" /></td>
						<td><a href="getProductsByProductId/{{b.productId }}">{{b.productId
								}}</a></td>
						<td>{{b.productName}}</td>
						<td>{{b.category.categoryName}}</td>
						<td><security:authorize access="hasRole('ROLE_ADMIN')">

								<a href="admin/delete/{{b.productId}}"><span
									class="glyphicon glyphicon-trash"></span></a>
								<a href="admin/product/editProduct/{{b.productId}}"><span
									class="glyphicon glyphicon-edit"></span></a>

							</security:authorize></td>
					</tr>
				</tbody>

			</table>

		</div>
	</div>
</body>
</html>