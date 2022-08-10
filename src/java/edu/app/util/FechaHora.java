/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.app.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author andre
 */
public class FechaHora {

    Date date = Calendar.getInstance().getTime();

    public String fecha() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-YYYY");
        String dateString = sdf.format(date);
        return dateString;
    }

    public String hora() {
        SimpleDateFormat sdfHora = new SimpleDateFormat("hh:mm:ss aa");
        String horaString = sdfHora.format(date);
        return horaString;
    }

    public String primerDiaMes() {
        String fecha = "01" + fecha().substring(3, fecha().length());
        return fecha;
    }

    public String fechaSinDia() {
        String fecha = fecha().substring(2, fecha().length());
        return fecha;
    }

    public boolean bisiesto() {
        int anio = Integer.parseInt(fecha().substring((fecha().length()-4) , fecha().length()));
        GregorianCalendar calendar = new GregorianCalendar();
        if (calendar.isLeapYear(anio)) {
            return true;
        } else {
            return false;
        }
    }

    public String anio(){
        String fecha = fecha().substring((fecha().length()-4) , fecha().length());
        return fecha;
    }
    
    public String filtrarFechaInicio(String fechaIni) {
        String mesIni = fechaIni.substring(0, 2);
        String diaIni = fechaIni.substring(3, 5);
        String anioIni = fechaIni.substring(6, 10);

        switch (mesIni) {
            case "01":
                mesIni = "enero";
                break;
            case "02":
                mesIni = "febrero";
                break;
            case "03":
                mesIni = "marzo";
                break;
            case "04":
                mesIni = "abril";
                break;
            case "05":
                mesIni = "mayo";
                break;
            case "06":
                mesIni = "junio";
                break;
            case "07":
                mesIni = "julio";
                break;
            case "08":
                mesIni = "agosto";
                break;
            case "09":
                mesIni = "septiembre";
                break;
            case "10":
                mesIni = "octubre";
                break;
            case "11":
                mesIni = "noviembre";
                break;
            case "12":
                mesIni = "diciembre";
                break;
        }
        String fechaInicio = diaIni + "-" + mesIni + "-" + anioIni;
        return fechaInicio;
    }

    public String filtrarFechaFinal(String fechaFin) {
        String mesFin = fechaFin.substring(13, 15);
        String diaFin = fechaFin.substring(16, 18);
        String anioFin = fechaFin.substring(19, 23);

        switch (mesFin) {
            case "01":
                mesFin = "enero";
                break;
            case "02":
                mesFin = "febrero";
                break;
            case "03":
                mesFin = "marzo";
                break;
            case "04":
                mesFin = "abril";
                break;
            case "05":
                mesFin = "mayo";
                break;
            case "06":
                mesFin = "junio";
                break;
            case "07":
                mesFin = "julio";
                break;
            case "08":
                mesFin = "agosto";
                break;
            case "09":
                mesFin = "septiembre";
                break;
            case "10":
                mesFin = "octubre";
                break;
            case "11":
                mesFin = "noviembre";
                break;
            case "12":
                mesFin = "diciembre";
                break;
        }
        String fechaFinal = diaFin + "-" + mesFin + "-" + anioFin;
        return fechaFinal;
    }

}
