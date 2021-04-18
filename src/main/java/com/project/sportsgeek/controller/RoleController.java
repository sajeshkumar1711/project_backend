package com.project.sportsgeek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.sportsgeek.model.profile.Role;
import com.project.sportsgeek.response.Result;
import com.project.sportsgeek.service.RoleService;

@Controller
@RequestMapping(path = "/role",produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleController {
    
    @Autowired
    RoleService roleService;

    @GetMapping
    public ResponseEntity<Result<List<Role>>> getAllRole() {
        Result<List<Role>> roleList = roleService.findAllRole();
        return new ResponseEntity<>(roleList, HttpStatus.valueOf(roleList.getCode()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Result<Role>> getRoleById(@PathVariable int id) throws Exception {
        Result<Role> roleList = roleService.findRoleById(id);
        return new ResponseEntity<>(roleList, HttpStatus.valueOf(roleList.getCode()));
    }
    @PostMapping
    public ResponseEntity<Result<Role>> addRole(@RequestBody(required = true) Role role) throws  Exception {
        Result<Role> roleResult = roleService.addRole(role);
        return new ResponseEntity(roleResult,HttpStatus.valueOf(roleResult.getCode()));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Result<Role>> updateRole(@PathVariable int id,@RequestBody(required = true) Role role) throws Exception {
        Result<Role> roleResult = roleService.updateRole(id,role);
        return new ResponseEntity(roleResult,HttpStatus.valueOf(roleResult.getCode()));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Result<Role>> deleteRoleById(@PathVariable int id) throws Exception {
        Result<Integer> integerResult =  roleService.deleteRole(id);
        return new ResponseEntity(integerResult,HttpStatus.valueOf(integerResult.getCode()));
    }
}
