package com.example.tiendaproductos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tiendaproductos.model.productos;

public interface ProductRepository extends JpaRepository<productos, Long> {


    
}
