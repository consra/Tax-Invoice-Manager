package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Implementare.Produs;
import Implementare.Testare;

public class GestionareProduse extends JPanel{
	
	final int width = 20;
	final int height = 20;
	
	static Vector<Produs> produse = new Vector<Produs>();
	static Vector<String> tari = new Vector<String>();
	
	JTextField CampProdus  = new JTextField(10);
	JTextField CampCategorie = new JTextField(10);
	JTextField CampTara  = new JTextField(10);
	JTextField CampPret  = new JTextField(10);
	JTextField Cautare = new JTextField(15);
	JButton cautare = new JButton("Cauta");
	static DefaultTableModel model;
	Produs SelectedProduct = null;
	static String antet = null;
	JButton AfiseazaProduse = new JButton("Afiseaza Produse");
	
	public static Object[][] getData() throws IOException
	{
		Object[][] data = new Object[20][20];
		FileReader f = null;
		try {
			f = new FileReader("produse.txt");
			BufferedReader in = new BufferedReader(f);
			String linie = in.readLine();
			antet = linie;
			String []tara = linie.split(" ");
			for(int i = 2; i < tara.length; i++)
			{
				tari.add(tara[i]);
			}
 		
			while((linie=in.readLine()) != null)
			{
				int j = 2;
				Vector<PairF> taxe = new Vector<PairF>();
				for(String t : tari) {
					produse.add(new Produs(
					linie.split(" ")[0],	
					linie.split(" ")[1],
					t,
					linie.split(" ")[j]
				));
					taxe.add(new PairF(t,linie.split(" ")[j++]));
				}
			}
		
			int i = 0;
			for(Produs e : produse)
			{
				data[i][0] = e.getDenumire();
				data[i][1] = e.getCategorie();
				data[i][2] = e.getTaraOrigine();
				data[i][3] = String.valueOf(e.getPret());
				i++;
			}
			System.out.println(i + " " + produse.size());
		}
		finally{
			if(f != null)
				f.close();
		}
		return data;
	}
	static double returnPret(String tara,String nume)
	{
		for(Produs e : produse)
		{
			if(e.getDenumire().equals(nume) && e.getTaraOrigine().equals(tara))
				return e.getPret(); 
		}
		return 0.0;
	}
	static void writeProductsToFile()
	{
		Writer writer = null;
		try{
			FileOutputStream users = new FileOutputStream("produse.txt",false);
			writer = new BufferedWriter(new OutputStreamWriter(
			          users, "utf-8"));
			TreeMap<String,Double> names = new TreeMap<String,Double>();
			writer.write(antet+"\n");
			for(Produs e : produse)
			{
				if(!names.containsKey(e.getDenumire())){
					names.put(e.getDenumire(), 0.0);
					writer.write(e.getDenumire() + " " + e.getCategorie() + " ");
					for(String tara : tari)
					{
						writer.write(String.valueOf(returnPret(tara,e.getDenumire())) + " ");
					}
					writer.write("\n");
				}
			}
		}
		catch (IOException ex) {
		  ex.printStackTrace();
		} finally {
		   try {writer.close();} catch (Exception ex) {/*ignore*/}
		}
	}
	static JTable createJTable() throws IOException
	{
		 String[] columns = new String[] {
				    "Produs", "Categorie", "Tara Origine", "Pret"
		 };
				 
				 
		 final Class[] columnClass = new Class[] {
		      String.class, String.class, String.class, String.class
		 };


				
		model = new DefaultTableModel(getData(), columns) {
				            @Override
			  public boolean isCellEditable(int row, int column)
		      {
				  return false;
			  }
				            @Override
			  public Class<?> getColumnClass(int columnIndex)
		      {
		          return columnClass[columnIndex];
			  }
		
				 };
				 JTable tabel = new JTable(model);
			 for(int j=model.getRowCount()-1;j> 0;j--)
		     {
				 if(!(tabel.getValueAt(j, 0) != null 
		         && tabel.getValueAt(j, 0).toString().trim().length() != 0) )
		        {
		               model.removeRow(j);
		        }

		      }
		

		tabel.setPreferredSize(new Dimension(700,700));
		return tabel;
	}
	GestionareProduse() throws IOException
	{
		
		   setLayout(new GridBagLayout());
		  
           GridBagConstraints gbc = new GridBagConstraints();
   			setName("");
   	 
   			JPanel panel = new JPanel(new GridBagLayout());
   	 
   			JTable t = createJTable();
   			Dimension d = t.getPreferredSize();
   			JScrollPane scrollPane = new JScrollPane();
//   			scrollPane.setPreferredSize(
//   			    new Dimension( d.width, t.getRowHeight() * (5+1) ));
   			
   	 
   			JPanel tableButtonPanel = new JPanel();
   			JButton adauga = new JButton("Adauga Produs");
   			JButton sterge = new JButton("Sterge Produs");
   			JButton editeaza =  new JButton("Editeaza");
   			tableButtonPanel.add(adauga);
   			tableButtonPanel.add(sterge);
   			tableButtonPanel.add(editeaza);
   	 
   			JButton sorteaza = new JButton("Sorteaza");
   			JPanel buttonPanel = new JPanel();
   			buttonPanel.add(sorteaza);
   		    JRadioButton tara = new JRadioButton("dupa tara");
   		    JRadioButton nume = new JRadioButton("dupa nume");
   		    tara.setSelected(true); 
   		    
   		    ButtonGroup btnGroup = new ButtonGroup();
   		    btnGroup.add(tara);
   		    btnGroup.add(nume);
   		    buttonPanel.add(tara);
   		    buttonPanel.add(nume);
   			
   			GridBagConstraints gbc1 = new GridBagConstraints();
   			TitledBorder border = new TitledBorder("Gestionare Produse");
   			border.setTitleJustification(TitledBorder.CENTER);
   			border.setTitlePosition(TitledBorder.TOP);
   			border.setTitleFont(new Font("Courier New", Font.ITALIC, 25));
   			this.setBorder(border);
   	 
   			gbc1.gridx = 0;
   			gbc1.gridy = 1;
   			gbc1.anchor = GridBagConstraints.NORTH;
   		
   			panel.add(new JScrollPane(t), gbc1);
   	 
   			gbc1.gridx = 0;
   			gbc1.gridy = 2;
   			gbc1.anchor = GridBagConstraints.SOUTH;
   			panel.add(tableButtonPanel, gbc1);
   	 
   			gbc1.gridx = 0;
   			gbc1.gridy = 3;
   			gbc1.gridwidth = 2;
   			panel.add(buttonPanel, gbc1);
   	 
   			gbc1.gridx = 1;
   			gbc1.gridy = 0;
   			gbc1.gridwidth = 1;
   			gbc1.gridheight = 2;
   			gbc1.anchor = GridBagConstraints.EAST;
   			panel.add(new InformationPane(CampProdus,CampCategorie,CampTara,
   			CampPret,Cautare,cautare), gbc1);
   			panel.setPreferredSize(new Dimension(800,600));
   			this.add(panel);
   			
   			t.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
   			    @Override
   			    public void valueChanged(ListSelectionEvent event) {
   			        if (t.getSelectedRow() > -1) {
   			         CampProdus.setText(t.getValueAt(t.getSelectedRow(), 0).toString());
   			         CampCategorie.setText(t.getValueAt(t.getSelectedRow(), 1).toString());
   			         CampTara.setText(t.getValueAt(t.getSelectedRow(), 2).toString());
   			         CampPret.setText(t.getValueAt(t.getSelectedRow(), 3).toString());
   			        
   			        String nume = t.getValueAt(t.getSelectedRow(), 0).toString();
					String cat = t.getValueAt(t.getSelectedRow(), 1).toString();  
					String tR = t.getValueAt(t.getSelectedRow(), 2).toString();
					String pret = t.getValueAt(t.getSelectedRow(), 3).toString();
					SelectedProduct = new Produs(nume,cat,tR,pret);
   			        }
   			    }
   			});
   			adauga.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					String nume = CampProdus.getText();
					String cat = CampCategorie.getText();  
					String tR = CampTara.getText();
					String pret = CampPret.getText();
					
					Produs nou = new Produs(nume,cat,tR,pret);
					
					if(produse.contains(nou) == true)
					{
						JOptionPane.showMessageDialog(panel,"Produsul exista deja");
					}
					
					produse.add(nou);
					Object[] rowData = new Object[10];
					
					rowData[0] = nume;
					rowData[1] = cat;
					rowData[2] = tR;
					rowData[3] = pret;
					model.addRow(rowData);
					writeProductsToFile();
				}
   			}
   			
   			);

   			sterge.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					String nume = CampProdus.getText();
					String cat = CampCategorie.getText();  
					String tR = CampTara.getText();
					String pret = CampPret.getText();
					
					Produs nou = new Produs(nume,cat,tR,pret);
					int index = -1;
					for(int i = 0 ; i < produse.size() ; i++)
					{
						Produs aux = produse.elementAt(i);
						if(aux.getDenumire().equals(nume) && aux.getPret() 
								==Double.parseDouble(pret)){
							index = i;
							System.out.println(aux.getCategorie());
							System.out.println(index);
							System.out.println(model.getRowCount());
							break;
							
							
					}}
					if(index != -1 ){//&& index < model.getRowCount()) {
						model.removeRow(index);
						produse.remove(index);
						System.out.println(produse);
						writeProductsToFile();
					}
				}
   			}
   			);
   			TableRowSorter<TableModel> sorter1 = new TableRowSorter<>(model); 
   			t.setRowSorter(sorter1);
   			Cautare.getDocument().addDocumentListener(new DocumentListener(){
   				@Override 
   				public void insertUpdate(DocumentEvent e) {
   					search(Cautare.getText());

   				}
   				@Override 
   				public void removeUpdate(DocumentEvent e) {
   					search(Cautare.getText());
   				}
   				@Override 
   				public void changedUpdate(DocumentEvent e) {
   					search(Cautare.getText());
   				}
   				public void search(String s)
   				{
   					if (s.length() == 0) {

   						sorter1.setRowFilter(null);
   					} else {

   						sorter1.setRowFilter(RowFilter.regexFilter(s));
   					}
   					}
   				});
   			sterge.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					String nume = CampProdus.getText();
					String cat = CampCategorie.getText();  
					String tR = CampTara.getText();
					String pret = CampPret.getText();
					
					Produs nou = new Produs(nume,cat,tR,pret);
					int index = -1;
					for(int i = 0 ; i < produse.size() ; i++)
					{
						Produs aux = produse.elementAt(i);
						if(aux.getDenumire().equals(nume) && aux.getPret() 
								==Double.parseDouble(pret)){
							index = i;
							System.out.println(aux.getCategorie());
							System.out.println(index);
							System.out.println(model.getRowCount());
							break;
							
							
					}}
					if(index != -1 ){//&& index < model.getRowCount()) {
						model.removeRow(index);
						produse.remove(index);
						System.out.println(produse);
						writeProductsToFile();
					}
				}
   			}
   			);
   			editeaza.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					if(SelectedProduct != null)
					{
						String nume = CampProdus.getText();
						String cat = CampCategorie.getText();  
						String tR = CampTara.getText();
						String pret = CampPret.getText();
						
						Produs nou = new Produs(nume,cat,tR,pret);
						int index = -1;
						for(int i = 0 ; i < produse.size() ; i++)
						{
							Produs aux = produse.elementAt(i);
							if(aux.getDenumire().equals(SelectedProduct.getDenumire()) && aux.getPret() 
									== SelectedProduct.getPret()){
								index = i;
								break;
						}}
						if(index != -1) {
							model.removeRow(index);
							produse.remove(index);
						
						produse.add(nou);
						Object[] rowData = new Object[10];
						
						rowData[0] = nume;
						rowData[1] = cat;
						rowData[2] = tR;
						rowData[3] = pret;
						model.addRow(rowData);
						writeProductsToFile();
						}
					}						
		
					
				}
   			}
			);
   			sorteaza.addActionListener(new ActionListener(){

			  public void actionPerformed(ActionEvent e) {
				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>( t.getModel());
				if(nume.isSelected()) {
					t.setRowSorter(sorter);

					List<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>(model.getRowCount());
					sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
					sorter.setSortKeys(sortKeys);
				}
				else if(tara.isSelected())
				{
					t.setRowSorter(sorter);
					List<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>(model.getRowCount());
					sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
					sorter.setSortKeys(sortKeys);
				}
				}
   			}
			);
	}
}
