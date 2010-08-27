<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<div id="header">
	<div class="container">
	
		<div id="topbar">
			<div id="logo">
				<h1>Quizzer !</h1>
			</div>
			<div id="languages">
				<ul>
					<li><html:link action="/ChangeLanguage.do?lang=fr"><html:img page="/images/fr.png"/><bean:message key="header.language.french"/></html:link></li>
					<li><html:link action="/ChangeLanguage.do?lang=en"><html:img page="/images/en.png"/><bean:message key="header.language.english"/></html:link></li>
				</ul>
			</div>
			
			<div id="login">
				<ul>
					<logic:notPresent name="PLAYER" scope="session">
						<li><html:link forward="Registration Page"><html:img page="/images/user.png"/><bean:message key="header.register"/></html:link></li>
						<li><html:link forward="Login Page"><html:img page="/images/key.png"/><bean:message key="header.login"/></html:link></li>
					</logic:notPresent>	
					
					<logic:present name="PLAYER" scope="session">
						<li><html:link forward="Results Page"><html:img page="/images/results.png"/><bean:message key="header.my.results"/></html:link></li>
						<li><html:link action="/Logout"><html:img page="/images/user.png"/>
							<bean:message key="header.logout" arg0="${PLAYER.login}"/>
							</html:link>
						</li>						
					</logic:present>
				</ul>	
			</div>
		</div>
	</div>
	<div id="navbar">
		<ul>
			<li><html:link forward="Home Page"><html:img page="/images/home.png"/><bean:message key="header.home"/></html:link></li>
			<li><html:link forward="Categories Page"><bean:message key="header.categories"/></html:link></li>
				<%/*<li><html:link forward="Rankings Page"><bean:message key="header.rankings"/></html:link></li>
			*/%>
			<li><html:link forward="Add Category Page"><html:img page="/images/add.png"/><bean:message key="header.addCategory"/></html:link></li>
			<li><html:link forward="Add Question Page"><html:img page="/images/add.png"/><bean:message key="header.addQuestion"/></html:link></li>
		</ul>
	</div>

</div>

