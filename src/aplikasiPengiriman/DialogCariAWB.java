package aplikasiPengiriman;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class DialogCariAWB extends JDialog 
{

	private final JPanel contentPanel = new JPanel();
	JTable tabel;
	String header[] = {"No.AWB","Pengirim","Penerima","Alamat"};
	DefaultTableModel tabelModel; 
	FrmCetakKirimBarang kb = new FrmCetakKirimBarang();

	
	public DialogCariAWB() 
	{
		setBounds(100, 100, 587, 284);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setVisible(true);
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(87, 47, 3, 3);
		contentPanel.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(38, 27, 507, 180);
		contentPanel.add(scrollPane_1);
		
		tabelModel = new DefaultTableModel(null,header);
		tabel = new JTable();
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
				
				String AWB = (String) tabelModel.getValueAt(pilih, 0);
				kb.txtNoAWB.setText(AWB);
				
				dispose();
				kb.tabbedPane.setSelectedIndex(1);
				kb.txtNoAWB.requestFocus();
			}
		});
		tabel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabel.setModel(tabelModel);
		scrollPane_1.setViewportView(tabel);
		
		JLabel lblBgDialog = new JLabel("");
		lblBgDialog.setIcon(new ImageIcon("src/GambarApp/bgDialog.jpg"));
		lblBgDialog.setBounds(0, 0, 583, 257);
		contentPanel.add(lblBgDialog);
		tampilTabel();
		setLebarKolom();
	}
	
	public void tampilTabel()
	{
		tabelModel.getDataVector().removeAllElements();
		tabelModel.fireTableDataChanged();
		try
		{
			Connection konek = Koneksi.getKoneksi();
			Statement state = konek.createStatement();
			String query = "SELECT Pengiriman.NoAWB,Pengirim.Nama,Pengiriman.Nama,Pengiriman.Alamat FROM Pengiriman INNER JOIN Pengirim ON Pengirim.NoPengirim = Pengiriman.NoPengirim";
			ResultSet rs = state.executeQuery(query);
			while(rs.next())
			{
				Object obj[] = new Object[4];
				obj[0] = rs.getString(1);
				obj[1] = rs.getString(2);
				obj[2] = rs.getString(3);
				obj[3] = rs.getString(4);
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
}