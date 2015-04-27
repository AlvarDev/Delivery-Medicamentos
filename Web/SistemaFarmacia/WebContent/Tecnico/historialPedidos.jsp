<%@page import="beans.PedidoBean"%>
<%@page import="java.util.Vector"%>
<%@include file="menuPrincipal.jsp" %>
<%Vector<PedidoBean> historialpedidos=(Vector<PedidoBean>)request.getAttribute("historialpedidos"); %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.dataTables.css">
<script src="<%=request.getContextPath()%>/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script>
<script src="<%=request.getContextPath()%>/js/table.js"></script>
<div id="header"><h1>HISTORIAL DE PEDIDOS</h1></div>
	<div class="table-responsive" style="width: auto;">
  	<div id="tablapedido">
  		<table id="tabla" class="table table-hover table-bordered ">
 			<thead>
 			<tr class="btn-info">
 			<th style="width:150px; text-align: center;" valign="middle">N° Pedido</th>
 			<th style="width:470px; text-align: center;" valign="middle">Cliente</th>
 			<th style="width:150px; text-align: center;" valign="middle">Fecha</th>
 			<th style="width:150px; text-align: center;" valign="middle">Hora</th>
 			<th style="width:140px; text-align: center;" valign="middle">Estado</th>
 			<th style="width:115px; text-align: center;" valign="middle">Detalle</th>
 			</tr>
 			</thead>
 			<tbody>
	 		<% for(int i=0;i<historialpedidos.size();i++){%>	
	 			<tr>
	 			<td style="width:150px; text-align: center; vertical-align:middle;"><%=historialpedidos.get(i).getCodPedido()%></td>
	 			<td style="width:470px; text-align: center; vertical-align:middle;"><%=historialpedidos.get(i).getPersona().getNombres()+" "+historialpedidos.get(i).getPersona().getApellidoPaterno()+" "+historialpedidos.get(i).getPersona().getApellidoMaterno()%></td>
	 			<td style="width:150px; text-align: center; vertical-align:middle;"><%=historialpedidos.get(i).getFechaPedido()%></td>
	 			<td style="width:150px; text-align: center; vertical-align:middle;"><%=historialpedidos.get(i).getHoraPedido()%></td>
	 			<td style="width:140px; text-align: center; vertical-align:middle;"><%=historialpedidos.get(i).getEstadoPedido().getDescripcion()%></td>
	 			<td style="width:115px; text-align: center; vertical-align:middle;">
	 				<form action="Pedido" method="post">
		 				<button type="button" style="background-color:transparent; border-color:transparent; outline:transparent;" onclick="window.open('Pedido?accion=DetallePedido&&id=<%=historialpedidos.get(i).getCodPedido()%>','Detalle Pedido','toolbar=no,location=no,menubar=no,scrollbars=NO,height=695,width=900')">
		 				<img alt="" src="<%=request.getContextPath()%>/images/detalle.png"></button>
		 			</form>
	 			</td>
	 			</tr>
	 		<%}%>
 			</tbody>
  		</table>
  	</div>
	</div>
</div>
</body>
</html>
<%}%>