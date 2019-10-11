package br.com.MBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.pie.PieChartDataSet;

import br.com.DAO.itensDAO;
import br.com.entities.Itens;

@ManagedBean
@ViewScoped
public class GraficoMB {

	itensDAO iDAO = new itensDAO();
	List<Itens> itensL = new ArrayList<Itens>();

	PieChartModel pieModel;

	public GraficoMB() {
		createPieModel();
	}

	public void createPieModel() {

		itensL = iDAO.listarTodos();

		pieModel = new PieChartModel();

		for (Itens i : itensL) {
			pieModel.set(i.getDescricao(), i.getEstoque_at());
		}
		
		pieModel.setLegendPosition("w");
		pieModel.setShadow(false);
		pieModel.setDiameter(500);
		pieModel.setShowDataLabels(true);
	}

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}
}
