<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<html:html>
	<head>
		<title><bean:message key="categoryView.title" arg0="${CATEGORY.title}"/></title>
		<link rel="stylesheet" type="text/css" href='<html:rewrite page="/css/design.css"/>'/>
		<html:base />
	</head>
	<body bgcolor="white">
		<%@include file="/header.jsp"%>
		<div id="content">
		
			<h2><html:img page="/images/cat.png"/>
				<bean:message key="categoryView.title" arg0="${CATEGORY.title}"/>
				&lt; 
				<html:link action="/PlayCategory.do?ID=${CATEGORY.id}">
					<bean:message key="play"/>
				</html:link> 
				&gt;
			</h2>
			
			<table class="center">
					<tr>
						<th align="right"><html:img page="/images/up.png"/><bean:message key="category.numberOfPros"/></th>
						<td align="left"><bean:write name="CATEGORY" property="numberOfPros"/></td>
					</tr>
					<tr>
						<th align="right"><html:img page="/images/down.png"/><bean:message key="category.numberOfCons"/></th>
						<td align="left"><bean:write name="CATEGORY" property="numberOfCons"/></td>
					</tr>
					<logic:present name="PLAYER" scope="session">
						<tr>
							<th align="right"><html:img page="/images/user.png"/><bean:message key="categoryView.your.vote"/></th>
							<td align="left">
								<logic:present name="MY_VOTE_IS_CON" scope="request">
									<logic:equal name="MY_VOTE_IS_CON" value="false">
										<html:img page="/images/up.png"/>
									</logic:equal>
									<logic:notEqual name="MY_VOTE_IS_CON" value="false">
										<html:img page="/images/down.png"/>
									</logic:notEqual>
								</logic:present>
								<logic:notPresent name="MY_VOTE_IS_CON" scope="request">
									<bean:message key="categoryView.not.voted.yet" />
								</logic:notPresent>
							</td>
						</tr>
						<tr>
							<th align="right"><bean:message key="categoryView.change.vote"/></th>
							<td align="left">
								<html:form  action="/ChangeVoteCategory">
										<html:hidden property="idCategory" value="${CATEGORY.id }"/>
										<html:radio  property="isCon" value="false"  >
											<html:img page="/images/up.png"/>
										</html:radio>
										<html:radio property="isCon" value="true">
											<html:img page="/images/down.png"/>
										</html:radio>
										<html:submit><bean:message key="buttons.vote"/></html:submit>
								</html:form>
							</td>
						</tr>
					</logic:present>
					<logic:notPresent name="PLAYER" scope="session">
						<tr>
							<td colspan="2">
								<span class="warn">
									<bean:message key="categoryView.must.login.to.vote"/>
									<html:link forward="Login Page">
										<bean:message key="header.login"/>
										<html:img page="/images/key.png"/>
									</html:link>
								</span>
							</td>
						</tr>
					</logic:notPresent>
			</table>

		</div>
		<%@include file="/footer.jsp"%>
	</body>
</html:html>
