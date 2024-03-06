package strategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import mvc.DrawingFrame;
import mvc.DrawingModel;

public class LogStrategy implements Strategy {
	
	DrawingFrame frame;
	DrawingModel model;
	
	ArrayList<String> load;

	public LogStrategy(DrawingFrame frame) {
		this.frame = frame;
	}
	
	public LogStrategy(DrawingFrame frame, DrawingModel model) {
		this.frame = frame;
		this.model = model;
	}

	@Override
	public void saveFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify where to save file");   
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt", "text");
		fileChooser.setFileFilter(filter);
		
		
		int userSelection = fileChooser.showSaveDialog(frame);
		File fileToSave;  
		FileWriter fw;
		PrintWriter pw;
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    
			
			fileToSave = new File(fileChooser.getSelectedFile().getAbsolutePath());
			
			if(fileToSave != null) {
				String extension = filter.getExtensions()[0];

			    String newName = fileToSave.getName().trim() + "." + extension;
			    fileToSave = new File(fileToSave.getParent(), newName);
				
			    try {
					fw = new FileWriter(fileToSave);
					pw = new PrintWriter(fw);
					for(int i = 0; i<frame.getCommands().size(); i++) {
	
						pw.println(frame.getCommands().get(i));
					}
					pw.close();
					fw.close(); 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}

		}
	}

	@Override
	public void loadFile() {
		
		model.getShapes().clear();
		frame.getCommands().clear();
		load = new ArrayList<String>();
		JFileChooser fileChooser = new JFileChooser();
		int userSelection = fileChooser.showSaveDialog(frame);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave;
			fileToSave = new File(fileChooser.getSelectedFile().getAbsolutePath());
			try {
				Scanner scan = new Scanner(fileToSave);
				while(scan.hasNext()) {
					load.add(scan.nextLine());
					
				}

				for(int i = 0; i<load.size(); i++) {
					System.out.println(i + " " + load.get(i));
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i =0; i<model.getShapes().size(); i++) {
			model.get(i).setSelected(false);
		}
		
	}

}
