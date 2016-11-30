var myapp = angular.module("myapp", []).controller(
		"productController",
		function($scope, $http) {

			// To get all products
			$scope.getAllProducts = function() {

				$http.get('http://localhost:9090/spring/getProductsList')
						.success(function(data) {
							$scope.products = data;
						})
			}

			// to add a book to the cart
			$scope.addToCart = function(productId) {
				$http.put('http://localhost:9090/spring/cart/add/' + productId)
						.success(function() {
							alert('Product Added Successfully');
						})
			}

			// to refresh the cart
			$scope.refreshCart = function() {
				alert("entering the refresh cart");
				$http.get(
						'http://localhost:9090/spring/cart/getCart/'
								+ $scope.cartId).success(function(data) {
					$scope.cart = data;
				})
			}

			// to get the cart
			$scope.getCart = function(cartId) {
				alert("entering the get cart");
				$scope.cartId = cartId;
				$scope.refreshCart(cartId);
			}

			// to remove a book from the cart
			$scope.removeFromCart = function(cartItemId) {
				$http.put(
						'http://localhost:9090/spring/cart/removecartitem/'
								+ cartItemId).success(function() {
					$scope.refreshCart();
				})
			}

			// to clear the cart
			$scope.clearCart = function() {
				$http.put(
						'http://localhost:9090/spring/cart/removeAllItems/'
								+ $scope.cartId).success(function() {
					$scope.refreshCart();
				});
			}

			// to calculate total price
			$scope.calculateGrandTotal = function() {
				alert("aaaaaaa");
				var grandTotal = 0.0
				for (var i = 0; i < $scope.cart.cartItem.length; i++)
					grandTotal = grandTotal
							+ $scope.cart.cartItem[i].totalPrice;
				return grandTotal;
			}

		});