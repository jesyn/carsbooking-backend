package com.dh.PIG11.service;


import com.dh.PIG11.dto.CharacteristicDTO;
import com.dh.PIG11.dto.ProductDTO;
import com.dh.PIG11.entities.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface IProductService {
    ResponseEntity<Product> agregarProducto(ProductDTO product);
    ProductDTO agregarProductoCompleto(ProductDTO product, MultipartFile portada,List<MultipartFile> images) ;
    List<ProductDTO> listarProductos();
    List<ProductDTO> listarProductosAleatorio();
    List<ProductDTO> listarProductosXcategoria(Long category_id);
    List<ProductDTO> listarProductosXciudad(Long city_id);
    List<CharacteristicDTO> listarCaracteristicasXproducto(Long id);
   ResponseEntity<Product> editarProductos(ProductDTO product);
    void eliminarProducto (Long id);
    ProductDTO buscarProducto(Long id);
    List<ProductDTO> listarProductosXfechas(String start_date, String end_date);
    List<ProductDTO> listarProductosXfechasYCiudad(String start_date, String end_date,Long city_id);
}
