const app = angular.module("shopping-cart-app",[]);



app.controller("shipping-cart-ctrl",function($scope,$http) {
    var proId = document.querySelector('label[id="proId"]').innerText
    $scope.proId = proId;
    $scope.cart = {
        items: [],
        // them san pham vao gio hang
        add(){
            console.log($scope.color+''+$scope.size+''+$scope.qty+''+$scope.proId);
           var item = this.items.find(item => item.color.colorId == $scope.color & item.size.sizeId == $scope.size & item.product.productId == $scope.proId)
           if(item){
            console.log(Boolean(item));
            item.quantity+=$scope.qty;
            this.saveToLocalStorage();
           }
           else{
            console.log(Boolean(item));
            const url = `/rest/products/${$scope.color}&${$scope.size}&${$scope.qty}&${$scope.proId}`;
            $http.get(url).then((result) => {
                this.items.push(result.data);
                this.saveToLocalStorage();
            })
           }
        },
        // xoa san pham ra khoi gio hang
        remove(id){},
        // xoa sach cac mat hang trong gio
        clear(){},
        // tinh thanh tien cua mot san pham
        amt_of(items){},
        // tinh tong so luong cac mat hang trong gio
        get count(){},
        // tong thanh tien cac mat hang trong gio
        get amount(){},
        // luu gio hang vao localStorage
        saveToLocalStorage(){
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart",json);
        },
    }
})