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
public class DialogRegServ extends JDialog 
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
	public DialogRegServ() 
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
				
				String kode = (String) tabelModel.getValueAt(pilih, 0);
				zt.txtKodeReg.setText(kode);
				String kotamadya = (String) tabelModel.getValueAt(pilih, 1);
				zt.txtKotamadyaReg.setText(kotamadya);
				String propinsi = (String) tabelModel.getValueAt(pilih, 2);
				zt.txtPropinsiReg.setText(propinsi);
				dispose();
			}
		});
		tabel.setModel(tabelModel);
		scrollPane_1.setViewportView(tabel);
		
		JLabel lblBgDialogReg = new JLabel("");
		lblBgDialogReg.setIcon(new ImageIcon("src/GambarApp/bgDialog.jpg"));
		lblBgDialogReg.setBounds(0, 0, 583, 257);
		contentPanel.add(lblBgDialogReg);
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
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			DialogRegServ dialog = new DialogRegServ();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
