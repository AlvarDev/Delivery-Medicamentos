<%@page import="beans.PedidoBean"%>
<%@page import="java.util.Vector"%>
<%@include file="menuPrincipal.jsp" %>
<%Vector<PedidoBean> pedidosProceso=(Vector<PedidoBean>)request.getAttribute("pedidosProceso"); %>
<div id="header"><h1>PEDIDOS EN PROCESO</h1></div>
	<div class="table-responsive" style="width: auto;">
  	<div id="tablapedido">
  		<table class="table table-hover table-bordered ">
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
 			<% for(int i=0;i<pedidosProceso.size();i++){%>
	 			<tr>
		 			<td style="width:150px; text-align: center; vertical-align:middle;"><%=pedidosProceso.get(i).getCodPedido()%></td>
		 			<td style="width:470px; text-align: center; vertical-align:middle;"><%=pedidosProceso.get(i).getPersona().getNombres()+" "+pedidosProceso.get(i).getPersona().getApellidoPaterno()+" "+pedidosProceso.get(i).getPersona().getApellidoMaterno()%></td>
		 			<td style="width:150px; text-align: center; vertical-align:middle;"><%=pedidosProceso.get(i).getFechaPedido()%></td>
		 			<td style="width:150px; text-align: center; vertical-align:middle;"><%=pedidosProceso.get(i).getHoraPedido()%></td>
		 			<td style="width:130px; text-align: center; vertical-align:middle;">
		 				<form action="Pedido" method="post">
		 				<input name="accion" type="hidden" value="finalizarPedido">
		 				<input type="hidden" name="idpedido" value="<%=pedidosProceso.get(i).getCodPedido()%>">
		 				<select class="selectpicker" size="1" name="slt_estado" onchange="this.form.submit()">
						    <option style="display: none;">En proceso</option>
						    <option value="4">Terminado</option>
						    <option value="3">Cancelado</option>
						</select>
						</form>
		 			</td>
		 			<td style="width:115px; text-align: center; vertical-align:middle;">
		 				<form action="Pedido" method="post">
		 					<button type="button" style="background-color:transparent; border-color:transparent; outline:transparent;" onclick="window.open('Pedido?accion=DetallePedido&&id=<%=pedidosProceso.get(i).getCodPedido()%>','Detalle Pedido','toolbar=no,location=no,menubar=no,scrollbars=NO,height=695,width=900')">
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