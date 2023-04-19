package IBM.Prueba.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "proveedores")
public class ProveedoresModel {

    public ProveedoresModel() {
    }

    public ProveedoresModel(String nombre, Date fecha_alta, int id_cliente) {
        this.nombre = nombre;
        this.fecha_alta = fecha_alta;
        this.id_cliente = id_cliente;
    }
       
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id_proveedor;
    
    @Column
    private String nombre;
    
    @Column
    private Date fecha_alta;
    
    @Column
    private int id_cliente;

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
    
    
    
}
