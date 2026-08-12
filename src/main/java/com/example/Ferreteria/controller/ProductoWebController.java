package com.example.Ferreteria.controller;

import com.example.Ferreteria.model.Producto;
import com.example.Ferreteria.service.IProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoWebController {

    private final IProductoService prodServ;

    public ProductoWebController(IProductoService prodServ) {
        this.prodServ = prodServ;
    }

    //Get para traer todos los productos
    @GetMapping
    public String traerProductos(Model model) {
        model.addAttribute(
                "productos",
                prodServ.traerProductos()
        );

        return "productos/lista";
    }

    //Get para traer la página/vista del formulario
    @GetMapping("/nuevo")
    public String mostrarFormulario (Model model) {

        model.addAttribute("producto", new Producto());
        model.addAttribute("titulo", "Registrar Producto");

        return "productos/formulario";
    }

    //Post para guardar el formulario
    @PostMapping("/crear")
    public String crearProducto (@ModelAttribute Producto producto, Model model) {

        Producto resultado;

        if (producto.getCodProducto() == null) {
            resultado = prodServ.crearProducto(producto);
        } else {
            resultado = prodServ.editarProducto(
                    producto.getCodProducto(),
                    producto
            );
        }

        if (resultado == null) {
            model.addAttribute("producto", producto);
            model.addAttribute(
                    "titulo",
                    producto.getCodProducto() == null
                            ? "Registrar producto"
                            : "Editar producto"

                    /* Con IF ELSE
                    * if (producto.getCodProducto() == null) {
                        titulo = "Registrar producto";
                    } else {
                        titulo = "Editar producto";
                    }
                    *
                    * */
            );
            model.addAttribute(
                    "error",
                    "Revisá los datos. Nombre, marca y categoría son obligatorios. " +
                            "El precio debe ser mayor a cero y el stock no puede ser negativo."
            );

            return "productos/formulario";
        }

        return "redirect:/productos";
    }

    //Get para traer la página/vista del formulario de edición
    @GetMapping("/editar/{codProd}")
    public String mostrarFormularioEditar(
            @PathVariable Long codProd,
            Model model) {

        Producto producto = prodServ.buscarProducto(codProd);

        if (producto == null) {
            return "redirect:/productos";
        }

        model.addAttribute("producto", producto);
        model.addAttribute("titulo", "Editar producto");

        return "productos/formulario";
    }

    //Post para eliminar un producto
    @PostMapping("/eliminar/{codProd}")
    public String eliminarproducto(@PathVariable Long codProd) {

        prodServ.eliminarProducto(codProd);

        return "redirect:/productos";
    }

}
