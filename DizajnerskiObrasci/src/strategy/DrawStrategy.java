package strategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import drawing_Stevanovic_Jelena_IT58_2018.Shape;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class DrawStrategy implements Strategy {

	private DrawingModel model;
	private DrawingFrame frame;
	
	public DrawStrategy(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}

	@Override
	public void saveFile() {
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify where to save file");   
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".ser", "ser");
		fileChooser.setFileFilter(filter);
		
		int userSelection = fileChooser.showSaveDialog(frame);
		File fileToSave; 
		
		
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			
			fileToSave = new File(fileChooser.getSelectedFile().getAbsolutePath());
			
			if(fileToSave != null) {
				String extension = filter.getExtensions()[0];

			    String newName = fileToSave.getName().trim() + "." + extension;
			    fileToSave = new File(fileToSave.getParent(), newName);
				
			    try {
					 
		            FileOutputStream fileOut = new FileOutputStream(fileChooser.getSelectedFile().getAbsolutePath());
		            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
		            for(int i = 0; i<model.getShapes().size(); i++) {
		            	objectOut.writeObject(model.get(i));
		            }
		            
		            frame.getCommands().add(frame.getCommands().size(),"Draw saved" );
		            
		            objectOut.close();
		            fileOut.close(); 

		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
			} 
		}
		
	}

	@Override
	public void loadFile() {
		
		model.getShapes().clear();
		frame.getCommands().clear();
		
		JFileChooser fileChooser = new JFileChooser();
		
		int userSelection = fileChooser.showOpenDialog(frame);
		
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave;
			fileToSave = new File(fileChooser.getSelectedFile().getAbsolutePath());
			
			FileInputStream fis; 
			
			try {
				
				fis = new FileInputStream(fileToSave);
				
				try (ObjectInputStream input = new ObjectInputStream(fis)) {
					
					while(fis.available() != 0) {
						Object obj = input.readObject();
						model.add((Shape) obj);
						frame.getCommands().add(frame.getCommands().size(),"Load draw" + " " + obj.toString());
					}
				    
				  } catch (Exception e) {
				    // System.out.println(e.printStackTrace());
				  }
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
