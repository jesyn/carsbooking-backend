package com.dh.PIG11.controller;

import com.dh.PIG11.dto.*;
import com.dh.PIG11.entities.Characteristic;
import com.dh.PIG11.entities.Product;
import com.dh.PIG11.exceptions.BadRequestException;
import com.dh.PIG11.exceptions.ResourceNotFoundException;
import com.dh.PIG11.security.UserAuth;
import com.dh.PIG11.service.ICategoryService;
import com.dh.PIG11.service.ICityService;
import com.dh.PIG11.service.ProductServiceImpl;
import com.sun.security.auth.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.apache.commons.io.FilenameUtils;


@Slf4j
@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {
    @Value("${image.server.url}")
    private String IMAGE_URL;

    private final String FOLDER_PORTADA="products";

    @Autowired
    private final ProductServiceImpl productService;
    @Autowired
    @Qualifier("CategoryServiceImpl")
    private final ICategoryService categoryService;
    @Autowired
    @Qualifier("CityServiceImpl")
    private final ICityService cityService;
    public static String DATE_FORMAT = "yyyy-MM-dd";

    public ProductController(ProductServiceImpl productService, ICategoryService categoryService, ICityService cityService) {
        this.productService = productService;
        this.cityService = cityService;
        this.categoryService = categoryService;
    }

   /* @PostMapping
    public ResponseEntity<ResponseEntity<Product>> registrarProduct(@RequestBody ProductDTO product) throws BadRequestException {
        Optional<CategoryDTO> categoryBuscada = Optional.ofNullable(categoryService.buscarCategoria(product.getCategory().getId()));
        Optional<CityDTO> cityBuscada = Optional.ofNullable(cityService.buscarCiudad(product.getCity().getId()));
        log.info("Comenzó el proceso: guardando producto");
        if (categoryBuscada.isPresent() && cityBuscada.isPresent()) {
            return ResponseEntity.ok(productService.agregarProducto(product));
        } else {
            if (categoryBuscada.isPresent()) {
                throw new BadRequestException("La categoria con id= " + product.getCategory().getId() + " no existe");
            }
            if (cityBuscada.isPresent()) {
                throw new BadRequestException("La ciudad con id= " + product.getCity().getId() + " no existe");
            }
            throw new BadRequestException("No se pudo registrar el producto");
        }
    }*/

 @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

 //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDTO> registrarProduct(@RequestPart("images") @Valid List<MultipartFile> images,
                                                        @RequestPart("portada_url") @Valid MultipartFile portada,
                                                        @RequestPart("product") @Valid ProductDTO product) throws BadRequestException, IOException {
     //Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
     //product.setUser(new UserDTO());
     //booking.getUser().setId(((UserAuth)authentication.getPrincipal()).getId());

     return ResponseEntity.ok(productService.agregarProductoCompleto(product,portada,images));

    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> buscarProductoXId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.buscarProducto(id));}


    @GetMapping
    public ResponseEntity<List<ProductDTO>> listarProductos() {
        log.info("Comenzo el proceso: listando productos");
        return ResponseEntity.ok(productService.listarProductos());
    }

    @GetMapping("/random")
    public ResponseEntity<List<ProductDTO>> listarProductosAleatorio() {
        log.info("Comenzo el proceso: listando productos de manera aleatoria");
        return ResponseEntity.ok(productService.listarProductosAleatorio());
    }

    @GetMapping("category/{category_id}")
    public ResponseEntity<List<ProductDTO>> listarProductosXCategoria(@PathVariable("category_id") Long category_id) {
        log.info("Comenzo el proceso: listando productos por categoria");
        return ResponseEntity.ok(productService.listarProductosXcategoria(category_id));

    }

    @GetMapping("city/{city_id}")
    public ResponseEntity<List<ProductDTO>> listarProductosXCiudad(@PathVariable("city_id") Long city_id) {
        log.info("Comenzo el proceso: listando productos por ciudad");

        return ResponseEntity.ok(productService.listarProductosXciudad(city_id));
    }

    @GetMapping("/{id}/characteristics")
    public ResponseEntity<List<CharacteristicDTO>> listarCaracteristicasXproducto(@PathVariable("id") Long id) {
        log.info("Comenzo el proceso: listando caracteristicas por producto");

        return ResponseEntity.ok(productService.listarCaracteristicasXproducto(id));
    }

    @PutMapping
    public ResponseEntity<?> editarProducto(@RequestBody ProductDTO product) throws ResourceNotFoundException {
        Optional<ProductDTO> productoBuscado = Optional.ofNullable(productService.buscarProducto(product.getId()));
        if (productoBuscado.isPresent()) {
            log.info("Se edito el producto");
            return ResponseEntity.ok(productService.editarProductos(product));
        } else {
            throw new ResourceNotFoundException("No se encontro el producto con id= " + product.getId() + " para editarlo. Error al ingresar el id.");

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<ProductDTO> productoBuscado = Optional.ofNullable(productService.buscarProducto(id));
        if (productoBuscado.isPresent()) {
            productService.eliminarProducto(id);
            return ResponseEntity.ok("Se elimino el producto con id= " + id);
        } else {
            throw new ResourceNotFoundException("No se encontro el producto con id= " + id + " para eliminarlo. Error al ingresar el id.");

        }

    }

    @GetMapping("/datesAvailables")
    public ResponseEntity<List<ProductDTO>> listarProductosXfechas(@RequestParam(value="start_date") String start_date, @RequestParam(value="end_date") String end_date) throws ParseException {
        log.info("Comenzo el proceso: listando productos por fechas");
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        //Date startDate=df.parse(start_date);
        //Date endDate=df.parse(end_date);

        return ResponseEntity.ok(productService.listarProductosXfechas(start_date,end_date));

    }
    @GetMapping("/datesAndCityAvailables")
    public ResponseEntity<List<ProductDTO>> listarProductosXfechasYCiudad(@RequestParam(value="start_date") String start_date, @RequestParam(value="end_date") String end_date,@RequestParam(value = "city_id")Long city_id) throws ParseException {
        log.info("Comenzo el proceso: listando productos por fechas y ciudad");
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        //Date startDate=df.parse(start_date);
        //Date endDate=df.parse(end_date);

        return ResponseEntity.ok(productService.listarProductosXfechasYCiudad(start_date,end_date,city_id));

    }
}
