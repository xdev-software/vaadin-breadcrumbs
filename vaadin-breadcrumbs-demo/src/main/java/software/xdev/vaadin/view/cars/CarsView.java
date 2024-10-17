package software.xdev.vaadin.view.cars;

import java.util.List;

import com.vaadin.flow.router.Route;

import software.xdev.vaadin.view.MainView;


@Route(value = "cars", layout = MainView.class)
public class CarsView extends AbstractCarView
{
	@Override
	protected List<Car> getCars()
	{
		return getAllCars();
	}
}
