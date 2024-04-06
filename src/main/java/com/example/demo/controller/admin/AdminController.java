package com.example.demo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
@RequestMapping("/admin")
public String admin() {
	return "admin/index";
}
// @RequestMapping("/admin/form-order")
// public String formOrder() {
// 	return "admin/form-order";
// }
// @RequestMapping("/admin/form-customer")
// public String formCustomer() {
// 	return "admin/form-customer";
// }
// @RequestMapping("/admin/form-brand")
// public String formBrand() {
// 	return "admin/form-brand";
// }
// @RequestMapping("/admin/form-product")
// public String formProduct() {
// 	return "admin/form-product";
// }
// @RequestMapping("/admin/form-productDetail")
// public String formProductDetail() {
// 	return "admin/form-productDetail";
// }
// @RequestMapping("/admin/form-size")
// public String formSize() {
// 	return "admin/form-size";
// }
// @RequestMapping("/admin/form-discount")
// public String formDiscount() {
// 	return "admin/form-discount";
// }
// @RequestMapping("/admin/account-management")
// public String formAccountMN() {
// 	return "admin/account-management";
// }
// @RequestMapping("/admin/Brand-management")
// public String formBrandMN() {
// 	return "admin/Brand-management";
// }
// @RequestMapping("/admin/customer-management")
// public String formCustomerMN() {
// 	return "admin/customer-management";
// }
// @RequestMapping("/admin/discount-management")
// public String formDiscountMN() {
// 	return "admin/discount-management";
// }
// @RequestMapping("/admin/order-management")
// public String formOrderMN() {
// 	return "admin/order-management";
// }
// @RequestMapping("/admin/orderDetail-management")
// public String formOrderDetailMN() {
// 	return "admin/orderDetail-management";
// }
// @RequestMapping("/admin/product-management")
// public String formProductMN() {
// 	return "admin/product-management";
// }
// @RequestMapping("/admin/productDetail-management")
// public String formProductDetailMN() {
// 	return "admin/productDetail-management";
// }
// @RequestMapping("/admin/Size-management")
// public String formSizeMN() {
// 	return "admin/Size-management";
// }
// @RequestMapping("/admin/statistical")
// public String formStatistical() {
// 	return "admin/statistical";
// }
// @RequestMapping("/admin/list-orderDetail")
// public String formListOrderDetail() {
// 	return "admin/list-orderDetail";
// }
}
