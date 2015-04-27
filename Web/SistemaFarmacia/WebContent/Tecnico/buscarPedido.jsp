<%@page import="beans.PedidoBean"%>
<%@page import="java.util.Vector"%>
<%@include file="menuPrincipal.jsp" %>
<%PedidoBean pedido=(PedidoBean)request.getAttribute("pedido"); 
String titulo=(String)request.getAttribute("titulo");%>
<div id="header"><h1><%=titulo%></h1></div>
	<%if(pedido!=null){%>
	<div class="table-responsive" style="width: auto;">
  	<div id="tablapedido">
  		<%if(pedido.getEstadoPedido().getDescripcion().equals("Pendiente")){%>
  			<table class="table table-hover table-bordered">
 			<thead>
 			<tr class="btn-info">
 			<th style="width:150px; text-align: center;" valign="middle">N° Pedido</th>
 			<th style="width:470px; text-align: center;" valign="middle">Cliente</th>
 			<th style="width:150px; text-align: center;" valign="middle">Fecha</th>
 			<th style="width:150px; text-align: center;" valign="middle">Hora</th>
 			<th style="width:100px; text-align: center;" valign="middle">Detalle</th>
 			<th style="width:155px; text-align: center;" valign="middle">&nbsp;&nbsp;</th>
 			</tr>
 			</thead>
 			<tbody>
 				<tr>
		 			<td valign="bottom" style="width:150px; text-align: center; vertical-align:middle;"><%=pedido.getCodPedido()%></td>
		 			<td style="width:470px; text-align: center; vertical-align:middle;"><%=pedido.getPersona().getNombres()+" "+pedido.getPersona().getApellidoPaterno()+" "+pedido.getPersona().getApellidoMaterno()%></td>
		 			<td style="width:150px; text-align: center; vertical-align:middle;"><%=pedido.getFechaPedido()%></td>
		 			<td style="width:150px; text-align: center; vertical-align:middle;"><%=pedido.getHoraPedido()%></td>
		 			<td style="width:100px; text-align: center; vertical-align:middle;">
		 				<form action="Pedido" method="post">
		 					<button type="button" style="background-color:transparent; border-color:transparent; outline:transparent;" onclick="window.open('Pedido?accion=DetallePedido&&id=<%=pedido.getCodPedido()%>','Detalle Pedido','toolbar=no,location=no,menubar=no,scrollbars=NO,height=695,width=900')">
		 					<img alt="" src="<%=request.getContextPath()%>/images/detalle.png"></button>
		 				</form>
		 			</td>
		 			<td style="width:155px; text-align: center; vertical-align:middle;">
			 			<form action="Pedido" method="post">
			 				<input type="hidden" name="idpedido" value="<%=pedido.getCodPedido()%>">
			 				<input type="hidden" name="accion" value="generarBoleta">
			 				<button  class="btn-large clickSubmit btn btn-primary" type="submit">Generar Boleta</button>
			 			</form>
		 			</td>
	 			</tr>
 			</tbody>
  			</table>
  		<%}else if(pedido.getEstadoPedido().getDescripcion().equals("En Proceso")){%>
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
 				<tr>
		 			<td style="width:150px; text-align: center; vertical-align:middle;"><%=pedido.getCodPedido()%></td>
		 			<td style="width:470px; text-align: center; vertical-align:middle;"><%=pedido.getPersona().getNombres()+" "+pedido.getPersona().getApellidoPaterno()+" "+pedido.getPersona().getApellidoMaterno()%></td>
		 			<td style="width:150px; text-align: center; vertical-align:middle;"><%=pedido.getFechaPedido()%></td>
		 			<td style="width:150px; text-align: center; vertical-align:middle;"><%=pedido.getHoraPedido()%></td>
		 			<td style="width:130px; text-align: center; vertical-align:middle;">
		 				<form action="Pedido" method="post">
		 				<input name="accion" type="hidden" value="finalizarPedido">
		 				<input type="hidden" name="idpedido" value="<%=pedido.getCodPedido()%>">
		 				<select class="selectpicker" size="1" name="slt_estado" onchange="this.form.submit()">
						    <option style="display: none;">En proceso</option>
						    <option value="4">Terminado</option>
						    <option value="3">Cancelado</option>
						</select>
						</form>
		 			</td>
		 			<td style="width:115px; text-align: center; vertical-align:middle;">
		 				<form action="Pedido" method="post">
		 					<button type="button" style="background-color:transparent; border-color:transparent; outline:transparent;" onclick="window.open('Pedido?accion=DetallePedido&&id=<%=pedido.getCodPedido()%>','Detalle Pedido','toolbar=no,location=no,menubar=no,scrollbars=NO,height=695,width=900')">
		 					<img alt="" src="<%=request.getContextPath()%>/images/detalle.png"></button>
		 				</form>
		 			</td>
	 			</tr>
 			</tbody>
  			</table>
  		<%}else{%>
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
	 			<tr>
	 			<td style="width:150px; text-align: center; vertical-align:middle;"><%=pedido.getCodPedido()%></td>
	 			<td style="width:470px; text-align: center; vertical-align:middle;"><%=pedido.getPersona().getNombres()+" "+pedido.getPersona().getApellidoPaterno()+" "+pedido.getPersona().getApellidoMaterno()%></td>
	 			<td style="width:150px; text-align: center; vertical-align:middle;"><%=pedido.getFechaPedido()%></td>
	 			<td style="width:150px; text-align: center; vertical-align:middle;"><%=pedido.getHoraPedido()%></td>
	 			<td style="width:140px; text-align: center; vertical-align:middle;"><%=pedido.getEstadoPedido().getDescripcion()%></td>
	 			<td style="width:115px; text-align: center; vertical-align:middle;">
	 				<form action="Pedido" method="post">
		 				<button type="button" style="background-color:transparent; border-color:transparent; outline:transparent;" onclick="window.open('Pedido?accion=DetallePedido&&id=<%=pedido.getCodPedido()%>','Detalle Pedido','toolbar=no,location=no,menubar=no,scrollbars=NO,height=695,width=900')">
		 				<img alt="" src="<%=request.getContextPath()%>/images/detalle.png"></button>
		 			</form>
	 			</td>
	 			</tr>
	 		</tbody>
  			</table>
  		<%}%>
	</div>
</div>
<%}%>
</div>
</body>
</html>
<%}%>