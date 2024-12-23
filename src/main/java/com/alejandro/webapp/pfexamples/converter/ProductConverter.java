package com.alejandro.webapp.pfexamples.converter;

import com.alejandro.webapp.pfexamples.controllers.ProductoBean;
import com.alejandro.webapp.pfexamples.entities.Producto;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

import java.util.List;

@FacesConverter("productConverter")
public class ProductConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        ProductoBean productBean = context.getApplication().evaluateExpressionGet(context, "#{productoBean}", ProductoBean.class);
        List<Producto> productos = productBean.getProductos();
        for (Producto producto : productos) {
            if (producto.getId().equals(Integer.valueOf(value))) {
                return producto;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        return String.valueOf(((Producto) value).getId());
    }
}
