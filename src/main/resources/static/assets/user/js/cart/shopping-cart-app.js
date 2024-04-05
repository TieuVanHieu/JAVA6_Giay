const app = angular.module("shopping-cart-app", []);

app.controller("shipping-cart-ctrl", function ($scope, $http) {
  var sweetalert = function (icon, text) {
    Swal.fire({
      icon: icon,
      title: text,
      showConfirmButton: false,
      timer: 3000,
    });
  };

  $scope.cart = {
    items: [],
    // them san pham vao gio hang
    add() {
      var proId = document.querySelector('label[id="proId"]').innerText;
      $scope.proId = proId;
      console.log(
        $scope.color +
          "" +
          $scope.size +
          "" +
          $scope.quantity +
          "" +
          $scope.proId
      );
      if (
        $scope.color == null ||
        $scope.size == null ||
        $scope.quantity == null
      ) {
        sweetalert("error", "Thêm sản phẩm thất bại");
      } else {
        var item = this.items.find(
          (item) =>
            (item.color.colorId == $scope.color) &
            (item.size.sizeId == $scope.size) &
            (item.product.productId == $scope.proId)
        );
        if (item) {
          item.qty += $scope.quantity;
          this.saveToLocalStorage();
        } else {
          console.log(Boolean(item));
          const url = `/rest/products/${$scope.color}&${$scope.size}&${$scope.quantity}&${$scope.proId}`;
          $http.get(url).then((result) => {
            result.data.qty = $scope.quantity;
            this.items.push(result.data);
            this.saveToLocalStorage();
          });
        }

        sweetalert("success", "Thêm sản phẩm thành công");
      }
    },
    // xoa san pham ra khoi gio hang
    remove(id) {
      // swal.fire({
      //     title: 'Xác nhận xóa',
      //     text: 'Bạn có chắc chắn muốn xóa mục này?',
      //     icon: 'warning',
      //     showCancelButton: true,
      //     confirmButtonColor: '#3085d6',
      //     cancelButtonColor: '#d33',
      //     confirmButtonText: 'Xóa',
      //     cancelButtonText: 'Hủy'
      // }).then((result) => {
      //     if (result.isConfirmed) {
      //         // thong bao xoa
      //         Swal.fire({
      //             icon: 'success',
      //             title: 'Xóa thành công!',
      //             showConfirmButton: false,
      //             timer: 1500

      //         });
      //         var index = this.items.findIndex((item) => item.productId == id);
      //         this.items.splice(index, 1);
      //         this.saveToLocalStorage();
      //     }

      // });
      var index = this.items.findIndex((item) => item.productId == id);
      this.items.splice(index, 1);
      this.saveToLocalStorage();
    },

    // xoa sach cac mat hang trong gio
    clear() {
      this.items = [];
      this.saveToLocalStorage();
    },
    // tinh thanh tien cua mot san pham
    amt_of(items) {},
    // tinh tong so luong cac mat hang trong gio
    get count() {
      $scope.cart.loadFromLocalStorage();
      return this.items
        .map((item) => item.qty)
        .reduce((total, qty) => (total += qty), 0);
    },
    // tong thanh tien cac mat hang trong gio
    get amount() {
      return this.items
        .map((item) => item.qty * item.product.productPrice)
        .reduce((total, qty) => (total += qty), 0);
    },
    // luu gio hang vao localStorage
    saveToLocalStorage() {
      var json = JSON.stringify(angular.copy(this.items));
      localStorage.setItem("cart", json);
    },

    // Đọc giỏ hàng từ localStorage
    loadFromLocalStorage() {
      var json = localStorage.getItem("cart");
      this.items = json ? JSON.parse(json) : [];
    },
  };

  $scope.cart.loadFromLocalStorage();

  $scope.order = {
    orderDate: new Date(),
    orderAddress: "",
    account: { userName: $("#username").text() },
    get orderDetail() {
      return $scope.cart.items.map((item) => {
        return {
          quantity: item.qty,
          price: item.product.productPrice,
          productDetail: { productDetailId: item.productDetailId },
        };
      });
    },
    purchase() {
      var order = angular.copy(this);
      var username = document.getElementById("username").textContent;
      var date = document.getElementById("date").textContent;
      var address = $scope.order.orderAddress;
      if (username == null || date == null || address == '') {
        sweetalert("error", "Đặt hàng thất bại !");
      } else {
        // thực hiện đặt hàng
        $http
          .post("/rest/orders", order)
          .then((result) => {
            $scope.cart.clear();
            sweetalert("success", "Đặt hàng thành công !");

            // Chờ 3 giây trước khi chuyển hướng
            setTimeout(() => {
              location.href = "/order/detail/" + result.data.orderId;
            }, 3000);
          })
          .catch((error) => {
            sweetalert("error", "Đặt hàng thất bại !");
            console.log(error);
          });
      }
    },
  };
});
