package aplikasiPengiriman;

import java.awt.BorderLayout;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.freixas.jcalendar.JCalendarCombo;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public class FrmLaporanPengiriman extends JDialog 
{
	private final JPanel contentPanel = new JPanel();
	private JCalendarCombo tglNow;
	private JButton btnCetak;
	private JLabel lblLaporanPengiriman;
	private JLabel lblIconPengirim;

	/**
	 * Create the dialog.
	 */
	public FrmLaporanPengiriman() 
	{
		setResizable(false);
		setBounds(100, 100, 524, 194);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		tglNow = new JCalendarCombo(JCalendarCombo.DISPLAY_DATE,false);
		tglNow.setDateFormat(new SimpleDateFormat("yyyy-MM-dd")); //yyyy-MM-dd
		tglNow.setBounds(173, 43, 189, 24);
		contentPanel.add(tglNow);
		
		btnCetak = new JButton("Cetak");
		btnCetak.setIcon(new ImageIcon("src/GambarApp/cetak.png"));
		btnCetak.addActionListener(new ActionListener() 
		{
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent act)
			{
				try
	               {
	                   String NamaFile = "src/iReport/LaporanPengiriman.jasper"; //Lokasi File jasper
	                   Class.forName("com.mysql.jdbc.Driver").newInstance();
	                   Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/Pengiriman","root","root");
	                   @SuppressWarnings("rawtypes")
	                   HashMap hash = new HashMap();
	                   //Mengambil parameter dari ireport
	                   hash.put("tglNow",tglNow.getSelectedItem());
	                    
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
		btnCetak.setBounds(204, 124, 117, 31);
		contentPanel.add(btnCetak);
		
		lblLaporanPengiriman = new JLabel("Laporan Pengiriman");
		lblLaporanPengiriman.setFont(new Font("Liberation Serif", Font.BOLD, 16));
		lblLaporanPengiriman.setForeground(new Color(255, 255, 255));
		lblLaporanPengiriman.setBounds(194, 16, 154, 15);
		contentPanel.add(lblLaporanPengiriman);
		
		lblIconPengirim = new JLabel("");
		lblIconPengirim.setIcon(new ImageIcon("src/GambarApp/Kalender.png"));
		lblIconPengirim.setBounds(383, 79, 90, 76);
		contentPanel.add(lblIconPengirim);
		
		JLabel lblWall = new JLabel("");
		lblWall.setIcon(new ImageIcon("src/GambarApp/wallLapAWB.jpg"));
		lblWall.setBounds(0, 0, 520, 167);
		contentPanel.add(lblWall);
		setVisible(true);
		setLocationRelativeTo(null);
	}
}
