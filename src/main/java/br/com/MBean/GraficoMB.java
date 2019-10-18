package br.com.MBean;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.charts.ChartData;

import org.primefaces.model.charts.pie.PieChartDataSet;

import br.com.DAO.entradaDAO;
import br.com.DAO.itensDAO;
import br.com.entities.Entrada;
import br.com.entities.Itens;

@ManagedBean
@ViewScoped
public class GraficoMB {

	itensDAO iDAO = new itensDAO();
	List<Itens> itensL = new ArrayList<Itens>();

	PieChartModel pieModel;
	LineChartModel lineModel = new LineChartModel();
	int Item;
	entradaDAO eDAO = new entradaDAO();
	List<Entrada> en = new ArrayList<Entrada>();

	public GraficoMB() {
		createPieModel();
	}

	public void createPieModel() {

		itensL = iDAO.listarTodosOrderDesc();

		pieModel = new PieChartModel();

		for (Itens i : itensL) {
			pieModel.set(i.getDescricao(), i.getEstoque_at());
		}

		pieModel.setShadow(false);
		pieModel.setDiameter(500);
		pieModel.setShowDataLabels(true);
	}

	public void createLine() {
		lineModel = new LineChartModel();
		LineChartSeries linha1 = new LineChartSeries();
		linha1.setLabel("Entrada");
		
		en = eDAO.listarEntrada(Item);

		for(Entrada ent : en) {
			SimpleDateFormat fmt = new SimpleDateFormat("dd/MMM/yyyy");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date d = fmt.parse(ent.getDia());
				String dia = sdf.format(d);
				linha1.set(dia, ent.getEntrada());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		lineModel.addSeries(linha1);

		lineModel.setTitle("Zoom for Details");
		lineModel.setZoom(true);
		lineModel.getAxis(AxisType.Y).setLabel("Values");
		DateAxis axis = new DateAxis("Dates");
		axis.setTickAngle(-50);
		axis.setTickFormat("%d-%b, %y");

		lineModel.getAxes().put(AxisType.X, axis);
	}

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}

	public itensDAO getiDAO() {
		return iDAO;
	}

	public void setiDAO(itensDAO iDAO) {
		this.iDAO = iDAO;
	}

	public List<Itens> getItensL() {
		return itensL;
	}

	public void setItensL(List<Itens> itensL) {
		this.itensL = itensL;
	}

	public LineChartModel getLineModel() {
		return lineModel;
	}

	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
	}

	public int getItem() {
		return Item;
	}

	public void setItem(int item) {
		Item = item;
	}

}
