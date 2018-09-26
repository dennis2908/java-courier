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

@SuppressWarnings("serial")
public class cetakAWB extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JCalendarCombo tglAwal;
	private JCalendarCombo tglAkhir;
	private JButton btnCetak;

	/**
	 * Create the dialog.
	 */
	public cetakAWB() 
	{
		setResizable(false);
		setBounds(100, 100, 524, 194);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		tglAwal = new JCalendarCombo(JCalendarCombo.DISPLAY_DATE,false);
		tglAwal.setDateFormat(new SimpleDateFormat("yyyy-MM-dd")); //yyyy-MM-dd
		tglAwal.setBounds(12, 43, 189, 24);
		contentPanel.add(tglAwal);
		
		tglAkhir = new JCalendarCombo(JCalendarCombo.DISPLAY_DATE,false);
		tglAkhir.setDateFormat(new SimpleDateFormat("yyyy-MM-dd")); //yyyy-MM-dd
		tglAkhir.setBounds(319, 43, 189, 24);
		contentPanel.add(tglAkhir);
		
		JLabel lblSD = new JLabel("s / d");
		lblSD.setForeground(new Color(255, 255, 255));
		lblSD.setBounds(237, 48, 50, 15);
		contentPanel.add(lblSD);
		
		btnCetak = new JButton("Cetak");
		btnCetak.setIcon(new ImageIcon("src/GambarApp/cetak.png"));
		btnCetak.addActionListener(new ActionListener() 
		{
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent act)
			{
				try
	               {
	                   String NamaFile = "src/iReport/LaporanAWB.jasper"; //Lokasi File jasper
	                   Class.forName("com.mysql.jdbc.Driver").newInstance();
	                   Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/JNE","root","root");
	                   @SuppressWarnings("rawtypes")
	                   HashMap hash = new HashMap(2);
	                   //Mengambil parameter dari ireport
	                   hash.put("tglAwal",tglAwal.getSelectedItem());
	                   hash.put("tglAkhir",tglAkhir.getSelectedItem());
	                    
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
		
		JLabel lblWall = new JLabel("");
		lblWall.setIcon(new ImageIcon("/home/resa/resa/Aplikasi Java/Aplikasi Pengiriman/src/GambarApp/wallLapAWB.jpg"));
		lblWall.setBounds(0, 0, 520, 167);
		contentPanel.add(lblWall);
		setVisible(true);
		setLocationRelativeTo(null);
	}
}
