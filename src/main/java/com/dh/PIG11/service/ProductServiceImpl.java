package com.dh.PIG11.service;

import com.dh.PIG11.dto.*;
import com.dh.PIG11.entities.Characteristic;
import com.dh.PIG11.entities.Product;
import com.dh.PIG11.entities.ProductCharacteristic;
import com.dh.PIG11.exceptions.BadRequestException;
import com.dh.PIG11.exceptions.ResourceNotFoundException;
import com.dh.PIG11.repository.BookingRepository;
import com.dh.PIG11.repository.CharacteristicRepository;
import com.dh.PIG11.repository.ProductRepository;
import com.dh.PIG11.utils.ImageUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Slf4j
@Qualifier("ProductServiceImpl")
@Service
public class ProductServiceImpl implements IProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CharacteristicRepository characteristicRepository;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    ImageUtils imageUtils;
    @Autowired
    IImageTransferService iImageTransferService;
    @Autowired
    ICategoryService categoryService;
    @Autowired
    ICityService cityService;
    @Autowired
    IImageService imageService;

    @Autowired
    IProductCharacteristicService productCharacteristicService;




    @Override
    public ResponseEntity<Product> agregarProducto(ProductDTO product) {
        Product newProduct=  objectMapper.convertValue(product, Product.class);
        return ResponseEntity.ok(productRepository.save(newProduct));
    }
    @Transactional
    @Override
    public ProductDTO agregarProductoCompleto(ProductDTO product, MultipartFile portada,List<MultipartFile> images) {
        Product productEntity=null;
        ProductDTO productoAgregado;
        try {
        ImageTransferDTO imageTransferDTO =imageUtils.convertToImageTransferForPortada(portada);
        iImageTransferService.transferImage(imageTransferDTO);

            images.forEach(img->{
                ImageTransferDTO imgTransferDTO =imageUtils.convertToImageTransferForImage(img);
                try {
                    iImageTransferService.transferImage(imgTransferDTO);
                    product.getImages().add(new ImageDTO(img.getName(),imgTransferDTO.getUrl()));
                } catch (IOException e) {
                    throw new BadRequestException("No se pudo registrar el producto. Causa: "+ e.getMessage());
                }

            });


        Optional<CategoryDTO> categoryBuscada = Optional.ofNullable(categoryService.buscarCategoria(product.getCategory().getId()));
        if (!categoryBuscada.isPresent()) {
            throw new BadRequestException("La categoria con id= " + product.getCategory().getId() + " no existe");
        }
        Optional<CityDTO> cityBuscada = Optional.ofNullable(cityService.buscarCiudad(product.getCity().getId()));
        if (!cityBuscada.isPresent()) {
            throw new BadRequestException("La ciudad con id= " + product.getCity().getId() + " no existe");
        }
            product.setPortada_url(imageTransferDTO.getUrl());

           // product.setImages(imagesTranferDTO);

            Product newProduct=  objectMapper.convertValue(product, Product.class);

            productEntity= productRepository.save(newProduct);


            productoAgregado= objectMapper.convertValue(productEntity, ProductDTO.class);
            productoAgregado.setImages( imageService.agregarImagenes(product.getImages(), productEntity));


            for (ProductCharacteristic pcha : productCharacteristicService.agregarCharacteristics(new ArrayList<>(product.getCharacteristics()),productoAgregado.getId())){
                productoAgregado.getCharacteristics().add(new CharacteristicDTO(pcha.getCharacteristic().getId(), pcha.getCharacteristic().getCharacteristic(),pcha.getCharacteristic().getUrl_icon()));
            }




        }

         catch (Exception ex){

            //TODO.ELIMINAR LA IMAGEN

            log.error(ex.getMessage(), ex);

            throw new BadRequestException("No se pudo registrar el producto. Causa: "+ ex.getMessage());

        }
        return productoAgregado;


    }

    @Override
    public List<ProductDTO> listarProductos() {
        List<Product> allProducts = productRepository.findAll();
        List<ProductDTO> allProductsDTO = new ArrayList<>();
        ProductDTO productDTO;
        for(Product product: allProducts) {
            productDTO = objectMapper.convertValue(product, ProductDTO.class);
            productDTO.setCharacteristics(listarCaracteristicasXproducto(productDTO.getId()));
            allProductsDTO.add(productDTO);
        }

        return allProductsDTO;
    }
    @Override
    public List<ProductDTO> listarProductosAleatorio() {
        List<Product> allProductsRandom = productRepository.findAllRandom();
        List<ProductDTO> allProductsDTO = new ArrayList<>();
        for(Product product: allProductsRandom)
            allProductsDTO.add(objectMapper.convertValue(product,ProductDTO.class));

        return allProductsDTO;
    }

    @Override
    public List<ProductDTO> listarProductosXcategoria(Long category_id) {
        List<Product> allProductsXCategory = productRepository.findAllByCategoryId(category_id);
        List<ProductDTO> allProductsDTO = new ArrayList<>();
        for(Product product: allProductsXCategory)
            allProductsDTO.add(objectMapper.convertValue(product,ProductDTO.class));

        return allProductsDTO;
    }

    @Override
    public List<ProductDTO> listarProductosXciudad(Long city_id) {
        List<Product> allProductsXCity = productRepository.findAllByCityId(city_id);
        List<ProductDTO> allProductsDTO = new ArrayList<>();
        for(Product product: allProductsXCity)
            allProductsDTO.add(objectMapper.convertValue(product,ProductDTO.class));

        return allProductsDTO;
    }

    @Override
    public List<CharacteristicDTO> listarCaracteristicasXproducto(Long id) {
        List<Characteristic> allCharacteristicXProduct = characteristicRepository.findAllByProductId(id);
        List<CharacteristicDTO> allCharacterisiticsDTO = new ArrayList<>();
        for(Characteristic characteristic: allCharacteristicXProduct)
            allCharacterisiticsDTO.add(objectMapper.convertValue(characteristic,CharacteristicDTO.class));

        return allCharacterisiticsDTO;

    }


    @Override
    public ResponseEntity<Product> editarProductos(ProductDTO product) {
        Product editProduct=  objectMapper.convertValue(product, Product.class);
        return ResponseEntity.ok(productRepository.save(editProduct));
    }


   @Override
    public void eliminarProducto(Long id) {
        productRepository.deleteById(id);
    }

  @Override
    public ProductDTO buscarProducto(Long id) throws ResourceNotFoundException{

      Optional<Product> productoBuscado=productRepository.findById(id);
      if (productoBuscado.isPresent()){
          log.info("Comenzo el proceso: buscando producto");
          ProductDTO productDTO1 = objectMapper.convertValue(productoBuscado,ProductDTO.class);
          productDTO1.setCharacteristics(listarCaracteristicasXproducto(productDTO1.getId()));
          return productDTO1;
      }
      else {
          throw new ResourceNotFoundException("No se encontro el producto con id= "+id+". Error al ingresar el id.");

      }
    }
    @Override
    public List<ProductDTO> listarProductosXfechas(String start_date, String end_date) {
        List<Product> allProductsXDates = productRepository.findAllByDates(start_date,end_date);
        List<ProductDTO> allProductsDTO = new ArrayList<>();
        for(Product product: allProductsXDates)
            allProductsDTO.add(objectMapper.convertValue(product,ProductDTO.class));

        return allProductsDTO;

    }
    @Override
    public List<ProductDTO> listarProductosXfechasYCiudad(String start_date, String end_date, Long city_id) {
        List<Product> allProductsXDatesAndCity = productRepository.findAllByDatesAndCity(start_date,end_date,city_id);
        List<ProductDTO> allProductsDTO = new ArrayList<>();
        for(Product product: allProductsXDatesAndCity)
            allProductsDTO.add(objectMapper.convertValue(product,ProductDTO.class));

        return allProductsDTO;
    }
}
