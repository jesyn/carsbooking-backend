package com.dh.PIG11.security;

import com.dh.PIG11.dto.CityDTO;
import com.dh.PIG11.dto.RoleDTO;
import com.dh.PIG11.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@Qualifier("RoleServiceImpl")
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public ResponseEntity<Role> agregarRole(RoleDTO role) {
        Role newRole= objectMapper.convertValue(role,Role.class);
        return ResponseEntity.ok(roleRepository.save(newRole));
    }


    @Override
    public List<RoleDTO> listarRoles() {
        List<Role> allRoles = roleRepository.findAll();
        List<RoleDTO> allRolesDTO = new ArrayList<>();
        for(Role role: allRoles)
            allRolesDTO.add(objectMapper.convertValue(role,RoleDTO.class));

        return allRolesDTO;
    }

    @Override
    public void eliminarRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public RoleDTO buscarRole(Long id) throws ResourceNotFoundException{
        Optional<Role> roleBuscado=roleRepository.findById(id);
        if (roleBuscado.isPresent()){
            log.info("Comenzo el proceso: buscando role");
            return objectMapper.convertValue(roleBuscado,RoleDTO.class);
        }
        else {
            throw new ResourceNotFoundException("No se encontro el role con id= "+id+". Error al ingresar el id.");

        }
    }
}
