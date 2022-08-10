/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.controlador;

import edu.app.entity.Carrito;
import edu.app.entity.Orden;
import edu.app.entity.TopProductos;
import edu.app.facade.CarritoFacadeLocal;
import edu.app.facade.OrdenFacadeLocal;
import edu.app.util.FechaHora;
import edu.app.util.Redireccion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Dark Jack
 */
@Named(value = "graficaSession")
@SessionScoped
public class GraficaSession implements Serializable {

    private String datosMesString;
    private String categoriasMesString;
    private String datosDiaS;
    private String categoriasDiaS;
    private String datosAnioS;
    private String categoriasAnioS;
    private String datosTopS;
    private String categoriasTopS;

    @EJB
    OrdenFacadeLocal ordenFacadeLocal;

    @EJB
    CarritoFacadeLocal carritoFacadeLocal;

    FechaHora fh = new FechaHora();
    Redireccion redir = new Redireccion();
    List<TopProductos> listaTopProductos = new ArrayList<>();
    List<TopProductos> listaTopDiez = new ArrayList<>();
    TopProductos objTopProductos = new TopProductos();

    public GraficaSession() {
    }

    public void generarDatosMes() {
        StringBuilder datosMes = new StringBuilder();
        StringBuilder categoriasMes = new StringBuilder();

        String diaS = "";

        for (int i = 1; i < 31; i++) {
            int dia = i;
            double total = 0;

            if (dia < 10) {
                diaS = "0" + String.valueOf(dia);
            } else {
                diaS = String.valueOf(dia);
            }
            List<Orden> listaOrdenesFecha = ordenFacadeLocal.listarOrdenesFecha(diaS + fh.fechaSinDia());
            if (listaOrdenesFecha.size() > 0) {
                for (int j = 0; j < listaOrdenesFecha.size(); j++) {
                    total += listaOrdenesFecha.get(j).getTotal();
                }
                categoriasMes.append(diaS + ", ");
                datosMes.append(String.valueOf(total) + ", ");
            }
        }
        datosMesString = datosMes.toString().substring(0, (datosMes.length() - 2));
        categoriasMesString = categoriasMes.toString().substring(0, (categoriasMes.length() - 2));
        generarDatosDia();
        generarDatosAnio();
        topDiez();
        redir.redireccionar("/admin-ultimate/ordenes/estadisticas_orden.xhtml");
    }

    public void generarDatosDia() {
        StringBuilder datosDia = new StringBuilder();
        StringBuilder categoriaDia = new StringBuilder();
        List<Carrito> productosVendidos = carritoFacadeLocal.listarPorFecha(fh.fecha());
        listaTopProductos.clear();

        if (productosVendidos.size() > 0) {

            for (Carrito productosVendido : productosVendidos) {
                int cantidad = 0;
                int idProducto = 0;
                String nombre = "";
                boolean flag = true;

                List<Carrito> prods = carritoFacadeLocal.listaPorFechaProducto(fh.fecha(), productosVendido.getProductoIdproducto().getIdproducto());

                for (int i = 0; i < listaTopProductos.size(); i++) {
                    if (listaTopProductos.get(i).getIdProducto() == prods.get(0).getProductoIdproducto().getIdproducto()) {
                        flag = false;
                    }
                }
                if (flag) {
                    for (Carrito prod : prods) {
                        cantidad += prod.getCantidad();
                        idProducto = prod.getProductoIdproducto().getIdproducto();
                        nombre = prod.getProductoIdproducto().getNombreProducto();
                        TopProductos tp = new TopProductos(idProducto, cantidad, nombre);
                        listaTopProductos.add(tp);
                    }
                    datosDia.append(String.valueOf(cantidad) + ", ");
                    categoriaDia.append("'" + nombre + "', ");
                }
            }
            datosDiaS = datosDia.toString().substring(0, (datosDia.length() - 2));
            categoriasDiaS = categoriaDia.toString().substring(0, (categoriaDia.length() - 2));
        }

    }

    public void generarDatosAnio() {
        StringBuilder datosAnio = new StringBuilder();
        StringBuilder categoriasAnio = new StringBuilder();
        double total = 0;

        List<Orden> ordenesEnero = new ArrayList<>();
        List<Orden> ordenesFebrero = new ArrayList<>();
        List<Orden> ordenesMarzo = new ArrayList<>();
        List<Orden> ordenesAbril = new ArrayList<>();
        List<Orden> ordenesMayo = new ArrayList<>();
        List<Orden> ordenesJunio = new ArrayList<>();
        List<Orden> ordenesJulio = new ArrayList<>();
        List<Orden> ordenesAgosto = new ArrayList<>();
        List<Orden> ordenesSeptiembre = new ArrayList<>();
        List<Orden> ordenesOctubre = new ArrayList<>();
        List<Orden> ordenesNoviembre = new ArrayList<>();
        List<Orden> ordenesDiciembre = new ArrayList<>();

        ordenesEnero = ordenFacadeLocal.listarOrdenesPorMes("%enero-" + fh.anio() + "%");
        ordenesFebrero = ordenFacadeLocal.listarOrdenesPorMes("%febrero-" + fh.anio() + "%");
        ordenesMarzo = ordenFacadeLocal.listarOrdenesPorMes("%marzo-" + fh.anio() + "%");
        ordenesAbril = ordenFacadeLocal.listarOrdenesPorMes("%abril-" + fh.anio() + "%");
        ordenesMayo = ordenFacadeLocal.listarOrdenesPorMes("%mayo-" + fh.anio() + "%");
        ordenesJunio = ordenFacadeLocal.listarOrdenesPorMes("%junio-" + fh.anio() + "%");
        ordenesJulio = ordenFacadeLocal.listarOrdenesPorMes("%julio-" + fh.anio() + "%");
        ordenesAgosto = ordenFacadeLocal.listarOrdenesPorMes("%agosto-" + fh.anio() + "%");
        ordenesSeptiembre = ordenFacadeLocal.listarOrdenesPorMes("%septiembre-" + fh.anio() + "%");
        ordenesOctubre = ordenFacadeLocal.listarOrdenesPorMes("%octubre-" + fh.anio() + "%");
        ordenesNoviembre = ordenFacadeLocal.listarOrdenesPorMes("%noviembre-" + fh.anio() + "%");
        ordenesDiciembre = ordenFacadeLocal.listarOrdenesPorMes("%diciembre-" + fh.anio() + "%");

        if (ordenesEnero.size() > 0) {

            for (Orden ordenEne : ordenesEnero) {
                total += ordenEne.getTotal();
            }
            datosAnio.append(String.valueOf(total) + ", ");
            categoriasAnio.append("'Enero', ");
            total = 0;
        }
        if (ordenesFebrero.size() > 0) {
            for (Orden ordenFeb : ordenesFebrero) {
                total += ordenFeb.getTotal();
            }
            datosAnio.append(String.valueOf(total) + ", ");
            categoriasAnio.append("'Febrero', ");
            total = 0;
        }
        if (ordenesMarzo.size() > 0) {
            for (Orden ordenMar : ordenesMarzo) {
                total += ordenMar.getTotal();
            }
            datosAnio.append(String.valueOf(total) + ", ");
            categoriasAnio.append("'Marzo', ");
            total = 0;
        }
        if (ordenesAbril.size() > 0) {
            for (Orden ordenAbr : ordenesAbril) {
                total += ordenAbr.getTotal();
            }
            datosAnio.append(String.valueOf(total) + ", ");
            categoriasAnio.append("'Abril', ");
            total = 0;
        }

        if (ordenesMayo.size() > 0) {
            for (Orden ordenMay : ordenesMayo) {
                total += ordenMay.getTotal();
            }
            datosAnio.append(String.valueOf(total) + ", ");
            categoriasAnio.append("'Mayo', ");
            total = 0;
        }

        if (ordenesJunio.size() > 0) {
            for (Orden ordenJun : ordenesJunio) {
                total += ordenJun.getTotal();
            }
            datosAnio.append(String.valueOf(total) + ", ");
            categoriasAnio.append("'Junio', ");
            total = 0;
        }
        if (ordenesJulio.size() > 0) {
            for (Orden ordenJul : ordenesJulio) {
                total += ordenJul.getTotal();
            }
            datosAnio.append(String.valueOf(total) + ", ");
            categoriasAnio.append("'Julio', ");
            total = 0;
        }
        if (ordenesAgosto.size() > 0) {
            for (Orden ordenAgo : ordenesAgosto) {
                total += ordenAgo.getTotal();
            }
            datosAnio.append(String.valueOf(total) + ", ");
            categoriasAnio.append("'Agosto', ");
            total = 0;
        }
        if (ordenesSeptiembre.size() > 0) {
            for (Orden ordenSep : ordenesSeptiembre) {
                total += ordenSep.getTotal();
            }
            datosAnio.append(String.valueOf(total) + ", ");
            categoriasAnio.append("'Septiembre', ");
            total = 0;
        }
        if (ordenesOctubre.size() > 0) {
            for (Orden ordenOct : ordenesOctubre) {
                total += ordenOct.getTotal();
            }
            datosAnio.append(String.valueOf(total) + ", ");
            categoriasAnio.append("'Octubre', ");
            total = 0;
        }

        if (ordenesNoviembre.size() > 0) {
            for (Orden ordenNov : ordenesNoviembre) {
                total += ordenNov.getTotal();
            }
            datosAnio.append(String.valueOf(total) + ", ");
            categoriasAnio.append("'Noviembre', ");
            total = 0;
        }

        if (ordenesDiciembre.size() > 0) {
            for (Orden ordenDic : ordenesDiciembre) {
                total += ordenDic.getTotal();
            }
            datosAnio.append(String.valueOf(total) + ", ");
            categoriasAnio.append("'Diciembre'");
            total = 0;
        }
        datosAnioS = datosAnio.toString();
        categoriasAnioS = categoriasAnio.toString();
    }

    public void topDiez() {
        StringBuilder datosTop = new StringBuilder();
        StringBuilder categoriasTop = new StringBuilder();
        List<TopProductos> mayor = new ArrayList<>();
        int cant = 0;
        int idP = 0;
        String nomP = "";
        TopProductos ltd = new TopProductos(idP, cant, nomP);

        List<Carrito> productosV = carritoFacadeLocal.listarTodos();
        listaTopDiez.clear();
        int iteraciones = 0;

        if (productosV.size() > 0) {

            for (Carrito productosVendido : productosV) {
                int cantidad = 0;
                int idProducto = 0;
                String nombre = "";
                boolean flag = true;

                List<Carrito> prods = carritoFacadeLocal.listaPorProductos(productosVendido.getProductoIdproducto().getIdproducto());

                for (int i = 0; i < listaTopDiez.size(); i++) {
                    if (listaTopDiez.get(i).getIdProducto() == prods.get(0).getProductoIdproducto().getIdproducto()) {
                        flag = false;
                    }
                }
                if (flag) {
                    for (Carrito prod : prods) {
                        cantidad += prod.getCantidad();
                        idProducto = prod.getProductoIdproducto().getIdproducto();
                        nombre = prod.getProductoIdproducto().getNombreProducto();
                    }
                    TopProductos tp = new TopProductos(idProducto, cantidad, nombre);
                    listaTopDiez.add(tp);
                }
            }
            Collections.sort(listaTopDiez);
            if (listaTopDiez.size() <= 10) {
                iteraciones = listaTopDiez.size();
            } else {
                iteraciones = 10;
            }
            for (int i = 0; i < iteraciones; i++) {
                datosTop.append(listaTopDiez.get(i).getCantidad() + ", ");
                categoriasTop.append("'" + listaTopDiez.get(i).getNombre() + "', ");
            }
            datosTopS = datosTop.toString().substring(0, (datosTop.length() - 2));
            categoriasTopS = categoriasTop.toString().substring(0, (categoriasTop.length() - 2));
        }

    }

    //    GET
    //    *
    //    *
    //    *
    //    *
    //    *
    //    SET
    public String getDatosMesString() {
        return datosMesString;
    }

    public void setDatosMesString(String datosMesString) {
        this.datosMesString = datosMesString;
    }

    public String getCategoriasMesString() {
        return categoriasMesString;
    }

    public void setCategoriasMesString(String categoriasMesString) {
        this.categoriasMesString = categoriasMesString;
    }

    public String getDatosDia() {
        return datosDiaS;
    }

    public void setDatosDia(String datosDia) {
        this.datosDiaS = datosDia;
    }

    public String getCategoriasDia() {
        return categoriasDiaS;
    }

    public void setCategoriasDia(String catoegoriasDia) {
        this.categoriasDiaS = catoegoriasDia;
    }

    public String getDatosAnioS() {
        return datosAnioS;
    }

    public void setDatosAnioS(String datosAnioS) {
        this.datosAnioS = datosAnioS;
    }

    public String getCategoriasAnioS() {
        return categoriasAnioS;
    }

    public void setCategoriasAnioS(String categoriasAnioS) {
        this.categoriasAnioS = categoriasAnioS;
    }

    public String getDatosTopS() {
        return datosTopS;
    }

    public void setDatosTopS(String datosTopS) {
        this.datosTopS = datosTopS;
    }

    public String getCategoriasTopS() {
        return categoriasTopS;
    }

    public void setCategoriasTopS(String categoriasTopS) {
        this.categoriasTopS = categoriasTopS;
    }

    public String getDatosDiaS() {
        return datosDiaS;
    }

    public void setDatosDiaS(String datosDiaS) {
        this.datosDiaS = datosDiaS;
    }

    public String getCategoriasDiaS() {
        return categoriasDiaS;
    }

    public void setCategoriasDiaS(String categoriasDiaS) {
        this.categoriasDiaS = categoriasDiaS;
    }

}
