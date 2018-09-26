package aplikasiPengiriman;


import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class FrmEntryWilayah extends JFrame
{
	private JPanel contentPane;
	private JTextField txtKode;
	private JTextField txtKotamadya;
	private JLabel lblKotamadya;
	private JLabel lblKode;
	private JTextField txtPropinsi;
	private JButton btnUbah;
	private JButton btnSimpan;
	private JButton btnHapus;
	private JScrollPane scrollPane;
	private JTable tabel;
	DefaultTableModel tabelModel;
	String header[] = {"Kode","Kotamadya","Propinsi"};
	private JLabel lblPropinsi;
	private JLabel lblWallWilayah;
	private JButton btnRefresh;
	private JLabel lblIconMap;

	/**
	 * Create the frame.
	 */
	public FrmEntryWilayah() 
	{
		super("Wilayah");
		setResizable(false);
		setBounds(100, 100, 634, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblKode = new JLabel("Kode : ");
		lblKode.setBounds(12, 12, 70, 15);
		contentPane.add(lblKode);
		
		txtKode = new JTextField();
		txtKode.setBounds(115, 10, 200, 19);
		contentPane.add(txtKode);
		txtKode.setColumns(10);
		
		lblKotamadya = new JLabel("Kotamadya : ");
		lblKotamadya.setBounds(12, 48, 93, 15);
		contentPane.add(lblKotamadya);
		
		txtKotamadya = new JTextField();
		txtKotamadya.setBounds(115, 49, 328, 19);
		contentPane.add(txtKotamadya);
		txtKotamadya.setColumns(10);
		
		lblPropinsi = new JLabel("Propinsi : ");
		lblPropinsi.setBounds(12, 84, 93, 15);
		contentPane.add(lblPropinsi);
		
		txtPropinsi = new JTextField();
		txtPropinsi.setBounds(115, 80, 297, 19);
		contentPane.add(txtPropinsi);
		txtPropinsi.setColumns(10);
		
		btnSimpan = new JButton("Simpan");
		btnSimpan.setIcon(new ImageIcon("src/GambarApp/Simpan.png"));
		btnSimpan.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent me)
			{
				try
				{
					Connection konek = Koneksi.getKoneksi();
					String query = "INSERT INTO Wilayah VALUES(?,?,?)";
					PreparedStatement prepare = konek.prepareStatement(query);
					
	
					prepare.setString(1,txtKode.getText());
					prepare.setString(2,txtKotamadya.getText());
					prepare.setString(3,txtPropinsi.getText());
					
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
					txtKode.setText("");
					txtKotamadya.setText("");
					txtPropinsi.setText("");
					txtKode.requestFocus();
				}
			}
		});
		btnSimpan.setBounds(58, 308, 117, 38);
		contentPane.add(btnSimpan);
		
		btnUbah = new JButton("Ubah");
		btnUbah.setIcon(new ImageIcon("src/GambarApp/Edit.png"));
		btnUbah.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act) 
			{
				try
				{
					Connection konek = Koneksi.getKoneksi();
					String query = "UPDATE Wilayah SET Kotamadya = ?, Propinsi = ? WHERE Kode = ? ";
					PreparedStatement prepare = konek.prepareStatement(query);
					
					prepare.setString(1,txtKotamadya.getText());
					prepare.setString(2,txtPropinsi.getText());
					prepare.setString(3,txtKode.getText());
					
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
					txtKode.setText("");
					txtKotamadya.setText("");
					txtPropinsi.setText("");
					txtKode.requestFocus();
				}
			}
		});
		btnUbah.setBounds(201, 308, 117, 38);
		contentPane.add(btnUbah);
		
		btnHapus = new JButton("Hapus");
		btnHapus.setIcon(new ImageIcon("src/GambarApp/Hapus.png"));
		btnHapus.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent me)
			{
				try
				{
					Connection konek = Koneksi.getKoneksi();
					String query = "DELETE FROM Wilayah WHERE Kode = ? ";
					PreparedStatement prepare = konek.prepareStatement(query);
				
					prepare.setString(1,txtKode.getText());
					
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
					txtKode.setText("");
					txtKotamadya.setText("");
					txtPropinsi.setText("");
					txtKode.requestFocus();
				}
			}
		});
		btnHapus.setBounds(358, 308, 117, 38);
		contentPane.add(btnHapus);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(115, 150, 484, 139);
		contentPane.add(scrollPane);
		
		tabelModel = new DefaultTableModel(null,header);
		tabel = new JTable();
		tabel.setModel(tabelModel);
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
				
				String kode = (String) tabelModel.getValueAt(pilih, 0);
				txtKode.setText(kode);
				String kotamadya = (String) tabelModel.getValueAt(pilih, 1);
				txtKotamadya.setText(kotamadya);
				String propinsi = (String) tabelModel.getValueAt(pilih, 2);
				txtPropinsi.setText(propinsi);
			}
		});
		//tabel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(tabel);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setIcon(new ImageIcon("src/GambarApp/Refresh.png"));
		btnRefresh.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act) 
			{
				txtKode.setText("");
				txtKotamadya.setText("");
				txtPropinsi.setText("");
				txtKode.requestFocus();
			}
		});
		btnRefresh.setBounds(501, 308, 117, 38);
		contentPane.add(btnRefresh);
		
		lblIconMap = new JLabel("");
		lblIconMap.setIcon(new ImageIcon("src/GambarApp/mapWilayah.png"));
		lblIconMap.setBounds(472, 12, 133, 116);
		contentPane.add(lblIconMap);
		
		lblWallWilayah = new JLabel("");
		lblWallWilayah.setIcon(new ImageIcon("src/GambarApp/wallWilayah.png"));
		lblWallWilayah.setBounds(0, 0, 654, 373);
		contentPane.add(lblWallWilayah);
		setLocationRelativeTo(null);
		setVisible(true);
		
		setLebarKolom();
		tampilTabel();
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
			String query = "SELECT * FROM Wilayah";
			ResultSet rs = state.executeQuery(query);
			while(rs.next())
			{
				Object obj[] = new Object[3];
		
				obj[0] = rs.getString(1);
				obj[1] = rs.getString(2);
				obj[2] = rs.getString(3);
				
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
