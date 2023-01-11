package com.dh.PIG11.security;



import com.dh.PIG11.dto.CityDTO;
import com.dh.PIG11.dto.RoleDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRoleService {
    ResponseEntity<Role> agregarRole(RoleDTO role);
    List<RoleDTO> listarRoles();
    void eliminarRole (Long id);
    RoleDTO buscarRole(Long id);
}
