package software.xdev.vaadin.ui.cars;

import java.util.List;

import com.vaadin.flow.component.grid.Grid;

import software.xdev.vaadin.ui.TranslationKeys;


public class CarGrid extends Grid<Car>
{
	
	public CarGrid(final List<Car> cars)
	{
		this.setItems(cars);
		this.addColumn(car -> this.getTranslation(car.fuelI18N()))
			.setHeader(this.getTranslation(TranslationKeys.FUEL));
		this.addColumn(Car::name).setHeader(this.getTranslation(TranslationKeys.MODEL));
		this.addColumn(Car::hp).setHeader(this.getTranslation(TranslationKeys.HP));
	}
}
