package br.com.m3Tech.utils;

import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.OrientationRequested;

public class ImpressoraUtils {
	
	private ImpressoraUtils() {}

	
	public static void imprimir(String impressora, String texto) {
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        aset.add(OrientationRequested.PORTRAIT);
        aset.add(new JobName("Impressao Etiqueta", null));

        //PROCURA A IMPRESSORA
        PrintService printer = null;

        PrintService[] impressoras = PrinterJob.lookupPrintServices();


        for (int i = 0; i < impressoras.length; i++) {
            PrintService p = impressoras[i];

            if (p.getName().equalsIgnoreCase(impressora)) {
                printer = p;
                break;

            }
        }

        try {
	        //ENVIA COMO ARRAY DE BYTES PARA IMPRESSORA DESEJADA
	        DocPrintJob docPrint = printer.createPrintJob();
	        InputStream stream = new ByteArrayInputStream(texto.getBytes());
	        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
	        Doc doc = new SimpleDoc(stream, flavor, null);
        
        
			docPrint.print(doc, aset);
		} catch (PrintException e) {
			e.printStackTrace();
		}
	}

}
