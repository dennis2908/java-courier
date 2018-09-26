package aplikasiPengiriman;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

@SuppressWarnings("serial")
public class FrmEntryPengirim extends JFrame 
{

	private JPanel contentPane;
	private JTextField txtNoPengirim;
	private JTextField txtNama;
	private JTextField txtPerusahaan;
	private JTextField txtKota;
	private JTextField txtPropinsi;
	private JTextArea textAlamat;
	private JLabel lblNopengirim;
	private JLabel lblNama;
	private JLabel lblNamaPerusahaan;
	private JLabel lblAlamat;
	private JLabel lblKota;
	private JLabel lblPropinsi;
	private JTextField txtKdPos;
	private JLabel lblKodePos;
	private JTextField txtNegara;
	private JTextField txtTelepon;
	private JTable tabel;
	private JScrollPane scrollPane;
	private JButton btnSimpan;
	DefaultTableModel tabelModel;
	String header[] = {"No.Pengirim","Nama","Nama PT","Alamat","Kota","Propinsi","KodePos","Negara","Telepon"};
	private JButton btnEdit;
	private JButton btnHapus;
	private JLabel lblWall;
	private JTextField txtCariNama;
	private JButton btnRefresh;
	private JLabel lblIconPengirim;

	/**
	 * Create the frame.
	 */
	public FrmEntryPengirim()
	{
		super("Menu Pengirim");
		setResizable(false);
		setBounds(100, 100, 904, 667);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		
		lblNopengirim = new JLabel("No.Pengirim : ");
		lblNopengirim.setForeground(new Color(255, 255, 255));
		lblNopengirim.setBounds(12, 12, 99, 15);
		contentPane.add(lblNopengirim);
		
		txtNoPengirim = new JTextField();
		txtNoPengirim.setBounds(161, 10, 144, 19);
		contentPane.add(txtNoPengirim);
		txtNoPengirim.setColumns(10);
		
		lblNama = new JLabel("Nama : ");
		lblNama.setForeground(new Color(255, 255, 255));
		lblNama.setBounds(12, 47, 70, 15);
		contentPane.add(lblNama);
		
		txtNama = new JTextField();
		txtNama.setBounds(161, 41, 404, 19);
		contentPane.add(txtNama);
		txtNama.setColumns(10);
		
		lblNamaPerusahaan = new JLabel("Nama Perusahaan :");
		lblNamaPerusahaan.setForeground(new Color(255, 255, 255));
		lblNamaPerusahaan.setBounds(12, 74, 139, 15);
		contentPane.add(lblNamaPerusahaan);
		
		txtPerusahaan = new JTextField();
		txtPerusahaan.setBounds(161, 72, 315, 19);
		contentPane.add(txtPerusahaan);
		txtPerusahaan.setColumns(10);
		
		lblAlamat = new JLabel("Alamat : ");
		lblAlamat.setForeground(new Color(255, 255, 255));
		lblAlamat.setBounds(12, 101, 70, 15);
		contentPane.add(lblAlamat);
		
		textAlamat = new JTextArea();
		textAlamat.setBounds(161, 103, 332, 109);
		contentPane.add(textAlamat);
		
		lblKota = new JLabel("Kota : ");
		lblKota.setForeground(new Color(255, 255, 255));
		lblKota.setBounds(12, 228, 70, 15);
		contentPane.add(lblKota);
		
		txtKota = new JTextField();
		txtKota.setBounds(161, 224, 273, 19);
		contentPane.add(txtKota);
		txtKota.setColumns(10);
		
		lblPropinsi = new JLabel("Propinsi : ");
		lblPropinsi.setForeground(new Color(255, 255, 255));
		lblPropinsi.setBounds(12, 255, 99, 15);
		contentPane.add(lblPropinsi);
		
		txtPropinsi = new JTextField();
		txtPropinsi.setBounds(161, 255, 186, 19);
		contentPane.add(txtPropinsi);
		txtPropinsi.setColumns(10);
		
		lblKodePos = new JLabel("Kode Pos : ");
		lblKodePos.setForeground(new Color(255, 255, 255));
		lblKodePos.setBounds(12, 291, 99, 15);
		contentPane.add(lblKodePos);
		
		txtKdPos = new JTextField();
		txtKdPos.setBounds(160, 286, 114, 19);
		contentPane.add(txtKdPos);
		txtKdPos.setColumns(10);
		
		JLabel lblNegara = new JLabel("Negara : ");
		lblNegara.setForeground(new Color(255, 255, 255));
		lblNegara.setBounds(12, 318, 70, 15);
		contentPane.add(lblNegara);
		
		txtNegara = new JTextField();
		txtNegara.setBounds(161, 317, 219, 19);
		contentPane.add(txtNegara);
		txtNegara.setColumns(10);
		
		JLabel lblTelepon = new JLabel("Telepon : ");
		lblTelepon.setForeground(new Color(255, 255, 255));
		lblTelepon.setBounds(12, 350, 70, 15);
		contentPane.add(lblTelepon);
		
		txtTelepon = new JTextField();
		txtTelepon.setBounds(161, 348, 214, 19);
		contentPane.add(txtTelepon);
		txtTelepon.setColumns(10);
		
		btnSimpan = new JButton("Simpan");
		btnSimpan.setIcon(new ImageIcon("src/GambarApp/Simpan.png"));
		btnSimpan.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent act) 
			{
				try
				{
					Connection konek = Koneksi.getKoneksi();
					String query = "INSERT INTO Pengirim VALUES(?,?,?,?,?,?,?,?,?)";
					PreparedStatement prepare = konek.prepareStatement(query);
					
					prepare.setString(1,txtNoPengirim.getText());
					prepare.setString(2,txtNama.getText());
					prepare.setString(3,txtPerusahaan.getText());
					prepare.setString(4,textAlamat.getText());
					prepare.setString(5,txtKota.getText());
					prepare.setString(6,txtPropinsi.getText());
					prepare.setString(7,txtKdPos.getText());
					prepare.setString(8,txtNegara.getText());
					prepare.setString(9,txtTelepon.getText());
					
					prepare.executeUpdate();
					JOptionPane.showMessageDialog(null,"Data berhasil disimpan","Pesan",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("src/GambarApp/Berhasil.png"));
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,"Data gagal disimpan","Pesan",JOptionPane.ERROR_MESSAGE, new ImageIcon("src/GambarApp/Gagal.png"));
					System.out.println(ex);
				}
				finally
				{
					tampilTabel();
					setLebarKolom();
					autoNumber();
					txtNama.setText("");
					txtPerusahaan.setText("");
					textAlamat.setText("");
					txtKota.setText("");
					txtPropinsi.setText("");
					txtKdPos.setText("");
					txtNegara.setText("");
					txtTelepon.setText("");
					txtNama.requestFocus();
				}
			}
		});
		btnSimpan.setBounds(255, 578, 131, 42);
		contentPane.add(btnSimpan);
		
		scrollPane = new JScrollPane();
		scrollPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
		scrollPane.setBounds(161, 397, 680, 169);
		contentPane.add(scrollPane);
		
		tabelModel = new DefaultTableModel(null,header);
		tabel = new JTable();
		tabel = new ZebraColorClass(tabelModel);
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
				txtNoPengirim.setText(noPengirim);
				String nama = (String) tabelModel.getValueAt(pilih, 1);
				txtNama.setText(nama);
				String namaPT = (String) tabelModel.getValueAt(pilih, 2);
				txtPerusahaan.setText(namaPT);
				String alamat = (String) tabelModel.getValueAt(pilih, 3);
				textAlamat.setText(alamat);
				String kota = (String) tabelModel.getValueAt(pilih, 4);
				txtKota.setText(kota);
				String propinsi = (String) tabelModel.getValueAt(pilih, 5);
				txtPropinsi.setText(propinsi);
				String kodePos = (String) tabelModel.getValueAt(pilih, 6);
				txtKdPos.setText(kodePos);
				String negara = (String) tabelModel.getValueAt(pilih, 7);
				txtNegara.setText(negara);
				String noTelp = (String) tabelModel.getValueAt(pilih, 8);
				txtTelepon.setText(noTelp);
			}
			
		});
		tabel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabel.setModel(tabelModel);
		scrollPane.setViewportView(tabel);
		
		btnEdit = new JButton("Ubah");
		btnEdit.setIcon(new ImageIcon("src/GambarApp/Edit.png"));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent act)
			{
				try
				{
					Connection konek = Koneksi.getKoneksi();
					String query = "UPDATE Pengirim SET Nama = ?, NamaPT = ?, Alamat = ?, Kota = ?, Propinsi = ?, KodePos = ?, Negara = ?, Telepon = ? WHERE NoPengirim = ? ";
					PreparedStatement prepare = konek.prepareStatement(query);
					
					prepare.setString(1,txtNama.getText());
					prepare.setString(2,txtPerusahaan.getText());
					prepare.setString(3,textAlamat.getText());
					prepare.setString(4,txtKota.getText());
					prepare.setString(5,txtPropinsi.getText());
					prepare.setString(6,txtKdPos.getText());
					prepare.setString(7,txtNegara.getText());
					prepare.setString(8,txtTelepon.getText());
					prepare.setString(9,txtNoPengirim.getText());
					
					prepare.executeUpdate();
					JOptionPane.showMessageDialog(null,"Data berhasil disimpan","Pesan",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/GambarApp/Berhasil.png"));
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,"Data gagal disimpan","Pesan",JOptionPane.ERROR_MESSAGE, new ImageIcon("src/GambarApp/Gagal.png"));
					System.out.println(ex);
				}
				finally
				{
					tampilTabel();
					setLebarKolom();
					autoNumber();
				}
			}
		});
		btnEdit.setBounds(453, 578, 144, 42);
		contentPane.add(btnEdit);
		
		btnHapus = new JButton("Hapus");
		btnHapus.setIcon(new ImageIcon("src/GambarApp/Hapus.png"));
		btnHapus.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act) 
			{
				try
				{
					Connection konek = Koneksi.getKoneksi();
					String query = "DELETE FROM Pengirim WHERE NoPengirim = ? ";
					PreparedStatement prepare = konek.prepareStatement(query);
				
					prepare.setString(1,txtNoPengirim.getText());
					
					prepare.executeUpdate();
					JOptionPane.showMessageDialog(null,"Data berhasil dihapus","Pesan",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/GambarApp/Berhasil.png"));
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,"Data gagal dihapus","Pesan",JOptionPane.ERROR_MESSAGE, new ImageIcon("src/GambarApp/Gagal.png"));
					System.out.println(ex);
				}
				finally
				{
					tampilTabel();
					setLebarKolom();
					autoNumber();
					txtNama.setText("");
					txtPerusahaan.setText("");
					textAlamat.setText("");
					txtKota.setText("");
					txtPropinsi.setText("");
					txtKdPos.setText("");
					txtNegara.setText("");
					txtTelepon.setText("");
					txtNama.requestFocus();
				}
			}
		});
		btnHapus.setBounds(670, 578, 144, 42);
		contentPane.add(btnHapus);
		
		txtCariNama = new JTextField();
		txtCariNama.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent act)
			{
				tabelModel.getDataVector().removeAllElements();
				tabelModel.fireTableDataChanged();
				try
				{
					Connection konek = Koneksi.getKoneksi();
					Statement state = konek.createStatement();
					String query = "SELECT *  FROM Pengirim WHERE Nama = '"+txtCariNama.getText()+"'";
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
						int b = rs.getInt(7);
						String kdPos = String.format("%03d", b);
						obj[6] = kdPos;
						obj[7] = rs.getString(8);
						int c = rs.getInt(9);
						String telp = String.format("%03d", c);
						obj[8] = telp;
						
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
		txtCariNama.setBounds(541, 363, 186, 19);
		txtCariNama.addKeyListener(new keyTextFieldCariNama(txtCariNama));
		contentPane.add(txtCariNama);
		txtCariNama.setColumns(10);
		
		JLabel lblCariNama = new JLabel("Cari berdasarkan nama : ");
		lblCariNama.setForeground(new Color(255, 255, 255));
		lblCariNama.setBounds(541, 336, 186, 15);
		contentPane.add(lblCariNama);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent act) 
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
						int b = rs.getInt(7);
						String kdPos = String.format("%03d", b);
						obj[6] = kdPos;
						obj[7] = rs.getString(8);
						int c = rs.getInt(9);
						String telp = String.format("%03d", c);
						obj[8] = telp;
						
						tabelModel.addRow(obj);
					}
					rs.close();
					state.close();
				}
				catch(Exception ex)
				{
					System.out.println(ex);
				}
				finally
				{
					txtCariNama.setText("");
					txtNama.setText("");
					textAlamat.setText("");
					txtPerusahaan.setText("");
					txtKota.setText("");
					txtPropinsi.setText("");
					txtKdPos.setText("");
					txtNegara.setText("");
					txtTelepon.setText("");
					txtNama.requestFocus();
					autoNumber();
				}
			}
		});
		btnRefresh.setIcon(new ImageIcon("src/GambarApp/Refresh.png"));
		btnRefresh.setBounds(748, 360, 109, 25);
		contentPane.add(btnRefresh);
		
		lblIconPengirim = new JLabel("");
		lblIconPengirim.setIcon(new ImageIcon("src/GambarApp/iconPengirim.png"));
		lblIconPengirim.setBounds(610, 74, 287, 231);
		contentPane.add(lblIconPengirim);
		
		lblWall = new JLabel("");
		lblWall.setIcon(new ImageIcon("src/GambarApp/wallMenuPengirim.jpg"));
		lblWall.setBounds(0, 0, 1007, 781);
		contentPane.add(lblWall);
		
		tampilTabel();
		setLebarKolom();
		autoNumber();
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
	
	public void autoNumber()
	{
		try
		{
			Connection konek = Koneksi.getKoneksi();
			Statement state = konek.createStatement();
			String query = "SELECT MAX(right(NoPengirim,2)) AS no FROM Pengirim";
			
			ResultSet rs = state.executeQuery(query);
			while(rs.next())
			{
				if(rs.first() == false)
				{
					txtNoPengirim.setText("P001");
				}
				else
				{
					rs.last();
					int noKirim = rs.getInt(1) + 1;
					String no = String.valueOf(noKirim);
					int noLong = no.length();
					for(int a=0;a<2-noLong;a++)
					{
						no = "00" + no;
					}
					txtNoPengirim.setText("P" + no);
				}
					
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
