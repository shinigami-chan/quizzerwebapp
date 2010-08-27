<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<html:html>
	<head>
		<title><bean:message key="index.title"/></title>
		<link rel="stylesheet" type="text/css" href="<html:rewrite page='/css/design.css'/>"/>
		<html:base />
	</head>
	<body>
		<%@include file="/header.jsp"%>
		<div id="content">
			<h2><bean:message key="index.title"/></h2>
			<div id="welcome">
				<p><bean:message key="index.message"/></p>
			</div>
		</div>
		<%@include file="/footer.jsp"%>
		
	</body>
</html:html>