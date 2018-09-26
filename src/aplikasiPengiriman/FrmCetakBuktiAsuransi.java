package aplikasiPengiriman;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

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
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import org.freixas.jcalendar.JCalendarCombo;

@SuppressWarnings("serial")
public class FrmCetakBuktiAsuransi extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnCetak;
	DefaultTableModel tabelModel;
	String header[] = {"NoBPK","TglKirim","Biaya"};
	private JButton btnSimpan;
	private JTextField txtNoBA;
	JTextField txtAWB;
	private JTextArea textIsi;
	private JScrollPane scrollPane;
	private JLabel lblPengirim;
	private JLabel lblPenerima;
	private JTextField txtPengirim;
	private JTextField txtPenerima;
	private JTextField txtBiayaAsuransi;
	private JTextField txtOngkosKirim;
	private JLabel lblIcon;
	private JCalendarCombo tglBA;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public FrmCetakBuktiAsuransi() {
		setBounds(100, 100, 769, 453);
		setVisible(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			btnCetak = new JButton("Cetak");
			btnCetak.setIcon(new ImageIcon("src/GambarApp/cetak.png"));
			btnCetak.addActionListener(new ActionListener()
			{
				@SuppressWarnings("unchecked")
				public void actionPerformed(ActionEvent act)
				{
					try
		               {
		                   String NamaFile = "src/iReport/BuktiAsuransi.jasper"; //Lokasi File jasper
		                   Class.forName("com.mysql.jdbc.Driver").newInstance();
		                   Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/Pengiriman","root","root");
		                   @SuppressWarnings("rawtypes")
		                   HashMap hash = new HashMap();
		                   //Mengambil parameter dari ireport
		                   hash.put("ParamAsuransi",txtNoBA.getText());
		                    
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
			btnCetak.setBounds(361, 379, 117, 32);
			contentPanel.add(btnCetak);
		}
		
		tabelModel = new DefaultTableModel(null,header);
		
		btnSimpan = new JButton("Simpan");
		btnSimpan.setIcon(new ImageIcon("src/GambarApp/Simpan.png"));
		btnSimpan.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act)
			{
				try
				{
					Connection konek = Koneksi.getKoneksi();
					String query = "INSERT INTO BAsuransi VALUES(?,?,?)";
					PreparedStatement prepare = konek.prepareStatement(query);
					
					prepare.setString(1, txtNoBA.getText());
					prepare.setString(2, (String) tglBA.getSelectedItem());
					prepare.setString(3,txtAWB.getText());
					prepare.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Data berhasil disimpan","Pesan",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("src/GambarApp/Berhasil.png"));
					prepare.close();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,"Data gagal disimpan","Pesan",JOptionPane.ERROR_MESSAGE, new ImageIcon("src/GambarApp/Gagal.png"));
					System.out.println(ex);
				}
			}
		});
		btnSimpan.setBounds(194, 379, 117, 32);
		contentPanel.add(btnSimpan);
		
		JLabel lblNobuktiAsuransi = new JLabel("No.Bukti Asuransi : ");
		lblNobuktiAsuransi.setForeground(new Color(255, 255, 255));
		lblNobuktiAsuransi.setBounds(40, 29, 139, 15);
		contentPanel.add(lblNobuktiAsuransi);
		
		txtNoBA = new JTextField();
		txtNoBA.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act) 
			{
				try
				{
					Connection konek = Koneksi.getKoneksi();
					Statement state = konek.createStatement();
					String query = "SELECT Pengiriman.NoAWB,Pengirim.Nama,Pengiriman.Nama,Pengiriman.NamaBarang,BAsuransi.TglBA,Pengiriman.Harga,Pengiriman.Berat,Pengiriman.totalAsuransi,Pengiriman.Packaging FROM Pengirim INNER JOIN Pengiriman ON Pengirim.NoPengirim = Pengiriman.NoPengirim INNER JOIN Wilayah ON Wilayah.Kode = Pengiriman.Kode INNER JOIN BAsuransi ON BAsuransi.NoAWB = Pengiriman.NoAWB WHERE BAsuransi.NoBA = '"+txtNoBA.getText()+"'";
					ResultSet rs = state.executeQuery(query);
					
					if(rs.next() == true)
					{
						Object obj[] = new Object[7];
						obj[0] = rs.getString(1);
						obj[1] = rs.getString(2);
						obj[2] = rs.getString(3);
						obj[3] = rs.getString(4);//Jenis
						obj[4] = rs.getString(5);
						obj[5] = (rs.getInt(6) * rs.getInt(7)) + rs.getInt(8) + rs.getInt(9);
						obj[6] = rs.getInt(9);
						
						txtAWB.setText("" + obj[0]);
						txtPengirim.setText("" + obj[1]);
						txtPenerima.setText("" + obj[2]);
						textIsi.setText("" + obj[3]);
						tglBA.setSelectedItem(obj[4]);
						txtOngkosKirim.setText("" + obj[5]);
						txtBiayaAsuransi.setText("" + rs.getInt(8));
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Data tidak ditemukan","Pesan",JOptionPane.ERROR_MESSAGE);
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
		txtNoBA.setBounds(194, 27, 114, 19);
		contentPanel.add(txtNoBA);
		txtNoBA.setColumns(10);
		
		JButton btnCari = new JButton("");
		btnCari.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				new DialogAWBAsuransi();
				dispose();
			}
		});
		btnCari.setIcon(new ImageIcon("src/GambarApp/Cari.png"));
		btnCari.setBounds(469, 60, 50, 32);
		contentPanel.add(btnCari);
		
		JLabel lblNoawb = new JLabel("No.AWB : ");
		lblNoawb.setForeground(new Color(255, 255, 255));
		lblNoawb.setBounds(40, 68, 70, 15);
		contentPanel.add(lblNoawb);
		
		txtAWB = new JTextField();
		txtAWB.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act)
			{
				try
				{
					Connection konek = Koneksi.getKoneksi();
					Statement state = konek.createStatement();
					String query = "SELECT Pengirim.Nama,Pengiriman.Nama,Pengiriman.NamaBarang,Pengiriman.Harga,Pengiriman.Berat,Pengiriman.totalAsuransi,Pengiriman.Packaging,Pengiriman.Asuransi,Pengiriman.AdminAsuransi FROM Pengirim INNER JOIN Pengiriman ON Pengirim.NoPengirim = Pengiriman.NoPengirim INNER JOIN Wilayah ON Wilayah.Kode = Pengiriman.Kode WHERE Pengiriman.NoAWB = '"+txtAWB.getText()+"'";
					ResultSet rs = state.executeQuery(query);
					
					while(rs.next())
					{
						Object obj[] = new Object[5];
						
						obj[0] = rs.getString(1);
						obj[1] = rs.getString(2);
						obj[2] = rs.getString(3);//Jenis
						obj[3] = (rs.getInt(4) * rs.getInt(5)) + rs.getInt(6) + rs.getInt(7);
						obj[4] = rs.getInt(7);
						
						txtPengirim.setText("" + obj[0]);
						txtPenerima.setText("" + obj[1]);
						textIsi.setText("" + obj[2]);
						txtOngkosKirim.setText("" + obj[3]);
						txtBiayaAsuransi.setText("" + rs.getInt(6));
						
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
		txtAWB.setBounds(194, 66, 263, 19);
		contentPanel.add(txtAWB);
		txtAWB.setColumns(10);
		
		JLabel lblIsiBarang = new JLabel("Isi Barang : ");
		lblIsiBarang.setForeground(new Color(255, 255, 255));
		lblIsiBarang.setBounds(42, 161, 97, 15);
		contentPanel.add(lblIsiBarang);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(194, 155, 223, 78);
		contentPanel.add(scrollPane);
		
		textIsi = new JTextArea();
		scrollPane.setViewportView(textIsi);
		
		lblPengirim = new JLabel("Pengirim : ");
		lblPengirim.setForeground(new Color(255, 255, 255));
		lblPengirim.setBounds(40, 109, 99, 15);
		contentPanel.add(lblPengirim);
		
		lblPenerima = new JLabel("Penerima : ");
		lblPenerima.setForeground(new Color(255, 255, 255));
		lblPenerima.setBounds(40, 134, 85, 15);
		contentPanel.add(lblPenerima);
		
		txtPengirim = new JTextField();
		txtPengirim.setBounds(194, 104, 284, 19);
		contentPanel.add(txtPengirim);
		txtPengirim.setColumns(10);
		
		txtPenerima = new JTextField();
		txtPenerima.setBounds(194, 132, 284, 19);
		contentPanel.add(txtPenerima);
		txtPenerima.setColumns(10);
		
		JLabel lblBiayaAsuransi = new JLabel("Biaya Asuransi : ");
		lblBiayaAsuransi.setForeground(new Color(255, 255, 255));
		lblBiayaAsuransi.setBounds(40, 255, 139, 15);
		contentPanel.add(lblBiayaAsuransi);
		
		txtBiayaAsuransi = new JTextField();
		txtBiayaAsuransi.setBounds(194, 253, 234, 19);
		contentPanel.add(txtBiayaAsuransi);
		txtBiayaAsuransi.setColumns(10);
		
		JLabel lblOngkosKirim = new JLabel("Ongkos Kirim : ");
		lblOngkosKirim.setForeground(new Color(255, 255, 255));
		lblOngkosKirim.setBounds(40, 282, 114, 15);
		contentPanel.add(lblOngkosKirim);
		
		txtOngkosKirim = new JTextField();
		txtOngkosKirim.setBounds(194, 284, 247, 19);
		contentPanel.add(txtOngkosKirim);
		txtOngkosKirim.setColumns(10);
		
		lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon("src/GambarApp/Surat.png"));
		lblIcon.setBounds(549, 158, 156, 138);
		contentPanel.add(lblIcon);
		
		JLabel lblTanggal = new JLabel("Tanggal : ");
		lblTanggal.setForeground(new Color(255, 255, 255));
		lblTanggal.setBounds(469, 29, 70, 15);
		contentPanel.add(lblTanggal);
		
		tglBA = new JCalendarCombo(JCalendarCombo.DISPLAY_DATE,false);
		tglBA.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		tglBA.setBounds(557, 24, 180, 24);
		contentPanel.add(tglBA);
	
		
		JLabel lblWall = new JLabel("");
		lblWall.setForeground(new Color(255, 255, 255));
		lblWall.setIcon(new ImageIcon("src/GambarApp/BAWall.png"));
		lblWall.setBounds(0, 0, 767, 423);
		contentPanel.add(lblWall);
		
		autoNumber();
	}
	
	public void autoNumber()
	{
		try
		{
			Connection konek = Koneksi.getKoneksi();
			Statement state = konek.createStatement();
			String query = "SELECT MAX(right(NoBA,2)) AS no FROM BAsuransi";
			
			ResultSet rs = state.executeQuery(query);
			while(rs.next())
			{
				if(rs.first() == false)
				{
					txtNoBA.setText("BA001");
				}
				else
				{
					rs.last();
					int noBA = rs.getInt(1) + 1;
					String no = String.valueOf(noBA);
					int noLong = no.length();
					for(int a=0;a<2-noLong;a++)
					{
						no = "00" + no;
					}
					txtNoBA.setText("BA" + no);
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
	
	public static void main(String[] args) {
		try {
			@SuppressWarnings("unused")
			FrmCetakBuktiAsuransi ba = new FrmCetakBuktiAsuransi();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
