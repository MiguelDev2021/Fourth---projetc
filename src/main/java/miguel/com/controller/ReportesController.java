package miguel.com.controller;

import java.sql.SQLException;
import java.util.List;

import miguel.com.model.dao.ComprasDeLiderDao;
import miguel.com.model.dao.PagadoPorProyectoDao;
import miguel.com.model.dao.ProyectoBancoDao;
import miguel.com.model.vo.ComprasDeLiderVo;
import miguel.com.model.vo.PagadoPorProyectoVo;
import miguel.com.model.vo.ProyectoBancovo;

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
