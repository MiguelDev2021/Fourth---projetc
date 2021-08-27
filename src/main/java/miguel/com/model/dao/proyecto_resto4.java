import java.sql.SQLException;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;

public class ReportesController {
    private ProyectoBancoDao proyectoBancoDao;
    private PagadoPorProyectoDao pagadoPorProyectoDao;
    private ComprasDeLiderDao comprasDeLiderDao;

   public ReportesController() {
       proyectoBancoDao = new ProyectoBancoDao();
       pagadoPorProyectoDao = new PagadoPorProyectoDao();
       comprasDeLiderDao = new ComprasDeLiderDao();
   }

   public List<ProyectoBancovo> ListadoProyectoBanco(String banco) throws SQLException{
        return proyectoBancoDao.listarProyectos(banco);

   }

   public List<PagadoPorProyectoVo> ListadoPagadoProyecto(Double LimiteInferior) throws SQLException{
       return pagadoPorProyectoDao.listarpagadoproyecto(LimiteInferior);

   }

   public List<ComprasDeLiderVo> listadoLideres() throws SQLException{
       return comprasDeLiderDao.ListadoLideres();
   }




}
public class ComprasDeLiderDao {
    
    public List<ComprasDeLiderVo> ListadoLideres() throws SQLException{
        
        List<ComprasDeLiderVo> respuesta = new ArrayList<>();
        var conn = JDBCUtilities.getConnection();
        PreparedStatement stmt = null;
        ResultSet rst =  null;

        var query = "SELECT   l.Nombre || ' ' || l.Primer_Apellido ||' '|| l.Segundo_Apellido as LIDER, SUM(c.Cantidad * mc.Precio_Unidad) as VALOR" 
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
public class PagadoPorProyectoDao {

    public List<PagadoPorProyectoVo> listarpagadoproyecto(Double LimiteInferior) throws SQLException{
        
         List<PagadoPorProyectoVo> respuesta = new ArrayList<>();
         var conn = JDBCUtilities.getConnection();
         PreparedStatement stmt  = null;
         ResultSet rst  = null;
        try{
            var query =  "SELECT P.ID_Proyecto AS ID,sum(c.Cantidad * mc.Precio_Unidad) as VALOR"
             + " FROM Proyecto p"
             + " Join Compra c using(ID_Proyecto)"
             + " Join MaterialConstruccion mc  using(ID_MaterialConstruccion)"
             + " WHERE c.Pagado = 'Si'"
             + " GROUP BY  p.ID_Proyecto"
             + " HAVING SUM(c.Cantidad * mc.Precio_Unidad) > (?)"
             + " ORDER BY VALOR DESC";
             var limiteInferior = LimiteInferior.intValue();
             stmt = conn.prepareStatement(query);
             stmt.setInt(1, limiteInferior);
             rst = stmt.executeQuery();

             while (rst.next()){
                var vo  = new PagadoPorProyectoVo(); 
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
public class ProyectoBancoDao {

    public List<ProyectoBancovo> listarProyectos(String banco) throws SQLException {
        List<ProyectoBancovo> respuesta = new ArrayList<>();

        var conn = JDBCUtilities.getConnection();
        PreparedStatement stmt = null;
        ResultSet rst = null;
        try {
            var Query = "SELECT p.ID_Proyecto AS ID , p.Constructora AS CONSTRUCTORA , p.Ciudad AS CUIDAD ,p.Clasificacion as CLASIFICACION, t.Estrato AS ESTRATO , l.Nombre || ' ' || l.Primer_Apellido ||' '|| l.Segundo_Apellido as LIDER" 
            +" From Proyecto p" 
            +" Join Tipo t using(ID_Tipo)"
            +" Join Lider l using(ID_Lider)"
            +" WHERE  Banco_Vinculado = (?)" 
            +" ORDER BY p.Fecha_Inicio DESC , p.Ciudad ASC , p.Constructora";
            
            
            stmt = conn.prepareStatement(Query);
            stmt.setString(1, banco);
            rst = stmt.executeQuery();
            while (rst.next()) {
                var vo = new ProyectoBancovo();
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
public class ComprasDeLiderVo {
    private String Lider;
    private Double Valor;
    public String getLider() {
        return Lider;
    }
    public void setLider(String lider) {
        Lider = lider;
    }
    public Double getValor() {
        return Valor;
    }
    public void setValor(Double valor) {
        Valor = valor;
    }

    



}

public class PagadoPorProyectoVo {

    private Integer Id;
    private Double Valor;
    public Integer getId() {
        return Id;
    }
    public void setId(Integer id) {
        Id = id;
    }
    public Double getValor() {
        return Valor;
    }
    public void setValor(Double valor) {
        Valor = valor;
    }
        
}
public class ProyectoBancovo {
    private Integer Id;
    private String Constructora;
    private String Cuidad;
    private String Clasificacion;
    private Integer estrato;
    private String lider;
   
   
    public Integer getId() {
        return Id;
    }
    public void setId(Integer id) {
        Id = id;
    }
    public String getConstructora() {
        return Constructora;
    }
    public void setConstructora(String constructora) {
        Constructora = constructora;
    }
    public String getCuidad() {
        return Cuidad;
    }
    public void setCuidad(String cuidad) {
        Cuidad = cuidad;
    }
    public String getClasificacion() {
        return Clasificacion;
    }
    public void setClasificacion(String clasificacion) {
        Clasificacion = clasificacion;
    }
 
    public String getLider() {
        return lider;
    }
    public void setLider(String lider) {
        this.lider = lider;
    }
    public Integer getEstrato() {
        return estrato;
    }
    public void setEstrato(Integer estrato) {
        this.estrato = estrato;
    }



    
}
public class JDBCUtilities {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlite:" + "ProyectosConstruccion.db";
        return DriverManager.getConnection(url);
        }
}
public class ReportesView {
    private ReportesController reportesController;

    public ReportesView(){
        reportesController = new ReportesController();
    }

    private String repitaCaracter(Character caracter, Integer veces) {
        var respuesta = "";
        for (int i = 0; i < veces; i++) {
            respuesta += caracter;
        }
        return respuesta;
    }

    public void proyectosFinanciadosPorBanco(String banco){
        System.out.println(repitaCaracter('=', 36) + " LISTADO DE PROYECTOS POR BANCO " + repitaCaracter('=', 37));
        if (banco != null && !banco.isBlank()) {
            System.out.println(String.format("%3s %-25s %-20s %-15s %-7s %-30s", "ID", "CONSTRUCTORA", "CIUDAD",
                    "CLASIFICACION", "ESTRATO", "LIDER"));
            System.out.println(repitaCaracter('-', 105));
            
            try {
               var lista = reportesController.ListadoProyectoBanco(banco);
                for (ProyectoBancovo consulta : lista) {
                    System.out.printf("%3d %-25s %-20s %-15s %7d %-30s %n", consulta.getId(), consulta.getConstructora(), consulta.getCuidad(),consulta.getClasificacion(),
                                    consulta.getEstrato(), consulta.getLider());
                }
                  
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
              
        
        }
    }

    public void totalPagadoPorProyectosSuperioresALimite(Double limiteInferior)  {
        System.out.println(repitaCaracter('=', 1) + " TOTAL PAGADO POR PROYECTO " + repitaCaracter('=', 1));
        if (limiteInferior != null) {
            System.out.println(String.format("%3s %15s", "ID", "VALOR "));
            System.out.println(repitaCaracter('-', 29));
            try {
                var lista = reportesController.ListadoPagadoProyecto(limiteInferior);
                for (PagadoPorProyectoVo consulta : lista) {
                    System.out.printf("%3d %,15.1f %n", consulta.getId() , consulta.getValor());
                }

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
               
        }
    }

    public void lideresQueMenosGastan() {
        System.out.println(repitaCaracter('=', 5) + " 10 LIDERES MENOS COMPRADORES " + repitaCaracter('=', 6));
        System.out.println(String.format("%-25s %1s", "LIDER", "VALOR "));
        System.out.println(repitaCaracter('-', 41));
        
        try {
           var lista = reportesController.listadoLideres();
           for (ComprasDeLiderVo consulta : lista) {
            System.out.printf("%-25s %,15.1f %n", consulta.getLider() ,  consulta.getValor());
        }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
    }

}


