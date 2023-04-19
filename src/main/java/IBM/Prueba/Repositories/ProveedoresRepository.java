package IBM.Prueba.Repositories;

import IBM.Prueba.Models.ProveedoresModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedoresRepository extends JpaRepository <ProveedoresModel, Integer> {
    
    @Query("SELECT e FROM ProveedoresModel e where e.id_cliente = :cliente")
    public List<ProveedoresModel> obtenerProveedores(@Param("cliente") int cliente);
    
}
