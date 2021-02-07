package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import tess4J.TessAction;

public class Application extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabPane;
	private String name;
	
	public static void main(String[] args) 
	{
		new Application().setVisible(true);
	}
	
	private Application()
	{	
		super("My Tech Book");
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initialize();
	}
	
	private void initialize()
	{
		tabPane = new JTabbedPane();
		WordArea doc = new WordArea();
		tabPane.addTab(doc.getName(), doc);
		
		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem newDoc = new JMenuItem("New");
		JMenuItem open = new JMenuItem("Open");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem saveas = new JMenuItem("Save as");
		JMenuItem exit = new JMenuItem("Exit");
		
		JMenu image = new JMenu("Image");
		JMenuItem openImage = new JMenuItem("Open Image");
		JMenuItem cropScreen = new JMenuItem("Snipping Tool");
		
		JMenuItem[] items = { newDoc, open, save, saveas, exit, openImage };
		for(JMenuItem item : items)
		{
			item.addActionListener(this);
		}
		
		file.add(newDoc);
		file.add(open);
		file.add(save);
		file.add(saveas);
		file.addSeparator();
		file.add(exit);
		image.add(openImage);
		image.add(cropScreen);
		
		bar.add(file);
		bar.add(image);
		
		setJMenuBar(bar);
		add(tabPane);
		//add(scroll); //Adding text to the frame which Application extends from
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand().equals("Open"))
		{
			open();
		}
		else if(e.getActionCommand().equals("Save"))
		{
			save();
		}
		if(e.getActionCommand().equals("Save as"))
		{
			saveAs();
		}
		if(e.getActionCommand().equals("exit"))
		{
			System.exit(0);
		}
		if(e.getActionCommand().equals("New"))
		{
			WordArea doc = new WordArea();
			tabPane.addTab(doc.getName(), doc);
			tabPane.setSelectedComponent(doc);
		}
		if(e.getActionCommand().equals("Open Image"))
		{
			openImage();
		}
		if(e.getActionCommand().equals("Snipping Tool"))
		{
			cropImage();
		}
		
	}

	private void cropImage() {
		
	}

	private void save() 
	{
		WordArea doc = (WordArea) tabPane.getSelectedComponent();
		doc.save();
	}
	
	public void saveAs()
	{
		JFileChooser chooser = new JFileChooser("./");
		int returned = chooser.showSaveDialog(this);
		
		if(returned == JFileChooser.APPROVE_OPTION)
		{
			File file = chooser.getSelectedFile();
			WordArea doc = (WordArea) tabPane.getSelectedComponent();
			
			doc.saveAs(file.getAbsolutePath());
			tabPane.setTitleAt(tabPane.getSelectedIndex(), file.getName());
		}
	}

	private void open() 
	{
		JFileChooser chooser = new JFileChooser("./");
		int returned = chooser.showOpenDialog(this);
		
		if(returned == JFileChooser.APPROVE_OPTION)
		{
			File file = chooser.getSelectedFile();
			
			WordArea doc = new WordArea(file.getName(), new JTextArea());
			tabPane.addTab(file.getName(), doc);
			tabPane.setSelectedComponent(doc);
			
			try 
			{
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;
				
				while((line = br.readLine()) != null)
				{
					doc.getText().append(line + "\n");
				}
				
				br.close();
			} catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	private void openImage() 
	{
		JFileChooser chooser = new JFileChooser("./");
		int returned = chooser.showOpenDialog(this);
		
		if(returned == JFileChooser.APPROVE_OPTION)
		{
			File file = chooser.getSelectedFile();
			
			TessAction tess = new TessAction();
			tess.readImage(file.getAbsolutePath());
		}
	}

}
