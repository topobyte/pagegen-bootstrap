// Copyright 2017 Sebastian Kuerten
//
// This file is part of pagegen-bootstrap.
//
// pagegen-bootstrap is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// pagegen-bootstrap is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with pagegen-bootstrap. If not, see <http://www.gnu.org/licenses/>.

package de.topobyte.pagegen.bootstrap;

import static de.topobyte.jsoup.ElementBuilder.create;
import static de.topobyte.jsoup.ElementBuilder.script;
import static de.topobyte.jsoup.ElementBuilder.styleSheet;

import java.io.IOException;

import org.jsoup.nodes.Element;

import de.topobyte.jsoup.FaviconUtil;
import de.topobyte.jsoup.components.Div;
import de.topobyte.pagegen.core.BaseFileGenerator;
import de.topobyte.pagegen.core.Context;
import de.topobyte.pagegen.core.LinkResolver;
import de.topobyte.webpaths.WebPath;
import de.topobyte.webpaths.WebPaths;

public class BootstrapGenerator extends BaseFileGenerator
{

	public BootstrapGenerator(Context context, WebPath path)
	{
		super(context, path);
	}

	private static String[] cssPaths = new String[] {
			"bower/bootstrap/styles/bootstrap.css",
			"bower/bootstrap/styles/bootstrap-theme.css", "custom.css",
			"sticky-footer-navbar.css" };

	private static String[] jsPaths = new String[] {
			"bower/jquery/js/jquery.min.js", "bower/bootstrap/js/collapse.js",
			"bower/bootstrap/js/transition.js",
			"bower/bootstrap/js/dropdown.js" };

	public static void setupHeader(LinkResolver resolver, Element head)
	{
		head.appendChild(create("meta", "http-equiv", "content-type",
				"content", "text/html; charset=utf-8"));
		head.appendChild(create("meta", "name", "viewport", "content",
				"width=device-width, initial-scale=1"));

		for (String cssPath : cssPaths) {
			head.appendChild(styleSheet(resolver.getLink(WebPaths.get(cssPath))));
		}

		for (String jsPath : jsPaths) {
			head.appendChild(script(resolver.getLink(WebPaths.get(jsPath))));
		}
	}

	@Override
	public void generate() throws IOException
	{
		Element head = builder.getHead();

		setupHeader(this, head);

		String faviconPath = getLink(context.getFavIcon());
		FaviconUtil.addToHeader(head, faviconPath);

		/*
		 * Main Content
		 */

		content = new Div("container");
		builder.getBody().appendChild(content);
	}

}
