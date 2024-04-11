var app = angular.module("app",[]);

app.controller("ctrl",function ($scope,$http) {
	
	 console.log("auuu");

	$http
		.get("/admin/authorities")
		.then((response) => {
			$scope.db = response.data;
			console.log(response.data);
			console.log();
		})
		.catch((error) =>{
			console.error("Error fetching data:", error);

			// Handle errors if the requet fails
		});

		$scope.index_of = function (Uname , role) {
			try {
				return $scope.db.authorities.findIndex(
					(a) => a.account.userName == Uname && a.role.roleId == role
				);
			} catch (error){
				console.log(error);
			}
		};
		
		
		$scope.update = function (Uname, role){
			var index = $scope.index_of(Uname, role);
			if(index >= 0){
				var i = $scope.db.authorities[index].id;
				$http.delete(`/admin/authorities/${i}`).then((result) => {
					$scope.db.authorities.splice(index, 1);
				});
			} else {
				var authority = {
					account: {
						userName: Uname
					},
					role: {
						roleId: role
					},
				};
				$http.post(`/admin/authoritites`, authority).then((result) =>{
					$scope.db.authorities.push(result.data);
				});
			}
		};
});