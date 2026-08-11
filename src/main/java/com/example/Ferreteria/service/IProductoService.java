package com.example.Ferreteria.service;

import com.example.Ferreteria.model.Producto;

import java.util.List;

public interface IProductoService {
    // Métodos para el CRUD

    //READ
    List<Producto> traerProducto();
    Producto buscarProducto(Long codProd);

    //CREATE
    Producto crearProducto(Producto prod);

    //UPDATE
    Producto editarProducto(Long codProd, Producto prod);

    //DELETE
    boolean eliminarProducto(Long codProd);
}
