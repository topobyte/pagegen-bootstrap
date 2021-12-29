// Copyright 2020 Sebastian Kuerten
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

import static de.topobyte.jsoup.ElementBuilder.script;
import static de.topobyte.jsoup.ElementBuilder.styleSheet;

import java.io.IOException;

import de.topobyte.jsoup.HTML;
import de.topobyte.jsoup.components.Div;
import de.topobyte.jsoup.components.Head;
import de.topobyte.jsoup.components.Meta;
import de.topobyte.pagegen.core.BaseFileGenerator;
import de.topobyte.webgun.util.CacheBuster;
import de.topobyte.webpaths.WebPath;

public class Bootstrap4Generator extends BaseFileGenerator
{

	private CacheBuster buster;
	private boolean fluid = false;

	public Bootstrap4Generator(WebPath path, CacheBuster buster, boolean fluid)
	{
		super(path);
		this.buster = buster;
		this.fluid = fluid;
	}

	public boolean isFluid()
	{
		return fluid;
	}

	public void setFluid(boolean fluid)
	{
		this.fluid = fluid;
	}

	private static String[] cssPaths = new String[] {
			"client/bootstrap/css/bootstrap.min.css" };

	private static String[] jsPaths = new String[] {
			"client/jquery/jquery.min.js", "client/popper.js/umd/popper.min.js",
			"client/bootstrap/js/bootstrap.min.js" };

	public static void setupHeader(CacheBuster buster, Head head)
	{
		Meta meta = head.ac(HTML.meta());
		meta.attr("http-equiv", "content-type");
		meta.attr("content", "text/html; charset=utf-8");

		meta = head.ac(HTML.meta());
		meta.attr("name", "viewport");
		meta.attr("content",
				"width=device-width, initial-scale=1, shrink-to-fit=no");

		for (String cssPath : cssPaths) {
			head.appendChild(styleSheet(buster.resolve(cssPath)));
		}

		for (String jsPath : jsPaths) {
			head.appendChild(script(buster.resolve(jsPath)));
		}
	}

	@Override
	public void generate() throws IOException
	{
		Head head = builder.getHead();

		setupHeader(buster, head);

		/*
		 * Main Content
		 */

		if (fluid) {
			content = new Div("container-fluid");
		} else {
			content = new Div("container");
		}

		builder.getBody().appendChild(content);
	}

}
