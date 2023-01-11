package com.dh.PIG11.service;

import com.dh.PIG11.dto.CategoryDTO;
import com.dh.PIG11.entities.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICategoryService {
    ResponseEntity<Category> agregarCategoria(CategoryDTO category);
    List<CategoryDTO> listarCategorias();
    ResponseEntity<Category> editarCategoria(CategoryDTO category);
    void eliminarCategoria (Long id);
    CategoryDTO buscarCategoria(Long id);





}
