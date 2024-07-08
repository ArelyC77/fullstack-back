package com.example.arely.fullstackbackend.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserManagementController {

    @Autowired
    private UserManagementService userManagementService;

    @PostMapping("/auth/register")
    public ResponseEntity<RequestResponse> register(@RequestBody RequestResponse registration){
        return ResponseEntity.ok(userManagementService.register(registration));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<RequestResponse> login(@RequestBody RequestResponse request){
        return ResponseEntity.ok(userManagementService.login(request));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<RequestResponse> refreshToken(@RequestBody RequestResponse request){
        return ResponseEntity.ok(userManagementService.refreshToken(request));
    }

    @GetMapping("/admin/get-all-users")
    public ResponseEntity<RequestResponse> getAllUsers(){
        return ResponseEntity.ok(userManagementService.getAllUsers());
    }

    @GetMapping("/admin/get-users/{employeeID}")
    public ResponseEntity<RequestResponse> getUserByID(@PathVariable Integer employeeID){
        return ResponseEntity.ok(userManagementService.getUsersById(employeeID));
    }

    @PutMapping("/admin/update/{employeeID}")
    public ResponseEntity<RequestResponse> updateUser(@PathVariable Integer employeeID, @RequestBody User requestResponse){
        return ResponseEntity.ok(userManagementService.updateUser(employeeID, requestResponse));
    }

    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<RequestResponse> getMyProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        RequestResponse response= userManagementService.getMyInfo(email);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/admin/delete/{employeeID}")
    public ResponseEntity<RequestResponse> deleteUser(@PathVariable Integer employeeID){
        return ResponseEntity.ok(userManagementService.deleteUser(employeeID));
    }


}
