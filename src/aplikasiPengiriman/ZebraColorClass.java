package aplikasiPengiriman;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class ZebraColorClass extends JTable 
{
	//Konstruktor
	public ZebraColorClass(DefaultTableModel tabelModel)
	{
		super(tabelModel);
	}//Akhir Konstruktor
	
	public Component prepareRenderer(TableCellRenderer tcr,int baris,int kolom)
	{
		Component komponen = super.prepareRenderer(tcr, baris, kolom);
		komponen.setForeground(Color.BLACK);
		if(baris%2 == 0)
		{
			komponen.setBackground(Color.CYAN);
		}
		else
		{
			komponen.setBackground(Color.WHITE);
		}
		return komponen;
	}
}
