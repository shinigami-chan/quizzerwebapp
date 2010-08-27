<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<html:html>
	<head>
		<title><bean:message key="registrationForm.title" /></title>
		<link rel="stylesheet" type="text/css" href='<html:rewrite page="/css/design.css"/>' />
		<html:javascript formName="registrationDynaForm" />
		<html:base />
	</head>
	<body bgcolor="white">
		<%@include file="/header.jsp"%>
		<div id="content">
			<h2><bean:message key="registrationForm.title" /></h2>
			
			<html:form action="/Register" method="post"
				onsubmit="return validateRegistrationDynaForm(this)">
				<table class="center">
					<tr>
						<td colspan="3">			
							<html:errors property="registrationFailed" header="errors.header"
								footer="errors.footer" prefix="" suffix="" />
						</td>
					</tr>
					<tr>
						<th align="right"><bean:message key="player.firstName" /></th>
						<td align="left"><html:text property="player.firstName" /></td>
						<td><html:errors property="player.firstName"
							header="errors.header" footer="errors.footer" prefix="" suffix="" /></td>
					</tr>
					<tr>
						<th align="right"><bean:message key="player.lastName" /></th>
						<td align="left"><html:text property="player.lastName" /></td>
						<td><html:errors property="player.lastName"
							header="errors.header" footer="errors.footer" prefix="" suffix="" /></td>
					</tr>
					<tr>
						<th align="right"><bean:message key="player.birthDate" /></th>
						<td align="left"><html:text property="birthDateString" /></td>
						<td>&nbsp;[ <bean:message key="datePattern" /> ] <html:errors
							property="birthDateString" header="errors.header"
							footer="errors.footer" prefix="" suffix="" /></td>
					</tr>
					<tr>
						<th align="right"><bean:message key="player.login" /></th>
						<td align="left"><html:text property="player.login" /></td>
						<td><html:errors property="player.login" header="errors.header"
							footer="errors.footer" prefix="" suffix="" /></td>
					</tr>
					<tr>
						<th align="right"><bean:message key="player.password" /></th>
						<td align="left"><html:password property="player.password" /></td>
						<td><html:errors property="player.password"
							header="errors.header" footer="errors.footer" prefix="" suffix="" /></td>
					</tr>
					<tr>
						<th align="right"><bean:message key="player.confirmPassword" /></th>
						<td align="left"><html:password property="confirmPassword" /></td>
						<td><html:errors property="confirmPassword"
							header="errors.header" footer="errors.footer" prefix="" suffix="" /></td>
					</tr>
					<tr>
						<th align="right"><bean:message key="player.email" /></th>
						<td align="left"><html:text property="player.email" /></td>
						<td>&nbsp;[ <bean:message key="emailPattern" /> ] <html:errors
							property="player.email" header="errors.header"
							footer="errors.footer" prefix="" suffix="" /></td>
					</tr>
					<tr>
						<th align="right">
							<html:submit>
								<bean:message key="buttons.submit" />
							</html:submit>
						</th>
						<td align="left">
							<html:reset>
								<bean:message key="buttons.reset" />
							</html:reset>
						</td>
						<td>
							<input name="datePattern" type="hidden" value="<bean:message key="datePattern"/>"/>
						</td>
					</tr>
				</table>
			</html:form>
		</div>
		<%@include file="/footer.jsp"%>	
	</body>
</html:html>
