/*
 * codigoControl.java
 *
 * Created on 12 de septiembre de 2007, 17:25
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.ayaic.web.administrarcobranza.reporteeconomico;

/**
 *
 * @eloy alberto reinaga cespedes
 */
public class codigoControl {

    public long autorizacion = 0;
    public long factura = 0;
    public long nit = 0;
    public long fecha = 20140101;
    public long monto = 5;
    public String llave = "33E265B43C4435sdTuyBVssD355FC4A6F46sdQWasdA)d56666fDsmp9846636B3";

    public codigoControl(long xautorizacion, long xfactura, long xnit, long xfecha, long xmonto, String xllave) {
        autorizacion = xautorizacion;
        factura = xfactura;
        nit = xnit;
        fecha = xfecha;
        monto = xmonto;
        llave = xllave;
    }

    private String CifrarMensajeRC4(String mensaje, String key, boolean codigo) {
        int State[] = new int[256], aux;
        int i, x = 0, y = 0, index1 = 0, index2 = 0, nmen;
        String MensajeCifrado = "";

        for (i = 0; i < 256; i++) {
            State[i] = i;
        }

        for (i = 0; i < 256; i++) {
            index2 = ((int) key.charAt(index1) + State[i] + index2) % 256;
            aux = State[i];
            State[i] = State[index2];
            State[index2] = aux;
            index1 = (index1 + 1) % key.length();
        }
        for (i = 0; i < mensaje.length(); i++) {
            x = (x + 1) % 256;
            y = (State[x] + y) % 256;
            aux = State[x];
            State[x] = State[y];
            State[y] = aux;
            nmen = (int) mensaje.charAt(i) ^ State[(State[x] + State[y]) % 256];
            if (codigo) {
                if (nmen > 15) {
                    MensajeCifrado = MensajeCifrado + "-" + Integer.toString(nmen, 16).toUpperCase();
                } else {
                    MensajeCifrado = MensajeCifrado + "-" + "0" + Integer.toString(nmen, 16).toUpperCase();
                }
            } else {
                if (nmen > 15) {
                    MensajeCifrado = MensajeCifrado + Integer.toString(nmen, 16).toUpperCase();
                } else {
                    MensajeCifrado = MensajeCifrado + "0" + Integer.toString(nmen, 16).toUpperCase();
                }
            }
        }
        if (codigo) {
            return MensajeCifrado.substring(1, MensajeCifrado.length());
        } else {
            return MensajeCifrado;
        }
    }

    private long ObtenerVerhoeff(long numero) {
        int mul[][] = {{0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
        {1, 2, 3, 4, 0, 6, 7, 8, 9, 5},
        {2, 3, 4, 0, 1, 7, 8, 9, 5, 6},
        {3, 4, 0, 1, 2, 8, 9, 5, 6, 7},
        {4, 0, 1, 2, 3, 9, 5, 6, 7, 8},
        {5, 9, 8, 7, 6, 0, 4, 3, 2, 1},
        {6, 5, 9, 8, 7, 1, 0, 4, 3, 2},
        {7, 6, 5, 9, 8, 2, 1, 0, 4, 3},
        {8, 7, 6, 5, 9, 3, 2, 1, 0, 4},
        {9, 8, 7, 6, 5, 4, 3, 2, 1, 0}};

        int per[][] = {{0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
        {1, 5, 7, 6, 2, 8, 3, 0, 9, 4},
        {5, 8, 0, 3, 7, 9, 6, 1, 4, 2},
        {8, 9, 1, 6, 0, 4, 3, 5, 2, 7},
        {9, 4, 5, 3, 1, 2, 6, 8, 7, 0},
        {4, 2, 8, 6, 5, 7, 3, 9, 0, 1},
        {2, 7, 9, 3, 8, 0, 6, 4, 1, 5},
        {7, 0, 4, 6, 9, 1, 3, 2, 5, 8}};

        long Inv[] = {0, 4, 3, 2, 1, 5, 6, 7, 8, 9};
        int NumeroInvertido[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int i = 0, longitud = 0;
        int Check = 0;
        long Cociente = 1;
        long Resto;
        while (Cociente > 0) {
            Cociente = numero / 10;
            Resto = numero % 10;
            NumeroInvertido[i++] = (int) Resto;
            numero = Cociente;
        }
        longitud = i;
        for (i = 0; i < longitud; i++) {
            Check = mul[Check][per[(i + 1) % 8][NumeroInvertido[i]]];
        }
        return Inv[Check];
    }

    private String ObtenerBase64(long numero) {
        String Dicccionario = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz+/";
        long Cociente = 1;
        long Resto;
        String Palabra = "";
        while (Cociente > 0) {
            Cociente = numero / 64;
            Resto = numero % 64;

            Palabra = Dicccionario.charAt((int) Resto) + Palabra;
            numero = Cociente;
        }
        return Palabra;
    }

    private int valpos(long numero, int pos) {
        numero = numero / (long) Math.pow(10, pos - 1);
        numero = numero % 10;
        return (int) numero;
    }

    private long calcularAscii(String mensaje, int inicio, int inc) {
        int i, suma = 0;
        for (i = inicio; i < mensaje.length(); i += inc) {
            suma += (int) mensaje.charAt(i);
        }
        return suma;
    }

    public String verCodigoControl() {
        // TODO code application logic here
        long suma;
        String sw = "1";
        String base64, codigoControl, cad1, cad2, cad3, cad4, cad5, cadena, cadenallave;
        // paso 1
        factura = factura * 10 + ObtenerVerhoeff(factura);
        factura = factura * 10 + ObtenerVerhoeff(factura);

        if (nit == 0) {
            nit = 47;
            sw = "0";
        } else {
            nit = nit * 10 + ObtenerVerhoeff(nit);
            nit = nit * 10 + ObtenerVerhoeff(nit);
        }

        fecha = fecha * 10 + ObtenerVerhoeff(fecha);
        fecha = fecha * 10 + ObtenerVerhoeff(fecha);

        monto = monto * 10 + ObtenerVerhoeff(monto);
        monto = monto * 10 + ObtenerVerhoeff(monto);

        suma = factura + nit + fecha + monto;
        suma = suma * 10 + ObtenerVerhoeff(suma);
        suma = suma * 10 + ObtenerVerhoeff(suma);
        suma = suma * 10 + ObtenerVerhoeff(suma);
        suma = suma * 10 + ObtenerVerhoeff(suma);
        suma = suma * 10 + ObtenerVerhoeff(suma);

        suma = suma % ((long) Math.pow(10, 5));
        // paso 5
        int x1 = valpos(suma, 5) + 1, x2 = valpos(suma, 4) + 1, x3 = valpos(suma, 3) + 1, x4 = valpos(suma, 2) + 1, x5 = valpos(suma, 1) + 1;

        String nnn = Integer.toString(valpos(suma, 5)) + Integer.toString(valpos(suma, 4)) + Integer.toString(valpos(suma, 3)) + Integer.toString(valpos(suma, 2)) + Integer.toString(valpos(suma, 1));
        cad1 = Long.toString(autorizacion).concat(llave.substring(0, x1));
        cad2 = Long.toString(factura).concat(llave.substring(x1, x1 + x2));
        if ("0".equals(sw)) {
            cad3 = "047".concat(llave.substring(x1 + x2, x1 + x2 + x3));
        } else {
            cad3 = Long.toString(nit).concat(llave.substring(x1 + x2, x1 + x2 + x3));
        }
        cad4 = Long.toString(fecha).concat(llave.substring(x1 + x2 + x3, x1 + x2 + x3 + x4));
        cad5 = Long.toString(monto).concat(llave.substring(x1 + x2 + x3 + x4, x1 + x2 + x3 + x4 + x5));

        cadena = cad1.concat(cad2).concat(cad3).concat(cad4).concat(cad5);
        cadenallave = llave.concat(nnn);

        codigoControl = CifrarMensajeRC4(cadena, cadenallave, false);

        long valormio = calcularAscii(codigoControl, 0, 1);
        long valor1 = (valormio * calcularAscii(codigoControl, 0, 5)) / x1;
        long valor2 = (valormio * calcularAscii(codigoControl, 1, 5)) / x2;
        long valor3 = (valormio * calcularAscii(codigoControl, 2, 5)) / x3;
        long valor4 = (valormio * calcularAscii(codigoControl, 3, 5)) / x4;
        long valor5 = (valormio * calcularAscii(codigoControl, 4, 5)) / x5;

        long numero = valor1 + valor2 + valor3 + valor4 + valor5;

        base64 = ObtenerBase64(numero);

        // paso6
        codigoControl = CifrarMensajeRC4(base64, cadenallave, true);

        return codigoControl;
    }

}
