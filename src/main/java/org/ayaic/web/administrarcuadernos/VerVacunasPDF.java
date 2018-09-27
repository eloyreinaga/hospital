package org.ayaic.web.administrarcuadernos;

import java.awt.Color;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.util.*;
import java.text.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Localidades;

public class VerVacunasPDF extends AbstractPdfView {

    private static final Font TITULO_FONT = new Font(Font.HELVETICA, 12, Font.BOLD, Color.black);
    private static final Font CABEZA_COLUMNA_FONT = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.black);
    private static final Font DATO_FONT = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.black);
    private static final Font DATO_FONT_BOLD = new Font(Font.HELVETICA, 8, Font.BOLD, Color.black);
    private static final int MARGIN = 32;

    protected void buildPdfMetadata(Map model, Document document, HttpServletRequest request) {
        Localidades dl = (Localidades) model.get("localidades");
        document.setPageSize(PageSize.LEGAL.rotate());
        document.setMargins(dl.getIzquierda(), dl.getDerecha(), dl.getSuperior(), dl.getInferior());
    }

    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {

     java.util.List lFopos = (java.util.List)model.get("listaCobros");
     java.util.List estab = (java.util.List)model.get("estab");
     Clientes dato = (Clientes)model.get("dato");
     int[] sumas={0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0};
     int[] sumbs={0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0};
     int[] sumcs={0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0};
     int una,filas=35;
     
     //Localidades Estable = (Localidades) estab.get(0);    
      
      if(lFopos.size()==0){
         Paragraph  p = new Paragraph("No existe Datos", new Font(Font.HELVETICA, 22, Font.BOLDITALIC, new Color(0, 0, 0)) );
         document.add(p);   
     }    
     
     if(lFopos.size()%filas==0) una=0; else una=1;
         
     for (int pag = 0; pag < lFopos.size()/filas+una ; pag++) {
         Paragraph  p ;
         PdfPCell cell;     
              
         int NumColumns =61;
         PdfPTable   table = new PdfPTable(NumColumns);
         int[] fwidths = {20,37,37,54,150,45,10,10,10,10, 10,10,10,10,10, 10,10,10,10,10, 10,10,10,10,10, 10,10,10,10,10, 10,10,10,10,10, 10,10,10,10,10, 10,10,10,10,10, 10,10,10,10,10, 10,10,10,10,10, 10,10,10,10,10,30}; 
         table.setWidths(fwidths);
         table.setWidthPercentage(100);

         cell = new PdfPCell(new Phrase("REGISTRO DIARIO DE VACUNACION GENERAL" , TITULO_FONT));
         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
         cell.setColspan(NumColumns);
         table.addCell(cell);

         String sCampos [] = {"No","FECHA","Numero \nHistoria\nClinica","Numero de\nCarnet de\nAsegurado","NOMBRE","Edad","M","F","BCG\nUnica"};    
         // coloca la cabecera de titulos
         for (int i = 0; i < 6; i++) {
           cell = new PdfPCell(new Phrase(sCampos[i], CABEZA_COLUMNA_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           table.addCell(cell);
         }
         
         PdfPTable   table1 = new PdfPTable(2);
         int[] fmwidths = {50,50}; // percentage
         table1.setWidths(fmwidths);
         table1.setWidthPercentage(100);

         table1 = new PdfPTable(30);
         int[] xwidths = {5,5, 5,5,5,5,5,5, 5,5,5,5,5,5, 5,5,5,5,5,5, 5,5,5,5,5,5, 5,5,5,5}; // percentage
         table1.setWidths(xwidths);
         table1.setWidthPercentage(100);

         cell = new PdfPCell(new Phrase("MENORES DE 1 AÑO", CABEZA_COLUMNA_FONT));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setColspan(30);
         table1.addCell(cell);
         
         addtitulofila2(table1,"BCG","M","F");
         addtitulofila3(table1,"Pentavalente","1a","2a","3a");
         addtitulofila3(table1,"OPV(Antipolio)","1a","2a","3a");  
         addtitulofila3(table1,"Anti-rotavirus","1a","2a","3a");
         addtitulofila3(table1,"Neumococica","1a","2a","3a");
         addtitulofila21(table1,"Infl.Est/n 6 a 11m","1a","2a");  
         
         cell = new PdfPCell(table1);
         cell.setColspan(30);
         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
         table.addCell(cell);

         PdfPTable   table11 = new PdfPTable(2);
         int[] fmwidths1 = {50,50}; // percentage
         table11.setWidths(fmwidths1);
         table11.setWidthPercentage(100);

         table11 = new PdfPTable(24);
         int[] xwidths1 = {5,5,5,5,5, 5,5,5,5,5, 5,5,5,5,5, 5,5,5,5,5, 5,5,5,5}; // percentage
         table11.setWidths(xwidths1);
         table11.setWidthPercentage(100);

         cell = new PdfPCell(new Phrase("DE 1 AÑO (12 a 23 meses)", CABEZA_COLUMNA_FONT));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setColspan(24);
         table11.addCell(cell);
         
         addtitulofila2(table11,"SRP 1ra","M","F");
         addtitulofila2(table11,"SRP 2da","M","F");
         addtitulofila2(table11,"Anti\nAmar.","M","F");
         addtitulofila4(table11,"Pentavelente","1a","2a","3a","4a");
         addtitulofila4(table11,"OPV Antipolio","1a","2a","3a","4a");
         addtitulofila2(table11,"Inf.Est","M","F");

         cell = new PdfPCell(table11);
         cell.setColspan(24);
         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
         table.addCell(cell);

         cell = new PdfPCell(new Phrase("Atendido \npor", CABEZA_COLUMNA_FONT));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setRotation(90);
         table.addCell(cell);
    
// coloca el detalle de loS datos
        for (int i = pag*filas+0; i<pag*filas+filas && i < lFopos.size(); i++) {

           Cuadernos datopac = (Cuadernos) lFopos.get(i);	 

           cell = new PdfPCell(new Phrase(Integer.toString(i+1) , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_LEFT);
           table.addCell(cell);

           cell = new PdfPCell(new Phrase(format(datopac.getFechap(),"dd/MM/yy") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_LEFT);
           table.addCell(cell);

           cell = new PdfPCell(new Phrase(Integer.toString(datopac.getId_historial()) , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_LEFT);
           table.addCell(cell);

           cell = new PdfPCell(new Phrase(datopac.getRegistro() , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_LEFT);
           table.addCell(cell);

           cell = new PdfPCell(new Phrase((datopac.getNombres().length()>28)?datopac.getNombres().substring(0,28):datopac.getNombres() , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_LEFT);
           table.addCell(cell);
           
           if(datopac.getEdad()<5){
              cell = new PdfPCell(new Phrase(Integer.toString(datopac.getEdad())+"a"+Integer.toString(datopac.getMes())+"m"+Integer.toString(datopac.getDia())+"d" , DATO_FONT)); 
           }else{
              cell = new PdfPCell(new Phrase(Integer.toString(datopac.getEdad())+" a" , DATO_FONT)); 
           }
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           table.addCell(cell);
////bcg menor de un año masculino
           cell = new PdfPCell(new Phrase(((datopac.getBcg()==1 && datopac.getId_tipo_sexo()==2)?"1":"") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           table.addCell(cell);
           sumas[0]+=(datopac.getBcg()==1 && datopac.getId_tipo_sexo()==2)?1:0;
////bcg menor de un año femenino           
           cell = new PdfPCell(new Phrase(((datopac.getBcg()==1 && datopac.getId_tipo_sexo()==1)?"1":"") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           table.addCell(cell);
           sumas[1]+=(datopac.getBcg()==1 && datopac.getId_tipo_sexo()==1)?1:0;
////Penta menor de un año                    
           int puno=0,pdos=0,ptres=0,pcuatro=0,pcinco=0,pseis=0,psiete=0,pocho=0,pnueve=0,pdiez=0;
           if("P".equals(datopac.getPenta()) && datopac.getEdad()<1 && datopac.getId_tipo_sexo()==2) puno++;
           if("P".equals(datopac.getPenta()) && datopac.getEdad()<1 && datopac.getId_tipo_sexo()==1) pdos++;
           if("S".equals(datopac.getPenta()) && datopac.getEdad()<1 && datopac.getId_tipo_sexo()==2) ptres++;
           if("S".equals(datopac.getPenta()) && datopac.getEdad()<1 && datopac.getId_tipo_sexo()==1) pcuatro++;
           if("T".equals(datopac.getPenta()) && datopac.getEdad()<1 && datopac.getId_tipo_sexo()==2) pcinco++;
           if("T".equals(datopac.getPenta()) && datopac.getEdad()<1 && datopac.getId_tipo_sexo()==1) pseis++;
           
           sumas[2]+=puno;
           sumas[3]+=pdos;
           sumas[4]+=ptres;
           sumas[5]+=pcuatro;
           sumas[6]+=pcinco;
           sumas[7]+=pseis;
           
           cell = new PdfPCell(new Phrase((puno==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);

           cell = new PdfPCell(new Phrase((pdos==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((ptres==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcuatro==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcinco==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pseis==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);

           puno=0;pdos=0;ptres=0;pcuatro=0;pcinco=0;pseis=0;
////Polio menor de un año              
           if("P".equals(datopac.getPolio()) && datopac.getEdad()<1 && datopac.getId_tipo_sexo()==2) puno++;////////
           if("P".equals(datopac.getPolio()) && datopac.getEdad()<1 && datopac.getId_tipo_sexo()==1) pdos++;
           if("S".equals(datopac.getPolio()) && datopac.getEdad()<1 && datopac.getId_tipo_sexo()==2) ptres++;
           if("S".equals(datopac.getPolio()) && datopac.getEdad()<1 && datopac.getId_tipo_sexo()==1) pcuatro++;////////
           if("T".equals(datopac.getPolio()) && datopac.getEdad()<1 && datopac.getId_tipo_sexo()==2) pcinco++;
           if("T".equals(datopac.getPolio()) && datopac.getEdad()<1 && datopac.getId_tipo_sexo()==1) pseis++;
           sumas[8]+=puno;
           sumas[9]+=pdos;
           sumas[10]+=ptres;
           sumas[11]+=pcuatro;
           sumas[12]+=pcinco;
           sumas[13]+=pseis;
           
           cell = new PdfPCell(new Phrase((puno==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);

           cell = new PdfPCell(new Phrase((pdos==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((ptres==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcuatro==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcinco==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pseis==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
////Ante-Rotavirus menor de un año                
           puno=0;pdos=0;ptres=0;pcuatro=0;pcinco=0;pseis=0;
           if("P".equals(datopac.getAnti()) && datopac.getEdad()<1 && datopac.getId_tipo_sexo()==2) puno++;
           if("P".equals(datopac.getAnti()) && datopac.getEdad()<1 && datopac.getId_tipo_sexo()==1) pdos++;
           if("S".equals(datopac.getAnti()) && datopac.getEdad()<1 && datopac.getId_tipo_sexo()==2) ptres++;
           if("S".equals(datopac.getAnti()) && datopac.getEdad()<1 && datopac.getId_tipo_sexo()==1) pcuatro++;
           if("T".equals(datopac.getAnti()) && datopac.getEdad()<1 && datopac.getId_tipo_sexo()==2) pcinco++;
           if("T".equals(datopac.getAnti()) && datopac.getEdad()<1 && datopac.getId_tipo_sexo()==1) pseis++;
           sumas[14]+=puno;
           sumas[15]+=pdos;
           sumas[16]+=ptres;
           sumas[17]+=pcuatro;
           sumas[18]+=pcinco;
           sumas[19]+=pseis;
           
           cell = new PdfPCell(new Phrase((puno==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);

           cell = new PdfPCell(new Phrase((pdos==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((ptres==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcuatro==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcinco==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pseis==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
///////Neumococica  para todos menores de 5 años
           puno=0;pdos=0;ptres=0;pcuatro=0;pcinco=0;pseis=0;
           if("P".equals(datopac.getBacterias()) && datopac.getEdad()<5 && datopac.getId_tipo_sexo()==2) puno++;
           if("P".equals(datopac.getBacterias()) && datopac.getEdad()<5 && datopac.getId_tipo_sexo()==1) pdos++;
           if("S".equals(datopac.getBacterias()) && datopac.getEdad()<5 && datopac.getId_tipo_sexo()==2) ptres++;
           if("S".equals(datopac.getBacterias()) && datopac.getEdad()<5 && datopac.getId_tipo_sexo()==1) pcuatro++;
           if("T".equals(datopac.getBacterias()) && datopac.getEdad()<5 && datopac.getId_tipo_sexo()==2) pcinco++;
           if("T".equals(datopac.getBacterias()) && datopac.getEdad()<5 && datopac.getId_tipo_sexo()==1) pseis++;
           sumas[20]+=puno;
           sumas[21]+=pdos;
           sumas[22]+=ptres;
           sumas[23]+=pcuatro;
           sumas[24]+=pcinco;
           sumas[25]+=pseis;
           
           cell = new PdfPCell(new Phrase((puno==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);

           cell = new PdfPCell(new Phrase((pdos==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((ptres==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcuatro==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcinco==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pseis==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
           ///INFLUENZA de 6 a 11 meses
           puno=0;pdos=0;ptres=0;pcuatro=0;
           if(datopac.getCancer()==6 && datopac.getEdad()==0 && datopac.getId_tipo_sexo()==2 ) puno++;
           if(datopac.getCancer()==6 && datopac.getEdad()==0 && datopac.getId_tipo_sexo()==1 ) pdos++;
           if(datopac.getCancer()==7 && datopac.getEdad()==0 && datopac.getId_tipo_sexo()==2 ) ptres++;
           if(datopac.getCancer()==7 && datopac.getEdad()==0 && datopac.getId_tipo_sexo()==1 ) pcuatro++;
           sumas[26]+=puno;
           sumas[27]+=pdos;
           sumas[28]+=ptres;
           sumas[29]+=pcuatro;
           
           cell = new PdfPCell(new Phrase((puno==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);

           cell = new PdfPCell(new Phrase((pdos==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((ptres==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcuatro==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           puno=0;pdos=0;ptres=0;pcuatro=0;pcinco=0;pseis=0;psiete=0;pocho=0;pnueve=0;
           if("1".equals(datopac.getSrp()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=1 && datopac.getEdad()<2) puno++;
           if("1".equals(datopac.getSrp()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=1 && datopac.getEdad()<2) pdos++;
           if("2".equals(datopac.getSrp()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=1 && datopac.getEdad()<2) ptres++;
           if("2".equals(datopac.getSrp()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=1 && datopac.getEdad()<2) pcuatro++;
           if("1".equals(datopac.getFama()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=1 && datopac.getEdad()<2) pcinco++;
           if("1".equals(datopac.getFama()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=1 && datopac.getEdad()<2) pseis++;
           sumas[30]+=puno;
           sumas[31]+=pdos;
           sumas[32]+=ptres;
           sumas[33]+=pcuatro;
           sumas[34]+=pcinco;
           sumas[35]+=pseis;
           
           cell = new PdfPCell(new Phrase((puno==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);

           cell = new PdfPCell(new Phrase((pdos==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((ptres==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcuatro==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcinco==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pseis==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           puno=0;pdos=0;ptres=0;pcuatro=0;pcinco=0;pseis=0;psiete=0;pocho=0;pnueve=0;
           if("P".equals(datopac.getPenta()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=1 && datopac.getEdad()<2 ) puno++;
           if("P".equals(datopac.getPenta()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=1 && datopac.getEdad()<2 ) pdos++;
           if("S".equals(datopac.getPenta()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=1 && datopac.getEdad()<2 ) ptres++;
           if("S".equals(datopac.getPenta()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=1 && datopac.getEdad()<2 ) pcuatro++;
           if("T".equals(datopac.getPenta()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=1 && datopac.getEdad()<2 ) pcinco++;
           if("T".equals(datopac.getPenta()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=1 && datopac.getEdad()<2 ) pseis++;
           if("C".equals(datopac.getPenta()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=1 && datopac.getEdad()<2 ) psiete++;
           if("C".equals(datopac.getPenta()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=1 && datopac.getEdad()<2 ) pocho++;
           sumas[36]+=puno;
           sumas[37]+=pdos;
           sumas[38]+=ptres;
           sumas[39]+=pcuatro;
           sumas[40]+=pcinco;
           sumas[41]+=pseis;
           sumas[42]+=psiete;
           sumas[43]+=pocho;
           
           cell = new PdfPCell(new Phrase((puno==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);

           cell = new PdfPCell(new Phrase((pdos==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((ptres==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcuatro==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcinco==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pseis==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((psiete==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pocho==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
          
           puno=0;pdos=0;ptres=0;pcuatro=0;pcinco=0;pseis=0;psiete=0;pocho=0;pnueve=0;
           if("P".equals(datopac.getPolio()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=1 && datopac.getEdad()<2 ) puno++;
           if("P".equals(datopac.getPolio()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=1 && datopac.getEdad()<2 ) pdos++;
           if("S".equals(datopac.getPolio()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=1 && datopac.getEdad()<2 ) ptres++;
           if("S".equals(datopac.getPolio()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=1 && datopac.getEdad()<2 ) pcuatro++;
           if("T".equals(datopac.getPolio()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=1 && datopac.getEdad()<2 ) pcinco++;
           if("T".equals(datopac.getPolio()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=1 && datopac.getEdad()<2 ) pseis++;
           if("C".equals(datopac.getPolio()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=1 && datopac.getEdad()<2 ) psiete++;
           if("C".equals(datopac.getPolio()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=1 && datopac.getEdad()<2 ) pocho++;
           sumas[44]+=puno;
           sumas[45]+=pdos;
           sumas[46]+=ptres;
           sumas[47]+=pcuatro;
           sumas[48]+=pcinco;
           sumas[49]+=pseis;
           sumas[50]+=psiete;
           sumas[51]+=pocho;
           
           cell = new PdfPCell(new Phrase((puno==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);

           cell = new PdfPCell(new Phrase((pdos==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((ptres==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcuatro==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcinco==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pseis==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((psiete==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pocho==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);         table.addCell(cell);
           
            ///INFLUENZA de 6 a 11 meses
           puno=0;pdos=0;
           if(datopac.getCancer()==8 && datopac.getEdad()==1 && datopac.getId_tipo_sexo()==2 ) puno++;
           if(datopac.getCancer()==8 && datopac.getEdad()==1 && datopac.getId_tipo_sexo()==1 ) pdos++;
           sumas[52]+=puno;
           sumas[53]+=pdos;
           
           cell = new PdfPCell(new Phrase((puno==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);

           cell = new PdfPCell(new Phrase((pdos==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
                   
           cell = new PdfPCell(new Phrase(Integer.toString(datopac.getId_persona()) , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           table.addCell(cell);  
         
         }
         for (int i = lFopos.size(); i<pag*filas+filas ; i++) {
                     cell = new PdfPCell(new Phrase(Integer.toString(i+1) , DATO_FONT));
                     cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                     table.addCell(cell);
                     for (int j =  1;j<NumColumns; j++) {
                         cell = new PdfPCell(new Phrase("" , DATO_FONT));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         table.addCell(cell);                         
                     }                           
         }
         
         cell = new PdfPCell(new Phrase("TOTAL" , DATO_FONT));
         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
         cell.setColspan(6);
         table.addCell(cell);
         
         for (int j =  0;j<54; j++) {
             cell = new PdfPCell(new Phrase(Integer.toString(sumas[j]) , DATO_FONT));
             cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
             table.addCell(cell);         
         } 
         
         for (int j = 1;j<54; j++) {
                 cell = new PdfPCell(new Phrase("" , DATO_FONT));
                 cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                 table.addCell(cell);
                     }
         
         document.add(table); 
         
         //// NUEVO HOJA segunda hoja      
         document.newPage(); 
           
         NumColumns =64;
         table = new PdfPTable(NumColumns);
         int[] fwnidthsa = {15, 8,8,8,8,8, 8,8,8,8,8, 8,8,8,8,8, 8,8,8,8,8, 8,8,8,8,8, 8,8,8,8,8, 8,8,8,8,8, 8,8,8,8,8, 8,8,8,8,8, 8,8,8,8,8, 8,8,8,8,8, 8,8,8,8,8, 8,8,16}; 
         table.setWidths(fwnidthsa);
         table.setWidthPercentage(100);

         cell = new PdfPCell(new Phrase("REGISTRO DIARIO DE VACUNACION" , TITULO_FONT));
         cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
         cell.setColspan(NumColumns);
         table.addCell(cell);

    // coloca la cabecera de titulos
         cell = new PdfPCell(new Phrase("No", CABEZA_COLUMNA_FONT));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         table.addCell(cell);
         
          
         table1 = new PdfPTable(22);
         int[] xwidths3 = {5,5,5,5,5, 5,5,5,5,5, 5,5,5,5,5, 5,5,5,5,5, 5,5}; // percentage
         table1.setWidths(xwidths3);
         table1.setWidthPercentage(100);

         cell = new PdfPCell(new Phrase("De 2 a 3 años", CABEZA_COLUMNA_FONT));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setColspan(22);
         table1.addCell(cell);
         
         addtitulofila2(table1,"SRP 1ra","M","F");
         addtitulofila2(table1,"SRP 2da","M","F");
         addtitulofila2(table1,"Anti\nAma","M","F");
         addtitulofila4(table1,"Pentavelente","1a","2a","3a","4a");
         addtitulofila4(table1,"OPV Antipolio","1a","2a","3a","4a");   
         
         cell = new PdfPCell(table1);
         cell.setColspan(22);
         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
         table.addCell(cell);
         
         
         table1 = new PdfPTable(26);
         int[] xwidths4 = {5,5,5,5,5, 5,5,5,5,5, 5,5,5,5,5, 5,5,5,5,5, 5,5,5,5,5,5}; // percentage
         table1.setWidths(xwidths4);
         table1.setWidthPercentage(100);

         cell = new PdfPCell(new Phrase("De 4 años", CABEZA_COLUMNA_FONT));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setColspan(26);
         table1.addCell(cell);
         
         addtitulofila5(table1,"Pentavelente","1a","2a","3a","4a","5a");
         addtitulofila5(table1,"OPV Antipolio","1a","2a","3a","4a","5a");  
         addtitulofila2(table1,"SRP 1ra","M","F");
         addtitulofila2(table1,"SRP 2da","M","F");
         addtitulofila2(table1,"Anti\nAma","M","F");
         
         cell = new PdfPCell(table1);
         cell.setColspan(26);
         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
         table.addCell(cell);
         
         
         table1 = new PdfPTable(14);
         int[] xwidths5 = {5,5,5,5,5, 5,5,5,5,5,5,5,5,5}; // percentage
         table1.setWidths(xwidths5);
         table1.setWidthPercentage(100);

         cell = new PdfPCell(new Phrase("D. T.", CABEZA_COLUMNA_FONT));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setColspan(14);
         table1.addCell(cell);
         
         addtitulofila21(table1,"7 a 9 anios","1a","2a");
         addtitulofila5(table1,"10 a 49 anios","1a","2a","3a","4a","5a");  

         cell = new PdfPCell(table1);
         cell.setColspan(14);
         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
         table.addCell(cell);
              
         cell = new PdfPCell(new Phrase("Atendido \npor", CABEZA_COLUMNA_FONT));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setRotation(90);
         table.addCell(cell);
         
         // coloca el detalle de loS datos
         for (int i = pag*filas+0; i<pag*filas+filas && i < lFopos.size(); i++) {

           Cuadernos datopac = (Cuadernos) lFopos.get(i);    
           
           cell = new PdfPCell(new Phrase(Integer.toString(i+1) , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_LEFT);
           table.addCell(cell);
           ///
           int puno=0,pdos=0,ptres=0,pcuatro=0,pcinco=0,pseis=0,psiete=0,pocho=0,pnueve=0,pdiez=0,ponce=0,pdoce=0,ptrece=0,pcatorce=0;
           if("1".equals(datopac.getSrp()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=2 && datopac.getEdad()<4) puno++;
           if("1".equals(datopac.getSrp()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=2 && datopac.getEdad()<4) pdos++;
           if("2".equals(datopac.getSrp()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=2 && datopac.getEdad()<4) ptres++;
           if("2".equals(datopac.getSrp()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=2 && datopac.getEdad()<4) pcuatro++;
           if("1".equals(datopac.getFama()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=2 && datopac.getEdad()<4) pcinco++;
           if("1".equals(datopac.getFama()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=2 && datopac.getEdad()<4) pseis++;
           sumbs[0]+=puno;
           sumbs[1]+=pdos;
           sumbs[2]+=ptres;
           sumbs[3]+=pcuatro;
           sumbs[4]+=pcinco;
           sumbs[5]+=pseis;
           
           cell = new PdfPCell(new Phrase((puno==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);

           cell = new PdfPCell(new Phrase((pdos==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((ptres==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcuatro==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcinco==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pseis==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
      //calculos de penta de 2 a 3 años de edad      
           puno=0;pdos=0;ptres=0;pcuatro=0;pcinco=0;pseis=0;psiete=0;pocho=0;
           if("P".equals(datopac.getPenta()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=2 && datopac.getEdad()<4) puno++;
           if("P".equals(datopac.getPenta()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=2 && datopac.getEdad()<4) pdos++;
           if("S".equals(datopac.getPenta()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=2 && datopac.getEdad()<4) ptres++;
           if("S".equals(datopac.getPenta()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=2 && datopac.getEdad()<4) pcuatro++;
           if("T".equals(datopac.getPenta()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=2 && datopac.getEdad()<4) pcinco++;
           if("T".equals(datopac.getPenta()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=2 && datopac.getEdad()<4) pseis++;
           if("C".equals(datopac.getPenta()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=2 && datopac.getEdad()<4) psiete++;
           if("C".equals(datopac.getPenta()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=2 && datopac.getEdad()<4) pocho++;
           sumbs[6]+=puno;
           sumbs[7]+=pdos;
           sumbs[8]+=ptres;
           sumbs[9]+=pcuatro;
           sumbs[10]+=pcinco;
           sumbs[11]+=pseis;
           sumbs[12]+=psiete;
           sumbs[13]+=pocho;
           
           cell = new PdfPCell(new Phrase((puno==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);

           cell = new PdfPCell(new Phrase((pdos==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((ptres==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);

           cell = new PdfPCell(new Phrase((pcuatro==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcinco==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);

           cell = new PdfPCell(new Phrase((pseis==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((psiete==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);

           cell = new PdfPCell(new Phrase((pocho==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           puno=0;pdos=0;ptres=0;pcuatro=0;pcinco=0;pseis=0;psiete=0;pocho=0;
           if("P".equals(datopac.getPolio()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=2 && datopac.getEdad()<4) puno++;
           if("P".equals(datopac.getPolio()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=2 && datopac.getEdad()<4) pdos++;
           if("S".equals(datopac.getPolio()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=2 && datopac.getEdad()<4) ptres++;
           if("S".equals(datopac.getPolio()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=2 && datopac.getEdad()<4) pcuatro++;
           if("T".equals(datopac.getPolio()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=2 && datopac.getEdad()<4) pcinco++;
           if("T".equals(datopac.getPolio()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=2 && datopac.getEdad()<4) pseis++;
           if("C".equals(datopac.getPolio()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=2 && datopac.getEdad()<4) psiete++;
           if("C".equals(datopac.getPolio()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=2 && datopac.getEdad()<4) pocho++;
           sumbs[14]+=puno;
           sumbs[15]+=pdos;
           sumbs[16]+=ptres;
           sumbs[17]+=pcuatro;
           sumbs[18]+=pcinco;
           sumbs[19]+=pseis;
           sumbs[20]+=psiete;
           sumbs[21]+=pocho;
           
           cell = new PdfPCell(new Phrase((puno==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);

           cell = new PdfPCell(new Phrase((pdos==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((ptres==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);

           cell = new PdfPCell(new Phrase((pcuatro==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcinco==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);

           cell = new PdfPCell(new Phrase((pseis==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((psiete==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);

           cell = new PdfPCell(new Phrase((pocho==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
     //calculos de penta de 4 años de edad      
           puno=0;pdos=0;ptres=0;pcuatro=0;pcinco=0;pseis=0;psiete=0;pocho=0;pnueve=0;pdiez=0;
           if("P".equals(datopac.getPenta()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=4 && datopac.getEdad()<5) puno++;
           if("P".equals(datopac.getPenta()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=4 && datopac.getEdad()<5) pdos++;
           if("S".equals(datopac.getPenta()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=4 && datopac.getEdad()<5) ptres++;
           if("S".equals(datopac.getPenta()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=4 && datopac.getEdad()<5) pcuatro++;
           if("T".equals(datopac.getPenta()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=4 && datopac.getEdad()<5) pcinco++;
           if("T".equals(datopac.getPenta()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=4 && datopac.getEdad()<5) pseis++;
           if("C".equals(datopac.getPenta()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=4 && datopac.getEdad()<5) psiete++;
           if("C".equals(datopac.getPenta()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=4 && datopac.getEdad()<5) pocho++;
           if("Q".equals(datopac.getPenta()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=4 && datopac.getEdad()<5) pnueve++;
           if("Q".equals(datopac.getPenta()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=4 && datopac.getEdad()<5) pdiez++;
           sumbs[22]+=puno;
           sumbs[23]+=pdos;
           sumbs[24]+=ptres;
           sumbs[25]+=pcuatro;
           sumbs[26]+=pcinco;
           sumbs[27]+=pseis;
           sumbs[28]+=psiete;
           sumbs[29]+=pocho;
           sumbs[30]+=pnueve;
           sumbs[31]+=pdiez;
           
           cell = new PdfPCell(new Phrase((puno==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);

           cell = new PdfPCell(new Phrase((pdos==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((ptres==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);

           cell = new PdfPCell(new Phrase((pcuatro==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcinco==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);

           cell = new PdfPCell(new Phrase((pseis==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((psiete==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);

           cell = new PdfPCell(new Phrase((pocho==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pnueve==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pdiez==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           puno=0;pdos=0;ptres=0;pcuatro=0;pcinco=0;pseis=0;psiete=0;pocho=0;pnueve=0;pdiez=0;
           if("P".equals(datopac.getPolio()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=4 && datopac.getEdad()<5) puno++;
           if("P".equals(datopac.getPolio()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=4 && datopac.getEdad()<5) pdos++;
           if("S".equals(datopac.getPolio()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=4 && datopac.getEdad()<5) ptres++;
           if("S".equals(datopac.getPolio()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=4 && datopac.getEdad()<5) pcuatro++;
           if("T".equals(datopac.getPolio()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=4 && datopac.getEdad()<5) pcinco++;
           if("T".equals(datopac.getPolio()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=4 && datopac.getEdad()<5) pseis++;
           if("C".equals(datopac.getPolio()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=4 && datopac.getEdad()<5) psiete++;
           if("C".equals(datopac.getPolio()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=4 && datopac.getEdad()<5) pocho++;
           if("Q".equals(datopac.getPolio()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=4 && datopac.getEdad()<5) pnueve++;
           if("Q".equals(datopac.getPolio()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=4 && datopac.getEdad()<5) pdiez++;
           sumbs[32]+=puno;
           sumbs[33]+=pdos;
           sumbs[34]+=ptres;
           sumbs[35]+=pcuatro;
           sumbs[36]+=pcinco;
           sumbs[37]+=pseis;
           sumbs[38]+=psiete;
           sumbs[39]+=pocho;
           sumbs[40]+=pnueve;
           sumbs[41]+=pdiez;
           
           cell = new PdfPCell(new Phrase((puno==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);

           cell = new PdfPCell(new Phrase((pdos==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((ptres==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);

           cell = new PdfPCell(new Phrase((pcuatro==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcinco==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);

           cell = new PdfPCell(new Phrase((pseis==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((psiete==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);

           cell = new PdfPCell(new Phrase((pocho==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pnueve==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pdiez==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           puno=0;pdos=0;ptres=0;pcuatro=0;pcinco=0;pseis=0;
           if("1".equals(datopac.getSrp()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=4 && datopac.getEdad()<5) puno++;
           if("1".equals(datopac.getSrp()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=4 && datopac.getEdad()<5) pdos++;
           if("2".equals(datopac.getSrp()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=4 && datopac.getEdad()<5) ptres++;
           if("2".equals(datopac.getSrp()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=4 && datopac.getEdad()<5) pcuatro++;
           if("1".equals(datopac.getFama()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>=4 && datopac.getEdad()<5) pcinco++;
           if("1".equals(datopac.getFama()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>=4 && datopac.getEdad()<5) pseis++;
           sumbs[42]+=puno;
           sumbs[43]+=pdos;
           sumbs[44]+=ptres;
           sumbs[45]+=pcuatro;
           sumbs[46]+=pcinco;
           sumbs[47]+=pseis;
           
           cell = new PdfPCell(new Phrase((puno==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);

           cell = new PdfPCell(new Phrase((pdos==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((ptres==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);

           cell = new PdfPCell(new Phrase((pcuatro==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcinco==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pseis==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
  
           puno=0;pdos=0;ptres=0;pcuatro=0;pcinco=0;pseis=0;psiete=0;pocho=0;pnueve=0;pdiez=0;
           if("P".equals(datopac.getMujerdt()) && (datopac.getEdad()>6 &&  datopac.getEdad()<10) && datopac.getId_tipo_sexo()==2 ) puno++;
           if("P".equals(datopac.getMujerdt()) && (datopac.getEdad()>6 &&  datopac.getEdad()<10) && datopac.getId_tipo_sexo()==1 ) pdos++;
           if("S".equals(datopac.getMujerdt()) && (datopac.getEdad()>6 &&  datopac.getEdad()<10) && datopac.getId_tipo_sexo()==2 ) ptres++;
           if("S".equals(datopac.getMujerdt()) && (datopac.getEdad()>6 &&  datopac.getEdad()<10) && datopac.getId_tipo_sexo()==1 ) pcuatro++;
           if("P".equals(datopac.getMujerdt()) && (datopac.getEdad()>9 &&  datopac.getEdad()<50) && datopac.getId_tipo_sexo()==2 ) pcinco++;
           if("P".equals(datopac.getMujerdt()) && (datopac.getEdad()>9 &&  datopac.getEdad()<50) && datopac.getId_tipo_sexo()==1 ) pseis++;
           if("S".equals(datopac.getMujerdt()) && (datopac.getEdad()>9 &&  datopac.getEdad()<50) && datopac.getId_tipo_sexo()==2 ) psiete++;
           if("S".equals(datopac.getMujerdt()) && (datopac.getEdad()>9 &&  datopac.getEdad()<50) && datopac.getId_tipo_sexo()==1 ) pocho++;
           if("T".equals(datopac.getMujerdt()) && (datopac.getEdad()>9 &&  datopac.getEdad()<50) && datopac.getId_tipo_sexo()==2 ) pnueve++;
           if("T".equals(datopac.getMujerdt()) && (datopac.getEdad()>9 &&  datopac.getEdad()<50) && datopac.getId_tipo_sexo()==1 ) pdiez++;
           if("C".equals(datopac.getMujerdt()) && (datopac.getEdad()>9 &&  datopac.getEdad()<50) && datopac.getId_tipo_sexo()==2 ) ponce++;
           if("C".equals(datopac.getMujerdt()) && (datopac.getEdad()>9 &&  datopac.getEdad()<50) && datopac.getId_tipo_sexo()==1 ) pdoce++;
           if("Q".equals(datopac.getMujerdt()) && (datopac.getEdad()>9 &&  datopac.getEdad()<50) && datopac.getId_tipo_sexo()==2 ) ptrece++;
           if("Q".equals(datopac.getMujerdt()) && (datopac.getEdad()>9 &&  datopac.getEdad()<50) && datopac.getId_tipo_sexo()==1 ) pcatorce++;
           sumbs[48]+=puno;
           sumbs[49]+=pdos;
           sumbs[50]+=ptres;
           sumbs[51]+=pcuatro;
           sumbs[52]+=pcinco;
           sumbs[53]+=pseis;
           sumbs[54]+=psiete;
           sumbs[55]+=pocho;
           sumbs[56]+=pnueve;
           sumbs[57]+=pdiez;
           sumbs[58]+=ponce;
           sumbs[59]+=pdoce;
           sumbs[60]+=ptrece;
           sumbs[61]+=pcatorce;
           
           cell = new PdfPCell(new Phrase((puno==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);

           cell = new PdfPCell(new Phrase((pdos==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((ptres==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);

           cell = new PdfPCell(new Phrase((pcuatro==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcinco==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pseis==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((psiete==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pocho==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pnueve==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pdiez==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((ponce==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pdoce==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((ptrece==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcatorce==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
             
           cell = new PdfPCell(new Phrase(Integer.toString(datopac.getId_persona()) , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           table.addCell(cell);    
         }
         
         for (int i = lFopos.size(); i<pag*filas+filas ; i++) {
                     cell = new PdfPCell(new Phrase(Integer.toString(i+1) , DATO_FONT));
                     cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                     table.addCell(cell);
                     for (int j =  1;j<NumColumns; j++) {
                         cell = new PdfPCell(new Phrase("" , DATO_FONT));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         table.addCell(cell);
                     }
         }
     
         cell = new PdfPCell(new Phrase("Total" , DATO_FONT));
         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
         table.addCell(cell);
         
         for (int j =  0;j<62; j++) {
             cell = new PdfPCell(new Phrase(Integer.toString(sumbs[j]) , DATO_FONT));
             cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
             table.addCell(cell);
         }
         
         for (int j = 1;j<62; j++) {
                 cell = new PdfPCell(new Phrase("" , DATO_FONT));
                 cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                 table.addCell(cell);
                     }
         
        
         
         
         document.add(table); 
         
         //// NUEVO HOJA TERCERA hoja      
         document.newPage(); 
           
         NumColumns =42;
         table = new PdfPTable(NumColumns);
         int[] fwnidthsa3 = {15, 8,8,8,8,8, 8,8,8,8,8, 8,8,8,8,8, 8,8,8,8,8, 8,8,8,8,8, 8,8,8,8,8,8,8,8,8,8,8,8,8,8, 60, 16}; 
         table.setWidths(fwnidthsa3);
         table.setWidthPercentage(100);

         cell = new PdfPCell(new Phrase("REGISTRO DIARIO DE VACUNACION" , TITULO_FONT));
         cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
         cell.setColspan(NumColumns);
         table.addCell(cell);

    // coloca la cabecera de titulos
         cell = new PdfPCell(new Phrase("No", CABEZA_COLUMNA_FONT));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         table.addCell(cell);
         
         addtitulofila5(table,"Influenza Estacional","Pers.\nSalud","Enf.\nCronico","Mujer\nEmbarazada","> 60","Otros");
         addtitulofila3(table,"HEPATITIS Pers. Salud","1ra","2da","3ra");  
         addtitulofila3(table,"HEPATITIS Poblac. Vulnerable","1ra","2da","3ra");  
         addtitulofila2(table,"SR Unic","M","F");
         addtitulofila2(table,"VPH","1a F","2a F");
         addtitulofila2(table,"DT\n>50 años","M","F");
         addtitulofila2(table,"FA\n5 a 49 \naños","M","F");
         
         PdfPTable   table13 = new PdfPTable(2);
         int[] fmwidths3 = {50,50}; // percentage
         table13.setWidths(fmwidths3);
         table13.setWidthPercentage(100);

         table13 = new PdfPTable(9);
         int[] xwidths33 = {5,5,5,5,5, 5,5,5,5}; // percentage
         table13.setWidths(xwidths33);
         table13.setWidthPercentage(100);

         cell = new PdfPCell(new Phrase("RABIA", CABEZA_COLUMNA_FONT));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setColspan(9);
         table13.addCell(cell);
         
         addtitulofila4(table13,"No de personas","vacuna antirrab.","esquem clasico complet","esque. redusido complt.","abandon esquema");
         
         cell = new PdfPCell(new Phrase("perros y gatos", CABEZA_COLUMNA_FONT));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         table13.addCell(cell);
         
         cell = new PdfPCell(table13);
         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
         cell.setColspan(9);
         table.addCell(cell);
            
         cell = new PdfPCell(new Phrase("Otro vacunas", CABEZA_COLUMNA_FONT));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         table.addCell(cell);
                  
         cell = new PdfPCell(new Phrase("Atendido \npor", CABEZA_COLUMNA_FONT));
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
         cell.setRotation(90);
         table.addCell(cell);
         
         // coloca el detalle de loS datos
         for (int i = pag*filas+0; i<pag*filas+filas && i < lFopos.size(); i++) {

           Cuadernos datopac = (Cuadernos) lFopos.get(i);    
           
           cell = new PdfPCell(new Phrase(Integer.toString(i+1) , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_LEFT);
           table.addCell(cell);
           ///
           ///INFLUENZA
           int puno=0,pdos=0,ptres=0,pcuatro=0,pcinco=0,pseis=0,psiete=0,pocho=0,pnueve=0,pdiez=0;
           if(datopac.getCancer()==1 && datopac.getId_tipo_sexo()==2 ) puno++;
           if(datopac.getCancer()==1 && datopac.getId_tipo_sexo()==1 ) pdos++;
           if(datopac.getCancer()==2 && datopac.getId_tipo_sexo()==2 ) ptres++;
           if(datopac.getCancer()==2 && datopac.getId_tipo_sexo()==1 ) pcuatro++;
           if(datopac.getCancer()==3 && datopac.getId_tipo_sexo()==2 ) pcinco++;
           if(datopac.getCancer()==3 && datopac.getId_tipo_sexo()==1 ) pseis++;
           if(datopac.getCancer()==4 && datopac.getId_tipo_sexo()==2 ) psiete++;
           if(datopac.getCancer()==4 && datopac.getId_tipo_sexo()==1 ) pocho++;
           if((datopac.getCancer()==5 || datopac.getCancer()==9) && datopac.getId_tipo_sexo()==2 ) pnueve++;
           if((datopac.getCancer()==5 || datopac.getCancer()==9) && datopac.getId_tipo_sexo()==1 ) pdiez++;
           sumcs[0]+=puno;
           sumcs[1]+=pdos;
           sumcs[2]+=ptres;
           sumcs[3]+=pcuatro;
           sumcs[4]+=pcinco;
           sumcs[5]+=pseis;
           sumcs[6]+=psiete;
           sumcs[7]+=pocho;
           sumcs[8]+=pnueve;
           sumcs[9]+=pdiez;
           
           cell = new PdfPCell(new Phrase((puno==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);

           cell = new PdfPCell(new Phrase((pdos==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((ptres==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);

           cell = new PdfPCell(new Phrase((pcuatro==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcinco==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);

           cell = new PdfPCell(new Phrase((pseis==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((psiete==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);

           cell = new PdfPCell(new Phrase((pocho==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pnueve==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pdiez==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);           table.addCell(cell);
           
     //HEpatitis personal de salud
           puno=0;pdos=0;ptres=0;pcuatro=0;pcinco=0;pseis=0;
           if(datopac.getAuto()==1 && datopac.getId_tipo_sexo()==2 ) puno++;
           if(datopac.getAuto()==1 && datopac.getId_tipo_sexo()==1 ) pdos++;
           if(datopac.getAuto()==2 && datopac.getId_tipo_sexo()==2 ) ptres++;
           if(datopac.getAuto()==2 && datopac.getId_tipo_sexo()==1 ) pcuatro++;
           if(datopac.getAuto()==3 && datopac.getId_tipo_sexo()==2 ) pcinco++;
           if(datopac.getAuto()==3 && datopac.getId_tipo_sexo()==1 ) pseis++;
           sumcs[10]+=puno;
           sumcs[11]+=pdos;
           sumcs[12]+=ptres;
           sumcs[13]+=pcuatro;
           sumcs[14]+=pcinco;
           sumcs[15]+=pseis;
 
           
           cell = new PdfPCell(new Phrase((puno==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);

           cell = new PdfPCell(new Phrase((pdos==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((ptres==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcuatro==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);

           cell = new PdfPCell(new Phrase((pcinco==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pseis==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           //HEpatitis poblacion Vulnerable
           puno=0;pdos=0;ptres=0;pcuatro=0;pcinco=0;pseis=0;
           if(datopac.getAuto()==4 && datopac.getId_tipo_sexo()==2 ) puno++;
           if(datopac.getAuto()==4 && datopac.getId_tipo_sexo()==1 ) pdos++;
           if(datopac.getAuto()==5 && datopac.getId_tipo_sexo()==2 ) ptres++;
           if(datopac.getAuto()==5 && datopac.getId_tipo_sexo()==1 ) pcuatro++;
           if(datopac.getAuto()==6 && datopac.getId_tipo_sexo()==2 ) pcinco++;
           if(datopac.getAuto()==6 && datopac.getId_tipo_sexo()==1 ) pseis++;
           sumcs[16]+=puno;
           sumcs[17]+=pdos;
           sumcs[18]+=ptres;
           sumcs[19]+=pcuatro;
           sumcs[20]+=pcinco;
           sumcs[21]+=pseis;
 
           cell = new PdfPCell(new Phrase((puno==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);

           cell = new PdfPCell(new Phrase((pdos==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((ptres==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcuatro==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);

           cell = new PdfPCell(new Phrase((pcinco==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pseis==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
          //////SR y VPH
           puno=0;pdos=0;ptres=0;pcuatro=0;
           if(datopac.getSr()==1 && datopac.getId_tipo_sexo()==2 ) puno++;
           if(datopac.getSr()==1 && datopac.getId_tipo_sexo()==1 ) pdos++;
           if(datopac.getVph()==1 && datopac.getId_tipo_sexo()==1 ) ptres++;
           if(datopac.getVph()==2 && datopac.getId_tipo_sexo()==1 ) pcuatro++;
           sumcs[22]+=puno;
           sumcs[23]+=pdos;
           sumcs[24]+=ptres;
           sumcs[25]+=pcuatro;
 
           cell = new PdfPCell(new Phrase((puno==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);

           cell = new PdfPCell(new Phrase((pdos==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((ptres==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcuatro==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           ///DT mayores de 50 anios
           puno=0;pdos=0;
           if((!"N".equals(datopac.getMujerdt()) && !"".equals(datopac.getMujerdt()) && datopac.getMujerdt()!=null ) && (datopac.getEdad()>50 &&  datopac.getEdad()<99) && datopac.getId_tipo_sexo()==2 ) puno++;
           if((!"N".equals(datopac.getMujerdt()) && !"".equals(datopac.getMujerdt()) && datopac.getMujerdt()!=null ) && (datopac.getEdad()>50 &&  datopac.getEdad()<99) && datopac.getId_tipo_sexo()==1 ) pdos++;
           sumcs[26]+=puno;
           sumcs[27]+=pdos;

           cell = new PdfPCell(new Phrase((puno==0?"":"1"), DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);

           cell = new PdfPCell(new Phrase((pdos==0?"":"1"), DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           //FA e 5 a 49 anios
           puno=0;pdos=0;
           if("1".equals(datopac.getFama()) && datopac.getId_tipo_sexo()==2 && datopac.getEdad()>5 && datopac.getEdad()<99) puno++;
           if("1".equals(datopac.getFama()) && datopac.getId_tipo_sexo()==1 && datopac.getEdad()>5 && datopac.getEdad()<99) pdos++;
           sumcs[28]+=puno;
           sumcs[29]+=pdos;

           cell = new PdfPCell(new Phrase((puno==0?"":"1"), DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);

           cell = new PdfPCell(new Phrase((pdos==0?"":"1"), DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           //////Rabia
           puno=0;pdos=0;ptres=0;pcuatro=0;pcinco=0;pseis=0;psiete=0;pocho=0;
           if(datopac.getRhumana()==1 && datopac.getId_tipo_sexo()==2 ) puno++;
           if(datopac.getRhumana()==1 && datopac.getId_tipo_sexo()==1 ) pdos++;
           if(datopac.getRhumana()==2 && datopac.getId_tipo_sexo()==2 ) ptres++;
           if(datopac.getRhumana()==2 && datopac.getId_tipo_sexo()==1 ) pcuatro++;
           if(datopac.getRhumana()==3 && datopac.getId_tipo_sexo()==2 ) pcinco++;
           if(datopac.getRhumana()==3 && datopac.getId_tipo_sexo()==1 ) pseis++;
           if(datopac.getRhumana()==4 && datopac.getId_tipo_sexo()==2 ) psiete++;
           if(datopac.getRhumana()==4 && datopac.getId_tipo_sexo()==1 ) pocho++;
           sumcs[30]+=puno;
           sumcs[31]+=pdos;
           sumcs[32]+=ptres;
           sumcs[33]+=pcuatro;
           sumcs[34]+=pcinco;
           sumcs[35]+=pseis;
           sumcs[36]+=psiete;
           sumcs[37]+=pocho;
 
           cell = new PdfPCell(new Phrase((puno==0?"":"1"), DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);

           cell = new PdfPCell(new Phrase((pdos==0?"":"1"), DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((ptres==0?"":"1"), DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcuatro==0?"":"1"), DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pcinco==0?"":"1") , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);

           cell = new PdfPCell(new Phrase((pseis==0?"":"1"), DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((psiete==0?"":"1"), DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           cell = new PdfPCell(new Phrase((pocho==0?"":"1"), DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);          table.addCell(cell);
           
           ///Rabia Animal Canina
           if(datopac.getRcanina()>0 ){
              cell = new PdfPCell(new Phrase(Integer.toString(datopac.getRcanina()) , DATO_FONT));
           }
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           table.addCell(cell);  
           
           ////otras vacunas
           if(datopac.getDvitaa().length()>2){
              cell = new PdfPCell(new Phrase(datopac.getDvitaa(), DATO_FONT)); 
           }else{
              cell = new PdfPCell(new Phrase(" ", DATO_FONT));  
           }
           cell.setHorizontalAlignment(Element.ALIGN_LEFT);
           table.addCell(cell);  	    
		   
           cell = new PdfPCell(new Phrase(Integer.toString(datopac.getId_persona()) , DATO_FONT));
           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
           table.addCell(cell);    
         }
         
         for (int i = lFopos.size(); i<pag*filas+filas ; i++) {
                     cell = new PdfPCell(new Phrase(Integer.toString(i+1) , DATO_FONT));
                     cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                     table.addCell(cell);
                     for (int j = 1; j<NumColumns; j++) {
                         cell = new PdfPCell(new Phrase("" , DATO_FONT));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         table.addCell(cell);
                     }
         }
     
         cell = new PdfPCell(new Phrase("Total" , DATO_FONT));
         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
         table.addCell(cell);
         
         for (int j = 0;j<39; j++){
             cell = new PdfPCell(new Phrase(Integer.toString(sumcs[j]) , DATO_FONT));
             cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
             table.addCell(cell);
         }
         
         for (int j = 1;j<31; j++) {
                 cell = new PdfPCell(new Phrase("" , DATO_FONT));
                 cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                 table.addCell(cell);
         }
         
         document.add(table); 
         document.newPage();
		 
		 
     }   
  
  }

   public static void addtitulofila3(PdfPTable table, String cadena, String a, String b, String c){
     PdfPCell cell; 
     int NumColumns =6;
     PdfPTable  table11 = new PdfPTable(NumColumns);
     table11.setWidthPercentage(100);

     cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
     cell.setColspan(6);
     cell.setHorizontalAlignment(Element.ALIGN_CENTER);
     table11.addCell(cell);
     
       addtitulofila2(table11,a,"M","F");

       addtitulofila2(table11,b,"M","F");

       addtitulofila2(table11,c,"M","F");

     cell = new PdfPCell(table11);
     cell.setColspan(6);
     cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     table.addCell(cell);
   }
   
   public static void addtitulofila21(PdfPTable table, String cadena, String a, String b){
     PdfPCell cell; 
     int NumColumns =4;
     PdfPTable  table11 = new PdfPTable(NumColumns);
     table11.setWidthPercentage(100);

     cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
     cell.setColspan(4);
     cell.setHorizontalAlignment(Element.ALIGN_CENTER);
     table11.addCell(cell);
     
       addtitulofila2(table11,a,"M","F");

       addtitulofila2(table11,b,"M","F");

     cell = new PdfPCell(table11);
     cell.setColspan(4);
     cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     table.addCell(cell);
   }
   
   public static void addtitulofila4(PdfPTable table, String cadena, String a, String b, String c, String d){
     PdfPCell cell; 
     int NumColumns =8;
     PdfPTable  table11 = new PdfPTable(NumColumns);
     table11.setWidthPercentage(100);

     cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
     cell.setColspan(8);
     cell.setHorizontalAlignment(Element.ALIGN_CENTER);
     table11.addCell(cell);
     
       addtitulofila2(table11,a,"M","F");

       addtitulofila2(table11,b,"M","F");

       addtitulofila2(table11,c,"M","F");
       
       addtitulofila2(table11,d,"M","F");

     cell = new PdfPCell(table11);
     cell.setColspan(8);
     cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     table.addCell(cell);
   }
   
   public static void addtitulofila5(PdfPTable table, String cadena, String a, String b, String c, String d, String e){
     PdfPCell cell; 
     int NumColumns =10;
     PdfPTable  table11 = new PdfPTable(NumColumns);
     table11.setWidthPercentage(100);

     cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
     cell.setColspan(10);
     cell.setHorizontalAlignment(Element.ALIGN_CENTER);
     table11.addCell(cell);
     
       addtitulofila2(table11,a,"M","F");

       addtitulofila2(table11,b,"M","F");

       addtitulofila2(table11,c,"M","F");
       
       addtitulofila2(table11,d,"M","F");

       addtitulofila2(table11,e,"M","F");
       
     cell = new PdfPCell(table11);
     cell.setColspan(10);
     cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     table.addCell(cell);
   }
   
   public static void addtitulofila2(PdfPTable table, String cadena, String a, String b){
     PdfPCell cell;
     int NumColumns =2;
     PdfPTable  table12 = new PdfPTable(NumColumns);
     table12.setWidthPercentage(100);

     cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
     cell.setColspan(2);
     cell.setHorizontalAlignment(Element.ALIGN_CENTER);
     table12.addCell(cell);

       cell = new PdfPCell(new Phrase(a, DATO_FONT));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       table12.addCell(cell);

       cell = new PdfPCell(new Phrase(b, DATO_FONT));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       table12.addCell(cell);

     cell = new PdfPCell(table12);
     cell.setColspan(2);
     cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     table.addCell(cell);
   }
   
    public static void addtitulofila51(PdfPTable table, String cadena, String a, String b, String c, String d, String e){
     PdfPCell cell; 
     int NumColumns =5;
     PdfPTable  table11 = new PdfPTable(NumColumns);
     table11.setWidthPercentage(100);

     cell = new PdfPCell(new Phrase(cadena, CABEZA_COLUMNA_FONT));
     cell.setColspan(5);
     cell.setHorizontalAlignment(Element.ALIGN_CENTER);
     table11.addCell(cell);
     
       cell = new PdfPCell(new Phrase(a, DATO_FONT));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       table11.addCell(cell);

       cell = new PdfPCell(new Phrase(b, DATO_FONT));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       table11.addCell(cell);

       cell = new PdfPCell(new Phrase(c, DATO_FONT));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       table11.addCell(cell);
       
       cell = new PdfPCell(new Phrase(d, DATO_FONT));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       table11.addCell(cell);
       
       cell = new PdfPCell(new Phrase(e, DATO_FONT));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       table11.addCell(cell);

     cell = new PdfPCell(table11);
     cell.setColspan(5);
     cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     table.addCell(cell);
   }
    
   public static String format(Date dia,String formato){
     if(dia == null) return "";
     SimpleDateFormat formatDate = new SimpleDateFormat(formato);
     return formatDate.format(dia);
  }
}