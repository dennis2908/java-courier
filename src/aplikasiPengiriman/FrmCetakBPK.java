package aplikasiPengiriman;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.awt.Color;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.JTextArea;
import org.freixas.jcalendar.JCalendarCombo;

public class FrmCetakBPK extends JFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel contentPane;
	JTextField txtBPK;
	 JTextField txtBA;
	 JTextField txtPengirim;
	 JTextField txtPenerima;
	 JTextField txtJenisKiriman;
	 JTextField txtMerk;
	 JTextField txtPcs;
	 JTextField txtSeri;
	 JTextField txtImei;
	 JTextField txtWarna;
	 JTextField txtKondisi;
	 JButton btnSimpan;
	 JTextField txtTglKirim;
	 JButton btnCariAWB;
	 private JLabel lblBgBPK;
	 private JButton btnCetak;
	 private JTextArea txtAcessoris;
	 private JButton btnTambah;
	 private JButton btnTambahBarang;
	 private JCalendarCombo tglBPK;
	 JTextField txtNoAWB;

	/**
	 * Create the frame.
	 */
	public FrmCetakBPK()
	{
		setResizable(false);
		setBounds(100, 100, 742, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNoBpk = new JLabel("No BPK : ");
		lblNoBpk.setForeground(new Color(255, 255, 255));
		lblNoBpk.setBounds(12, 12, 70, 18);
		contentPane.add(lblNoBpk);
		
		txtBPK = new JTextField();
		txtBPK.setBounds(111, 10, 134, 22);
		contentPane.add(txtBPK);
		txtBPK.setColumns(10);
		
		JLabel lblNoBA = new JLabel("No BA : ");
		lblNoBA.setForeground(new Color(255, 255, 255));
		lblNoBA.setBounds(265, 12, 70, 18);
		contentPane.add(lblNoBA);
		
		txtBA = new JTextField();
		txtBA.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act) 
			{
				try
				{
					Connection konek = Koneksi.getKoneksi();
					Statement state = konek.createStatement();
					String query = "SELECT Pengirim.Nama,Pengiriman.Nama,Pengiriman.TglKirim FROM Pengirim INNER JOIN Pengiriman ON Pengirim.NoPengirim = Pengiriman.NoPengirim WHERE NoAWB = '"+txtBA.getText()+"'";
					ResultSet rs = state.executeQuery(query);
					while(rs.next())
					{
						txtPengirim.setText(rs.getString(1));
						txtPenerima.setText(rs.getString(2));
						txtTglKirim.setText(rs.getString(3));
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
		txtBA.setBounds(340, 11, 157, 22);
		contentPane.add(txtBA);
		txtBA.setColumns(10);
		
		JLabel lblPengirim = new JLabel("Pengirim : ");
		lblPengirim.setForeground(new Color(255, 255, 255));
		lblPengirim.setBounds(12, 78, 88, 18);
		contentPane.add(lblPengirim);
		
		txtPengirim = new JTextField();
		txtPengirim.setBounds(111, 74, 272, 22);
		contentPane.add(txtPengirim);
		txtPengirim.setColumns(10);
		
		JLabel lblPenerima = new JLabel("Penerima : ");
		lblPenerima.setForeground(new Color(255, 255, 255));
		lblPenerima.setBounds(12, 108, 81, 18);
		contentPane.add(lblPenerima);
		
		txtPenerima = new JTextField();
		txtPenerima.setBounds(111, 103, 272, 22);
		contentPane.add(txtPenerima);
		txtPenerima.setColumns(10);
		
		JLabel lblJenis = new JLabel("Jenis : ");
		lblJenis.setForeground(new Color(255, 255, 255));
		lblJenis.setBounds(12, 138, 70, 18);
		contentPane.add(lblJenis);
		
		txtJenisKiriman = new JTextField();
		txtJenisKiriman.setBounds(111, 137, 320, 22);
		contentPane.add(txtJenisKiriman);
		txtJenisKiriman.setColumns(10);
		
		JLabel lblMerk = new JLabel("Merk : ");
		lblMerk.setForeground(new Color(255, 255, 255));
		lblMerk.setBounds(12, 168, 70, 18);
		contentPane.add(lblMerk);
		
		txtMerk = new JTextField();
		txtMerk.setBounds(111, 167, 338, 22);
		contentPane.add(txtMerk);
		txtMerk.setColumns(10);
		
		JLabel lblPcs = new JLabel("Pcs : ");
		lblPcs.setForeground(new Color(255, 255, 255));
		lblPcs.setBounds(12, 198, 70, 18);
		contentPane.add(lblPcs);
		
		txtPcs = new JTextField();
		txtPcs.setBounds(111, 197, 70, 22);
		contentPane.add(txtPcs);
		txtPcs.setColumns(10);
		
		JLabel lblSeri = new JLabel("Seri : ");
		lblSeri.setForeground(new Color(255, 255, 255));
		lblSeri.setBounds(12, 228, 70, 18);
		contentPane.add(lblSeri);
		
		txtSeri = new JTextField();
		txtSeri.setBounds(111, 227, 134, 22);
		contentPane.add(txtSeri);
		txtSeri.setColumns(10);
		
		JLabel lblImei = new JLabel("Imei : ");
		lblImei.setForeground(new Color(255, 255, 255));
		lblImei.setBounds(12, 258, 70, 18);
		contentPane.add(lblImei);
		
		txtImei = new JTextField();
		txtImei.setBounds(111, 257, 193, 22);
		contentPane.add(txtImei);
		txtImei.setColumns(10);
		
		JLabel lblWarna = new JLabel("Warna : ");
		lblWarna.setForeground(new Color(255, 255, 255));
		lblWarna.setBounds(12, 288, 70, 18);
		contentPane.add(lblWarna);
		
		txtWarna = new JTextField();
		txtWarna.setBounds(111, 284, 120, 22);
		contentPane.add(txtWarna);
		txtWarna.setColumns(10);
		
		JLabel lblKondisi = new JLabel("Kondisi : ");
		lblKondisi.setForeground(new Color(255, 255, 255));
		lblKondisi.setBounds(12, 311, 70, 18);
		contentPane.add(lblKondisi);
		
		txtKondisi = new JTextField();
		txtKondisi.setBounds(111, 310, 134, 22);
		contentPane.add(txtKondisi);
		txtKondisi.setColumns(10);
		
		JLabel lblAcessoris = new JLabel("Acessoris : ");
		lblAcessoris.setForeground(new Color(255, 255, 255));
		lblAcessoris.setBounds(316, 198, 88, 18);
		contentPane.add(lblAcessoris);
		
		btnSimpan = new JButton("Simpan");
		btnSimpan.setIcon(new ImageIcon("src/GambarApp/Simpan.png"));
		btnSimpan.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act) 
			{
				try
				{
					Connection konek = Koneksi.getKoneksi();
					String query = "INSERT INTO BPK VALUES(?,?,?,?)";
					PreparedStatement prepare = konek.prepareStatement(query);
					
					prepare.setString(1,txtBPK.getText());
					prepare.setString(2,(String)tglBPK.getSelectedItem());
					prepare.setString(3,txtNoAWB.getText());
					prepare.setString(4,txtBA.getText());
					
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
					cetakBPK();
				}
			}
		});
		btnSimpan.setBounds(111, 404, 134, 34);
		contentPane.add(btnSimpan);
		
		JLabel lblTglKirim = new JLabel("Tgl Kirim : ");
		lblTglKirim.setForeground(new Color(255, 255, 255));
		lblTglKirim.setBounds(441, 45, 88, 15);
		contentPane.add(lblTglKirim);
		
		txtTglKirim = new JTextField();
		txtTglKirim.setBounds(560, 45, 166, 19);
		contentPane.add(txtTglKirim);
		txtTglKirim.setColumns(10);
		
		btnCariAWB = new JButton("");
		btnCariAWB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent act)
			{
				new DialogBPK();
				dispose();
			}
		});
		btnCariAWB.setIcon(new ImageIcon("src/GambarApp/Cari.png"));
		btnCariAWB.setBounds(509, 10, 47, 25);
		contentPane.add(btnCariAWB);
		
		btnCetak = new JButton("Cetak");
		btnCetak.setIcon(new ImageIcon("src/GambarApp/cetak.png"));
		btnCetak.addActionListener(new ActionListener() 
		{
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent act)
			{
				try
	               {
	                   String NamaFile = "src/iReport/BPK.jasper"; //Lokasi File jasper
	                   Class.forName("com.mysql.jdbc.Driver").newInstance();
	                   Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/Pengiriman","root","root");
	                   @SuppressWarnings("rawtypes")
	                   HashMap hash = new HashMap();
	                   //Mengambil parameter dari ireport
	                   hash.put("ParamBPK",txtBPK.getText());
	                    
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
		btnCetak.setBounds(279, 404, 134, 34);
		contentPane.add(btnCetak);
		
		btnTambah = new JButton("Tambah");
		btnTambah.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act)
			{
				try
				{
					Connection konek = Koneksi.getKoneksi();
					String query = "INSERT INTO BarangBPK VALUES(?,?,?,?,?,?,?,?,?)";
					PreparedStatement prepare = konek.prepareStatement(query);
					
					prepare.setString(1,txtBPK.getText());
					prepare.setString(2,txtJenisKiriman.getText());
					prepare.setString(3,txtMerk.getText());
					prepare.setString(4,txtAcessoris.getText());
					prepare.setInt(5, Integer.parseInt(txtPcs.getText()));
					prepare.setString(6, txtSeri.getText());
					prepare.setString(7,txtImei.getText());
					prepare.setString(8,txtWarna.getText());
					prepare.setString(9,txtKondisi.getText());
					
					prepare.executeUpdate();
					JOptionPane.showMessageDialog(null,"Data berhasil ditambah","Pesan",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("src/GambarApp/Berhasil.png"));
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,"Data gagal ditambah","Pesan",JOptionPane.ERROR_MESSAGE, new ImageIcon("src/GambarApp/Gagal.png"));
					System.out.println(ex);
				}
			}
		});
		btnTambah.setBounds(111, 338, 117, 25);
		contentPane.add(btnTambah);
		
		btnTambahBarang = new JButton("");
		btnTambahBarang.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent act)
			{
				txtJenisKiriman.setText("");
				txtMerk.setText("");
				txtAcessoris.setText("");
				txtPcs.setText("");
				txtSeri.setText("");
				txtImei.setText("");
				txtWarna.setText("");
				txtKondisi.setText("");
				txtJenisKiriman.requestFocus();
			}
		});
		btnTambahBarang.setIcon(new ImageIcon("src/GambarApp/Refresh.png"));
		btnTambahBarang.setBounds(238, 338, 47, 25);
		contentPane.add(btnTambahBarang);
		
		JLabel lblIconBarang = new JLabel("");
		lblIconBarang.setIcon(new ImageIcon("src/GambarApp/iconBarang.png"));
		lblIconBarang.setBounds(560, 153, 128, 126);
		contentPane.add(lblIconBarang);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(316, 223, 207, 109);
		contentPane.add(scrollPane_1);
		
		txtAcessoris = new JTextArea();
		scrollPane_1.setViewportView(txtAcessoris);
		
		JLabel lblTglBpk = new JLabel("Tgl BPK : ");
		lblTglBpk.setForeground(new Color(255, 255, 255));
		lblTglBpk.setBounds(441, 74, 70, 15);
		contentPane.add(lblTglBpk);
		
		tglBPK = new JCalendarCombo(JCalendarCombo.DISPLAY_DATE,false);
		tglBPK.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		tglBPK.setBounds(560, 69, 166, 24);
		contentPane.add(tglBPK);
		
		JLabel lblNoawb = new JLabel("NoAWB : ");
		lblNoawb.setForeground(new Color(255, 255, 255));
		lblNoawb.setBounds(12, 51, 70, 15);
		contentPane.add(lblNoawb);
		
		txtNoAWB = new JTextField();
		txtNoAWB.setBounds(111, 43, 224, 19);
		contentPane.add(txtNoAWB);
		txtNoAWB.setColumns(10);
		
		lblBgBPK = new JLabel("");
		lblBgBPK.setIcon(new ImageIcon("src/GambarApp/bgBPK.jpg"));
		lblBgBPK.setBounds(0, 0, 738, 535);
		contentPane.add(lblBgBPK);
		setVisible(true);
		setLocationRelativeTo(null);
		autoNumber();
		
	}
	
	@SuppressWarnings("unchecked")
	public void cetakBPK()
	{
		try
        {
            String NamaFile = "src/iReport/BPK.jasper"; //Lokasi File jasper
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/Pengiriman","root","root");
            @SuppressWarnings("rawtypes")
			HashMap hash = new HashMap();
            //Mengambil parameter dari ireport
            hash.put("ParamBPK",txtBPK.getText());
             
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
	
	public void autoNumber()
	{
		try
		{
			Connection konek = Koneksi.getKoneksi();
			Statement state = konek.createStatement();
			String query = "SELECT MAX(right(NoBPK,2)) AS no FROM BPK";
			
			ResultSet rs = state.executeQuery(query);
			while(rs.next())
			{
				if(rs.first() == false)
				{
					txtBPK.setText("BP001");
				}
				else
				{
					rs.last();
					int noBPK = rs.getInt(1) + 1;
					String no = String.valueOf(noBPK);
					int noLong = no.length();
					for(int a=0;a<2-noLong;a++)
					{
						no = "00" + no;
					}
					txtBPK.setText("BP" + no);
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