package tess4J;

import java.io.File;

import net.sourceforge.tess4j.Tesseract; 
import net.sourceforge.tess4j.TesseractException; 


public class TessAction 
{
	public TessAction()
	{
		super();
	}
	
	public void readImage(String path)
	{
		Tesseract tesseract = new Tesseract(); 
	    try 
	    { 
	        tesseract.setDatapath("D:\\Engenharia\\Projetos\\MyTechBook\\Tess4J\\tessdata"); 
	
	        String text 
	            = tesseract.doOCR(new File(path)); 
	
	        System.out.print(text); 
	    } 
	    catch (TesseractException e) { 
	        e.printStackTrace(); 
	    } 
	}

}
