package software.xdev.vaadin.view.cars.brand;

import java.util.List;

import com.vaadin.flow.router.Route;

import software.xdev.vaadin.view.MainLayout;
import software.xdev.vaadin.view.cars.AbstractCarView;
import software.xdev.vaadin.view.cars.Car;


@Route(value = "cars/porsche", layout = MainLayout.class)
public class PorscheView extends AbstractCarView
{
	@Override
	@SuppressWarnings("checkstyle:MagicNumber")
	protected List<Car> getCars()
	{
		return getCarsByBrand(PORSCHE);
	}
}
