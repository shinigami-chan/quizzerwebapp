<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<html:html>
	<head>
		<title><bean:message key="categoriesView.title" /></title>
		<link rel="stylesheet" type="text/css" href='<html:rewrite page="/css/design.css"/>'/>
		<html:base />
	</head>
	<body bgcolor="white">
		<%@include file="/header.jsp"%>
		<div id="content">
		
			<h2><bean:message key="categoriesView.title" /></h2>
			
			<table class="center">
				<thead>
					<tr>
						<th><bean:message key="number"/></th>
						<th><bean:message key="category.title"/></th>
						<th><bean:message key="play"/></th>
					</tr>				
				</thead>
				
				<logic:iterate id="category" indexId="i" name="CATEGORIES" scope="request">
					<tr>
						<td align="right">${i+1}</td>
						<td align="left">
							<html:link action="/ViewCategory.do?ID=${category.id}">
								<bean:write name="category" property="title"/>
							</html:link>
						</td>
						<td align="left">
							<html:img page="/images/play.png"/>
							<html:link action="/PlayCategory.do?ID=${category.id}">
								<bean:message key="play"/>
							</html:link>
						</td>
					</tr>
				</logic:iterate>				
			</table>

		</div>
		<%@include file="/footer.jsp"%>
	</body>
</html:html>
