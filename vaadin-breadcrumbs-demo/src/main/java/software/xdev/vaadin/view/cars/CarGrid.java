package software.xdev.vaadin.view.cars;

import java.util.List;

import com.vaadin.flow.component.grid.Grid;


public class CarGrid extends Grid<Car>
{
	private static final String I18N_PREFIX = "grid_";
	
	public CarGrid(final List<Car> cars)
	{
		this.setItems(cars);
		this.addColumn((car) -> this.getTranslation(I18N_PREFIX + car.fuel()))
			.setHeader(this.getTranslation(I18N_PREFIX + "fuel"));
		this.addColumn(Car::name).setHeader(this.getTranslation(I18N_PREFIX + "type"));
		this.addColumn(Car::hp).setHeader(this.getTranslation(I18N_PREFIX + "hp"));
	}
}
