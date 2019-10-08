package br.com.MBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

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
		ChartData data = new ChartData();

		PieChartDataSet dataSet = new PieChartDataSet();

		List<Number> values = new ArrayList<>();
		for (Itens i : itensL) {
			values.add(i.getEstoque_at());
		}

		dataSet.setData(values);

		List<String> bgColors = new ArrayList<>();
		int hsl = 0;
		for (Itens i : itensL) {
			bgColors.add("hsl(" + hsl + ", 100%, 50%)");
			
			hsl = hsl + 10;
		}

		dataSet.setBackgroundColor(bgColors);

		data.addChartDataSet(dataSet);
		List<String> labels = new ArrayList<>();
		for (Itens i : itensL) {
			labels.add(i.getDescricao());
		}
		data.setLabels(labels);

		pieModel.setData(data);
	}

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}
}
