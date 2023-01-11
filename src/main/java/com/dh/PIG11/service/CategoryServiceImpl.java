package com.dh.PIG11.service;

import com.dh.PIG11.dto.CategoryDTO;
import com.dh.PIG11.entities.Category;
import com.dh.PIG11.exceptions.ResourceNotFoundException;
import com.dh.PIG11.repository.CategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
@Slf4j
@Qualifier("CategoryServiceImpl")
@Service
public class CategoryServiceImpl implements ICategoryService {

@Autowired
private CategoryRepository categoryRepository;
  @Autowired
    ObjectMapper objectMapper;

    @Override
    public ResponseEntity<Category> agregarCategoria(CategoryDTO category) {
    Category newCategory=  objectMapper.convertValue(category, Category.class);
         return ResponseEntity.ok(categoryRepository.save(newCategory));
    }

    @Override
    public List<CategoryDTO> listarCategorias() {
        //return categoryRepository.findAll();
        List<Category> allCategories = categoryRepository.findAll();
        List<CategoryDTO> allCategoriesDTO = new ArrayList<>();
        for(Category category: allCategories)
            allCategoriesDTO.add(objectMapper.convertValue(category,CategoryDTO.class));

        return allCategoriesDTO;
    }

    @Override
    public ResponseEntity<Category> editarCategoria(CategoryDTO category) {
        Category editCategory=  objectMapper.convertValue(category, Category.class);
        return ResponseEntity.ok(categoryRepository.save(editCategory));

    }


    @Override
    public void eliminarCategoria(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDTO buscarCategoria(Long id) throws ResourceNotFoundException{
        //return categoryRepository.findById(id);
        Optional<Category> categoriaBuscada=categoryRepository.findById(id);
        if (categoriaBuscada.isPresent()){
            log.info("Comenzo el proceso: buscando categoria");
            return objectMapper.convertValue(categoriaBuscada,CategoryDTO.class);
        }
        else {
            throw new ResourceNotFoundException("No se encontro la categoria con id= "+id+". Error al ingresar el id.");

        }

}


}
