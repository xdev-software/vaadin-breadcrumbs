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
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;


/**
 * Represents a horizontal breadcrumb navigation bar
 */
@SuppressWarnings("java:S1948")
@CssImport("./styles/breadcrumb.css")
public class Breadcrumbs extends HorizontalLayout
{
	protected static final String DELIMITER_URL = "/";
	
	protected static final String BREADCRUMB_CLASS = "breadcrumb";
	protected static final String BREADCRUMBS_CONTAINER_CLASS = "breadcrumbs-container";
	
	protected String homeBreadcrumbName = "Home";
	protected BiFunction<List<String>, String, String> breadcrumbNameResolver =
		(parts, part) -> part;
	
	public Breadcrumbs(final String path)
	{
		this();
		this.updatePath(path);
	}
	
	public Breadcrumbs()
	{
		this.addClassName(BREADCRUMBS_CONTAINER_CLASS);
	}
	
	public Breadcrumbs withHomeBreadcrumbName(final String name)
	{
		this.homeBreadcrumbName = Objects.requireNonNull(name);
		return this;
	}
	
	public Breadcrumbs withBreadcrumbNameResolver(final BiFunction<List<String>, String, String> breadcrumbNameResolver)
	{
		this.breadcrumbNameResolver = Objects.requireNonNull(breadcrumbNameResolver);
		return this;
	}
	
	/**
	 * Initializes the breadcrumb navigation based on the given URL path. It splits the path, creates breadcrumb items
	 * for each part, and updates the view.
	 *
	 * @param path the navigation path to be represented by the breadcrumbs.
	 */
	public void updatePath(final String path)
	{
		this.updateView(
			Stream.concat(
				Stream.of(new Breadcrumb(this.homeBreadcrumbName, DELIMITER_URL)),
				this.buildBreadcrumbs(Arrays.asList(path.split(DELIMITER_URL))).stream()
			).toList()
		);
	}
	
	protected List<Breadcrumb> buildBreadcrumbs(final List<String> parts)
	{
		final List<String> currentPaths = new ArrayList<>();
		
		return parts.stream()
			.filter(s -> !s.isEmpty())
			.map(part -> {
				currentPaths.add(part);
				return new Breadcrumb(
					this.breadcrumbNameResolver.apply(
						// Use unmodifiable so that it can be accidentally modified by user
						Collections.unmodifiableList(currentPaths),
						part),
					String.join(DELIMITER_URL, currentPaths));
			})
			.toList();
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
			anchor.addClassName(BREADCRUMB_CLASS);
			
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

