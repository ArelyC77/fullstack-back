package com.example.arely.fullstackbackend.controller;

import com.example.arely.fullstackbackend.exception.OrdersNotFoundException;
import com.example.arely.fullstackbackend.model.Orders;
import com.example.arely.fullstackbackend.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class OrdersController {


    @Autowired
    private OrdersRepository ordersRepository;

    @PostMapping(path="/orders") //was path="/user"
    Orders newOrders(@RequestBody Orders newOrders){
        return ordersRepository.save(newOrders);
    }

    @GetMapping(path="/orders")
    List<Orders> getAllOrderss(){
        return ordersRepository.findAll();
    }

    @GetMapping("/orders/{id}")
    Orders getOrdersById(@PathVariable Long id){
        return ordersRepository.findById(id)
                .orElseThrow(()-> new OrdersNotFoundException(id));
    }

    @PutMapping("/orders/{id}")
    Orders updateOrders(@RequestBody Orders newOrders, @PathVariable Long id){
        return ordersRepository.findById(id)
                .map(orders -> {
                    orders.setDateRequestReceived(newOrders.getDateRequestReceived()); //was ordersname
                    orders.setDepartment(newOrders.getDepartment()); //was name
                    orders.setShoppingCartNo(newOrders.getShoppingCartNo());//was email
                    orders.setPoNo(newOrders.getPoNo());
                    orders.setVendorName(newOrders.getVendorName());
                    orders.setRequestorName(newOrders.getRequestorName());
                    orders.setDescription(newOrders.getDescription());
                    orders.setAmount(newOrders.getAmount());
                    orders.setFundNo(newOrders.getFundNo());
                    orders.setDatePOCreated(newOrders.getDatePOCreated());
                    orders.setVendorNo(newOrders.getVendorNo());
                    orders.setShipTo(newOrders.getShipTo());
                    orders.setObjectNo(newOrders.getObjectNo());
                    orders.setLocationNo(newOrders.getLocationNo());
                    orders.setProgramNo(newOrders.getProgramNo());
                    orders.setFunctionNo(newOrders.getFunctionNo());
                    orders.setSapOrCreditCard(newOrders.getSapOrCreditCard());
                    orders.setDateApproved(newOrders.getDateApproved());
                    orders.setDateGottardiApproved(newOrders.getDateGottardiApproved());
                    orders.setProcessorName(newOrders.getProcessorName());
                    orders.setStatusGoodReceipts(newOrders.getStatusGoodReceipts());
                    orders.setInvoiceStatus(newOrders.getInvoiceStatus());
                    return ordersRepository.save(orders);
                }).orElseThrow(()-> new OrdersNotFoundException(id));//creates a new array from calling a function for every  array
    }

    @DeleteMapping("/orders/{id}")
    String deleteOrders(@PathVariable Long id){
        if(!ordersRepository.existsById(id)){
            throw new OrdersNotFoundException(id);
        }
        ordersRepository.deleteById(id);
        return "Orders with id "+ id+ " has been deleted successfully.";
    }

}
