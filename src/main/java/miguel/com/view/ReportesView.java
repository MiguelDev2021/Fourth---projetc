package miguel.com.view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import miguel.com.controller.ReportesController;
import miguel.com.model.vo.ComprasDeLiderVo;
import miguel.com.model.vo.PagadoPorProyectoVo;
import miguel.com.model.vo.ProyectoBancovo;

public class ReportesView {
    private ReportesController reportesController;
   
    public ReportesView(){
        reportesController = new ReportesController();
    }

    private String repitaCaracter(Character caracter, Integer veces) {
        String respuesta = "";
        for (int i = 0; i < veces; i++) {
            respuesta += caracter;
        }
        return respuesta;
    }

    public void proyectosFinanciadosPorBanco(String banco){
        System.out.println(repitaCaracter('=', 36) + " LISTADO DE PROYECTOS POR BANCO " + repitaCaracter('=', 37));
        if (banco != null && !banco.isEmpty()) {
            System.out.println(String.format("%3s %-25s %-20s %-15s %-7s %-30s", "ID", "CONSTRUCTORA", "CIUDAD",
                    "CLASIFICACION", "ESTRATO", "LIDER"));
            System.out.println(repitaCaracter('-', 105));
            
            try {
              List<ProyectoBancovo>  lista = reportesController.ListadoProyectoBanco(banco);
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
                List<PagadoPorProyectoVo> lista = reportesController.ListadoPagadoProyecto(limiteInferior);
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
        System.out.println(String.format("%-25s %15s", "LIDER", "VALOR "));
        System.out.println(repitaCaracter('-', 41));
        
        try {
           List<ComprasDeLiderVo> lista = reportesController.listadoLideres();
           for (ComprasDeLiderVo consulta : lista) {
            System.out.printf("%-25s %,15.1f %n", consulta.getLider() ,  consulta.getValor());
        }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
    }

}
