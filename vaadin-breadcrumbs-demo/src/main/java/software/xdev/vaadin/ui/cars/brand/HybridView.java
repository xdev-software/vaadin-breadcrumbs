package software.xdev.vaadin.ui.cars.brand;

import static software.xdev.vaadin.ui.TranslationKeys.HYBRID;

import java.util.List;

import com.vaadin.flow.router.Route;

import software.xdev.vaadin.ui.cars.AbstractCarView;
import software.xdev.vaadin.ui.cars.Car;


@Route("cars/hybrid")
public class HybridView extends AbstractCarView
{
	@Override
	@SuppressWarnings("checkstyle:MagicNumber")
	protected List<Car> getCars()
	{
		return getCarsByFuel(HYBRID);
	}
}
