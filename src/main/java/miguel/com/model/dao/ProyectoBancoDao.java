package miguel.com.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import miguel.com.model.vo.ProyectoBancovo;
import miguel.com.util.JDBCUtilities;

public class ProyectoBancoDao {

    public List<ProyectoBancovo> listarProyectos(String banco) throws SQLException {
        List<ProyectoBancovo> respuesta = new ArrayList<>();

        Connection conn = JDBCUtilities.getConnection();
        PreparedStatement stmt = null;
        ResultSet rst = null;
        try {
            String Query = "SELECT p.ID_Proyecto AS ID , p.Constructora AS CONSTRUCTORA , p.Ciudad AS CUIDAD ,p.Clasificacion as CLASIFICACION, t.Estrato AS ESTRATO , l.Nombre || ' ' || l.Primer_Apellido ||' '|| l.Segundo_Apellido as LIDER" 
            +" From Proyecto p" 
            +" Join Tipo t using(ID_Tipo)"
            +" Join Lider l using(ID_Lider)"
            +" WHERE  Banco_Vinculado = (?)" 
            +" ORDER BY p.Fecha_Inicio DESC , p.Ciudad ASC , p.Constructora";
            
            
            stmt = conn.prepareStatement(Query);
            stmt.setString(1, banco);
            rst = stmt.executeQuery();
            while (rst.next()) {
                ProyectoBancovo vo = new ProyectoBancovo();
                vo.setId(rst.getInt("ID"));
                vo.setConstructora(rst.getString("CONSTRUCTORA"));
                vo.setCuidad(rst.getString("CUIDAD"));
                vo.setClasificacion(rst.getString("CLASIFICACION"));
                vo.setEstrato(rst.getInt("ESTRATO"));
                vo.setLider(rst.getString("LIDER"));

                respuesta.add(vo);
            }
            conn.close();

        } finally {

            if (rst != null) {
                rst.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return respuesta;
    }
}
