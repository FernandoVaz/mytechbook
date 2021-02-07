package core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class WordArea extends JScrollPane
{
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private JTextArea text;
	
	public WordArea(String name, JTextArea text)
	{
		super(text);
		this.name = name;
		this.text = text;
		
	}
	
	public WordArea()
	{
		this("untitled.txt", new JTextArea());
	}
	
	public void save()
	{
		saveAs(name);
	}
	
	public void saveAs(String path)
	{
		// C:\Users\Joe\Path\test3.txt
		// C:/Users/Test
		// 
		
		try 
		{
			File file = new File(path);
			this.name = file.getName();
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(text.getText());
			bw.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	public JTextArea getText()
	{
		return text;
	}
	
	
	@Override
	public String getName()
	{ 
		return name;
	}
		
	
}
