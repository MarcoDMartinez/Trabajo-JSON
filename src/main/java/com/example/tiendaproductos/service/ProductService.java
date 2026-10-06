package com.example.tiendaproductos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.tiendaproductos.model.productos;
import com.example.tiendaproductos.repository.ProductRepository;

@Service 
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    public List<productos> getAll(){
        return productRepository.findAll();
    }

    public Optional<productos> findById(Long id){
        return productRepository.findById(id);
    }

    public productos save(productos product){
        return productRepository.save(product);
    }

    public Optional<productos> update(Long id, productos product){
        Optional<productos> optional = productRepository.findById(id);
        if(optional.isEmpty()){
            return Optional.empty();
        }
        productos productDB = optional.get();

        productDB.setNombre(product.getNombre());
        productDB.setPrecio(product.getPrecio());
        productDB.setStock(product.getStock());

        return Optional.of(productDB);
    }

    public boolean delete(Long id){
        if(!productRepository.existsById(id)){
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }
}
