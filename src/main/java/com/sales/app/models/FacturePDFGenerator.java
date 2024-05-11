package com.sales.app.models;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.sales.app.entities.Vente;

import java.io.FileOutputStream;

public class FacturePDFGenerator {
//    public static void generateFacturePDF(Vente vente) throws DocumentException {
//        Document document = new Document();
//        PdfWriter.getInstance(document, new FileOutputStream("facture_" + vente.getVenteId() + ".pdf"));
//        document.open();
//
//        // Ajoutez les champs de la facture à votre modèle PDF
//        document.add(new Paragraph("Facture pour la vente numéro " + vente.getVenteId()));
//        document.add(new Paragraph("Date de vente: " + vente.getDateVente()));
//        document.add(new Paragraph("Client: " + vente.getClient().getNom()));
//        document.add(new Paragraph("Total: " + vente.getTotal()));
//
//        document.close();
//    }
}
