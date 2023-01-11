package com.dh.PIG11.controller;

import com.dh.PIG11.dto.RoleDTO;
import com.dh.PIG11.exceptions.ResourceNotFoundException;
import com.dh.PIG11.security.IRoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
@CrossOrigin
public class RoleController {
    private static final Logger logger = Logger.getLogger(IRoleService.class);
    @Autowired
    private IRoleService roleService;
    @GetMapping
    public ResponseEntity<List<RoleDTO>> listarRoles(){
        logger.info("Comenzo el proceso: listando roles");
        return ResponseEntity.ok(roleService.listarRoles());
    }
    @PostMapping
    public ResponseEntity<HttpStatus> registrarNuevoRole(@RequestBody RoleDTO role){
        logger.info("Comenzo el proceso: guardando role");
        roleService.agregarRole(role);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> buscarRoleXid(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<RoleDTO> roleBuscado= Optional.ofNullable(roleService.buscarRole(id));
        if (roleBuscado.isPresent()){
            logger.info("Comenzo el proceso: buscando role");
            return ResponseEntity.ok(roleBuscado.get());
        }
        else {
            throw new ResourceNotFoundException("No se encontro el role con id= "+id+". Error al ingresar el id.");

        }}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRole(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<RoleDTO> roleBuscado= Optional.ofNullable(roleService.buscarRole(id));
        if(roleBuscado.isPresent()){
            roleService.eliminarRole(id);
            return ResponseEntity.ok("Se elimino el role con id= "+ id);
        }
        else{
            throw new ResourceNotFoundException("No se encontro el role con id= "+id+" para eliminarlo. Error al ingresar el id.");

        }
}}
