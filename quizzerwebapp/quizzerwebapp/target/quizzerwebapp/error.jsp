<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<html:html>
	<head>
		<title><bean:message key="error.title"/></title>
		<link rel="stylesheet" type="text/css" href="<html:rewrite page='/css/design.css'/>"/>
		<html:base />
	</head>
	<body>
		<%@include file="/header.jsp"%>
		<div id="content">
			<h2><bean:message key="error.title"/></h2>
			<p><html:img page="/images/error.png"/><bean:message key="error.message"/></p>
			<html:errors/>
			
			<div id="navbar">
				<ul>
					<li><html:link forward="Home Page"><html:img page="/images/home.png"/><bean:message key="header.home"/></html:link></li>
					<li><html:link forward="Add Category Page"><html:img page="/images/add.png"/><bean:message key="header.addCategory"/></html:link></li>
					<li><html:link forward="Add Question Page"><html:img page="/images/add.png"/><bean:message key="header.addQuestion"/></html:link></li>
				</ul>
			</div>
		</div>
		<%@include file="/footer.jsp"%>
		
	</body>
</html:html>