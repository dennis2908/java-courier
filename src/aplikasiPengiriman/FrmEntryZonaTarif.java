package aplikasiPengiriman;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

@SuppressWarnings("serial")
public class FrmEntryZonaTarif extends JFrame
{
	JPanel contentPane;
	JTextField txtKodeReg;
	public JTextField txtKotamadyaReg;
    JTextField txtPropinsiReg;
	JTextField txtZonaAReg;
	JTextField txtZonaBReg;
	JTextField txtZonaCReg;
	JTextField txtZonaDReg;
	JButton btnSimpanReg;
	JButton btnUpdateReg;
	JButton btnHapusReg;
	DefaultTableModel tabelModelReg;
	DefaultTableModel tabelModelOK;
	DefaultTableModel tabelModelYes;
	DefaultTableModel tabelModelSS;
	String headerReguler[] = {"Kode","Kotamadya","Propinsi","Zona A","Zona B","Zona C","Zona D"};
	String headerOK[] = {"Kode","Kotamadya","Propinsi","Zona A","Zona B","Zona C"};
	String headerYes[] = {"Kode","Kotamadya","Propinsi","Tarif Yes"};
	String headerSS[] = {"Kode","Kotamadya","Propinsi","Tarif Super Speed","Tarif Next"};
	 JButton btnCariReg;
	 JTextField txtCariTarifReg;
	 JLabel lblCariKota;
	 JTextField txtKodeOK;
	 JTextField txtKotamadyaOK;
	 JTextField txtPropinsiOK;
	 JTextField txtZonaAOK;
	 JTextField txtZonaBOK;
	 JTextField txtZonaCOK;
	 JButton btnSimpanOK;
	 JButton btnUbahOK;
	 JButton btnHapusOK;
	 JButton btnCariOK;
	 JPanel panelYes;
	 JLabel lblKode_2;
	 JTextField txtKodeYes;
	 JLabel lblKotamadya_2;
	 JTextField txtKotamadyaYes;
	 JLabel lblPropinsi_2;
	 JTextField txtPropinsiYes;
	 JLabel lblTarif;
	 JTextField txtTarifYes;
	 JButton btnSimpanYes;
	 JButton btnUbahYes;
	 JButton btnHapusYes;
	 JButton btnCariYes;
	 JPanel panelReg;
	 JPanel panelOK;
	 JTabbedPane tabbedPane;
	 JTextField txtCariTarifOK;
	 JTextField txtCariTarifYes;
	 JLabel lblWallReg;
	 JLabel lblWallOK;
	 JLabel lblCariBerdasarkanKota;
	 JLabel lblCariBerdasarkanKota_1;
	 JLabel lblWallYes;
	 private JButton btnRefreshReguler;
	 private JButton btnRefreshOK;
	 private JButton btnRefreshYes;
	/**
	 * Create the frame.
	 */
	public FrmEntryZonaTarif() 
	{
		super("Zona Tarif");
		setResizable(false);
		setBounds(100, 100, 658, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setVisible(true);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		panelReg = new JPanel();
		ImageIcon iconReg = new ImageIcon("src/GambarApp/tabReg.png");
		tabbedPane.addTab("Reguler", iconReg, panelReg, "Paket Reguler");
		panelReg.setLayout(null);
		
		JLabel lblKode = new JLabel("Kode : ");
		lblKode.setForeground(new Color(255, 255, 255));
		lblKode.setBounds(12, 12, 70, 18);
		panelReg.add(lblKode);
		
		txtKodeReg = new JTextField();
		txtKodeReg.setBounds(126, 10, 134, 22);
		panelReg.add(txtKodeReg);
		txtKodeReg.setColumns(10);
		
		JLabel lblKotamadya = new JLabel("Kotamadya : ");
		lblKotamadya.setForeground(new Color(255, 255, 255));
		lblKotamadya.setBounds(12, 53, 103, 18);
		panelReg.add(lblKotamadya);
		
		txtKotamadyaReg = new JTextField();
		txtKotamadyaReg.setBounds(126, 51, 305, 22);
		panelReg.add(txtKotamadyaReg);
		txtKotamadyaReg.setColumns(10);
		
		JLabel lblPropinsi = new JLabel("Propinsi : ");
		lblPropinsi.setForeground(new Color(255, 255, 255));
		lblPropinsi.setBounds(12, 97, 84, 18);
		panelReg.add(lblPropinsi);
		
		txtPropinsiReg = new JTextField();
		txtPropinsiReg.setBounds(126, 95, 305, 22);
		panelReg.add(txtPropinsiReg);
		txtPropinsiReg.setColumns(10);
		
		JLabel lblZonaA = new JLabel("Zona A : ");
		lblZonaA.setForeground(new Color(255, 255, 255));
		lblZonaA.setBounds(12, 137, 70, 18);
		panelReg.add(lblZonaA);
		
		txtZonaAReg = new JTextField();
		txtZonaAReg.setBounds(126, 135, 215, 22);
		panelReg.add(txtZonaAReg);
		txtZonaAReg.setColumns(10);
		
		JLabel lblZonaB = new JLabel("Zona B : ");
		lblZonaB.setForeground(new Color(255, 255, 255));
		lblZonaB.setBounds(12, 178, 70, 18);
		panelReg.add(lblZonaB);
		
		txtZonaBReg = new JTextField();
		txtZonaBReg.setBounds(126, 176, 215, 22);
		panelReg.add(txtZonaBReg);
		txtZonaBReg.setColumns(10);
		
		JLabel lblZonaC = new JLabel("Zona C : ");
		lblZonaC.setForeground(new Color(255, 255, 255));
		lblZonaC.setBounds(12, 219, 70, 18);
		panelReg.add(lblZonaC);
		
		txtZonaCReg = new JTextField();
		txtZonaCReg.setBounds(126, 217, 215, 22);
		panelReg.add(txtZonaCReg);
		txtZonaCReg.setColumns(10);
		
		tabelModelReg = new DefaultTableModel(null,headerReguler);
		
		btnSimpanReg = new JButton("Simpan");
		btnSimpanReg.setIcon(new ImageIcon("src/GambarApp/Simpan.png"));
		btnSimpanReg.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act)
			{
				try
				{
					Connection konek = Koneksi.getKoneksi();
					String query = "INSERT INTO RegulerServ VALUES(?,?,?,?,?)";
					PreparedStatement prepare = konek.prepareStatement(query);
					
	
					prepare.setString(1,txtKodeReg.getText());
					prepare.setInt(2,Integer.parseInt(txtZonaAReg.getText()));
					prepare.setInt(3,Integer.parseInt(txtZonaBReg.getText()));
					prepare.setInt(4,Integer.parseInt(txtZonaCReg.getText()));
					prepare.setInt(5,Integer.parseInt(txtZonaDReg.getText()));
					
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
					txtKodeReg.setText("");
					txtKodeReg.requestFocus();
					txtKotamadyaReg.setText("");
					txtPropinsiReg.setText("");
					txtZonaAReg.setText("");
					txtZonaBReg.setText("");
					txtZonaCReg.setText("");
					txtZonaDReg.setText("");
				}
			}
		});
		btnSimpanReg.setBounds(40, 290, 118, 41);
		panelReg.add(btnSimpanReg);
		
		btnUpdateReg = new JButton("Ubah");
		btnUpdateReg.setIcon(new ImageIcon("src/GambarApp/Edit.png"));
		btnUpdateReg.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act)
			{
				try
				{
					Connection konek = Koneksi.getKoneksi();
					String query = "UPDATE RegulerServ SET ZonaA = ?, ZonaB = ?, ZonaC = ?, ZonaD = ? WHERE Kode = ? ";
					PreparedStatement prepare = konek.prepareStatement(query);
					
					prepare.setInt(1,Integer.parseInt(txtZonaAReg.getText()));
					prepare.setInt(2,Integer.parseInt(txtZonaBReg.getText()));
					prepare.setInt(3,Integer.parseInt(txtZonaCReg.getText()));
					prepare.setInt(4,Integer.parseInt(txtZonaDReg.getText()));
					prepare.setString(5,txtKodeReg.getText());
					
					prepare.executeUpdate();
					JOptionPane.showMessageDialog(null,"Data berhasil disimpan","Pesan",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/GambarApp/Berhasil.png"));
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,"Data gagal disimpan","Pesan",JOptionPane.ERROR_MESSAGE, new ImageIcon("src/GambarApp/Gagal.png"));
					System.out.println(ex);
				}
			}
		});
		btnUpdateReg.setBounds(185, 290, 118, 41);
		panelReg.add(btnUpdateReg);
		
		btnHapusReg = new JButton("Hapus");
		btnHapusReg.setIcon(new ImageIcon("src/GambarApp/Hapus.png"));
		btnHapusReg.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act)
			{
				try
				{
					Connection konek = Koneksi.getKoneksi();
					String query = "DELETE FROM RegulerServ WHERE Kode = ? ";
					PreparedStatement prepare = konek.prepareStatement(query);
				
					prepare.setString(1,txtKodeReg.getText());
					
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
					txtKodeReg.setText("");
					txtKodeReg.requestFocus();
					txtKotamadyaReg.setText("");
					txtPropinsiReg.setText("");
					txtZonaAReg.setText("");
					txtZonaBReg.setText("");
					txtZonaCReg.setText("");
					txtZonaDReg.setText("");
				}
			}
		});
		btnHapusReg.setBounds(334, 290, 118, 41);
		panelReg.add(btnHapusReg);
		
		JLabel lblZonaD = new JLabel("Zona D : ");
		lblZonaD.setForeground(new Color(255, 255, 255));
		lblZonaD.setBounds(12, 258, 70, 18);
		panelReg.add(lblZonaD);
		
		txtZonaDReg = new JTextField();
		txtZonaDReg.setBounds(126, 256, 215, 22);
		panelReg.add(txtZonaDReg);
		txtZonaDReg.setColumns(10);
		
		txtCariTarifReg = new JTextField();
		txtCariTarifReg.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent act) 
			{
				try
				{
					Connection konek = Koneksi.getKoneksi();
					Statement state = konek.createStatement();
					String query = "SELECT RegulerServ.Kode,Wilayah.Kotamadya,Wilayah.Propinsi,RegulerServ.ZonaA,RegulerServ.ZonaB,RegulerServ.ZonaC,RegulerServ.ZonaD FROM RegulerServ INNER JOIN Wilayah ON RegulerServ.Kode = Wilayah.Kode WHERE Wilayah.Kotamadya = '"+txtCariTarifReg.getText()+"'";
					ResultSet rs = state.executeQuery(query);
					if(rs.next() == true)
					{
						String kodeReg = rs.getString(1);
						String kotamadyaReg = rs.getString(2);
						String propinsiReg = rs.getString(3);
						int zonaAReg = rs.getInt(4);
						int zonaBReg = rs.getInt(5);
						int zonaCReg = rs.getInt(6);
						int zonaDReg = rs.getInt(7);
						
						txtKodeReg.setText(kodeReg);
						txtKotamadyaReg.setText(kotamadyaReg);
						txtPropinsiReg.setText(propinsiReg);
						txtZonaAReg.setText("" + zonaAReg);
						txtZonaBReg.setText("" + zonaBReg);
						txtZonaCReg.setText("" + zonaCReg);
						txtZonaDReg.setText("" + zonaDReg);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Data tidak ada dalam daftar");
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
		txtCariTarifReg.addKeyListener(new keyTextFieldWilayah(txtCariTarifReg));
		txtCariTarifReg.setBounds(364, 258, 164, 18);
		panelReg.add(txtCariTarifReg);
		txtCariTarifReg.setColumns(10);
		
		btnCariReg = new JButton("");
		btnCariReg.setIcon(new ImageIcon("src/GambarApp/Cari.png"));
		btnCariReg.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act) 
			{
				new DialogRegServ();
				dispose();
			}
		});
		btnCariReg.setBounds(272, 9, 69, 25);
		panelReg.add(btnCariReg);
		
		lblCariKota = new JLabel("Cari berdasarkan kota :");
		lblCariKota.setForeground(new Color(255, 255, 255));
		lblCariKota.setBounds(366, 231, 199, 15);
		panelReg.add(lblCariKota);
		
		btnRefreshReguler = new JButton("Refresh");
		btnRefreshReguler.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act)
			{
				txtKodeReg.setText("");
				txtKotamadyaReg.setText("");
				txtPropinsiReg.setText("");
				txtZonaAReg.setText("");
				txtZonaBReg.setText("");
				txtZonaCReg.setText("");
				txtZonaDReg.setText("");
				txtCariTarifReg.setText("");
				txtKodeReg.setText("");
			}
		});
		btnRefreshReguler.setIcon(new ImageIcon("src/GambarApp/Refresh.png"));
		btnRefreshReguler.setBounds(471, 290, 117, 41);
		panelReg.add(btnRefreshReguler);
		
		lblWallReg = new JLabel("");
		lblWallReg.setIcon(new ImageIcon("src/GambarApp/wallReguler.png"));
		lblWallReg.setBounds(-9, 0, 662, 488);
		panelReg.add(lblWallReg);
		
		panelOK = new JPanel();
		ImageIcon iconOK = new ImageIcon("src/GambarApp/tabOK.png");
		tabbedPane.addTab("OK", iconOK, panelOK, "Paket OK");
		panelOK.setLayout(null);
		
		JLabel lblKode_1 = new JLabel("Kode : ");
		lblKode_1.setForeground(new Color(255, 255, 255));
		lblKode_1.setBounds(12, 12, 70, 15);
		panelOK.add(lblKode_1);
		
		txtKodeOK = new JTextField();
		txtKodeOK.setBounds(110, 10, 167, 19);
		panelOK.add(txtKodeOK);
		txtKodeOK.setColumns(10);
		
		JLabel lblKotamadya_1 = new JLabel("Kotamadya : ");
		lblKotamadya_1.setForeground(new Color(255, 255, 255));
		lblKotamadya_1.setBounds(12, 55, 93, 15);
		panelOK.add(lblKotamadya_1);
		
		txtKotamadyaOK = new JTextField();
		txtKotamadyaOK.setBounds(110, 53, 290, 19);
		panelOK.add(txtKotamadyaOK);
		txtKotamadyaOK.setColumns(10);
		
		JLabel lblPropinsi_1 = new JLabel("Propinsi : ");
		lblPropinsi_1.setForeground(new Color(255, 255, 255));
		lblPropinsi_1.setBounds(12, 102, 93, 15);
		panelOK.add(lblPropinsi_1);
		
		txtPropinsiOK = new JTextField();
		txtPropinsiOK.setBounds(110, 100, 372, 19);
		panelOK.add(txtPropinsiOK);
		txtPropinsiOK.setColumns(10);
		
		JLabel lblZonaA_1 = new JLabel("Zona A : ");
		lblZonaA_1.setForeground(new Color(255, 255, 255));
		lblZonaA_1.setBounds(12, 153, 70, 15);
		panelOK.add(lblZonaA_1);
		
		txtZonaAOK = new JTextField();
		txtZonaAOK.setBounds(110, 151, 187, 19);
		panelOK.add(txtZonaAOK);
		txtZonaAOK.setColumns(10);
		
		JLabel lblZonaB_1 = new JLabel("Zona B : ");
		lblZonaB_1.setForeground(new Color(255, 255, 255));
		lblZonaB_1.setBounds(12, 192, 70, 15);
		panelOK.add(lblZonaB_1);
		
		txtZonaBOK = new JTextField();
		txtZonaBOK.setBounds(110, 190, 187, 19);
		panelOK.add(txtZonaBOK);
		txtZonaBOK.setColumns(10);
		
		JLabel lblZonaC_1 = new JLabel("Zona C : ");
		lblZonaC_1.setForeground(new Color(255, 255, 255));
		lblZonaC_1.setBounds(12, 227, 70, 15);
		panelOK.add(lblZonaC_1);
		
		txtZonaCOK = new JTextField();
		txtZonaCOK.setBounds(110, 225, 187, 19);
		panelOK.add(txtZonaCOK);
		txtZonaCOK.setColumns(10);
		
		tabelModelOK = new DefaultTableModel(null,headerOK);
		
		btnSimpanOK = new JButton("Simpan");
		btnSimpanOK.setIcon(new ImageIcon("src/GambarApp/Simpan.png"));
		btnSimpanOK.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent act)
			{
				try
				{
					Connection konek = Koneksi.getKoneksi();
					String query = "INSERT INTO OKServ VALUES(?,?,?,?)";
					PreparedStatement prepare = konek.prepareStatement(query);
					
	
					prepare.setString(1,txtKodeOK.getText());
					prepare.setInt(2,Integer.parseInt(txtZonaAOK.getText()));
					prepare.setInt(3,Integer.parseInt(txtZonaBOK.getText()));
					prepare.setInt(4,Integer.parseInt(txtZonaCOK.getText()));
					
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
					txtKodeOK.setText("");
					txtKodeOK.requestFocus();
					txtKotamadyaOK.setText("");
					txtPropinsiOK.setText("");
					txtZonaAOK.setText("");
					txtZonaBOK.setText("");
					txtZonaCOK.setText("");
				}
			}
		});
		btnSimpanOK.setBounds(62, 273, 117, 37);
		panelOK.add(btnSimpanOK);
		
		btnUbahOK = new JButton("Ubah");
		btnUbahOK.setIcon(new ImageIcon("src/GambarApp/Edit.png"));
		btnUbahOK.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act)
			{
				try
				{
					Connection konek = Koneksi.getKoneksi();
					String query = "UPDATE OKServ SET ZonaA = ?, ZonaB = ?, ZonaC = ? WHERE Kode = ? ";
					PreparedStatement prepare = konek.prepareStatement(query);
					
					prepare.setInt(1,Integer.parseInt(txtZonaAOK.getText()));
					prepare.setInt(2,Integer.parseInt(txtZonaBOK.getText()));
					prepare.setInt(3,Integer.parseInt(txtZonaCOK.getText()));
					prepare.setString(4,txtKodeOK.getText());
					
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
					txtKodeOK.setText("");
					txtKodeOK.requestFocus();
					txtKotamadyaOK.setText("");
					txtPropinsiOK.setText("");
					txtZonaAOK.setText("");
					txtZonaBOK.setText("");
					txtZonaCOK.setText("");
				}
			}
		});
		btnUbahOK.setBounds(202, 273, 117, 37);
		panelOK.add(btnUbahOK);
		
		btnHapusOK = new JButton("Hapus");
		btnHapusOK.setIcon(new ImageIcon("src/GambarApp/Hapus.png"));
		btnHapusOK.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act)
			{

				try
				{
					Connection konek = Koneksi.getKoneksi();
					String query = "DELETE FROM OKServ WHERE Kode = ? ";
					PreparedStatement prepare = konek.prepareStatement(query);
				
					prepare.setString(1,txtKodeOK.getText());
					
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
						txtKodeOK.setText("");
						txtKodeOK.requestFocus();
						txtKotamadyaOK.setText("");
						txtPropinsiOK.setText("");
						txtZonaAOK.setText("");
						txtZonaBOK.setText("");
						txtZonaCOK.setText("");
				}
			}
		});
		btnHapusOK.setBounds(347, 273, 117, 37);
		panelOK.add(btnHapusOK);
		
		btnCariOK = new JButton("");
		btnCariOK.setIcon(new ImageIcon("src/GambarApp/Cari.png"));
		btnCariOK.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act)
			{
				new DialogOKServ();
				dispose();
			}
			
		});
		btnCariOK.setBounds(289, 7, 70, 25);
		panelOK.add(btnCariOK);
		
		txtCariTarifOK = new JTextField();
		txtCariTarifOK.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act) 
			{
				try
				{
					Connection konek = Koneksi.getKoneksi();
					Statement state = konek.createStatement();
					String query = "SELECT OKServ.Kode,Wilayah.Kotamadya,Wilayah.Propinsi,OKServ.ZonaA,OKServ.ZonaB,OKServ.ZonaC FROM OKServ INNER JOIN Wilayah ON OKServ.Kode = Wilayah.Kode WHERE Wilayah.Kotamadya = '"+txtCariTarifOK.getText()+"'";
					ResultSet rs = state.executeQuery(query);
					if(rs.next() == true)
					{
						String kodeOK = rs.getString(1);
						String kotamadyaOK = rs.getString(2);
						String propinsiOK = rs.getString(3);
						int zonaAOK = rs.getInt(4);
						int zonaBOK = rs.getInt(5);
						int zonaCOK = rs.getInt(6);
						
						txtKodeOK.setText(kodeOK);
						txtKotamadyaOK.setText(kotamadyaOK);
						txtPropinsiOK.setText(propinsiOK);
						txtZonaAOK.setText("" + zonaAOK);
						txtZonaBOK.setText("" + zonaBOK);
						txtZonaCOK.setText("" + zonaCOK);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Tidak ada dalam daftar");
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
		txtCariTarifOK.addKeyListener(new keyTextFieldWilayah(txtCariTarifOK));
		txtCariTarifOK.setBounds(337, 225, 176, 19);
		panelOK.add(txtCariTarifOK);
		txtCariTarifOK.setColumns(10);
		
		lblCariBerdasarkanKota = new JLabel("Cari berdasarkan kota : ");
		lblCariBerdasarkanKota.setForeground(new Color(255, 255, 255));
		lblCariBerdasarkanKota.setBounds(337, 208, 176, 15);
		panelOK.add(lblCariBerdasarkanKota);
		
		btnRefreshOK = new JButton("Refresh");
		btnRefreshOK.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent act) 
			{
				txtKodeOK.setText("");
				txtKotamadyaOK.setText("");
				txtPropinsiOK.setText("");
				txtZonaAOK.setText("");
				txtZonaBOK.setText("");
				txtZonaCOK.setText("");
				txtCariTarifOK.setText("");
				txtKodeOK.requestFocus();
			}
		});
		btnRefreshOK.setIcon(new ImageIcon("src/GambarApp/Refresh.png"));
		btnRefreshOK.setBounds(489, 273, 117, 37);
		panelOK.add(btnRefreshOK);
		
		lblWallOK = new JLabel("New label");
		lblWallOK.setForeground(new Color(255, 255, 255));
		lblWallOK.setIcon(new ImageIcon("src/GambarApp/wallReguler.png"));
		lblWallOK.setBounds(0, 0, 641, 488);
		panelOK.add(lblWallOK);
		
		panelYes = new JPanel();
		ImageIcon iconYes = new ImageIcon("src/GambarApp/tabYes.png");
		tabbedPane.addTab("YES", iconYes, panelYes, "Paket Yes");
		panelYes.setLayout(null);
		
		lblKode_2 = new JLabel("Kode : ");
		lblKode_2.setForeground(new Color(255, 255, 255));
		lblKode_2.setBounds(12, 12, 70, 15);
		panelYes.add(lblKode_2);
		
		txtKodeYes = new JTextField();
		txtKodeYes.setBounds(135, 10, 146, 19);
		panelYes.add(txtKodeYes);
		txtKodeYes.setColumns(10);
		
		lblKotamadya_2 = new JLabel("Kotamadya : ");
		lblKotamadya_2.setForeground(new Color(255, 255, 255));
		lblKotamadya_2.setBounds(12, 49, 93, 15);
		panelYes.add(lblKotamadya_2);
		
		txtKotamadyaYes = new JTextField();
		txtKotamadyaYes.setBounds(135, 47, 281, 19);
		panelYes.add(txtKotamadyaYes);
		txtKotamadyaYes.setColumns(10);
		
		lblPropinsi_2 = new JLabel("Propinsi : ");
		lblPropinsi_2.setForeground(new Color(255, 255, 255));
		lblPropinsi_2.setBounds(12, 85, 93, 15);
		panelYes.add(lblPropinsi_2);
		
		txtPropinsiYes = new JTextField();
		txtPropinsiYes.setBounds(135, 83, 276, 19);
		panelYes.add(txtPropinsiYes);
		txtPropinsiYes.setColumns(10);
		
		lblTarif = new JLabel("Tarif : ");
		lblTarif.setForeground(new Color(255, 255, 255));
		lblTarif.setBounds(12, 123, 70, 15);
		panelYes.add(lblTarif);
		
		txtTarifYes = new JTextField();
		txtTarifYes.setBounds(135, 114, 114, 19);
		panelYes.add(txtTarifYes);
		txtTarifYes.setColumns(10);
		
		tabelModelYes = new DefaultTableModel(null,headerYes);
		
		btnSimpanYes = new JButton("Simpan");
		btnSimpanYes.setIcon(new ImageIcon("src/GambarApp/Simpan.png"));
		btnSimpanYes.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act)
			{
				try
				{
					Connection konek = Koneksi.getKoneksi();
					String query = "INSERT INTO YesServ VALUES(?,?)";
					PreparedStatement prepare = konek.prepareStatement(query);
					
	
					prepare.setString(1,txtKodeYes.getText());
					prepare.setInt(2,Integer.parseInt(txtTarifYes.getText()));
					
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
					txtKodeYes.setText("");
					txtKodeYes.requestFocus();
					txtKotamadyaYes.setText("");
					txtPropinsiYes.setText("");
					txtTarifYes.setText("");
				}
			}
		});
		btnSimpanYes.setBounds(51, 255, 117, 41);
		panelYes.add(btnSimpanYes);
		
		btnUbahYes = new JButton("Ubah");
		btnUbahYes.setIcon(new ImageIcon("src/GambarApp/Edit.png"));
		btnUbahYes.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent act) 
			{
				try
				{
					Connection konek = Koneksi.getKoneksi();
					String query = "UPDATE YesServ SET TarifYes = ? WHERE Kode = ? ";
					PreparedStatement prepare = konek.prepareStatement(query);
					
					prepare.setInt(1,Integer.parseInt(txtTarifYes.getText()));
					prepare.setString(2,txtKodeYes.getText());
					
					prepare.executeUpdate();
					JOptionPane.showMessageDialog(null,"Data berhasil disimpan","Pesan",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/GambarApp/Berhasil.png"));
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,"Data gagal disimpan","Pesan",JOptionPane.ERROR_MESSAGE, new ImageIcon("src/GambarApp/Gagal.png"));
					System.out.println(ex);
				}
			}
		});
		btnUbahYes.setBounds(193, 255, 117, 41);
		panelYes.add(btnUbahYes);
		
		btnHapusYes = new JButton("Hapus");
		btnHapusYes.setIcon(new ImageIcon("src/GambarApp/Hapus.png"));
		btnHapusYes.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act) 
			{
				try
				{
					Connection konek = Koneksi.getKoneksi();
					String query = "DELETE FROM YesServ WHERE Kode = ? ";
					PreparedStatement prepare = konek.prepareStatement(query);
				
					prepare.setString(1,txtKodeYes.getText());
					
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
					txtKodeYes.setText("");
					txtKodeYes.requestFocus();
					txtKotamadyaYes.setText("");
					txtPropinsiYes.setText("");
					txtTarifYes.setText("");
					txtCariTarifYes.setText("");
				}
			}
		});
		btnHapusYes.setBounds(335, 255, 117, 41);
		panelYes.add(btnHapusYes);
		
		btnCariYes = new JButton("");
		btnCariYes.setIcon(new ImageIcon("src/GambarApp/Cari.png"));
		btnCariYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent act)
			{
				new DialogYesServ();
				dispose();
			}
		});
		btnCariYes.setBounds(298, 7, 70, 25);
		panelYes.add(btnCariYes);
		
		txtCariTarifYes = new JTextField();
		txtCariTarifYes.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent act)
			{
				try
				{
					Connection konek = Koneksi.getKoneksi();
					Statement state = konek.createStatement();
					String query = "SELECT YesServ.Kode,Wilayah.Kotamadya,Wilayah.Propinsi,YesServ.TarifYes FROM YesServ INNER JOIN Wilayah ON YesServ.Kode = Wilayah.Kode WHERE Wilayah.Kotamadya = '"+txtCariTarifYes.getText()+"'";
					ResultSet rs = state.executeQuery(query);
					if(rs.next() == true)
					{
						String kodeYes = rs.getString(1);
						String kotamadyaYes = rs.getString(2);
						String propinsiYes = rs.getString(3);
						int tarifYes = rs.getInt(4);
						
						txtKodeYes.setText(kodeYes);
						txtKotamadyaYes.setText(kotamadyaYes);
						txtPropinsiYes.setText(propinsiYes);
						txtTarifYes.setText("" + tarifYes);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Tidak ada dalam daftar");
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
		txtCariTarifYes.addKeyListener(new keyTextFieldWilayah(txtCariTarifYes));
		txtCariTarifYes.setBounds(428, 121, 182, 19);
		panelYes.add(txtCariTarifYes);
		txtCariTarifYes.setColumns(10);
		
		lblCariBerdasarkanKota_1 = new JLabel("Cari berdasarkan kota : ");
		lblCariBerdasarkanKota_1.setForeground(new Color(255, 255, 255));
		lblCariBerdasarkanKota_1.setBounds(429, 97, 182, 15);
		panelYes.add(lblCariBerdasarkanKota_1);
		
		btnRefreshYes = new JButton("Refresh");
		btnRefreshYes.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act) 
			{
				txtKodeYes.setText("");
				txtKotamadyaYes.setText("");
				txtPropinsiYes.setText("");
				txtTarifYes.setText("");
				txtCariTarifYes.setText("");
				txtKodeYes.requestFocus();
			}
		});
		btnRefreshYes.setIcon(new ImageIcon("src/GambarApp/Refresh.png"));
		btnRefreshYes.setBounds(480, 255, 117, 41);
		panelYes.add(btnRefreshYes);
		
		lblWallYes = new JLabel("");
		lblWallYes.setIcon(new ImageIcon("src/GambarApp/wallReguler.png"));
		lblWallYes.setBounds(0, 0, 641, 488);
		panelYes.add(lblWallYes);
		
	}
}
