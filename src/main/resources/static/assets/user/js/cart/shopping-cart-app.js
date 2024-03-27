const app = angular.module("shopping-cart-app",[]);



app.controller("shipping-cart-ctrl",function($scope,$http) {
    var proId = document.querySelector('label[id="proId"]').innerText
    $scope.proId = proId;
    $scope.cart = {
        items:[],
        // them san pham vao gio hang
        add(){
            console.log($scope.color+''+$scope.size+''+$scope.qty+''+$scope.proId);
           var item = this.items.find(item => item.color.colorId == $scope.color & item.size.sizeId == $scope.size & item.product.productId == $scope.proId)
           if(item){
            item.qty++;
            this.saveToLocalStorage();
           }
           else{
            console.log(Boolean(item));
            const url = `/rest/products/${$scope.color}&${$scope.size}&${$scope.qty}&${$scope.proId}`;
            $http.get(url).then((result) => {
                result.data.qty=1;
                this.items.push(result.data);
                this.saveToLocalStorage();
            })
           }
        },
        // xoa san pham ra khoi gio hang
        remove(id){
            var index=this.items.findIndex(item=>item.productId==id);
            this.items.splice(index, 1);
            this.saveToLocalStorage();
        },
        // xoa sach cac mat hang trong gio
        clear(){
            this.items=[]
            this.saveToLocalStorage();
        },
        // tinh thanh tien cua mot san pham
        amt_of(items){},
        // tinh tong so luong cac mat hang trong gio
        get count(){
             return this.items
            .map(item=>item.qty)
            .reduce((total,qty)=>total+=qty,0);
        },
        // tong thanh tien cac mat hang trong gio
        get amount(){
            return this.items
            .map(item=>item.qty*item.productPrice)
            .reduce((total,qty)=>total+=qty,0);
        },
        // luu gio hang vao localStorage
        saveToLocalStorage(){
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart",json);
        },

        // Đọc giỏ hàng từ localStorage
        loadFromLocalStorage(){
            var json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        }
    }
    $scope.cart.loadFromLocalStorage();
})