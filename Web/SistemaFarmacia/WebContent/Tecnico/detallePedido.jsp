<%@page import="beans.DetallePedidoBean"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	<%Vector<DetallePedidoBean> detallePedidos=(Vector<DetallePedidoBean>)request.getAttribute("detallePedidos"); %>
</head>
<body>
<div id="headerinicio"></div>
<div id="logofondo">
	<img src="<%=request.getContextPath()%>/images/logoIni.png" id="logo">
</div>
<div id="cuerpoPedidos">
<div id="header"><h1>DETALLE DE PEDIDO</h1></div>
	
	<div class="table-responsive" style="width: auto;">
  	<div id="tabladetalle">
  		<table class="table table-hover ">
 			<thead>
 			<tr class="btn-info">
 			<th style="width:250px; text-align: center; vertical-align:middle;">Pedido N°<%=detallePedidos.get(0).getPedido().getCodPedido()%></th>
 			<th style="width:250px; text-align: center; vertical-align:middle;">Fecha: <%=detallePedidos.get(0).getPedido().getFechaPedido()%></th>
 			<th style="width:250px; text-align: center; vertical-align:middle;">Hora: <%=detallePedidos.get(0).getPedido().getHoraPedido()%></th>
 			<th style="width:250px; text-align: center; vertical-align:middle;">Estado: <%=detallePedidos.get(0).getPedido().getEstadoPedido().getDescripcion()%></th>
 			</tr>
 			<tr class="btn-info">
 			<th style="width:500px; text-align: left; vertical-align:middle;" colspan="2">Datos del Cliente</th>
 			<th style="width:500px; text-align: left; vertical-align:middle;" colspan="2">
 			Nombre: <%=detallePedidos.get(0).getPedido().getPersona().getNombres()+" "+detallePedidos.get(0).getPedido().getPersona().getApellidoPaterno()+" "+detallePedidos.get(0).getPedido().getPersona().getApellidoMaterno()%>
 			<br>
 			Distrito: <%=detallePedidos.get(0).getPedido().getPersona().getDistrito().getNombre()%>
 			<br>
 			Direccion: <%=detallePedidos.get(0).getPedido().getPersona().getDireccion()%>
 			</th>
 			</tr>
 			<tr class="btn-info">
 			<th style="width:500px; text-align: center; vertical-align:middle;" colspan="2">Medicamento</th>
 			<th style="width:250px; text-align: center; vertical-align:middle;">Precio Unitario x Cantidad</th>
 			<th style="width:250px; text-align: center; vertical-align:middle;">Precio</th>
 			</tr>
 			</thead>
 			<tbody>
 			<% for(int i=0;i<detallePedidos.size();i++){%>
 			<tr>
 			<td style="width:500px; text-align: center; vertical-align:middle;" colspan="2"><%=detallePedidos.get(i).getPresentacionxmedicamento().getAbreviatura()%></td>
 			<td style="width:250px; text-align: center; vertical-align:middle;"><%=detallePedidos.get(i).getPresentacionxmedicamento().getPrecio()+"   x   "+detallePedidos.get(i).getCantidad()%></td>
 			<td style="width:250px; text-align: center; vertical-align:middle;"><%=detallePedidos.get(i).getPrecioTotal()%></td>
 			</tr>
 			<%}%>
 			<tr>
 			<td style="width:500px; text-align: rigth; vertical-align:middle;" colspan="2"></td>
 			<td style="width:250px; text-align: center; vertical-align:middle;"><b>TOTAL</b><br><b>EFECTIVO SOLES</b><br><b>VUELTO</b></td>
 			<td style="width:250px; text-align: left; vertical-align:middle; padding-left: 90px;"><b>S/. <%=detallePedidos.get(0).getPedido().getMontoTotal()%></b><br><b>S/. <%=detallePedidos.get(0).getPedido().getMontoCancelar()%></b><br><b>S/. <%=detallePedidos.get(0).getPedido().getMontoCancelar()-detallePedidos.get(0).getPedido().getMontoTotal()%></b></td>
 			</tr>
 			</tbody>
  		</table>
  		</div>
  		
	</div>
	<button  class="btn-large clickSubmit btn btn-info" type="button" onclick="window.close();">Cerrar Detalle</button>
</div>
</body>
</html>