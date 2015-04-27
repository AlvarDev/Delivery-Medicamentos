<%@page import="beans.UsuarioBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%UsuarioBean usuario=(UsuarioBean) session.getAttribute("usuario");%>
<% if(usuario==null){
	request.setAttribute("mensaje", "No inicio sesión");
	request.getRequestDispatcher("/login.jsp").forward(request, response);
}else{%>
<!DOCTYPE html>
<html>
<head>
	
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Shop Medical S.A.C.</title>
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/icono.ico" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css" type="text/css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/Bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/Bootstrap/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/Bootstrap/css/jquery-ui.min.css">
	<script src="<%=request.getContextPath()%>/Bootstrap/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/Bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/Bootstrap/js/jquery-ui.min.js"></script>
	<script type="text/javascript">
		function hora(){
			var fecha = new Date()
			var hora = fecha.getHours()
			var minuto = fecha.getMinutes()
			var segundo = fecha.getSeconds()
			if (hora < 10) {hora = "0" + hora}
			if (minuto < 10) {minuto = "0" + minuto}
			if (segundo < 10) {segundo = "0" + segundo}
			var horita = hora + ":" + minuto + ":" + segundo
			document.getElementById('hora').firstChild.nodeValue = horita
			tiempo = setTimeout('hora()',1000)
		}
		function inicio(){
			document.write('<span id="hora">')
			document.write ('000000</span>')
			hora()
		}
		function fecha(){
			var f = new Date();
			document.write(f.getDate() + "/" + (f.getMonth()+1) + "/" + f.getFullYear());
		}
	</script>
	
	<%String notificacion=(String) request.getAttribute("notificacion");%>
</head>
<body>
<div id="headerinicio"><div id="usuarioSession">
	<table>
	<tr>
	<td>
	<%if(notificacion.equals("false")){%><img id="notificacion" style="vertical-align: middle;" src="<%=request.getContextPath()%>/images/campana1.png"><%}else{%>
	<button type="button" style="background-color:transparent; border-color:transparent; outline:transparent;" onclick="window.location.href='<%=request.getContextPath()%>/Pedido?accion=PedidosPendientes'">
	<img id="notificacion" style="vertical-align: middle;" src="<%=request.getContextPath()%>/images/campana2.png"></button><%}%>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td><label style="float: right;">Bienvenido: <%=usuario.getPersona().getNombres()+" "+usuario.getPersona().getApellidoPaterno()+" "+usuario.getPersona().getApellidoMaterno()%></label><br>
	<label style="float: right;">Fecha: <script type="text/javascript">fecha()</script>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	Hora: <script type="text/javascript">inicio()</script></label><br>
	<a href="<%=request.getContextPath()%>/Logout" style="float: right; color: purple;">Cerrar Sessi&oacute;n</a>
	</td>
	</tr> 
	</table>
	</div>
	</div>
<div id="logofondo">
	<img src="<%=request.getContextPath()%>/images/logoIni.png" id="logo">
	
</div>
<nav class="navbar navbar-default" role="navigation">
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse"
            data-target=".navbar-ex1-collapse">
      <span class="sr-only"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <div class="navbar-brand">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
  </div>
 
	 <div class="collapse navbar-collapse navbar-ex1-collapse">
    <ul class="nav navbar-nav">
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
          <b id="modulo">&nbsp;&nbsp;&nbsp;PEDIDOS&nbsp;&nbsp;&nbsp;</b><b class="caret"></b>
        </a>
        <ul class="dropdown-menu">
          <li><a href="<%=request.getContextPath()%>/Pedido?accion=PedidosPendientes"><b id="submodulo">Lista de Pedidos Pendientes</b></a></li>
          <li class="divider"></li>
          <li><a href="<%=request.getContextPath()%>/Pedido?accion=PedidosEnProceso"><b id="submodulo">Lista de Pedidos en Proceso</b></a></li>
          <li class="divider"></li>
          <li><a href="<%=request.getContextPath()%>/Pedido?accion=HistorialDePedidos"><b id="submodulo">Historial de Pedidos</b></a></li>
        </ul>
      </li>
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
          <b id="modulo">&nbsp;&nbsp;REPORTES&nbsp;&nbsp;</b><b class="caret"></b>
        </a>
        <ul class="dropdown-menu">
          <li><a href="<%=request.getContextPath()%>/Pedido?accion=RankingMedicamentos"><b id="submodulo">Lista de Medicamentos m&aacute;s Vendidos</b></a></li>
           <li class="divider"></li>
          <li><a href="<%=request.getContextPath()%>/Pedido?accion=ClientesPremium"><b id="submodulo">Lista de Clientes Premium</b></a></li>
          </ul>
          
      </li>
    </ul>
 
    <form class="navbar-form navbar-right" role="search" action="Pedido" method="post">
      <div class="form-group">
        <input type="text" class="form-control" name="txt_busqueda" placeholder="Buscar Pedido" required="required">
      </div>
      <input type="hidden" name="accion" value="buscarPedido">
      <button  class="btn-large clickSubmit btn btn-info" type="submit">Buscar</button>
    </form>
  </div>
</nav>
<div id="cuerpoPedidos">
<%}%>