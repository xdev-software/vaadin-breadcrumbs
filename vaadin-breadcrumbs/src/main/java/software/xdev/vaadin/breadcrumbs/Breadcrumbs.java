/*
 * Copyright © 2024 XDEV Software (https://xdev.software)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package software.xdev.vaadin.breadcrumbs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;


/**
 * The Breadcrumbs class is a custom component for creating a breadcrumb navigation bar within a Vaadin application.
 * <p>
 * It extends the HorizontalLayout class to arrange breadcrumb links horizontally.
 * </p>
 */
@CssImport("./styles/breadcrumb.css")
public class Breadcrumbs extends HorizontalLayout
{
	private static final String DELIMITER_URL = "/";
	private static final String I18N_PREFIX = "breadcrumb_";
	private static final String BREADCRUMB_CSS = "breadcrumb";
	private static final String BREADCRUMBS_CONTAINER_CSS = "breadcrumbs-container";
	private static final String BREADCRUMB_DISABLED_CSS = "breadcrumb-disabled";
	
	public Breadcrumbs(final String path)
	{
		this();
		this.updatePath(path);
	}
	
	public Breadcrumbs()
	{
		this.addClassName(BREADCRUMBS_CONTAINER_CSS);
	}
	
	/**
	 * Initializes the breadcrumb navigation based on the given URL path. It splits the path, creates breadcrumb items
	 * for each part, and updates the view.
	 *
	 * @param path the navigation path to be represented by the breadcrumbs.
	 */
	public void updatePath(final String path)
	{
		final List<Breadcrumb> breadcrumbs = new ArrayList<>();
		breadcrumbs.add(new Breadcrumb(this.getTranslation(I18N_PREFIX + "home"), DELIMITER_URL));
		
		final List<String> partsList = new ArrayList<>(Arrays.asList(path.split(DELIMITER_URL)));
		
		final StringBuilder hrefBuilder = new StringBuilder();
		for(final String part : partsList)
		{
			if(!part.isEmpty())
			{
				// append to full link
				hrefBuilder.append(DELIMITER_URL).append(part);
				final String title = this.getTranslation(I18N_PREFIX + part.toLowerCase());
				
				breadcrumbs.add(new Breadcrumb(title, hrefBuilder.toString()));
			}
		}
		
		this.updateView(breadcrumbs);
	}
	
	/**
	 * Updates the view with the given list of breadcrumbs. It removes all existing components and adds new anchors for
	 * each breadcrumb.
	 *
	 * @param breadcrumbs the list of breadcrumbs to be displayed.
	 */
	protected void updateView(final List<Breadcrumb> breadcrumbs)
	{
		this.removeAll();
		
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(false);
		for(final Breadcrumb breadcrumb : breadcrumbs)
		{
			final Anchor anchor = new Anchor(breadcrumb.url(), breadcrumb.label());
			anchor.addClassName(BREADCRUMB_CSS);
			
			// remove link and set css if url is empty (e.g. for 'edit' pages)
			if(breadcrumb.url().isEmpty())
			{
				anchor.removeHref();
				anchor.addClassName(BREADCRUMB_DISABLED_CSS);
			}
			
			layout.add(anchor);
		}
		this.add(layout);
	}
	
	public void updateFromCurrentPath()
	{
		this.updatePath(UI.getCurrent().getActiveViewLocation().getPath());
	}
	
	protected record Breadcrumb(String label, String url)
	{
	}
}
