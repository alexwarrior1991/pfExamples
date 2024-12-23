package com.alejandro.webapp.pfexamples.controllers;

import com.alejandro.webapp.pfexamples.entities.Producto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Named
@RequestScoped
public class ProductoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Producto> productos;
    private Producto selectedProduct;

    @PostConstruct
    public void init() {
        productos = new ArrayList<>();
        productos.add(new Producto(1, "Laptop"));
        productos.add(new Producto(2, "Smartphone"));
        productos.add(new Producto(3, "Tablet"));
        productos.add(new Producto(4, "Shoes"));
        productos.add(new Producto(5, "Boots"));

        System.out.println("Products initialized with size: " + productos.size());
        productos.forEach(p -> System.out.println("Product: " + p.getName() + ", ID: " + p.getId()));
    }

    public List<Producto> completeProduct(String query) {

        System.out.println("Autocomplete query: " + query);
        System.out.println(productos);

        var filteredProducts = productos.stream()
                .filter(p -> p.getName().toLowerCase().startsWith(query.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("Filtered products size: " + filteredProducts.size());
        filteredProducts.forEach(p -> System.out.println("Filtered product: " + p.getName()));

        return filteredProducts;
    }

    public Producto getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Producto selectedProduct) {
        System.out.println("Selected product: " + selectedProduct);
        this.selectedProduct = selectedProduct;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
