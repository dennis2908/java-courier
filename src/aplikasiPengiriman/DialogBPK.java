package aplikasiPengiriman;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class DialogBPK extends JDialog 
{

	private final JPanel contentPanel = new JPanel();
	JTable tabel;
	String header[] = {"NoBA","No.AWB","Pengirim","Penerima","Tgl Kirim"};
	DefaultTableModel tabelModel; 
	FrmCetakBPK bpk = new FrmCetakBPK();

	
	public DialogBPK() 
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
		scrollPane_1.setBounds(77, 64, 441, 120);
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
				
				String noBA = (String) tabelModel.getValueAt(pilih, 0);
				bpk.txtBA.setText(noBA);
				String NoAWB = (String) tabelModel.getValueAt(pilih, 1);
				bpk.txtNoAWB.setText(NoAWB);
				String Pengirim = (String) tabelModel.getValueAt(pilih, 2);
				bpk.txtPengirim.setText(Pengirim);
				String Penerima = (String) tabelModel.getValueAt(pilih, 3);
				bpk.txtPenerima.setText(Penerima);
				String TglKirim = (String) tabelModel.getValueAt(pilih, 4);
				bpk.txtTglKirim.setText(TglKirim);
				
				dispose();
			}
		});
		tabel.setModel(tabelModel);
		scrollPane_1.setViewportView(tabel);
		
		JLabel lblDialogBPK = new JLabel("");
		lblDialogBPK.setIcon(new ImageIcon("src/GambarApp/bgDialog.jpg"));
		lblDialogBPK.setBounds(0, 0, 583, 257);
		contentPanel.add(lblDialogBPK);
		tampilTabel();
	}
	
	public void tampilTabel()
	{
		tabelModel.getDataVector().removeAllElements();
		tabelModel.fireTableDataChanged();
		try
		{
			Connection konek = Koneksi.getKoneksi();
			Statement state = konek.createStatement();
			String query = "SELECT BAsuransi.NoBA,BAsuransi.NoAWB,Pengirim.Nama,Pengiriman.Nama,Pengiriman.tglKirim FROM Pengirim INNER JOIN Pengiriman ON Pengirim.NoPengirim = Pengiriman.NoPengirim INNER JOIN BAsuransi ON Pengiriman.NoAWB = BAsuransi.NoAWB";
			ResultSet rs = state.executeQuery(query);
			while(rs.next())
			{
				Object obj[] = new Object[5];
				obj[0] = rs.getString(1);
				obj[1] = rs.getString(2);
				obj[2] = rs.getString(3);
				obj[3] = rs.getString(4);
				obj[4] = rs.getString(5);
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