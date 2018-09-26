package aplikasiPengiriman;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class DialogFPK extends JDialog 
{

	private final JPanel contentPanel = new JPanel();
	JTextField txtCariAWB;
	JTable tabel;
	String header[] = {"No.AWB","NoPengirim","Nama","Alamat","Telp","TglKirim","Penerima","Alamat","Pembayaran","Paket","Asuransi"};
	DefaultTableModel tabelModel; 
	FrmCetakFPK fpk = new FrmCetakFPK();

	
	public DialogFPK() 
	{
		setBounds(100, 100, 587, 284);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setVisible(true);
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblAWB = new JLabel("No AWB : ");
		lblAWB.setBounds(12, 12, 70, 15);
		contentPanel.add(lblAWB);
		
		txtCariAWB = new JTextField();
		txtCariAWB.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				tabelModel.getDataVector().removeAllElements();
				tabelModel.fireTableDataChanged();
				try
				{
					Connection konek = Koneksi.getKoneksi();
					Statement state = konek.createStatement();
					String query = "SELECT Pengiriman.NoAWB,Pengirim.NoPengirim,Pengirim.Nama,Pengirim.Alamat,Pengirim.Telepon,Pengiriman.TglKirim,Pengiriman.Nama,Pengiriman.Alamat,Pengiriman.Pembayaran,Pengiriman.Paket,Pengiriman.Asuransi FROM Pengirim INNER JOIN Pengiriman ON Pengirim.NoPengirim = Pengiriman.NoPengirim WHERE NoAWB = '"+txtCariAWB.getText()+"'";
					ResultSet rs = state.executeQuery(query);
					while(rs.next())
					{
						Object obj[] = new Object[11];
						
						obj[0] = rs.getString(1);
						obj[1] = rs.getString(2);
						obj[2] = rs.getString(3);
						obj[3] = rs.getString(4);
						obj[4] = rs.getString(5);
						obj[5] = rs.getString(6);
						obj[6] = rs.getString(7);
						obj[7] = rs.getString(8);
						obj[8] = rs.getString(9);
						obj[9] = rs.getString(10);
						obj[10] = rs.getString(11);
						
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
		txtCariAWB.setBounds(104, 10, 286, 19);
		contentPanel.add(txtCariAWB);
		txtCariAWB.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(87, 47, 3, 3);
		contentPanel.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(77, 64, 441, 120);
		contentPanel.add(scrollPane_1);
		
		tabelModel = new DefaultTableModel(null,header);
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
				
				String AWB = (String) tabelModel.getValueAt(pilih, 0);
				fpk.txtAWB.setText(AWB);
				String noPengirim = (String) tabelModel.getValueAt(pilih, 1);
				fpk.txtKdPengirim.setText(noPengirim);
				String pengirim = (String) tabelModel.getValueAt(pilih, 2);
				fpk.txtNama.setText(pengirim);
				String alamat = (String) tabelModel.getValueAt(pilih, 3);
				fpk.textAlamat.setText(alamat);
				String Telepon = (String) tabelModel.getValueAt(pilih, 4);
				fpk.txtTelp.setText(Telepon);
				String TglKirim = (String) tabelModel.getValueAt(pilih, 5);
				fpk.txtTglKirim.setText(TglKirim);
				String Penerima = (String) tabelModel.getValueAt(pilih,6);
				fpk.txtPenerima.setText(Penerima);
				String Tujuan = (String) tabelModel.getValueAt(pilih, 7);
				fpk.txtTujuan.setText(Tujuan);
				String Pembayaran = (String) tabelModel.getValueAt(pilih, 8);
				fpk.txtPembayaran.setText(Pembayaran);
				String Paket = (String) tabelModel.getValueAt(pilih, 9);
				fpk.txtService.setText(Paket);
				String Asuransi = (String) tabelModel.getValueAt(pilih, 10);
				fpk.txtGantiRugi.setText(Asuransi);
				fpk.txtGantiRugi.requestFocus();
				dispose();
			}
		});
		tabel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabel.setModel(tabelModel);
		scrollPane_1.setViewportView(tabel);
		
		JLabel lblBgDialog = new JLabel("");
		lblBgDialog.setIcon(new ImageIcon("src/GambarApp/bgDialog.jpg"));
		lblBgDialog.setBounds(0, 0, 583, 257);
		contentPanel.add(lblBgDialog);
		tampilTabel();
		setLebarKolom();
	}
	
	public void tampilTabel()
	{
		tabelModel.getDataVector().removeAllElements();
		tabelModel.fireTableDataChanged();
		try
		{
			Connection konek = Koneksi.getKoneksi();
			Statement state = konek.createStatement();
			String query = "SELECT Pengiriman.NoAWB,Pengirim.NoPengirim,Pengirim.Nama,Pengirim.Alamat,Pengirim.Telepon,Pengiriman.TglKirim,Pengiriman.Nama,Pengiriman.Alamat,Pengiriman.Pembayaran,Pengiriman.Paket,Pengiriman.Asuransi FROM Pengirim INNER JOIN Pengiriman ON Pengirim.NoPengirim = Pengiriman.NoPengirim";
			ResultSet rs = state.executeQuery(query);
			while(rs.next())
			{
				Object obj[] = new Object[11];
				obj[0] = rs.getString(1);
				obj[1] = rs.getString(2);
				obj[2] = rs.getString(3);
				obj[3] = rs.getString(4);
				obj[4] = rs.getString(5);
				obj[5] = rs.getString(6);
				obj[6] = rs.getString(7);
				obj[7] = rs.getString(8);
				obj[8] = rs.getString(9);
				obj[9] = rs.getString(10);
				obj[10] = rs.getString(11);
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
	
	public void setColumnWidth(int kolom)
	{
		DefaultTableColumnModel modelKolom = (DefaultTableColumnModel) tabel.getColumnModel();
		TableColumn kolomTabel = modelKolom.getColumn(kolom);
		int lebar = 0;
		int margin = 10;
		int a;
		
		TableCellRenderer renderer = kolomTabel.getHeaderRenderer();
		if(renderer == null)
		{
			renderer = tabel.getTableHeader().getDefaultRenderer();
		}
		Component komponen = renderer.getTableCellRendererComponent(tabel, kolomTabel.getHeaderValue(), false, false, 0, 0);
		lebar = komponen.getPreferredSize().width;
			for(a=0;a<tabel.getRowCount();a++)
			{
				renderer = tabel.getCellRenderer(a, kolom);
				komponen = renderer.getTableCellRendererComponent(tabel, tabel.getValueAt(a, kolom), false, false, a, kolom);
				int lebarKolom = komponen.getPreferredSize().width;
				lebar = Math.max(lebar, lebarKolom);
				
			}
			lebar  = lebar + margin;
			kolomTabel.setPreferredWidth(lebar);
	}
	
	public void setLebarKolom()
	{
		int a;
		for(a=0;a<tabel.getColumnCount();a++)
		{
			setColumnWidth(a);
		}
	}
}