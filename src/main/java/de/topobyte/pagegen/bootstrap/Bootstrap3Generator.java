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

public class Bootstrap3Generator extends BaseFileGenerator
{

	private CacheBuster buster;

	public Bootstrap3Generator(WebPath path, CacheBuster buster)
	{
		super(path);
		this.buster = buster;
	}

	private static String[] cssPaths = new String[] {
			"client/bootstrap/css/bootstrap.min.css",
			"client/bootstrap/css/bootstrap-theme.min.css" };

	private static String[] jsPaths = new String[] {
			"client/jquery/jquery.min.js",
			"client/bootstrap/js/bootstrap.min.js" };

	public static void setupHeader(CacheBuster buster, Head head)
	{
		Meta meta = head.ac(HTML.meta());
		meta.attr("http-equiv", "content-type");
		meta.attr("content", "text/html; charset=utf-8");

		meta = head.ac(HTML.meta());
		meta.attr("name", "viewport");
		meta.attr("content", "width=device-width, initial-scale=1");

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

		content = new Div("container");
		builder.getBody().appendChild(content);
	}

}
