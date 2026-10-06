package com.example.tiendaproductos.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tiendaproductos.model.productos;
import com.example.tiendaproductos.service.ProductService;






@RestController 
@RequestMapping ("/products")
public class ProductController {

    private ProductService Servicio;

    public ProductController(ProductService Servicio) {
        this.Servicio = Servicio;
    }

    @GetMapping 
    public ResponseEntity<List<productos>> findAll(){
        return ResponseEntity.ok(Servicio.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<productos> getById(@PathVariable Long id) {
        Optional<productos> producto = Servicio.findById(id);
        if (!producto.isEmpty()) {
            return ResponseEntity.ok(producto.get());
            
        } else {
            return ResponseEntity.notFound().build();
        }
    }

   

    @PostMapping
    public ResponseEntity<productos> save(@RequestBody productos entity){
        Servicio.save(entity);

        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<productos> delete(@PathVariable Long id){
        boolean deleted = Servicio.delete(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping ("/{id}")
        public ResponseEntity<productos> update(@PathVariable Long id, @RequestBody productos entity) {
        Optional<productos> updatedProduct = Servicio.update(id, entity);
        if (updatedProduct.isPresent()) {
            return ResponseEntity.ok(updatedProduct.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
