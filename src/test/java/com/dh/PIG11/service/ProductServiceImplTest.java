/*package com.dh.PIG11.service;

import com.dh.PIG11.entities.Category;
import com.dh.PIG11.entities.City;
import com.dh.PIG11.entities.Product;
import com.dh.PIG11.service.ICategoryService;
import com.dh.PIG11.service.ICityService;
import com.dh.PIG11.service.IProductService;
import com.dh.PIG11.service.ProductServiceImpl;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private ICityService cityService;

    @Test
    @Order(1)
    public void agregarProductoTest() {
        City cityPrueba= new City(8L,"Rosario","Santa Fe","Argentina","", " ");
        Category categoryPrueba= new Category(8L,"Classic","","",10000.0);
        Product product= new Product("Ford","Focus","","","","","","","",cityPrueba,categoryPrueba);

           //Category category= categoryService.agregarCategoria(new Category());
           //City city=cityService.agregarCiudad(new City());
            assertNotNull(productService.agregarProducto(product));
        productService.eliminarProducto(product.getId());
    }

    @Test
    @Order(2)
    public void listarProductosTest() {
        City cityPrueba= new City(8L,"Rosario","Santa Fe","Argentina","","");
        Category categoryPrueba= new Category(8L,"Classic","","",10000.0);
       // Category category = categoryService.agregarCategoria(new Category());
       // City city = cityService.agregarCiudad(new City());
        Product product = productService.agregarProducto( new Product("Ford","Focus","","","","","","","",cityPrueba,categoryPrueba));
        assertNotNull(productService.listarProductos());
        productService.eliminarProducto(product.getId());
    }

    @Test
    @Order(3)
    public void buscarYeliminarProductoTest(){
        City cityPrueba= new City(1L,"Rosario","Santa Fe","Argentina","","");
        Category categoryPrueba= new Category(8L,"Classic","","",10000.0);
        Product product = productService.agregarProducto(new Product("Ford","Focus","","","","","","","",cityPrueba,categoryPrueba));

        productService.eliminarProducto(product.getId());
        Optional<Product> productoBuscado=productService.buscarProducto(product.getId());
        //assertTrue(productoBuscado.isEmpty());
    }
    @Test
    @Order(4)
    public void listarProductosXCategoriaTest() {
        City cityPrueba= new City(8L,"Rosario","Santa Fe","Argentina","", "");
        Category categoryPrueba= new Category(8L,"Classic","","",10000.0);
        // Category category = categoryService.agregarCategoria(new Category());
        // City city = cityService.agregarCiudad(new City());
        Product product1 = productService.agregarProducto(new Product("Ford","Focus","","","","","","","",cityPrueba,categoryPrueba));
        Product product2 = productService.agregarProducto(new Product("Toyota","Corolla","","","","","","","",cityPrueba,categoryPrueba));
        assertNotNull(productService.listarProductosXcategoria(2L));
        assertTrue(productService.listarProductosXcategoria(2L).size()>1);
        productService.eliminarProducto(product1.getId());
        productService.eliminarProducto(product2.getId());
    }
    @Test
    @Order(5)
    public void listarProductosXCiudadTest() {
        City cityPrueba= new City(8L,"Rosario","Santa Fe","Argentina","", "");
        Category categoryPrueba= new Category(8L,"Classic","","",10000.0);
        // Category category = categoryService.agregarCategoria(new Category());
        // City city = cityService.agregarCiudad(new City());
        Product product1 = productService.agregarProducto(new Product("Ford","Focus","","","","","","","",cityPrueba,categoryPrueba));
        Product product2 = productService.agregarProducto(new Product("Toyota","Corolla","","","","","","","",cityPrueba,categoryPrueba));
        assertNotNull(productService.listarProductosXciudad(1L));
        productService.eliminarProducto(product1.getId());
        productService.eliminarProducto(product2.getId());
    }
}*/