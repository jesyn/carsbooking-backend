package com.dh.PIG11.controller;

import com.dh.PIG11.dto.CategoryDTO;
import com.dh.PIG11.entities.Category;
import com.dh.PIG11.exceptions.ResourceNotFoundException;
import com.dh.PIG11.service.ICategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
@CrossOrigin
public class CategoryController {
    private static final Logger logger= Logger.getLogger(ICategoryService.class);
    @Autowired
    private ICategoryService categoryService;


    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> buscarCategoriasXId(@PathVariable("id") Long id)  {
        /*Optional<CategoryDTO> categoriaBuscada= categoryService.buscarCategoria(id);
        if (categoriaBuscada.isPresent()){
            logger.info("Comenzo el proceso: buscando categoria");
            return ResponseEntity.ok(categoriaBuscada.get());
        }
        else {
            throw new ResourceNotFoundException("No se encontro la categoria con id= "+id+". Error al ingresar el id.");

        }*/
        return ResponseEntity.ok(categoryService.buscarCategoria(id));

    }
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> listaCategorias(){
        logger.info("Comenzo el proceso: listando categorias");
        return ResponseEntity.ok(categoryService.listarCategorias());
    }

    @PostMapping
    public ResponseEntity<?> registrarNuevaCategoria(@RequestBody CategoryDTO category){
        logger.info("Comenzo el proceso: guardando categoria");
        categoryService.agregarCategoria(category);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> editarCategoria(@RequestBody CategoryDTO category) throws ResourceNotFoundException {
        Optional<CategoryDTO> categoriaBuscada= Optional.ofNullable(categoryService.buscarCategoria(category.getId()));
        if (categoriaBuscada.isPresent()){
            logger.info("Se edito la categoria");
            categoryService.editarCategoria(category);
            return ResponseEntity.ok(HttpStatus.OK);
        }
        else {
            throw new ResourceNotFoundException("No se encontro la categoria con id= "+ category.getId()+" para editarla. Error al ingresar el id.");

        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<CategoryDTO> categoriaBuscada= Optional.ofNullable(categoryService.buscarCategoria(id));
        if(categoriaBuscada.isPresent()){
            categoryService.eliminarCategoria(id);
            return ResponseEntity.ok("Se elimino la categoria con id= "+ id);
        }
        else{
            throw new ResourceNotFoundException("No se encontro la categoria con id= "+id+" para eliminarla. Error al ingresar el id.");

        }

    }
}
