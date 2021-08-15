package miguel.com.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import miguel.com.model.vo.PagadoPorProyectoVo;
import miguel.com.util.JDBCUtilities;

public class PagadoPorProyectoDao {

    public List<PagadoPorProyectoVo> listarpagadoproyecto(Double LimiteInferior) throws SQLException{
        
         List<PagadoPorProyectoVo> respuesta = new ArrayList<>();
         Connection conn = JDBCUtilities.getConnection();
         PreparedStatement stmt  = null;
         ResultSet rst  = null;
        try{
            String query =  "SELECT P.ID_Proyecto AS ID,sum(c.Cantidad * mc.Precio_Unidad) as VALOR"
             + " FROM Proyecto p"
             + " Join Compra c using(ID_Proyecto)"
             + " Join MaterialConstruccion mc  using(ID_MaterialConstruccion)"
             + " WHERE c.Pagado = 'Si'"
             + " GROUP BY  p.ID_Proyecto"
             + " HAVING SUM(c.Cantidad * mc.Precio_Unidad) > (?)"
             + " ORDER BY VALOR DESC";
             Integer limiteInferior = LimiteInferior.intValue();
             stmt = conn.prepareStatement(query);
             stmt.setInt(1, limiteInferior);
             rst = stmt.executeQuery();

             while (rst.next()){
                PagadoPorProyectoVo vo  = new PagadoPorProyectoVo(); 
                vo.setId(rst.getInt("ID"));
                vo.setValor( (double) rst.getInt("VALOR"));

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



    

