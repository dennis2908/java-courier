package aplikasiPengiriman;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import org.freixas.jcalendar.JCalendarCombo;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.Color;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.io.File;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.awt.Font;

@SuppressWarnings({ "unused", "serial" })
public class FrmCetakKirimBarang extends JFrame 
{
	 JPanel contentPane;
	 JTextField txtNoPengirim;
	 JLabel lblNama;
	 JTextField txtNamaPengirim;
	 JTextField txtNamaPTPengirim;
	 JTextField txtKotaPengirim;
	 JTextField txtPropinsiPengirim;
	 JTextField txtKdPosPengirim;
	 JTextField txtNegaraPengirim;
	 JTextField txtTelpPengirim;
	 JTextField txtKdKotaPengiriman;
	 JTextField txtKotaPengiriman;
	 JTextField txtPropinsiPengiriman;
	 JTextField txtKdPosPengiriman;
	 JTextField txtNegaraPengiriman;
	 JTextField txtTeleponPengiriman;
	 @SuppressWarnings("rawtypes")
	 JComboBox cbPaket;
	 JTextField txtHarga;
	 JTextField txtPieces;
	 JTextField txtBerat;
	 JTextField txtTotalHarga;
	 JTextField txtPanjang;
	 JTextField txtLebar;
	 JTextField txtTinggi;
	 JCheckBox checkDokumen;
	 JCheckBox checkBarang;
	 JButton btnCariPengirim;
	 JTextArea textAlamatPengirim;
	 JTabbedPane tabbedPane;
	 JTextField txtNoAWB;
	 JTextField txtNamaPenerima;
	 JTextArea textAlamatPenerima;
	 JTextField txtNamaPTPenerima;
	 JCalendarCombo tglKirim;
	 Locale indonesia=new Locale("id", "ID");
	 JLabel lblPembayaran;
	 @SuppressWarnings("rawtypes")
	 JComboBox cbPembayaran;
	 JButton btnSimpan;
	 @SuppressWarnings("rawtypes")
	 JComboBox cbPktTambahan;
	 private JTable tabel;
	 private JTextField txtAsuransi;
	 private JTextField txtHargaPaket;
	 private JTextField txtHargaAsuransi;
	 DefaultTableModel tabelModel;
	 String header[] = {"Pieces","Berat","Panjang","Lebar","Tinggi","Harga","Other Fee","Harga Asuransi","Admin Asuransi","Total Harga"};
	 @SuppressWarnings("rawtypes")
	 private JComboBox cbZonaReguler;
	 @SuppressWarnings("rawtypes")
	 private JComboBox cbZonaOK;
	 NumberFormat formatRupiah = NumberFormat.getCurrencyInstance();
	 private JLabel lblHasilTerbilang;
	 private JLabel lblWallKirim;
	 private JLabel lblBiayaAdminAsuransi;
	 private JTextField txtAdminAsuransi;
	 private JButton btnCetak;
	 private JTextField txtKoli;
	 private JButton btnRefresh;
	 private JTextArea txtNamaBarang;
	 private JScrollPane scrollBarang;
	 private JButton btnCariAWB;
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public FrmCetakKirimBarang()
	{
		setResizable(false);
		setBounds(100, 100, 885, 677);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelPengirim = new JPanel();
		ImageIcon imgPengirim = new ImageIcon("src/GambarApp/tabPengirim.png");
		tabbedPane.addTab("Pengirim", imgPengirim, panelPengirim, "Pengirim");
		panelPengirim.setLayout(null);
		
		JLabel lblNopengirim = new JLabel("No.Pengirim : ");
		lblNopengirim.setForeground(new Color(255, 255, 255));
		lblNopengirim.setBounds(12, 22, 113, 15);
		panelPengirim.add(lblNopengirim);
		
		txtNoPengirim = new JTextField();
		txtNoPengirim.setBounds(120, 20, 114, 19);
		panelPengirim.add(txtNoPengirim);
		txtNoPengirim.setColumns(10);
		
		lblNama = new JLabel("Nama : ");
		lblNama.setForeground(new Color(255, 255, 255));
		lblNama.setBounds(12, 49, 70, 15);
		panelPengirim.add(lblNama);
		
		txtNamaPengirim = new JTextField();
		txtNamaPengirim.setBounds(120, 47, 483, 19);
		panelPengirim.add(txtNamaPengirim);
		txtNamaPengirim.setColumns(10);
		
		JLabel lblAlamat = new JLabel("Alamat : ");
		lblAlamat.setForeground(new Color(255, 255, 255));
		lblAlamat.setBounds(12, 103, 70, 15);
		panelPengirim.add(lblAlamat);
		
		textAlamatPengirim = new JTextArea();
		textAlamatPengirim.setBounds(120, 103, 328, 58);
		panelPengirim.add(textAlamatPengirim);
		
		JLabel lblNamaPt = new JLabel("Nama PT : ");
		lblNamaPt.setForeground(new Color(255, 255, 255));
		lblNamaPt.setBounds(12, 76, 85, 15);
		panelPengirim.add(lblNamaPt);
		
		txtNamaPTPengirim = new JTextField();
		txtNamaPTPengirim.setBounds(119, 74, 181, 19);
		panelPengirim.add(txtNamaPTPengirim);
		txtNamaPTPengirim.setColumns(10);
		
		JLabel lblKota = new JLabel("Kota : ");
		lblKota.setForeground(new Color(255, 255, 255));
		lblKota.setBounds(12, 170, 70, 15);
		panelPengirim.add(lblKota);
		
		txtKotaPengirim = new JTextField();
		txtKotaPengirim.setBounds(119, 168, 307, 19);
		panelPengirim.add(txtKotaPengirim);
		txtKotaPengirim.setColumns(10);
		
		JLabel lblPropinsi = new JLabel("Propinsi : ");
		lblPropinsi.setForeground(new Color(255, 255, 255));
		lblPropinsi.setBounds(12, 197, 85, 15);
		panelPengirim.add(lblPropinsi);
		
		txtPropinsiPengirim = new JTextField();
		txtPropinsiPengirim.setBounds(120, 195, 316, 19);
		panelPengirim.add(txtPropinsiPengirim);
		txtPropinsiPengirim.setColumns(10);
		
		JLabel lblKodePos = new JLabel("Kode Pos : ");
		lblKodePos.setForeground(new Color(255, 255, 255));
		lblKodePos.setBounds(12, 224, 85, 15);
		panelPengirim.add(lblKodePos);
		
		txtKdPosPengirim = new JTextField();
		txtKdPosPengirim.setBounds(119, 222, 145, 19);
		panelPengirim.add(txtKdPosPengirim);
		txtKdPosPengirim.setColumns(10);
		
		JLabel lblNegara = new JLabel("Negara : ");
		lblNegara.setForeground(new Color(255, 255, 255));
		lblNegara.setBounds(12, 251, 70, 15);
		panelPengirim.add(lblNegara);
		
		txtNegaraPengirim = new JTextField();
		txtNegaraPengirim.setBounds(120, 249, 296, 19);
		panelPengirim.add(txtNegaraPengirim);
		txtNegaraPengirim.setColumns(10);
		
		JLabel lblTelp = new JLabel("Telp : ");
		lblTelp.setForeground(new Color(255, 255, 255));
		lblTelp.setBounds(12, 278, 70, 15);
		panelPengirim.add(lblTelp);
		
		txtTelpPengirim = new JTextField();
		txtTelpPengirim.setBounds(120, 276, 255, 19);
		panelPengirim.add(txtTelpPengirim);
		txtTelpPengirim.setColumns(10);
		
		btnSimpan = new JButton("Simpan ");
		btnSimpan.setIcon(new ImageIcon("src/GambarApp/Simpan.png"));
		btnSimpan.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act)
			{
					String pembayaran = "";
						if(cbPembayaran.getSelectedIndex() == 0)
						{
							pembayaran = "Cash";
						}
						else if(cbPembayaran.getSelectedIndex() == 1)
						{
							pembayaran = "COD";
						}
				String Paket = "";
				if(cbPaket.getSelectedIndex() == 1)
				{
					Paket = "Reguler";
				}
				else if(cbPaket.getSelectedIndex() == 2)
				{
					Paket = "OKE";
				}
				else if(cbPaket.getSelectedIndex() == 3)
				{
					Paket = "YES";
				}
				String jenisBarang = "";
				if(checkDokumen.isSelected())
				{
					jenisBarang = "Dokumen";
				}
				else if(checkBarang.isSelected())
				{
					jenisBarang = "Barang";
				}
				try
				{
					Connection konek = Koneksi.getKoneksi();
					String query = "INSERT INTO Pengiriman VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement prepare = konek.prepareStatement(query);
					
					prepare.setString(1,txtNoAWB.getText());
					prepare.setString(2,txtNamaPenerima.getText());
					prepare.setString(3,txtNamaPTPenerima.getText());
					prepare.setString(4,textAlamatPenerima.getText());
					prepare.setString(5,txtNoPengirim.getText());
					prepare.setString(6,txtKdKotaPengiriman.getText());
					prepare.setString(7,txtKdPosPengiriman.getText());
					prepare.setString(8,txtNegaraPengiriman.getText());
					prepare.setString(9,txtTeleponPengiriman.getText());
					prepare.setString(10,Paket);
					prepare.setString(11,(String) tglKirim.getSelectedItem());
					prepare.setInt(12,Integer.parseInt(txtPanjang.getText()));
					prepare.setInt(13,Integer.parseInt(txtLebar.getText()));
					prepare.setInt(14,Integer.parseInt(txtTinggi.getText()));
					prepare.setInt(15,Integer.parseInt(txtHarga.getText()));
					prepare.setString(16,txtNamaBarang.getText());
					prepare.setString(17,jenisBarang);
					prepare.setInt(18,Integer.parseInt(txtKoli.getText()));
					prepare.setInt(19,Integer.parseInt(txtPieces.getText()));
					prepare.setInt(20,Integer.parseInt(txtBerat.getText()));
					prepare.setString(21,pembayaran);
					prepare.setInt(22,Integer.parseInt(txtHargaPaket.getText()));
					prepare.setInt(23,Integer.parseInt(txtHargaAsuransi.getText()));
					prepare.setInt(24,Integer.parseInt(txtAsuransi.getText()));
					prepare.setInt(25,Integer.parseInt(txtAdminAsuransi.getText()));
					
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
					cetakAWB();
					autoNumber();
				}
			}
		});
		btnSimpan.setBounds(120, 500, 133, 37);
		panelPengirim.add(btnSimpan);
		
		btnCariPengirim = new JButton("");
		btnCariPengirim.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act) 
			{
				new DialogPengirim();
				dispose();
			}
		});
		btnCariPengirim.setIcon(new ImageIcon("src/GambarApp/Cari.png"));
		btnCariPengirim.setBounds(246, 16, 70, 27);
		panelPengirim.add(btnCariPengirim);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(120, 315, 712, 173);
		panelPengirim.add(scrollPane);
		
		tabelModel = new DefaultTableModel(null,header);
		tabel = new JTable();
		//tabel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabel.setModel(tabelModel);
		scrollPane.setViewportView(tabel);
		
		btnCetak = new JButton("Cetak");
		btnCetak.setIcon(new ImageIcon("src/GambarApp/cetak.png"));
		btnCetak.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act) 
			{
				String paketReg = "";
				String paketOk = "";
				String paketYes = "";
					if(cbPaket.getSelectedIndex() == 1)
					{
						paketReg = "X";
					}
					else if(cbPaket.getSelectedIndex() == 2)
					{
						paketOk = "X";
					}
					else if(cbPaket.getSelectedIndex() == 3)
					{
						paketYes = "X";
					}
						String bayarCash = "";
						if(cbPembayaran.getSelectedIndex() == 0)
						{
							bayarCash = "X";
						}
						
						String dokumen = "";
						String barang = "";
						if(checkDokumen.isSelected())
						{
							dokumen = "X";
						}
						else if(checkBarang.isSelected())
						{
							barang = "X";
						}
				try
	               {
	                   String NamaFile = "src/iReport/Pengiriman.jasper"; //Lokasi File jasper
	                   Class.forName("com.mysql.jdbc.Driver").newInstance();
	                   Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/Pengiriman","root","root");
	                   HashMap hash = new HashMap();
	                   //Mengambil parameter dari ireport
	                   hash.put("paramAWB",txtNoAWB.getText());
	                   hash.put("paketReg",paketReg);
	                   hash.put("paketOK",paketOk);
	                   hash.put("paketYes",paketYes);
	                   hash.put("cash",bayarCash);
	                   hash.put("dokumen",dokumen);
	                   hash.put("barang",barang);
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
		btnCetak.setBounds(279, 500, 147, 37);
		panelPengirim.add(btnCetak);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setIcon(new ImageIcon("src/GambarApp/Refresh.png"));
		btnRefresh.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act)
			{
				txtNoPengirim.setText("");
				txtNamaPengirim.setText("");
				txtNamaPTPengirim.setText("");
				textAlamatPengirim.setText("");
				txtKotaPengirim.setText("");
				txtPropinsiPengirim.setText("");
				txtNegaraPengirim.setText("");
				txtTelpPengirim.setText("");
				txtKdPosPengirim.setText("");
				txtNoAWB.setText("");
				txtNamaPenerima.setText("");
				txtNamaPTPenerima.setText("");
				textAlamatPenerima.setText("");
				txtKdKotaPengiriman.setText("");
				txtKotaPengiriman.setText("");
				txtPropinsiPengiriman.setText("");
				txtKdPosPengiriman.setText("");
				txtNegaraPengiriman.setText("");
				txtTeleponPengiriman.setText("");
				cbPaket.setSelectedIndex(0);
				cbZonaReguler.setSelectedIndex(0);
				cbZonaOK.setSelectedIndex(0);
				txtPanjang.setText("0");
				txtLebar.setText("0");
				txtTinggi.setText("0");
				txtHarga.setText("");
				txtNamaBarang.setText("");
				txtKoli.setText("");
				txtPieces.setText("");
				txtBerat.setText("");
				txtHargaPaket.setText("0");
				txtHargaAsuransi.setText("0");
				cbPembayaran.setSelectedIndex(0);
				cbPktTambahan.setSelectedIndex(0);
				txtTotalHarga.setText("");
				lblHasilTerbilang.setText("");
				cbPembayaran.setSelectedIndex(0);
				txtAsuransi.setText("0");
				txtAdminAsuransi.setText("0");
				txtNoPengirim.requestFocus();
				hapusTabel();
			}
		});
		btnRefresh.setBounds(462, 500, 141, 37);
		panelPengirim.add(btnRefresh);
		
		JLabel lblIconKlien = new JLabel("");
		lblIconKlien.setIcon(new ImageIcon("src/GambarApp/klien.png"));
		lblIconKlien.setBounds(586, 78, 246, 215);
		panelPengirim.add(lblIconKlien);
		
		lblWallKirim = new JLabel("");
		lblWallKirim.setIcon(new ImageIcon("src/GambarApp/bgKirim.jpg"));
		lblWallKirim.setBounds(0, 0, 878, 613);
		panelPengirim.add(lblWallKirim);
		
		JPanel panelPengiriman = new JPanel();
		ImageIcon imgKirim = new ImageIcon("src/GambarApp/tabPengiriman.png");
		tabbedPane.addTab("Paket Pengiriman", imgKirim, panelPengiriman, "Paket Pengiriman");
		panelPengiriman.setLayout(null);
		
		JLabel lblKode = new JLabel("Kode : ");
		lblKode.setForeground(new Color(255, 255, 255));
		lblKode.setBounds(12, 164, 70, 15);
		panelPengiriman.add(lblKode);
		
		txtKdKotaPengiriman = new JTextField();
		txtKdKotaPengiriman.setBounds(100, 162, 88, 19);
		panelPengiriman.add(txtKdKotaPengiriman);
		txtKdKotaPengiriman.setColumns(10);
		
		JLabel lblKota_1 = new JLabel("Kota : ");
		lblKota_1.setForeground(new Color(255, 255, 255));
		lblKota_1.setBounds(12, 191, 58, 15);
		panelPengiriman.add(lblKota_1);
		
		txtKotaPengiriman = new JTextField();
		txtKotaPengiriman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent act)
			{
				try
				{
					Connection konek = Koneksi.getKoneksi();
					Statement state = konek.createStatement();
					String query = "SELECT Kode,Kotamadya FROM Wilayah WHERE Kotamadya = '"+txtKotaPengiriman.getText()+"'";
					ResultSet rs = state.executeQuery(query);
					while(rs.next())
					{
						String kode = rs.getString(1);
						String propinsi = rs.getString(2);
						
						txtKdKotaPengiriman.setText(kode);
						txtPropinsiPengiriman.setText(propinsi);
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
		txtKotaPengiriman.addKeyListener(new keyTextFieldWilayah(txtKotaPengiriman));
		txtKotaPengiriman.setBounds(101, 189, 229, 19);
		panelPengiriman.add(txtKotaPengiriman);
		txtKotaPengiriman.setColumns(10);
		
		JLabel lblPropinsi_1 = new JLabel("Propinsi : ");
		lblPropinsi_1.setForeground(new Color(255, 255, 255));
		lblPropinsi_1.setBounds(12, 220, 80, 15);
		panelPengiriman.add(lblPropinsi_1);
		
		txtPropinsiPengiriman = new JTextField();
		txtPropinsiPengiriman.setBounds(101, 218, 281, 19);
		panelPengiriman.add(txtPropinsiPengiriman);
		txtPropinsiPengiriman.setColumns(10);
		
		JLabel lblKodePos_1 = new JLabel("Kode Pos : ");
		lblKodePos_1.setForeground(new Color(255, 255, 255));
		lblKodePos_1.setBounds(504, 191, 88, 15);
		panelPengiriman.add(lblKodePos_1);
		
		txtKdPosPengiriman = new JTextField();
		txtKdPosPengiriman.setBounds(610, 189, 153, 19);
		panelPengiriman.add(txtKdPosPengiriman);
		txtKdPosPengiriman.setColumns(10);
		
		JLabel lblNegara_1 = new JLabel("Negara : ");
		lblNegara_1.setForeground(new Color(255, 255, 255));
		lblNegara_1.setBounds(504, 212, 70, 15);
		panelPengiriman.add(lblNegara_1);
		
		txtNegaraPengiriman = new JTextField();
		txtNegaraPengiriman.setBounds(610, 210, 244, 19);
		panelPengiriman.add(txtNegaraPengiriman);
		txtNegaraPengiriman.setColumns(10);
		
		JLabel lblTelepon = new JLabel("Telepon : ");
		lblTelepon.setForeground(new Color(255, 255, 255));
		lblTelepon.setBounds(504, 235, 70, 15);
		panelPengiriman.add(lblTelepon);
		
		txtTeleponPengiriman = new JTextField();
		txtTeleponPengiriman.setBounds(610, 233, 229, 19);
		panelPengiriman.add(txtTeleponPengiriman);
		txtTeleponPengiriman.setColumns(10);
		
		JLabel lblPaket = new JLabel("Paket : ");
		lblPaket.setForeground(new Color(255, 255, 255));
		lblPaket.setBounds(12, 282, 70, 15);
		panelPengiriman.add(lblPaket);
		
		cbPaket = new JComboBox();
		cbPaket.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent act) 
			{
				if(cbPaket.getSelectedIndex() == 0)
				{
					cbZonaReguler.setEnabled(false);
					cbZonaOK.setEnabled(false);
				}
				else if(cbPaket.getSelectedIndex() == 1)
				{
					cbZonaOK.setEnabled(false);
					cbZonaReguler.setEnabled(true);
					cbZonaOK.setSelectedIndex(0);
					cbZonaReguler.addActionListener(new ActionListener() 
					{
						public void actionPerformed(ActionEvent act) 
						{
							if(cbZonaReguler.getSelectedIndex() == 1)
							{
								try
								{
									Connection konek = Koneksi.getKoneksi();
									Statement state = konek.createStatement();
									String query = "SELECT ZonaA FROM RegulerServ WHERE Kode = '"+txtKdKotaPengiriman.getText()+"'";
									ResultSet rs = state.executeQuery(query);
									while(rs.next())
									{
										Object obj[] = new Object[1];
										obj[0] = rs.getInt(1);
										
										txtHarga.setText("" + obj[0]);
									}
									rs.close();
									state.close();
								}
								catch(Exception ex)
								{
									System.out.println(ex);
								}
							}
								else if(cbZonaReguler.getSelectedIndex() == 2)
								{
									try
									{
										Connection konek = Koneksi.getKoneksi();
										Statement state = konek.createStatement();
										String query = "SELECT ZonaB FROM RegulerServ WHERE Kode = '"+txtKdKotaPengiriman.getText()+"'";
										ResultSet rs = state.executeQuery(query);
										while(rs.next())
										{
											Object obj[] = new Object[1];
											obj[0] = rs.getInt(1);
											
											txtHarga.setText("" + obj[0]);
										}
										rs.close();
										state.close();
									}
									catch(Exception ex)
									{
										System.out.println(ex);
									}
							}
							
							if(cbZonaReguler.getSelectedIndex() == 3)
							{
								try
								{
									Connection konek = Koneksi.getKoneksi();
									Statement state = konek.createStatement();
									String query = "SELECT ZonaC FROM RegulerServ WHERE Kode = '"+txtKdKotaPengiriman.getText()+"'";
									ResultSet rs = state.executeQuery(query);
									while(rs.next())
									{
										Object obj[] = new Object[1];
										obj[0] = rs.getInt(1);
										
										txtHarga.setText("" + obj[0]);
									}
									rs.close();
									state.close();
								}
								catch(Exception ex)
								{
									System.out.println(ex);
								}
							}
							
							else if(cbZonaReguler.getSelectedIndex() == 4)
							{
								try
								{
									Connection konek = Koneksi.getKoneksi();
									Statement state = konek.createStatement();
									String query = "SELECT ZonaD FROM RegulerServ WHERE Kode = '"+txtKdKotaPengiriman.getText()+"'";
									ResultSet rs = state.executeQuery(query);
									while(rs.next())
									{
										Object obj[] = new Object[1];
										obj[0] = rs.getInt(1);
										
										txtHarga.setText("" + obj[0]);
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
						}
					);
				}
				else if(cbPaket.getSelectedIndex() == 2)
				{
					cbZonaOK.setEnabled(true);
					cbZonaReguler.setEnabled(false);
					cbZonaReguler.setSelectedIndex(0);
					
					cbZonaOK.addActionListener(new ActionListener() 
					{
						public void actionPerformed(ActionEvent e) 
						{
							if(cbZonaOK.getSelectedIndex() == 1)
							{
								try
								{
									Connection konek = Koneksi.getKoneksi();
									Statement state = konek.createStatement();
									String query = "SELECT ZonaA FROM OKServ WHERE Kode = '"+txtKdKotaPengiriman.getText()+"'";
									ResultSet rs = state.executeQuery(query);
									while(rs.next())
									{
										Object obj[] = new Object[1];
										obj[0] = rs.getInt(1);
										
										txtHarga.setText("" + obj[0]);
									}
									rs.close();
									state.close();
								}
								catch(Exception ex)
								{
									System.out.println(ex);
								}
							}
							else if(cbZonaOK.getSelectedIndex() == 2)
							{
								try
								{
									Connection konek = Koneksi.getKoneksi();
									Statement state = konek.createStatement();
									String query = "SELECT ZonaB FROM OKServ WHERE Kode = '"+txtKdKotaPengiriman.getText()+"'";
									ResultSet rs = state.executeQuery(query);
									while(rs.next())
									{
										Object obj[] = new Object[1];
										obj[0] = rs.getInt(1);
										
										txtHarga.setText("" + obj[0]);
									}
									rs.close();
									state.close();
								}
								catch(Exception ex)
								{
									System.out.println(ex);
								}
							}
							
							else if(cbZonaOK.getSelectedIndex() == 3)
							{
								try
								{
									Connection konek = Koneksi.getKoneksi();
									Statement state = konek.createStatement();
									String query = "SELECT ZonaC FROM OKServ WHERE Kode = '"+txtKdKotaPengiriman.getText()+"'";
									ResultSet rs = state.executeQuery(query);
									while(rs.next())
									{
										Object obj[] = new Object[1];
										obj[0] = rs.getInt(1);
										
										txtHarga.setText("" + obj[0]);
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
						
					});
				}
				else if(cbPaket.getSelectedIndex() == 3)
				{
					cbZonaOK.setEnabled(false);
					cbZonaReguler.setEnabled(false);
					cbZonaReguler.setSelectedIndex(0);
					cbZonaOK.setSelectedIndex(0);
					try
					{
						Connection konek = Koneksi.getKoneksi();
						Statement state = konek.createStatement();
						String query = "SELECT TarifYes FROM YesServ WHERE Kode = '"+txtKdKotaPengiriman.getText()+"'";
						ResultSet rs = state.executeQuery(query);
						while(rs.next())
						{
							Object obj[] = new Object[1];
							obj[0] = rs.getInt(1);
							
							txtHarga.setText("" + obj[0]);
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
		});
		cbPaket.setModel(new DefaultComboBoxModel(new String[] {"=Pilih Paket=", "Reguler", "OKE", "YES"}));
		cbPaket.setBounds(77, 277, 135, 24);
		panelPengiriman.add(cbPaket);
		
		JLabel lblReguler = new JLabel("Reguler");
		lblReguler.setForeground(new Color(255, 255, 255));
		lblReguler.setBounds(245, 262, 70, 15);
		panelPengiriman.add(lblReguler);
		
		JLabel lblOke = new JLabel("OKE");
		lblOke.setForeground(new Color(255, 255, 255));
		lblOke.setBounds(357, 262, 44, 15);
		panelPengiriman.add(lblOke);
		
		JLabel lblHarga = new JLabel("Harga : ");
		lblHarga.setForeground(new Color(255, 255, 255));
		lblHarga.setBounds(12, 318, 70, 15);
		panelPengiriman.add(lblHarga);
		
		txtHarga = new JTextField();
		txtHarga.setBounds(128, 316, 202, 19);
		panelPengiriman.add(txtHarga);
		txtHarga.setColumns(10);
		
		JLabel lblNamaBarang = new JLabel("Isi Kiriman : ");
		lblNamaBarang.setForeground(new Color(255, 255, 255));
		lblNamaBarang.setBounds(12, 347, 108, 15);
		panelPengiriman.add(lblNamaBarang);
		
		JLabel lblPieces = new JLabel("Pieces : ");
		lblPieces.setForeground(new Color(255, 255, 255));
		lblPieces.setBounds(12, 434, 70, 15);
		panelPengiriman.add(lblPieces);
		
		txtPieces = new JTextField();
		txtPieces.setBounds(128, 432, 114, 19);
		panelPengiriman.add(txtPieces);
		txtPieces.setColumns(10);
		
		JLabel lblBerat = new JLabel("Berat : ");
		lblBerat.setForeground(new Color(255, 255, 255));
		lblBerat.setBounds(12, 461, 70, 15);
		panelPengiriman.add(lblBerat);
		
		txtBerat = new JTextField();
		txtBerat.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent act) 
			{
				int hargaPaket = Integer.parseInt(txtHarga.getText());
				int beratBarang = Integer.parseInt(txtBerat.getText());
				
				int hargaBarang = hargaPaket * beratBarang;
				txtTotalHarga.setText(formatRupiah.format(hargaBarang).substring(2));
				lblHasilTerbilang.setText("" + new KelasTerbilang(hargaBarang).toString());
			}
		});
		
		txtBerat.setBounds(128, 459, 114, 19);
		panelPengiriman.add(txtBerat);
		txtBerat.setColumns(10);
		
		JLabel lblKgs = new JLabel("Kgs");
		lblKgs.setForeground(new Color(255, 255, 255));
		lblKgs.setBounds(260, 461, 58, 15);
		panelPengiriman.add(lblKgs);
		
		JLabel lblTotalHarga = new JLabel("Ongkos Kirim : ");
		lblTotalHarga.setForeground(new Color(255, 255, 255));
		lblTotalHarga.setBounds(12, 524, 114, 15);
		panelPengiriman.add(lblTotalHarga);
		
		txtTotalHarga = new JTextField();
		txtTotalHarga.setBounds(128, 522, 270, 19);
		panelPengiriman.add(txtTotalHarga);
		txtTotalHarga.setColumns(10);
		
		JLabel lblPanjang = new JLabel("Panjang : ");
		lblPanjang.setForeground(new Color(255, 255, 255));
		lblPanjang.setBounds(567, 264, 88, 15);
		panelPengiriman.add(lblPanjang);
		
		txtPanjang = new JTextField();
		txtPanjang.setBounds(673, 260, 114, 19);
		panelPengiriman.add(txtPanjang);
		txtPanjang.setColumns(10);
		
		JLabel lblLebar = new JLabel("Lebar : ");
		lblLebar.setForeground(new Color(255, 255, 255));
		lblLebar.setBounds(567, 291, 70, 15);
		panelPengiriman.add(lblLebar);
		
		txtLebar = new JTextField();
		txtLebar.setBounds(673, 289, 114, 19);
		panelPengiriman.add(txtLebar);
		txtLebar.setColumns(10);
		
		JLabel lblTinggi = new JLabel("Tinggi : ");
		lblTinggi.setForeground(new Color(255, 255, 255));
		lblTinggi.setBounds(567, 318, 70, 15);
		panelPengiriman.add(lblTinggi);
		
		txtTinggi = new JTextField();
		txtTinggi.setBounds(673, 316, 114, 19);
		panelPengiriman.add(txtTinggi);
		txtTinggi.setColumns(10);
		
		JLabel lblJenis = new JLabel("Jenis : ");
		lblJenis.setForeground(new Color(255, 255, 255));
		lblJenis.setBounds(567, 345, 70, 15);
		panelPengiriman.add(lblJenis);
		
		checkDokumen = new JCheckBox("Dokumen");
		checkDokumen.setBounds(673, 343, 129, 23);
		panelPengiriman.add(checkDokumen);
		
		checkBarang = new JCheckBox("Barang");
		checkBarang.setBounds(673, 376, 129, 23);
		panelPengiriman.add(checkBarang);
		
		JLabel lblNoAwb = new JLabel("No AWB : ");
		lblNoAwb.setForeground(new Color(255, 255, 255));
		lblNoAwb.setBounds(12, 12, 70, 15);
		panelPengiriman.add(lblNoAwb);
		
		txtNoAWB = new JTextField();
		txtNoAWB.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act)
			{
				try
				{
					Connection konek = Koneksi.getKoneksi();
					Statement state = konek.createStatement();
					String query = "SELECT Pengirim.NoPengirim,Pengirim.Nama,Pengirim.NamaPT,Pengirim.Alamat,Pengirim.Kota,Pengirim.Propinsi,Pengirim.KodePos,Pengirim.Negara,Pengirim.Telepon,Pengiriman.Nama,Pengiriman.TglKirim,Pengiriman.NamaPT,Pengiriman.Alamat,Pengiriman.Kode,Wilayah.Kotamadya,Wilayah.Propinsi,Pengiriman.KodePos,Pengiriman.Negara,Pengiriman.Telepon,Pengiriman.Paket,Pengiriman.Panjang,Pengiriman.Lebar,Pengiriman.Tinggi,Pengiriman.Jenis,Pengiriman.Koli,Pengiriman.Harga,Pengiriman.NamaBarang,Pengiriman.Pieces,Pengiriman.Berat,Pengiriman.Packaging,Pengiriman.totalAsuransi,Pengiriman.Asuransi,Pengiriman.AdminAsuransi FROM Pengiriman INNER JOIN Pengirim ON Pengirim.NoPengirim = Pengiriman.NoPengirim INNER JOIN Wilayah ON Wilayah.Kode = Pengiriman.Kode WHERE NoAWB = '"+txtNoAWB.getText()+"'";
					ResultSet rs = state.executeQuery(query);
					if(rs.next() == true)
					{
						//Pengirim
						String noPengirim = rs.getString(1);
						String nama = rs.getString(2);
						String namaPT = rs.getString(3);
						String alamat = rs.getString(4);
						String kota = rs.getString(5);
						String propinsi = rs.getString(6);
						int kodepos = rs.getInt(7);
						String negara = rs.getString(8);
						String telp = rs.getString(9);
						
						//Pengiriman
						String namaPengiriman = rs.getString(10);
						String tglKirim = rs.getString(11);
						String namaPTPengiriman = rs.getString(12);
						String alamatPengiriman = rs.getString(13);
						String kode = rs.getString(14);
						String kotaPengiriman = rs.getString(15);//1
						String propinsiPengiriman = rs.getString(16);
						String kodePosPengiriman = rs.getString(17);
						String negaraPengiriman = rs.getString(18);
						String teleponPengiriman = rs.getString(19);
						String paket = rs.getString(20);
						int panjang = rs.getInt(21);//
						int lebar = rs.getInt(22);
						int tinggi = rs.getInt(23);
						String jenis = rs.getString(24);
						int koli = rs.getInt(25);
						int harga = rs.getInt(26);
						String namaBarang = rs.getString(27);
						int pieces = rs.getInt(28);//
						int berat = rs.getInt(29);
						int packaging = rs.getInt(30);
						int totalAsuransi = rs.getInt(31);
						int asuransi = rs.getInt(32);
						int adminAsuransi = rs.getInt(33);
						
						//Pengirim
						txtNamaPengirim.setText(nama);
						txtNamaPTPengirim.setText(namaPT);
						textAlamatPengirim.setText(alamat);
						txtKotaPengirim.setText(kota);
						txtPropinsiPengirim.setText(propinsi);
						txtKdPosPengirim.setText("" + kodepos);
						txtNegaraPengirim.setText(negara);
						txtTelpPengirim.setText(telp);
						
						//Pengiriman
						txtNoPengirim.setText(noPengirim);
						txtNamaPenerima.setText(namaPengiriman);
						FrmCetakKirimBarang.this.tglKirim.setSelectedItem(tglKirim);
						txtNamaPTPenerima.setText(namaPTPengiriman);
						textAlamatPenerima.setText(alamatPengiriman);
						txtKdKotaPengiriman.setText(kode);
						txtKotaPengiriman.setText(kotaPengiriman);
						txtPropinsiPengiriman.setText(propinsiPengiriman);
						txtKdPosPengiriman.setText(kodePosPengiriman);
						txtNegaraPengiriman.setText(negaraPengiriman);
						txtTeleponPengiriman.setText(teleponPengiriman);
						cbPaket.setSelectedItem(paket);
						txtPanjang.setText("" + panjang);
						txtLebar.setText("" + lebar);
						txtTinggi.setText("" + tinggi);
						if(jenis.equals("Barang"))
						{
							checkBarang.setSelected(true);
						}
						else if(jenis.equals("Dokumen"))
						{
							checkDokumen.setSelected(true);
						}
						txtKoli.setText("" + koli);
						txtHarga.setText("" + harga);
						txtNamaBarang.setText(namaBarang);
						txtPieces.setText("" + pieces);
						txtBerat.setText("" + berat);
						txtAsuransi.setText("" + asuransi);
						txtHargaPaket.setText("" + packaging);
						txtHargaAsuransi.setText("" + totalAsuransi);
						txtAdminAsuransi.setText("" + adminAsuransi);
						int totalHarga = (harga * berat) + totalAsuransi + packaging;
						txtTotalHarga.setText(formatRupiah.format(totalHarga).substring(2));
						lblHasilTerbilang.setText("" + new KelasTerbilang(totalHarga).toString());
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
		txtNoAWB.setBounds(100, 10, 270, 19);
		panelPengiriman.add(txtNoAWB);
		txtNoAWB.setColumns(10);
		
		JLabel lblNama_1 = new JLabel("Nama : ");
		lblNama_1.setForeground(new Color(255, 255, 255));
		lblNama_1.setBounds(12, 49, 70, 15);
		panelPengiriman.add(lblNama_1);
		
		txtNamaPenerima = new JTextField();
		txtNamaPenerima.setBounds(100, 47, 364, 19);
		panelPengiriman.add(txtNamaPenerima);
		txtNamaPenerima.setColumns(10);
		
		JLabel lblAlamat_1 = new JLabel("Alamat : ");
		lblAlamat_1.setForeground(new Color(255, 255, 255));
		lblAlamat_1.setBounds(12, 117, 70, 15);
		panelPengiriman.add(lblAlamat_1);
		
		textAlamatPenerima = new JTextArea();
		textAlamatPenerima.setBounds(100, 103, 286, 47);
		panelPengiriman.add(textAlamatPenerima);
		
		JLabel lblNamaPt_1 = new JLabel("Nama PT : ");
		lblNamaPt_1.setForeground(new Color(255, 255, 255));
		lblNamaPt_1.setBounds(12, 76, 80, 15);
		panelPengiriman.add(lblNamaPt_1);
		
		txtNamaPTPenerima = new JTextField();
		txtNamaPTPenerima.setBounds(100, 78, 398, 19);
		panelPengiriman.add(txtNamaPTPenerima);
		txtNamaPTPenerima.setColumns(10);
		
		tglKirim = new JCalendarCombo(JCalendarCombo.DISPLAY_DATE,false);
		tglKirim.setDateFormat(new SimpleDateFormat("yyyy-MM-dd")); //yyyy-MM-dd
		tglKirim.setBounds(619, 7, 221, 24);
		panelPengiriman.add(tglKirim);
		
		JLabel lblTanggalKirim = new JLabel("Tanggal Kirim : ");
		lblTanggalKirim.setForeground(new Color(255, 255, 255));
		lblTanggalKirim.setBounds(487, 12, 114, 15);
		panelPengiriman.add(lblTanggalKirim);
		
		lblPembayaran = new JLabel("Pembayaran : ");
		lblPembayaran.setForeground(new Color(255, 255, 255));
		lblPembayaran.setBounds(567, 459, 102, 15);
		panelPengiriman.add(lblPembayaran);
		
		cbPembayaran = new JComboBox();
		cbPembayaran.setModel(new DefaultComboBoxModel(new String[] {"Cash ", "COD"}));
		cbPembayaran.setBounds(673, 452, 124, 24);
		panelPengiriman.add(cbPembayaran);
		
		JLabel lblPaketTambahan = new JLabel("Paket Tambah : ");
		lblPaketTambahan.setForeground(new Color(255, 255, 255));
		lblPaketTambahan.setBounds(12, 488, 114, 15);
		panelPengiriman.add(lblPaketTambahan);
		
		cbPktTambahan = new JComboBox();
		cbPktTambahan.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent act) 
			{
				if(cbPktTambahan.getSelectedIndex() == 1)
				{
					int panjang = Integer.parseInt(txtPanjang.getText());
					int lebar = Integer.parseInt(txtLebar.getText());
					int tinggi = Integer.parseInt(txtTinggi.getText());
					int harga = Integer.parseInt(txtHarga.getText());
					int berat = Integer.parseInt(txtBerat.getText()) * 2;
					
					int ukuran = (panjang + 12) * (lebar + 12) * (tinggi + 12) * harga / 6000;
					txtHargaPaket.setText("" + ukuran);
					//txtHargaPaket.setText(formatRupiah.format(ukuran).substring(1));
					int beratHarga = Integer.parseInt(txtHarga.getText()) * berat;
					txtBerat.setText("" + berat);
					
					int total = beratHarga + ukuran;
					txtTotalHarga.setText(formatRupiah.format(total).substring(2));
					lblHasilTerbilang.setText("" + new KelasTerbilang(total).toString());
				}
				
				else if(cbPktTambahan.getSelectedIndex() == 2)
				{
					int asuransi = Integer.parseInt(txtAsuransi.getText());
					int harga = Integer.parseInt(txtHarga.getText()) * Integer.parseInt(txtBerat.getText());
					int adminAsuransi = Integer.parseInt(txtAdminAsuransi.getText());
					
					int hargaAsuransi = (int) ((asuransi * 0.002) + adminAsuransi);
					int totalAsuransi = hargaAsuransi + harga;
					
					txtHargaAsuransi.setText("" + hargaAsuransi);
					txtTotalHarga.setText(formatRupiah.format(totalAsuransi).substring(2));
					lblHasilTerbilang.setText("" + new KelasTerbilang(totalAsuransi).toString());
				}
				
				else if(cbPktTambahan.getSelectedIndex() == 3)
				{
					int panjang = Integer.parseInt(txtPanjang.getText());
					int lebar = Integer.parseInt(txtLebar.getText());
					int tinggi = Integer.parseInt(txtTinggi.getText());
					int harga = Integer.parseInt(txtHarga.getText());
					int berat = Integer.parseInt(txtBerat.getText()) * 2;
					int adminAsuransi = Integer.parseInt(txtAdminAsuransi.getText());
					
					int ukuran = (panjang + 12) * (lebar + 12) * (tinggi + 12) * harga / 6000;
					txtHargaPaket.setText("" + ukuran);
					txtBerat.setText("" + berat);
					int beratHarga = Integer.parseInt(txtHarga.getText()) * Integer.parseInt(txtBerat.getText());
					
					int total = beratHarga + ukuran;
				
					int asuransi = Integer.parseInt(txtAsuransi.getText());
					int Kaliharga = Integer.parseInt(txtHarga.getText()) * Integer.parseInt(txtBerat.getText());
					
					int hargaAsuransi = (int) ((asuransi * 0.002) + adminAsuransi);
					int totalAsuransi = hargaAsuransi;
					
					int amount = total + totalAsuransi;
					
					txtHargaAsuransi.setText("" + hargaAsuransi);
					//Math.floor(Double.parseDouble(txtTotalHarga.getText()));
					txtTotalHarga.setText(formatRupiah.format(amount).substring(2));
					lblHasilTerbilang.setText("" + new KelasTerbilang(amount).toString());
				}
			}
		});
		cbPktTambahan.setModel(new DefaultComboBoxModel(new String[] {"==Pilih Paket==", "Packaging", "Asuransi", "Packaging + Asuransi"}));
		cbPktTambahan.setBounds(128, 486, 169, 24);
		panelPengiriman.add(cbPktTambahan);
		
		JLabel lblHargaBarangAsuransi = new JLabel("Harga Barang");
		lblHargaBarangAsuransi.setForeground(new Color(255, 255, 255));
		lblHargaBarangAsuransi.setBounds(671, 507, 102, 15);
		panelPengiriman.add(lblHargaBarangAsuransi);
		
		JLabel lblAsuransi = new JLabel("Asuransi");
		lblAsuransi.setForeground(new Color(255, 255, 255));
		lblAsuransi.setBounds(673, 486, 70, 15);
		panelPengiriman.add(lblAsuransi);
		
		txtAsuransi = new JTextField();
		txtAsuransi.setBounds(673, 534, 180, 19);
		panelPengiriman.add(txtAsuransi);
		txtAsuransi.setColumns(10);
		
		JLabel lblBiayaPackaging = new JLabel("Paket");
		lblBiayaPackaging.setForeground(new Color(255, 255, 255));
		lblBiayaPackaging.setBounds(312, 436, 44, 15);
		panelPengiriman.add(lblBiayaPackaging);
		
		txtHargaPaket = new JTextField();
		txtHargaPaket.setEditable(false);
		txtHargaPaket.setBounds(387, 436, 153, 19);
		panelPengiriman.add(txtHargaPaket);
		txtHargaPaket.setColumns(10);
		
		JLabel lblAsuransi_1 = new JLabel("Asuransi");
		lblAsuransi_1.setForeground(new Color(255, 255, 255));
		lblAsuransi_1.setBounds(312, 461, 70, 15);
		panelPengiriman.add(lblAsuransi_1);
		
		txtHargaAsuransi = new JTextField();
		txtHargaAsuransi.setEditable(false);
		txtHargaAsuransi.setBounds(387, 461, 153, 19);
		panelPengiriman.add(txtHargaAsuransi);
		txtHargaAsuransi.setColumns(10);
		
		cbZonaReguler = new JComboBox();
		cbZonaReguler.setModel(new DefaultComboBoxModel(new String[] {"=Zona=", "Zona A", "Zona B", "Zona C", "Zona D"}));
		cbZonaReguler.setBounds(225, 277, 88, 24);
		panelPengiriman.add(cbZonaReguler);
		
		cbZonaOK = new JComboBox();
		cbZonaOK.setModel(new DefaultComboBoxModel(new String[] {"=Zona=", "Zona A", "Zona B", "Zona C"}));
		cbZonaOK.setBounds(340, 277, 88, 24);
		panelPengiriman.add(cbZonaOK);
		
		JLabel lblTerbilang = new JLabel("Terbilang : ");
		lblTerbilang.setForeground(new Color(255, 255, 255));
		lblTerbilang.setBounds(12, 564, 80, 15);
		panelPengiriman.add(lblTerbilang);
		
		lblHasilTerbilang = new JLabel("");
		lblHasilTerbilang.setForeground(new Color(255, 255, 255));
		lblHasilTerbilang.setBounds(128, 564, 529, 15);
		panelPengiriman.add(lblHasilTerbilang);
		
		lblBiayaAdminAsuransi = new JLabel("Biaya Admin Asuransi");
		lblBiayaAdminAsuransi.setForeground(new Color(255, 255, 255));
		lblBiayaAdminAsuransi.setBounds(673, 564, 206, 15);
		panelPengiriman.add(lblBiayaAdminAsuransi);
		
		txtAdminAsuransi = new JTextField();
		txtAdminAsuransi.setBounds(673, 582, 180, 19);
		panelPengiriman.add(txtAdminAsuransi);
		txtAdminAsuransi.setColumns(10);
		
		JLabel lblKoli = new JLabel("Koli : ");
		lblKoli.setForeground(new Color(255, 255, 255));
		lblKoli.setBounds(567, 423, 70, 15);
		panelPengiriman.add(lblKoli);
		
		txtKoli = new JTextField();
		txtKoli.setBounds(673, 421, 114, 19);
		panelPengiriman.add(txtKoli);
		txtKoli.setColumns(10);
		
		JLabel lblAutocomplete = new JLabel("* AutoComplete");
		lblAutocomplete.setForeground(new Color(255, 255, 255));
		lblAutocomplete.setFont(new Font("Dialog", Font.BOLD, 10));
		lblAutocomplete.setBounds(340, 191, 102, 15);
		panelPengiriman.add(lblAutocomplete);
		
		scrollBarang = new JScrollPane();
		scrollBarang.setBounds(128, 343, 228, 84);
		panelPengiriman.add(scrollBarang);
		
		txtNamaBarang = new JTextArea();
		scrollBarang.setViewportView(txtNamaBarang);
		
		btnCariAWB = new JButton("");
		btnCariAWB.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act)
			{
				new DialogCariAWB();
				dispose();
			}
		});
		btnCariAWB.setIcon(new ImageIcon("src/GambarApp/Cari.png"));
		btnCariAWB.setBounds(382, 7, 50, 25);
		panelPengiriman.add(btnCariAWB);
		
		JLabel lblCm = new JLabel("cm");
		lblCm.setForeground(new Color(255, 255, 255));
		lblCm.setBounds(796, 262, 60, 15);
		panelPengiriman.add(lblCm);
		
		JLabel lblCm_1 = new JLabel("cm");
		lblCm_1.setForeground(Color.WHITE);
		lblCm_1.setBounds(796, 291, 57, 15);
		panelPengiriman.add(lblCm_1);
		
		JLabel lblCm_2 = new JLabel("cm");
		lblCm_2.setForeground(Color.WHITE);
		lblCm_2.setBounds(796, 318, 57, 15);
		panelPengiriman.add(lblCm_2);
		
		JLabel lblWallKirim2 = new JLabel("");
		lblWallKirim2.setIcon(new ImageIcon("src/GambarApp/bgKirim.jpg"));
		lblWallKirim2.setBounds(0, 0, 866, 613);
		panelPengiriman.add(lblWallKirim2);
		setVisible(true);
		setLocationRelativeTo(null);
		setLebarKolom();
		autoNumber();
		txtPanjang.setText("0");
		txtLebar.setText("0");
		txtTinggi.setText("0");
		txtHargaPaket.setText("0");
		txtHargaAsuransi.setText("0");
		txtAsuransi.setText("0");
		txtAdminAsuransi.setText("0");
	}
	
	@SuppressWarnings("unchecked")
	public void cetakAWB()
	{
		String paketReg = "";
		String paketOk = "";
		String paketYes = "";
			if(cbPaket.getSelectedIndex() == 1)
			{
				paketReg = "X";
			}
			else if(cbPaket.getSelectedIndex() == 2)
			{
				paketOk = "X";
			}
			else if(cbPaket.getSelectedIndex() == 3)
			{
				paketYes = "X";
			}
				String bayarCash = "";
				if(cbPembayaran.getSelectedIndex() == 0)
				{
					bayarCash = "X";
				}
				
				String dokumen = "";
				String barang = "";
				if(checkDokumen.isSelected())
				{
					dokumen = "X";
				}
				else if(checkBarang.isSelected())
				{
					barang = "X";
				}
		try
           {
               String NamaFile = "src/iReport/Pengiriman.jasper"; //Lokasi File jasper
               Class.forName("com.mysql.jdbc.Driver").newInstance();
               Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/Pengiriman","root","root");
               @SuppressWarnings("rawtypes")
               HashMap hash = new HashMap();
               //Mengambil parameter dari ireport
               hash.put("paramAWB",txtNoAWB.getText());
               hash.put("paketReg",paketReg);
               hash.put("paketOK",paketOk);
               hash.put("paketYes",paketYes);
               hash.put("cash",bayarCash);
               hash.put("dokumen",dokumen);
               hash.put("barang",barang);
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
	
	public void tampilTabel()
	{
		tabelModel.getDataVector().removeAllElements();
		tabelModel.fireTableDataChanged();
		try
		{
			Connection konek = Koneksi.getKoneksi();
			Statement state = konek.createStatement();
			String query = "SELECT Pengiriman.Pieces,Pengiriman.Berat,Pengiriman.Panjang,Pengiriman.Lebar,Pengiriman.Tinggi,Pengiriman.Harga,Pengiriman.Packaging,Pengiriman.totalAsuransi,Pengiriman.AdminAsuransi FROM Pengiriman WHERE NoAWB = '"+txtNoAWB.getText()+"'";
			
			ResultSet rs = state.executeQuery(query);
			while(rs.next())
			{	
				Object obj[] = new Object[10];
			    obj[0] = rs.getInt(1);
			    obj[1] = rs.getInt(2);
			    obj[2] = rs.getInt(3);
			    obj[3] = rs.getInt(4);
				obj[4] = rs.getInt(5);
			    obj[5] = rs.getInt(6);
			    obj[6] = rs.getInt(7);
			    obj[7] = rs.getInt(8);
			    obj[8] = rs.getInt(9);
			    int kolom2 = rs.getInt(2);
				int kolom6 = rs.getInt(6);
				int kolom7 = rs.getInt(7);
				int kolom8 = rs.getInt(8);
				int kolom9 = rs.getInt(9);
				int total = (kolom2 * kolom6) + kolom7 + kolom8; 
				obj[9] = formatRupiah.format(total).substring(2);
			    
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
	
	public void hapusTabel()
	{
		int a = tabel.getRowCount();
		int b = 0;
		for(a = 0; a<b;a++)
		{
			tabelModel.removeRow(0);
		}
	}
	
	public void autoNumber()
	{
		try
		{
			Connection konek = Koneksi.getKoneksi();
			Statement state = konek.createStatement();
			String query = "SELECT MAX(right(NoAWB,1)) AS no FROM Pengiriman";
			
			ResultSet rs = state.executeQuery(query);
			while(rs.next())
			{
				if(rs.first() == false)
				{
					txtNoAWB.setText("22284788478001");
				}
				else
				{
					rs.last();
					int noAWB = rs.getInt(1) + 1;
					String no = String.valueOf(noAWB);
					int noLong = no.length();
					for(int a=0;a<2-noLong;a++)
					{
						no = "" + no;
					}
					txtNoAWB.setText("222000366000" + no);
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
