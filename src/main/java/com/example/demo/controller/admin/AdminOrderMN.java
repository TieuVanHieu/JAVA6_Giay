// package com.example.demo.controller.admin;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;

// import com.example.demo.model.OrderDetailEntity;
// import com.example.demo.model.OrderEntity;
// // import com.example.demo.model.OrderStatusEntity;
// import com.example.demo.repository.OrderDetailEntityDAO;
// import com.example.demo.repository.OrderEntityDAO;
// // import com.example.demo.repository.OrderStatusEntityDAO;
// import com.example.demo.repository.ProductDetailEntityDAO;
// // import com.example.demo.repository.ShippingInfoEntityDAO;
// // import com.example.demo.repository.UserEntityDAO;

// @Controller
// @RequestMapping("/admin")
// public class AdminOrderMN {
//     @Autowired
//     OrderEntityDAO orderEntityDAO;

//     @Autowired
//     OrderDetailEntityDAO orderDetailEntityDAO;

//     @Autowired 
//     OrderStatusEntityDAO orderStatusEntityDAO;


//     @RequestMapping("/orderreport")
//     public String ShowOrder(Model model){
//         List<OrderEntity> orderEntity = orderEntityDAO.findByOrderStatusStatusId(1);
//         for (OrderEntity order2 : orderEntity) {
//             System.out.println(order2.getShippingInfo().get(0).getShippingName().toString());
//         }
//         List<OrderDetailEntity> od = orderDetailEntityDAO.findAll();
//         model.addAttribute("orders", orderEntity);
//         model.addAttribute("ad", od);
        
//         return "/admin/index";
//     }

//     @RequestMapping("/orderDetail/{Id}")
//     public String showODD(Model model, @PathVariable("Id") Integer Id){
//         List<OrderDetailEntity> orderDetailEntity = orderDetailEntityDAO.findByOrderOrderId(Id);
//         model.addAttribute("orderDetail", orderDetailEntity);
//         System.out.println(Id);
//         return "/admin/list-orderDetail";
//     }

//     /**
//      * @param model
//      * @param statusId
//      * @return
//      */
//     @RequestMapping("/orderStatus/{statusId}")
//     public String updateOrderStatus(Model model, @PathVariable("statusId") Integer statusId){
//         OrderEntity orderEntity = orderEntityDAO.findByOrderId(statusId);
//          List<OrderStatusEntity> status = orderStatusEntityDAO.findByStatusId(2);
//         orderEntity.setOrderStatus(status.get(0));
//         orderEntityDAO.save(orderEntity);
//         return "redirect:/admin/orderreport";
//     }
// }
