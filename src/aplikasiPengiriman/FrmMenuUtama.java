package aplikasiPengiriman;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Toolkit;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import java.awt.Cursor;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import IconMakeOver.IconLabel;

@SuppressWarnings("serial")
public class FrmMenuUtama extends JFrame 
{
	private JPanel contentPane;
	private JLabel lblbgMu;
	private JLabel lblmenuPengirim;
	private JLabel lblnamaMenuPengirim;
	private JFileChooser fc = new JFileChooser();
	private JMenuItem miImage = new JMenuItem("Change Wallpaper", new ImageIcon("src/GambarApp/viewer.png"));
	private JPopupMenu popUp = new JPopupMenu();
	private JLabel lblRunText;
	javax.swing.Timer waktu;
	private JLabel lblTextZona;
	private JProgressBar LineMaster;
	private JLabel lblMenuMaster;
	private JProgressBar LineTransaksi;
	private JLabel lblMenuTransaksi;
	private JLabel lblMenuFpk;
	private JLabel lblMenuLaporan;
	private JLabel lblLaporanAwb;

	/**
	 * Create the frame.
	 */
	public FrmMenuUtama() 
	{
		super("Menu Utama");
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 1024);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/GambarApp/iconMenuUtama.png"));
		setLocationRelativeTo(null);
		
		IconLabel ilMenuPengirim = new IconLabel();
		lblmenuPengirim = new JLabel("");
		ilMenuPengirim.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				new FrmEntryPengirim().setVisible(true);
			}
		});
		lblmenuPengirim.setIcon(new ImageIcon("src/GambarApp/iconPelanggan.png"));
		ilMenuPengirim.setIconReflect(new ImageIcon("src/GambarApp/iconPelanggan.png"));
		ilMenuPengirim.setBounds(32, 48, 179, 211);
		contentPane.add(ilMenuPengirim);
		
		lblnamaMenuPengirim = new JLabel("Entry Pengirim");
		lblnamaMenuPengirim.setForeground(new Color(255, 255, 153));
		lblnamaMenuPengirim.setFont(new Font("Liberation Serif", Font.BOLD, 15));
		lblnamaMenuPengirim.setBounds(63, 271, 119, 15);
		contentPane.add(lblnamaMenuPengirim);
		
		lblRunText = new JLabel("Sistem Informasi Pengiriman Barang ");
		lblRunText.setHorizontalAlignment(SwingConstants.CENTER);
		lblRunText.setBounds(396, 12, 518, 15);
		contentPane.add(lblRunText);
		
		waktu = new Timer(100,new LabelListener(lblRunText));
		lblRunText.addMouseListener(new LabelMouseListener(waktu));
		waktu.start();
		IconLabel ilMenuWilayah = new IconLabel();
		new JLabel("");
		ilMenuWilayah.addMouseListener(new MouseAdapter() 
		{
			@Override
			
			public void mouseClicked(MouseEvent me) 
			{
				@SuppressWarnings("unused")
				FrmEntryWilayah mz = new FrmEntryWilayah();
			}
		});
		
		ilMenuWilayah.setIconReflect(new ImageIcon("src/GambarApp/iconZona.png"));
		ilMenuWilayah.setBounds(298, 48, 172, 211);
		contentPane.add(ilMenuWilayah);
		
		lblTextZona = new JLabel("Entry Wilayah");
		lblTextZona.setFont(new Font("Liberation Serif", Font.BOLD, 15));
		lblTextZona.setForeground(new Color(255, 255, 0));
		lblTextZona.setBounds(319, 271, 125, 15);
		contentPane.add(lblTextZona);
		
		IconLabel ilMenuZona = new IconLabel();
		ilMenuZona.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent me) 
			{
				new FrmEntryZonaTarif();
			}
		});
		
		ilMenuZona.setIconReflect(new ImageIcon("src/GambarApp/iconTarif.png"));
		ilMenuZona.setBounds(825, 116, 158, 192);
		
		ilMenuZona.setBounds(587, 39, 158, 220);
		contentPane.add(ilMenuZona);
		
		JLabel lblMenuZonaTarif = new JLabel("Entry Zona Tarif");
		lblMenuZonaTarif.setFont(new Font("Liberation Serif", Font.BOLD, 15));
		lblMenuZonaTarif.setForeground(new Color(255, 255, 102));
		lblMenuZonaTarif.setBounds(587, 271, 135, 15);
		contentPane.add(lblMenuZonaTarif);
		
		IconLabel ilMenuPengiriman = new IconLabel();
		ilMenuPengiriman.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent me)
			{
				new FrmCetakKirimBarang();
			}
		});
		ilMenuPengiriman.setIcon(new ImageIcon("src/GambarApp/iconPengiriman.png"));
		ilMenuPengiriman.setIconReflect(new ImageIcon("src/GambarApp/iconPengiriman.png"));
		ilMenuPengiriman.setBounds(53, 298, 172, 188);
		contentPane.add(ilMenuPengiriman);
		
		JLabel lblMenuPengiriman = new JLabel("Cetak Pengiriman ");
		lblMenuPengiriman.setFont(new Font("Liberation Serif", Font.BOLD, 15));
		lblMenuPengiriman.setForeground(new Color(255, 255, 51));
		lblMenuPengiriman.setBounds(63, 486, 144, 15);
		contentPane.add(lblMenuPengiriman);
		
		LineMaster = new JProgressBar();
		LineMaster.setForeground(new Color(255, 255, 255));
		LineMaster.setBounds(12, 42, 148, 5);
		contentPane.add(LineMaster);
		
		lblMenuMaster = new JLabel("Menu Master");
		lblMenuMaster.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblMenuMaster.setForeground(new Color(204, 255, 255));
		lblMenuMaster.setBounds(22, 22, 119, 15);
		contentPane.add(lblMenuMaster);
		
		LineTransaksi = new JProgressBar();
		LineTransaksi.setForeground(Color.WHITE);
		LineTransaksi.setBounds(12, 308, 148, 5);
		contentPane.add(LineTransaksi);
		
		lblMenuTransaksi = new JLabel("Menu Transaksi");
		lblMenuTransaksi.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblMenuTransaksi.setForeground(new Color(204, 255, 255));
		lblMenuTransaksi.setBounds(12, 285, 119, 15);
		contentPane.add(lblMenuTransaksi);
		
		IconLabel ilMenuFPK = new IconLabel();
		ilMenuFPK.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent me)
			{
				new FrmCetakFPK();
			}
		});
		
		ilMenuFPK.setIconReflect(new ImageIcon("src/GambarApp/FPK.png"));
		ilMenuFPK.setBounds(262, 298, 158, 188);
		contentPane.add(ilMenuFPK);
		
		lblMenuFpk = new JLabel("Cetak FPK");
		lblMenuFpk.setFont(new Font("Liberation Serif", Font.BOLD, 15));
		lblMenuFpk.setForeground(new Color(255, 255, 51));
		lblMenuFpk.setBounds(292, 486, 102, 15);
		contentPane.add(lblMenuFpk);
		
		IconLabel ilMenuBPK = new IconLabel();
		ilMenuBPK.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent me)
			{
				new FrmCetakBPK();
			}
		});
		
		ilMenuBPK.setIconReflect(new ImageIcon("src/GambarApp/BPK.png"));
		ilMenuBPK.setBounds(722, 271, 144, 197);
		contentPane.add(ilMenuBPK);
		
		JLabel lblMenuBpk = new JLabel("Cetak BPK");
		lblMenuBpk.setForeground(new Color(255, 255, 51));
		lblMenuBpk.setFont(new Font("Liberation Serif", Font.BOLD, 15));
		lblMenuBpk.setBounds(756, 486, 89, 15);
		contentPane.add(lblMenuBpk);
		
		lblMenuLaporan = new JLabel("Menu Cetak Laporan");
		lblMenuLaporan.setForeground(new Color(204, 255, 255));
		lblMenuLaporan.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblMenuLaporan.setBounds(12, 503, 178, 15);
		contentPane.add(lblMenuLaporan);
		
		JProgressBar LineCetak = new JProgressBar();
		LineCetak.setForeground(Color.WHITE);
		LineCetak.setBounds(12, 520, 148, 5);
		contentPane.add(LineCetak);
		
		IconLabel ilMenuBA = new IconLabel();
		ilMenuBA.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent me) 
			{
				new FrmCetakBuktiAsuransi();
			}
		});
		
		ilMenuBA.setIconReflect(new ImageIcon("src/GambarApp/cetakAsuransi.png"));
		ilMenuBA.setBounds(474, 271, 135, 201);
		contentPane.add(ilMenuBA);
		
		JLabel lblCetakAsuransi_1 = new JLabel("Cetak Bukti Asuransi");
		lblCetakAsuransi_1.setFont(new Font("Liberation Serif", Font.BOLD, 15));
		lblCetakAsuransi_1.setForeground(new Color(255, 255, 51));
		lblCetakAsuransi_1.setBounds(484, 486, 158, 15);
		contentPane.add(lblCetakAsuransi_1);
		
		IconLabel ilLapKirim = new IconLabel();
		ilLapKirim.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent me)
			{
				new FrmLaporanPengiriman();
			}
		});
		
		ilLapKirim.setIconReflect(new ImageIcon("src/GambarApp/iconLapAWB.png"));
		ilLapKirim.setBounds(53, 537, 144, 155);
		contentPane.add(ilLapKirim);
		
		lblLaporanAwb = new JLabel("Laporan Pengiriman");
		lblLaporanAwb.setFont(new Font("Liberation Serif", Font.BOLD, 15));
		lblLaporanAwb.setForeground(new Color(255, 255, 0));
		lblLaporanAwb.setBounds(50, 696, 161, 15);
		contentPane.add(lblLaporanAwb);
		
		IconLabel ilMenuPendapatan = new IconLabel();
		ilMenuPendapatan.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent me)
			{
				new FrmLaporanPendapatan();
			}
		});
		
		ilMenuPendapatan.setIconReflect(new ImageIcon("src/GambarApp/iconLapPendapatan.png"));
		ilMenuPendapatan.setBounds(290, 520, 158, 177);
		contentPane.add(ilMenuPendapatan);
		
		JLabel lblLaporanPendapatan = new JLabel("Laporan Pendapatan");
		lblLaporanPendapatan.setForeground(new Color(255, 255, 51));
		lblLaporanPendapatan.setFont(new Font("Liberation Serif", Font.BOLD, 15));
		lblLaporanPendapatan.setBounds(298, 696, 158, 15);
		contentPane.add(lblLaporanPendapatan);
		
		lblbgMu = new JLabel("");
		lblbgMu.setIcon(new ImageIcon("src/GambarApp/bgBlur.png"));
		lblbgMu.setBounds(0, 0, 1366, 1024);
		contentPane.add(lblbgMu);
		
		popUp.add(miImage);
		
		fc.setFileFilter(new PictureFilter());
		contentPane.addMouseListener(new MouseAdapter() 
		{
			public void mouseReleased(MouseEvent me)
			{
				if(me.getButton() == 3) //klik kanan
				{
					popUp.show(me.getComponent(),me.getX(),me.getY());
				}
			}
		});
		
		miImage.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent act)
			{
				// TODO Auto-generated method stub
				int buka = fc.showOpenDialog(lblbgMu);
				if(buka == JFileChooser.APPROVE_OPTION)
				{
					String src = fc.getSelectedFile().getPath();
					lblbgMu.setIcon(new ImageIcon(src));
				}
				
			}
		});
		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					com.jtattoo.plaf.mcwin.McWinLookAndFeel.setTheme("Large-Font", "Aplikasi JNE", "");
			        UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
					FrmMenuUtama mu = new FrmMenuUtama();
					mu.setVisible(true);
					mu.setExtendedState(Frame.MAXIMIZED_BOTH);  
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}
