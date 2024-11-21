package software.xdev.vaadin.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.Route;


@Route(value = "", layout = MainLayout.class)
public class HomeView extends VerticalLayout
{
	public HomeView()
	{
		this.add(new Button("Toggle theme", ev -> {
			final Element uiElement = UI.getCurrent().getElement();
			uiElement.setAttribute("theme", "dark".equals(uiElement.getAttribute("theme")) ? "" : "dark");
		}));
	}
}
