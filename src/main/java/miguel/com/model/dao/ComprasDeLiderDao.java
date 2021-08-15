package miguel.com.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import miguel.com.model.vo.ComprasDeLiderVo;
import miguel.com.util.JDBCUtilities;

public class ComprasDeLiderDao {
    
    public List<ComprasDeLiderVo> ListadoLideres() throws SQLException{
        
        List<ComprasDeLiderVo> respuesta = new ArrayList<>();
        Connection conn = JDBCUtilities.getConnection();
        PreparedStatement stmt = null;
        ResultSet rst =  null;

        String query = "SELECT   l.Nombre || ' ' || l.Primer_Apellido ||' '|| l.Segundo_Apellido as LIDER, SUM(c.Cantidad * mc.Precio_Unidad) as VALOR" 
                    +" FROM Proyecto p"
                    +" Join Lider l using(ID_Lider)"
                    + " join Compra c using(ID_Proyecto)"
                    + " Join MaterialConstruccion mc using(ID_MaterialConstruccion) "
                    + " group by Lider" 
                    + " ORDER by VALOR ASC"
                    + " LIMIT 10";


        try{
            
            stmt = conn.prepareStatement(query);
            rst = stmt.executeQuery();
            while(rst.next()){
                ComprasDeLiderVo vo  = new ComprasDeLiderVo();
                vo.setLider(rst.getString("LIDER"));
                vo.setValor((double) rst.getInt("VALOR"));

                respuesta.add(vo);

            }



        }finally{
            if (rst != null){
                rst.close();
            }
            if (stmt != null){
                stmt.close();
            }
            if (conn != null){
                conn.close();
            }

        }
        



       return respuesta;     
    }


}
