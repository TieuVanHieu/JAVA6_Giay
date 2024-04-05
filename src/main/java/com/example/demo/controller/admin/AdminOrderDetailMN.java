// package com.example.demo.controller.admin;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;

// import com.example.demo.model.OrderDetailEntity;
// import com.example.demo.model.OrderEntity;
// import com.example.demo.repository.OrderDetailEntityDAO;
// import com.example.demo.repository.OrderEntityDAO;

// @Controller
// @RequestMapping("/order")
// public class AdminOrderDetailMN {
    
//     @Autowired
//     OrderEntityDAO orderEntityDAO;

//     @Autowired
//     OrderDetailEntityDAO orderDetailEntityDAO;

//     @RequestMapping("/orderreport")
//     public String ShowOrder(Model model){
//         List<OrderEntity> orderEntity = orderEntityDAO.findAll();
//         for (OrderEntity order2 : orderEntity) {
//             System.out.println(order2.getShippingInfo().get(0).getShippingName().toString());
//         }
//         List<OrderDetailEntity> od = orderDetailEntityDAO.findAll();
//         model.addAttribute("orders", orderEntity);
//         model.addAttribute("ad", od);
        
//         return "/admin/orderDetail-management";
//     }
    
//      @RequestMapping("/orderDetail/{Id}")
//     public String showODD(Model model, @PathVariable("Id") Integer Id){
//         List<OrderDetailEntity> orderDetailEntity = orderDetailEntityDAO.findByOrderOrderId(Id);
//         model.addAttribute("orderDetail", orderDetailEntity);
//         System.out.println(Id);
//         return "/admin/list-orderDetail";
//     }
// }
