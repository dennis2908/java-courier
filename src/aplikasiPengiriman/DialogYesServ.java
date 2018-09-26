package aplikasiPengiriman;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class DialogYesServ extends JDialog 
{

	private final JPanel contentPanel = new JPanel();
	JTextField txtCariKota;
	JTable tabel;
	String headerKota[] = {"Kode","Kotamadya","Propinsi"};
	DefaultTableModel tabelModel;
	FrmEntryZonaTarif zt= new FrmEntryZonaTarif(); 

	/**
	 * Create the dialog.
	 */
	public DialogYesServ() 
	{
		setBounds(100, 100, 587, 284);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setVisible(true);
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblKota = new JLabel("Kota : ");
		lblKota.setBounds(12, 12, 70, 15);
		contentPanel.add(lblKota);
		
		txtCariKota = new JTextField();
		txtCariKota.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				tabelModel.getDataVector().removeAllElements();
				tabelModel.fireTableDataChanged();
				try
				{
					Connection konek = Koneksi.getKoneksi();
					Statement state = konek.createStatement();
					String query = "SELECT *  FROM Wilayah WHERE Kotamadya = '"+txtCariKota.getText()+"'";
					ResultSet rs = state.executeQuery(query);
					while(rs.next())
					{
						Object obj[] = new Object[3];
						
						obj[0] = rs.getString(1);
						obj[1] = rs.getString(2);
						obj[2] = rs.getString(3);
						
						tabelModel.addRow(obj);
					}
					rs.close();
					state.close();
				}
				catch(Exception ex)
				{
					System.out.println(ex);
				}
			}
		});
		txtCariKota.addKeyListener(new keyTextFieldWilayah(txtCariKota));
		txtCariKota.setBounds(77, 10, 286, 19);
		contentPanel.add(txtCariKota);
		txtCariKota.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(87, 47, 3, 3);
		contentPanel.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(77, 64, 441, 120);
		contentPanel.add(scrollPane_1);
		
		tabelModel = new DefaultTableModel(null,headerKota);
		tabel = new JTable();
		tabel.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent me) 
			{
				int pilih = tabel.getSelectedRow();
				if(pilih == -1)
				{
					return;
				}
				
				String kodeYes = (String) tabelModel.getValueAt(pilih, 0);
				zt.txtKodeYes.setText(kodeYes);
				String kotamadyaYes = (String) tabelModel.getValueAt(pilih, 1);
				zt.txtKotamadyaYes.setText(kotamadyaYes);
				String propinsiYes = (String) tabelModel.getValueAt(pilih, 2);
				zt.txtPropinsiYes.setText(propinsiYes);
				dispose();
				zt.tabbedPane.setSelectedIndex(2);
			}
		});
		tabel.setModel(tabelModel);
		scrollPane_1.setViewportView(tabel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("src/GambarApp/bgDialog.jpg"));
		lblNewLabel.setBounds(0, 0, 583, 257);
		contentPanel.add(lblNewLabel);
		tampilTabel();
	}
	
	public void tampilTabel()
	{
		try
		{
			Connection konek = Koneksi.getKoneksi();
			Statement state = konek.createStatement();
			String query = "SELECT * FROM Wilayah";
			ResultSet rs = state.executeQuery(query);
			while(rs.next())
			{
			Object obj[] = new Object[3];
			
			obj[0] = rs.getString(1);
			obj[1] = rs.getString(2);
			obj[2] = rs.getString(3);
			
			tabelModel.addRow(obj);
			}
			rs.close();
			state.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}

}
