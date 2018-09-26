package aplikasiPengiriman;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.freixas.jcalendar.JCalendarCombo;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.text.NumberFormat;
import java.util.HashMap;
import java.awt.Color;
import java.io.File;

@SuppressWarnings("serial")
public class FrmCetakFPK extends JFrame 
{
	JPanel contentPane;
	 JTextField txtFPK;
	 JTextField txtNama;
	 JTextArea textAlamat;
	 JTextField txtTelp;
	 JTextField txtKTP;
	 JTextField txtAWB;
	 JTextField txtTglKirim;
	 JTextField txtPenerima;
	 JTextField txtTujuan;
	 JTextField txtPembayaran;
	 JTextField txtService;
	 JTextField txtGantiRugi;
	 JTextField txtTerbilang;
	 @SuppressWarnings("rawtypes")
	 JComboBox cbKeterangan;
	 @SuppressWarnings("rawtypes")
	 JComboBox cbBayar;
	 JTextField txtNamaBank;
	 JTextField txtCabang;
	 JTextField txtRekening;
	 JTextField txtAtasNama;
	 JButton btnSimpan;
	 JTextField txtKdPengirim;
	 JCalendarCombo tglFPK;
	 DefaultTableModel tabelModel;
	 String header[] = {"No.AWB","TglKirim","Penerima","Tujuan","Pembayaran","Service","Keterangan","Klaim","Pembayaran","Bank","Cabang","Rekening","Atas Nama"};
	 NumberFormat formatRupiah = NumberFormat.getCurrencyInstance();
	 JLabel lblWallFPK;
	 JButton btnCariAWB;
	 private JTable tabel;
	 private JButton btnCetak;
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public FrmCetakFPK() 
	{
		setResizable(false);
		setBounds(100, 100, 889, 673);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNoFpk = new JLabel("No FPK : ");
		lblNoFpk.setForeground(new Color(255, 255, 255));
		lblNoFpk.setBounds(12, 12, 70, 15);
		contentPane.add(lblNoFpk);
		
		txtFPK = new JTextField();
		txtFPK.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act) 
			{
				try
				{
					Connection konek = Koneksi.getKoneksi();
					Statement state = konek.createStatement();
					String query = "SELECT Pengiriman.NoAWB,FPK.TglFPK,Pengirim.NoPengirim,Pengirim.Nama,Pengirim.Alamat,Pengirim.Telepon,FPK.NoKTP,Pengiriman.TglKirim,Pengiriman.Nama,Pengiriman.Alamat,Pengiriman.Pembayaran,Pengiriman.Paket,FPK.Keterangan,FPK.Klaim,FPK.Pembayaran,FPK.Bank,FPK.Cabang,FPK.NoRekening,FPK.AtasNama FROM Pengiriman INNER JOIN FPK ON FPK.NoAWB = Pengiriman.NoAWB INNER JOIN Pengirim ON Pengirim.NoPengirim = Pengiriman.NoPengirim WHERE NoFPK = '"+txtFPK.getText()+"'";
					
					ResultSet rs = state.executeQuery(query);
					if(rs.next() == true)
					{
						Object obj[] = new Object[19];
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
					    obj[11] = rs.getString(12);
					    obj[12] = rs.getString(13);
					    obj[13] = rs.getInt(14);
					    obj[14] = rs.getString(15);
					    obj[15] = rs.getString(16);
					    obj[16] = rs.getString(17);
					    obj[17] = rs.getString(18);
					    obj[18] = rs.getString(19);
					    
					    txtAWB.setText("" + obj[0]);
					    tglFPK.setSelectedItem("" + obj[1]);
					    txtKdPengirim.setText("" + obj[2]);
					    txtNama.setText("" + obj[3]);
					    textAlamat.setText("" + obj[4]);
					    txtTelp.setText("" + obj[5]);
					    txtKTP.setText("" + obj[6]);
					    txtTglKirim.setText("" + obj[7]);
					    txtPenerima.setText("" + obj[8]);
					    txtTujuan.setText("" + obj[9]);
					    txtPembayaran.setText("" + obj[10]);
					    txtService.setText("" + obj[11]);
					    cbKeterangan.setSelectedItem(obj[12]);
					    //txtGantiRugi.setText("" + obj[13]);
					    int ganti = rs.getInt(14);
					    //int ganti = Integer.parseInt(txtGantiRugi.getText());
					    txtGantiRugi.setText(formatRupiah.format(ganti).substring(2));
					    txtTerbilang.setText("" + new KelasTerbilang(obj[13]).toString());
					    cbBayar.setSelectedItem(obj[14]);
					    txtNamaBank.setText("" + obj[15]);
					    txtCabang.setText("" + obj[16]);
					    txtRekening.setText("" + obj[17]);
					    txtAtasNama.setText("" + obj[18]);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Data tidak ditemukan");
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
					tampilTabel();
				}
			}
		});
		txtFPK.setBounds(121, 10, 128, 19);
		contentPane.add(txtFPK);
		txtFPK.setColumns(10);
		
		JLabel lblNama = new JLabel("Nama : ");
		lblNama.setForeground(new Color(255, 255, 255));
		lblNama.setBounds(12, 66, 70, 15);
		contentPane.add(lblNama);
		
		txtNama = new JTextField();
		txtNama.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					Connection konek = Koneksi.getKoneksi();
					Statement state = konek.createStatement();
					String query = "SELECT Pengirim.NoPengirim,Pengirim.Alamat,Pengirim.Telepon FROM Pengirim WHERE Nama = '"+txtNama.getText()+"'";
					ResultSet rs = state.executeQuery(query);
					while(rs.next())
					{
						String noPengirim = rs.getString(1);
						String alamat = rs.getString(2);
						String telepon = rs.getString(3);
						
						txtKdPengirim.setText(noPengirim);
						textAlamat.setText(alamat);
						txtTelp.setText(telepon);
					}
					rs.close();
					state.close();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,"Data tidak ditemukan");
					System.out.println(ex);
				}
			}
		});
		txtNama.setBounds(121, 64, 393, 19);
		contentPane.add(txtNama);
		txtNama.setColumns(10);
		
		JLabel lblAlamat = new JLabel("Alamat : ");
		lblAlamat.setForeground(new Color(255, 255, 255));
		lblAlamat.setBounds(525, 39, 70, 15);
		contentPane.add(lblAlamat);
		
		textAlamat = new JTextArea();
		textAlamat.setBounds(606, 39, 210, 50);
		contentPane.add(textAlamat);
		
		JLabel lblTelp = new JLabel("Telp : ");
		lblTelp.setForeground(new Color(255, 255, 255));
		lblTelp.setBounds(525, 103, 70, 15);
		contentPane.add(lblTelp);
		
		txtTelp = new JTextField();
		txtTelp.setBounds(606, 101, 150, 19);
		contentPane.add(txtTelp);
		txtTelp.setColumns(10);
		
		JLabel lblNoKtp = new JLabel("No KTP / SIM : ");
		lblNoKtp.setForeground(new Color(255, 255, 255));
		lblNoKtp.setBounds(12, 103, 99, 15);
		contentPane.add(lblNoKtp);
		
		txtKTP = new JTextField();
		txtKTP.setBounds(121, 101, 220, 19);
		contentPane.add(txtKTP);
		txtKTP.setColumns(10);
		
		JLabel lblNoawb = new JLabel("No.AWB : ");
		lblNoawb.setForeground(new Color(255, 255, 255));
		lblNoawb.setBounds(267, 12, 70, 15);
		contentPane.add(lblNoawb);
		
		txtAWB = new JTextField();
		txtAWB.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act)
			{
				try
				{
					Connection konek = Koneksi.getKoneksi();
					Statement state = konek.createStatement();
					String query = "SELECT Pengiriman.TglKirim,Pengiriman.Nama,Pengiriman.Alamat,Pengiriman.Pembayaran,Pengiriman.Paket FROM Pengiriman INNER JOIN Pengirim ON Pengiriman.NoPengirim = Pengirim.NoPengirim WHERE NoAWB = '"+txtAWB.getText()+"'";
					ResultSet rs = state.executeQuery(query);
					while(rs.next())
					{
						String tglKirim = rs.getString(1);
						String penerima = rs.getString(2);
						String tujuan = rs.getString(3);
						String pembayaran = rs.getString(4);
						String service = rs.getString(5);
						
						txtTglKirim.setText(tglKirim);
						txtPenerima.setText(penerima);
						txtTujuan.setText(tujuan);
						txtPembayaran.setText(pembayaran);
						txtService.setText(service);
					}
					rs.close();
					state.close();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,"Data tidak ditemukan");
					System.out.println(ex);
				}
				
			}
		});
		txtAWB.setBounds(338, 10, 251, 19);
		contentPane.add(txtAWB);
		txtAWB.setColumns(10);
		
		JLabel lblTglKirim = new JLabel("Tgl Kirim : ");
		lblTglKirim.setForeground(new Color(255, 255, 255));
		lblTglKirim.setBounds(12, 157, 86, 15);
		contentPane.add(lblTglKirim);
		
		txtTglKirim = new JTextField();
		txtTglKirim.setBounds(121, 155, 199, 19);
		contentPane.add(txtTglKirim);
		txtTglKirim.setColumns(10);
		
		JLabel lblPenerima = new JLabel("Penerima : ");
		lblPenerima.setForeground(new Color(255, 255, 255));
		lblPenerima.setBounds(12, 184, 86, 15);
		contentPane.add(lblPenerima);
		
		txtPenerima = new JTextField();
		txtPenerima.setBounds(121, 182, 381, 19);
		contentPane.add(txtPenerima);
		txtPenerima.setColumns(10);
		
		JLabel lblTujuan = new JLabel("Tujuan : ");
		lblTujuan.setForeground(new Color(255, 255, 255));
		lblTujuan.setBounds(12, 211, 70, 15);
		contentPane.add(lblTujuan);
		
		txtTujuan = new JTextField();
		txtTujuan.setBounds(121, 209, 358, 19);
		contentPane.add(txtTujuan);
		txtTujuan.setColumns(10);
		
		JLabel lblPembayaran = new JLabel("Pembayaran : ");
		lblPembayaran.setForeground(new Color(255, 255, 255));
		lblPembayaran.setBounds(12, 238, 113, 15);
		contentPane.add(lblPembayaran);
		
		txtPembayaran = new JTextField();
		txtPembayaran.setBounds(121, 236, 113, 19);
		contentPane.add(txtPembayaran);
		txtPembayaran.setColumns(10);
		
		JLabel lblService = new JLabel("Service : ");
		lblService.setForeground(new Color(255, 255, 255));
		lblService.setBounds(250, 238, 70, 15);
		contentPane.add(lblService);
		
		txtService = new JTextField();
		txtService.setBounds(320, 236, 182, 19);
		contentPane.add(txtService);
		txtService.setColumns(10);
		
		JLabel lblKeterangan = new JLabel("Keterangan : ");
		lblKeterangan.setForeground(new Color(255, 255, 255));
		lblKeterangan.setBounds(12, 265, 99, 15);
		contentPane.add(lblKeterangan);
		
		cbKeterangan = new JComboBox();
		cbKeterangan.setModel(new DefaultComboBoxModel(new String[] {"TERLAMBAT", "RUSAK", "HILANG", "LAIN - LAIN"}));
		cbKeterangan.setBounds(121, 260, 190, 24);
		contentPane.add(cbKeterangan);
		
		JLabel lblKlaimGantiRugi = new JLabel("Klaim Ganti Rugi : ");
		lblKlaimGantiRugi.setForeground(new Color(255, 255, 255));
		lblKlaimGantiRugi.setBounds(12, 301, 137, 15);
		contentPane.add(lblKlaimGantiRugi);
		
		txtGantiRugi = new JTextField();
		txtGantiRugi.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent act) 
			{
				@SuppressWarnings("unused")
				int klaim = Integer.parseInt(txtGantiRugi.getText());
				txtTerbilang.setText("" + new KelasTerbilang(txtGantiRugi.getText()).toString());
				//txtGantiRugi.setText(formatRupiah.format(klaim).substring(1));
			}
		});
		txtGantiRugi.setBounds(151, 299, 169, 19);
		contentPane.add(txtGantiRugi);
		txtGantiRugi.setColumns(10);
		
		JLabel lblTerbilang = new JLabel("Terbilang : ");
		lblTerbilang.setForeground(new Color(255, 255, 255));
		lblTerbilang.setBounds(320, 301, 86, 15);
		contentPane.add(lblTerbilang);
		
		txtTerbilang = new JTextField();
		txtTerbilang.setBounds(416, 299, 463, 19);
		contentPane.add(txtTerbilang);
		txtTerbilang.setColumns(10);
		
		JLabel lblPembayaran_1 = new JLabel("Pembayaran : ");
		lblPembayaran_1.setForeground(new Color(255, 255, 255));
		lblPembayaran_1.setBounds(12, 328, 113, 15);
		contentPane.add(lblPembayaran_1);
		
		cbBayar = new JComboBox();
		cbBayar.setModel(new DefaultComboBoxModel(new String[] {"Cash", "Transfer"}));
		cbBayar.setBounds(151, 323, 182, 24);
		contentPane.add(cbBayar);
		
		JLabel lblNamaBank = new JLabel("Nama Bank : ");
		lblNamaBank.setForeground(new Color(255, 255, 255));
		lblNamaBank.setBounds(151, 361, 99, 15);
		contentPane.add(lblNamaBank);
		
		txtNamaBank = new JTextField();
		txtNamaBank.setBounds(263, 359, 169, 19);
		contentPane.add(txtNamaBank);
		txtNamaBank.setColumns(10);
		
		JLabel lblCabang = new JLabel("Cabang : ");
		lblCabang.setForeground(new Color(255, 255, 255));
		lblCabang.setBounds(151, 386, 70, 15);
		contentPane.add(lblCabang);
		
		txtCabang = new JTextField();
		txtCabang.setBounds(263, 384, 169, 19);
		contentPane.add(txtCabang);
		txtCabang.setColumns(10);
		
		JLabel lblNorek = new JLabel("No.Rek : ");
		lblNorek.setForeground(new Color(255, 255, 255));
		lblNorek.setBounds(151, 417, 70, 15);
		contentPane.add(lblNorek);
		
		txtRekening = new JTextField();
		txtRekening.setBounds(263, 415, 447, 19);
		contentPane.add(txtRekening);
		txtRekening.setColumns(10);
		
		JLabel lblAtasNama = new JLabel("Atas Nama : ");
		lblAtasNama.setForeground(new Color(255, 255, 255));
		lblAtasNama.setBounds(151, 444, 99, 15);
		contentPane.add(lblAtasNama);
		
		txtAtasNama = new JTextField();
		txtAtasNama.setBounds(263, 442, 261, 19);
		contentPane.add(txtAtasNama);
		txtAtasNama.setColumns(10);
		
		btnSimpan = new JButton("Simpan");
		btnSimpan.setIcon(new ImageIcon("src/GambarApp/Simpan.png"));
		btnSimpan.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent act) 
			{
					String ket = "";
					if(cbKeterangan.getSelectedIndex() == 0)
					{
						ket = "TERLAMBAT";
					}
					else if(cbKeterangan.getSelectedIndex() == 1)
					{
						ket = "RUSAK";
					}
					else if(cbKeterangan.getSelectedIndex() == 2)
					{
						ket = "HILANG";
					}
					else if(cbKeterangan.getSelectedIndex() == 3)
					{
						ket = "LAIN - LAIN";
					}
					
					String pembayaran =  "";
						if(cbBayar.getSelectedIndex() == 0)
						{
							pembayaran = "CASH";
						}
						else if(cbBayar.getSelectedIndex() == 1)
						{
							pembayaran = "TRANSFER";
						}
				try
				{
					Connection konek = Koneksi.getKoneksi();
					String query = "INSERT INTO FPK VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement prepare = konek.prepareStatement(query);
					
					prepare.setString(1,txtFPK.getText());
					prepare.setString(2,txtKdPengirim.getText());
					prepare.setString(3,txtKTP.getText());
					prepare.setString(4,txtAWB.getText());
					prepare.setString(5,(String) tglFPK.getSelectedItem());
					prepare.setString(6, ket);
					prepare.setInt(7,Integer.parseInt(txtGantiRugi.getText()));
					prepare.setString(8,pembayaran);
					prepare.setString(9,txtNamaBank.getText());
					prepare.setString(10,txtCabang.getText());
					prepare.setString(11,txtRekening.getText());
					prepare.setString(12,txtAtasNama.getText());
					
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
					cetakFPK();
				}
				
			}
		});
		btnSimpan.setBounds(151, 591, 117, 30);
		contentPane.add(btnSimpan);
		
		JLabel lblKodePengirim = new JLabel("Kd Pengirim : ");
		lblKodePengirim.setForeground(new Color(255, 255, 255));
		lblKodePengirim.setBounds(12, 39, 99, 15);
		contentPane.add(lblKodePengirim);
		
		txtKdPengirim = new JTextField();
		txtKdPengirim.setBounds(121, 37, 145, 19);
		contentPane.add(txtKdPengirim);
		txtKdPengirim.setColumns(10);
		
		tglFPK = new JCalendarCombo(JCalendarCombo.DISPLAY_DATE,false);
		tglFPK.setDateFormat(new SimpleDateFormat("yyyy-MM-dd")); //yyyy-MM-dd
		tglFPK.setBounds(692, 7, 183, 24);
		contentPane.add(tglFPK);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(151, 471, 705, 108);
		contentPane.add(scrollPane);
		
		tabelModel = new DefaultTableModel(null,header);
		tabel = new JTable();
		tabel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabel.setModel(tabelModel);
		scrollPane.setViewportView(tabel);
		
		btnCariAWB = new JButton("");
		btnCariAWB.setIcon(new ImageIcon("src/GambarApp/Cari.png"));
		btnCariAWB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent act)
			{
				new DialogFPK();
				dispose();
			}
		});
		btnCariAWB.setBounds(593, 7, 64, 25);
		contentPane.add(btnCariAWB);
		
		btnCetak = new JButton("Cetak");
		btnCetak.setIcon(new ImageIcon("src/GambarApp/cetak.png"));
		btnCetak.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent act)
			{
				try
	               {
	                   String NamaFile = "src/iReport/FPK.jasper"; //Lokasi File jasper
	                   Class.forName("com.mysql.jdbc.Driver").newInstance();
	                   Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/Pengiriman","root","root");
	                   HashMap hash = new HashMap(2);
	                   //Mengambil parameter dari ireport
	                   hash.put("ParamFPK",txtFPK.getText());
	                   hash.put("Terbilang",txtTerbilang.getText());
	                    
	                   File file = new File(NamaFile);
	                   JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file.getPath());
	                   JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hash,koneksi);
	                   JasperViewer.viewReport(jasperPrint,false);
	               }
	               catch(Exception ex)
	               {
	                   System.out.println(ex);
	               }
			}
		});
		btnCetak.setBounds(331, 591, 117, 30);
		contentPane.add(btnCetak);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("src/GambarApp/buku.png"));
		lblNewLabel.setBounds(654, 132, 137, 140);
		contentPane.add(lblNewLabel);
		
		lblWallFPK = new JLabel("");
		lblWallFPK.setIcon(new ImageIcon("src/GambarApp/wallFPK.jpg"));
		lblWallFPK.setBounds(0, 0, 895, 646);
		contentPane.add(lblWallFPK);
		setLocationRelativeTo(null);
		setVisible(true);
		
		autoNumber();
		setLebarKolom();
	}
	
	@SuppressWarnings("unchecked")
	public void cetakFPK()
	{
		try
        {
            String NamaFile = "src/iReport/FPK.jasper"; //Lokasi File jasper
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/Pengiriman","root","root");
            @SuppressWarnings("rawtypes")
			HashMap hash = new HashMap(2);
            //Mengambil parameter dari ireport
            hash.put("ParamFPK",txtFPK.getText());
            hash.put("Terbilang",txtTerbilang.getText());
             
            File file = new File(NamaFile);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hash,koneksi);
            JasperViewer.viewReport(jasperPrint,false);
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
	
	public void autoNumber()
	{
		try
		{
			Connection konek = Koneksi.getKoneksi();
			Statement state = konek.createStatement();
			String query = "SELECT MAX(right(NoFPK,2)) AS no FROM FPK";
			
			ResultSet rs = state.executeQuery(query);
			while(rs.next())
			{
				if(rs.first() == false)
				{
					txtFPK.setText("FP001");
				}
				else
				{
					rs.last();
					int noFPK = rs.getInt(1) + 1;
					String no = String.valueOf(noFPK);
					int noLong = no.length();
					for(int a=0;a<2-noLong;a++)
					{
						no = "00" + no;
					}
					txtFPK.setText("FP" + no);
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
	
	public void tampilTabel()
	{
		tabelModel.getDataVector().removeAllElements();
		tabelModel.fireTableDataChanged();
		try
		{
			Connection konek = Koneksi.getKoneksi();
			Statement state = konek.createStatement();
			//String query = "SELECT Pengiriman.NoAWB,Pengiriman.TglKirim,Pengiriman.Nama,Pengiriman.Alamat,Pengiriman.Pembayaran,Pengiriman.Paket,FPK.Keterangan,FPK.Klaim,FPK.Pembayaran,FPK.Bank,FPK.Cabang,FPK.NoRekening,FPK.AtasNama FROM Pengiriman INNER JOIN FPK ON FPK.NoAWB = Pengiriman.NoAWB && FPK.NoPengirim = Pengiriman.NoPengirim";
			String query = "SELECT Pengiriman.NoAWB,Pengiriman.TglKirim,Pengiriman.Nama,Pengiriman.Alamat,Pengiriman.Pembayaran,Pengiriman.Paket,FPK.Keterangan,FPK.Klaim,FPK.Pembayaran,FPK.Bank,FPK.Cabang,FPK.NoRekening,FPK.AtasNama FROM Pengiriman INNER JOIN FPK ON FPK.NoAWB = Pengiriman.NoAWB AND FPK.NoPengirim = Pengiriman.NoPengirim WHERE NoFPK = '"+txtFPK.getText()+"'";
			
			ResultSet rs = state.executeQuery(query);
			while(rs.next())
			{
				Object obj[] = new Object[13];
				obj[0] = rs.getString(1);
			    obj[1] = rs.getString(2);
			    obj[2] = rs.getString(3);
			    obj[3] = rs.getString(4);
			    obj[4] = rs.getString(5);
				obj[5] = rs.getString(6);
			    obj[6] = rs.getString(7);
			    obj[7] = rs.getInt(8);
				obj[8] = rs.getString(9);
				obj[9] = rs.getString(10);
				obj[10] = rs.getString(11);
			    obj[11] = rs.getString(12);
			    obj[12] = rs.getString(13);
			    
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
