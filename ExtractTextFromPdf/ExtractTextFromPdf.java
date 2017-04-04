/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Kavya Gupta
 */
import java.io.*;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


public class ExtractTextFromPdf {

	public static void main(String[] args) {
		
		PDFParser parser = null;
	    PDDocument pdDoc = null;
	    COSDocument cosDoc = null;
	    PDFTextStripper pdfStripper;

	    String parsedText;
	    String fileName = "C:/Users/Kavya Gupta/Desktop/Texas_Title.pdf";
	    File file = new File(fileName);
	    try {
	    	byte data[] = new byte[1024];
	    	((RandomAccessRead) file).read(data,0,1024);
	    	
	    	pdDoc = PDDocument.load(new File(fileName));
	    	pdfStripper = new PDFTextStripper();
	        parsedText = pdfStripper.getText(pdDoc);
	        System.out.println(parsedText.replaceAll("[^A-Za-z0-9. ]+", ""));
	    } catch (Exception e) {
	        e.printStackTrace();
	        try {
	            if (cosDoc != null)
	                cosDoc.close();
	            if (pdDoc != null)
	                pdDoc.close();
	        } catch (Exception e1) {
	            e.printStackTrace();
	        }
	    }
	}
}

