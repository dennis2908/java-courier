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
public class DialogPengirim extends JDialog 
{

	private final JPanel contentPanel = new JPanel();
	JTextField txtCariPengirim;
	JTable tabel;
	String headerKota[] = {"No.Pengirim","Nama","Nama PT","Alamat","Kota","Propinsi","KodePos","Negara","Telepon"};
	DefaultTableModel tabelModel; 
	FrmCetakKirimBarang kb = new FrmCetakKirimBarang();

	
	public DialogPengirim() 
	{
		setBounds(100, 100, 587, 284);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setVisible(true);
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblKota = new JLabel("Nama : ");
		lblKota.setBounds(12, 12, 70, 15);
		contentPanel.add(lblKota);
		
		txtCariPengirim = new JTextField();
		txtCariPengirim.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				tabelModel.getDataVector().removeAllElements();
				tabelModel.fireTableDataChanged();
				try
				{
					Connection konek = Koneksi.getKoneksi();
					Statement state = konek.createStatement();
					String query = "SELECT *  FROM Pengirim WHERE Nama = '"+txtCariPengirim.getText()+"'";
					ResultSet rs = state.executeQuery(query);
					while(rs.next())
					{
						Object obj[] = new Object[9];
						
						obj[0] = rs.getString(1);
						obj[1] = rs.getString(2);
						obj[2] = rs.getString(3);
						obj[3] = rs.getString(4);
						obj[4] = rs.getString(5);
						obj[5] = rs.getString(6);
						obj[6] = rs.getString(7);
						obj[7] = rs.getString(8);
						obj[8] = rs.getString(9);
						
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
		txtCariPengirim.addKeyListener(new keyTextFieldCariNama(txtCariPengirim));
		txtCariPengirim.setBounds(77, 10, 286, 19);
		contentPanel.add(txtCariPengirim);
		txtCariPengirim.setColumns(10);
		
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
				
				String noPengirim = (String) tabelModel.getValueAt(pilih, 0);
				kb.txtNoPengirim.setText(noPengirim);
				String nama = (String) tabelModel.getValueAt(pilih, 1);
				kb.txtNamaPengirim.setText(nama);
				String namaPT = (String) tabelModel.getValueAt(pilih, 2);
				kb.txtNamaPTPengirim.setText(namaPT);
				String alamat = (String) tabelModel.getValueAt(pilih, 3);
				kb.textAlamatPengirim.setText(alamat);
				String kota = (String) tabelModel.getValueAt(pilih, 4);
				kb.txtKotaPengirim.setText(kota);
				String propinsi = (String) tabelModel.getValueAt(pilih, 5);
				kb.txtPropinsiPengirim.setText(propinsi);
				String kodePos = (String) tabelModel.getValueAt(pilih, 6);
				kb.txtKdPosPengirim.setText(kodePos);
				String negara = (String) tabelModel.getValueAt(pilih, 7);
				kb.txtNegaraPengirim.setText(negara);
				String noTelp = (String) tabelModel.getValueAt(pilih, 8);
				kb.txtTelpPengirim.setText(noTelp);
				dispose();
				kb.tabbedPane.setSelectedIndex(0);
			}
		});
		tabel.setModel(tabelModel);
		scrollPane_1.setViewportView(tabel);
		
		JLabel lblDialogPengirim = new JLabel("");
		lblDialogPengirim.setIcon(new ImageIcon("src/GambarApp/bgDialog.jpg"));
		lblDialogPengirim.setBounds(0, 0, 583, 257);
		contentPanel.add(lblDialogPengirim);
		tampilTabel();
	}
	
	public void tampilTabel()
	{
		tabelModel.getDataVector().removeAllElements();
		tabelModel.fireTableDataChanged();
		try
		{
			Connection konek = Koneksi.getKoneksi();
			Statement state = konek.createStatement();
			String query = "SELECT * FROM Pengirim";
			ResultSet rs = state.executeQuery(query);
			while(rs.next())
			{
				Object obj[] = new Object[9];
				obj[0] = rs.getString(1);
				obj[1] = rs.getString(2);
				obj[2] = rs.getString(3);
				obj[3] = rs.getString(4);
				obj[4] = rs.getString(5);
				obj[5] = rs.getString(6);
				obj[6] = rs.getString(7);
				obj[7] = rs.getString(8);
				obj[8] = rs.getString(9);
				
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