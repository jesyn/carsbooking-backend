/*package com.dh.PIG11.service;

import com.dh.PIG11.entities.Category;
import com.dh.PIG11.service.ICategoryService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoryServiceImplTest {
    @Autowired
    private ICategoryService categoryService;
    @Test
    @Order(1)
    public void agregarCategoriaTest(){

        //preparo categoria para prueba
        Category categoriaParaAgregar = new Category("Deportivos","Ideal para tirar facha","imagenDep", 20000.00);

        //uso el service
        Category categoriaAgregada= categoryService.agregarCategoria(categoriaParaAgregar);
        //realizo el assert
        assertEquals(categoriaParaAgregar,categoriaAgregada);
        categoryService.eliminarCategoria(categoriaAgregada.getId());

    }
    @Test
    @Order(2)
    public void listarTodasCategoriasTest(){

        //uso el service
        List<Category> categorias= categoryService.listarCategorias();
        System.out.println("Se listaron las siguientes categorias: ");
        for (Category o:categorias) {
            System.out.println( o.getCategory()+": "+o.getDescription() +" "+" con precios desde "+ o.getPrice());
        }
        //realizo el assert
        assertTrue(categorias.size()>0);
    }
    @Test
    @Order(3)
    public void editarCategoriasTest(){
        Category categoriaParaAgregar = new Category("Deportivos","Ideal para tirar facha","imagenDep", 20000.00);
        Category categoriaAgregada= categoryService.agregarCategoria(categoriaParaAgregar);
        categoriaParaAgregar.setPrice(15000.00);

        categoryService.editarCategoria(categoriaParaAgregar);
        assertEquals(15000.00, categoriaParaAgregar.getPrice());
        categoryService.eliminarCategoria(categoriaAgregada.getId());
    }
    @Test
    @Order(4)
    public void buscarYeliminarCategoriaTest(){
        Category categoriaParaAgregar = new Category("Deportivos","Ideal para tirar facha","imagenDep", 20000.00);
        Category categoriaAgregada= categoryService.agregarCategoria(categoriaParaAgregar);


        categoryService.eliminarCategoria(categoriaParaAgregar.getId());
        Optional<Category> categoriaBuscada=categoryService.buscarCategoria(categoriaParaAgregar.getId());

        //assertTrue(categoriaBuscada.isEmpty());
    }}

*/